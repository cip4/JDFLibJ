/*
 *
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
 *//**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFPreflightReport.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPRItem;
import org.cip4.jdflib.auto.JDFAutoAction.EnumSeverity;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.w3c.dom.DOMException;


public class JDFPRItem extends JDFAutoPRItem
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFPRItem
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFPRItem(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFPRItem
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFPRItem(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFPRItem
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFPRItem(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFPRItem[  --> " + super.toString() + " ]";
    }


    /**
     * @param groupMap
     * @return
     */
    public JDFPRGroup getPRGroup(JDFAttributeMap groupMap)
    {
        VElement v= getChildElementVector(ElementName.PRGROUP, null, null, true, -1, false);
        for(int i=0;i<v.size();i++)
        {
            JDFPRGroup pg=(JDFPRGroup)v.elementAt(i);
            JDFPRGroupOccurrence pgo=pg.getPRGroupOccurrence();
            if(pgo==null)
                continue;
            JDFAttributeMap map=pgo.getAttributeMap();
            if(map.subMap(groupMap))
                return pg;
        }
        return null;
    }


    /**
     * @param groupMap
     * @return
     */
    public JDFPRGroup getCreatePRGroup(JDFAttributeMap groupMap)
    {
        JDFPRGroup pg=getPRGroup(groupMap);
        if(pg==null)
        {
            pg=appendPRGroup();
            pg.appendPRGroupOccurrence().setAttributes(groupMap);
        }
        return pg;
    }


    /**
     * increment occurrences by i
     * if this lives in a standard preflight report tree, also increment the appropriate higher up counters
     * @param i
     */
    public void addOccurrences(int i, EnumSeverity sev)
    {
        addAttribute(AttributeName.OCCURRENCES, i, null);
        KElement e=getParentNode_KElement();
        if (e instanceof JDFPreflightReport)
        {
            ((JDFPreflightReport)e).addOccurrences(i, sev);
        }

    }


    /**
     * insert pageSet into PageSet.
     * @param pageSet
     */
    public void insertPageSet(int pageSet)
    {
        JDFIntegerRangeList irl=getPageSet();
        if(irl==null)
        {
            setPageSet(new JDFIntegerRangeList(new JDFIntegerRange(pageSet)));
        }
        else if(!irl.inRange(pageSet))
        {
            irl.append(pageSet);
            irl.normalize(true);
            setPageSet(irl);
        }
        
    }
}
