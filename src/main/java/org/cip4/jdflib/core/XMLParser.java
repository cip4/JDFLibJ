/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of
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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XNIException;
import org.cip4.jdflib.util.ByteArrayIOStream.ByteArrayIOInputStream;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.SkipInputStream;
import org.cip4.jdflib.util.StreamUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * @author matternk
 */
public class XMLParser extends DOMParser
{
	/**
	 * simple search stream that will find a valid xml wherever it starts
	 *
	 * @author prosirai
	 *
	 */
	private class XMLReaderStream extends SkipInputStream
	{
		/**
		 * @param searchXML
		 * @param stream
		 */
		public XMLReaderStream(final boolean searchXML, final InputStream stream)
		{
			super(searchXML ? "<?xml" : null, stream, searchXML, 10000);
		}
	}

	/**
	 *
	 */
	public XMLErrorHandler m_ErrorHandler = null;
	/**
	 *
	 */
	protected String m_SchemaLocation = null;
	String inputID;

	/**
	 * @return the class name
	 *
	 */
	public String getDocumentClass()
	{
		return DocumentXMLImpl.class.getName();
	}

	/**
	 *
	 */
	public Exception m_lastExcept = null;
	/**
	 *
	 */
	public static boolean m_searchStream = false;

	/**
	 * set ignoreNSDefault=true if you do not want any heuristics to be performed regarding DOM level 1 / 2 namespace associations
	 */
	public boolean ignoreNSDefault = false;

	/**
	 * if true, empty pools and whitespace are removed when parsing
	 */
	public boolean m_eraseEmpty = true;
	protected final Log log;

	/**
	 * default constructor
	 */
	public XMLParser()
	{
		super();
		log = LogFactory.getLog(getClass());
		inputID = null;
	}

	/**
	 * @deprecated - use default constructor
	 * @param strDocType
	 */
	@Deprecated
	public XMLParser(final String strDocType)
	{
		this();
	}

	/**
	 * @param parser
	 */
	public XMLParser(final XMLParser parser)
	{
		this();
		m_eraseEmpty = parser.m_eraseEmpty;
		inputID = parser.inputID;
		initParser(m_SchemaLocation, (XMLErrorHandler) parser.getErrorHandler());
	}

	/**
	 * parseFile - parse a file specified by strFile
	 *
	 * @param strFile link to the document to parse, may be either a file path or a url
	 * @return JDFDoc or null if File not found
	 */
	public XMLDoc parseFile(final String strFile)
	{
		final File file = UrlUtil.urlToFile(strFile);
		return parseFile(file);
	}

	/**
	 * @param file
	 * @return
	 */
	public XMLDoc parseFile(final File file)
	{
		if (file == null)
		{
			return null;
		}
		inputID = file.getAbsolutePath();

		if (file.canRead())
		{
			try
			{
				final BufferedInputStream is = FileUtil.getBufferedInputStream(file);
				if (is != null)
				{
					final XMLDoc doc = parseStream(is);
					if (doc != null)
					{
						doc.setOriginalFileName(file.getAbsolutePath());
					}
					StreamUtil.close(is);
					return doc;
				}
			}
			catch (final Exception e)
			{
				if (XMLErrorHandler.isWantLog())
				{
					log.error("cannot find file to parse:", e);
				}
			}
		}
		return null;
	}

	/**
	 * parseString - parse a string specified by stringInput
	 *
	 * @param stringInput string to parse
	 * @return JDFDoc or null if parse failed default: parseString(stringInput)
	 */
	public XMLDoc parseString(final String stringInput)
	{
		if (stringInput == null)
		{
			log.error("cannot parse null string");
			return null;
		}
		if (inputID == null)
			inputID = "String";

		ByteArrayInputStream is;
		try
		{
			is = new ByteArrayInputStream(stringInput.getBytes(StringUtil.UTF8));
		}
		catch (final UnsupportedEncodingException x)
		{
			log.warn("bad encoding ", x);
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
	public XMLDoc parseStream(final InputStream inStream)
	{
		if (inStream == null)
		{
			return null;
		}
		if (inputID == null)
			inputID = "Stream";
		XMLDoc d = null;
		if (m_searchStream)
		{
			final XMLReaderStream bis = new XMLReaderStream(false, inStream);
			bis.mark(0);

			InputSource inSource = new InputSource(bis);
			d = parseInputSource(inSource);

			if (d == null && m_searchStream)
			{
				bis.reset();

				inSource = new InputSource(new XMLReaderStream(true, bis));
				d = parseInputSource(inSource);
			}
		}
		else
		{
			final InputStream bis = (inStream instanceof BufferedInputStream) || (inStream instanceof ByteArrayIOInputStream) ? inStream : new BufferedInputStream(inStream);
			final InputSource inSource = new InputSource(bis);
			d = parseInputSource(inSource);
		}
		try
		{
			inStream.close();
		}
		catch (final IOException x)
		{
			//NOP
		}
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
	public XMLDoc parseInputSource(final InputSource inSource)
	{
		XMLDoc jdfDoc = null;
		if (inSource != null)
		{
			if (StringUtil.getNonEmpty(inSource.getSystemId()) == null)
			{
				inSource.setSystemId(inputID);
			}
			initParser(m_SchemaLocation, m_ErrorHandler);
			m_ErrorHandler.setInputSource(inSource);
			jdfDoc = runParser(inSource, m_eraseEmpty);
		}

		return jdfDoc;
	}

	/**
	 * @param schemaLocation
	 * @param errorHandler default: initParser(null, DocumentJDFImpl.class.getName(), null);
	 */
	protected void initParser(final String schemaLocation, final XMLErrorHandler errorHandler)
	{
		m_SchemaLocation = schemaLocation;
		this.setErrorHandler(errorHandler);

		try
		{
			if (m_SchemaLocation == null || m_SchemaLocation.equals(JDFCoreConstants.EMPTYSTRING))
			{
				this.setFeature("http://xml.org/sax/features/validation", false);
				this.setFeature("http://apache.org/xml/features/validation/schema", false);
			}
			else
			{
				this.setFeature("http://xml.org/sax/features/validation", true);
				this.setFeature("http://apache.org/xml/features/validation/schema", true);
				this.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", m_SchemaLocation);
			}

			this.setProperty("http://apache.org/xml/properties/dom/document-class-name", getDocumentClass());
			this.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
			this.setFeature("http://xml.org/sax/features/namespaces", true);

		}
		catch (final SAXNotRecognizedException e)
		{
			log.error("error parsing", e);
			m_lastExcept = e;
		}
		catch (final SAXNotSupportedException e)
		{
			log.error("error parsing", e);
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
		m_ErrorHandler.setParser(this);
		super.setErrorHandler(m_ErrorHandler);
	}

	/**
	 * @param inSource
	 * @param bEraseEmpty
	 * @return
	 */
	protected XMLDoc runParser(final InputSource inSource, final boolean bEraseEmpty)
	{
		XMLDoc doc = getXMLDoc();
		try
		{
			super.parse(inSource);
			doc.setMemberDoc((DocumentXMLImpl) getDocument());
			if (bEraseEmpty)
			{
				doc.getRoot().eraseEmptyNodes(true); // cleanup the XML
			}
		}
		catch (final Exception e)
		{
			m_lastExcept = e;
			if (XMLErrorHandler.isWantLog())
			{
				log.error("error parsing " + e.getMessage());
			}
			doc = null;
		}
		catch (final StackOverflowError e)
		{
			m_lastExcept = null;
			log.error("error parsing", e);
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
			final DocumentXMLImpl memberDocument = doc.getMemberDocument();
			final String namespaceURI = root.getNamespaceURI();
			setDocumentProperties(root, memberDocument, namespaceURI);
			memberDocument.setIgnoreNSDefault(ignoreNSDefault);
		}
		inputID = null;
		return doc;
	}

	protected XMLDoc getXMLDoc()
	{
		final XMLDoc doc = new XMLDoc();
		return doc;
	}

	protected void setDocumentProperties(final KElement root, final DocumentXMLImpl memberDocument, final String namespaceURI)
	{
		//nop
	}

	/**
	 * set the physical schema location
	 *
	 * @param nsURI the schema namespace uri
	 * @param locationURL the schema location url
	 */
	public void setSchemaLocation(final String nsURI, final String locationURL)
	{
		m_SchemaLocation = nsURI + JDFCoreConstants.BLANK + locationURL;
	}

	/**
	 * @see org.apache.xerces.parsers.AbstractDOMParser#startDocument(org.apache.xerces.xni.XMLLocator, java.lang.String,
	 * org.apache.xerces.xni.NamespaceContext, org.apache.xerces.xni.Augmentations)
	 */
	@Override
	public void startDocument(final XMLLocator locator, final String encoding, final NamespaceContext namespaceContext, final Augmentations augs) throws XNIException
	{
		super.startDocument(locator, encoding, namespaceContext, augs);
		final DocumentXMLImpl memberDocument = (DocumentXMLImpl) getDocument();
		memberDocument.setIgnoreNSDefault(ignoreNSDefault);
	}

	/**
	 * (non-Javadoc) reset all internal variables to a reasonable default
	 *
	 * @see org.apache.xerces.parsers.AbstractDOMParser#reset()
	 */
	public void cleanup()
	{
		m_lastExcept = null;
		ignoreNSDefault = false;
		m_eraseEmpty = true;
		if (m_ErrorHandler != null)
			m_ErrorHandler.reset();
		m_SchemaLocation = null;
		fDocument = null;
		fDocumentSource = null;
		fDocumentImpl = null;
		inputID = null;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + ": " + m_SchemaLocation + " " + m_ErrorHandler;
	}

	/**
	 * set the input id attribute for error logging
	 * @param inputID
	 */
	public void setInputID(final String inputID)
	{
		this.inputID = inputID;
	}
}
