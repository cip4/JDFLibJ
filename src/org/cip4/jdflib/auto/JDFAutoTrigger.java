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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.jmf.JDFAdded;
import org.cip4.jdflib.jmf.JDFChangedPath;
import org.cip4.jdflib.resource.JDFChangedAttribute;
import org.cip4.jdflib.resource.JDFRemoved;
import org.cip4.jdflib.resource.JDFResource;

public abstract class JDFAutoTrigger extends JDFResource
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.REPEATSTEP, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.REPEATTIME, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.CHANGEDATTRIBUTE, 0x44444433);
        elemInfoTable[1] = new ElemInfoTable(ElementName.ADDED, 0x77777766);
        elemInfoTable[2] = new ElemInfoTable(ElementName.CHANGEDPATH, 0x33333311);
        elemInfoTable[3] = new ElemInfoTable(ElementName.REMOVED, 0x77777766);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoTrigger
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoTrigger(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoTrigger
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoTrigger(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoTrigger
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoTrigger(
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
        return " JDFAutoTrigger[  --> " + super.toString() + " ]";
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
        Methods for Attribute RepeatStep
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RepeatStep
          * @param value: the value to set the attribute to
          */
        public void setRepeatStep(int value)
        {
            setAttribute(AttributeName.REPEATSTEP, value, null);
        }

        /**
          * (15) get int attribute RepeatStep
          * @return int the value of the attribute
          */
        public int getRepeatStep()
        {
            return getIntAttribute(AttributeName.REPEATSTEP, null, 0);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RepeatTime
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RepeatTime
          * @param value: the value to set the attribute to
          */
        public void setRepeatTime(double value)
        {
            setAttribute(AttributeName.REPEATTIME, value, null);
        }

        /**
          * (17) get double attribute RepeatTime
          * @return double the value of the attribute
          */
        public double getRepeatTime()
        {
            return getRealAttribute(AttributeName.REPEATTIME, null, 0.0);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateChangedAttribute
     * 
     * @param iSkip number of elements to skip
     * @return JDFChangedAttribute the element
     */
    public JDFChangedAttribute getCreateChangedAttribute(int iSkip)
    {
        return (JDFChangedAttribute)getCreateElement_KElement(ElementName.CHANGEDATTRIBUTE, null, iSkip);
    }

    /**
     * (27) const get element ChangedAttribute
     * @param iSkip number of elements to skip
     * @return JDFChangedAttribute the element
     * default is getChangedAttribute(0)     */
    public JDFChangedAttribute getChangedAttribute(int iSkip)
    {
        return (JDFChangedAttribute) getElement(ElementName.CHANGEDATTRIBUTE, null, iSkip);
    }

    /**
     * Get all ChangedAttribute from the current element
     * 
     * @return Collection<JDFChangedAttribute>, null if none are available
     */
    public Collection<JDFChangedAttribute> getAllChangedAttribute()
    {
        final VElement vc = getChildElementVector(ElementName.CHANGEDATTRIBUTE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFChangedAttribute> v = new Vector<JDFChangedAttribute>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFChangedAttribute) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element ChangedAttribute
     */
    public JDFChangedAttribute appendChangedAttribute() throws JDFException
    {
        return (JDFChangedAttribute) appendElement(ElementName.CHANGEDATTRIBUTE, null);
    }

    /**
     * (24) const get element Added
     * @return JDFAdded the element
     */
    public JDFAdded getAdded()
    {
        return (JDFAdded) getElement(ElementName.ADDED, null, 0);
    }

    /** (25) getCreateAdded
     * 
     * @return JDFAdded the element
     */
    public JDFAdded getCreateAdded()
    {
        return (JDFAdded) getCreateElement_KElement(ElementName.ADDED, null, 0);
    }

    /**
     * (29) append element Added
     */
    public JDFAdded appendAdded() throws JDFException
    {
        return (JDFAdded) appendElementN(ElementName.ADDED, 1, null);
    }

    /** (26) getCreateChangedPath
     * 
     * @param iSkip number of elements to skip
     * @return JDFChangedPath the element
     */
    public JDFChangedPath getCreateChangedPath(int iSkip)
    {
        return (JDFChangedPath)getCreateElement_KElement(ElementName.CHANGEDPATH, null, iSkip);
    }

    /**
     * (27) const get element ChangedPath
     * @param iSkip number of elements to skip
     * @return JDFChangedPath the element
     * default is getChangedPath(0)     */
    public JDFChangedPath getChangedPath(int iSkip)
    {
        return (JDFChangedPath) getElement(ElementName.CHANGEDPATH, null, iSkip);
    }

    /**
     * Get all ChangedPath from the current element
     * 
     * @return Collection<JDFChangedPath>, null if none are available
     */
    public Collection<JDFChangedPath> getAllChangedPath()
    {
        final VElement vc = getChildElementVector(ElementName.CHANGEDPATH, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFChangedPath> v = new Vector<JDFChangedPath>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFChangedPath) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element ChangedPath
     */
    public JDFChangedPath appendChangedPath() throws JDFException
    {
        return (JDFChangedPath) appendElement(ElementName.CHANGEDPATH, null);
    }

    /**
     * (24) const get element Removed
     * @return JDFRemoved the element
     */
    public JDFRemoved getRemoved()
    {
        return (JDFRemoved) getElement(ElementName.REMOVED, null, 0);
    }

    /** (25) getCreateRemoved
     * 
     * @return JDFRemoved the element
     */
    public JDFRemoved getCreateRemoved()
    {
        return (JDFRemoved) getCreateElement_KElement(ElementName.REMOVED, null, 0);
    }

    /**
     * (29) append element Removed
     */
    public JDFRemoved appendRemoved() throws JDFException
    {
        return (JDFRemoved) appendElementN(ElementName.REMOVED, 1, null);
    }

}// end namespace JDF
