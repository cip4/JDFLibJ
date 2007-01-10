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
*/

/**
 * @author Elena Skobchenko
 *
 * JDFand.java
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.w3c.dom.DOMException;

/**
 * 
 * @author prosirai
 *
 */
public class JDFand extends JDFNodeTerm
{
    private static final long serialVersionUID = 1L;


    /**
     * Constructor for JDFand
     * @param myOwnerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFand(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFand
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFand(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFand
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     * @throws DOMException
     */
    public JDFand(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFand[  --> " + super.toString() + " ]";
    }
    
    /**
     * Evaluates two or more Term elements (and, or, xor, not, Evaluation, TestRef) 
     * to determine if, as a set, they evaluate to “true” 
     * when combined in a boolean “and” function.
     * 
     * Looks for Evaluations, that “and” consists of, and tests the status of every Evaluation, 
     * till the result of a whole boolean expression will be determinated - tests if attribute map 'm' has a key,
     * specified by Evaluation/BasicPreflightTest/@Name and if 'm' has such key, checks
     * if its value fits testlists, specified for matching Evaluation (uses FitsValue(value))
     *
     * @param JDFAttributeMap m -  key-value pair attribute map
     * @return boolean - true, if boolean “and” expression evaluates to “true”
     */
    public boolean fitsMap(JDFAttributeMap m) 
    {
        VElement v = getTermVector(null);
        int siz = v.size();
        for (int i=0; i<siz; i++)
        {
            JDFTerm t = (JDFTerm)v.elementAt(i);
            if(!t.fitsMap(m))
                return false; 
        }
        return true;
    }

    /**
     * Evaluates two or more Term elements (and, or, xor, not, Evaluation, TestRef) 
     * to determine if, as a set, they evaluate to “true” 
     * when combined in a boolean “and” function.
     *
     * @param jdf - JDFNode we test to know if the Device can accept it 
     * @return boolean - true, if boolean “and” expression evaluates to “true”
     */
    public boolean fitsJDF(KElement jdf, KElement reportRoot) // const JDFNode
    {
        VElement v = getTermVector(null);
        if(reportRoot!=null)
            reportRoot=reportRoot.appendElement("and");
        int siz = v.size();
        boolean b=true;
        for (int i=0; i<siz; i++)
        {
            final JDFTerm t=(JDFTerm)v.elementAt(i);
            boolean b2=t.fitsJDF(jdf,reportRoot);
            if(!b2 )
            {
                if(reportRoot==null)
                    return false;
            }
            b=b&&b2;
        }
        if(reportRoot!=null)
            reportRoot.setAttribute("Value",b,null);
        return b;
    }

     
    /////////////////////////////////////////////////////////
    
    public VString getMissingElements(int nMax)
    {
        VString v=super.getMissingElements(nMax);
        if(v.size()>=nMax)
            return v;
        
        v.appendUnique(getMissingTerms(2));
        return v;        
     }
    
    /////////////////////////////////////////////////////////


}
