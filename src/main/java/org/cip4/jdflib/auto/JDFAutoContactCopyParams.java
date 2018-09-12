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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.prepress.JDFScreeningParams;

/**
 *****************************************************************************
 * class JDFAutoContactCopyParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoContactCopyParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CONTACTSCREEN, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.POLARITYCHANGE, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "true");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.REPEATSTEP, 0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, "1 1");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.CYCLE, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.DIFFUSION, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumDiffusion.getEnum(0), null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.VACUUM, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.SCREENINGPARAMS, 0x66666661);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoContactCopyParams
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoContactCopyParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoContactCopyParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoContactCopyParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoContactCopyParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoContactCopyParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoContactCopyParams[  --> " + super.toString() + " ]";
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

	/**
	 * Enumeration strings for Diffusion
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDiffusion extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumDiffusion(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDiffusion getEnum(String enumName)
		{
			return (EnumDiffusion) getEnum(EnumDiffusion.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDiffusion getEnum(int enumValue)
		{
			return (EnumDiffusion) getEnum(EnumDiffusion.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDiffusion.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDiffusion.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDiffusion.class);
		}

		/**  */
		public static final EnumDiffusion On = new EnumDiffusion("On");
		/**  */
		public static final EnumDiffusion Off = new EnumDiffusion("Off");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ContactScreen ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ContactScreen
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setContactScreen(boolean value)
	{
		setAttribute(AttributeName.CONTACTSCREEN, value, null);
	}

	/**
	 * (18) get boolean attribute ContactScreen
	 * 
	 * @return boolean the value of the attribute
	 */
	public boolean getContactScreen()
	{
		return getBoolAttribute(AttributeName.CONTACTSCREEN, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PolarityChange ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PolarityChange
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPolarityChange(boolean value)
	{
		setAttribute(AttributeName.POLARITYCHANGE, value, null);
	}

	/**
	 * (18) get boolean attribute PolarityChange
	 * 
	 * @return boolean the value of the attribute
	 */
	public boolean getPolarityChange()
	{
		return getBoolAttribute(AttributeName.POLARITYCHANGE, null, true);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RepeatStep ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RepeatStep
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setRepeatStep(JDFXYPair value)
	{
		setAttribute(AttributeName.REPEATSTEP, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute RepeatStep
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getRepeatStep()
	{
		final String strAttrName = getAttribute(AttributeName.REPEATSTEP, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Cycle ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Cycle
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setCycle(int value)
	{
		setAttribute(AttributeName.CYCLE, value, null);
	}

	/**
	 * (15) get int attribute Cycle
	 * 
	 * @return int the value of the attribute
	 */
	public int getCycle()
	{
		return getIntAttribute(AttributeName.CYCLE, null, 0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Diffusion ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Diffusion
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDiffusion(EnumDiffusion enumVar)
	{
		setAttribute(AttributeName.DIFFUSION, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Diffusion
	 * 
	 * @return the value of the attribute
	 */
	public EnumDiffusion getDiffusion()
	{
		return EnumDiffusion.getEnum(getAttribute(AttributeName.DIFFUSION, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Vacuum ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Vacuum
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setVacuum(double value)
	{
		setAttribute(AttributeName.VACUUM, value, null);
	}

	/**
	 * (17) get double attribute Vacuum
	 * 
	 * @return double the value of the attribute
	 */
	public double getVacuum()
	{
		return getRealAttribute(AttributeName.VACUUM, null, 0.0);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element ScreeningParams
	 * 
	 * @return JDFScreeningParams the element
	 */
	public JDFScreeningParams getScreeningParams()
	{
		return (JDFScreeningParams) getElement(ElementName.SCREENINGPARAMS, null, 0);
	}

	/**
	 * (25) getCreateScreeningParams
	 * 
	 * @return JDFScreeningParams the element
	 */
	public JDFScreeningParams getCreateScreeningParams()
	{
		return (JDFScreeningParams) getCreateElement_JDFElement(ElementName.SCREENINGPARAMS, null, 0);
	}

	/**
	 * (29) append element ScreeningParams
	 * 
	 * @return JDFScreeningParams the element
	 * @throws JDFException if the element already exists
	 */
	public JDFScreeningParams appendScreeningParams() throws JDFException
	{
		return (JDFScreeningParams) appendElementN(ElementName.SCREENINGPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refScreeningParams(JDFScreeningParams refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
