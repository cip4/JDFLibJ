/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFBinderySignature.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoBinderySignature;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFXYPair;

/**
 *
 *
 * @author rainer prosi
 * @date Oct 22, 2012
 */
public class JDFBinderySignature extends JDFAutoBinderySignature
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFBinderySignature
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFBinderySignature(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFBinderySignature
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFBinderySignature(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFBinderySignature
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFBinderySignature(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
		return "JDFBinderySignature[  --> " + super.toString() + " ]";
	}

	/**
	 * convenience method
	 *
	 * @param i
	 * @param j
	 */
	public void setNumberUp(final int i, final int j)
	{
		setNumberUp(new JDFXYPair(i, j));
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoBinderySignature#setFoldCatalog(java.lang.String)
	 */
	@Override
	public void setFoldCatalog(final String value)
	{
		if (!hasNonEmpty_KElement(AttributeName.BINDERYSIGNATURETYPE))
		{
			setBinderySignatureType(EnumBinderySignatureType.Fold);
		}
		super.setFoldCatalog(value);
	}

	/**
	 * 
	 * @param pages
	 * @param index
	 */
	public void setFoldCatalog(final int pages, final int index)
	{
		if (!hasNonEmpty_KElement(AttributeName.BINDERYSIGNATURETYPE))
		{
			setBinderySignatureType(EnumBinderySignatureType.Fold);
		}
		final String value = getCatalog(pages, index);
		setFoldCatalog(value);
	}

	/**
	 * 
	 * @param pages
	 * @param index
	 * @return the foldcatalog entry
	 * @throws IllegalArgumentException if pages is noegative or an odd number or index is 0 or negative
	 */
	public static String getCatalog(final int pages, final int index)
	{
		if (pages < 1 || pages % 2 != 0)
			throw new IllegalArgumentException("invalid pages for fold catalog: " + pages);
		if (index < 1)
			throw new IllegalArgumentException("invalid index for fold catalog: " + index);

		return "F" + pages + "-" + index;
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoBinderySignature#appendDieLayout()
	 */
	@Override
	public JDFDieLayout appendDieLayout() throws JDFException
	{
		if (!hasNonEmpty_KElement(AttributeName.BINDERYSIGNATURETYPE))
		{
			setBinderySignatureType(EnumBinderySignatureType.Die);
		}
		return super.appendDieLayout();
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoBinderySignature#refDieLayout(org.cip4.jdflib.resource.process.JDFDieLayout)
	 */
	@Override
	public void refDieLayout(final JDFDieLayout refTarget)
	{
		if (!hasNonEmpty_KElement(AttributeName.BINDERYSIGNATURETYPE))
		{
			setBinderySignatureType(EnumBinderySignatureType.Die);
		}
		super.refDieLayout(refTarget);
	}

}
