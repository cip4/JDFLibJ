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
    class JDFAutoBookCase : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoBookCase extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[3];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.HEADBANDS, 0x77777776);
        elemInfoTable[1] = new ElemInfoTable(ElementName.SHAPE, 0x77777776);
        elemInfoTable[2] = new ElemInfoTable(ElementName.THICKNESS, 0x77777776);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoBookCase
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoBookCase(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBookCase
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoBookCase(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoBookCase
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoBookCase(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoBookCase[  --> " + super.toString() + " ]";
    }


/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element HeadBands
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getHeadBands()
    {
        return (JDFOptionSpan) getElement(ElementName.HEADBANDS, null, 0);
    }

    /** (25) getCreateHeadBands
     * 
     * @return JDFOptionSpan the element
     */
    public JDFOptionSpan getCreateHeadBands()
    {
        return (JDFOptionSpan) getCreateElement_KElement(ElementName.HEADBANDS, null, 0);
    }

    /**
     * (29) append element HeadBands
     */
    public JDFOptionSpan appendHeadBands() throws JDFException
    {
        return (JDFOptionSpan) appendElementN(ElementName.HEADBANDS, 1, null);
    }

    /**
     * (24) const get element Shape
     * @return JDFSpanShape the element
     */
    public JDFSpanShape getShape()
    {
        return (JDFSpanShape) getElement(ElementName.SHAPE, null, 0);
    }

    /** (25) getCreateShape
     * 
     * @return JDFSpanShape the element
     */
    public JDFSpanShape getCreateShape()
    {
        return (JDFSpanShape) getCreateElement_KElement(ElementName.SHAPE, null, 0);
    }

    /**
     * (29) append element Shape
     */
    public JDFSpanShape appendShape() throws JDFException
    {
        return (JDFSpanShape) appendElementN(ElementName.SHAPE, 1, null);
    }

    /**
     * (24) const get element Thickness
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getThickness()
    {
        return (JDFNumberSpan) getElement(ElementName.THICKNESS, null, 0);
    }

    /** (25) getCreateThickness
     * 
     * @return JDFNumberSpan the element
     */
    public JDFNumberSpan getCreateThickness()
    {
        return (JDFNumberSpan) getCreateElement_KElement(ElementName.THICKNESS, null, 0);
    }

    /**
     * (29) append element Thickness
     */
    public JDFNumberSpan appendThickness() throws JDFException
    {
        return (JDFNumberSpan) appendElementN(ElementName.THICKNESS, 1, null);
    }

}// end namespace JDF
