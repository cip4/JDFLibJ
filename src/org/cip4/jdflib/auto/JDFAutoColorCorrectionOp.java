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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.resource.process.JDFFileSpec;
    /*
    *****************************************************************************
    class JDFAutoColorCorrectionOp : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoColorCorrectionOp extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.SOURCEOBJECTS, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, EnumSourceObjects.getEnum(0), "All");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ADJUSTCYANRED, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ADJUSTMAGENTAGREEN, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.ADJUSTYELLOWBLUE, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.ADJUSTCONTRAST, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.ADJUSTHUE, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.ADJUSTLIGHTNESS, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.ADJUSTSATURATION, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x33333311);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoColorCorrectionOp
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoColorCorrectionOp(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorCorrectionOp
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoColorCorrectionOp(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoColorCorrectionOp
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoColorCorrectionOp(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoColorCorrectionOp[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for SourceObjects
        */

        public static class EnumSourceObjects extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumSourceObjects(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumSourceObjects getEnum(String enumName)
            {
                return (EnumSourceObjects) getEnum(EnumSourceObjects.class, enumName);
            }

            public static EnumSourceObjects getEnum(int enumValue)
            {
                return (EnumSourceObjects) getEnum(EnumSourceObjects.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumSourceObjects.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumSourceObjects.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumSourceObjects.class);
            }

            public static final EnumSourceObjects All = new EnumSourceObjects("All");
            public static final EnumSourceObjects ImagePhotographic = new EnumSourceObjects("ImagePhotographic");
            public static final EnumSourceObjects ImageScreenShot = new EnumSourceObjects("ImageScreenShot");
            public static final EnumSourceObjects Text = new EnumSourceObjects("Text");
            public static final EnumSourceObjects LineArt = new EnumSourceObjects("LineArt");
            public static final EnumSourceObjects SmoothShades = new EnumSourceObjects("SmoothShades");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute SourceObjects
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute SourceObjects
          * @param v vector of the enumeration values
          */
        public void setSourceObjects(Vector v)
        {
            setEnumerationsAttribute(AttributeName.SOURCEOBJECTS, v, null);
        }

        /**
          * (9.2) get SourceObjects attribute SourceObjects
          * @return Vector of the enumerations
          */
        public Vector getSourceObjects()
        {
            return getEnumerationsAttribute(AttributeName.SOURCEOBJECTS, null, EnumSourceObjects.All, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AdjustCyanRed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AdjustCyanRed
          * @param value: the value to set the attribute to
          */
        public void setAdjustCyanRed(double value)
        {
            setAttribute(AttributeName.ADJUSTCYANRED, value, null);
        }

        /**
          * (17) get double attribute AdjustCyanRed
          * @return double the value of the attribute
          */
        public double getAdjustCyanRed()
        {
            return getRealAttribute(AttributeName.ADJUSTCYANRED, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AdjustMagentaGreen
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AdjustMagentaGreen
          * @param value: the value to set the attribute to
          */
        public void setAdjustMagentaGreen(double value)
        {
            setAttribute(AttributeName.ADJUSTMAGENTAGREEN, value, null);
        }

        /**
          * (17) get double attribute AdjustMagentaGreen
          * @return double the value of the attribute
          */
        public double getAdjustMagentaGreen()
        {
            return getRealAttribute(AttributeName.ADJUSTMAGENTAGREEN, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AdjustYellowBlue
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AdjustYellowBlue
          * @param value: the value to set the attribute to
          */
        public void setAdjustYellowBlue(double value)
        {
            setAttribute(AttributeName.ADJUSTYELLOWBLUE, value, null);
        }

        /**
          * (17) get double attribute AdjustYellowBlue
          * @return double the value of the attribute
          */
        public double getAdjustYellowBlue()
        {
            return getRealAttribute(AttributeName.ADJUSTYELLOWBLUE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AdjustContrast
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AdjustContrast
          * @param value: the value to set the attribute to
          */
        public void setAdjustContrast(double value)
        {
            setAttribute(AttributeName.ADJUSTCONTRAST, value, null);
        }

        /**
          * (17) get double attribute AdjustContrast
          * @return double the value of the attribute
          */
        public double getAdjustContrast()
        {
            return getRealAttribute(AttributeName.ADJUSTCONTRAST, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AdjustHue
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AdjustHue
          * @param value: the value to set the attribute to
          */
        public void setAdjustHue(double value)
        {
            setAttribute(AttributeName.ADJUSTHUE, value, null);
        }

        /**
          * (17) get double attribute AdjustHue
          * @return double the value of the attribute
          */
        public double getAdjustHue()
        {
            return getRealAttribute(AttributeName.ADJUSTHUE, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AdjustLightness
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AdjustLightness
          * @param value: the value to set the attribute to
          */
        public void setAdjustLightness(double value)
        {
            setAttribute(AttributeName.ADJUSTLIGHTNESS, value, null);
        }

        /**
          * (17) get double attribute AdjustLightness
          * @return double the value of the attribute
          */
        public double getAdjustLightness()
        {
            return getRealAttribute(AttributeName.ADJUSTLIGHTNESS, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AdjustSaturation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AdjustSaturation
          * @param value: the value to set the attribute to
          */
        public void setAdjustSaturation(double value)
        {
            setAttribute(AttributeName.ADJUSTSATURATION, value, null);
        }

        /**
          * (17) get double attribute AdjustSaturation
          * @return double the value of the attribute
          */
        public double getAdjustSaturation()
        {
            return getRealAttribute(AttributeName.ADJUSTSATURATION, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateFileSpec
     * 
     * @param iSkip number of elements to skip
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getCreateFileSpec(int iSkip)
    {
        return (JDFFileSpec)getCreateElement_KElement(ElementName.FILESPEC, null, iSkip);
    }

    /**
     * (27) const get element FileSpec
     * @param iSkip number of elements to skip
     * @return JDFFileSpec the element
     * default is getFileSpec(0)     */
    public JDFFileSpec getFileSpec(int iSkip)
    {
        return (JDFFileSpec) getElement(ElementName.FILESPEC, null, iSkip);
    }

    /**
     * (30) append element FileSpec
     */
    public JDFFileSpec appendFileSpec() throws JDFException
    {
        return (JDFFileSpec) appendElement(ElementName.FILESPEC, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refFileSpec(JDFFileSpec refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
