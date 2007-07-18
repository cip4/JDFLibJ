/**
==========================================================================
class JDFQueueEntry extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
 **/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoQueueEntry;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;



//----------------------------------
public class JDFQueueEntry extends JDFAutoQueueEntry
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFQueueEntry
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFQueueEntry(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFQueueEntry
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFQueueEntry(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFQueueEntry
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFQueueEntry(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString()
     * @return String
     */
    public String toString()
    {
        return "JDFQueueEntry[  --> " + super.toString() + " ]";
    }

    /**
     * get part map vector
     * @return VJDFAttributeMap: vector of attribute maps, one for each part
     */
    public VJDFAttributeMap getPartMapVector()
    {
        return super.getPartMapVector();
    }

    /**
     * set all parts to those define in vParts
     * @param vParts vector of attribute maps for the parts
     */
    public void setPartMapVector(VJDFAttributeMap vParts)
    {
        super.setPartMapVector(vParts);
    }

    /**
     * set all parts to those defined by mPart
     * @param mPart attribute map for the part to set
     */
    public void setPartMap(JDFAttributeMap mPart)
    {
        super.setPartMap(mPart);
    }

    /**
     * remove the part defined in mPart
     * @param mPart attribute map for the part to remove
     */
    public void removePartMap(JDFAttributeMap mPart)
    {
        super.removePartMap(mPart);
    }

    /**
     * check whether the part defined by mPart is included
     * @param mPart attribute map to look for
     * @return boolean - returns true if the part exists
     */
    public boolean hasPartMap(JDFAttributeMap mPart)
    {
        return super.hasPartMap(mPart);
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.auto.JDFAutoQueueEntry#setPriority(int)
     */
    public void setPriority(int value)
    {
        // TODO Auto-generated method stub
        int oldVal=getPriority();
        super.setPriority(value);
        if(isAutomated()&& value!=oldVal)
        {
            JDFQueueEntry qEBefore=null;
            if(value>oldVal)
            {
                JDFQueueEntry qEPrev=getPreviousQueueEntry();
                while(qEPrev!=null)
                {
                    if(EnumQueueEntryStatus.Running.equals(qEPrev.getQueueEntryStatus()))
                        break;
                    else if(qEPrev.getPriority()<=value)
                        qEBefore=qEPrev;
                    else
                        break;
                    qEPrev= qEPrev.getPreviousQueueEntry();
                }
                if(qEBefore!=null)
                    moveMe(qEBefore);
            }
            else
            {
                JDFQueueEntry qENext=getNextQueueEntry();
                while(qENext!=null)
                {
                    final EnumQueueEntryStatus queueEntryStatus = qENext.getQueueEntryStatus();
                    if(EnumQueueEntryStatus.Aborted.equals(queueEntryStatus))
                        break;
                    else if(EnumQueueEntryStatus.Completed.equals(queueEntryStatus))
                        break;
                    else if(EnumQueueEntryStatus.Removed.equals(queueEntryStatus))
                        break;
                    if(qENext.getPriority()>=value)
                        qEBefore=qENext;
                    else
                        break;
                    qENext= qENext.getNextQueueEntry();
                }
                if(qEBefore!=null)
                {
                    qEBefore = qEBefore.getNextQueueEntry();
                    moveMe(qEBefore);
                }
            }
        }
    }

    /**
     * @return true if the parent queue is automated
     */
    private boolean isAutomated()
    {
        KElement e=getParentNode_KElement();
        if(e instanceof JDFQueue)
        {
            return ((JDFQueue)e).isAutomated();
        }
        return false;
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.auto.JDFAutoQueueEntry#setQueueEntryStatus(org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus)
     */
    public void setQueueEntryStatus(EnumQueueEntryStatus value)
    {
        EnumQueueEntryStatus oldVal=getQueueEntryStatus();
        super.setQueueEntryStatus(value);
        if(isAutomated()&& value!=null && !value.equals(oldVal))
        {
            if(EnumQueueEntryStatus.Running.equals(value)) // running is alway front of queue
            {
                JDFQueueEntry qe=((JDFQueue)getParentNode_KElement()).getQueueEntry(0);
                while(qe!=null)
                {
                    if(qe==this)
                        break;
                    if(EnumQueueEntryStatus.Running.equals(qe.getQueueEntryStatus()))
                        qe = qe.getNextQueueEntry();
                    else
                        break;
                }
                moveMe(qe);
            }
            else if(EnumQueueEntryStatus.Completed.equals(value) || EnumQueueEntryStatus.Aborted.equals(value)) 
            {
                JDFQueueEntry qe=((JDFQueue)getParentNode_KElement()).getQueueEntry(-1);
                while(qe!=null)
                {
                    final EnumQueueEntryStatus queueEntryStatus = qe.getQueueEntryStatus();
                    if(qe==this)
                        break;
                    if(EnumQueueEntryStatus.Completed.equals(queueEntryStatus) || EnumQueueEntryStatus.Aborted.equals(queueEntryStatus))
                        qe=qe.getPreviousQueueEntry();
                    else
                        break;
                }
                qe = qe==null ? qe=((JDFQueue)getParentNode_KElement()).getQueueEntry(0): qe.getNextQueueEntry();
                moveMe(qe);
                
            }
                
        }
    }

    /**
     * get the next sibling queueentry
     * @return
     */
   public JDFQueueEntry getNextQueueEntry()
    {
        return (JDFQueueEntry)getNextSiblingElement(ElementName.QUEUEENTRY,null);
    }
    /**
     * get the previous sibling queueentry
     * @return
     */
    public JDFQueueEntry getPreviousQueueEntry()
    {
        return (JDFQueueEntry)getPreviousSiblingElement(ElementName.QUEUEENTRY,null);
    }

    /**
     * @return true if this entry is completed
     */
    public boolean isCompleted()
    {
       EnumQueueEntryStatus status=getQueueEntryStatus();
       return (status==null) || 
       EnumQueueEntryStatus.Completed.equals(status) || 
       EnumQueueEntryStatus.Aborted.equals(status);
    }

}



