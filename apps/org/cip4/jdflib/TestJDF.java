//Titel:        JDF TestApplication
//Version:
//Copyright:    Copyright (c) 2002
//Autor:        Sabine Jonas, sjonas@topmail.de, Dietrich Mucha
//Firma:        BU/GH Wuppertal
//Beschreibung: first Applications using the JDFLibrary
//package testNew;

package org.cip4.jdflib;

import java.io.File;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.JDFSpawn;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * < July 9, 2009
 */
public class TestJDF
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
		final JDFDoc d = new JDFParser().parseFile("C:\\data\\jdf\\JanHoppe\\prepartRed.jdf");
		final JDFNode n = d.getJDFRoot().getJobPart("ImO1.I", null);
		JDFResourceLink rl = n.getLink(0, "Preview", null, null);
		VElement v = rl.getTargetVector(0);

		JDFSpawn spawn = new JDFSpawn(n);
		spawn.vRWResources_in = new VString("Preview", null);
		spawn.vSpawnParts = new VJDFAttributeMap();
		JDFAttributeMap map = new JDFAttributeMap();
		spawn.vSpawnParts.add(map);
		map.put("SignatureName", "Sig001");
		map.put("PartVersion", "NL FR EN");
		map.put("SheetName", "Omslag_FB 001");
		map.put("Side", "Front");
		JDFNode n2 = spawn.spawn();
		n2.getOwnerDocument_JDFElement().write2File("C:\\data\\jdf\\JanHoppe\\prepartRed_spawn.jdf", 2, false);
		d.write2File("C:\\data\\jdf\\JanHoppe\\prepartRed_main.jdf", 2, false);
	}
}