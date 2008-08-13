/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumWorkType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoResourceAudit.EnumReason;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.StatusCounter;

public class RIPTest extends JDFTestCaseBase
{
	private JDFNode n;
	private JDFDoc d;
	private JDFMedia inMedia;
	private JDFExposedMedia outXM;
	private JDFResourceLink rlMediaIn;
	private JDFResourceLink rlXMOut;
	private StatusCounter statCounter;
	private VElement vRL;
	private VString vsSheet;
	private VString vsCMYK;

	// /////////////////////////////////////////////////////////////////
	public void testReprintJMF() throws Exception
	{
		testAuditsImageSetting();
		statCounter.setActiveNode(null, null, null);
		String sheet = vsSheet.stringAt(1);
		VJDFAttributeMap vmP = new VJDFAttributeMap();
		final JDFAttributeMap attributeMap = new JDFAttributeMap(
				EnumPartIDKey.SheetName, sheet);
		attributeMap.put("SignatureName", "Sig1");
		attributeMap.put("Separation", vsCMYK.stringAt(3));
		attributeMap.put("Side", "Front");

		vmP.add(attributeMap);
		statCounter.setActiveNode(n, vmP, vRL);
		String refXM = rlXMOut.getrRef();
		String refMedia = rlMediaIn.getrRef();

		statCounter.setTrackWaste(refXM, true);
		statCounter.setTrackWaste(refMedia, false);

		statCounter.setActiveNode(n, vmP, vRL);
		statCounter.setWorkType(EnumWorkType.Rework);
		statCounter.setPhase(EnumNodeStatus.InProgress, "Imaging",
				EnumDeviceStatus.Running, null);
		statCounter.addPhase(refMedia, 1, 0);
		statCounter.addPhase(refXM, 1, 0);
		statCounter.setPhase(EnumNodeStatus.InProgress, "Imaging",
				EnumDeviceStatus.Running, null);
		JDFDoc d2 = statCounter.getDocJMFResource();
		JDFJMF jmf = d2.getJMFRoot();
		jmf.convertResponses(null);
		VElement vSigs = jmf.getMessageVector(JDFMessage.EnumFamily.Signal,
				JDFMessage.EnumType.Resource);

		d2.write2File(sm_dirTestDataTemp + File.separator
				+ "ImageSetResourceReprint_.jmf", 2, false);
		JDFDoc dStatusJMF = statCounter.getDocJMFPhaseTime();
		jmf = dStatusJMF.getJMFRoot();
		jmf.convertResponses(null);
		for (int i = 0; i < vSigs.size(); i++)
			jmf.copyElement(vSigs.item(i), null);
		dStatusJMF.write2File(sm_dirTestDataTemp + File.separator
				+ "ImageSetReprint_.jmf", 2, false);

		// JDFResourceAudit ra=
		statCounter.setResourceAudit(refMedia, EnumReason.ProcessResult);
		// JDFProcessRun pr=
		statCounter.setProcessResult(EnumNodeStatus.Completed);

		d.write2File(sm_dirTestDataTemp + File.separator
				+ "ImageSetAmount_Reprint.jdf", 2, false);
	}

	/**
	 * @return
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		JDFElement.setLongID(false);
		d = new JDFDoc("JDF");

		n = d.getJDFRoot();
		n
				.appendXMLComment(
						"Example to illustrate JDF 1.3 Base and MIS compatible amount handling",
						null);
		n.setType(EnumType.Combined);
		inMedia = (JDFMedia) n.addResource(ElementName.MEDIA, null,
				EnumUsage.Input, null, null, null, null);
		outXM = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA,
				EnumUsage.Output);
		outXM.refMedia(inMedia);
		n.addTypes(EnumType.Interpreting);
		n.addTypes(EnumType.Rendering);
		n.addTypes(EnumType.ImageSetting);
		n.setJobID("RIP-job");
		rlXMOut = n.getLink(outXM, null);
		rlMediaIn = n.getLink(inMedia, null);
		inMedia.setProductID("Media-ProductID");
		inMedia.setMediaType(EnumMediaType.Plate);
		inMedia.setMediaTypeDetails("Aluminum");

		vRL = new VElement();
		vRL.add(rlMediaIn);
		vRL.add(rlXMOut);

		JDFExposedMedia xmPart = (JDFExposedMedia) outXM.addPartition(
				EnumPartIDKey.SignatureName, "Sig1");
		vsSheet = new VString("Cover Sheet1 Sheet2", " ");
		vsCMYK = new VString("Cyan Magenta Yellow Black Spot1", " ");
		VElement v = xmPart.addPartitions(EnumPartIDKey.SheetName, vsSheet);
		for (int i = 0; i < v.size(); i++)
		{
			JDFResource xmPart2 = (JDFResource) v.elementAt(i);
			xmPart2.getCreatePartition(EnumPartIDKey.Side, "Front", null)
					.addPartitions(EnumPartIDKey.Separation, vsCMYK);
			// xmPart2.getCreatePartition(EnumPartIDKey.Side,"Back",null).
			// addPartitions(EnumPartIDKey.Separation, vsCMYK);
		}
		statCounter = new StatusCounter(n, null, vRL);
		statCounter.setDeviceID("Rip-DeviceID");
		statCounter.setCopyResInResInfo(rlMediaIn.getrRef(), true);

	}

	public void testAuditsImageSetting() throws Exception
	{

		for (int i = 0; i < vsSheet.size(); i++)
		{
			String sheet = vsSheet.stringAt(i);
			VJDFAttributeMap vmP = new VJDFAttributeMap();
			final JDFAttributeMap attributeMap = new JDFAttributeMap(
					EnumPartIDKey.SheetName, sheet);
			attributeMap.put("SignatureName", "Sig1");

			vmP.add(attributeMap);
			statCounter.setActiveNode(n, vmP, vRL);
			String refXM = rlXMOut.getrRef();
			String refMedia = rlMediaIn.getrRef();

			statCounter.setTrackWaste(refXM, true);
			statCounter.setTrackWaste(refMedia, false);

			statCounter.setPhase(EnumNodeStatus.Stopped, "PowerOn",
					EnumDeviceStatus.Stopped, "PowerOn");

			statCounter.setPhase(EnumNodeStatus.InProgress, "Imaging",
					EnumDeviceStatus.Running, null);
			statCounter.addPhase(refMedia, 5, 0);
			statCounter.addPhase(refXM, 5, 0);
			statCounter.setPhase(EnumNodeStatus.InProgress, "Imaging",
					EnumDeviceStatus.Running, null);

			// JDFResourceAudit ra=
			statCounter.setResourceAudit(refMedia, EnumReason.ProcessResult);

			JDFProcessRun pr = statCounter
					.setProcessResult(EnumNodeStatus.Completed);
			pr
					.setDescriptiveName("we even have the utterly useless ProcessRun");
		}
		d.write2File(sm_dirTestDataTemp + File.separator
				+ "ImageSetAmount_.jdf", 2, false);
		JDFDoc d2 = statCounter.getDocJMFResource();
		JDFJMF jmf = d2.getJMFRoot();
		jmf.convertResponses(null);
		JDFSignal sig = jmf
				.appendSignal(org.cip4.jdflib.jmf.JDFMessage.EnumType.Notification);
		JDFNotification not = sig.appendNotification();
		not.setXPathAttribute("MileStone/@MileStoneType", "PrepressCompleted");
		not.setXPathAttribute("MileStone/@Amount", "5");
		d2.write2File(sm_dirTestDataTemp + File.separator
				+ "ImageSetAmount_.jmf", 2, false);
		JDFDoc dStatusJMF = statCounter.getDocJMFPhaseTime();
		jmf = dStatusJMF.getJMFRoot();
		jmf.convertResponses(null);
		dStatusJMF.write2File(sm_dirTestDataTemp + File.separator
				+ "ImageSetPhaseTime_.jmf", 2, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	protected void tearDown() throws Exception
	{
		// TODO Auto-generated method stub
		super.tearDown();
		statCounter.setActiveNode(null, null, null);
		statCounter.setWorkType(null);
	}

	// /////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////

}
