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

package org.cip4.jdflib.auto;

import java.util.Collection;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFProductionSubPath;

/**
 *****************************************************************************
 * class JDFAutoProductionPath : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoProductionPath extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.PRODUCTIONPATHID, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FOLDERSUPERSTRUCTUREWEBPATH, 0x66666111);
		elemInfoTable[1] = new ElemInfoTable(ElementName.POSTPRESSCOMPONENTPATH, 0x33333111);
		elemInfoTable[2] = new ElemInfoTable(ElementName.PRINTINGUNITWEBPATH, 0x33333111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoProductionPath
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoProductionPath(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoProductionPath
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoProductionPath(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoProductionPath
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoProductionPath(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoProductionPath[  --> " + super.toString() + " ]";
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Parameter);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Parameter;
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ProductionPathID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ProductionPathID
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setProductionPathID(String value)
	{
		setAttribute(AttributeName.PRODUCTIONPATHID, value, null);
	}

	/**
	 * (23) get String attribute ProductionPathID
	 * 
	 * @return the value of the attribute
	 */
	public String getProductionPathID()
	{
		return getAttribute(AttributeName.PRODUCTIONPATHID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element FolderSuperstructureWebPath
	 * 
	 * @return JDFProductionSubPath the element
	 */
	public JDFProductionSubPath getFolderSuperstructureWebPath()
	{
		return (JDFProductionSubPath) getElement(ElementName.FOLDERSUPERSTRUCTUREWEBPATH, null, 0);
	}

	/**
	 * (25) getCreateFolderSuperstructureWebPath
	 * 
	 * @return JDFProductionSubPath the element
	 */
	public JDFProductionSubPath getCreateFolderSuperstructureWebPath()
	{
		return (JDFProductionSubPath) getCreateElement_KElement(ElementName.FOLDERSUPERSTRUCTUREWEBPATH, null, 0);
	}

	/**
	 * (29) append element FolderSuperstructureWebPath
	 * 
	 * @return JDFProductionSubPath the element
	 * @throws JDFException if the element already exists
	 */
	public JDFProductionSubPath appendFolderSuperstructureWebPath() throws JDFException
	{
		return (JDFProductionSubPath) appendElementN(ElementName.FOLDERSUPERSTRUCTUREWEBPATH, 1, null);
	}

	/**
	 * (26) getCreatePostPressComponentPath
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFProductionSubPath the element
	 */
	public JDFProductionSubPath getCreatePostPressComponentPath(int iSkip)
	{
		return (JDFProductionSubPath) getCreateElement_KElement(ElementName.POSTPRESSCOMPONENTPATH, null, iSkip);
	}

	/**
	 * (27) const get element PostPressComponentPath
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFProductionSubPath the element default is getPostPressComponentPath(0)
	 */
	public JDFProductionSubPath getPostPressComponentPath(int iSkip)
	{
		return (JDFProductionSubPath) getElement(ElementName.POSTPRESSCOMPONENTPATH, null, iSkip);
	}

	/**
	 * Get all PostPressComponentPath from the current element
	 * 
	 * @return Collection<JDFProductionSubPath>, null if none are available
	 */
	public Collection<JDFProductionSubPath> getAllPostPressComponentPath()
	{
		return getChildrenByClass(JDFProductionSubPath.class, false, 0);
	}

	/**
	 * (30) append element PostPressComponentPath
	 * 
	 * @return JDFProductionSubPath the element
	 */
	public JDFProductionSubPath appendPostPressComponentPath()
	{
		return (JDFProductionSubPath) appendElement(ElementName.POSTPRESSCOMPONENTPATH, null);
	}

	/**
	 * (26) getCreatePrintingUnitWebPath
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFProductionSubPath the element
	 */
	public JDFProductionSubPath getCreatePrintingUnitWebPath(int iSkip)
	{
		return (JDFProductionSubPath) getCreateElement_KElement(ElementName.PRINTINGUNITWEBPATH, null, iSkip);
	}

	/**
	 * (27) const get element PrintingUnitWebPath
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFProductionSubPath the element default is getPrintingUnitWebPath(0)
	 */
	public JDFProductionSubPath getPrintingUnitWebPath(int iSkip)
	{
		return (JDFProductionSubPath) getElement(ElementName.PRINTINGUNITWEBPATH, null, iSkip);
	}

	/**
	 * Get all PrintingUnitWebPath from the current element
	 * 
	 * @return Collection<JDFProductionSubPath>, null if none are available
	 */
	public Collection<JDFProductionSubPath> getAllPrintingUnitWebPath()
	{
		return getChildrenByClass(JDFProductionSubPath.class, false, 0);
	}

	/**
	 * (30) append element PrintingUnitWebPath
	 * 
	 * @return JDFProductionSubPath the element
	 */
	public JDFProductionSubPath appendPrintingUnitWebPath()
	{
		return (JDFProductionSubPath) appendElement(ElementName.PRINTINGUNITWEBPATH, null);
	}

}// end namespace JDF
