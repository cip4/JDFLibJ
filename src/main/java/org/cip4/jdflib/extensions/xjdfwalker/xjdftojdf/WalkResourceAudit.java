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

import java.util.List;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 *         at this point only a dummy since we have a specific WalkResourceAudit child
 *
 *         TODO how should resource consumption be tracked?
 */
public class WalkResourceAudit extends WalkAudit
{
	/**
	 *
	 */
	public WalkResourceAudit()
	{
		super();
	}

	/**
	 * @param xjdf
	 * @return true if must continue
	 */
	@Override
	public KElement walk(final KElement xjdf, final KElement jdf)
	{
		moveFromSender(xjdf, xjdf.getElement(XJDFConstants.Header));
		final JDFResourceInfo ri = (JDFResourceInfo) xjdf.getElement(ElementName.RESOURCEINFO);
		final KElement ret = super.walk(xjdf, jdf);
		linkSet(ri, ret);
		return null;
	}

	private void linkSet(final JDFResourceInfo ri, final KElement resAudit)
	{

		if (ri == null)
			return;

		final XJDFHelper h = xjdfToJDFImpl.xjdf;
		final KElement resSet = ri.getElement(XJDFConstants.ResourceSet);
		final SetHelper sha = resSet == null ? null : new SetHelper(resSet);
		final SetHelper sh = sha == null ? null : h.getSet(sha.getName(), sha.getUsage(), sha.getProcessUsage());
		ResourceHelper ph = sh == null ? null : sh.getPartition(ri.getPartMapVector());
		if (ph == null)
		{
			ph = sh == null ? null : sh.getPartition(0);
		}
		final String id = ph == null ? null : xjdfToJDFImpl.idMap.get(ph.getID()).getID();

		if (id != null)
		{
			final JDFResourceLink link = (JDFResourceLink) resAudit.appendElement(sh.getName() + "Link", null);
			link.setAttribute(AttributeName.RREF, id);
			link.setUsage(sha.getUsage());
			final VJDFAttributeMap partMapVector = sha.getPartMapVector();
			partMapVector.removeKey(XJDFConstants.ProductPart);
			if (partMapVector != null)
			{
				for (final JDFAttributeMap partMap : partMapVector)
				{
					final String sheetName = partMap.get(AttributeName.SHEETNAME);
					String signatureName = partMap.get(AttributeName.SIGNATURENAME);
					if (StringUtil.getNonEmpty(sheetName) != null && StringUtil.getNonEmpty(signatureName) == null)
					{
						signatureName = XJDFToJDFConverter.SIG + sheetName;
						partMap.put(AttributeName.SIGNATURENAME, signatureName);
					}
				}
			}
			partMapVector.unify();
			link.setPartMapVector(partMapVector);
			final JDFAmountPool ap = link.appendAmountPool();
			final List<JDFAmountPool> aps = sha.getRoot().getChildArrayByClass(JDFAmountPool.class, true, 0);
			for (final JDFAmountPool ap1 : aps)
			{
				final VElement partAmounts = ap1.getChildElementVector(ElementName.PARTAMOUNT, null);
				for (final KElement pa : partAmounts)
				{
					xjdfToJDFImpl.walkTree(pa, ap);
				}
			}
		}
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return "AuditResource".equals(toCheck.getLocalName());
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf.WalkXElement#getJDFName(org.cip4.jdflib.core.KElement)
	 */
	@Override
	String getJDFName(final KElement e)
	{
		return ElementName.RESOURCEAUDIT;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
	 */
	@Override
	public VString getElementNames()
	{
		return VString.getVString(XJDFConstants.AuditResource, null);
	}

}