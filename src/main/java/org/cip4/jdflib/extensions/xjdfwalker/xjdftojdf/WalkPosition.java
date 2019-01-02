/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
 */
public class WalkPosition extends WalkXElement
{
	/**
	 *
	 */
	public WalkPosition()
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
		return toCheck instanceof JDFPosition;
	}

	/**
	 * @param e
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement e, final KElement trackElem)
	{
		final JDFStrippingParams stripParams = (trackElem instanceof JDFStrippingParams) ? (JDFStrippingParams) trackElem : null;
		doCells(e, stripParams);
		final KElement walk = super.walk(e, trackElem);
		moveBSRef(e, stripParams);
		return walk;
	}

	/**
	 *
	 * move BinderySig ref one down
	 *
	 * @param e
	 * @param stripParams
	 */
	private void moveBSRef(final KElement e, final JDFStrippingParams stripParams)
	{
		if (stripParams != null)
		{
			final KElement bsRef = e.getElement(ElementName.BINDERYSIGNATURE + "Ref");
			if (bsRef != null)
			{
				stripParams.moveElement(bsRef, null);
			}
			else
			{
				final String bsID = e.getNonEmpty(XJDFConstants.BinderySignatureID);
				if (bsID != null)
				{
					e.removeAttribute(XJDFConstants.BinderySignatureID);
					final SetHelper bsSet = XJDFHelper.getHelper(xjdfToJDFImpl.xjdf).getSet(ElementName.BINDERYSIGNATURE, 0);
					if (bsSet != null)
					{
						bsSet.getPartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, bsID));
						final String id = bsSet.getID();
						final JDFRefElement re = (JDFRefElement) stripParams.appendElement(ElementName.BINDERYSIGNATURE + "Ref");
						re.setrRef(id);
						re.setPartMap(new JDFAttributeMap(AttributeName.BINDERYSIGNATURENAME, bsID));
					}
				}
			}
		}
	}

	/**
	 *
	 * split SignatureCells and StripCellParams appropriately
	 *
	 * @param e
	 * @param stripParams
	 */
	private void doCells(final KElement e, final JDFStrippingParams stripParams)
	{
		if (stripParams != null && stripParams.getStripCellParams() == null)
		{
			final String id = StringUtil.getNonEmpty(e.getAttribute(ElementName.BINDERYSIGNATURE + "Ref", null, null));
			final XJDFHelper xh = XJDFHelper.getHelper(e);
			if (xh != null)
			{
				final ResourceHelper bsh = xh.getPartition(id);
				final VElement vSigCell = bsh == null ? null : bsh.getResource().getChildElementVector(ElementName.SIGNATURECELL, null);
				if (vSigCell != null)
				{
					for (final KElement sigCell : vSigCell)
					{
						doCell(stripParams, sigCell);
					}
				}
			}
		}
	}

	void doCell(final JDFStrippingParams stripParams, final KElement sigCell)
	{
		final JDFStripCellParams stripCell = stripParams.appendStripCellParams();
		final VString stripCellKnown = stripCell.knownAttributes();
		final JDFAttributeMap sigCelMap = sigCell.getAttributeMap();
		sigCelMap.reduceMap(stripCellKnown);
		if (!sigCelMap.isEmpty())
		{
			stripCell.setAttributes(sigCelMap);
			sigCell.removeAttributes(sigCelMap.keySet());
		}
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.WalkXElement#updateAttributes(org.cip4.jdflib.core.KElement)
	 */
	@Override
	protected void updateAttributes(final KElement elem)
	{
		elem.removeAttribute(XJDFConstants.BinderySignatureID);
		super.updateAttributes(elem);
	}
}