/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.HashSet;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen <br/>
 *         walker for JMF mesaages
 */
public class WalkMessage extends WalkJDFElement
{
	private final static HashSet<EnumType> badtypes = fillBadTypes();

	/**
	 *
	 */
	public WalkMessage()
	{
		super();
	}

	private static HashSet<EnumType> fillBadTypes()
	{
		final HashSet<EnumType> s = new HashSet<>();
		s.add(EnumType.CloseQueue);
		s.add(EnumType.FlushResources);
		s.add(EnumType.HoldQueue);
		s.add(EnumType.Events);
		s.add(EnumType.KnownControllers);
		s.add(EnumType.KnownJDFServices);
		s.add(EnumType.ModifyNode);
		s.add(EnumType.NewJDF);
		s.add(EnumType.NodeInfo);
		s.add(EnumType.Occupation);
		s.add(EnumType.OpenQueue);
		s.add(EnumType.QueueEntryStatus);
		s.add(EnumType.RepeatMessages);
		s.add(EnumType.RequestForAuthentication);
		s.add(EnumType.ResumeQueue);
		s.add(EnumType.SubmissionMethods);
		s.add(EnumType.Track);
		s.add(EnumType.UpdateJDF);
		return s;

	}

	/**
	 * @param jdf
	 * @return the created message
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		final JDFMessage m = (JDFMessage) jdf;

		if (jdfToXJDF.isTypeSafeMessage())
		{
			final JDFMessage m2 = makeTypesafe(m);
			if (m2 == null)
			{
				return null;
			}
		}
		return super.walk(jdf, xjdf);
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkElement#updateAttributes(org.cip4.jdflib.datatypes.JDFAttributeMap)
	 */
	@Override
	protected void updateAttributes(final JDFAttributeMap map)
	{
		super.updateAttributes(map);
		map.remove(AttributeName.VERSION);
		map.remove(AttributeName.MAXVERSION);
		if (jdfToXJDF.isTypeSafeMessage())
		{
			map.renameKey(AttributeName.SENDERID, AttributeName.DEVICEID);
			map.remove(AttributeName.TYPE);
			map.remove(AttributeName.ACKNOWLEDGED);
			map.remove(AttributeName.ACKNOWLEDGETYPE);
			map.remove(AttributeName.ACKNOWLEDGEURL);
			map.remove(AttributeName.FORMAT);
			map.remove(AttributeName.LASTREPEAT);
			map.remove(AttributeName.TEMPLATE);
		}

	}

	/**
	 *
	 * @param m
	 */
	JDFMessage makeTypesafe(final JDFMessage m)
	{
		final EnumFamily family = getNewFamily(m);
		final String type = family == null ? null : getMessageType(m);
		if (type == null)
		{
			return null;
		}
		else
		{
			m.renameElement(getFamilyName(family) + type, null);
		}
		return m;
	}

	/**
	 *
	 * @param family
	 * @return
	 */
	String getFamilyName(final EnumFamily family)
	{
		return family.getName();
	}

	/**
	 *
	 * @param m
	 * @return
	 */
	String getMessageType(final JDFMessage m)
	{
		final EnumType type = m.getEnumType();
		final boolean zappType = isZappType(type);
		if (zappType)
		{
			log.warn("Removing unsupported message Type: " + m.getType());
		}
		return zappType ? null : type.getName();
	}

	/**
	 *
	 * @param type
	 * @return
	 */
	boolean isZappType(final EnumType type)
	{
		return type == null || badtypes.contains(type);
	}

	/**
	 *
	 *
	 * @param m
	 * @return
	 */
	EnumFamily getNewFamily(final JDFMessage m)
	{
		final String type = m.getType();
		if (StringUtil.isEmpty(type))
		{
			return null;
		}
		EnumFamily family = m.getFamily();
		if (jdfToXJDF.isAbstractMessage())
		{
			if (EnumFamily.Command.equals(family) || EnumFamily.Registration.equals(family))
				family = EnumFamily.Query;
			if (EnumFamily.Acknowledge.equals(family))
				family = EnumFamily.Response;
		}
		return family;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return (toCheck instanceof JDFMessage);
	}
}