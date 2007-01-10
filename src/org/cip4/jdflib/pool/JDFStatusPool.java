/**
 *
 * Copyright (c) 2001-2003 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * String myLocalName)
 *
 * Last changes
 *
 */
package org.cip4.jdflib.pool;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoStatusPool;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFPartStatus;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;

/**
 * This class represents a JDF-Status Pool. THe status pool discribes ths status of a JDF node
 * that processes partitioned resources. StatusPool elements are only valid if the nodes
 * Status="Pool", otherwise the nodes status is valid for all parts, regardless of the contents
 * of StatusPool.
 */
public class JDFStatusPool extends JDFAutoStatusPool
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFStatusPool
     * @param ownerDocument
     * @param qualifiedName
     */
    public JDFStatusPool(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFStatusPool
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFStatusPool(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFStatusPool
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFStatusPool(CoreDocumentImpl myOwnerDocument, 
                         String myNamespaceURI, 
                         String qualifiedName,
                         String myLocalName) 
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.PARTSTATUS, 0x44444222);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFStatusPool[ -->" + super.toString() + "]";
    }

    /**
     * getElementStatus - get the status of a part defined in StatusPool
     *
     * @param JDFAttributeMap mPart - filter for the part to get the status
     *
     * @return EnumNodeStatus - the status for the filter defined in mPart
     */
    public JDFElement.EnumNodeStatus getStatus(JDFAttributeMap mPart)
    {
        final JDFNode n = getParentJDF();

        if (!(n.getStatus().equals(JDFElement.EnumNodeStatus.Pool)))
        {
            return n.getStatus();
        }

        final JDFPartStatus ps = getPartStatus(mPart);

        // default to status of this
        if (ps == null)
        {
            return getStatus();
        }

        return ps.getStatus();
    }

    /**
     * getPartStatus - get a PartStatus that fits to the filter defined by mPart
     *
     * @param JDFAttributeMap mPart - the filter for the part to set the status
     *
     * @return JDFPartStatus - the PartStatus that fits
     */
    public JDFPartStatus getPartStatus(JDFAttributeMap mPart)
    {
        final VElement vPartStatus = 
            getChildElementVector(ElementName.PARTSTATUS, null, null, true, 0, false);
        int nSep = 0;
        JDFPartStatus retPartStatus = null;

        for (int i=vPartStatus.size()-1; i >= 0; i--)
        {
            JDFPartStatus ps = (JDFPartStatus) vPartStatus.elementAt(i);
            final JDFAttributeMap mapPart = ps.getPartMap();

            if (mPart.subMap(mapPart))
            {
                if (mapPart.size() > nSep)
                {
                    nSep = mapPart.size();
                    retPartStatus = ps; // mPart is a subset of  of mapPart
                }
            }
        }

        return retPartStatus;
    }

    /**
     * getCreatePartStatus - get a PartStatus that fits to the filter defined by mPart<br>
     * create it if it does not exist
     *
     * @param JDFAttributeMap mPart   - the filter for the part to set the status
    
     *
     * @return JDFPartStatus - the PartStatus that fits
     */
    public JDFPartStatus getCreatePartStatus(JDFAttributeMap mPart)
    {
        JDFPartStatus p = getPartStatus(mPart);

        if (p == null)
        {
            p = appendPartStatus();
            p.setPartMap(mPart);
        }

        return p;
    }

    /**
     * getPartStatusVector - get a vector of PartStatus that fits to the filter defined by mPart
     *
     * @param Vector mPart - the filter vector for the part to set the status
     *
     * @return VElement - the PartStatus that fits
     */
    public VElement getPartStatusVector(Vector vmPart)
    {
        final VElement vPartStatus = new VElement();

        for (int i = 0; i < vmPart.size(); i++)
        {
            final JDFPartStatus ps = getPartStatus((JDFAttributeMap) vmPart.elementAt(i));

            if (ps != null)
            {
                vPartStatus.add(ps);
            }
        }

        return vPartStatus;
    }

    /**
     * @param JDFAttributeMap mPart
     * @return VElement
     */
    public VElement getMatchingPartStatusVector(JDFAttributeMap mPart)
    {
        final VElement vPartStatus = getChildElementVector(
                ElementName.PARTSTATUS, null, null, true, 0, false);
        
        final VElement vPS = new VElement();
        
        for (int i=0;i<vPartStatus.size();i++)
        {
            final JDFPartStatus ps = (JDFPartStatus) vPartStatus.elementAt(i);
            final JDFAttributeMap mapPart = ps.getPartMap();

            if (mapPart.subMap(mPart))
            {
                vPS.add(ps); // mPart is a subset of  of mapPart
            }
        }
        
        return vPS;
    }

    /**
     * getCreatePartStatusVector - get a vector of PartStatus that fits to the filter defined by mPart
     *
     * @param Vector  mPart   - the filter vector for the part to set the status
     *
     * @return VElement - the PartStatus that fits
     */
    public VElement getCreatePartStatusVector(Vector vmPart)
    {
        final VElement vPartStatus = new VElement();

        for (int i = 0; i < vmPart.size(); i++)
        {
            final JDFPartStatus ps = getCreatePartStatus((JDFAttributeMap) vmPart.elementAt(i));

            if (ps != null)
            {
                vPartStatus.add(ps);
            }
        }

        return vPartStatus;
    }

    public VElement getPoolChildren(JDFAttributeMap mAttrib)
    {
        final VElement v = getPoolChildrenGeneric(ElementName.PARTSTATUS, mAttrib, JDFConstants.EMPTYSTRING);
        return v;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public JDFPartStatus getPoolChild(int i, JDFAttributeMap mAttrib)
    {
        return (JDFPartStatus) getPoolChildGeneric(i, ElementName.PARTSTATUS, mAttrib, JDFConstants.EMPTYSTRING);
    }

    /**
     * Set the status of the entire StatusPool
     * @param EnumNodeStatus s the status for the filter defined in mPart
     */
    public void setStatus(JDFElement.EnumNodeStatus s)
    {
        final JDFNode n = (JDFNode) getParentNode_KElement();
        n.setStatus(JDFElement.EnumNodeStatus.Pool);
        super.setStatus(s);
    }

    //////////////////////////////////////////////////////////////////////
    /**
     * Set the status of a part defined in StatusPool
     * @default setStatus(vmPart, s, JDFConstants.EMPTYSTRING)
     */
    public void setStatus(VJDFAttributeMap vmPart, 
                          JDFElement.EnumNodeStatus s, String statusDetails)
    {
        for (int i = 0; i < vmPart.size(); i++)
        {
            setStatus(vmPart.elementAt(i), s, statusDetails);
        }
    }

    //////////////////////////////////////////////////////////////////////

    /**
     * Set the status of a part defined in StatusPool
     * @default setStatus(mPart, s, JDFConstants.EMPTYSTRING)
     */
    public void setStatus(JDFAttributeMap mPart, 
                          JDFElement.EnumNodeStatus s, String statusDetails)
    {
        if ((mPart!=null) && !mPart.isEmpty()){
            final JDFPartStatus ps = getCreatePartStatus(mPart);
            ps.setStatus(s);
            if (statusDetails != null && 
                !statusDetails.equals(JDFConstants.EMPTYSTRING))
            {
                ps.setStatusDetails(statusDetails);
            }
        } else {
            setStatus(s);
            if (statusDetails != null &&
                !statusDetails.equals(JDFConstants.EMPTYSTRING))
            {
                setStatusDetails(statusDetails);
            }
        }
    }

    /**
     * @see org.cip4.jdflib.core.JDFElement#ValidStatus(EnumValidationLevel)
     */
    public boolean validStatus()
    {
        if (!super.validAttribute(AttributeName.STATUS,null,KElement.EnumValidationLevel.Complete))
        {
            return false;
        }
        if (getStatus().equals(JDFElement.EnumNodeStatus.Pool))
        {
            return false;
        }
        return true;
    }
}
