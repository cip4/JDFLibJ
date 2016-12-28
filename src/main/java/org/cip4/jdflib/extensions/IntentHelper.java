/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of
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

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class IntentHelper extends BaseXJDFHelper
{

	/**
	 * 
	 */
	public static boolean bSpanAsAttribute = true;

	public static final String INTENT = XJDFConstants.Intent;

	/**
	 * @param intent
	 */
	public IntentHelper(final KElement intent)
	{
		super();
		theElement = intent;
	}

	/**
	 * 
	 * @param toCheck
	 */
	public static boolean isIntentResource(KElement toCheck)
	{
		if (toCheck == null)
			return false;
		KElement parent = toCheck.getParentNode_KElement();
		if (parent == null)
			return false;

		return (INTENT.equals(parent.getLocalName())) ? new IntentHelper(parent).getResource() == toCheck : false;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return "IntentHelper: " + theElement;
	}

	/**
	 * @return the "Intent" element
	 */

	public KElement getIntent()
	{
		return theElement;
	}

	/**
	 * @return the detailed intent resource
	 */
	public KElement getResource()
	{
		String name = theElement.getAttribute(AttributeName.NAME, null, null);
		if (name != null)
		{
			return theElement.getElement(name);
		}
		else
		{
			KElement e = theElement.getFirstChildElement();
			while (e != null)
			{
				if (!(e instanceof JDFPart) && !(e instanceof JDFGeneralID) && !(e instanceof JDFComment))
				{
					return e;
				}
				e = e.getNextSiblingElement();
			}
		}
		return null;
	}

	/**
	 * @return the detailed intent resource
	 */
	public KElement getCreateResource()
	{
		String name = theElement.getAttribute(AttributeName.NAME, null, null);
		if (name != null)
		{
			return theElement.getCreateElement(name);
		}
		else
		{
			KElement e = theElement.getFirstChildElement();
			while (e != null)
			{
				if (!(e instanceof JDFPart) && !(e instanceof JDFGeneralID) && !(e instanceof JDFComment))
				{
					return e;
				}
				e = e.getNextSiblingElement();
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		String name = theElement.getAttribute(AttributeName.NAME, null, null);
		if (name == null)
		{
			KElement r = getResource();
			if (r != null)
			{
				name = r.getLocalName();
				theElement.setAttribute(AttributeName.NAME, name);
			}
		}
		return name;
	}

	/**
	 * @param att
	 * @param val
	 * @param dataType - if null always set as simple attribute
	 */
	public void setSpan(final String att, final String val, final String dataType)
	{
		final KElement resource = getCreateResource();
		setSpan(resource, att, val, dataType);
	}

	/**
	 * @param att
	 * @param val
	 *
	 */
	public void setSpan(final String att, final String val)
	{
		setSpan(att, val, null);
	}

	/**
	 * get the span attribute - initially try attribute, else Element/@Actual
	 * @param spanPath
	 * @return 
	 */
	public String getSpan(final String spanPath)
	{
		KElement resource = getResource();
		if (resource == null)
			return null;
		String elem = StringUtil.removeToken(spanPath, -1, JDFConstants.SLASH);
		if (elem != null)
		{
			resource = resource.getXPathElement(elem);
		}
		if (resource == null)
		{
			return null;
		}
		String spanName = StringUtil.token(spanPath, -1, JDFConstants.SLASH);
		String s = resource.getAttribute(spanName, null, null);
		if (s == null)
		{
			s = resource.getXPathAttribute(spanName + "/@Actual", null);
		}
		return s;
	}

	/**
	 * set the span in a subelement
	 * @param resource
	 * @param spanPath
	 * @param val
	 * @param dataType
	 */
	public void setSpan(KElement resource, final String spanPath, final String val, String dataType)
	{
		String elem = StringUtil.removeToken(spanPath, -1, JDFConstants.SLASH);
		if (elem != null)
		{
			resource = resource.getCreateXPathElement(elem);
		}
		if (resource == null)
		{
			return;
		}
		String spanName = StringUtil.token(spanPath, -1, JDFConstants.SLASH);
		if (bSpanAsAttribute || dataType == null)
		{
			resource.setAttribute(spanName, val);
		}
		else
		{
			if (!dataType.endsWith("Span"))
			{
				dataType += "Span";
			}
			final KElement span = resource.getCreateElement(dataType);
			span.setAttribute(AttributeName.NAME, spanName);
			span.setAttribute(AttributeName.ACTUAL, val);
		}
	}

	/**
	 * @see org.cip4.jdflib.extensions.BaseXJDFHelper#cleanUp()
	 */
	@Override
	public void cleanUp()
	{

	}
}
