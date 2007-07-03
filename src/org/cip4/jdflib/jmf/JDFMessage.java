/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFDeviceList;
import org.cip4.jdflib.resource.JDFQueueEntryDefList;
import org.cip4.jdflib.resource.process.JDFNotificationFilter;

public class JDFMessage extends JDFAutoMessage
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFMessage
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFMessage(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFMessage
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFMessage(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFMessage
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFMessage(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[4];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ID, 0x22222222, AttributeInfo.EnumAttributeType.ID, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.TIME, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.TYPE, 0x22222222, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.XSITYPE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }

    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }

    public static class EnumFamily extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        private EnumFamily(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumFamily getEnum(String enumName)
        {
            return (EnumFamily) getEnum(EnumFamily.class, enumName);
        }

        public static EnumFamily getEnum(int enumValue)
        {
            return (EnumFamily) getEnum(EnumFamily.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumFamily.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumFamily.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumFamily.class);
        }

        public static final EnumFamily Query = new EnumFamily(ElementName.QUERY);
        public static final EnumFamily Signal = new EnumFamily(ElementName.SIGNAL);
        public static final EnumFamily Command = new EnumFamily(ElementName.COMMAND);
        public static final EnumFamily Response = new EnumFamily(ElementName.RESPONSE);
        public static final EnumFamily Acknowledge = new EnumFamily(ElementName.ACKNOWLEDGE);
        public static final EnumFamily Registration = new EnumFamily(ElementName.REGISTRATION);
    }

    public static class EnumType extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        private EnumType(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumType getEnum(String enumName)
        {
            return (EnumType) getEnum(EnumType.class, enumName);
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

        public static final EnumType AbortQueueEntry = new EnumType("AbortQueueEntry");
        public static final EnumType CloseQueue = new EnumType("CloseQueue");
        public static final EnumType FlushQueue = new EnumType("FlushQueue");
        public static final EnumType FlushResources = new EnumType("FlushResources");
        public static final EnumType Events = new EnumType("Events");
        public static final EnumType ForceGang = new EnumType("ForceGang");
        public static final EnumType GangStatus = new EnumType("GangStatus");
        public static final EnumType HoldQueue = new EnumType("HoldQueue");
        public static final EnumType HoldQueueEntry = new EnumType("HoldQueueEntry");
        public static final EnumType KnownControllers = new EnumType("KnownControllers");
        public static final EnumType KnownDevices = new EnumType("KnownDevices");
        public static final EnumType KnownJDFServices = new EnumType("KnownJDFServices");
        public static final EnumType KnownMessages = new EnumType("KnownMessages");
        public static final EnumType ModifyNode = new EnumType("ModifyNode");
        public static final EnumType NewJDF = new EnumType("NewJDF");
        public static final EnumType NodeInfo = new EnumType("NodeInfo");
        public static final EnumType Notification = new EnumType("Notification");
        public static final EnumType Occupation = new EnumType("Occupation");
        public static final EnumType OpenQueue = new EnumType("OpenQueue");
        public static final EnumType PipeClose = new EnumType("PipeClose");
        public static final EnumType PipePull = new EnumType("PipePull");
        public static final EnumType PipePush = new EnumType("PipePush");
        public static final EnumType PipePause = new EnumType("PipePause");
        public static final EnumType QueueEntryStatus = new EnumType("QueueEntryStatus");
        public static final EnumType QueueStatus = new EnumType("QueueStatus");
        public static final EnumType RequestQueueEntry = new EnumType("RequestQueueEntry");
        public static final EnumType RemoveQueueEntry = new EnumType("RemoveQueueEntry");
        public static final EnumType RepeatMessages = new EnumType("RepeatMessages");
        public static final EnumType Resource = new EnumType("Resource");
        public static final EnumType ResourcePull = new EnumType("ResourcePull");
        public static final EnumType ResumeQueue = new EnumType("ResumeQueue");
        public static final EnumType ResumeQueueEntry = new EnumType("ResumeQueueEntry");
        public static final EnumType ResubmitQueueEntry = new EnumType("ResubmitQueueEntry");
        public static final EnumType ReturnQueueEntry = new EnumType("ReturnQueueEntry");
        public static final EnumType SetQueueEntryPosition = new EnumType("SetQueueEntryPosition");
        public static final EnumType SetQueueEntryPriority = new EnumType("SetQueueEntryPriority");
        public static final EnumType ShutDown = new EnumType("ShutDown");
        public static final EnumType Status = new EnumType("Status");
        public static final EnumType StopPersistentChannel = new EnumType("StopPersistentChannel");
        public static final EnumType SubmissionMethods = new EnumType("SubmissionMethods");
        public static final EnumType SubmitQueueEntry = new EnumType("SubmitQueueEntry");
        public static final EnumType SuspendQueueEntry = new EnumType("SuspendQueueEntry");
        public static final EnumType Track = new EnumType("Track");
        public static final EnumType UpdateJDF = new EnumType("UpdateJDF");
        public static final EnumType WakeUp = new EnumType("WakeUp");
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
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
    public boolean isMessageElement()
    {
        return true;
    }

    /**
     * init
     *
     * @return boolean
     */
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
    public String getIDPrefix()
    {
        return "m";
    }

    /**
     * getFamily: get the enum family type
     *
     * @return EnumFamily - type Family_Unknown, Family_Query, Family_Signal,
     *                      Family_Command, Family_Response or Family_Acknowledge
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
    public String getType()
    {
        return getAttribute(AttributeName.TYPE, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * Set attribute <code>Type</code> and <code>xsi:type</code>
     *
     * @param typ the type
     */
    public void setType(String typ)
    {
        removeAttribute("type",AttributeName.XSIURI);
        setAttribute(AttributeName.TYPE, typ, null);
        if(xmlnsPrefix(typ)==null) {
			setXSIType(getLocalName()+typ);
		}
    }


    /**
     * SetQuery - sets the initiating query, command or registration to q
     *
     * @param q
     */
    public void setQuery(JDFMessage q)
    {
        EnumFamily f=getFamily();
        if(f==null || f.equals(EnumFamily.Query)||
                f.equals(EnumFamily.Command)||
                f.equals(EnumFamily.Registration))
        {
            String message = f == null
                                ? "JDFMessage.setQuery: illegal family type "
                                : "JDFMessage.setQuery: illegal family type " + f.getName();
            throw new JDFException(message);
        }
        setAttribute(AttributeName.REFID, q.getID(), null);
        setType(q.getType());
    }

    /**
     * isValid
     * @deprecated
     * @return boolean
     */
    public boolean isValid()
    {
        return isValid(KElement.EnumValidationLevel.Complete);
    }


    /**
     * Enumeration string for enum value
     * @param EnumType value the enumeration to translate
     * @return KString the string representation of the enumeration
     */
    public static String typeString(EnumType value)
    {
        return value.toString();
    }

    /**
     * @deprecated use EnumType to get strings
     * @return
     */
    public static String typeString()
    {
        final String enums =
            "Unknown,Events,KnownControllers,KnownDevices,KnownJDFServices,KnownMessages,"
            + "RepeatMessages,StopPersistentChannel,Occupation,Resource,"
            + "Status,Track,PipeClose,PipePull,PipePush,PipePause,AbortQueueEntry,"
            + "HoldQueueEntry,removeQueueEntry,ResubmitQueueEntry,"
            + "ResumeQueueEntry,SetQueueEntryPosition,SetQueueEntryPriority,"
            + "SubmitQueueEntry,CloseQueue,FlushQueue,HoldQueue,OpenQueue,QueueEntryStatus,QueueStatus,"
            + "ResumeQueue,SubmissionMethods";
        return enums;
    }

    /**
     * Set attribute Type
     * @param value the value to set the attribute to
     * @deprecated use setType()
     */
    public void setEnumType(EnumType value)
    {
        setType(value);
    }

    /**
     * Set attribute Type
     * @param value the value to set the attribute to
     */
    public void setType(EnumType value)
    {
        final String typeName = value.getName();
        setType(typeName);
    }

    /**
     * create a new response for this if this is any message except response
     * correctly fills refId, type etc.
      * @return the newly created message
     */    
    public JDFJMF createResponse()
    {
        JDFMessage.EnumFamily family=getFamily();

        if (family==null)
            throw new JDFException ("createResponse: creating resp from undefined message family");

        JDFJMF jmf=JDFJMF.createJMF(EnumFamily.Response, null);
        jmf.getResponse(0).setQuery(this);
        return jmf;
    }

    /**
     * fixVersion()<br>
     * adds xsi:Type if it doesn't exist
     * @param version
     */
    public boolean fixVersion(EnumVersion version)
    {
        if(version!=null) {
			version.getClass(); // dummy to fool compiler
		}
        if(hasAttribute(AttributeName.TYPE)&&!hasAttribute(AttributeName.XSITYPE,AttributeName.XSIURI,false))
        {
            setAttribute(AttributeName.XSITYPE,getLocalName()+getType(),AttributeName.XSIURI);
        }
        return true;
    }

    /**
     * checks whether the type of messageElement is valid for this message
     * @param elementName the name of the element to be tested
     * @return boolean: true if valid; always true if not in JDFNameSpace
     */
    public boolean isValidMessageElement(String elementName, int iSkip)
    {
        // this is not a standard jdf element, but rather an extension
        // we assume that anyone calling the typesafe methods knows what he is doing
        if (!isInJDFNameSpaceStatic(this))
        {
            return true;
        }

        // this is not a standard jdf type, but rather an extension type
        // we assume that anyone calling the typesafe methods knows what he is doing
        String sTyp=getType();
        if(KElement.xmlnsPrefix(sTyp)!=null)
        {
            return true;
        }

        // it aint even valid for any family
        final String[] familyTypeObj = familyTypeObj();
        boolean isFamilyTypeString = (familyTypeObj==null) ? false : ArrayUtils.contains(familyTypeObj,elementName);
        if (!isFamilyTypeString)
        {
            return false;
        }

        EnumType typ = getEnumType();
        // the type seams to be unknown, we certainly don't know what to dump in
        if (typ==null)
        {
            return false;
        }

        Vector validList = getValidTypeVector(elementName, iSkip);
        return validList.contains(typ);
     }

    /**
     * returns a vector of valid messageElement types for this element
     * @param elementName the name of the element to be tested
     * @return vector of valid EnumTypes; empty if all are invalid
     * @default getValidTypeVector(elementName, 0)
     */
    private Vector getValidTypeVector(String elementName, int iSkip)
    {
        //  typedef std::vector<EnumType> vEnumType;
        Vector validList = new Vector();

        // Commands
        if (elementName.equals(ElementName.FLUSHQUEUEPARAMS))
        {
            if (iSkip == 0)
            { // validation for cardinality '?' or '-', when no more than 1 element are allowed
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
        else if (elementName.equals(ElementName.TRACKRESULT))
        {
            validList.addElement(EnumType.Track);

        }
        else if (elementName.equals(ElementName.UPDATEJDFCMDPARAMS))
        {
            if(iSkip==0)
            {
                validList.addElement(EnumType.UpdateJDF);
            }
        }
        else if (elementName.equals(ElementName.MODIFYNODECMDPARAMS))
        {
            if(iSkip==0)
            {
                validList.addElement(EnumType.ModifyNode);
            }
        }



        else if (EnumFamily.getEnum(elementName)!=null)
        {
            validList.addElement(EnumType.RepeatMessages);
        }

        return validList;
    }

    /**
     * Typesafe enumerated attribute Type
     * @return EnumType: the enumeration value of the attribute
     */
    public EnumType getEnumType()
    {
        return EnumType.getEnum(getAttribute(AttributeName.TYPE, null, null));
    }

    /**
     * get a vector of valid object names for this family type
     * @return String comma separated valid object types for this family type
     */
    private String[] familyTypeObj()
    {
        EnumFamily family = getFamily();
        if(family==null) {
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

        if (family.equals(EnumFamily.Response)|| family.equals(EnumFamily.Acknowledge))
        {
            return responseTypeObjString;
        }

        if (family.equals(EnumFamily.Signal))
        {
            if(signalTypeObjString==null)
            {
                VString v=new VString(queryTypeObjString);
                v.addAll(responseTypeObjString);
                v.unify();
                signalTypeObjString=(String[])v.toArray(new String[v.size()]);
            }
            return signalTypeObjString;
        }

        return null;
    }

    /**
     * Enumeration strings for list of CommandTypeObj
     * @returnString comma separated list of enumerated string values
     */
    private static String[] commandTypeObjString =
    {
        ElementName.FLUSHQUEUEPARAMS,
        ElementName.FLUSHRESOURCEPARAMS,
        ElementName.MODIFYNODECMDPARAMS,
        ElementName.NEWJDFCMDPARAMS,
        ElementName.NODEINFOCMDPARAMS,
        ElementName.PIPEPARAMS,
        ElementName.QUEUEENTRYDEF,
        ElementName.QUEUEENTRYPRIPARAMS,
        ElementName.QUEUEENTRYPOSPARAMS,
        ElementName.QUEUEFILTER,
        ElementName.QUEUESUBMISSIONPARAMS,
        ElementName.REQUESTQUEUEENTRYPARAMS,
        ElementName.RESOURCECMDPARAMS,
        ElementName.RESOURCEPULLPARAMS,
        ElementName.RESUBMISSIONPARAMS,
        ElementName.RETURNQUEUEENTRYPARAMS,
        ElementName.SHUTDOWNCMDPARAMS,
        ElementName.STOPPERSCHPARAMS,
        ElementName.UPDATEJDFCMDPARAMS,
        ElementName.WAKEUPCMDPARAMS
    };

    /**
     * Enumeration strings for list of CommandTypeObj
     * @returnString comma separated list of enumerated string values
     */
    private static String[] registrationTypeObjString=
    {
        ElementName.PIPEPARAMS,
        ElementName.RESOURCECMDPARAMS,
        ElementName.RESOURCEPULLPARAMS,
    };

    /**
     * Enumeration strings for list of QueryTypeObj
     * @returnString comma separated list of enumerated string values
     */
    private static String[] queryTypeObjString=
    {
        ElementName.DEVICEFILTER,
        ElementName.EMPLOYEEDEF,
        ElementName.KNOWNMSGQUPARAMS,
        ElementName.MSGFILTER,
        ElementName.MODIFYNODECMDPARAMS,
        ElementName.NEWJDFQUPARAMS,
        ElementName.NODEINFOQUPARAMS,
        ElementName.NOTIFICATIONFILTER,
        ElementName.QUEUEENTRYDEFLIST,
        ElementName.QUEUEFILTER,
        ElementName.RESOURCEQUPARAMS,
        ElementName.STATUSQUPARAMS,
        ElementName.TRACKFILTER,
        ElementName.UPDATEJDFCMDPARAMS
    };

    /**
     * Enumeration strings for list of ResponseTypeObj
     * @returnString comma separated list of enumerated string values
     */
    private static String[]responseTypeObjString=
    {
        ElementName.DEVICELIST,
        ElementName.DEVICEINFO,
        ElementName.FLUSHEDRESOURCES,
        ElementName.IDINFO,
        ElementName.JDFCONTROLLER,
        ElementName.JDFSERVICE,
        ElementName.JOBPHASE,
        ElementName.MESSAGESERVICE,
        ElementName.NODEINFORESP,
        ElementName.NOTIFICATIONDEF,
        ElementName.OCCUPATION,
        ElementName.QUEUE,
        ElementName.QUEUEENTRY,
        ElementName.RESOURCEINFO,
        ElementName.SUBMISSIONMETHODS,
        ElementName.TRACKRESULT,
        ElementName.COMMAND,
        ElementName.QUERY,
        ElementName.ACKNOWLEDGE,
        ElementName.RESPONSE,
        ElementName.SIGNAL,
        ElementName.REGISTRATION
    };
    private static String[] signalTypeObjString=null;

    /**
     * append an element<br>
     * throws an JDFException, if <code>elementName</code> is not legal
     * @param elementName  name of the element to append
     * @param nameSpaceURI namespace URI of the element to append
     * @return the appended element
     */
    public KElement appendValidElement(String elementName, String nameSpaceURI)
    {
        int iSkip = numChildElements(elementName, nameSpaceURI);
        if (!isValidMessageElement(elementName, iSkip))
        {
            throw new JDFException("AppendValidElement: illegal element :" + elementName);
        }
        return appendElement(elementName, nameSpaceURI);
    }

    /**
     * @deprecated use appendValidElement(elementName, null);
     * @param elementName
     * @return
     */
    public KElement appendValidElement(String elementName)
    {
        return appendValidElement(elementName, null);
    }

    /**
     * get a (valid) element<br>
     * throws <code>JDFException</code> if the element is not valid
     * @param nodeName     name of the element to get
     * @param nameSpaceURI namespace URI of the element to get
     * @param iSkip        number of elements to skip
     * @return             the element
     */
    public KElement getValidElement(
            String nodeName,
            String nameSpaceURI,
            int iSkip)
    {
        if (!isValidMessageElement(nodeName, iSkip))
        {
            throw new JDFException("getValidElement: illegal element :" + nodeName);
        }

        return getElement_JDFElement(nodeName, nameSpaceURI, iSkip);
    }

    //////////////////////////////////////////////////////////////////////
    /**
     * get a (valid) element, create if it doesn't exist<br>
     * throws <code>JDFException</code> if the element is not valid
     * @param nodeName     name of the element to get
     * @param nameSpaceURI namespace URI of the element to get
     * @param iSkip        number of elements to skip
     * @return             KElement
     */
    public KElement getCreateValidElement(
            String nodeName,
            String nameSpaceURI,
            int iSkip)
    {
        if (!isValidMessageElement(nodeName, iSkip))
        {
            throw new JDFException("getCreateValidElement: illegal element :" + nodeName);
        }

        return getCreateElement_KElement(nodeName, nameSpaceURI, iSkip);
    }

    /* ******************************************************
     // Element getter / Setter
      **************************************************************** */

    /**
     * get device, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFDevice
     */
    public JDFDevice getCreateDevice(int iSkip)
    {
        JDFDevice e = (JDFDevice) getCreateValidElement(ElementName.DEVICE, JDFConstants.EMPTYSTRING, iSkip);

        return e;
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * append element <code>Device</code>
     * @return JDFDevice: the element
     */
    public JDFDevice appendDevice()
    {
        JDFDevice e = (JDFDevice) appendValidElement(ElementName.DEVICE, null);

        return e;
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get the iSkip'th device
     * @param iSkip number of elements to skip
     * @return JDFDevice: the element
     */
    public JDFDevice getDevice(int iSkip)
    {
        return (JDFDevice) getValidElement(ElementName.DEVICE, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>DeviceFilter</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFDeviceFilter: the element
     */
    public JDFDeviceFilter getCreateDeviceFilter(int iSkip)
    {
        JDFDeviceFilter e =
            (JDFDeviceFilter) getCreateValidElement(ElementName.DEVICEFILTER, JDFConstants.EMPTYSTRING, iSkip);

        return e;
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * append element <code>DeviceFilter</code>
     * @return JDFDeviceFilter: the element
     */
    public JDFDeviceFilter appendDeviceFilter()
    {
        return (JDFDeviceFilter) appendValidElement(ElementName.DEVICEFILTER, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>DeviceFilter</code>
     * @param iSkip number of elements to skip
     * @return JDFDeviceFilter: the element
     */
    public JDFDeviceFilter getDeviceFilter(int iSkip)
    {
        return (JDFDeviceFilter) getValidElement(ElementName.DEVICEFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    /**
     * get element <code>DeviceInfo</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFDeviceInfo: the element
     */
    public JDFDeviceInfo getCreateDeviceInfo(int iSkip)
    {
        return (JDFDeviceInfo)
        getCreateValidElement(ElementName.DEVICEINFO, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * append element <code>DeviceInfo</code>
     * @return JDFDeviceInfo: the element
     */
    public JDFDeviceInfo appendDeviceInfo()
    {
        return (JDFDeviceInfo) appendValidElement(ElementName.DEVICEINFO, null);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>DeviceInfo</code>
     * @param iSkip number of elements to skip
     * @return JDFDeviceInfo: the elment
     */
    public JDFDeviceInfo getDeviceInfo(int iSkip)
    {
        return (JDFDeviceInfo) getValidElement(ElementName.DEVICEINFO, JDFConstants.EMPTYSTRING, iSkip);
    }

    //////////////////////////////////////////////////////////////////////

    /** get Element <code>DeviceList</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFDeviceList: the element
     */
    public JDFDeviceList getCreateDeviceList(int iSkip)
    {
        return (JDFDeviceList)
        getCreateValidElement(ElementName.DEVICELIST, JDFConstants.EMPTYSTRING, iSkip);
    }

    /////////////////////////////////////////////////////////////////////

    /**
     * Append element <code>DeviceList</code>
     * @return JDFDeviceList the element
     */
    public JDFDeviceList appendDeviceList()
    {
        return (JDFDeviceList) appendValidElement(ElementName.DEVICELIST, null);
    }

    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th DeviceList element
     * @param iSkip number of elements to skip
     * @return JDFDeviceList: the element
     */
    public JDFDeviceList getDeviceList(int iSkip)
    {
        return (JDFDeviceList) getValidElement(ElementName.DEVICELIST, JDFConstants.EMPTYSTRING, iSkip);
    }

    //////////////////////////////////////////////////////////////////////

    /** get element <code>EmployeeDef</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFEmployeeDef: the element
     */
    public JDFEmployeeDef getCreateEmployeeDef(int iSkip)
    {
        return (JDFEmployeeDef)
        getCreateValidElement(ElementName.EMPLOYEEDEF, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * Append element <code>EmployeeDef</code>
     * @return JDFEmployeeDef: the element
     */
    public JDFEmployeeDef appendEmployeeDef()
    {
        return (JDFEmployeeDef) appendValidElement(ElementName.EMPLOYEEDEF, null);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>EmployeeDef</code>
     * @param iSkip number of elements to skip
     * @return JDFEmployeeDef: the element
     */
    public JDFEmployeeDef getEmployeeDef(int iSkip)
    {
        return (JDFEmployeeDef) getValidElement(ElementName.EMPLOYEEDEF, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    /** get Element <code>JDFController</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFJDFController: the element
     */
    public JDFJDFController getCreateJDFController(int iSkip)
    {
        return (JDFJDFController)
        getCreateValidElement(ElementName.JDFCONTROLLER, JDFConstants.EMPTYSTRING, iSkip);
    }

    /////////////////////////////////////////////////////////////////////

    /**
     * Append element <code>JDFController</code>
     * @return JDFJDFController: the element
     */
    public JDFJDFController appendJDFController()
    {
        return (JDFJDFController) appendValidElement(ElementName.JDFCONTROLLER, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th JDFController element
     * @param iSkip number of elemts to skip
     * @return JDFJDFController: the element
     */
    public JDFJDFController getJDFController(int iSkip)
    {
        return (JDFJDFController) getValidElement(ElementName.JDFCONTROLLER, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    /** get iSkip'th element <code>JDFService</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFJDFService: the element
     */
    public JDFJDFService getCreateJDFService(int iSkip)
    {
        return (JDFJDFService)
        getCreateValidElement(ElementName.JDFSERVICE, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * Append element <code>JDFService</code>
     * @return JDFJDFService the element
     */
    public JDFJDFService appendJDFService()
    {
        return (JDFJDFService) appendValidElement(ElementName.JDFSERVICE, null);
    }

    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th <code>JDFService</code> element
     * @param iSkip number of elements to skip
     * @return JDFJDFService: the element
     */
    public JDFJDFService getJDFService(int iSkip)
    {
        return (JDFJDFService) getValidElement(ElementName.JDFSERVICE, JDFConstants.EMPTYSTRING, iSkip);
    }

    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    /** get Element <code>JobPhase</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFJobPhase: the element
     */
    public JDFJobPhase getCreateJobPhase(int iSkip)
    {
        return (JDFJobPhase) getValidElement(ElementName.JOBPHASE, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>JobPhase</code>
     * @return JDFJobPhase: the element
     */
    public JDFJobPhase appendJobPhase()
    {
        return  (JDFJobPhase) appendValidElement(ElementName.JOBPHASE, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th <code>JobPhase</code> element
     * @param iSkip elements to skip
     * @return JDFJobPhase: the element
     */
    public JDFJobPhase getJobPhase(int iSkip)
    {
        return (JDFJobPhase) getValidElement(ElementName.JOBPHASE, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    /** get iSkip'th <code>KnownMsgQuParams</code> element, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFKnownMsgQuParams the element
     */
    public JDFKnownMsgQuParams getCreateKnownMsgQuParams(int iSkip)
    {
        return (JDFKnownMsgQuParams)
        getCreateValidElement(ElementName.KNOWNMSGQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>KnownMsgQuParams</code>
     * @return JDFKnownMsgQuParams: the element
     */
    public JDFKnownMsgQuParams appendKnownMsgQuParams()
    {
        return (JDFKnownMsgQuParams) appendValidElement(ElementName.KNOWNMSGQUPARAMS, null);
    }

    /////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th <code>KnownMsgQuParams</code>
     * @param iSkip number of elements to skip
     * @return JDFKnownMsgQuParams: the element
     */
    public JDFKnownMsgQuParams getKnownMsgQuParams(int iSkip)
    {
        return (JDFKnownMsgQuParams) getValidElement(ElementName.KNOWNMSGQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }

    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /** get iSkip'th element <code>MessageService</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFMessageService: the element
     */
    public JDFMessageService getCreateMessageService(int iSkip)
    {
        return (JDFMessageService)
        getCreateValidElement(ElementName.MESSAGESERVICE, JDFConstants.EMPTYSTRING, iSkip);
    }

    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>MessageService</code>
     */
    public JDFMessageService appendMessageService()
    {
        return  (JDFMessageService) appendValidElement(ElementName.MESSAGESERVICE, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>MessageService</code>
     * @param iSkip number of elements to skip
     * @return JDFMessageService: the element
     */
    public JDFMessageService getMessageService(int iSkip)
    {
        return (JDFMessageService) getValidElement(ElementName.MESSAGESERVICE, JDFConstants.EMPTYSTRING, iSkip);
    }

    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /**
     * get element <code>MsgFilter</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFMsgFilter: the element
     */
    public JDFMsgFilter getCreateMsgFilter(int iSkip)
    {
        return  (JDFMsgFilter) getCreateValidElement(ElementName.MSGFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }

    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>MsgFilter</code>
     * @return JDFMsgFilter: the element
     */
    public JDFMsgFilter appendMsgFilter()
    {
        return  (JDFMsgFilter) appendValidElement(ElementName.MSGFILTER, null);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>MsgFilter</code>
     * @param iSkip number of elements to skip
     * @return JDFMsgFilter: the element
     */
    public JDFMsgFilter getMsgFilter(int iSkip)
    {
        return (JDFMsgFilter) getValidElement(ElementName.MSGFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /** get element <code>NotificationDef</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFNotificationDef: the element
     */
    public JDFNotificationDef getCreateNotificationDef(int iSkip)
    {
        return  (JDFNotificationDef)
        getCreateValidElement(ElementName.NOTIFICATIONDEF, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>NotificationDef</code>
     * @return JDFNotificationDef: the element
     */
    public JDFNotificationDef appendNotificationDef()
    {
        return (JDFNotificationDef) appendValidElement(ElementName.NOTIFICATIONDEF, null);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>NotificationDef</code>
     * @param iSkip number of elements to skip
     * @return JDFNotificationDef: the element
     */
    public JDFNotificationDef getNotificationDef(int iSkip)
    {
        return (JDFNotificationDef) getValidElement(ElementName.NOTIFICATIONDEF, JDFConstants.EMPTYSTRING, iSkip);
    }

    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /**
     * get element <code>NotificationFilter</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFNotificationFilter: the element
     */
    public JDFNotificationFilter getCreateNotificationFilter(int iSkip)
    {
        return  (JDFNotificationFilter)
        getCreateValidElement(ElementName.NOTIFICATIONFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }

    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>NotificationFilter</code>
     * @return JDFNotificationFilter: the element
     */
    public JDFNotificationFilter appendNotificationFilter()
    {
        return  (JDFNotificationFilter) appendValidElement(ElementName.NOTIFICATIONFILTER, null);
    }

    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th <code>NotificationFilter</code> element
     * @param iSkip number of elements to skip
     * @return JDFNotificationFilter: the element
     */
    public JDFNotificationFilter getNotificationFilter(int iSkip)
    {
        return (JDFNotificationFilter) getValidElement(ElementName.NOTIFICATIONFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th <code>Occupation</code> element
     * @param iSkip number of elements to skip
     * @return JDFOccupation: the element
     */
    public JDFOccupation getCreateOccupation(int iSkip)
    {
        return (JDFOccupation)
        getCreateValidElement(ElementName.OCCUPATION, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>Occupation</code>
     * @return JDFOccupation: the element
     */
    public JDFOccupation appendOccupation()
    {
        return (JDFOccupation) appendValidElement(ElementName.OCCUPATION, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>Occupation</code>
     * @param iSkip number of elements to skip
     * @return JDFOccupation: the element
     */
    public JDFOccupation getOccupation(int iSkip)
    {
        return (JDFOccupation) getValidElement(ElementName.OCCUPATION, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>PipeParams</code>
     * @param iSkip number of elements to skip
     * @return JDFPipeParams: the element
     */
    public JDFPipeParams getCreatePipeParams(int iSkip)
    {
        return  (JDFPipeParams)
        getCreateValidElement(ElementName.PIPEPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>PipeParams</code>
     * @return JDFPipeParams: the element
     */
    public JDFPipeParams appendPipeParams()
    {
        return  (JDFPipeParams) appendValidElement(ElementName.PIPEPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get element <code>PipeParams</code>
     * @return JDFPipeParams: the element
     */
    public JDFPipeParams getPipeParams()
    {
        return (JDFPipeParams) getValidElement(ElementName.PIPEPARAMS, null,0);
    }
    /**
     * get iSkip'th element <code>PipeParams</code>
     * @param iSkip number of elements to skip
     * @deprecated - use the 0 parameter version
     * @return JDFPipeParams: the element
     */
    public JDFPipeParams getPipeParams(int iSkip)
    {
        return (JDFPipeParams) getValidElement(ElementName.PIPEPARAMS, null, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /** get iSkip'th element <code>Queue</code>
     * @param iSkip number of elements to skip
     * @return JDFQueue: the element
     */
    public JDFQueue getCreateQueue(int iSkip)
    {
        return (JDFQueue) getCreateValidElement(ElementName.QUEUE, JDFConstants.EMPTYSTRING, iSkip);
    }

    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>Queue</code>
     * @return JDFQueue: the element
     */
    public JDFQueue appendQueue()
    {
        return (JDFQueue) appendValidElement(ElementName.QUEUE, null);
    }

    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>Queue</code>
     * @param iSkip number of elements to skip
     * @return JDFQueue: the element
     */
    public JDFQueue getQueue(int iSkip)
    {
        return (JDFQueue) getValidElement(ElementName.QUEUE, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>QueueEntry</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFQueueEntry: the element
     */
    public JDFQueueEntry getCreateQueueEntry(int iSkip)
    {
        return  (JDFQueueEntry)
        getCreateValidElement(ElementName.QUEUEENTRY, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * append element <code>QueueEntry</code>
     * @return JDFQueueEntry: the element
     */
    public JDFQueueEntry appendQueueEntry()
    {
        return  (JDFQueueEntry) appendValidElement(ElementName.QUEUEENTRY, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>QueueEntry</code>
     * @param iSkip number of elements to skip
     * @return JDFQueueEntry: the element
     */
    public JDFQueueEntry getQueueEntry(int iSkip)
    {
        return (JDFQueueEntry)
        getValidElement(ElementName.QUEUEENTRY, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>QueueEntryDef</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFQueueEntryDef: the element
     */
    public JDFQueueEntryDef getCreateQueueEntryDef(int iSkip)
    {
        return (JDFQueueEntryDef)
        getCreateValidElement(ElementName.QUEUEENTRYDEF, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>QueueEntryDef</code>
     * @return JDFQueueEntryDef: the element
     */
    public JDFQueueEntryDef appendQueueEntryDef()
    {
        return  (JDFQueueEntryDef) appendValidElement(ElementName.QUEUEENTRYDEF, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>QueueEntryDef</code>
     * @param iSkip number of elements to skip
     * @return JDFQueueEntryDef: the element
     */
    public JDFQueueEntryDef getQueueEntryDef(int iSkip)
    {
        return (JDFQueueEntryDef) getValidElement(ElementName.QUEUEENTRYDEF, JDFConstants.EMPTYSTRING, iSkip);
    }

    //////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element QueueEntryDefList, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFQueueEntryDefList: the element
     */
    public JDFQueueEntryDefList getCreateQueueEntryDefList(int iSkip)
    {
        return (JDFQueueEntryDefList)
        getCreateValidElement(ElementName.QUEUEENTRYDEFLIST, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>QueueEntryDefList</code>
     * @return JDFQueueEntryDefList: the element
     */
    public JDFQueueEntryDefList appendQueueEntryDefList()
    {
        return  (JDFQueueEntryDefList) appendValidElement(ElementName.QUEUEENTRYDEFLIST, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>QueueEntryDefList</code>
     * @param iSkip number of elements to skip
     * @return JDFQueueEntryDefList: the element
     */
    public JDFQueueEntryDefList getQueueEntryDefList(int iSkip)
    {
        return (JDFQueueEntryDefList)
        getValidElement(ElementName.QUEUEENTRYDEFLIST, JDFConstants.EMPTYSTRING, iSkip);

    }

    //////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>QueueEntryPriParams</code>
     * @param iSkip number of elements to skip
     * @return JDFQueueEntryPriParams: the element
     */
    public JDFQueueEntryPriParams getCreateQueueEntryPriParams(int iSkip)
    {
        return  (JDFQueueEntryPriParams)
        getCreateValidElement(ElementName.QUEUEENTRYPRIPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }

    /**
     * append element <code>QueueEntryPriParams</code>
     * @return JDFQueueEntryPriParams: the element
     */
    public JDFQueueEntryPriParams appendQueueEntryPriParams()
    {
        return  (JDFQueueEntryPriParams) appendValidElement(ElementName.QUEUEENTRYPRIPARAMS, null);
    }

    /**
     * get iSkip'th element <code>QueueEntryPriParams</code>
     * @param iSkip number of elements to skip
     * @return JDFQueueEntryPriParams: the element
     */
    public JDFQueueEntryPriParams getQueueEntryPriParams(int iSkip)
    {
        return (JDFQueueEntryPriParams)
        getValidElement(ElementName.QUEUEENTRYPRIPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element QueueEntryPosParams, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFQueueEntryPosParams: the element
     */
    public JDFQueueEntryPosParams getCreateQueueEntryPosParams(int iSkip)
    {
        return  (JDFQueueEntryPosParams)
        getCreateValidElement(ElementName.QUEUEENTRYPOSPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>QueueEntryPosParams</code>
     * @return JDFQueueEntryPosParams: the element
     */
    public JDFQueueEntryPosParams appendQueueEntryPosParams()
    {
        return  (JDFQueueEntryPosParams) appendValidElement(ElementName.QUEUEENTRYPOSPARAMS, null);
    }

    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>QeueEntryPosParams</code>
     * @param iSkip number of elements to skip
     * @return JDFQueueEntryPosParams: the element
     */
    public JDFQueueEntryPosParams getQueueEntryPosParams(int iSkip)
    {
        return (JDFQueueEntryPosParams)
        getValidElement(ElementName.QUEUEENTRYPOSPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /** get iSkip'th element <code>QueueSubmissionParams</code>
     * @param int iSkip number of elements to skip
     * @return JDFQueueSubmissionParams: the element
     */
    public JDFQueueSubmissionParams getCreateQueueSubmissionParams(int iSkip)
    {
        return  (JDFQueueSubmissionParams)
        getCreateValidElement(ElementName.QUEUESUBMISSIONPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>QueueSubmissionParams</code>
     * @return JDFQueueSubmissionParams: the element
     */
    public JDFQueueSubmissionParams appendQueueSubmissionParams()
    {
        return  (JDFQueueSubmissionParams) appendValidElement(ElementName.QUEUESUBMISSIONPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>QueueSubmissionParams</code>
     * @param iSkip number of elements to skip
     * @return JDFQueueSubmissionParams: the element
     */
    public JDFQueueSubmissionParams getQueueSubmissionParams(int iSkip)
    {
        return (JDFQueueSubmissionParams)
        getValidElement(ElementName.QUEUESUBMISSIONPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /** get iSkip'th element <code>ResourceCmdParams</code>
     * @param iSkip number of elements to skip
     * @return JDFResourceCmdParams: the element
     */
    public JDFResourceCmdParams getCreateResourceCmdParams(int iSkip)
    {
        return  (JDFResourceCmdParams)
        getCreateValidElement(ElementName.RESOURCECMDPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>ResourceCmdParams</code>
     * @return JDFResourceCmdParams: the element
     */
    public JDFResourceCmdParams appendResourceCmdParams()
    {
        return  (JDFResourceCmdParams) appendValidElement(ElementName.RESOURCECMDPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>ResourceCmdParams</code>
     * @param iSkip number of elements to skip
     * @return JDFResourceCmdParams: the element
     */
    public JDFResourceCmdParams getResourceCmdParams(int iSkip)
    {
        return (JDFResourceCmdParams) getValidElement(ElementName.RESOURCECMDPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>ResourceQuParams</code>
     * @param iSkip number of elements to skip
     * @return JDFResourceQuParams: the element
     */
    public JDFResourceQuParams getCreateResourceQuParams(int iSkip)
    {
        return (JDFResourceQuParams)
        getCreateValidElement(ElementName.RESOURCEQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>ResourceQuParams</code>
     * @return JDFResourceQuParams: the element
     */
    public JDFResourceQuParams appendResourceQuParams()
    {
        return (JDFResourceQuParams) appendValidElement(ElementName.RESOURCEQUPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>ResourceQuParams</code>
     * @param iSkip number of elements to skip
     * @return JDFResourceQuParams: the element
     */
    public JDFResourceQuParams getResourceQuParams(int iSkip)
    {
        return (JDFResourceQuParams) getValidElement(ElementName.RESOURCEQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>ResourceInfo</code>
     * @param iSkip number of elements to skip
     * @return JDFResourceInfo: the element
     */
    public JDFResourceInfo getCreateResourceInfo(int iSkip)
    {
        return (JDFResourceInfo)
        getCreateValidElement(ElementName.RESOURCEINFO, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>ResourceInfo</code>
     * @return JDFResourceInfo: the element
     */
    public JDFResourceInfo appendResourceInfo()
    {
        return  (JDFResourceInfo) appendValidElement(ElementName.RESOURCEINFO, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>ResourceInfo</code>
     * @param iSkip number of elements to skip
     * @return JDFResourceInfo: the element
     */
    public JDFResourceInfo getResourceInfo(int iSkip)
    {
        return (JDFResourceInfo) getValidElement(ElementName.RESOURCEINFO, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /** get iSkip'th element <code>StatusQuParams</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFStatusQuParams: the element
     */
    public JDFStatusQuParams getCreateStatusQuParams(int iSkip)
    {
        return  (JDFStatusQuParams)
        getCreateValidElement(ElementName.STATUSQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>StatusQuParams</code>
     * @return JDFStatusQuParams: the element
     */
    public JDFStatusQuParams appendStatusQuParams()
    {
        return  (JDFStatusQuParams) appendValidElement(ElementName.STATUSQUPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element StatusQuParams
     * @param iSkip number of elements to skip
     * @return JDFStatusQuParams: the element
     * @deprecated - use 0 parameter version
     */
    public JDFStatusQuParams getStatusQuParams(int iSkip)
    {
        return (JDFStatusQuParams) getValidElement(ElementName.STATUSQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /**
     * get iSkip'th element StatusQuParams
     * @param iSkip number of elements to skip
     * @return JDFStatusQuParams: the element
     */
    public JDFStatusQuParams getStatusQuParams()
    {
        return (JDFStatusQuParams) getValidElement(ElementName.STATUSQUPARAMS, JDFConstants.EMPTYSTRING, 0);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element StopPersChParams
     * @param int iSkip number of elements to skip
     * @return JDFStopPersChParams: the element
     */
    public JDFStopPersChParams getCreateStopPersChParams(int iSkip)
    {
        return  (JDFStopPersChParams)
        getCreateValidElement(ElementName.STOPPERSCHPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>StopPersChParams</code>
     * @return JDFStopPersChParams: the element
     */
    public JDFStopPersChParams appendStopPersChParams()
    {
        return (JDFStopPersChParams) appendValidElement(ElementName.STOPPERSCHPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>StopPersChParams</code>
     * @param iSkip number of elements to skip
     * @return  JDFStopPersChParams: the element
     */
    public JDFStopPersChParams getStopPersChParams(int iSkip)
    {
        return (JDFStopPersChParams) getValidElement(ElementName.STOPPERSCHPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }

    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>SubmissionMethods</code>
     * @param iSkip number of elements to skip
     * @return JDFSubmissionMethods: the element
     */
    public JDFSubmissionMethods getCreateSubmissionMethods(int iSkip)
    {
        return (JDFSubmissionMethods)
        getCreateValidElement(ElementName.SUBMISSIONMETHODS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>SubmissionMethods</code>
     * @return JDFSubmissionMethods: the element
     */
    public JDFSubmissionMethods appendSubmissionMethods()
    {
        return  (JDFSubmissionMethods) appendValidElement(ElementName.SUBMISSIONMETHODS, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>SubMissionMethods</code>
     * @param iSkip number of elements to skip
     * @return JDFSubmissionMethods: the element
     */
    public JDFSubmissionMethods getSubmissionMethods(int iSkip)
    {
        return (JDFSubmissionMethods) getValidElement(ElementName.SUBMISSIONMETHODS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /**
     * get iSkip'th element <code>TrackFilter</code>
     * @param iSkip number of elements to skip
     * @return JDFTrackFilter: the element
     */
    public JDFTrackFilter getCreateTrackFilter(int iSkip)
    {
        return (JDFTrackFilter)
        getCreateValidElement(ElementName.TRACKFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>TrackFilter</code>
     * @return JDFTrackFilter: the element
     */
    public JDFTrackFilter appendTrackFilter()
    {
        return (JDFTrackFilter) appendValidElement(ElementName.TRACKFILTER, null);
    }

    /////////////////////////////////////////////////////////////////////
    /**
     * get iSkip'th element <code>TrackFilter</code>
     * @param iSkip number of elements to skip
     * @return JDFTrackFilter: the element
     */
    public JDFTrackFilter getTrackFilter(int iSkip)
    {
        return (JDFTrackFilter) getValidElement(ElementName.TRACKFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////

    /** get iSkip'th element <code>TrackResult</code>
     * @param iSkip number of elements to skip
     * @return JDFTrackResult: the element
     */
    public JDFTrackResult getCreateTrackResult(int iSkip)
    {
        return (JDFTrackResult)
        getCreateValidElement(ElementName.TRACKRESULT, JDFConstants.EMPTYSTRING, iSkip);
    }

    /////////////////////////////////////////////////////////////////////

    /**
     * append element <code>TrackResult</code>
     * @return JDFTrackResult: the element
     */
    public JDFTrackResult appendTrackResult()
    {
        return (JDFTrackResult) appendValidElement(ElementName.TRACKRESULT, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * get the iSkip'th element <code>TrackResult</code>
     * @param iSkip the number of elements to skip
     * @return JDFTrackResult: the element
     */
    public JDFTrackResult getTrackResult(int iSkip)
    {
        return (JDFTrackResult) getValidElement(ElementName.TRACKRESULT, JDFConstants.EMPTYSTRING, iSkip);
    }


    // new from QC.JMF package

    /**
     * Returns the ReturnCode; applies to JmfResponse and JmfAcknowledge.
     * @return ConstReturnCode
     */
    public int getReturnCode()
    {
        return getIntAttribute(AttributeName.RETURNCODE, null, 0);
    }


    /**
     * get iSkip'th element <code>IDInfo</code>, create if it doesn't exist
     * @param iSkip number of elements to skip
     * @return JDFIDInfo: the element
     */
    public JDFIDInfo getCreateIDInfo(int iSkip)
    {
        return (JDFIDInfo)getCreateValidElement(ElementName.IDINFO, null, iSkip);
    }

    /**
     * append element <code>IDInfo</code>
     * JDFIDInfo: the element
     */
    public  JDFIDInfo appendIDInfo()
    {
        return (JDFIDInfo)appendValidElement(ElementName.IDINFO, null);
    }

    /**
     * get iSkip'th element <code>IDInfo</code>
     * @param iSkip number of elements to skip
     * JDFIDInfo: the element
     */
    public JDFIDInfo getIDInfo(int iSkip)
    {
        return (JDFIDInfo)getValidElement(ElementName.IDINFO, null, iSkip);
    }

    /**
     * get iSkip'th element FlushedResources
     * @param iSkip number of elements to skip
     * @return JDFFlushedResources: the element
     */
    public JDFFlushedResources getCreateFlushedResources(int iSkip)
    {
        return (JDFFlushedResources)getCreateValidElement(ElementName.FLUSHEDRESOURCES, null, iSkip);
    }

    /**
     * append element <code>FlushedResources</code>
     * @return JDFFlushedResources: the element
     */
    public JDFFlushedResources appendFlushedResources()
    {
        return (JDFFlushedResources)appendValidElement(ElementName.FLUSHEDRESOURCES, null);
    }

    public JDFFlushedResources getFlushedResources(int iSkip)
    {
        return (JDFFlushedResources)getValidElement(ElementName.FLUSHEDRESOURCES, null, iSkip);
    }

    public JDFFlushQueueParams getCreateFlushQueueParams(int iSkip)
    {
        return (JDFFlushQueueParams)getCreateValidElement(ElementName.FLUSHQUEUEPARAMS, null, iSkip);
    }

    public JDFFlushQueueParams appendFlushQueueParams()
    {
        return (JDFFlushQueueParams)appendValidElement(ElementName.FLUSHQUEUEPARAMS, null);
    }

    public JDFFlushQueueParams getFlushQueueParams(int iSkip)
    {
        return (JDFFlushQueueParams)getValidElement( ElementName.FLUSHQUEUEPARAMS, null, iSkip);
    }

    public JDFFlushResourceParams getCreateFlushResourceParams(int iSkip)
    {
        return (JDFFlushResourceParams)getCreateValidElement(ElementName.FLUSHRESOURCEPARAMS, null, iSkip);
    }

    public JDFFlushResourceParams appendFlushResourceParams()
    {
        return (JDFFlushResourceParams)appendValidElement(ElementName.FLUSHRESOURCEPARAMS, null);
    }

    public JDFFlushResourceParams getFlushResourceParams(int iSkip)
    {
        return (JDFFlushResourceParams)getValidElement(ElementName.FLUSHRESOURCEPARAMS, null, iSkip);
    }

    public JDFNewJDFCmdParams getCreateNewJDFCmdParams(int iSkip)
    {
        return (JDFNewJDFCmdParams)getCreateValidElement(ElementName.NEWJDFCMDPARAMS, null, iSkip);
    }

    public JDFNewJDFCmdParams appendNewJDFCmdParams()
    {
        return (JDFNewJDFCmdParams)appendValidElement(ElementName.NEWJDFCMDPARAMS, null);
    }

    public JDFNewJDFCmdParams getNewJDFCmdParams(int iSkip)
    {
        return (JDFNewJDFCmdParams)getValidElement(ElementName.NEWJDFCMDPARAMS, null, iSkip);
    }

    public JDFNewJDFQuParams getCreateNewJDFQuParams(int iSkip)
    {
        return(JDFNewJDFQuParams)getCreateValidElement(ElementName.NEWJDFQUPARAMS, null, iSkip);
    }

    public JDFNewJDFQuParams appendNewJDFQuParams()
    {
        return (JDFNewJDFQuParams)appendValidElement(ElementName.NEWJDFQUPARAMS, null);
    }

    public JDFNewJDFQuParams getNewJDFQuParams(int iSkip)
    {
        return (JDFNewJDFQuParams)getValidElement(ElementName.NEWJDFQUPARAMS, null, iSkip);
    }

    public JDFNodeInfoCmdParams getCreateNodeInfoCmdParams(int iSkip)
    {
        return (JDFNodeInfoCmdParams)getCreateValidElement(ElementName.NODEINFOCMDPARAMS, null, iSkip);
    }

    public JDFNodeInfoCmdParams appendNodeInfoCmdParams()
    {
        return (JDFNodeInfoCmdParams)appendValidElement(ElementName.NODEINFOCMDPARAMS, null);
    }

    public JDFNodeInfoCmdParams getNodeInfoCmdParams(int iSkip)
    {
        return (JDFNodeInfoCmdParams)getValidElement(ElementName.NODEINFOCMDPARAMS, null, iSkip);
    }

    public JDFNodeInfoQuParams getCreateNodeInfoQuParams(int iSkip)
    {
        return (JDFNodeInfoQuParams)getCreateValidElement(ElementName.NODEINFOQUPARAMS, null, iSkip);
    }

    public JDFNodeInfoQuParams appendNodeInfoQuParams()
    {
        return (JDFNodeInfoQuParams)appendValidElement(ElementName.NODEINFOQUPARAMS, null);
    }

    public JDFNodeInfoQuParams getNodeInfoQuParams(int iSkip)
    {
        return (JDFNodeInfoQuParams)getValidElement(ElementName.NODEINFOQUPARAMS, null, iSkip);
    }

    public JDFNodeInfoResp getCreateNodeInfoResp(int iSkip)
    {
        return (JDFNodeInfoResp)getCreateValidElement(ElementName.NODEINFORESP, null, iSkip);
    }

    public JDFNodeInfoResp appendNodeInfoResp()
    {
        return (JDFNodeInfoResp)appendValidElement(ElementName.NODEINFORESP, null);
    }

    public JDFNodeInfoResp getNodeInfoResp(int iSkip)
    {
        return (JDFNodeInfoResp)getValidElement(ElementName.NODEINFORESP, null, iSkip);
    }

    public JDFQueueFilter getCreateQueueFilter(int iSkip)
    {
        return (JDFQueueFilter)getCreateValidElement(ElementName.QUEUEFILTER, null, iSkip);
    }
    /////////////////////////////////////////////////////////////////////

    public JDFQueueFilter appendQueueFilter()
    {
        return (JDFQueueFilter)appendValidElement(ElementName.QUEUEFILTER, null);
    }
    /////////////////////////////////////////////////////////////////////

    public JDFQueueFilter getQueueFilter(int iSkip)
    {
        return (JDFQueueFilter)getValidElement(ElementName.QUEUEFILTER, null, iSkip);
    }

    public JDFRequestQueueEntryParams getCreateRequestQueueEntryParams(int iSkip)
    {
        return (JDFRequestQueueEntryParams)getCreateValidElement(ElementName.REQUESTQUEUEENTRYPARAMS, null, iSkip);
    }

    public JDFRequestQueueEntryParams appendRequestQueueEntryParams()
    {
        return (JDFRequestQueueEntryParams)appendValidElement(ElementName.REQUESTQUEUEENTRYPARAMS, null);
    }

    public JDFRequestQueueEntryParams getRequestQueueEntryParams(int iSkip)
    {
        return (JDFRequestQueueEntryParams)getValidElement(ElementName.REQUESTQUEUEENTRYPARAMS, null, iSkip);
    }

    JDFResourcePullParams getCreateResourcePullParams(int iSkip)
    {
        return (JDFResourcePullParams)getCreateValidElement(ElementName.RESOURCEPULLPARAMS, null, iSkip);
    }

    public JDFResourcePullParams appendResourcePullParams()
    {
        return (JDFResourcePullParams)appendValidElement(ElementName.RESOURCEPULLPARAMS, null);
    }

    public JDFResourcePullParams getResourcePullParams(int iSkip)
    {
        return (JDFResourcePullParams)getValidElement(ElementName.RESOURCEPULLPARAMS, null, iSkip);
    }

    public JDFResubmissionParams getCreateResubmissionParams(int iSkip)
    {
        return (JDFResubmissionParams)getCreateValidElement(ElementName.RESUBMISSIONPARAMS, null, iSkip);
    }

    public JDFResubmissionParams appendResubmissionParams()
    {
        return (JDFResubmissionParams)appendValidElement(ElementName.RESUBMISSIONPARAMS, null);
    }

    public JDFResubmissionParams getResubmissionParams(int iSkip)
    {
        return (JDFResubmissionParams)getValidElement(ElementName.RESUBMISSIONPARAMS, null, iSkip);
    }

    public JDFReturnQueueEntryParams getCreateReturnQueueEntryParams(int iSkip)
    {
        return (JDFReturnQueueEntryParams)getCreateValidElement(ElementName.RETURNQUEUEENTRYPARAMS, null, iSkip);
    }

    public JDFReturnQueueEntryParams appendReturnQueueEntryParams()
    {
        return (JDFReturnQueueEntryParams)appendValidElement(ElementName.RETURNQUEUEENTRYPARAMS, null);
    }

    public JDFReturnQueueEntryParams getReturnQueueEntryParams(int iSkip)
    {
        return (JDFReturnQueueEntryParams)getValidElement(ElementName.RETURNQUEUEENTRYPARAMS, null, iSkip);
    }

    public JDFShutDownCmdParams getCreateShutDownCmdParams(int iSkip)
    {
        return (JDFShutDownCmdParams)getCreateValidElement(ElementName.SHUTDOWNCMDPARAMS, null, iSkip);
    }

    public JDFShutDownCmdParams appendShutDownCmdParams()
    {
        return (JDFShutDownCmdParams)appendValidElement(ElementName.SHUTDOWNCMDPARAMS, null);
    }

    public JDFShutDownCmdParams getShutDownCmdParams(int iSkip)
    {
        return (JDFShutDownCmdParams)getValidElement(ElementName.SHUTDOWNCMDPARAMS, null, iSkip);
    }

   //////////////////////////////////////////////////////////////////////////////

    public JDFWakeUpCmdParams getCreateWakeUpCmdParams()
    {
        return (JDFWakeUpCmdParams)getCreateValidElement(ElementName.WAKEUPCMDPARAMS, null, 0);
    }

    public JDFWakeUpCmdParams appendWakeUpCmdParams()
    {
        return (JDFWakeUpCmdParams)appendValidElement(ElementName.WAKEUPCMDPARAMS, null);
    }

    public JDFWakeUpCmdParams getWakeUpCmdParams()
    {
        return (JDFWakeUpCmdParams)getValidElement(ElementName.WAKEUPCMDPARAMS, null, 0);
    }
   //////////////////////////////////////////////////////////////////////////////

    public JDFModifyNodeCmdParams getCreateModifyNodeCmdParams()
    {
        return (JDFModifyNodeCmdParams)getCreateValidElement(ElementName.MODIFYNODECMDPARAMS, null, 0);
    }

    public JDFModifyNodeCmdParams appendModifyNodeCmdParams()
    {
        return (JDFModifyNodeCmdParams)appendValidElement(ElementName.MODIFYNODECMDPARAMS, null);
    }

    public JDFModifyNodeCmdParams getModifyNodeCmdParams()
    {
        return (JDFModifyNodeCmdParams)getValidElement(ElementName.MODIFYNODECMDPARAMS, null, 0);
    }
   //////////////////////////////////////////////////////////////////////////////

    public JDFUpdateJDFCmdParams getCreateUpdateJDFCmdParams()
    {
        return (JDFUpdateJDFCmdParams)getCreateValidElement(ElementName.UPDATEJDFCMDPARAMS, null, 0);
    }

    public JDFUpdateJDFCmdParams appendUpdateJDFCmdParams()
    {
        return (JDFUpdateJDFCmdParams)appendValidElement(ElementName.UPDATEJDFCMDPARAMS, null);
    }

    public JDFUpdateJDFCmdParams getUpdateJDFCmdParams()
    {
        return (JDFUpdateJDFCmdParams)getValidElement(ElementName.UPDATEJDFCMDPARAMS, null, 0);
    }

    /**
     * Method getrefID.
     * @return String
     */
    public String getrefID()
    {
        return getAttribute(AttributeName.REFID);
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.auto.JDFAutoMessage#getID()
     */
    public String getID()
    {
        return this.getAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING);
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.core.JDFElement#getInvalidElements(org.cip4.jdflib.core.KElement.EnumValidationLevel, boolean, int)
     */
    public VString getInvalidElements(
            EnumValidationLevel level,
            boolean bIgnorePrivate,
            int nMax)
    {
        int nElem = 0;
        VString vElem = super.getInvalidElements(level, bIgnorePrivate, nMax);
        int n = vElem.size();
        if (n >= nMax)
        {
            return vElem;
        }

        String[] elementArray=
        {
                ElementName.DEVICE,
                ElementName.DEVICEFILTER,
                ElementName.DEVICEINFO,
                ElementName.DEVICELIST,
                ElementName.FLUSHEDRESOURCES,
                ElementName.FLUSHQUEUEPARAMS,
                ElementName.FLUSHRESOURCEPARAMS,
                ElementName.IDINFO,
                ElementName.JDFCONTROLLER,
                ElementName.JDFSERVICE,
                ElementName.JOBPHASE,
                ElementName.KNOWNMSGQUPARAMS,
                ElementName.MESSAGESERVICE,
                ElementName.MSGFILTER,
                ElementName.NEWJDFCMDPARAMS,
                ElementName.NEWJDFQUPARAMS,
                ElementName.NODEINFOCMDPARAMS,
                ElementName.NODEINFOQUPARAMS,
                ElementName.NODEINFORESP,
                ElementName.NOTIFICATIONDEF,
                ElementName.NOTIFICATIONFILTER,
                ElementName.OCCUPATION,
                ElementName.PIPEPARAMS,
                ElementName.QUEUE,
                ElementName.QUEUEENTRY,
                ElementName.QUEUEENTRYDEF,
                ElementName.QUEUEENTRYDEFLIST,
                ElementName.QUEUEENTRYPRIPARAMS,
                ElementName.QUEUEENTRYPOSPARAMS,
                ElementName.QUEUEFILTER,
                ElementName.QUEUESUBMISSIONPARAMS,
                ElementName.REQUESTQUEUEENTRYPARAMS,
                ElementName.RESOURCECMDPARAMS,
                ElementName.RESOURCEINFO,
                ElementName.RESOURCEPULLPARAMS,
                ElementName.RESOURCEQUPARAMS,
                ElementName.RESUBMISSIONPARAMS,
                ElementName.RETURNQUEUEENTRYPARAMS,
                ElementName.SHUTDOWNCMDPARAMS,
                ElementName.STATUSQUPARAMS,
                ElementName.STOPPERSCHPARAMS,
                ElementName.SUBMISSIONMETHODS,
                ElementName.TRACKFILTER,
                ElementName.TRACKRESULT,
                ElementName.WAKEUPCMDPARAMS,
         };
        KElement[] ae=getChildElementArray();
        if(ae==null || ae.length==0)
        {
         return vElem;
        }
        Set s=new HashSet();
        for(int i=0;i<ae.length;i++) {
			s.add(ae[i].getLocalName());
		}

        for(int ii=0;ii<elementArray.length;ii++)
        {
            String element=elementArray[ii];
            if(!s.contains(element)) {
				continue;
			}

            nElem = numChildElements(element,null);
            for (int i = 0; i < nElem; i++)
            {
                KElement child = null;
                boolean bCatch = false;

                try
                {
                    child = getValidElement(element, null, i);
                }
                catch(JDFException e)
                {
                    bCatch = true;
                }
                if (bCatch || child == null || !child.isValid(level))
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
     definition of optional elements in the JDF namespace
     */
    // TODO move to elemeInfoTable creation
    /* (non-Javadoc)
     * @see org.cip4.jdflib.core.KElement#optionalElements()
     */
    public VString optionalElements()
    {
        VString s = super.optionalElements();
        EnumType t = getEnumType();
        // loop over all valid potential elements for this family
        String[] vObjs = familyTypeObj();
        // for each object, check whether it is compatible with the type of this
        for (int i = 0; i < vObjs.length; i++)
        {
            Vector vt = getValidTypeVector(vObjs[i], 0);
            // is it there ?
            for (int j = 0; j < vt.size(); j++)
            {
                if (vt.elementAt(j).equals(t))
                {
                    // obj x is compatible with this -> add it to the list of elements
                    s.appendUnique(vObjs[i]);
                    break;
                }
            }
        }
        return s;
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.core.JDFElement#getInvalidAttributes(org.cip4.jdflib.core.KElement.EnumValidationLevel, boolean, int)
     */
    public VString getInvalidAttributes(EnumValidationLevel level, boolean bIgnorePrivate, int nMax)
    {
         VString s=super.getInvalidAttributes(level, bIgnorePrivate, nMax);
         if(s.contains(AttributeName.XSITYPE)) {
			return s;
		}

         if(!hasAttribute(AttributeName.XSITYPE)) {
			return s;
		}
         String t=getType();
         if(xmlnsPrefix(t)!=null) {
			return s;
		}
         String xs=getXSIType();
         if(!xs.equals(getLocalName()+t)) {
			s.add(AttributeName.XSITYPE);
		}
         return s;

    }

}
