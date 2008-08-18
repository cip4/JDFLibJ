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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFSpanNamedColor;

public abstract class JDFAutoNumberItem extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.STARTVALUE, 0x33333333, AttributeInfo.EnumAttributeType.string, null, "1");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.STEP, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, "1");
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COLORNAME, 0x66666666);
        elemInfoTable[1] = new ElemInfoTable(ElementName.XPOSITION, 0x66666666);
        elemInfoTable[2] = new ElemInfoTable(ElementName.YPOSITION, 0x66666666);
        elemInfoTable[3] = new ElemInfoTable(ElementName.ORIENTATION, 0x66666666);
        elemInfoTable[4] = new ElemInfoTable(ElementName.SEPARATIONSPEC, 0x66666666);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoNumberItem
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoNumberItem(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoNumberItem
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoNumberItem(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoNumberItem
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoNumberItem(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoNumberItem[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute StartValue
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StartValue
          * @param value: the value to set the attribute to
          */
        public void setStartValue(String value)
        {
            setAttribute(AttributeName.STARTVALUE, value, null);
        }

        /**
          * (23) get String attribute StartValue
          * @return the value of the attribute
          */
        public String getStartValue()
        {
            return getAttribute(AttributeName.STARTVALUE, null, "1");
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Step
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Step
          * @param value: the value to set the attribute to
          */
        public void setStep(int value)
        {
            setAttribute(AttributeName.STEP, value, null);
        }

        /**
          * (15) get int attribute Step
          * @return int the value of the attribute
          */
        public int getStep()
        {
            return getIntAttribute(AttributeName.STEP, null, 1);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element ColorName
     * @return JDFSpanNamedColor the element
     */
    public JDFSpanNamedColor getColorName()
    {
        return (JDFSpanNamedColor) getElement(ElementName.COLORNAME, null, 0);
    }

    /** (25) getCreateColorName
     * 
     * @return JDFSpanNamedColor the element
     */
    public JDFSpanNamedColor getCreateColorName()
    {
        return (JDFSpanNamedColor) getCreateElement_KElement(ElementName.COLORNAME, null, 0);
    }

    /**
     * (29) append element ColorName
     */
    public JDFSpanNamedColor appendColorName() throws JDFException
    {
        return (JDFSpanNamedColor) appendElementN(ElementName.COLORNAME, 1, null);
    }

    /**
     * (24) const get element XPosition
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getXPosition()
    {
        return (JDFNumberSpan) getElement(ElementName.XPOSITION, null, 0);
    }

    /** (25) getCreateXPosition
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateXPosition()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.XPOSITION, null, 0);
    }

    /**
     * (29) append element XPosition
     */
    public JDFNumberSpan appendXPosition() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.XPOSITION, 1, null);
    }

    /**
     * (24) const get element YPosition
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getYPosition()
    {
        return (JDFNumberSpan) getElement(ElementName.YPOSITION, null, 0);
    }

    /** (25) getCreateYPosition
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateYPosition()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.YPOSITION, null, 0);
    }

    /**
     * (29) append element YPosition
     */
    public JDFNumberSpan appendYPosition() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.YPOSITION, 1, null);
    }

    /**
     * (24) const get element Orientation
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getOrientation()
    {
        return (JDFNumberSpan) getElement(ElementName.ORIENTATION, null, 0);
    }

    /** (25) getCreateOrientation
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateOrientation()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.ORIENTATION, null, 0);
    }

    /**
     * (29) append element Orientation
     */
    public JDFNumberSpan appendOrientation() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.ORIENTATION, 1, null);
    }

    /**
     * (24) const get element SeparationSpec
     * @return JDFSeparationSpec the element
     */
    public JDFSeparationSpec getSeparationSpec()
    {
        return (JDFSeparationSpec) getElement(ElementName.SEPARATIONSPEC, null, 0);
    }

    /** (25) getCreateSeparationSpec
     * 
     * @return JDFSeparationSpec the element
     */
    public JDFSeparationSpec getCreateSeparationSpec()
    {
        return (JDFSeparationSpec) getCreateElement_KElement(ElementName.SEPARATIONSPEC, null, 0);
    }

    /**
     * (29) append element SeparationSpec
     */
    public JDFSeparationSpec appendSeparationSpec() throws JDFException
    {
        return (JDFSeparationSpec) appendElementN(ElementName.SEPARATIONSPEC, 1, null);
    }

}// end namespace JDF
