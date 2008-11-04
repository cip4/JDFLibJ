/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of 
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
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFColorSpaceConversionOp.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process.prepress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoColorSpaceConversionOp;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.resource.process.JDFFileSpec;

public class JDFColorSpaceConversionOp extends JDFAutoColorSpaceConversionOp
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFColorSpaceConversionOp
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFColorSpaceConversionOp(CoreDocumentImpl myOwnerDocument,
			String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFColorSpaceConversionOp
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFColorSpaceConversionOp(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFColorSpaceConversionOp
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFColorSpaceConversionOp(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFColorSpaceConversionOp[  --> " + super.toString() + " ]";
	}

	/**
	 * Gets of 'this' an existing child FileSpec(AbstractProfile) element
	 * 
	 * @return JDFFileSpec the matching AbstractProfile element
	 */
	public JDFFileSpec getAbstractProfile()
	{
		return (JDFFileSpec) getChildWithAttribute(ElementName.FILESPEC,
				AttributeName.RESOURCEUSAGE, null, "AbstractProfile", 0, true);
	}

	/**
	 * Gets of 'this' child FileSpec(AbstractProfile) element, optionally
	 * creates it, if it doesn't exist.
	 * 
	 * @return JDFFileSpec the matching AbstractProfile element
	 */
	public JDFFileSpec getCreateAbstractProfile()
	{
		JDFFileSpec res = getAbstractProfile();
		if (res == null)
		{
			res = appendAbstractProfile();
		}
		return res;
	}

	/**
	 * Appends new FileSpec(AbstractProfile) element to the end of 'this'
	 * 
	 * @return JDFFileSpec newly created child AbstractProfile element
	 */
	public JDFFileSpec appendAbstractProfile()
	{
		JDFFileSpec res = appendFileSpec();
		res.setResourceUsage("AbstractProfile");

		return res;
	}

	/**
	 * Gets of 'this' an existing child FileSpec(SourceProfile) element
	 * 
	 * @return JDFFileSpec the matching SourceProfile element
	 */
	public JDFFileSpec getSourceProfile()
	{
		return (JDFFileSpec) getChildWithAttribute(ElementName.FILESPEC,
				AttributeName.RESOURCEUSAGE, null, "SourceProfile", 0, true);
	}

	/**
	 * Gets of 'this' child FileSpec(SourceProfile) element, optionally creates
	 * it, if it doesn't exist.
	 * 
	 * @return JDFFileSpec the matching SourceProfile element
	 */
	public JDFFileSpec getCreateSourceProfile()
	{
		JDFFileSpec res = getSourceProfile();
		if (res == null)
		{
			res = appendSourceProfile();
		}
		return res;
	}

	/**
	 * Appends new FileSpec(SourceProfile) element to the end of 'this'
	 * 
	 * @return JDFFileSpec newly created child SourceProfile element
	 */
	public JDFFileSpec appendSourceProfile()
	{
		JDFFileSpec res = appendFileSpec();
		res.setResourceUsage("SourceProfile");

		return res;
	}

}
