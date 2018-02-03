/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoDigitalPrintingParams.EnumSides;
import org.cip4.jdflib.auto.JDFAutoLayoutElement;
import org.cip4.jdflib.auto.JDFAutoResourceAudit.EnumReason;
import org.cip4.jdflib.auto.JDFAutoStitchingParams.EnumStitchType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFPipeParams;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFModulePhase;
import org.cip4.jdflib.resource.JDFModuleStatus;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.devicecapability.JDFDevCap;
import org.cip4.jdflib.resource.devicecapability.JDFDevCaps;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.devicecapability.JDFNameState;
import org.cip4.jdflib.resource.process.JDFApprovalSuccess;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContentData;
import org.cip4.jdflib.resource.process.JDFContentList;
import org.cip4.jdflib.resource.process.JDFDigitalPrintingParams;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFMiscConsumable;
import org.cip4.jdflib.resource.process.JDFPageData;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFUsageCounter;
import org.cip4.jdflib.resource.process.postpress.JDFStitchingParams;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StatusCounter;
import org.cip4.jdflib.util.StatusUtil;
import org.cip4.jdflib.util.StatusUtil.AmountBag;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class DigiPrintTest extends JDFTestCaseBase
{
	private JDFDoc doc;
	private JDFNode n;
	private JDFComponent comp;
	private JDFRunList ruli;
	private JDFResourceLink rlComp;
	private JDFDigitalPrintingParams digiParams;
	private JDFMedia med;
	private JDFResourceLink rlMedia;

	/**
	 * test amount handling
	 * @throws Exception
	 *
	 */
	@Test
	public void testModules() throws Exception
	{
		final JDFAuditPool ap = n.getCreateAuditPool();
		ap.appendXMLComment("JDF 1.3 compatible auditing of module phases - note that modulephase start and end times are set outside of the phasetime start and end times", null);
		final JDFPhaseTime pt = ap.addPhaseTime(EnumNodeStatus.Setup, null, null);
		final JDFPhaseTime pt2 = ap.addPhaseTime(EnumNodeStatus.InProgress, null, null);
		final JDFDate date = new JDFDate();
		final JDFModulePhase mpRIP = pt.appendModulePhase();
		final JDFModulePhase mpRIP2 = pt2.appendModulePhase();
		mpRIP.setStatus(EnumNodeStatus.InProgress);
		mpRIP2.setStatus(EnumNodeStatus.InProgress);
		mpRIP2.setDescriptiveName("This ModulePhase is actually the same as the initial ModulePhase in the setup PhaseTime");
		mpRIP.setModuleType("Imaging");
		mpRIP2.setModuleType("Imaging");
		pt.setStart(date);
		mpRIP.setStart(date);
		mpRIP2.setStart(date);
		date.addOffset(0, 5, 0, 0);
		pt.setEnd(date);

		final JDFModulePhase mpPrint = pt2.appendModulePhase();
		mpPrint.setStatus(EnumNodeStatus.InProgress);
		pt2.setStart(date);
		mpPrint.setStart(date);
		date.addOffset(0, 30, 0, 0);
		mpRIP.setEnd(date);
		mpRIP2.setEnd(date);

		date.addOffset(0, 70, 0, 0);
		pt2.setEnd(date);
		mpPrint.setEnd(date);
		mpPrint.setModuleType("Printer");
		doc.write2File(sm_dirTestDataTemp + "DigiPrintModule1.jdf", 2, false);
	}

	/**
	 * test amount handling
	 * @throws Exception
	 *
	 */
	@Test
	public void testPDFVTStream() throws Exception
	{
		for (int strm = 0; strm < 3; strm++)
		{
			doc = new JDFDoc(ElementName.JDF);
			n = doc.getJDFRoot();
			n.setType(EnumType.Combined);
			n.setTypes(new VString("Interpreting Reendering DigitalPrinting", null));

			final JDFFileSpec fsContainer = (JDFFileSpec) n.addResource("FileSpec", null);
			fsContainer.setURL("http://fileServer/getFile");
			fsContainer.setMimeType("multipart/related");
			fsContainer.setMimeTypeVersion("application/pdf-vt-stream");
			final JDFRunList rl = (JDFRunList) n.addResource("RunList", EnumUsage.Input);
			if (strm == 0)
			{
				for (int i = 0; i < 3; i++)
				{
					final JDFRunList rlPart = rl.addPDF("foo" + i + ".pdf", 0, -1);
					final JDFLayoutElement layoutElement = rlPart.getLayoutElement();
					layoutElement.setElementType(JDFAutoLayoutElement.EnumElementType.MultiSet);
					final JDFFileSpec f2 = layoutElement.getFileSpec();
					f2.appendContainer().refElement(fsContainer);

				}
			}
			else
			{
				final JDFLayoutElement layoutElement = rl.appendLayoutElement();
				layoutElement.setElementType(JDFAutoLayoutElement.EnumElementType.MultiSet);
				if (strm == 1)
				{
					final JDFFileSpec fs = layoutElement.appendFileSpec();
					fs.setMimeType(JDFConstants.MIME_PDF);
					fs.setFileFormat("cid:streamFile%i.pdf");
					fs.setFileTemplate("i");
					fs.appendContainer().refElement(fsContainer);
				}
				else
				{
					// TODO discuss containers
					final JDFFileSpec fs = layoutElement.appendFileSpec();
					fs.setMimeType("PDF/VT");
					fs.setFileFormat("cid:streamFile%i.pdf");
					fs.setFileTemplate("i");
					fs.appendContainer().refElement(fsContainer);
				}
			}
			writeTest(doc, "PDFVTStream.jdf" + strm + ".jdf");
		}
	}

	/**
	 *
	 */
	@Test
	public void testHoldPipeRIP()
	{
		final JDFDoc jdfDoc = new JDFDoc("JDF");
		final JDFNode n = jdfDoc.getJDFRoot();
		n.setCombined(new VString("LayoutPreparation Interpreting Rendering DigitalPrinting", null));
		final JDFRunList rl0 = (JDFRunList) n.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		final JDFResourceLink rll0 = n.getLink(rl0, EnumUsage.Input);
		rll0.setCombinedProcessIndex(0);
		final JDFRunList ruLi = (JDFRunList) n.addResource(ElementName.RUNLIST, EnumUsage.Output);
		ruLi.setResStatus(EnumResStatus.Unavailable, true);
		ruLi.setPipeProtocol("Internal");
		ruLi.setDescriptiveName("Internal RunList for Hold");
		final JDFResourceLink rll = n.getLink(ruLi, EnumUsage.Output);
		final int pos = n.getTypes().index("Rendering");
		rll.setCombinedProcessIndex(pos);
		final JDFResourceLink rll2 = n.ensureLink(ruLi, EnumUsage.Input, null);
		rll2.setCombinedProcessIndex(pos + 1);
		rll2.setMinStatus(EnumResStatus.Available);
		writeTest(jdfDoc, "HoldRIP.jdf");
		rll2.setMinStatus(EnumResStatus.Unavailable);
		rll2.setPipeResume(5);
		writeTest(jdfDoc, "PipeRIP.jdf");
	}

	/**
	 *
	 */
	@Test
	public void testHoldQueueRIP()
	{
		final JDFDoc jdfDoc = new JDFDoc("JDF");
		final JDFNode n = jdfDoc.getJDFRoot();
		n.setCombined(new VString("LayoutPreparation Interpreting Rendering DigitalPrinting", null));
		final JDFRunList rl0 = (JDFRunList) n.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		final JDFResourceLink rll0 = n.getLink(rl0, EnumUsage.Input);
		rll0.setCombinedProcessIndex(0);
		final JDFApprovalSuccess apSuc = (JDFApprovalSuccess) n.addResource(ElementName.APPROVALSUCCESS, EnumUsage.Input);
		apSuc.setResStatus(EnumResStatus.Unavailable, true);
		apSuc.setDescriptiveName("This can be set Available either by a JMF Resource Command or from the printer UI");
		final JDFResourceLink rll = n.getLink(apSuc, EnumUsage.Input);
		rll.setCombinedProcessIndex(0);
		rll.setMinStatus(EnumResStatus.Unavailable);
		final int pos = n.getTypes().index("DigitalPrinting");
		final JDFResourceLink rll2 = n.ensureLink(apSuc, EnumUsage.Input, null);
		rll2.setCombinedProcessIndex(pos);
		rll2.setMinStatus(EnumResStatus.Available);
		writeTest(jdfDoc, "HoldQRIP.jdf");
	}

	/**
	 *
	 *
	 */
	public void testPipePushSheet()
	{
		final JDFJMF jmf = new JDFDoc("JMF").getJMFRoot();
		jmf.setSenderID("DFE");
		final VString frontback = new VString("Front Back", null);
		final VString coverBody = new VString("Cover Body", null);
		final VString cmyk = new VString("Cyan Magenta Yellow Black ", null);
		for (int j = 0; j < 2; j++)
		{
			for (final String cb : coverBody)
			{
				for (int i = 0; i < 3 + j; i++)
				{
					if (i > 0 && cb.equals("Cover"))
						break;
					for (final String fb : frontback)
					{
						for (final String sep : cmyk)
						{
							final JDFCommand command = jmf.appendCommand();
							command.setXMLComment("The " + (i + 1) + " push: " + (3 + j) + " body sheets");
							command.setType(JDFMessage.EnumType.PipePush);
							command.setSenderID("RIP");
							final JDFPipeParams pp = createPipeParams(command);
							final JDFAttributeMap m = createRunListPartition(j, cb, i, fb, sep, pp);
							final JDFAttributeMap m2 = m.clone().removeKeys(new VString("DocTags SheetIndex Side Separation", null));
							final JDFAmountPool ap = (JDFAmountPool) pp.getCreateElement(ElementName.AMOUNTPOOL);
							final JDFPartAmount pa2 = ap.getCreatePartAmount(m2);
							pa2.setActualAmount(2 * 4 * (4 + j), null);
							final JDFPartAmount pa = ap.getCreatePartAmount(m);
							pa.setActualAmount(1, null);
						}
					}
				}
			}
		}
		writeTest(jmf, "RIPPipePushSheetMeta.jmf", true, null);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testModulesUpdate() throws Exception
	{
		final JDFAuditPool ap = n.getCreateAuditPool();
		ap.appendXMLComment("JDF 1.3 compatible auditing of module phases with updates", null);
		final JDFPhaseTime pt = ap.addPhaseTime(EnumNodeStatus.Setup, null, null);
		final JDFPhaseTime pt2 = ap.addPhaseTime(EnumNodeStatus.InProgress, null, null);
		final JDFPhaseTime pt3 = ap.addPhaseTime(EnumNodeStatus.InProgress, null, null);
		final JDFDate date = new JDFDate();
		final JDFModulePhase mpRIP = pt.appendModulePhase();
		mpRIP.setModuleType("Imaging");
		final JDFModulePhase mpJob = pt.appendModulePhase();
		mpJob.setModuleType("Manual");
		mpJob.setStatus(EnumNodeStatus.InProgress);
		final JDFModulePhase mpPrint = pt.appendModulePhase();
		mpPrint.setModuleType("Printing");

		mpRIP.setStatus(EnumNodeStatus.InProgress);
		pt.setStart(date);
		mpRIP.setStart(date);
		date.addOffset(0, 5, 0, 0);
		pt.setEnd(date);

		pt2.copyElement(mpRIP, null);
		pt2.copyElement(mpJob, null);
		pt2.copyElement(mpPrint, null);
		mpPrint.setStatus(EnumNodeStatus.InProgress);
		pt2.setStart(date);
		mpPrint.setStart(date);
		date.addOffset(0, 30, 0, 0);
		mpRIP.setEnd(date);

		date.addOffset(0, 70, 0, 0);
		pt2.setEnd(date);
		mpPrint.setEnd(date);
		pt3.copyElement(mpRIP, null);
		pt3.copyElement(mpJob, null);
		pt3.copyElement(mpPrint, null);

		writeTest(doc, "DigiPrintModuleUpdate.jdf");
	}

	/**
	 * test amount handling
	 * @throws Exception
	 *
	 */
	@Test
	public void testModules14() throws Exception
	{
		final VString v = new VString("orig fullList end", null);
		for (int i = 0; i < v.size(); i++)
		{
			setUp();
			final String testType = v.get(i);
			final JDFAuditPool ap = n.getCreateAuditPool();
			ap.appendXMLComment("JDF 1.3 incompatible auditing of module phases the REQUIRED time attributes are not set in the ModulePhase elements\n"
					+ "- note that phases may now arbitrarily overlap\n"
					+ "The modulePhase elements now only specify which modules are involved, times are all defined by the phasetime proper", null);
			ap.appendXMLComment("The following phaseTime is executed by one module - the RIP,which executes two process steps (Interpreting and Rendering)", null);
			final JDFPhaseTime ptRIP = ap.addPhaseTime(EnumNodeStatus.Setup, null, null);
			final JDFDate date = new JDFDate();
			ptRIP.setStart(date);

			final JDFDoc jmfDoc = new JDFDoc("JMF");
			final JDFJMF jmf = jmfDoc.getJMFRoot();
			jmf.setDescriptiveName("Initial phase when the RIP starts up");
			JDFSignal signal = jmf.appendSignal(JDFMessage.EnumType.Status);
			JDFDeviceInfo di = signal.appendDeviceInfo();

			final JDFJobPhase jpRIP = di.appendJobPhase();
			di.setDeviceStatus(EnumDeviceStatus.Setup);
			jpRIP.setStartTime(date);
			jpRIP.setStatus(EnumNodeStatus.Setup);
			jpRIP.setJobID(n.getJobID(true));
			jpRIP.setJobPartID(n.getJobPartID(true));

			final JDFModuleStatus msRIP = jpRIP.appendModuleStatus();
			msRIP.setCombinedProcessIndex(new JDFIntegerList("0 1"));
			msRIP.setModuleType("Imaging");
			msRIP.setModuleID("ID_Imaging");

			final JDFModulePhase mpRIP = ptRIP.appendModulePhase();
			mpRIP.setCombinedProcessIndex(new JDFIntegerList("0 1"));
			mpRIP.setModuleType("Imaging");
			mpRIP.setModuleID("ID_Imaging");

			JDFModuleStatus msPrint = di.appendModuleStatus();
			msPrint.setCombinedProcessIndex(new JDFIntegerList("2"));
			msPrint.setModuleType("Printer");
			msPrint.setModuleID("ID_Printer");

			JDFModuleStatus msStitch = di.appendModuleStatus();
			msStitch.setCombinedProcessIndex(new JDFIntegerList("3"));
			msStitch.setModuleType("Stitcher");
			msStitch.setModuleID("ID_Stitcher");

			jmfDoc.write2File(sm_dirTestDataTemp + "moduleStatus" + testType + "0.jmf", 2, false);
			date.addOffset(0, 5, 0, 0);
			jmf.setTimeStamp(date);

			final JDFJobPhase jpPrint = di.appendJobPhase();
			di.setDeviceStatus(EnumDeviceStatus.Running);
			jpPrint.setStatus(EnumNodeStatus.InProgress);
			jpPrint.setStartTime(date);
			jpPrint.setJobID(n.getJobID(true));
			jpPrint.setJobPartID(n.getJobPartID(true));

			msPrint = jpPrint.appendModuleStatus();
			msPrint.setCombinedProcessIndex(new JDFIntegerList("2"));
			msPrint.setModuleType("Printer");
			msPrint.setModuleID("ID_Printer");

			msStitch = jpPrint.appendModuleStatus();
			msStitch.setCombinedProcessIndex(new JDFIntegerList("3"));
			msStitch.setModuleType("Stitcher");
			msStitch.setModuleID("ID_Stitcher");

			di.removeChildren(ElementName.MODULESTATUS, null, null);
			jmf.setDescriptiveName("Phase when the Printer and Finisher start up; RIP is still RIPping");
			jmfDoc.write2File(sm_dirTestDataTemp + "moduleStatus" + testType + "1.jmf", 2, false);

			ap.appendXMLComment("The following phaseTime is executed by two modules - sticher and printer", null);
			final JDFPhaseTime ptPrint = ap.addPhaseTime(EnumNodeStatus.InProgress, null, null);
			final JDFModulePhase mpPrint = ptPrint.appendModulePhase();
			mpPrint.setCombinedProcessIndex(new JDFIntegerList("2"));
			mpPrint.setModuleType("Printer");
			mpPrint.setModuleID("ID_Printer");
			ptPrint.setStart(date);

			final JDFModulePhase mpStitch = ptPrint.appendModulePhase();
			mpStitch.setCombinedProcessIndex(new JDFIntegerList("3"));
			mpStitch.setModuleType("Stitcher");
			mpStitch.setModuleID("ID_Stitcher");
			date.addOffset(0, 30, 0, 0);
			ptRIP.setEnd(date);

			JDFDeviceInfo di2 = null;
			if (i < 2)
			{
				final JDFSignal signal2 = jmf.appendSignal(JDFMessage.EnumType.Status);
				di2 = (JDFDeviceInfo) signal2.copyElement(di, null);
				di2.removeChild(ElementName.JOBPHASE, null, 0);
				if (i == 1)
				{
					final JDFModuleStatus directMSRip = (JDFModuleStatus) di2.copyElement(msRIP, null);
					directMSRip.setDeviceStatus(EnumDeviceStatus.Idle);
				}
			}
			else
			{
				jpRIP.setAttribute("EndTime", date.getDateTimeISO());
				jpRIP.setDescriptiveName("Added EndTime to explicitly close phase");
			}
			jmf.setTimeStamp(date);
			jmf.setDescriptiveName("Phase when the RIP has completed, Printer and Finisher are still RIPping");
			jmfDoc.write2File(sm_dirTestDataTemp + "moduleStatus" + testType + "2.jmf", 2, false);

			date.addOffset(0, 70, 0, 0);
			ptPrint.setEnd(date);
			jmf.setTimeStamp(date);

			if (i < 2)
			{
				signal.deleteNode();
				signal = jmf.appendSignal(JDFMessage.EnumType.Status);
				di = (JDFDeviceInfo) signal.copyElement(di2, null);
				di.removeChild(ElementName.JOBPHASE, null, 0);
				di.removeChild(ElementName.MODULESTATUS, null, 0);
				di.setDeviceStatus(EnumDeviceStatus.Idle);
				signal.appendXMLComment("Or should the complete list of modules also be specified here?", di);
			}
			else
			{
				jpRIP.deleteNode();
				jpPrint.setAttribute("EndTime", date.getDateTimeISO());
				jpPrint.setDescriptiveName("Added EndTime to explicitly close phase");
			}
			jmf.setDescriptiveName("Phase when the Printer and Finisher have completed");
			jmfDoc.write2File(sm_dirTestDataTemp + "moduleStatus" + testType + "3.jmf", 2, false);
			writeTest(doc, "DigiPrintModule.1.4" + testType + ".jdf");
		}
	}

	/**
	 * test devcaps for usagecounters
	 * @throws Exception
	 *
	 */
	@Test
	public void testUsageCounterDevCaps() throws Exception
	{
		final JDFDoc duc = new JDFDoc("DeviceCap");
		final JDFDeviceCap devicecap = (JDFDeviceCap) duc.getRoot();
		final JDFDevCaps dcs = devicecap.appendDevCaps();
		dcs.setName(ElementName.USAGECOUNTER);
		final JDFDevCap dc = dcs.appendDevCapInPool();
		dc.setMinOccurs(3);
		dc.setMaxOccurs(3);
		final JDFNameState counterID = dc.appendNameState(AttributeName.COUNTERID);
		counterID.setAllowedValueList(new VString("ID_Black ID_Color ID_Total", null));
		counterID.setListType(EnumListType.SingleValue);
		duc.write2File(sm_dirTestDataTemp + "DevCapUsageCounter.jdf", 2, false);
	}

	/**
	 * test amount handling
	 *
	 */
	@Test
	public void testDirectProof()
	{
		n.setXMLComment("Example workflow with initioal warmup phase, one direct proof and 100 copies of 10 sheets each.\n"
				+ "The direct proof is acceptable and included in the good output");
		digiParams.setDirectProofAmount(1);
		digiParams.setXMLComment("1 initial proof is requested");
		rlComp.setAmount(100, null);
		final JDFAuditPool ap = n.getAuditPool();

		final VElement vRL = new VElement();
		vRL.add(rlComp);
		vRL.add(rlMedia);

		final StatusCounter stCounter = new StatusCounter(n, null, vRL);
		stCounter.setDeviceID("MyDevice");
		final String mediaRef = rlMedia.getrRef();
		stCounter.setTrackWaste(mediaRef, true);
		final String compRef = rlComp.getrRef();
		stCounter.setTrackWaste(compRef, false);

		doc.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmount_initial.jdf", 2, false);

		stCounter.setPhase(EnumNodeStatus.InProgress, "Waste", EnumDeviceStatus.Running, null);
		ap.getLastPhase(null, null).setXMLComment("Phase where warm up waste is produced");
		stCounter.addPhase(mediaRef, 0, 2, true);
		stCounter.addPhase(compRef, 0, 20, true);

		stCounter.setPhase(EnumNodeStatus.InProgress, "Waste", EnumDeviceStatus.Running, null);

		stCounter.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);
		stCounter.addPhase(mediaRef, 1, 0, true);
		stCounter.addPhase(compRef, 10, 0, true);
		stCounter.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);
		ap.getLastPhase(null, null).setXMLComment("Phase where 1 proof is produced");

		stCounter.setPhase(EnumNodeStatus.Stopped, "WaitForApproval", EnumDeviceStatus.Stopped, null);
		ap.getLastPhase(null, null).setXMLComment("Phase where the proof is evaluated while the device is stopped");
		stCounter.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);

		stCounter.addPhase(mediaRef, 99, 0, true);
		stCounter.addPhase(compRef, 990, 0, true);
		stCounter.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null);
		ap.getLastPhase(null, null).setXMLComment("Phase where the 100 copies are produced");

		stCounter.setPhase(EnumNodeStatus.Completed, "Idle", EnumDeviceStatus.Idle, null);
		stCounter.setResourceAudit(mediaRef, EnumReason.ProcessResult);
		doc.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintProof_final.jdf", 2, false);

	}

	/**
	 * test amount handling
	 * @throws Exception
	 *
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAmount() throws Exception
	{
		rlComp.setAmount(20, null);
		rlComp.setDescriptiveName("The link points to 20 planned and 20 good + 2 Waste brochures");

		final JDFMiscConsumable mc = (JDFMiscConsumable) n.appendMatchingResource(ElementName.MISCCONSUMABLE, EnumProcessUsage.AnyInput, null);
		mc.setResStatus(EnumResStatus.Available, false);
		mc.setConsumableType("FooBar");
		mc.setUnit("Fnarfs");
		mc.setDescriptiveName("FooBars are always measured in Fnarfs");
		final JDFResourceLink rlmc = n.getLink(mc, null);
		rlmc.setAmount(42, null);
		rlmc.setDescriptiveName("The link points to 42 actual FooBars");

		final JDFUsageCounter uc = (JDFUsageCounter) n.appendMatchingResource(ElementName.USAGECOUNTER, EnumProcessUsage.AnyInput, null);
		uc.setResStatus(EnumResStatus.Available, false);
		uc.setCounterTypes(new VString("Click", " "));
		final JDFResourceLink rlu = n.getLink(uc, null);
		rlu.setAmount(200, null);
		rlu.setDescriptiveName("The link points to 200 actual clicks");

		rlMedia.setAmount(100, null);
		rlMedia.setDescriptiveName("The link points to 100 actual sheets");

		Thread.sleep(1000);
		comp.setResStatus(EnumResStatus.Available, true);

		final VElement vRL = new VElement();
		vRL.add(rlComp);
		vRL.add(rlu);
		vRL.add(rlMedia);
		vRL.add(rlmc);
		final StatusUtil stUtil = new StatusUtil(n, null, vRL);
		stUtil.setDeviceID("MyDevice");
		stUtil.setTrackWaste(rlMedia, true);
		stUtil.setTrackWaste(rlComp, true);
		stUtil.setCopyResInResInfo(rlu, true);

		doc.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmount_initial.jdf", 2, false);

		final AmountBag[] bags = new AmountBag[vRL.size()];
		bags[0] = stUtil.new AmountBag(rlComp.getrRef());
		bags[1] = stUtil.new AmountBag(rlu.getrRef());
		bags[2] = stUtil.new AmountBag(rlMedia.getrRef());
		bags[3] = stUtil.new AmountBag(rlmc.getrRef());
		stUtil.setPhase(EnumNodeStatus.InProgress, "Waste", EnumDeviceStatus.Running, null, bags);
		JDFDoc docStatusJMF = stUtil.getDocJMFPhaseTime();
		docStatusJMF.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmountStatus0.jmf", 2, false);
		JDFDoc docResJMF = stUtil.getDocJMFResource();
		docResJMF.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmountResource0.jmf", 2, false);

		bags[0].addPhase(0, 2, true);
		bags[1].addPhase(0, 20, true);
		bags[2].addPhase(0, 10, true);
		bags[3].addPhase(0, 0, true);
		stUtil.setPhase(EnumNodeStatus.InProgress, "Waste", EnumDeviceStatus.Running, null, bags);
		docStatusJMF = stUtil.getDocJMFPhaseTime();
		docStatusJMF.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmountStatus1.jmf", 2, false);
		docResJMF = stUtil.getDocJMFResource();
		docResJMF.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmountResource1.jmf", 2, false);

		bags[0].addPhase(15, 0, true);
		bags[1].addPhase(150, 0, true);
		bags[2].addPhase(75, 0, true);
		bags[3].addPhase(32, 0, true);
		stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null, bags);
		docStatusJMF = stUtil.getDocJMFPhaseTime();
		docStatusJMF.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmountStatus2.jmf", 2, false);
		docResJMF = stUtil.getDocJMFResource();
		docResJMF.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmountResource2.jmf", 2, false);

		bags[0].addPhase(5, 0, false);
		bags[1].addPhase(50, 0, false);
		bags[2].addPhase(25, 0, false);
		bags[3].addPhase(11, 0, false);
		stUtil.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, null, bags);
		docStatusJMF = stUtil.getDocJMFPhaseTime();
		docStatusJMF.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmountStatus3.jmf", 2, false);
		docResJMF = stUtil.getDocJMFResource();
		docResJMF.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmountResource3.jmf", 2, false);

		bags[0].addPhase(0, 0, true);
		bags[1].addPhase(0, 0, true);
		bags[2].addPhase(0, 0, true);
		bags[3].addPhase(0, 0, true);
		stUtil.setPhase(EnumNodeStatus.Completed, "Idle", EnumDeviceStatus.Idle, null, bags);
		docStatusJMF = stUtil.getDocJMFPhaseTime();
		docStatusJMF.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmountStatus4.jmf", 2, false);
		docResJMF = stUtil.getDocJMFResource();
		docResJMF.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmountResource4.jmf", 2, false);

		doc.write2File(sm_dirTestDataTemp + File.separator + "DigiPrintAmount_final.jdf", 2, false);
	}

	/**
	 * @throws Exception
	 */
	/**
	 * @throws Exception
	 */
	@Test
	public void testContentDataRunList() throws Exception
	{
		final JDFContentList cl = (JDFContentList) n.addResource(ElementName.CONTENTLIST, null);
		final JDFContentData cover = cl.appendContentData();
		final JDFContentData insert = cl.appendContentData();
		final String idCover = cover.appendAnchor(null);
		final KElement covermd = cover.appendElement("ContentMetaData");
		JDFPart part = (JDFPart) covermd.appendElement("Part");
		part.setRunTags("CoverLetter");
		part.setSheetName("CoverLetterSheet");
		final String idSheet = insert.appendAnchor(null);
		final KElement insertmd = cover.appendElement("ContentMetaData");
		part = (JDFPart) insertmd.appendElement("Part");
		part.setRunTags("BrochureSheets");
		part.setSheetName("BrochureSheet");

		int xMin = 0;
		int xMax = 0;
		for (int i = 0; i < 4; i++)
		{
			final JDFRunList rl = (JDFRunList) ruli.addPartition(EnumPartIDKey.Run, "" + (i + 1));
			rl.setEndOfSet(true);
			final JDFIntegerRangeList irl = new JDFIntegerRangeList();
			irl.append(xMin, xMax);
			rl.setPages(irl);
			rl.appendLayoutElement().setAttribute("ContentDataRefs", i % 2 == 0 ? idCover : idSheet);
			xMin = xMax + 1;
			xMax += i % 2 == 0 ? 4 : 1;
			rl.setAttribute("SheetSides", i % 2 == 0 ? "Front" : "FrontBack");
		}
		Assert.assertTrue(cover.hasAttribute("ID"));
		n.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "ContentDataRunList.jdf", 2, false);
	}

	/**
	 *
	 *
	 */
	public void testVariableRipBookletPipe()
	{
		final JDFDoc jdfDoc = new JDFDoc("JDF");
		final JDFNode n = jdfDoc.getJDFRoot();
		n.setJobID("J1");
		n.setType(JDFNode.EnumType.ProcessGroup);
		final JDFNode idp = n.addCombined(new VString("Imposition Interpreting Rendering", null));
		JDFRunList rl = (JDFRunList) idp.addResource(ElementName.RUNLIST, null);
		rl.setAttribute("Automation", "Dynamic");
		rl.setPipeID("PipeByteMap");
		rl.setID("ByteMap");
		rl.setPartIDKeys(new VString("SetIndex DocTags SheetIndex Side Separation", null));
		rl = (JDFRunList) rl.addPartition(EnumPartIDKey.SetIndex, "0~-1");
		idp.ensureLink(rl, EnumUsage.Output, null);
		final JDFRunList cover = (JDFRunList) rl.addPartition(EnumPartIDKey.DocTags, "Cover");
		cover.setNPage(2);
		rl.addPartition(EnumPartIDKey.DocTags, "Body");
		final JDFNode print = n.addCombined(new VString("DigitalPrinting", null));
		print.ensureLink(rl, EnumUsage.Input, null);
		writeTest(jdfDoc, "BookletPipeRunList.jdf");
	}

	/**
	 *
	 *
	 * @param command
	 * @return
	 */
	JDFPipeParams createPipeParams(final JDFCommand command)
	{
		final JDFPipeParams pp = command.appendPipeParams();
		pp.setPipeID("PipeByteMap");
		pp.setJobID("J1");
		pp.setJobPartID("Part");
		return pp;
	}

	/**
	 *
	 *
	 */
	public void testPipePushSheetMeta()
	{
		final JDFJMF jmf = new JDFDoc("JMF").getJMFRoot();
		jmf.setSenderID("DFE");
		final VString frontback = new VString("Front Back", null);
		final VString coverBody = new VString("Cover Body", null);
		final VString cmyk = new VString("Cyan Magenta Yellow Black ", null);
		for (int j = 0; j < 2; j++)
		{
			for (final String cb : coverBody)
			{
				for (int i = 0; i < 2 + j; i++)
				{
					if (i > 0 && cb.equals("Cover"))
						break;
					for (final String fb : frontback)
					{
						for (final String sep : cmyk)
						{
							final JDFCommand command = jmf.appendCommand();
							command.setXMLComment("The " + (i + 1) + " push: " + (3 + j) + " body sheets");
							command.setType(JDFMessage.EnumType.PipePush);
							command.setSenderID("RIP");
							final JDFPipeParams pp = createPipeParams(command);
							final JDFAttributeMap m = createRunListPartition(j, cb, i, fb, sep, pp);
							final JDFAttributeMap m2 = m.clone().removeKeys(new VString("DocTags SheetIndex Side Separation", null));
							final JDFAmountPool ap = (JDFAmountPool) pp.getCreateElement(ElementName.AMOUNTPOOL);
							final JDFPartAmount pa2 = ap.getCreatePartAmount(m2);
							pa2.setActualAmount(2 * 4 * (4 + j), null);
							final JDFPartAmount pa = ap.getCreatePartAmount(m);
							pa.setActualAmount(1, null);
							pa2.getPart(0).setAttribute(EnumPartIDKey.Metadata0.getName(), ((j == 0) ? "Fuzzy" : "Crisp"));
						}
					}
				}
			}
		}
		jmf.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "RIPPipePushRunListSheet.jmf", 2, false);
	}

	/**
	 *
	 *
	 */
	public void testPipePushSetMeta()
	{
		final JDFJMF jmf = new JDFDoc("JMF").getJMFRoot();
		jmf.setSenderID("DFE");
		final VString frontback = new VString("Front Back", null);
		final VString coverBody = new VString("Cover Body", null);
		final VString cmyk = new VString("Cyan Magenta Yellow Black ", null);
		for (int j = 0; j < 2; j++)
		{
			final JDFCommand command = jmf.appendCommand();
			command.setXMLComment("The " + j + " set: ");
			command.setType(JDFMessage.EnumType.PipePush);
			command.setSenderID("RIP");
			final JDFPipeParams pp = createPipeParams(command);
			final JDFAmountPool ap = (JDFAmountPool) pp.getCreateElement(ElementName.AMOUNTPOOL);
			final JDFPartAmount pa0 = ap.getCreatePartAmount(new JDFAttributeMap("SetIndex", "" + j));
			pa0.getPart(0).setAttribute(EnumPartIDKey.Metadata0.getName(), ((j == 0) ? "Nice" : "Nasty"));
			pa0.getPart(0).setAttribute(EnumPartIDKey.Metadata1.getName(), ((j == 0) ? "Hot" : "Cold"));
			pa0.setActualAmount(2 * (j + 3), null);
			pa0.setAmount((j + 1) * 200, null);
			pa0.setXMLComment("is this the right place for requested number of copies?");
			for (final String cb : coverBody)
			{
				for (int i = 0; i < 2 + j; i++)
				{
					if (i > 0 && cb.equals("Cover"))
						break;
					for (final String fb : frontback)
					{
						for (final String sep : cmyk)
						{
							final JDFAttributeMap m = createRunListPartition(j, cb, i, fb, sep, pp);
							final JDFPartAmount pa = ap.getCreatePartAmount(m);
							pa.setActualAmount(1, null);
						}
					}
				}
			}
		}
		writeTest(jmf, "RIPPipePushRunListSet.jmf", true, null);
	}

	JDFAttributeMap createRunListPartition(final int set, final String cb, final int sheet, final String fb, final String sep, final JDFPipeParams pp)
	{
		JDFRunList rl = (JDFRunList) pp.getCreateResource(ElementName.RUNLIST);
		rl.setID("ByteMap");
		rl.setAttribute("Automation", "Dynamic");
		final JDFAttributeMap m = new JDFAttributeMap("SetIndex", "" + set);
		m.put(EnumPartIDKey.DocTags, cb);
		m.put(EnumPartIDKey.SheetIndex, "" + sheet);
		m.put(EnumPartIDKey.Side, fb);
		m.put(EnumPartIDKey.Separation, sep);
		rl = (JDFRunList) rl.getCreatePartition(m, new VString("SetIndex DocTags SheetIndex Side Separation", null));

		rl.setFileURL("http://Set_" + set + "_" + cb + "_sheet" + sheet + "_" + fb + "_" + sep + ".tif");
		return m;
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testVariableManifests() throws Exception
	{
		ruli.setXMLComment("the set / doc / ... structure is transferred from the pre-impositioning pdl");

		final JDFPageList pl = (JDFPageList) n.addResource(ElementName.PAGELIST, null);
		pl.setResStatus(EnumResStatus.Available, false);

		final JDFContentList cl = (JDFContentList) pl.appendContentList().makeRootResource(null, null, true);
		cl.setResStatus(EnumResStatus.Available, false);
		cl.setXMLComment("Should we allow for content-data cross references to forther contentdata fields?");
		ruli.refPageList(pl);
		comp.refPageList(pl);
		int pageCount = 0;
		digiParams.setSides(EnumSides.TwoSidedFlipY);
		digiParams.setXMLComment("the sides attribute may be overridden by explicitly or implicitly missing runlist input elements");

		final VString vRun = new VString("Letter BrochureMale BrochureFemale", null);

		final JDFStitchingParams sp = (JDFStitchingParams) n.addResource(ElementName.STITCHINGPARAMS, EnumUsage.Input);
		med.setXMLComment("Media Partitioning is convenience only- the actual media selection is done by the digitalprintingparams MediaRef");
		for (int i = 0; i < vRun.size(); i++)
		{
			final String part = vRun.elementAt(i);
			final JDFMedia partMedia = (JDFMedia) med.addPartition(EnumPartIDKey.Run, part);
			partMedia.setProductID(part + "Media");

			final JDFDigitalPrintingParams digiPart = (JDFDigitalPrintingParams) digiParams.addPartition(EnumPartIDKey.Run, part);
			digiPart.refMedia(partMedia);

			sp.setXMLComment("how are multiple runs stitched together e.g. cover + body?");
			final JDFStitchingParams spPart = (JDFStitchingParams) sp.addPartition(EnumPartIDKey.Run, part);
			if (i == 0)
			{
				spPart.setNoOp(true);
				spPart.setDescriptiveName("The letter is a loose leaf collection");
			}
			else
			{
				spPart.setNumberOfStitches(3);
				spPart.setStitchType(EnumStitchType.Saddle);
			}
		}

		// loop over records
		for (int i = 0; i < 10; i++)
		{
			final JDFContentData letter = cl.appendContentData();
			letter.setContentType("Letter");
			final JDFContentData brochure = cl.appendContentData();
			brochure.setContentType("Brochure");
			final KElement lMeta = letter.appendElement("ContentMetadata");
			// TODO apply new example
			lMeta.setAttribute("Record", "" + i);
			final String gender = i % 2 == 0 ? "Male" : "Female";
			lMeta.setAttribute("Gender", gender);
			lMeta.setXMLComment("Note that the final format of the metadata element is open");
			final KElement bMeta = brochure.appendElement("Metadata");
			bMeta.setAttribute("Record", "" + i);
			bMeta.setAttribute("Gender", gender);

			final JDFRunList runSet = (JDFRunList) ruli.addPartition(EnumPartIDKey.RunSet, "Record" + i);
			JDFRunList run = runSet.addRun("file://server/tifs/testLetter" + i + ".tif", 0, -1);
			run.setRun("Letter");
			final JDFComponent compSet = (JDFComponent) comp.addPartition(EnumPartIDKey.RunSet, runSet.getRunSet());
			JDFComponent co = (JDFComponent) compSet.addPartition(EnumPartIDKey.Run, run.getRun());
			int page = (3 * i) % 7 + 1;
			run.setPageListIndex(new JDFIntegerRangeList(new JDFIntegerRange(pageCount, pageCount + page - 1)));
			co.setPageListIndex(new JDFIntegerRangeList(new JDFIntegerRange(pageCount, pageCount + page - 1)));
			co.setSurfaceCount(2 * ((page + 1) / 2));
			run.setPages(new JDFIntegerRangeList("0~" + (page - 1)));
			run.setXMLComment("Cover Letter - record " + i);
			run.setEndOfDocument(true);
			JDFPageData pd = pl.appendPageData();
			pd.refContentData(letter);
			pd.setAttribute("PageIndex", pageCount + " ~ " + (pageCount + page - 1));
			pageCount += page;

			run = runSet.addRun("file://server/tifs/testBrochure" + i + ".tif", 0, -1);
			run.setRun("Brochure" + gender);

			co = (JDFComponent) compSet.addPartition(EnumPartIDKey.Run, run.getRun());
			page = 2 * ((7 * i) % 13) + 2;
			run.setPageListIndex(new JDFIntegerRangeList(new JDFIntegerRange(pageCount, pageCount + page - 1)));
			co.setPageListIndex(new JDFIntegerRangeList(new JDFIntegerRange(pageCount, pageCount + page - 1)));
			co.setSurfaceCount(2 * ((page + 1 + 1) / 2)); // the 2nd +1 is for
			// the blank inside cover
			run.setPages(new JDFIntegerRangeList("0~" + (page - 1)));
			run.setXMLComment("Brochure - record " + i);
			run.setEndOfDocument(true);
			runSet.setEndOfSet(true);
			run.setAttribute("SkipBlankOrds", "1");
			run.setNPage(page + 1);
			run.setXMLComment("SkipBlankOrds specifies the relative position of pages to skip\n1 specifies that the first back sheet is skipped\nNPage MUST be incremented by the # of skipped pages.");
			pd = pl.appendPageData();
			pd.refContentData(brochure);
			pd.setAttribute("PageIndex", pageCount + " ~ " + (pageCount + page - 1));
			pageCount += page;
		}
		doc.write2File(sm_dirTestDataTemp + "RunlistManifest.jdf", 2, false);

	}

	/**
	 *
	 */
	@Override
	public void setUp()
	{
		KElement.setLongID(false);
		JDFAudit.setStaticAgentName(null);
		JDFAudit.setStaticAgentVersion(null);
		JDFAudit.setStaticAuthor(null);

		doc = new JDFDoc("JDF");
		n = doc.getJDFRoot();
		n.setJobID("JobID");
		n.setType(EnumType.Combined);
		n.setTypes(new VString("Interpreting Rendering DigitalPrinting Stitching", " "));
		comp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		rlComp = n.getLink(comp, null);
		digiParams = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		med = (JDFMedia) n.appendMatchingResource(ElementName.MEDIA, EnumProcessUsage.AnyInput, null);
		med.setResStatus(EnumResStatus.Available, false);
		rlMedia = n.getLink(med, null);
		ruli = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);

	}

	// ///////////////////////////////////////////////////////////////////////
}
