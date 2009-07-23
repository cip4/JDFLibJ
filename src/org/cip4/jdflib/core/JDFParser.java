/*
 *
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
/*
 * Created on July 3, 2003
 */
package org.cip4.jdflib.core;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XNIException;
import org.cip4.jdflib.util.SkipInputStream;
import org.cip4.jdflib.util.UrlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * @author matternk
 */
public class JDFParser extends DOMParser
{
	/**
	 * simple search stream that will find a valid xml wherever it starts
	 * 
	 * @author prosirai
	 * 
	 */
	private class XMLReaderStream extends SkipInputStream
	{
		protected boolean allowClose = false;

		/**
		 * @param searchXML
		 * @param stream
		 */
		public XMLReaderStream(final boolean searchXML, final InputStream stream)
		{
			super(searchXML ? "<?xml" : null, stream, searchXML, 10000);
		}

		/**
		 * the parser closes on error - something we do not want especially for the underlying stream
		 * 
		 */
		@Override
		public void close()
		{
			try
			{
				if (allowClose)
				{
					super.close();
				}
			}
			catch (final Exception x)
			{
				// nop
			}
		}

	}

	/**
	 * 
	 */
	public XMLErrorHandler m_ErrorHandler = null;
	/**
	 * 
	 */
	public String m_SchemaLocation = null;
	/**
	 * 
	 */
	public String m_DocumentClass = DocumentJDFImpl.class.getName();
	/**
	 * 
	 */
	public Exception m_lastExcept = null;
	/**
	 * 
	 */
	public static boolean m_searchStream = false;

	/**
	 * set bKElementOnly=true if you want the output ojects all to be instatnces of KElement rather than instantiated JDF instances
	 */
	public boolean bKElementOnly = false;
	/**
	 * set ignoreNSDefault=true if you do not want any heuristics to be performed regarding DOM level 1 / 2 namespace associations
	 */
	public boolean ignoreNSDefault = false;

	/**
	 * if true, empty pools and whitespace are removed when parsing
	 */
	public boolean m_eraseEmpty = true;

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
		initParser(m_SchemaLocation, m_DocumentClass, (XMLErrorHandler) parser.getErrorHandler());
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

	/**
	 * parseFile - parse a file specified by strFile
	 * 
	 * @param strFile link to the document to parse, may be either a file path or a url
	 * @return JDFDoc or null if File not found
	 */
	public JDFDoc parseFile(final String strFile)
	{
		final File file = UrlUtil.urlToFile(strFile);
		return parseFile(file);
	}

	/**
	 * @param file
	 * @return
	 */
	public JDFDoc parseFile(final File file)
	{
		if (file == null)
		{
			return null;
		}

		JDFDoc doc = null;
		if (file.canRead())
		{
			try
			{
				doc = parseStream(new FileInputStream(file));
				if (doc != null)
				{
					doc.setOriginalFileName(file.getAbsolutePath());
				}
				return doc;

			}
			catch (final FileNotFoundException e)
			{
				return null;
			}
		}
		return doc;
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
	public JDFDoc parseString(final String stringInput)
	{
		if (stringInput == null)
		{
			return null;
		}
		ByteArrayInputStream is;
		try
		{
			is = new ByteArrayInputStream(stringInput.getBytes("UTF-8"));
		}
		catch (final UnsupportedEncodingException x)
		{
			is = new ByteArrayInputStream(stringInput.getBytes());
		}
		return parseStream(is);
	}

	/**
	 * parseStream - parse a stream specified by inStream
	 * 
	 * @param inStream stream to parse
	 * @return JDFDoc or null if parse failed default: parseStream(inStream)
	 */
	public JDFDoc parseStream(final InputStream inStream)
	{
		if (inStream == null)
		{
			return null;
		}
		final XMLReaderStream bis = new XMLReaderStream(false, inStream);
		bis.mark(0);

		InputSource inSource = new InputSource(bis);
		JDFDoc d = parseInputSource(inSource);

		if (d == null && m_searchStream)
		{
			try
			{
				bis.reset();
			}
			catch (final IOException x)
			{
				bis.allowClose = true;
				bis.close();
				return null;
			}

			inSource = new InputSource(new XMLReaderStream(true, bis));
			d = parseInputSource(inSource);
		}
		bis.allowClose = true;
		bis.close();
		return d;
	}

	/**
	 * parse an input source
	 * 
	 * @param inSource the InputSource to parse
	 */
	@Override
	public void parse(final InputSource inSource)
	{
		parseInputSource(inSource);
	}

	/**
	 * parse an input source
	 * 
	 * @param inSource the InputSource to parse
	 * @return JDFDoc the newly parsed doc
	 */
	public JDFDoc parseInputSource(final InputSource inSource)
	{
		JDFDoc jdfDoc = null;
		if (inSource != null)
		{
			initParser(m_SchemaLocation, m_DocumentClass, m_ErrorHandler);
			jdfDoc = runParser(inSource, m_eraseEmpty);
		}

		return jdfDoc;
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
	public JDFDoc parseInputSource(final InputSource inSource, final String schemaLocation, final String documentClassName, final ErrorHandler errorHandler, final boolean bEraseEmpty,
			final boolean bDoNamespaces)
	{
		JDFDoc doc = null;
		if (errorHandler instanceof XMLErrorHandler)
		{
			initParser(schemaLocation, documentClassName, (XMLErrorHandler) errorHandler);
		}
		else
		{
			initParser(schemaLocation, documentClassName, null);
		}

		doc = runParser(inSource, bEraseEmpty);
		if (doc == null)
		{ // this is the error case:
			if (!bDoNamespaces)
			{
				// try again, ignoring name spaces
				setIgnoreNamespace(false);
				doc = runParser(inSource, bEraseEmpty);
			}
		}

		return doc;
	}

	/**
	 * @param schemaLocation
	 * @param documentClassName
	 * @param ErrorHandler default: initParser(null, DocumentJDFImpl.class.getName(), null);
	 */
	private void initParser(final String schemaLocation, final String documentClassName, final XMLErrorHandler errorHandler)
	{
		m_SchemaLocation = schemaLocation;
		m_ErrorHandler = errorHandler;
		m_DocumentClass = documentClassName;

		try
		{
			if (m_SchemaLocation == null || m_SchemaLocation.equals(JDFConstants.EMPTYSTRING))
			{
				this.setFeature("http://xml.org/sax/features/validation", false);
				this.setFeature("http://apache.org/xml/features/validation/schema", false);
			}
			else
			{
				if (!m_SchemaLocation.startsWith(JDFElement.getSchemaURL()))
				{
					m_SchemaLocation = JDFElement.getSchemaURL() + " " + m_SchemaLocation;
				}
				this.setFeature("http://xml.org/sax/features/validation", true);
				this.setFeature("http://apache.org/xml/features/validation/schema", true);
				// this.setFeature(
				// "http://apache.org/xml/features/validation/schema/element-default"
				// , false);
				// this.setFeature(
				// "http://apache.org/xml/features/validation/schema/normalized-value"
				// , false);
				this.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", m_SchemaLocation);
			}

			// use our own JDF document for creating JDF elements
			this.setProperty("http://apache.org/xml/properties/dom/document-class-name", documentClassName);

			this.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
			this.setFeature("http://xml.org/sax/features/namespaces", true);

			this.setErrorHandler(errorHandler);
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
	 * @see org.apache.xerces.parsers.DOMParser#setErrorHandler(org.xml.sax.ErrorHandler)
	 */
	@Override
	public void setErrorHandler(final ErrorHandler handler)
	{
		m_ErrorHandler = handler != null && (handler instanceof XMLErrorHandler) ? (XMLErrorHandler) handler : new XMLErrorHandler();
		super.setErrorHandler(m_ErrorHandler);
	}

	/**
	 * @param parser
	 * @param inSource
	 * @param bEraseEmpty
	 * @return
	 */
	private JDFDoc runParser(final InputSource inSource, final boolean bEraseEmpty)
	{
		JDFDoc doc = new JDFDoc();
		try
		{
			super.parse(inSource);
			doc.setMemberDoc((DocumentJDFImpl) getDocument());
			if (bEraseEmpty)
			{
				doc.getRoot().eraseEmptyNodes(true); // cleanup the XML
			}
		}
		catch (final Exception e)
		{
			m_lastExcept = e;
			doc = null;
		}
		catch (final StackOverflowError e)
		{
			m_lastExcept = null;
			doc = null;
		}

		if (doc != null && m_ErrorHandler != null)
		{
			doc.setValidationResult(m_ErrorHandler.getXMLOutput());
			m_ErrorHandler.cleanXML(m_SchemaLocation);
		}

		if (doc != null)
		{
			final KElement root = doc.getRoot();
			final DocumentJDFImpl memberDocument = doc.getMemberDocument();
			final String namespaceURI = root.getNamespaceURI();
			if (namespaceURI == null || !namespaceURI.toLowerCase().contains(JDFConstants.CIP4ORG))
			{
				memberDocument.bKElementOnly = true;
				memberDocument.setIgnoreNSDefault(true);
			}
			else
			{
				memberDocument.setIgnoreNSDefault(ignoreNSDefault);
			}
		}
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
		if (_schemaLocation != null && _schemaLocation.length() != 0)
		{
			final String fileToUrl = UrlUtil.fileToUrl(_schemaLocation, false);
			m_SchemaLocation = "http://www.CIP4.org/JDFSchema_1_1 " + fileToUrl;
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

		final Document document = getDocument();

		if (document instanceof DocumentJDFImpl)
		{
			final DocumentJDFImpl memberDocument = (DocumentJDFImpl) document;

			memberDocument.bKElementOnly = bKElementOnly;
			memberDocument.setIgnoreNSDefault(ignoreNSDefault);
		}
	}

	/**
	 * (non-Javadoc) reset all internal variables to a reasonable default
	 * 
	 * @see org.apache.xerces.parsers.AbstractDOMParser#reset()
	 */
	public void cleanup()
	{
		bKElementOnly = false;
		m_lastExcept = null;
		ignoreNSDefault = false;
		m_eraseEmpty = true;
		m_ErrorHandler = null;
		m_SchemaLocation = null;
		m_DocumentClass = DocumentJDFImpl.class.getName();
	}

}
