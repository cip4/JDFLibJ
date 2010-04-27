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
		final JDFDoc d = new JDFParser().parseFile("C:\\temp\\IdenticalPlate.jdf");
		final JDFNode n = d.getJDFRoot().getJobPart("ImO2.I", null);

		JDFSpawn spawn = new JDFSpawn(n);
		spawn.vRWResources_in = new VString("Preview NodeInfo ExposedMedia", null);
		//		spawn.vSpawnParts = null;
		JDFAttributeMap map = new JDFAttributeMap();
		map.put("SignatureName", "Sig003");
		map.put("PartVersion", "Engl Franz");
		map.put("SheetName", "FB 003");
		map.put("Side", "Front");
		map.put("Separation", "Black");
		spawn.vSpawnParts = new VJDFAttributeMap();
		spawn.vSpawnParts.add(map);
		map = new JDFAttributeMap(map);
		map.put("Separation", "Yellow");
		spawn.vSpawnParts.add(map);
		spawn.bSpawnIdentical = false;
		spawn.bFixResources = false;
		JDFNode n2 = spawn.spawn();
		n2.getOwnerDocument_JDFElement().write2File("C:\\temp\\spawn.jdf", 2, false);
	}
}