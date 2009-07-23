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
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.datatypes.*;                 
import org.cip4.jdflib.resource.*;                  
import org.cip4.jdflib.resource.process.*;

public abstract class JDFAutoAssembly extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[7];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.JOGSIDE, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumJogSide.getEnum(0), "Top");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ORDER, 0x44443311, AttributeInfo.EnumAttributeType.enumeration, EnumOrder.getEnum(0), "Gathering");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.BINDINGSIDE, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumBindingSide.getEnum(0), "Left");
        atrInfoTable[3] = new AtrInfoTable(AttributeName.ASSEMBLYID, 0x44444311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.ASSEMBLYIDS, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.JOBID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.PHYSICALSECTION, 0x33333111, AttributeInfo.EnumAttributeType.IntegerList, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.ASSEMBLYSECTION, 0x33333311);
        elemInfoTable[1] = new ElemInfoTable(ElementName.PAGELIST, 0x66666111);
        elemInfoTable[2] = new ElemInfoTable(ElementName.PAGEASSIGNEDLIST, 0x33333111);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoAssembly
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoAssembly(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoAssembly
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoAssembly(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoAssembly
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoAssembly(
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
        return " JDFAutoAssembly[  --> " + super.toString() + " ]";
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
        * Enumeration strings for JogSide
        */

        public static class EnumJogSide extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumJogSide(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumJogSide getEnum(String enumName)
            {
                return (EnumJogSide) getEnum(EnumJogSide.class, enumName);
            }

            public static EnumJogSide getEnum(int enumValue)
            {
                return (EnumJogSide) getEnum(EnumJogSide.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumJogSide.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumJogSide.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumJogSide.class);
            }

            public static final EnumJogSide Left = new EnumJogSide("Left");
            public static final EnumJogSide Right = new EnumJogSide("Right");
            public static final EnumJogSide Top = new EnumJogSide("Top");
            public static final EnumJogSide Bottom = new EnumJogSide("Bottom");
            public static final EnumJogSide None = new EnumJogSide("None");
        }      



        /**
        * Enumeration strings for Order
        */

        public static class EnumOrder extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumOrder(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumOrder getEnum(String enumName)
            {
                return (EnumOrder) getEnum(EnumOrder.class, enumName);
            }

            public static EnumOrder getEnum(int enumValue)
            {
                return (EnumOrder) getEnum(EnumOrder.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumOrder.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumOrder.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumOrder.class);
            }

            public static final EnumOrder Collecting = new EnumOrder("Collecting");
            public static final EnumOrder Gathering = new EnumOrder("Gathering");
            public static final EnumOrder None = new EnumOrder("None");
            public static final EnumOrder List = new EnumOrder("List");
        }      



        /**
        * Enumeration strings for BindingSide
        */

        public static class EnumBindingSide extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumBindingSide(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumBindingSide getEnum(String enumName)
            {
                return (EnumBindingSide) getEnum(EnumBindingSide.class, enumName);
            }

            public static EnumBindingSide getEnum(int enumValue)
            {
                return (EnumBindingSide) getEnum(EnumBindingSide.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumBindingSide.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumBindingSide.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumBindingSide.class);
            }

            public static final EnumBindingSide Left = new EnumBindingSide("Left");
            public static final EnumBindingSide Right = new EnumBindingSide("Right");
            public static final EnumBindingSide Top = new EnumBindingSide("Top");
            public static final EnumBindingSide Bottom = new EnumBindingSide("Bottom");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute JogSide
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute JogSide
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setJogSide(EnumJogSide enumVar)
        {
            setAttribute(AttributeName.JOGSIDE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute JogSide
          * @return the value of the attribute
          */
        public EnumJogSide getJogSide()
        {
            return EnumJogSide.getEnum(getAttribute(AttributeName.JOGSIDE, null, "Top"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Order
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Order
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setOrder(EnumOrder enumVar)
        {
            setAttribute(AttributeName.ORDER, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Order
          * @return the value of the attribute
          */
        public EnumOrder getOrder()
        {
            return EnumOrder.getEnum(getAttribute(AttributeName.ORDER, null, "Gathering"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute BindingSide
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute BindingSide
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setBindingSide(EnumBindingSide enumVar)
        {
            setAttribute(AttributeName.BINDINGSIDE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute BindingSide
          * @return the value of the attribute
          */
        public EnumBindingSide getBindingSide()
        {
            return EnumBindingSide.getEnum(getAttribute(AttributeName.BINDINGSIDE, null, "Left"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AssemblyID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AssemblyID
          * @param value: the value to set the attribute to
          */
        public void setAssemblyID(String value)
        {
            setAttribute(AttributeName.ASSEMBLYID, value, null);
        }

        /**
          * (23) get String attribute AssemblyID
          * @return the value of the attribute
          */
        public String getAssemblyID()
        {
            return getAttribute(AttributeName.ASSEMBLYID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute AssemblyIDs
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AssemblyIDs
          * @param value: the value to set the attribute to
          */
        public void setAssemblyIDs(VString value)
        {
            setAttribute(AttributeName.ASSEMBLYIDS, value, null);
        }

        /**
          * (21) get VString attribute AssemblyIDs
          * @return VString the value of the attribute
          */
        public VString getAssemblyIDs()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.ASSEMBLYIDS, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute JobID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute JobID
          * @param value: the value to set the attribute to
          */
        public void setJobID(String value)
        {
            setAttribute(AttributeName.JOBID, value, null);
        }

        /**
          * (23) get String attribute JobID
          * @return the value of the attribute
          */
        public String getJobID()
        {
            return getAttribute(AttributeName.JOBID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute PhysicalSection
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PhysicalSection
          * @param value: the value to set the attribute to
          */
        public void setPhysicalSection(JDFIntegerList value)
        {
            setAttribute(AttributeName.PHYSICALSECTION, value, null);
        }

        /**
          * (20) get JDFIntegerList attribute PhysicalSection
          * @return JDFIntegerList the value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFIntegerList
          */
        public JDFIntegerList getPhysicalSection()
        {
            String strAttrName = "";
            JDFIntegerList nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.PHYSICALSECTION, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFIntegerList(strAttrName);
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

    /** (26) getCreateAssemblySection
     * 
     * @param iSkip number of elements to skip
     * @return JDFAssemblySection the element
     */
    public JDFAssemblySection getCreateAssemblySection(int iSkip)
    {
        return (JDFAssemblySection)getCreateElement_KElement(ElementName.ASSEMBLYSECTION, null, iSkip);
    }

    /**
     * (27) const get element AssemblySection
     * @param iSkip number of elements to skip
     * @return JDFAssemblySection the element
     * default is getAssemblySection(0)     */
    public JDFAssemblySection getAssemblySection(int iSkip)
    {
        return (JDFAssemblySection) getElement(ElementName.ASSEMBLYSECTION, null, iSkip);
    }

    /**
     * Get all AssemblySection from the current element
     * 
     * @return Collection<JDFAssemblySection>, null if none are available
     */
    public Collection<JDFAssemblySection> getAllAssemblySection()
    {
        final VElement vc = getChildElementVector(ElementName.ASSEMBLYSECTION, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFAssemblySection> v = new Vector<JDFAssemblySection>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFAssemblySection) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element AssemblySection
     */
    public JDFAssemblySection appendAssemblySection() throws JDFException
    {
        return (JDFAssemblySection) appendElement(ElementName.ASSEMBLYSECTION, null);
    }

    /**
     * (24) const get element PageList
     * @return JDFPageList the element
     */
    public JDFPageList getPageList()
    {
        return (JDFPageList) getElement(ElementName.PAGELIST, null, 0);
    }

    /** (25) getCreatePageList
     * 
     * @return JDFPageList the element
     */
    public JDFPageList getCreatePageList()
    {
        return (JDFPageList) getCreateElement_KElement(ElementName.PAGELIST, null, 0);
    }

    /**
     * (29) append element PageList
     */
    public JDFPageList appendPageList() throws JDFException
    {
        return (JDFPageList) appendElementN(ElementName.PAGELIST, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refPageList(JDFPageList refTarget)
    {
        refElement(refTarget);
    }

    /** (26) getCreatePageAssignedList
     * 
     * @param iSkip number of elements to skip
     * @return JDFPageAssignedList the element
     */
    public JDFPageAssignedList getCreatePageAssignedList(int iSkip)
    {
        return (JDFPageAssignedList)getCreateElement_KElement(ElementName.PAGEASSIGNEDLIST, null, iSkip);
    }

    /**
     * (27) const get element PageAssignedList
     * @param iSkip number of elements to skip
     * @return JDFPageAssignedList the element
     * default is getPageAssignedList(0)     */
    public JDFPageAssignedList getPageAssignedList(int iSkip)
    {
        return (JDFPageAssignedList) getElement(ElementName.PAGEASSIGNEDLIST, null, iSkip);
    }

    /**
     * Get all PageAssignedList from the current element
     * 
     * @return Collection<JDFPageAssignedList>, null if none are available
     */
    public Collection<JDFPageAssignedList> getAllPageAssignedList()
    {
        final VElement vc = getChildElementVector(ElementName.PAGEASSIGNEDLIST, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFPageAssignedList> v = new Vector<JDFPageAssignedList>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFPageAssignedList) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element PageAssignedList
     */
    public JDFPageAssignedList appendPageAssignedList() throws JDFException
    {
        return (JDFPageAssignedList) appendElement(ElementName.PAGEASSIGNEDLIST, null);
    }

}// end namespace JDF
