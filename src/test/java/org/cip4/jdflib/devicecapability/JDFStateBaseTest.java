/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of
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

import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumOrientation;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.devicecapability.JDFBooleanState;
import org.cip4.jdflib.resource.devicecapability.JDFDevCap;
import org.cip4.jdflib.resource.devicecapability.JDFDevCaps;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap.EnumAvailability;
import org.cip4.jdflib.resource.devicecapability.JDFIntegerState;
import org.cip4.jdflib.resource.devicecapability.JDFMatrixState;
import org.cip4.jdflib.resource.devicecapability.JDFStringState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class JDFStateBaseTest extends JDFTestCaseBase
{

	private JDFDeviceCap deviceCap;

	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		JDFDoc doc = new JDFDoc("JMF");
		JDFJMF jmf = doc.getJMFRoot();
		JDFResponse resp = (JDFResponse) jmf.appendMessageElement(
				EnumFamily.Response, JDFMessage.EnumType.KnownDevices);
		deviceCap = resp.appendDeviceList().appendDeviceInfo().appendDevice()
				.appendDeviceCap();
		deviceCap.appendBooleanState("Template");

	}

	// //////////////////////////////////////////////////

	@Test
	public final void testGetNamePath()
	{
		JDFParser p = new JDFParser();
		String strNode = "<DevCaps xmlns=\"http://www.CIP4.org/JDFSchema_1_1\" Name=\"RenderingParams\" LinkUsage=\"Input\">"
				+ "<DevCap>"
				+ "<DevCap Name=\"AutomatedOverprintParams\">"
				+ "<BooleanState Name=\"OverPrintBlackText\" DefaultValue=\"true\" AllowedValueList=\"true false\"/>"
				+ "<BooleanState Name=\"OverPrintBlackLineArt\" DefaultValue=\"true\" AllowedValueList=\"true false\"/>"
				+ "</DevCap>" + "</DevCap>" + "</DevCaps>";

		JDFDoc jdfDoc = p.parseString(strNode);

		JDFDevCaps devCaps = (JDFDevCaps) jdfDoc.getRoot();

		JDFBooleanState state = (JDFBooleanState) devCaps.getChildByTagName(
				ElementName.BOOLEANSTATE, null, 0, null, false, true);
		System.out.println(state.getNamePath());
		Assertions.assertEquals(state.getNamePath(), "JDF/ResourcePool/RenderingParams/AutomatedOverprintParams/@OverPrintBlackText", "");
	}

	@Test
	public final void testFitsListType_IntegerState()
	{
		JDFIntegerRangeList list = new JDFIntegerRangeList();
		list.append(1);
		list.append(8);
		list.append(12);

		// System.out.println(state.fitsCompleteList(value, list));
		// System.out.println(state.fitsCompleteOrderedList(value, list));
		// System.out.println(state.fitsContainedList(value, list));

		// state.setListType(EnumListType.List);
		// System.out.println(state.fitsListType(list.toString()));
		// System.out.println(state.getListType());

	}

	@Test
	public final void testFitsValue_StringState()
	{
		JDFDoc jdfDoc = new JDFDoc(ElementName.STRINGSTATE);
		JDFStringState root = (JDFStringState) jdfDoc.getRoot();
		root.appendValueAllowedValue("foo");
		Assertions.assertTrue(root.fitsValue("foo", EnumFitsValue.Allowed));
		Assertions.assertFalse(root.fitsValue("bar", EnumFitsValue.Allowed));

	}

	@Test
	public final void testFitsValue_MatrixState() throws Exception
	{
		JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		JDFNode root = jdfDoc.getJDFRoot();

		JDFMatrix matrix1 = new JDFMatrix("1 0 0 1 3.14 21631.3");
		JDFMatrix matrix2 = new JDFMatrix("0 1 1 0 2 21000");

		Vector<EnumOrientation> transforms = new Vector<EnumOrientation>();
		transforms.add(EnumOrientation.Rotate0);
		transforms.add(EnumOrientation.Rotate270);
		transforms.add(EnumOrientation.Flip0);
		JDFRectangle shift = new JDFRectangle("2 4 20000 23000");

		String value1 = "1 0 0 1 3.14 21631.3";

		JDFMatrixState k = (JDFMatrixState) root.appendElement("MatrixState");
		k.appendValue();
		// k.setValueValueUsage(0, EnumFitsValue.Allowed);
		k.setValueAllowedValue(0, matrix2);

		k.appendValue();
		// k.setValueValueUsage(1, EnumFitsValue.Present);
		k.setValueAllowedValue(1, matrix1);

		k.setAllowedTransforms(transforms);
		k.setAllowedShift(shift);
		k.setAllowedRotateMod(15);

		EnumListType lt = EnumListType.UniqueList;
		// JDFAbstractState.EnumListType lt = EnumListType.ListType.Unknown;
		// JDFAbstractState.EnumListType lt = EnumListType.ListType.List;
		k.setListType(lt);
		// EnumListType listType = k.getListType();

		Assertions.assertTrue(k.fitsValue(value1, EnumFitsValue.Allowed), "Matrix OK");

		String value = "1 2 3 4 5 6 7 8 9 10 11 12 3 4 5 6 7 8";

		VString vs = new VString(value, JDFConstants.BLANK);
		int siz = vs.size();
		Assertions.assertEquals(siz % 6, 0, "It is not a Matrix");
		VString matrixList = new VString();
		int i = 0;
		StringBuffer sb = new StringBuffer(250);
		sb.append(vs.elementAt(i));
		while ((i + 1) < siz)
		{
			do
			{
				sb.append(JDFConstants.BLANK);
				i++;
				sb.append(vs.elementAt(i));
			} while ((i + 1) % 6 != 0);
			matrixList.add(sb.toString());
			if ((i + 1) < siz)
			{
				i++;
				sb = new StringBuffer(250);
				sb.append(vs.elementAt(i));
			}
		}
		for (int z = 0; z < matrixList.size(); z++)
		{
			JDFMatrix matrix3 = new JDFMatrix(matrixList.get(z));
			matrix3.getA();
		}

	}

	// //////////////////////////////////////////////////////////

	/**
	 * tests defaults and handling of "unbounded"
	 */
	@Test
	public final void testFixVersion()
	{
		JDFDevCap dc = deviceCap.appendDevCaps().appendDevCap();
		JDFIntegerState is = dc.appendIntegerState();
		Assertions.assertNull(is.getAttribute(AttributeName.MAXOCCURS, null, null));
		is.fixVersion(null);
		Assertions.assertNull(is.getAttribute(AttributeName.MAXOCCURS, null, null));
		is.setAttribute(AttributeName.MAXOCCURS, "unbounded");
		is.fixVersion(null);
		Assertions.assertEquals(is.getAttribute(AttributeName.MAXOCCURS), JDFConstants.POSINF);
		is.setAttribute(AttributeName.MAXOCCURS, "3");
		is.fixVersion(null);
		Assertions.assertEquals(is.getAttribute(AttributeName.MAXOCCURS), "3");
	}

	// //////////////////////////////////////////////////////////

	/**
	 * tests defaults and handling of "unbounded"
	 */
	@Test
	public final void testMaxOccurs()
	{
		JDFDevCap dc = deviceCap.appendDevCaps().appendDevCap();
		JDFIntegerState is = dc.appendIntegerState();
		Assertions.assertEquals(is.getMaxOccurs(), 1);
		is.setMaxOccurs(Integer.MAX_VALUE);
		Assertions.assertEquals(is.getAttribute(AttributeName.MAXOCCURS, null, null), JDFConstants.POSINF);
		Assertions.assertTrue(is.getMaxOccurs() > 999);
		is.setAttribute(AttributeName.MAXOCCURS, "unbounded");
		Assertions.assertTrue(is
				.getMaxOccurs() > 999, "correctly parsed unbounded for legacy support");
	}

	// //////////////////////////////////////////////////////////

	@Test
	void testGetNamePathVector()
	{

		JDFBooleanState b = deviceCap.getBooleanState("Template");
		VString v = b.getNamePathVector(true);
		Assertions.assertEquals(v.size(), 1);
		Assertions.assertEquals(v.get(0), "JDF/@Template");
	}

	/**
	 * tests defaults
	 */
	@Test
	public final void testMinOccurs()
	{
		JDFDevCap dc = deviceCap.appendDevCaps().appendDevCap();
		JDFIntegerState is = dc.appendIntegerState();
		Assertions.assertEquals(is.getMinOccurs(), 1, "default=1");
	}

	/**
	 * tests defaults for availability
	 */
	@Test
	public final void testGetAvailability()
	{
		JDFDevCap dc = deviceCap.appendDevCaps().appendDevCap();
		JDFIntegerState is = dc.appendIntegerState();

		Assertions.assertEquals(EnumAvailability.Installed, dc.getAvailability());
		Assertions.assertEquals(EnumAvailability.Installed, is.getAvailability());

		dc.setAvailability(EnumAvailability.NotLicensed);
		Assertions.assertEquals(EnumAvailability.NotLicensed, dc.getAvailability());
		Assertions.assertEquals(EnumAvailability.NotLicensed, is.getAvailability());

	}

	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////

}
