/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 * DocumentJDFImpl.java - JDFElement Factory
 *
 * @author Dietrich Mucha
 *
 *         This method creates at least a KElement !!! (was JDFElement until 11.2005)
 *
 *         Copyright (C) 2003 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */

package org.cip4.jdflib.core;

import java.util.HashMap;
import java.util.Vector;

import javax.mail.BodyPart;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.ParentNode;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.zip.ZipReader;
import org.w3c.dom.Element;

/**
 * implementation of the JDFLib class factory
 * 
 * @author prosirai
 *
 */
public class DocumentXMLImpl extends DocumentImpl
{

	private static final long serialVersionUID = 1L;
	private static boolean bStaticStrictNSCheck = true;
	/**
	 * skip initialization when creating a new element
	 */
	private boolean ignoreNSDefault;
	private boolean strictNSCheck;
	/**
	 * the xml output of the schema validation
	 */
	public XMLDoc m_validationResult = null;

	/**
	 * @return the bStaticStrictNSCheck
	 */
	public static boolean isStaticStrictNSCheck()
	{
		return bStaticStrictNSCheck;
	}

	/**
	 * @param staticStrictNSCheck the bStaticStrictNSCheck to set
	 */
	public static void setStaticStrictNSCheck(final boolean staticStrictNSCheck)
	{
		bStaticStrictNSCheck = staticStrictNSCheck;
	}

	/**
	 * @return the strictNSCheck
	 */
	public boolean isStrictNSCheck()
	{
		return strictNSCheck;
	}

	/**
	 * @param strictNSCheck the strictNSCheck to set
	 */
	public void setStrictNSCheck(final boolean strictNSCheck)
	{
		this.strictNSCheck = strictNSCheck;
	}

	// used mainly for memory debugging purposes
	protected long initialMem;
	boolean bGlobalDirtyFlag = false;
	boolean bGlobalDirtyPolicy = true;
	/**
	 * the original file name if an element was parsed, else null
	 */
	protected String m_OriginalFileName = null;
	/**
	 * the mime bodypart that this document was parsed from
	 */
	protected BodyPart m_Bodypart = null;
	/**
	 * we need this for grabbing local urls in a zip stream
	 */
	protected ZipReader m_ZipReader = null;
	protected final HashMap<String, String> nsMap;

	/**
	 * rough guestimate of the memory used by this if called after parsing
	 *
	 * @return the difference of memory used when calling compared to construction time
	 */
	public long getDocMemoryUsed()
	{
		final Runtime rt = Runtime.getRuntime();
		final long mem = rt.totalMemory() - rt.freeMemory();
		if (mem < initialMem)
		{
			initialMem = mem;
		}
		return mem - initialMem;
	}

	/**
	 * @see org.apache.xerces.dom.CoreDocumentImpl#clone()
	 */
	@Override
	public DocumentXMLImpl clone()
	{
		DocumentXMLImpl clon;
		try
		{
			clon = (DocumentXMLImpl) super.clone();
		}
		catch (final CloneNotSupportedException x)
		{
			clon = new DocumentXMLImpl();
		}
		clon.m_Bodypart = m_Bodypart;
		clon.m_OriginalFileName = m_OriginalFileName;
		clon.docElement = ((KElement) docElement).cloneRoot(new XMLDoc(clon));
		clon.ownerDocument = clon;
		clon.firstChild = clon.docElement;
		clon.nsMap.clear();
		clon.setNSMap(this);
		return clon;
	}

	/**
	 * @param documentJDFImpl
	 */
	void setNSMap(final DocumentXMLImpl documentJDFImpl)
	{
		if (documentJDFImpl == null)
			return;

		nsMap.putAll(documentJDFImpl.nsMap);
		final Element e = getDocumentElement();
		if (e != null)
		{
			final Vector<String> keys = ContainerUtil.getKeyVector(nsMap);
			if (keys != null)
			{
				for (int i = 0; i < keys.size(); i++)
				{
					final String prefix = keys.get(i);
					setRootNSAttribute(prefix, nsMap.get(prefix));
				}
			}
		}
	}

	/**
	 * Constructor for DocumentJDFImpl.
	 */
	public DocumentXMLImpl()
	{
		super();

		final Runtime rt = Runtime.getRuntime();
		initialMem = rt.totalMemory() - rt.freeMemory();
		nsMap = new HashMap<>();
		ignoreNSDefault = false;
		strictNSCheck = bStaticStrictNSCheck;
	}

	/**
	 * Factory method; creates an <code>Element</code> having this <code>Document</code> as its OwnerDoc.
	 *
	 * @param qualifiedName The name of the element type to instantiate. For XML, this is case-sensitive.
	 *
	 */
	@Override
	public Element createElement(final String qualifiedName)
	{
		final String localPart = KElement.xmlnsLocalName(qualifiedName);

		return createElementNS(null, qualifiedName, localPart);
	}

	/**
	 * @see org.apache.xerces.dom.CoreDocumentImpl#createElementNS(java.lang.String, java.lang.String)
	 * @param namespaceURI
	 * @param qualifiedName
	 * @return
	 */
	@Override
	public Element createElementNS(final String namespaceURI, final String qualifiedName)
	{
		final String localPart = KElement.xmlnsLocalName(qualifiedName);
		return createElementNS(namespaceURI, qualifiedName, localPart);
	}

	/**
	 * @see org.apache.xerces.dom.CoreDocumentImpl#createElementNS(java.lang.String, java.lang.String, java.lang.String)
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localPart
	 * @return
	 */
	@Override
	public Element createElementNS(final String namespaceURI, final String qualifiedName, final String localPart)
	{
		return new KElement(this, namespaceURI, qualifiedName, localPart);
	}

	/**
	 * @param parent
	 * @param qualifiedName
	 * @return the new {@link KElement}
	 */
	KElement factoryCreate(final ParentNode parent, final String qualifiedName)
	{
		return (KElement) createElement(qualifiedName);
	}

	/**
	 * @param parent
	 * @param namespaceURI
	 * @param qualifiedName
	 * @return
	 */
	KElement factoryCreate(final ParentNode parent, final String namespaceURI, final String qualifiedName)
	{
		return (KElement) createElementNS(namespaceURI, qualifiedName);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final Element rootElement = getDocumentElement();
		if (rootElement != null)
		{
			return super.toString() + rootElement.toString();
		}
		return super.toString();
	}

	/**
	 * @return the setIgnoreNSDefault; if true no namespaces are collected
	 */
	public boolean isIgnoreNSDefault()
	{
		return ignoreNSDefault;
	}

	/**
	 * if true no namespaces are heuristically gathered
	 *
	 * @param _setIgnoreNSDefault the setIgnoreNSDefault to set
	 */
	public void setIgnoreNSDefault(final boolean _setIgnoreNSDefault)
	{
		this.ignoreNSDefault = _setIgnoreNSDefault;
	}

	/**
	 * @param prefix
	 * @return
	 */
	public String getNamespaceURIFromPrefix(String prefix)
	{
		if (ignoreNSDefault)
			return null;
		if (prefix == null)
			prefix = JDFCoreConstants.COLON;
		return nsMap.get(prefix);
	}

	/**
	 * @param prefix
	 * @param strNamespaceURI
	 */
	public void setNamespaceURIFromPrefix(String prefix, final String strNamespaceURI)
	{
		if (StringUtil.getNonEmpty(prefix) == null)
			prefix = JDFCoreConstants.COLON;
		final String old = nsMap.get(prefix);
		if (old == null)
		{
			setRootNSAttribute(prefix, strNamespaceURI);
			nsMap.put(prefix, strNamespaceURI);
		}
	}

	/**
	 * @param prefix
	 * @param strNamespaceURI
	 */
	private void setRootNSAttribute(final String prefix, final String strNamespaceURI)
	{
		String qualifiedName = "xmlns";
		if (!JDFCoreConstants.COLON.equals(prefix))
			qualifiedName += JDFCoreConstants.COLON + prefix;
		final KElement element = (KElement) getDocumentElement();
		if (element != null)
		{
			element.setAttributeNS(JDFCoreConstants.XMLNSURI, qualifiedName, strNamespaceURI);
		}
	}

}
