/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of 
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
/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFConvertingConfig;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.junit.jupiter.api.Test;
/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 28.11.2008
 */
class SheetOptimizeTest extends JDFTestCaseBase
{
	/**
	 * test sheetoptimization
	 * TODO selective part from larger stripping (cover, creep etc.)
	 * TODO productID
	 * TODO margins vs. output stripping
	 * 
	 * @throws Exception x
	 */
	@Test
	void testDescribeSheetOptimize() throws Exception
	{
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setXMLComment("this is a process PRIOR to stripping - the output is the completed strippingparams\n"
				+ "note that each GangElement is essentially a simplified stripping description of the individual product with additional amounts", true);
		n.setType("SheetOptimizing", false);
		final JDFRunList rl1 = (JDFRunList) n.addResource("RunList", EnumUsage.Input);
		rl1.addPDF("file1.pdf", 0, -1);
		final JDFRunList rl2 = (JDFRunList) n.addResource("RunList", EnumUsage.Input);
		rl2.addPDF("file2.pdf", 0, -1);

		final JDFResource sheetOptParams = n.addResource("SheetOptimizingParams", EnumUsage.Input);
		sheetOptParams.setXMLComment("Similar to DieLayoutProductionParams\nalso add general parameters for optimization"
				+ "\nalso discuss reusing repeatdesc versus dedicated GangElement", true);

		final JDFConvertingConfig cc = (JDFConvertingConfig) sheetOptParams.appendElement(ElementName.CONVERTINGCONFIG, null);
		cc.setSheetHeight(new JDFNumberRange(600, 800));
		cc.setSheetWidth(new JDFNumberRange(900, 1100));
		cc.appendDevice().setDeviceID("SM102-a");
		cc.setMarginBottom(36);
		cc.setMarginTop(36);
		cc.setMarginLeft(36);
		cc.setMarginRight(36);
		cc.setXMLComment("Here we also should ask ourselves whether we want to reuse ConvertingConfig or create our own resource?", true);

		final JDFElement repDesc = (JDFElement) sheetOptParams.appendElement("GangElement", null);
		repDesc.setAttribute("OrderQuantity", 5000, null);
		repDesc.setAttribute("JobID", "IndividualJobID");
		repDesc.setAttribute("NPage", "16");
		repDesc.setAttribute("Dimension", "300 400");
		repDesc.setXMLComment("simple description of an individual job with no knowledge of the pdf - is this a valid scenario?", true);
		repDesc.appendElement(ElementName.MEDIA).setXMLComment("Should Media go here or be inferred from the output Stripping which is matched by some partition key in SheetoptimizingParams?", true);
		repDesc.refElement(rl1);

		JDFAssembly assembly = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Output);
		assembly.setJobID(repDesc.getAttribute(AttributeName.JOBID));
		assembly.setXMLComment("one output assembly/GangElement (JobID) \n it is necessary to create multiple GangElement entries/jobID e.g. for cover + body - this would make @OrderBinderySignatures redundant because strict ordering could be achieved by always providing exactly the number of pages required", true);

		{
			final JDFElement repDesca = (JDFElement) sheetOptParams.appendElement("GangElement", null);
			repDesca.setAttribute("OrderQuantity", 8000, null);
			repDesca.setAttribute("JobID", "IndividualJobID");
			repDesca.setAttribute("NPage", "24");
			repDesca.setAttribute("Dimension", "300 400");
			repDesca.setAttribute(AttributeName.GRAINDIRECTION, "LongEdge");
			repDesca.setXMLComment("simple description of an individual job with no knowledge of the pdf but with knowledge of a BinderySignature", true);
			repDesca.appendElement(ElementName.MEDIA).setXMLComment("Should Media go here or be inferred from the output Stripping which is matched by some partition key in SheetoptimizingParams?", true);
			repDesca.refElement(rl2);
			JDFBinderySignature bsa = (JDFBinderySignature) repDesca.appendElement(ElementName.BINDERYSIGNATURE);
			bsa.setFoldCatalog("F16-2");
			bsa = (JDFBinderySignature) repDesca.appendElement(ElementName.BINDERYSIGNATURE);
			bsa.setFoldCatalog("F8-2");
			bsa.setXMLComment("do we need all pages of binderysignatures to add up or do we assume that the optimizer will do its best by repeating appropriate binderysignatures if less bs than pages are provided", true);
			JDFSeparationList sl = (JDFSeparationList) repDesca.appendElement(ElementName.SEPARATIONLIST);
			sl.setCMYK();
			sl.renameElement("Colors", null);
			repDesca.copyElement(sl, null).renameElement("BackColors", null);
			sl.setXMLComment("is this ok or do we want a complete colorantcontrol?", true);
		}
		{
			final JDFElement repDesca = (JDFElement) sheetOptParams.appendElement("GangElement", null);
			repDesca.setAttribute("OrderQuantity", 8000, null);
			repDesca.setAttribute("JobID", "IndividualJobIDA");
			repDesca.setAttribute("NPage", "40");
			repDesca.setAttribute("Dimension", "300 400");
			repDesca.setAttribute("OrderBinderySignatures", "Reorder");
			repDesca.setXMLComment("simple description of an individual job with no knowledge of the pdf but with knowledge of a BinderySignature", true);
			repDesca.appendElement(ElementName.MEDIA).setXMLComment("Should Media go here or be inferred from the output Stripping which is matched by some partition key in SheetoptimizingParams?", true);
			JDFBinderySignature bsa = (JDFBinderySignature) repDesca.appendElement(ElementName.BINDERYSIGNATURE);
			bsa.setFoldCatalog("F16-2");
			bsa = (JDFBinderySignature) repDesca.appendElement(ElementName.BINDERYSIGNATURE);
			bsa.setFoldCatalog("F8-2");
			bsa.setXMLComment("do we need all pages of binderysignatures to add up or do we assume that the optimizer will do its best by repeating appropriate binderysignatures if less bs than pages are provided", true);
			assembly = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Output);
			assembly.setJobID(repDesca.getAttribute(AttributeName.JOBID));
		}

		final JDFElement repDesc1 = (JDFElement) sheetOptParams.appendElement("GangElement", null);
		repDesc1.refElement(rl1);

		repDesc1.setAttribute("OrderQuantity", 5000, null);
		final JDFBinderySignature bs1 = (JDFBinderySignature) repDesc1.appendElement(ElementName.BINDERYSIGNATURE);
		bs1.setFoldCatalog("F8-2");
		repDesc1.setXMLComment("do we want to explicitly specify BinderySignatures or do we only send # pages or allow both?", true);
		repDesc1.setAttribute("JobID", "IndividualJobIDB");
		assembly = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Output);
		assembly.setJobID(repDesc1.getAttribute(AttributeName.JOBID));

		final JDFElement repDesc2 = (JDFElement) sheetOptParams.appendElement("GangElement", null);
		repDesc2.refElement(rl2);
		repDesc2.setAttribute("OrderQuantity", 5000, null);
		final JDFBinderySignature bs2 = (JDFBinderySignature) repDesc2.appendElement(ElementName.BINDERYSIGNATURE);
		bs2.setFoldCatalog("F16-2");
		repDesc1.setAttribute("JobID", "IndividualJobIDC");
		assembly = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Output);
		assembly.setJobID(repDesc1.getAttribute(AttributeName.JOBID));

		JDFStrippingParams stripparams = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Output);
		stripparams.setXMLComment("This is the empty shell that should be filled by the optimizer", true);
		stripparams.setResStatus(EnumResStatus.Unavailable, false);
		JDFResourceLink strpLink = n.getLink(stripparams, null);
		strpLink.setAmount(1234, new JDFAttributeMap("SheetName", "S1"));
		strpLink.getAmountPool().setXMLComment("\n planned ideal amounts go here - I assume we do not want an entire process network and that the amounts are minimum # of planned good copies", true);

		d.write2File(sm_dirTestDataTemp + "sheetOptimize.jdf", 2, false);
	}

	/**
	 * test sheetoptimization
	 * TODO selective part from larger stripping (cover, creep etc.)
	 * TODO productID
	 * TODO margins vs. output stripping
	 * 
	 * @throws Exception x
	 */
	@Test
	void testDescribeSheetOptimizeNew() throws Exception
	{
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setXMLComment("this is a process PRIOR to stripping - the output is the completed strippingparams\n"
				+ "note that each GangElement is essentially a simplified stripping description of the individual product with additional amounts", true);
		n.setType("SheetOptimizing", false);

		final JDFResource sheetOptParams = n.addResource("SheetOptimizingParams", EnumUsage.Input);
		sheetOptParams.setXMLComment("Similar to DieLayoutProductionParams\nalso add general parameters for optimization"
				+ "\nalso discuss reusing repeatdesc versus dedicated GangElement", true);

		final JDFConvertingConfig cc = (JDFConvertingConfig) sheetOptParams.appendElement(ElementName.CONVERTINGCONFIG, null);
		cc.setSheetHeight(new JDFNumberRange(600, 800));
		cc.setSheetWidth(new JDFNumberRange(900, 1100));
		cc.appendDevice().setDeviceID("SM102-a");
		cc.setMarginBottom(36);
		cc.setMarginTop(36);
		cc.setMarginLeft(36);
		cc.setMarginRight(36);
		cc.setXMLComment("Here we also should ask ourselves whether we want to reuse ConvertingConfig or create our own resource?", true);

		createNewGangElement(n, 1, sheetOptParams);
		createNewGangElement(n, 2, sheetOptParams);

		JDFStrippingParams stripparams = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Output);
		stripparams.setXMLComment("This is the empty shell describes the optimided gang jobs and should be filled by the optimizer", true);
		stripparams.setResStatus(EnumResStatus.Unavailable, false);
		JDFResourceLink strpLink = n.getLink(stripparams, null);
		strpLink.setAmount(1234, new JDFAttributeMap("SheetName", "S1"));
		strpLink.getAmountPool().setXMLComment("\n planned ideal amounts go here - I assume we do not want an entire process network and that the amounts are minimum # of planned good copies", true);

		d.write2File(sm_dirTestDataTemp + "sheetOptimizeNew.jdf", 2, false);
	}

	protected void createNewGangElement(final JDFNode n, final int i, final JDFResource sheetOptParams)
	{
		final JDFRunList rl2 = (JDFRunList) n.addResource("RunList", EnumUsage.Input);
		rl2.addPDF("file" + i + ".pdf", 0, -1);

		final JDFElement repDesca = (JDFElement) sheetOptParams.appendElement("GangElement", null);
		repDesca.setAttribute("OrderQuantity", i * 2000, null);
		repDesca.setAttribute("JobID", "IndividualJobID_" + i);
		repDesca.setAttribute(AttributeName.GRAINDIRECTION, "LongEdge");
		repDesca.setXMLComment("description of an individual job with 3 Fold sheets, one of which must be ganged: AssemblyID=AssemID" + i + "3", true);
		repDesca.appendElement(ElementName.MEDIA).setXMLComment("Should Media go here or be inferred from the output Stripping which is matched by some partition key in SheetoptimizingParams?", true);
		repDesca.refElement(rl2);
		repDesca.setAttribute(AttributeName.ASSEMBLYIDS, "AssemID" + i + "3");

		JDFAssembly assembly = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
		assembly.setJobID(repDesca.getAttribute(AttributeName.JOBID));
		assembly.setAssemblyIDs(new VString("AssemID" + i + "1 AssemID" + i + "2 AssemID" + i + "3", null));

		JDFSeparationList sl = (JDFSeparationList) repDesca.appendElement(ElementName.SEPARATIONLIST);
		sl.setCMYK();
		sl.renameElement("Colors", null);
		repDesca.copyElement(sl, null).renameElement("BackColors", null);
		JDFStrippingParams sp = (JDFStrippingParams) repDesca.appendElement(ElementName.STRIPPINGPARAMS);
		sp.setXMLComment("This Input StrippingParams describes the individual set of foldingsheets with NO sheet context.", true);
		sp.makeRootResource(null, null, true);
		JDFStrippingParams spa = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.BinderySignatureName, "BS_" + i + "_1");
		spa.setAssemblyIDs(new VString("AssemID" + i + "1 AssemID" + i + "2", null));
		JDFBinderySignature bsa = (JDFBinderySignature) spa.appendElement(ElementName.BINDERYSIGNATURE);
		bsa.setFoldCatalog("F16-2");
		spa = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.BinderySignatureName, "BS_" + i + "_2");
		spa.setAssemblyIDs(new VString("AssemID" + i + "3", null));
		bsa = (JDFBinderySignature) spa.appendElement(ElementName.BINDERYSIGNATURE);
		bsa.setFoldCatalog("F8-2");
	}
}
