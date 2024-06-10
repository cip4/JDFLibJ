/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */

package org.cip4.jdflib.elementwalker;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.security.CodeSource;
import java.util.List;
import java.util.zip.ZipEntry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.ListMap;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.zip.ZipReader;

/**
 * this class is similar to BaseElementWalker but searches it package rather than the local walker classes elementwalker class that allows you to traverse a dom tree starting at a
 * given root also handles the construction of the walker classes by name, just make sure that your walker subclasses match the naming convention Walk<name> and reside in one of
 * the declared packages, e.g. if your class is called FixVersion, the classes in the same package must be called WalkFoo, WalkBar etc.
 *
 * @author rainer prosi
 *
 */
public class PackageElementWalker extends ElementWalker
{
	final private static Log slog = LogFactory.getLog(PackageElementWalker.class); // final protected Log log = LogFactory.getLog(getClass());

	/**
	 *
	 */
	private static final String WALK_CLASS = "Walk*.class";

	static ListMap<Class<?>, String> classes = null;

	/**
	 * @param _theFactory
	 */
	public PackageElementWalker(final BaseWalkerFactory _theFactory)
	{
		super(_theFactory);
		if (classes == null)
			classes = new ListMap<>();
		constructWalkers();
	}

	/**
	 * construct all walkers confirming to the naming convention public package.Walk<xxx>
	 *
	 * @param classPrefix
	 */
	private void constructWalkers()
	{
		// we don't want any nasty race conditions where we construct from incomplete class name vectors
		synchronized (classes)
		{
			final Class<?> parent = getClass();
			if (classes.get(parent) != null)
			{
				constructWorkersVClass(classes.get(parent));
			}
			else
			{
				final CodeSource codesrc = parent.getProtectionDomain().getCodeSource();
				final URL packsrc = codesrc.getLocation();
				slog.info("Constructing walkers for package URL: " + packsrc.toExternalForm());
				final File f = UrlUtil.urlToFile(UrlUtil.urlToString(packsrc));
				if (f.isDirectory())
				{
					constructWorkersDir(f);
				}
				else
				{
					constructWorkersJar(f);
				}
			}
		}
	}

	/**
	 * @param classVector
	 *
	 */
	private void constructWorkersVClass(final List<String> classVector)
	{
		for (final String classConst : classVector)
		{
			constructWalker(classConst);
		}
	}

	/**
	 *
	 * @param jarFile
	 */
	private void constructWorkersJar(final File jarFile)
	{
		final ZipReader zr = ZipReader.getZipReader(jarFile);
		if (zr == null)
		{
			slog.error("Could not unpack zip file: " + jarFile);
		}
		else
		{
			Class<? extends PackageElementWalker> currentClass = getClass();
			final Class<? extends PackageElementWalker> baseClass = currentClass;
			slog.info("constructing from jar: " + jarFile);
			while (currentClass != null)
			{
				final String packageName = currentClass.getPackage().getName();
				final String packagePath = StringUtil.replaceChar(packageName, '.', "/", 0);
				final String classExpr = packagePath + "/" + WALK_CLASS;
				zr.buffer();
				while (true)
				{
					final ZipEntry ze = zr.getNextMatchingEntry(classExpr);
					if (ze == null)
						break;
					processSingleEntry(baseClass, packageName, packagePath, ze);
				}
				currentClass = getParentClass(currentClass);
			}
		}
	}

	void processSingleEntry(final Class<? extends PackageElementWalker> baseClass, final String packageName, final String packagePath, final ZipEntry ze)
	{
		final String entryName = ZipReader.getEntryName(ze);
		final String className = StringUtil.token(entryName, -1, "/");
		final String zipPackageName = StringUtil.leftStr(entryName, -1 - className.length());
		if (packagePath.equals(zipPackageName))
		{
			final String name = packageName + "." + UrlUtil.newExtension(className, null);
			if (name.indexOf('$') < 0)
			{
				constructWalker(name);
				classes.putOne(baseClass, name);
			}
		}
	}

	/**
	 *
	 * @param dir
	 */
	private void constructWorkersDir(final File dir)
	{
		Class<? extends PackageElementWalker> currentClass = getClass();
		final Class<? extends PackageElementWalker> baseClass = currentClass;
		while (currentClass != null)
		{
			final String packageName = currentClass.getPackage().getName();
			String packagePath = StringUtil.replaceChar(packageName, '.', File.separator, 0);
			packagePath = dir.getAbsolutePath() + File.separator + packagePath;
			final File[] classFiles = FileUtil.listFilesWithExpression(new File(packagePath), WALK_CLASS);
			if (classFiles != null)
			{
				for (final File f : classFiles)
				{
					String name = f.getName();
					name = UrlUtil.prefix(name);
					if (name.indexOf('$') > 0)
						continue;
					name = packageName + "." + name;
					final BaseWalker w = constructWalker(name);
					if (w == null)
					{
						slog.warn("could not construct class: " + name);
					}
					classes.putOne(baseClass, name);
				}
			}
			currentClass = getParentClass(currentClass);
		}
	}

	/**
	 *
	 *
	 * @param currentClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Class<? extends PackageElementWalker> getParentClass(Class<? extends PackageElementWalker> currentClass)
	{
		final Class<?> nextClass = currentClass.getSuperclass();
		if (PackageElementWalker.class.isAssignableFrom(nextClass))
			currentClass = (Class<? extends PackageElementWalker>) nextClass;
		else
			currentClass = null;
		return currentClass;
	}

	/**
	 *
	 *
	 * @param name
	 * @return
	 */
	protected BaseWalker constructWalker(final String name)
	{
		try
		{
			final Class<?> newClass = Class.forName(name);
			final Constructor<?> con = newClass.getConstructor();
			final BaseWalker w = (BaseWalker) con.newInstance();
			w.addToFactory(getFactory());
			return w;
		}
		catch (final Throwable e)
		{
			slog.warn("Cannot construct class: " + name, e);
		}
		return null;
	}

	/**
	 * @return the factory for this worker
	 */
	@Override
	public BaseWalkerFactory getFactory()
	{
		return (BaseWalkerFactory) theFactory;
	}

}
