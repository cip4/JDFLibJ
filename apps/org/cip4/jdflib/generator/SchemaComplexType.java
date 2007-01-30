/**
 *
 *  Copyright (c)   2002 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *  Author:         Kai Mattern
 *  Titel:          SchemaComplexType.java
 *  Version:        0.1
 *  Description:
 *
 *  History:        03-13-2002  Kai Mattern started file
 *
 *
 */

package org.cip4.jdflib.generator;

import java.io.Serializable;
import java.util.Vector;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;


//======================================================================================================
//     SchemaComplexType
//======================================================================================================
public class SchemaComplexType implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public SchemaComplexType(KElement other)
    {
        m_kElem = other;
    }

    public String toString()
    {
        return "SchemaComplexType[ --> " + m_kElem + " ]";
    }

    transient protected KElement m_kElem = null;
    
    //global vectors with all elements and attributes of the spezific complex type
    public Vector m_vSchemaElements         = new Vector();
    public Vector m_vSchemaAttributes       = new Vector();

    //every complex type extends of an other complex or simple type
    public String m_ExtendOff               = "";
    //name of the complex type
    public String m_SchemaComplexTypeName   = "";

    //THIS VARIABLE IS VERY IMPORTANT 
    //many methods require the information if they need to generate java or C++ Code
    //A SchemaComplexType can be a Java or a C++ one. Yoh can set it here.
    //Remember you can use the complexType for C++ or Java only by switching this variable
    //BUT you need to regenerate the information with getSchemaInfo!!
    //true if Java is the code you desire
    public boolean isJava                   = false;

    //source vaildation
    public boolean isMessage                = false;
    public boolean isCore                   = false;
    public boolean isNode                   = false;
    public boolean hasMessage               = false; //TBD isMessage need to be eliminated, its no longer needed.
    //All Mesages are now core. But i need a boolean for attribute handling (in GeneratorUtil.java). When all occurences of
    //isMessage are eliminated this boolean can be removed.


    //type validation
    public boolean isResource               = true;
    public boolean isResourceElement        = false;
    public boolean isPartitionalResource    = false;
    public boolean isPool                   = false;
    public boolean isElementOnly            = false;
    public boolean isAudit                  = false;
    
    private String strFileName                  = "";

    //Java related FileNames
    private String strAutoJavaCoreFileName      = "";
    private String strAutoJavaNodeFileName      = "";
    private String strAutoJavaMessageFileName   = "";

    //C++ related FileNames (the .cpp ones)
    private String strAutoCppCoreFileNameCPP    = "";
    private String strAutoCppNodeFileNameCPP    = "";
    private String strAutoCppMessageFileNameCPP = "";

    //C++ related FileNames (the .h ones)
    private String strAutoCppCoreFileNameH      = "";
    private String strAutoCppNodeFileNameH      = "";
    private String strAutoCppMessageFileNameH   = "";

    private String strRequiredElements      = "";
    //is also filled in setStrRequiredElements
    private String strOptionalElements      = "";
    private String strRequiredAttributes    = "";
    private String strOptionalAttributes    = "";
    //is also filled in setStrRequiredElements
    private String m_strUniqueElements       = "";

    private String strExtendsName           = "JDFResource"; //JDFResource by default
    private String strIdentifier            = "";
    private String strBase                  = "jdf:Parameter";             

    //used by Nodes only
    private String strNodeLinkInfo          = "";
    private String strNodeUsageString       = "";
    private int    iNodeLinkLength          = 0;

    //only used by Message
    public boolean isAcknowledge            = false;
    public boolean isCommand                = false;
    public boolean isResponse               = false;
    public boolean isSignal                 = false;
    public boolean isQuery                  = false;

    //used by node only (refer to GeneratorUtil)
    public String strNodeUsage              = "";
    
    //used to determin a Sequence min and max accource
    public String strSequenceMin = "";
    public String strSequenceMax = "";    
    public String strMotherOfComplexType = "";
    public String strVersionInfoPath = "";
    
    /**
     *  Kind of an initializor for every complexType
     *
     * @param
     *
     * @return
     */
    public void setStrSchemaComplexType()
    {
        setStrFileName();
        setStrAutoJavaCoreFileName();
        setStrAutoJavaNodeFileName();
        setStrAutoJavaMessageFileName();

        setStrAutoCppCoreFileNameCPP();
        setStrAutoCppNodeFileNameCPP();
        setStrAutoCppMessageFileNameCPP();

        setStrAutoCppCoreFileNameH();
        setStrAutoCppNodeFileNameH();
        setStrAutoCppMessageFileNameH();

        setStrRequiredElements();
        setStrRequiredAttributes();
        setStrExtendsName();
    }

    /**
     *
     * @param
     *
     * @return
     */
    public String getStrAutoCoreFileName()
    {
        return strAutoJavaCoreFileName;
    }

    public String getStrMessageCoreFileName()
    {
        return strAutoJavaMessageFileName;
    }

    /**
     *
     * @param
     *
     * @return
     */
    public String getStrFileName()
    {
        return strFileName;
    }

    public void setStrAutoCppCoreFileNameCPP()
    {
        strAutoCppCoreFileNameCPP = "JDFAuto" + m_SchemaComplexTypeName + ".cpp";
    }

    public String getStrAutoCppCoreFileNameCPP()
    {
        return strAutoCppCoreFileNameCPP;
    }

    public void setStrAutoCppNodeFileNameCPP()
    {
        strAutoCppNodeFileNameCPP = "JDFAutoNode" + m_SchemaComplexTypeName + ".cpp";
    }

    public String getStrAutoCppNodeFileNameCPP()
    {
        return strAutoCppNodeFileNameCPP;
    }

    public void setStrAutoCppMessageFileNameCPP()
    {
        strAutoCppMessageFileNameCPP = "JDFAutoJMF" + strIdentifier + m_SchemaComplexTypeName + ".cpp";
    }

    public String getStrAutoCppMessageFileNameCPP()
    {
        return strAutoCppMessageFileNameCPP;
    }

    public void setStrAutoCppCoreFileNameH()
    {
        strAutoCppCoreFileNameH = "JDFAuto" + m_SchemaComplexTypeName + ".h";
    }

    public String getStrAutoCppCoreFileNameH()
    {
        return strAutoCppCoreFileNameH;
    }

    public void setStrAutoCppNodeFileNameH()
    {
        strAutoCppNodeFileNameH = "JDFAutoNode" + m_SchemaComplexTypeName + ".h";
    }

    public String getStrAutoCppNodeFileNameH()
    {
        return strAutoCppNodeFileNameH;
    }

    public void setStrAutoCppMessageFileNameH()
    {
        strAutoCppMessageFileNameH = "JDFAutoJMF" + strIdentifier + m_SchemaComplexTypeName + ".h";
    }

    public String getStrAutoCppMessageFileNameH()
    {
        return strAutoCppMessageFileNameH;
    }

    /**
     * private
     *
     * @param
     *
     * @retun
     */
    private void setStrFileName()
    {
        strFileName = "JDF" + m_SchemaComplexTypeName + ".java";
    }

    /**
     * private method to set the AutoFileName when the SchemaComplexTypeName ist set
     *
     * @param void - nothing
     *
     * @return void - nothing
     */
    private void setStrAutoJavaCoreFileName()
    {
        strAutoJavaCoreFileName = "JDFAuto" + m_SchemaComplexTypeName + ".java";
    }

    private void setStrAutoJavaNodeFileName()
    {
        strAutoJavaNodeFileName = "JDFAutoNode" + m_SchemaComplexTypeName + ".java";
    }

    public String getStrAutoJavaNodeFileName()
    {
        return strAutoJavaNodeFileName;
    }


    private void setStrAutoJavaMessageFileName()
    {
        strAutoJavaMessageFileName = "JDFAutoJMF" + strIdentifier + m_SchemaComplexTypeName + ".java";
    }

    public String getStrAutoJavaMessageFileName()
    {
        return strAutoJavaMessageFileName;
    }
    
    /**
     *
     */
    private void setStrExtendsName()
    {
        if (isResource && !isPool)
        {
            strExtendsName = "JDFResource";
        }

        if (!isResource && isResourceElement)
        {
            strExtendsName = "JDFElement";
        }
        
        if (m_SchemaComplexTypeName.endsWith("Intent") && !"DropIntent".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFIntentResource";
        }

        if ("PRGroupOccurrenceBase".equals(m_ExtendOff))
        {
            strExtendsName = "JDFElement";
        } 
        else if ("QueryTypeObj".equals(m_ExtendOff))
        {
            strExtendsName = "JDFElement";
        }
        else if ("CommandTypeObj".equals(m_ExtendOff))
        {
            strExtendsName = "JDFElement";
        }
        else if ("CommandOrQueryTypeObj".equals(m_ExtendOff))
        {
            strExtendsName = "JDFElement";
        }
        else if ("ResponseTypeObj".equals(m_ExtendOff))
        {
            strExtendsName = "JDFElement";
        }
        else if ("BaseElement".equals(m_ExtendOff))
        {
            strExtendsName = "JDFElement";
        }
        else if ("ResourceElement".equals(m_ExtendOff))
        {
            strExtendsName = "JDFElement";
        }
        else if ("Message".equals(m_ExtendOff))
        {
            strExtendsName = "JDFMessage";
        }

        if ("Part".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFElement";
            isPool = true;
        }

        if ("MarkObject".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFElement";
        }
        if ("ContentObject".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFElement";
        }

        if ("Comment".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFElement";
        }

        if ("AncestorPool".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFPool";
            isElementOnly = true;
        }

        if ("StatusPool".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFPool";
            isPool = true;
        }

        if (isAudit)
        {
            strExtendsName = "JDFAudit";
        }

        // isPartitionalResource
        if (isElementOnly && !("AncestorPool".equals(m_SchemaComplexTypeName)))
        {
            strExtendsName = "JDFElement";
        }

        if (isAcknowledge)
        {
            setStrIdentifier("A");
            strExtendsName = "JDFAcknowledge";
        }
        if (isCommand)
        {
            setStrIdentifier("C");
            strExtendsName = "JDFCommand";
        }
        if (isResponse)
        {
            setStrIdentifier("R");
            strExtendsName = "JDFResponse";
        }

        if (isSignal)
        {
            setStrIdentifier("S");
            strExtendsName = "JDFSignal";
        }

        if (isQuery)
        {
            setStrIdentifier("Q");
            strExtendsName = "JDFQuery";
        }

        if ("Spawned".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFAudit";
        }

        if ("ResourceAudit".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFAudit";
        }

        if ("PhaseTime".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFAudit";
        }

        if ("ProcessRun".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFAudit";
        }

        if ("Notification".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFAudit";
        }

        if ("JMF".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFPool";
        }

        if ("AmountPool".equals(m_SchemaComplexTypeName))
        {
            strExtendsName = "JDFPool";
        }

        if (isNode)
        {

            if (m_SchemaComplexTypeName.endsWith("Product"))
            {
                strExtendsName = "JDFNodeProcessGroup";
            }
            else
            {
                strExtendsName = "JDFNode";
            }
        }
    }

    public String getStrRequiredElements()
    {
        return strRequiredElements;
    }

    public String getStrUniqueElements()
    {
        return m_strUniqueElements;
    }

    public String getStrOptionalElements()
    {
        return strOptionalElements;
    }

    private void setStrRequiredElements()
    {
        for(int i = 0; i < m_vSchemaElements.size(); i++)
        {
            SchemaElement nSchemaElement = ((SchemaElement)m_vSchemaElements.elementAt(i));
            if(nSchemaElement.getIsOptionalElement() || JDFConstants.EMPTYSTRING.equals(nSchemaElement.getStrMaxOccurs()))
            {
                String nHelperString = nSchemaElement.getStrElementName();
                if(!nHelperString.endsWith("Ref"))
                {
                    strOptionalElements += nHelperString;
                    if(i != m_vSchemaElements.size() - 1)
                    {
                       strOptionalElements += ",";
                    }
                }
            }
            else
            {
                String nHelperString = nSchemaElement.getStrElementName();
                if(!nHelperString.endsWith("Ref"))
                {
                    strRequiredElements += nHelperString;
                    if(i != m_vSchemaElements.size() - 1)
                    {
                        strRequiredElements += ",";
                    }
                }
            }
            if("1".equals(nSchemaElement.getStrMaxOccurs()))
            {
                String nHelperString = nSchemaElement.getStrElementName();
                if(!nHelperString.endsWith("Ref"))
                {
                    m_strUniqueElements += nHelperString;
                    if(i != m_vSchemaElements.size() - 1)
                    {
                        m_strUniqueElements += ",";
                    }
                }
            }
        }

    }

    private void setStrRequiredAttributes() 
    {
        for(int i = 0; i < m_vSchemaAttributes.size(); i++)
        {
            
            SchemaAttribute nSchemaAttribute = ((SchemaAttribute)m_vSchemaAttributes.elementAt(i));
            if ("optional".equals(nSchemaAttribute.getStrUse()) 
                || !JDFConstants.EMPTYSTRING.equals(nSchemaAttribute.getStrDefault()) 
                || JDFConstants.EMPTYSTRING.equals(nSchemaAttribute.getStrUse()))
            {
                strOptionalAttributes += nSchemaAttribute.getStrAttributeName();
                if(i != m_vSchemaAttributes.size() -1)
                {
                    strOptionalAttributes += ",";
                }
            }
            else
            {
                strRequiredAttributes += nSchemaAttribute.getStrAttributeName();
                if(i != m_vSchemaAttributes.size() -1)
                {
                    strRequiredAttributes += ",";
                }
            }
        }
    }
    public String getStrRequiredAttributes()
    {
        return strRequiredAttributes;
    }

    public String getStrOptionalAttributes()
    {
        return strOptionalAttributes;
    }

    public String getStrExtendsName()
    {
        return strExtendsName;
    }

    public void setStrIdentifier(String s)
    {
        strIdentifier = s;
    }

    public String getStrIdentifier()
    {
        return strIdentifier;
    }
    public String getStrNodeLinkInfo()
    {
        return strNodeLinkInfo;
    }

    public void setStrNodeLinkInfo(String strLinkInfo)
    {
        strNodeLinkInfo = strLinkInfo;
    }

    public void setStrNodeUsageString(String strUsageString)
    {
        strNodeUsageString = strUsageString;
    }

    public String getStrNodeUsageString()
    {
        return strNodeUsageString;
    }

    public int getNodeLinkLength()
    {
        return iNodeLinkLength;
    }

    public void setNodeLinkLength(int i)
    {
        iNodeLinkLength = i;
    }
    
    public void getAllDataFromComplexType(SchemaComplexType myComplex)
    {
        setStrIdentifier(myComplex.getStrIdentifier());
        setStrNodeLinkInfo(myComplex.getStrNodeLinkInfo());
        setStrNodeUsageString(myComplex.getStrNodeUsageString());
        setNodeLinkLength(myComplex.getNodeLinkLength());
    }
    
    public String getStrMotherOfComplexType()
    {
        return strMotherOfComplexType;
    }
    
    public void setStrMotherOfComplexType(String name)
    {
        strMotherOfComplexType = name;
    }
    
    public String getBase()
    {
        return strBase;
    }
    
    public void setBase(String b)
    {
        strBase = b;
    }
}