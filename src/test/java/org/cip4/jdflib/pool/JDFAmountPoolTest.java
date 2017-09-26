/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib.pool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool.AmountMap;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.junit.Test;

/**
 * @author RP
 *
 * This implements the first fixture with unit tests for class JDFAmountPool.
 */
public class JDFAmountPoolTest extends JDFTestCaseBase
{

	/**
	 * @see junit.framework.TestCase#toString()
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return ap == null ? " empty amountpooltest " : ap.toString();
	}

	JDFAmountPool ap;

	// /////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////
	/**
	 * Method testReducePartAmounts.
	 *
	 *
	 */
	@Test
	public void testReducePartAmounts()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFComponent comp = (JDFComponent) n.addResource("Component", null, EnumUsage.Output, null, null, null, null);
		final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SignatureName, "Sig1");
		final JDFResourceLink rl = n.getLink(comp, null);
		for (int i = 0; i < 5; i++)
		{
			map.put(EnumPartIDKey.SheetName, "Sheet" + i);
			comp.getCreatePartition(map, new VString("SignatureName SheetName", " "));
			rl.setAmount(500 + i, map);
			final JDFAttributeMap map2 = new JDFAttributeMap(map);
			map2.put("Condition", "Good");
			rl.setActualAmount(500 + i, map2);
			map2.put("Condition", "Waste");
			rl.setActualAmount(50 + i, map2);
		}
		final VJDFAttributeMap v = new VJDFAttributeMap();
		final JDFAttributeMap testMap = new JDFAttributeMap(EnumPartIDKey.Condition, "Good");
		v.add(testMap);
		final JDFAmountPool aplocal = rl.getAmountPool();
		assertEquals("15 pa entries", aplocal.numChildElements(ElementName.PARTAMOUNT, null), 15);
		aplocal.reducePartAmounts(v);
		assertEquals("5 pa entries", aplocal.numChildElements(ElementName.PARTAMOUNT, null), 5);
		testMap.put("SheetName", "Sheet3");
		aplocal.reducePartAmounts(v);
		assertEquals("1 pa entries", aplocal.numChildElements(ElementName.PARTAMOUNT, null), 1);
	} // /////////////////////////////////////////////////////

	/**
	 * Method testVirtualAmounts.
	 *
	 */
	@Test
	public void testVirtualAmounts()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFComponent comp = (JDFComponent) n.addResource("Component", null, EnumUsage.Output, null, null, null, null);
		final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SignatureName, "Sig1");
		final JDFResourceLink rl = n.getLink(comp, null);
		for (int i = 0; i < 5; i++)
		{
			map.put(EnumPartIDKey.SheetName, "Sheet" + i);
			comp.getCreatePartition(map, new VString("SignatureName SheetName", " "));
			rl.setAmount(500 + i, map);
			final JDFAttributeMap map2 = new JDFAttributeMap(map);
			map2.put("Condition", "Good");
			rl.setActualAmount(500 + i, map2);
			map2.put("Condition", "Waste");
			rl.setActualAmount(50 + i, map2);

			map2.put("Condition", "Good");
			assertEquals(rl.getActualAmount(map2), 500 + i, 0.01);
			map2.put("Condition", "Waste");
			assertEquals(rl.getActualAmount(map2), 50 + i, 0.01);
		}
	}

	/**
	 * Method .
	 *
	 */
	@Test
	public void testSetPartAmount()
	{
		ap.setPartAttribute("Foo", "Bar", null, (JDFAttributeMap) null);
		assertEquals(ap.getPartAttribute("Foo", null, (JDFAttributeMap) null), "Bar");
		ap = (JDFAmountPool) new JDFDoc(ElementName.AMOUNTPOOL).getRoot();
		ap.setPartAttribute("Foo", "Bar", null, new JDFAttributeMap());
		assertEquals(ap.getPartAttribute("Foo", null, (JDFAttributeMap) null), "Bar");
		ap = (JDFAmountPool) new JDFDoc(ElementName.AMOUNTPOOL).getRoot();
		ap.setPartAttribute("Foo", "Bar", null, new JDFAttributeMap());
		assertEquals(ap.getPartAttribute("Foo", null, new JDFAttributeMap()), "Bar");
		assertEquals(ap.getPartAttribute("Foo", null, new VJDFAttributeMap(new JDFAttributeMap())), "Bar");
		ap = (JDFAmountPool) new JDFDoc(ElementName.AMOUNTPOOL).getRoot();
		ap.setPartAttribute("Foo", "Bar", null, new JDFAttributeMap("Run", "R1"));
		ap.setPartAttribute("Foo", "Bar2", null, new JDFAttributeMap());
		assertEquals(ap.getPartAttribute("Foo", null, new JDFAttributeMap("Run", "R1")), "Bar");
		assertEquals(ap.getPartAttribute("Foo", null, new VJDFAttributeMap(new JDFAttributeMap("Run", "R1"))), "Bar");
		assertEquals(ap.getPartAttribute("Foo", null, (JDFAttributeMap) null), "Bar2");
	}

	/**
	 * Method testVirtualAmounts.
	 *
	 */
	@Test
	public void testVirtualAmountsSplit()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFComponent comp = (JDFComponent) n.addResource("Component", EnumUsage.Output);
		final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SignatureName, "Sig1");
		final JDFResourceLink rl = n.getLink(comp, null);
		for (int i = 0; i < 5; i++)
		{
			map.put(EnumPartIDKey.SheetName, "Sheet" + i);
			comp.getCreatePartition(map, new VString("SignatureName SheetName", " "));
			rl.setAmount(500 + i, map);
			final JDFAttributeMap map2 = new JDFAttributeMap(map);
			for (int j = 0; j < 2; j++)
			{
				map2.put("Side", j == 0 ? "Front" : "Back");
				map2.put("Condition", "Good");
				rl.setActualAmount(500 + i, map2);
				map2.put("Condition", "Waste");
				rl.setActualAmount(50 + i, map2);

				map2.put("Condition", "Good");
				assertEquals(rl.getActualAmount(map2), 500 + i, 0.01);
				map2.put("Condition", "Waste");
				assertEquals(rl.getActualAmount(map2), 50 + i, 0.01);
			}
			assertEquals(rl.getAmount(map), 500.0 + i, 0.01);
		}
	}

	// /////////////////////////////////////////////////////

	/**
	 * Method testGetMatchingPartAmountVector.
	 *
	 */
	@Test
	public void testGetMatchingPartAmountVector()
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFResourceLink xmLink = n.getLink(0, ElementName.EXPOSEDMEDIA, null, null);
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		mPart.put("Separation", "Black");
		mPart.put("Condition", "Good");
		xmLink.setAmount(2, mPart);
		mPart.put("Condition", "Waste");
		xmLink.setAmount(1, mPart);

		final JDFAmountPool aplocal = xmLink.getAmountPool();
		assertNotNull(aplocal);
		mPart.remove("Condition");

		VElement v = aplocal.getMatchingPartAmountVector(mPart);
		assertEquals(v.size(), 2);
		mPart.put("Side", "Moebius");
		v = aplocal.getMatchingPartAmountVector(mPart);
		assertNull("there certainly is no moebius side ...", v);
	}

	/**
	 *
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		ap = (JDFAmountPool) new JDFDoc(ElementName.AMOUNTPOOL).getRoot();
	}

	/**
	 *
	 */
	@Test
	public void testGetPartVectorNull()
	{
		JDFAmountPool ap = (JDFAmountPool) new JDFDoc(ElementName.AMOUNTPOOL).getRoot();
		JDFPartAmount pa = ap.appendPartAmount();
		pa.setAmount(42);
		assertEquals(pa, ap.getPartAmount(new VJDFAttributeMap()));
		assertEquals(pa, ap.getPartAmount((VJDFAttributeMap) null));

		pa.setPart("Location", "l1");
		assertNull(ap.getPartAmount(new VJDFAttributeMap()));
		assertNull(ap.getPartAmount((VJDFAttributeMap) null));
	}

	/**
	 * Method testGetMatchingPartAmountVector.
	 *
	 */
	@Test
	public void testGetPartAmountMulti()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFComponent comp = (JDFComponent) n.addResource("Component", null, EnumUsage.Output, null, null, null, null);
		final JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SignatureName, "Sig1");
		final JDFAttributeMap mapSig = new JDFAttributeMap(map);
		map.put(EnumPartIDKey.SheetName, "Sheet");
		final JDFAttributeMap map2 = new JDFAttributeMap(map);
		final JDFResourceLink rl = n.getLink(comp, null);
		map.put(EnumPartIDKey.SheetName, "Sheet");
		comp.getCreatePartition(map, new VString("SignatureName SheetName", " "));
		map.put(EnumPartIDKey.Side, "Front");
		map2.put(EnumPartIDKey.Side, "Back");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(map);
		vMap.add(map2);
		final JDFAmountPool aplocal = rl.appendAmountPool();
		final JDFPartAmount pa = aplocal.appendPartAmount(vMap);
		assertEquals(pa.numChildElements_JDFElement(ElementName.PART, null), 2);
		rl.setActualAmount(42, map);
		rl.setActualAmount(21, map2);
		assertEquals(pa.numChildElements_JDFElement(ElementName.PART, null), 2);
		assertEquals(rl.getActualAmount(map), 42., 0.);
		assertEquals(rl.getActualAmount(mapSig), 21., 0.);
		assertEquals(pa, aplocal.getPartAmount(vMap));
	}

	/**
	 * Method testGetMatchingPartAmountVector.
	 *
	 */
	@Test
	public void testGetCreatePartAmount()
	{
		final JDFAttributeMap map = new JDFAttributeMap("Separation", "Black");
		final JDFAttributeMap map2 = new JDFAttributeMap("Separation", "Cyan");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(map);
		vMap.add(map2);

		final JDFPartAmount pa1 = ap.getCreatePartAmount(map);
		assertEquals(pa1.getPartMap(), map);
		final JDFPartAmount pa3 = ap.getCreatePartAmount(vMap);
		assertEquals(pa3.getPartMapVector(), vMap);
		final JDFPartAmount pa4 = ap.getCreatePartAmount(vMap);
		assertEquals(pa3, pa4);
		final JDFPartAmount pa2 = ap.getCreatePartAmount(map2);
		assertEquals(pa2.getPartMap(), map2);
	}

	/**
	 * Method testGetMatchingPartAmountVector.
	 *
	 */
	@Test
	public void testGetAmountMap()
	{
		final JDFAttributeMap map = new JDFAttributeMap("Separation", "Black");
		final JDFAttributeMap map2 = new JDFAttributeMap("Separation", "Cyan");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(new JDFAttributeMap(map));
		vMap.add(new JDFAttributeMap(map2));
		for (int i = 0; i < 10; i++)
		{
			vMap.put("SignatureName", "Sig" + i);
			for (int j = 0; j < 100; j++)
			{
				vMap.put("SheetName", "Sheet" + j);
				ap.appendPartAmount(vMap).setAmount(j, null);
			}
		}
		AmountMap am = ap.getAmountMap(new VString("Separation", null));
		assertEquals(am.size(), 2);
		assertEquals(am.getAmountDouble(map, AttributeName.AMOUNT), 50 * 99 * 10., 0.);

		am = ap.getAmountMap(new VString("SheetName Separation", null));
		assertEquals(am.size(), 200);
		assertEquals(am.getAmountDouble(map, AttributeName.AMOUNT), -1.0, 0.);
		map.put("SheetName", "Sheet12");
		assertEquals("10 signatures * 12", am.getAmountDouble(map, AttributeName.AMOUNT), 10 * 12.0, 0.);
	}

	/**
	 * Method testGetMatchingPartAmountVector.
	 *
	 */
	@Test
	public void testGetPartMapVector()
	{
		final JDFAttributeMap map = new JDFAttributeMap("Separation", "Black");
		final JDFAttributeMap map2 = new JDFAttributeMap("Separation", "Cyan");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(new JDFAttributeMap(map));
		vMap.add(new JDFAttributeMap(map2));
		for (int i = 0; i < 10; i++)
		{
			vMap.put("SignatureName", "Sig" + i);
			for (int j = 0; j < 100; j++)
			{
				vMap.put("SheetName", "Sheet" + j);
				ap.appendPartAmount(vMap).setAmount(j, null);
			}
		}
		VJDFAttributeMap am = ap.getPartMapVector();
		assertEquals(am.size(), 2000);
		assertEquals(3, am.getKeys().size());
	}

	/**
	 * Method testGetMatchingPartAmountVector.
	 *
	 */
	@Test
	public void testGetCorruptAmountMap()
	{
		ap.appendPartAmount();
		final AmountMap am = ap.getAmountMap(null);
		assertEquals(am.size(), 1);
	}

	/**
	 * make sure no npe in empty amountpools
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPartAmountNull() throws Exception
	{
		final JDFAttributeMap map = new JDFAttributeMap("Separation", "Black");
		final VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(map);

		final JDFResourceLink rl = (JDFResourceLink) new JDFDoc("MediaLink").getRoot();
		ap = rl.appendAmountPool();
		assertNull(ap.getPartAmount(vMap));
		assertNull(ap.getPartAmount(map));
		assertNull(ap.getPartAmount(map, 0));
		assertNull(ap.getPartAmount(2));
		assertNull(ap.getPartAmount(0));

		final JDFPartAmount pa = ap.appendPartAmount();
		assertNull(ap.getPartAmount(vMap));
		assertNull(ap.getPartAmount(map));
		assertNull(ap.getPartAmount(map, 0));
		assertNull(ap.getPartAmount(2));
		assertEquals(pa, ap.getPartAmount(0));
	}

	/**
	 * make sure no npe in empty amountpools
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetPartAmountEmpty() throws Exception
	{

		final JDFResourceLink rl = (JDFResourceLink) new JDFDoc("MediaLink").getRoot();
		ap = rl.appendAmountPool();
		JDFPartAmount pa = ap.appendPartAmount();
		assertEquals(pa, ap.getPartAmount(new JDFAttributeMap()));
		assertEquals(pa, ap.getPartAmount((JDFAttributeMap) null));
		pa.appendPart();
		assertEquals(pa, ap.getPartAmount(new JDFAttributeMap()));
		assertEquals(pa, ap.getPartAmount((JDFAttributeMap) null));
	}
}