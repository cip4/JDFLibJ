/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.extensions.xjdfwalker.XJMFTypeMap;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.util.StringUtil;

public class WalkModifyQueueEntry extends WalkTypesafeMessage
{

	/**
	 *
	 */
	public WalkModifyQueueEntry()
	{
		super();
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.WalkTypesafeMessage#matches(org.cip4.jdflib.core.KElement)
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return true;
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.WalkTypesafeMessage#getMessageType(org.cip4.jdflib.core.KElement, java.lang.String, java.lang.String)
	 */
	@Override
	String getMessageType(final KElement e, final String messageName, final String family)
	{
		String operation = e.getXPathAttribute("ModifyQueueEntryParams/@Operation", null);
		if (operation == null)
		{
			final String refID = e.getNonEmpty(AttributeName.REFID);
			operation = XJMFTypeMap.getMap().remove(refID);
			if (operation == null)
			{
				operation = super.getMessageType(e, messageName, family);
			}
		}
		else
		{
			final String id = e.getID();
			if ("Complete".equals(operation))
			{
				operation = "AbortQueueEntry";
			}
			else if ("Move".equals(operation))
			{
				final String prio = e.getXPathAttribute("ModifyQueueEntryParams/@Priority", null);
				operation = StringUtil.isEmpty(prio) ? EnumType.SetQueueEntryPosition.getName() : EnumType.SetQueueEntryPriority.getName();
			}
			else
			{
				operation += "QueueEntry";
			}
			XJMFTypeMap.getMap().put(id, operation);

		}

		return operation;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
	 */
	@Override
	public VString getElementNames()
	{
		return VString.getVString("CommandModifyQueueEntry ResponseModifyQueueEntry", null);
	}
}
