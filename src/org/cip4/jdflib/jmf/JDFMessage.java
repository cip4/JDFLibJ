/*--------------------------------------------------------------------------------------------------
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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
     * @param ownerDocument     
     * @param qualifiedName
     */
    public JDFMessage(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    /**
     * Constructor for JDFMessage
     * @param ownerDocument
     * @param namespaceURI
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
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
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
     * getFamily
     *
     * @return EnumFamily - the enum family type Family_Unknown, Family_Query, Family_Signal,
     *                      Family_Command, Family_Response, Family_Acknowledge
     */
    public EnumFamily getFamily()
    {
        return EnumFamily.getEnum(getLocalName());
    }
    
    /**
     * getType
     *
     * @return String
     */
    public String getType()
    {
        return getAttribute(AttributeName.TYPE, null, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * Set attribute Type and xsi:type 
     *
     * @param typ the type
     */
    public void setType(String typ)
    {
        removeAttribute("type",AttributeName.XSIURI);
        setAttribute(AttributeName.TYPE, typ, null);
        if(xmlnsPrefix(typ)==null)
            setXSIType(getLocalName()+typ);
    }
    
    
    /**
     * SetQuery - sets the initiating query to q
     *
     * @param JDFQuery q
     *
     * @return boolean
     */
    public void setQuery(JDFQuery q)
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
        setAttribute(AttributeName.REFID, q.getID(), JDFConstants.EMPTYSTRING);
        setType(q.getType());
    }
    
    /**
     * isValid the default is EnumValidationLevel.Complete
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
     * @param EnumType value the value to set the attribute to
     * @deprecated use setType()
     */
    public void setEnumType(EnumType value)
    {
        setType(value);
    }
    /**
     * Set attribute Type
     * @param EnumType value the value to set the attribute to
     */
    public void setType(EnumType value)
    {
        final String typeName = value.getName();
        setType(typeName);
    }
    
    /**
     * adds xsi:Type if it doesn't exist
     */
    public boolean fixVersion(EnumVersion version)
    {
        if(version!=null)
            version.getClass(); // dummy to fool compiler
        if(hasAttribute(AttributeName.TYPE)&&!hasAttribute(AttributeName.XSITYPE,AttributeName.XSIURI,false))
        {
            setAttribute(AttributeName.XSITYPE,getLocalName()+getType(),AttributeName.XSIURI);
        }
        return true;
    }
    /**
     * checks whether the type of messageElement is valid for this message
     * @param KString elementName the name of the element to be tested
     * @return boolean true if valid; true always if not in JDFNameSpace
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
        final VString familyTypeObj = familyTypeObj();
        boolean isFamilyTypeString = (familyTypeObj==null) ? false : familyTypeObj.contains(elementName);
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
     * @return vEnumType vector of valid EnumTypes; empty if all are invalid
     * @default getValidTypeVector(elementName, 0)
     */
    //  typedef std::vector<EnumType> vEnumType;        
    private Vector getValidTypeVector(String elementName, int iSkip)
    {
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
     * @return EnumTypethe enumeration value of the attribute
     */
    public EnumType getEnumType()
    {
        return EnumType.getEnum(getAttribute(AttributeName.TYPE, null, null));
    }
    
    /**
     * return a vector of valid object names for this family type 
     * @return String comma separated valid object types for this family type
     */
    public VString familyTypeObj()
    {
        EnumFamily family = getFamily();
        if(family==null)
            return null;
        
        if (family.equals(EnumFamily.Command))
        {
            return new VString(commandTypeObjString());
        }
        if (family.equals(EnumFamily.Registration))
        {
            return new VString(registrationTypeObjString());
        }
        
        if (family.equals(EnumFamily.Query))
        {
            return new VString(queryTypeObjString());
        }
        
        if (family.equals(EnumFamily.Response)|| family.equals(EnumFamily.Acknowledge))
        {
            return new VString( responseTypeObjString());
        }
        
        if (family.equals(EnumFamily.Signal))
        {
            VString v=new VString(queryTypeObjString());
            v.addAll(responseTypeObjString());
            v.unify();
            return v;
        }
        
        return null;
    }
    
    /**
     * Enumeration strings for list of CommandTypeObj
     * @returnString comma separated list of enumerated string values 
     */
    private static String[] commandTypeObjString()
    {
        
        final String[] enums =
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
            
        return enums;
    }
    /**
     * Enumeration strings for list of CommandTypeObj
     * @returnString comma separated list of enumerated string values 
     */
    private static String[] registrationTypeObjString()
    {
        
        final String[] enums =
            {
                ElementName.PIPEPARAMS,
                ElementName.RESOURCECMDPARAMS,
                ElementName.RESOURCEPULLPARAMS,
            };
            
        return enums;
    }    
    /**
     * Enumeration strings for list of QueryTypeObj
     * @returnString comma separated list of enumerated string values 
     */
    private static String[] queryTypeObjString()
    {
        final String[] enums =
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
        return enums;
    }
    
    /**
     * Enumeration strings for list of ResponseTypeObj
     * @returnString comma separated list of enumerated string values 
     */
    private static String[]responseTypeObjString()
    {
        final String[] enums = {
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
        return enums;
    }
    
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
    
    public KElement getValidElement(
            String nodeName,
            String nameSpaceURI,
            int iSkip)
    {
        if (!isValidMessageElement(nodeName, iSkip))
        {
            throw new JDFException("AppendValidElement: illegal element :" + nodeName);
        }
        
        return getElement_JDFElement(nodeName, nameSpaceURI, iSkip);
    }
    
    //////////////////////////////////////////////////////////////////////
    public KElement getCreateValidElement(
            String nodeName,
            String nameSpaceURI,
            int iSkip)
    {
        if (!isValidMessageElement(nodeName, iSkip))
        {
            throw new JDFException("AppendValidElement: illegal element :" + nodeName);
        }
        
        return getCreateElement_KElement(nodeName, nameSpaceURI, iSkip);
    }
    
    /* ******************************************************
     // Element getter / Setter
      **************************************************************** */
    
    public JDFDevice getCreateDevice(int iSkip)
    {
        JDFDevice e = (JDFDevice) getCreateValidElement(ElementName.DEVICE, JDFConstants.EMPTYSTRING, iSkip);
        
        return e;
    }
    /////////////////////////////////////////////////////////////////////
    public JDFDevice appendDevice()
    {
        JDFDevice e = (JDFDevice) appendValidElement(ElementName.DEVICE, null);
        
        return e;
    }
    /////////////////////////////////////////////////////////////////////
    /*
     get first element 
     */
    public JDFDevice getDevice(int iSkip)
    {
        return (JDFDevice) getValidElement(ElementName.DEVICE, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    public JDFDeviceFilter getCreateDeviceFilter(int iSkip)
    {
        JDFDeviceFilter e =
            (JDFDeviceFilter) getCreateValidElement(ElementName.DEVICEFILTER, JDFConstants.EMPTYSTRING, iSkip);
        
        return e;
    }
    /////////////////////////////////////////////////////////////////////
    public JDFDeviceFilter appendDeviceFilter()
    {
        return (JDFDeviceFilter) appendValidElement(ElementName.DEVICEFILTER, null);
    }
    /////////////////////////////////////////////////////////////////////
    /*
     get first element 
     */
    public JDFDeviceFilter getDeviceFilter(int iSkip)
    {
        return (JDFDeviceFilter) getValidElement(ElementName.DEVICEFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    public JDFDeviceInfo getCreateDeviceInfo(int iSkip)
    {
        return (JDFDeviceInfo) 
        getCreateValidElement(ElementName.DEVICEINFO, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    public JDFDeviceInfo appendDeviceInfo()
    {
        return (JDFDeviceInfo) appendValidElement(ElementName.DEVICEINFO, null);
    }
    /////////////////////////////////////////////////////////////////////
    
    /*
     get first element 
     */
    public JDFDeviceInfo getDeviceInfo(int iSkip)
    {
        return (JDFDeviceInfo) getValidElement(ElementName.DEVICEINFO, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element DeviceList
     * @param int iSkip number of elements to skip
     * @return JDFDeviceList The element
     */
    public JDFDeviceList getCreateDeviceList(int iSkip)
    {
        return (JDFDeviceList) 
        getCreateValidElement(ElementName.DEVICELIST, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element DeviceList
     */
    public JDFDeviceList appendDeviceList()
    {
        return (JDFDeviceList) appendValidElement(ElementName.DEVICELIST, null);
    }
    
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFDeviceList getDeviceList(int iSkip)
    {
        return (JDFDeviceList) getValidElement(ElementName.DEVICELIST, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element EmployeeDef
     * @param int iSkip number of elements to skip
     * @return JDFEmployeeDef The element
     */
    public JDFEmployeeDef getCreateEmployeeDef(int iSkip)
    {
        return (JDFEmployeeDef) 
        getCreateValidElement(ElementName.EMPLOYEEDEF, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element EmployeeDef
     */
    public JDFEmployeeDef appendEmployeeDef()
    {
        return (JDFEmployeeDef) appendValidElement(ElementName.EMPLOYEEDEF, null);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     *const get first element 
     */
    public JDFEmployeeDef getEmployeeDef(int iSkip)
    {
        return (JDFEmployeeDef) getValidElement(ElementName.EMPLOYEEDEF, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    
    /** get Element JDFController
     * @param int iSkip number of elements to skip
     * @return JDFJDFController The element
     */
    public JDFJDFController getCreateJDFController(int iSkip)
    {
        return (JDFJDFController) 
        getCreateValidElement(ElementName.JDFCONTROLLER, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element JDFController
     */
    public JDFJDFController appendJDFController()
    {
        return (JDFJDFController) appendValidElement(ElementName.JDFCONTROLLER, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFJDFController getJDFController(int iSkip)
    {
        return (JDFJDFController) getValidElement(ElementName.JDFCONTROLLER, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    
    /** get Element JDFService
     * @param int iSkip number of elements to skip
     * @return JDFJDFService The element
     */
    public JDFJDFService getCreateJDFService(int iSkip)
    {
        return (JDFJDFService) 
        getCreateValidElement(ElementName.JDFSERVICE, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element JDFService
     */
    public JDFJDFService appendJDFService()
    {
        return (JDFJDFService) appendValidElement(ElementName.JDFSERVICE, null);
    }
    
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFJDFService getJDFService(int iSkip)
    {
        return (JDFJDFService) getValidElement(ElementName.JDFSERVICE, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    
    /** get Element JobPhase
     * @param int iSkip number of elements to skip
     * @return JDFJobPhase The element
     */
    public JDFJobPhase getCreateJobPhase(int iSkip)
    {
        return (JDFJobPhase) getValidElement(ElementName.JOBPHASE, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element JobPhase
     */
    public JDFJobPhase appendJobPhase()
    {
        return  (JDFJobPhase) appendValidElement(ElementName.JOBPHASE, null);
    }
    /////////////////////////////////////////////////////////////////////
    /*
     get first element 
     */
    public JDFJobPhase getJobPhase(int iSkip)
    {
        return (JDFJobPhase) getValidElement(ElementName.JOBPHASE, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    
    /** get Element KnownMsgQuParams
     * @param int iSkip number of elements to skip
     * @return JDFKnownMsgQuParams The element
     */
    public JDFKnownMsgQuParams getCreateKnownMsgQuParams(int iSkip)
    {
        return (JDFKnownMsgQuParams) 
        getCreateValidElement(ElementName.KNOWNMSGQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element KnownMsgQuParams
     */
    public JDFKnownMsgQuParams appendKnownMsgQuParams()
    {
        return (JDFKnownMsgQuParams) appendValidElement(ElementName.KNOWNMSGQUPARAMS, null);
    }
    
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFKnownMsgQuParams getKnownMsgQuParams(int iSkip)
    {
        return (JDFKnownMsgQuParams) getValidElement(ElementName.KNOWNMSGQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element MessageService
     * @param int iSkip number of elements to skip
     * @return JDFMessageService The element
     */
    public JDFMessageService getCreateMessageService(int iSkip)
    {
        return (JDFMessageService) 
        getCreateValidElement(ElementName.MESSAGESERVICE, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element MessageService
     */
    public JDFMessageService appendMessageService()
    {
        return  (JDFMessageService) appendValidElement(ElementName.MESSAGESERVICE, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFMessageService getMessageService(int iSkip)
    {
        return (JDFMessageService) getValidElement(ElementName.MESSAGESERVICE, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element MsgFilter
     * @param int iSkip number of elements to skip
     * @return JDFMsgFilter The element
     */
    public JDFMsgFilter getCreateMsgFilter(int iSkip)
    {
        return  (JDFMsgFilter) getCreateValidElement(ElementName.MSGFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element MsgFilter
     */
    public JDFMsgFilter appendMsgFilter()
    {
        return  (JDFMsgFilter) appendValidElement(ElementName.MSGFILTER, null);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     *get first element 
     */
    public JDFMsgFilter getMsgFilter(int iSkip)
    {
        return (JDFMsgFilter) getValidElement(ElementName.MSGFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element NotificationDef
     * @param int iSkip number of elements to skip
     * @return JDFNotificationDef The element
     */
    public JDFNotificationDef getCreateNotificationDef(int iSkip)
    {
        return  (JDFNotificationDef) 
        getCreateValidElement(ElementName.NOTIFICATIONDEF, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element NotificationDef
     */
    public JDFNotificationDef appendNotificationDef()
    {
        return (JDFNotificationDef) appendValidElement(ElementName.NOTIFICATIONDEF, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFNotificationDef getNotificationDef(int iSkip)
    {
        return (JDFNotificationDef) getValidElement(ElementName.NOTIFICATIONDEF, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element NotificationFilter
     * @param int iSkip number of elements to skip
     * @return JDFNotificationFilter The element
     */
    public JDFNotificationFilter getCreateNotificationFilter(int iSkip)
    {
        return  (JDFNotificationFilter) 
        getCreateValidElement(ElementName.NOTIFICATIONFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element NotificationFilter
     */
    public JDFNotificationFilter appendNotificationFilter()
    {
        return  (JDFNotificationFilter) appendValidElement(ElementName.NOTIFICATIONFILTER, null);
    }
    
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFNotificationFilter getNotificationFilter(int iSkip)
    {
        return (JDFNotificationFilter) getValidElement(ElementName.NOTIFICATIONFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element Occupation
     * @param int iSkip number of elements to skip
     * @return JDFOccupation The element
     */
    public JDFOccupation getCreateOccupation(int iSkip)
    {
        return (JDFOccupation) 
        getCreateValidElement(ElementName.OCCUPATION, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element Occupation
     */
    public JDFOccupation appendOccupation()
    {
        return (JDFOccupation) appendValidElement(ElementName.OCCUPATION, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFOccupation getOccupation(int iSkip)
    {
        return (JDFOccupation) getValidElement(ElementName.OCCUPATION, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element PipeParams
     * @param int iSkip number of elements to skip
     * @return JDFPipeParams The element
     */
    public JDFPipeParams getCreatePipeParams(int iSkip)
    {
        return  (JDFPipeParams) 
        getCreateValidElement(ElementName.PIPEPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element PipeParams
     */
    public JDFPipeParams appendPipeParams()
    {
        return  (JDFPipeParams) appendValidElement(ElementName.PIPEPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////
    /*
     get first element 
     */
    public JDFPipeParams getPipeParams(int iSkip)
    {
        return (JDFPipeParams) getValidElement(ElementName.PIPEPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element Queue
     * @param int iSkip number of elements to skip
     * @return JDFQueue The element
     */
    public JDFQueue getCreateQueue(int iSkip)
    {
        return (JDFQueue) getCreateValidElement(ElementName.QUEUE, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element Queue
     */
    public JDFQueue appendQueue()
    {
        return (JDFQueue) appendValidElement(ElementName.QUEUE, null);
    }
    
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFQueue getQueue(int iSkip)
    {
        return (JDFQueue) getValidElement(ElementName.QUEUE, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    public JDFQueueEntry getCreateQueueEntry(int iSkip)
    {
        return  (JDFQueueEntry) 
        getCreateValidElement(ElementName.QUEUEENTRY, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     * Append element QueueEntry
     */
    public JDFQueueEntry appendQueueEntry()
    {
        return  (JDFQueueEntry) appendValidElement(ElementName.QUEUEENTRY, null);
    }
    /////////////////////////////////////////////////////////////////////
    /*
     get first element 
     */
    public JDFQueueEntry getQueueEntry(int iSkip)
    {
        return (JDFQueueEntry) 
        getValidElement(ElementName.QUEUEENTRY, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element QueueEntryDef
     * @param int iSkip number of elements to skip
     * @return JDFQueueEntryDef The element
     */
    public JDFQueueEntryDef getCreateQueueEntryDef(int iSkip)
    {
        return (JDFQueueEntryDef) 
        getCreateValidElement(ElementName.QUEUEENTRYDEF, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element QueueEntryDef
     */
    public JDFQueueEntryDef appendQueueEntryDef()
    {
        return  (JDFQueueEntryDef) appendValidElement(ElementName.QUEUEENTRYDEF, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFQueueEntryDef getQueueEntryDef(int iSkip)
    {
        return (JDFQueueEntryDef) getValidElement(ElementName.QUEUEENTRYDEF, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    //////////////////////////////////////////////////////////////////////
    
    public JDFQueueEntryDefList getCreateQueueEntryDefList(int iSkip)
    {
        return (JDFQueueEntryDefList) 
        getCreateValidElement(ElementName.QUEUEENTRYDEFLIST, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    public JDFQueueEntryDefList appendQueueEntryDefList()
    {
        return  (JDFQueueEntryDefList) appendValidElement(ElementName.QUEUEENTRYDEFLIST, null);
    }
    /////////////////////////////////////////////////////////////////////
    /*
     get first element 
     */
    public JDFQueueEntryDefList getQueueEntryDefList(int iSkip)
    {
        return (JDFQueueEntryDefList) 
        getValidElement(ElementName.QUEUEENTRYDEFLIST, JDFConstants.EMPTYSTRING, iSkip);
        
    }
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element QueueEntryPriParams
     * @param int iSkip number of elements to skip
     * @return JDFQueueEntryPriParams The element
     */
    public JDFQueueEntryPriParams getCreateQueueEntryPriParams(int iSkip)
    {
        return  (JDFQueueEntryPriParams) 
        getCreateValidElement(ElementName.QUEUEENTRYPRIPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    /**
     * Append element QueueEntryPriParams
     */
    public JDFQueueEntryPriParams appendQueueEntryPriParams()
    {
        return  (JDFQueueEntryPriParams) appendValidElement(ElementName.QUEUEENTRYPRIPARAMS, null);
    }
    
    /**
     *get first element QueueEntryPriParams
     * @param int iSkip number of elements to skip
     *@return  JDFQueueEntryPriParams The element
     */
    public JDFQueueEntryPriParams getQueueEntryPriParams(int iSkip)
    {
        return (JDFQueueEntryPriParams) 
        getValidElement(ElementName.QUEUEENTRYPRIPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element QueueEntryPosParams
     * @param int iSkip number of elements to skip
     * @return JDFQueueEntryPosParams The element
     */
    public JDFQueueEntryPosParams getCreateQueueEntryPosParams(int iSkip)
    {
        return  (JDFQueueEntryPosParams) 
        getCreateValidElement(ElementName.QUEUEENTRYPOSPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element QueueEntryPosParams
     */
    public JDFQueueEntryPosParams appendQueueEntryPosParams()
    {
        return  (JDFQueueEntryPosParams) appendValidElement(ElementName.QUEUEENTRYPOSPARAMS, null);
    }
    
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFQueueEntryPosParams getQueueEntryPosParams(int iSkip)
    {
        return (JDFQueueEntryPosParams) 
        getValidElement(ElementName.QUEUEENTRYPOSPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element QueueSubmissionParams
     * @param int iSkip number of elements to skip
     * @return JDFQueueSubmissionParams The element
     */
    public JDFQueueSubmissionParams getCreateQueueSubmissionParams(int iSkip)
    {
        return  (JDFQueueSubmissionParams) 
        getCreateValidElement(ElementName.QUEUESUBMISSIONPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element QueueSubmissionParams
     */
    public JDFQueueSubmissionParams appendQueueSubmissionParams()
    {
        return  (JDFQueueSubmissionParams) appendValidElement(ElementName.QUEUESUBMISSIONPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFQueueSubmissionParams getQueueSubmissionParams(int iSkip)
    {
        return (JDFQueueSubmissionParams) 
        getValidElement(ElementName.QUEUESUBMISSIONPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element ResourceCmdParams
     * @param int iSkip number of elements to skip
     * @return JDFResourceCmdParams The element
     */
    public JDFResourceCmdParams getCreateResourceCmdParams(int iSkip)
    {
        return  (JDFResourceCmdParams) 
        getCreateValidElement(ElementName.RESOURCECMDPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element ResourceCmdParams
     */
    public JDFResourceCmdParams appendResourceCmdParams()
    {
        return  (JDFResourceCmdParams) appendValidElement(ElementName.RESOURCECMDPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFResourceCmdParams getResourceCmdParams(int iSkip)
    {
        return (JDFResourceCmdParams) getValidElement(ElementName.RESOURCECMDPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element ResourceQuParams
     * @param int iSkip number of elements to skip
     * @return JDFResourceQuParams The element
     */
    public JDFResourceQuParams getCreateResourceQuParams(int iSkip)
    {
        return (JDFResourceQuParams) 
        getCreateValidElement(ElementName.RESOURCEQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element ResourceQuParams
     */
    public JDFResourceQuParams appendResourceQuParams()
    {
        return (JDFResourceQuParams) appendValidElement(ElementName.RESOURCEQUPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFResourceQuParams getResourceQuParams(int iSkip)
    {
        return (JDFResourceQuParams) getValidElement(ElementName.RESOURCEQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element ResourceInfo
     * @param int iSkip number of elements to skip
     * @return JDFResourceInfo The element
     */
    public JDFResourceInfo getCreateResourceInfo(int iSkip)
    {
        return (JDFResourceInfo) 
        getCreateValidElement(ElementName.RESOURCEINFO, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element ResourceInfo
     */
    public JDFResourceInfo appendResourceInfo()
    {
        return  (JDFResourceInfo) appendValidElement(ElementName.RESOURCEINFO, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFResourceInfo getResourceInfo(int iSkip)
    {
        return (JDFResourceInfo) getValidElement(ElementName.RESOURCEINFO, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element StatusQuParams
     * @param int iSkip number of elements to skip
     * @return JDFStatusQuParams The element
     */
    public JDFStatusQuParams getCreateStatusQuParams(int iSkip)
    {
        return  (JDFStatusQuParams) 
        getCreateValidElement(ElementName.STATUSQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element StatusQuParams
     */
    public JDFStatusQuParams appendStatusQuParams()
    {
        return  (JDFStatusQuParams) appendValidElement(ElementName.STATUSQUPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     *get first element StatusQuParams
     * @param int iSkip number of elements to skip
     * @return  JDFStatusQuParams The element
     */
    public JDFStatusQuParams getStatusQuParams(int iSkip)
    {
        return (JDFStatusQuParams) getValidElement(ElementName.STATUSQUPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    
    /** get Element StopPersChParams
     * @param int iSkip number of elements to skip
     * @return JDFStopPersChParams The element
     */
    public JDFStopPersChParams getCreateStopPersChParams(int iSkip)
    {
        return  (JDFStopPersChParams) 
        getCreateValidElement(ElementName.STOPPERSCHPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element StopPersChParams
     */
    public JDFStopPersChParams appendStopPersChParams()
    {
        return (JDFStopPersChParams) appendValidElement(ElementName.STOPPERSCHPARAMS, null);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     *get first element StopPersChParams
     *@return  JDFStopPersChParams The element
     */
    public JDFStopPersChParams getStopPersChParams(int iSkip)
    {
        return (JDFStopPersChParams) getValidElement(ElementName.STOPPERSCHPARAMS, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element SubmissionMethods
     * @param int iSkip number of elements to skip
     * @return JDFSubmissionMethods The element
     */
    public JDFSubmissionMethods getCreateSubmissionMethods(int iSkip)
    {
        return (JDFSubmissionMethods) 
        getCreateValidElement(ElementName.SUBMISSIONMETHODS, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element SubmissionMethods
     */
    public JDFSubmissionMethods appendSubmissionMethods()
    {
        return  (JDFSubmissionMethods) appendValidElement(ElementName.SUBMISSIONMETHODS, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFSubmissionMethods getSubmissionMethods(int iSkip)
    {
        return (JDFSubmissionMethods) getValidElement(ElementName.SUBMISSIONMETHODS, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element TrackFilter
     * @param int iSkip number of elements to skip
     * @return JDFTrackFilter The element
     */
    public JDFTrackFilter getCreateTrackFilter(int iSkip)
    {
        return (JDFTrackFilter) 
        getCreateValidElement(ElementName.TRACKFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element TrackFilter
     */
    public JDFTrackFilter appendTrackFilter()
    {
        return (JDFTrackFilter) appendValidElement(ElementName.TRACKFILTER, null);
    }
    
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
     */
    public JDFTrackFilter getTrackFilter(int iSkip)
    {
        return (JDFTrackFilter) getValidElement(ElementName.TRACKFILTER, JDFConstants.EMPTYSTRING, iSkip);
    }
    //////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////
    
    /** get Element TrackResult
     * @param int iSkip number of elements to skip
     * @return JDFTrackResult The element
     */
    public JDFTrackResult getCreateTrackResult(int iSkip)
    {
        return (JDFTrackResult) 
        getCreateValidElement(ElementName.TRACKRESULT, JDFConstants.EMPTYSTRING, iSkip);
    }
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Append element TrackResult
     */
    public JDFTrackResult appendTrackResult()
    {
        return (JDFTrackResult) appendValidElement(ElementName.TRACKRESULT, null);
    }
    /////////////////////////////////////////////////////////////////////
    /**
     *get first element 
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
    
    
    public JDFIDInfo getCreateIDInfo(int iSkip)
    {
        return (JDFIDInfo)getCreateValidElement(ElementName.IDINFO, null, iSkip);
    }
    
    public  JDFIDInfo appendIDInfo()
    {
        return (JDFIDInfo)appendValidElement(ElementName.IDINFO, null);
    }
    
    public JDFIDInfo getIDInfo(int iSkip)
    {
        return (JDFIDInfo)getValidElement(ElementName.IDINFO, null, iSkip);
    }
    
    public JDFFlushedResources getCreateFlushedResources(int iSkip)
    {
        return (JDFFlushedResources)getCreateValidElement(ElementName.FLUSHEDRESOURCES, null, iSkip);
    }
    
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
    
    public String getID()
    {
        return this.getAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING);
    }
    
    public VString getInvalidElements(
            EnumValidationLevel level,
            boolean bIgnorePrivate,
            int nMax)
    {
        int nElem = 0;
        int i = 0;
        boolean bCatch = false;
        VString vElem = super.getInvalidElements(level, bIgnorePrivate, nMax);
        int n = vElem.size();
        if (n >= nMax)
        {
            return vElem;
        }
        
        nElem = numChildElements(ElementName.DEVICE, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFDevice child = null;
            try
            {
                child = (JDFDevice) getValidElement(ElementName.DEVICE, JDFConstants.EMPTYSTRING, i);
            }
            catch(JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level)) 
            {
                vElem.appendUnique("Device");
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        
        nElem = numChildElements(ElementName.DEVICEFILTER, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFDeviceFilter child = null;
            try
            {
                child = (JDFDeviceFilter) getValidElement(ElementName.DEVICEFILTER, JDFConstants.EMPTYSTRING, i);
            }
            catch(JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.DEVICEFILTER);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.DEVICEINFO, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFDeviceInfo child = null;
            try
            {
                child = (JDFDeviceInfo) getValidElement(ElementName.DEVICEINFO, JDFConstants.EMPTYSTRING, i);
            }
            catch(JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.DEVICEINFO);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        
        nElem = numChildElements(ElementName.DEVICELIST, null);
        for (i = 0; i < nElem; i++)
        {
            JDFDeviceList child = null;
            try
            {
                child = (JDFDeviceList)getValidElement(ElementName.DEVICELIST, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.DEVICELIST);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        
        nElem = numChildElements(ElementName.EMPLOYEEDEF, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFEmployeeDef child = null;
            try
            {
                child = (JDFEmployeeDef) getValidElement(ElementName.EMPLOYEEDEF, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.EMPLOYEEDEF);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        
        nElem = numChildElements(ElementName.FLUSHEDRESOURCES, null);
        for (i = 0; i < nElem; i++)
        {
            JDFFlushedResources child = null;
            try
            {
                child = (JDFFlushedResources)getValidElement(ElementName.FLUSHEDRESOURCES, JDFConstants.EMPTYSTRING,i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.FLUSHEDRESOURCES);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.FLUSHQUEUEPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFFlushQueueParams child = null;
            try
            {
                child = (JDFFlushQueueParams)getValidElement(ElementName.FLUSHQUEUEPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.FLUSHQUEUEPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.FLUSHRESOURCEPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFFlushResourceParams child = null;
            try
            {
                child = (JDFFlushResourceParams)getValidElement(ElementName.FLUSHRESOURCEPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.FLUSHRESOURCEPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.IDINFO, null);
        for (i = 0; i < nElem; i++)
        {
            JDFIDInfo child = null;
            try
            {
                child = (JDFIDInfo)getValidElement(ElementName.IDINFO, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.IDINFO);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        
        nElem = numChildElements(ElementName.JDFCONTROLLER, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFJDFController child = null;
            try
            {
                child = (JDFJDFController) getValidElement(ElementName.JDFCONTROLLER, JDFConstants.EMPTYSTRING, i);
            }
            catch(JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.JDFCONTROLLER);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.JDFSERVICE, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFJDFService child = null;
            try
            {
                child = (JDFJDFService) getValidElement(ElementName.JDFSERVICE, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.JDFSERVICE);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.JOBPHASE, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFJobPhase child = null;
            try
            {   
                child = (JDFJobPhase) getValidElement(ElementName.JOBPHASE, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.JOBPHASE);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.KNOWNMSGQUPARAMS, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFKnownMsgQuParams child = null;
            try
            {
                child = (JDFKnownMsgQuParams) getValidElement(ElementName.KNOWNMSGQUPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.KNOWNMSGQUPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.MESSAGESERVICE, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFMessageService child = null;
            try
            {
                child = (JDFMessageService) getValidElement(ElementName.MESSAGESERVICE, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.MESSAGESERVICE);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.MSGFILTER, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFMsgFilter child = null;
            try
            {
                child = (JDFMsgFilter) getValidElement(ElementName.MSGFILTER, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.MSGFILTER);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        
        nElem = numChildElements(ElementName.NEWJDFCMDPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFNewJDFCmdParams child = null;
            try
            {
                child = (JDFNewJDFCmdParams)getValidElement(
                        ElementName.NEWJDFCMDPARAMS,
                        JDFConstants.EMPTYSTRING,
                        i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.NEWJDFCMDPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.NEWJDFQUPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFNewJDFQuParams child = null;
            try
            {
                child = (JDFNewJDFQuParams)getValidElement(
                        ElementName.NEWJDFQUPARAMS,
                        JDFConstants.EMPTYSTRING,
                        i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.NEWJDFQUPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.NODEINFOCMDPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFNodeInfoCmdParams child = null;
            try
            {
                child = (JDFNodeInfoCmdParams)getValidElement(
                        ElementName.NODEINFOCMDPARAMS,
                        JDFConstants.EMPTYSTRING,
                        i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.NODEINFOCMDPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.NODEINFOQUPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFNodeInfoQuParams child = null;
            try
            {
                child = (JDFNodeInfoQuParams)getValidElement(
                        ElementName.NODEINFOQUPARAMS,
                        JDFConstants.EMPTYSTRING,
                        i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.NODEINFOQUPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.NODEINFORESP, null);
        for (i = 0; i < nElem; i++)
        {
            JDFNodeInfoResp child = null;
            try
            {
                child = (JDFNodeInfoResp)getValidElement(ElementName.NODEINFORESP, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.NODEINFORESP);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.NOTIFICATIONDEF, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFNotificationDef child = null;
            try
            {
                child = (JDFNotificationDef) getValidElement(ElementName.NOTIFICATIONDEF, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.NOTIFICATIONDEF);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.NOTIFICATIONFILTER, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFNotificationFilter child = null;
            try
            {
                child = (JDFNotificationFilter) getValidElement(ElementName.NOTIFICATIONFILTER, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.NOTIFICATIONFILTER);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.OCCUPATION, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFOccupation child = null;
            try
            {    
                child = (JDFOccupation) getValidElement(ElementName.OCCUPATION, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.OCCUPATION);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.PIPEPARAMS, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFPipeParams child = null;
            try
            {    
                child = (JDFPipeParams) getValidElement(ElementName.PIPEPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.PIPEPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.QUEUE, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFQueue child = null;
            try
            {
                child = (JDFQueue) getValidElement(ElementName.QUEUE, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.QUEUE);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.QUEUEENTRY, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFQueueEntry child = null;
            try
            {
                child = (JDFQueueEntry) getValidElement(ElementName.QUEUEENTRY, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.QUEUEENTRY);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.QUEUEENTRYDEF, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFQueueEntryDef child = null;
            try
            {
                child = (JDFQueueEntryDef) getValidElement(ElementName.QUEUEENTRYDEF, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.QUEUEENTRYDEF);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        
        nElem = numChildElements(ElementName.QUEUEENTRYDEFLIST, JDFConstants.EMPTYSTRING);
        for(i = 0; i < nElem; i++)
        {
            JDFQueueEntryDefList child = null;
            try 
            {
                child = (JDFQueueEntryDefList)getValidElement(ElementName.QUEUEENTRYDEFLIST, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level)) 
            {
                vElem.appendUnique(ElementName.QUEUEENTRYDEFLIST);
                bCatch = false;
                if (++n >= nMax) 
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        
        nElem = numChildElements(ElementName.QUEUEENTRYPRIPARAMS, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFQueueEntryPriParams child = null;
            try
            {
                child = (JDFQueueEntryPriParams) getValidElement(ElementName.QUEUEENTRYPRIPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.QUEUEENTRYPRIPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.QUEUEENTRYPOSPARAMS, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFQueueEntryPosParams child = null;
            try
            {
                child = (JDFQueueEntryPosParams) getValidElement(ElementName.QUEUEENTRYPOSPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException e)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.QUEUEENTRYPOSPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        
        nElem = numChildElements(ElementName.QUEUEFILTER, null);
        for (i = 0; i < nElem; i++)
        {
            JDFQueueFilter child = null;
            try
            {
                child = (JDFQueueFilter)getValidElement(ElementName.QUEUEFILTER, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.QUEUEFILTER);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        
        nElem = numChildElements(ElementName.QUEUESUBMISSIONPARAMS, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFQueueSubmissionParams child = null;
            try
            {
                child = (JDFQueueSubmissionParams)getValidElement(ElementName.QUEUESUBMISSIONPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.QUEUESUBMISSIONPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        
        nElem = numChildElements(ElementName.REQUESTQUEUEENTRYPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFRequestQueueEntryParams child = null;
            try
            {
                child =(JDFRequestQueueEntryParams) getValidElement(
                        ElementName.REQUESTQUEUEENTRYPARAMS,
                        JDFConstants.EMPTYSTRING,
                        i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.REQUESTQUEUEENTRYPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.RESOURCECMDPARAMS, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFResourceCmdParams child = null;
            try
            {
                child = (JDFResourceCmdParams) getValidElement(ElementName.RESOURCECMDPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.RESOURCECMDPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.RESOURCEINFO, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFResourceInfo child = null;
            try
            {
                child = (JDFResourceInfo) getValidElement(ElementName.RESOURCEINFO, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.RESOURCEINFO);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        
        nElem = numChildElements(ElementName.RESOURCEPULLPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFResourcePullParams child = null;
            try
            {
                child = (JDFResourcePullParams)getValidElement(ElementName.RESOURCEPULLPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.RESOURCEPULLPARAMS);
                bCatch = false;
                if (++n >= nMax)
                    return vElem;
                break;
            }
        }
        
        nElem = numChildElements(ElementName.RESOURCEQUPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFResourceQuParams child = null;
            try
            {
                child = (JDFResourceQuParams)getValidElement(ElementName.RESOURCEQUPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.RESOURCEQUPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.RESUBMISSIONPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFResubmissionParams child = null;
            try
            {
                child = (JDFResubmissionParams)getValidElement(ElementName.RESUBMISSIONPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.RESUBMISSIONPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.RETURNQUEUEENTRYPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFReturnQueueEntryParams child = null;
            try
            {
                child = (JDFReturnQueueEntryParams)getValidElement(ElementName.RETURNQUEUEENTRYPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.RETURNQUEUEENTRYPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.SHUTDOWNCMDPARAMS, null);
        for (i = 0; i < nElem; i++)
        {
            JDFShutDownCmdParams child = null;
            try
            {
                child = (JDFShutDownCmdParams)getValidElement(
                        ElementName.SHUTDOWNCMDPARAMS,
                        JDFConstants.EMPTYSTRING,
                        i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.SHUTDOWNCMDPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {    
                    return vElem; 
                } 
                break;
            }
        }
        
        nElem = numChildElements(ElementName.STATUSQUPARAMS, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFStatusQuParams child = null;
            try
            {
                child = (JDFStatusQuParams) getValidElement(ElementName.STATUSQUPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.STATUSQUPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.STOPPERSCHPARAMS, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFStopPersChParams child = null;
            try
            {
                child = (JDFStopPersChParams) getValidElement(ElementName.STOPPERSCHPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.STOPPERSCHPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.SUBMISSIONMETHODS, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFSubmissionMethods child = null;
            try
            {
                child = (JDFSubmissionMethods) getValidElement(ElementName.SUBMISSIONMETHODS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.SUBMISSIONMETHODS);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.TRACKFILTER, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFTrackFilter child = null;
            try
            {
                child = (JDFTrackFilter) getValidElement(ElementName.TRACKFILTER, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.TRACKFILTER);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.TRACKRESULT, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFTrackResult child = null;
            try
            {
                child = (JDFTrackResult) getValidElement(ElementName.TRACKRESULT, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.TRACKRESULT);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        nElem = numChildElements(ElementName.WAKEUPCMDPARAMS, JDFConstants.EMPTYSTRING);
        for (i = 0; i < nElem; i++)
        {
            JDFTrackResult child = null;
            try
            {
                child = (JDFTrackResult) getValidElement(ElementName.WAKEUPCMDPARAMS, JDFConstants.EMPTYSTRING, i);
            }
            catch (JDFException x)
            {
                bCatch = true;
            }
            if (bCatch || child == null || !child.isValid(level))
            {
                vElem.appendUnique(ElementName.WAKEUPCMDPARAMS);
                bCatch = false;
                if (++n >= nMax)
                {
                    return vElem;
                }
                break;
            }
        }
        return vElem;
    }
    
    /**
     definition of optional elements in the JDF namespace
     */
    // TODO move to elemeInfoTable creation
    public VString optionalElements()
    {
        VString s = super.optionalElements();
        EnumType t = getEnumType();
        // loop over all valid potential elements for this family
        VString vObjs = familyTypeObj();
        // for each object, check whether it is compatible with the type of this
        for (int i = 0; i < vObjs.size(); i++)
        {
            Vector vt = getValidTypeVector((String) vObjs.elementAt(i), 0);
            // is it there ?
            for (int j = 0; j < vt.size(); j++)
            {
                if (vt.elementAt(j).equals(t))
                {
                    // obj x is compatible with this -> add it to the list of elements
                    s.appendUnique(vObjs.stringAt(i));
                    break;
                }
            }
        }
        return s;
    }
    
}
