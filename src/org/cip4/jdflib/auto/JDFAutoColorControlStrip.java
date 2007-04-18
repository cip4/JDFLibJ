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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFCIELABMeasuringField;
import org.cip4.jdflib.resource.process.JDFDensityMeasuringField;
    /*
    *****************************************************************************
    class JDFAutoColorControlStrip : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoColorControlStrip extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CENTER, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.SIZE, 0x22222222, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ROTATION, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.STRIPTYPE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.CIELABMEASURINGFIELD, 0x33333331);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DENSITYMEASURINGFIELD, 0x33333331);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoColorControlStrip
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoColorControlStrip(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorControlStrip
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoColorControlStrip(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorControlStrip
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoColorControlStrip(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoColorControlStrip[  --> " + super.toString() + " ]";
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
        Methods for Attribute Center
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Center
          * @param value: the value to set the attribute to
          */
        public void setCenter(JDFXYPair value)
        {
            setAttribute(AttributeName.CENTER, value, null);
        }

        /**
          * (20) get JDFXYPair attribute Center
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getCenter()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CENTER, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Size
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Size
          * @param value: the value to set the attribute to
          */
        public void setSize(JDFXYPair value)
        {
            setAttribute(AttributeName.SIZE, value, null);
        }

        /**
          * (20) get JDFXYPair attribute Size
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getSize()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.SIZE, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Rotation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Rotation
          * @param value: the value to set the attribute to
          */
        public void setRotation(double value)
        {
            setAttribute(AttributeName.ROTATION, value, null);
        }

        /**
          * (17) get double attribute Rotation
          * @return double the value of the attribute
          */
        public double getRotation()
        {
            return getRealAttribute(AttributeName.ROTATION, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StripType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StripType
          * @param value: the value to set the attribute to
          */
        public void setStripType(String value)
        {
            setAttribute(AttributeName.STRIPTYPE, value, null);
        }

        /**
          * (23) get String attribute StripType
          * @return the value of the attribute
          */
        public String getStripType()
        {
            return getAttribute(AttributeName.STRIPTYPE, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateCIELABMeasuringField
     * 
     * @param iSkip number of elements to skip
     * @return JDFCIELABMeasuringField the element
     */
    public JDFCIELABMeasuringField getCreateCIELABMeasuringField(int iSkip)
    {
        return (JDFCIELABMeasuringField)getCreateElement_KElement(ElementName.CIELABMEASURINGFIELD, null, iSkip);
    }

    /**
     * (27) const get element CIELABMeasuringField
     * @param iSkip number of elements to skip
     * @return JDFCIELABMeasuringField the element
     * default is getCIELABMeasuringField(0)     */
    public JDFCIELABMeasuringField getCIELABMeasuringField(int iSkip)
    {
        return (JDFCIELABMeasuringField) getElement(ElementName.CIELABMEASURINGFIELD, null, iSkip);
    }

    /**
     * (30) append element CIELABMeasuringField
     */
    public JDFCIELABMeasuringField appendCIELABMeasuringField() throws JDFException
    {
        return (JDFCIELABMeasuringField) appendElement(ElementName.CIELABMEASURINGFIELD, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refCIELABMeasuringField(JDFCIELABMeasuringField refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateDensityMeasuringField
     * 
     * @param iSkip number of elements to skip
     * @return JDFDensityMeasuringField the element
     */
    public JDFDensityMeasuringField getCreateDensityMeasuringField(int iSkip)
    {
        return (JDFDensityMeasuringField)getCreateElement_KElement(ElementName.DENSITYMEASURINGFIELD, null, iSkip);
    }

    /**
     * (27) const get element DensityMeasuringField
     * @param iSkip number of elements to skip
     * @return JDFDensityMeasuringField the element
     * default is getDensityMeasuringField(0)     */
    public JDFDensityMeasuringField getDensityMeasuringField(int iSkip)
    {
        return (JDFDensityMeasuringField) getElement(ElementName.DENSITYMEASURINGFIELD, null, iSkip);
    }

    /**
     * (30) append element DensityMeasuringField
     */
    public JDFDensityMeasuringField appendDensityMeasuringField() throws JDFException
    {
        return (JDFDensityMeasuringField) appendElement(ElementName.DENSITYMEASURINGFIELD, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refDensityMeasuringField(JDFDensityMeasuringField refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
