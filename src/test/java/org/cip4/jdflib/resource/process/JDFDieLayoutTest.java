package org.cip4.jdflib.resource.process;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.junit.Test;
public class JDFDieLayoutTest extends JDFTestCaseBase
{
	private JDFNode n;
	private JDFDoc d;

	/**
	 * tests the proposed Color/@PDLName attribute
	 * 
	 */
	@Test
	public final void testStationMapStrip() throws Exception
	{
		n.setType(EnumType.Stripping);
		JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, null);
		JDFDieLayout dl = bs.appendDieLayout();
		JDFStation station = dl.appendStation();
		station.setStationName("Lid");
		station.setStationAmount(9);
		station = dl.appendStation();
		station.setStationName("Box");
		station.setStationAmount(3);
		JDFResource sm = n.addResource("StationMap", EnumResourceClass.Parameter, null, null, null, null, null);
		sm.setXMLComment("The partition structure should reflect the product structure ");
		JDFResource sm1 = sm.addPartition(EnumPartIDKey.PartVersion, "Strawberry");
		sm1.setAttribute("StationName", "Lid");
		sm1.setAttribute("MapAmount", "3");
		sm1.setAttribute("StationIndex", "0 1 2");
		sm1.setXMLComment("3*Strawberry MUST be placed on station index 0-2");

		sm1 = sm.addPartition(EnumPartIDKey.PartVersion, "Blueberry");
		sm1.setAttribute("StationName", "Lid");
		sm1.setAttribute("MapAmount", "2");
		sm1.setXMLComment("2*Blueberry can be placed on any remaining lid station");

		sm1 = sm.addPartition(EnumPartIDKey.PartVersion, "Cherry");
		sm1.setAttribute("StationName", "Lid");
		sm1.setAttribute("MapAmount", "4");
		sm1.setXMLComment("4*Cherry can be placed on any remaining lid station");

		dl.refElement(sm);

		d.write2File(sm_dirTestDataTemp + "StationMapStripping.jdf", 2, false);

	}

	/**
	 * @return
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		d = new JDFDoc(ElementName.JDF);
		n = d.getJDFRoot();

	}

}
