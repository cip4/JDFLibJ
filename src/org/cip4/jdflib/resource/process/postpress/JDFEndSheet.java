/**
 * ========================================================================== 
 * class JDFEndSheet extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process.postpress;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoEndSheet;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.w3c.dom.DOMException;


public class JDFEndSheet extends JDFAutoEndSheet
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFEndSheet
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFEndSheet(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFEndSheet
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFEndSheet(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFEndSheet
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFEndSheet(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.SIDE, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumSide.getEnum(0),null);
    }
    

    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }
    
   /**
     * set attribute Side
     * @param EnumSide value: the value to set the attribute to
     */
   public void setSide(EnumSide value)
   {
       setAttribute(AttributeName.SIDE, value.getName(),null);
   }



   /**
     * get Enumeration attribute Side
     * @return EnumSide - value of the attribute
     */
   public EnumSide getSide()
   {
       return EnumSide.getEnum(getAttribute(AttributeName.SIDE, null, null));
   }
   
   
    public String toString()
    {
        return "JDFEndSheet[  --> " + super.toString() + " ]";
    }
} // class JDFIDPLayout
// ==========================================================================
