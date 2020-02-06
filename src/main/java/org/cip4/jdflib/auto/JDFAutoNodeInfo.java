/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */

package org.cip4.jdflib.auto;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.resource.JDFGangSource;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFBusinessInfo;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFMISDetails;
import org.cip4.jdflib.resource.process.JDFNotificationFilter;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;

/**
 *****************************************************************************
 * class JDFAutoNodeInfo : public JDFResource
 *****************************************************************************
 *
 */

public abstract class JDFAutoNodeInfo extends JDFResource
{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFAutoNodeInfo
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	protected JDFAutoNodeInfo(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoNodeInfo
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	protected JDFAutoNodeInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAutoNodeInfo
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	protected JDFAutoNodeInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @return true if ok
	 */
	@Override
	public boolean init()
	{
		final boolean bRet = super.init();
		setResourceClass(JDFResource.EnumResourceClass.Parameter);
		return bRet;
	}

	/**
	 * @return the resource Class
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Parameter;
	}

	/**
	 * Enumeration strings for DueLevel
	 */

	@SuppressWarnings("rawtypes")
	public static class EnumDueLevel extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumDueLevel(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumDueLevel getEnum(final String enumName)
		{
			return (EnumDueLevel) getEnum(EnumDueLevel.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumDueLevel getEnum(final int enumValue)
		{
			return (EnumDueLevel) getEnum(EnumDueLevel.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDueLevel.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDueLevel.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDueLevel.class);
		}

		/**  */
		public static final EnumDueLevel Unknown = new EnumDueLevel("Unknown");
		/**  */
		public static final EnumDueLevel Trivial = new EnumDueLevel("Trivial");
		/**  */
		public static final EnumDueLevel Penalty = new EnumDueLevel("Penalty");
		/**  */
		public static final EnumDueLevel JobCancelled = new EnumDueLevel("JobCancelled");
	}

	/*
	 * ************************************************************************ Attribute getter / setter ************************************************************************
	 */

	/*
	 * --------------------------------------------------------------------- Methods for Attribute JobPriority ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute JobPriority
	 *
	 * @param value the value to set the attribute to
	 */
	public void setJobPriority(final int value)
	{
		setAttribute(AttributeName.JOBPRIORITY, value, null);
	}

	/**
	 * (15) get int attribute JobPriority
	 *
	 * @return int the value of the attribute
	 */
	public int getJobPriority()
	{
		return getIntAttribute(AttributeName.JOBPRIORITY, null, 50);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute CleanupDuration ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute CleanupDuration
	 *
	 * @param value the value to set the attribute to
	 */
	public void setCleanupDuration(final JDFDuration value)
	{
		setAttribute(AttributeName.CLEANUPDURATION, value, null);
	}

	/**
	 * (20) get JDFDuration attribute CleanupDuration
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getCleanupDuration()
	{
		final String strAttrName = getAttribute(AttributeName.CLEANUPDURATION, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute DueLevel ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute DueLevel
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setDueLevel(final EnumDueLevel enumVar)
	{
		setAttribute(AttributeName.DUELEVEL, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute DueLevel
	 *
	 * @return the value of the attribute
	 */
	public EnumDueLevel getDueLevel()
	{
		return EnumDueLevel.getEnum(getAttribute(AttributeName.DUELEVEL, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute End ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute End
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setEnd(final JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
			date = new JDFDate();
		setAttribute(AttributeName.END, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute End
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getEnd()
	{
		final String str = getAttribute(AttributeName.END, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FirstEnd ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute FirstEnd
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setFirstEnd(final JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
			date = new JDFDate();
		setAttribute(AttributeName.FIRSTEND, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute FirstEnd
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getFirstEnd()
	{
		final String str = getAttribute(AttributeName.FIRSTEND, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute FirstStart ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute FirstStart
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setFirstStart(final JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
			date = new JDFDate();
		setAttribute(AttributeName.FIRSTSTART, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute FirstStart
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getFirstStart()
	{
		final String str = getAttribute(AttributeName.FIRSTSTART, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute IPPVersion ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute IPPVersion
	 *
	 * @param value the value to set the attribute to
	 */
	public void setIPPVersion(final JDFXYPair value)
	{
		setAttribute(AttributeName.IPPVERSION, value, null);
	}

	/**
	 * (20) get JDFXYPair attribute IPPVersion
	 *
	 * @return JDFXYPair the value of the attribute, null if a the attribute value is not a valid to create a JDFXYPair
	 */
	public JDFXYPair getIPPVersion()
	{
		final String strAttrName = getAttribute(AttributeName.IPPVERSION, null, null);
		final JDFXYPair nPlaceHolder = JDFXYPair.createXYPair(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LastEnd ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute LastEnd
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setLastEnd(final JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
			date = new JDFDate();
		setAttribute(AttributeName.LASTEND, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute LastEnd
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getLastEnd()
	{
		final String str = getAttribute(AttributeName.LASTEND, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute LastStart ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute LastStart
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setLastStart(final JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
			date = new JDFDate();
		setAttribute(AttributeName.LASTSTART, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute LastStart
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getLastStart()
	{
		final String str = getAttribute(AttributeName.LASTSTART, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NaturalLang ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NaturalLang
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNaturalLang(final String value)
	{
		setAttribute(AttributeName.NATURALLANG, value, null);
	}

	/**
	 * (23) get String attribute NaturalLang
	 *
	 * @return the value of the attribute
	 */
	public String getNaturalLang()
	{
		return getAttribute(AttributeName.NATURALLANG, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NodeStatus ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute NodeStatus
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setNodeStatus(final EnumNodeStatus enumVar)
	{
		setAttribute(AttributeName.NODESTATUS, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute NodeStatus
	 *
	 * @return the value of the attribute
	 */
	public EnumNodeStatus getNodeStatus()
	{
		return EnumNodeStatus.getEnum(getAttribute(AttributeName.NODESTATUS, null, null));
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute NodeStatusDetails ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute NodeStatusDetails
	 *
	 * @param value the value to set the attribute to
	 */
	public void setNodeStatusDetails(final String value)
	{
		setAttribute(AttributeName.NODESTATUSDETAILS, value, null);
	}

	/**
	 * (23) get String attribute NodeStatusDetails
	 *
	 * @return the value of the attribute
	 */
	public String getNodeStatusDetails()
	{
		return getAttribute(AttributeName.NODESTATUSDETAILS, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute MergeTarget ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute MergeTarget
	 *
	 * @param value the value to set the attribute to
	 */
	public void setMergeTarget(final boolean value)
	{
		setAttribute(AttributeName.MERGETARGET, value, null);
	}

	/**
	 * (18) get boolean attribute MergeTarget
	 *
	 * @return boolean the value of the attribute
	 */
	public boolean getMergeTarget()
	{
		return getBoolAttribute(AttributeName.MERGETARGET, null, false);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Route ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute Route
	 *
	 * @param value the value to set the attribute to
	 */
	public void setRoute(final String value)
	{
		setAttribute(AttributeName.ROUTE, value, null);
	}

	/**
	 * (23) get String attribute Route
	 *
	 * @return the value of the attribute
	 */
	public String getRoute()
	{
		return getAttribute(AttributeName.ROUTE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute SetupDuration ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute SetupDuration
	 *
	 * @param value the value to set the attribute to
	 */
	public void setSetupDuration(final JDFDuration value)
	{
		setAttribute(AttributeName.SETUPDURATION, value, null);
	}

	/**
	 * (20) get JDFDuration attribute SetupDuration
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getSetupDuration()
	{
		final String strAttrName = getAttribute(AttributeName.SETUPDURATION, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Start ---------------------------------------------------------------------
	 */
	/**
	 * (11) set attribute Start
	 *
	 * @param value the value to set the attribute to or null
	 */
	public void setStart(final JDFDate value)
	{
		JDFDate date = value;
		if (date == null)
			date = new JDFDate();
		setAttribute(AttributeName.START, date.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute Start
	 *
	 * @return JDFDate the value of the attribute
	 */
	public JDFDate getStart()
	{
		final String str = getAttribute(AttributeName.START, null, null);
		final JDFDate ret = JDFDate.createDate(str);
		return ret;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TargetRoute ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TargetRoute
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTargetRoute(final String value)
	{
		setAttribute(AttributeName.TARGETROUTE, value, null);
	}

	/**
	 * (23) get String attribute TargetRoute
	 *
	 * @return the value of the attribute
	 */
	public String getTargetRoute()
	{
		return getAttribute(AttributeName.TARGETROUTE, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute TotalDuration ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute TotalDuration
	 *
	 * @param value the value to set the attribute to
	 */
	public void setTotalDuration(final JDFDuration value)
	{
		setAttribute(AttributeName.TOTALDURATION, value, null);
	}

	/**
	 * (20) get JDFDuration attribute TotalDuration
	 *
	 * @return JDFDuration the value of the attribute, null if a the attribute value is not a valid to create a JDFDuration
	 */
	public JDFDuration getTotalDuration()
	{
		final String strAttrName = getAttribute(AttributeName.TOTALDURATION, null, null);
		final JDFDuration nPlaceHolder = JDFDuration.createDuration(strAttrName);
		return nPlaceHolder;
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute WorkStepID ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute WorkStepID
	 *
	 * @param value the value to set the attribute to
	 */
	public void setWorkStepID(final String value)
	{
		setAttribute(AttributeName.WORKSTEPID, value, null);
	}

	/**
	 * (23) get String attribute WorkStepID
	 *
	 * @return the value of the attribute
	 */
	public String getWorkStepID()
	{
		return getAttribute(AttributeName.WORKSTEPID, null, JDFCoreConstants.EMPTYSTRING);
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute rRefs ---------------------------------------------------------------------
	 */
	/**
	 * (36) set attribute rRefs
	 *
	 * @param value the value to set the attribute to
	 */
	public void setrRefs(final VString value)
	{
		setAttribute(AttributeName.RREFS, value, null);
	}

	/*
	 * *********************************************************************** Element getter / setter ***********************************************************************
	 */

	/**
	 * (24) const get element BusinessInfo
	 *
	 * @return JDFBusinessInfo the element
	 */
	public JDFBusinessInfo getBusinessInfo()
	{
		return (JDFBusinessInfo) getElement(ElementName.BUSINESSINFO, null, 0);
	}

	/**
	 * (25) getCreateBusinessInfo
	 *
	 * @return JDFBusinessInfo the element
	 */
	public JDFBusinessInfo getCreateBusinessInfo()
	{
		return (JDFBusinessInfo) getCreateElement_JDFElement(ElementName.BUSINESSINFO, null, 0);
	}

	/**
	 * (29) append element BusinessInfo
	 *
	 * @return JDFBusinessInfo the element @ if the element already exists
	 */
	public JDFBusinessInfo appendBusinessInfo()
	{
		return (JDFBusinessInfo) appendElementN(ElementName.BUSINESSINFO, 1, null);
	}

	/**
	 * (24) const get element Employee
	 *
	 * @return JDFEmployee the element
	 */
	public JDFEmployee getEmployee()
	{
		return (JDFEmployee) getElement(ElementName.EMPLOYEE, null, 0);
	}

	/**
	 * (25) getCreateEmployee
	 *
	 * @return JDFEmployee the element
	 */
	public JDFEmployee getCreateEmployee()
	{
		return (JDFEmployee) getCreateElement_JDFElement(ElementName.EMPLOYEE, null, 0);
	}

	/**
	 * (29) append element Employee
	 *
	 * @return JDFEmployee the element @ if the element already exists
	 */
	public JDFEmployee appendEmployee()
	{
		return (JDFEmployee) appendElementN(ElementName.EMPLOYEE, 1, null);
	}

	/**
	 * (31) create inter-resource link to refTarget
	 *
	 * @param refTarget the element that is referenced
	 */
	public void refEmployee(final JDFEmployee refTarget)
	{
		refElement(refTarget);
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
	 * @return JDFGangSource the element default is getGangSource(0)
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
	 * (26) getCreateJMF
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFJMF the element
	 */
	public JDFJMF getCreateJMF(final int iSkip)
	{
		return (JDFJMF) getCreateElement_JDFElement(ElementName.JMF, null, iSkip);
	}

	/**
	 * (27) const get element JMF
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFJMF the element default is getJMF(0)
	 */
	public JDFJMF getJMF(final int iSkip)
	{
		return (JDFJMF) getElement(ElementName.JMF, null, iSkip);
	}

	/**
	 * Get all JMF from the current element
	 *
	 * @return Collection<JDFJMF>, null if none are available
	 */
	public Collection<JDFJMF> getAllJMF()
	{
		return getChildArrayByClass(JDFJMF.class, false, 0);
	}

	/**
	 * (30) append element JMF
	 *
	 * @return JDFJMF the element
	 */
	public JDFJMF appendJMF()
	{
		return (JDFJMF) appendElement(ElementName.JMF, null);
	}

	/**
	 * (24) const get element MISDetails
	 *
	 * @return JDFMISDetails the element
	 */
	public JDFMISDetails getMISDetails()
	{
		return (JDFMISDetails) getElement(ElementName.MISDETAILS, null, 0);
	}

	/**
	 * (25) getCreateMISDetails
	 *
	 * @return JDFMISDetails the element
	 */
	public JDFMISDetails getCreateMISDetails()
	{
		return (JDFMISDetails) getCreateElement_JDFElement(ElementName.MISDETAILS, null, 0);
	}

	/**
	 * (29) append element MISDetails
	 *
	 * @return JDFMISDetails the element @ if the element already exists
	 */
	public JDFMISDetails appendMISDetails()
	{
		return (JDFMISDetails) appendElementN(ElementName.MISDETAILS, 1, null);
	}

	/**
	 * (26) getCreateNotificationFilter
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNotificationFilter the element
	 */
	public JDFNotificationFilter getCreateNotificationFilter(final int iSkip)
	{
		return (JDFNotificationFilter) getCreateElement_JDFElement(ElementName.NOTIFICATIONFILTER, null, iSkip);
	}

	/**
	 * (27) const get element NotificationFilter
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNotificationFilter the element default is getNotificationFilter(0)
	 */
	public JDFNotificationFilter getNotificationFilter(final int iSkip)
	{
		return (JDFNotificationFilter) getElement(ElementName.NOTIFICATIONFILTER, null, iSkip);
	}

	/**
	 * Get all NotificationFilter from the current element
	 *
	 * @return Collection<JDFNotificationFilter>, null if none are available
	 */
	public Collection<JDFNotificationFilter> getAllNotificationFilter()
	{
		return getChildArrayByClass(JDFNotificationFilter.class, false, 0);
	}

	/**
	 * (30) append element NotificationFilter
	 *
	 * @return JDFNotificationFilter the element
	 */
	public JDFNotificationFilter appendNotificationFilter()
	{
		return (JDFNotificationFilter) appendElement(ElementName.NOTIFICATIONFILTER, null);
	}

}// end namespace JDF
