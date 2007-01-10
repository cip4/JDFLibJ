/**
 * ========================================================================== 
 * class JDFNotification extends JDFAutoNotification
 * created 2001-09-06T10:02:57GMT+02:00 
 * ==========================================================================
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 * Revision history:   ...
 */

package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoNotification;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;


public class JDFNotification extends JDFAutoNotification
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFNotification
     * @param ownerDocument
     * @param qualifiedName
     */
     public JDFNotification(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFNotification
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFNotification(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFNotification
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFNotification(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    public String toString()
    {
        return "JDFNotification[  --> " + super.toString() + " ]";
    }
    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[7];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.BARCODE, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.FCNKEY, 0x33333333);
        elemInfoTable[2] = new ElemInfoTable(ElementName.SYSTEMTIMESET, 0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.COUNTERRESET, 0x33333333);
        elemInfoTable[4] = new ElemInfoTable(ElementName.ERROR, 0x33333333);
        elemInfoTable[5] = new ElemInfoTable(ElementName.EVENT, 0x33333333);
        elemInfoTable[6] = new ElemInfoTable(ElementName.MILESTONE, 0x33333333);
        elemInfoTable[6] = new ElemInfoTable(ElementName.PART, 0x33333333);
    }
    

    protected ElementInfo getTheElementInfo()
    {
        return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
    }


    /**
     * get part map vector
     * @return VJDFAttributeMap: vector of mAttribute, one for each part
     */
    public VJDFAttributeMap getPartMapVector()
    {
        return super.getPartMapVector();
    }

    /**
     * set all parts to those define in vParts
     * @param VJDFAttributeMap vParts: vector of attribute maps for the parts
     */
    public void setPartMapVector(VJDFAttributeMap vParts)
    {
        super.setPartMapVector(vParts);
    }

    /**
     * set all parts to those define in vParts
     * @param JDFAttributeMap mPart: attribute map for the part to set
     */
    public void setPartMap(JDFAttributeMap mPart)
    {
        super.setPartMap(mPart);
    }

    /**
     * remove the part defined in mPart
     * @param JDFAttributeMap mPart: attribute map for the part to remove
     */
    public void removePartMap(JDFAttributeMap mPart)
    {
        super.removePartMap(mPart);
    }

    /**
     * check whether the part defined in mPart is included
     * @param JDFAttributeMap mPart: attribute map for the part to remove
     * @return boolean - returns true if the part exists
     */
    public boolean hasPartMap(JDFAttributeMap mPart)
    {
        return super.hasPartMap(mPart);
    }   
   
   public JDFBarcode getCreateBarcode()
   {
       return  (JDFBarcode)getCreateElement_KElement(ElementName.BARCODE, null, 0);
   }

   public JDFBarcode appendBarcode()
   {
       return (JDFBarcode)appendElementN(ElementName.BARCODE, 1, null);
   }
   
   public JDFBarcode getBarcode()
   {
       return (JDFBarcode) getElement(ElementName.BARCODE, null, 0);
   }
   
   
   
   public JDFFCNKey getCreateFCNKey()
   {
       return (JDFFCNKey)getCreateElement_KElement(ElementName.FCNKEY, null, 0);
    }

   public JDFFCNKey appendFCNKey()
   {
       return (JDFFCNKey)appendElementN(ElementName.FCNKEY, 1, null);
   }
   
   public JDFFCNKey getFCNKey()
   {
       return (JDFFCNKey) getElement(ElementName.FCNKEY, null, 0);
   }
   
   
   public JDFSystemTimeSet getCreateSystemTimeSet()
   {
       return (JDFSystemTimeSet)getCreateElement_KElement(ElementName.SYSTEMTIMESET, null, 0);
   }

   public JDFSystemTimeSet appendSystemTimeSet()
   {
       return (JDFSystemTimeSet)appendElementN(ElementName.SYSTEMTIMESET, 1, null);
   }
   
   public JDFSystemTimeSet getSystemTimeSet()
   {
       return (JDFSystemTimeSet) getElement(ElementName.SYSTEMTIMESET, null, 0);
   }
   
   
   public JDFCounterReset getCreateCounterReset()
   {
       return (JDFCounterReset)getCreateElement_KElement(ElementName.COUNTERRESET, null, 0);
   }

   public JDFCounterReset appendCounterReset()
   {
       return (JDFCounterReset)appendElementN(ElementName.COUNTERRESET, 1, null);
   }
   
   public JDFCounterReset getCounterReset()
   {
       return (JDFCounterReset) getElement(ElementName.COUNTERRESET, null, 0);
   }
   
   
   public JDFError getCreateError()
   {
       return (JDFError)getCreateElement_KElement(ElementName.ERROR, null, 0);
   }

   public JDFError appendError()
   {
       return (JDFError)appendElementN(ElementName.ERROR, 1, null);
   }
   
   public JDFError getError()
   {
       return (JDFError) getElement(ElementName.ERROR, null, 0);
   }
   
   
   public JDFEvent getCreateEvent()
   {
       return (JDFEvent)getCreateElement_KElement(ElementName.EVENT, null, 0);
   }

   public JDFEvent appendEvent()
   {
       return (JDFEvent)appendElementN(ElementName.EVENT, 1, null);
   }
   
   public JDFEvent getEvent()
   {
       return (JDFEvent) getElement(ElementName.EVENT, null, 0);
   }
   
} // class JDFNotification
// ==========================================================================
