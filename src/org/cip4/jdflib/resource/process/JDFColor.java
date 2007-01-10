/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFColor.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoColor;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.util.StringUtil;


public class JDFColor extends JDFAutoColor
{
    private static final long serialVersionUID = 1L;


    /**
     * Constructor for JDFColor
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFColor(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFColor
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFColor(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFColor
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFColor(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFColor[  --> " + super.toString() + " ]";
    }
    

    /** 
     * Set the Name and RawName attributes to the value given in pName
     * The value in Name uses the default encoding
     * @param char[] cName the 8 bit string to set the name to
     */
    public void set8BitNames(byte[] cName)
    {
        String rawName = JDFConstants.EMPTYSTRING;
        rawName = StringUtil.setHexBinaryBytes(cName, -1);
        setAttribute("RawName", rawName, JDFConstants.EMPTYSTRING);
        setName(new String(cName));
    }



    /**
     * Gets the 16 bit representation of the 8 bit color name
     * Use String GetRawBytes() to extract the 8 bit representation
     * @return String Name of the color extracted from RawName, 
     *         or if this is missing from Name, using the default transcoder
     */
    public String get8BitName() 
    {
        String strName = JDFConstants.EMPTYSTRING;
        if (hasAttribute("RawName", JDFConstants.EMPTYSTRING, false))
        {
            strName = getAttribute("RawName", JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING);
            byte[] rawName   = strName.getBytes();
            byte[] foundName = StringUtil.getHexBinaryBytes(rawName);
            
            return new String(foundName);
        }
        return getName();
    }
    
    
    public JDFFileSpec getColorProfile()
    {
        VElement v = getChildElementVector(ElementName.FILESPEC,
                                           null,
                                           null, 
                                           true, 
                                           0, 
                                           false);
        int siz    = v.size();
        for(int i = 0; i < siz; i++)
        {
            JDFFileSpec res = (JDFFileSpec)v.elementAt(i);
            if (res.hasAttribute(AttributeName.RESOURCEUSAGE))
            {
                if (res.getResourceUsage().equals("ColorProfile"))
                {
                    return res;
                }   
            }
        }
        return null;
    }

    ///////////////////////////////////////////////////////////////////
    
    public JDFFileSpec getCreateColorProfile() 
    {
        JDFFileSpec res = getColorProfile();
        if (res == null) 
        {
            res = appendColorProfile();
        }
        return res;
    }

    ///////////////////////////////////////////////////////////////////
    
    public JDFFileSpec appendColorProfile() 
    {
        JDFFileSpec res = appendFileSpec();
        res.setResourceUsage("ColorProfile");

        return res;
    }
    
    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    public JDFFileSpec getTargetProfile()
    {
        VElement v = getChildElementVector(ElementName.FILESPEC,
                                           null, 
                                           null, 
                                           true, 
                                           0, 
                                           false);
        
        int siz = v.size();
        for(int i = 0; i < siz; i++)
        {
            JDFFileSpec res = (JDFFileSpec)v.elementAt(i);
            if (res.hasAttribute(AttributeName.RESOURCEUSAGE))
            {
                if (res.getResourceUsage().equals("TargetProfile"))
                {
                    return res;
                }   
            }
        }
        return null;
    }

    ///////////////////////////////////////////////////////////////////
    
    public JDFFileSpec getCreateTargetProfile() 
    {
        JDFFileSpec res = getTargetProfile();
        if (res == null) 
        {
            res = appendTargetProfile();
        }
        return res;
    }

    ///////////////////////////////////////////////////////////////////
    
    JDFFileSpec appendTargetProfile()
    {
        JDFFileSpec res = appendFileSpec();
        res.setResourceUsage("TargetProfile");

        return res;
    }

}