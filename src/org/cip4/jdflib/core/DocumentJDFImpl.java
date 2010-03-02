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
 * DocumentJDFImpl.java - JDFElement Factory
 *
 * @author Dietrich Mucha
 * 
 * This method creates at least a KElement !!! (was JDFElement until 11.2005)
 *
 * Copyright (C) 2003 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */

package org.cip4.jdflib.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Vector;

import javax.mail.BodyPart;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.ParentNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.ContainerUtil;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * implementation of the JDFLib class factory
 * @author prosirai
 * 
 */
public class DocumentJDFImpl extends DocumentImpl
{

	private static final long serialVersionUID = 1L;
	private static boolean bStaticStrictNSCheck = true;
	/**
	 * if true, the factory is bypassed and only KElements are created rather than the typesafe element classes
	 */
	public boolean bKElementOnly = false;
	/**
	 * skip initialization when creating a new element
	 */
	public boolean bInitOnCreate;
	private boolean ignoreNSDefault = false;
	private boolean strictNSCheck = bStaticStrictNSCheck;
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

	private boolean bInJDFJMF = false;

	/** Caches default package name classes of files. */
	private static HashMap<String, String> sm_PackageNames = new HashMap<String, String>();

	/** Caches Classes */
	private static HashMap<String, Class<?>> sm_ClassAlreadyInstantiated = new HashMap<String, Class<?>>();

	/** Caches JDF element constructors (namespace variant) */
	private static HashMap<String, Constructor<?>> sm_hashCtorElementNS = new HashMap<String, Constructor<?>>();

	private Node m_ParentNode = null;

	// used mainly for memory debugging purposes
	private long initialMem;
	boolean bGlobalDirtyFlag = false;
	boolean bGlobalDirtyPolicy = true;
	/**
	 * the original file name if an element was parsed, else null
	 */
	public String m_OriginalFileName = null;
	/**
	 * the xml output of the schema validation
	 */
	public XMLDoc m_validationResult = null;
	/**
	 * the mime bodypart that this document was parsed from
	 */
	public BodyPart m_Bodypart = null;
	private final HashMap<String, String> nsMap;

	private static final String jdfNSURI = JDFElement.getSchemaURL();

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
	public Object clone()
	{
		DocumentJDFImpl clon;
		try
		{
			clon = (DocumentJDFImpl) super.clone();
		}
		catch (final CloneNotSupportedException x)
		{
			clon = new DocumentJDFImpl();
		}
		clon.m_Bodypart = m_Bodypart;
		clon.m_OriginalFileName = m_OriginalFileName;
		clon.docElement = ((KElement) docElement).cloneRoot(new XMLDoc(clon));
		clon.ownerDocument = clon;
		clon.firstChild = clon.docElement;
		clon.bInitOnCreate = bInitOnCreate;
		clon.bKElementOnly = bKElementOnly;
		clon.myXMLUserDat = new XMLDocUserData(this);
		clon.nsMap.clear();
		clon.setNSMap(this);

		if (userData != null)
			userData.clear(); // otherwise, clon is indefinitely retained in userdata of the original document and we have a memory leak problem....
		if (clon.userData != null)
			clon.userData.clear();
		return clon;
	}

	/**
	 * @param documentJDFImpl
	 */
	void setNSMap(DocumentJDFImpl documentJDFImpl)
	{
		if (documentJDFImpl == null)
			return;

		nsMap.putAll(documentJDFImpl.nsMap);
		Element e = getDocumentElement();
		if (e != null)
		{
			Vector<String> keys = ContainerUtil.getKeyVector(nsMap);
			if (keys != null)
			{
				for (int i = 0; i < keys.size(); i++)
				{
					String prefix = keys.get(i);
					setRootNSAttribute(prefix, nsMap.get(prefix));
				}
			}
		}
	}

	// public static String getPackage(String nodeName)
	// {
	// synchronized (sm_PackageNames)
	// {
	// return (String) sm_PackageNames.get(nodeName);
	// }
	// }

	/**
	 * register new custom class in the factory
	 * 
	 * @param strElement local name
	 * @param packagepath package path
	 */
	public static void registerCustomClass(final String strElement, final String packagepath)
	{
		synchronized (sm_PackageNames)
		{
			sm_PackageNames.put(strElement, packagepath);
			sm_ClassAlreadyInstantiated.remove(strElement);
			sm_hashCtorElementNS.remove(strElement);
		}
	}

	/**
	 * @param parent
	 * @param qualifiedName
	 * @return the new {@link KElement}
	 */
	KElement factoryCreate(final ParentNode parent, final String qualifiedName)
	{
		setParentNode(parent); // set the parent in the factory for
		// private Elements
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
		setParentNode(parent); // set the parent in the factory for
		// private Elements
		return (KElement) createElementNS(namespaceURI, qualifiedName);
	}

	/**
	 * Constructor for DocumentJDFImpl.
	 */
	public DocumentJDFImpl()
	{
		super();
		final Runtime rt = Runtime.getRuntime();
		initialMem = rt.totalMemory() - rt.freeMemory();
		nsMap = new HashMap<String, String>();
		bInitOnCreate = true;
		myXMLUserDat = new XMLDocUserData(this);
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
		final String namespaceURI = null;
		final String localPart = KElement.xmlnsLocalName(qualifiedName);

		return createElementNS(namespaceURI, qualifiedName, localPart);
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
		Constructor<?> constructi;
		Class<?> classOfConstructor = null;
		// we are not yet in a JDF or JMF
		if (bKElementOnly)
		{
			return new KElement(this, namespaceURI, qualifiedName, localPart);
		}

		if (!bInJDFJMF && (jdfNSURI.equals(namespaceURI) || ElementName.JDF.equals(localPart) || ElementName.JMF.equals(localPart)))
		{
			bInJDFJMF = true;
		}

		synchronized (sm_hashCtorElementNS)
		{
			constructi = sm_hashCtorElementNS.get(qualifiedName);

			// otherwhise don't use hashtableentry
			if (constructi == null)// AS 17.09.02
			{
				try
				{
					classOfConstructor = getFactoryClass(namespaceURI, qualifiedName, localPart);
					if (classOfConstructor != null)
					{
						final Class<?>[] constructorParameters = { org.apache.xerces.dom.CoreDocumentImpl.class, java.lang.String.class, java.lang.String.class,
								java.lang.String.class, };

						constructi = classOfConstructor.getDeclaredConstructor(constructorParameters);
						putConstructorToHashMap(qualifiedName, constructi);
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

		return newElement;
	}

	/**
	 * @param qualifiedName
	 * @param constructi
	 */
	private void putConstructorToHashMap(final String qualifiedName, final Constructor<?> constructi)
	{
		// only put the constructor into the map if its not a Resource or an element
		// there are a couple of nodes which can be both resource and element depending on the occurrence
		final String className = constructi.getDeclaringClass().getName();
		final boolean bSpecialClass = isSpecialClass(qualifiedName, className);
		// it has to be 1 coreDocImpl plus 3 String Parameters
		if (bSpecialClass && constructi.getParameterTypes().length == 4)
		{
			sm_hashCtorElementNS.put(qualifiedName, constructi);
		}
	}

	/**
	 * @param qualifiedName
	 * @param className
	 * @return
	 */
	private boolean isSpecialClass(final String qualifiedName, final String className)
	{
		final boolean bSpecialClass = !className.endsWith("JDFResource") && !className.endsWith("JDFElement") && !className.endsWith("KElement")
				&& !qualifiedName.equals("HoleType") && !qualifiedName.equals("Method") && !qualifiedName.equals("Shape") && !qualifiedName.equals("Position")
				&& !qualifiedName.equals("Surface");
		return bSpecialClass;
	}

	/**
	 * Method createKElement
	 * 
	 * @param constructi
	 * @param constructorArguments
	 * @return KElement (always != <code>null</code>)
	 */
	private KElement createKElement(final Constructor<?> constructi, final Object[] constructorArguments)
	{
		KElement newElement = null;
		String message = null;

		try
		{
			newElement = (KElement) constructi.newInstance(constructorArguments);
		}
		// re-throw on error is done below
		catch (final IllegalAccessException e)
		{
			message = "(DocumentJDFImpl.createKElement) IllegalAccessException caught :" + e.getMessage();
		}
		catch (final InstantiationException e)
		{
			message = "(DocumentJDFImpl.createKElement) InstantiationException caught (abstract class?) : " + constructi.getName() + "(CoreDocumentImpl, String, String, String)";
		}
		catch (final InvocationTargetException e)
		{
			message = "(DocumentJDFImpl.createKElement) InvocationTargetException caught :" + e.getMessage();
		}
		catch (final Exception e)
		{
			message = "(DocumentJDFImpl.createKElement) Exception caught :" + e.getMessage();
		}

		if (newElement == null)
		{
			if (message == null)
			{
				message = "(DocumentJDFImpl.createKElement) Could not create an element with " + constructi.getName() + "(CoreDocumentImpl, String, String, String)";
			}
			// something went wrong
			throw new DOMException(DOMException.SYNTAX_ERR, message);
		}

		return newElement;
	}

	/**
	 * Searches for the matching factory class in sm_PackageNames If a match could not be found then JDFResource.class is returned if the element is in a
	 * resource pool else if the element is in the default name space JDFElement.class is returned else KElement.class is returned
	 * 
	 * will return JDFElement.class or JDFResource.class only.
	 * @param qualifiedName the qualified name of the class
	 * @return
	 */
	public Class<?> getFactoryClass(final String qualifiedName)
	{
		Class<?> packageNameClass = null;

		try
		{
			packageNameClass = getFactoryClass(null, qualifiedName, qualifiedName);
		}
		catch (final ClassNotFoundException e)
		{ /**/
		}

		return packageNameClass;
	}

	private Class<?> getFactoryClass(final String strNameSpaceURI, final String qualifiedName, final String localPart) throws ClassNotFoundException
	{
		Class<?> packageNameClass = sm_ClassAlreadyInstantiated.get(qualifiedName);

		if (packageNameClass == null)
		{ // class not found in the buffer! Instantiate it and add it to the
			// buffer
			synchronized (sm_PackageNames)
			{
				String strClassPath = getFactoryClassPath(strNameSpaceURI, qualifiedName, localPart);

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

				// assert strClassPath != null
				try
				{
					packageNameClass = Class.forName(strClassPath);
				}
				catch (final ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					throw new ClassNotFoundException(e.getMessage(), e);
				}

				if (normalElement || strClassPath.equals(sm_PackageNames.get("ResDefault")))
				{
					sm_ClassAlreadyInstantiated.put(qualifiedName, packageNameClass);
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
			return "org.cip4.jdflib.core.KElement";
		}

		String strClassPath = null;

		if (qualifiedName.endsWith(JDFConstants.LINK)
		// CreateLink and RemoveLink are messages, no links
				&& !ElementName.CREATELINK.equals(qualifiedName) && !ElementName.REMOVELINK.equals(qualifiedName))
		{
			strClassPath = sm_PackageNames.get(ElementName.RESOURCELINK);
		}
		else if (qualifiedName.endsWith(JDFConstants.REF) && !qualifiedName.equals(ElementName.TESTREF))
		{
			strClassPath = sm_PackageNames.get(ElementName.REFELEMENT);
		}
		else
		{
			strClassPath = sm_PackageNames.get(qualifiedName);
			if (strClassPath == null && (null == strNameSpaceURI || jdfNSURI.equals(strNameSpaceURI) || JDFConstants.EMPTYSTRING.equals(strNameSpaceURI)))
			{ // the maps only contain local names for jdf - recheck in case of prefix
				strClassPath = sm_PackageNames.get(localPart);
			}
		}
		return strClassPath;
	}

	/**
	 * @param nameSpaceURI
	 * @param strName
	 * @return Sting the class name
	 */
	private String getSpecialClassPath(final String nameSpaceURI, final String strName)
	{
		String strClassPath = null;

		String strParentNodeClass = null;
		if (m_ParentNode != null)
		{
			// strParentNodeClass = m_ParentNode.getClass().getCanonicalName();
			strParentNodeClass = m_ParentNode.getClass().getName();
		}

		if (ElementName.HOLETYPE.equals(strName))
		{
			if ("org.cip4.jdflib.resource.process.postpress.JDFRingBinding".equals(strParentNodeClass))
			{
				strClassPath = "org.cip4.jdflib.span.JDFSpanHoleType";
			}
			else
			{
				strClassPath = "org.cip4.jdflib.span.JDFStringSpan";
			}
		}
		else if (ElementName.METHOD.equals(strName))
		{
			if ("org.cip4.jdflib.resource.intent.JDFInsertingIntent".equals(strParentNodeClass) || "org.cip4.jdflib.resource.JDFInsert".equals(strParentNodeClass))
			{
				strClassPath = "org.cip4.jdflib.span.JDFSpanMethod";
			}
			else
			{
				strClassPath = "org.cip4.jdflib.span.JDFNameSpan";
			}
		}
		else if (ElementName.SHAPE.equals(strName))
		{
			if ("org.cip4.jdflib.resource.intent.JDFBookCase".equals(strParentNodeClass))
			{
				strClassPath = "org.cip4.jdflib.span.JDFSpanShape";
			}
			else
			{
				strClassPath = "org.cip4.jdflib.resource.JDFShapeElement";
			}
		}
		else if (ElementName.SURFACE.equals(strName))
		{
			if ("org.cip4.jdflib.resource.intent.JDFLaminatingIntent".equals(strParentNodeClass))
			{
				strClassPath = "org.cip4.jdflib.span.JDFSpanSurface";
			}
			else
			{
				strClassPath = "org.cip4.jdflib.resource.process.JDFLayout";
			}
		}
		else if (ElementName.POSITION.equals(strName))
		{
			if ("org.cip4.jdflib.resource.JDFEmbossingItem".equals(strParentNodeClass))
			{
				strClassPath = "org.cip4.jdflib.span.JDFXYPairSpan";
			}
			else
			{
				strClassPath = "org.cip4.jdflib.resource.process.JDFPosition";
			}
		}
		else
		{
			if (isDeepResource(strName))
			{
				strClassPath = sm_PackageNames.get("ResDefault");
			}
			else
			{
				strClassPath = (nameSpaceURI == null && bInJDFJMF || JDFConstants.JDFNAMESPACE.equals(nameSpaceURI)) ? sm_PackageNames.get("EleDefault") : sm_PackageNames.get("OtherNSDefault");
			}
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

	void setParentNode(final Node node)
	{
		m_ParentNode = node;
	}

	private boolean isDeepResource(final String strName)
	{
		if (m_ParentNode == null)
		{
			return false;
		}
		if (m_ParentNode instanceof JDFResourcePool)
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
	 * Attention! this only sets the initial parent for deep=true
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.apache.xerces.dom.CoreDocumentImpl#importNode(org.w3c.dom.Node, boolean)
	 */
	// TODO revisit setting parent nodes when importing
	// @Override
	// public synchronized Node importNode(final Node importedNode, final boolean deep)
	// {
	// if (importedNode == null)
	// {
	// return null;
	// }
	// synchronized (importedNode)
	// {
	// setParentNode(importedNode.getParentNode());
	// return super.importNode(importedNode, deep);
	// }
	// }
	/**
	 * register all default classes in the factory mapping of the element names which occur during parsing to the corresponding classes
	 * 
	 * @param strElement
	 * @param elemClass
	 */
	static
	{
		sm_PackageNames.put("ResDefault", "org.cip4.jdflib.resource.JDFResource");
		sm_PackageNames.put("EleDefault", "org.cip4.jdflib.core.JDFElement");
		sm_PackageNames.put("OtherNSDefault", "org.cip4.jdflib.core.KElement");
		// sm_PackageNames.put("AttributeName",
		// "org.cip4.jdflib.core.AttributeName");

		// root elements
		sm_PackageNames.put(ElementName.JDF, "org.cip4.jdflib.node.JDFNode");
		sm_PackageNames.put(ElementName.JMF, "org.cip4.jdflib.jmf.JDFJMF");

		sm_PackageNames.put(ElementName.ACKNOWLEDGE, "org.cip4.jdflib.jmf.JDFAcknowledge");
		sm_PackageNames.put(ElementName.ACTION, "org.cip4.jdflib.resource.devicecapability.JDFAction");
		sm_PackageNames.put(ElementName.ACTIONPOOL, "org.cip4.jdflib.resource.devicecapability.JDFActionPool");
		sm_PackageNames.put(ElementName.ADDED, "org.cip4.jdflib.jmf.JDFAdded");
		sm_PackageNames.put(ElementName.ADDRESS, "org.cip4.jdflib.resource.process.JDFAddress");
		sm_PackageNames.put(ElementName.ADHESIVEBINDING, "org.cip4.jdflib.resource.process.postpress.JDFAdhesiveBinding");
		sm_PackageNames.put(ElementName.ADHESIVEBINDINGPARAMS, "org.cip4.jdflib.resource.JDFAdhesiveBindingParams");
		sm_PackageNames.put(ElementName.ADVANCEDPARAMS, "org.cip4.jdflib.resource.process.JDFAdvancedParams");
		sm_PackageNames.put(ElementName.AMOUNT, "org.cip4.jdflib.span.JDFIntegerSpan");
		sm_PackageNames.put(ElementName.AMOUNTPOOL, "org.cip4.jdflib.pool.JDFAmountPool");
		sm_PackageNames.put(ElementName.ANCESTOR, "org.cip4.jdflib.node.JDFAncestor");
		sm_PackageNames.put(ElementName.ANCESTORPOOL, "org.cip4.jdflib.pool.JDFAncestorPool");
		sm_PackageNames.put(ElementName.AND, "org.cip4.jdflib.resource.devicecapability.JDFand");
		sm_PackageNames.put(ElementName.APPROVALDETAILS, "org.cip4.jdflib.resource.process.JDFApprovalDetails");
		sm_PackageNames.put(ElementName.APPROVALPARAMS, "org.cip4.jdflib.resource.process.JDFApprovalParams");
		sm_PackageNames.put(ElementName.APPROVALPERSON, "org.cip4.jdflib.resource.process.JDFApprovalPerson");
		sm_PackageNames.put(ElementName.APPROVALSUCCESS, "org.cip4.jdflib.resource.process.JDFApprovalSuccess");
		sm_PackageNames.put(ElementName.ARGUMENTVALUE, "org.cip4.jdflib.resource.process.JDFArgumentValue");
		sm_PackageNames.put(ElementName.ARTDELIVERY, "org.cip4.jdflib.resource.intent.JDFArtDelivery");
		sm_PackageNames.put(ElementName.ARTDELIVERYDATE, "org.cip4.jdflib.span.JDFTimeSpan");
		sm_PackageNames.put(ElementName.ARTDELIVERYDURATION, "org.cip4.jdflib.span.JDFDurationSpan");
		sm_PackageNames.put(ElementName.ARTDELIVERYINTENT, "org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent");
		sm_PackageNames.put(ElementName.ARTDELIVERYTYPE, "org.cip4.jdflib.resource.intent.JDFArtDeliveryType");
		sm_PackageNames.put(ElementName.ARTHANDLING, "org.cip4.jdflib.span.JDFSpanArtHandling");
		sm_PackageNames.put(ElementName.ASSEMBLY, "org.cip4.jdflib.resource.process.JDFAssembly");
		sm_PackageNames.put(ElementName.ASSEMBLYSECTION, "org.cip4.jdflib.resource.process.JDFAssemblySection");
		// sm_PackageNames.put(ElementName.ASSETLISTCREATION,
		// "org.cip4.jdflib.resource.process.prepress.JDFAssetListCreation");
		sm_PackageNames.put(ElementName.ASSETLISTCREATIONPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFAssetListCreationParams");
		sm_PackageNames.put(ElementName.AUDIT, "org.cip4.jdflib.core.JDFAudit");
		sm_PackageNames.put(ElementName.AUDITPOOL, "org.cip4.jdflib.pool.JDFAuditPool");
		sm_PackageNames.put(ElementName.AUTHENTICATIONCMDPARAMS, "org.cip4.jdflib.jmf.JDFAuthenticationCmdParams");
		sm_PackageNames.put(ElementName.AUTHENTICATIONQUPARAMS, "org.cip4.jdflib.jmf.JDFAuthenticationQuParams");
		sm_PackageNames.put(ElementName.AUTHENTICATIONRESP, "org.cip4.jdflib.jmf.JDFAuthenticationResp");
		sm_PackageNames.put(ElementName.AUTOMATEDOVERPRINTPARAMS, "org.cip4.jdflib.resource.process.JDFAutomatedOverPrintParams");
		sm_PackageNames.put(ElementName.BACKCOATINGS, "org.cip4.jdflib.span.JDFSpanCoatings");
		sm_PackageNames.put(ElementName.BACKCOVERCOLOR, "org.cip4.jdflib.span.JDFSpanNamedColor");
		sm_PackageNames.put(ElementName.BACKCOVERCOLORDETAILS, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.BAND, "org.cip4.jdflib.resource.JDFBand");
		sm_PackageNames.put(ElementName.BARCODE, "org.cip4.jdflib.resource.JDFBarcode");
		sm_PackageNames.put(ElementName.BARCODECOMPPARAMS, "org.cip4.jdflib.resource.process.JDFBarcodeCompParams");
		sm_PackageNames.put(ElementName.BARCODEDETAILS, "org.cip4.jdflib.resource.process.JDFBarcodeDetails");
		sm_PackageNames.put(ElementName.BARCODEREPROPARAMS, "org.cip4.jdflib.resource.process.JDFBarcodeReproParams");
		sm_PackageNames.put(ElementName.BARCODEPRODUCTIONPARAMS, "org.cip4.jdflib.resource.process.JDFBarcodeProductionParams");
		sm_PackageNames.put(ElementName.BASICPREFLIGHTTEST, "org.cip4.jdflib.resource.devicecapability.JDFBasicPreflightTest");
		sm_PackageNames.put(ElementName.BENDINGPARAMS, "org.cip4.jdflib.resource.process.JDFBendingParams");
		sm_PackageNames.put(ElementName.BINDERMATERIAL, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.BINDERBRAND, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.BINDERYSIGNATURE, "org.cip4.jdflib.resource.process.JDFBinderySignature");
		sm_PackageNames.put(ElementName.BINDINGCOLOR, "org.cip4.jdflib.span.JDFSpanNamedColor");
		sm_PackageNames.put(ElementName.BINDINGCOLORDETAILS, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.BINDINGINTENT, "org.cip4.jdflib.resource.intent.JDFBindingIntent");
		sm_PackageNames.put(ElementName.BINDINGLENGTH, "org.cip4.jdflib.span.JDFSpanBindingLength");
		sm_PackageNames.put(ElementName.BINDINGQUALITYMEASUREMENT, "org.cip4.jdflib.resource.process.JDFBindingQualityMeasurement");
		sm_PackageNames.put(ElementName.BINDINGQUALITYPARAMS, "org.cip4.jdflib.resource.process.JDFBindingQualityParams");
		sm_PackageNames.put(ElementName.BINDINGSIDE, "org.cip4.jdflib.span.JDFSpanBindingSide");
		sm_PackageNames.put(ElementName.BINDINGTYPE, "org.cip4.jdflib.span.JDFSpanBindingType");
		sm_PackageNames.put(ElementName.BINDITEM, "org.cip4.jdflib.resource.JDFBindItem");
		sm_PackageNames.put(ElementName.BINDLIST, "org.cip4.jdflib.resource.JDFBindList");
		sm_PackageNames.put(ElementName.BLOCKPREPARATIONPARAMS, "org.cip4.jdflib.resource.JDFBlockPreparationParams");
		sm_PackageNames.put(ElementName.BLOCKTHREADSEWING, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.BOOKCASE, "org.cip4.jdflib.resource.intent.JDFBookCase");
		sm_PackageNames.put(ElementName.BOOLEANEVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFBooleanEvaluation");
		sm_PackageNames.put(ElementName.BOOLEANSTATE, "org.cip4.jdflib.resource.devicecapability.JDFBooleanState");
		sm_PackageNames.put(ElementName.BOXAPPLICATION, "org.cip4.jdflib.resource.process.JDFBoxApplication");
		sm_PackageNames.put(ElementName.BOXARGUMENT, "org.cip4.jdflib.resource.process.JDFBoxArgument");
		sm_PackageNames.put(ElementName.BOXEDQUANTITY, "org.cip4.jdflib.span.JDFIntegerSpan");
		sm_PackageNames.put(ElementName.BOXFOLDACTION, "org.cip4.jdflib.resource.process.JDFBoxFoldAction");
		sm_PackageNames.put(ElementName.BOXFOLDINGPARAMS, "org.cip4.jdflib.resource.process.JDFBoxFoldingParams");
		sm_PackageNames.put(ElementName.BOXPACKINGPARAMS, "org.cip4.jdflib.resource.JDFBoxPackingParams");
		sm_PackageNames.put(ElementName.BOXSHAPE, "org.cip4.jdflib.span.JDFShapeSpan");
		sm_PackageNames.put(ElementName.BOXTOBOXDIFFERENCE, "org.cip4.jdflib.resource.process.JDFBoxToBoxDifference");
		sm_PackageNames.put(ElementName.BRANDNAME, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.BRIGHTNESS, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.BUFFERPARAMS, "org.cip4.jdflib.resource.JDFBufferParams");
		sm_PackageNames.put(ElementName.BUNDLE, "org.cip4.jdflib.resource.JDFBundle");
		sm_PackageNames.put(ElementName.BUNDLEITEM, "org.cip4.jdflib.resource.JDFBundleItem");
		sm_PackageNames.put(ElementName.BUNDLINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFBundlingParams");
		sm_PackageNames.put(ElementName.BUSINESSINFO, "org.cip4.jdflib.resource.process.JDFBusinessInfo");
		sm_PackageNames.put(ElementName.BUYERSUPPLIED, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.BYTEMAP, "org.cip4.jdflib.resource.process.JDFByteMap");

		sm_PackageNames.put(ElementName.CALL, "org.cip4.jdflib.resource.devicecapability.JDFcall");
		sm_PackageNames.put(ElementName.CARTONMAXWEIGHT, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.CARTONQUANTITY, "org.cip4.jdflib.span.JDFIntegerSpan");
		sm_PackageNames.put(ElementName.CARTONSHAPE, "org.cip4.jdflib.span.JDFShapeSpan");
		sm_PackageNames.put(ElementName.CARTONSTRENGTH, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.CASEMAKINGPARAMS, "org.cip4.jdflib.resource.JDFCaseMakingParams");
		sm_PackageNames.put(ElementName.CASINGINPARAMS, "org.cip4.jdflib.resource.JDFCasingInParams");
		sm_PackageNames.put(ElementName.CCITTFAXPARAMS, "org.cip4.jdflib.resource.process.JDFCCITTFaxParams");
		sm_PackageNames.put(ElementName.CERTIFICATE, "org.cip4.jdflib.jmf.JDFCertificate");
		sm_PackageNames.put(ElementName.CHANGEDATTRIBUTE, "org.cip4.jdflib.resource.JDFChangedAttribute");
		sm_PackageNames.put(ElementName.CHANGEDPATH, "org.cip4.jdflib.jmf.JDFChangedPath");
		sm_PackageNames.put(ElementName.CHANNELBINDING, "org.cip4.jdflib.resource.process.postpress.JDFChannelBinding");
		sm_PackageNames.put(ElementName.CHANNELBINDINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFChannelBindingParams");
		sm_PackageNames.put(ElementName.CHANNELBRAND, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.CHOICE, "org.cip4.jdflib.resource.devicecapability.JDFchoice");
		sm_PackageNames.put(ElementName.CIELABMEASURINGFIELD, "org.cip4.jdflib.resource.process.JDFCIELABMeasuringField");
		sm_PackageNames.put(ElementName.CIRCULATION, "org.cip4.jdflib.span.JDFIntegerSpan");
		sm_PackageNames.put(ElementName.COATINGS, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.COILBINDING, "org.cip4.jdflib.resource.process.postpress.JDFCoilBinding");
		sm_PackageNames.put(ElementName.COILBINDINGPARAMS, "org.cip4.jdflib.resource.JDFCoilBindingParams");
		sm_PackageNames.put(ElementName.COILBRAND, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.COILMATERIAL, "org.cip4.jdflib.span.JDFSpanCoilMaterial");
		sm_PackageNames.put(ElementName.COLLATINGITEM, "org.cip4.jdflib.resource.process.JDFCollatingItem");
		sm_PackageNames.put(ElementName.COLLECTINGPARAMS, "org.cip4.jdflib.resource.process.JDFCollectingParams");
		sm_PackageNames.put(ElementName.COLOR, "org.cip4.jdflib.resource.process.JDFColor");
		sm_PackageNames.put(ElementName.COLORANTALIAS, "org.cip4.jdflib.resource.process.JDFColorantAlias");
		sm_PackageNames.put(ElementName.COLORANTCONTROL, "org.cip4.jdflib.resource.process.JDFColorantControl");
		sm_PackageNames.put(ElementName.COLORANTCONVERTPROCESS, "org.cip4.jdflib.core.JDFSeparationList");
		sm_PackageNames.put(ElementName.COLORANTORDER, "org.cip4.jdflib.core.JDFSeparationList");
		sm_PackageNames.put(ElementName.COLORANTPARAMS, "org.cip4.jdflib.core.JDFSeparationList");
		sm_PackageNames.put(ElementName.COLORANTZONEDETAILS, "org.cip4.jdflib.resource.process.JDFColorantZoneDetails");
		sm_PackageNames.put(ElementName.COLORCONTROLSTRIP, "org.cip4.jdflib.resource.process.JDFColorControlStrip");
		sm_PackageNames.put(ElementName.COLORCORRECTIONOP, "org.cip4.jdflib.resource.process.prepress.JDFColorCorrectionOp");
		sm_PackageNames.put(ElementName.COLORCORRECTIONPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFColorCorrectionParams");
		sm_PackageNames.put(ElementName.COLORICCSTANDARD, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.COLORINTENT, "org.cip4.jdflib.resource.intent.JDFColorIntent");
		sm_PackageNames.put(ElementName.COLORMEASUREMENTCONDITIONS, "org.cip4.jdflib.resource.JDFColorMeasurementConditions");
		sm_PackageNames.put(ElementName.COLORNAME, "org.cip4.jdflib.span.JDFSpanNamedColor");
		sm_PackageNames.put(ElementName.COLORNAMEDETAILS, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.COLORPOOL, "org.cip4.jdflib.resource.process.JDFColorPool");
		sm_PackageNames.put(ElementName.COLORSPACECONVERSIONOP, "org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionOp");
		sm_PackageNames.put(ElementName.COLORSPACECONVERSIONPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams");
		sm_PackageNames.put(ElementName.COLORSPACESUBSTITUTE, "org.cip4.jdflib.resource.process.prepress.JDFColorSpaceSubstitute");
		sm_PackageNames.put(ElementName.COLORSRESULTSPOOL, "org.cip4.jdflib.resource.process.JDFColorsResultsPool");
		sm_PackageNames.put(ElementName.COLORSTANDARD, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.COLORSUSED, "org.cip4.jdflib.core.JDFSeparationList");
		sm_PackageNames.put(ElementName.COLORTYPE, "org.cip4.jdflib.span.JDFSpanColorType");
		sm_PackageNames.put(ElementName.COMBBRAND, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.COMCHANNEL, "org.cip4.jdflib.resource.process.JDFComChannel");
		sm_PackageNames.put(ElementName.COMMAND, "org.cip4.jdflib.jmf.JDFCommand");
		sm_PackageNames.put(ElementName.COMMENT, "org.cip4.jdflib.core.JDFComment");
		sm_PackageNames.put(ElementName.COMPANY, "org.cip4.jdflib.resource.process.JDFCompany");
		sm_PackageNames.put(ElementName.COMPONENT, "org.cip4.jdflib.resource.process.JDFComponent");
		sm_PackageNames.put(ElementName.CONSTRAINTVALUE, "org.cip4.jdflib.resource.process.JDFConstraintValue");
		sm_PackageNames.put(ElementName.CONTACT, "org.cip4.jdflib.resource.process.JDFContact");
		sm_PackageNames.put(ElementName.CONTACTCOPYPARAMS, "org.cip4.jdflib.resource.JDFContactCopyParams");
		sm_PackageNames.put(ElementName.CONTAINER, "org.cip4.jdflib.resource.process.JDFContainer");
		sm_PackageNames.put(ElementName.CONTENTDATA, "org.cip4.jdflib.resource.process.JDFContentData");
		sm_PackageNames.put(ElementName.CONTENTLIST, "org.cip4.jdflib.resource.process.JDFContentList");
		sm_PackageNames.put(ElementName.CONTENTMETADATA, "org.cip4.jdflib.resource.process.JDFContentMetaData");
		sm_PackageNames.put(ElementName.CONTENTOBJECT, "org.cip4.jdflib.resource.process.JDFContentObject");
		sm_PackageNames.put(ElementName.CONTROLLERFILTER, "org.cip4.jdflib.jmf.JDFControllerFilter");
		sm_PackageNames.put(ElementName.CONVENTIONALPRINTINGPARAMS, "org.cip4.jdflib.resource.process.JDFConventionalPrintingParams");
		sm_PackageNames.put(ElementName.CONVERTINGCONFIG, "org.cip4.jdflib.resource.process.JDFConvertingConfig");
		sm_PackageNames.put(ElementName.COSTCENTER, "org.cip4.jdflib.resource.process.JDFCostCenter");
		sm_PackageNames.put(ElementName.COUNTERRESET, "org.cip4.jdflib.resource.JDFCounterReset");
		sm_PackageNames.put(ElementName.COVER, "org.cip4.jdflib.resource.process.JDFCover");
		sm_PackageNames.put(ElementName.COVERAGE, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.COVERAPPLICATIONPARAMS, "org.cip4.jdflib.resource.JDFCoverApplicationParams");
		sm_PackageNames.put(ElementName.COVERCOLOR, "org.cip4.jdflib.span.JDFSpanNamedColor");
		sm_PackageNames.put(ElementName.COVERCOLORDETAILS, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.COVERSTYLE, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.CREASE, "org.cip4.jdflib.resource.process.postpress.JDFCrease");
		sm_PackageNames.put(ElementName.CREASINGPARAMS, "org.cip4.jdflib.resource.JDFCreasingParams");
		sm_PackageNames.put(ElementName.CREATED, "org.cip4.jdflib.resource.JDFCreated");
		sm_PackageNames.put(ElementName.CREATELINK, "org.cip4.jdflib.jmf.JDFCreateLink");
		sm_PackageNames.put(ElementName.CREATERESOURCE, "org.cip4.jdflib.jmf.JDFCreateResource");
		sm_PackageNames.put(ElementName.CREDITCARD, "org.cip4.jdflib.resource.JDFCreditCard");
		sm_PackageNames.put(ElementName.CUSTOMERINFO, "org.cip4.jdflib.core.JDFCustomerInfo");
		sm_PackageNames.put(ElementName.CUSTOMERMESSAGE, "org.cip4.jdflib.core.JDFCustomerMessage");
		sm_PackageNames.put(ElementName.CUT, "org.cip4.jdflib.resource.process.postpress.JDFCut");
		sm_PackageNames.put(ElementName.CUTBLOCK, "org.cip4.jdflib.resource.process.JDFCutBlock");
		sm_PackageNames.put(ElementName.CUTMARK, "org.cip4.jdflib.resource.process.postpress.JDFCutMark");
		sm_PackageNames.put(ElementName.CUTTINGPARAMS, "org.cip4.jdflib.resource.JDFCuttingParams");
		sm_PackageNames.put(ElementName.CUTTYPE, "org.cip4.jdflib.span.JDFSpanCutType");
		sm_PackageNames.put(ElementName.CYLINDERLAYOUT, "org.cip4.jdflib.resource.process.JDFCylinderLayout");
		sm_PackageNames.put(ElementName.CYLINDERLAYOUTPREPARATIONPARAMS, "org.cip4.jdflib.resource.process.JDFCylinderLayoutPreparationParams");
		sm_PackageNames.put(ElementName.CYLINDERPOSITION, "org.cip4.jdflib.resource.process.JDFCylinderPosition");
		sm_PackageNames.put(ElementName.DATETIMEEVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFDateTimeEvaluation");
		sm_PackageNames.put(ElementName.DATETIMESTATE, "org.cip4.jdflib.resource.devicecapability.JDFDateTimeState");
		sm_PackageNames.put(ElementName.DCTPARAMS, "org.cip4.jdflib.resource.process.JDFDCTParams");
		sm_PackageNames.put(ElementName.DBMERGEPARAMS, "org.cip4.jdflib.resource.process.JDFDBMergeParams");
		sm_PackageNames.put(ElementName.DBRULES, "org.cip4.jdflib.resource.process.JDFDBRules");
		sm_PackageNames.put(ElementName.DBSCHEMA, "org.cip4.jdflib.resource.JDFDBSchema");
		sm_PackageNames.put(ElementName.DBSELECTION, "org.cip4.jdflib.resource.process.JDFDBSelection");
		sm_PackageNames.put(ElementName.DELETED, "org.cip4.jdflib.resource.JDFDeleted");
		sm_PackageNames.put(ElementName.DELIVERYCHARGE, "org.cip4.jdflib.span.JDFSpanDeliveryCharge");
		sm_PackageNames.put(ElementName.DELIVERYINTENT, "org.cip4.jdflib.resource.intent.JDFDeliveryIntent");
		sm_PackageNames.put(ElementName.DELIVERYPARAMS, "org.cip4.jdflib.resource.process.JDFDeliveryParams");
		sm_PackageNames.put(ElementName.DENSITYMEASURINGFIELD, "org.cip4.jdflib.resource.process.JDFDensityMeasuringField");
		sm_PackageNames.put(ElementName.DEPENDENCIES, "org.cip4.jdflib.resource.process.JDFDependencies");
		sm_PackageNames.put(ElementName.DEVCAP, "org.cip4.jdflib.resource.devicecapability.JDFDevCap");
		sm_PackageNames.put(ElementName.DEVCAPPOOL, "org.cip4.jdflib.resource.devicecapability.JDFDevCapPool");
		sm_PackageNames.put(ElementName.DEVCAPS, "org.cip4.jdflib.resource.devicecapability.JDFDevCaps");
		sm_PackageNames.put(ElementName.DEVELOPINGPARAMS, "org.cip4.jdflib.resource.JDFDevelopingParams");
		sm_PackageNames.put(ElementName.DEVICE, "org.cip4.jdflib.resource.JDFDevice");
		sm_PackageNames.put(ElementName.DEVICECAP, "org.cip4.jdflib.resource.devicecapability.JDFDeviceCap");
		sm_PackageNames.put(ElementName.DEVICECOLORANTORDER, "org.cip4.jdflib.core.JDFSeparationList");
		sm_PackageNames.put(ElementName.DEVICEFILTER, "org.cip4.jdflib.jmf.JDFDeviceFilter");
		sm_PackageNames.put(ElementName.DEVICEINFO, "org.cip4.jdflib.jmf.JDFDeviceInfo");
		sm_PackageNames.put(ElementName.DEVICELIST, "org.cip4.jdflib.resource.JDFDeviceList");
		sm_PackageNames.put(ElementName.DEVICEMARK, "org.cip4.jdflib.resource.JDFDeviceMark");
		sm_PackageNames.put(ElementName.DEVICENCOLOR, "org.cip4.jdflib.resource.process.JDFDeviceNColor");
		sm_PackageNames.put(ElementName.DEVICENSPACE, "org.cip4.jdflib.resource.process.JDFDeviceNSpace");
		sm_PackageNames.put(ElementName.DIELAYOUT, "org.cip4.jdflib.resource.process.JDFDieLayout");
		sm_PackageNames.put(ElementName.DIELAYOUTPRODUCTIONPARAMS, "org.cip4.jdflib.resource.process.JDFDieLayoutProductionParams");
		sm_PackageNames.put(ElementName.DIGITALDELIVERYPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFDigitalDeliveryParams");
		sm_PackageNames.put(ElementName.DIGITALMEDIA, "org.cip4.jdflib.resource.process.JDFDigitalMedia");
		sm_PackageNames.put(ElementName.DIGITALPRINTINGPARAMS, "org.cip4.jdflib.resource.process.JDFDigitalPrintingParams");
		sm_PackageNames.put(ElementName.DIMENSIONS, "org.cip4.jdflib.span.JDFXYPairSpan");
		sm_PackageNames.put(ElementName.DIRECTION, "org.cip4.jdflib.span.JDFSpanDirection");
		sm_PackageNames.put(ElementName.DISJOINTING, "org.cip4.jdflib.resource.process.JDFDisjointing");
		sm_PackageNames.put(ElementName.DISPLAYGROUP, "org.cip4.jdflib.resource.devicecapability.JDFDisplayGroup");
		sm_PackageNames.put(ElementName.DISPLAYGROUPPOOL, "org.cip4.jdflib.resource.devicecapability.JDFDisplayGroupPool");
		sm_PackageNames.put(ElementName.DISPOSITION, "org.cip4.jdflib.resource.process.JDFDisposition");
		sm_PackageNames.put(ElementName.DIVIDINGPARAMS, "org.cip4.jdflib.resource.process.JDFDividingParams");
		sm_PackageNames.put(ElementName.DOCUMENTRESULTSPOOL, "org.cip4.jdflib.resource.process.JDFDocumentResultsPool");
		sm_PackageNames.put(ElementName.DOTSIZE, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.DROP, "org.cip4.jdflib.resource.process.JDFDrop");
		sm_PackageNames.put(ElementName.DROPINTENT, "org.cip4.jdflib.resource.intent.JDFDropIntent");
		sm_PackageNames.put(ElementName.DROPITEM, "org.cip4.jdflib.resource.process.JDFDropItem");
		sm_PackageNames.put(ElementName.DROPITEMINTENT, "org.cip4.jdflib.resource.intent.JDFDropItemIntent");
		sm_PackageNames.put(ElementName.DURATIONEVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFDurationEvaluation");
		sm_PackageNames.put(ElementName.DURATIONSTATE, "org.cip4.jdflib.resource.devicecapability.JDFDurationState");
		sm_PackageNames.put(ElementName.DYNAMICFIELD, "org.cip4.jdflib.resource.process.JDFDynamicField");
		sm_PackageNames.put(ElementName.DYNAMICINPUT, "org.cip4.jdflib.resource.process.JDFDynamicInput");

		sm_PackageNames.put(ElementName.EARLIEST, "org.cip4.jdflib.span.JDFTimeSpan");
		sm_PackageNames.put(ElementName.EARLIESTDURATION, "org.cip4.jdflib.span.JDFDurationSpan");
		sm_PackageNames.put(ElementName.EDGEANGLE, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.EDGEGLUE, "org.cip4.jdflib.span.JDFSpanGlue");
		sm_PackageNames.put(ElementName.EDGEGLUING, "org.cip4.jdflib.resource.JDFEdgeGluing");
		sm_PackageNames.put(ElementName.EDGESHAPE, "org.cip4.jdflib.span.JDFSpanEdgeShape");
		sm_PackageNames.put(ElementName.ELEMENTCOLORPARAMS, "org.cip4.jdflib.resource.process.JDFElementColorParams");
		sm_PackageNames.put(ElementName.EMBOSS, "org.cip4.jdflib.resource.JDFEmboss");
		sm_PackageNames.put(ElementName.EMBOSSINGINTENT, "org.cip4.jdflib.resource.intent.JDFEmbossingIntent");
		sm_PackageNames.put(ElementName.EMBOSSINGITEM, "org.cip4.jdflib.resource.JDFEmbossingItem");
		sm_PackageNames.put(ElementName.EMBOSSINGPARAMS, "org.cip4.jdflib.resource.JDFEmbossingParams");
		sm_PackageNames.put(ElementName.EMBOSSINGTYPE, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.EMPLOYEE, "org.cip4.jdflib.resource.process.JDFEmployee");
		sm_PackageNames.put(ElementName.EMPLOYEEDEF, "org.cip4.jdflib.jmf.JDFEmployeeDef");
		sm_PackageNames.put(ElementName.ENDSHEET, "org.cip4.jdflib.resource.process.postpress.JDFEndSheet");
		sm_PackageNames.put(ElementName.ENDSHEETGLUINGPARAMS, "org.cip4.jdflib.resource.JDFEndSheetGluingParams");
		sm_PackageNames.put(ElementName.ENDSHEETS, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.ENUMERATIONEVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFEnumerationEvaluation");
		sm_PackageNames.put(ElementName.ENUMERATIONSTATE, "org.cip4.jdflib.resource.devicecapability.JDFEnumerationState");
		sm_PackageNames.put(ElementName.ERROR, "org.cip4.jdflib.resource.JDFError");
		sm_PackageNames.put(ElementName.ERRORDATA, "org.cip4.jdflib.resource.JDFErrorData");
		sm_PackageNames.put(ElementName.EVENT, "org.cip4.jdflib.resource.JDFEvent");
		sm_PackageNames.put(ElementName.EXPOSEDMEDIA, "org.cip4.jdflib.resource.process.JDFExposedMedia");
		sm_PackageNames.put(ElementName.EXPR, "org.cip4.jdflib.resource.process.JDFExpr");
		sm_PackageNames.put(ElementName.EXTENDEDADDRESS, "org.cip4.jdflib.core.JDFComment");
		sm_PackageNames.put(ElementName.EXTERNALIMPOSITIONTEMPLATE, "org.cip4.jdflib.resource.process.JDFExternalImpositionTemplate");
		sm_PackageNames.put(ElementName.EXTRAVALUES, "org.cip4.jdflib.resource.process.JDFExtraValues");

		sm_PackageNames.put(ElementName.FCNKEY, "org.cip4.jdflib.resource.JDFFCNKey");
		sm_PackageNames.put(ElementName.FEATUREATTRIBUTE, "org.cip4.jdflib.resource.devicecapability.JDFFeatureAttribute");
		sm_PackageNames.put(ElementName.FEATUREPOOL, "org.cip4.jdflib.resource.devicecapability.JDFFeaturePool");
		sm_PackageNames.put(ElementName.FEEDER, "org.cip4.jdflib.resource.process.JDFFeeder");
		sm_PackageNames.put(ElementName.FEEDERQUALITYPARAMS, "org.cip4.jdflib.resource.process.JDFFeederQualityParams");
		sm_PackageNames.put(ElementName.FEEDINGPARAMS, "org.cip4.jdflib.resource.process.JDFFeedingParams");
		sm_PackageNames.put(ElementName.FILEALIAS, "org.cip4.jdflib.resource.process.JDFFileAlias");
		sm_PackageNames.put(ElementName.FILESPEC, "org.cip4.jdflib.resource.process.JDFFileSpec");
		sm_PackageNames.put(ElementName.FILETYPERESULTSPOOL, "org.cip4.jdflib.resource.process.prepress.JDFFileTypeResultsPool");
		sm_PackageNames.put(ElementName.FINISHEDDIMENSIONS, "org.cip4.jdflib.span.JDFShapeSpan");
		sm_PackageNames.put(ElementName.FINISHEDGRAINDIRECTION, "org.cip4.jdflib.span.JDFSpanFinishedGrainDirection");
		sm_PackageNames.put(ElementName.FITPOLICY, "org.cip4.jdflib.resource.JDFFitPolicy");
		sm_PackageNames.put(ElementName.FLATEPARAMS, "org.cip4.jdflib.resource.process.JDFFlateParams");
		sm_PackageNames.put(ElementName.FLUSHEDRESOURCES, "org.cip4.jdflib.jmf.JDFFlushedResources");
		sm_PackageNames.put(ElementName.FLUSHQUEUEINFO, "org.cip4.jdflib.jmf.JDFFlushQueueInfo");
		sm_PackageNames.put(ElementName.FLUSHQUEUEPARAMS, "org.cip4.jdflib.jmf.JDFFlushQueueParams");
		sm_PackageNames.put(ElementName.FLUSHRESOURCEPARAMS, "org.cip4.jdflib.jmf.JDFFlushResourceParams");
		sm_PackageNames.put(ElementName.FLUTE, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.FLUTEDIRECTION, "org.cip4.jdflib.span.JDFSpanFluteDirection");
		sm_PackageNames.put(ElementName.FOILCOLOR, "org.cip4.jdflib.span.JDFSpanNamedColor");
		sm_PackageNames.put(ElementName.FOILCOLORDETAILS, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.FOLD, "org.cip4.jdflib.resource.process.postpress.JDFFold");
		sm_PackageNames.put(ElementName.FOLDERPRODUCTION, "org.cip4.jdflib.resource.process.JDFFolderProduction");
		sm_PackageNames.put(ElementName.FOLDINGCATALOG, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.FOLDINGINTENT, "org.cip4.jdflib.resource.intent.JDFFoldingIntent");
		sm_PackageNames.put(ElementName.FOLDINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFFoldingParams");
		sm_PackageNames.put(ElementName.FOLDINGWIDTH, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.FOLDINGWIDTHBACK, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.FOLDOPERATION, "org.cip4.jdflib.resource.process.JDFFoldOperation");
		sm_PackageNames.put(ElementName.FONTPARAMS, "org.cip4.jdflib.resource.process.JDFFontParams");
		sm_PackageNames.put(ElementName.FONTPOLICY, "org.cip4.jdflib.resource.process.JDFFontPolicy");
		sm_PackageNames.put(ElementName.FONTSRESULTSPOOL, "org.cip4.jdflib.resource.process.prepress.JDFFontsResultsPool");
		sm_PackageNames.put(ElementName.FORMATCONVERSIONPARAMS, "org.cip4.jdflib.resource.JDFFormatConversionParams");
		sm_PackageNames.put(ElementName.FREQUENCY, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.FREQUENCYSELECTION, "org.cip4.jdflib.span.JDFSpanFrequencySelection");
		sm_PackageNames.put(ElementName.FRONTCOATINGS, "org.cip4.jdflib.span.JDFSpanCoatings");

		sm_PackageNames.put(ElementName.GANGCMDFILTER, "org.cip4.jdflib.jmf.JDFGangCmdFilter");
		sm_PackageNames.put(ElementName.GANGINFO, "org.cip4.jdflib.jmf.JDFGangInfo");
		sm_PackageNames.put(ElementName.GANGQUFILTER, "org.cip4.jdflib.jmf.JDFGangQuFilter");
		sm_PackageNames.put(ElementName.GATHERINGPARAMS, "org.cip4.jdflib.resource.JDFGatheringParams");
		sm_PackageNames.put(ElementName.GENERALID, "org.cip4.jdflib.resource.process.JDFGeneralID");
		sm_PackageNames.put(ElementName.GLUE, "org.cip4.jdflib.resource.process.postpress.JDFGlue");
		sm_PackageNames.put(ElementName.GLUEAPPLICATION, "org.cip4.jdflib.resource.process.postpress.JDFGlueApplication");
		sm_PackageNames.put(ElementName.GLUELINE, "org.cip4.jdflib.resource.process.postpress.JDFGlueLine");
		sm_PackageNames.put(ElementName.GLUEPROCEDURE, "org.cip4.jdflib.span.JDFSpanGlueProcedure");
		sm_PackageNames.put(ElementName.GLUETYPE, "org.cip4.jdflib.span.JDFSpanGlueType");
		sm_PackageNames.put(ElementName.GLUINGPARAMS, "org.cip4.jdflib.resource.JDFGluingParams");
		sm_PackageNames.put(ElementName.GRADE, "org.cip4.jdflib.span.JDFIntegerSpan");
		sm_PackageNames.put(ElementName.GRAINDIRECTION, "org.cip4.jdflib.span.JDFSpanGrainDirection");
		sm_PackageNames.put(ElementName.HALFTONE, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.HARDCOVERBINDING, "org.cip4.jdflib.resource.JDFHardCoverBinding");
		sm_PackageNames.put(ElementName.HEADBANDAPPLICATIONPARAMS, "org.cip4.jdflib.resource.JDFHeadBandApplicationParams");
		sm_PackageNames.put(ElementName.HEADBANDCOLOR, "org.cip4.jdflib.span.JDFSpanNamedColor");
		sm_PackageNames.put(ElementName.HEADBANDCOLORDETAILS, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.HEADBANDS, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.HEIGHT, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.HOLE, "org.cip4.jdflib.resource.process.postpress.JDFHole");
		sm_PackageNames.put(ElementName.HOLECOUNT, "org.cip4.jdflib.span.JDFIntegerSpan");
		sm_PackageNames.put(ElementName.HOLELINE, "org.cip4.jdflib.resource.JDFHoleLine");
		sm_PackageNames.put(ElementName.HOLELIST, "org.cip4.jdflib.resource.process.postpress.JDFHoleList");
		sm_PackageNames.put(ElementName.HOLEMAKINGINTENT, "org.cip4.jdflib.resource.intent.JDFHoleMakingIntent");
		sm_PackageNames.put(ElementName.HOLEMAKINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams");
		// "HoleType" is context sensitive, see handleOtherElements() and
		// putConstructorToHashMap()
		sm_PackageNames.put(ElementName.ICON, "org.cip4.jdflib.resource.JDFIcon");
		sm_PackageNames.put(ElementName.ICONLIST, "org.cip4.jdflib.resource.JDFIconList");
		sm_PackageNames.put(ElementName.IDENTICAL, "org.cip4.jdflib.resource.process.JDFIdentical");
		sm_PackageNames.put(ElementName.IDENTIFICATIONFIELD, "org.cip4.jdflib.resource.process.JDFIdentificationField");
		sm_PackageNames.put(ElementName.IDINFO, "org.cip4.jdflib.jmf.JDFIDInfo");
		sm_PackageNames.put(ElementName.IDPCOVER, "org.cip4.jdflib.resource.JDFIDPCover");
		sm_PackageNames.put(ElementName.IDPFINISHING, "org.cip4.jdflib.resource.process.JDFIDPFinishing");
		sm_PackageNames.put(ElementName.IDPFOLDING, "org.cip4.jdflib.resource.process.JDFIDPFolding");
		sm_PackageNames.put(ElementName.IDPHOLEMAKING, "org.cip4.jdflib.resource.process.JDFIDPHoleMaking");
		sm_PackageNames.put(ElementName.IDPIMAGESHIFT, "org.cip4.jdflib.resource.JDFIDPImageShift");
		sm_PackageNames.put(ElementName.IDPJOBSHEET, "org.cip4.jdflib.resource.JDFIDPJobSheet");
		sm_PackageNames.put(ElementName.IDPLAYOUT, "org.cip4.jdflib.resource.process.JDFIDPLayout");
		sm_PackageNames.put(ElementName.IDPRINTINGPARAMS, "org.cip4.jdflib.resource.process.press.JDFIDPrintingParams");
		sm_PackageNames.put(ElementName.IDPSTITCHING, "org.cip4.jdflib.resource.process.JDFIDPStitching");
		sm_PackageNames.put(ElementName.IDPTRIMMING, "org.cip4.jdflib.resource.process.JDFIDPTrimming");
		sm_PackageNames.put(ElementName.IMAGECOMPRESSION, "org.cip4.jdflib.resource.JDFImageCompression");
		sm_PackageNames.put(ElementName.IMAGECOMPRESSIONPARAMS, "org.cip4.jdflib.resource.process.JDFImageCompressionParams");
		sm_PackageNames.put(ElementName.IMAGEREPLACEMENTPARAMS, "org.cip4.jdflib.resource.process.JDFImageReplacementParams");
		sm_PackageNames.put(ElementName.IMAGESETTERPARAMS, "org.cip4.jdflib.resource.process.JDFImageSetterParams");
		sm_PackageNames.put(ElementName.IMAGESHIFT, "org.cip4.jdflib.resource.JDFImageShift");
		sm_PackageNames.put(ElementName.IMAGESIZE, "org.cip4.jdflib.span.JDFXYPairSpan");
		sm_PackageNames.put(ElementName.IMAGESRESULTSPOOL, "org.cip4.jdflib.resource.process.JDFImagesResultsPool");
		sm_PackageNames.put(ElementName.IMAGESTRATEGY, "org.cip4.jdflib.span.JDFSpanImageStrategy");
		sm_PackageNames.put(ElementName.INK, "org.cip4.jdflib.resource.process.prepress.JDFInk");
		sm_PackageNames.put(ElementName.INKMANUFACTURER, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.INKZONECALCULATIONPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFInkZoneCalculationParams");
		sm_PackageNames.put(ElementName.INKZONEPROFILE, "org.cip4.jdflib.resource.process.prepress.JDFInkZoneProfile");
		sm_PackageNames.put(ElementName.INSERT, "org.cip4.jdflib.resource.JDFInsert");
		sm_PackageNames.put(ElementName.INSERTINGINTENT, "org.cip4.jdflib.resource.intent.JDFInsertingIntent");
		sm_PackageNames.put(ElementName.INSERTINGPARAMS, "org.cip4.jdflib.resource.JDFInsertingParams");
		sm_PackageNames.put(ElementName.INSERTLIST, "org.cip4.jdflib.resource.JDFInsertList");
		sm_PackageNames.put(ElementName.INSERTSHEET, "org.cip4.jdflib.resource.process.JDFInsertSheet");
		sm_PackageNames.put(ElementName.INTEGEREVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFIntegerEvaluation");
		sm_PackageNames.put(ElementName.INTEGERSTATE, "org.cip4.jdflib.resource.devicecapability.JDFIntegerState");
		sm_PackageNames.put(ElementName.INTENTRESOURCE, "org.cip4.jdflib.resource.intent.JDFIntentResource");
		sm_PackageNames.put(ElementName.INTERPRETEDPDLDATA, "org.cip4.jdflib.resource.process.JDFInterpretedPDLData");
		sm_PackageNames.put(ElementName.INTERPRETINGPARAMS, "org.cip4.jdflib.resource.JDFInterpretingParams");
		sm_PackageNames.put(ElementName.ISPRESENTEVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFIsPresentEvaluation");
		sm_PackageNames.put(ElementName.ISSUEDATE, "org.cip4.jdflib.span.JDFTimeSpan");
		sm_PackageNames.put(ElementName.ISSUENAME, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.ISSUETYPE, "org.cip4.jdflib.span.JDFNameSpan");

		sm_PackageNames.put(ElementName.JACKET, "org.cip4.jdflib.span.JDFSpanJacket");
		sm_PackageNames.put(ElementName.JACKETFOLDINGWIDTH, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.JACKETINGPARAMS, "org.cip4.jdflib.resource.JDFJacketingParams");
		sm_PackageNames.put(ElementName.JAPANBIND, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.JBIG2PARAMS, "org.cip4.jdflib.resource.process.JDFJBIG2Params");
		sm_PackageNames.put(ElementName.JDFCONTROLLER, "org.cip4.jdflib.jmf.JDFJDFController");
		sm_PackageNames.put(ElementName.JDFSERVICE, "org.cip4.jdflib.jmf.JDFJDFService");
		sm_PackageNames.put(ElementName.JOBFIELD, "org.cip4.jdflib.resource.JDFJobField");
		sm_PackageNames.put(ElementName.JOBPHASE, "org.cip4.jdflib.jmf.JDFJobPhase");
		sm_PackageNames.put(ElementName.JOBSHEET, "org.cip4.jdflib.resource.JDFJobSheet");
		sm_PackageNames.put(ElementName.JPEG2000PARAMS, "org.cip4.jdflib.resource.process.JDFJPEG2000Params");
		sm_PackageNames.put(ElementName.KNOWNMSGQUPARAMS, "org.cip4.jdflib.jmf.JDFKnownMsgQuParams");
		sm_PackageNames.put(ElementName.LABELINGPARAMS, "org.cip4.jdflib.resource.JDFLabelingParams");
		sm_PackageNames.put(ElementName.LAMINATED, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.LAMINATINGINTENT, "org.cip4.jdflib.resource.intent.JDFLaminatingIntent");
		sm_PackageNames.put(ElementName.LAMINATINGPARAMS, "org.cip4.jdflib.resource.JDFLaminatingParams");
		sm_PackageNames.put(ElementName.LAYERDETAILS, "org.cip4.jdflib.resource.JDFLayerDetails");
		sm_PackageNames.put(ElementName.LAYERLIST, "org.cip4.jdflib.resource.JDFLayerList");
		sm_PackageNames.put(ElementName.LAYOUT, "org.cip4.jdflib.resource.process.JDFLayout");
		sm_PackageNames.put(ElementName.LAYOUTELEMENT, "org.cip4.jdflib.resource.process.JDFLayoutElement");
		sm_PackageNames.put(ElementName.LAYOUTELEMENTPART, "org.cip4.jdflib.resource.process.JDFLayoutElementPart");
		sm_PackageNames.put(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS, "org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams");
		sm_PackageNames.put(ElementName.LAYOUTINTENT, "org.cip4.jdflib.resource.intent.JDFLayoutIntent");
		sm_PackageNames.put(ElementName.LAYOUTPREPARATIONPARAMS, "org.cip4.jdflib.resource.JDFLayoutPreparationParams");
		sm_PackageNames.put(ElementName.LAYOUTSHIFT, "org.cip4.jdflib.resource.process.JDFLayoutShift");
		sm_PackageNames.put(ElementName.LEVEL, "org.cip4.jdflib.span.JDFSpanLevel");
		sm_PackageNames.put(ElementName.LOC, "org.cip4.jdflib.resource.devicecapability.JDFLoc");
		sm_PackageNames.put(ElementName.LOCATION, "org.cip4.jdflib.resource.JDFLocation");
		sm_PackageNames.put(ElementName.LONGFOLD, "org.cip4.jdflib.resource.process.JDFLongFold");
		sm_PackageNames.put(ElementName.LONGGLUE, "org.cip4.jdflib.resource.process.JDFLongGlue");
		sm_PackageNames.put(ElementName.LONGITUDINALRIBBONOPERATIONPARAMS, "org.cip4.jdflib.resource.process.JDFLongitudinalRibbonOperationParams");
		sm_PackageNames.put(ElementName.LONGPERFORATE, "org.cip4.jdflib.resource.process.JDFLongPerforate");
		sm_PackageNames.put(ElementName.LONGSLIT, "org.cip4.jdflib.resource.process.JDFLongSlit");
		sm_PackageNames.put(ElementName.LOT, "org.cip4.jdflib.resource.process.JDFLot");
		sm_PackageNames.put(ElementName.LZWPARAMS, "org.cip4.jdflib.resource.process.JDFLZWParams");

		sm_PackageNames.put(ElementName.MACRO, "org.cip4.jdflib.resource.devicecapability.JDFmacro");
		sm_PackageNames.put(ElementName.MACROPOOL, "org.cip4.jdflib.resource.devicecapability.JDFMacroPool");
		sm_PackageNames.put(ElementName.MANUALLABORPARAMS, "org.cip4.jdflib.resource.process.JDFManualLaborParams");
		sm_PackageNames.put(ElementName.MARKOBJECT, "org.cip4.jdflib.resource.JDFMarkObject");
		sm_PackageNames.put(ElementName.MARKACTIVATION, "org.cip4.jdflib.resource.JDFMarkActivation");
		sm_PackageNames.put(ElementName.MATERIAL, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.MATRIXEVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFMatrixEvaluation");
		sm_PackageNames.put(ElementName.MATRIXSTATE, "org.cip4.jdflib.resource.devicecapability.JDFMatrixState");
		sm_PackageNames.put(ElementName.MEDIA, "org.cip4.jdflib.resource.process.JDFMedia");
		sm_PackageNames.put(ElementName.MEDIACOLOR, "org.cip4.jdflib.span.JDFSpanNamedColor");
		sm_PackageNames.put(ElementName.MEDIACOLORDETAILS, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.MEDIAINTENT, "org.cip4.jdflib.resource.intent.JDFMediaIntent");
		sm_PackageNames.put(ElementName.MEDIALAYERS, "org.cip4.jdflib.resource.process.JDFMediaLayers");
		sm_PackageNames.put(ElementName.MEDIAQUALITY, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.MEDIASOURCE, "org.cip4.jdflib.resource.process.JDFMediaSource");
		sm_PackageNames.put(ElementName.MEDIATYPE, "org.cip4.jdflib.span.JDFSpanMediaType");
		sm_PackageNames.put(ElementName.MEDIATYPEDETAILS, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.MEDIAUNIT, "org.cip4.jdflib.span.JDFSpanMediaUnit");
		sm_PackageNames.put(ElementName.MERGED, "org.cip4.jdflib.resource.JDFMerged");
		sm_PackageNames.put(ElementName.MESSAGE, "org.cip4.jdflib.jmf.JDFMessage");
		sm_PackageNames.put(ElementName.MESSAGESERVICE, "org.cip4.jdflib.jmf.JDFMessageService");
		sm_PackageNames.put(ElementName.METADATAMAP, "org.cip4.jdflib.resource.process.JDFMetadataMap");
		// "Method" is context sensitive, see handleOtherElements() and
		// putConstructorToHashMap()
		sm_PackageNames.put(ElementName.MILESTONE, "org.cip4.jdflib.resource.JDFMilestone");
		sm_PackageNames.put(ElementName.MISCCONSUMABLE, "org.cip4.jdflib.resource.process.JDFMiscConsumable");
		sm_PackageNames.put(ElementName.MISDETAILS, "org.cip4.jdflib.resource.process.JDFMISDetails");
		sm_PackageNames.put(ElementName.MODIFIED, "org.cip4.jdflib.resource.JDFModified");
		sm_PackageNames.put(ElementName.MODIFYNODECMDPARAMS, "org.cip4.jdflib.jmf.JDFModifyNodeCmdParams");
		sm_PackageNames.put(ElementName.MODULE, "org.cip4.jdflib.resource.devicecapability.JDFModule");
		sm_PackageNames.put(ElementName.MODULECAP, "org.cip4.jdflib.resource.devicecapability.JDFModuleCap");
		sm_PackageNames.put(ElementName.MODULEPHASE, "org.cip4.jdflib.resource.JDFModulePhase");
		sm_PackageNames.put(ElementName.MODULEPOOL, "org.cip4.jdflib.resource.devicecapability.JDFModulePool");
		sm_PackageNames.put(ElementName.MODULESTATUS, "org.cip4.jdflib.resource.JDFModuleStatus");
		sm_PackageNames.put(ElementName.MOVERESOURCE, "org.cip4.jdflib.jmf.JDFMoveResource");
		sm_PackageNames.put(ElementName.MSGFILTER, "org.cip4.jdflib.jmf.JDFMsgFilter");
		sm_PackageNames.put(ElementName.NAMEEVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFNameEvaluation");
		sm_PackageNames.put(ElementName.NAMESTATE, "org.cip4.jdflib.resource.devicecapability.JDFNameState");
		sm_PackageNames.put(ElementName.NEWCOMMENT, "org.cip4.jdflib.jmf.JDFNewComment");
		sm_PackageNames.put(ElementName.NEWJDFCMDPARAMS, "org.cip4.jdflib.jmf.JDFNewJDFCmdParams");
		sm_PackageNames.put(ElementName.NEWJDFQUPARAMS, "org.cip4.jdflib.jmf.JDFNewJDFQuParams");
		sm_PackageNames.put(ElementName.NODEINFO, "org.cip4.jdflib.core.JDFNodeInfo");
		sm_PackageNames.put(ElementName.NODEINFOCMDPARAMS, "org.cip4.jdflib.jmf.JDFNodeInfoCmdParams");
		sm_PackageNames.put(ElementName.NODEINFOQUPARAMS, "org.cip4.jdflib.jmf.JDFNodeInfoQuParams");
		sm_PackageNames.put(ElementName.NODEINFORESP, "org.cip4.jdflib.jmf.JDFNodeInfoResp");
		sm_PackageNames.put(ElementName.NOT, "org.cip4.jdflib.resource.devicecapability.JDFnot");
		sm_PackageNames.put(ElementName.NOTIFICATION, "org.cip4.jdflib.resource.JDFNotification");
		sm_PackageNames.put(ElementName.NOTIFICATIONDEF, "org.cip4.jdflib.jmf.JDFNotificationDef");
		sm_PackageNames.put(ElementName.NOTIFICATIONFILTER, "org.cip4.jdflib.resource.process.JDFNotificationFilter");
		sm_PackageNames.put(ElementName.NUMBEREVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFNumberEvaluation");
		sm_PackageNames.put(ElementName.NUMBERINGINTENT, "org.cip4.jdflib.resource.intent.JDFNumberingIntent");
		sm_PackageNames.put(ElementName.NUMBERINGPARAM, "org.cip4.jdflib.resource.process.JDFNumberingParam");
		sm_PackageNames.put(ElementName.NUMBERINGPARAMS, "org.cip4.jdflib.resource.JDFNumberingParams");
		sm_PackageNames.put(ElementName.NUMBERITEM, "org.cip4.jdflib.resource.JDFNumberItem");
		sm_PackageNames.put(ElementName.NUMBERSTATE, "org.cip4.jdflib.resource.devicecapability.JDFNumberState");

		sm_PackageNames.put(ElementName.OBJECTMODEL, "org.cip4.jdflib.resource.process.JDFObjectModel");
		sm_PackageNames.put(ElementName.OBJECTRESOLUTION, "org.cip4.jdflib.resource.process.JDFObjectResolution");
		sm_PackageNames.put(ElementName.OBSERVATIONTARGET, "org.cip4.jdflib.resource.JDFObservationTarget");
		sm_PackageNames.put(ElementName.OCCUPATION, "org.cip4.jdflib.jmf.JDFOccupation");
		sm_PackageNames.put(ElementName.OCGCONTROL, "org.cip4.jdflib.resource.process.JDFOCGControl");
		sm_PackageNames.put(ElementName.OFFERRANGE, "org.cip4.jdflib.core.JDFComment");
		sm_PackageNames.put(ElementName.OPACITY, "org.cip4.jdflib.span.JDFSpanOpacity");
		sm_PackageNames.put(ElementName.OPACITYLEVEL, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.OR, "org.cip4.jdflib.resource.devicecapability.JDFor");
		sm_PackageNames.put(ElementName.ORDERINGPARAMS, "org.cip4.jdflib.resource.process.JDFOrderingParams");
		sm_PackageNames.put(ElementName.ORGANIZATIONALUNIT, "org.cip4.jdflib.core.JDFComment");
		sm_PackageNames.put(ElementName.ORIENTATION, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.OTHERWISE, "org.cip4.jdflib.resource.devicecapability.JDFotherwise");
		sm_PackageNames.put(ElementName.OVERAGE, "org.cip4.jdflib.span.JDFNumberSpan");

		sm_PackageNames.put(ElementName.PACKINGINTENT, "org.cip4.jdflib.resource.intent.JDFPackingIntent");
		sm_PackageNames.put(ElementName.PACKINGPARAMS, "org.cip4.jdflib.resource.process.JDFPackingParams");
		sm_PackageNames.put(ElementName.PAGEASSIGNEDLIST, "org.cip4.jdflib.resource.process.JDFPageAssignedList");
		sm_PackageNames.put(ElementName.PAGEASSIGNPARAMS, "org.cip4.jdflib.resource.process.JDFPageAssignParams");
		sm_PackageNames.put(ElementName.PAGECELL, "org.cip4.jdflib.resource.JDFPageCell");
		sm_PackageNames.put(ElementName.PAGECONDITION, "org.cip4.jdflib.resource.JDFPageCondition");
		sm_PackageNames.put(ElementName.PAGEDATA, "org.cip4.jdflib.resource.process.JDFPageData");
		sm_PackageNames.put(ElementName.PAGEELEMENT, "org.cip4.jdflib.resource.process.JDFPageElement");
		sm_PackageNames.put(ElementName.PAGELIST, "org.cip4.jdflib.resource.JDFPageList");
		sm_PackageNames.put(ElementName.PAGES, "org.cip4.jdflib.span.JDFIntegerSpan");
		sm_PackageNames.put(ElementName.PAGESRESULTSPOOL, "org.cip4.jdflib.resource.process.prepress.JDFPagesResultsPool");
		sm_PackageNames.put(ElementName.PAGEVARIANCE, "org.cip4.jdflib.span.JDFIntegerSpan");
		sm_PackageNames.put(ElementName.PALLET, "org.cip4.jdflib.resource.JDFPallet");
		sm_PackageNames.put(ElementName.PALLETCORNERBOARDS, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.PALLETIZINGPARAMS, "org.cip4.jdflib.resource.JDFPalletizingParams");
		sm_PackageNames.put(ElementName.PALLETMAXHEIGHT, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.PALLETMAXWEIGHT, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.PALLETQUANTITY, "org.cip4.jdflib.span.JDFIntegerSpan");
		sm_PackageNames.put(ElementName.PALLETSIZE, "org.cip4.jdflib.span.JDFXYPairSpan");
		sm_PackageNames.put(ElementName.PALLETTYPE, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.PALLETWRAPPING, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.PART, "org.cip4.jdflib.resource.JDFPart");
		sm_PackageNames.put(ElementName.PARTAMOUNT, "org.cip4.jdflib.core.JDFPartAmount");
		sm_PackageNames.put(ElementName.PARTSTATUS, "org.cip4.jdflib.core.JDFPartStatus");
		sm_PackageNames.put(ElementName.PAYMENT, "org.cip4.jdflib.resource.JDFPayment");
		sm_PackageNames.put(ElementName.PAYTERM, "org.cip4.jdflib.core.JDFComment");
		sm_PackageNames.put(ElementName.PDFINTERPRETINGPARAMS, "org.cip4.jdflib.resource.JDFPDFInterpretingParams");
		sm_PackageNames.put(ElementName.PDFPATHEVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFPDFPathEvaluation");
		sm_PackageNames.put(ElementName.PDFPATHSTATE, "org.cip4.jdflib.resource.devicecapability.JDFPDFPathState");
		sm_PackageNames.put(ElementName.PDFTOPSCONVERSIONPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFPDFToPSConversionParams");
		sm_PackageNames.put(ElementName.PDFXPARAMS, "org.cip4.jdflib.resource.process.JDFPDFXParams");
		sm_PackageNames.put(ElementName.PDLCREATIONPARAMS, "org.cip4.jdflib.resource.process.JDFPDLCreationParams");
		sm_PackageNames.put(ElementName.PDLRESOURCEALIAS, "org.cip4.jdflib.resource.process.prepress.JDFPDLResourceAlias");
		sm_PackageNames.put(ElementName.PERFORATE, "org.cip4.jdflib.resource.process.JDFPerforate");
		sm_PackageNames.put(ElementName.PERFORATINGPARAMS, "org.cip4.jdflib.resource.JDFPerforatingParams");
		sm_PackageNames.put(ElementName.PERFORMANCE, "org.cip4.jdflib.resource.JDFPerformance");
		sm_PackageNames.put(ElementName.PERSON, "org.cip4.jdflib.resource.process.JDFPerson");
		sm_PackageNames.put(ElementName.PHASETIME, "org.cip4.jdflib.resource.JDFPhaseTime");
		sm_PackageNames.put(ElementName.PIPEPARAMS, "org.cip4.jdflib.jmf.JDFPipeParams");
		sm_PackageNames.put(ElementName.PIXELCOLORANT, "org.cip4.jdflib.resource.process.JDFPixelColorant");
		sm_PackageNames.put(ElementName.PLACEHOLDERRESOURCE, "org.cip4.jdflib.resource.JDFPlaceHolderResource");
		sm_PackageNames.put(ElementName.PLASTICCOMBBINDING, "org.cip4.jdflib.resource.process.postpress.JDFPlasticCombBinding");
		sm_PackageNames.put(ElementName.PLASTICCOMBBINDINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFPlasticCombBindingParams");
		sm_PackageNames.put(ElementName.PLASTICCOMBTYPE, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.PLATECOPYPARAMS, "org.cip4.jdflib.resource.process.JDFPlateCopyParams");
		sm_PackageNames.put(ElementName.POSITION, "org.cip4.jdflib.resource.process.JDFPosition");
		sm_PackageNames.put(ElementName.POSITIONOBJ, "org.cip4.jdflib.resource.process.JDFPositionObj");
		sm_PackageNames.put(ElementName.PREFLIGHTACTION, "org.cip4.jdflib.resource.process.JDFPreflightAction");
		sm_PackageNames.put(ElementName.PREFLIGHTANALYSIS, "org.cip4.jdflib.resource.JDFPreflightAnalysis");
		sm_PackageNames.put(ElementName.PREFLIGHTARGUMENT, "org.cip4.jdflib.resource.process.JDFPreflightArgument");
		sm_PackageNames.put(ElementName.PREFLIGHTCONSTRAINT, "org.cip4.jdflib.resource.process.prepress.JDFPreflightConstraint");
		sm_PackageNames.put(ElementName.PREFLIGHTCONSTRAINTSPOOL, "org.cip4.jdflib.pool.JDFPreflightConstraintsPool");
		sm_PackageNames.put(ElementName.PREFLIGHTDETAIL, "org.cip4.jdflib.resource.process.prepress.JDFPreflightDetail");
		sm_PackageNames.put(ElementName.PREFLIGHTINSTANCE, "org.cip4.jdflib.resource.process.prepress.JDFPreflightInstance");
		sm_PackageNames.put(ElementName.PREFLIGHTINSTANCEDETAIL, "org.cip4.jdflib.resource.process.prepress.JDFPreflightInstanceDetail");
		sm_PackageNames.put(ElementName.PREFLIGHTINVENTORY, "org.cip4.jdflib.resource.process.prepress.JDFPreflightInventory");
		sm_PackageNames.put(ElementName.PREFLIGHTPARAMS, "org.cip4.jdflib.resource.process.JDFPreflightParams");
		sm_PackageNames.put(ElementName.PREFLIGHTPROFILE, "org.cip4.jdflib.resource.process.prepress.JDFPreflightProfile");
		sm_PackageNames.put(ElementName.PREFLIGHTREPORT, "org.cip4.jdflib.resource.process.JDFPreflightReport");
		sm_PackageNames.put(ElementName.PREFLIGHTREPORTRULEPOOL, "org.cip4.jdflib.resource.process.JDFPreflightReportRulePool");
		sm_PackageNames.put(ElementName.PREFLIGHTRESULTSPOOL, "org.cip4.jdflib.pool.JDFPreflightResultsPool");
		sm_PackageNames.put(ElementName.PRERROR, "org.cip4.jdflib.resource.process.JDFPRError");
		sm_PackageNames.put(ElementName.PREVIEW, "org.cip4.jdflib.resource.process.JDFPreview");
		sm_PackageNames.put(ElementName.PREVIEWGENERATIONPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFPreviewGenerationParams");
		sm_PackageNames.put(ElementName.PRGROUP, "org.cip4.jdflib.resource.process.JDFPRGroup");
		sm_PackageNames.put(ElementName.PRGROUPOCCURRENCE, "org.cip4.jdflib.resource.process.JDFPRGroupOccurrence");
		sm_PackageNames.put(ElementName.PRICING, "org.cip4.jdflib.resource.intent.JDFPricing");
		sm_PackageNames.put(ElementName.PRINTCONDITION, "org.cip4.jdflib.resource.process.press.JDFPrintCondition");
		sm_PackageNames.put(ElementName.PRINTCONDITIONCOLOR, "org.cip4.jdflib.resource.process.JDFPrintConditionColor");
		sm_PackageNames.put(ElementName.PRINTPREFERENCE, "org.cip4.jdflib.span.JDFSpanPrintPreference");
		sm_PackageNames.put(ElementName.PRINTPROCESS, "org.cip4.jdflib.span.JDFSpanPrintProcess");
		sm_PackageNames.put(ElementName.PRINTROLLINGPARAMS, "org.cip4.jdflib.resource.process.JDFPrintRollingParams");
		sm_PackageNames.put(ElementName.PRITEM, "org.cip4.jdflib.resource.process.JDFPRItem");
		sm_PackageNames.put(ElementName.PROCCURRENCE, "org.cip4.jdflib.resource.process.JDFPROccurrence");
		sm_PackageNames.put(ElementName.PROCESSRUN, "org.cip4.jdflib.resource.JDFProcessRun");
		sm_PackageNames.put(ElementName.PRODUCTIONINTENT, "org.cip4.jdflib.resource.intent.JDFProductionIntent");
		sm_PackageNames.put(ElementName.PRODUCTIONPATH, "org.cip4.jdflib.resource.process.JDFProductionPath");
		// ProductionSubPath is a helper class needed by ProductionPath
		sm_PackageNames.put(ElementName.PRODUCTIONSUBPATH, "org.cip4.jdflib.resource.process.JDFProductionSubPath");
		sm_PackageNames.put(ElementName.PROOFINGINTENT, "org.cip4.jdflib.resource.intent.JDFProofingIntent");
		sm_PackageNames.put(ElementName.PROOFINGPARAMS, "org.cip4.jdflib.resource.process.JDFProofingParams");
		sm_PackageNames.put(ElementName.PROOFITEM, "org.cip4.jdflib.resource.JDFProofItem");
		sm_PackageNames.put(ElementName.PROOFTYPE, "org.cip4.jdflib.span.JDFSpanProofType");
		sm_PackageNames.put(ElementName.PRRULE, "org.cip4.jdflib.resource.process.JDFPRRule");
		sm_PackageNames.put(ElementName.PRRULEATTR, "org.cip4.jdflib.resource.process.JDFPRRuleAttr");
		sm_PackageNames.put(ElementName.PSTOPDFCONVERSIONPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFPSToPDFConversionParams");
		sm_PackageNames.put(ElementName.PUBLISHINGINTENT, "org.cip4.jdflib.resource.intent.JDFPublishingIntent");
		sm_PackageNames.put(ElementName.QUALITYCONTROLPARAMS, "org.cip4.jdflib.resource.process.JDFQualityControlParams");
		sm_PackageNames.put(ElementName.QUALITYCONTROLRESULT, "org.cip4.jdflib.resource.process.JDFQualityControlResult");
		sm_PackageNames.put(ElementName.QUALITYMEASUREMENT, "org.cip4.jdflib.resource.process.JDFQualityMeasurement");
		sm_PackageNames.put(ElementName.QUERY, "org.cip4.jdflib.jmf.JDFQuery");
		sm_PackageNames.put(ElementName.QUEUE, "org.cip4.jdflib.jmf.JDFQueue");
		sm_PackageNames.put(ElementName.QUEUEENTRY, "org.cip4.jdflib.jmf.JDFQueueEntry");
		sm_PackageNames.put(ElementName.QUEUEENTRYDEF, "org.cip4.jdflib.jmf.JDFQueueEntryDef");
		sm_PackageNames.put(ElementName.QUEUEENTRYDEFLIST, "org.cip4.jdflib.resource.JDFQueueEntryDefList");
		sm_PackageNames.put(ElementName.QUEUEENTRYPOSPARAMS, "org.cip4.jdflib.jmf.JDFQueueEntryPosParams");
		sm_PackageNames.put(ElementName.QUEUEENTRYPRIPARAMS, "org.cip4.jdflib.jmf.JDFQueueEntryPriParams");
		sm_PackageNames.put(ElementName.QUEUEFILTER, "org.cip4.jdflib.jmf.JDFQueueFilter");
		sm_PackageNames.put(ElementName.QUEUESUBMISSIONPARAMS, "org.cip4.jdflib.jmf.JDFQueueSubmissionParams");
		sm_PackageNames.put(ElementName.RANGE, "org.cip4.jdflib.core.JDFComment");
		sm_PackageNames.put(ElementName.RECTANGLEEVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFRectangleEvaluation");
		sm_PackageNames.put(ElementName.RECTANGLESTATE, "org.cip4.jdflib.resource.devicecapability.JDFRectangleState");
		sm_PackageNames.put(ElementName.RECYCLED, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.RECYCLEDPERCENTAGE, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.REFANCHOR, "org.cip4.jdflib.resource.JDFRefAnchor");
		sm_PackageNames.put(ElementName.REFELEMENT, "org.cip4.jdflib.core.JDFRefElement");
		sm_PackageNames.put(ElementName.REFERENCEXOBJPARAMS, "org.cip4.jdflib.resource.process.JDFReferenceXObjParams");
		sm_PackageNames.put(ElementName.REGISTERMARK, "org.cip4.jdflib.resource.process.JDFRegisterMark");
		sm_PackageNames.put(ElementName.REGISTERRIBBON, "org.cip4.jdflib.resource.JDFRegisterRibbon");
		sm_PackageNames.put(ElementName.REGISTRATION, "org.cip4.jdflib.jmf.JDFRegistration");
		sm_PackageNames.put(ElementName.REMOVED, "org.cip4.jdflib.resource.JDFRemoved");
		sm_PackageNames.put(ElementName.REMOVELINK, "org.cip4.jdflib.jmf.JDFRemoveLink");
		sm_PackageNames.put(ElementName.RENDERINGPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFRenderingParams");
		sm_PackageNames.put(ElementName.REPEATDESC, "org.cip4.jdflib.resource.process.JDFRepeatDesc");
		sm_PackageNames.put(ElementName.REQUESTQUEUEENTRYPARAMS, "org.cip4.jdflib.jmf.JDFRequestQueueEntryParams");
		sm_PackageNames.put(ElementName.REQUIRED, "org.cip4.jdflib.span.JDFTimeSpan");
		sm_PackageNames.put(ElementName.REQUIREDDURATION, "org.cip4.jdflib.span.JDFDurationSpan");
		sm_PackageNames.put(ElementName.RESOURCE, "org.cip4.jdflib.resource.JDFResource");
		sm_PackageNames.put(ElementName.RESOURCEAUDIT, "org.cip4.jdflib.resource.JDFResourceAudit");
		sm_PackageNames.put(ElementName.RESOURCECMDPARAMS, "org.cip4.jdflib.jmf.JDFResourceCmdParams");
		sm_PackageNames.put(ElementName.RESOURCEDEFINITIONPARAMS, "org.cip4.jdflib.resource.process.JDFResourceDefinitionParams");
		sm_PackageNames.put(ElementName.RESOURCEINFO, "org.cip4.jdflib.jmf.JDFResourceInfo");
		sm_PackageNames.put(ElementName.RESOURCELINK, "org.cip4.jdflib.core.JDFResourceLink");
		sm_PackageNames.put(ElementName.RESOURCELINKPOOL, "org.cip4.jdflib.pool.JDFResourceLinkPool");
		sm_PackageNames.put(ElementName.RESOURCEPARAM, "org.cip4.jdflib.resource.JDFResourceParam");
		sm_PackageNames.put(ElementName.RESOURCEPOOL, "org.cip4.jdflib.pool.JDFResourcePool");
		sm_PackageNames.put(ElementName.RESOURCEPULLPARAMS, "org.cip4.jdflib.jmf.JDFResourcePullParams");
		sm_PackageNames.put(ElementName.RESOURCEQUPARAMS, "org.cip4.jdflib.jmf.JDFResourceQuParams");
		sm_PackageNames.put(ElementName.RESPONSE, "org.cip4.jdflib.jmf.JDFResponse");
		sm_PackageNames.put(ElementName.RESUBMISSIONPARAMS, "org.cip4.jdflib.jmf.JDFResubmissionParams");
		sm_PackageNames.put(ElementName.RETURNMETHOD, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.RETURNQUEUEENTRYPARAMS, "org.cip4.jdflib.jmf.JDFReturnQueueEntryParams");
		sm_PackageNames.put(ElementName.RINGBINDING, "org.cip4.jdflib.resource.process.postpress.JDFRingBinding");
		sm_PackageNames.put(ElementName.RINGBINDINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFRingBindingParams");
		sm_PackageNames.put(ElementName.RINGDIAMETER, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.RINGMECHANIC, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.RINGSHAPE, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.RINGSYSTEM, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.RIVETSEXPOSED, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.ROLLSTAND, "org.cip4.jdflib.resource.process.JDFRollStand");
		sm_PackageNames.put(ElementName.RULELENGTH, "org.cip4.jdflib.resource.process.JDFRuleLength");
		sm_PackageNames.put(ElementName.RUNLIST, "org.cip4.jdflib.resource.process.JDFRunList");

		sm_PackageNames.put(ElementName.SADDLESTITCHING, "org.cip4.jdflib.resource.process.postpress.JDFSaddleStitching");
		sm_PackageNames.put(ElementName.SADDLESTITCHINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFSaddleStitchingParams");
		sm_PackageNames.put(ElementName.SCANPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFScanParams");
		sm_PackageNames.put(ElementName.SCAVENGERAREA, "org.cip4.jdflib.resource.JDFScavengerArea");
		sm_PackageNames.put(ElementName.SCORE, "org.cip4.jdflib.resource.process.postpress.JDFScore");
		sm_PackageNames.put(ElementName.SCORING, "org.cip4.jdflib.span.JDFSpanScoring");
		sm_PackageNames.put(ElementName.SCREENINGPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFScreeningParams");
		sm_PackageNames.put(ElementName.SCREENINGINTENT, "org.cip4.jdflib.resource.intent.JDFScreeningIntent");
		sm_PackageNames.put(ElementName.SCREENINGTYPE, "org.cip4.jdflib.span.JDFSpanScreeningType");
		sm_PackageNames.put(ElementName.SCREENSELECTOR, "org.cip4.jdflib.resource.process.JDFScreenSelector");
		sm_PackageNames.put(ElementName.SEALING, "org.cip4.jdflib.resource.process.JDFSealing");
		sm_PackageNames.put(ElementName.SEARCHPATH, "org.cip4.jdflib.core.JDFComment");
		sm_PackageNames.put(ElementName.SEPARATIONCONTROLPARAMS, "org.cip4.jdflib.resource.process.JDFSeparationControlParams");
		sm_PackageNames.put(ElementName.SEPARATIONLIST, "org.cip4.jdflib.core.JDFSeparationList");
		sm_PackageNames.put(ElementName.SEPARATIONSPEC, "org.cip4.jdflib.resource.process.JDFSeparationSpec");
		sm_PackageNames.put(ElementName.SERVICELEVEL, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.SET, "org.cip4.jdflib.resource.devicecapability.JDFset");
		// "Shape" is context sensitive, see handleOtherElements() and
		// putConstructorToHashMap()
		sm_PackageNames.put(ElementName.SHAPECUT, "org.cip4.jdflib.resource.intent.JDFShapeCut");
		sm_PackageNames.put(ElementName.SHAPECUTTINGINTENT, "org.cip4.jdflib.resource.intent.JDFShapeCuttingIntent");
		sm_PackageNames.put(ElementName.SHAPECUTTINGPARAMS, "org.cip4.jdflib.resource.JDFShapeCuttingParams");
		sm_PackageNames.put(ElementName.SHAPEDEF, "org.cip4.jdflib.resource.process.JDFShapeDef");
		sm_PackageNames.put(ElementName.SHAPEDEFPRODUCTIONPARAMS, "org.cip4.jdflib.resource.process.JDFShapeDefProductionParams");
		sm_PackageNames.put(ElementName.SHAPEDEPTH, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.SHAPEEVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFShapeEvaluation");
		sm_PackageNames.put(ElementName.SHAPESTATE, "org.cip4.jdflib.resource.devicecapability.JDFShapeState");
		sm_PackageNames.put(ElementName.SHAPETEMPLATE, "org.cip4.jdflib.resource.process.JDFShapeTemplate");
		sm_PackageNames.put(ElementName.SHAPETYPE, "org.cip4.jdflib.span.JDFSpanShapeType");
		sm_PackageNames.put(ElementName.SHEET, "org.cip4.jdflib.resource.process.JDFLayout");
		sm_PackageNames.put(ElementName.SHIFTPOINT, "org.cip4.jdflib.resource.process.JDFShiftPoint");
		sm_PackageNames.put(ElementName.SHRINKINGPARAMS, "org.cip4.jdflib.resource.JDFShrinkingParams");
		sm_PackageNames.put(ElementName.SHUTDOWNCMDPARAMS, "org.cip4.jdflib.jmf.JDFShutDownCmdParams");
		sm_PackageNames.put(ElementName.SIDESEWING, "org.cip4.jdflib.resource.process.postpress.JDFSideSewing");
		sm_PackageNames.put(ElementName.SIDESEWINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFSideSewingParams");
		sm_PackageNames.put(ElementName.SIDESTITCHING, "org.cip4.jdflib.resource.process.postpress.JDFSideStitching");
		sm_PackageNames.put(ElementName.SIGNAL, "org.cip4.jdflib.jmf.JDFSignal");
		sm_PackageNames.put(ElementName.SIGNATURE, "org.cip4.jdflib.resource.process.JDFLayout");
		sm_PackageNames.put(ElementName.SIGNATURECELL, "org.cip4.jdflib.resource.process.JDFSignatureCell");
		sm_PackageNames.put(ElementName.SIZEINTENT, "org.cip4.jdflib.resource.intent.JDFSizeIntent");
		sm_PackageNames.put(ElementName.SIZEPOLICY, "org.cip4.jdflib.span.JDFSpanSizePolicy");
		sm_PackageNames.put(ElementName.SOFTCOVERBINDING, "org.cip4.jdflib.resource.JDFSoftCoverBinding");
		sm_PackageNames.put(ElementName.SOURCERESOURCE, "org.cip4.jdflib.resource.process.JDFSourceResource");
		sm_PackageNames.put(ElementName.SPAWNED, "org.cip4.jdflib.node.JDFSpawned");
		sm_PackageNames.put(ElementName.SPINEBRUSHING, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.SPINEFIBERROUGHING, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.SPINEGLUE, "org.cip4.jdflib.span.JDFSpanGlue");
		sm_PackageNames.put(ElementName.SPINELEVELLING, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.SPINEMILLING, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.SPINENOTCHING, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.SPINEPREPARATIONPARAMS, "org.cip4.jdflib.resource.JDFSpinePreparationParams");
		sm_PackageNames.put(ElementName.SPINESANDING, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.SPINESHREDDING, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.SPINETAPINGPARAMS, "org.cip4.jdflib.resource.JDFSpineTapingParams");
		sm_PackageNames.put(ElementName.STACKINGPARAMS, "org.cip4.jdflib.resource.JDFStackingParams");
		sm_PackageNames.put(ElementName.STATION, "org.cip4.jdflib.resource.process.JDFStation");
		sm_PackageNames.put(ElementName.STATUSPOOL, "org.cip4.jdflib.pool.JDFStatusPool");
		sm_PackageNames.put(ElementName.STATUSQUPARAMS, "org.cip4.jdflib.jmf.JDFStatusQuParams");
		sm_PackageNames.put(ElementName.STITCHINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFStitchingParams");
		sm_PackageNames.put(ElementName.STITCHNUMBER, "org.cip4.jdflib.span.JDFIntegerSpan");
		sm_PackageNames.put(ElementName.STOCKBRAND, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.STOCKTYPE, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.STOPPERSCHPARAMS, "org.cip4.jdflib.jmf.JDFStopPersChParams");
		sm_PackageNames.put(ElementName.STRAP, "org.cip4.jdflib.resource.JDFStrap");
		sm_PackageNames.put(ElementName.STRAPPINGPARAMS, "org.cip4.jdflib.resource.JDFStrappingParams");
		sm_PackageNames.put(ElementName.STRINGEVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFStringEvaluation");
		sm_PackageNames.put(ElementName.STRINGLISTVALUE, "org.cip4.jdflib.resource.process.JDFStringListValue");
		sm_PackageNames.put(ElementName.STRINGSTATE, "org.cip4.jdflib.resource.devicecapability.JDFStringState");
		sm_PackageNames.put(ElementName.STRIPBINDING, "org.cip4.jdflib.resource.JDFStripBinding");
		sm_PackageNames.put(ElementName.STRIPBINDINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFStripBindingParams");
		sm_PackageNames.put(ElementName.STRIPCELLPARAMS, "org.cip4.jdflib.resource.process.JDFStripCellParams");
		sm_PackageNames.put(ElementName.STRIPMARK, "org.cip4.jdflib.resource.process.JDFStripMark");
		sm_PackageNames.put(ElementName.STRIPMATERIAL, "org.cip4.jdflib.span.JDFSpanStripMaterial");
		sm_PackageNames.put(ElementName.STRIPPINGPARAMS, "org.cip4.jdflib.resource.JDFStrippingParams");
		sm_PackageNames.put(ElementName.SUBMISSIONMETHODS, "org.cip4.jdflib.jmf.JDFSubmissionMethods");
		sm_PackageNames.put(ElementName.SUBSCRIPTION, "org.cip4.jdflib.jmf.JDFSubscription");
		sm_PackageNames.put(ElementName.SUBSCRIPTIONFILTER, "org.cip4.jdflib.jmf.JDFSubscriptionFilter");
		sm_PackageNames.put(ElementName.SUBSCRIPTIONINFO, "org.cip4.jdflib.jmf.JDFSubscriptionInfo");
		// "Surface" is context sensitive, see handleOtherElements() and
		// putConstructorToHashMap()
		sm_PackageNames.put(ElementName.SURPLUSHANDLING, "org.cip4.jdflib.span.JDFSpanSurplusHandling");
		sm_PackageNames.put(ElementName.SYSTEMTIMESET, "org.cip4.jdflib.resource.JDFSystemTimeSet");
		sm_PackageNames.put(ElementName.TABBINDMYLAR, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.TABBODYCOPY, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.TABBRAND, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.TABDIMENSIONS, "org.cip4.jdflib.resource.process.JDFTabDimensions");
		sm_PackageNames.put(ElementName.TABEXTENSIONDISTANCE, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.TABEXTENSIONMYLAR, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.TABMYLARCOLOR, "org.cip4.jdflib.span.JDFSpanNamedColor");
		sm_PackageNames.put(ElementName.TABMYLARCOLORDETAILS, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.TABS, "org.cip4.jdflib.resource.JDFTabs");
		sm_PackageNames.put(ElementName.TAPE, "org.cip4.jdflib.resource.JDFTape");
		sm_PackageNames.put(ElementName.TAPEBINDING, "org.cip4.jdflib.span.JDFOptionSpan");
		sm_PackageNames.put(ElementName.TAPECOLOR, "org.cip4.jdflib.span.JDFSpanNamedColor");
		sm_PackageNames.put(ElementName.TECHNOLOGY, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.TEETHPERDIMENSION, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.TEMPERATURE, "org.cip4.jdflib.span.JDFSpanTemperature");
		sm_PackageNames.put(ElementName.TEST, "org.cip4.jdflib.resource.devicecapability.JDFTest");
		sm_PackageNames.put(ElementName.TESTPOOL, "org.cip4.jdflib.resource.devicecapability.JDFTestPool");
		sm_PackageNames.put(ElementName.TESTREF, "org.cip4.jdflib.resource.devicecapability.JDFTestRef");
		sm_PackageNames.put(ElementName.TEXTURE, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.THICKNESS, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.THINPDFPARAMS, "org.cip4.jdflib.resource.process.JDFThinPDFParams");
		sm_PackageNames.put(ElementName.THREADSEALING, "org.cip4.jdflib.resource.process.postpress.JDFThreadSealing");
		sm_PackageNames.put(ElementName.THREADSEALINGPARAMS, "org.cip4.jdflib.resource.JDFThreadSealingParams");
		sm_PackageNames.put(ElementName.THREADSEWING, "org.cip4.jdflib.resource.process.postpress.JDFThreadSewing");
		sm_PackageNames.put(ElementName.THREADSEWINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFThreadSewingParams");
		sm_PackageNames.put(ElementName.TIFFEMBEDDEDFILE, "org.cip4.jdflib.resource.process.JDFTIFFEmbeddedFile");
		sm_PackageNames.put(ElementName.TIFFFORMATPARAMS, "org.cip4.jdflib.resource.process.JDFTIFFFormatParams");
		sm_PackageNames.put(ElementName.TIFFTAG, "org.cip4.jdflib.resource.process.JDFTIFFtag");
		sm_PackageNames.put(ElementName.TIGHTBACKING, "org.cip4.jdflib.span.JDFSpanTightBacking");
		sm_PackageNames.put(ElementName.TILE, "org.cip4.jdflib.resource.process.JDFTile");
		sm_PackageNames.put(ElementName.TOOL, "org.cip4.jdflib.resource.JDFTool");
		sm_PackageNames.put(ElementName.TRACKFILTER, "org.cip4.jdflib.jmf.JDFTrackFilter");
		sm_PackageNames.put(ElementName.TRACKRESULT, "org.cip4.jdflib.jmf.JDFTrackResult");
		sm_PackageNames.put(ElementName.TRANSFER, "org.cip4.jdflib.span.JDFSpanTransfer");
		sm_PackageNames.put(ElementName.TRANSFERCURVE, "org.cip4.jdflib.resource.process.JDFTransferCurve");
		sm_PackageNames.put(ElementName.TRANSFERCURVEPOOL, "org.cip4.jdflib.resource.process.JDFTransferCurvePool");
		sm_PackageNames.put(ElementName.TRANSFERCURVESET, "org.cip4.jdflib.resource.process.JDFTransferCurveSet");
		sm_PackageNames.put(ElementName.TRANSFERFUNCTIONCONTROL, "org.cip4.jdflib.resource.JDFTransferFunctionControl");
		sm_PackageNames.put(ElementName.TRAPPINGDETAILS, "org.cip4.jdflib.resource.process.prepress.JDFTrappingDetails");
		sm_PackageNames.put(ElementName.TRAPPINGORDER, "org.cip4.jdflib.resource.process.prepress.JDFTrappingOrder");
		sm_PackageNames.put(ElementName.TRAPPINGPARAMS, "org.cip4.jdflib.resource.process.prepress.JDFTrappingParams");
		sm_PackageNames.put(ElementName.TRAPREGION, "org.cip4.jdflib.resource.process.JDFTrapRegion");
		sm_PackageNames.put(ElementName.TRIGGER, "org.cip4.jdflib.jmf.JDFTrigger");
		sm_PackageNames.put(ElementName.TRIMMINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFTrimmingParams");

		sm_PackageNames.put(ElementName.UNDERAGE, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.UPDATEJDFCMDPARAMS, "org.cip4.jdflib.jmf.JDFUpdateJDFCmdParams");
		sm_PackageNames.put(ElementName.USAGECOUNTER, "org.cip4.jdflib.resource.process.JDFUsageCounter");
		sm_PackageNames.put(ElementName.USWEIGHT, "org.cip4.jdflib.span.JDFNumberSpan");

		sm_PackageNames.put(ElementName.VALUE, "org.cip4.jdflib.resource.JDFValue");
		sm_PackageNames.put(ElementName.VALUELOC, "org.cip4.jdflib.resource.devicecapability.JDFValueLoc");
		sm_PackageNames.put(ElementName.VARNISHINGPARAMS, "org.cip4.jdflib.resource.JDFVarnishingParams");
		sm_PackageNames.put(ElementName.VELOBINDING, "org.cip4.jdflib.resource.process.postpress.JDFVeloBinding");
		sm_PackageNames.put(ElementName.VERIFICATIONPARAMS, "org.cip4.jdflib.resource.JDFVerificationParams");
		sm_PackageNames.put(ElementName.VIEWBINDER, "org.cip4.jdflib.span.JDFNameSpan");

		sm_PackageNames.put(ElementName.WAKEUPCMDPARAMS, "org.cip4.jdflib.jmf.JDFWakeUpCmdParams");
		sm_PackageNames.put(ElementName.WEBINLINEFINISHINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFWebInlineFinishingParams");
		sm_PackageNames.put(ElementName.WEIGHT, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.WHEN, "org.cip4.jdflib.resource.devicecapability.JDFwhen");
		sm_PackageNames.put(ElementName.WIRECOMBBINDING, "org.cip4.jdflib.resource.process.postpress.JDFWireCombBinding");
		sm_PackageNames.put(ElementName.WIRECOMBBINDINGPARAMS, "org.cip4.jdflib.resource.process.postpress.JDFWireCombBindingParams");
		sm_PackageNames.put(ElementName.WIRECOMBBRAND, "org.cip4.jdflib.span.JDFStringSpan");
		sm_PackageNames.put(ElementName.WIRECOMBMATERIAL, "org.cip4.jdflib.span.JDFSpanWireCombMaterial");
		sm_PackageNames.put(ElementName.WIRECOMBSHAPE, "org.cip4.jdflib.span.JDFSpanWireCombShape");
		sm_PackageNames.put(ElementName.WRAPPEDQUANTITY, "org.cip4.jdflib.span.JDFIntegerSpan");
		sm_PackageNames.put(ElementName.WRAPPINGMATERIAL, "org.cip4.jdflib.span.JDFNameSpan");
		sm_PackageNames.put(ElementName.WRAPPINGPARAMS, "org.cip4.jdflib.resource.JDFWrappingParams");

		sm_PackageNames.put(ElementName.XOR, "org.cip4.jdflib.resource.devicecapability.JDFxor");
		sm_PackageNames.put(ElementName.XPOSITION, "org.cip4.jdflib.span.JDFNumberSpan");
		sm_PackageNames.put(ElementName.XYPAIREVALUATION, "org.cip4.jdflib.resource.devicecapability.JDFXYPairEvaluation");
		sm_PackageNames.put(ElementName.XYPAIRSTATE, "org.cip4.jdflib.resource.devicecapability.JDFXYPairState");

		sm_PackageNames.put(ElementName.YPOSITION, "org.cip4.jdflib.span.JDFNumberSpan");
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
			prefix = JDFConstants.COLON;
		return nsMap.get(prefix);
	}

	/**
	 * @param prefix
	 * @param strNamespaceURI
	 */
	public void setNamespaceURIFromPrefix(String prefix, String strNamespaceURI)
	{
		if (prefix == null)
			prefix = JDFConstants.COLON;
		String old = nsMap.get(prefix);
		if (old == null || !old.equals(strNamespaceURI))
		{
			setRootNSAttribute(prefix, strNamespaceURI);
			nsMap.put(prefix, strNamespaceURI);
		}
	}

	/**
	 * @param prefix
	 * @param strNamespaceURI
	 */
	private void setRootNSAttribute(String prefix, String strNamespaceURI)
	{
		String qualifiedName = "xmlns";
		if (!JDFConstants.COLON.equals(prefix))
			qualifiedName += JDFConstants.COLON + prefix;
		KElement element = (KElement) getDocumentElement();
		if (element != null)
			element.setAttribute(qualifiedName, strNamespaceURI, AttributeName.XMLNSURI);
	}

	/**
	 * @return
	*/
	public XMLDocUserData getMyUserData()
	{
		return myXMLUserDat;
	}

}
