/**
 * ========================================================================== 
 * class JDFValue extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 * Revision history:   ...
 */

package org.cip4.jdflib.resource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.devicecapability.JDFLoc;
import org.cip4.jdflib.resource.devicecapability.JDFMatrixState;
import org.cip4.jdflib.resource.devicecapability.JDFPDFPathState;
import org.cip4.jdflib.resource.devicecapability.JDFStringState;
import org.w3c.dom.Node;

public class JDFValue extends JDFElement // ignore JDFAutoValue
{
    private static final long serialVersionUID = 1L;

    
    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static 
    {
        atrInfoTable[0]  = new AtrInfoTable(AttributeName.VALUEUSAGE,   0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumValueUsage.getEnum(0), null);
    }

    private static AtrInfoTable[] atrInfoTable_matrix = new AtrInfoTable[1];
    static 
    {
        atrInfoTable_matrix[0]  = new AtrInfoTable(AttributeName.ALLOWEDVALUE, 0x22222222, AttributeInfo.EnumAttributeType.matrix, null, null);
    }

    private static AtrInfoTable[] atrInfoTable_pdfpath = new AtrInfoTable[1];
    static 
    {
        atrInfoTable_pdfpath[0]  = new AtrInfoTable(AttributeName.ALLOWEDVALUE, 0x22222222, AttributeInfo.EnumAttributeType.PDFPath, null, null);
    }

    private static AtrInfoTable[] atrInfoTable_string = new AtrInfoTable[1];
    static 
    {
        atrInfoTable_string[0]  = new AtrInfoTable(AttributeName.ALLOWEDVALUE, 0x22222222, AttributeInfo.EnumAttributeType.string, null, null);
    }

    protected AttributeInfo getTheAttributeInfo() 
    {
        AttributeInfo ai = super.getTheAttributeInfo().updateReplace(atrInfoTable);
        
        KElement parent = getParentNode_KElement();
        if (parent instanceof JDFMatrixState)
            ai.updateAdd(atrInfoTable_matrix);
        else if (parent instanceof JDFPDFPathState)
            ai.updateAdd(atrInfoTable_pdfpath);
        else if (parent instanceof JDFStringState)
            ai.updateAdd(atrInfoTable_string);
        
        return ai;

    }
    

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static 
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.LOC, 0x33333311);
    }

    protected ElementInfo getTheElementInfo() 
    {        
        ElementInfo ei = super.getTheElementInfo();
        
        Node parentNode = getParentNode();
        if (parentNode!=null && parentNode.getLocalName().endsWith("State"))
        {
            ei.updateAdd(elemInfoTable);
        }
        return ei;
    }
    

    /**
     * Constructor for JDFValue
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFValue(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFValue
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFValue(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFValue
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFValue(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFValue[  --> " + super.toString() + " ]";
    }

    /**
    * Enumeration strings for ValueUsage
    */

    public static class EnumValueUsage extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        private EnumValueUsage(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumValueUsage getEnum(String enumName)
        {
            return (EnumValueUsage) getEnum(EnumValueUsage.class, enumName);
        }

        public static EnumValueUsage getEnum(int enumValue)
        {
            return (EnumValueUsage) getEnum(EnumValueUsage.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumValueUsage.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumValueUsage.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumValueUsage.class);
        }

        public static final EnumValueUsage Present = new EnumValueUsage("Present");
        public static final EnumValueUsage Allowed = new EnumValueUsage("Allowed");
    }      



    public static EnumValueUsage stringToValueUsage(String enumName)
    {
        return EnumValueUsage.getEnum(enumName);
     }

/**
      * (14) set attribute AllowedValue
      * @param String value: the value to set the attribute to
      */
    public void setAllowedValue(String value)
    {
        setAttribute(AttributeName.ALLOWEDVALUE, value, null);
    }



    /**
      * (23) get String attribute AllowedValue
      * @return String the value of the attribute
      */
    public String getAllowedValue()
    {
        return getAttribute(AttributeName.ALLOWEDVALUE, null, "");
    }


    /**
      * (14) set attribute PresentValue
      * @param String value: the value to set the attribute to
      */
    public void setPresentValue(String value)
    {
        setAttribute(AttributeName.PRESENTVALUE, value, null);
    }



    /**
      * (23) get String attribute PresentValue
      * @return String the value of the attribute
      */
    public String getPresentValue()
    {
        return getAttribute(AttributeName.PRESENTVALUE, null, "");
    }



    
    /* ---------------------------------------------------------------------
    Methods for Attribute ValueUsage
    --------------------------------------------------------------------- */
    ////////////////////////////////////////////////////////////////////////
 
    /**
      * (5) set attribute ValueUsage
      * @param EnumValueUsage enumVar: the enumVar to set the attribute to
      */
    public void setValueUsage(EnumValueUsage enumVar)
    {
        setAttribute(AttributeName.VALUEUSAGE, enumVar.getName(), null);
    }



    /**
      * (9) get ValueUsage attribute ValueUsage
      * @return EnumValueUsage the value of the attribute
      */
    public EnumValueUsage getValueUsage()
    {
        return  EnumValueUsage.getEnum(getAttribute(AttributeName.VALUEUSAGE, null, null));
    }

    /**
      * (14) set attribute Value
      * @param String value: the value to set the attribute to
      */
    public void setValue(String value)
    {
        setAttribute(AttributeName.VALUE, value, null);
    }



    /**
      * (23) get String attribute Value
      * @return String the value of the attribute
      */
    public String getValue()
    {
        return getAttribute(AttributeName.VALUE, null, null);
    }



/* ************************************************************************
// Element getter / setter

************************************************************************** */

    /** (26) getCreateLoc
     * 
     * @param int iSkip number of elements to skip
     * @return JDFLoc the element
     */
    public JDFLoc getCreateLoc(int iSkip)
    {
        return (JDFLoc)getCreateElement_KElement("Loc", null, iSkip);
    }



    /**
     * (27) const get element Loc
     * @param int iSkip number of elements to skip
     * @return JDFLoc the element
     * default is getLoc(0)     
     */
    public JDFLoc getLoc(int iSkip)
    {
        return (JDFLoc) getElement("Loc", null, iSkip);
    }



    /**
     * (28) get vector of all direct child elements Loc
     * @param JDFAttributeMap mAttrib the map of attributes to select
     * @param boolean bAnd if true all attributes in the map are AND'ed, else they are OR'ed
     * @deprecated use getChildElementVector() instead
     */
    public VElement getLocVector(JDFAttributeMap mAttrib, boolean bAnd)
    {
        VElement myResource = new VElement();
        Vector vElem = getChildElementVector("Loc", null, mAttrib, bAnd, 0, true);
        for(int i = 0; i < vElem.size(); i++)
        {
            JDFElement k = (JDFElement) vElem.elementAt(i);
            JDFLoc myJDFLoc = (JDFLoc)k;
            myResource.addElement(myJDFLoc);
        }
        return myResource;
    }



    /**
     * @deprecated use getChildElementVector() instead
     */
    public VElement getLocVector()
    {
        return getLocVector(new JDFAttributeMap(), true);
    }



    public JDFLoc appendLoc()
    {
        return (JDFLoc)appendElement(ElementName.LOC, null);
     }

}// end namespace JDF


