/*
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
/*
 * MediaColorTest.java
 * @author Dietrich Mucha
 * 
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.devicecapability;

import java.util.Vector;

import junit.framework.TestCase;

import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumBoolean;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
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
import org.junit.Assert;
import org.junit.Test;
/**
 * 
 *  
 * @author rainer prosi
 * @date before Jun 7, 2012
 */
public class JDFDevCapTest extends TestCase
{
	/**
	 * 
	 *  
	 */
	@Test
	public void testBooleanState()
	{
		JDFDoc d = new JDFDoc("BooleanState");
		JDFBooleanState bs = (JDFBooleanState) d.getRoot();
		Vector<EnumBoolean> v = new Vector<EnumBoolean>();
		v.add(EnumBoolean.False);
		bs.setAllowedValueList(v);
		Assert.assertTrue(bs.fitsValue("false", EnumFitsValue.Allowed));
		Assert.assertFalse(bs.fitsValue("fnarf", EnumFitsValue.Allowed));
		Assert.assertFalse(bs.fitsValue("true", EnumFitsValue.Allowed));
		v.add(EnumBoolean.True);
		bs.setAllowedValueList(v);
		Assert.assertTrue(bs.fitsValue("true", EnumFitsValue.Allowed));
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testAppendStringState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap root = (JDFDevCap) d.getRoot();
		JDFStringState ss1 = root.appendStringState("foo");
		Assert.assertEquals(ss1.getName(), "foo");
		JDFStringState ss2 = root.appendStringState(null);
		Assert.assertFalse(ss2.hasAttribute("Name"));
	}

	/**
	 * @throws Exception 
	 * 
	 *  
	 */
	@Test
	public void testIntegerState() throws Exception
	{
		JDFDoc d = new JDFDoc("IntegerState");
		JDFIntegerState is = (JDFIntegerState) d.getRoot();
		JDFIntegerRangeList irl = new JDFIntegerRangeList("12~15");
		is.setAllowedValueList(irl);
		is.setListType(EnumListType.RangeList);
		Assert.assertTrue(is.fitsValue("12~15", EnumFitsValue.Allowed));
		Assert.assertFalse(is.fitsValue("19~33", EnumFitsValue.Allowed));
		irl = new JDFIntegerRangeList("12~15 19~33");
		is.setAllowedValueList(irl);
		Assert.assertTrue(is.fitsValue("12~15", EnumFitsValue.Allowed));
		Assert.assertTrue(is.fitsValue("19~33", EnumFitsValue.Allowed));
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testgetMatchingElementsFromParentSingle()
	{
		JDFDoc ddc = new JDFDoc("DevCap");
		JDFDoc dde = new JDFDoc("Layout");
		JDFDevCap dc = (JDFDevCap) ddc.getRoot();
		JDFLayout e = (JDFLayout) dde.getRoot();

		JDFDevCap dc1 = dc.appendDevCap();
		dc1.setName("Media");
		dc1.setMaxOccurs(1);
		dc1.setMinOccurs(1);

		for (int i = 0; i < 2; i++)
		{
			final String mediaType = i == 0 ? "Paper" : "Plate";
			e.appendElement("Media").setAttribute("MediaType", mediaType);

		}
		final VElement devCapVector = dc.getDevCapVector(null, true);
		VElement vMatch = ((JDFDevCap) devCapVector.item(0)).getMatchingElementsFromParent(e, devCapVector);
		Assert.assertEquals(vMatch.size(), 2);
		Assert.assertEquals(vMatch.item(0), e.getElement("Media", null, 0));
		Assert.assertEquals(vMatch.item(1), e.getElement("Media", null, 1));
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testgetMatchingElementsFromParentMulti()
	{
		JDFDoc ddc = new JDFDoc("DevCap");
		JDFDoc dde = new JDFDoc("Layout");
		JDFDevCap dc = (JDFDevCap) ddc.getRoot();
		JDFLayout e = (JDFLayout) dde.getRoot();

		for (int i = 0; i < 2; i++)
		{
			JDFDevCap dc1 = dc.appendDevCap();
			dc1.setName("Media");
			dc1.setMaxOccurs(1);
			dc1.setMinOccurs(1);
			JDFEnumerationState es = dc1.appendEnumerationState("MediaType");
			final String mediaType = i == 0 ? "Paper" : "Plate";
			es.setAllowedValueList(new VString(mediaType, null));

			e.appendElement("Media").setAttribute("MediaType", mediaType);

		}
		final VElement devCapVector = dc.getDevCapVector(null, true);
		for (int i = 0; i < 2; i++)
		{
			VElement vMatch = ((JDFDevCap) devCapVector.item(i)).getMatchingElementsFromParent(e, devCapVector);
			Assert.assertEquals(vMatch.size(), 1);
			Assert.assertEquals(vMatch.item(0), e.getElement("Media", null, i));
		}
	}

	/**
	 * @throws Exception 
	 * 
	 *  
	 */
	@Test
	public void testNumberState() throws Exception
	{
		JDFDoc d = new JDFDoc("NumberState");
		JDFNumberState ns = (JDFNumberState) d.getRoot();
		JDFNumberRangeList nrl = new JDFNumberRangeList("12.45~15.88");
		ns.setAllowedValueList(nrl);
		ns.setListType(EnumListType.RangeList);
		Assert.assertTrue(ns.fitsValue("12.45~15.88", EnumFitsValue.Allowed));
		Assert.assertTrue(ns.fitsValue("12.45~13.0", EnumFitsValue.Allowed));
		Assert.assertFalse(ns.fitsValue("19.0~33.234", EnumFitsValue.Allowed));
		nrl = new JDFNumberRangeList("12.45~15.88 19.0~33.234");
		ns.setAllowedValueList(nrl);
		Assert.assertTrue(ns.fitsValue("12.45", EnumFitsValue.Allowed));
		Assert.assertTrue(ns.fitsValue("19.0~33.234", EnumFitsValue.Allowed));
		Assert.assertFalse(ns.fitsValue("16.01", EnumFitsValue.Allowed));
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testEnumerationState()
	{
		JDFDoc d = new JDFDoc("EnumerationState");
		JDFEnumerationState es = (JDFEnumerationState) d.getRoot();
		VString v = new VString();
		v.add("foo");
		v.add("bar");

		es.setAllowedValueList(v);
		Assert.assertTrue(es.fitsValue("foo", EnumFitsValue.Allowed));
		Assert.assertTrue(es.fitsValue("bar", EnumFitsValue.Allowed));
		Assert.assertFalse(es.fitsValue("fnarf", EnumFitsValue.Allowed));

		es.setListType(EnumListType.List);
		Assert.assertTrue(es.fitsValue("foo", EnumFitsValue.Allowed));
		Assert.assertTrue(es.fitsValue("foo bar", EnumFitsValue.Allowed));
		Assert.assertTrue(es.fitsValue("foo bar foo", EnumFitsValue.Allowed));
		Assert.assertFalse(es.fitsValue("foo bar fnarf", EnumFitsValue.Allowed));

		es.setListType(EnumListType.CompleteList);
		Assert.assertFalse(es.fitsValue("foo", EnumFitsValue.Allowed));
		Assert.assertTrue(es.fitsValue("foo bar", EnumFitsValue.Allowed));
		Assert.assertTrue(es.fitsValue("bar foo", EnumFitsValue.Allowed));
		Assert.assertFalse(es.fitsValue("foo bar foo", EnumFitsValue.Allowed));
		Assert.assertFalse(es.fitsValue("foo bar fnarf", EnumFitsValue.Allowed));

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
	public void testRegExp()
	{
		for (int i = 0; i < 2; i++)
		{
			JDFDoc d = new JDFDoc("EnumerationState");
			JDFEnumerationState es = (JDFEnumerationState) d.getRoot();

			es.setListType(EnumListType.List);
			es.setAllowedRegExp("a b( c)?( d)*");
			if (i == 1)
				es.setAllowedValueList(new VString("a b c d", " "));
			Assert.assertTrue(es.fitsValue("a b", EnumFitsValue.Allowed));
			Assert.assertTrue(es.fitsValue("a b c", EnumFitsValue.Allowed));
			Assert.assertTrue(es.fitsValue("a b c d d", EnumFitsValue.Allowed));
			Assert.assertFalse(es.fitsValue("a b c c", EnumFitsValue.Allowed));
			Assert.assertFalse(es.fitsValue("a c b", EnumFitsValue.Allowed));
			Assert.assertFalse(es.fitsValue("abc", EnumFitsValue.Allowed));
			Assert.assertFalse(es.fitsValue("A b c", EnumFitsValue.Allowed));
		}
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testNameState()
	{
		JDFDoc d = new JDFDoc("NameState");
		JDFNameState ns = (JDFNameState) d.getRoot();
		VString nl = new VString();
		nl.add("anna~berta");
		ns.setAllowedValueList(nl);
		ns.setListType(EnumListType.RangeList);
		Assert.assertTrue(ns.fitsValue("anna~berta", EnumFitsValue.Allowed));
		Assert.assertFalse(ns.fitsValue("hans~otto", EnumFitsValue.Allowed));
		nl.add("anna~berta hans~otto");
		ns.setAllowedValueList(nl);
		Assert.assertTrue(ns.fitsValue("anna~berta", EnumFitsValue.Allowed));
		Assert.assertTrue(ns.fitsValue("hans~otto", EnumFitsValue.Allowed));
		ns.setAllowedValueList(null);
		ns.setAllowedRegExp("*");
		Assert.assertTrue(ns.fitsValue("hans~otto", EnumFitsValue.Allowed));
		ns.setAllowedRegExp("[ab].*");
		Assert.assertTrue(ns.fitsValue("al", EnumFitsValue.Allowed));
		Assert.assertFalse(ns.fitsValue("cl", EnumFitsValue.Allowed));
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetBooleanState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFBooleanState bs = dc.appendBooleanState("foo");
		Assert.assertEquals(bs.getName(), "foo");
		bs = dc.getBooleanState("bar");
		Assert.assertNull(bs);
		bs = dc.getCreateBooleanState("bar");
		Assert.assertNotNull(bs);
		Assert.assertEquals(bs.getName(), "bar");
		bs = dc.getBooleanState("bar");
		Assert.assertNotNull(bs);
		Assert.assertEquals(bs.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetIntegerState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFIntegerState is = dc.appendIntegerState("foo");
		Assert.assertEquals(is.getName(), "foo");
		is = dc.getIntegerState("bar");
		Assert.assertNull(is);
		is = dc.getCreateIntegerState("bar");
		Assert.assertNotNull(is);
		Assert.assertEquals(is.getName(), "bar");
		is = dc.getIntegerState("bar");
		Assert.assertNotNull(is);
		Assert.assertEquals(is.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetInValidAttributes()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		dc.setDevNS(null);
		Assert.assertEquals(dc.getInvalidAttributes(EnumValidationLevel.Complete, true, 0).size(), 0);
		dc.setName("Foo9182");
		Assert.assertTrue(dc.getInvalidAttributes(EnumValidationLevel.RecursiveComplete, true, 0).contains("DevNS"));
		Assert.assertTrue(dc.getInvalidAttributes(EnumValidationLevel.Complete, true, 0).contains("DevNS"));
		dc.setName("FooLink");
		Assert.assertFalse(dc.getInvalidAttributes(EnumValidationLevel.Complete, true, 0).contains("DevNS"));
		dc.setName("ScreeningParams");
		Assert.assertFalse(dc.getInvalidAttributes(EnumValidationLevel.Complete, true, 0).contains("DevNS"));
		dc.setName("ScreeningParams_");
		Assert.assertTrue(dc.getInvalidAttributes(EnumValidationLevel.Complete, true, 0).contains("DevNS"));

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetNumberState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFNumberState ns = dc.appendNumberState("foo");
		Assert.assertEquals(ns.getName(), "foo");
		ns = dc.getNumberState("bar");
		Assert.assertNull(ns);
		ns = dc.getCreateNumberState("bar");
		Assert.assertNotNull(ns);
		Assert.assertEquals(ns.getName(), "bar");
		ns = dc.getNumberState("bar");
		Assert.assertNotNull(ns);
		Assert.assertEquals(ns.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetEnumerationState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFEnumerationState es = dc.appendEnumerationState("foo");
		Assert.assertEquals(es.getName(), "foo");
		es = dc.getEnumerationState("bar");
		Assert.assertNull(es);
		es = dc.getCreateEnumerationState("bar");
		Assert.assertNotNull(es);
		Assert.assertEquals(es.getName(), "bar");
		es = dc.getEnumerationState("bar");
		Assert.assertNotNull(es);
		Assert.assertEquals(es.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetNameState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFNameState ns = dc.appendNameState("foo");
		Assert.assertEquals(ns.getName(), "foo");
		ns = dc.getNameState("bar");
		Assert.assertNull(ns);
		ns = dc.getCreateNameState("bar");
		Assert.assertNotNull(ns);
		Assert.assertEquals(ns.getName(), "bar");
		ns = dc.getNameState("bar");
		Assert.assertNotNull(ns);
		Assert.assertEquals(ns.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetStringState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFStringState ss = dc.appendStringState("foo");
		Assert.assertEquals(ss.getName(), "foo");
		ss = dc.getStringState("bar");
		Assert.assertNull(ss);
		ss = dc.getCreateStringState("bar");
		Assert.assertNotNull(ss);
		Assert.assertEquals(ss.getName(), "bar");
		ss = dc.getStringState("bar");
		Assert.assertNotNull(ss);
		Assert.assertEquals(ss.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetXYPairState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFXYPairState xy = dc.appendXYPairState("foo");
		Assert.assertEquals(xy.getName(), "foo");
		xy = dc.getXYPairState("bar");
		Assert.assertNull(xy);
		xy = dc.getCreateXYPairState("bar");
		Assert.assertNotNull(xy);
		Assert.assertEquals(xy.getName(), "bar");
		xy = dc.getXYPairState("bar");
		Assert.assertNotNull(xy);
		Assert.assertEquals(xy.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetShapeState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFShapeState ss = dc.appendShapeState("foo");
		Assert.assertEquals(ss.getName(), "foo");
		ss = dc.getShapeState("bar");
		Assert.assertNull(ss);
		ss = dc.getCreateShapeState("bar");
		Assert.assertNotNull(ss);
		Assert.assertEquals(ss.getName(), "bar");
		ss = dc.getShapeState("bar");
		Assert.assertNotNull(ss);
		Assert.assertEquals(ss.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetAvailability()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		dc.setAvailability(EnumAvailability.NotInstalled);
		Assert.assertEquals(dc.getAvailability(), EnumAvailability.NotInstalled);
		Assert.assertEquals(dc.getModuleAvailability(), EnumAvailability.NotInstalled);
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testAppendModuleRef()
	{
		JDFDoc d = new JDFDoc("DeviceCap");
		JDFDeviceCap deviceCap = (JDFDeviceCap) d.getRoot();
		JDFDevCap dc = deviceCap.appendDevCaps().appendDevCap();
		dc.setAvailability(EnumAvailability.NotInstalled);
		Assert.assertEquals(dc.getAvailability(), EnumAvailability.NotInstalled);
		Assert.assertEquals(dc.getModuleAvailability(), EnumAvailability.NotInstalled);
		JDFModuleCap mc = dc.appendModuleRef("MyDev");
		mc.setAvailability(EnumAvailability.Disabled);
		Assert.assertEquals(dc.getAvailability(), EnumAvailability.Module);
		Assert.assertEquals(dc.getModuleAvailability(), EnumAvailability.Disabled);
		Assert.assertEquals(mc.getAvailability(), EnumAvailability.Disabled);
		mc = dc.appendModuleRef("MyOtherDev");
		mc.setAvailability(EnumAvailability.NotInstalled);
		Assert.assertEquals(dc.getModuleAvailability(), EnumAvailability.NotInstalled);
		Assert.assertEquals(mc.getAvailability(), EnumAvailability.NotInstalled);
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetModuleAvailability()
	{
		JDFDoc d = new JDFDoc("DeviceCap");
		JDFDeviceCap deviceCap = (JDFDeviceCap) d.getRoot();
		JDFModuleCap mc = deviceCap.appendModulePool().appendModuleCap();
		mc.setID("i");
		mc.setAvailability(EnumAvailability.NotLicensed);
		JDFDevCap dc = deviceCap.appendDevCaps().appendDevCap();
		Assert.assertEquals(dc.getModuleAvailability(), EnumAvailability.Installed);
		dc.setAvailability(EnumAvailability.Module);
		Assert.assertEquals(dc.getAvailability(), EnumAvailability.Module);
		Assert.assertNull(dc.getModuleAvailability());
		dc.setModuleRefs(new VString("i", null));
		Assert.assertEquals(dc.getModuleAvailability(), EnumAvailability.NotLicensed);
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetMatrixState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFMatrixState ms = dc.appendMatrixState("foo");
		Assert.assertEquals(ms.getName(), "foo");
		ms = dc.getMatrixState("bar");
		Assert.assertNull(ms);
		ms = dc.getCreateMatrixState("bar");
		Assert.assertNotNull(ms);
		Assert.assertEquals(ms.getName(), "bar");
		ms = dc.getMatrixState("bar");
		Assert.assertNotNull(ms);
		Assert.assertEquals(ms.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetDateTimeState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFDateTimeState dts = dc.appendDateTimeState("foo");
		Assert.assertEquals(dts.getName(), "foo");
		dts = dc.getDateTimeState("bar");
		Assert.assertNull(dts);
		dts = dc.getCreateDateTimeState("bar");
		Assert.assertNotNull(dts);
		Assert.assertEquals(dts.getName(), "bar");
		dts = dc.getDateTimeState("bar");
		Assert.assertNotNull(dts);
		Assert.assertEquals(dts.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetDurationState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFDurationState ds = dc.appendDurationState("foo");
		Assert.assertEquals(ds.getName(), "foo");
		ds = dc.getDurationState("bar");
		Assert.assertNull(ds);
		ds = dc.getCreateDurationState("bar");
		Assert.assertNotNull(ds);
		Assert.assertEquals(ds.getName(), "bar");
		ds = dc.getDurationState("bar");
		Assert.assertNotNull(ds);
		Assert.assertEquals(ds.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetPDFPathState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFPDFPathState pps = dc.appendPDFPathState("foo");
		Assert.assertEquals(pps.getName(), "foo");
		pps = dc.getPDFPathState("bar");
		Assert.assertNull(pps);
		pps = dc.getCreatePDFPathState("bar");
		Assert.assertNotNull(pps);
		Assert.assertEquals(pps.getName(), "bar");
		pps = dc.getPDFPathState("bar");
		Assert.assertNotNull(pps);
		Assert.assertEquals(pps.getName(), "bar");

	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testGetRectangleState()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		JDFRectangleState rs = dc.appendRectangleState("foo");
		Assert.assertEquals(rs.getName(), "foo");
		rs = dc.getRectangleState("bar");
		Assert.assertNull(rs);
		rs = dc.getCreateRectangleState("bar");
		Assert.assertNotNull(rs);
		Assert.assertEquals(rs.getName(), "bar");
		rs = dc.getRectangleState("bar");
		Assert.assertNotNull(rs);
		Assert.assertEquals(rs.getName(), "bar");
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testStateReportRequired()
	{
		JDFDoc d = new JDFDoc("DevCap");
		JDFDevCap dc = (JDFDevCap) d.getRoot();
		dc.setName("foo");
		JDFIntegerState is = dc.appendIntegerState();
		is.setName("bar1");
		is.setRequired(true);
		JDFIntegerState is2 = dc.appendIntegerState();
		is2.setName("bar2");
		is2.setRequired(false);

		JDFDoc d2 = new JDFDoc("foo");
		KElement foo = d2.getRoot();

		JDFDoc d3 = new JDFDoc("parent");
		KElement parent = d3.getRoot();
		dc.stateReport(foo, EnumFitsValue.Allowed, EnumValidationLevel.Complete, false, true, parent);
		Assert.assertTrue(parent.toString().indexOf("bar1") >= 0);
		Assert.assertFalse(parent.toString().indexOf("bar2") >= 0);
	}
}
