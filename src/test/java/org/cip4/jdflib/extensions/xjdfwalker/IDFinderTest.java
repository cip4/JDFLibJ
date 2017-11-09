/**
 * 
 */
package org.cip4.jdflib.extensions.xjdfwalker;

import java.util.Map;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class IDFinderTest extends TestCase
{

	KElement root;

	/**
	 * Test method for {@link org.cip4.jdflib.extensions.xjdfwalker.IDFinder#getMap(KElement)}.
	 */
	@Test
	public void testGetMap()
	{
		IDFinder finder = new IDFinder();
		Map<String, IDPart> m = finder.getMap(root);
		Assert.assertEquals(m.size(), 9 + 3);
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		root = new XMLDoc("XJDF", null).getRoot();
		for (int i = 1; i < 4; i++)
		{
			root.setXPathAttribute("ResourceSet[" + i + "]/@ID", "R" + i);
			for (int j = 1; j < 4; j++)
			{
				root.setXPathAttribute("ResourceSet[" + i + "]/Resource[" + j + "]/@ID", "R" + i + "." + j + "");
				root.setXPathAttribute("ResourceSet[" + i + "]/Resource[" + j + "]/Part/@SheetName", "S" + j + "");
			}
		}
	}
}
