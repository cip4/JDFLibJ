/*
 * JDFExampleDocTest.java
 *
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoResourceAudit.EnumReason;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.examples.ExampleTest;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.StatusCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AmountTest extends ExampleTest
{
	private JDFNode n;
	private JDFComponent outComp;
	private JDFMedia inMedia;
	private JDFResourceLink rlOut;
	private JDFResourceLink rlMediaIn;
	private JDFDoc d;

	/**
	 * test specification of planned waste in AmountPool
	 *
	 *
	 */
	@Test
	void testPlannedWaste()
	{
		KElement.setLongID(false);
		d = new JDFDoc("JDF");
		n = d.getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);
		outComp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		rlOut = n.getLink(outComp, null);
		final JDFAmountPool ap = rlOut.getCreateAmountPool();

		final JDFComponent cover = (JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Cover");
		JDFAttributeMap map = cover.getPartMap();
		ap.appendXMLComment("Want 10000-10500 good cover sheets and allow for 500 waste cover sheets", null);
		map.put(EnumPartIDKey.Condition, "Good");
		rlOut.setAmount(10000, map);
		rlOut.setMaxAmount(10500, map);
		map.put(EnumPartIDKey.Condition, "Waste");
		rlOut.setMaxAmount(500, map);

		ap.appendXMLComment("Want 20000 good first insert sheets and allow for 200 waste insert sheets", null);
		final JDFComponent sheet1 = (JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Sheet1");
		map = sheet1.getPartMap();
		map.put(EnumPartIDKey.Condition, "Good");
		rlOut.setAmount(20000, map);
		map.put(EnumPartIDKey.Condition, "Waste");
		rlOut.setMaxAmount(200, map);

		ap.appendXMLComment("Want 20000 good second insert sheets and allow for 200 waste insert sheets", null);
		final JDFComponent sheet2 = (JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Sheet2");
		map = sheet2.getPartMap();
		map.put(EnumPartIDKey.Condition, "Good");
		rlOut.setAmount(20000, map);
		map.put(EnumPartIDKey.Condition, "Waste");
		rlOut.setMaxAmount(100, map);

		d.write2File(sm_dirTestDataTemp + "plannedWaste.jdf", 2, true);
	}

	/**
	 * test specification of planned waste in AmountPool
	 *
	 * @return
	 */
	@Test
	void testPlannedWasteICS()
	{
		final JDFAmountPool ap = rlOut.getCreateAmountPool();
		final JDFAmountPool apIn = rlMediaIn.getCreateAmountPool();

		final JDFComponent cover = (JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Cover");
		JDFAttributeMap map = cover.getPartMap();
		ap.appendXMLComment("Want 10000-10400 good cover sheets and allow for 500 waste cover sheets", null);
		map.put(EnumPartIDKey.Condition, "Good");
		rlOut.setAmount(10000, map);
		rlOut.setMaxAmount(10400, map);
		apIn.appendXMLComment("Amount[Good]: planned consumption for good production\n" + "MaxAmount[Good]: planned maximum consumption for good production\n"
				+ "MaxAmount[Waste]: planned Media for waste", null);
		rlMediaIn.setAmount(10500, map);
		map.put(EnumPartIDKey.Condition, "Waste");
		rlMediaIn.setMaxAmount(500, map);

		ap.appendXMLComment("Want 20000 good first insert sheets and allow for 200 waste insert sheets", null);
		final JDFComponent sheet1 = (JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Sheet1");
		map = sheet1.getPartMap();
		map.put(EnumPartIDKey.Condition, "Good");
		rlOut.setAmount(20000, map);
		rlOut.setMaxAmount(20800, map);
		rlMediaIn.setAmount(21000, map);
		map.put(EnumPartIDKey.Condition, "Waste");
		rlMediaIn.setMaxAmount(200, map);

		ap.appendXMLComment("Want 20000 good second insert sheets and allow for 100 waste insert sheets", null);
		final JDFComponent sheet2 = (JDFComponent) outComp.addPartition(EnumPartIDKey.SheetName, "Sheet2");
		map = sheet2.getPartMap();
		map.put(EnumPartIDKey.Condition, "Good");
		rlOut.setAmount(20000, map);
		rlOut.setMaxAmount(20800, map);
		rlMediaIn.setAmount(20900, map);
		map.put(EnumPartIDKey.Condition, "Waste");
		rlMediaIn.setMaxAmount(100, map);

		d.write2File(sm_dirTestDataTemp + "plannedWasteICS.jdf", 2, true);

		/*
		 * map=cover.getPartMap(); map.put(EnumPartIDKey.Condition, "Good");
		 * rl.getAmountPool().getPartAmount(map,0).appendXMLComment(
		 * "Actually produced covers: 10200\nWaste put on output stack:100");
		 * rlIn.getAmountPool().getPartAmount(map,0).appendXMLComment(
		 * "Total consumed sheets: 10400\nOf that: sheets wasted: 200");
		 * rl.setActualAmount(10200, map); rlIn.setActualAmount(10200, map);
		 * map.put(EnumPartIDKey.Condition, "Waste"); rl.setActualAmount(100,
		 * map); rlIn.setActualAmount(200, map);
		 *
		 * d.write2File(sm_dirTestDataTemp+"actualWasteICS.jdf", 2, true);
		 */
	}

	/**
	 * @return
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		d = new JDFDoc("JDF");

		n = d.getJDFRoot();
		n.appendXMLComment("Example to illustrate JDF 1.3 Base and MIS compatible amount handling", null);
		n.setType(EnumType.ConventionalPrinting);
		outComp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		inMedia = (JDFMedia) n.addResource(ElementName.MEDIA, null, EnumUsage.Input, null, null, null, null);
		rlOut = n.getLink(outComp, null);
		rlMediaIn = n.getLink(inMedia, null);
	}

	////////////////////////////////////////////////////////////////////////////
	// //////

	@Test
	void testAudits()
	{
		testPlannedWasteICS();
		final VString vs = new VString("Cover Sheet1 Sheet2", " ");

		final VElement vRL = new VElement();
		vRL.add(rlOut);
		vRL.add(rlMediaIn);

		for (int j = 0; j < 2; j++)
		{
			final boolean bMinimal = j == 0;

			for (int i = 0; i < vs.size(); i++)
			{
				final String sheet = vs.get(i);
				final VJDFAttributeMap vmP = new VJDFAttributeMap();
				vmP.add(new JDFAttributeMap(EnumPartIDKey.SheetName, sheet));
				final StatusCounter stUtil = new StatusCounter(n, vmP, vRL);

				final String refComp = rlOut.getrRef();
				final String refMedia = rlMediaIn.getrRef();

				stUtil.setTrackWaste(refComp, true);
				stUtil.setTrackWaste(refMedia, true);

				if (i == 0)
					stUtil.setPhase(EnumNodeStatus.Stopped, "PowerOn", EnumDeviceStatus.Stopped, "PowerOn");
				stUtil.setPhase(EnumNodeStatus.Setup, "FormChange", EnumDeviceStatus.Setup, "FormChange");
				stUtil.addPhase(refMedia, 0, 200, true);
				stUtil.addPhase(refComp, 0, 200, true);
				stUtil.setPhase(EnumNodeStatus.Setup, "FormChange", EnumDeviceStatus.Setup, "FormChange");

				if (i >= 1 && !bMinimal)
				{
					final JDFResourceAudit ra = stUtil.setResourceAudit(refMedia, EnumReason.ProcessResult);

					stUtil.setResourceAudit(refComp, EnumReason.ProcessResult);

					stUtil.clearAmounts(refMedia);
					stUtil.addPhase(refMedia, 50, 0, true);
					final JDFResourceAudit ra2 = stUtil.setResourceAudit(refMedia, EnumReason.OperatorInput);
					ra2.setRef(ra);
					ra2.setDescriptiveName("manual reset to using only 50 sheets because 100 initially were wastes");

				}
				stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);
				stUtil.addPhase(refMedia, 4000, 0, true);
				stUtil.addPhase(refComp, 4000, 0, true);
				stUtil.setPhase(EnumNodeStatus.Cleanup, "Washup during processing", EnumDeviceStatus.Cleanup, "Washup");
				stUtil.setPhase(EnumNodeStatus.InProgress, "Waste", EnumDeviceStatus.Running, null);

				stUtil.addPhase(refMedia, 0, i == 0 ? 40 : 30, true);
				stUtil.addPhase(refComp, 0, i == 0 ? 40 : 30, true);
				stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);

				stUtil.addPhase(refMedia, 1000, 0, true);
				stUtil.addPhase(refComp, 1000, 0, true);
				stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);
				stUtil.addPhase(refMedia, i == 0 ? 5200 : 5400, 0, true);
				stUtil.addPhase(refComp, i == 0 ? 5200 : 5400, 0, true);
				stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);

				final JDFResourceAudit ra = stUtil.setResourceAudit(refMedia, EnumReason.ProcessResult);

				if (!bMinimal)
				{
					stUtil.setResourceAudit(refComp, EnumReason.ProcessResult);

					stUtil.clearAmounts(refMedia);
					stUtil.addPhase(refMedia, 1 == 0 ? 10100 : 10200, 0, true);
					final JDFResourceAudit ra2 = stUtil.setResourceAudit(refMedia, EnumReason.OperatorInput);
					ra2.setRef(ra);
					ra2.setDescriptiveName("manual reset to using only 10200 sheets because 100 initially were  wates");
				}
				final JDFProcessRun pr = stUtil.setProcessResult(EnumNodeStatus.Completed);
				pr.setDescriptiveName("we even have the utterly useless ProcessRun");
			}
			if (bMinimal)
			{
				final JDFAuditPool ap = n.getAuditPool();
				final VElement audits = ap.getAudits(EnumAuditType.PhaseTime, null, null);
				for (int i = 0; i < audits.size(); i++)
				{
					audits.item(i).deleteNode();
				}
			}
			d.write2File(sm_dirTestDataTemp + File.separator + "ConvPrintAmount_" + (bMinimal ? "min" : "full") + ".jdf", 2, false);
		}
	}
}
