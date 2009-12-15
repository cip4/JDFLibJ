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
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.process.postpress.JDFGlueLine;
import org.cip4.jdflib.span.JDFSpanGlueType;
import org.cip4.jdflib.span.JDFSpanMethod;

public abstract class JDFAutoInsert extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.FOLIO, 0x22222222, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.SHEETOFFSET, 0x44444443, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.TRANSFORMATION, 0x33333333, AttributeInfo.EnumAttributeType.matrix, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.WRAPPAGES, 0x33333331, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.GLUETYPE, 0x66666666);
        elemInfoTable[1] = new ElemInfoTable(ElementName.METHOD, 0x66666666);
        elemInfoTable[2] = new ElemInfoTable(ElementName.GLUELINE, 0x33333331);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoInsert
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoInsert(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoInsert
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoInsert(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoInsert
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoInsert(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoInsert[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Folio
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Folio
          * @param value: the value to set the attribute to
          */
        public void setFolio(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.FOLIO, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute Folio
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getFolio()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.FOLIO, null, JDFConstants.EMPTYSTRING);
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

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SheetOffset
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SheetOffset
          * @param value: the value to set the attribute to
          */
        public void setSheetOffset(JDFXYPair value)
        {
            setAttribute(AttributeName.SHEETOFFSET, value, null);
        }

        /**
          * (20) get JDFXYPair attribute SheetOffset
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getSheetOffset()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SHEETOFFSET, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFXYPair(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Transformation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Transformation
          * @param value: the value to set the attribute to
          */
        public void setTransformation(JDFMatrix value)
        {
            setAttribute(AttributeName.TRANSFORMATION, value, null);
        }

        /**
          * (20) get JDFMatrix attribute Transformation
          * @return JDFMatrix the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFMatrix
          */
        public JDFMatrix getTransformation()
        {
            String strAttrName = "";
            JDFMatrix nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.TRANSFORMATION, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFMatrix(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute WrapPages
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute WrapPages
          * @param value: the value to set the attribute to
          */
        public void setWrapPages(JDFIntegerRangeList value)
        {
            setAttribute(AttributeName.WRAPPAGES, value, null);
        }

        /**
          * (20) get JDFIntegerRangeList attribute WrapPages
          * @return JDFIntegerRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerRangeList
          */
        public JDFIntegerRangeList getWrapPages()
        {
            String strAttrName = "";
            JDFIntegerRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.WRAPPAGES, null, JDFConstants.EMPTYSTRING);
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
     * (24) const get element GlueType
     * @return JDFSpanGlueType the element
     */
    public JDFSpanGlueType getGlueType()
    {
        return (JDFSpanGlueType) getElement(ElementName.GLUETYPE, null, 0);
    }

    /** (25) getCreateGlueType
     * 
     * @return JDFSpanGlueType the element
     */
    public JDFSpanGlueType getCreateGlueType()
    {
        return (JDFSpanGlueType) getCreateElement_KElement(ElementName.GLUETYPE, null, 0);
    }

    /**
     * (29) append element GlueType
     */
    public JDFSpanGlueType appendGlueType() throws JDFException
    {
        return (JDFSpanGlueType) appendElementN(ElementName.GLUETYPE, 1, null);
    }

    /**
     * (24) const get element Method
     * @return JDFSpanMethod the element
     */
    public JDFSpanMethod getMethod()
    {
        return (JDFSpanMethod) getElement(ElementName.METHOD, null, 0);
    }

    /** (25) getCreateMethod
     * 
     * @return JDFSpanMethod the element
     */
    public JDFSpanMethod getCreateMethod()
    {
        return (JDFSpanMethod) getCreateElement_KElement(ElementName.METHOD, null, 0);
    }

    /**
     * (29) append element Method
     */
    public JDFSpanMethod appendMethod() throws JDFException
    {
        return (JDFSpanMethod) appendElementN(ElementName.METHOD, 1, null);
    }

    /** (26) getCreateGlueLine
     * 
     * @param iSkip number of elements to skip
     * @return JDFGlueLine the element
     */
    public JDFGlueLine getCreateGlueLine(int iSkip)
    {
        return (JDFGlueLine)getCreateElement_KElement(ElementName.GLUELINE, null, iSkip);
    }

    /**
     * (27) const get element GlueLine
     * @param iSkip number of elements to skip
     * @return JDFGlueLine the element
     * default is getGlueLine(0)     */
    public JDFGlueLine getGlueLine(int iSkip)
    {
        return (JDFGlueLine) getElement(ElementName.GLUELINE, null, iSkip);
    }

    /**
     * Get all GlueLine from the current element
     * 
     * @return Collection<JDFGlueLine>, null if none are available
     */
    public Collection<JDFGlueLine> getAllGlueLine()
    {
        final VElement vc = getChildElementVector(ElementName.GLUELINE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFGlueLine> v = new Vector<JDFGlueLine>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFGlueLine) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element GlueLine
     */
    public JDFGlueLine appendGlueLine() throws JDFException
    {
        return (JDFGlueLine) appendElement(ElementName.GLUELINE, null);
    }

}// end namespace JDF
