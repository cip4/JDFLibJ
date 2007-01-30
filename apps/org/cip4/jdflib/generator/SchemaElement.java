/**
 *
 *  Copyright (c)   2002 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *  Author:         Kai Mattern
 *  Titel:          SchemaElement.java
 *  Version:        0.1
 *  Description:    The xml Schema is partitioned into many "complex type's" 
 *                  these types have children named "attributes" and "elements"
 *                  this file is for describing all values a "element" can have.
 *
 *  History:        03-13-2002  Kai Mattern started file
 *
 *  TBD:            getMinOccurs should return  int
 *
 */

package org.cip4.jdflib.generator;

import java.io.Serializable;
import java.util.Vector;

import org.cip4.jdflib.core.KElement;
import org.w3c.dom.Element;


//======================================================================================================
//     SchemaElement
//=======================================================================================================
public class SchemaElement implements Serializable
{
    private static final long serialVersionUID = 1L;

    public SchemaElement(KElement other)
    {
        setm_schemaKElem(other);
        this.init();
    }

    public SchemaElement(Element other)
    {
        setm_schemaKElem((KElement) other);
        this.init();
        
    }
    
    transient private KElement m_schemaKElem = null;
    

    //private values of that might be set in an element
    private String  m_elementName        = "";
    private String  m_minOccurs          = "";
    private String  m_maxOccurs          = "";
    private String  m_usageString        = "";
    private String  m_returnType         = "";
    private String  m_elementType        = "";
    private Vector  m_vProcessUsage      = new Vector();
    private String  m_processUsageString = "Unknown,AnyInput,AnyOutput,Any,";
    
    private boolean m_isOptionalElement  = true; // if it's false, element is required
    private boolean m_isEnumerationSpan  = false;
    private String m_firstVersion        = "";
    private String m_lastVersion         = "";
    
    
    private void init()
    {
        this.m_vProcessUsage.add("Unknown");
        this.m_vProcessUsage.add("AnyInput");
        this.m_vProcessUsage.add("AnyOutput");
        this.m_vProcessUsage.add("Any");
    }

    public String toString()
    {
        return "SchemaElement[ --> " + m_schemaKElem + " ]";
    }


    /**
     *  Every element inside the schema has a unique Name.
     *  the Schema is reflected in a couple of vectors. One vector holds all elements from as given
     *  complex Type. To set the name of this specific element, use this method
     *
     *  @param String ElementName - the name of the element
     *  @return void - nothing
     */
    public void setStrElementName(String elementName)
    {
        if(elementName.startsWith("jdf:"))
        {
            m_elementName = elementName.substring("jdf:".length()); 
        }
        else if ("CreateLink".equals(elementName) || "RemoveLink".equals(elementName))
        {
            m_elementName = elementName;
        }
        else if(elementName.endsWith("Link"))
        {
            m_elementName = elementName.substring(0, elementName.length() - "Link".length());
        }
        else
        {
            m_elementName = elementName;
        }
    }


    /**
     *  Every element is of a specific Type. To set the type, use this method
     *
     *  @param String ElementType - the Type of the element
     *
     *  @return void - nothing
     */
    public void setStrElementType(String elementType)
    {
        if(elementType.startsWith("jdf:"))
        {
            m_elementType = elementType.substring(4, elementType.length());
        }
        else
        {
            m_elementType = elementType;
        }
    }

    /**
     *  The Schema and the Spec of the JDF regulates, when where who whether and in this case, how often a element
     *  can occur. Normaly its only important to know if the occurance of the element is unequally 0. This means the
     *  Element is required. Otherwise the element is optional. To set the minoccurance, use this method.
     *
     *  @param String minOccurs - the minimum occurance of the element
     *
     *  @return void - nothing
     */
    public void setStrMinOccurs(String minOccurs)
    {
        m_minOccurs = minOccurs;
    }

    /**
     *  To get the element name, call this method.
     *
     *  @return String - Name of the element or an empty string
     */
    public String getStrElementName()
    {
        return m_elementName;
    }

    /**
     * To get the element type, call this method
     *
     * @return String - the type of the element
     */
    public String getStrElementType()
    {
        return m_elementType;
    }


    /**
     *  To get the minoccurance of an element, call this method
     *  NOTE: deprecated (this method will soon return int).
     *
     * @return String - the minoccurance of the element
     */
    public String getStrMinOccurs()
    {
//        return Integer.parseInt(m_strMinOccurs, 10); //intvalue of String representativ;
        return m_minOccurs;
    }

    public boolean getIsOptionalElement()
    {
        return m_isOptionalElement;
    }

    public String getStrUsageString()
    {
        return m_usageString;
    }

    public void setStrUsageString(String nStrUsageString)
    {
        m_usageString = nStrUsageString;
    }

    public void setStrReturnType(String nStrReturnType)
    {
        m_returnType = nStrReturnType;
    }

    public String getStrReturnType()
    {
        return m_returnType;
    }
    
    public void setIsOptionalElement()
    {
        if("0".equals(m_minOccurs))
        {
            m_isOptionalElement = true;
        }
        
        else
        {
            m_isOptionalElement = false;
        }
    }
    
    public void setIsEnumerationSpan(boolean isTrue)
    {
        m_isEnumerationSpan = isTrue;
    }

    public boolean getIsEnumerationSpan()
    {
        return m_isEnumerationSpan;
    }
    
    public void setStrMaxOccurs(String maxOccurs)
    {
        m_maxOccurs = maxOccurs;        
    }
    
    public String getStrMaxOccurs()
    {
        return m_maxOccurs;
    }
    
    public void appendProcessUsageToVector(String usage)
    {
        this.m_vProcessUsage.add(usage);   
    }
    
    public void appendProcessUsageToString(String usage)
    {
        m_processUsageString += usage;
    }
    
    public Vector getUsageVector()
    {
        return this.m_vProcessUsage;
    }
    
    public String getStrProcessUsageString()
    {
        return this.m_processUsageString;
    }
    
    public Vector getVecProcessUsageVector()
    {
        return this.m_vProcessUsage;
    }

    protected void setm_schemaKElem(KElement schemaKElem)
    {
        this.m_schemaKElem = schemaKElem;
    }

    protected KElement getm_schemaKElem()
    {
        return m_schemaKElem;
    }
    
    public void setFirstVersion(String strFirst)
    {
//        if (!JDFConstants.EMPTYSTRING.equals(strFirst))
            m_firstVersion = strFirst;
    }

    public String getFirstVersion()
    {
        return m_firstVersion;
    }

    public void setLastVersion(String strLast)
    {
//        if (!JDFConstants.EMPTYSTRING.equals(strLast))
            m_lastVersion = strLast;
    }
    
    public String getLastVersion()
    {
        return m_lastVersion;
    }
}