/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFAuditPool.java
 *
 * -------------------------------------------------------------------------------------------------
 *
 * Last changes
 *
 * 2002-07-02 JG renamed JDFProcess to JDFNode
 * 2002-07-02 JG SetPhase modified first parameter to be JDFPhaseTime::EnumNodeStatus
 * 2002-07-02 JG removed IsValid 
 * 2002-07-02 JG getAudits added const mAttribute &mAttributes=mAttribute() also fixed inversion logic bug
 * 2002-07-02 JG getAudit modified 3rd parameter to const mAttribute &mAttributes=mAttribute()
 * 2002-07-02 JG remove getPoolChildName
 */
package org.cip4.jdflib.pool;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFDeleted;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFModified;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class represents a JDF-AuditPool
 */
public class JDFAuditPool extends JDFPool
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFAuditPool
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFAuditPool(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    /**
     * Constructor for JDFAuditPool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFAuditPool(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFAuditPool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFAuditPool(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[9];
    static 
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.CREATED,          0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DELETED,          0x33333333); // TODO add JDFDeleted
        elemInfoTable[2] = new ElemInfoTable(ElementName.MODIFIED,         0x33333333);
        elemInfoTable[3] = new ElemInfoTable(ElementName.NOTIFICATION,     0x33333333);
        elemInfoTable[4] = new ElemInfoTable(ElementName.RESOURCEAUDIT,    0x33333333);
        elemInfoTable[5] = new ElemInfoTable(ElementName.SPAWNED,          0x33333333);
        elemInfoTable[6] = new ElemInfoTable(ElementName.MERGED,           0x33333333);
        elemInfoTable[7] = new ElemInfoTable(ElementName.PHASETIME,        0x33333333);
        elemInfoTable[8] = new ElemInfoTable(ElementName.PROCESSRUN,       0x33333333);
    }
    
    protected ElementInfo getTheElementInfo() 
    {
        return new ElementInfo(super.getTheElementInfo(), elemInfoTable);
    }
    
    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFAuditPool[ -->" + super.toString() + "]";
    }
    
    /**
     * Add a ProcessRun Audit
     * @param s   the  node status at this time
     * @param by  the author keyword
     * @return JDFProcessRun the newly created ProcessRun audit
     * 
     * default: addProcessRun(s, JDFConstants.EMPTYSTRING)
     * @deprecated use addProcessRun(JDFElement.EnumNodeStatus s, JDFConstants.EMPTYSTRING, new VJDFAttributeMap())
     */
    public JDFProcessRun addProcessRun(JDFElement.EnumNodeStatus s, String by)
    {
         return addProcessRun(s,by,null);
    }
    
    /**
     * Add a ProcessRun Audit
     * @param s  the node status at this time
     * @param by the author keyword
     * @return the newly created ProcessRun audit
     * 
     * default: AddProcessRun(s, JDFConstants.EMPTYSTRING)
     */
    public JDFProcessRun addProcessRun(JDFElement.EnumNodeStatus s,String by, VJDFAttributeMap vmParts)
    {
        JDFProcessRun pr = (JDFProcessRun)addAudit(JDFAudit.EnumAuditType.ProcessRun, by);
        pr.setStart(null);
        pr.setEnd(null);
        pr.setEndStatus(s);
        pr.setPartMapVector(vmParts);
        
        return pr;
    }
    
    /**
     * add an audit, called internally by the specialized functions
     * @param typ audit type
     * @param by  the author keyword
     * @return JDFAudit
     * 
     * default: AddAudit(typ, JDFConstants.EMPTYSTRING)
     */
    public JDFAudit addAudit(JDFAudit.EnumAuditType typ, String by)
    {
        final JDFAudit l = (JDFAudit) appendElement(typ.getName(), null);
        if(by!=null)
            l.setAuthor(by);
        
        final JDFNode r = getJDFRoot();
        if (r.hasAttribute(AttributeName.SPAWNID))
        {
            l.setSpawnID(r.getSpawnID(false));
        }
        
        return l;
    }
    
    /**
     * Append a Created audit element, 
     * if createdElem==null only add if it is not yet there
     * @param by          the author keyword
     * @param createdElem the created element
     * @return the newly created Created audit
     * 
     * default: AddCreated(by, null)
     */
    public JDFCreated addCreated(String by, KElement createdElem)
    {
        
        final JDFCreated created = (JDFCreated) addAudit(JDFAudit.EnumAuditType.Created, by);
               
        if (createdElem != null)
        {
            final String xpath=createdElem.buildXPath(getParentJDF().buildXPath(null));
            created.setXPath(xpath);
        }
        
        return created;
    }
    
    /**
     * Append a Modified audit element
     * @param by the author keyword
     * @param modifiedElem the modified element
     * 
     * default: AddModified(by, null)
     */
    public JDFModified addModified(String by, KElement modifiedElem)
    {
        final JDFModified modified = (JDFModified) addAudit(JDFAudit.EnumAuditType.Modified, by);
        if (modifiedElem != null)
        {
            final String xpath=modifiedElem.buildXPath(getParentJDF().buildXPath(null));
            modified.setXPath(xpath);
        }

        return modified;
    }  /**
     * Append a Deleted audit element
     * @param by the author keyword
     * @param modifiedElem the modified element
     * @return JDFDeleted  the newly created Modified audit
     * 
     * default: AddDeleted(null, null)
     */
    public JDFDeleted addDeleted(String by, KElement deletedElem)
    {
        final JDFDeleted deleted = (JDFDeleted) addAudit(JDFAudit.EnumAuditType.Deleted, by);
        if (deletedElem != null)
        {
            final String xpath=deletedElem.buildXPath(getParentJDF().buildXPath(null));
            deleted.setXPath(xpath);
        }

        return deleted;
    }
    
    /**
     * append a ResourceAudit audit element
     *
     * @param by the author keyword
     *
     * @return JDFResourceAudit - the newly created ResourceAudit audit, null if an error occured
     */
    public JDFResourceAudit addResourceAudit(String by)
    {
        return (JDFResourceAudit) addAudit(JDFAudit.EnumAuditType.ResourceAudit, by);
    }
    
    /**
     * add a Notification Audit
     *
     * @param by the author keyword
     * @param s  severity of the event
     *
     * @return JDFAudit - the newly created Notification Audit
     * TODO replace with addNotification
     */
    public JDFAudit addEvent(String by, JDFAudit.EnumSeverity s)
    {
        final JDFAudit l = addAudit(JDFAudit.EnumAuditType.Notification, by);
        l.setSeverity(s);
        return l;
    }
    
    /**
     * Append a PhaseTime audit element
     * @param phase   the node status at this time
     * @param by      the author keyword
     * @param vmParts defines a vector of map of parts for which the Spawned is valid
     * @return the newly created PhaseTime audit
     * 
     * default: AddPhaseTime(phase, JDFConstants.EMPTYSTRING, new VJDFAttributeMap())
     */
    public JDFPhaseTime addPhaseTime(
            EnumNodeStatus phase,
            String by,
            VJDFAttributeMap vmParts)
    {
        JDFPhaseTime myAudit = (JDFPhaseTime) addAudit(JDFAudit.EnumAuditType.PhaseTime, by);
        myAudit.setStatus(phase);
        myAudit.setStart(new JDFDate());
        myAudit.setEnd(new JDFDate());
        myAudit.setPartMapVector(vmParts);
        
        return myAudit;
    }
    
    /**
     * Append a Spawned audit element
     *
     * @param spawned the spawned node
     * @param rRefsRO a vector of rRefs that are spawned read-only
     * @param rRefsRW a vector of rRefs that are spawned read-write
     * @param by      the author keyword
     * @param vmParts
     *
     * @return JDFAudit - the newly created Spawned audit
     * 
     * default: AddSpawned(spawned, new Vector(), new Vector(), JDFConstants.EMPTYSTRING, new VJDFAttributeMap())
     */
    public JDFSpawned addSpawned( JDFNode spawned, VString rRefsRO, VString rRefsRW,
            String by, VJDFAttributeMap vmParts)
    {
        final JDFSpawned a = (JDFSpawned) addAudit(JDFAudit.EnumAuditType.Spawned, by);
        a.setAttribute(JDFConstants.JREF, spawned.getID(), null);
        String ms = null;
        if (rRefsRO!=null && !rRefsRO.isEmpty())
        {
            ms = StringUtil.setvString(rRefsRO);
            a.setAttribute(AttributeName.RREFSROCOPIED, ms, null);
        }
        
        if (rRefsRW!=null && !rRefsRW.isEmpty())
        {
            ms = StringUtil.setvString(rRefsRW);
            a.setAttribute(AttributeName.RREFSRWCOPIED, ms, null);
        }
        
        setPartMapVector(vmParts);
        
        return a;
    }
    
    /**
     * Append a Merged audit element
     *
     * @param merged           the merged node
     * @param rRefsOverwritten a vector of rRefs that are overwritten
     * @param by               the author keyword
     *
     * @return JDFMerged - the newly created Merged audit
     * 
     * default: AddMerged(merged, rRefsOverwritten, JDFConstants.EMPTYSTRING, null)
     */
    public JDFMerged addMerged( JDFNode merged,VString rRefsOverwritten, String by,VJDFAttributeMap vmParts)
    {
        final JDFMerged mergedAudit = (JDFMerged)addAudit(JDFAudit.EnumAuditType.Merged, by);
        mergedAudit.setjRef(merged.getID());
        if(rRefsOverwritten!=null && rRefsOverwritten.isEmpty())
            rRefsOverwritten=null;
        mergedAudit.setrRefsOverwritten(rRefsOverwritten);
        
        setPartMapVector(vmParts);
        return mergedAudit;
    }
    
    /**
     * Append a Notification audit element with a Class attribute of Severity
     *
     * @param by the author keyword
     * @param s  the severity
     *
     * @return JDFAudit - the newly created Notification audit
     */
    public JDFNotification addNotification(
            JDFNotification.EnumClass severity,
            String by,
            VJDFAttributeMap vmParts)
    {
        final JDFNotification l = (JDFNotification) addAudit(JDFAudit.EnumAuditType.Notification, by);
        if (l != null)
        {
            l.setClass(severity);
            l.setPartMapVector(vmParts);
        }
        return l;
    }
    
    /**
     * getLastPhase - get the most recent PhaseTime audit in this pool
     * @deprecated use getLastPhase(VJDFAttributeMap)
     * @return JDFAudit - the last PhaseTime audit
     */
    public JDFPhaseTime getLastPhase()
    {
        return getLastPhase(null);
    }

    /**
     * getLastPhase - get the most recent PhaseTime audit in this pool
     * @param vPartMap the list of matching partMaps
     * @return JDFAudit - the last PhaseTime audit
     */
    public JDFPhaseTime getLastPhase(VJDFAttributeMap vPartMap)
    {
        return (JDFPhaseTime)getAudit(-1, EnumAuditType.PhaseTime, null,vPartMap);
    }
    
    /**
     * getAudits - get all audits with attributes and partMap
     *
     * @param  typ        type of the audit to take
     * @param mAttributes attribute map to filter the audits
     *
     * @return VElement - all elements, that matches the filter
     * 
     * default: getAudits(null, null)
     * @deprecated use getAudits(null, null, null)
     */
    public VElement getAudits(
            JDFAudit.EnumAuditType typ,
            JDFAttributeMap mAttributes)
    {
        return getAudits(typ, mAttributes,null);
    }
    /**
    }
     * getAudits - get all audits with attributes and partMap
     *
     * @param typ         type of the audit to take
     * @param mAttributes attribute map to filter the audits
     * @param vParts      the partmap vector - note that not all audits legally have parts
     * @return VElement - all elements, that matches the filter
     * 
     * default: getAudits(null, null, null)
     */
    public VElement getAudits(JDFAudit.EnumAuditType typ, JDFAttributeMap mAttributes, VJDFAttributeMap vParts)
    {
        String strAuditType = null;
        if (typ!=null)
        {
            strAuditType = typ.getName();
        }
        
        final VElement vElem = getPoolChildrenGeneric(strAuditType, mAttributes, null);
        if(vParts!=null && vParts.size()==0)
            vParts=null;
        
        for (int i = vElem.size() - 1; i >= 0; i--)
        { // remove known comments - this would be aught in the next check but we avoid the exception
            if (!(vElem.elementAt(i) instanceof JDFAudit))
            {
                vElem.removeElementAt(i);
                continue; // look at next element
            }
            
            final JDFAudit audit = (JDFAudit) vElem.elementAt(i);
            if(vParts!=null && !vParts.equals(audit.getPartMapVector()))
            {
                vElem.removeElementAt(i);
                continue; // look at next element
            }
         }
        return vElem;
    }
    /**
     * get the index'th audit of the given typ
     * 
     * @param index index of the audit negativ values are possible and will 
     * be substracted from the vector size. For example, your given Filter returns a 
     * Vector of 10 Posible Elements and your index is -7 you will get 10 - 7 = Element 
     * Number 3 
     * @param typ type of the audit to take
     * @param mAttributes attribute map to filter the audits
     * @return an Audit that matches the filers
     * 
     * default: getAudit(index, typ, null)
     * @deprecated use 4 parameter version
     */
    public JDFAudit getAudit(
            int index,
            JDFAudit.EnumAuditType typ,
            JDFAttributeMap mAttributes)
    {
        return getAudit(index, typ, mAttributes,null);
    }
    
    /**
     * get the index'th audit of the given typ
     * 
     * @param index index of the audit negativ values are possible and will 
     * be substracted from the vector size. For example,your given Filter returns a 
     * Vector of 10 Posible Elements and your index is -7 you will get 10 - 7 = Element 
     * Number 3 
     * @param typ         type of the audit to take
     * @param mAttributes attribute map to filter the audits
     * @param vParts      the partmap vector - note that not all audits legally have parts
     * @return an Audit that matches the filers
     * 
     * default: getAudit(index, typ, null)
     */
    public JDFAudit getAudit(int index, JDFAudit.EnumAuditType typ, JDFAttributeMap mAttributes,VJDFAttributeMap vParts)
    {
        final VElement v = getAudits(typ, mAttributes,vParts);
        if (index < 0)
        {
            index = v.size() + index;
        }
        if (index >= v.size() || index < 0)
        {
            return null;
        }
        
        return (JDFAudit) v.elementAt(index);
    }
    
    /**
     * Create a PhaseTime Audit and fill it
     * 
     * @param status        the node status at this time
     * @param statusDetails details of this status
     * @param vmParts       defines a vector of map of parts for which the PhaseTime is valid
     * @return JDFPhaseTime the newly created PhaseTime audit
     * 
     * default: SetPhase(status, null,null)
     */
    public JDFPhaseTime setPhase( EnumNodeStatus status,String statusDetails, VJDFAttributeMap vmParts)
    {
        JDFPhaseTime pt = getLastPhase(vmParts);
        
        if (pt == null)
        {
            pt = addPhaseTime(status, null ,vmParts);
        }
        else if (!pt.getStatus().equals(status)
                || (statusDetails!=null && !statusDetails.equals(pt.getStatusDetails()) ))
        {
            pt.setEnd(new JDFDate());
            pt = addPhaseTime(status, null,vmParts);
        }
        else
        {
            // no change but keep stop time
            pt.setEnd(new JDFDate());
            return pt;
        }
        if (statusDetails != null)
        {
            pt.setStatusDetails(statusDetails);
        }

        pt.setPartMapVector(vmParts);
        
        return pt;
    }
    
    /**
     * get the linked resources matching some conditions
     * @param mResAtt     map of Resource attributes to search for
     * @param bFollowRefs true if internal references shall be followed
     * @return VElement   vector with all elements matching the conditions
     * 
     * default: getLinkedResources(null, true)
     */
    public VElement getLinkedResources(JDFAttributeMap mResAtt, boolean bFollowRefs)
    {
        final VString refs = getHRefs(null,false,true);
        refs.unify();
        final VElement v = new VElement();
        
        for (int i = 0; i < refs.size(); i++)
        {
            final KElement e  =  getTarget((String) refs.elementAt(i), AttributeName.ID);            
            if(e!=null && e.includesAttributes(mResAtt, true)) 
            {
                v.addElement(e);
                if(bFollowRefs && (e instanceof JDFElement))
                {
                    v.appendUnique(((JDFElement)e).getvHRefRes(bFollowRefs,true));
                }
            }            
        }
        return v;
    }
    
    /**
     * getLinks - get the links matching mLinkAtt out of the resource pool
     *
     * @param mLinkAtt the attribute to search for
     *
     * @return VElement - vector all all elements matching the condition mLinkAtt
     * @deprecated 060216 - this seams to have accidentally been added
     * default: getLinks(null)
     */
    public VElement getLinks(JDFAttributeMap mLinkAtt)
    {
        return getPoolChildrenGeneric(JDFConstants.EMPTYSTRING, mLinkAtt, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * Append a new child if no identical child exists
     * @param p the Child to add to the element
     */
    public void appendUnique(JDFAudit p)
    {
        appendUniqueGeneric(p);
    }
    
    /**
     * Append all children of p for which no identical child exists
     * @param p the Child to add to the element
     */
    public void appendUnique(JDFAuditPool p)
    {
        appendUniqueGeneric(p);
    }
    
    /**
     * gets all children with the attribute name,mAttrib, nameSpaceURI out of the pool
     * @param strName name of the Child
     * @param mAttrib an attribute to search for
     * @return VElement: a vector with all elements in the pool matching the conditions
     * 
     * default: getPoolChildren(null,null)
     */
    public VElement getPoolChildren(String strName, JDFAttributeMap mAttrib)
    {
        return getPoolChildrenGeneric(strName, mAttrib, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * @param cleanPolicy
     * @param spawnID
     */
    public void cleanUpMerge(JDFNode.EnumCleanUpMerge cleanPolicy, String spawnID)
    {
        if (cleanPolicy != JDFNode.EnumCleanUpMerge.None)
        {
            VElement vMerged    = new VElement();
            VElement vSpawned   = new VElement();
            
            if (isWildCard(spawnID))
            {
                vMerged     = getAudits(JDFAudit.EnumAuditType.Merged, null,null);
                vSpawned    = getAudits(JDFAudit.EnumAuditType.Spawned, null,null);
            }
            else
            {
                
                final JDFAttributeMap mSpawnID = new JDFAttributeMap(AttributeName.MERGEID, spawnID);
                JDFAudit a = getAudit(0, JDFAudit.EnumAuditType.Merged, mSpawnID,null);
                if(a != null)
                {    
                    vMerged.add(a);
                }
                mSpawnID.clear();
                mSpawnID.put(AttributeName.NEWSPAWNID, spawnID);
                a = getAudit(0, JDFAudit.EnumAuditType.Spawned, mSpawnID,null);
                if(a != null)
                {    
                    vSpawned.add(a);
                }
            }
            for (int i = vMerged.size() - 1; i >= 0; i--)
            {
                final JDFMerged merged = (JDFMerged)vMerged.elementAt(i);
                final String mergeID = merged.getMergeID();
                for (int j = vSpawned.size() - 1; j >= 0; j--)
                {
                    final JDFSpawned spawned = (JDFSpawned)vSpawned.elementAt(i);
                    if (spawned.getNewSpawnID().equals(mergeID))
                    {
                        if (cleanPolicy == JDFNode.EnumCleanUpMerge.RemoveAll)
                        {
                            spawned.deleteNode();
                            merged.deleteNode();
                            vSpawned.remove(j);
                        }
                        else if (cleanPolicy == JDFNode.EnumCleanUpMerge.RemoveRRefs)
                        {
                            spawned.removeAttribute(AttributeName.RREFSRWCOPIED);
                            spawned.removeAttribute(AttributeName.RREFSROCOPIED);
                            merged.removeAttribute(AttributeName.RREFSOVERWRITTEN);
                        }
                        else
                        {
                            // never get here
                            throw new JDFException("JDFNode.EnumCleanUpMerge: illegal cleanPolicy enumeration: " + 
                                    cleanPolicy.getValue());
                        }
                    }
                }
            }
        }
    }
    
   //////////////////////////////////////////////////////////////////////
    
    /**
     * Mother of all version fixing routines
     *
     * uses heuristics to modify this element and its children to be compatible with a given version
     * in general, it will be able to move from low to high versions but potentially fail 
     * when attempting to move from higher to lower versions
     *
     * @param version version that the resulting element should correspond to
     * @return true if successful
     */
   public boolean fixVersion(EnumVersion version){
        if(hasAttribute(AttributeName.RREFS))
            removeAttribute(AttributeName.RREFS);
        return super.fixVersion(version);
    }
   //////////////////////////////////////////////////////////////////////
    
}
