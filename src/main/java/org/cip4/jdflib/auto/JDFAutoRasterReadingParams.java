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
import org.cip4.jdflib.resource.JDFFitPolicy;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFMedia;

/**
 *****************************************************************************
 * class JDFAutoRasterReadingParams : public JDFResource
 *****************************************************************************
 * 
 */

public abstract class JDFAutoRasterReadingParams extends JDFResource
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CENTER, 0x33333111, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.MIRRORAROUND, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumMirrorAround.getEnum(0), "None");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.POLARITY, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumPolarity.getEnum(0), "Positive");
		atrInfoTable[3] = new AtrInfoTable(AttributeName.POSTER, 0x44433111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.POSTEROVERLAP, 0x44433111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.SCALING, 0x33333111, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.SCALINGORIGIN, 0x33333111, AttributeInfo.EnumAttributeType.XYPair, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.FITPOLICY, 0x66666111);
		elemInfoTable[1] = new ElemInfoTable(ElementName.MEDIA, 0x33333111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoRasterReadingParams
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoRasterReadingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRasterReadingParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoRasterReadingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoRasterReadingParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoRasterReadingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoRasterReadingParams[  --> " + super.toString() + " ]";
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
	 * Enumeration strings for MirrorAround
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumMirrorAround extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumMirrorAround(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumMirrorAround getEnum(String enumName)
		{
			return (EnumMirrorAround) getEnum(EnumMirrorAround.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumMirrorAround getEnum(int enumValue)
		{
			return (EnumMirrorAround) getEnum(EnumMirrorAround.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumMirrorAround.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumMirrorAround.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumMirrorAround.class);
		}

		/**  */
		public static final EnumMirrorAround None = new EnumMirrorAround("None");
		/**  */
		public static final EnumMirrorAround FeedDirection = new EnumMirrorAround("FeedDirection");
		/**  */
		public static final EnumMirrorAround MediaWidth = new EnumMirrorAround("MediaWidth");
		/**  */
		public static final EnumMirrorAround Both = new EnumMirrorAround("Both");
	}

	/**
	 * Enumeration strings for Polarity
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumPolarity extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumPolarity(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumPolarity getEnum(String enumName)
		{
			return (EnumPolarity) getEnum(EnumPolarity.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumPolarity getEnum(int enumValue)
		{
			return (EnumPolarity) getEnum(EnumPolarity.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumPolarity.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumPolarity.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumPolarity.class);
		}

		/**  */
		public static final EnumPolarity Positive = new EnumPolarity("Positive");
		/**  */
		public static final EnumPolarity Negative = new EnumPolarity("Negative");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Center ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Center
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setCenter(boolean value)
	{
		setAttribute(AttributeName.CENTER, value, null);
	}

	/**
	 * (18) get boolean attribute Center
	 * 
	 * @return boolean the value of the attribute
	 */
	public boolean getCenter()
	{
		return getBoolAttribute(AttributeName.CENTER, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MirrorAround ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute MirrorAround
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setMirrorAround(EnumMirrorAround enumVar)
	{
		setAttribute(AttributeName.MIRRORAROUND, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute MirrorAround
	 * 
	 * @return the value of the attribute
	 */
	public EnumMirrorAround getMirrorAround()
	{
		return EnumMirrorAround.getEnum(getAttribute(AttributeName.MIRRORAROUND, null, "None"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Polarity ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Polarity
	 * 
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setPolarity(EnumPolarity enumVar)
	{
		setAttribute(AttributeName.POLARITY, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Polarity
	 * 
	 * @return the value of the attribute
	 */
	public EnumPolarity getPolarity()
	{
		return EnumPolarity.getEnum(getAttribute(AttributeName.POLARITY, null, "Positive"));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Poster ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Poster
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPoster(JDFXYPair value)
	{
		setAttribute(AttributeName.POSTER, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Poster
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getPoster()
	{
		final String strAttrName = getAttribute(AttributeName.POSTER, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute PosterOverlap ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute PosterOverlap
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setPosterOverlap(JDFXYPair value)
	{
		setAttribute(AttributeName.POSTEROVERLAP, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute PosterOverlap
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getPosterOverlap()
	{
		final String strAttrName = getAttribute(AttributeName.POSTEROVERLAP, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Scaling ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Scaling
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setScaling(JDFXYPair value)
	{
		setAttribute(AttributeName.SCALING, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute Scaling
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getScaling()
	{
		final String strAttrName = getAttribute(AttributeName.SCALING, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ScalingOrigin ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute ScalingOrigin
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setScalingOrigin(JDFXYPair value)
	{
		setAttribute(AttributeName.SCALINGORIGIN, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute ScalingOrigin
	 * 
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getScalingOrigin()
	{
		final String strAttrName = getAttribute(AttributeName.SCALINGORIGIN, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element FitPolicy
	 * 
	 * @return JDFFitPolicy the element
	 */
	public JDFFitPolicy getFitPolicy()
	{
		return (JDFFitPolicy) getElement(ElementName.FITPOLICY, null, 0);
	}

	/**
	 * (25) getCreateFitPolicy
	 * 
	 * @return JDFFitPolicy the element
	 */
	public JDFFitPolicy getCreateFitPolicy()
	{
		return (JDFFitPolicy) getCreateElement(ElementName.FITPOLICY, null, 0);
	}

	/**
	 * (29) append element FitPolicy
	 * 
	 * @return JDFFitPolicy the element
	 * @throws JDFException if the element already exists
	 */
	public JDFFitPolicy appendFitPolicy() throws JDFException
	{
		return (JDFFitPolicy) appendElementN(ElementName.FITPOLICY, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refFitPolicy(JDFFitPolicy refTarget)
	{
		refElement(refTarget);
	}

	/**
	 * (26) getCreateMedia
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMedia the element
	 */
	public JDFMedia getCreateMedia(int iSkip)
	{
		return (JDFMedia) getCreateElement(ElementName.MEDIA, null, iSkip);
	}

	/**
	 * (27) const get element Media
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMedia the element default is getMedia(0)
	 */
	public JDFMedia getMedia(int iSkip)
	{
		return (JDFMedia) getElement(ElementName.MEDIA, null, iSkip);
	}

	/**
	 * Get all Media from the current element
	 * 
	 * @return Collection<JDFMedia>, null if none are available
	 */
	public Collection<JDFMedia> getAllMedia()
	{
		return getChildrenByClass(JDFMedia.class, false, 0);
	}

	/**
	 * (30) append element Media
	 * 
	 * @return JDFMedia the element
	 */
	public JDFMedia appendMedia()
	{
		return (JDFMedia) appendElement(ElementName.MEDIA, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refMedia(JDFMedia refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
