/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
/**
 * ========================================================================== class JDFQueueSubmissionParams extends JDFResource
 * ==========================================================================
 *
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author sabjon@topmail.de using a code generator Warning! very preliminary test version. Interface subject to change without prior notice! Revision history: ...
 **/

package org.cip4.jdflib.jmf;

import java.net.URL;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoNotification.EnumClass;
import org.cip4.jdflib.auto.JDFAutoQueueSubmissionParams;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.ifaces.IURLSetter;
import org.cip4.jdflib.util.MyPair;

// ----------------------------------
/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         prior to 10.02.2009
 */
public class JDFQueueSubmissionParams extends JDFAutoQueueSubmissionParams implements IURLSetter
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFQueueSubmissionParams
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFQueueSubmissionParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFQueueSubmissionParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFQueueSubmissionParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString() return String
	 */
	@Override
	public String toString()
	{
		return "JDFQueueSubmissionParams[  --> " + super.toString() + " ]";
	}

	/**
	 * @deprecated use 3 parameter version
	 * @param theQueue
	 * @param responseIn
	 * @return
	 */
	@Deprecated
	public JDFResponse addEntry(final JDFQueue theQueue, final JDFJMF responseIn)
	{
		return addEntry(theQueue, responseIn, null);
	}

	/**
	 * add a queueentry to a queue based on the parameters of this you can get the new queueentry by getQueueEntry(0) on the response
	 *
	 * @param theQueue the queue to submit to, note that the queue IS modified by this call
	 * @param responseIn the jmf that serves as a container for the new response
	 * @param filter the filter to apply
	 *
	 * @return the response jmf to the submission message
	 */
	public JDFResponse addEntry(final JDFQueue theQueue, final JDFJMF responseIn, JDFQueueFilter filter)
	{
		return addQueueEntry(theQueue, responseIn, filter).getA();
	}

	/**
	 * add a queueentry to a queue based on the parameters of this you can get the new queueentry by getQueueEntry(0) on the response
	 *
	 * @param theQueue the queue to submit to, note that the queue IS modified by this call
	 * @param responseIn the jmf that serves as a container for the new response
	 * @param filter the filter to apply
	 *
	 * @return the response jmf to the submission message
	 */
	public MyPair<JDFResponse, JDFQueueEntry> addQueueEntry(final JDFQueue theQueue, final JDFJMF responseIn, JDFQueueFilter filter)
	{
		final JDFCommand command = (JDFCommand) getParentNode_KElement();
		final JDFJMF jmf = command.createResponse();
		if (filter == null)
		{
			filter = (JDFQueueFilter) new JDFDoc(ElementName.QUEUEFILTER).getRoot();
		}
		JDFResponse resp = jmf.getResponse(0);
		if (responseIn != null)
		{
			resp = (JDFResponse) responseIn.copyElement(resp, null);
		}
		MyPair<JDFResponse, JDFQueueEntry> ret = new MyPair<JDFResponse, JDFQueueEntry>(resp, null);

		if (theQueue == null)
		{
			resp.setErrorText("No Queue specified", EnumClass.Error);
			resp.setReturnCode(2); //
		}
		else
		{
			final boolean bAuto = theQueue.isAutomated();
			if (bAuto)
			{
				theQueue.setAutomated(false);
			}
			final JDFQueueEntry qe = theQueue.createQueueEntry(getHold());
			if (qe == null) // can't accept
			{
				resp.setReturnCode(112);
				theQueue.copyToResponse(resp, filter, null);
			}
			else
			{
				ret.setB(qe);
				internalAdd(theQueue, filter, resp, bAuto, qe);
			}
			if (bAuto)
			{
				theQueue.setAutomated(true);
			}
		}

		return ret;
	}

	private static final String copyAtts[] = new String[] { AttributeName.GANGNAME, AttributeName.GANGPOLICY, AttributeName.DESCRIPTIVENAME, AttributeName.PRIORITY,
			AttributeName.ACTIVATION };

	void internalAdd(final JDFQueue theQueue, final JDFQueueFilter filter, final JDFResponse resp, final boolean bAuto, final JDFQueueEntry qe)
	{
		for (final String copyAtt : copyAtts)
		{
			qe.copyAttribute(copyAtt, this);
		}

		if (bAuto)
		{
			theQueue.sortChild(qe);
		}

		resp.copyElement(qe, null);
	}

	/**
	 * returns the jdf doc referenced by url
	 *
	 * @return the document
	 */
	public JDFDoc getURLDoc()
	{
		return getURLDoc(getURL());
	}

	/**
	 *
	 * @return the filename of this; null if not implemented
	 */
	@Override
	public String getUserFileName()
	{
		return null;
	}

	/**
	 * sets ReturnURL to the value of url
	 *
	 * @param url the URL to set
	 */
	public void setReturnURL(final URL url)
	{
		super.setReturnURL(url == null ? null : url.toExternalForm());
	}

	/**
	 * sets ReturnURL to the value of url
	 *
	 * @param url the URL to set
	 */
	public void setReturnJMF(final URL url)
	{
		super.setReturnJMF(url == null ? null : url.toExternalForm());
	}

}
