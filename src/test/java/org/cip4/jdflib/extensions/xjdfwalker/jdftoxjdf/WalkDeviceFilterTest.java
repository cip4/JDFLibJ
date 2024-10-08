/**
 * The CIP4 Software License, Version 1.0
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

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceFilter.EnumDeviceDetails;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.jmf.JDFDeviceFilter;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
class WalkDeviceFilterTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	void testDeviceDetails()
	{
		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildKnownDevicesQuery(null);
		JDFDeviceFilter f = jmf.getQuery(0).getDeviceFilter(0);
		f.setDeviceDetails(EnumDeviceDetails.NamedFeature);
		KElement e = new JDFToXJDF().convert(jmf);
		JDFDeviceFilter fConverted = (JDFDeviceFilter) e.getElement("QueryKnownDevices").getElement(ElementName.DEVICEFILTER);
		Assertions.assertEquals(fConverted.getDeviceDetails(), EnumDeviceDetails.Details);
		f.setDeviceDetails(EnumDeviceDetails.Capability);
		e = new JDFToXJDF().convert(jmf);
		fConverted = (JDFDeviceFilter) e.getElement("QueryKnownDevices").getElement(ElementName.DEVICEFILTER);
		Assertions.assertEquals(fConverted.getDeviceDetails(), EnumDeviceDetails.Full);
	}

	/**
	 *
	 */
	@Test
	void testLocalization()
	{
		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildKnownDevicesQuery(null);
		KElement e = new JDFToXJDF().convert(jmf);
		Assertions.assertNotNull(e, "no exception");
		JDFDeviceFilter f = jmf.getQuery(0).getDeviceFilter(0);
		f.setLocalization("DE");
		f.setDeviceDetails(EnumDeviceDetails.Details);
		e = new JDFToXJDF().convert(jmf);
		JDFDeviceFilter fConverted = (JDFDeviceFilter) e.getElement("QueryKnownDevices").getElement(ElementName.DEVICEFILTER);
		Assertions.assertEquals("", fConverted.getLocalization());
	}

	/**
	 *
	 */
	@Test
	void testDevice()
	{
		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildKnownDevicesQuery(null);
		JDFDeviceFilter f = jmf.getQuery(0).getDeviceFilter(0);
		f.appendDevice().setDeviceID("ID1");
		f.setDeviceDetails(EnumDeviceDetails.Details);
		JDFToXJDF jdfToXJDF = new JDFToXJDF();
		KElement e = jdfToXJDF.convert(jmf);
		JDFDeviceFilter fConverted = (JDFDeviceFilter) e.getElement("QueryKnownDevices").getElement(ElementName.DEVICEFILTER);
		Assertions.assertNull(fConverted.getDevice(0), "");
	}

	/**
	 *
	 */
	@Test
	void testDeviceFilter()
	{
		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildKnownDevicesQuery(null);
		JDFDeviceFilter f = jmf.getQuery(0).getDeviceFilter(0);
		f.appendDevice().setDeviceID("ID1");
		JDFToXJDF jdfToXJDF = new JDFToXJDF();
		KElement e = jdfToXJDF.convert(jmf);
		JDFDeviceFilter fConverted = (JDFDeviceFilter) e.getElement("QueryKnownDevices").getElement(ElementName.DEVICEFILTER);
		Assertions.assertNull(fConverted, "");
	}

}
