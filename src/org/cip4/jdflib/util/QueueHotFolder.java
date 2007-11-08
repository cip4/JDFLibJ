/*
 *
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
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * KString.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.util;

import java.io.File;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFQueueSubmissionParams;
import org.cip4.jdflib.jmf.JDFReturnQueueEntryParams;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;


/**
 * a hotfolder that emulates JMF queue functionality 
 * by applying a specific queue submission or queue return message
 * to any file that is dropped into it
 * 
 * @author prosirai
 *
 */
public class QueueHotFolder implements HotFolderListener
{
    private final File storageDir;
    private final HotFolder hf;
    private final QueueHotFolderListener qhfl;
    private final JDFCommand queueCommand;


    /**
     *     
     * constructor for a simple queue based hotfolder watcher that is automagically started in its own thread
     * 
     * @param _hotFolderDir the hot folder directory to watch
     * @param _storageDir the storage directory wher hot files are moved to
     * @param ext the file extensions that are moved - if null no filtering
     * @param hfListener callback that receives the generated JMF - the location of the stored file will be found in the standard command parameters
     * @param _queueCommand the jmf template that will be used to generate a new message
     */
    public QueueHotFolder(File _hotFolderDir, File _storageDir, String ext, QueueHotFolderListener hfListener, JDFJMF _queueCommand) 
    {
        storageDir=_storageDir;
        qhfl=hfListener;
        hf=new HotFolder(_hotFolderDir,ext,this);
        queueCommand=_queueCommand.getCommand(0);
    }

    public void stop()
    {
        hf.interrupt=true;
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.util.HotFolderListener#hotFile(java.io.File)
     */
    public void hotFile(File hotFile)
    {
        File storedFile=FileUtil.moveFileToDir(hotFile, storageDir);
        String stringURL;
        stringURL=UrlUtil.fileToUrl(storedFile, false);

        JDFDoc doc=new JDFDoc("JMF");
        final JDFJMF jmfRoot = doc.getJMFRoot();
        JDFCommand newCommand=(JDFCommand) jmfRoot.copyElement(queueCommand, null);
        EnumType cType=newCommand.getEnumType();
        if(EnumType.ReturnQueueEntry.equals(cType))
        {
            JDFReturnQueueEntryParams rqp=newCommand.getCreateReturnQueueEntryParams(0);
            rqp.setURL(stringURL);
        }
        else if(EnumType.SubmitQueueEntry.equals(cType))
        {
            JDFQueueSubmissionParams sqp=newCommand.getCreateQueueSubmissionParams(0);
            sqp.setURL(stringURL);
        }
        qhfl.submitted(jmfRoot);
    }


}
