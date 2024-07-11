/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
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
 * 2001-09-07 Torsten Kaehlert - if (!GetNodeName().equals("JDF")) changed from equals to !equals TKAE20010907
 *
 * 2001-07-30 Torsten Kaehlert - delete isNull() and throwNull() methods in parent class KNode TKAE20010730 13-02-2002 Kai Mattern - added getSpawnID 13-02-2002 Kai Mattern - added removeSpawnID
 * 15-02-2002 Kai Mattern - changed isValid in linkResource (added parameter ValidationLevel_Construct) 06-02-2002 Kai Mattern - added LinkNames() 06-02-2002 Kai Mattern - added LinkInfo() 06-02-2002
 * Kai Mattern - added isValidLinkIndex() 06-02-2002 Kai Mattern - added getLinksLength() 06-02-2002 Kai Mattern - added getGenericLinksLength() 06-02-2002 Kai Mattern - added EnumProcessUsage()
 * 06-02-2002 Kai Mattern - defined ant value GENERIC_LINKS_LENGHT; 07-02-2002 Kai Mattern - added getMatchingResource() 07-02-2002 Kai Mattern - added getMatchingLink() 07-02-2002 Kai Mattern - added
 * getMatchingLinks() 08-02-2002 Kai Mattern - added MapEnumToInfo() 12-02-2002 Kai Mattern - added NumMatchingLinks() 12-02-2002 Kai Mattern - added removeMatchingLink() 12-02-2002 Kai Mattern -
 * added LinkMatchingResource() 18-06-2002 JG - added UnSpawn 18-06-2002 JG - modified activation list 18-06-2002 JG - added ProjectID support 18-06-2002 JG - modified getParentJDFNode() 18-06-2002 JG
 * - added bSpawnROPartsOnly to Spawn(...) and addSpawnedResources(...) 18-06-2002 JG - added getAncestorAttribute(), hasAncestorAttribute() 18-06-2002 JG - getMatchingLinks() minor bug fixes
 * 18-06-2002 JG - renamed UnSpawn to UnSpawnNode() 18-06-2002 JG - added getAncestorElement(), hasAncestorElement() 18-06-2002 JG - fixed StatusPool Handling in UnspawnNode() and setPartStatus()
 * 18-06-2002 JG - getMissingLinkVecor() bug fix 18-06-2002 JG - removed getAncestorNode -> use getParentJDFNode instead 18-06-2002 JG - calls to getInheritedAttribute replaced by calls to
 * getAncestorAttribute 18-06-2002 JG - calls to hasAttribute replaced by calls to hasAncestorAttribute 18-06-2002 JG - addSpawnedResources() bug fix for appending to rRefsRO / rRefsRW, remove call to
 * setLocked for root of partitioned resource 10-09-2002 RP - MapEnumToInfo >= --> > bug fix 27-09-2006 NB - finished mapPut(), fixed TypeLinkInfo(), TypeLinksNames() [16x]
 *
 */
package org.cip4.jdflib.node;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFCustomerMessage;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFPartStatus;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDocUserData;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.UnLinkFinder;
import org.cip4.jdflib.ifaces.INodeIdentifiable;
import org.cip4.jdflib.ifaces.IURLSetter;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAncestorPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.pool.JDFStatusPool;
import org.cip4.jdflib.resource.JDFCreated;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.PartitionGetter;
import org.cip4.jdflib.resource.process.JDFBusinessInfo;
import org.cip4.jdflib.resource.process.JDFCompany;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.resource.process.JDFNotificationFilter;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.JDFMerge;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.StatusCounter;
import org.cip4.jdflib.util.StringUtil;

/**
 * This is the main node for the JDF ticket. Others are around, but this is the main one to do editing.
 */
public class JDFNode extends JDFElement implements INodeIdentifiable, IURLSetter
{
	/**
	 * get the input stream that reads from URL
	 *
	 * @return InputStream the input stream that the url points to, null if the url is inaccessible
	 */
	@Override
	public InputStream getURLInputStream()
	{
		return getURLInputStream(getURL());
	}

	/**
	 * 0x22222222 is the HexValue used so programmers know which attribute/element is REQUIRED when "Add Required elements/attributes" is selected. The validation tool will also
	 * throw an error until the attribute/element is added.
	 */
	private static AtrInfoTable[] atrInfoTable_abstract = new AtrInfoTable[21];
	static
	{
		atrInfoTable_abstract[0] = new AtrInfoTable(AttributeName.ACTIVATION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumActivation.getEnum(0), null);
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
		atrInfoTable_abstract[14] = new AtrInfoTable(AttributeName.TEMPLATE, 0x33333311, AttributeInfo.EnumAttributeType.boolean_, null, "false");
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
		atrInfoTable_root[1] = new AtrInfoTable(AttributeName.XMLNS, 0x33333331, AttributeInfo.EnumAttributeType.URI, null, null);
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
	 *
	 * @return comma separated list of optional attributes for JDF nodes
	 */
	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		final AttributeInfo ai = super.getTheAttributeInfo().updateReplace(atrInfoTable_abstract);
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
	@Override
	protected ElementInfo getTheElementInfo()
	{
		final ElementInfo ei = super.getTheElementInfo().updateReplace(elemInfoTable_abstract);

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
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFNode(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFNode
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFNode(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFNode
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFNode(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	private static final long serialVersionUID = 1L;
	private static final Log nLog = LogFactory.getLog(JDFElement.class);

	/**
	 * Enumeration for the policy of cleaning up the Spawn and Merge audits
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumCleanUpMerge extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;

		private static int m_startValue = 0;

		/**
		 *
		 * @see org.apache.commons.lang.enums.ValuedEnum#toString()
		 */
		@Override
		public String toString()
		{
			return getName();
		}

		protected EnumCleanUpMerge(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumCleanUpMerge getEnum(final String enumName)
		{
			return (EnumCleanUpMerge) getEnum(EnumCleanUpMerge.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumCleanUpMerge getEnum(final int enumValue)
		{
			return (EnumCleanUpMerge) getEnum(EnumCleanUpMerge.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumCleanUpMerge.class);
		}

		/**
		 * @return
		 */
		public static List<EnumCleanUpMerge> getEnumList()
		{
			return getEnumList(EnumCleanUpMerge.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumCleanUpMerge.class);
		}

		/**
		 * Constants EnumActivation
		 */
		public static final EnumCleanUpMerge None = new EnumCleanUpMerge(JDFConstants.CLEANUPMERGE_NONE);
		/**
		 *
		 */
		public static final EnumCleanUpMerge RemoveRRefs = new EnumCleanUpMerge(JDFConstants.CLEANUPMERGE_REMOVERREFS);
		/**
		 */
		public static final EnumCleanUpMerge RemoveAll = new EnumCleanUpMerge(JDFConstants.CLEANUPMERGE_REMOVEALL);

	}

	/**
	 * inner class EnumActivation:<br>
	 * Enumeration for attribute Activation
	 */
	public static final class EnumActivation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;

		private static int m_startValue = 0;

		/**
		 *
		 * return true if a allows us to actively process now
		 *
		 * @param a
		 * @return
		 */
		public static boolean isActive(final EnumActivation a)
		{
			return a == null || EnumActivation.Active.equals(a) || EnumActivation.TestRunAndGo.equals(a);
		}

		protected EnumActivation(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumActivation getEnum(final String enumName)
		{
			return (EnumActivation) getEnum(EnumActivation.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumActivation getEnum(final int enumValue)
		{
			return (EnumActivation) getEnum(EnumActivation.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumActivation.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumActivation.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumActivation.class);
		}

		/**
		 *
		 */
		public static final EnumActivation Unknown = null;

		/**
		 *
		 */
		public static final EnumActivation Inactive = new EnumActivation(JDFConstants.ACTIVATION_INACTIVE);
		/**
		 *
		 */
		public static final EnumActivation Informative = new EnumActivation(JDFConstants.ACTIVATION_INFORMATIVE);
		/**
		 *
		 */
		public static final EnumActivation Held = new EnumActivation(JDFConstants.ACTIVATION_HELD);
		/**
		 *
		 */
		public static final EnumActivation TestRun = new EnumActivation(JDFConstants.ACTIVATION_TESTRUN);
		/**
		 *
		 */
		public static final EnumActivation TestRunAndGo = new EnumActivation(JDFConstants.ACTIVATION_TESTRUNANDGO);
		/**
		 *
		 */
		public static final EnumActivation Active = new EnumActivation(JDFConstants.ACTIVATION_ACTIVE);
	}

	/**
	 * Constants EnumActivation use EnumActivation.xxx instead of the deprecated constants EnumActivation.Activation_xxx
	 */

	/** @deprecated use EnumActivation.Inactive */
	@Deprecated
	public static final EnumActivation Activation_Inactive = EnumActivation.Inactive;
	/** @deprecated use EnumActivation.EnumActivation.Informative */
	@Deprecated
	public static final EnumActivation Activation_Informative = EnumActivation.Informative;
	/** @deprecated use EnumActivation.Held */
	@Deprecated
	public static final EnumActivation Activation_Held = EnumActivation.Held;
	/** @deprecated use EnumActivation.TestRun */
	@Deprecated
	public static final EnumActivation Activation_TestRun = EnumActivation.TestRun;
	/** @deprecated use EnumActivation.TestRunAndGo */
	@Deprecated
	public static final EnumActivation Activation_TestRunAndGo = EnumActivation.TestRunAndGo;
	/** @deprecated use EnumActivation.Active */
	@Deprecated
	public static final EnumActivation Activation_Active = EnumActivation.Active;

	/**
	 * inner class EnumType: Enumeration for accessing typesafe node types
	 */
	public static final class EnumType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;

		private static int m_startValue = 0;

		protected EnumType(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumType getEnum(final String enumName)
		{
			final EnumType myEnum = (EnumType) getEnum(EnumType.class, enumName);
			return myEnum;
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumType getEnum(final int enumValue)
		{
			return (EnumType) getEnum(EnumType.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumType.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumType.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumType.class);
		}

		// generic
		/**
		 *
		 */
		public static final EnumType ProcessGroup = new EnumType(JDFConstants.TYPE_PROCESSGROUP);
		/**
		 *
		 */
		public static final EnumType Combined = new EnumType(JDFConstants.TYPE_COMBINED);
		/**
		 *
		 */
		public static final EnumType Product = new EnumType(JDFConstants.TYPE_PRODUCT);
		/**
		 *
		 */
		public static final EnumType Approval = new EnumType(JDFConstants.TYPE_APPROVAL);
		/**
		 *
		 */
		public static final EnumType Buffer = new EnumType(JDFConstants.TYPE_BUFFER);
		/**
		 *
		 */
		public static final EnumType Combine = new EnumType(JDFConstants.TYPE_COMBINE);
		/**
		 *
		 */
		public static final EnumType Delivery = new EnumType(JDFConstants.TYPE_DELIVERY);
		/**
		 *
		 */
		public static final EnumType ManualLabor = new EnumType(JDFConstants.TYPE_MANUALLABOR);
		/**
		 *
		 */
		public static final EnumType Ordering = new EnumType(JDFConstants.TYPE_ORDERING);
		/**
		 *
		 */
		public static final EnumType Packing = new EnumType(JDFConstants.TYPE_PACKING);
		/** * */
		public static final EnumType QualityControl = new EnumType(JDFConstants.TYPE_QUALITYCONTROL);
		/** * */
		public static final EnumType ResourceDefinition = new EnumType(JDFConstants.TYPE_RESOURCEDEFINITION);
		/** * */
		public static final EnumType Split = new EnumType(JDFConstants.TYPE_SPLIT);
		/** * */
		public static final EnumType Verification = new EnumType(JDFConstants.TYPE_VERIFICATION);

		// prepress
		/** * */
		public static final EnumType AssetListCreation = new EnumType(JDFConstants.TYPE_ASSETLISTCREATION);
		/** * */
		public static final EnumType Bending = new EnumType(JDFConstants.TYPE_BENDING);
		/** * */
		public static final EnumType ColorCorrection = new EnumType(JDFConstants.TYPE_COLORCORRECTION);
		/** * */
		public static final EnumType ColorSpaceConversion = new EnumType(JDFConstants.TYPE_COLORSPACECONVERSION);
		/** * */
		public static final EnumType ContactCopying = new EnumType(JDFConstants.TYPE_CONTACTCOPYING);
		/** * */
		public static final EnumType ContoneCalibration = new EnumType(JDFConstants.TYPE_CONTONECALIBRATION);
		/** * */
		public static final EnumType CylinderLayoutPreparation = new EnumType(JDFConstants.TYPE_CYLINDERLAYOUTPREPARATION);
		/** * */
		public static final EnumType DBDocTemplateLayout = new EnumType(JDFConstants.TYPE_DBDOCTEMPLATELAYOUT);
		/** * */
		public static final EnumType DBTemplateMerging = new EnumType(JDFConstants.TYPE_DBTEMPLATEMERGING);
		/** * */
		public static final EnumType DieDesign = new EnumType("DieDesign");
		/** * */
		public static final EnumType DieLayoutProduction = new EnumType("DieLayoutProduction");
		/** * */
		public static final EnumType DigitalDelivery = new EnumType(JDFConstants.TYPE_DIGITALDELIVERY);
		/** * */
		public static final EnumType FilmToPlateCopying = new EnumType(JDFConstants.TYPE_FILMTOPLATECOPYING);
		/** * */
		public static final EnumType FormatConversion = new EnumType(JDFConstants.TYPE_FORMATCONVERSION);
		/** * */
		public static final EnumType ImageEnhancement = new EnumType("ImageEnhancement");
		/** * */
		public static final EnumType ImageReplacement = new EnumType(JDFConstants.TYPE_IMAGEREPLACEMENT);
		/** * */
		public static final EnumType ImageSetting = new EnumType(JDFConstants.TYPE_IMAGESETTING);
		/** * */
		public static final EnumType Imposition = new EnumType(JDFConstants.TYPE_IMPOSITION);
		/** * */
		public static final EnumType InkZoneCalculation = new EnumType(JDFConstants.TYPE_INKZONECALCULATION);
		/** * */
		public static final EnumType Interpreting = new EnumType(JDFConstants.TYPE_INTERPRETING);
		/** * */
		public static final EnumType LayoutElementProduction = new EnumType(JDFConstants.TYPE_LAYOUTELEMENTPRODUCTION);
		/** * */
		public static final EnumType LayoutPreparation = new EnumType(JDFConstants.TYPE_LAYOUTPREPARATION);
		/** * */
		public static final EnumType LayoutShifting = new EnumType("LayoutShifting");
		/** * */
		public static final EnumType PageAssigning = new EnumType("PageAssigning");
		/** * */
		public static final EnumType PDFToPSConversion = new EnumType(JDFConstants.TYPE_PDFTOPSCONVERSION);
		/** * */
		public static final EnumType PDLCreation = new EnumType(JDFConstants.TYPE_PDLCREATION);
		/** * */
		public static final EnumType Preflight = new EnumType(JDFConstants.TYPE_PREFLIGHT);
		/** * */
		public static final EnumType PreviewGeneration = new EnumType(JDFConstants.TYPE_PREVIEWGENERATION);
		/** * */
		public static final EnumType Proofing = new EnumType(JDFConstants.TYPE_PROOFING);
		/** * */
		public static final EnumType PSToPDFConversion = new EnumType(JDFConstants.TYPE_PSTOPDFCONVERSION);
		/** * */
		public static final EnumType RasterReading = new EnumType(JDFConstants.TYPE_RASTERREADING);
		/** * */
		public static final EnumType Rendering = new EnumType(JDFConstants.TYPE_RENDERING);
		/** * */
		public static final EnumType RIPing = new EnumType("RIPing");
		/** * */
		public static final EnumType Scanning = new EnumType(JDFConstants.TYPE_SCANNING);
		/** * */
		public static final EnumType Screening = new EnumType(JDFConstants.TYPE_SCREENING);
		/** * */
		public static final EnumType Separation = new EnumType(JDFConstants.TYPE_SEPARATION);
		/** * */
		public static final EnumType SoftProofing = new EnumType(JDFConstants.TYPE_SOFTPROOFING);
		/** * */
		public static final EnumType Stripping = new EnumType(JDFConstants.TYPE_STRIPPING);
		/** * */
		public static final EnumType Tiling = new EnumType(JDFConstants.TYPE_TILING);
		/** * */
		public static final EnumType Trapping = new EnumType(JDFConstants.TYPE_TRAPPING);

		// press
		/** * */
		public static final EnumType ConventionalPrinting = new EnumType(JDFConstants.TYPE_CONVENTIONALPRINTING);
		/** * */
		public static final EnumType DigitalPrinting = new EnumType(JDFConstants.TYPE_DIGITALPRINTING);
		/** * */
		public static final EnumType IDPrinting = new EnumType(JDFConstants.TYPE_IDPRINTING);
		/** * */
		public static final EnumType Varnishing = new EnumType("Varnishing");

		// postpress
		/** * */
		public static final EnumType AdhesiveBinding = new EnumType(JDFConstants.TYPE_ADHESIVEBINDING);
		/** * */
		public static final EnumType BlockPreparation = new EnumType(JDFConstants.TYPE_BLOCKPREPARATION);
		/** * */
		public static final EnumType BoxPacking = new EnumType(JDFConstants.TYPE_BOXPACKING);
		/** * */
		public static final EnumType BoxFolding = new EnumType(JDFConstants.TYPE_BOXFOLDING);
		/** * */
		public static final EnumType Bundling = new EnumType(JDFConstants.TYPE_BUNDLING);
		/** * */
		public static final EnumType CaseMaking = new EnumType(JDFConstants.TYPE_CASEMAKING);
		/** * */
		public static final EnumType CasingIn = new EnumType(JDFConstants.TYPE_CASINGIN);
		/** * */
		public static final EnumType ChannelBinding = new EnumType(JDFConstants.TYPE_CHANNELBINDING);
		/** * */
		public static final EnumType CoilBinding = new EnumType(JDFConstants.TYPE_COILBINDING);
		/** * */
		public static final EnumType Collecting = new EnumType(JDFConstants.TYPE_COLLECTING);
		/** * */
		public static final EnumType CoverApplication = new EnumType(JDFConstants.TYPE_COVERAPPLICATION);
		/** * */
		public static final EnumType Creasing = new EnumType(JDFConstants.TYPE_CREASING);
		/** * */
		public static final EnumType Cutting = new EnumType(JDFConstants.TYPE_CUTTING);
		/** * */
		public static final EnumType DieMaking = new EnumType("DieMaking");
		/** * */
		public static final EnumType Dividing = new EnumType(JDFConstants.TYPE_DIVIDING);
		/** * */
		public static final EnumType Embossing = new EnumType(JDFConstants.TYPE_EMBOSSING);
		/** * */
		public static final EnumType EndSheetGluing = new EnumType(JDFConstants.TYPE_ENDSHEETGLUING);
		/** * */
		public static final EnumType Feeding = new EnumType(JDFConstants.TYPE_FEEDING);
		/** * */
		public static final EnumType Folding = new EnumType(JDFConstants.TYPE_FOLDING);
		/** * */
		public static final EnumType Gathering = new EnumType(JDFConstants.TYPE_GATHERING);
		/** * */
		public static final EnumType Gluing = new EnumType(JDFConstants.TYPE_GLUING);
		/** * */
		public static final EnumType HeadBandApplication = new EnumType(JDFConstants.TYPE_HEADBANDAPPLICATION);
		/** * */
		public static final EnumType HoleMaking = new EnumType(JDFConstants.TYPE_HOLEMAKING);
		/** * */
		public static final EnumType Inserting = new EnumType(JDFConstants.TYPE_INSERTING);
		/** * */
		public static final EnumType Jacketing = new EnumType(JDFConstants.TYPE_JACKETING);
		/** * */
		public static final EnumType Labeling = new EnumType(JDFConstants.TYPE_LABELING);
		/** * */
		public static final EnumType Laminating = new EnumType(JDFConstants.TYPE_LAMINATING);
		/** * */
		public static final EnumType LongitudinalRibbonOperations = new EnumType(JDFConstants.TYPE_LONGITUDINALRIBBONOPERATIONS);
		/** * */
		public static final EnumType Numbering = new EnumType(JDFConstants.TYPE_NUMBERING);
		/** * */
		public static final EnumType Palletizing = new EnumType(JDFConstants.TYPE_PALLETIZING);
		/** * */
		public static final EnumType Perforating = new EnumType(JDFConstants.TYPE_PERFORATING);
		/** * */
		public static final EnumType PlasticCombBinding = new EnumType(JDFConstants.TYPE_PLASTICCOMBBINDING);
		/** * */
		public static final EnumType PrintRolling = new EnumType(JDFConstants.TYPE_PRINTROLLING);
		/** * */
		public static final EnumType RingBinding = new EnumType(JDFConstants.TYPE_RINGBINDING);
		/** * */
		public static final EnumType SaddleStitching = new EnumType(JDFConstants.TYPE_SADDLESTITCHING);
		/** * */
		public static final EnumType ShapeCutting = new EnumType(JDFConstants.TYPE_SHAPECUTTING);
		/** * */
		public static final EnumType ShapeDefProduction = new EnumType("ShapeDefProduction");
		/** * */
		public static final EnumType SheetOptimizing = new EnumType("SheetOptimizing");
		/** * */
		public static final EnumType Shrinking = new EnumType(JDFConstants.TYPE_SHRINKING);
		/** * */
		public static final EnumType SideSewing = new EnumType(JDFConstants.TYPE_SIDESEWING);
		/** * */
		public static final EnumType SpinePreparation = new EnumType(JDFConstants.TYPE_SPINEPREPARATION);
		/** * */
		public static final EnumType SpineTaping = new EnumType(JDFConstants.TYPE_SPINETAPING);
		/** * */
		public static final EnumType Stacking = new EnumType(JDFConstants.TYPE_STACKING);
		/** * */
		public static final EnumType StaticBlocking = new EnumType("StaticBlocking");
		/** * */
		public static final EnumType Stitching = new EnumType(JDFConstants.TYPE_STITCHING);
		/** * */
		public static final EnumType Strapping = new EnumType(JDFConstants.TYPE_STRAPPING);
		/** * */
		public static final EnumType StripBinding = new EnumType(JDFConstants.TYPE_STRIPBINDING);
		/** * */
		public static final EnumType ThreadSealing = new EnumType(JDFConstants.TYPE_THREADSEALING);
		/** * */
		public static final EnumType ThreadSewing = new EnumType(JDFConstants.TYPE_THREADSEWING);
		/** * */
		public static final EnumType Trimming = new EnumType(JDFConstants.TYPE_TRIMMING);
		/** * */
		public static final EnumType WebInlineFinishing = new EnumType(JDFConstants.TYPE_WEBINLINEFINISHING);
		/** * */
		public static final EnumType Winding = new EnumType("Winding");
		/** * */
		public static final EnumType WireCombBinding = new EnumType(JDFConstants.TYPE_WIRECOMBBINDING);
		/** * */
		public static final EnumType Wrapping = new EnumType(JDFConstants.TYPE_WRAPPING);

		// prepress gray box types
		/** * */
		public static final EnumType PlateSetting = new EnumType("PlateSetting");
		/** * */
		public static final EnumType PlateMaking = new EnumType("PlateMaking");
		/** * */
		public static final EnumType ProofAndPlateMaking = new EnumType("ProofAndPlateMaking");
		/** * */
		public static final EnumType ImpositionPreparation = new EnumType("ImpositionPreparation");
		/** * */
		public static final EnumType ImpositionProofing = new EnumType("ImpositionProofing");
		/** * */
		public static final EnumType PageProofing = new EnumType("PageProofing");
		/** * */
		public static final EnumType PageSoftProofing = new EnumType("PageSoftProofing");
		/** * */
		public static final EnumType PrepressPreparation = new EnumType("PrePressPreparation");
		/** * */
		public static final EnumType ProofImaging = new EnumType("ProofImaging");
	}

	/**
	 * Constants EnumType use EnumType.xxx instead of the deprecated constants EnumType.Type_xxx
	 */
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ProcessGroup = EnumType.ProcessGroup;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Combined = EnumType.Combined;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Product = EnumType.Product;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Approval = EnumType.Approval;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Buffer = EnumType.Buffer;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Combine = EnumType.Combine;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Delivery = EnumType.Delivery;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ManualLabor = EnumType.ManualLabor;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Ordering = EnumType.Ordering;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Packing = EnumType.Packing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_QualityControl = EnumType.QualityControl;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ResourceDefinition = EnumType.ResourceDefinition;
	/** @deprecated use EnumType.Type_xxx x */
	@Deprecated
	public static final EnumType Type_Split = EnumType.Split;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Verification = EnumType.Verification;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_AssetListCreation = EnumType.AssetListCreation;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ColorCorrection = EnumType.ColorCorrection;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ColorSpaceConversion = EnumType.ColorSpaceConversion;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ContactCopying = EnumType.ContactCopying;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ContoneCalibration = EnumType.ContoneCalibration;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_DBDocTemplateLayout = EnumType.DBDocTemplateLayout;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_DBTemplateMerging = EnumType.DBTemplateMerging;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_DigitalDelivery = EnumType.DigitalDelivery;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_FilmToPlateCopying = EnumType.FilmToPlateCopying;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_FormatConversion = EnumType.FormatConversion;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ImageReplacement = EnumType.ImageReplacement;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ImageSetting = EnumType.ImageSetting;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Imposition = EnumType.Imposition;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_InkZoneCalculation = EnumType.InkZoneCalculation;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Interpreting = EnumType.Interpreting;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_LayoutElementProduction = EnumType.LayoutElementProduction;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_LayoutPreparation = EnumType.LayoutPreparation;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_PDFToPSConversion = EnumType.PDFToPSConversion;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Preflight = EnumType.Preflight;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_PreviewGeneration = EnumType.PreviewGeneration;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Proofing = EnumType.Proofing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_PSToPDFConversion = EnumType.PSToPDFConversion;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Rendering = EnumType.Rendering;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Scanning = EnumType.Scanning;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Screening = EnumType.Screening;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Separation = EnumType.Separation;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_SoftProofing = EnumType.SoftProofing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Stripping = EnumType.Stripping;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Tiling = EnumType.Tiling;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Trapping = EnumType.Trapping;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ConventionalPrinting = EnumType.ConventionalPrinting;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_DigitalPrinting = EnumType.DigitalPrinting;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_IDPrinting = EnumType.IDPrinting;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_AdhesiveBinding = EnumType.AdhesiveBinding;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_BlockPreparation = EnumType.BlockPreparation;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_BoxPacking = EnumType.BoxPacking;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Bundling = EnumType.Bundling;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_CaseMaking = EnumType.CaseMaking;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_CasingIn = EnumType.CasingIn;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ChannelBinding = EnumType.ChannelBinding;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_CoilBinding = EnumType.CoilBinding;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Collecting = EnumType.Collecting;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_CoverApplication = EnumType.CoverApplication;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Creasing = EnumType.Creasing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Cutting = EnumType.Cutting;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Dividing = EnumType.Dividing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Embossing = EnumType.Embossing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_EndSheetGluing = EnumType.EndSheetGluing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Feeding = EnumType.Feeding;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Folding = EnumType.Folding;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Gathering = EnumType.Gathering;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Gluing = EnumType.Gluing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_HeadBandApplication = EnumType.HeadBandApplication;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_HoleMaking = EnumType.HoleMaking;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Inserting = EnumType.Inserting;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Jacketing = EnumType.Jacketing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Labeling = EnumType.Labeling;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Laminating = EnumType.Laminating;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_LongitudinalRibbonOperations = EnumType.LongitudinalRibbonOperations;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Numbering = EnumType.Numbering;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Palletizing = EnumType.Palletizing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Perforating = EnumType.Perforating;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_PlasticCombBinding = EnumType.PlasticCombBinding;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_PrintRolling = EnumType.PrintRolling;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_RingBinding = EnumType.RingBinding;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_SaddleStitching = EnumType.SaddleStitching;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ShapeCutting = EnumType.ShapeCutting;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Shrinking = EnumType.Shrinking;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_SideSewing = EnumType.SideSewing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_SpinePreparation = EnumType.SpinePreparation;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_SpineTaping = EnumType.SpineTaping;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Stacking = EnumType.Stacking;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Stitching = EnumType.Stitching;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Strapping = EnumType.Strapping;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_StripBinding = EnumType.StripBinding;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ThreadSealing = EnumType.ThreadSealing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_ThreadSewing = EnumType.ThreadSewing;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Trimming = EnumType.Trimming;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_WireCombBinding = EnumType.WireCombBinding;
	/** @deprecated use EnumType.xxx */
	@Deprecated
	public static final EnumType Type_Wrapping = EnumType.Wrapping;

	/**
	 *
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 *
	 *         Sep 25, 2009
	 */
	public class CombinedProcessLinkHelper
	{
		private int nType;
		private EnumUsage usage;
		private String linkName;
		private boolean linkBoth;

		/**
		 *
		 */
		public CombinedProcessLinkHelper()
		{
			nType = 0;
			usage = null;
			linkName = null;
		}

		/**
		 * @param _linkName
		 * @param _usage
		 *
		 */
		public CombinedProcessLinkHelper(final String _linkName, final EnumUsage _usage)
		{
			nType = 0;
			usage = _usage;
			setLinkName(_linkName);
		}

		/**
		 * get the links that are selected by a given CombinedProcessIndex<br>
		 * all links with no CombinedProcessIndex are included in the list
		 *
		 * @param type the process type for which to get the links
		 * @return
		 *
		 * @default getLinksForType(type, -1)
		 */
		public JDFResourceLink getCreateLinkForType(final EnumType type)
		{
			final int cpiType = getCombinedProcessIndex(type, nType);
			final int nPI = getTypes().size();
			if (cpiType < 0)
			{
				return null; // no matching type
			}
			final VElement ve = getLinksForType(type);
			if (ve != null)
			{
				return (JDFResourceLink) ve.get(0);
			}

			JDFResourceLink rlLast = null;
			if (EnumUsage.Input.equals(usage) && cpiType > 0)
			{
				final CombinedProcessLinkHelper hTmp = new CombinedProcessLinkHelper();
				hTmp.setUsage(EnumUsage.Output);
				hTmp.setLinkName(linkName);
				rlLast = hTmp.getLinkForCombinedProcessIndex(cpiType - 1, 0);
			}
			else if (EnumUsage.Output.equals(usage) && cpiType < nPI - 1)
			{
				final CombinedProcessLinkHelper hTmp = new CombinedProcessLinkHelper();
				hTmp.setUsage(EnumUsage.Input);
				hTmp.setLinkName(linkName);
				rlLast = hTmp.getLinkForCombinedProcessIndex(cpiType + 1, 0);
			}
			final JDFResource r = (rlLast != null) ? rlLast.getLinkRoot() : addResource(getResName(), usage);
			final JDFIntegerList il = new JDFIntegerList();
			final JDFResourceLink rl = ensureLink(r, usage, null);
			int nPos = 0;
			while (true)
			{
				final int cpi = getCombinedProcessIndex(type, nPos);
				if (cpi < 0)
				{
					break;
				}
				if (nType < 0 || nType == nPos)
				{
					il.add(cpi);
				}
				nPos = cpi + 1;
			}
			rl.setCombinedProcessIndex(il);

			if (rlLast == null && linkBoth && nType >= 0)
			{
				final EnumUsage otherUsage = usage.invert();
				rlLast = ensureLink(r, otherUsage, null);
				nPos--; // undo +1
				nPos += (EnumUsage.Output.equals(otherUsage)) ? -1 : 1;
				if (nPos >= 0 && nPos < getTypes().size())
				{
					rlLast.setCombinedProcessIndex(nPos);
				}
			}

			return rl;

		}

		/**
		 * get the link that is selected by a given CombinedProcessIndex<br>
		 * links with no CombinedProcessIndex are included in the list
		 *
		 * @param combinedProcessIndex the nTh occurence of the CombinedProcessIndex field, -1 if all valid positions are ok
		 * @param skip
		 * @return
		 *
		 * @default getLinksForCombinedProcessIndex(-1)
		 */
		public JDFResourceLink getLinkForCombinedProcessIndex(final int combinedProcessIndex, final int skip)
		{
			final VElement v = getLinksForCombinedProcessIndex(combinedProcessIndex);
			return (JDFResourceLink) ((v != null && v.size() > skip) ? v.get(skip) : null);
		}

		/**
		 * get the links that are selected by a given CombinedProcessIndex<br>
		 * all links with no CombinedProcessIndex are included in the list
		 *
		 * @param combinedProcessIndex the nTh occurence of the CombinedProcessIndex field, -1 if all valid positions are ok
		 * @return
		 *
		 * @default getLinksForCombinedProcessIndex(-1)
		 */
		public VElement getLinksForCombinedProcessIndex(final int combinedProcessIndex)
		{
			final EnumType typ = getEnumType();
			if (!EnumType.Combined.equals(typ) && !EnumType.ProcessGroup.equals(typ))
			{
				return null;
			}

			final JDFAttributeMap attMap = new JDFAttributeMap();
			if (usage != null)
			{
				attMap.put(AttributeName.USAGE, usage);
			}

			final VElement vLinks = getResourceLinks(linkName, attMap, null);
			if (vLinks == null)
			{
				return null;
			}

			final String indexString = StringUtil.formatInteger(combinedProcessIndex);
			// loop over all links
			for (int i = vLinks.size() - 1; i >= 0; i--)
			{
				final JDFResourceLink rl = (JDFResourceLink) vLinks.elementAt(i);
				if (rl.hasAttribute(AttributeName.COMBINEDPROCESSINDEX)
						&& !rl.includesMatchingAttribute(AttributeName.COMBINEDPROCESSINDEX, indexString, AttributeInfo.EnumAttributeType.IntegerList))
				{
					vLinks.remove(i);
				}
			}
			return vLinks;
		}

		/**
		 * get the links that are selected by a given CombinedProcessIndex<br>
		 * all links with no CombinedProcessIndex are included in the list
		 *
		 * @param type the process type for which to get the links
		 * @return
		 *
		 * @default getLinksForType(type, -1)
		 */
		public VElement getLinksForType(final EnumType type)
		{
			final EnumType typ = getEnumType();
			if (typ == null)
			{
				return null;
			}
			// not combined, simpy get links from entire node
			// no links here at all
			final JDFAttributeMap attMap = new JDFAttributeMap();
			if (usage != null)
			{
				attMap.put(AttributeName.USAGE, usage);
			}

			if (typ.equals(type))
			{
				return getResourceLinks(linkName, attMap, null);
			}

			// nasty - mismatching type attribute
			if (!typ.equals(EnumType.Combined) && !typ.equals(EnumType.ProcessGroup))
			{
				return null;
			}

			// no types - this is a corrupt node
			final Vector<EnumType> vTypes = getEnumTypes();
			if (vTypes == null)
			{
				return null;
			}
			final int typSize = vTypes.size();

			final VElement vLinks = getResourceLinks(linkName, attMap, null);
			if (vLinks == null)
			{
				return null;
			}

			// loop over all links and remove non-matching entries
			for (int iLink = vLinks.size() - 1; iLink >= 0; iLink--)
			{
				final JDFResourceLink rl = (JDFResourceLink) vLinks.elementAt(iLink);
				final JDFIntegerList cpi = rl.getCombinedProcessIndex();
				if (cpi != null) // there is a cpi, check if it matches
				{
					final int size = cpi.size();
					boolean bFound = false;
					// loop over indeces of CombinedProcessIndex
					for (int j = 0; j < size; j++)
					{
						final int index = cpi.getInt(j);
						if (index < typSize) // the index points to a vaild position
						// in the list
						{
							final EnumType cpiType = vTypes.elementAt(index);
							if (cpiType.equals(type))
							{
								if (nType < 0) // flag not to check which ocurrence
								{
									bFound = true;
								}
								else
								{
									int nFound = -1;
									for (int k = 0; k <= index; k++) // count occurences of this process type in front of and including this
									{
										final EnumType cpiTypeCount = vTypes.elementAt(k);
										if (cpiTypeCount.equals(type))
										{
											nFound++;
										}
									}
									bFound = nFound == nType;
									if (bFound)
									{
										break;
									}
								}
							}
						}
					}
					// found non matching cpi - remove link
					if (!bFound)
					{
						vLinks.remove(iLink);
					}
				}
			}
			return vLinks.isEmpty() ? null : vLinks;
		}

		/**
		 * @param nPos the nTh occurence of the Type field, -1 if all valid positions are ok
		 */
		public void setNPos(final int nPos)
		{
			nType = nPos;
		}

		/**
		 * @param usage the usage of the requested link
		 */
		public void setUsage(final EnumUsage usage)
		{
			this.usage = usage;
		}

		/**
		 * @param b if true always ensure that the link exists both in and out
		 */
		public void setBoth(final boolean b)
		{
			this.linkBoth = b;
		}

		/**
		 * @param linkName the name of the requested link
		 */
		public void setLinkName(String linkName)
		{
			if (!linkName.endsWith(JDFConstants.LINK))
			{
				linkName += JDFConstants.LINK;
			}
			this.linkName = linkName;
		}

		/**
		 * @return the name of the requested resource
		 */
		public String getResName()
		{
			return StringUtil.leftStr(linkName, -4);
		}

	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	private class PartStatusHelper
	{

		/**
		 */
		protected PartStatusHelper()
		{
			super();
		}

		/**
		 * set the node's partition status if nodeinfo is partitioned, all leaves NodeStati below part are removed
		 *
		 * @param mattr Attribute map of partition
		 * @param status Status to set
		 * @param statusDetails
		 * @return boolean: success or not
		 */
		public boolean setPartStatus(final JDFAttributeMap mattr, final JDFElement.EnumNodeStatus status, String statusDetails)
		{
			statusDetails = StringUtil.getNonEmpty(statusDetails);
			// 100602 handle nasty combination
			if (mattr != null && (!mattr.isEmpty() && (status.equals(JDFElement.EnumNodeStatus.Pool) || status.equals(JDFElement.EnumNodeStatus.Part))))
			{
				// throw an exception??? this is a snafu to set an individual part status to
				// pool
				return false;
			}

			if (mattr == null || mattr.isEmpty())
			{
				return setRootStatus(status, statusDetails);
			}

			if (getVersion(true).getValue() < JDFElement.EnumVersion.Version_1_3.getValue())
			{
				setPoolStatus(mattr, status, statusDetails);
			}
			else
			{
				setPartitionedStatus(mattr, status, statusDetails);
			}
			return true;
		}

		/**
		 * @param mattr
		 * @param status
		 * @param statusDetails
		 */
		private void setPartitionedStatus(final JDFAttributeMap mattr, final JDFElement.EnumNodeStatus status, final String statusDetails)
		{
			// version >=1.3
			JDFNodeInfo ni = getCreateNodeInfo();
			ni = (JDFNodeInfo) ni.getResourceRoot(); // 101104 RP in case we set to a non linked resource, this avoids exceptions
			if (!JDFElement.EnumNodeStatus.Part.equals(getStatus()))
			{ // set a decent default status for implicit
				ni.setNodeStatus(getStatus());
				if (statusDetails != null)
				{
					ni.setNodeStatusDetails(statusDetails);
				}
			}

			final Collection<JDFNodeInfo> niLeaves = getPartitionsForMap(mattr, ni);
			for (final JDFNodeInfo niLeaf : niLeaves)
			{
				if (niLeaf != null)
				{
					niLeaf.removeAttributeFromLeaves(AttributeName.NODESTATUS, null);
					niLeaf.setNodeStatus(status);
					if (statusDetails != null)
					{
						niLeaf.removeAttributeFromLeaves(AttributeName.NODESTATUSDETAILS, null);
						niLeaf.setNodeStatusDetails(statusDetails);
					}
				}
			}
			setStatus(JDFElement.EnumNodeStatus.Part);
		}

		/**
		 * @param mattr
		 * @param ni
		 * @return
		 */
		private Collection<JDFNodeInfo> getPartitionsForMap(final JDFAttributeMap mattr, final JDFNodeInfo ni)
		{
			final JDFResource niRoot = ni.getResourceRoot();
			final Collection<JDFNodeInfo> leaves = new ArrayList<>();
			niRoot.setPartUsage(JDFResource.EnumPartUsage.Implicit);
			JDFNodeInfo niLeaf;
			niLeaf = (JDFNodeInfo) ni.getPartition(mattr, EnumPartUsage.Explicit);
			if (niLeaf == null) // no preexisting matching partition - attempt to create it
			{
				final VElement l2 = ni.getPartitionVector(mattr, EnumPartUsage.Explicit);
				if (!ContainerUtil.isEmpty(l2))
				{
					for (final KElement e : l2)
					{
						leaves.add((JDFNodeInfo) e);
					}
				}
				else
				{
					try
					{
						niLeaf = (JDFNodeInfo) ni.getCreatePartition(mattr, null);
						leaves.add(niLeaf);
					}
					catch (final Exception x)
					{
						// nop
					}
				}
			}
			else
			{
				leaves.add(niLeaf);
			}
			return leaves;
		}

		/**
		 * @param mattr
		 * @param status
		 * @param statusDetails
		 */
		private void setPoolStatus(final JDFAttributeMap mattr, final JDFElement.EnumNodeStatus status, final String statusDetails)
		{
			// we are setting an individual attribute
			final JDFStatusPool statusPool = getCreateStatusPool();
			final EnumNodeStatus stat = getStatus();

			if (!stat.equals(JDFElement.EnumNodeStatus.Pool))
			{
				statusPool.setStatus(stat);
				setStatus(JDFElement.EnumNodeStatus.Pool);
			}

			statusPool.setStatus(mattr, status, statusDetails);

			// this can happen if status = the previous status
			// just remove the pool and reset the status to the original status

			if (statusPool.numChildElements(ElementName.PARTSTATUS, null) == 0)
			{
				setStatus(status);
				if (statusDetails != null)
				{
					setStatusDetails(statusDetails);
				}
				statusPool.deleteNode();
			}
		}

		/**
		 * @param status
		 * @param statusDetails
		 * @return
		 */
		private boolean setRootStatus(final JDFElement.EnumNodeStatus status, final String statusDetails)
		{
			setStatus(status);
			if (statusDetails != null)
			{
				setStatusDetails(statusDetails);
			}
			removeChild(ElementName.STATUSPOOL, null, 0);
			if (getVersion(true).getValue() >= JDFElement.EnumVersion.Version_1_3.getValue())
			{
				final JDFNodeInfo ni = getNodeInfo();
				if (ni != null)
				{
					ni.removeAttributeFromLeaves(AttributeName.NODESTATUS, null);
					ni.setNodeStatus(status);
					if (statusDetails != null)
					{
						ni.removeAttributeFromLeaves(AttributeName.NODESTATUSDETAILS, null);
						ni.setNodeStatusDetails(statusDetails);
					}
				}
			}
			return true;
		}

		/**
		 * sets the node's partition status and StatusDetails
		 *
		 * @param vmattr vector Attribute maps of partition
		 * @param status Status to set
		 * @param statusDetails
		 * @return boolean: success or not
		 */
		public boolean setPartStatus(final VJDFAttributeMap vmattr, final EnumNodeStatus status, final String statusDetails)
		{
			boolean bRet = true;
			if (!ContainerUtil.isEmpty(vmattr))
			{
				for (final JDFAttributeMap map : vmattr)
				{
					bRet = setPartStatus(map, status, statusDetails) && bRet;
				}
			}
			else
			{
				bRet = setPartStatus((JDFAttributeMap) null, status, statusDetails);
			}

			return bRet;
		}
	}

	static class ResPart
	{
		ResPart(final JDFResource res)
		{
			super();
			this.res = res;
			this.part = res == null ? null : res.getAttributeMap();
		}

		private final JDFResource res;
		private final JDFAttributeMap part;

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + Objects.hash(part, res);
			return result;
		}

		@Override
		public boolean equals(final Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final ResPart other = (ResPart) obj;
			return Objects.equals(part, other.part) && Objects.equals(res, other.res);
		}

		@Override
		public String toString()
		{
			return "ResPart [res=" + res + ", part=" + part + "]";
		}
	}

	/**
	 * synchronization of stati based on child jdf node status
	 *
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 *
	 *         July 17, 2009
	 */
	public class StatusSynch
	{
		/**
		 * update the parent node
		 */
		public void update()
		{
			update(JDFNode.this);
		}

		private void update(final JDFNode n)
		{
			final VElement vNodes = n.getvJDFNode(null, null, true);
			if (vNodes != null && !vNodes.isEmpty())
			{
				final VJDFAttributeMap vMap = getPartVector(n);
				// recurse down first so we have updated kids
				for (int i = 0; i < vNodes.size(); i++)
				{
					final JDFNode kid = (JDFNode) vNodes.get(i);
					update(kid);

				}
				// now compare by part
				for (int ii = 0; ii < vMap.size(); ii++)
				{
					boolean ciao = vNodes.size() == 0;
					final JDFAttributeMap partMap = vMap.get(ii);
					EnumNodeStatus minStatus = EnumNodeStatus.Aborted;
					EnumNodeStatus maxStatus = EnumNodeStatus.Waiting;
					for (int i = 0; i < vNodes.size(); i++)
					{
						final JDFNode kid = (JDFNode) vNodes.get(i);
						minStatus = (EnumNodeStatus) EnumUtil.min(minStatus, kid.getPartStatus(partMap, -1));
						if (minStatus == null)
						{
							ciao = true;
							break;
						}
						maxStatus = (EnumNodeStatus) EnumUtil.max(maxStatus, kid.getPartStatus(partMap, 1));
					}
					if (!ciao)
					{
						final EnumNodeStatus synchStatus = getSynchStatus(minStatus, maxStatus);
						if (synchStatus != null)
						{
							if (EnumUtil.aLessThanB(n.getPartStatus(partMap, 0), synchStatus))
							{
								n.setPartStatus(partMap, synchStatus, null);
							}
						}
					}
				}
			}
		}

		/**
		 * @param minStatus
		 * @param maxStatus
		 * @return
		 */
		private EnumNodeStatus getSynchStatus(final EnumNodeStatus minStatus, final EnumNodeStatus maxStatus)
		{
			EnumNodeStatus synchStatus = null;
			if (minStatus.equals(maxStatus))
			{
				synchStatus = minStatus;
			}
			else if (EnumUtil.aLessEqualsThanB(minStatus, EnumNodeStatus.Ready))
			{
				if (EnumUtil.aLessEqualsThanB(EnumNodeStatus.InProgress, maxStatus))
				{
					synchStatus = EnumNodeStatus.InProgress;
				}
				else
				{
					synchStatus = maxStatus;
				}
			}
			else
			{
				synchStatus = minStatus;
			}

			return synchStatus;
		}

		/**
		 * @param n
		 * @return
		 */
		private VJDFAttributeMap getPartVector(final JDFNode n)
		{
			VJDFAttributeMap vParts = n.getNodeInfoPartMapVector();
			if (vParts == null)
			{
				vParts = new VJDFAttributeMap();
				vParts.add(null);
			}
			return vParts;
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 *
	 *         Aug 10, 2009
	 */
	// **************************************** Constructors
	public static class CombinedProcessIndexHelper
	{
		/**
		 * @param jdfResource
		 * @param usage
		 * @param processUsage
		 * @param resourceLink
		 * @param types
		 */
		public static void generateCombinedProcessIndex(final JDFResource jdfResource, final EnumUsage usage, final EnumProcessUsage processUsage, final JDFResourceLink resourceLink, final VString types)
		{
			if (resourceLink == null || jdfResource == null || resourceLink instanceof JDFPartAmount)
				return;

			final JDFIntegerList cpi = new JDFIntegerList();
			final String resName = jdfResource.getLocalName();
			final int typSize = types.size();
			LinkInfo linkInfoLast = null;
			for (int i = 0; i < typSize; i++)
			{
				boolean bAddCPI = false;
				final EnumType typ = EnumType.getEnum(types.get(i));
				final LinkInfoMap linkInfoMap = LinkValidatorMap.getLinkValidatorMap().getTypeMap(typ, true);
				final LinkInfo linkInfo = linkInfoMap == null ? null : linkInfoMap.getStar(resName);
				if (linkInfo != null)
				{
					// if we already added a cpi, but this is an exchange resource, only set cpi for
					// the last one
					boolean bMatchUsage = false;

					for (int ti = 0; ti < linkInfo.size(); ti++)
					{
						if (linkInfo.matchesUsage(ti, usage))
						{
							if (processUsage == null || processUsage.getName().equals(linkInfo.getProcessUsage(ti)))
							{
								bMatchUsage = true;
								bAddCPI = true;
								break; // one match is enough!
							}
						}
					}
					if (bMatchUsage && linkInfoLast != null)
					{
						bAddCPI = cleanCombinedProcessIndex(usage, linkInfo, cpi, linkInfoLast, bAddCPI);
					}
					if (bAddCPI)
					{
						cpi.add(i);
					}
					linkInfoLast = linkInfo;
				}
				else
				{
					linkInfoLast = null;
				}
			}
			if (cpi.size() > 0)
			{
				resourceLink.setCombinedProcessIndex(cpi);
			}
		}

		private static boolean cleanCombinedProcessIndex(final EnumUsage usage, final LinkInfo linkInfo, final JDFIntegerList cpi, final LinkInfo linkInfoLast, boolean bAddCPI)
		{
			boolean bOut = linkInfoLast.hasOutput(null);
			if (!bOut)
				return bAddCPI;

			final boolean bIn = linkInfo.hasInput(null);
			bOut = linkInfo.hasOutput(null);

			if (bIn && bOut)
			{ // remove the last output if we found a pass through
				if (EnumUsage.Input.equals(usage))
				{
					bAddCPI = false;
				}
				else
				{
					cpi.removeElementAt(-1);
					bAddCPI = true;
				}
			}
			else
			{
				// not continuous - reset
				bAddCPI = true;
			}
			return bAddCPI;
		}
	}

	/**
	 * Enumeration for accessing typesafe nodes
	 */
	public static final class EnumProcessUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;

		private static int m_startValue = 0;

		protected EnumProcessUsage(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumProcessUsage getEnum(final String enumName)
		{
			return (EnumProcessUsage) getEnum(EnumProcessUsage.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumProcessUsage getEnum(final int enumValue)
		{
			return (EnumProcessUsage) getEnum(EnumProcessUsage.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumProcessUsage.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumProcessUsage.class);
		}

		/**
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		public static Iterator iterator()
		{
			return iterator(EnumProcessUsage.class);
		}

		/**
		 *
		 */
		public static final EnumProcessUsage AnyInput = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_ANYINPUT);
		/**
		 *
		 */
		public static final EnumProcessUsage AnyOutput = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_ANYOUTPUT);
		/**
		 * @deprecated use null instead
		 */
		@Deprecated
		public static final EnumProcessUsage Any = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_ANY);
		/** * */
		public static final EnumProcessUsage Rejected = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_REJECTED);
		/** * */
		public static final EnumProcessUsage Accepted = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_ACCEPTED);
		/** * */
		public static final EnumProcessUsage Application = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_APPLICATION);
		/** * */
		public static final EnumProcessUsage Marks = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_MARKS);
		/** * */
		public static final EnumProcessUsage Document = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_DOCUMENT);
		/** * */
		public static final EnumProcessUsage Surface = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_SURFACE);
		/** * */
		public static final EnumProcessUsage Waste = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_WASTE);
		/** * */
		public static final EnumProcessUsage ThumbNail = new EnumProcessUsage("ThumbNail");
		/** * */
		public static final EnumProcessUsage Proof = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_PROOF);
		/** * */
		public static final EnumProcessUsage Input = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_INPUT);
		/** * */
		public static final EnumProcessUsage Plate = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_PLATE);
		/** * */
		public static final EnumProcessUsage Cylinder = new EnumProcessUsage("Cylinder");
		/** * */
		public static final EnumProcessUsage Good = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_GOOD);
		/** * */
		public static final EnumProcessUsage Core = new EnumProcessUsage("Core");
		/** * */
		public static final EnumProcessUsage Cover = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_COVER);
		/** * */
		public static final EnumProcessUsage BookBlock = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_BOOKBLOCK);
		/** * */
		public static final EnumProcessUsage Box = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_BOX);
		/** * */
		public static final EnumProcessUsage CoverMaterial = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_COVERMATERIAL);
		/** * */
		public static final EnumProcessUsage SpineBoard = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_SPINEBOARD);
		/** * */
		public static final EnumProcessUsage CoverBoard = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_COVERBOARD);
		/** * */
		public static final EnumProcessUsage Case = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_CASE);
		/** * */
		public static final EnumProcessUsage EndCustomer = new EnumProcessUsage("EndCustomer");
		/** * */
		public static final EnumProcessUsage FrontEndSheet = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_FRONTENDSHEET);
		/** * */
		public static final EnumProcessUsage BackEndSheet = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_BACKENDSHEET);
		/** * */
		public static final EnumProcessUsage Child = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_CHILD);
		/** * */
		public static final EnumProcessUsage Mother = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_MOTHER);
		/** * */
		public static final EnumProcessUsage Jacket = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_JACKET);
		/** * */
		public static final EnumProcessUsage Book = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_BOOK);
		/** * */
		public static final EnumProcessUsage Label = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_LABEL);
		/** * */
		public static final EnumProcessUsage RingBinder = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_RINGBINDER);
		/** * */
		public static final EnumProcessUsage Ancestor = new EnumProcessUsage(JDFConstants.PROCESSUSAGE_ANCESTOR);
		/**
		 * added for MISPre ICS
		 */
		public static final EnumProcessUsage Paper = new EnumProcessUsage("Paper");
	}

	/**
	 * constants EnumProcessUsage use EnumProcessUsage.xxx instead of the deprecated constants EnumProcessUsage.ProcessUsage_xxx
	 */
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_AnyInput = EnumProcessUsage.AnyInput;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_AnyOutput = EnumProcessUsage.AnyOutput;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Any = EnumProcessUsage.Any;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Rejected = EnumProcessUsage.Rejected;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Accepted = EnumProcessUsage.Accepted;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Marks = EnumProcessUsage.Marks;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Document = EnumProcessUsage.Document;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Surface = EnumProcessUsage.Surface;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Waste = EnumProcessUsage.Waste;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Proof = EnumProcessUsage.Proof;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Input = EnumProcessUsage.Input;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Plate = EnumProcessUsage.Plate;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Good = EnumProcessUsage.Good;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Cover = EnumProcessUsage.Cover;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_BookBlock = EnumProcessUsage.BookBlock;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Box = EnumProcessUsage.Box;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_CoverMaterial = EnumProcessUsage.CoverMaterial;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_SpineBoard = EnumProcessUsage.SpineBoard;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_CoverBoard = EnumProcessUsage.CoverBoard;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Case = EnumProcessUsage.Case;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_FrontEndSheet = EnumProcessUsage.FrontEndSheet;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_BackEndSheet = EnumProcessUsage.BackEndSheet;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Child = EnumProcessUsage.Child;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Mother = EnumProcessUsage.Mother;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Jacket = EnumProcessUsage.Jacket;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Book = EnumProcessUsage.Book;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Label = EnumProcessUsage.Label;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_RingBinder = EnumProcessUsage.RingBinder;
	/** @deprecated use EnumProcessUsage.xxx */
	@Deprecated
	public static final EnumProcessUsage ProcessUsage_Ancestor = EnumProcessUsage.Ancestor;

	// **************************************** Methods
	// *********************************************
	/**
	 * toString - StringRepresentation of JDFNode
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFNode[ --> " + super.toString() + " ]";
	}

	/**
	 * init - init the node
	 *
	 * @return boolean: always true
	 */
	@Override
	public boolean init()
	{
		if (hasAttribute(AttributeName.ID))
		{
			return false; // has previously been initialized
		}

		appendAnchor(null);
		// 080612 moved from root only to all nodes
		ensureCreated();
		if (isJDFRoot())
		{
			// create a standard JDFRoot with namespace, version, comment and audit pool
			addNameSpace(null, getSchemaURL());
			setVersion(getDefaultJDFVersion());

			String comment = "Generated by the CIP4 Java open source JDF Library version : ";
			comment += JDFAudit.software();
			if (getOwnerDocument_KElement().getXMLComment() == null && getOwnerDocument_KElement().getRoot() != null)
				setXMLComment(comment, true);

		}
		ensureJobPartID();
		setStatus(JDFElement.EnumNodeStatus.Waiting);
		return true;
	}

	public String ensureJobPartID()
	{
		if (!hasNonEmpty(AttributeName.JOBPARTID))
		{
			final String jpid = generateDotID(AttributeName.JOBPARTID, null);
			setJobPartID(jpid);
		}
		return getJobPartID(false);
	}

	/**
	 * is this the JDF root element, i.e. it has no JDF above it
	 *
	 * @return true, if this is a root
	 */
	public boolean isJDFRoot()
	{
		final KElement e = getParentNode_KElement();
		if (e == null)
		{
			return true;
		}
		return !(e instanceof JDFNode);
	}

	/**
	 * definition of resource link names in the JDF namespace
	 *
	 * @return String list of resource names that may be linked
	 */
	public VString linkNames()
	{
		return new LinkValidator(this).linkNames();
	}

	/**
	 * definition of resource link usage, cardinality and ProcessUsage in the JDF namespace
	 *
	 * @return String list of resource information usages that may be linked
	 * @deprecated
	 */
	@Deprecated
	public VString linkInfo()
	{
		final LinkInfoMap linkInfos = new LinkValidator(this).getLinkInfoMap();
		final VString v = new VString();
		if (linkInfos != null)
		{
			for (final LinkInfo linkInfo : linkInfos.values())
			{
				v.add(linkInfo.getString());
			}
		}
		return v;
	}

	public LinkInfoMap getLinkInfoMap()
	{
		return new LinkValidator(this).getLinkInfoMap();
	}

	// ////////////////////////////////////////////////////////////////////

	// --------------------------------------------------------------------------

	/**
	 * sets the node's partition status and StatusDetails
	 *
	 * @param vmattr vector Attribute maps of partition
	 * @param status Status to set
	 * @return boolean: success or not
	 * @deprecated use 3 parameter version
	 */
	@Deprecated
	public boolean setPartStatus(final VJDFAttributeMap vmattr, final EnumNodeStatus status)
	{
		return setPartStatus(vmattr, status, null);
	}

	/**
	 * sets the node's partition status and StatusDetails
	 *
	 * @param vmattr vector Attribute maps of partition
	 * @param status Status to set
	 * @param statusDetails
	 * @return boolean: success or not
	 */
	public boolean setPartStatus(final VJDFAttributeMap vmattr, final EnumNodeStatus status, final String statusDetails)
	{
		return new PartStatusHelper().setPartStatus(vmattr, status, statusDetails);
	}

	/**
	 * set the node's partition status if nodeinfo is partitioned, all leaves NodeStati below part are removed
	 *
	 * @param mattr Attribute map of partition
	 * @param status Status to set
	 * @return boolean: success or not
	 * @deprecated use 3 parameter version
	 */
	@Deprecated
	public boolean setPartStatus(final JDFAttributeMap mattr, final JDFElement.EnumNodeStatus status)
	{
		return setPartStatus(mattr, status, null);
	}

	/**
	 * set the node's partition status if nodeinfo is partitioned, all leaves NodeStati below part are removed
	 *
	 * @param mattr Attribute map of partition
	 * @param status Status to set
	 * @param statusDetails
	 * @return boolean: success or not
	 */
	public boolean setPartStatus(final JDFAttributeMap mattr, final JDFElement.EnumNodeStatus status, final String statusDetails)
	{
		return new PartStatusHelper().setPartStatus(mattr, status, statusDetails);
	}

	/**
	 * get the status for the vector v
	 *
	 * @param vMap the vevtor of partmaps
	 * @return the status, null if the value is not consistent
	 */
	public EnumNodeStatus getVectorPartStatus(final VJDFAttributeMap vMap)
	{
		if (vMap == null || vMap.size() == 0)
		{
			return getPartStatus(null, 0);
		}
		final EnumNodeStatus status = getPartStatus(vMap.elementAt(0), 0);
		if (status == null)
		{
			return null;
		}
		for (int i = 1; i < vMap.size(); i++)
		{
			final EnumNodeStatus status2 = getPartStatus(vMap.elementAt(i), 0);
			if (!status.equals(status2))
			{
				return null;
			}
		}
		return status;
	}

	/**
	 * get the statusdetails for the vector v
	 *
	 * @param vMap the vevtor of partmaps
	 * @return the status, null if the value is not consistent
	 */
	public String getVectorPartStatusDetails(final VJDFAttributeMap vMap)
	{
		if (vMap == null || vMap.size() == 0)
		{
			return getPartStatusDetails(null);
		}
		final String status = getPartStatusDetails(vMap.elementAt(0));
		if (status == null)
		{
			return null;
		}
		for (int i = 1; i < vMap.size(); i++)
		{
			final String status2 = getPartStatusDetails(vMap.elementAt(i));
			if (!status.equals(status2))
			{
				return null;
			}
		}
		return status;
	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	 * get the node's partition status
	 *
	 * @param mattr Attribute map of partition
	 * @return JDFElement.EnumNodeStatus: Status of the partition, null if no Status exists
	 * @deprecated us 2 parameter method
	 */
	@Deprecated
	public JDFElement.EnumNodeStatus getPartStatus(final JDFAttributeMap mattr)
	{
		return getPartStatus(mattr, 0);
	}

	/**
	 * get the node's partition status, even if the link does not match mattr
	 *
	 * @param attMap Attribute map of partition
	 * @param method : -1, 0 or 1; -1 min status; 0 equals, 1 max status
	 * @return JDFElement.EnumNodeStatus: Status of the partition, null if no Status exists
	 */
	public JDFElement.EnumNodeStatus getPartStatus(final JDFAttributeMap attMap, final int method)
	{
		EnumNodeStatus status = getStatus();
		if ((status != EnumNodeStatus.Pool) && (status != EnumNodeStatus.Part))
		{
			return status;
		}
		else if (status == EnumNodeStatus.Part)
		{
			final JDFNodeInfo niBase = getNodeInfo();
			if (niBase == null)
			{
				return null;
			}
			// this is required to compare lower partitions
			final int numParts = attMap == null ? 0 : attMap.size();
			JDFNodeInfo ni = (JDFNodeInfo) niBase.getPartition(attMap, null);
			if (ni == null)
			{
				ni = (JDFNodeInfo) niBase.getResourceRoot().getPartition(attMap, null);
			}
			if (ni == null)
			{
				return null;
			}
			JDFAttributeMap identicalSrcMap = null;
			if (attMap != null && !attMap.overlapMap(ni.getPartMap()))
			{
				final PartitionGetter partitionGetter = new PartitionGetter(niBase);
				partitionGetter.setFollowIdentical(false);
				final JDFNodeInfo identicalSrc = (JDFNodeInfo) partitionGetter.getPartition(attMap, null);
				if (identicalSrc != null)
					identicalSrcMap = identicalSrc.getPartMap();
			}
			status = null;

			final List<JDFResource> vLeaves = ni.getLeafArray(false);
			final int size = vLeaves.size();

			for (int i = 0; i < size; i++)
			{
				JDFNodeInfo niCmp = (JDFNodeInfo) vLeaves.get(i);
				JDFAttributeMap map = niCmp.getPartMap();
				if (identicalSrcMap != null)
					map.putAll(identicalSrcMap);
				if (map != null && !JDFPart.overlapPartMap(map, attMap, false))
				{
					continue;
				}
				while (niCmp != null) // also loop down in case kids are incorrectly set
				{
					final int mapParts = map == null ? 0 : map.size();
					final EnumNodeStatus nodeStatus = niCmp.getNodeStatus();
					if (!ContainerUtil.equals(nodeStatus, status))
					{
						if (status == null)
						{
							status = nodeStatus;
						}
						else if (nodeStatus == null || method == 0)
						{
							return null;
						}
						else if (method < 0)
						{
							status = (EnumNodeStatus) EnumUtil.min(status, nodeStatus);
						}
						else if (method > 0)
						{
							status = (EnumNodeStatus) EnumUtil.max(status, nodeStatus);
						}
					}
					final KElement parent = niCmp.getParentNode_KElement();
					// the map still has more partitions than the queried partmap - must loop down
					if (parent instanceof JDFNodeInfo && mapParts > numParts)
					{
						niCmp = (JDFNodeInfo) parent;
						map = niCmp.getPartMap();
					}
					else
					// we are closer to the root than our query, break downwards loop
					{
						niCmp = null;
					}
				}
			}
			if (status == null)
			{
				ni = (JDFNodeInfo) ni.getPartition(attMap, null);
				if (ni != null)
				{
					status = ni.getNodeStatus();
				}
			}
		}
		else if (status == EnumNodeStatus.Pool)
		{
			final JDFStatusPool statusPool = getStatusPool();
			if (statusPool == null)
			{
				return null;
			}
			status = statusPool.getStatus(attMap);
		}
		return status;
	}

	/**
	 * get the node's partition statusdetails
	 *
	 * @param mattr Attribute map of partition
	 *
	 * @return String: Status of the partition, null if no Status exists (note the null return!)
	 */
	public String getPartStatusDetails(final JDFAttributeMap mattr)
	{
		final EnumNodeStatus stat = getStatus();
		String statDetails = null;
		if ((stat != EnumNodeStatus.Pool) && (stat != EnumNodeStatus.Part))
		{
			return StringUtil.getNonEmpty(getStatusDetails());
		}
		else if (stat == EnumNodeStatus.Part)
		{
			JDFNodeInfo ni = getNodeInfo();
			if (ni == null)
			{
				return null;
			}
			ni = (JDFNodeInfo) ni.getPartition(mattr, null);
			if (ni == null)
			{
				return null;
			}
			statDetails = ni.getNodeStatusDetails();
			JDFAttributeMap identicalSrcMap = null;
			if (mattr != null && !mattr.overlapMap(ni.getPartMap()))
			{
				final PartitionGetter partitionGetter = new PartitionGetter(ni);
				partitionGetter.setFollowIdentical(false);
				final JDFNodeInfo identicalSrc = (JDFNodeInfo) partitionGetter.getPartition(mattr, null);
				if (identicalSrc != null)
					identicalSrcMap = identicalSrc.getPartMap();
			}

			final List<JDFResource> vLeaves = ni.getLeafArray(false);
			final int size = vLeaves.size();
			for (int i = 0; i < size; i++)
			{
				final JDFNodeInfo niCmp = (JDFNodeInfo) vLeaves.get(i);
				final JDFAttributeMap map = niCmp.getPartMap();
				if (identicalSrcMap != null)
					map.putAll(identicalSrcMap);
				if (map != null && !map.overlapMap(mattr))
				{
					continue;
				}

				if (!ContainerUtil.equals(statDetails, niCmp.getNodeStatusDetails()))
				{
					return null; // inconsistent
				}
			}
		}
		else if (stat == EnumNodeStatus.Pool)
		{
			final JDFStatusPool statusPool = getStatusPool();
			if (statusPool == null)
			{
				return null;
			}

			final JDFPartStatus ps = statusPool.getPartStatus(mattr);
			statDetails = ps == null ? null : StringUtil.getNonEmpty(ps.getStatusDetails());
		}

		return StringUtil.getNonEmpty(statDetails);
	}

	/**
	 * Set the Status and StatusDetails of this node update the PhaseTime audit or append a new phasetime as appropriate also generate a status JMF
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
	@Deprecated
	public JDFDoc setPhase(final EnumNodeStatus nodeStatus, final String nodeStatusDetails, final EnumDeviceStatus deviceStatus, final String deviceStatusDetails, final VJDFAttributeMap vPartMap)
	{
		final StatusCounter sc = new StatusCounter(this, vPartMap, null);
		sc.setPhase(nodeStatus, nodeStatusDetails, deviceStatus, deviceStatusDetails);
		return sc.getDocJMFPhaseTime();
	}

	/**
	 * return the partMapVector defined by nodeInfo partitioning null if nodeInfo is not partitioned or if the node status is neither pool nor part
	 *
	 * @return the vector of PartMaps
	 */
	public VJDFAttributeMap getStatusPartMapVector()
	{
		final EnumNodeStatus status = getStatus();
		if (EnumNodeStatus.Pool.equals(status))
		{
			final JDFStatusPool pool = getStatusPool();
			if (pool != null)
			{
				final VJDFAttributeMap vMap = new VJDFAttributeMap();
				final VElement vParts = pool.getPartStatusVector(null);
				for (int i = 0; i < vParts.size(); i++)
				{
					final JDFPartStatus ps = (JDFPartStatus) vParts.item(i);
					vMap.add(ps.getPartMap());
				}
				vMap.unify();
				return vMap;
			}
		}
		else if (EnumNodeStatus.Part.equals(status))
		{
			final JDFNodeInfo ni = getNodeInfo();
			if (ni != null)
			{
				return ni.getPartMapVector(true);
			}
		}

		return null; // nop
	}

	/**
	 * return the partMapVector defined in AncestorPool, null if no AncestorPool exists, or AncestorPool has no Part elements
	 *
	 * @return the vector of PartMaps
	 */
	@Override
	public VJDFAttributeMap getPartMapVector()
	{
		final JDFAncestorPool ancPool = getAncestorPool();
		if (ancPool != null)
		{
			return ancPool.getPartMapVector();
		}
		return null;
	}

	/**
	 * return the partMapVector defined in AncestorPool or NodeInfo or output resource in that sequence, null if no NodeInfo exists, or NodeInfo has no Part elements
	 *
	 * @return the vector of PartMaps
	 */
	public VJDFAttributeMap getNodeInfoPartMapVector()
	{
		VJDFAttributeMap parts = getPartMapVector();
		if (parts == null)
		{
			final JDFNodeInfo ni = getNodeInfo();
			parts = ni == null ? null : ni.getPartMapVector(false);
			if (parts == null)
			{
				final JDFResource output = getResource(null, EnumUsage.Output, null, 0);
				parts = output == null ? null : output.getPartMapVector(false);
			}

		}
		return ContainerUtil.isEmpty(parts) ? null : parts;
	}

	/**
	 * getActivation
	 *
	 * @deprecated 060406 use getActivation(false)
	 * @return EnumActivation
	 */
	@Deprecated
	public EnumActivation getActivation()
	{
		return getActivation(false);
	}

	/**
	 * get attribute Activation; defaults to Active
	 *
	 * @param bWalkThroughAncestors if true, walks through all ancestors which may overwrite the local activation state; if false only the explicit activation, if any, is returned
	 *
	 * @return the enumeration value of the attribute
	 */
	public JDFNode.EnumActivation getActivation(final boolean bWalkThroughAncestors)
	{
		EnumActivation res = null;
		if (bWalkThroughAncestors)
		{
			res = EnumActivation.Active;
			JDFNode p = this;
			while (p != null)
			{
				// walk through through all anchestors, to parent to grandparent to
				// grandgrandparent
				// and so on until root and compare the Activation state
				final EnumActivation a = EnumActivation.getEnum(p.getAttribute(AttributeName.ACTIVATION, null, null));
				if (a != null)
				{
					final int value = a.getValue();
					if ((value <= EnumActivation.TestRun.getValue()) || (res.getValue() < EnumActivation.Active.getValue()))
					{
						res = (value < res.getValue()) ? a : res; // smaller enums are inherited to alldescendants
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
				}
				p = (JDFNode) p.getParentNode_KElement();
			} // end while
		}
		else
		{
			res = EnumActivation.getEnum(getAttribute(AttributeName.ACTIVATION, null, null));
		}
		return res;
	}

	/**
	 * Set attribute Activation
	 *
	 * @param bActive the value to set the attribute to
	 */
	public void setActivation(final EnumActivation bActive)
	{
		setAttribute(AttributeName.ACTIVATION, bActive == null ? null : bActive.getName(), null);
	}

	/**
	 * addModified
	 *
	 * @param by
	 *
	 * @return JDFAudit
	 */
	public JDFAudit addModified(final String by)
	{
		return getCreateAuditPool().addModified(by, null);
	}

	/**
	 * Get the linked resource with name=strName
	 *
	 * @param strName the resource name
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param processUsage
	 * @param i the nuber of matches to skip, if negative, count backwards
	 * @return the matching resource, null if none matches
	 */
	public JDFResource getResource(final String strName, final EnumUsage usage, final EnumProcessUsage processUsage, final int i)
	{
		return getResource(strName, usage, processUsage == null ? null : processUsage.getName(), null, i);
	}

	/**
	 * Get the linked resource with name=strName
	 *
	 * @param strName the resource name
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param i the nuber of matches to skip, if negative, count backwards
	 * @return the matching resource, null if none matches
	 */
	public JDFResource getResourceRoot(final String strName, final EnumUsage usage, final int i)
	{
		final JDFAttributeMap map = usage == null ? null : new JDFAttributeMap(AttributeName.USAGE, usage);
		final JDFResourceLink rl = getLink(i, strName, map, null);
		return rl == null ? null : rl.getLinkRoot();
	}

	/**
	 * Get the linked resource with name=strName
	 *
	 * @param strName the resource name
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param i the number of matches to skip, if negative, count backwards
	 * @return the matching resource, null if none matches
	 */
	public JDFResource getResource(final String strName, final EnumUsage usage, final int i)
	{
		return getResource(strName, usage, null, null, i);
	}

	/**
	 * Get the linked resource with name=strName
	 *
	 * @param strName the resource name
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @return the matching resource, null if none matches
	 */
	public JDFResource getResource(final String strName, final EnumUsage usage)
	{
		return getResource(strName, usage, null, null, 0);
	}

	/**
	 * Get the linked resource with name=strName
	 *
	 * @param strName the resource name
	 * @return the matching resource, null if none matches
	 */
	public JDFResource getResource(final String strName)
	{
		return getResource(strName, null, null, null, 0);
	}

	/**
	 * Get the linked resource with name=strName
	 *
	 * @param strName the resource name if strName has a prefix, the explicit DOM level 1 resource with prefix will be searched
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param processUsage the processUsage of the respective resource
	 * @param i the nuber of matches to skip, if negative, count backwards
	 * @param namespaceURI if null and no prefix, assume JDF namespace, else correct lvl 2 handling
	 * @return the matching resource, null if none matches
	 * @deprecated
	 */
	@Deprecated
	public JDFResource getResource(final String strName, final EnumUsage usage, final EnumProcessUsage processUsage, final int i, final String namespaceURI)
	{
		return getResource(strName, usage, processUsage == null ? null : processUsage.getName(), namespaceURI, i);
	}

	/**
	 * Get the linked resource with name=strName
	 *
	 * @param strName the resource name if strName has a prefix, the explicit DOM level 1 resource with prefix will be searched
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param processUsage the processUsage of the respective resource
	 *
	 * @return the matching resource, null if none matches
	 */
	public JDFResource getCreateResource(final String strName, final EnumUsage usage, final String processUsage)
	{
		JDFResource r = getResource(strName, usage, processUsage, null, 0);
		if (r == null)
		{
			r = addResource(strName, usage);
			if (usage != null)
			{
				getLink(r, usage).setProcessUsage(processUsage);
			}
		}
		return r;
	}

	/**
	 * Get the resource link with name=strName - convenience
	 *
	 * @param strName the resource name if strName has a prefix, the explicit DOM level 1 resource with prefix will be searched
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param processUsage the processUsage of the respective resource
	 * @return the matching resource, null if none matches
	 */
	public JDFResourceLink getLink(final String strName, final EnumUsage usage, final String processUsage)
	{
		return getLink(strName, usage, processUsage, null, 0);
	}

	/**
	 * Get the resource link with name=strName
	 *
	 * @param strName the resource name if strName has a prefix, the explicit DOM level 1 resource with prefix will be searched
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param processUsage the processUsage of the respective resource
	 * @param i the nuber of matches to skip, if negative, count backwards
	 * @param namespaceURI if null and no prefix, assume JDF namespace, else correct lvl 2 handling
	 * @return the matching resource, null if none matches
	 */
	public JDFResourceLink getLink(final String strName, final EnumUsage usage, final String processUsage, final String namespaceURI, int i)
	{
		VElement velem = null;
		final JDFResourceLinkPool rlp = getResourceLinkPool();
		if (rlp != null)
		{
			velem = rlp.getInOutLinksExtended(usage, true, strName, processUsage, namespaceURI, false);
		}

		final int siz = velem == null ? 0 : velem.size();
		if (i < 0)
		{
			i += siz;
		}

		if (siz == 0 || i < 0 || i >= siz || velem == null)
		{
			return null;
		}

		return (JDFResourceLink) velem.elementAt(i);
	}

	/**
	 * Get the linked resource with name=strName
	 *
	 * @param strName the resource name if strName has a prefix, the explicit DOM level 1 resource with prefix will be searched
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param processUsage the processUsage of the respective resource
	 * @param i the nuber of matches to skip, if negative, count backwards
	 * @param namespaceURI if null and no prefix, assume JDF namespace, else correct lvl 2 handling
	 * @return the matching resource, null if none matches
	 */
	public JDFResource getResource(final String strName, final EnumUsage usage, final String processUsage, final String namespaceURI, final int i)
	{
		final JDFResourceLink rl = getLink(strName, usage, processUsage, namespaceURI, i);
		return rl == null ? null : rl.getTarget();
	}

	/**
	 * Get the linked resource with name=strName
	 *
	 * @param strName the resource name if strName has a prefix, the explicit DOM level 1 resource with prefix will be searched
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param processUsage the processUsage of the respective resource
	 * @param i the nuber of matches to skip, if negative, count backwards
	 * @param namespaceURI if null and no prefix, assume JDF namespace, else correct lvl 2 handling
	 * @return the matching resource, null if none matches
	 */
	public List<JDFResource> getResourceLeaves(final String strName, final EnumUsage usage)
	{
		return getResourceLeaves(strName, usage, null, null, 0);
	}

	/**
	 * Get the linked resource with name=strName
	 *
	 * @param strName the resource name if strName has a prefix, the explicit DOM level 1 resource with prefix will be searched
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param processUsage the processUsage of the respective resource
	 * @param i the nuber of matches to skip, if negative, count backwards
	 * @param namespaceURI if null and no prefix, assume JDF namespace, else correct lvl 2 handling
	 * @return the matching resource, null if none matches
	 */
	public List<JDFResource> getResourceLeaves(final String strName, final EnumUsage usage, final String processUsage, final String namespaceURI, final int i)
	{
		final JDFResourceLink rl = getLink(strName, usage, processUsage, namespaceURI, i);
		return rl == null ? null : rl.getTargetList();
	}

	/**
	 * Get the linked resource with name=strName; create it if it does not exist
	 *
	 * @param strName the resource name
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param i the nuber of matches to skip, if negative, count backwards
	 * @return the matching resource,
	 *
	 * @throws JDFException if resource does not exist and EnumUsage is null
	 */
	public JDFResource getCreateResource(final String strName, final EnumUsage usage, final int i)
	{
		JDFResource r = getResource(strName, usage, i);
		if (r == null)
		{
			r = addResource(strName, usage);
		}
		return r;
	}

	/**
	 * Get the linked resource with name=strName; create it if it does not exist
	 *
	 * @param strName the resource name
	 * @param usage the ResourceLink Usage, if null either in or out are accepted
	 * @param i the nuber of matches to skip, if negative, count backwards
	 * @return the matching resource,
	 *
	 * @throws JDFException if resource does not exist and EnumUsage is null
	 */
	public JDFResource getCreateResource(final String strName, final EnumUsage usage)
	{
		return getCreateResource(strName, usage, 0);
	}

	/**
	 * addResource - add a resource to resroot and link it to this process
	 *
	 * @param strName the localname of the resource
	 * @param resClass the JFD/@Class of the resource; if null, find from factory
	 * @param bInput if true, the resource is linked as input, else output
	 * @param resRoot the node where to add the Resource, if null defaults to this. Note that the link is always in this
	 * @param bLink if true, creat a ResourceLink to the newly created resource
	 * @param nameSpaceURI the nsURI of the resource, if null take the default ns
	 *
	 * @return JDFResource
	 * @deprecated use addResource(String strName, JDFResource.EnumResourceClass resClass, EnumUsage usage, EnumProcessUsage processUsage, JDFNode resRoot, String nameSpaceURI)
	 * @default addResource(name, null, bInput, null, true, null)
	 */
	@Deprecated
	public JDFResource addResource(final String strName, final JDFResource.EnumResourceClass resClass, final boolean bInput, final JDFNode resRoot, final boolean bLink, final String nameSpaceURI)
	{
		EnumUsage usage = null;
		if (bLink)
		{
			usage = bInput ? EnumUsage.Input : EnumUsage.Output;
		}
		return addResource(strName, resClass, usage, null, resRoot, nameSpaceURI, null);
	}

	/**
	 * addResource - add a resource to resroot and link it to this process utility with the minimal parameter set
	 *
	 * @param strName the localname of the resource
	 * @param usage the Usage attribute of the ResourceLink. If null, the resource is not linked
	 * @return JDFResource the new resource
	 *
	 * @default addResource(name, null, usage, null, null, null,null)
	 */
	public JDFResource addResource(final String strName, final EnumUsage usage)
	{
		return addResource(strName, null, usage, null, null, null, null);
	}

	/**
	 * addResource - add a resource to resroot and link it to this process
	 *
	 * @param strName the localname of the resource
	 * @param resClass the JFD/@Class of the resource; if null, find the resource class from factory
	 * @param usage the Usage attribute of the ResourceLink. If null, the resource is not linked
	 * @param processUsage the processUsage attribute of the link to the resource
	 * @param resRoot the node to add the Resource to, defaults to 'this' if null. Note that the link is always in 'this'
	 * @param nameSpaceURI the nsURI of the resource, if null take the default ns
	 * @param toReplace the resource to replace by this - also add a resource audit
	 * @return JDFResource
	 *
	 * @default addResource(name, null, usage, null, null, null,null)
	 */
	public JDFResource addResource(final String strName, JDFResource.EnumResourceClass resClass, final EnumUsage usage, final EnumProcessUsage processUsage, JDFNode resRoot, final String nameSpaceURI, final JDFResource toReplace)
	{
		if (resRoot == null)
		{
			resRoot = this;
		}

		final JDFResourcePool p = resRoot.getCreateResourcePool();
		final JDFResource r = p.appendResource(strName, null, nameSpaceURI);

		if (usage != null)
		{
			linkResource(r, usage, processUsage);
		}

		// if the factory already did a type safe class creation, use it instead
		final EnumResourceClass resClass2 = r.getResourceClass();
		if (resClass2 != null)
		{
			resClass = resClass2;
		}
		if (resClass != null)
		{
			r.setResourceClass(resClass);
		}

		// parameters and consumables are assumed to be available by default
		if (EnumUsage.Input.equals(usage)
				&& (EnumResourceClass.Parameter.equals(resClass) || EnumResourceClass.Consumable.equals(resClass) || EnumResourceClass.Intent.equals(resClass)))
		{
			r.setResStatus(EnumResStatus.Available, false);
		}
		else
		{
			r.setResStatus(JDFResource.EnumResStatus.Unavailable, false);
		}
		if (toReplace != null)
		{
			replaceUpdate(usage, toReplace, r);
		}
		return r;
	}

	protected void replaceUpdate(final EnumUsage usage, final JDFResource toReplace, final JDFResource r)
	{
		final JDFAuditPool auditPool = getCreateAuditPool();
		final JDFResourceAudit resourceAudit = auditPool.addResourceAudit(null);
		resourceAudit.addNewOldLink(true, r, usage);
		resourceAudit.addNewOldLink(false, toReplace, usage);
		final JDFResourceLinkPool resourceLinkPool = getResourceLinkPool();
		final VElement vRL = (resourceLinkPool == null) ? null : resourceLinkPool.getInOutLinks(usage, true, null, null);
		if (vRL != null)
		{
			for (int i = 0; i < vRL.size(); i++)
			{
				final JDFResourceLink l = (JDFResourceLink) vRL.elementAt(i);
				if (l.getTarget() == toReplace)
				{
					l.deleteNode();
				}
			}
		}
	}

	/**
	 * LinkResource: create a resourceLink in the resourceLinkPool that refers to the resource jdfResource also sets the appropriate combined process index
	 *
	 *
	 * @param jdfResource the resource or partition to link to
	 * @param input it true, link as input, else link as output
	 * @param bForce if true, create a new link, even if an existing link already exists
	 *
	 * @return JDFResourceLink the new link
	 * @deprecated use linkResource(enum)
	 * @default LinkResource(r, true, false)
	 */
	@Deprecated
	public JDFResourceLink linkResource(final JDFResource jdfResource, final boolean input, final boolean bForce)
	{
		boolean bForceLocal = bForce;

		if (bForceLocal)
		{
			bForceLocal = true;
		}

		return linkResource(jdfResource, input ? EnumUsage.Input : EnumUsage.Output, null);
	}

	/**
	 * LinkResource: create a resourceLink in the resourceLinkPool that refers to the resource jdfResource also sets the appropriate combined process index
	 *
	 *
	 * @param jdfResource the resource or partition to link to
	 * @param usage Usage of the resource
	 * @param processUsage processUsage of the resource
	 *
	 * @return JDFResourceLink the new link
	 *
	 * @default LinkResource(r, usage, null)
	 */
	public JDFResourceLink linkResource(final JDFResource jdfResource, final EnumUsage usage, final EnumProcessUsage processUsage)
	{
		if (jdfResource == null || usage == null)
		{
			return null;
		}

		final JDFResourceLinkPool resourceLinkPool = getCreateResourceLinkPool();
		final JDFResourceLink resourceLink = resourceLinkPool.linkResource(jdfResource, usage, processUsage);
		final VString types = getTypes();
		// generate
		if (resourceLink != null && types != null && !EnumResourceClass.Implementation.equals(jdfResource.getResourceClass()) && !(jdfResource instanceof JDFNodeInfo))
		{
			CombinedProcessIndexHelper.generateCombinedProcessIndex(jdfResource, usage, processUsage, resourceLink, types);
		}
		return resourceLink;
	}

	/**
	 *
	 * @param jdfResource
	 * @param usage
	 * @param processUsage
	 * @return
	 */
	public JDFResourceLink ensureLink(final JDFResource jdfResource, final EnumUsage usage, final EnumProcessUsage processUsage)
	{
		final String puName = processUsage == null ? null : processUsage.getName();
		return ensureLinkPU(jdfResource, usage, puName);
	}

	/**
	 * ensureLink: if it does not yet exist, create a resourceLink in the resourceLinkPool that refers to the resource jdfResource also sets the appropriate combined process index
	 *
	 *
	 * @param jdfResource the resource or partition to link to
	 * @param usage Usage of the resource
	 * @param processUsage processUsage of the resource
	 *
	 * @return JDFResourceLink the link
	 *
	 * @default ensureLink(r, usage, null)
	 */
	public JDFResourceLink ensureLinkPU(final JDFResource jdfResource, final EnumUsage usage, final String processUsage)
	{
		if (jdfResource == null)
		{
			return null;
		}
		final JDFAttributeMap m = new JDFAttributeMap();
		if (usage != null)
		{
			m.put(AttributeName.USAGE, usage);
		}
		m.put(AttributeName.RREF, jdfResource.getID());
		if (processUsage != null)
		{
			m.put(AttributeName.PROCESSUSAGE, processUsage);
		}
		JDFResourceLink rl = getLink(0, jdfResource.getNodeName(), m, null);
		if (rl == null)
		{
			if (processUsage != null)
			{
				m.remove(AttributeName.PROCESSUSAGE);
				rl = getLink(0, jdfResource.getNodeName(), m, null);
				if (rl != null)
				{
					rl.setProcessUsage(processUsage);
				}
			}
			if (rl == null && usage != null)
			{
				rl = linkResource(jdfResource, usage, null);
				rl.setProcessUsage(processUsage);
			}
		}
		return rl;
	}

	/**
	 * ensure that the linked resource and all referenced resources are correctly positioned
	 *
	 * @param r
	 */
	public void ensureValidRefsPosition(final JDFResource r)
	{
		if (r == null)
			return;
		// move the resource to the closest common ancestor if it is not already an
		// ancestor of this
		VElement refs = r.getvHRefRes(true, true);
		if (refs == null)
			refs = new VElement();
		refs.add(r);
		for (final KElement target : refs)
		{
			// move the resource to the closest common ancestor if it is not already an
			// ancestor of this
			final JDFResource res = ((JDFResource) target).getResourceRoot();
			ensureValidResPosition(res);
		}
	}

	/**
	 * @param res
	 */
	public void ensureValidResPosition(JDFResource res)
	{
		if (res == null)
			return;

		JDFNode parent = res.getParentJDF();
		while (parent != null && !parent.isAncestor(this))
		{
			parent = res.getParentJDF();
			if (parent == null)
			{
				break;
			}

			parent = parent.getParentJDF();
			if (parent == null)
			{
				throw new JDFException("JDFResourceLink ensureValidResPosition resource is not in the same document");
			}

			res = (JDFResource) parent.getCreateResourcePool().moveElement(res, null);
		}
	}

	/**
	 * get the resourcelinks in the resourcepool of this node
	 *
	 * @return VElement the vector of ResorceLinks:
	 * @deprecated use getResourceLinks(null)
	 */
	@Deprecated
	public VElement getResourceLinks()
	{
		return getResourceLinks(null);
	}

	/**
	 * get the resourcelinks in the resourcepool of this node
	 *
	 * @param mLinkAtt the map of attributes
	 *
	 * @return VElement - the vector of ResourceLinks, null if none exist:
	 */
	public VElement getResourceLinks(final JDFAttributeMap mLinkAtt)
	{
		final JDFResourceLinkPool resList = getResourceLinkPool();
		if (resList == null)
		{
			return null;
		}
		if (mLinkAtt == null)
		{
			final VElement v = new VElement();
			final List<JDFResourceLink> vrl = resList.getLinkArray();
			if (!ContainerUtil.isEmpty(vrl))
			{
				v.addAll(vrl);
				return v;
			}
			else
			{
				return null;
			}
		}
		else
		{
			// return contents as vector
			return resList.getPoolChildren(null, mLinkAtt, null);
		}
	}

	/**
	 * get the linked resources matching certain conditions<br>
	 * combines all linked resources from ResourceLinkPool, CustomerInfo, NodeInfo and AuditPool
	 *
	 * @param mResAtt map of Resource attributes to search for
	 * @param bFollowRefs true if internal references shall be followed
	 *
	 * @return vResource: vector with all elements matching the conditions
	 *
	 * @default getLinkedResources(null, false)
	 */
	public VElement getLinkedResources(final JDFAttributeMap mResAtt, final boolean bFollowRefs)
	{
		final JDFResourceLinkPool resourceLinkPool = getResourceLinkPool();
		VElement vLinkedResources = new VElement();
		if (resourceLinkPool != null)
		{
			vLinkedResources = resourceLinkPool.getLinkedResources(null, null, mResAtt, bFollowRefs, null);
		}

		final JDFAuditPool auditPool = getAuditPool();
		if (auditPool != null)
		{
			vLinkedResources.appendUnique(auditPool.getLinkedResources(null, true));
		}

		// only needed for the JDF 1.2 subelement, resources are handled
		// generically
		final JDFCustomerInfo customerInfo = (JDFCustomerInfo) getElement(ElementName.CUSTOMERINFO);
		if (customerInfo != null)
		{
			vLinkedResources.appendUnique(customerInfo.getLinkedResources(mResAtt, bFollowRefs));
		}

		// only needed for the JDF 1.2 subelement, resources are handled
		// generically
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
	 * get all the unlinked resources in this node<br>
	 * TODO: also include resources that are only linked by other unlinked resources
	 *
	 * @param bLocal if true, only in the local resourcepool, else also recurse into children
	 *
	 * @return vElement vector with all
	 * @deprecated - Use @see UnlinkFinder
	 */
	@Deprecated
	public VElement getUnlinkedResources(final boolean bLocal)
	{
		final JDFResourcePool resourcePool = getResourcePool();
		if (resourcePool == null)
		{
			return null;
		}
		VElement vUnlinkedResources = resourcePool.getUnlinkedResources();
		if (bLocal)
		{
			return vUnlinkedResources;
		}
		if (vUnlinkedResources == null)
		{
			vUnlinkedResources = new VElement();
		}
		final VElement children = getvJDFNode(null, null, true);
		for (int i = 0; i < children.size(); i++)
		{
			vUnlinkedResources.addAll(((JDFNode) children.elementAt(i)).getUnlinkedResources(bLocal));
		}
		return vUnlinkedResources.size() == 0 ? null : vUnlinkedResources;
	}

	/**
	 * get a vector of all direct predecessor or following nodes, depending on bPre
	 *
	 * @param bPre if true get predecessors, if false get following nodes
	 *
	 * @return Vector of pre / post decessor nodes
	 * @deprecated use getPredecessors(bPre,false);
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Vector getPredecessors(final boolean bPre)
	{
		return getPredecessors(bPre, false);
	}

	/**
	 * get a vector of all direct predecessor or following nodes, depending on bPre
	 *
	 * @param bPre if true get predecessors, if false get following nodes
	 * @param bDirect if true, only return the direct condidates
	 *
	 * @return Vector of pre / post decessor nodes
	 */
	public VElement getPredecessors(final boolean bPre, final boolean bDirect)
	{
		final HashSet<KElement> hashSet = new HashSet<>();
		final HashSet<ResPart> done = new HashSet<>();
		getPredecessorImpl(bPre, bDirect, hashSet, done);
		final VElement v = new VElement();
		v.addAll(hashSet);
		return v;
	}

	/**
	 *
	 *
	 * @param bPre
	 * @param bDirect
	 * @param h
	 * @param done
	 */
	void getPredecessorImpl(final boolean bPre, final boolean bDirect, final HashSet<KElement> h, final HashSet<ResPart> done)
	{
		final JDFResourceLinkPool rlp = getResourceLinkPool();

		// get either all input or output resources, depending on bPre
		final VElement vLoc = (rlp == null) ? null : rlp.getInOutLinks(bPre ? EnumUsage.Input : EnumUsage.Output, false, null, null);

		if (vLoc != null)
		{
			for (final KElement resElem : vLoc)
			{
				if (!(resElem instanceof JDFNodeInfo) && !(resElem instanceof JDFDevice))
				{
					checkPredecessorResource(bPre, bDirect, h, done, resElem);
				}
			}
		}
	}

	protected void checkPredecessorResource(final boolean bPre, final boolean bDirect, final HashSet<KElement> h, final HashSet<ResPart> done, final KElement resElem)
	{
		final JDFResource r = (JDFResource) resElem;
		final ResPart id = new ResPart(r);
		if (done.add(id))
		{
			// get all creator or consumer processes
			final VElement vNode = r.getCreator(bPre);

			if (vNode != null)
			{
				final JDFResourceLink rl = getLink(r, bPre ? EnumUsage.Input : EnumUsage.Output);
				if (rl != null)
				{
					checkPredecessorLink(bPre, bDirect, h, done, r, vNode, rl);
				}
			}
		}
	}

	void checkPredecessorLink(final boolean bPre, final boolean bDirect, final HashSet<KElement> h, final HashSet<ResPart> done, final JDFResource r, final VElement vNode, final JDFResourceLink rl)
	{
		final VJDFAttributeMap vMaps = rl.getPartMapVector();
		for (final KElement nodeElem : vNode)
		{
			final JDFNode p = (JDFNode) nodeElem;
			if (!h.contains(p) && p != this)
			{
				final JDFResourceLink rl2 = p.getLink(r, bPre ? EnumUsage.Output : EnumUsage.Input);
				if ((rl2 != null) && (vMaps == null || vMaps.overlapsMap(rl2.getPartMapVector())))
				{
					h.add(p);

					if (!bDirect)
					{
						p.getPredecessorImpl(bPre, bDirect, h, done);
					}
				}
			}
		}
	}

	/**
	 * isExecutable - checks whether a node is executable by checking the resources linked by the resourcelinkpool and @Status or NodeInfo/@NodeStatus and JDF/@Activation
	 *
	 * @param partMap the Attribute to check
	 * @param bCheckChildren if true, calculates the availability Status of a resource from all child partition leaves, else the Status is taken from the appropriate leaf itself
	 *
	 * @return boolean - true if the node is executable, false if not
	 *
	 *         default: isExecutable(null, true)
	 */

	/**
	 *
	 * @param partMap
	 * @param bCheckChildren
	 * @return
	 */
	public boolean isExecutable(final JDFAttributeMap partMap, final boolean bCheckChildren)
	{
		final JDFResourceLinkPool resourceLinkPool = getResourceLinkPool();
		if (resourceLinkPool == null)
		{
			return false;
		}
		final EnumNodeStatus status = getPartStatus(partMap, 0);
		if ((status != EnumNodeStatus.Waiting) && (status != EnumNodeStatus.Ready))
		{
			return false;
		}
		if (!fitsActivation(EnumActivation.Active, true))
		{
			return false;
		}

		final VElement v = resourceLinkPool.getPoolChildren(null, null, null);
		if (v != null)
		{
			for (final KElement e : v)
			{
				final JDFResourceLink rl = (JDFResourceLink) e;

				if (rl != null && !rl.isExecutable(partMap, bCheckChildren))
				{
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * gets the status of a certain partition of the node. The partition is given by a map of partition attributes or by a JDFResource object containing such a map.
	 *
	 * @param mattr
	 * @return
	 * @deprecated use getPartStatus()
	 */

	@Deprecated
	public JDFElement.EnumNodeStatus getProcessStatus(final JDFAttributeMap mattr)
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
	
	 *
	 */

	/**
	 * ResourceTypeEqual<br>
	 * Checks whether the given resources are of the same type. Resources are considered equal by this method if they have identical Class attributes and their resource type is
	 * equal. Basically the resource type is the node name.<br>
	 * Two resources with different node names are considered equal if their Type attributes from the ToolConfig.xml file are equal. This is not implemented yet. Instead of it is
	 * hard-coded that "RunList" and "HDM:ReportList" are of the same type.
	 *
	 * @param res1 first resource
	 * @param res2 second resource
	 * @return boolean
	 */
	static public boolean resourceTypeEqual(final JDFResource res1, final JDFResource res2)
	{
		final JDFResource.EnumResourceClass res1_class = res1.getResourceClass();
		final JDFResource.EnumResourceClass res2_class = res2.getResourceClass();

		if (!res1_class.equals(res2_class))
		{
			return false;
		}

		String res1_type = res1.getNodeName();
		String res2_type = res2.getNodeName();

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
	 *
	 * @param nodeType : node Type attribute
	 * @param active : Activation of the requesetd nodes, if null ignore activation
	 * @param bDirect : if true, get direct children only, else recurse down the tree and include this, i.e. return a complete tree starting at this
	 *
	 * @return: VElement of JDF nodes
	 *
	 *          default: getvJDFNode(null, JDFNode.EnumActivation.Unknown, false)
	 */

	/**
	 * Get a vector of all JDF children with type nodeType
	 *
	 * @param task node type
	 * @param active Activation of the requested nodes, if null ignore activation
	 * @param bDirect if true, get direct children only, else recurse down the tree and include this, i.e. return a complete tree starting at this
	 *
	 * @return VElement of JDF nodes
	 * @default getvJDFNode(null, null, false)
	 */
	public VElement getvJDFNode(final String task, final EnumActivation active, final boolean bDirect)
	{
		final VElement v = new VElement();
		final List<KElement> l = bDirect ? getTree(ElementName.JDF, null, null, bDirect, true) : getTree(ElementName.JDF);
		if (!bDirect && l.size() > 1)
		{
			final KElement e0 = l.remove(0);
			l.add(e0);
		}
		final boolean wantTask = !KElement.isWildCard(task);
		if (active == null && !wantTask)
		{
			v.addAll(l);
		}
		else
		{
			for (final KElement e : l)
			{
				final JDFNode p = (JDFNode) e;
				if (p.fitsActivation(active, true) && (!wantTask || p.getType().equals(task)))
				{
					v.addElement(p);
				}
			}
		}
		return v;
	}

	/**
	 * getvJDFNode
	 *
	 * @param task
	 * @param active
	 *
	 * @return Vector of JDFNodes
	 *
	 * @default getvJDFNode(null, false)
	 * @deprecated use public Vector getvJDFNode(task, JDFNode.EnumActivation.Unknown, false)
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Vector getvJDFNode(final String task, final boolean active)
	{
		return getvJDFNode(task, null, active);
	}

	/**
	 * isActive
	 *
	 * @deprecated use fitsActivation
	 *
	 * @return boolean
	 */
	@Deprecated
	public boolean isActive()
	{
		return fitsActivation(EnumActivation.Active, true);
	}

	/**
	 * @deprecated use fitsActivation
	 * @param bWalkThroughAnchestors
	 * @return boolean
	 */
	@Deprecated
	public boolean isActive(final boolean bWalkThroughAnchestors)
	{
		return fitsActivation(EnumActivation.Active, bWalkThroughAnchestors);
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * the activation state of this node
	 *
	 * @param active
	 * @param bWalkThroughAncestors if true, walks through all anchestors which may overwrite the local activation state
	 * @return boolean true if the activations are compatible
	 */
	public boolean fitsActivation(final EnumActivation active, final boolean bWalkThroughAncestors)
	{
		if (active == null)
		{
			return true;
		}
		final EnumActivation a = getActivation(bWalkThroughAncestors);
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
	 * removeNode - remove a node. If bLeaveSubmit is true, leave a stub with the id and status field
	 *
	 * @param bLeaveSubmit if true, leave a stub with id and status field
	 *
	 * @default removeNode(true)
	 * @deprecated
	 */
	@Deprecated
	public void removeNode(final boolean bLeaveSubmit)
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
	 * @param task
	 * @param tasks
	 * @deprecated use addJDFNode
	 * @return JDFNode
	 */
	@Deprecated
	public JDFNode addTask(final String task, final VString tasks)
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
	 * @param task
	 *
	 * @return JDFNode
	 * @deprecated use addJDFNode
	 */
	@Deprecated
	public JDFNode addTask(final String task)
	{
		return addTask(task, null);
	}

	/**
	 * setType set the type attribute to the enumeration type also set xsi:type etc
	 *
	 * @param typ the new type to set this to
	 */
	public void setType(final EnumType typ)
	{
		setType(typ == null ? null : typ.getName(), true);
	}

	/**
	 * setType set the type attribute to the string type
	 *
	 * @param newType the new type to set this to
	 * @param checkName if true, check whether this type exists and throw an exception if not
	 *
	 * @return ignore, always true
	 * @throws JDFException if type is not a known JDF type
	 * @default setType(newType, false)
	 */
	public boolean setType(final String newType, final boolean checkName)
	{
		final EnumType eTyp = EnumType.getEnum(newType);
		if (!checkName || eTyp != null)
		{
			removeAttribute("type", AttributeName.XSIURI);
			setAttribute(AttributeName.TYPE, newType, null);
			if (eTyp != null)
			{
				setXSIType(newType);
				if (!eTyp.equals(EnumType.Combined) && !eTyp.equals(EnumType.ProcessGroup))
				{
					removeAttribute(AttributeName.TYPES);
				}
			}
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
	 * getType - get node Types or Type attribute
	 *
	 * @return String - the type
	 */
	public String getTypesString()
	{
		final String s = getAttribute(AttributeName.TYPES, null, null);
		return s == null ? getType() : s;
	}

	// /////////////////////////////////////////////////////////////

	/**
	 * fix NodeInfo and CustomerInfo
	 *
	 * @param version target version
	 * @return
	 */
	public boolean fixNiCi(final EnumVersion version)
	{
		boolean bRet = true;

		// fix NodeInfo and CustomerInfo
		for (int i = 0; i < 2; i++)
		{
			final String nam = (i > 0) ? ElementName.NODEINFO : ElementName.CUSTOMERINFO;
			final String linkNam = nam + JDFConstants.LINK;
			if (version.getValue() >= EnumVersion.Version_1_3.getValue())
			{
				bRet = fixNiCiToResource(i, nam, linkNam);
			}
			else
			{ // move to version <= 1.2
				fixNiCiToElement(i, nam, linkNam);
			}
		}
		return bRet;
	}

	private void fixNiCiToElement(final int i, final String nam, final String linkNam)
	{
		final JDFResourceLinkPool rlp = getResourceLinkPool();
		if (rlp != null && rlp.hasChildElement(linkNam, null))
		{
			final JDFResourceLink rl = rlp.getPoolChild(0, linkNam, null, null);
			final JDFResource root = rl.getLinkRoot();
			final JDFElement e = (JDFElement) getCreateElement(nam);

			// not partitioned, simply copy into nodeinfo element
			if (!root.hasAttribute(AttributeName.PARTIDKEYS))
			{
				e.mergeElement(root, false);
				if (i == 1)
				{
					if (getStatus() == EnumNodeStatus.Part)
					{
						moveAttribute(AttributeName.STATUS, e, AttributeName.NODESTATUS, null, null);
						if (e.hasAttribute(AttributeName.NODESTATUSDETAILS))
						{
							moveAttribute(AttributeName.STATUSDETAILS, e, AttributeName.NODESTATUSDETAILS, null, null);
						}
					}
				}
			}
			else
			{ // partitioned nodeinfo or customerinfo handling
				if (i == 1)
				{ // copy nodeinfo stati into statuspool
					setStatus(EnumNodeStatus.Pool);
					final VElement vLeaves = root.getLeaves(false);
					final JDFStatusPool sp = getCreateStatusPool();
					sp.removeChildren(null, null, null);
					for (int j = 0; j < vLeaves.size(); j++)
					{
						final JDFNodeInfo ni = (JDFNodeInfo) vLeaves.elementAt(j);
						final JDFPartStatus ps = sp.appendPartStatus();
						ps.setPartMap(ni.getPartMap());
						ps.setAttribute(AttributeName.STATUS, ni.getAttribute(AttributeName.NODESTATUS));
						if (ni.hasAttribute(AttributeName.STATUSDETAILS))
						{
							ps.setAttribute(AttributeName.STATUSDETAILS, ni.getAttribute(AttributeName.NODESTATUSDETAILS));
						}
					}
				}
				// merge the most fitting resource partition into the
				// unpartitioned
				// nodeinfo or customerinfo
				final JDFResource target = rl.getTarget();
				target.removeChildren(target.getNodeName(), null, null);
				target.expand(false);
				e.mergeElement(target, false);
				final String partidkeys = root.getAttribute(AttributeName.PARTIDKEYS, null, null);
				if (partidkeys != null)
				{
					e.setAttribute(AttributeName.PARTIDKEYS, partidkeys);
				}
			}

			((JDFResource) e).cleanResourceAttributes();

			// ciao bello
			rl.deleteNode();
			root.deleteNode();
		}
	}

	private boolean fixNiCiToResource(final int i, final String nam, final String linkNam)
	{
		boolean bRet = true;
		final JDFResourceLinkPool rlp = getResourceLinkPool();
		if (hasChildElement(nam, null) || ((i == 1) && hasChildElement(ElementName.STATUSPOOL, null)))
		{
			final JDFElement e = (JDFElement) getElement(nam);
			// move nodeinfo or CustomerInfo into the resource
			if (rlp == null || !rlp.hasChildElement(linkNam, null))
			{
				final JDFResource r = addResource(nam, null, EnumUsage.Input, null, null, null, null);
				if (e != null)
				{
					r.mergeElement(e, false);
				}
				if (i == 1) // nodeinfo
				{
					JDFNodeInfo ni = (JDFNodeInfo) r;

					if (hasChildElement(ElementName.STATUSPOOL, null))
					{

						// get PartStatus vector
						final JDFStatusPool statusPool = getStatusPool();
						final VElement vPartStatus = statusPool.getPoolChildren(null);
						setStatus(EnumNodeStatus.Part);
						JDFAttributeMap mps = null;
						if (!vPartStatus.isEmpty())
						{
							final JDFPartStatus ps = (JDFPartStatus) vPartStatus.elementAt(0);
							mps = ps.getPartMap();
						}
						final VString partIDKeys = getPartIDKeys(mps);

						ni.setAttribute(AttributeName.NODESTATUS, statusPool.getAttribute(AttributeName.STATUS));
						ni.setAttribute(AttributeName.NODESTATUSDETAILS, statusPool.getStatusDetails());
						for (int ips = 0; ips < vPartStatus.size(); ips++)
						{
							final JDFPartStatus ps = (JDFPartStatus) vPartStatus.elementAt(ips);
							try
							{ // see if the partstatus is consistent with what
								// we have
								ni = (JDFNodeInfo) r.getCreatePartition(ps.getPartMap(), partIDKeys);
							}
							catch (final JDFException ex)
							{
								// couldn't create a partiton - flag failure and
								// move on
								bRet = false;
								continue;
							}
							ni.setAttribute(AttributeName.NODESTATUS, ps.getAttribute(AttributeName.STATUS));
							if (ps.hasAttribute(AttributeName.STATUSDETAILS))
							{
								ni.setAttribute(AttributeName.NODESTATUSDETAILS, ps.getStatusDetails());
							}
						}
						removeChild(ElementName.STATUSPOOL, null, 0);
					}
					else
					// no statuspool
					{
						// not yet
					}
				}
			}
			// have both link and element - snafu simply remove element
			removeChild(nam, null, 0);
		}
		return bRet;
	}

	/**
	 * typesafe validator, checks whether all resource links are ok
	 *
	 * @param level validation level
	 * @return true if this node is valid
	 */
	@Override
	public boolean isValid(final EnumValidationLevel level)
	{
		boolean bValid = super.isValid(level);
		if (!bValid)
		{
			return false;
		}

		bValid = !hasInvalidLinks(level);
		if (bValid && EnumType.Product.equals(getEnumType()))
		{
			final JDFNode n = getParentJDF();
			if (n != null)
			{
				bValid = EnumType.Product.equals(n.getEnumType());
			}
		}
		return bValid;
	}

	/**
	 * true if invalid Links are in this element
	 *
	 * @param level validation level
	 * @return boolean - true if unknown Links are in this element
	 *
	 * @default public boolean hasInvalidLinks (ValidationLevel_Complete)
	 */
	public boolean hasInvalidLinks(final EnumValidationLevel level)
	{
		return getInvalidLinks(level, 1).size() > 0;
	}

	/**
	 * typesafe validator utility
	 *
	 * @param level validation level
	 * @param nMax max. size of the returned vector
	 * @return vector of invalid Link names
	 *
	 * @default getInvalidLinks (ValidationLevel_Complete, Integer.MAX_VALUE)
	 */
	public VString getInvalidLinks(final EnumValidationLevel level, final int nMax)
	{
		return new LinkValidator(this).getInvalidLinks(level, nMax);
	}

	/**
	 * get the status synchronizer
	 *
	 * @return
	 */
	public StatusSynch getStatusSynch()
	{
		return new StatusSynch();
	}

	/**
	 * update the node status or nodeinfo/@NodeStatus for all partitions specified in vMap
	 *
	 * @param vMap the map of partitions to apply the update algorithm to
	 * @param updateKids if true, also recursively update all kids, if false move to root without updating kids
	 * @param updateParents if true, recurse down to the root, updatimg the satus based on modifications in this
	 * @deprecated use 4 parameter version
	 */
	@Deprecated
	public void updatePartStatus(final VJDFAttributeMap vMap, final boolean updateKids, final boolean updateParents)
	{
		updatePartStatus(vMap, updateKids, updateParents, 0);
	}

	/**
	 * update the node status or nodeinfo/@NodeStatus for all partitions specified in vMap
	 *
	 * @param vMap the map of partitions to apply the update algorithm to
	 * @param updateKids if true, also recursively update all kids, if false move to root without updating kids
	 * @param updateParents if true, recurse down to the root, updatimg the satus based on modifications in this
	 * @param method : -1, 0 or 1; -1 min status; 0 equals, 1 max status
	 */
	public void updatePartStatus(final VJDFAttributeMap vMap, final boolean updateKids, final boolean updateParents, final int method)
	{
		final VElement vNodes = getvJDFNode(null, null, true);
		if (vNodes != null && !vNodes.isEmpty())
		{

			final int kidsize = vNodes.size();
			// clean kids first and then start algorithm based on clean kids
			final VJDFAttributeMap statusMaps = new VJDFAttributeMap();
			for (int i = 0; i < kidsize; i++)
			{
				final JDFNode node = (JDFNode) vNodes.item(i);
				if (updateKids)
				{
					node.updatePartStatus(vMap, updateKids, false, method);
				}
				statusMaps.addAll(node.getStatusPartMapVector());
			}
			statusMaps.unify();
			if (statusMaps.size() > 0)
			{
				for (int i = statusMaps.size() - 1; i >= 0; i--)
				{
					final JDFAttributeMap map = statusMaps.elementAt(i);
					if (!map.subMap(vMap))
					{
						statusMaps.removeElementAt(i);
					}
				}
				if (statusMaps.size() == 0)
				{
					return;
				}
			}
			else
			{
				statusMaps.add(null);
			}
			for (int i = 0; i < statusMaps.size(); i++)
			{
				final JDFAttributeMap map = statusMaps.elementAt(i);
				EnumNodeStatus minStatus = EnumNodeStatus.Aborted;
				EnumNodeStatus maxStatus = EnumNodeStatus.Waiting;
				for (int j = 0; j < kidsize; j++)
				{
					final JDFNode node = (JDFNode) vNodes.item(j);

					final EnumNodeStatus status = node.getPartStatus(map, method);
					if (status == null)
					{
						minStatus = null;
						break; // no consistent status, don't set
					}
					if (minStatus.getValue() > status.getValue())
					{
						minStatus = status;
					}
					else if (maxStatus.getValue() < status.getValue())
					{
						maxStatus = status;
					}
				}
				if (minStatus != null)
				{
					if (method <= 0)
					{
						setPartStatus(map, minStatus, null);
					}
					else
					{
						setPartStatus(map, maxStatus, null);
					}
				}
			}
		}

		// recurse down to root
		if (updateParents)
		{
			final JDFNode parent = getParentJDF();
			if (parent != null)
			{
				parent.updatePartStatus(vMap, false, true, 0);
			}
		}

	}

	/**
	 * UpDateStatus - update the status of a node depending on its resources and child nodes
	 *
	 * @deprecated use updatePartStatus(VJDFAttributeMAP)
	 */
	@Deprecated
	public void upDateStatus()
	{
		final JDFResourceLinkPool resourceLinkPool = getResourceLinkPool();
		if (resourceLinkPool == null)
		{
			return;
		}

		final VElement vOut = resourceLinkPool.getInOutLinks(EnumUsage.Output, false, null, null);
		if (vOut == null || vOut.isEmpty())
		{
			return;
		}

		boolean bReady = true;
		for (int i = 0; i < vOut.size(); i++)
		{
			final JDFResource g = (JDFResource) vOut.elementAt(i);

			if (g.getResStatus(false).getValue() < EnumResStatus.Available.getValue())
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
	 * @param nodeID the NodeIdentifier of the job part
	 *
	 * @return JDFNode
	 *
	 */
	public JDFNode getJobPart(final NodeIdentifier nodeID)
	{
		if (nodeID == null)
		{
			return this;
		}
		return getJobPart(nodeID.getJobPartID(), nodeID.getJobID());
	}

	/**
	 * getJobPart - get a child node with a given jobpartid
	 *
	 * @param nodeID the NodeIdentifier of the job part
	 *
	 * @return JDFNode
	 *
	 */
	public JDFNode getJDF(final int iSkip)
	{
		return (JDFNode) getElement(ElementName.JDF, null, iSkip);
	}

	/**
	 * getJobPart - get a child node with a given jobpartid
	 *
	 * @param jobPartID the ID of the part job
	 * @param jobID the ID of the job
	 *
	 * @return JDFNode
	 *
	 * @default getJobPart(jobPartID, JDFConstants.EMPTYSTRING)
	 */
	public JDFNode getJobPart(final String jobPartID, final String jobID)
	{
		final JDFAttributeMap jobPartIDMap = new JDFAttributeMap(AttributeName.JOBPARTID, jobPartID);
		JDFNode n = (JDFNode) getTreeElement(ElementName.JDF, null, jobPartIDMap, false, true);
		if (n != null && !isWildCard(jobID))
		{
			if (!jobID.equals(n.getJobID(true)))
			{
				jobPartIDMap.put(AttributeName.JOBID, jobID);
				n = (JDFNode) getTreeElement(ElementName.JDF, null, jobPartIDMap, false, true);
			}
		}
		return n;
	}

	/**
	 * add any resources that live in ancestor nodes to this node
	 *
	 * @param vRWResources vector of resource names and Usage / ProcessUsage that are spawned as rw <br>
	 *        the format is one of:<br>
	 *        <li>ResName</li><br>
	 *        <li>ResName:Input</li><br>
	 *        <li>ResName:Output</li><br>
	 *        <li>ResName:ProcessUsage</li><br>
	 *        <li>ID<br>
	 * @param vSpawnParts vector of JDFAttributeMaps that describe the parts to spawn
	 *
	 * @return HashSet of resources or resource partitions that would be spawned rw multiple times
	 */
	public Collection<JDFResource> checkSpawnedResources(final VString vRWResources, final VJDFAttributeMap vSpawnParts)
	{
		final JDFSpawn spawn = new JDFSpawn(this);
		spawn.vSpawnParts = vSpawnParts;
		spawn.vRWResources_in = new VString(vRWResources);
		return spawn.checkSpawnedResources();
	}

	/**
	 * get inter-resource linked resource refs and resourcs links
	 *
	 * @param vDoneRefs
	 * @param bRecurse if true, also return references linked from the resource pool directly
	 *
	 * @return HashSet of referenced resource refs and links
	 */
	@Override
	public HashSet<JDFElement> getAllRefs(final HashSet<JDFElement> vDoneRefs, final boolean bRecurse)
	{
		HashSet<JDFElement> v1 = vDoneRefs;
		if (v1 == null)
			v1 = new LinkedHashSet<>();
		final JDFResourcePool rp = getResourcePool();
		if (rp != null && bRecurse)
		{
			v1 = rp.getAllRefs(v1, bRecurse);
		}

		final JDFResourceLinkPool rlp = getResourceLinkPool();
		if (rlp != null)
		{
			v1 = rlp.getAllRefs(v1, bRecurse);
		}

		// only 1.2 direct element - resources are retrieved from the ResourcePool
		final JDFCustomerInfo ci = (JDFCustomerInfo) getElement(ElementName.CUSTOMERINFO);
		if (ci != null)
		{
			v1 = ci.getAllRefs(v1, bRecurse);
		}

		// only 1.2 direct element - resources are retrieved from the ResourcePool
		final JDFNodeInfo ni = (JDFNodeInfo) getElement(ElementName.NODEINFO);
		if (ni != null)
		{
			v1 = ni.getAllRefs(v1, bRecurse);
		}

		final JDFAncestorPool ap = getAncestorPool();
		if (ap != null)
		{
			v1 = ap.getAllRefs(v1, true);
		}

		final VElement vNodes = getvJDFNode(null, null, true);
		for (final KElement e : vNodes)
		{
			v1 = ((JDFNode) e).getAllRefs(v1, bRecurse);
		}

		return v1;
	}

	/**
	 * setCombined - set the combined node types to the values in vCombiNodes
	 *
	 * @param vCombiNodes
	 */
	public void setCombined(final VString vCombiNodes)
	{
		setType(JDFConstants.COMBINED, false);
		setTypes(vCombiNodes);
	}

	/**
	 * getCombinedTypes - get the list of combined types if this is a combined node
	 *
	 * @deprecated use getTypes() or getEnumTypes()
	 * @return Vector
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Vector getCombinedTypes()
	{
		if (!isTypesNode())
		{
			return new Vector();
		}

		final String s = getAttribute(AttributeName.TYPES, null, JDFConstants.EMPTYSTRING);
		return StringUtil.tokenize(s, JDFConstants.BLANK, false);
	}

	/**
	 * addComponent - add a component resource to resroot and link it to this process
	 *
	 * @param cType
	 * @param bInput
	 * @param resRoot
	 * @param bLink
	 *
	 * @return JDFComponent
	 * @deprecated use standard addResource
	 * @default addComponent(cType, bInput, null, true)
	 */
	@Deprecated
	public JDFComponent addComponent(final String cType, final boolean bInput, final JDFNode resRoot, final boolean bLink)
	{
		final JDFComponent c = (JDFComponent) addResource(ElementName.COMPONENT, JDFResource.EnumResourceClass.Quantity, bInput, resRoot, bLink, null);

		if (c != null)
		{
			// true --> input resource
			c.setResStatus(JDFResource.EnumResStatus.Unavailable, false);
			c.setDescriptiveName(cType);
		}

		return c;
	}

	/**
	 * Set attribute SpawnID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSpawnID(final String value)
	{
		setAttribute(JDFConstants.SPAWNID, value, null);
	}

	/**
	 * spawn a node; url is the file name of the new node, vRWResourceUsage is the vector of Resources Usages (or Names if no usage exists for the process) that are spawned RW, all
	 * others are spawned read only; vParts is the vector of part maps that are to be spawned, defaults to no part, i.e. the whole thing
	 *
	 * @param parentURL
	 * @param spawnURL URL of the spawned JDF file
	 * @param vRWResources_in vector of resource names and Usage / ProcessUsage that are spawned as rw <br>
	 *        the format is one of:<br>
	 *        ResName:Input<br>
	 *        ResName:Output<br>
	 *        ResName:ProcessUsage<br>
	 * @param vSpawnParts vector of mAttributes that describe the parts to spawn, only valid PartIDKeys are allowed
	 * @param bSpawnROPartsOnly if true, only the parts of RO resources that are specified in vParts are spawned, else the complete resource is spawned
	 * @param bCopyNodeInfo copy the NodeInfo elements into the Ancestors
	 * @param bCopyCustomerInfo copy the CustomerInfo elements into the Ancestors
	 * @param bCopyComments copy the Comment elements into the Ancestors
	 *
	 * @return The spawned node
	 * @since 050831 added bCopyComments
	 * @tbd enhance nested spawning of partitioned nodes
	 * @deprecated - use JDFSpawn class ( see code below)
	 * @default spawn(parentURL, null, null, null, false, false, false, false)
	 */
	@Deprecated
	public JDFNode spawn(final String parentURL, final String spawnURL, final VString vRWResources_in, final VJDFAttributeMap vSpawnParts, final boolean bSpawnROPartsOnly, final boolean bCopyNodeInfo, final boolean bCopyCustomerInfo, final boolean bCopyComments)
	{
		final JDFSpawn spawn = new JDFSpawn(this);
		return spawn.spawn(parentURL, spawnURL, vRWResources_in, vSpawnParts, bSpawnROPartsOnly, bCopyNodeInfo, bCopyCustomerInfo, bCopyComments);
	}

	// ///////////////////////////////////////////////////////////////////////

	/**
	 * spawn a node in informative mode without modifying the root JDF; url is the file name of the new node, the parameters except for the list of rw resources, which are by
	 * definition empty, are identical to those of Spawn
	 *
	 * vRWResourceUsage is the vector of Resources Usages, Resource Names or Resource IDs that are spawned RW, all others are spawned read only; vParts is the vector of part maps
	 * that are to be spawned, defaults to no part, i.e. the whole thing
	 *
	 * @param parentURL
	 *
	 * @param spawnURL URL of the spawned JDF file
	 * @param vSpawnParts vector of mAttributes that describe the parts to spawn
	 * @param bSpawnROPartsOnly if true, only the parts of RO resources that are specified in vParts are spawned, else the complete resource is spawned
	 * @param bCopyNodeInfo copy the NodeInfo elements into the Ancestors
	 * @param bCopyCustomerInfo copy the CustomerInfo elements into the Ancestors
	 * @param bCopyComments copy the Comment elements into the Ancestors
	 * @return JDFDoc - the spawned node's owner document.
	 *
	 * @default spawnInformative(parentURL, null, null, false, false, false, false);
	 * @deprecated use JDFSpawn.spawnInformative()
	 */
	@Deprecated
	public JDFNode spawnInformative(final String parentURL, final String spawnURL, final VJDFAttributeMap vSpawnParts, final boolean bSpawnROPartsOnly, final boolean bCopyNodeInfo, final boolean bCopyCustomerInfo, final boolean bCopyComments)
	{
		final JDFSpawn _spawn = new JDFSpawn(this);
		return _spawn.spawnInformative(parentURL, spawnURL, vSpawnParts, bSpawnROPartsOnly, bCopyNodeInfo, bCopyCustomerInfo, bCopyComments);
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * Method unSpawn. undo a spawn, removing any and all bookkeeping of that spawning
	 *
	 * @param spawnID spawnID of the spawn to undo
	 * @return the fixed unspawned node
	 * @deprecated use new JDFSpawn(this).unSpawn(spawnID);
	 */
	@Deprecated
	public JDFNode unSpawn(final String spawnID)
	{
		return new JDFSpawn(this).unSpawn(spawnID);
	}

	/**
	 * merge nodes in a way that no duplicate elements are created<br>
	 * attention !! this kills pools !!
	 *
	 * @param e the node element to merge with the current node
	 * @param bDelete if true KElement e will be deleted
	 * @return JDFNode: the merged node element
	 */
	// JDFNode MergeNode(JDFNode e,bool bDelete);
	public JDFNode mergeNode(final JDFNode e, final boolean bDelete)
	{
		// merge nodes in a way that no duplicate elements are created
		// attention - this kills pools....

		final VElement v = e.getChildElementVector(null, null, null, true, 0, false);
		for (int i = 0; i < v.size(); i++)
		{
			final KElement m = v.elementAt(i);
			final String strName = m.getNodeName();
			KElement mHere = null;
			if ((strName.equals(ElementName.NODEINFO)) || (strName.equals(ElementName.CUSTOMERINFO)) || (strName.equals(ElementName.RESOURCEPOOL))
					|| (strName.equals(ElementName.RESOURCELINKPOOL)) || (strName.equals(ElementName.ANCESTORPOOL)) || (strName.equals(ElementName.AUDITPOOL)))
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
	 * getLink - get the resourcelink that resides in the ResourceLinkPool of this node and references the resource r
	 *
	 * @param r the resource you are searching a link for
	 * @param bInput
	 *
	 * @return JDFResourceLink - the resource link you was searching for or if not found, a new empty JDFResource Link
	 *
	 * @default getLink(r, true)
	 * @deprecated use getLink(resource, EnumUsage)
	 */
	@Deprecated
	public JDFResourceLink getLink(final JDFResource r, final boolean bInput)
	{
		return getLink(r, bInput ? EnumUsage.Input : EnumUsage.Output);
	}

	/**
	 * getLink - get the resourcelink that resides in the ResourceLinkPool of this node and references the resource r
	 *
	 * @param r the resource you are searching a link for
	 * @param usage the usage attribute of the link. If null, both input and output resourelinks will be returned
	 *
	 * @return JDFResourceLink - the resource link you was searching for or if not found, null
	 *
	 */
	public JDFResourceLink getLink(final JDFResource r, final EnumUsage usage)
	{
		// get the reslink pool
		final JDFResourceLinkPool p = getResourceLinkPool();
		if (p == null || r == null)
		{
			return null;
		}

		// get any possible links
		final VElement v = p.getInOutLinks(usage, true, null, null);

		if (v != null)
		{
			final String linkString = r.getLinkString();
			final String rID = r.getID();
			for (final KElement e : v)
			{
				final JDFResourceLink resLink = (JDFResourceLink) e;
				// is it the right one?
				if (resLink != null && resLink.getrRef().equals(rID) && resLink.getNodeName().equals(linkString))
				{
					return resLink;
				}
			}
		}

		// nothing found
		return null;
	}

	/**
	 *
	 * get the vector of all resource leaves linked to a resource
	 *
	 * @param usage
	 * @param resName
	 * @param procUsage
	 * @param expandLeaves if true expand to the lowest leaves
	 * @return
	 */
	public VElement getLinkedResourceVector(final EnumUsage usage, final String resName, final EnumProcessUsage procUsage, final boolean expandLeaves)
	{
		final JDFResourceLinkPool p = getResourceLinkPool();
		final VElement vLinks = p == null ? null : p.getInOutLinks(usage, true, resName, procUsage);
		if (vLinks == null || vLinks.size() == 0)
			return null;
		final VElement v = new VElement();
		for (final KElement e : vLinks)
		{
			final JDFResourceLink rl = (JDFResourceLink) e;
			final VElement vRes = rl.getTargetVector(0);
			if (vRes != null)
			{
				if (expandLeaves)
				{
					for (final KElement r : vRes)
					{
						final JDFResource res = (JDFResource) r;
						v.addAll(res.getLeafArray(false));
					}
				}
				else
				{
					v.addAll(vRes);
				}
			}
		}
		v.unify();
		return v.size() == 0 ? null : v;
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
	 * Get an ordered list of all Parents ID attributes:<br>
	 * the last entry is the direct parent, the last-1 entry is the grandparent etc.<br>
	 * This is analog to the definition of JDFAncestorPool
	 *
	 * @return vector of strings representing the node IDs
	 */
	public VString getAncestorIDs()
	{
		final VString vs = new VString();
		JDFNode me = this;

		while (true)
		{
			final String pid = me.getID();
			vs.addElement(pid);

			final KElement parent = me.getParentNode_KElement();

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
	 * @param nSkip
	 * @deprecated use getParentJDF()
	 * @return JDFNode - the ancestor node
	 */
	@Deprecated
	public JDFNode getAncestorNode(final int nSkip)
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
	 * searches for the first element occurence in the parent nodes and then the ancestor elements of the root ancestorpool
	 *
	 * @param attrib the element name
	 * @param nameSpaceURI the XML-namespace
	 * @param def the default value, if there is no ancestor attribute
	 *
	 * @return String - value of attribute found, value of def if not available
	 *
	 * @default getAncestorAttribute(attrib, null, null)
	 */
	public String getAncestorAttribute(final String attrib, final String nameSpaceURI, final String def)
	{
		final String s = getInheritedAttribute(attrib, nameSpaceURI, null);
		if (s != null)
		{
			return s;
		}
		final JDFNode root = getJDFRoot();
		if (root == null)
		{
			return def;
		}
		// not in the inherited nodes, check the root node's AncestorPool
		final JDFAncestorPool ap = root.getAncestorPool();
		if (ap == null)
		{
			return def;
		}
		return ap.getAncestorAttribute(attrib, nameSpaceURI, def);
	}

	/**
	 * true if a non default attribute occurence in the parent nodes and then the ancestor elements of the root ancestorpool exists
	 *
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 *
	 * @return true if the attribute exists
	 *
	 * @default hasAncestorAttribute(attrib, null)
	 */
	public boolean hasAncestorAttribute(final String attrib, final String nameSpaceURI)
	{
		return getAncestorAttribute(attrib, nameSpaceURI, null) != null;
	}

	/**
	 * Check existance of attribute Activation
	 *
	 * @param bInherit recurse through ancestors and Ancestor elements of the AncestorPool when searching
	 * @return true if attribute Activation exists
	 */
	// bool hasActivation(bool bInherit=false) ;
	public boolean hasActivation(final boolean bInherit)
	{
		if (bInherit)
		{
			return hasAncestorAttribute(AttributeName.ACTIVATION, null);
		}
		return hasAttribute(AttributeName.ACTIVATION, null, false);
	}

	/**
	 * Check existence of attribute JobID
	 *
	 * @param bInherit recurse through ancestors and Ancestor elements of the AncestorPool when searching
	 * @return true if attribute JobID exists
	 * @deprecated
	 */
	@Deprecated
	public boolean hasJobID(final boolean bInherit)
	{
		if (bInherit)
		{
			return hasAncestorAttribute(AttributeName.JOBID, null);
		}
		return hasAttribute(AttributeName.JOBID, null, false);
	}

	/**
	 * searches for the first element occurence in this and the ancestor elements
	 *
	 * @param element the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @since 180502
	 * @return the KElement found
	 */
	public KElement getAncestorElement(final String element, final String nameSpaceURI)
	{
		if (ElementName.NODEINFO.equals(element) || ElementName.CUSTOMERINFO.equals(element))
		{
			return getNiCi(element, true, null);
		}
		final JDFElement e = (JDFElement) getInheritedElement(element, nameSpaceURI, 0);
		if (e != null)
		{
			return e;
		}

		final JDFNode root = getJDFRoot();
		if (root == null)
		{
			return null;
		}
		// not in the inherited nodes, check the root node's AncestorPool
		final JDFAncestorPool ap = root.getAncestorPool();
		if (ap == null)
		{
			return null;
		}

		return ap.getAncestorElement(element, nameSpaceURI, null);
	}

	/**
	 * true if a non default attribute occurs in the parent nodes and then the ancestor elements of the root ancestorpool exists
	 *
	 * @deprecated
	 * @param element the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @since 180502
	 * @return boolean - true if the attribute exists
	 */
	@Deprecated
	public boolean hasAncestorElement(final String element, final String nameSpaceURI)
	{

		return getAncestorElement(element, nameSpaceURI) != null;
	}

	/**
	 * addParameter - add a parameter resource to resroot and link it to this process
	 *
	 * @param strName
	 * @param bInput
	 * @param resRoot
	 * @param bLink
	 *
	 * @return JDFResource
	 * @deprecated use addResource(strName, JDFResource.EnumClass.Parameter, bInput, resRoot, bLink, null)
	 */
	@Deprecated
	public JDFResource addParameter(final String strName, final boolean bInput, final JDFNode resRoot, final boolean bLink)
	{
		return addResource(strName, JDFResource.EnumResourceClass.Parameter, bInput, resRoot, bLink, null);
	}

	/**
	 * addConsumable - add a consumable resource to resroot and link it to this process
	 *
	 * @param strName
	 * @param bInput
	 * @param resRoot
	 * @param bLink
	 *
	 * @deprecated use addResource(name, null, true, null, true)
	 * @return JDFResource
	 *
	 * @default addResource(name, null, true, null, true)
	 */
	@Deprecated
	public JDFResource addConsumable(final String strName, final boolean bInput, final JDFNode resRoot, final boolean bLink)
	{
		return addResource(strName, JDFResource.EnumResourceClass.Consumable, bInput, resRoot, bLink, null);
	}

	/**
	 * addHandling - add a handling resource to resroot and link it to this process
	 *
	 * @param strName
	 * @param bInput
	 * @param resRoot
	 * @param bLink
	 *
	 * @deprecated use addResource(name, null, true, null, true)
	 * @return JDFResource
	 *
	 * @default addResource(name, JDFResource.EnumClass.Handling, true, null, true)
	 */
	@Deprecated
	public JDFResource addHandling(final String strName, final boolean bInput, final JDFNode resRoot, final boolean bLink)
	{
		return addResource(strName, JDFResource.EnumResourceClass.Handling, bInput, resRoot, bLink, null);
	}

	/**
	 * isCombined - is this a Combined resource type ?
	 *
	 * @return boolean - true if it is, otherwise false
	 */
	public boolean isCombined()
	{
		return JDFConstants.COMBINED.equals(getType());
	}

	/**
	 * Is this a product node type ?
	 *
	 * @return boolean - true if this is a product node
	 */
	public boolean isProduct()
	{
		return JDFConstants.PRODUCT.equals(getType());
	}

	/**
	 * Is this a Combined node type ?
	 *
	 * @return boolean - true if this is a combined node
	 */
	public boolean isProcessGroup()
	{
		return JDFConstants.PROCESSGROUP.equals(getType());
	}

	/**
	 * Is this a group node type (ProcessGroup or Product)?
	 *
	 * @return boolean - true if this is a combined node
	 */
	public boolean isGroupNode()
	{
		final EnumType type2 = getEnumType();
		return EnumType.ProcessGroup.equals(type2) && !hasAttribute(AttributeName.TYPES) || EnumType.Product.equals(type2);
	}

	/**
	 * Is this a group node type that allows @Types (ProcessGroup or Combined)?
	 *
	 * @return boolean - true if this is a combined node
	 */
	public boolean isTypesNode()
	{
		final EnumType type2 = getEnumType();
		return EnumType.ProcessGroup.equals(type2) || EnumType.Combined.equals(type2);
	}

	/**
	 * getIDPrefix
	 *
	 * @return the ID prefix of JDFNode
	 */
	@Override
	public String getIDPrefix()
	{
		return "n";
	}

	/**
	 * get string attribute JobID
	 *
	 * @param bInherit recurse through ancestors when searching
	 * @return String - attribute value
	 */
	public String getJobID(final boolean bInherit)
	{
		if (bInherit)
		{
			return getAncestorAttribute(AttributeName.JOBID, null, JDFConstants.EMPTYSTRING);
		}
		return getAttribute(AttributeName.JOBID, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * get string attribute JobID
	 *
	 * @return attribute value
	 * @deprecated use getJobPartID(false);
	 */
	@Deprecated
	public String getJobPartID()
	{
		return getJobPartID(false);
	}

	/**
	 * get string attribute JobID
	 *
	 * @param bInherit if true recurse through ancestors when searching
	 * @return String - attribute value
	 *
	 * @default getJobPartID(flase)
	 */
	public String getJobPartID(final boolean bInherit)
	{
		if (bInherit)
		{
			return getAncestorAttribute(AttributeName.JOBPARTID, null, JDFConstants.EMPTYSTRING);
		}
		return getAttribute(AttributeName.JOBPARTID, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Set attribute JobPartID
	 *
	 * @param jobPartID the value to set the attribute to
	 */
	public void setJobPartID(final String jobPartID)
	{
		setAttribute(AttributeName.JOBPARTID, jobPartID, null);
	}

	/**
	 * set attribute JobID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobID(final String value)
	{
		setAttribute(AttributeName.JOBID, value, null);
	}

	/**
	 * test element StatusPool existence
	 *
	 * @return boolean - true if a matching element exists
	 * @deprecated
	 */
	@Deprecated
	public boolean hasStatusPool()
	{
		return numChildElements(ElementName.STATUSPOOL, null) > 0;
	}

	/**
	 * get string attribute SpawnID
	 *
	 * @return String - attribute value
	 * @deprecated use getSpawnID(boolean)
	 */
	@Deprecated
	public String getSpawnID()
	{
		return getSpawnID(false);
	}

	/**
	 * get string attribute SpawnID
	 *
	 * @param bInherit if true recurse through ancestors when searching
	 * @return String - attribute value
	 *
	 * @default getSpawnID(false)
	 */
	public String getSpawnID(final boolean bInherit)
	{
		if (bInherit)
		{
			return getAncestorAttribute(AttributeName.SPAWNID, null, JDFConstants.EMPTYSTRING);
		}
		return getAttribute(AttributeName.SPAWNID);
	}

	/**
	 * remove attribute SpawnID
	 *
	 * @deprecated
	 */
	@Deprecated
	public void removeSpawnID()
	{
		removeAttribute(JDFConstants.SPAWNID, null);
	}

	/**
	 * remove element AncestorPool
	 *
	 * @deprecated
	 */
	@Deprecated
	public void removeAncestorPool()
	{
		removeChild(ElementName.ANCESTORPOOL, null, 0);
	}

	/**
	 * get the Parent JDFNode, null if the parent element is the document or an envelope xml
	 *
	 * @return JDFNode: the parent JDF, null if this is the root JDF
	 * @deprecated use getParentJDF()
	 */
	@Deprecated
	public JDFNode getParentJDFNode()
	{
		return getParentJDF();
	}

	/**
	 * get the Parent JDFNode, null if the parent element is the document or an envelope xml
	 *
	 * @return JDFNode: the parent JDF, null if this is the root JDF
	 */
	@Override
	public JDFNode getParentJDF()
	{
		final KElement jdfElem = getParentNode_KElement();
		if (jdfElem instanceof JDFNode)
		{
			return (JDFNode) jdfElem;
		}
		return null;
	}

	/**
	 * get the Parent JDFNode with Type=Product, null if the parent element is the document or an envelope xml or no product exists
	 *
	 * @return JDFNode: the parent JDF, null if this is the root JDF
	 */
	public JDFNode getParentProduct()
	{
		JDFNode parent = this;
		while (parent != null)
		{
			if (parent.isProduct())
				break;
			parent = parent.getParentJDF();
		}
		return parent;
	}

	/**
	 * Check existence of attribute Type
	 *
	 * @return boolean - true if attribute Type exists
	 * @deprecated use inline hasAttribute
	 */
	@Deprecated
	public boolean hasType()
	{
		return hasAttribute(AttributeName.TYPE, null, false);
	}

	/**
	 * Check whether typ or types contains type
	 *
	 * @param type the type to check for
	 * @return boolean - true if attribute Type is either in Type or types always true for null
	 *
	 */
	public boolean containsType(final String type)
	{
		if (type == null)
			return true;

		final String myType = getTypesString();
		if (myType == null)
			return false;
		return StringUtil.hasToken(myType, type, " ", 0);
	}

	/**
	 * get a vector of Link names that may be inserted in this element if the links need a processusage, the format is LinkName:ProcessUsage
	 *
	 * @param nMax maximum size of the returned vector
	 * @return vector of strings that contains insertable link names
	 */
	public VString getInsertLinkVector(final int nMax)
	{
		return new LinkValidator(this).getInsertLinkVector(nMax);
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get the resource that matches the typesafe link described by i
	 *
	 * @param info the LinkInfo string for this name
	 * @param i the index of the pu to find
	 * @return the enumerated process usage of this checked link
	 * @default getEnumProcessUsage(info, 0)
	 * @deprecated
	 */
	@Deprecated
	public EnumProcessUsage getEnumProcessUsage(final String info, final int i)
	{
		final String iToken = StringUtil.token(info, i, JDFConstants.BLANK);
		if (iToken.equals(JDFConstants.EMPTYSTRING))
		{
			return null;
		}

		if (iToken.length() > 2)
		{
			final String pu = iToken.substring(2);
			return EnumProcessUsage.getEnum(pu);
		}

		if (iToken.charAt(0) == 'i')
		{
			return EnumProcessUsage.AnyInput;
		}
		else if (iToken.charAt(0) == 'o')
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
	 *
	 * @deprecated use numChildElements(ElementName.ANCESTORPOOL, null) > 0;
	 * @return bool true if a matching element exists
	 */
	@Deprecated
	public boolean hasAncestorPool()
	{
		return numChildElements(ElementName.ANCESTORPOOL, null) > 0;
	}

	/**
	 * Check existance of attribute ProjectID
	 *
	 * @param bInherit recurse through ancestors when searching
	 * @return true if attribute ProjectID exists
	 * @deprecated
	 */
	@Deprecated
	public boolean hasProjectID(final boolean bInherit)
	{
		if (bInherit)
		{
			return hasAncestorAttribute(JDFConstants.PROJECTID, null);
		}
		return hasAttribute(JDFConstants.PROJECTID, null, false);
	}

	/**
	 * Check existence of attribute ProjectID
	 *
	 * @return true if attribute ProjectID exists
	 * @deprecated
	 */
	@Deprecated
	public boolean hasProjectID()
	{
		return hasProjectID(false);
	}

	/**
	 * set attribute ProjectID
	 *
	 * @param strValue the value to set the attribute to
	 */
	public void setProjectID(final String strValue)
	{
		setAttribute(JDFConstants.PROJECTID, strValue, null);
	}

	/**
	 * get string attribute ProjectID
	 *
	 * @param bInherit recurse through ancestors when searching
	 * @return the value of the attribute
	 */
	public String getProjectID(final boolean bInherit)
	{
		if (bInherit)
		{
			return getAncestorAttribute(JDFConstants.PROJECTID, null, JDFConstants.EMPTYSTRING);
		}
		return getAttribute(JDFConstants.PROJECTID, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * get string attribute ProjectID
	 *
	 * @return the value of the attribute
	 * @deprecated use getProjectID(boolean bInherit)
	 */
	@Deprecated
	public String getProjectID()
	{
		return getProjectID(false);
	}

	/**
	 * isValidLink check whether an resLink is legal for this class
	 *
	 * @param level the checking level
	 * @param rl the JDFResourceLink to check
	 * @param doneNameList Vector of Integer
	 * @param doneIndexList Vector of Integer
	 * @return true if valid
	 */
	public boolean isValidLink(final EnumValidationLevel level, final JDFResourceLink rl)
	{
		return new LinkValidator(this).isValidLink(level, rl);
	}

	/**
	 * isValidLink check whether an resLink is legal for this class
	 *
	 * @param resName
	 * @param usage
	 * @param processUsage
	 * @return true if valis
	 */
	public boolean isValidLink(final String resName, final EnumUsage usage, final String processUsage)
	{
		return new LinkValidator(this).isValidLink(resName, usage, processUsage);
	}

	/**
	 * @deprecated use getMissingLinkVector
	 * @param nMax
	 * @return VString
	 */
	@Deprecated
	public VString getMissingLinks(final int nMax)
	{
		return getMissingLinkVector(nMax);
	}

	// ///////////////////////////////////////////////////////////////////////

	/**
	 * get the links that match the typesafe resource name if the Resource type is not defined for the process represented by this node see chapter 6 JDFSpec, then the links are
	 * ignored
	 *
	 * @param resName of the resource to remove
	 * @param bLink if false, returns the linked resources, else if true, returns the ResourceLink elements
	 * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
	 * @return vector of resourcelink elements
	 */
	public VElement getMatchingLinks(final String resName, final boolean bLink, final EnumProcessUsage processUsage)
	{
		return new LinkValidator(this).getMatchingLinks(resName, bLink, processUsage);
	}

	/**
	 * get a vector of Link names that are missing in this element<br>
	 * if the links need a processusage, the format is LinkName:ProcessUsage
	 *
	 * @param nMax maximum size of the returned vector
	 * @return VString vector of strings that contains missing Link names
	 */
	public VString getMissingLinkVector(final int nMax)
	{
		return new LinkValidator(this).getMissingLinkVector(nMax);
	}

	/**
	 *
	 * @param resName
	 * @param processUsage
	 * @param partMap
	 * @param pos
	 * @deprecated use getMatchingResource(String resName, EnumProcessUsage processUsage, JDFAttributeMap partMap, int pos))
	 * @return JDFResource
	 */
	@Deprecated
	public JDFResource getMatchingResource(final String resName, final int processUsage, final JDFAttributeMap partMap, final int pos)
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
	 * get the resource that matches a typesafe resource name if the Resource type is not defined for the process represented by this node see chapter 6 JDFSpec, then the resource
	 * is ignored
	 *
	 * @param resName of the resource to remove
	 * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
	 * @param partMap
	 * @param pos the position of the link if multiple matching links exist
	 * @return JDFResource - the resourcelink element
	 */
	public JDFResource getMatchingResource(final String resName, final EnumProcessUsage processUsage, final JDFAttributeMap partMap, final int pos)
	{
		final JDFResourceLink rl = getMatchingLink(resName, processUsage, pos);
		if (rl == null)
		{
			return null;
		}

		if (partMap != null && !partMap.isEmpty() && !rl.hasPartMap(partMap))
		{
			return null;
		}

		final JDFResource r = rl.getTarget();
		if (r == null)
		{
			return null;
		}

		return r.getPartition(partMap, null);
	}

	/**
	 * get the link that matches the typesafe resource name<br>
	 * if the Resource type is not defined for the process represented by this node, the link is ignored (see JDF Spec Chapter 6)
	 *
	 * @param resName name of the resource to remove
	 * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
	 * @param pos the position of the link (if multiple matching links exist)
	 * @return JDFResourceLink - the resourcelink
	 */
	public JDFResourceLink getMatchingLink(final String resName, final EnumProcessUsage processUsage, final int pos)
	{
		return new LinkValidator(this).getMatchingLink(resName, processUsage, pos);
	}

	/**
	 * Method AppendMatchingResource. Appends a resource and link it to this if it is listed in the list of valid nodes for for a JDF with the given type also creates the matching
	 * resource link in this
	 *
	 * @param resName the name of the resource to add
	 * @param usage the Usage of the resourcelink of the resource to add:
	 *        <li>null EnumProcessUsage.AnyOutput - for input but no processUsage</li>
	 *
	 * @return JDFResource the newly created resource
	 */
	public JDFResource appendMatchingResource(final String resName, final EnumUsage usage)
	{
		EnumProcessUsage processUsage = null;
		if (EnumUsage.Input.equals(usage))
			processUsage = EnumProcessUsage.AnyInput;
		else if (EnumUsage.Output.equals(usage))
			processUsage = EnumProcessUsage.AnyOutput;
		return new LinkValidator(this).appendMatchingResource(resName, processUsage, null);
	}

	/**
	 * Method AppendMatchingResource. Appends a resource and link it to this if it is listed in the list of valid nodes for for a JDF with the given type also creates the matching
	 * resource link in this
	 *
	 * @param resName the name of the resource to add
	 * @param processUsage the processUsage of the resourcelink of the resource to add:
	 *        <li>null EnumProcessUsage.AnyOutput - for input but no processUsage</li>
	 *        <li>EnumProcessUsage.AnyOutput - for output but no processUsage</li>
	 *
	 * @param resourceRoot the root JDF node, that is the parent of the resourcepool where the resource should be added. If null, this node is assumed.
	 *
	 * @return JDFResource the newly created resource
	 */
	public JDFResource appendMatchingResource(final String resName, final EnumProcessUsage processUsage, final JDFNode resourceRoot)
	{
		return new LinkValidator(this).appendMatchingResource(resName, processUsage, resourceRoot);
	}

	/**
	 * remove the link that matches the typesafe link resource name
	 *
	 * @param resName name of the resource to remove
	 * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
	 * @param bRemoveResource also remove the resource, if it is not linked by any other process
	 * @param pos the position of the link, if multiple matching links exist
	 * @return true if successful
	 *
	 * @default removeMatchingLink(resName, processUsage, false, 0)
	 */
	public boolean removeMatchingLink(final String resName, final int processUsage, final boolean bRemoveResource, final int pos)
	{
		JDFResourceLink l = null;
		l = getMatchingLink(resName, EnumProcessUsage.getEnum(processUsage), pos);
		if (l == null)
		{
			return false;
		}
		removeLink(l, bRemoveResource);
		return true;
	}

	/**
	 *
	 * remove a resourceLink and potentially its linked resource
	 *
	 * @param l
	 * @param bRemoveResource
	 */
	public void removeLink(final JDFResourceLink l, final boolean bRemoveResource)
	{
		if (l == null)
			return;

		if (bRemoveResource)
		{
			final JDFResource r = l.getLinkRoot();
			l.deleteNode();

			if (r.getLinks(null, null).size() == 0)
			{
				r.deleteUnLinked();
			}
		}
		else
		{
			l.deleteNode();
		}
	}

	/**
	 * remove the link that matches the typesafe link resource name
	 *
	 * @param resName name of the resource to remove
	 * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
	 * @param bRemoveResource also remove the resource, if it is not linked by any other process
	 * @return true if successful
	 *
	 * @default removeMatchingLink(resName, processUsage, false, 0)
	 */
	public boolean removeMatchingLinks(final String resName, final EnumProcessUsage processUsage, final boolean bRemoveResource)
	{
		final VElement v = getMatchingLinks(resName, true, processUsage);
		for (final KElement e : v)
		{
			removeLink((JDFResourceLink) e, bRemoveResource);
		}
		return v.size() > 0;
	}

	/**
	 * Append a resource that matches the typesafe link described by resource name
	 *
	 * @param resource the resource to link
	 * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
	 * @param partMap the Attribute map of parts
	 * @return the new link, null if failure
	 *
	 * @default linkMatchingResource(resource, processUsage, null)
	 */
	public JDFResourceLink linkMatchingResource(final JDFResource resource, final EnumProcessUsage processUsage, final JDFAttributeMap partMap)
	{
		return new LinkValidator(this).linkMatchingResource(resource, processUsage, partMap);
	}

	/**
	 * get the number of links that match the typesafe link resource name
	 *
	 * @param resName name of the resources to match
	 * @param bLink if false: returns the linked resources, if true: returns the ResourceLink elements
	 * @param processUsage enum that defines if all links matching the name or only those matching the name usage and/or processusage are requested
	 * @return int - the number of resourcelink elements
	 *
	 * @default numMatchingLinks(resName, true, ProcessUsage_Any.getValue())
	 */
	public int numMatchingLinks(final String resName, final boolean bLink, final EnumProcessUsage processUsage)
	{
		return new LinkValidator(this).numMatchingLinks(resName, bLink, processUsage);
	}

	/*
	 * // Element getter / setter
	 */

	/**
	 * @return
	 */
	public JDFAncestorPool getCreateAncestorPool()
	{
		return (JDFAncestorPool) getCreateElement_KElement(ElementName.ANCESTORPOOL, null, 0);
	}

	/**
	 * @return
	 */
	public JDFAncestorPool appendAncestorPool()
	{
		return (JDFAncestorPool) appendElementN(ElementName.ANCESTORPOOL, 1, null);
	}

	/**
	 * @return
	 */
	public JDFAncestorPool getAncestorPool()
	{
		return getElementByClass(JDFAncestorPool.class, 0, false);
	}

	/**
	 * @return
	 */
	public JDFAuditPool getCreateAuditPool()
	{
		return (JDFAuditPool) getCreateElement_KElement(ElementName.AUDITPOOL, null, 0);
	}

	/**
	 * @return
	 */
	public JDFAuditPool appendAuditPool()
	{
		return (JDFAuditPool) appendElementN(ElementName.AUDITPOOL, 1, null);
	}

	/**
	 * @return
	 */
	public JDFAuditPool getAuditPool()
	{
		return getElementByClass(JDFAuditPool.class, 0, false);
	}

	/**
	 * gets the existing CustomerInfo or creates a new one if none exists this method will check if a NodeInfo exists,
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
		return (JDFCustomerInfo) appendNiCi(ElementName.CUSTOMERINFO);
	}

	/**
	 * gets the existing CustomerInfo
	 *
	 * @return the existing CustomerInfo.
	 */
	public JDFCustomerInfo getCustomerInfo()
	{
		return (JDFCustomerInfo) getNiCi(ElementName.CUSTOMERINFO, false, null);
	}

	/**
	 * gets the NodeIdetifier that matches this
	 *
	 * @return
	 */
	@Override
	public NodeIdentifier getIdentifier()
	{
		return new NodeIdentifier(getJobID(true), getJobPartID(false), getNodeInfoPartMapVector());
	}

	/**
	 * gets the existing inherited CustomerInfo or NodeInfo from parents including ancestorpool
	 *
	 * @param elementName name of the element to look for
	 * @param bInherit if true: recurse into parents
	 * @param xPath
	 * @return the existing CustomerInfo or NodeInfo
	 */
	private KElement getNiCi(final String elementName, final boolean bInherit, final String xPath)
	{
		// always get the element
		KElement nici = getElement(elementName);
		final EnumVersion eVer = getVersion(true);

		// if version>=1.0 or no direct element is there try the resource
		if (eVer == null || eVer.getValue() >= EnumVersion.Version_1_3.getValue() || (nici == null))
		{
			final VElement v = getResourceLinks(elementName, new JDFAttributeMap(AttributeName.USAGE, "Input"), null);
			JDFResourceLink retLink = null;
			if (v == null || v.isEmpty())
			{
				retLink = null;
			}
			else if (v.size() == 1)
			{
				retLink = (JDFResourceLink) v.get(0);
			}
			else
			{
				final VString types = getTypes();

				for (final KElement e : v)
				{
					final JDFResourceLink rl = (JDFResourceLink) e;
					final JDFIntegerList combinedProcessIndex = rl.getCombinedProcessIndex();
					if (combinedProcessIndex == null || types == null || (types != null && combinedProcessIndex.size() == types.size()))
					{
						retLink = rl;
						break;
					}
				}
				if (retLink == null)
				{
					retLink = (JDFResourceLink) v.get(0);
				}
			}
			// in case of multiple parts - grab root - else potential performance hit
			if (retLink != null)
			{
				nici = retLink.getPart(1) == null ? retLink.getTarget() : retLink.getLinkRoot();
			}
		}

		// search kids too
		if (xPath != null)
		{
			if (nici instanceof JDFResource)
			{
				final List<JDFResource> vLeaves = ((JDFResource) nici).getLeafArray(true);
				for (final KElement leaf : vLeaves)
				{
					if (leaf.hasXPathNode(xPath))
						return nici;
				}
			}

			KElement nici2 = nici;

			// continue search if not found but retain nici
			while (nici2 != null && (!nici2.hasXPathNode(xPath)))
			{
				if ((nici2 instanceof JDFResource) && !((JDFResource) nici2).isResourceRoot())
				{
					nici2 = nici2.getParentNode_KElement();
				}
				else
				{
					nici = nici2 = null;
				}
			}
		}

		if (nici != null || !bInherit)
		{
			return nici;
		}

		final JDFNode parent = getParentJDF();
		if (parent != null)
		{
			return parent.getNiCi(elementName, bInherit, xPath);
		}
		final JDFAncestorPool ap = getAncestorPool();
		if (ap != null)
		{
			return ap.getAncestorElement(elementName, null, xPath);
		}
		return null;
	}

	/**
	 * gets the existing NodeInfo or creates a new one if none exists this method will check if a NodeInfo exists,
	 *
	 * @return the found or created nodeinfo.
	 */
	public JDFNodeInfo getCreateNodeInfo()
	{
		return (JDFNodeInfo) getCreateNiCi(ElementName.NODEINFO);
	}

	/**
	 * gets the existing NodeInfo or creates a new one if none exists this method will check if a NodeInfo/CustomerInfo exists,
	 *
	 * @param s
	 *
	 * @return the found or created NodeInfo/CustomerInfo
	 */
	private KElement getCreateNiCi(final String s)
	{
		// check if this already has a Nodeinfo/CustomerInfo
		KElement nici = getNiCi(s, false, null);
		if (nici == null)
		{
			nici = appendNiCi(s);
		}
		return nici;
	}

	private KElement appendNiCi(final String s)
	{
		KElement nici;
		final EnumVersion eVer = getVersion(true);
		if (eVer == null || eVer.getValue() >= EnumVersion.Version_1_3.getValue())
		{
			nici = addResource(s, EnumUsage.Input);
		}
		else if (getNiCi(s, false, null) == null)
		{
			nici = appendElement(s);
		}
		else
		{
			throw new JDFException(s + " already exists");
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
		return (JDFNodeInfo) appendNiCi(ElementName.NODEINFO);
	}

	/**
	 * appends a NodeInfo for a given combinedprocessindex to this
	 *
	 * @param combinedProcessIndex the combinedprocessindex that must be explicitly specified in the link
	 * @return the appended NodeInfo
	 * @throws JDFException if combinedProcessIndex is outside the legal range implied by @Types
	 */
	public JDFNodeInfo appendNodeInfo(final int combinedProcessIndex)
	{
		if (combinedProcessIndex < 0 || combinedProcessIndex >= getTypes().size())
		{
			throw new JDFException("appendNodeInfo: appending ni for non existing ccombinedProcessIndex:" + combinedProcessIndex + " types=" + getTypes());
		}
		if (getNodeInfo(combinedProcessIndex) != null)
		{
			throw new JDFException("JDFNodeInfo.appendNodeInfo: NodeInfo already exists");
		}
		final JDFNodeInfo ni = (JDFNodeInfo) addResource(ElementName.NODEINFO, EnumUsage.Input);
		final JDFResourceLink rl = getLink(ni, null);
		rl.setCombinedProcessIndex(new JDFIntegerList(combinedProcessIndex));
		return ni;
	}

	/**
	 * gets the existing local NodeInfo for a given CombinedProcessIndex
	 *
	 * @param combinedProcessIndex the combinedprocessindex that must be explicitly specified in the link
	 * @return the existing NodeInfo.
	 */
	public JDFNodeInfo getNodeInfo(final int combinedProcessIndex)
	{
		if (combinedProcessIndex < 0 || combinedProcessIndex >= ContainerUtil.size(getTypes()))
		{
			if (combinedProcessIndex == 0)
				return getNodeInfo();
			else
				return null;
		}
		final JDFResourceLinkPool rlp = getResourceLinkPool();
		if (rlp == null)
		{
			return null;
		}
		final JDFResourceLink rl = (JDFResourceLink) rlp.getChildWithMatchingAttribute("NodeInfoLink", AttributeName.COMBINEDPROCESSINDEX, null,
				String.valueOf(combinedProcessIndex), 0, true, AttributeInfo.EnumAttributeType.IntegerList);
		if (rl == null)
		{
			return null;
		}
		else
			return (JDFNodeInfo) rl.getTarget();
	}

	/**
	 * gets the existing local NodeInfo if it is a resource or an element and the NodeInfo is unique for all CombinedProcessIndex values
	 *
	 * @return the existing NodeInfo, null if multiple NodeInfos exist, or the CombinedProcessIndex of the Link does not apply to the entire node
	 */
	public JDFNodeInfo getNodeInfo()
	{
		return (JDFNodeInfo) getNiCi(ElementName.NODEINFO, false, null);
	}

	/**
	 * get first NodeInfo element from child list or child list of any ancestor
	 *
	 * @param xPath the xPath to an element or attribute that must exist in the queried CustomerInfo<br>
	 *        note that attributes must be marked with an "@", if xPath=null, simply return the next in line
	 *
	 * @return JDFNodeInfo The matching NodeInfo element
	 */
	public JDFNodeInfo getInheritedNodeInfo(final String xPath)
	{
		return (JDFNodeInfo) getNiCi(ElementName.NODEINFO, true, xPath);
	}

	/**
	 * get first NodeInfo element from child list or child list of any ancestor
	 *
	 * @param xPath the xPath to an element or attribute that must exist in the queried CustomerInfo<br>
	 *        note that attributes must be marked with an "@", if xPath=null, simply return the next in line
	 *
	 * @return JDFNodeInfo The matching NodeInfo element
	 */
	JDFNodeInfo getScheduleNodeInfo(final String att)
	{
		final KElement ni = getNiCi(ElementName.NODEINFO, true, '@' + att);
		return (JDFNodeInfo) (ni == null ? JDFElement.createRoot(ElementName.NODEINFO) : ni);
	}

	/**
	 * get first NodeInfo element from child list or child list of any ancestor
	 *
	 * @return JDFNodeInfo - the element
	 * @deprecated 060221 use getInheritedNodeInfo(String xPath)
	 */
	@Deprecated
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
	 */
	public void removeCustomerInfo()
	{
		removeNiCi(ElementName.CUSTOMERINFO);
	}

	/**
	 * remove element NodeInfo or CustomerInfo, no matter whether it is an element or a resource
	 *
	 * @param elmName name of the element to remove
	 */
	private void removeNiCi(final String elmName)
	{
		// just in case : zapp them both
		removeResource(elmName, 0);
		removeChild(elmName, null, 0);
	}

	/**
	 * removes all NodeInfo elements
	 *
	 * @deprecated removes only 1 NodeInfo. In Version 1.3 the cardinality of NodeInfo
	 */
	@Deprecated
	public void removeNodeInfos()
	{
		while (numNodeInfos() > 0)
		{
			final KElement remRes = removeResource(ElementName.NODEINFO, 0);
			if (remRes == null)
			{
				// removed all in the resource pool
				break;
			}
		}

		// remove all direct childs
		final VElement nodeInfoChilds = getChildElementVector(ElementName.NODEINFO, null, null, false, Integer.MAX_VALUE, false);
		for (int i = 0; i < nodeInfoChilds.size(); i++)
		{
			removeChild(nodeInfoChilds.elementAt(i));
		}
	}

	/**
	 * removes all CustomerInfo elements whether it is an element or a resource
	 *
	 * @deprecated 060220 use removeCustomerInfo
	 */
	@Deprecated
	public void removeCustomerInfos()
	{
		// TODO hasCustomerInfo returns true if there is one or more
		// customerinfo ANYWHERE
		// so the while loop will end in an infinite loop (the break prohibit
		// this but thats not
		// realy nice (same for removeNodeInfos)!
		while (hasCustomerInfo())
		{
			final KElement remRes = removeResource(ElementName.CUSTOMERINFO, 0);
			if (remRes == null)
			{
				// remove all in the resource pool
				break;
			}
		}

		// remove all direct childs
		final VElement nodeInfoChilds = getChildElementVector(ElementName.CUSTOMERINFO, null, null, false, Integer.MAX_VALUE, false);
		for (int i = 0; i < nodeInfoChilds.size(); i++)
		{
			removeChild(nodeInfoChilds.elementAt(i));
		}
	}

	/**
	 * removes all unlinked resources
	 */
	public void eraseUnlinkedResources()
	{
		final UnLinkFinder uf = new UnLinkFinder();
		uf.eraseUnlinkedResources(this);
	}

	/**
	 * remove a type from the types list - also cleaning up combinedprocessindex
	 *
	 * @param type the type
	 * @param iSkip the index of this type in the list of identical types - typically 0
	 * @deprecated use the 3-parameter version
	 *
	 */
	@Deprecated
	public void removeFromTypes(final String type, final int iSkip)
	{
		removeFromTypes(type, iSkip, false);
	}

	/**
	 * remove a type from the types list - also cleaning up combinedprocessindex
	 *
	 * @param type the type
	 * @param iSkip the index of this type in the list of identical types - typically 0
	 * @param bRemoveEmptyLink if true, remove any reslinks that have no remaining combinedprocessindex
	 *
	 */
	public void removeFromTypes(final String type, final int iSkip, final boolean bRemoveEmptyLink)
	{
		final VString v = getTypes();
		if (v == null)
		{
			return;
		}
		int n = 0;
		int posLast = -1;
		while (n <= iSkip)
		{
			final int pos = v.indexOf(type, posLast + 1);
			posLast = pos;
			if (pos < 0)
			{
				break;
			}
			n++;
		}
		if (posLast >= 0)
		{
			v.remove(posLast);
			setTypes(v);
			final VElement vResLinks = getResourceLinks(new JDFAttributeMap(AttributeName.COMBINEDPROCESSINDEX, "*"));
			if (vResLinks != null)
			{
				for (final KElement e : vResLinks)
				{
					final JDFResourceLink rl = (JDFResourceLink) e;
					final JDFIntegerList list = rl.getCombinedProcessIndex();
					if (list != null)
					{
						final JDFIntegerList newList = new JDFIntegerList();
						final int[] ii = list.getIntArray();
						for (final int i : ii)
						{
							if (i < posLast)
							{
								newList.add(i);
							}
							else if (i > posLast)
							{
								newList.add(i - 1);
							}
							// == is obviously omitted - the type is gone

						}
						if (newList.size() > 0)
						{
							rl.setCombinedProcessIndex(newList);
						}
						else if (bRemoveEmptyLink)
						{
							removeLink(rl, true);
						}
					}
				}
			}
		}
	}

	/**
	 * removes a Resource from this ResourceLinkPool and from the resourcePool if it is no longer linked to any other process
	 *
	 * @param nodeName the Nodename of the Resource "NodeInfo" for example
	 * @param iSkip number to skip before deleting
	 * @return KElement the removed resource, null if nothing was found or deleted (e.g. 4 found and the 5th is the one to delete). The link is not returned<br>
	 *         If the link is deleted and the resource is still linked to another process, null is returned.
	 */
	public JDFResource removeResource(final String nodeName, final int iSkip)
	{
		JDFResource kRet = null;
		final JDFResourceLink rl = getLink(iSkip, nodeName, null, null);
		if (rl != null)
		{
			kRet = rl.getTarget();
			removeLink(rl, true);
		}
		return kRet;
	}

	/**
	 * Number of NodeInfo elements
	 *
	 * @return int number of matching elements
	 * @deprecated must never be more than one...
	 */
	@Deprecated
	public int numNodeInfos()
	{
		int i = numChildElements(ElementName.NODEINFO, null);

		final JDFResourceLinkPool rlp = getResourceLinkPool();
		if (rlp != null)
		{
			final VElement poolChildren = rlp.getPoolChildren("NodeInfoLink", null, null);
			if (poolChildren != null)
			{
				i += poolChildren.size();
			}
		}

		return i;
	}

	/**
	 * Number of NodeInfo elements
	 *
	 * @return int - number of matching elements
	 * @deprecated must never be more than one...
	 */
	@Deprecated
	public int numCustomerInfos()
	{
		int i = numChildElements(ElementName.CUSTOMERINFO, null);

		final JDFResourceLinkPool rlp = getResourceLinkPool();
		if (rlp != null)
		{
			final VElement poolChildren = rlp.getPoolChildren("CustomerInfoLink", null, null);
			if (poolChildren != null)
			{
				i += poolChildren.size();
			}
		}

		return i;
	}

	/**
	 * test whether either an element NodeInfo or a JDF 1.3 NodeInfo Resource exists
	 *
	 * @return true if at least one matching element exists
	 * @deprecated use getNodeInfo()!=null
	 */
	@Deprecated
	public boolean hasNodeInfo()
	{
		return getNodeInfo() != null;
	}

	/**
	 * test whether either an element CustomerInfo or a JDF 1.3 CustomerInfo Resource exists
	 *
	 * @return bool true if at least one matching element exists
	 * @deprecated use getCustomerInfo()!=null
	 */
	@Deprecated
	public boolean hasCustomerInfo()
	{
		return getCustomerInfo() != null;
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * Get element ResourceLinkPool, create if it doesn't exist
	 *
	 * @return the found/created element
	 */
	public JDFResourceLinkPool getCreateResourceLinkPool()
	{
		return (JDFResourceLinkPool) getCreateElement_KElement(ElementName.RESOURCELINKPOOL, null, 0);
	}

	/**
	 * Append a ResourceLinkPool element, return existing element if one already exist
	 *
	 * @return the ResourceLinkPool element
	 */
	public JDFResourceLinkPool appendResourceLinkPool()
	{
		return (JDFResourceLinkPool) appendElementN(ElementName.RESOURCELINKPOOL, 1, null);
	}

	/**
	 * get the first ResourceLinkPool element
	 *
	 * @return the element
	 */
	public JDFResourceLinkPool getResourceLinkPool()
	{
		return getElementByClass(JDFResourceLinkPool.class, 0, false);
	}

	/**
	 * Get element ResourcePool, create if it doesn't exist
	 *
	 * @return the found/created element
	 */
	public JDFResourcePool getCreateResourcePool()
	{
		return (JDFResourcePool) getCreateElement_KElement(ElementName.RESOURCEPOOL, null, 0);
	}

	/**
	 * append a ResourcePool element, return existing element if one already exist
	 *
	 * @return the ResourcePool element
	 */
	public JDFResourcePool appendResourcePool()
	{
		return (JDFResourcePool) appendElementN(ElementName.RESOURCEPOOL, 1, null);
	}

	/**
	 * get the first ResourcePool element
	 *
	 * @return the element
	 */
	public JDFResourcePool getResourcePool()
	{
		return getElementByClass(JDFResourcePool.class, 0, false);
	}

	/**
	 * Get element StatusPool, create if it doesn't exist
	 *
	 * @return the found/created element
	 */
	public JDFStatusPool getCreateStatusPool()
	{
		setStatus(JDFElement.EnumNodeStatus.Pool);
		return (JDFStatusPool) getCreateElement_KElement(ElementName.STATUSPOOL, null, 0);
	}

	/**
	 * append a StatusPool element, return existing element if one already exist
	 *
	 * @return the StatusPool element
	 */
	public JDFStatusPool appendStatusPool()
	{
		setStatus(JDFElement.EnumNodeStatus.Pool);
		return (JDFStatusPool) appendElementN(ElementName.STATUSPOOL, 1, null);
	}

	/**
	 * get the first StatusPool element
	 *
	 * @return the element
	 */
	public JDFStatusPool getStatusPool()
	{
		return (JDFStatusPool) getElement(ElementName.STATUSPOOL, null, 0);
	}

	/**
	 * get a Child JDFNode with a given ID attribute
	 *
	 * @param id the id of the child
	 * @param bDirect if true, only direct children, else recurse down the tree
	 *
	 * @return JDFNode - the parent JDF, null if this is the root JDF
	 *
	 * @default getChildJDFNode(id, false)
	 */
	public JDFNode getChildJDFNode(final String id, final boolean bDirect)
	{
		final JDFAttributeMap m = new JDFAttributeMap(AttributeName.ID, id);
		return (JDFNode) getTreeElement(ElementName.JDF, null, m, bDirect, true);
	}

	/**
	 * Check existence of attribute "version"
	 *
	 * @param bInherit recurse through ancestors and Ancestor elements of the AncestorPool when searching
	 * @return true if attribute Version exists
	 *
	 * @default hasVersion(false)
	 */
	public boolean hasVersion(final boolean bInherit)
	{
		if (bInherit)
		{
			return hasAncestorAttribute(AttributeName.VERSION, null);
		}
		return hasAttribute(AttributeName.VERSION, null, false);
	}

	/**
	 * set attribute "version"
	 *
	 * @param value the value to set the attribute to
	 * @deprecated use JDFElement.setVersion(EnumVersion.getEnum(value))
	 */
	@Deprecated
	public void setVersion(final String value)
	{
		setAttribute(AttributeName.VERSION, value, null);
	}

	/**
	 * get enum attribute "version"
	 *
	 * @param bInherit recurse through ancestors when searching
	 * @return EnumVersion - attribute value
	 *
	 * @default getVersion(false)
	 *
	 *          this method replaces the C++ methods GetVersion and GetEnumVersion
	 */
	@Override
	public EnumVersion getVersion(final boolean bInherit)
	{
		String version;
		if (bInherit)
		{
			version = getAncestorAttribute(AttributeName.VERSION, null, null);
		}
		else
		{
			version = getAttribute(AttributeName.VERSION, null, null);
		}

		return EnumVersion.getEnum(version);
	}

	/**
	 * clone the target resource of this and generate a ResourceAudit in the parent node's AuditPool. Both resourcelinks of the ResourceAudit are filled in.<br>
	 * The resource selected by this may now be modified. <br>
	 * The cloned copy has a new Id in the format: (thisID)_old_nnn
	 *
	 * @param resLink
	 *
	 * @return the ResourceAudit that was created
	 */
	public JDFResourceAudit cloneResourceToModify(final JDFResourceLink resLink)
	{
		JDFResourceAudit resourceAudit = null;

		final JDFResource r = resLink.getLinkRoot();
		if (r == null)
		{
			return null;
		}
		final JDFResourcePool pool = r.getParentJDF().getResourcePool();
		final JDFResource oldCopy = (JDFResource) pool.copyElement(r, null);

		if (oldCopy != null)
		{
			oldCopy.setLocked(true);
			final String newID = r.newModifiedID();
			oldCopy.setID(newID);
			resourceAudit = prepareToModifyLink(resLink);
			final JDFResourceLink resLinkAudit = (JDFResourceLink) resourceAudit.copyElement(resLink, null);

			if (resLinkAudit != null)
			{
				resLinkAudit.setrRef(newID);
			}
		}

		return resourceAudit;
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * Generate a ResourceAudit in the parent node's AuditPool an initial copy of the not yet modified resourcelink is inserted<br>
	 * call JDFResourceAudit.UpdateLink with the modified link to finalize
	 *
	 * @param resLink
	 *
	 * @return the ResourceAudit that was created
	 */
	public JDFResourceAudit prepareToModifyLink(final JDFResourceLink resLink)
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
	 *
	 * @return vector of Strings in Types, null if this may not contain multiple types
	 */
	public VString getTypes()
	{
		if (!isTypesNode())
		{
			return null;
		}
		final String types = StringUtil.getNonEmpty(getAttribute(AttributeName.TYPES, null, null));
		return types == null ? null : new VString(types, null);
	}

	/**
	 * get the Types as a vector of EnumType
	 *
	 * @return vector of enumerated types, null if extensions exist that hinder us from generating a complete vector<br>
	 *         e.g. extension types or gray box names
	 */
	public Vector<EnumType> getEnumTypes()
	{
		Vector<EnumType> vs = null;
		final VString types = getTypes();
		if (types != null)
		{
			final Iterator<String> typesIterator = types.iterator();
			while (typesIterator.hasNext())
			{
				final EnumType typ = EnumType.getEnum(typesIterator.next());
				if (typ == null)
				{
					return null;
				}
				if (vs == null)
				{
					vs = new Vector<>();
				}
				vs.add(typ);
			}
		}
		return vs;
	}

	/**
	 * get the first index of a process in types after start
	 *
	 * @param typ the Type to search for
	 * @param start the position to start searching at - generally 0
	 * @return int the position of the first occurence after start,-1 if none is found
	 */
	public int getCombinedProcessIndex(final EnumType typ, final int start)
	{
		if (typ == null)
		{
			return -1;
		}
		return getCombinedProcessIndex(typ.getName(), start);
	}

	/**
	 * get the first index of a process in types after start
	 *
	 * @param typ the Type to search for
	 * @param start the position to start searching at - generally 0
	 * @return int the position of the first occurence after start,-1 if none is found
	 */
	public int getCombinedProcessIndex(final String typ, final int start)
	{
		final VString types = getTypes();
		if (types == null)
		{
			return -1;
		}
		return types.indexOf(typ, start);
	}

	/**
	 * add typ to the types list if this is a combined node or gray box
	 *
	 * @param typ
	 */
	public void addTypes(final EnumType typ)
	{
		addTypes(typ, null, false);
	}

	/**
	 * add typ to the types list if this is a combined node or gray box
	 *
	 * @param typ
	 * @param combineType one of null, Combined or ProcessGroup
	 */
	public void addTypes(final EnumType typ, final EnumType combineType, final boolean unique)
	{
		if (EnumType.Combined.equals(combineType) || EnumType.ProcessGroup.equals(combineType))
		{
			ensureCombined(combineType);
		}
		if (!isTypesNode() || typ == null)
		{
			return;
		}
		appendAttribute(AttributeName.TYPES, typ.getName(), null, " ", unique);
	}

	/**
	 * one of Combined or ProcessGroup
	 * 
	 * @param combineType
	 */
	public void ensureCombined(final EnumType combineType)
	{
		final boolean isCombined = isTypesNode();

		if (!isCombined && !isProduct())
		{
			renameAttribute(AttributeName.TYPE, AttributeName.TYPES);
		}
		setType(combineType);

	}

	/**
	 * Gets the vector of the string Type/Types attribute values of the given JDFNode by recursively traversing the tree<br>
	 * returns exactly one element="Product" if the tested node's type is product
	 *
	 * @return VString - vector of Type/Types attributes of the tested ProcessGroup JDFNode
	 * @throws JDFException if the tested JDFNode has an illegal combination of attribute 'Types' and child JDFNodes
	 */
	public VString getAllTypes()
	{
		VString vs = new VString();
		final String myType = getType();
		if (myType.equals(JDFConstants.PRODUCT))
		{
			vs = new VString(JDFConstants.PRODUCT, null);
		}
		else if (myType.equals(JDFConstants.COMBINED))
		{
			vs = getTypes();
		}
		else if (myType.equals(JDFConstants.PROCESSGROUP))
		{
			final VElement vNodes = getvJDFNode(null, null, true);
			final VString vsTypes = getTypes();
			final int nodeSize = vNodes.size();
			for (int i = 0; i < nodeSize; i++)
			{
				final JDFNode node = (JDFNode) vNodes.elementAt(i);
				final VString allTypes = node.getAllTypes();
				if (allTypes != null)
				{
					vs.addAll(allTypes);
				}
			}
			if (vsTypes != null) // grey box or simple type
			{
				vs.addAll(vsTypes);
			}
		}
		else
		{
			final String type = myType;
			vs = new VString(type, null);
		}
		return vs;
	}

	/**
	 * set node Types , also set Type to Combined
	 *
	 * @param vCombiNodes vector of types
	 */
	public void setTypes(final VString vCombiNodes)
	{
		final EnumType type = EnumType.getEnum(getType());
		// 080408 lets be gracefull in case we are building sequentially
		if (type == null || EnumType.Combined.equals(type) || EnumType.ProcessGroup.equals(type))
		{
			setAttribute(AttributeName.TYPES, vCombiNodes, null);
		}
		else
		{
			throw new JDFException("Setting Types on illegal node Type:" + getType());
		}
	}

	/**
	 * set node Types , also set Type to Combined
	 *
	 * @param vCombiNodes vector of types
	 */
	public void setTypes(final VString vCombiNodes, final boolean isProcessGroup)
	{
		setType(isProcessGroup ? EnumType.ProcessGroup : EnumType.Combined);
		setTypes(vCombiNodes);
	}

	/**
	 * get the links that are selected by a given CombinedProcessIndex<br>
	 * all links with no CombinedProcessIndex are included in the list
	 *
	 * @param combinedProcessIndex the nTh occurence of the CombinedProcessIndex field, -1 if all valid positions are ok
	 * @return
	 *
	 * @default getLinksForCombinedProcessIndex(-1)
	 */
	public VElement getLinksForCombinedProcessIndex(final int combinedProcessIndex)
	{
		final CombinedProcessLinkHelper h = new CombinedProcessLinkHelper();
		return h.getLinksForCombinedProcessIndex(combinedProcessIndex);
	}

	/**
	 * get the links that are selected by a given CombinedProcessIndex<br>
	 * all links with no CombinedProcessIndex are included in the list
	 *
	 * @param type the process type for which to get the links
	 * @param nType the nTh occurence of the Type field, -1 if all valid positions are ok
	 * @return
	 *
	 * @default getLinksForType(type, -1)
	 */
	public VElement getLinksForType(final EnumType type, final int nType)
	{

		final CombinedProcessLinkHelper combinedProcessLinkHelper = new CombinedProcessLinkHelper();
		combinedProcessLinkHelper.setNPos(nType);
		return combinedProcessLinkHelper.getLinksForType(type);
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
	public void insertTypeInTypes(final EnumType type, int beforePos)
	{

		VString types = getTypes();
		if (types == null)
		{
			types = new VString();
		}

		final int typeSize = types.size();
		if (beforePos < 0)
		{
			beforePos = typeSize + beforePos;
		}

		if (beforePos < 0)
		{
			beforePos = 0;
		}

		if (beforePos <= typeSize) // insert somehwere within the list
		{
			final VElement vResLinks = getResourceLinks(null, new JDFAttributeMap(AttributeName.COMBINEDPROCESSINDEX, ""), null);
			if (vResLinks != null)
			{
				for (int i = 0; i < vResLinks.size(); i++)
				{
					final JDFResourceLink rl = (JDFResourceLink) vResLinks.elementAt(i);
					final int[] cpi = rl.getCombinedProcessIndex().getIntArray();
					for (int j = 0; j < cpi.length; j++)
					{
						if (cpi[j] >= beforePos)
						{
							cpi[j]++;
						}
					}

					rl.setCombinedProcessIndex(new JDFIntegerList(cpi));
				}
			}

			types.insertElementAt(type.getName(), beforePos);
		}
		else
		// append at end
		{
			types.add(type.getName());
		}

		setTypes(types);
	}

	/**
	 * Get an ordered list of the ids of the parents of this node<br>
	 * the last element in the pool is the direct parent, the second to last is the grandparent etc. The first element is the original root element.
	 *
	 * @return the list of ids in the order parent, grandparent...
	 */
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
	 *
	 * @param toMerge the previosly spawned jdf node
	 * @param urlMerge the url of the ???
	 * @param cleanPolicy how to clean up the spawn and merge audits after merging
	 * @param amountPolicy how to clean up the Resource amounts after merging
	 * @return JDFNode - the merged node in the new document<br>
	 *         note that the return value used to be boolean. The boolean value is now replaced by exceptions. This always corresponds to <code>true</code>.
	 *
	 * @throws JDFException if toMerge has already been merged
	 * @throws JDFException if toMerge was not spawned from this
	 * @throws JDFException if toMerge has no AncestorPool
	 *
	 * @default mergeJDF(toMerge, null, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.None)
	 * @deprecated use JDFMerge class
	 */
	@Deprecated
	public JDFNode mergeJDF(final JDFNode toMerge, final String urlMerge, final EnumCleanUpMerge cleanPolicy, final JDFResource.EnumAmountMerge amountPolicy)
	{
		return new JDFMerge(this).mergeJDF(toMerge, urlMerge, cleanPolicy, amountPolicy);
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * check whether a node with the same ID as one in p's ancestorpool exists in this document
	 *
	 * @param p the node to check
	 * @return true if a node with the same ID as one in p's ancestorpool exists
	 */
	public boolean hasParent(final JDFNode p)
	{
		final VString vpa = p.getAncestorIDs();
		final VString vParents = getParentIds();
		vParents.add(getID());

		if (vpa.size() == 0)
		{
			return false;
		}
		final String id = vpa.elementAt(0);
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

	// ////////////////////////////////////////////////////////////////////

	/**
	 * loop over all IDs and find the min ID that will create unique new IDs
	 *
	 * @return the new minimum ID that will generate unique IDs
	 * @deprecated - actually does more harm than good
	 */
	@Deprecated
	public int getMinID()
	{
		final VElement v = getChildrenByTagName(null, null, null, false, true, 0);
		v.add(this);

		int iMax = 0;
		final VString vIDNames = new VString("ID SpawnID MergeID NewSpawnID", null);
		final int idSize = vIDNames.size();

		final int size = v.size();
		for (int i = 0; i < size; i++)
		{
			final KElement jdfElem = v.item(i);

			for (int j = 0; j < idSize; j++)
			{
				// 4 = size of the atr vector
				// get the rightmost last 4 numerical characters as seed for
				// UniqueID()

				String strID = jdfElem.getAttribute(vIDNames.get(j), null, null);
				if (strID != null)
				{
					if (strID.length() > 7)
					{
						strID = strID.substring(strID.length() - 7); // only use the last 5 chars
					}

					final int pos = StringUtil.find_last_not_of(strID, "0123456789");

					if (pos == -1)
					{
						continue;
					}

					strID = strID.substring(pos + 1);
					strID = strID.trim();
					final int len = strID.length();

					if (strID.equals(JDFConstants.EMPTYSTRING))
					{
						continue;
					}

					int iPos = 0;
					while (iPos < len && strID.charAt(iPos) == '0')
					{
						iPos++;
					}

					if (iPos > 0)
					{
						strID = strID.substring(iPos);
					}

					if (strID.equals(JDFConstants.EMPTYSTRING))
					{
						continue;
					}

					int iS = StringUtil.parseInt(strID, 0);
					if (iS > 1000000) // not in the simple ordering
					{
						iS = iS % 1000000;
					}

					iMax = (iS > iMax) ? iS : iMax;
				}
			}
		}

		uniqueID(iMax);

		return iMax;
	}

	// ////////////////////////////////////////////////////////////////////
	/**
	 * gets the maximum job part id; note that this assumes integer job part ids return
	 *
	 * @param idPrefix
	 * @return int
	 */
	public int getMaxJobPartId(final String idPrefix)
	{
		final VElement v = getvJDFNode(null, null, false);
		final int prefixSize = idPrefix.length();
		int iMax = -1;
		final int size = v.size();
		for (int i = 0; i < size; i++)
		{
			final JDFNode e = (JDFNode) v.elementAt(i);
			String s = e.getJobPartID(false);
			if (s.equals(JDFConstants.EMPTYSTRING) || s.substring(0, prefixSize).equals(idPrefix.substring(0, prefixSize)))
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
			if (pos > 0)
			{
				s = s.substring(s.length() - pos, s.length());
			}
			if (s.equals(JDFConstants.EMPTYSTRING))
			{
				continue;
			}
			final int parseInt = StringUtil.parseInt(s, 0);
			iMax = (parseInt > iMax) ? parseInt : iMax;
		}
		return iMax;
	}

	// //////////////////////////////////////////////////////////////////////////
	// ///////////////

	/**
	 * add a JDFNode remove @Types to avoid inconsistent JDF
	 *
	 * @param typ type of JDFNode to add
	 * @return JDFNode the added JDFNode
	 */
	public JDFNode addJDFNode(final String typ)
	{
		final EnumType myType = EnumType.getEnum(getType());

		if (myType == null || !myType.equals(EnumType.Product) && !myType.equals(EnumType.ProcessGroup))
		{
			throw new JDFException("JDFNode.addJDFNode adding JDF Node to invalid node type: Type = " + getType());
		}
		final JDFNode p = (JDFNode) appendElement(ElementName.JDF, null);
		if (typ != null && !typ.equals(JDFConstants.EMPTYSTRING))
		{
			p.setType(typ, false);
		}
		if (EnumType.Product.equals(myType) || EnumType.ProcessGroup.equals(myType))
		{
			removeAttribute(AttributeName.TYPES); // otherwise we have an illegal combination
		}
		return p;
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * add a JDFNode
	 *
	 * @param typ enum type of JDFNode to add
	 * @return the added JDFNode
	 */
	public JDFNode addJDFNode(final EnumType typ)
	{
		final JDFNode p = addJDFNode((String) null);
		p.setType(typ);
		return p;
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * @param prodName
	 * @return
	 * @deprecated use addJDFNode(EnumType typ) or addJDFNode(String typ)
	 */
	@Deprecated
	public JDFNode addProcess(final String prodName)
	{
		final JDFNode p = addJDFNode(prodName);
		return p;
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * Add a process group node
	 *
	 * @param tasks types of the processgroup
	 * @return the added JDFNode
	 *
	 * @default addProcessGroup(null)
	 */
	public JDFNode addProcessGroup(final VString tasks)
	{
		final JDFNode p = addJDFNode(EnumType.ProcessGroup);
		p.setType(EnumType.ProcessGroup.getName(), false);
		if (!StringUtil.isEmpty(tasks))
		{
			p.setTypes(tasks);
		}
		return p;
	}

	// /////////////////////////////////////////////////////////////

	/**
	 * add a combined node
	 *
	 * @param tasks types of the node to add
	 * @return the added node
	 */
	public JDFNode addCombined(final VString tasks)
	{
		final JDFNode cNode = addJDFNode(EnumType.Combined);
		cNode.setTypes(tasks);
		return cNode;
	}

	/**
	 * add a product node to this
	 *
	 * @return
	 *
	 * @throws JDFException ith this is not a Product itself
	 */
	public JDFNode addProduct()
	{
		if (!EnumType.getEnum(getType()).equals(EnumType.Product))
		{
			throw new JDFException("JDFNode.AddProduct adding Product to invalid node type: Type = " + getType());
		}
		final JDFNode p = addJDFNode(EnumType.Product);
		return p;
	}

	// /////////////////////////////////////////////////////////////
	/**
	 * remove all completed nodes
	 *
	 * @return
	 *
	 * @deprecated
	 */
	@SuppressWarnings("rawtypes")
	@Deprecated
	public boolean removeCompleted()
	{
		final Vector v = getCompleted();
		for (int i = 0; i < v.size(); i++)
		{
			final JDFNode pr = (JDFNode) v.elementAt(i);
			pr.removeNode(false);
		}
		return true;
	}

	// ////////////////////////////////////////////////////////////////////
	/**
	 * get a vector with all nodes
	 *
	 * @return vector with all nodes
	 */
	public VElement getCompleted()
	{
		final VElement v = getvJDFNode(null, null, false);
		final VElement v2 = new VElement();
		final int size = v.size();
		for (int i = 0; i < size; i++)
		{
			final JDFNode pr = (JDFNode) v.elementAt(i);
			if (pr == null)
			{
				break;
			}
			if (EnumNodeStatus.Completed.equals(pr.getStatus()))
			{
				v2.addElement(pr);
			}
		}
		return v2;
	}

	// //////////////////////////////////////////////////////////////////////////
	// //////////////////

	/**
	 * Returns a resource with id anywhere in the tree below this node similar to getTarget(id) but looks only in the resource pool's direct children
	 *
	 * @param id the id of the resource
	 *
	 * @return the resource, if available
	 */
	public JDFResource getTargetResource(final String id)
	{

		final XMLDocUserData ud = getXMLDocUserData();
		if (ud != null)
		{
			final KElement e = ud.getTarget(id);
			if (e instanceof JDFResource)
			{
				return (JDFResource) e;
			}
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

		final VElement v = getvJDFNode(null, null, true);
		for (int i = 0; i < v.size(); i++)
		{
			final JDFResource r = ((JDFNode) v.elementAt(i)).getTargetResource(id);
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
	 * @param element
	 *
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @param def default value, if no matching attribute is found
	 * @since 180502
	 * @return String - value of attribute found, empty string if not available
	 */
	public String getAncestorElementAttribute(final String element, final String attrib, final String nameSpaceURI, final String def)
	{
		if (StringUtil.getNonEmpty(attrib) == null)
			return null;

		if (ElementName.NODEINFO.equals(element) || ElementName.CUSTOMERINFO.equals(element))
		{
			final String xpath = "@" + attrib;
			final KElement e = getNiCi(element, true, xpath);
			return e == null ? null : e.getAttribute(attrib, nameSpaceURI, "");
		}
		else
		{
			JDFNode n = this;
			while (n != null)
			{
				final KElement e = getElement(element, nameSpaceURI, 0);

				if ((e != null) && (e.hasAttribute(attrib, nameSpaceURI, false)))
				{
					return e.getAttribute(attrib, nameSpaceURI, null);
				}
				n = getParentJDF();
			}

			final JDFNode root = getJDFRoot();
			if (root == null)
			{
				return def;
			}
			final JDFAncestorPool ancestorPool = root.getAncestorPool();
			if (ancestorPool == null)
			{
				return def;
			}
			return ancestorPool.getAncestorElementAttribute(element, attrib, nameSpaceURI, def);
		}
	}

	/**
	 * true if a non default attribute occurs in the parent nodes and the ancestor elements subelements of the root ancestorpool exists
	 *
	 * @param element
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @since 180502
	 * @return true if the attribute exists
	 */
	public boolean hasAncestorElementAttribute(final String element, final String attrib, final String nameSpaceURI)
	{
		return getAncestorElementAttribute(element, attrib, nameSpaceURI, null) != null;
	}

	/**
	 * Get vector of linked input resource intents
	 *
	 * @return VElement vector of all input intent resources that are linked as inputs to this node
	 */
	public VElement getIntents()
	{
		VElement velem = null;

		final JDFResourceLinkPool rlp = getResourceLinkPool();

		if (rlp != null)
		{
			final JDFAttributeMap mALink = new JDFAttributeMap(AttributeName.USAGE, "Input"); // map
			// of
			// requesetd
			// link
			// attributes
			final JDFAttributeMap mARes = new JDFAttributeMap(AttributeName.CLASS, "Intent"); // map
			// of
			// requesetd
			// resource
			// attributes

			velem = rlp.getLinkedResources(null, mALink, mARes, false, null);
		}
		return velem; // grab em, don't worry about the resname
	}

	/**
	 * get a vector of ResourceLink elements that exist but are unknown by this element
	 *
	 * @param vInNameSpace list of namespaces where unknown Links are searched for<br>
	 *        if null, all namespaces are searched
	 * @param nMax maximum size of the returned vector
	 * @return VElement vector of unknown elements
	 * @since 060730 return type changed to VElement - but since the routine was utterly broken, we should be ok
	 */
	public VElement getUnknownLinkVector(final VString vInNameSpace, final int nMax)
	{
		VElement vUnknown = null;

		final VString names = linkNames();
		final VElement ve = getResourceLinks(null, null, null);
		final boolean bAllNS = vInNameSpace == null || vInNameSpace.isEmpty();

		if (vInNameSpace != null)
		{
			for (int j = 0; j < vInNameSpace.size(); j++)
			{
				// tokenize needs a blank
				if (vInNameSpace.elementAt(j).equals(JDFConstants.BLANK))
				{
					vInNameSpace.setElementAt(JDFConstants.EMPTYSTRING, j);
				}
			}
		}

		if (ve != null)
		{
			final Iterator<KElement> veIterator = ve.iterator();
			while (veIterator.hasNext())
			{
				final JDFResourceLink rl = (JDFResourceLink) veIterator.next();
				final String nodename = rl.getNodeName().substring(0, rl.getNodeName().length() - 4);

				if (bAllNS || (vInNameSpace != null && vInNameSpace.contains(xmlnsPrefix(nodename))))
				{
					if (!names.contains(nodename))
					{
						if (vUnknown == null)
						{
							vUnknown = new VElement();
						}

						vUnknown.add(rl);
						if (vUnknown.size() >= nMax)
						{
							break;
						}
					}
				}
			}
		}

		return vUnknown;
	}

	/**
	 * set attribute Category
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCategory(final String value)
	{
		setAttribute(AttributeName.CATEGORY, value);
	}

	/**
	 * get string attribute Category
	 *
	 * @return the attribute value
	 */
	public String getCategory()
	{
		return getAttribute(AttributeName.CATEGORY);
	}

	/**
	 * @param bInherit
	 * @return
	 * @deprecated - use getCategory() instead
	 */
	@Deprecated
	public String getCategory(final boolean bInherit)
	{
		if (bInherit)
		{
			return getAncestorAttribute(AttributeName.CATEGORY, null, JDFConstants.EMPTYSTRING);
		}
		return getAttribute(AttributeName.CATEGORY);
	}

	/**
	 * set attribute ICSVersions
	 *
	 * @param value the value to set the attribute to
	 */
	public void setICSVersions(final VString value)
	{
		setAttribute(AttributeName.ICSVERSIONS, value, null);
	}

	/**
	 * set attribute ICSVersions
	 *
	 * @param value the value to set the attribute to
	 */
	public VString setICSVersions(final ICSVersion... versions)
	{
		setAttribute(AttributeName.ICSVERSIONS, null);
		for (final ICSVersion v : versions)
		{
			appendICSVersion(v);
		}
		return getICSVersions(true);
	}

	public String appendICSVersion(final ICSVersion v)
	{
		return appendAttribute(AttributeName.ICSVERSIONS, v == null ? null : v.toString(), true);
	}

	/**
	 * get NMTOKENS attribute ICSVersions
	 *
	 * @param bInherit if true recurse through ancestors when searching
	 * @return VString - attribute value
	 *
	 * @default getICSVersions(false)
	 */
	public VString getICSVersions(final boolean bInherit)
	{
		if (bInherit)
		{
			return new VString(getAncestorAttribute(AttributeName.ICSVERSIONS, null, JDFConstants.EMPTYSTRING), null);
		}
		return new VString(getAttribute(AttributeName.ICSVERSIONS), null);
	}

	/**
	 * set MaxVersion to enumVer
	 *
	 * @param enumVer the EnumVersion to set
	 */
	public void setMaxVersion(final EnumVersion enumVer)
	{
		setAttribute(AttributeName.MAXVERSION, enumVer == null ? null : enumVer.getName(), null);
	}

	/**
	 *
	 * @param value the string version to set MaxVersion to
	 * @deprecated use setMaxVersion(EnumVersion)
	 */
	@Deprecated
	public void setMaxVersion(final String value)
	{
		setAttribute(AttributeName.MAXVERSION, value);
	}

	/**
	 * get attribute MaxVersion, defaults to version if not set
	 *
	 * @param bInherit if true recurse through ancestors when searching
	 * @return EnumVersion - attribute value
	 *
	 *         default - getMaxVersion(false)
	 */
	@Override
	public EnumVersion getMaxVersion(final boolean bInherit)
	{
		final String version = (bInherit) ? getAncestorAttribute(AttributeName.MAXVERSION, null, null) : getAttribute(AttributeName.MAXVERSION, null, null);

		if (version == null)
		{
			return getVersion(bInherit);
		}

		return EnumVersion.getEnum(version);
	}

	/**
	 * set attribute NamedFeatures
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNamedFeatures(final VString value)
	{
		setAttribute(AttributeName.NAMEDFEATURES, value, null);
	}

	/**
	 * Get NMTOKENS attribute NamedFeatures
	 *
	 * @return the attribute value
	 */
	public VString getNamedFeatures()
	{
		return new VString(getAttribute(AttributeName.NAMEDFEATURES, null, null), null);
	}

	/**
	 * set attribute ProjectID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRelatedJobID(final String value)
	{
		setAttribute(AttributeName.RELATEDJOBID, value);
	}

	/**
	 * get string attribute RelatedJobID
	 *
	 * @param bInherit recurse through ancestors when searching
	 * @return the attribute value
	 */
	public String getRelatedJobID(final boolean bInherit)
	{
		if (bInherit)
		{
			return getAncestorAttribute(AttributeName.RELATEDJOBID, null, JDFConstants.EMPTYSTRING);
		}
		return getAttribute(AttributeName.RELATEDJOBID);
	}

	/**
	 * set attribute RelatedJobPartID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRelatedJobPartID(final String value)
	{
		setAttribute(AttributeName.RELATEDJOBPARTID, value);
	}

	/**
	 * get string attribute RelatedJobPartID
	 *
	 * @param bInherit recurse through ancestors when searching
	 * @return the attribute value
	 */
	public String getRelatedJobPartID(final boolean bInherit)
	{
		if (bInherit)
		{
			return getAncestorAttribute(AttributeName.RELATEDJOBPARTID, null, JDFConstants.EMPTYSTRING);
		}
		return getAttribute(AttributeName.RELATEDJOBPARTID);
	}

	/**
	 * set attribute StatusDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setStatusDetails(final String value)
	{
		setAttribute(AttributeName.STATUSDETAILS, value);
	}

	/**
	 * get string attribute StatusDetails
	 *
	 * @return the attribute value
	 */
	public String getStatusDetails()
	{
		return getAttribute(AttributeName.STATUSDETAILS);
	}

	/**
	 * @param bInherit
	 * @return
	 * @deprecated - use getStatusDetails() instead
	 */
	@Deprecated
	public String getStatusDetails(final boolean bInherit)
	{
		if (bInherit)
		{
			return getAncestorAttribute(AttributeName.STATUSDETAILS, null, JDFConstants.EMPTYSTRING);
		}
		return getAttribute(AttributeName.STATUSDETAILS);
	}

	/**
	 * set attribute Template
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTemplate(final boolean value)
	{
		setAttribute(AttributeName.TEMPLATE, value, null);
	}

	/**
	 * get boolean attribute Template, default=false
	 *
	 * @return the attribute value
	 */
	public boolean getTemplate()
	{
		return getBoolAttribute(AttributeName.TEMPLATE, null, false);
	}

	/**
	 * set attribute TemplateID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTemplateID(final String value)
	{
		setAttribute(AttributeName.TEMPLATEID, value);
	}

	/**
	 * get string attribute TemplateID
	 *
	 * @param bInherit recurse through ancestors when searching
	 * @return the attribute value
	 */
	public String getTemplateID(final boolean bInherit)
	{
		if (bInherit)
		{
			return getAncestorAttribute(AttributeName.TEMPLATEID, null, JDFConstants.EMPTYSTRING);
		}
		return getAttribute(AttributeName.TEMPLATEID);
	}

	/**
	 * set attribute TemplateVersion
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTemplateVersion(final String value)
	{
		setAttribute(AttributeName.TEMPLATEVERSION, value);
	}

	/**
	 * get string attribute TemplateVersion
	 *
	 * @param bInherit recurse through ancestors when searching
	 * @return the attribute value
	 */
	public String getTemplateVersion(final boolean bInherit)
	{
		if (bInherit)
		{
			return getAncestorAttribute(AttributeName.TEMPLATEVERSION, null, JDFConstants.EMPTYSTRING);
		}
		return getAttribute(AttributeName.TEMPLATEVERSION);
	}

	/**
	 * get the NodeInfo/@workstepid for a given partition if no workstepID exists, returns jobPartID
	 *
	 * @param map
	 *
	 * @return the workstepid
	 *
	 */
	public String getWorkStepID(final JDFAttributeMap map)
	{
		JDFNodeInfo ni = getNodeInfo();
		if (ni == null)
		{
			return getJobPartID(false);
		}
		ni = (JDFNodeInfo) ni.getPartition(map, null);
		if (ni == null)
		{
			return getJobPartID(false);
		}
		final String wsID = ni.getWorkStepID();
		return isWildCard(wsID) ? getJobPartID(false) : wsID;
	}

	/**
	 * @return JDFDuration
	 */
	public JDFDuration getNodeInfoCleanupDuration()
	{
		return getScheduleNodeInfo(AttributeName.CLEANUPDURATION).getCleanupDuration();
	}

	/**
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 * @return JDFMISDetails.EnumCostType
	 */
	@Deprecated
	public JDFMISDetails.EnumCostType getNodeInfoCostType()
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return null;
		}
		final JDFMISDetails details = inheritedNodeInfo.getMISDetails();
		if (details == null)
		{
			return null;
		}
		return details.getCostType();
	}

	/**
	 * @return #
	 */
	public JDFNodeInfo.EnumDueLevel getNodeInfoDueLevel()
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return null;
		}
		return getScheduleNodeInfo(AttributeName.DUELEVEL).getDueLevel();
	}

	/**
	 * @return
	 * 
	 */
	public JDFDate getNodeInfoEnd()
	{
		return getScheduleNodeInfo(AttributeName.END).getEnd();
	}

	/**
	 * @return
	 */
	public JDFDate getNodeInfoFirstEnd()
	{
		return getScheduleNodeInfo(AttributeName.FIRSTEND).getFirstEnd();
	}

	/**
	 * @return
	 */
	public JDFDate getNodeInfoFirstStart()
	{
		return getScheduleNodeInfo(AttributeName.FIRSTSTART).getFirstStart();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 */
	@Deprecated
	public JDFXYPair getNodeInfoIPPVersion()
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return null;
		}
		return inheritedNodeInfo.getIPPVersion();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 */
	@Deprecated
	public int getNodeInfoJobPriority()
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return 0;
		}
		return inheritedNodeInfo.getJobPriority();
	}

	/**
	 * @return
	 */
	public JDFDate getNodeInfoLastEnd()
	{
		return getScheduleNodeInfo(AttributeName.LASTEND).getLastEnd();
	}

	/**
	 * @return
	 */
	public JDFDate getNodeInfoLastStart()
	{
		return getScheduleNodeInfo(AttributeName.LASTSTART).getLastStart();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 */
	@Deprecated
	public String getNodeInfoNaturalLang()
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return JDFConstants.EMPTYSTRING;
		}
		return inheritedNodeInfo.getNaturalLang();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 */
	@Deprecated
	public String getNodeInfoRoute()
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return JDFConstants.EMPTYSTRING;
		}
		return inheritedNodeInfo.getRoute();
	}

	/**
	 * 
	 * @return
	 */
	public JDFDuration getNodeInfoSetupDuration()
	{
		return getScheduleNodeInfo(AttributeName.SETUPDURATION).getSetupDuration();
	}

	/**
	 * @return
	 */
	public JDFDate getNodeInfoStart()
	{
		return getScheduleNodeInfo(AttributeName.START).getStart();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 */
	@Deprecated
	public String getNodeInfoTargetRoute()
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return JDFConstants.EMPTYSTRING;
		}
		return inheritedNodeInfo.getTargetRoute();
	}

	/**
	 * @return
	 */
	public JDFDuration getNodeInfoTotalDuration()
	{
		return getScheduleNodeInfo(AttributeName.TOTALDURATION).getTotalDuration();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 */
	@Deprecated
	public JDFMISDetails.EnumWorkType getNodeInfoWorkType()
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return null;
		}
		final JDFMISDetails details = inheritedNodeInfo.getMISDetails();
		if (details == null)
		{
			return null;
		}
		return details.getWorkType();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 */
	@Deprecated
	public String getNodeInfoWorkTypeDetails()
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return null;
		}
		final JDFMISDetails details = inheritedNodeInfo.getMISDetails();
		if (details == null)
		{
			return null;
		}
		return details.getWorkTypeDetails();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 */
	@Deprecated
	public JDFBusinessInfo getNodeInfoBusinessInfo()
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return null;
		}
		return inheritedNodeInfo.getBusinessInfo();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 */
	@Deprecated
	public JDFEmployee getNodeInfoEmployee()
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return null;
		}
		return inheritedNodeInfo.getEmployee();
	}

	/**
	 * @param iSkip
	 * @return
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 */
	@Deprecated
	public JDFJMF getNodeInfoJMF(final int iSkip)
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return null;
		}
		return inheritedNodeInfo.getJMF(iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 * @deprecated 06�221 use getInheritedNodeInfo(String attName)
	 */
	@Deprecated
	public JDFNotificationFilter getNodeInfoNotificationFilter(final int iSkip)
	{
		final JDFNodeInfo inheritedNodeInfo = getInheritedNodeInfo(null);
		if (inheritedNodeInfo == null)
		{
			return null;
		}
		return inheritedNodeInfo.getNotificationFilter(iSkip);
	}

	/**
	 * get first CustomerInfo element from child list or child list of any ancestor
	 *
	 * @param xPath the the xPath to an element or attribute that must exist in the queried CustomerInfo<br>
	 *        note that attributes must be marked with an "@", if xPath=null, simply return the next in line
	 *
	 * @return the matching CustomerInfo element
	 */
	public JDFCustomerInfo getInheritedCustomerInfo(final String xPath)
	{
		return (JDFCustomerInfo) getNiCi(ElementName.CUSTOMERINFO, true, xPath);
	}

	/**
	 * get first CustomerInfo element from child list or child list of any ancestor
	 *
	 * @deprecated 06�221 use getInheritedCustomerInfo(String xPath)
	 * @return CustomerInfo The matching CustomerInfo element
	 */
	@Deprecated
	public JDFCustomerInfo getInheritedCustomerInfo()
	{
		return getInheritedCustomerInfo(null);
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedCustomerInfo(String attName)
	 */
	@Deprecated
	public String getCustomerInfoBillingCode()
	{
		final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
		if (inheritedCustomerInfo == null)
		{
			return JDFConstants.EMPTYSTRING;
		}
		return inheritedCustomerInfo.getBillingCode();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedCustomerInfo(String attName)
	 */
	@Deprecated
	public String getCustomerInfoCustomerID()
	{
		final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
		if (inheritedCustomerInfo == null)
		{
			return JDFConstants.EMPTYSTRING;
		}
		return inheritedCustomerInfo.getCustomerID();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedCustomerInfo(String attName)
	 */
	@Deprecated
	public String getCustomerInfoCustomerJobName()
	{
		final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
		if (inheritedCustomerInfo == null)
		{
			return JDFConstants.EMPTYSTRING;
		}
		return inheritedCustomerInfo.getCustomerJobName();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedCustomerInfo(String attName)
	 */
	@Deprecated
	public String getCustomerInfoCustomerOrderID()
	{
		final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
		if (inheritedCustomerInfo == null)
		{
			return JDFConstants.EMPTYSTRING;
		}
		return inheritedCustomerInfo.getCustomerOrderID();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedCustomerInfo(String attName)
	 */
	@Deprecated
	public String getCustomerInfoCustomerProjectID()
	{
		final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
		if (inheritedCustomerInfo == null)
		{
			return JDFConstants.EMPTYSTRING;
		}
		return inheritedCustomerInfo.getCustomerProjectID();
	}

	/**
	 * @return
	 * @deprecated 06�221 use getInheritedCustomerInfo(String attName)
	 */
	@Deprecated
	public JDFCompany getCustomerInfoCompany()
	{
		final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
		if (inheritedCustomerInfo == null)
		{
			return null;
		}
		return inheritedCustomerInfo.getCompany();
	}

	/**
	 * @param iSkip
	 * @return
	 * @deprecated 06�221 use getInheritedCustomerInfo(String attName)
	 */
	@Deprecated
	public JDFContact getCustomerInfoContact(final int iSkip)
	{
		final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
		if (inheritedCustomerInfo == null)
		{
			return null;
		}
		return inheritedCustomerInfo.getContact(iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 * @deprecated 06�221 use getInheritedCustomerInfo(String attName)
	 */
	@Deprecated
	public JDFCustomerMessage getCustomerInfoCustomerMessage(final int iSkip)
	{
		final JDFCustomerInfo inheritedCustomerInfo = getInheritedCustomerInfo();
		if (inheritedCustomerInfo == null)
		{
			return null;
		}
		return inheritedCustomerInfo.getCustomerMessage(iSkip);
	}

	/**
	 * Checks if this process is the successor of the given process node.
	 *
	 * @param proc node to check against
	 *
	 * @return boolean - <code>true</code> if this process is successor of given process
	 */

	public boolean isSuccessor(final JDFNode proc)
	{
		boolean isSuccessor = false;

		if (isProcessNode() && proc.isProcessNode())
		{
			final VString vsInputResIDs = getResourceIDs(true);

			final VString vsOutputResIDs = proc.getResourceIDs(false);

			for (int i = 0; (i < vsInputResIDs.size()) && !isSuccessor; i++)
			{
				isSuccessor = vsOutputResIDs.contains(vsInputResIDs.get(i));
			}
		}

		return isSuccessor;
	}

	/**
	 * Returns the input or output resource IDs of this process node.
	 *
	 * @param isInput
	 *        <li><code>true</code> to get input resource IDs.</li>
	 *        <li><code>false</code> to get output resource IDs.</li>
	 *
	 * @return VString - Vector containing resource IDs.
	 */
	public VString getResourceIDs(final boolean isInput)
	{
		final VString vsLinks = new VString();
		final JDFResourceLinkPool linkPool = getResourceLinkPool();
		if (linkPool != null)
		{
			final VElement vInOutLinks = linkPool.getInOutLinks(isInput ? EnumUsage.Input : EnumUsage.Output, true, null, null);
			if (vInOutLinks != null)
			{
				final int nInOutLinks = vInOutLinks.size();
				for (int i = 0; i < nInOutLinks; i++)
				{
					final JDFResourceLink link = (JDFResourceLink) vInOutLinks.get(i);
					vsLinks.add(link.getrRef());
				}
			}
		}

		return vsLinks;
	}

	/**
	 * Gets the executable partitions of the resource in this node (with corresponding resource link). The part maps returned may be nested. If the empty part map is contained, the
	 * whole resource is executable.<br>
	 *
	 * Availability of a resource depends on the status, the availability of refered sub-partitions and the part usage.
	 *
	 * @param link the resource link.
	 * @param res the resource. (legacy dummy the resource is actually calculated from the link)
	 * @param minStatus minimum resource status to include.
	 * @deprecated use getExecutablePartitions(link, minStatus);
	 * @return VJDFAttributeMap - A part map vector containing the executable partitions.
	 */
	@Deprecated
	public VJDFAttributeMap getExecutablePartitions(final JDFResourceLink link, final JDFResource res, final JDFResource.EnumResStatus minStatus)
	{
		JDFResource resLocal = res;

		if (resLocal != null)
		{
			resLocal = null; // satisfy compiler
		}

		return getExecutablePartitions(link, minStatus);
	}

	/**
	 * Method getExecutablePartitions
	 *
	 * @deprecated only for backward compatibility !!!
	 *
	 * @param link
	 * @param minStatus
	 * @return
	 */

	@Deprecated
	public VJDFAttributeMap getExecutablePartitions(final JDFResourceLink link, final JDFResource.EnumResStatus minStatus)
	{
		return (getExecutablePartitions(link, minStatus, true));
	}

	/**
	 * Gets the executable partitions of the resource in this node (with corresponding resource link). The part maps returned may be nested. If the empty part map is contained, the
	 * whole resource is executable.<br>
	 *
	 * Availability of a resource depends on the status, the availability of refered sub-partitions and the part usage.
	 *
	 * @param link the resource link.
	 * @param minStatus minimum resource status to include.
	 * @param bCheckNodeStatus check node status.
	 *
	 * @return VJDFAttributeMap - A part map vector containing the executable partitions.
	 */

	public VJDFAttributeMap getExecutablePartitions(final JDFResourceLink link, final JDFResource.EnumResStatus minStatus, final boolean bCheckNodeStatus)
	{
		final VJDFAttributeMap vp = new VJDFAttributeMap();
		if (link == null)
		{
			return null;
		}
		final VElement v = link.getTargetVector(0);
		for (int i = 0; i < v.size(); i++)
		{
			final JDFResource res = (JDFResource) v.elementAt(i);
			new ExecCheck().addExecutablePartitions(link, res, res.getPartIDKeys(), vp, minStatus, bCheckNodeStatus);
		}
		vp.unify();
		return vp;
	}

	private class ExecCheck
	{
		private ExecPartFlags addExecutablePartitions(final JDFResourceLink link, final JDFResource res, final VString vsPartIDKeys, final VJDFAttributeMap vamPartMaps, final JDFResource.EnumResStatus minStatus, final boolean bCheckNodeStatus)
		{
			final JDFAttributeMap amPartMap = res.getPartMap();

			// //////////////////////////////////////////////////////
			// Check if all child partitions are executable.
			//

			final VElement veChildPartitions = res.getChildElementVector_JDFElement(res.getNodeName(), null, null, false, 0, true);

			final int nChildPartitions = veChildPartitions.size();

			// //////////////////////////////////////////////////////
			// Check if this is a leaf partition.
			//

			final boolean isLeaf = isLeaf(vsPartIDKeys, amPartMap, nChildPartitions);

			// //////////////////////////////////////////////////////
			// Check the process status.
			//

			final JDFElement.EnumNodeStatus stat = getPartStatus(amPartMap, 0);

			boolean isProcStatOK = false;
			if (bCheckNodeStatus)
			{
				if (((stat == null) && (isLeaf)) || (stat == JDFNode.EnumNodeStatus.Waiting) || (stat == JDFNode.EnumNodeStatus.Ready))
				{
					isProcStatOK = true;
				}
			}
			else
			{
				isProcStatOK = true;
			}

			final JDFResource.EnumPartUsage partUsage = res.getPartUsage();

			boolean allChildsAvailable = true;
			for (int i = 0; i < nChildPartitions; i++)
			{
				final JDFResource sub = (JDFResource) veChildPartitions.get(i);
				final JDFAttributeMap amSub = sub.getPartMap();

				if (link.overlapsResourcePartMap(amSub))
				{
					final ExecPartFlags ExecChild = addExecutablePartitions(link, sub, vsPartIDKeys, vamPartMaps, minStatus, bCheckNodeStatus);
					if (!ExecChild.isAvailable())
					{
						allChildsAvailable = false;
					}

					if (!ExecChild.isProcStatOK() && (partUsage != JDFResource.EnumPartUsage.Implicit))
					{
						isProcStatOK = false;
					}
				}
			}

			// //////////////////////////////////////////////////////
			// Determine status of resource.
			//

			final JDFResource.EnumResStatus statRes = res.getResStatus(false);
			boolean isAvailable = (statRes.getValue() >= minStatus.getValue());

			if (nChildPartitions > 0) // Non leaf
			{
				// //////////////////////////////////////////////////////
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
					if ((partUsage != JDFResource.EnumPartUsage.Implicit) || (link.hasChildElement(ElementName.PART, null)))
					{
						isAvailable = true;
					}
				}
			}

			// //////////////////////////////////////////////////////
			// Add part map if executable, and spawn is allowed.
			//

			final boolean hasResourcePartMap = link.hasResourcePartMap(amPartMap, true);

			final boolean isExecutable = hasResourcePartMap && isProcStatOK && isAvailable && res.isSpawnAllowed();

			modifyPartMap(vamPartMaps, amPartMap, isLeaf, stat, isExecutable);

			// //////////////////////////////////////////////////////
			// Return the two booleans as integer.
			//

			return new ExecPartFlags(isAvailable, isProcStatOK);
		}

		private void modifyPartMap(final VJDFAttributeMap vamPartMaps, final JDFAttributeMap amPartMap, final boolean isLeaf, final JDFElement.EnumNodeStatus stat, final boolean isExecutable)
		{
			if (isExecutable)
			{
				if ((isLeaf) && (stat == null)) // leaf with unknown PartStatus
				{
					if (getStatus() == EnumNodeStatus.Part)
					{
						final JDFNodeInfo ni = getNodeInfo();

						final VElement veParts = ni.getPartitionVector(amPartMap, JDFResource.EnumPartUsage.Implicit);

						if ((veParts == null) || veParts.isEmpty())
						{
							vamPartMaps.add(amPartMap);
						}
						else
						{
							for (int p = 0; p < veParts.size(); p++)
							{
								final JDFNodeInfo niPart = (JDFNodeInfo) veParts.elementAt(p);

								final JDFElement.EnumNodeStatus statPart = niPart.getNodeStatus();

								if ((statPart == JDFNode.EnumNodeStatus.Waiting) || (statPart == JDFNode.EnumNodeStatus.Ready))
								{
									vamPartMaps.add(niPart.getPartMap());
								}
							}
							vamPartMaps.unify();
						}
					}
				}
				else
				{
					vamPartMaps.add(amPartMap);
				}
			}
		}

		private boolean isLeaf(final VString vsPartIDKeys, final JDFAttributeMap amPartMap, final int nChildPartitions)
		{
			boolean isLeaf = false;

			if (nChildPartitions == 0)
			{
				if (vsPartIDKeys != null)
				{
					final StringArray vsMapKeys = amPartMap.getKeyList();

					if (vsMapKeys != null)
					{
						isLeaf = vsMapKeys.containsAll(vsPartIDKeys);
					}
				}
				else
				{
					isLeaf = true;
				}
			}
			return isLeaf;
		}
	}

	/**
	 * Class ExecPartFlags
	 *
	 * Returned by <code>addExecutablePartitions</code>
	 *
	 */
	private static final class ExecPartFlags
	{
		private final boolean m_isAvailable;
		private final boolean m_isProcStatOK;

		protected ExecPartFlags(final boolean isAvailable, final boolean isProcStatOK)
		{
			m_isAvailable = isAvailable;
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
	 * Gets all child process nodes. This function replaces the JDFDoc.getProcessNodes, which may be implemented as getJDFRoot.getProcessNodes();
	 *
	 * @deprecated use getvJDFNode(null,null,false) and skip intermediate nodes
	 *
	 * @return JDFNode [] - All child process nodes.
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Deprecated
	public JDFNode[] getProcessNodes()
	{
		final Vector vJDFNodes = getvJDFNode(null, null, false);

		final Vector vProcessNodes = new Vector();

		JDFNode[] processNodes = null;

		for (int i = 0; i < vJDFNodes.size(); i++)
		{
			final JDFNode jdfNode = (JDFNode) vJDFNodes.elementAt(i);

			if (jdfNode.isProcessNode())
			{
				vProcessNodes.add(jdfNode);
			}
		}

		processNodes = new JDFNode[vProcessNodes.size()];

		vProcessNodes.copyInto(processNodes);

		return processNodes;
	}

	/**
	 * Checks if this node is a simple process (including Combined and grey box ProcessGroup) leaf node.
	 *
	 * @return boolean - <code>true</code> if this is a process node.
	 */
	public boolean isProcessNode()
	{
		final EnumType typ = getEnumType();
		return !hasChildElement(ElementName.JDF, null) && !EnumType.Product.equals(typ);

	}

	/**
	 * add an internal pipe (an input and an output link to an explicitly defined exchange resource)
	 *
	 * @param resourceName The name of the reource to create
	 * @param indexOutput the CombinedProcessIndex of the output ResourceLink to the internal pipe
	 * @param indexInput the CombinedProcessIndex of the input ResourceLink to the internal pipe
	 * @return JDFResource - the newly created resource
	 */
	public JDFResource addInternalPipe(final String resourceName, final int indexOutput, final int indexInput)
	{
		if (EnumType.getEnum(getType()) != EnumType.Combined)
		{
			throw new JDFException("JDFNode.addInternalPipe: adding pipe to node that is not combined " + getType());
		}
		final JDFResource r = addResource(resourceName, null, null, null, null, null, null);
		r.setPipeProtocol(JDFConstants.INTERNAL);

		JDFResourceLink rl = linkResource(r, EnumUsage.Input, null);
		rl.setPipeProtocol(JDFConstants.INTERNAL); // redundant but not harmful
		rl.setCombinedProcessIndex(new JDFIntegerList(indexInput));

		rl = linkResource(r, EnumUsage.Output, null);
		rl.setPipeProtocol(JDFConstants.INTERNAL);// redundant but not harmful
		rl.setCombinedProcessIndex(new JDFIntegerList(indexOutput));

		return r;
	}

	/**
	 * get a heuristic partidkey vector from the partitons of the linked resources
	 *
	 * @param partMap the partmap to order. If not specified, use the output link
	 *
	 * @return the ordered vector of partIDKeys
	 */
	public VString getPartIDKeys(final JDFAttributeMap partMap)
	{
		VString matchingPartIDKeys = new VString();
		if ((partMap != null) && partMap.size() > 1)
		{
			final JDFResourceLinkPool resourceLinkPool = getResourceLinkPool();
			if (resourceLinkPool != null)
			{
				final VElement linkedResources = resourceLinkPool.getLinkedResources(null, null, null, false, null);
				final int linkedResourcesSize = linkedResources.size();
				for (int i = 0; i < linkedResourcesSize; i++)
				{
					final JDFResource resource = (JDFResource) linkedResources.elementAt(i);
					final VString partIDKeys = resource.getPartIDKeys();
					if (partIDKeys.size() >= partMap.size() && partIDKeys.containsAll(partMap.getKeyList()))
					{
						matchingPartIDKeys = partIDKeys;
						break;
					}
				}
			}
		}
		else if (partMap != null)
		{
			matchingPartIDKeys = partMap.getKeys();
		}
		if (matchingPartIDKeys.isEmpty())
		{
			// grab output link and partition nodeinfo accordingly
			VElement vRes = null;
			final JDFResourceLinkPool rp = getResourceLinkPool();
			if (rp != null)
			{
				vRes = rp.getInOutLinks(EnumUsage.Output, true, null, null);
				if (vRes != null)
				{
					for (int i = 0; i < vRes.size(); i++)
					{
						final JDFResourceLink rl = (JDFResourceLink) vRes.get(i);
						vRes.setElementAt(rl.getLinkRoot(), i);
					}
				}
			}

			// get heuristic list of partidkeys from the output
			if (vRes != null && vRes.size() > 0)
			{
				final JDFResource r = (JDFResource) vRes.elementAt(0);
				if (r != null)
				{
					final JDFResource resRoot = r.getResourceRoot();
					matchingPartIDKeys = resRoot.getPartIDKeys();
				}
			}
		}

		return matchingPartIDKeys;
	}

	/**
	 * prepare the nodeinfo for a list of parts, e.g. for a partitioned spawn if nodeinfo is prepartitioned it will return a vector of all matching leaves
	 *
	 * @param vSpawnParts the list of parts
	 *
	 * @return the vector of nodeinfo leaves
	 */
	public VElement prepareNodeInfo(final VJDFAttributeMap vSpawnParts)
	{
		// make sure we have a nodeinfo in case we have to merge stati
		final JDFNodeInfo ni = getCreateNodeInfo();
		final VElement vni = new VElement();

		if (ni.hasAttribute(AttributeName.CLASS, null, false))
		{ // it is a 1.3 style resource
			JDFAttributeMap spawnPart = new JDFAttributeMap();
			if (vSpawnParts != null && vSpawnParts.size() > 0)
			{
				for (int i = 0; i < vSpawnParts.size(); i++)
				{
					if (vSpawnParts.elementAt(i).size() > spawnPart.size())
					{
						spawnPart = vSpawnParts.elementAt(i);
					}
				}

				if (!EnumNodeStatus.Part.equals(getStatus()))
				{
					ni.setAttribute(AttributeName.NODESTATUS, getAttribute(AttributeName.STATUS));
					setStatus(EnumNodeStatus.Part);
				}

				final VString partVector = getPartIDKeys(spawnPart);
				for (int i = 0; i < vSpawnParts.size(); i++)
				{
					// in case we spawn a subset, try to get the superset list
					// first no preexisting leaves - create them
					final JDFAttributeMap partMap = vSpawnParts.elementAt(i);
					final VElement v = ni.getPartitionVector(partMap, EnumPartUsage.Explicit);
					if (v != null && v.size() > 0)
					{
						vni.addAll(v);
					}
					else
					{
						final JDFNodeInfo niLeaf = (JDFNodeInfo) ni.getCreatePartition(partMap, partVector);
						niLeaf.setAttribute(AttributeName.NODESTATUS, "Waiting");
						vni.add(niLeaf);
					}
				}
			}
			else
			// not partitioned
			{
				vni.add(ni); // simply return the 1.3 resource
			}
		}
		else
		{
			vni.add(ni); // simply return the 1.2 element
		}
		vni.unify();
		return vni;
	}

	/**
	 * getLinks - get the links matching mLinkAtt out of the resource link pool
	 *
	 * @param linkName the name of the link including or excluding the "Link", If it is omitted, it will be added
	 * @param mLinkAtt the attributes to search for
	 * @param linkNS the namespace of the link
	 *
	 * @return VElement - all elements matching the condition mLinkAtt
	 * @default getLinks(null,null,null)
	 * @deprecated - use getResourceLinks
	 */
	@Deprecated
	public VElement getLinks(final String linkName, final JDFAttributeMap mLinkAtt, final String linkNS)
	{
		return getResourceLinks(linkName, mLinkAtt, linkNS);
	}

	/**
	 * getLinks - get the links matching mLinkAtt out of the resource link pool
	 *
	 * @param linkName the name of the link including or excluding the "Link", If it is omitted, it will be added
	 * @param mLinkAtt the resourcelink attributes to search for
	 * @param linkNS the namespace of the link
	 *
	 * @return VElement - all elements matching the condition mLinkAtt,
	 * @default getLinks(null,null,null)
	 */
	public VElement getResourceLinks(String linkName, final JDFAttributeMap mLinkAtt, final String linkNS)
	{
		final JDFResourceLinkPool rlp = getResourceLinkPool();
		if (rlp == null)
		{
			return null;
		}
		if (linkName != null && !linkName.endsWith(JDFConstants.LINK))
		{
			linkName += JDFConstants.LINK;
		}

		return rlp.getPoolChildren(linkName, mLinkAtt, linkNS);
	}

	/**
	 * getLink - get the n'th link matching mLinkAtt out of the resource link pool
	 *
	 * @param index the index of the matching link
	 * @param linkName the name of the link including or excluding the "Link". If it is omitted, it will be added.
	 * @param mLinkAtt the attributes to search for
	 * @param linkNS the namespace of the link
	 *
	 * @return JDFResourceLink - the ResourceLink matching the condition mLinkAtt
	 * @default getLinks(null,null,null)
	 */
	public JDFResourceLink getLink(final int index, String linkName, final JDFAttributeMap mLinkAtt, final String linkNS)
	{
		final JDFResourceLinkPool rlp = getResourceLinkPool();
		if (rlp == null)
		{
			return null;
		}
		if (linkName != null && !linkName.endsWith(JDFConstants.LINK))
		{
			linkName += JDFConstants.LINK;
		}

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
	 * @default getLinks(null, null)
	 */
	@Deprecated
	public VElement getLinks(final String linkName, final String nameSpaceURI)
	{
		final JDFAttributeMap m = new JDFAttributeMap(AttributeName.RREF, getID());
		return getDocRoot().getChildrenByTagName(linkName, nameSpaceURI, m, false, false, 0);
	}

	/**
	 * sorts all elements alphabetically also recurses into the resourcepool and the sub JDF Node NO other sub-elements are sorted
	 *
	 * @see org.cip4.jdflib.core.KElement#sortChildren()
	 */
	@Override
	public void sortChildren()
	{
		super.sortChildren(); // KElement.sortChildren is NOT recursive
		final JDFResourcePool rp = getResourcePool();
		if (rp != null)
		{
			rp.sortChildren();
		}
		final VElement vNode = getvJDFNode(null, null, true);
		for (int i = 0; i < vNode.size(); i++)
		{
			vNode.item(i).sortChildren();
		}
	}

	/**
	 * returns all subnodes of this (including this) that match ni
	 *
	 * @param ni the Identifier to match
	 * @return
	 */
	public VElement getMatchingNodes(final NodeIdentifier ni)
	{
		final VElement v = getvJDFNode(null, null, false);
		if (ni == null)
		{
			return v;
		}

		if (v != null)
		{
			int siz = v.size();
			for (int i = siz - 1; i >= 0; i--)
			{
				final JDFNode n = (JDFNode) v.get(i);
				if (!n.getIdentifier().matches(ni))
				{
					v.remove(i);
					siz--;
				}
			}
			return siz == 0 ? null : v;
		}
		else
		{
			return null;
		}
	}

	/**
	 * links all output resources of thePreviousNode as inputs to this
	 *
	 * @param thePreviousNode
	 */
	public void linkOutputs(final JDFNode thePreviousNode)
	{
		if (thePreviousNode == null)
		{
			return;
		}
		final JDFResourceLinkPool resourceLinkPool = thePreviousNode.getResourceLinkPool();
		if (resourceLinkPool == null)
		{
			return;
		}
		final VElement v = resourceLinkPool.getInOutLinks(EnumUsage.Output, true, null, null);
		final JDFResourceLinkPool rlp = getCreateResourceLinkPool();
		for (int i = 0; i < v.size(); i++)
		{
			final JDFResourceLink rl0 = (JDFResourceLink) v.elementAt(i);
			JDFResourceLink rl = (JDFResourceLink) rlp.getChildWithAttribute(null, AttributeName.RREF, null, rl0.getrRef(), 0, true);
			if (rl == null)
			{
				final JDFResource r = rl0.getLinkRoot();
				rl = linkResource(r, EnumUsage.Input, rl0.getEnumProcessUsage());
				rl.removeAttribute(AttributeName.COMBINEDPROCESSINDEX);
			}
		}
	}

	/**
	 * synchronize the amounts of a gray box parent with the expanded jdfnode
	 */
	public void synchParentAmounts()
	{
		final JDFNode parent = getParentJDF();
		if (parent == null)
		{
			return;
		}
		final JDFResourceLinkPool parentPool = parent.getResourceLinkPool();
		final JDFResourceLinkPool linkPool = getResourceLinkPool();
		if (parentPool == null || linkPool == null)
		{
			return;
		}
		final VElement links = linkPool.getPoolChildren(null, null, null);
		if (links == null)
		{
			return;
		}
		for (int i = 0; i < links.size(); i++)
		{
			final JDFResourceLink link = (JDFResourceLink) links.get(i);
			final JDFResourceLink parentLink = (JDFResourceLink) parentPool.getChildWithAttribute(link.getLocalName(), AttributeName.RREF, null, link.getrRef(), 0, true);
			if (parentLink != null)
			{
				VJDFAttributeMap vParts = link.getPartMapVector();
				if (vParts == null)
				{
					vParts = new VJDFAttributeMap();
					vParts.add(null);
				}
				for (int j = 0; j < vParts.size(); j++)
				{
					final JDFAmountPool amountPool = link.getAmountPool();
					if (amountPool != null)
					{
						final JDFAmountPool parentAmountPool = parentLink.getCreateAmountPool();
						final VElement parts = amountPool.getMatchingPartAmountVector(vParts.elementAt(j));
						if (parts != null)
						{
							final int pSiz = parts.size();
							for (int k = 0; k < pSiz; k++)
							{
								final JDFPartAmount pa = (JDFPartAmount) parts.elementAt(k);
								if (pa.hasAttribute(AttributeName.ACTUALAMOUNT))
								{
									final VJDFAttributeMap vPAMap = pa.getPartMapVector();
									final JDFPartAmount parentPA = parentAmountPool.getCreatePartAmount(vPAMap);
									parentPA.copyAttribute(AttributeName.ACTUALAMOUNT, pa, null, null, null);
								}
							}
						}
					}
					else
					{
						parentLink.copyAttribute(AttributeName.ACTUALAMOUNT, link, null, null, null);
					}
					// if(parentLink)
				}
			}
		}
	}

	/**
	 * ensure that an auditpool with a created audit exists - base ICS fodder
	 */
	private void ensureCreated()
	{
		final JDFAuditPool ap = getCreateAuditPool();
		ap.ensureCreated();
	}

	// //////////////////////////////////////////////////////////////////////////
	// ///////

	/**
	 * make any combined or single type process to a gray box
	 *
	 * @param bExpand if true, create a parent gray box that wraps this, else simply rename this
	 */
	public void toGrayBox(final boolean bExpand)
	{
		final EnumType t = getEnumType();
		final String typeString = getType();

		if (EnumType.ProcessGroup.equals(t) || EnumType.Product.equals(t))
		{
			return;
		}
		VString types = null;
		if (!bExpand && !EnumType.Combined.equals(t))
		{
			renameAttribute(AttributeName.TYPE, AttributeName.TYPES, null, null);
		}
		else
		{
			types = getTypes();
		}

		setType(EnumType.ProcessGroup);
		if (bExpand)
		{
			final JDFNode child = addJDFNode(typeString);
			final String jobPart = getJobPartID(false);
			child.setJobPartID(jobPart);
			setJobPartID("pg." + jobPart);
			child.copyElement(getResourceLinkPool(), null);
			if (types != null)
			{
				child.setTypes(types);
			}
			final JDFAuditPool ap = child.getCreateAuditPool();
			ap.ensureCreated();
			JDFCreated c = (JDFCreated) ap.getAudit(0, EnumAuditType.Created, null, null);
			if (c == null)
			{
				c = ap.addCreated(null, null);
			}
			c.setDescriptiveName("automatically generated by toGrayBox");
		}
	}

	/**
	 * @see org.cip4.jdflib.ifaces.INodeIdentifiable#setIdentifier(org.cip4.jdflib.node.NodeIdentifier)
	 * @param ni
	 */
	@Override
	public void setIdentifier(NodeIdentifier ni)
	{
		if (ni == null)
		{
			ni = new NodeIdentifier();
		}

		setJobID(ni.getJobID());
		setJobPartID(ni.getJobPartID());
		setPartMapVector(ni.getPartMapVector());
	}

	/**
	 * sets the CommentURL this allows us to implement {@link IURLSetter} and automagically manipulate attatched commenturl files
	 *
	 * @see org.cip4.jdflib.ifaces.IURLSetter#setURL(java.lang.String)
	 */
	@Override
	public void setURL(final String url)
	{
		setCommentURL(url);
	}

	/**
	 * gets the CommentURL this allows us to implement {@link IURLSetter} and automagically manipulate attatched commenturl files
	 *
	 * @see org.cip4.jdflib.ifaces.IURLSetter#getURL()
	 */
	@Override
	public String getURL()
	{
		return getCommentURL();
	}

	/**
	 *
	 * @return the filename of this; null if not implemented
	 */
	@Override
	public String getUserFileName()
	{
		return null;
	}

	/**
	 * @see org.cip4.jdflib.core.JDFElement#setVersion(org.cip4.jdflib.core.JDFElement.EnumVersion)
	 */
	@Override
	public void setVersion(final EnumVersion enumVer)
	{
		super.setVersion(enumVer);
		final EnumVersion maxVersion = getMaxVersion(true);
		if (EnumUtil.aLessEqualsThanB(maxVersion, enumVer))
		{
			setMaxVersion(enumVer);
		}
	}

	/**
	 * parse a JDF file
	 *
	 * @param file
	 * @return the parsed JDFNode
	 */
	public static JDFNode parseFile(final File file)
	{
		final JDFDoc doc = JDFDoc.parseFile(file);
		return doc == null ? null : doc.getJDFRoot();
	}

	/**
	 * parse a JDF file
	 *
	 * @param fileName
	 * @return the parsed JDFNode
	 */
	public static JDFNode parseFile(final String fileName)
	{
		final JDFDoc doc = JDFDoc.parseFile(fileName);
		return doc == null ? null : doc.getJDFRoot();
	}

	/**
	 * parse a JDF input stream
	 *
	 * @param is
	 * @return the parsed JDFNode
	 */
	public static JDFNode parseStream(final InputStream is)
	{
		final JDFDoc doc = JDFDoc.parseStream(is);
		return doc == null ? null : doc.getJDFRoot();

	}

	/**
	 *
	 * @return
	 */
	public static JDFNode createRoot()
	{
		return new JDFDoc(ElementName.JDF).getJDFRoot();
	}

	/**
	 * convenience deep getter
	 *
	 * @param jobPartID
	 * @return
	 */
	public JDFNode getJobPart(final String jobPartID)
	{
		return getJobPart(jobPartID, null);
	}

}
