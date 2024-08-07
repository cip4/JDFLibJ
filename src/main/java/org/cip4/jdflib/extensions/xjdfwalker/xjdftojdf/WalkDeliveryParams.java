/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import java.util.Iterator;
import java.util.List;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFDrop;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Layout elements
 *
 */
public class WalkDeliveryParams extends WalkResource
{
	/**
	 *
	 */
	public WalkDeliveryParams()
	{
		super();
	}

	/**
	 *
	 * @param xjdfDeliveryParams
	 * @param jdfDeliveryParams
	 * @return
	 */
	boolean moveToDrop(final KElement xjdfDeliveryParams)
	{
		final JDFDrop drop = ((JDFDeliveryParams) xjdfDeliveryParams).appendDrop();
		final VString vAtt = drop.knownAttributes();
		final JDFAttributeMap map = xjdfDeliveryParams.getAttributeMap();
		final Iterator<String> it = map.getKeyIterator();
		boolean foundSome = false;
		while (it.hasNext())
		{
			final String s = it.next();
			if (vAtt.contains(s))
			{
				drop.setAttribute(s, map.get(s));
				xjdfDeliveryParams.removeAttribute(s);
				foundSome = true;
			}
		}
		final String dropID = xjdfDeliveryParams.getXPathAttribute("../Part/@DropID", null);
		if (StringUtil.getNonEmpty(dropID) != null)
		{
			foundSome = true;
			drop.setDropID(dropID);
		}
		foundSome = ensureContact(drop, dropID) || foundSome;
		final VString dropKnown = drop.knownElements();
		final List<KElement> vMyElm = xjdfDeliveryParams.getChildArray_KElement(null, null, null, true, 0);
		for (final KElement myElm : vMyElm)
		{
			final String localName = myElm.getLocalName();
			if (dropKnown.contains(localName) || localName.endsWith(JDFConstants.REF) && dropKnown.contains(StringUtil.leftStr(localName, -3)))
			{
				drop.moveElement(myElm, null);
				foundSome = true;
			}
		}
		if (!foundSome)
			drop.deleteNode();
		return foundSome;
	}

	boolean ensureContact(final JDFDrop drop, final String dropID)
	{
		final SetHelper shc = xjdfToJDFImpl.xjdf.getSet(ElementName.CONTACT, EnumUsage.Input);
		if (shc != null)
		{
			final JDFAttributeMap m = new JDFAttributeMap(XJDFConstants.ContactType, EnumContactType.Delivery);
			if (!StringUtil.isEmpty(dropID))
				m.put(AttributeName.DROPID, dropID);
			final ResourceHelper rhc = shc.getPartition(m);
			if (rhc != null)
			{
				rhc.ensureReference(drop, null);
				return true;
			}
		}
		return false;

	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
	 */
	@Override
	public KElement walk(final KElement xjdfDelParams, final KElement jdfDelParams)
	{
		moveToDrop(xjdfDelParams);
		final KElement jdfDelParams2;
		if (jdfDelParams.hasNonEmpty(AttributeName.DROPID))
		{
			jdfDelParams2 = jdfDelParams.getParentNode_KElement();
			jdfDelParams.deleteNode();
			((JDFResource) jdfDelParams2).getResourceRoot().removeFromAttribute(AttributeName.PARTIDKEYS, AttributeName.DROPID, null, null, 0);
		}
		else
		{
			jdfDelParams2 = jdfDelParams;
		}
		final KElement walk = super.walk(xjdfDelParams, jdfDelParams2);
		return walk;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
	 */
	@Override
	public VString getElementNames()
	{
		return VString.getVString(ElementName.DELIVERYPARAMS, null);
	}

}