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
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JDFSpawn;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * < July 9, 2009
 */
public class TestJDF extends JDFTestCaseBase
{
	private static final String SEPARATOR = File.separator; // "/"; //

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

	public void testSpawnROPartsOnly() throws Throwable
	{
		JDFDoc jdfDoc = new JDFParser().parseFile("/share/data/fehler/000080_ArtikelNummer(ArtikelNummer)_JDFRouter.jdf");

		JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("SFP0.P", JDFConstants.EMPTYSTRING);

		VString vsRWResourceIDs = new VString("r_130916_093633605_000365 r_130916_093633604_000363 Link_130916_093647006_000650 Link_130916_093647006_000651 r_130916_093633599_000349", null);

		VJDFAttributeMap vamParts = new VJDFAttributeMap();

		JDFAttributeMap amPart = new JDFAttributeMap();

		amPart.put("Separation", "Black");
		amPart.put("SheetName", "Faltschachtel");
		amPart.put("Side", "Front");
		amPart.put("SignatureName", "SIG001");

		vamParts.add(amPart);

		JDFSpawn spawn = new JDFSpawn(nodeProc);

		spawn.setNode(nodeProc);
		spawn.bFixResources = false;
		spawn.bSpawnRWPartsMultiple = true;
		spawn.bSpawnIdentical = true;
		spawn.bSpawnROPartsOnly = false;

		JDFNode nodeSubJDF = spawn.spawn("", null, vsRWResourceIDs, vamParts, false, true, true, false);

		JDFResource resPreview = nodeSubJDF.getResource("Preview", EnumUsage.Input, 0);

		// resPreview enthält nur "Black" !!!
	}
	/**
	 * 
	 * TODO Please insert comment!
	 * @throws Throwable
	 */
	// @Test
	//	public void testSpawnRW() throws Throwable
	//	{
	//		JDFDoc jdfDoc = new JDFParser().parseFile("/share/data/fehler/PD-42464/page.jdf");
	//
	//		JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("Qua0.P", null);
	//
	//		final VJDFAttributeMap vamParts = new VJDFAttributeMap();
	//
	//		final JDFAttributeMap amParts0 = new JDFAttributeMap();
	//
	//		amParts0.put("Run", "Run_121015_072229975_000405");
	//
	//		vamParts.add(amParts0);
	//
	//		final VString vsRWResourceIDs = new VString("RunList", null);
	//
	//		final JDFSpawn spawn = new JDFSpawn(nodeProc);
	//
	//		JDFNode nodeSubJDF = spawn.spawn(null, null, vsRWResourceIDs, vamParts, true, true, true, false);
	//		nodeSubJDF.getOwnerDocument_JDFElement().write2File("/data/JDF/Out.Spawned.spawn.jdf", 2, false);
	//		String strOutJDFPath = "/data/JDF/Out.Spawned.MAIN.jdf";
	//		jdfDoc.write2File(strOutJDFPath, 2, false);
	//
	//		// Link_110412_072920686_018182
	//	}

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