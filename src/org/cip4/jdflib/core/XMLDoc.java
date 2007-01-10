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
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import javax.mail.BodyPart;

import org.apache.xerces.dom.ElementDefinitionImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.HashUtil;
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

//TODO: why does XMLDoc know JDFDocImpl??? 

public class XMLDoc
{
    
    private DocumentJDFImpl m_doc;
    


    //**************************************** Constructors ****************************************
    /**
     * constructor
     */
    public XMLDoc()
    {
        m_doc = new DocumentJDFImpl();
        getCreateXMLDocUserData();
    }
    
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        if (!(o instanceof XMLDoc))
            return false;

        XMLDoc d = (XMLDoc) o;
        if (m_doc == null)
            return d.m_doc == null;

        return m_doc.equals(d.m_doc);
    }
    
    public int hashCode() 
    {
        return HashUtil.hashCode(0, this.m_doc);
    }
    
    /**
     * constructor
     *
     * @param Document document
     */
    public XMLDoc(Document document)
    {
        if(document==null)
        {
            throw new JDFException("XMLDoc(Document) null input Document");           
        }         
        else if (document instanceof DocumentJDFImpl)
        {
            m_doc = (DocumentJDFImpl) document;
            getCreateXMLDocUserData();
        }
        else
        {
            String s=document.getClass().toString();
            throw new JDFException("XMLDoc(Document) not implemented; class="+s);
//          m_doc = new DocumentJDFImpl(document);    // does not set all fields
        }
    }
    
    /**
     * constructor
     *
     * @param DocumentJDFImpl document
     */
    public XMLDoc(DocumentJDFImpl document)
    {
        if(document==null)
        {
            throw new JDFException("XMLDoc(DocumentJDFImpl) null input Document");           
        }         
        m_doc = document;
        getCreateXMLDocUserData();
    }
    
    /**
     * constructor
     *
     * @param int strDocType - ElementName.JDF, ElementName.JMF, "Config" ...
     * @deprecated - use XMLDoc(String strDocType, String namespaceURI)
     */
    public XMLDoc(String strDocType)
    {
        m_doc = new DocumentJDFImpl();
        
        setRoot(strDocType);
        getCreateXMLDocUserData();
    }
    
    /**
     * constructor
     *
     * @param int strDocType - ElementName.JDF, ElementName.JMF, "Config" ...
     */
    public XMLDoc(String strDocType, String namespaceURI)
    {
        m_doc = new DocumentJDFImpl();
        
        setRoot(strDocType,namespaceURI);
        getCreateXMLDocUserData();
    }
    
    /**
     * 
     * @param strDocType
     * @return
     * @deprecated - use setRoot(String strDocType, String namespaceURI)
     *
     */
    public KElement setRoot(String strDocType)
    {
        return setRoot(strDocType,JDFElement.getSchemaURL());
    }
    //**************************************** Methods *********************************************
    /**
     * initialize a new root of strDocType in the document
     * called by constructor XMLDoc(String strDocType)
     *
     * @param String strDocType
     * @return JDFElement
     * 
     * default: setRoot(ElementName.JDF, null)
     */
    public KElement setRoot(String strDocType, String namespaceURI)
    {
        KElement root = (JDFElement) m_doc.getDocumentElement();

        if (root != null)
        {
            throw new JDFException("XMLDoc.setRoot:  root already exists: ");
        }

        // create a new document root element
        root = DocumentJDFImpl.factoryCreate(this.m_doc, root, namespaceURI, strDocType);
        if (root != null)
        {
            appendChild(root);
            
            if (strDocType.equals(ElementName.JDF))
            {
                ((JDFNode) root).init();
            }
            else if (strDocType.equals(ElementName.JMF))
            {
                String comment = "Generated by the CIP4 Java open source JDF Library version : ";
                comment += JDFAudit.software();
                ((JDFJMF) root).init();
                ((JDFJMF) root).appendXMLComment(comment);
            }
        }
        
        return root;
    }
    
    
    
    /**
     * getMemberDocument
     *
     * @return Document
     */
    public DocumentJDFImpl getMemberDocument()
    {
        return this.m_doc;
    }
    
    /**
     * Method Flush
     * clean the m_doc and restart from scratch. The root element remains
     * @return boolean true if successful
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
     * @param int indent the indentation of the xml
     *
     * @return String
     */
    public String write2String(int indent)
    {
        String strResult = JDFConstants.EMPTYSTRING;
        ByteArrayOutputStream outStream = null;
        
        try
        {
            outStream = new ByteArrayOutputStream(4096);
            write2Stream(outStream, indent);
            strResult = outStream.toString("UTF-8");
        }
        catch (IOException e)
        {
            System.out.println("write2String: " + 
                    ((outStream != null) 
                            ? outStream.toString()
                                    : "")
                                    + " : " + e);
        }
        finally
        {
            if (outStream != null)
            {
                try
                {
                    outStream.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            } 
        }
        
        return strResult;
    }
    
    /**
     * write2File - write to a file; Create if it doesn't exist always assume utf-8 encoding
     *
     * @param String oFilePath
     * @param int indent
     *
     * @return boolean
     * @deprecated 060419 use write2File(oFilePath, indent, true);
     * default: write2File(String oFilePath, 0)
     */    
    public boolean write2File(String oFilePath, int indent)
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
     * @return boolean
     * 
     * default: write2File(String oFilePath, 0)
     */
    public boolean write2File(String oFilePath, int indent, boolean bPreserveSpace)
    {
        if(oFilePath==null)
            oFilePath=m_doc.m_OriginalFileName;
        
        if(oFilePath==null)
            return false;
        
        boolean fSuccess = true;
        FileOutputStream outStream = null;
        
        try
        {
            final File outFile = new File(oFilePath);
            // ensure having an empty file in case it did not exist
            outFile.delete();
            if (outFile.createNewFile())
            {
                outStream = new FileOutputStream(outFile);
                write2Stream(outStream, indent, bPreserveSpace);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Write2File: " + oFilePath + " : " + e);
            fSuccess = false;
        }
        catch (IOException e)
        {
            System.out.println("Write2File: " + oFilePath + " : " + e);
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
                catch (IOException e1)
                {
                    e1.printStackTrace();
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
    public void write2Stream(OutputStream outStream, int indent) throws IOException
    {
        write2Stream(outStream, indent, true);
    }
    
    public void write2Stream(OutputStream outStream, int indent, boolean bPreserveSpace) throws IOException
    {        
        final OutputFormat format = new OutputFormat(m_doc);
        
        if(bPreserveSpace)
            format.setPreserveSpace(true);
        
        if (indent < 1)
        {
            format.setIndenting(false);
        }
        else
        {
            format.setIndenting(true);
            format.setIndent(indent);
            //TODO remove schema defaulted attributes when serializing
        }
        
        final XMLSerializer serial = new XMLSerializer(outStream, format);
//      serial.setNamespaces(false);    // ###DOM_1_nodes
        serial.setNamespaces(true);    
        serial.asDOMSerializer();
        
        serial.serialize(m_doc);        
    }
    
    /**
     * @deprecated 060306
     * @param elem
     * @param outStream
     * @param indent
     * @throws IOException
     */
    public static void write2StreamStatic(Element elem, OutputStream outStream, int indent) throws IOException
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
    public static void write2StreamStatic(Element elem, OutputStream outStream, int indent, boolean bPreserveSpace) throws IOException
    {
        final Document doc = elem.getOwnerDocument();
        
        final OutputFormat format = new OutputFormat(doc);
        
        if(bPreserveSpace)
            format.setPreserveSpace(true);
        
        if (indent < 1)
        {
            format.setIndenting(false);
        }
        else
        {
            format.setIndenting(true);
            format.setIndent(indent);
            //TODO remove schema defaulted attributes when serializing
        }
        
        final XMLSerializer serial = new XMLSerializer(outStream, format);
//      serial.setNamespaces(false);    // ###DOM_1_nodes
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
     * createElement
     * create a JDFElement that floats in nirvana this must be appended to a node with appendChild 
     * (created in namespace JDFConstants.NONAMESPACE (DOM Level 2))
     * another way would be to use KElement.appendElement(String elementName, String nameSpaceURI)
     *
     * @param String tagName
     *
     * @return Element
     */
    public Element createElement(String elementName)
    {
        Element elem = null;
        
        if (m_doc != null) elem = m_doc.createElementNS(null, elementName);
        
        if (elem instanceof JDFElement) ((JDFElement) elem).init();
        
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
     * @param String data
     *
     * @return Text
     */
    public Text createTextNode(String data)
    {
        return (m_doc == null) ? null : m_doc.createTextNode(data);
    }
    
    /**
     * createComment
     *
     * @param String data
     *
     * @return Comment
     */
    public Comment createComment(String data)
    {
        return (m_doc == null) ? null : m_doc.createComment(data);
    }
    
    /**
     * createCDATASection
     *
     * @param String data
     *
     * @return CDATASection
     */
    public CDATASection createCDATASection(String data)
    {
        return (m_doc == null) ? null : m_doc.createCDATASection(data);
    }
    
    /**
     * sets the processing instruction for an xslt stylesheet
     * @param url the url of the xslt file
     */
    public void setXSLTURL(String url)
    {
        String data="type=\"text/xsl\" href=\""+url+"\"";
        ProcessingInstruction pi=createProcessingInstruction("xml-stylesheet",data);
        insertBefore(pi,getRoot());
    }
    /**
     * createProcessingInstruction
     *
     * @param String target
     * @param String data
     *
     * @return ProcessingInstruction
     */
    public ProcessingInstruction createProcessingInstruction(
            String target,
            String data)
    {
        return (m_doc == null)
        ? null
                : m_doc.createProcessingInstruction(target, data);
    }
    
    /**
     * createAttribute in namespace JDFConstants.NONAMESPACE (DOM Level 2)
     *
     * @param String name
     * TODO fix handling of namespaces
     * @return Attr
     */
    public Attr createAttribute(String name)
    {
        Attr a=null;
        if(m_doc == null)
            throw new JDFException("Creating an attribute on a null document");
        
        if(name.indexOf(":")<0){
            a=m_doc.createAttributeNS(null, name);
        }else{
            if (name.startsWith("xsi:")){
                a=m_doc.createAttributeNS(AttributeName.XSIURI, name);
            }
            else if(name.startsWith("xmlns:"))
            {
                a=m_doc.createAttributeNS(AttributeName.XMLNSURI, name);
            }
            else
            {                   
                a=m_doc.createAttribute(name);
            }
        }
        return a;
    }
    
    /**
     * createEntityReference
     *
     * @param String name
     *
     * @return EntityReference
     */
    public EntityReference createEntityReference(String name)
    {
        return (m_doc == null) ? null : m_doc.createEntityReference(name);
    }
    
    /**
     * getElementsByTagName
     *
     * @param String tagname
     *
     * @return NodeList
     */
    public NodeList getElementsByTagName(String tagname)
    {
        return (m_doc == null) ? null : m_doc.getElementsByTagName(tagname);
    }
    
    /**
     * importNode
     *
     * @param Node importedNode
     * @param boolean deep
     *
     * @return Node
     */
    public Node importNode(Node importedNode, boolean deep)
    {
        return (m_doc == null) ? null : m_doc.importNode(importedNode, deep);
    }
    
    /**
     * createElementNS
     *
     * @param String namespaceURI
     * @param String qualifiedName
     *
     * @return Element
     */
    public Element createElementNS(String namespaceURI, String qualifiedName)
    {
        return (m_doc == null)
        ? null
                : m_doc.createElementNS(namespaceURI, qualifiedName);
    }
    
    /**
     * createAttributeNS
     *
     * @param String namespaceURI
     * @param String qualifiedName
     *
     * @return Attr
     */
    public Attr createAttributeNS(String namespaceURI, String qualifiedName)
    {
        return (m_doc == null)
        ? null
                : m_doc.createAttributeNS(namespaceURI, qualifiedName);
    }
    
    /**
     * getElementsByTagNameNS
     *
     * @param String namespaceURI
     * @param String localName
     *
     * @return NodeList
     */
    public NodeList getElementsByTagNameNS(
            String namespaceURI,
            String myLocalName)
    {
        return (m_doc == null)
        ? null
                : m_doc.getElementsByTagNameNS(namespaceURI, myLocalName);
    }
    
    /**
     * getElementById
     *
     * @param String elementId
     *
     * @return Element
     */
    public Element getElementById(String elementId)
    {
        return (m_doc == null) ? null : m_doc.getElementById(elementId);
    }
    
    /**
     * getNodeName
     *
     * @return String
     */
    public String getNodeName()
    {
        return (m_doc == null) ? null : m_doc.getNodeName();
    }
    
    /**
     * getNodeValue
     *
     * @return String
     */
    public String getNodeValue()
    {
        return (m_doc == null) ? null : m_doc.getNodeValue();
    }
    
    /**
     * setNodeValue
     *
     * @param String nodeValue
     */
    public void setNodeValue(String nodeValue)
    {
        if (m_doc != null)
        {
            m_doc.setNodeValue(nodeValue);
        }
    }
    
    /**
     * getNodeType
     *
     * @return short
     */
    public short getNodeType()
    {
        return (m_doc == null) ? 0 : m_doc.getNodeType();
    }
    
    /**
     * getParentNode
     *
     * @return Node
     */
    public Node getParentNode()
    {
        return (m_doc == null) ? null : m_doc.getParentNode();
    }
    
    /**
     * getChildNodes
     *
     * @return NodeList
     */
    public NodeList getChildNodes()
    {
        return (m_doc == null) ? null : m_doc.getChildNodes();
    }
    
    /**
     * getFirstChild
     *
     * @return Node
     */
    public Node getFirstChild()
    {
        return (m_doc == null) ? null : m_doc.getFirstChild();
    }
    
    /**
     * getLastChild
     *
     * @return Node
     */
    public Node getLastChild()
    {
        return (m_doc == null) ? null : m_doc.getLastChild();
    }
    
    /**
     * getPreviousSibling
     *
     * @return Node
     */
    public Node getPreviousSibling()
    {
        return (m_doc == null) ? null : m_doc.getPreviousSibling();
    }
    
    /**
     * getNextSibling
     *
     * @return Node
     */
    public Node getNextSibling()
    {
        return (m_doc == null) ? null : m_doc.getNextSibling();
    }
    
    /**
     * getAttributes
     *
     * @return NamedNodeMap
     */
    public NamedNodeMap getAttributes()
    {
        return (m_doc == null) ? null : m_doc.getAttributes();
    }
    
    /**
     * insertBefore
     *
     * @param Node newChild
     * @param Node refChild
     *
     * @return Node
     */
    public Node insertBefore(Node newChild, Node refChild)
    {
        return (m_doc == null) ? null : m_doc.insertBefore(newChild, refChild);
    }
    
    /**
     * replaceChild
     *
     * @param Node newChild
     * @param Node oldChild
     *
     * @return Node
     */
    public Node replaceChild(Node newChild, Node oldChild)
    {
        return (m_doc == null) ? null : m_doc.replaceChild(newChild, oldChild);
    }
    
    /**
     * removeChild
     *
     * @param Node oldChild
     *
     * @return Node
     */
    public Node removeChild(Node oldChild)
    {
        return (m_doc == null) ? null : m_doc.removeChild(oldChild);
    }
    
    /**
     * appendChild
     *
     * @param Node newChild
     *
     * @return Node
     */
    public Node appendChild(Node newChild)
    {
        return (m_doc == null) ? null : m_doc.appendChild(newChild);
    }
    
    /**
     * hasChildNodes
     *
     * @return boolean
     */
    public boolean hasChildNodes()
    {
        return (m_doc == null) ? false : m_doc.hasChildNodes();
    }
    
    /**
     * cloneNode
     *
     * @param boolean deep
     *
     * @return Node
     */
    public Node cloneNode(boolean deep)
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
     * isSupported
     *
     * @param String feature
     * @param String version
     *
     * @return boolean
     */
    public boolean isSupported(String feature, String version)
    {
        return (m_doc == null) ? false : m_doc.isSupported(feature, version);
    }
    
    /**
     * getPrefix
     *
     * @return String
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
     * setPrefix
     *
     * @param String prefix
     */
    public void setPrefix(String prefix)
    {
        if (m_doc != null)
        {
            m_doc.setPrefix(prefix);
        }
    }
    
    /**
     * getLocalName
     *
     * @return 
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
     * hasAttributes
     *
     * @return boolean
     */
    public boolean hasAttributes()
    {
        return (m_doc == null) ? false : m_doc.hasAttributes();
    }
    
    /**
     * createDocumentType
     *
     * @param String qualifiedName
     * @param String publicID
     * @param String systemID
     *
     * @return DocumentType
     */
    public DocumentType createDocumentType(
            String qualifiedName,
            String publicID,
            String systemID)
    {
        return (m_doc == null)
        ? null
                : m_doc.createDocumentType(qualifiedName, publicID, systemID);
    }
    
    /**
     * setErrorChecking
     *
     * @param boolean check
     */
    public void setErrorChecking(boolean check)
    {
        if (m_doc != null)
        {
            m_doc.setErrorChecking(check);
        }
    }
    
    /**
     * getErrorChecking
     *
     * @return boolean
     */
    public boolean getErrorChecking()
    {
        return (m_doc == null) ? false : m_doc.getErrorChecking();
    }
    
    /**
     * createEntity
     *
     * @param String name
     *
     * @return Entity
     */
    public Entity createEntity(String name)
    {
        return (m_doc == null) ? null : m_doc.createEntity(name);
    }
    
    /**
     * createNotation
     *
     * @param String name
     *
     * @return Notation
     */
    public Notation createNotation(String name)
    {
        return (m_doc == null) ? null : m_doc.createNotation(name);
    }
    
    /**
     * createElementDefinition
     *
     * @param String name
     *
     * @return ElementDefinitionImpl
     */
    public ElementDefinitionImpl createElementDefinition(String name)
    {
        return (m_doc == null) ? null : m_doc.createElementDefinition(name);
    }
    
    /**
     * putIdentifier
     *
     * @param String idName
     * @param Element element
     */
    public void putIdentifier(String idName, Element element)
    {
        if (m_doc != null)
        {
            m_doc.putIdentifier(idName, element);
        }
    }
    
    /**
     * getIdentifier
     *
     * @param String idName
     *
     * @return Element
     */
    public Element getIdentifier(String idName)
    {
        return (m_doc == null) ? null : m_doc.getIdentifier(idName);
    }
    
    /**
     * removeIdentifier
     *
     * @param String idName
     */
    public void removeIdentifier(String idName)
    {
        if (m_doc != null)
        {
            m_doc.removeIdentifier(idName);
        }
    }
    
    /**
     * getIdentifiers
     *
     * @return Enumeration
     */
    public Enumeration getIdentifiers()
    {
        return (m_doc == null) ? null : m_doc.getIdentifiers();
    }
    
    /**
     * createNodeIterator
     *
     * @param Node       root
     * @param short      whatToShow
     * @param NodeFilter filter
     *
     * @return NodeIterator
     */
    public NodeIterator createNodeIterator(
            Node root,
            short whatToShow,
            NodeFilter filter)
    {
        return (m_doc == null)
        ? null
                : m_doc.createNodeIterator(root, whatToShow, filter);
    }
    
    /**
     * createNodeIterator
     *
     * @param Node       root
     * @param int        whatToShow
     * @param NodeFilter filter
     * @param boolean    entityReferenceExpansion
     *
     * @return NodeIterator
     */
    public NodeIterator createNodeIterator(
            Node root,
            int whatToShow,
            NodeFilter filter,
            boolean entityReferenceExpansion)
    {
        return (m_doc == null)
        ? null
                : m_doc.createNodeIterator(
                        root,
                        whatToShow,
                        filter,
                        entityReferenceExpansion);
    }
    
    /**
     * createTreeWalker
     *
     * @param Node       root
     * @param int        whatToShow
     * @param NodeFilter filter
     *
     * @return TreeWalker
     */
    public TreeWalker createTreeWalker(
            Node root,
            short whatToShow,
            NodeFilter filter)
    {
        return (m_doc == null)
        ? null
                : m_doc.createTreeWalker(root, whatToShow, filter);
    }
    
    /**
     * createTreeWalker
     *
     * @param Node       root
     * @param int        whatToShow
     * @param NodeFilter filter
     * @param boolean    entityReferenceExpansion
     *
     * @return TreeWalker
     */
    public TreeWalker createTreeWalker(
            Node root,
            int whatToShow,
            NodeFilter filter,
            boolean entityReferenceExpansion)
    {
        return (m_doc == null)
        ? null
                : m_doc.createTreeWalker(
                        root,
                        whatToShow,
                        filter,
                        entityReferenceExpansion);
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
     * createEvent
     *
     * @param String type
     *
     * @return Event
     */
    public Event createEvent(String type)
    {
        return (m_doc == null) ? null : m_doc.createEvent(type);
    }
    
    /**
     * clone
     *
     * @return Object
     * throws CloneNotSupportedException
     */
    public Object clone() 
    {
        XMLDoc clon=new XMLDoc();
        if(m_doc!=null)
            clon.m_doc=(DocumentJDFImpl) m_doc.clone();
        return clon;
    }
    
    /**
     * Method getXMLDocUserData - get the associated XMLDocUserData
     * @return XMLDocUserData of this object
     */
    protected XMLDocUserData getXMLDocUserData()
    {
        final XMLDocUserData userData = (XMLDocUserData) m_doc.getUserData();
        
        return userData;
    }
    
    /**
     * does the owner document of this have an associated XMLDocUserData
     * @return true if XMLDocUserData of this exists
     */
    protected boolean hasXMLDocUserData()
    {
        final XMLDocUserData userData = (XMLDocUserData) m_doc.getUserData();
        
        return userData != null;
    }
    
    /**
     * get/create the associated XMLDocUserData
     * @return the XMLDocUserData of this
     */
    public XMLDocUserData getCreateXMLDocUserData()
    {
        XMLDocUserData userData = (XMLDocUserData) m_doc.getUserData();
        if (userData == null)
        {
            userData = new XMLDocUserData();
            m_doc.setUserData(userData);
        }
        
        return userData;
    }
    
    /**
     * delete the XMLDocUserData structure
     * @return void
     */
    protected void deleteUserData()
    {
        final XMLDocUserData userData = (XMLDocUserData) m_doc.getUserData();
        if (userData != null)
        {
            //delete (userData); hopefully the garbage collector will do his stuff
            m_doc.setUserData(null);
        }
    }
    
    /**
     * get a vector of all IDs of elements that are dirty
     * @return VString the vector of element IDs
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
     * @return void
     */
    public void clearDirtyIDs()
    {
        getCreateXMLDocUserData().clearDirtyIDs();
    }
    
    
    /**
     * is the node with ID dirty?
     * @param String id the id to be checked
     * @return boolean true if the node with ID=id is dirty
     */
    protected boolean isDirty(String strID)
    {
        final XMLDocUserData docUserData = getXMLDocUserData();
        return docUserData==null ? false : docUserData.isDirty(strID);
    }
    
    /**
     * toString
     *
     * @return String
     */
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
        catch (IOException e)
        {
            strXML = "XMLDoc.toXML: ### ERROR while serializing " + getClass().getName() + " element.";
        }
        
        return strXML;
    }
    
    protected void setMemberDoc(DocumentJDFImpl myDoc)
    {
        m_doc = myDoc;
        if(m_doc!=null)
            getCreateXMLDocUserData();
    }   
    
    /**
     * This method sends the contents of this XMLDoc to the URL <code>strURL</code> 
     * and receives the response in the returned XMLDoc.
     * 
     * @param strURL            the URL to write to
     * @param strContentType    the content type to write to
     * @param schemaLocation    the location of the schema if validation is required. 
     *                          A non-empty string implies validation
     * 
     * @return docResponse the response received from URL. A Null document if no response was received
     */
    public XMLDoc write2URL(String strURL, String strContentType) throws IOException
    {
        XMLDoc docResponse    = null;
        
        final URL url         = new URL(strURL);
        final String protocol = url.getProtocol(); //file; ftp; http
        if(protocol.equalsIgnoreCase("File"))
        {
            write2File(strURL, 0, true);
        }
        else
        {    
            final URLConnection urlCon = url.openConnection();
            urlCon.setDoOutput(true);
            urlCon.setRequestProperty("Connection", "close");
            urlCon.setRequestProperty("Content-Type", strContentType);
            
            write2Stream(urlCon.getOutputStream(), 0);
            final JDFParser parser = new JDFParser();
            final InputStream inStream = urlCon.getInputStream();
            parser.parseStream(inStream); 
            docResponse = new XMLDoc(parser.getDocument());
        }
        return docResponse;
    }
    
    /**
     * register new custom class in the factory
     * @param strElement local name
     * @param elemClass package path
     */
    public static void registerCustomClass(String strElement, String packagepath)
    {
        DocumentJDFImpl.registerCustomClass(strElement,packagepath);
    }
    
    /**
     * rough guestimate of the memory used by this if called after parsing
     * @return the difference of memory used when calling compared to construction time 
     */
    public long getDocMemoryUsed()
    {
        return m_doc.getDocMemoryUsed();
    }
    
    /**
     * @return Returns the m_OriginalFileName.
     */
    public BodyPart getBodyPart()
    {
        return m_doc.m_Bodypart;
    }
    
    /**
     * @param originalFileName The OriginalFileName to set.
     */
    public void setBodyPart(BodyPart bodyPart)
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
    public void setOriginalFileName(String originalFileName)
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
     * @param originalFileName The OriginalFileName to set.
     */
    public void setValidationResult(XMLDoc validationResult)
    {
        m_doc.m_validationResult = validationResult;
    }
    
}