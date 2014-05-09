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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.process.JDFIDPFinishing;
import org.cip4.jdflib.resource.process.JDFIDPLayout;
import org.cip4.jdflib.resource.process.JDFMediaSource;

/**
*****************************************************************************
class JDFAutoIDPCover : public JDFElement

*****************************************************************************
*/

public abstract class JDFAutoIDPCover extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.BACKSIDE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
		atrInfoTable[1] = new AtrInfoTable(AttributeName.COVERTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumCoverType.getEnum(0), "Front");
		atrInfoTable[2] = new AtrInfoTable(AttributeName.FRONTSIDE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.IDPFINISHING, 0x33333333);
		elemInfoTable[1] = new ElemInfoTable(ElementName.IDPLAYOUT, 0x33333333);
		elemInfoTable[2] = new ElemInfoTable(ElementName.MEDIAINTENT, 0x33333333);
		elemInfoTable[3] = new ElemInfoTable(ElementName.MEDIASOURCE, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoIDPCover
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoIDPCover(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIDPCover
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoIDPCover(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoIDPCover
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoIDPCover(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return  the string representation
	 */
	@Override
	public String toString()
	{
		return " JDFAutoIDPCover[  --> " + super.toString() + " ]";
	}

	/**
	* Enumeration strings for CoverType
	*/

	public static class EnumCoverType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumCoverType(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumCoverType getEnum(String enumName)
		{
			return (EnumCoverType) getEnum(EnumCoverType.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumCoverType getEnum(int enumValue)
		{
			return (EnumCoverType) getEnum(EnumCoverType.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCoverType.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumCoverType.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCoverType.class);
		}

		public static final EnumCoverType Front = new EnumCoverType("Front");
		public static final EnumCoverType Back = new EnumCoverType("Back");
	}

	/* ************************************************************************
	 * Attribute getter / setter
	 * ************************************************************************
	 */

	/* ---------------------------------------------------------------------
	Methods for Attribute BackSide
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute BackSide
	  * @param value the value to set the attribute to
	  */
	public void setBackSide(boolean value)
	{
		setAttribute(AttributeName.BACKSIDE, value, null);
	}

	/**
	  * (18) get boolean attribute BackSide
	  * @return boolean the value of the attribute
	  */
	public boolean getBackSide()
	{
		return getBoolAttribute(AttributeName.BACKSIDE, null, false);
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute CoverType
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute CoverType
	  * @param enumVar the enumVar to set the attribute to
	  */
	public void setCoverType(EnumCoverType enumVar)
	{
		setAttribute(AttributeName.COVERTYPE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute CoverType
	  * @return the value of the attribute
	  */
	public EnumCoverType getCoverType()
	{
		return EnumCoverType.getEnum(getAttribute(AttributeName.COVERTYPE, null, "Front"));
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute FrontSide
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute FrontSide
	  * @param value the value to set the attribute to
	  */
	public void setFrontSide(boolean value)
	{
		setAttribute(AttributeName.FRONTSIDE, value, null);
	}

	/**
	  * (18) get boolean attribute FrontSide
	  * @return boolean the value of the attribute
	  */
	public boolean getFrontSide()
	{
		return getBoolAttribute(AttributeName.FRONTSIDE, null, false);
	}

	/* ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/** (26) getCreateIDPFinishing
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIDPFinishing the element
	 */
	public JDFIDPFinishing getCreateIDPFinishing(int iSkip)
	{
		return (JDFIDPFinishing) getCreateElement_KElement(ElementName.IDPFINISHING, null, iSkip);
	}

	/**
	 * (27) const get element IDPFinishing
	 * @param iSkip number of elements to skip
	 * @return JDFIDPFinishing the element
	 * default is getIDPFinishing(0)     */
	public JDFIDPFinishing getIDPFinishing(int iSkip)
	{
		return (JDFIDPFinishing) getElement(ElementName.IDPFINISHING, null, iSkip);
	}

	/**
	 * Get all IDPFinishing from the current element
	 * 
	 * @return Collection<JDFIDPFinishing>, null if none are available
	 */
	public Collection<JDFIDPFinishing> getAllIDPFinishing()
	{
		final VElement vc = getChildElementVector(ElementName.IDPFINISHING, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFIDPFinishing> v = new Vector<JDFIDPFinishing>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFIDPFinishing) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element IDPFinishing
	 * @return JDFIDPFinishing the element
	 */
	public JDFIDPFinishing appendIDPFinishing()
	{
		return (JDFIDPFinishing) appendElement(ElementName.IDPFINISHING, null);
	}

	/** (26) getCreateIDPLayout
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFIDPLayout the element
	 */
	public JDFIDPLayout getCreateIDPLayout(int iSkip)
	{
		return (JDFIDPLayout) getCreateElement_KElement(ElementName.IDPLAYOUT, null, iSkip);
	}

	/**
	 * (27) const get element IDPLayout
	 * @param iSkip number of elements to skip
	 * @return JDFIDPLayout the element
	 * default is getIDPLayout(0)     */
	public JDFIDPLayout getIDPLayout(int iSkip)
	{
		return (JDFIDPLayout) getElement(ElementName.IDPLAYOUT, null, iSkip);
	}

	/**
	 * Get all IDPLayout from the current element
	 * 
	 * @return Collection<JDFIDPLayout>, null if none are available
	 */
	public Collection<JDFIDPLayout> getAllIDPLayout()
	{
		final VElement vc = getChildElementVector(ElementName.IDPLAYOUT, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFIDPLayout> v = new Vector<JDFIDPLayout>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFIDPLayout) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element IDPLayout
	 * @return JDFIDPLayout the element
	 */
	public JDFIDPLayout appendIDPLayout()
	{
		return (JDFIDPLayout) appendElement(ElementName.IDPLAYOUT, null);
	}

	/** (26) getCreateMediaIntent
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMediaIntent the element
	 */
	public JDFMediaIntent getCreateMediaIntent(int iSkip)
	{
		return (JDFMediaIntent) getCreateElement_KElement(ElementName.MEDIAINTENT, null, iSkip);
	}

	/**
	 * (27) const get element MediaIntent
	 * @param iSkip number of elements to skip
	 * @return JDFMediaIntent the element
	 * default is getMediaIntent(0)     */
	public JDFMediaIntent getMediaIntent(int iSkip)
	{
		return (JDFMediaIntent) getElement(ElementName.MEDIAINTENT, null, iSkip);
	}

	/**
	 * Get all MediaIntent from the current element
	 * 
	 * @return Collection<JDFMediaIntent>, null if none are available
	 */
	public Collection<JDFMediaIntent> getAllMediaIntent()
	{
		final VElement vc = getChildElementVector(ElementName.MEDIAINTENT, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFMediaIntent> v = new Vector<JDFMediaIntent>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFMediaIntent) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element MediaIntent
	 * @return JDFMediaIntent the element
	 */
	public JDFMediaIntent appendMediaIntent()
	{
		return (JDFMediaIntent) appendElement(ElementName.MEDIAINTENT, null);
	}

	/**
	  * (31) create inter-resource link to refTarget
	  * @param refTarget the element that is referenced
	  */
	public void refMediaIntent(JDFMediaIntent refTarget)
	{
		refElement(refTarget);
	}

	/** (26) getCreateMediaSource
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFMediaSource the element
	 */
	public JDFMediaSource getCreateMediaSource(int iSkip)
	{
		return (JDFMediaSource) getCreateElement_KElement(ElementName.MEDIASOURCE, null, iSkip);
	}

	/**
	 * (27) const get element MediaSource
	 * @param iSkip number of elements to skip
	 * @return JDFMediaSource the element
	 * default is getMediaSource(0)     */
	public JDFMediaSource getMediaSource(int iSkip)
	{
		return (JDFMediaSource) getElement(ElementName.MEDIASOURCE, null, iSkip);
	}

	/**
	 * Get all MediaSource from the current element
	 * 
	 * @return Collection<JDFMediaSource>, null if none are available
	 */
	public Collection<JDFMediaSource> getAllMediaSource()
	{
		final VElement vc = getChildElementVector(ElementName.MEDIASOURCE, null);
		if (vc == null || vc.size() == 0)
		{
			return null;
		}

		final Vector<JDFMediaSource> v = new Vector<JDFMediaSource>();
		for (int i = 0; i < vc.size(); i++)
		{
			v.add((JDFMediaSource) vc.get(i));
		}

		return v;
	}

	/**
	 * (30) append element MediaSource
	 * @return JDFMediaSource the element
	 */
	public JDFMediaSource appendMediaSource()
	{
		return (JDFMediaSource) appendElement(ElementName.MEDIASOURCE, null);
	}

	/**
	  * (31) create inter-resource link to refTarget
	  * @param refTarget the element that is referenced
	  */
	public void refMediaSource(JDFMediaSource refTarget)
	{
		refElement(refTarget);
	}

}// end namespace JDF
