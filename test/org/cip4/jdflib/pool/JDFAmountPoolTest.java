/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFComponent;

/**
 * @author RP
 * 
 *         This implements the first fixture with unit tests for class
 *         JDFAmountPool.
 */
public class JDFAmountPoolTest extends JDFTestCaseBase
{

	JDFAmountPool ap;

	// /////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////
	/**
	 * Method testVirtualAmounts.
	 * 
	 * @throws Exception
	 */
	public void testReducePartAmounts() throws Exception
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		JDFComponent comp = (JDFComponent) n.addResource("Component", null,
				EnumUsage.Output, null, null, null, null);
		JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SignatureName,
				"Sig1");
		JDFResourceLink rl = n.getLink(comp, null);
		for (int i = 0; i < 5; i++)
		{
			map.put(EnumPartIDKey.SheetName, "Sheet" + i);
			comp.getCreatePartition(map, new VString("SignatureName SheetName",
					" "));
			rl.setAmount(500 + i, map);
			JDFAttributeMap map2 = new JDFAttributeMap(map);
			map2.put("Condition", "Good");
			rl.setActualAmount(500 + i, map2);
			map2.put("Condition", "Waste");
			rl.setActualAmount(50 + i, map2);
		}
		VJDFAttributeMap v = new VJDFAttributeMap();
		JDFAttributeMap testMap = new JDFAttributeMap(EnumPartIDKey.Condition,
				"Good");
		v.add(testMap);
		JDFAmountPool ap = rl.getAmountPool();
		assertEquals("15 pa entries", ap.numChildElements(
				ElementName.PARTAMOUNT, null), 15);
		ap.reducePartAmounts(v);
		assertEquals("5 pa entries", ap.numChildElements(
				ElementName.PARTAMOUNT, null), 5);
		testMap.put("SheetName", "Sheet3");
		ap.reducePartAmounts(v);
		assertEquals("1 pa entries", ap.numChildElements(
				ElementName.PARTAMOUNT, null), 1);
	} // /////////////////////////////////////////////////////

	/**
	 * Method testVirtualAmounts.
	 * 
	 * @throws Exception
	 */
	public void testVirtualAmounts() throws Exception
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		JDFComponent comp = (JDFComponent) n.addResource("Component", null,
				EnumUsage.Output, null, null, null, null);
		JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SignatureName,
				"Sig1");
		JDFResourceLink rl = n.getLink(comp, null);
		for (int i = 0; i < 5; i++)
		{
			map.put(EnumPartIDKey.SheetName, "Sheet" + i);
			comp.getCreatePartition(map, new VString("SignatureName SheetName",
					" "));
			rl.setAmount(500 + i, map);
			JDFAttributeMap map2 = new JDFAttributeMap(map);
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

	// /////////////////////////////////////////////////////

	/**
	 * Method testGetMatchingPartAmountVector.
	 * 
	 * @throws Exception
	 */
	public void testGetMatchingPartAmountVector() throws Exception
	{
		JDFDoc d = JDFTestCaseBase.creatXMDoc();
		JDFNode n = d.getJDFRoot();
		JDFResourceLink xmLink = n.getLink(0, ElementName.EXPOSEDMEDIA, null,
				null);
		JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SheetName", "S1");
		mPart.put("Side", "Front");
		mPart.put("Separation", "Black");
		mPart.put("Condition", "Good");
		xmLink.setAmount(2, mPart);
		mPart.put("Condition", "Waste");
		xmLink.setAmount(1, mPart);

		JDFAmountPool ap = xmLink.getAmountPool();
		assertNotNull(ap);
		mPart.remove("Condition");

		VElement v = ap.getMatchingPartAmountVector(mPart);
		assertEquals(v.size(), 2);
		mPart.put("Side", "Moebius");
		v = ap.getMatchingPartAmountVector(mPart);
		assertNull("there certainly is no moebius side ...", v);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		ap = (JDFAmountPool) new JDFDoc(ElementName.AMOUNTPOOL).getRoot();
	}

	// /////////////////////////////////////////////////////
	/**
	 * Method testGetMatchingPartAmountVector.
	 * 
	 * @throws Exception
	 */
	public void testGetPartAmountMulti() throws Exception
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		JDFComponent comp = (JDFComponent) n.addResource("Component", null,
				EnumUsage.Output, null, null, null, null);
		JDFAttributeMap map = new JDFAttributeMap(EnumPartIDKey.SignatureName,
				"Sig1");
		JDFAttributeMap mapSig = new JDFAttributeMap(map);
		JDFAttributeMap map2 = new JDFAttributeMap(EnumPartIDKey.SignatureName,
				"Sig1");
		JDFResourceLink rl = n.getLink(comp, null);
		map.put(EnumPartIDKey.SheetName, "Sheet");
		comp.getCreatePartition(map,
				new VString("SignatureName SheetName", " "));
		map.put(EnumPartIDKey.Side, "Front");
		map2.put(EnumPartIDKey.Side, "Back");
		VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(map);
		vMap.add(map2);
		JDFAmountPool ap = rl.appendAmountPool();
		JDFPartAmount pa = ap.appendPartAmount(vMap);
		assertEquals(pa.numChildElements_JDFElement(ElementName.PART, null), 2);
		rl.setActualAmount(42, map);
		rl.setActualAmount(21, map2);
		assertEquals(pa.numChildElements_JDFElement(ElementName.PART, null), 2);
		assertEquals(rl.getActualAmount(map), 42., 0.);
		assertEquals(rl.getActualAmount(mapSig), 42. + 21., 0.);
		assertEquals(pa, ap.getPartAmount(vMap));
	}

	/**
	 * Method testGetMatchingPartAmountVector.
	 * 
	 * @throws Exception
	 */
	public void testGetCreatePartAmount() throws Exception
	{
		JDFAttributeMap map = new JDFAttributeMap("Separation", "Black");
		JDFAttributeMap map2 = new JDFAttributeMap("Separation", "Cyan");
		VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(map);
		vMap.add(map2);

		JDFPartAmount pa1 = ap.getCreatePartAmount(map);
		assertEquals(pa1.getPartMap(), map);
		JDFPartAmount pa3 = ap.getCreatePartAmount(vMap);
		assertEquals(pa3.getPartMapVector(), vMap);
		JDFPartAmount pa4 = ap.getCreatePartAmount(vMap);
		assertEquals(pa3, pa4);
		JDFPartAmount pa2 = ap.getCreatePartAmount(map2);
		assertEquals(pa2.getPartMap(), map2);
	}

	/**
	 * make sure no npe in empty amountpools
	 * 
	 * @throws Exception
	 */
	public void testGetPartAmountNull() throws Exception
	{
		JDFAttributeMap map = new JDFAttributeMap("Separation", "Black");
		VJDFAttributeMap vMap = new VJDFAttributeMap();
		vMap.add(map);

		JDFResourceLink rl = (JDFResourceLink) new JDFDoc("MediaLink")
				.getRoot();
		ap = rl.appendAmountPool();
		assertNull(ap.getPartAmount(vMap));
		assertNull(ap.getPartAmount(map));
		assertNull(ap.getPartAmount(2));
		assertNull(ap.getPartAmount(0));

		JDFPartAmount pa = ap.appendPartAmount();
		assertNull(ap.getPartAmount(vMap));
		assertNull(ap.getPartAmount(map));
		assertNull(ap.getPartAmount(2));
		assertEquals(pa, ap.getPartAmount(0));
	}
}