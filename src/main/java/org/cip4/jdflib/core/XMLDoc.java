/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 * XMLDoc.java
 *
 * Copyright (c) 2001-2003 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 */
package org.cip4.jdflib.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.mail.BodyPart;
import javax.mail.Multipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.dom.ElementDefinitionImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.ifaces.IStreamWriter;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.HashUtil;
import org.cip4.jdflib.util.StreamUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.UrlPart;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.net.HTTPDetails;
import org.cip4.jdflib.util.zip.XMLZipWriter;
import org.cip4.jdflib.util.zip.ZipReader;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Notation;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.events.Event;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.traversal.TreeWalker;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         < July 15, 2009
 */
@SuppressWarnings("deprecation")
public class XMLDoc implements Cloneable, IStreamWriter
{

	protected DocumentXMLImpl m_doc;
	final private static Log log = LogFactory.getLog(XMLDoc.class);
	private static int lineWidth = 100;
	private static int indent = 2;

	public static int getIndent()
	{
		return indent;
	}

	public static void setIndent(final int indent)
	{
		XMLDoc.indent = indent;
	}

	public static int getLineWidth()
	{
		return lineWidth;
	}

	public static void setLineWidth(final int lineWidth)
	{
		XMLDoc.lineWidth = lineWidth;
	}

	// **************************************** Constructors
	// ****************************************
	/**
	 * constructor
	 */
	public XMLDoc()
	{
		m_doc = getImpl();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o)
	{
		if (o == null)
		{
			return false;
		}
		if (!(o instanceof XMLDoc))
		{
			return false;
		}

		final XMLDoc d = (XMLDoc) o;
		if (m_doc == null)
		{
			return d.m_doc == null;
		}

		return m_doc.equals(d.m_doc);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return HashUtil.hashCode(0, this.m_doc);
	}

	/**
	 * constructor
	 *
	 * @param document
	 */
	public XMLDoc(final Document document)
	{
		if (document == null)
		{
			throw new JDFException("XMLDoc(Document) null input Document");
		}
		try
		{
			if (document instanceof DocumentXMLImpl)
			{
				m_doc = (DocumentXMLImpl) document;
			}
			else
			{
				m_doc = reparseDOM(document);
			}
		}
		catch (final Exception e)
		{
			log.error("exception constructing Document", e);
			final String s = e.toString();
			throw new JDFException("Snafu: XMLDoc(Document) not implemented; class=" + s);
		}
	}

	protected XMLDoc createRoot(final Document document)
	{
		return new XMLDoc(document.getDocumentElement().getNodeName(), StringUtil.getNonEmpty(document.getDocumentElement().getNamespaceURI()));
	}

	/**
	 * reparse a document that
	 *
	 * @param document
	 * @return
	 */
	private DocumentXMLImpl reparseDOM(final Document document)
	{

		final XMLDoc d = createRoot(document);

		final DocumentXMLImpl newDoc = d.getMemberDocument();
		reparseElement(newDoc.getDocumentElement(), document.getDocumentElement());
		return newDoc;
	}

	/**
	 * reparse an element in the context of the current document implemetation
	 *
	 * @param dst
	 * @param src
	 */
	private void reparseElement(final Element dst, final Element src)
	{
		final NamedNodeMap atts = src.getAttributes();
		for (int i = 0; i < atts.getLength(); i++)
		{
			final Attr item = (Attr) atts.item(i);
			dst.setAttribute(item.getNodeName(), item.getNodeValue());
		}
		final NodeList nl = src.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++)
		{
			final Node n = nl.item(i);
			final Node childNode = dst.getOwnerDocument().importNode(n, true);
			dst.insertBefore(childNode, null);
		}

	}

	/**
	 * constructor
	 *
	 * @param document
	 */
	public XMLDoc(final DocumentXMLImpl document)
	{
		if (document == null)
		{
			throw new JDFException("XMLDoc(DocumentXMLImpl) null input Document");
		}
		m_doc = document;
	}

	/**
	 * constructor
	 *
	 * @param other
	 */
	public XMLDoc(final XMLDoc other)
	{
		m_doc = other == null ? null : other.m_doc;
	}

	/**
	 * convenience constructor
	 *
	 * @param strDocType ElementName.JDF, ElementName.JMF, "Config" ...
	 *
	 */
	public XMLDoc(final String strDocType)
	{
		this(strDocType, null);
	}

	/**
	 * constructor
	 *
	 * @param strDocType ElementName.JDF, ElementName.JMF, "Config" ...
	 * @param namespaceURI namespace to be used by the new XMLDoc
	 */
	public XMLDoc(final String strDocType, final String namespaceURI)
	{
		m_doc = getImpl();
		setRoot(strDocType, namespaceURI);
	}

	protected DocumentXMLImpl getImpl()
	{
		return new DocumentXMLImpl();
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * initialize a new root of strDocType in the document called by constructor XMLDoc(String strDocType)
	 *
	 * @param strDocType qualified tag name of the doc root to create if still empty
	 * @param namespaceURI namespace URI of the doc root
	 * @return JDFElement - the root element
	 *
	 * @default setRoot(ElementName.JDF, null)
	 */
	public KElement setRoot(final String strDocType, final String namespaceURI)
	{
		KElement root = (KElement) m_doc.getDocumentElement();

		if (root != null)
		{
			throw new JDFException("XMLDoc.setRoot:  root already exists: ");
		}

		root = (KElement) appendChild(m_doc.createElementNS(namespaceURI, strDocType));
		return root;
	}

	/**
	 * getMemberDocument
	 *
	 * @return the MemberDocument
	 */
	public DocumentXMLImpl getMemberDocument()
	{
		return this.m_doc;
	}

	/**
	 * Method Flush<br>
	 * clean the m_doc and restart from scratch. The root element remains
	 *
	 * @return boolean - true if successful
	 */
	protected boolean flush()
	{
		return getRoot().flush();
	}

	/**
	 * get the root of the dom tree
	 *
	 * @return JDFElement
	 *
	 *         default: getRoot()
	 */
	public KElement getRoot()
	{
		return (KElement) m_doc.getDocumentElement();
	}

	/**
	 * write2String - write to a string;
	 *
	 * @param indent the indentation of the xml
	 *
	 * @return String - output
	 */
	public String write2String(final int indent)
	{
		String strResult = JDFCoreConstants.EMPTYSTRING;
		ByteArrayOutputStream outStream = null;

		try
		{
			outStream = new ByteArrayOutputStream(4096);
			write2Stream(outStream, indent, indent == 0);
			strResult = outStream.toString(StringUtil.UTF8);
		}
		catch (final IOException e)
		{
			log.error("write2String: ", e);
		}
		finally
		{
			if (outStream != null)
			{
				try
				{
					outStream.close();
				}
				catch (final IOException e1)
				{
					log.error("error closing: ", e1);
				}
			}
		}

		return strResult;
	}

	/**
	 * write2File - write to a file; Create if it doesn't exist always assume utf-8 encoding
	 *
	 * @param oFilePath
	 * @param indent
	 *
	 * @return boolean
	 * @deprecated 060419 use write2File(oFilePath, indent, true);
	 * @default write2File(String oFilePath, 0)
	 */
	@Deprecated
	public boolean write2File(final String oFilePath, final int indent)
	{
		return write2File(oFilePath, indent, true);
	}

	/**
	 * write2File - write to a file; Create if it doesn't exist always assume utf-8 encoding
	 *
	 * @param oFilePath
	 * @param indent
	 *
	 * @return boolean
	 * @default write2File(String oFilePath, 0)
	 */
	public boolean write2File(final String oFilePath)
	{
		return write2File(oFilePath, indent, indent == 0);
	}

	/**
	 * write2File - write to a file; Create if it doesn't exist always assume utf-8 encoding
	 *
	 * @param oFilePath
	 * @param indent
	 *
	 * @return boolean
	 * @default write2File(String oFilePath, 0)
	 */
	public boolean write2File(final File oFilePath)
	{
		return write2File(oFilePath, indent, indent == 0);
	}

	/**
	 * write2Zip - write to a file and compress as zip; remove the .zip from the extension in the zip entry or add .zip
	 *
	 * @param file
	 *
	 * @return the created file
	 * 
	 */
	public File write2Zip(File file)
	{
		if (file == null)
		{
			if (getOriginalFileName() != null)
				file = new File(getOriginalFileName());
			if (!UrlUtil.isZip(FileUtil.getExtension(file)))
				file = new File(file.getAbsolutePath() + ".zip");
		}

		final XMLZipWriter w = new XMLZipWriter(this, file.getName());
		return FileUtil.writeFile(w, file);
	}

	/**
	 * write2File - write to a file; Create if it doesn't exist DO NOT attempt any url escaping voodoo @see write2URL
	 *
	 * @param oFilePath the file path where to write the file
	 * @param indent indentation
	 * @param bPreserveSpace if true, preserve whitespace
	 *
	 * @return boolean - true if successful
	 *
	 * @default write2File(String oFilePath, 0)
	 */
	public boolean write2File(String oFilePath, final int indent, final boolean bPreserveSpace)
	{
		if (oFilePath == null)
		{
			oFilePath = m_doc.m_OriginalFileName;
		}

		if (oFilePath == null)
		{
			log.error("not writing " + getRootName() + " to null File, bailing out");
			return false;
		}

		return write2File(new File(oFilePath), indent, bPreserveSpace);
	}

	/**
	 * write2File - write to a file; Create if it doesn't exist
	 *
	 * @param file the file to write to
	 * @param indent indentation
	 * @param bPreserveSpace if true, preserve whitespace
	 *
	 * @return boolean - true if successful
	 *
	 * @default write2File(String oFilePath, 0)
	 */
	public boolean write2File(File file, final int indent, final boolean bPreserveSpace)
	{
		if (file == null)
		{
			if (getOriginalFileName() != null)
			{
				file = new File(getOriginalFileName());
			}
			else
			{
				log.error("not writing " + getRootName() + " to null File, bailing out");
				return false;
			}
		}

		if (file.isDirectory() && getOriginalFileName() != null)
		{
			final File orig = new File(getOriginalFileName());
			file = new File(file + File.separator + orig.getName());
		}

		final OutputStream outStream = FileUtil.getBufferedOutputStream(file);
		try
		{
			write2Stream(outStream, indent, bPreserveSpace);
		}
		catch (final IOException e)
		{
			log.warn("could not write to file " + file, e);
			return false;
		}
		finally
		{
			StreamUtil.close(outStream);
		}
		if (getOriginalFileName() == null)
		{
			setOriginalFileName(file.getPath());
		}

		return true;
	}

	public String getRootName()
	{

		final Element e = getDocumentElement();
		return e == null ? null : e.getNodeName();
	}

	/**
	 * @param outStream
	 * @param indent
	 * @throws IOException
	 */
	public void write2Stream(final OutputStream outStream, final int indent) throws IOException
	{
		write2Stream(outStream, indent, indent == 0);
	}

	@Override
	public void writeStream(final OutputStream stream) throws IOException
	{
		write2Stream(stream, indent);
	}

	/**
	 * write this to a stream
	 *
	 * @param outStream
	 * @param indent
	 * @param bPreserveSpace
	 * @throws IOException
	 * @since 080425 synchronized
	 */
	public void write2Stream(final OutputStream outStream, final int indent, final boolean bPreserveSpace) throws IOException
	{
		if (outStream == null)
		{
			throw new IOException("Cannot write to null stream");
		}
		else
		{
			for (int i = 1; true; i++)
			{
				try
				{
					final OutputFormat format = new OutputFormat(m_doc);

					if (bPreserveSpace)
					{
						format.setPreserveSpace(true);
					}

					if (!XMLFormatter.isDefault())
					{
						XMLFormatter.apply(format);
					}
					if (indent < 1)
					{
						format.setIndenting(false);
					}
					else
					{
						format.setIndenting(true);
						format.setIndent(indent);
						format.setLineWidth(lineWidth);
						// TODO remove schema defaulted attributes when serializing
					}

					final XMLSerializer serial = new XMLSerializer(outStream, format);
					// serial.setNamespaces(false); // ###DOM_1_nodes
					serial.setNamespaces(true);
					serial.asDOMSerializer();
					synchronized (m_doc)
					{
						serial.serialize(m_doc);
					}
					return; // all is well here
				}
				catch (final Exception x)
				{
					if (i >= 5)
					{
						log.error("writing to Stream, bailing out after " + i + " attempts", x);
						throw x; // try n times, else ciao
					}
					ThreadUtil.sleep((100 * i));
					log.warn("retry exception #" + i + " for writing to stream; original XML file=" + getOriginalFileName(), x);
				}
			}
		}
	}

	/**
	 * @deprecated 060306
	 * @param elem
	 * @param outStream
	 * @param indent
	 * @throws IOException
	 */
	@Deprecated
	public static void write2StreamStatic(final Element elem, final OutputStream outStream, final int indent) throws IOException
	{
		write2StreamStatic(elem, outStream, indent, true);
	}

	/**
	 * @deprecated 060306
	 * @param elem
	 * @param outStream
	 * @param indent
	 * @param bPreserveSpace
	 * @throws IOException
	 */
	@Deprecated
	public static void write2StreamStatic(final Element elem, final OutputStream outStream, final int indent, final boolean bPreserveSpace) throws IOException
	{
		final Document doc = elem.getOwnerDocument();

		final OutputFormat format = new OutputFormat(doc);

		if (bPreserveSpace)
		{
			format.setPreserveSpace(true);
		}

		if (indent < 1)
		{
			format.setIndenting(false);
		}
		else
		{
			format.setIndenting(true);
			format.setIndent(indent);
			// TODO remove schema defaulted attributes when serializing
		}

		final XMLSerializer serial = new XMLSerializer(outStream, format);
		// serial.setNamespaces(false); // ###DOM_1_nodes
		serial.setNamespaces(true);
		serial.asDOMSerializer();
		serial.serialize(elem);
	}

	/**
	 * getDoctype
	 *
	 * @return DocumentType
	 */
	public DocumentType getDoctype()
	{
		return (m_doc == null) ? null : m_doc.getDoctype();
	}

	/**
	 * getImplementation
	 *
	 * @return DOMImplementation
	 */
	public DOMImplementation getImplementation()
	{
		return (m_doc == null) ? null : m_doc.getImplementation();
	}

	/**
	 * getDocumentElement
	 *
	 * @return Element
	 */
	public Element getDocumentElement()
	{
		return (m_doc == null) ? null : m_doc.getDocumentElement();
	}

	/**
	 * createElement create a JDFElement that floats in nirvana. This must be appended to a node with appendChild (created in namespace JDFCoreConstants.NONAMESPACE (DOM Level
	 * 2)).<br>
	 * Another way would be to use KElement.appendElement(String elementName, String nameSpaceURI)
	 *
	 * @param elementName name of the element that is created
	 *
	 * @return Element - unconnected element that is created
	 */
	public Element createElement(final String elementName)
	{
		Element elem = null;

		if (m_doc != null)
		{
			elem = m_doc.createElement(elementName);
		}

		return elem;
	}

	/**
	 * createDocumentFragment
	 *
	 * @return DocumentFragment
	 */
	public DocumentFragment createDocumentFragment()
	{
		return (m_doc == null) ? null : m_doc.createDocumentFragment();
	}

	/**
	 * createTextNode
	 *
	 * @param data
	 *
	 * @return Text
	 */
	public Text createTextNode(final String data)
	{
		return (m_doc == null) ? null : m_doc.createTextNode(data);
	}

	/**
	 * createComment
	 *
	 * @param data
	 *
	 * @return Comment
	 */
	public Comment createComment(final String data)
	{
		return (m_doc == null) ? null : m_doc.createComment(data);
	}

	/**
	 * create a CDATA section, which has this document as ownerDoc
	 *
	 * @param data content of the CDATA
	 *
	 * @return CDATASection
	 */
	public CDATASection createCDATASection(final String data)
	{
		return (m_doc == null) ? null : m_doc.createCDATASection(data);
	}

	/**
	 * sets the processing instruction for an xslt stylesheet
	 *
	 * @param url the url of the xslt file
	 */
	public void setXSLTURL(final String url)
	{
		if (StringUtil.getNonEmpty(url) != null)
		{
			final String data = "type=\"text/xsl\" href=\"" + url + "\"";
			final ProcessingInstruction pi = createProcessingInstruction("xml-stylesheet", data);
			insertBefore(pi, getRoot());
		}
	}

	/**
	 * gets the url for an xslt stylesheet
	 *
	 * e
	 */
	public String getXSLTURL()
	{
		final ProcessingInstruction pi = getProcessingInstruction(0, "xml-stylesheet");
		final String val = pi == null ? null : pi.getNodeValue();
		final VString v = StringUtil.tokenize(val, null, false);
		if (v != null)
		{
			for (final String pair : v)
			{
				if ("href".equalsIgnoreCase(StringUtil.token(pair, 0, "=")))
				{
					return StringUtil.token(pair, -1, "\"'= ");
				}
			}
		}
		return null;
	}

	/**
	 * set the xpath values of thei to the values in the node
	 *
	 * @param valueMap the map of values, if this is still null, MUST be fully qualified
	 */
	public void setXPathValues(final JDFAttributeMap valueMap)
	{
		if (valueMap != null && valueMap.size() > 0)
		{
			if (getRoot() == null)
			{
				for (String root : valueMap.keySet())
				{
					if (root.startsWith("/"))
					{
						root = StringUtil.token(root, 0, "/[");
						setRoot(root, null);
						break;
					}
				}
			}
			getRoot().setXPathValues(valueMap);
		}
	}

	/**
	 * creates a ProcessingInstruction having this Document as ownerDoc
	 *
	 * @param target the target "processor channel"
	 * @param data parameter string to be passed to the target
	 *
	 * @return ProcessingInstruction
	 */
	public ProcessingInstruction createProcessingInstruction(final String target, final String data)
	{
		return (m_doc == null) ? null : m_doc.createProcessingInstruction(target, data);
	}

	/**
	 *
	 * @param skip
	 * @param name
	 * @return
	 */
	public ProcessingInstruction getProcessingInstruction(final int skip, final String name)
	{
		return (ProcessingInstruction) getNode(Document.PROCESSING_INSTRUCTION_NODE, skip, name);
	}

	/**
	 *
	 * @param type the node type see {@link Document}
	 * @param skip the number onodes to skip
	 * @return
	 */
	public Node getNode(final short type, int skip, final String nodeName)
	{
		if (m_doc == null)
			return null;
		Node n = m_doc.getFirstChild();
		while (n != null)
		{
			if (n.getNodeType() == type && (nodeName == null || nodeName.equals(n.getNodeName())))
			{
				if (skip-- <= 0)
				{
					return n;
				}
			}
			n = n.getNextSibling();
		}
		return null;
	}

	/**
	 * createAttribute in namespace JDFCoreConstants.NONAMESPACE (DOM Level 2)
	 *
	 * @param name attribute name
	 * @return Attr
	 */
	public Attr createAttribute(final String name)
	{
		Attr a = null;
		if (m_doc == null)
		{
			throw new JDFException("Creating an attribute on a null document");
		}

		if (name.indexOf(":") < 0)
		{
			a = m_doc.createAttributeNS(null, name);
		}
		else
		{
			if (name.startsWith("xsi:"))
			{
				a = m_doc.createAttributeNS(JDFCoreConstants.XSIURI, name);
			}
			else if (name.startsWith("xmlns:"))
			{
				a = m_doc.createAttributeNS(JDFCoreConstants.XMLNSURI, name);
			}
			else
			{
				a = m_doc.createAttribute(name);
			}
		}
		return a;
	}

	/**
	 * creates an EntityReference
	 *
	 * @param name name of the entity to refer to
	 *
	 * @return the newly created EntityReference
	 */
	public EntityReference createEntityReference(final String name)
	{
		return (m_doc == null) ? null : m_doc.createEntityReference(name);
	}

	/**
	 * return a NodeList of all elements having the specified tagname
	 *
	 * @param tagname tag name of the elements to find (JDFCoreConstants.star for all elements)
	 *
	 * @return NodeList
	 */
	public NodeList getElementsByTagName(final String tagname)
	{
		return (m_doc == null) ? null : m_doc.getElementsByTagName(tagname);
	}

	/**
	 * copy a node from another document in this document
	 *
	 * @param importedNode node to import
	 * @param deep if true: recurse and import the subtree under the node as well
	 *
	 * @return Node
	 */
	public Node importNode(final Node importedNode, final boolean deep)
	{
		return (m_doc == null) ? null : m_doc.importNode(importedNode, deep);
	}

	/**
	 * create a Element that floats in nirvana, this must be appended to a node with appendChild
	 *
	 * @param namespaceURI the namespace uri of the created element
	 * @param qualifiedName name of the element that is created
	 *
	 * @return Element - unconnected element that is created
	 */
	public Element createElementNS(final String namespaceURI, final String qualifiedName)
	{
		return (m_doc == null) ? null : m_doc.createElementNS(namespaceURI, qualifiedName);
	}

	/**
	 * create an attribute withe the given name in the given namespace
	 *
	 * @param namespaceURI namespace URI of the attribute
	 * @param qualifiedName qualified name of the attribute
	 *
	 * @return Attr - the newly created attribute
	 */
	public Attr createAttributeNS(final String namespaceURI, final String qualifiedName)
	{
		return (m_doc == null) ? null : m_doc.createAttributeNS(namespaceURI, qualifiedName);
	}

	/**
	 * get a NodeList of all elements with a given name and namespace URI
	 *
	 * @param namespaceURI the namespace URI to look for
	 * @param myLocalName the element name to look for
	 *
	 * @return NodeList with all elements found
	 */
	public NodeList getElementsByTagNameNS(final String namespaceURI, final String myLocalName)
	{
		return (m_doc == null) ? null : m_doc.getElementsByTagNameNS(namespaceURI, myLocalName);
	}

	/**
	 * get element with ID = elementId similar to Docoment.getElementByID but works with non schema parsed documents
	 *
	 * @param elementId the element ID to look for
	 *
	 * @return the Element found
	 */
	public Element getElementById(final String elementId)
	{
		final KElement root = getRoot();
		return root.getTarget(elementId, null);
	}

	/**
	 * gets the node name
	 *
	 * @return String
	 */
	public String getNodeName()
	{
		return (m_doc == null) ? null : m_doc.getNodeName();
	}

	/**
	 * gets the node value
	 *
	 * @return String
	 */
	public String getNodeValue()
	{
		return (m_doc == null) ? null : m_doc.getNodeValue();
	}

	/**
	 * set the node value
	 *
	 * @param nodeValue value to set the node to
	 */
	public void setNodeValue(final String nodeValue)
	{
		if (m_doc != null)
		{
			m_doc.setNodeValue(nodeValue);
		}
	}

	/**
	 * set the namespace map from another document
	 *
	 * @param other value to set the node to
	 */
	public void setNSMap(final XMLDoc other)
	{
		if (m_doc != null)
		{
			m_doc.setNSMap(other.m_doc);
		}
	}

	/**
	 * get a copy of the nsmap
	 *
	 */
	public JDFAttributeMap getNSMap()
	{
		if (m_doc != null)
		{
			return new JDFAttributeMap(m_doc.nsMap);
		}
		return null;
	}

	/**
	 * get node type
	 *
	 * @return a <i>short</i> representing the node type
	 */
	public short getNodeType()
	{
		return (m_doc == null) ? 0 : m_doc.getNodeType();
	}

	/**
	 * get the parent node of 'this' node
	 *
	 * @return the parent node
	 */
	public Node getParentNode()
	{
		return (m_doc == null) ? null : m_doc.getParentNode();
	}

	/**
	 * get the child nodes of this node
	 *
	 * @return a NodeList containing the child nodes
	 */
	public NodeList getChildNodes()
	{
		return (m_doc == null) ? null : m_doc.getChildNodes();
	}

	/**
	 * get the first child node of 'this'
	 *
	 * @return the first child node
	 */
	public Node getFirstChild()
	{
		return (m_doc == null) ? null : m_doc.getFirstChild();
	}

	/**
	 * get the last child node of 'this'
	 *
	 * @return the last child node
	 */
	public Node getLastChild()
	{
		return (m_doc == null) ? null : m_doc.getLastChild();
	}

	/**
	 * get the previous sibling of 'this'
	 *
	 * @return the previous sibling node
	 */
	public Node getPreviousSibling()
	{
		return (m_doc == null) ? null : m_doc.getPreviousSibling();
	}

	/**
	 * get the next sibling of 'this'
	 *
	 * @return the next sibling node
	 */
	public Node getNextSibling()
	{
		return (m_doc == null) ? null : m_doc.getNextSibling();
	}

	/**
	 * get the attributes associated with this node
	 *
	 * @return NamedNodeMap containing the attributes associated with this node
	 */
	public NamedNodeMap getAttributes()
	{
		return (m_doc == null) ? null : m_doc.getAttributes();
	}

	/**
	 * insert a new node before a given node
	 *
	 * @param newChild the new child node to insert
	 * @param refChild the ref child node, the new node is inserted before it
	 *
	 * @return Node
	 */
	public Node insertBefore(final Node newChild, final Node refChild)
	{
		return (m_doc == null) ? null : m_doc.insertBefore(newChild, refChild);
	}

	/**
	 * replace a child node with a new one
	 *
	 * @param newChild the new child node to add
	 * @param oldChild the old child node to be replaced
	 *
	 * @return Node
	 */
	public Node replaceChild(final Node newChild, final Node oldChild)
	{
		return (m_doc == null) ? null : m_doc.replaceChild(newChild, oldChild);
	}

	/**
	 * remove a child from 'this'
	 *
	 * @param oldChild the child node to be removed
	 *
	 * @return oldChild, in its new state (removed)
	 */
	public Node removeChild(final Node oldChild)
	{
		return (m_doc == null) ? null : m_doc.removeChild(oldChild);
	}

	/**
	 * append a new child node to 'this'
	 *
	 * @param newChild new child node to add
	 *
	 * @return Node
	 */
	public Node appendChild(final Node newChild)
	{
		return (m_doc == null) ? null : m_doc.appendChild(newChild);
	}

	/**
	 * test if 'this' has any children
	 *
	 * @return boolean - true, if 'this' has children
	 */
	public boolean hasChildNodes()
	{
		return (m_doc == null) ? false : m_doc.hasChildNodes();
	}

	/**
	 * get a copy of 'this'
	 *
	 * @param deep true: copy children as well
	 *
	 * @return Node - a copy of 'this'
	 */
	public Node cloneNode(final boolean deep)
	{
		return (m_doc == null) ? null : m_doc.cloneNode(deep);
	}

	/**
	 * normalize
	 */
	public void normalize()
	{
		if (m_doc != null)
		{
			m_doc.normalize();
		}
	}

	/**
	 * test whether a specific DOMImplelementation feature is supported by 'this'
	 *
	 * @param feature package name of the feature to test
	 * @param version version number of the package name to test
	 *
	 * @return boolean - true, if the feature is sopported
	 * @see <a href="http://xerces.apache.org/xerces-j/apiDocs/org/apache/xerces/dom/NodeImpl.html#isSupported(java.lang.String,%20java.lang.String)" <a>Xerxes-Documentation</a>
	 */
	public boolean isSupported(final String feature, final String version)
	{
		return (m_doc == null) ? false : m_doc.isSupported(feature, version);
	}

	/**
	 * get the namespace prefix of 'this' node
	 *
	 * @return String - namespace prefix (null if unspecified)
	 */
	public String getPrefix()
	{
		if (m_doc != null)
		{
			return m_doc.getPrefix();
		}

		return null;
	}

	/**
	 * set the namespace prefix of 'this' node
	 *
	 * @param prefix namespace prefix
	 */
	public void setPrefix(final String prefix)
	{
		if (m_doc != null)
		{
			m_doc.setPrefix(prefix);
		}
	}

	/**
	 * get the the local part of the qualified name of 'this'
	 *
	 * @return String - local name
	 */
	public String getLocalName()
	{
		return (m_doc == null) ? null : m_doc.getLocalName();
	}

	/**
	 * check whether the underlying document is null
	 *
	 * @return true if m_doc==null
	 */
	public boolean isNull()
	{
		return m_doc == null;
	}

	/**
	 * check whether the underlying document is null
	 *
	 * @return true if m_doc==null
	 */
	public boolean isDirty()
	{
		return m_doc != null && m_doc.isDirty();
	}

	/**
	 * check if 'this' has attributes
	 *
	 * @return true, if 'this' has attributes
	 */
	public boolean hasAttributes()
	{
		return (m_doc == null) ? false : m_doc.hasAttributes();
	}

	/**
	 * createDocumentType
	 *
	 * @param qualifiedName
	 * @param publicID
	 * @param systemID
	 *
	 * @return DocumentType
	 */
	public DocumentType createDocumentType(final String qualifiedName, final String publicID, final String systemID)
	{
		return (m_doc == null) ? null : m_doc.createDocumentType(qualifiedName, publicID, systemID);
	}

	/**
	 * sets whether the DOM implementation performs error checking upon operations
	 *
	 * @param check true - enable error checking
	 */
	public void setErrorChecking(final boolean check)
	{
		if (m_doc != null)
		{
			m_doc.setErrorChecking(check);
		}
	}

	/**
	 * does the DOM implementation perform error checking upon operations?
	 *
	 * @return true - error checking is enabled, otherwise false
	 */
	public boolean getErrorChecking()
	{
		return (m_doc == null) ? false : m_doc.getErrorChecking();
	}

	/**
	 * create an entity
	 *
	 * @param name name of the entity
	 *
	 * @return Entity - the newly created entity
	 */
	public Entity createEntity(final String name)
	{
		return (m_doc == null) ? null : m_doc.createEntity(name);
	}

	/**
	 * creates a Notation having this Document as its OwnerDoc
	 *
	 * @param name name of the notation
	 *
	 * @return Notation - the newly created notation
	 */
	public Notation createNotation(final String name)
	{
		return (m_doc == null) ? null : m_doc.createNotation(name);
	}

	/**
	 * creates an element definition. Element definitions hold default attribute values.
	 *
	 * @param name
	 *
	 * @return ElementDefinitionImpl
	 */
	public ElementDefinitionImpl createElementDefinition(final String name)
	{
		return (m_doc == null) ? null : m_doc.createElementDefinition(name);
	}

	/**
	 * Registers an identifier name with a specified element node
	 *
	 * @param idName
	 * @param element
	 */
	public void putIdentifier(final String idName, final Element element)
	{
		if (m_doc != null)
		{
			m_doc.putIdentifier(idName, element);
		}
	}

	/**
	 * gets the element with the registered name = "idName"
	 *
	 * @param idName name of the element to get
	 *
	 * @return Element - the element with "idName"
	 */
	public Element getIdentifier(final String idName)
	{
		return (m_doc == null) ? null : m_doc.getIdentifier(idName);
	}

	/**
	 * remove element with identifier "idName"
	 *
	 * @param idName
	 */
	public void removeIdentifier(final String idName)
	{
		if (m_doc != null)
		{
			m_doc.removeIdentifier(idName);
		}
	}

	/**
	 * gets the registered identifiers
	 *
	 * @return Enumeration of registered identifiers
	 */
	public Enumeration<?> getIdentifiers()
	{
		return (m_doc == null) ? null : m_doc.getIdentifiers();
	}

	/**
	 * create a node iterator
	 *
	 * @param root the root of the iterator
	 * @param whatToShow the whatToShow mask
	 * @param filter the node filter (null = no filter)
	 *
	 * @return the newly created NodeIterator
	 */
	public NodeIterator createNodeIterator(final Node root, final short whatToShow, final NodeFilter filter)
	{
		return (m_doc == null) ? null : m_doc.createNodeIterator(root, whatToShow, filter);
	}

	/**
	 * createNodeIterator
	 *
	 * @param root the root of the iterator
	 * @param whatToShow the whatToShow mask
	 * @param filter the node filter (null = no filter)
	 * @param entityReferenceExpansion true: expand the contents of EntityReference nodes
	 *
	 * @return the newly created NodeIterator
	 */
	public NodeIterator createNodeIterator(final Node root, final int whatToShow, final NodeFilter filter, final boolean entityReferenceExpansion)
	{
		return (m_doc == null) ? null : m_doc.createNodeIterator(root, whatToShow, filter, entityReferenceExpansion);
	}

	/**
	 * creates a TreeWalker
	 *
	 * @param root the root of the iterator
	 * @param whatToShow the whatToShow mask
	 * @param filter the node filter (null = no filter)
	 *
	 * @return the newly created TreeWalker
	 */
	public TreeWalker createTreeWalker(final Node root, final short whatToShow, final NodeFilter filter)
	{
		return (m_doc == null) ? null : m_doc.createTreeWalker(root, whatToShow, filter);
	}

	/**
	 * creates a TreeWalker
	 *
	 * @param root the root of the iterator
	 * @param whatToShow the whatToShow mask
	 * @param filter the node filter (null = no filter)
	 * @param entityReferenceExpansion true: expand the contents of EntityReference nodes
	 *
	 * @return the newly created TreeWalker
	 */
	public TreeWalker createTreeWalker(final Node root, final int whatToShow, final NodeFilter filter, final boolean entityReferenceExpansion)
	{
		return (m_doc == null) ? null : m_doc.createTreeWalker(root, whatToShow, filter, entityReferenceExpansion);
	}

	/**
	 * createRange
	 *
	 * @return Range
	 */
	public Range createRange()
	{
		return (m_doc == null) ? null : m_doc.createRange();
	}

	/**
	 * create an Event object
	 *
	 * @param type type of Event interface to be created
	 *
	 * @return the newly created Event
	 */
	public Event createEvent(final String type)
	{
		return (m_doc == null) ? null : m_doc.createEvent(type);
	}

	/**
	 * clone the document, completely severing all connections to the original document
	 *
	 * @return Object
	 */
	@Override
	public XMLDoc clone()
	{
		final XMLDoc clon = new XMLDoc();
		if (m_doc != null)
		{
			clon.m_doc = m_doc.clone();
		}
		return clon;
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return write2String(2);
	}

	/**
	 * toXML
	 *
	 * @return String
	 */
	public String toXML()
	{
		return write2String(2);
	}

	protected void setMemberDoc(final DocumentXMLImpl myDoc)
	{
		m_doc = myDoc;
	}

	/**
	 * This method sends the contents of this XMLDoc to the URL <code>strURL</code> and receives the response in the returned XMLDoc.
	 *
	 * @param strURL the URL to write to
	 * @param strContentType the content type to write to
	 *
	 * @return docResponse the response received from URL. if url is a file, an empty doc is returned
	 *
	 *         A Null document if no response was received, or an exception occurred
	 */
	public XMLDoc write2URL(final String strURL, final String strContentType)
	{
		return write2URL(strURL, strContentType, null);
	}

	/**
	 * This method sends the contents of this XMLDoc to the URL <code>strURL</code> and receives the response in the returned XMLDoc.
	 *
	 * @param strURL the URL to write to
	 * @param strContentType the content type to write to
	 *
	 * @return docResponse the response received from URL. if url is a file, an empty doc is returned
	 *
	 *         A Null document if no response was received, or an exception occurred
	 */

	public XMLDoc write2URL(final String strURL, final String strContentType, final HTTPDetails details)
	{
		XMLDoc docResponse = null;
		final URL url = UrlUtil.stringToURL(strURL);
		final String protocol = url.getProtocol(); // file; ftp; http
		if (protocol.equalsIgnoreCase("File"))
		{
			write2File(UrlUtil.urlToFile(strURL), 0, true);
			docResponse = new XMLDoc();
		}
		else
		{
			final UrlPart p = write2HttpURL(url, strContentType, details);
			final InputStream inStream = p == null ? null : p.getResponseStream();
			if (inStream != null)
			{
				final XMLParser xmlParser = getXMLParser();
				docResponse = xmlParser.parseStream(inStream);
				pushParser(xmlParser);
			}

		}
		return docResponse;
	}

	/**
	 *
	 * @param xmlParser
	 */
	protected void pushParser(final XMLParser xmlParser)
	{
		XMLParserFactory.getFactory().push(xmlParser);
	}

	/**
	 * gets the n'th child node of nodetype <code>nodeType</code> with n = iPos
	 *
	 * @param nodeType the DOM node type to get <blockquote> - copy from KElement -
	 *
	 *        <ul>
	 *        <li>ELEMENT_NODE = 1
	 *        <li>ATTRIBUTE_NODE = 2
	 *        <li>TEXT_NODE = 3
	 *        <li>CDATA_SECTION_NODE = 4
	 *        <li>ENTITY_REFERENCE_NODE = 5
	 *        <li>ENTITY_NODE = 6
	 *        <li>PROCESSING_INSTRUCTION_NODE = 7
	 *        <li>COMMENT_NODE = 8
	 *        <li>DOCUMENT_NODE = 9
	 *        <li>DOCUMENT_TYPE_NODE = 10
	 *        <li>DOCUMENT_FRAGMENT_NODE = 11
	 *        <li>NOTATION_NODE = 12
	 *        <li>XML_DECL_NODE = 13 </blockquote>
	 *        </ul>
	 * @param iPos the index of the node with default 0 for the first occurance
	 * @return KElement: a node that matches the filter, null if iPos is higher then the number of child nodes
	 */
	protected Node getChildNode(final int nodeType, final int iPos)
	{
		Node retNode = null;
		Node node = getFirstChild();
		int i = 0;

		// iPos + 1 because we need to stop after the iPos
		// turn and i will then be iPos + 1
		while ((node != null) && (i != iPos + 1))
		{
			if (node.getNodeType() == nodeType)
			{
				if (i++ == iPos)
				{
					retNode = node;
				}
			}
			node = node.getNextSibling();
		}
		return retNode;
	}

	/**
	 * gets of 'this' the text of the i-th child XMLComment. <code><!-- this is a XMLComment --></code> would return <code>this is a XMLComment</code>
	 *
	 * @return String text document XMLComment, null if i is higher then the number of child nodes
	 */
	public String getXMLComment()
	{
		final Node n = getChildNode(Node.COMMENT_NODE, 0);
		return (n == null) ? null : n.getNodeValue();
	}

	/**
	 *
	 *
	 * @return
	 */
	protected XMLParser getXMLParser()
	{
		return XMLParserFactory.getFactory().get();
	}

	/**
	 * @return Returns the validation result.
	 */
	public XMLDoc getValidationResult()
	{
		return m_doc.m_validationResult;
	}

	/**
	 * @return Returns the m_OriginalFileName.
	 */
	public boolean isSchemaValid()
	{
		return getValidationResult() != null && XMLErrorHandler.VALID.equals(getValidationResult().getRoot().getAttribute(XMLErrorHandler.VALIDATION_RESULT));
	}

	/**
	 * @param url the url to write to
	 * @param strContentType the content type; if null use text/xml
	 * @param det the details to set
	 * @return the HttpURLConnection, null if failure
	 */
	public HttpURLConnection write2HTTPURL(final URL url, final String strContentType, final HTTPDetails det)
	{
		UrlPart p = null;
		for (int i = 0; i < 2; i++) //
		{

			p = write2HttpURL(url, strContentType, det);
			if (p == null && i == 0)
				ThreadUtil.sleep(1000); // wait and retry once
			else
				break;
		}
		return p == null || !(p.getConnection() instanceof HttpURLConnection) ? null : (HttpURLConnection) p.getConnection();
	}

	/**
	 * @param url the url to write to
	 * @param strContentType the content type; if null use text/xml
	 * @param det the details to set
	 * @return the HttpURLConnection, null if failure
	 */
	public UrlPart write2HttpURL(final URL url, String strContentType, final HTTPDetails det)
	{
		if (url == null)
		{
			return null;
		}
		if (strContentType == null)
		{
			strContentType = UrlUtil.TEXT_XML;
		}
		try
		{
			final ByteArrayIOStream ios = new ByteArrayIOStream(1000);
			write2Stream(ios, 0, true);
			return UrlUtil.writeToURL(url.toExternalForm(), ios.getInputStream(), UrlUtil.POST, strContentType, det);
		}
		catch (final IOException e)
		{
			// nop
		}

		return null;
	}

	/**
	 * rough guestimate of the memory used by this if called after parsing
	 *
	 * @return the difference of memory used when calling compared to construction time
	 */
	public long getDocMemoryUsed()
	{
		return m_doc.getDocMemoryUsed();
	}

	/**
	 * get the Javax.Mail.BodyPart
	 *
	 * @return the BodyPart
	 */
	public BodyPart getBodyPart()
	{
		return m_doc.m_Bodypart;
	}

	/**
	 * get the Javax.Mail.Multipart
	 *
	 * @return the Multipart
	 */
	public Multipart getMultiPart()
	{
		final BodyPart bp = getBodyPart();
		if (bp == null)
		{
			return null;
		}
		return bp.getParent();
	}

	/**
	 * set the Javax.Mail.BodyPart
	 *
	 * @param bodyPart the value to set
	 */
	public void setBodyPart(final BodyPart bodyPart)
	{
		m_doc.m_Bodypart = bodyPart;
	}

	/**
	 * set the ZipReader
	 *
	 * @param zip the value to set
	 */
	public void setZipReader(final ZipReader zip)
	{
		m_doc.m_ZipReader = zip;
	}

	/**
	 * get the ZipReader
	 *
	 * @return zip the value to set
	 */
	public ZipReader getZipReader()
	{
		return m_doc.m_ZipReader;
	}

	/**
	 * @return Returns the m_OriginalFileName.
	 */
	public String getOriginalFileName()
	{
		return m_doc.m_OriginalFileName;
	}

	/**
	 * @param originalFileName The OriginalFileName to set.
	 */
	public void setOriginalFileName(final String originalFileName)
	{
		m_doc.m_OriginalFileName = originalFileName;
	}

	/**
	 * @param nsURI the namespace uri to get the schema location for
	 * @return String that corresponds to the schema, null if no schemalocation is defined for nsURI
	 */
	public String getSchemaLocation(final String nsURI)
	{
		final KElement root = getRoot();
		if (root == null)
		{
			return null;
		}
		String schemaloc = root.getAttribute(JDFCoreConstants.SCHEMALOCATION, JDFCoreConstants.XSI, null);
		if (schemaloc == null)
		{
			return null;
		}
		final VString strings = StringUtil.tokenize(schemaloc, null, false);
		final int indexOfNS = strings.indexOf(nsURI);
		if (indexOfNS >= 0 && indexOfNS < strings.size() - 1)
		{
			schemaloc = strings.get(indexOfNS + 1);
		}
		else
		{
			return null;
		}

		return schemaloc;
	}

	/**
	 * @param nsURI the namespace uri to get the schema location for
	 * @return File that corresponds to the schema, null if no readable file is found
	 */
	public File getSchemaLocationFile(final String nsURI)
	{

		final String schemaloc = getSchemaLocation(nsURI);
		if (schemaloc == null)
		{
			return null;
		}

		final File f = UrlUtil.urlToFile(schemaloc);
		if (f != null && f.canRead())
		{
			return f;
		}
		return null;
	}

	/**
	 * set xs:schemalocation to
	 *
	 * @param nsURI
	 * @param _schemaLocation
	 */
	public void setSchemaLocation(final String nsURI, final File _schemaLocation)
	{
		if (_schemaLocation != null && _schemaLocation.length() != 0)
		{
			final String fileToUrl = UrlUtil.fileToUrl(_schemaLocation, false);
			final String schemaLocation = nsURI + JDFConstants.SPACE + fileToUrl;
			final KElement root = getRoot();
			if (root != null)
			{
				root.setAttribute("xs:" + JDFCoreConstants.SCHEMALOCATION, schemaLocation, JDFCoreConstants.XSI);
			}
		}
	}

	/**
	 * @param validationResult the validationResult to set.
	 */
	public void setValidationResult(final XMLDoc validationResult)
	{
		m_doc.m_validationResult = validationResult;
	}

	/**
	 * parse a JDF input stream
	 *
	 * @param is
	 * @return the parsed JDFDoc
	 */
	public static XMLDoc parseStream(final InputStream is)
	{
		if (is == null)
			return null;
		final XMLParserFactory factory = XMLParserFactory.getFactory();
		final XMLParser p = factory.get();
		final XMLDoc d = p.parseStream(is);
		factory.push(p);
		return d;
	}

	/**
	 * parse an XML file
	 *
	 * @param fileName
	 * @return the parsed JDFDoc
	 */
	public static XMLDoc parseFile(final String fileName)
	{
		final XMLParserFactory factory = XMLParserFactory.getFactory();
		final XMLParser p = factory.get();
		final XMLDoc d = p.parseFile(fileName);
		factory.push(p);
		return d;
	}

	/**
	 * parse an XML file
	 *
	 * @param string
	 * @return the parsed JDFDoc
	 */
	public static XMLDoc parseString(final String string)
	{
		final XMLParserFactory factory = XMLParserFactory.getFactory();
		final XMLParser p = factory.get();
		final XMLDoc d = p.parseString(string);
		factory.push(p);
		return d;
	}

	/**
	 * parse an XML file
	 *
	 * @param file
	 * @return the parsed JDFDoc
	 */
	public static XMLDoc parseFile(final File file)
	{
		final XMLParserFactory factory = XMLParserFactory.getFactory();
		final XMLParser p = factory.get();
		final XMLDoc d = p.parseFile(file);
		factory.push(p);
		return d;
	}

	/**
	 * parse an XML file
	 *
	 * @param file
	 * @return the parsed JDFDoc
	 */
	public static XMLDoc parseZipFile(final File file)
	{
		final ZipReader r = new ZipReader(file);
		final XMLDoc doc = r.getXMLDoc();
		r.close();
		return doc;

	}

	/**
	 * parse a given url
	 *
	 * @param url the url to search
	 * @param bp the bodypart that the CID url is located in
	 * @return the parsed JDFDoc
	 */
	public static XMLDoc parseURL(final String url, final BodyPart bp)
	{
		final XMLParserFactory factory = XMLParserFactory.getFactory();
		final XMLParser p = factory.get();
		final InputStream inStream = UrlUtil.getURLInputStream(url, bp);
		XMLDoc d = p.parseStream(inStream);
		if (d != null)
		{
			d = new XMLDoc(d.getMemberDocument());
			final File f = UrlUtil.urlToFile(url);
			if (f != null && f.canRead())
			{
				d.setOriginalFileName(f.getAbsolutePath());
			}
			else
			{
				final String fn = UrlUtil.urlToFileName(url);
				d.setOriginalFileName(fn);
			}
		}
		factory.push(p);
		return d;
	}

	/**
	 * copy metadata from other document
	 *
	 * @param dMine
	 */
	public void copyMeta(final XMLDoc dMine)
	{
		setBodyPart(dMine.getBodyPart());
		setZipReader(dMine.getZipReader());
		setOriginalFileName(dMine.getOriginalFileName());

	}

}