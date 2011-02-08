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

import java.util.Collection;                        
import java.util.Iterator;                          
import java.util.List;                              
import java.util.Map;                               
import java.util.Vector;                            
import java.util.zip.DataFormatException;           

import org.apache.commons.lang.enums.ValuedEnum;    
import org.w3c.dom.Element;                         
import org.apache.xerces.dom.CoreDocumentImpl;      
import org.cip4.jdflib.*;                           
import org.cip4.jdflib.auto.*;                      
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.core.ElementInfo;                      
import org.cip4.jdflib.span.*;                      
import org.cip4.jdflib.node.*;                      
import org.cip4.jdflib.pool.*;                      
import org.cip4.jdflib.jmf.*;                       
import org.cip4.jdflib.datatypes.*;                 
import org.cip4.jdflib.resource.*;                  
import org.cip4.jdflib.resource.devicecapability.*; 
import org.cip4.jdflib.resource.intent.*;           
import org.cip4.jdflib.resource.process.*;          
import org.cip4.jdflib.resource.process.postpress.*;
import org.cip4.jdflib.resource.process.press.*;    
import org.cip4.jdflib.resource.process.prepress.*; 
import org.cip4.jdflib.util.*;           
    /**
    *****************************************************************************
    class JDFAutoGlue : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoGlue extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.WORKINGDIRECTION, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumWorkingDirection.getEnum(0), null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.GLUEAPPLICATION, 0x66666666);
        elemInfoTable[1] = new ElemInfoTable(ElementName.GLUELINE, 0x66666111);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoGlue
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoGlue(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoGlue
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoGlue(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoGlue
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoGlue(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoGlue[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for WorkingDirection
        */

        public static class EnumWorkingDirection extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumWorkingDirection(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumWorkingDirection getEnum(String enumName)
            {
                return (EnumWorkingDirection) getEnum(EnumWorkingDirection.class, enumName);
            }

            public static EnumWorkingDirection getEnum(int enumValue)
            {
                return (EnumWorkingDirection) getEnum(EnumWorkingDirection.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumWorkingDirection.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumWorkingDirection.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumWorkingDirection.class);
            }

            public static final EnumWorkingDirection Top = new EnumWorkingDirection("Top");
            public static final EnumWorkingDirection Bottom = new EnumWorkingDirection("Bottom");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute WorkingDirection
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute WorkingDirection
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setWorkingDirection(EnumWorkingDirection enumVar)
        {
            setAttribute(AttributeName.WORKINGDIRECTION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute WorkingDirection
          * @return the value of the attribute
          */
        public EnumWorkingDirection getWorkingDirection()
        {
            return EnumWorkingDirection.getEnum(getAttribute(AttributeName.WORKINGDIRECTION, null, null));
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element GlueApplication
     * @return JDFGlueApplication the element
     */
    public JDFGlueApplication getGlueApplication()
    {
        return (JDFGlueApplication) getElement(ElementName.GLUEAPPLICATION, null, 0);
    }

    /** (25) getCreateGlueApplication
     * 
     * @return JDFGlueApplication the element
     */
    public JDFGlueApplication getCreateGlueApplication()
    {
        return (JDFGlueApplication) getCreateElement_KElement(ElementName.GLUEAPPLICATION, null, 0);
    }

    /**
     * (29) append element GlueApplication
     */
    public JDFGlueApplication appendGlueApplication() throws JDFException
    {
        return (JDFGlueApplication) appendElementN(ElementName.GLUEAPPLICATION, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refGlueApplication(JDFGlueApplication refTarget)
    {
        refElement(refTarget);
    }

    /**
     * (24) const get element GlueLine
     * @return JDFGlueLine the element
     */
    public JDFGlueLine getGlueLine()
    {
        return (JDFGlueLine) getElement(ElementName.GLUELINE, null, 0);
    }

    /** (25) getCreateGlueLine
     * 
     * @return JDFGlueLine the element
     */
    public JDFGlueLine getCreateGlueLine()
    {
        return (JDFGlueLine) getCreateElement_KElement(ElementName.GLUELINE, null, 0);
    }

    /**
     * (29) append element GlueLine
     */
    public JDFGlueLine appendGlueLine() throws JDFException
    {
        return (JDFGlueLine) appendElementN(ElementName.GLUELINE, 1, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refGlueLine(JDFGlueLine refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
