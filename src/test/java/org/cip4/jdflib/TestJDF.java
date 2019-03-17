/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */

package org.cip4.jdflib;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.Collection;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.JDFSpawn;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         < July 9, 2009
 */
public class TestJDF extends JDFTestCaseBase
{

	/**
	 *
	 *
	 * @throws FileNotFoundException
	 */
	// @Test
	// public void testSize() throws FileNotFoundException
	// {
	// SizeWalker sw = new SizeWalker(new File("/share/data/size.xml"));
	// final JDFDoc d = new JDFParser().parseFile("/share/data/big.jdf");
	// sw.walkAll(d.getRoot());
	// }

	/**
	 *
	 */
	@Test
	@Ignore
	public void fixExampleVersions()
	{
		final Vector<File> v = FileUtil.listFilesInTree(new File("/gitreps/samples/src/main/resources/jdf"), "*.jdf");
		for (final File f : v)
		{
			final JDFDoc d = JDFDoc.parseFile(f);
			final KElement e = d == null ? null : d.getRoot();
			final VElement vAll = e == null ? null : e.getChildrenByTagName_KElement(null, null, null, false, true, 0);
			if (vAll != null)
			{
				vAll.add(e);
				boolean b = false;
				for (final KElement e2 : vAll)
				{
					String s = e2.getNonEmpty(AttributeName.VERSION);
					if (s != null && !"1.6".equals(s))
					{
						e2.setAttribute(AttributeName.VERSION, "1.6");
						b = true;
					}
					s = e2.getNonEmpty(AttributeName.MAXVERSION);
					if (s != null && !"1.6".equals(s))
					{
						e2.setAttribute(AttributeName.MAXVERSION, "1.6");
						b = true;
					}
				}
				if (b)
				{
					log.info(f.getAbsolutePath());
					d.write2File(f, 2, false);
				}
			}
		}
	}
	/**
	 *
	 * TODO Please insert comment!
	 *
	 * @throws Throwable
	 */
	/*
	 * public void testSpawnRW() throws Throwable { JDFDoc jdfDoc = new JDFParser().parseFile("/share/data/fehler/PD-68493/giant.jdf"); if (jdfDoc == null) return; JDFNode jdfRoot =
	 * jdfDoc.getJDFRoot(); JDFNode nodeProc = jdfRoot.getJobPart("IPr0.PP", null); JDFResource.setUnpartitiondImplicit(true); CPUTimer ct = new CPUTimer(false); CPUTimer ctm = new CPUTimer(false);
	 * JDFSpawn spawn; spawn = new JDFSpawn(nodeProc); spawn.bSpawnIdentical = true; spawn.bSpawnRWPartsMultiple = true; JDFMerge m = new JDFMerge(jdfRoot);
	 *
	 * Vector<JDFNode> vSpawned = new Vector<JDFNode>(); for (int ii = 1; ii < 21; ii++) { final VJDFAttributeMap vamParts = new VJDFAttributeMap(); for (int i = 1; i <= 20; i++) { JDFAttributeMap
	 * amParts0 = new JDFAttributeMap(); amParts0.put("BinderySignatureName", "Booklet_" + ii); amParts0.put("PartVersion", "Pol"); amParts0.put("SheetName", StringUtil.sprintf("FB %03i", "" + i));
	 * amParts0.put("SignatureName", StringUtil.sprintf("Sig%03i", "" + i)); amParts0.put("Side", "Front"); vamParts.add(amParts0); amParts0 = amParts0.clone(); amParts0.put("Side", "Back");
	 * vamParts.add(amParts0); } final VString vsRWResourceIDs = new VString("Output", null);
	 *
	 * ct.start(); JDFNode nodeSubJDF = spawn.spawn(null, null, vsRWResourceIDs, vamParts, true, true, true, false); vSpawned.add(nodeSubJDF); log.info(ii + " " + ct.getSingleSummary()); ct.stop(); if
	 * (ii == 1) nodeSubJDF.getOwnerDocument_JDFElement().write2File("/share/data/fehler/PD-68493/spawn.jdf", 2, false); } String strOutJDFPath = "/share/data/fehler/PD-68493/giant_out.jdf";
	 * jdfDoc.write2File(strOutJDFPath, 2, false); int ii = 0; for (JDFNode nodeSubJDF : vSpawned) { ctm.start(); m.mergeJDF(nodeSubJDF); log.info(ii++ + " " + ctm.getSingleSummary()); ctm.stop(); }
	 * strOutJDFPath = "/share/data/fehler/PD-68493/giant_merged.jdf"; jdfDoc.write2File(strOutJDFPath, 2, false);
	 *
	 * }
	 */

	/**
	 *
	 * @throws Throwable
	 */
	public void _testSpawnf() throws Throwable
	{
		final JDFDoc jdfDoc = JDFDoc.parseFile("/data/JDF/FrankB.jdf");

		final JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("PP153.D", JDFConstants.EMPTYSTRING);
		final VJDFAttributeMap vamParts = new VJDFAttributeMap();
		final JDFAttributeMap amParts0 = new JDFAttributeMap();

		amParts0.put("Run", "_160421_143800168_022360m1");

		vamParts.add(amParts0);

		final VString vsRWResourceIDs = new VString();

		vsRWResourceIDs.add("Output");

		final JDFSpawn spawn = new JDFSpawn(nodeProc);

		spawn.setNode(nodeProc);
		spawn.bFixResources = false;
		spawn.bSpawnRWPartsMultiple = true;
		spawn.bSpawnIdentical = true;
		spawn.bSpawnROPartsOnly = true;

		final JDFNode nodeSubJDF = spawn.spawn("", null, vsRWResourceIDs, vamParts, true, true, true, false);
		nodeSubJDF.write2File("/data/JDF/FrankB.spawn.jdf");

	}

	@Test
	@Ignore
	public void testCheckSpawnedResources() throws Throwable
	{
		final JDFDoc jdfDoc = JDFDoc.parseFile("C:/data/spawnedrw.jdf");

		final VString vsRWResourceIDs = new VString();

		vsRWResourceIDs.add("Link_190305_130954868_000176");
		vsRWResourceIDs.add("Link_190305_130954788_000156");
		vsRWResourceIDs.add("Link_190305_130921088_000073");

		final VJDFAttributeMap vamSpawn = new VJDFAttributeMap();
		final JDFAttributeMap amSpawn = new JDFAttributeMap();
		amSpawn.put("Run", "Run_190305_131018209_000353");
		vamSpawn.add(amSpawn);

		final JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("Prp0.C", JDFConstants.EMPTYSTRING);

		final Collection<JDFResource> col = nodeProc.checkSpawnedResources(vsRWResourceIDs, vamSpawn);

		assertNotNull("No spawned resource found", col);
	}

	@Test
	@Ignore
	public void testSpawnedResources() throws Throwable
	{
		final JDFDoc jdfDoc = JDFDoc.parseFile("C:/data/spawnedrw.jdf");

		final VString vsRWResourceIDs = new VString();

		vsRWResourceIDs.add("Link_190305_130954868_000176");
		vsRWResourceIDs.add("Link_190305_130954788_000156");
		vsRWResourceIDs.add("Link_190305_130921088_000073");

		final VJDFAttributeMap vamSpawn = new VJDFAttributeMap();
		final JDFAttributeMap amSpawn = new JDFAttributeMap();
		amSpawn.put("Run", "Run_190305_131018209_000353");
		vamSpawn.add(amSpawn);

		final JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("Prp0.C", JDFConstants.EMPTYSTRING);
		final JDFResource pd = nodeProc.getResource("HDM:PageDocs", EnumUsage.Input, 0).getResourceRoot();
		pd.removeAttribute(AttributeName.SPAWNSTATUS);
		pd.removeAttribute(AttributeName.SPAWNIDS);
		pd.removeAttribute(AttributeName.LOCKED);
		final VElement v = nodeProc.getResourceLinks(null);
		for (final KElement e : v)
		{
			if (!e.getNodeName().startsWith("HDM:"))
				e.deleteNode();
		}

		final JDFSpawn sp = new JDFSpawn(nodeProc);
		sp.vRWResources_in = vsRWResourceIDs;
		sp.vSpawnParts = vamSpawn;
		final JDFNode n2 = sp.spawn();

		final JDFResource pds = n2.getResource("HDM:PageDocs", EnumUsage.Input, 0);

	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	@After
	public void tearDown() throws Exception
	{
		JDFResource.setUnpartitiondImplicit(false);
		super.tearDown();
	}

	/**
	 *
	 */
	@Test
	public void testDumy()
	{
		assertNull(null);
	}

}