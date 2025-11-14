/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
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
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFSubscription;
import org.cip4.jdflib.jmf.JMFBuilder;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.util.EnumUtil;
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
		Audit, Command, Query, Response, Signal;

		public static EFamily getEnum(final String name)
		{
			final EFamily ret = EnumUtil.getJavaEnumIgnoreCase(EFamily.class, name);
			if (ret == null && name != null)
			{
				final String sl = name.toLowerCase();
				for (final EFamily f : values())
				{
					if (sl.startsWith(f.name().toLowerCase()))
					{
						return f;
					}
				}

			}
			return ret;
		}

		public String getType(final String base)
		{
			if (StringUtil.length(base) > name().length() && base.toLowerCase().startsWith(name().toLowerCase()))
			{
				return base.substring(name().length());
			}
			return null;
		}

	}

	public enum EType
	{
		ForceGang, GangStatus, KnownDevices, KnownMessages, KnownSubscriptions, ModifyQueueEntry, Notification, PipeControl, QueueStatus, RequestQueueEntry, Resource, ResubmitQueueEntry, ReturnQueueEntry, ShutDown, Status, StopPersistentChannel, SubmitQueueEntry, Wakeup;

		public static EType getEnum(final String name)
		{
			final EType ret = EnumUtil.getJavaEnumIgnoreCase(EType.class, name);
			if (ret == null && name != null)
			{
				final String sl = name.toLowerCase();
				for (final EType t : values())
				{
					if (sl.endsWith(t.name().toLowerCase()))
					{
						return t;
					}
				}

			}
			return ret;
		}

		public String getType(final String base)
		{
			if (StringUtil.length(base) > name().length() && base.toLowerCase().startsWith(name().toLowerCase()))
			{
				return base.substring(name().length());
			}
			return null;
		}

	}

	/**
	 * @param e           the element - needed for subclasses
	 * @param messageName
	 * @param family
	 * @return
	 */
	String getMessageType(final KElement e, final String messageName, final String family)
	{
		final String type = StringUtil.rightStr(messageName, -family.length());
		return type;
	}

	/**
	 * @param audit
	 */
	public MessageHelper(final KElement mes)
	{
		theElement = mes;
	}

	/**
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#cleanUp()
	 */
	@Override
	public void cleanUp()
	{
		super.cleanUp();
		ensureHeader(theElement);
	}

	static String getMessageName(EFamily family, EType typ)
	{
		if (family == null)
		{
			throw new IllegalArgumentException("Family must not be null");
		}
		if (typ == null)
		{
			throw new IllegalArgumentException("Type must not be null");
		}
		return family.name() + typ.name();

	}

	/**
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
	 * @return
	 */
	public int getReturnCode()
	{
		if (!isResponse())
		{
			return -1;
		}
		final String rc = getAttribute(AttributeName.RETURNCODE);
		return StringUtil.parseInt(rc, 0);
	}

	/**
	 * @return
	 * @throws IllegalArgumentException
	 */
	public void setReturnCode(final int rc)
	{
		if (!isResponse())
		{
			throw new IllegalArgumentException("Can only set return code on response");
		}
		setAttribute(AttributeName.RETURNCODE, rc);
	}

	/**
	 * @param headerAttribute
	 * @param value
	 */
	public void setHeader(final String headerAttribute, final String value)
	{
		final KElement header = getCreateElement(XJDFConstants.Header);
		header.setAttribute(headerAttribute, value);
	}

	/**
	 * @param headerAttribute
	 * @param value
	 */
	public String getHeader(final String headerAttribute)
	{
		final KElement header = getHeader();
		return header == null ? null : header.getNonEmpty(headerAttribute);
	}

	/**
	 * @param url
	 * @return null if we ain't no query, else the subscription which can be further updated
	 */
	public JDFSubscription subscribe(final String url)
	{
		if (!isQuery())
		{
			return null;
		}
		final JDFSubscription sub = (JDFSubscription) getCreateElement(ElementName.SUBSCRIPTION);
		sub.setURL(url);
		return sub;
	}

	/**
	 * @return
	 */
	public boolean isQuery()
	{
		return getLocalName().startsWith(ElementName.QUERY);
	}

	/**
	 * @return
	 */
	public static boolean isMessage(final KElement element)
	{
		final String localName = element == null ? null : element.getLocalName();
		if (localName == null)
		{
			return false;
		}
		return new MessageHelper(element).getEFamily() != null;
	}

	/**
	 * @return
	 */
	public boolean isCommand()
	{
		return getLocalName().startsWith(ElementName.COMMAND);
	}

	/**
	 * @return
	 */
	public boolean isResponse()
	{
		return getLocalName().startsWith(ElementName.RESPONSE);
	}

	/**
	 * @return
	 */
	public boolean isAudit()
	{
		return getLocalName().startsWith(ElementName.AUDIT);
	}

	/**
	 * @return
	 */
	public boolean isSignal()
	{
		return getLocalName().startsWith(ElementName.SIGNAL);
	}

	/**
	 * @param hQuery the query or command to set the refID to
	 */
	public void setQuery(final MessageHelper hQuery)
	{
		if (!isResponse() && !isSignal() || hQuery == null || (hQuery.isResponse() || hQuery.isSignal()))
		{
			return;
		}
		final String id = ensureHeader(hQuery.theElement).appendAnchor(null);
		ensureHeader(theElement).setAttribute(AttributeName.REFID, id);
	}

	/**
	 * @deprecated
	 * @return
	 */
	@Deprecated
	public EnumFamily getFamily()
	{
		if (isCommand())
		{
			return EnumFamily.Command;
		}
		else if (isQuery())
		{
			return EnumFamily.Query;
		}
		else if (isSignal())
		{
			return EnumFamily.Signal;
		}
		else if (isResponse())
		{
			return EnumFamily.Response;
		}
		return null;

	}

	public EFamily getEFamily()
	{
		final String name = getLocalName();
		return EFamily.getEnum(name);

	}

	/**
	 * @return
	 */
	public String getType()
	{
		final EFamily f = getEFamily();
		final String name = getLocalName();
		return (f == null || f.name().equals(name)) ? null : StringUtil.rightStr(name, -f.name().length());
	}

	public EType getEType()
	{
		final String name = getLocalName();
		return EType.getEnum(name);
	}

}
