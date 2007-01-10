/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of 
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of 
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of 
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration 
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software 
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */

/*
 * @author muchadie
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.cip4.jdflib.JDFTestCaseBase;
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


public class MimeUtilTest extends JDFTestCaseBase
{
    public void testBuildMimePackageDocJMF() throws Exception
    {
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
        fs0.setURL(StringUtil.uncToUrl(sm_dirTestData+fileSeparator+"test.icc",true));
        JDFRunList rl=(JDFRunList)n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
        rl.addPDF(StringUtil.uncToUrl(sm_dirTestData+fileSeparator+"url1.pdf",false), 0, -1);
        Multipart m=MimeUtil.buildMimePackage(d1,doc);
        MimeUtil.writeToFile(m, sm_dirTestDataTemp+fileSeparator+"testMimePackageDoc.mjm");
        
    }
    
    public void testGetPartByCID() throws Exception
    {
        testBuildMimePackageDocJMF();
        Multipart mp=MimeUtil.getMultiPart(sm_dirTestDataTemp+fileSeparator+"testMimePackageDoc.mjm");
        BodyPart bp=MimeUtil.getPartByCID(mp, "TheJDF");
        assertNotNull(bp);
        assertEquals(bp.getFileName(), "JDF.jdf");
    }
    
    public void testGetContentID() throws Exception
    {
        testBuildMimePackageDocJMF();
        Multipart mp=MimeUtil.getMultiPart(sm_dirTestDataTemp+fileSeparator+"testMimePackageDoc.mjm");
        BodyPart bp=MimeUtil.getPartByCID(mp, "TheJDF");
        assertNotNull(bp);
        assertEquals(bp.getFileName(), "JDF.jdf");
        assertEquals(MimeUtil.getContentID(bp), "TheJDF");
        MimeUtil.setContentID(bp,  "TheJDF");
        assertEquals(MimeUtil.getContentID(bp), "TheJDF");
        MimeUtil.setContentID(bp,  "<TheJDF>");
        assertEquals(MimeUtil.getContentID(bp), "TheJDF");
        MimeUtil.setContentID(bp,  "<cid:TheJDF>");
        assertEquals(MimeUtil.getContentID(bp), "TheJDF");
    }
    
    public void testGetMultiPart() throws Exception
    {
        testBuildMimePackageDocJMF();
        Multipart mp=MimeUtil.getMultiPart(sm_dirTestDataTemp+fileSeparator+"testMimePackageDoc.mjm");
        MimeUtil.writeToFile(mp, sm_dirTestDataTemp+fileSeparator+"testMimePackageDoc_out.mjm");
    }
    
    public void testGetCreatePartByCID() throws Exception
    {
        Message message = new MimeMessage((Session)null);
        Multipart multipart = new MimeMultipart("related"); // JDF: multipart/related
        message.setContent(multipart);
        BodyPart bp=MimeUtil.getCreatePartByCID(multipart, "cid1");
        bp.setContent("boo","Text/plain");
        BodyPart bp2=MimeUtil.getCreatePartByCID(multipart, "cid2");
        bp2.setContent("bar","Text/plain");
        BodyPart bp3=MimeUtil.getCreatePartByCID(multipart, "cid1");
        assertEquals(bp, bp3);
        assertEquals(multipart.getCount(), 2);
        assertEquals(bp3.getContent(), "boo");
    }
    
    public void testGetJDFDoc() throws Exception
    {
        testBuildMimePackageDocJMF();
        Multipart mp=MimeUtil.getMultiPart(sm_dirTestDataTemp+fileSeparator+"testMimePackageDoc.mjm");
        BodyPart bp=MimeUtil.getPartByCID(mp, "TheJDF");
        assertNotNull(bp);
        BodyPart bp2=MimeUtil.getPartByCID(mp, "CID:thejdf");
        assertEquals(bp, bp2);
        BodyPart bp3=MimeUtil.getPartByCID(mp, "<cid:thejdf>");
        assertEquals(bp, bp3);
        assertEquals(bp.getFileName(), "JDF.jdf");
        JDFDoc d=MimeUtil.getJDFDoc(bp);
        assertNotNull(d);
        JDFNode n=d.getJDFRoot();
        assertNotNull(n);
    }
 
    public void testBuildMimePackageDoc() throws Exception
    {
        JDFDoc doc=new JDFDoc("JDF");
        doc.setOriginalFileName("JDF.jdf");  
        JDFNode n=doc.getJDFRoot();
        n.setType(EnumType.ColorSpaceConversion);
        JDFColorSpaceConversionParams cscp=(JDFColorSpaceConversionParams) n.addResource(ElementName.COLORSPACECONVERSIONPARAMS, null, EnumUsage.Input, null, null, null, null);
        JDFFileSpec fs0=cscp.appendFinalTargetDevice();
        fs0.setURL(StringUtil.uncToUrl(sm_dirTestData+fileSeparator+"test.icc",true));
        JDFRunList rl=(JDFRunList)n.addResource(ElementName.RUNLIST, null, EnumUsage.Input, null, null, null, null);
        rl.addPDF(StringUtil.uncToUrl(sm_dirTestData+fileSeparator+"url1.pdf",false), 0, -1);
        rl.addPDF(StringUtil.uncToUrl(sm_dirTestData+fileSeparator+"url1.pdf",false), 0, -1);
        rl.addPDF(StringUtil.uncToUrl(sm_dirTestData+fileSeparator+"url2.pdf",false), 0, -1);
        Multipart m=MimeUtil.buildMimePackage(null,doc);
        
        final String mimeFile = sm_dirTestDataTemp+fileSeparator+"testMimePackageDoc.mjm";
        MimeUtil.writeToFile(m, mimeFile);
        
        Multipart mp=MimeUtil.getMultiPart(mimeFile);
        assertEquals("JDF, 2* rl, 1 icc",mp.getCount(), 4);
     }
    
    public void testUpdateXMLMultipart() throws Exception
    {
        Message message = new MimeMessage((Session)null);
        Multipart multipart = new MimeMultipart("related"); // JDF: multipart/related
        message.setContent(multipart);
        JDFDoc jDoc=new JDFDoc("JDF");
        jDoc.setOriginalFileName("jdf1.jdf");
        MimeUtil.updateXMLMultipart(multipart, jDoc, null);
        final String mimeFile = sm_dirTestDataTemp+fileSeparator+"testUpdateXML";
        MimeUtil.writeToFile(multipart, mimeFile+"1.mjm");
        
        JDFDoc jDoc2=new JDFDoc("JDF");
        jDoc2.setOriginalFileName("jdf1.jdf");
        jDoc2.getJDFRoot().setDescriptiveName("updated jdf");
        MimeUtil.updateXMLMultipart(multipart, jDoc2, "jdf1.jdf");
        MimeUtil.writeToFile(multipart, mimeFile+"2.mjm");
        
        Multipart multipart3=MimeUtil.getMultiPart(mimeFile+"2.mjm");
        jDoc2.getJDFRoot().setDescriptiveName("3rd jdf");
        MimeUtil.updateXMLMultipart(multipart3, jDoc2, "jdf2.jdf");
        MimeUtil.writeToFile(multipart3, mimeFile+"3.mjm");
       
    }
        
    public void testReplaceContents() throws Exception
        {
        JDFDoc doc=new JDFDoc("JDF");
        doc.setOriginalFileName("JDF.jdf");  
        JDFNode n=doc.getJDFRoot();
        n.setType(EnumType.ColorSpaceConversion);
        JDFColorSpaceConversionParams cscp=(JDFColorSpaceConversionParams) n.addResource(ElementName.COLORSPACECONVERSIONPARAMS, null, EnumUsage.Input, null, null, null, null);
        JDFFileSpec fs0=cscp.appendFinalTargetDevice();
        fs0.setURL(StringUtil.uncToUrl(sm_dirTestData+fileSeparator+"test.icc",true));
        Multipart mp=MimeUtil.buildMimePackage(null,doc);
        assertEquals("JDF,  1 icc",mp.getCount(), 2);

       
        BodyPart bp=mp.getBodyPart(1);
        assertNotNull(bp);
        String newContents="hello World";
        bp.setContent(newContents, "text/plain");
        
        BodyPart bp2=mp.getBodyPart(1);
        Object o=bp2.getContent();
        assertEquals(o.toString(), "hello World");
        
        final String mimeFile = sm_dirTestDataTemp+fileSeparator+"testReplaceContents";
        MimeUtil.writeToFile(mp, mimeFile+".mjm");
        
        Multipart mp2=MimeUtil.getMultiPart(mimeFile+".mjm");
        BodyPart bp21=mp2.getBodyPart(1);
        Object o2=bp21.getContent();
        assertEquals(o2.toString(), "hello World");
        
        BodyPart bp22=mp2.getBodyPart(1);
        assertNotNull(bp22);
        String newContents2="bye World";
        bp22.setContent(newContents2, "text/plain");
        Object o22=bp22.getContent();
        assertEquals(o22.toString(), "bye World");
        BodyPart bp23=mp2.getBodyPart(1);
        assertEquals(bp22,bp23);


        MimeUtil.writeToFile(mp2, mimeFile+"_1.mjm");
        Multipart mp3=MimeUtil.getMultiPart(mimeFile+"_1.mjm");
        BodyPart bp31=mp3.getBodyPart(1);
        Object o3=bp31.getContent();
        assertEquals(o3.toString(), "bye World");
      
     }
    
    /////////////////////////////////////////////////////
    
    public void testBuildMimePackage() throws Exception
    {
        JDFDoc d1=new JDFDoc("JMF");
        d1.setOriginalFileName("JMF.jmf");
        JDFDoc d2=new JDFDoc("JDF");
        d2.setOriginalFileName("JDF.jdf");
        Vector vXMLDocs=new Vector();
        vXMLDocs.add(d1);
        vXMLDocs.add(d2);
        
        Multipart m=MimeUtil.buildMimePackage(vXMLDocs);
        final File file = MimeUtil.writeToFile(m,sm_dirTestDataTemp+fileSeparator+"test.mjm");
        
        FileInputStream fis=new FileInputStream(file);
        BodyPart[] aBp=MimeUtil.extractMultipartMime(fis);
        assertEquals(aBp.length, 2);
        
        assertEquals(aBp[0].getFileName(), "JMF.jmf");
        assertEquals(aBp[1].getFileName(), "JDF.jdf");
    }
    
    ///////////////////////////////////////////////////////
    
    public void testWriteToDir() throws Exception
    {
        testBuildMimePackageDocJMF();
        
        Multipart mp=MimeUtil.getMultiPart(sm_dirTestDataTemp+fileSeparator+"testMimePackageDoc.mjm");
        File directory=new File(sm_dirTestDataTemp+fileSeparator+"TestWriteMime");
        if(directory.exists())
            directory.delete();
        MimeUtil.writeToDir(mp, directory);
        assertTrue(new File(sm_dirTestDataTemp+fileSeparator+"TestWriteMime"+fileSeparator+"test.icc").exists());
    }
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
}
