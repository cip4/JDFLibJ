/**
 * The CIP4 Software License, Version 1.0
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
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFAmountPool.java
 *
 * -------------------------------------------------------------------------------------------------
 *
 * The CIP4 Software License, Version 0.1
 *
 */
package org.cip4.jdflib.core;

//import java.util.StringTokenizer;

import java.util.Iterator;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;

/**
 * This class represents a JDF-ResourceLink/AmountPool/PartAmount element
 * it inherits may methods fro ResourceLink, since PartAmount specifies ResourceLink properties of a partition
 */
public class JDFPartAmount extends JDFResourceLink
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable_ToRemove = new AtrInfoTable[8];
    static 
    {
        atrInfoTable_ToRemove[0] = new AtrInfoTable(AttributeName.COMBINEDPROCESSINDEX, 0x33333331, AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable_ToRemove[1] = new AtrInfoTable(AttributeName.COMBINEDPROCESSTYPE,  0x44444443, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_ToRemove[2] = new AtrInfoTable(AttributeName.PIPEPROTOCOL,         0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_ToRemove[3] = new AtrInfoTable(AttributeName.PROCESSUSAGE,         0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_ToRemove[4] = new AtrInfoTable(AttributeName.RSUBREF,              0x44444433, AttributeInfo.EnumAttributeType.IDREF, null, null);
        atrInfoTable_ToRemove[5] = new AtrInfoTable(AttributeName.PIPEPARTIDKEYS,       0x33333333, AttributeInfo.EnumAttributeType.enumerations, EnumPartIDKey.getEnum(0),null);
        atrInfoTable_ToRemove[6] = new AtrInfoTable(AttributeName.RREF,                 0x22222222, AttributeInfo.EnumAttributeType.IDREF ,null, null);
        atrInfoTable_ToRemove[7] = new AtrInfoTable(AttributeName.USAGE,                0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumUsage.getEnum(0), null);
    }
    private static ElemInfoTable[] elemInfoTable_ToRemove = new ElemInfoTable[1];
    static 
    {
        elemInfoTable_ToRemove[0] = new ElemInfoTable(ElementName.AMOUNTPOOL, 0x66666661);        
    }
    private static ElemInfoTable[] elemInfoTable_ToReplace = new ElemInfoTable[1];
    static 
    {
        elemInfoTable_ToReplace[0] = new ElemInfoTable(ElementName.PART, 0x22222221);        
    }

    @Override
    protected AttributeInfo getTheAttributeInfo() 
    {     
        AttributeInfo ai = super.getTheAttributeInfo().updateReplace((AtrInfoTable) null);
        ai.updateRemove(atrInfoTable_ToRemove);
        return ai;
    }

    @Override
    protected ElementInfo getTheElementInfo() 
    {
        ElementInfo eiRL=super.getTheElementInfo();
        eiRL.updateRemove(elemInfoTable_ToRemove);
        eiRL.updateReplace(elemInfoTable_ToReplace);
        return eiRL;
        
    }

    /**
     * Constructor for JDFPartAmount
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFPartAmount(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFPartAmount
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFPartAmount(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFPartAmount
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFPartAmount(
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
    @Override
    public String toString()
    {
        return "JDFPartAmount[ -->" + super.toString() + "]";
    }
    /* (non-Javadoc)
     * @see org.cip4.jdflib.core.JDFElement#getInvalidAttributes(org.cip4.jdflib.core.KElement.EnumValidationLevel, boolean, int)
     */
    @Override
    public VString getInvalidAttributes(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
    {
        final VString v= super.getInvalidAttributes(level, bIgnorePrivate, nMax);
        JDFResourceLink rl=(JDFResourceLink) getDeepParentChild(ElementName.RESOURCELINKPOOL);
        if(rl!=null)
        {
            JDFAttributeMap rlMap=rl.getAttributeMap();

            JDFAttributeMap map=rlMap==null ? null : getAttributeMap();
            if(map!=null)
            {
                Iterator<String> it=map.getKeyIterator();
                while(it.hasNext())
                {
                    String s=it.next();
                    if(rlMap.containsKey(s))
                    {
                        v.add(s);
                    }
                }      
            }
        }
        return v;
    }


    /**
     * gets part map 
     *
     * @return JDFAttributeMap, of the part element
     */
    @Override
    public JDFAttributeMap getPartMap()
    {
        return super.getPartMap();
    }

    /**
     * gets part map vector
     *
     * @return JDFAttributeMap, of the part element
     */
    @Override
    public VJDFAttributeMap getPartMapVector()
    {
        return super.getPartMapVector();
    }
    
    /**
     * returns the parent resourcelink root resource
     */
    @Override
    public JDFResource getLinkRoot()
    {
        KElement rl=getParentNode_KElement();
        if(rl!=null)
            rl=rl.getParentNode_KElement();
        return (rl instanceof JDFResourceLink) ? ((JDFResourceLink)rl).getLinkRoot() : null;
       
    }


}
