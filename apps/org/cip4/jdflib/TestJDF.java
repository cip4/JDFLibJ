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
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;

public class TestJDF
{
	private static final String SEPARATOR = File.separator; // "/"; //
	static protected final String sm_dirTestSchema = ".." + SEPARATOR + "schema" + SEPARATOR + "Version_1_3" + SEPARATOR;
	static protected final String sm_dirTestData = "O:\\jdflibj\\test" + SEPARATOR + "data" + SEPARATOR;
	static protected final String sm_dirTestDataTemp = sm_dirTestData + "temp" + SEPARATOR;

	public static void main(final String[] argv)
	{
		final JDFDoc d = new JDFParser().parseFile("C:\\data\\JDF\\Collapse\\data_OhneCollapse.jdf");
		d.write2File("C:\\data\\JDF\\Collapse\\data_VorCollapse.jdf", 0, true);
		final JDFNode root = d.getJDFRoot();
		final VElement vJDF = root.getvJDFNode(null, null, false);
		for (int i = 0; i < vJDF.size(); i++)
		{
			final JDFNode n = (JDFNode) vJDF.get(i);
			final VElement v = n.getResourcePool().getPoolChildren(null, null, null);
			for (int j = 0; j < v.size(); j++)
			{
				System.out.println(i + " " + j + " " + System.currentTimeMillis());
				final JDFResource r = (JDFResource) v.get(j);
				r.collapse(false, true);
				System.out.println(i + " " + j + " " + r.getLocalName() + " " + r.getID() + " " + System.currentTimeMillis());
			}
		}
		d.write2File("C:\\data\\JDF\\Collapse\\data_NachCollapse.jdf", 0, true);

	}
}