/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFJMF.java
 *
 * Last changes
 *
 * 2002-07-02 JG modifications in GetMessageElement() 2002-07-02 JG added GetInvalidElements(), RequiredElements() 2002-07-02 JG added EnumTyp to calls of AppendCommand/Response/... 2002-07-02 JG
 * init() added call to GetSchemaURL()
 *
 *
 */
package org.cip4.jdflib.jmf;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoJMF;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.util.EnumUtil;

/**
 * The wrapper for JMF messages, i.e. the root of a JMF document
 */
public class JDFJMF extends JDFAutoJMF
{
	private static final long serialVersionUID = 1L;
	private static String theSenderID = null;
	private static final Log mLog = LogFactory.getLog(JDFElement.class);

	/**
	 * parse a JMF file
	 *
	 * @param file
	 * @return the parsed JDFNode
	 */
	public static JDFJMF parseFile(final File file)
	{
		final JDFDoc doc = JDFDoc.parseFile(file);
		return doc == null ? null : doc.getJMFRoot();
	}

	/**
	 * parse a JMF file
	 *
	 * @param fileName
	 * @return the parsed JDFNode
	 */
	public static JDFJMF parseFile(final String fileName)
	{
		final JDFDoc doc = JDFDoc.parseFile(fileName);
		return doc == null ? null : doc.getJMFRoot();
	}

	/**
	 * Constructor for JDFJMF
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFJMF(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFJMF
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFJMF(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI == null ? getSchemaURL() : myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFJMF
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFJMF(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI == null ? getSchemaURL() : myNamespaceURI, qualifiedName, myLocalName);
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
		return "JDFJMF[ --> " + super.toString() + " ]";
	}

	/**
	 * init
	 *
	 * @return boolean
	 */
	@Override
	public boolean init()
	{
		super.init();
		setTimeStamp(null);
		setVersion(getDefaultJDFVersion());
		final EnumVersion version = getVersion(true);
		if (!EnumVersion.Version_1_3.isGreater(version))
		{
			setMaxVersion(version);
			if (!EnumVersion.Version_1_4.isGreater(version))
			{
				setAgentName(JDFAudit.getStaticAgentName());
				setAgentVersion(JDFAudit.getStaticAgentVersion());
			}
		}
		setXSIType("JMFRootMessage");
		if (theSenderID != null)
		{
			setSenderID(theSenderID);
		}
		return true;
	}

	/**
	 * get attribute MaxVersion, defaults to version if not set
	 *
	 * @return EnumVersion - attribute value
	 *
	 *         default - getMaxVersion(false)
	 */
	@Override
	public EnumVersion getMaxVersion()
	{
		final String version = getAttribute(AttributeName.MAXVERSION, null, null);

		if (version == null)
		{
			return getVersion(false);
		}

		return EnumVersion.getEnum(version);
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
	 * set MaxVersion to enumVer
	 *
	 * @param enumVer the EnumVersion to set
	 */
	@Override
	public void setMaxVersion(final EnumVersion enumVer)
	{
		setAttribute(AttributeName.MAXVERSION, enumVer == null ? null : enumVer.getName(), null);
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 *
	 *         Appendix D (JDF 1.3) - Supported Error Codes in JMF and Notification elements Jun 7, 2009
	 */
	public static class EnumJMFReturnCode extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;

		private EnumJMFReturnCode(final int code, final String message)
		{
			super(message, code);
		}

		/**
		 * @param message
		 * @return
		 */
		public static EnumJMFReturnCode getEnum(final String message)
		{
			return (EnumJMFReturnCode) getEnum(EnumJMFReturnCode.class, message);
		}

		/**
		 * @param code
		 * @return
		 */
		public static EnumJMFReturnCode getEnum(final int code)
		{
			return (EnumJMFReturnCode) getEnum(EnumJMFReturnCode.class, code);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumJMFReturnCode.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumJMFReturnCode.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumJMFReturnCode.class);
		}

		/**
		 *
		 */
		public static final EnumJMFReturnCode SUCCESS = new EnumJMFReturnCode(0, "Success");

		/**
		 * 1..99 Protocol errors
		 */
		public static final EnumJMFReturnCode GENERAL_ERROR = new EnumJMFReturnCode(1, "General error");

		/**
		 *
		 */
		public static final EnumJMFReturnCode INTERNAL_ERROR = new EnumJMFReturnCode(2, "Internal error");

		/**
		 *
		 */
		public static final EnumJMFReturnCode XML_PARSER = new EnumJMFReturnCode(3, "XML parser error, e.g., if a MIME file is sent to an XML controller. ");

		/**
		 *
		 */
		public static final EnumJMFReturnCode XML_VALIDATION = new EnumJMFReturnCode(4, "XML validation error");

		/**
		 *
		 */
		public static final EnumJMFReturnCode MESSAGE_NOT_IMPLEMENTED = new EnumJMFReturnCode(5, "Query/command not implemented");

		/**
		 *
		 */
		public static final EnumJMFReturnCode INVALID_PARAMETER = new EnumJMFReturnCode(6, "Invalid parameters");

		/**
		 *
		 */
		public static final EnumJMFReturnCode INSUFFICIENT_PARAMETER = new EnumJMFReturnCode(7, "Insufficient parameters");

		/**
		 *
		 */
		public static final EnumJMFReturnCode DEVICE_NOT_AVAILABLE = new EnumJMFReturnCode(8, "Device not available (controller exists but not the device or queue)");

		/**
		 *
		 */
		public static final EnumJMFReturnCode MESSAGE_INCOMPLETE = new EnumJMFReturnCode(9, "Message incomplete.");

		/**
		 *
		 */
		public static final EnumJMFReturnCode MESSAGESERVICE_BUSY = new EnumJMFReturnCode(10, "Message Service is busy");

		/**
		 * 100..199 Device and controller errors
		 */
		public static final EnumJMFReturnCode DEVICE_NOT_RUNNING = new EnumJMFReturnCode(100, "Device not running");

		/**
		 *
		 */
		public static final EnumJMFReturnCode INCAPABLE_REQUEST = new EnumJMFReturnCode(101, "Device incapable of fulfilling request");

		/**
		 *
		 */
		public static final EnumJMFReturnCode NO_EXCUTABLE_NODE = new EnumJMFReturnCode(102, "No executable node exists in the JDF");

		/**
		 *
		 */
		public static final EnumJMFReturnCode UNKNOWN_JOB_ID = new EnumJMFReturnCode(103, "Job ID not known by controller");

		/**
		 *
		 */
		public static final EnumJMFReturnCode UNKNOWN_JOBPART_ID = new EnumJMFReturnCode(104, "JobPartID not known by controller");

		/**
		 *
		 */
		public static final EnumJMFReturnCode UNKNOWN_QUEUE_ENRTY = new EnumJMFReturnCode(105, "Queue entry not in queue");

		/**
		 *
		 */
		public static final EnumJMFReturnCode QUEUE_ENRTY_ALREADY_EXECUTED = new EnumJMFReturnCode(106, "Queue request failed because queue entry is already executing");

		/**
		 *
		 */
		public static final EnumJMFReturnCode NO_CHANGE_EXECUTING_QUEUE_ENRTY = new EnumJMFReturnCode(107, "Queue entry is already executing. Late change is not accepted");

		/**
		 *
		 */
		public static final EnumJMFReturnCode RESULT_SELECTION_EMPTY = new EnumJMFReturnCode(108, "Selection or applied filter results in an empty list");

		/**
		 *
		 */
		public static final EnumJMFReturnCode RESULT_SELECTION_INCOMPLETE = new EnumJMFReturnCode(109,
				"Selection or applied filter results in an incomplete list. A buffer cannot provide the complete list queried for");

		/**
		 *
		 */
		public static final EnumJMFReturnCode REQUEST_FAILED_COMPLETION_TIME = new EnumJMFReturnCode(110,
				"Queue request of a job submission failed because the requested completion time of the job can-not be fulfilled");

		/**
		 *
		 */
		public static final EnumJMFReturnCode SUBSCRIPTION_REQUEST_DENIED = new EnumJMFReturnCode(111, "Subscription request denied");

		/**
		 *
		 */
		public static final EnumJMFReturnCode QUEUE_CLOSED = new EnumJMFReturnCode(112, "Queue request failed because the Queue is closed and does not accept new entries");

		/**
		 *
		 */
		public static final EnumJMFReturnCode QUEUE_ENTRY_ALREADY_IN_STATUS = new EnumJMFReturnCode(113, "Queue entry is already in the resulting status");

		/**
		 *
		 */
		public static final EnumJMFReturnCode QUEUE_ENTRY_COMPLETED_OR_ABORTED = new EnumJMFReturnCode(114,
				"Queue entry is already completed or aborted and therefore does not accept changes");

		/**
		 *
		 */
		public static final EnumJMFReturnCode QUEUE_ENTRY_NOT_RUNNING = new EnumJMFReturnCode(115, "Queue entry is not running");
		/**
		 *
		 */
		public static final EnumJMFReturnCode QUEUE_ENTRY_EXISTS = new EnumJMFReturnCode(116, "Queue entry already exists");
		/**
		 *
		 */
		public static final EnumJMFReturnCode URL_INACCESSIBLE = new EnumJMFReturnCode(120, "URL is not accessible");
		/**
		 *
		 */
		public static final EnumJMFReturnCode UNKNOWN_DEVICEID = new EnumJMFReturnCode(121, "Unknown DeviceID");
		/**
		 *
		 */
		public static final EnumJMFReturnCode GANGING_NOT_SUPPORTED = new EnumJMFReturnCode(130, "Ganging not supported");
		/**
		 *
		 */
		public static final EnumJMFReturnCode UNKNOWN_GANGNAME = new EnumJMFReturnCode(131, " Unknown Gang name");

		// 200..299 Job and pipe specific errors
		/**
		 *
		 */
		public static final EnumJMFReturnCode INVALID_RESOURCE_PARAMETERS = new EnumJMFReturnCode(200, "Invalid resource parameters");

		/**
		 *
		 */
		public static final EnumJMFReturnCode INSUFFICIENT_RESOURCE_PARAMETERS = new EnumJMFReturnCode(201, "Insufficient resource parameters");

		/**
		 *
		 */
		public static final EnumJMFReturnCode PIPE_UNKNOWN = new EnumJMFReturnCode(202, "Pipe unknown");

		/**
		 *
		 */
		public static final EnumJMFReturnCode UNLINKED_RESOURCE_LINK = new EnumJMFReturnCode(203, "Unlinked resource link");
		/**
		 *
		 */
		public static final EnumJMFReturnCode JDF_NOT_CREATED = new EnumJMFReturnCode(204, "Could not create new JDF node");

		/**
		 *
		 */
		public static final EnumJMFReturnCode AUTHENTICATION_DENIED = new EnumJMFReturnCode(300, "Authentication Denied");
		/**
		 *
		 */
		public static final EnumJMFReturnCode SECURE_NOT_SUPPORTED = new EnumJMFReturnCode(301, "Secure channel not supported");
		/**
		 *
		 */
		public static final EnumJMFReturnCode SECURE_REQUIRED = new EnumJMFReturnCode(302, "Secure channel required");
		/**
		 *
		 */
		public static final EnumJMFReturnCode CERTIFICATE_EXPIRED = new EnumJMFReturnCode(303, "Certificate is expired");
		/**
		 *
		 */
		public static final EnumJMFReturnCode AUTHENTICATION_PENDING = new EnumJMFReturnCode(304, "Authentication pending");
		/**
		 *
		 */
		public static final EnumJMFReturnCode AUTHENTICATION_ESTABLISHED = new EnumJMFReturnCode(305, "Authentication already established");
		/**
		 *
		 */
		public static final EnumJMFReturnCode NO_AUTHENTICATION_IN_PROCESS = new EnumJMFReturnCode(306, "No Authentication in process");
		/**
		 *
		 */
		public static final EnumJMFReturnCode CERTIFICATE_INVALID = new EnumJMFReturnCode(307, "Certificate is invalid");
	}

	/**
	 * GetMessage - get the ith message, regardless of type
	 *
	 * @param i message index
	 *
	 * @return JDFMessage - the ith message
	 *
	 */
	public JDFMessage getMessage(final int i)
	{
		return getMessageElement(null, null, i);
	}

	/**
	 * Get the 0h command
	 *
	 * @return JDFCommand: the message element
	 * 
	 */
	@Deprecated
	public JDFCommand getCommand()
	{
		return getCommand(0, false);
	}

	/**
	 *
	 * @param i
	 * @param bCreate
	 * @return
	 * @deprecated use getMessageElement or getCreateMessageElement
	 */
	@Deprecated
	public JDFCommand getCommand(final int i, final boolean bCreate)
	{
		if (bCreate)
		{
			return (JDFCommand) getCreateMessageElement(JDFMessage.EnumFamily.Command, null, i);
		}
		return (JDFCommand) getMessageElement(JDFMessage.EnumFamily.Command, null, i);
	}

	/**
	 * Get the 0th query,
	 *
	 * @return JDFQuery the message element
	 *
	 * @deprecated use getMessageElement
	 */
	// JDFQuery GetQuery(int i=0,bool bCreate=false);
	@Deprecated
	public JDFQuery getQuery()
	{
		return getQuery(0, false);
	}

	/**
	 *
	 * @param i
	 * @param bCreate
	 * @return
	 * @deprecated use getMessageElement or getCreateMessageElement
	 */
	@Deprecated
	public JDFQuery getQuery(final int i, final boolean bCreate)
	{
		if (bCreate)
		{
			return (JDFQuery) getCreateMessageElement(JDFMessage.EnumFamily.Query, null, i);
		}

		return (JDFQuery) getMessageElement(JDFMessage.EnumFamily.Query, null, i);
	}

	/**
	 * getResponse()
	 *
	 * @return JDFResponse the message element
	 * @deprecated use getMessageElement
	 */
	// JDFResponse GetResponse(int i=0,bool bCreate=false);
	@Deprecated
	public JDFResponse getResponse()
	{
		return getResponse(0, false);
	}

	/**
	 * getResponse()
	 *
	 * @param i
	 * @param bCreate
	 * @return
	 * @deprecated use getMessageElement or getCreateMessageElement
	 */
	@Deprecated
	public JDFResponse getResponse(final int i, final boolean bCreate)
	{
		if (bCreate)
		{
			return (JDFResponse) getCreateMessageElement(JDFMessage.EnumFamily.Response, null, i);
		}
		return (JDFResponse) getMessageElement(JDFMessage.EnumFamily.Response, null, i);
	}

	/**
	 * Get the 0th signal,
	 *
	 * @return JDFSignal the message element
	 * @deprecated use getMessageElement
	 */
	// JDFSignal GetSignal(int i=0,bool bCreate=false);
	@Deprecated
	public JDFSignal getSignal()
	{
		return getSignal(0, false);
	}

	/**
	 *
	 * @param i
	 * @param bCreate
	 * @return
	 * @deprecated use getMessageElement
	 */
	@Deprecated
	public JDFSignal getSignal(final int i, final boolean bCreate)
	{
		if (bCreate)
		{
			return (JDFSignal) getCreateMessageElement(JDFMessage.EnumFamily.Signal, null, i);
		}
		return (JDFSignal) getMessageElement(JDFMessage.EnumFamily.Signal, null, i);
	}

	/**
	 * get an existing message element, create it if it doesn't exist
	 *
	 * @param family the Message family - Query, Acknowledge, Command, Response, Registration or Signal
	 * @param typ the message type
	 * @param i get the ith element
	 * @return the newly created message
	 */
	public JDFMessage getCreateMessageElement(final JDFMessage.EnumFamily family, final JDFMessage.EnumType typ, final int i)
	{
		if (family == null)
		{
			throw new JDFException("GetMessageElement: creating undefined message family");
		}

		JDFMessage m = getMessageElement(family, typ, i);

		if (m == null)
		{
			m = appendMessageElement(family, typ);
		}

		return m;
	}

	/**
	 * get an existing message element, create it if it doesn't exist
	 *
	 * @param family the Message family - Query, Acknowledge, Command, Response, Registration or Signal
	 * @param i get the ith element
	 * @return the newly created message
	 * @deprecated use getCreateMessageElement(family, null, i);
	 */
	@Deprecated
	public JDFMessage getCreateMessageElement(final JDFMessage.EnumFamily family, final int i)
	{
		return getCreateMessageElement(family, null, i);
	}

	/**
	 * append a message element to this
	 *
	 * @param family
	 * @return
	 * @deprecated use appendMessageElement (family, null);
	 */
	@Deprecated
	public JDFMessage appendMessageElement(final JDFMessage.EnumFamily family)
	{
		return appendMessageElement(family, null);
	}

	/**
	 * create a new JMF with one Message Element of family <code>family</code> and type <code>typ</code>
	 *
	 * @param family the Message family - Query, Acknowledge, Command, Response, Registration or Signal
	 * @param typ the messages @Type value, null if unknown
	 * @return the newly created message
	 */
	public static JDFJMF createJMF(final JDFMessage.EnumFamily family, final JDFMessage.EnumType typ)
	{
		if (family == null)
		{
			throw new JDFException("createJMF: creating undefined message family");
		}

		final JDFJMF jmf = new JDFDoc(ElementName.JMF).getJMFRoot();
		jmf.appendMessageElement(family, typ);
		return jmf;
	}

	/**
	 * append a message element to <code>this</code>
	 *
	 * @param family the Message family - Query, Acknowledge, Command, Response, Registration or Signal
	 * @param typ the messages @Type value, null if unknown
	 * @return the newly created message
	 */
	public JDFMessage appendMessageElement(final JDFMessage.EnumFamily family, final JDFMessage.EnumType typ)
	{
		if (family == null)
		{
			throw new JDFException("appendMessageElement: creating undefined message family");
		}

		final String sFamily = family.getName();
		final JDFMessage m = (JDFMessage) appendElement(sFamily, null);
		if (typ != null)
		{
			m.setType(typ);
		}

		return m;
	}

	/**
	 * get the ith message element of family type family
	 *
	 * @param family
	 * @param i
	 * @return
	 * @deprecated since 060619, use getMessageElement (JDFMessage.EnumFamily family, JDFMessage.EnumType typ, int i)
	 */
	@Deprecated
	public JDFMessage getMessageElement(final JDFMessage.EnumFamily family, final int i)
	{
		return getMessageElement(family, null, i);
	}

	/**
	 * get the ith message element of family type family
	 *
	 * @param family the Message family - Query, Acknowledge, Command, Response, Registration or Signal
	 * @param typ the messages @Type value, null if unknown
	 * @param i the i'th message element to get, if i<0, get from back
	 * @return the matching message, null if none are found
	 */
	public JDFMessage getMessageElement(final JDFMessage.EnumFamily family, final JDFMessage.EnumType typ, int i)
	{
		if (i < 0) // search from back
		{
			JDFMessage message = null;
			final VElement v = getMessageVector(family, typ);
			if (v != null)
			{
				final int siz = v.size();
				i = siz + i;
				message = (JDFMessage) (i >= 0 ? v.get(i) : null);
			}

			return message;
		}

		final String typString = typ == null ? null : typ.getName();
		final String familyString = family == null ? null : family.getName();

		KElement e = getElement(familyString, null, 0);
		int n = 0;

		while (e != null)
		{
			if (e instanceof JDFMessage)
			{
				final JDFMessage m = (JDFMessage) e;
				if (typString == null || typString.equals(m.getType()))
				{
					if (n++ >= i)
					{
						return m;
					}
				}
			}
			e = e.getNextSiblingElement(familyString, null);
		}

		return null;
	}

	/**
	 * get a vector of all messages in this JMF
	 *
	 * @return VElement all message elements
	 * @deprecated use getMessageVector (null, null)
	 */
	@Deprecated
	public VElement getMessageVector()
	{
		return getMessageVector(null, null);
	}

	/**
	 * get a vector of all messages in this JMF
	 *
	 * @param family requested message family
	 * @return VElement all message elements
	 * @deprecated use getMessageVector (family, null)
	 */
	@Deprecated
	public VElement getMessageVector(final JDFMessage.EnumFamily family)
	{
		return getMessageVector(family, null);
	}

	/**
	 * get a vector of all messages in a JMF from a JDFDoc
	 *
	 * @param doc the JDFDoc to search - only valid for root JMF
	 * @param family requested message family
	 * @param typ requested message type
	 * @return VElement all message elements, null if no match found
	 *
	 */
	public static VElement getMessageVector(final JDFDoc doc, final JDFMessage.EnumFamily family, final JDFMessage.EnumType typ)
	{
		if (doc == null)
		{
			return null;
		}
		final JDFJMF jmf = doc.getJMFRoot();
		if (jmf == null)
		{
			return null;
		}

		final VElement vM = jmf.getMessageVector(family, typ);
		return vM.size() == 0 ? null : vM;
	}

	/**
	 * get a vector of all messages in this JMF
	 *
	 * @param family requested message family
	 * @param typ requested message type
	 * @return VElement all message elements
	 *
	 */
	public VElement getMessageVector(final JDFMessage.EnumFamily family, final JDFMessage.EnumType typ)
	{
		final String sFamily = (family != null) ? family.getName() : null;
		final JDFAttributeMap typMap = typ == null ? null : new JDFAttributeMap(AttributeName.TYPE, typ.getName());
		final VElement vM = getChildrenByTagName(sFamily, null, typMap, true, true, 0);

		if (family == null) // only needed if call was generic
		{
			// do reverse iteration because erase invalidates
			for (int i = vM.size() - 1; i >= 0; i--)
			{
				if (!(vM.elementAt(i) instanceof JDFMessage))
				{
					vM.removeElementAt(i);
				}
			}
		}
		return vM;
	}

	/**
	 * Get the 0 acknowledge,
	 *
	 * @return JDFAcknowledge: the message element
	 * @deprecated use getMessageElement
	 */
	@Deprecated
	public JDFAcknowledge getAcknowledge()
	{
		return getAcknowledge(0, false);
	}

	/**
	 *
	 * @param i
	 * @param bCreate
	 * @return
	 * @deprecated use getMessageElement or appendMessageElement
	 */
	@Deprecated
	public JDFAcknowledge getAcknowledge(final int i, final boolean bCreate)
	{
		if (bCreate)
		{
			return (JDFAcknowledge) getCreateMessageElement(JDFMessage.EnumFamily.Acknowledge, null, i);
		}
		return (JDFAcknowledge) getMessageElement(JDFMessage.EnumFamily.Acknowledge, null, i);
	}

	/**
	 * Append a Command
	 *
	 * @param typ the type attribute of the appended message
	 * @return JDFQuery the newly created message element
	 */
	public JDFCommand appendCommand(final JDFMessage.EnumType typ)
	{
		return (JDFCommand) appendMessageElement(JDFMessage.EnumFamily.Command, typ);
	}

	/**
	 * Append a Command
	 *
	 * @param typ the type attribute of the appended message
	 * @return JDFQuery the newly created message element
	 */
	public JDFRegistration appendRegistration(final JDFMessage.EnumType typ)
	{
		return (JDFRegistration) appendMessageElement(JDFMessage.EnumFamily.Registration, typ);
	}

	/**
	 * Append a query
	 *
	 * @param typ the type attribute of the appended message
	 * @return JDFQuery: the newly created message element
	 */
	public JDFQuery appendQuery(final JDFMessage.EnumType typ)
	{
		return (JDFQuery) appendMessageElement(JDFMessage.EnumFamily.Query, typ);

	}

	/**
	 * Append a Signal
	 *
	 * @param typ the type attribute of the appended message
	 * @return JDFSignal: the newly created message element
	 */
	public JDFSignal appendSignal(final JDFMessage.EnumType typ)
	{
		return (JDFSignal) appendMessageElement(JDFMessage.EnumFamily.Signal, typ);

	}

	/**
	 * Append a Response
	 *
	 * @param typ the type attribute of the appended message
	 * @return JDFResponse the newly created message element
	 */
	public JDFResponse appendResponse(final JDFMessage.EnumType typ)
	{
		return (JDFResponse) appendMessageElement(JDFMessage.EnumFamily.Response, typ);

	}

	/**
	 * Append an Acknowledge
	 *
	 * @param typ the type attribute of the appended message
	 * @return JDFAcknowledge the newly created message element
	 */
	public JDFAcknowledge appendAcknowledge(final JDFMessage.EnumType typ)
	{
		return (JDFAcknowledge) appendMessageElement(JDFMessage.EnumFamily.Acknowledge, typ);

	}

	/**
	 * @return the theSenderID which is used as default when initializing new messages
	 */
	public static String getTheSenderID()
	{
		return theSenderID;
	}

	/**
	 * set the default senderID that is used to generate jmf messages
	 *
	 * @param _theSenderID the theSenderID to set
	 */
	public static void setTheSenderID(final String _theSenderID)
	{
		JDFJMF.theSenderID = _theSenderID;
	}

	/**
	 * create a new response for all messages of this if the message is any message except response correctly fills refId, type etc.
	 *
	 * @return the newly created JMF with multiple responses
	 */
	public JDFJMF createResponse()
	{
		final VElement v = getMessageVector(null, null);
		final JDFJMF jmf = new JDFDoc(ElementName.JMF).getJMFRoot();
		for (int i = 0; i < v.size(); i++)
		{
			final JDFMessage m = (JDFMessage) v.elementAt(i);
			final EnumFamily family = m.getFamily();
			if (family != null && EnumFamily.Response != family)
			{
				if (!m.hasAttribute(AttributeName.ID))
				{
					// crappy requests...
					m.appendAnchor(null);
				}
				final JDFResponse r = jmf.appendResponse();
				r.setQuery(m);
			}
		}
		return jmf;
	}

	/**
	 * collect ICSVersions from all message children also removes duplicate entries from this and remove ""
	 *
	 * @return VString of all ICS versions
	 */
	public VString collectICSVersions()
	{
		final VElement v = getMessageVector(null, null);
		final VString vICS = getICSVersions();
		for (int i = 0; i < v.size(); i++)
		{
			final JDFMessage m = (JDFMessage) v.elementAt(i);
			if (m.hasAttribute(AttributeName.ICSVERSIONS))
			{
				vICS.addAll(m.getICSVersions());
			}
		}
		vICS.unify();
		setICSVersions(vICS.size() == 0 ? null : vICS);
		return vICS;
	}

	/**
	 * convert all responses that match the query q to signals
	 *
	 * @param q the query to convert
	 *
	 */
	public void convertResponses(final JDFQuery q)
	{
		final EnumType t = q == null ? null : q.getEnumType();
		final VElement v = getMessageVector(EnumFamily.Response, t);
		final String queryID = q == null ? null : q.getID();
		for (int i = 0; i < v.size(); i++)
		{
			final JDFResponse r = (JDFResponse) v.elementAt(i);
			if (queryID == null || queryID.equals(r.getrefID()))
			{
				final JDFSignal s = appendSignal();
				moveElement(s, r); // retain ordering
				s.convertResponse(r, q);
				r.deleteNode();
			}
		}
	}

	/**
	 * get the @URL of this message if it is either a submitQueueEntry, a returnQueuentry or a resubmitqueueentry
	 *
	 * @return the String
	 */
	public String getSubmissionURL()
	{
		JDFCommand cSubmit = (JDFCommand) getMessageElement(EnumFamily.Command, EnumType.SubmitQueueEntry, 0);
		if (cSubmit != null)
		{
			final JDFQueueSubmissionParams qsp = cSubmit.getQueueSubmissionParams(0);
			return qsp == null ? null : isWildCard(qsp.getURL()) ? null : qsp.getURL();
		}
		cSubmit = (JDFCommand) getMessageElement(EnumFamily.Command, EnumType.ResubmitQueueEntry, 0);
		if (cSubmit != null)
		{
			final JDFResubmissionParams rsp = cSubmit.getResubmissionParams(0);
			return rsp == null ? null : isWildCard(rsp.getURL()) ? null : rsp.getURL();
		}
		cSubmit = (JDFCommand) getMessageElement(EnumFamily.Command, EnumType.ReturnQueueEntry, 0);
		if (cSubmit != null)
		{
			final JDFReturnQueueEntryParams rsp = cSubmit.getReturnQueueEntryParams(0);
			return rsp == null ? null : isWildCard(rsp.getURL()) ? null : rsp.getURL();
		}
		return null;

	}

	/**
	 * get element Acknowledge
	 *
	 * @param refID refID of the response
	 * @return JDFResponse the element
	 *
	 */
	public JDFAcknowledge getAcknowledge(final String refID)
	{
		return (JDFAcknowledge) getChildWithAttribute(ElementName.ACKNOWLEDGE, AttributeName.REFID, null, refID, 0, true);
	}

	/**
	 * (27) get element Response
	 *
	 * @param refID refID of the response
	 * @return JDFResponse the element
	 *
	 */
	public JDFResponse getResponse(final String refID)
	{
		return (JDFResponse) getChildWithAttribute(ElementName.RESPONSE, AttributeName.REFID, null, refID, 0, true);
	}

}
