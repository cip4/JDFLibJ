
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
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource;

public class TestJDF
{
    private static final String SEPARATOR = File.separator; // "/"; //
    static protected final String sm_dirTestSchema   = ".." + SEPARATOR + "schema" + SEPARATOR + "Version_1_3" + SEPARATOR;
    static protected final String sm_dirTestData     = "O:\\jdflibj\\test" + SEPARATOR + "data" + SEPARATOR;
    static protected final String sm_dirTestDataTemp = sm_dirTestData + "temp" + SEPARATOR;


    public static void main(String[] argv)
    {         
         JDFDoc jdfDoc = JDFDoc.parseFile ("C:/data/vers.jdf");
        
        JDFResource res = jdfDoc.getJDFRoot().getTargetResource ("Link071112_104319650_000411");
        
        JDFAttributeMap amParts = new JDFAttributeMap ();
        
        amParts.put ("Side", "Front");
        amParts.put ("PartVersion", "Eng");
        amParts.put ("SheetName", "S1B");
        amParts.put ("SignatureName", "Sig002");
       
        JDFResource part = res.getPartition (amParts, JDFResource.EnumPartUsage.Implicit);

        
//        final JDFAttributeMap mpart = 
    	part.getPartMap ();
// mpart ist leer, warum ???
    }
}