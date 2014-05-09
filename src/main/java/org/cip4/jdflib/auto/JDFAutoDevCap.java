/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
 */

package org.cip4.jdflib.auto;

import java.util.Collection;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;

/**
*****************************************************************************
class JDFAutoDevCap : public JDFElement

*****************************************************************************
*/

public abstract class JDFAutoDevCap extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[9];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AVAILABILITY, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, JDFDeviceCap.EnumAvailability.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DEVCAPREFS, 0x33333111, AttributeInfo.EnumAttributeType.IDREFS, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DEVNS, 0x33333331, AttributeInfo.EnumAttributeType.URI, null, "http://www.CIP4.org/JDFSchema_1_1");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.ID, 0x33333111, AttributeInfo.EnumAttributeType.ID, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.MAXOCCURS, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, "1");
		atrInfoTable[5] = new AtrInfoTable(AttributeName.MINOCCURS, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, "1");
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MODULEREFS, 0x33333111, AttributeInfo.EnumAttributeType.IDREFS, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.NAME, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.RESOURCEUSAGE, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x33333311);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoDevCap
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoDevCap(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDevCap
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoDevCap(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoDevCap
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoDevCap(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoDevCap[  --> " + super.toString() + " ]";
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute Availability
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute Availability
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setAvailability(JDFDeviceCap.EnumAvailability enumVar)
	{
		setAttribute(AttributeName.AVAILABILITY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute Availability
	  * @return the value of the attribute
	  */
	public JDFDeviceCap.EnumAvailability getAvailability()
	{
		return JDFDeviceCap.EnumAvailability.getEnum(getAttribute(AttributeName.AVAILABILITY, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute DevCapRefs
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute DevCapRefs
	  * @param value the value to set the attribute to
	  */
	public void setDevCapRefs(VString value)
	{
		setAttribute(AttributeName.DEVCAPREFS, value, null);
	}

	/**
	  * (21) get VString attribute DevCapRefs
	  * @return VString the value of the attribute
	  */
	public VString getDevCapRefs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.DEVCAPREFS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute DevNS
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute DevNS
	  * @param value the value to set the attribute to
	  */
	public void setDevNS(String value)
	{
		setAttribute(AttributeName.DEVNS, value, null);
	}

	/**
	  * (23) get String attribute DevNS
	  * @return the value of the attribute
	  */
	public String getDevNS()
	{
		return getAttribute(AttributeName.DEVNS, null, "http://www.CIP4.org/JDFSchema_1_1");
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ID
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ID
	  * @param value the value to set the attribute to
	  */
	@Override
	public void setID(String value)
	{
		setAttribute(AttributeName.ID, value, null);
	}

	/**
	  * (23) get String attribute ID
	  * @return the value of the attribute
	  */
	@Override
	public String getID()
	{
		return getAttribute(AttributeName.ID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute MaxOccurs
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute MaxOccurs
	  * @param value the value to set the attribute to
	  */
	public void setMaxOccurs(int value)
	{
		setAttribute(AttributeName.MAXOCCURS, value, null);
	}

	/**
	  * (15) get int attribute MaxOccurs
	  * @return int the value of the attribute
	  */
	public int getMaxOccurs()
	{
		return getIntAttribute(AttributeName.MAXOCCURS, null, 1);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute MinOccurs
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute MinOccurs
	  * @param value the value to set the attribute to
	  */
	public void setMinOccurs(int value)
	{
		setAttribute(AttributeName.MINOCCURS, value, null);
	}

	/**
	  * (15) get int attribute MinOccurs
	  * @return int the value of the attribute
	  */
	public int getMinOccurs()
	{
		return getIntAttribute(AttributeName.MINOCCURS, null, 1);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ModuleRefs
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ModuleRefs
	  * @param value the value to set the attribute to
	  */
	public void setModuleRefs(VString value)
	{
		setAttribute(AttributeName.MODULEREFS, value, null);
	}

	/**
	  * (21) get VString attribute ModuleRefs
	  * @return VString the value of the attribute
	  */
	public VString getModuleRefs()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.MODULEREFS, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Name
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Name
	  * @param value the value to set the attribute to
	  */
	public void setName(String value)
	{
		setAttribute(AttributeName.NAME, value, null);
	}

	/**
	  * (23) get String attribute Name
	  * @return the value of the attribute
	  */
	public String getName()
	{
		return getAttribute(AttributeName.NAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ResourceUsage
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ResourceUsage
	  * @param value the value to set the attribute to
	  */
	public void setResourceUsage(String value)
	{
		setAttribute(AttributeName.RESOURCEUSAGE, value, null);
	}

	/**
	  * (23) get String attribute ResourceUsage
	  * @return the value of the attribute
	  */
	public String getResourceUsage()
	{
		return getAttribute(AttributeName.RESOURCEUSAGE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/** (26) getCreateLoc
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc(int iSkip)
	{
		return (JDFLoc) getCreateElement_KElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * (27) const get element Loc
	 * @param iSkip number of elements to skip
	 * @return JDFLoc the element
	 * default is getLoc(0)     */
	public JDFLoc getLoc(int iSkip)
	{
		return (JDFLoc) getElement(ElementName.LOC, null, iSkip);
	}

	/**
	 * Get all Loc from the current element
	 * 
	 * @return Collection<JDFLoc>, null if none are available
	 */
	public Collection<JDFLoc> getAllLoc()
	{
		final VElement vc = getChildElementVector(ElementName.LOC, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFLoc> v = new Vector<JDFLoc>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFLoc) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element Loc
	 * @return JDFLoc the element
	 */
	public JDFLoc appendLoc()
	{
		return (JDFLoc) appendElement(ElementName.LOC, null);
	}

}// end namespace JDF
