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

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
    /*
    *****************************************************************************
    class JDFAutoResourceQuParams : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoResourceQuParams extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[10];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.CLASSES, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, JDFResource.EnumResourceClass.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.EXACT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.JOBID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.JOBPARTID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.PROCESSUSAGE, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.PRODUCTID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.QUEUEENTRYID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.RESOURCEID, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.RESOURCENAME, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.USAGE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, JDFResourceLink.EnumUsage.getEnum(0), null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.PART, 0x33333311);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoResourceQuParams
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoResourceQuParams(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoResourceQuParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoResourceQuParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoResourceQuParams
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoResourceQuParams(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoResourceQuParams[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Classes
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute Classes
          * @param v vector of the enumeration values
          */
        public void setClasses(Vector v)
        {
            setEnumerationsAttribute(AttributeName.CLASSES, v, null);
        }



        /**
          * (9.2) get Classes attribute Classes
          * @return Vector of the enumerations
          */
        public Vector getClasses()
        {
            return getEnumerationsAttribute(AttributeName.CLASSES, null, JDFResource.EnumResourceClass.getEnum(0), false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Exact
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Exact
          * @param value: the value to set the attribute to
          */
        public void setExact(boolean value)
        {
            setAttribute(AttributeName.EXACT, value, null);
        }



        /**
          * (18) get boolean attribute Exact
          * @return boolean the value of the attribute
          */
        public boolean getExact()
        {
            return getBoolAttribute(AttributeName.EXACT, null, false);
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
        Methods for Attribute JobPartID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute JobPartID
          * @param value: the value to set the attribute to
          */
        public void setJobPartID(String value)
        {
            setAttribute(AttributeName.JOBPARTID, value, null);
        }



        /**
          * (23) get String attribute JobPartID
          * @return the value of the attribute
          */
        public String getJobPartID()
        {
            return getAttribute(AttributeName.JOBPARTID, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ProcessUsage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ProcessUsage
          * @param value: the value to set the attribute to
          */
        public void setProcessUsage(String value)
        {
            setAttribute(AttributeName.PROCESSUSAGE, value, null);
        }



        /**
          * (23) get String attribute ProcessUsage
          * @return the value of the attribute
          */
        public String getProcessUsage()
        {
            return getAttribute(AttributeName.PROCESSUSAGE, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ProductID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ProductID
          * @param value: the value to set the attribute to
          */
        public void setProductID(String value)
        {
            setAttribute(AttributeName.PRODUCTID, value, null);
        }



        /**
          * (23) get String attribute ProductID
          * @return the value of the attribute
          */
        public String getProductID()
        {
            return getAttribute(AttributeName.PRODUCTID, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute QueueEntryID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute QueueEntryID
          * @param value: the value to set the attribute to
          */
        public void setQueueEntryID(String value)
        {
            setAttribute(AttributeName.QUEUEENTRYID, value, null);
        }



        /**
          * (23) get String attribute QueueEntryID
          * @return the value of the attribute
          */
        public String getQueueEntryID()
        {
            return getAttribute(AttributeName.QUEUEENTRYID, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ResourceID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ResourceID
          * @param value: the value to set the attribute to
          */
        public void setResourceID(String value)
        {
            setAttribute(AttributeName.RESOURCEID, value, null);
        }



        /**
          * (23) get String attribute ResourceID
          * @return the value of the attribute
          */
        public String getResourceID()
        {
            return getAttribute(AttributeName.RESOURCEID, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ResourceName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ResourceName
          * @param value: the value to set the attribute to
          */
        public void setResourceName(String value)
        {
            setAttribute(AttributeName.RESOURCENAME, value, null);
        }



        /**
          * (23) get String attribute ResourceName
          * @return the value of the attribute
          */
        public String getResourceName()
        {
            return getAttribute(AttributeName.RESOURCENAME, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Usage
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Usage
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setUsage(JDFResourceLink.EnumUsage enumVar)
        {
            setAttribute(AttributeName.USAGE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute Usage
          * @return the value of the attribute
          */
        public JDFResourceLink.EnumUsage getUsage()
        {
            return JDFResourceLink.EnumUsage.getEnum(getAttribute(AttributeName.USAGE, null, null));
        }



/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

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



    public JDFPart appendPart()
    {
        return (JDFPart) appendElement(ElementName.PART, null);
    }

}// end namespace JDF
