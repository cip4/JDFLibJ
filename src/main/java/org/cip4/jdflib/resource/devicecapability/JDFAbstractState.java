/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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

/**
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * @author Elena Skobchenko
 *
 * JDFAbstractState.java
 *
 */

package org.cip4.jdflib.resource.devicecapability;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.auto.JDFAutoDevCaps.EnumContext;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.datatypes.JDFRangeList;
import org.cip4.jdflib.ifaces.ICapabilityElement;
import org.cip4.jdflib.jmf.JDFMessageService;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap.EnumAvailability;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.span.JDFSpanBase;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 * befoer June 7, 2009
 */
public abstract class JDFAbstractState extends JDFElement implements JDFBaseDataTypes, ICapabilityElement
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[16];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.AVAILABILITY, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumAvailability.getEnum(0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.ACTIONREFS, 0x33333311, AttributeInfo.EnumAttributeType.IDREFS, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.DEPENDENTMACROREF, 0x33333311, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.DEVNS, 0x33333331, AttributeInfo.EnumAttributeType.URI, null, JDFConstants.JDFNAMESPACE);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.EDITABLE, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, JDFConstants.TRUE);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.HASDEFAULT, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, JDFConstants.TRUE);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.ID, 0x33333311, AttributeInfo.EnumAttributeType.ID, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.LISTTYPE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumListType.getEnum(0), EnumListType.SingleValue.getName());
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MACROREFS, 0x33333311, AttributeInfo.EnumAttributeType.IDREFS, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.MAXOCCURS, 0x33333311, AttributeInfo.EnumAttributeType.unbounded, null, "1");
		atrInfoTable[10] = new AtrInfoTable(AttributeName.MINOCCURS, 0x33333311, AttributeInfo.EnumAttributeType.integer, null, "1");
		atrInfoTable[11] = new AtrInfoTable(AttributeName.MODULEREFS, 0x33333111, AttributeInfo.EnumAttributeType.IDREFS, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.NAME, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.REQUIRED, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.SPAN, 0x44444431, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.USERDISPLAY, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumUserDisplay.getEnum(0), EnumUserDisplay.Display.getName());
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
	 * constructor for JDFAbstractState
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFAbstractState(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * constructor for JDFAbstractState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFAbstractState(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * constructor for JDFAbstractState
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFAbstractState(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 *
	 * June 7, 2009
	 */
	@SuppressWarnings("unchecked")
	public static class EnumUserDisplay extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumUserDisplay(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumUserDisplay getEnum(final String enumName)
		{
			return (EnumUserDisplay) getEnum(EnumUserDisplay.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumUserDisplay getEnum(final int enumValue)
		{
			return (EnumUserDisplay) getEnum(EnumUserDisplay.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumUserDisplay.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumUserDisplay.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumUserDisplay.class);
		}

		/**
		 *
		 */
		public static final EnumUserDisplay Display = new EnumUserDisplay("Display");
		/**
		 *
		 */
		public static final EnumUserDisplay Hide = new EnumUserDisplay("Hide");
		/**
		 *
		 */
		public static final EnumUserDisplay Dependent = new EnumUserDisplay("Dependent");

	}

	/**
	 *
	 * add a value to the list of values defined by testlists
	 *
	 * @param value value to test
	 * @param testlists switches between Allowed test lists and Present test lists. Has two values: Allowed and Present.
	 */
	public abstract void addValue(String value, EnumFitsValue testlists);

	/**
	 * Tests whether the defined value matches the Allowed test lists or the Present test lists specified for this state
	 *
	 * @param value value to test
	 * @param testlists switches between Allowed test lists and Present test lists. Has two values: Allowed and Present.
	 * @return boolean - true, if the value matches the test lists or if Allowed testlists are not specified
	 */
	public abstract boolean fitsValue(String value, EnumFitsValue testlists);

	/**
	 * Gets the NamePath of this State in form " <code>DevCapsName[Context=aaa, LinkUsage=ccc]/DevCapName1/DevCapName2../@StateName</code> "
	 * <p>
	 * default getNamePath(false)
	 *
	 * @return String - NamePath of this State
	 */
	public final String getNamePath()
	{
		String namePath = getParentPath();

		final JDFDevCaps dcs = getParentDevCaps();
		if (dcs != null)
		{
			final EnumContext context = dcs.getContext();
			if (EnumContext.Link.equals(context))
			{
				namePath += "Link";
			}
		}
		if (getListType().equals(EnumListType.Span))
		{
			return (namePath + "/" + getName()); // Span is an element
		}
		return (namePath + "/@" + getName());
	}

	private String getParentPath()
	{
		String namePath = null;
		final KElement parent = getParentNode_KElement();
		if (parent instanceof JDFDevCap)
		{
			final JDFDevCap devCap = getParentDevCap();
			namePath = devCap.getNamePath(true);
		}
		else if (parent instanceof JDFDeviceCap)
		{
			namePath = "JDF";
		}
		else if (parent instanceof JDFMessageService)
		{
			namePath = "JMF";
		}
		return namePath;
	}

	/**
	 * @see org.cip4.jdflib.ifaces.ICapabilityElement#getNamePathVector()
	 * @return
	*/
	@Override
	public final VString getNamePathVector()
	{
		return getNamePathVector(true);
	}

	/**
	 * Gets the NamePath of this State in form " <code>DevCapsName[Context=aaa, LinkUsage=ccc]/DevCapName1/DevCapName2../@StateName</code> "
	 * <p>
	 * default getNamePath(false)
	 *
	 * @param bRecurse if true returns " <code>DevCapsName/SubelemName1/SubelemName2/../@StateName</code> "
	 *
	 * @return String - NamePath of this State
	 */
	public final VString getNamePathVector(final boolean bRecurse)
	{
		final VString vNamePath = getParentPathVector(bRecurse);
		final JDFDevCaps dcs = getParentDevCaps();
		if (dcs != null)
		{
			final EnumContext context = dcs.getContext();
			if (EnumContext.Link.equals(context))
			{
				StringUtil.concatStrings(vNamePath, "Link");
			}
		}
		if (getListType().equals(EnumListType.Span))
		{
			StringUtil.concatStrings(vNamePath, "/" + getName()); // Span is an
			// element
		}
		else
		{
			StringUtil.concatStrings(vNamePath, "/@" + getName());
		}
		return vNamePath;
	}

	/**
	 * @param recurse
	 * @return
	 */
	private VString getParentPathVector(final boolean recurse)
	{
		final KElement parent = getParentNode_KElement();
		if (parent instanceof JDFDevCap)
		{
			return ((JDFDevCap) parent).getNamePathVector(recurse);
		}
		return new VString(getParentPath(), null);
	}

	/**
	 * get the ancestor devCaps, null if this resides in a DevCapPool
	 *
	 * @return JDFDevCaps
	 */
	public JDFDevCaps getParentDevCaps()
	{
		return (JDFDevCaps) getDeepParent(ElementName.DEVCAPS, 0);
	}

	/*
	 * // Attribute Getter / Setter
	 */
	/**
	 * Sets attribute Availability
	 *
	 * @param value the value to set the attribute to
	 */
	@Override
	public void setAvailability(final EnumAvailability value)
	{
		setAttribute(AttributeName.AVAILABILITY, value.getName(), null);
	}

	/**
	 * Gets typesafe enumerated attribute Availability
	 *
	 * @return EnumAvailability: the enumeration value of the attribute
	 */
	@Override
	public EnumAvailability getAvailability()
	{
		EnumAvailability avail = EnumAvailability.getEnum(getAttribute(AttributeName.AVAILABILITY, null, null));

		if (avail == null)
		{
			final JDFDevCap par = getParentDevCap();
			if (par != null)
			{
				avail = par.getAvailability();
			}
		}
		return avail == null ? EnumAvailability.Installed : avail;
	}

	/**
	 * get the parent devCap of this
	 *
	 * @return
	 */
	public JDFDevCap getParentDevCap()
	{
		return (JDFDevCap) getParentNode();
	}

	/**
	 * Sets attribute DevNS
	 *
	 * @param value the value to set the attribute to
	 */
	public void setDevNS(final String value)
	{
		setAttribute(AttributeName.DEVNS, value);
	}

	/**
	 * Gets URI attribute DevNS
	 *
	 * @return String: the attribute value
	 */
	public String getDevNS()
	{
		return getAttribute(AttributeName.DEVNS, null, JDFElement.getSchemaURL());
	}

	/**
	 * Sets attribute HasDefault, default=true
	 *
	 * @param value the value to set the attribute to
	 */
	public void setHasDefault(final boolean value)
	{
		setAttribute(AttributeName.HASDEFAULT, value, null);
	}

	/**
	 * Gets boolean attribute HasDefault
	 *
	 * @return boolean: the attribute value
	 */
	public boolean getHasDefault()
	{
		return getBoolAttribute(AttributeName.HASDEFAULT, null, true);
	}

	/**
	 * Sets attribute MaxOccurs,
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxOccurs(final int value)
	{
		setAttribute(AttributeName.MAXOCCURS, value, null);
	}

	/**
	 * Gets integer attribute MaxOccurs
	 *
	 * @return int: the attribute value
	 */
	public int getMaxOccurs()
	{
		final String s = getAttribute(AttributeName.MAXOCCURS, null, null);
		if (JDFConstants.UNBOUNDED.equals(s))
		{
			return Integer.MAX_VALUE;
		}
		return StringUtil.parseInt(s, 1);
	}

	/**
	 * Sets attribute MinOccurs, default=1
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinOccurs(final int value)
	{
		setAttribute(AttributeName.MINOCCURS, value, null);
	}

	/**
	 * Get integer attribute MinOccurs
	 *
	 * @return int: the attribute value
	 */
	public int getMinOccurs()
	{
		return getIntAttribute(AttributeName.MINOCCURS, JDFConstants.EMPTYSTRING, 1);
	}

	/**
	 * Sets String attribute Name<br>
	 * Since name is independent of the data type of the State element, the setter is defined here
	 *
	 * @param value the value to set the attribute to
	 */
	public void setName(final String value)
	{
		setAttribute(AttributeName.NAME, value);
	}

	/**
	 * Gets String attribute Name<br>
	 * Since name is independent of the data type of the State element,the getter is defined here
	 *
	 * @return String: the attribute value
	 */
	public String getName()
	{
		return getAttribute(AttributeName.NAME, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Sets attribute Required
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRequired(final boolean value)
	{
		setAttribute(AttributeName.REQUIRED, value, null);
	}

	/**
	 * Gets boolean attribute Required
	 *
	 * @return boolean: the attribute value
	 */
	public boolean getRequired()
	{
		return getBoolAttribute(AttributeName.REQUIRED, JDFConstants.EMPTYSTRING, false);
	}

	/**
	 * Sets attribute ListType, default=SingleValue
	 *
	 * @param value the value to set the attribute to
	 */
	public void setListType(final EnumListType value)
	{
		setAttribute(AttributeName.LISTTYPE, value.getName(), null);
	}

	/**
	 * Gets typesafe enumerated attribute ListType
	 *
	 * @return EnumListType: the enumeration value of the attribute
	 */
	public EnumListType getListType()
	{
		return EnumListType.getEnum(getAttribute(AttributeName.LISTTYPE, null, EnumListType.SingleValue.getName()));
	}

	/**
	 * Sets attribute ActionRefs
	 *
	 * @param value vector of ActionRefs
	 */
	public void setActionRefs(final VString value)
	{
		final StringBuffer strActionRefs = new StringBuffer(100);
		for (int i = 0; i < value.size(); i++)
		{
			strActionRefs.append(value.elementAt(i));
		}
		setAttribute(AttributeName.ACTIONREFS, strActionRefs.toString());
	}

	/**
	 * Gets NMTOKENS attribute ActionRefs
	 *
	 * @return VString: the attribute value
	 */
	public VString getActionRefs()
	{
		final String strActionRefs = getAttribute(AttributeName.ACTIONREFS, null, JDFConstants.EMPTYSTRING);
		return StringUtil.tokenize(strActionRefs, JDFConstants.COMMA, false);
	}

	/**
	 * Sets attribute Editable
	 *
	 * @param value the value to set the attribute to
	 */
	public void setEditable(final boolean value)
	{
		setAttribute(AttributeName.EDITABLE, value, null);
	}

	/**
	 * Gets boolean attribute Editable
	 *
	 * @return boolean: the attribute value
	 */
	public boolean getEditable()
	{
		return getBoolAttribute(AttributeName.EDITABLE, JDFConstants.EMPTYSTRING, true);
	}

	/**
	 * Sets attribute MacroRefs
	 *
	 * @param value vector of MacroRefs
	 */
	public void setMacroRefs(final VString value)
	{
		final StringBuffer strMacroRefs = new StringBuffer(100);
		for (int i = 0; i < value.size(); i++)
		{
			strMacroRefs.append(value.elementAt(i));
		}
		setAttribute(AttributeName.MACROREFS, strMacroRefs.toString());
	}

	/**
	 * Get NMTOKENS attribute MacroRefs
	 *
	 * @return VString: the attribute value
	 */
	public VString getMacroRefs()
	{
		final String strMacroRef = getAttribute(AttributeName.MACROREFS, null, JDFConstants.EMPTYSTRING);
		return StringUtil.tokenize(strMacroRef, JDFConstants.COMMA, false);
	}

	/**
	 * Sets attribute DependentMacroRef
	 *
	 * @param value vector of DependentMacroRef
	 */
	public void setDependentMacroRef(final String value)
	{
		setAttribute(AttributeName.DEPENDENTMACROREF, value);
	}

	/**
	 * Get string attribute DependentMacroRef
	 *
	 * @return String: the attribute value
	 */
	public String getDependentMacroRef()
	{
		return getAttribute(AttributeName.DEPENDENTMACROREF, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Sets attribute UserDisplay, default=Display
	 *
	 * @param value the value to set the attribute to
	 */
	public void setUserDisplay(final EnumUserDisplay value)
	{
		setAttribute(AttributeName.USERDISPLAY, value.getName(), null);
	}

	/**
	 * Gets typesafe enumerated attribute UserDisplay
	 *
	 * @return EnumUserDisplay: the enumeration value of the attribute
	 */
	public EnumUserDisplay getUserDisplay()
	{
		return EnumUserDisplay.getEnum(getAttribute(AttributeName.USERDISPLAY, null, EnumUserDisplay.Display.getName()));
	}

	/*
	 * // Element Getter / Setter
	 */
	// @{
	/**
	 * Gets the iSkip-th element Loc. If doesn't exist, it is created.
	 * <p>
	 * default: getCreateLoc(0)
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFLoc: the matching element
	 */
	public JDFLoc getCreateLoc(final int iSkip)
	{
		return (JDFLoc) getCreateElement(ElementName.LOC, JDFConstants.EMPTYSTRING, iSkip);
	}

	/**
	 * Gets the iSkip-th element Loc
	 * <p>
	 * default: getCreateLoc(0)
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFLoc: the matching element
	 */
	public JDFLoc getLoc(final int iSkip)
	{
		final JDFLoc e = (JDFLoc) getElement(ElementName.LOC, JDFConstants.EMPTYSTRING, iSkip);
		return e;
	}

	/**
	 * Appends element Loc to the end of <code>this</code>
	 *
	 * @return JDFLoc: newly created Loc element
	 */
	public JDFLoc appendLoc()
	{
		return (JDFLoc) appendElement(ElementName.LOC, null);
	}

	/**
	 * set attribute AllowedLength
	 *
	 * @param value the value to set the attribute to
	 */
	protected void setAllowedLength(final JDFIntegerRange value)
	{
		setAttribute(AttributeName.ALLOWEDLENGTH, value.toString());
	}

	/**
	 * get attribute AllowedLength
	 *
	 * @return JDFIntegerRange: the attribute value
	 */
	protected JDFIntegerRange getAllowedLength()
	{
		try
		{
			final String len = getAttribute(AttributeName.ALLOWEDLENGTH, null, null);
			if (len == null)
			{
				return null;
			}
			final JDFIntegerRange ir = new JDFIntegerRange(len);
			return ir;
		}
		catch (final DataFormatException dfe)
		{
			throw new JDFException("JDFAbstractState.getAllowedLength: Attribute ALLOWEDLENGTH is not capable to create JDFIntegerRange");
		}

	}

	protected void setPresentLength(final JDFIntegerRange value)
	{
		setAttribute(AttributeName.PRESENTLENGTH, value.toString());
	}

	protected JDFIntegerRange getPresentLength()
	{
		if (hasAttribute(AttributeName.PRESENTLENGTH))
		{
			try
			{
				final JDFIntegerRange ir = new JDFIntegerRange(getAttribute(AttributeName.PRESENTLENGTH));
				return ir;
			}
			catch (final DataFormatException dfe)
			{
				throw new JDFException("JDFAbstractState.getPresentLength: Attribute PRESENTLENGTH is not capable to create JDFIntegerRange");
			}
		}
		return getAllowedLength();
	}

	/**
	 * fitsLength - tests, if the defined String <code>str</code> matches AllowedLength or the PresentLength, specified for this State
	 *
	 * @param str string to test
	 * @param length switches between AllowedLength and PresentLength.
	 * @return boolean - true, if 'str' matches Length or if AllowedLength is not specified
	 */
	protected final boolean fitsLength(final String str, final EnumFitsValue length)
	{

		JDFIntegerRange lengthlist;
		if (length.equals(EnumFitsValue.Allowed))
		{
			lengthlist = getAllowedLength();
		}
		else
		{
			lengthlist = getPresentLength();
		}

		if (lengthlist != null)
		{
			final int len = str.length();
			return lengthlist.inRange(len);
		}
		return true;
	}

	/**
	 * gets the matching Attribute value String or AbstractSpan object from the parent, depending on the type of the state
	 *
	 * @param element the parent in which to search
	 * @return Object: either a String or AbstractSpan
	 */
	public Object getMatchingObjectInNode(final KElement element)
	{
		final String nam = getName();
		if (getListType().equals(EnumListType.Span))
		{
			return element.getElement(nam, getDevNS(), 0);
		}

		return element.getAttribute(nam, getDevNS(), null);
	}

	/**
	 * set the default values specified in this in element
	 *
	 * @param element the element to set the defaults on
	 * @param bAll
	 * @return true if successful
	 */
	public boolean setDefaultsFromCaps(final KElement element, final boolean bAll)
	{
		String def = getAttribute(AttributeName.DEFAULTVALUE, null, null);
		if (!bAll && def == null)
		{
			return false;
		}
		if (def == null)
		{
			def = getAttribute(AttributeName.CURRENTVALUE, null, null);
		}

		if (def == null)
		{
			def = getAttribute(AttributeName.ALLOWEDVALUELIST, null, null);
			if (def != null && (def.indexOf("~") >= 0 || def.indexOf(" ") >= 0))// allowedvaluelist
			// is
			// a
			// list
			// or
			// range
			{
				final String lt = getListType().getName();
				if (!lt.endsWith("List") && def.indexOf(" ") >= 0)
				{
					def = StringUtil.token(def, 0, " ");
				}
				else if (lt.indexOf("Range") < 0 && def.indexOf("~") >= 0)
				{
					def = null;
				}
			}
			if (def == null)
			{
				def = getXPathAttribute("Value/@AllowedValue", null);
			}
		}
		if (def == null)
		{
			if ((this instanceof JDFIntegerState) || (this instanceof JDFNumberState))
			{
				def = "1";
			}
			else if (this instanceof JDFXYPairState)
			{
				def = "1 1";
			}
			else if (this instanceof JDFBooleanState)
			{
				def = "true";
			}
			else if (this instanceof JDFMatrixState)
			{
				def = JDFMatrix.getUnitMatrix().toString();
			}
			else if (this instanceof JDFShapeState)
			{
				def = "1 2 3";
			}
			else if (this instanceof JDFDateTimeState)
			{
				def = new JDFDate().getDateTimeISO();
			}
			else if (this instanceof JDFDurationState)
			{
				def = new JDFDuration(42).getDurationISO();
			}
			else
			{
				def = "some_value"; // TODO add better type dependent value
				// generator
			}
		}
		final Object theValue = getMatchingObjectInNode(element);
		if (theValue != null)
		{
			return false;
		}

		String nam = getName();
		String nsURI = getDevNS();
		if (nsURI.equals(JDFElement.getSchemaURL()))
		{
			nsURI = null;
		}
		if (nsURI != null)
		{
			final String prefix = KElement.xmlnsPrefix(nam);
			if (prefix == null)
			{
				nam = StringUtil.token(nsURI, -1, "/") + ":" + nam;
			}
		}

		if (getListType().equals(EnumListType.Span))
		{
			final JDFIntentResource ir = (JDFIntentResource) element;
			final JDFSpanBase span = ir.appendSpan(nam, null);
			span.setAttribute(AttributeName.PREFERRED, def);
		}
		else
		// some attribute...
		{

			element.setAttribute(nam, def, nsURI);
		}
		return true;
	}

	/**
	 * fitsListType - tests, if the defined <code>value</code> matches value of ListType attribute, specified for this State
	 *
	 * @param value value to test
	 *
	 * @return boolean - true, if 'value' matches specified ListType
	 */
	protected final boolean fitsListType(final String value)
	{
		if (value == null)
		{
			return true;
		}

		final EnumListType listType = getListType();

		JDFRangeList rangelist; // lists of strings are most generic
		try
		{
			rangelist = new JDFNameRangeList(value);
		}
		catch (final DataFormatException e)
		{
			return false;
		}
		catch (final JDFException e)
		{
			return false;
		}

		if (listType == null || listType.equals(EnumListType.SingleValue))
		{// default ListType = SingleValue
			return value.indexOf(" ") == -1;
		}
		else if (listType.equals(EnumListType.RangeList) || listType.equals(EnumListType.Span))
		{
			return true;
		}
		else if (listType.equals(EnumListType.Range))
		{
			return rangelist.size() == 1;
		}
		else if (listType.equals(EnumListType.List) || listType.equals(EnumListType.CompleteList) || // complete or
		// not -
		// tested in
		// fitsValueList
				listType.equals(EnumListType.CompleteOrderedList) || // tested
				// in
				// fitsValueList
				listType.equals(EnumListType.ContainedList)) // tested in
		// fitsValueList
		{
			return rangelist.isList();
		}
		else if (listType.equals(EnumListType.OrderedList))
		{
			return (rangelist.isList() && rangelist.isOrdered());
		}
		else if (listType.equals(EnumListType.UniqueList))
		{
			return (rangelist.isList() && rangelist.isUnique());
		}
		else if (listType.equals(EnumListType.UniqueOrderedList))
		{
			return (rangelist.isList() && rangelist.isUniqueOrdered());
		}
		else if (listType.equals(EnumListType.OrderedRangeList))
		{
			return rangelist.isOrdered();
		}
		else if (listType.equals(EnumListType.UniqueRangeList))
		{
			return rangelist.isUnique();
		}
		else if (listType.equals(EnumListType.UniqueOrderedRangeList))
		{
			return (rangelist.isUniqueOrdered());
		}
		else
		{
			throw new JDFException("JDFDateTimeState.fitsListType illegal ListType attribute");
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.core.JDFElement#getInvalidAttributes(org.cip4.jdflib. core.KElement.EnumValidationLevel, boolean, int)
	 */
	protected VString getInvalidAttributesImpl(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{
		final VString v = super.getInvalidAttributes(level, bIgnorePrivate, nMax);
		if (nMax > 0 && v.size() >= nMax)
		{
			return v;
		}
		if (!fitsListType(getAttribute(AttributeName.DEFAULTVALUE)))
		{
			v.appendUnique(AttributeName.DEFAULTVALUE);
		}
		if (!fitsListType(getAttribute(AttributeName.CURRENTVALUE)))
		{
			v.appendUnique(AttributeName.CURRENTVALUE);
		}
		return v;
	}

	/**
	 * Gets the j-th element Loc of the i-th element Value
	 *
	 * @param iSkip number of Value elements to skip (iSkip=0 - first Value element)
	 * @param jSkip number of Loc subelements of i-th Value element to skip, (jSkip=0 - first Loc element)
	 * @return JDFLoc: the matching Loc element
	 */
	public JDFLoc getValueLocLoc(final int iSkip, final int jSkip)
	{
		final JDFValueLoc val = getValueLoc(iSkip);
		if (val == null)
		{
			return null;
		}
		return val.getLoc(jSkip);
	}

	/**
	 * get iSkip'th element Loc
	 *
	 * @param iSkip number of Value elements to skip (iSkip=0 - get first element)
	 * @return JDFValueLoc: the element
	 */
	final public JDFValueLoc getValueLoc(final int iSkip)
	{
		return (JDFValueLoc) getElement(ElementName.VALUELOC, null, iSkip);
	}

	/**
	 * appends element Loc to the end of the i-th subelement Value
	 *
	 * @param iSkip number of Value elements to skip (iSkip=0 - first Value element)
	 * @return JDFLoc: newly created Loc element
	 */
	public JDFLoc appendValueLocLoc(final int iSkip)
	{
		final JDFValueLoc val = getValueLoc(iSkip);
		if (val == null)
		{
			return null;
		}
		return val.appendLoc();
	}

	/**
	 * Appends element ValueLoc
	 *
	 * @return JDFLoc: newly created ValueLoc element
	 */
	final public JDFValueLoc appendValueLoc()
	{
		return (JDFValueLoc) appendElement(ElementName.VALUELOC, null);
	}

	/**
	 * fitsRegExp - checks whether <code>str</code> matches the AllowedRegExp/PresentRegExp specified for this State
	 *
	 * @param str string to test
	 * @param regexp switches between AllowedRegExp and PresentRegExp.
	 * @return boolean - true, if <code>str</code> matches the RegExp or if AllowedRegExp is not specified
	 */
	protected final boolean fitsRegExp(final String str, final EnumFitsValue regexp)
	{
		String rExp;
		if (regexp.equals(EnumFitsValue.Allowed))
		{
			rExp = getAllowedRegExp();
		}
		else
		{
			rExp = getPresentRegExp();
		}
		if (rExp.length() == 0)
		{
			return true; // if AllowedRegExp is not specified return true
		}

		if (!StringUtil.matches(str, rExp))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	protected String getPresentRegExp()
	{
		// dummy - never used
		return null;
	}

	/**
	 * @return
	 */
	protected String getAllowedRegExp()
	{
		// dummy - never used
		return null;
	}

	/**
	 * get the availability of this devcaps based on the list of installed modules in ModuleRefs and ModulePool
	 *
	 * @return
	 */
	public EnumAvailability getModuleAvailability()
	{
		return JDFModulePool.getModuleAvailability(this);
	}

	/**
	 * @see org.cip4.jdflib.ifaces.ICapabilityElement#getModulePool()
	 * @return
	*/
	@Override
	public JDFModulePool getModulePool()
	{
		return (JDFModulePool) getParentPool(ElementName.MODULEPOOL);
	}

	/**
	 * @see org.cip4.jdflib.ifaces.ICapabilityElement#getCreateModulePool()
	 * @return
	*/
	@Override
	public JDFModulePool getCreateModulePool()
	{
		return (JDFModulePool) getCreateParentPool(ElementName.MODULEPOOL);
	}

	/**
	 * get the DEvCapPool that contains devcap elements referenced by this
	 * @param poolName
	 *
	 * @return KElement the pool
	 */
	private KElement getParentPool(final String poolName)
	{
		final KElement parent = getPoolParent();
		return parent.getElement(poolName);
	}

	/**
	 * get the DEvCapPool that contains devcap elements referenced by this
	 * @param poolName
	 *
	 * @return KElement the pool
	 */
	private KElement getCreateParentPool(final String poolName)
	{
		final KElement parent = getPoolParent();
		return parent.getCreateElement(poolName);
	}

	/**
	 *
	 * @return
	 */
	private KElement getPoolParent()
	{
		KElement parent = getDeepParent(ElementName.DEVICECAP, 0);
		if (parent == null)
		{
			parent = getDeepParent(ElementName.MESSAGESERVICE, 0);
		}
		if (!(parent instanceof JDFDeviceCap) && !(parent instanceof JDFMessageService))
		{
			throw new JDFException("JDFDevCap.getParentPool - invalid parent context");
		}
		return parent;
	}

	/**
	 * (21) get VString attribute ModuleRefs
	 *
	 * @return VString the value of the attribute
	 */
	@Override
	public VString getModuleRefs()
	{
		return StringUtil.tokenize(getAttribute(AttributeName.MODULEREFS, null, null), " ", false);
	}

	/**
	 * @see org.cip4.jdflib.ifaces.ICapabilityElement#appendModuleRef(java.lang.String)
	 * @param id
	 * @return
	*/
	@Override
	public JDFModuleCap appendModuleRef(final String id)
	{
		return JDFModulePool.appendModuleRef(this, id);
	}

	/**
	 * @see org.cip4.jdflib.core.KElement#init()
	 * @return
	*/
	@Override
	public boolean init()
	{
		appendAnchor(null);
		return super.init();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.core.JDFElement#getIDPrefix()
	 */
	@Override
	protected String getIDPrefix()
	{
		return "d";
	}

	/**
	 * @see org.cip4.jdflib.ifaces.ICapabilityElement#getEvaluationType()
	 * @return
	*/
	@Override
	public abstract EnumTerm getEvaluationType();

}