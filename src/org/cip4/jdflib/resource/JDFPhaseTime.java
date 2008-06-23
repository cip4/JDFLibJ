/**
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
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
 * copyright (c) 1999-2006, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */
/**
 *========================================================================== class JDFPhaseTime extends JDFAutoPhaseTime
 * created 2001-09-06T10:02:57GMT+02:00 ==========================================================================
 *          @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 *              @Author: sabjon@topmail.de   using a code generator
 * Warning! very preliminary test version. Interface subject to change without prior notice! Revision history:   ...
 */

package org.cip4.jdflib.resource;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPhaseTime;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;


public class JDFPhaseTime extends JDFAutoPhaseTime
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFPhaseTime
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFPhaseTime(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFPhaseTime
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFPhaseTime(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFPhaseTime
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFPhaseTime(
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
        return "JDFPhaseTime[  --> " + super.toString() + " ]";
    }
    
 
    /**
     * set all parts to those defined in vParts
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
     * remove the part defined by mPart
     * @param mPart attribute map for the part to remove
     */
    public void removePartMap(JDFAttributeMap mPart)
    {
        super.removePartMap(mPart);
    }

    /**
     * check whether the part defined in mPart is included
     * @param mPart attribute map to look for
     * @return boolean - returns true if the part exists
     */
    public boolean hasPartMap(JDFAttributeMap mPart)
    {
        return super.hasPartMap(mPart);
    }
    
    /**
     * return a vector of unknown element nodenames
     * <p>
     * default: GetInvalidElements(true, 999999)
     * 
     * @param bIgnorePrivate used by JDFElement during the validation
     * @param nMax           maximum size of the returned vector
     * @return Vector - vector of unknown element nodenames
     * 
     * !!! Do not change the signature of this method
     */
    public Vector getUnknownElements(boolean bIgnorePrivate, int nMax)
    {
        if(bIgnorePrivate)
            bIgnorePrivate=false; // dummy to fool compiler
        return getUnknownPoolElements(JDFElement.EnumPoolType.ResourceLinkPool, nMax);
    }


    /**
     * copy a Vector of resourceLinks into this PhaseTime
     * 
     * @param vRL the Vector of resourceLinks to copy - the order is significant, because
     * the first rl will be used to fill the Amount in Signal/DeviceInfo/JobPhase  
     */
    public void setLinks(VElement vRL)
    {
        if(vRL==null)
            return;
        final int size = vRL.size();
        if(size==0)
            return;
        
        for(int i=0;i<size;i++)
        {        
            JDFResourceLink rl=(JDFResourceLink)vRL.elementAt(i);
            removeChildren(rl.getLocalName(), rl.getNamespaceURI(),null);
        }
        for(int i=0;i<size;i++)
        {            
            JDFResourceLink rl=(JDFResourceLink)vRL.elementAt(i);
            copyElement(rl, null);
        }
    }


    /**
     * return the ResourceLink in <code>this</code>, null if none exists
     * @param iSkip the nTh resourceLink to retrieve
     * @return JDFResourceLink - <code>this</code> phaseTimes ResourceLink
     */
    public JDFResourceLink getLink(int iSkip)
    {
        KElement e=getFirstChildElement();
        int n=0;
        while(e!=null)
        {
            if(e instanceof JDFResourceLink)
            {
                if(n++>=iSkip)
                    return (JDFResourceLink)e;
            }
            e=e.getNextSiblingElement();
        }
        return null;
    }
    
    /**
     * return the ResourceLink in <code>this</code>, null if none exists
     * @param iSkip the n'th resourceLink to retrieve
     * @return JDFResourceLink - this phaseTimes ResourceLink
     */
    public VElement getLinkVector()
    {
        KElement e=getFirstChildElement();
        VElement vRet=new VElement();
        while(e!=null)
        {
            if(e instanceof JDFResourceLink)
            {
                vRet.add(e);
            }
            e=e.getNextSiblingElement();
        }
        return vRet.size()==0 ? null : vRet;
    }
    /**
     * get the implied duration from Start and End
     * @return JDFDuration the duration
     */
    public JDFDuration getDuration()
    {
        JDFDate dStart=getStart();
        JDFDate dEnd=getEnd();
        if(dStart==null || dEnd==null)
            return null;
        int dur = (int)((dEnd.getTimeInMillis()-dStart.getTimeInMillis())/1000);
        if(dur<0)
            dur=0;
        return new JDFDuration(dur);
    }

    /**
     * @param m_moduleid the list of module ids to add, if null: nop
     * @return the list of ModulePhase element
     * @throws IllegalArgumentException if the vectors have different lengths
     */
    public VElement setModules(VString moduleIDs, VString moduleTypes)
    {
        if(moduleIDs==null || moduleIDs.size()==0)
            return null;
        if(moduleTypes==null || moduleTypes.size()==0 || moduleTypes.size()!=moduleIDs.size())
            throw new IllegalArgumentException("Inconsistent vector lengths");
        VElement v=new VElement();
        for(int i=0;i<moduleIDs.size();i++)
        {
            final JDFModulePhase modulePhase = getCreateModulePhase(i);
            v.add(modulePhase);
            modulePhase.setModuleID(moduleIDs.stringAt(i));
            modulePhase.setModuleType(moduleTypes.stringAt(i));
        }
        return v;
    }   
    @Override
    public void fixBad(EnumVersion version, EnumValidationLevel level)
    {
        JDFResourceLink cl=(JDFResourceLink) getChildByTagName( "ComponentLink", null, 0, new JDFAttributeMap(AttributeName.USAGE,EnumUsage.Output), true,true);
        if(cl!=null)
        {
            JDFResourceLink ml=(JDFResourceLink) getChildByTagName( "MediaLink", null, 0, new JDFAttributeMap(AttributeName.USAGE,EnumUsage.Input), true,true);
            if(ml==null)
            {
                ml=(JDFResourceLink) copyElement(getParentJDF().getResourceLinkPool().getElement("MediaLink"), null);
                ml.removeChild(ElementName.AMOUNTPOOL, null, 0);
                ml.appendAmountPool();
            }
            JDFAmountPool apm=ml.getAmountPool();
            JDFAmountPool apc=cl.getAmountPool();

            if(apm!=null && apc!=null)
            {
                JDFAttributeMap aMapW=new JDFAttributeMap(AttributeName.CONDITION,"Waste");
                for(int i=0;i<99999;i++)
                {
                    JDFPartAmount pac=apc.getPartAmount(aMapW, i);
                    if(pac==null)
                        break;
                    JDFAttributeMap m2=pac.getPartMap();
                    JDFPartAmount pam=apm.getCreatePartAmount(m2);
                    if(pac.hasAttribute(AttributeName.MAXAMOUNT)&&!pam.hasAttribute(AttributeName.MAXAMOUNT))
                        pam.moveAttribute(AttributeName.MAXAMOUNT, pac, null, null, null);
                    else if(pac.hasAttribute(AttributeName.AMOUNT)&&!pam.hasAttribute(AttributeName.MAXAMOUNT))
                        pam.moveAttribute(AttributeName.MAXAMOUNT, pac, AttributeName.AMOUNT, null, null);
                    if(pac.hasAttribute(AttributeName.ACTUALAMOUNT)&&!pam.hasAttribute(AttributeName.ACTUALAMOUNT))
                        pam.moveAttribute(AttributeName.ACTUALAMOUNT, pac, null, null, null);
                    pac.deleteNode();
                }
                JDFAttributeMap aMapG=new JDFAttributeMap(AttributeName.CONDITION,"Good");
                for(int i=0;i<99999;i++)
                {
                    JDFPartAmount pam=apm.getPartAmount(aMapG, i);
                    if(pam==null)
                        break;
                    JDFAttributeMap m2=pam.getPartMap();
                    JDFPartAmount pac=apc.getCreatePartAmount(m2);
                    if(pam.hasAttribute(AttributeName.MAXAMOUNT)&&!pac.hasAttribute(AttributeName.MAXAMOUNT))
                        pac.moveAttribute(AttributeName.MAXAMOUNT, pam, null, null, null);
                }
            }
        }
        super.fixBad(version, level);
    }
    
} // class JDFPhaseTime
// ==========================================================================
