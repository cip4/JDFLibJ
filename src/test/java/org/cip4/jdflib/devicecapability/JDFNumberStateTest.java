/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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
/**
 * JDFStateBaseTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.devicecapability;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.devicecapability.JDFNumberState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class JDFNumberStateTest extends JDFTestCaseBase
{

	JDFNumberState iState = null;

	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		JDFDoc doc = new JDFDoc("NumberState");
		iState = (JDFNumberState) doc.getRoot();

	}

	// //////////////////////////////////////////////////

	@Test
	public final void testFitsValue()
	{
		JDFParser p = new JDFParser();
		String strNode = "<NumberState Name=\"BitDepth\" DefaultValue=\"1\" AllowedValueList=\"1 8.5 12\"/>";

		JDFDoc jdfDoc = p.parseString(strNode);
		JDFNumberState state = (JDFNumberState) jdfDoc.getRoot();

		JDFNumberRangeList list = new JDFNumberRangeList();
		list.append(new JDFNumberRange(1, 12.5)); // 1~12
		// list.append(12);

		state.setListType(EnumListType.RangeList);
		Assertions.assertFalse(state.fitsValue(list.toString(),
				EnumFitsValue.Allowed), "ListType=RangeList");

		JDFNumberRangeList list2 = new JDFNumberRangeList();
		list2.append(new JDFNumberRange(1, 12.5)); // 1~-2

		JDFNumberRangeList allowedVL = new JDFNumberRangeList();
		allowedVL.append(new JDFNumberRange(1, 32.666)); // 1~32

		state.setAllowedValueList(allowedVL); // new AllowedVlaueList

		Assertions.assertTrue(state.fitsValue(list2.toString(),
				EnumFitsValue.Allowed));

		list.erase(list.size() - 1); // erase "1~12"
		list.append(2);
		list.append(12);
		list.append(22);
		state.setListType(EnumListType.List);
		state.setAllowedValueMod(new JDFXYPair(10, 2));
		Assertions.assertTrue(state.fitsValue(list.toString(),
                EnumFitsValue.Allowed), "ListType=List, ValueMod=" + state.getAllowedValueMod());
	}

	// //////////////////////////////////////////////////////////

	@Test
	public final void testSetCurrentValue() throws Exception
	{
		final JDFNumberList integerList = new JDFNumberList("1 2 3");
		iState.setCurrentValue(integerList);
		Assertions.assertEquals(iState.getCurrentValue(), integerList);
		iState.setCurrentValue(1);
		Assertions.assertEquals(iState.getCurrentValue(), new JDFNumberList("1"));

	}

	// //////////////////////////////////////////////////////////
	@Test
	public final void testSetDefaultValue() throws Exception
	{
		final JDFNumberList integerList = new JDFNumberList("1 2 3");
		iState.setDefaultValue(integerList);
		Assertions.assertEquals(iState.getDefaultValue(), integerList);
		iState.setDefaultValue(1);
		Assertions.assertEquals(iState.getDefaultValue(), new JDFNumberList("1"));

	}

	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	@Test
	public final void testAddValue() throws Exception
	{
		final JDFNumberRangeList integerList = new JDFNumberRangeList(
				"1 2 3 4 ~ 44");
		iState.setAllowedValueList(integerList);
		iState.addValue("24", EnumFitsValue.Allowed);
		Assertions.assertEquals(iState.getAllowedValueList(), integerList);
		iState.addValue("45", EnumFitsValue.Allowed);
		Assertions.assertEquals(iState.getAllowedValueList(), new JDFNumberRangeList(
				"1 2 3 4 ~ 44 45"));
		iState.addValue("48", EnumFitsValue.Present);
		Assertions.assertEquals(iState.getPresentValueList(), new JDFNumberRangeList("48"));
	}

	// //////////////////////////////////////////////////////////
	@Test
	public final void testSetAllowedValueList() throws Exception
	{
		final JDFNumberRangeList integerList = new JDFNumberRangeList(
				"1 2 3 4 ~ 44");
		iState.setAllowedValueList(integerList);
		Assertions.assertEquals(iState.getPresentValueList(), integerList);
		Assertions.assertEquals(iState.getAllowedValueList(), integerList);
		final JDFNumberRangeList integerList2 = new JDFNumberRangeList(
				"1 2 3 7~77");
		iState.setPresentValueList(integerList2);
		Assertions.assertEquals(iState.getPresentValueList(), integerList2);
		Assertions.assertEquals(iState.getAllowedValueList(), integerList);
	}

	// //////////////////////////////////////////////////////////

	@Test
	public final void testIsValid() throws Exception
	{
		final JDFNumberList numberList = new JDFNumberList("1 2 3");
		iState.setDefaultValue(numberList);
		iState.setCurrentValue(numberList);
		Assertions.assertFalse(iState.isValid(EnumValidationLevel.Complete));
		iState.setListType(EnumListType.List);
		Assertions.assertTrue(iState.isValid(EnumValidationLevel.Complete));
		final JDFNumberRangeList numberRList = new JDFNumberRangeList(
				"1 2 3 4 ~ 44");
		iState.setAllowedValueList(numberRList);
		Assertions.assertTrue(iState.isValid(EnumValidationLevel.Complete));
		final JDFNumberRangeList numberList2 = new JDFNumberRangeList(
				"1 2 3 7~77");
		iState.setPresentValueList(numberList2);
		Assertions.assertTrue(iState.isValid(EnumValidationLevel.Complete));
	}
	// //////////////////////////////////////////////////////////

}
