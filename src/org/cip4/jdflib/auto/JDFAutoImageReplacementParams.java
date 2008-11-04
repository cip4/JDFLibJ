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
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFFileSpec;

public abstract class JDFAutoImageReplacementParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.IMAGEREPLACEMENTSTRATEGY, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumImageReplacementStrategy.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.IMAGEPRESCANSTRATEGY, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.MAXRESOLUTION, 0x44444443, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.MINRESOLUTION, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.RESOLUTIONREDUCTIONSTRATEGY, 0x44444443, AttributeInfo.EnumAttributeType.enumeration, EnumResolutionReductionStrategy.getEnum(0), null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.IGNOREEXTENSIONS, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.MAXSEARCHRECURSION, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.FILESPEC, 0x22222221);
        elemInfoTable[1] = new ElemInfoTable(ElementName.SEARCHPATH, 0x44444443);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoImageReplacementParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoImageReplacementParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoImageReplacementParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoImageReplacementParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoImageReplacementParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoImageReplacementParams(
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
        return " JDFAutoImageReplacementParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for ImageReplacementStrategy
        */

        public static class EnumImageReplacementStrategy extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumImageReplacementStrategy(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumImageReplacementStrategy getEnum(String enumName)
            {
                return (EnumImageReplacementStrategy) getEnum(EnumImageReplacementStrategy.class, enumName);
            }

            public static EnumImageReplacementStrategy getEnum(int enumValue)
            {
                return (EnumImageReplacementStrategy) getEnum(EnumImageReplacementStrategy.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumImageReplacementStrategy.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumImageReplacementStrategy.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumImageReplacementStrategy.class);
            }

            public static final EnumImageReplacementStrategy Omit = new EnumImageReplacementStrategy("Omit");
            public static final EnumImageReplacementStrategy Proxy = new EnumImageReplacementStrategy("Proxy");
            public static final EnumImageReplacementStrategy Replace = new EnumImageReplacementStrategy("Replace");
            public static final EnumImageReplacementStrategy AttemptReplacement = new EnumImageReplacementStrategy("AttemptReplacement");
        }      



        /**
        * Enumeration strings for ResolutionReductionStrategy
        */

        public static class EnumResolutionReductionStrategy extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumResolutionReductionStrategy(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumResolutionReductionStrategy getEnum(String enumName)
            {
                return (EnumResolutionReductionStrategy) getEnum(EnumResolutionReductionStrategy.class, enumName);
            }

            public static EnumResolutionReductionStrategy getEnum(int enumValue)
            {
                return (EnumResolutionReductionStrategy) getEnum(EnumResolutionReductionStrategy.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumResolutionReductionStrategy.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumResolutionReductionStrategy.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumResolutionReductionStrategy.class);
            }

            public static final EnumResolutionReductionStrategy Downsample = new EnumResolutionReductionStrategy("Downsample");
            public static final EnumResolutionReductionStrategy Subsample = new EnumResolutionReductionStrategy("Subsample");
            public static final EnumResolutionReductionStrategy Bicubic = new EnumResolutionReductionStrategy("Bicubic");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute ImageReplacementStrategy
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ImageReplacementStrategy
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setImageReplacementStrategy(EnumImageReplacementStrategy enumVar)
        {
            setAttribute(AttributeName.IMAGEREPLACEMENTSTRATEGY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute ImageReplacementStrategy
          * @return the value of the attribute
          */
        public EnumImageReplacementStrategy getImageReplacementStrategy()
        {
            return EnumImageReplacementStrategy.getEnum(getAttribute(AttributeName.IMAGEREPLACEMENTSTRATEGY, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ImagePreScanStrategy
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ImagePreScanStrategy
          * @param value: the value to set the attribute to
          */
        public void setImagePreScanStrategy(String value)
        {
            setAttribute(AttributeName.IMAGEPRESCANSTRATEGY, value, null);
        }

        /**
          * (23) get String attribute ImagePreScanStrategy
          * @return the value of the attribute
          */
        public String getImagePreScanStrategy()
        {
            return getAttribute(AttributeName.IMAGEPRESCANSTRATEGY, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxResolution
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxResolution
          * @param value: the value to set the attribute to
          */
        public void setMaxResolution(double value)
        {
            setAttribute(AttributeName.MAXRESOLUTION, value, null);
        }

        /**
          * (17) get double attribute MaxResolution
          * @return double the value of the attribute
          */
        public double getMaxResolution()
        {
            return getRealAttribute(AttributeName.MAXRESOLUTION, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MinResolution
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MinResolution
          * @param value: the value to set the attribute to
          */
        public void setMinResolution(double value)
        {
            setAttribute(AttributeName.MINRESOLUTION, value, null);
        }

        /**
          * (17) get double attribute MinResolution
          * @return double the value of the attribute
          */
        public double getMinResolution()
        {
            return getRealAttribute(AttributeName.MINRESOLUTION, null, 0.0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ResolutionReductionStrategy
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ResolutionReductionStrategy
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setResolutionReductionStrategy(EnumResolutionReductionStrategy enumVar)
        {
            setAttribute(AttributeName.RESOLUTIONREDUCTIONSTRATEGY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute ResolutionReductionStrategy
          * @return the value of the attribute
          */
        public EnumResolutionReductionStrategy getResolutionReductionStrategy()
        {
            return EnumResolutionReductionStrategy.getEnum(getAttribute(AttributeName.RESOLUTIONREDUCTIONSTRATEGY, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute IgnoreExtensions
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IgnoreExtensions
          * @param value: the value to set the attribute to
          */
        public void setIgnoreExtensions(VString value)
        {
            setAttribute(AttributeName.IGNOREEXTENSIONS, value, null);
        }

        /**
          * (21) get VString attribute IgnoreExtensions
          * @return VString the value of the attribute
          */
        public VString getIgnoreExtensions()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.IGNOREEXTENSIONS, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MaxSearchRecursion
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MaxSearchRecursion
          * @param value: the value to set the attribute to
          */
        public void setMaxSearchRecursion(int value)
        {
            setAttribute(AttributeName.MAXSEARCHRECURSION, value, null);
        }

        /**
          * (15) get int attribute MaxSearchRecursion
          * @return int the value of the attribute
          */
        public int getMaxSearchRecursion()
        {
            return getIntAttribute(AttributeName.MAXSEARCHRECURSION, null, 0);
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

    /** (26) getCreateSearchPath
     * 
     * @param iSkip number of elements to skip
     * @return JDFElement the element
     */
    public JDFElement getCreateSearchPath(int iSkip)
    {
        return (JDFElement)getCreateElement_KElement(ElementName.SEARCHPATH, null, iSkip);
    }

    /**
     * (27) const get element SearchPath
     * @param iSkip number of elements to skip
     * @return JDFElement the element
     * default is getSearchPath(0)     */
    public JDFElement getSearchPath(int iSkip)
    {
        return (JDFElement) getElement(ElementName.SEARCHPATH, null, iSkip);
    }

    /**
     * Get all SearchPath from the current element
     * 
     * @return Collection<JDFElement>
     */
    public Collection<JDFElement> getAllSearchPath()
    {
        Vector<JDFElement> v = new Vector<JDFElement>();

        JDFElement kElem = (JDFElement) getFirstChildElement(ElementName.SEARCHPATH, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFElement) kElem.getNextSiblingElement(ElementName.SEARCHPATH, null);
        }

        return v;
    }

    /**
     * (30) append element SearchPath
     */
    public JDFElement appendSearchPath() throws JDFException
    {
        return (JDFElement) appendElement(ElementName.SEARCHPATH, null);
    }

}// end namespace JDF
