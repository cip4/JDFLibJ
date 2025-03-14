/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 *
 */
package org.cip4.jdflib.elementwalker;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.ifaces.IElementConverter;
import org.cip4.jdflib.ifaces.IURLSetter;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker that extracts all URLs and dumps them to a directory URLS are modified to reflect the new location
 */
public class URLMapper extends BaseElementWalker implements IElementConverter
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

	private final String baseIn;
	private final String baseOut;
	private boolean wantLog;

	/**
	 * @param baseIn the input (to be modified) base url
	 * @param baseOut the output base url
	 *
	 */
	public URLMapper(final String baseIn, final String baseOut)
	{
		super(new BaseWalkerFactory());
		this.baseIn = baseIn;
		this.baseOut = baseOut;
		wantLog = false;
	}

	/**
	 * @return the wantLog
	 */
	public boolean isWantLog()
	{
		return wantLog;
	}

	/**
	 * @param wantLog the wantLog to set
	 */
	public void setWantLog(final boolean wantLog)
	{
		this.wantLog = wantLog;
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
			final IURLSetter u = (IURLSetter) e;
			final String url = StringUtil.getNonEmpty(u.getURL());
			if (url != null)
			{
				final String relativeURL = UrlUtil.getLocalURL(baseIn, url);
				if (relativeURL != null)
				{
					final String newUrl = UrlUtil.getURLWithDirectory(baseOut, relativeURL);
					u.setURL(newUrl);
					if (wantLog)
					{
						log.info("renaming url from: " + url + " to " + newUrl);
					}
				}
			}
			return e;
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
	public KElement convert(final KElement e)
	{
		walkTree(e, null);
		return e;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return super.toString() + " baseIn=" + baseIn + ", baseOut=" + baseOut + "]";
	}
}
