/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/*
 * MediaColorTest.java
 *
 * @author Dietrich Mucha
 *
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.devicecapability;

import java.util.Vector;

import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.*;
import org.cip4.jdflib.core.JDFElement.EnumBoolean;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.resource.devicecapability.JDFBooleanState;
import org.cip4.jdflib.resource.devicecapability.JDFDateTimeState;
import org.cip4.jdflib.resource.devicecapability.JDFDevCap;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap.EnumAvailability;
import org.cip4.jdflib.resource.devicecapability.JDFDurationState;
import org.cip4.jdflib.resource.devicecapability.JDFEnumerationState;
import org.cip4.jdflib.resource.devicecapability.JDFIntegerState;
import org.cip4.jdflib.resource.devicecapability.JDFMatrixState;
import org.cip4.jdflib.resource.devicecapability.JDFModuleCap;
import org.cip4.jdflib.resource.devicecapability.JDFNameState;
import org.cip4.jdflib.resource.devicecapability.JDFNumberState;
import org.cip4.jdflib.resource.devicecapability.JDFPDFPathState;
import org.cip4.jdflib.resource.devicecapability.JDFRectangleState;
import org.cip4.jdflib.resource.devicecapability.JDFShapeState;
import org.cip4.jdflib.resource.devicecapability.JDFStringState;
import org.cip4.jdflib.resource.devicecapability.JDFXYPairState;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 *
 * @author rainer prosi
 * @date before Jun 7, 2012
 */
class JDFDevCapTest {
	/**
	 *
	 *
	 */
	@Test
	void testBooleanState()
	{
		final JDFDoc d = new JDFDoc("BooleanState");
		final JDFBooleanState bs = (JDFBooleanState) d.getRoot();
		final Vector<EnumBoolean> v = new Vector<>();
		v.add(EnumBoolean.False);
		bs.setAllowedValueList(v);
		Assertions.assertTrue(bs.fitsValue("false", EnumFitsValue.Allowed));
		Assertions.assertFalse(bs.fitsValue("fnarf", EnumFitsValue.Allowed));
		Assertions.assertFalse(bs.fitsValue("true", EnumFitsValue.Allowed));
		v.add(EnumBoolean.True);
		bs.setAllowedValueList(v);
		Assertions.assertTrue(bs.fitsValue("true", EnumFitsValue.Allowed));
	}

	/**
	 *
	 *
	 */
	@Test
	void testAppendStringState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap root = (JDFDevCap) d.getRoot();
		final JDFStringState ss1 = root.appendStringState("foo");
		Assertions.assertEquals(ss1.getName(), "foo");
		final JDFStringState ss2 = root.appendStringState(null);
		Assertions.assertFalse(ss2.hasAttribute("Name"));
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testIntegerState() throws Exception
	{
		final JDFDoc d = new JDFDoc("IntegerState");
		final JDFIntegerState is = (JDFIntegerState) d.getRoot();
		JDFIntegerRangeList irl = new JDFIntegerRangeList("12~15");
		is.setAllowedValueList(irl);
		is.setListType(EnumListType.RangeList);
		Assertions.assertTrue(is.fitsValue("12~15", EnumFitsValue.Allowed));
		Assertions.assertFalse(is.fitsValue("19~33", EnumFitsValue.Allowed));
		irl = new JDFIntegerRangeList("12~15 19~33");
		is.setAllowedValueList(irl);
		Assertions.assertTrue(is.fitsValue("12~15", EnumFitsValue.Allowed));
		Assertions.assertTrue(is.fitsValue("19~33", EnumFitsValue.Allowed));
	}

	/**
	 *
	 *
	 */
	@Test
	void testgetMatchingElementsFromParentSingle()
	{
		final JDFDoc ddc = new JDFDoc("DevCap");
		final JDFDoc dde = new JDFDoc("Layout");
		final JDFDevCap dc = (JDFDevCap) ddc.getRoot();
		final JDFLayout e = (JDFLayout) dde.getRoot();

		final JDFDevCap dc1 = dc.appendDevCap();
		dc1.setName("Media");
		dc1.setMaxOccurs(1);
		dc1.setMinOccurs(1);

		for (int i = 0; i < 2; i++)
		{
			final String mediaType = i == 0 ? "Paper" : "Plate";
			e.appendElement("Media").setAttribute("MediaType", mediaType);

		}
		final VElement devCapVector = dc.getDevCapVector(null, true);
		final VElement vMatch = ((JDFDevCap) devCapVector.item(0)).getMatchingElementsFromParent(e, devCapVector);
		Assertions.assertEquals(vMatch.size(), 2);
		Assertions.assertEquals(vMatch.item(0), e.getElement("Media", null, 0));
		Assertions.assertEquals(vMatch.item(1), e.getElement("Media", null, 1));
	}

	/**
	 *
	 *
	 */
	@Test
	void testgetMatchingElementsFromParentMulti()
	{
		final JDFDoc d = new JDFDoc(ElementName.DEVICECAP);
		final JDFDeviceCap ddc = (JDFDeviceCap) d.getRoot();
		final JDFDevCap dc = ddc.appendDevCapPool().appendDevCap();

		final JDFDoc dde = new JDFDoc("Layout");
		final JDFLayout e = (JDFLayout) dde.getRoot();

		for (int i = 0; i < 2; i++)
		{
			final JDFDevCap dc1 = dc.appendDevCap();
			dc1.setName("Media");
			dc1.setMaxOccurs(1);
			dc1.setMinOccurs(1);
			final JDFEnumerationState es = dc1.appendEnumerationState("MediaType");
			final String mediaType = i == 0 ? "Paper" : "Plate";
			es.setAllowedValueList(new VString(mediaType, null));

			e.appendElement("Media").setAttribute("MediaType", mediaType);

		}
		final VElement devCapVector = dc.getDevCapVector(null, true);
		for (int i = 0; i < 2; i++)
		{
			final VElement vMatch = ((JDFDevCap) devCapVector.item(i)).getMatchingElementsFromParent(e, devCapVector);
			Assertions.assertEquals(vMatch.size(), 1);
			Assertions.assertEquals(vMatch.item(0), e.getElement("Media", null, i));
		}
	}

	/**
	 * @throws Exception
	 *
	 *
	 */
	@Test
	void testNumberState() throws Exception
	{
		final JDFDoc d = new JDFDoc("NumberState");
		final JDFNumberState ns = (JDFNumberState) d.getRoot();
		JDFNumberRangeList nrl = new JDFNumberRangeList("12.45~15.88");
		ns.setAllowedValueList(nrl);
		ns.setListType(EnumListType.RangeList);
		Assertions.assertTrue(ns.fitsValue("12.45~15.88", EnumFitsValue.Allowed));
		Assertions.assertTrue(ns.fitsValue("12.45~13.0", EnumFitsValue.Allowed));
		Assertions.assertFalse(ns.fitsValue("19.0~33.234", EnumFitsValue.Allowed));
		nrl = new JDFNumberRangeList("12.45~15.88 19.0~33.234");
		ns.setAllowedValueList(nrl);
		Assertions.assertTrue(ns.fitsValue("12.45", EnumFitsValue.Allowed));
		Assertions.assertTrue(ns.fitsValue("19.0~33.234", EnumFitsValue.Allowed));
		Assertions.assertFalse(ns.fitsValue("16.01", EnumFitsValue.Allowed));
	}

	/**
	 *
	 *
	 */
	@Test
	void testEnumerationState()
	{
		final JDFDoc d = new JDFDoc("EnumerationState");
		final JDFEnumerationState es = (JDFEnumerationState) d.getRoot();
		final VString v = new VString();
		v.add("foo");
		v.add("bar");

		es.setAllowedValueList(v);
		Assertions.assertTrue(es.fitsValue("foo", EnumFitsValue.Allowed));
		Assertions.assertTrue(es.fitsValue("bar", EnumFitsValue.Allowed));
		Assertions.assertFalse(es.fitsValue("fnarf", EnumFitsValue.Allowed));

		es.setListType(EnumListType.List);
		Assertions.assertTrue(es.fitsValue("foo", EnumFitsValue.Allowed));
		Assertions.assertTrue(es.fitsValue("foo bar", EnumFitsValue.Allowed));
		Assertions.assertTrue(es.fitsValue("foo bar foo", EnumFitsValue.Allowed));
		Assertions.assertFalse(es.fitsValue("foo bar fnarf", EnumFitsValue.Allowed));

		es.setListType(EnumListType.CompleteList);
		Assertions.assertFalse(es.fitsValue("foo", EnumFitsValue.Allowed));
		Assertions.assertTrue(es.fitsValue("foo bar", EnumFitsValue.Allowed));
		Assertions.assertTrue(es.fitsValue("bar foo", EnumFitsValue.Allowed));
		Assertions.assertFalse(es.fitsValue("foo bar foo", EnumFitsValue.Allowed));
		Assertions.assertFalse(es.fitsValue("foo bar fnarf", EnumFitsValue.Allowed));

		// TODO implement more list types
		// es.setListType(EnumListType.OrderedList);
		// Assert.assertFalse(es.fitsValue("foo", EnumFitsValue.Allowed));
		// Assert.assertTrue(es.fitsValue("foo bar", EnumFitsValue.Allowed));
		// Assert.assertFalse(es.fitsValue("bar foo", EnumFitsValue.Allowed));
		// Assert.assertFalse(es.fitsValue("foo bar foo", EnumFitsValue.Allowed));
		// Assert.assertFalse(es.fitsValue("foo bar fnarf", EnumFitsValue.Allowed));
	}

	/**
	 *
	 *
	 */
	@Test
	void testRegExp()
	{
		for (int i = 0; i < 2; i++)
		{
			final JDFDoc d = new JDFDoc("EnumerationState");
			final JDFEnumerationState es = (JDFEnumerationState) d.getRoot();

			es.setListType(EnumListType.List);
			es.setAllowedRegExp("a b( c)?( d)*");
			if (i == 1)
				es.setAllowedValueList(new VString("a b c d", " "));
			Assertions.assertTrue(es.fitsValue("a b", EnumFitsValue.Allowed));
			Assertions.assertTrue(es.fitsValue("a b c", EnumFitsValue.Allowed));
			Assertions.assertTrue(es.fitsValue("a b c d d", EnumFitsValue.Allowed));
			Assertions.assertFalse(es.fitsValue("a b c c", EnumFitsValue.Allowed));
			Assertions.assertFalse(es.fitsValue("a c b", EnumFitsValue.Allowed));
			Assertions.assertFalse(es.fitsValue("abc", EnumFitsValue.Allowed));
			Assertions.assertFalse(es.fitsValue("A b c", EnumFitsValue.Allowed));
		}
	}

	/**
	 *
	 *
	 */
	@Test
	void testNameState()
	{
		final JDFDoc d = new JDFDoc("NameState");
		final JDFNameState ns = (JDFNameState) d.getRoot();
		final VString nl = new VString();
		nl.add("anna~berta");
		ns.setAllowedValueList(nl);
		ns.setListType(EnumListType.RangeList);
		Assertions.assertTrue(ns.fitsValue("anna~berta", EnumFitsValue.Allowed));
		Assertions.assertFalse(ns.fitsValue("hans~otto", EnumFitsValue.Allowed));
		nl.add("anna~berta hans~otto");
		ns.setAllowedValueList(nl);
		Assertions.assertTrue(ns.fitsValue("anna~berta", EnumFitsValue.Allowed));
		Assertions.assertTrue(ns.fitsValue("hans~otto", EnumFitsValue.Allowed));
		ns.setAllowedValueList(null);
		ns.setAllowedRegExp("*");
		Assertions.assertTrue(ns.fitsValue("hans~otto", EnumFitsValue.Allowed));
		ns.setAllowedRegExp("[ab].*");
		Assertions.assertTrue(ns.fitsValue("al", EnumFitsValue.Allowed));
		Assertions.assertFalse(ns.fitsValue("cl", EnumFitsValue.Allowed));
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetBooleanState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFBooleanState bs = dc.appendBooleanState("foo");
		Assertions.assertEquals(bs.getName(), "foo");
		bs = dc.getBooleanState("bar");
		Assertions.assertNull(bs);
		bs = dc.getCreateBooleanState("bar");
		Assertions.assertNotNull(bs);
		Assertions.assertEquals(bs.getName(), "bar");
		bs = dc.getBooleanState("bar");
		Assertions.assertNotNull(bs);
		Assertions.assertEquals(bs.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetIntegerState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFIntegerState is = dc.appendIntegerState("foo");
		Assertions.assertEquals(is.getName(), "foo");
		is = dc.getIntegerState("bar");
		Assertions.assertNull(is);
		is = dc.getCreateIntegerState("bar");
		Assertions.assertNotNull(is);
		Assertions.assertEquals(is.getName(), "bar");
		is = dc.getIntegerState("bar");
		Assertions.assertNotNull(is);
		Assertions.assertEquals(is.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetInValidAttributes()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		dc.setDevNS(null);
		Assertions.assertEquals(dc.getInvalidAttributes(EnumValidationLevel.Complete, true, 0).size(), 0);
		dc.setName("Foo9182");
		Assertions.assertTrue(dc.getInvalidAttributes(EnumValidationLevel.RecursiveComplete, true, 0).contains("DevNS"));
		Assertions.assertTrue(dc.getInvalidAttributes(EnumValidationLevel.Complete, true, 0).contains("DevNS"));
		dc.setName("FooLink");
		Assertions.assertFalse(dc.getInvalidAttributes(EnumValidationLevel.Complete, true, 0).contains("DevNS"));
		dc.setName("ScreeningParams");
		Assertions.assertFalse(dc.getInvalidAttributes(EnumValidationLevel.Complete, true, 0).contains("DevNS"));
		dc.setName("ScreeningParams_");
		Assertions.assertTrue(dc.getInvalidAttributes(EnumValidationLevel.Complete, true, 0).contains("DevNS"));

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetNumberState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFNumberState ns = dc.appendNumberState("foo");
		Assertions.assertEquals(ns.getName(), "foo");
		ns = dc.getNumberState("bar");
		Assertions.assertNull(ns);
		ns = dc.getCreateNumberState("bar");
		Assertions.assertNotNull(ns);
		Assertions.assertEquals(ns.getName(), "bar");
		ns = dc.getNumberState("bar");
		Assertions.assertNotNull(ns);
		Assertions.assertEquals(ns.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetEnumerationState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFEnumerationState es = dc.appendEnumerationState("foo");
		Assertions.assertEquals(es.getName(), "foo");
		es = dc.getEnumerationState("bar");
		Assertions.assertNull(es);
		es = dc.getCreateEnumerationState("bar");
		Assertions.assertNotNull(es);
		Assertions.assertEquals(es.getName(), "bar");
		es = dc.getEnumerationState("bar");
		Assertions.assertNotNull(es);
		Assertions.assertEquals(es.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetNameState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFNameState ns = dc.appendNameState("foo");
		Assertions.assertEquals(ns.getName(), "foo");
		ns = dc.getNameState("bar");
		Assertions.assertNull(ns);
		ns = dc.getCreateNameState("bar");
		Assertions.assertNotNull(ns);
		Assertions.assertEquals(ns.getName(), "bar");
		ns = dc.getNameState("bar");
		Assertions.assertNotNull(ns);
		Assertions.assertEquals(ns.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetStringState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFStringState ss = dc.appendStringState("foo");
		Assertions.assertEquals(ss.getName(), "foo");
		ss = dc.getStringState("bar");
		Assertions.assertNull(ss);
		ss = dc.getCreateStringState("bar");
		Assertions.assertNotNull(ss);
		Assertions.assertEquals(ss.getName(), "bar");
		ss = dc.getStringState("bar");
		Assertions.assertNotNull(ss);
		Assertions.assertEquals(ss.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetXYPairState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFXYPairState xy = dc.appendXYPairState("foo");
		Assertions.assertEquals(xy.getName(), "foo");
		xy = dc.getXYPairState("bar");
		Assertions.assertNull(xy);
		xy = dc.getCreateXYPairState("bar");
		Assertions.assertNotNull(xy);
		Assertions.assertEquals(xy.getName(), "bar");
		xy = dc.getXYPairState("bar");
		Assertions.assertNotNull(xy);
		Assertions.assertEquals(xy.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetShapeState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFShapeState ss = dc.appendShapeState("foo");
		Assertions.assertEquals(ss.getName(), "foo");
		ss = dc.getShapeState("bar");
		Assertions.assertNull(ss);
		ss = dc.getCreateShapeState("bar");
		Assertions.assertNotNull(ss);
		Assertions.assertEquals(ss.getName(), "bar");
		ss = dc.getShapeState("bar");
		Assertions.assertNotNull(ss);
		Assertions.assertEquals(ss.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetAvailability()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		dc.setAvailability(EnumAvailability.NotInstalled);
		Assertions.assertEquals(dc.getAvailability(), EnumAvailability.NotInstalled);
		Assertions.assertEquals(dc.getModuleAvailability(), EnumAvailability.NotInstalled);
	}

	/**
	 *
	 *
	 */
	@Test
	void testAppendModuleRef()
	{
		final JDFDoc d = new JDFDoc("DeviceCap");
		final JDFDeviceCap deviceCap = (JDFDeviceCap) d.getRoot();
		final JDFDevCap dc = deviceCap.appendDevCaps().appendDevCap();
		dc.setAvailability(EnumAvailability.NotInstalled);
		Assertions.assertEquals(dc.getAvailability(), EnumAvailability.NotInstalled);
		Assertions.assertEquals(dc.getModuleAvailability(), EnumAvailability.NotInstalled);
		JDFModuleCap mc = dc.appendModuleRef("MyDev");
		mc.setAvailability(EnumAvailability.Disabled);
		Assertions.assertEquals(dc.getAvailability(), EnumAvailability.Module);
		Assertions.assertEquals(dc.getModuleAvailability(), EnumAvailability.Disabled);
		Assertions.assertEquals(mc.getAvailability(), EnumAvailability.Disabled);
		mc = dc.appendModuleRef("MyOtherDev");
		mc.setAvailability(EnumAvailability.NotInstalled);
		Assertions.assertEquals(dc.getModuleAvailability(), EnumAvailability.NotInstalled);
		Assertions.assertEquals(mc.getAvailability(), EnumAvailability.NotInstalled);
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetModuleAvailability()
	{
		final JDFDoc d = new JDFDoc("DeviceCap");
		final JDFDeviceCap deviceCap = (JDFDeviceCap) d.getRoot();
		final JDFModuleCap mc = deviceCap.appendModulePool().appendModuleCap();
		mc.setID("i");
		mc.setAvailability(EnumAvailability.NotLicensed);
		final JDFDevCap dc = deviceCap.appendDevCaps().appendDevCap();
		Assertions.assertEquals(dc.getModuleAvailability(), EnumAvailability.Installed);
		dc.setAvailability(EnumAvailability.Module);
		Assertions.assertEquals(dc.getAvailability(), EnumAvailability.Module);
		Assertions.assertNull(dc.getModuleAvailability());
		dc.setModuleRefs(new VString("i", null));
		Assertions.assertEquals(dc.getModuleAvailability(), EnumAvailability.NotLicensed);
	}

	/**
	 *
	 *
	 */
	@Test
	void testGetMatrixState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFMatrixState ms = dc.appendMatrixState("foo");
		Assertions.assertEquals(ms.getName(), "foo");
		ms = dc.getMatrixState("bar");
		Assertions.assertNull(ms);
		ms = dc.getCreateMatrixState("bar");
		Assertions.assertNotNull(ms);
		Assertions.assertEquals(ms.getName(), "bar");
		ms = dc.getMatrixState("bar");
		Assertions.assertNotNull(ms);
		Assertions.assertEquals(ms.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetDateTimeState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFDateTimeState dts = dc.appendDateTimeState("foo");
		Assertions.assertEquals(dts.getName(), "foo");
		dts = dc.getDateTimeState("bar");
		Assertions.assertNull(dts);
		dts = dc.getCreateDateTimeState("bar");
		Assertions.assertNotNull(dts);
		Assertions.assertEquals(dts.getName(), "bar");
		dts = dc.getDateTimeState("bar");
		Assertions.assertNotNull(dts);
		Assertions.assertEquals(dts.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetDurationState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFDurationState ds = dc.appendDurationState("foo");
		Assertions.assertEquals(ds.getName(), "foo");
		ds = dc.getDurationState("bar");
		Assertions.assertNull(ds);
		ds = dc.getCreateDurationState("bar");
		Assertions.assertNotNull(ds);
		Assertions.assertEquals(ds.getName(), "bar");
		ds = dc.getDurationState("bar");
		Assertions.assertNotNull(ds);
		Assertions.assertEquals(ds.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetPDFPathState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFPDFPathState pps = dc.appendPDFPathState("foo");
		Assertions.assertEquals(pps.getName(), "foo");
		pps = dc.getPDFPathState("bar");
		Assertions.assertNull(pps);
		pps = dc.getCreatePDFPathState("bar");
		Assertions.assertNotNull(pps);
		Assertions.assertEquals(pps.getName(), "bar");
		pps = dc.getPDFPathState("bar");
		Assertions.assertNotNull(pps);
		Assertions.assertEquals(pps.getName(), "bar");

	}

	/**
	 *
	 *
	 */
	@Test
	void testGetRectangleState()
	{
		final JDFDoc d = new JDFDoc("DevCap");
		final JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFRectangleState rs = dc.appendRectangleState("foo");
		Assertions.assertEquals(rs.getName(), "foo");
		rs = dc.getRectangleState("bar");
		Assertions.assertNull(rs);
		rs = dc.getCreateRectangleState("bar");
		Assertions.assertNotNull(rs);
		Assertions.assertEquals(rs.getName(), "bar");
		rs = dc.getRectangleState("bar");
		Assertions.assertNotNull(rs);
		Assertions.assertEquals(rs.getName(), "bar");
	}

	/**
	 *
	 *
	 */
	@Test
	void testStateReportRequired()
	{
		final JDFDoc d = new JDFDoc(ElementName.DEVICECAP);
		final JDFDeviceCap ddc = (JDFDeviceCap) d.getRoot();
		final JDFDevCap dc = ddc.appendDevCapPool().appendDevCap();
		dc.setName("foo");
		final JDFIntegerState is = dc.appendIntegerState();
		is.setName("bar1");
		is.setRequired(true);
		final JDFIntegerState is2 = dc.appendIntegerState();
		is2.setName("bar2");
		is2.setRequired(false);

		final JDFDoc d2 = new JDFDoc("foo");
		final KElement foo = d2.getRoot();

		final JDFDoc d3 = new JDFDoc("parent");
		final KElement parent = d3.getRoot();
		dc.stateReport(foo, EnumFitsValue.Allowed, EnumValidationLevel.Complete, false, true, parent);
		Assertions.assertTrue(parent.toString().indexOf("bar1") >= 0);
		Assertions.assertFalse(parent.toString().indexOf("bar2") >= 0);
	}
}
