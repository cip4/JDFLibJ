/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.util.xml;

import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.ifaces.IStreamWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * class that adds some trivial xsl transform routines for KElements
 * 
 * @author rainer prosi
 * @date Jan 22, 2013
 */
public class XSLTransformHelper implements IStreamWriter
{
	private final KElement elem;
	private final XMLDoc xsl;
	private final Log log;

	/**
	 * @param e
	 * @param xsl 
	 */
	public XSLTransformHelper(KElement e, XMLDoc xsl)
	{
		elem = e;
		this.xsl = xsl;
		log = LogFactory.getLog(getClass());
	}

	/**
	 * @param d - the document to transform - must not be null
	 * @param xsl 
	 */
	public XSLTransformHelper(XMLDoc d, XMLDoc xsl)
	{
		this(d.getRoot(), xsl);
	}

	/**
	 * 
	 * @return
	 */
	public XMLDoc getTransformElement()
	{
		if (xsl == null)
		{
			return null;
		}
		DOMResult transformedResult = new DOMResult();
		getTransformedResult(transformedResult);
		Node transformedDoc = transformedResult.getNode();
		return transformedDoc == null ? null : new XMLDoc((Document) transformedDoc);
	}

	private void getTransformedResult(Result transformedResult)
	{
		Source mySrc = new DOMSource(elem);
		Source xslSrc = new DOMSource(xsl.getMemberDocument());
		try
		{
			Transformer theTransformer = TransformerFactory.newInstance().newTransformer(xslSrc);
			theTransformer.transform(mySrc, transformedResult);
		}
		catch (TransformerException x)
		{
			log.error("error applying xsl transform ", x);
		}
	}

	/**
	* 
	* @param stream the stream to fill
	*  
	*/
	public void writeStream(OutputStream stream)
	{
		if (xsl != null && stream != null)
		{
			StreamResult transformedResult = new StreamResult(stream);
			getTransformedResult(transformedResult);
		}
	}
}
