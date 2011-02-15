/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.util.hotfolder;

import java.io.File;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFQueueSubmissionParams;
import org.cip4.jdflib.jmf.JDFReturnQueueEntryParams;
import org.cip4.jdflib.jmf.JMFBuilder;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFProcessRun;
import org.cip4.jdflib.util.UrlUtil;

/**
 * 
 * hot folder listener that submits to a queue
 * @author rainerprosi
 * @date Feb 14, 2011
 */
public class QueueHotFolderListenerImpl implements HotFolderListener
{

	/**
	 * 
	 * @param qhfl
	 * @param _queueCommand
	 */
	public QueueHotFolderListenerImpl(QueueHotFolderListener qhfl, JDFJMF _queueCommand)
	{
		super();
		this.qhfl = qhfl;
		if (_queueCommand == null)
		{
			_queueCommand = new JMFBuilder().buildSubmitQueueEntry(null);
		}

		this.queueCommand = _queueCommand.getCommand(0);
	}

	final QueueHotFolderListener qhfl; // the callback that is called
	// whenever a file is dropped
	final JDFCommand queueCommand; // the jdf command template that is

	/**
	 * extract the JDF document from the file
	 * 
	 * @param storedFile
	 * @return the JDFDoc for the file
	 */
	protected JDFDoc getJDFFromFile(final File storedFile)
	{
		final JDFDoc jdfDoc = JDFDoc.parseFile(storedFile.getPath());
		return jdfDoc;
	}

	/**
	 * @see org.cip4.jdflib.util.hotfolder.HotFolderListener#hotFile(java.io.File)
	 * @param hotFile
	 */
	public void hotFile(final File hotFile)
	{
		final String stringURL = UrlUtil.fileToUrl(hotFile, false);

		final JDFDoc jmfDoc = new JDFDoc("JMF");
		final JDFJMF jmfRoot = jmfDoc.getJMFRoot();
		final JDFCommand newCommand = (JDFCommand) jmfRoot.copyElement(queueCommand, null);
		newCommand.removeAttribute(AttributeName.ID);
		newCommand.appendAnchor(null);
		final EnumType cType = newCommand.getEnumType();
		final JDFDoc jdfDoc = getJDFFromFile(hotFile);

		if (jdfDoc != null)
		{
			final String newURL = UrlUtil.newExtension(stringURL, "jdf");
			if (!newURL.equalsIgnoreCase(stringURL))
			{
				hotFile.delete();
				jdfDoc.write2File(UrlUtil.urlToFile(newURL), 2, false);
			}

			final JDFNode jdfRoot = jdfDoc.getJDFRoot();

			if (EnumType.ReturnQueueEntry.equals(cType))
			{
				extractReturnParams(newURL, newCommand, jdfRoot);
			}
			else if (EnumType.SubmitQueueEntry.equals(cType))
			{
				extractSubmitParams(newURL, newCommand, jdfRoot);
			}
			qhfl.submitted(jmfRoot);
		}
	}

	/**
	 * overwrite this method in case you want to customize the hotfolder for returnqueueentryparams and paramtetrizing the ReturnQueueEntryParams template is
	 * insufficient
	 * 
	 * @param stringURL the file url of the hotfolder jdf in the local storage directory (NOT the hf)
	 * @param newCommand the command that was generated from the template
	 * @param jdfRoot the root jdf node of the dropped file
	 */
	protected void extractReturnParams(final String stringURL, final JDFCommand newCommand, final JDFNode jdfRoot)
	{
		final JDFReturnQueueEntryParams rqp = newCommand.getCreateReturnQueueEntryParams(0);
		rqp.setURL(stringURL);
		if (jdfRoot != null)
		{
			final JDFAuditPool ap = jdfRoot.getCreateAuditPool();
			final JDFProcessRun pr = (JDFProcessRun) ap.getAudit(-1, EnumAuditType.ProcessRun, null, null);
			final String queueEID = pr == null ? null : pr.getAttribute(AttributeName.QUEUEENTRYID);
			if (!KElement.isWildCard(queueEID))
			{
				rqp.setQueueEntryID(queueEID);
			}
		}
	}

	/**
	 * overwrite this method in case you want to customize the hotfolder for submitqueentry and parametrizing the QueueSubmissionParams template is
	 * insufficient
	 * 
	 * @param stringURL the file url of the hotfolder jdf in the local storage directory (NOT the hf)
	 * @param newCommand the command that was generated from the template
	 * @param jdfRoot the root jdfnode of the dropped file
	 */
	protected void extractSubmitParams(final String stringURL, final JDFCommand newCommand, final JDFNode jdfRoot)
	{
		final JDFQueueSubmissionParams sqp = newCommand.getCreateQueueSubmissionParams(0);
		sqp.setURL(stringURL);
		if (jdfRoot != null)
		{
			final JDFAuditPool ap = jdfRoot.getCreateAuditPool();
			ap.createSubmitProcessRun(null);
		}
	}

}