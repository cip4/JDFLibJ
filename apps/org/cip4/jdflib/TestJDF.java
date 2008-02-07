
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
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;

public class TestJDF
{
    private static final String SEPARATOR = File.separator; // "/"; //
    static protected final String sm_dirTestSchema   = ".." + SEPARATOR + "schema" + SEPARATOR + "Version_1_3" + SEPARATOR;
    static protected final String sm_dirTestData     = "O:\\jdflibj\\test" + SEPARATOR + "data" + SEPARATOR;
    static protected final String sm_dirTestDataTemp = sm_dirTestData + "temp" + SEPARATOR;


    public static void main(String[] argv)
    {         
         JDFDoc jdfDoc = JDFDoc.parseFile ("C:/data/junk/vj.jdf");

         JDFNode n=jdfDoc.getJDFRoot();
         VJDFAttributeMap vm=n.getAncestorPool().getPartMapVector();
         for(int i=0;i<vm.size();i++){
             System.out.println(i+" "+vm.elementAt(i)+n.getPartStatus(vm.elementAt(i)));
         }
         
        
  // mpart ist leer, warum ???
    }
}