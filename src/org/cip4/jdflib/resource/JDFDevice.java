/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
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
 ==========================================================================
 class JDFDevice
 ==========================================================================
 @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
 ALL RIGHTS RESERVED
 @Author: sabjon@topmail.de   using a code generator
 Warning! very preliminary test version. Interface subject to change without prior notice!
 Revision history:    ...
 **/
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDevice;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.w3c.dom.DOMException;

public class JDFDevice extends JDFAutoDevice
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFDevice
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFDevice(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDevice
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFDevice(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDevice
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFDevice(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	@Override
	public String toString()
	{
		return "JDFDevice[  --> " + super.toString() + " ]";
	}

	/**
	 * test whether a given node has the corect Types and Type Attribute
	 * 
	 * @param testRoot the JDF or JMF to test
	 * 
	 * @return VElement - the list of matching JDF nodes, null if none found
	 * 
	 */
	public VElement getMatchingTypeNodeVector(JDFNode testRoot)
	{
		VElement vDeviceCap = getChildElementVector(ElementName.DEVICECAP, null, null, false, -1, false);
		if (vDeviceCap == null || vDeviceCap.isEmpty())
			return null;
		VElement vRet = new VElement();
		for (int i = 0; i < vDeviceCap.size(); i++)
		{
			VElement vMatch = ((JDFDeviceCap) vDeviceCap.elementAt(i)).getMatchingTypeNodeVector(testRoot);
			if (vMatch != null)
			{
				vRet.addAll(vMatch);
			}
		}
		vRet.unify();
		return vRet.isEmpty() ? null : vRet;
	}

	/**
	 * return all deviceCap elements that correspond to testRoot
	 * 
	 * @param testRoot the JDF or JMF to test
	 * 
	 * @return VElement - the list of matching devicecap nodes, null if none found
	 * 
	 */
	public VElement getMatchingDeviceCapVector(JDFNode testRoot, boolean bLocal)
	{
		VElement vDeviceCap = getChildElementVector(ElementName.DEVICECAP, null, null, false, -1, false);
		if (vDeviceCap == null || vDeviceCap.isEmpty())
			return null;
		VElement vRet = new VElement();
		for (int i = 0; i < vDeviceCap.size(); i++)
		{
			final JDFDeviceCap dc = (JDFDeviceCap) vDeviceCap.elementAt(i);
			if (dc.matchesType(testRoot, bLocal))
			{
				vRet.add(dc);
			}
		}
		return vRet.isEmpty() ? null : vRet;
	}

	/**
	 * test whether a given node has the corect Types and Type Attribute
	 * 
	 * @param testRoot the JDF or JMF to test
	 * @param bLocal if true, only check the root of this, else check children as well
	 * 
	 * @return boolean - true if this DeviceCaps TypeExpression fits testRoot/@Type and testRoot/@Types
	 * 
	 */
	public boolean matchesType(JDFNode testRoot, boolean bLocal)
	{
		VElement v = getMatchingTypeNodeVector(testRoot);
		if (v == null)
			return false;
		if (bLocal)
			return v.contains(testRoot);
		return true;
	}

	/**
	 * Gets of jdfRoot a vector of all executable nodes (jdf root or children nodes that this Device may execute)
	 * 
	 * @param jdfRoot the node we test
	 * @param testlists testlists that are specified for the State elements (FitsValue_Allowed or FitsValue_Present)<br>
	 *            Will be used in fitsValue method of the State class.
	 * @param level validation level
	 * @return VElement - vector of executable JDFNodes
	 */
	public VElement getExecutableJDF(JDFNode docRoot, EnumFitsValue testlists, EnumValidationLevel validationLevel)
	{
		VElement vDC = getChildElementVector(ElementName.DEVICECAP, null, null, true, -1, false);
		if (vDC == null || vDC.isEmpty())
			return null;

		VElement vn = new VElement();
		for (int i = 0; i < vDC.size(); i++)
		{
			JDFDeviceCap dc = (JDFDeviceCap) vDC.elementAt(i);
			final VElement executableJDF = dc.getExecutableJDF(docRoot, testlists, validationLevel);
			if (executableJDF != null)
				vn.addAll(executableJDF);
		}
		vn.unify();
		return vn.isEmpty() ? null : vn;
	}

	/**
	 * Composes a BugReport in XML form for the given JDFNode 'jdfRoot'. Gives a list of error messages for 'jdfRoot'
	 * and every child rejected Node.<br>
	 * Returns <code>null</code> if there are no errors.
	 * 
	 * @param jdfRoot the node to test
	 * @param testlists testlists that are specified for the State elements (FitsValue_Allowed or FitsValue_Present)<br>
	 *            Will be used in fitsValue method of the State class.
	 * @param level validation level
	 * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc is null there are no errors.
	 */
	public final XMLDoc getBadJDFInfo(final JDFNode jdfRoot, final EnumFitsValue testlists, final EnumValidationLevel level)
	{
		VElement vDC = getChildElementVector(ElementName.DEVICECAP, null, null, true, -1, false);
		if (vDC == null || vDC.isEmpty())
			return null;

		VElement vn = new VElement();
		for (int i = 0; i < vDC.size(); i++)
		{
			JDFDeviceCap dc = (JDFDeviceCap) vDC.elementAt(i);
			XMLDoc bugReport = dc.getBadJDFInfo(jdfRoot, testlists, level);
			if (bugReport == null)
				return null;
			vn.addAll(bugReport.getRoot().getChildElementVector(null, null, null, true, -1, false));
		}

		final int vnSize = vn.size();
		if (vnSize == 0)
			return null;

		XMLDoc bugReport = new XMLDoc("BugReport", null);
		KElement root = bugReport.getRoot();
		boolean bFit = false;
		for (int i = 0; i < vnSize; i++)
		{
			KElement e = vn.elementAt(i);
			if (JDFConstants.TRUE.equals(e.getAttribute(JDFDeviceCap.FITS_TYPE)))
				bFit = true;
		}
		if (bFit)
		{
			for (int i = 0; i < vnSize; i++)
			{
				KElement e = vn.elementAt(i);
				if (JDFConstants.FALSE.equals(e.getAttribute(JDFDeviceCap.FITS_TYPE)))
					vn.set(i, null);
			}
		}
		for (int i = 0; i < vnSize; i++)
		{
			if (vn.elementAt(i) != null)
				root.moveElement(vn.item(i), null);
		}
		return bugReport;
	}
}
