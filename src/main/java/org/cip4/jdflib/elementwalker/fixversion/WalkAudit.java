/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib.elementwalker.fixversion;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 * June 7, 2009
 */
public class WalkAudit extends WalkElement
{
	/**
	 *
	 */
	public WalkAudit()
	{
		super();
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return (toCheck instanceof JDFAudit);
	}

	/**
	 * @see WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
	 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
	 * high versions but potentially fail when attempting to move from higher to lower versions
	 */
	@Override
	public KElement walk(final KElement e1, final KElement trackElem)
	{
		final JDFAudit audit = (JDFAudit) e1;
		if (fixVersion.version != null)
		{
			fixID(audit);
			final String author = audit.getAuthor();
			if (fixVersion.lessThanVersion(EnumVersion.Version_1_2))
			{
				mergeAuthor(audit, author);
			}
			else if (author.length() > 0) // version>=1.2 and has author
			{
				splitAuthor(audit, author);
			}
			if (fixVersion.lessThanVersion(EnumVersion.Version_2_0))
			{
				authorToEmployee(audit);
			}
		}
		return super.walk(e1, trackElem);
	}

	/**
	 * @param audit
	 */
	private void fixID(final JDFAudit audit)
	{
		if (this.fixVersion.lessThanVersion(EnumVersion.Version_1_3))
		{
			audit.removeAttribute(AttributeName.ID);
		}
		else
		{
			audit.appendAnchor(null);
		}
	}

	/**
	 * @param audit
	 * @param author
	 */
	private void mergeAuthor(final JDFAudit audit, String author)
	{
		String tmp = audit.getAgentName();
		boolean b = false;
		if (tmp.length() != 0)
		{
			author += "_|_" + tmp;
			b = true;
		}
		tmp = audit.getAgentVersion();
		if (tmp.length() != 0)
		{
			if (!b)
			{
				author += "_|_ ";
			}

			author += "_|_" + tmp;
			b = true;
		}
		audit.removeAttribute(AttributeName.AGENTNAME);
		audit.removeAttribute(AttributeName.AGENTVERSION);
		if (b)
		{
			audit.setAuthor(author);
		}
	}

	/**
	 * @param audit
	 * @param author
	 */
	private void splitAuthor(final JDFAudit audit, final String author)
	{
		final VString tokens = StringUtil.tokenize(author, "_|_", false);
		if (tokens.size() == 3)
		{ // it was previously fixed
			String tmp = tokens.get(0);
			if (!tmp.equals(JDFConstants.EMPTYSTRING) && !tmp.equals(JDFConstants.BLANK))
			{
				audit.setAuthor(tmp);
			}
			tmp = tokens.get(1);
			if (!tmp.equals(JDFConstants.EMPTYSTRING) && !tmp.equals(JDFConstants.BLANK))
			{
				audit.setAgentName(tmp);
			}
			tmp = tokens.get(2);
			if (!tmp.equals(JDFConstants.EMPTYSTRING) && !tmp.equals(JDFConstants.BLANK))
			{
				audit.setAgentVersion(tmp);
			}
		}
	}

	/**
	 * @param audit
	 */
	private void authorToEmployee(final JDFAudit audit)
	{
		if (!this.fixVersion.lessThanVersion(EnumVersion.Version_1_4))
		{
			final String finalAuthor = StringUtil.getNonEmpty(audit.getAuthor());
			if (finalAuthor != null && !audit.hasChildElement(ElementName.EMPLOYEE, null))
			{
				audit.getCreateEmployee(0).setDescriptiveName(finalAuthor);
			}
			audit.removeAttribute(AttributeName.AUTHOR);
		}
	}
}