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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFColorantZoneDetails;

/**
*****************************************************************************
class JDFAutoTrappingParams : public JDFResource

*****************************************************************************
*/

public abstract class JDFAutoTrappingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[21];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.MINIMUMBLACKWIDTH, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, "0");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.TRAPENDSTYLE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, "Miter");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.TRAPJOINSTYLE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, "Miter");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.BLACKCOLORLIMIT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.BLACKDENSITYLIMIT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.BLACKWIDTH, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.ENABLED, 0x44444433, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.HALFTONENAME, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.IMAGEINTERNALTRAPPING, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.IMAGERESOLUTION, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.IMAGEMASKTRAPPING, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.IMAGETOIMAGETRAPPING, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.IMAGETOOBJECTTRAPPING, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.IMAGETRAPPLACEMENT, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumImageTrapPlacement.getEnum(0), null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.IMAGETRAPWIDTH, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.IMAGETRAPWIDTHY, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.SLIDINGTRAPLIMIT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.STEPLIMIT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.TRAPCOLORSCALING, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.TRAPWIDTH, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable[20] = new AtrInfoTable(AttributeName.TRAPWIDTHY, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.COLORANTZONEDETAILS, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoTrappingParams
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoTrappingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoTrappingParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoTrappingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoTrappingParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoTrappingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoTrappingParams[  --> " + super.toString() + " ]";
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

	/**
	* Enumeration strings for ImageTrapPlacement
	*/

	@SuppressWarnings("rawtypes")
	public static class EnumImageTrapPlacement extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumImageTrapPlacement(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumImageTrapPlacement getEnum(String enumName)
		{
			return (EnumImageTrapPlacement) getEnum(EnumImageTrapPlacement.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumImageTrapPlacement getEnum(int enumValue)
		{
			return (EnumImageTrapPlacement) getEnum(EnumImageTrapPlacement.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumImageTrapPlacement.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumImageTrapPlacement.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumImageTrapPlacement.class);
		}

		/**  */
		public static final EnumImageTrapPlacement Center = new EnumImageTrapPlacement("Center");
		/**  */
		public static final EnumImageTrapPlacement Choke = new EnumImageTrapPlacement("Choke");
		/**  */
		public static final EnumImageTrapPlacement Normal = new EnumImageTrapPlacement("Normal");
		/**  */
		public static final EnumImageTrapPlacement Spread = new EnumImageTrapPlacement("Spread");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute MinimumBlackWidth
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute MinimumBlackWidth
	  * @param value the value to set the attribute to
	  */
	public void setMinimumBlackWidth(double value)
	{
		setAttribute(AttributeName.MINIMUMBLACKWIDTH, value, null);
	}

	/**
	  * (17) get double attribute MinimumBlackWidth
	  * @return double the value of the attribute
	  */
	public double getMinimumBlackWidth()
	{
		return getRealAttribute(AttributeName.MINIMUMBLACKWIDTH, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute TrapEndStyle
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute TrapEndStyle
	  * @param value the value to set the attribute to
	  */
	public void setTrapEndStyle(String value)
	{
		setAttribute(AttributeName.TRAPENDSTYLE, value, null);
	}

	/**
	  * (23) get String attribute TrapEndStyle
	  * @return the value of the attribute
	  */
	public String getTrapEndStyle()
	{
		return getAttribute(AttributeName.TRAPENDSTYLE, null, "Miter");
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute TrapJoinStyle
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute TrapJoinStyle
	  * @param value the value to set the attribute to
	  */
	public void setTrapJoinStyle(String value)
	{
		setAttribute(AttributeName.TRAPJOINSTYLE, value, null);
	}

	/**
	  * (23) get String attribute TrapJoinStyle
	  * @return the value of the attribute
	  */
	public String getTrapJoinStyle()
	{
		return getAttribute(AttributeName.TRAPJOINSTYLE, null, "Miter");
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute BlackColorLimit
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute BlackColorLimit
	  * @param value the value to set the attribute to
	  */
	public void setBlackColorLimit(double value)
	{
		setAttribute(AttributeName.BLACKCOLORLIMIT, value, null);
	}

	/**
	  * (17) get double attribute BlackColorLimit
	  * @return double the value of the attribute
	  */
	public double getBlackColorLimit()
	{
		return getRealAttribute(AttributeName.BLACKCOLORLIMIT, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute BlackDensityLimit
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute BlackDensityLimit
	  * @param value the value to set the attribute to
	  */
	public void setBlackDensityLimit(double value)
	{
		setAttribute(AttributeName.BLACKDENSITYLIMIT, value, null);
	}

	/**
	  * (17) get double attribute BlackDensityLimit
	  * @return double the value of the attribute
	  */
	public double getBlackDensityLimit()
	{
		return getRealAttribute(AttributeName.BLACKDENSITYLIMIT, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute BlackWidth
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute BlackWidth
	  * @param value the value to set the attribute to
	  */
	public void setBlackWidth(double value)
	{
		setAttribute(AttributeName.BLACKWIDTH, value, null);
	}

	/**
	  * (17) get double attribute BlackWidth
	  * @return double the value of the attribute
	  */
	public double getBlackWidth()
	{
		return getRealAttribute(AttributeName.BLACKWIDTH, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Enabled
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute Enabled
	  * @param value the value to set the attribute to
	  */
	public void setEnabled(boolean value)
	{
		setAttribute(AttributeName.ENABLED, value, null);
	}

	/**
	  * (18) get boolean attribute Enabled
	  * @return boolean the value of the attribute
	  */
	public boolean getEnabled()
	{
		return getBoolAttribute(AttributeName.ENABLED, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute HalftoneName
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute HalftoneName
	  * @param value the value to set the attribute to
	  */
	public void setHalftoneName(String value)
	{
		setAttribute(AttributeName.HALFTONENAME, value, null);
	}

	/**
	  * (23) get String attribute HalftoneName
	  * @return the value of the attribute
	  */
	public String getHalftoneName()
	{
		return getAttribute(AttributeName.HALFTONENAME, null, JDFCoreConstants.EMPTYSTRING);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ImageInternalTrapping
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ImageInternalTrapping
	  * @param value the value to set the attribute to
	  */
	public void setImageInternalTrapping(boolean value)
	{
		setAttribute(AttributeName.IMAGEINTERNALTRAPPING, value, null);
	}

	/**
	  * (18) get boolean attribute ImageInternalTrapping
	  * @return boolean the value of the attribute
	  */
	public boolean getImageInternalTrapping()
	{
		return getBoolAttribute(AttributeName.IMAGEINTERNALTRAPPING, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ImageResolution
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ImageResolution
	  * @param value the value to set the attribute to
	  */
	public void setImageResolution(int value)
	{
		setAttribute(AttributeName.IMAGERESOLUTION, value, null);
	}

	/**
	  * (15) get int attribute ImageResolution
	  * @return int the value of the attribute
	  */
	public int getImageResolution()
	{
		return getIntAttribute(AttributeName.IMAGERESOLUTION, null, 0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ImageMaskTrapping
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ImageMaskTrapping
	  * @param value the value to set the attribute to
	  */
	public void setImageMaskTrapping(boolean value)
	{
		setAttribute(AttributeName.IMAGEMASKTRAPPING, value, null);
	}

	/**
	  * (18) get boolean attribute ImageMaskTrapping
	  * @return boolean the value of the attribute
	  */
	public boolean getImageMaskTrapping()
	{
		return getBoolAttribute(AttributeName.IMAGEMASKTRAPPING, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ImageToImageTrapping
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ImageToImageTrapping
	  * @param value the value to set the attribute to
	  */
	public void setImageToImageTrapping(boolean value)
	{
		setAttribute(AttributeName.IMAGETOIMAGETRAPPING, value, null);
	}

	/**
	  * (18) get boolean attribute ImageToImageTrapping
	  * @return boolean the value of the attribute
	  */
	public boolean getImageToImageTrapping()
	{
		return getBoolAttribute(AttributeName.IMAGETOIMAGETRAPPING, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ImageToObjectTrapping
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ImageToObjectTrapping
	  * @param value the value to set the attribute to
	  */
	public void setImageToObjectTrapping(boolean value)
	{
		setAttribute(AttributeName.IMAGETOOBJECTTRAPPING, value, null);
	}

	/**
	  * (18) get boolean attribute ImageToObjectTrapping
	  * @return boolean the value of the attribute
	  */
	public boolean getImageToObjectTrapping()
	{
		return getBoolAttribute(AttributeName.IMAGETOOBJECTTRAPPING, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ImageTrapPlacement
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute ImageTrapPlacement
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setImageTrapPlacement(EnumImageTrapPlacement enumVar)
	{
		setAttribute(AttributeName.IMAGETRAPPLACEMENT, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute ImageTrapPlacement
	  * @return the value of the attribute
	  */
	public EnumImageTrapPlacement getImageTrapPlacement()
	{
		return EnumImageTrapPlacement.getEnum(getAttribute(AttributeName.IMAGETRAPPLACEMENT, null, null));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ImageTrapWidth
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ImageTrapWidth
	  * @param value the value to set the attribute to
	  */
	public void setImageTrapWidth(double value)
	{
		setAttribute(AttributeName.IMAGETRAPWIDTH, value, null);
	}

	/**
	  * (17) get double attribute ImageTrapWidth
	  * @return double the value of the attribute
	  */
	public double getImageTrapWidth()
	{
		return getRealAttribute(AttributeName.IMAGETRAPWIDTH, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute ImageTrapWidthY
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute ImageTrapWidthY
	  * @param value the value to set the attribute to
	  */
	public void setImageTrapWidthY(double value)
	{
		setAttribute(AttributeName.IMAGETRAPWIDTHY, value, null);
	}

	/**
	  * (17) get double attribute ImageTrapWidthY
	  * @return double the value of the attribute
	  */
	public double getImageTrapWidthY()
	{
		return getRealAttribute(AttributeName.IMAGETRAPWIDTHY, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute SlidingTrapLimit
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute SlidingTrapLimit
	  * @param value the value to set the attribute to
	  */
	public void setSlidingTrapLimit(double value)
	{
		setAttribute(AttributeName.SLIDINGTRAPLIMIT, value, null);
	}

	/**
	  * (17) get double attribute SlidingTrapLimit
	  * @return double the value of the attribute
	  */
	public double getSlidingTrapLimit()
	{
		return getRealAttribute(AttributeName.SLIDINGTRAPLIMIT, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute StepLimit
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute StepLimit
	  * @param value the value to set the attribute to
	  */
	public void setStepLimit(double value)
	{
		setAttribute(AttributeName.STEPLIMIT, value, null);
	}

	/**
	  * (17) get double attribute StepLimit
	  * @return double the value of the attribute
	  */
	public double getStepLimit()
	{
		return getRealAttribute(AttributeName.STEPLIMIT, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute TrapColorScaling
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute TrapColorScaling
	  * @param value the value to set the attribute to
	  */
	public void setTrapColorScaling(double value)
	{
		setAttribute(AttributeName.TRAPCOLORSCALING, value, null);
	}

	/**
	  * (17) get double attribute TrapColorScaling
	  * @return double the value of the attribute
	  */
	public double getTrapColorScaling()
	{
		return getRealAttribute(AttributeName.TRAPCOLORSCALING, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute TrapWidth
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute TrapWidth
	  * @param value the value to set the attribute to
	  */
	public void setTrapWidth(double value)
	{
		setAttribute(AttributeName.TRAPWIDTH, value, null);
	}

	/**
	  * (17) get double attribute TrapWidth
	  * @return double the value of the attribute
	  */
	public double getTrapWidth()
	{
		return getRealAttribute(AttributeName.TRAPWIDTH, null, 0.0);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute TrapWidthY
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute TrapWidthY
	  * @param value the value to set the attribute to
	  */
	public void setTrapWidthY(double value)
	{
		setAttribute(AttributeName.TRAPWIDTHY, value, null);
	}

	/**
	  * (17) get double attribute TrapWidthY
	  * @return double the value of the attribute
	  */
	public double getTrapWidthY()
	{
		return getRealAttribute(AttributeName.TRAPWIDTHY, null, 0.0);
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/** (26) getCreateColorantZoneDetails
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFColorantZoneDetails the element
	 */
	public JDFColorantZoneDetails getCreateColorantZoneDetails(int iSkip)
	{
		return (JDFColorantZoneDetails) getCreateElement_KElement(ElementName.COLORANTZONEDETAILS, null, iSkip);
	}

	/**
	 * (27) const get element ColorantZoneDetails
	 * @param iSkip number of elements to skip
	 * @return JDFColorantZoneDetails the element
	 * default is getColorantZoneDetails(0)     */
	public JDFColorantZoneDetails getColorantZoneDetails(int iSkip)
	{
		return (JDFColorantZoneDetails) getElement(ElementName.COLORANTZONEDETAILS, null, iSkip);
	}

	/**
	 * Get all ColorantZoneDetails from the current element
	 * 
	 * @return Collection<JDFColorantZoneDetails>, null if none are available
	 */
	public Collection<JDFColorantZoneDetails> getAllColorantZoneDetails()
	{
		final VElement vc = getChildElementVector(ElementName.COLORANTZONEDETAILS, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFColorantZoneDetails> v = new Vector<JDFColorantZoneDetails>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFColorantZoneDetails) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element ColorantZoneDetails
	 * @return JDFColorantZoneDetails the element
	 */
	public JDFColorantZoneDetails appendColorantZoneDetails()
	{
		return (JDFColorantZoneDetails) appendElement(ElementName.COLORANTZONEDETAILS, null);
	}

}// end namespace JDF
