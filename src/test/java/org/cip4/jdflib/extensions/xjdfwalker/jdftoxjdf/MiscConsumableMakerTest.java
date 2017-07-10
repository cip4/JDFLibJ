package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import static org.junit.Assert.assertEquals;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumNamedColor;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.resource.JDFHeadBandApplicationParams;
import org.junit.Test;

public class MiscConsumableMakerTest
{
	/**
	 * 
	 */
	@Test
	public void testCreate()
	{
		XJDFHelper root = new XJDFHelper("j1", "p1", null);
		SetHelper sh = root.getCreateSet(XJDFConstants.Resource, ElementName.HEADBANDAPPLICATIONPARAMS, EnumUsage.Input);
		ResourceHelper ph = sh.getCreatePartition(null, true);
		JDFHeadBandApplicationParams hp = (JDFHeadBandApplicationParams) ph.getResource();
		MiscConsumableMaker m = new MiscConsumableMaker(ph);
		ResourceHelper misc = m.create("Headband");
		assertEquals(misc.getResource().getAttribute(AttributeName.TYPE), "Headband");
		assertEquals(misc.getSet().getProcessUsage(), "Headband");
	}

	/**
	 * 
	 */
	@Test
	public void testBrand()
	{
		XJDFHelper root = new XJDFHelper("j1", "p1", null);
		SetHelper sh = root.getCreateSet(XJDFConstants.Resource, ElementName.HEADBANDAPPLICATIONPARAMS, EnumUsage.Input);
		ResourceHelper ph = sh.getCreatePartition(null, true);
		JDFHeadBandApplicationParams hp = (JDFHeadBandApplicationParams) ph.getResource();
		hp.setTopBrand("b1");
		MiscConsumableMaker m = new MiscConsumableMaker(ph);
		ResourceHelper misc = m.create("Headband");
		m.setBrand("b1");
		assertEquals(misc.getBrand(), "b1");
	}

	/**
	 * 
	 */
	@Test
	public void testColor()
	{
		XJDFHelper root = new XJDFHelper("j1", "p1", null);
		SetHelper sh = root.getCreateSet(XJDFConstants.Resource, ElementName.HEADBANDAPPLICATIONPARAMS, EnumUsage.Input);
		ResourceHelper ph = sh.getCreatePartition(null, true);
		JDFHeadBandApplicationParams hp = (JDFHeadBandApplicationParams) ph.getResource();
		hp.setTopBrand("b1");
		hp.setTopColor(EnumNamedColor.Black);
		MiscConsumableMaker m = new MiscConsumableMaker(ph);
		ResourceHelper misc = m.create("Headband");
		m.setColor("Black");
		assertEquals(misc.getResource().getAttribute(AttributeName.COLOR), "Black");
	}

}
