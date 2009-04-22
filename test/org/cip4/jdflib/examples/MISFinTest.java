/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.auto.JDFAutoBundle.EnumBundleType;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.goldenticket.BaseGoldenTicketTest;
import org.cip4.jdflib.goldenticket.MISFinGoldenTicket;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFBundle;
import org.cip4.jdflib.resource.JDFBundleItem;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFComponent;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * Apr 1, 2009
 */
public class MISFinTest extends BaseGoldenTicketTest
{
	/**
	 * test MIS to Finishing ICS
	 * 
	 * 
	 */
	public void testAmount()
	{
		JDFElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		n.setTypes(new VString("Binding Stacking BoxPacking Palletizing", " "));
		final JDFComponent comp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		final JDFResourceLink rl = n.getLink(comp, null);
		rl.setAmount(2, null);
		rl.setDescriptiveName("The link point to 2 pallets with a total comtent of 10000 brochures in 200 boxes");

		// create a pallet partition - may also use the root
		final JDFComponent compPallet = (JDFComponent) comp.addPartition(EnumPartIDKey.DeliveryUnit0, "Pallet");

		final JDFComponent compBox = (JDFComponent) compPallet.addPartition(EnumPartIDKey.DeliveryUnit1, "Box");
		final JDFBundle bundlePallet = compPallet.appendBundle();
		bundlePallet.setDescriptiveName("Bundle describing 100 boxes with 5000 Brochures Contents total");
		bundlePallet.setTotalAmount(5000);
		bundlePallet.setBundleType(EnumBundleType.Pallet);
		final JDFBundleItem biBoxes = bundlePallet.appendBundleItem();
		biBoxes.refElement(compBox);
		biBoxes.setAmount(100);

		final JDFComponent compBrochure = (JDFComponent) compBox.addPartition(EnumPartIDKey.DeliveryUnit2, "Brochure");
		final JDFBundle bundleBox = compBox.appendBundle();
		bundleBox.setDescriptiveName("Bundle describing 1 boxes with 50 Brochures Contents per box");
		bundleBox.setTotalAmount(50);
		bundleBox.setBundleType(EnumBundleType.Box);

		final JDFBundleItem biBrochures = bundleBox.appendBundleItem();
		biBrochures.refElement(compBrochure);
		biBrochures.setAmount(50);

		final JDFBundle bundleBrochure = compBrochure.appendBundle();
		bundleBrochure.setDescriptiveName("Dummy Bundle to inhibit inheritence of the box Bundle");
		d.write2File(sm_dirTestDataTemp + File.separator + "MISFinAmount.jdf", 2, false);
	}

	// ///////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void testAmountPalletteManifest()
	{
		JDFElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		n.setTypes(new VString("Binding Stacking BoxPacking Palletizing", " "));
		final JDFComponent comp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		final JDFResourceLink rl = n.getLink(comp, null);
		rl.setAmount(2, null);
		rl.setDescriptiveName("The link point to 2 pallets with a total comtent of 10000 brochures in 200 boxes");
		final JDFComponent compBrochure = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Input, null, null, null, null);
		compBrochure.setResStatus(EnumResStatus.Available, true);
		compBrochure.setDescriptiveName("The individual Brochures");
		final JDFResourceLink rlB = n.getLink(compBrochure, null);
		rlB.setAmount(10000, null);
		rlB.setActualAmount(9700, null);

		for (int i = 0; i < 2; i++)
		{
			// create a pallet partition - may also use the root
			final JDFComponent compPallet = (JDFComponent) comp.addPartition(EnumPartIDKey.DeliveryUnit0, "Pallet" + i);
			compPallet.setProductID("Pallet_" + i);

			final JDFBundle bundlePallet = compPallet.getCreateBundle();
			final int nBox = i == 0 ? 100 : 94;
			bundlePallet.setDescriptiveName("Pallet Bundle describing " + nBox + " boxes with " + nBox * 50 + " Brochures Contents total");
			bundlePallet.setTotalAmount(nBox * 50);
			bundlePallet.setBundleType(EnumBundleType.Pallet);
			final JDFBundleItem biBoxes = bundlePallet.appendBundleItem();

			final JDFComponent compBox = (JDFComponent) compPallet.addPartition(EnumPartIDKey.DeliveryUnit1, "Box");
			biBoxes.refElement(compBox);
			biBoxes.setAmount(nBox);

			final JDFBundle bundleBox = compBox.appendBundle();
			bundleBox.setDescriptiveName("Bundle describing 1 boxes with 50 Brochures Contents per box");
			bundleBox.setTotalAmount(50);
			bundleBox.setBundleType(EnumBundleType.Box);

			final JDFBundleItem biBrochures = bundleBox.appendBundleItem();
			biBrochures.refElement(compBrochure);
			biBrochures.setAmount(50);
		}

		d.write2File(sm_dirTestDataTemp + File.separator + "MISFinAmountManifest.jdf", 2, false);
	}

	// ///////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public void testStitchGB()
	{
		final MISFinGoldenTicket fgt = new MISFinGoldenTicket(2, EnumVersion.Version_1_3, 2, 2, null);
		fgt.setCategory(MISFinGoldenTicket.MISFIN_STITCHFIN);
		fgt.assign(null);
		fgt.bExpandGrayBox = false;
		write3GTFiles(fgt, "GBStitching");
	}

	/**
		 * 
		 */
	public void testAmountPalletteCompleteManifest()
	{
		JDFElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		n.setTypes(new VString("Binding Stacking BoxPacking Palletizing", " "));
		final JDFComponent comp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		final JDFResourceLink rl = n.getLink(comp, null);
		rl.setAmount(2, null);
		rl.setDescriptiveName("The link point to 2 pallets with a total comtent of 10000 brochures in 200 boxes");
		final JDFComponent compBrochure = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Input, null, null, null, null);
		compBrochure.setResStatus(EnumResStatus.Available, true);
		compBrochure.setDescriptiveName("The individual Brochures");
		final JDFResourceLink rlB = n.getLink(compBrochure, null);
		rlB.setAmount(10000, null);
		rlB.setActualAmount(9700, null);

		for (int i = 0; i < 2; i++)
		{
			// create a pallet partition - may also use the root
			final JDFComponent compPallet = (JDFComponent) comp.addPartition(EnumPartIDKey.DeliveryUnit0, "Pallet" + i);
			compPallet.setProductID("Pallet_" + i);

			final JDFBundle bundlePallet = compPallet.getCreateBundle();
			final int nBox = i == 0 ? 100 : 94;
			bundlePallet.setDescriptiveName("Pallet Bundle describing " + nBox + " boxes with " + nBox * 50 + " Brochures Contents total");
			bundlePallet.setTotalAmount(nBox * 50);
			bundlePallet.setBundleType(EnumBundleType.Pallet);

			for (int j = 0; j < nBox; j++)
			{
				final JDFBundleItem biBoxes = bundlePallet.appendBundleItem();
				final JDFComponent compBox = (JDFComponent) compPallet.addPartition(EnumPartIDKey.DeliveryUnit1, "Box_" + i + "_" + j);
				compBox.setProductID("Box_" + i + "_" + j);
				biBoxes.refElement(compBox);
				biBoxes.setAmount(1);

				final JDFBundle bundleBox = compBox.appendBundle();
				bundleBox.setDescriptiveName("Bundle describing box #" + j + " with 50 Brochures Contents per box");
				bundleBox.setTotalAmount(50);
				bundleBox.setBundleType(EnumBundleType.Box);

				final JDFBundleItem biBrochures = bundleBox.appendBundleItem();
				biBrochures.refElement(compBrochure);
				biBrochures.setAmount(50);
			}
		}

		d.write2File(sm_dirTestDataTemp + File.separator + "MISFinAmountCompleteManifest.jdf", 2, false);
	}

	// ///////////////////////////////////////////////////////////////////////
}
