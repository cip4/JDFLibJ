/**
 * 
 */
package org.cip4.jdflib.elementwalker;

import java.util.Map;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class ResourceIDFinderTest extends JDFTestCaseBase
{

	/**
	 * 
	 */
	public void testMap()
	{
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		JDFResource r1 = n.addResource("RunList", null);
		n.setType(EnumType.ProcessGroup);
		JDFNode n2 = n.addCombined(new VString("Trapping", null));
		JDFResource r2 = n2.addResource("RunList", EnumUsage.Output);

		Map<String, JDFResource> m = new ResourceIDFinder().getMap(n);
		assertEquals(m.size(), 2);
		assertEquals(m.get(r1.getID()), r1);
		assertEquals(m.get(r2.getID()), r2);
	}
}
