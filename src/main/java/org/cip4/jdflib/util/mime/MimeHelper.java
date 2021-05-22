/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.util.mime;

import java.util.Vector;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * class to create and write mime files
 *
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         July 24, 2009
 */
public class MimeHelper
{
	protected Multipart theMultipart;
	private static boolean bNeedAParser = true;
	protected int markSize;
	protected final Log log;

	/**
	 *
	 */
	public MimeHelper()
	{
		super();
		log = LogFactory.getLog(getClass());
		// there is a bug in xerces that screws up the reference count for shared files when the static stuff in domparser is initialized.
		// make sure that this happens prior to any mime related tasks and all is well
		if (bNeedAParser)
		{
			bNeedAParser = false;
			new JDFParser();
		}
		theMultipart = null;
		markSize = 1000000;
	}

	/**
	 * @param mp
	 */
	public MimeHelper(final Multipart mp)
	{
		this();
		theMultipart = mp;
	}

	/**
	 * @return
	 */
	public int getCount()
	{
		try
		{
			return theMultipart == null ? 0 : theMultipart.getCount();
		}
		catch (final MessagingException e)
		{
			log.error("Cannot get count", e);
			return 0;
		}
	}

	/**
	 * @return
	 */
	public Multipart getMultiPart()
	{
		return theMultipart;
	}

	/**
	 * get the MIME BodyPart from a multiPart package with a given cid create one if it does not exist;
	 *
	 * @param cid the cid of the requested bodypart
	 * @return BodyPart the matching BodyPart, null if none is found
	 */
	public BodyPart getCreatePartByCID(final String cid)
	{
		BodyPart bp = getPartByCID(cid);
		if (bp != null)
		{
			return bp;
		}

		final BodyPartHelper bph = new BodyPartHelper();
		try
		{
			bph.setContentID(cid);
			theMultipart.addBodyPart(bph.getBodyPart());
			bp = bph.getBodyPart();
		}
		catch (final MessagingException x)
		{
			log.error("Cannot create part; cid=" + cid, x);
		}
		return bp;
	}

	/**
	 * get the MIME BodyPart from a multiPart package with a given cid create one if it does not exist;
	 *
	 * @param name the cid of the requested bodypart
	 * @return BodyPart the matching BodyPart, null if none is found
	 */
	public BodyPartHelper getCreatePartByLocalName(final String name)
	{
		BodyPartHelper bph = getPartHelperByLocalName(name);
		if (bph == null)
		{
			bph = new BodyPartHelper();
			try
			{
				bph.setFileName(name);
				theMultipart.addBodyPart(bph.getBodyPart());
			}
			catch (final MessagingException x)
			{
				log.error("Cannot create part; name=" + name, x);
			}
		}
		return bph;
	}

	/**
	 * get the MIME BodyPart from a multiPart package with a given cid
	 *
	 * @param cid the cid of the requested bodypart
	 * @return BodyPart the matching BodyPart, null if none is found
	 */
	public BodyPart getPartByCID(final String cid)
	{
		final BodyPartHelper bph = getPartHelperByCID(cid);
		return bph == null ? null : bph.getBodyPart();
	}

	/**
	 * get the MIME BodyPart from a multiPart package with a given cid
	 *
	 * @param cid the cid of the requested bodypart
	 * @return BodyPartHelper the matching BodyPart, null if none is found
	 */
	public BodyPartHelper getPartHelperByCID(String cid)
	{
		cid = StringUtil.getNonEmpty(cid);
		if (UrlUtil.isNotCID(cid))
		{
			log.debug("incorrect CID format: cid=" + cid);
			return null;
		}
		try
		{
			for (int i = 0; true; i++)
			{
				final BodyPartHelper bph = getBodyPartHelper(i);
				if (bph == null)
				{
					return null;
				}
				if (bph.matchesCID(cid))
				{
					return bph;
				}
			}
		}
		catch (final ArrayIndexOutOfBoundsException e)
		{
			// we catch the exception rather than calculate length because calculating length requires a full parse of the entire stream
			return null;
		}
	}

	/**
	 * 	get the MIME BodyPart from a multiPart package with a given header
	*
	* @return BodyPartHelper the matching BodyPart, null if none is found
	*/
	public BodyPartHelper getPartHelperByAttribute(final String key, final String value)
	{
		try
		{
			for (int i = 0; true; i++)
			{
				final BodyPartHelper bph = getBodyPartHelper(i);
				if (bph == null)
				{
					return null;
				}
				if (bph.matchesKey(key, value))
				{
					return bph;
				}
			}
		}
		catch (final ArrayIndexOutOfBoundsException e)
		{
			// we catch the exception rather than calculate length because calculating length requires a full parse of the entire stream
			return null;
		}
	}

	/**
	 * @param i
	 * @return
	 */
	public BodyPartHelper getBodyPartHelper(final int i)
	{
		if (theMultipart == null)
		{
			return null;
		}
		try
		{
			final BodyPart bp = theMultipart.getBodyPart(i);
			return new BodyPartHelper(bp);
		}
		catch (final MessagingException e)
		{
			return null;
		}
	}

	/**
	 * get all the parts of of a multipart an
	 *
	 * @return the array of parts, null if snafu...
	 */
	public BodyPart[] getBodyParts()
	{
		final Vector<BodyPart> v = new Vector<>();
		try
		{
			for (int i = 0; true; i++)
			{
				final BodyPart bp = theMultipart.getBodyPart(i);
				v.add(bp);
			}

		}
		catch (final MessagingException m)
		{
			return null;
		}
		// this may seem messy, but getCount() can be very costly since it
		// requires a complete mime parse.
		// simply getting the next reduces the time by a factor 2, since only
		// one linear parse is required
		catch (final ArrayIndexOutOfBoundsException m)
		{
			if (v.size() == 0)
			{
				return null;
			}
			BodyPart[] ret = new BodyPart[v.size()];
			ret = v.toArray(ret);
			return ret;
		}
	}

	/**
	 * @return the markSize, i.e. the maximum stream mark for resetting
	 */
	public int getMarkSize()
	{
		return markSize;
	}

	/**
	 * the maximum stream mark for resetting
	 *
	 * @param markSize the markSize to set
	 */
	public void setMarkSize(final int markSize)
	{
		this.markSize = markSize;
	}

	public BodyPartHelper getPartHelperByLocalName(String name)
	{
		name = StringUtil.getNonEmpty(name);
		if (UrlUtil.isRelativeURL(name))
		{
			log.debug("incorrect URL local  format: url=" + name);
			return null;
		}
		try
		{
			for (int i = 0; true; i++)
			{
				final BodyPartHelper bph = getBodyPartHelper(i);
				if (bph == null)
				{
					return null;
				}
				if (bph.matchesFileName(name))
				{
					return bph;
				}
			}
		}
		catch (final ArrayIndexOutOfBoundsException e)
		{
			// we catch the exception rather than calculate length because calculating length requires a full parse of the entire stream
		}
		return null;
	}
}
