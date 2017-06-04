/*
 * The CIP4 Software License, Version 1.0
 *
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import java.util.Vector;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class MessagePoolHelper extends BaseXJDFHelper
{

	/**
	 * @param pool
	 */
	public MessagePoolHelper(KElement pool)
	{
		super(pool);
	}

	/**
	 *
	 * @param sh
	 * @return
	 */
	public MessageResourceHelper getMessageResourceHelper(SetHelper sh)
	{
		String name = sh == null ? null : sh.getName();
		if (name == null)
		{
			return null;
		}
		VElement v = theElement.getXPathElementVector("*/ResourceInfo/@ResourceSet[@Name=\"" + name + "\"]", 0);
		if (v != null)
		{
			for (KElement e : v)
			{
				SetHelper thisHelper = new SetHelper(e);
				if (!ContainerUtil.equals(sh.getUsage(), thisHelper.getUsage()))
					continue;
				if (!ContainerUtil.equals(sh.getProcessUsage(), thisHelper.getProcessUsage()))
					continue;
				KElement message = e.getParentNode_KElement().getParentNode_KElement();
				return newMessageResourceHelper(message);
			}
		}
		return null;
	}

	/**
	 *
	 * @param message
	 * @return
	 */
	MessageResourceHelper newMessageResourceHelper(KElement message)
	{
		return new MessageResourceHelper(message);
	}

	/**
	 *
	 * @param sh
	 * @return
	 */
	public MessageResourceHelper getCreateMessageResourceHelper(SetHelper sh)
	{
		String name = sh == null ? null : sh.getName();
		if (name == null)
		{
			return null;
		}
		MessageResourceHelper ah = getMessageResourceHelper(sh);
		if (ah == null)
		{
			ah = new MessageResourceHelper(theElement.appendElement(XJDFConstants.AuditResource));
			KElement set = ah.getRoot().appendElement(ElementName.RESOURCEINFO).appendElement(XJDFConstants.ResourceSet);
			SetHelper shNew = new SetHelper(set);
			shNew.setName(name);
			shNew.setUsage(sh.getUsage());
			shNew.setProcessUsage(sh.getProcessUsage());
		}
		ah.cleanUp();
		return ah;
	}

	/**
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#cleanUp()
	 */
	@Override
	public void cleanUp()
	{
		Vector<MessageHelper> vA = getAuditHelpers();
		if (vA != null)
		{
			for (MessageHelper ah : vA)
			{
				ah.cleanUp();
			}
		}
		super.cleanUp();
	}

	/**
	 *
	 * @return
	 */
	public Vector<MessageHelper> getAuditHelpers()
	{
		Vector<MessageHelper> vA = new Vector<MessageHelper>();
		VElement v = theElement.getChildElementVector(null, null);
		for (KElement e : v)
		{
			if (!XJDFConstants.Header.equals(e.getLocalName()))
			{
				vA.add(getMessageHelper(e));
			}
		}
		return vA;
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	public MessageHelper getMessageHelper(KElement e)
	{
		if (e == null)
			return null;
		String name = e.getLocalName();
		if (XJDFConstants.AuditResource.equals(name))
			return newMessageResourceHelper(e);
		else if (XJDFConstants.AuditStatus.equals(name))
			return new MessageHelper(e);
		else
			return new MessageHelper(e);
	}

	/**
	 *
	 * @param elementName
	 * @return
	 */
	public MessageHelper appendMessage(String elementName)
	{
		if (StringUtil.getNonEmpty(elementName) == null)
		{
			log.error("Cannot append null element");
			return null;
		}
		KElement e = theElement.appendElement(elementName);
		MessageHelper messageHelper = getMessageHelper(e);
		messageHelper.cleanUp();
		return messageHelper;
	}
}
