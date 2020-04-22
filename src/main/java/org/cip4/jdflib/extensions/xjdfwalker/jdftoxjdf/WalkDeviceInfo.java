/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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

import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumDeviceOperationMode;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFDeviceList;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
 */
public class WalkDeviceInfo extends WalkJDFSubElement
{
	/**
	 *
	 */
	public WalkDeviceInfo()
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
		return !jdfToXJDF.isRetainAll();
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkElement#updateAttributes(org.cip4.jdflib.datatypes.JDFAttributeMap)
	 */
	@Override
	protected void updateAttributes(final JDFAttributeMap map)
	{
		updateDeviceStatus(map);
		super.updateAttributes(map);
	}

	/**
	 *
	 * @param map
	 */
	void updateDeviceStatus(final JDFAttributeMap map)
	{
		map.renameKey(AttributeName.DEVICESTATUS, AttributeName.STATUS);
		String status = map.get(AttributeName.STATUS);
		final String opMode = map.remove(AttributeName.DEVICEOPERATIONMODE);

		final EnumDeviceOperationMode eOpMode = EnumDeviceOperationMode.getEnum(opMode);
		if (EnumDeviceOperationMode.NonProductive.equals(eOpMode) || EnumDeviceOperationMode.Maintenance.equals(eOpMode))
		{
			status = "NonProductive";
		}
		else if (StringUtil.getNonEmpty(status) != null)
		{
			if ("Unknown".equals(status) || "Down".equals(status))
			{
				status = "Offline";
			}
			else if ("Running".equals(status))
			{
				status = "Production";
			}
		}
		map.put(AttributeName.STATUS, status);
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
	 */
	@Override
	public VString getElementNames()
	{
		return new VString(ElementName.DEVICEINFO, null);
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkElement#setAttributes(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
	 */
	@Override
	protected void setAttributes(final KElement jdf, final KElement eNew)
	{
		super.setAttributes(jdf, eNew);
		final KElement parent = eNew.getParentNode_KElement();
		if (parent != null)
		{
			parent.moveAttribute(AttributeName.DEVICEID, eNew);
		}
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkJDFElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		final KElement e = jdf.getParentNode_KElement();
		if (e instanceof JDFDeviceList)
		{
			final String devID = jdf.getNonEmpty(AttributeName.DEVICEID);
			if (devID != null)
			{
				jdf.getCreateElement(ElementName.DEVICE).setAttribute(AttributeName.DEVICEID, devID);
			}
			return xjdf;
		}
		return super.walk(jdf, xjdf);
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkElement#removeUnusedElements(org.cip4.jdflib.core.KElement)
	 */
	@Override
	protected void removeUnusedElements(final KElement jdf)
	{
		final JDFDevice dev = (JDFDevice) jdf.getElement(ElementName.DEVICE);
		if (dev != null)
		{
			final String devID = dev.getDeviceID();
			if (devID != null && jdf.getNonEmpty(AttributeName.DEVICEID) == null)
			{
				jdf.setAttribute(AttributeName.DEVICEID, devID);
			}
			dev.deleteNode();
		}
		super.removeUnusedElements(jdf);
	}

}