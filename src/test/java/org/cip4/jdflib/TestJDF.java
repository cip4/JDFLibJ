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
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.StringUtil;

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
	public void testWriteJMF()
	{
		final JDFDoc d = new JDFDoc("JMF");
		JDFJMF jmf = d.getJMFRoot();
		jmf.appendCommand().setType("getVersion");
		// JDFDoc d2 = d.write2URL("http://kie-schielke-nb:6311/StorageService-J/Storage");
		JDFDoc d2 = d.write2URL("http://kie-wf16prdy:6311/StorageService-J/Storage");

	}

	/**
	 * 
	 * TODO Please insert comment!
	 * @throws Throwable
	 */

	public void testSpawnRW() throws Throwable
	{
		JDFDoc jdfDoc = new JDFParser().parseFile("/share/data/fehler/PD-68493/giant.jdf");
		if (jdfDoc == null)
			return;
		JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("IPr0.PP", null);
		JDFResource.setUnpartitiondImplicit(true);
		CPUTimer ct = new CPUTimer(false);
		JDFSpawn spawn;

		for (int ii = 0; ii < 21; ii++)
		{
			final VJDFAttributeMap vamParts = new VJDFAttributeMap();
			for (int i = 1; i <= 40; i++)
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
			spawn = new JDFSpawn(nodeProc);
			spawn.bSpawnIdentical = true;
			spawn.bSpawnRWPartsMultiple = true;
			JDFNode nodeSubJDF = spawn.spawn(null, null, vsRWResourceIDs, vamParts, true, true, true, false);
			log.info(ii + " " + ct.getSingleSummary());
			ct.stop();
			if (ii == 1)
				nodeSubJDF.getOwnerDocument_JDFElement().write2File("/share/data/fehler/PD-68493/spawn.jdf", 2, false);
		}
		String strOutJDFPath = "/share/data/fehler/PD-68493/giant_out.jdf";
		jdfDoc.write2File(strOutJDFPath, 2, false);

	}

	/**
	 * 
	 */
	// @Test
	//	public void testgetPartition()
	//	{
	//		final JDFDoc d = new JDFParser().parseFile("/share/data/JDF/StefanBartels/crap.jdf");
	//		final JDFNode n = d.getJDFRoot();
	//		JDFResource r = (n.getResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output, 0));
	//		JDFResource rp = r.getPartition(new JDFAttributeMap("SignatureName", "Sig0001"), null);
	//		Assert.assertNull(rp);
	//	}

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