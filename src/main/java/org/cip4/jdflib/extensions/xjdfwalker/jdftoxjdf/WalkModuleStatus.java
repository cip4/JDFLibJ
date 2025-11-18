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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFEnums.eDeviceStatus;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.resource.JDFModuleStatus;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
 */
public class WalkModuleStatus extends WalkJDFSubElement
{
	/**
	 *
	 */
	public WalkModuleStatus()
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
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
	 */
	@Override
	public VString getElementNames()
	{
		return new VString(ElementName.MODULESTATUS, null);
	}

	@Override
	protected void updateAttributes(final JDFAttributeMap map)
	{
		super.updateAttributes(map);
		final String id = map.remove(AttributeName.MODULEINDEX);
		if (id != null && map.get(AttributeName.MODULEID) == null)
		{
			map.put(AttributeName.MODULEID, StringUtil.token(id, 0, null));
		}
		String deviceStatus = map.remove(AttributeName.DEVICESTATUS);
		if ("Running".equals(deviceStatus))
		{
			deviceStatus = eDeviceStatus.Production.name();
		}
		map.putNotNull(AttributeName.STATUS, deviceStatus);

		map.remove(AttributeName.COMBINEDPROCESSINDEX);
	}

	@Override
	protected String getXJDFName(final KElement jdf)
	{
		return ElementName.MODULEINFO;
	}

	/**
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkJDFElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
	 */
	public KElement walkOld(final KElement jdf, final KElement xjdf)
	{
		final boolean parentIdle;
		if (xjdf instanceof JDFJobPhase)
		{
			final EnumNodeStatus ns = ((JDFJobPhase) xjdf).getStatus();
			parentIdle = !EnumNodeStatus.InProgress.equals(ns) && !EnumNodeStatus.Setup.equals(ns) && !EnumNodeStatus.Cleanup.equals(ns);
		}
		else
		{
			String deviceStatus = (xjdf instanceof JDFDeviceInfo) ? xjdf.getAttribute(AttributeName.STATUS) : null;
			if ("Production".equals(deviceStatus))
			{
				deviceStatus = "Running";
			}
			final EnumDeviceStatus eDeviceInfoStatus = EnumDeviceStatus.getEnum(deviceStatus);
			parentIdle = EnumDeviceStatus.Down.equals(eDeviceInfoStatus) || EnumDeviceStatus.Idle.equals(eDeviceInfoStatus)
					|| EnumDeviceStatus.Stopped.equals(eDeviceInfoStatus);
		}

		final JDFModuleStatus ms = (JDFModuleStatus) jdf;
		final String moduleStatus = ms.getNonEmpty(AttributeName.DEVICESTATUS);
		final EnumDeviceStatus eModuleStatus = EnumDeviceStatus.getEnum(moduleStatus);
		final boolean bModuleIdle = EnumDeviceStatus.Down.equals(eModuleStatus) || EnumDeviceStatus.Idle.equals(eModuleStatus)
				|| EnumDeviceStatus.Stopped.equals(eModuleStatus);

		final boolean needCopy = moduleStatus == null || bModuleIdle == parentIdle;
		if (needCopy)
		{
			final String id = StringUtil.getNonEmpty(ms.getModuleID());
			if (id != null && xjdf != null)
			{
				xjdf.appendAttribute(XJDFConstants.ModuleIDs, id, null, null, true);
			}
			else
			{
				final JDFIntegerRangeList index = ms.getModuleIndex();
				if (index != null && xjdf != null)
				{
					final JDFIntegerList il = index.getIntegerList();
					il.unify();
					final int size = il.size();
					for (int i = 0; i < size; i++)
					{
						xjdf.appendAttribute(XJDFConstants.ModuleIDs, JDFConstants.EMPTYSTRING + il.getInt(i), null, null, true);
					}
				}
			}
		}
		return null;
	}

	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		final EnumVersion v = jdfToXJDF.getNewVersion();
		if (EnumUtil.aLessThanB(v, EnumVersion.Version_2_3))
		{
			return walkOld(jdf, xjdf);
		}
		return super.walk(jdf, xjdf);
	}
}