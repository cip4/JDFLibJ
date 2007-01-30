
//Titel:        JDF TestApplication
//Version:
//Copyright:    Copyright (c) 1999
//Autor:       Sabine Jonas, sjonas@topmail.de
//Firma:      BU/GH Wuppertal
//Beschreibung:  first Applications using the JDFLibrary
//package testApps;

package org.cip4.jdflib;

import java.io.File;
import java.util.Vector;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.MyArgs;
import org.cip4.jdflib.util.StringUtil;

public class SpawnJDF
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
    
    public static void main(String argv[])
    {   
        // -i bookintent.jdf -o spawned.jdf -p 4
        // -i bookintent.jdf -o spawned.jdf -p 0 -w r0007
        // -iic2.jdf -ospawnic.jdf -pp1 -wOutput
        String prg = "SpawnJDF" + ":\t";
        System.out.print(prg + "START.");

        MyArgs args = new MyArgs(argv, "adev", "iopkwt",null);
        System.out.println("MyArgs-args:" + args);

        String usage =
            "SpawnJDF: JDF spawn processor;\n"
                + "-i input JDF;\n"
                + "-o output JDF;\n"
                + "-p jobpartId;\n"
                + "-k keys comma separated list of part keys\n"
                + "-w spawn global resources rw(default=ro)\n"
                + "-d delete node from original file";

        boolean doEscapes = args.boolParameter('e' + JDFConstants.EMPTYSTRING, false);
        System.out.println(prg + "e: doEscapes=" + doEscapes);

        boolean useVDOMParser = args.boolParameter('v' + JDFConstants.EMPTYSTRING, false);
        System.out.println(prg + "v: useVDOMParser=" + useVDOMParser);

        
        VString vRWResources = new VString(StringUtil.tokenize(args.parameter('w'), ",", false));
        System.out.println(prg + "w: resRW=" + args.parameter('w'));

        //-//HDM//-//Check command line and extract arguments.
        String xmlFile = args.parameter('i');
        System.out.println(prg + "i: xmlFile=" + xmlFile);

        if (!fileExists(xmlFile))
        {
            System.err.println(args.usage(usage));
            System.exit(1);
        }

        String outFile = args.parameter('o');
        System.out.println(prg + "o: outFile=" + outFile);

        String task = args.parameter('t');
        System.out.println(prg + "t: task=" + task);

        boolean v = args.boolParameter('v' + JDFConstants.EMPTYSTRING, false);
        System.out.println(prg + "v: =" + v);

        String strPartID = args.parameter('p');
        System.out.println(prg + "p: =" + strPartID);

        // start spawning
        // ==============
        JDFParser p = new JDFParser();
        JDFDoc docIn = p.parseFile(xmlFile);

        if (docIn == null)
        {
            System.err.println(args.usage(""));
            System.exit(1);
        }
        else
        {
 
            JDFNode rootIn = (JDFNode) docIn.getRoot();

            JDFNode pCut;
            if (strPartID.equals(""))
            {
                pCut = rootIn;
            }
            else
            {
                pCut = rootIn.getJobPart(strPartID, "");
            }
            
            if (pCut == null)
            {
                System.err.println("No such JobPartID: " + strPartID);
                System.exit(1);
            }
            else
            {
                VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();
                JDFAttributeMap part1 = new JDFAttributeMap();
                Vector partKeys = StringUtil.tokenize(args.parameter('k'), ",", false);
                for (int iKey = 1; iKey < partKeys.size(); iKey += 2)
                {
                    part1.put(
                        (String) partKeys.elementAt(iKey - 1), (String) partKeys.elementAt(iKey));
                }
                
                vSpawnParts.add(part1);
                final JDFSpawn spawn=new JDFSpawn(pCut);

                JDFNode node = spawn.spawn(xmlFile,outFile,vRWResources,vSpawnParts,false,true,true,true);

                // neu gespawntes File rausschreiben
                JDFNode rootOut = node;
                XMLDoc docOut = rootOut.getOwnerDocument_KElement();
                docOut.write2File(outFile, 0, true);

                // verändertes Ausgangsfile rausschreiben
                String strOutXMLFile = "_" + xmlFile;
                rootIn.eraseEmptyNodes(true);
                docIn.write2File(strOutXMLFile, 2, true);

                System.out.print(prg + "ENDE.");
                System.exit(0);
            }
        }
    }
}
