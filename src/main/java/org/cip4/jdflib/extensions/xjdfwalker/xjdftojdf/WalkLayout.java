/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import java.util.List;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Layout elements TODO unmerge stripping and binderysignature and signaturecell see WalkRunList
 */
public class WalkLayout extends WalkStrippingParams
{
	/**
	 *
	 */
	public WalkLayout()
	{
		super();
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return toCheck instanceof JDFLayout;
	}

	/**
	 * split a Layout into a Layout and a StrippingParams
	 *
	 * @param e
	 * @param trackElem
	 */
	private void splitLayout(final KElement e, final KElement trackElem)
	{
		final JDFStrippingParams stripParams = (JDFStrippingParams) e.appendElement(ElementName.STRIPPINGPARAMS);
		final boolean foundSome = moveToStripping(e, stripParams);
		if (foundSome)
		{
			createStrippingPartition(stripParams, (JDFLayout) trackElem);
		}
		stripParams.deleteNode();
	}

	private static final StringArray copyRefs = new StringArray(
			new String[] { XJDFConstants.FilmRef, XJDFConstants.PaperRef, XJDFConstants.PlateRef, XJDFConstants.ProofPaperRef, AttributeName.AUTOMATED });

	private boolean moveToStripping(final KElement e, final JDFStrippingParams stripParams)
	{
		final VString vAtt = stripParams.knownAttributes();
		final JDFAttributeMap map = e.getAttributeMap();
		boolean foundSome = false;
		for (final String s : map.keySet())
		{
			if (copyRefs.contains(s))
			{
				stripParams.setAttribute(s, map.get(s));
				foundSome = true;
			}
			else if (vAtt.contains(s))
			{
				stripParams.setAttribute(s, map.get(s));
				e.removeAttribute(s);
				foundSome = true;
			}

		}
		final VString stripKnown = stripParams.knownElements();
		final List<KElement> vMyElm = e.getChildArray_KElement(null, null, null, true, 0);
		for (final KElement myElm : vMyElm)
		{
			final String localName = myElm.getLocalName();
			if (stripKnown.contains(localName) || localName.endsWith(JDFConstants.REF) && stripKnown.contains(StringUtil.leftStr(localName, -3)))
			{
				stripParams.moveElement(myElm, null);
				foundSome = true;
			}
			else if (ElementName.FILESPEC.equals(localName))
			{
				stripParams.getCreateExternalImpositionTemplate().moveElement(myElm, null);
				foundSome = true;
			}
			else if (ElementName.FITPOLICY.equals(localName))
			{
				// TODO fix when stripping valid
				myElm.deleteNode();
			}
		}
		return foundSome;
	}

	/**
	 *
	 * @param stripParams
	 * @param trackLayout
	 */
	private void createStrippingPartition(final JDFStrippingParams stripParams, final JDFLayout trackLayout)
	{
		JDFNode node = xjdfToJDFImpl.currentJDFNode;
		node = getNode(stripParams.getParentNode_KElement().getParentNode_KElement(), node);
		if (node == null)
		{
			log.error("whazzup - not in xjdf root???");
		}
		else
		{
			JDFNode layoutParent = trackLayout.getParentJDF();
			JDFStrippingParams sp0 = layoutParent == null ? null : (JDFStrippingParams) layoutParent.getResourcePool().getResource(ElementName.STRIPPINGPARAMS, 0, null);
			final JDFAttributeMap partMap = trackLayout.getPartMap();
			final JDFStrippingParams sp;
			if (sp0 != null)
			{
				sp = sp0;
				node.ensureLink(sp, EnumUsage.Input, null);
			}
			else
			{
				sp = (JDFStrippingParams) node.getCreateResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
			}
			final JDFStrippingParams part = (JDFStrippingParams) sp.getCreatePartition(partMap, trackLayout.getPartIDKeys());
			final KElement foo = part.appendElement(ElementName.RESOURCEPOOL);
			xjdfToJDFImpl.walkTree(stripParams, foo);
			final KElement tmpStripParams = foo.getElement(ElementName.STRIPPINGPARAMS);
			tmpStripParams.removeAttribute(AttributeName.CLASS);
			tmpStripParams.removeAttribute(AttributeName.ID);
			tmpStripParams.copyAttribute(AttributeName.STATUS, trackLayout);

			tmpStripParams.removeAttribute(AttributeName.PARTIDKEYS);

			part.copyAttribute(AttributeName.DESCRIPTIVENAME, trackLayout);
			part.copyInto(tmpStripParams, false);
			if (tmpStripParams.getElement_KElement(ElementName.STRIPPINGPARAMS, null, 0) != null)
			{
				sp.addPartIDKey(EnumPartIDKey.BinderySignatureName);
			}
			foo.deleteNode();
		}

	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
	 */
	@Override
	public KElement walk(final KElement xjdfLayout, final KElement jdfLayout)
	{
		splitLayout(xjdfLayout, jdfLayout);
		final KElement walk = super.walk(xjdfLayout, jdfLayout);
		return walk;
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.WalkXElement#getRefName(java.lang.String)
	 */
	@Override
	protected String getRefName(final String val)
	{
		if ("PaperRef".equals(val) || "PlateRef".equals(val) || "ProofRef".equals(val))
		{
			return "MediaRef";
		}
		return super.getRefName(val);
	}

}