/**
 * 
 */
package org.cip4.jdflib.resource;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFPhaseTimeTest extends JDFTestCaseBase
{
	private JDFPhaseTime pt = null;

	/**
	 * Test method for {@link org.cip4.jdflib.resource.JDFPhaseTime#getDeviceID()}.
	 */
	public void testGetDeviceID()
	{
		testSetDeviceID();
		assertEquals("d1", pt.getDeviceID());
	}

	/**
	 * Test method for {@link org.cip4.jdflib.resource.JDFPhaseTime#setDeviceID(java.lang.String)}.
	 */
	public void testSetDeviceID()
	{
		pt.setDeviceID("d1");
		assertEquals(pt.getDevice(0).getDeviceID(), "d1");
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception
	*/
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		pt = new JDFDoc("JDF").getJDFRoot().getCreateAuditPool().addPhaseTime(EnumNodeStatus.InProgress, null, null);
	}

}
