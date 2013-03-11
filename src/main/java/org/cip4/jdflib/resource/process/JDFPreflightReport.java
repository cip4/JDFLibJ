/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of 
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
 * 
 *//**
*
* Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
*
* JDFPreflightReport.java
*
* Last changes
*
*/
package org.cip4.jdflib.resource.process;

import java.util.Set;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAction.EnumSeverity;
import org.cip4.jdflib.auto.JDFAutoPreflightReport;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.devicecapability.JDFAction;
import org.w3c.dom.DOMException;

public class JDFPreflightReport extends JDFAutoPreflightReport
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFPreflightReport
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFPreflightReport(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFPreflightReport
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFPreflightReport(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFPreflightReport
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFPreflightReport(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFPreflightReport[  --> " + super.toString() + " ]";
	}

	public JDFPRItem setPR(JDFAction action, int pageSet, JDFAttributeMap prMap, VString groupBy)
	{

		JDFAttributeMap groupMap = null;
		JDFAttributeMap instanceMap = null;
		if (prMap != null)
		{
			groupMap = new JDFAttributeMap(prMap);
			final Set set = groupBy.getSet();
			groupMap.reduceMap(set);
			instanceMap = new JDFAttributeMap(prMap);
			instanceMap.removeKeys(set);
		}
		JDFPRItem pi = getCreatePRItem(action, groupMap);

		JDFPRGroup pg = pi.getCreatePRGroup(groupMap);
		JDFPROccurrence pgInstance = pg.getCreatePROccurrence(instanceMap);
		pgInstance.addOccurrences(1, action.getSeverity());
		pi.insertPageSet(pageSet);
		return pi;
	}

	/**
	 * increments the errorCount attribute by i
	 * 
	 * @param i
	 *            the amount to increment by
	 */
	public int addErrorCount(int i)
	{
		return addAttribute(AttributeName.ERRORCOUNT, i, null);
	}

	/**
	 * increments the WarningCount attribute by i
	 * 
	 * @param i
	 *            the amount to increment by
	 */
	public int addWarningCount(int i)
	{
		return addAttribute(AttributeName.WARNINGCOUNT, i, null);
	}

	/**
	 * recursive call that sets errorcount and warning count according to the
	 * value of severity
	 * 
	 * @param i
	 *            the number of occurrences to add
	 * @param sev
	 *            the severity of the occurrences
	 */
	public void addOccurrences(int i, EnumSeverity sev)
	{
		if (EnumSeverity.Warning.equals(sev))
			addWarningCount(i);
		else if (EnumSeverity.Error.equals(sev))
			addErrorCount(i);

	}

	/**
	 * @param action
	 * @param groupMap
	 * @return
	 */
	public JDFPRItem getCreatePRItem(JDFAction action, JDFAttributeMap groupMap)
	{
		JDFPRItem pi = getPRItem(action, null);
		if (pi == null)
		{
			pi = appendPRItem();
			pi.setActionRef(action.getID());
		}
		pi.getCreatePRGroup(groupMap);
		return pi;

	}

	/**
	 * @param action
	 * @return
	 */
	private JDFPRItem getPRItem(JDFAction action, JDFAttributeMap groupMap)
	{
		String id = action == null ? null : action.getID();
		JDFAttributeMap map = (id == null) ? null : new JDFAttributeMap("ActionRef", id);
		JDFPRItem pi = (JDFPRItem) getChildByTagName(ElementName.PRITEM, null, 0, map, true, true);
		if (groupMap != null && pi != null)
		{
			JDFPRGroup pg = pi.getPRGroup(groupMap);
			if (pg == null)
				return null;
		}
		return pi;
	}

	/**
	 * generic initialization routine - normally called automagically
	 * initializes required attributes to 0
	 */
	@Override
	public boolean init()
	{
		boolean b = super.init();
		setWarningCount(0);
		setErrorCount(0);
		return b;
	}

}
