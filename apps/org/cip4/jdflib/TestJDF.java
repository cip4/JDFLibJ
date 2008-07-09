
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
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;

public class TestJDF
{
    private static final String SEPARATOR = File.separator; // "/"; //
    static protected final String sm_dirTestSchema   = ".." + SEPARATOR + "schema" + SEPARATOR + "Version_1_3" + SEPARATOR;
    static protected final String sm_dirTestData     = "O:\\jdflibj\\test" + SEPARATOR + "data" + SEPARATOR;
    static protected final String sm_dirTestDataTemp = sm_dirTestData + "temp" + SEPARATOR;


    public static void main(String[] argv)
    {         
         JDFDoc jdfDoc = new JDFParser().parseFile("82.jdf");
         JDFNode root=jdfDoc.getJDFRoot();
         JDFNode n=root.getJobPart("SG1.0.I", null);
         VElement vL=n.getResourceLinks(new JDFAttributeMap("Usage","Input"));
         for(int i=0;i<vL.size();i++)
         {
             JDFResourceLink rl=(JDFResourceLink) vL.get(i);
//             JDFResourceLink rl=(JDFResourceLink) n.getResourceLinkPool().getChildWithAttribute("PreviewLink", "rRef", null, "r080707_024519175_000439", 0, true);
         VJDFAttributeMap v=n.getExecutablePartitions(rl, EnumResStatus.Available, true);
         System.out.print(rl.getrRef()+" "+v);
         }
        
  // mpart ist leer, warum ???
    }
}