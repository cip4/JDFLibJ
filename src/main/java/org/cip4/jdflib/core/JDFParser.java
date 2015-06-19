/*
 *
 * The CIP4 Software License, Version 1.0
 *
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
/*
 * Created on July 3, 2003
 */
package org.cip4.jdflib.core;

import java.io.File;
import java.io.InputStream;

import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XNIException;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * @author matternk
 */
public class JDFParser extends XMLParser
{

	/**
	 * set bKElementOnly=true if you want the output ojects all to be instatnces of KElement rather than instantiated JDF instances
	 */
	public boolean bKElementOnly = false;
	/**
	 * set ignoreNSDefault=true if you do not want any heuristics to be performed regarding DOM level 1 / 2 namespace associations
	 */
	public boolean ignoreNSDefault = false;

	/**
	 * default constructor
	 */
	public JDFParser()
	{
		super();
	}

	/**
	 * @deprecated - use default constructor
	 * @param strDocType
	 */
	@Deprecated
	public JDFParser(final String strDocType)
	{
		this();
	}

	/**
	 * @param parser
	 */
	public JDFParser(final JDFParser parser)
	{
		this();
		bKElementOnly = parser.bKElementOnly;
		m_eraseEmpty = parser.m_eraseEmpty;
		initParser(m_SchemaLocation, (XMLErrorHandler) parser.getErrorHandler());
	}

	/**
	 * (non-Javadoc) reset all internal variables to a reasonable default
	 * 
	 * @see org.apache.xerces.parsers.AbstractDOMParser#reset()
	 */
	@Override
	public void cleanup()
	{
		super.cleanup();
		bKElementOnly = false;
	}

	/**
	 * parseFile - parse a file specified by strFile
	 * 
	 * @param strFile link to the document to parse, may be either a file path or a url
	 * @return JDFDoc or null if File not found
	 */
	@Override
	public JDFDoc parseFile(final String strFile)
	{
		return (JDFDoc) super.parseFile(strFile);
	}

	/**
	 * @param file
	 * @return
	 */
	@Override
	public JDFDoc parseFile(final File file)
	{
		return (JDFDoc) super.parseFile(file);
	}

	/**
	 * parseFile - parse a file specified by strFile
	 * 
	 * @param strFile link to the document to parse
	 * @param schemaLocation link to the schema to use, null if no validation required
	 * @return JDFDoc or null if File not found default: parseFile(strFile,null)
	 * @deprecated set the parser members instead
	 */
	@Deprecated
	public JDFDoc parseFile(final String strFile, final String schemaLocation)
	{
		m_SchemaLocation = schemaLocation;
		return parseFile(strFile);
	}

	/**
	 * parseString - parse a string specified by stringInput
	 * 
	 * @param stringInput string to parse
	 * @return JDFDoc or null if parse failed default: parseString(stringInput)
	 */
	@Override
	public JDFDoc parseString(final String stringInput)
	{
		return (JDFDoc) super.parseString(stringInput);
	}

	/**
	 * parseStream - parse a stream specified by inStream
	 * 
	 * @param inStream stream to parse
	 * @return JDFDoc or null if parse failed default: parseStream(inStream)
	 */
	@Override
	public JDFDoc parseStream(final InputStream inStream)
	{
		return (JDFDoc) super.parseStream(inStream);
	}

	/**
	 * This is the sophisticated parse function, where validation, error handlers et al. can be set
	 * 
	 * @param inSource
	 * @param schemaLocation schema location, null if no validation required
	 * @param documentClassName
	 * @param errorHandler
	 * @param bEraseEmpty if true empty nodes are erased after parsing
	 * @param bDoNamespaces if false a second parse is done, where namespaces are ignored
	 * 
	 * @return JDFDoc
	 * 
	 * default: parseInputSource(inSource, null, DocumentJDFImpl.class.getName(), null, true, true);
	 * @deprecated set the parser members instead
	 */
	@Deprecated
	public JDFDoc parseInputSource(final InputSource inSource, final String schemaLocation, final String documentClassName, final ErrorHandler errorHandler, final boolean bEraseEmpty, final boolean bDoNamespaces)
	{
		JDFDoc doc = null;
		if (errorHandler instanceof XMLErrorHandler)
		{
			initParser(schemaLocation, (XMLErrorHandler) errorHandler);
		}
		else
		{
			initParser(schemaLocation, null);
		}

		doc = (JDFDoc) runParser(inSource, bEraseEmpty);
		if (doc == null)
		{ // this is the error case:
			if (!bDoNamespaces)
			{
				// try again, ignoring name spaces
				setIgnoreNamespace(false);
				doc = (JDFDoc) runParser(inSource, bEraseEmpty);
			}
		}

		return doc;
	}

	/**
	 * 
	 */
	@Override
	public String getDocumentClass()
	{
		return DocumentJDFImpl.class.getName();
	}

	/**
	 * @see org.apache.xerces.parsers.AbstractDOMParser#createElementNode(org.apache.xerces.xni.QName)
	 */
	@Override
	public Element createElementNode(final QName element)
	{
		if (fCurrentNode.getLocalName() != null)
		{
			((DocumentJDFImpl) (this.fDocument)).setParentNode(fCurrentNode);
		}

		final Element e = super.createElementNode(element);
		((DocumentJDFImpl) (this.fDocument)).setParentNode(null);
		return e;

	}

	@Override
	protected XMLDoc getXMLDoc()
	{
		XMLDoc doc = new JDFDoc();
		return doc;
	}

	private void setIgnoreNamespace(final boolean toSet)
	{
		try
		{
			setFeature("http://xml.org/sax/features/namespaces", toSet);
			setFeature("http://xml.org/sax/features/validation", toSet);
		}
		catch (final SAXNotRecognizedException e)
		{
			m_lastExcept = e;
		}
		catch (final SAXNotSupportedException e)
		{
			m_lastExcept = e;
		}
	}

	/**
	 * @param _schemaLocation the schema location
	 */
	public void setJDFSchemaLocation(final File _schemaLocation)
	{
		final String url = UrlUtil.fileToUrl(_schemaLocation, false);
		setJDFSchemaLocation(url);
	}

	/**
	 * @param schemaLocation the schema location
	 */
	public void setJDFSchemaLocation(final String schemaLocation)
	{
		if (StringUtil.getNonEmpty(schemaLocation) != null)
		{
			setSchemaLocation(JDFElement.getSchemaURL(), schemaLocation);
		}
	}

	/**
	 * @see org.apache.xerces.parsers.AbstractDOMParser#startDocument(org.apache.xerces.xni.XMLLocator, java.lang.String,
	 * org.apache.xerces.xni.NamespaceContext, org.apache.xerces.xni.Augmentations)
	 */
	@Override
	public void startDocument(final XMLLocator locator, final String encoding, final NamespaceContext namespaceContext, final Augmentations augs) throws XNIException
	{
		super.startDocument(locator, encoding, namespaceContext, augs);
		final DocumentJDFImpl memberDocument = (DocumentJDFImpl) getDocument();

		memberDocument.bKElementOnly = bKElementOnly;
		memberDocument.bInitOnCreate = false;
	}

	@Override
	protected void setDocumentProperties(final KElement root, final DocumentXMLImpl memberDocument, final String namespaceURI)
	{
		boolean isXJDF = XJDFHelper.XJDF.equals(root.getLocalName()) || JDFElement.getSchemaURL(2, 0).equals(root.getNamespaceURI());
		boolean bJDFRoot = (root instanceof JDFNode) || (root instanceof JDFJMF);
		if (bJDFRoot && !JDFConstants.JDFNAMESPACE.equals(namespaceURI) && !isXJDF)
		{
			root.setAttribute(JDFConstants.XMLNS, JDFConstants.JDFNAMESPACE);
		}
		if (!bJDFRoot && (namespaceURI == null || !namespaceURI.toLowerCase().contains(JDFConstants.CIP4ORG)))
		{
			((DocumentJDFImpl) memberDocument).bKElementOnly = true;
		}
	}

	@Override
	final protected XMLDoc runParser(InputSource inSource, boolean bEraseEmpty)
	{
		XMLDoc doc = super.runParser(inSource, bEraseEmpty);
		if (doc != null)
			((JDFDoc) doc).setInitOnCreate(true);
		return doc;
	}

}
