/**
 *
 *  Copyright (c)   2002 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *  Author:         Kai Mattern
 *  Titel:          Util.java
 *  Version:        0.1
 *  Description:    The xml Schema is partitioned into many "complex type's" 
 *                  these types have children named "attributes" and "elements"
 *                  this file is for describing all values a "element" can have.
 *
 *  History:        03-13-2002  Kai Mattern started file
 *
 *  TBD:            getMinOccurs should return  int
 *                  isAttributeToAdd - Attribute SettingsPolicy is defined where ??
 *                  isAttributeToAdd - Attribute Locked is defined where ??
 *                  isAttributeToAdd - CommentURL is a Attribute from ???? (its used in JDFElement)
 *                  isAttributeToAdd - DescriptiveName is a Attribute from ??? (its used in JEFElement)
 *                  isAttributeToAdd - xmlns is a Attribute from ???? (its defined in KElement)
 *                  getAllValidElements - COMPLETE TBD
 *                  GetEnumValues - COMPLETE TBD
 *                  getAllValidElements - add all values a element can have
 */

/*
 *   NOTE: This is a little sequence out of the 'Schema' to make comments of the methods more clear
 *   so if you find a 'the complexType', you can find what its about here...
 *
 *   <xs:complexType name="PhaseTimeAudit">
 *       <xs:complexContent>
 *           <xs:extension base="jdf:AuditElement">
 *               <xs:sequence minOccurs="0" maxOccurs="unbounded">
 *                   <xs:group ref="jdf:GenericElements" minOccurs="0" />
 *                   <xs:element name="Device" type="jdf:Device_r" minOccurs="0" />
 *                   <xs:element name="DeviceRef" type="jdf:ResourceRef" minOccurs="0" />
 *                   <xs:element name="Employee" type="jdf:Employee_re" minOccurs="0" />
 *                   <xs:element name="EmployeeRef" type="jdf:ResourceRef" minOccurs="0" />
 *                   <xs:element name="ModulePhase" minOccurs="0">
 *                       <xs:complexType>
 *                           <xs:sequence minOccurs="0" maxOccurs="unbounded">
 *                               <xs:group ref="jdf:GenericElements" minOccurs="0" />
 *                               <xs:element name="Employee" type="jdf:Employee_re" minOccurs="0" />
 *                               <xs:element name="EmployeeRef" type="jdf:ResourceRef" minOccurs="0" />
 *                           </xs:sequence>
 *                           .
 *                           .
 *                           ...etc
 */

//package
package org.cip4.jdflib.generator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.generator.JavaCoreStringUtil.EnumVersion;


//======================================================================================================
//     Util
//======================================================================================================

public class Util
{

    /**
     * This method is for further expansions. There are right now no known "invalid" elements (elements which are
     * defindes, added or what ever, in other classes). To make the generator expandable this method was added and
     * is already in use in method "getAllValidElements". Id there is an element which you dont want to be added in
     * the autoclass. Add it here.
     *
     * @param   String strElementName - Name of the Element to validate
     *
     * @return  boolean - true if the Element is valid : NOTE returns TRUE always at the moment!
     */
    public static boolean isElementToAdd (String elementName, String complexTypeName, boolean hasMessage)
    {
        boolean isValid = true;

        if (elementName.endsWith("Update"))
        {
            // Update Elements will be handled in their 'mother' objects (JDFResource, JDFElement etc)
            isValid = false;
        }
        else if ("ResponseTypeObj".equals(elementName) || "QueryTypeObj".equals(elementName)
                || "AbstractTerms".equals(elementName) || "AbstractStates".equals(elementName)
                || "CommandTypeObj".equals(elementName) || "CommandOrQueryTypeObj".equals(elementName) 
                || "JDF".equals(elementName)
                || "Location".equals(elementName) || "FoldOperation".equals(elementName)
                || "NotificationDetails".equals(elementName))
        {
            // no auto file generation for abstract classes
            isValid = false;
        } 
        else if ("Part".equals (elementName))
        {
            if ( complexTypeName.endsWith("Ref") 
                    || complexTypeName.endsWith("Link")
                    || complexTypeName.endsWith("PartAmount") )
            {
                isValid = false;
            }
        }
        else if ("Queue".equals(elementName))
        {
            if (complexTypeName.startsWith("Acknowledge")
                    || complexTypeName.startsWith("Response")
                    || complexTypeName.startsWith("Signal"))
            {
                isValid = false;
            }
        }
        else if (complexTypeName.equals(elementName))
        {
            if ("Pricing".equals(elementName) || "Module".equals(elementName))
            {
                // Exception for JDFPricing and JDFModule. 
                // These are the only known Elements which can have subelements of the same type
                isValid = true;
            }
            else
            {
                isValid = false;
            }
        }

        return isValid;
    }

    /**
     * Every complex type in the schema has elements and attributes. 
     * Some of the attributes defined in the Schema are already defined 
     * in the lib because they are attributes of a base resource. 
     * These attributes do not need new getter and setter methods. 
     * This Function validates the attribute in an easy way if its already definded in the lib or not.
     * @param  String strAttributeName - Name of the Attribute to "test"
     *
     * @return boolean - true if the attribute is a valid attribute of this complex type
     */
    public static boolean isAttributeToAdd (String strAttributeName, String strComplexTypeName)
    {
        boolean isValid = true;
        
        // Attributes defined in JDFResource (Part of Partitionable Resources)
        // are not meant to be added again (12 Attributes). You can find these Attributes
        // in JDFResource in the Method "OptionalAttributes()"

        // IMPORTANT: If you try to get the optional attributes of an attribute you will get these
        // attributes also. All OptionalAttribute methods in auto classes call super.optionalAttrbutes
        // so in the end you will get them all

        // Table 3-26: Part element, JDFSpec 1.3
        if (!"Part".equals (strComplexTypeName)
                && ("BinderySignatureName".equals (strAttributeName) 
                        || "BlockName".equals (strAttributeName) 
                        || "BundleItemIndex".equals (strAttributeName)
                        || "CellIndex".equals (strAttributeName) 
                        || "Condition".equals (strAttributeName)
                        || (strAttributeName.startsWith("DeliveryUnit") && strAttributeName.length() == ("DeliveryUnit".length()+1))
                        || "DocCopies".equals (strAttributeName) 
                        || "DocIndex".equals (strAttributeName)
                        || "DocRunIndex".equals (strAttributeName) 
                        || "DocSheetIndex".equals (strAttributeName)
                        || "DocTags".equals (strAttributeName) 
                        || "Edition".equals (strAttributeName)
                        || "EditionVersion".equals (strAttributeName) 
                        || "FountainNumber".equals (strAttributeName) 
                        || "ItemNames".equals (strAttributeName)
                        || "LayerIDs".equals (strAttributeName) 
                        || "Location".equals (strAttributeName)
                        || "Option".equals (strAttributeName) 
                        || "PageNumber".equals (strAttributeName)
                        || "PageTags".equals (strAttributeName)
                        || "PartVersion".equals (strAttributeName) 
                        || "PlateLayout".equals (strAttributeName) 
                        || "PreflightRule".equals (strAttributeName)
                        || "PreviewType".equals (strAttributeName) 
                        || "ProductionRun".equals (strAttributeName)
                        || "RibbonName".equals (strAttributeName)
                        || "Run".equals (strAttributeName) 
                        || "RunIndex".equals (strAttributeName)
                        || "RunPage".equals (strAttributeName)
                        || "RunSet".equals (strAttributeName) 
                        || "RunTags".equals (strAttributeName) 
                        || "SectionIndex".equals (strAttributeName) 
                        || "Separation".equals (strAttributeName)
                        || "SetDocIndex".equals (strAttributeName) 
                        || "SetIndex".equals (strAttributeName)
                        || "SetRunIndex".equals (strAttributeName) 
                        || "SetSheetIndex".equals (strAttributeName)
                        || "SetTags".equals (strAttributeName)
                        || "SheetIndex".equals (strAttributeName) 
                        || "SheetName".equals (strAttributeName)
                        || "Side".equals (strAttributeName) 
                        || "SignatureName".equals (strAttributeName)
                        || "Sorting".equals (strAttributeName) 
                        || "SortAmount".equals (strAttributeName) 
                        || "SubRun".equals (strAttributeName) 
                        || "TileID".equals (strAttributeName) 
                        || "WebName".equals (strAttributeName)
                        || "WebProduct".equals (strAttributeName)
                   )
           )
        {
            isValid = false;
        }

        if (!"CutBlock".equals (strComplexTypeName) && "BlockName".equals (strAttributeName))
        {
            isValid = false;
        }

        if (!"Notification".equals (strComplexTypeName) && "Class".equals (strAttributeName))
        //JDFResource Attribute defined in JDFResource.OptionalAttributes()
        {
            isValid = false;
        }

        if (("JDFProcessNode".equals (strComplexTypeName) || "JDFAbstractNode".equals (strComplexTypeName)
          || "DevCapState".equals (strComplexTypeName)    || "AbstractState".equals (strComplexTypeName))
          && "ID".equals (strAttributeName))
        //JDFResource Attribute IMPORTANT defined in JDFElement.OptionalAttributes()
        //Action is JDFElementBased and needs the ID Attribute
        {
            isValid = false;
        }

        //rRefs is defined in JDFResource. But NodeInfo implements it also and is derived from
        //JDFPool - JDFElement. 
        if (!"NodeInfo".equals (strComplexTypeName) && "rRefs".equals (strAttributeName))
        //JDFResource Attribute defined in JDFResource.OptionalAttributes()
        {
            isValid = false;
        }

        if (!strComplexTypeName.equals("PipeParams") && "PipeID".equals (strAttributeName))
            //JDFResource Attriubte defined in JDFResource.OptionalAttributes()
        {
            isValid = false;
        }
        
        if (strComplexTypeName.equals("BaseElement"))
        {
            if ("CommentURL".equals(strAttributeName))
            {
                isValid = false;
            }
            else if ("DescriptiveName".equals(strAttributeName))
            {
                isValid = false;
            }
            else if ("SettingsPolicy".equals(strAttributeName))
            {
                isValid = false;
            }
        }

        return isValid;
    }

    /**
     *  Every complexType inside the Schema has many many elements and attributes. 
     *  To reflect the Schema as closly as possible and the need of it, 
     *  all VALID attributes will be listed inside the SchemaComplexType (as a vector of attributes).
     *  What are Valid attributes? 
     *  A ComplexType can be a Resource for example. 
     *  A Resource has attributes which are listed as a String in the JDFResource file 
     *  but are also listed in the Schema and the Example ComplexType. 
     *  To provide the attribute to be listed twice, it has to "validated". 
     *  This validation isn't more then a question 
     *  (is this attribute already processed in JDFResource or somewhere else?). 
     *  If not, its added to the "ComplexType" unique attribute list.
     * @param schemaElement TODO
     * @param vComplexTypes TODO
     * @param vSimpleType TODO
     * @param parents
     * @param vAppInfoElements
     * @param  SchemaComplexType complexType - The ComplexType the attributes to process for
     * @return SchemaComplexType - The attribute done SchemaComplextype
     */
    public static SchemaComplexType getAllValidAttributes (
            KElement schemaElement, Vector vComplexTypes, VElement vSimpleType, 
            String[] parents, VElement vAppInfoElements, SchemaComplexType complexType)
    {
        String complexTypeName = complexType.m_SchemaComplexTypeName;
        
        VElement vAttributes = 
            collectAllAttributes(schemaElement, complexType, vComplexTypes, complexTypeName);
        
        Iterator attributeIter = vAttributes.iterator();
        while (attributeIter.hasNext())
        {
            final KElement attribute   = (KElement) attributeIter.next();
            
            fillAttributeIntoComplexType(
                attribute, complexTypeName, parents, vAppInfoElements, vSimpleType, complexType);
        }
        
        return complexType;
    }

    private static VElement collectAllAttributes(KElement schemaElement, 
            SchemaComplexType complexType, Vector vComplexTypes, String complexTypeName)
    {
        KElement nKElement;
        VElement vAttributes;
        KElement complexContent = schemaElement.getElement_KElement("xs:complexContent", "", 0);

        // Inline complextypes don't have a 'complexContent' so ...check it out
        if (complexContent != null || "Comment".equals(complexTypeName))
        {
            if (complexContent != null)
            {
                nKElement = complexContent.getElement_KElement("xs:extension", "", 0);
                if (nKElement != null)
                {
                    complexType.m_ExtendOff = getStrExtendsOff(nKElement);
                }
                else
                {
                    nKElement = complexContent.getElement_KElement("xs:restriction", "", 0);
                }
            }
            else // "Comment".equals (strComplexTypeName)
            {
                nKElement = schemaElement.getXPathElement("./xs:simpleContent/xs:extension");
            }

            vAttributes = nKElement.
                getChildrenByTagName("xs:attribute", "", new JDFAttributeMap(), true, true, 0);
            
            // BAD WORKAROUND; this method should check the extend for attributes to add
            if ("ContentObject".equals(complexTypeName) || "MarkObject".equals(complexTypeName))
            {
                for (int i = 0; i < vComplexTypes.size(); i++)
                {
                    SchemaComplexType myComplexType = (SchemaComplexType) vComplexTypes.get(i);

                    if ("PlacedObject".equals(myComplexType.m_SchemaComplexTypeName))
                    {
                        SchemaComplexType schemaCom = new SchemaComplexType(myComplexType.m_kElem
                                .getXPathElement("./xs:complexContent/xs:extension"));
                        vAttributes.appendUnique(schemaCom.m_kElem.getChildrenByTagName(
                                "xs:attribute", "", new JDFAttributeMap(), true, true, 0));
                    }
                }

                vAttributes = removeDoubleEntrys(vAttributes);
            }
        }
        else // (complexContent == null)
        {
            // there are some complexTypes in the schema which actualy don't have anything at all...
            // you have to catch these few special cases here (an example is 'EmptyElement')
            SchemaComplexType nExtension = complexType;

            nKElement = nExtension.m_kElem.getElement_KElement("xs:complexType", "", 0);
            if (nKElement != null)
            {
                KElement kElemTemp = nKElement.getElement_KElement("xs:complexContent", "", 0);
                if (kElemTemp != null)
                {
                    nKElement = kElemTemp.getElement_KElement("xs:extension", "", 0);
                }

                nExtension = new SchemaComplexType(nKElement);
            }
            else
            {
                nKElement = nExtension.m_kElem;
            }

            vAttributes = nKElement.
                getChildrenByTagName("xs:attribute", "", new JDFAttributeMap(),  true, true, 0);
        }
        
        VElement vAttributesGroup = nKElement.
            getChildrenByTagName("xs:attributeGroup", "", new JDFAttributeMap(), true, true, 0);
        
        vAttributes.addAll(groups2Attributes(vAttributesGroup));
        
        return vAttributes;
    }
    

    private static void fillAttributeIntoComplexType(KElement attribute, String complexTypeName, 
            String[] parents, VElement vAppInfoElements, VElement vSimpleType, SchemaComplexType complexType)
    {
        final String attributeName = attribute.getAttribute("name").replace('-', '_');
        
        if (isAttributeToAdd(attributeName, complexTypeName))
        {
            SchemaAttribute schemaAttribute = new SchemaAttribute(attribute);
            
            schemaAttribute.setStrAttributeName(attributeName);
            schemaAttribute.setStrEnumName(attributeName);
            
            attributeHandleVersion(parents, vAppInfoElements, schemaAttribute, complexType);
            
            String type = attribute.getAttribute("type").replace('-', '_');
            schemaAttribute.setStrType(type, vSimpleType);
            schemaAttribute.setStrUse(
                    attribute.getAttribute("use").replace('-', '_'));
            schemaAttribute.setStrFixed(
                    attribute.getAttribute("fixed").replace('-', '_'));
            schemaAttribute.setStrValue(
                    attribute.getAttribute("value").replace('-', '_'));
            
            
            String defaultAttributeValue = attribute.getAttribute("default").replace('-', '_');
            if (!defaultAttributeValue.equals(JDFConstants.EMPTYSTRING))
            {   // if an attribute has a default value, its usage is "optional"
                schemaAttribute.setStrUse("optional");
            }
            schemaAttribute.setStrDefault(defaultAttributeValue);
            
            // Note, the isEnum depends in some cases on the return type !!!
            // The return type must be set first!
            schemaAttribute.setStrReturnType(
                    getReturnType(attributeName, type, true, complexType.isJava, false));
            schemaAttribute.setIsEnum(isEnumAttribute(vSimpleType, schemaAttribute, type));
            schemaAttribute.setVEnumValues(getEnumValues(schemaAttribute, vSimpleType));
            
            complexType.m_vSchemaAttributes.addElement(schemaAttribute);
        }
    }

    /**
     * Every complexType inside the Schema has many many elements and atributes. To reflect the
     * Schema as closly as possible and the need of it, all VALID elements will be listed inside the
     * SchemaComplexType (as a Vector of elements). What are Valid elements? a ComplexType can be a
     * Resource for example. A Resource has elements which are listed in the JDFResource file but
     * are also listed in the Schema and the Example ComplexType. To provide the element to be
     * listed twice, it has to "validated". This validation isn't more then a question (is this
     * elements already processed in JDFResource or somewhere else?). If not, its added to the
     * "ComplexType" unique elements list.
     * @param parents
     * @param vAppInfoElements
     * @param SchemaComplexType
     *            nComplexType - The ComplexType the elements to process for
     * @return SchemaComplexType - The completed element (SchemaComplexType)
     */
    public static SchemaComplexType getAllValidElements (
            String[] parents, VElement vAppInfoElements, SchemaComplexType complexType)
    {
        KElement sequence = getElementSequence(complexType);
        if (sequence != null)
        {
            String minOccurs = sequence.getAttribute("minOccurs", "", "");
            String maxOccurs = sequence.getAttribute("maxOccurs", "", "");
            
            VElement vElements = 
                sequence.getChildrenByTagName("xs:element", "", new JDFAttributeMap(), true, true, 0);
            
            VElement vElementsGroup = 
                sequence.getChildrenByTagName("xs:group", "", new JDFAttributeMap(), true, true, 0);
            
            vElements.addAll(groups2Elements(vElementsGroup));
            
            Iterator elementIter = vElements.iterator();
            while (elementIter.hasNext())
            {
                final KElement element = (KElement) elementIter.next();
                
                fillElementIntoComplexType(element, parents, vAppInfoElements, 
                                            minOccurs, maxOccurs, complexType);
            }
        }

        return complexType;
    }

    private static KElement getElementSequence(SchemaComplexType complexType)
    {
        KElement extension = null;

        KElement complexTypeKElement = complexType.m_kElem;
        if ("xs:element".equals(complexTypeKElement.getNodeName()))
        {
            complexTypeKElement = complexTypeKElement.getElement("xs:complexType", "", 0);
        }
        
        KElement complexContent = complexTypeKElement.getElement("xs:complexContent", "", 0);
        if (complexContent != null)
        {
            extension = complexContent.getElement("xs:extension", "", 0);
            if (extension == null)
            {
                extension = complexContent.getElement("xs:restriction", "", 0);
            }
        }
        else
        {
            extension = complexTypeKElement;
        }
        
        /*
         * assumption for getElement : 
         * we use the element information from either resourcename_r or resourcename_re 
         * 
         * to use the element information from resourcename_rp one has to use getChildByTagName 
         */
        KElement sequence = extension.getElement("xs:sequence", "", 0);
//            KElement sequence = extension.getChildByTagName("xs:sequence", null, 0, null, true, true);
        
        return sequence;
    }


    private static void fillElementIntoComplexType(KElement element, String[] parents, 
            VElement vAppInfoElements, String minOccurs, String maxOccurs, SchemaComplexType complexType)
    {
        String elementName = element.getAttribute("ref");
        if (complexType.isCore || complexType.isNode)
        {
            String name = element.getAttribute("name").replace('-', '_');
            if (!JDFConstants.EMPTYSTRING.equals(name))
            {
                elementName = name;
            }
        }
        
        SchemaElement schemaElem = new SchemaElement(element);
        
        schemaElem.setStrElementName(elementName);
        elementName = schemaElem.getStrElementName();

        if (isElementToAdd(elementName, complexType.m_SchemaComplexTypeName, complexType.hasMessage))
        {
            schemaElem.setStrMinOccurs(minOccurs);
            schemaElem.setStrMaxOccurs(maxOccurs);
            
            elementHandleVersion(parents, vAppInfoElements, schemaElem, complexType);

            elementHandleMinMaxOccurs(parents, vAppInfoElements, schemaElem, complexType);

            schemaElem.setIsOptionalElement();

            schemaElem.setStrElementType(getStrExtendsOff(element));

            String elementType = schemaElem.getStrElementType();
            if ("EnumerationSpan".equals(elementType))
            {
                schemaElem.setIsEnumerationSpan(true);
            }

            schemaElem.setStrReturnType(
                    getReturnType(elementName, elementType, false, complexType.isJava, true));

            complexType.m_vSchemaElements.add(schemaElem);
        }
    }

    /**
     * Every ComplexType is more then one time Present inside the Schema. It can be a: A 'Resource'
     * indicated through '_r' at the end of it's name A 'ResourceElement' indicated through '_re' at
     * the end of it's name A 'PartitionableResource' indicated through '_rp' at the end of it's
     * name A 'BaseElement' indicated through '__' at the end of it's name A 'Message' indicated
     * through '_m' at the end of it's name A 'WhatEver' indicated through nothing at the end of
     * it's name So a ComplexType can have a '__' a '_r' and a '_rp'. At the end, there is only ONE
     * file for this CompleyType. This means All Elements and Attributes for this spezific
     * ComplexType need to take out of these three Vector elements. To make this task a little
     * easier, all names will be shorten ('_r', '_rp', '___' and '_m', '_rp').
     * 
     * @param SchemaComplexType
     *            nComplexType
     * 
     * @return SchemaComplexType - The done element (SchemaComplextype)
     */
    public static SchemaComplexType unifyComplexTypNames (SchemaComplexType nComplexType, String strType)
    {
        boolean isMessage   = "Message".equals (strType);
        boolean isNode      = "Node".equals (strType);

        if (nComplexType.m_kElem.getAttribute ("name", "", "").length () != 0)
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_kElem.getAttribute ("name", "", "");
        }
        else
        {
            // looks like an inlined element (refer to example at beginning of file)
            nComplexType = new SchemaComplexType (nComplexType.m_kElem.getParentNode_KElement ());
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_kElem.getAttribute ("name", "", "");
            nComplexType.isElementOnly = true;
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("__"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 2);
        }

        // NODE: the '_' and '__' NEED this to follow THIS (first '__' then '_' sequence!!
        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_"))
        {
            if (isNode)
            {
                // do nothing
            }
            else
            {
                nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                        nComplexType.m_SchemaComplexTypeName.length () - 1);
                nComplexType.isResource = true;
            }
        }

        if (nComplexType.m_SchemaComplexTypeName.startsWith ("IDPrintingParams_")
                && !"IDPrintingParams_r".equals (nComplexType.m_SchemaComplexTypeName))
        {
            if (!nComplexType.m_SchemaComplexTypeName.endsWith ("Params_ru"))
            {
                nComplexType.m_SchemaComplexTypeName = "IDP"
                        + nComplexType.m_SchemaComplexTypeName.substring (17, nComplexType.m_SchemaComplexTypeName
                                .length ());
            }
        }

        // there are a couple of Device Caps in the Schema. All starting with DeviceCap_
        // all expect one need to cut away this start extension (refer to schema, search for DeviceCap_)
        if (nComplexType.m_SchemaComplexTypeName.startsWith ("DeviceCap_"))
        {
            if (!"DeviceCap_re".equals (nComplexType.m_SchemaComplexTypeName))
            {
                nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (10,
                        nComplexType.m_SchemaComplexTypeName.length ());
            }
        }

        if (nComplexType.m_SchemaComplexTypeName.startsWith ("Delivery_Params"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (15,
                    nComplexType.m_SchemaComplexTypeName.length ());
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_r"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 2);

        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_ru"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 3);
            nComplexType.isPartitionalResource = true;
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("ResLinkPool_"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 12);
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_rue"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 4);
            nComplexType.isPartitionalResource = true;
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_rp"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 3);
            nComplexType.isPartitionalResource = true;
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_l"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 2);
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_re"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 3);
            nComplexType.isResourceElement = true;
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_lu"))
        {
            nComplexType.m_SchemaComplexTypeName = "";
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_lr"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 3);
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_m"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 2);
            if ("TriggerElement".equals (nComplexType.m_SchemaComplexTypeName) ||
                "SubscriptionElement".equals (nComplexType.m_SchemaComplexTypeName))
            {
                // cut off Element
                nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.
                    substring (0, nComplexType.m_SchemaComplexTypeName.length () - 7);
            }
            nComplexType.hasMessage = true;
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_u"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 2);
            int iIndexOf = nComplexType.m_SchemaComplexTypeName.indexOf ("_");
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (iIndexOf + 1,
                    nComplexType.m_SchemaComplexTypeName.length ());
        }

        // the extension is cutt off not its time to write this name as a paramater into the
        // complex type. This is needed to find its parent and the version information
        nComplexType.strVersionInfoPath = nComplexType.m_SchemaComplexTypeName;

        if (nComplexType.m_SchemaComplexTypeName.startsWith ("BindingIntent"))
        {
            if (!nComplexType.m_SchemaComplexTypeName.endsWith ("BindingIntent"))
            {
                nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (13,
                        nComplexType.m_SchemaComplexTypeName.length ());
            }
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("_Type"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 5);
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("Element_m"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 9);
        }

        if (nComplexType.m_SchemaComplexTypeName.endsWith ("Ack"))
        {
            nComplexType.m_SchemaComplexTypeName = "A"
                    + nComplexType.m_SchemaComplexTypeName.substring (0, nComplexType.m_SchemaComplexTypeName
                            .length () - 3);
            nComplexType.isAcknowledge = true;
        }

        if ((nComplexType.m_SchemaComplexTypeName.endsWith ("Audit"))
                && (!nComplexType.m_SchemaComplexTypeName.endsWith ("ResourceAudit")))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (0,
                    nComplexType.m_SchemaComplexTypeName.length () - 5);
            nComplexType.isAudit = true;
        }
        else if (nComplexType.m_SchemaComplexTypeName.endsWith ("Command") && isMessage)
        {
            nComplexType.m_SchemaComplexTypeName = "C"
                    + nComplexType.m_SchemaComplexTypeName.substring (0, nComplexType.m_SchemaComplexTypeName
                            .length () - 7);

            nComplexType.isCommand = true;
        }
        else if (nComplexType.m_SchemaComplexTypeName.endsWith ("Response") && isMessage)
        {
            nComplexType.m_SchemaComplexTypeName = "R"
                    + nComplexType.m_SchemaComplexTypeName.substring (0, nComplexType.m_SchemaComplexTypeName
                            .length () - 8);
            nComplexType.isResponse = true;
        }
        else if (nComplexType.m_SchemaComplexTypeName.endsWith ("Signal") && isMessage)
        {
            nComplexType.m_SchemaComplexTypeName = "S"
                    + nComplexType.m_SchemaComplexTypeName.substring (0, nComplexType.m_SchemaComplexTypeName
                            .length () - 6);
            nComplexType.isSignal = true;
        }
        else if (nComplexType.m_SchemaComplexTypeName.endsWith ("Query") && isMessage)
        {
            nComplexType.m_SchemaComplexTypeName = "Q"
                    + nComplexType.m_SchemaComplexTypeName.substring (0, nComplexType.m_SchemaComplexTypeName
                            .length () - 5);
            nComplexType.isQuery = true;
        }
        else if (nComplexType.m_SchemaComplexTypeName.startsWith ("Generic"))
        {
            nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (7,
                    nComplexType.m_SchemaComplexTypeName.length ());
        }
        else if (nComplexType.m_SchemaComplexTypeName.startsWith ("AuditElement"))
        {
            nComplexType.m_SchemaComplexTypeName = "Audit";
        }

        else if (nComplexType.m_SchemaComplexTypeName.startsWith ("AcknowledgeBaseType"))
        {
            nComplexType.m_SchemaComplexTypeName = "Acknowledge";
        }

        else if (nComplexType.m_SchemaComplexTypeName.startsWith ("JMFRootMessage"))
        {
            nComplexType.m_SchemaComplexTypeName = "JMF";
        }

        else if (nComplexType.m_SchemaComplexTypeName.startsWith ("ColorantControl_CO_CP_DCO"))
        {
            nComplexType.m_SchemaComplexTypeName = "SeparationList";
        }

        // some messages are defined in the core part, these need to be changed here!!
        if ("Signal".equals (nComplexType.m_SchemaComplexTypeName)
                || "Acknowledge".equals (nComplexType.m_SchemaComplexTypeName)
                || "Query".equals (nComplexType.m_SchemaComplexTypeName)
                || "Registration".equals (nComplexType.m_SchemaComplexTypeName)
                || "Response".equals (nComplexType.m_SchemaComplexTypeName))
        {
            nComplexType.isResource = false;
            nComplexType.isMessage = true;
        }

        if (!nComplexType.isNode)
        {
            int iIndexOf = 0;
            while (iIndexOf != -1)
            {
                iIndexOf = nComplexType.m_SchemaComplexTypeName.indexOf ("_");
                if (iIndexOf != -1)
                {
                    nComplexType.setStrMotherOfComplexType (nComplexType.m_SchemaComplexTypeName.substring (0,
                            iIndexOf));
                }
                else
                {
                    nComplexType.setStrMotherOfComplexType (nComplexType.m_SchemaComplexTypeName);
                }

                nComplexType.m_SchemaComplexTypeName = nComplexType.m_SchemaComplexTypeName.substring (iIndexOf + 1,
                        nComplexType.m_SchemaComplexTypeName.length ());
            }
        }

        if ("Shape".equals (nComplexType.m_SchemaComplexTypeName))
        {
            nComplexType.m_SchemaComplexTypeName = "ShapeElement";
        }

        return nComplexType;
    } // unifyComplexTypNames

    /**
     *  Most Attributes are tied together to groups. 
     *  These groups are just referenced inside the ComplexTyps
     *  To generate the file you need the Attributes out of these references. 
     *  So this method expects a group as input-paramter and will return you all attributes in the group 
     *  (also with recursive call if there were attribute groups in the group)
     *  
     *  @param  VElement - vector with groups (of attributes) to resolve into members
     *
     *  @return VElement - vector of all attributes
     */
    private static VElement groups2Attributes (VElement vAttributesGroup)
    {
        VElement v = new VElement();

        Iterator attributesGroupIter = vAttributesGroup.iterator();
        while (attributesGroupIter.hasNext())
        {
            final KElement attributesGroup = (KElement) attributesGroupIter.next();
            String ref = attributesGroup.getAttribute("ref", "", "").substring("jdf:".length());
    
            if (isAttributesGroupToAdd(ref))
            {
                KElement nKElement = attributesGroup.getDocRoot().
                    getChildWithAttribute("xs:attributeGroup", "name", "", ref, 0, false);
    
                if (nKElement != null)
                {
                    VElement vAttributes = nKElement.
                        getChildrenByTagName("xs:attribute", "", new JDFAttributeMap(), true, true, 0);
                    v.addAll(vAttributes);
       
                    vAttributesGroup = nKElement.
                        getChildrenByTagName("xs:attributeGroup", "", new JDFAttributeMap(), true, true, 0);
    
                    v.addAll(groups2Attributes(vAttributesGroup));
                }
            }
        }

        return v;
    }

    /**
     *  Most Attributes are tied together to groups. These groups are just referenced inside the ComplexTyps
     *  To generate the file you need the Attributes out of these references. 
     *  So this Mehtod expects a group as input-paramter and will return you all Attributes in the group 
     *  (also with recursive call if there were Attribute groups in the group)
     *
     *  @param  VElement - vector with groups (of elements) to resolve into members
     *
     *  @return VElement - vector of all elements
     */
    private static VElement groups2Elements (VElement vElementsGroup)
    {
        VElement v = new VElement();

        Iterator elementsGroupIter = vElementsGroup.iterator();
        while (elementsGroupIter.hasNext())
        {
            final KElement elementsGroup = (KElement) elementsGroupIter.next();
            String ref = elementsGroup.getAttribute("ref", "", "").substring("jdf:".length());

            if (isElementsGroupToAdd(ref))
            {
                KElement nKElement = elementsGroup.getDocRoot().
                    getChildWithAttribute("xs:group", "name", "", ref, 0, false);

                if (nKElement != null)
                {
                    KElement seqElem = nKElement.
                        getChildByTagName("xs:sequence", "", 0, new JDFAttributeMap(), true, true);
                    VElement vElements = seqElem.
                        getChildrenByTagName("xs:element", "", new JDFAttributeMap(), true, true, 0);
                    v.addAll(vElements);

                    vElementsGroup = nKElement.
                        getChildrenByTagName("xs:group", "", new JDFAttributeMap(), true, true, 0);
                    
                    v.addAll(groups2Elements(vElementsGroup));
                }
            }
        }

        return v;
    }

    /**
     * Most Attributes are tied together to groups. These groups are just referenced inside the
     * ComplexTypes. To generate the file you need the Attributes out of these references. So this
     * Method expects a group as input-paramter and will return you all Attributes in the group
     * (also with recursive call if there where Attribute groups in the group)
     * 
     * @param KElement - the Group to resolve into its members
     * 
     * @return VElement - Vector of all Attributes (Strings)
     */
    private static Vector getEnumValues(SchemaAttribute nSchemaAttribute, VElement nSimpleType)
    {
        Vector vStrEnum = new Vector();
        VElement vEnumElements = new VElement();
        KElement nChild = null;

        if (nSchemaAttribute.getIsEnum())
        {
            nChild = nSchemaAttribute.getm_KElement().getChildByTagName("xs:simpleType", "", 0, new JDFAttributeMap(), true, true);

            if ((nChild != null) && !"Classes".equals(nSchemaAttribute.getStrAttributeName()))
            // is the enum defined right under the attribute ?
            {
                nChild = nChild.getChildByTagName("xs:restriction", "", 0, new JDFAttributeMap(), true, true);
                vEnumElements = nChild.getChildElementVector("*", "", new JDFAttributeMap(), true, 0, false);
                
                for (int i = 0; i < vEnumElements.size(); i++)
                {
                    String strEnumName = ((KElement) vEnumElements.elementAt(i)).getAttribute("value");
                    vStrEnum.addElement(strEnumName);
                }
            }
            else
            {   // looks like its defined in a simple type elsewehere
                String strType = nSchemaAttribute.getStrType();
                nChild = getEnumSimpleType(nSchemaAttribute, strType, nSimpleType);
                if (nChild != null) // this one is the right one
                {
                    nChild = nChild.getChildByTagName("xs:restriction", "", 0, new JDFAttributeMap(), true, true);
                    vEnumElements = nChild.getChildrenByTagName("xs:enumeration", "", new JDFAttributeMap(), true, true, 0);
                    
                    for (int i = 0; i < vEnumElements.size(); i++)
                    {
                        String strEnumName = ((KElement) vEnumElements.elementAt(i)).getAttribute("value");
                        vStrEnum.addElement(strEnumName);
                    }
                }
            }
        }

//        if (vStrEnum.size() > 0 && !"Unknown".equals(vStrEnum.elementAt(0)))
//        {
////            System.out.println(nSchemaAttribute.getStrAttributeName() + "\t" + nSchemaAttribute.getStrEnumName());
//            vStrEnum.insertElementAt("Unknown", 0);
//            
//            for (int i = 1; i < vStrEnum.size(); i++)
//            {
//                if (vStrEnum.elementAt(i).equals("Unknown"))
//                {
//                    vStrEnum.remove(i);
//                    i--;
//                }
//            }
//        }

        return vStrEnum;
    }

    private static KElement getEnumSimpleType (SchemaAttribute sa, String strType, Vector nSimpleTypes)
    {
        if (strType.startsWith("jdftyp:"))
        {
            strType = strType.substring("jdftyp:".length());
        }
        
        if (strType.startsWith("jdf:"))
        {
            strType = strType.substring("jdf:".length());
        }

        KElement nChild = null;
        KElement restrict = null;
        KElement list = null;
        VElement vEnumElements = new VElement();

        for (int i = 0; i < nSimpleTypes.size(); i++)
        {
            KElement nKElement = (KElement) nSimpleTypes.elementAt(i);
            
            if ((strType + "_").equals(nKElement.getAttribute("name", "", ""))
                    || nKElement.getAttribute("name", "", "").equals(strType))
            // found it!
            {
                nChild = (KElement) nSimpleTypes.elementAt(i);
                restrict = nChild.getChildByTagName(
                        "xs:restriction", "", 0, new JDFAttributeMap(), true, true);
                
                if (restrict == null)
                {
                    list = nChild.getChildByTagName(
                            "xs:list", "", 0, new JDFAttributeMap(), true, true);
                    if (list != null)
                    {
                        sa.setIsEnumList(true);
                    }
                }
                else
                {
                    vEnumElements = restrict.getChildrenByTagName(
                            "xs:enumeration", "", new JDFAttributeMap(), true, true, 0);
                }

                if (vEnumElements.size() == 0)
                {
                    if (restrict != null)
                    {
                        return getEnumSimpleType(sa, restrict.getAttribute(
                                "base", null, JDFConstants.EMPTYSTRING), nSimpleTypes);
                    }

                    if (list != null)
                    {
                        return getEnumSimpleType(sa, list.getAttribute(
                                "itemType", null, JDFConstants.EMPTYSTRING), nSimpleTypes);
                    }
                }
                
                return nChild;
            }
        }
        
        return nChild;
    }

    /**
     * Many "complexType"'s inside the vector are multiple times present and sometimes they refer to the same
     * elements and element groups. If you would add all elements they refer you had many of them twice or even
     * more often. To prevent this, all elements will be added unique to a ComplexType.
     *
     * @params SchemaComplexType nNewComplexType - the vector to add all attributes to
     * @params SchemaComplexType nOldComplexType - the source of the attributes
     *
     * @return SchemaComplexType - a complex type where all attributes from "nOldComplexType" are unique
     */
    public static SchemaComplexType addElementUniqueToVector (SchemaComplexType nNewComplexType,
            SchemaComplexType nOldComplexType)
    {
        //get both element vectors
        Vector nNewVector = nNewComplexType.m_vSchemaElements;
        Vector nOldVector = nOldComplexType.m_vSchemaElements;
        //loop over the old vector
        for (int i = 0; i < nOldVector.size (); i++)
        {
            boolean fAdd = true;
            //get one of the old element names
            String strOldName = ((SchemaElement) nOldVector.elementAt (i)).getStrElementName ();
            //and loop with it over the new vector to look if its already in or not
            for (int j = 0; j < nNewVector.size (); j++)
            {
                //get one of the new names
                String strNewName = ((SchemaElement) nNewVector.elementAt (j)).getStrElementName ();
                //compare the old and the new one
                if (strNewName.equals (strOldName))
                {
                    //uhh they are equal..dont add it! Its already in
                    fAdd = false;
                }
            }
            //if its not in, fAdd is still true and the element can be added
            if (fAdd == true)
            {
                nNewVector.add (nOldVector.elementAt (i));
            }
        }
        nNewComplexType.m_vSchemaElements = nNewVector;
        return nNewComplexType;
    }

    /**
     * Many "complexType"'s inside the vector are multiple times present and sometimes they refer to the same
     * attribute groups. If you would add all attributes together you had many of them twice or even more often.
     * To prevent this, all attribute will be added unique to a ComplexType
     *
     * @params SchemaComplexType nNewComplexType - the vector to add all attributes to
     * @params SchemaComplexType nOldComplexType - the source of the attributes
     *
     * @return SchemaComplexType - a complex type where all attributes from "nOldComplexType" are unique
     */
    public static SchemaComplexType addAttributeUniqueToVector (SchemaComplexType nNewComplexType,
            SchemaComplexType nOldComplexType)
    {
        //first, you need both attribute vectors
        Vector nNewVector = nNewComplexType.m_vSchemaAttributes;
        Vector nOldVector = nOldComplexType.m_vSchemaAttributes;

        //loop over the oldVector
        for (int i = 0; i < nOldVector.size (); i++)
        {
            boolean fAdd = true;
            //get the attribute name out of the old vector
            String strOldName = ((SchemaAttribute) nOldVector.elementAt (i)).getStrAttributeName ();
            // now, when you have the old name its time to loop over the new vector to look if its already in
            for (int j = 0; j < nNewVector.size (); j++)
            {
                //get the attribute name
                String strNewName = ((SchemaAttribute) nNewVector.elementAt (j)).getStrAttributeName ();
                //an compare the old with the new
                if (strNewName.equals (strOldName))
                {
                    //if both names are equal, this attribute is already in the new vector and should not be added!
                    //merge attribute informations from old to new
                    mergeAttributeInfo ((SchemaAttribute) nNewVector.elementAt (j), (SchemaAttribute) nOldVector
                            .elementAt (i));
                    fAdd = false;
                }
            }
            if (fAdd == true)
            {
                //this a new attribute...add it!
                nNewVector.add (nOldVector.elementAt (i));
            }
        }
        //change the attribute vector to the new one
        nNewComplexType.m_vSchemaAttributes = nNewVector;
        //and return the complete complex type
        return nNewComplexType;
    }

    /**
     * Every attribute can also be a enumeration. This method checks if the attribute is one
     * further description:
     * This is a little example how a attribute with enumeration looks like. With this in mind it should
     * be quit easy to follow the code:
     *
     *  - <xs:attribute name="StapleShape" use="optional">
     *      - <xs:simpleType>
     *          - <xs:restriction base="xs:NMTOKEN">
     *              <xs:enumeration value="Crown" />
     *              <xs:enumeration value="Overlap" />
     *              <xs:enumeration value="Butted" />
     *              <xs:enumeration value="ClinchOut" />
     *              <xs:enumeration value="Eyelet" />
     *          </xs:restriction>
     *      </xs:simpleType>
     *  </xs:attribute>
     *
     * This Attribute "StapleShape is optinla in use and is an enumeration with 5 possible values
     *
     * @param   SchemaAttrbute nSchemaAttribute - The Attribute to check if it is a enum
     *
     * @return  boolean - true if it is a enumaeration
     */
    public static boolean isEnumAttribute (Vector nSimpleType, SchemaAttribute nSchemaAttribute, String sType)
    {
        String strName = nSchemaAttribute.getStrAttributeName ();
        if ("PresentationDirection".equals (strName)) //its a enum in the schema but its a pattern
        {
            return false;
        }

        boolean isEnum = false;
        //boolean to indicate if this attribute ist an enumeration
        KElement nChild = null; //just a placeholder placeholder
        //you have the attribute so check now if there is a childnode "simpeType" under it. Every enumeration
        //has one.
        nChild = nSchemaAttribute.getm_KElement ().getChildByTagName ("xs:simpleType", "", 0, new JDFAttributeMap (),
                true, true);
        if (nChild != null)
        {
            //looks like there was a childnode so check out if there is the restriction
            nChild = nChild.getChildByTagName ("xs:restriction", "", 0, new JDFAttributeMap (), true, true);
            if (nChild != null)
            {
                //there is one! Its an enum...
                isEnum = true;
            }
        }
        //there are some enum where the enumeration itself is defined ewlsewhere (inside a simpleType)
        //so after we looked if we found a enum right under the attribute, we will check the return type
        //of the attribute if its a enum one.
        if (!isEnum)
        {
            String stringType = sType;
            if ("enum".equals (nSchemaAttribute.getStrReturnType ()))
            {
                isEnum = true;
            }
            else if (sType.startsWith ("jdf:e"))
            {
                isEnum = true;
                stringType = sType.substring (5, sType.length ());
            }
            else if (sType.startsWith ("jdftyp:e"))
            {
                isEnum = true;
                stringType = sType.substring (8, sType.length ());
            }

            else
            {
                if (sType.startsWith ("jdftyp:"))
                {
                    stringType = sType.substring (7, sType.length ());
                }
                for (int i = 0; i < nSimpleType.size (); i++)
                {
                    String name = ((KElement) nSimpleType.elementAt (i)).getAttribute ("name", "", "");
                    if (name.equals (stringType))
                    {
                        isEnum = isHiddenEnum ((KElement) nSimpleType.elementAt (i), nSchemaAttribute, nSimpleType);
                    }
                }

            }
        }
        return isEnum;
    }

    private static boolean isHiddenEnum (KElement simple, SchemaAttribute nSchemaAttribute, Vector nSimpleType)
    {
        String strBase = "";
        KElement k = simple.getChildByTagName ("xs:restriction", null, 0, new JDFAttributeMap (), true, true);
        if (k == null)
        {
            return false;
        }
        strBase = k.getAttribute ("base", null, JDFConstants.EMPTYSTRING);

        if (strBase.startsWith ("jdf:"))
        {
            strBase = strBase.substring (4, strBase.length ());
        }
        if (strBase.startsWith ("jdftyp:"))
        {
            strBase = strBase.substring (7, strBase.length ());
        }
        VElement v = k.getChildrenByTagName ("xs:enumeration", null, new JDFAttributeMap (), true, true, 0);
        if (v.size () > 0)
        {
            return true;
        }
        for (int i = 0; i < nSimpleType.size (); i++)
        {
            String name = ((KElement) nSimpleType.elementAt (i)).getAttribute ("name", "", "");
            if (name.equals (strBase))
            {
                return isHiddenEnum ((KElement) nSimpleType.elementAt (i), nSchemaAttribute, nSimpleType);
            }
        }
        return false;
    }

    /**
     * After all Elements and Attributes are set, there are some information which need to be set
     * These Information are tied to the typ of input file (node, message or core) so all the "rest"
     * information will be set in this file.
     * This method is also a placeholder for further enhancements. If more information will be added to the
     * schema, it can be solved here.
     * @param parents
     * @param vAppInfoElements
     */
    public static SchemaComplexType getRestInfo (
            String[] parents, VElement vAppInfoElements, 
            SchemaComplexType complexType)
    {
        if (complexType.isNode)
        {
            complexType = getNodeUsageString (complexType, parents, vAppInfoElements);
            complexType = getNodeLinkNames (complexType);
        }
        
        return complexType;
    }

    private static SchemaComplexType getNodeLinkNames (SchemaComplexType nSchemaComplexType)
    {
        int iNodeLength = 0;
        String strLinkNames = ",";
        Vector vSchemaElements = nSchemaComplexType.m_vSchemaElements;
        for (int i = 0; i < vSchemaElements.size (); i++)
        {
            SchemaElement nSchemaElement = ((SchemaElement) vSchemaElements.elementAt (i));
            String strName = nSchemaElement.getm_schemaKElem ().getAttribute ("name");
            
            if (strName.endsWith ("Link"))
            {
                strName = strName.substring (0, strName.length () - 4);
            }
            
            strLinkNames += strName;
            iNodeLength += 1;
            if (iNodeLength != vSchemaElements.size ())
            {
                strLinkNames += ",";
            }
        }
        
        nSchemaComplexType.setStrNodeLinkInfo (strLinkNames);
        nSchemaComplexType.setNodeLinkLength (iNodeLength);
        
        return nSchemaComplexType;
    }

    // Some groups are real core attributes and already handled in deeper inheritance classes like JDFResource 
    // or JDFPart. To make sure that these Attributes and elements does not show up in the autofiles
    // this "check" is added. If it is a group where the getter and setter methods for the members already
    // exist, they will not be resolved. 
    private static boolean isAttributesGroupToAdd (String ref)
    {
        boolean isGroupToAdd = true;

        if (ref.endsWith("_u")
                || "PartitionAttribs".equals(ref)
                || "ResourceAttribs".equals(ref)
                || "ResourceElementAttribs".equals(ref)
                || "QuantityAttribs".equals(ref)
                || "PlaceHolderAttribs".equals(ref)
                || "ParameterAttribs".equals(ref)
                || "ImplementationAttribs".equals(ref)
                || "HandlingAttribs".equals(ref)
                || "ConsumableAttribs".equals(ref)
                || "PhysicalResourceAttribs".equals(ref)
                || "IntentAttribs".equals(ref)
                || "ResourcePartAttribs".equals(ref))
        {
            isGroupToAdd = false;
        }

        return isGroupToAdd;
    }

    // Some groups are real core attributes and already handled in deeper inheritance classes like
    // JDFResource
    // or JDFPart. To make sure that these Attributes and elements does not show up in the autofiles
    // this "check" is added. If it is a group where the getter and setter methods for the members already
    // exist, they will not be resolved. 
    private static boolean isElementsGroupToAdd (String ref)
    {
        boolean isGroupToAdd = true;

        if (JDFConstants.EMPTYSTRING.equals(ref) 
                || "GenericElements".equals(ref) 
                || "GenericResourceElements".equals(ref))
        {
            isGroupToAdd = false;
        }
        
        return isGroupToAdd;
    }

    public static String getStrExtendsOff (KElement nKElement)
    {
        String strExtendsOff = JDFConstants.EMPTYSTRING;
        strExtendsOff = nKElement.getAttribute ("base");
        if (!JDFConstants.EMPTYSTRING.equals (strExtendsOff))
        {
            if (strExtendsOff.startsWith ("jdf:"))
            {
                strExtendsOff = strExtendsOff.substring (4, strExtendsOff.length ());
                if (strExtendsOff.endsWith ("_"))
                {
                    strExtendsOff = strExtendsOff.substring (0, strExtendsOff.length () - 1);
                }
            }
            else if (strExtendsOff.startsWith ("jdftyp:"))
            {
                strExtendsOff = strExtendsOff.substring (7, strExtendsOff.length ());
                if (strExtendsOff.endsWith ("_"))
                {
                    strExtendsOff = strExtendsOff.substring (0, strExtendsOff.length () - 1);
                }
            }
        }

        if (JDFConstants.EMPTYSTRING.equals (strExtendsOff))
        {
            strExtendsOff = nKElement.getAttribute ("type");
            if (strExtendsOff.startsWith ("jdf:"))
            {
                strExtendsOff = strExtendsOff.substring (4, strExtendsOff.length ());
            }
            else if (strExtendsOff.startsWith ("jdftyp:"))
            {
                strExtendsOff = strExtendsOff.substring (7, strExtendsOff.length ());
            }
        }
        return strExtendsOff;
    }

    /**
     * This Method defines the return typ for every Attribute or Element in the Schema.
     * @param String strName        -   Name of the Element (only used to construct the return typ
     *                                   for enumeration spans
     * @param String strType        -   Type of Attribute or Element
     * @param boolean isAttribute   -   true if the calling class is SchemaAttribute false for SchemaElement
     * @param boolean isJava        -   true if the return type is used in java. False for C++ 
     */
    private static String getReturnType (String strName, String strType, boolean isAttribute, boolean isJava,
            boolean isElement)
    { // note!!! The Return type will be overwritten if the Attribute is an Enum
        // refer to StringHandling classes.

        String strStartType = strType;
        if (strType.startsWith("jdftyp:"))
        {
            strType = strType.substring(7, strType.length());
        }
        if (strType.startsWith("jdf:"))
        {
            strType = strType.substring(4, strType.length());
        }

        if (strType.endsWith("_r"))
        {
            strType = strType.substring(0, strType.length() - 2);
        }
        if (strType.endsWith("_"))
        {
            strType = strType.substring(0, strType.length() - 1);
        }
        if (strType.endsWith("_re"))
        {
            strType = strType.substring(0, strType.length() - 3);
        }

        if (strType.endsWith("_ru"))
        {
            strType = strType.substring(0, strType.length() - 3);
        }

        if (strType.endsWith("_rue"))
        {
            strType = strType.substring(0, strType.length() - 4);
        }

        if (strType.endsWith("_lu"))
        {
            strType = strType.substring(0, strType.length() - 3);
        }

        if (strType.endsWith("_lr"))
        {
            strType = strType.substring(0, strType.length() - 3);
        }

        if (strType.endsWith("_rp"))
        {
            strType = strType.substring(0, strType.length() - 3);
        }

        if (strType.endsWith("_m"))
        {
            strType = strType.substring(0, strType.length() - 2);
        }

        if (strType.endsWith("_me"))
        {
            strType = strType.substring(0, strType.length() - 3);
        }

        int iFirst = strType.indexOf("_");
        if (iFirst != -1)
        {
            strType = strType.substring(iFirst + 1, strType.length());
        }

        String strReturnType = "";

        if (!isElement)
        {
            strReturnType = "KString";
            if (isJava)
            {
                strReturnType = "String";
            }
            else if (!isJava)
            {
                strReturnType = "KString";
            }
            else
            {
                System.out.println("Attribute or Element isnt Java nor C++!! Error.");
            }
        }

        if (isElement)
        {
            strReturnType = "JDFElement";
        }

        if ("IDPFinishing".equals(strName))
        {
            strReturnType = "JDFIDPFinishing";
        }

        if ("jdf:ColorantControl_CO_CP_DCO_lr".equals(strStartType)
                || "ColorantControl_CO_CP_DCO_lr".equals(strStartType))
        {
            strReturnType = "JDFSeparationList";
        }

        if (!JDFConstants.EMPTYSTRING.equals(strName) && JDFConstants.EMPTYSTRING.equals(strType))
        {
            strReturnType = "JDF" + strName;
        }

        if (!JDFConstants.EMPTYSTRING.equals(strName) && strType.startsWith(strName))
        {
            strReturnType = "JDF" + strName;
        }

        if ("EnumerationSpan".equals(strType))
        {
            strReturnType = "JDFSpan" + strName;

            if ("BindingColor".equals(strName) || "CoverColor".equals(strName)
                    || "BackCoverColor".equals(strName) || "HeadBandColor".equals(strName)
                    || "FoilColor".equals(strName) || "MediaColor".equals(strName)
                    || "ColorName".equals(strName) || "TabMylarColor".equals(strName)
                    || "TapeColor".equals(strName))
            {
                strReturnType = "JDFSpanNamedColor";
            }

            if ("SpineGlue".equals(strName) || "EdgeGlue".equals(strName))
            {
                strReturnType = "JDFSpanGlue";
            }

            if ("BackCoatings".equals(strName) || "FrontCoatings".equals(strName))
            {
                strReturnType = "JDFSpanCoatings";
            }

            if ("BindingLength".equals(strName))
            {
                strReturnType = "JDFSpan" + strName;
            }

            if ("BindingSide".equals(strName))
            {
                strReturnType = "JDFSpan" + strName;
            }

            if ("CoverColor".equals(strName))
            {
                strReturnType = "JDFSpanNamedColor";
            }
        }

        if ("XPath".equals(strName))
        {
            strReturnType = "String";
        }

        if ("Integer0To100".equals(strType))
        {
            strReturnType = "int";
        }
        if ("NMTOKENS".equals(strType) || "languages".equals(strType))
        {
            strReturnType = "VString";
        }
        if ("Integer".equals(strType))
        {
            strReturnType = "int";
        }
        if ("double".equals(strType))
        {
            strReturnType = "double";
        }

        if ("xs:double".equals(strType))
        {
            strReturnType = "double";
        }

        if ("number".equals(strType))
        {
            strReturnType = "double";
        }
        if ("integer".equals(strType))
        {
            strReturnType = "int";
        }
        if ("xs:integer".equals(strType))
        {
            strReturnType = "int";
        }
        if ("boolean".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "boolean";
            }
            else
            {
                strReturnType = "bool";
            }
        }
        if ("xs:boolean".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "boolean";
            }
            else
            {
                strReturnType = "bool";
            }
        }
        if ("NMTOKEN".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }

        }
        if ("xs:NMTOKEN".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }

        }
        if (strStartType.startsWith("jdf:e"))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }

        }
        if ("IDREFS".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "VString"; // VString
            }
            else
            {
                strReturnType = "vKString";
            }
        }
        if ("xs:IDREFS".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "VString"; // VString
            }
            else
            {
                strReturnType = "vKString";
            }
        }
        if ("ID".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }
        }
        if ("xs:ID".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }
        }
        if ("IDREF".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }
        }
        if ("DropIntent_DropItemIntent".equals(strType))
        {
            strReturnType = "JDFDropItemIntent";
        }
        if ("xs:IDREF".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }
        }
        if ("string".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }
        }
        if ("xs:string".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }
        }
        if ("dateTime".equals(strType))
        {
            strReturnType = "JDFDate";
        }
        if ("xs:dateTime".equals(strType))
        {
            strReturnType = "JDFDate";
        }
        if ("duration".equals(strType))
        {
            strReturnType = "JDFDate";
        }
        if ("xs:duration".equals(strType))
        {
            strReturnType = "JDFDate";
        }
        if ("language".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }
        }
        if ("xs:language".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }
        }

        if ("TimeRange".equals(strType))
        {
            strReturnType = "JDFDate";
        }

        if ("StepRepeat".equals(strType))
        {
            strReturnType = "JDFIntegerRange";
        }

        if ("TimeSpan".equals(strType))
        {
            strReturnType = "JDFTimeSpan";
        }

        if ("IntegerSpan".equals(strType))
        {
            strReturnType = "JDFIntegerSpan";
        }

        if ("StringSpan".equals(strType))
        {
            strReturnType = "JDFStringSpan";
        }

        if ("ColorsUsed".equals(strType))
        {
            strReturnType = "JDFSeparationList";
        }

        if ("ShapeSpan".equals(strType))
        {
            strReturnType = "JDFShapeSpan";
        }

        if ("XYPairSpan".equals(strType))
        {
            strReturnType = "JDFXYPairSpan";
        }

        if ("NameIntent".equals(strType))
        {
            strReturnType = "JDFNameSpan";
        }

        if ("NameSpan".equals(strType))
        {
            strReturnType = "JDFNameSpan";
        }

        if ("CMYKColor".equals(strType))
        {
            strReturnType = "JDFCMYKColor";
        }

        if ("LabColor".equals(strType))
        {
            strReturnType = "JDFLabColor";
        }

        if ("sRGBColor".equals(strType))
        {
            strReturnType = "JDFRGBColor";
        }

        if ("XYPair".equals(strType))
        {
            strReturnType = "JDFXYPair";
        }

        if ("shape".equals(strType))
        {
            strReturnType = "JDFShape";
        }

        if ("matrix".equals(strType))
        {
            strReturnType = "JDFMatrix";
        }

        if ("rectangle".equals(strType))
        {
            strReturnType = "JDFRectangle";
        }

        if ("NumberIntent".equals(strType))
        {
            strReturnType = "JDFNumberSpan";
        }

        if ("NumberSpan".equals(strType))
        {
            strReturnType = "JDFNumberSpan";
        }

        if ("OptionSpan".equals(strType))
        {
            strReturnType = "JDFOptionSpan";
        }

        if ("OptionIntent".equals(strType))
        {
            strReturnType = "JDFOptionSpan";
        }

        if ("DeviceList".equals(strType))
        {
            strReturnType = "JDFDeviceList";
        }

        if ("IntegerIntent".equals(strType))
        {
            strReturnType = "JDFIntegerSpan";
        }

        if ("StringIntent".equals(strType))
        {
            strReturnType = "JDFStringSpan";
        }

        if ("TimeIntent".equals(strType))
        {
            strReturnType = "JDFTimeSpan";
        }

        if ("Comment_Type".equals(strType))
        {
            strReturnType = "JDFComment";
        }

        if ("Comment".equals(strType))
        {
            strReturnType = "JDFComment";
        }

        if ("IntegerRangeList".equals(strType))
        {
            strReturnType = "JDFIntegerRangeList";
        }

        if ("IntegerList".equals(strType))
        {
            strReturnType = "JDFIntegerList";
        }

        if ("IntegerRange".equals(strType))
        {
            strReturnType = "JDFIntegerRange";
        }

        if ("NumberList".equals(strType))
        {
            strReturnType = "JDFNumberList";
        }

        if ("TransferFunction".equals(strType))
        {
            strReturnType = "JDFNumberList";
        }

        if ("NumberRangeList".equals(strType))
        {
            strReturnType = "JDFNumberRangeList";
        }

        if ("NumberRange".equals(strType))
        {
            strReturnType = "JDFNumberRange";
        }

        if ("NameRangeList".equals(strType))
        {
            strReturnType = "JDFNameRangeList";
        }

        if ("NameRange".equals(strType))
        {
            strReturnType = "JDFNameRange";
        }

        if ("XYPairRangeList".equals(strType))
        {
            strReturnType = "JDFXYPairRangeList";
        }

        if ("XYPairRange".equals(strType))
        {
            strReturnType = "JDFXYPairRange";
        }

        if ("ePartitionKeys".equals(strType))
        {
            strReturnType = "";
        }

        if ("URL".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }
        }

        if ("path".equals(strType))
        {
            if (isJava)
            {
                strReturnType = "String";
            }
            else
            {
                strReturnType = "KString";
            }
        }

        if ("Shape".equals(strType))
        {
            strReturnType = "JDFShapeElement";
        }

        if ("BaseElement".equals(strType))
        {
            strReturnType = "JDFElement";
        }

        if ("AbstractEvaluation".equals(strType))
        {
            strReturnType = "JDFElement";
        }

        if ("EmptyElement".equals(strType))
        {
            strReturnType = "JDFElement";
        }

        if ("telem".equals(strType))
        {
            strReturnType = "JDFElement";
        }

        // for attributes exclusive

        if (isAttribute)
        {
            if ("eSide".equals(strType))
            {
                return "enum";
            }

            if ("eActivation".equals(strType))
            {
                return "enum";
            }

            if ("eSpawnStatus".equals(strType))
            {
                return "enum";
            }

            if ("eDeviceStatus".equals(strType))
            {
                return "enum";
            }

            if ("eNodeStatus".equals(strType))
            {
                return "enum";
            }

            if ("ePoolStatus".equals(strType))
            {
                return "enum";
            }

            if ("eEndStatusOfNode".equals(strType))
            {
                return "enum";
            }

            if ("eResourceStatus".equals(strType))
            {
                return "enum";
            }

            if ("eResourceClass".equals(strType))
            {
                return "enum";
            }

            if ("eNotificationClass".equals(strType))
            {
                return "enum";
            }

            if ("eEndStatusOfNode".equals(strType))
            {
                return "EnumNodeStatus";
            }

            if ("eNotificationClass".equals(strType))
            {
                return "enum";
            }

            if ("eMessage".equals(strType))
            {
                return "enum";
            }

            if ("xs:QName".equals(strType) || "QName".equals(strType))
            {
                if (isJava)
                {
                    return "String";
                }
                return "KString";
            }

            if ("eOwnership".equals(strType))
            {
                return "enum";
            }

            if ("eFinishedPageOrientation".equals(strType))
            {
                return "enum";
            }

            if ("eDrying".equals(strType))
            {
                return "enum";
            }

            if ("eFirstSurface".equals(strType))
            {
                return "enum";
            }

            if ("eAppOS".equals(strType))
            {
                return "enum";
            }

            if ("eCompression".equals(strType))
            {
                return "enum";
            }

            if ("eDisposition".equals(strType))
            {
                return "enum";
            }

            if ("ePageDelivery".equals(strType))
            {
                return "enum";
            }

            if ("eElementType".equals(strType))
            {
                return "enum";
            }

            if ("eCoatings".equals(strType))
            {
                return "enum";
            }

            if ("eImagableSide".equals(strType))
            {
                return "enum";
            }

            if ("eIncludePerPage".equals(strType))
            {
                return "enum";
            }

            if ("eOutputType".equals(strType))
            {
                return "enum";
            }

            if ("eCompensation".equals(strType))
            {
                return "enum";
            }

            if ("eAutoRotatePages".equals(strType))
            {
                return "enum";
            }

            if ("eBinding".equals(strType))
            {
                return "enum";
            }

            if ("eRenderingIntent".equals(strType))
            {
                return "enum";
            }

            if ("eTrapEndStyle".equals(strType))
            {
                return "enum";
            }

            if ("eTrapJoinStyle".equals(strType))
            {
                return "enum";
            }

            if ("eUCRandBGInfo".equals(strType))
            {
                return "enum";
            }

            if ("NamedColor".equals(strType))
            {
                return "EnumNamedColor";
            }

            if ("IDPFinishingsList".equals(strType))
            {
                if (isJava)
                {
                    strReturnType = "String"; // VString
                }
                else
                {
                    strReturnType = "vKString";
                }
            }

            if ("eNodeStati".equals(strType))
            {
                if (isJava)
                {
                    strReturnType = "String"; // VString
                }
                else
                {
                    strReturnType = "vKString";
                }
            }

            if (strType.endsWith("Classes"))
            {
                if (isJava)
                {
                    strReturnType = "String"; // VString
                }
                else
                {
                    strReturnType = "vKString";
                }
            }

        }
        return strReturnType;
    }

    // THIS IS NOT OK, CHANGE IT SO YOU CAN DIF BETWEEN NODE CORE AND MESSAGE
    public static boolean isComplexTypeToGenerate (String strComplexTypeName)
    {
        boolean bGenerateIt = true;
        if ("ParameterLinkIn".equals (strComplexTypeName))
        {
            bGenerateIt = false;
        }
        if ("IntentLinkIn".equals (strComplexTypeName))
        {
            bGenerateIt = false;
        }
        if ("ParameterLinkOut".equals (strComplexTypeName))
        {
            bGenerateIt = false;
        }
        if ("QuantityLinkIn".equals (strComplexTypeName))
        {
            bGenerateIt = false;
        }
        if ("QuantityLinkOut".equals (strComplexTypeName))
        {
            bGenerateIt = false;
        }
        if ("HandlingLinkIn".equals (strComplexTypeName))
        {
            bGenerateIt = false;
        }
        if ("HandlingLinkOut".equals (strComplexTypeName))
        {
            bGenerateIt = false;
        }
        if ("ConsumableLinkIn".equals (strComplexTypeName))
        {
            bGenerateIt = false;
        }
        if ("ConsumableLinkOut".equals (strComplexTypeName))
        {
            bGenerateIt = false;
        }
        if (strComplexTypeName.endsWith ("_lu"))
        {
            bGenerateIt = false;
        }

        int iHasUnderscore = 0;
        iHasUnderscore = strComplexTypeName.indexOf ("_");

        if (iHasUnderscore != -1)
        {
            bGenerateIt = false;
        }
        return bGenerateIt;

    }

    static HashMap typeInfo = new HashMap ();
    static
    {
        typeInfo.put ("booleanList", "string");
        typeInfo.put ("double", "double_");
        typeInfo.put ("DoubleList", "string");
        typeInfo.put ("DoubleList", "string");
        typeInfo.put ("DoubleRange", "string");
        typeInfo.put ("DoubleRangeList", "string");
        typeInfo.put ("gYearMonth", "string");
        typeInfo.put ("Integer", "integer");
        typeInfo.put ("Integer0To100", "integer");
        typeInfo.put ("JDFJMFVersions", "string");
        typeInfo.put ("LanguagesOrAll", "languages");
        typeInfo.put ("LongInteger", "integer");
        typeInfo.put ("longString", "string");
        typeInfo.put ("MatrixShift", "string");
        typeInfo.put ("NamedColor", "string");
        typeInfo.put ("number", "double_");
        typeInfo.put ("OrientationList", "string");
        typeInfo.put ("pBinderySignatureName", "NMTOKEN");
        typeInfo.put ("pBlockName", "NMTOKEN");
        typeInfo.put ("pBundleItemIndex", "IntegerRangeList");
        typeInfo.put ("pCellIndex", "IntegerRangeList");
        typeInfo.put ("pCondition", "NMTOKEN");
        typeInfo.put ("pDocCopies", "IntegerRangeList");
        typeInfo.put ("pDocIndex", "IntegerRangeList");
        typeInfo.put ("pDocRunIndex", "IntegerRangeList");
        typeInfo.put ("pDocSheetIndex", "IntegerRangeList");
        typeInfo.put ("pFountainNumber", "integer");
        typeInfo.put ("pItemNames", "NMTOKENS");
        typeInfo.put ("pLayerIDs", "IntegerRangeList");
        typeInfo.put ("pPageNumber", "IntegerRangeList");
        typeInfo.put ("pRunIndex", "IntegerRangeList");
        typeInfo.put ("pRunPage", "integer");
        typeInfo.put ("pRunTags", "NMTOKENS");
        typeInfo.put ("pSectionIndex", "IntegerRangeList");
        typeInfo.put ("pSeparation", "string");
        typeInfo.put ("pSetDocIndex", "IntegerRangeList");
        typeInfo.put ("pSetIndex", "IntegerRangeList");
        typeInfo.put ("pSetRunIndex", "IntegerRangeList");
        typeInfo.put ("pSetSheetIndex", "IntegerRangeList");
        typeInfo.put ("pSheetIndex", "IntegerRangeList");
        typeInfo.put ("pSheetName", "string");
        typeInfo.put ("pSignatureName", "string");
        typeInfo.put ("pSignatureName", "string");
        typeInfo.put ("pSortAmount", "boolean");
        typeInfo.put ("pSorting", "IntegerRangeList");
        typeInfo.put ("pTileID", "XYPair");
        typeInfo.put ("shortString", "string");
        typeInfo.put ("sRGBColor", "string");
        typeInfo.put ("StepRepeat", "string");
//        typeInfo.put ("pLocation", "string");
//        typeInfo.put ("pOption", "string");
//        typeInfo.put ("pPartVersion", "string");
//        typeInfo.put ("pPreflightRule", "string");
//        typeInfo.put ("pRibbonName", "string");
//        typeInfo.put ("pRun", "string");
//        typeInfo.put ("pWebName", "string");
//        typeInfo.put ("shortString", "shortString");
    }

    public static String getAttributeExt (String m_strType, boolean isEnum)
    {
        AttributeInfo.EnumAttributeType at = AttributeInfo.EnumAttributeType.getEnum (m_strType);

        // if a valid enumType was found we are done no need for further mapping
        if (at != AttributeInfo.EnumAttributeType.Any)
        {
            m_strType = at.getName ();
        }
        else if (isEnum)
        {
            m_strType = "enumeration";
        }
        else
        {
            String value = (String) typeInfo.get (m_strType);
            m_strType = AttributeInfo.EnumAttributeType.getEnum (value).getName ();
        }

        // if(m_strType.equals("Any"))
        // System.out.println(temp);

        return m_strType;
    }

    public static SchemaComplexType getNodeUsageString (
            SchemaComplexType complexType, String[] parents,
            VElement vAppInfoElements)
    {
        String sUsage = "";
        String sMinOccurs = "";
        String sMaxOccurs = "";
        String sProcessUsage = "";
        String sUsageString = ",";
        String sBuffer = "";
        KElement appinfo = null;

        for (int i = 0; i < complexType.m_vSchemaElements.size (); i++)
        {
            //to write some info in it
            SchemaElement myElement = (SchemaElement) complexType.m_vSchemaElements.elementAt (i);
            
            boolean isUnbounded = false;
            KElement k = myElement.getm_schemaKElem ();
            KElement annotation = k.getElement_KElement ("xs:annotation", "", 0);

            //            "NodeProduct".equals(nSchemaComplexType.strSchemaComplexTypeName);

            if (annotation != null)
            {
                appinfo = annotation.getElement_KElement ("xs:appinfo", "", 0);
                if (appinfo != null)
                {
                    VElement vInOut = appinfo.getChildElementVector("Constraint", "", new JDFAttributeMap (), true, 0,false);
                    
                    for (int j = 0; j < vInOut.size (); j++)
                    {
                        // reset it to empty string
                        sProcessUsage = "";
                        SchemaElement schemaElement = new SchemaElement ((KElement) vInOut.elementAt (j));
                        KElement kElem = schemaElement.getm_schemaKElem ();

                        sUsage = kElem.getAttribute ("Usage");
                        
                        sMinOccurs = kElem.getAttribute ("minOccurs");
                        sMaxOccurs = kElem.getAttribute ("maxOccurs");

                        elementHandleMinMaxOccurs (parents, vAppInfoElements, schemaElement, complexType);
                        sMaxOccurs = schemaElement.getStrMaxOccurs();
                        sMinOccurs = schemaElement.getStrMinOccurs();

                        if ("unbounded".equals (sMaxOccurs))
                        {
                            isUnbounded = true;
                        }

                        sProcessUsage = kElem.getAttribute ("ProcessUsage");

                        if (!JDFConstants.EMPTYSTRING.equals (sProcessUsage))
                        {
                            myElement.appendProcessUsageToVector (sProcessUsage);
                            myElement.appendProcessUsageToString (sProcessUsage + ",");
                        }

                        if ("Input".equals (sUsage))
                        {
                            if ("0".equals (sMinOccurs))
                            {
                                if ("unbounded".equals (sMaxOccurs))
                                {
                                    sBuffer += "i*";
                                }
                                else
                                {
                                    sBuffer += "i?";
                                }
                            }
                            else
                            {
                                sBuffer += "i_";
                            }
                            
                            if (!JDFConstants.EMPTYSTRING.equals (sProcessUsage))
                            {
                                sBuffer += sProcessUsage;
                            }
                            
                            if (j != vInOut.size () - 1)
                            {
                                sBuffer += " ";
                            }
                        }
                        else if ("Output".equals (sUsage))
                        {
                            if ("0".equals (sMinOccurs))
                            {
                                if ("unbounded".equals (sMaxOccurs))
                                {
                                    sBuffer += "o*";
                                }
                                else
                                {
                                    sBuffer += "o?";
                                }
                            }
                            else
                            {
                                sBuffer += "o_";
                            }
                            
                            if (!JDFConstants.EMPTYSTRING.equals (sProcessUsage))
                            {
                                sBuffer += sProcessUsage;
                            }
                            
                            if (j != vInOut.size () - 1)
                            {
                                sBuffer += " ";
                            }
                        }
                    }
                    
                    if (i == complexType.m_vSchemaElements.size () - 1)
                    {
                        sUsageString += sBuffer + "";
                    }
                    else
                    {
                        sUsageString += sBuffer + ",";
                    }

                    myElement.setStrUsageString (sBuffer);
                    
                    if (isUnbounded)
                    {
                        myElement.setStrMaxOccurs ("unbounded");
                    }
                    else
                    {
                        myElement.setStrMaxOccurs ("1");
                    }

                    myElement.setStrMinOccurs (sMinOccurs);
                    sBuffer = "";
                }
            }
        }
        
        //set the usage string in the actual complex type
        complexType.setStrNodeUsageString (sUsageString);
        
        return complexType;
    }

    //    /**
    //     * @param object
    //     * @return Object
    //     */
    //    public static KElement getConstraintInfo(Object o)
    //    {
    //        KElement k = (KElement)o;
    //        KElement h = k.GetChildByTagName("xs:annotation");
    //        if(h == null)
    //        {
    //            return null;
    //        }
    //        h = h.GetChildByTagName("xs:appinfo");
    //        VElement v = new VElement(h.GetChildElementVector("Constraint"));
    //        for(int i = 0; i < v.size(); i++)
    //        {
    //            
    //        }
    //        
    //        return null;
    //    }

    
    public static String[] fillParents(SchemaComplexType complexType)
    {
        String parents[] = null;
        String motherOf  = complexType.getStrMotherOfComplexType();

        if (!JDFConstants.EMPTYSTRING.equals(motherOf))
        {
            if (complexType.strVersionInfoPath.indexOf('_') != -1)
            {
                parents = complexType.strVersionInfoPath.split("_");
            }
        }
        
        return parents;
    }
             
    
    public static void fillAppInfoElements(String motherOf, String[] parents, 
            VElement vElements, VElement vAppInfoElements)
    {
        for (int i = 0; i < vElements.size(); i++)
        {
            KElement kElem = ((KElement) vElements.elementAt(i));
            String kElemName = kElem.getAttribute("name", "", "");
            kElemName = kElemName.split("_")[0];

            if (kElemName.equals(motherOf) 
                    || ((parents != null) && kElemName.equals(parents[0])))
            {
                // found mother of complexType, now get the appinfo
                KElement appInfoElement = kElem.getXPathElement("xs:annotation/xs:appinfo");

                if (appInfoElement != null)
                {
                    vAppInfoElements.add(appInfoElement);
                }
            }
        }
    }
             
    
    /**
     * @param parents
     * @param vAppInfoElements
     * @param schemaElement
     * @param complexType TODO
     */
    private static void elementHandleMinMaxOccurs (
            String[] parents, VElement vAppInfoElements, SchemaElement schemaElement, 
            SchemaComplexType complexType)
    {
        String parentsPath = "";
        if (parents != null)
        {
            for (int pa = 1; pa < parents.length; pa++)
            {
                parentsPath += parents[pa];
                parentsPath += "/";
            }
        }

        final String elementName = schemaElement.getStrElementName();
        parentsPath += elementName;

        KElement complexTypeappInfoElement = 
            complexType.m_kElem.getXPathElement("xs:annotation/xs:appinfo");

        boolean isMinMaxOccursInitialized = false;
        if (complexTypeappInfoElement != null)
        {
            isMinMaxOccursInitialized = 
                elementSetMinMaxOccurs(schemaElement, parentsPath, elementName, complexTypeappInfoElement);
        }
        
        if (!isMinMaxOccursInitialized)
        {
            Iterator appInfoIter = vAppInfoElements.iterator();
            while (appInfoIter.hasNext() && !isMinMaxOccursInitialized)
            {
                KElement appInfoElement = (KElement) appInfoIter.next();

                isMinMaxOccursInitialized = 
                    elementSetMinMaxOccurs(schemaElement, parentsPath, elementName, appInfoElement);
            }
        }
    }

    private static boolean elementSetMinMaxOccurs(SchemaElement schemaElement, 
            final String parentsPath, final String elementName, final KElement appInfoElement)
    {
        boolean isMinMaxOccursInitialized = false;
        
        VElement vConstraintElements = appInfoElement.getChildrenByTagName(
                "Constraint", "", new JDFAttributeMap(), true, true, 0);
        
        Iterator constraintIter = vConstraintElements.iterator();
        while (constraintIter.hasNext())
        {
            final KElement constraintElement = (KElement) constraintIter.next();
            String elementPath = constraintElement.getAttribute("Path", "", "");

            if (isElementInParent(parentsPath, elementPath) 
                    || isElementInSchema(elementPath, elementName)
                    // I don´t want to implement [@xxx ... notation, so I use a special case for it
                    || (parentsPath.equals("EndSheet/GlueLine") && elementPath.equals("EndSheet[@Side=\"Back\"]/GlueLine"))
                    || (parentsPath.equals("FileSpec") && elementPath.startsWith("FileSpec[@ResourceUsage="))
                    || (elementPath.equals("SpinePreparationParams GlueApplication SpineTapingParams CoverApplicationParams")
                            && elementPath.indexOf(parentsPath) >= 0)
                    || (elementPath.equals("MarkObject/DynamicField/DeviceMark") && parentsPath.equals("DynamicField/DeviceMark"))
               )
            {
                // found it again, get the maxOccurs/minOccurs now
                String strMinValue = constraintElement.getAttribute("minOccurs", "", "1");
                String strMaxValue = constraintElement.getAttribute("maxOccurs", "", "1");
                
                schemaElement.setStrMinOccurs(strMinValue);
                schemaElement.setStrMaxOccurs(strMaxValue);
        
                if (isElementInSchema(elementPath, elementName))
                {
                    isMinMaxOccursInitialized = true;
                }
            }
        }
        
        return isMinMaxOccursInitialized;
    }

    public static void elementHandleVersion (
            String[] parents, VElement vAppInfoElements, SchemaElement schemaElement, 
            SchemaComplexType complexType)
    {
        String parentsPath = "";
        if (parents != null)
        {
            for (int pa = 1; pa < parents.length; pa++)
            {
                parentsPath += parents[pa];
                parentsPath += "/";
            }
        }
        
        final String elementName = schemaElement.getStrElementName();
        parentsPath += elementName;
        
        KElement complexTypeappInfoElement = 
            complexType.m_kElem.getXPathElement("xs:annotation/xs:appinfo");

        boolean isVersionInitialized = false;
        if (complexTypeappInfoElement != null)
        {
            isVersionInitialized = true;
            elementSetVersion(schemaElement, parentsPath, elementName, complexTypeappInfoElement);
        }
        
        if (!isVersionInitialized)
        {
            Iterator appInfoIter = vAppInfoElements.iterator();
            while (appInfoIter.hasNext() && !isVersionInitialized)
            {
                KElement appInfoElement = (KElement) appInfoIter.next();
                
                isVersionInitialized = 
                    elementSetVersion(schemaElement, parentsPath, elementName, appInfoElement);
            }
        }
    }

    private static boolean elementSetVersion(SchemaElement schemaElement, final String parentsPath, 
            final String elementName, final KElement appInfoElement)
    {
        boolean isVersionInitialized = false;
        VElement vVersionElements = 
            appInfoElement.getChildrenByTagName("Version", "", new JDFAttributeMap(), true, true, 0);
        
        Iterator versionIter = vVersionElements.iterator();
        while (versionIter.hasNext())
        {
            final KElement versionElement = (KElement) versionIter.next();
            String elementPath = versionElement.getAttribute("Path", "", "");

            if (isElementInParent(parentsPath, elementPath)
                    || isElementInSchema(elementPath, elementName)
                    || (".".equals(elementPath))
               )
            {
                String strFirst = versionElement.getAttribute("First", "", "");
                String strLast  = versionElement.getAttribute("Last", "", "");
                schemaElement.setFirstVersion(strFirst);
                schemaElement.setLastVersion(strLast);                        
                
                if (isElementInSchema(elementPath, elementName))
                {
                    isVersionInitialized = true;
                }
            }
        }
        
        return isVersionInitialized;
    }

    private static boolean isElementInParent (String parentsPath, String elementPath)
    {
        boolean result = parentsPath.equals(elementPath) 
                            || parentsPath.startsWith(elementPath+"/")
                            || parentsPath.endsWith("/" + elementPath);
        
//        boolean resultNew = isElementInSchema(elementPath, elementName);
//        
//        if (resultNew && result != resultNew)
//            System.out.println(complexType.strVersionInfoPath+"\t"+parentsPath+"\t"+
//                    parentsPath.equals(elementPath)+"\t"+parentsPath.startsWith(elementPath+"/")+"\t"+result+"\t"+
//                    elementPath+"\t"+
//                    elementPath.equals(elementName)+"\t"+elementPath.endsWith("/"+elementName)+"\t"+resultNew+"\t"+
//                    elementName);
        
        return result;
    }

    private static boolean isElementInSchema(String elementPath, String elementName)
    {
        return elementPath.equals(elementName) || elementPath.endsWith("/"+elementName);
    }

    public static void attributeHandleVersion (
            String[] parents, VElement vAppInfoElements, SchemaAttribute schemaAttribute, 
            SchemaComplexType complexType)
    {
        String parentsPath = "";
        if (parents != null)
        {
            for (int pa = 1; pa < parents.length; pa++)
            {
                parentsPath += parents[pa];
                parentsPath += "/";
            }
        }
        
        final String attributeName = "@" + schemaAttribute.getStrAttributeName();
        parentsPath += attributeName;
        
        KElement complexTypeappInfoElement = 
            complexType.m_kElem.getXPathElement("xs:annotation/xs:appinfo");

        boolean isVersionInitialized = false;
        if (complexTypeappInfoElement != null)
        {
            isVersionInitialized = true;
            attributeSetVersion(schemaAttribute, parentsPath, attributeName, complexTypeappInfoElement);
        }
        
        if (!isVersionInitialized)
        {
            Iterator appInfoIter = vAppInfoElements.iterator();
            while (appInfoIter.hasNext() && ! isVersionInitialized)
            {
                KElement appInfoElement = (KElement) appInfoIter.next();
                
                isVersionInitialized = 
                    attributeSetVersion(schemaAttribute, parentsPath, attributeName, appInfoElement);
            }
        }
    }

    private static boolean attributeSetVersion(SchemaAttribute schemaAttribute, 
            final String parentsPath, final String attributeName, final KElement appInfoElement)
    {
        boolean isVersionInitialized = false;

        VElement vVersionElements = 
            appInfoElement.getChildrenByTagName("Version", "", new JDFAttributeMap(), true, true, 0);
        
        Iterator versionIter = vVersionElements.iterator();
        while (versionIter.hasNext())
        {
            KElement versionElement = (KElement) versionIter.next();
            String attributePath    = versionElement.getAttribute("Path", "", "");
        
            if (isElementInParent(parentsPath, attributePath) 
                    || isElementInSchema(attributePath, attributeName)
                    || (".".equals(attributePath))
               )
            {
                // found attributePath, get First and Last for the parent as default
                String strFirst = versionElement.getAttribute("First", "", "");
                String strLast  = versionElement.getAttribute("Last", "", "");
        
                schemaAttribute.setFirstVersion(strFirst);
                schemaAttribute.setLastVersion(strLast);
                
                if (isElementInSchema(attributePath, attributeName))
                {
                    isVersionInitialized = true;
                }
            }
        }
        
        return isVersionInitialized;
    }

    private static void mergeAttributeInfo (SchemaAttribute newSchema, SchemaAttribute oldSchema)
    {
        if (!JDFConstants.EMPTYSTRING.equals (oldSchema.getStrEnumName ()))
        {
            newSchema.setStrEnumName (oldSchema.getStrEnumName ());
        }

        if (!JDFConstants.EMPTYSTRING.equals (oldSchema.getStrType ()))
        {
            newSchema.setStrStrType (oldSchema.getStrType ());
        }

        if (!JDFConstants.EMPTYSTRING.equals (oldSchema.getStrReturnType ()))
        {
            newSchema.setStrReturnType (oldSchema.getStrReturnType ());
        }

        if (!JDFConstants.EMPTYSTRING.equals (oldSchema.getStrUse ()))
        {
            newSchema.setStrUse (oldSchema.getStrUse ());
        }

        if (!JDFConstants.EMPTYSTRING.equals (oldSchema.getStrFixed ()))
        {
            newSchema.setStrFixed (oldSchema.getStrFixed ());
        }

        if (!JDFConstants.EMPTYSTRING.equals (oldSchema.getStrValue ()))
        {
            newSchema.setStrValue (oldSchema.getStrValue ());
        }

        if (!JDFConstants.EMPTYSTRING.equals (oldSchema.getStrDefault ()))
        {
            newSchema.setStrDefault (oldSchema.getStrDefault ());
        }
    }

    /**
     *  generates a string of form 11111333. Each digit denotates a version starting 
     *  at the right with version 1.0 and incrementing to the left 1.1, 1.2 ... 1.7
     *  (not sure if the world exists after 1.7 ;)
     *  
     *  Number lookup for attributes (-> JDFSpec 1.3, 1.3.4 Specification of Cardinality
     *  None            = 1
     *  Required        = 2 - A
     *  Optional        = 3 - A?
     *  Deprecated      = 4
     *  
     *  44444432 means "was required in 1.0, was optional in 1.1, is deprecated in 1.2 
     *  44333311 means "was first introduced as optional in 1.2, was deprecated in 1.6
     *  
     * @param String usage        "optional" or "required"
     * @param String firstVersion i.e. "1.1" and lastVersion  = ""
     * @param String lastVersion  i.e. "1.2" and firstVersion = ""
     * @return a String of form  44333311
     
     */
    public static String getVersionInfoAttributes (String usage, String firstVersion, String lastVersion)
    {
        // we need a vector the iterator can be random and we need a spezific order
        Vector version = new Vector();
        version.add(EnumVersion._1_7);
        version.add(EnumVersion._1_6);
        version.add(EnumVersion._1_5);
        version.add(EnumVersion._1_4);
        version.add(EnumVersion._1_3);
        version.add(EnumVersion._1_2);
        version.add(EnumVersion._1_1);
        version.add(EnumVersion._1_0);

        EnumVersion eFirstVer = EnumVersion.getEnum(firstVersion);
        EnumVersion eLastVer = EnumVersion.getEnum(lastVersion);

        if (eFirstVer == null)
            eFirstVer = EnumVersion._1_0;

        if (eLastVer == null)
            eLastVer = EnumVersion._1_7;

        StringBuffer verBuffer = new StringBuffer(10);

        for (int i = 0; i < version.size(); i++)
        {
            EnumVersion processedVersion = (EnumVersion) version.elementAt(i);

            if (processedVersion.getValue() > eLastVer.getValue())
            {
                verBuffer.append("4");
            }
            else if (processedVersion.getValue() >= eFirstVer.getValue())
            {
                if (usage.equals("optional"))
                {
                    verBuffer.append("3");
                }
                else
                {
                    verBuffer.append("2");
                }
            }
            else
            {
                verBuffer.append("1");
            }
        }

        return verBuffer.toString();
    }

    /**
     * generates a string of form 33333333. Every digit is a version starting at the right with
     * version 1.0 and incrementing to the left 1.1, 1.2 etc.
     * 
     * Number lookup for elements (-> JDFSpec 1.3, 1.3.4 Specification of Cardinality None = 1
     * Required = 2 - E+ Optional = 3 - E* Deprecated = 4 Single Required = 5 - E Single Optional =
     * 6 - E? Single Deprecated = 7 Dummy = 8
     * 
     * 00000432 means "was required in 1.0, was optional in 1.1, is deprecated in 1.2 and no
     * informations for further versions are available"
     * 
     * @param isOptional
     * @param firstVersion
     * @param lastVersion
     * @param maxOccurs
     * @param schemaComplexType
     * 
     * @return a String of form 11111333 boolean isOptional, String firstVersion, String
     *         lastVersion, String maxOccurs element.getIsOptionalElement(),
     *         element.getFirstVersion(), element.getLastVersion(), element.getStrMaxOccurs()
     */
    public static String getVersionInfoElements (boolean isOptional, String firstVersion, String lastVersion, String maxOccurs)
    {
        // we need a vector the iterator can be random and we need a spezific order
        Vector version = new Vector();
        version.add(EnumVersion._1_7);
        version.add(EnumVersion._1_6);
        version.add(EnumVersion._1_5);
        version.add(EnumVersion._1_4);
        version.add(EnumVersion._1_3);
        version.add(EnumVersion._1_2);
        version.add(EnumVersion._1_1);
        version.add(EnumVersion._1_0);

        EnumVersion eLastVer = EnumVersion.getEnum(lastVersion);
        EnumVersion eFirstVer = EnumVersion.getEnum(firstVersion);

        if (eFirstVer == null)
            eFirstVer = EnumVersion._1_0;

        if (eLastVer == null)
            eLastVer = EnumVersion._1_7;

        StringBuffer verBuffer = new StringBuffer(10);

        for (int i = 0; i < version.size(); i++)
        {
            EnumVersion processedVersion = (EnumVersion) version.elementAt(i);

            if (processedVersion.getValue() > eLastVer.getValue())
            {   // is deprecated
                if (maxOccurs.equals("1"))
                {
                    verBuffer.append("7");
                }
                else
                {
                    verBuffer.append("4");
                }
            }
            else if (processedVersion.getValue() >= eFirstVer.getValue())
            {   // nicht deprecated und erste Version noch nicht erreicht
                if (isOptional)
                {
                    if (maxOccurs.equals("1"))
                    {
                        verBuffer.append("6");
                    }
                    else
                    {
                        verBuffer.append("3");
                    }
                }
                else
                {
                    if (maxOccurs.equals("1"))
                    {
                        verBuffer.append("5");
                    }
                    else
                    {
                        verBuffer.append("2");
                    }
                }
            }
            else
            {   // fallback
                verBuffer.append("1");
            }
        }

        return verBuffer.toString();
    }

    private static VElement removeDoubleEntrys (VElement source)
    {
        VElement goal = new VElement ();
        HashMap h = new HashMap ();
        for (int i = 0; i < source.size (); i++)
        {
            KElement k = (KElement) source.elementAt (i);
            h.put (k.getAttribute ("name"), k);
        }

        Iterator it = h.keySet ().iterator ();
        while (it.hasNext ())
        {
            goal.appendUnique ((KElement) h.get (it.next ()));
        }

        return goal;
    }
}
