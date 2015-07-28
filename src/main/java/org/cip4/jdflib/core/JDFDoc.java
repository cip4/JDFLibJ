/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of 
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
 * JDFDoc.java
 *
 * -------------------------------------------------------------------------------------------------
 */
package org.cip4.jdflib.core;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.mail.BodyPart;

import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlPart;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.UrlUtil.HTTPDetails;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 */
public class JDFDoc extends XMLDoc
{
	// **************************************** Constructors
	// ****************************************
	/**
	 * constructor
	 */
	public JDFDoc()
	{
		super();
		((DocumentJDFImpl) m_doc).bInitOnCreate = true;
		((DocumentJDFImpl) m_doc).bKElementOnly = false;
	}

	/**
	 * constructor from a document
	 * note that the constructor will create a copy of the document in case it is not a typesafe DocumentJDFImpl
	 * 
	 * @param document
	 */
	public JDFDoc(final Document document)
	{
		super(document);
		// we have to reparse type safe
		if ((document instanceof DocumentXMLImpl) && !(document instanceof DocumentJDFImpl))
		{
			reparse(document);
		}
	}

	/**
	 * constructor
	 * note that the constructor will create a copy of the document in case it is not a typesafe DocumentJDFImpl
	 * 
	 * @param document
	 */
	public JDFDoc(final XMLDoc document)
	{
		super(document);
		// we have to reparse type safe
		Document memberDoc = document == null ? null : document.getMemberDocument();
		if ((memberDoc instanceof DocumentXMLImpl) && !(memberDoc instanceof DocumentJDFImpl))
		{
			reparse(memberDoc);
		}
	}

	protected void reparse(final Document document)
	{
		JDFDoc doc = createRoot(document);
		doc.setInitOnCreate(false);
		doc.copyMeta(this);
		KElement newRoot = doc.getRoot();
		if (newRoot != null)
			newRoot.copyInto((KElement) document.getDocumentElement(), false);
		m_doc = doc.m_doc;
	}

	@Override
	protected JDFDoc createRoot(final Document document)
	{
		Element documentElement = document.getDocumentElement();
		return documentElement == null ? new JDFDoc() : new JDFDoc(documentElement.getNodeName());
	}

	/**
	 * constructor
	 * 
	 * @param document
	 */
	public JDFDoc(final DocumentJDFImpl document)
	{
		super(document);
	}

	/**
	 * constructor - create the kind of JDF you need
	 * 
	 * @param strDocType - ElementName.JDF, ElementName.JMF, "Config" ...
	 */
	public JDFDoc(final String strDocType)
	{
		super(strDocType, JDFElement.getSchemaURL());
		((DocumentJDFImpl) m_doc).bInitOnCreate = true;
		((DocumentJDFImpl) m_doc).bKElementOnly = false;
	}

	/**
	 * constructor - create the kind of JDF you need
	 * 
	 * @param strDocType - ElementName.JDF, ElementName.JMF, "Config" ...
	 * @param version 
	 */
	public JDFDoc(final String strDocType, EnumVersion version)
	{
		super(strDocType, JDFElement.getSchemaURL(version));
		((DocumentJDFImpl) m_doc).bInitOnCreate = true;
		((DocumentJDFImpl) m_doc).bKElementOnly = false;
	}

	@Override
	protected DocumentXMLImpl getImpl()
	{
		return new DocumentJDFImpl();
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * GetJDFRoot - get the jdf root
	 * 
	 * @return JDFNode - the root of the JDF file
	 */
	public JDFNode getJDFRoot()
	{
		return (JDFNode) getJXFRoot(ElementName.JDF);
	}

	@Override
	protected void setMemberDoc(final DocumentXMLImpl myDoc)
	{
		super.setMemberDoc(myDoc);
		if (m_doc != null)
		{
			getCreateXMLDocUserData();
		}
	}

	/**
	 * GetJMFRoot - get the jmf root
	 * 
	 * @return JDFJMF - the root of the JMF file
	 */
	public JDFJMF getJMFRoot()
	{
		return (JDFJMF) getJXFRoot(ElementName.JMF);
	}

	/**
	 * @param rootName ElementName.JDF or ElementName.JMF
	 * @return
	 */
	private KElement getJXFRoot(final String rootName)
	{
		KElement root = getRoot();
		if (root == null)
			return null;

		if (!root.getLocalName().equals(rootName))
		{
			root = root.getChildByTagName(rootName, JDFElement.getSchemaURL(), 0, null, true, false);
		}

		return root;
	}

	/**
	 * clone
	 * 
	 * @return JDFDoc the cloned JDFDoc
	 */
	@Override
	public JDFDoc clone()
	{
		return new JDFDoc(super.clone().getMemberDocument());
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFDoc: " + super.toString();
	}

	/**
	 * CreateJDF
	 * 
	 * @param jdfPath
	 * @deprecated simply use constructor
	 * @return JDFDoc
	 */
	@Deprecated
	public static JDFDoc createJDF(final String jdfPath)
	{
		final JDFDoc new_doc = new JDFDoc();
		final JDFNode root = (JDFNode) new_doc.createElement(ElementName.JDF);
		root.setAttribute(AttributeName.ID, KElement.uniqueID(50), JDFConstants.EMPTYSTRING);
		root.init();

		new_doc.appendChild(root);
		new_doc.write2File(jdfPath, 0, true);

		return new_doc;
	}

	/**
	 * returns a JDFNode by its id attribute
	 * 
	 * @param id the ID parameter of the JDF node
	 * @return JDFNode - the corresponding JDF Node, null if no such node exists
	 * @deprecated use getRoot().getTarget(id, AttributeName.ID) and cast.
	 * 
	 */
	@Deprecated
	public JDFNode getJDFNodeByID(final String id)
	{

		return (JDFNode) getRoot().getTarget(id, AttributeName.ID);
	}

	/**
	 * removes all dangling resources and cleans up the rrefs attributes
	 * 
	 * @param nodeNames the list of nodenames to remove, remove all if nodenames is empty
	 * @return the number of collected resources
	 * 
	 * @default CollectGarbageResources(new vString())
	 * @deprecated use JDFNode.eraseUnlinkedResources
	 */
	@Deprecated
	public int collectGarbageResources(final VString nodeNames)
	{
		final boolean bCollectAll = nodeNames == null || nodeNames.isEmpty();

		final VElement vProcs = getJDFRoot().getvJDFNode(null, null, false);
		VElement vResources = new VElement();
		VElement vLinkedResources = new VElement();

		// loop over all jdf nodes
		for (int i = 0; i < vProcs.size(); i++)
		{
			int j;
			final JDFNode n = (JDFNode) vProcs.elementAt(i);
			vLinkedResources.appendUnique(n.getLinkedResources(null, true));

			final JDFResourcePool rp = n.getResourcePool();
			if (rp != null)
			{
				final VElement resources = rp.getPoolChildren(null, null, null);
				vResources.appendUnique(resources);

				for (j = 0; j < resources.size(); j++)
				{
					vResources.appendUnique(((JDFResource) resources.elementAt(j)).getvHRefRes(true, true));
				}
			}
		}

		final VElement vr = new VElement();
		for (int i = 0; i < vResources.size(); i++)
		{
			vr.appendUnique(((JDFResource) vResources.elementAt(i)).getResourceRoot());
		}

		vResources = vr;
		vr.clear();
		for (int i = 0; i < vLinkedResources.size(); i++)
		{
			vr.appendUnique(((JDFResource) vLinkedResources.elementAt(i)).getResourceRoot());
		}
		vLinkedResources = vr;

		int nDeleted = 0;
		for (int i = 0; i < vResources.size(); i++)
		{
			final JDFResource r = (JDFResource) vResources.elementAt(i);
			if (!(vLinkedResources.index(r) >= 0))
			{
				if (bCollectAll || (nodeNames != null && nodeNames.contains(r.getLocalName())))
				{
					r.deleteNode();
					nDeleted++;
				}
			}
		}
		// run gc a few times to really clean up
		Runtime.getRuntime().gc();
		Runtime.getRuntime().gc();

		return nDeleted;
	}

	/**
	 * gets the content type
	 * 
	 * @return the content type, Text/XML if neither jdf nor jmf
	 */
	public String getContentType()
	{
		final KElement e = getRoot();
		final String strContentType;

		if (e instanceof JDFNode)
		{
			strContentType = JDFConstants.MIME_JDF;
		}
		else if (e instanceof JDFJMF)
		{
			strContentType = JDFConstants.MIME_JMF;
		}
		else
		{
			strContentType = JDFConstants.MIME_TEXTXML;
		}
		return strContentType;
	}

	/**
	 * parse a JDF input stream
	 * 
	 * @param is
	 * @return the parsed JDFDoc
	 */
	public static JDFDoc parseStream(InputStream is)
	{
		if (is == null)
			return null;
		final JDFParser p = JDFParserFactory.getFactory().get();
		JDFDoc doc = p.parseStream(is);
		JDFParserFactory.getFactory().push(p);
		return doc;
	}

	/**
	 * parse a JDF file
	 * 
	 * @param file
	 * @return the parsed JDFDoc
	 */
	public static JDFDoc parseFile(final File file)
	{
		final InputStream is = FileUtil.getBufferedInputStream(file);
		final JDFDoc d = parseStream(is);
		if (d != null)
		{
			d.setOriginalFileName(file.getAbsolutePath());
		}
		return d;
	}

	/**
	 * parse a JDF file
	 * 
	 * @param fileName
	 * @return the parsed JDFDoc
	 */
	public static JDFDoc parseFile(final String fileName)
	{
		if (StringUtil.getNonEmpty(fileName) == null)
			return null;
		final File f = new File(fileName);
		return parseFile(f);
	}

	/**
	 * parse a given url
	 * 
	 * @param url the url to search
	 * @param bp the bodypart that the CID url is located in
	 * @return the parsed JDFDoc
	 */
	public static JDFDoc parseURL(final String url, final BodyPart bp)
	{
		final InputStream inStream = UrlUtil.getURLInputStream(url, bp);
		final File f = UrlUtil.urlToFile(url);
		final JDFDoc d = parseStream(inStream);
		if (d != null)
		{
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

	/**
	 * initialize a new root of strDocType in the document called by constructor XMLDoc(String strDocType)
	 * 
	 * @param strDocType qualified tag name of the doc root to create if still empty
	 * @param namespaceURI namespace URI of the doc root
	 * @return KElement - the root element
	 * 
	 * @default setRoot(ElementName.JDF, null)
	 */
	@Override
	public KElement setRoot(final String strDocType, final String namespaceURI)
	{
		final KElement root = super.setRoot(strDocType, namespaceURI);
		if (root != null && ((DocumentJDFImpl) m_doc).bInitOnCreate)
		{
			if (root instanceof JDFNode)
			{
				((JDFNode) root).init();
			}
			else if (root instanceof JDFJMF)
			{
				String comment = "Generated by the CIP4 Java open source JDF Library version : ";
				comment += JDFAudit.software();
				((JDFJMF) root).init();
				((JDFJMF) root).appendXMLComment(comment, null);
			}
		}
		return root;
	}

	/**
	 * register new custom class in the factory
	 * 
	 * @param strElement fully qualified name
	 * @param packagepath package path
	 */
	public static void registerCustomClass(final String strElement, final String packagepath)
	{
		DocumentJDFImpl.registerCustomClass(strElement, packagepath);
	}

	/**
	 * This method sends the contents of this JDFDoc to the URL <code>strURL</code> and receives the response in the returned JDFDoc. the content type is
	 * automagically set to either text/xml for undefined xml or to application/vnd.cip4-jdf+xml or application/vnd.cip4-jmf+xml
	 * 
	 * @param strURL the URL to write to
	 * 
	 * @return docResponse the response received from URL. A Null document if no response was received, or an exception occurred
	 */
	public JDFDoc write2URL(final String strURL)
	{
		final KElement e = getRoot();
		if (e == null)
		{
			return null;
		}
		final String strContentType = getContentType(e);

		final XMLDoc d = super.write2URL(strURL, strContentType);
		return d == null ? null : new JDFDoc(d.getMemberDocument());
	}

	/**get the correct parser for this type of document
	 * 
	 * @return
	 */
	@Override
	protected XMLParser getXMLParser()
	{
		return new JDFParser();
	}

	/**
	 * @param strURL
	 * @param det
	 * @return
	 */
	public HttpURLConnection write2HTTPURL(final URL strURL, final HTTPDetails det)
	{
		final KElement e = getRoot();
		if (e == null)
		{
			return null;
		}
		final String strContentType = getContentType(e);

		return super.write2HTTPURL(strURL, strContentType, det);
	}

	/**
	 * @param strURL
	 * @param det
	 * @return
	 */
	public UrlPart write2HttpURL(final URL strURL, final HTTPDetails det)
	{
		final KElement e = getRoot();
		if (e == null)
		{
			return null;
		}
		final String strContentType = getContentType(e);

		return super.write2HttpURL(strURL, strContentType, det);
	}

	/**
	 * gets the contentType for a given root element
	 * 
	 * @param e
	 * @return
	 */
	public static String getContentType(final KElement e)
	{
		String strContentType = UrlUtil.TEXT_XML;
		if (e instanceof JDFNode)
		{
			strContentType = UrlUtil.VND_JDF;
		}
		else if (e instanceof JDFJMF)
		{
			strContentType = UrlUtil.VND_JMF;
		}
		return strContentType;
	}

	@Override
	public Element createElement(String elementName)
	{
		Element elem = super.createElement(elementName);
		if (elem instanceof KElement)
		{
			if (((DocumentJDFImpl) m_doc).bInitOnCreate)
			{
				((KElement) elem).init();
			}
		}
		return elem;
	}

	/**
	 * Method getXMLDocUserData - get the associated XMLDocUserData
	 * 
	 * @return XMLDocUserData of this object
	 */
	protected XMLDocUserData getXMLDocUserData()
	{
		return ((DocumentJDFImpl) m_doc).getMyUserData();
	}

	/**
	 * does the owner document of this have an associated XMLDocUserData
	 * 
	 * @return true if XMLDocUserData of this exists
	 */
	protected boolean hasXMLDocUserData()
	{
		return ((DocumentJDFImpl) m_doc).getMyUserData() != null;
	}

	/**
	 * get/create the associated XMLDocUserData - it is always there!
	 * 
	 * @return the XMLDocUserData of this
	 */
	public XMLDocUserData getCreateXMLDocUserData()
	{
		return ((DocumentJDFImpl) m_doc).getMyUserData();
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
	 * @return Returns the m_OriginalFileName.
	 */
	public XMLDoc getValidationResult()
	{
		return ((DocumentJDFImpl) m_doc).m_validationResult;
	}

	/**
	 * if true (the default) initialize element when they are created, 
	 * else don't call init() when an element is initially created
	 * 
	 * @param bInitOnCreate
	 */
	public void setInitOnCreate(boolean bInitOnCreate)
	{
		((DocumentJDFImpl) m_doc).bInitOnCreate = bInitOnCreate;
	}

	/**
	 * if true (the default) initialize element when they are created, 
	 * else don't call init() when an element is initially created
	 * 
	 * @return bInitOnCreate
	 */
	public boolean getInitOnCreate()
	{
		return ((DocumentJDFImpl) m_doc).bInitOnCreate;
	}

	/**
	 * getMemberDocument
	 * 
	 * @return the MemberDocument
	 */
	@Override
	public DocumentJDFImpl getMemberDocument()
	{
		return (DocumentJDFImpl) this.m_doc;
	}

	/**
	 * @see org.cip4.jdflib.core.XMLDoc#setXPathValues(org.cip4.jdflib.datatypes.JDFAttributeMap)
	 */
	@Override
	public void setXPathValues(JDFAttributeMap valueMap)
	{
		boolean bOld = getInitOnCreate();
		setInitOnCreate(false);
		super.setXPathValues(valueMap);
		setInitOnCreate(bOld);
	}

}
