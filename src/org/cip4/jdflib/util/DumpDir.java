/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;

/**
 *
 * @author  rainer
 *
 * very trivial temp file dump
 * 
 */
public class DumpDir {

    private File baseDir=null;
    private static int index=0;
    private static Object mutexInc=new Object();
    private static Object mutexDel=new Object();
    private int maxKeep=500;
    /**
     * 
     */
    private static final long serialVersionUID = -8902151736333089036L;

    private static int increment()
    {
        synchronized (mutexInc)
        {
            return index++;            
        }
    }

    /**
     * 
     */
    public DumpDir(File dir)
    {
        baseDir=dir;
        baseDir.mkdirs();
        synchronized (mutexDel)
        {
            String[] names=baseDir.list();
            int max=0;
            int l;
            for(int i=0;i<names.length;i++)
            {
                if(names[i].length()>9)
                    l=StringUtil.parseInt(names[i].substring(1, 9), 0);
                else
                    l=0;
                if(l>max)
                    max=l;
            }
            index=max;
        }
    }

    
    /** 
     * create a new File in this dump
     * 
     */
    public File newFile()
    {
        final int inc = increment();
        if(inc%100==0)
            System.out.println("jmf dump service "+index);

        String s=StringUtil.sprintf("m%08i.tmp", ""+inc);
        File f=FileUtil.getFileInDirectory(baseDir, new File(s));
        cleanup(inc);
        return f;
    }
    /** 
     * create a new File in this dump and fill it from is
     * @param is the input stream to fill
     * 
     */

    public File newFileFromStream(InputStream is)
    {
        File dump=newFile();
        FileOutputStream fs;
        try
        {
            fs = new FileOutputStream(dump);
            if(!(is instanceof BufferedInputStream))
            {
                is=new BufferedInputStream(is);
                is.mark(100000);
            }
            IOUtils.copy(is, fs);
            fs.flush();
            fs.close();
            is.reset();
       }
        catch (FileNotFoundException x)
        {
            //
        }
        catch (IOException x)
        {
            //
        }
        return dump;
     }

    /**
     * @param inc
     */
    private void cleanup(final int inc)
    {
        if(inc%100==0)
        {
            synchronized (mutexDel)
            {
                String[] names=baseDir.list();
                if(names.length>maxKeep)
                {
                    Arrays.sort(names);
                    for(int i=0;i<names.length-maxKeep;i++)
                    {
                        File f=new File(names[i]);
                        f=FileUtil.getFileInDirectory(baseDir, f);
                        f.delete();
                    }
                }
            }
        }
    }
    @Override
    public String toString()
    {
        return "DumpDir "+baseDir;
    }

}
