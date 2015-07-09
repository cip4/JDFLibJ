/*--------------------------------------------------------------------------------------------------
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
package org.cip4.jdflib.node;

import java.util.Collections;
import java.util.Vector;

import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.ISignalAudit;
import org.cip4.jdflib.pool.JDFAuditPool;

/**
 * @author prosirai
 */
public class AuditToJMF
{
	private final JDFNode theNode;
	private final VJDFAttributeMap vPartMap;
	private final boolean bInlineUpdates;

	/**
	 * @param _theNode the jdf node to parse
	 * @param vParts the job part to search for, if null don't filter
	 * @param inlineUpdates replace all updated audits with the updated version
	 */
	public AuditToJMF(final JDFNode _theNode, final VJDFAttributeMap vParts, final boolean inlineUpdates)
	{
		super();
		this.theNode = _theNode;
		vPartMap = vParts;
		bInlineUpdates = inlineUpdates;
	}

	/**
	 * @param auditType the audit type to use
	 * @return vector of messages to send
	 */
	public VElement getLocalJMFs(final EnumAuditType auditType)
	{
		final JDFAuditPool ap = theNode == null ? null : theNode.getAuditPool();
		final VElement audits = ap == null ? null : ap.getAudits(auditType, null, vPartMap);
		if (bInlineUpdates)
		{
			inlineUpdates(audits);
		}
		if (audits == null || audits.size() == 0)
		{
			return null;
		}

		// we need a type safe list for sort
		final Vector<JDFAudit> va = new Vector<JDFAudit>();
		for (int i = 0; i < audits.size(); i++)
		{
			va.add((JDFAudit) audits.get(i));
		}
		Collections.sort(va, (JDFAudit) audits.elementAt(0));

		final VElement vJMF = new VElement();
		for (int i = 0; i < audits.size(); i++)
		{
			final JDFAudit audit = (JDFAudit) audits.get(i);
			if (audit instanceof ISignalAudit)
			{
				vJMF.add(((ISignalAudit) audit).toSignalJMF());
			}
		}
		return vJMF.size() == 0 ? null : vJMF;
	}

	/**
	 * remove all updated audits from the todo vector
	 * @param audits
	 */
	private void inlineUpdates(final VElement audits)
	{
		if (audits == null)
		{
			return;
		}
		for (int i = audits.size() - 1; i >= 0; i--)
		{
			JDFAudit audit = (JDFAudit) audits.elementAt(i);
			while (audit != null)
			{
				audit = audit.getUpdatedPreviousAudit();
				if (audit != null)
				{
					audits.remove(audit);
					i--; // decrement i, since we removed an element below
				}
			}
		}
	}
}
