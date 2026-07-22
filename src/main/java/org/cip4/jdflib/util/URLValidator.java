/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2026 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.StringArray;

public class URLValidator
{
	private static final String LOCALHOST = "localhost";

	final static Log log = LogFactory.getLog(URLValidator.class);

	protected static final HashSet<String> validhosts = new HashSet<>();

	protected static EPackage packMethod = EPackage.PACKAGE;
	protected final String urlString;
	protected final List<File> localRoots;
	protected File notRelative;

	protected EPackage localPackMethod;

	Throwable lastException;

	public URLValidator(String urlString)
	{
		this.urlString = UrlUtil.normalize(urlString);
		localRoots = new ArrayList<>();
		notRelative = null;
		localPackMethod = null;
		lastException = null;
	}

	/**
	 * add a trusted host
	 *
	 * @param host the hostname to add
	 * @return the current list of valid hosts
	 */
	public static Collection<String> addLocal()
	{
		return addHost(LOCALHOST);
	}

	/**
	 * add a trusted host
	 *
	 * @param host the hostname to add
	 * @return the current list of valid hosts
	 */
	public static Collection<String> addHost(String host)
	{
		final String hostLower = StringUtil.getNonEmpty(StringUtil.toLowerCase(host));
		return ContainerUtil.appendUnique(validhosts, hostLower);
	}

	public static boolean removeHost(String host)
	{
		return validhosts.remove(StringUtil.toLowerCase(host));
	}

	public static void clearHosts()
	{
		validhosts.clear();
	}

	public static class InvalidHostException extends RuntimeException
	{

		public InvalidHostException(String message)
		{
			super(message);
		}

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

	}

	/**
	 * the default is to allow all
	 */
	public static void initFilters()
	{
		clearHosts();
		validhosts.add(JDFConstants.STAR);
		packMethod = EPackage.PACKAGE;
	}

	public static EPackage getPackMethod()
	{
		return packMethod;
	}

	public static void setPackMethod(EPackage packMethod)
	{
		if (packMethod != null)
		{
			URLValidator.packMethod = packMethod;
		}
	}

	public boolean checkHost()
	{
		return checkHost(urlString);
	}

	public boolean checkLocalHost()
	{
		return checkHost(LOCALHOST);
	}

	public boolean checkHost(String urlStr)
	{
		if (validhosts.contains(JDFConstants.STAR))
		{
			return true;
		}
		else if (validhosts.isEmpty())
		{
			return false;
		}
		try
		{
			final StringArray hosts = new StringArray();
			if (fillHosts(urlStr, hosts))
			{
				return true;
			}
			for (final String validHost : validhosts)
			{
				for (final String host : hosts)
				{
					if (StringUtil.matchesIgnoreCase(host, validHost, true))
					{
						return true;
					}
				}
			}
		}
		catch (final MalformedURLException e)
		{
			// nop
		}
		return false;
	}

	boolean fillHosts(String urlStr, final StringArray hosts) throws MalformedURLException
	{
		try
		{
			if (UrlUtil.isRelativeURL(urlStr))
			{
				hosts.add(InetAddress.getLocalHost().getHostName());
				hosts.add(LOCALHOST);
			}
			else
			{
				String host = new URL(urlStr).getHost();
				if ("".equals(host))
				{
					host = LOCALHOST;
				}
				hosts.add(host);
			}
			for (final String validHost : validhosts)
			{
				for (final String host : hosts)
				{
					if (StringUtil.matchesIgnoreCase(host, validHost, true))
					{
						return true;
					}
				}
			}
			for (final String host : new StringArray(hosts))
			{
				final InetAddress[] allByName = InetAddress.getAllByName(host);
				for (final InetAddress a : allByName)
				{
					ContainerUtil.add(hosts, a.getHostAddress());
					ContainerUtil.add(hosts, a.getHostName());
					ContainerUtil.add(hosts, a.getCanonicalHostName());
				}
			}
			hosts.unify();
		}
		catch (final UnknownHostException e)
		{
			// nop
		}
		return false;
	}

	/**
	 * add a root for local files
	 *
	 * @param root
	 */
	public void addLocalRoot(final File root)
	{
		if (root == null)
		{
			log.error("cannot add null root");
		}
		else if (checkLocalHost())
		{
			ContainerUtil.appendUnique(localRoots, root);
		}
	}

	/**
	 * @return
	 */
	File getRelativeFile()
	{
		if (UrlUtil.isRelativeURL(urlString) && checkHost())
		{
			try
			{
				final File fLocal = UrlUtil.urlToFile(UrlUtil.getSecurePath(urlString, false));
				for (final File root : localRoots)
				{
					final File f = FileUtil.getFileInDirectory(root, fLocal);
					if (f != null && f.canRead())
					{
						notRelative = f;
						return notRelative;
					}
				}
			}
			catch (final IllegalArgumentException e)
			{
				log.error("illegal url " + urlString);
			}

		}
		return null;
	}

	URL getUrl()
	{
		return UrlUtil.stringToURL(urlString);
	}

	/**
	 * override for the static default
	 *
	 * @return
	 */
	public EPackage getLocalPackMethod()
	{
		return localPackMethod;
	}

	/**
	 * override for the static default
	 *
	 * @return
	 */
	public EPackage getCurrentPackMethod()
	{
		return localPackMethod == null ? getPackMethod() : localPackMethod;
	}

	/**
	 * override for the static default
	 *
	 * @param localPackMethod
	 */
	public void setLocalPackMethod(EPackage localPackMethod)
	{
		this.localPackMethod = localPackMethod;
	}

	public File getFile()
	{
		File file = getAbsoluteFile();
		if (file == null)
		{
			file = getRelativeFile();
		}
		return file;
	}

	/**
	 * @return
	 */
	File getAbsoluteFile()
	{
		if (checkHost())
		{
			try
			{
				final File f = !UrlUtil.isNet(urlString) && !UrlUtil.isCID(urlString) ? UrlUtil.urlToFile(UrlUtil.getSecurePath(urlString, true)) : null;
				if ((f != null) && f.canRead())
				{
					if (localRoots.isEmpty())
					{
						return f;
					}
					else
					{
						for (final File root : localRoots)
						{
							if (FileUtil.isParentFile(f, root))
							{
								return f;
							}
						}
					}
				}
			}
			catch (final IllegalArgumentException e)
			{
				log.error("illegal absolute url " + urlString);
			}
		}
		return null;
	}

	public Throwable getLastException()
	{
		return lastException;
	}

	static
	{
		initFilters();
	}

	public enum EPackage
	{
		MIME, ZIP, PACKAGE, NONE;

		public static EPackage getEnum(String s)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EPackage.class, s, EPackage.PACKAGE);
		}
	}
}
