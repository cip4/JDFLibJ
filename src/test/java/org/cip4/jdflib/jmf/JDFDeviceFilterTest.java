/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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

package org.cip4.jdflib.jmf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceFilter.EnumDeviceDetails;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFDeviceList;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi
 *
 * Test of the Status JMF
 */
class JDFDeviceFilterTest extends JDFTestCaseBase
{
	private JDFDeviceList dl;

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp()
	{
		KElement.setLongID(false);
		final JDFDoc doc = new JDFDoc(ElementName.DEVICELIST);
		dl = (JDFDeviceList) doc.getRoot();
		for (int i = 0; i < 10; i++)
		{
			final JDFDeviceInfo di = dl.appendDeviceInfo();
			di.setDeviceID("I" + i);
			JDFDevice dev = di.appendDevice();
			dev.appendDeviceCap();
			di.appendModuleStatus();
			di.setDeviceStatus(EnumDeviceStatus.Running);
			di.setStatusDetails("foo");

		}

	}

	/**
	 *
	 */
	@Test
	void testApplyToNone()
	{
		final JDFDeviceFilter filter = (JDFDeviceFilter) new JDFDoc("DeviceFilter").getRoot();
		filter.setDeviceDetails(EnumDeviceDetails.None);
		filter.applyTo(dl);
		for (int i = 0; i < 10; i++)
		{
			final JDFDeviceInfo deviceInfo = dl.getDeviceInfo(i);
			Assertions.assertNull(deviceInfo.getDevice());
			Assertions.assertNull(StringUtil.getNonEmpty(deviceInfo.getStatusDetails()));
			Assertions.assertNotNull(deviceInfo.getDeviceStatus());
			Assertions.assertNotNull(deviceInfo.getDeviceID());
		}
	}

	/**
	 *
	 */
	@Test
	void testApplyToBrief()
	{
		final JDFDeviceFilter filter = (JDFDeviceFilter) new JDFDoc("DeviceFilter").getRoot();
		filter.setDeviceDetails(EnumDeviceDetails.Brief);
		filter.applyTo(dl);
		for (int i = 0; i < 10; i++)
		{
			final JDFDeviceInfo deviceInfo = dl.getDeviceInfo(i);
			Assertions.assertNull(deviceInfo.getDevice());
			Assertions.assertNotNull(StringUtil.getNonEmpty(deviceInfo.getStatusDetails()));
			Assertions.assertNotNull(deviceInfo.getDeviceStatus());
			Assertions.assertNotNull(deviceInfo.getDeviceID());
		}
	}

	/**
	 *
	 */
	@Test
	void testApplyToClass()
	{
		//ToDo upgrade Filter
	}

	/**
	 *
	 */
	@Test
	void testApplyToDetails()
	{
		final JDFDeviceFilter filter = (JDFDeviceFilter) new JDFDoc("DeviceFilter").getRoot();
		filter.setDeviceDetails(EnumDeviceDetails.Details);
		filter.applyTo(dl);
		for (int i = 0; i < 10; i++)
		{
			final JDFDeviceInfo deviceInfo = dl.getDeviceInfo(i);
			Assertions.assertNotNull(deviceInfo.getDevice());
			Assertions.assertNull(deviceInfo.getDevice().getDeviceCap(0));
			Assertions.assertNotNull(StringUtil.getNonEmpty(deviceInfo.getStatusDetails()));
			Assertions.assertNotNull(deviceInfo.getDeviceStatus());
			Assertions.assertNotNull(deviceInfo.getDeviceID());
		}
	}

	/**
	 *
	 */
	@Test
	void testApplyToCaps()
	{
		final JDFDeviceFilter filter = (JDFDeviceFilter) new JDFDoc("DeviceFilter").getRoot();
		filter.setDeviceDetails(EnumDeviceDetails.Capability);
		filter.applyTo(dl);
		for (int i = 0; i < 10; i++)
		{
			final JDFDeviceInfo deviceInfo = dl.getDeviceInfo(i);
			Assertions.assertNotNull(deviceInfo.getDevice());
			Assertions.assertNotNull(deviceInfo.getDevice().getDeviceCap(0));
			Assertions.assertNotNull(StringUtil.getNonEmpty(deviceInfo.getStatusDetails()));
			Assertions.assertNotNull(deviceInfo.getDeviceStatus());
			Assertions.assertNotNull(deviceInfo.getDeviceID());
		}
	}
}
