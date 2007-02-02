
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
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.node.JDFNode;


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
        JDFDoc d=p.parseFile("big.jdf");
        long ii=d.getDocMemoryUsed();
        JDFNode n=d.getJDFRoot();
        n.eraseEmptyNodes(true);
        d.write2File("big2.jdf", 0, false);
        VElement v=n.getChildrenByTagName("AuditPool", null, null, false, true, 0);
        for(int i=0;i<v.size();i++)
            v.item(i).deleteNode();
        n.eraseEmptyNodes(true);
        d.write2File("big3.jdf", 0, false);
  
    }
}