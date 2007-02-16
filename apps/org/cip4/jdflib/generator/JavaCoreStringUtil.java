/**
 *
 * Copyright (c) 2002 Heidelberger Druckmaschinen AG, All Rights Reserved.
 * Author: Kai Mattern Dietrich Mucha Titel: JavaCoreStringUtil.java Version:
 * 2.0 Description: The xml Schema is partitioned into many "complex type's"
 * these types have children named "attributes" and "elements" this file is for
 * describing all values a "element" can have. Counter 34
 *
 * History: 03-13-2002 Kai Mattern started file
 *          30-11-2005 Dietrich Mucha
 *
 * TBD: getMinOccurs should return int isAttributeToAdd - Attribute
 * settingsPolicy is defined where ?? isAttributeToAdd - Attribute Locked is
 * defined where ?? isAttributeToAdd - CommentURL is a Attribute from ???? (its
 * used in JDFElement) isAttributeToAdd - DescriptiveName is a Attribute from
 * ??? (its used in JEFElement) isAttributeToAdd - xmlns is a Attribute from
 * ???? (its defined in KElement) getAllValidElements - COMPLETE TBD
 * getEnumValues - COMPLETE TBD getAllValidElements - add all values a element
 * can have
 */

/*
 * NOTE: This is a little sequence out of the 'Schema' to make comments of the
 * methods more clear
 *
 * <xs:complexType name="PhaseTimeAudit"> <xs:complexContent> <xs:extension
 * base="jdf:AuditElement"> <xs:sequence minOccurs="0" maxOccurs="unbounded">
 * <xs:group ref="jdf:GenericElements" minOccurs="0" /> <xs:element
 * name="Device" type="jdf:Device_r" minOccurs="0" /> <xs:element
 * name="DeviceRef" type="jdf:ResourceRef" minOccurs="0" /> <xs:element
 * name="Employee" type="jdf:Employee_re" minOccurs="0" /> <xs:element
 * name="EmployeeRef" type="jdf:ResourceRef" minOccurs="0" /> <xs:element
 * name="ModulePhase" minOccurs="0"> <xs:complexType> <xs:sequence minOccurs="0"
 * maxOccurs="unbounded"> <xs:group ref="jdf:GenericElements" minOccurs="0" />
 * <xs:element name="Employee" type="jdf:Employee_re" minOccurs="0" />
 * <xs:element name="EmployeeRef" type="jdf:ResourceRef" minOccurs="0" />
 * </xs:sequence> . . ...etc
 */
/*
 * 36
 */

// package
package org.cip4.jdflib.generator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.core.JDFConstants;


// imports

// ======================================================================================================
// StringUtil
// ======================================================================================================

public class JavaCoreStringUtil
{
    static final String            strLineEnd         = "\n";
    static final String            strDepth1          = "    ";
    static final String            strDepth2          = "        ";
    static final String            strDepth3          = "            ";
    static final String            strDepth4          = "                ";
    static final String            strDepth5          = "                    ";

    private static String          m_strIsOff         = "0123456789";
    private static SchemaElement   m_nSchemaElement   = null;

    public static final class EnumVersion extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;

        private static int        m_startValue     = 0;

        protected EnumVersion(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumVersion getEnum(String enumName)
        {
            return (EnumVersion) getEnum(EnumVersion.class, enumName);
        }

        public static EnumVersion getEnum(int enumValue)
        {
            return (EnumVersion) getEnum(EnumVersion.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumVersion.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumVersion.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumVersion.class);
        }

        public static Vector getNamesVector()
        {
            final Vector namesVector = new Vector();
            final Iterator it = iterator(EnumVersion.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }

            return namesVector;
        }

        public static final EnumVersion _1_0     = new EnumVersion("1.0");
        public static final EnumVersion _1_1     = new EnumVersion("1.1");
        public static final EnumVersion _1_2     = new EnumVersion("1.2");
        public static final EnumVersion _1_3     = new EnumVersion("1.3");
        public static final EnumVersion _1_4     = new EnumVersion("1.4");
        public static final EnumVersion _1_5     = new EnumVersion("1.5");
        public static final EnumVersion _1_6     = new EnumVersion("1.6");
        public static final EnumVersion _1_7     = new EnumVersion("1.7");
    }

    public static String getStrJavaCoreFile(SchemaComplexType schemaComplexType)
    {
        Vector vAttributes          = schemaComplexType.m_vSchemaAttributes;
        Vector vElements            = schemaComplexType.m_vSchemaElements;

        String strComplexTypeName   = schemaComplexType.m_SchemaComplexTypeName;
        String strExtends           = schemaComplexType.getStrExtendsName();
        String strJDFAutoFileName   = "JDFAuto" + strComplexTypeName;

        StringBuffer strbufResult   = new StringBuffer(100000);

        
		// remove unwanted abstract ResourceLink elements
		for (int i = vElements.size() - 1; i >= 0; i--) {
			final SchemaElement element = (SchemaElement) vElements.elementAt(i);
			final String strName = element.getStrElementName();
			if ("ResourceLink".equals(strName))
				vElements.remove(i);
		}

        appendImportAndStartOfClass(strJDFAutoFileName, strExtends, strbufResult);

        if (!strComplexTypeName.equals("NodeInfo") && !strComplexTypeName.equals("CustomerInfo"))
        {
            if (vAttributes.size() > 0)
            {
                appendAtrInfoTable(strComplexTypeName, vAttributes, strbufResult);
            }

            if (vElements.size() > 0)
            {
                appendElemInfoTable(vElements, strbufResult);
            }
        }

        appendConstructorsAndToString(strJDFAutoFileName, strbufResult);
        appendResourceSpecificMethods(schemaComplexType.getBase(), strExtends, strbufResult);
        appendEnumTypes(vAttributes, strComplexTypeName, strbufResult);
        appendAttributeGetterAndSetter(strComplexTypeName, vAttributes, strbufResult);
        appendElementGetterAndSetter(strComplexTypeName, vElements, strbufResult);

        return strbufResult.toString();
    }


    /**
     * @param strPackage
     * @param strExtends
     * @param strbufResult
     */
    private static void appendImportAndStartOfClass(String strJDFAutoFileName,
            String strExtends, StringBuffer strbufResult)
    {
        String strPackage = "org.cip4.jdflib.auto";

        strbufResult.append(GeneratorStringUtil.getStrAllCopyrightInformation()).append(strLineEnd);

        strbufResult.append("package ").append(strPackage).append(";").append(strLineEnd).append(
                strLineEnd);

        strbufResult.append("import java.util.Iterator;                          ").append(strLineEnd);
        strbufResult.append("import java.util.List;                              ").append(strLineEnd);
        strbufResult.append("import java.util.Map;                               ").append(strLineEnd);
        strbufResult.append("import java.util.Vector;                            ").append(strLineEnd);
        strbufResult.append("import java.util.zip.DataFormatException;           ").append(strLineEnd)
                .append(strLineEnd);

        strbufResult.append("import org.apache.commons.lang.enums.ValuedEnum;    ").append(strLineEnd);
        strbufResult.append("import org.w3c.dom.Element;                         ").append(strLineEnd);

        strbufResult.append("import org.apache.xerces.dom.CoreDocumentImpl;      ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.*;                           ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.auto.*;                      ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.core.*;                      ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.span.*;                      ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.node.*;                      ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.pool.*;                      ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.jmf.*;                       ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.datatypes.*;                 ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.resource.*;                  ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.resource.devicecapability.*; ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.resource.intent.*;           ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.resource.process.*;          ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.resource.process.postpress.*;").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.resource.process.press.*;    ").append(strLineEnd);
        strbufResult.append("import org.cip4.jdflib.resource.process.prepress.*; ").append(strLineEnd);

        strbufResult.append("import org.cip4.jdflib.util").append(".*;           ")
                .append(strLineEnd);

        strbufResult.append(strDepth1).append("/*").append(strLineEnd);
        strbufResult.append(strDepth1).append(
                "*****************************************************************************")
                .append(strLineEnd);
        strbufResult.append(strDepth1).append("class ").append(strJDFAutoFileName).append(" : public ")
                .append(strExtends).append(strLineEnd).append(strLineEnd);
        strbufResult.append(strDepth1).append(
                "*****************************************************************************")
                .append(strLineEnd);
        strbufResult.append(strDepth1).append("*/").append(strLineEnd).append(strLineEnd);

        strbufResult.append("public abstract class ").append(strJDFAutoFileName).append(
                " extends ").append(strExtends).append(strLineEnd);
        strbufResult.append("{").append(strLineEnd);
        strbufResult.append(strLineEnd);
        strbufResult.append(strDepth1).append("private static final long serialVersionUID = 1L;")
                .append(strLineEnd);
        strbufResult.append(strLineEnd);
    }


    /**
     * @param complexTypeName
     * @param vAttributes
     * @param strbufResult
     */
    private static void appendAtrInfoTable(
            String complexTypeName, Vector vAttributes, StringBuffer strbufResult)
    {
        // siz > 0 !!!
        int siz = vAttributes.size();

        strbufResult.append(strDepth1).
            append("private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[").
            append(siz).append("];").append(strLineEnd);
        strbufResult.append(strDepth1).append("static").append(strLineEnd);
        strbufResult.append(strDepth1).append("{").append(strLineEnd);

        for (int i = 0; i < siz; i++)
        {
            SchemaAttribute schemaAttribute = (SchemaAttribute) vAttributes.elementAt(i);
            String attributeName            = schemaAttribute.getStrAttributeName();

            String usage           = schemaAttribute.getStrUse();  // usage is either optional or required
            String firstVersion    = schemaAttribute.getFirstVersion();
            String lastVersion     = schemaAttribute.getLastVersion();
            String versionInfo     = GeneratorUtil.getVersionInfoAttributes(usage, firstVersion, lastVersion);

            String enumAttributeType = schemaAttribute.getIsEnumList()
                                        ? "enumerations"
                                        : GeneratorUtil.getAttributeExt(schemaAttribute.getStrType(),
                                                useEnumAttribute(schemaAttribute.getIsEnum(), attributeName));

            String enumType = null;
            if (useEnumAttribute(schemaAttribute.getIsEnum(), attributeName))
            {
                enumType = "Enum" + attributeName;
                if ("Status".equals(attributeName))
                {
                    enumType = getTypeName(complexTypeName);
                }
                else if ("MaxVersion".equals(attributeName))
                    enumType = "EnumVersion";
                else if ("UpdatedStatus".equals(attributeName))
                    enumType = "JDFResource.EnumResStatus";
                else if ("LinkUsage".equals(attributeName))
                    enumType = "JDFResourceLink.EnumUsage";
                else if ("Usage".equals(attributeName) && "ResourceInfo".equals(complexTypeName))
                    enumType = "JDFResourceLink.EnumUsage";
                else if ("Usage".equals(attributeName) && "ResourceQuParams".equals(complexTypeName))
                    enumType = "JDFResourceLink.EnumUsage";
                else if ("Classes".equals(attributeName) && "ResourceQuParams".equals(complexTypeName))
                    enumType = "JDFResource.EnumResourceClass";
                else if ("Usage".equals(attributeName) && "InsertSheet".equals(complexTypeName))
                    enumType = "EnumSheetUsage";
                else if ("DeviceStatus".equals(attributeName) && "ModulePhase".equals(complexTypeName))
                    enumType = "JDFDeviceInfo.EnumDeviceStatus";
                else if ("DeviceStatus".equals(attributeName) && "ModuleStatus".equals(complexTypeName))
                    enumType = "JDFDeviceInfo.EnumDeviceStatus";
                else if ("HoleType".equals(attributeName) && "HoleMakingParams".equals(complexTypeName))
                    enumType = "JDFMedia.EnumHoleType";
                enumType = enumType + ".getEnum(0)";
            }

            String defaultValue = schemaAttribute.getStrDefault();
            defaultValue = defaultValue.equals(JDFConstants.EMPTYSTRING)
                                ? "null"
                                : "\"" + defaultValue + "\"";

            strbufResult.append(strDepth2)
                .append("atrInfoTable[").append(i).append("] = new AtrInfoTable(AttributeName.")
                .append(attributeName.toUpperCase()).append(", 0x").append(versionInfo)
                .append(", AttributeInfo.EnumAttributeType.").append(enumAttributeType)
                .append(", ").append(enumType)
                .append(", ").append(defaultValue)
                .append(");").append(strLineEnd);
        }

        strbufResult.append(strDepth1).append("}").append(strLineEnd);
        strbufResult.append(strDepth1).append(strLineEnd);

        strbufResult.append(strDepth1).append("protected AttributeInfo getTheAttributeInfo()").append(strLineEnd);
        strbufResult.append(strDepth1).append("{").append(strLineEnd);
        strbufResult.append(strDepth2).append(
                "return super.getTheAttributeInfo().updateReplace(atrInfoTable);").append(strLineEnd);
        strbufResult.append(strDepth1).append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd);
    }


    private static String getStatusName(String complexTypeName)
    {
        String statusName = null;
        if ("Queue".equals(complexTypeName))
        {
            statusName = "QueueStatus";
        }
        else if ("QueueEntry".equals(complexTypeName))
        {
            statusName = "QueueEntryStatus";
        }
        else if ("ResourceCmdParams".equals(complexTypeName) || "ResourceInfo".equals(complexTypeName))
        {
            statusName = "ResStatus";
        }
        else if ("ProcessRun".equals(complexTypeName))
        {
            statusName = "EndStatus";
        }
        else
        {
            statusName = "NodeStatus";
        }

        return statusName;
    }


    private static String getTypeName(String complexTypeName)
    {
        String typeName = null;
        if ("Queue".equals(complexTypeName))
        {
            typeName = "EnumQueueStatus";
        }
        else if ("QueueEntry".equals(complexTypeName))
        {
            typeName = "EnumQueueEntryStatus";
        }
        else if ("ResourceCmdParams".equals(complexTypeName) || "ResourceInfo".equals(complexTypeName))
        {
            typeName = "JDFResource.EnumResStatus";
        }
        else
        {
            typeName = "EnumNodeStatus";
        }

        return typeName;
    }


    /**
     * @param vElements
     * @param strbufResult
     */
    private static void appendElemInfoTable(Vector vElements, StringBuffer strbufResult)
    {
		// vElements.size() > 0 !!!
		Vector elementsForVersion = new Vector(vElements);

		for (int i = elementsForVersion.size() - 1; i >= 0; i--) {
			SchemaElement element = (SchemaElement) elementsForVersion.elementAt(i);
			String strName = element.getStrElementName();
			if ((strName.endsWith("Ref") && !strName.equals("TestRef")))
				elementsForVersion.remove(i);
		}

		int siz2 = elementsForVersion.size();
		if (siz2 > 0) {
			strbufResult
					.append(strDepth1)
					.append("private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[")
					.append(siz2).append("];").append(strLineEnd);
			strbufResult.append(strDepth1).append("static").append(strLineEnd);
			strbufResult.append(strDepth1).append("{").append(strLineEnd);

			for (int i = 0; i < siz2; i++) {
				SchemaElement element = (SchemaElement) elementsForVersion.elementAt(i);
				String elemName = element.getStrElementName().toUpperCase();
				
				String versionInfo = GeneratorUtil.getVersionInfoElements(
						element.getIsOptionalElement(), element.getFirstVersion(), 
						element.getLastVersion(), element.getStrMaxOccurs());

				strbufResult.append(strDepth2).append("elemInfoTable[")
						.append(i).append("] = new ElemInfoTable(ElementName.")
						.append(elemName).append(", 0x").append(versionInfo)
						.append(");").append(strLineEnd);
			}

			strbufResult.append(strDepth1).append("}").append(strLineEnd);
			strbufResult.append(strDepth1).append(strLineEnd);

			strbufResult.append(strDepth1)
					.append("protected ElementInfo getTheElementInfo()")
					.append(strLineEnd);
			strbufResult.append(strDepth1).append("{").append(strLineEnd);
			strbufResult
					.append(strDepth2)
					.append("return super.getTheElementInfo().updateReplace(elemInfoTable);")
					.append(strLineEnd);
			strbufResult.append(strDepth1).append("}").append(strLineEnd)
					.append(strLineEnd).append(strLineEnd);
		}
	}


    /**
	 * @param strbufResult
	 */
    private static void appendConstructorsAndToString(String strJDFAutoFileName, StringBuffer strbufResult)
    {
        strbufResult.append(strLineEnd);
        strbufResult.append(strDepth1).append("/**").append(strLineEnd);
        strbufResult.append(strDepth1).append(" * Constructor for ").append(strJDFAutoFileName).append(
                strLineEnd);
        strbufResult.append(strDepth1).append(" * @param myOwnerDocument").append(strLineEnd);
        strbufResult.append(strDepth1).append(" * @param qualifiedName").append(strLineEnd);
        strbufResult.append(strDepth1).append(" */").append(strLineEnd);
        strbufResult.append(strDepth1).append("protected ").append(strJDFAutoFileName).append("(").append(
                strLineEnd);
        strbufResult.append(strDepth2).append("CoreDocumentImpl myOwnerDocument,").append(strLineEnd);
        strbufResult.append(strDepth2).append("String qualifiedName)").append(strLineEnd);
        strbufResult.append(strDepth1).append("{").append(strLineEnd);
        strbufResult.append(strDepth1).append("    super(myOwnerDocument, qualifiedName);").append(
                strLineEnd);
        strbufResult.append(strDepth1).append("}").append(strLineEnd).append(strLineEnd);

        strbufResult.append(strDepth1).append("/**").append(strLineEnd);
        strbufResult.append(strDepth1).append(" * Constructor for ").append(strJDFAutoFileName).append(
                strLineEnd);
        strbufResult.append(strDepth1).append(" * @param myOwnerDocument").append(strLineEnd);
        strbufResult.append(strDepth1).append(" * @param myNamespaceURI").append(strLineEnd);
        strbufResult.append(strDepth1).append(" * @param qualifiedName").append(strLineEnd);
        strbufResult.append(strDepth1).append(" */").append(strLineEnd);
        strbufResult.append(strDepth1).append("protected ").append(strJDFAutoFileName).append("(").append(
                strLineEnd);
        strbufResult.append(strDepth2).append("CoreDocumentImpl myOwnerDocument,").append(strLineEnd);
        strbufResult.append(strDepth2).append("String myNamespaceURI,").append(strLineEnd);
        strbufResult.append(strDepth2).append("String qualifiedName)").append(strLineEnd);
        strbufResult.append(strDepth1).append("{").append(strLineEnd);
        strbufResult.append(strDepth1).append(
                "    super(myOwnerDocument, myNamespaceURI, qualifiedName);").append(strLineEnd);
        strbufResult.append(strDepth1).append("}").append(strLineEnd).append(strLineEnd);

        strbufResult.append(strDepth1).append("/**").append(strLineEnd);
        strbufResult.append(strDepth1).append(" * Constructor for ").append(strJDFAutoFileName).append(
                strLineEnd);
        strbufResult.append(strDepth1).append(" * @param myOwnerDocument").append(strLineEnd);
        strbufResult.append(strDepth1).append(" * @param myNamespaceURI").append(strLineEnd);
        strbufResult.append(strDepth1).append(" * @param qualifiedName").append(strLineEnd);
        strbufResult.append(strDepth1).append(" * @param myLocalName").append(strLineEnd);
        strbufResult.append(strDepth1).append(" */").append(strLineEnd);
        strbufResult.append(strDepth1).append("protected ").append(strJDFAutoFileName).append("(").append(
                strLineEnd);
        strbufResult.append(strDepth2).append("CoreDocumentImpl myOwnerDocument,").append(strLineEnd);
        strbufResult.append(strDepth2).append("String myNamespaceURI,").append(strLineEnd);
        strbufResult.append(strDepth2).append("String qualifiedName,").append(strLineEnd);
        strbufResult.append(strDepth2).append("String myLocalName)").append(strLineEnd);
        strbufResult.append(strDepth1).append("{").append(strLineEnd);
        strbufResult.append(strDepth1).append(
                "    super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);").append(
                strLineEnd);
        strbufResult.append(strDepth1).append("}").append(strLineEnd).append(strLineEnd).append(
                strLineEnd);

        strbufResult.append(strDepth1).append("public String toString()").append(strLineEnd);
        strbufResult.append(strDepth1).append("{").append(strLineEnd);
        strbufResult.append(strDepth1).append("    return \" ").append(strJDFAutoFileName).append(
                "[  --> \" + super.toString() + \" ]\";").append(strLineEnd);
        strbufResult.append(strDepth1).append("}").append(strLineEnd).append(strLineEnd).append(
                strLineEnd);
    }


    private static void appendResourceSpecificMethods (String base, String extendsClass, StringBuffer strbufResult)
    {
        if ("JDFResource".equals(extendsClass))
        {
            String baseOf = getBaseOf(base);

            String resourceClass = baseOf;
            if (JDFConstants.EMPTYSTRING.equals(baseOf)) resourceClass = "Parameter";

            strbufResult.append(strDepth1)
                .append("public boolean ").append(" init()").append(strLineEnd);
            strbufResult.append(strDepth1)
                .append("{").append(strLineEnd);
            strbufResult.append(strDepth2)
                .append("boolean bRet = super.init();").append(strLineEnd);
            strbufResult.append(strDepth2)
                .append("setResourceClass(JDFResource.EnumResourceClass.").append(resourceClass)
                .append(");").append(strLineEnd);
            strbufResult.append(strDepth2)
                .append("return bRet;").append(strLineEnd);
            strbufResult.append(strDepth1)
                .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd);

            if (!JDFConstants.EMPTYSTRING.equals(baseOf))
            {
                strbufResult.append(strDepth1)
                    .append("public EnumResourceClass getValidClass()").append(strLineEnd);
                strbufResult.append(strDepth1)
                    .append("{").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("return JDFResource.EnumResourceClass.")
                    .append(baseOf).append(";").append(strLineEnd);
                strbufResult.append(strDepth1)
                    .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd);
            }
        }
    }


    private static String getBaseOf (String base)
    {
        String baseOf = "";
        if (base.startsWith("jdf:Parameter"))
        {
            baseOf = "Parameter";
        }
        else if (base.startsWith("jdf:Handling"))
        {
            baseOf = "Handling";
        }
        else if (base.startsWith("jdf:Consumable"))
        {
            baseOf = "Consumable";
        }
        else if (base.startsWith("jdf:Quantity"))
        {
            baseOf = "Quantity";
        }
        else if (base.startsWith("jdf:Implementation"))
        {
            baseOf = "Implementation";
        }
        else if (base.startsWith("jdf:PlaceHolder"))
        {
            baseOf = "PlaceHolder";
        }
        else if (base.startsWith("jdf:Intent"))
        {
            baseOf = "Intent";
        }

        return baseOf;
    }


    /**
     * @param vAttributes
     * @param complexTypeName
     * @param strbufResult
     */
    private static void appendEnumTypes(Vector vAttributes, String complexTypeName, StringBuffer strbufResult)
    {
        for (int i = 0; i < vAttributes.size(); i++)
        {
            SchemaAttribute schemaAttribute = (SchemaAttribute) vAttributes.elementAt(i);
            String attributeName = schemaAttribute.getStrAttributeName();

            if (useEnumAttribute(schemaAttribute.getIsEnum(), attributeName)
                    && !"MaxVersion".equals(attributeName)
                    && !"UpdatedStatus".equals(attributeName)
                    && !"LinkUsage".equals(attributeName)
                    && !("Usage".equals(attributeName) && "ResourceInfo".equals(complexTypeName))
                    && !("Usage".equals(attributeName) && "ResourceQuParams".equals(complexTypeName))
                    && !("Classes".equals(attributeName) && "ResourceQuParams".equals(complexTypeName))
                    && !("Usage".equals(attributeName) && "InsertSheet".equals(complexTypeName))
                    && !("DeviceStatus".equals(attributeName) && "ModulePhase".equals(complexTypeName))
                    && !("DeviceStatus".equals(attributeName) && "ModuleStatus".equals(complexTypeName))
                    && !("HoleType".equals(attributeName) && "HoleMakingParams".equals(complexTypeName))
                    && !"NodeStatus".equals(attributeName)
                    && (!"Status".equals(attributeName) ||
                        ("Status".equals(attributeName) &&
                            ("QueueEntry".equals(complexTypeName) || "Queue".equals(complexTypeName)))))
            {
                String attributeTypeName = "Enum" + attributeName;
                if ("Status".equals(attributeName))
                {
                    // just some specials for QueueStatus and QueueEntryStatus. The
                    // attribute has to be handled like "Status"
                    // but to be called as "QueueStatus" and "QueueEntryStatus"
                    attributeTypeName = getTypeName(complexTypeName);
                }

                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("* Enumeration strings for ").
                    append(attributeTypeName.substring(4)).append(strLineEnd);
                strbufResult.append(strDepth2).append("*/").append(strLineEnd).append(strLineEnd);

                strbufResult.append(strDepth2).append("public static class ").append(
                        attributeTypeName).append(" extends ValuedEnum").append(strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);
                strbufResult.append(strDepth2).append(
                        "    private static final long serialVersionUID = 1L;").append(
                        strLineEnd);
                strbufResult.append(strDepth2).append(
                        "    private static int m_startValue = 0;").append(strLineEnd).append(
                        strLineEnd);

                strbufResult.append(strDepth2).append("    private ").append(
                        attributeTypeName).append("(String name)").append(strLineEnd);
                strbufResult.append(strDepth2).append("    {").append(strLineEnd);
                strbufResult.append(strDepth2).append("        super(name, m_startValue++);")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("    }").append(strLineEnd).append(
                        strLineEnd);

                strbufResult.append(strDepth2).append("    public static ").append(
                        attributeTypeName).append(" getEnum(String enumName)")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("    {").append(strLineEnd);
                strbufResult.append(strDepth2).append("        return (").append(
                        attributeTypeName).append(") getEnum(").append(attributeTypeName)
                        .append(".class, enumName);").append(strLineEnd);
                strbufResult.append(strDepth2).append("    }").append(strLineEnd).append(
                        strLineEnd);

                strbufResult.append(strDepth2).append("    public static ").append(
                        attributeTypeName).append(" getEnum(int enumValue)").append(strLineEnd);
                strbufResult.append(strDepth2).append("    {").append(strLineEnd);
                strbufResult.append(strDepth2).append("        return (").append(
                        attributeTypeName).append(") getEnum(").append(attributeTypeName)
                        .append(".class, enumValue);").append(strLineEnd);
                strbufResult.append(strDepth2).append("    }").append(strLineEnd).append(
                        strLineEnd);

                strbufResult.append(strDepth2).append("    public static Map getEnumMap()")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("    {").append(strLineEnd);
                strbufResult.append(strDepth2).append("        return getEnumMap(").append(
                        attributeTypeName).append(".class);").append(strLineEnd);
                strbufResult.append(strDepth2).append("    }").append(strLineEnd).append(
                        strLineEnd);

                strbufResult.append(strDepth2).append("    public static List getEnumList()")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("    {").append(strLineEnd);
                strbufResult.append(strDepth2).append("        return getEnumList(")
                        .append(attributeTypeName).append(".class);").append(strLineEnd);
                strbufResult.append(strDepth2).append("    }").append(strLineEnd).append(
                        strLineEnd);

                strbufResult.append(strDepth2).append("    public static Iterator iterator()")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("    {").append(strLineEnd);
                strbufResult.append(strDepth2).append("        return iterator(").append(
                        attributeTypeName).append(".class);").append(strLineEnd);
                strbufResult.append(strDepth2).append("    }").append(strLineEnd).append(
                        strLineEnd);

                Vector vEnumValues = schemaAttribute.getEnumValues();
                for (int j = 0; j < vEnumValues.size(); j++)
                {
                    String enumValue = (String) vEnumValues.elementAt(j);

                    strbufResult.append(strDepth3).append("public static final ").append(
                            attributeTypeName).append(" ");

                    // check if the enum starts with a digit instead of a char.
                    // If so, append an underscore at start
                    if (m_strIsOff.indexOf(enumValue.charAt(0)) != -1)
                    {
                        strbufResult.append(strDepth2).append(attributeTypeName.substring(4)).append("_");
                    }

                    String enumName = enumValue;
                    if (enumName.indexOf(JDFConstants.DOT) >= 0 || enumName.indexOf(JDFConstants.HYPHEN) >= 0)
                    {
                        // The enumName has to be a valid Java identifier, so change dot and hyphen to underscore
                        enumName = enumName.replace('.', '_');
                        enumName = enumName.replace('-', '_');
                    }

                    strbufResult.append(enumName);

                    strbufResult.append(" = new ").append(attributeTypeName);
                    strbufResult.append("(\"").append(enumValue).append("\");").append(strLineEnd);
                }

                strbufResult.append(strDepth2).append("}      ").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);
            }
        }
    }


    /**
     * @param isEnum
     * @param attributeName
     * @return
     */
    private static boolean useEnumAttribute(boolean isEnum, String attributeName)
    {
        return isEnum && !"Version".equals(attributeName);
    }


    /**
     * @param complexTypeName
     * @param vAttributes
     * @param strbufResult
     */
    private static void appendAttributeGetterAndSetter(String complexTypeName,
            Vector vAttributes, StringBuffer strbufResult)
    {
        SchemaAttribute schemaAttribute = null;
        String attributeName;
        String modifiedAttributeName;
        String modifiedAttributeTypeName;
        String returnType;

        boolean isHeaderWritten = false;
        for (int i = 0; i < vAttributes.size(); i++)
        {
            schemaAttribute = (SchemaAttribute) vAttributes.elementAt(i);

            if (isCreateGetterAndSetter(schemaAttribute))
            {
                returnType = schemaAttribute.getStrReturnType();

                attributeName = schemaAttribute.getStrAttributeName();
                modifiedAttributeName     = attributeName;
                modifiedAttributeTypeName = "Enum" + modifiedAttributeName;
                if ("Status".equals(attributeName) || "EndStatus".equals(attributeName))
                {
                    modifiedAttributeName     = getStatusName(complexTypeName);
                    modifiedAttributeTypeName = getTypeName(complexTypeName);
                }
                else if ("MaxVersion".equals(attributeName))
                {
                    modifiedAttributeTypeName = "EnumVersion";
                }
                else if ("UpdatedStatus".equals(attributeName))
                {
                    modifiedAttributeTypeName = "JDFResource.EnumResStatus";
                }
                else if ("LinkUsage".equals(attributeName))
                {
                    modifiedAttributeTypeName = "JDFResourceLink.EnumUsage";
                }
                else if ("Usage".equals(attributeName) && "ResourceInfo".equals(complexTypeName))
                {
                    modifiedAttributeTypeName = "JDFResourceLink.EnumUsage";
                }
                else if ("Usage".equals(attributeName) && "ResourceQuParams".equals(complexTypeName))
                {
                    modifiedAttributeTypeName = "JDFResourceLink.EnumUsage";
                }
                else if ("Classes".equals(attributeName) && "ResourceQuParams".equals(complexTypeName))
                {
                    modifiedAttributeTypeName = "JDFResource.EnumResourceClass";
                }
                else if ("Usage".equals(attributeName) && "InsertSheet".equals(complexTypeName))
                {
                    modifiedAttributeTypeName = "EnumSheetUsage";
                }
                else if ("DeviceStatus".equals(attributeName) && "ModulePhase".equals(complexTypeName))
                {
                    modifiedAttributeTypeName = "JDFDeviceInfo.EnumDeviceStatus";
                }
                else if ("DeviceStatus".equals(attributeName) && "ModuleStatus".equals(complexTypeName))
                {
                    modifiedAttributeTypeName = "JDFDeviceInfo.EnumDeviceStatus";
                }
                else if ("HoleType".equals(attributeName) && "HoleMakingParams".equals(complexTypeName))
                {
                    modifiedAttributeTypeName = "JDFMedia.EnumHoleType";
                }


                if (!isHeaderWritten)
                {
                    strbufResult.append("/* ************************************************************************").append(strLineEnd);
                    strbufResult.append(" * Attribute getter / setter").append(strLineEnd);
                    strbufResult.append(" * ************************************************************************").append(strLineEnd);
                    strbufResult.append(" */").append(strLineEnd);
                    isHeaderWritten = true;
                }

                if (schemaAttribute.getIsEnum())
                {
                    appendAttributeGetterAndSetterForEnum(strbufResult, complexTypeName, schemaAttribute, attributeName, modifiedAttributeName, modifiedAttributeTypeName, returnType);
                }
                else
                {
                    appendAttributeGetterAndSetterForNonEnum(strbufResult, schemaAttribute, modifiedAttributeName, returnType);
                }
            }
        }
    }


    private static void appendAttributeGetterAndSetterForEnum(
            StringBuffer strbufResult, String complexTypeName, SchemaAttribute schemaAttribute,
            String attributeName, String modifiedAttributeName, String modifiedAttributeTypeName, String returnType)
    {
        // set
        if (!"Status".equals(attributeName)
                || "Queue".equals(complexTypeName) || "QueueEntry".equals(complexTypeName)
                || "ResourceCmdParams".equals(complexTypeName) || "ResourceInfo".equals(complexTypeName))
        {
            strbufResult.append(strDepth2).append(strLineEnd);
            strbufResult.append(strDepth2)
                .append("/* ---------------------------------------------------------------------").append(strLineEnd);
            strbufResult.append(strDepth2)
                .append("Methods for Attribute ").append(attributeName).append(strLineEnd);
            strbufResult.append(strDepth2)
                .append("--------------------------------------------------------------------- */").append(strLineEnd);

            if ("QueueEntry".equals(complexTypeName) && "Priority".equals(attributeName))
            {
                strbufResult.append(strDepth2)
                    .append("/**").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  * (3) set attribute ").append(modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  * @param enumVar: the enumVar to set the attribute to").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  */").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("public void set").append(modifiedAttributeName).append("(int priority)").append(strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("setIntAttribute(\"Priority\", priority, null);").append( strLineEnd);
                strbufResult.append(strDepth2).
                    append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);
            }
            else if ("QueueEntryPosParams".equals(complexTypeName) && "Position".equals(attributeName))
            {
                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("  * (4) set attribute ").append(
                        modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2)
                	.append("  * @param enumVar: the enumVar to set the attribute to").append(strLineEnd);
                strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                strbufResult.append(strDepth2).append("public void set").append(
                        modifiedAttributeName).append("(int position)").append(
                        strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);
                strbufResult.append(strDepth3).append(
                        "setIntAttribute(\"Position\", position, null);").append(
                        strLineEnd);
                strbufResult.append(strDepth2).append("}").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);
            }
            else if (schemaAttribute.getIsEnumList())
            {
                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("  * (5.2) set attribute ")
                        .append(modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2).append(
                        "  * @param v vector of the enumeration values").append(strLineEnd);
                strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                strbufResult.append(strDepth2).append("public void set").append(
                        modifiedAttributeName).append("(").append("Vector v)").append(
                        strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);
                strbufResult.append(strDepth3).append(
                        "setEnumerationsAttribute(AttributeName.").append(
                        attributeName.toUpperCase()).append(", v, null);").append(
                        strLineEnd);
                strbufResult.append(strDepth2).append("}").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);
            }
            else
            {
                strbufResult.append(strDepth2)
                    .append("/**").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  * (5) set attribute ").append(attributeName).append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  * @param enumVar: the enumVar to set the attribute to").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  */").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("public void set").append(modifiedAttributeName)
                    .append("(").append(modifiedAttributeTypeName).append(" enumVar)").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("{").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("setAttribute(AttributeName.").append(attributeName.toUpperCase())
                    .append(", enumVar.getName(), null);").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);
            }

            // get
            if ("Class".equals(modifiedAttributeName))
            {
                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("  * (6) get ")
                    .append(modifiedAttributeTypeName).append(" attribute ")
                    .append(modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2).append("  * @return ")
                    .append(modifiedAttributeTypeName).append(" the value of the attribute")
                    .append(strLineEnd);
                strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                strbufResult.append(strDepth2).append("public ")
                    .append(modifiedAttributeTypeName)
                    .append(" get").append(modifiedAttributeName).append("JDF()").append(strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);

                if (!schemaAttribute.getStrDefault().equals(JDFConstants.EMPTYSTRING))
                {
                    // System.out.println ("6");
                    strbufResult.append(strDepth3).append("return ")
                        .append(modifiedAttributeTypeName)
                        .append(".getEnum(getAttribute(AttributeName.")
                        .append(modifiedAttributeName.toUpperCase())
                        .append(", null, ").append("\"").append(schemaAttribute.getStrDefault()).append("\"));")
                        .append(strLineEnd);
                }
                else
                {
                    strbufResult.append(strDepth3).append("return ")
                        .append(modifiedAttributeTypeName)
                        .append(".getEnum(getAttribute(AttributeName.")
                        .append(modifiedAttributeName.toUpperCase())
                        .append(", null, null));").append(strLineEnd);
                }

                strbufResult.append(strDepth2).append("}").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);
            }
            else
            {
                if ("QueueEntry".equals(complexTypeName) && "Priority".equals(attributeName))
                {
                    strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * (7) get ").append(returnType).append(" attribute ").append(modifiedAttributeName).append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * @return ").append(returnType).append(" the value of the attribute").append(strLineEnd);
                    strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                    strbufResult.append(strDepth2).append("public int get").append(modifiedAttributeName).append("()").append(strLineEnd);
                    strbufResult.append(strDepth2).append("{").append(strLineEnd);
                    strbufResult.append(strDepth3).append("return getIntAttribute(\"Priority\", null, 0);").append(strLineEnd);
                    strbufResult.append(strDepth2).append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);
                }
                else if ("QueueEntryPosParams".equals(complexTypeName) && "Position".equals(attributeName))
                {
                    strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * (8) get ").append(returnType).append(" attribute ").append(modifiedAttributeName).append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * @return ").append(returnType).append(" the value of the attribute").append(strLineEnd);
                    strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("public int get").append(modifiedAttributeName).append("()").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("{").append(strLineEnd);
                    strbufResult.append(strDepth3)
                        .append("return getIntAttribute(\"Position\", null, -1);").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);
                }
                else if ("JobPhase".equals(complexTypeName) && "Status".equals(attributeName))
                {
                    strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * (35) get ").append(modifiedAttributeName).append(" attribute ").append(modifiedAttributeName).append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * @return ").append(modifiedAttributeTypeName).append(" the value of the attribute").append(strLineEnd);
                    strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("public ").append(modifiedAttributeTypeName)
                        .append(" get").append(modifiedAttributeName).append("JDF").append("()").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("{").append(strLineEnd);

                    if (!schemaAttribute.getStrDefault().equals( JDFConstants.EMPTYSTRING))
                    {
                        // System.out.println ("35");
                        strbufResult.append(strDepth3).append("return ")
                            .append(modifiedAttributeTypeName)
                            .append(".getEnum(getAttribute(AttributeName.")
                            .append(modifiedAttributeName.toUpperCase())
                            .append(", null, ").append("\"").append(schemaAttribute.getStrDefault())
                            .append("\"));").append(strLineEnd);
                    }
                    else
                    {
                        strbufResult.append(strDepth3).append("return ")
                            .append(modifiedAttributeTypeName)
                            .append(".getEnum(getAttribute(AttributeName.")
                            .append(modifiedAttributeName.toUpperCase())
                            .append(", null, null));").append(strLineEnd);
                    }

                    strbufResult.append(strDepth2).append("}").append(strLineEnd)
                            .append(strLineEnd).append(strLineEnd).append(strLineEnd);
                }
                else if (schemaAttribute.getIsEnumList())
                {
                    strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * (9.2) get ").append( modifiedAttributeName)
                        .append(" attribute ").append(modifiedAttributeName).append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * @return Vector of the enumerations").append(strLineEnd);
                    strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                    strbufResult.append(strDepth2).append("public Vector get").append(modifiedAttributeName).append("()").append(strLineEnd);
                    strbufResult.append(strDepth2).append("{").append(strLineEnd);

                    String defaultValue = schemaAttribute.getStrDefault();
                    if (defaultValue.equals(JDFConstants.EMPTYSTRING)) defaultValue = "getEnum(0)";

                    strbufResult.append(strDepth3).append("return getEnumerationsAttribute(AttributeName.")
                        .append(modifiedAttributeName.toUpperCase()).append(", null, ")
                        .append(modifiedAttributeTypeName).append(".").append(defaultValue)
                        .append(", false);").append(strLineEnd);
                    strbufResult.append(strDepth2).append("}").append(strLineEnd)
                        .append(strLineEnd).append(strLineEnd).append(strLineEnd);
                }
                else
                {
                    strbufResult.append(strDepth2)
                        .append("/**").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("  * (9) get attribute ").append(attributeName).append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("  * @return the value of the attribute").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("  */").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("public ").append(modifiedAttributeTypeName)
                        .append(" get").append(modifiedAttributeName).append("()").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("{").append(strLineEnd);

                    if (!schemaAttribute.getStrDefault().equals(JDFConstants.EMPTYSTRING))
                    {
                        strbufResult.append(strDepth3)
                            .append("return ")
                            .append(modifiedAttributeTypeName)
                            .append(".getEnum(getAttribute(AttributeName.")
                            .append(attributeName.toUpperCase())
                            .append(", null, ").append("\"").append(schemaAttribute.getStrDefault())
                            .append("\"));").append(strLineEnd);
                    }
                    else
                    {
                        strbufResult.append(strDepth3)
                            .append("return ")
                            .append(modifiedAttributeTypeName)
                            .append(".getEnum(getAttribute(AttributeName.")
                            .append(attributeName.toUpperCase())
                            .append(", null, null));").append(strLineEnd);
                    }

                    strbufResult.append(strDepth2)
                        .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);
                }
            }
        }
    }


    private static void appendAttributeGetterAndSetterForNonEnum(
            StringBuffer strbufResult, SchemaAttribute schemaAttribute, String modifiedAttributeName, String returnType)
    {
        strbufResult.append(strDepth2).append(strLineEnd);
        strbufResult.append(strDepth2)
            .append("/* ---------------------------------------------------------------------").append(strLineEnd);
        strbufResult.append(strDepth2)
            .append("Methods for Attribute ").append(modifiedAttributeName).append(strLineEnd);
        strbufResult.append(strDepth2)
            .append("--------------------------------------------------------------------- */").append(strLineEnd);

        if ("JDFDate".equals(returnType))
        {
            if ("duration".equals(schemaAttribute.getStrType()))
            {
                // for duration its no longer JDFDate its JDFDuration
                // TODO das ist ein quick hack, das muss normal in der
                // Datensammlung geändert werden!
                returnType = "JDFDuration";
                // set1
                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("  * (32) set attribute ")
                        .append(modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                strbufResult.append(strDepth2).append("public void set").append(
                        modifiedAttributeName).append("()").append(strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);
                strbufResult.append(strDepth3).append("setAttribute(AttributeName.")
                        .append(modifiedAttributeName.toUpperCase()).append(
                                ", new JDFDuration().getDurationISO(), null);").append(
                                strLineEnd);
                strbufResult.append(strDepth2).append("}").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);

                // set2
                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("  * (33) set attribute ")
                        .append(modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2)
                	.append("  * @param value: the value to set the attribute to").append(strLineEnd);
                strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                strbufResult.append(strDepth2).append("public void set").append(
                        modifiedAttributeName).append("(").append(returnType).append(
                        " value)").append(strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);
                strbufResult.append(strDepth3).append("setAttribute(AttributeName.")
                        .append(modifiedAttributeName.toUpperCase()).append(
                                ", value.getDurationISO(), null);").append(strLineEnd);
                strbufResult.append(strDepth2).append("}").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);

                // get 5
                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("  * (34) get ").append(
                        returnType).append(" attribute ").append(modifiedAttributeName)
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("  * @return ")
                        .append(returnType).append(" the value of the attribute")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                strbufResult.append(strDepth2).append("public ").append(returnType)
                        .append(" get").append(modifiedAttributeName).append("()")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);
                strbufResult.append(strDepth3).append("JDFDuration nMyDate = null;")
                        .append(strLineEnd);
                strbufResult.append(strDepth3).append("String str = \"\";").append(
                        strLineEnd);
                strbufResult.append(strDepth3).append(
                        "str = getAttribute(AttributeName.").append(
                        modifiedAttributeName.toUpperCase()).append(", null, JDFConstants.EMPTYSTRING);")
                        .append(strLineEnd);
                strbufResult.append(strDepth3).append("if (!str.equals(\"\"))").append(
                        strLineEnd);
                strbufResult.append(strDepth3).append("{").append(strLineEnd);
                strbufResult.append(strDepth4).append("try").append(strLineEnd);
                strbufResult.append(strDepth4).append("{").append(strLineEnd);
                strbufResult.append(strDepth5)
                        .append("nMyDate = new JDFDuration(str);").append(strLineEnd);
                strbufResult.append(strDepth4).append("}").append(strLineEnd);
                strbufResult.append(strDepth4).append("catch(DataFormatException dfe)")
                        .append(strLineEnd);
                strbufResult.append(strDepth4).append("{").append(strLineEnd);
                strbufResult
                        .append(strDepth5)
                        .append(
                                "throw new JDFException(\"not a valid date string. Malformed JDF\");")
                        .append(strLineEnd);
                strbufResult.append(strDepth4).append("}").append(strLineEnd);
                strbufResult.append(strDepth3).append("}").append(strLineEnd);
                strbufResult.append(strDepth3).append("return nMyDate;").append(
                        strLineEnd);
                strbufResult.append(strDepth2).append("}").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);
            }
            else
            {
                // set 10
                strbufResult.append(strDepth2)
                    .append("/**").append(strLineEnd);
                strbufResult.append(strDepth2)
                .append("  * (10) set attribute ").append(modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2)
                .append("  * @deprecated 2005-12-02 use set").append(modifiedAttributeName).append("(null)").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  */").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("public void set").append(modifiedAttributeName).append("()").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("{").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("setAttribute(AttributeName.").append(modifiedAttributeName.toUpperCase())
                        .append(", new JDFDate().getDateTimeISO(), null);").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);

                // set 11
                strbufResult.append(strDepth2)
                    .append("/**").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  * (11) set attribute ").append(modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  * @param value: the value to set the attribute to or null").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  */").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("public void set").append(modifiedAttributeName).append("(")
                        .append(returnType).append(" value)").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("{").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("if (value == null) value = new JDFDate();").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("setAttribute(AttributeName.").append(modifiedAttributeName.toUpperCase())
                        .append(", value.getDateTimeISO(), null);").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);

                // get 12
                strbufResult.append(strDepth2)
                    .append("/**").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  * (12) get ").append(returnType).append(" attribute ").append(modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  * @return ").append(returnType).append(" the value of the attribute").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  */").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("public ").append(returnType).append(" get").append(modifiedAttributeName).append("()").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("{").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("JDFDate nMyDate = null;").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("String str = JDFConstants.EMPTYSTRING;").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("str = getAttribute(AttributeName.").append(modifiedAttributeName.toUpperCase()).append(", null, JDFConstants.EMPTYSTRING);").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("if (!JDFConstants.EMPTYSTRING.equals(str))").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("{").append(strLineEnd);
                strbufResult.append(strDepth4)
                    .append("try").append(strLineEnd);
                strbufResult.append(strDepth4)
                    .append("{").append(strLineEnd);
                strbufResult.append(strDepth5)
                    .append("nMyDate = new JDFDate(str);").append(strLineEnd);
                strbufResult.append(strDepth4).append("}")
                    .append(strLineEnd);
                strbufResult.append(strDepth4)
                    .append("catch(DataFormatException dfe)").append(strLineEnd);
                strbufResult.append(strDepth4)
                    .append("{").append(strLineEnd);
                strbufResult.append(strDepth5)
                    .append("throw new JDFException(\"not a valid date string. Malformed JDF\");").append(strLineEnd);
                strbufResult.append(strDepth4)
                    .append("}").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("}").append(strLineEnd);
                strbufResult.append(strDepth3)
                    .append("return nMyDate;").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);
            }
        }
        else
        {
            // this SET is a quickfix TBD
            if (returnType.startsWith("EnumNamedColor"))
            {
                // set
                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("  * (13) set attribute ")
                    .append(modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2)
                	.append("  * @param value: the value to set the attribute to").append(strLineEnd);
                strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                strbufResult.append(strDepth2).append("public void set").append(
                        modifiedAttributeName).append("(").append(returnType).append(
                        " value)").append(strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);
                strbufResult.append(strDepth3).append("setAttribute(AttributeName.")
                        .append(modifiedAttributeName.toUpperCase()).append(
                                ", value.getName(), null);").append(strLineEnd);
                strbufResult.append(strDepth2).append("}").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);
            }
            else
            {
                // set 2 everything above in the same else statement is deprecated
                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("  * (36) set attribute ")
                        .append(modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2)
                	.append("  * @param value: the value to set the attribute to").append(strLineEnd);
                strbufResult.append(strDepth2).append("  */").append(strLineEnd);

                if ("Attribute".equals(modifiedAttributeName))
                {
                    strbufResult.append(strDepth2)
                        .append("public void set").append(modifiedAttributeName).append("JDF(")
                            .append(returnType).append(" value)").append(strLineEnd);
                }
                else
                {
                    strbufResult.append(strDepth2)
                        .append("public void set").append(modifiedAttributeName).append("(")
                            .append(returnType).append(" value)").append(strLineEnd);
                }

                strbufResult.append(strDepth2).append("{").append(strLineEnd);

                // method body
                if ("JDFXYPairRangeList".equals(returnType))
                {
                    strbufResult.append(strDepth3)
                    .append("setAttribute(AttributeName.").append(modifiedAttributeName.toUpperCase())
                    .append(", value.toString(), null);").append(strLineEnd);
                }
                else
                {
                    strbufResult.append(strDepth3)
                        .append("setAttribute(AttributeName.").append(modifiedAttributeName.toUpperCase())
                            .append(", value, null);").append(strLineEnd);
                }

                strbufResult.append(strDepth2).append("}").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);

            }

            if ("int".equals(returnType))
            {
                // get 1
                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("  * (15) get ").append(
                        returnType).append(" attribute ").append(modifiedAttributeName)
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("  * @return ")
                        .append(returnType).append(" the value of the attribute")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                strbufResult.append(strDepth2).append("public ").append(returnType)
                        .append(" get").append(modifiedAttributeName).append("()")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);

                if (!schemaAttribute.getStrDefault().equals(JDFConstants.EMPTYSTRING))
                {
                    strbufResult.append(strDepth3).append(
                            "return getIntAttribute(AttributeName.").append(
                            modifiedAttributeName.toUpperCase()).append(
                            ", null, " + schemaAttribute.getStrDefault() + ");")
                            .append(strLineEnd);
                }
                else
                {
                    strbufResult.append(strDepth3).append(
                            "return getIntAttribute(AttributeName.").append(
                            modifiedAttributeName.toUpperCase()).append(", null, 0);")
                            .append(strLineEnd);
                }

                strbufResult.append(strDepth2).append("}").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);
            }
            else if ("double".equals(returnType))
            {
                // get 2
                if ("Length".equals(modifiedAttributeName))
                {
                    strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * (16) get ").append(
                            returnType).append(" attribute ").append(
                            modifiedAttributeName).append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * @return ").append(
                            returnType).append(" the value of the attribute").append(
                            strLineEnd);
                    strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                    strbufResult.append(strDepth2).append("public ").append(returnType)
                            .append(" get").append(modifiedAttributeName).append(
                                    "JDF()").append(strLineEnd);
                    strbufResult.append(strDepth2).append("{").append(strLineEnd);
                    strbufResult.append(strDepth3).append(
                            "return getRealAttribute(AttributeName.").append(
                            modifiedAttributeName.toUpperCase())
                            .append(", null, 0.0);").append(strLineEnd);
                    strbufResult.append(strDepth2).append("}").append(strLineEnd)
                            .append(strLineEnd).append(strLineEnd).append(strLineEnd);
                }
                else
                {
                    strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * (17) get ").append(
                            returnType).append(" attribute ").append(
                            modifiedAttributeName).append(strLineEnd);
                    strbufResult.append(strDepth2).append("  * @return ").append(
                            returnType).append(" the value of the attribute").append(
                            strLineEnd);
                    strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                    strbufResult.append(strDepth2).append("public ").append(returnType)
                            .append(" get").append(modifiedAttributeName).append("()")
                            .append(strLineEnd);
                    strbufResult.append(strDepth2).append("{").append(strLineEnd);
                    strbufResult.append(strDepth3).append(
                            "return getRealAttribute(AttributeName.").append(
                            modifiedAttributeName.toUpperCase())
                            .append(", null, 0.0);").append(strLineEnd);
                    strbufResult.append(strDepth2).append("}").append(strLineEnd)
                            .append(strLineEnd).append(strLineEnd).append(strLineEnd);
                }
            }
            else if ("boolean".equals(returnType))
            {
                // get 3
                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("  * (18) get ").append(
                        returnType).append(" attribute ").append(modifiedAttributeName)
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("  * @return ")
                        .append(returnType).append(" the value of the attribute")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("  */").append(strLineEnd);

                if ("true".equals(schemaAttribute.getStrDefault()))
                {
                    strbufResult.append(strDepth2).append("public ").append(returnType)
                            .append(" get").append(modifiedAttributeName).append("()")
                            .append(strLineEnd);
                    strbufResult.append(strDepth2).append("{").append(strLineEnd);
                    strbufResult.append(strDepth3).append(
                            "return getBoolAttribute(AttributeName.").append(
                            modifiedAttributeName.toUpperCase()).append(
                            ", null, true);").append(strLineEnd);
                    strbufResult.append(strDepth2).append("}").append(strLineEnd)
                            .append(strLineEnd).append(strLineEnd).append(strLineEnd);
                }
                else
                {
                    strbufResult.append(strDepth2).append("public ").append(returnType)
                            .append(" get").append(modifiedAttributeName).append("()")
                            .append(strLineEnd);
                    strbufResult.append(strDepth2).append("{").append(strLineEnd);
                    strbufResult.append(strDepth3).append(
                            "return getBoolAttribute(AttributeName.").append(
                            modifiedAttributeName.toUpperCase()).append(
                            ", null, false);").append(strLineEnd);
                    strbufResult.append(strDepth2).append("}").append(strLineEnd)
                            .append(strLineEnd).append(strLineEnd).append(strLineEnd);
                }

            }
            else if ("EnumNamedColor".equals(returnType))
            {
                // get 3.5
                strbufResult.append(strDepth2).append("/**").append(strLineEnd);
                strbufResult.append(strDepth2).append("  * (19) get ").append(
                        returnType).append(" attribute ").append(modifiedAttributeName)
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("  * @return ")
                        .append(returnType).append(" the value of the attribute")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("  */").append(strLineEnd);
                strbufResult.append(strDepth2).append("public ").append(returnType)
                        .append(" get").append(modifiedAttributeName).append("()")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);
                strbufResult.append(strDepth3).append("String strAttrName = \"\";")
                        .append(strLineEnd);
                strbufResult.append(strDepth3).append(returnType).append(
                        " nPlaceHolder = null;").append(strLineEnd);
                strbufResult.append(strDepth3).append(
                        "strAttrName = getAttribute(AttributeName.").append(
                        modifiedAttributeName.toUpperCase()).append(", null, JDFConstants.EMPTYSTRING);")
                        .append(strLineEnd);
                strbufResult.append(strDepth3).append("nPlaceHolder = ").append(
                        returnType).append(".getEnum(strAttrName);").append(strLineEnd);
                strbufResult.append(strDepth3).append("return nPlaceHolder;").append(
                        strLineEnd);
                strbufResult.append(strDepth2).append("}").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);
            }
            else if (!("String".equals(returnType)) && !"VString".equals(returnType))
            {
                // get 4
                strbufResult.append(strDepth2)
                    .append("/**").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  * (20) get ").append(returnType).append(" attribute ")
                    .append(modifiedAttributeName).append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  * @return ").append(returnType)
                    .append(" the value of the attribute, null if a the").append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  *         attribute value is not a valid to create a ")
                    .append(returnType).append(strLineEnd);
                strbufResult.append(strDepth2)
                    .append("  */").append(strLineEnd);
                strbufResult.append(strDepth2).append("public ").append(returnType)
                        .append(" get").append(modifiedAttributeName).append("()")
                        .append(strLineEnd);
                strbufResult.append(strDepth2).append("{").append(strLineEnd);
                strbufResult.append(strDepth3).append("String strAttrName = \"\";")
                        .append(strLineEnd);
                strbufResult.append(strDepth3).append(returnType).append(
                        " nPlaceHolder = null;").append(strLineEnd);
                strbufResult.append(strDepth3).append(
                        "strAttrName = getAttribute(AttributeName.").append(
                        modifiedAttributeName.toUpperCase()).append(", null, JDFConstants.EMPTYSTRING);")
                        .append(strLineEnd);
                strbufResult.append(strDepth3).append("try").append(strLineEnd);
                strbufResult.append(strDepth3).append("{").append(strLineEnd);
                strbufResult.append(strDepth4).append("nPlaceHolder = new ").append(
                        returnType).append("(strAttrName);").append(strLineEnd);
                strbufResult.append(strDepth3).append("}").append(strLineEnd);
                strbufResult.append(strDepth3).append("catch(DataFormatException e)")
                        .append(strLineEnd);
                strbufResult.append(strDepth3).append("{").append(strLineEnd);
                strbufResult.append(strDepth4).append("return null;")
                        .append(strLineEnd);
                strbufResult.append(strDepth3).append("}").append(strLineEnd);
                strbufResult.append(strDepth3).append("return nPlaceHolder;").append(
                        strLineEnd);
                strbufResult.append(strDepth2).append("}").append(strLineEnd).append(
                        strLineEnd).append(strLineEnd).append(strLineEnd);
            }
            else
            {
                if (!"rRefs".equals(modifiedAttributeName))// the rRef getter is
                // defined in superclass
                {
                    if ("VString".equals(returnType))
                    {
                        // get 5
                        strbufResult.append(strDepth2).append("/**").append(strLineEnd);

                        if ("Attributes".equals(modifiedAttributeName))
                        {
                            strbufResult.append(strDepth2).append("  * (21) get ")
                                    .append(returnType).append(" attribute ").append(
                                            modifiedAttributeName).append("JDF")
                                    .append(strLineEnd);
                            strbufResult.append(strDepth2).append("  * @return ")
                                    .append(returnType).append(
                                            " the value of the attribute").append(
                                            strLineEnd);
                            strbufResult.append(strDepth2).append("  */").append(
                                    strLineEnd);
                            strbufResult.append(strDepth2).append("public ").append(
                                    returnType).append(" get").append(
                                    modifiedAttributeName).append("JDF").append("()")
                                    .append(strLineEnd);
                        }
                        else
                        {
                            strbufResult.append(strDepth2).append("  * (21) get ")
                                    .append(returnType).append(" attribute ").append(
                                            modifiedAttributeName).append(strLineEnd);
                            strbufResult.append(strDepth2).append("  * @return ")
                                    .append(returnType).append(
                                            " the value of the attribute").append(
                                            strLineEnd);
                            strbufResult.append(strDepth2).append("  */").append(
                                    strLineEnd);
                            strbufResult.append(strDepth2).append("public ").append(
                                    returnType).append(" get").append(
                                    modifiedAttributeName).append("()").append(
                                    strLineEnd);
                        }

                        strbufResult.append(strDepth2).append("{").append(strLineEnd);
                        strbufResult.append(strDepth3).append(
                                "VString vStrAttrib = new VString();").append(
                                strLineEnd);

                        if (schemaAttribute.getStrDefault().equals(
                                JDFConstants.EMPTYSTRING))
                        {
                            strbufResult.append(strDepth3).append(
                                    "String  s = getAttribute(AttributeName.").append(
                                    modifiedAttributeName.toUpperCase()).append(
                                    ", null, JDFConstants.EMPTYSTRING);").append(strLineEnd);
                        }
                        else
                        {
                            strbufResult.append(strDepth3).append(
                                    "String  s = getAttribute(AttributeName.").append(
                                    modifiedAttributeName.toUpperCase()).append(
                                    ", null, \"").append(
                                    schemaAttribute.getStrDefault()).append("\");")
                                    .append(strLineEnd);
                        }

                        strbufResult.append(strDepth3).append(
                                "vStrAttrib.setAllStrings(s, \" \");").append(
                                strLineEnd);
                        strbufResult.append(strDepth3).append("return vStrAttrib;")
                                .append(strLineEnd);
                        strbufResult.append(strDepth2).append("}").append(strLineEnd)
                                .append(strLineEnd).append(strLineEnd).append(
                                        strLineEnd);
                    }
                    else
                    {
                        if ("Attributes".equals(modifiedAttributeName)
                                || "Attribute".equals(modifiedAttributeName))
                        {
                            // get 5
                            strbufResult.append(strDepth2).append("/**").append(
                                    strLineEnd);
                            strbufResult.append(strDepth2).append("  * (22) get ")
                                    .append(returnType).append(" attribute ").append(
                                            modifiedAttributeName).append(strLineEnd);
                            strbufResult.append(strDepth2).append("  * @return ")
                                    .append(returnType).append(
                                            " the value of the attribute").append(
                                            strLineEnd);
                            strbufResult.append(strDepth2).append("  */").append(
                                    strLineEnd);
                            strbufResult.append(strDepth2).append("public ").append(
                                    returnType).append(" get").append(
                                    modifiedAttributeName).append("JDF()").append(
                                    strLineEnd);
                            strbufResult.append(strDepth2).append("{").append(
                                    strLineEnd);
                            strbufResult.append(strDepth3).append(
                                    "return getAttribute(AttributeName.").append(
                                    modifiedAttributeName.toUpperCase()).append(
                                    ", null, JDFConstants.EMPTYSTRING);").append(strLineEnd);
                            strbufResult.append(strDepth2).append("}").append(
                                    strLineEnd).append(strLineEnd).append(strLineEnd)
                                    .append(strLineEnd);
                        }
                        else
                        {
                            // get 5
                            strbufResult.append(strDepth2).append("/**").append(
                                    strLineEnd);
                            strbufResult.append(strDepth2).append("  * (23) get ")
                                    .append(returnType).append(" attribute ")
                                    .append(modifiedAttributeName).append(strLineEnd);
                            strbufResult.append(strDepth2)
                            	.append("  * @return the value of the attribute").append(strLineEnd);
                            strbufResult.append(strDepth2).append("  */").append(
                                    strLineEnd);
                            strbufResult.append(strDepth2).append("public ").append(
                                    returnType).append(" get").append(
                                    modifiedAttributeName).append("()").append(strLineEnd);
                            strbufResult.append(strDepth2).append("{").append(strLineEnd);

                            if (schemaAttribute.getStrDefault().equals(
                                    JDFConstants.EMPTYSTRING))
                            {
                                strbufResult.append(strDepth3).append(
                                        "return getAttribute(AttributeName.").append(
                                        modifiedAttributeName.toUpperCase()).append(
                                        ", null, JDFConstants.EMPTYSTRING);").append(strLineEnd);
                            }
                            else
                            {
                                strbufResult.append(strDepth3).append(
                                        "return getAttribute(AttributeName.").append(
                                        modifiedAttributeName.toUpperCase()).append(
                                        ", null, \"").append(
                                        schemaAttribute.getStrDefault()).append("\");")
                                        .append(strLineEnd);
                            }

                            strbufResult.append(strDepth2).append("}").append(
                                    strLineEnd).append(strLineEnd).append(strLineEnd)
                                    .append(strLineEnd);
                        }
                    }
                }
            }
        }
    }


    private static boolean isCreateGetterAndSetter(SchemaAttribute schemaAttribute)
    {
        // Attributes already defined in JDFElement, we need them here only for AtrInfoTable ...
        return !("Version".equals(schemaAttribute.getStrAttributeName()) );
    }


    /**
     * @param strComplexTypeName
     * @param vElements
     * @param strbufResult
     */
    private static void appendElementGetterAndSetter(String strComplexTypeName, Vector vElements,
            StringBuffer strbufResult)
    {
        String strElementName;
        String strReturnType;

        boolean isHeaderWritten = false;
        for (int i = 0; i < vElements.size(); i++)
        {
            if (!isHeaderWritten)
            {
                strbufResult.append("/* ***********************************************************************").append(strLineEnd);
                strbufResult.append(" * Element getter / setter").append(strLineEnd);
                strbufResult.append(" * ***********************************************************************").append(strLineEnd);
                strbufResult.append(" */").append(strLineEnd).append(strLineEnd);
                isHeaderWritten = true;
            }

            m_nSchemaElement = (SchemaElement) vElements.elementAt(i);
            strElementName = m_nSchemaElement.getStrElementName();
            if (!strElementName.endsWith("Ref"))
            {
                strReturnType = m_nSchemaElement.getStrReturnType();
                if ("1".equals(m_nSchemaElement.getStrMaxOccurs()))
                {
                    // get
                    if ("FileSpec".equals(strComplexTypeName) &&
                            ("JDFDisposition".equals(strReturnType) || "JDFContainer".equals(strReturnType)))
                    {
                        // special case for
                        // 1. deprecated element FileSpec.Disposition, as there exists
                        //    a not deprecated attribute FileSpec.Disposition with
                        //    method EnumDisposition getDisposition()
                        // 2. element FileSpec.Container, as there already exists
                        //    a different method org.apache.xerces.dom.NodeImpl.getContainer()
                        strbufResult.append(strDepth1)
                            .append("/**").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append(" * (24) const get element ").append(strElementName).append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append(" * @param iSkip number of elements to skip").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append(" * @return ").append(strReturnType).append(" the element").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append(" * default is get").append(strElementName).append("(0)");
                        strbufResult.append(strDepth1)
                            .append(" */").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("public ").append(strReturnType).append(" get").append(strElementName)
                            .append("(int iSkip)").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("{").append(strLineEnd);
                        strbufResult.append(strDepth2)
                            .append("return (").append(strReturnType).append(") getElement(ElementName.")
                            .append(strElementName.toUpperCase ()).append(", null, iSkip);").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);
                    }
                    else
                    {
                        strbufResult.append(strDepth1)
                            .append("/**").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append(" * (24) const get element ").append(strElementName).append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append(" * @return ").append(strReturnType).append(" the element").append(strLineEnd);
                         strbufResult.append(strDepth1)
                             .append(" */").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("public ").append(strReturnType).append(" get").append(strElementName).append("()").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("{").append(strLineEnd);
                        strbufResult.append(strDepth2)
                            .append("return (").append(strReturnType).append(") getElement(ElementName.")
                            .append(strElementName.toUpperCase()).append(", null, 0);").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);
                    }

                    // getCreate
                    strbufResult.append(strDepth1)
                        .append("/** (25) getCreate").append(strElementName).append(strLineEnd);
                    strbufResult.append(strDepth1).append(" * ").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append(" * @return ").append(strReturnType + " the element").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append(" */").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("public ").append(strReturnType).append(" getCreate").append(strElementName).append("()").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("{").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("return (").append(strReturnType).append(") getCreateElement_KElement(ElementName.")
                        .append(strElementName.toUpperCase()).append(", null, 0);").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);
                }
                else
                {
                    // getCreate
                    strbufResult.append(strDepth1)
                        .append("/** (26) getCreate").append(strElementName).append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append(" * ").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append(" * @param iSkip number of elements to skip").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append(" * @return ").append(strReturnType).append(" the element").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append(" */").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("public ").append(strReturnType).append(" getCreate").append(strElementName).append("(int iSkip)").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("{").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("return (").append(strReturnType).append(")getCreateElement_KElement(ElementName.")
                        .append(strElementName.toUpperCase()).append(", null, iSkip);").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("}").append(strLineEnd).append(strLineEnd).append(strLineEnd).append(strLineEnd);

                    // get
                    strbufResult.append(strDepth1).append("/**").append(strLineEnd);
                    strbufResult.append(strDepth1).append(" * (27) const get element ").append(
                            strElementName).append(strLineEnd);
                    strbufResult.append(strDepth1).append(
                            " * @param iSkip number of elements to skip").append(strLineEnd);
                    strbufResult.append(strDepth1).append(" * @return ").append(strReturnType)
                            .append(" the element").append(strLineEnd);
                    strbufResult.append(strDepth1).append(" * default is get").append(strElementName)
                            .append("(0)");
                    strbufResult.append(strDepth1).append(" */").append(strLineEnd);
                    strbufResult.append(strDepth1).append("public ").append(strReturnType).append(
                            " get").append(strElementName).append("(int iSkip)").append(
                            strLineEnd);
                    strbufResult.append(strDepth1).append("{").append(strLineEnd);
                    strbufResult.append(strDepth2).append("return (").append(strReturnType).append(
                            ") getElement(ElementName.").append(strElementName.toUpperCase())
                            .append(", null, iSkip);").append(strLineEnd);
                    strbufResult.append(strDepth1).append("}").append(strLineEnd).append(strLineEnd)
                            .append(strLineEnd).append(strLineEnd);
                }


                // TODO change that if statement, this is very quick and very
                // very dirty. Signature has to be unbounded but
                // is recognized as MaxOccures = 1 in GeneratorUtil.getNodeUsageString
                // FIX that method so Signature is correctly handled
                if ("1".equals(m_nSchemaElement.getStrMaxOccurs())
                        && !("Layout".equals(strComplexTypeName) && "Signature".equals(m_nSchemaElement.getStrElementName())))
                {
                    // append
                    strbufResult.append(strLineEnd).append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("/**").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append(" * (29) append element").append(strElementName).append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append(" */").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("public ").append(strReturnType).append(" append").append(strElementName).append("() throws JDFException").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("{").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("return (").append(strReturnType).append(") appendElementN(ElementName.")
                        .append(strElementName.toUpperCase()).append(", 1, null);").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("}").append(strLineEnd);
                }
                else if ("Layout".equals(strComplexTypeName) && "Signature".equals(m_nSchemaElement.getStrElementName()))
                {
                    strbufResult.append(strLineEnd).append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("/**").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append(" * (30) append element strElementName").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append(" */").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("public ").append(strReturnType).append(" append").append(strElementName).append("() throws JDFException").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("{").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("return (").append(strReturnType).append(") appendElement(ElementName.")
                        .append(strElementName.toUpperCase()).append(", null);").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("}").append(strLineEnd);
                }
                else
                {
                    // append
                    strbufResult.append(strDepth1)
                        .append("public ").append(strReturnType).append(" append").append(strElementName).append("()").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("{").append(strLineEnd);
                    strbufResult.append(strDepth2)
                        .append("return (").append(strReturnType).append(") appendElement(ElementName.")
                        .append(strElementName.toUpperCase()).append(", null);").append(strLineEnd);
                    strbufResult.append(strDepth1)
                        .append("}").append(strLineEnd).append(strLineEnd);
                }

                // Ref
                for (int j = 0; j < vElements.size(); j++)
                {
                    String strHelperName = ((SchemaElement) vElements.elementAt(j)).getStrElementName();

                    if ((strElementName + "Ref").equals(strHelperName)
                            && !strElementName.equals("MISDetails") // TODO remove when schema fixes MISDetails as element
                       )
                    {
                        strbufResult.append(strDepth1)
                            .append("/**").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("  * (31) create inter-resource link to refTarget").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("  * @param refTarget the element that is referenced").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("  */").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("public void ref").append(strElementName).append("(JDF").append(strElementName).append(" refTarget)").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("{").append(strLineEnd);
                        strbufResult.append(strDepth2)
                            .append("refElement(refTarget);").append(strLineEnd);
                        strbufResult.append(strDepth1)
                            .append("}").append(strLineEnd);
                    }
                }
            }
        }

        strbufResult.append(GeneratorStringUtil.getStrCloseFile()).append("// end namespace JDF").append(strLineEnd);
    }


    public static boolean isKindOf(Object obj, String type) throws ClassNotFoundException
    {
        // get the class def for obj and type
        Class c = obj.getClass();
        Class tClass = Class.forName(type);

        // check against type and superclasses
        while (c != null)
        {
            // have we found the given classname?
            if (c == tClass)
                return true;

            c = c.getSuperclass();
        }

        return false;
    }

}
