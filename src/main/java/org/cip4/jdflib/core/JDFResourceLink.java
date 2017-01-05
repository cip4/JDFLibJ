/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of Processes in
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

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoResourceLink;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.IAmountPoolContainer;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.CombinedProcessIndexHelper;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAmountPool.AmountMap;
import org.cip4.jdflib.pool.JDFAmountPool.AmountPoolHelper;
import org.cip4.jdflib.pool.JDFPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFLot;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 * way before 20.02.2009
 */
public class JDFResourceLink extends JDFAutoResourceLink implements IAmountPoolContainer
{
	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.PART, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * check whether e is a "real" resourceLink and NOT a partamount
	 * @param e
	 */
	public static boolean isResourceLink(KElement e)
	{
		return (e instanceof JDFResourceLink) && !(e instanceof JDFPartAmount);
	}

	/**
	 *
	 * @see org.cip4.jdflib.core.JDFElement#getInvalidAttributes(org.cip4.jdflib.core.JDFElement.EnumValidationLevel, boolean, int)
	 */
	@Override
	public VString getInvalidAttributes(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{
		final VString v = super.getInvalidAttributes(level, bIgnorePrivate, nMax);
		if (!v.contains(AttributeName.COMBINEDPROCESSINDEX) && !validCombinedProcessIndex())
		{
			v.add(AttributeName.COMBINEDPROCESSINDEX);
		}
		return v;
	}

	/**
	* Enumeration strings for Usage
	*/

	public static class EnumUsage extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		protected EnumUsage(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName the string to convert
		 * @return the enum
		 */
		public static EnumUsage getEnum(String enumName)
		{
			return (EnumUsage) getEnum(EnumUsage.class, enumName);
		}

		/**
		 * @param enumValue the integer to convert
		 * @return the enum
		 */
		public static EnumUsage getEnum(int enumValue)
		{
			return (EnumUsage) getEnum(EnumUsage.class, enumValue);
		}

		/**
		 * @return the map of enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumUsage.class);
		}

		/**
		 * @return the list of enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumUsage.class);
		}

		/**
		 * @return the iterator
		 */
		public static Iterator iterator()
		{
			return iterator(EnumUsage.class);
		}

		public static final EnumUsage Input = new EnumUsage("Input");
		public static final EnumUsage Output = new EnumUsage("Output");

		/**
		 * @return the opposite usage for this
		 */
		public EnumUsage invert()
		{
			return Output.equals(this) ? Input : Output;
		}

		/**
		 * @return true if I am input
		 */
		public boolean isInput()
		{
			return Input.equals(this);
		}
	}

	/* ---------------------------------------------------------------------
	Methods for Attribute Usage
	--------------------------------------------------------------------- */
	/**
	  * (5) set attribute Usage
	  * @param enumVar the enumVar to set the attribute to
	  */
	@Override
	public void setUsage(EnumUsage enumVar)
	{
		setAttribute(AttributeName.USAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	  * (9) get attribute Usage
	  * @return the value of the attribute
	  */
	@Override
	public EnumUsage getUsage()
	{
		return EnumUsage.getEnum(getAttribute(AttributeName.USAGE, null, null));
	}

	/**
	 *
	 */
	public void generateCombinedProcessIndex()
	{
		final JDFNode n = getParentJDF();
		if (n != null)
		{
			final VString types = n.getTypes();
			if (types != null)
			{
				CombinedProcessIndexHelper.generateCombinedProcessIndex(getLinkRoot(), getUsage(), getEnumProcessUsage(), this, types);
			}
		}
	}

	/**
	 * @return
	 */
	public boolean validCombinedProcessIndex()
	{
		final JDFIntegerList vCombined = getCombinedProcessIndex();
		if (vCombined == null)
		{
			return true;
		}
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
	 *
	 *  remove all partition stuff
	 * @param bRemovePartMapVector
	 * @param bRemoveAmountPool if true, assume all amounts apply to the main resource and copy from the first partamount
	 */
	public void unpartition(boolean bRemovePartMapVector, boolean bRemoveAmountPool)
	{
		if (bRemovePartMapVector)
			setPartMapVector(null);
		JDFAmountPool ap = getAmountPool();
		if (bRemoveAmountPool && ap != null)
		{
			JDFPartAmount pa = ap.getPartAmount(0);
			setAttributes(pa);
			ap.deleteNode();
		}
	}

	/**
	 * setTarget - sets the link to the target defined by partLeaf. Automatically generates a part subelement, if partleaf is not the root resource
	 *
	 * @param resourceTarget the resource that this ResourceLink shoud refer to
	 *
	 * @throws JDFException if an attempt is made to link to a resource sub-element
	 * @return boolean - always true
	 */
	public boolean setTarget(final JDFResource resourceTarget)
	{
		if (resourceTarget.isResourceElement())
		{
			throw new JDFException("attempting to link to a resource subelement");
		}

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
	@Override
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
	 * getParts - get the vector of part elements, note that a resource link with multiple part elements is effectively an OR of these parts
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
	 * isExecutable - checks whether the resource link links to a resource, which is in a state that will allow a node to execute
	 *
	 * @param partMap the attribute map of parts
	 * @param bCheckChildren if true, calculates the availability status of a resource from all child partition leaves, else the status is taken from the
	 * appropriate leaf itself
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
				{
					leaves.add(leaf);
				}
			}
		}
		leaves.unify();

		for (int i = 0; i < leaves.size(); i++)
		{
			final JDFResource leaf = (JDFResource) leaves.elementAt(i);
			if (partMap != null && !partMap.isEmpty() && !partMap.overlapMap(leaf.getPartMap()))
			{
				continue;
			}

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
	 * overrides the deprecated method JDFElement.getTarget()
	 *
	 * @since 102103 GetTarget returns the lowest common denominator if all children of a resource are referenced
	 *
	 * @return JDFResource - the first leaf that is referenced by this ResourceLink
	 */
	@Override
	@SuppressWarnings(value = { "deprecation" })
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
	 * Method getTargetVector gets the resource nodes this resourcelink refers to. Skips links that do not exist or where the name mangling is illegal.<br>
	 * Actual behavior varies according to the value of PartUsage of the referenced resource:<br>
	 * if PartUsage="Explicit", all elements that are referenced in PartIDKeys and the ResourceLink must exist and fit<br>
	 * if PartUsage="Implicit", the best fitting intermediate node of the partitioned resource is returned.<br>
	 * Attributes in the Part elements, that are not referenced in PartIDKeys, are assumed to be logical attributes (e.g. RunIndex of a RunList) and ignored
	 * when searching the part.
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
	 * Gets the resource nodes this resourcelink refers to. Skips links that do not exist or where the name mangling is illegal.<br>
	 * Actual behavior varies according to the value of PartUsage of the referenced resource:<br>
	 * if PartUsage="Explicit", all elements that are referenced in PartIDKeys and the ResourceLink must exist and fit<br>
	 * if PartUsage="Implicit", the best fitting intermediate node of the partitioned resource is returned.<br>
	 * Attributes in the Part elements, that are not referenced in PartIDKeys, are assumed to be logical attributes (e.g. RunIndex of a RunList) and ignored
	 * when searching the part.
	 *
	 * @param vmParts target map to use
	 * @param nMax maximum number of requested resources; -1= all
	 * @return VElement the set of leaves that are referenced by this ResourceLink
	 */
	private VElement getMapTargetVector(final VJDFAttributeMap vmParts, final int nMax)
	{
		VElement v = new VElement();
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

		if (JDFResource.EnumPartUsage.Implicit.equals(partUsage))
		{
			vmParts.reduceMap(resRoot.getPartIDKeys().getSet());
		}
		if (vmParts.isEmpty())
		{
			v.addElement(resRoot);
			return v;
		}

		v = resRoot.getPartitionVector(vmParts, partUsage);
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
	public boolean hasResourcePartMap(final JDFAttributeMap partMap, final boolean bCheckResource)
	{
		// TODO remove implicit partitions
		// Attention !!!
		// Don't change this method without checking if routing is still working
		// !
		// The C++ method is different and is not used, the java method is used
		// for routing.
		final JDFResource linkRoot = getLinkRoot();
		if (linkRoot == null)
		{
			return false;
		}

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
				if (siz == 0 || vPart == null)
				{
					return true;
				}

				for (int i = 0; i < siz; i++)
				{
					if (vPart.elementAt(i).overlapMap(partMap))
					{
						return true;
					}
				}
			}
			else
			{ // explicit
				if (siz == 0 && !bCheckResource)
				{
					return true;
				}

				if (vPart != null)
				{
					for (int i = 0; i < siz; i++)
					{
						final JDFAttributeMap part = vPart.elementAt(i);
						if (part == null || part.size() == 0)
						{
							return true;
						}

						// RP 050120 swap of vPart[i] and partmap
						// RP 070511 swap back of vPart[i] and partmap
						if (part.subMap(partMap))
						{
							return true;
						}
					}
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

		if (vPart != null)
		{
			for (int i = 0; i < siz; i++)
			{
				if (vPart.elementAt(i).overlapMap(partMap))
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Check whether a resource is selected by a ResourceLink.<br>
	 * A resource is selected if all (partition) leaves are selected by the resource link
	 *
	 * @param resourceToCheck The resource which may be selected by the ResourceLink.
	 *
	 * This ResourceLink must always be the full ResourceLink, i.e. Part Elements are not allowed as parameters.
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
			if (nPartChildren > 0 && vPartMap != null) // suppress possible NPE warning for vPartMap
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
				// no parts in the resourcelink -> simply append the resources partmap
				vMap.addElement(leafMap);
			}
		}

		return vMap.size() == 0 ? null : vMap;
	}

	/**
	 *
	 * @return the vector of referenced leaves
	 */
	public VElement getLeafVector()
	{
		final VJDFAttributeMap vmParts = getPartMapVector();
		final JDFResource resRoot = getLinkRoot();
		if (resRoot == null) // snafu - link pointing to nirvana
		{
			return null;
		}
		final VElement v = new VElement();

		if (vmParts == null)
		{
			return resRoot.getLeaves(false);
		}

		final EnumPartUsage partUsage = resRoot.getPartUsage();

		for (int i = 0; i < vmParts.size(); i++)
		{
			final VElement vr = resRoot.getPartitionVector(vmParts.elementAt(i), partUsage);
			for (KElement e : vr)
			{
				final JDFResource targ = (JDFResource) e;
				final VElement leaves = targ.getLeaves(false);
				for (int k = 0; k < leaves.size(); k++)
				{
					JDFResource leaf = (JDFResource) leaves.get(k);
					while (!leaf.getPartMap().overlapMap(vmParts.elementAt(i)) && !leaf.isResourceRoot())
					{
						leaf = (JDFResource) leaf.getParentNode();
					}
					v.add(leaf);
				}
			}
		}
		v.unify();
		return v.isEmpty() ? null : v;
	}

	/**
	 * Returns the linked resource name
	 *
	 * @return - the name
	 */
	public String getLinkedResourceName()
	{
		final String nodeName = getNodeName();
		return StringUtil.leftStr(nodeName, -4);
	}

	/**
	 * Get the expected name of the linked resource and an optional processusage in name:usage format. If no processusage is available, return
	 * GetLinkedResourceName:input / GetLinkedResourceName:output respectively.
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
	public boolean isValid(final EnumValidationLevel level)
	{
		EnumValidationLevel levelLocal = level;

		if (levelLocal == null)
		{
			levelLocal = EnumValidationLevel.Complete;
		}

		final boolean b = super.isValid(levelLocal);
		if (!b)
		{
			return false;
		}

		if (this instanceof JDFPartAmount)
		{
			return true;
		}

		if ((levelLocal != EnumValidationLevel.Complete) && (levelLocal != EnumValidationLevel.Incomplete) && (levelLocal != EnumValidationLevel.RecursiveIncomplete))
		{
			return true;
		}

		if (!validResourcePosition())
		{
			return false;
		}

		final VElement vRes = getTargetVector(0);
		if ((vRes == null || vRes.isEmpty()) && ((levelLocal == EnumValidationLevel.Complete) || (levelLocal == EnumValidationLevel.RecursiveComplete)))
		{
			// if any partition points to nirvana and we are validating
			// complete, the entire resource is invalid
			return false;
		}

		if (levelLocal.equals(EnumValidationLevel.Complete))
		{
			return true;
		}

		if (vRes != null)
		{
			for (int iRes = 0; iRes < vRes.size(); iRes++)
			{
				final JDFResource r = (JDFResource) vRes.elementAt(iRes);
				// reslinks that point to nothing may be valid

				// but they certainly aren't valid if they point to the wrong
				// resource
				if (!getNodeName().equals(r.getLinkString()))
				{
					return false;
				}

				if (levelLocal.getValue() >= EnumValidationLevel.RecursiveIncomplete.getValue())
				{
					final EnumValidationLevel valDown = (levelLocal == EnumValidationLevel.RecursiveIncomplete) ? EnumValidationLevel.Incomplete : EnumValidationLevel.Complete;

					if (!r.isValid(valDown))
					{
						return false;
					}
				}
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
	 * @return
	 */
	public JDFPart appendPart()
	{
		return (JDFPart) appendElement(ElementName.PART, null);
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IAmountPoolContainer#getCreateAmountPool()
	 * @return
	*/
	@Override
	public JDFAmountPool getCreateAmountPool()
	{
		if (this instanceof JDFPartAmount)
		{
			throw new JDFException("JDFResourceLink.getCreateAmountPool: calling method on PartAmount object");
		}
		return (JDFAmountPool) getCreateElement_KElement(ElementName.AMOUNTPOOL, null, 0);
	}

	/**
	 * @return
	 */
	@Override
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
	@Override
	public JDFLot getLot(final int n)
	{
		return (JDFLot) getElement(ElementName.LOT, null, n);
	}

	/**
	 * reduce the parts to the canonical representation. If all children of a parent node are in defined in parts, they are replaced by their parent. E.g. the
	 * canonical representation of all leaves is the root.
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
		{
			return; // bail out!
		}

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
					{
						r.getCreatePartition(apParts.elementAt(i), null);
					}
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
		{
			return;
		}

		final VString attribs = new VString();
		attribs.add(AttributeName.AMOUNT);
		attribs.add(AttributeName.ACTUALAMOUNT);

		for (int j = 0; j < attribs.size(); j++)
		{
			final String attribName = attribs.get(j);
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
	 * @param mPart defines which part of this ResourceLink the amount belongs to. If empty get the ResourceLink root attribute.
	 * @param def the default value id, if no matching attribute is found
	 * @return double - the value of attribute found, def if no matches found
	 * @since 060630
	 */
	public double getMinAmountPoolAttribute(final String attrib, final String nameSpaceURI, final JDFAttributeMap mPart, final int def)
	{
		final JDFAmountPool ap = getAmountPool();
		if (ap == null)
		{
			return def;
		}
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
	 * get an AmountMap for the child Amountpool of this
	 * @param vPartIDKeys
	 * @return the AmountMap for the Amountpool, null if no amountpool exists
	 */
	public AmountMap getAmountMap(final VString vPartIDKeys)
	{
		return AmountPoolHelper.getAmountMap(this, vPartIDKeys);
	}

	/**
	 * returns the attribute occurence in PartAmount, or the default in the ResourceLink
	 *
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @param mPart defines which part of this ResourceLink the Amount belongs to. If empty get the ResourceLink root attribute.
	 * @param iSkip
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
	 * @param vPart defines which part of this ResourceLink the Amount belongs to. If null get the ResourceLink root attribute.
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
	 * sets the attribute occurence in the appropriate PartAmount when called for a resourceLink and creates the AmountPool and/or PartAmount(s) if they are not
	 * yet there
	 *
	 * @param attrib the attribute name
	 * @param value value to set in string form.
	 * @param nameSpaceURI the XML-namespace
	 * @param vPart defines which part of this ResourceLink the Amount belongs to, if empty set the ResourceLink root attribute.
	 * @throws JDFException when called directly on a PartAmount
	 * @since 060630
	 */
	public void setAmountPoolAttribute(final String attrib, final String value, final String nameSpaceURI, final VJDFAttributeMap vPart)
	{
		AmountPoolHelper.setAmountPoolAttribute(this, attrib, value, nameSpaceURI, vPart);
	}

	/**
	 * sets the attribute occurence in the appropriate PartAmount when called for a resourceLink and creates the AmountPool and/or PartAmount if it is not yet
	 * there
	 *
	 * @param attrib the attribute name
	 * @param value value to set in string form.
	 * @param nameSpaceURI the XML-namespace
	 * @param mPart defines which part of this ResourceLink the Amount belongs to, if empty set the ResourceLink root attribute
	 * @throws JDFException when called directly on a PartAmount
	 * @since 071103
	 */
	public void setAmountPoolAttribute(final String attrib, final String value, final String nameSpaceURI, final JDFAttributeMap mPart)
	{
		AmountPoolHelper.setAmountPoolAttribute(this, attrib, value, nameSpaceURI, mPart);
	}

	// //////////////////////////////////////////////////////////////////////////
	// ////

	/**
	 * @param attName
	 * @param vPart
	 * @return
	 */
	public double getAmountPoolSumDouble(final String attName, final VJDFAttributeMap vPart)
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
	 * get the sum of all matching AmountPool/PartAmount/@attName as a double PartAmounts match if all attributes match those in PartAmount, i.e. mPart is a
	 * submap of the searched PartAmount elements
	 *
	 *
	 * @param attName the Attribute name , e.g Amount, ActualAmount
	 * @param mPart
	 * @return double - the element
	 * @throws JDFException if the element can not be cast to double
	 */
	public double getAmountPoolDouble(final String attName, final JDFAttributeMap mPart)
	{
		final JDFResource r = getTarget();
		double d = 0;
		final JDFAmountPool amountPool = getAmountPool();
		if (amountPool == null && hasAttribute(attName))
		{
			return getRealAttribute(attName, null, 0);
		}
		else if (r == null || EnumPartUsage.Implicit.equals(r.getPartUsage()) || (this instanceof JDFPartAmount))
		{
			d = AmountPoolHelper.getAmountPoolDouble(this, attName, mPart);
		}
		else if (amountPool != null)
		{

			final VElement vResLeaves = r.getLeaves(false);
			final HashSet<JDFPartAmount> hsDone = new HashSet<JDFPartAmount>();
			for (KElement leaf : vResLeaves)
			{
				final JDFResource resLeaf = (JDFResource) leaf;
				final JDFAttributeMap m = resLeaf.getPartMap();
				if ((m == null) || m.overlapMap(mPart))
				{
					final JDFAttributeMap m2 = (m == null) ? mPart : m.getOrMap(mPart);

					final JDFPartAmount pa = amountPool.getPartAmount(mPart);
					// don't count ParAmount elements with multiple parts more than once
					if (!hsDone.contains(pa))
					{
						double delta = AmountPoolHelper.getAmountPoolMinDouble(this, attName, m2);
						if (m2.get(AttributeName.CONDITION) == null)
						{
							JDFAttributeMap m2Good = m2.clone();
							m2Good.put(AttributeName.CONDITION, "Good");
							double deltaGood = AmountPoolHelper.getAmountPoolMinDouble(this, attName, m2Good);
							delta = Math.max(delta, deltaGood);
						}
						if (delta > 0)
						{
							d += delta;
						}
						hsDone.add(pa);
					}
				}
			}
			if (d == 0)
			{
				d = AmountPoolHelper.getAmountPoolDouble(this, attName, mPart);
			}

		}
		return d;
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

	/**
	 * @param mPart
	 * @return
	 */
	public double getActualAmount(final JDFAttributeMap mPart)
	{
		final double amountPoolDouble = getAmountPoolDouble(AttributeName.ACTUALAMOUNT, mPart);
		return amountPoolDouble == -1 ? 0 : amountPoolDouble;
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
	 * getMinStatus - get the minimum status of the ResourceLink in a JDF node. If usage is input or not available, check DraftOK as well.
	 *
	 * @return the status of the ResourceLink
	 */
	@Override
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
	 * get attribute MinLateStatus
	 *
	 * @return EnumUsage
	 */
	@Override
	public JDFResource.EnumResStatus getMinLateStatus()
	{
		if (!this.hasAttribute(AttributeName.MINLATESTATUS))
		{
			return getMinStatus();
		}

		return JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.MINLATESTATUS, null, null));
	}

	/**
	 * Sets the value of PipePartIDKeys
	 *
	 * @param keys vector of values to set
	 * @deprecated use setPipePartIDKeys(Vector enum)
	 */
	@SuppressWarnings("unchecked")
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
	 * Gets an enumerated list of all valid pipe part keys for this resource
	 *
	 * @return Vector of EnumPartIDKey - list of all PipePartIDKeys
	 */
	@SuppressWarnings("unchecked")
	public Vector<EnumPartIDKey> getPipePartIDKeysEnum()
	{
		Vector<EnumPartIDKey> v = null;

		final JDFResource res = getTarget();
		final VString vPartIDKeys = res.getPartIDKeys();
		if (hasAttribute(AttributeName.PIPEPARTIDKEYS))
		{
			v = (Vector<EnumPartIDKey>) getEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS, null, EnumPartIDKey.getEnum(0), false);
		}
		else
		{
			v = res.getPipePartIDKeysEnum();
		}
		for (int i = 0; i < v.size(); i++)
		{
			if (!vPartIDKeys.contains((v.elementAt(i)).getName()))
			{
				throw new JDFException("JDFResourceLink.getPipePartIDKeys: key " + v.elementAt(i) + " is not subset of PartIDKey");
			}
		}
		return v;
	}

	/**
	 * sets attribute CombinedProcessIndex
	 *
	 * @param value attribute value to set
	 */
	@Override
	public void setCombinedProcessIndex(JDFIntegerList value)
	{
		if (value != null && value.size() == 0)
		{
			value = null;
		}
		setAttribute(AttributeName.COMBINEDPROCESSINDEX, value, null);
	}

	/**
	 * sets attribute CombinedProcessIndex
	 *
	 * @param value attribute value to set
	 */
	public void setCombinedProcessIndex(final int value)
	{
		setAttribute(AttributeName.COMBINEDPROCESSINDEX, new JDFIntegerList(value), null);
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
			for (final int index : intArray)
			{
				if (index < size)
				{
					vs.add(vTypes.get(index));
				}
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
			{
				vs = null;
			}
		}
		return vs;
	}

	/**
	 * sets attribute DraftOK if version>=1.3, set MinStatus=Draft instead of DraftOK=true
	 *
	 * @param value attribute value to set
	 */
	@Override
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
	@Override
	public boolean getDraftOK()
	{
		if (hasAttribute(AttributeName.DRAFTOK))
		{
			return getBoolAttribute(AttributeName.DRAFTOK, null, false);
		}

		// try to infer draftok from the JDF 1.3 MinStatus flag
		if (!hasAttribute(AttributeName.MINSTATUS))
		{
			return false;
		}

		return getMinStatus().getValue() <= JDFResource.EnumResStatus.Draft.getValue();
	}

	/**
	 * gets string attribute PipeProtocol
	 *
	 * @return String - attribute value.
	 */
	@Override
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
	 * gets string attribute PipeURL
	 *
	 * @return String - attribute value.
	 */
	@Override
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
	public boolean matchesString(final String namedResLink)
	{
		if (namedResLink == null)
		{
			return false;
		}

		boolean bMatch = namedResLink.equals(getNamedProcessUsage());
		bMatch = bMatch || namedResLink.equals(getLinkedResourceName());
		bMatch = bMatch || namedResLink.equals(getLocalName());
		bMatch = bMatch || namedResLink.equals(getNodeName());
		bMatch = bMatch || namedResLink.equals(getrRef());
		bMatch = bMatch || namedResLink.equals(getAttribute(AttributeName.USAGE));
		bMatch = bMatch || namedResLink.equals(getLinkedResourceName() + JDFConstants.COLON + getAttribute(AttributeName.USAGE));

		if (!bMatch && StringUtil.token(namedResLink, 0, JDFConstants.COLON).equals(getLinkedResourceName()))
		{
			final VElement v = getTargetVector(0);
			if (v != null)
			{
				final int siz = v.size();
				for (int i = 0; i < siz; i++)
				{
					if (((JDFResource) v.elementAt(i)).matchesString(namedResLink))
					{
						return true;
					}
				}
			}
		}

		return bMatch;
	}

}
