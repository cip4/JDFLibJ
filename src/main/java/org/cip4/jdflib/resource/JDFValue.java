/**
 * ==========================================================================
 * class JDFValue extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   using a code generator
 * Warning! very preliminary test version.
 * Interface subject to change without prior notice!
 * Revision history:   ...
 */

package org.cip4.jdflib.resource;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoValue.EnumValueUsage;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.devicecapability.JDFAbstractState;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;
import org.cip4.jdflib.resource.devicecapability.JDFMatrixEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFMatrixState;
import org.cip4.jdflib.resource.devicecapability.JDFPDFPathEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFPDFPathState;
import org.cip4.jdflib.resource.devicecapability.JDFStringEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFStringState;
import org.w3c.dom.Node;

public class JDFValue extends JDFElement // ignore JDFAutoValue
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.VALUEUSAGE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumValueUsage.getEnum(0), null);
	}

	private static AtrInfoTable[] atrInfoTable_matrix = new AtrInfoTable[2];
	static
	{
		atrInfoTable_matrix[0] = new AtrInfoTable(AttributeName.ALLOWEDVALUE, 0x22222222, AttributeInfo.EnumAttributeType.matrix, null, null);
		atrInfoTable_matrix[1] = new AtrInfoTable(AttributeName.PRESENTVALUE, 0x44444431, AttributeInfo.EnumAttributeType.matrix, null, null);
	}

	private static AtrInfoTable[] atrInfoTable_pdfpath = new AtrInfoTable[2];
	static
	{
		atrInfoTable_pdfpath[0] = new AtrInfoTable(AttributeName.ALLOWEDVALUE, 0x22222222, AttributeInfo.EnumAttributeType.PDFPath, null, null);
		atrInfoTable_pdfpath[1] = new AtrInfoTable(AttributeName.PRESENTVALUE, 0x44444431, AttributeInfo.EnumAttributeType.PDFPath, null, null);
	}

	private static AtrInfoTable[] atrInfoTable_string = new AtrInfoTable[2];
	static
	{
		atrInfoTable_string[0] = new AtrInfoTable(AttributeName.ALLOWEDVALUE, 0x22222222, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable_string[1] = new AtrInfoTable(AttributeName.PRESENTVALUE, 0x44444431, AttributeInfo.EnumAttributeType.string, null, null);
	}
	private static AtrInfoTable[] atrInfoTable_matrixEval = new AtrInfoTable[1];
	static
	{
		atrInfoTable_matrixEval[0] = new AtrInfoTable(AttributeName.VALUE, 0x22222222, AttributeInfo.EnumAttributeType.matrix, null, null);
	}

	private static AtrInfoTable[] atrInfoTable_pdfpathEval = new AtrInfoTable[1];
	static
	{
		atrInfoTable_pdfpathEval[0] = new AtrInfoTable(AttributeName.VALUE, 0x22222222, AttributeInfo.EnumAttributeType.PDFPath, null, null);
	}

	private static AtrInfoTable[] atrInfoTable_stringEval = new AtrInfoTable[1];
	static
	{
		atrInfoTable_stringEval[0] = new AtrInfoTable(AttributeName.VALUE, 0x22222222, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		final AttributeInfo ai = super.getTheAttributeInfo();

		final KElement parent = getParentNode_KElement();
		if (parent instanceof JDFAbstractState)
		{
			ai.updateReplace(atrInfoTable);
		}

		if (parent instanceof JDFMatrixState)
		{
			ai.updateReplace(atrInfoTable_matrix);
		}
		else if (parent instanceof JDFPDFPathState)
		{
			ai.updateReplace(atrInfoTable_pdfpath);
		}
		else if (parent instanceof JDFStringState)
		{
			ai.updateReplace(atrInfoTable_string);
		}
		else if (parent instanceof JDFMatrixEvaluation)
		{
			ai.updateReplace(atrInfoTable_matrixEval);
		}
		else if (parent instanceof JDFPDFPathEvaluation)
		{
			ai.updateReplace(atrInfoTable_pdfpathEval);
		}
		else if (parent instanceof JDFStringEvaluation)
		{
			ai.updateReplace(atrInfoTable_stringEval);
		}

		return ai;

	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x33333311);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		final ElementInfo ei = super.getTheElementInfo();

		final Node parentNode = getParentNode();
		if (parentNode instanceof JDFAbstractState)
		{
			ei.updateAdd(elemInfoTable);
		}
		return ei;
	}

	/**
	 * Constructor for JDFValue
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFValue(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFValue
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFValue(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFValue
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFValue(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @deprecated use EnumValueUsage.getEnum(enumName);
	 * @param enumName
	 * @return
	 */
	@Deprecated
	public static EnumValueUsage stringToValueUsage(final String enumName)
	{
		return EnumValueUsage.getEnum(enumName);
	}

	/**
	 * (14) set attribute AllowedValue
	 *
	 * @param String value: the value to set the attribute to
	 */
	public void setAllowedValue(final String value)
	{
		setAttribute(AttributeName.ALLOWEDVALUE, value, null);
	}

	/**
	 * (23) get String attribute AllowedValue
	 *
	 * @return String the value of the attribute
	 */
	public String getAllowedValue()
	{
		return getAttribute(AttributeName.ALLOWEDVALUE, null, "");
	}

	/**
	 * (14) set attribute PresentValue
	 *
	 * @param String value: the value to set the attribute to
	 */
	public void setPresentValue(final String value)
	{
		setAttribute(AttributeName.PRESENTVALUE, value, null);
	}

	/**
	 * (23) get String attribute PresentValue
	 *
	 * @return String the value of the attribute
	 */
	public String getPresentValue()
	{
		return getAttribute(AttributeName.PRESENTVALUE, null, "");
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ValueUsage ---------------------------------------------------------------------
	 */
	// //////////////////////////////////////////////////////////////////////
	/**
	 * (5) set attribute ValueUsage
	 *
	 * @param EnumValueUsage enumVar: the enumVar to set the attribute to
	 */
	public void setValueUsage(final EnumValueUsage enumVar)
	{
		setAttribute(AttributeName.VALUEUSAGE, enumVar, null);
	}

	/**
	 * (9) get ValueUsage attribute ValueUsage
	 *
	 * @return EnumValueUsage the value of the attribute
	 */
	public EnumValueUsage getValueUsage()
	{
		return EnumValueUsage.getEnum(getAttribute(AttributeName.VALUEUSAGE, null, null));
	}

	/**
	 * (14) set attribute Value
	 *
	 * @param String value: the value to set the attribute to
	 */
	public void setValue(final String value)
	{
		setAttribute(AttributeName.VALUE, value, null);
	}

	/**
	 * (23) get String attribute Value
	 *
	 * @return String the value of the attribute
	 */
	public String getValue()
	{
		return getAttribute(AttributeName.VALUE, null, null);
	}

	/*
	 * // Element getter / setter
	 */

	/**
	 * (26) getCreateLoc
	 *
	 * @param int iSkip number of elements to skip
	 * @return JDFLoc the element
	 */
	public JDFLoc getCreateLoc(final int iSkip)
	{
		return (JDFLoc) getCreateElement_KElement("Loc", null, iSkip);
	}

	/**
	 * (27) const get element Loc
	 *
	 * @param int iSkip number of elements to skip
	 * @return JDFLoc the element default is getLoc(0)
	 */
	public JDFLoc getLoc(final int iSkip)
	{
		return (JDFLoc) getElement("Loc", null, iSkip);
	}

	/**
	 * (28) get vector of all direct child elements Loc
	 *
	 * @param JDFAttributeMap mAttrib the map of attributes to select
	 * @param boolean bAnd if true all attributes in the map are AND'ed, else they are OR'ed
	 * @deprecated use getChildElementVector() instead
	 */
	@Deprecated
	public VElement getLocVector(final JDFAttributeMap mAttrib, final boolean bAnd)
	{
		final VElement myResource = new VElement();
		final Vector vElem = getChildElementVector("Loc", null, mAttrib, bAnd, 0, true);
		for (int i = 0; i < vElem.size(); i++)
		{
			final JDFElement k = (JDFElement) vElem.elementAt(i);
			final JDFLoc myJDFLoc = (JDFLoc) k;
			myResource.addElement(myJDFLoc);
		}
		return myResource;
	}

	/**
	 * @deprecated use getChildElementVector() instead
	 */
	@Deprecated
	public VElement getLocVector()
	{
		return getLocVector(new JDFAttributeMap(), true);
	}

	public JDFLoc appendLoc()
	{
		return (JDFLoc) appendElement(ElementName.LOC, null);
	}

}// end namespace JDF
