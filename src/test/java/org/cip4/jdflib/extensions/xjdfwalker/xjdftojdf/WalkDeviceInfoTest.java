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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.MessageHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJMFHelper;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
class WalkDeviceInfoTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	void testDeviceStatus()
	{
		JDFDeviceInfo di = (JDFDeviceInfo) new JDFDoc(ElementName.DEVICEINFO).getRoot();
		di.setAttribute(AttributeName.STATUS, "Production");
		WalkDeviceInfo wdi = new WalkDeviceInfo();
		wdi.setParent(new XJDFToJDFImpl(null));
		KElement rp = new JDFDoc(ElementName.RESOURCEPOOL).getRoot();
		wdi.walk(di, rp);
		JDFDeviceInfo di2 = (JDFDeviceInfo) rp.getElement(ElementName.DEVICEINFO);
		String status = di2.getNonEmpty(AttributeName.DEVICESTATUS);
		Assertions.assertNull(di2.getNonEmpty(AttributeName.STATUS));
		Assertions.assertEquals(status, EnumDeviceStatus.Running.getName());
	}

	@Test
	void testGetElementNamesAndUpdateDeviceStatus()
	{
		final WalkDeviceInfo wdi = new WalkDeviceInfo();
		Assertions.assertTrue(wdi.getElementNames().contains(ElementName.DEVICEINFO));
		Assertions.assertEquals("Unknown", WalkDeviceInfo.updateDeviceStatus("Offline"));
		Assertions.assertEquals("Running", WalkDeviceInfo.updateDeviceStatus("Production"));
		Assertions.assertNull(WalkDeviceInfo.updateDeviceStatus(null));
	}

	@Test
	void testWalkModuleIDs()
	{
		final JDFDeviceInfo source = (JDFDeviceInfo) new JDFDoc(ElementName.DEVICEINFO).getRoot();
		source.setAttribute(AttributeName.STATUS, "Offline");
		source.setAttribute(XJDFConstants.ModuleIDs, "M1 M2");

		final WalkDeviceInfo walker = new WalkDeviceInfo();
		walker.setParent(new XJDFToJDFImpl(null));
		Assertions.assertTrue(walker.matches(source));

		final KElement targetPool = new JDFDoc(ElementName.RESOURCEPOOL).getRoot();
		final KElement walked = walker.walk(source, targetPool);
		Assertions.assertNotNull(walked);
		Assertions.assertEquals("Unknown", walked.getAttribute(AttributeName.DEVICESTATUS));
		Assertions.assertNull(walked.getNonEmpty(AttributeName.STATUS));
		Assertions.assertNull(walked.getNonEmpty(XJDFConstants.ModuleIDs));
		Assertions.assertNotNull(walked.getXPathElement("ModuleStatus[@ModuleID=\"M1\"]"));
		Assertions.assertNotNull(walked.getXPathElement("ModuleStatus[@ModuleID=\"M2\"]"));
	}

	@Test
	void testRoundTripX()
	{
		final XJMFHelper xjmf = new XJMFHelper();
		final MessageHelper messageHelper = xjmf.appendMessage(EnumFamily.Signal, EnumType.Status);
		messageHelper.getRoot().setXPathAttribute("DeviceInfo/@Status", "Production");
		messageHelper.getRoot().setXPathAttribute("DeviceInfo/@ModuleIDs", "Module_A Module_B");

		final JDFElement jmfRoundTrip = writeRoundTripX(xjmf.getRoot(), "walkdeviceinfo_xjdf", EnumValidationLevel.Complete);
		Assertions.assertNotNull(jmfRoundTrip);
		Assertions.assertNotNull(jmfRoundTrip.getXPathElement("Signal/DeviceInfo/ModuleStatus"));
	}
}
