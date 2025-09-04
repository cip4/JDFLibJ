/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2005 The International Cooperation for the Integration of
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

/*
 * MediaColorTest.java
 * @author Dietrich Mucha
 * 
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.resource;

import org.cip4.jdflib.auto.JDFAutoInsertSheet.EnumSheetType;
import org.cip4.jdflib.auto.JDFAutoInsertSheet.EnumSheetUsage;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNamedColor;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.devicecapability.JDFModule;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFInsertSheet;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFScore;
import org.cip4.jdflib.resource.process.postpress.JDFScore.EnumScoreSide;
import org.cip4.jdflib.span.JDFStringSpan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JDFAutoResourceTest {
	@Test
	void testRunList()
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode r = d.getJDFRoot();
		JDFRunList rl = (JDFRunList) r.addResource("RunList", null,
				EnumUsage.Input, null, null, null, null);
		JDFInsertSheet is1 = rl.appendInsertSheet();
		is1.setSheetType(EnumSheetType.SeparatorSheet);
		is1.setSheetUsage(EnumSheetUsage.Slip);
		JDFInsertSheet is2 = rl.appendInsertSheet();
		is2.setSheetType(EnumSheetType.SeparatorSheet);
		is2.setSheetUsage(EnumSheetUsage.Slip);
		Assertions.assertNotSame(is1, is2, "two insert sheets");

		rl.appendLayoutElement(); // 1
		Assertions.assertTrue(rl.isValid(EnumValidationLevel.Complete), "runlist valid");
		boolean b1 = false;
		try
		{
			rl.appendLayoutElement();
		} catch (JDFException e)
		{
			b1 = true;
		}
		Assertions.assertTrue(b1, "only one layoutelement possible");
	}

	@Test
	void testEnumerations()
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode r = d.getJDFRoot();
		JDFColorPool cp = (JDFColorPool) r.addResource("ColorPool", null,
				EnumUsage.Input, null, null, null, null);
		JDFColor col = cp.appendColor();
		col.setColorName(EnumNamedColor.Red);
		Assertions.assertTrue(col.getColorName() == EnumNamedColor.Red, "named color get");
		Assertions.assertTrue(col
				.getAttribute(AttributeName.COLORNAME) == "Red", "named color get raw");
	}

	@Test
	void testBinderySignature()
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		JDFResource bs = n.addResource(ElementName.BINDERYSIGNATURE, null,
				null, null, null, null, null);
		Assertions.assertEquals(bs.getResourceClass(), EnumResourceClass.Parameter, "bs class");
		bs = n.addResource(ElementName.BINDERYSIGNATURE,
				JDFResource.EnumResourceClass.Parameter, null, null, n, null,
				null);
		Assertions.assertEquals(bs.getResourceClass(), EnumResourceClass.Parameter, "bs class old style");
		Assertions.assertTrue(bs.validClass());
	}

	@Test
	void testMediaIntent()
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("Product", true);

		JDFMediaIntent mi = (JDFMediaIntent) n.appendMatchingResource(
				ElementName.MEDIAINTENT, EnumProcessUsage.AnyInput, null);
		JDFStringSpan sb = mi.appendStockBrand();
		sb.setActual("abc foo");
		sb.setPreferred("abc foo");
		Assertions.assertTrue(sb.isValid(EnumValidationLevel.Complete), "valid StockBrand");
		Assertions.assertEquals(mi.getValidClass(), EnumResourceClass.Intent);
		Assertions.assertTrue(mi.validClass());
	}

	@Test
	void testDevice()
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("Stitching", true);
		JDFDevice dev = (JDFDevice) n.appendMatchingResource(
				ElementName.DEVICE, EnumProcessUsage.AnyInput, null);
		dev.setResStatus(EnumResStatus.Available, true);
		dev.setKnownLocalizations(new VString("De", null));
		dev.setSerialNumber("12345");
		dev.setSecureJMFURL("http://fififi");
		JDFModule m = dev.appendModule();
		// m.setModuleIndex(0);
		m.setModelDescription("1234");
		JDFIconList il = dev.appendIconList();
		Assertions.assertFalse(il.isValid(EnumValidationLevel.Complete), "empty iconlist");
		Assertions.assertTrue(il.isValid(EnumValidationLevel.Incomplete), "empty iconlist");
		JDFIcon ic = il.appendIcon();
		ic.setSize(new JDFXYPair(200, 200));
		ic.setBitDepth(8);
		JDFFileSpec fs = ic.appendFileSpec();
		fs.setURL("file:///this.ico");

		Assertions.assertTrue(ic.isValid(EnumValidationLevel.Complete), "icon valid");
		Assertions.assertTrue(il.isValid(EnumValidationLevel.Complete), "iconlist valid");
		Assertions.assertTrue(m.isValid(EnumValidationLevel.Complete), "mod valid");
		Assertions.assertTrue(dev.isValid(EnumValidationLevel.Complete), "dev valid");
		Assertions.assertTrue(dev.validClass());

	}

	// test coverapplication and score
	@Test
	void testScore()
	{

		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("CoverApplication", true);
		JDFCoverApplicationParams cap = (JDFCoverApplicationParams) n
				.appendMatchingResource(ElementName.COVERAPPLICATIONPARAMS,
						EnumProcessUsage.AnyInput, null);
		JDFScore score = cap.appendScore();
		score.setSide(EnumScoreSide.FromInside);
		score.setOffset(1234.5);
		Assertions.assertTrue(score.isValid(EnumValidationLevel.Complete), "score 1");
		score = cap.appendScore();
		score.setSide(EnumScoreSide.FromOutside);
		Assertions.assertTrue(score.isValid(EnumValidationLevel.Complete), "score 2");
		Assertions.assertTrue(cap.isValid(EnumValidationLevel.Complete), "cap");
		// we know that we are incomplete since we have missing resources
		Assertions.assertTrue(n.isValid(EnumValidationLevel.Incomplete), "n");

	}

}
