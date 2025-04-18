/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.node.JDFActivity;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen at this point only a dummy since we have a specific WalkResourceAudit child
 */
public class WalkEmployee extends WalkResource
{

	/**
	 *
	 */
	public WalkEmployee()
	{
		super();
	}

	/**
	 * the new name
	 * 
	 * @param jdf
	 * @return
	 */
	@Override
	protected String getXJDFName(final KElement jdf)
	{
		return ElementName.CONTACT;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return !jdfToXJDF.isRetainAll() && toCheck instanceof JDFEmployee;
	}

	/**
	 *
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkJDFElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
	 */
	@Override
	public KElement walk(KElement jdf, KElement xjdf)
	{
		KElement parent = jdf.getParentNode_KElement();
		JDFEmployee emp = (JDFEmployee) jdf;
		if ((parent instanceof JDFAudit) || (parent instanceof JDFMessage))
		{
			String personalID = StringUtil.getNonEmpty(emp.getPersonalID());
			xjdf.setAttribute(AttributeName.PERSONALID, personalID);
			String author = StringUtil.getNonEmpty(emp.getDescriptiveName());
			xjdf.setAttribute(AttributeName.AUTHOR, author);
			return null;
		}
		else if (parent instanceof JDFJMF)
		{
			return null;
		}
		else if (parent instanceof JDFDeviceInfo)
		{
			JDFActivity a = ((JDFDeviceInfo) xjdf).appendActivity();
			a.setPersonalID(emp.getPersonalID());
			return null;
		}
		else
		{
			if (parent instanceof JDFNodeInfo)
			{
				jdf.appendAttribute(AttributeName.ROLES, "CSR", null, JDFConstants.BLANK, true);
				KElement e = moveToContact(jdf);
				KElement walk = super.walk(e, xjdf);
				walk.moveAttribute(XJDFConstants.ExternalID, e);
				return walk;
			}
			else
			{
				KElement e = moveToContact(jdf);
				KElement walk = super.walk(e, xjdf);

				ResourceHelper helper = ResourceHelper.getHelper(xjdf);
				helper.appendPartMap(new JDFAttributeMap(XJDFConstants.ContactType, ElementName.EMPLOYEE));
				return walk;
			}
		}
	}

	/**
	 *
	 * @param jdfEmployee
	 * @return
	 */
	public KElement moveToContact(KElement jdfEmployee)
	{
		JDFContact contact = (JDFContact) safeRename(jdfEmployee, ElementName.CONTACT);

		contact.renameAttribute(AttributeName.PERSONALID, XJDFConstants.ExternalID, null, null);
		contact.renameAttribute(AttributeName.ROLES, AttributeName.CONTACTTYPEDETAILS, null, null);
		return contact;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
	 */
	@Override
	public VString getElementNames()
	{
		return new VString(ElementName.EMPLOYEE, null);
	}
}