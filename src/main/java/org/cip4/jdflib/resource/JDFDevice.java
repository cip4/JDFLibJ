/*
 *
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
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDevice;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.DOMException;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 *         way before June 3, 2009
 */
public class JDFDevice extends JDFAutoDevice
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFDevice
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 * 
	 */
	public JDFDevice(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDevice
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 * 
	 */
	public JDFDevice(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDevice
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFDevice(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoDevice#toString()
	 */
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
	public VElement getMatchingTypeNodeVector(final JDFNode testRoot)
	{
		final VElement vDeviceCap = getChildElementVector(ElementName.DEVICECAP, null, null, false, -1, false);
		if (vDeviceCap == null || vDeviceCap.isEmpty())
		{
			return null;
		}
		final VElement vRet = new VElement();
		for (int i = 0; i < vDeviceCap.size(); i++)
		{
			final VElement vMatch = ((JDFDeviceCap) vDeviceCap.elementAt(i)).getMatchingTypeNodeVector(testRoot);
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
	 * @param bLocal
	 * 
	 * @return VElement - the list of matching devicecap nodes, null if none found
	 * 
	 */
	public VElement getMatchingDeviceCapVector(final JDFNode testRoot, final boolean bLocal)
	{
		final VElement vDeviceCap = getChildElementVector(ElementName.DEVICECAP, null, null, false, -1, false);
		if (vDeviceCap == null || vDeviceCap.isEmpty())
		{
			return null;
		}
		final VElement vRet = new VElement();
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
	public boolean matchesType(final JDFNode testRoot, final boolean bLocal)
	{
		final VElement v = getMatchingTypeNodeVector(testRoot);
		if (v == null)
		{
			return false;
		}
		if (bLocal)
		{
			return v.contains(testRoot);
		}
		return true;
	}

	/**
	 * Gets of jdfRoot a vector of all executable nodes (jdf root or children nodes that this Device may execute)
	 * 
	 * @param docRoot the node we test
	 * @param testlists testlists that are specified for the State elements (FitsValue_Allowed or FitsValue_Present)<br>
	 *        Will be used in fitsValue method of the State class.
	 * @param validationLevel validation level
	 * @return VElement - vector of executable JDFNodes
	 */
	public VElement getExecutableJDF(final JDFNode docRoot, final EnumFitsValue testlists, final EnumValidationLevel validationLevel)
	{
		final VElement vDC = getChildElementVector(ElementName.DEVICECAP, null, null, true, -1, false);
		if (vDC == null || vDC.isEmpty())
		{
			return null;
		}

		final VElement vn = new VElement();
		for (int i = 0; i < vDC.size(); i++)
		{
			final JDFDeviceCap dc = (JDFDeviceCap) vDC.elementAt(i);
			final VElement executableJDF = dc.getExecutableJDF(docRoot, testlists, validationLevel);
			if (executableJDF != null)
			{
				vn.addAll(executableJDF);
			}
		}
		vn.unify();
		return vn.isEmpty() ? null : vn;
	}

	/**
	 * Composes a BugReport in XML form for the given JDFNode 'jdfRoot'. Gives a list of error messages for 'jdfRoot' and every child rejected Node.<br>
	 * Returns <code>null</code> if there are no errors.
	 * 
	 * @param jdfRoot the node to test
	 * @param testlists testlists that are specified for the State elements (FitsValue_Allowed or FitsValue_Present)<br>
	 *        Will be used in fitsValue method of the State class.
	 * @param level validation level
	 * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc is null there are no errors.
	 */
	public final XMLDoc getBadJDFInfo(final JDFNode jdfRoot, final EnumFitsValue testlists, final EnumValidationLevel level)
	{
		final VElement vDC = getChildElementVector(ElementName.DEVICECAP, null, null, true, -1, false);
		if (vDC == null || vDC.isEmpty())
		{
			return null;
		}

		final VElement vn = new VElement();
		for (int i = 0; i < vDC.size(); i++)
		{
			final JDFDeviceCap dc = (JDFDeviceCap) vDC.elementAt(i);
			final XMLDoc bugReport = dc.getBadJDFInfo(jdfRoot, testlists, level);
			if (bugReport == null)
			{
				return null;
			}
			vn.addAll(bugReport.getRoot().getChildElementVector(null, null, null, true, -1, false));
		}

		final int vnSize = vn.size();
		if (vnSize == 0)
		{
			return null;
		}

		final XMLDoc bugReport = new XMLDoc("BugReport", null);
		final KElement root = bugReport.getRoot();
		boolean bFit = false;
		for (int i = 0; i < vnSize; i++)
		{
			final KElement e = vn.elementAt(i);
			if (JDFConstants.TRUE.equals(e.getAttribute(JDFDeviceCap.FITS_TYPE)))
			{
				bFit = true;
			}
		}
		if (bFit)
		{
			for (int i = 0; i < vnSize; i++)
			{
				final KElement e = vn.elementAt(i);
				if (JDFConstants.FALSE.equals(e.getAttribute(JDFDeviceCap.FITS_TYPE)))
				{
					vn.set(i, null);
				}
			}
		}
		for (int i = 0; i < vnSize; i++)
		{
			if (vn.elementAt(i) != null)
			{
				root.moveElement(vn.item(i), null);
			}
		}
		return bugReport;
	}

	/**
	 * get the descriptivename - default to friendlyname if descriptivename is not set
	 * 
	 * @see org.cip4.jdflib.core.JDFElement#getDescriptiveName()
	 */
	@Override
	public String getDescriptiveName()
	{
		final String ret = StringUtil.getNonEmpty(super.getDescriptiveName());
		return ret != null ? ret : getFriendlyName();
	}

	/**
	 * 
	 * @return
	 */
	public String getRestApiBaseURL()
	{
		return getAttribute(XJDFConstants.RestApiBaseURL, null, JDFCoreConstants.EMPTYSTRING);
	}

	public void setRestApiBaseURL(final String value)
	{
		setAttribute(XJDFConstants.RestApiBaseURL, value, null);
	}

}
