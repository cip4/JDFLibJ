/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of
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
 *========================================================================== class JDFSignature extends JDFAutoSignature
 * created 2001-09-06T10:02:57GMT+02:00 ==========================================================================
 *          @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 *              @Author sabjon@topmail.de   using a code generator
 * Warning! very preliminary test version. Interface subject to change without prior notice! Revision history:   ...
 */

package org.cip4.jdflib.resource;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoLayout;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.postpress.JDFSheet;

/**
 * class that maps both patitioned and non-partitoned layouts
 * 
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class JDFSignature extends JDFAutoLayout
{
	private static final long serialVersionUID = 1L;

	// Handle partitioned layouts
	private static ElemInfoTable[] elemInfoTable_Sheet = new ElemInfoTable[1];
	static
	{
		elemInfoTable_Sheet[0] = new ElemInfoTable(ElementName.SHEET, 0x44444333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return new ElementInfo(super.getTheElementInfo(), elemInfoTable_Sheet);
	}

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.NAME, 0x44444333, AttributeInfo.EnumAttributeType.string, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		final AttributeInfo ai = super.getTheAttributeInfo();
		if (getLocalName().equals(ElementName.SIGNATURE))
		{
			ai.updateReplace(atrInfoTable);
		}
		return ai;
	}

	/**
	 * Constructor for JDFSignature
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFSignature(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSignature
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFSignature(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSignature
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFSignature(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSignature[  --> " + super.toString() + " ]";
	}

	// /////////////////////////////////////////////////////////////////

	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		if (getLocalName().equals(ElementName.SIGNATURE)) // signatures are
		// resource elements
		// only!, no class
		{
			removeAttribute(AttributeName.CLASS);
		}
		return bRet;
	}

	/**
	 * gets or appends a JDFSheet in both old and new Layouts <li>if old: a <code>Sheet</code> element <li>if new: a <code>SheetName</code> partition leaf
	 * 
	 * @param iSkip the number of Sheets to skip
	 * @return JDFSheet
	 */
	public JDFSheet getCreateSheet(final int iSkip)
	{
		JDFSheet s = getSheet(iSkip);
		if (s == null)
		{
			s = appendSheet();
		}
		return s;
	}

	/**
	 * gets a Sheet in both old and new Layouts <li>if old: a <code>Sheet</code> element <li>if new: a <code>SheetName</code> partition leaf
	 * 
	 * @param iSkip the number of Sheets to skip
	 * @return JDFSheet
	 */
	public JDFSheet getSheet(final int iSkip)
	{
		return getLayoutElement(this, ElementName.SHEET, AttributeName.SHEETNAME, iSkip);
	}

	/**
	 * gets a signature in both old and new Layouts if old: a <Signature> element if new: a SignatureName partition leaf
	 * @param sheetName the SheetName partition key value(new) or Sheet/@Name(old)
	 * 
	 * @return the signature
	 */
	public JDFSheet getSheet(final String sheetName)
	{
		return getLayoutElement(this, ElementName.SHEET, AttributeName.SHEETNAME, sheetName);
	}

	/**
	 * gets a signature in both old and new Layouts if old: a <Signature>creates it if it does not exist element if new: a SignatureName partition leaf
	 * @param sheetName the SheetName partition key value(new) or Sheet/@Name(old)
	 * 
	 * @return the signature
	 * @throws JDFException
	 */
	public JDFSheet getCreateSheet(final String sheetName) throws JDFException
	{
		return getCreateLayoutElement(this, ElementName.SHEET, AttributeName.SHEETNAME, sheetName);
	}

	/**
	 * counts the number of Sheets in both old and new Layouts <li>if old: a <code>Sheet</code> element <li>if new: a <code>SheetName</code> partition leaf
	 * 
	 * @return the number of Sheets
	 */
	public int numSheets()
	{
		return numLayoutElements(this, ElementName.SHEET, AttributeName.SHEETNAME);
	}

	/**
	 * (28) get vector of all direct child elements Sheet
	 * 
	 * @param mAttrib the map of attributes to select
	 * @param bAnd if true all attributes in the map are AND'ed, else they are OR'ed
	 * @deprecated use getChildElementVector() instead
	 */
	@Deprecated
	public VElement getSheetVector(final JDFAttributeMap mAttrib, final boolean bAnd)
	{
		final VElement myResource = new VElement();
		final Vector vElem = getChildElementVector(ElementName.SHEET, null, mAttrib, bAnd, 0, true);
		for (int i = 0; i < vElem.size(); i++)
		{
			final JDFElement k = (JDFElement) vElem.elementAt(i);
			final JDFSheet myJDFSheet = (JDFSheet) k;
			myResource.addElement(myJDFSheet);
		}
		return myResource;
	}

	/**
	 * get the vector of sheets in this signature
	 * 
	 * @return {@link VElement} the vector of signatures in this
	 */
	public VElement getSheetVector()
	{
		return getLayoutElementVector(this, ElementName.SHEET, AttributeName.SHEETNAME);
	}

	/**
	 * appends a Sheet in both old and new Layouts <li>if old: a <code>Sheet</code> element <li>if new: a <code>SheetName</code> partition leaf
	 */
	public JDFSheet appendSheet() throws JDFException
	{
		return appendLayoutElement(this, ElementName.SHEET, AttributeName.SHEETNAME);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 * 
	 * @param refTarget the element that is referenced
	 */
	public void refSheet(final JDFSheet refTarget)
	{
		if (JDFLayout.isNewLayout(this))
		{
			throw new JDFException("refSheet: attempting to reference a partition");
		}
		refElement(refTarget);
	}

	/**
	 * appends a signature in both old and new Layouts if old: a <code>< Signature></code> element if new: a SignatureName partition leaf
	 * @param layout
	 * @param elementName
	 * @param partitionKeyName
	 * 
	 * @return JDFLayout
	 * @throws JDFException
	 */
	protected static JDFLayout appendLayoutElement(final JDFResource layout, final String elementName, final String partitionKeyName) throws JDFException
	{
		JDFLayout s = null;
		if (JDFLayout.isNewLayout(layout))
		{
			final int n = 1 + numLayoutElements(layout, elementName, partitionKeyName);
			s = (JDFLayout) layout.addPartition(EnumPartIDKey.getEnum(partitionKeyName), partitionKeyName + String.valueOf(n));
		}
		else
		{
			s = (JDFLayout) layout.appendElement(elementName);
		}
		return s;
	}

	/**
	 * get the number of layout elements
	 * 
	 * @param layout
	 * @param elementName
	 * @param partitionKeyName
	 * @return int: number of layout elements
	 */
	protected static int numLayoutElements(final JDFResource layout, final String elementName, final String partitionKeyName)
	{
		final VElement v = getLayoutElementVector(layout, elementName, partitionKeyName);
		return v == null ? 0 : v.size();
	}

	/**
	 * get a specific layout element, in old style (pre 1.3) layouts, this must be a direct child wherea post 1.3 layouts allow for search of lower level elements (grandchildren etc.) 
	 * 
	 * @param layout
	 * @param elementName
	 * @param partitionKeyName
	 * @param iSkip the index of the element, negative values count backwards from the end
	 * @return JDFLayout: the element
	 */
	protected static JDFLayout getLayoutElement(final JDFResource layout, final String elementName, final String partitionKeyName, int iSkip)
	{
		JDFLayout s = null;
		if (JDFLayout.isNewLayout(layout))
		{
			VElement v = layout.getLeaves(true);
			final VElement v2 = new VElement();
			for (int i = 0; i < v.size(); i++)
			{
				if (v.get(i).hasAttribute_KElement(partitionKeyName, null, false))
				{
					v2.add(v.get(i));
				}
			}
			v = v2;
			if (iSkip < 0)
			{
				iSkip = v.size() + iSkip;
			}

			if (iSkip >= 0 && v.size() > iSkip)
			{
				s = (JDFLayout) v.elementAt(iSkip);
			}
		}
		else
		{
			s = (JDFLayout) layout.getElement(elementName, null, iSkip);
		}
		return s;
	}

	/**
	 * get a specific layout element by name
	 * 
	 * @param layout
	 * @param elementName
	 * @param partitionKeyName
	 * @param objectName
	 * @return JDFLayout: the element
	 */
	protected static JDFLayout getLayoutElement(final JDFResource layout, final String elementName, final String partitionKeyName, final String objectName)
	{
		JDFLayout s = null;
		if (JDFLayout.isNewLayout(layout))
		{
			s = (JDFLayout) layout.getPartition(new JDFAttributeMap(partitionKeyName, objectName), null);
		}
		else
		{
			s = (JDFLayout) layout.getChildWithAttribute(elementName, AttributeName.NAME, null, objectName, 0, true);
		}
		return s;
	}

	/**
	 * get a specific layout element by name, creates it if it does not exist
	 * 
	 * @param layout
	 * @param elementName
	 * @param partitionKeyName
	 * @param objectName
	 * @return JDFLayout: the element
	 * @throws JDFException if the location of a newly created element is not well defined
	 */
	protected static JDFLayout getCreateLayoutElement(final JDFResource layout, final String elementName, final String partitionKeyName, final String objectName)
	{
		JDFLayout s = getLayoutElement(layout, elementName, partitionKeyName, objectName);
		if (s != null)
		{
			return s;
		}
		if (JDFLayout.isNewLayout(layout))
		{
			s = (JDFLayout) layout.addPartition(EnumPartIDKey.getEnum(partitionKeyName), objectName);
		}
		else
		{
			s = (JDFLayout) layout.appendElement(elementName);
			s.setName(objectName);
		}
		return s;
	}

	/**
	 * get a vector of specific layout elements
	 * 
	 * @param layout
	 * @param elementName
	 * @param partitionKeyName
	 * @return VElement: the vector of elements
	 */
	protected static VElement getLayoutElementVector(final JDFResource layout, final String elementName, final String partitionKeyName)
	{
		if (JDFLayout.isNewLayout(layout))
		{
			return layout.getChildElementVector_JDFElement(ElementName.LAYOUT, null, new JDFAttributeMap(partitionKeyName, (String) null), true, 0, true);
		}
		return layout.getChildElementVector_JDFElement(elementName, null, null, true, 0, true);
	}

	/**
	 * get the leaves of a layout, either pre 1.2 or post 1.3
	 * 
	 * @return VElement the layout leaves, i.e. partition leaves(1.3+) or explicit surfaces(1.2-)
	 */
	public VElement getLayoutLeaves(final boolean bAll)
	{
		if (JDFLayout.isNewLayout(this))
		{
			return getLeaves(bAll);
		}
		return getChildrenByTagName(ElementName.SURFACE, null, null, false, true, -1, true);
	}

	/**
	 * if this is a new layout, return the partition key signaturename else return Signature/@Name of this or its appropriate parent
	 * 
	 * @return the name of the signature
	 */
	@Override
	public String getSignatureName()
	{
		if (getLocalName().equals(ElementName.SIGNATURE))
		{
			return getName();
		}
		final KElement k = getParentNode_KElement();
		if (getLocalName().equals(ElementName.SHEET))
		{
			if (k instanceof JDFSignature)
			{
				final JDFSignature sh = (JDFSignature) k;
				return sh.getSignatureName();
			}
			else if (k instanceof JDFResourcePool)
			{
				final JDFNode n = ((JDFElement) k).getParentJDF();
				final KElement surfaceRef = n.getChildWithAttribute("SheetRef", AttributeName.RREF, null, getID(), 0, false);
				if (surfaceRef != null)
				{
					final KElement shE = surfaceRef.getParentNode_KElement();
					if (shE instanceof JDFSignature)
					{
						final JDFSignature sh = (JDFSignature) shE;
						return sh.getSignatureName();
					}
				}
			}

		}
		else if (getLocalName().equals(ElementName.SURFACE))
		{
			final KElement parentNode = getParentNode_KElement().getParentNode_KElement();
			if (parentNode instanceof JDFSignature)
			{
				final JDFSignature sig = (JDFSignature) parentNode;
				return sig.getSignatureName();
			}
			else if (k instanceof JDFResourcePool)
			{
				final JDFNode n = ((JDFElement) k).getParentJDF();
				final KElement surfaceRef = n.getChildWithAttribute("SurfaceRef", AttributeName.RREF, null, getID(), 0, false);
				if (surfaceRef != null)
				{
					final KElement shE = surfaceRef.getParentNode_KElement();
					if (shE instanceof JDFSheet)
					{
						final JDFSheet sh = (JDFSheet) shE;
						return sh.getSignatureName();
					}
				}
			}

		}
		return super.getSignatureName();
	}

	/**
	 * gets the corresponding media with a given mediatype
	 * @param mediaType the mediaType - must NOT be null
	 * @return the media, null if none is there or mediaType==null;
	 */
	public JDFMedia getMedia(final EnumMediaType mediaType)
	{
		if (mediaType == null)
		{
			return null;
		}

		final VElement v = getChildElementVector(ElementName.MEDIA, null);
		if (v != null)
		{
			final int siz = v.size();
			for (int i = 0; i < siz; i++)
			{
				final JDFMedia m = (JDFMedia) v.get(i);
				if (mediaType.equals(m.getMediaType()))
				{
					return m;
				}
			}
		}

		return null;
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute SurfaceContentsBox
	--------------------------------------------------------------------- */
	/**
	  * (36) set attribute SurfaceContentsBox
	  * @param value the value to set the attribute to
	 * @param precision 
	  */
	public void setSurfaceContentsBox(JDFRectangle value, int precision)
	{
		setAttribute(AttributeName.SURFACECONTENTSBOX, value, null, precision);
	}

}