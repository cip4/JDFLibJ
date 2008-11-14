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
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.datatypes.*;                 
import org.cip4.jdflib.resource.*;

public abstract class JDFAutoChannelBindingParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CLAMPSYSTEM, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.BRAND, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.CLAMPCOLOR, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.CLAMPCOLORDETAILS, 0x33331111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.CLAMPD, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.CLAMPSIZE, 0x33333333, AttributeInfo.EnumAttributeType.shape, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoChannelBindingParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoChannelBindingParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoChannelBindingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoChannelBindingParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoChannelBindingParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoChannelBindingParams(
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
        return " JDFAutoChannelBindingParams[  --> " + super.toString() + " ]";
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


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ClampSystem
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClampSystem
          * @param value: the value to set the attribute to
          */
        public void setClampSystem(boolean value)
        {
            setAttribute(AttributeName.CLAMPSYSTEM, value, null);
        }

        /**
          * (18) get boolean attribute ClampSystem
          * @return boolean the value of the attribute
          */
        public boolean getClampSystem()
        {
            return getBoolAttribute(AttributeName.CLAMPSYSTEM, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Brand
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Brand
          * @param value: the value to set the attribute to
          */
        @Override
		public void setBrand(String value)
        {
            setAttribute(AttributeName.BRAND, value, null);
        }

        /**
          * (23) get String attribute Brand
          * @return the value of the attribute
          */
        @Override
		public String getBrand()
        {
            return getAttribute(AttributeName.BRAND, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ClampColor
        --------------------------------------------------------------------- */
        /**
          * (13) set attribute ClampColor
          * @param value: the value to set the attribute to
          */
        public void setClampColor(EnumNamedColor value)
        {
            setAttribute(AttributeName.CLAMPCOLOR, value==null ? null : value.getName(), null);
        }

        /**
          * (19) get EnumNamedColor attribute ClampColor
          * @return EnumNamedColor the value of the attribute
          */
        public EnumNamedColor getClampColor()
        {
            String strAttrName = "";
            EnumNamedColor nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CLAMPCOLOR, null, JDFConstants.EMPTYSTRING);
            nPlaceHolder = EnumNamedColor.getEnum(strAttrName);
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ClampColorDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClampColorDetails
          * @param value: the value to set the attribute to
          */
        public void setClampColorDetails(String value)
        {
            setAttribute(AttributeName.CLAMPCOLORDETAILS, value, null);
        }

        /**
          * (23) get String attribute ClampColorDetails
          * @return the value of the attribute
          */
        public String getClampColorDetails()
        {
            return getAttribute(AttributeName.CLAMPCOLORDETAILS, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ClampD
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClampD
          * @param value: the value to set the attribute to
          */
        public void setClampD(double value)
        {
            setAttribute(AttributeName.CLAMPD, value, null);
        }

        /**
          * (17) get double attribute ClampD
          * @return double the value of the attribute
          */
        public double getClampD()
        {
            return getRealAttribute(AttributeName.CLAMPD, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ClampSize
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClampSize
          * @param value: the value to set the attribute to
          */
        public void setClampSize(JDFShape value)
        {
            setAttribute(AttributeName.CLAMPSIZE, value, null);
        }

        /**
          * (20) get JDFShape attribute ClampSize
          * @return JDFShape the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFShape
          */
        public JDFShape getClampSize()
        {
            String strAttrName = "";
            JDFShape nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.CLAMPSIZE, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFShape(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

}// end namespace JDF
