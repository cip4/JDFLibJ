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

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFSpanCutType;
import org.cip4.jdflib.span.JDFSpanShapeType;
import org.cip4.jdflib.span.JDFStringSpan;

public abstract class JDFAutoShapeCut extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CUTBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.CUTOUT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.CUTPATH, 0x33333333, AttributeInfo.EnumAttributeType.PDFPath, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.PAGES, 0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.MATERIAL, 0x66666666);
        elemInfoTable[1] = new ElemInfoTable(ElementName.CUTTYPE, 0x66666666);
        elemInfoTable[2] = new ElemInfoTable(ElementName.SHAPEDEPTH, 0x66666661);
        elemInfoTable[3] = new ElemInfoTable(ElementName.SHAPETYPE, 0x55555555);
        elemInfoTable[4] = new ElemInfoTable(ElementName.TEETHPERDIMENSION, 0x66666666);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoShapeCut
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoShapeCut(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoShapeCut
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoShapeCut(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoShapeCut
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoShapeCut(
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
        return " JDFAutoShapeCut[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute CutBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CutBox
          * @param value: the value to set the attribute to
          */
        public void setCutBox(JDFRectangle value)
        {
            setAttribute(AttributeName.CUTBOX, value, null);
        }

        /**
          * (20) get JDFRectangle attribute CutBox
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getCutBox()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CUTBOX, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFRectangle(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CutOut
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CutOut
          * @param value: the value to set the attribute to
          */
        public void setCutOut(boolean value)
        {
            setAttribute(AttributeName.CUTOUT, value, null);
        }

        /**
          * (18) get boolean attribute CutOut
          * @return boolean the value of the attribute
          */
        public boolean getCutOut()
        {
            return getBoolAttribute(AttributeName.CUTOUT, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute CutPath
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CutPath
          * @param value: the value to set the attribute to
          */
        public void setCutPath(String value)
        {
            setAttribute(AttributeName.CUTPATH, value, null);
        }

        /**
          * (23) get String attribute CutPath
          * @return the value of the attribute
          */
        public String getCutPath()
        {
            return getAttribute(AttributeName.CUTPATH, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Pages
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Pages
          * @param value: the value to set the attribute to
          */
        public void setPages(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.PAGES, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute Pages
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getPages()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.PAGES, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Material
     * @return JDFStringSpan the element
     */
    public JDFStringSpan getMaterial()
    {
        return (JDFStringSpan) getElement(ElementName.MATERIAL, null, 0);
    }

    /** (25) getCreateMaterial
     * 
     * @return JDFStringSpan the element
     */
    public JDFStringSpan getCreateMaterial()
    {
        return (JDFStringSpan) getCreateElement_KElement(ElementName.MATERIAL, null, 0);
    }

    /**
     * (29) append element Material
     */
    public JDFStringSpan appendMaterial() throws JDFException
    {
        return (JDFStringSpan) appendElementN(ElementName.MATERIAL, 1, null);
    }

    /**
     * (24) const get element CutType
     * @return JDFSpanCutType the element
     */
    public JDFSpanCutType getCutType()
    {
        return (JDFSpanCutType) getElement(ElementName.CUTTYPE, null, 0);
    }

    /** (25) getCreateCutType
     * 
     * @return JDFSpanCutType the element
     */
    public JDFSpanCutType getCreateCutType()
    {
        return (JDFSpanCutType) getCreateElement_KElement(ElementName.CUTTYPE, null, 0);
    }

    /**
     * (29) append element CutType
     */
    public JDFSpanCutType appendCutType() throws JDFException
    {
        return (JDFSpanCutType) appendElementN(ElementName.CUTTYPE, 1, null);
    }

    /**
     * (24) const get element ShapeDepth
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getShapeDepth()
    {
        return (JDFNumberSpan) getElement(ElementName.SHAPEDEPTH, null, 0);
    }

    /** (25) getCreateShapeDepth
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateShapeDepth()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.SHAPEDEPTH, null, 0);
    }

    /**
     * (29) append element ShapeDepth
     */
    public JDFNumberSpan appendShapeDepth() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.SHAPEDEPTH, 1, null);
    }

    /**
     * (24) const get element ShapeType
     * @return JDFSpanShapeType the element
     */
    public JDFSpanShapeType getShapeType()
    {
        return (JDFSpanShapeType) getElement(ElementName.SHAPETYPE, null, 0);
    }

    /** (25) getCreateShapeType
     * 
     * @return JDFSpanShapeType the element
     */
    public JDFSpanShapeType getCreateShapeType()
    {
        return (JDFSpanShapeType) getCreateElement_KElement(ElementName.SHAPETYPE, null, 0);
    }

    /**
     * (29) append element ShapeType
     */
    public JDFSpanShapeType appendShapeType() throws JDFException
    {
        return (JDFSpanShapeType) appendElementN(ElementName.SHAPETYPE, 1, null);
    }

    /**
     * (24) const get element TeethPerDimension
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getTeethPerDimension()
    {
        return (JDFNumberSpan) getElement(ElementName.TEETHPERDIMENSION, null, 0);
    }

    /** (25) getCreateTeethPerDimension
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateTeethPerDimension()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.TEETHPERDIMENSION, null, 0);
    }

    /**
     * (29) append element TeethPerDimension
     */
    public JDFNumberSpan appendTeethPerDimension() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.TEETHPERDIMENSION, 1, null);
    }

}// end namespace JDF
