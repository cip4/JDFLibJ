package org.cip4.jdflib.node;

import java.util.HashMap;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.junit.Test;

public class LinkValidatorMapTest extends JDFTestCaseBase
{

	/**
	 * 
	 */
	@Test
	public void testGetLinkNames()
	{
		VString linkInfo = LinkValidatorMap.getLinkValidatorMap().getLinkNames(EnumType.ConventionalPrinting, null);
		assertTrue(linkInfo.contains("ConventionalPrintingParams"));
		assertTrue(linkInfo.contains("Preview"));
		assertTrue(linkInfo.contains("Media"));
		assertTrue(linkInfo.contains("Component"));
	}

	/**
	 * 
	 */
	@Test
	public void testGetLinkInfo()
	{
		VString linkInfo = LinkValidatorMap.getLinkValidatorMap().getLinkNames(EnumType.ConventionalPrinting, null);
		Vector<LinkInfo> linkNames = LinkValidatorMap.getLinkValidatorMap().getLinkInfo(EnumType.ConventionalPrinting, null);
		assertEquals(linkInfo.size(), linkNames.size());
	}

	/**
	 * 
	 */
	@Test
	public void testGetLinkInfoMap()
	{
		HashMap<String, LinkInfo> linkInfo = LinkValidatorMap.getLinkValidatorMap().getLinkInfoMap(EnumType.ConventionalPrinting, null);
		LinkInfo cp = linkInfo.get("ConventionalPrintingParams");
		assertTrue(cp.getVString().contains("i_"));
	}

}
