/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of 
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
 * XMLDoc.java
 *
 * Copyright (c) 2001-2003 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 */
package org.cip4.jdflib.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.mail.BodyPart;
import javax.mail.Multipart;

import org.apache.xerces.dom.ElementDefinitionImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.HashUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.UrlUtil.HTTPDetails;
import org.cip4.jdflib.util.UrlUtil.UrlPart;
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
 * < July 15, 2009
 */
@SuppressWarnings("deprecation")
public class XMLDoc
{

	protected DocumentJDFImpl m_doc;

	// **************************************** Constructors
	// ****************************************
	/**
	 * constructor
	 */
	public XMLDoc()
	{
		m_doc = new DocumentJDFImpl();
		m_doc.bKElementOnly = true;
		getCreateXMLDocUserData();
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
		else if (document instanceof XMLDoc)
		{
			m_doc = ((XMLDoc) document).getMemberDocument();
			getCreateXMLDocUserData();
			if (m_doc == null)
			{
				final String s = document.getClass().toString();
				throw new JDFException("Quite Snafu: null m_doc for class=" + s);
			}
		}
		try
		{
			m_doc = (DocumentJDFImpl) document;
			getCreateXMLDocUserData();
		}
		catch (final Exception e)
		{
			final String s = e.toString();
			throw new JDFException("Snafu: XMLDoc(Document) not implemented; class=" + s);
		}
	}

	/**
	 * constructor
	 * 
	 * @param document
	 */
	public XMLDoc(final DocumentJDFImpl document)
	{
		if (document == null)
		{
			throw new JDFException("XMLDoc(DocumentJDFImpl) null input Document");
		}
		m_doc = document;
		getCreateXMLDocUserData();
	}

	/**
	 * constructor
	 * 
	 * @param strDocType ElementName.JDF, ElementName.JMF, "Config" ...
	 * @deprecated use XMLDoc(String strDocType, String namespaceURI)
	 */
	@Deprecated
	public XMLDoc(final String strDocType)
	{
		new XMLDoc(strDocType, null);
	}

	/**
	 * constructor
	 * 
	 * @param strDocType ElementName.JDF, ElementName.JMF, "Config" ...
	 * @param namespaceURI namespace to be used by the new XMLDoc
	 */
	public XMLDoc(final String strDocType, final String namespaceURI)
	{
		m_doc = new DocumentJDFImpl();
		if (namespaceURI == null)
		{
			String rootNode = KElement.xmlnsLocalName(strDocType);
			m_doc.bKElementOnly = !(ElementName.JDF.equals(rootNode) || ElementName.JMF.equals(rootNode) || XJDF20.rootName.equals(rootNode));
		}
		m_doc.bInitOnCreate = true;
		setRoot(strDocType, namespaceURI);
		getCreateXMLDocUserData();
	}

	/**
	 * 
	 * @param strDocType
	 * @return KElement
	 * @deprecated use setRoot(String strDocType, String namespaceURI)
	 * 
	 */
	@Deprecated
	public KElement setRoot(final String strDocType)
	{
		return setRoot(strDocType, JDFElement.getSchemaURL());
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
		KElement root = (JDFElement) m_doc.getDocumentElement();

		if (root != null)
		{
			throw new JDFException("XMLDoc.setRoot:  root already exists: ");
		}

		// create a new document root element
		root = m_doc.factoryCreate(root, namespaceURI, strDocType);
		if (root != null)
		{
			appendChild(root);
		}
		return root;
	}

	/**
	 * getMemberDocument
	 * 
	 * @return the MemberDocument
	 */
	public DocumentJDFImpl getMemberDocument()
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
	 * default: getRoot()
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
		String strResult = JDFConstants.EMPTYSTRING;
		ByteArrayOutputStream outStream = null;

		try
		{
			outStream = new ByteArrayOutputStream(4096);
			write2Stream(outStream, indent, indent == 0);
			strResult = outStream.toString("UTF-8");
		}
		catch (final IOException e)
		{
			System.out.println("write2String: " + outStream + " : " + e);
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
					// nop
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
	 * @param oFilePath where to write the file
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
			return false;
		}

		return write2File(new File(oFilePath), indent, bPreserveSpace);
	}

	/**
	 * write2File - write to a file; Create if it doesn't exist always assume utf-8 encoding
	 * 
	 * @param file fthe file to write to
	 * @param indent indentation
	 * @param bPreserveSpace if true, preserve whitespace
	 * 
	 * @return boolean - true if successful
	 * 
	 * @default write2File(String oFilePath, 0)
	 */
	public boolean write2File(File file, final int indent, final boolean bPreserveSpace)
	{
		boolean fSuccess = true;
		FileOutputStream outStream = null;

		if (file == null)
		{
			if (getOriginalFileName() != null)
				file = new File(getOriginalFileName());
			else
				return false;
		}

		try
		{
			if (file.isDirectory() && getOriginalFileName() != null)
			{
				final File orig = new File(getOriginalFileName());
				file = new File(file + File.separator + orig.getName());
			}

			// ensure having an empty file in case it did not exist
			file.delete();
			fSuccess = FileUtil.createNewFile(file);
			if (fSuccess)
			{
				outStream = new FileOutputStream(file);
				write2Stream(outStream, indent, bPreserveSpace);
			}
		}
		catch (final FileNotFoundException e)
		{
			System.out.println("Write2File: " + file.getAbsolutePath() + " : " + e);
			fSuccess = false;
		}
		catch (final IOException e)
		{
			System.out.println("Write2File: " + file.getAbsolutePath() + " : " + e);
			fSuccess = false;
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
					// nop
				}
			}
		}

		return fSuccess;
	}

	/**
	 * @deprecated use write2Stream(outStream, indent, true);
	 * @param outStream
	 * @param indent
	 * @throws IOException
	 */
	@Deprecated
	public void write2Stream(final OutputStream outStream, final int indent) throws IOException
	{
		write2Stream(outStream, indent, indent == 0);
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
		for (int i = 0; i < 3; i++)
		{
			try
			{
				final OutputFormat format = new OutputFormat(m_doc);

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
				synchronized (m_doc)
				{
					serial.serialize(m_doc);
				}
				return; // all is well here
			}
			catch (final IOException x)
			{
				if (i >= 3)
				{
					throw x; // try three times, else ciao
				}
				ThreadUtil.sleep((1000 * (i + 1)));
				System.out.println("retry exception " + i + " for " + getOriginalFileName());
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
	 * createElement create a JDFElement that floats in nirvana. This must be appended to a node with appendChild (created in namespace JDFConstants.NONAMESPACE
	 * (DOM Level 2)).<br>
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

		if (elem instanceof KElement)
		{
			if (m_doc.bInitOnCreate)
				((KElement) elem).init();
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
		final String data = "type=\"text/xsl\" href=\"" + url + "\"";
		final ProcessingInstruction pi = createProcessingInstruction("xml-stylesheet", data);
		insertBefore(pi, getRoot());
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
	 * createAttribute in namespace JDFConstants.NONAMESPACE (DOM Level 2)
	 * 
	 * @param name attribute name TODO fix handling of namespaces
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
				a = m_doc.createAttributeNS(AttributeName.XSIURI, name);
			}
			else if (name.startsWith("xmlns:"))
			{
				a = m_doc.createAttributeNS(AttributeName.XMLNSURI, name);
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
	 * @param tagname tag name of the elements to find (JDFConstants.star for all elements)
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
	 * @see <a href="http://xerces.apache.org/xerces-j/apiDocs/org/apache/xerces/dom/NodeImpl.html#isSupported(java.lang.String,%20java.lang.String)"
	 * <a>Xerxes-Documentation</a>
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
	@SuppressWarnings("unchecked")
	public Enumeration getIdentifiers()
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
	 * creats a TreeWalker
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
	 * creats a TreeWalker
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
			clon.m_doc = (DocumentJDFImpl) m_doc.clone();
		}
		return clon;
	}

	/**
	 * Method getXMLDocUserData - get the associated XMLDocUserData
	 * 
	 * @return XMLDocUserData of this object
	 */
	protected XMLDocUserData getXMLDocUserData()
	{
		return m_doc.getMyUserData();
	}

	/**
	 * does the owner document of this have an associated XMLDocUserData
	 * 
	 * @return true if XMLDocUserData of this exists
	 */
	protected boolean hasXMLDocUserData()
	{
		return m_doc.getMyUserData() != null;
	}

	/**
	 * get/create the associated XMLDocUserData - it is always there!
	 * 
	 * @return the XMLDocUserData of this
	 */
	public XMLDocUserData getCreateXMLDocUserData()
	{
		return m_doc.getMyUserData();
	}

	/**
	 * delete the XMLDocUserData structure
	 * 
	 */
	protected void deleteUserData()
	{
		final XMLDocUserData userData = (XMLDocUserData) m_doc.getUserData();
		if (userData != null)
		{
			// delete (userData); hopefully the garbage collector will do his
			// stuff
			m_doc.setUserData(null);
		}
	}

	/**
	 * get a vector of all IDs of elements that are dirty
	 * 
	 * @return VString - the vector of element IDs
	 */
	public VString getDirtyIDs()
	{
		final XMLDocUserData xmlUserData = getXMLDocUserData();
		if (xmlUserData != null)
		{
			return xmlUserData.getDirtyIDs();
		}
		return null;
	}

	/**
	 * clear the vector of all IDs of elements that are dirty
	 */
	public void clearDirtyIDs()
	{
		getCreateXMLDocUserData().clearDirtyIDs();
	}

	/**
	 * is the node with ID dirty?
	 * 
	 * @param strID id the id to be checked
	 * @return boolean - true if the node with ID=id is dirty
	 */
	public boolean isDirty(final String strID)
	{
		final XMLDocUserData docUserData = getXMLDocUserData();
		return docUserData == null ? false : docUserData.isDirty(strID);
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

	/** Encoding. */
	protected static final String sm_strENCODING = "UTF-8";

	/**
	 * toXML
	 * 
	 * @return String
	 */
	public String toXML()
	{
		String strXML = JDFConstants.EMPTYSTRING;
		try
		{
			final StringWriter osw = new StringWriter();
			final KElement thisRoot = this.getRoot();

			final OutputFormat format = new OutputFormat(thisRoot.getOwnerDocument());
			format.setPreserveSpace(true);
			format.setIndenting(true);
			format.setIndent(2);
			format.setEncoding(sm_strENCODING);

			final XMLSerializer serial = new XMLSerializer(osw, format);
			serial.setNamespaces(true);
			serial.asDOMSerializer();

			serial.serialize(this.getDocumentElement());

			strXML = osw.toString();
		}
		catch (final IOException e)
		{
			strXML = "XMLDoc.toXML: ### ERROR while serializing " + getClass().getName() + " element.";
		}

		return strXML;
	}

	protected void setMemberDoc(final DocumentJDFImpl myDoc)
	{
		m_doc = myDoc;
		if (m_doc != null)
		{
			getCreateXMLDocUserData();
		}
	}

	/**
	 * This method sends the contents of this XMLDoc to the URL <code>strURL</code> and receives the response in the returned XMLDoc.
	 * 
	 * @param strURL the URL to write to
	 * @param strContentType the content type to write to
	 * 
	 * @return docResponse the response received from URL. if url is a file, an empty doc is returned
	 * 
	 * A Null document if no response was received, or an exception occurred
	 */
	public XMLDoc write2URL(final String strURL, final String strContentType)
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
			final UrlPart p = write2HttpURL(url, strContentType, null);
			InputStream inStream = p == null ? null : p.getResponseStream();
			if (inStream == null)
			{
				return null;
			}
			final JDFParser parser = new JDFParser();

			parser.parseStream(inStream);
			try
			{
				inStream.close();
			}
			catch (IOException x)
			{
				// nop
			}
			docResponse = parser.getDocument() == null ? null : new XMLDoc(parser.getDocument());
		}

		return docResponse;

	}

	/**
	 * @param url the url to write to
	 * @param strContentType the content type; if null use text/xml
	 * @param det the details to set
	 * @return the HttpURLConnection, null if failure
	 */
	public HttpURLConnection write2HTTPURL(final URL url, String strContentType, final HTTPDetails det)
	{
		UrlPart p = null;
		for (int i = 0; i < 2; i++) //
		{

			p = write2HttpURL(url, strContentType, det);
			if (p == null)
				ThreadUtil.sleep(1000); // wait and retry once
			else
				break;
		}
		return p == null ? null : p.getConnection();
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
			ByteArrayIOStream ios = new ByteArrayIOStream(1000);
			write2Stream(ios, 0, true);
			return UrlUtil.writeToURL(url.toExternalForm(), ios.getInputStream(), UrlUtil.POST, strContentType, det);
		}
		catch (final IOException e)
		{
			//nop
		}

		return null;
	}

	/**
	 * register new custom class in the factory
	 * 
	 * @param strElement local name
	 * @param packagepath package path
	 */
	public static void registerCustomClass(final String strElement, final String packagepath)
	{
		DocumentJDFImpl.registerCustomClass(strElement, packagepath);
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
	 * @return Returns the m_OriginalFileName.
	 */
	public XMLDoc getValidationResult()
	{
		return m_doc.m_validationResult;
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
		String schemaloc = root.getAttribute(AttributeName.SCHEMALOCATION, AttributeName.XSI, null);
		if (schemaloc == null)
		{
			return null;
		}
		final VString strings = StringUtil.tokenize(schemaloc, " ", false);
		final int indexOfNS = strings.indexOf(nsURI);
		if (indexOfNS >= 0 && indexOfNS < strings.size() - 1)
		{
			schemaloc = strings.stringAt(indexOfNS + 1);
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
			final String schemaLocation = nsURI + " " + fileToUrl;
			final KElement root = getRoot();
			if (root != null)
			{
				root.setAttribute("xs:" + AttributeName.SCHEMALOCATION, schemaLocation, AttributeName.XSI);
			}
		}
	}

	/**
	 * if true (the default) initialize element when they are created, 
	 * else don't call init() when an element is initially created
	 * 
	 * @param bInitOnCreate
	 */
	public void setInitOnCreate(boolean bInitOnCreate)
	{
		m_doc.bInitOnCreate = bInitOnCreate;
	}

	/**
	 * if true (the default) initialize element when they are created, 
	 * else don't call init() when an element is initially created
	 * 
	 * @return bInitOnCreate
	 */
	public boolean getInitOnCreate()
	{
		return m_doc.bInitOnCreate;
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
	public static XMLDoc parseStream(InputStream is)
	{
		final JDFParser p = new JDFParser();
		p.bKElementOnly = true;
		XMLDoc d = p.parseStream(is);
		if (d != null)
		{
			d = new XMLDoc(d.getMemberDocument());
		}
		return d;
	}

	/**
	 * parse a JDF file
	 * 
	 * @param fileName
	 * @return the parsed JDFDoc
	 */
	public static XMLDoc parseFile(final String fileName)
	{
		final JDFParser p = new JDFParser();
		p.bKElementOnly = true;
		XMLDoc d = p.parseFile(fileName);
		if (d != null)
		{
			d = new XMLDoc(d.getMemberDocument());
		}
		return d;
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
		final JDFParser p = new JDFParser();
		p.bKElementOnly = true;
		final InputStream inStream = UrlUtil.getURLInputStream(url, bp);
		final File f = UrlUtil.urlToFile(url);
		XMLDoc d = p.parseStream(inStream);
		if (d != null)
		{
			d = new XMLDoc(d.getMemberDocument());
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
		return d;
	}

}