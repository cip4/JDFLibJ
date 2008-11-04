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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFHoleLine;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.postpress.JDFHole;

public abstract class JDFAutoHoleList extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.HOLE, 0x33333331);
        elemInfoTable[1] = new ElemInfoTable(ElementName.HOLELINE, 0x33333331);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoHoleList
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoHoleList(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoHoleList
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoHoleList(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoHoleList
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoHoleList(
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
        return " JDFAutoHoleList[  --> " + super.toString() + " ]";
    }


    @Override
	public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Parameter);
        return bRet;
    }


    @Override
	public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Parameter;
    }


/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateHole
     * 
     * @param iSkip number of elements to skip
     * @return JDFHole the element
     */
    public JDFHole getCreateHole(int iSkip)
    {
        return (JDFHole)getCreateElement_KElement(ElementName.HOLE, null, iSkip);
    }

    /**
     * (27) const get element Hole
     * @param iSkip number of elements to skip
     * @return JDFHole the element
     * default is getHole(0)     */
    public JDFHole getHole(int iSkip)
    {
        return (JDFHole) getElement(ElementName.HOLE, null, iSkip);
    }

    /**
     * Get all Hole from the current element
     * 
     * @return Collection<JDFHole>
     */
    public Collection<JDFHole> getAllHole()
    {
        Vector<JDFHole> v = new Vector<JDFHole>();

        JDFHole kElem = (JDFHole) getFirstChildElement(ElementName.HOLE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFHole) kElem.getNextSiblingElement(ElementName.HOLE, null);
        }

        return v;
    }

    /**
     * (30) append element Hole
     */
    public JDFHole appendHole() throws JDFException
    {
        return (JDFHole) appendElement(ElementName.HOLE, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refHole(JDFHole refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateHoleLine
     * 
     * @param iSkip number of elements to skip
     * @return JDFHoleLine the element
     */
    public JDFHoleLine getCreateHoleLine(int iSkip)
    {
        return (JDFHoleLine)getCreateElement_KElement(ElementName.HOLELINE, null, iSkip);
    }

    /**
     * (27) const get element HoleLine
     * @param iSkip number of elements to skip
     * @return JDFHoleLine the element
     * default is getHoleLine(0)     */
    public JDFHoleLine getHoleLine(int iSkip)
    {
        return (JDFHoleLine) getElement(ElementName.HOLELINE, null, iSkip);
    }

    /**
     * Get all HoleLine from the current element
     * 
     * @return Collection<JDFHoleLine>
     */
    public Collection<JDFHoleLine> getAllHoleLine()
    {
        Vector<JDFHoleLine> v = new Vector<JDFHoleLine>();

        JDFHoleLine kElem = (JDFHoleLine) getFirstChildElement(ElementName.HOLELINE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFHoleLine) kElem.getNextSiblingElement(ElementName.HOLELINE, null);
        }

        return v;
    }

    /**
     * (30) append element HoleLine
     */
    public JDFHoleLine appendHoleLine() throws JDFException
    {
        return (JDFHoleLine) appendElement(ElementName.HOLELINE, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refHoleLine(JDFHoleLine refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
