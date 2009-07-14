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
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFExposedMedia;

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
		final JDFDoc d = new JDFParser().parseFile("C:\\data\\JDF\\outout.jdf");
		d.write2File("C:\\data\\JDF\\Collapse\\data_VorCollapse.jdf", 0, true);
		final JDFNode root = d.getJDFRoot();
		final JDFResourceLink rl = root.getLink(0, "ExposedMedia", null, null);
		final JDFExposedMedia xm = (JDFExposedMedia) rl.getLinkRoot();
		final VElement v = xm.getLeaves(true);
		for (int i = 0; i < v.size(); i++)
		{
			final JDFResource leaf = (JDFResource) v.get(i);
			leaf.updateAmounts(-1);
		}
		d.write2File("C:\\data\\JDF\\outout2.jdf", 0, true);

	}
}