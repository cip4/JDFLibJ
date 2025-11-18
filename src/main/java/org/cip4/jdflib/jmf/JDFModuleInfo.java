/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
/**
 * ========================================================================== class JDFDeviceInfo extends JDFResource ==========================================================================
 *
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED Author: sabjon@topmail.de using a code generator Warning! very preliminary test version. Interface subject to change without
 *            prior notice! Revision history: ...
 **/

package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceCondition;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFEnums.eDeviceStatus;
import org.cip4.jdflib.util.JDFDuration;

// ----------------------------------
/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *         much prior to May 17, 2009
 */
public class JDFModuleInfo extends JDFElement
{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFDeviceInfo
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFModuleInfo(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDeviceInfo
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFModuleInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDeviceInfo
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFModuleInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	public eDeviceStatus getModuleStatus()
	{
		return eDeviceStatus.getEnum(getAttribute(XJDFConstants.ModuleStatus));
	}

	public void setModuleStatus(final eDeviceStatus s)
	{
		setAttribute(XJDFConstants.ModuleStatus, s, null);
	}

	/**
	 * (36) set attribute HourCounter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHourCounter(final JDFDuration value)
	{
		setAttribute(AttributeName.HOURCOUNTER, value, null);
	}

	/**
	 * (20) get JDFDuration attribute HourCounter
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getHourCounter()
	{
		final String strAttrName = getNonEmpty(AttributeName.HOURCOUNTER);
		return JDFDuration.createDuration(strAttrName);
	}

	/**
	 * @param enumVar
	 */
	public void setModuleCondition(final EnumDeviceCondition enumVar)
	{
		setAttribute(AttributeName.MODULECONDITION, enumVar, null);
	}

	/**
	 * @return
	 */
	public EnumDeviceCondition getModuleCondition()
	{
		return EnumDeviceCondition.getEnum(getAttribute(AttributeName.MODULECONDITION));
	}

	/**
	 * @param value
	 */
	public void setProductionCounter(final double value)
	{
		setAttribute(AttributeName.PRODUCTIONCOUNTER, value, null);
	}

	/**
	 * @return
	 */
	public double getProductionCounter()
	{
		return getRealAttribute(AttributeName.PRODUCTIONCOUNTER, null, 0.0);
	}

	/**
	 * @param value
	 */
	public void setStatusDetails(final String value)
	{
		setAttribute(AttributeName.STATUSDETAILS, value, null);
	}

	/**
	 * @return
	 */
	public String getStatusDetails()
	{
		return getAttribute(AttributeName.STATUSDETAILS);
	}

	/**
	 * @param value
	 */
	public void setTotalProductionCounter(final double value)
	{
		setAttribute(AttributeName.TOTALPRODUCTIONCOUNTER, value, null);
	}

	/**
	 * @return
	 */
	public double getTotalProductionCounter()
	{
		return getRealAttribute(AttributeName.TOTALPRODUCTIONCOUNTER, null, 0.0);
	}

	/**
	 * @param value
	 */
	public void setModuleID(final String value)
	{
		setAttribute(AttributeName.MODULEID, value);
	}

	/**
	 * @return
	 */
	public String getModuleID()
	{
		return getAttribute(AttributeName.MODULEID);
	}

}
