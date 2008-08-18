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
import java.util.zip.DataFormatException;

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
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFDieLayout;
import org.cip4.jdflib.resource.process.JDFSignatureCell;
import org.cip4.jdflib.resource.process.postpress.JDFFold;

public abstract class JDFAutoBinderySignature extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[10];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.BINDERYSIGNATURETYPE, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumBinderySignatureType.getEnum(0), "Fold");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.BINDINGEDGE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumBindingEdge.getEnum(0), "Left");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.JOGEDGE, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumJogEdge.getEnum(0), "Top");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.NUMBERUP, 0x33333311, AttributeInfo.EnumAttributeType.XYPair, null, "1 1");
        atrInfoTable[4] = new AtrInfoTable(AttributeName.FOLDCATALOG, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.BINDINGORIENTATION, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumBindingOrientation.getEnum(0), null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.OUTSIDEGUTTER, 0x33333111, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.STAGGERCOLUMNS, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.STAGGERCONTINUOUS, 0x33333111, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.STAGGERROWS, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.DIELAYOUT, 0x66666111);
        elemInfoTable[1] = new ElemInfoTable(ElementName.FOLD, 0x33333311);
        elemInfoTable[2] = new ElemInfoTable(ElementName.SIGNATURECELL, 0x33333311);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoBinderySignature
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoBinderySignature(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBinderySignature
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoBinderySignature(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBinderySignature
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoBinderySignature(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoBinderySignature[  --> " + super.toString() + " ]";
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


        /**
        * Enumeration strings for BinderySignatureType
        */

        public static class EnumBinderySignatureType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumBinderySignatureType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumBinderySignatureType getEnum(String enumName)
            {
                return (EnumBinderySignatureType) getEnum(EnumBinderySignatureType.class, enumName);
            }

            public static EnumBinderySignatureType getEnum(int enumValue)
            {
                return (EnumBinderySignatureType) getEnum(EnumBinderySignatureType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumBinderySignatureType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumBinderySignatureType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumBinderySignatureType.class);
            }

            public static final EnumBinderySignatureType Die = new EnumBinderySignatureType("Die");
            public static final EnumBinderySignatureType Fold = new EnumBinderySignatureType("Fold");
            public static final EnumBinderySignatureType Grid = new EnumBinderySignatureType("Grid");
        }      



        /**
        * Enumeration strings for BindingEdge
        */

        public static class EnumBindingEdge extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumBindingEdge(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumBindingEdge getEnum(String enumName)
            {
                return (EnumBindingEdge) getEnum(EnumBindingEdge.class, enumName);
            }

            public static EnumBindingEdge getEnum(int enumValue)
            {
                return (EnumBindingEdge) getEnum(EnumBindingEdge.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumBindingEdge.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumBindingEdge.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumBindingEdge.class);
            }

            public static final EnumBindingEdge Left = new EnumBindingEdge("Left");
            public static final EnumBindingEdge Right = new EnumBindingEdge("Right");
            public static final EnumBindingEdge Top = new EnumBindingEdge("Top");
            public static final EnumBindingEdge Bottom = new EnumBindingEdge("Bottom");
            public static final EnumBindingEdge None = new EnumBindingEdge("None");
        }      



        /**
        * Enumeration strings for JogEdge
        */

        public static class EnumJogEdge extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumJogEdge(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumJogEdge getEnum(String enumName)
            {
                return (EnumJogEdge) getEnum(EnumJogEdge.class, enumName);
            }

            public static EnumJogEdge getEnum(int enumValue)
            {
                return (EnumJogEdge) getEnum(EnumJogEdge.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumJogEdge.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumJogEdge.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumJogEdge.class);
            }

            public static final EnumJogEdge Left = new EnumJogEdge("Left");
            public static final EnumJogEdge Right = new EnumJogEdge("Right");
            public static final EnumJogEdge Top = new EnumJogEdge("Top");
            public static final EnumJogEdge Bottom = new EnumJogEdge("Bottom");
            public static final EnumJogEdge None = new EnumJogEdge("None");
        }      



        /**
        * Enumeration strings for BindingOrientation
        */

        public static class EnumBindingOrientation extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumBindingOrientation(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumBindingOrientation getEnum(String enumName)
            {
                return (EnumBindingOrientation) getEnum(EnumBindingOrientation.class, enumName);
            }

            public static EnumBindingOrientation getEnum(int enumValue)
            {
                return (EnumBindingOrientation) getEnum(EnumBindingOrientation.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumBindingOrientation.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumBindingOrientation.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumBindingOrientation.class);
            }

            public static final EnumBindingOrientation Rotate0 = new EnumBindingOrientation("Rotate0");
            public static final EnumBindingOrientation Rotate90 = new EnumBindingOrientation("Rotate90");
            public static final EnumBindingOrientation Rotate180 = new EnumBindingOrientation("Rotate180");
            public static final EnumBindingOrientation Rotate270 = new EnumBindingOrientation("Rotate270");
            public static final EnumBindingOrientation Flip0 = new EnumBindingOrientation("Flip0");
            public static final EnumBindingOrientation Flip90 = new EnumBindingOrientation("Flip90");
            public static final EnumBindingOrientation Flip180 = new EnumBindingOrientation("Flip180");
            public static final EnumBindingOrientation Flip270 = new EnumBindingOrientation("Flip270");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute BinderySignatureType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute BinderySignatureType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setBinderySignatureType(EnumBinderySignatureType enumVar)
        {
            setAttribute(AttributeName.BINDERYSIGNATURETYPE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute BinderySignatureType
          * @return the value of the attribute
          */
        public EnumBinderySignatureType getBinderySignatureType()
        {
            return EnumBinderySignatureType.getEnum(getAttribute(AttributeName.BINDERYSIGNATURETYPE, null, "Fold"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BindingEdge
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute BindingEdge
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setBindingEdge(EnumBindingEdge enumVar)
        {
            setAttribute(AttributeName.BINDINGEDGE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute BindingEdge
          * @return the value of the attribute
          */
        public EnumBindingEdge getBindingEdge()
        {
            return EnumBindingEdge.getEnum(getAttribute(AttributeName.BINDINGEDGE, null, "Left"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute JogEdge
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute JogEdge
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setJogEdge(EnumJogEdge enumVar)
        {
            setAttribute(AttributeName.JOGEDGE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute JogEdge
          * @return the value of the attribute
          */
        public EnumJogEdge getJogEdge()
        {
            return EnumJogEdge.getEnum(getAttribute(AttributeName.JOGEDGE, null, "Top"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NumberUp
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NumberUp
          * @param value: the value to set the attribute to
          */
        public void setNumberUp(JDFXYPair value)
        {
            setAttribute(AttributeName.NUMBERUP, value, null);
        }

        /**
          * (20) get JDFXYPair attribute NumberUp
          * @return JDFXYPair the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getNumberUp()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.NUMBERUP, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute FoldCatalog
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FoldCatalog
          * @param value: the value to set the attribute to
          */
        public void setFoldCatalog(String value)
        {
            setAttribute(AttributeName.FOLDCATALOG, value, null);
        }

        /**
          * (23) get String attribute FoldCatalog
          * @return the value of the attribute
          */
        public String getFoldCatalog()
        {
            return getAttribute(AttributeName.FOLDCATALOG, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BindingOrientation
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute BindingOrientation
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setBindingOrientation(EnumBindingOrientation enumVar)
        {
            setAttribute(AttributeName.BINDINGORIENTATION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute BindingOrientation
          * @return the value of the attribute
          */
        public EnumBindingOrientation getBindingOrientation()
        {
            return EnumBindingOrientation.getEnum(getAttribute(AttributeName.BINDINGORIENTATION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute OutsideGutter
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OutsideGutter
          * @param value: the value to set the attribute to
          */
        public void setOutsideGutter(boolean value)
        {
            setAttribute(AttributeName.OUTSIDEGUTTER, value, null);
        }

        /**
          * (18) get boolean attribute OutsideGutter
          * @return boolean the value of the attribute
          */
        public boolean getOutsideGutter()
        {
            return getBoolAttribute(AttributeName.OUTSIDEGUTTER, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StaggerColumns
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StaggerColumns
          * @param value: the value to set the attribute to
          */
        public void setStaggerColumns(JDFNumberList value)
        {
            setAttribute(AttributeName.STAGGERCOLUMNS, value, null);
        }

        /**
          * (20) get JDFNumberList attribute StaggerColumns
          * @return JDFNumberList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNumberList
          */
        public JDFNumberList getStaggerColumns()
        {
            String strAttrName = "";
            JDFNumberList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.STAGGERCOLUMNS, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute StaggerContinuous
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StaggerContinuous
          * @param value: the value to set the attribute to
          */
        public void setStaggerContinuous(boolean value)
        {
            setAttribute(AttributeName.STAGGERCONTINUOUS, value, null);
        }

        /**
          * (18) get boolean attribute StaggerContinuous
          * @return boolean the value of the attribute
          */
        public boolean getStaggerContinuous()
        {
            return getBoolAttribute(AttributeName.STAGGERCONTINUOUS, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StaggerRows
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StaggerRows
          * @param value: the value to set the attribute to
          */
        public void setStaggerRows(JDFNumberList value)
        {
            setAttribute(AttributeName.STAGGERROWS, value, null);
        }

        /**
          * (20) get JDFNumberList attribute StaggerRows
          * @return JDFNumberList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFNumberList
          */
        public JDFNumberList getStaggerRows()
        {
            String strAttrName = "";
            JDFNumberList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.STAGGERROWS, null, JDFConstants.EMPTYSTRING);
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

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element DieLayout
     * @return JDFDieLayout the element
     */
    public JDFDieLayout getDieLayout()
    {
        return (JDFDieLayout) getElement(ElementName.DIELAYOUT, null, 0);
    }

    /** (25) getCreateDieLayout
     * 
     * @return JDFDieLayout the element
     */
    public JDFDieLayout getCreateDieLayout()
    {
        return (JDFDieLayout) getCreateElement_KElement(ElementName.DIELAYOUT, null, 0);
    }

    /**
     * (29) append element DieLayout
     */
    public JDFDieLayout appendDieLayout() throws JDFException
    {
        return (JDFDieLayout) appendElementN(ElementName.DIELAYOUT, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refDieLayout(JDFDieLayout refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreateFold
     * 
     * @param iSkip number of elements to skip
     * @return JDFFold the element
     */
    public JDFFold getCreateFold(int iSkip)
    {
        return (JDFFold)getCreateElement_KElement(ElementName.FOLD, null, iSkip);
    }

    /**
     * (27) const get element Fold
     * @param iSkip number of elements to skip
     * @return JDFFold the element
     * default is getFold(0)     */
    public JDFFold getFold(int iSkip)
    {
        return (JDFFold) getElement(ElementName.FOLD, null, iSkip);
    }

    /**
     * Get all Fold from the current element
     * 
     * @return Collection<JDFFold>
     */
    public Collection<JDFFold> getAllFold()
    {
        Vector<JDFFold> v = new Vector<JDFFold>();

        JDFFold kElem = (JDFFold) getFirstChildElement(ElementName.FOLD, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFFold) kElem.getNextSiblingElement(ElementName.FOLD, null);
        }

        return v;
    }

    /**
     * (30) append element Fold
     */
    public JDFFold appendFold() throws JDFException
    {
        return (JDFFold) appendElement(ElementName.FOLD, null);
    }

    /** (26) getCreateSignatureCell
     * 
     * @param iSkip number of elements to skip
     * @return JDFSignatureCell the element
     */
    public JDFSignatureCell getCreateSignatureCell(int iSkip)
    {
        return (JDFSignatureCell)getCreateElement_KElement(ElementName.SIGNATURECELL, null, iSkip);
    }

    /**
     * (27) const get element SignatureCell
     * @param iSkip number of elements to skip
     * @return JDFSignatureCell the element
     * default is getSignatureCell(0)     */
    public JDFSignatureCell getSignatureCell(int iSkip)
    {
        return (JDFSignatureCell) getElement(ElementName.SIGNATURECELL, null, iSkip);
    }

    /**
     * Get all SignatureCell from the current element
     * 
     * @return Collection<JDFSignatureCell>
     */
    public Collection<JDFSignatureCell> getAllSignatureCell()
    {
        Vector<JDFSignatureCell> v = new Vector<JDFSignatureCell>();

        JDFSignatureCell kElem = (JDFSignatureCell) getFirstChildElement(ElementName.SIGNATURECELL, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFSignatureCell) kElem.getNextSiblingElement(ElementName.SIGNATURECELL, null);
        }

        return v;
    }

    /**
     * (30) append element SignatureCell
     */
    public JDFSignatureCell appendSignatureCell() throws JDFException
    {
        return (JDFSignatureCell) appendElement(ElementName.SIGNATURECELL, null);
    }

}// end namespace JDF
