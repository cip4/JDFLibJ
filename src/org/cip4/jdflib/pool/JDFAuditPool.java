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

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFModified;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
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
     * @param ownerDocument
     * @param qualifiedName
     */
    public JDFAuditPool(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    /**
     * Constructor for JDFAuditPool
     * @param ownerDocument
     * @param namespaceURI
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
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
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
     * @param EnumNodeStatus s The  node status at this time
     * @param KString& by the author keyword
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
     * @param EnumNodeStatus s The  node status at this time
     * @param KString& by the author keyword
     * @return JDFProcessRun the newly created ProcessRun audit
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
     * Method AddAudit.add an audit, called internally by the specialized functions
     * @param typ
     * @param by
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
            l.setSpawnID(r.getSpawnID());
        }
        
        return l;
    }
    
    /**
     * Append a Created audit element, 
     * if createdelem==null only add if it is not yet there
     * @param KString& by the author keyword
     * @param JDFElement createdElem the created element
     * @return JDFCreated the newly created Created audit
     * 
     * default: AddCreated(by, null)
     */
    public JDFCreated addCreated(String by, JDFElement createdElem)
    {
        
        final JDFCreated created = (JDFCreated) addAudit(JDFAudit.EnumAuditType.Created, by);
               
        if (createdElem != null)
        {
            String id = createdElem.getAttribute(AttributeName.ID);
            
            if (id.equals(JDFConstants.EMPTYSTRING) && 
                    (createdElem instanceof JDFResource || createdElem instanceof JDFNode))
            {
                id = createdElem.appendAnchor(JDFConstants.EMPTYSTRING);
            }
            if(!id.equals(JDFConstants.EMPTYSTRING))
                created.setref(id);
        }
        
        return created;
    }
    
    /**
     * Append a Modified audit element
     * @param KString& by the author keyword
     * @param JDFElement modifiedElem the modified element
     * @return JDFModified  the newly created Modified audit
     * 
     * default: AddModified(by, null)
     */
    public JDFModified addModified(String by, JDFElement modifiedElem)
    {
        final JDFModified modified = (JDFModified) addAudit(JDFAudit.EnumAuditType.Modified, by);
        if (modifiedElem != null)
        {
            String id = modifiedElem.getAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING);
            if (id.equals(JDFConstants.EMPTYSTRING) && 
                    (modifiedElem instanceof JDFResource || modifiedElem instanceof JDFNode))
            {
                id = modifiedElem.appendAnchor(JDFConstants.EMPTYSTRING);
                modified.setjRef(id);
            }
        }
        
        return modified;
    }
    
    /**
     * AddResourceAudit - Append a ResourceAudit audit element
     *
     * @param String by - the author keyword
     *
     * @return JDFResourceAudit - the newly created ResourceAudit audit, null if an error accured
     */
    public JDFResourceAudit addResourceAudit(String by)
    {
        return (JDFResourceAudit) addAudit(JDFAudit.EnumAuditType.ResourceAudit, by);
    }
    
    /**
     * AddEvent add a Notification Audit
     *
     * @param String by
     * @param JDFAudit.EnumSeverity s
     *
     * @return JDFAudit
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
     * @param EnumNodeStatus phase The  node status at this time
     * @param KString& by the author keyword
     * @param vmAttribute &vmParts defines a vector of map of parts for which the Spawned is valid
     * @return JDFPhaseTime the newly created PhaseTime audit
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
     * AddSpawned - Append a Spawned audit element
     *
     * @param JDFNode spawned - the spawned node
     * @param Vector rRefsRO - a vector of rRefs that are spawned read-only
     * @param Vector rRefsRW - a vector of rRefs that are spawned read-write
     * @param String by
     * @param VJDFAttributeMap vmParts
     *
     * @return JDFAudit - the newly created Spawned audit
     * 
     * default: AddSpawned(spawned, new Vector(), new Vector(), JDFConstants.EMPTYSTRING, new VJDFAttributeMap())
     */
    public JDFSpawned addSpawned(
            JDFNode spawned,
            Vector rRefsRO,
            Vector rRefsRW,
            String by,
            VJDFAttributeMap vmParts)
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
     * AddMerged - Append a Merged audit element
     *
     * @param JDFNode merged - the merged node
     * @param Vector rRefsOverwritten - a vector of rRefs that are overwritten
     * @param String by - the author keyword
     *
     * @return JDFAudit - the newly created Merged audit
     * 
     * default: AddMerged(merged, rRefsOverwritten, JDFConstants.EMPTYSTRING, null)
     */
    public JDFAudit addMerged(
            JDFNode merged,
            Vector rRefsOverwritten,
            String by,
            VJDFAttributeMap vmParts)
    {
        final JDFAudit a = addAudit(JDFAudit.EnumAuditType.Merged, by);
        a.setAttribute(JDFConstants.JREF, merged.getID(), JDFConstants.EMPTYSTRING);
        
        if (!rRefsOverwritten.isEmpty())
        {
            final String ms = StringUtil.setvString(rRefsOverwritten);
            a.setAttribute(AttributeName.RREFSOVERWRITTEN, ms, JDFConstants.EMPTYSTRING);
        }
        
        if (vmParts != null)
        {
            for (int i = 0; i < vmParts.size(); i++)
            {
                a.getCreateElement_KElement(ElementName.PART, JDFConstants.EMPTYSTRING, i).setAttributes(
                        vmParts.elementAt(i));
            }
        }
        
        return a;
    }
    
    /**
     * Append a Notification audit element with a Class attribute of Severity
     *
     * @param String by - the author keyword
     * @param JDFAudit.EnumSeverity s
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
     *
     * @return JDFAudit - the last PhaseTime audit
     */
    public JDFAudit getLastPhase()
    {
        return (JDFAudit) getPoolChildGeneric(-1, 
                JDFAudit.EnumAuditType.PhaseTime.getName(), null, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * getAudits - get all audits with attributes
     *
     * @param JDFAudit.EnumAuditType typ - type of the audit to take
     * @param JDFAttributeMap mAttributes - attribute map to filter the audits
     *
     * @return VElement - all elements, that matches the filter
     * 
     * default: getAudits(null, null)
     */
    public VElement getAudits(
            JDFAudit.EnumAuditType typ,
            JDFAttributeMap mAttributes)
    {
        String strAuditType = null;
        if (typ!=null)
        {
            strAuditType = typ.getName();
        }
        
        final VElement vElem = getPoolChildrenGeneric(strAuditType, null, JDFConstants.EMPTYSTRING);
        // herein we have KElements
        for (int i = vElem.size() - 1; i >= 0; i--)
        { // remove known comments - this would be aught in the next check but we avoid the exception
            if (((KElement) vElem.elementAt(i)) instanceof JDFComment)
            {
                vElem.removeElementAt(i);
                continue; // look at next element
            }
            
            // cast to an audit and zapp all non audits
            try
            {
                final JDFAudit audit = (JDFAudit) vElem.elementAt(i);
                // filter for attributes if applicable
                if (!audit.includesAttributes(mAttributes, true))
                {
                    vElem.removeElementAt(i);
                    continue;
                }
            }
            catch (JDFException e)
            {
                vElem.removeElementAt(i);
                continue;
            }
        }
        return vElem;
    }
    
    /**
     * get the index'th audit of the given typ
     * 
     * @param int index index of the audit negativ values are possible and will 
     * be substracted from the vector size. For example,your given Filter returns a 
     * Vector of 10 Posible Elements and your index is -7 you will get 10 - 7 = Element 
     * Number 3 
     * @param EnumAuditType typ type of the audit to take
     * @param mAttribute &mAttributes attribute map to filter the audits
     * @return an Audit that matches the filers
     * 
     * default: getAudit(index, typ, null)
     */
    public JDFAudit getAudit(
            int index,
            JDFAudit.EnumAuditType typ,
            JDFAttributeMap mAttributes)
    {
        final VElement v = getAudits(typ, mAttributes);
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
     * @param EnumNodeStatus status the node status at this time
     * @param KString& statusDetails details of this status
     * @param vmAttribute &vmParts defines a vector of map of parts for which the PhaseTime is valid
     * @return JDFPhaseTime the newly created PhaseTime audit
     * 
     * default: SetPhase(status, null,null)
     */
    public JDFPhaseTime setPhase(
            EnumNodeStatus status,
            String statusDetails,
            VJDFAttributeMap vmParts)
    {
        JDFAudit myAudit = null;
        JDFPhaseTime a = null;
        
        myAudit = getLastPhase();
        
        if (myAudit != null)
        {
            a = (JDFPhaseTime) myAudit;
        }
        if (a == null)
        {
            a = addPhaseTime(status, null,null);
        }
        else if (
                !a.getStatus().equals(status)
                || (statusDetails!=null && !statusDetails.equals(a.getStatusDetails()) ))
        {
            a.setEnd(new JDFDate());
            a = addPhaseTime(status, null,null);
            if (statusDetails != null)
            {
                a.setStatusDetails(statusDetails);
            }
        }
        else
        {
            // no change but keep stop time
            a.setEnd(new JDFDate());
            return a;
        }
        a.setPartMapVector(vmParts);
        
        return a;
    }
    
    /**
     * get the linked resources matching some conditions
     * @param JDFAttributeMap mResAtt   map of Resource attributes to search for
     * @param boolean bFollowRefs       true if internal references shall be followed
     * @return VElement                vector with all elements matching the conditions
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
     * @param JDFAttributeMap mLinkAtt - the attribute to search for
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
     * @param JDFAudit p: the Child to add to the element
     */
    public void appendUnique(JDFAudit p)
    {
        appendUniqueGeneric(p);
    }
    
    /**
     * Append all children of p for which no identical child exists
     * @param JDFAuditPool p: the Child to add to the element
     */
    public void appendUnique(JDFAuditPool p)
    {
        appendUniqueGeneric(p);
    }
    
    /**
     * gets all children with the attribute name,mAttrib, nameSpaceURI out of the pool
     * @param String strName: name of the Child
     * @param JDFAttributeMap mAttrib: an attribute to search for
     * @return VElement: a vector with all elements in the pool matching the conditions
     * 
     * default: getPoolChildren(null,null)
     */
    public VElement getPoolChildren(String strName, JDFAttributeMap mAttrib)
    {
        return getPoolChildrenGeneric(strName, mAttrib, JDFConstants.EMPTYSTRING);
    }
    
    public void cleanUpMerge(JDFNode.EnumCleanUpMerge cleanPolicy, String spawnID)
    {
        if (cleanPolicy != JDFNode.EnumCleanUpMerge.None)
        {
            VElement vMerged    = new VElement();
            VElement vSpawned   = new VElement();
            
            if (spawnID.equals(JDFConstants.EMPTYSTRING))
            {
                vMerged     = getAudits(JDFAudit.EnumAuditType.Merged, null);
                vSpawned    = getAudits(JDFAudit.EnumAuditType.Spawned, null);
            }
            else
            {
                
                final JDFAttributeMap mSpawnID = new JDFAttributeMap(AttributeName.MERGEID, spawnID);
                JDFAudit a = getAudit(0, JDFAudit.EnumAuditType.Merged, mSpawnID);
                if(a != null)
                {    
                    vMerged.add(a);
                }
                mSpawnID.clear();
                mSpawnID.put(AttributeName.NEWSPAWNID, spawnID);
                a = getAudit(0, JDFAudit.EnumAuditType.Spawned, mSpawnID);
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
     * @param version: version that the resulting element should correspond to
     * @return true if successful
     */
   public boolean fixVersion(EnumVersion version){
        if(hasAttribute(AttributeName.RREFS))
            removeAttribute(AttributeName.RREFS);
        return super.fixVersion(version);
    }
   //////////////////////////////////////////////////////////////////////
    
}
