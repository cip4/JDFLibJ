/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFNode.java
 *
 * Last changes
 *
 * 2001-09-07   Torsten Kaehlert - if (!GetNodeName().equals("JDF")) changed from equals to !equals
 *              TKAE20010907
 *
 * 2001-07-30   Torsten Kaehlert - delete isNull() and throwNull() methods in parent class KNode
 *              TKAE20010730
 * 13-02-2002   Kai Mattern - added getSpawnID
 * 13-02-2002   Kai Mattern - added removeSpawnID
 * 15-02-2002   Kai Mattern - changed isValid in linkResource (added parameter ValidationLevel_Construct)
 * 06-02-2002   Kai Mattern - added LinkNames()
 * 06-02-2002   Kai Mattern - added LinkInfo()
 * 06-02-2002   Kai Mattern - added isValidLinkIndex()
 * 06-02-2002   Kai Mattern - added getLinksLength()
 * 06-02-2002   Kai Mattern - added getGenericLinksLength()
 * 06-02-2002   Kai Mattern - added EnumProcessUsage()
 * 06-02-2002   Kai Mattern - defined ant value GENERIC_LINKS_LENGHT;
 * 07-02-2002   Kai Mattern - added getMatchingResource()
 * 07-02-2002   Kai Mattern - added getMatchingLink()
 * 07-02-2002   Kai Mattern - added getMatchingLinks()
 * 08-02-2002   Kai Mattern - added MapEnumToInfo()
 * 12-02-2002   Kai Mattern - added NumMatchingLinks()
 * 12-02-2002   Kai Mattern - added removeMatchingLink()
 * 12-02-2002   Kai Mattern - added LinkMatchingResource()
 * 18-06-2002   JG  - added UnSpawn
 * 18-06-2002   JG  - modified activation list
 * 18-06-2002   JG  - added ProjectID support
 * 18-06-2002   JG  - modified getParentJDFNode()
 * 18-06-2002   JG  - added  bSpawnROPartsOnly to Spawn(...) and addSpawnedResources(...)
 * 18-06-2002   JG  - added getAncestorAttribute(), hasAncestorAttribute()
 * 18-06-2002   JG  - getMatchingLinks() minor bug fixes
 * 18-06-2002   JG  - renamed UnSpawn to UnSpawnNode()
 * 18-06-2002   JG  - added getAncestorElement(), hasAncestorElement()
 * 18-06-2002   JG  - fixed StatusPool Handling in UnspawnNode() and setPartStatus()
 * 18-06-2002   JG  - getMissingLinkVecor() bug fix
 * 18-06-2002   JG  - removed getAncestorNode -> use getParentJDFNode instead
 * 18-06-2002   JG  - calls to getInheritedAttribute replaced by calls to getAncestorAttribute
 * 18-06-2002   JG  - calls to hasAttribute replaced by calls to hasAncestorAttribute
 * 18-06-2002   JG  - addSpawnedResources() bug fix for appending to rRefsRO / rRefsRW, 
 *                    remove call to setLocked for root of partitioned resource
 * 10-09-2002   RP  - MapEnumToInfo >= --> > bug fix
 *
 */
package org.cip4.jdflib.node;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFCustomerMessage;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFPartStatus;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDocUserData;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAncestorPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.pool.JDFStatusPool;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFBusinessInfo;
import org.cip4.jdflib.resource.process.JDFCompany;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.resource.process.JDFNotificationFilter;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.Node;

/**
 *
 */
public class JDFNode extends JDFElement
{
    private static AtrInfoTable[] atrInfoTable_abstract = new AtrInfoTable[21];
    static
    {
        atrInfoTable_abstract[0] = new AtrInfoTable(AttributeName.ACTIVATION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration,EnumActivation.getEnum(0), null);
        atrInfoTable_abstract[1] = new AtrInfoTable(AttributeName.CATEGORY, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_abstract[2] = new AtrInfoTable(AttributeName.ICSVERSIONS, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable_abstract[3] = new AtrInfoTable(AttributeName.ID, 0x22222222, AttributeInfo.EnumAttributeType.ID, null, null);
        atrInfoTable_abstract[4] = new AtrInfoTable(AttributeName.JOBID, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_abstract[5] = new AtrInfoTable(AttributeName.JOBPARTID, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_abstract[6] = new AtrInfoTable(AttributeName.MAXVERSION, 0x33333311, AttributeInfo.EnumAttributeType.JDFJMFVersion, null, null);
        atrInfoTable_abstract[7] = new AtrInfoTable(AttributeName.NAMEDFEATURES, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable_abstract[8] = new AtrInfoTable(AttributeName.PROJECTID, 0x33333331, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_abstract[9] = new AtrInfoTable(AttributeName.RELATEDJOBID, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_abstract[10] = new AtrInfoTable(AttributeName.RELATEDJOBPARTID, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_abstract[11] = new AtrInfoTable(AttributeName.SPAWNID, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_abstract[12] = new AtrInfoTable(AttributeName.STATUS, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumNodeStatus.getEnum(0), null);
        atrInfoTable_abstract[13] = new AtrInfoTable(AttributeName.STATUSDETAILS, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_abstract[14] = new AtrInfoTable(AttributeName.TEMPLATE, 0x33333331, AttributeInfo.EnumAttributeType.boolean_, null,"false");
        atrInfoTable_abstract[15] = new AtrInfoTable(AttributeName.TEMPLATEID, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_abstract[16] = new AtrInfoTable(AttributeName.TEMPLATEVERSION, 0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_abstract[17] = new AtrInfoTable(AttributeName.TYPE, 0x22222222, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_abstract[18] = new AtrInfoTable(AttributeName.VERSION, 0x33333333, AttributeInfo.EnumAttributeType.JDFJMFVersion, EnumVersion.getEnum(0), null);
        atrInfoTable_abstract[19] = new AtrInfoTable(AttributeName.XMLNS, 0x33333331, AttributeInfo.EnumAttributeType.URI, EnumVersion.getEnum(0), null);
        atrInfoTable_abstract[20] = new AtrInfoTable(AttributeName.XSITYPE, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, EnumVersion.getEnum(0), null);
    }
    private static AtrInfoTable[] atrInfoTable_root = new AtrInfoTable[2];
    static
    {
        atrInfoTable_root[0] = new AtrInfoTable(AttributeName.VERSION, 0x22222222, AttributeInfo.EnumAttributeType.JDFJMFVersion, EnumVersion.getEnum(0), null);
        atrInfoTable_root[1] = new AtrInfoTable(AttributeName.XMLNS, 0x22222221, AttributeInfo.EnumAttributeType.URI, null, null);
    }

    private static AtrInfoTable[] atrInfoTable_Combined = new AtrInfoTable[1];
    static
    {
        atrInfoTable_Combined[0] = new AtrInfoTable(AttributeName.TYPES, 0x22222222, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
    }   

    private static AtrInfoTable[] atrInfoTable_PG = new AtrInfoTable[1];
    static
    {
        atrInfoTable_PG[0] = new AtrInfoTable(AttributeName.TYPES, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
    }   

    /**
     * definition of optional attributes in the JDF namespace
     * @return comma separated list of optional attributes for JDF nodes
     */
    protected AttributeInfo getTheAttributeInfo() 
    {
        AttributeInfo ai = super.getTheAttributeInfo().updateReplace(atrInfoTable_abstract);
        final String nodeType = getType();
        if (JDFConstants.PROCESSGROUP.equals(nodeType) && !hasChildElement(ElementName.JDF, null))
        {
            ai.updateAdd(atrInfoTable_PG);
        }
        else if (JDFConstants.COMBINED.equals(nodeType))
        {
            ai.updateAdd(atrInfoTable_Combined);
        }

        if (isJDFRoot())
        {
            ai.updateReplace(atrInfoTable_root);
        }

        return ai;
    }


    private static ElemInfoTable[] elemInfoTable_abstract = new ElemInfoTable[6];
    static 
    {
        elemInfoTable_abstract[0] = new ElemInfoTable(ElementName.AUDITPOOL, 0x66666666);
        elemInfoTable_abstract[1] = new ElemInfoTable(ElementName.CUSTOMERINFO, 0x77777666);
        elemInfoTable_abstract[2] = new ElemInfoTable(ElementName.NODEINFO, 0x77777666);
        elemInfoTable_abstract[3] = new ElemInfoTable(ElementName.RESOURCELINKPOOL, 0x66666666);
        elemInfoTable_abstract[4] = new ElemInfoTable(ElementName.RESOURCEPOOL, 0x66666666);
        elemInfoTable_abstract[5] = new ElemInfoTable(ElementName.STATUSPOOL, 0x77777666);
    }

    private static ElemInfoTable[] elemInfoTable_root = new ElemInfoTable[1];
    static
    {
        elemInfoTable_root[0] = new ElemInfoTable(ElementName.ANCESTORPOOL, 0x66666666);
    }

    private static ElemInfoTable[] elemInfoTable_JDF = new ElemInfoTable[1];
    static
    {
        elemInfoTable_JDF[0] = new ElemInfoTable(ElementName.JDF, 0x33333333);
    }

    /**
     * 
     */
    protected ElementInfo getTheElementInfo() 
    {        
        ElementInfo ei = super.getTheElementInfo().updateReplace(elemInfoTable_abstract);

        final String typ = getType();
        if (JDFConstants.PROCESSGROUP.equals(typ) || JDFConstants.PRODUCT.equals(typ))
        {               
            ei.updateAdd(elemInfoTable_JDF);
        }

        if (isJDFRoot())
        {
            ei.updateAdd(elemInfoTable_root);
        }

        return ei;
    }



    /**
     * Constructor for JDFNode
     * @param ownerDocument
     * @param qualifiedName
     */
    public JDFNode(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFNode
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFNode(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFNode
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFNode(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     *  Member Variablen
     */
    private static HashMap m_LinkNamesMap = new HashMap();
    private static HashMap m_LinkInfoMap = new HashMap();


    private static final String[] m_GenericLinkInfo =
    {
        JDFConstants.INPUT_ZEROTOINFINITY ,//APPROVALSUCCESS
        JDFConstants.INPUT_ZEROTOONE,      //CUSTOMERINFO
        JDFConstants.INPUT_ZEROTOINFINITY, //DEVICE
        JDFConstants.INPUT_ZEROTOINFINITY, //EMPLOYEE
        JDFConstants.INPUT_ZEROTOINFINITY, //MISCCONSUMABLE
        JDFConstants.INPUT_ZEROTOONE,      //NODEINFO
        JDFConstants.INPUT_ZEROTOINFINITY, //PREFLIGHTREPORT
        JDFConstants.INPUT_ZEROTOINFINITY, //PREVIEW
        JDFConstants.INPUT_ZEROTOINFINITY, //TOOL
        JDFConstants.INPUT_ZEROTOINFINITY  //USAGECOUNTER
    };                                

    private static String[] m_strGenericLinkNames =
    {
        ElementName.APPROVALSUCCESS   ,
        ElementName.CUSTOMERINFO      ,
        ElementName.DEVICE            ,
        ElementName.EMPLOYEE          ,
        ElementName.MISCCONSUMABLE    ,
        ElementName.NODEINFO          ,
        ElementName.PREFLIGHTREPORT   , 
        ElementName.PREVIEW           , 
        ElementName.TOOL              ,
        ElementName.USAGECOUNTER
    };


    private static final long serialVersionUID = 1L;

    /**
     * exception id for multiple merge attempt
     * @deprecated use JDFSpawn.exAlreadyMerged
     */
    public static final int exAlreadyMerged=JDFSpawn.exAlreadyMerged;
    /**
     * exception id for multiple rw spawns
     * @deprecated use JDFSpawn.exMultiSpawnRW
     */
    public static final int exMultiSpawnRW=JDFSpawn.exMultiSpawnRW;


    public static final class EnumCleanUpMerge extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;

        private static int m_startValue = 0;

        public String toString()
        {
            return getName();
        }


        protected EnumCleanUpMerge(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumCleanUpMerge getEnum(String enumName)
        {
            return (EnumCleanUpMerge) getEnum(EnumCleanUpMerge.class, enumName);
        }

        public static EnumCleanUpMerge getEnum(int enumValue)
        {
            return (EnumCleanUpMerge) getEnum(EnumCleanUpMerge.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumCleanUpMerge.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumCleanUpMerge.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumCleanUpMerge.class);
        }

        /**
         * @deprecated
         * @return
         */
         public static Vector getNamesVector()
         {
             final Vector namesVector = new Vector();
             final Iterator it = iterator(EnumActivation.class);
             while (it.hasNext())
             {
                 namesVector.addElement(((ValuedEnum) it.next()).getName());
             }

             return namesVector;
         }

         /**
          * Constants EnumActivation
          */
         public static final EnumCleanUpMerge None         = new EnumCleanUpMerge(JDFConstants.CLEANUPMERGE_NONE);
         public static final EnumCleanUpMerge RemoveRRefs  = new EnumCleanUpMerge(JDFConstants.CLEANUPMERGE_REMOVERREFS);
         public static final EnumCleanUpMerge RemoveAll    = new EnumCleanUpMerge(JDFConstants.CLEANUPMERGE_REMOVEALL);
    }

    /**
     * inner class EnumActivation
     */
    public static final class EnumActivation extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;

        private static int m_startValue = 0;

        protected EnumActivation(String name)
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

        /**
         * @deprecated
         * @return
         */
        public static Vector getNamesVector()
        {
            final Vector namesVector = new Vector();
            final Iterator it = iterator(EnumActivation.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }

            return namesVector;
        }

        /**
         * Constants EnumActivation
         */
        /**
         * @deprectated
         */
        public static final EnumActivation Unknown = null;
        public static final EnumActivation Inactive = new EnumActivation(JDFConstants.ACTIVATION_INACTIVE);
        public static final EnumActivation Informative = new EnumActivation(JDFConstants.ACTIVATION_INFORMATIVE);
        public static final EnumActivation Held = new EnumActivation(JDFConstants.ACTIVATION_HELD);
        public static final EnumActivation TestRun = new EnumActivation(JDFConstants.ACTIVATION_TESTRUN);
        public static final EnumActivation TestRunAndGo = new EnumActivation(JDFConstants.ACTIVATION_TESTRUNANDGO);
        public static final EnumActivation Active = new EnumActivation(JDFConstants.ACTIVATION_ACTIVE); }

    /**
     * Constants EnumActivation
     * use EnumActivation.xxx instead of the deprecated constants EnumActivation.Activation_xxx
     */

    /** @deprecated */
    public static final EnumActivation Activation_Inactive      = EnumActivation.Inactive;  
    /** @deprecated */
    public static final EnumActivation Activation_Informative   = EnumActivation.Informative;
    /** @deprecated */
    public static final EnumActivation Activation_Held          = EnumActivation.Held;
    /** @deprecated */
    public static final EnumActivation Activation_TestRun       = EnumActivation.TestRun;
    /** @deprecated */
    public static final EnumActivation Activation_TestRunAndGo  = EnumActivation.TestRunAndGo;
    /** @deprecated */
    public static final EnumActivation Activation_Active        = EnumActivation.Active;

    /**
     * inner class EnumType
     */
    public static final class EnumType extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;

        private static int m_startValue = 0;

        protected EnumType(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumType getEnum(String enumName)
        {
            EnumType myEnum = (EnumType)getEnum(EnumType.class, enumName);
            return myEnum;
        }

        public static EnumType getEnum(int enumValue)
        {
            return (EnumType) getEnum(EnumType.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumType.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumType.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumType.class);
        }

        /**
         * @deprecated
         * @return
         */
        public static Vector getNamesVector()
        {
            final Vector namesVector = new Vector();
            final Iterator it = iterator(EnumType.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }

            return namesVector;
        }

        // generic
        public static final EnumType ProcessGroup = new EnumType(JDFConstants.TYPE_PROCESSGROUP);
        public static final EnumType Combined = new EnumType(JDFConstants.TYPE_COMBINED);
        public static final EnumType Product = new EnumType(JDFConstants.TYPE_PRODUCT);
        public static final EnumType Approval = new EnumType(JDFConstants.TYPE_APPROVAL);
        public static final EnumType Buffer = new EnumType(JDFConstants.TYPE_BUFFER);
        public static final EnumType Combine = new EnumType(JDFConstants.TYPE_COMBINE);
        public static final EnumType Delivery = new EnumType(JDFConstants.TYPE_DELIVERY);
        public static final EnumType ManualLabor = new EnumType(JDFConstants.TYPE_MANUALLABOR);
        public static final EnumType Ordering = new EnumType(JDFConstants.TYPE_ORDERING);
        public static final EnumType Packing = new EnumType(JDFConstants.TYPE_PACKING);
        public static final EnumType QualityControl = new EnumType(JDFConstants.TYPE_QUALITYCONTROL);
        public static final EnumType ResourceDefinition = new EnumType(JDFConstants.TYPE_RESOURCEDEFINITION);
        public static final EnumType Split = new EnumType(JDFConstants.TYPE_SPLIT);
        public static final EnumType Verification = new EnumType(JDFConstants.TYPE_VERIFICATION);

        // prepress
        public static final EnumType AssetListCreation = new EnumType(JDFConstants.TYPE_ASSETLISTCREATION);
        public static final EnumType Bending = new EnumType(JDFConstants.TYPE_BENDING);
        public static final EnumType ColorCorrection = new EnumType(JDFConstants.TYPE_COLORCORRECTION);
        public static final EnumType ColorSpaceConversion = new EnumType(JDFConstants.TYPE_COLORSPACECONVERSION);
        public static final EnumType ContactCopying = new EnumType(JDFConstants.TYPE_CONTACTCOPYING);
        public static final EnumType ContoneCalibration = new EnumType(JDFConstants.TYPE_CONTONECALIBRATION);
        public static final EnumType DBDocTemplateLayout = new EnumType(JDFConstants.TYPE_DBDOCTEMPLATELAYOUT);
        public static final EnumType DBTemplateMerging = new EnumType(JDFConstants.TYPE_DBTEMPLATEMERGING);
        public static final EnumType DigitalDelivery = new EnumType(JDFConstants.TYPE_DIGITALDELIVERY);
        public static final EnumType FilmToPlateCopying = new EnumType(JDFConstants.TYPE_FILMTOPLATECOPYING);
        public static final EnumType FormatConversion = new EnumType(JDFConstants.TYPE_FORMATCONVERSION);
        public static final EnumType ImageReplacement = new EnumType(JDFConstants.TYPE_IMAGEREPLACEMENT);
        public static final EnumType ImageSetting = new EnumType(JDFConstants.TYPE_IMAGESETTING);
        public static final EnumType Imposition = new EnumType(JDFConstants.TYPE_IMPOSITION);
        public static final EnumType InkZoneCalculation = new EnumType(JDFConstants.TYPE_INKZONECALCULATION);
        public static final EnumType Interpreting = new EnumType(JDFConstants.TYPE_INTERPRETING);
        public static final EnumType LayoutElementProduction = new EnumType(JDFConstants.TYPE_LAYOUTELEMENTPRODUCTION);
        public static final EnumType LayoutPreparation = new EnumType(JDFConstants.TYPE_LAYOUTPREPARATION);
        public static final EnumType PDFToPSConversion = new EnumType(JDFConstants.TYPE_PDFTOPSCONVERSION);
        public static final EnumType Preflight = new EnumType(JDFConstants.TYPE_PREFLIGHT);
        public static final EnumType PreviewGeneration = new EnumType(JDFConstants.TYPE_PREVIEWGENERATION);
        public static final EnumType Proofing = new EnumType(JDFConstants.TYPE_PROOFING);
        public static final EnumType PSToPDFConversion = new EnumType(JDFConstants.TYPE_PSTOPDFCONVERSION);
        public static final EnumType Rendering = new EnumType(JDFConstants.TYPE_RENDERING);
        public static final EnumType Scanning = new EnumType(JDFConstants.TYPE_SCANNING);
        public static final EnumType Screening = new EnumType(JDFConstants.TYPE_SCREENING);
        public static final EnumType Separation = new EnumType(JDFConstants.TYPE_SEPARATION);
        public static final EnumType SoftProofing = new EnumType(JDFConstants.TYPE_SOFTPROOFING);
        public static final EnumType Stripping = new EnumType(JDFConstants.TYPE_STRIPPING);
        public static final EnumType Tiling = new EnumType(JDFConstants.TYPE_TILING);
        public static final EnumType Trapping = new EnumType(JDFConstants.TYPE_TRAPPING);

        // press
        public static final EnumType ConventionalPrinting = new EnumType(JDFConstants.TYPE_CONVENTIONALPRINTING);
        public static final EnumType DigitalPrinting = new EnumType(JDFConstants.TYPE_DIGITALPRINTING);
        public static final EnumType IDPrinting = new EnumType(JDFConstants.TYPE_IDPRINTING);

        //postpress
        public static final EnumType AdhesiveBinding = new EnumType(JDFConstants.TYPE_ADHESIVEBINDING);
        public static final EnumType BlockPreparation = new EnumType(JDFConstants.TYPE_BLOCKPREPARATION);
        public static final EnumType BoxPacking = new EnumType(JDFConstants.TYPE_BOXPACKING);
        public static final EnumType BoxFolding = new EnumType(JDFConstants.TYPE_BOXFOLDING);
        public static final EnumType Bundling = new EnumType(JDFConstants.TYPE_BUNDLING);
        public static final EnumType CaseMaking = new EnumType(JDFConstants.TYPE_CASEMAKING);
        public static final EnumType CasingIn = new EnumType(JDFConstants.TYPE_CASINGIN);
        public static final EnumType ChannelBinding = new EnumType(JDFConstants.TYPE_CHANNELBINDING);
        public static final EnumType CoilBinding = new EnumType(JDFConstants.TYPE_COILBINDING);
        public static final EnumType Collecting = new EnumType(JDFConstants.TYPE_COLLECTING);
        public static final EnumType CoverApplication = new EnumType(JDFConstants.TYPE_COVERAPPLICATION);
        public static final EnumType Creasing = new EnumType(JDFConstants.TYPE_CREASING);
        public static final EnumType Cutting = new EnumType(JDFConstants.TYPE_CUTTING);
        public static final EnumType Dividing = new EnumType(JDFConstants.TYPE_DIVIDING);
        public static final EnumType Embossing = new EnumType(JDFConstants.TYPE_EMBOSSING);
        public static final EnumType EndSheetGluing = new EnumType(JDFConstants.TYPE_ENDSHEETGLUING);
        public static final EnumType Feeding = new EnumType(JDFConstants.TYPE_FEEDING);
        public static final EnumType Folding = new EnumType(JDFConstants.TYPE_FOLDING);
        public static final EnumType Gathering = new EnumType(JDFConstants.TYPE_GATHERING);
        public static final EnumType Gluing = new EnumType(JDFConstants.TYPE_GLUING);
        public static final EnumType HeadBandApplication = new EnumType(JDFConstants.TYPE_HEADBANDAPPLICATION);
        public static final EnumType HoleMaking = new EnumType(JDFConstants.TYPE_HOLEMAKING);
        public static final EnumType Inserting = new EnumType(JDFConstants.TYPE_INSERTING);
        public static final EnumType Jacketing = new EnumType(JDFConstants.TYPE_JACKETING);
        public static final EnumType Labeling = new EnumType(JDFConstants.TYPE_LABELING);
        public static final EnumType Laminating = new EnumType(JDFConstants.TYPE_LAMINATING);
        public static final EnumType LongitudinalRibbonOperations = new EnumType(JDFConstants.TYPE_LONGITUDINALRIBBONOPERATIONS);
        public static final EnumType Numbering = new EnumType(JDFConstants.TYPE_NUMBERING);
        public static final EnumType Palletizing = new EnumType(JDFConstants.TYPE_PALLETIZING);
        public static final EnumType Perforating = new EnumType(JDFConstants.TYPE_PERFORATING);
        public static final EnumType PlasticCombBinding = new EnumType(JDFConstants.TYPE_PLASTICCOMBBINDING);
        public static final EnumType PrintRolling = new EnumType(JDFConstants.TYPE_PRINTROLLING);
        public static final EnumType RingBinding = new EnumType(JDFConstants.TYPE_RINGBINDING);
        public static final EnumType SaddleStitching = new EnumType(JDFConstants.TYPE_SADDLESTITCHING);
        public static final EnumType ShapeCutting = new EnumType(JDFConstants.TYPE_SHAPECUTTING);
        public static final EnumType Shrinking = new EnumType(JDFConstants.TYPE_SHRINKING);
        public static final EnumType SideSewing = new EnumType(JDFConstants.TYPE_SIDESEWING);
        public static final EnumType SpinePreparation = new EnumType(JDFConstants.TYPE_SPINEPREPARATION);
        public static final EnumType SpineTaping = new EnumType(JDFConstants.TYPE_SPINETAPING);
        public static final EnumType Stacking = new EnumType(JDFConstants.TYPE_STACKING);
        public static final EnumType Stitching = new EnumType(JDFConstants.TYPE_STITCHING);
        public static final EnumType Strapping = new EnumType(JDFConstants.TYPE_STRAPPING);
        public static final EnumType StripBinding = new EnumType(JDFConstants.TYPE_STRIPBINDING);
        public static final EnumType ThreadSealing = new EnumType(JDFConstants.TYPE_THREADSEALING);
        public static final EnumType ThreadSewing = new EnumType(JDFConstants.TYPE_THREADSEWING);
        public static final EnumType Trimming = new EnumType(JDFConstants.TYPE_TRIMMING);
        public static final EnumType WireCombBinding = new EnumType(JDFConstants.TYPE_WIRECOMBBINDING);
        public static final EnumType Wrapping = new EnumType(JDFConstants.TYPE_WRAPPING);
    }

    /**
     * Constants EnumType
     * use EnumType.xxx instead of the deprecated constants EnumType.Type_xxx
     */
    /** @deprecated */
    public static final EnumType Type_ProcessGroup = EnumType.ProcessGroup;
    /** @deprecated */
    public static final EnumType Type_Combined = EnumType.Combined;
    /** @deprecated */
    public static final EnumType Type_Product = EnumType.Product;
    /** @deprecated */
    public static final EnumType Type_Approval = EnumType.Approval;
    /** @deprecated */
    public static final EnumType Type_Buffer = EnumType.Buffer;
    /** @deprecated */
    public static final EnumType Type_Combine = EnumType.Combine;
    /** @deprecated */
    public static final EnumType Type_Delivery = EnumType.Delivery;
    /** @deprecated */
    public static final EnumType Type_ManualLabor = EnumType.ManualLabor;
    /** @deprecated */
    public static final EnumType Type_Ordering = EnumType.Ordering;
    /** @deprecated */
    public static final EnumType Type_Packing = EnumType.Packing;
    /** @deprecated */
    public static final EnumType Type_QualityControl = EnumType.QualityControl;
    /** @deprecated */
    public static final EnumType Type_ResourceDefinition = EnumType.ResourceDefinition;
    /** @deprecated */
    public static final EnumType Type_Split = EnumType.Split;
    /** @deprecated */
    public static final EnumType Type_Verification = EnumType.Verification;
    /** @deprecated */
    public static final EnumType Type_AssetListCreation = EnumType.AssetListCreation;
    /** @deprecated */
    public static final EnumType Type_ColorCorrection = EnumType.ColorCorrection;
    /** @deprecated */
    public static final EnumType Type_ColorSpaceConversion = EnumType.ColorSpaceConversion;
    /** @deprecated */
    public static final EnumType Type_ContactCopying = EnumType.ContactCopying;
    /** @deprecated */
    public static final EnumType Type_ContoneCalibration = EnumType.ContoneCalibration;
    /** @deprecated */
    public static final EnumType Type_DBDocTemplateLayout = EnumType.DBDocTemplateLayout;
    /** @deprecated */
    public static final EnumType Type_DBTemplateMerging = EnumType.DBTemplateMerging;
    /** @deprecated */
    public static final EnumType Type_DigitalDelivery = EnumType.DigitalDelivery;
    /** @deprecated */
    public static final EnumType Type_FilmToPlateCopying = EnumType.FilmToPlateCopying;
    /** @deprecated */
    public static final EnumType Type_FormatConversion = EnumType.FormatConversion;
    /** @deprecated */
    public static final EnumType Type_ImageReplacement = EnumType.ImageReplacement;
    /** @deprecated */
    public static final EnumType Type_ImageSetting = EnumType.ImageSetting;
    /** @deprecated */
    public static final EnumType Type_Imposition = EnumType.Imposition;
    /** @deprecated */
    public static final EnumType Type_InkZoneCalculation = EnumType.InkZoneCalculation;
    /** @deprecated */
    public static final EnumType Type_Interpreting = EnumType.Interpreting;
    /** @deprecated */
    public static final EnumType Type_LayoutElementProduction = EnumType.LayoutElementProduction;
    /** @deprecated */
    public static final EnumType Type_LayoutPreparation = EnumType.LayoutPreparation;
    /** @deprecated */
    public static final EnumType Type_PDFToPSConversion = EnumType.PDFToPSConversion;
    /** @deprecated */
    public static final EnumType Type_Preflight = EnumType.Preflight;
    /** @deprecated */
    public static final EnumType Type_PreviewGeneration = EnumType.PreviewGeneration;
    /** @deprecated */
    public static final EnumType Type_Proofing = EnumType.Proofing;
    /** @deprecated */
    public static final EnumType Type_PSToPDFConversion = EnumType.PSToPDFConversion;
    /** @deprecated */
    public static final EnumType Type_Rendering = EnumType.Rendering;
    /** @deprecated */
    public static final EnumType Type_Scanning = EnumType.Scanning;
    /** @deprecated */
    public static final EnumType Type_Screening = EnumType.Screening;
    /** @deprecated */
    public static final EnumType Type_Separation = EnumType.Separation;
    /** @deprecated */
    public static final EnumType Type_SoftProofing = EnumType.SoftProofing;
    /** @deprecated */
    public static final EnumType Type_Stripping = EnumType.Stripping;
    /** @deprecated */
    public static final EnumType Type_Tiling = EnumType.Tiling;
    /** @deprecated */
    public static final EnumType Type_Trapping = EnumType.Trapping;
    /** @deprecated */
    public static final EnumType Type_ConventionalPrinting = EnumType.ConventionalPrinting;
    /** @deprecated */
    public static final EnumType Type_DigitalPrinting = EnumType.DigitalPrinting;
    /** @deprecated */
    public static final EnumType Type_IDPrinting = EnumType.IDPrinting;
    /** @deprecated */
    public static final EnumType Type_AdhesiveBinding = EnumType.AdhesiveBinding;
    /** @deprecated */
    public static final EnumType Type_BlockPreparation = EnumType.BlockPreparation;
    /** @deprecated */
    public static final EnumType Type_BoxPacking = EnumType.BoxPacking;
    /** @deprecated */
    public static final EnumType Type_Bundling = EnumType.Bundling;
    /** @deprecated */
    public static final EnumType Type_CaseMaking = EnumType.CaseMaking ;
    /** @deprecated */
    public static final EnumType Type_CasingIn = EnumType.CasingIn ;
    /** @deprecated */
    public static final EnumType Type_ChannelBinding = EnumType.ChannelBinding ;
    /** @deprecated */
    public static final EnumType Type_CoilBinding = EnumType.CoilBinding;
    /** @deprecated */
    public static final EnumType Type_Collecting = EnumType.Collecting;
    /** @deprecated */
    public static final EnumType Type_CoverApplication = EnumType.CoverApplication ;
    /** @deprecated */
    public static final EnumType Type_Creasing = EnumType.Creasing;
    /** @deprecated */
    public static final EnumType Type_Cutting = EnumType.Cutting;
    /** @deprecated */
    public static final EnumType Type_Dividing = EnumType.Dividing;
    /** @deprecated */
    public static final EnumType Type_Embossing = EnumType.Embossing;
    /** @deprecated */
    public static final EnumType Type_EndSheetGluing = EnumType.EndSheetGluing;
    /** @deprecated */
    public static final EnumType Type_Feeding = EnumType.Feeding;
    /** @deprecated */
    public static final EnumType Type_Folding = EnumType.Folding;
    /** @deprecated */
    public static final EnumType Type_Gathering = EnumType.Gathering;
    /** @deprecated */
    public static final EnumType Type_Gluing = EnumType.Gluing;
    /** @deprecated */
    public static final EnumType Type_HeadBandApplication = EnumType.HeadBandApplication;
    /** @deprecated */
    public static final EnumType Type_HoleMaking = EnumType.HoleMaking;
    /** @deprecated */
    public static final EnumType Type_Inserting = EnumType.Inserting;
    /** @deprecated */
    public static final EnumType Type_Jacketing = EnumType.Jacketing;
    /** @deprecated */
    public static final EnumType Type_Labeling = EnumType.Labeling;
    /** @deprecated */
    public static final EnumType Type_Laminating = EnumType.Laminating;
    /** @deprecated */
    public static final EnumType Type_LongitudinalRibbonOperations = EnumType.LongitudinalRibbonOperations;
    /** @deprecated */
    public static final EnumType Type_Numbering = EnumType.Numbering;
    /** @deprecated */
    public static final EnumType Type_Palletizing = EnumType.Palletizing;
    /** @deprecated */
    public static final EnumType Type_Perforating = EnumType.Perforating;
    /** @deprecated */
    public static final EnumType Type_PlasticCombBinding = EnumType.PlasticCombBinding;
    /** @deprecated */
    public static final EnumType Type_PrintRolling = EnumType.PrintRolling;
    /** @deprecated */
    public static final EnumType Type_RingBinding = EnumType.RingBinding;
    /** @deprecated */
    public static final EnumType Type_SaddleStitching = EnumType.SaddleStitching;
    /** @deprecated */
    public static final EnumType Type_ShapeCutting = EnumType.ShapeCutting;
    /** @deprecated */
    public static final EnumType Type_Shrinking = EnumType.Shrinking;
    /** @deprecated */
    public static final EnumType Type_SideSewing = EnumType.SideSewing;
    /** @deprecated */
    public static final EnumType Type_SpinePreparation = EnumType.SpinePreparation;
    /** @deprecated */
    public static final EnumType Type_SpineTaping = EnumType.SpineTaping;
    /** @deprecated */
    public static final EnumType Type_Stacking = EnumType.Stacking;
    /** @deprecated */
    public static final EnumType Type_Stitching = EnumType.Stitching;
    /** @deprecated */
    public static final EnumType Type_Strapping = EnumType.Strapping;
    /** @deprecated */
    public static final EnumType Type_StripBinding = EnumType.StripBinding;
    /** @deprecated */
    public static final EnumType Type_ThreadSealing = EnumType.ThreadSealing;
    /** @deprecated */
    public static final EnumType Type_ThreadSewing = EnumType.ThreadSewing;
    /** @deprecated */
    public static final EnumType Type_Trimming = EnumType.Trimming;
    /** @deprecated */
    public static final EnumType Type_WireCombBinding = EnumType.WireCombBinding;
    /** @deprecated */
    public static final EnumType Type_Wrapping = EnumType.Wrapping;




    private static void nameMapPut(String key, String addon, String[] mMaps, HashMap hm)
    {
        VString vs=StringUtil.tokenize(addon,JDFConstants.COMMA,false);
        String[] v=new String[mMaps.length+vs.size()];
        int i=0;
        for(;i<mMaps.length;i++)
        {
            v[i]=mMaps[i];
        }
        for(i=0;i<vs.size();i++)
        {
            v[i+mMaps.length]=vs.stringAt(i);
        }
        hm.put(key,v);
    }

    private static void linkNamemapPut(String key, String addon)
    {
        nameMapPut(key,addon,m_strGenericLinkNames, m_LinkNamesMap);
    }
    private static void infomapPut(String key, String addon)
    {
        nameMapPut(key,addon,m_GenericLinkInfo, m_LinkInfoMap);
    }

    private static void mapPut(String key, String nameAddon, String linkAddon)
    {
        nameMapPut(key,nameAddon,m_strGenericLinkNames, m_LinkNamesMap);
        nameMapPut(key,linkAddon,m_GenericLinkInfo, m_LinkInfoMap);
    }

    //Note: This MUST be behind the enum declaration because the enums are used 
    static 
    {
        mapPut(EnumType.Product.getName(),
                ",Component,ArtDeliveryIntent,BindingIntent,ColorIntent,DeliveryIntent,EmbossingIntent,FoldingIntent," + 
                "HoleMakingIntent,InsertingIntent,LaminatingIntent,LayoutIntent,MediaIntent,NumberingIntent," +
                "PackingIntent,ProductionIntent,ProofingIntent,ScreeningIntent,ShapeCuttingIntent,SizeIntent" 
                // links
                , ",o+ i* i*Cover i?Jacket i?Parent i*EndSheet,i?,i?,i?,i?,i?,i?,"+
                "i?,i?,i?,i?,i?,i?,"+
        "i?,i?,i?,i?,i?,i?");


        mapPut(EnumType.ProcessGroup.getName(),
                ",*",
                // links
        ",i* o*");

        mapPut(EnumType.Combined.getName(),
                ",",
                //links
        ",");

        linkNamemapPut(EnumType.Approval.getName(),
        ",*,ApprovalSuccess,ApprovalParams");
        linkNamemapPut(EnumType.Buffer.getName(),
        ",*,BufferParams");
        linkNamemapPut(EnumType.Combine.getName(),
        ",*");
        linkNamemapPut(EnumType.Delivery.getName(),
        ",*,DeliveryParams");
        linkNamemapPut(EnumType.ManualLabor.getName(),
        ",*,ManualLaborParams");
        linkNamemapPut(EnumType.Ordering.getName(),
        ",*,OrderingParams");
        linkNamemapPut(EnumType.Packing.getName(),
        ",*,PackingParams");
        linkNamemapPut(EnumType.QualityControl.getName(),
        ",*,QualityControlResult,QualityControlParams");
        linkNamemapPut(EnumType.ResourceDefinition.getName(),
        ",*,ResourceDefinitionParams");
        linkNamemapPut(EnumType.Split.getName(),
        ",*");
        linkNamemapPut(EnumType.Verification.getName(),
        ",*,DBSelection,ApprovalSuccess,VerificationParams,IdentificationField,DBSchema");

        // prepress
        linkNamemapPut(EnumType.AssetListCreation.getName(),
        ",AssetListCreationParams,RunList");
        linkNamemapPut(EnumType.Bending.getName(),
        ",BendingParams,ExposedMedia,Media");
        linkNamemapPut(EnumType.ColorCorrection.getName(),
        ",ColorantControl,ColorCorrectionParams,RunList");
        linkNamemapPut(EnumType.ColorSpaceConversion.getName(),
        ",ColorantControl,ColorSpaceConversionParams,RunList");
        linkNamemapPut(EnumType.ContactCopying.getName(),
        ",ContactCopyParams,DevelopingParams,ExposedMedia,Media,TransferCurvePool");
        linkNamemapPut(EnumType.ContoneCalibration.getName(),
        ",RunList,ScreeningParams,TransferFunctionControl");
        linkNamemapPut(EnumType.DBDocTemplateLayout.getName(),
        ",DBRules,DBSchema,LayoutElement");
        linkNamemapPut(EnumType.DBTemplateMerging.getName(),
        ",DBMergeParams,DBSelection,LayoutElement,RunList");
        linkNamemapPut(EnumType.DigitalDelivery.getName(),
        ",DigitalDeliveryParams,RunList");
        linkNamemapPut(EnumType.FilmToPlateCopying.getName(),
        ",DevelopingParams,ExposedMedia,Media,PlateCopyParams");
        linkNamemapPut(EnumType.FormatConversion.getName(),
        ",FormatConversionParams,RunList");
        linkNamemapPut(EnumType.ImageReplacement.getName(),
        ",ImageCompressionParams,ImageReplacementParams,RunList");
        linkNamemapPut(EnumType.ImageSetting.getName(),
                ",ColorantControl,DevelopingParams,ImageSetterParams,Media" +
        ",RunList,TransferCurvePool,ExposedMedia");
        linkNamemapPut(EnumType.Imposition.getName(),
        ",Layout,RunList");
        linkNamemapPut(EnumType.InkZoneCalculation.getName(),
                ",InkZoneCalculationParams,InkZoneProfile,Layout" +
        ",TransferCurvePool,Sheet,Preview");
        linkNamemapPut(EnumType.Interpreting.getName(),
                ",ColorantControl,FontPolicy,InterpretedPDLData" +
        ",InterpretingParams,PDLResourceAlias,RunList");
        linkNamemapPut(EnumType.LayoutElementProduction.getName(),
        ",LayoutElement,RunList,LayoutElementProductionParams");
        linkNamemapPut(EnumType.LayoutPreparation.getName(),
        ",LayoutPreparationParams,RunList,Layout,TransferCurvePool");
        linkNamemapPut(EnumType.PDFToPSConversion.getName(),
        ",PDFToPSConversionParams,RunList");
        linkNamemapPut(EnumType.Preflight.getName(),
                ",PreflightParams,PreflightReportRulePool,RunList" +
        ",PreflightReport");
        linkNamemapPut(EnumType.PreviewGeneration.getName(),
                ",ColorantControl,ExposedMedia,PreviewGenerationParams" +
        ",RunList,TransferCurvePool,Preview");
        linkNamemapPut(EnumType.Proofing.getName(),
                ",ColorantControl,ColorSpaceConversionParams" +
        ",ExposedMedia,Layout,Media,ProofingParams,RunList");
        linkNamemapPut(EnumType.PSToPDFConversion.getName(),
                ",FontParams,ImageCompressionParams" +
        ",PSToPDFConversionParams,RunList");
        linkNamemapPut(EnumType.Rendering.getName(),
        ",InterpretedPDLData,Media,RenderingParams,RunList");
        linkNamemapPut(EnumType.Scanning.getName(),
        ",ExposedMedia,ScanParams,RunList");
        linkNamemapPut(EnumType.Screening.getName(),
        ",RunList,ScreeningParams");
        linkNamemapPut(EnumType.Separation.getName(),
        ",ColorantControl,RunList,SeparationControlParams");
        linkNamemapPut(EnumType.SoftProofing.getName(),
                ",ColorantControl,ColorSpaceConversionParams,Layout" +
        ",ProofingParams,RunList");
        linkNamemapPut(EnumType.Stripping.getName(),
        ",RunList,Layout,Assembly,TransferCurvePool,StrippingParams");
        linkNamemapPut(EnumType.Tiling.getName(),
        ",RunList,Tile");
        linkNamemapPut(EnumType.Trapping.getName(),
        ",ColorantControl,RunList,TrappingDetails,FontPolicy");
        linkNamemapPut(EnumType.ConventionalPrinting.getName(),
                ",ColorantControl,Component,ConventionalPrintingParams" +
                ",ExposedMedia,Ink,InkZoneProfile,Layout,Media" +
        ",PrintCondition,Sheet,TransferCurvePool");

        mapPut(EnumType.DigitalPrinting.getName(),
                ",ColorantControl,Component,DigitalPrintingParams" +
                ",ExposedMedia,Ink,PrintCondition,Media,RunList" +
                ",Layout,Sheet,TransferCurvePool",
                //links
                ",i?,o?Waste o_ i?Proof i*Input,i_"+
                ",i?,i?,i?,i*,i_"+
        ",i?,i?,i?");

        linkNamemapPut(EnumType.IDPrinting.getName(),
                ",ColorantControl,Component,ExposedMedia,FontPolicy" +
                ",Ink,InterpretingParams,IDPrintingParams,Media" +
                ",RenderingParams,RunList,ScreeningParams" +
        ",TransferFunctionControl");
        linkNamemapPut(EnumType.AdhesiveBinding.getName(),
        ",AdhesiveBindingParams,Component");
        linkNamemapPut(EnumType.BlockPreparation.getName(),
        ",Component,BlockPreparationParams");
        linkNamemapPut(EnumType.BoxFolding.getName(),
        ",Component,BoxFoldingParams");
        linkNamemapPut(EnumType.BoxPacking.getName(),
        ",Component,BoxPackingParams");
        linkNamemapPut(EnumType.Bundling.getName(),
        ",Component,BundlingParams,Media");
        linkNamemapPut(EnumType.CaseMaking.getName(),
        ",Component,CaseMakingParams,Media");
        linkNamemapPut(EnumType.CasingIn.getName(),
        ",Component,CasingInParams");
        linkNamemapPut(EnumType.ChannelBinding.getName(),
        ",ChannelBindingParams,Component");
        linkNamemapPut(EnumType.CoilBinding.getName(),
        ",CoilBindingParams,Component");
        linkNamemapPut(EnumType.Collecting.getName(),
                ",CollectingParams,Component,DBRules,DBSelection" +
        ",IdentificationField");
        linkNamemapPut(EnumType.CoverApplication.getName(),
        ",Component,CoverApplicationParams");
        linkNamemapPut(EnumType.Creasing.getName(),
        ",CreasingParams,Component");
        linkNamemapPut(EnumType.Cutting.getName(),
        ",Component,CutBlock,CutMark,CuttingParams,Media");
        linkNamemapPut(EnumType.Dividing.getName(),
        ",Component,DividingParams");
        linkNamemapPut(EnumType.Embossing.getName(),
        ",Component,EmbossingParams,Media,Tool");
        linkNamemapPut(EnumType.EndSheetGluing.getName(),
        ",Component,EndSheetGluingParams");
        linkNamemapPut(EnumType.Feeding.getName(),
        ",Component,FeedingParams,Media");
        linkNamemapPut(EnumType.Folding.getName(),
        ",Component,FoldingParams");
        linkNamemapPut(EnumType.Gathering.getName(),
                ",Component,DBRules,DBSelection,GatheringParams" +
        ",IdentificationField");
        linkNamemapPut(EnumType.Gluing.getName(),
        ",Component,GluingParams");
        linkNamemapPut(EnumType.HeadBandApplication.getName(),
        ",Component,HeadBandApplicationParams");
        linkNamemapPut(EnumType.HoleMaking.getName(),
        ",Component,HoleMakingParams");
        linkNamemapPut(EnumType.Inserting.getName(),
                ",Component,DBRules,DBSelection,IdentificationField" +
        ",InsertingParams");
        linkNamemapPut(EnumType.Jacketing.getName(),
        ",Component,JacketingParams");
        linkNamemapPut(EnumType.Labeling.getName(),
        ",Component,LabelingParams");
        linkNamemapPut(EnumType.Laminating.getName(),
        ",Component,LaminatingParams,Media");
        linkNamemapPut(EnumType.LongitudinalRibbonOperations.getName(),
        ",Component,LongitudinalRibbonOperationParams");
        linkNamemapPut(EnumType.Numbering.getName(),
        ",Component,NumberingParams");
        linkNamemapPut(EnumType.Palletizing.getName(),
        ",Component,PalletizingParams,Pallet");
        linkNamemapPut(EnumType.Perforating.getName(),
        ",PerforatingParams,Component");
        linkNamemapPut(EnumType.PlasticCombBinding.getName(),
        ",Component,PlasticCombBindingParams");
        linkNamemapPut(EnumType.PrintRolling.getName(),
        ",Component,PrintRollingParams,RollStand");
        linkNamemapPut(EnumType.RingBinding.getName(),
        ",Component,RingBindingParams");
        linkNamemapPut(EnumType.SaddleStitching.getName(),
        ",Component,SaddleStitchingParams");
        linkNamemapPut(EnumType.ShapeCutting.getName(),
        ",Component,ShapeCuttingParams,Tool");
        linkNamemapPut(EnumType.Shrinking.getName(),
        ",Component,ShrinkingParams");
        linkNamemapPut(EnumType.SideSewing.getName(),
        ",Component,SideSewingParams");
        linkNamemapPut(EnumType.SpinePreparation.getName(),
        ",Component,SpinePreparationParams");
        linkNamemapPut(EnumType.SpineTaping.getName(),
        ",Component,SpineTapingParams");
        linkNamemapPut(EnumType.Stacking.getName(),
        ",Component,StackingParams");
        linkNamemapPut(EnumType.Stitching.getName(),
        ",Component,StitchingParams");
        linkNamemapPut(EnumType.Strapping.getName(),
        ",Component,StrappingParams,Strap");
        linkNamemapPut(EnumType.StripBinding.getName(),
        ",Component,StripBindingParams");
        linkNamemapPut(EnumType.ThreadSealing.getName(),
        ",Component,ThreadSealingParams");
        linkNamemapPut(EnumType.ThreadSewing.getName(),
        ",Component,ThreadSewingParams");
        linkNamemapPut(EnumType.Trimming.getName(),
        ",Component,TrimmingParams");
        linkNamemapPut(EnumType.WireCombBinding.getName(),
        ",Component,WireCombBindingParams");
        linkNamemapPut(EnumType.Wrapping.getName(),
        ",Component,WrappingParams,Media");
    }


    static 
    {
        infomapPut(EnumType.Approval.getName(),",o*Rejected o*Accepted i*,o_ i*,i_");
        infomapPut(EnumType.Buffer.getName(),",o_ i_,i_");
        infomapPut(EnumType.Combine.getName(),",o_ i+");
        infomapPut(EnumType.Delivery.getName(), ",o+ i?,i_");
        infomapPut(EnumType.ManualLabor.getName(), ",o_ i*,i_");
        infomapPut(EnumType.Ordering.getName(), ",o+,i_");
        infomapPut(EnumType.Packing.getName(),",o_ i_,i_");
        infomapPut(EnumType.QualityControl.getName(),",o_ i_,o_,i_");
        infomapPut(EnumType.ResourceDefinition.getName(), ",o+ i*,i?");
        infomapPut(EnumType.Split.getName(),",o+ i_");
        infomapPut(EnumType.Verification.getName(),",o? i?,o? i*,i_,i*,i?");
        infomapPut(EnumType.AssetListCreation.getName(), ",i_,i_ o_");
        infomapPut(EnumType.ColorCorrection.getName(),",i?,i_,o_ i_");
        infomapPut(EnumType.Bending.getName(),
        ",i_,i? o,i?");
        infomapPut(EnumType.ColorSpaceConversion.getName(),",o? i?,i_,o_ i_");
        infomapPut(EnumType.ContactCopying.getName(),",i_,i?,o_ i+,i_,i?");
        infomapPut(EnumType.ContoneCalibration.getName(),
        ",o_ i_,i?,i?");
        infomapPut(EnumType.DBDocTemplateLayout.getName(),
        ",i_,i_,o* i*");
        infomapPut(EnumType.DBTemplateMerging.getName(),
        ",i_,i_,i*,o_");
        infomapPut(EnumType.DigitalDelivery.getName(),
        ",i_,o_ i_");
        infomapPut(EnumType.FilmToPlateCopying.getName(),
        ",i?,o_ i_,i_,i_");
        infomapPut(EnumType.FormatConversion.getName(),
        ",i_,o_ i_");
        infomapPut(EnumType.ImageReplacement.getName(),
        ",i?,i_,o_ i_");
        infomapPut(EnumType.ImageSetting.getName(),
        ",i?,i?,i?,i_,i_,i?,o_");
        infomapPut(EnumType.Imposition.getName(),
        ",i_,o_ i?Marks i_Document");
        infomapPut(EnumType.InkZoneCalculation.getName(),
        ",i_,o_,i?,i?,i?,i_");
        infomapPut(EnumType.Interpreting.getName(),
        ",i?,i?,o?,i_,i*,o? i_");
        infomapPut(EnumType.LayoutElementProduction.getName(),
        ",o? i*,o?,i?");  
        infomapPut(EnumType.LayoutPreparation.getName(),
        ",i_,o?Marks i?Marks i?Document,o_,o?");
        infomapPut(EnumType.PDFToPSConversion.getName(),
        ",i_,o_ i_");
        infomapPut(EnumType.Preflight.getName(),
        ",i_,i_,i_,o_");
        infomapPut(EnumType.PreviewGeneration.getName(),
        ",i?,i?,i_,i?,i?,i? o_");
        infomapPut(EnumType.Proofing.getName(),
        ",i?,i?,o_,i?,i_,i_,i?Marks i_Document");
        infomapPut(EnumType.PSToPDFConversion.getName(),
        ",i?,i?,i?,o_ i_");
        infomapPut(EnumType.Rendering.getName(),
        ",i?,i?,i?,o_ i?");
        infomapPut(EnumType.Scanning.getName(),
        ",i_,i_,o_");
        infomapPut(EnumType.Screening.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.Separation.getName(),
        ",i?,o_ i_,i_");
        infomapPut(EnumType.SoftProofing.getName(),
        ",i?,i?,i?,i_,i?Marks i_Document");
        infomapPut(EnumType.Stripping.getName(),
        ",o?Marks o?Document i?Document,o_,i+,i?,i_");
        infomapPut(EnumType.Tiling.getName(),
        ",o_ i?Marks i_Surface,i_");
        infomapPut(EnumType.Trapping.getName(),
        ",i?,o_ i_,i_,i?");
        infomapPut(EnumType.ConventionalPrinting.getName(),
        ",i?,o?Waste o?Good o_ i?Proof i?Input,i_,i_Plate i?Proof,i?,i?,i?,i?,i?,i?,i?");
        infomapPut(EnumType.IDPrinting.getName(),
        ",i?,o?Waste o_Good i?Proof i?Input i?Cover,i?,i?,i?,i*,i?,i?,i?,i_,i?,i?");
        infomapPut(EnumType.AdhesiveBinding.getName(),
        ",i_,o_ i?Cover i_BookBlock");
        infomapPut(EnumType.BlockPreparation.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.BoxPacking.getName(),
        ",o_ i?Box i_,i_");
        infomapPut(EnumType.BoxFolding.getName(),
        ",o_ i*Application i_,i_");
        infomapPut(EnumType.Bundling.getName(),
        ",o_ i_,i_,i?");
        infomapPut(EnumType.CaseMaking.getName(),
        ",o_ i?CoverMaterial,i_,i?SpineBoard i_CoverBoard i?CoverMaterial");
        infomapPut(EnumType.CasingIn.getName(),
        ",o_ i_infomapPut(i_,i_");
        infomapPut(EnumType.ChannelBinding.getName(),
        ",i_,o_ i?Cover i_BookBlock");
        infomapPut(EnumType.CoilBinding.getName(),
        ",i_,o_ i_");
        infomapPut(EnumType.Collecting.getName(),
        ",i?,o_ i+,i*,i?,i?");
        infomapPut(EnumType.CoverApplication.getName(),
        ",o_ i_Cover i_,i_");
        infomapPut(EnumType.Creasing.getName(),
        ",i_,o_ i_");
        infomapPut(EnumType.Cutting.getName(),
        ",o* i?,i*,i*,i_,o* i?");
        infomapPut(EnumType.Dividing.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.Embossing.getName(),
        ",o_ i_,i_,i?,i?");
        infomapPut(EnumType.EndSheetGluing.getName(),
        ",o_ i_FrontEndSheet i_BookBlock i_BackEndSheet,i_");
        infomapPut(EnumType.Feeding.getName(),
        ",o* i*,i_,o* i*");
        infomapPut(EnumType.Folding.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.Gathering.getName(),
        ",o_ i+,i*,i?,i_,i?");
        infomapPut(EnumType.Gluing.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.HeadBandApplication.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.HoleMaking.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.Inserting.getName(),
        ",o_ i_Child i_Mother,i?,i?,i?,i_");
        infomapPut(EnumType.Jacketing.getName(),
        ",o_ i_Jacket i_Book,i_");
        infomapPut(EnumType.Labeling.getName(),
        ",o_ i?Label i_,i_");
        infomapPut(EnumType.Laminating.getName(),
        ",o_ i_,i_,i?");
        infomapPut(EnumType.LongitudinalRibbonOperations.getName(),
        ",o+ i_,i_");
        infomapPut(EnumType.Numbering.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.Palletizing.getName(),
        ",o_ i_,i_,i_");
        infomapPut(EnumType.Perforating.getName(),
        ",i_,o_ i_");
        infomapPut(EnumType.PlasticCombBinding.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.PrintRolling.getName(),
        ",o_ i_,i?,i?");
        infomapPut(EnumType.RingBinding.getName(),
        ",o_ i?RingBinder i_BookBlock,i_");
        infomapPut(EnumType.SaddleStitching.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.ShapeCutting.getName(),
        ",o_ i_,i?,i*");
        infomapPut(EnumType.Shrinking.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.SideSewing.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.SpinePreparation.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.SpineTaping.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.Stacking.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.Stitching.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.Strapping.getName(),
        ",o_ i_,i_,i?");
        infomapPut(EnumType.StripBinding.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.ThreadSealing.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.ThreadSewing.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.Trimming.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.WireCombBinding.getName(),
        ",o_ i_,i_");
        infomapPut(EnumType.Wrapping.getName(),
        ",o_ i_,i_,i?");

    }
    //**************************************** Constructors ****************************************
    //NEWWWW

    public static final class EnumProcessUsage extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;

        private static int m_startValue = 0;

        protected EnumProcessUsage(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumProcessUsage getEnum(String enumName)
        {
            return (EnumProcessUsage)getEnum(EnumProcessUsage.class, enumName);
        }

        public static EnumProcessUsage getEnum(int enumValue)
        {
            return (EnumProcessUsage) getEnum(EnumProcessUsage.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumProcessUsage.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumProcessUsage.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumProcessUsage.class);
        }

        /**
         * @deprecated
         * @return
         */
        public static Vector getNamesVector()
        {
            final Vector namesVector = new Vector();
            final Iterator it = iterator(EnumProcessUsage.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }

            return namesVector;
        }


        public static final EnumProcessUsage AnyInput = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_ANYINPUT);
        public static final EnumProcessUsage AnyOutput = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_ANYOUTPUT);
        public static final EnumProcessUsage Any = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_ANY);
        public static final EnumProcessUsage Rejected = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_REJECTED);
        public static final EnumProcessUsage Accepted = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_ACCEPTED);
        public static final EnumProcessUsage Application = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_APPLICATION);
        public static final EnumProcessUsage Marks = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_MARKS);
        public static final EnumProcessUsage Document = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_DOCUMENT);
        public static final EnumProcessUsage Surface = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_SURFACE);
        public static final EnumProcessUsage Waste = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_WASTE);
        public static final EnumProcessUsage Proof = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_PROOF);
        public static final EnumProcessUsage Input = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_INPUT);
        public static final EnumProcessUsage Plate = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_PLATE);
        public static final EnumProcessUsage Good = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_GOOD);
        public static final EnumProcessUsage Cover = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_COVER);
        public static final EnumProcessUsage BookBlock = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_BOOKBLOCK);
        public static final EnumProcessUsage Box = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_BOX);
        public static final EnumProcessUsage CoverMaterial = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_COVERMATERIAL);
        public static final EnumProcessUsage SpineBoard = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_SPINEBOARD);
        public static final EnumProcessUsage CoverBoard = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_COVERBOARD);
        public static final EnumProcessUsage Case = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_CASE);
        public static final EnumProcessUsage FrontEndSheet = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_FRONTENDSHEET);
        public static final EnumProcessUsage BackEndSheet = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_BACKENDSHEET);
        public static final EnumProcessUsage Child = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_CHILD);
        public static final EnumProcessUsage Mother = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_MOTHER);
        public static final EnumProcessUsage Jacket = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_JACKET);
        public static final EnumProcessUsage Book = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_BOOK);
        public static final EnumProcessUsage Label = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_LABEL);
        public static final EnumProcessUsage RingBinder = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_RINGBINDER);
        public static final EnumProcessUsage Ancestor = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_ANCESTOR);
    }

    /**
     * constants EnumProcessUsage
     * use EnumProcessUsage.xxx instead of the deprecated constants EnumProcessUsage.ProcessUsage_xxx
     */
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_AnyInput= EnumProcessUsage.AnyInput;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_AnyOutput = EnumProcessUsage.AnyOutput;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Any = EnumProcessUsage.Any;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Rejected = EnumProcessUsage.Rejected;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Accepted = EnumProcessUsage.Accepted;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Marks = EnumProcessUsage.Marks;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Document = EnumProcessUsage.Document;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Surface = EnumProcessUsage.Surface;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Waste = EnumProcessUsage.Waste;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Proof = EnumProcessUsage.Proof;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Input = EnumProcessUsage.Input;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Plate = EnumProcessUsage.Plate;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Good = EnumProcessUsage.Good;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Cover = EnumProcessUsage.Cover;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_BookBlock = EnumProcessUsage.BookBlock;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Box = EnumProcessUsage.Box;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_CoverMaterial = EnumProcessUsage.CoverMaterial;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_SpineBoard = EnumProcessUsage.SpineBoard;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_CoverBoard = EnumProcessUsage.CoverBoard;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Case = EnumProcessUsage.Case;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_FrontEndSheet = EnumProcessUsage.FrontEndSheet;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_BackEndSheet = EnumProcessUsage.BackEndSheet;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Child = EnumProcessUsage.Child;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Mother = EnumProcessUsage.Mother;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Jacket = EnumProcessUsage.Jacket;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Book = EnumProcessUsage.Book;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Label = EnumProcessUsage.Label;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_RingBinder = EnumProcessUsage.RingBinder;
    /** @deprecated */
    public static final EnumProcessUsage ProcessUsage_Ancestor = EnumProcessUsage.Ancestor;


    //**************************************** Methods *********************************************
    /**
     * toString - StringRepresentation of JDFNode
     *
     * @return String
     */
    public String toString()
    {
        return "JDFNode[ --> " + super.toString() + " ]";
    }


    /**
     * init - init the node
     *
     * @return boolean: true always
     */
    public boolean init()
    {

        String id=appendAnchor(null);
        if (isJDFRoot())
        {
            // create a standard JDFRoot with namespace, version, comment and audit pool
            addNameSpace(JDFConstants.EMPTYSTRING, getSchemaURL());
            setVersion(getDefaultJDFVersion());

            String comment = "Generated by the CIP4 Java open source JDF Library version : ";
            comment += JDFAudit.software();
            appendXMLComment(comment);

            final JDFAuditPool h = getCreateAuditPool();
            h.addCreated(JDFAudit.software(), null);

            // set an initial jobpartid
            if(!hasAttribute(AttributeName.JOBPARTID))
                setJobPartID(id);
        }
        else
        {            
            // set an initial jobpartid
            if(!hasAttribute(AttributeName.JOBPARTID))
            {
                setJobPartID(generateDotID(AttributeName.JOBPARTID,null));
            }
        }

        setStatus(JDFElement.EnumNodeStatus.Waiting);       
        return true;
    }


    //////////////////////////////////////////////////////////////////////

    public boolean isJDFRoot()
    {
        final KElement e = getParentNode_KElement();
        if (e == null)
        {
            return true;
        }
        return !(e instanceof JDFNode);
    }

    /////////////////////////////////////////////////////////////////////////
    //TODO check if we really dont need this method. a processUsage.toString 
    //should have the same effekt.
    //    public String ProcessUsageString(EnumProcessUsage processUsage)
    //    {
    //        return ProcessUsageString().Token(processUsage,String.comma);
    //    }
    //  
    /////////////////////////////////////////////////////////////////////////


    /**
     * definition of resource link names in the JDF namespace
     * @return String list of resource names that may be linked
     */
    public VString linkNames()
    {
        final EnumType typ = EnumType.getEnum(getType());
        if(typ==null)
            return null;

        if (typ.equals(EnumType.Combined) || (typ==EnumType.ProcessGroup && hasAttribute(AttributeName.TYPES)))
        {
            final VString v = new VString(m_strGenericLinkNames);
            final VString vTypes = getTypes();
            for (int i = 0; i < vTypes.size(); i++)
            {
                final EnumType t = EnumType.getEnum(vTypes.stringAt(i));
                final String[] typeLinkNames = typeLinkNames(t);
                if(typeLinkNames==null)
                    return null; // bail out - it's open anyhow
                for (int j = m_strGenericLinkNames.length; j < typeLinkNames.length; j++)
                {
                    v.add(typeLinkNames[j]);
                }
            }
            return v;
        }

        // sinmple single type
        final String[] typeLinkNames = typeLinkNames(typ);
        if(typeLinkNames==null)
            return null; // bail out - it's open anyhow
        return new VString(typeLinkNames);
    }

    public VString linkInfo()
    {
        final EnumType typ = EnumType.getEnum(getType());
        if(typ==null)
            return null;
        if (typ.equals(EnumType.Combined)|| (typ==EnumType.ProcessGroup && hasAttribute(AttributeName.TYPES)))
        {

            final VString vLinkInfo = new VString(m_GenericLinkInfo);
            final VString vNames = new VString(m_strGenericLinkNames);

            final VString vTypes = getTypes();
            int i = 0;
            for (i = 0; i < vTypes.size(); i++)
            {
                final EnumType t = EnumType.getEnum((String) vTypes.elementAt(i));
                if(t==null)
                    return null;

                final String[] typeLinkInfo = typeLinkInfo(t);
                final String[] typeLinkNames = typeLinkNames(t);
                if(typeLinkInfo==null || typeLinkNames==null)
                    return null;

                for(int j = m_GenericLinkInfo.length; j < typeLinkInfo.length; j++)
                {
                    vLinkInfo.add(typeLinkInfo[j]);
                    vNames.add(typeLinkNames[j]);
                }
            }

            // make the intermediate links optional
            final int s = vLinkInfo.size();
            // loop over all links
            for (i = 0; i < s; i++)
            {
                final VString typeList = StringUtil.tokenize((String) vLinkInfo.elementAt(i), JDFConstants.BLANK, false);                
                for (int iTyp = 0; iTyp < typeList.size(); iTyp++)
                {
                    String strTyp = (String) typeList.elementAt(iTyp);
                    if (strTyp.charAt(0) =='o')
                    {
                        final String linkName = (String) vNames.elementAt(i);
                        // loop over all links behind this one in types
                        for (int j = i + 1; j < s; j++)
                        {
                            if (vNames.elementAt(j).equals(linkName))
                            { // if the names match, they should fit 
                                boolean bGotOne = false;

                                final VString typeList2 = StringUtil.tokenize((String) vLinkInfo.elementAt(j), JDFConstants.BLANK, false);

                                for (int iTyp2 = 0; iTyp2 < typeList2.size(); iTyp2++)
                                {
                                    String typ2 = (String) typeList2.elementAt(iTyp2);
                                    if (typ2.charAt(0) == 'i')
                                    {
                                        bGotOne = true;
                                        // make them optional
                                        if (typ2.charAt(1) == '_')
                                        {
                                            final char[] c_typ2 = typ2.toCharArray();
                                            c_typ2[1] = '?';
                                            typ2 = new String(c_typ2);
                                        }
                                        else if (typ2.charAt(1) == '+')
                                        {
                                            final char[] c_typ2 = typ2.toCharArray();
                                            c_typ2[1] = '*';
                                            typ2 = new String(c_typ2);
                                        }
                                        typeList2.set(iTyp2, typ2);
                                    }
                                }
                                if (bGotOne)
                                {
                                    // replace input link entry
                                    vLinkInfo.set(j, typeList2.getString(JDFConstants.BLANK, 
                                            null, null));
                                    if (strTyp.charAt(1) == '_')
                                    {
                                        final char[] c_strTyp = strTyp.toCharArray();
                                        c_strTyp[1] = '?';
                                        strTyp = new String(c_strTyp);
                                    }
                                    else if (strTyp.charAt(1) == '+')
                                    {
                                        final char[] c_strTyp = strTyp.toCharArray();
                                        c_strTyp[1] = '*';
                                        strTyp = new String(c_strTyp);
                                    }
                                    typeList.set(iTyp, strTyp);
                                }
                            }
                        }
                    }
                }
                vLinkInfo.set(i, typeList.getString(JDFConstants.BLANK, null, null));
            }
            return vLinkInfo;
        }
        return new VString(typeLinkInfo(typ));
    }
    //////////////////////////////////////////////////////////////////////
    /**
     * definition of resource link names in the JDF namespace
     * @return String list of resource names that may be linked
     */
    private static String[] typeLinkNames(EnumType typeNum)
    {
        if(typeNum==null)
            return null;
        return (String[])  m_LinkNamesMap.get(typeNum.getName());
    }

    //////////////////////////////////////////////////////////////////////

    private String[] typeLinkInfo(EnumType typeNum)
    {
        final String[] strValueOfEnum = (String[]) m_LinkInfoMap.get(typeNum.getName());
        return (strValueOfEnum==null) ? m_GenericLinkInfo : strValueOfEnum;
    }

    //--------------------------------------------------------------------------------


    /**
     * sets node's partition status
     */
    public boolean setPartStatus (VJDFAttributeMap vmattr, EnumNodeStatus status)
    {
        boolean bRet = true;

        int siz = vmattr==null ? 0 : vmattr.size();
        if (siz > 0)
        {
            for (int i = 0; i < siz; i++)
            {
                bRet = bRet && setPartStatus(vmattr.elementAt(i), status);
            }
        }
        else
        {
            bRet = setPartStatus((JDFAttributeMap) null, status);
        }
        return bRet;
    }

    /**
     * set the node's partition status
     * if nodeinfo is partitioned, all leaves NodeStati below part are removed
     * @param mAttribute mattr: Attribute map of partition
     * @param EnumNodeStatus status: Status to set
     * @return boolean: success or not
     */
    public boolean setPartStatus(JDFAttributeMap mattr, JDFElement.EnumNodeStatus status)
    {
        final EnumNodeStatus stat = getStatus();

        // 100602 handle nasty combination
        if (mattr!=null && (!mattr.isEmpty()
                && (status.equals(JDFElement.EnumNodeStatus.Pool) || status.equals(JDFElement.EnumNodeStatus.Part))))
        {
            // throw an exception??? this is a snafu to set an individual part status to pool
            return false;
        }

        if (mattr==null || mattr.isEmpty())
        {
            setStatus(status);
            removeChild(ElementName.STATUSPOOL, null, 0);
            //TODO          tbd kai, also clean up nodeinfo status leaves
            return true;
        }

        if (getVersion(true).getValue() < JDFElement.EnumVersion.Version_1_3.getValue())
        {
            // we are setting an individual attribute
            final JDFStatusPool statusPool = getCreateStatusPool();

            if (!stat.equals(JDFElement.EnumNodeStatus.Pool))
            {
                statusPool.setStatus(stat);
                setStatus(JDFElement.EnumNodeStatus.Pool);
            }

            statusPool.setStatus(mattr, status, null);

            // this can happen if status = the previous status
            // just remove the pool and reset the status to the original status

            if (statusPool.numChildElements(ElementName.PARTSTATUS, null) == 0)
            {
                setStatus(status);
                statusPool.deleteNode();
            }
        }
        else // version >=1.3
        {
            JDFNodeInfo ni=getCreateNodeInfo();
            if(getStatus() != JDFElement.EnumNodeStatus.Part) 
            { //  set a decent default status for implicit
                ni.setNodeStatus(getStatus());
            }

            ni.setPartUsage(JDFResource.EnumPartUsage.Implicit);
            ni=(JDFNodeInfo) ni.getCreatePartition(mattr, null);
            ni.removeAttributeFromLeaves(AttributeName.NODESTATUS,null);
            ni.setNodeStatus(status);
            setStatus(JDFElement.EnumNodeStatus.Part);
        }

        return true;
    }

    /**
     * get the node's partition status
     * 
     * @param JDFAttributeMap mattr: Attribute map of partition
     * 
     * @return JDFElement.EnumNodeStatus: Status of the partition, null if no Status exists
     */
    public JDFElement.EnumNodeStatus getPartStatus (JDFAttributeMap mattr)
    {
        EnumNodeStatus stat = getStatus();
        if ((stat != EnumNodeStatus.Pool)&&(stat != EnumNodeStatus.Part)){
            return stat;
        }
        else if (stat==EnumNodeStatus.Part)
        {
            JDFNodeInfo ni=getNodeInfo();
            if(ni==null)
                return null;
            ni=(JDFNodeInfo)ni.getPartition(mattr, null);
            if(ni==null)
                return null;
            stat = ni.getNodeStatus();

            final VElement vLeaves=ni.getLeaves(true);
            final int size = vLeaves.size();
            for(int i=0;i<size;i++){
                final JDFNodeInfo niCmp=(JDFNodeInfo) vLeaves.elementAt(i);
                if(niCmp.getNodeStatus()!=stat)
                {
                    return null; //inconsistent
                }
            }
        }
        else if (stat==EnumNodeStatus.Pool)
        {
            final JDFStatusPool statusPool = getStatusPool();
            if(statusPool==null)
                return null;
            stat = statusPool.getStatus(mattr);
        }
        return stat;
    }

    /**
     * Set the Status and StatusDetails of this node
     * update the PhaseTime audit or append a new phasetime as appropriate  
     * also generate a status JMF
     * 
     * @param nodeStatus the new status of the node
     * @param nodeStatusDetails the new statusDetails of the node
     * @param deviceStatus the new status of the device
     * @param deviceStatusDetails the new statusDetails of the device
     * @param vPartMap the vector of parts to that should be set
     * 
     * @return The root element representing the PhaseTime JMF
     * @deprecated use the version with deviceID
     */
    public JDFDoc setPhase(EnumNodeStatus nodeStatus, String nodeStatusDetails, EnumDeviceStatus deviceStatus, String deviceStatusDetails, VJDFAttributeMap vPartMap)
    {
        return setPhase(nodeStatus, nodeStatusDetails, null, deviceStatus, deviceStatusDetails, vPartMap);
    }
    /**
     * Set the Status and StatusDetails of this node
     * update the PhaseTime audit or append a new phasetime as appropriate  
     * also generate a status JMF
     * 
     * @param nodeStatus the new status of the node
     * @param nodeStatusDetails the new statusDetails of the node
     * @param deviceStatus the new status of the device
     * @param deviceStatusDetails the new statusDetails of the device
     * @param vPartMap the vector of parts to that should be set
     * 
     * @return The root element representing the PhaseTime JMF
     */
    public JDFDoc setPhase(EnumNodeStatus nodeStatus, String nodeStatusDetails, String deviceID, EnumDeviceStatus deviceStatus, String deviceStatusDetails, VJDFAttributeMap vPartMap)
    {
        JDFDoc jmfDoc=new JDFDoc(ElementName.JMF);
        JDFJMF jmf=jmfDoc.getJMFRoot();
        if(vPartMap==null)
        {
            vPartMap = getPartMapVector();
        }

        JDFAuditPool ap=getCreateAuditPool();
        JDFPhaseTime pt1=(JDFPhaseTime) ap.getAudit(-1,EnumAuditType.PhaseTime,null);
        JDFPhaseTime pt2=ap.setPhase(nodeStatus,nodeStatusDetails,vPartMap);
        if(pt1!=null && pt2!=pt1) // we explicitly added a new phasetime audit, thus we need to add a closing JMF for the original jobPhase
        {
            JDFSignal s=(JDFSignal)jmf.appendMessageElement(JDFMessage.EnumFamily.Signal,JDFMessage.EnumType.Status);
            JDFDeviceInfo deviceInfo = s.appendDeviceInfo();
            deviceInfo.createJobPhaseFromPhaseTime(pt1);           
            if(deviceID!=null)
                pt2.appendDevice().setDeviceID(deviceID);
        }
        JDFSignal s=(JDFSignal)jmf.appendMessageElement(JDFMessage.EnumFamily.Signal,JDFMessage.EnumType.Status);
        JDFDeviceInfo deviceInfo = s.appendDeviceInfo();
        deviceInfo.createJobPhaseFromPhaseTime(pt2);           
        deviceInfo.setDeviceStatus(deviceStatus);
        deviceInfo.setStatusDetails(deviceStatusDetails);
        deviceInfo.setDeviceID(deviceID);
        setPartStatus(vPartMap,nodeStatus);
        // cleanup!
        jmf.eraseEmptyAttributes(true);
        pt2.eraseEmptyAttributes(true);
        return jmfDoc;
    }

    /**
     * return the partMapVector defined in AncestorPool, 
     * null if no AncestorPool exists, or AncestorPool has no Part elements
     * @return the vector of PartMaps
     */
    public VJDFAttributeMap getPartMapVector()
    {
        JDFAncestorPool ancPool=getAncestorPool();
        if(ancPool!=null)
            return ancPool.getPartMapVector();
        return null;
    }
    /**
     * getActivation
     *
     * @param EnumActivation bActive
     * @deprecated 060406 use getActivation(false)
     * @return EnumActivation
     */
    public EnumActivation getActivation()
    {
        return getActivation(false);
    }

    /**
     * getActivation
     *
     * @param EnumActivation bActive
     *
     * @return EnumActivation
     */
    public JDFNode.EnumActivation getActivation(boolean bWalkThroughAnchestors)
    {
        EnumActivation res=null;
        if (bWalkThroughAnchestors)
        {
            res = EnumActivation.Active;
            JDFNode p = this;
            while (p != null)
            {
                // walk through through all anchestors, to parent to grandparent to grandgrandparent 
                // and so on until root and compare the Activation state
                EnumActivation a = EnumActivation.getEnum(p.getAttribute(AttributeName.ACTIVATION, null, null));

                if ((a.getValue() <= EnumActivation.TestRun.getValue()) || (res.getValue() < EnumActivation.Active.getValue()))
                {
                    res = (a.getValue() < res.getValue()) ? a : res; // smaller enums are inherited to all descendants
                }
                else
                { // special case for non-linear test run / test run and go
                    if (res.equals(EnumActivation.TestRunAndGo))
                    {
                        res = a; // either TRG or TR
                    }
                    else
                    {
                        // nop it remains TestRun
                    }
                }

                p = (JDFNode) p.getParentNode_KElement();
            }
        }
        else
        {
            res = EnumActivation.getEnum(getAttribute(AttributeName.ACTIVATION, null, null));
        }
        return res;
    }


    /**
     * setActivation
     *
     * @param EnumActivation bActive
     */
    public void setActivation(EnumActivation bActive)
    {
        setAttribute(AttributeName.ACTIVATION, bActive.getName(), JDFConstants.EMPTYSTRING);
    }

    /**
     * addModified
     *
     * @param String by
     *
     * @return JDFAudit
     */
    public JDFAudit addModified(String by)
    {
        return getCreateAuditPool().addModified(by, null);
    }

    /**
     * addResource - add a resource to resroot and link it to this process
     *
     * @param name      the localname of the resource
     * @param resClass  the JFD/@Class of the resource; if null, find from factory
     * @param bInput    if true, the resource is linked as input, else output
     * @param resRoot   the node where to add the Resource, if null defaults to this. Note that the link is always in this
     * @param bLink     if true, creat a ResourceLink to the newly created resource
     * @param nameSpaceURI the nsURI of the resource, if null take the default ns
     *
     * @return JDFResource
     * @deprecated use addResource(String strName,  JDFResource.EnumResourceClass resClass, EnumUsage usage, EnumProcessUsage processUsage, JDFNode resRoot, String nameSpaceURI)    
     * default: addResource(name, null, bInput, null, true, null)
     */
    public JDFResource addResource(
            String strName,
            JDFResource.EnumResourceClass resClass,
            boolean bInput,
            JDFNode resRoot,
            boolean bLink,
            String nameSpaceURI)
    {
        EnumUsage usage=null;
        if(bLink)
            usage=bInput ? EnumUsage.Input : EnumUsage.Output; 
        return addResource(strName,resClass,usage,null,resRoot,nameSpaceURI,null);
    }
    /**
     * addResource - add a resource to resroot and link it to this process
     *
     * @param name      the localname of the resource
     * @param resClass  the JFD/@Class of the resource; if null, find the resource class from factory
     * @param usage    the Usage attrinute of the ResourceLink. if null, ther resource is not linked
     * @param processUsage     The processUsage attribute of the link to the resource
     * @param resRoot   the node where to add the Resource, if null defaults to this. Note that the link is always in this
     * @param nameSpaceURI the nsURI of the resource, if null take the default ns
     * @param toReplace the resource to replace by this - also add a resource audit
     * @return JDFResource
     * 
     * default: addResource(name, null, usage, null, null, null,null)
     */
    public JDFResource addResource(
            String strName,
            JDFResource.EnumResourceClass resClass,
            EnumUsage usage,
            EnumProcessUsage processUsage,
            JDFNode resRoot,
            String nameSpaceURI,
            JDFResource toReplace)    
    {
        if (resRoot == null)
        {
            resRoot = this;
        }

        final JDFResourcePool p = resRoot.getCreateResourcePool();
        final JDFResource r = p.appendResource(strName, null, nameSpaceURI);

        if (usage!=null)
        {
            linkResource(r, usage, processUsage);
        }

        // if the factory already did a type safe class creation, use it instead 
        final EnumResourceClass resClass2 = r.getResourceClass();
        if(resClass2!=null)
        {
            resClass=resClass2;
        }
        else
        {
            resClass=null;
        }
        if(resClass!=null)
            r.setResourceClass(resClass);


        // parameters and consumables are assumed to be available by default
        if (EnumUsage.Input.equals(usage) && resClass!=null
                && ((resClass.equals(JDFResource.EnumResourceClass.Parameter))
                        || (resClass.equals(JDFResource.EnumResourceClass.Consumable))))
        {
            r.setResStatus(EnumResStatus.Available,false);
        }
        else
        {
            r.setResStatus(JDFResource.EnumResStatus.Unavailable,false);
        }
        if(toReplace!=null){
            JDFAuditPool auditPool=getCreateAuditPool();
            JDFResourceAudit resourceAudit=auditPool.addResourceAudit(null);
            resourceAudit.addNewOldLink(true, r, usage);
            resourceAudit.addNewOldLink(false,toReplace,usage);
            VElement vRL=getResourceLinkPool().getInOutLinks(usage,true,null,null);
            for(int i=0;i<vRL.size();i++){
                JDFResourceLink l=(JDFResourceLink) vRL.elementAt(i);
                if(l.getTarget()==toReplace){
                    l.deleteNode();
                }
            }           
        }    
        return r;
    }

    /**
     * LinkResource: create a resourceLink in the resourceLinkPool that refers to 
     * the resource jdfResource 
     * also sets the appropriate combined process index
     * 
     *
     * @param jdfResource the resource or partition to link to
     * @param input it true, link as input, else link as output
     * @param bForce if true, create a new link, even if an existing link already exists
     *
     * @return JDFResourceLink the new link
     * @deprecated use linkResource(enum)
     * default: LinkResource(r, true, false)
     */
    public JDFResourceLink linkResource(JDFResource jdfResource, boolean input,boolean bForce)
    {
        if(bForce)
            bForce=true;
        return linkResource(jdfResource,input ? EnumUsage.Input : EnumUsage.Output,null);
    }
    /**
     * LinkResource: create a resourceLink in the resourceLinkPool that refers to 
     * the resource jdfResource 
     * also sets the appropriate combined process index
     * 
     *
     * @param jdfResource the resource or partition to link to
     * @param usage Usage of the resource
     * @param processUsage processUsage of the resource
     *
     * @return JDFResourceLink the new link
     * 
     * default: LinkResource(r, usage, null)
     */
    public JDFResourceLink linkResource(JDFResource jdfResource, EnumUsage usage, EnumProcessUsage processUsage)
    {
        if (jdfResource == null)
            return null;

        final JDFResourceLinkPool resourceLinkPool = getCreateResourceLinkPool();
        JDFResourceLink resourceLink = resourceLinkPool.linkResource(jdfResource, usage, processUsage);
        final VString types = getTypes();
        // generate 
        if(types!=null) 
        {
            JDFIntegerList cpi=new JDFIntegerList();
            final String resName=jdfResource.getLocalName();
            final int typSize = types.size();
            int lastGot=-2; // not -1!!!
            String typeLinkNamesLast[]=null;
            for(int i=0;i<typSize;i++)
            {
                boolean bAddCPI=true;
                final EnumType t = EnumType.getEnum(types.stringAt(i));
                final String[] typeLinkNames = typeLinkNames(t);
                if(typeLinkNames!=null && ArrayUtils.contains(typeLinkNames,resName))
                {
                    // if we already added a cpi, but this is an exchange resource, only set cpi for the last one
                    if(lastGot==i-1)
                    {
                        int iPos = ArrayUtils.indexOf(typeLinkNames,resName);
                        int iPosLast = ArrayUtils.indexOf(typeLinkNamesLast,resName);
                        // the i* i?pu ... list of this
                        final VString typeInfo = StringUtil.tokenize(typeLinkInfo(t)[iPos]," ",false);
                        // the o* i?pu ... list of the previous type
                        final VString typeInfoLast = StringUtil.tokenize(typeLinkInfo(EnumType.getEnum(types.stringAt(lastGot)))[iPosLast]," ",false);
                        boolean bOut=false;

                        for(int ii=0;ii<typeInfoLast.size();ii++)
                        {
                            if(typeInfoLast.stringAt(ii).startsWith("o"))
                            {
                                bOut=true; // we found a matching output
                                break;
                            }
                        }
                        if(bOut)
                        {
                            boolean bIn=false;
                            bOut=false;
                            for(int ii=0;ii<typeInfo.size();ii++)
                            {
                                if(!bIn && typeInfo.stringAt(ii).startsWith("i"))
                                {
                                    bIn=true; // after finding a matching output in last, we find an input here
                                }
                                if(!bOut && typeInfo.stringAt(ii).startsWith("o"))
                                {
                                    bOut=true; // after finding a matching output in last, we find an input here
                                }
                            }
                            if(bIn && bOut)
                            { // remove the last output if we found a pass through
                                if(EnumUsage.Input.equals(usage))
                                {
                                    bAddCPI=false;
                                }
                                else
                                {
                                    cpi.removeElementAt(-1);
                                    bAddCPI=true;
                                }
                            }
                            else
                            {
                                //not continuuos - reset
                                bAddCPI=true;
                            }
                        }
                    }
                    if(bAddCPI)
                        cpi.add(i);

                    lastGot=i;
                    typeLinkNamesLast=typeLinkNames;
                }                               
            }
            if(cpi.size()>0)
                resourceLink.setCombinedProcessIndex(cpi);
        }
        return resourceLink;
    }

    /**
     * get the resourcelinks in the resourcepool of this node
     * 
     * @return VElement             the vector of ResorceLinks:
     * @deprecated use getResourceLinks(null)
     */
    public VElement getResourceLinks()
    {
        return getResourceLinks(null);
    }

    /**
     * get the resourcelinks in the resourcepool of this node
     * 
     * @param Attributes mLinkAtt   the map of attributes
     * 
     * @return VElement             the vector of ResorceLinks:
     */
    public VElement getResourceLinks(JDFAttributeMap mLinkAtt)
    {
        final JDFResourceLinkPool resList = getResourceLinkPool();

        if (resList == null)
        {
            return new VElement();
        }

        // return contents as vector
        return resList.getPoolChildren(null, mLinkAtt, null);
    }

    /**
     * get the linked resources matching some conditions<br>
     * combines all linked resources from ResourceLinkPool, CustomerInfo, NodeInfo and AuditPool
     *
     * @param mAttribute mResAtt: map of Resource attributes to search for
     * @param boolean bFollowRefs true if internal references shall be followed
     * 
     * @return vResource: vector with all elements matching the conditions
     * 
     * default: getLinkedResources(null, false)
     */
    public VElement getLinkedResources(JDFAttributeMap mResAtt, boolean bFollowRefs)
    {
        final JDFResourceLinkPool resourceLinkPool = getResourceLinkPool();
        VElement vLinkedResources = new VElement();
        if (resourceLinkPool != null)
        {
            vLinkedResources = resourceLinkPool.getLinkedResources(null, 
                    null, mResAtt, bFollowRefs);
        }

        final JDFAuditPool auditPool = getAuditPool();
        if (auditPool != null)
        {
            vLinkedResources.appendUnique(auditPool.getLinkedResources(null, true));
        }

        // only needed for the JDF 1.2 subelement, resources are handled generically
        final JDFCustomerInfo customerInfo = (JDFCustomerInfo) getElement(ElementName.CUSTOMERINFO);
        if (customerInfo != null)
        {
            vLinkedResources.appendUnique(customerInfo.getLinkedResources(mResAtt, bFollowRefs));
        }

        // only needed for the JDF 1.2 subelement, resources are handled generically
        final JDFNodeInfo nodeInfo = (JDFNodeInfo) getElement(ElementName.NODEINFO);
        if (nodeInfo != null)
        {
            vLinkedResources.appendUnique(nodeInfo.getLinkedResources(mResAtt, bFollowRefs));
        }

        final JDFAncestorPool ancestorPool = getAncestorPool();
        if (ancestorPool != null)
        {
            vLinkedResources.appendUnique(ancestorPool.getLinkedResources(mResAtt, bFollowRefs));
        }
        return vLinkedResources;
    }

    /**
     * getPredecessors - get a vector of all direct predecessor or following nodes depending on bPre
     *
     * @param boolean bPre
     *
     * @return Vector
     *
     */
    public Vector getPredecessors(boolean bPre)
    {
        final Vector v = new Vector();
        final JDFResourceLinkPool rp = getResourceLinkPool();

        // get either all input or output resources, depending on bPre
        final Vector vLoc = rp.getInOutLinks(bPre?EnumUsage.Input:EnumUsage.Output, false, null,null);

        for (int i = 0; i < vLoc.size(); i++)
        {
            final JDFResource r = (JDFResource) vLoc.elementAt(i);
            // get all creator or consumer processes
            final Vector vc = r.getCreator(bPre);

            // recurse for these
            for (int j = 0; j < vc.size(); j++)
            {
                final JDFNode p = (JDFNode) vc.elementAt(j);
                v.addElement(p);
                final Vector vRec = p.getPredecessors(bPre);

                if (!vRec.isEmpty())
                {
                    v.addAll(vRec);
                }
            }
        }

        return v;
    }

    /**
     * isExecutable - checks whether a node is executable by checking the resources linked by the
     * resourcelinkpool and @Status or NodeInfo/@NodeStatus
     *
     * @param JDFAttributeMap partMap - the Attribute to check
     * @param boolean bCheckChildren 
     *                   if true, calculates the availability Status of a resource from all child partition leaves, 
     *                   else the Status is taken from the appropriate leaf itself
     *
     * @return boolean - true if the node is executable, false if not
     * 
     * default: isExecutable(null, true)
     */

    public boolean isExecutable(JDFAttributeMap partMap, boolean bCheckChildren)
    {
        final Vector v = getResourceLinkPool().getPoolChildren(null, null, null);
        EnumNodeStatus status=getPartStatus(partMap);
        if((status!=EnumNodeStatus.Waiting)&&(status!=EnumNodeStatus.Ready))
            return false;

        for (int i = 0; i < v.size(); i++)
        {
            final JDFResourceLink rl = (JDFResourceLink) v.elementAt(i);

            if (rl != null && !rl.isExecutable(partMap, bCheckChildren))
            {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * getProcessStatus
     *
     * gets the status of a certain partition of the node. The partition is given by a map of
     * partition attributes or by a JDFResource object containing such a map.
     * @deprecated use getPartStatus()
     */

    public JDFElement.EnumNodeStatus getProcessStatus(JDFAttributeMap mattr)
    {
        JDFElement.EnumNodeStatus stat = getStatus();

        if (stat.compareTo(JDFElement.EnumNodeStatus.Pool) != 0)
        {
            return stat;
        }

        stat = null;
        final KElement statusPoolEl = getElement_JDFElement(ElementName.STATUSPOOL, null, 0);

        if (statusPoolEl == null)
        {
            return stat;
        }
        final JDFStatusPool statusPool = (JDFStatusPool) statusPoolEl;
        return statusPool.getStatus(mattr);
    }

    /**
     *
     * ResourceTypeEqual
     *
     * Checks whether the given resources are of the same type. Resources are considered equal by
     * this method if they have identical Class attributes and their resource type is equal.
     * Basically the resource type is the node name.
     *
     * Two resources with different node names are considered equal if their Type attributes from
     * the ToolConfig.xml file are equal. This is not implemented yet. Instead of it is hard-coded
     * that "RunList" and "HDM:ReportList" are of the same type.
     *
     */

    static public boolean resourceTypeEqual(JDFResource res1, JDFResource res2)
    {
        final JDFResource.EnumResourceClass res1_class = res1.getResourceClass();
        final JDFResource.EnumResourceClass res2_class = res2.getResourceClass();

        if (!res1_class.equals(res2_class))
        {
            return false;
        }

        String res1_type = res1.getResourceType();
        String res2_type = res2.getResourceType();

        if (res1_type.compareTo("HDM:ReportList") == 0)
        {
            res1_type = ElementName.RUNLIST;
        }

        if (res2_type.compareTo("HDM:ReportList") == 0)
        {
            res2_type = ElementName.RUNLIST;
        }

        if (res1_type.compareTo(res2_type) == 0)
        {
            return true;
        }

        return false;
    }

    /**
     * Get a vector of all JDF children with type nodeType 
     * @param nodeType: node Type attribute
     * @param active:  Activation of the requesetd nodes, if null ignore activation
     * @param bDirect: if true, only direct children, 
     * else recurse down the tree and include this, i.e. return a complete tree starting at this 
     * 
     * @return: VElement of JDF nodes
     * 
     * default: getvJDFNode(null, JDFNode.EnumActivation.Unknown, false)
     */  
    public VElement getvJDFNode(String task, EnumActivation active, boolean bDirect)
    {

        final VElement v = new VElement();
        final VElement l = getTree(ElementName.JDF, null, null, bDirect, true);
        // only create a complete tree including this in the recursive case
        if (!bDirect)
        {
            l.add(this);
        }

        final boolean wantTask = !KElement.isWildCard(task);
        final int size = l.size();
        for (int i = 0; i < size; i++)
        {
            final JDFNode p = (JDFNode)l.elementAt(i);
            if (p.fitsActivation(active, true)
                    && (!wantTask || p.getType().equals(task)))
                v.addElement(p);
        }
        return v;
    }

    /**
     * getvJDFNode
     *
     * @param String  task
     * @param boolean active
     *
     * @return Vector of JDFNodes
     * 
     * default: getvJDFNode(null, false)
     * @deprecated use     public Vector getvJDFNode(task, JDFNode.EnumActivation.Unknown, false)
     */
    public Vector getvJDFNode(String task, boolean active)
    {
        return getvJDFNode(task, null, active);
    }

    /**
     * isActive
     * @deprecated use fitsActivation
     *
     * @return boolean
     */
    public boolean isActive()
    {
        return fitsActivation(EnumActivation.Active,true);
    }

    /**
     * @deprecated use fitsActivation
     * @param bWalkThroughAnchestors
     * @return
     */
    public boolean isActive(boolean bWalkThroughAnchestors)
    {
        return fitsActivation(EnumActivation.Active,bWalkThroughAnchestors);
    }

    //    ////////////////////////////////////////////////////////////////////

    public boolean fitsActivation(EnumActivation active, boolean bWalkThroughAnchestors)
    {
        if(active==null)
        {
            return true;
        }
        final EnumActivation a = getActivation(bWalkThroughAnchestors);
        if (active.equals(EnumActivation.TestRun))
        {
            return a.equals(EnumActivation.TestRun) || a.equals(EnumActivation.TestRunAndGo);
        }
        else if (active.equals(EnumActivation.Active))
        {
            return a.equals(EnumActivation.Active) || a.equals(EnumActivation.TestRunAndGo);
        }
        else
        {
            return a.equals(active);
        }
    }

    /**
     * removeNode - remove a node. If bLeaveSubmit is true, leave a stub with the id and status
     * field
     *
     * @param boolean bLeaveSubmit - if true, leave a stub with id and status field
     * 
     * default: removeNode(true)
     * @deprecated
     */
    public void removeNode(boolean bLeaveSubmit)
    {
        if (bLeaveSubmit)
        {
            final String id = getID();
            removeAttributes(VString.emptyVector);
            setAttribute(AttributeName.ID, id, null);
            setStatus(JDFElement.EnumNodeStatus.Spawned);
            removeChildren(null, null, null);
        }
        else
        {
            deleteNode();
        }
    }

    /**
     * addTask
     *
     * @param String task
     * @param Vector tasks
     * @deprecated - use addJDFNode
     * @return JDFNode
     */
    public JDFNode addTask(String task, VString tasks)
    {
        if (task.equals(JDFConstants.EMPTYSTRING))
        {
            return this;
        }

        final JDFNode p = (JDFNode) appendElement(ElementName.JDF, null);

        if (p != null)
        {
            if (task.equals(JDFConstants.COMBINED))
            {
                p.setCombined(tasks);
            }
            else
            {
                p.setType(task, false);
            }
        }

        return p;
    }

    /**
     * addTask
     *
     * @param String task
     *
     * @return JDFNode
     * @deprecated - use addJDFNode
     */
    public JDFNode addTask(String task)
    {
        return addTask(task, null);
    }


    /**
     * setType set the type attribute to the enumeration type
     *
     * @param newType the new type to set this to
     * 
     * default: setType(newType, false)
     */
    public void setType(EnumType typ)
    {
        setType(typ.getName(),true);
    }

    /**
     * setType set the type attribute to the string type
     *
     * @param newType the new type to set this to
     * @param checkName if true, check whether this type exists and throw an exception if not
     *
     * @return ignore,  always true
     * @throws JDFException if type is not a known JDF type
     * default: setType(newType, false)
     */
    public boolean setType(String newType, boolean checkName)
    {
        final EnumType eTyp = EnumType.getEnum(newType);
        if (!checkName || eTyp!=null)
        {
            removeAttribute("type",AttributeName.XSIURI);
            setAttribute(AttributeName.TYPE, newType, null);
            if(eTyp!=null)
                setXSIType(newType);
        }
        else
        {
            throw new JDFException("SetType illegal type: " + newType);
        }
        return true;
    }

    /**
     * getType - get node Type
     *
     * @return String - the type
     */
    public String getType()
    {
        return getAttribute(AttributeName.TYPE, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * version fixing routine for JDF
     *
     * uses heuristics to modify this element and its children to be compatible with a given version
     * in general, it will be able to move from low to high versions but potentially fail 
     * when attempting to move from higher to lower versions
     *
     * @param version: version that the resulting element should correspond to
     * @return true if successful
     */
    public boolean fixVersion(EnumVersion version)
    {
        boolean bRet=true;
        if(version!=null)
        {
            setVersion(version);
            setMaxVersion(version);
            bRet = fixNiCi(version);
        }
        if(!hasAttribute(AttributeName.JOBPARTID))
            setJobPartID(generateDotID(AttributeName.JOBPARTID,null));

        if(isJDFRoot()&&!hasAncestorAttribute(AttributeName.JOBID,null))
            setJobID(generateDotID(AttributeName.JOBID,null));

        return super.fixVersion(version) && bRet;
    }

///////////////////////////////////////////////////////////////

    private boolean fixNiCi(EnumVersion version)
    {
        boolean bRet=true;

        // fix NodeInfo and CustomerInfo
        for(int i=0;i<2;i++){
            String nam= (i>0) ? ElementName.NODEINFO : ElementName.CUSTOMERINFO;
            String linkNam=nam+JDFConstants.LINK;
            if(version.getValue()>=EnumVersion.Version_1_3.getValue()){
                bRet=fixNiCiToResource(i, nam, linkNam);
            }else{ // move to version <= 1.2
                fixNiCiToElement(i, nam, linkNam);
            }
        }
        return bRet;
    }



    private void fixNiCiToElement(int i, String nam, String linkNam)
    {
        JDFResourceLinkPool rlp=getResourceLinkPool();
        if(rlp!=null && rlp.hasChildElement(linkNam,null)){
            JDFResourceLink rl=rlp.getPoolChild(0,linkNam,null,null);
            JDFResource root=rl.getLinkRoot();
            JDFElement e=(JDFElement)getCreateElement(nam);

            // not partitioned, simply copy into nodeinfo element
            if(!root.hasAttribute(AttributeName.PARTIDKEYS)){
                e.mergeElement(root,false);
                if(i==1){
                    if(getStatus()==EnumNodeStatus.Part)
                    {
                        moveAttribute(AttributeName.STATUS,e,AttributeName.NODESTATUS,null,null);
                        if(e.hasAttribute(AttributeName.NODESTATUSDETAILS))
                            moveAttribute(AttributeName.STATUSDETAILS,e,AttributeName.NODESTATUSDETAILS,null,null);
                    }
                }
            }else{ // partitioned nodeinfo or customerinfo handling
                if(i==1){ // copy nodeinfo stati into statuspool
                    setStatus(EnumNodeStatus.Pool);
                    Vector vLeaves=root.getLeaves(false);
                    JDFStatusPool sp=getCreateStatusPool();
                    sp.removeChildren(null, null, null);
                    for(int j=0;j<vLeaves.size();j++){
                        JDFNodeInfo ni=(JDFNodeInfo)vLeaves.elementAt(j);
                        JDFPartStatus ps=sp.appendPartStatus();
                        ps.setPartMap(ni.getPartMap());
                        ps.setAttribute(AttributeName.STATUS,ni.getAttribute(AttributeName.NODESTATUS));
                        if(ni.hasAttribute(AttributeName.STATUSDETAILS))
                            ps.setAttribute(AttributeName.STATUSDETAILS,ni.getAttribute(AttributeName.NODESTATUSDETAILS));
                    }
                }
                // merge the most fitting resource partition into the unpartitioned 
                // nodeinfo or customerinfo
                JDFResource target=rl.getTarget();
                target.removeChildren(target.getNodeName(), null, null);
                target.expand(false);
                e.mergeElement(target,false);
                final String partidkeys = root.getAttribute(AttributeName.PARTIDKEYS,null,null);
                if(partidkeys!=null)
                    e.setAttribute(AttributeName.PARTIDKEYS,partidkeys);
            }

            ((JDFResource)e).cleanResourceAttributes();

            // ciao bello
            rl.deleteNode();
            root.deleteNode();
        }
    }




/////////////////////////////////////////////////////////////////////////

    private boolean fixNiCiToResource(int i, String nam, String linkNam)
    {
        boolean bRet=true;
        JDFResourceLinkPool rlp=getResourceLinkPool();
        if(hasChildElement(nam,null)||((i==1) && hasChildElement(ElementName.STATUSPOOL,null))){
            JDFElement e=(JDFElement)getElement(nam);
            // move nodeinfo or CustomerInfo into the resource
            if(rlp==null || !rlp.hasChildElement(linkNam,null)){
                JDFResource r=addResource(nam,null,EnumUsage.Input, null, null,null,null);
                if(e!=null){
                    r.mergeElement(e,false);
                }
                if(i==1) // nodeinfo
                { 
                    JDFNodeInfo ni=(JDFNodeInfo)r;

                    if(hasChildElement(ElementName.STATUSPOOL,null)){

                        // get PartStatus vector
                        JDFStatusPool statusPool=getStatusPool();
                        Vector vPartStatus=statusPool.getPoolChildren(null);
                        setStatus(EnumNodeStatus.Part);
                        JDFAttributeMap mps=null;
                        if(!vPartStatus.isEmpty()){
                            JDFPartStatus ps=(JDFPartStatus)vPartStatus.elementAt(0);
                            mps=ps.getPartMap();
                        }
                        VString partIDKeys=getPartIDKeys(mps);

                        ni.setAttribute(AttributeName.NODESTATUS,statusPool.getAttribute(AttributeName.STATUS));
                        ni.setAttribute(AttributeName.NODESTATUSDETAILS,statusPool.getStatusDetails());
                        for(int ips=0;ips<vPartStatus.size();ips++){
                            JDFPartStatus ps=(JDFPartStatus)vPartStatus.elementAt(ips);
                            try{ // see if the partstatus is consistent with what we have
                                ni=(JDFNodeInfo)r.getCreatePartition(ps.getPartMap(),partIDKeys);
                            }catch (JDFException ex){
                                // couldn't create a partiton - flag failure and move on
                                bRet=false;
                                continue;
                            }
                            ni.setAttribute(AttributeName.NODESTATUS,ps.getAttribute(AttributeName.STATUS));
                            if(ps.hasAttribute(AttributeName.STATUSDETAILS))
                                ni.setAttribute(AttributeName.NODESTATUSDETAILS,ps.getStatusDetails());
                        }
                        removeChild(ElementName.STATUSPOOL,null,0);
                    }
                    else // no statuspool
                    {
                        // not yet
                    }
                }
            }
            // have both link and element - snafu simply remove element
            removeChild(nam,null,0);
        }
        return bRet;
    }

    /**
     * typesafe validator
     * checks whether all resource links are ok
     * @return true if this node is valid
     */
    public boolean isValid(EnumValidationLevel level)
    {
        boolean bValid=super.isValid(level);
        if(!bValid) 
            return false;

        bValid=!hasInvalidLinks(level);
        if(bValid && EnumType.Product.equals(getEnumType()))
        {
            JDFNode n=getParentJDF();
            if(n!=null){
                bValid=EnumType.Product.equals(n.getEnumType());
            }
        }
        return bValid;
    }

    /**
     * true if invalid Links are in this element
     *
     * @param vKString& vInNameSpace list of namespaces where unknown Links are searched for. 
     *                               if empty, all namespaces are searched
     * @return boolean true if unknown Links are in this element
     * 
     * @default public boolean hasInvalidLinks (ValidationLevel_Complete)
     */
    public boolean hasInvalidLinks(EnumValidationLevel level)
    {
        return getInvalidLinks(level, 1).size() > 0;
    }

    /**
     * typesafe validator utility
     * @param EnumValidationLevel level validation level
     * @param bool bIgnorePrivate ignore objects in foreign namespaces
     * @param int nMax size of the returned vector
     * @return vKString vector of invalid Link names
     * 
     * @default getInvalidLinks (ValidationLevel_Complete, Integer.MAX_VALUE)
     */
    public VString getInvalidLinks(EnumValidationLevel level, int nMax)
    {
        final VString vElem = new VString();
        final Vector foundSingleLinks  = new Vector();
        final Vector foundSingleLinks2 = new Vector();

        final JDFResourceLinkPool linkPool = getResourceLinkPool();
        if (linkPool != null)
        {
            final VElement vLinks = linkPool.getPoolChildren(null, null, null);
            for (int i = vLinks.size() - 1; i >= 0; i--)
            {
                final JDFResourceLink rl = (JDFResourceLink) vLinks.elementAt(i);
                if(!isValidLink(level, rl, foundSingleLinks, foundSingleLinks2))
                {
                    vElem.appendUnique(rl.getLinkedResourceName());
                }
            }
        }

        if (requiredLevel(level))
        {
            vElem.appendUnique(getMissingLinkVector(nMax));
        }

        return vElem;
    }


    /**
     * UpDateStatus - update the status of a node depending on its resources and child nodes
     */
    public void upDateStatus()
    {
        final JDFResourceLinkPool resourceLinkPool = getResourceLinkPool();
        if(resourceLinkPool==null)
            return;

        final VElement vOut = resourceLinkPool.getInOutLinks(EnumUsage.Output, false, null,null);
        boolean bReady = true;

        if (vOut.isEmpty())
        {
            return;
        }

        for (int i = 0; i < vOut.size(); i++)
        {
            final JDFResource g = (JDFResource) vOut.elementAt(i);

            if (g.getResStatus(false).getValue()<EnumResStatus.Available.getValue())
            {
                bReady = false;
            }
        }

        if (bReady)
        {
            setStatus(JDFElement.EnumNodeStatus.Completed);
            final KElement parent = getParentNode_KElement();

            if (parent != null)
            {
                final JDFNode p = (JDFNode) parent;
                p.upDateStatus();
            }
        }
    }

    /**
     * getJobPart - get a child node with a given jobpartid
     *
     * @param String jobPartID - the ID of the part job
     * @param String jobID     - the ID of the job
     *
     * @return JDFNode
     * 
     * default: getJobPart(jobPartID, JDFConstants.EMPTYSTRING)
     */
    public JDFNode getJobPart(String jobPartID, String jobID)
    {
        if (includesAttribute(AttributeName.JOBPARTID, jobPartID))
        {
            return this;
        }

        final JDFAttributeMap jobPartIDMap = new JDFAttributeMap(AttributeName.JOBPARTID, jobPartID);

        if (jobID!=null && !jobID.equals(JDFConstants.EMPTYSTRING))
        {
            jobPartIDMap.put(AttributeName.JOBID, jobID);
        }

        //        System.out.println("JDFNode.getJobPart: " + getNodeName());
        final KElement child = getChildByTagName(getNodeName(), null, 0, jobPartIDMap, false, true);

        return (JDFNode) child;
    }

    /**
     * add any resources that live in ancestor nodes to this node
     * @param VString vRWResourceUsage: vector of resource names and Usage / ProcessUsage that are spawned as rw <br>
     *       the format is one of:<br>
     *                     ResName<br>
     *                     ResName:Input<br>
     *                     ResName:Output<br>
     *                     ResName:ProcessUsage<br>
     *                     ID<br>
     * @param VJDFAttributeMap vParts: vector of JDFAttributeMaps that describe the parts to spawn
     * 
     * @return VElement: vector of resources or resource partitions that would be spawned rw multiple times
     */
    public HashSet checkSpawnedResources(VString  vRWResources, VJDFAttributeMap vSpawnParts)
    {
        HashSet v = new HashSet();
        // grab the root node and all it's children
        HashSet vRootLinks = getAllRefs(null,true);
        Iterator iter=vRootLinks.iterator();
        while(iter.hasNext())
        {
            JDFElement liRoot = (JDFElement)iter.next();

            boolean  bResRW = false;
            if(liRoot instanceof JDFResourceLink)
            {
                bResRW = linkFitsRWRes((JDFResourceLink)liRoot, vRWResources);
                if(bResRW)
                {
                    JDFResourceLink rl = (JDFResourceLink)liRoot;
                    JDFResource r      = rl.getTarget();
                    if(r!=null)
                    {
                        VElement vRes      = new VElement();
                        if(vSpawnParts==null || vSpawnParts.isEmpty())
                        {
                            vRes = r.getLeaves(false);
                        }
                        else
                        {
                            for(int j = 0; j < vSpawnParts.size(); j++)
                            { 
                                vRes.appendUnique(r.getPartitionVector(vSpawnParts.elementAt(j), null));
                            }
                        }
                        for(int k = 0; k < vRes.size(); k++)
                        {
                            JDFResource rTarget = (JDFResource)vRes.elementAt(k);
                            if(rTarget.getSpawnStatus() == JDFResource.EnumSpawnStatus.SpawnedRW)
                            {
                                if(!v.contains(rTarget))
                                    v.add(rTarget);
                            }
                        }
                    }
                }
            }
            else if(liRoot instanceof JDFRefElement)
            {
                JDFRefElement re = (JDFRefElement)liRoot;
                JDFResource r    = re.getTarget();
                if(r!=null){
                    bResRW = resFitsRWRes(r,vRWResources);
                    if(bResRW)
                    {
                        if(r.getSpawnStatus() == JDFResource.EnumSpawnStatus.SpawnedRW)
                            if(!v.contains(r))
                                v.add(r);
                    }
                }
            }
        }
        // empty if all is well
        return v;
    }


    /**
     * get inter-resource linked resource refs and resourcs links
     * @param VString vDoneRefs:
     * @param bool bRecurse if true, also return references linked from the resource pool directly
     * 
     * @return velement: the vector of referenced resource refs and links
     */
    public HashSet getAllRefs(HashSet vDoneRefs, boolean bRecurse)
    {
        HashSet v1 = vDoneRefs!=null ? vDoneRefs : new HashSet();

        JDFResourcePool rp = getResourcePool();
        if(rp != null && bRecurse)
            v1 = rp.getAllRefs(v1, bRecurse);

        JDFResourceLinkPool rlp = getResourceLinkPool();
        if(rlp != null)
            v1 = rlp.getAllRefs(v1, bRecurse);

        JDFCustomerInfo ci = getCustomerInfo();
        if(ci != null)
            v1 = ci.getAllRefs(v1, bRecurse);

        JDFNodeInfo ni = getNodeInfo();
        if(ni != null)
            v1 = ni.getAllRefs(v1, bRecurse);

        JDFAncestorPool ap = getAncestorPool();
        if(ap != null)
            v1 = ap.getAllRefs(v1, true);

        Vector vNodes = getvJDFNode(null, null, true);
        for(int i = 0; i < vNodes.size(); i++)
            v1 = ((JDFNode) vNodes.elementAt(i)).getAllRefs(v1,bRecurse);

        return v1;
    }


    /**
     * setCombined - set the combined node types to the values in vCombiNodes
     *
     * @param Vector vCombiNodes
     */
    public void setCombined(VString vCombiNodes)
    {
        setType(JDFConstants.COMBINED, false);
        setTypes(vCombiNodes);
    }

    /**
     * getCombinedTypes - get the list of combined types if this is a combined node
     *
     * @return Vector
     */
    public Vector getCombinedTypes()
    {
        if (!getType().equals(JDFConstants.COMBINED))
        {
            return new Vector();
        }

        final String s = getAttribute(AttributeName.TYPES, null, JDFConstants.EMPTYSTRING);
        return StringUtil.tokenize(s, JDFConstants.BLANK, false);
    }

    /**
     * addComponent - add a component resource to resroot and link it to this process
     *
     * @param String  cType
     * @param boolean bInput
     * @param JDFNode resRoot
     * @param boolean bLink
     *
     * @return JDFComponent
     * @deprecated - use standard addResource
     * default: addComponent(cType, bInput, null, true)
     */
    public JDFComponent addComponent(
            String cType,
            boolean bInput,
            JDFNode resRoot,
            boolean bLink)
    {
        JDFComponent c =(JDFComponent)addResource(
                ElementName.COMPONENT,
                JDFResource.EnumResourceClass.Quantity,
                bInput,
                resRoot,
                bLink,
                null);

        if (c != null)
        {
            // true --> input resource
            c.setResStatus(JDFResource.EnumResStatus.Unavailable,false);
            c.setDescriptiveName(cType);
        }

        return c;
    }

    /**
     *
     */
    public void setSpawnID(String value)
    {
        setAttribute(JDFConstants.SPAWNID, value, null);
    }

    /**
     * spawn a node; url is the file name of the new node,
     * vRWResourceUsage is the vector of Resources Usages
     * (or Names if no usage exists for the process) that are
     * spawned RW, all others are spawned read only;
     * vParts is the vector of part maps that are to be spawned,
     * defaults to no part, i.e. the whole thing
     * 
     * @param parentURL
     * @param spawnURL: URL of the spawned JDF file
     * @param vRWResources: vector of resource names and Usage / ProcessUsage that are spawned as rw <br>
     *       the format is one of:<br>
     *                     ResName:Input<br>
     *                     ResName:Output<br>
     *                     ResName:ProcessUsage<br>
     * @param VJDFAttributeMap vSpawnParts: vector of mAttributes that describe the parts to spawn, only 
     *        valid PartIDKeys are allowed
     * @param bSpawnROPartsOnly if true, only the parts of RO resources that are specified in vParts are 
     *                                  spawned, else the complete resource is spawned
     * @param bCopyNodeInfo copy the NodeInfo elements into the Ancestors
     * @param bCopyCustomerInfo copy the CustomerInfo elements into the Ancestors
     * @param bCopyComments copy the Comment elements into the Ancestors
     * 
     * @return The spawned node
     * @since 050831 added bCopyComments
     * @ tbd enhance nested spawning of partitioned nodes
     * @deprecated - use JDFSpawn class ( see code below) 
     * default: spawn(parentURL, null, null, null, false, false, false, false)
     */
    public JDFNode spawn(
            String parentURL,
            String spawnURL,
            Vector vRWResources_in,
            VJDFAttributeMap vSpawnParts,
            boolean bSpawnROPartsOnly,
            boolean bCopyNodeInfo, 
            boolean bCopyCustomerInfo,
            boolean bCopyComments)
    {
        JDFSpawn spawn=new JDFSpawn(this);
        spawn.bCopyComments=bCopyComments;
        spawn.bCopyCustomerInfo=bCopyCustomerInfo;
        spawn.bCopyNodeInfo=bCopyNodeInfo;
        spawn.bSpawnROPartsOnly=bSpawnROPartsOnly;
        spawn.vSpawnParts=vSpawnParts;
        spawn.vRWResources_in=new VString(vRWResources_in);
        spawn.spawnURL=spawnURL;
        spawn.parentURL=parentURL;
        return spawn.spawn();
        

    }

/////////////////////////////////////////////////////////////////////////

    /**
     * spawn a node in informative mode without modifying the root JDF; url is the file name of the new node,
     * the parameters except for the list of rw resources, which are by definition empty, are identical to those of Spawn
     *
     * vRWResourceUsage is the vector of Resources Usages, Resource Names or Resource IDs that are
     * spawned RW, all others are spawned read only;
     * vParts is the vector of part maps that are to be spawned,
     * defaults to no part, i.e. the whole thing
     *
     * @param spawnURL: URL of the spawned JDF file
     * @param vParts: vector of mAttributes that describe the parts to spawn
     * @param bSpawnROPartsOnly if true, only the parts of RO resources that are specified in vParts are spawned, else the complete resource is spawned
     * @param bCopyNodeInfo copy the NodeInfo elements into the Ancestors
     * @param bCopyCustomerInfo copy the CustomerInfo elements into the Ancestors
     * @param bCopyComments copy the Comment elements into the Ancestors
     * @return JDFDoc: The spawned node's owner document.
     * 
     * @default spawnInformative(parentURL, 
     *                           null, 
     *                           null, 
     *                           false, 
     *                           false, 
     *                           false, 
     *                           false); 
     *@deprecated USE JDFSpawn.spawnInformative()
     */
    public JDFNode spawnInformative(String parentURL, 
            String spawnURL, 
            VJDFAttributeMap vSpawnParts, 
            boolean bSpawnROPartsOnly,
            boolean bCopyNodeInfo, 
            boolean bCopyCustomerInfo,
            boolean bCopyComments)
    {
        JDFSpawn _spawn=new JDFSpawn(this);
        return _spawn.spawnInformative(parentURL, spawnURL, vSpawnParts, bSpawnROPartsOnly, bCopyNodeInfo, bCopyCustomerInfo, bCopyComments);
    }   

///////////////////////////////////////////////////////////////////   

    /**
     * Method unSpawn. undo a spawn, removing any and all bookkeeping of that spawning
     * 
     * @param spawnID spawnID of the spawn to undo
     * @return the fixed unspawned node
     * @deprecated use new JDFSpawn(this).unSpawn(spawnID);
     */
    public JDFNode unSpawn(String spawnID)
    {
       return new JDFSpawn(this).unSpawn(spawnID);
    }


    /**
     * merge nodes in a way that no duplicate elements are created
     * attention !! this kills pools !!
     * @param JDFNode e: the node element to merge with the current node
     * @param boolean bDelete: if true KElement e will be deleted
     * @return JDFNode: the merged node element
     */
    //JDFNode MergeNode(JDFNode e,bool bDelete);
    public JDFNode mergeNode(JDFNode e, boolean bDelete)
    {
        // merge nodes in a way that no duplicate elements are created
        // attention - this kills pools....

        final VElement v = e.getChildElementVector(null ,null, null, true, 0, false);
        for (int i = 0; i < v.size(); i++)
        {
            final KElement m = (KElement) v.elementAt(i);
            final String strName = m.getNodeName();
            KElement mHere = null;
            if ((strName.equals(ElementName.NODEINFO))
                    || (strName.equals(ElementName.CUSTOMERINFO))
                    || (strName.equals(ElementName.RESOURCEPOOL))
                    || (strName.equals(ElementName.RESOURCELINKPOOL))
                    || (strName.equals(ElementName.ANCESTORPOOL))
                    || (strName.equals(ElementName.AUDITPOOL)))
            {
                mHere = getElement_JDFElement(m.getNodeName(), null, 0);
            }
            if (mHere == null)
            {
                if (bDelete)
                {
                    moveElement(m, null);
                }
                else
                {
                    copyElement(m, null);
                }
            }
            else
            {
                mHere.mergeElement(m, bDelete);
                if (bDelete)
                {
                    m.deleteNode();
                }
            }
        }

        setAttributes(e);
        return this;
    }

    /**
     * getLink - get the resourcelink that resides in the ResourceLinkPool of this node and references
     * the resource r
     *
     * @param r      - the resource you are searching a link for
     * @param bInput
     *
     * @return JDFResourceLink - the resource link you was searching for or if not found, a new
     *                            empty JDFResource Link
     * 
     * default: getLink(r, true)
     * @deprecated use getLink(resource, EnumUsage)
     */
    public JDFResourceLink getLink(JDFResource r, boolean bInput)
    {
        return getLink(r,bInput ? EnumUsage.Input : EnumUsage.Output);
    }
    /**
     * getLink - get the resourcelink that resides in the ResourceLinkPool of this node and references
     * the resource r
     *
     * @param r      - the resource you are searching a link for
     * @param usage  - the usage attribute of the link. If null, both input and output resourelinks will be returned
     *
     * @return JDFResourceLink - the resource link you was searching for or if not found, null
     * 
     */
    public JDFResourceLink getLink(JDFResource r, EnumUsage usage)
    {
        // get the reslink pool
        final JDFResourceLinkPool p = getResourceLinkPool();

        if (p == null || r==null)
        {
            return null;
        }

        // get any possible links
        final VElement v = p.getInOutLinks(usage, true, null,null);

        // is it the right one?
        final int vSize = v.size();
        for (int i = 0; i < vSize; i++)
        {
            final JDFResourceLink resLink = (JDFResourceLink) v.elementAt(i);

            if (resLink != null && 
                    resLink.getrRef().equals(r.getID()) && 
                    resLink.getLocalName().equals(r.getLinkString()))
            {
                return resLink;
            }
        }

        // nothing found
        return null;
    }

    /**
     * getRoot - this function returns the root of the JDF document
     *
     * @return JDFNode - the root node of the document
     */
    public JDFNode getRoot()
    {
        return (JDFNode) getDeepParent(ElementName.JDF, Integer.MAX_VALUE);
    }

    /**
     * getAncestorIDs
     *
     * @return Vector
     */
    public VString getAncestorIDs()
    {
        final VString vs = new VString();
        JDFNode me = this;

        while (true)
        {
            final String pid = me.getID();
            vs.addElement(pid);

            KElement parent = me.getParentNode_KElement();

            if (parent == null)
            {
                break;
            }
            else if (!(parent instanceof JDFNode))
            {
                break;
            }

            me = (JDFNode) parent;
        }

        // 010702 invert
        final VString vs2 = new VString();
        for (int i = vs.size() - 1; i >= 0; i--)
        {
            vs2.addElement(vs.elementAt(i));
        }

        return vs2;
    }

    /**
     * getAncestorNode - return the ancestor node
     *
     * @param int nSkip
     * @deprecated - use getParentJDF
     * @return JDFNode - the ancestor node
     */
    public JDFNode getAncestorNode(int nSkip)
    {
        KElement parent = getParentNode_KElement();
        JDFNode node = (JDFNode) parent;

        if (node != null)
        {
            for (int i = 0; i < nSkip; i++)
            {
                parent = node.getParentNode_KElement();
                node = (JDFNode) parent;

                if (node == null)
                {
                    break;
                }
            }
        }

        return node;
    }

    /**
     * searches for the first element occurence in the parent nodes 
     * and then the ancestor elements of the root ancestorpool
     *
     * @param String element: the element name
     * @param String nameSpaceURI: the XML-namespace
     * @param String def: the default if it does not exist
     * 
     * @return String: value of attribute found, value of def if not available
     * 
     * default: getAncestorAttribute(attrib, null, "")
     */
    public String getAncestorAttribute(
            String attrib,
            String nameSpaceURI,
            String def)
    {
        String s=getInheritedAttribute(attrib, nameSpaceURI, def);
        if((s==null && def==null)||(s!=null && !s.equals(def)))
        {
            return s;
        }
        // not in the inherited nodes, check the root node's AncestorPool
        final JDFAncestorPool ap = getJDFRoot().getAncestorPool();
        if (ap == null)
        {
            return JDFConstants.EMPTYSTRING;
        }
        return ap.getAncestorAttribute(attrib, nameSpaceURI, def);
    }

    /**
     * true if a non default attribute occurence in the parent nodes 
     * and then the ancestor elements of the root ancestorpool exists
     *
     * @param String attrib: the attribute name
     * @param String nameSpaceURI: the XML-namespace
     * 
     * @return boolean: true if the attribute exists
     * 
     * default: hasAncestorAttribute(attrib, "")
     */
    public boolean hasAncestorAttribute(String attrib, String nameSpaceURI)
    {
        return getAncestorAttribute(attrib, nameSpaceURI, "\"").compareTo("\"") != 0;
    }

    /**
     * Check existance of attribute Activation
     * @param bool bInherit recurse through ancestors and Ancestor elements of the AncestorPool when searching
     * @return bool true if attribute Activation exists
     */
    //bool hasActivation(bool bInherit=false) ;
    public boolean hasActivation(boolean bInherit)
    {
        if (bInherit)
        {
            return hasAncestorAttribute(AttributeName.ACTIVATION, null);
        }
        return hasAttribute(AttributeName.ACTIVATION, null, false);
    }

    /**
     * Check existance of attribute JobID
     * @param bool bInherit recurse through ancestors and Ancestor elements of the AncestorPool when searching
     * @return bool true if attribute JobID exists
     * @deprecated
     */
    //bool hasJobID(bool bInherit=false) ;
    public boolean hasJobID(boolean bInherit)
    {
        if (bInherit)
        {
            return hasAncestorAttribute(AttributeName.JOBID, null);
        }
        return hasAttribute(AttributeName.JOBID, null, false);
    }

    /**
     * searches for the first element occurence in the ancestor elements
     * @param KString attrib: the attribute name
     * @param KString nameSpaceURI: the XML-namespace
     * @since 180502 
     * @return KString: value of attribute found, empty string if not available
     */
    public KElement getAncestorElement(String element, String nameSpaceURI)
    {
        JDFElement e=(JDFElement) getInheritedElement(element, nameSpaceURI, 0);
        if(e!=null)
            return e;

        // not in the inherited nodes, check the root node's AncestorPool
        JDFAncestorPool ap=getJDFRoot().getAncestorPool();
        if(ap==null)
            return null;

        return ap.getAncestorElement(element, nameSpaceURI, null);
    }

    /**
     * true if a non default attribute occurence in the parent nodes 
     * and then the ancestor elements of the root ancestorpool exists
     *
     *@deprecated
     * @param KString attrib: the attribute name
     * @param KString nameSpaceURI: the XML-namespace
     * @since 180502 
     * @return bool: true if the attribute exists
     */
    public boolean hasAncestorElement(String element, String nameSpaceURI)
    {

        return getAncestorElement(element, nameSpaceURI)!=null;
    }

    /**
     * addParameter - add a parameter resource to resroot and link it to this process
     *
     * @param String  strName
     * @param boolean bInput
     * @param JDFNode resRoot
     * @param boolean bLink
     *
     * @return JDFResource
     * @deprecated use addResource(strName, JDFResource.EnumClass.Parameter, bInput, resRoot, bLink, null)
     */
    public JDFResource addParameter(
            String strName,
            boolean bInput,
            JDFNode resRoot,
            boolean bLink)
    {
        return addResource(
                strName,
                JDFResource.EnumResourceClass.Parameter,
                bInput,
                resRoot,
                bLink,
                null);
    }

    /**
     * addConsumable - add a consumable resource to resroot and link it to this process
     *
     * @param String  name
     * @param boolean bInput
     * @param JDFNode resRoot
     * @param boolean bLink
     *
     * @deprecated use addResource(name, null, true, null, true)
     * @return JDFResource
     * 
     * default: addResource(name, null, true, null, true)
     */
    public JDFResource addConsumable(
            String strName,
            boolean bInput,
            JDFNode resRoot,
            boolean bLink)
    {
        return addResource(
                strName,
                JDFResource.EnumResourceClass.Consumable,
                bInput,
                resRoot,
                bLink,
                null);
    }

    /**
     * addHandling - add a handling resource to resroot and link it to this process
     *
     * @param String  name
     * @param boolena bInput
     * @param JDFNode resRoot
     * @param boolean bLink
     *
     * @deprecated use addResource(name, null, true, null, true)
     * @return JDFResource
     * 
     * default: addResource(name, JDFResource.EnumClass.Handling, true, null, true)
     */
    public JDFResource addHandling(
            String strName,
            boolean bInput,
            JDFNode resRoot,
            boolean bLink)
    {
        return addResource(
                strName,
                JDFResource.EnumResourceClass.Handling,
                bInput,
                resRoot,
                bLink,
                null);
    }


    /**
     * isCombined - is this a Combined resource type ?
     *
     * return boolean - true if it is, otherwise false
     * @deprecated use JDFConstants.COMBINED.equals(getType());
     */
    public boolean isCombined()
    {
        return JDFConstants.COMBINED.equals(getType());
    }

    /**
     * Is this a Combined node type ?
     * return boolean: true if this is a product node
     * @deprecated use JDFConstants.PRODUCT.equals(getType());
     */
    public boolean isProduct()
    {
        return JDFConstants.PRODUCT.equals(getType());
    }

    /**
     * Is this a Combined node type ?
     * @return boolean: true if this is a combined node
     * @deprecated use JDFConstants.PROCESSGROUP.equals(getType());
     */
    public boolean isProcessGroup()
    {
        return JDFConstants.PROCESSGROUP.equals(getType());
    }

    /**
     * Is this a group node type (ProcessGroup or Product)?
     * return boolean: true if this is a combined node
     */
    public boolean isGroupNode()
    {
        final String type2 = getType();
        return JDFConstants.PROCESSGROUP.equals(type2) || JDFConstants.PRODUCT.equals(type2);
    }

    /**
     * getIDPrefix
     *
     * @return the ID prefix of JDFNode
     */
    public String getIDPrefix()
    {
        return "n";
    }

    /**
     * get string attribute JobID
     * @param boolean bInherit - recurse through ancestors when searching
     * @return String - attribute value  
     */
    public String getJobID(boolean bInherit)
    {
        if (bInherit)
        {
            return getAncestorAttribute(AttributeName.JOBID, null, JDFConstants.EMPTYSTRING);
        }
        return getAttribute(AttributeName.JOBID, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * get string attribute JobID
     * @return String - attribute value  
     * @deprecated use getJobPartID(false);
     */
    public String getJobPartID()
    {
        return getJobPartID(false);
    }

    /**
     * get string attribute JobID
     * @param boolean bInherit - if true recurse through ancestors when searching
     * @return String - attribute value
     * 
     * default - getJobPartID(flase)
     */
    public String getJobPartID(boolean bInherit)
    {
        if (bInherit)
        {
            return getAncestorAttribute(AttributeName.JOBPARTID, null, JDFConstants.EMPTYSTRING);
        }
        return getAttribute(AttributeName.JOBPARTID, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * setJobPartID
     *
     * @param String jobPartID
     */
    public void setJobPartID(String jobPartID)
    {
        setAttribute(AttributeName.JOBPARTID, jobPartID, null);
    }

    /**
     * set attribute JobID
     *@param String value: the value to set the attribute to
     */
    public void setJobID(String value)
    {
        setAttribute(AttributeName.JOBID, value, null);
    }

    /**
     * test element StatusPool existance
     * @return bool true if a matching element exists 
     * @deprecated
     */
    public boolean hasStatusPool()
    {
        return numChildElements(ElementName.STATUSPOOL, null) > 0;
    }

    /**
     * get string attribute SpawnID
     * @return String - attribute value
     */
    public String getSpawnID()
    {
        return getSpawnID(false);
    }

    /**
     * get string attribute SpawnID
     * @param boolean bInherit - if true recurse through ancestors when searching
     * @return String - attribute value
     * 
     * default - getSpawnID(flase)
     */
    public String getSpawnID(boolean bInherit)
    {
        if(bInherit)
        {
            return getAncestorAttribute(AttributeName.SPAWNID, 
                    null, JDFConstants.EMPTYSTRING);
        }
        return getAttribute(AttributeName.SPAWNID);
    }

    /**
     * remove attribute SpawnID
     *@deprecated
     */
    public void removeSpawnID()
    {
        removeAttribute(JDFConstants.SPAWNID, null);
    }

    /**
     * remove element AncestorPool
     * @deprecated
     */
    public void removeAncestorPool()
    {
        removeChild(ElementName.ANCESTORPOOL, null, 0);
    }

    /**
     * get the Parent JDFNode, null if the parent element is the document or an
     * envelope xml
     * @return JDFNode: the parent JDF, null if this is the root JDF
     * @deprecated use getParentJDF()
     * */
    public JDFNode getParentJDFNode()
    {
        return getParentJDF();
    }

    /**
     * get the Parent JDFNode, null if the parent element is the document or an
     * envelope xml
     * @return JDFNode: the parent JDF, null if this is the root JDF
     */
    public JDFNode getParentJDF()
    {
        final KElement jdfElem = getParentNode_KElement();        
        if (jdfElem != null && jdfElem instanceof JDFNode)
        {
            return (JDFNode) jdfElem;
        }       
        return null;
    }

    private VString vLinkInfo(int namIndex)
    {

        final VString vRet    = new VString();
        final VString linkInfo  = linkInfo();

        if (namIndex < 0)
        {
            // tokenize retains order
            return new VString(linkInfo);
        }

        final VString linkNames = linkNames();

        final String strName  = linkNames.stringAt(namIndex);

        while (namIndex >= 0)
        {
            final String kToken  = linkInfo.stringAt(namIndex);
            final VString  vToken = StringUtil.tokenize(kToken, JDFConstants.BLANK, false);

            vRet.addAll(vToken);
            namIndex = linkNames.indexOf(strName, ++namIndex);
        }

        return vRet.isEmpty() ? null : vRet;
    }

    /**
     * Get the index in Linknames of the ResourceLink described by rl
     * @param JDFResourceLink rl
     * @param int nOccur for looping over combined nodes
     * @return -1 if it does not fit
     */
    private int[] getMatchingNamIndex(JDFResourceLink rl, int nOccur)
    {
        int[] ret=new int[2];
        ret[0]=ret[1]=-1;
        if (rl == null)
            return ret;

        // 311002 KM added nOccur for looping over combined nodes
        // TBD evaluate CombinedProcessIndex when generating nOccur
        final VString linkNames = linkNames();
        if(linkNames==null)
            return ret;

        int namIndex = linkNames.indexOf(rl.getLinkedResourceName());


        //int namIndex = vName.index((new KString(rl.getNodeName())).leftStr(-4), nOccur);
        // 120802 rp add check for *
        if (namIndex < 0)
        {
            namIndex = linkNames.indexOf(JDFConstants.STAR);
        }

        if (namIndex < 0)
        {
            return ret;
        }

        final VString vIndex = vLinkInfo(namIndex);
        if(vIndex==null)
            return ret;

        int iLoop = 0;
        for (int i = 0; i < vIndex.size(); i++)
        {

            final String typ = (String) vIndex.elementAt(i);

            if (typ.charAt(0) == 'i' && !JDFResourceLink.EnumUsage.Input.equals(rl.getUsage()))
            {
                continue;
            }
            if (typ.charAt(0) == 'o' && !JDFResourceLink.EnumUsage.Output.equals(rl.getUsage()))
            {
                continue;
            }
            if (typ.length() > 2)
            { // processusage is specified
                if (!StringUtil.rightStr(typ, -2).equals(rl.getProcessUsage()))
                {
                    continue;
                }
            }
            else
            { // no processusage is specified
                if (rl.hasAttribute(AttributeName.PROCESSUSAGE))
                {
                    continue;
                }

            }
            if(iLoop++ < nOccur)
            {
                continue;
            } 
            ret[0]=namIndex;
            ret[1]=i;
            return ret;
        }
        return ret;
    }


    /**
     * Check existance of attribute Type
     * @return bool true if attribute Type exists
     * @deprecated use inline hasAttribute
     */
    public boolean hasType()
    {
        return hasAttribute(AttributeName.TYPE, null, false);
    }

    /**
     typesafe validator
     */
    public VString getInsertLinkVector(int nMax) 
    {
        final VString names = linkNames();
        final VString vInsert = new VString();
        for (int i = 0; i < names.size(); i++)
        {
            final VString types = vLinkInfo(i);
            for (int j = 0; j < types.size(); j++)
            {
                final EnumProcessUsage pu = getEnumProcessUsage((String)types.elementAt(j), 0);
                if ( ((String)types.elementAt(j)).charAt(1) == '?' || 
                        ((String)types.elementAt(j)).charAt(1) == '_' )
                {
                    // 110602 added
                    if (getMatchingLink((String) names.elementAt(i), pu, 0) != null)
                    {
                        continue; // skip existing links with maxOccurs=1
                    }
                }

                String s = (String)names.elementAt(i) + "Link";
                if (!pu.equals(EnumProcessUsage.Any))
                {
                    s += JDFConstants.COLON + pu.getName();
                }
                vInsert.add(s);
                if (vInsert.size() >= nMax)
                {
                    break;
                }
            }
        }
        return vInsert;
    }

    //////////////////////////////////////////////////////////////////////

    //default for int i = 0
    public EnumProcessUsage getEnumProcessUsage(String info, int i)
    {
        String iToken = StringUtil.token(info,i,JDFConstants.BLANK);
        if(iToken.equals(JDFConstants.EMPTYSTRING))
        {
            return EnumProcessUsage.Any;
        }

        if(iToken.length() > 2)
        {
            final String pu = iToken.substring(2);
            return EnumProcessUsage.getEnum(pu);
        }

        if(iToken.charAt(0) == 'i')
        {
            return EnumProcessUsage.AnyInput;
        }
        else if(iToken.charAt(0) == 'o')
        {
            return EnumProcessUsage.AnyOutput;
        }
        else
        {
            throw new JDFException("JDFNode.getEnumProcessUsage: bad input: " + info);
        }
    }

    /**
     * test element AncestorPool existance
     * @deprecated use numChildElements(ElementName.ANCESTORPOOL, null) > 0;
     * @return bool true if a matching element exists 
     */
    public boolean hasAncestorPool()
    {
        return numChildElements(ElementName.ANCESTORPOOL, null) > 0;
    }

    /**
     * Check existance of attribute ProjectID
     * @param bool bInherit recurse through ancestors when searching
     * @return bool true if attribute ProjectID exists
     * @deprecated
     */
    public boolean hasProjectID(boolean bInherit)
    {
        if (bInherit)
        {
            return hasAncestorAttribute(JDFConstants.PROJECTID, null);
        }
        return hasAttribute(JDFConstants.PROJECTID, null, false);
    }

    /**
     * Check existance of attribute ProjectID
     * @return bool true if attribute ProjectID exists
     * @deprecated
     */
    public boolean hasProjectID()
    {
        return hasProjectID(false);
    }


    /**
     * set attribute ProjectID
     *@param String value: the value to set the attribute to
     */
    public void setProjectID(String strValue)
    {
        setAttribute(JDFConstants.PROJECTID, strValue, null);
    }

    /**
     * get string attribute ProjectID
     * @param bool bInherit recurse through ancestors when searching
     * @return String the value of the attribute 
     */
    public String getProjectID(boolean bInherit)
    {
        if (bInherit)
        {
            return getAncestorAttribute(JDFConstants.PROJECTID, null, JDFConstants.EMPTYSTRING);
        }
        return getAttribute(JDFConstants.PROJECTID, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * get string attribute ProjectID
     * @return String the value of the attribute 
     * @deprecated use getProjectID(boolean bInherit)
     */
    public String getProjectID()
    {
        return getProjectID(false);
    }

    /**
     * definition of the length of LinkNames, LinkInfo for generic JDF nodes
     * @return int length of the generic () link string functions,
     * @deprecated use m_strGenericLinkNames.length;
     */
    public int getGenericLinksLength()
    {
        return m_strGenericLinkNames.length;
    }

    /**
     * isValidLink   check whether an index is legal for this class
     * @param        level the checking level
     * @param        rl the JDFResourceLink to check
     * @param        doneNameList    Vector of Integer
     * @param        doneIndexList   Vector of Integer
     * @return boolean true if valid
     */

    public boolean isValidLink(EnumValidationLevel level, JDFResourceLink rl, Vector doneNameList, Vector doneIndexList)
    {
        if (rl == null)
        {
            return false;
        }

        if (!isInJDFNameSpaceStatic(rl))
        {
            return true;
        }

        // allow call with initial null
        if(doneIndexList==null)
            doneIndexList =new Vector();
        if(doneNameList==null)
            doneNameList =new Vector();

        int nOccur = 0;
        int iIndex;
        int namIndex;

        // loop over all potential occurrences
        while (true)
        {
            // on the C++ side the following two methods are represented with
            // a method getMatchingIndex(rl, iIndex, nOccur) that
            // has 3 parameters, one of them is a reference(!) at iIndex
            int[] ii= getMatchingNamIndex(rl, nOccur);
            namIndex = ii[0];
            iIndex = ii[1];

            // not found -> check whether this node is known yet
            if (namIndex < 0)
            {
                if (!hasAttribute(AttributeName.TYPE, null, false))
                {
                    return true;
                }
                if (getType().equals(ElementName.PROCESSGROUP))
                {
                    return true;
                }
                return false;
            }

            // loop over all completed occurrences with maxOccurs=1
            // if they have already been found, search for next occurrence
            boolean bTryNext = false;
            final int dns = doneNameList.size();

            for (int i = 0; i < dns; i++)
            {
                if (((Integer) doneNameList.elementAt(i)).intValue()==namIndex && 
                        ((Integer) doneIndexList.elementAt(i)).intValue()==iIndex)
                {
                    nOccur++; // this one is gone, try next
                    bTryNext = true;
                    break; // 
                }
            }

            if (!bTryNext) // we got here and the link is potentially valid
            {
                break;
            }
        }

        final boolean isValid = rl.isValid(level);

        //TODO remove line     wchar_t card=vLinkInfo(namIndex)[iIndex][1];
        final VString vCard = vLinkInfo(namIndex);
        final String str = vCard.stringAt(iIndex);
        final char card = str.charAt(1);

        if (isValid && ((card == '_') || (card == '?')))
        {
            doneNameList.addElement(new Integer(namIndex));
            doneIndexList.addElement(new Integer(iIndex));
        }

        return isValid;
    }


    /**
     * @deprecated use getMissingLinkVector
     * @param nMax
     * @return
     */
    public VString getMissingLinks(int nMax)
    {
        return getMissingLinkVector(nMax);
    }

    /////////////////////////////////////////////////////////////////////////

    /**
     * get the links that match the typesafe resource name
     * if the Resource type is not defined for the process represented by this node
     * see chapter 6 JDFSpec, then the links are ignored
     *
     * @param resName of the resource to remove
     * @param bLink: if false, returns the linked resources, else if true, returns the ResourceLink elements
     * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
     * @return vector of resourcelink elements
     */
    public VElement getMatchingLinks(String resName, boolean bLink, EnumProcessUsage processUsage)
    {
        VElement vE=null;

        final JDFResourceLinkPool rlp = getResourceLinkPool();
        if (rlp == null)
        {
            return null;
        }

        final VString linkNames = linkNames();
        if(linkNames==null)
        {
            return rlp.getInOutLinks(null, bLink, resName,null);
        }

        int namIndex = linkNames.indexOf(resName);
        if (namIndex < 0)
        {
            namIndex = linkNames.indexOf(JDFConstants.STAR);
            if (namIndex < 0)
            {
                return null;
            }
        }

        final VString vInfo = vLinkInfo(namIndex);


        if (processUsage!=null && processUsage.getValue() > EnumProcessUsage.Any.getValue())
        {
            final String pu=processUsage.getName();
            for (int i = 0; i < vInfo.size(); i++)
            {
                if (((String)vInfo.elementAt(i)).endsWith(pu))
                {
                    final boolean bInput = ((String)vInfo.elementAt(i)).charAt(0) == 'i';
                    // 240502 RP bug fix by Komori
                    vE = rlp.getInOutLinks(bInput?EnumUsage.Input:EnumUsage.Output, bLink, resName,processUsage);
                    break;
                }
            }
        }
        else
        {
            if(processUsage==null)
            {
                vE=rlp.getPoolChildren(null,null,null);
                if (!bLink)
                {
                    vE = JDFResourceLinkPool.resourceVector(vE, resName);
                }
            }
            else if (processUsage == EnumProcessUsage.AnyInput)
            {
                vE = rlp.getInOutLinks(true?EnumUsage.Input:EnumUsage.Output, bLink, resName,null);
                int vEsize = vE==null ? 0 : vE.size();
                // 170205 RP remove internal pipes from all inputs
                // TODO ideally we would check if they are connected, but this is a sufficient 98% solution
                if(bLink)
                {
                    for(int i = vEsize - 1; i >= 0; i--)
                    {
                        JDFResourceLink rl = (JDFResourceLink)vE.elementAt(i);
                        if(rl.getPipeProtocol().equals(JDFConstants.INTERNAL))
                        {
                            vE.remove(i);
                        }
                    }
                }
            }
            else if (processUsage == EnumProcessUsage.AnyOutput)
            {
                vE = rlp.getInOutLinks(false?EnumUsage.Input:EnumUsage.Output, bLink, resName,null);
                int vEsize = vE==null ? 0 : vE.size();
                // 170205 RP remove internal pipes from all outputs
                // TODO ideally we would check if they are connected, but this is a sufficient 98% solution
                if(bLink)
                {
                    for(int i = vEsize-1; i >= 0; i--)
                    {
                        JDFResourceLink rl = (JDFResourceLink)vE.elementAt(i);
                        if(JDFConstants.INTERNAL.equals(rl.getPipeProtocol()))
                        {
                            vE.remove(i);
                        }
                    }
                }
            }
        }        
        return vE;
    }

    /**
     * get a vector of Link names that are missing in this element<br>
     * if the links need a processusage, the format is LinkName:ProcessUsage
     *
     * @param int nMax maximum size of the returned vector
     * @return  VString vector of strings that contains missing Link names
     */
    public VString getMissingLinkVector(int nMax)
    {
        final VString names = linkNames();
        if(names==null)
            return null;
        if(getType().equals(EnumType.ProcessGroup.getName())){ // TODO fix processgroup with Types and gray box category entries
            return null;
        }

        final VString vMissing = new VString();
        final int nameSize = names.size();
        for (int i = 0; i < nameSize; i++)
        {
            final VString types = vLinkInfo(i);
            final int size = types.size();
            for (int j = 0; j < size; j++)
            {
                final String typesAt = (String) types.elementAt(j);
                if (typesAt.charAt(1) == '+' || typesAt.charAt(1) == '_')
                {
                    // 110602 added
                    final EnumProcessUsage pu = getEnumProcessUsage(typesAt, 0);
                    if (getMatchingLink((String)names.elementAt(i), pu, 0) == null)
                    {
                        String s = names.elementAt(i) + "Link";

                        if(pu != EnumProcessUsage.Any)
                        {
                            s += JDFConstants.COLON + pu.getName();
                        }

                        vMissing.addElement(s);
                        if (vMissing.size() >= nMax)
                        {
                            break;
                        }
                    }
                }
            }
        }

        return vMissing;
    }

    /**
     * 
     * @param resName
     * @param processUsage
     * @param partMap
     * @param pos
     * @deprecated
     * @return
     */
    public JDFResource getMatchingResource(String resName, int processUsage, JDFAttributeMap partMap, int pos)
    {
        final JDFResourceLink rl = getMatchingLink(resName, EnumProcessUsage.getEnum(processUsage), pos);

        if (rl == null)
        {
            return null;
        }

        if (!partMap.isEmpty() && !rl.hasPartMap(partMap))
        {
            return null;
        }

        final JDFResource r = rl.getTarget();
        return r.getPartition(partMap, null);
    }

    /**
     * get the resource that matches a typesafe resource name
     * if the Resource type is not defined for the process represented by this node
     * see chapter 6 JDFSpec, then the resource is ignored
     * 
     * @param resName of the resource to remove
     * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
     * @param pos the position of the link if multiple matching links exist
     * @return the resourcelink element
     */
    public JDFResource getMatchingResource(String resName, EnumProcessUsage processUsage, JDFAttributeMap partMap, int pos)
    {
        final JDFResourceLink rl = getMatchingLink(resName, processUsage, pos);
        if (rl == null)
            return null;

        if (partMap!=null && !partMap.isEmpty() && !rl.hasPartMap(partMap))
        {
            return null;
        }

        final JDFResource r = rl.getTarget();
        if (r == null)
            return null;
        
        return r.getPartition(partMap, null);
    }

    /**
     * get the link that matches the typesafe resource name
     * if the Resource type is not defined for the process represented by this node
     * see chapter 6 JDFSpec, then the link is ignored
     *
     * @param resName of the resource to remove
     * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
     * @param pos the position of the link if multiple matching links exist
     * @return JDFResourceLink the resourcelink
     */    
    public JDFResourceLink getMatchingLink(String resName, EnumProcessUsage processUsage, int pos)
    {
        JDFResourceLink rl = null;

        final VElement vE = getMatchingLinks(resName, true, processUsage);
        if (vE != null && vE.size() > pos)
        {
            rl = (JDFResourceLink) vE.elementAt(pos);
        }
        return rl;
    }

    /**
     * Method AppendMatchingResource. 
     * Appends a resource and link it to this if it is listed in the list of valid nodes for for a JDF with the given type
     * also creates the matching resource link in this
     * 
     * @param resName the name of the resource to add
     * @param processUsage the processUsage of the resourcelink of the resource to add,
     * null EnumProcessUsage.AnyOutput for input but no processUsage 
     * EnumProcessUsage.AnyOutput for output but no processUsage
     * 
     * @param resourceRoot the root JDF node that is the parent of the resourcepool where the resource should be added
     * if null, this node is assumed
     * 
     * @return JDFResource the newly created resource
     */
    public JDFResource appendMatchingResource(String resName, EnumProcessUsage processUsage, JDFNode resourceRoot)
    {
        final VString vtyp = getMatchType(resName, processUsage);
        if (vtyp==null) // anything goes
        {
            boolean bInput=!EnumProcessUsage.AnyOutput.equals(processUsage);
            JDFResource r=addResource(resName, null, bInput, resourceRoot, true, null);
            JDFResourceLink rl=getLink(r,null);
            if(processUsage!=null && processUsage.getValue()>EnumProcessUsage.Any.getValue())
            {
                rl.setProcessUsage(processUsage);
            }
            return r;
        }

        int nFound=0;
        String foundTyp=null;
        boolean foundMulti=false;
        int iInputFound=0; // 1 is in, 2 is out

        for (int i = 0; i < vtyp.size(); i++)
        {
            final String typ = (String)vtyp.elementAt(i);
            final boolean bInput = typ.charAt(0) == 'i';

            if ((typ.charAt(1) == '?') || (typ.charAt(1) == '_'))
            {
                if (numMatchingLinks(resName, false, processUsage) > nFound)
                {
                    nFound++; // TODO need to fix this, it is only a 90% solution
                    continue;
                }
            }
            if (foundTyp==null)
            {
                foundTyp=typ;
                iInputFound=bInput ? 1 : 2; 

            }
            else if(!typ.equals(foundTyp))
            {
                foundMulti=true;
                if((bInput ? 1 : 2) != iInputFound){
                    iInputFound=0; // we could have either in or out - cannot link
                    break;
                }
            }
        }
        if(foundTyp==null)
        {
            // should only get here it the link alreay exists
            throw new JDFException("JDFNode.appendMatchingResource already exists");
        }

        final JDFResource r = addResource(resName, null, true, resourceRoot, false, null);
        if(iInputFound>0)
        {
            final JDFResourceLink rl = linkResource(r,iInputFound==1 ? EnumUsage.Input : EnumUsage.Output,null);
            if (!foundMulti && foundTyp.length() > 2)
            {
                rl.setProcessUsage(EnumProcessUsage.getEnum(foundTyp.substring(2)));
            }
        }

        return r;
    }


    private VString getMatchType(String resName, EnumProcessUsage processUsage)
    {
        final VString vRet = new VString();
        final VString linkNames = linkNames();
        if(linkNames==null)
            return null;

        int namIndex = linkNames.indexOf(resName);
        if (namIndex < 0)
        {
            namIndex = linkNames.indexOf(JDFConstants.STAR);
        }
        if (namIndex < 0)
        {
            throw new JDFException("JDFNode.appendMatchingResource invalid resName: " + resName);
        }

        final VString vInfo = vLinkInfo(namIndex);
        if(vInfo==null)
            return null;

        if ((processUsage == null) || (processUsage == EnumProcessUsage.Any))
            return vInfo; // no filtering required

        String infoTemp = null;
        final String pu = processUsage.getName();

        for (int i = 0; i < vInfo.size(); i++)
        {
            infoTemp = (String) vInfo.elementAt(i);

            if (processUsage.getValue() > EnumProcessUsage.Any.getValue())
            {
                if (infoTemp.endsWith(pu))
                {
                    vRet.add(infoTemp);
                }
            }
            else
            {
                if (processUsage.getValue() == EnumProcessUsage.AnyInput.getValue()
                        && infoTemp.charAt(0) == 'i')
                {
                    vRet.add(infoTemp);
                }
                else if (processUsage.getValue() == EnumProcessUsage.AnyOutput.getValue()
                        && infoTemp.charAt(0) == 'o')
                {
                    vRet.add(infoTemp);
                }
            }
        }

        return vRet.isEmpty() ? null : vRet;
    }

    /**
     * @param resName
     * @param processUsage
     * @param bRemoveResource
     * @param pos
     * @return
     * 
     * default: removeMatchingLink(resName, processUsage, false, 0)
     */
    public boolean removeMatchingLink(String resName, int processUsage, boolean bRemoveResource, int pos)
    {
        JDFResourceLink l = null;
        l = getMatchingLink(resName, EnumProcessUsage.getEnum(processUsage), pos);
        if (l == null)
        {
            return false;
        }
        if (bRemoveResource)
        {
            final JDFResource r = l.getLinkRoot();
            if (r.getLinks(null, null).size() == 0)
            {
                r.deleteNode();
            }
        }
        l.deleteNode();
        return true;
    }


    public boolean removeMatchingLinks(String resName, EnumProcessUsage processUsage, boolean bRemoveResource)
    {
        final VElement v = getMatchingLinks(resName, true, processUsage);
        for(int i = 0; i < v.size(); i++)
        {
            if(bRemoveResource)
            {
                final JDFResource r = ((JDFResourceLink)v.elementAt(i)).getLinkRoot();
                // only remove if not linked from elsewhere
                if(r.getLinks(null, null).isEmpty())
                {    
                    r.deleteNode(); 
                } 
            }
            ((JDFResourceLink)v.elementAt(i)).deleteNode();
        }
        return v.size() > 0;
    }

    /**
     * @param resource
     * @param processUsage
     * @param partMap
     * @return
     * 
     * default: LinkMatchingResource(resource, processUsage, null)
     */
    public JDFResourceLink 
    linkMatchingResource(JDFResource resource, EnumProcessUsage processUsage, JDFAttributeMap partMap) 
    {
        JDFResourceLink rl = null;

        final String resName = resource.getLocalName();
        final VString vtyp   = getMatchType(resName, processUsage);

        for (int i = 0; i < vtyp.size(); i++)
        {
            final String typ = (String)vtyp.elementAt(i);
            if ((typ.charAt(1) == '?') || (typ.charAt(1) == '_'))
            {
                if (numMatchingLinks(resName, false, processUsage) > 0)
                {
                    continue; // not this one...
                }
            }

            rl = linkResource(resource,typ.charAt(0) == 'i' ? EnumUsage.Input : EnumUsage.Output,null);
            if (typ.length() > 2)
            {
                rl.setProcessUsage(EnumProcessUsage.getEnum(typ.substring(2)));
            }

            rl.setPartMap(partMap);

            return rl;
        }

        // should only get here it the link alreay exists
        throw new JDFException("JDFNode.LinkMatchingResource already exists");
    }

    /**
     * @param resName
     * @param bLink
     * @param processUsage
     * @return
     * 
     * default: NumMatchingLinks(resName, true, ProcessUsage_Any.getValue())
     */
    public int numMatchingLinks(String resName, boolean bLink, EnumProcessUsage processUsage)
    {
        int iNumMatchingLinks = 0;
        final VElement v = getMatchingLinks(resName, bLink, processUsage);
        if (v != null)
        {
            iNumMatchingLinks = v.size();
        }
        return iNumMatchingLinks;
    }

    /* ******************************************************
     // Element getter / setter
     **************************************************************** */

    public JDFAncestorPool getCreateAncestorPool()
    {
        return (JDFAncestorPool) getCreateElement_KElement(ElementName.ANCESTORPOOL, null, 0);
    }
    public JDFAncestorPool appendAncestorPool()
    {
        return (JDFAncestorPool) appendElementN(ElementName.ANCESTORPOOL, 1, null);
    }
    public JDFAncestorPool getAncestorPool()
    {
        return (JDFAncestorPool) getElement(ElementName.ANCESTORPOOL, null, 0);
    }

    //////////////////////////////////////////////////////////////////////

    public JDFAuditPool getCreateAuditPool()
    {
        return (JDFAuditPool) getCreateElement_KElement(ElementName.AUDITPOOL, null, 0);
    }
    public JDFAuditPool appendAuditPool()
    {
        return (JDFAuditPool) appendElementN(ElementName.AUDITPOOL, 1, null);
    }
    public JDFAuditPool getAuditPool()
    {
        return (JDFAuditPool) getElement(ElementName.AUDITPOOL, null, 0);
    }

    /**
     * gets the existing CustomerInfo or creates a new one if none exists
     * this method will check if a NodeInfo exists, 
     * 
     * @return the found or created CustomerInfo.
     */
    public JDFCustomerInfo getCreateCustomerInfo()
    {
        return (JDFCustomerInfo) getCreateNiCi(ElementName.CUSTOMERINFO);
    }

    /**
     * appends a CustomerInfo to this 
     * 
     * @return the appended CustomerInfo
     */
    public JDFCustomerInfo appendCustomerInfo()
    {
        if(getCustomerInfo()!=null)
        {
            throw new JDFException("JDFNodeInfo.appendNodeInfo: NodeInfo already exists");
        }
        return getCreateCustomerInfo();
    }

    /**
     * gets the existing CustomerInfo
     * 
     * @return the existing CustomerInfo.
     */
    public JDFCustomerInfo getCustomerInfo()
    {       
        return (JDFCustomerInfo) getNiCi(ElementName.CUSTOMERINFO, false,null);
    }

    /**
     * gets the existing inherited CustomerInfo or NodeInfo from parents including ancestorpool
     * 
     * @return the existing CustomerInfo or NodeInfo.
     */
    private KElement getNiCi(String elementName, boolean bInherit, String xPath)
    {       
        // always get the element
        KElement nici=getElement(elementName);
        EnumVersion eVer = getVersion(true);

        // if version>=1.0 or no direct element is there try the resource
        if (eVer.getValue() >= EnumVersion.Version_1_3.getValue() || (nici == null))
        {
            JDFResourceLinkPool rlp = getResourceLinkPool();
            if(rlp != null){
                JDFResourceLink rl=rlp.getPoolChild(0,elementName+"Link",new JDFAttributeMap(AttributeName.USAGE,"Input"),null);
                if(rl != null)
                    nici=rl.getTarget();
            }
        }

        // continue search if not found
        if(nici!=null && (xPath!=null) &&(!nici.hasXPathNode(xPath)))
        {
            nici=null;
        }

        if(nici!=null || ! bInherit)
            return nici;

        JDFNode parent = getParentJDF();
        if(parent!=null){
            return parent.getNiCi(elementName,bInherit, xPath);
        }
        JDFAncestorPool ap=getAncestorPool();
        if(ap!=null)
            return ap.getAncestorElement(elementName,null,xPath);
        return null;
    }
    ///////////////////////////////////////////////////////////////////////////////////

    /**
     * gets the existing NodeInfo or creates a new one if none exists
     * this method will check if a NodeInfo exists, 
     * 
     * @return the found or created nodeinfo.
     */
    public JDFNodeInfo getCreateNodeInfo()
    {
        return (JDFNodeInfo) getCreateNiCi(ElementName.NODEINFO);
    }
    /**
     * gets the existing NodeInfo or creates a new one if none exists
     * this method will check if a NodeInfo exists, 
     * 
     * @return the found or created nodeinfo.
     */
    private KElement getCreateNiCi(String s)
    {
        // check if this already has a Nodeinfo
        KElement nici = getNiCi(s, false, null);
        if (nici == null)
        {
            EnumVersion eVer = getVersion(true);
            if (eVer.getValue() >= EnumVersion.Version_1_3.getValue())
            {
                nici = addParameter(s, true, this, true);
            }
            else
            {
                nici = appendElement(s);
            }
        }
        return nici;
    }

    /**
     * appends a NodeInfo to this
     * 
     * @return the appended NodeInfo
     */
    public JDFNodeInfo appendNodeInfo()
    {
        if(getNodeInfo() !=null)
        {
            throw new JDFException("JDFNodeInfo.appendNodeInfo: NodeInfo already exists");
        }
        return getCreateNodeInfo();
    }  

    /**
     * gets the existing local NodeInfo whether it is a resource or an element
     * 
     * @return the existing NodeInfo.
     */
    public JDFNodeInfo getNodeInfo()
    {
        return (JDFNodeInfo) getNiCi(ElementName.NODEINFO,false,null);
    }

    ///////////////////////////////////////////////////////////////////////////////////

    /**
     * get first NodeInfo element from child list or child list of any ancestor
     * @param xPath the the xPath to an element or attribute that must exist in the queried CustomerInfo
     * note that attributes must be marked with an "@", 
     * if xPath=null, simply return the next in line
     * 
     * @return  JDFNodeInfo The matching NodeInfo element
     */
    public JDFNodeInfo getInheritedNodeInfo(String xPath)
    {
        return (JDFNodeInfo) getNiCi(ElementName.NODEINFO,true,xPath);      
    }
    /**
     * get first NodeInfo element from child list or child list of any ancestor
     * @return  JDFNodeInfo The element
     * @deprecated 060221 use getInheritedNodeInfo(String xPath)
     */
    public JDFNodeInfo getInheritedNodeInfo()
    {
        return getInheritedNodeInfo(null);      
    }

    /**
     * remove element NodeInfo
     * 
     * with ProcessUsage="Ancestor" is infinity. Use removeNodeInfos() to remove all.
     */
    public void removeNodeInfo()
    {
        removeNiCi(ElementName.NODEINFO);
    }
    /**
     * remove element Customerinfo whether it is an element or a resource
     * 
     */
    public void removeCustomerInfo()
    {
        removeNiCi(ElementName.CUSTOMERINFO);
    }
    /**
     * remove element NodeInfo or CustomerInfo whether it is an element or a resource
     * 
     */
    private void removeNiCi(String elmName)
    {
        // just in case : zapp them both
        removeResource(elmName, 0);
        removeChild(elmName, null, 0);
    }

    /**
     * removes all NodeInfo elements 
     * @deprecated removes only 1 NodeInfo. In Version 1.3 the cardinality of NodeInfo 
     *
     */
    public void removeNodeInfos()
    {
        while (numNodeInfos() > 0)
        {
            KElement remRes = removeResource(ElementName.NODEINFO, 0);
            if(remRes == null)
            {
                //remove all in the resource pool
                break;
            }
        }

        //remove all direct childs
        Vector nodeInfoChilds = getChildElementVector(ElementName.NODEINFO, null, null, false, Integer.MAX_VALUE,false);
        for(int i = 0; i < nodeInfoChilds.size(); i++)
        {
            removeChild((Node)nodeInfoChilds.elementAt(i));
        }
    }


    /**
     * removes all CustomerInfo elements whether it is an element or a resource
     *
     *@deprecated 060220 use removeCustomerInfo
     */
    public void removeCustomerInfos()
    {
        //TODO hasCustomerInfo returns true if there is one or more customerinfo ANYWHERE
        //so the while loop will end in an infinite loop (the break prohibit this but thats not 
        //realy nice (same for removeNodeInfos)!
        while (hasCustomerInfo())
        {
            KElement remRes = removeResource(ElementName.CUSTOMERINFO, 0);
            if(remRes == null)
            {
                //remove all in the resource pool
                break;
            }
        }

        //remove all direct childs
        Vector nodeInfoChilds = getChildElementVector(ElementName.CUSTOMERINFO, null, null, false, Integer.MAX_VALUE,false);
        for(int i = 0; i < nodeInfoChilds.size(); i++)
        {
            removeChild((Node)nodeInfoChilds.elementAt(i));
        }
    }


    /**
     * removes a Resource from this ResourceLinkPool and from the resourcePool if it is 
     * no longer linked to any other process
     * 
     * @param nodeName  the Nodename of the Resource "NodeInfo" for example
     * @param iSkip     number to skip before deleating
     * @return KElement the removed resource, null if nothing was found or deleated e.g. 4 found 
     *                  and the 5th is the one to deleate. The link is not returned. 
     *                  If the link is deleated and the resource is still linked to another 
     *                  process null is returned. 
     */
    public JDFResource removeResource(String nodeName, int iSkip)
    {
        JDFResource kRet = null;

        JDFResourceLinkPool rlp = getResourceLinkPool();
        if (rlp != null)
        {
            String linkName = nodeName + "Link";

            JDFResourceLink rl = (JDFResourceLink) rlp.getChildByTagName(linkName, null, iSkip, null, true, false);
            if (rl!=null)
            {
                kRet=rl.getTarget();
                rlp.removeChild(rl);
                if(!kRet.deleteUnLinked()) // it is still linked - don't zapp
                    kRet=null;               
            }
        }        
        return kRet;
    }

    /**
     * Number of NodeInfo elements
     * @return int number of matching elements
     * @deprecated must never be more than one...
     */
    public int numNodeInfos()
    {
        int i = numChildElements(ElementName.NODEINFO, null);

        JDFResourceLinkPool rlp = getResourceLinkPool();
        if (rlp != null)
        {
            i += rlp.getPoolChildren("NodeInfoLink", null, null).size();
        }

        return i;
    }


    /**
     * Number of NodeInfo elements
     * @return int number of matching elements
     * @deprecated must never be more than one...
     */
    public int numCustomerInfos()
    {
        int i = numChildElements(ElementName.CUSTOMERINFO, null);

        JDFResourceLinkPool rlp = getResourceLinkPool();
        if (rlp != null)
        {
            i += rlp.getPoolChildren("CustomerInfoLink", null, null).size();
        }

        return i;
    }

    /**
     * test whether either an element NodeInfo exists
     * or a JDF 1.3 NodeInfo Resource exists
     * @return bool true if at least one matching element exists 
     * @deprecated use getNodeInfo()!=null 
     */
    public boolean hasNodeInfo()
    {
        return getNodeInfo()!=null;
    }



    /**
     * test whether either an element CustomerInfo exists
     * or a JDF 1.3 CustomerInfo Resource exists
     * @return bool true if at least one matching element exists
     * @deprecated use getCustomerInfo()!=null 
     */
    public boolean hasCustomerInfo()
    {
        return getCustomerInfo()!=null;
    }

    //////////////////////////////////////////////////////////////////////

    public JDFResourceLinkPool getCreateResourceLinkPool()
    {
        return (JDFResourceLinkPool) 
        getCreateElement_KElement(ElementName.RESOURCELINKPOOL, null, 0);
    }
    public JDFResourceLinkPool appendResourceLinkPool()
    {
        return (JDFResourceLinkPool) appendElementN(ElementName.RESOURCELINKPOOL, 1, null);
    }
    public JDFResourceLinkPool getResourceLinkPool()
    {
        return (JDFResourceLinkPool) getElement(ElementName.RESOURCELINKPOOL, null, 0);
    }

    //////////////////////////////////////////////////////////////////////

    public JDFResourcePool getCreateResourcePool()
    {
        return (JDFResourcePool) getCreateElement_KElement(ElementName.RESOURCEPOOL, null, 0);
    }
    public JDFResourcePool appendResourcePool()
    {
        return (JDFResourcePool) appendElementN(ElementName.RESOURCEPOOL, 1, null);
    }
    public JDFResourcePool getResourcePool()
    {
        return (JDFResourcePool) getElement(ElementName.RESOURCEPOOL, null, 0);
    }

    //////////////////////////////////////////////////////////////////////

    public JDFStatusPool getCreateStatusPool()
    {
        setStatus(JDFElement.EnumNodeStatus.Pool);
        return (JDFStatusPool) getCreateElement_KElement(ElementName.STATUSPOOL, null, 0);
    }

    public JDFStatusPool appendStatusPool()
    {
        setStatus(JDFElement.EnumNodeStatus.Pool);
        return (JDFStatusPool) appendElementN(ElementName.STATUSPOOL, 1, null);
    }

    public JDFStatusPool getStatusPool()
    {
        return (JDFStatusPool) getElement(ElementName.STATUSPOOL, null, 0);
    }

    /**
     * get a Child JDFNode with a given ID attribute
     *
     * @param String id the id of the child
     * @param boolean bDirect: if true, only direct children, else recurse down the tree
     *
     * @return JDFNode: the parent JDF, null if this is the root JDF
     * 
     * default: getChildJDFNode(id, false)
     */
    public JDFNode getChildJDFNode(String id, boolean bDirect)
    {
        if(getID().equals(id))
        {
            return this;
        }

        final JDFAttributeMap m = new JDFAttributeMap(AttributeName.ID, id);
        return (JDFNode)getTreeElement(ElementName.JDF,null, m, bDirect, true);
    }

    /**
     * Check existance of attribute Version
     * @param bool bInherit recurse through ancestors and Ancestor elements of the AncestorPool when searching
     * @return bool true if attribute Version exists
     * 
     * default: hasVersion(false)
     */
    public boolean hasVersion(boolean bInherit)
    {
        if (bInherit)
        {
            return hasAncestorAttribute(AttributeName.VERSION, null);
        }
        return hasAttribute(AttributeName.VERSION, null, false);
    }


    /**
     * set attribute Version
     *@param KString value: the value to set the attribute to
     *@deprecated use JDFElement.setVersion(EnumVersion.getEnum(value))
     */
    public void setVersion(String value)
    {
        setAttribute(AttributeName.VERSION, value, null);
    }


    /**
     * get EnumVersion attribute Version
     * @param boolean bInherit - recurse through ancestors when searching
     * @return EnumVersion - attribute value
     * 
     * default - getVersion(false)
     * 
     * this method replaces the C++ methods GetVersion and GetEnumVersion
     */
    public EnumVersion getVersion(boolean bInherit)
    {
        String version;
        if (bInherit)
        {
            version = getAncestorAttribute(AttributeName.VERSION,  null, null);
        }
        else
        {
            version = getAttribute(AttributeName.VERSION,null,null);
        }

        return EnumVersion.getEnum(version);
    }

    /** 
     * clone the target resource of this and generate a ResourceAudit in the parent node's AuditPool
     * both resourcelinks of the ResourceAudit are filled in<br>
     * the resource selected by this may now be modified. <br>
     * the cloned copy has a new Id in the format: (thisID)_old_nnn 
     *
     * @return the ResourceAudit that was created
     */
    public JDFResourceAudit cloneResourceToModify(JDFResourceLink resLink)
    {
        JDFResourceAudit resourceAudit = null;

        final JDFResource r       = resLink.getLinkRoot();
        final JDFResourcePool p   = r.getParentJDF().getResourcePool();
        final JDFResource oldCopy = (JDFResource) p.copyElement(r, null);

        if (oldCopy != null)
        {
            oldCopy.setLocked(true);
            final String newID = r.newModifiedID();
            oldCopy.setID(newID);
            resourceAudit = prepareToModifyLink(resLink);
            JDFResourceLink resLinkAudit = (JDFResourceLink) resourceAudit.copyElement(resLink, null);

            if (resLinkAudit != null)
            {
                resLinkAudit.setrRef(newID);
            }
        }

        return resourceAudit;
    }

    //////////////////////////////////////////////////////////////////////

    /** 
     * Generate a ResourceAudit in the parent node's AuditPool
     * an initial copy of the not yet modified resourcelink is inserted<br>
     * call JDFResourceAudit.UpdateLink with the modified link to finalize
     *
     * @return the ResourceAudit that was created
     */
    public JDFResourceAudit prepareToModifyLink(JDFResourceLink resLink)
    {

        final JDFAuditPool ap = getCreateAuditPool();
        final JDFResourceAudit resourceAudit = ap.addResourceAudit(null);
        if (resourceAudit != null)
        {
            resourceAudit.setContentsModified(false);
            resourceAudit.updateLink(resLink);
        }
        return resourceAudit;
    }

    /**
     * get the Types as a vector of strings
     * @return VString The vector of Strings in Types, null if this may not contain multiple types
     */
    public VString getTypes()
    {
        final EnumType type = EnumType.getEnum(getType());
        if (type==null || !type.equals(EnumType.Combined) && !type.equals(EnumType.ProcessGroup))
        {
            return null;
        }
        final String types = getAttribute(AttributeName.TYPES, null, null);
        return types==null ? null : new VString(types,null);
    }
    /**
     * get the Types as a vector of EnumType
     * @return Vector The vector of enumerated types, null if extensions exist that hinder us from generating a complete vector
     * e.g. extension types or gray box names
     */
    public Vector getEnumTypes()
    {
        VString types=getTypes();
        if(types==null)
            return null;
        Vector vs=null;
        boolean bFirst=true;
        for(int i=0;i<types.size();i++)
        {
            EnumType typ=EnumType.getEnum(types.stringAt(i));
            if(typ==null)
                return null;
            if(bFirst)
            {
                bFirst=false;
                vs=new Vector();
            }
            vs.add(typ);
        }
        return vs;
    }

    /**
     * Gets the vector of the string Type/Types attribute values of the given JDFNode by 
     * recursively traversing the tree
     * returns exactly one element="Product" if the tested node's type is product
     *
     * @param JDFNode jdfRoot - the ProcessGroup JDFNode
     * @return VString - vector of Type/Types attributes of the tested ProcessGroup JDFNode
     * @throws JDFException if the testen JDFNode has illegal combination of attribute 'Types' and child JDFNodes
     */
    public VString getAllTypes()
    {
        VString vs=null;
        final String myType = getType();
        if(myType.equals(JDFConstants.PRODUCT))
        {
            vs=new VString(JDFConstants.PRODUCT,null);
        }
        else if(myType.equals(JDFConstants.COMBINED))
        {
            vs=getTypes();
        }
        else if(myType.equals(JDFConstants.PROCESSGROUP))
        {
            VElement vNodes = getvJDFNode(null,null,true);
            vs = getTypes();
            final int nodeSize = vNodes.size();
            if (vs!=null) // grey box or simple type 
            {
                if (nodeSize!=0) 
                {
                    throw new JDFException ("JDFNode.getAllTypes: illegal combination of the attribute 'Types' and child JDF Nodes");
                }
                return vs;// __Lena__  May contain GrayBoxes
            }
            for (int i=0; i<nodeSize; i++) 
            {
                JDFNode node = (JDFNode)vNodes.elementAt(i);
                VString allTypes = node.getAllTypes();
                if(allTypes!=null)
                {
                    if(vs==null)
                    {
                        vs=allTypes;
                    }
                    else
                    {
                        vs.addAll(allTypes);
                    }
                }
            }
        }
        else
        {
            final String type=myType;
            vs=new VString(type,null);
        }
        return vs;
    }

    //new since Ver 2.0
    /*
     * Attribute Types must be defined in Combined Nodes (Type="Combined")
     * and may be defined in ProcessGroup Nodes (Type="ProcessGroup") 
     */
    public void setTypes(VString vCombiNodes)
    {
        EnumType type = EnumType.getEnum(getType());
        if (type.equals(EnumType.Combined) || type.equals(EnumType.ProcessGroup))
        {
            setAttribute(AttributeName.TYPES, vCombiNodes, null);
        }
        else
        {
            throw new JDFException("Setting Types on illegal node Type:"+getType());
        }
    }

    /**
     * get the links that are selected by a given CombinedProcessIndex
     * all links with no CombinedProcessIndex are included in the list
     * 
     * @param combinedProcessIndex the process type for which to get the links
     * @param nType the nTh occurence of the Type field, -1 if all valid positions are ok
     * 
     * @default getLinksForType(type, -1)
     */
    public VElement getLinksForCombinedProcessIndex(int combinedProcessIndex)
    {
        EnumType typ = getEnumType();
        if(!EnumType.Combined.equals(typ)&&!EnumType.ProcessGroup.equals(typ))
            return null;


        VElement vLinks=getLinks(null,null,null);
        if(vLinks==null)
            return null;

        final String indexString=StringUtil.formatInteger(combinedProcessIndex);
        // loop over all links
        for(int i=vLinks.size()-1;i>=0;i--)
        {
            JDFResourceLink rl=(JDFResourceLink) vLinks.elementAt(i);
            if(rl.hasAttribute(AttributeName.COMBINEDPROCESSINDEX) &&
                    !rl.includesMatchingAttribute(AttributeName.COMBINEDPROCESSINDEX,indexString,AttributeInfo.EnumAttributeType.IntegerList)) 
            {
                vLinks.remove(i);
            }
        }
        return vLinks;
    }
    /**
     * get the links that are selected by a given CombinedProcessIndex
     * all links with no CombinedProcessIndex are included in the list
     * 
     * @param type the process type for which to get the links
     * @param nType the nTh occurence of the Type field, -1 if all valid positions are ok
     * 
     * @default getLinksForType(type, -1)
     */
    public VElement getLinksForType(EnumType type, int nType)
    {
        EnumType typ = getEnumType();
        if(typ==null)
            return null;
        // not combined, simpy get links from entire node 
        if(typ.equals(type))
        {
            return getLinks(null,null,null);
        }

        // nasty - mismatching type attribute
        if(!typ.equals(EnumType.Combined)&&!typ.equals(EnumType.ProcessGroup))
            return null;

        // no types - this is a corrupt node
        Vector vTypes=getEnumTypes();
        if(vTypes==null)
            return null;
        final int typSize=vTypes.size();

        // no links here at all
        VElement vLinks=getLinks(null,null,null);
        if(vLinks==null)
            return null;

        // loop over all links and remove non-matching entries
        for(int i=vLinks.size()-1;i>=0;i--)
        {
            JDFResourceLink rl=(JDFResourceLink) vLinks.elementAt(i);
            JDFIntegerList cpi=rl.getCombinedProcessIndex();
            if(cpi!=null) // there is a cpi, check if it matches
            {
                final int size = cpi.size();
                boolean bFound=false;
                // loop over indeces of CombinedProcessIndex
                for(int j=0;j<size;j++)
                {
                    int index=cpi.getInt(j);
                    if(index<typSize) // the index points to a vaild position in the list
                    {
                        final EnumType cpiType=(EnumType)vTypes.elementAt(index);
                        if(cpiType.equals(type))
                        {
                            if(nType<0) // flag not to check which ocurrence
                            {
                                bFound=true;
                            }
                            else
                            {
                                int nFound=-1;
                                for(int k=0;k<=index;k++) // count occurences of this process type in front of and including this
                                {
                                    final EnumType cpiTypeCount=(EnumType)vTypes.elementAt(k);
                                    if(cpiTypeCount.equals(type))
                                        nFound++;                                  
                                }
                                bFound=nFound==nType;
                                if(bFound) // we found a good cpi, break search
                                    break;
                            }
                        }
                    }
                }
                // found non matching cpi - remove link
                if(!bFound) 
                {
                    vLinks.remove(i);
                }
            }
        }

        return vLinks;
    }



    /**
     * get the enumerated type value of @Type
     * 
     * @return the enumerated type
     */
    public EnumType getEnumType()
    {
        return EnumType.getEnum(getType());
    }

    /**
     * insert a new Process into @Types at the position pos
     * 
     * @param type the process type
     * @param beforePos the position before which to add the in the list, 0 is first, ... -1 is before the last, very large is append
     */
    public void insertTypeInTypes(EnumType type, int beforePos)
    {
        VString types=getTypes();
        if(types==null)
            types=new VString();

        final int typeSize = types.size();
        if(beforePos<0)
            beforePos=typeSize+beforePos;

        if(beforePos<0)
            beforePos=0;

        if(beforePos<=typeSize) // insert somehwere within the list
        {
            VElement vResLinks=getLinks(null,new JDFAttributeMap(AttributeName.COMBINEDPROCESSINDEX,""),null);
            if(vResLinks!=null)
            {
                for(int i=0;i<vResLinks.size();i++)
                {
                    JDFResourceLink rl=(JDFResourceLink)vResLinks.elementAt(i);
                    int[] cpi=rl.getCombinedProcessIndex().getIntArray();
                    for(int j=0;j<cpi.length;j++)
                    {
                        if (cpi[j]>=beforePos)
                            cpi[j]++;
                    }
                    rl.setCombinedProcessIndex(new JDFIntegerList(cpi));
                }
            }

            types.insertElementAt(type.getName(),beforePos);
        }
        else // append at end
        {
            types.add(type.getName());
        }

        setTypes(types);
    }


    public VString getParentIds()
    {
        final VString vs = new VString();
        if (getAncestorPool() != null)
        {
            final VElement v = getAncestorPool().getPoolChildren(null);

            for (int i = 0; i < v.size(); i++)
            {
                vs.add(((JDFAncestor) v.elementAt(i)).getNodeID());
            }
        }
        return vs;
    }


    /**
     * merge a previously spawned JDF into a node that is a child of, or this root 
     * @param JDFNode toMerge the previosly spawned jdf node
     * @param String urlMerge the url of the ???
     * @param EnumCleanUpMerge policy how to clean up the spawn and merge audits after merging
     * @param EnumAmountMerge policy how to clean up the Resource amounts after merging
     * @return JDFNode the merged node in the new document
     * note that the return value used to be boolean. The boolean value is now replaced by exceptions. This corresponds to <true> always.
     *
     * @throws JDFException if toMerge has already been merged 
     * @throws JDFException if toMerge was not spawned from this
     * @throws JDFException if toMerge has no AncestorPool
     * 
     * default:  mergeJDF(toMerge, null, 
     *              JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None)
     */


    public JDFNode mergeJDF(JDFNode toMerge, String urlMerge, EnumCleanUpMerge cleanPolicy, JDFResource.EnumAmountMerge amountPolicy)
    {
        if (!toMerge.hasParent(this))
        {
            throw new JDFException("JDFNode.MergeJDF no matching parent found");
        }

        final String idm      = toMerge.getID();
        JDFNode overWriteNode  = (JDFNode) getTarget(idm, AttributeName.ID);

        if (overWriteNode == null)
        {
            throw new JDFException("JDFNode.MergeJDF no Node with ID: " + idm);
        }

        //tbd multiple ancestor handling
        final JDFAncestorPool ancestorPool = toMerge.getAncestorPool();
        if (ancestorPool==null)
        {
            throw new JDFException("JDFNode.MergeJDF no Ancestor Pool in Node: " + idm);
        }
        final int numAncestors             = ancestorPool.numChildElements(ElementName.ANCESTOR, null);

        if (numAncestors <= 0)
        {
            throw new JDFException("JDFNode.MergeJDF no Ancestors in AncestorPool found. Node: " + idm);
        }

        String spawnID              = JDFConstants.EMPTYSTRING;
        boolean bSnafu              = true;
        JDFSpawned spawnAudit       = null;
        final VString previousMergeIDs    = new VString(); // list of merges in the ancestors
        int iFound                  = 0;

        for (int whereToLook = 1; whereToLook <= numAncestors; whereToLook++)
        {
            // the last ancestor has the id!
            final String idParent = ancestorPool.getAncestor(numAncestors - whereToLook).getNodeID();
            final KElement k = getTarget(idParent, AttributeName.ID);
            if (k == null)
            {
                break;
            }

            JDFNode op = (JDFNode)k;
            final JDFAuditPool ap = op.getCreateAuditPool();

            // find all ids of previous merge operations for reverse merge cleanup
            final VElement vMergeAudit = ap.getAudits(EnumAuditType.Merged, null);
            for (int nMerged = 0; nMerged < vMergeAudit.size(); nMerged++)
            {
                final JDFMerged merged = (JDFMerged)vMergeAudit.elementAt(nMerged);
                previousMergeIDs.appendUnique(merged.getMergeID());
            }

            if (iFound != 0) // we've already found a spawned Audit, just looping for previous merges
            {
                continue;
            }

            // get appropriate spawned element
            final VElement vSpawnAudit = ap.getChildrenByTagName(ElementName.SPAWNED, null, new JDFAttributeMap(AttributeName.JREF, idm), 
                    true, true, 0);
            spawnID = toMerge.getSpawnID();

            for (int isp = vSpawnAudit.size() - 1; isp >= 0; isp--)
            { // loop backwards because the latest is assumed correct
                JDFSpawned testSpawn = (JDFSpawned)vSpawnAudit.elementAt(isp);
                if (testSpawn.getNewSpawnID().equals(spawnID))
                {
                    // tbd check for matching merged...
                    spawnAudit = testSpawn;
                    final JDFMerged matchingMerged = 
                        (JDFMerged) ap.getChildWithAttribute(ElementName.MERGED, AttributeName.MERGEID, null, spawnID, 0, true);

                    if (matchingMerged != null)
                    {
                        throw new JDFException("JDFNode.MergeJDF Spawn Audit already merged, SpawnID: " + spawnID, JDFSpawn.exAlreadyMerged);
                    }
                    break;
                }
            }
            // found an audit that fits, 
            if (spawnAudit != null)
            {
                iFound = whereToLook;
            }
        }

        // if the spawn Audit is not found at the first attempt, something went badly wrong
        // we will insert a error audit later but continue limping along!
        bSnafu = iFound != 1;

        if (spawnAudit == null)
        {
            throw new JDFException("JDFNode.MergeJDF no matching Spawn Audit, SpawnID: " + spawnID);
        }

        // get parts from audit
        final VJDFAttributeMap parts = spawnAudit.getPartMapVector();

        // merge copied readOnly resources
        final VString vsRO = spawnAudit.getrRefsROCopied();
        final VString vsRW = spawnAudit.getrRefsRWCopied();

        String preSpawn = mergeCheckPrespawn(toMerge, spawnAudit, vsRO, vsRW);

        overWriteNode.mergeLocalLinks(toMerge, parts);

        overWriteNode.cleanROResources(toMerge, previousMergeIDs, vsRO, spawnID);
        overWriteNode.mergeRWResources(toMerge, previousMergeIDs, vsRW, spawnID, amountPolicy);

        overWriteNode.mergeLocalNodes(toMerge, previousMergeIDs, spawnID, amountPolicy, parts);
        JDFMerged mergeAudit=overWriteNode.mergeMainPools(toMerge, parts, vsRW, spawnID, preSpawn, urlMerge, bSnafu);
        // an empty spawnID should never happen here, but check just in case
        // since an empty spawnID in CleanUpMerge removes all Spawned audits
        if (spawnID != null)
        {
            JDFNode overWriteParent=mergeAudit.getParentJDF(); // since all links get screwed up, let's relink here
            overWriteParent.cleanUpMerge(cleanPolicy, spawnID, false);
        }

        // now burn it in!
        overWriteNode=(JDFNode)overWriteNode.replaceElement(toMerge);
        overWriteNode.eraseEmptyNodes(true);

        return overWriteNode;
    }


    ///////////////////////////////////////////////////////////////////

    private String mergeCheckPrespawn(JDFNode toMerge, JDFSpawned spawnAudit, final VString vsRO, final VString vsRW)
    {
        String preSpawn = spawnAudit.getSpawnID();
        // check all recursive previous spawns
        while (preSpawn!=null && !preSpawn.equals(JDFConstants.EMPTYSTRING))
        {
            final JDFMerged preMerge = (JDFMerged)getTarget(preSpawn, AttributeName.MERGEID);
            if (preMerge != null)
            {
                final JDFSpawned preSpawnAudit = (JDFSpawned)getTarget(preSpawn, AttributeName.NEWSPAWNID);
                vsRO.appendUnique(preSpawnAudit.getrRefsROCopied());
                vsRW.appendUnique(preSpawnAudit.getrRefsRWCopied());
                preSpawn = preSpawnAudit.getSpawnID();
            }
            else
            {
                toMerge.setSpawnID(preSpawn);
                break;                
            }
        }
        return preSpawn;
    }

    /////////////////////////////////////////////////////////////////////////////////////

    private void mergeLocalNodes(JDFNode toMerge, final VString previousMergeIDs, String spawnID, JDFResource.EnumAmountMerge amountPolicy, final VJDFAttributeMap parts)
    {
        // merge local (internal) partitioned resources
        final Vector vn = getvJDFNode(null, null, false);
        for (int nod = 0; nod < vn.size(); nod++)
        {
            final JDFNode overwriteLocalNode = (JDFNode)vn.elementAt(nod);

            final JDFNode toMergeLocalNode = (JDFNode)toMerge.getTarget(overwriteLocalNode.getID(), AttributeName.ID);
            final JDFResourcePool poolOverWrite = overwriteLocalNode.getResourcePool();
            final JDFResourcePool poolToMerge = toMergeLocalNode.getResourcePool();

            if (poolOverWrite != null)
            {
                final VElement resOverWrite = 
                    poolOverWrite.getPoolChildren(null, null, "");

                final int size = resOverWrite.size();
                for (int i = 0; i < size; i++)
                {
                    final JDFResource res1 = (JDFResource)resOverWrite.elementAt(i);
                    mergeLocalResource(previousMergeIDs, spawnID, amountPolicy, poolToMerge, res1);
                }
            }

            //retain all other elements of the original (non spawned) JDF Node if the spawn is partitioned¬
            final JDFAncestorPool ancestorPool = toMerge.getAncestorPool();
            if (ancestorPool!=null && ancestorPool.isPartitioned())
            {
                final VElement localChildren = overwriteLocalNode.getChildElementVector(null, null, null, true, 0, false);

                final int siz = localChildren.size();
                for (int i = 0; i < siz; i++)
                {
                    final KElement e = (KElement) localChildren.elementAt(i);
                    //          skip all pools
                    final String nodeName = e.getLocalName();
                    if (nodeName.endsWith("Pool"))
                    {
                        if (nodeName.equals(ElementName.RESOURCELINKPOOL))
                        {
                            continue;
                        }
                        if (nodeName.equals(ElementName.RESOURCEPOOL))
                        {
                            continue;
                        }
                        if (nodeName.equals(ElementName.AUDITPOOL))
                        {
                            overwriteLocalNode.mergeAuditPool(toMergeLocalNode);
                            continue;
                        }
                        if (nodeName.equals(ElementName.STATUSPOOL))
                        {
                            overwriteLocalNode.mergeStatusPool(toMergeLocalNode,parts);
                            continue;
                        }
                        if (nodeName.equals(ElementName.ANCESTORPOOL))
                        {
                            continue;
                        }
                    }

                    // 131204 RP also skip all sub-JDF nodes!!!
                    if(nodeName.equals(ElementName.JDF))
                    {
                        continue;
                    }
                    //050708 RP special handling for comments
                    if(nodeName==ElementName.COMMENT){
                        overwriteLocalNode.mergeComments(toMergeLocalNode);
                        continue;
                    }

                    toMergeLocalNode.removeChildren(nodeName, null, null);
                    toMergeLocalNode.moveElement(e, null);

                    // repeat in case of multiple identical elements (e.g. comments)
                    for (int j = i + 1; j < siz; j++)
                    {
                        final JDFElement localChild = (JDFElement) localChildren.elementAt(j);
                        if ( localChild != null )
                        {
                            if ( localChild.getNodeName().equals(nodeName) )
                            {
                                toMergeLocalNode.moveElement(localChild, null);
                                localChildren.set(j, null);
                            }
                        }
                    }
                }
            }
        }
    }



    private void mergeLocalResource(final VString previousMergeIDs, String spawnID, JDFResource.EnumAmountMerge amountPolicy, final JDFResourcePool poolToMerge, JDFResource res1)
    {
        final String resID = res1.getID();
        final JDFResource res2 = poolToMerge.getResourceByID(resID);

        if (res2 != null)
        {
            res2.mergeSpawnIDs(res1, previousMergeIDs);
            res1.mergePartition(res2, spawnID, amountPolicy, true); // esp. deletes res2 from toMerge node
        }
        // copy resource from orig to spawned node
        poolToMerge.copyElement(res1, null);
        res1 = poolToMerge.getResourceByID(resID);
        final VElement resLeafsSpawned = res1.getNodesWithSpawnID(spawnID);
        for (int leaf = 0; leaf < resLeafsSpawned.size(); leaf++)
        {
            final JDFResource leafRes = (JDFResource)resLeafsSpawned.elementAt(leaf);
            leafRes.removeFromSpawnIDs(spawnID);
            final VString spawnIDs = leafRes.getSpawnIDs(false);
            spawnIDs.removeAll(previousMergeIDs);
            if(spawnIDs.isEmpty())
            {
                leafRes.removeAttribute(AttributeName.SPAWNIDS);
                leafRes.removeAttribute(AttributeName.SPAWNSTATUS);
                leafRes.removeAttribute(AttributeName.LOCKED);

            }
            else
            {
                leafRes.setSpawnIDs(spawnIDs);
            }
        }
    }

/////////////////////////////////////////////////////////////////////

    private JDFMerged mergeMainPools(JDFNode toMerge, final VJDFAttributeMap parts, final VString vsRW, String spawnID, String preSpawn, String urlMerge, boolean bSnafu)
    {
        // add the merged audit - maintain sychronicity of spawned and merged
        JDFNode overWriteParent=null;
        JDFAuditPool ap=toMerge.getAuditPool();
        JDFSpawned sa=null;

        if(ap!=null)
        {
            sa=(JDFSpawned) ap.getChildWithAttribute(ElementName.SPAWNED,AttributeName.NEWSPAWNID,null,spawnID,0,true);
            overWriteParent=this;
        }

        if(sa==null)
        {
            overWriteParent=getParentJDF();
            if(overWriteParent==null)
            {
                throw new JDFException("mergeMainPools - corrupt audit structure");
            }

            ap=overWriteParent.getAuditPool();
            if(ap!=null)
                sa=(JDFSpawned) ap.getChildWithAttribute(ElementName.SPAWNED,AttributeName.NEWSPAWNID,null,spawnID,0,true);
        }

        if(sa==null)
        {
            //????
            throw new JDFException("mergeMainPools - corrupt audit structure");
        }

//      JDFNode overWriteParent=ap.getParentJDF();
        final JDFMerged mergeAudit = (JDFMerged) ap.addMerged(toMerge,vsRW, null, parts);

        if (urlMerge!=null && !urlMerge.equals(JDFConstants.EMPTYSTRING))
        {
            String url = urlMerge;
            // 300802 RP added check for preexisting file prefix
            if (url.indexOf("://") == -1)
            {
                url = "File://" + url;
            }
            mergeAudit.setURL(url);
        }
        mergeAudit.setMergeID(spawnID);

        // if something went wrong, also add a notification
        if (bSnafu)
        {
            final JDFNotification notification = ap.addNotification(EnumClass.Error, "JDFNode.MergeJDF ", parts);
            notification.setType("Error");
            notification.appendComment().appendText("The Ancestor list was incorrectly ordered for merging in the spawned JDF");
        }

        // cleanup
        toMerge.removeChild(ElementName.ANCESTORPOOL, null, 0);
        if (parts!=null && parts.size() >= 1){
            mergeStatusPool(toMerge,parts);
            // handle ancestor pools only in partitioned spawns
            final JDFAncestorPool ancPool=overWriteParent.getAncestorPool();
            if(ancPool!=null){
                toMerge.copyElement(ancPool,null);
            }
        }

        final String jid = overWriteParent.getJobID(true);
        if (toMerge.getAttribute(AttributeName.JOBID, null, JDFConstants.EMPTYSTRING).equals(jid))
        {
            toMerge.removeAttribute(AttributeName.JOBID, null);
        }
        if(preSpawn==null || preSpawn.equals(JDFConstants.EMPTYSTRING))
        {
            toMerge.removeAttribute(AttributeName.SPAWNID, null);
        }
        else
        {
            toMerge.setSpawnID(preSpawn);
        }
        return mergeAudit;
    }

    /**
     * merge the RW resources of the main JDF
     * @param toMerge the source node of the status pool to merge into this
     * @param previousMergeIDs SpawnIDs of previously merged 
     * @param vsRW Resource IDs of non-local spawned resources 
     * @param spawnID the original spawnID 
     * @param amountPolicy  
     */
    private void mergeRWResources(JDFNode toMerge, final VString previousMergeIDs, final VString vsRW, String spawnID, JDFResource.EnumAmountMerge amountPolicy)
    {
        // merge rw resources
        for (int i = 0; i < vsRW.size(); i++)
        {
            JDFResource oldRes = getLinkRoot((String)vsRW.elementAt(i));
            if(oldRes == null) // also check in tree below
            {
                oldRes = getTargetResource((String)vsRW.elementAt(i));
                if(oldRes == null) // also check in entire tree below root
                {
                    oldRes = getTargetResource((String)vsRW.elementAt(i));
                }
            }
            if(oldRes == null)
            {
                continue;
            }


            final JDFResource newRes = toMerge.getTargetResource((String)vsRW.elementAt(i));

            // merge all potential new spawnIds from this to toMerge before merging them
            oldRes.mergeSpawnIDs(newRes, previousMergeIDs);
            // do both, since some leaves may be RO
            newRes.mergeSpawnIDs(oldRes, previousMergeIDs);

            try
            {
                // merge the resource from the spawned node into the lower level resourcepool
                oldRes.mergePartition(newRes, spawnID, amountPolicy, false);
            }
            catch (Exception e)
            {
                throw new JDFException("JDFNode:mergeJDF, error in mergePartition: ID="+oldRes.getID()+" SpawnID="+spawnID);
            }

            final String oldID        = oldRes.getID();
            final JDFResource myRes   = (JDFResource)getTarget(oldID, AttributeName.ID);
            if (myRes == null)
            {
                throw new JDFException("JDFNode.mergeJDF: Merged Resource not found! Cant remove SpawnIDs");
            }
            final VElement oldResLeafsSpawned = myRes.getNodesWithSpawnID(spawnID);
            for (int leaf = 0; leaf < oldResLeafsSpawned.size(); leaf++)
            {
                final JDFResource leafRes = (JDFResource)oldResLeafsSpawned.elementAt(leaf);
                leafRes.removeFromSpawnIDs(spawnID);
                final KElement leafElem = leafRes;
                if (!leafElem.hasAttribute(AttributeName.SPAWNIDS, null, false))
                {
                    leafRes.removeAttribute(AttributeName.SPAWNSTATUS, null);
                    leafRes.removeAttribute(AttributeName.LOCKED, null);
                }
            }
        }
    }

    /**
     * merge the RO resources of the main JDF
     * @param toMerge the source node of the status pool to merge into this
     * @param previousMergeIDs SpawnIDs of previously merged 
     * @param vsRO Resource IDs of non-local spawned resources 
     * @param spawnID the original spawnID 
     */
    private void cleanROResources(JDFNode toMerge, final VString previousMergeIDs, final VString vsRO, String spawnID)
    {
        for (int i = 0; i < vsRO.size(); i++)
        {
            final JDFResource newRes = toMerge.getTargetResource((String)vsRO.elementAt(i));
            final JDFResource oldRes = (JDFResource) getTarget((String)vsRO.elementAt(i), AttributeName.ID);

            // merge all potential new spawnIds from toMerge to this
            oldRes.mergeSpawnIDs(newRes, previousMergeIDs);
            final VElement oldResLeafsSpawned = oldRes.getNodesWithSpawnID(spawnID);
            for (int leaf = 0; leaf < oldResLeafsSpawned.size(); leaf++)
            {
                final JDFResource leafRes = (JDFResource)oldResLeafsSpawned.elementAt(leaf);
                //  handle multiple spawns (reference count of spawned audits!)
                leafRes.removeFromSpawnIDs(spawnID);

                if (!leafRes.hasAttribute(AttributeName.SPAWNIDS, null, false))
                {
                    leafRes.removeAttribute(AttributeName.SPAWNSTATUS);
                }

            }
            if(!newRes.getParentJDF().getID().equals(oldRes.getParentJDF().getID()))
            {
                // this has been copied from lower down up and MUST be deleted...
                newRes.deleteNode();
            }
            else
            {
                // don't use a simple for because deleting a parent may invalidate later resources!
                VElement newResLeafsSpawned = newRes.getNodesWithSpawnID(spawnID);
                // just in case: if no SpawnID exists assume the whole thing 
                if (newResLeafsSpawned.size() == 0)
                {
                    newResLeafsSpawned.add(newRes);
                }
                while (newResLeafsSpawned.size() > 0)
                {
                    // use the last because it is potentially the root...
                    final JDFResource leafRes = (JDFResource)newResLeafsSpawned.elementAt(newResLeafsSpawned.size() - 1);
                    final boolean bZappRoot = leafRes.equals(newRes);
                    leafRes.deleteNode();
                    // we killed the root, nothing can be left...
                    if (bZappRoot)
                    {
                        break;
                    }
                    // regenerate the list
                    newResLeafsSpawned = newRes.getNodesWithSpawnID(spawnID);
                }
            }
        }
    }

    private void mergeLocalLinks(JDFNode toMerge, final VJDFAttributeMap parts)
    {
        int numParts = parts==null ? 0 : parts.size();
        final Vector vn = getvJDFNode(null, null, false);
        int nod = 0;
        // merge local (internal) partitioned resource links
        for (nod = 0; nod < vn.size(); nod++)
        {
            final JDFNode overwriteLocalNode  = (JDFNode)vn.elementAt(nod);
            final JDFNode toMergeLocalNode    = toMerge.getChildJDFNode(overwriteLocalNode.getID(), false);
            overwriteLocalNode.mergeResourceLinkPool(toMergeLocalNode, parts);

            final EnumVersion version = toMergeLocalNode.getVersion(true);
            if ((version!=null)&&(version.getValue() >= EnumVersion.Version_1_3.getValue()))
            {
                final JDFNode.EnumNodeStatus stat=toMergeLocalNode.getStatus();
                if (stat!=null && !stat.equals(JDFElement.EnumNodeStatus.Part) &&
                        !stat.equals(JDFElement.EnumNodeStatus.Pool) &&
                        numParts > 0)
                {
                    toMergeLocalNode.setPartStatus(parts,stat);
                }
            }
        }
    }

    //////////////////////////////////////////////////////////////////////

    private void mergeComments(JDFNode toMerge){
        VElement v=getChildElementVector(ElementName.COMMENT,null,null,false,0,false);
        VElement vToMerge=toMerge.getChildElementVector(ElementName.COMMENT,null,null,false,0,false);
        final int siz=vToMerge.size(); // size prior to appending
        vToMerge.appendUnique(v);
        for(int i=siz;i<vToMerge.size();i++){
            toMerge.moveElement((KElement)vToMerge.elementAt(i),null);
        }
    }

    //    ////////////////////////////////////////////////////////////////////

    public boolean hasParent(JDFNode p)
    {
        final VString vpa = p.getAncestorIDs();
        final VString vParents = getParentIds();
        vParents.add(getID());

        if (vpa.size() == 0)
        {
            return false;
        }
        final String id = (String) vpa.elementAt(0);
        if (id.equals(JDFConstants.EMPTYSTRING))
        {
            throw new JDFException("JDFNode.HasParent: no id???");
        }
        for (int i = 0; i < vParents.size(); i++)
        {
            if (id.equals(vParents.elementAt(i)))
            {
                return true;
            }
        }
        return false;
    }

    //    ////////////////////////////////////////////////////////////////////

    public int getMinID()
    {
        final VElement v = getChildrenByTagName(null, null,null, false, true, 0);
        v.add(this);

        int iMax = 0;
        final VString vIDNames = new VString("ID SpawnID MergeID NewSpawnID",null);

        for (int i = 0; i < v.size(); i++)
        {
            final JDFElement jdfElem = (JDFElement)v.elementAt(i);

            for (int j = 0; j < vIDNames.size(); j++)
            {   
                // 4 = size of the atr vector
                // get the rightmost last 4 numerical characters as seed for UniqueID()

                String strID = jdfElem.getAttribute((String)vIDNames.elementAt(j), 
                        null, JDFConstants.EMPTYSTRING);
                if (strID.length() > 0)
                {
                    if (strID.length() > 5)
                    {
                        strID = strID.substring(strID.length()-5);  // only use the last 5 chars
                    }

                    final int pos = StringUtil.find_last_not_of(strID, "0123456789");

                    if (pos == -1)
                    {
                        continue;
                    }

                    strID = strID.substring(pos + 1);
                    strID = strID.trim();

                    if (strID.equals(JDFConstants.EMPTYSTRING))
                    {
                        continue;
                    }

                    int iPos = 0;

                    while (strID.charAt(iPos) == '0')
                    {
                        iPos++;
                    }

                    if (iPos > 0)
                    {
                        strID = strID.substring(strID.length() - pos, strID.length()); //.rightStr(-iPos);
                    }

                    if (strID.equals(JDFConstants.EMPTYSTRING))
                    {
                        continue;
                    }

                    //strID.strzapp(L"0",true,false); // otherwise int returns the octal value!

                    int iS = new Integer(strID).intValue();
                    if (iS > 10000) // not in the simple ordering
                    {
                        iS = iS % 10000;
                    }

                    iMax = (iS > iMax) ? iS : iMax;
                }
            }
        }

        uniqueID(iMax);

        return iMax;
    }

    //    ////////////////////////////////////////////////////////////////////
    public int getMaxJobPartId(String idPrefix)
    {
        final Vector v = getvJDFNode(null, null, false);
        final int prefixSize = idPrefix.length();
        int iMax = -1;
        final int size = v.size();
        for (int i = 0; i < size; i++)
        {
            final JDFNode e = (JDFNode) v.elementAt(i);
            String s = e.getJobPartID(false);
            if (s.equals(JDFConstants.EMPTYSTRING) || 
                    s.substring(0, prefixSize).equals(idPrefix.substring(0, prefixSize)))
            {
                continue;
            }
            s = s.substring(prefixSize).trim();

            int pos = 0;
            final int len = s.length();

            while ((pos < len) && (s.charAt(pos) == '0'))
            {
                pos++;
            }
            // 300402 RP added
            if(pos > 0)
            {
                s = s.substring(s.length() - pos, s.length()); //.rightStr(-pos);
            }
            if (s.equals(JDFConstants.EMPTYSTRING))
            {
                continue;
            }
            iMax = (new Integer(s).intValue() > iMax) ? new Integer(s).intValue() : iMax;
        }
        return iMax;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////   

    public JDFNode addJDFNode(String typ)
    {
        final EnumType t = EnumType.getEnum(getType());

        if (t==null || !t.equals(EnumType.Product) && !t.equals(EnumType.ProcessGroup))
        {
            throw new 
            JDFException("JDFNode.addJDFNode adding ProcessGroup to invalid node type: Type = " + getType());
        }
        final JDFNode p = (JDFNode)appendElement(ElementName.JDF, null);
        if (typ!=null && !typ.equals(JDFConstants.EMPTYSTRING))
        {
            p.setType(typ, false);
        }
        return p;
    }

    //    ////////////////////////////////////////////////////////////////////

    public JDFNode addJDFNode(EnumType typ)
    {
        final JDFNode p = addJDFNode((String)null);
        p.setType(typ.getName(), false);
        return p;
    }

    //    ////////////////////////////////////////////////////////////////////

    /**
     * @deprecated use  addJDFNode(EnumType typ) or addJDFNode(String typ)
     */
    public JDFNode addProcess(String prodName)
    {
        final JDFNode p = addJDFNode(prodName);
        return p;
    }

    //    ////////////////////////////////////////////////////////////////////

    /**
     * @param tasks
     * @return
     * 
     * default: addProcessGroup(null)
     */
    public JDFNode addProcessGroup(VString tasks)
    {
        final JDFNode p = addJDFNode(EnumType.ProcessGroup);
        p.setType(EnumType.ProcessGroup.getName(), false);
        if (tasks != null && !tasks.equals(VString.emptyVector))
            p.setTypes(tasks);
        return p;
    }

    //    /////////////////////////////////////////////////////////////

    public JDFNode addCombined(VString tasks)
    {
        final JDFNode cNode = addJDFNode(EnumType.Combined);
        cNode.setTypes(tasks);
        return cNode;
    }

    //    /////////////////////////////////////////////////////////////
    /**
     * add a product node to this
     * @throws JDFException ith this is not a Product itself
     */
    public JDFNode addProduct()
    {
        if (!EnumType.getEnum(getType()).equals(EnumType.Product))
        {
            throw new JDFException("JDFNode.AddProduct adding Product to invalid node type: Type = " + getType());
        }
        final JDFNode p = addJDFNode(EnumType.Product);
        p.setType(EnumType.Product.getName(), false);
        return p;
    }
    //    /////////////////////////////////////////////////////////////
    /**
     * remove all completed nodes
     * @deprecated
     */
    public boolean removeCompleted()
    {
        final Vector v = getCompleted();
        for (int i = 0; i < v.size(); i++)
        {
            final JDFNode pr = (JDFNode)v.elementAt(i);
            pr.removeNode(false);
        }
        return true;
    }

    //    ////////////////////////////////////////////////////////////////////
    public Vector getCompleted()
    {
        final Vector v = getvJDFNode(null,null, false);
        final Vector v2 = new Vector();
        final int size = v.size();
        for (int i = 0; i < size; i++)
        {
            final JDFNode pr = (JDFNode) v.elementAt(i);
            if (pr == null)
            {
                break;
            }
            if (pr.getStatus().equals(EnumNodeStatus.Completed))
            {
                v2.addElement(pr);
            }
        }
        return v2;
    }

    //    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns a resource with id anywhere in the tree below this node
     * similar to getTarget(id) but looks only in the resource pool's direct children
     * @param id the id of the resource
     * 
     * @return the resource, if available
     */
    public JDFResource getTargetResource(String id)
    {

        XMLDocUserData ud=getXMLDocUserData();
        if(ud!=null)
        {
            KElement e=ud.getTarget(id);
            if(e instanceof JDFResource)
                return (JDFResource)e;
        }

        final JDFResourcePool p = getResourcePool();

        if (p != null)
        {
            final JDFResource r = p.getResourceByID(id);
            if (r != null)
            {
                return r;
            }
        }

        final Vector v = getvJDFNode(null, null, true);
        for (int i = 0; i < v.size(); i++)
        {
            final JDFResource r = ((JDFNode)v.elementAt(i)).getTargetResource(id);
            if (r != null)
            {
                return r;
            }
        }
        return null;
    }

    /**
     * searches for the first attribute occurence in the ancestor elements subelements
     *
     * @param String attrib: the attribute name
     * @param String nameSpaceURI: the XML-namespace
     * @param String def: the default if it does not exist
     * @since 180502 
     * @return String: value of attribute found, empty string if not available
     */
    public String getAncestorElementAttribute(
            String element,
            String attrib,
            String nameSpaceURI,
            String def)
    {
        JDFNode n = this;
        while (n != null)
        {
            final KElement e = getElement(element, nameSpaceURI, 0);

            if ((e != null) && (e.hasAttribute(attrib, nameSpaceURI, false)))
            {
                return e.getAttribute(attrib, nameSpaceURI, "");
            }

            n = getParentJDF();
        }

        // not in the inherited nodes, check the root node's AncestorPool
        return getJDFRoot().getAncestorPool().getAncestorElementAttribute(
                element,
                attrib,
                nameSpaceURI,
                def);
    }

    /**
     * true if a non default attribute occurence in the parent nodes 
     * and then the ancestor elements subelements of the root ancestorpool exists
     *
     * @param String attrib: the attribute name
     * @param String nameSpaceURI: the XML-namespace
     * @param String def: the default if it does not exist
     * @since 180502 
     * @return bool: true if the attribute exists
     */
    public boolean hasAncestorElementAttribute(String element, String attrib, String nameSpaceURI)
    {
        return 
        getAncestorElementAttribute(element, attrib, nameSpaceURI, JDFConstants.QUOTE).equals(JDFConstants.QUOTE);
    }


    /**
     * Get vector of linked input resource intents
     * @return VElement vector of all input intent resources that are linked as inputs to this node
     */
    public VElement getIntents()
    {
        VElement velem=null;

        final JDFResourceLinkPool rlp = getResourceLinkPool();

        if (rlp != null)
        {
            final JDFAttributeMap mALink = new JDFAttributeMap(AttributeName.USAGE, "Input");   // map of requesetd link attributes         
            final JDFAttributeMap mARes = new JDFAttributeMap(AttributeName.CLASS,"Intent");    // map of requesetd resource attributes

            velem = rlp.getLinkedResources(null, mALink, mARes, false);
        }
        return velem; // grab em, don't worry about the resname
    }


    /**
     * get a vector of ResourceLink elements that exist but are unknown by this element
     *
     * @param VString vInNameSpace list of namespaces where unknown Links are searched for. 
     *                             if null, all namespaces are searched
     * @param nMax maximum size of the returned vector
     * @return  VElement vector of unknown elements
     * @since 060730 returntype changed to VElement - but since the routine was utterly broken, we should be ok
     */
    public VElement getUnknownLinkVector(VString vInNameSpace, int nMax)
    {
        VElement vUnknown = null;

        final VString names   = linkNames();
        final VElement ve    = getLinks(null,null,null);
        final boolean bAllNS = vInNameSpace==null || vInNameSpace.isEmpty();

        if(vInNameSpace!=null)
        {
            for(int j = 0; j < vInNameSpace.size(); j++) 
            {
                // tokenize needs a blank¬
                if(vInNameSpace.elementAt(j).equals(JDFConstants.BLANK))
                {
                    vInNameSpace.setElementAt(JDFConstants.EMPTYSTRING, j);
                }
            }
        }

        final int veSize = ve==null ? 0 : ve.size();
        for(int i = 0; i < veSize; i++)
        {
            final JDFResourceLink rl = (JDFResourceLink) ve.elementAt(i);
            final String nodename    = rl.getNodeName().substring(0, rl.getNodeName().length() -4);

            if(bAllNS || (vInNameSpace.contains(xmlnsPrefix(nodename))))
            {
                if(!names.contains(nodename))
                {
                    if(vUnknown==null)
                        vUnknown=new VElement();

                    vUnknown.add(rl);
                    if(vUnknown.size()>=nMax)
                    {
                        break;
                    }
                }
            }
        }
        return vUnknown;
    }

    /**
     * clean up the spawn and merge audits in this Node
     * @param EnumCleanUpMerge policy how to clean up the spawn and merge audits after merging
     * 
     * @param String spawnID the SpawnID of the spawn and MergeID of the merge to clean up
     *        if not specified all spawns will be cleaned up
     * 
     * @param boolean bRecurse if true also recurse into all child JDF nodes; default=false
     * 
     * @default CleanUpMerge(EnumCleanUpMerge cleanPolicy, JDFConstants.EMPTYSTRING, false);
     * */

    private void cleanUpMerge(EnumCleanUpMerge cleanPolicy, String spawnID, boolean bRecurse)
    {
        if(!cleanPolicy.equals(EnumCleanUpMerge.None))
        {
            if(bRecurse)
            {
                final Vector v = getvJDFNode(null, null, false);
                for(int i = v.size(); i >= 0; i--)
                {
                    ((JDFNode)v.elementAt(i)).cleanUpMerge(cleanPolicy, spawnID, false);
                }

            }
            else
            {
                JDFAuditPool auditPool = getAuditPool();
                if (auditPool != null)
                    auditPool.cleanUpMerge(cleanPolicy, spawnID);
            }
        }
    }

    /**
     * merge the audit pools
     * @param JDFNode& toMerge the source node of the audit pool to merge into this
     */
    private void mergeAuditPool(JDFNode toMerge)
    {
        //      merge audit pool
        final JDFAuditPool overWriteAuditPool = getAuditPool();
        final JDFAuditPool toMergeAuditPool = toMerge.getAuditPool();

        // the node that is overwritten has an audit pool that must be merged
        if (overWriteAuditPool != null)
        {
            // the overwriting node node is empty, just copy the previous pool
            if (toMergeAuditPool == null)
            {
                toMerge.copyElement(overWriteAuditPool, null);
            }
            else
            {
                // must merge the old node into the overwriting node
                overWriteAuditPool.appendUnique(toMergeAuditPool);
                toMergeAuditPool.replaceElement(overWriteAuditPool);
            }
        }
    }

    /**
     * merge the status pools
     * @param JDFNode& toMerge the source node of the status pool to merge into this
     * @param mAttribute parts the partitions to merge
     */
    private void mergeStatusPool(JDFNode toMerge, VJDFAttributeMap parts)
    {
        if (toMerge.hasChildElement(ElementName.STATUSPOOL, null) || 
                hasChildElement(ElementName.STATUSPOOL, null))
        {
            final JDFStatusPool overWriteStatusPool = getCreateStatusPool();
            if (!getStatus().equals(JDFElement.EnumNodeStatus.Pool))
            {
                overWriteStatusPool.setStatus(getStatus());
                setStatus(JDFElement.EnumNodeStatus.Pool);
            }

            final JDFStatusPool toMergeStatusPool = toMerge.getStatusPool();
            if (toMerge.getStatus() == JDFElement.EnumNodeStatus.Pool)
            {
                for (int i = 0; i < parts.size(); i++)
                {
                    int j;
                    // clean up the pool to overwrite
                    final VElement vpso = overWriteStatusPool.getMatchingPartStatusVector(parts.elementAt(i));
                    for (j=0;j<vpso.size();j++)
                    {
                        // remove all matching partstatus elements in case they were expanded in the spawned node
                        ((JDFPartStatus) vpso.elementAt(j)).deleteNode(); 
                    }

                    // extract data from spawned node
                    final VElement vps=toMergeStatusPool.getMatchingPartStatusVector(parts.elementAt(i));
                    for (j=0; j<vps.size(); j++)
                    {
                        final JDFPartStatus ps = (JDFPartStatus) vps.elementAt(j);
                        final JDFAttributeMap m = ps.getPartMap();
                        overWriteStatusPool.setStatus(m, ps.getStatus(), ps.getStatusDetails());
                    }

                    //100305 RP the following lines cause problems with nested spawn and therefore are commented out
//                  final JDFPartStatus ps=overWriteStatusPool.getPartStatus(parts.elementAt(i));
                    // just in case someone updated detailed partstatus elements
//                  if (ps != null && ps.getStatus() == EnumNodeStatus.Spawned)
//                  ps.deleteNode();
                }
                toMergeStatusPool.replaceElement(overWriteStatusPool);
            }
            else
            {
                // this part of the program will probably never be executed, but...
                for (int i = 0; i < parts.size(); i++)
                    overWriteStatusPool.setStatus(parts.elementAt(i), 
                            toMerge.getStatus(), null);
                if (toMergeStatusPool != null)
                    toMergeStatusPool.deleteNode();
                toMerge.setStatus(JDFElement.EnumNodeStatus.Pool);
                toMerge.moveElement(overWriteStatusPool, null);
            }
        }

    }

    /**
     * merge the resource link pools
     * @param oMerge the source node of the status pool to merge into this
     * @param parts the partitions to merge
     */
    private void mergeResourceLinkPool(JDFNode toMerge, VJDFAttributeMap parts)
    {
        final JDFResourceLinkPool resourceLinkPool = toMerge.getResourceLinkPool();
        if(resourceLinkPool!=null)
        {
            final VElement links=resourceLinkPool.getPoolChildren(null, null, null);
            for(int i=0;i<links.size();i++){
                JDFResourceLink rl=(JDFResourceLink) links.elementAt(i);
                rl.expandTarget(false);
            }
        }

        if (parts!=null && !parts.isEmpty())
        {
            final JDFResourceLinkPool overWriteRLP = getResourceLinkPool();
            final JDFResourceLinkPool toMergeRLP = resourceLinkPool;
            final VElement overWriteLinks = 
                overWriteRLP.getPoolChildren(null, null, null);
            final VElement toMergeLinks = 
                toMergeRLP.getPoolChildren(null, null, null);

            for (int rl = 0; rl < toMergeLinks.size(); rl++)
            {
                JDFResourceLink overWriteLink = null;
                final JDFResourceLink toMergeLink = (JDFResourceLink)toMergeLinks.elementAt(rl);
                final String rRef = toMergeLink.getAttribute(AttributeName.RREF);
                for (int k = 0; k < overWriteLinks.size(); k++)
                {
                    if (((JDFResourceLink)overWriteLinks.elementAt(k)).getAttribute(AttributeName.RREF).equals(rRef))
                    {
                        overWriteLink = (JDFResourceLink)overWriteLinks.elementAt(k);
                        overWriteLinks.remove(overWriteLinks.elementAt(k));
                        break;
                    }
                }

                final JDFAmountPool jdfAmountPool = toMergeLink.getAmountPool();

                if (overWriteLink != null)
                {
                    if (toMergeLink.hasChildElement(ElementName.PART, null))
                    {
                        for (int i = 0; i < parts.size(); i++)
                        {
                            final boolean hasAP     = 
                                overWriteLink.hasChildElement(ElementName.AMOUNTPOOL, null);
                            JDFAttributeMap mpaMap  = null;
                            VElement vPartAmounts = null;
                            if(jdfAmountPool!=null)
                                vPartAmounts=jdfAmountPool.getMatchingPartAmountVector(parts.elementAt(i));

                            if (vPartAmounts == null)
                            {
                                mpaMap = toMergeLink.getAttributeMap();
                                // remove generic link stuff
                                mpaMap.remove(AttributeName.COMBINEDPROCESSINDEX);
                                mpaMap.remove(AttributeName.COMBINEDPROCESSTYPE);
                                //tbd opa.RemoveAttribute(atr_PipePartIDKeys);
                                mpaMap.remove(AttributeName.PIPEPROTOCOL);
                                mpaMap.remove(AttributeName.PROCESSUSAGE);
                                mpaMap.remove(AttributeName.RREF);
                                mpaMap.remove(AttributeName.RSUBREF);
                                mpaMap.remove(AttributeName.USAGE);
                                if(!hasAP){
                                    JDFAttributeMap opaMap=overWriteLink.getAttributeMap();
                                    if(opaMap.subMap(mpaMap))
                                    {
                                        mpaMap.clear();
                                    }
                                }
                                // only add something if partmap contains real information
                                if(hasAP || (!mpaMap.isEmpty()&&!mpaMap.equals(parts.elementAt(i))))
                                {
                                    JDFPartAmount opa=overWriteLink.getCreateAmountPool().getCreatePartAmount(parts.elementAt(i));
                                    opa.setAttributes(mpaMap);
                                    overWriteLink.removeAttributes(mpaMap.getKeys());
                                }                           
                            }
                            else
                            {
                                // loop over all fitting part amounts and blast them in
                                for(int j=0;j<vPartAmounts.size();j++)
                                {
                                    JDFPartAmount mpa= (JDFPartAmount)vPartAmounts.elementAt(j);                              
                                    JDFAttributeMap amountMap=mpa.getAttributeMap();
                                    JDFAttributeMap partMap=mpa.getPartMap();
                                    JDFPartAmount opa=overWriteLink.getCreateAmountPool().getCreatePartAmount(partMap);
                                    opa.setAttributes(amountMap);
                                    overWriteLink.removeAttributes(amountMap.getKeys());                                
                                }
                            }
                            if(mpaMap!=null)
                            {
                                // dont add ap if mpa link only has copies of the original link
                                if(!hasAP)
                                {
                                    final JDFAttributeMap opaMap = overWriteLink.getAttributeMap();
                                    if(opaMap.subMap(mpaMap))   
                                    {
                                        mpaMap.clear();
                                    }
                                }

                                // only add something if partmap contains real information
                                if((!mpaMap.isEmpty() && !mpaMap.equals(parts.elementAt(i))))
                                {
                                    final JDFPartAmount opa = 
                                        overWriteLink.getCreateAmountPool().getCreatePartAmount(parts.elementAt(i));
                                    opa.setAttributes(mpaMap);
                                }
                            }
                            // nothing has changed --> leave as is
                        }
                    }
                    else
                    {
                        // blast the spawned link into the overWritePool completely
                        overWriteLink.replaceElement(toMergeLink);
                    }
                }
            }
            toMergeRLP.deleteNode();
            toMerge.copyElement(overWriteRLP, null);
        }
    }

    public void setCategory(String value)
    {
        setAttribute(AttributeName.CATEGORY, value);
    }

    public String getCategory()
    {
        return getAttribute(AttributeName.CATEGORY);
    }

    /**
     * @deprecated - use getCategory() instead
     */
    public String getCategory(boolean bInherit)
    {
        if(bInherit)
        {
            return getAncestorAttribute(AttributeName.CATEGORY, null, JDFConstants.EMPTYSTRING);
        }
        return getAttribute(AttributeName.CATEGORY);
    }

    public void setICSVersions(VString value)
    {
        setAttribute(AttributeName.ICSVERSIONS, value, null);
    }

    /**
     * get NMTOKENS attribute ICSVersions
     * @param boolean bInherit - if true recurse through ancestors when searching
     * @return VString - attribute value
     * 
     * default - getICSVersions(flase)
     */
    public VString getICSVersions(boolean bInherit) 
    {
        if(bInherit)
        {
            return new VString(getAncestorAttribute(AttributeName.ICSVERSIONS, 
                    null, JDFConstants.EMPTYSTRING),null);
        }
        return new VString(getAttribute(AttributeName.ICSVERSIONS),null);
    }


    public void setID(String value)
    {
        setAttribute(AttributeName.ID, value);
    }

    public String getID()
    {
        return this.getAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * set MaxVersion to enumVer
     * @param enumVer the EnumVersion to set
     */
    public void setMaxVersion(EnumVersion enumVer)
    {
        setAttribute(AttributeName.MAXVERSION, enumVer.getName(), null);
    }

    /**
     * 
     * @param value the string version to set MaxVersion to
     * @deprecated use setMaxVersion(EnumVersion)
     */
    public void setMaxVersion(String value)
    {
        setAttribute(AttributeName.MAXVERSION, value);
    }

    /**
     * get attribute MaxVersion
     * @param boolean bInherit - if true recurse through ancestors when searching
     * @return EnumVersion - attribute value
     * 
     * default - getMaxVersion(flase)
     */
    public EnumVersion getMaxVersion(boolean bInherit) 
    {
        String version;
        if (bInherit)
        {
            version = getAncestorAttribute(AttributeName.MAXVERSION, 
                    null, 
                    JDFConstants.EMPTYSTRING);
        }
        else
        {
            version = getAttribute(AttributeName.MAXVERSION, 
                    null, 
                    JDFConstants.EMPTYSTRING);
        }

        return EnumVersion.getEnum(version);
    }

    public void setNamedFeatures(VString value)
    {
        final StringBuffer strbuff = new StringBuffer(100);
        for(int i = 0; i < value.size(); i++)
        {
            strbuff.append(value.elementAt(i));
        }
        setAttribute(AttributeName.NAMEDFEATURES, strbuff.toString());
    }


    public VString getNamedFeatures() 
    {
        return new VString(getAttribute(AttributeName.NAMEDFEATURES, null, JDFConstants.EMPTYSTRING),null);
    }

    /**
     * @deprecated - use getNamedFeatures() instead
     */
    public VString GetNamedFeatures(boolean bInherit) 
    {
        final VString v = new VString();
        Vector v2;

        if(bInherit)
        {
            v2 = StringUtil.tokenize(getAncestorAttribute(AttributeName.NAMEDFEATURES, 
                    null, JDFConstants.EMPTYSTRING), JDFConstants.BLANK, false);
            v.addAll(v2);
            return v;
        }
        v2 = StringUtil.tokenize(getAttribute(AttributeName.NAMEDFEATURES), JDFConstants.BLANK, false);
        v.addAll(v2);
        return v;
    }

    public void setRelatedJobID(String value)
    {
        setAttribute(AttributeName.RELATEDJOBID, value);
    }


    public String getRelatedJobID(boolean bInherit)
    {
        if(bInherit)
        {
            return getAncestorAttribute(AttributeName.RELATEDJOBID, null, JDFConstants.EMPTYSTRING);
        }
        return getAttribute(AttributeName.RELATEDJOBID);
    }

    public void setRelatedJobPartID(String value)
    {
        setAttribute(AttributeName.RELATEDJOBPARTID,value);
    }

    public String getRelatedJobPartID(boolean bInherit) 
    {
        if(bInherit)
        {
            return getAncestorAttribute(AttributeName.RELATEDJOBPARTID, 
                    null, JDFConstants.EMPTYSTRING);
        }
        return getAttribute(AttributeName.RELATEDJOBPARTID);
    }

    public void setStatusDetails(String value)
    {
        setAttribute(AttributeName.STATUSDETAILS, value);
    }

    public String getStatusDetails()
    {
        return getAttribute(AttributeName.STATUSDETAILS);
    }

    /**
     * @deprecated - use getStatusDetails() instead
     */
    public String getStatusDetails(boolean bInherit)
    {
        if(bInherit)
        {
            return getAncestorAttribute(AttributeName.STATUSDETAILS, 
                    null, JDFConstants.EMPTYSTRING);
        }
        return getAttribute(AttributeName.STATUSDETAILS);
    }

    public void setTemplate(boolean value)
    {
        setAttribute(AttributeName.TEMPLATE, value, null);
    }


    public boolean getTemplate()
    {
        return getBoolAttribute(AttributeName.TEMPLATE, null, false);
    }


    public void setTemplateID(String value)
    {
        setAttribute(AttributeName.TEMPLATEID, value);
    }

    public String getTemplateID(boolean bInherit)
    {
        if(bInherit)
        {
            return getAncestorAttribute(AttributeName.TEMPLATEID, null, JDFConstants.EMPTYSTRING);
        }
        return getAttribute(AttributeName.TEMPLATEID);
    }

    public void setTemplateVersion(String value)
    {
        setAttribute(AttributeName.TEMPLATEVERSION, value);
    }

    public String getTemplateVersion(boolean bInherit)
    {
        if(bInherit)
        {
            return getAncestorAttribute(AttributeName.TEMPLATEVERSION, 
                    null, JDFConstants.EMPTYSTRING);
        }
        return getAttribute(AttributeName.TEMPLATEVERSION);
    }


    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFDuration getNodeInfoCleanupDuration()
    {
        return getInheritedNodeInfo(null).getCleanupDuration();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFMISDetails.EnumCostType getNodeInfoCostType()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        final JDFMISDetails details = inheritedNodeInfo.getMISDetails();
        if(details==null)
            return null;
        return details.getCostType();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFNodeInfo.EnumDueLevel getNodeInfoDueLevel()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getDueLevel();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFDate getNodeInfoEnd()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getEnd();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFDate getNodeInfoFirstEnd()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getFirstEnd();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFDate getNodeInfoFirstStart()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getFirstStart();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFXYPair getNodeInfoIPPVersion()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getIPPVersion();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public int getNodeInfoJobPriority()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return 0;
        return inheritedNodeInfo.getJobPriority();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFDate getNodeInfoLastEnd()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getLastEnd();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFDate getNodeInfoLastStart()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getLastStart();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public String getNodeInfoNaturalLang()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return JDFConstants.EMPTYSTRING;
        return inheritedNodeInfo.getNaturalLang();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public String getNodeInfoRoute()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return JDFConstants.EMPTYSTRING;
        return inheritedNodeInfo.getRoute();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFDuration getNodeInfoSetupDuration()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getSetupDuration();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFDate getNodeInfoStart()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getStart();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public String getNodeInfoTargetRoute(){
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return JDFConstants.EMPTYSTRING;
        return inheritedNodeInfo.getTargetRoute();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFDuration getNodeInfoTotalDuration()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getTotalDuration();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFMISDetails.EnumWorkType getNodeInfoWorkType()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        final JDFMISDetails details = inheritedNodeInfo.getMISDetails();
        if(details==null)
            return null;
        return details.getWorkType();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public String getNodeInfoWorkTypeDetails()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        final JDFMISDetails details = inheritedNodeInfo.getMISDetails();
        if(details==null)
            return null;
        return details.getWorkTypeDetails();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFBusinessInfo getNodeInfoBusinessInfo()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getBusinessInfo();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFEmployee getNodeInfoEmployee()
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getEmployee();
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFJMF getNodeInfoJMF(int iSkip)
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getJMF(iSkip);
    }

    /**
     *@deprecated 06ß221 use getInheritedNodeInfo(String attName)
     * @return
     */
    public JDFNotificationFilter getNodeInfoNotificationFilter(int iSkip)
    {
        final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
        if(inheritedNodeInfo==null)
            return null;
        return inheritedNodeInfo.getNotificationFilter(iSkip);
    }

    /**
     * get first CustomerInfo element from child list or child list of any ancestor
     * 
     * @param xPath the the xPath to an element or attribute that must exist in the queried CustomerInfo
     * note that attributes must be marked with an "@", 
     * if xPath=null, simply return the next in line
     * 
     * @return  CustomerInfo The matching CustomerInfo element
     */
    public JDFCustomerInfo getInheritedCustomerInfo(String xPath)
    {
        return (JDFCustomerInfo)getNiCi(ElementName.CUSTOMERINFO, true,xPath);
    }

    /**
     * get first CustomerInfo element from child list or child list of any ancestor
     * 
     * @deprecated 06ß221 use getInheritedCustomerInfo(String xPath)
     * @return  CustomerInfo The matching CustomerInfo element
     */
    public JDFCustomerInfo getInheritedCustomerInfo()
    {
        return getInheritedCustomerInfo(null);
    }

    /**
     *@deprecated 06ß221 use getInheritedCustomerInfo(String attName)
     * @return
     */
    public String getCustomerInfoBillingCode() 
    {
        final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
        if(inheritedCustomerInfo==null)
            return JDFConstants.EMPTYSTRING;
        return inheritedCustomerInfo.getBillingCode();
    }

    /**
     *@deprecated 06ß221 use getInheritedCustomerInfo(String attName)
     * @return
     */
    public String getCustomerInfoCustomerID() 
    {
        final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
        if(inheritedCustomerInfo==null)
            return JDFConstants.EMPTYSTRING;
        return inheritedCustomerInfo.getCustomerID();
    }
    /**
     *@deprecated 06ß221 use getInheritedCustomerInfo(String attName)
     * @return
     */   
    public String getCustomerInfoCustomerJobName() 
    {
        final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
        if(inheritedCustomerInfo==null)
            return JDFConstants.EMPTYSTRING;
        return inheritedCustomerInfo.getCustomerJobName();
    }
    /**
     *@deprecated 06ß221 use getInheritedCustomerInfo(String attName)
     * @return
     */    
    public String getCustomerInfoCustomerOrderID() 
    {
        final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
        if(inheritedCustomerInfo==null)
            return JDFConstants.EMPTYSTRING;
        return inheritedCustomerInfo.getCustomerOrderID();
    }
    /**
     *@deprecated 06ß221 use getInheritedCustomerInfo(String attName)
     * @return
     */    
    public String getCustomerInfoCustomerProjectID() 
    {
        final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
        if(inheritedCustomerInfo==null)
            return JDFConstants.EMPTYSTRING;
        return inheritedCustomerInfo.getCustomerProjectID();
    }
    /**
     *@deprecated 06ß221 use getInheritedCustomerInfo(String attName)
     * @return
     */    
    public JDFCompany getCustomerInfoCompany() 
    {
        final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
        if(inheritedCustomerInfo==null)
            return null;
        return inheritedCustomerInfo.getCompany();
    }
    /**
     *@deprecated 06ß221 use getInheritedCustomerInfo(String attName)
     * @return
     */    
    public JDFContact getCustomerInfoContact(int iSkip) 
    {
        final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
        if(inheritedCustomerInfo==null)
            return null;
        return inheritedCustomerInfo.getContact(iSkip);
    }
    /**
     *@deprecated 06ß221 use getInheritedCustomerInfo(String attName)
     * @return
     */    
    public JDFCustomerMessage getCustomerInfoCustomerMessage(int iSkip) 
    {
        final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
        if(inheritedCustomerInfo==null)
            return null;
        return inheritedCustomerInfo.getCustomerMessage(iSkip);
    }



    /**
     * Checks if this process is the successor of the given process node.
     * 
     * @param  proc
     * 
     * @return boolean - <code>true</code> if this process is successor of given process
     */

    public boolean isSuccessor (JDFNode proc)
    {
        boolean isSuccessor = false;

        if (isProcessNode () && proc.isProcessNode ())
        {
            final VString vsInputResIDs = getResourceIDs (true);

            final VString vsOutputResIDs = proc.getResourceIDs (false);

            for (int i = 0; (i < vsInputResIDs.size ()) && !isSuccessor; i++)
            {
                isSuccessor = vsOutputResIDs.contains(vsInputResIDs.get(i));
            }
        }

        return isSuccessor;
    }

    /**
     * Returns the input or output resource IDs of this process node.
     * 
     * @param isInput - <code>true</code> to get input resource IDs.
     *                  <code>false</code> to get output resource IDs.
     * 
     * @return VString - Vector containing resource IDs.
     */

    public VString getResourceIDs(boolean isInput)
    {
        final VString vsLinks = new VString();        
        final JDFResourceLinkPool linkPool = getResourceLinkPool();        
        if (linkPool != null)
        {
            final VElement vInOutLinks = linkPool.getInOutLinks(isInput?EnumUsage.Input:EnumUsage.Output, true, null,null);
            final int nInOutLinks = vInOutLinks.size();
            for (int i = 0; i < nInOutLinks; i++)
            {
                final JDFResourceLink link = (JDFResourceLink) vInOutLinks.get(i);

                vsLinks.add(link.getrRef());
            }
        }
        return vsLinks;
    }
    /**
     * Gets the executable partitions of the resource in this node (with corresponding
     * resource link). The part maps returned may be nested. If the empty part map
     * is contained the whole resource is executable.
     *
     * Availability of a resource depends on the status, the availability of refered
     * sub-partitions and the part usage.
     *
     * @param link              - The resource link.
     * @param res               - The resource.
     * @param minStatus   - Minimum resource status to include.
     *
     * @return VJDFAttributeMap - A part map vector containing the executable partitions.
     */

    public VJDFAttributeMap getExecutablePartitions (JDFResourceLink link, JDFResource res, JDFResource.EnumResStatus minStatus)
    {
        final VJDFAttributeMap vp = new VJDFAttributeMap ();

        addExecutablePartitions (link, res, vp, minStatus);

        return vp;
    }

    /**
     * Adds the executable partitions of the resource in the node (with corresponding
     * resource link). The part maps returned may be nested.
     *
     * isAvailable will become true, if the given resource is available. Availability
     * depends on the status, the availability of refered sub-partitions and the part
     * usage.
     *
     * isProcStatOK will become true, if the process states of the given resource and all
     * its sub-partitions are "Waiting".
     *
     * The method may be applied to the root or sub-parts. If it is applied to the root
     * a returned empty part map (in the vector of part maps) indicates that the whole
     * resource is executable. 
     *
     * NOTE: The method only adds partitions to vamPartMaps. Therefore, the caller has to
     * clear the vector before calling this method.
     *
     * 
     * @param link              - The resource link.
     * @param res               - May be the root node or any partition.
     * @param vamPartMaps       - The vector to which the found part maps will be added.
     * @param minStatus   - Minimum resource status to include.
     * 
     * @return ExecPartFlags - The flags <code>isAvailable</code> and <code>isProcStatOK</code>.
     */

    private ExecPartFlags addExecutablePartitions(
            JDFResourceLink link, JDFResource res, VJDFAttributeMap vamPartMaps, JDFResource.EnumResStatus minStatus)
    {
        final JDFAttributeMap amPartMap = res.getPartMap();

        ////////////////////////////////////////////////////////
        // Check the process status.
        //

        boolean isProcStatOK = false; 

        final JDFElement.EnumNodeStatus stat = getPartStatus (amPartMap);

        if ((stat==null) ||
                (stat == JDFNode.EnumNodeStatus.Waiting) ||
                (stat == JDFNode.EnumNodeStatus.Ready)   )
        {
            isProcStatOK = true;
        }

        JDFResource.EnumPartUsage partUsage = res.getPartUsage ();

        ////////////////////////////////////////////////////////
        // Check if all child partitions are executable.
        //

        final VElement veChildPartitions = res.getChildElementVector_JDFElement(
                res.getNodeName (), null, null, false, 0, true);

        final int nChildPartitions = veChildPartitions.size ();

        boolean allChildsAvailable = true;

        for (int i = 0; i < nChildPartitions; i++)
        {
            final JDFResource sub = (JDFResource) veChildPartitions.get (i);

            final JDFAttributeMap amSub = sub.getPartMap ();

            if (link.overlapsResourcePartMap (amSub))
            {
                final ExecPartFlags ExecChild = addExecutablePartitions (link, sub, vamPartMaps, minStatus);

                if (!ExecChild.isAvailable ())
                {
                    allChildsAvailable = false;
                }

                if (!ExecChild.isProcStatOK () && (partUsage == JDFResource.EnumPartUsage.Explicit))
                {
                    isProcStatOK = false;
                }
            }            
        }

        ////////////////////////////////////////////////////////
        // Determine status of resource.
        //

        final JDFResource.EnumResStatus statRes = res.getResStatus (false);
        final JDFResource.EnumSpawnStatus statSpawn = res.getSpawnStatus ();
        boolean isAvailable = ((statRes.getValue () >= minStatus.getValue ())
                && (statSpawn != JDFResource.EnumSpawnStatus.SpawnedRW));

        if (nChildPartitions > 0)   // Non leaf
        {
            ////////////////////////////////////////////////////////
            // In case special parts are referenced by the link, the
            // resource should behave as if it has explicit part usage
            // when determining availability.
            //

            if (!allChildsAvailable)
            {
                isAvailable = false;
            }
            else
            {
                if ((partUsage == JDFResource.EnumPartUsage.Explicit)
                        || (link.hasChildElement (ElementName.PART, JDFConstants.EMPTYSTRING)))
                {
                    isAvailable = true;
                }
            }
        }

        ////////////////////////////////////////////////////////
        // Add part map if executable, and spawn is allowed.
        //

        final boolean hasResourcePartMap = link.hasResourcePartMap (amPartMap, false);

        final boolean isExecutable = hasResourcePartMap && isProcStatOK && isAvailable && res.isSpawnAllowed();

        if (isExecutable)
        {
            vamPartMaps.add (amPartMap);
        }

        ////////////////////////////////////////////////////////
        // Return the two booleans as integer.
        //

        return new ExecPartFlags (isAvailable, isProcStatOK);
    }
    /**
     * Class ExecPartFlags
     * 
     * Returned by <code>addExecutablePartitions</code>
     *
     */

    private static final class ExecPartFlags
    {
        private boolean m_isAvailable;
        private boolean m_isProcStatOK;

        protected ExecPartFlags(boolean isAvailable, boolean isProcStatOK)
        {
            m_isAvailable  = isAvailable;
            m_isProcStatOK = isProcStatOK;
        }
        boolean isAvailable()
        {
            return m_isAvailable;
        }

        boolean isProcStatOK()
        {
            return m_isProcStatOK;
        }
    }

    /**
     * Gets all child process nodes. This function replaces the
     * JDFDoc.getProcessNodes, which may be implemented as
     * getJDFRoot.getProcessNodes();
     * @deprecated use getvJDFNode(null,null,false) and skip intermediate nodes
     * 
     * @return JDFNode [] - All child process nodes.
     */

    public JDFNode [] getProcessNodes()
    {
        final Vector vJDFNodes = getvJDFNode(null, null, false);

        final Vector vProcessNodes = new Vector();

        JDFNode [] processNodes = null;

        for (int i = 0; i < vJDFNodes.size(); i++)
        {
            final JDFNode jdfNode = (JDFNode) vJDFNodes.elementAt(i);

            if (jdfNode.isProcessNode())
            {
                vProcessNodes.add(jdfNode);
            }
        }

        processNodes = new JDFNode [vProcessNodes.size()];

        vProcessNodes.copyInto(processNodes);

        return processNodes;
    }

    /**
     * Checks if this node is a simple process (including Combined) leaf node.
     * 
     * @return boolean - <code>true</code> if this is a process node.
     */

    public boolean isProcessNode()
    {
        return !isGroupNode();        
    }


    /**
     * calculate whether a link should be RW or RO
     * 
     * @param    JDFResourceLink li the link to check
     * @param    VString vRWResources the list of resource ids, process usages, usages, names. 
     *           If any match, the referenced resource is considered rw
     * @return   boolean true if rw
     */
    private boolean linkFitsRWRes(JDFResourceLink li,  VString vRWResources)
    {
        boolean bResRW = vRWResources.contains(li.getNamedProcessUsage());
        // 200602 RP added fix
        if(!bResRW)
        {
            bResRW = vRWResources.contains(li.getLinkedResourceName());
        }

        // 230802 RP added check for ID in vRWResources
        if(!bResRW)
        {
            bResRW = vRWResources.contains(li.getrRef());
        }

        // 040902 RP added check for Usage in vRWResources
        if(!bResRW)
        {
            bResRW = vRWResources.contains(li.getAttribute(AttributeName.USAGE));
        }
        return bResRW;
    }

    /**
     * calculate whether a link should be RW or RO
     * 
     * @param    JDFResourceLink li the link to check
     * @param    VString vRWResources the list of resource ids, process usages, usages, names. 
     *           If any match, the referenced resource is considered rw
     * @return   boolean true if rw
     */
    private boolean resFitsRWRes(JDFResource r, VString vRWResources)
    {

        boolean bResRW = vRWResources.contains(r.getLocalName());
        // 200602 RP added fix
        if(!bResRW)
        {
            bResRW = vRWResources.contains(JDFConstants.STAR);
        }

        // 230802 RP added check for ID in vRWResources
        if(!bResRW)
        {
            bResRW = vRWResources.contains(r.getID());
        }

        return bResRW;
    }


    /**
     * add an internal pipe 
     * (an input and an output link to an explicitly defined exchange resource)
     * @param resourceName The name of the reource to create
     * @param indexOutput the CombinedProcessIndex of the output ResourceLink to the internal pipe
     * @param indexInput the CombinedProcessIndex of the input ResourceLink to the internal pipe
     * @return JDFResource the newly created resource
     */
    public JDFResource addInternalPipe(String resourceName, int indexOutput, int indexInput)
    {
        if(EnumType.getEnum(getType()) != EnumType.Combined)
        {
            throw new JDFException("JDFNode.addInternalPipe: adding pipe to node that is not combined " + getType());
        } 
        JDFResource r = addResource(resourceName, null,  null, null, null, null,null);
        r.setPipeProtocol(JDFConstants.INTERNAL);

        JDFResourceLink rl = linkResource(r,EnumUsage.Input,null);
        rl.setPipeProtocol(JDFConstants.INTERNAL); // redundant but not harmful
        rl.setCombinedProcessIndex(new JDFIntegerList(indexInput)); 

        rl = linkResource(r, EnumUsage.Output,null);
        rl.setPipeProtocol(JDFConstants.INTERNAL);// redundant but not harmful
        rl.setCombinedProcessIndex(new JDFIntegerList(indexOutput));

        return r;
    }


    /**
     * get a heuristic partidkey vector from the partitons of the linked resources
     * 
     * @param JDFAttributeMap partMap the partmap to order. If not specified, use the output link
     * 
     * @return the ordered vector of partIDKeys
     */
    public VString getPartIDKeys(JDFAttributeMap partMap)
    {
        VString matchingPartIDKeys = new VString();
        if((partMap!=null)&& partMap.size() > 1)
        {
            JDFResourceLinkPool resourceLinkPool = getResourceLinkPool();
            if(resourceLinkPool != null)
            {
                VElement linkedResources = resourceLinkPool.getLinkedResources(null,null,null,false);
                int linkedResourcesSize = linkedResources.size();
                for (int i = 0; i < linkedResourcesSize; i++)
                {
                    JDFResource resource = (JDFResource)linkedResources.elementAt(i);
                    VString partIDKeys   = resource.getPartIDKeys();
                    if(partIDKeys.size() >= partMap.size() && partIDKeys.containsAll(partMap.getKeys()))
                    {
                        matchingPartIDKeys = partIDKeys;
                        break;
                    }
                }
            }
        }
        else
        {
            if(partMap!=null)
                matchingPartIDKeys = partMap.getKeys();
        }
        if(matchingPartIDKeys.isEmpty())
        {
            // grab output link and partition nodeinfo accordingly
            VElement vRes = null;
            JDFResourceLinkPool rp=getResourceLinkPool();
            if(rp!=null)
                vRes=rp.getInOutLinks(EnumUsage.Output, false, null,null);

            // get heuristic list of partidkeys from the output
            if(vRes!=null && vRes.size() > 0)
            {
                JDFResource r       = (JDFResource)vRes.elementAt(0);
                JDFResource resRoot = r.getResourceRoot();
                if(resRoot != null)
                {
                    matchingPartIDKeys = resRoot.getPartIDKeys();
                }
            }
        }

        return matchingPartIDKeys;
    }


    /**
     * prepare the nodeinfo for a partitioned spawn
     * 
     * @param VJDFAttributeMap vSpawnParts the list of parts to spawn
     * 
     * @return the vector of nodeinfo leaves
     */
    public VElement prepareNodeInfo(VJDFAttributeMap vSpawnParts)
    {

        //make sure we have a nodeinfo in case we have to merge stati
        JDFNodeInfo ni = getCreateNodeInfo();  
        VElement vni=new VElement();

        if(ni.hasAttribute(AttributeName.CLASS,null,false))
        { // it is a 1.3 style resource
            JDFAttributeMap spawnPart = new JDFAttributeMap();
            if(vSpawnParts!=null && vSpawnParts.size()>0)
            {               
                for(int i = 0;i < vSpawnParts.size(); i++)
                {
                    if(vSpawnParts.elementAt(i).size() > spawnPart.size())
                    {
                        spawnPart = vSpawnParts.elementAt(i);
                    }
                }

                VString partVector=getPartIDKeys(spawnPart);
                if(getStatus()!=EnumNodeStatus.Part){
                    ni.setAttribute(AttributeName.NODESTATUS,getAttribute(AttributeName.STATUS));
                    this.setStatus(EnumNodeStatus.Part);
                }
                for(int i=0;i<vSpawnParts.size();i++){
                    JDFNodeInfo niLeaf=(JDFNodeInfo)ni.getPartition(vSpawnParts.elementAt(i), EnumPartUsage.Explicit);
                    if(niLeaf==null){ // leaves that do not exist yet are assumed waiting
                        niLeaf=(JDFNodeInfo) ni.getCreatePartition(vSpawnParts.elementAt(i),partVector);
                        niLeaf.setAttribute(AttributeName.NODESTATUS, "Waiting");
                    }
                    vni.add(niLeaf);
                }
            }
            else
            {
                vni.add(ni); // simply return the 1.3 resource              
            }
        }
        else
        {
            vni.add(ni); // simply return the 1.2 element
        }

        return vni;
    }

    /**
     * Erases all empty text nodes in 'this' and its subelements
     * If there any empty text nodes removes them.
     * the JDF node version also removes empty pools
     * If bTrimWhite is true, then trims white spaces from both
     * ends of a text node and only then, if it is empty, removes it
     *
     * @param bTrimWhite trims whitespace of text, default = true
     * @return int       the number of removed nodes
     */
    public int eraseEmptyNodes(boolean bTrimWhite)
    {
        int nRemove = super.eraseEmptyNodes(bTrimWhite);

        KElement n = getFirstChildElement();
        while (n != null)
        {
            KElement nNext=n.getNextSiblingElement();
            if(n.getLocalName().endsWith("Pool")){
                if(!n.hasAttributes()&&!n.hasChildElements()){
                    n.deleteNode();
                }
            }
            n = nNext;      
        }

        return nRemove;
    }   

    /**
     * getLinks - get the links matching mLinkAtt out of the resource link pool
     *
     * @param linkName - the name of the link including or excluding the "Link", 
     * If it is omitted, it will be added
     * @param mLinkAtt - the attributes to search for
     * @param linkNS - the namespace of the link
     *
     * @return VElement - all elements matching the condition mLinkAtt
     * default: getLinks(null,null,null)
     */
    public VElement getLinks(String linkName, JDFAttributeMap mLinkAtt, String linkNS)
    {
        JDFResourceLinkPool rlp=getResourceLinkPool();
        if(rlp==null)
            return null;
        if(linkName!=null && !linkName.endsWith(JDFConstants.LINK))
            linkName+=JDFConstants.LINK;

        return rlp.getPoolChildren(linkName, mLinkAtt, linkNS);
    }

    /**
     * getLink - get the n'th link matching mLinkAtt out of the resource link pool
     *
     * @param index - the index of the matching link
     * @param linkName - the name of the link including or excluding the "Link", 
     * If it is omitted, it will be added
     * @param mLinkAtt - the attributes to search for
     * @param linkNS - the namespace of the link
     *
     * @return JDFResourceLink - the ResourceLink matching the condition mLinkAtt
     * default: getLinks(null,null,null)
     */
    public JDFResourceLink getLink(int index, String linkName, JDFAttributeMap mLinkAtt, String linkNS)
    {
        JDFResourceLinkPool rlp=getResourceLinkPool();
        if(rlp==null)
            return null;
        if(linkName!=null && !linkName.endsWith(JDFConstants.LINK))
            linkName+=JDFConstants.LINK;

        return rlp.getPoolChild(index, linkName, mLinkAtt, linkNS);
    }

    /**
     * Gets all elements with name linkName, which contain resource links that point to this resource
     *
     * @param linkName defaults to any 
     * @param nameSpaceURI attribute namespace you are searching in
     * 
     * @return VElement vector of all found elements
     * @deprecated this routine does not belong here at all!
     * default: getLinks(null, null)
     */
    public VElement getLinks(String linkName, String nameSpaceURI)
    {
        final JDFAttributeMap m = new JDFAttributeMap(AttributeName.RREF, getID());
        return getDocRoot().getChildrenByTagName(linkName, nameSpaceURI, m, false, false, 0);
    }   



}