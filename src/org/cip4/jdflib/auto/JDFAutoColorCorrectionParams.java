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
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.prepress.JDFColorCorrectionOp;

public abstract class JDFAutoColorCorrectionParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.COLORMANAGEMENTSYSTEM, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x66666666);
        elemInfoTable[1] = new ElemInfoTable(ElementName.COLORCORRECTIONOP, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoColorCorrectionParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoColorCorrectionParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorCorrectionParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoColorCorrectionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorCorrectionParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoColorCorrectionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoColorCorrectionParams[  --> " + super.toString() + " ]";
    }


    public boolean  init()
    {
        boolean bRet = super.init();
        setResourceClass(JDFResource.EnumResourceClass.Parameter);
        return bRet;
    }


    public EnumResourceClass getValidClass()
    {
        return JDFResource.EnumResourceClass.Parameter;
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ColorManagementSystem
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ColorManagementSystem
          * @param value: the value to set the attribute to
          */
        public void setColorManagementSystem(String value)
        {
            setAttribute(AttributeName.COLORMANAGEMENTSYSTEM, value, null);
        }

        /**
          * (23) get String attribute ColorManagementSystem
          * @return the value of the attribute
          */
        public String getColorManagementSystem()
        {
            return getAttribute(AttributeName.COLORMANAGEMENTSYSTEM, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element FileSpec
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getFileSpec()
    {
        return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
    }

    /** (25) getCreateFileSpec
     * 
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getCreateFileSpec()
    {
        return (JDFFileSpec) getCreateElement_KElement(ElementName.FILESPEC, null, 0);
    }

    /**
     * (29) append element FileSpec
     */
    public JDFFileSpec appendFileSpec() throws JDFException
    {
        return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refFileSpec(JDFFileSpec refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateColorCorrectionOp
     * 
     * @param iSkip number of elements to skip
     * @return JDFColorCorrectionOp the element
     */
    public JDFColorCorrectionOp getCreateColorCorrectionOp(int iSkip)
    {
        return (JDFColorCorrectionOp)getCreateElement_KElement(ElementName.COLORCORRECTIONOP, null, iSkip);
    }

    /**
     * (27) const get element ColorCorrectionOp
     * @param iSkip number of elements to skip
     * @return JDFColorCorrectionOp the element
     * default is getColorCorrectionOp(0)     */
    public JDFColorCorrectionOp getColorCorrectionOp(int iSkip)
    {
        return (JDFColorCorrectionOp) getElement(ElementName.COLORCORRECTIONOP, null, iSkip);
    }

    /**
     * Get all ColorCorrectionOp from the current element
     * 
     * @return Collection<JDFColorCorrectionOp>
     */
    public Collection<JDFColorCorrectionOp> getAllColorCorrectionOp()
    {
        Vector<JDFColorCorrectionOp> v = new Vector<JDFColorCorrectionOp>();

        JDFColorCorrectionOp kElem = (JDFColorCorrectionOp) getFirstChildElement(ElementName.COLORCORRECTIONOP, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFColorCorrectionOp) kElem.getNextSiblingElement(ElementName.COLORCORRECTIONOP, null);
        }

        return v;
    }

    /**
     * (30) append element ColorCorrectionOp
     */
    public JDFColorCorrectionOp appendColorCorrectionOp() throws JDFException
    {
        return (JDFColorCorrectionOp) appendElement(ElementName.COLORCORRECTIONOP, null);
    }

}// end namespace JDF
