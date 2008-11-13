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
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.process.JDFEmployee;

public abstract class JDFAutoSpawned extends JDFAudit
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[8];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.INDEPENDENT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[1] = new AtrInfoTable(AttributeName.JREF, 0x22222222, AttributeInfo.EnumAttributeType.IDREF, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.JREFDESTINATION, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.NEWSPAWNID, 0x22222221, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.RREFSROCOPIED, 0x33333333, AttributeInfo.EnumAttributeType.IDREFS, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.RREFSRWCOPIED, 0x33333333, AttributeInfo.EnumAttributeType.IDREFS, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.STATUS, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumNodeStatus.getEnum(0), null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.URL, 0x33333331, AttributeInfo.EnumAttributeType.URL, null, null);
    }
    
    @Override
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
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoSpawned
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoSpawned(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoSpawned
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoSpawned(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoSpawned
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoSpawned(
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
        return " JDFAutoSpawned[  --> " + super.toString() + " ]";
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
          * @param value: the value to set the attribute to
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
          * @param value: the value to set the attribute to
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
            return getAttribute(AttributeName.JREF, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute jRefDestination
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute jRefDestination
          * @param value: the value to set the attribute to
          */
        public void setjRefDestination(String value)
        {
            setAttribute(AttributeName.JREFDESTINATION, value, null);
        }

        /**
          * (23) get String attribute jRefDestination
          * @return the value of the attribute
          */
        public String getjRefDestination()
        {
            return getAttribute(AttributeName.JREFDESTINATION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NewSpawnID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NewSpawnID
          * @param value: the value to set the attribute to
          */
        public void setNewSpawnID(String value)
        {
            setAttribute(AttributeName.NEWSPAWNID, value, null);
        }

        /**
          * (23) get String attribute NewSpawnID
          * @return the value of the attribute
          */
        public String getNewSpawnID()
        {
            return getAttribute(AttributeName.NEWSPAWNID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute rRefsROCopied
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute rRefsROCopied
          * @param value: the value to set the attribute to
          */
        public void setrRefsROCopied(VString value)
        {
            setAttribute(AttributeName.RREFSROCOPIED, value, null);
        }

        /**
          * (21) get VString attribute rRefsROCopied
          * @return VString the value of the attribute
          */
        public VString getrRefsROCopied()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.RREFSROCOPIED, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute rRefsRWCopied
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute rRefsRWCopied
          * @param value: the value to set the attribute to
          */
        public void setrRefsRWCopied(VString value)
        {
            setAttribute(AttributeName.RREFSRWCOPIED, value, null);
        }

        /**
          * (21) get VString attribute rRefsRWCopied
          * @return VString the value of the attribute
          */
        public VString getrRefsRWCopied()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.RREFSRWCOPIED, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute URL
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute URL
          * @param value: the value to set the attribute to
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
            return getAttribute(AttributeName.URL, null, JDFConstants.EMPTYSTRING);
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
     * @return Collection<JDFEmployee>
     */
    public Collection<JDFEmployee> getAllEmployee()
    {
        Vector<JDFEmployee> v = new Vector<JDFEmployee>();

        JDFEmployee kElem = (JDFEmployee) getFirstChildElement(ElementName.EMPLOYEE, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFEmployee) kElem.getNextSiblingElement(ElementName.EMPLOYEE, null);
        }

        return v;
    }

    /**
     * (30) append element Employee
     */
    public JDFEmployee appendEmployee() throws JDFException
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
     * @return Collection<JDFPart>
     */
    public Collection<JDFPart> getAllPart()
    {
        Vector<JDFPart> v = new Vector<JDFPart>();

        JDFPart kElem = (JDFPart) getFirstChildElement(ElementName.PART, null);

        while (kElem != null)
        {
            v.add(kElem);

            kElem = (JDFPart) kElem.getNextSiblingElement(ElementName.PART, null);
        }

        return v;
    }

    /**
     * (30) append element Part
     */
    public JDFPart appendPart() throws JDFException
    {
        return (JDFPart) appendElement(ElementName.PART, null);
    }

}// end namespace JDF
