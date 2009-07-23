/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2005 The International Cooperation for the Integration of
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

package org.cip4.jdflib.auto;

import java.util.Collection;                        
import java.util.Vector;                            
import org.apache.xerces.dom.CoreDocumentImpl;      
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.resource.process.*;

public abstract class JDFAutoPreflightArgument extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.BOXARGUMENT, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.BOXTOBOXDIFFERENCE, 0x33333333);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPreflightArgument
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPreflightArgument(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreflightArgument
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPreflightArgument(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPreflightArgument
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPreflightArgument(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    @Override
	public String toString()
    {
        return " JDFAutoPreflightArgument[  --> " + super.toString() + " ]";
    }


/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateBoxArgument
     * 
     * @param iSkip number of elements to skip
     * @return JDFBoxArgument the element
     */
    public JDFBoxArgument getCreateBoxArgument(int iSkip)
    {
        return (JDFBoxArgument)getCreateElement_KElement(ElementName.BOXARGUMENT, null, iSkip);
    }

    /**
     * (27) const get element BoxArgument
     * @param iSkip number of elements to skip
     * @return JDFBoxArgument the element
     * default is getBoxArgument(0)     */
    public JDFBoxArgument getBoxArgument(int iSkip)
    {
        return (JDFBoxArgument) getElement(ElementName.BOXARGUMENT, null, iSkip);
    }

    /**
     * Get all BoxArgument from the current element
     * 
     * @return Collection<JDFBoxArgument>, null if none are available
     */
    public Collection<JDFBoxArgument> getAllBoxArgument()
    {
        final VElement vc = getChildElementVector(ElementName.BOXARGUMENT, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFBoxArgument> v = new Vector<JDFBoxArgument>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFBoxArgument) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element BoxArgument
     */
    public JDFBoxArgument appendBoxArgument() throws JDFException
    {
        return (JDFBoxArgument) appendElement(ElementName.BOXARGUMENT, null);
    }

    /** (26) getCreateBoxToBoxDifference
     * 
     * @param iSkip number of elements to skip
     * @return JDFBoxToBoxDifference the element
     */
    public JDFBoxToBoxDifference getCreateBoxToBoxDifference(int iSkip)
    {
        return (JDFBoxToBoxDifference)getCreateElement_KElement(ElementName.BOXTOBOXDIFFERENCE, null, iSkip);
    }

    /**
     * (27) const get element BoxToBoxDifference
     * @param iSkip number of elements to skip
     * @return JDFBoxToBoxDifference the element
     * default is getBoxToBoxDifference(0)     */
    public JDFBoxToBoxDifference getBoxToBoxDifference(int iSkip)
    {
        return (JDFBoxToBoxDifference) getElement(ElementName.BOXTOBOXDIFFERENCE, null, iSkip);
    }

    /**
     * Get all BoxToBoxDifference from the current element
     * 
     * @return Collection<JDFBoxToBoxDifference>, null if none are available
     */
    public Collection<JDFBoxToBoxDifference> getAllBoxToBoxDifference()
    {
        final VElement vc = getChildElementVector(ElementName.BOXTOBOXDIFFERENCE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFBoxToBoxDifference> v = new Vector<JDFBoxToBoxDifference>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFBoxToBoxDifference) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element BoxToBoxDifference
     */
    public JDFBoxToBoxDifference appendBoxToBoxDifference() throws JDFException
    {
        return (JDFBoxToBoxDifference) appendElement(ElementName.BOXTOBOXDIFFERENCE, null);
    }

}// end namespace JDF
