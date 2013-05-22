/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFAncestorPool.java
 *
 * Last changes
 *
 * 2002-07-02 JG added Part element support
 * 2002-07-02 JG added GetPoolChild, GetPoolChildren
 * 2002-07-02 JG added CopyNodeData
 * 2002-07-02 JG added GetAncestorAttribute(), HasAncestorAttribute()
 * 2002-07-02 JG added GetAncestorElement(), HasAncestorElement()
 *
 */
package org.cip4.jdflib.pool;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAncestorPool;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFGeneralID;

/**
 * 
 * Description: This class represents an JDFAncestorPool
 * 
 * @author Torsten Kaehlert
 * 
 * @version 1.0 2002-01-28
 * 
 */
public class JDFAncestorPool extends JDFAutoAncestorPool
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFAncestorPool
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFAncestorPool(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAncestorPool
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFAncestorPool(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAncestorPool
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFAncestorPool(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * searches for the first attribute occurence in the ancestor elements
	 * 
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @param def the default if it does not exist
	 * @since 180502
	 * @return value of attribute found, value of def if not available
	 */
	public String getAncestorAttribute(String attrib, String nameSpaceURI, String def)
	{
		final VElement v = getPoolChildren(null);

		// the last in list is the direct parent, the first is the original root
		for (int i = v.size() - 1; i >= 0; i--)
		{
			final JDFAncestor ancestor = (JDFAncestor) v.elementAt(i);
			if (ancestor.hasAttribute(attrib, nameSpaceURI, false))
			{
				return ancestor.getAttribute(attrib, nameSpaceURI, JDFConstants.EMPTYSTRING);
			}
		}
		// not found, return the default
		return def;
	}

	/**
	 * @deprecated
	 * 
	 * @param element
	 * @param nameSpaceURI
	 * @return an element
	 */
	@Deprecated
	public KElement getAncestorElement(String element, String nameSpaceURI)
	{
		return getAncestorElement(element, nameSpaceURI, null);
	}

	/**
	 * searches for the first element occurence in the ancestor elements
	 * 
	 * @param element the element name
	 * @param nameSpaceURI the XML-namespace of the element
	 * @param xPath the xpath of a required attribute
	 * @since 290502
	 * @return value of attribute found, empty string if not available
	 */
	public KElement getAncestorElement(String element, String nameSpaceURI, String xPath)
	{
		final VElement v = getPoolChildren(null);
		boolean bWildCard = isWildCard(xPath);
		// the last in list is the direct parent, the first is the original root
		for (int i = v.size() - 1; i >= 0; i--)
		{
			final JDFAncestor ancestor = (JDFAncestor) v.elementAt(i);
			final KElement e = ancestor.getElement(element, nameSpaceURI, 0);
			if ((e != null) && (bWildCard || e.hasXPathNode(xPath)))
				return e;
		}
		// not found, return an empty element
		return null;
	}

	/**
	 * true id a non default occurence in the ancestor elements exists
	 * 
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @since 180502
	 * @return value of attribute found, empty string if not available
	 */
	public boolean hasAncestorElement(String attrib, String nameSpaceURI)
	{
		return getAncestorElement(attrib, nameSpaceURI, null) != null;
	}

	/**
	 * Get the linked resources matching some conditions
	 * 
	 * @param mResAtt map of Resource attributes to search for
	 * @param bFollowRefs true if internal references shall be followed
	 * 
	 * @return vResource: vector with all elements matching the conditions
	 * 
	 *         default: GetLinkedResources(new JDFAttributeMap(), false)
	 */
	public VElement getLinkedResources(JDFAttributeMap mResAtt, boolean bFollowRefs)
	{
		final VElement vChild = getPoolChildren(mResAtt);
		final VElement vElem = new VElement();
		for (int i = 0; i < vChild.size(); i++)
		{
			final JDFAncestor anc = (JDFAncestor) vChild.elementAt(i);
			vElem.appendUnique(anc.getLinkedResources(mResAtt, bFollowRefs));
		}
		return vElem;
	}

	/**
	 * Copy all data from rootNode into the Ancestor elements of this
	 * 
	 * @param parentNode the closest parent Node that contains the information to be copied
	 * @deprecated use public void copyNodeData (JDFNode parentNode, false, false) instead
	 */
	@Deprecated
	public void copyNodeData(JDFNode parentNode)
	{
		copyNodeData(parentNode, true, true, true);
	}

	/**
	 * Copy all data from parentNode into the ancestor elements of this
	 * 
	 * @param parentNode the closest parent Node that contains the information to be copied
	 * @param bCopyNodeInfo if true, also copy the NodeInfo into the ancestor
	 * @param bCopyCustomerInfo if true, also copy the CustomerInfo into the ancestor
	 * @param bCopyComments if true, also copy the comments and generalID elements into the ancestor
	 * @default copyNodeData(parentNode, false, false, false);
	 */
	public void copyNodeData(JDFNode parentNode, boolean bCopyNodeInfo, boolean bCopyCustomerInfo, boolean bCopyComments)
	{
		final VElement vAncestors = getPoolChildren(null);
		JDFNode node = parentNode;

		final JDFNode thisParentNode = getParentJDF();

		int i;
		for (i = vAncestors.size() - 1; i >= 0; i--)
		{
			final JDFAncestor ancestor = (JDFAncestor) vAncestors.elementAt(i);
			if (!node.getID().equals(ancestor.getNodeID()))
			{
				throw new JDFException("JDFAncestorPool::CopyNodeData: Invalid pairing");
			}

			ancestor.setAttributes(node);
			ancestor.removeAttribute(AttributeName.XSITYPE);
			ancestor.renameAttribute(AttributeName.ID, AttributeName.NODEID, null, null);
			// only copy nodeinfo and customerinfo in real parent nodes, not in this of partitioned spawns
			if (!thisParentNode.getID().equals(node.getID()))
			{
				if (bCopyNodeInfo)
				{
					final JDFNodeInfo nodeInfo = node.getNodeInfo();
					if (nodeInfo != null)
					{
						if (nodeInfo.getParentNode_KElement() instanceof JDFResourcePool)
						{
							// add a low level refelement, the copying takes place in addspawnedresources
							JDFRefElement re = (JDFRefElement) ancestor.appendElement(ElementName.NODEINFO + JDFConstants.REF);
							re.setrRef(nodeInfo.getID());
							re.setPartMap(nodeInfo.getPartMap());
						}
						else
						{
							ancestor.copyElement(nodeInfo, null);
						}
					}
				}

				if (bCopyCustomerInfo)
				{
					final JDFCustomerInfo customerInfo = node.getCustomerInfo();
					if (customerInfo != null)
					{
						if (customerInfo.getParentNode_KElement() instanceof JDFResourcePool)
						{
							// add a low level refelement, the copying takes place inaddspawnedresources
							JDFRefElement re = (JDFRefElement) ancestor.appendElement(ElementName.CUSTOMERINFO + JDFConstants.REF);
							re.setrRef(customerInfo.getID());
							re.setPartMap(customerInfo.getPartMap());
						}
						else
						{
							ancestor.copyElement(customerInfo, null);
						}
					}
				}

				if (bCopyComments)
				{
					Vector<JDFComment> vc = node.getChildrenByClass(JDFComment.class, false, 0);
					for (KElement comment : vc)
					{
						ancestor.copyElement(comment, null);
					}
					Vector<JDFGeneralID> vgid = node.getChildrenByClass(JDFGeneralID.class, false, 0);
					for (KElement generalid : vgid)
					{
						ancestor.copyElement(generalid, null);
					}
				}
			}

			JDFNode node2 = node.getParentJDF();

			// 100602 RP added i--
			if (node2 == null)
			{
				i--;
				break;
			}
			node = node2;
		}

		// the original node was already spawned --> also copy the elements of the original nodes Ancestorpool
		if (i >= 0)
		{
			final VElement parentAncestors = node.getAncestorPool().getPoolChildren(null);
			final int parentAncestorSize = parentAncestors.size();
			if (parentAncestorSize < i + 1)
			{
				throw new JDFException("JDFAncestorPool.CopyNodeData: Invalid AncestorPool pairing");
			}

			// now copy the ancestorpool elements that have not yet been added from the original nodes
			for (; i >= 0; i--)
			{
				final JDFAncestor ancestor = (JDFAncestor) vAncestors.elementAt(i);
				final JDFAncestor parentAncestor = (JDFAncestor) parentAncestors.elementAt(i);
				ancestor.mergeElement(parentAncestor, false);
			}
		}
	}

	/**
	 * Gets all children with the attribute out of the pool
	 * 
	 * @param mAttrib the attribute to search for
	 * @return VElement: a vector with all elements in the pool matching the conditions
	 * 
	 *         default: GetPoolChildren(null)
	 */
	public VElement getPoolChildren(JDFAttributeMap mAttrib)
	{
		return getPoolChildrenGeneric(ElementName.ANCESTOR, mAttrib, null);
	}

	/**
	 * get a child from the pool matching the parameters
	 * 
	 * @param i the index of the child or -1 to make a new one.
	 * @param mAttrib an attribute to search for
	 * @return JDFAncestor: the pool child matching the above conditions
	 * 
	 *         default: GetPoolChild(i, null)
	 */
	public JDFAncestor getPoolChild(int i, JDFAttributeMap mAttrib)
	{
		return (JDFAncestor) getPoolChildGeneric(i, ElementName.ANCESTOR, mAttrib, JDFConstants.EMPTYSTRING);
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
	 * set all parts to those define in vParts
	 * 
	 * @param vParts vector of attribute maps for the parts
	 */
	@Override
	public void setPartMapVector(VJDFAttributeMap vParts)
	{
		super.setPartMapVector(vParts);
	}

	/**
	 * set all parts to those define in vParts
	 * 
	 * @param mPart attribute map for the part to set
	 */
	@Override
	public void setPartMap(JDFAttributeMap mPart)
	{
		super.setPartMap(mPart);
	}

	/**
	 * remove the part defined in mPart
	 * 
	 * @param mPart attribute map for the part to remove
	 */
	@Override
	public void removePartMap(JDFAttributeMap mPart)
	{
		super.removePartMap(mPart);
	}

	/**
	 * check whether the part defined in mPart is included
	 * 
	 * @param mPart attribute map for the part to remove
	 * @return true if the part exists
	 */
	@Override
	public boolean hasPartMap(JDFAttributeMap mPart)
	{
		return super.hasPartMap(mPart);
	}

	/**
	 * check whether a defined attribute exists in the ancestor elements
	 * 
	 * @param attrib the attribute name to look for
	 * @param nameSpaceURI the XML-namespace to search in
	 * @since 180502
	 * @return value of attribute found, empty string if not available
	 */
	public boolean hasAncestorAttribute(String attrib, String nameSpaceURI)
	{
		return getAncestorAttribute(attrib, nameSpaceURI, null) != null;
	}

	/**
	 * searches for the first attribute occurence in the ancestor elements subelements<br>
	 * e.g. the JobPriority in NodeInfo
	 * 
	 * @param element node name to look in
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @param def the default if it does not exist
	 * @since 200503
	 * @return value of attribute found, empty string if not available
	 */
	public String getAncestorElementAttribute(String element, String attrib, String nameSpaceURI, String def)
	{
		final VElement v = getPoolChildren(null);
		// the last in list is the direct parent, the first is the original root

		for (int i = v.size() - 1; i >= 0; i--)
		{
			final JDFAncestor ancestor = (JDFAncestor) v.elementAt(i);
			final KElement e = ancestor.getElement(element, nameSpaceURI, 0);
			if ((e != null) && (e.hasAttribute(attrib, nameSpaceURI, false)))
			{
				return e.getAttribute(attrib, nameSpaceURI, JDFConstants.EMPTYSTRING);
			}
		}
		return def;
	}

	/**
	 * check whether the Ancestor pool contains any part elements
	 * 
	 * @return true if the pool comtains part elements
	 */
	public boolean isPartitioned()
	{
		final VJDFAttributeMap partMapVector = getPartMapVector();
		return partMapVector != null && partMapVector.size() > 0;
	}
}
