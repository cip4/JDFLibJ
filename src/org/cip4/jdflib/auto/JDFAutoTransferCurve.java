/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.resource.JDFResource;
    /**
    *****************************************************************************
    class JDFAutoTransferCurve : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoTransferCurve extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[12];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CURVE, 0x22222222, AttributeInfo.EnumAttributeType.TransferFunction, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.SEPARATION, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.METADATA0, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.METADATA1, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.METADATA2, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.METADATA3, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.METADATA4, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.METADATA5, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.METADATA6, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.METADATA7, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.METADATA8, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.METADATA9, 0x33333333, AttributeInfo.EnumAttributeType.NameRangeList, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoTransferCurve
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoTransferCurve(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoTransferCurve
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoTransferCurve(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoTransferCurve
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoTransferCurve(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoTransferCurve[  --> " + super.toString() + " ]";
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
        Methods for Attribute Curve
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Curve
          * @param value: the value to set the attribute to
          */
        public void setCurve(JDFTransferFunction value)
        {
            setAttribute(AttributeName.CURVE, value, null);
        }

        /**
          * (20) get JDFTransferFunction attribute Curve
          * @return JDFTransferFunction the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFTransferFunction
          */
        public JDFTransferFunction getCurve()
        {
            String strAttrName = "";
            JDFTransferFunction nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CURVE, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFTransferFunction(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Separation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Separation
          * @param value: the value to set the attribute to
          */
        public void setSeparation(String value)
        {
            setAttribute(AttributeName.SEPARATION, value, null);
        }

        /**
          * (23) get String attribute Separation
          * @return the value of the attribute
          */
        public String getSeparation()
        {
            return getAttribute(AttributeName.SEPARATION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Metadata0
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Metadata0
          * @param value: the value to set the attribute to
          */
        public void setMetadata0(JDFNameRangeList value)
        {
            setAttribute(AttributeName.METADATA0, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute Metadata0
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getMetadata0()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.METADATA0, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Metadata1
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Metadata1
          * @param value: the value to set the attribute to
          */
        public void setMetadata1(JDFNameRangeList value)
        {
            setAttribute(AttributeName.METADATA1, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute Metadata1
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getMetadata1()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.METADATA1, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Metadata2
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Metadata2
          * @param value: the value to set the attribute to
          */
        public void setMetadata2(JDFNameRangeList value)
        {
            setAttribute(AttributeName.METADATA2, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute Metadata2
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getMetadata2()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.METADATA2, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Metadata3
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Metadata3
          * @param value: the value to set the attribute to
          */
        public void setMetadata3(JDFNameRangeList value)
        {
            setAttribute(AttributeName.METADATA3, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute Metadata3
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getMetadata3()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.METADATA3, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Metadata4
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Metadata4
          * @param value: the value to set the attribute to
          */
        public void setMetadata4(JDFNameRangeList value)
        {
            setAttribute(AttributeName.METADATA4, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute Metadata4
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getMetadata4()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.METADATA4, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Metadata5
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Metadata5
          * @param value: the value to set the attribute to
          */
        public void setMetadata5(JDFNameRangeList value)
        {
            setAttribute(AttributeName.METADATA5, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute Metadata5
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getMetadata5()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.METADATA5, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Metadata6
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Metadata6
          * @param value: the value to set the attribute to
          */
        public void setMetadata6(JDFNameRangeList value)
        {
            setAttribute(AttributeName.METADATA6, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute Metadata6
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getMetadata6()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.METADATA6, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Metadata7
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Metadata7
          * @param value: the value to set the attribute to
          */
        public void setMetadata7(JDFNameRangeList value)
        {
            setAttribute(AttributeName.METADATA7, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute Metadata7
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getMetadata7()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.METADATA7, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Metadata8
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Metadata8
          * @param value: the value to set the attribute to
          */
        public void setMetadata8(JDFNameRangeList value)
        {
            setAttribute(AttributeName.METADATA8, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute Metadata8
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getMetadata8()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.METADATA8, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Metadata9
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Metadata9
          * @param value: the value to set the attribute to
          */
        public void setMetadata9(JDFNameRangeList value)
        {
            setAttribute(AttributeName.METADATA9, value, null);
        }

        /**
          * (20) get JDFNameRangeList attribute Metadata9
          * @return JDFNameRangeList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNameRangeList
          */
        public JDFNameRangeList getMetadata9()
        {
            String strAttrName = "";
            JDFNameRangeList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.METADATA9, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNameRangeList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

}// end namespace JDF
