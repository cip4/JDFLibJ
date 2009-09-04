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
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFAutomatedOverPrintParams;
import org.cip4.jdflib.resource.process.JDFColorantAlias;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionOp;

public abstract class JDFAutoElementColorParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.COLORMANAGEMENTSYSTEM, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ICCOUTPUTPROFILEUSAGE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumICCOutputProfileUsage.getEnum(0), null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.AUTOMATEDOVERPRINTPARAMS, 0x66666611);
        elemInfoTable[1] = new ElemInfoTable(ElementName.COLORANTALIAS, 0x22222211);
        elemInfoTable[2] = new ElemInfoTable(ElementName.COLORSPACECONVERSIONOP, 0x66666611);
        elemInfoTable[3] = new ElemInfoTable(ElementName.FILESPEC, 0x66666611);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoElementColorParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoElementColorParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoElementColorParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoElementColorParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoElementColorParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoElementColorParams(
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
        return " JDFAutoElementColorParams[  --> " + super.toString() + " ]";
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


        /**
        * Enumeration strings for ICCOutputProfileUsage
        */

        public static class EnumICCOutputProfileUsage extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumICCOutputProfileUsage(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumICCOutputProfileUsage getEnum(String enumName)
            {
                return (EnumICCOutputProfileUsage) getEnum(EnumICCOutputProfileUsage.class, enumName);
            }

            public static EnumICCOutputProfileUsage getEnum(int enumValue)
            {
                return (EnumICCOutputProfileUsage) getEnum(EnumICCOutputProfileUsage.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumICCOutputProfileUsage.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumICCOutputProfileUsage.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumICCOutputProfileUsage.class);
            }

            public static final EnumICCOutputProfileUsage PDLActual = new EnumICCOutputProfileUsage("PDLActual");
            public static final EnumICCOutputProfileUsage PDLReference = new EnumICCOutputProfileUsage("PDLReference");
            public static final EnumICCOutputProfileUsage IgnorePDL = new EnumICCOutputProfileUsage("IgnorePDL");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ColorManagementSystem
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ColorManagementSystem
          * @param value: the value to set the attribute to
          */
        public void setColorManagementSystem(String value)
        {
            setAttribute(AttributeName.COLORMANAGEMENTSYSTEM, value, null);
        }

        /**
          * (23) get String attribute ColorManagementSystem
          * @return the value of the attribute
          */
        public String getColorManagementSystem()
        {
            return getAttribute(AttributeName.COLORMANAGEMENTSYSTEM, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ICCOutputProfileUsage
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ICCOutputProfileUsage
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setICCOutputProfileUsage(EnumICCOutputProfileUsage enumVar)
        {
            setAttribute(AttributeName.ICCOUTPUTPROFILEUSAGE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute ICCOutputProfileUsage
          * @return the value of the attribute
          */
        public EnumICCOutputProfileUsage getICCOutputProfileUsage()
        {
            return EnumICCOutputProfileUsage.getEnum(getAttribute(AttributeName.ICCOUTPUTPROFILEUSAGE, null, null));
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element AutomatedOverPrintParams
     * @return JDFAutomatedOverPrintParams the element
     */
    public JDFAutomatedOverPrintParams getAutomatedOverPrintParams()
    {
        return (JDFAutomatedOverPrintParams) getElement(ElementName.AUTOMATEDOVERPRINTPARAMS, null, 0);
    }

    /** (25) getCreateAutomatedOverPrintParams
     * 
     * @return JDFAutomatedOverPrintParams the element
     */
    public JDFAutomatedOverPrintParams getCreateAutomatedOverPrintParams()
    {
        return (JDFAutomatedOverPrintParams) getCreateElement_KElement(ElementName.AUTOMATEDOVERPRINTPARAMS, null, 0);
    }

    /**
     * (29) append element AutomatedOverPrintParams
     */
    public JDFAutomatedOverPrintParams appendAutomatedOverPrintParams() throws JDFException
    {
        return (JDFAutomatedOverPrintParams) appendElementN(ElementName.AUTOMATEDOVERPRINTPARAMS, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refAutomatedOverPrintParams(JDFAutomatedOverPrintParams refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateColorantAlias
     * 
     * @param iSkip number of elements to skip
     * @return JDFColorantAlias the element
     */
    public JDFColorantAlias getCreateColorantAlias(int iSkip)
    {
        return (JDFColorantAlias)getCreateElement_KElement(ElementName.COLORANTALIAS, null, iSkip);
    }

    /**
     * (27) const get element ColorantAlias
     * @param iSkip number of elements to skip
     * @return JDFColorantAlias the element
     * default is getColorantAlias(0)     */
    public JDFColorantAlias getColorantAlias(int iSkip)
    {
        return (JDFColorantAlias) getElement(ElementName.COLORANTALIAS, null, iSkip);
    }

    /**
     * Get all ColorantAlias from the current element
     * 
     * @return Collection<JDFColorantAlias>, null if none are available
     */
    public Collection<JDFColorantAlias> getAllColorantAlias()
    {
        final VElement vc = getChildElementVector(ElementName.COLORANTALIAS, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFColorantAlias> v = new Vector<JDFColorantAlias>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFColorantAlias) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element ColorantAlias
     */
    public JDFColorantAlias appendColorantAlias() throws JDFException
    {
        return (JDFColorantAlias) appendElement(ElementName.COLORANTALIAS, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refColorantAlias(JDFColorantAlias refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element ColorSpaceConversionOp
     * @return JDFColorSpaceConversionOp the element
     */
    public JDFColorSpaceConversionOp getColorSpaceConversionOp()
    {
        return (JDFColorSpaceConversionOp) getElement(ElementName.COLORSPACECONVERSIONOP, null, 0);
    }

    /** (25) getCreateColorSpaceConversionOp
     * 
     * @return JDFColorSpaceConversionOp the element
     */
    public JDFColorSpaceConversionOp getCreateColorSpaceConversionOp()
    {
        return (JDFColorSpaceConversionOp) getCreateElement_KElement(ElementName.COLORSPACECONVERSIONOP, null, 0);
    }

    /**
     * (29) append element ColorSpaceConversionOp
     */
    public JDFColorSpaceConversionOp appendColorSpaceConversionOp() throws JDFException
    {
        return (JDFColorSpaceConversionOp) appendElementN(ElementName.COLORSPACECONVERSIONOP, 1, null);
    }

    /**
     * (24) const get element FileSpec
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getFileSpec()
    {
        return (JDFFileSpec) getElement(ElementName.FILESPEC, null, 0);
    }

    /** (25) getCreateFileSpec
     * 
     * @return JDFFileSpec the element
     */
    public JDFFileSpec getCreateFileSpec()
    {
        return (JDFFileSpec) getCreateElement_KElement(ElementName.FILESPEC, null, 0);
    }

    /**
     * (29) append element FileSpec
     */
    public JDFFileSpec appendFileSpec() throws JDFException
    {
        return (JDFFileSpec) appendElementN(ElementName.FILESPEC, 1, null);
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
