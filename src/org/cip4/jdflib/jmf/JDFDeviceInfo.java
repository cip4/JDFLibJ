/**
==========================================================================
class JDFDeviceInfo extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
**/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFPhaseTime;



//----------------------------------
    public class JDFDeviceInfo extends JDFAutoDeviceInfo
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFDeviceInfo
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFDeviceInfo(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFDeviceInfo
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFDeviceInfo(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFDeviceInfo
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFDeviceInfo(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString()
     * @see org.cip4.jdflib.auto.JDFAutoDeviceInfo#toString()
     */
    public String toString()
    {
        return "JDFDeviceInfo[  --> " + super.toString() + " ]";
    }
    
     /**
     * Method getJobCount.
     * @return int
     * @deprecated use numChildElements(ElementName.JOBPHASE,null)
     */
    public int getJobCount()
    {
        return getChildrenByTagName(ElementName.JOBPHASE,null,null, false, true,0).size();
    }   
    
    /**
     * create a JobPhase message from a phaseTime Audit
     * @param pt the phasetime audit
     * @return JDFJobPhase: the jobphase element that has been filled by the phaseTime
     */
    public JDFJobPhase createJobPhaseFromPhaseTime(final JDFPhaseTime pt)
    {
        JDFJobPhase jp=appendJobPhase();
        JDFNode node=pt.getParentJDF();
        
        jp.setJobID(node.getJobID(true));
        jp.setJobPartID(node.getJobPartID(true));
        final VJDFAttributeMap partMapVector = pt.getPartMapVector();
        jp.setPartMapVector(partMapVector);
        jp.setStatus(pt.getStatus());
        final String statusDetails = pt.getStatusDetails();
        jp.setStatusDetails(statusDetails);
        jp.setPhaseStartTime(pt.getStart());
        JDFResourceLink rl=pt.getLink(0);
        if(rl!=null)
        {
            if(rl.getAmountPoolAttribute(AttributeName.ACTUALAMOUNT, null, null, 0)!=null)
                jp.setPhaseAmount(rl.getActualAmount(null));
        }
        //TODO set more
        jp.eraseEmptyAttributes(true);
        return jp;
    }
}



