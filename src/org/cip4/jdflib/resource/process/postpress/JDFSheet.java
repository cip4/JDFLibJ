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
/**
 * ==========================================================================
 * class JDFSheet
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process.postpress;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFSignature;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFSurface;

public class JDFSheet extends JDFSignature
{
    private static final long serialVersionUID = 1L;
    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[5];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.SURFACECONTENTSBOX, 0x44444333, AttributeInfo.EnumAttributeType.rectangle, null, null);        
        atrInfoTable[1] = new AtrInfoTable(AttributeName.LOCKORIGINS, 0x44444333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.NAME, 0x44444333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.SOURCEWORKSTYLE, 0x44444333, AttributeInfo.EnumAttributeType.enumeration, EnumSourceWorkStyle.getEnum(0), null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.SURFACECONTENTSBOX, 0x44444333, AttributeInfo.EnumAttributeType.rectangle, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        AttributeInfo ai=super.getTheAttributeInfo();
        if(getLocalName().equals(ElementName.SHEET))
            ai.updateReplace(atrInfoTable);
        return ai;
    }
   
    // Handle partitioned layouts
    private static ElemInfoTable[] elemInfoTable_Surface = new ElemInfoTable[1];
    static
    {
        elemInfoTable_Surface[0] = new ElemInfoTable(ElementName.SURFACE, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return new ElementInfo(super.getTheElementInfo(), elemInfoTable_Surface);
    }
    
    
    /**
     * Constructor for JDFSheet
     * @param ownerDocument
     * @param qualifiedName
     */
    public JDFSheet(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    /**
     * Constructor for JDFSheet
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFSheet(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFSheet
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFSheet(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    public String toString()
    {
        return "JDFSheet[  --> " + super.toString() + " ]";
    }
    
    //________________________________________________________________________________________
    // Start FRONT Surface
    //________________________________________________________________________________________
    
    public JDFSurface getCreateFrontSurface()
    {
        JDFSurface e= getSurface(EnumSide.Front);
        if(e == null)
        {
            e = appendSurface();
            e.setSide(JDFPart.EnumSide.Front);
        }
        
        return e;
    }
    
    
    /**
     * const get surface with the correct partition key
     */
    public JDFSurface getSurface(EnumSide side)
    {
        JDFSurface s=getSurface(0);
        if(s==null)
            return null;
        if(s.getSide()==side)
            return s;
        s=getSurface(1);
        if(s==null)
            return null;
        if(s.getSide()==side)
            return s;
        return null;
    }
    /**
     * const get first element
     * @deprecated use getSurface(EnumSide side)
     */
    public JDFSurface getFrontSurface()
    {
        return getSurface(EnumSide.Front);
    }
    
    
    public JDFSurface appendFrontSurface()
    {
        JDFSurface e = getSurface(EnumSide.Front);
        if(e != null)
        {
            throw new JDFException("appendFrontSurface surface already exists");
        }
        
        e = appendSurface();
        e.setSide(JDFPart.EnumSide.Front);
        return e;
    }
    
    /**
     * remove first element Surface
     */
    public void removeFrontSurface()
    {
        JDFSurface e = getSurface(EnumSide.Front);
        if(e != null)
        {
            e.deleteNode();
        }
    }
    /**
     * if this is a new layout, return the partition key signaturename
     * else return Signature/@Name of this or its appropriate parent
     * @return the name of the signature
     */
    public String getSheetName()
    {
        if(getLocalName().equals(ElementName.SHEET))
        {
            return getName();
        }
        if(getLocalName().equals(ElementName.SURFACE))
        {
            JDFSheet sh=(JDFSheet)getParentNode_KElement();
            return sh.getSheetName();
        }
        return super.getSheetName();
    }
    
    /**
     * test element Surface existance
     * @return boolean true if a matching element exists
     */
    public boolean hasFrontSurface()
    {
        return getSurface(EnumSide.Front) != null;
    }
    
    /**
     * create inter-resource link to refTarget
     * @param JDFSurface refTarget the element that is referenced
     */
    public void refFrontSurface(JDFSurface refTarget)
    {
        refElement(refTarget); 
        refTarget.setSide(EnumSide.Back);
    }
    
    //_______________________________________________________________
    // Start BACK Surface
    //_______________________________________________________________
    
    public JDFSurface getCreateBackSurface()
    {
        JDFSurface e = getSurface(EnumSide.Back);
        if(e == null)
        {
            e = appendBackSurface();
        }
        return e;
    }
    
    
    /**
     * const get first element
     * @deprecated use getSurface(EnumSide side)
     */
    public JDFSurface getBackSurface()
    {
        return getSurface(EnumSide.Back);
    }
    
    public JDFSurface appendBackSurface()
    {
        JDFSurface e = getSurface(EnumSide.Back);
        if(e != null)
        {
            throw new JDFException("appendBackSurface surface already exists");
        }
        
        e = appendSurface();
        e.setSide(JDFPart.EnumSide.Back);
        return e;
    }
    
    /**
     * remove back element Surface
     */
    public void removeBackSurface()
    {
        JDFSurface e = getSurface(EnumSide.Back);
        if( e != null)
        {
            e.deleteNode();
        }
    }
    
    
    /**
     * test element Surface existance
     * @return boolean true if a matching element exists
     */
    public boolean hasBackSurface()
    {
        return getSurface(EnumSide.Back) != null;
    }
    
    /**
     * create inter-resource link to refTarget
     * @param JDFSurface refTarget the element that is referenced
     */
    public void refBackSurface(JDFSurface refTarget)
    {
        refElement(refTarget);
        refTarget.setSide(EnumSide.Back);
    }
    
    /**
     * gets or appends a signature in both old and new Layouts
     * if old: a <Surface> element
     * if new: a Side partition leaf
     * @param iSkip the number of signatures to skip
     */
    public JDFSurface getCreateSurface(int iSkip)
    {
        JDFSurface s=getSurface(iSkip);
        if(s==null){
            s=appendSurface();
        }
        return s;
    }
    
    /**
     * gets a Surface in both old and new Layouts
     * if old: a <Surface> element
     * if new: a Side partition leaf
     * @param iSkip the number of signatures to skip
     */
    public JDFSurface getSurface(int iSkip)
    {
        return getLayoutElement(this,ElementName.SURFACE,AttributeName.SIDE,iSkip);
    }
    
    
    
    /**
     * appends a Surface in both old and new Layouts
     * if old: a <Surface> element
     * if new: a Side partition leaf
     */
    public JDFSurface appendSurface() throws JDFException
    {
        if(numSurfaces()>1)
            throw new JDFException("appendSurface: sheet already has two surfaces");
        
        return appendLayoutElement(this,ElementName.SURFACE,AttributeName.SIDE);
    }
    
    /**
     * counts the number of Surfaces in both old and new Layouts
     * if old: the number of <Surface> elements
     * if new: the number of Side partition leaves
     * @return the number of Surfaces
     */
    public int numSurfaces()
    {
        return numLayoutElements(this,ElementName.SURFACE, AttributeName.SIDE);
    }
    
    
    /**
     * (28) get vector of all direct child elements Surface
     * @param JDFAttributeMap mAttrib the map of attributes to select
     * @param boolean bAnd if true all attributes in the map are AND'ed, else they are OR'ed
     * @deprecated use getChildElementVector() instead
     */
    public VElement getSurfaceVector(JDFAttributeMap mAttrib, boolean bAnd)
    {
        VElement myResource = new VElement();
        Vector vElem = getChildElementVector(ElementName.SURFACE, null, mAttrib, bAnd, 0, true);
        for(int i = 0; i < vElem.size(); i++)
        {
            JDFElement k = (JDFElement) vElem.elementAt(i);
            JDFSurface myJDFSurface = (JDFSurface)k;
            myResource.addElement(myJDFSurface);
        }
        return myResource;
    }
    
    /**
     * @deprecated use getChildElementVector() instead
     */
    public VElement getSurfaceVector()
    {
        return getSurfaceVector(null, true);
    }
    
    
    /**
     * (31) create inter-resource link to refTarget
     * @param JDFSurface refTarget the element that is referenced
     */
    public void refSurface(JDFSurface refTarget)
    {
        if(JDFLayout.isNewLayout(this))
            throw new JDFException("refSheet: attempting to reference a partition");
        refElement(refTarget);
    }

} 
//==========================================================================
