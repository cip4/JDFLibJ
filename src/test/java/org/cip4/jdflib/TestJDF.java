/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of 
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

package org.cip4.jdflib;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.cip4.jdflib.resource.JDFResource;
import org.junit.After;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * < July 9, 2009
 */
public class TestJDF extends JDFTestCaseBase
{
	private static final String SEPARATOR = File.separator;

	static protected final String sm_dirTestData = getTestDataDir();
	static protected final String sm_dirTestSchema = sm_dirTestData + "schema" + SEPARATOR + "Version_1_3" + SEPARATOR;
	static protected final String sm_dirTestDataTemp = sm_dirTestData + "temp" + SEPARATOR;

	private static String getTestDataDir()
	{

		String path = JDFTestCaseBase.class.getResource("/data").getPath();
		path = FilenameUtils.normalize(path) + File.separator;

		return path;

	}

	/**
	 * 
	 * 
	 * @throws FileNotFoundException
	 */
	// @Test
	//	public void testSize() throws FileNotFoundException
	//	{
	//		SizeWalker sw = new SizeWalker(new File("/share/data/size.xml"));
	//		final JDFDoc d = new JDFParser().parseFile("/share/data/big.jdf");
	//		sw.walkAll(d.getRoot());
	//	}

	/**
	 * 
	 */
	// @Test
	//	public void testWriteJMF()
	//	{
	//		final JDFDoc d = new JDFDoc("JMF");
	//		JDFJMF jmf = d.getJMFRoot();
	//		jmf.appendCommand().setType("getVersion");
	//		// JDFDoc d2 = d.write2URL("http://kie-schielke-nb:6311/StorageService-J/Storage");
	//		JDFDoc d2 = d.write2URL("http://kie-wf16prdy:6311/StorageService-J/Storage");
	//
	//	}

	/**
	 * 
	 * TODO Please insert comment!
	 * @throws Throwable
	 */
	/*
		public void testSpawnRW() throws Throwable
		{
			JDFDoc jdfDoc = new JDFParser().parseFile("/share/data/fehler/PD-68493/giant.jdf");
			if (jdfDoc == null)
				return;
			JDFNode jdfRoot = jdfDoc.getJDFRoot();
			JDFNode nodeProc = jdfRoot.getJobPart("IPr0.PP", null);
			JDFResource.setUnpartitiondImplicit(true);
			CPUTimer ct = new CPUTimer(false);
			CPUTimer ctm = new CPUTimer(false);
			JDFSpawn spawn;
			spawn = new JDFSpawn(nodeProc);
			spawn.bSpawnIdentical = true;
			spawn.bSpawnRWPartsMultiple = true;
			JDFMerge m = new JDFMerge(jdfRoot);

			Vector<JDFNode> vSpawned = new Vector<JDFNode>();
			for (int ii = 1; ii < 21; ii++)
			{
				final VJDFAttributeMap vamParts = new VJDFAttributeMap();
				for (int i = 1; i <= 20; i++)
				{
					JDFAttributeMap amParts0 = new JDFAttributeMap();
					amParts0.put("BinderySignatureName", "Booklet_" + ii);
					amParts0.put("PartVersion", "Pol");
					amParts0.put("SheetName", StringUtil.sprintf("FB %03i", "" + i));
					amParts0.put("SignatureName", StringUtil.sprintf("Sig%03i", "" + i));
					amParts0.put("Side", "Front");
					vamParts.add(amParts0);
					amParts0 = amParts0.clone();
					amParts0.put("Side", "Back");
					vamParts.add(amParts0);
				}
				final VString vsRWResourceIDs = new VString("Output", null);

				ct.start();
				JDFNode nodeSubJDF = spawn.spawn(null, null, vsRWResourceIDs, vamParts, true, true, true, false);
				vSpawned.add(nodeSubJDF);
				log.info(ii + " " + ct.getSingleSummary());
				ct.stop();
				if (ii == 1)
					nodeSubJDF.getOwnerDocument_JDFElement().write2File("/share/data/fehler/PD-68493/spawn.jdf", 2, false);
			}
			String strOutJDFPath = "/share/data/fehler/PD-68493/giant_out.jdf";
			jdfDoc.write2File(strOutJDFPath, 2, false);
			int ii = 0;
			for (JDFNode nodeSubJDF : vSpawned)
			{
				ctm.start();
				m.mergeJDF(nodeSubJDF);
				log.info(ii++ + " " + ctm.getSingleSummary());
				ctm.stop();
			}
			strOutJDFPath = "/share/data/fehler/PD-68493/giant_merged.jdf";
			jdfDoc.write2File(strOutJDFPath, 2, false);

		}*/

	/*public void testSpawn() throws Throwable
	{
		JDFDoc jdfDoc = JDFDoc.parseFile("/share/data/fehler/Vers_Sted.1(Vers_Sted.1).jdf");

		JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("SFP0.I", JDFConstants.EMPTYSTRING);

		VJDFAttributeMap vamParts = new VJDFAttributeMap();

		JDFAttributeMap amParts0 = new JDFAttributeMap();

		amParts0.put("PartVersion", "DT_Mac DT_Mac");
		amParts0.put("Separation", "Cyan");
		amParts0.put("SheetName", "FB 001");
		amParts0.put("Side", "Front");
		amParts0.put("SignatureName", "Sig001");

		vamParts.add(amParts0);

		VString vsRWResourceIDs = new VString();

		vsRWResourceIDs.add("Link_131218_053435863_000421");
		vsRWResourceIDs.add("Link_131218_053435871_000422");
		vsRWResourceIDs.add("r_130507_125650793_001226");

		JDFSpawn spawn = new JDFSpawn(nodeProc);

		spawn.setNode(nodeProc);
		spawn.bFixResources = false;
		spawn.bSpawnRWPartsMultiple = true;
		spawn.bSpawnIdentical = true;

		JDFNode nodeSubJDF = spawn.spawn("", null, vsRWResourceIDs, vamParts, true, true, true, false);

		assertNotNull(nodeSubJDF);
	}*/

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	@After
	protected void tearDown() throws Exception
	{
		JDFResource.setUnpartitiondImplicit(false);
		super.tearDown();
	}

	/*	*//**
			* 
			*  
			* @throws Throwable
			*/
	/*
	public void testAddNodeInfoParts() throws Throwable
	{
	final JDFDoc jdfDoc = JDFDoc.parseFile("/share/data/fehler/InkZoneCalculator2.jdf");
	if (jdfDoc == null)
		return;
	final JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("SFP0.P", JDFConstants.EMPTYSTRING);
	final JDFNodeInfo nodeInfo = nodeProc.getNodeInfo();
	final VJDFAttributeMap vamDeepest = new VJDFAttributeMap();
	JDFAttributeMap amPart0 = new JDFAttributeMap();

	amPart0.put("PartVersion", "chn");
	amPart0.put("SheetName", "IN_FB 001");
	amPart0.put("Side", "Back");
	amPart0.put("SignatureName", "Sig001");

	vamDeepest.add(amPart0);

	final VElement veParts = nodeInfo.getPartitionVector(vamDeepest, null);

	assertEquals("Number of spawnInfos", 1, veParts.size());

	final JDFNodeInfo niFound = (JDFNodeInfo) veParts.get(0);

	JDFAttributeMap amFound = niFound.getPartMap();

	assertEquals("Found partmap must equal input partmap", amPart0, amFound);
	}*/

	/**
	 * 
	 */
	// @Test
	//	public void testMergeAmount()
	//	{
	//		final JDFDoc d = new JDFParser().parseFile("/share/data/JDF/Jira/PD-1735/amount.jdf");
	//		final JDFNode n = d.getJDFRoot();
	//		JDFResource rr = (JDFResource) n.getChildWithAttribute(ElementName.COMPONENT, "ID", null, "PrintedPaper", 0, false);
	//		final VElement vr = rr.getLeaves(true);
	//		for (KElement r : vr)
	//			((JDFResource) r).updateAmounts(false);
	//		d.write2File("/share/data/JDF/Jira/PD-1735/amountnew.jdf", 2, false);
	//	}

	/**
	 * 
	public void testelementsbytag()
	{
		final JDFDoc d = new JDFParser().parseFile("/share/data/badident.jdf");
		final JDFNode n = d.getJDFRoot();
		Vector<JDFIdentical> mediaList = n.getChildrenByClass(JDFIdentical.class, true, 0);
		for (JDFIdentical id : mediaList)
		{
			if (id.getTarget() == null)
			{
				System.out.println("ID: " + id.getParentResource().getID());
				System.out.print(id.getParentResource());
			}
		}
	}
	*/
}