
//Titel:        JDF TestApplication
//Version:
//Copyright:    Copyright (c) 2002
//Autor:        Sabine Jonas, sjonas@topmail.de, Dietrich Mucha
//Firma:        BU/GH Wuppertal
//Beschreibung: first Applications using the JDFLibrary
//package testNew;

package org.cip4.jdflib;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.MimeUtil;
import org.cip4.jdflib.util.StringUtil;

public class TestJDF
{
    private static final String SEPARATOR = File.separator; // "/"; //
    static protected final String sm_dirTestSchema   = ".." + SEPARATOR + "schema" + SEPARATOR + "Version_1_3" + SEPARATOR;
    static protected final String sm_dirTestData     = "O:\\jdflibj\\test" + SEPARATOR + "data" + SEPARATOR;
    static protected final String sm_dirTestDataTemp = sm_dirTestData + "temp" + SEPARATOR;

    
    public static void main(String[] argv)
    {         
//        JDFParser p=new JDFParser();
//        JDFDoc d=p.parseFile("canon1.xml");
//        
//        JDFDeviceCap dc=(JDFDeviceCap) d.getRoot().getChildByTagName("DeviceCap", null, 0, null, false, true);
//        dc.createModuleCaps(".*Params");
//        d.write2File("canon2.jdf", 2, false);
//        XMLDoc d2=d.write2URL("http://localhost:8080/JDFUtility/Bambi/a", "text/xml");
//        d2=d.write2URL("http://localhost:8080/JDFUtility/Bambi/dc");
//        System.out.print(d2==null ? "null doc" : d2.toString());
//        JDFDoc dj=new JDFDoc("JDF");
//        d2=dj.write2URL("http://localhost:8080/JDFUtility/Bambi/ddd");
//        Multipart mp=MimeUtil.buildMimePackage(d, dj);
//        try
//        {
//            HttpURLConnection uc=MimeUtil.writeToURL(mp, "http://localhost:8080/JDFUtility/Bambi/ccc");
//            System.out.print(uc.getResponseCode());
//            System.out.print(uc.getResponseMessage());
//            final InputStream inStream = uc.getInputStream();
//            FileUtil.streamToFile(inStream, "response.txt");
//
//            MimeUtil.writeToFile(mp, "test.mjm");
//            
//        }
//        catch (IOException x)
//        {
//            // TODO Auto-generated catch block
//            x.printStackTrace();
//        }
//        catch (MessagingException x)
//        {
//            // TODO Auto-generated catch block
//            x.printStackTrace();
//        }
//        JDFDoc dj=new JDFDoc("JMF");
//        JDFJMF jmf=dj.getJMFRoot();
//        jmf.appendQuery(EnumType.KnownMessages);
//        jmf.appendQuery(EnumType.Events);
//        JDFDoc d2=dj.write2URL("http://localhost:8080/JDFUtility/Bambi/jmfTest");
//        if(d2!=null)
//            d2.write2File("jmfresp.xml", 2, false);
//        else
//            System.out.println("null d");
  
        JDFDoc d1=new JDFDoc("JMF");
        d1.setOriginalFileName("JMF.jmf");
        JDFJMF jmf=d1.getJMFRoot();
        JDFCommand com=(JDFCommand) jmf.appendMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.SubmitQueueEntry);
        com.appendQueueSubmissionParams().setURL("cid:TheJDF");
        
        JDFDoc doc=new JDFDoc("JDF");
        doc.setOriginalFileName("JDF.jdf");  
        JDFNode n=doc.getJDFRoot();
        n.setType(EnumType.ColorSpaceConversion);
        JDFColorSpaceConversionParams cscp=(JDFColorSpaceConversionParams) n.addResource(ElementName.COLORSPACECONVERSIONPARAMS, null, EnumUsage.Input, null, null, null, null);
        JDFFileSpec fs0=cscp.appendFinalTargetDevice();
        JDFRunList rl=(JDFRunList)n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
        try
        {
            fs0.setURL(StringUtil.uncToUrl(sm_dirTestData+File.separator+"test.icc",true));
            rl.addPDF(StringUtil.uncToUrl(sm_dirTestData+File.separator+"url1.pdf",false), 0, -1);
        }
        catch (MalformedURLException x)
        {
//
            }
        Multipart m=MimeUtil.buildMimePackage(d1,doc);
        try
        {
            MimeUtil.writeToFile(m, sm_dirTestDataTemp+"/Bambitest.mjm");
            HttpURLConnection uc=MimeUtil.writeToURL(m, "http://localhost:8080/Bambi/jmf/device001/");
            FileUtil.streamToFile(uc.getInputStream(), sm_dirTestDataTemp+"/BambiIn.txt");
            FileUtil.streamToFile(uc.getErrorStream(), sm_dirTestDataTemp+"/BambiErr.txt");
        }
        catch (IOException x)
        {
            // TODO Auto-generated catch block
            x.printStackTrace();
        }
        catch (MessagingException x)
        {
            // TODO Auto-generated catch block
            x.printStackTrace();
        }

    }
}