/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util.net;

import java.io.InputStream;

import org.cip4.jdflib.util.UrlPart;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.thread.WaitTimeout;

/**
 * class to do network checks on urls
 *
 * @author rainer prosi
 * @date Nov 16, 2012
 */
public class UrlCheck
{
	private String method;

	/**
	 *
	 * internal wait thread
	 *
	 * @author rainer prosi
	 * @date Nov 16, 2012
	 */
	private class UrlWait extends WaitTimeout<UrlPart>
	{

		/**
		 *
		 * @param millis
		 */
		UrlWait(final int millis)
		{
			super(millis);
			start();
		}

		/**
		 * @see org.cip4.jdflib.util.thread.WaitTimeout#handle()
		 */
		@Override
		protected UrlPart handle()
		{
			final UrlPart p = UrlUtil.writeToURL(url, stream, method, null, det);
			if (p != null && buffer)
				p.buffer();
			return p;
		}

	}

	private final String url;
	private UrlWait wait;
	private InputStream stream;
	private boolean buffer;
	private HTTPDetails det;

	/**
	 * @return the method
	 */
	public String getMethod()
	{
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(final String method)
	{
		this.method = method;
	}

	/**
	 * @return the buffer
	 */
	public boolean isBuffer()
	{
		return buffer;
	}

	/**
	 *
	 * @param url
	 */
	public UrlCheck(final String url)
	{
		this(url, UrlUtil.HEAD);
	}

	/**
	 *
	 * @param url
	 * @param method
	 */
	public UrlCheck(final String url, final String method)
	{
		this.url = url;
		wait = null;
		this.method = method;
		stream = null;
		buffer = false;
	}

	/**
	 *
	 * check if a url is accessible, return null if no connection within waitMillis millisecons
	 *
	 * @param waitMillis
	 * @return the returned urlPart
	 */
	public UrlPart ping(final int waitMillis)
	{
		startPing(waitMillis);
		final UrlPart p = getPing();
		return p;
	}

	/**
	 * get the ping of a started thread
	 *
	 * @return
	 */
	public UrlPart getPing()
	{
		final UrlPart p = wait == null ? null : wait.getWaitedObject();
		return p;
	}

	/**
	 *
	 * start pinging - multiple pings may run simultaneously
	 *
	 * @param waitMillis time to wait (in total)
	 *
	 */
	public void startPing(final int waitMillis)
	{
		wait = new UrlWait(waitMillis);
	}

	/**
	 *
	 * get the rc
	 *
	 * @param waitMillis
	 * @return
	 */
	public int pingRC(final int waitMillis)
	{
		final UrlPart p = ping(waitMillis);
		return p == null ? -1 : p.getResponseCode();
	}

	/**
	 *
	 * get the rc
	 *
	 * @return
	 */
	public int getPingRC()
	{
		final UrlPart p = getPing();
		return p == null ? -1 : p.getResponseCode();
	}

	/**
	 * Setter for stream attribute. This Stream will be sent to the request url
	 *
	 * @param stream the stream to set
	 */
	public void setStream(final InputStream stream)
	{
		this.stream = stream;
		if (stream != null)
			method = UrlUtil.POST;
	}

	/**
	 *
	 * @param buffer
	 */
	public void setBuffer(final boolean buffer)
	{
		this.buffer = buffer;
	}

	/**
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UrlCheck [method=" + method + ", url=" + url + ", wait=" + wait + ", buffer=" + buffer + " " + ((det == null) ? "" : det);
	}

	/**
	 * @return the det
	 */
	public HTTPDetails getHTTPDetails()
	{
		return det;
	}

	/**
	 * @param det the det to set
	 */
	public void setHTTPDetails(final HTTPDetails details)
	{
		this.det = details;
	}
}
