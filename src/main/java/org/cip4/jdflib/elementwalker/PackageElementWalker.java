/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */

package org.cip4.jdflib.elementwalker;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.security.CodeSource;
import java.util.Vector;
import java.util.zip.ZipEntry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.VectorMap;
import org.cip4.jdflib.util.zip.ZipReader;

/**
 * this class is similar to BaseElementWalker but searches it package rather than the local walker classes
 * elementwalker class that allows you to traverse a dom tree starting at a given root also handles the construction of the walker classes by name, just make
 * sure that your walker subclasses match the naming convention Walk<name> and reside in one of the declared packages, e.g. if your class is called FixVersion, the classes in the same package must be called WalkFoo, WalkBar etc.
 * 
 * @author rainer prosi
 * 
 */
public class PackageElementWalker extends ElementWalker
{
	/**
	 * 
	 */
	private static final String WALK_CLASS = "Walk*.class";
	//	private static final String WALK_CLASS = "*.class";
	final protected Log log;
	static VectorMap<Class<?>, String> classes = null;

	/**
	 * @param _theFactory
	 */
	public PackageElementWalker(final BaseWalkerFactory _theFactory)
	{
		super(_theFactory);
		Class<? extends PackageElementWalker> myClass = getClass();
		log = LogFactory.getLog(myClass);
		if (classes == null)
			classes = new VectorMap<Class<?>, String>();
		constructWalkers();
	}

	/**
	 * construct all walkers confirming to the naming convention public package.Walk<xxx>
	 * 
	 * @param classPrefix
	 */
	private void constructWalkers()
	{
		Class<?> parent = getClass();
		CodeSource codesrc = parent.getProtectionDomain().getCodeSource();
		URL packsrc = codesrc.getLocation();
		File f = UrlUtil.urlToFile(UrlUtil.urlToString(packsrc));
		if (classes.get(parent) != null)
		{
			constructWorkersVClass(classes.get(parent));
		}
		else
		{
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

	/**
	 * @param classVector 
	 *  
	 */
	private void constructWorkersVClass(Vector<String> classVector)
	{
		for (String classConst : classVector)
		{
			constructWalker(classConst);
		}

	}

	/**
	 *  
	 * @param jarFile
	 */
	private void constructWorkersJar(File jarFile)
	{
		ZipReader zr = new ZipReader(jarFile);
		Class<? extends PackageElementWalker> currentClass = getClass();
		Class<? extends PackageElementWalker> baseClass = currentClass;
		while (currentClass != null)
		{
			String packageName = currentClass.getPackage().getName();
			String packagePath = StringUtil.replaceChar(packageName, '.', "/", 0);
			String classExpr = packagePath + "/" + WALK_CLASS;
			zr.buffer();
			while (true)
			{
				ZipEntry ze = zr.getNextMatchingEntry(classExpr);
				if (ze == null)
					break;
				String name = ZipReader.getEntryName(ze);
				String className = StringUtil.token(name, -1, "/");
				String zipPackageName = StringUtil.leftStr(name, -1 - className.length());
				if (packagePath.equals(zipPackageName))
				{
					String fullClassName = packageName + "." + UrlUtil.newExtension(className, null);
					log.info("constructing " + fullClassName);
					constructWalker(fullClassName);
					classes.putOne(baseClass, name);
				}
			}
			currentClass = getParentClass(currentClass);
		}
	}

	/**
	 * 
	 * @param dir
	 */
	private void constructWorkersDir(File dir)
	{
		Class<? extends PackageElementWalker> currentClass = getClass();
		Class<? extends PackageElementWalker> baseClass = currentClass;
		while (currentClass != null)
		{
			String packageName = currentClass.getPackage().getName();
			String packagePath = StringUtil.replaceChar(packageName, '.', "/", 0);
			packagePath = dir.getAbsolutePath() + "/" + packagePath;
			File[] classFiles = FileUtil.listFilesWithExpression(new File(packagePath), WALK_CLASS);
			if (classFiles != null)
			{
				for (File f : classFiles)
				{
					String name = f.getName();
					name = UrlUtil.prefix(name);
					name = packageName + "." + name;
					constructWalker(name);
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
	private Class<? extends PackageElementWalker> getParentClass(Class<? extends PackageElementWalker> currentClass)
	{
		Class<?> nextClass = currentClass.getSuperclass();
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
	protected BaseWalker constructWalker(String name)
	{
		try
		{
			Class<?> newClass = Class.forName(name);
			final Constructor<?> con = newClass.getConstructor();
			BaseWalker w = (BaseWalker) con.newInstance();
			w.addToFactory(getFactory());
			return w;
		}
		catch (Throwable e)
		{
			log.warn("Cannot construct class: " + name, e);
		}
		return null;
	}

	/**
	 * @return the factory for this worker
	 */
	protected BaseWalkerFactory getFactory()
	{
		return (BaseWalkerFactory) theFactory;
	}

}
