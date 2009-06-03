/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of 
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
 ==========================================================================
 class JDFResourceCmdParams extends JDFResource
 ==========================================================================
 @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
 ALL RIGHTS RESERVED
 @Author sabjon@topmail.de   using a code generator
 Warning! very preliminary test version. Interface subject to change without prior notice!
 Revision history:    ...
 **/

package org.cip4.jdflib.jmf;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoResourceCmdParams;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.INodeIdentifiable;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.util.StringUtil;

/**
 * class that wraps a ResourceCmdParams element
 * 
 * @author prosirai
 * 
 */
public class JDFResourceCmdParams extends JDFAutoResourceCmdParams implements INodeIdentifiable
{
	private static final long serialVersionUID = 1L;

	/**
	 * container for applying resource commands to commands
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class ApplyCommand
	{

		/**
		 * apply the parameters in this to all appropriate resources in parentNode or one of parentNode's children
		 * 
		 * @param parentNode the node to search in
		 */
		void applyResourceCommand(final JDFNode parentNode)
		{
			// TODO incrementa/complete etc.
			if (parentNode == null)
			{
				return;
			}

			final VElement vNodes = parentNode.getvJDFNode(null, null, false);

			final int size = vNodes.size();
			for (int i = 0; i < size; i++)
			{
				final JDFNode node = (JDFNode) vNodes.elementAt(i);
				if (!matchesNode(node))
				{
					continue;
				}
				final JDFResource resCmd = getResource(null);
				if (resCmd == null)
				{
					continue;
				}

				final boolean isIncremental = (getUpdateMethod() == EnumUpdateMethod.Incremental);

				// commented out, statements have no effect
				// double dAmount = -1.0;
				// if (hasAttribute (AttributeName.PRODUCTIONAMOUNT))
				// {
				// dAmount = getProductionAmount (); // TODO: set ProductionAmount
				// }
				// final String strProcessUsage = getProcessUsage(); // TODO: use
				// ProcessUsage
				// final JDFElement.EnumNodeStatus status = getStatus(); // TODO:
				// set Status

				final VJDFAttributeMap vamParts = getPartMapVector();
				JDFResource resTarget = getTargetResource(node);
				if (resTarget == null)
				{
					resTarget = createNewResource(node, resCmd);
					if (resTarget == null)
					{
						continue;
					}
				}

				// get the most granular list of partIDKeys from the cmd or resource
				VString vsPartIDKeys = resTarget.getPartIDKeys();
				final VString vsPartIDKeysCmd = resCmd.getPartIDKeys();
				final int sizTarget = vsPartIDKeys == null ? 0 : vsPartIDKeys.size();
				final int sizCmd = vsPartIDKeysCmd == null ? 0 : vsPartIDKeysCmd.size();
				if (sizCmd > sizTarget)
				{
					vsPartIDKeys = vsPartIDKeysCmd;
				}

				final int sizeParts = vamParts == null ? 1 : vamParts.size();
				for (int j = 0; j < sizeParts; j++)
				{
					final JDFAttributeMap amParts = vamParts == null ? null : vamParts.elementAt(j);
					final JDFResource resTargetPart = resTarget.getCreatePartition(amParts, vsPartIDKeys);
					if (resTargetPart == null)
					{
						continue;
					}

					final String id = resTargetPart.getID();
					if (!isIncremental)
					{
						final JDFAttributeMap map = resTargetPart.getPartMap();
						resTargetPart.flush();
						resTargetPart.setAttributes(map);
					}

					final JDFResource resCmdPart = resCmd.getPartition(amParts, EnumPartUsage.Implicit);
					final JDFAttributeMap map = resCmdPart.getAttributeMap();
					final VString keys = map.getKeys();
					if (keys != null)
					{
						final int keySize = keys.size();
						for (int k = 0; k < keySize; k++)
						{
							final String key = keys.elementAt(k);
							final String value = map.get(key);
							if (value == null)
							{
								resCmdPart.removeAttribute(key);
								resTargetPart.removeAttribute(key);
							}
						}
					}

					resTargetPart.mergeElement(resCmdPart, false);
					resTarget.setID(id);
				}

				if (sizeParts > 0 && resTarget instanceof JDFNodeInfo)
				{
					fixNodeStatusFromNodeInfo(node, resTarget);
				}
			}
		}

		/**
		 * @param node
		 * @return the target resource
		 */
		private JDFResource getTargetResource(final JDFNode node)
		{
			if (node == null)
			{
				return null;
			}
			final JDFResourceLinkPool rlp = node.getResourceLinkPool();
			if (rlp == null)
			{
				return null;
			}
			final String resID = getResourceID();
			if (resID != null && !resID.equals(""))
			{
				final VElement vRes = rlp.getLinkedResources(null, null, new JDFAttributeMap(AttributeName.ID, resID), false);
				if (vRes.size() > 0)
				{
					return (JDFResource) vRes.elementAt(0);
				}
			}

			final String resName = getResourceName();
			if (resName != null && !resName.equals(""))
			{
				final VElement vRes = rlp.getLinkedResources(resName, null, null, false);
				if (vRes.size() > 0)
				{
					return (JDFResource) vRes.elementAt(0);
				}

				// TODO link usage, process usage etc.

			}
			return null;
		}

		/**
		 * @param node
		 * @return true if it matches
		 */
		private boolean matchesNode(final JDFNode node)
		{
			if (node == null)
			{
				return false;
			}
			boolean bMatch = true;
			final String jobID = StringUtil.getNonEmpty(getJobID());
			if (jobID != null)
			{
				bMatch = jobID.equals(node.getJobID(true));
			}
			if (!bMatch)
			{
				return false;
			}
			final String jobPartID = StringUtil.getNonEmpty(getJobPartID());
			if (jobPartID != null)
			{
				bMatch = jobPartID.equals(node.getJobPartID(true));
			}
			return bMatch;
		}

		/**
		 * @param node
		 * @param resCmd
		 * @return the new resource
		 */
		private JDFResource createNewResource(final JDFNode node, final JDFResource resCmd)
		{
			JDFResource resTarget = null;
			final EnumUsage u = getUsage();
			if (u != null)
			{
				resTarget = (JDFResource) node.getCreateResourcePool().copyElement(resCmd, null);
				final JDFResourceLink rl = node.linkResource(resTarget, u, null);
				rl.copyAttribute(AttributeName.PROCESSUSAGE, JDFResourceCmdParams.this);
				resTarget = getTargetResource(node);
			}
			return resTarget;
		}

		/**
		 * @param node the jdf node
		 * @param resTarget
		 */
		private void fixNodeStatusFromNodeInfo(final JDFNode node, final JDFResource resTarget)
		{
			final EnumNodeStatus nodeStatus = node.getStatus();
			if (!EnumNodeStatus.Part.equals(nodeStatus) && !EnumNodeStatus.Pool.equals(node.getStatus()))
			{
				node.setStatus(EnumNodeStatus.Part);
				final JDFNodeInfo ni = (JDFNodeInfo) resTarget;
				if (!ni.hasAttribute(AttributeName.NODESTATUS))
				{
					ni.setNodeStatus(nodeStatus);
				}
			}
		}

	}

	/**
	 * Constructor for JDFResourceCmdParams
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFResourceCmdParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFResourceCmdParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFResourceCmdParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFResourceCmdParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFResourceCmdParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		int i = 0;
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PREVIEW, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * toString()
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFResourceCmdParams[  --> " + super.toString() + " ]";
	}

	/**
	 * get resource defined by <code>resName</code>, create if it doesn't exist
	 * 
	 * @param resName name of the resource to get/create
	 * @return JDFResource the element
	 */
	public JDFResource getCreateResource(final String resName)
	{
		final JDFResource r = null;
		final KElement e = getCreateElement(resName, JDFConstants.EMPTYSTRING, 0);
		if (!(e instanceof JDFResource))
		{
			throw new JDFException("JDFResourceCmdParams.getCreateResource tried to create a JDFElement instead of a JDFResource");
		}
		return r;
	}

	/**
	 * get resource defined by <code>resNam</code>
	 * 
	 * @param resName name of the resource to get, if null get the one and only resource
	 * @return JDFResource the element, null if none exists
	 */
	public JDFResource getResource(final String resName)
	{
		if (resName != null)
		{
			final KElement e = getElement(resName, null, 0);
			if (e instanceof JDFResource)
			{
				return (JDFResource) e;
			}
		}
		else
		{
			final String resName2 = getResourceName();
			if (resName2 != null && !resName2.equals(""))
			{
				return getResource(resName2);
			}
			KElement e2 = getFirstChildElement();
			while (e2 != null)
			{
				if (e2 instanceof JDFResource)
				{
					return (JDFResource) e2;
				}
				e2 = e2.getNextSiblingElement();
			}
		}
		return null;
	}

	/**
	 * Append Resource
	 * 
	 * @param resName name of the resource to append
	 * @return JDFResource the element
	 */
	public JDFResource appendResource(final String resName)
	{
		final KElement e = appendElement(resName, null);
		if (!(e instanceof JDFResource))
		{
			throw new JDFException("JDFResourceCMDParams.appendResource tried to return a JDFElement instead of a JDFResource");
		}
		return (JDFResource) e;
	}

	/**
	 * return the vector of unknown element nodenames
	 * <p>
	 * default: GetInvalidElements(true, 999999)<br>
	 * !!! Do not change the signature of this method
	 * 
	 * @param bIgnorePrivate used by JDFElement during the validation
	 * @param nMax maximum number of elements to get
	 * 
	 * @return Vector - vector of unknown element nodenames
	 */
	@Override
	public Vector getUnknownElements(final boolean bIgnorePrivate, final int nMax)
	{
		return getUnknownPoolElements(JDFElement.EnumPoolType.ResourcePool, nMax);
	}

	/**
	 * get part map vector
	 * 
	 * @return VJDFAttributeMap: vector of attribute maps, one for each part
	 */
	@Override
	public VJDFAttributeMap getPartMapVector()
	{
		return super.getPartMapVector();
	}

	/**
	 * set all parts to those defined by vParts
	 * 
	 * @param vParts vector of attribute maps for the parts
	 */
	@Override
	public void setPartMapVector(final VJDFAttributeMap vParts)
	{
		super.setPartMapVector(vParts);
	}

	/**
	 * set all parts to those define by mPart
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
	 * @param mPart attribute map to look for
	 * @return boolean - returns true if the part exists
	 */
	@Override
	public boolean hasPartMap(final JDFAttributeMap mPart)
	{
		return super.hasPartMap(mPart);
	}

	/**
	 * apply the parameters in this to all appropriate resources in parentNode or one of parentNode's children if no matching resource exists in the node, Usage
	 * MUST be set in this JDFResourceCmdParams, otherwise it is not possible to correctly link the newly created resource
	 * 
	 * @param parentNode the node to search in
	 */
	public void applyResourceCommand(final JDFNode parentNode)
	{
		new ApplyCommand().applyResourceCommand(parentNode);
	}

	/**
	 * gets the NodeIdetifier that matches this
	 * 
	 * @return {@link NodeIdentifier} the matching NodeIdentifier
	 */
	public NodeIdentifier getIdentifier()
	{
		return new NodeIdentifier(getJobID(), getJobPartID(), getPartMapVector());
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute Usage
	 * ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute Usage
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setUsage(final JDFResourceLink.EnumUsage enumVar)
	{
		setAttribute(AttributeName.USAGE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * (9) get attribute Usage
	 * @return the value of the attribute
	 */
	public JDFResourceLink.EnumUsage getUsage()
	{
		return JDFResourceLink.EnumUsage.getEnum(getAttribute(AttributeName.USAGE, null, null));
	}

	/**
	 * @see org.cip4.jdflib.ifaces.INodeIdentifiable#setIdentifier(org.cip4.jdflib.node.JDFNode.NodeIdentifier)
	 * @param ni
	 */
	public void setIdentifier(final NodeIdentifier ni)
	{
		NodeIdentifier niLocal = ni;

		if (niLocal == null)
		{
			niLocal = new NodeIdentifier();
		}

		setJobID(niLocal.getJobID());
		setJobPartID(niLocal.getJobPartID());
		setPartMapVector(niLocal.getPartMapVector());
	}
}
