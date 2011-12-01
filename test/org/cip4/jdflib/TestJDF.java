//Titel:        JDF TestApplication
//Version:
//Copyright:    Copyright (c) 2002
//Autor:        Sabine Jonas, sjonas@topmail.de, Dietrich Mucha
//Firma:        BU/GH Wuppertal
//Beschreibung: first Applications using the JDFLibrary
//package testNew;

package org.cip4.jdflib;

import java.io.File;
import java.io.InputStream;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFResourceCmdParams;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumCleanUpMerge;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumAmountMerge;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.JDFMerge;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlPart;
import org.cip4.jdflib.util.UrlUtil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * < July 9, 2009
 */
public class TestJDF extends JDFTestCaseBase
{
	private static final String SEPARATOR = File.separator; // "/"; //
	static protected final String sm_dirTestSchema = ".." + SEPARATOR + "schema" + SEPARATOR + "Version_1_3" + SEPARATOR;
	static protected final String sm_dirTestData = "O:\\jdflibj\\test" + SEPARATOR + "data" + SEPARATOR;
	static protected final String sm_dirTestDataTemp = sm_dirTestData + "temp" + SEPARATOR;

	/**
	 * @param argv
	 */
	public static void main(final String[] argv)
	{
		testCollapse();
		//testSpawn2();
	}

	/**
	 * 
	 */
	public static void testCollapse()
	{
		final JDFDoc d = new JDFParser().parseFile("/share/data/JDF/UlfPrien/jdfportal.jdf");
		final JDFNode n = d.getJDFRoot();
		JDFResource r = n.getResource("ColorantControl", EnumUsage.Input, 0);
		r.expand(true);
		d.write2File("/share/data/JDF/UlfPrien/jdfportal_new.jdf", 2, false);
		r.collapse(false, true);
		d.write2File("/share/data/JDF/UlfPrien/jdfportal_new2.jdf", 2, false);
	}

	/**
	 * 
	 * TODO Please insert comment!
	 * @throws Throwable
	 */
	public void testSpawnRW() throws Throwable
	{
		JDFDoc jdfDoc = new JDFParser().parseFile("/data/JDF/aquafitmain.jdf");

		JDFNode nodeProc = jdfDoc.getJDFRoot().getJobPart("1003.I", null);

		final VJDFAttributeMap vamParts = new VJDFAttributeMap();

		final JDFAttributeMap amParts0 = new JDFAttributeMap();

		amParts0.put("RunPage", "0");
		amParts0.put("SheetName", "Faltschachtel");
		amParts0.put("Side", "Front");
		amParts0.put("SignatureName", "SIG001");
		amParts0.put("Separation", "Magenta");

		vamParts.add(amParts0);

		final VString vsRWResourceIDs = new VString("r_110412_072923347_007064 r_110412_072919155_016691 Link_110412_072920686_018184 r_110412_072923300_007044 Link_110412_072921327_018304 r_110412_073508639_001995 r_110412_072923300_007043 r_110412_073508655_002008");

		final JDFSpawn spawn = new JDFSpawn(nodeProc);

		JDFNode nodeSubJDF = spawn.spawn("aquafit(11-0078)", JDFConstants.EMPTYSTRING, vsRWResourceIDs, vamParts, true, true, true, false);
		nodeSubJDF.getOwnerDocument_JDFElement().write2File("/data/JDF/Out.Spawned.spawn.jdf", 2);
		String strOutJDFPath = "/data/JDF/Out.Spawned.MAIN.jdf";
		jdfDoc.write2File(strOutJDFPath, 2);

		// Link_110412_072920686_018182
	}

	/**
	 * 
	 */
	public void testgetPartition()
	{
		final JDFDoc d = new JDFParser().parseFile("/share/data/JDF/StefanBartels/crap.jdf");
		final JDFNode n = d.getJDFRoot();
		JDFResource r = (n.getResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output, 0));
		JDFResource rp = r.getPartition(new JDFAttributeMap("SignatureName", "Sig0001"), null);
		assertNull(rp);
	}

	/**
	 * 
	 */
	public void testMergeAmount()
	{
		final JDFDoc d = new JDFParser().parseFile("/share/data/JDF/Jira/PD-1735/amount.jdf");
		final JDFNode n = d.getJDFRoot();
		JDFResource rr = (JDFResource) n.getChildWithAttribute(ElementName.COMPONENT, "ID", null, "PrintedPaper", 0, false);
		final VElement vr = rr.getLeaves(true);
		for (KElement r : vr)
			((JDFResource) r).updateAmounts(false);
		d.write2File("/share/data/JDF/Jira/PD-1735/amountnew.jdf", 2, false);
	}

	/**
	 * 
	 */
	public void testelementsbytag()
	{
		final JDFDoc d = new JDFParser().parseFile("/share/data/JDF/Bodo/media.jdf");
		final JDFNode n = d.getJDFRoot();
		NodeList mediaList = n.getElementsByTagName("Media");
		for (int i = 0; i < mediaList.getLength(); i++)
		{
			Node node = mediaList.item(i);
			JDFMedia media = (JDFMedia) node;
		}
		d.write2File("test.jdf", 2, false);
	}

	/**
	 * 
	 */
	public static void testSpawn2()
	{
		JDFResource.setUnpartitiondImplicit(true);
		CPUTimer ct = new CPUTimer(true);
		final JDFDoc d = new JDFParser().parseFile("/share/data/fehler/15495-PD/export.jdf");
		JDFNode n = d.getJDFRoot().getJobPart("IPr4.I", null);
		ct.stop();
		System.out.println(ct);
		JDFSpawn spawn = new JDFSpawn(n);
		ct = new CPUTimer(false);
		ct.setName("spawn");
		CPUTimer ct1 = new CPUTimer(false);
		ct1.setName("write");
		CPUTimer ct2 = new CPUTimer(false);
		ct2.setName("merge");
		spawn.vRWResources_in = new VString("Output NodeInfo", null);
		spawn.vSpawnParts = new VJDFAttributeMap();
		ct.start();
		JDFAttributeMap map = new JDFAttributeMap();
		map.put("SignatureName", "Sig001");
		map.put("SheetName", "radson_001");
		map.put("Side", "Front");
		map.put("PartVersion", "SL");
		spawn.vSpawnParts.add(map);
		JDFAttributeMap map2 = new JDFAttributeMap();
		map2.put("SignatureName", "Sig001");
		map2.put("SheetName", "radson_001");
		map2.put("Side", "Back");
		map2.put("PartVersion", "SL");
		spawn.vSpawnParts.add(map2);
		//		spawn.bSpawnIdentical = false;
		//		spawn.bFixResources = false;
		spawn.bSpawnROPartsOnly = true;
		JDFNode n2 = spawn.spawn();
		n2.getOwnerDocument_JDFElement().write2File("/share/data/fehler/15495-PD/spawn.jdf", 2, false);

	}

	public void testPing()
	{
		String s = "a";
		InputStream is = new ByteArrayIOStream(s.getBytes()).getInputStream();
		UrlPart p = UrlUtil.writeToURL("http://10.51.201.148:8010", is, UrlUtil.POST, UrlUtil.VND_JMF, null);
		//		UrlPart p = UrlUtil.writeToURL("http://10.51.206.254:8010/jmf", is, UrlUtil.POST, UrlUtil.VND_JMF, null);
		p.buffer();
	}

	public void testResourceCommand3() throws Exception
	{
		final JDFDoc jdfDoc = JDFDoc.parseFile("/data/JDF/RainerSchielke/rescmd/main.jdf");

		final JDFNode nodeRoot = jdfDoc.getJDFRoot();

		final JDFDoc jmf = JDFDoc.parseFile("/data/JDF/RainerSchielke/rescmd/cmd.jdf");

		final JDFJMF inJMF = jmf.getJMFRoot();

		final VElement veMessages = inJMF.getMessageVector(null, null);

		for (int i = 0; i < veMessages.size(); i++)
		{
			final JDFMessage msg = (JDFMessage) veMessages.elementAt(i);

			if (msg.getType().equals(JDFMessage.EnumType.Resource.getName()))
			{
				final JDFResourceCmdParams resCmdParams = msg.getResourceCmdParams(0);

				resCmdParams.applyResourceCommand(nodeRoot);
			}
		}
	}

	/**
	 * 
	 */
	public static void testSpawn()
	{
		JDFResource.setUnpartitiondImplicit(true);
		for (int ii = 0; ii < 2; ii++)
		{
			CPUTimer ct = new CPUTimer(true);
			final JDFDoc d = new JDFParser().parseFile("C:/data/JDF/Arne/export.jdf");
			JDFNode n = d.getJDFRoot().getJobPart("1001.0", null);
			ct.stop();
			System.out.println(ct);
			JDFSpawn spawn = new JDFSpawn(n);
			ct = new CPUTimer(false);
			ct.setName("spawn");
			CPUTimer ct1 = new CPUTimer(false);
			ct1.setName("write");
			CPUTimer ct2 = new CPUTimer(false);
			ct2.setName("merge");
			spawn.vRWResources_in = new VString("Output NodeInfo", null);
			spawn.vSpawnParts = new VJDFAttributeMap();
			JDFMerge m = new JDFMerge(d.getJDFRoot());
			for (int i = 1; i < 250; i++)
			{
				ct.start();
				String s = StringUtil.sprintf("%03i", "" + i);
				//		spawn.vSpawnParts = null;
				JDFAttributeMap map = new JDFAttributeMap();
				map.put("SignatureName", "Sig" + s);
				map.put("SheetName", "FB " + s);
				map.put("Side", "Front");
				if (ii == 0)
					spawn.vSpawnParts = new VJDFAttributeMap();
				spawn.vSpawnParts.add(map);
				map = new JDFAttributeMap(map);
				map.put("Side", "Back");
				spawn.vSpawnParts.add(map);
				spawn.bSpawnIdentical = false;
				spawn.bFixResources = false;
				if (ii == 0)
				{
					JDFNode n2 = spawn.spawn();
					System.out.println(ct);
					ct.stop();
					ct1.start();
					n2.getOwnerDocument_JDFElement().write2File("C:\\temp\\spawn" + s + ".jdf", 2, false);
					System.out.println(ct1);
					ct1.stop();

					ct2.start();
					n = m.mergeJDF(n2, null, EnumCleanUpMerge.RemoveAll, EnumAmountMerge.UpdateLink);
					spawn.setNode(n);
					System.out.println(ct2);
					ct2.stop();
				}

			}
			if (ii == 1)
			{
				JDFNode n2 = spawn.spawn();
				System.out.println(ct);
				ct.stop();
				ct1.start();
				n2.getOwnerDocument_JDFElement().write2File("C:\\temp\\spawnall.jdf", 2, false);
				System.out.println(ct1);
				ct1.stop();
			}
			d.write2File("C:\\temp\\main.jdf", 2, false);
		}
	}
}