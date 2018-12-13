/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of
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
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFMessage.java
 *
 * Last changes
 *
 * 2002-07-02 JG added IsValidMessageElement() and all detailed mesaage element manipulation
 *
 */

package org.cip4.jdflib.jmf;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoMessage;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFDeviceList;
import org.cip4.jdflib.resource.JDFQueueEntryDefList;
import org.cip4.jdflib.resource.process.JDFNotificationFilter;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
 * super class for all message families Signal, Command,...
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class JDFMessage extends JDFAutoMessage
{
	private static final long serialVersionUID = 1L;
	private static boolean strictValidation = true;
	private static VString families = null;

	/**
	 * if true, all typesafe calls are strictly validated
	 *
	 * @return the strictValidation
	 */
	public static boolean isStrictValidation()
	{
		return strictValidation;
	}

	/**
	 * set false to switch off jmf validation when adding elements set true (default) to enable run time checking when constructing JMF messages
	 *
	 * @param strictValidation the strictValidation to set
	 */
	public static void setStrictValidation(final boolean strictValidation)
	{
		JDFMessage.strictValidation = strictValidation;
	}

	/**
	 * Constructor for JDFMessage
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFMessage(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFMessage
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFMessage(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFMessage
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFMessage(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[2];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.XSITYPE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.SENDERID, 0x33333111, AttributeInfo.EnumAttributeType.shortString, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.EMPLOYEE, 0x33331111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Enumerations for message families
	 *
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 *
	 *         May 17, 2009
	 */
	@SuppressWarnings("unchecked")
	public static class EnumFamily extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumFamily(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumFamily getEnum(final String enumName)
		{
			return (EnumFamily) getEnum(EnumFamily.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumFamily getEnum(final int enumValue)
		{
			return (EnumFamily) getEnum(EnumFamily.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map<String, EnumFamily> getEnumMap()
		{
			return getEnumMap(EnumFamily.class);
		}

		/**
		 * @return
		 */
		public static List<EnumFamily> getEnumList()
		{
			return getEnumList(EnumFamily.class);
		}

		/**
		 * @return
		 */
		public static VString getFamilies()
		{
			if (families == null)
			{
				families = new VString();
				final List<EnumFamily> enumList = getEnumList(EnumFamily.class);

				for (final EnumFamily f : enumList)
				{
					families.add(f.getName());
				}
				families.sort();
			}
			return families;
		}

		/**
		 * @return
		 */
		public static Iterator<EnumFamily> iterator()
		{
			return iterator(EnumFamily.class);
		}

		/**
		 *
		 */
		public static final EnumFamily Query = new EnumFamily(ElementName.QUERY);
		/**
		 *
		 */
		public static final EnumFamily Signal = new EnumFamily(ElementName.SIGNAL);
		/**
		 *
		 */
		public static final EnumFamily Command = new EnumFamily(ElementName.COMMAND);
		/**
		 *
		 */
		public static final EnumFamily Response = new EnumFamily(ElementName.RESPONSE);
		/**
		 *
		 */
		public static final EnumFamily Acknowledge = new EnumFamily(ElementName.ACKNOWLEDGE);
		/**
		 *
		 */
		public static final EnumFamily Registration = new EnumFamily(ElementName.REGISTRATION);
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 *
	 *         < July 21, 2009
	 */
	@SuppressWarnings("unchecked")
	public static class EnumType extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumType(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumType getEnum(final String enumName)
		{
			return (EnumType) getEnum(EnumType.class, enumName);
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
		public static Map<String, EnumType> getEnumMap()
		{
			return getEnumMap(EnumType.class);
		}

		/**
		 * @return
		 */
		public static List<EnumType> getEnumList()
		{
			return getEnumList(EnumType.class);
		}

		/**
		 * @return
		 */
		public static Iterator<EnumType> iterator()
		{
			return iterator(EnumType.class);
		}

		/**
		 *
		 */
		public static final EnumType AbortQueueEntry = new EnumType("AbortQueueEntry");
		/**
		 *
		 */
		public static final EnumType CloseQueue = new EnumType("CloseQueue");
		/**
		 *
		 */
		public static final EnumType FlushQueue = new EnumType("FlushQueue");
		/**
		 *
		 */
		public static final EnumType FlushResources = new EnumType("FlushResources");
		/**
		 *
		 */
		public static final EnumType Events = new EnumType("Events");
		/**
		 *
		 */
		public static final EnumType ForceGang = new EnumType("ForceGang");
		/**
		 *
		 */
		public static final EnumType GangStatus = new EnumType("GangStatus");
		/**
		 *
		 */
		public static final EnumType HoldQueue = new EnumType("HoldQueue");
		/**
		 *
		 */
		public static final EnumType HoldQueueEntry = new EnumType("HoldQueueEntry");
		/**
		 *
		 */
		public static final EnumType KnownControllers = new EnumType("KnownControllers");
		/**
		 *
		 */
		public static final EnumType KnownDevices = new EnumType("KnownDevices");
		/**
		 *
		 */
		public static final EnumType KnownJDFServices = new EnumType("KnownJDFServices");
		/**
		 *
		 */
		public static final EnumType KnownMessages = new EnumType("KnownMessages");
		/**
		 *
		 */
		public static final EnumType KnownSubscriptions = new EnumType("KnownSubscriptions");
		/**
		 *
		 */
		public static final EnumType ModifyNode = new EnumType("ModifyNode");
		/**
		 *
		 */
		public static final EnumType NewJDF = new EnumType("NewJDF");
		/**
		 *
		 */
		public static final EnumType NodeInfo = new EnumType("NodeInfo");
		/**
		 *
		 */
		public static final EnumType Notification = new EnumType("Notification");
		/**
		 *
		 */
		public static final EnumType Occupation = new EnumType("Occupation");
		/**
		 *
		 */
		public static final EnumType OpenQueue = new EnumType("OpenQueue");
		/**
		 *
		 */
		public static final EnumType PipeClose = new EnumType("PipeClose");
		/**
		 *
		 */
		public static final EnumType PipePull = new EnumType("PipePull");
		/**
		 *
		 */
		public static final EnumType PipePush = new EnumType("PipePush");
		/**
		 *
		 */
		public static final EnumType PipePause = new EnumType("PipePause");
		/**
		 *
		 */
		public static final EnumType QueueEntryStatus = new EnumType("QueueEntryStatus");
		/**
		 *
		 */
		public static final EnumType QueueStatus = new EnumType("QueueStatus");
		/**
		 *
		 */
		public static final EnumType RequestForAuthentication = new EnumType("RequestForAuthentication");
		/**
		 *
		 */
		public static final EnumType RequestQueueEntry = new EnumType("RequestQueueEntry");
		/**
		 *
		 */
		public static final EnumType RemoveQueueEntry = new EnumType("RemoveQueueEntry");
		/**
		 *
		 */
		public static final EnumType RepeatMessages = new EnumType("RepeatMessages");
		/**
		 *
		 */
		public static final EnumType Resource = new EnumType("Resource");
		/**
		 *
		 */
		public static final EnumType ResourcePull = new EnumType("ResourcePull");
		/**
		 *
		 */
		public static final EnumType ResumeQueue = new EnumType("ResumeQueue");
		/**
		 *
		 */
		public static final EnumType ResumeQueueEntry = new EnumType("ResumeQueueEntry");
		/**
		 *
		 */
		public static final EnumType ResubmitQueueEntry = new EnumType("ResubmitQueueEntry");
		/**
		 *
		 */
		public static final EnumType ReturnQueueEntry = new EnumType("ReturnQueueEntry");
		/**
		 *
		 */
		public static final EnumType SetQueueEntryPosition = new EnumType("SetQueueEntryPosition");
		/**
		 *
		 */
		public static final EnumType SetQueueEntryPriority = new EnumType("SetQueueEntryPriority");
		/**
		 *
		 */
		public static final EnumType ShutDown = new EnumType("ShutDown");
		/**
		 *
		 */
		public static final EnumType Status = new EnumType("Status");
		/**
		 *
		 */
		public static final EnumType StopPersistentChannel = new EnumType("StopPersistentChannel");
		/**
		 *
		 */
		public static final EnumType SubmissionMethods = new EnumType("SubmissionMethods");
		/**
		 *
		 */
		public static final EnumType SubmitQueueEntry = new EnumType("SubmitQueueEntry");
		/**
		 *
		 */
		public static final EnumType SuspendQueueEntry = new EnumType("SuspendQueueEntry");
		/**
		 *
		 */
		public static final EnumType Track = new EnumType("Track");
		/**
		 *
		 */
		public static final EnumType UpdateJDF = new EnumType("UpdateJDF");
		/**
		 *
		 */
		public static final EnumType WakeUp = new EnumType("WakeUp");
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFMessage[ --> " + super.toString() + " ]";
	}

	/**
	 * IsMessageElement - is a message with this name a message element?
	 *
	 * @return boolean
	 * @deprecated use instanceof JDFMessage
	 */
	@Deprecated
	public boolean isMessageElement()
	{
		return true;
	}

	/**
	 * init
	 *
	 * @return boolean
	 */
	@Override
	public boolean init()
	{
		appendAnchor(null);
		return super.init();
	}

	/**
	 * getIDPrefix
	 *
	 * @return the ID prefix of JDFMessage
	 */
	@Override
	public String getIDPrefix()
	{
		return "m";
	}

	/**
	 * getFamily: get the enum family type
	 *
	 * @return EnumFamily - type Family_Unknown, Family_Query, Family_Signal, Family_Command, Family_Response or Family_Acknowledge
	 */
	public EnumFamily getFamily()
	{
		return EnumFamily.getEnum(getLocalName());
	}

	/**
	 * getType: get attribute <code>Type</code>
	 *
	 * @return String
	 */
	@Override
	public String getType()
	{
		return getAttribute(AttributeName.TYPE, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Set attribute <code>Type</code> and <code>xsi:type</code>
	 *
	 * @param typ the type
	 */
	@Override
	public void setType(final String typ)
	{
		removeAttribute("type", AttributeName.XSIURI);
		setAttribute(AttributeName.TYPE, typ, null);
		if (xmlnsPrefix(typ) == null)
		{
			setXSIType(getLocalName() + typ);
		}
	}

	/**
	 * SetQuery - sets the initiating query, command, Acknowledge, Signal or registration to q
	 *
	 * @param q the query, command or registration to create a response for
	 */
	public void setQuery(final JDFMessage q)
	{
		final EnumFamily f = getFamily();
		if (f == null || f.equals(EnumFamily.Query) || f.equals(EnumFamily.Command) || f.equals(EnumFamily.Registration))
		{
			final String message = f == null ? "JDFMessage.setQuery: illegal family type " : "JDFMessage.setQuery: illegal family type " + f.getName();
			throw new JDFException(message);
		}
		setrefID(q.getID());
		setType(q.getType());
	}

	/**
	 * Enumeration string for enum value
	 *
	 * @param value the enumeration to translate
	 * @return String the string representation of the enumeration
	 */
	public static String typeString(final EnumType value)
	{
		return value.toString();
	}

	/**
	 * @deprecated use EnumType to get strings
	 * @return
	 */
	@Deprecated
	public static String typeString()
	{
		final String enums = "Unknown,Events,KnownControllers,KnownDevices,KnownJDFServices,KnownMessages," + "RepeatMessages,StopPersistentChannel,Occupation,Resource,"
				+ "Status,Track,PipeClose,PipePull,PipePush,PipePause,AbortQueueEntry," + "HoldQueueEntry,removeQueueEntry,ResubmitQueueEntry,"
				+ "ResumeQueueEntry,SetQueueEntryPosition,SetQueueEntryPriority," + "SubmitQueueEntry,CloseQueue,FlushQueue,HoldQueue,OpenQueue,QueueEntryStatus,QueueStatus,"
				+ "ResumeQueue,SubmissionMethods";
		return enums;
	}

	/**
	 * Set attribute Type
	 *
	 * @param value the value to set the attribute to
	 * @deprecated use setType()
	 */
	@Deprecated
	public void setEnumType(final EnumType value)
	{
		setType(value);
	}

	/**
	 * Set attribute Type
	 *
	 * @param value the value to set the attribute to
	 */
	public void setType(final EnumType value)
	{
		final String typeName = value == null ? null : value.getName();
		setType(typeName);
	}

	/**
	 * create a new response for this if this is any message except response correctly fills refId, type etc.
	 *
	 * @return the newly created message
	 */
	public JDFJMF createResponse()
	{
		final JDFMessage.EnumFamily family = getFamily();

		if (family == null)
		{
			throw new JDFException("createResponse: creating resp from undefined message family");
		}

		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, null);
		jmf.getResponse(0).setQuery(this);
		return jmf;
	}

	/**
	 * create a new signal for this if this is any message except response correctly fills refId, type etc.
	 *
	 * @return the newly created message
	 */
	public JDFJMF createSignal()
	{
		final JDFMessage.EnumFamily family = getFamily();

		if (family == null)
		{
			throw new JDFException("createResponse: creating resp from undefined message family");
		}

		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, null);
		final JDFSignal signal = jmf.getSignal(0);
		signal.setQuery(this);
		signal.copyElements(getChildElementVector(null, null), null);
		signal.removeChild(ElementName.SUBSCRIPTION, null, 0);
		return jmf;
	}

	/**
	 * checks whether the type of messageElement is valid for this message
	 *
	 * @param elementName the name of the element to be tested
	 * @param iSkip
	 * @return boolean: true if valid; always true if not in JDFNameSpace
	 */
	public boolean isValidMessageElement(final String elementName, final int iSkip)
	{
		// this is not a standard jdf element, but rather an extension
		// we assume that anyone calling the typesafe methods knows what he is
		// doing
		if (!isInJDFNameSpaceStatic(this))
		{
			return true;
		}

		// this is not a standard jdf type, but rather an extension type
		// we assume that anyone calling the typesafe methods knows what he is
		// doing
		final String sTyp = getType();
		if (KElement.xmlnsPrefix(sTyp) != null || !isInJDFNameSpaceStatic(this) || StringUtil.getNonEmpty(sTyp) == null)
		{
			return true;
		}

		// it aint even valid for any family
		final String[] familyTypeObj = familyTypeObj();
		final boolean isFamilyTypeString = (familyTypeObj == null) ? false : ArrayUtils.contains(familyTypeObj, elementName);
		if (!isFamilyTypeString)
		{
			return false;
		}

		final EnumType typ = getEnumType();
		// the type seams to be unknown, we certainly don't know what to dump in
		if (typ == null)
		{
			return false;
		}

		final Vector<EnumType> validList = getValidTypeVector(elementName, iSkip);
		return validList.contains(typ);
	}

	/**
	 * returns a vector of valid messageElement types for this element
	 *
	 * @param elementName the name of the element to be tested
	 * @param iSkip
	 * @return vector of valid EnumTypes; empty if all are invalid
	 * @default getValidTypeVector(elementName, 0)
	 */
	private Vector<EnumType> getValidTypeVector(final String elementName, final int iSkip)
	{
		final Vector<EnumType> validList = new Vector<>();

		// Commands
		if (elementName.equals(ElementName.FLUSHQUEUEPARAMS))
		{
			if (iSkip == 0)
			{ // validation for cardinality '?' or '-', when no more than 1
				// element are allowed
				validList.addElement(EnumType.FlushQueue);
			}

		}
		else if (elementName.equals(ElementName.FLUSHQUEUEINFO))
		{
			if (iSkip == 0)
			{ // validation for cardinality '?' or '-', when no more than 1
				// element are allowed
				validList.addElement(EnumType.FlushQueue);
			}

		}
		else if (elementName.equals(ElementName.FLUSHRESOURCEPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.FlushResources);
			}

		}
		else if (elementName.equals(ElementName.NEWJDFCMDPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.NewJDF);
			}

		}
		else if (elementName.equals(ElementName.NODEINFOCMDPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.NodeInfo);
			}

		}
		else if (elementName.equals(ElementName.PIPEPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.PipeClose);
				validList.addElement(EnumType.PipePush);
				validList.addElement(EnumType.PipePull);
				validList.addElement(EnumType.PipePause);
			}

		}
		else if (elementName.equals(ElementName.QUEUEENTRYDEF))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.AbortQueueEntry);
				validList.addElement(EnumType.HoldQueueEntry);
				validList.addElement(EnumType.RemoveQueueEntry);
				validList.addElement(EnumType.ResumeQueueEntry);
				validList.addElement(EnumType.SuspendQueueEntry);
			}

		}
		else if (elementName.equals(ElementName.QUEUEENTRYPRIPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.SetQueueEntryPriority);
			}

		}
		else if (elementName.equals(ElementName.QUEUEENTRYPOSPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.SetQueueEntryPosition);
			}

		}
		else if (elementName.equals(ElementName.QUEUEFILTER))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.AbortQueueEntry);
				validList.addElement(EnumType.CloseQueue);
				validList.addElement(EnumType.FlushQueue);
				validList.addElement(EnumType.FlushResources);
				validList.addElement(EnumType.HoldQueue);
				validList.addElement(EnumType.HoldQueueEntry);
				validList.addElement(EnumType.OpenQueue);
				validList.addElement(EnumType.RemoveQueueEntry);
				validList.addElement(EnumType.ResourcePull);
				validList.addElement(EnumType.ResubmitQueueEntry);
				validList.addElement(EnumType.ResumeQueue);
				validList.addElement(EnumType.ResumeQueueEntry);
				validList.addElement(EnumType.SetQueueEntryPosition);
				validList.addElement(EnumType.SetQueueEntryPriority);
				validList.addElement(EnumType.ShutDown);
				validList.addElement(EnumType.SubmitQueueEntry);
				validList.addElement(EnumType.SuspendQueueEntry);
				// Queries for QueueFilter
				validList.addElement(EnumType.QueueStatus);
			}

		}
		else if (elementName.equals(ElementName.ABORTQUEUEENTRYPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.AbortQueueEntry);
			}
		}
		else if (elementName.equals(ElementName.HOLDQUEUEENTRYPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.HoldQueueEntry);
			}
		}
		else if (elementName.equals(ElementName.RESUMEQUEUEENTRYPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.ResumeQueueEntry);
			}
		}
		else if (elementName.equals(ElementName.REMOVEQUEUEENTRYPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.RemoveQueueEntry);
			}
		}
		else if (elementName.equals(ElementName.QUEUESUBMISSIONPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.SubmitQueueEntry);
			}

		}
		else if (elementName.equals(ElementName.REQUESTQUEUEENTRYPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.RequestQueueEntry);
			}

		}
		else if (elementName.equals(ElementName.RESOURCECMDPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.Resource);
			}

		}
		else if (elementName.equals(ElementName.RESOURCEPULLPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.ResourcePull);
			}

		}
		else if (elementName.equals(ElementName.RESUBMISSIONPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.ResubmitQueueEntry);
			}

		}
		else if (elementName.equals(ElementName.RETURNQUEUEENTRYPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.ReturnQueueEntry);
			}

		}
		else if (elementName.equals(ElementName.SHUTDOWNCMDPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.ShutDown);
			}

		}
		else if (elementName.equals(ElementName.STOPPERSCHPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.StopPersistentChannel);
			}

		}
		else if (elementName.equals(ElementName.WAKEUPCMDPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.WakeUp);
			}

			// Queries
		}
		else if (elementName.equals(ElementName.DEVICEFILTER))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.KnownDevices);
			}

		}
		else if (elementName.equals(ElementName.EMPLOYEEDEF))
		{
			validList.addElement(EnumType.Occupation);

		}
		else if (elementName.equals(ElementName.KNOWNMSGQUPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.KnownMessages);
			}

		}
		else if (elementName.equals(ElementName.MSGFILTER))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.RepeatMessages);
			}

		}
		else if (elementName.equals(ElementName.NEWJDFQUPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.NewJDF);
			}

		}
		else if (elementName.equals(ElementName.NODEINFOQUPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.NodeInfo);
			}

		}
		else if (elementName.equals(ElementName.NOTIFICATIONFILTER))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.Events);
			}

		}
		else if (elementName.equals(ElementName.QUEUEENTRYDEFLIST))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.QueueEntryStatus);
			}

		}
		else if (elementName.equals(ElementName.RESOURCEQUPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.Resource);
			}

		}
		else if (elementName.equals(ElementName.STATUSQUPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.Status);
			}

		}
		else if (elementName.equals(ElementName.TRACKFILTER))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.Track);
			}

			// Responses
		}
		else if (elementName.equals(ElementName.DEVICELIST))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.KnownDevices);
			}

		}
		else if (elementName.equals(ElementName.DEVICEINFO))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.ShutDown);
				validList.addElement(EnumType.WakeUp);
			}
			validList.addElement(EnumType.Status);

		}
		else if (elementName.equals(ElementName.FLUSHEDRESOURCES))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.FlushResources);
			}

		}
		else if (elementName.equals(ElementName.IDINFO))
		{
			validList.addElement(EnumType.NewJDF);

		}
		else if (elementName.equals(ElementName.JDFCONTROLLER))
		{
			validList.addElement(EnumType.KnownControllers);

		}
		else if (elementName.equals(ElementName.JDFSERVICE))
		{
			validList.addElement(EnumType.KnownJDFServices);

		}
		else if (elementName.equals(ElementName.JOBPHASE))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.PipeClose);
				validList.addElement(EnumType.PipePush);
				validList.addElement(EnumType.PipePull);
				validList.addElement(EnumType.PipePause);
			}

		}
		else if (elementName.equals(ElementName.MESSAGESERVICE))
		{
			validList.addElement(EnumType.KnownMessages);

		}
		else if (elementName.equals(ElementName.NODEINFORESP))
		{
			validList.addElement(EnumType.NodeInfo);

		}
		else if (elementName.equals(ElementName.NOTIFICATIONDEF))
		{
			validList.addElement(EnumType.Events);

		}
		else if (elementName.equals(ElementName.OCCUPATION))
		{
			validList.addElement(EnumType.Occupation);

		}
		else if (elementName.equals(ElementName.QUEUE))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.AbortQueueEntry);
				validList.addElement(EnumType.CloseQueue);
				validList.addElement(EnumType.FlushQueue);
				validList.addElement(EnumType.HoldQueue);
				validList.addElement(EnumType.HoldQueueEntry);
				validList.addElement(EnumType.OpenQueue);
				validList.addElement(EnumType.QueueStatus);
				validList.addElement(EnumType.RemoveQueueEntry);
				validList.addElement(EnumType.ResourcePull);
				validList.addElement(EnumType.ResumeQueue);
				validList.addElement(EnumType.ResubmitQueueEntry);
				validList.addElement(EnumType.ResumeQueueEntry);
				validList.addElement(EnumType.SetQueueEntryPosition);
				validList.addElement(EnumType.SetQueueEntryPriority);
				validList.addElement(EnumType.ShutDown);
				validList.addElement(EnumType.Status);
				validList.addElement(EnumType.SubmitQueueEntry);
				validList.addElement(EnumType.SuspendQueueEntry);
			}

		}
		else if (elementName.equals(ElementName.QUEUEENTRY))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.ResourcePull);
				validList.addElement(EnumType.SubmitQueueEntry);
			}
			validList.addElement(EnumType.QueueEntryStatus);

		}
		else if (elementName.equals(ElementName.RESOURCEINFO))
		{
			validList.addElement(EnumType.Resource);

		}
		else if (elementName.equals(ElementName.SUBMISSIONMETHODS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.SubmissionMethods);
			}
		}
		else if (elementName.equals(ElementName.SUBSCRIPTIONFILTER))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.KnownSubscriptions);
			}
		}
		else if (elementName.equals(ElementName.SUBSCRIPTIONINFO))
		{
			validList.addElement(EnumType.KnownSubscriptions);
		}
		else if (elementName.equals(ElementName.TRACKRESULT))
		{
			validList.addElement(EnumType.Track);
		}
		else if (elementName.equals(ElementName.UPDATEJDFCMDPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.UpdateJDF);
			}
		}
		else if (elementName.equals(ElementName.MODIFYNODECMDPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.ModifyNode);
			}
		}
		else if (elementName.equals(ElementName.AUTHENTICATIONCMDPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.RequestForAuthentication);
			}
		}
		else if (elementName.equals(ElementName.AUTHENTICATIONQUPARAMS))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.RequestForAuthentication);
			}
		}
		else if (elementName.equals(ElementName.AUTHENTICATIONRESP))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.RequestForAuthentication);
			}
		}
		else if (elementName.equals(ElementName.CONTROLLERFILTER))
		{
			if (iSkip == 0)
			{
				validList.addElement(EnumType.KnownControllers);
			}
		}

		else if (EnumFamily.getEnum(elementName) != null)
		{
			validList.addElement(EnumType.RepeatMessages);
		}

		return validList;
	}

	/**
	 * Typesafe enumerated attribute Type
	 *
	 * @return EnumType: the enumeration value of the attribute
	 */
	public EnumType getEnumType()
	{
		return EnumType.getEnum(getAttribute(AttributeName.TYPE, null, null));
	}

	/**
	 * get a vector of valid object names for this family type
	 *
	 * @return String comma separated valid object types for this family type
	 */
	private String[] familyTypeObj()
	{
		final EnumFamily family = getFamily();
		if (family == null)
		{
			return null;
		}

		if (family.equals(EnumFamily.Command))
		{
			return commandTypeObjString;
		}
		if (family.equals(EnumFamily.Registration))
		{
			return registrationTypeObjString;
		}

		if (family.equals(EnumFamily.Query))
		{
			return queryTypeObjString;
		}

		if (family.equals(EnumFamily.Response) || family.equals(EnumFamily.Acknowledge))
		{
			return responseTypeObjString;
		}

		if (family.equals(EnumFamily.Signal))
		{
			if (signalTypeObjString == null)
			{
				final VString v = new VString(queryTypeObjString);
				v.addAll(responseTypeObjString);
				v.unify();
				signalTypeObjString = v.toArray(new String[v.size()]);
			}
			return signalTypeObjString;
		}

		return null;
	}

	/**
	 * Enumeration strings for list of CommandTypeObj
	 *
	 * @returnString comma separated list of enumerated string values
	 */
	private static String[] commandTypeObjString = { ElementName.AUTHENTICATIONCMDPARAMS, ElementName.ABORTQUEUEENTRYPARAMS, ElementName.HOLDQUEUEENTRYPARAMS, ElementName.RESUMEQUEUEENTRYPARAMS,
			ElementName.REMOVEQUEUEENTRYPARAMS, ElementName.FLUSHQUEUEPARAMS, ElementName.FLUSHRESOURCEPARAMS, ElementName.MODIFYNODECMDPARAMS, ElementName.NEWJDFCMDPARAMS,
			ElementName.NODEINFOCMDPARAMS, ElementName.PIPEPARAMS, ElementName.QUEUEENTRYDEF, ElementName.QUEUEENTRYPRIPARAMS, ElementName.QUEUEENTRYPOSPARAMS, ElementName.QUEUEFILTER,
			ElementName.QUEUESUBMISSIONPARAMS, ElementName.REQUESTQUEUEENTRYPARAMS, ElementName.RESOURCECMDPARAMS, ElementName.RESOURCEPULLPARAMS, ElementName.RESUBMISSIONPARAMS,
			ElementName.RETURNQUEUEENTRYPARAMS, ElementName.SHUTDOWNCMDPARAMS, ElementName.STOPPERSCHPARAMS, ElementName.UPDATEJDFCMDPARAMS, ElementName.WAKEUPCMDPARAMS };

	/**
	 * Enumeration strings for list of CommandTypeObj
	 *
	 * @returnString comma separated list of enumerated string values
	 */
	private static String[] registrationTypeObjString = { ElementName.PIPEPARAMS, ElementName.RESOURCECMDPARAMS, ElementName.RESOURCEPULLPARAMS, };

	/**
	 * Enumeration strings for list of QueryTypeObj
	 *
	 * @returnString comma separated list of enumerated string values
	 */
	private static String[] queryTypeObjString = { ElementName.AUTHENTICATIONQUPARAMS, ElementName.DEVICEFILTER, ElementName.EMPLOYEEDEF, ElementName.KNOWNMSGQUPARAMS, ElementName.MSGFILTER,
			ElementName.MODIFYNODECMDPARAMS, ElementName.NEWJDFQUPARAMS, ElementName.NODEINFOQUPARAMS, ElementName.NOTIFICATIONFILTER, ElementName.QUEUEENTRYDEFLIST, ElementName.QUEUEFILTER,
			ElementName.RESOURCEQUPARAMS, ElementName.STATUSQUPARAMS, ElementName.SUBSCRIPTIONFILTER, ElementName.TRACKFILTER, ElementName.UPDATEJDFCMDPARAMS };

	/**
	 * Enumeration strings for list of ResponseTypeObj
	 *
	 * @returnString comma separated list of enumerated string values
	 */
	private static String[] responseTypeObjString = { ElementName.DEVICELIST, ElementName.DEVICEINFO, ElementName.FLUSHQUEUEINFO, ElementName.FLUSHEDRESOURCES, ElementName.IDINFO,
			ElementName.JDFCONTROLLER, ElementName.JDFSERVICE, ElementName.JOBPHASE, ElementName.MESSAGESERVICE, ElementName.NODEINFORESP, ElementName.NOTIFICATIONDEF, ElementName.OCCUPATION,
			ElementName.QUEUE, ElementName.QUEUEENTRY, ElementName.RESOURCEINFO, ElementName.SUBSCRIPTIONINFO, ElementName.SUBMISSIONMETHODS, ElementName.TRACKRESULT, ElementName.COMMAND,
			ElementName.QUERY, ElementName.ACKNOWLEDGE, ElementName.RESPONSE, ElementName.SIGNAL, ElementName.REGISTRATION };

	private static String[] signalTypeObjString = null;
	private static String[] elemArray = null;

	/**
	 * append an element<br>
	 * throws a JDFException, if <code>elementName</code> is not legal and strictValidation is switched on
	 *
	 * @param elementName name of the element to append
	 * @param nameSpaceURI namespace URI of the element to append
	 * @return the appended element
	 */
	public KElement appendValidElement(final String elementName, final String nameSpaceURI)
	{
		// 100125 - RP we now have a switch to avoid validating appended elements
		if (strictValidation)
		{
			final int iSkip = numChildElements(elementName, nameSpaceURI);
			if (!isValidMessageElement(elementName, iSkip))
			{
				throw new JDFException("AppendValidElement: illegal element :" + elementName);
			}
		}
		return appendElement(elementName, nameSpaceURI);
	}

	/**
	 * @deprecated use appendValidElement(elementName, null);
	 * @param elementName
	 * @return
	 */
	@Deprecated
	public KElement appendValidElement(final String elementName)
	{
		return appendValidElement(elementName, null);
	}

	/**
	 * get a (valid) element<br>
	 * throws <code>JDFException</code> if the element is not valid and strictValidation is switched on
	 *
	 * @param nodeName name of the element to get
	 * @param nameSpaceURI namespace URI of the element to get
	 * @param iSkip number of elements to skip
	 * @return the element
	 */
	public KElement getValidElement(final String nodeName, final String nameSpaceURI, final int iSkip)
	{
		if (strictValidation && !isValidMessageElement(nodeName, iSkip))
		{
			throw new JDFException("getValidElement: illegal element :" + nodeName + " Family=" + getLocalName() + " Type=" + getType() + " ID=" + getID());
		}

		return getElement_JDFElement(nodeName, nameSpaceURI, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////
	/**
	 * get a (valid) element, create if it doesn't exist<br>
	 * throws <code>JDFException</code> if the element is not valid and strictValidation is switched on
	 *
	 * @param nodeName name of the element to get
	 * @param nameSpaceURI namespace URI of the element to get
	 * @param iSkip number of elements to skip
	 * @return KElement
	 */
	public KElement getCreateValidElement(final String nodeName, final String nameSpaceURI, final int iSkip)
	{
		if (strictValidation && !isValidMessageElement(nodeName, iSkip))
		{
			throw new JDFException("getCreateValidElement: illegal element :" + nodeName);
		}

		return getCreateElement_KElement(nodeName, nameSpaceURI, iSkip);
	}

	/*
	 * // Element getter / Setter
	 */

	/**
	 * get device, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDevice
	 */
	public JDFDevice getCreateDevice(final int iSkip)
	{
		final JDFDevice e = (JDFDevice) getCreateValidElement(ElementName.DEVICE, null, iSkip);

		return e;
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * append element <code>Device</code>
	 *
	 * @return JDFDevice: the element
	 */
	public JDFDevice appendDevice()
	{
		final JDFDevice e = (JDFDevice) appendValidElement(ElementName.DEVICE, null);

		return e;
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get the iSkip'th device
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDevice: the element
	 */
	public JDFDevice getDevice(final int iSkip)
	{
		return (JDFDevice) getValidElement(ElementName.DEVICE, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>DeviceFilter</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceFilter: the element
	 */
	public JDFDeviceFilter getCreateDeviceFilter(final int iSkip)
	{
		final JDFDeviceFilter e = (JDFDeviceFilter) getCreateValidElement(ElementName.DEVICEFILTER, null, iSkip);

		return e;
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * append element <code>DeviceFilter</code>
	 *
	 * @return JDFDeviceFilter: the element
	 */
	public JDFDeviceFilter appendDeviceFilter()
	{
		return (JDFDeviceFilter) appendValidElement(ElementName.DEVICEFILTER, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>DeviceFilter</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceFilter: the element
	 */
	public JDFDeviceFilter getDeviceFilter(final int iSkip)
	{
		return (JDFDeviceFilter) getValidElement(ElementName.DEVICEFILTER, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	/**
	 * get element <code>DeviceInfo</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceInfo: the element
	 */
	public JDFDeviceInfo getCreateDeviceInfo(final int iSkip)
	{
		return (JDFDeviceInfo) getCreateValidElement(ElementName.DEVICEINFO, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * append element <code>DeviceInfo</code>
	 *
	 * @return JDFDeviceInfo: the element
	 */
	public JDFDeviceInfo appendDeviceInfo()
	{
		return (JDFDeviceInfo) appendValidElement(ElementName.DEVICEINFO, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>DeviceInfo</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceInfo: the element
	 */
	public JDFDeviceInfo getDeviceInfo(final int iSkip)
	{
		return (JDFDeviceInfo) getValidElement(ElementName.DEVICEINFO, null, iSkip);
	}

	/**
	 * get a matching deviceInfo for a given DeviceID
	 *
	 * @param deviceID the deviceID of the device to get
	 * @return
	 */
	public JDFDeviceInfo getDeviceInfo(final String deviceID)
	{
		if (strictValidation && !isValidMessageElement(ElementName.DEVICEINFO, 0))
		{
			throw new JDFException("getValidElement: illegal element :" + ElementName.DEVICEINFO);
		}
		JDFDeviceInfo d = (JDFDeviceInfo) getChildWithAttribute(ElementName.DEVICEINFO, AttributeName.DEVICEID, null, deviceID, 0, true);
		if (d == null)
		{
			final JDFDevice dev = (JDFDevice) getChildWithAttribute(ElementName.DEVICE, AttributeName.DEVICEID, null, deviceID, 0, false);
			if (dev != null)
			{
				d = (JDFDeviceInfo) dev.getParentNode_KElement();
			}
		}
		return d;
	}

	/**
	 * get a matching deviceInfo for a given DeviceID, create it if it does not exist
	 *
	 * @param deviceID the deviceID of the device to get
	 * @return
	 */
	public JDFDeviceInfo getCreateDeviceInfo(final String deviceID)
	{
		JDFDeviceInfo d = getDeviceInfo(deviceID);
		if (d == null)
		{
			d = appendDeviceInfo();
			d.setDeviceID(deviceID);
		}
		return d;
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get Element <code>DeviceList</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceList: the element
	 */
	public JDFDeviceList getCreateDeviceList(final int iSkip)
	{
		return (JDFDeviceList) getCreateValidElement(ElementName.DEVICELIST, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * Append element <code>DeviceList</code>
	 *
	 * @return JDFDeviceList the element
	 */
	public JDFDeviceList appendDeviceList()
	{
		return (JDFDeviceList) appendValidElement(ElementName.DEVICELIST, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th DeviceList element
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFDeviceList: the element
	 */
	public JDFDeviceList getDeviceList(final int iSkip)
	{
		return (JDFDeviceList) getValidElement(ElementName.DEVICELIST, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get element <code>EmployeeDef</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFEmployeeDef: the element
	 */
	public JDFEmployeeDef getCreateEmployeeDef(final int iSkip)
	{
		return (JDFEmployeeDef) getCreateValidElement(ElementName.EMPLOYEEDEF, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * Append element <code>EmployeeDef</code>
	 *
	 * @return JDFEmployeeDef: the element
	 */
	public JDFEmployeeDef appendEmployeeDef()
	{
		return (JDFEmployeeDef) appendValidElement(ElementName.EMPLOYEEDEF, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>EmployeeDef</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFEmployeeDef: the element
	 */
	public JDFEmployeeDef getEmployeeDef(final int iSkip)
	{
		return (JDFEmployeeDef) getValidElement(ElementName.EMPLOYEEDEF, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////

	/**
	 * get Element <code>JDFController</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFJDFController: the element
	 */
	public JDFJDFController getCreateJDFController(final int iSkip)
	{
		return (JDFJDFController) getCreateValidElement(ElementName.JDFCONTROLLER, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * Append element <code>JDFController</code>
	 *
	 * @return JDFJDFController: the element
	 */
	public JDFJDFController appendJDFController()
	{
		return (JDFJDFController) appendValidElement(ElementName.JDFCONTROLLER, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th JDFController element
	 *
	 * @param iSkip number of elemts to skip
	 * @return JDFJDFController: the element
	 */
	public JDFJDFController getJDFController(final int iSkip)
	{
		return (JDFJDFController) getValidElement(ElementName.JDFCONTROLLER, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>JDFService</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFJDFService: the element
	 */
	public JDFJDFService getCreateJDFService(final int iSkip)
	{
		return (JDFJDFService) getCreateValidElement(ElementName.JDFSERVICE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * Append element <code>JDFService</code>
	 *
	 * @return JDFJDFService the element
	 */
	public JDFJDFService appendJDFService()
	{
		return (JDFJDFService) appendValidElement(ElementName.JDFSERVICE, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th <code>JDFService</code> element
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFJDFService: the element
	 */
	public JDFJDFService getJDFService(final int iSkip)
	{
		return (JDFJDFService) getValidElement(ElementName.JDFSERVICE, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////

	/**
	 * get Element <code>JobPhase</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFJobPhase: the element
	 */
	public JDFJobPhase getCreateJobPhase(final int iSkip)
	{
		return (JDFJobPhase) getValidElement(ElementName.JOBPHASE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>JobPhase</code>
	 *
	 * @return JDFJobPhase: the element
	 */
	public JDFJobPhase appendJobPhase()
	{
		return (JDFJobPhase) appendValidElement(ElementName.JOBPHASE, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th <code>JobPhase</code> element
	 *
	 * @param iSkip elements to skip
	 * @return JDFJobPhase: the element
	 */
	public JDFJobPhase getJobPhase(final int iSkip)
	{
		return (JDFJobPhase) getValidElement(ElementName.JOBPHASE, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th <code>KnownMsgQuParams</code> element, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFKnownMsgQuParams the element
	 */
	public JDFKnownMsgQuParams getCreateKnownMsgQuParams(final int iSkip)
	{
		return (JDFKnownMsgQuParams) getCreateValidElement(ElementName.KNOWNMSGQUPARAMS, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>KnownMsgQuParams</code>
	 *
	 * @return JDFKnownMsgQuParams: the element
	 */
	public JDFKnownMsgQuParams appendKnownMsgQuParams()
	{
		return (JDFKnownMsgQuParams) appendValidElement(ElementName.KNOWNMSGQUPARAMS, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th <code>KnownMsgQuParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFKnownMsgQuParams: the element
	 */
	public JDFKnownMsgQuParams getKnownMsgQuParams(final int iSkip)
	{
		return (JDFKnownMsgQuParams) getValidElement(ElementName.KNOWNMSGQUPARAMS, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>MessageService</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMessageService: the element
	 */
	public JDFMessageService getCreateMessageService(final int iSkip)
	{
		return (JDFMessageService) getCreateValidElement(ElementName.MESSAGESERVICE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>MessageService</code>
	 *
	 * @return
	 */
	public JDFMessageService appendMessageService()
	{
		return (JDFMessageService) appendValidElement(ElementName.MESSAGESERVICE, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>MessageService</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMessageService: the element
	 */
	public JDFMessageService getMessageService(final int iSkip)
	{
		return (JDFMessageService) getValidElement(ElementName.MESSAGESERVICE, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get element <code>MsgFilter</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMsgFilter: the element
	 */
	public JDFMsgFilter getCreateMsgFilter(final int iSkip)
	{
		return (JDFMsgFilter) getCreateValidElement(ElementName.MSGFILTER, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>MsgFilter</code>
	 *
	 * @return JDFMsgFilter: the element
	 */
	public JDFMsgFilter appendMsgFilter()
	{
		return (JDFMsgFilter) appendValidElement(ElementName.MSGFILTER, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>MsgFilter</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFMsgFilter: the element
	 */
	public JDFMsgFilter getMsgFilter(final int iSkip)
	{
		return (JDFMsgFilter) getValidElement(ElementName.MSGFILTER, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get element <code>NotificationDef</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNotificationDef: the element
	 */
	public JDFNotificationDef getCreateNotificationDef(final int iSkip)
	{
		return (JDFNotificationDef) getCreateValidElement(ElementName.NOTIFICATIONDEF, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>NotificationDef</code>
	 *
	 * @return JDFNotificationDef: the element
	 */
	public JDFNotificationDef appendNotificationDef()
	{
		return (JDFNotificationDef) appendValidElement(ElementName.NOTIFICATIONDEF, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>NotificationDef</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNotificationDef: the element
	 */
	public JDFNotificationDef getNotificationDef(final int iSkip)
	{
		return (JDFNotificationDef) getValidElement(ElementName.NOTIFICATIONDEF, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get element <code>NotificationFilter</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNotificationFilter: the element
	 */
	public JDFNotificationFilter getCreateNotificationFilter(final int iSkip)
	{
		return (JDFNotificationFilter) getCreateValidElement(ElementName.NOTIFICATIONFILTER, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>NotificationFilter</code>
	 *
	 * @return JDFNotificationFilter: the element
	 */
	public JDFNotificationFilter appendNotificationFilter()
	{
		return (JDFNotificationFilter) appendValidElement(ElementName.NOTIFICATIONFILTER, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th <code>NotificationFilter</code> element
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFNotificationFilter: the element
	 */
	public JDFNotificationFilter getNotificationFilter(final int iSkip)
	{
		return (JDFNotificationFilter) getValidElement(ElementName.NOTIFICATIONFILTER, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th <code>Occupation</code> element
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFOccupation: the element
	 */
	public JDFOccupation getCreateOccupation(final int iSkip)
	{
		return (JDFOccupation) getCreateValidElement(ElementName.OCCUPATION, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>Occupation</code>
	 *
	 * @return JDFOccupation: the element
	 */
	public JDFOccupation appendOccupation()
	{
		return (JDFOccupation) appendValidElement(ElementName.OCCUPATION, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>Occupation</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFOccupation: the element
	 */
	public JDFOccupation getOccupation(final int iSkip)
	{
		return (JDFOccupation) getValidElement(ElementName.OCCUPATION, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>PipeParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFPipeParams: the element
	 */
	public JDFPipeParams getCreatePipeParams(final int iSkip)
	{
		return (JDFPipeParams) getCreateValidElement(ElementName.PIPEPARAMS, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>PipeParams</code>
	 *
	 * @return JDFPipeParams: the element
	 */
	public JDFPipeParams appendPipeParams()
	{
		return (JDFPipeParams) appendValidElement(ElementName.PIPEPARAMS, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get element <code>PipeParams</code>
	 *
	 * @return JDFPipeParams: the element
	 */
	public JDFPipeParams getPipeParams()
	{
		return (JDFPipeParams) getValidElement(ElementName.PIPEPARAMS, null, 0);
	}

	/**
	 * get iSkip'th element <code>PipeParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @deprecated - use the 0 parameter version
	 * @return JDFPipeParams: the element
	 */
	@Deprecated
	public JDFPipeParams getPipeParams(final int iSkip)
	{
		return (JDFPipeParams) getValidElement(ElementName.PIPEPARAMS, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>Queue</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueue: the element
	 */
	public JDFQueue getCreateQueue(final int iSkip)
	{
		return (JDFQueue) getCreateValidElement(ElementName.QUEUE, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>Queue</code>
	 *
	 * @return JDFQueue: the element
	 */
	public JDFQueue appendQueue()
	{
		return (JDFQueue) appendValidElement(ElementName.QUEUE, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>Queue</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueue: the element
	 */
	public JDFQueue getQueue(final int iSkip)
	{
		return (JDFQueue) getValidElement(ElementName.QUEUE, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>QueueEntry</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntry: the element
	 */
	public JDFQueueEntry getCreateQueueEntry(final int iSkip)
	{
		return (JDFQueueEntry) getCreateValidElement(ElementName.QUEUEENTRY, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * append element <code>QueueEntry</code>
	 *
	 * @return JDFQueueEntry: the element
	 */
	public JDFQueueEntry appendQueueEntry()
	{
		return (JDFQueueEntry) appendValidElement(ElementName.QUEUEENTRY, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>QueueEntry</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntry: the element
	 */
	public JDFQueueEntry getQueueEntry(final int iSkip)
	{
		return (JDFQueueEntry) getValidElement(ElementName.QUEUEENTRY, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>QueueEntryDef</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntryDef: the element
	 */
	public JDFQueueEntryDef getCreateQueueEntryDef(final int iSkip)
	{
		return (JDFQueueEntryDef) getCreateValidElement(ElementName.QUEUEENTRYDEF, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>QueueEntryDef</code>
	 *
	 * @return JDFQueueEntryDef: the element
	 */
	public JDFQueueEntryDef appendQueueEntryDef()
	{
		return (JDFQueueEntryDef) appendValidElement(ElementName.QUEUEENTRYDEF, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>QueueEntryDef</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntryDef: the element
	 */
	public JDFQueueEntryDef getQueueEntryDef(final int iSkip)
	{
		return (JDFQueueEntryDef) getValidElement(ElementName.QUEUEENTRYDEF, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element QueueEntryDefList, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntryDefList: the element
	 */
	public JDFQueueEntryDefList getCreateQueueEntryDefList(final int iSkip)
	{
		return (JDFQueueEntryDefList) getCreateValidElement(ElementName.QUEUEENTRYDEFLIST, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>QueueEntryDefList</code>
	 *
	 * @return JDFQueueEntryDefList: the element
	 */
	public JDFQueueEntryDefList appendQueueEntryDefList()
	{
		return (JDFQueueEntryDefList) appendValidElement(ElementName.QUEUEENTRYDEFLIST, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>QueueEntryDefList</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntryDefList: the element
	 */
	public JDFQueueEntryDefList getQueueEntryDefList(final int iSkip)
	{
		return (JDFQueueEntryDefList) getValidElement(ElementName.QUEUEENTRYDEFLIST, null, iSkip);

	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>QueueEntryPriParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntryPriParams: the element
	 */
	public JDFQueueEntryPriParams getCreateQueueEntryPriParams(final int iSkip)
	{
		return (JDFQueueEntryPriParams) getCreateValidElement(ElementName.QUEUEENTRYPRIPARAMS, null, iSkip);
	}

	/**
	 * append element <code>QueueEntryPriParams</code>
	 *
	 * @return JDFQueueEntryPriParams: the element
	 */
	public JDFQueueEntryPriParams appendQueueEntryPriParams()
	{
		return (JDFQueueEntryPriParams) appendValidElement(ElementName.QUEUEENTRYPRIPARAMS, null);
	}

	/**
	 * get iSkip'th element <code>QueueEntryPriParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntryPriParams: the element
	 */
	public JDFQueueEntryPriParams getQueueEntryPriParams(final int iSkip)
	{
		return (JDFQueueEntryPriParams) getValidElement(ElementName.QUEUEENTRYPRIPARAMS, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element QueueEntryPosParams, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntryPosParams: the element
	 */
	public JDFQueueEntryPosParams getCreateQueueEntryPosParams(final int iSkip)
	{
		return (JDFQueueEntryPosParams) getCreateValidElement(ElementName.QUEUEENTRYPOSPARAMS, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>QueueEntryPosParams</code>
	 *
	 * @return JDFQueueEntryPosParams: the element
	 */
	public JDFQueueEntryPosParams appendQueueEntryPosParams()
	{
		return (JDFQueueEntryPosParams) appendValidElement(ElementName.QUEUEENTRYPOSPARAMS, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>QeueEntryPosParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueEntryPosParams: the element
	 */
	public JDFQueueEntryPosParams getQueueEntryPosParams(final int iSkip)
	{
		return (JDFQueueEntryPosParams) getValidElement(ElementName.QUEUEENTRYPOSPARAMS, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>QueueSubmissionParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueSubmissionParams: the element
	 */
	public JDFQueueSubmissionParams getCreateQueueSubmissionParams(final int iSkip)
	{
		return (JDFQueueSubmissionParams) getCreateValidElement(ElementName.QUEUESUBMISSIONPARAMS, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>QueueSubmissionParams</code>
	 *
	 * @return JDFQueueSubmissionParams: the element
	 */
	public JDFQueueSubmissionParams appendQueueSubmissionParams()
	{
		return (JDFQueueSubmissionParams) appendValidElement(ElementName.QUEUESUBMISSIONPARAMS, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>QueueSubmissionParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFQueueSubmissionParams: the element
	 */
	public JDFQueueSubmissionParams getQueueSubmissionParams(final int iSkip)
	{
		return (JDFQueueSubmissionParams) getValidElement(ElementName.QUEUESUBMISSIONPARAMS, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>ResourceCmdParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFResourceCmdParams: the element
	 */
	public JDFResourceCmdParams getCreateResourceCmdParams(final int iSkip)
	{
		return (JDFResourceCmdParams) getCreateValidElement(ElementName.RESOURCECMDPARAMS, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>ResourceCmdParams</code>
	 *
	 * @return JDFResourceCmdParams: the element
	 */
	public JDFResourceCmdParams appendResourceCmdParams()
	{
		return (JDFResourceCmdParams) appendValidElement(ElementName.RESOURCECMDPARAMS, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>ResourceCmdParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFResourceCmdParams: the element
	 */
	public JDFResourceCmdParams getResourceCmdParams(final int iSkip)
	{
		return (JDFResourceCmdParams) getValidElement(ElementName.RESOURCECMDPARAMS, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>ResourceQuParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFResourceQuParams: the element
	 */
	public JDFResourceQuParams getCreateResourceQuParams(final int iSkip)
	{
		return (JDFResourceQuParams) getCreateValidElement(ElementName.RESOURCEQUPARAMS, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>ResourceQuParams</code>
	 *
	 * @return JDFResourceQuParams: the element
	 */
	public JDFResourceQuParams appendResourceQuParams()
	{
		return (JDFResourceQuParams) appendValidElement(ElementName.RESOURCEQUPARAMS, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>ResourceQuParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFResourceQuParams: the element
	 * @deprecated use null parameter version
	 */
	@Deprecated
	public JDFResourceQuParams getResourceQuParams(final int iSkip)
	{
		return (JDFResourceQuParams) getValidElement(ElementName.RESOURCEQUPARAMS, null, iSkip);
	}

	/**
	 * get first element <code>ResourceQuParams</code>
	 *
	 * @return JDFResourceQuParams: the element
	 */
	public JDFResourceQuParams getResourceQuParams()
	{
		return (JDFResourceQuParams) getValidElement(ElementName.RESOURCEQUPARAMS, null, 0);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>ResourceInfo</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFResourceInfo: the element
	 */
	public JDFResourceInfo getCreateResourceInfo(final int iSkip)
	{
		return (JDFResourceInfo) getCreateValidElement(ElementName.RESOURCEINFO, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>ResourceInfo</code>
	 *
	 * @return JDFResourceInfo: the element
	 */
	public JDFResourceInfo appendResourceInfo()
	{
		return (JDFResourceInfo) appendValidElement(ElementName.RESOURCEINFO, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>ResourceInfo</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFResourceInfo: the element
	 */
	public JDFResourceInfo getResourceInfo(final int iSkip)
	{
		return (JDFResourceInfo) getValidElement(ElementName.RESOURCEINFO, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>StatusQuParams</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStatusQuParams: the element
	 */
	public JDFStatusQuParams getCreateStatusQuParams(final int iSkip)
	{
		return (JDFStatusQuParams) getCreateValidElement(ElementName.STATUSQUPARAMS, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>StatusQuParams</code>
	 *
	 * @return JDFStatusQuParams: the element
	 */
	public JDFStatusQuParams appendStatusQuParams()
	{
		return (JDFStatusQuParams) appendValidElement(ElementName.STATUSQUPARAMS, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element StatusQuParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStatusQuParams: the element
	 * @deprecated - use 0 parameter version
	 */
	@Deprecated
	public JDFStatusQuParams getStatusQuParams(final int iSkip)
	{
		return (JDFStatusQuParams) getValidElement(ElementName.STATUSQUPARAMS, null, iSkip);
	}

	/**
	 * get StatusQuParams
	 *
	 *
	 * @return JDFStatusQuParams: the element
	 */
	public JDFStatusQuParams getStatusQuParams()
	{
		return (JDFStatusQuParams) getValidElement(ElementName.STATUSQUPARAMS, null, 0);
	}

	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element StopPersChParams
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStopPersChParams: the element
	 */
	public JDFStopPersChParams getCreateStopPersChParams(final int iSkip)
	{
		return (JDFStopPersChParams) getCreateValidElement(ElementName.STOPPERSCHPARAMS, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>StopPersChParams</code>
	 *
	 * @return JDFStopPersChParams: the element
	 */
	public JDFStopPersChParams appendStopPersChParams()
	{
		return (JDFStopPersChParams) appendValidElement(ElementName.STOPPERSCHPARAMS, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>StopPersChParams</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFStopPersChParams: the element
	 */
	public JDFStopPersChParams getStopPersChParams(final int iSkip)
	{
		return (JDFStopPersChParams) getValidElement(ElementName.STOPPERSCHPARAMS, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>SubmissionMethods</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSubmissionMethods: the element
	 */
	public JDFSubmissionMethods getCreateSubmissionMethods(final int iSkip)
	{
		return (JDFSubmissionMethods) getCreateValidElement(ElementName.SUBMISSIONMETHODS, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>SubmissionMethods</code>
	 *
	 * @return JDFSubmissionMethods: the element
	 */
	public JDFSubmissionMethods appendSubmissionMethods()
	{
		return (JDFSubmissionMethods) appendValidElement(ElementName.SUBMISSIONMETHODS, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>SubMissionMethods</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFSubmissionMethods: the element
	 */
	public JDFSubmissionMethods getSubmissionMethods(final int iSkip)
	{
		return (JDFSubmissionMethods) getValidElement(ElementName.SUBMISSIONMETHODS, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>TrackFilter</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFTrackFilter: the element
	 */
	public JDFTrackFilter getCreateTrackFilter(final int iSkip)
	{
		return (JDFTrackFilter) getCreateValidElement(ElementName.TRACKFILTER, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>TrackFilter</code>
	 *
	 * @return JDFTrackFilter: the element
	 */
	public JDFTrackFilter appendTrackFilter()
	{
		return (JDFTrackFilter) appendValidElement(ElementName.TRACKFILTER, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get iSkip'th element <code>TrackFilter</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFTrackFilter: the element
	 */
	public JDFTrackFilter getTrackFilter(final int iSkip)
	{
		return (JDFTrackFilter) getValidElement(ElementName.TRACKFILTER, null, iSkip);
	}

	// ////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get iSkip'th element <code>TrackResult</code>
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFTrackResult: the element
	 */
	public JDFTrackResult getCreateTrackResult(final int iSkip)
	{
		return (JDFTrackResult) getCreateValidElement(ElementName.TRACKRESULT, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * append element <code>TrackResult</code>
	 *
	 * @return JDFTrackResult: the element
	 */
	public JDFTrackResult appendTrackResult()
	{
		return (JDFTrackResult) appendValidElement(ElementName.TRACKRESULT, null);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * get the iSkip'th element <code>TrackResult</code>
	 *
	 * @param iSkip the number of elements to skip
	 * @return JDFTrackResult: the element
	 */
	public JDFTrackResult getTrackResult(final int iSkip)
	{
		return (JDFTrackResult) getValidElement(ElementName.TRACKRESULT, null, iSkip);
	}

	// new from QC.JMF package

	/**
	 * Returns the ReturnCode; applies to JmfResponse and JmfAcknowledge.
	 *
	 * @return ConstReturnCode
	 */
	public int getReturnCode()
	{
		return getIntAttribute(AttributeName.RETURNCODE, null, 0);
	}

	/**
	 * get iSkip'th element <code>IDInfo</code>, create if it doesn't exist
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFIDInfo: the element
	 */
	public JDFIDInfo getCreateIDInfo(final int iSkip)
	{
		return (JDFIDInfo) getCreateValidElement(ElementName.IDINFO, null, iSkip);
	}

	/**
	 * append element <code>IDInfo</code> JDFIDInfo: the element
	 *
	 * @return
	 */
	public JDFIDInfo appendIDInfo()
	{
		return (JDFIDInfo) appendValidElement(ElementName.IDINFO, null);
	}

	/**
	 * get iSkip'th element <code>IDInfo</code>
	 *
	 * @param iSkip number of elements to skip JDFIDInfo: the element
	 * @return
	 */
	public JDFIDInfo getIDInfo(final int iSkip)
	{
		return (JDFIDInfo) getValidElement(ElementName.IDINFO, null, iSkip);
	}

	/**
	 * get iSkip'th element FlushedResources
	 *
	 * @param iSkip number of elements to skip
	 * @return JDFFlushedResources: the element
	 */
	public JDFFlushedResources getCreateFlushedResources(final int iSkip)
	{
		return (JDFFlushedResources) getCreateValidElement(ElementName.FLUSHEDRESOURCES, null, iSkip);
	}

	/**
	 * append element <code>FlushedResources</code>
	 *
	 * @return JDFFlushedResources: the element
	 */
	public JDFFlushedResources appendFlushedResources()
	{
		return (JDFFlushedResources) appendValidElement(ElementName.FLUSHEDRESOURCES, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFFlushedResources getFlushedResources(final int iSkip)
	{
		return (JDFFlushedResources) getValidElement(ElementName.FLUSHEDRESOURCES, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFFlushQueueParams getCreateFlushQueueParams(final int iSkip)
	{
		return (JDFFlushQueueParams) getCreateValidElement(ElementName.FLUSHQUEUEPARAMS, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFFlushQueueInfo getCreateFlushQueueInfo(final int iSkip)
	{
		return (JDFFlushQueueInfo) getCreateValidElement(ElementName.FLUSHQUEUEINFO, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFFlushQueueInfo getFlushQueueInfo(final int iSkip)
	{
		return (JDFFlushQueueInfo) getValidElement(ElementName.FLUSHQUEUEINFO, null, iSkip);
	}

	/**
	 * @return
	 */
	public JDFFlushQueueParams appendFlushQueueParams()
	{
		return (JDFFlushQueueParams) appendValidElement(ElementName.FLUSHQUEUEPARAMS, null);
	}

	/**
	 * @return
	 */
	public JDFFlushQueueInfo appendFlushQueueInfo()
	{
		return (JDFFlushQueueInfo) appendValidElement(ElementName.FLUSHQUEUEINFO, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFFlushQueueParams getFlushQueueParams(final int iSkip)
	{
		return (JDFFlushQueueParams) getValidElement(ElementName.FLUSHQUEUEPARAMS, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFFlushResourceParams getCreateFlushResourceParams(final int iSkip)
	{
		return (JDFFlushResourceParams) getCreateValidElement(ElementName.FLUSHRESOURCEPARAMS, null, iSkip);
	}

	/**
	 * @return
	 */
	public JDFFlushResourceParams appendFlushResourceParams()
	{
		return (JDFFlushResourceParams) appendValidElement(ElementName.FLUSHRESOURCEPARAMS, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFFlushResourceParams getFlushResourceParams(final int iSkip)
	{
		return (JDFFlushResourceParams) getValidElement(ElementName.FLUSHRESOURCEPARAMS, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFNewJDFCmdParams getCreateNewJDFCmdParams(final int iSkip)
	{
		return (JDFNewJDFCmdParams) getCreateValidElement(ElementName.NEWJDFCMDPARAMS, null, iSkip);
	}

	/**
	 * @return
	 */
	public JDFNewJDFCmdParams appendNewJDFCmdParams()
	{
		return (JDFNewJDFCmdParams) appendValidElement(ElementName.NEWJDFCMDPARAMS, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFNewJDFCmdParams getNewJDFCmdParams(final int iSkip)
	{
		return (JDFNewJDFCmdParams) getValidElement(ElementName.NEWJDFCMDPARAMS, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFNewJDFQuParams getCreateNewJDFQuParams(final int iSkip)
	{
		return (JDFNewJDFQuParams) getCreateValidElement(ElementName.NEWJDFQUPARAMS, null, iSkip);
	}

	/**
	 * @return
	 */
	public JDFNewJDFQuParams appendNewJDFQuParams()
	{
		return (JDFNewJDFQuParams) appendValidElement(ElementName.NEWJDFQUPARAMS, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFNewJDFQuParams getNewJDFQuParams(final int iSkip)
	{
		return (JDFNewJDFQuParams) getValidElement(ElementName.NEWJDFQUPARAMS, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFNodeInfoCmdParams getCreateNodeInfoCmdParams(final int iSkip)
	{
		return (JDFNodeInfoCmdParams) getCreateValidElement(ElementName.NODEINFOCMDPARAMS, null, iSkip);
	}

	/**
	 * @return
	 */
	public JDFNodeInfoCmdParams appendNodeInfoCmdParams()
	{
		return (JDFNodeInfoCmdParams) appendValidElement(ElementName.NODEINFOCMDPARAMS, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFNodeInfoCmdParams getNodeInfoCmdParams(final int iSkip)
	{
		return (JDFNodeInfoCmdParams) getValidElement(ElementName.NODEINFOCMDPARAMS, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFNodeInfoQuParams getCreateNodeInfoQuParams(final int iSkip)
	{
		return (JDFNodeInfoQuParams) getCreateValidElement(ElementName.NODEINFOQUPARAMS, null, iSkip);
	}

	/**
	 * @return
	 */
	public JDFNodeInfoQuParams appendNodeInfoQuParams()
	{
		return (JDFNodeInfoQuParams) appendValidElement(ElementName.NODEINFOQUPARAMS, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFNodeInfoQuParams getNodeInfoQuParams(final int iSkip)
	{
		return (JDFNodeInfoQuParams) getValidElement(ElementName.NODEINFOQUPARAMS, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFNodeInfoResp getCreateNodeInfoResp(final int iSkip)
	{
		return (JDFNodeInfoResp) getCreateValidElement(ElementName.NODEINFORESP, null, iSkip);
	}

	/**
	 * @return
	 */
	public JDFNodeInfoResp appendNodeInfoResp()
	{
		return (JDFNodeInfoResp) appendValidElement(ElementName.NODEINFORESP, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFNodeInfoResp getNodeInfoResp(final int iSkip)
	{
		return (JDFNodeInfoResp) getValidElement(ElementName.NODEINFORESP, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFQueueFilter getCreateQueueFilter(final int iSkip)
	{
		return (JDFQueueFilter) getCreateValidElement(ElementName.QUEUEFILTER, null, iSkip);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @return
	 */
	public JDFQueueFilter appendQueueFilter()
	{
		return (JDFQueueFilter) appendValidElement(ElementName.QUEUEFILTER, null);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFQueueFilter getQueueFilter(final int iSkip)
	{
		return (JDFQueueFilter) getValidElement(ElementName.QUEUEFILTER, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFRequestQueueEntryParams getCreateRequestQueueEntryParams(final int iSkip)
	{
		return (JDFRequestQueueEntryParams) getCreateValidElement(ElementName.REQUESTQUEUEENTRYPARAMS, null, iSkip);
	}

	/**
	 * @return
	 */
	public JDFRequestQueueEntryParams appendRequestQueueEntryParams()
	{
		return (JDFRequestQueueEntryParams) appendValidElement(ElementName.REQUESTQUEUEENTRYPARAMS, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFRequestQueueEntryParams getRequestQueueEntryParams(final int iSkip)
	{
		return (JDFRequestQueueEntryParams) getValidElement(ElementName.REQUESTQUEUEENTRYPARAMS, null, iSkip);
	}

	JDFResourcePullParams getCreateResourcePullParams(final int iSkip)
	{
		return (JDFResourcePullParams) getCreateValidElement(ElementName.RESOURCEPULLPARAMS, null, iSkip);
	}

	/**
	 * @return
	 */
	public JDFResourcePullParams appendResourcePullParams()
	{
		return (JDFResourcePullParams) appendValidElement(ElementName.RESOURCEPULLPARAMS, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFResourcePullParams getResourcePullParams(final int iSkip)
	{
		return (JDFResourcePullParams) getValidElement(ElementName.RESOURCEPULLPARAMS, null, iSkip);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFResubmissionParams getCreateResubmissionParams(final int iSkip)
	{
		return (JDFResubmissionParams) getCreateValidElement(ElementName.RESUBMISSIONPARAMS, null, iSkip);
	}

	/**
	 * @return
	 */
	public JDFResubmissionParams appendResubmissionParams()
	{
		return (JDFResubmissionParams) appendValidElement(ElementName.RESUBMISSIONPARAMS, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFResubmissionParams getResubmissionParams(final int iSkip)
	{
		return (JDFResubmissionParams) getValidElement(ElementName.RESUBMISSIONPARAMS, null, iSkip);
	}

	/**
	 *
	 * @param iSkip
	 * @return
	 */
	public JDFReturnQueueEntryParams getCreateReturnQueueEntryParams(final int iSkip)
	{
		return (JDFReturnQueueEntryParams) getCreateValidElement(ElementName.RETURNQUEUEENTRYPARAMS, null, iSkip);
	}

	/**
	 *
	 * @return
	 */
	public JDFReturnQueueEntryParams appendReturnQueueEntryParams()
	{
		return (JDFReturnQueueEntryParams) appendValidElement(ElementName.RETURNQUEUEENTRYPARAMS, null);
	}

	/**
	 *
	 * @param iSkip
	 * @return
	 */
	public JDFReturnQueueEntryParams getReturnQueueEntryParams(final int iSkip)
	{
		return (JDFReturnQueueEntryParams) getValidElement(ElementName.RETURNQUEUEENTRYPARAMS, null, iSkip);
	}

	/**
	 *
	 * @param iSkip
	 * @return
	 */
	public JDFSubscriptionInfo getCreateSubscriptionInfo(final int iSkip)
	{
		return (JDFSubscriptionInfo) getCreateValidElement(ElementName.SUBSCRIPTIONINFO, null, iSkip);
	}

	/**
	 *
	 * @return
	 */
	public JDFSubscriptionInfo appendSubscriptionInfo()
	{
		return (JDFSubscriptionInfo) appendValidElement(ElementName.SUBSCRIPTIONINFO, null);
	}

	/**
	 *
	 * @param iSkip
	 * @return
	 */
	public JDFSubscriptionInfo getSubscriptionInfo(final int iSkip)
	{
		return (JDFSubscriptionInfo) getValidElement(ElementName.SUBSCRIPTIONINFO, null, iSkip);
	}

	/**
	 *
	 * @return
	 */
	public JDFSubscriptionFilter getSubscriptionFilter()
	{
		return (JDFSubscriptionFilter) getValidElement(ElementName.SUBSCRIPTIONFILTER, null, 0);
	}

	/**
	 *
	 * @return
	 */
	public JDFSubscriptionFilter getCreateSubscriptionFilter()
	{
		return (JDFSubscriptionFilter) getCreateValidElement(ElementName.SUBSCRIPTIONFILTER, null, 0);
	}

	/**
	 *
	 * @return
	 */
	public JDFSubscriptionFilter appendSubscriptionFilter()
	{
		return (JDFSubscriptionFilter) appendValidElement(ElementName.SUBSCRIPTIONFILTER, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFShutDownCmdParams getCreateShutDownCmdParams(final int iSkip)
	{
		return (JDFShutDownCmdParams) getCreateValidElement(ElementName.SHUTDOWNCMDPARAMS, null, iSkip);
	}

	/**
	 * @return
	 */
	public JDFShutDownCmdParams appendShutDownCmdParams()
	{
		return (JDFShutDownCmdParams) appendValidElement(ElementName.SHUTDOWNCMDPARAMS, null);
	}

	/**
	 * @param iSkip
	 * @return
	 */
	public JDFShutDownCmdParams getShutDownCmdParams(final int iSkip)
	{
		return (JDFShutDownCmdParams) getValidElement(ElementName.SHUTDOWNCMDPARAMS, null, iSkip);
	}

	// //////////////////////////////////////////////////////////////////////////
	// //

	/**
	 * @return
	 */
	public JDFWakeUpCmdParams getCreateWakeUpCmdParams()
	{
		return (JDFWakeUpCmdParams) getCreateValidElement(ElementName.WAKEUPCMDPARAMS, null, 0);
	}

	/**
	 * @return
	 */
	public JDFWakeUpCmdParams appendWakeUpCmdParams()
	{
		return (JDFWakeUpCmdParams) appendValidElement(ElementName.WAKEUPCMDPARAMS, null);
	}

	/**
	 * @return
	 */
	public JDFWakeUpCmdParams getWakeUpCmdParams()
	{
		return (JDFWakeUpCmdParams) getValidElement(ElementName.WAKEUPCMDPARAMS, null, 0);
	}

	// //////////////////////////////////////////////////////////////////////////
	// //

	/**
	 * @return
	 */
	public JDFModifyNodeCmdParams getCreateModifyNodeCmdParams()
	{
		return (JDFModifyNodeCmdParams) getCreateValidElement(ElementName.MODIFYNODECMDPARAMS, null, 0);
	}

	/**
	 * @return
	 */
	public JDFModifyNodeCmdParams appendModifyNodeCmdParams()
	{
		return (JDFModifyNodeCmdParams) appendValidElement(ElementName.MODIFYNODECMDPARAMS, null);
	}

	/**
	 * @return
	 */
	public JDFModifyNodeCmdParams getModifyNodeCmdParams()
	{
		return (JDFModifyNodeCmdParams) getValidElement(ElementName.MODIFYNODECMDPARAMS, null, 0);
	}

	// //////////////////////////////////////////////////////////////////////////
	// //

	/**
	 * @return
	 */
	public JDFUpdateJDFCmdParams getCreateUpdateJDFCmdParams()
	{
		return (JDFUpdateJDFCmdParams) getCreateValidElement(ElementName.UPDATEJDFCMDPARAMS, null, 0);
	}

	/**
	 * @return
	 */
	public JDFUpdateJDFCmdParams appendUpdateJDFCmdParams()
	{
		return (JDFUpdateJDFCmdParams) appendValidElement(ElementName.UPDATEJDFCMDPARAMS, null);
	}

	/**
	 * @return
	 */
	public JDFUpdateJDFCmdParams getUpdateJDFCmdParams()
	{
		return (JDFUpdateJDFCmdParams) getValidElement(ElementName.UPDATEJDFCMDPARAMS, null, 0);
	}

	/**
	 * Method getrefID.
	 *
	 * @return String
	 */
	public String getrefID()
	{
		return getAttribute(AttributeName.REFID);
	}

	/**
	 * Method setrefID.
	 *
	 * @param refID
	 */
	public void setrefID(final String refID)
	{
		setAttribute(AttributeName.REFID, refID);
	}

	/**
	 *
	 *
	 * @see org.cip4.jdflib.core.JDFElement#getInvalidElements(org.cip4.jdflib.core.JDFElement.EnumValidationLevel, boolean, int)
	 */
	@Override
	public VString getInvalidElements(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{
		int nElem = 0;
		final VString vElem = super.getInvalidElements(level, bIgnorePrivate, nMax);
		int n = vElem.size();
		if (n >= nMax)
		{
			return vElem;
		}

		final KElement[] ae = getChildElementArray();
		if (ae == null || ae.length == 0)
		{
			return vElem;
		}
		final Set<String> s = new HashSet<>();
		for (final KElement element : ae)
		{
			s.add(element.getLocalName());
		}

		final String[] elementArray = getElementArray();

		for (final String element2 : elementArray)
		{
			final String element = element2;
			if (!s.contains(element))
			{
				continue;
			}

			nElem = numChildElements(element, null);
			for (int i = 0; i < nElem; i++)
			{
				KElement child = null;
				boolean bCatch = false;

				try
				{
					child = getValidElement(element, null, i);
				}
				catch (final JDFException e)
				{
					bCatch = true;
				}
				if (bCatch || child == null || (child instanceof JDFElement) && !((JDFElement) child).isValid(level))
				{
					vElem.appendUnique(element);
					if (++n >= nMax)
					{
						return vElem;
					}
					break;
				}
			}
		}

		return vElem;
	}

	/**
	 * @return
	 */
	private String[] getElementArray()
	{
		if (elemArray == null)
		{
			final VString v = new VString(queryTypeObjString);
			v.addAll(responseTypeObjString);
			v.addAll(commandTypeObjString);
			v.unify();
			elemArray = v.toArray(new String[v.size()]);
		}
		return elemArray;
	}

	/**
	 * definition of optional elements in the JDF namespace
	 */
	// TODO move to elemeInfoTable creation
	/*
	 * (non-Javadoc)
	 *
	 * @see org.cip4.jdflib.core.KElement#optionalElements()
	 */
	@Override
	public VString optionalElements()
	{
		final VString s = super.optionalElements();
		final EnumType t = getEnumType();
		// loop over all valid potential elements for this family
		final String[] vObjs = familyTypeObj();
		if (vObjs == null)
			return s;
		// for each object, check whether it is compatible with the type of this
		for (final String vObj : vObjs)
		{
			final Vector<EnumType> vt = getValidTypeVector(vObj, 0);
			// is it there ?
			for (int j = 0; j < vt.size(); j++)
			{
				if (vt.elementAt(j).equals(t))
				{
					// obj x is compatible with this -> add it to the list of
					// elements
					s.appendUnique(vObj);
					break;
				}
			}
		}
		return s;
	}

	/**
	 *
	 * @see org.cip4.jdflib.core.JDFElement#getInvalidAttributes(org.cip4.jdflib.core.JDFElement.EnumValidationLevel, boolean, int)
	 */
	@Override
	public VString getInvalidAttributes(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{
		final VString s = super.getInvalidAttributes(level, bIgnorePrivate, nMax);
		if (s.contains(AttributeName.XSITYPE))
		{
			return s;
		}

		if (!hasAttribute(AttributeName.XSITYPE))
		{
			return s;
		}
		final String t = getType();
		if (xmlnsPrefix(t) != null)
		{
			return s;
		}
		final String xs = getXSIType();
		if (xs != null && !xs.equals(getLocalName() + t))
		{
			s.add(AttributeName.XSITYPE);
		}
		return s;

	}

	// //////////////////////////////////////////////////////////////////////////
	// ////
	/*
	 * --------------------------------------------------------------------- Methods for Attribute ICSVersions ---------------------------------------------------------------------
	 */

	/**
	 * (21) get VString attribute ICSVersions, grab from parent JMF if none exists here
	 *
	 * @return VString the value of the attribute
	 *
	 */
	@Override
	public VString getICSVersions()
	{
		final String s = getAttribute(AttributeName.ICSVERSIONS, null, null);
		if (s != null)
		{
			return StringUtil.tokenize(s, null, false);
		}
		final KElement parentJMF = getParentNode_KElement();
		if (parentJMF instanceof JDFJMF)
		{
			return ((JDFJMF) parentJMF).getICSVersions();
		}
		return null;
	}

	// //////////////////////////////////////////////////////////////////////////
	// ////

	/**
	 * gets the senderID of this message
	 *
	 * @return String the senderID of this message or the SenderID of the parent JMF; null if neither are specified
	 */
	@Override
	public String getSenderID()
	{
		if (hasAttribute(AttributeName.SENDERID))
		{
			return getAttribute(AttributeName.SENDERID);
		}
		final JDFJMF parentJMF = getJMFRoot();
		return parentJMF == null ? null : parentJMF.getSenderID();

	}

	/**
	 * @see org.cip4.jdflib.core.JDFElement#getDeprecatedElements(int)
	 * @param nMax
	 * @return
	 */
	@Override
	public VString getDeprecatedElements(final int nMax)
	{
		final VString v = super.getDeprecatedElements(nMax);

		if (EnumUtil.aLessThanB(EnumVersion.Version_1_1, getVersion(true)) && hasChildElement(ElementName.JDFSERVICE, null))
		{
			v.add(ElementName.JDFSERVICE);
		}
		return v;
	}

	/**
	 * @see org.cip4.jdflib.core.JDFElement#getLastVersion(java.lang.String, boolean)
	 * @param eaName
	 * @param bElement
	 * @return
	 */
	@Override
	public EnumVersion getLastVersion(final String eaName, final boolean bElement)
	{
		if (ElementName.JDFSERVICE.equals(eaName) && bElement)
		{
			return EnumVersion.Version_1_1;
		}

		return super.getLastVersion(eaName, bElement);
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoMessage#getTime()
	 */
	@Override
	public JDFDate getTime()
	{
		final JDFDate time = super.getTime();
		if (time == null)
		{
			final JDFJMF jmf = getJMFRoot();
			return jmf == null ? null : jmf.getTimeStamp();
		}
		return time;
	}

}
