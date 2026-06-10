/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

package org.cip4.jdflib.resource.devicecapability;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.ifaces.IDeviceCapable;
import org.cip4.jdflib.util.JavaEnumUtil;
import org.w3c.dom.DOMException;

public abstract class JDFTerm extends JDFElement
{

	/**
	 *
	 */
	private static final long serialVersionUID = 6785589345368148259L;

	/**
	 * Constructor for JDFTerm
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFTerm(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFTerm
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFTerm(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFTerm
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFTerm(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
			throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Evaluates the boolean expression (child Term element): checks whether it fits <code>jdf</code>
	 *
	 * @param jdf        JDFNode to test to know if the Device can accept it
	 * @param reportRoot the report to generate; set to <code>null</code> if no report is requested
	 * @return boolean - true, if boolean expression (child Term element) evaluates to true
	 */
	public abstract boolean fitsJDF(KElement jdf, KElement reportRoot); // const

	// JDFNode

	/**
	 * checks the xpath whether this term applies
	 *
	 * @param jdf the KElement to check
	 * @return boolean
	 */
	public abstract boolean fitsContext(KElement jdf);

	/**
	 * Tests whether this Term is compatible with the attribute map <code>m</code> (and, or, xor, not, Evaluation, TestRef).<br>
	 * To determine the state of Term tests Evaluations thatdoes not consists of, this method checks if attribute map <code>m</code> has a key. specified by Evaluation/BasicPreflightTest/@Name
	 * If
	 * <code>m</code> has such key, it checks whether the value of <code>m#</code> fits the testlists specified for matching Evaluation (uses FitsValue(value))
	 *
	 * @param m key-value pair attribute map
	 * @return boolean - true, if boolean �not� expression evaluates to �true�
	 */
	public abstract boolean fitsMap(JDFAttributeMap m);

	public enum EnumTerm
	{
		and, or, not, xor, BooleanEvaluation, DateTimeEvaluation, DurationEvaluation, EnumerationEvaluation, IntegerEvaluation, IsPresentEvaluation, MatrixEvaluation, NameEvaluation, NumberEvaluation, PDDFPathEvaluation, RectangleEvaluation, ShapeEvaluation, StringEvaluation, XYPairEvaluation, TestRef;

		public static EnumTerm getEnum(final String enumName)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumTerm.class, enumName, null);
		}
	}

	/**
	 * get the parent devicecap or messageservice
	 *
	 * @return
	 */
	IDeviceCapable getDeviceCapable()
	{
		IDeviceCapable deviceCap = (IDeviceCapable) getDeepParent(ElementName.DEVICECAP, 0);
		if (deviceCap != null)
		{
			return deviceCap;
		}
		deviceCap = (IDeviceCapable) getDeepParent(ElementName.MESSAGESERVICE, 0);
		return deviceCap;
	}

}
