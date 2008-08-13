/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress (CIP4). All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must include the
 * following acknowledgment: "This product includes software developed by the The International
 * Cooperation for the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)"
 * Alternately, this acknowledgment may appear in the software itself, if and wherever such
 * third-party acknowledgments normally appear.
 * 
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress" must not be used to endorse or promote products derived from this
 * software without prior written permission. For written permission, please contact info@cip4.org.
 * 
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their
 * name, without prior written permission of the CIP4 organization
 * 
 * Usage of this software in commercial products is subject to restrictions. For details please
 * consult info@cip4.org.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN
 * PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The
 * International Cooperation for the Integration of Processes in Prepress, Press and Postpress and
 * was originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 *//**
 *
 * Copyright (c) 2001-2005 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFResourceLink.java
 *
 * Last changes 2002-07-02 JG - added Get/SetProcessUsage 2002-07-02 JG - MyString -> KString :
 * all strings now 16 bit 2002-07-02 JG - now inherits from JDFElement 2002-07-02 JG -
 * GetProcessUsage and GetLinkedResourceName are now 2 sepaarte functions 2002-07-02 JG -
 * completely removed selector handling 2002-07-02 JG - HasResourcePartMap bug fix if no parts
 * in this - now returns true for no parts in this 2002-07-02 JG - removed JDFResource
 * GetPartition(boolean bCreate=false, int i=0); 2002-07-02 JG - added AppendPart 2002-07-02 JG -
 * added CombinedProcessIndex, PipeProtocol support 2002-07-02 JG - added AmountPool 2002-07-02
 * JG - added Transformation + Orientation support 2002-07-02 JG - removed GetAmount(boolean
 * bSelector) 2002-07-02 JG - removed GetPartTarget(int iPart=0,int iSelector=-1); 2002-07-02 JG -
 * modified GetNamedProcessUsage to default to xxx:Input / xxx:Output respectively 2002-07-02 JG -
 * SetPartition() now uses JDFResource::EnumPartIDKey 2002-07-02 JG - added GetTarget 2002-07-02
 * JG - GetTargetVector is now const 2002-07-02 JG - added GetTarget() 22-10-2003 KM -
 * IsExecutable() added bCheckChildren 22-10-2003 KM - IsExecutable() fixed bCheckChildren
 * 22-10-2003 KM - GetTarget() now returns the lowest common denominator resource if all leaves
 * are available
 */

package org.cip4.jdflib.core;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.IAmountPoolContainer;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.CombinedProcessIndexHelper;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFAmountPool.AmountPoolHelper;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFLot;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;

public class JDFResourceLink extends JDFElement implements IAmountPoolContainer
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.core.JDFElement#getInvalidAttributes(org.cip4.jdflib. core.KElement.EnumValidationLevel,
	 * boolean, int)
	 */
	@Override
	public VString getInvalidAttributes(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{
		final VString v = super.getInvalidAttributes(level, bIgnorePrivate, nMax);
		if (!v.contains(AttributeName.COMBINEDPROCESSINDEX) && !validCombinedProcessIndex())
			v.add(AttributeName.COMBINEDPROCESSINDEX);
		return v;
	}

	public void generateCombinedProcessIndex()
	{
		JDFNode n = getParentJDF();
		if (n != null)
		{
			VString types = n.getTypes();
			if (types != null)
				CombinedProcessIndexHelper.generateCombinedProcessIndex(getLinkRoot(), getUsage(), getEnumProcessUsage(), this, types);
		}
	}

	/**
	 * @param v
	 */
	public boolean validCombinedProcessIndex()
	{
		final JDFIntegerList vCombined = getCombinedProcessIndex();
		if (vCombined == null)
			return true;
		final JDFNode parentJDF = getParentJDF();
		if (parentJDF == null)
		{
			return false;
		}
		final VString types = parentJDF.getTypes();
		if (types == null)
		{
			return false;
		}
		final int typSize = types.size();
		final int size = vCombined.size();
		for (int i = 0; i < size; i++)
		{
			final int combinedIndex = vCombined.getInt(i);
			if (combinedIndex < 0 || combinedIndex >= typSize)
			{
				return false;
			}
		}
		return true;
	}

	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable_Abstract = new AtrInfoTable[12];
	static
	{
		atrInfoTable_Abstract[0] = new AtrInfoTable(AttributeName.COMBINEDPROCESSINDEX, 0x33333331, AttributeInfo.EnumAttributeType.IntegerList, null, null);
		atrInfoTable_Abstract[1] = new AtrInfoTable(AttributeName.COMBINEDPROCESSTYPE, 0x44444443, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable_Abstract[2] = new AtrInfoTable(AttributeName.DRAFTOK, 0x44444333, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable_Abstract[3] = new AtrInfoTable(AttributeName.MINLATESTATUS, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumResStatus.getEnum(0), null);
		atrInfoTable_Abstract[4] = new AtrInfoTable(AttributeName.MINSTATUS, 0x33333111, AttributeInfo.EnumAttributeType.enumeration, EnumResStatus.getEnum(0), null);
		atrInfoTable_Abstract[5] = new AtrInfoTable(AttributeName.PIPEPARTIDKEYS, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, EnumPartIDKey.getEnum(0), null);
		atrInfoTable_Abstract[6] = new AtrInfoTable(AttributeName.PIPEPROTOCOL, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
		atrInfoTable_Abstract[7] = new AtrInfoTable(AttributeName.PIPEURL, 0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
		atrInfoTable_Abstract[8] = new AtrInfoTable(AttributeName.PROCESSUSAGE, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
		atrInfoTable_Abstract[9] = new AtrInfoTable(AttributeName.RREF, 0x22222222, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable_Abstract[10] = new AtrInfoTable(AttributeName.RSUBREF, 0x44444433, AttributeInfo.EnumAttributeType.IDREF, null, null);
		atrInfoTable_Abstract[11] = new AtrInfoTable(AttributeName.USAGE, 0x22222222, AttributeInfo.EnumAttributeType.enumeration, EnumUsage.getEnum(0), null);
	}

	private static AtrInfoTable[] atrInfoTable_Physical = new AtrInfoTable[10];
	static
	{
		atrInfoTable_Physical[1] = new AtrInfoTable(AttributeName.ACTUALAMOUNT, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable_Physical[0] = new AtrInfoTable(AttributeName.AMOUNT, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable_Physical[9] = new AtrInfoTable(AttributeName.MAXAMOUNT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable_Physical[8] = new AtrInfoTable(AttributeName.MINAMOUNT, 0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable_Physical[3] = new AtrInfoTable(AttributeName.ORIENTATION, 0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumOrientation.getEnum(0), null);
		atrInfoTable_Physical[4] = new AtrInfoTable(AttributeName.PIPEPAUSE, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable_Physical[5] = new AtrInfoTable(AttributeName.PIPERESUME, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable_Physical[6] = new AtrInfoTable(AttributeName.REMOTEPIPEENDPAUSE, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable_Physical[7] = new AtrInfoTable(AttributeName.REMOTEPIPEENDRESUME, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
		atrInfoTable_Physical[2] = new AtrInfoTable(AttributeName.TRANSFORMATION, 0x33333331, AttributeInfo.EnumAttributeType.matrix, null, null);
	}

	private static AtrInfoTable[] atrInfoTable_Implement = new AtrInfoTable[4];
	static
	{
		atrInfoTable_Implement[0] = new AtrInfoTable(AttributeName.DURATION, 0x33333333, AttributeInfo.EnumAttributeType.duration, null, null);
		atrInfoTable_Implement[1] = new AtrInfoTable(AttributeName.RECOMMENDATION, 0x44444433, AttributeInfo.EnumAttributeType.boolean_, null, null);
		atrInfoTable_Implement[2] = new AtrInfoTable(AttributeName.START, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
		atrInfoTable_Implement[3] = new AtrInfoTable(AttributeName.STARTOFFSET, 0x33333333, AttributeInfo.EnumAttributeType.duration, null, null);
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		final AttributeInfo ai = super.getTheAttributeInfo().updateReplace(atrInfoTable_Abstract);
		if (isPhysical() || getLocalName().equals(ElementName.PARTAMOUNT))
		{
			ai.updateAdd(atrInfoTable_Physical);
		}
		else if (isImplementation() || getLocalName().equals(ElementName.PARTAMOUNT))
		{
			ai.updateAdd(atrInfoTable_Implement);
		}
		return ai;
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.AMOUNTPOOL, 0x66666661);
		elemInfoTable[1] = new ElemInfoTable(ElementName.PART, 0x33333333);
	}

	private static ElemInfoTable[] physInfoTable = new ElemInfoTable[1];
	static
	{
		physInfoTable[0] = new ElemInfoTable(ElementName.LOT, 0x33333111);
	}

	// //////////////////////////////////////////////////////////////

	@Override
	protected ElementInfo getTheElementInfo()
	{
		final ElementInfo ei = super.getTheElementInfo().updateReplace(elemInfoTable);
		if (this.isPhysical())
			ei.updateAdd(physInfoTable);
		return ei;
	}

	// //////////////////////////////////////////////////////////////

	/**
	 * Constructor for JDFResourceLink
	 * 
	 * @param myOwnerDocument owner document
	 * @param qualifiedName qualified name
	 */
	public JDFResourceLink(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFResourceLink
	 * 
	 * @param myOwnerDocument owner documen
	 * @param myNamespaceURI namespace URI
	 * @param qualifiedName qualified name
	 */
	public JDFResourceLink(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFResourceLink
	 * 
	 * @param myOwnerDocument owner documen
	 * @param myNamespaceURI namespace URI
	 * @param qualifiedName qualified name
	 * @param myLocalName local name
	 */
	public JDFResourceLink(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * Enumeration for Orientation
	 */
	public static final class EnumOrientation extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;

		private static int m_startValue = 0;

		private EnumOrientation(final String name)
		{
			super(name, m_startValue++);
		}

		public static EnumOrientation getEnum(final String enumName)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumName);
		}

		public static EnumOrientation getEnum(final int enumValue)
		{
			return (EnumOrientation) getEnum(EnumOrientation.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumOrientation.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumOrientation.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumOrientation.class);
		}

		public static final EnumOrientation Flip0 = new EnumOrientation("Flip0");

		public static final EnumOrientation Flip90 = new EnumOrientation("Flip90");

		public static final EnumOrientation Flip180 = new EnumOrientation("Flip180");

		public static final EnumOrientation Flip270 = new EnumOrientation("Flip270");

		public static final EnumOrientation Rotate0 = new EnumOrientation("Rotate0");

		public static final EnumOrientation Rotate90 = new EnumOrientation("Rotate90");

		public static final EnumOrientation Rotate180 = new EnumOrientation("Rotate180");

		public static final EnumOrientation Rotate270 = new EnumOrientation("Rotate270");

		public static final EnumOrientation Matrix = new EnumOrientation("Matrix");
	}

	/**
	 * Enumeration for attribute Usage
	 */
	public static final class EnumUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;

		private static int m_startValue = 0;

		private EnumUsage(final String name)
		{
			super(name, m_startValue++);
		}

		public static EnumUsage getEnum(final String enumName)
		{
			return (EnumUsage) getEnum(EnumUsage.class, enumName);
		}

		public static EnumUsage getEnum(final int enumValue)
		{
			return (EnumUsage) getEnum(EnumUsage.class, enumValue);
		}

		public static Map getEnumMap()
		{
			return getEnumMap(EnumUsage.class);
		}

		public static List getEnumList()
		{
			return getEnumList(EnumUsage.class);
		}

		public static Iterator iterator()
		{
			return iterator(EnumUsage.class);
		}

		public static final EnumUsage Input = new EnumUsage("Input");

		public static final EnumUsage Output = new EnumUsage("Output");

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
		return "JDFResourceLink[ --> " + super.toString() + " ]";
	}

	/**
	 * version fixing routine for JDF
	 * 
	 * uses heuristics to modify this element and its children to be compatible with a given version<br>
	 * in general, it will be able to move from low to high versions, but potentially fail when attempting to move from
	 * higher to lower versions
	 * 
	 * @param version version that the resulting element should correspond to
	 * @return true if successful
	 */
	@Override
	public boolean fixVersion(final EnumVersion version)
	{
		final boolean bRet = true;
		if (version != null)
		{
			if (version.getValue() >= EnumVersion.Version_1_3.getValue())
			{
				if (hasAttribute(AttributeName.DRAFTOK))
				{
					if (!hasAttribute(AttributeName.MINSTATUS))
						setMinStatus(EnumResStatus.Draft);
					removeAttribute(AttributeName.DRAFTOK);
				}
			}
			else
			{
				if (hasAttribute(AttributeName.MINSTATUS))
				{
					if (!hasAttribute(AttributeName.DRAFTOK))
						setDraftOK(true);
					removeAttribute(AttributeName.MINSTATUS);
				}
				removeAttribute(AttributeName.MINLATESTATUS);
			}
		}
		return super.fixVersion(version) && bRet;

	}

	/**
	 * setTarget - sets the link to the target defined by partLeaf. Automatically generates a part subelement, if
	 * partleaf is not the root resource
	 * 
	 * @param resourceTarget the resource that this ResourceLink shoud refer to
	 * 
	 * @throws JDFException if an attempt is made to link to a resource sub-element
	 * @return boolean - always true
	 */
	public boolean setTarget(final JDFResource resourceTarget)
	{
		if (resourceTarget.isResourceElement())
			throw new JDFException("attempting to link to a resource subelement");

		appendHRef(resourceTarget.getResourceRoot(), JDFConstants.RREF, null);

		if (!resourceTarget.isResourceRoot())
		{
			removeChildren(ElementName.PART, null, null);
			final JDFAttributeMap mPart = resourceTarget.getPartMap();
			if (mPart != null && mPart.size() > 0)
			{
				final JDFElement part = appendPart();
				part.setAttributes(mPart);
			}
		}

		return true;
	}

	/**
	 * get double attribute Amount, defaults to the value of Amount for the linked partition
	 * 
	 * @param mPart partition map to retrieve Amount for
	 * @return the amount, -1 if none is specified
	 * 
	 * @default getAmount(null)
	 */
	public double getAmount(final JDFAttributeMap mPart)
	{
		return AmountPoolHelper.getAmount(this, mPart);
	}

	/**
	 * get double attribute MinAmount, defaults to getAmount if MinAmount is not set
	 * 
	 * @param mPart partition map to retrieve MinAmount for
	 * @return the MinAmount value
	 * @default getAmount(null)
	 */
	public double getMinAmount(final JDFAttributeMap mPart)
	{
		return AmountPoolHelper.getMinAmount(this, mPart);
	}

	/**
	 * get double attribute MaxAmount, defaults to getAmount if MinAmount is not set
	 * 
	 * @param mPart partition map to retrieve MaxAmount for
	 * @return the MaxAmount value
	 * @default getAmount(null)
	 */
	public double getMaxAmount(final JDFAttributeMap mPart)
	{
		return AmountPoolHelper.getMaxAmount(this, mPart);
	}

	/**
	 * getLinkRoot - gets the root resource of the target
	 * 
	 * @return JDFResource
	 */
	public JDFResource getLinkRoot()
	{
		final JDFResource eLink = super.getLinkRoot(null);
		if (eLink != null)
		{
			if (!(eLink.getNodeName().equals(getLinkedResourceName())))
			{
				return null;
			}
		}
		return eLink;
	}

	/**
	 * getLinkTarget
	 * 
	 * @return JDFResource
	 * @deprecated never used
	 */
	@Deprecated
	public JDFResource getLinkTarget()
	{
		return getTarget();
	}

	/**
	 * setQuantity
	 * 
	 * @param quant
	 */
	public void setQuantity(final int quant)
	{
		setAttribute(AttributeName.AMOUNT, quant, null);
	}

	/**
	 * setAmount in PartAmount or in this if partAmount=null
	 * 
	 * @param value amount to set
	 * @param mPart partition map to set amount for
	 * 
	 * @default setAmount(double value, null)
	 */
	public void setAmount(final double value, final JDFAttributeMap mPart)
	{
		setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(value), null, mPart);
	}

	/**
	 * set MinAmount in PartAmount or in this if partAmount=null
	 * 
	 * @param value amount to set
	 * @param mPart partition map to set amount for
	 * 
	 * @default setAmount(double value, null)
	 */
	public void setMinAmount(final double value, final JDFAttributeMap mPart)
	{
		setAmountPoolAttribute(AttributeName.MINAMOUNT, StringUtil.formatDouble(value), null, mPart);
	}

	/**
	 * set MaxAmount in PartAmount or in this if partAmount=null
	 * 
	 * @param value amount to set
	 * @param mPart partition map to set amount for
	 * 
	 * @default setAmount(double value, null)
	 */
	public void setMaxAmount(final double value, final JDFAttributeMap mPart)
	{

		setAmountPoolAttribute(AttributeName.MAXAMOUNT, StringUtil.formatDouble(value), null, mPart);
	}

	/**
	 * get the status of the Resource that is linked by this link
	 * 
	 * @return JDFResource.EnumResStatus
	 */
	public JDFResource.EnumResStatus getStatusJDF()
	{
		return JDFResource.EnumResStatus.getEnum(getLinkRoot().getResStatus(false).getName());
	}

	/**
	 * set the status of the Resource that is linked by this link
	 * 
	 * @param s value to set
	 */
	public void setStatus(final JDFResource.EnumResStatus s)
	{
		final VElement targets = getTargetVector(-1);
		for (int i = 0; i < targets.size(); i++)
		{
			((JDFResource) targets.elementAt(i)).setResStatus(s, true);
		}
	}

	/**
	 * check whether the resource is in the same node as the link
	 * 
	 * @return true, if the linked resource resides in the same node
	 */
	public boolean isLocal()
	{
		final JDFElement linkParent = getParentJDF();
		final JDFElement resParent = getTarget().getParentJDF();
		return resParent.isEqual(linkParent);
	}

	/**
	 * get first Part element beyond i
	 * 
	 * @param i number of elements to skip
	 * 
	 * @return JDFResource
	 * 
	 * @default getPart(0)
	 */
	public JDFPart getPart(final int i)
	{
		return (JDFPart) getElement(ElementName.PART, null, i);
	}

	/**
	 * get element Part, create if it does not exist
	 * 
	 * @param i number of elements to skip
	 * 
	 * @return JDFResource
	 * 
	 * @default getCreatePart(0)
	 */
	public JDFPart getCreatePart(final int i)
	{
		return (JDFPart) getCreateElement_KElement(ElementName.PART, null, i);
	}

	/**
	 * get element Audit
	 * 
	 * @return String
	 */
	public String getAuditString()
	{
		final String s = getNodeName();
		return s.substring(3, s.length()) + JDFConstants.AUDIT;
	}

	/**
	 * getParts - get the vector of part elements, note that a resource link with multiple part elements is effectively
	 * an OR of these parts
	 * 
	 * @return VElement
	 */
	public VElement getParts()
	{
		return getChildElementVector(ElementName.PART, null, null, true, 0, false);
	}

	/**
	 * setPart - shorthand if only one part is required, should be set to key = value
	 * 
	 * @param key the partition key
	 * @param value the partition value
	 */
	public void setPart(final String key, final String value)
	{
		final JDFPart part = getCreatePart(0);
		part.setAttribute(key, value, null);
	}

	/**
	 * shorthand if only one part is required, should be set to key = value
	 * 
	 * @param key the partition key
	 * @param value the partition value
	 */
	public void setPartition(final JDFResource.EnumPartIDKey key, final String value)
	{
		while (hasChildElement(ElementName.PART, null))
		{
			removePart(0);
		}

		final JDFPart part = getCreatePart(0);
		part.setAttribute(key.getName(), value, null);
	}

	/**
	 * remove element Part
	 * 
	 * @param iSkip number of elements to skip
	 * 
	 * @default removePart(0)
	 */
	public void removePart(final int iSkip)
	{
		removeChild(ElementName.PART, null, iSkip);
	}

	/**
	 * isExecutable - checks whether the resource link links to a resource, which is in a state that will allow a node
	 * to execute
	 * 
	 * @param partMap the attribute map of parts
	 * @param bCheckChildren if true, calculates the availability status of a resource from all child partition leaves,
	 *            else the status is taken from the appropriate leaf itself
	 * 
	 * @return boolean - true if the node is executable, false if not
	 * 
	 * @default isExecutable(null, true)
	 */
	public boolean isExecutable(final JDFAttributeMap partMap, final boolean bCheckChildren)
	{
		if (!hasResourcePartMap(partMap, false))
		{
			return false;
		}

		final VElement leaves = new VElement();
		boolean bExec = false;

		if (bCheckChildren)
		{
			final VElement leaves2 = getTargetVector(-1);
			for (int i = 0; i < leaves2.size(); i++)
			{
				final JDFResource p = (JDFResource) leaves2.elementAt(i);
				if (p == null)
				{
					continue;
				}
				final VElement targetVector = p.getLeaves(false);
				leaves.addAll(targetVector);
			}
		}

		else
		{ // calculate availability directly, but only for the subelements as
			// specified by partMap
			final VElement leaves2 = getTargetVector(-1);
			for (int i = 0; i < leaves2.size(); i++)
			{
				JDFResource leaf = (JDFResource) leaves2.elementAt(i);
				leaf = leaf.getPartition(partMap, null);
				if (leaf != null)
					leaves.add(leaf);
			}
		}
		leaves.unify();

		for (int i = 0; i < leaves.size(); i++)
		{
			final JDFResource leaf = (JDFResource) leaves.elementAt(i);
			if (partMap != null && !partMap.isEmpty() && !partMap.overlapMap(leaf.getPartMap()))
				continue;

			final JDFResource.EnumResStatus status = leaf.getResStatus(true);
			if (status.equals(JDFResource.EnumResStatus.InUse))
			{
				return false;
			}

			bExec = getMinStatus().getValue() <= status.getValue();
			// any leaf not executable --> the whole thing is not executable
			if (!bExec)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * get the parent ResourceLinkPool
	 * 
	 * @return JDFResourceLinkPool - the parent ResourceLinkPool
	 */
	protected JDFResourceLinkPool getResourceLinkPool()
	{
		if (getParentNode_KElement() != null)
		{
			return (JDFResourceLinkPool) getParentNode_KElement();
		}

		return null;
	}

	/**
	 * gets the first resource leaf that this resourcelink refers to<br>
	 * see the description of {@link #getTargetVector(int) getTargetVector} for details
	 * 
	 * @since 102103 GetTarget returns the lowest common denominator if all children of a resource are referenced
	 * @return JDFResource - the first leaf that is referenced by this ResourceLink
	 */
	@Override
	public JDFResource getTarget()
	{
		final VElement v = getTargetVector(-1);
		if (v == null)
		{
			return null;
		}
		return (JDFResource) v.getCommonAncestor();
	}

	/**
	 * Method getTargetVector gets the resource nodes this resourcelink refers to. Skips links that do not exist or
	 * where the name mangling is illegal.<br>
	 * Actual behavior varies according to the value of PartUsage of the referenced resource:<br>
	 * if PartUsage="Explicit", all elements that are referenced in PartIDKeys and the ResourceLink must exist and fit<br>
	 * if PartUsage="Implicit", the best fitting intermediate node of the partitioned resource is returned.<br>
	 * Attributes in the Part elements, that are not referenced in PartIDKeys, are assumed to be logical attributes
	 * (e.g. RunIndex of a RunList) and ignored when searching the part.
	 * 
	 * @param nMax maximum number of requested resources; -1= all
	 * 
	 * @return VElement - the set of leaves that are referenced by this ResourceLink
	 * 
	 * @default getTargetVector(-1)
	 */
	public VElement getTargetVector(final int nMax)
	{
		// split it into leaves
		// 221003 changed GetResourcePartMapVector to GetPartMapVector
		final VJDFAttributeMap vmParts = getPartMapVector();
		return getMapTargetVector(vmParts, nMax);
	}

	/**
	 * Gets the resource nodes this resourcelink refers to. Skips links that do not exist or where the name mangling is
	 * illegal.<br>
	 * Actual behavior varies according to the value of PartUsage of the referenced resource:<br>
	 * if PartUsage="Explicit", all elements that are referenced in PartIDKeys and the ResourceLink must exist and fit<br>
	 * if PartUsage="Implicit", the best fitting intermediate node of the partitioned resource is returned.<br>
	 * Attributes in the Part elements, that are not referenced in PartIDKeys, are assumed to be logical attributes
	 * (e.g. RunIndex of a RunList) and ignored when searching the part.
	 * 
	 * @param vmParts target map to use
	 * @param nMax maximum number of requested resources; -1= all
	 * @return VElement the set of leaves that are referenced by this ResourceLink
	 */
	private VElement getMapTargetVector(final VJDFAttributeMap vmParts, final int nMax)
	{
		final VElement v = new VElement();
		// get the resource root
		final JDFResource resRoot = getLinkRoot();
		if (resRoot == null)
		{
			return v;
		}

		if (vmParts == null || vmParts.isEmpty())
		{
			v.addElement(resRoot);
			return v;
		}
		// get the value of PartUsage
		final JDFResource.EnumPartUsage partUsage = resRoot.getPartUsage();

		if (partUsage.equals(JDFResource.EnumPartUsage.Implicit))
		{
			vmParts.reduceMap(resRoot.getPartIDKeys().getSet());
		}
		if (vmParts.isEmpty())
		{
			v.addElement(resRoot);
			return v;
		}

		final int partSize = vmParts.size();
		for (int i = 0; i < partSize; i++)
		{
			final VElement vr = resRoot.getPartitionVector(vmParts.elementAt(i), partUsage);
			if (vr != null && !vr.isEmpty())
			{
				v.addAll(vr);
				// we have enough!
				if (v.size() == nMax)
				{
					break;
				}
			}
		}
		return v;
	}

	/**
	 * get the parent ResourceLinkPool
	 * 
	 * @return the parent ResourceLinkPool
	 */
	public JDFPool getPool()
	{
		final KElement deepParentNotName = getDeepParentNotName(getLocalName());
		if (deepParentNotName != null)
		{
			return (JDFPool) deepParentNotName;
		}
		return null;
	}

	/**
	 * checks whether a given partMap is compatible with this link
	 * 
	 * @param partMap the map of parts that this link is compared to
	 * @param bCheckResource if true, also recurse into the resource and check if the parts exist
	 * 
	 * @return boolean - true if this is compatible with partMap
	 * 
	 * @default HasResourcePartMap(partMap, false)
	 */
	public boolean hasResourcePartMap(final JDFAttributeMap partMap, boolean bCheckResource)
	{
		// TODO remove implicit partitions
		// Attention !!!
		// Don't change this method without checking if routing is still working
		// !
		// The C++ method is different and is not used, the java method is used
		// for routing.
		final JDFResource linkRoot = getLinkRoot();
		if (linkRoot == null)
			return false;

		VJDFAttributeMap vPart;
		final EnumPartUsage partUsage = linkRoot.getPartUsage();
		final boolean bImplicit = JDFResource.EnumPartUsage.Implicit.equals(partUsage);
		if (bCheckResource)
		{
			if ((partMap == null || partMap.isEmpty()))
			{
				return true;
			}
			final JDFResource partition = linkRoot.getPartition(partMap, partUsage);
			if (partition != null && partition != linkRoot)
			{
				return true;
			}
			else if (bImplicit)
			{
				final KElement childByTagName = linkRoot.getElement(linkRoot.getNodeName());
				if (childByTagName == null)
				{
					// there is no partition
					return true;
				}
			}
		}
		else
		{
			vPart = getPartMapVector();
			if ((partMap == null || partMap.isEmpty()) && (vPart == null || vPart.isEmpty()))
			{
				return true;
			}

			final int siz = vPart == null ? 0 : vPart.size();
			if (bImplicit)
			{
				if (siz == 0)
				{
					return true;
				}
				for (int i = 0; i < siz; i++)
				{
					if (vPart.elementAt(i).overlapMap(partMap))
						return true;
				}
			}
			else
			{ // explicit
				if (siz == 0 && !bCheckResource)
				{
					return true;
				}

				for (int i = 0; i < siz; i++)
				{
					final JDFAttributeMap part = vPart.elementAt(i);
					if (part == null || part.size() == 0)
						return true;

					// RP 050120 swap of vPart[i] and partmap
					// RP 070511 swap back of vPart[i] and partmap
					if (part.subMap(partMap))
						return true;
				}
			}
		}

		return false;
	}

	/**
	 * @param partMap
	 * @return boolean
	 */
	public boolean overlapsResourcePartMap(final JDFAttributeMap partMap)
	{
		if (partMap.isEmpty())
		{
			return true; // speed up...
		}

		final VJDFAttributeMap vPart = getPartMapVector();

		final int siz = vPart == null ? 0 : vPart.size();
		// if no part, any resource that is linked is valid
		if (siz == 0)
		{
			return true;
		}

		for (int i = 0; i < siz; i++)
		{
			if (vPart.elementAt(i).overlapMap(partMap))
				return true;
		}

		return false;
	}

	/**
	 * Check whether a resource is selected by a ResourceLink.<br>
	 * A resource is selected if all (partition) leaves are selected by the resource link
	 * 
	 * @param resourceToCheck The resource which may be selected by the ResourceLink.
	 * 
	 *            This ResourceLink must always be the full ResourceLink, i.e. Part Elements are not allowed as
	 *            parameters.
	 * 
	 * @return true, if the resource link selects the resource
	 */
	public boolean isResourceSelected(final JDFResource resourceToCheck)
	{
		// For the decision, compare the leaves of the Resource with the Leaves
		// pointed to by the
		// resource link. If all leaves of the Resource are pointed to by the
		// ResourceLink, then the
		// ResourceLink selects the Resource (partition). This method checks if
		// the leaves
		// represented by the
		// Resource are a subset of the leaves represented by the ResourceLink
		boolean b_ResurceIsSelected = false;

		// get the resource leaves from resource and resource link
		final VElement resourceLeavesFromResource = resourceToCheck.getLeaves(false);
		final VElement resourceLeavesFromLink = getTargetVector(-1);

		// number of resources found
		final int i_NoResourceLeavesFromResource = resourceLeavesFromResource.size();
		final int i_NoResourceLeavesFromLink = resourceLeavesFromLink.size();

		// compare Resource Vectors if they contain the same Resources (here
		// ResourceLeaves)
		// Ordering
		// of verctors is not important
		// Note: a method vResource::IsSubSet(vResource) would help here; the
		// following is an
		// implementation
		// for this

		int i_CurrentLeafFromResource = 0;
		int i_CurrentLeafFromLink = 0;

		// look if every Resource leaf from the ResourceLeavesFromResource is in
		// the
		// ResourceLeavesFromLink
		// vector
		boolean b_SelectionIsPossible = true;
		while (b_SelectionIsPossible && i_CurrentLeafFromResource < i_NoResourceLeavesFromResource)
		{
			// get ResourceLeaf from Resource Vector to compare
			final JDFResource currentLeafFromResource = (JDFResource) resourceLeavesFromResource.elementAt(i_CurrentLeafFromResource);

			// compare with ResourceLeaf in ResourceLink vector till the
			// Resource is found
			// iterate the vector with leaves from ResourceLinks
			boolean b_ResourceFoundInLink = false;
			while (!b_ResourceFoundInLink && i_CurrentLeafFromLink < i_NoResourceLeavesFromLink)
			{
				final JDFResource currentLeafFromLink = (JDFResource) resourceLeavesFromLink.elementAt(i_CurrentLeafFromLink);
				b_ResourceFoundInLink = currentLeafFromResource == currentLeafFromLink;
				i_CurrentLeafFromLink++;
			}

			// if value is false, one partition is not selected => whole
			// resource is not selected
			b_SelectionIsPossible = b_ResourceFoundInLink;
			i_CurrentLeafFromResource++;
		}

		b_ResurceIsSelected = b_SelectionIsPossible;

		return b_ResurceIsSelected;
	}

	/**
	 * get part map vector as defined by the linked resource. This returns the vector of leaves that would be returned.
	 * 
	 * @return vector of mAttribute, one for each part
	 */
	public VJDFAttributeMap getResourcePartMapVector()
	{
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		final VJDFAttributeMap vPartMap = getPartMapVector();
		final int nPartChildren = vPartMap == null ? 0 : vPartMap.size();
		final JDFResource root = getLinkRoot();
		final VElement leaves = root.getLeaves(false);
		// loop over resource leaves
		for (int i = 0; i < leaves.size(); i++)
		{
			final JDFAttributeMap leafMap = ((JDFResource) leaves.elementAt(i)).getPartMap();
			if (nPartChildren > 0)
			{ // it's reduced
				for (int j = 0; j < nPartChildren; j++)
				{
					final JDFAttributeMap mPart = vPartMap.elementAt(j);
					if (!mPart.overlapMap(leafMap))
					{
						continue;
					}
					// got one; break both loops and continue with the next leaf
					vMap.addElement(leafMap.orMap(mPart));
				}
			}
			else
			{
				// no parts in the resourcelink -> simply append the resources
				// partmap
				vMap.addElement(leafMap);
			}
		}
		return vMap.size() == 0 ? null : vMap;
	}

	/**
	 * Returns the linked resource name
	 * 
	 * @return String - the name
	 */
	public String getLinkedResourceName()
	{
		final String nodeName = getNodeName();
		final int length = nodeName.length();
		if (length <= 4)
			throw new JDFException("invalid nodename for resource link");
		return nodeName.substring(0, length - JDFConstants.LINK.length());

	}

	/**
	 * Get the expected name of the linked resource and an optional processusage in name:usage format. If no
	 * processusage is available, return GetLinkedResourceName:input / GetLinkedResourceName:output respectively.
	 * 
	 * @return String
	 */
	public String getNamedProcessUsage()
	{
		String s = getProcessUsage();
		// 030502 RP modified to default tx xxx:Input / xxx:Output respectively
		if (isWildCard(s))
		{
			// 200602 RP need the string type - don't cycle to and from enum
			// type...
			s = getAttribute(AttributeName.USAGE, null, JDFConstants.EMPTYSTRING);

		}

		s = getLinkedResourceName() + JDFConstants.COLON + s;

		return s;
	}

	/**
	 * checks whether the resource lives in the same node or an ancestor node of the link
	 * 
	 * @return true, if the linked resource resides in a legal node
	 */
	public boolean validResourcePosition()
	{
		return validResourcePosition(getLinkRoot());
	}

	/**
	 * default validator
	 * 
	 * @param level validation level
	 * @see org.cip4.jdflib.core.JDFElement#isValid(org.cip4.jdflib.core.KElement.EnumValidationLevel)
	 */
	@Override
	public boolean isValid(EnumValidationLevel level)
	{
		if (level == null)
			level = EnumValidationLevel.Complete;

		boolean b = super.isValid(level);
		if (!b)
			return false;

		if (this instanceof JDFPartAmount)
			return true;

		if ((level != EnumValidationLevel.Complete) && (level != EnumValidationLevel.Incomplete)
				&& (level != EnumValidationLevel.RecursiveIncomplete))
			return true;

		if (!validResourcePosition())
			return false;

		final VElement vRes = getTargetVector(0);
		if ((vRes == null || vRes.isEmpty())
				&& ((level == EnumValidationLevel.Complete) || (level == EnumValidationLevel.RecursiveComplete)))
		{
			// if any partition points to nirvana and we are validating
			// complete, the entire resource is invalid
			return false;
		}
		if (level.equals(EnumValidationLevel.Complete))
			return true;

		for (int iRes = 0; iRes < vRes.size(); iRes++)
		{
			final JDFResource r = (JDFResource) vRes.elementAt(iRes);
			// reslinks that point to nothing may be valid

			// but they certainly aren't valid if they point to the wrong
			// resource
			if (!getNodeName().equals(r.getLinkString()))
				return false;

			if (level.getValue() >= EnumValidationLevel.RecursiveIncomplete.getValue())
			{
				final EnumValidationLevel valDown = (level == EnumValidationLevel.RecursiveIncomplete) ? EnumValidationLevel.Incomplete : EnumValidationLevel.Complete;

				if (!r.isValid(valDown))
					return false;
			}
		}
		return true;
	}

	/**
	 * checks whether this is a link to a physical resource.<br>
	 * Note that this method only works on links to resources that have a valid "Class" attribute.
	 * 
	 * @return true, if the link links to a physical resource
	 */
	public boolean isPhysical()
	{
		boolean fIsPhysical = false;
		final JDFResource resource = getLinkRoot();
		if (resource != null)
		{
			fIsPhysical = resource.isPhysical();
		}

		return fIsPhysical;
	}

	/**
	 * @return boolean
	 */
	public boolean isImplementation()
	{
		boolean fIsImplementation = false;
		final JDFResource linkRoot = getLinkRoot();
		if (linkRoot != null)
		{
			if (linkRoot.getResourceClass() == JDFResource.EnumResourceClass.Implementation)
			{
				fIsImplementation = true;
			}
		}
		return fIsImplementation;
	}

	/**
	 * append element Part
	 */
	public JDFPart appendPart()
	{
		return (JDFPart) appendElement(ElementName.PART, null);
	}

	public JDFAmountPool getAmountPool()
	{
		return (JDFAmountPool) getElement(ElementName.AMOUNTPOOL, null, 0);
	}

	public JDFAmountPool getCreateAmountPool()
	{
		if (this instanceof JDFPartAmount)
		{
			throw new JDFException("JDFResourceLink.getCreateAmountPool: calling method on PartAmount object");
		}
		return (JDFAmountPool) getCreateElement_KElement(ElementName.AMOUNTPOOL, null, 0);
	}

	public JDFAmountPool appendAmountPool()
	{
		// ideally the method would be hidden in PartAmount
		if (this instanceof JDFPartAmount)
		{
			throw new JDFException("JDFResourceLink.appendAmountPool: calling method on PartAmount object");
		}
		return (JDFAmountPool) appendElementN(ElementName.AMOUNTPOOL, 1, null);
	}

	/**
	 * get the nTh Lot element
	 * 
	 * @param n the index of the element
	 * @return the nth Lot, null if it does not exist
	 */
	public JDFLot getLot(int n)
	{
		return (JDFLot) getElement(ElementName.LOT, null, n);
	}

	/**
	 * get the nTh Lot element
	 * 
	 * @param n the index of the element
	 * @return the nth Lot, creates all Lots in case of n-1 does not exist
	 */
	public JDFLot getCreateLot(int n)
	{
		return (JDFLot) getCreateElement_KElement(ElementName.LOT, null, n);
	}

	/**
	 * append Lot element
	 * 
	 * @return the new Lot
	 */
	public JDFLot appendLot()
	{
		return (JDFLot) appendElement(ElementName.LOT, null);
	}

	/**
	 * reduce the parts to the canonical representation. If all children of a parent node are in defined in parts, they
	 * are replaced by their parent. E.g. the canonical representation of all leaves is the root.
	 */
	public void reduceParts()
	{
		final VJDFAttributeMap vParts = getPartMapVector();
		if (vParts.isEmpty()) // nothing to do
		{
			return;
		}

		final VJDFAttributeMap vReducedParts = getLinkRoot().reducePartVector(vParts);

		if (vParts != vReducedParts)
		{
			setPartMapVector(vReducedParts);
		}
	}

	/**
	 * Expand the target resource to contain all parts specified in the link. <br>
	 * If PartUsage==Explicit or bForce==true, loop over all part elements as well.<br>
	 * 
	 * @param bForce if true, implicitly referenced partitions are also expanded
	 */
	public void expandTarget(final boolean bForce)
	{
		final JDFResource r = getLinkRoot();
		if (r == null)
			return; // bail out!

		// loop over parts for partusage explicit
		if (r.getPartUsage() == JDFResource.EnumPartUsage.Explicit || bForce)
		{
			final VJDFAttributeMap apParts = getPartMapVector();
			if (apParts != null)
			{
				for (int i = 0; i < apParts.size(); i++)
				{
					final VElement vExist = r.getPartitionVector(apParts.elementAt(i), null);
					if (vExist.isEmpty())
						r.getCreatePartition(apParts.elementAt(i), null);
				}
			}
		}
	}

	/**
	 * create an Amountpool and fill it with the values of Amount and ActualAmount <br>
	 */
	public void expandAmountPool()
	{
		final VJDFAttributeMap apParts = getResourcePartMapVector();
		if (apParts == null)
			return;

		final VString attribs = new VString();
		attribs.add(AttributeName.AMOUNT);
		attribs.add(AttributeName.ACTUALAMOUNT);

		for (int j = 0; j < attribs.size(); j++)
		{
			final String attribName = attribs.stringAt(j);
			if (hasAttribute(attribName))
			{
				final String att = getAttribute(attribName, null, null);
				for (int i = 0; i < apParts.size(); i++)
				{
					setAmountPoolAttribute(attribName, att, null, apParts.elementAt(i));
				}
			}
		}
	}

	/**
	 * returns the minimum value of attribute occurence in PartAmount,
	 * 
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace URI
	 * @param mPart defines which part of this ResourceLink the amount belongs to. If empty get the ResourceLink root
	 *            attribute.
	 * @param def the default value id, if no matching attribute is found
	 * @return double - the value of attribute found, def if no matches found
	 * @since 060630
	 */
	public double getMinAmountPoolAttribute(final String attrib, final String nameSpaceURI, final JDFAttributeMap mPart, final int def)
	{
		final JDFAmountPool ap = getAmountPool();
		if (ap == null)
			return def;
		final VElement vPartAmount = ap.getMatchingPartAmountVector(mPart);

		boolean bMatch = false;
		double d = Double.MAX_VALUE;
		final int size = vPartAmount.size();
		for (int i = 0; i < size; i++)
		{
			final JDFPartAmount pa = (JDFPartAmount) vPartAmount.elementAt(i);
			final double realAttribute = pa.getRealAttribute(attrib, nameSpaceURI, def);
			if (realAttribute != def && realAttribute < d)
			{
				bMatch = true;
				d = realAttribute;
			}
		}
		return bMatch ? d : def;
	}

	/**
	 * returns the attribute occurence in PartAmount, or the default in the ResourceLink
	 * 
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @param mPart defines which part of this ResourceLink the Amount belongs to. If empty get the ResourceLink root
	 *            attribute.
	 * @return value of attribute found, null if not available
	 * @since 071103
	 */
	public String getAmountPoolAttribute(final String attrib, final String nameSpaceURI, final JDFAttributeMap mPart, final int iSkip)
	{
		return AmountPoolHelper.getAmountPoolAttribute(this, attrib, nameSpaceURI, mPart, iSkip);
	}

	/**
	 * returns the attribute occurence in PartAmount, or the default in the ResourceLink
	 * 
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @param vPart defines which part of this ResourceLink the Amount belongs to. If null get the ResourceLink root
	 *            attribute.
	 * @return value of attribute found, null if not available
	 * @since 071103
	 */
	public String getAmountPoolAttribute(final String attrib, final String nameSpaceURI, final VJDFAttributeMap vPart)
	{
		return AmountPoolHelper.getAmountPoolAttribute(this, attrib, nameSpaceURI, vPart);
	}

	/**
	 * returns true if the attribute occurrs
	 * 
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @param mPart which part of this ResourceLink the Amount belongs to, if empty get the ResourceLink root attribute
	 * @return true if available
	 * @deprecated 060601 use getAmountPoolAttribute(attrib,nameSpaceURI,mPart,0)!=null;
	 * @since 071103
	 */
	@Deprecated
	public boolean hasAmountPoolAttribute(final String attrib, final String nameSpaceURI, final JDFAttributeMap mPart)
	{
		return getAmountPoolAttribute(attrib, nameSpaceURI, mPart, 0) != null;
	}

	/**
	 * sets the attribute occurence in the appropriate PartAmount when called for a resourceLink and creates the
	 * AmountPool and/or PartAmount(s) if they are not yet there
	 * 
	 * @param attrib the attribute name
	 * @param value value to set in string form.
	 * @param nameSpaceURI the XML-namespace
	 * @param vPart defines which part of this ResourceLink the Amount belongs to, if empty set the ResourceLink root
	 *            attribute.
	 * @throws JDFException when called directly on a PartAmount
	 * @since 060630
	 */
	public void setAmountPoolAttribute(final String attrib, final String value, final String nameSpaceURI, VJDFAttributeMap vPart)
	{
		AmountPoolHelper.setAmountPoolAttribute(this, attrib, value, nameSpaceURI, vPart);
	}

	/**
	 * sets the attribute occurence in the appropriate PartAmount when called for a resourceLink and creates the
	 * AmountPool and/or PartAmount if it is not yet there
	 * 
	 * @param attrib the attribute name
	 * @param value value to set in string form.
	 * @param nameSpaceURI the XML-namespace
	 * @param mPart defines which part of this ResourceLink the Amount belongs to, if empty set the ResourceLink root
	 *            attribute
	 * @throws JDFException when called directly on a PartAmount
	 * @since 071103
	 */
	public void setAmountPoolAttribute(final String attrib, final String value, final String nameSpaceURI, final JDFAttributeMap mPart)
	{
		AmountPoolHelper.setAmountPoolAttribute(this, attrib, value, nameSpaceURI, mPart);
	}

	// //////////////////////////////////////////////////////////////////////////
	// ////

	public double getAmountPoolSumDouble(final String attName, VJDFAttributeMap vPart)
	{
		return AmountPoolHelper.getAmountPoolSumDouble(this, attName, vPart);
	}

	/**
	 * get the exactly matching AmountPool/PartAmount/@AttName as a double
	 * 
	 * @param attName
	 * @param vPart
	 * @return double -
	 * @throws JDFException if the element can not be cast to double
	 */
	public double getAmountPoolDouble(final String attName, final VJDFAttributeMap vPart)
	{
		return AmountPoolHelper.getAmountPoolDouble(this, attName, vPart);
	}

	/**
	 * get the sum of all matching AmountPool/PartAmount/@attName as a double PartAmounts match if all attributes match
	 * those in PartAmount, i.e. mPart is a submap of the searche PartAmount elements
	 * 
	 * 
	 * @param attName the Attribute name , e.g Amount, ActualAmount
	 * @param mPart
	 * @return double - the element
	 * @throws JDFException if the element can not be cast to double
	 */
	public double getAmountPoolDouble(final String attName, final JDFAttributeMap mPart)
	{
		return AmountPoolHelper.getAmountPoolDouble(this, attName, mPart);
	}

	/**
	 * Set attribute ActualAmount in the AmountPool or in the link, depending on the value of mPart
	 * 
	 * @param value the value to set ActualAmount to
	 * @param mPart the part map of AmountPool/PartAmount
	 */
	public void setActualAmount(final double value, final JDFAttributeMap mPart)
	{
		setAmountPoolAttribute("ActualAmount", StringUtil.formatDouble(value), null, mPart);
	}

	public double getActualAmount(final JDFAttributeMap mPart)
	{
		final double amountPoolDouble = getAmountPoolDouble(AttributeName.ACTUALAMOUNT, mPart);
		return amountPoolDouble == -1 ? 0 : amountPoolDouble;
	}

	/**
	 * set attribute ProcessUsage
	 * 
	 * @param s
	 * @deprecated use the enum method
	 */
	@Deprecated
	public void setProcessUsage(final String s)
	{
		setAttribute(AttributeName.PROCESSUSAGE, s, null);
	}

	/**
	 * get attribute ProcessUsage
	 * 
	 * @return String
	 */
	public String getProcessUsage()
	{
		return getAttribute(AttributeName.PROCESSUSAGE, null, JDFConstants.EMPTYSTRING);
	}

	/**
	 * get attribute ProcessUsage
	 * 
	 * @return EnumProcessUsage
	 */
	public EnumProcessUsage getEnumProcessUsage()
	{
		return EnumProcessUsage.getEnum(getAttribute(AttributeName.PROCESSUSAGE, null, null));
	}

	/**
	 * set attribute ProcessUsage
	 * 
	 * @param processUsage
	 */
	public void setProcessUsage(final EnumProcessUsage processUsage)
	{
		setAttribute(AttributeName.PROCESSUSAGE, processUsage == null ? null : processUsage.getName(), null);
	}

	/**
	 * set attribute Usage
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setUsage(final EnumUsage value)
	{
		setAttribute(AttributeName.USAGE, value == null ? null : value.getName(), null);
	}

	/**
	 * getUsage - get the usage of the ResourceLink in a JDF node. If no usage is available, default to the resource
	 * name.
	 * 
	 * @return EnumUsage
	 */
	public EnumUsage getUsage()
	{
		return EnumUsage.getEnum(getAttribute(AttributeName.USAGE, null, null));
	}

	/**
	 * set attribute MinStatus
	 * 
	 * @param value the value to set the attribute to
	 */
	public void setMinStatus(final JDFResource.EnumResStatus value)
	{
		setAttribute(AttributeName.MINSTATUS, value.getName(), null);
	}

	/**
	 * getMinStatus - get the minimum status of the ResourceLink in a JDF node. If usage is input or not available,
	 * check DraftOK as well.
	 * 
	 * @return the status of the ResourceLink
	 */
	public JDFResource.EnumResStatus getMinStatus()
	{
		final EnumResStatus returnEnum;
		if (hasAttribute(AttributeName.MINSTATUS))
		{
			returnEnum = JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.MINSTATUS));
		}
		else
		{
			if (getUsage() == EnumUsage.Output)
			{
				returnEnum = JDFResource.EnumResStatus.Unavailable;
			}
			else if (getBoolAttribute(AttributeName.DRAFTOK, null, false))
			{
				returnEnum = JDFResource.EnumResStatus.Draft;
			}
			else
			{
				returnEnum = JDFResource.EnumResStatus.Available;
			}
		}

		return returnEnum;
	}

	/**
	 * Method setUsage.
	 * 
	 * @param value
	 */
	public void setMinLateStatus(final JDFResource.EnumResStatus value)
	{
		setAttribute(AttributeName.MINLATESTATUS, value.getName(), null);
	}

	/**
	 * get attribute MinLateStatus
	 * 
	 * @return EnumUsage
	 */
	public JDFResource.EnumResStatus getMinLateStatus()
	{
		if (!this.hasAttribute(AttributeName.MINLATESTATUS))
			return getMinStatus();

		return JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.MINLATESTATUS, null, null));
	}

	/**
	 * Sets the value of PipePartIDKeys
	 * 
	 * @param keys vector of values to set
	 * @deprecated use setPipePartIDKeys(Vector enum)
	 */
	@Deprecated
	public void setPipePartIDKeys(final VString keys)
	{
		final Vector vEnum = new Vector();
		for (int i = 0; i < keys.size(); i++)
		{
			vEnum.add(EnumPartIDKey.getEnum(keys.elementAt(i)));
		}
		setPipePartIDKeys(vEnum);
	}

	/**
	 * Sets the value of PipePartIDKeys
	 * 
	 * @param keys vector of values to set
	 */
	public void setPipePartIDKeys(final Vector keys)
	{
		setEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS, keys, null);

	}

	/**
	 * Gets a list of all valid pipe part keys for this resource
	 * 
	 * @return VString - list of all PipePartIDKeys
	 * @deprecated
	 */
	@Deprecated
	public VString getPipePartIDKeys()
	{
		final VString vPipePartIDKeys = new VString();
		final Vector v = getPipePartIDKeysEnum();
		for (int i = 0; i < v.size(); i++)
			vPipePartIDKeys.add(((EnumPartIDKey) v.elementAt(i)).getName());

		return vPipePartIDKeys;
	}

	/**
	 * Gets an enumerated list of all valid pipe part keys for this resource
	 * 
	 * @return Vector of EnumPartIDKey - list of all PipePartIDKeys
	 */
	public Vector getPipePartIDKeysEnum()
	{
		Vector<EnumPartIDKey> v = null;

		final JDFResource res = getTarget();
		final VString vPartIDKeys = res.getPartIDKeys();
		if (hasAttribute(AttributeName.PIPEPARTIDKEYS))
		{
			v = getEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS, null, EnumPartIDKey.getEnum(0), false);
		}
		else
		{
			v = res.getPipePartIDKeysEnum();
		}
		for (int i = 0; i < v.size(); i++)
		{
			if (!vPartIDKeys.contains((v.elementAt(i)).getName()))
			{
				throw new JDFException("JDFResourceLink.getPipePartIDKeys: key " + v.elementAt(i)
						+ " is not subset of PartIDKey");
			}
		}
		return v;
	}

	/**
	 * sets attribute CombinedProcessIndex
	 * 
	 * @param value attribute value to set
	 */
	public void setCombinedProcessIndex(JDFIntegerList value)
	{
		if (value != null && value.size() == 0)
			value = null;
		setAttribute(AttributeName.COMBINEDPROCESSINDEX, value, null);
	}

	/**
	 * gets attribute CombinedProcessIndex
	 * 
	 * @return JDFIntegerList - attribute value, null if no CombinedProcessIndex is set or the format is illegal
	 */
	public JDFIntegerList getCombinedProcessIndex()
	{
		try
		{
			final String cpi = getAttribute(AttributeName.COMBINEDPROCESSINDEX, null, null);
			return cpi == null ? null : new JDFIntegerList(cpi);
		}
		catch (final DataFormatException dfe)
		{
			return null;
		}
	}

	/**
	 * sets attribute CombinedProcessType
	 * 
	 * @param value attribute value to set
	 */
	public void setCombinedProcessType(final String value)
	{
		setAttribute(AttributeName.COMBINEDPROCESSTYPE, value);
	}

	/**
	 * gets attribute CombinedProcessType
	 * 
	 * @return String - attribute value
	 */
	public String getCombinedProcessType()
	{
		return getAttribute(AttributeName.COMBINEDPROCESSTYPE);
	}

	/**
	 * gets list of all types referenced by CombinedProccessIndex or CombinedProcessType
	 * 
	 * @return VString - the list of types. Each type occurs at most once
	 */
	public VString getCombinedProcessTypes()
	{
		VString vs = null;
		final JDFNode parentJDF = getParentJDF();

		final VString vTypes = parentJDF == null ? null : parentJDF.getTypes();

		final JDFIntegerList il = getCombinedProcessIndex();
		if (il == null && !hasAttribute(AttributeName.COMBINEDPROCESSTYPE))
		{
			vs = vTypes;
		}
		else if (il != null && vTypes != null)
		{
			final int[] intArray = il.getIntArray();
			final int size = vTypes.size();
			vs = new VString();
			for (int i = 0; i < intArray.length; i++)
			{
				final int index = intArray[i];
				if (index < size)
					vs.add(vTypes.stringAt(index));
			}
		}
		else if (hasAttribute(AttributeName.COMBINEDPROCESSTYPE))
		{
			vs = new VString();
			vs.add(getCombinedProcessType());
		}

		if (vs != null)
		{
			vs.unify();
			if (vs.size() == 0)
				vs = null;
		}
		return vs;
	}

	/**
	 * sets attribute DraftOK if version>=1.3, set MinStatus=Draft instead of DraftOK=true
	 * 
	 * @param value attribute value to set
	 */
	public void setDraftOK(final boolean value)
	{
		final EnumVersion eVer = getVersion(true);
		if (eVer.getValue() < EnumVersion.Version_1_3.getValue())
		{
			setAttribute(AttributeName.DRAFTOK, value, null);
		}
		else if (value == true)
		{
			setMinStatus(JDFResource.EnumResStatus.Draft);
		}
		else
		// 1.3 DraftOK=false
		{
			setMinStatus(EnumUsage.Output.equals(getUsage()) ? JDFResource.EnumResStatus.Unavailable : JDFResource.EnumResStatus.Available);
		}
	}

	/**
	 * gets attribute DraftOK
	 * 
	 * @return boolean - attribute value. Default is false
	 */
	public boolean getDraftOK()
	{
		if (hasAttribute(AttributeName.DRAFTOK))
		{
			return getBoolAttribute(AttributeName.DRAFTOK, null, false);
		}

		// try to infer draftok from the JDF 1.3 MinStatus flag
		if (!hasAttribute(AttributeName.MINSTATUS))
			return false;

		return getMinStatus().getValue() <= JDFResource.EnumResStatus.Draft.getValue();
	}

	/**
	 * sets attribute PipeProtocol
	 * 
	 * @param value attribute value to set
	 */
	public void setPipeProtocol(final String value)
	{
		setAttribute(AttributeName.PIPEPROTOCOL, value);
	}

	/**
	 * gets string attribute PipeProtocol
	 * 
	 * @return String - attribute value.
	 */
	public String getPipeProtocol()
	{
		String str = JDFConstants.EMPTYSTRING;
		if (!hasAttribute(AttributeName.PIPEPROTOCOL))
		{
			final JDFResource res = getTarget();
			if (res != null)
			{
				str = res.getPipeProtocol();
			}
		}
		else
		{
			str = getAttribute(AttributeName.PIPEPROTOCOL);
		}
		return str;
	}

	/**
	 * sets attribute PipeURL
	 * 
	 * @param value attribute value to set
	 */
	public void setPipeURL(final String value)
	{
		setAttribute(AttributeName.PIPEURL, value, null);
	}

	/**
	 * gets string attribute PipeURL
	 * 
	 * @return String - attribute value.
	 */
	public String getPipeURL()
	{
		String str = JDFConstants.EMPTYSTRING;
		if (!hasAttribute(AttributeName.PIPEURL))
		{
			final JDFResource res = getTarget();
			if (res != null)
			{
				str = res.getPipeURL();
			}
		}
		else
		{
			str = getAttribute(AttributeName.PIPEURL);
		}
		return str;
	}

	/**
	 * sets attribute rRef
	 * 
	 * @param value attribute value to set
	 */
	public void setrRef(final String value)
	{
		setAttribute(AttributeName.RREF, value, null);
	}

	/**
	 * gets string attribute rRef
	 * 
	 * @return String - attribute value.
	 */
	public String getrRef()
	{
		return getAttribute(AttributeName.RREF);
	}

	/**
	 * sets attribute rSubRef
	 * 
	 * @param value attribute value to set
	 */
	public void setrSubRef(final String value)
	{
		setAttribute(AttributeName.RSUBREF, value);
	}

	/**
	 * gets string attribute rSubRef
	 * 
	 * @return String - attribute value.
	 */
	public String getrSubRef()
	{
		return getAttribute(AttributeName.RSUBREF);
	}

	/**
	 * sets attribute PipePause
	 * 
	 * @param value attribute value to set
	 */
	public void setPipePause(final double value)
	{
		setAttribute(AttributeName.PIPEPAUSE, value, null);
	}

	/**
	 * gets attribute PipePause
	 * 
	 * @return double - attribute value.
	 */
	public double getPipePause()
	{
		return getRealAttribute(AttributeName.PIPEPAUSE, null, 0.0);
	}

	/**
	 * sets attribute PipeResume
	 * 
	 * @param value attribute value to set
	 */
	public void setPipeResume(final double value)
	{
		setAttribute(AttributeName.PIPERESUME, value, null);
	}

	/**
	 * gets attribute PipeResume
	 * 
	 * @return double - attribute value.
	 */
	public double getPipeResume()
	{
		return getRealAttribute(AttributeName.PIPERESUME, null, 0.0);
	}

	/**
	 * sets attribute Orientation
	 * 
	 * @param value attribute value to set
	 */
	public void setOrientation(final EnumOrientation value)
	{
		setAttribute(AttributeName.ORIENTATION, value.getName(), null);
	}

	/**
	 * gets string attribute Orientation
	 * 
	 * @return EnumOrientation - attribute value
	 */
	public EnumOrientation getOrientation()
	{
		return EnumOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
	}

	/**
	 * sets attribute RemotePipeEndPause
	 * 
	 * @param value attribute value to set
	 */
	public void setRemotePipeEndPause(final double value)
	{
		setAttribute(AttributeName.REMOTEPIPEENDPAUSE, value, null);
	}

	/**
	 * gets attribute RemotePipeEndPause
	 * 
	 * @return double - attribute value.
	 */
	public double getRemotePipeEndPause()
	{
		return getRealAttribute(AttributeName.REMOTEPIPEENDPAUSE, null, 0.0);
	}

	/**
	 * sets attribute RemotePipeEndResume
	 * 
	 * @param value attribute value to set
	 */
	public void setRemotePipeEndResume(final double value)
	{
		setAttribute(AttributeName.REMOTEPIPEENDRESUME, value, null);
	}

	/**
	 * gets attribute RemotePipeEndResume
	 * 
	 * @return double - attribute value.
	 */
	public double getRemotePipeEndResume()
	{
		return getRealAttribute(AttributeName.REMOTEPIPEENDRESUME, null, 0.0);
	}

	/**
	 * sets attribute Transformation(
	 * 
	 * @param value attribute value to set
	 * @throws JDFException
	 */
	public void setTransformation(final JDFMatrix value)
	{
		setAttribute(AttributeName.TRANSFORMATION, value.toString());
	}

	/**
	 * gets string attribute Transformation
	 * 
	 * @return JDFMatrix - attribute value
	 * @throws JDFException if attribute has not a type JDFMatrix
	 */
	public JDFMatrix getTransformation()
	{
		try
		{
			return new JDFMatrix(getAttribute(AttributeName.TRANSFORMATION));
		}
		catch (final DataFormatException dfe)
		{
			throw new JDFException("JDFResourceLink.getTransformation: not capable to create JDFMatrix");
		}
	}

	/**
	 * sets attribute Duration
	 * 
	 * @param value attribute value to set
	 */
	public void setDuration(JDFDuration value)
	{
		if (value == null)
		{
			value = new JDFDuration();
		}
		setAttribute(AttributeName.DURATION, value.getDurationISO());
	}

	/**
	 * gets attribute Duration
	 * 
	 * @return JDFDuration - attribute value.
	 * @throws JDFException if attribute has not a type JDFDuration
	 */
	public JDFDuration getDuration()
	{
		try
		{
			return new JDFDuration(getAttribute(AttributeName.DURATION));
		}
		catch (final DataFormatException dfe)
		{
			throw new JDFException("JDFResourceLink.getDuration: not capable to create JDFDuration");
		}
	}

	/**
	 * sets attribute Recommendation
	 * 
	 * @param value attribute value to set
	 */
	public void setRecommendation(final boolean value)
	{
		setAttribute(AttributeName.RECOMMENDATION, value, null);
	}

	/**
	 * gets attribute Recommendation
	 * 
	 * @return boolean - attribute value.
	 */
	public boolean getRecommendation()
	{
		return getBoolAttribute(AttributeName.RECOMMENDATION, null, false);
	}

	/**
	 * sets attribute Start
	 * 
	 * @param value attribute value to set
	 */
	public void setStart(JDFDate value)
	{
		if (value == null)
		{
			value = new JDFDate();
		}
		setAttribute(AttributeName.START, value.getDateTimeISO());
	}

	/**
	 * gets attribute Start
	 * 
	 * @return JDFDate - attribute value
	 * @throws JDFException if attribute has not a type JDFDate
	 */
	public JDFDate getStart()
	{
		try
		{
			return new JDFDate(getAttribute(AttributeName.START, null, (new JDFDate()).getDateTimeISO()));
		}
		catch (final DataFormatException dfe)
		{
			throw new JDFException("JDFResourceLink.getStart: not capable to create JDFDate");
		}
	}

	/**
	 * sets attribute StartOffset
	 * 
	 * @param value - attribute value to set
	 */
	public void setStartOffset(JDFDuration value)
	{
		if (value == null)
		{
			value = new JDFDuration();
		}
		setAttribute(AttributeName.STARTOFFSET, value.getDurationISO());
	}

	/**
	 * gets attribute StartOffset
	 * 
	 * @return JDFDuration - attribute value
	 * @throws JDFException if attribute has not a type JDFDuration
	 */
	public JDFDuration getStartOffset()
	{
		try
		{
			return new JDFDuration(getAttribute(AttributeName.STARTOFFSET, null, (new JDFDuration()).getDurationISO()));
		}
		catch (final DataFormatException dfe)
		{
			throw new JDFException("JDFResourceLink.getStartOffset: not capable to create JDFDuration");
		}
	}

	/**
	 * get part map vector
	 * 
	 * @return VJDFAttributeMap - vector of attribute maps, one for each part
	 */
	@Override
	public VJDFAttributeMap getPartMapVector()
	{
		return super.getPartMapVector();
	}

	/**
	 * set all parts to those define in vParts
	 * 
	 * @param vParts vector of attribute maps for the parts
	 */
	@Override
	public void setPartMapVector(final VJDFAttributeMap vParts)
	{
		super.setPartMapVector(vParts);
	}

	/**
	 * set all parts to those defined in vParts
	 * 
	 * @param mPart attribute map for the part to set
	 */
	@Override
	public void setPartMap(final JDFAttributeMap mPart)
	{
		super.setPartMap(mPart);
	}

	/**
	 * remove the part defined in mPart
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
	 * return true if this is moderately well described by namedReslink
	 * 
	 * @param namedResLink
	 * @return
	 */
	public boolean matchesString(String namedResLink)
	{
		if (namedResLink == null)
			return false;

		boolean bMatch = namedResLink.equals(getNamedProcessUsage());
		bMatch = bMatch || namedResLink.equals(getLinkedResourceName());
		bMatch = bMatch || namedResLink.equals(getLocalName());
		bMatch = bMatch || namedResLink.equals(getNodeName());
		bMatch = bMatch || namedResLink.equals(getrRef());
		bMatch = bMatch || namedResLink.equals(getAttribute(AttributeName.USAGE));
		bMatch = bMatch
				|| namedResLink.equals(getLinkedResourceName() + JDFConstants.COLON + getAttribute(AttributeName.USAGE));

		if (!bMatch && StringUtil.token(namedResLink, 0, JDFConstants.COLON).equals(getLinkedResourceName()))
		{
			VElement v = getTargetVector(0);
			int siz = v == null ? 0 : v.size();
			for (int i = 0; i < siz; i++)
			{
				if (((JDFResource) v.elementAt(i)).matchesString(namedResLink))
					return true;
			}
		}
		return bMatch;
	}

}
