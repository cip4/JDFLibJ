
//Titel:        JDF TestApplication
//Version:
//Copyright:    Copyright (c) 2002
//Autor:        Sabine Jonas, sjonas@topmail.de, Dietrich Mucha
//Firma:        BU/GH Wuppertal
//Beschreibung: first Applications using the JDFLibrary
//package testNew;

package org.cip4.jdflib;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;


public class TestJDF
{
 
    
    public static void main(String[] argv)
    {         
        JDFParser p=new JDFParser();
        JDFDoc d=p.parseFile("canon1.xml");
        
        JDFDeviceCap dc=(JDFDeviceCap) d.getRoot().getChildByTagName("DeviceCap", null, 0, null, false, true);
        dc.createModuleCaps(".*Params");
        d.write2File("canon2.jdf", 2, false);
  
    }
}