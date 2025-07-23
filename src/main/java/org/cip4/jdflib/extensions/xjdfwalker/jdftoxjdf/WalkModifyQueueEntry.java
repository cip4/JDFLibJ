/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import java.util.Collection;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.xjdfwalker.XJMFTypeMap;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFModifyQueueEntryParams;
import org.cip4.jdflib.jmf.JDFModifyQueueEntryParams.eOperation;
import org.cip4.jdflib.jmf.JDFQuery;
import org.cip4.jdflib.jmf.JDFQueueEntryDef;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen <br/>
 *         walker for JMF mesaages
 */
public class WalkModifyQueueEntry extends WalkMessage
{
	private static final StringArray QUEUE_CONTROL = new StringArray(
			"AbortQueueEntry,HoldQueueEntry,RemoveQueueEntry,ResumeQueueEntry,SetGangQueueEntry,SetQueueEntryPosition,SetQueueEntryPriority,SuspendQueueEntry", ",");

	/**
	 *
	 */
	public WalkModifyQueueEntry()
	{
		super();
	}

	/**
	 *
	 * @return
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkMessage#makeTypesafe(org.cip4.jdflib.jmf.JDFMessage)
	 */
	@Override
	JDFMessage makeTypesafe(JDFMessage m)
	{
		m = super.makeTypesafe(m);
		if ((m instanceof JDFQuery) || (m instanceof JDFCommand))
		{
			final String id = m.getID();
			final String originalType = super.getMessageType(m);
			final eOperation operation = eOperation.getEnum(originalType);
			if (operation != null)
			{
				updateMQP(m, id, originalType, operation);
			}
		}

		return m;
	}

	void updateMQP(final JDFMessage m, final String id, final String originalType, final eOperation operation)
	{
		XJMFTypeMap.getMap().put(id, originalType);
		final JDFModifyQueueEntryParams modifyParams = (JDFModifyQueueEntryParams) m.getCreateElement(XJDFConstants.ModifyQueueEntryParams, null, 0);
		modifyParams.setOperation(operation);
		final String oldParams;
		if (EnumType.SetQueueEntryPriority.getName().equals(originalType))
		{
			oldParams = ElementName.QUEUEENTRYPRIPARAMS;
			final KElement op = m.getElement(oldParams);
			modifyParams.setAttributes(op);
		}
		else if (EnumType.SetQueueEntryPosition.getName().equals(originalType))
		{
			oldParams = ElementName.QUEUEENTRYPOSPARAMS;
			final KElement op = m.getElement(oldParams);
			modifyParams.setAttributes(op);
			modifyParams.removeAttribute(AttributeName.QUEUEENTRYID);
		}
		else
		{
			oldParams = originalType + "Params";
		}
		final JDFQueueEntryDef queueEntryDef = (JDFQueueEntryDef) m.getElement(ElementName.QUEUEENTRYDEF, null, 0);
		final String qeid = queueEntryDef == null ? null : queueEntryDef.getQueueEntryID();
		if (qeid != null)
		{
			modifyParams.setXPathAttribute("QueueFilter/@QueueEntryIDs", qeid);
			m.removeChild(ElementName.QUEUEENTRYDEF, null, 0);
		}
		updateOP(m, operation, modifyParams, oldParams);
	}

	void updateOP(final JDFMessage m, final eOperation operation, final JDFModifyQueueEntryParams modifyParams, final String oldParams)
	{
		final KElement op = m.getElement(oldParams);
		if (op != null)
		{
			final String qeid0 = op.getNonEmpty(AttributeName.QUEUEENTRYID);
			if (qeid0 != null)
			{
				modifyParams.setXPathAttribute("QueueFilter/@QueueEntryIDs", qeid0);
			}
			if (eOperation.Abort.equals(operation))
			{
				final String endstate = op.getNonEmpty(AttributeName.ENDSTATUS);
				if (EnumNodeStatus.Completed.getName().equals(endstate))
				{
					modifyParams.setOperation(eOperation.Complete);
				}
			}
			modifyParams.moveElement(op.getElement(ElementName.QUEUEFILTER), null);
			op.deleteNode();
		}
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.XJDF20.WalkMessage#getMessageType(org.cip4.jdflib.jmf.JDFMessage)
	 */
	@Override
	String getMessageType(final JDFMessage m)
	{
		return XJDFConstants.ModifyQueueEntry;
	}

	/**
	 * @see org.cip4.jdflib.extensions.XJDF20.WalkMessage#matches(org.cip4.jdflib.core.KElement)
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return !jdfToXJDF.isRetainAll() && super.matches(toCheck) && isQueueControl(toCheck.getAttribute(AttributeName.TYPE));
	}

	/**
	 *
	 * @param type
	 * @return
	 */
	public static boolean isQueueControl(final String type)
	{
		return QUEUE_CONTROL.contains(type);
	}

	/**
	 *
	 * @param type
	 * @return
	 */
	public static Collection<String> getQueueControl()
	{
		return new StringArray(QUEUE_CONTROL);
	}

}