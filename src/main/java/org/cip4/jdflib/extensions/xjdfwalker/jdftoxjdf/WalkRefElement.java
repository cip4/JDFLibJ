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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.List;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.intent.JDFProductionIntent;
import org.cip4.jdflib.util.ContainerUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkRefElement extends WalkJDFElement
{
	/**
	 *
	 */
	public WalkRefElement()
	{
		super();
	}

	/**
	 * @param jdf
	 * @param xjdf
	 * @return the created resource in this case just remove the pool
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		final JDFRefElement refElem = (JDFRefElement) jdf;
		if (mustInline(refElem.getRefLocalName()) && refElem.getTarget() != null)
		{
			try
			{
				final JDFElement e = refElem.inlineRef();
				if (e != null && !(e instanceof JDFRefElement))
				{
					jdfToXJDF.walkTree(e, xjdf);
				}
			}
			catch (final JDFException x)
			{
				log.error("Problems converting refElement: " + refElem.getNodeName(), x);
			}
		}
		else if (isProduct(refElem))
		{
			refProduct(refElem, xjdf);
		}
		else if (wantRef(refElem))
		{
			makeRefAttribute(refElem, xjdf);
		}
		return null;
	}

	private boolean wantRef(final JDFRefElement refElem)
	{
		return !(refElem.getParentNode_KElement() instanceof JDFProductionIntent);
	}

	/**
	 *
	 * @param refElem
	 * @param xjdf
	 */
	private void refProduct(final JDFRefElement refElem, final KElement xjdf)
	{
		final String attName = "ItemRef";
		final String id = jdfToXJDF.getProduct(refElem.getrRef());
		xjdf.appendAttribute(attName, id, null, " ", true);
	}

	/**
	 *
	 * @param refElem
	 * @return
	 */
	private boolean isProduct(final JDFRefElement refElem)
	{
		return jdfToXJDF.getProduct(refElem.getrRef()) != null;
	}

	/**
	 * @param re
	 * @param xjdf
	 */
	protected void makeRefAttribute(final JDFRefElement re, final KElement xjdf)
	{
		final JDFResource target = re.getTarget();
		final JDFResourceLink rl = getLinkForRef(re, target);
		final KElement refRoot = getRefRoot(xjdf);
		if (rl != null)
		{
			setResource(rl, target, refRoot);
		}
		final VJDFAttributeMap partMapVector = rl == null ? null : rl.getPartMapVector();
		final boolean overlap = VJDFAttributeMap.isEmpty(partMapVector) || partMapVector.overlapsMap(re.getPartMap());
		final List<KElement> v = overlap ? setResource(re, target, refRoot) : null;
		if (v != null)
		{
			final String attName = getRefName(re);
			for (final KElement ref : v)
			{
				if (!ref.hasNonEmpty(AttributeName.ID))
				{
					ref.setID(ref.generateDotID(AttributeName.ID, null));
				}
				xjdf.appendAttribute(attName, ref.getID(), null, JDFConstants.BLANK, true);
			}
		}
		re.deleteNode();
	}

	/**
	 *
	 * @param re
	 * @param xjdf
	 */
	protected void makeSetRefAttribute(final JDFRefElement re, final KElement xjdf)
	{
		final String attName = getRefName(re);
		final List<KElement> v = setResource(re, re.getTarget(), jdfToXJDF.newRoot);
		// we want a ref to the set rather than the standard ref to the list of elements
		if (!ContainerUtil.isEmpty(v))
		{
			final KElement ref = v.get(0).getParentNode_KElement();
			xjdf.setAttribute(attName, ref.getID());
		}
		re.deleteNode();
	}

	/**
	 * @param re
	 * @param target
	 * @return
	 */
	private JDFResourceLink getLinkForRef(final JDFRefElement re, final JDFResource target)
	{
		JDFResourceLink rl = null;
		if (this.jdfToXJDF.oldRoot != null)
		{
			final JDFResourceLinkPool resourceLinkPool = this.jdfToXJDF.oldRoot.getResourceLinkPool();
			rl = resourceLinkPool != null ? resourceLinkPool.getLink(target, null, null) : null;
		}
		return rl;
	}

	/**
	 * @param xjdf
	 * @return
	 */
	private KElement getRefRoot(final KElement xjdf)
	{
		KElement ret = null;
		if (xjdf != null)
		{
			ret = xjdf.getDeepParent(ElementName.RESOURCEINFO, 0);
		}
		return ret == null ? this.jdfToXJDF.newRoot : ret;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return toCheck instanceof JDFRefElement;
	}
}