/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERN
 }ATIONAL COOPERATION FOR
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
 }
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
 * Copyright (c) 2001-2005 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFNodeInfo.java
 *
 * Last changes
 *
 * 02-07-2002  JG - added GetHRefs()
 *
 */
package org.cip4.jdflib.core;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoNodeInfo;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFQuery;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.EnumUtil;
import org.w3c.dom.Node;

/**
 *
 */
public class JDFNodeInfo extends JDFAutoNodeInfo
{
	private static final long serialVersionUID = 1L;
	private static boolean bDefaultWorkStepID = false;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[20];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.CLEANUPDURATION, 0x33333333, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[1] = new AtrInfoTable(AttributeName.DUELEVEL, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumDueLevel.getEnum(0), null);
		atrInfoTable[2] = new AtrInfoTable(AttributeName.END, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[3] = new AtrInfoTable(AttributeName.FIRSTEND, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[4] = new AtrInfoTable(AttributeName.FIRSTSTART, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[5] = new AtrInfoTable(AttributeName.IPPVERSION, 0x33333331, AttributeInfo.EnumAttributeType.XYPair, null, null);
		atrInfoTable[6] = new AtrInfoTable(AttributeName.JOBPRIORITY, 0x33333331, AttributeInfo.EnumAttributeType.integer, null, "50");
		atrInfoTable[7] = new AtrInfoTable(AttributeName.LASTEND, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[8] = new AtrInfoTable(AttributeName.LASTSTART, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[9] = new AtrInfoTable(AttributeName.NATURALLANG, 0x33333331, AttributeInfo.EnumAttributeType.language, null, null);
		atrInfoTable[10] = new AtrInfoTable(AttributeName.MERGETARGET, 0x44444443, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable[11] = new AtrInfoTable(AttributeName.ROUTE, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[12] = new AtrInfoTable(AttributeName.RREFS, 0x44444433, AttributeInfo.EnumAttributeType.IDREFS, null, null);
		atrInfoTable[13] = new AtrInfoTable(AttributeName.SETUPDURATION, 0x33333333, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[14] = new AtrInfoTable(AttributeName.START, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable[15] = new AtrInfoTable(AttributeName.TARGETROUTE, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable[16] = new AtrInfoTable(AttributeName.TOTALDURATION, 0x33333333, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable[17] = new AtrInfoTable(AttributeName.NODESTATUS, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumNodeStatus.getEnum(0), null);
		atrInfoTable[18] = new AtrInfoTable(AttributeName.NODESTATUSDETAILS, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable[19] = new AtrInfoTable(AttributeName.WORKSTEPID, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null); // added
		// as
		// valid
		// for
		// 1.3
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		AttributeInfo ai;

		if (getParentNode().getLocalName().equals(ElementName.JDF))
		{
			ai = super.getTheAttributeInfo_JDFElement().updateReplace(atrInfoTable);
		}
		else
		{
			ai = super.getTheAttributeInfo().updateReplace(atrInfoTable);
		}

		return ai;
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BUSINESSINFO, 0x33333333);
		elemInfoTable[1] = new ElemInfoTable(ElementName.EMPLOYEE, 0x33333333);
		elemInfoTable[2] = new ElemInfoTable(ElementName.JMF, 0x33333333);
		elemInfoTable[3] = new ElemInfoTable(ElementName.MISDETAILS, 0x33333311);
		elemInfoTable[4] = new ElemInfoTable(ElementName.NOTIFICATIONFILTER, 0x33333311);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		ElementInfo ei;

		if (getParentNode().getLocalName().equals(ElementName.JDF))
		{
			ei = new ElementInfo(super.getTheElementInfo_JDFElement(), elemInfoTable);
		}
		else
		{
			ei = new ElementInfo(super.getTheElementInfo(), elemInfoTable);
		}
		return ei;
	}

	/**
	 * Constructor for JDFNodeInfo
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFNodeInfo(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFNodeInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFNodeInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFNodeInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFNodeInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * inner class EnumBusinessObject - printtalk based business objects
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumBusinessObject extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;

		private static int m_startValue = 0;

		private EnumBusinessObject(final String status)
		{
			super(status, m_startValue++);
		}

		/**
		 * @param status
		 * @return
		 */
		public static EnumBusinessObject getEnum(final String status)
		{
			return (EnumBusinessObject) getEnum(EnumBusinessObject.class, status);
		}

		/**
		 * @param value
		 * @return
		 */
		public static EnumBusinessObject getEnum(final int value)
		{
			return (EnumBusinessObject) getEnum(EnumBusinessObject.class, value);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumBusinessObject.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumBusinessObject.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumBusinessObject.class);
		}

		/**
		 * Retrieve all allowed value names of this Enum in a vector
		 * 
		 * @return the <code>String Vector of</code> names
		 * @deprecated
		 */
		@Deprecated
		public static Vector getNamesVector()
		{
			final Vector namesVector = new Vector();
			final Iterator it = iterator(EnumBusinessObject.class);
			while (it.hasNext())
			{
				namesVector.addElement(((ValuedEnum) it.next()).getName());
			}

			return namesVector;
		}

		/**
		 * constants EnumBusinessObject
		 */
		/** * */
		public static final EnumBusinessObject BusinessObject_Unknown = new EnumBusinessObject("BusinessObject_Unknown");
		/** * */
		public static final EnumBusinessObject BusinessObject_RFQ = new EnumBusinessObject("BusinessObject_RFQ");
		/** * */
		public static final EnumBusinessObject BusinessObject_Quote = new EnumBusinessObject("BusinessObject_Quote");
		/** * */
		public static final EnumBusinessObject BusinessObject_RFRequote = new EnumBusinessObject("BusinessObject_RFRequote");
		/** * */
		public static final EnumBusinessObject BusinessObject_Requote = new EnumBusinessObject("BusinessObject_Requote");
		/** * */
		public static final EnumBusinessObject BusinessObject_PO = new EnumBusinessObject("BusinessObject_PO");
		/** * */
		public static final EnumBusinessObject BusinessObject_Confirmation = new EnumBusinessObject("BusinessObject_Confirmation");
		/** * */
		public static final EnumBusinessObject BusinessObject_CO_RFQ = new EnumBusinessObject("BusinessObject_CO_RFQ");
		/** * */
		public static final EnumBusinessObject BusinessObject_CO_Quote = new EnumBusinessObject("BusinessObject_CO_Quote");
		/** * */
		public static final EnumBusinessObject BusinessObject_CO_PO = new EnumBusinessObject("BusinessObject_CO_PO");
		/** * */
		public static final EnumBusinessObject BusinessObject_CO_Confirmation = new EnumBusinessObject("BusinessObject_CO_Confirmation");
		/** * */
		public static final EnumBusinessObject BusinessObject_Invoice = new EnumBusinessObject("BusinessObject_Invoice");
		/** * */
		public static final EnumBusinessObject BusinessObject_None = new EnumBusinessObject("BusinessObject_None");

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
		return "JDFNodeInfo[ --> " + super.toString() + " ]";
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoNodeInfo#init()
	 */
	@Override
	public boolean init()
	{
		final Node n = getParentNode();
		if (bDefaultWorkStepID && !hasAttribute(AttributeName.WORKSTEPID))
		{
			setWorkStepID("W" + uniqueID(0));
		}
		if (n != null && ElementName.RESOURCEPOOL.equals(n.getLocalName()))
		{
			super.init();
			setResStatus(EnumResStatus.Available, false);
			setPartUsage(JDFResource.EnumPartUsage.Implicit);
		}
		return true;
	}

	/**
	 * set the workstepid
	 * 
	 * @param workStep
	 */
	@Override
	public void setWorkStepID(final String workStep)
	{
		setAttribute(AttributeName.WORKSTEPID, workStep, null);
	}

	/**
	 * get the workstepid
	 * 
	 * @return the workstepid
	 * 
	 */
	@Override
	public String getWorkStepID()
	{
		return getAttribute(AttributeName.WORKSTEPID, null, "");
	}

	/**
	 * UpdateBusiness
	 * 
	 * @param businessObject
	 * @param newID
	 * @return
	 */
	public boolean updateBusiness(final EnumBusinessObject businessObject, final String newID)
	{
		final KElement bo = getElement(ElementName.BUSINESSINFO, JDFConstants.EMPTYSTRING, 0);

		final VString vBos = EnumUtil.getNamesVector(EnumBusinessObject.class);
		final KElement boe = bo.getChildFromList(vBos, 0, null, true);
		final String bos = boe.getNodeName();

		final int oldType = vBos.indexOf(bos);

		// System.out.println("JDFNodeInfo:: " + businessObject.getValue() + " Boe:: " + boe);
		boe.renameElement(vBos.elementAt(businessObject.getValue()), null);

		if (businessObject.getValue() > oldType)
		{
			boe.setAttribute(JDFConstants.BUSINESSREFID, boe.getAttribute(JDFConstants.BUSINESSID));

			if (newID.length() != 0)
			{
				boe.setAttribute(JDFConstants.BUSINESSID, newID);
			}
		}

		return true;
	}

	/**
	 * Get the linked resources matching some conditions
	 * 
	 * @param mResAtt map of Resource attributes to search for
	 * @param bFollowRefs true if internal references shall be followed
	 * @return vResource: vector with all elements matching the conditions default: GetLinkedResources(new JDFAttributeMap(), false)
	 */
	public VElement getLinkedResources(final JDFAttributeMap mResAtt, final boolean bFollowRefs)
	{
		final VElement vChild = getChildElementVector(null, null, null, true, 0, false);
		final VElement vElem = new VElement();
		for (int i = 0; i < vChild.size(); i++)
		{
			if (!(vChild.elementAt(i) instanceof JDFRefElement))
			{
				continue;
			}

			final JDFRefElement l = (JDFRefElement) vChild.elementAt(i);
			JDFResource r = l.getTarget();
			r = r == null ? null : r.getResourceRoot();
			if (r != null && r.includesAttributes(mResAtt, true))
			{
				vElem.addElement(r); // vElem.push_back(r);
				if (bFollowRefs)
				{
					vElem.appendUnique(r.getvHRefRes(bFollowRefs, true));
				}
			}
		}
		return vElem;
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * remove any resource specific attribute when making this to an element
	 * 
	 */
	@Override
	public void cleanResourceAttributes()
	{
		removeAttribute(AttributeName.NODESTATUS);
		removeAttribute(AttributeName.NODESTATUSDETAILS);
		super.cleanResourceAttributes();
	}

	/**
	 * @return the bDefaultWorkStepID
	 */
	public static boolean isDefaultWorkStepID()
	{
		return bDefaultWorkStepID;
	}

	/**
	 * if set to true, all newly generated partitions are generated with a unique WorkStepID attribute
	 * 
	 * @param defaultWorkStepID the bDefaultWorkStepID to set
	 */
	public static void setDefaultWorkStepID(final boolean defaultWorkStepID)
	{
		bDefaultWorkStepID = defaultWorkStepID;
	}

	/**
	 * gets the subscription query for a given messagetype or creates one if not yet there note that newly created query do not contain a subscription
	 * 
	 * @param queryType
	 * @return the appropriate query
	 */
	public JDFQuery getCreateJMFQuery(final EnumType queryType)
	{
		JDFQuery q = null;
		final VElement v = getChildElementVector(ElementName.JMF, null);
		if (v != null)
		{
			final int siz = v.size();
			for (int i = 0; i < siz; i++)
			{
				final JDFJMF jmf = (JDFJMF) v.elementAt(i);
				q = (JDFQuery) jmf.getMessageElement(EnumFamily.Query, queryType, 0);
				if (q != null)
				{
					break;
				}
			}
		}

		if (q == null)
		{
			q = appendJMF().appendQuery(queryType);
		}

		return q;
	}

}
