/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of 
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

package org.cip4.jdflib.resource.process;

import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoUsageCounter.EnumScope;
import org.cip4.jdflib.core.*;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.process.JDFUsageCounter.EnumCounterType;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 * 
 * TODO Please insert comment!
 * @author rainerprosi
 * @date Jan 10, 2012
 */
class JDFUsageCounterTest extends JDFTestCaseBase
{

	/**
	 * Test method for setCounterTypes
	 */
	@Test
	void testSetUsageCounterTypes()
	{
		JDFUsageCounter uc = (JDFUsageCounter) new JDFDoc(ElementName.USAGECOUNTER).getRoot();
		Vector<EnumCounterType> v = new Vector<JDFUsageCounter.EnumCounterType>();
		v.add(EnumCounterType.Auxiliary);
		v.add(EnumCounterType.Black);
		uc.setCounterTypes(v);
		Vector<EnumCounterType> v2 = uc.getEnumCounterTypes();
		Assertions.assertEquals(v, v2);
		Assertions.assertEquals(v.size(), 2);
	}

	/**
	 * Test method for appendCounterTypes
	 */
	@Test
	void testAppendCounterTypes()
	{
		JDFUsageCounter uc = (JDFUsageCounter) new JDFDoc(ElementName.USAGECOUNTER).getRoot();
		uc.appendCounterType(EnumCounterType.Auxiliary);
		uc.appendCounterType(EnumCounterType.Color);
		uc.appendCounterType(null);
		Vector<EnumCounterType> v = uc.getEnumCounterTypes();
		Assertions.assertEquals(v.size(), 2);
	}

	/**
	 * Test method for
	 * 'org.cip4.jdflib.resource.process.JDFMedia.setDimensionCM(JDFXYPair)'
	 */
	@Test
	public final void testUsageCounter()
	{
		KElement.setLongID(false);
		JDFDoc doc = new JDFDoc("JDF");
		JDFNode root = doc.getJDFRoot();
		root.setType(EnumType.DigitalPrinting);
		JDFUsageCounter uc = (JDFUsageCounter) root.appendMatchingResource(ElementName.USAGECOUNTER, EnumProcessUsage.AnyInput, null);
		Assertions.assertTrue(uc.isValid(EnumValidationLevel.Incomplete));
		Assertions.assertFalse(uc.isValid(EnumValidationLevel.Complete));
		uc.setCounterID("c1");
		uc.setScope(EnumScope.Job);
		uc.setCounterTypes(new VString("NormalSize Black OneSided TwoSided Impressions", " "));
		Assertions.assertTrue(uc.isValid(EnumValidationLevel.Complete));
		Assertions.assertEquals(StringUtil.setvString(uc.getEnumCounterTypes()), StringUtil.setvString(uc.getCounterTypes()));
		JDFResourceLink rl = root.getLink(uc, null);
		rl.setActualAmount(10, null);
		JDFAuditPool ap = root.getCreateAuditPool();
		JDFResourceAudit ra = (JDFResourceAudit) ap.addAudit(EnumAuditType.ResourceAudit, null);
		ra.updateLink(rl);

		JDFUsageCounter uc2 = (JDFUsageCounter) root.appendMatchingResource(ElementName.USAGECOUNTER, EnumProcessUsage.AnyInput, null);
		uc2.setCounterID("c2");
		uc2.setScope(EnumScope.Job);
		uc2.setCounterTypes(new VString("NormalSize Color OneSided TwoSided Impressions", " "));
		Assertions.assertTrue(uc2.isValid(EnumValidationLevel.Complete));
		Assertions.assertEquals(StringUtil.setvString(uc2.getEnumCounterTypes()), StringUtil.setvString(uc2.getCounterTypes()));
		rl = root.getLink(uc2, null);
		rl.setActualAmount(20, null);
		ra = (JDFResourceAudit) ap.addAudit(EnumAuditType.ResourceAudit, null);
		ra.updateLink(rl);

		JDFUsageCounter uc3 = (JDFUsageCounter) root.appendMatchingResource(ElementName.USAGECOUNTER, EnumProcessUsage.AnyInput, null);
		uc3.setCounterID("c3");
		uc3.setScope(EnumScope.Job);
		uc3.setCounterTypes(new VString("LargeSize Black OneSided TwoSided Impressions", " "));
		Assertions.assertTrue(uc3.isValid(EnumValidationLevel.Complete));
		Assertions.assertEquals(StringUtil.setvString(uc3.getEnumCounterTypes()), StringUtil.setvString(uc3.getCounterTypes()));
		rl = root.getLink(uc3, null);
		rl.setActualAmount(30, null);
		ra = (JDFResourceAudit) ap.addAudit(EnumAuditType.ResourceAudit, null);
		ra.updateLink(rl);

		JDFUsageCounter uc4 = (JDFUsageCounter) root.appendMatchingResource(ElementName.USAGECOUNTER, EnumProcessUsage.AnyInput, null);
		uc4.setCounterID("c4");
		uc4.setScope(EnumScope.Job);
		uc4.setCounterTypes(new VString("LargeSize Color OneSided TwoSided Impressions", " "));
		Assertions.assertTrue(uc4.isValid(EnumValidationLevel.Complete));
		Assertions.assertEquals(StringUtil.setvString(uc4.getEnumCounterTypes()), StringUtil.setvString(uc4.getCounterTypes()));
		rl = root.getLink(uc4, null);
		rl.setActualAmount(40, null);
		ra = (JDFResourceAudit) ap.addAudit(EnumAuditType.ResourceAudit, null);
		ra.updateLink(rl);
		Assertions.assertTrue(root.isValid(EnumValidationLevel.Incomplete));

		doc.write2File(sm_dirTestDataTemp + "UCList.jdf", 2, false);
	}

	/**
	 * 	
	 */
	@Test
	public final void testMatchesString()
	{
		JDFUsageCounter c = (JDFUsageCounter) new JDFDoc(ElementName.USAGECOUNTER).getRoot();
		Assertions.assertTrue(c.matchesString(ElementName.USAGECOUNTER));
		Assertions.assertFalse(c.matchesString(ElementName.USAGECOUNTER + ":"));
		c.setCounterTypes(new VString("Black SingleSided", null));
		Assertions.assertFalse(c.matchesString(ElementName.USAGECOUNTER + ":Black"));
		Assertions.assertTrue(c.matchesString(ElementName.USAGECOUNTER + ":Black_SingleSided"));
		Assertions.assertTrue(c.matchesString(ElementName.USAGECOUNTER));
		c.setCounterID("CID");
		Assertions.assertTrue(c.matchesString(ElementName.USAGECOUNTER + ":CID"));
		Assertions.assertFalse(c.matchesString(ElementName.USAGECOUNTER + ":CID2"));
		Assertions.assertFalse(c.matchesString(ElementName.USAGECOUNTER + ":CI"));
		Assertions.assertFalse(c.matchesString(ElementName.USAGECOUNTER + ":cid"));
	}
}
