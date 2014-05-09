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
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.JDFCoverApplicationParams;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFSpinePreparationParams;
import org.cip4.jdflib.resource.JDFSpineTapingParams;
import org.cip4.jdflib.resource.process.postpress.JDFGlueApplication;

/**
*****************************************************************************
class JDFAutoAdhesiveBindingParams : public JDFResource

*****************************************************************************
*/

public abstract class JDFAutoAdhesiveBindingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.PULLOUTVALUE, 0x44444443, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.FLEXVALUE, 0x44444443, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.SPINEPREPARATIONPARAMS, 0x44444442);
		elemInfoTable[1] = new ElemInfoTable(ElementName.GLUEAPPLICATION, 0x44444442);
		elemInfoTable[2] = new ElemInfoTable(ElementName.SPINETAPINGPARAMS, 0x44444442);
		elemInfoTable[3] = new ElemInfoTable(ElementName.COVERAPPLICATIONPARAMS, 0x44444442);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoAdhesiveBindingParams
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoAdhesiveBindingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAdhesiveBindingParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoAdhesiveBindingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoAdhesiveBindingParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoAdhesiveBindingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoAdhesiveBindingParams[  --> " + super.toString() + " ]";
	}

	/**
	 * @return  true if ok
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

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute PullOutValue
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute PullOutValue
	  * @param value the value to set the attribute to
	  */
	public void setPullOutValue(double value)
	{
		setAttribute(AttributeName.PULLOUTVALUE, value, null);
	}

	/**
	  * (17) get double attribute PullOutValue
	  * @return double the value of the attribute
	  */
	public double getPullOutValue()
	{
		return getRealAttribute(AttributeName.PULLOUTVALUE, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute FlexValue
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute FlexValue
	  * @param value the value to set the attribute to
	  */
	public void setFlexValue(double value)
	{
		setAttribute(AttributeName.FLEXVALUE, value, null);
	}

	/**
	  * (17) get double attribute FlexValue
	  * @return double the value of the attribute
	  */
	public double getFlexValue()
	{
		return getRealAttribute(AttributeName.FLEXVALUE, null, 0.0);
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/** (26) getCreateSpinePreparationParams
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFSpinePreparationParams the element
	 */
	public JDFSpinePreparationParams getCreateSpinePreparationParams(int iSkip)
	{
		return (JDFSpinePreparationParams) getCreateElement_KElement(ElementName.SPINEPREPARATIONPARAMS, null, iSkip);
	}

	/**
	 * (27) const get element SpinePreparationParams
	 * @param iSkip number of elements to skip
	 * @return JDFSpinePreparationParams the element
	 * default is getSpinePreparationParams(0)     */
	public JDFSpinePreparationParams getSpinePreparationParams(int iSkip)
	{
		return (JDFSpinePreparationParams) getElement(ElementName.SPINEPREPARATIONPARAMS, null, iSkip);
	}

	/**
	 * Get all SpinePreparationParams from the current element
	 * 
	 * @return Collection<JDFSpinePreparationParams>, null if none are available
	 */
	public Collection<JDFSpinePreparationParams> getAllSpinePreparationParams()
	{
		final VElement vc = getChildElementVector(ElementName.SPINEPREPARATIONPARAMS, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFSpinePreparationParams> v = new Vector<JDFSpinePreparationParams>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFSpinePreparationParams) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element SpinePreparationParams
	 * @return JDFSpinePreparationParams the element
	 */
	public JDFSpinePreparationParams appendSpinePreparationParams()
	{
		return (JDFSpinePreparationParams) appendElement(ElementName.SPINEPREPARATIONPARAMS, null);
	}

	/** (26) getCreateGlueApplication
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFGlueApplication the element
	 */
	public JDFGlueApplication getCreateGlueApplication(int iSkip)
	{
		return (JDFGlueApplication) getCreateElement_KElement(ElementName.GLUEAPPLICATION, null, iSkip);
	}

	/**
	 * (27) const get element GlueApplication
	 * @param iSkip number of elements to skip
	 * @return JDFGlueApplication the element
	 * default is getGlueApplication(0)     */
	public JDFGlueApplication getGlueApplication(int iSkip)
	{
		return (JDFGlueApplication) getElement(ElementName.GLUEAPPLICATION, null, iSkip);
	}

	/**
	 * Get all GlueApplication from the current element
	 * 
	 * @return Collection<JDFGlueApplication>, null if none are available
	 */
	public Collection<JDFGlueApplication> getAllGlueApplication()
	{
		final VElement vc = getChildElementVector(ElementName.GLUEAPPLICATION, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFGlueApplication> v = new Vector<JDFGlueApplication>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFGlueApplication) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element GlueApplication
	 * @return JDFGlueApplication the element
	 */
	public JDFGlueApplication appendGlueApplication()
	{
		return (JDFGlueApplication) appendElement(ElementName.GLUEAPPLICATION, null);
	}

	/** (26) getCreateSpineTapingParams
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFSpineTapingParams the element
	 */
	public JDFSpineTapingParams getCreateSpineTapingParams(int iSkip)
	{
		return (JDFSpineTapingParams) getCreateElement_KElement(ElementName.SPINETAPINGPARAMS, null, iSkip);
	}

	/**
	 * (27) const get element SpineTapingParams
	 * @param iSkip number of elements to skip
	 * @return JDFSpineTapingParams the element
	 * default is getSpineTapingParams(0)     */
	public JDFSpineTapingParams getSpineTapingParams(int iSkip)
	{
		return (JDFSpineTapingParams) getElement(ElementName.SPINETAPINGPARAMS, null, iSkip);
	}

	/**
	 * Get all SpineTapingParams from the current element
	 * 
	 * @return Collection<JDFSpineTapingParams>, null if none are available
	 */
	public Collection<JDFSpineTapingParams> getAllSpineTapingParams()
	{
		final VElement vc = getChildElementVector(ElementName.SPINETAPINGPARAMS, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFSpineTapingParams> v = new Vector<JDFSpineTapingParams>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFSpineTapingParams) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element SpineTapingParams
	 * @return JDFSpineTapingParams the element
	 */
	public JDFSpineTapingParams appendSpineTapingParams()
	{
		return (JDFSpineTapingParams) appendElement(ElementName.SPINETAPINGPARAMS, null);
	}

	/** (26) getCreateCoverApplicationParams
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFCoverApplicationParams the element
	 */
	public JDFCoverApplicationParams getCreateCoverApplicationParams(int iSkip)
	{
		return (JDFCoverApplicationParams) getCreateElement_KElement(ElementName.COVERAPPLICATIONPARAMS, null, iSkip);
	}

	/**
	 * (27) const get element CoverApplicationParams
	 * @param iSkip number of elements to skip
	 * @return JDFCoverApplicationParams the element
	 * default is getCoverApplicationParams(0)     */
	public JDFCoverApplicationParams getCoverApplicationParams(int iSkip)
	{
		return (JDFCoverApplicationParams) getElement(ElementName.COVERAPPLICATIONPARAMS, null, iSkip);
	}

	/**
	 * Get all CoverApplicationParams from the current element
	 * 
	 * @return Collection<JDFCoverApplicationParams>, null if none are available
	 */
	public Collection<JDFCoverApplicationParams> getAllCoverApplicationParams()
	{
		final VElement vc = getChildElementVector(ElementName.COVERAPPLICATIONPARAMS, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFCoverApplicationParams> v = new Vector<JDFCoverApplicationParams>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFCoverApplicationParams) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element CoverApplicationParams
	 * @return JDFCoverApplicationParams the element
	 */
	public JDFCoverApplicationParams appendCoverApplicationParams()
	{
		return (JDFCoverApplicationParams) appendElement(ElementName.COVERAPPLICATIONPARAMS, null);
	}

}// end namespace JDF
