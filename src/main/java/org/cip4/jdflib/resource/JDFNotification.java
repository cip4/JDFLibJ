/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of
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
 *//**
	* ==========================================================================
	* class JDFNotification extends JDFAutoNotification
	* created 2001-09-06T10:02:57GMT+02:00
	* ==========================================================================
	* ==========================================================================
	* @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
	* @Author : sabjon@topmail.de   using a code generator
	* Warning! very preliminary test version.
	* Interface subject to change without prior notice!
	* Revision history:   ...
	*/

package org.cip4.jdflib.resource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoNotification;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.ifaces.INodeIdentifiable;
import org.cip4.jdflib.ifaces.ISignalAudit;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.util.ContainerUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 */
public class JDFNotification extends JDFAutoNotification implements INodeIdentifiable, ISignalAudit, IMatches
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFNotification
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFNotification(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFNotification
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFNotification(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFNotification
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFNotification(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	public static class EnumNotificationDetails extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumNotificationDetails(final String name)
		{
			super(name, m_startValue++);
		}

		public static EnumNotificationDetails getEnum(final String enumName)
		{
			return (EnumNotificationDetails) getEnum(EnumNotificationDetails.class, enumName);
		}

		public static EnumNotificationDetails getEnum(final int enumValue)
		{
			return (EnumNotificationDetails) getEnum(EnumNotificationDetails.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumNotificationDetails.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumNotificationDetails.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumNotificationDetails.class);
		}

		public static final EnumNotificationDetails Barcode = new EnumNotificationDetails(ElementName.BARCODE);
		public static final EnumNotificationDetails FCNKey = new EnumNotificationDetails(ElementName.FCNKEY);
		public static final EnumNotificationDetails SystemTimeSet = new EnumNotificationDetails(ElementName.SYSTEMTIMESET);
		public static final EnumNotificationDetails CounterReset = new EnumNotificationDetails(ElementName.COUNTERRESET);
		public static final EnumNotificationDetails Error = new EnumNotificationDetails(ElementName.ERROR);
		public static final EnumNotificationDetails Event = new EnumNotificationDetails(ElementName.EVENT);
		public static final EnumNotificationDetails Milestone = new EnumNotificationDetails(ElementName.MILESTONE);
	}

	/**
	 * toString()
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFNotification[  --> " + super.toString() + " ]";
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[7];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BARCODE, 0x33333333);
		elemInfoTable[1] = new ElemInfoTable(ElementName.FCNKEY, 0x33333333);
		elemInfoTable[2] = new ElemInfoTable(ElementName.SYSTEMTIMESET, 0x33333333);
		elemInfoTable[3] = new ElemInfoTable(ElementName.COUNTERRESET, 0x33333333);
		elemInfoTable[4] = new ElemInfoTable(ElementName.ERROR, 0x33333333);
		elemInfoTable[5] = new ElemInfoTable(ElementName.EVENT, 0x33333333);
		elemInfoTable[6] = new ElemInfoTable(ElementName.MILESTONE, 0x33333333);
	}
	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.TIMESTAMP, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateAdd(elemInfoTable);
	}

	/**
	 * get part map vector
	 * 
	 * @return VJDFAttributeMap: vector of mAttribute, one for each part
	 */
	@Override
	public VJDFAttributeMap getPartMapVector()
	{
		return super.getPartMapVector();
	}

	/**
	 * set all parts to those defined in vParts
	 * 
	 * @param vParts vector of attribute maps for the parts
	 */
	@Override
	public void setPartMapVector(final VJDFAttributeMap vParts)
	{
		super.setPartMapVector(vParts);
	}

	/**
	 * set all parts to those defined by mPart
	 * 
	 * @param mPart attribute map for the part to set
	 */
	@Override
	public void setPartMap(final JDFAttributeMap mPart)
	{
		super.setPartMap(mPart);
	}

	/**
	 * remove the part defined by mPart
	 * 
	 * @param mPart attribute map for the part to remove
	 */
	@Override
	public void removePartMap(final JDFAttributeMap mPart)
	{
		super.removePartMap(mPart);
	}

	/**
	 * check whether the part defined in mPart is included
	 * 
	 * @param mPart attribute map for the part to remove
	 * @return boolean - returns true if the part exists
	 */
	@Override
	public boolean hasPartMap(final JDFAttributeMap mPart)
	{
		return super.hasPartMap(mPart);
	}

	/**
	 * get element <code>Barcode</code>, create if it doesn't exist
	 * 
	 * @return JDFBarcode: the element
	 */
	public JDFBarcode getCreateBarcode()
	{
		return (JDFBarcode) getCreateNotificationDetails(EnumNotificationDetails.Barcode);
	}

	/**
	 * append element <code>Barcode</code>
	 * 
	 * @return JDFBarcode: the element
	 */
	public JDFBarcode appendBarcode()
	{
		return (JDFBarcode) appendNotificationDetails(EnumNotificationDetails.Barcode);
	}

	/**
	 * get element <code>Barcode</code>
	 * 
	 * @return JDFBarcode: the element
	 */
	public JDFBarcode getBarcode()
	{
		final JDFElement notificationDetails = getNotificationDetails();
		if (notificationDetails == null || !(notificationDetails instanceof JDFBarcode))
			return (JDFBarcode) getElement(ElementName.BARCODE);
		return (JDFBarcode) notificationDetails;
	}

	/**
	 * get element <code>FCNKey</code>, create if it doesn't exist
	 * 
	 * @return JDFFCNKey: the element
	 */
	public JDFFCNKey getCreateFCNKey()
	{
		return (JDFFCNKey) getCreateNotificationDetails(EnumNotificationDetails.FCNKey);
	}

	/**
	 * append element <code>FCNKey</code>
	 * 
	 * @return JDFFCNKey: the element
	 */
	public JDFFCNKey appendFCNKey()
	{
		return (JDFFCNKey) appendNotificationDetails(EnumNotificationDetails.FCNKey);
	}

	/**
	 * get element <code>FCNKey</code>
	 * 
	 * @return JDFFCNKey: the element
	 */
	public JDFFCNKey getFCNKey()
	{
		final JDFElement notificationDetails = getNotificationDetails();
		if (notificationDetails == null || !(notificationDetails instanceof JDFFCNKey))
			return (JDFFCNKey) getElement(ElementName.FCNKEY);
		return (JDFFCNKey) notificationDetails;
	}

	/**
	 * get element <code>SystemTimeSet</code>, create if it doesn't exist
	 * 
	 * @return JDFSystemTimeSet: the element
	 */
	public JDFSystemTimeSet getCreateSystemTimeSet()
	{
		return (JDFSystemTimeSet) getCreateNotificationDetails(EnumNotificationDetails.SystemTimeSet);
	}

	/**
	 * append element <code>SystemTimeSet</code>
	 * 
	 * @return JDFSystemTimeSet: the element
	 */
	public JDFSystemTimeSet appendSystemTimeSet()
	{
		return (JDFSystemTimeSet) appendNotificationDetails(EnumNotificationDetails.SystemTimeSet);
	}

	/**
	 * get element <code>SystemTimeSet</code>
	 * 
	 * @return JDFSystemTimeSet: the element
	 */
	public JDFSystemTimeSet getSystemTimeSet()
	{
		final JDFElement notificationDetails = getNotificationDetails();
		if (notificationDetails == null || !(notificationDetails instanceof JDFSystemTimeSet))
			return (JDFSystemTimeSet) getElement(ElementName.SYSTEMTIMESET);
		return (JDFSystemTimeSet) notificationDetails;
	}

	/**
	 * get element <code>CreateCounterReset</code>, create if it doesn't exist
	 * 
	 * @return JDFCreateCounterReset: the element
	 */
	public JDFCounterReset getCreateCounterReset()
	{
		return (JDFCounterReset) getCreateNotificationDetails(EnumNotificationDetails.CounterReset);
	}

	/**
	 * append element <code>CreateCounterReset</code>
	 * 
	 * @return JDFCreateCounterReset: the element
	 */
	public JDFCounterReset appendCounterReset()
	{
		return (JDFCounterReset) appendNotificationDetails(EnumNotificationDetails.CounterReset);
	}

	/**
	 * get element <code>CreateCounterReset</code>
	 * 
	 * @return JDFCreateCounterReset: the element
	 */
	public JDFCounterReset getCounterReset()
	{
		final JDFElement notificationDetails = getNotificationDetails();
		if (notificationDetails == null || !(notificationDetails instanceof JDFCounterReset))
			return (JDFCounterReset) getElement(ElementName.COUNTERRESET);
		return (JDFCounterReset) notificationDetails;
	}

	/**
	 * get comment text if available,
	 * 
	 * @return String the comment text, else null
	 */
	public String getCommentText()
	{
		final JDFComment c = getComment(0);
		if (c == null)
		{
			return null;
		}
		return c.getText();
	}

	/**
	 * get element <code>Error</code>, create if it doesn't exist
	 * 
	 * @return JDFError: the element
	 */
	public JDFError getCreateError()
	{
		return (JDFError) getCreateNotificationDetails(EnumNotificationDetails.Error);
	}

	/**
	 * append element <code>Error</code>
	 * 
	 * @return JDFError: the element
	 */
	public JDFError appendError()
	{
		return (JDFError) appendNotificationDetails(EnumNotificationDetails.Error);
	}

	/**
	 * get element <code>Error</code>
	 * 
	 * @return JDFError: the element
	 */
	public JDFError getError()
	{
		final KElement notificationDetails = getNotificationDetails();
		if (notificationDetails == null || !(notificationDetails instanceof JDFError))
			return (JDFError) getElement(ElementName.ERROR);

		return (JDFError) notificationDetails;
	}

	/**
	 * get element <code>Event</code>, create if it doesn't exist
	 * 
	 * @return JDFEvent: the element
	 */
	public JDFEvent getCreateEvent()
	{
		return (JDFEvent) getCreateNotificationDetails(EnumNotificationDetails.Event);
	}

	/**
	 * append element <code>Event</code>
	 * 
	 * @return JDFEvent: the element
	 */
	public JDFEvent appendEvent()
	{
		return (JDFEvent) appendNotificationDetails(EnumNotificationDetails.Event);
	}

	/**
	 * set this to an event, append the Event element and optionally the comment<br/>
	 * overwrites existing values
	 * 
	 * @param eventID Event/@EventID to set
	 * @param eventValue Event/@EventValue to set
	 * @param comment the comment text, if null no comment is set
	 * @return the newly created event
	 */
	public JDFEvent setEvent(final String eventID, final String eventValue, final String comment)
	{
		final JDFEvent event = getCreateEvent();
		if (event == null)
		{
			return null;
		}
		event.setEventID(eventID);
		event.setEventValue(eventValue);
		setCommentText(comment);
		return event;
	}

	public void setNode(final JDFNode n)
	{
		setNode(new NodeIdentifier(n));
	}

	/**
	 * @param identifier
	 */
	public void setNode(final NodeIdentifier identifier)
	{
		NodeIdentifier identifierLocal = identifier;

		if (identifierLocal == null)
		{
			identifierLocal = new NodeIdentifier((JDFNode) null);
		}

		setJobID(identifierLocal.getJobID());
		setJobPartID(identifierLocal.getJobPartID());
		setPartMapVector(identifierLocal.getPartMapVector());
	}

	/**
	 * append one of the predefined notification details
	 * 
	 * @param details
	 * @return
	 */
	public JDFElement appendNotificationDetails(final EnumNotificationDetails details)
	{
		final EnumNotificationDetails det = getNotificationDetailsType();
		if (det != null && !det.equals(details))
		{
			return null;
		}
		setType(details.getName());
		return (JDFElement) appendElementN(details.getName(), 1, null);
	}

	/**
	 * append one of the predefined notification details
	 * 
	 * @param details
	 * @return
	 */
	public JDFElement getCreateNotificationDetails(final EnumNotificationDetails details)
	{
		final JDFElement e = getNotificationDetails();
		if (e == null)
		{
			return appendNotificationDetails(details);
		}
		if (!details.getName().equals(e.getLocalName()))
		{
			return null;
		}
		setType(details.getName());
		return e;
	}

	/**
	 * get the predefined notification details
	 * 
	 * @param details
	 * @return
	 */
	public JDFElement getNotificationDetails()
	{
		final EnumNotificationDetails det = getNotificationDetailsType();
		if (det == null)
		{
			return null;
		}
		return (JDFElement) getElement(det.getName(), null, 0);
	}

	/**
	 * @return
	 */
	public EnumNotificationDetails getNotificationDetailsType()
	{
		final String s = getType();
		if (isWildCard(s))
		{
			return null;
		}
		return EnumNotificationDetails.getEnum(s);
	}

	/**
	 * get element <code>Event</code>
	 * 
	 * @return JDFEvent: the element
	 */
	public JDFEvent getEvent()
	{
		final JDFElement notificationDetails = getNotificationDetails();
		if (notificationDetails == null || !(notificationDetails instanceof JDFEvent))
			return (JDFEvent) getElement(ElementName.EVENT);
		return (JDFEvent) notificationDetails;
	}

	/**
	 * get element <code>Milestone</code>, create if it doesn't exist
	 * 
	 * @return JDFMilestone: the element
	 */
	public JDFMilestone getCreateMilestone()
	{
		return (JDFMilestone) getCreateNotificationDetails(EnumNotificationDetails.Milestone);
	}

	/**
	 * append element <code>Milestone</code>
	 * 
	 * @return JDFMilestone: the element
	 */
	public JDFMilestone appendMilestone()
	{
		return (JDFMilestone) appendNotificationDetails(EnumNotificationDetails.Milestone);
	}

	/**
	 * get element <code>Milestone</code>
	 * 
	 * @return JDFMilestone: the element
	 */
	public JDFMilestone getMilestone()
	{
		final JDFElement notificationDetails = getNotificationDetails();
		if (notificationDetails == null || !(notificationDetails instanceof JDFMilestone))
			return (JDFMilestone) getElement(ElementName.MILESTONE);
		return (JDFMilestone) notificationDetails;
	}

	/**
	 * gets the NodeIdetifier that matches this
	 * 
	 * @return the matching node identifier
	 */
	@Override
	public NodeIdentifier getIdentifier()
	{
		return new NodeIdentifier(getJobID(), getJobPartID(), getPartMapVector());
	}

	/**
	 * @see org.cip4.jdflib.ifaces.INodeIdentifiable#setIdentifier(org.cip4.jdflib.node.JDFNode.NodeIdentifier)
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
	 * creates a JDFJMF that corresponds to this
	 * 
	 * @see org.cip4.jdflib.ifaces.ISignalAudit#toSignalJMF()
	 */
	@Override
	public JDFJMF toSignalJMF()
	{
		final JDFJMF newJMF = JDFJMF.createJMF(EnumFamily.Signal, EnumType.Notification);
		newJMF.getSignal(0).copyElement(this, null);
		return newJMF;
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IMatches#matches(java.lang.Object)
	 */
	@Override
	public boolean matches(final Object subset)
	{
		if (!(subset instanceof JDFNotification))
			return false;
		final JDFNotification other = (JDFNotification) subset;
		final JDFAttributeMap map = getAttributeMap();
		final JDFAttributeMap map2 = other.getAttributeMap();

		map.remove(AttributeName.ID);
		map2.remove(AttributeName.ID);
		map.remove(AttributeName.TIMESTAMP);
		map2.remove(AttributeName.TIMESTAMP);
		if (!map.equals(map2))
			return false;

		if (!ContainerUtil.matches(getEmployee(0), other.getEmployee(0)))
			return false;
		if (!ContainerUtil.equals(getNotificationDetails(), other.getNotificationDetails()))
			return false;
		if (!ContainerUtil.matches(getCostCenter(), other.getCostCenter()))
			return false;
		return true;

	}

	/**
	 * SetSeverity
	 * 
	 * @param s
	 */
	@Override
	public void setSeverity(final EnumSeverity s)
	{
		setAttribute(JDFConstants.SEVERITY, s, null);
	}

	/**
	 * GetSeverity
	 * 
	 * @return EnumSeverity
	 */
	@Override
	public EnumSeverity getSeverity()
	{
		return EnumSeverity.getEnum(getAttribute(JDFConstants.SEVERITY));
	}

} // class JDFNotification
	// ==========================================================================
