/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import java.io.IOException;
import java.io.OutputStream;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.extensions.xjdfwalker.IDRemover;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;

public class XJMFHelper extends MessagePoolHelper
{

	/**
	 *
	 * @param pool
	 */
	public XJMFHelper(KElement pool)
	{
		super(pool);
	}

	/**
	 *
	 * @param pool
	 */
	public XJMFHelper()
	{
		super(null);
		newXJMF();
	}

	/**
	 *
	 */
	void newXJMF()
	{
		JDFDoc doc = new JDFDoc(XJDFConstants.XJMF, EnumVersion.Version_2_0);
		doc.setInitOnCreate(false);
		theElement = doc.getRoot();
		cleanUp();
	}

	/**
	 *
	 * @param family
	 * @param typ
	 * @return
	 */
	public MessageHelper appendMessage(EnumFamily family, EnumType typ)
	{
		if (family == null || typ == null)
			return null;

		return appendMessage(getMessageName(family, typ.getName()));
	}

	/**
	 *
	 * @param family
	 * @param typ
	 * @return
	 */
	public MessageHelper appendMessage(EnumFamily family, String typ)
	{
		if (family == null || typ == null)
			return null;

		return appendMessage(getMessageName(family, typ));
	}

	/**
	 *
	 * @param family
	 * @param typ
	 * @return
	 */
	private String getMessageName(EnumFamily family, String typ)
	{
		return family.getName() + typ;
	}

	/**
	 * @param file
	 * @return
	 */
	public boolean writeToFile(String file)
	{
		cleanUp();
		boolean b = getRoot().getOwnerDocument_KElement().write2File(file, 2, false);
		return b;
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#cleanUp()
	 */
	@Override
	public void cleanUp()
	{
		super.cleanUp();
		MessageHelper.ensureHeader(theElement);
		new IDRemover().removeIDs(theElement);
	}

	/**
	 * @param os
	 * @throws IOException
	 */
	public void writeToStream(OutputStream os) throws IOException
	{
		cleanUp();
		getRoot().getOwnerDocument_KElement().write2Stream(os, 2, false);
	}

	/**
	 * factory to create a helper from an element
	 *
	 * @param root the element to parse if not an XJDF - search in ancestors of element
	 * @return the helper
	 */
	public static XJMFHelper getHelper(KElement root)
	{
		if (root == null)
			return null;
		if (!root.getLocalName().equals(XJDFConstants.XJMF))
			root = root.getDeepParent(XJDFConstants.XJMF, 0);
		return (root != null) ? new XJMFHelper(root) : null;
	}

	/**
	 * factory to create a helper from a doc
	 *
	 * @param doc the xmldoc to parse
	 * @return the helper
	 */
	public static XJMFHelper getHelper(XMLDoc doc)
	{
		if (doc == null)
			return null;
		KElement root = doc.getRoot();
		return getHelper(root);
	}

}
