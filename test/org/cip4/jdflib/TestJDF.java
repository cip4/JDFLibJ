//Titel:        JDF TestApplication
//Version:
//Copyright:    Copyright (c) 2002
//Autor:        Sabine Jonas, sjonas@topmail.de, Dietrich Mucha
//Firma:        BU/GH Wuppertal
//Beschreibung: first Applications using the JDFLibrary
//package testNew;

package org.cip4.jdflib;

import java.io.File;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumCleanUpMerge;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumAmountMerge;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.CPUTimer;
import org.cip4.jdflib.util.JDFMerge;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.StringUtil;

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
		//		testCollapse();
		testSpawn2();
	}

	/**
	 * 
	 */
	private static void testCollapse()
	{
		final JDFDoc d = new JDFParser().parseFile("/share/data/data.jdf");
		final JDFNode n = d.getJDFRoot();
		JDFRunList r = (JDFRunList) n.getResource("RunList", EnumUsage.Input, 1);
		r.getLayoutElement().deleteNode();
		r.collapse(false, true);

		d.write2File("C:/data/jdf/signa/collapse/data2.jdf", 2, false);
	}

	/**
	 * 
	 */
	public void testgetPartition()
	{
		final JDFDoc d = new JDFParser().parseFile("/share/data/data.jdf");
		final JDFNode n = d.getJDFRoot();
		JDFResource r = (n.getResource(ElementName.COLORANTCONTROL, EnumUsage.Input, 0));
		JDFResource rp = r.getPartition(new JDFAttributeMap("SignatureName", "Sig001"), null);
		assertNull(rp);
	}

	/**
	 * 
	 */
	public static void testSpawn2()
	{
		JDFResource.setUnpartitiondImplicit(true);
		CPUTimer ct = new CPUTimer(true);
		final JDFDoc d = new JDFParser().parseFile("/data/JDF/2011_01052/main.jdf");
		JDFNode n = d.getJDFRoot().getJobPart("ImP1.MR", null);
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
		map.put("SheetName", "A_FB 001");
		map.put("PartVersion", "All");
		spawn.vSpawnParts.add(map);
		JDFAttributeMap map2 = new JDFAttributeMap();
		map2.put("SignatureName", "Sig002");
		map2.put("SheetName", "A_FB 002");
		map2.put("PartVersion", "All");
		spawn.vSpawnParts.add(map2);
		//		spawn.bSpawnIdentical = false;
		//		spawn.bFixResources = false;
		spawn.spawn();

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