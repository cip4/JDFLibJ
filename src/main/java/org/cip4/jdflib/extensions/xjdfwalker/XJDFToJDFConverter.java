/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.XJDFToJDFImpl;
import org.cip4.jdflib.ifaces.IXJDFSplit;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.process.JDFColor;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class XJDFToJDFConverter extends XJDFToJDFImpl
{
	final private static Log log = LogFactory.getLog(XJDFToJDFConverter.class);

	protected IXJDFSplit splitter;
	private static StringArray resLinkAttribs;
	public static final String SIG = "Sig_";

	public static StringArray getResLinkAttribs()
	{
		if (resLinkAttribs == null)
		{
			resLinkAttribs = new StringArray(new String[] { AttributeName.PROCESSUSAGE, AttributeName.AMOUNT, AttributeName.ACTUALAMOUNT, AttributeName.MAXAMOUNT,
					AttributeName.MINAMOUNT, AttributeName.COMBINEDPROCESSINDEX });

		}
		return resLinkAttribs;
	}

	/**
	 * @param template the jdfdoc to fill this into
	 *
	 */
	public XJDFToJDFConverter(final JDFDoc template)
	{
		super(template);
		splitter = null;
	}

	/**
	 * convert using splitter if appropriate
	 *
	 * @see org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.XJDFToJDFImpl#convert(org.cip4.jdflib.core.KElement)
	 */
	@Override
	public JDFDoc convert(final KElement xjdf)
	{
		JDFDoc d = null;
		if (xjdf != null)
		{
			final XJDFHelper xjdfHelper = presplit(xjdf);

			if (needSplit(xjdfHelper.getRoot()))
			{

				final Collection<XJDFHelper> vSplit = splitter.splitXJDF(xjdfHelper);
				if (vSplit == null || vSplit.size() == 0)
				{
					log.error("no xjdf elements returned by splitter");
				}
				else
				{
					for (final XJDFHelper h : vSplit)
					{
						d = super.convert(h.getRoot());
						setCreateProduct(false);
					}
				}
			}
			else
			{
				d = super.convert(xjdfHelper.getRoot());
			}
			if (d != null)
			{
				mapActualColors(d);
			}
		}
		return d;
	}

	void mapActualColors(final JDFDoc d)
	{
		final KElement root = d.getRoot();

		final Collection<JDFColor> vc = root.getChildArrayByClass(JDFColor.class, true, 0);
		if (vc != null)
		{
			for (final JDFColor c : vc)
			{
				fixColor(root, c);
			}
		}
	}

	void fixColor(final KElement root, final JDFColor c)
	{
		c.removeChildrenByClass(JDFPart.class);
		final String actual = c.getActualColorName();
		final String name = c.getName();
		if (!name.equals(actual))
		{
			c.setName(actual);
			final VElement v = root.getChildrenByTagName(null, null, new JDFAttributeMap(AttributeName.SEPARATION, name), false, true, 0);
			for (final KElement e : v)
			{
				e.setAttribute(AttributeName.SEPARATION, actual);
			}
			final VElement w = root.getChildrenByTagName(ElementName.SEPARATIONSPEC, null, new JDFAttributeMap(AttributeName.NAME, name), false, true, 0);
			for (final KElement e : w)
			{
				e.setAttribute(AttributeName.NAME, actual);
			}
		}
	}

	/**
	 * convenience method
	 *
	 * @param helper
	 * @return
	 */
	public JDFDoc convert(final XJDFHelper helper)
	{
		final KElement e = helper == null ? null : helper.getRoot();
		return e == null ? null : convert(e);
	}

	/**
	 *
	 * @param xjdf
	 * @return
	 */
	protected boolean needSplit(final KElement xjdf)
	{
		return splitter != null && XJDFHelper.isXJDF(xjdf);
	}

	/**
	 *
	 * @param splitter
	 */
	public void setSplitter(final IXJDFSplit splitter)
	{
		this.splitter = splitter;
	}

}
