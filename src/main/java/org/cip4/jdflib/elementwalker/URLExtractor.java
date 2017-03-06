/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
/**
 *
 */
package org.cip4.jdflib.elementwalker;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.ifaces.IElementConverter;
import org.cip4.jdflib.ifaces.IURLSetter;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.UrlUtil.URLProtocol;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * walker that extracts all URLs and dumps them to a directory
 * URLS are modified to reflect the new location
 */
public class URLExtractor extends BaseElementWalker implements IElementConverter
{

	/**
	 * the URL walker
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 */
	public class WalkElement extends BaseWalker
	{
		/**
		 *
		 */
		public WalkElement()
		{
			super(getFactory());
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e
		 * @param trackElem
		 * @return
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			return e;
		}
	}

	protected final File dir;
	protected final String baseURL;
	protected Set<URLProtocol> protocols;
	protected final String currentURL;
	protected final Set<String> saved;
	protected boolean deleteFile;

	/**
	 * @return the deleteFile
	 */
	public boolean isDeleteFile()
	{
		return deleteFile;
	}

	/**
	 * @param deleteFile the deleteFile to set; if true files are move rather than copied
	 * note that files are NOT removed from zip or mime packages
	 */
	public void setDeleteFile(boolean deleteFile)
	{
		this.deleteFile = deleteFile;
	}

	/**
	 * Getter for list of saved files
	 * @return the saved
	 */
	public Set<String> getSaved()
	{
		return saved;
	}

	private boolean wantLog;

	/**
	 * @param dumpDir the local directory where any files are dumped
	 * @param currentURL the current local input url for relative urls - in general this will be a file url (cwd)
	 * @param baseURL the base output url of the extracted data, for instance in an http server environment
	 */
	public URLExtractor(final File dumpDir, final String currentURL, final String baseURL)
	{
		super(new BaseWalkerFactory());
		dir = dumpDir;
		this.baseURL = baseURL;
		this.currentURL = currentURL;
		saved = new HashSet<String>();
		protocols = null;
		setWantLog(false);
	}

	/**
	 *
	 *
	 * @param bWant if true, we will log each move
	 *
	 */
	public void setWantLog(boolean bWant)
	{
		wantLog = bWant;
	}

	/**
	 * add a protocol to the list of protocols that are supported
	 * @param protocol the protocol to add
	 */
	public void addProtocol(URLProtocol protocol)
	{
		if (protocols == null)
			protocols = new HashSet<URLProtocol>();
		protocols.add(protocol);
	}

	/**
	 * the resource walker note the naming convention Walkxxx so that it is automagically instantiated by the super classes
	 *
	 * @author prosirai
	 *
	 */
	public class WalkURL extends WalkElement
	{

		/**
		 * fills this into the factory
		 */
		public WalkURL()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e the element to walk over
		 * @param trackElem - unused should be null
		 * @return the element to continue walking
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			IURLSetter urlSetter = (IURLSetter) e;
			String url = StringUtil.getNonEmpty(urlSetter.getURL());
			if (url == null)
			{
				return e;
			}
			// we have a circular reference to something we put here ourselves - no need to do anything
			if (baseURL != null && url.startsWith(baseURL))
				return e;

			if (protocols != null)
			{
				URLProtocol protocol = UrlUtil.getProtocol(url);
				if (!protocols.contains(protocol))
				{
					return e;
				}
			}
			boolean bOverwrite = !saved.contains(url);
			final File newFile = UrlUtil.moveToDir(urlSetter, dir, currentURL, bOverwrite, deleteFile);
			if (newFile != null)
			{
				if (baseURL != null)
				{
					String s = UrlUtil.isRelativeURL(url) ? url : UrlUtil.escape(newFile.getName(), false);
					String urlWithDirectory = UrlUtil.getURLWithDirectory(baseURL, s);
					urlSetter.setURL(urlWithDirectory);
				}
				if (wantLog && bOverwrite)
				{
					log.info("copied " + url + " to " + urlSetter.getURL());
				}
			}
			else if (wantLog)
			{
				log.warn("Could not copy " + url + " to " + dir);
			}
			saved.add(url);
			return e; //  continue
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof IURLSetter);
		}
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IElementConverter#convert(org.cip4.jdflib.core.KElement)
	 */
	@Override
	public KElement convert(KElement e)
	{
		walkTree(e, null);
		return e;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.ElementWalker#toString()
	 */
	@Override
	public String toString()
	{
		return super.toString() + "\n " + protocols + " baseURL: " + baseURL + " currentURL: " + currentURL + " dir: " + dir + " delete: " + deleteFile;
	}
}
