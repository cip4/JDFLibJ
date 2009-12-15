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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.VString;

public abstract class JDFAutoAncestor extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[19];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTIVATION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumActivation.getEnum(0), null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.FILENAME, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.ICSVERSIONS, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.JOBID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.JOBPARTID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.MAXVERSION, 0x33333311, AttributeInfo.EnumAttributeType.JDFJMFVersion, EnumVersion.getEnum(0), null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.NODEID, 0x22222222, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.PROJECTID, 0x33333333, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.RELATEDJOBID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.RELATEDJOBPARTID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.SPAWNID, 0x33333331, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.STATUS, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumNodeStatus.getEnum(0), null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.STATUSDETAILS, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.TEMPLATE, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable[14] = new AtrInfoTable(AttributeName.TEMPLATEID, 0x33333311, AttributeInfo.EnumAttributeType.shortString, null, null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.TEMPLATEVERSION, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.TYPE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.TYPES, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[18] = new AtrInfoTable(AttributeName.VERSION, 0x33333333, AttributeInfo.EnumAttributeType.JDFJMFVersion, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.CUSTOMERINFO, 0x66666661);
        elemInfoTable[1] = new ElemInfoTable(ElementName.NODEINFO, 0x66666661);
    }
    
    protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoAncestor
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoAncestor(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoAncestor
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoAncestor(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoAncestor
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoAncestor(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoAncestor[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for Activation
        */

        public static class EnumActivation extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumActivation(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumActivation getEnum(String enumName)
            {
                return (EnumActivation) getEnum(EnumActivation.class, enumName);
            }

            public static EnumActivation getEnum(int enumValue)
            {
                return (EnumActivation) getEnum(EnumActivation.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumActivation.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumActivation.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumActivation.class);
            }

            public static final EnumActivation Inactive = new EnumActivation("Inactive");
            public static final EnumActivation Informative = new EnumActivation("Informative");
            public static final EnumActivation Held = new EnumActivation("Held");
            public static final EnumActivation Active = new EnumActivation("Active");
            public static final EnumActivation TestRun = new EnumActivation("TestRun");
            public static final EnumActivation TestRunAndGo = new EnumActivation("TestRunAndGo");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute Activation
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Activation
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setActivation(EnumActivation enumVar)
        {
            setAttribute(AttributeName.ACTIVATION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute Activation
          * @return the value of the attribute
          */
        public EnumActivation getActivation()
        {
            return EnumActivation.getEnum(getAttribute(AttributeName.ACTIVATION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute FileName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FileName
          * @param value: the value to set the attribute to
          */
        public void setFileName(String value)
        {
            setAttribute(AttributeName.FILENAME, value, null);
        }

        /**
          * (23) get String attribute FileName
          * @return the value of the attribute
          */
        public String getFileName()
        {
            return getAttribute(AttributeName.FILENAME, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ICSVersions
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ICSVersions
          * @param value: the value to set the attribute to
          */
        public void setICSVersions(VString value)
        {
            setAttribute(AttributeName.ICSVERSIONS, value, null);
        }

        /**
          * (21) get VString attribute ICSVersions
          * @return VString the value of the attribute
          */
        public VString getICSVersions()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.ICSVERSIONS, null, JDFConstants.EMPTYSTRING);
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
        Methods for Attribute MaxVersion
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute MaxVersion
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setMaxVersion(EnumVersion enumVar)
        {
            setAttribute(AttributeName.MAXVERSION, enumVar==null ? null : enumVar.getName(), null);
        }

        /**
          * (9) get attribute MaxVersion
          * @return the value of the attribute
          */
        public EnumVersion getMaxVersion()
        {
            return EnumVersion.getEnum(getAttribute(AttributeName.MAXVERSION, null, null));
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute NodeID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NodeID
          * @param value: the value to set the attribute to
          */
        public void setNodeID(String value)
        {
            setAttribute(AttributeName.NODEID, value, null);
        }

        /**
          * (23) get String attribute NodeID
          * @return the value of the attribute
          */
        public String getNodeID()
        {
            return getAttribute(AttributeName.NODEID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute ProjectID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ProjectID
          * @param value: the value to set the attribute to
          */
        public void setProjectID(String value)
        {
            setAttribute(AttributeName.PROJECTID, value, null);
        }

        /**
          * (23) get String attribute ProjectID
          * @return the value of the attribute
          */
        public String getProjectID()
        {
            return getAttribute(AttributeName.PROJECTID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RelatedJobID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RelatedJobID
          * @param value: the value to set the attribute to
          */
        public void setRelatedJobID(String value)
        {
            setAttribute(AttributeName.RELATEDJOBID, value, null);
        }

        /**
          * (23) get String attribute RelatedJobID
          * @return the value of the attribute
          */
        public String getRelatedJobID()
        {
            return getAttribute(AttributeName.RELATEDJOBID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute RelatedJobPartID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RelatedJobPartID
          * @param value: the value to set the attribute to
          */
        public void setRelatedJobPartID(String value)
        {
            setAttribute(AttributeName.RELATEDJOBPARTID, value, null);
        }

        /**
          * (23) get String attribute RelatedJobPartID
          * @return the value of the attribute
          */
        public String getRelatedJobPartID()
        {
            return getAttribute(AttributeName.RELATEDJOBPARTID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute SpawnID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SpawnID
          * @param value: the value to set the attribute to
          */
        public void setSpawnID(String value)
        {
            setAttribute(AttributeName.SPAWNID, value, null);
        }

        /**
          * (23) get String attribute SpawnID
          * @return the value of the attribute
          */
        public String getSpawnID()
        {
            return getAttribute(AttributeName.SPAWNID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StatusDetails
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StatusDetails
          * @param value: the value to set the attribute to
          */
        public void setStatusDetails(String value)
        {
            setAttribute(AttributeName.STATUSDETAILS, value, null);
        }

        /**
          * (23) get String attribute StatusDetails
          * @return the value of the attribute
          */
        public String getStatusDetails()
        {
            return getAttribute(AttributeName.STATUSDETAILS, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Template
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Template
          * @param value: the value to set the attribute to
          */
        public void setTemplate(boolean value)
        {
            setAttribute(AttributeName.TEMPLATE, value, null);
        }

        /**
          * (18) get boolean attribute Template
          * @return boolean the value of the attribute
          */
        public boolean getTemplate()
        {
            return getBoolAttribute(AttributeName.TEMPLATE, null, false);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TemplateID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TemplateID
          * @param value: the value to set the attribute to
          */
        public void setTemplateID(String value)
        {
            setAttribute(AttributeName.TEMPLATEID, value, null);
        }

        /**
          * (23) get String attribute TemplateID
          * @return the value of the attribute
          */
        public String getTemplateID()
        {
            return getAttribute(AttributeName.TEMPLATEID, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute TemplateVersion
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TemplateVersion
          * @param value: the value to set the attribute to
          */
        public void setTemplateVersion(String value)
        {
            setAttribute(AttributeName.TEMPLATEVERSION, value, null);
        }

        /**
          * (23) get String attribute TemplateVersion
          * @return the value of the attribute
          */
        public String getTemplateVersion()
        {
            return getAttribute(AttributeName.TEMPLATEVERSION, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Type
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Type
          * @param value: the value to set the attribute to
          */
        public void setType(String value)
        {
            setAttribute(AttributeName.TYPE, value, null);
        }

        /**
          * (23) get String attribute Type
          * @return the value of the attribute
          */
        public String getType()
        {
            return getAttribute(AttributeName.TYPE, null, JDFConstants.EMPTYSTRING);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute Types
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Types
          * @param value: the value to set the attribute to
          */
        public void setTypes(VString value)
        {
            setAttribute(AttributeName.TYPES, value, null);
        }

        /**
          * (21) get VString attribute Types
          * @return VString the value of the attribute
          */
        public VString getTypes()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.TYPES, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /**
     * (24) const get element CustomerInfo
     * @return JDFCustomerInfo the element
     */
    public JDFCustomerInfo getCustomerInfo()
    {
        return (JDFCustomerInfo) getElement(ElementName.CUSTOMERINFO, null, 0);
    }

    /** (25) getCreateCustomerInfo
     * 
     * @return JDFCustomerInfo the element
     */
    public JDFCustomerInfo getCreateCustomerInfo()
    {
        return (JDFCustomerInfo) getCreateElement_KElement(ElementName.CUSTOMERINFO, null, 0);
    }

    /**
     * (29) append element CustomerInfo
     */
    public JDFCustomerInfo appendCustomerInfo() throws JDFException
    {
        return (JDFCustomerInfo) appendElementN(ElementName.CUSTOMERINFO, 1, null);
    }

    /**
     * (24) const get element NodeInfo
     * @return JDFNodeInfo the element
     */
    public JDFNodeInfo getNodeInfo()
    {
        return (JDFNodeInfo) getElement(ElementName.NODEINFO, null, 0);
    }

    /** (25) getCreateNodeInfo
     * 
     * @return JDFNodeInfo the element
     */
    public JDFNodeInfo getCreateNodeInfo()
    {
        return (JDFNodeInfo) getCreateElement_KElement(ElementName.NODEINFO, null, 0);
    }

    /**
     * (29) append element NodeInfo
     */
    public JDFNodeInfo appendNodeInfo() throws JDFException
    {
        return (JDFNodeInfo) appendElementN(ElementName.NODEINFO, 1, null);
    }

}// end namespace JDF
