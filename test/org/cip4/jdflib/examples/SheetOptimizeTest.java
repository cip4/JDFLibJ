/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFConvertingConfig;
import org.cip4.jdflib.resource.process.JDFRepeatDesc;
import org.cip4.jdflib.resource.process.JDFRunList;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 28.11.2008
 */
public class SheetOptimizeTest extends JDFTestCaseBase
{
	/**
	 * test sheetoptimization
	 * 
	 * @throws Exception x
	 */
	public void testDescribeSheetOptimize() throws Exception
	{
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setXMLComment("");
		n.setType("SheetOptimizing", false);
		final JDFRunList rl1 = (JDFRunList) n.addResource("RunList", EnumUsage.Input);
		rl1.addPDF("file1.pdf", 0, -1);
		final JDFRunList rl2 = (JDFRunList) n.addResource("RunList", EnumUsage.Input);
		rl2.addPDF("file2.pdf", 0, -1);

		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Output);
		sp.setResStatus(EnumResStatus.Unavailable, false);
		final JDFResource sop = n.addResource("SheetOptimizingParams", EnumUsage.Input);
		sop.setXMLComment("Similar to DieLayoutProductionParams");
		final JDFConvertingConfig cc = (JDFConvertingConfig) sop.appendElement(ElementName.CONVERTINGCONFIG, null);
		cc.setSheetHeight(new JDFNumberRange(600, 800));
		cc.setSheetWidth(new JDFNumberRange(900, 1100));
		cc.appendDevice().setDeviceID("SM102-a");
		cc.setMarginBottom(36);
		cc.setMarginTop(36);
		cc.setMarginLeft(36);
		cc.setMarginRight(36);

		final JDFRepeatDesc rd1 = (JDFRepeatDesc) sop.appendElement(ElementName.REPEATDESC, null);
		rd1.refElement(rl1);
		rd1.setOrderQuantity(5000);
		final JDFBinderySignature bs1 = (JDFBinderySignature) rd1.appendElement(ElementName.BINDERYSIGNATURE);
		bs1.setFoldCatalog("F8-2");

		final JDFRepeatDesc rd2 = (JDFRepeatDesc) sop.appendElement(ElementName.REPEATDESC, null);
		rd2.refElement(rl2);
		rd2.setOrderQuantity(15000);
		final JDFBinderySignature bs2 = (JDFBinderySignature) rd2.appendElement(ElementName.BINDERYSIGNATURE);
		bs2.setFoldCatalog("F16-2");

		d.write2File(sm_dirTestDataTemp + "sheetOptimize.jdf", 2, true);
	}
}
