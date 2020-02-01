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
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.jmf.JDFSignal;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen <br/>
 *
 */
public class WalkSignalResource extends WalkSignal
{
	/**
	 *
	 */
	public WalkSignalResource()
	{
		super();
	}

	/**
	 * @see org.cip4.jdflib.extensions.XJDF20.WalkMessage#matches(org.cip4.jdflib.core.KElement)
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return !jdfToXJDF.isRetainAll() && (toCheck instanceof JDFSignal) && ElementName.RESOURCE.equals(((JDFSignal) toCheck).getType());
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkMessage#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		moveFromQuParams(jdf);
		splitAmounts(jdf);
		return super.walk(jdf, xjdf);
	}

	void splitAmounts(final KElement jdf)
	{
		final List<JDFResourceInfo> vri = jdf.getChildArrayByClass(JDFResourceInfo.class, false, 0);
		for (final JDFResourceInfo ri : vri)
		{
			final double aa = ri.getAmountPoolSumDouble(AttributeName.ACTUALAMOUNT, null);
			if (aa > 0)
			{
				final double a = ri.getAmountPoolSumDouble(AttributeName.AMOUNT, null);
				if (a > 0 && !ri.hasNonEmpty(AttributeName.SCOPE))
				{

					final JDFResourceInfo ri2 = (JDFResourceInfo) jdf.copyElement(ri, ri);

					for (final JDFPartAmount pa : ri2.getChildArrayByClass(JDFPartAmount.class, true, 0))
					{
						pa.removeAttribute(AttributeName.ACTUALAMOUNT);
					}
					ri2.removeAttribute(AttributeName.ACTUALAMOUNT);
					ri2.setAttribute(AttributeName.SCOPE, "Estimate");
				}
				for (final JDFPartAmount pa : ri.getChildArrayByClass(JDFPartAmount.class, true, 0))
				{
					pa.removeAttribute(AttributeName.AMOUNT);
					pa.renameAttribute(AttributeName.ACTUALAMOUNT, AttributeName.AMOUNT);
				}
				ri.removeAttribute(AttributeName.AMOUNT);
				ri.renameAttribute(AttributeName.ACTUALAMOUNT, AttributeName.AMOUNT);
				if (!ri.hasNonEmpty(AttributeName.SCOPE))
					ri.setAttribute(AttributeName.SCOPE, "Job");

			}
		}

	}

	final static private String[] moveAtts = new String[] { AttributeName.JOBID, AttributeName.JOBPARTID, AttributeName.QUEUEENTRYID, AttributeName.SCOPE };

	/**
	 *
	 * @param jdf
	 */
	void moveFromQuParams(final KElement jdf)
	{
		final JDFResourceQuParams rqp = ((JDFSignal) jdf).getCreateResourceQuParams(0);
		if (rqp != null)
		{
			final VElement vRI = jdf.getChildElementVector(ElementName.RESOURCEINFO, null);
			if (vRI != null)
			{
				for (final KElement e : vRI)
				{
					final JDFResourceInfo ri = (JDFResourceInfo) e;
					for (final String att : moveAtts)
					{
						if (!ri.hasAttribute(att))
						{
							ri.copyAttribute(att, rqp);
						}
					}
				}
			}
			rqp.deleteNode();
		}
	}

}