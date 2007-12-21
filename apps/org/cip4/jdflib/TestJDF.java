
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
         JDFDoc jdfDoc = JDFDoc.parseFile ("C:/data/sub.jdf");
         assert(jdfDoc.getJDFRoot()==jdfDoc.getRoot());
        
  // mpart ist leer, warum ???
    }
}