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
package org.cip4.jdflib.extensions;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFSubscription;
import org.cip4.jdflib.jmf.JMFBuilder;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
 * superclass for audits and messages
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class MessageHelper extends BaseXJDFHelper
{
	public enum EFamily
	{
		Audit, Command, Query, Response, Signal
	};

	/**
	 * @param audit
	 */
	public MessageHelper(final KElement mes)
	{
		theElement = mes;
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#cleanUp()
	 */
	@Override
	public void cleanUp()
	{
		super.cleanUp();
		ensureHeader(theElement);
		final VElement v = theElement.getChildrenByTagName(XJDFConstants.ResourceSet, null, null, false, true, 0);
		if (v != null)
		{
			for (final KElement e : v)
			{
				new SetHelper(e).cleanUp();
				e.removeAttribute(AttributeName.ID);
				final VElement vRes = e.getChildElementVector(ElementName.RESOURCE, null);
				if (vRes != null)
				{
					for (final KElement res : vRes)
					{
						res.removeAttribute(AttributeName.ID);
					}
				}
			}
		}
	}

	/**
	 *
	 * @param message
	 * @return
	 */
	static KElement ensureHeader(final KElement message)
	{
		final KElement header = message.getCreateElement(XJDFConstants.Header);
		header.appendAnchor(null);
		if (!header.hasAttribute(AttributeName.TIME))
		{
			header.setAttribute(AttributeName.TIME, new JDFDate().getDateTimeISO());
		}
		final JMFBuilder jmfBuilder = JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF);
		if (!header.hasAttribute(AttributeName.DEVICEID))
		{
			String senderID = jmfBuilder.getSenderID();
			if (StringUtil.getNonEmpty(senderID) == null)
			{
				senderID = JDFJMF.getTheSenderID();
			}
			if (StringUtil.getNonEmpty(senderID) == null)
			{
				senderID = StringUtil.replaceString(JDFAudit.getStaticAgentName(), JDFConstants.BLANK, JDFConstants.UNDERSCORE);
			}
			header.setAttribute(AttributeName.DEVICEID, senderID);
		}
		if (!header.hasAttribute(AttributeName.AGENTNAME))
		{
			header.setAttribute(AttributeName.AGENTNAME, jmfBuilder.getAgentName());
		}
		if (!header.hasAttribute(AttributeName.AGENTVERSION))
		{
			header.setAttribute(AttributeName.AGENTVERSION, jmfBuilder.getAgentVersion());
		}
		return header;
	}

	/**
	 * get the header of this - create if not there yet
	 *
	 * @return
	 */
	public KElement getHeader()
	{
		return ensureHeader(theElement);
	}

	/**
	 * get the header of this - create if not there yet
	 *
	 * @return
	 */
	public String getDeviceID()
	{
		return ensureHeader(theElement).getNonEmpty(AttributeName.DEVICEID);
	}

	/**
	 *
	 * @return
	 */
	public int getReturnCode()
	{
		if (!isResponse())
			return -1;
		final String rc = getAttribute(AttributeName.RETURNCODE);
		return StringUtil.parseInt(rc, 0);
	}

	/**
	 *
	 * @param url
	 * @return null if we ain't no query, else the subscription which can be further updated
	 */
	public JDFSubscription subscribe(final String url)
	{
		if (!isQuery())
			return null;
		final JDFSubscription sub = (JDFSubscription) getCreateElement(ElementName.SUBSCRIPTION);
		sub.setURL(url);
		return sub;
	}

	/**
	 *
	 * @return
	 */
	public boolean isQuery()
	{
		return getLocalName().startsWith(ElementName.QUERY);
	}

	/**
	 *
	 * @return
	 */
	public static boolean isMessage(final KElement element)
	{
		final String localName = element == null ? null : element.getLocalName();
		if (localName == null)
		{
			return false;
		}
		return new MessageHelper(element).getFamily() != null;
	}

	/**
	 *
	 * @return
	 */
	public boolean isCommand()
	{
		return getLocalName().startsWith(ElementName.COMMAND);
	}

	/**
	 *
	 * @return
	 */
	public boolean isResponse()
	{
		return getLocalName().startsWith(ElementName.RESPONSE);
	}

	/**
	 *
	 * @return
	 */
	public boolean isAudit()
	{
		return getLocalName().startsWith(ElementName.AUDIT);
	}

	/**
	 *
	 * @return
	 */
	public boolean isSignal()
	{
		return getLocalName().startsWith(ElementName.SIGNAL);
	}

	/**
	 *
	 * @param hQuery the query to set the refID to
	 *
	 */
	public void setQuery(final MessageHelper hQuery)
	{
		if (!isResponse() && !isSignal() || hQuery == null || (hQuery.isResponse() || hQuery.isSignal()))
			return;
		final String id = ensureHeader(hQuery.theElement).appendAnchor(null);
		ensureHeader(theElement).setAttribute(AttributeName.REFID, id);
	}

	public EFamily getFamily()
	{
		if (isCommand())
			return EFamily.Command;
		else if (isQuery())
			return EFamily.Query;
		else if (isSignal())
			return EFamily.Signal;
		else if (isResponse())
			return EFamily.Response;
		else if (isAudit())
			return EFamily.Audit;
		return null;

	}

	/**
	 *
	 * @return
	 */
	public String getType()
	{
		final EFamily f = getFamily();
		final String name = getLocalName();
		return (f == null || f.name().equals(name)) ? null : StringUtil.rightStr(name, -f.name().length());

	}

}
