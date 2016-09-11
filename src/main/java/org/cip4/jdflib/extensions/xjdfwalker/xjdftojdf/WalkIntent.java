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
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for non partitioned intent elements
 */
public class WalkIntent extends WalkXElement
{
	/**
	 * 
	 */
	public WalkIntent()
	{
		super();
	}

	/**
	 * @param e
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement e, final KElement trackElem)
	{
		final JDFNode parent = (JDFNode) trackElem;
		final JDFNode root = parent.getJDFRoot();
		EnumUsage inOut = EnumUsage.getEnum(e.getAttribute(AttributeName.USAGE));
		if (inOut == null)
		{
			inOut = EnumUsage.Input;
		}
		String id = e.getAttribute(AttributeName.ID, null, null);
		if (id == null)
		{
			// we need an id to copy. technically no id is invalid, but... whatever
			id = "r" + KElement.uniqueID(0);
			e.setAttribute(AttributeName.ID, id);
		}
		JDFResource r = null;
		final KElement idElem = parent.getCreateResourcePool().getChildWithAttribute(null, "ID", null, id, 0, true);
		if (idElem instanceof JDFResource)
		{
			r = (JDFResource) idElem;
		}
		else
		{
			r = (JDFResource) root.getChildWithAttribute(null, "ID", null, id, 0, false);
			if (r != null)
			{
				final JDFResourcePool rp = root.getCreateResourcePool();
				if (!rp.equals(r.getParentNode_KElement()))
				{
					rp.moveElement(r, null);
				}
			}
		}
		if (r == null)
		{
			final IntentHelper h = new IntentHelper(e);
			String name = h.getName();
			if (XJDFConstants.AssemblingIntent.equals(name))
			{
				name = ElementName.INSERTINGINTENT;
			}
			if (name != null)
			{
				r = parent.addResource(name, null);
				r.removeAttribute(AttributeName.STATUS);
			}
		}
		if (r != null)
		{
			r.setAttributes(e);
			if (r.getResourceClass() == null)
			{
				r.setResourceClass(EnumResourceClass.Intent);
			}
			final JDFResourceLink rl = parent.ensureLink(r, inOut, null);
			if (rl != null)
			{
				rl.setrRef(id);
				r.removeAttribute(AttributeName.USAGE);
				rl.moveAttribute(AttributeName.PROCESSUSAGE, r);
				rl.moveAttribute(AttributeName.AMOUNT, r);
				rl.moveAttribute(AttributeName.ACTUALAMOUNT, r);
				rl.moveAttribute(AttributeName.MAXAMOUNT, r);
				rl.moveAttribute(AttributeName.MINAMOUNT, r);
			}
			r.removeAttribute(AttributeName.NAME);
			r.removeAttribute(AttributeName.USAGE);
			if (!r.hasAttribute(AttributeName.STATUS))
			{
				r.setResStatus(EnumResStatus.Available, true);
			}
		}
		return r;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		final KElement parent = toCheck.getParentNode_KElement();
		final boolean bL1 = parent != null && parent.getLocalName().equals(ProductHelper.PRODUCT);
		return bL1 && super.matches(toCheck) && toCheck.getLocalName().equals(IntentHelper.INTENT);
	}
}