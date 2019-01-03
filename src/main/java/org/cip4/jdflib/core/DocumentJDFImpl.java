/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import java.lang.reflect.Constructor;

import org.apache.commons.logging.LogFactory;
import org.apache.xerces.dom.ParentNode;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.thread.MyMutex;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * implementation of the JDFLib class factory
 *
 * @author prosirai
 *
 */
public class DocumentJDFImpl extends DocumentXMLImpl
{

	/**
	 *
	 */
	static final String CORE_KELEMENT = "org.cip4.jdflib.core.KElement";
	static final String CORE_JDFELEMENT = "org.cip4.jdflib.core.JDFElement";
	static final String CORE_JDFRESOURCE = "org.cip4.jdflib.resource.JDFResource";

	private static DocumentData data = new DocumentData();

	private static final long serialVersionUID = 1L;
	private static boolean bStaticStrictNSCheck = true;
	static MyMutex mutex = new MyMutex();
	/**
	 * if true, the factory is bypassed and only KElements are created rather than the typesafe element classes
	 */
	public boolean bKElementOnly = false;
	/**
	 * skip initialization when creating a new element
	 */
	public boolean bInitOnCreate;
	private XMLDocUserData myXMLUserDat;

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

	private boolean bInJDFJMF = false;

	private Node m_ParentNode = null;

	private static final String jdfNSURIPrefix = StringUtil.leftStr(JDFElement.getSchemaURL(), -3);
	private static final String PRINTTALK = "PrintTalk";
	private static final String PRINTTALK_URI = "http://www.printtalk.org/schema_";

	/**
	 * @see org.apache.xerces.dom.CoreDocumentImpl#clone()
	 */
	@Override
	public DocumentJDFImpl clone()
	{
		final DocumentJDFImpl clon = (DocumentJDFImpl) super.clone();
		clon.myXMLUserDat = new XMLDocUserData(clon);
		clon.bInitOnCreate = bInitOnCreate;
		clon.bKElementOnly = bKElementOnly;
		clon.m_Bodypart = m_Bodypart;
		clon.m_ZipReader = m_ZipReader;
		clon.m_OriginalFileName = m_OriginalFileName;
		return clon;
	}

	/**
	 * register new custom class in the factory
	 *
	 * @param strElement local name
	 * @param packagepath package path
	 */
	public static void registerCustomClass(final String strElement, final String packagepath)
	{
		data.registerCustomClass(strElement, packagepath);
	}

	/**
	 * @param parent
	 * @param qualifiedName
	 * @return the new {@link KElement}
	 */
	@Override
	KElement factoryCreate(final ParentNode parent, final String qualifiedName)
	{
		// set the parent in the factory for private Elements
		setParentNode(parent);
		return (KElement) createElement(qualifiedName);
	}

	/**
	 * @param parent
	 * @param namespaceURI
	 * @param qualifiedName
	 * @return
	 */
	@Override
	KElement factoryCreate(final ParentNode parent, final String namespaceURI, final String qualifiedName)
	{
		// set the parent in the factory for private Elements
		setParentNode(parent);
		return (KElement) createElementNS(namespaceURI, qualifiedName);
	}

	/**
	 * Constructor for DocumentJDFImpl.
	 */
	public DocumentJDFImpl()
	{
		super();
		bInitOnCreate = true;
		myXMLUserDat = new XMLDocUserData(this);
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
		Constructor<?> constructi;
		Class<?> classOfConstructor = null;
		if (bKElementOnly)
		{
			return new KElement(this, namespaceURI, qualifiedName, localPart);
		}
		if (!bInJDFJMF)
		{

			if (namespaceURI != null)
				bInJDFJMF = namespaceURI.startsWith(jdfNSURIPrefix);
			bInJDFJMF = bInJDFJMF || ElementName.JDF.equals(localPart) || XJDFConstants.XJDF.equals(localPart) || XJDFConstants.XJMF.equals(localPart) || ElementName.JMF.equals(localPart)
					|| PRINTTALK.equals(localPart);
		}

		synchronized (data.sm_hashCtorElementNS)
		{
			constructi = data.sm_hashCtorElementNS.get(qualifiedName);

			String path = null;
			// otherwhise don't use hashtableentry
			if (constructi == null)// AS 17.09.02
			{
				path = getFactoryClassPath(namespaceURI, qualifiedName, localPart);
				if (path == null)
					path = getSpecialClassPath(namespaceURI, qualifiedName);

				if (path != null)
					constructi = data.sm_hashCtorElementNS.get(path);
			}
			if (constructi == null)// AS 17.09.02
			{
				try
				{
					classOfConstructor = getFactoryClass(namespaceURI, qualifiedName, localPart, path);
					if (classOfConstructor != null)
					{
						final Class<?>[] constructorParameters = { org.apache.xerces.dom.CoreDocumentImpl.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, };

						constructi = classOfConstructor.getDeclaredConstructor(constructorParameters);
						putConstructorToHashMap(qualifiedName, constructi, path);
					}
				}
				catch (final ClassNotFoundException e)
				{
					// internal error
					final String message = "(DocumentJDFImpl.createElementNS) getFactoryClass() " + "class " + e.getMessage() + " could not be created"
							+ " (surplus line in sm_PackageNames or non existing class ???)";
					throw new DOMException(DOMException.NOT_FOUND_ERR, message);
				}
				catch (final NoSuchMethodException e)
				{
					// internal error
					String message = "(DocumentJDFImpl.createElementNS) getDeclaredConstructor() not found: ";
					if (classOfConstructor != null)
					{
						message += classOfConstructor.getName() + "(CoreDocumentImpl, String, String, String)";
					}
					throw new DOMException(DOMException.NOT_FOUND_ERR, message);
				}
			}
		}

		final Object[] constructorArguments = { this, namespaceURI, qualifiedName, localPart };

		final KElement newElement = createKElement(constructi, constructorArguments);

		return newElement == null ? new KElement(this, namespaceURI, qualifiedName, localPart) : newElement;
	}

	/**
	 * @param qualifiedName
	 * @param constructi
	 */
	private void putConstructorToHashMap(final String qualifiedName, final Constructor<?> constructi, final String path)
	{
		// only put the constructor into the map if its not a Resource or an element
		// there are a couple of nodes which can be both resource and element depending on the occurrence
		final String className = constructi.getDeclaringClass().getName();
		final boolean bSpecialClass = isSpecialClass(qualifiedName, className);

		if (bSpecialClass)
		{
			data.sm_hashCtorElementNS.put(qualifiedName, constructi);
		}
		else if (path != null)
		{
			data.sm_hashCtorElementNS.put(path, constructi);
		}
	}

	/**
	 * @param qualifiedName
	 * @param className
	 * @return
	 */
	private boolean isSpecialClass(String qualifiedName, final String className)
	{
		qualifiedName = KElement.xmlnsLocalName(qualifiedName);
		return !data.contextSensitive.contains(qualifiedName) && (className == null || !data.contextSensitive.contains(className));
	}

	/**
	 * Method createKElement
	 *
	 * @param constructi
	 * @param constructorArguments
	 * @return KElement
	 */
	KElement createKElement(final Constructor<?> constructi, final Object[] constructorArguments)
	{

		try
		{
			return (KElement) constructi.newInstance(constructorArguments);
		}
		catch (final Exception e)
		{
			LogFactory.getLog(DocumentJDFImpl.class).error(" Exception caught :", e);

		}
		return null;

	}

	/**
	 * Searches for the matching factory class in sm_PackageNames If a match could not be found then JDFResource.class is returned if the element is in a resource pool else if the element is in the
	 * default name space JDFElement.class is returned else KElement.class is returned
	 *
	 * will return JDFElement.class or JDFResource.class only.
	 *
	 * @param qualifiedName the qualified name of the class
	 * @return
	 */
	public Class<?> getFactoryClass(final String qualifiedName)
	{
		Class<?> packageNameClass = null;

		try
		{
			packageNameClass = getFactoryClass(null, qualifiedName, qualifiedName, null);
		}
		catch (final ClassNotFoundException e)
		{ /**/
		}

		return packageNameClass;
	}

	private Class<?> getFactoryClass(final String strNameSpaceURI, final String qualifiedName, final String localPart, String strClassPath) throws ClassNotFoundException
	{
		Class<?> packageNameClass = data.sm_ClassAlreadyInstantiated.get(qualifiedName);

		if (packageNameClass == null)
		{ // class not found in the buffer! Instantiate it and add it to the buffer
			synchronized (data.sm_PackageNames)
			{
				if (strClassPath == null)
					strClassPath = getFactoryClassPath(strNameSpaceURI, qualifiedName, localPart);

				boolean normalElement = true;
				if (strClassPath == null)
				{
					normalElement = false;
					strClassPath = getSpecialClassPath(strNameSpaceURI, qualifiedName);
				}
				else
				{
					normalElement = isSpecialClass(qualifiedName, strClassPath);
				}
				packageNameClass = data.sm_hashPathToClass.get(strClassPath);
				if (packageNameClass == null)
				{
					try
					{
						packageNameClass = Class.forName(strClassPath);
					}
					catch (final ClassNotFoundException e)
					{
						throw new ClassNotFoundException(e.getMessage(), e);
					}
					data.sm_hashPathToClass.put(strClassPath, packageNameClass);
				}

				if (normalElement || strClassPath.equals(data.sm_PackageNames.get("ResDefault")))
				{
					data.sm_ClassAlreadyInstantiated.put(qualifiedName, packageNameClass);
				}
			}
		}
		return packageNameClass;
	}

	/**
	 * @param strNameSpaceURI
	 * @param qualifiedName
	 * @param localPart
	 * @return
	 */
	private String getFactoryClassPath(final String strNameSpaceURI, final String qualifiedName, final String localPart)
	{
		// we are not yet in a JDF or JMF - it must be a KElement
		if (!bInJDFJMF)
		{
			return CORE_KELEMENT;
		}

		String strClassPath = null;

		if (qualifiedName.endsWith(JDFConstants.LINK)
				// CreateLink and RemoveLink are messages, no links
				&& !ElementName.CREATELINK.equals(qualifiedName) && !ElementName.REMOVELINK.equals(qualifiedName))
		{
			strClassPath = data.sm_PackageNames.get(ElementName.RESOURCELINK);
		}
		else if (qualifiedName.endsWith(JDFConstants.REF) && !qualifiedName.equals(ElementName.TESTREF))
		{
			strClassPath = data.sm_PackageNames.get(ElementName.REFELEMENT);
		}
		else
		{
			strClassPath = data.sm_PackageNames.get(qualifiedName);
			if (strClassPath == null && (null == strNameSpaceURI || (strNameSpaceURI != null && strNameSpaceURI.startsWith(jdfNSURIPrefix)) || JDFConstants.EMPTYSTRING.equals(strNameSpaceURI)))
			{ // the maps only contain local names for jdf - recheck in case of prefix
				strClassPath = data.sm_PackageNames.get(localPart);
			}
		}
		return strClassPath;
	}

	/**
	 * @param nameSpaceURI
	 * @param strName
	 * @return Sting the class name
	 */
	private String getSpecialClassPath(final String nameSpaceURI, String strName)
	{
		final String strClassPath;
		final String strParentNodeClass = (m_ParentNode == null) ? null : m_ParentNode.getClass().getName();
		strName = KElement.xmlnsLocalName(strName);
		if (!isSpecialClass(strName, null))
		{
			if (ElementName.HOLETYPE.equals(strName))
			{
				strClassPath = getHoleTypeClass(strParentNodeClass);
			}
			else if (ElementName.METHOD.equals(strName))
			{
				strClassPath = getMethodClass(strParentNodeClass);
			}
			else if (ElementName.SHAPE.equals(strName))
			{
				strClassPath = getShapeClass(strParentNodeClass);
			}
			else if (ElementName.SURFACE.equals(strName))
			{
				strClassPath = getSurfaceClass(strParentNodeClass);
			}
			else if (ElementName.POSITION.equals(strName))
			{
				strClassPath = getPositionClass(strParentNodeClass);
			}
			else
			{
				// should never get her - needed for compiler happiness
				strClassPath = (nameSpaceURI == null && bInJDFJMF || (nameSpaceURI != null && nameSpaceURI.startsWith(jdfNSURIPrefix))) ? data.sm_PackageNames.get("EleDefault")
						: data.sm_PackageNames.get("OtherNSDefault");
			}
		}
		else
		{
			if (isDeepResource(strName, nameSpaceURI))
			{
				strClassPath = data.sm_PackageNames.get("ResDefault");
			}
			else
			{
				strClassPath = (nameSpaceURI == null && bInJDFJMF || (nameSpaceURI != null && nameSpaceURI.startsWith(jdfNSURIPrefix))) ? data.sm_PackageNames.get("EleDefault")
						: data.sm_PackageNames.get("OtherNSDefault");
			}
		}

		return strClassPath;
	}

	protected String getPositionClass(final String strParentNodeClass)
	{
		final String strClassPath;
		if ("org.cip4.jdflib.resource.JDFEmbossingItem".equals(strParentNodeClass))
		{
			strClassPath = "org.cip4.jdflib.span.JDFXYPairSpan";
		}
		else
		{
			strClassPath = "org.cip4.jdflib.resource.process.JDFPosition";
		}
		return strClassPath;
	}

	protected String getSurfaceClass(final String strParentNodeClass)
	{
		final String strClassPath;
		if ("org.cip4.jdflib.resource.intent.JDFLaminatingIntent".equals(strParentNodeClass))
		{
			strClassPath = "org.cip4.jdflib.span.JDFSpanSurface";
		}
		else
		{
			strClassPath = "org.cip4.jdflib.resource.process.JDFLayout";
		}
		return strClassPath;
	}

	protected String getShapeClass(final String strParentNodeClass)
	{
		final String strClassPath;
		if ("org.cip4.jdflib.resource.intent.JDFBookCase".equals(strParentNodeClass))
		{
			strClassPath = "org.cip4.jdflib.span.JDFSpanShape";
		}
		else
		{
			strClassPath = "org.cip4.jdflib.resource.JDFShapeElement";
		}
		return strClassPath;
	}

	protected String getMethodClass(final String strParentNodeClass)
	{
		final String strClassPath;
		if ("org.cip4.jdflib.resource.intent.JDFInsertingIntent".equals(strParentNodeClass) || "org.cip4.jdflib.resource.JDFInsert".equals(strParentNodeClass))
		{
			strClassPath = "org.cip4.jdflib.span.JDFSpanMethod";
		}
		else
		{
			strClassPath = "org.cip4.jdflib.span.JDFNameSpan";
		}
		return strClassPath;
	}

	protected String getHoleTypeClass(final String strParentNodeClass)
	{
		final String strClassPath;
		if ("org.cip4.jdflib.resource.process.postpress.JDFRingBinding".equals(strParentNodeClass))
		{
			strClassPath = "org.cip4.jdflib.span.JDFSpanHoleType";
		}
		else
		{
			strClassPath = "org.cip4.jdflib.span.JDFStringSpan";
		}
		return strClassPath;
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
	 *
	 *
	 * @param node
	 */
	public void setParentNode(final Node node)
	{
		m_ParentNode = node;
	}

	private boolean isDeepResource(final String strName, final String nameSpaceURI)
	{
		if (m_ParentNode == null)
		{
			return false;
		}
		if (m_ParentNode instanceof JDFResourcePool)
		{
			return true;
		}
		// we assume any foreign ns thingy in resourceInfo is a resource
		if (m_ParentNode instanceof JDFResourceInfo)
		{
			return true;
		}
		if (m_ParentNode instanceof JDFResource) // partitioned resource leaf
		{
			return m_ParentNode.getLocalName().equals(strName);
		}
		return false;
	}

	/**
	 * get/create the associated XMLDocUserData
	 *
	 * @return the XMLDocUserData of this
	 */
	protected XMLDocUserData getXMLDocUserData()
	{
		return (XMLDocUserData) getUserData();
	}

	/**
	 * @see org.apache.xerces.dom.CoreDocumentImpl#removeChild(org.w3c.dom.Node)
	 */
	@Override
	public Node removeChild(final Node arg0) throws DOMException
	{
		final XMLDocUserData ud = getXMLDocUserData();
		if (ud != null)
		{
			ud.clearTargets();
		}

		return super.removeChild(arg0);
	}

	/**
	 * @see org.apache.xerces.dom.CoreDocumentImpl#replaceChild(org.w3c.dom.Node, org.w3c.dom.Node)
	 */
	@Override
	public Node replaceChild(final Node arg0, final Node arg1) throws DOMException
	{
		final XMLDocUserData ud = getXMLDocUserData();
		if (ud != null)
		{
			ud.clearTargets();
		}
		return super.replaceChild(arg0, arg1);
	}

	/**
	 * @return
	 */
	public XMLDocUserData getMyUserData()
	{
		return myXMLUserDat;
	}

}
