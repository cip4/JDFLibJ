//Titel:        JDF TestApplication
//Version:
//Copyright:    Copyright (c) 2002
//Autor:        Sabine Jonas, sjonas@topmail.de, Dietrich Mucha
//Firma:        BU/GH Wuppertal
//Beschreibung: first Applications using the JDFLibrary
//package testNew;

package org.cip4.jdflib;

import java.io.File;
import java.io.FileNotFoundException;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.elementwalker.SizeWalker;

public class TestJDF
{
	private static final String SEPARATOR = File.separator; // "/"; //
	static protected final String sm_dirTestSchema = ".." + SEPARATOR + "schema" + SEPARATOR + "Version_1_3" + SEPARATOR;
	static protected final String sm_dirTestData = "O:\\jdflibj\\test" + SEPARATOR + "data" + SEPARATOR;
	static protected final String sm_dirTestDataTemp = sm_dirTestData + "temp" + SEPARATOR;

	public static void main(final String[] argv)
	{
		final SizeWalker sw;
		try
		{
			sw = new SizeWalker(new File("size.txt"));
			final JDFDoc jdfDoc = new JDFParser().parseFile("Bene.jdf");
			sw.walkAll(jdfDoc.getRoot());
		}
		catch (final FileNotFoundException e)
		{
			//
		}

	}
}