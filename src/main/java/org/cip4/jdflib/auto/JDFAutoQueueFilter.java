/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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
import java.util.List;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.jmf.JDFQueueEntryDef;
import org.cip4.jdflib.node.JDFNode.EnumActivation;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFGangSource;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JavaEnumUtil;

/**
 ***************************************************************************** class JDFAutoQueueFilter : public JDFElement
 */

public abstract class JDFAutoQueueFilter extends JDFElement
{

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[15];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ACTIVATION, 0x3333311111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumActivation.class, 0), null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.FIRSTENTRY, 0x3333111111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.GANGNAMES, 0x3333333111l, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.JOBID, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.JOBPARTID, 0x3333331111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.LASTENTRY, 0x3333111111l, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.MAXENTRIES, 0x3333333311l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[7] = new AtrInfoTable(AttributeName.MAXPRIORITY, 0x3333111111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.MINPRIORITY, 0x3333111111l, AttributeInfo.EnumAttributeType.integer, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.OLDERTHAN, 0x3333333311l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.PREVIEWUSAGES, 0x3333331111l, AttributeInfo.EnumAttributeType.enumerations,
				JavaEnumUtil.getEnum(EnumPreviewUsages.class, 0), "Separation");
		atrInfoTable[11] = new AtrInfoTable(AttributeName.NEWERTHAN, 0x3333333311l, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.QUEUEENTRYDETAILS, 0x3333333311l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumQueueEntryDetails.class, 0), "Brief");
		atrInfoTable[13] = new AtrInfoTable(AttributeName.STATUSLIST, 0x3333333311l, AttributeInfo.EnumAttributeType.enumerations,
				JavaEnumUtil.getEnum(EnumStatusList.class, 0), null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.UPDATEGRANULARITY, 0x3333331111l, AttributeInfo.EnumAttributeType.enumeration,
				JavaEnumUtil.getEnum(EnumUpdateGranularity.class, 0), null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[4];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.QUEUEENTRYDEF, 0x3333333311l);
		elemInfoTable[1] = new ElemInfoTable(ElementName.DEVICE, 0x3333333311l);
		elemInfoTable[2] = new ElemInfoTable(ElementName.GANGSOURCE, 0x3333333311l);
		elemInfoTable[3] = new ElemInfoTable(ElementName.PART, 0x3333333311l);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFAutoQueueFilter
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoQueueFilter(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQueueFilter
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoQueueFilter(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoQueueFilter
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoQueueFilter(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration strings for numPreviewUsages
	 */

	public enum EnumPreviewUsages
	{
		numPreviewUsages_3D, Animation, Separation, SeparationRaw, SeparatedThumbNail, ThumbNail, Viewable;

		public static EnumPreviewUsages getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumPreviewUsages.class, val, EnumPreviewUsages.Separation);
		}
	}

	/**
	 * Enumeration strings for numQueueEntryDetails
	 */

	public enum EnumQueueEntryDetails
	{
		None, Brief, JobPhase, JDF;

		public static EnumQueueEntryDetails getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumQueueEntryDetails.class, val, EnumQueueEntryDetails.Brief);
		}
	}

	/**
	 * Enumeration strings for numStatusList
	 */

	public enum EnumStatusList
	{
		Running, Waiting, Held, Removed, Suspended, PendingReturn, Completed, Aborted;

		public static EnumStatusList getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumStatusList.class, val, null);
		}
	}

	/**
	 * Enumeration strings for numUpdateGranularity
	 */

	public enum EnumUpdateGranularity
	{
		All, ChangesOnly;

		public static EnumUpdateGranularity getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumUpdateGranularity.class, val, null);
		}
	}/*
		 * ************************************************************************
		 * Attribute getter / setter
		 * ************************************************************************
		 */

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute Activation
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Activation
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setActivation(final EnumActivation enumVar)
	{
		setAttribute(AttributeName.ACTIVATION, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute Activation
	 *
	 * @return the value of the attribute
	 */
	public EnumActivation getActivation()
	{
		return EnumActivation.getEnum(getAttribute(AttributeName.ACTIVATION, null, null));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute FirstEntry
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute FirstEntry
	 *
	 * @param value the value to set the attribute to
	 */
	public void setFirstEntry(final String value)
	{
		setAttribute(AttributeName.FIRSTENTRY, value, null);
	}

	/**
	 * (23) get String attribute FirstEntry
	 *
	 * @return the value of the attribute
	 */
	public String getFirstEntry()
	{
		return getAttribute(AttributeName.FIRSTENTRY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute GangNames
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute GangNames
	 *
	 * @param value the value to set the attribute to
	 */
	public void setGangNames(final VString value)
	{
		setAttribute(AttributeName.GANGNAMES, value, null);
	}

	/**
	 * (21) get VString attribute GangNames
	 *
	 * @return VString the value of the attribute
	 */
	public VString getGangNames()
	{
		final VString vStrAttrib = new VString();
		final String s = getAttribute(AttributeName.GANGNAMES, null, JDFCoreConstants.EMPTYSTRING);
		vStrAttrib.setAllStrings(s, " ");
		return vStrAttrib;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute JobID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobID(final String value)
	{
		setAttribute(AttributeName.JOBID, value, null);
	}

	/**
	 * (23) get String attribute JobID
	 *
	 * @return the value of the attribute
	 */
	public String getJobID()
	{
		return getAttribute(AttributeName.JOBID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute JobPartID
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobPartID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobPartID(final String value)
	{
		setAttribute(AttributeName.JOBPARTID, value, null);
	}

	/**
	 * (23) get String attribute JobPartID
	 *
	 * @return the value of the attribute
	 */
	public String getJobPartID()
	{
		return getAttribute(AttributeName.JOBPARTID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute LastEntry
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute LastEntry
	 *
	 * @param value the value to set the attribute to
	 */
	public void setLastEntry(final String value)
	{
		setAttribute(AttributeName.LASTENTRY, value, null);
	}

	/**
	 * (23) get String attribute LastEntry
	 *
	 * @return the value of the attribute
	 */
	public String getLastEntry()
	{
		return getAttribute(AttributeName.LASTENTRY, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MaxEntries
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxEntries
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxEntries(final int value)
	{
		setAttribute(AttributeName.MAXENTRIES, value, null);
	}

	/**
	 * (15) get int attribute MaxEntries
	 *
	 * @return int the value of the attribute
	 */
	public int getMaxEntries()
	{
		return getIntAttribute(AttributeName.MAXENTRIES, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MaxPriority
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MaxPriority
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMaxPriority(final int value)
	{
		setAttribute(AttributeName.MAXPRIORITY, value, null);
	}

	/**
	 * (15) get int attribute MaxPriority
	 *
	 * @return int the value of the attribute
	 */
	public int getMaxPriority()
	{
		return getIntAttribute(AttributeName.MAXPRIORITY, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute MinPriority
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MinPriority
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMinPriority(final int value)
	{
		setAttribute(AttributeName.MINPRIORITY, value, null);
	}

	/**
	 * (15) get int attribute MinPriority
	 *
	 * @return int the value of the attribute
	 */
	public int getMinPriority()
	{
		return getIntAttribute(AttributeName.MINPRIORITY, null, 0);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute OlderThan
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute OlderThan
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setOlderThan(final JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.OLDERTHAN, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute OlderThan
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getOlderThan()
	{
		final String str = getAttribute(AttributeName.OLDERTHAN, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute PreviewUsages
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute PreviewUsages
	 *
	 * @param v List of the enumeration values
	 */
	public void setPreviewUsages(final List<EnumPreviewUsages> v)
	{
		setEnumsAttribute(AttributeName.PREVIEWUSAGES, v, null);
	}

	/**
	 * (9.2) get PreviewUsages attribute PreviewUsages
	 *
	 * @return Vector of the enumerations
	 */
	public List<EnumPreviewUsages> getPreviewUsages()
	{
		return getEnumerationsAttribute(AttributeName.PREVIEWUSAGES, null, EnumPreviewUsages.class);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute NewerThan
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute NewerThan
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setNewerThan(final JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
		{
			date = new JDFDate();
		}
		setAttribute(AttributeName.NEWERTHAN, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute NewerThan
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getNewerThan()
	{
		final String str = getAttribute(AttributeName.NEWERTHAN, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute QueueEntryDetails
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute QueueEntryDetails
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setQueueEntryDetails(final EnumQueueEntryDetails enumVar)
	{
		setAttribute(AttributeName.QUEUEENTRYDETAILS, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute QueueEntryDetails
	 *
	 * @return the value of the attribute
	 */
	public EnumQueueEntryDetails getQueueEntryDetails()
	{
		return EnumQueueEntryDetails.getEnum(getAttribute(AttributeName.QUEUEENTRYDETAILS, null, "Brief"));
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute StatusList
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5.2) set attribute StatusList
	 *
	 * @param v List of the enumeration values
	 */
	public void setStatusList(final List<EnumQueueEntryStatus> v)
	{
		setEnumsAttribute(AttributeName.STATUSLIST, v, null);
	}

	/**
	 * (9.2) get StatusList attribute StatusList
	 *
	 * @return Vector of the enumerations
	 */
	public List<EnumQueueEntryStatus> getStatusList()
	{
		return getEnumerationsAttribute(AttributeName.STATUSLIST, null, EnumQueueEntryStatus.class);
	}

	/*
	 * ---------------------------------------------------------------------
	 * Methods for Attribute UpdateGranularity
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute UpdateGranularity
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setUpdateGranularity(final EnumUpdateGranularity enumVar)
	{
		setAttribute(AttributeName.UPDATEGRANULARITY, JavaEnumUtil.getName(enumVar), null);
	}

	/**
	 * (9) get attribute UpdateGranularity
	 *
	 * @return the value of the attribute
	 */
	public EnumUpdateGranularity getUpdateGranularity()
	{
		return EnumUpdateGranularity.getEnum(getAttribute(AttributeName.UPDATEGRANULARITY, null, null));
	}

	/*
	 * ***********************************************************************
	 * Element getter / setter
	 * ***********************************************************************
	 */

	/**
	 * (24) const get element QueueEntryDef
	 *
	 * @return JDFQueueEntryDef the element
	 */
	public JDFQueueEntryDef getQueueEntryDef()
	{
		return (JDFQueueEntryDef) getElement(ElementName.QUEUEENTRYDEF, null, 0);
	}

	/**
	 * (25) getCreateQueueEntryDef
	 * 
	 * @return JDFQueueEntryDef the element
	 */
	public JDFQueueEntryDef getCreateQueueEntryDef()
	{
		return (JDFQueueEntryDef) getCreateElement_JDFElement(ElementName.QUEUEENTRYDEF, null, 0);
	}

	/**
	 * (26) getCreateQueueEntryDef
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntryDef the element
	 */
	public JDFQueueEntryDef getCreateQueueEntryDef(final int iSkip)
	{
		return (JDFQueueEntryDef) getCreateElement_JDFElement(ElementName.QUEUEENTRYDEF, null, iSkip);
	}

	/**
	 * (27) const get element QueueEntryDef
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntryDef the element
	 *         default is getQueueEntryDef(0)
	 */
	public JDFQueueEntryDef getQueueEntryDef(final int iSkip)
	{
		return (JDFQueueEntryDef) getElement(ElementName.QUEUEENTRYDEF, null, iSkip);
	}

	/**
	 * Get all QueueEntryDef from the current element
	 * 
	 * @return Collection<JDFQueueEntryDef>, null if none are available
	 */
	public Collection<JDFQueueEntryDef> getAllQueueEntryDef()
	{
		return getChildArrayByClass(JDFQueueEntryDef.class, false, 0);
	}

	/**
	 * (30) append element QueueEntryDef
	 *
	 * @return JDFQueueEntryDef the element
	 */
	public JDFQueueEntryDef appendQueueEntryDef()
	{
		return (JDFQueueEntryDef) appendElement(ElementName.QUEUEENTRYDEF, null);
	}

	/**
	 * (24) const get element Device
	 *
	 * @return JDFDevice the element
	 */
	public JDFDevice getDevice()
	{
		return (JDFDevice) getElement(ElementName.DEVICE, null, 0);
	}

	/**
	 * (25) getCreateDevice
	 * 
	 * @return JDFDevice the element
	 */
	public JDFDevice getCreateDevice()
	{
		return (JDFDevice) getCreateElement_JDFElement(ElementName.DEVICE, null, 0);
	}

	/**
	 * (26) getCreateDevice
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFDevice the element
	 */
	public JDFDevice getCreateDevice(final int iSkip)
	{
		return (JDFDevice) getCreateElement_JDFElement(ElementName.DEVICE, null, iSkip);
	}

	/**
	 * (27) const get element Device
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDevice the element
	 *         default is getDevice(0)
	 */
	public JDFDevice getDevice(final int iSkip)
	{
		return (JDFDevice) getElement(ElementName.DEVICE, null, iSkip);
	}

	/**
	 * Get all Device from the current element
	 * 
	 * @return Collection<JDFDevice>, null if none are available
	 */
	public Collection<JDFDevice> getAllDevice()
	{
		return getChildArrayByClass(JDFDevice.class, false, 0);
	}

	/**
	 * (30) append element Device
	 *
	 * @return JDFDevice the element
	 */
	public JDFDevice appendDevice()
	{
		return (JDFDevice) appendElement(ElementName.DEVICE, null);
	}

	/**
	 * (24) const get element GangSource
	 *
	 * @return JDFGangSource the element
	 */
	public JDFGangSource getGangSource()
	{
		return (JDFGangSource) getElement(ElementName.GANGSOURCE, null, 0);
	}

	/**
	 * (25) getCreateGangSource
	 * 
	 * @return JDFGangSource the element
	 */
	public JDFGangSource getCreateGangSource()
	{
		return (JDFGangSource) getCreateElement_JDFElement(ElementName.GANGSOURCE, null, 0);
	}

	/**
	 * (26) getCreateGangSource
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFGangSource the element
	 */
	public JDFGangSource getCreateGangSource(final int iSkip)
	{
		return (JDFGangSource) getCreateElement_JDFElement(ElementName.GANGSOURCE, null, iSkip);
	}

	/**
	 * (27) const get element GangSource
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFGangSource the element
	 *         default is getGangSource(0)
	 */
	public JDFGangSource getGangSource(final int iSkip)
	{
		return (JDFGangSource) getElement(ElementName.GANGSOURCE, null, iSkip);
	}

	/**
	 * Get all GangSource from the current element
	 * 
	 * @return Collection<JDFGangSource>, null if none are available
	 */
	public Collection<JDFGangSource> getAllGangSource()
	{
		return getChildArrayByClass(JDFGangSource.class, false, 0);
	}

	/**
	 * (30) append element GangSource
	 *
	 * @return JDFGangSource the element
	 */
	public JDFGangSource appendGangSource()
	{
		return (JDFGangSource) appendElement(ElementName.GANGSOURCE, null);
	}

	/**
	 * (24) const get element Part
	 *
	 * @return JDFPart the element
	 */
	public JDFPart getPart()
	{
		return (JDFPart) getElement(ElementName.PART, null, 0);
	}

	/**
	 * (25) getCreatePart
	 * 
	 * @return JDFPart the element
	 */
	public JDFPart getCreatePart()
	{
		return (JDFPart) getCreateElement_JDFElement(ElementName.PART, null, 0);
	}

	/**
	 * (26) getCreatePart
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPart the element
	 */
	public JDFPart getCreatePart(final int iSkip)
	{
		return (JDFPart) getCreateElement_JDFElement(ElementName.PART, null, iSkip);
	}

	/**
	 * (27) const get element Part
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPart the element
	 *         default is getPart(0)
	 */
	public JDFPart getPart(final int iSkip)
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
		return getChildArrayByClass(JDFPart.class, false, 0);
	}

	/**
	 * (30) append element Part
	 *
	 * @return JDFPart the element
	 */
	public JDFPart appendPart()
	{
		return (JDFPart) appendElement(ElementName.PART, null);
	}

}
