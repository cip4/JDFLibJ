/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.process.JDFEmployee;
    /**
    *****************************************************************************
    class JDFAutoMerged : public JDFAudit

    *****************************************************************************
    */

public abstract class JDFAutoMerged extends JDFAudit
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.INDEPENDENT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.JREF, 0x22222222, AttributeInfo.EnumAttributeType.IDREF, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.JREFSOURCE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.MERGEID, 0x22222221, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.RREFSOVERWRITTEN, 0x33333333, AttributeInfo.EnumAttributeType.IDREFS, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.URL, 0x33333331, AttributeInfo.EnumAttributeType.URL, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.EMPLOYEE, 0x33333333);
        elemInfoTable[1] = new ElemInfoTable(ElementName.PART, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoMerged
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoMerged(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMerged
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoMerged(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoMerged
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoMerged(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    /**
     * @return  the string representation
     */
    @Override
    public String toString()
    {
        return " JDFAutoMerged[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Independent
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Independent
          * @param value the value to set the attribute to
          */
        public void setIndependent(boolean value)
        {
            setAttribute(AttributeName.INDEPENDENT, value, null);
        }

        /**
          * (18) get boolean attribute Independent
          * @return boolean the value of the attribute
          */
        public boolean getIndependent()
        {
            return getBoolAttribute(AttributeName.INDEPENDENT, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute jRef
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute jRef
          * @param value the value to set the attribute to
          */
        public void setjRef(String value)
        {
            setAttribute(AttributeName.JREF, value, null);
        }

        /**
          * (23) get String attribute jRef
          * @return the value of the attribute
          */
        public String getjRef()
        {
            return getAttribute(AttributeName.JREF, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute jRefSource
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute jRefSource
          * @param value the value to set the attribute to
          */
        public void setjRefSource(String value)
        {
            setAttribute(AttributeName.JREFSOURCE, value, null);
        }

        /**
          * (23) get String attribute jRefSource
          * @return the value of the attribute
          */
        public String getjRefSource()
        {
            return getAttribute(AttributeName.JREFSOURCE, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute MergeID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MergeID
          * @param value the value to set the attribute to
          */
        public void setMergeID(String value)
        {
            setAttribute(AttributeName.MERGEID, value, null);
        }

        /**
          * (23) get String attribute MergeID
          * @return the value of the attribute
          */
        public String getMergeID()
        {
            return getAttribute(AttributeName.MERGEID, null, JDFCoreConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute rRefsOverwritten
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute rRefsOverwritten
          * @param value the value to set the attribute to
          */
        public void setrRefsOverwritten(VString value)
        {
            setAttribute(AttributeName.RREFSOVERWRITTEN, value, null);
        }

        /**
          * (21) get VString attribute rRefsOverwritten
          * @return VString the value of the attribute
          */
        public VString getrRefsOverwritten()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.RREFSOVERWRITTEN, null, JDFCoreConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute URL
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute URL
          * @param value the value to set the attribute to
          */
        public void setURL(String value)
        {
            setAttribute(AttributeName.URL, value, null);
        }

        /**
          * (23) get String attribute URL
          * @return the value of the attribute
          */
        public String getURL()
        {
            return getAttribute(AttributeName.URL, null, JDFCoreConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateEmployee
     * 
     * @param iSkip number of elements to skip
     * @return JDFEmployee the element
     */
    public JDFEmployee getCreateEmployee(int iSkip)
    {
        return (JDFEmployee)getCreateElement_KElement(ElementName.EMPLOYEE, null, iSkip);
    }

    /**
     * (27) const get element Employee
     * @param iSkip number of elements to skip
     * @return JDFEmployee the element
     * default is getEmployee(0)     */
    public JDFEmployee getEmployee(int iSkip)
    {
        return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, iSkip);
    }

    /**
     * Get all Employee from the current element
     * 
     * @return Collection<JDFEmployee>, null if none are available
     */
    public Collection<JDFEmployee> getAllEmployee()
    {
        final VElement vc = getChildElementVector(ElementName.EMPLOYEE, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFEmployee> v = new Vector<JDFEmployee>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFEmployee) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Employee
     * @return JDFEmployee the element
     */
    public JDFEmployee appendEmployee()
    {
        return (JDFEmployee) appendElement(ElementName.EMPLOYEE, null);
    }

    /** (26) getCreatePart
     * 
     * @param iSkip number of elements to skip
     * @return JDFPart the element
     */
    public JDFPart getCreatePart(int iSkip)
    {
        return (JDFPart)getCreateElement_KElement(ElementName.PART, null, iSkip);
    }

    /**
     * (27) const get element Part
     * @param iSkip number of elements to skip
     * @return JDFPart the element
     * default is getPart(0)     */
    public JDFPart getPart(int iSkip)
    {
        return (JDFPart) getElement(ElementName.PART, null, iSkip);
    }

    /**
     * Get all Part from the current element
     * 
     * @return Collection<JDFPart>, null if none are available
     */
    public Collection<JDFPart> getAllPart()
    {
        final VElement vc = getChildElementVector(ElementName.PART, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFPart> v = new Vector<JDFPart>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFPart) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element Part
     * @return JDFPart the element
     */
    public JDFPart appendPart()
    {
        return (JDFPart) appendElement(ElementName.PART, null);
    }

}// end namespace JDF
