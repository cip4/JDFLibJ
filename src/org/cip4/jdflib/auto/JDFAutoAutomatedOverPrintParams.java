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
import org.cip4.jdflib.resource.JDFResource;

public abstract class JDFAutoAutomatedOverPrintParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.KNOCKOUTCMYKWHITE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.OVERPRINTBLACKTEXT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.OVERPRINTBLACKLINEART, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.TEXTBLACKLEVEL, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, "1");
        atrInfoTable[4] = new AtrInfoTable(AttributeName.LINEARTBLACKLEVEL, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.TEXTSIZETHRESHOLD, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoAutomatedOverPrintParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoAutomatedOverPrintParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoAutomatedOverPrintParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoAutomatedOverPrintParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoAutomatedOverPrintParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoAutomatedOverPrintParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoAutomatedOverPrintParams[  --> " + super.toString() + " ]";
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
        Methods for Attribute KnockOutCMYKWhite
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute KnockOutCMYKWhite
          * @param value: the value to set the attribute to
          */
        public void setKnockOutCMYKWhite(boolean value)
        {
            setAttribute(AttributeName.KNOCKOUTCMYKWHITE, value, null);
        }

        /**
          * (18) get boolean attribute KnockOutCMYKWhite
          * @return boolean the value of the attribute
          */
        public boolean getKnockOutCMYKWhite()
        {
            return getBoolAttribute(AttributeName.KNOCKOUTCMYKWHITE, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OverPrintBlackText
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OverPrintBlackText
          * @param value: the value to set the attribute to
          */
        public void setOverPrintBlackText(boolean value)
        {
            setAttribute(AttributeName.OVERPRINTBLACKTEXT, value, null);
        }

        /**
          * (18) get boolean attribute OverPrintBlackText
          * @return boolean the value of the attribute
          */
        public boolean getOverPrintBlackText()
        {
            return getBoolAttribute(AttributeName.OVERPRINTBLACKTEXT, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OverPrintBlackLineArt
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OverPrintBlackLineArt
          * @param value: the value to set the attribute to
          */
        public void setOverPrintBlackLineArt(boolean value)
        {
            setAttribute(AttributeName.OVERPRINTBLACKLINEART, value, null);
        }

        /**
          * (18) get boolean attribute OverPrintBlackLineArt
          * @return boolean the value of the attribute
          */
        public boolean getOverPrintBlackLineArt()
        {
            return getBoolAttribute(AttributeName.OVERPRINTBLACKLINEART, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TextBlackLevel
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TextBlackLevel
          * @param value: the value to set the attribute to
          */
        public void setTextBlackLevel(double value)
        {
            setAttribute(AttributeName.TEXTBLACKLEVEL, value, null);
        }

        /**
          * (17) get double attribute TextBlackLevel
          * @return double the value of the attribute
          */
        public double getTextBlackLevel()
        {
            return getRealAttribute(AttributeName.TEXTBLACKLEVEL, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute LineArtBlackLevel
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LineArtBlackLevel
          * @param value: the value to set the attribute to
          */
        public void setLineArtBlackLevel(double value)
        {
            setAttribute(AttributeName.LINEARTBLACKLEVEL, value, null);
        }

        /**
          * (17) get double attribute LineArtBlackLevel
          * @return double the value of the attribute
          */
        public double getLineArtBlackLevel()
        {
            return getRealAttribute(AttributeName.LINEARTBLACKLEVEL, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TextSizeThreshold
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TextSizeThreshold
          * @param value: the value to set the attribute to
          */
        public void setTextSizeThreshold(int value)
        {
            setAttribute(AttributeName.TEXTSIZETHRESHOLD, value, null);
        }

        /**
          * (15) get int attribute TextSizeThreshold
          * @return int the value of the attribute
          */
        public int getTextSizeThreshold()
        {
            return getIntAttribute(AttributeName.TEXTSIZETHRESHOLD, null, 0);
        }

}// end namespace JDF
