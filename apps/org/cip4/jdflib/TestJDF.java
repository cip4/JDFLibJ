
//Titel:        JDF TestApplication
//Version:
//Copyright:    Copyright (c) 2002
//Autor:        Sabine Jonas, sjonas@topmail.de, Dietrich Mucha
//Firma:        BU/GH Wuppertal
//Beschreibung: first Applications using the JDFLibrary
//package testNew;

package org.cip4.jdflib;

import java.io.UnsupportedEncodingException;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;


public class TestJDF
{
 
    
    public static void main(String[] argv)
    {         
        String test="<ü>\n";
        try
        {
            int uLength = test.getBytes("UTF-8").length;
            System.out.println(uLength);
            System.out.println(test.getBytes("UTF-8")[2]);
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JDFParser p=new JDFParser();
        JDFDoc d=p.parseFile("0.xml");
        d.write2File("big2.jdf", 2, false);
  
    }
}