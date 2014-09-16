/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFByteMap;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.junit.Assert;
import org.junit.Test;
public class WebTest extends JDFTestCaseBase
{
	private JDFNode node;
	private JDFNodeInfo nodeInfo;
	private JDFDoc doc;

	/**
	 * test WebGrowth Compensation
	 */
	@Test
	public void testWebGrowthCompensation()
	{

		KElement.setLongID(false);
		doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		final JDFResourcePool rp = n.getCreateResourcePool();
		final JDFResource lo = n.addResource("Layout", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		final JDFLayout losh = (JDFLayout) lo.addPartition(EnumPartIDKey.SheetName, "Sheet1");
		final JDFLayout lofr = (JDFLayout) losh.addPartition(EnumPartIDKey.Side, EnumSide.Front.getName());

		rp.appendXMLComment("LayoutShift SHOULD be partitioned: at least Side and Separation will make sense", null);

		JDFResource los = n.addResource("LayoutShift", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		los.appendXMLComment("Note that the interpolation algorithm between positions is implementation dependent", null);
		los = los.addPartition(EnumPartIDKey.Side, "Front");
		final VString vSep = new VString("Cyan Magenta Yellow Black", " ");

		for (int i = 0; i < 16; i++)
		{
			final int x = 720 * (i % 4);
			final int y = 1000 * (i / 4);
			final int ord = i % 8;
			final JDFContentObject co = lofr.appendContentObject();
			co.setOrd(ord);
			co.setOrdID(i);
			co.setCTM(new JDFMatrix(1, 0, 0, 1, x, y));
			final JDFMarkObject mo = lofr.appendMarkObject();
			mo.setOrd(ord);
			mo.setOrdID(i + 100);
			mo.setCTM(new JDFMatrix(1, 0, 0, 1, x + 700, y + 900));
		}
		for (int j = 0; j < vSep.size(); j++)
		{
			final KElement sepShift = los.addPartition(EnumPartIDKey.Separation, vSep.get(j));
			for (int i = 0; i < 16; i += 2)
			{

				final int x = 720 * (i % 4);
				final int y = 1000 * (i / 4);
				final KElement shiftObject = sepShift.appendElement("ShiftPoint");

				shiftObject.setAttribute("Position", new JDFXYPair(x + 360, y + 500).toString());
				shiftObject.setAttribute("CTM", new JDFMatrix(1, 0, 0, 1, j + i / 4, j + i % 4).toString());
			}
		}
		doc.write2File(sm_dirTestDataTemp + "WebgrowthPartition.jdf", 2, false);

	}

	/**
	 * test direct imaging
	 */
	@Test
	public void testDirectImage()
	{

		KElement.setLongID(false);
		doc = new JDFDoc("JDF");
		node = doc.getJDFRoot();
		node.setType(EnumType.Combined);
		final VString vTypes = new VString("ImageSetting ConventionalPrinting", " ");
		node.setTypes(vTypes);
		nodeInfo = node.appendNodeInfo();
		nodeInfo.setResStatus(EnumResStatus.Available, true);
		final JDFRunList rl = (JDFRunList) node.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
		final JDFByteMap bm = rl.appendByteMap();
		bm.appendFileSpec().setURL("File:///foo.tif");
		final JDFExposedMedia xm = (JDFExposedMedia) node.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.Plate, null);
		xm.setDescriptiveName("Real Plate");
		xm.appendMedia();
		final JDFExposedMedia xmCyl = (JDFExposedMedia) node.appendMatchingResource(ElementName.EXPOSEDMEDIA, EnumProcessUsage.Cylinder, null);
		xmCyl.setDescriptiveName("Optional cylinder");
		node.linkMatchingResource(xmCyl, EnumProcessUsage.AnyOutput, null);
		Assert.assertEquals("2 for cylinder one for plate", node.getResourceLinkPool().numChildElements("ExposedMediaLink", null), 3);
		doc.write2File(sm_dirTestDataTemp + File.separator + "webDirect.jdf", 2, false);

	}

	// /////////////////////////////////////////////////////////////////
	@Test
	public void testSplitDuct()
	{
		// TODO
	}

	// /////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////

}
