/**
*
*  Copyright (c)   2002 Heidelberger Druckmaschinen AG, All Rights Reserved.
*  Author:         Kai Mattern
*  Titel:          JavaCoreStringUtil.java
*  Version:        1.0
*  Description:    The xml Schema is partitioned into many "complex type's" 
*                  these types have children named "attributes" and "elements"
*                  this file is for describing all values a "element" can have.
*  Counter         34
*
*  History:        08-26-2004  Kai Mattern 
*
*/

package org.cip4.jdflib.generator;

import java.util.HashSet;
import java.util.Vector;

import org.cip4.jdflib.core.JDFConstants;



public class CppCoreStringUtil
{
    static final String strLineEnd     ="\n";

    static final String strDepth1                       = "    ";
    static final String strDepth2                       = "        ";
    static final String strDepth3                       = "            ";
    static final String strDepth4                       = "                ";
    static final String strDepth5                       = "                    ";
    
    //private static String m_strIsOff                    = "0123456789";
    
    public static String getStrHeaderFile(SchemaComplexType nSchemaComplexType)
    {
        SchemaComplexType myComplexType     = nSchemaComplexType;
        
        String strAttributeName             = JDFConstants.EMPTYSTRING;
        String strElementName               = JDFConstants.EMPTYSTRING;
        String strReturnType                = JDFConstants.EMPTYSTRING;
        String strAttribDefault             = JDFConstants.EMPTYSTRING;
        String strExtends                   = myComplexType.getStrExtendsName();
        String strJDFName                   = getJDFName(myComplexType);
        String strWrapperName               = "JDF" + myComplexType.m_SchemaComplexTypeName;
        
        int iNumOfAttribs                   = myComplexType.m_vSchemaAttributes.size();
        int iNumOfElements                  = myComplexType.m_vSchemaElements.size();
        
        Vector vAttributes                  = myComplexType.m_vSchemaAttributes;
        Vector VElements                    = myComplexType.m_vSchemaElements;
        
        SchemaAttribute[] schemaAttr        = new SchemaAttribute[iNumOfAttribs];
        SchemaElement[] schemaEle           = new SchemaElement[iNumOfElements];
        
        StringBuffer strFile                = new StringBuffer(100000);
        
        createSchemaAttributeArray(vAttributes, schemaAttr);
        createSchemaElementArray(VElements, schemaEle);
        
        strFile.append(GeneratorStringUtil.getStrAllCopyrightInformation()).append(strLineEnd);
        strFile.append("//EndCopyRight").append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append("///////////////////////////////////////////////////////////////////").append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append("#if !defined _").append(strJDFName).append("_H_").append(strLineEnd);
        strFile.append("#define _").append(strJDFName).append("_H_").append(strLineEnd);
        strFile.append("#if _MSC_VER >= 1000").append(strLineEnd);
        strFile.append("#pragma once").append(strLineEnd);
        strFile.append("#endif // _MSC_VER >= 1000").append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append("#include \"jdf/wrapper/").append(strExtends).append(".h\"").append(strLineEnd);
        strFile.append("namespace JDF{").append(strLineEnd);
        strFile.append(getForwardDeclaration(myComplexType)).append(strLineEnd);
        strFile.append("/*").append(strLineEnd);
        strFile.append("*********************************************************************").append(strLineEnd);
        strFile.append("class ").append(strJDFName).append(" : public ").append(strExtends).append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append("*********************************************************************").append(strLineEnd);
        strFile.append("*/").append(strLineEnd);
        strFile.append("/**").append(strLineEnd);
        strFile.append("* automatically generated header for ").append(strJDFName).append(" class").append(strLineEnd);
        strFile.append("*").append(strLineEnd);
        strFile.append("* Warning! Do not edit! This file may be regenerated").append(strLineEnd);
        strFile.append("* The child Class: @see ").append(strWrapperName).append(" should be edited instead").append(strLineEnd);
        strFile.append("*/").append(strLineEnd);
        strFile.append("class JDF_WRAPPERCORE_EXPORT ").append(strJDFName).append(" : public ").append(strExtends).append("{").append(strLineEnd);
        strFile.append("public:").append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append("// Constructors / Destructors").append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append(strLineEnd);        
        strFile.append("protected:").append(strLineEnd);
        strFile.append("/**").append(strLineEnd);
        strFile.append("* null ctor").append(strLineEnd);
        strFile.append("*/").append(strLineEnd);
        strFile.append(strDepth1).append("inline ").append(strJDFName).append("():").append(strExtends).append("(){};").append(strLineEnd);
        strFile.append("/**").append(strLineEnd);
        strFile.append("* copy ctor").append(strLineEnd);
        strFile.append("*/").append(strLineEnd);
        strFile.append(strDepth1).append("inline ").append(strJDFName).append("(const KElement & other):").append(strExtends).append("(){").append(strLineEnd);
        strFile.append(strDepth1).append("*this=other;").append(strLineEnd);
        strFile.append("};").append(strLineEnd);
        strFile.append("/**").append(strLineEnd);
        strFile.append("* copy equivalance operator").append(strLineEnd);
        strFile.append("*/").append(strLineEnd);
        strFile.append(strDepth1).append(strJDFName).append(" &operator =(const KElement& other);").append(strLineEnd);
        strFile.append("/**").append(strLineEnd);
        strFile.append("* dtor").append(strLineEnd);
        strFile.append("*/").append(strLineEnd);
        strFile.append(strDepth1).append("virtual ~").append(strJDFName).append("(){};").append(strLineEnd);
        strFile.append("public:").append(strLineEnd);
        
        //if(iNumOfAttribs != 0)
        {
            strFile.append(strLineEnd);
            strFile.append("/**").append(strLineEnd);
            strFile.append("* typesafe validator utility").append(strLineEnd);
            strFile.append("* @param EnumValidationLevel level validation level").append(strLineEnd);
            strFile.append("* @param bool bIgnorePrivate ignore objects in foreign namespaces").append(strLineEnd);
            strFile.append("* @param int nMax size of the returned vector").append(strLineEnd);
            strFile.append("* @return vWString vector of invalid attribute names").append(strLineEnd);
            strFile.append("*/").append(strLineEnd);
            strFile.append(strDepth1).append("virtual vWString GetInvalidAttributes(EnumValidationLevel level=ValidationLevel_Complete, bool bIgnorePrivate=true, int nMax=9999999)const;").append(strLineEnd);
            strFile.append(strLineEnd);
        }
        
        if(iNumOfElements != 0)
        {
            strFile.append("/**").append(strLineEnd);
            strFile.append("* typesafe validator utility").append(strLineEnd);
            strFile.append("* @param EnumValidationLevel level validation level").append(strLineEnd);
            strFile.append("* @param bool bIgnorePrivate ignore objects in foreign namespaces").append(strLineEnd);
            strFile.append("* @param int nMax size of the returned vector").append(strLineEnd);
            strFile.append("* @return vWString vector of invalid element names").append(strLineEnd);
            strFile.append("*/").append(strLineEnd);
            strFile.append(strDepth1).append("virtual vWString GetInvalidElements(EnumValidationLevel level=ValidationLevel_Complete, bool bIgnorePrivate=true, int nMax=9999999) const;").append(strLineEnd);
            strFile.append(strLineEnd);
        }

        strFile.append("protected:").append(strLineEnd);
        strFile.append("/**").append(strLineEnd);
        strFile.append("* typesafe validator utility - list of valid node names for this class").append(strLineEnd);
        strFile.append("* @return WString& comma separated list of valid node names").append(strLineEnd);
        strFile.append("*/").append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append("virtual WString ValidNodeNames()const;").append(strLineEnd);
        strFile.append("public:").append(strLineEnd);
        

        strFile.append(strLineEnd);
        strFile.append("/**").append(strLineEnd);
        strFile.append("* Checks if the node is abstract, i.e. is not completely described").append(strLineEnd);
        strFile.append("* utility used by GetUnknownElements, GetUnknownAttributes").append(strLineEnd);
        strFile.append("* @return boolean: true, if the node is abstract").append(strLineEnd);
        strFile.append("*/").append(strLineEnd);
        strFile.append("virtual bool IsAbstract()const;").append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append(strLineEnd);
        
        
        strFile.append("/* ******************************************************").append(strLineEnd);
        strFile.append("// Attribute Getter / Setter").append(strLineEnd);
        strFile.append("****************************************************** */").append(strLineEnd);
        strFile.append(strLineEnd);
        
        if("JDFResource".equals(strExtends))
        {
        	strFile.append("/**").append(strLineEnd);
            strFile.append("* Typesafe attribute validation of Class").append(strLineEnd);
            strFile.append("* @return true if class is valid").append(strLineEnd);
            strFile.append("*/").append(strLineEnd);
            strFile.append("virtual bool ValidClass(EnumValidationLevel level) const;").append(strLineEnd);
            strFile.append(strLineEnd);
            strFile.append("/**").append(strLineEnd);
            strFile.append("* Typesafe initialization").append(strLineEnd);
            strFile.append("* @return true if succcessful").append(strLineEnd);
            strFile.append("*/").append(strLineEnd);
            strFile.append("virtual bool init();").append(strLineEnd);
            strFile.append(strLineEnd);
        }
        
        for(int i = 0; i < schemaAttr.length; i++)
        {
        	if(schemaAttr[i].getIsEnum())
            {
        		strAttributeName = schemaAttr[i].getStrAttributeName();
                
                strFile.append("/**").append(strLineEnd);
                strFile.append("* Enumeration for attribute ").append(strAttributeName).append(strLineEnd);
                strFile.append("*/").append(strLineEnd);
                strFile.append(strLineEnd);
                strFile.append(strDepth1).append("enum Enum").append(strAttributeName).append("{");
                Vector vEnumValues = schemaAttr[i].getEnumValues();
                for(int j = 0; j < vEnumValues.size(); j++)
                {
                	String strEnumValue = (String)vEnumValues.elementAt(j);
                    strFile.append(strAttributeName).append("_").append(strEnumValue);
                    if(j != vEnumValues.size() - 1)
                    {
                    	strFile.append(",");
                    }
                }
                strFile.append("};").append(strLineEnd);
            }
        }
        
        
        if(iNumOfAttribs != 0)
        {
            {//these brackest just define a logical block
                if(!JDFConstants.EMPTYSTRING.equals(myComplexType.getStrRequiredAttributes()))
                {
                    strFile.append(strLineEnd);
                    strFile.append("/**").append(strLineEnd);
                    strFile.append("* definition of required attributes in the JDF namespace").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append("virtual WString RequiredAttributes()const;").append(strLineEnd);
                }
                
                if(!JDFConstants.EMPTYSTRING.equals(myComplexType.getStrOptionalAttributes()))
                {
                    strFile.append(strLineEnd);
                    strFile.append("/**").append(strLineEnd);
                    strFile.append("* definition of optional attributes in the JDF namespace").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append("virtual WString OptionalAttributes()const;").append(strLineEnd);
                }
                strFile.append(strLineEnd);
            }
            
            for(int i = 0; i < iNumOfAttribs; i++)
            {
                strAttributeName    = schemaAttr[i].getStrAttributeName();
                strReturnType       = getStrAttributeReturnType(schemaAttr[i].getStrReturnType());
                strAttribDefault    = schemaAttr[i].getStrDefault();
                //String strType      = schemaAttr[i].getStrType();
                
                if(schemaAttr[i].getIsEnum())
                {
                    strFile.append("/**").append(strLineEnd);
                    strFile.append("* Enumeration strings for ").append(strAttributeName).append(strLineEnd);
                    strFile.append("* @return const WString& comma separated list of enumerated string values").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append(strDepth1).append("static const WString& ").append(strAttributeName).append("String();").append(strLineEnd);
                    strFile.append("/**").append(strLineEnd);
                    strFile.append("* Enumeration string for enum value").append(strLineEnd);
                    strFile.append("* @param Enum").append(strAttributeName).append(" value the enumeration to translate").append(strLineEnd);
                    strFile.append("* @return WString the string representation of the enumeration").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append(strDepth1).append("static WString ").append(strAttributeName).append("String(Enum").append(strAttributeName).append(" value);").append(strLineEnd);
                    
                    strFile.append("/**").append(strLineEnd);
                    strFile.append("* Set attribute ").append(strAttributeName).append(strLineEnd);
                    strFile.append("* @param Enum").append(strAttributeName).append(" value the value to set the attribute to").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append(strDepth1).append("virtual void Set").append(strAttributeName).append("(Enum").append(strAttributeName).append(" value);").append(strLineEnd);
                    strFile.append(strLineEnd);
                    strFile.append("/**").append(strLineEnd);
                    strFile.append("* Typesafe enumerated attribute ").append(strAttributeName);
                    if(!JDFConstants.EMPTYSTRING.equals(strAttribDefault))
                    {
                        strFile.append("; defaults to ").append(strAttribDefault).append(strLineEnd);   
                    }
                    else
                    {
                    	strFile.append(strLineEnd);
                    }
                    strFile.append("* @return Enum").append(strAttributeName).append("the enumeration value of the attribute").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append(strDepth1).append("virtual Enum").append(strAttributeName).append(" Get").append(strAttributeName).append("() const;").append(strLineEnd);
                    strFile.append(strLineEnd);
                    strFile.append("/**").append(strLineEnd);
                    strFile.append("* Typesafe attribute validation of ").append(strAttributeName).append(strLineEnd);
                    strFile.append("* @param EnumValidationLevel level element validation level ").append(strLineEnd);
                    strFile.append("* @return bool true if valid").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append(strDepth1).append("virtual bool Valid").append(strAttributeName).append("(EnumValidationLevel level=ValidationLevel_Complete) const;").append(strLineEnd);
                }
                else
                {
                    if(!isPrimitivReturnType(strReturnType))
                    {
                        strFile.append("/**").append(strLineEnd);
                        strFile.append("* Set attribute ").append(strAttributeName).append(strLineEnd);
                        strFile.append("*@param ").append(strReturnType).append(" value: the value to set the attribute to").append(strLineEnd);
                        strFile.append("*/").append(strLineEnd);
                        strFile.append(strDepth1).append("virtual void Set").append(strAttributeName).append("(const ").append(strReturnType).append("& value);").append(strLineEnd);
                        strFile.append("/**").append(strLineEnd);
                        strFile.append("* Get ").append(getStrCommentReturnType(strReturnType)).append(" attribute ").append(strAttributeName).append(strLineEnd);
                        strFile.append("* @return ").append(strReturnType).append(" the vaue of the attribute ");
                    }
                    else
                    {
                        strFile.append("/**").append(strLineEnd);
                        strFile.append("* Set attribute ").append(strAttributeName).append(strLineEnd);
                        strFile.append("*@param ").append(strReturnType).append(" value: the value to set the attribute to").append(strLineEnd);
                        strFile.append("*/").append(strLineEnd);
                        strFile.append(strDepth1).append("virtual void Set").append(strAttributeName).append("(").append(strReturnType).append(" value);").append(strLineEnd);
                        strFile.append("/**").append(strLineEnd);
                        strFile.append("* Get ").append(getStrCommentReturnType(strReturnType)).append(" attribute ").append(strAttributeName).append(strLineEnd);
                        strFile.append("* @return ").append(strReturnType).append(" the vaue of the attribute ");
                    }
                    
                    if(!JDFConstants.EMPTYSTRING.equals(strAttribDefault))
                    {
                        strFile.append("; defaults to ").append(strAttribDefault).append(strLineEnd);   
                    }
                    
                    else
                    {
                        strFile.append(strLineEnd);
                    }
                    strFile.append("*/").append(strLineEnd);
                    strFile.append(strDepth1).append("virtual ").append(strReturnType).append(" Get").append(strAttributeName).append("() const;").append(strLineEnd);
                    strFile.append("/**").append(strLineEnd);
                    strFile.append("* Typesafe attribute validation of ").append(strAttributeName).append(strLineEnd);
                    strFile.append("* @param EnumValidationLevel level of attribute validation ").append(strLineEnd);
                    strFile.append("* @return bool true if valid").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append(strDepth1).append("virtual bool Valid").append(strAttributeName).append("(EnumValidationLevel level=ValidationLevel_Complete) const;").append(strLineEnd);
                }
            }
        }
        
        strFile.append(strLineEnd);
        strFile.append("/* ******************************************************").append(strLineEnd);
        strFile.append("// Element Getter / Setter").append(strLineEnd);
        strFile.append("**************************************************************** */").append(strLineEnd);            
        
        for(int i = 0; i < iNumOfElements; i++)
        {
            strElementName    = schemaEle[i].getStrElementName();
            strReturnType       = getStrElementReturnType(schemaEle[i].getStrReturnType());
            String strMaxOccurs = schemaEle[i].getStrMaxOccurs();
        
            if(i == 0)
            {
            	strFile.append(strLineEnd); //TODO remove later, this is just for analogousness to C++ Generator (easier to compare)
            }
            if (!strElementName.endsWith("Ref"))
            {
                if(!"1".equals(strMaxOccurs) && !JDFConstants.EMPTYSTRING.equals(strMaxOccurs))
                {
                    strFile.append(strLineEnd);
                    strFile.append("/** Get Element ").append(strElementName).append(strLineEnd);
                    strFile.append("*").append(strLineEnd);
                    strFile.append("* @param int iSkip number of elements to skip").append(strLineEnd);
                    strFile.append("* @return ").append(strReturnType).append(" The element").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append(strDepth1).append(strReturnType).append(" GetCreate").append(strElementName).append("(int iSkip=0);").append(strLineEnd);
                    strFile.append(strLineEnd);
                    
                    strFile.append("/**").append(strLineEnd);
                    strFile.append("* const get element ").append(strElementName).append(strLineEnd);
                    strFile.append("* @param int iSkip number of elements to skip").append(strLineEnd);
                    strFile.append("* @return ").append(strReturnType).append(" The element").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append(strDepth1).append(strReturnType).append(" Get").append(strElementName).append("(int iSkip=0)const;").append(strLineEnd);
                    strFile.append("/**").append(strLineEnd);
                    strFile.append(" * Append element ").append(strElementName).append(strLineEnd);
                    strFile.append(" */").append(strLineEnd);
                    strFile.append(strDepth1).append(strReturnType).append(" Append").append(strElementName).append("();").append(strLineEnd);
                }
                else
                {
                    strFile.append(strLineEnd);
                    strFile.append("/** Get Element ").append(strElementName).append(strLineEnd);
                    strFile.append("*").append(strLineEnd);
                    strFile.append("* @return ").append(strReturnType).append(" The element").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append(strDepth1).append(strReturnType).append(" GetCreate").append(strElementName).append("();").append(strLineEnd);
                    strFile.append(strLineEnd);
                    
                    strFile.append("/**").append(strLineEnd);
                    strFile.append("* const get element ").append(strElementName).append(strLineEnd);
                    strFile.append("* @return ").append(strReturnType).append(" The element").append(strLineEnd);
                    strFile.append("*/").append(strLineEnd);
                    strFile.append(strDepth1).append(strReturnType).append(" Get").append(strElementName).append("()const;").append(strLineEnd);
                    strFile.append("/**").append(strLineEnd);
                    strFile.append(" * Append element ").append(strElementName).append(strLineEnd);
                    strFile.append("  *").append(strLineEnd);
                    strFile.append(" */").append(strLineEnd);
                    strFile.append(strDepth1).append(strReturnType).append(" Append").append(strElementName).append("();").append(strLineEnd);
                }
                
                //loop over all elements to check if there is a strElementName + Ref Element. 
                for (int j = 0; j < myComplexType.m_vSchemaElements.size(); j++)
                {
                    String strHelperName =  ((SchemaElement) myComplexType.m_vSchemaElements.elementAt(j)).getStrElementName();
                    if ((strElementName + "Ref").equals(strHelperName))
                    {
                        strFile.append("/**").append(strLineEnd);
                        strFile.append("* create inter-resource link to refTarget").append(strLineEnd);
                        strFile.append("* @param JDF").append(strElementName).append("& refTarget the element that is referenced").append(strLineEnd);
                        strFile.append("*@return JDFRefElement the referenced element").append(strLineEnd);
                        strFile.append("*/").append(strLineEnd);
                        strFile.append(strDepth1).append("JDFRefElement Ref").append(strElementName).append("(JDF").append(strElementName).append("& refTarget);").append(strLineEnd);
                    }
                }
            }
        }
        
        if(iNumOfElements != 0)
        {
        	strFile.append(strLineEnd);
            strFile.append("/**").append(strLineEnd);
            strFile.append(" definition of unique elements in the JDF namespace").append(strLineEnd);
            strFile.append("*/").append(strLineEnd);
            strFile.append(strDepth1).append("virtual WString UniqueElements()const;").append(strLineEnd);
                
            strFile.append(strLineEnd);
            strFile.append("/**").append(strLineEnd);
            strFile.append(" definition of optional elements in the JDF namespace").append(strLineEnd);
            strFile.append("*/").append(strLineEnd);
            strFile.append("virtual WString OptionalElements()const;").append(strLineEnd);
        }
        
        if(iNumOfElements == 0) //TODO remove later, this is just for analogousness to C++ Generator (easier to compare)
        {
            strFile.append(strLineEnd);
        }
        strFile.append("}; // end").append(strJDFName).append(strLineEnd);
        strFile.append(strLineEnd);
        strFile.append("// ******************************************************").append(strLineEnd);
        strFile.append("}; // end namespace JDF").append(strLineEnd);
        strFile.append("#endif //_").append(strJDFName).append("_H_");
        
        
        return strFile.toString();
    }
    
    public static String getStrCppCoreFile(/*SchemaComplexType nSchemaComplexType*/)
    {
//        String strAttributeName             = JDFConstants.EMPTYSTRING;
//        String strElementName               = JDFConstants.EMPTYSTRING;
//        String strReturnType                = JDFConstants.EMPTYSTRING;
//        SchemaComplexType myComplexType     = nSchemaComplexType;
//        String strExtends                   = myComplexType.getStrExtendsName();
//        String strJDFName                   = getJDFName(myComplexType);
        
        StringBuffer strFile = new StringBuffer(100000);
        strFile.append(GeneratorStringUtil.getStrAllCopyrightInformation()).append(strLineEnd);
        return strFile.toString();
    }
    
    private static String getJDFName(SchemaComplexType myCompType)
    {
        SchemaComplexType myComplexType     = myCompType;
        String strJDFName                   = JDFConstants.EMPTYSTRING;
        
        if (myComplexType.isCore)
        {
            strJDFName = "JDFAuto" + myComplexType.m_SchemaComplexTypeName;
        }
        else if (myComplexType.isMessage)
        {
            strJDFName = "JDFAutoJMF" + myComplexType.m_SchemaComplexTypeName;
        }
        else if ("Message".equals(myComplexType.m_SchemaComplexTypeName))
        {
            strJDFName = "JDFAuto" + myComplexType.m_SchemaComplexTypeName;
        }
        else if ("Query".equals(myComplexType.m_SchemaComplexTypeName))
        {
            strJDFName = "JDFAuto" + myComplexType.m_SchemaComplexTypeName;
        }
        else if ("Response".equals(myComplexType.m_SchemaComplexTypeName))
        {
            strJDFName = "JDFAuto" + myComplexType.m_SchemaComplexTypeName;
        }
        else if ("Signal".equals(myComplexType.m_SchemaComplexTypeName))
        {
            strJDFName = "JDFAuto" + myComplexType.m_SchemaComplexTypeName;
        }
        else if ("Acknowledge".equals(myComplexType.m_SchemaComplexTypeName))
        {
            strJDFName = "JDFAuto" + myComplexType.m_SchemaComplexTypeName;
        }   
        
        return strJDFName;
    }
    
    private static SchemaAttribute[] createSchemaAttributeArray(Vector a, SchemaAttribute[] b)
    {
    	for(int i = 0; i < a.size(); i++)
        {
    		b[i] = (SchemaAttribute)a.elementAt(i);
        }
        return b;
    }
   
    private static SchemaElement[] createSchemaElementArray(Vector a, SchemaElement[] b)
    {
    	for(int i = 0; i < a.size(); i++)
        {
            b[i] = (SchemaElement)a.elementAt(i);
        }
        return b;
    }
   
    private static String getStrAttributeReturnType(String s)
    {
    	String strRet = s;
        if("boolean".equals(s))
        {
        	strRet = "bool";
        }
        if("String".equals(s))
        {
            strRet = "WString";
        }
        return strRet;
    }

    private static String getStrElementReturnType(String s)
    {
        String strRet = s;
        if("JDFSpanSpineGlue".equals(s))
        {
            strRet = "JDFSpanGlue";
        }
        if("JDFSpanBackCoverColor".equals(s))
        {
        	strRet = "JDFSpanNamedColor";
        }
        return strRet;
    }
    
    private static String getStrCommentReturnType(String s)
    {
        String strRet = s;
        if("int".equals(s))
        {
            strRet = "integer";
        }
        if("String".equals(s))
        {
            strRet = "WString";
        }
        return strRet;
    }
    
    private static boolean isPrimitivReturnType(String ret)
    {
    	boolean bIsPrimitv = false;
    	if("int".equals(ret))       { bIsPrimitv = true; }
        if("bool".equals(ret))      { bIsPrimitv = true; }
        if("double".equals(ret))    { bIsPrimitv = true; }
        if("long".equals(ret))      { bIsPrimitv = true; }
    	return bIsPrimitv;
    }
    
    private static String getForwardDeclaration(SchemaComplexType ct)
    {
    	StringBuffer strImports = new StringBuffer(400);
        int iNumOfAttribs                   = ct.m_vSchemaAttributes.size();
        int iNumOfElements                  = ct.m_vSchemaElements.size();
        Vector vAttributes                  = ct.m_vSchemaAttributes;
        Vector VElements                    = ct.m_vSchemaElements;
        SchemaAttribute[] schemaAttr        = new SchemaAttribute[iNumOfAttribs];
        SchemaElement[] schemaEle           = new SchemaElement[iNumOfElements];
        HashSet set                         = new HashSet();
        createSchemaAttributeArray(vAttributes, schemaAttr);
        createSchemaElementArray(VElements, schemaEle);
        
        for(int i = 0; i < iNumOfElements; i++)
        {
        	String strReturnType = schemaEle[i].getStrReturnType();
            if(strReturnType.startsWith("JDF") && strReturnType.indexOf("Span") == -1)
            {
            	if(set.add(strReturnType))
                {
            		strImports.append("class ").append(strReturnType).append(";").append(strLineEnd);
                }
            }
        }
        if(set.size() > 0)
        {
        	strImports.append("class vResouce;").append(strLineEnd);
        }
        return strImports.toString();
    }
}
