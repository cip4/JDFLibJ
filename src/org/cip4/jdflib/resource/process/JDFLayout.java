/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of
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
 * ========================================================================== 
 * class JDFLayout extends JDFAutoLayout
 * created 2001-09-05T08:21:57GMT+02:00 
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFSignature;
import org.cip4.jdflib.resource.process.postpress.JDFSheet;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class JDFLayout extends JDFSurface
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFLayout
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFLayout(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFLayout
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFLayout(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFLayout
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFLayout(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * @see org.cip4.jdflib.resource.process.JDFSurface#toString()
	 */
	@Override
	public String toString()
	{
		return "JDFLayout[  --> " + super.toString() + " ]";
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * generate a JDF 1.3 compatible Layout from this (1.2)
	 * 
	 * @return true if successful
	 */
	public boolean toNewLayout()
	{
		final VElement vSig = getChildElementVector(ElementName.SIGNATURE, null, null, false, 0, false);
		// loop over all signatures and rename them to Layout
		for (int iSig = 0; iSig < vSig.size(); iSig++)
		{
			JDFElement rSig = (JDFElement) vSig.elementAt(iSig);
			if (rSig instanceof JDFRefElement)
			{
				rSig = ((JDFRefElement) rSig).inlineRef();
			}
			JDFLayout newLO = (JDFLayout) rSig.renameElement(ElementName.LAYOUT, null);
			newLO.setPartIDKey(EnumPartIDKey.SignatureName, rSig.getAttribute(AttributeName.NAME, null, "Sig" + String.valueOf(iSig)));
			newLO.cleanLayoutLeaf();

			final VElement vSheet = newLO.getChildElementVector(ElementName.SHEET, null, null, false, 0, false);
			// loop over all sheets and rename them to Layout
			for (int iSheet = 0; iSheet < vSheet.size(); iSheet++)
			{
				JDFElement rSheet = (JDFElement) vSheet.elementAt(iSheet);
				if (rSheet instanceof JDFRefElement)
				{
					rSheet = ((JDFRefElement) rSheet).inlineRef();
				}
				newLO = (JDFLayout) rSheet.renameElement(ElementName.LAYOUT, null);
				newLO.setPartIDKey(EnumPartIDKey.SheetName, rSheet.getAttribute(AttributeName.NAME, null, "Sheet" + String.valueOf(iSheet)));
				newLO.cleanLayoutLeaf();

				final VElement vSurf = newLO.getChildElementVector(ElementName.SURFACE, null, null, false, 0, false);
				// loop over all surfaces and rename them to Layout
				for (int iSurf = 0; iSurf < vSurf.size(); iSurf++)
				{
					JDFElement rSurf = (JDFElement) vSurf.elementAt(iSurf);
					if (rSurf instanceof JDFRefElement)
					{
						rSurf = ((JDFRefElement) rSurf).inlineRef();
					}
					newLO = (JDFLayout) rSurf.renameElement(ElementName.LAYOUT, null);
					newLO.setPartIDKey(EnumPartIDKey.Side, rSurf.getAttribute(AttributeName.SIDE, null, "Surf" + String.valueOf(iSurf)));
					newLO.cleanLayoutLeaf();
				}
			}
		}
		return true;
	}

	// //////////////////////////////////////////////////////
	/**
	 * routine to clean up bookkeeping variables when moving from resource to partition leaf
	 */
	private void cleanLayoutLeaf()
	{
		removeAttribute(AttributeName.NAME);
		removeAttribute(AttributeName.CLASS);
	}

	// /////////////////////////////////////////////////////////////////
	/**
	 * generate a JDF 1.2 compatible Layout from this (1.3)
	 * 
	 * @return bool true if successful
	 * 
	 */
	public boolean fromNewLayout()
	{
		// TODO: fix content object placement
		VElement vLO = getChildElementVector_JDFElement(ElementName.LAYOUT, null, new JDFAttributeMap("SignatureName", ""), false, 0, false);
		final VElement vSig = new VElement();
		if (vLO.isEmpty())
		{
			final JDFSignature signature = (JDFSignature) appendElement(ElementName.SIGNATURE);
			signature.setName("Sig_00");
			vSig.add(signature);
			moveElementsTo((JDFLayout) signature);
		}
		else
		{
			JDFSignature sig = null;
			for (int i = 0; i < vLO.size(); i++)
			{
				final JDFElement lo = (JDFElement) vLO.elementAt(i);
				sig = null;
				if (lo.hasAttribute(AttributeName.SIGNATURENAME))
				{
					lo.renameAttribute(AttributeName.SIGNATURENAME, AttributeName.NAME, null, null);
					sig = (JDFSignature) lo.renameElement(ElementName.SIGNATURE, null);
					sig.cleanResourceAttributes();
					vSig.add(sig);
				}
				else
				{
					if (vSig.isEmpty())
					{
						final JDFSignature signature = (JDFSignature) appendElement(ElementName.SIGNATURE);
						signature.setName("Sig_00");
						vSig.add(sig);
					}
				}
				if (sig != null)
				{
					moveElement(sig, null);
				}
			}
		}
		int nSheet = 0;
		for (int iSig = 0; iSig < vSig.size(); iSig++)
		{
			final JDFSignature sig = (JDFSignature) vSig.elementAt(iSig);
			vLO = sig.getChildElementVector_JDFElement(ElementName.LAYOUT, null, new JDFAttributeMap("SheetName", ""), false, 0, false);
			final VElement vSheet = new VElement();
			if (vLO.isEmpty())
			{
				nSheet++;
				final JDFSheet sheet = (JDFSheet) sig.appendElement(ElementName.SHEET);
				sheet.setName("Sheet_" + String.valueOf(nSheet));
				vSheet.add(sheet);
				((JDFLayout) sig).moveElementsTo((JDFLayout) sheet);
			}
			else
			{
				JDFSheet sheet = null;
				for (int i = 0; i < vLO.size(); i++)
				{
					sheet = null;
					final JDFElement lo = (JDFElement) vLO.elementAt(i);
					if (lo.hasAttribute(AttributeName.SHEETNAME))
					{
						lo.renameAttribute(AttributeName.SHEETNAME, AttributeName.NAME, null, null);
						sheet = (JDFSheet) lo.renameElement(ElementName.SHEET, null);
						sheet.cleanResourceAttributes();
						vSheet.add(sheet);
						nSheet++;
					}
					else
					{
						if (vSheet.isEmpty())
						{
							nSheet++;
							sheet = (JDFSheet) sig.appendElement(ElementName.SHEET);
							sheet.setName("Sheet_" + String.valueOf(nSheet));
							vSheet.add(sheet);
						}
						if (sheet != null)
						{
							sheet.moveElement(lo, null);
						}
					}
				}
			}

			for (int iSheet = 0; iSheet < vSheet.size(); iSheet++)
			{
				final JDFSheet sheet = (JDFSheet) vSheet.elementAt(iSheet);
				vLO = sheet.getChildElementVector_JDFElement(ElementName.LAYOUT, null, new JDFAttributeMap("Side", ""), false, 0, false);
				if (vLO.isEmpty())
				{
					final JDFSurface surf = (JDFSurface) sheet.appendElement(ElementName.SURFACE);
					surf.setSide(EnumSide.Front);
					((JDFLayout) sheet).moveElementsTo((JDFLayout) surf);
				}
				else
				{
					for (int i = 0; i < vLO.size(); i++)
					{
						final JDFSurface surface = (JDFSurface) vLO.elementAt(i);
						surface.renameElement(ElementName.SURFACE, null);
						final EnumSide sid = surface.getSide();
						surface.cleanResourceAttributes();
						surface.setSide(sid);
					}
				}
			}
		}
		removeFromAttribute(AttributeName.PARTIDKEYS, AttributeName.SIGNATURENAME, null, JDFConstants.BLANK, -1);
		removeFromAttribute(AttributeName.PARTIDKEYS, AttributeName.SHEETNAME, null, JDFConstants.BLANK, -1);
		removeFromAttribute(AttributeName.PARTIDKEYS, AttributeName.SIDE, null, JDFConstants.BLANK, -1);
		return true;
	}

	// ///////////////////////////////////////////////////////////

	private void moveElementsTo(final JDFLayout target)
	{
		VElement vPO = getPlacedObjectVector();
		if (vPO != null)
		{
			for (int i = 0; i < vPO.size(); i++)
			{
				target.moveElement(vPO.elementAt(i), null);
			}
		}
		vPO = getChildElementVector_JDFElement(ElementName.LAYOUT, null, null, false, 0, false);
		if (vPO != null)
		{
			for (int i = 0; i < vPO.size(); i++)
			{
				target.moveElement(vPO.elementAt(i), null);
			}
		}

	}

	/**
	 * heuristics to check which version an element of a Layout is in: 1.3 or 1.2
	 * 
	 * Note that this routine is static since it must be used on all sheets, surfaces etc.
	 * 
	 * @param sheet the Sheet, Surface, Signature or Layout to check
	 * @return true if this is a new, i.e. partitioned Layout
	 * 
	 */
	public static boolean isNewLayout(final JDFResource sheet)
	{
		// not one of Layout, Signature, Sheet or Surface
		if (!(sheet instanceof JDFLayout))
		{
			return false;
		}

		// either Signature, Sheet or Surface --> old
		if (!sheet.getLocalName().equals(ElementName.LAYOUT))
		{
			return false;
		}

		// it's a layout the only allowed (old) element is a signature , if it
		// exists --> old
		if (sheet.getElement_KElement(ElementName.SIGNATURE, null, 0) != null)
		{
			return false;
		}
		// it is a layout and it has no subelements and it is partitioned -->
		// new
		final JDFResource resourceRoot = sheet.getResourceRoot();
		if (resourceRoot.hasAttribute(AttributeName.PARTIDKEYS))
		{
			return true;
		}
		// it is a non partitioned layout and it has palacedobjects --> new
		if (resourceRoot.hasChildElement(ElementName.CONTENTOBJECT, null) || resourceRoot.hasChildElement(ElementName.MARKOBJECT, null))
		{
			return true;
		}

		// now I'm ready to punt - no partition and no subelements --> assume
		// that version tags are correct
		final EnumVersion v = sheet.getVersion(true);

		// no version, we are 1.3 --> assume 1.3
		if (v == null)
		{
			return true;
		}

		return v.getValue() >= EnumVersion.Version_1_3.getValue();
	}

	/**
	 * appends a signature in both old and new Layouts if old: a <Signature> element if new: a SignatureName partition leaf
	 */
	@Override
	public JDFSignature appendSignature() throws JDFException
	{
		return appendLayoutElement(this, ElementName.SIGNATURE, AttributeName.SIGNATURENAME);
	}

	/**
	 * counts the number of signatures in both old and new Layouts if old: the number of <Signature> elements if new: the number of SignatureName partition
	 * leaves
	 * 
	 * @return the number of signatures
	 */
	public int numSignatures()
	{
		return numLayoutElements(this, ElementName.SIGNATURE, AttributeName.SIGNATURENAME);
	}

	/**
	 * gets or appends a signature in both old and new Layouts if old: a <Signature> element if new: a SignatureName partition leaf
	 * 
	 * @param iSkip the number of signatures to skip
	 */
	@Override
	public JDFSignature getCreateSignature(final int iSkip)
	{
		JDFSignature s = getSignature(iSkip);
		if (s == null)
		{
			s = appendSignature();
		}
		return s;
	}

	/**
	 * gets a signature in both old and new Layouts if old: a <Signature> element if new: a SignatureName partition leaf
	 * 
	 * @param iSkip the number of signatures to skip
	 */
	@Override
	public JDFSignature getSignature(final int iSkip)
	{
		return getLayoutElement(this, ElementName.SIGNATURE, AttributeName.SIGNATURENAME, iSkip);
	}

	/**
	 * gets a signature in both old and new Layouts if old: a <Signature> element if new: a SignatureName partition leaf
	 * @param signatureName the SignatureName partition key value(new) or Signature/@Name(old)
	 * 
	 * @return the signature
	 */
	public JDFSignature getSignature(final String signatureName)
	{
		return getLayoutElement(this, ElementName.SIGNATURE, AttributeName.SIGNATURENAME, signatureName);
	}

	/**
	 * gets a signature in both old and new Layouts if old: a <Signature>creates it if it does not exist element if new: a SignatureName partition leaf
	 * @param signatureName the SignatureName partition key value(new) or Signature/@Name(old)
	 * 
	 * @return the signature
	 * @throws JDFException
	 */
	public JDFSignature getCreateSignature(final String signatureName) throws JDFException
	{
		return getCreateLayoutElement(this, ElementName.SIGNATURE, AttributeName.SIGNATURENAME, signatureName);
	}

	/**
	 * get the vector of sheets in this signature
	 * 
	 * @return {@link VElement} the vector of signatures in this
	 */
	public VElement getSignatureVector()
	{
		return getLayoutElementVector(this, ElementName.SIGNATURE, AttributeName.SIGNATURENAME);
	}

} // class JDFLayout
// ==========================================================================
