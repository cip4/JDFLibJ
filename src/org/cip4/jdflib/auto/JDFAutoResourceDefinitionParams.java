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
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.resource.*;

public abstract class JDFAutoResourceDefinitionParams extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.DEFAULTPRIORITY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumDefaultPriority.getEnum(0), "DefaultJDF");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.DEFAULTID, 0x44444443, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.DEFAULTJDF, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.RESOURCEPARAM, 0x33333331);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoResourceDefinitionParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoResourceDefinitionParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoResourceDefinitionParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoResourceDefinitionParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoResourceDefinitionParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoResourceDefinitionParams(
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
        return " JDFAutoResourceDefinitionParams[  --> " + super.toString() + " ]";
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
        * Enumeration strings for DefaultPriority
        */

        public static class EnumDefaultPriority extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDefaultPriority(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDefaultPriority getEnum(String enumName)
            {
                return (EnumDefaultPriority) getEnum(EnumDefaultPriority.class, enumName);
            }

            public static EnumDefaultPriority getEnum(int enumValue)
            {
                return (EnumDefaultPriority) getEnum(EnumDefaultPriority.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDefaultPriority.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDefaultPriority.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDefaultPriority.class);
            }

            public static final EnumDefaultPriority Application = new EnumDefaultPriority("Application");
            public static final EnumDefaultPriority DefaultJDF = new EnumDefaultPriority("DefaultJDF");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute DefaultPriority
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DefaultPriority
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDefaultPriority(EnumDefaultPriority enumVar)
        {
            setAttribute(AttributeName.DEFAULTPRIORITY, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute DefaultPriority
          * @return the value of the attribute
          */
        public EnumDefaultPriority getDefaultPriority()
        {
            return EnumDefaultPriority.getEnum(getAttribute(AttributeName.DEFAULTPRIORITY, null, "DefaultJDF"));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DefaultID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DefaultID
          * @param value: the value to set the attribute to
          */
        public void setDefaultID(String value)
        {
            setAttribute(AttributeName.DEFAULTID, value, null);
        }

        /**
          * (23) get String attribute DefaultID
          * @return the value of the attribute
          */
        public String getDefaultID()
        {
            return getAttribute(AttributeName.DEFAULTID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute DefaultJDF
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DefaultJDF
          * @param value: the value to set the attribute to
          */
        public void setDefaultJDF(String value)
        {
            setAttribute(AttributeName.DEFAULTJDF, value, null);
        }

        /**
          * (23) get String attribute DefaultJDF
          * @return the value of the attribute
          */
        public String getDefaultJDF()
        {
            return getAttribute(AttributeName.DEFAULTJDF, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateResourceParam
     * 
     * @param iSkip number of elements to skip
     * @return JDFResourceParam the element
     */
    public JDFResourceParam getCreateResourceParam(int iSkip)
    {
        return (JDFResourceParam)getCreateElement_KElement(ElementName.RESOURCEPARAM, null, iSkip);
    }

    /**
     * (27) const get element ResourceParam
     * @param iSkip number of elements to skip
     * @return JDFResourceParam the element
     * default is getResourceParam(0)     */
    public JDFResourceParam getResourceParam(int iSkip)
    {
        return (JDFResourceParam) getElement(ElementName.RESOURCEPARAM, null, iSkip);
    }

    /**
     * Get all ResourceParam from the current element
     * 
     * @return Collection<JDFResourceParam>
     */
    public Collection<JDFResourceParam> getAllResourceParam()
    {
        Vector<JDFResourceParam> v = new Vector<JDFResourceParam>();

        JDFResourceParam kElem = (JDFResourceParam) getFirstChildElement(ElementName.RESOURCEPARAM, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFResourceParam) kElem.getNextSiblingElement(ElementName.RESOURCEPARAM, null);
        }

        return v;
    }

    /**
     * (30) append element ResourceParam
     */
    public JDFResourceParam appendResourceParam() throws JDFException
    {
        return (JDFResourceParam) appendElement(ElementName.RESOURCEPARAM, null);
    }

}// end namespace JDF
