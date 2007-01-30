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

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.mail.BodyPart;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.ElementImpl;
import org.apache.xerces.dom.ParentNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class DocumentJDFImpl extends DocumentImpl implements Serializable
{
    private static final long serialVersionUID = 1L;
    public boolean bKElementOnly=false;
    private boolean ignoreNSDefault=false;
    private boolean bInJDFJMF=false;
 
    /** Caches default package name classes of files. */
    private static HashMap  sm_PackageNames = new HashMap();
    
    /** Caches Classes */
    private static HashMap  sm_ClassAlreadyInstantiated = new HashMap();
    
    /** Caches JDF element constructors */
    private static HashMap sm_hashCtorElement = new HashMap();

    /** Caches JDF element constructors (namespace variant) */
    private static HashMap sm_hashCtorElementNS = new HashMap();

 
    private Node    m_ParentNode = null;
    
    // used mainly for memory debugging purposes
    private long initialMem;
    
    public String m_OriginalFileName=null;
    // the xml output of the schema validation
    public XMLDoc m_validationResult=null;
    /**
     * the mime bodypart that this document was parsed from
     */
    public BodyPart m_Bodypart=null;
    
    private static final String jdfNSURI=JDFElement.getSchemaURL();

    /**
     * rough guestimate of the memory used by this if called after parsing
     * @return the difference of memory used when calling compared to construction time 
     */
    public long getDocMemoryUsed()
    {
        final Runtime rt=Runtime.getRuntime();
        long mem=rt.totalMemory()-rt.freeMemory();
        if (mem<initialMem)
            initialMem=mem;
        return mem - initialMem;
    }

    public Object clone() 
    {
        DocumentJDFImpl clon;
        try
        {
            clon = (DocumentJDFImpl) super.clone();
        }
        catch (CloneNotSupportedException x)
        {
           clon=new DocumentJDFImpl();
        }
        clon.m_Bodypart=m_Bodypart;
        clon.m_OriginalFileName=m_OriginalFileName;
        clon.docElement = (ElementImpl) docElement.cloneNode(true);
        clon.firstChild=clon.docElement;
        
        return clon;
    }

//    public static String getPackage(String nodeName)
//    {
//        synchronized (sm_PackageNames)
//        {
//            return  (String) sm_PackageNames.get(nodeName);
//        }
//    }
    

    /**
     * register new custom class in the factory
     * @param strElement local name
     * @param elemClass package path
     */
    public static void registerCustomClass(String strElement, String packagepath)
    {
        synchronized (sm_PackageNames)
        {
            sm_PackageNames.put(strElement, packagepath);
            sm_ClassAlreadyInstantiated.remove(strElement);
            sm_hashCtorElement.remove(strElement);
            sm_hashCtorElementNS.remove(strElement);
           }
    }
    
    
    /**
     * @param ownerDoc
     * @param parent
     * @param qualifiedName
     * @return
     */
    public static KElement factoryCreate(DocumentJDFImpl ownerDoc, 
                                           ParentNode parent, 
                                           String qualifiedName)
    {
        ownerDoc.setParentNode(parent); // set the parent in the factory for private Elements
        return (KElement) ownerDoc.createElement(qualifiedName);
    }

    /**
     * @param ownerDoc
     * @param parent
     * @param namespaceURI
     * @param qualifiedName
     * @return
     */
    public static KElement factoryCreate(DocumentJDFImpl ownerDoc, 
                                           ParentNode parent, 
                                           String namespaceURI, 
                                           String qualifiedName)
    {
        ownerDoc.setParentNode(parent); // set the parent in the factory for private Elements
        return (KElement) ownerDoc.createElementNS(namespaceURI, qualifiedName);
    }

    /**
     * @param ownerDoc
     * @param parent
     * @param namespaceURI
     * @param qualifiedName
     * @param localpart
     * @return
     */
    public static KElement factoryCreate(DocumentJDFImpl ownerDoc, 
                                           ParentNode parent, 
                                           String namespaceURI, 
                                           String qualifiedName, 
                                           String localpart)
    {
        ownerDoc.setParentNode(parent); // set the parent in the factory for private Elements
        return (KElement) ownerDoc.createElementNS(namespaceURI, qualifiedName, localpart);
        
    }



    /**
     * Constructor for DocumentJDFImpl.
     */
    public DocumentJDFImpl()
    {
        super();
        final Runtime rt = Runtime.getRuntime();
        initialMem = rt.totalMemory() - rt.freeMemory();
        
    }


    /**
     * Factory method; creates an <code>Element</code> having this <code>Document</code>
     * as its OwnerDoc.
     *
     * @param qualifiedName The name of the element type to instantiate. For
     * XML, this is case-sensitive. 
     * 
     * @throws <code>DOMException(INVALID_NAME_ERR)</code> if the tag name is not
     * acceptable.
     */
    /* (non-Javadoc)
     * @see org.apache.xerces.dom.CoreDocumentImpl#createElement(java.lang.String)
     */
    public Element createElement(String qualifiedName)
    {
        String namespaceURI = null;
        String localPart = KElement.xmlnsLocalName(qualifiedName);
        
        return createElementNS(namespaceURI, qualifiedName, localPart);
    }

    /* (non-Javadoc)
     * @see org.apache.xerces.dom.CoreDocumentImpl#createElementNS(java.lang.String, java.lang.String)
     */
    public Element createElementNS(String namespaceURI, String qualifiedName)
    {
        String localPart = KElement.xmlnsLocalName(qualifiedName);
        return createElementNS(namespaceURI, qualifiedName, localPart);
    }

    /* (non-Javadoc)
     * @see org.apache.xerces.dom.CoreDocumentImpl#createElementNS(String, String, String)
     */
    public Element createElementNS(String namespaceURI, String qualifiedName, String localPart)
    {
        Constructor constructi;
        Class classOfConstructor = null;
        // we are not yet in a JDF or JMF
        if(!bInJDFJMF && (jdfNSURI.equals(namespaceURI) || ElementName.JDF.equals(localPart) || ElementName.JMF.equals(localPart)))
        {
            bInJDFJMF=true;
        }

        synchronized (sm_hashCtorElementNS)
        {
            constructi = (Constructor) sm_hashCtorElementNS.get(qualifiedName);

            // it has to be 1 coreDocImpl plus 3 String Parameters
            // otherwhise don't use hashtableentry
            if (constructi == null || constructi.getParameterTypes().length != 4) // AS 17.09.02
            {
                try
                {
                    classOfConstructor = getFactoryClass(namespaceURI, qualifiedName, localPart);
                    if (classOfConstructor != null)
                    {
                        final Class[] constructorParameters = {
                                org.apache.xerces.dom.CoreDocumentImpl.class, java.lang.String.class,
                                java.lang.String.class, java.lang.String.class, };
                        
                        constructi = classOfConstructor.getDeclaredConstructor(constructorParameters);
                        putConstructorToHashMap(sm_hashCtorElementNS, qualifiedName, constructi);
                    }
                }
                catch (ClassNotFoundException e)
                {
                    // internal error
                    String message = "(DocumentJDFImpl.createElementNS) getFactoryClass() " + 
                                     "class " + qualifiedName + " could not be created"
                                     + " (surplus line in sm_PackageNames or non existing class ???)";
                    throw new DOMException(DOMException.NOT_FOUND_ERR, message);
                }
                catch (NoSuchMethodException e)
                {
                    // internal error
                    String message = "(DocumentJDFImpl.createElementNS) getDeclaredConstructor() not found: ";
                    if (classOfConstructor != null)
                        message += classOfConstructor.getName() + "(CoreDocumentImpl, String, String, String)";
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
    private void putConstructorToHashMap(HashMap hashCtorElement, String qualifiedName, Constructor constructi)
    {
        // only put the constructor into the map if its not a Resource or an element
        // there are a couple of nodes which can be both resource and element 
        // depending on the occurrence  
        final String className = constructi.getDeclaringClass().getName();
        final boolean bSpecialClass = isSpecialClass(qualifiedName, className);
        if ( bSpecialClass )
        {   
            hashCtorElement.put(qualifiedName, constructi);
        }
    }

    /**
     * @param qualifiedName
     * @param className
     * @return
     */
    private boolean isSpecialClass(String qualifiedName, final String className)
    {
        final boolean bSpecialClass = !className.endsWith("JDFResource")        &&
                        !className.endsWith("JDFElement")         &&
                        !className.endsWith("KElement")         &&
                        !qualifiedName.equals("HoleType") &&
                        !qualifiedName.equals("Method")   &&
                        !qualifiedName.equals("Shape")    &&
                        !qualifiedName.equals("Position") &&
                        !qualifiedName.equals("Surface");
        return bSpecialClass;
    }

    /**
     * Method createKElement
     * @param constructi
     * @param constructorArguments
     * @return KElement (always != <code>null</code>)
     */
    private KElement createKElement(Constructor constructi, Object[] constructorArguments)
    {
        KElement newElement = null;
        String message = null;

        try
        {
            newElement = (KElement) constructi.newInstance(constructorArguments);
        }
        // re-throw on error is done below
        catch (IllegalAccessException e)
        {
            message = "(DocumentJDFImpl.createKElement) IllegalAccessException caught :" + e.getMessage();
        }
        catch (InstantiationException e)
        {
            message = "(DocumentJDFImpl.createKElement) InstantiationException caught (abstract class?) : " 
                        + constructi.getName() + "(CoreDocumentImpl, String, String, String)";
        }
        catch (InvocationTargetException e)
        {
            message = "(DocumentJDFImpl.createKElement) InvocationTargetException caught :" + e.getMessage();
        }
        catch (Exception e)
        {
            message = "(DocumentJDFImpl.createKElement) Exception caught :" + e.getMessage();
        }

        if (newElement == null)
        {
            if(message==null)
                message = "(DocumentJDFImpl.createKElement) Could not create an element with " + 
            constructi.getName() + "(CoreDocumentImpl, String, String, String)";
            // something went wrong
            throw new DOMException(DOMException.SYNTAX_ERR, message);
        }

        return newElement;
    }

    /**
     * Searches for the matching factory class in sm_PackageNames 
     * If a match could not be found 
     *    then JDFResource.class is returned if the element is in a resource pool
     *    else if the element is in the default name space JDFElement.class is returned
     *    else KElement.class is returned
     * 
     * @param strNameSpaceURI the namespace of the class. only http://www.CIP4.org/JDFSchema_1_1
     *                        is the valid namespace for JDF Elements all other namespaces will return
     *                        JDFElement.class or JDFResource.class only.
     * @param qualifiedName   the qualified name of the class
     * @param localPart       the local part of the qualified name
     * @return 
     */
    public Class getFactoryClass(String qualifiedName)
    {
        Class packageNameClass = null;
        
        try
        {
            packageNameClass = getFactoryClass(null, qualifiedName, qualifiedName);
        }
        catch (ClassNotFoundException e)
        { /**/ }
        
        return packageNameClass;
    }
    
    private Class getFactoryClass(String strNameSpaceURI, String qualifiedName, String localPart) throws ClassNotFoundException
    {
        Class packageNameClass = (Class) sm_ClassAlreadyInstantiated.get(qualifiedName);

        if (packageNameClass == null)
        { // class not found in the buffer! Instantiate it and add it to the buffer
            synchronized (sm_PackageNames)
            {
                String strClassPath = getFactoryClassPath(strNameSpaceURI, qualifiedName, localPart);
                
                boolean normalElement = true;
                if (strClassPath == null)
                {
                    normalElement = false;
                    strClassPath  = getSpecialClassPath(strNameSpaceURI, qualifiedName);
                }
                else
                {
                    normalElement=isSpecialClass(qualifiedName, strClassPath);
                }
                
                // assert strClassPath != null
                packageNameClass = Class.forName(strClassPath);
                
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
    private String getFactoryClassPath (String strNameSpaceURI, String qualifiedName, String localPart)
    {
        if(bKElementOnly)
            return "org.cip4.jdflib.core.KElement";
        
        // we are not yet in a JDF or JMF - it must be a KElement
        if(!bInJDFJMF)
            return "org.cip4.jdflib.core.KElement";

        String strClassPath = null;
        
        if (qualifiedName.endsWith(JDFConstants.LINK))
        {
            strClassPath = (String) sm_PackageNames.get(ElementName.RESOURCELINK);
        }
        else if (qualifiedName.endsWith(JDFConstants.REF)
                && !qualifiedName.equals(ElementName.TESTREF))
        {
            strClassPath = (String) sm_PackageNames.get(ElementName.REFELEMENT);
        }
        else
        {
            strClassPath = (String) sm_PackageNames.get(qualifiedName);
            if (strClassPath == null && (null == strNameSpaceURI || 
                                         jdfNSURI.equals(strNameSpaceURI) || 
                                         JDFConstants.EMPTYSTRING.equals(strNameSpaceURI)))
            { // the maps only contain local names for jdf - recheck in case of prefix
                strClassPath = (String) sm_PackageNames.get(localPart);
            }
        }
        return strClassPath;
    }
    
    
    
    /**
     * @param strName
     * @throws ClassNotFoundException 
     */
    private String getSpecialClassPath(String nameSpaceURI, String strName)
    {
        String strClassPath = null;
        
        String strParentNodeClass = null;
        if (m_ParentNode != null)
        {
            //          strParentNodeClass = m_ParentNode.getClass().getCanonicalName();
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
            if ("org.cip4.jdflib.resource.intent.JDFInsertingIntent".equals(strParentNodeClass)
              || "org.cip4.jdflib.resource.JDFInsert".equals(strParentNodeClass))
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
                strClassPath = (String) sm_PackageNames.get("ResDefault");
            }
            else
            {
                strClassPath = (nameSpaceURI == null && bInJDFJMF || JDFConstants.JDFNAMESPACE.equals(nameSpaceURI))
                                    ? (String) sm_PackageNames.get("EleDefault") 
                                    : (String) sm_PackageNames.get("OtherNSDefault");
            }
        }
        
        return strClassPath;
    }


    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        final Element rootElement = getDocumentElement();
        if(rootElement != null)
        {
            return super.toString() + rootElement.toString();
        }
        return super.toString();
    }
    
    public void setParentNode(Node node)
    {
        m_ParentNode = node;
    }

    private boolean isDeepResource(String strName)
    {
        if(m_ParentNode==null)
            return false;
        if (m_ParentNode instanceof JDFResourcePool) // direct child of an rp
            return true;
        if (m_ParentNode instanceof JDFResource) // partitioned resource leaf
        {
             return m_ParentNode.getLocalName().equals(strName);
        }
        return false; 
    }
    
    /*
     * Attention! this only sets the initial parent for deep=true
     *
     * (non-Javadoc)
     * @see org.apache.xerces.dom.CoreDocumentImpl#importNode(org.w3c.dom.Node, boolean)
     */
    //TODO revisit setting parent nodes when importing
    public Node importNode(Node importedNode, boolean deep)
    {
        setParentNode(importedNode.getParentNode());
        return super.importNode(importedNode, deep);
    }
    
    /**
     * register all default classes in the factory
     * @param strElement
     * @param elemClass
     */
    static
    {
        sm_PackageNames.put("ResDefault",                        "org.cip4.jdflib.resource.JDFResource");
        sm_PackageNames.put("EleDefault",                        "org.cip4.jdflib.core.JDFElement");      
        sm_PackageNames.put("OtherNSDefault",                    "org.cip4.jdflib.core.KElement");      

// root elements
        sm_PackageNames.put("JDF",                               "org.cip4.jdflib.node.JDFNode");
        sm_PackageNames.put("JMF",                               "org.cip4.jdflib.jmf.JDFJMF");

        sm_PackageNames.put("Acknowledge",                       "org.cip4.jdflib.jmf.JDFAcknowledge");
        sm_PackageNames.put("Action",                            "org.cip4.jdflib.resource.devicecapability.JDFAction");
        sm_PackageNames.put("ActionPool",                        "org.cip4.jdflib.resource.devicecapability.JDFActionPool");
        sm_PackageNames.put("Added",                             "org.cip4.jdflib.jmf.JDFAdded");
        sm_PackageNames.put("Address",                           "org.cip4.jdflib.resource.process.JDFAddress");
        sm_PackageNames.put("AdhesiveBinding",                   "org.cip4.jdflib.resource.process.postpress.JDFAdhesiveBinding");
        sm_PackageNames.put("AdhesiveBindingParams",             "org.cip4.jdflib.resource.JDFAdhesiveBindingParams");
        sm_PackageNames.put("AdvancedParams",                    "org.cip4.jdflib.resource.process.JDFAdvancedParams");
        sm_PackageNames.put("Amount",                            "org.cip4.jdflib.span.JDFIntegerSpan");
        sm_PackageNames.put("AmountPool",                        "org.cip4.jdflib.pool.JDFAmountPool");
        sm_PackageNames.put("Ancestor",                          "org.cip4.jdflib.node.JDFAncestor");
        sm_PackageNames.put("AncestorPool",                      "org.cip4.jdflib.pool.JDFAncestorPool");
        sm_PackageNames.put("and",                               "org.cip4.jdflib.resource.devicecapability.JDFand");
        sm_PackageNames.put("ApprovalDetails",                   "org.cip4.jdflib.resource.process.JDFApprovalDetails");
        sm_PackageNames.put("ApprovalParams",                    "org.cip4.jdflib.resource.process.JDFApprovalParams");
        sm_PackageNames.put("ApprovalPerson",                    "org.cip4.jdflib.resource.process.JDFApprovalPerson");
        sm_PackageNames.put("ApprovalSuccess",                   "org.cip4.jdflib.resource.process.JDFApprovalSuccess");
        sm_PackageNames.put("ArgumentValue",                     "org.cip4.jdflib.resource.process.JDFArgumentValue");
        sm_PackageNames.put("ArtDelivery",                       "org.cip4.jdflib.resource.intent.JDFArtDelivery");
        sm_PackageNames.put("ArtDeliveryDate",                   "org.cip4.jdflib.span.JDFTimeSpan");
        sm_PackageNames.put("ArtDeliveryIntent",                 "org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent");
        sm_PackageNames.put("ArtDeliveryType",                   "org.cip4.jdflib.resource.intent.JDFArtDeliveryType");
        sm_PackageNames.put("ArtHandling",                       "org.cip4.jdflib.span.JDFSpanArtHandling");
        sm_PackageNames.put("Assembly",                          "org.cip4.jdflib.resource.process.JDFAssembly");
        sm_PackageNames.put("AssemblySection",                   "org.cip4.jdflib.resource.process.JDFAssemblySection");
//        sm_PackageNames.put("AssetListCreation",                 "org.cip4.jdflib.resource.process.prepress.JDFAssetListCreation");
        sm_PackageNames.put("AssetListCreationParams",           "org.cip4.jdflib.resource.process.prepress.JDFAssetListCreationParams");
        sm_PackageNames.put("AttributeName",                     "org.cip4.jdflib.core.AttributeName");
        sm_PackageNames.put("Audit",                             "org.cip4.jdflib.core.JDFAudit");
        sm_PackageNames.put("AuditPool",                         "org.cip4.jdflib.pool.JDFAuditPool");
        sm_PackageNames.put("AutomatedOverprintParams",          "org.cip4.jdflib.resource.process.JDFAutomatedOverprintParams");
        sm_PackageNames.put("BackCoatings",                      "org.cip4.jdflib.span.JDFSpanCoatings");
        sm_PackageNames.put("BackCoverColor",                    "org.cip4.jdflib.span.JDFSpanNamedColor");
        sm_PackageNames.put("BackPreparation",                   "org.cip4.jdflib.resource.process.postpress.JDFBackPreparation");
        sm_PackageNames.put("Band",                              "org.cip4.jdflib.resource.JDFBand");
        sm_PackageNames.put("Barcode",                           "org.cip4.jdflib.resource.JDFBarcode");
        sm_PackageNames.put("BarcodeCompParams",                 "org.cip4.jdflib.resource.process.JDFBarcodeCompParams");
        sm_PackageNames.put("BarcodeDetails",                    "org.cip4.jdflib.resource.process.JDFBarcodeDetails");
        sm_PackageNames.put("BarcodeReproParams",                "org.cip4.jdflib.resource.process.JDFBarcodeReproParams");
        sm_PackageNames.put("BarcodeProductionParams",           "org.cip4.jdflib.resource.process.JDFBarcodeProductionParams");
        sm_PackageNames.put("BasicPreflightTest",                "org.cip4.jdflib.resource.devicecapability.JDFBasicPreflightTest");
        sm_PackageNames.put("BendingParams",                     "org.cip4.jdflib.resource.process.JDFBendingParams");
        sm_PackageNames.put("BinderMaterial",                    "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("BinderySignature",                  "org.cip4.jdflib.resource.process.JDFBinderySignature");
        sm_PackageNames.put("BindingColor",                      "org.cip4.jdflib.span.JDFSpanNamedColor");
        sm_PackageNames.put("BindingIntent",                     "org.cip4.jdflib.resource.intent.JDFBindingIntent");
        sm_PackageNames.put("BindingLength",                     "org.cip4.jdflib.span.JDFSpanBindingLength");
        sm_PackageNames.put("BindingQualityParams",              "org.cip4.jdflib.resource.process.JDFBindingQualityParams");
        sm_PackageNames.put("BindingSide",                       "org.cip4.jdflib.span.JDFSpanBindingSide");
        sm_PackageNames.put("BindingType",                       "org.cip4.jdflib.span.JDFSpanBindingType");
        sm_PackageNames.put("BindItem",                          "org.cip4.jdflib.resource.JDFBindItem");
        sm_PackageNames.put("BindList",                          "org.cip4.jdflib.resource.JDFBindList");
        sm_PackageNames.put("BlockPreparationParams",            "org.cip4.jdflib.resource.JDFBlockPreparationParams");
        sm_PackageNames.put("BlockThreadSewing",                 "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("BookCase",                          "org.cip4.jdflib.resource.intent.JDFBookCase");
        sm_PackageNames.put("BooleanEvaluation",                 "org.cip4.jdflib.resource.devicecapability.JDFBooleanEvaluation");
        sm_PackageNames.put("BooleanState",                      "org.cip4.jdflib.resource.devicecapability.JDFBooleanState");
        sm_PackageNames.put("BoxApplication",                    "org.cip4.jdflib.resource.process.JDFBoxApplication");
        sm_PackageNames.put("BoxArgument",                       "org.cip4.jdflib.resource.process.JDFBoxArgument");
        sm_PackageNames.put("BoxedQuantity",                     "org.cip4.jdflib.span.JDFIntegerSpan");
        sm_PackageNames.put("BoxFoldAction",                     "org.cip4.jdflib.resource.process.JDFBoxFoldAction");
        sm_PackageNames.put("BoxFoldingParams",                  "org.cip4.jdflib.resource.process.JDFBoxFoldingParams");
        sm_PackageNames.put("BoxPackingParams",                  "org.cip4.jdflib.resource.JDFBoxPackingParams");
        sm_PackageNames.put("BoxShape",                          "org.cip4.jdflib.span.JDFShapeSpan");
        sm_PackageNames.put("BoxToBoxDifference",                "org.cip4.jdflib.resource.process.JDFBoxToBoxDifference");
        sm_PackageNames.put("BrandName",                         "org.cip4.jdflib.span.JDFStringSpan");
        sm_PackageNames.put("Brightness",                        "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("BufferParams",                      "org.cip4.jdflib.resource.JDFBufferParams");
        sm_PackageNames.put("Bundle",                            "org.cip4.jdflib.resource.JDFBundle");
        sm_PackageNames.put("BundleItem",                        "org.cip4.jdflib.resource.JDFBundleItem");
        sm_PackageNames.put("BundlingParams",                    "org.cip4.jdflib.resource.process.postpress.JDFBundlingParams");
        sm_PackageNames.put("BusinessInfo",                      "org.cip4.jdflib.resource.process.JDFBusinessInfo");
        sm_PackageNames.put("BuyerSupplied",                     "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("ByteMap",                           "org.cip4.jdflib.resource.process.JDFByteMap");
        sm_PackageNames.put("call",                              "org.cip4.jdflib.resource.devicecapability.JDFcall");
        sm_PackageNames.put("CartonMaxWeight",                   "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("CartonQuantity",                    "org.cip4.jdflib.span.JDFIntegerSpan");
        sm_PackageNames.put("CartonShape",                       "org.cip4.jdflib.span.JDFShapeSpan");
        sm_PackageNames.put("CartonStrength",                    "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("CaseMakingParams",                  "org.cip4.jdflib.resource.JDFCaseMakingParams");
        sm_PackageNames.put("CasingInParams",                    "org.cip4.jdflib.resource.JDFCasingInParams");
        sm_PackageNames.put("CCITTFaxParams",                    "org.cip4.jdflib.resource.process.JDFCCITTFaxParams");
        sm_PackageNames.put("ChangedAttribute",                  "org.cip4.jdflib.resource.JDFChangedAttribute");
        sm_PackageNames.put("ChangedPath",                       "org.cip4.jdflib.jmf.JDFChangedPath");       
        sm_PackageNames.put("ChannelBinding",                    "org.cip4.jdflib.resource.process.postpress.JDFChannelBinding");
        sm_PackageNames.put("ChannelBindingParams",              "org.cip4.jdflib.resource.process.postpress.JDFChannelBindingParams");
        sm_PackageNames.put("choice",                            "org.cip4.jdflib.resource.devicecapability.JDFchoice");
        sm_PackageNames.put("CIELABMeasuringField",              "org.cip4.jdflib.resource.process.JDFCIELABMeasuringField");
        sm_PackageNames.put("CMYKColor",                         "org.cip4.jdflib.datatypes.JDFCMYKColor");
        sm_PackageNames.put("Coatings",                          "org.cip4.jdflib.span.JDFStringSpan");
        sm_PackageNames.put("CoilBinding",                       "org.cip4.jdflib.resource.process.postpress.JDFCoilBinding");
        sm_PackageNames.put("CoilBindingParams",                 "org.cip4.jdflib.resource.JDFCoilBindingParams");
        sm_PackageNames.put("CoilMaterial",                      "org.cip4.jdflib.span.JDFSpanCoilMaterial");
        sm_PackageNames.put("CollatingItem",                     "org.cip4.jdflib.resource.process.JDFCollatingItem");
        sm_PackageNames.put("CollectingParams",                  "org.cip4.jdflib.resource.process.JDFCollectingParams");
        sm_PackageNames.put("Color",                             "org.cip4.jdflib.resource.process.JDFColor");
        sm_PackageNames.put("ColorantAlias",                     "org.cip4.jdflib.resource.process.JDFColorantAlias");
        sm_PackageNames.put("ColorantControl",                   "org.cip4.jdflib.resource.process.JDFColorantControl");
        sm_PackageNames.put("ColorantCorrection",                "org.cip4.jdflib.resource.JDFColorantCorrection");
        sm_PackageNames.put("ColorantOrder",                     "org.cip4.jdflib.core.JDFSeparationList");
        sm_PackageNames.put("ColorantParams",                    "org.cip4.jdflib.core.JDFSeparationList");
        sm_PackageNames.put("ColorantZoneDetails",               "org.cip4.jdflib.resource.process.JDFColorantZoneDetails");
        sm_PackageNames.put("ColorControlStrip",                 "org.cip4.jdflib.resource.process.JDFColorControlStrip");
        sm_PackageNames.put("ColorCorrectionOp",                 "org.cip4.jdflib.resource.process.prepress.JDFColorCorrectionOp");
        sm_PackageNames.put("ColorCorrectionParams",             "org.cip4.jdflib.resource.process.prepress.JDFColorCorrectionParams");
        sm_PackageNames.put("ColorIntent",                       "org.cip4.jdflib.resource.intent.JDFColorIntent");
        sm_PackageNames.put("ColorMeasurementConditions",        "org.cip4.jdflib.resource.JDFColorMeasurementConditions");
        sm_PackageNames.put("ColorName",                         "org.cip4.jdflib.span.JDFSpanNamedColor");
        sm_PackageNames.put("ColorPool",                         "org.cip4.jdflib.resource.process.JDFColorPool");
        sm_PackageNames.put("ColorSpaceConversionOp",            "org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionOp");
        sm_PackageNames.put("ColorSpaceConversionParams",        "org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams");
        sm_PackageNames.put("ColorSpaceSubstitute",              "org.cip4.jdflib.resource.process.prepress.JDFColorSpaceSubstitute");
        sm_PackageNames.put("ColorsResultsPool",                 "org.cip4.jdflib.resource.process.JDFColorsResultsPool");
        sm_PackageNames.put("ColorStandard",                     "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("ColorsUsed",                        "org.cip4.jdflib.core.JDFSeparationList");
        sm_PackageNames.put("ColorType",                         "org.cip4.jdflib.span.JDFSpanColorType");
        sm_PackageNames.put("ComChannel",                        "org.cip4.jdflib.resource.process.JDFComChannel");
        sm_PackageNames.put("Command",                           "org.cip4.jdflib.jmf.JDFCommand");
        sm_PackageNames.put("Comment",                           "org.cip4.jdflib.core.JDFComment");
        sm_PackageNames.put("Company",                           "org.cip4.jdflib.resource.process.JDFCompany");
        sm_PackageNames.put("Component",                         "org.cip4.jdflib.resource.process.JDFComponent");
        sm_PackageNames.put("ConstraintValue",                   "org.cip4.jdflib.resource.process.JDFConstraintValue");
        sm_PackageNames.put("Contact",                           "org.cip4.jdflib.resource.process.JDFContact");
        sm_PackageNames.put("ContactCopyParams",                 "org.cip4.jdflib.resource.JDFContactCopyParams");
        sm_PackageNames.put("Container",                         "org.cip4.jdflib.resource.process.JDFContainer");
        sm_PackageNames.put("ContentData",                       "org.cip4.jdflib.resource.process.JDFContentData");
        sm_PackageNames.put("ContentList",                       "org.cip4.jdflib.resource.process.JDFContentList");
        sm_PackageNames.put("ContentObject",                     "org.cip4.jdflib.resource.process.JDFContentObject");
        sm_PackageNames.put("ConventionalPrintingParams",        "org.cip4.jdflib.resource.process.JDFConventionalPrintingParams");
        sm_PackageNames.put("CostCenter",                        "org.cip4.jdflib.resource.process.JDFCostCenter");
        sm_PackageNames.put("CounterReset",                      "org.cip4.jdflib.resource.JDFCounterReset");
        sm_PackageNames.put("Cover",                             "org.cip4.jdflib.resource.process.JDFCover");
        sm_PackageNames.put("Coverage",                          "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("CoverApplicationParams",            "org.cip4.jdflib.resource.JDFCoverApplicationParams");
        sm_PackageNames.put("CoverColor",                        "org.cip4.jdflib.span.JDFSpanNamedColor");
        sm_PackageNames.put("Crease",                            "org.cip4.jdflib.resource.process.postpress.JDFCrease");
        sm_PackageNames.put("CreasingParams",                    "org.cip4.jdflib.resource.JDFCreasingParams");
        sm_PackageNames.put("Created",                           "org.cip4.jdflib.resource.JDFCreated");
        sm_PackageNames.put("CreateResource",                    "org.cip4.jdflib.jmf.JDFCreateResource");
        sm_PackageNames.put("CreditCard",                        "org.cip4.jdflib.resource.JDFCreditCard");
        sm_PackageNames.put("CustomerInfo",                      "org.cip4.jdflib.core.JDFCustomerInfo");
        sm_PackageNames.put("CustomerMessage",                   "org.cip4.jdflib.core.JDFCustomerMessage");
        sm_PackageNames.put("Cut",                               "org.cip4.jdflib.resource.process.postpress.JDFCut");
        sm_PackageNames.put("CutBlock",                          "org.cip4.jdflib.resource.process.JDFCutBlock");
        sm_PackageNames.put("CutMark",                           "org.cip4.jdflib.resource.process.postpress.JDFCutMark");
        sm_PackageNames.put("CuttingParams",                     "org.cip4.jdflib.resource.JDFCuttingParams");
        sm_PackageNames.put("CutType",                           "org.cip4.jdflib.span.JDFSpanCutType");
        sm_PackageNames.put("CylinderLayout",                    "org.cip4.jdflib.resource.process.JDFCylinderLayout");
        sm_PackageNames.put("CylinderLayoutPreparationParams",   "org.cip4.jdflib.resource.process.JDFCylinderLayoutPreparationParams");
        sm_PackageNames.put("CylinderPosition",                  "org.cip4.jdflib.resource.process.JDFCylinderPosition");
        sm_PackageNames.put("DateTimeEvaluation",                "org.cip4.jdflib.resource.devicecapability.JDFDateTimeEvaluation");
        sm_PackageNames.put("DateTimeState",                     "org.cip4.jdflib.resource.devicecapability.JDFDateTimeState");
        sm_PackageNames.put("DCTParams",                         "org.cip4.jdflib.resource.process.JDFDCTParams");
        sm_PackageNames.put("DBMergeParams",                     "org.cip4.jdflib.resource.process.JDFDBMergeParams");
        sm_PackageNames.put("DBRules",                           "org.cip4.jdflib.resource.process.JDFDBRules");
        sm_PackageNames.put("DBSchema",                          "org.cip4.jdflib.resource.JDFDBSchema");
        sm_PackageNames.put("DBSelection",                       "org.cip4.jdflib.resource.process.JDFDBSelection");
        sm_PackageNames.put("Deleted",                           "org.cip4.jdflib.resource.JDFDeleted");
        sm_PackageNames.put("DeliveryCharge",                    "org.cip4.jdflib.span.JDFSpanDeliveryCharge");
        sm_PackageNames.put("DeliveryIntent",                    "org.cip4.jdflib.resource.intent.JDFDeliveryIntent");
        sm_PackageNames.put("DeliveryParams",                    "org.cip4.jdflib.resource.process.JDFDeliveryParams");
        sm_PackageNames.put("DensityMeasuringField",             "org.cip4.jdflib.resource.process.JDFDensityMeasuringField");
        sm_PackageNames.put("Dependencies",                      "org.cip4.jdflib.resource.process.JDFDependencies");
        sm_PackageNames.put("DevCap",                            "org.cip4.jdflib.resource.devicecapability.JDFDevCap");
        sm_PackageNames.put("DevCapPool",                        "org.cip4.jdflib.resource.devicecapability.JDFDevCapPool");
        sm_PackageNames.put("DevCaps",                           "org.cip4.jdflib.resource.devicecapability.JDFDevCaps");
        sm_PackageNames.put("DevelopingParams",                  "org.cip4.jdflib.resource.JDFDevelopingParams");
        sm_PackageNames.put("Device",                            "org.cip4.jdflib.resource.JDFDevice");
        sm_PackageNames.put("DeviceCap",                         "org.cip4.jdflib.resource.devicecapability.JDFDeviceCap");
        sm_PackageNames.put("DeviceColorantOrder",               "org.cip4.jdflib.core.JDFSeparationList");
        sm_PackageNames.put("DeviceFilter",                      "org.cip4.jdflib.jmf.JDFDeviceFilter");
        sm_PackageNames.put("DeviceInfo",                        "org.cip4.jdflib.jmf.JDFDeviceInfo");
        sm_PackageNames.put("DeviceList",                        "org.cip4.jdflib.resource.JDFDeviceList");
        sm_PackageNames.put("DeviceMark",                        "org.cip4.jdflib.resource.JDFDeviceMark");
        sm_PackageNames.put("DeviceNColor",                      "org.cip4.jdflib.resource.process.JDFDeviceNColor");
        sm_PackageNames.put("DeviceNSpace",                      "org.cip4.jdflib.resource.process.JDFDeviceNSpace");
        sm_PackageNames.put("DieLayout",                         "org.cip4.jdflib.resource.process.JDFDieLayout");
        sm_PackageNames.put("DigitalDeliveryParams",             "org.cip4.jdflib.resource.process.prepress.JDFDigitalDeliveryParams");
        sm_PackageNames.put("DigitalMedia",                      "org.cip4.jdflib.resource.process.JDFDigitalMedia");
        sm_PackageNames.put("DigitalPrintingParams",             "org.cip4.jdflib.resource.process.JDFDigitalPrintingParams");
        sm_PackageNames.put("Dimensions",                        "org.cip4.jdflib.span.JDFXYPairSpan");
        sm_PackageNames.put("Direction",                         "org.cip4.jdflib.span.JDFSpanDirection");
        sm_PackageNames.put("Disjointing",                       "org.cip4.jdflib.resource.process.JDFDisjointing");
        sm_PackageNames.put("DisplayGroup",                      "org.cip4.jdflib.resource.devicecapability.JDFDisplayGroup");
        sm_PackageNames.put("DisplayGroupPool",                  "org.cip4.jdflib.resource.devicecapability.JDFDisplayGroupPool");
        sm_PackageNames.put("Disposition",                       "org.cip4.jdflib.resource.process.JDFDisposition");
        sm_PackageNames.put("DividingParams",                    "org.cip4.jdflib.resource.process.JDFDividingParams");
        sm_PackageNames.put("DocumentResultsPool",               "org.cip4.jdflib.resource.process.JDFDocumentResultsPool");
        sm_PackageNames.put("Drop",                              "org.cip4.jdflib.resource.process.JDFDrop");
        sm_PackageNames.put("DropIntent",                        "org.cip4.jdflib.resource.intent.JDFDropIntent");
        sm_PackageNames.put("DropItem",                          "org.cip4.jdflib.resource.process.JDFDropItem");
        sm_PackageNames.put("DropItemIntent",                    "org.cip4.jdflib.resource.intent.JDFDropItemIntent");
        sm_PackageNames.put("DurationEvaluation",                "org.cip4.jdflib.resource.devicecapability.JDFDurationEvaluation");
        sm_PackageNames.put("DurationSpan",                      "org.cip4.jdflib.span.JDFDurationSpan");
        sm_PackageNames.put("DurationState",                     "org.cip4.jdflib.resource.devicecapability.JDFDurationState");
        sm_PackageNames.put("DynamicField",                      "org.cip4.jdflib.resource.process.JDFDynamicField");
        sm_PackageNames.put("DynamicInput",                      "org.cip4.jdflib.resource.process.JDFDynamicInput");
        sm_PackageNames.put("Earliest",                          "org.cip4.jdflib.span.JDFTimeSpan");
        sm_PackageNames.put("EdgeAngle",                         "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("EdgeGlue",                          "org.cip4.jdflib.span.JDFSpanGlue");
        sm_PackageNames.put("EdgeGluing",                        "org.cip4.jdflib.resource.JDFEdgeGluing");
        sm_PackageNames.put("EdgeShape",                         "org.cip4.jdflib.span.JDFSpanEdgeShape");
        sm_PackageNames.put("ElementColorParams",                "org.cip4.jdflib.resource.process.JDFElementColorParams");
        sm_PackageNames.put("Emboss",                            "org.cip4.jdflib.resource.JDFEmboss");
        sm_PackageNames.put("EmbossingIntent",                   "org.cip4.jdflib.resource.intent.JDFEmbossingIntent");
        sm_PackageNames.put("EmbossingItem",                     "org.cip4.jdflib.resource.JDFEmbossingItem");
        sm_PackageNames.put("EmbossingParams",                   "org.cip4.jdflib.resource.JDFEmbossingParams");
        sm_PackageNames.put("EmbossingType",                     "org.cip4.jdflib.span.JDFStringSpan");
        sm_PackageNames.put("Employee",                          "org.cip4.jdflib.resource.process.JDFEmployee");
        sm_PackageNames.put("EmployeeDef",                       "org.cip4.jdflib.jmf.JDFEmployeeDef");
        sm_PackageNames.put("EndSheet",                          "org.cip4.jdflib.resource.process.postpress.JDFEndSheet");
        sm_PackageNames.put("EndSheetGluingParams",              "org.cip4.jdflib.resource.JDFEndSheetGluingParams");
        sm_PackageNames.put("EndSheets",                         "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("EnumerationEvaluation",             "org.cip4.jdflib.resource.devicecapability.JDFEnumerationEvaluation");
        sm_PackageNames.put("EnumerationState",                  "org.cip4.jdflib.resource.devicecapability.JDFEnumerationState");
        sm_PackageNames.put("Error",                             "org.cip4.jdflib.resource.JDFError");
        sm_PackageNames.put("ErrorData",                         "org.cip4.jdflib.resource.JDFErrorData");
        sm_PackageNames.put("Event",                             "org.cip4.jdflib.resource.JDFEvent");
        sm_PackageNames.put("ExposedMedia",                      "org.cip4.jdflib.resource.process.JDFExposedMedia");
        sm_PackageNames.put("ExtendedAddress",                   "org.cip4.jdflib.core.JDFComment");
        sm_PackageNames.put("ExternalImpositionTemplate",        "org.cip4.jdflib.resource.process.JDFExternalImpositionTemplate");
        sm_PackageNames.put("ExtraValues",                       "org.cip4.jdflib.resource.process.JDFExtraValues");
        sm_PackageNames.put("FCNKey",                            "org.cip4.jdflib.resource.JDFFCNKey");
        sm_PackageNames.put("FeatureAttribute",                  "org.cip4.jdflib.resource.devicecapability.JDFFeatureAttribute");
        sm_PackageNames.put("FeaturePool",                       "org.cip4.jdflib.resource.devicecapability.JDFFeaturePool");
        sm_PackageNames.put("Feeder",                            "org.cip4.jdflib.resource.process.JDFFeeder");
        sm_PackageNames.put("FeederQualityParams",               "org.cip4.jdflib.resource.process.JDFFeederQualityParams");
        sm_PackageNames.put("FeedingParams",                     "org.cip4.jdflib.resource.process.JDFFeedingParams");
        sm_PackageNames.put("FileAlias",                         "org.cip4.jdflib.resource.process.JDFFileAlias");
        sm_PackageNames.put("FileSpec",                          "org.cip4.jdflib.resource.process.JDFFileSpec");
        sm_PackageNames.put("FileTypeResultsPool",               "org.cip4.jdflib.resource.process.prepress.JDFFileTypeResultsPool");
        sm_PackageNames.put("FinishedDimensions",                "org.cip4.jdflib.span.JDFShapeSpan");
        sm_PackageNames.put("FinishedGrainDirection",            "org.cip4.jdflib.span.JDFSpanFinishedGrainDirection");
        sm_PackageNames.put("FitPolicy",                         "org.cip4.jdflib.resource.JDFFitPolicy");
        sm_PackageNames.put("FlateParams",                       "org.cip4.jdflib.resource.process.JDFFlateParams");
        sm_PackageNames.put("FlushedResources",                  "org.cip4.jdflib.jmf.JDFFlushedResources");
        sm_PackageNames.put("FlushQueueInfo",                    "org.cip4.jdflib.jmf.JDFFlushQueueInfo");
        sm_PackageNames.put("FlushQueueParams",                  "org.cip4.jdflib.jmf.JDFFlushQueueParams");
        sm_PackageNames.put("FlushResourceParams",               "org.cip4.jdflib.jmf.JDFFlushResourceParams");
        sm_PackageNames.put("FoilColor",                         "org.cip4.jdflib.span.JDFSpanNamedColor");
        sm_PackageNames.put("Fold",                              "org.cip4.jdflib.resource.process.postpress.JDFFold");
        sm_PackageNames.put("FolderProduction",                  "org.cip4.jdflib.resource.process.JDFFolderProduction");
        sm_PackageNames.put("FoldingCatalog",                    "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("FoldingIntent",                     "org.cip4.jdflib.resource.intent.JDFFoldingIntent");
        sm_PackageNames.put("FoldingParams",                     "org.cip4.jdflib.resource.process.postpress.JDFFoldingParams");
        sm_PackageNames.put("FoldOperation",                     "org.cip4.jdflib.resource.process.JDFFoldOperation");
        sm_PackageNames.put("FontParams",                        "org.cip4.jdflib.resource.process.JDFFontParams");
        sm_PackageNames.put("FontPolicy",                        "org.cip4.jdflib.resource.process.JDFFontPolicy");
        sm_PackageNames.put("FontsResultsPool",                  "org.cip4.jdflib.resource.process.prepress.JDFFontsResultsPool");
        sm_PackageNames.put("FormatConversionParams",            "org.cip4.jdflib.resource.JDFFormatConversionParams");
        sm_PackageNames.put("FrequencySelection",                "org.cip4.jdflib.span.JDFSpanFrequencySelection");
        sm_PackageNames.put("FrontCoatings",                     "org.cip4.jdflib.span.JDFSpanCoatings");
        sm_PackageNames.put("GangCmdFilter",                     "org.cip4.jdflib.jmf.JDFGangCmdFilter");
        sm_PackageNames.put("GangInfo",                          "org.cip4.jdflib.jmf.JDFGangInfo");
        sm_PackageNames.put("GangQuFilter",                      "org.cip4.jdflib.jmf.JDFGangQuFilter");
        sm_PackageNames.put("GatheringParams",                   "org.cip4.jdflib.resource.JDFGatheringParams");
        sm_PackageNames.put("GeneralID",                         "org.cip4.jdflib.resource.process.JDFGeneralID");
        sm_PackageNames.put("Glue",                              "org.cip4.jdflib.resource.process.postpress.JDFGlue");
        sm_PackageNames.put("GlueApplication",                   "org.cip4.jdflib.resource.process.postpress.JDFGlueApplication");
        sm_PackageNames.put("GlueLine",                          "org.cip4.jdflib.resource.process.postpress.JDFGlueLine");
        sm_PackageNames.put("GlueProcedure",                     "org.cip4.jdflib.span.JDFSpanGlueProcedure");
        sm_PackageNames.put("GlueType",                          "org.cip4.jdflib.span.JDFSpanGlueType");
        sm_PackageNames.put("GluingParams",                      "org.cip4.jdflib.resource.JDFGluingParams");
        sm_PackageNames.put("Grade",                             "org.cip4.jdflib.span.JDFIntegerSpan");
        sm_PackageNames.put("GrainDirection",                    "org.cip4.jdflib.span.JDFSpanGrainDirection");
        sm_PackageNames.put("HalfTone",                          "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("HardCoverBinding",                  "org.cip4.jdflib.resource.JDFHardCoverBinding");
        sm_PackageNames.put("HeadBandApplicationParams",         "org.cip4.jdflib.resource.JDFHeadBandApplicationParams");
        sm_PackageNames.put("HeadBandColor",                     "org.cip4.jdflib.span.JDFSpanNamedColor");
        sm_PackageNames.put("Headbands",                         "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("Height",                            "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("Hole",                              "org.cip4.jdflib.resource.process.postpress.JDFHole");
        sm_PackageNames.put("HoleCount",                         "org.cip4.jdflib.span.JDFIntegerSpan");
        sm_PackageNames.put("HoleLine",                          "org.cip4.jdflib.resource.JDFHoleLine");
        sm_PackageNames.put("HoleList",                          "org.cip4.jdflib.resource.process.postpress.JDFHoleList");
        sm_PackageNames.put("HoleMakingIntent",                  "org.cip4.jdflib.resource.intent.JDFHoleMakingIntent");
        sm_PackageNames.put("HoleMakingParams",                  "org.cip4.jdflib.resource.process.postpress.JDFHoleMakingParams");
//      "HoleType" is context sensitive, see handleOtherElements() and putConstructorToHashMap()
        sm_PackageNames.put("Icon",                              "org.cip4.jdflib.resource.JDFIcon");
        sm_PackageNames.put("IconList",                          "org.cip4.jdflib.resource.JDFIconList");
        sm_PackageNames.put("Identical",                         "org.cip4.jdflib.resource.process.JDFIdentical");
        sm_PackageNames.put("IdentificationField",               "org.cip4.jdflib.resource.process.JDFIdentificationField");
        sm_PackageNames.put("IDInfo",                            "org.cip4.jdflib.jmf.JDFIDInfo");
        sm_PackageNames.put("IDPCover",                          "org.cip4.jdflib.resource.JDFIDPCover");
        sm_PackageNames.put("IDPFinishing",                      "org.cip4.jdflib.resource.process.JDFIDPFinishing");
        sm_PackageNames.put("IDPFolding",                        "org.cip4.jdflib.resource.process.JDFIDPFolding");
        sm_PackageNames.put("IDPHoleMaking",                     "org.cip4.jdflib.resource.process.JDFIDPHoleMaking");
        sm_PackageNames.put("IDPImageShift",                     "org.cip4.jdflib.resource.JDFIDPImageShift");
        sm_PackageNames.put("IDPJobSheet",                       "org.cip4.jdflib.resource.JDFIDPJobSheet");
        sm_PackageNames.put("IDPLayout",                         "org.cip4.jdflib.resource.process.JDFIDPLayout");
        sm_PackageNames.put("IDPrintingParams",                  "org.cip4.jdflib.resource.process.press.JDFIDPrintingParams");
        sm_PackageNames.put("IDPStitching",                      "org.cip4.jdflib.resource.process.JDFIDPStitching");
        sm_PackageNames.put("IDPTrimming",                       "org.cip4.jdflib.resource.process.JDFIDPTrimming");
        sm_PackageNames.put("ImageCompression",                  "org.cip4.jdflib.resource.JDFImageCompression");
        sm_PackageNames.put("ImageCompressionParams",            "org.cip4.jdflib.resource.process.JDFImageCompressionParams");
        sm_PackageNames.put("ImageReplacementParams",            "org.cip4.jdflib.resource.process.JDFImageReplacementParams");
        sm_PackageNames.put("ImageSetterParams",                 "org.cip4.jdflib.resource.process.JDFImageSetterParams");
        sm_PackageNames.put("ImageShift",                        "org.cip4.jdflib.resource.JDFImageShift");
        sm_PackageNames.put("ImageSize",                         "org.cip4.jdflib.span.JDFXYPairSpan");
        sm_PackageNames.put("ImagesResultsPool",                 "org.cip4.jdflib.resource.process.JDFImagesResultsPool");
        sm_PackageNames.put("ImageStrategy",                     "org.cip4.jdflib.span.JDFSpanImageStrategy");
        sm_PackageNames.put("Ink",                               "org.cip4.jdflib.resource.process.prepress.JDFInk");
        sm_PackageNames.put("InkManufacturer",                   "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("InkZoneCalculationParams",          "org.cip4.jdflib.resource.process.prepress.JDFInkZoneCalculationParams");
        sm_PackageNames.put("InkZoneProfile",                    "org.cip4.jdflib.resource.process.prepress.JDFInkZoneProfile");
        sm_PackageNames.put("Insert",                            "org.cip4.jdflib.resource.JDFInsert");
        sm_PackageNames.put("InsertingIntent",                   "org.cip4.jdflib.resource.intent.JDFInsertingIntent");
        sm_PackageNames.put("InsertingParams",                   "org.cip4.jdflib.resource.JDFInsertingParams");
        sm_PackageNames.put("InsertList",                        "org.cip4.jdflib.resource.JDFInsertList");
        sm_PackageNames.put("InsertSheet",                       "org.cip4.jdflib.resource.process.JDFInsertSheet");
        sm_PackageNames.put("IntegerEvaluation",                 "org.cip4.jdflib.resource.devicecapability.JDFIntegerEvaluation");
        sm_PackageNames.put("IntegerList",                       "org.cip4.jdflib.datatypes.JDFIntegerList");
        sm_PackageNames.put("IntegerRange",                      "org.cip4.jdflib.datatypes.JDFIntegerRange");
        sm_PackageNames.put("IntegerRangeList",                  "org.cip4.jdflib.datatypes.JDFIntegerRangeList");
        sm_PackageNames.put("IntegerSpan",                       "org.cip4.jdflib.span.JDFIntegerSpan");
        sm_PackageNames.put("IntegerState",                      "org.cip4.jdflib.resource.devicecapability.JDFIntegerState");
        sm_PackageNames.put("IntentResource",                    "org.cip4.jdflib.resource.intent.JDFIntentResource");
        sm_PackageNames.put("InterpretedPDLData",                "org.cip4.jdflib.resource.process.JDFInterpretedPDLData");
        sm_PackageNames.put("InterpretingParams",                "org.cip4.jdflib.resource.JDFInterpretingParams");
        sm_PackageNames.put("IsPresentEvaluation",               "org.cip4.jdflib.resource.devicecapability.JDFIsPresentEvaluation");
        sm_PackageNames.put("Jacket",                            "org.cip4.jdflib.span.JDFSpanJacket");
        sm_PackageNames.put("JacketingParams",                   "org.cip4.jdflib.resource.JDFJacketingParams");
        sm_PackageNames.put("JapanBind",                         "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("JBIG2Params",                       "org.cip4.jdflib.resource.process.JDFJBIG2Params");
        sm_PackageNames.put("JDFController",                     "org.cip4.jdflib.jmf.JDFJDFController");
        sm_PackageNames.put("Date",                              "org.cip4.jdflib.util.JDFDate");
        sm_PackageNames.put("JDFService",                        "org.cip4.jdflib.jmf.JDFJDFService");
        sm_PackageNames.put("JobField",                          "org.cip4.jdflib.resource.JDFJobField");
        sm_PackageNames.put("JobPhase",                          "org.cip4.jdflib.jmf.JDFJobPhase");
        sm_PackageNames.put("JobSheet",                          "org.cip4.jdflib.resource.JDFJobSheet");
        sm_PackageNames.put("JPEG2000Params",                    "org.cip4.jdflib.resource.process.JDFJPEG2000Params");
        sm_PackageNames.put("KnownMsgQuParams",                  "org.cip4.jdflib.jmf.JDFKnownMsgQuParams");
        sm_PackageNames.put("LabColor",                          "org.cip4.jdflib.datatypes.JDFLabColor");
        sm_PackageNames.put("LabelingParams",                    "org.cip4.jdflib.resource.JDFLabelingParams");
        sm_PackageNames.put("Laminated",                         "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("LaminatingIntent",                  "org.cip4.jdflib.resource.intent.JDFLaminatingIntent");
        sm_PackageNames.put("LaminatingParams",                  "org.cip4.jdflib.resource.JDFLaminatingParams");
        sm_PackageNames.put("LayerDetails",                      "org.cip4.jdflib.resource.JDFLayerDetails");
        sm_PackageNames.put("LayerList",                         "org.cip4.jdflib.resource.JDFLayerList");
        sm_PackageNames.put("Layout",                            "org.cip4.jdflib.resource.process.JDFLayout");
        sm_PackageNames.put("LayoutElement",                     "org.cip4.jdflib.resource.process.JDFLayoutElement");
        sm_PackageNames.put("LayoutElementPart",                 "org.cip4.jdflib.resource.process.JDFLayoutElementPart");
        sm_PackageNames.put("LayoutElementProductionParams",     "org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams");
        sm_PackageNames.put("LayoutIntent",                      "org.cip4.jdflib.resource.intent.JDFLayoutIntent");
        sm_PackageNames.put("LayoutPreparationParams",           "org.cip4.jdflib.resource.JDFLayoutPreparationParams");
        sm_PackageNames.put("Level",                             "org.cip4.jdflib.span.JDFSpanLevel");
        sm_PackageNames.put("Loc",                               "org.cip4.jdflib.resource.devicecapability.JDFLoc");
        sm_PackageNames.put("Location",                          "org.cip4.jdflib.resource.JDFLocation");
        sm_PackageNames.put("LongFold",                          "org.cip4.jdflib.resource.process.JDFLongFold");
        sm_PackageNames.put("LongGlue",                          "org.cip4.jdflib.resource.process.JDFLongGlue");
        sm_PackageNames.put("LongitudinalRibbonOperationParams", "org.cip4.jdflib.resource.process.JDFLongitudinalRibbonOperationParams");
        sm_PackageNames.put("LongPerforate",                     "org.cip4.jdflib.resource.process.JDFLongPerforate");
        sm_PackageNames.put("LongSlit",                          "org.cip4.jdflib.resource.process.JDFLongSlit");
        sm_PackageNames.put("Lot",                               "org.cip4.jdflib.resource.process.JDFLot");
        sm_PackageNames.put("LZWParams",                         "org.cip4.jdflib.resource.process.JDFLZWParams");
        sm_PackageNames.put("macro",                             "org.cip4.jdflib.resource.devicecapability.JDFmacro");
        sm_PackageNames.put("MacroPool",                         "org.cip4.jdflib.resource.devicecapability.JDFMacroPool");
        sm_PackageNames.put("ManualLaborParams",                 "org.cip4.jdflib.resource.process.JDFManualLaborParams");
        sm_PackageNames.put("MarkObject",                        "org.cip4.jdflib.resource.JDFMarkObject");
        sm_PackageNames.put("Material",                          "org.cip4.jdflib.span.JDFStringSpan");
        sm_PackageNames.put("Matrix",                            "org.cip4.jdflib.datatypes.JDFMatrix");
        sm_PackageNames.put("MatrixEvaluation",                  "org.cip4.jdflib.resource.devicecapability.JDFMatrixEvaluation");
        sm_PackageNames.put("MatrixState",                       "org.cip4.jdflib.resource.devicecapability.JDFMatrixState");
        sm_PackageNames.put("Media",                             "org.cip4.jdflib.resource.process.JDFMedia");
        sm_PackageNames.put("MediaColor",                        "org.cip4.jdflib.span.JDFSpanNamedColor");
        sm_PackageNames.put("MediaIntent",                       "org.cip4.jdflib.resource.intent.JDFMediaIntent");
        sm_PackageNames.put("MediaLayers",                       "org.cip4.jdflib.resource.process.JDFMediaLayers");
        sm_PackageNames.put("MediaSource",                       "org.cip4.jdflib.resource.process.JDFMediaSource");
        sm_PackageNames.put("MediaType",                         "org.cip4.jdflib.span.JDFSpanMediaType");
        sm_PackageNames.put("MediaUnit",                         "org.cip4.jdflib.span.JDFSpanMediaUnit");
        sm_PackageNames.put("Merged",                            "org.cip4.jdflib.resource.JDFMerged");
        sm_PackageNames.put("Message",                           "org.cip4.jdflib.jmf.JDFMessage");
        sm_PackageNames.put("MessageService",                    "org.cip4.jdflib.jmf.JDFMessageService");
//      "Method" is context sensitive, see handleOtherElements() and putConstructorToHashMap()
        sm_PackageNames.put("MiscConsumable",                    "org.cip4.jdflib.resource.process.JDFMiscConsumable");
        sm_PackageNames.put("MISDetails",                        "org.cip4.jdflib.resource.process.JDFMISDetails");
        sm_PackageNames.put("Modified",                          "org.cip4.jdflib.resource.JDFModified");
        sm_PackageNames.put("ModifyNodeCmdParams",               "org.cip4.jdflib.jmf.JDFModifyNodeCmdParams");
        sm_PackageNames.put("Module",                            "org.cip4.jdflib.resource.devicecapability.JDFModule");
        sm_PackageNames.put("ModuleCap",                         "org.cip4.jdflib.resource.devicecapability.JDFModuleCap");
        sm_PackageNames.put("ModulePhase",                       "org.cip4.jdflib.resource.JDFModulePhase");
        sm_PackageNames.put("ModulePool",                        "org.cip4.jdflib.resource.devicecapability.JDFModulePool");
        sm_PackageNames.put("ModuleStatus",                      "org.cip4.jdflib.resource.JDFModuleStatus");
        sm_PackageNames.put("MoveResource",                      "org.cip4.jdflib.jmf.JDFMoveResource");
        sm_PackageNames.put("MsgFilter",                         "org.cip4.jdflib.jmf.JDFMsgFilter");
        sm_PackageNames.put("NamedColor",                        "org.cip4.jdflib.span.JDFSpanNamedColor");
        sm_PackageNames.put("NameEvaluation",                    "org.cip4.jdflib.resource.devicecapability.JDFNameEvaluation");
        sm_PackageNames.put("NameRange",                         "org.cip4.jdflib.datatypes.JDFNameRange");
        sm_PackageNames.put("NameRangeList",                     "org.cip4.jdflib.datatypes.JDFNameRangeList");
        sm_PackageNames.put("NameSpan",                          "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("NameState",                         "org.cip4.jdflib.resource.devicecapability.JDFNameState");
        sm_PackageNames.put("NewComment",                        "org.cip4.jdflib.jmf.JDFNewComment");
        sm_PackageNames.put("NewJDFCmdParams",                   "org.cip4.jdflib.jmf.JDFNewJDFCmdParams");
        sm_PackageNames.put("NewJDFQuParams",                    "org.cip4.jdflib.jmf.JDFNewJDFQuParams");
        sm_PackageNames.put("NodeInfo",                          "org.cip4.jdflib.core.JDFNodeInfo");
        sm_PackageNames.put("NodeInfoCmdParams",                 "org.cip4.jdflib.jmf.JDFNodeInfoCmdParams");
        sm_PackageNames.put("NodeInfoQuParams",                  "org.cip4.jdflib.jmf.JDFNodeInfoQuParams");
        sm_PackageNames.put("NodeInfoResp",                      "org.cip4.jdflib.jmf.JDFNodeInfoResp");
        sm_PackageNames.put("not",                               "org.cip4.jdflib.resource.devicecapability.JDFnot");
        sm_PackageNames.put("Notification",                      "org.cip4.jdflib.resource.JDFNotification");
        sm_PackageNames.put("NotificationDef",                   "org.cip4.jdflib.jmf.JDFNotificationDef");
        sm_PackageNames.put("NotificationFilter",                "org.cip4.jdflib.resource.process.JDFNotificationFilter");
        sm_PackageNames.put("NumberEvaluation",                  "org.cip4.jdflib.resource.devicecapability.JDFNumberEvaluation");
        sm_PackageNames.put("NumberingIntent",                   "org.cip4.jdflib.resource.intent.JDFNumberingIntent");
        sm_PackageNames.put("NumberingParam",                    "org.cip4.jdflib.resource.process.JDFNumberingParam");
        sm_PackageNames.put("NumberingParams",                   "org.cip4.jdflib.resource.JDFNumberingParams");
        sm_PackageNames.put("NumberItem",                        "org.cip4.jdflib.resource.JDFNumberItem");
        sm_PackageNames.put("NumberList",                        "org.cip4.jdflib.datatypes.JDFNumberList");
        sm_PackageNames.put("NumberRange",                       "org.cip4.jdflib.datatypes.JDFNumberRange");
        sm_PackageNames.put("NumberRangeList",                   "org.cip4.jdflib.datatypes.JDFNumberRangeList");
        sm_PackageNames.put("NumberSpan",                        "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("NumberState",                       "org.cip4.jdflib.resource.devicecapability.JDFNumberState");
        sm_PackageNames.put("ObjectResolution",                  "org.cip4.jdflib.resource.process.JDFObjectResolution");
        sm_PackageNames.put("ObservationTarget",                 "org.cip4.jdflib.resource.JDFObservationTarget");
        sm_PackageNames.put("Occupation",                        "org.cip4.jdflib.jmf.JDFOccupation");
        sm_PackageNames.put("OCGControl",                        "org.cip4.jdflib.resource.process.JDFOCGControl");
        sm_PackageNames.put("OfferRange",                        "org.cip4.jdflib.core.JDFComment");
        sm_PackageNames.put("Opacity",                           "org.cip4.jdflib.span.JDFSpanOpacity");
        sm_PackageNames.put("OptionSpan",                        "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("or",                                "org.cip4.jdflib.resource.devicecapability.JDFor");
        sm_PackageNames.put("OrderingParams",                    "org.cip4.jdflib.resource.process.JDFOrderingParams");
        sm_PackageNames.put("OrganizationalUnit",                "org.cip4.jdflib.core.JDFComment");
        sm_PackageNames.put("Orientation",                       "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("otherwise",                         "org.cip4.jdflib.resource.devicecapability.JDFotherwise");
        sm_PackageNames.put("Overage",                           "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("PackingIntent",                     "org.cip4.jdflib.resource.intent.JDFPackingIntent");
        sm_PackageNames.put("PackingParams",                     "org.cip4.jdflib.resource.process.JDFPackingParams");
        sm_PackageNames.put("PageAssignedList",                  "org.cip4.jdflib.resource.process.JDFPageAssignedList");
        sm_PackageNames.put("PageCell",                          "org.cip4.jdflib.resource.JDFPageCell");
        sm_PackageNames.put("PageData",                          "org.cip4.jdflib.resource.process.JDFPageData");
        sm_PackageNames.put("PageElement",                       "org.cip4.jdflib.resource.process.JDFPageElement");
        sm_PackageNames.put("PageList",                          "org.cip4.jdflib.resource.JDFPageList");
        sm_PackageNames.put("Pages",                             "org.cip4.jdflib.span.JDFIntegerSpan");
        sm_PackageNames.put("PagesResultsPool",                  "org.cip4.jdflib.resource.process.prepress.JDFPagesResultsPool");
        sm_PackageNames.put("PageVariance",                      "org.cip4.jdflib.span.JDFIntegerSpan");
        sm_PackageNames.put("Pallet",                            "org.cip4.jdflib.resource.JDFPallet");
        sm_PackageNames.put("PalletizingParams",                 "org.cip4.jdflib.resource.JDFPalletizingParams");
        sm_PackageNames.put("PalletMaxHeight",                   "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("PalletMaxWeight",                   "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("PalletQuantity",                    "org.cip4.jdflib.span.JDFIntegerSpan");
        sm_PackageNames.put("PalletSize",                        "org.cip4.jdflib.span.JDFXYPairSpan");
        sm_PackageNames.put("PalletType",                        "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("PalletWrapping",                    "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("Part",                              "org.cip4.jdflib.resource.JDFPart");
        sm_PackageNames.put("PartAmount",                        "org.cip4.jdflib.core.JDFPartAmount");
        sm_PackageNames.put("PartStatus",                        "org.cip4.jdflib.core.JDFPartStatus");
        sm_PackageNames.put("Payment",                           "org.cip4.jdflib.resource.JDFPayment");
        sm_PackageNames.put("PayTerm",                           "org.cip4.jdflib.core.JDFComment");
        sm_PackageNames.put("PDFInterpretingParams",             "org.cip4.jdflib.resource.JDFPDFInterpretingParams");
        sm_PackageNames.put("PDFPathEvaluation",                 "org.cip4.jdflib.resource.devicecapability.JDFPDFPathEvaluation");
        sm_PackageNames.put("PDFPathState",                      "org.cip4.jdflib.resource.devicecapability.JDFPDFPathState");
        sm_PackageNames.put("PDFToPSConversionParams",           "org.cip4.jdflib.resource.process.prepress.JDFPDFToPSConversionParams");
        sm_PackageNames.put("PDFXParams",                        "org.cip4.jdflib.resource.process.JDFPDFXParams");
        sm_PackageNames.put("PDLCreationParams",                 "org.cip4.jdflib.resource.process.JDFPDLCreationParams");
        sm_PackageNames.put("PDLResourceAlias",                  "org.cip4.jdflib.resource.process.prepress.JDFPDLResourceAlias");
        sm_PackageNames.put("Perforate",                         "org.cip4.jdflib.resource.process.JDFPerforate");
        sm_PackageNames.put("PerforatingParams",                 "org.cip4.jdflib.resource.JDFPerforatingParams");
        sm_PackageNames.put("Performance",                       "org.cip4.jdflib.resource.JDFPerformance");
        sm_PackageNames.put("Person",                            "org.cip4.jdflib.resource.process.JDFPerson");
        sm_PackageNames.put("PhaseTime",                         "org.cip4.jdflib.resource.JDFPhaseTime");
        sm_PackageNames.put("PipeParams",                        "org.cip4.jdflib.jmf.JDFPipeParams");
        sm_PackageNames.put("PixelColorant",                     "org.cip4.jdflib.resource.process.JDFPixelColorant");
        sm_PackageNames.put("PlaceHolder",                       "org.cip4.jdflib.resource.JDFPlaceHolderResource");
        sm_PackageNames.put("PlaceHolderResource",               "org.cip4.jdflib.resource.JDFPlaceHolderResource");
        sm_PackageNames.put("PlasticCombBinding",                "org.cip4.jdflib.resource.process.postpress.JDFPlasticCombBinding");
        sm_PackageNames.put("PlasticCombBindingParams",          "org.cip4.jdflib.resource.process.postpress.JDFPlasticCombBindingParams");
        sm_PackageNames.put("PlasticCombType",                   "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("PlateCopyParams",                   "org.cip4.jdflib.resource.process.JDFPlateCopyParams");
        sm_PackageNames.put("Pool",                              "org.cip4.jdflib.pool.JDFPool");
        sm_PackageNames.put("Position",                          "org.cip4.jdflib.resource.process.JDFPosition");
        sm_PackageNames.put("PreflightAction",                   "org.cip4.jdflib.resource.process.JDFPreflightAction");
        sm_PackageNames.put("PreflightAnalysis",                 "org.cip4.jdflib.resource.JDFPreflightAnalysis");
        sm_PackageNames.put("PreflightArgument",                 "org.cip4.jdflib.resource.process.JDFPreflightArgument");
        sm_PackageNames.put("PreflightConstraint",               "org.cip4.jdflib.resource.process.prepress.JDFPreflightConstraint");
        sm_PackageNames.put("PreflightConstraintsPool",          "org.cip4.jdflib.pool.JDFPreflightConstraintsPool");
        sm_PackageNames.put("PreflightDetail",                   "org.cip4.jdflib.resource.process.prepress.JDFPreflightDetail");
        sm_PackageNames.put("PreflightInformation",              "org.cip4.jdflib.resource.process.JDFPreflightInformation");
        sm_PackageNames.put("PreflightInstance",                 "org.cip4.jdflib.resource.process.prepress.JDFPreflightInstance");
        sm_PackageNames.put("PreflightInstanceDetail",           "org.cip4.jdflib.resource.process.prepress.JDFPreflightInstanceDetail");
        sm_PackageNames.put("PreflightInventory",                "org.cip4.jdflib.resource.process.prepress.JDFPreflightInventory");
        sm_PackageNames.put("PreflightParams",                   "org.cip4.jdflib.resource.process.JDFPreflightParams");
        sm_PackageNames.put("PreflightProfile",                  "org.cip4.jdflib.resource.process.prepress.JDFPreflightProfile");
        sm_PackageNames.put("PreflightReport",                   "org.cip4.jdflib.resource.process.JDFPreflightReport");
        sm_PackageNames.put("PreflightReportRulePool",           "org.cip4.jdflib.resource.process.JDFPreflightReportRulePool");
        sm_PackageNames.put("PreflightResultsPool",              "org.cip4.jdflib.pool.JDFPreflightResultsPool");
        sm_PackageNames.put("PRError",                           "org.cip4.jdflib.resource.process.JDFPRError");
        sm_PackageNames.put("Preview",                           "org.cip4.jdflib.resource.process.JDFPreview");
        sm_PackageNames.put("PreviewGenerationParams",           "org.cip4.jdflib.resource.process.prepress.JDFPreviewGenerationParams");
        sm_PackageNames.put("PRGroup",                           "org.cip4.jdflib.resource.process.JDFPRGroup");
        sm_PackageNames.put("PRGroupOccurrence",                 "org.cip4.jdflib.resource.process.JDFPRGroupOccurrence");
        sm_PackageNames.put("PRGroupOccurrenceBase",             "org.cip4.jdflib.resource.process.JDFPRGroupOccurrenceBase");
        sm_PackageNames.put("Pricing",                           "org.cip4.jdflib.resource.intent.JDFPricing");
        sm_PackageNames.put("PrintCondition",                    "org.cip4.jdflib.resource.process.press.JDFPrintCondition");
        sm_PackageNames.put("PrintConditionColor",               "org.cip4.jdflib.resource.process.JDFPrintConditionColor");
        sm_PackageNames.put("PrintPreference",                   "org.cip4.jdflib.span.JDFSpanPrintPreference");
        sm_PackageNames.put("PrintProcess",                      "org.cip4.jdflib.span.JDFSpanPrintProcess");
        sm_PackageNames.put("PrintRollingParams",                "org.cip4.jdflib.resource.process.JDFPrintRollingParams");
        sm_PackageNames.put("PRItem",                            "org.cip4.jdflib.resource.process.JDFPRItem");
        sm_PackageNames.put("PROccurrence",                      "org.cip4.jdflib.resource.process.JDFPROccurrence");
        sm_PackageNames.put("ProcessRun",                        "org.cip4.jdflib.resource.JDFProcessRun");
        sm_PackageNames.put("ProductionIntent",                  "org.cip4.jdflib.resource.intent.JDFProductionIntent");
        sm_PackageNames.put("ProductionPath",                    "org.cip4.jdflib.resource.process.JDFProductionPath");
        sm_PackageNames.put("ProductionSubPath",                 "org.cip4.jdflib.resource.process.JDFProductionSubPath");
        sm_PackageNames.put("ProofingIntent",                    "org.cip4.jdflib.resource.intent.JDFProofingIntent");
        sm_PackageNames.put("ProofingParams",                    "org.cip4.jdflib.resource.process.JDFProofingParams");
        sm_PackageNames.put("ProofItem",                         "org.cip4.jdflib.resource.JDFProofItem");
        sm_PackageNames.put("ProofType",                         "org.cip4.jdflib.span.JDFSpanProofType");
        sm_PackageNames.put("PRRule",                            "org.cip4.jdflib.resource.process.JDFPRRule");
        sm_PackageNames.put("PRRuleAttr",                        "org.cip4.jdflib.resource.process.JDFPRRuleAttr");
        sm_PackageNames.put("PSToPDFConversionParams",           "org.cip4.jdflib.resource.process.prepress.JDFPSToPDFConversionParams");
        sm_PackageNames.put("PublishingIntent",                  "org.cip4.jdflib.resource.intent.JDFPublishingIntent");
        sm_PackageNames.put("QualityControlParams",              "org.cip4.jdflib.resource.process.JDFQualityControlParams");
        sm_PackageNames.put("QualityControlResult",              "org.cip4.jdflib.resource.process.JDFQualityControlResult");
        sm_PackageNames.put("QualityMeasurement",                "org.cip4.jdflib.resource.process.JDFQualityMeasurement");
        sm_PackageNames.put("Query",                             "org.cip4.jdflib.jmf.JDFQuery");
        sm_PackageNames.put("Queue",                             "org.cip4.jdflib.jmf.JDFQueue");
        sm_PackageNames.put("QueueEntry",                        "org.cip4.jdflib.jmf.JDFQueueEntry");
        sm_PackageNames.put("QueueEntryDef",                     "org.cip4.jdflib.jmf.JDFQueueEntryDef");
        sm_PackageNames.put("QueueEntryDefList",                 "org.cip4.jdflib.resource.JDFQueueEntryDefList");
        sm_PackageNames.put("QueueEntryPosParams",               "org.cip4.jdflib.jmf.JDFQueueEntryPosParams");
        sm_PackageNames.put("QueueEntryPriParams",               "org.cip4.jdflib.jmf.JDFQueueEntryPriParams");
        sm_PackageNames.put("QueueFilter",                       "org.cip4.jdflib.jmf.JDFQueueFilter");
        sm_PackageNames.put("QueueSubmissionParams",             "org.cip4.jdflib.jmf.JDFQueueSubmissionParams");
        sm_PackageNames.put("Range",                             "org.cip4.jdflib.core.JDFComment");
        sm_PackageNames.put("RangeType",                         "org.cip4.jdflib.resource.devicecapability.JDFRangeType");
        sm_PackageNames.put("Rectangle",                         "org.cip4.jdflib.datatypes.JDFRectangle");
        sm_PackageNames.put("RectangleEvaluation",               "org.cip4.jdflib.resource.devicecapability.JDFRectangleEvaluation");
        sm_PackageNames.put("RectangleState",                    "org.cip4.jdflib.resource.devicecapability.JDFRectangleState");
        sm_PackageNames.put("Recycled",                          "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("RefElement",                        "org.cip4.jdflib.core.JDFRefElement");
        sm_PackageNames.put("RegisterMark",                      "org.cip4.jdflib.resource.process.JDFRegisterMark");
        sm_PackageNames.put("RegisterRibbon",                    "org.cip4.jdflib.resource.JDFRegisterRibbon");
        sm_PackageNames.put("Registration",                      "org.cip4.jdflib.jmf.JDFRegistration");
        sm_PackageNames.put("Removed",                           "org.cip4.jdflib.resource.JDFRemoved");
        sm_PackageNames.put("RenderingParams",                   "org.cip4.jdflib.resource.process.prepress.JDFRenderingParams");
        sm_PackageNames.put("RequestQueueEntryParams",           "org.cip4.jdflib.jmf.JDFRequestQueueEntryParams");
        sm_PackageNames.put("Required",                          "org.cip4.jdflib.span.JDFTimeSpan");
        sm_PackageNames.put("Resource",                          "org.cip4.jdflib.resource.JDFResource");
        sm_PackageNames.put("ResourceAudit",                     "org.cip4.jdflib.resource.JDFResourceAudit");
        sm_PackageNames.put("ResourceCmdParams",                 "org.cip4.jdflib.jmf.JDFResourceCmdParams");
        sm_PackageNames.put("ResourceDefinitionParams",          "org.cip4.jdflib.resource.process.JDFResourceDefinitionParams");
        sm_PackageNames.put("ResourceInfo",                      "org.cip4.jdflib.jmf.JDFResourceInfo");
        sm_PackageNames.put("ResourceLink",                      "org.cip4.jdflib.core.JDFResourceLink");
        sm_PackageNames.put("ResourceLinkPool",                  "org.cip4.jdflib.pool.JDFResourceLinkPool");
        sm_PackageNames.put("ResourceParam",                     "org.cip4.jdflib.resource.JDFResourceParam");
        sm_PackageNames.put("ResourcePool",                      "org.cip4.jdflib.pool.JDFResourcePool");
        sm_PackageNames.put("ResourcePullParams",                "org.cip4.jdflib.jmf.JDFResourcePullParams");
        sm_PackageNames.put("ResourceQuParams",                  "org.cip4.jdflib.jmf.JDFResourceQuParams");
        sm_PackageNames.put("Response",                          "org.cip4.jdflib.jmf.JDFResponse");
        sm_PackageNames.put("ResubmissionParams",                "org.cip4.jdflib.jmf.JDFResubmissionParams");
        sm_PackageNames.put("Retention",                         "org.cip4.jdflib.resource.process.JDFRetention");
        sm_PackageNames.put("ReturnMethod",                      "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("ReturnQueueEntryParams",            "org.cip4.jdflib.jmf.JDFReturnQueueEntryParams");
        sm_PackageNames.put("RGBColor",                          "org.cip4.jdflib.datatypes.JDFRGBColor");
        sm_PackageNames.put("RingBinding",                       "org.cip4.jdflib.resource.process.postpress.JDFRingBinding");
        sm_PackageNames.put("RingBindingParams",                 "org.cip4.jdflib.resource.process.postpress.JDFRingBindingParams");
        sm_PackageNames.put("RingDiameter",                      "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("RingMechanic",                      "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("RingShape",                         "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("RingSystem",                        "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("RivetsExposed",                     "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("RollStand",                         "org.cip4.jdflib.resource.process.JDFRollStand");
        sm_PackageNames.put("RunList",                           "org.cip4.jdflib.resource.process.JDFRunList");
        sm_PackageNames.put("SaddleStitching",                   "org.cip4.jdflib.resource.process.postpress.JDFSaddleStitching");
        sm_PackageNames.put("SaddleStitchingParams",             "org.cip4.jdflib.resource.process.postpress.JDFSaddleStitchingParams");
        sm_PackageNames.put("ScanParams",                        "org.cip4.jdflib.resource.process.prepress.JDFScanParams");
        sm_PackageNames.put("ScavengerArea",                     "org.cip4.jdflib.resource.JDFScavengerArea");
        sm_PackageNames.put("Score",                             "org.cip4.jdflib.resource.process.postpress.JDFScore");
        sm_PackageNames.put("Scoring",                           "org.cip4.jdflib.span.JDFSpanScoring");
        sm_PackageNames.put("ScreeningParams",                   "org.cip4.jdflib.resource.process.prepress.JDFScreeningParams");
        sm_PackageNames.put("ScreeningIntent",                   "org.cip4.jdflib.resource.intent.JDFScreeningIntent");
        sm_PackageNames.put("ScreeningType",                     "org.cip4.jdflib.span.JDFSpanScreeningType");
        sm_PackageNames.put("ScreenSelector",                    "org.cip4.jdflib.resource.process.JDFScreenSelector");
        sm_PackageNames.put("Sealing",                           "org.cip4.jdflib.resource.process.JDFSealing");
        sm_PackageNames.put("SearchPath",                        "org.cip4.jdflib.core.JDFComment");
        sm_PackageNames.put("SeparationControlParams",           "org.cip4.jdflib.resource.process.JDFSeparationControlParams");
        sm_PackageNames.put("SeparationList",                    "org.cip4.jdflib.core.JDFSeparationList");
        sm_PackageNames.put("SeparationSpec",                    "org.cip4.jdflib.resource.process.JDFSeparationSpec");
        sm_PackageNames.put("set",                               "org.cip4.jdflib.resource.devicecapability.JDFset");
//      "Shape" is context sensitive, see handleOtherElements() and putConstructorToHashMap()
        sm_PackageNames.put("ShapeCut",                          "org.cip4.jdflib.resource.intent.JDFShapeCut");
        sm_PackageNames.put("ShapeCuttingIntent",                "org.cip4.jdflib.resource.intent.JDFShapeCuttingIntent");
        sm_PackageNames.put("ShapeCuttingParams",                "org.cip4.jdflib.resource.JDFShapeCuttingParams");
        sm_PackageNames.put("ShapeDepth",                        "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("ShapeEvaluation",                   "org.cip4.jdflib.resource.devicecapability.JDFShapeEvaluation");
        sm_PackageNames.put("ShapeSpan",                         "org.cip4.jdflib.span.JDFShapeSpan");
        sm_PackageNames.put("ShapeState",                        "org.cip4.jdflib.resource.devicecapability.JDFShapeState");
        sm_PackageNames.put("ShapeType",                         "org.cip4.jdflib.span.JDFSpanShapeType");
        sm_PackageNames.put("Sheet",                             "org.cip4.jdflib.resource.process.JDFLayout");
        sm_PackageNames.put("ShrinkingParams",                   "org.cip4.jdflib.resource.JDFShrinkingParams");
        sm_PackageNames.put("ShutDownCmdParams",                 "org.cip4.jdflib.jmf.JDFShutDownCmdParams");
        sm_PackageNames.put("SideSewing",                        "org.cip4.jdflib.resource.process.postpress.JDFSideSewing");
        sm_PackageNames.put("SideSewingParams",                  "org.cip4.jdflib.resource.process.postpress.JDFSideSewingParams");
        sm_PackageNames.put("SideStitching",                     "org.cip4.jdflib.resource.process.postpress.JDFSideStitching");
        sm_PackageNames.put("Signal",                            "org.cip4.jdflib.jmf.JDFSignal");
        sm_PackageNames.put("Signature",                         "org.cip4.jdflib.resource.process.JDFLayout");
        sm_PackageNames.put("SignatureCell",                     "org.cip4.jdflib.resource.process.JDFSignatureCell");
        sm_PackageNames.put("SizeIntent",                        "org.cip4.jdflib.resource.intent.JDFSizeIntent");
        sm_PackageNames.put("SizePolicy",                        "org.cip4.jdflib.span.JDFSpanSizePolicy");
        sm_PackageNames.put("SoftCoverBinding",                  "org.cip4.jdflib.resource.JDFSoftCoverBinding");
        sm_PackageNames.put("SourceResource",                    "org.cip4.jdflib.resource.process.JDFSourceResource");
        sm_PackageNames.put("Spawned",                           "org.cip4.jdflib.node.JDFSpawned");
        sm_PackageNames.put("SpineBrushing",                     "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("SpineGlue",                         "org.cip4.jdflib.span.JDFSpanGlue");
        sm_PackageNames.put("SpineLevelling",                    "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("SpineMilling",                      "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("SpineNotching",                     "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("SpinePreparationParams",            "org.cip4.jdflib.resource.JDFSpinePreparationParams");
        sm_PackageNames.put("SpineSanding",                      "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("SpineShredding",                    "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("SpineTaping",                       "org.cip4.jdflib.resource.process.postpress.JDFSpineTaping");
        sm_PackageNames.put("SpineTapingParams",                 "org.cip4.jdflib.resource.JDFSpineTapingParams");
        sm_PackageNames.put("StackingParams",                    "org.cip4.jdflib.resource.JDFStackingParams");
        sm_PackageNames.put("Station",                           "org.cip4.jdflib.resource.process.JDFStation");
        sm_PackageNames.put("StatusPool",                        "org.cip4.jdflib.pool.JDFStatusPool");
        sm_PackageNames.put("StatusQuParams",                    "org.cip4.jdflib.jmf.JDFStatusQuParams");
        sm_PackageNames.put("StitchingParams",                   "org.cip4.jdflib.resource.process.postpress.JDFStitchingParams");
        sm_PackageNames.put("StitchNumber",                      "org.cip4.jdflib.span.JDFIntegerSpan");
        sm_PackageNames.put("StockBrand",                        "org.cip4.jdflib.span.JDFStringSpan");
        sm_PackageNames.put("StockType",                         "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("StopPersChParams",                  "org.cip4.jdflib.jmf.JDFStopPersChParams");
        sm_PackageNames.put("Strap",                             "org.cip4.jdflib.resource.JDFStrap");
        sm_PackageNames.put("StrappingParams",                   "org.cip4.jdflib.resource.JDFStrappingParams");
        sm_PackageNames.put("StringEvaluation",                  "org.cip4.jdflib.resource.devicecapability.JDFStringEvaluation");
        sm_PackageNames.put("StringListValue",                   "org.cip4.jdflib.resource.process.JDFStringListValue");
        sm_PackageNames.put("StringSpan",                        "org.cip4.jdflib.span.JDFStringSpan");
        sm_PackageNames.put("StringState",                       "org.cip4.jdflib.resource.devicecapability.JDFStringState");
        sm_PackageNames.put("StripBinding",                      "org.cip4.jdflib.resource.JDFStripBinding");
        sm_PackageNames.put("StripBindingParams",                "org.cip4.jdflib.resource.process.postpress.JDFStripBindingParams");
        sm_PackageNames.put("StripBindingParamsUpdate",          "org.cip4.jdflib.resource.JDFStripBindingParamsUpdate");
        sm_PackageNames.put("StripCellParams",                   "org.cip4.jdflib.resource.process.JDFStripCellParams");
        sm_PackageNames.put("StripMark",                         "org.cip4.jdflib.resource.process.JDFStripMark");
        sm_PackageNames.put("StripMaterial",                     "org.cip4.jdflib.span.JDFSpanStripMaterial");
        sm_PackageNames.put("StrippingParams",                   "org.cip4.jdflib.resource.JDFStrippingParams"); 
        sm_PackageNames.put("SubmissionMethods",                 "org.cip4.jdflib.jmf.JDFSubmissionMethods");
        sm_PackageNames.put("Subscription",                      "org.cip4.jdflib.jmf.JDFSubscription");
//      "Surface" is context sensitive, see handleOtherElements() and putConstructorToHashMap()
        sm_PackageNames.put("SurplusHandling",                   "org.cip4.jdflib.span.JDFSpanSurplusHandling");
        sm_PackageNames.put("SystemTimeSet",                     "org.cip4.jdflib.resource.JDFSystemTimeSet");
        sm_PackageNames.put("TabBindMylar",                      "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("TabBodyCopy",                       "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("TabExtensionDistance",              "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("TabExtensionMylar",                 "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("TabMylarColor",                     "org.cip4.jdflib.span.JDFSpanNamedColor");
        sm_PackageNames.put("Tabs",                              "org.cip4.jdflib.resource.JDFTabs");
        sm_PackageNames.put("Tape",                              "org.cip4.jdflib.resource.JDFTape");
        sm_PackageNames.put("TapeBinding",                       "org.cip4.jdflib.span.JDFOptionSpan");
        sm_PackageNames.put("TapeColor",                         "org.cip4.jdflib.span.JDFSpanTapeColor");
        sm_PackageNames.put("Technology",                        "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("TeethPerDimension",                 "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("Temperature",                       "org.cip4.jdflib.span.JDFSpanTemperature");
        sm_PackageNames.put("Test",                              "org.cip4.jdflib.resource.devicecapability.JDFTest");
        sm_PackageNames.put("TestPool",                          "org.cip4.jdflib.resource.devicecapability.JDFTestPool");
        sm_PackageNames.put("TestRef",                           "org.cip4.jdflib.resource.devicecapability.JDFTestRef");
        sm_PackageNames.put("Texture",                           "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("Thickness",                         "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("ThinPDFParams",                     "org.cip4.jdflib.resource.process.JDFThinPDFParams");
        sm_PackageNames.put("ThreadSealing",                     "org.cip4.jdflib.resource.process.postpress.JDFThreadSealing");
        sm_PackageNames.put("ThreadSealingParams",               "org.cip4.jdflib.resource.JDFThreadSealingParams");
        sm_PackageNames.put("ThreadSewing",                      "org.cip4.jdflib.resource.process.postpress.JDFThreadSewing");
        sm_PackageNames.put("ThreadSewingParams",                "org.cip4.jdflib.resource.process.postpress.JDFThreadSewingParams");
        sm_PackageNames.put("TIFFEmbeddedFile",                  "org.cip4.jdflib.resource.process.JDFTIFFEmbeddedFile");
        sm_PackageNames.put("TIFFFormatParams",                  "org.cip4.jdflib.resource.process.JDFTIFFFormatParams");
        sm_PackageNames.put("TIFFtag",                           "org.cip4.jdflib.resource.process.JDFTIFFtag");
        sm_PackageNames.put("TightBacking",                      "org.cip4.jdflib.span.JDFSpanTightBacking");
        sm_PackageNames.put("Tile",                              "org.cip4.jdflib.resource.process.JDFTile");
        sm_PackageNames.put("TimeSpan",                          "org.cip4.jdflib.span.JDFTimeSpan");
        sm_PackageNames.put("Tool",                              "org.cip4.jdflib.resource.JDFTool");
        sm_PackageNames.put("TrackFilter",                       "org.cip4.jdflib.jmf.JDFTrackFilter");
        sm_PackageNames.put("TrackResult",                       "org.cip4.jdflib.jmf.JDFTrackResult");
        sm_PackageNames.put("Transfer",                          "org.cip4.jdflib.span.JDFSpanTransfer");
        sm_PackageNames.put("TransferCurve",                     "org.cip4.jdflib.resource.process.JDFTransferCurve");
        sm_PackageNames.put("TransferCurvePool",                 "org.cip4.jdflib.resource.process.JDFTransferCurvePool");
        sm_PackageNames.put("TransferCurveSet",                  "org.cip4.jdflib.resource.process.JDFTransferCurveSet");
        sm_PackageNames.put("TransferFunctionControl",           "org.cip4.jdflib.resource.JDFTransferFunctionControl");
        sm_PackageNames.put("TrappingDetails",                   "org.cip4.jdflib.resource.process.prepress.JDFTrappingDetails");
        sm_PackageNames.put("TrappingOrder",                     "org.cip4.jdflib.resource.process.prepress.JDFTrappingOrder");
        sm_PackageNames.put("TrappingParams",                    "org.cip4.jdflib.resource.process.prepress.JDFTrappingParams");
        sm_PackageNames.put("TrapRegion",                        "org.cip4.jdflib.resource.process.JDFTrapRegion");
        sm_PackageNames.put("Trigger",                           "org.cip4.jdflib.jmf.JDFTrigger");
        sm_PackageNames.put("TrimmingParams",                    "org.cip4.jdflib.resource.process.postpress.JDFTrimmingParams");

        sm_PackageNames.put("Underage",                          "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("UpdateJDFCmdParams",                "org.cip4.jdflib.jmf.JDFUpdateJDFCmdParams");
        sm_PackageNames.put("UsageCounter",                      "org.cip4.jdflib.resource.process.JDFUsageCounter");
        sm_PackageNames.put("USWeight",                          "org.cip4.jdflib.span.JDFNumberSpan");

        sm_PackageNames.put("Value",                             "org.cip4.jdflib.resource.JDFValue");
        sm_PackageNames.put("ValueLoc",                          "org.cip4.jdflib.resource.devicecapability.JDFValueLoc");
        sm_PackageNames.put("VeloBinding",                       "org.cip4.jdflib.resource.process.postpress.JDFVeloBinding");
        sm_PackageNames.put("VerificationParams",                "org.cip4.jdflib.resource.JDFVerificationParams");
        sm_PackageNames.put("ViewBinder",                        "org.cip4.jdflib.span.JDFNameSpan");

        sm_PackageNames.put("WakeUpCmdParams",                   "org.cip4.jdflib.jmf.JDFWakeUpCmdParams");
        sm_PackageNames.put("WebInlineFinishingParams",          "org.cip4.jdflib.resource.process.postpress.JDFWebInlineFinishingParams");
        sm_PackageNames.put("Weight",                            "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("when",                              "org.cip4.jdflib.resource.devicecapability.JDFwhen");
        sm_PackageNames.put("WireCombBinding",                   "org.cip4.jdflib.resource.process.postpress.JDFWireCombBinding");
        sm_PackageNames.put("WireCombBindingParams",             "org.cip4.jdflib.resource.process.postpress.JDFWireCombBindingParams");
        sm_PackageNames.put("WireCombMaterial",                  "org.cip4.jdflib.span.JDFSpanWireCombMaterial");
        sm_PackageNames.put("WireCombShape",                     "org.cip4.jdflib.span.JDFSpanWireCombShape");
        sm_PackageNames.put("WrappedQuantity",                   "org.cip4.jdflib.span.JDFIntegerSpan");
        sm_PackageNames.put("WrappingMaterial",                  "org.cip4.jdflib.span.JDFNameSpan");
        sm_PackageNames.put("WrappingParams",                    "org.cip4.jdflib.resource.JDFWrappingParams");

        sm_PackageNames.put("xor",                               "org.cip4.jdflib.resource.devicecapability.JDFxor");
        sm_PackageNames.put("XPosition",                         "org.cip4.jdflib.span.JDFNumberSpan");
        sm_PackageNames.put("XYPair",                            "org.cip4.jdflib.datatypes.JDFXYPair");
        sm_PackageNames.put("XYPairEvaluation",                  "org.cip4.jdflib.resource.devicecapability.JDFXYPairEvaluation");
        sm_PackageNames.put("XYPairRangeList",                   "org.cip4.jdflib.datatypes.JDFXYPairRangeList");
        sm_PackageNames.put("XYPairSpan",                        "org.cip4.jdflib.span.JDFXYPairSpan");
        sm_PackageNames.put("XYPairState",                       "org.cip4.jdflib.resource.devicecapability.JDFXYPairState");

        sm_PackageNames.put("YPosition",                         "org.cip4.jdflib.span.JDFNumberSpan");
    }
    
    /**
     * get/create the associated XMLDocUserData
     * @return the XMLDocUserData of this
     */
    protected XMLDocUserData getXMLDocUserData()
    {        
        return  (XMLDocUserData) getUserData();
    }

    public Node removeChild(Node arg0) throws DOMException
    {
        final XMLDocUserData ud=getXMLDocUserData();
        if(ud!=null)
            ud.clearTargets();
        
        return super.removeChild(arg0);
    }
    
    public Node replaceChild(Node arg0, Node arg1) throws DOMException
    {
        final XMLDocUserData ud=getXMLDocUserData();
        if(ud!=null)
            ud.clearTargets();
        return super.replaceChild(arg0,arg1);
    }

    /**
     * @return the setIgnoreNSDefault; if true no namespaces are heuristically gathered
     */
    public boolean isIgnoreNSDefault()
    {
        return ignoreNSDefault;
    }

    /**
     * if true no namespaces are heuristically gathered
     * @param ignoreNSDefault the setIgnoreNSDefault to set
     */
    public void setIgnoreNSDefault(boolean _setIgnoreNSDefault)
    {
        this.ignoreNSDefault = _setIgnoreNSDefault;
    }
    


}
