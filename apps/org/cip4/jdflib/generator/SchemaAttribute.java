/**
 * 
 * Copyright (c) 2002 Heidelberger Druckmaschinen AG, All Rights Reserved.
 * Author: Kai Mattern Titel: SchemaElement.java Version: 0.1 Description: The
 * xml Schema is partitioned into many "complex type's" these types have
 * children named "attributes" and "elements" this file is for describing all
 * values a "element" can have.
 * 
 * History: 03-13-2002 Kai Mattern started file
 * 
 * TBD: getMinOccurs should return int
 * 
 */

package org.cip4.jdflib.generator;

import java.io.Serializable;
import java.util.Vector;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;


// ======================================================================================================
// SchemaAttribute
// ======================================================================================================
public class SchemaAttribute implements Serializable
{
    private static final long  serialVersionUID = 1L;

    transient private KElement m_KElement       = null;
    private String             strFirstVersion  = "";
    private String             strLastVersion   = "";

    private String  m_strAttributeName = "";
    private String  m_strEnumName      = "";
    private String  m_strType          = "";
    private String  m_strReturnType    = "";
    private String  m_strUse           = "optional";
    private String  m_strFixed         = "";
    private String  m_strValue         = "";
    private String  m_strDefault       = "";
    private boolean m_isEnum           = false;
    private boolean m_isEnumerations   = false;
    private Vector  m_vEnumValues      = new Vector (); // String Vector
    private boolean m_bIsEnumList      = false;
    
    public SchemaAttribute (KElement other)
    {
        setm_KElement (other);
    }

    public String toString ()
    {
        return "SchemaAttribute[ --> " + m_KElement + " ]";
    }

    public void setStrAttributeName (String s)
    {
        if ("Length".equals (s))
        {
            s = "LengthJDF";
        }

        this.m_strAttributeName = s;
    }

    public void setStrEnumName (String nAttributeName)
    {
        if ("EndStatus".equals (nAttributeName))
        {
            m_strEnumName = "Status";
        }
        else if ("NodeStatus".equals (nAttributeName)) // JDF 1.3 NodeInfo
        {
            m_strEnumName = "Status";
        }
        else
        {
            m_strEnumName = nAttributeName;
        }
    }

    public String getStrEnumName ()
    {
        return m_strEnumName;
    }

    public void setStrType (String s, VElement nSimpleType)
    {
        KElement nHelp = null;
        if (!JDFConstants.EMPTYSTRING.equals (s))
        {
            if (s.startsWith ("jdftyp:"))
            {
                m_strType = s.substring (7, s.length ());
            }
            else
            {
                m_strType = s.substring (4, s.length ());
            }

            for (int i = 0; i < nSimpleType.size (); i++)
            {
                KElement k = (KElement) nSimpleType.elementAt (i);

                String strName = k.getAttribute ("name", "", "");
                if (m_strType != null && m_strType.equals (strName))
                {
                    nHelp = k.getChildByTagName ("xs:restriction", "", 0, new JDFAttributeMap (), true, true);

                    if (nHelp == null)
                    {
                        nHelp = k.getChildByTagName ("xs:list", "", 0, new JDFAttributeMap (), true, true);
                        m_isEnumerations = true;
                        m_strType = k.getAttribute ("itemType", "", "");
                        m_strType = s.substring (s.indexOf (":") + 1, s.length ());
                    }
                    else
                    {
                        m_strType = k.getAttribute ("name", "", "");
                        if (m_strType.startsWith ("jdf:e"))
                        {
                            m_strType = s.substring (4, s.length ());
                        }
                        if (m_strType.startsWith ("jdftyp:e"))
                        {
                            m_strType = s.substring (7, s.length ());
                        }
                        // else
                        // {
                        // m_strType = s.substring(4, s.length());
                        // }
                    }

                }
            }

            if (m_strType.endsWith ("_"))
            {
                m_strType = m_strType.substring (0, m_strType.length () - 1);
            }
            else if ("boolean".equals(m_strType) || "double".equals(m_strType))
            {
                m_strType = m_strType + "_";
            }
        }
    }

    public void setStrStrType (String strType)
    {
        m_strType = strType;
    }

    public void setStrUse (String s)
    {
        m_strUse = JDFConstants.EMPTYSTRING.equals(s)
                        ? "optional"
                        : s;
    }

    public void setStrFixed (String s)
    {
        m_strFixed = s;
    }

    public void setStrValue (String s)
    {
        m_strValue = s;
    }

    public void setStrDefault (String s)
    {
        m_strDefault = s;
    }

    public void setIsEnum (boolean isEnum)
    {
        m_isEnum = isEnum;
    }

    public boolean getIsEnum ()
    {
        return m_isEnum;
    }
    
    public void setIsEnumerations (boolean isEnumerations)
    {
        m_isEnumerations = isEnumerations;
    }

    public boolean getIsEnumerations ()
    {
        return m_isEnumerations;
    }
    
    public void setVEnumValues (Vector vValues)
    {
        m_vEnumValues = vValues;
    }

    public String getStrType ()
    {
        return m_strType;
    }

    public String getStrUse ()
    {
        return m_strUse;
    }

    public String getStrFixed ()
    {
        return m_strFixed;
    }

    public String getStrValue ()
    {
        return m_strValue;
    }

    public Vector getEnumValues ()
    {
        return m_vEnumValues;
    }

    public String getStrAttributeName ()
    {
        return m_strAttributeName;
    }

    public String getStrReturnType ()
    {
        return m_strReturnType;
    }

    public void setStrReturnType (String strReturn)
    {
        m_strReturnType = strReturn;
    }

    public String getStrDefault ()
    {
        return m_strDefault;
    }

    public void setIsEnumList (boolean isList)
    {
        this.m_bIsEnumList = isList;
    }

    public boolean getIsEnumList ()
    {
        return m_bIsEnumList;
    }

    void setm_KElement (KElement kElement)
    {
        this.m_KElement = kElement;
    }

    KElement getm_KElement ()
    {
        return m_KElement;
    }

    public void setFirstVersion (String strFirst)
    {
        if (!strFirst.equals(JDFConstants.EMPTYSTRING))
           strFirstVersion = strFirst;
    }

    public String getFirstVersion ()
    {
        return strFirstVersion;
    }

    public void setLastVersion (String strLast)
    {
        if (!strLast.equals(JDFConstants.EMPTYSTRING))
           strLastVersion = strLast;
    }

    public String getLastVersion ()
    {
        return strLastVersion;
    }
}
