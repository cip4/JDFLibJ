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
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFResource;
    /**
    *****************************************************************************
    class JDFAutoInkZoneCalculationParams : public JDFResource

    *****************************************************************************
    */

public abstract class JDFAutoInkZoneCalculationParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.FOUNTAINPOSITIONS, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.PRINTABLEAREA, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ZONEWIDTH, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.ZONES, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.ZONESY, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.ZONEHEIGHT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.DEVICE, 0x66666611);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoInkZoneCalculationParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoInkZoneCalculationParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoInkZoneCalculationParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoInkZoneCalculationParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoInkZoneCalculationParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoInkZoneCalculationParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoInkZoneCalculationParams[  --> " + super.toString() + " ]";
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
        Methods for Attribute FountainPositions
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FountainPositions
          * @param value: the value to set the attribute to
          */
        public void setFountainPositions(JDFNumberList value)
        {
            setAttribute(AttributeName.FOUNTAINPOSITIONS, value, null);
        }

        /**
          * (20) get JDFNumberList attribute FountainPositions
          * @return JDFNumberList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNumberList
          */
        public JDFNumberList getFountainPositions()
        {
            String strAttrName = "";
            JDFNumberList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.FOUNTAINPOSITIONS, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFNumberList(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PrintableArea
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PrintableArea
          * @param value: the value to set the attribute to
          */
        public void setPrintableArea(JDFRectangle value)
        {
            setAttribute(AttributeName.PRINTABLEAREA, value, null);
        }

        /**
          * (20) get JDFRectangle attribute PrintableArea
          * @return JDFRectangle the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getPrintableArea()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.PRINTABLEAREA, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute ZoneWidth
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ZoneWidth
          * @param value: the value to set the attribute to
          */
        public void setZoneWidth(double value)
        {
            setAttribute(AttributeName.ZONEWIDTH, value, null);
        }

        /**
          * (17) get double attribute ZoneWidth
          * @return double the value of the attribute
          */
        public double getZoneWidth()
        {
            return getRealAttribute(AttributeName.ZONEWIDTH, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Zones
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Zones
          * @param value: the value to set the attribute to
          */
        public void setZones(int value)
        {
            setAttribute(AttributeName.ZONES, value, null);
        }

        /**
          * (15) get int attribute Zones
          * @return int the value of the attribute
          */
        public int getZones()
        {
            return getIntAttribute(AttributeName.ZONES, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ZonesY
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ZonesY
          * @param value: the value to set the attribute to
          */
        public void setZonesY(int value)
        {
            setAttribute(AttributeName.ZONESY, value, null);
        }

        /**
          * (15) get int attribute ZonesY
          * @return int the value of the attribute
          */
        public int getZonesY()
        {
            return getIntAttribute(AttributeName.ZONESY, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ZoneHeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ZoneHeight
          * @param value: the value to set the attribute to
          */
        public void setZoneHeight(double value)
        {
            setAttribute(AttributeName.ZONEHEIGHT, value, null);
        }

        /**
          * (17) get double attribute ZoneHeight
          * @return double the value of the attribute
          */
        public double getZoneHeight()
        {
            return getRealAttribute(AttributeName.ZONEHEIGHT, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element Device
     * @return JDFDevice the element
     */
    public JDFDevice getDevice()
    {
        return (JDFDevice) getElement(ElementName.DEVICE, null, 0);
    }

    /** (25) getCreateDevice
     * 
     * @return JDFDevice the element
     */
    public JDFDevice getCreateDevice()
    {
        return (JDFDevice) getCreateElement_KElement(ElementName.DEVICE, null, 0);
    }

    /**
     * (29) append element Device
     */
    public JDFDevice appendDevice() throws JDFException
    {
        return (JDFDevice) appendElementN(ElementName.DEVICE, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refDevice(JDFDevice refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
