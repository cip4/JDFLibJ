/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of
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
 * 04022005 VF initial version
 */
/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.cip4.jdflib.JDFTestCaseBase;



public class FileUtilTest extends JDFTestCaseBase
{

    ///////////////////////////////////////////////////////////////////////////
    public void testListFiles() throws Exception
    {
         File f=new File(sm_dirTestDataTemp+"/foo");
         f.mkdir(); // make sure we have one
         assertTrue(FileUtil.deleteAll(f));
         assertTrue(f.mkdir());

         for(char c='a';c<'g';c++)
         {
             for(int i=0;i<3;i++)
             {
                 File f2=new File(f.getAbsolutePath()+File.separator+i+"."+c);
                 assertTrue(f2.createNewFile());
             }
         }
         assertEquals(FileUtil.listFiles(f, "a").length,3);
         assertEquals(FileUtil.listFiles(f, "C")[0].getName(),"0.c");
         assertNull(FileUtil.listFiles(f, "CC"));

    }

    ///////////////////////////////////////////////////////////////////////////
    public void testStreamToFile() throws Exception
    {
        byte[] b=new byte[55555];
        for(int i=0;i<55555;i++) {
			b[i]=(byte)(i%256);
		}
        ByteArrayInputStream is=new ByteArrayInputStream(b);
        is.close();
        File f=new File(sm_dirTestDataTemp+"stream.dat");
        if(f.exists()) {
			f.delete();
		}

        FileUtil.streamToFile(is, sm_dirTestDataTemp+"stream.dat");
        assertTrue(f.exists());

        FileInputStream fis=new FileInputStream(f);
        for(int i=0;i<55555;i++)
        {
            b[i]=(byte)fis.read();
            if(i%287==0) {
				assertEquals((256+b[i])%256, i%256);
			}
        }

        int j=fis.read();
        assertEquals("eof reached",j, -1);
        fis.close();

        FileInputStream fis2=new FileInputStream(f);
        File f2=FileUtil.streamToFile(fis2, sm_dirTestDataTemp+"stream2.dat");
        assertTrue(f2.canRead());
        assertTrue(f.delete());
        assertTrue(f2.delete());

    }

}
