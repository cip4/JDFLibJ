/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 *****************************************************************************
 * class JDFAutoRingBindingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoRingBindingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[13];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BINDERCOLOR, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.BINDERCOLORDETAILS, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.BINDERMATERIAL, 0x3333333333l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.BINDERNAME, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.RINGDIAMETER, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.RINGMECHANIC, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.RINGSHAPE, 0x3333333333l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.RINGSYSTEM, 0x4444444443l, AttributeInfo.EnumAttributeType.enumeration, EnumRingSystem.getEnum(0), null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.RIVETSEXPOSED, 0x3333333333l, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.SPINECOLOR, 0x3333333333l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.SPINECOLORDETAILS, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.SPINEWIDTH, 0x3333333333l, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.VIEWBINDER, 0x3333333333l, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.HOLEMAKINGPARAMS, 0x6666666611l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoRingBindingParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoRingBindingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRingBindingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoRingBindingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRingBindingParams
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoRingBindingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
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
	 * Enumeration strings for RingSystem
	 */

	public enum ERingSystem
	{
		RingSystem_2HoleEuro, RingSystem_3HoleUS, RingSystem_4HoleEuro;

		public static ERingSystem getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(ERingSystem.class, val, null);
		}
	}

	/**
	 * Enumeration strings for RingSystem
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumRingSystem extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumRingSystem(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumRingSystem getEnum(String enumName)
		{
			return (EnumRingSystem) getEnum(EnumRingSystem.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumRingSystem getEnum(int enumValue)
		{
			return (EnumRingSystem) getEnum(EnumRingSystem.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumRingSystem.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumRingSystem.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumRingSystem.class);
		}

		/**  */
		public static final EnumRingSystem RingSystem_2HoleEuro = new EnumRingSystem("2HoleEuro");
		/**  */
		public static final EnumRingSystem RingSystem_3HoleUS = new EnumRingSystem("3HoleUS");
		/**  */
		public static final EnumRingSystem RingSystem_4HoleEuro = new EnumRingSystem("4HoleEuro");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BinderColor ---------------------------------------------------------------------
	 */
	/**
	 * (13) set attribute BinderColor
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBinderColor(EnumNamedColor value)
	{
		setAttribute(AttributeName.BINDERCOLOR, value == null ? null : value.getName(), null);
	}

	/**
	 * (19) get EnumNamedColor attribute BinderColor
	 *
	 * @return EnumNamedColor the value of the attribute
	 */
	public EnumNamedColor getBinderColor()
	{
		String strAttrName = "";
		EnumNamedColor nPlaceHolder = null;
		strAttrName = getAttribute(AttributeName.BINDERCOLOR, null, JDFCoreConstants.EMPTYSTRING);
		nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BinderColorDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BinderColorDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBinderColorDetails(String value)
	{
		setAttribute(AttributeName.BINDERCOLORDETAILS, value, null);
	}

	/**
	 * (23) get String attribute BinderColorDetails
	 *
	 * @return the value of the attribute
	 */
	public String getBinderColorDetails()
	{
		return getAttribute(AttributeName.BINDERCOLORDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BinderMaterial
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BinderMaterial
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBinderMaterial(String value)
	{
		setAttribute(AttributeName.BINDERMATERIAL, value, null);
	}

	/**
	 * (23) get String attribute BinderMaterial
	 *
	 * @return the value of the attribute
	 */
	public String getBinderMaterial()
	{
		return getAttribute(AttributeName.BINDERMATERIAL, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute BinderName ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute BinderName
	 *
	 * @param value the value to set the attribute to
	 */
	public void setBinderName(String value)
	{
		setAttribute(AttributeName.BINDERNAME, value, null);
	}

	/**
	 * (23) get String attribute BinderName
	 *
	 * @return the value of the attribute
	 */
	public String getBinderName()
	{
		return getAttribute(AttributeName.BINDERNAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RingDiameter
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RingDiameter
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRingDiameter(double value)
	{
		setAttribute(AttributeName.RINGDIAMETER, value, null);
	}

	/**
	 * (17) get double attribute RingDiameter
	 *
	 * @return double the value of the attribute
	 */
	public double getRingDiameter()
	{
		return getRealAttribute(AttributeName.RINGDIAMETER, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RingMechanic
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RingMechanic
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRingMechanic(boolean value)
	{
		setAttribute(AttributeName.RINGMECHANIC, value, null);
	}

	/**
	 * (18) get boolean attribute RingMechanic
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getRingMechanic()
	{
		return getBoolAttribute(AttributeName.RINGMECHANIC, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RingShape ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RingShape
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRingShape(String value)
	{
		setAttribute(AttributeName.RINGSHAPE, value, null);
	}

	/**
	 * (23) get String attribute RingShape
	 *
	 * @return the value of the attribute
	 */
	public String getRingShape()
	{
		return getAttribute(AttributeName.RINGSHAPE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RingSystem ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute RingSystem
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setRingSystem(ERingSystem enumVar)
	{
		setAttribute(AttributeName.RINGSYSTEM, enumVar == null ? null : enumVar.name(), null);
	}

	/**
	 * (9) get attribute RingSystem
	 *
	 * @return the value of the attribute
	 */
	public ERingSystem getERingSystem()
	{
		return ERingSystem.getEnum(getAttribute(AttributeName.RINGSYSTEM, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RingSystem ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute RingSystem
	 *
	 * @param enumVar the enumVar to set the attribute to
	 * @deprecated use java.lang.enum
	 */
	@Deprecated
	public void setRingSystem(EnumRingSystem enumVar)
	{
		setAttribute(AttributeName.RINGSYSTEM, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute RingSystem
	 *
	 * @return the value of the attribute
	 */
	public EnumRingSystem getRingSystem()
	{
		return EnumRingSystem.getEnum(getAttribute(AttributeName.RINGSYSTEM, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute RivetsExposed
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute RivetsExposed
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRivetsExposed(boolean value)
	{
		setAttribute(AttributeName.RIVETSEXPOSED, value, null);
	}

	/**
	 * (18) get boolean attribute RivetsExposed
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getRivetsExposed()
	{
		return getBoolAttribute(AttributeName.RIVETSEXPOSED, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SpineColor ---------------------------------------------------------------------
	 */
	/**
	 * (13) set attribute SpineColor
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpineColor(EnumNamedColor value)
	{
		setAttribute(AttributeName.SPINECOLOR, value == null ? null : value.getName(), null);
	}

	/**
	 * (19) get EnumNamedColor attribute SpineColor
	 *
	 * @return EnumNamedColor the value of the attribute
	 */
	public EnumNamedColor getSpineColor()
	{
		String strAttrName = "";
		EnumNamedColor nPlaceHolder = null;
		strAttrName = getAttribute(AttributeName.SPINECOLOR, null, JDFCoreConstants.EMPTYSTRING);
		nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SpineColorDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SpineColorDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpineColorDetails(String value)
	{
		setAttribute(AttributeName.SPINECOLORDETAILS, value, null);
	}

	/**
	 * (23) get String attribute SpineColorDetails
	 *
	 * @return the value of the attribute
	 */
	public String getSpineColorDetails()
	{
		return getAttribute(AttributeName.SPINECOLORDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SpineWidth ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SpineWidth
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpineWidth(double value)
	{
		setAttribute(AttributeName.SPINEWIDTH, value, null);
	}

	/**
	 * (17) get double attribute SpineWidth
	 *
	 * @return double the value of the attribute
	 */
	public double getSpineWidth()
	{
		return getRealAttribute(AttributeName.SPINEWIDTH, null, 0.0);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ViewBinder ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ViewBinder
	 *
	 * @param value the value to set the attribute to
	 */
	public void setViewBinder(String value)
	{
		setAttribute(AttributeName.VIEWBINDER, value, null);
	}

	/**
	 * (23) get String attribute ViewBinder
	 *
	 * @return the value of the attribute
	 */
	public String getViewBinder()
	{
		return getAttribute(AttributeName.VIEWBINDER, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element HoleMakingParams
	 *
	 * @return JDFHoleMakingParams the element
	 */
	public JDFHoleMakingParams getHoleMakingParams()
	{
		return (JDFHoleMakingParams) getElement(ElementName.HOLEMAKINGPARAMS, null, 0);
	}

	/**
	 * (25) getCreateHoleMakingParams
	 * 
	 * @return JDFHoleMakingParams the element
	 */
	public JDFHoleMakingParams getCreateHoleMakingParams()
	{
		return (JDFHoleMakingParams) getCreateElement_JDFElement(ElementName.HOLEMAKINGPARAMS, null, 0);
	}

	/**
	 * (29) append element HoleMakingParams
	 *
	 * @return JDFHoleMakingParams the element @ if the element already exists
	 */
	public JDFHoleMakingParams appendHoleMakingParams()
	{
		return (JDFHoleMakingParams) appendElementN(ElementName.HOLEMAKINGPARAMS, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refHoleMakingParams(JDFHoleMakingParams refTarget)
	{
		refElement(refTarget);
	}

}
