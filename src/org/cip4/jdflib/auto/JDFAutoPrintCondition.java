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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.resource.JDFColorMeasurementConditions;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFFileSpec;

public abstract class JDFAutoPrintCondition extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.NAME, 0x22222211, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.AIMCURVE, 0x33333311, AttributeInfo.EnumAttributeType.TransferFunction, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.DENSITY, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COLORMEASUREMENTCONDITIONS, 0x66666611);
        elemInfoTable[1] = new ElemInfoTable(ElementName.DEVICE, 0x66666611);
        elemInfoTable[2] = new ElemInfoTable(ElementName.FILESPEC, 0x33333311);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoPrintCondition
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPrintCondition(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPrintCondition
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPrintCondition(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPrintCondition
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPrintCondition(
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
        return " JDFAutoPrintCondition[  --> " + super.toString() + " ]";
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
        Methods for Attribute Name
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Name
          * @param value: the value to set the attribute to
          */
        public void setName(String value)
        {
            setAttribute(AttributeName.NAME, value, null);
        }

        /**
          * (23) get String attribute Name
          * @return the value of the attribute
          */
        public String getName()
        {
            return getAttribute(AttributeName.NAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AimCurve
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AimCurve
          * @param value: the value to set the attribute to
          */
        public void setAimCurve(JDFTransferFunction value)
        {
            setAttribute(AttributeName.AIMCURVE, value, null);
        }

        /**
          * (20) get JDFTransferFunction attribute AimCurve
          * @return JDFTransferFunction the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFTransferFunction
          */
        public JDFTransferFunction getAimCurve()
        {
            String strAttrName = "";
            JDFTransferFunction nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.AIMCURVE, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute Density
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Density
          * @param value: the value to set the attribute to
          */
        public void setDensity(double value)
        {
            setAttribute(AttributeName.DENSITY, value, null);
        }

        /**
          * (17) get double attribute Density
          * @return double the value of the attribute
          */
        public double getDensity()
        {
            return getRealAttribute(AttributeName.DENSITY, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element ColorMeasurementConditions
     * @return JDFColorMeasurementConditions the element
     */
    public JDFColorMeasurementConditions getColorMeasurementConditions()
    {
        return (JDFColorMeasurementConditions) getElement(ElementName.COLORMEASUREMENTCONDITIONS, null, 0);
    }

    /** (25) getCreateColorMeasurementConditions
     * 
     * @return JDFColorMeasurementConditions the element
     */
    public JDFColorMeasurementConditions getCreateColorMeasurementConditions()
    {
        return (JDFColorMeasurementConditions) getCreateElement_KElement(ElementName.COLORMEASUREMENTCONDITIONS, null, 0);
    }

    /**
     * (29) append element ColorMeasurementConditions
     */
    public JDFColorMeasurementConditions appendColorMeasurementConditions() throws JDFException
    {
        return (JDFColorMeasurementConditions) appendElementN(ElementName.COLORMEASUREMENTCONDITIONS, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refColorMeasurementConditions(JDFColorMeasurementConditions refTarget)
    {
        refElement(refTarget);
    }

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
     * Get all FileSpec from the current element
     * 
     * @return Collection<JDFFileSpec>
     */
    public Collection<JDFFileSpec> getAllFileSpec()
    {
        Vector<JDFFileSpec> v = new Vector<JDFFileSpec>();

        JDFFileSpec kElem = (JDFFileSpec) getFirstChildElement(ElementName.FILESPEC, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFFileSpec) kElem.getNextSiblingElement(ElementName.FILESPEC, null);
        }

        return v;
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
