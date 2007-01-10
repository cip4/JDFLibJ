/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFAmountPool.java
 *
 * -------------------------------------------------------------------------------------------------
 *
 * The CIP4 Software License, Version 0.1
 *
 *
 * Copyright (c) 2001 The International Cooperation for the Integration of
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
 */
package org.cip4.jdflib.pool;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAmountPool;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;

/**
 * This class represents a JDF-AuditPool
 */
public class JDFAmountPool extends JDFAutoAmountPool
{
    private static final long serialVersionUID = 1L;


    /**
     * Constructor for JDFAmountPool
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFAmountPool(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAmountPool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFAmountPool(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAmountPool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFAmountPool(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * @param mPart
     */
    public void removePartAmount(JDFAttributeMap mPart)
    {
        getPartAmount(mPart).deleteNode();
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFAmountPool[ -->" + super.toString() + "]";
    }

    /**
     * Get a PartAmount that fits to the filter defined by mPart
     * @param mPart filter for the part to set the status
     * @return the PartAmount that fits
     */
    public JDFPartAmount getPartAmount(JDFAttributeMap mPart)
    {
        final VElement vPartAmount = 
            getChildElementVector(ElementName.PARTAMOUNT, null,null, true, 0, false);
         for (int i = vPartAmount.size() - 1; i >= 0; i--)
        {
            final JDFPartAmount partAmount = (JDFPartAmount) vPartAmount.elementAt(i);
            final VJDFAttributeMap vMapPart = partAmount.getPartMapVector();

            if (vMapPart.contains(mPart))
            {
                return partAmount; // exact match
            }
        }
        return null;
    }

    /**
     * Get a PartAmount that fits to the filter defined by mPart
     * @param mPart filter for the part to set the status
     * @param iSkip the iSkip'th element to get
     * @return the PartAmount that fits
     */
    public JDFPartAmount getPartAmount(JDFAttributeMap mPart,int iSkip)
    {
        final VElement vPartAmount = 
            getChildElementVector(ElementName.PARTAMOUNT, null,null, true, 0, false);
        int n=0;
        for (int i = vPartAmount.size() - 1; i >= 0; i--)
        {
            final JDFPartAmount partAmount = (JDFPartAmount) vPartAmount.elementAt(i);
            final VJDFAttributeMap vMapPart = partAmount.getPartMapVector();

            if (vMapPart.subMap(mPart) && ++n>iSkip)
            {
                return partAmount; // exact match
            }
        }
        return null;
    }

    /**
     * Get a PartAmount that fits to the filter defined by mPart
     * @param mPart filter for the part to set the status
     * @param bCreate
     * @return the PartAmount that fits
     * @deprecated use either getPartAmount or getCreatePartAmount
     */
    public JDFPartAmount getPartAmount(JDFAttributeMap mPart, boolean bCreate)
    {
        JDFPartAmount p = getPartAmount(mPart,0);
        if (bCreate && p == null)
        {
            p = (JDFPartAmount) appendElement("PartAmount", JDFConstants.EMPTYSTRING);
            p.setPartMap(mPart);
        }
        return p;
    }

    /**
     * Get a vector of PartAmount that fits to the filter defined by mPart
     * @param mPart filter vector for the part to set the status
     * @param bCreate
     * @return the PartAmount that fits
     * @deprecated use getMatchingPartAmountVector
     * default: GetPartAmountVector(VJDFAttributeMap vmPart, false)
     */
    public VElement getPartAmountVector(VJDFAttributeMap vmPart, boolean bCreate)
    {
        final VElement vPartAmount = new VElement();
        for (int i = 0; i < vmPart.size(); i++)
        {
            final JDFPartAmount ps = getPartAmount(vmPart.elementAt(i), bCreate);
            if (ps != null)
            {
                vPartAmount.addElement(ps);
            }
        }
        return vPartAmount;
    }

    /**
     * remove all partAmounts that are not specified in keepList
     * @param keepList partAmounts to keep
     */
    public void reducePartAmounts(VJDFAttributeMap keepList)
    {
        if(keepList==null)
            return;
        
        final VElement v=getChildElementVector(ElementName.PARTAMOUNT, null, null, true, -1, true);
        for(int i=0;i<v.size();i++)
        {
            final JDFPartAmount pa=(JDFPartAmount)v.elementAt(i);
            final JDFAttributeMap map=pa.getPartMap();
            boolean ciao=true;
            for(int j=0;j<keepList.size();j++)
            {
                if(map.subMap(keepList.elementAt(j)))
                {
                    ciao=false;
                    break;
                }
            }
            if(ciao)
                pa.deleteNode();
        }
    }
    /**
     * Append JDFPartAmount element
     * @param mPart JDFAttributeMap to append
     */
    public JDFPartAmount appendPartAmount(JDFAttributeMap mPart)
    {
        final JDFPartAmount p = super.appendPartAmount();
        p.setPartMap(mPart);
        return p;
    }
    
    /**
     * Append JDFPartAmount elements
     * @param vPArt vector of partAmounts to append
     */
    public JDFPartAmount appendPartAmount(VJDFAttributeMap vPart)
    {
        final JDFPartAmount p = super.appendPartAmount();
        p.setPartMapVector(vPart);
        return p;
    }

    /**
     * get JDFPartAmount specified by mPart, create a new one if it 
     * doesn't exist
     * @param mPart JDFPartAmount to get/create
     * @return
     */
    public JDFPartAmount getCreatePartAmount(JDFAttributeMap mPart)
    {
        JDFPartAmount p = getPartAmount(mPart);
        if(p == null) 
        {
            p = (JDFPartAmount) appendElement(ElementName.PARTAMOUNT, null);
            p.setPartMap(mPart);
        }
        return p;
    }
    
    /**
    * Get a vector of PartAmounts which are supersets of the filter defined by mPart<br>
    * i.e. mPart is a submap of all returned elements
    * 
    * @param mPart filter vector for the part to set the status
    * 
    * @return VElement - the vector of PartAmount elements that fit, null if nothing matches
    */
    public VElement getMatchingPartAmountVector(JDFAttributeMap mPart)
    {
        VElement vPartAmount = getChildElementVector(ElementName.PARTAMOUNT, null,null, true, 0, false);
        final int size = vPartAmount.size();
        if(size==0)
            return null;
        final VElement vPA =  new VElement();
        for(int i = 0; i <size; i++)
        {
            JDFPartAmount ps  = (JDFPartAmount)vPartAmount.elementAt(i);
            VJDFAttributeMap mm = ps.getPartMapVector();
            for(int j=0;j<mm.size();j++)
            {
                JDFAttributeMap m=mm.elementAt(j);
                if(m.subMap(mPart))
                {
                    vPA.add(ps);
                    break;
                }
            }
        }
        return vPA.size()==0 ? null : vPA;
    }
 ///////////////////////////////////////////////////////////////////////   
}
