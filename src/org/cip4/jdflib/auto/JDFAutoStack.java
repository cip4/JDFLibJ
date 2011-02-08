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
    class JDFAutoStack : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoStack extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.LOGICALSTACKORD, 0x22221111, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.LOGICALSTACKSEQUENCE, 0x33331111, AttributeInfo.EnumAttributeType.enumeration, EnumLogicalStackSequence.getEnum(0), "SheetIndex");
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoStack
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoStack(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoStack
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoStack(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoStack
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoStack(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoStack[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for LogicalStackSequence
        */

        public static class EnumLogicalStackSequence extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumLogicalStackSequence(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumLogicalStackSequence getEnum(String enumName)
            {
                return (EnumLogicalStackSequence) getEnum(EnumLogicalStackSequence.class, enumName);
            }

            public static EnumLogicalStackSequence getEnum(int enumValue)
            {
                return (EnumLogicalStackSequence) getEnum(EnumLogicalStackSequence.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumLogicalStackSequence.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumLogicalStackSequence.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumLogicalStackSequence.class);
            }

            public static final EnumLogicalStackSequence SheetIndex = new EnumLogicalStackSequence("SheetIndex");
            public static final EnumLogicalStackSequence DescendingSheetIndex = new EnumLogicalStackSequence("DescendingSheetIndex");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute LogicalStackOrd
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute LogicalStackOrd
          * @param value: the value to set the attribute to
          */
        public void setLogicalStackOrd(int value)
        {
            setAttribute(AttributeName.LOGICALSTACKORD, value, null);
        }

        /**
          * (15) get int attribute LogicalStackOrd
          * @return int the value of the attribute
          */
        public int getLogicalStackOrd()
        {
            return getIntAttribute(AttributeName.LOGICALSTACKORD, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute LogicalStackSequence
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute LogicalStackSequence
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setLogicalStackSequence(EnumLogicalStackSequence enumVar)
        {
            setAttribute(AttributeName.LOGICALSTACKSEQUENCE, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute LogicalStackSequence
          * @return the value of the attribute
          */
        public EnumLogicalStackSequence getLogicalStackSequence()
        {
            return EnumLogicalStackSequence.getEnum(getAttribute(AttributeName.LOGICALSTACKSEQUENCE, null, "SheetIndex"));
        }

}// end namespace JDF
