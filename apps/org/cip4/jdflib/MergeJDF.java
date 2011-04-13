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
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JDFMerge;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.MyArgs;

public class MergeJDF
{
	private static boolean fileExists(String name)
	{
		boolean exists = false;

		File file = new File(name);
		if (file.length() != 0.0)
		{
			exists = file.exists();
		}

		return exists;
	}

	public static void main(String[] argv)
	{ // job.jdf subjdf.jdf -o merged.jdf
		// _ic2.jdf spawnic.jdf -oMergeic2.jdf
		String prg = "MergeJDF" + ":\t";
		System.out.print(prg + "START.");

		String usage = "MergeJDF: JDF merger simulation;\n" + "Arguments: 1=parent input JDF, 2=child input JDF;\n" + "-o: output JDF;\n" + ""
				+ "-d: delete completed tasks from the output JDF\n";
		MyArgs args = new MyArgs(argv, "dev", "ou", null);
		System.out.println("MyArgs-args:" + args);

		String xmlFile1 = argv[0];
		System.out.println(prg + "[0]: xmlFile1=" + xmlFile1);
		String xmlFile2 = argv[1];
		System.out.println(prg + "[1]: xmlFile2=" + xmlFile2);

		String unspawn = args.parameter('u');
		System.out.println(prg + "u: " + unspawn);

		if (!fileExists(xmlFile1) || (!fileExists(xmlFile2) && unspawn.equals("")))
		{
			System.err.println(args.usage(usage + ": one File does not exist"));
			System.exit(1);
		}

		// testen der Konstruktoren
		// KElement kElem = new KElement ((KNode)null);

		String outFile = args.parameter('o');
		System.out.println(prg + "o: outFile=" + outFile);

		boolean v = args.boolParameter('v');
		System.out.println(prg + "v: " + v);

		JDFParser p = new JDFParser();
		JDFDoc gd = p.parseFile(xmlFile1);
		JDFNode root = null;

		if (gd == null)
		{
			System.err.println(args.usage(xmlFile1 + ": Parse Error"));
			System.exit(2);
		}
		else
		{
			root = (JDFNode) gd.getRoot();

			if (unspawn != null)
			{
				new JDFSpawn(root).unSpawn(unspawn);
			}
			else
			{
				JDFParser p2 = new JDFParser();
				JDFDoc gd2 = p2.parseFile(xmlFile2);
				JDFNode root2 = null;

				if (gd2 == null)
				{
					System.err.println(args.usage(xmlFile2 + ": Parse Error"));
					System.exit(2);
				}
				else
				{
					root2 = (JDFNode) gd2.getRoot();
				}

				try
				{
					new JDFMerge(root).mergeJDF(root2, xmlFile2, JDFNode.EnumCleanUpMerge.None, JDFResource.EnumAmountMerge.UpdateLink);
				}
				catch (JDFException toCatch)
				{
					System.err.println("Error during JDF-Merge.\n" + "  Exception message is " + toCatch.getMessage());
					System.exit(3);
				}
			}

			gd.write2File(outFile, 2, true);

			System.out.print(prg + "ENDE.");
			System.exit(0);
		}
	}
}