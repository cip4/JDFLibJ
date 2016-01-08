/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of 
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
 */
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.PartitionHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.xjdfwalker.IDFinder.IDPart;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.util.StringUtil;

/**
 * 
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class WalkXElement extends BaseWalker
{

	protected XJDFToJDFImpl xjdfToJDFImpl;

	/**
	 *  
	 *  
	 */
	public WalkXElement()
	{
		super();
		xjdfToJDFImpl = null;
	}

	/**
	 * fills this into the factory
	 * @param xjdftojdf 
	 */
	public void setParent(XJDFToJDFImpl xjdftojdf)
	{
		xjdfToJDFImpl = xjdftojdf;
	}

	/**
	 * @param e
	 * @return element to continue with if must continue
	 */
	@Override
	public KElement walk(final KElement e, KElement trackElem)
	{
		final VElement v = trackElem.getChildElementVector(null, null);
		for (KElement kk : v)
		{
			if (e.isEqual(kk))
			{
				return null;
			}
		}
		cleanRefs(e, trackElem);

		// dirty, dirty but needed in case of inherited inline resources
		if (PartitionHelper.isResourceElement(e) || IntentHelper.isIntentResource(e))
		{
			trackElem.setAttributes(e);
		}
		else
		{
			final KElement e2 = trackElem.copyElement(e, null);
			xjdfToJDFImpl.convertUnits(e2);
			xjdfToJDFImpl.convertTilde(e2);
			fixNamespace(e2);
			e2.removeChildren(null, null, null); // will be copied later
			trackElem = e2;
		}
		xjdfToJDFImpl.convertUnits(trackElem);
		xjdfToJDFImpl.convertTilde(trackElem);

		if (trackElem instanceof JDFElement)
		{
			// we want to retain all existing attributes
			JDFAttributeMap map = trackElem.getAttributeMap_KElement();
			((JDFElement) trackElem).init();
			trackElem.setAttributes(map);
		}
		updateAttributes(trackElem);
		return trackElem;
	}

	protected void updateAttributes(KElement elem)
	{
		elem.renameAttribute(XJDFConstants.ExternalID, AttributeName.PRODUCTID, null, null);
	}

	/**
	 * move namespace to 1.1 for all 2.x values
	 * @param e2
	 */
	private void fixNamespace(KElement e2)
	{
		String namespace = e2.getNamespaceURI();
		if (JDFElement.getSchemaURL(2, 0).equals(StringUtil.leftStr(namespace, -1) + 0))
		{
			e2.setNamespaceURI(JDFElement.getSchemaURL(1, 1));
			if (StringUtil.getNonEmpty(e2.getPrefix()) != null)
			{
				e2.setPrefix(null);
			}
		}
	}

	/**
	 * @param val
	 * @return
	 */
	protected String getRefName(final String val)
	{
		final String refName = val.endsWith("Refs") ? StringUtil.leftStr(val, -1) : val;
		return refName;
	}

	/**
	 * make a separationlist from an attribute
	 * @param rPart
	 * @param elem the separation list attribute / element
	 * @return 
	 */
	protected JDFSeparationList createSeparationList(final KElement rPart, final String elem)
	{
		final String c = rPart.getAttribute(elem, null, null);
		JDFSeparationList sepList = null;
		if (c != null)
		{
			sepList = (JDFSeparationList) rPart.getCreateElement(elem);
			sepList.setSeparations(new VString(c, null));
			rPart.removeAttribute(elem);
		}
		return sepList;
	}

	/**
	 * 
	 * @param xjdfRes
	 * @param jdfNode
	 * @return
	 */
	protected JDFNode getNode(KElement xjdfRes, KElement jdfNode)
	{
		JDFNode theNode = (JDFNode) jdfNode;
		final JDFPart part = (JDFPart) xjdfRes.getElement(ElementName.PART);
		JDFAttributeMap partMap = part == null ? null : part.getAttributeMap();
		if (partMap != null)
		{
			String productID = StringUtil.getNonEmpty(partMap.get(AttributeName.PRODUCTID));
			if (productID != null)
			{
				JDFNode newNode = (JDFNode) theNode.getChildWithAttribute(ElementName.JDF, AttributeName.ID, null, productID, 0, false);
				if (newNode != null)
				{
					theNode = newNode;
				}
			}
			String types = StringUtil.getNonEmpty(partMap.get(XJDFConstants.ProcessTypes));
			if (types != null && theNode.isProduct())
			{
				JDFNode newNode = (JDFNode) theNode.getChildWithAttribute(ElementName.JDF, AttributeName.TYPES, null, types, 0, false);
				if (newNode == null)
				{
					newNode = theNode.addProcessGroup(new VString(types, null));
				}
				theNode = newNode;
			}
		}
		return theNode;
	}

	/**
	 * @param e
	 * @param trackElem
	 */
	protected void cleanRefs(final KElement e, final KElement trackElem)
	{
		final JDFAttributeMap map = e.getAttributeMap();
		if (map == null)
		{
			return;
		}
		final VString keys = map.getKeys();
		if (keys != null)
		{
			for (String key : keys)
			{
				if ((key.endsWith("Ref") || key.endsWith("Refs")) && !key.equals("rRef"))
				{
					final String value = map.get(key);
					cleanRef(e, trackElem, key, value);
				}
			}
		}
	}

	protected void cleanRef(final KElement e, final KElement trackElem, final String val, final String values)
	{
		final VString vValues = StringUtil.tokenize(values, null, false);
		for (final String value : vValues)
		{
			final IDPart p = xjdfToJDFImpl.idMap.get(value);
			if (p != null)
			{
				final String refName = getRefName(val);
				if (refName != null)
				{
					final KElement refOld = trackElem != null ? trackElem.getElement(refName) : null;
					final KElement ref = e.appendElement(refName);
					ref.setAttribute("rRef", p.getID());

					final VJDFAttributeMap vpartmap = p.getPartMap();
					if (vpartmap != null)
					{
						for (int j = 0; j < vpartmap.size(); j++)
						{
							ref.appendElement(ElementName.PART).setAttributes(vpartmap.get(j));
						}
					}
					// we've been here already
					if (ref.isEqual(refOld))
					{
						ref.deleteNode();
					}
				}
				e.removeAttribute(val);
			}
		}
	}

	/**
	 * 
	 * @param e
	 */
	protected void fixAuthor(KElement e)
	{
		String author = e.getAttribute(AttributeName.AUTHOR, null, null);
		if (StringUtil.getNonEmpty(author) != null)
		{
			e.removeAttribute(AttributeName.AUTHOR);
			JDFEmployee emp = (JDFEmployee) e.getCreateElement(ElementName.EMPLOYEE, null, 0);
			emp.setDescriptiveName(author);
		}
		String pID = e.getAttribute(AttributeName.PERSONALID, null, null);
		if (StringUtil.getNonEmpty(pID) != null)
		{
			e.removeAttribute(AttributeName.PERSONALID);
			JDFEmployee emp = (JDFEmployee) e.getCreateElement(ElementName.EMPLOYEE, null, 0);
			emp.setPersonalID(pID);
		}
	}
}