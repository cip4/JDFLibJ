/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.util.net;

import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.ThreadUtil.MyMutex;
import org.cip4.jdflib.util.UrlUtil.UrlPart;
import org.cip4.jdflib.util.net.IPollHandler.PollResult;

/**
 * class to poll a network address
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class NetPoll
{
	/**
	 * @param url the url to poll - duh!
	 * @param poller 
	 * 
	 */
	public NetPoll(String url, IPollHandler poller)
	{
		super();
		this.url = url;
		idleWait = 15000; // milliseconds
		busyWait = 500; // milliSeconds
		pollThread = null;
		this.poller = poller;
		mutex = new MyMutex();
		method = UrlUtil.GET;
		contentType = null;
	}

	private final String url;
	protected final int idleWait;
	protected final int busyWait;
	private PollThread pollThread;
	static int threadCount = 0;
	protected IPollHandler poller;
	protected MyMutex mutex;
	protected String method;
	protected String contentType;

	/**
	 * start the poll loop
	 */
	public void start()
	{
		if (pollThread == null)
		{
			pollThread = new PollThread();
			pollThread.start();
		}
	}

	/**
	 * stop the poll loop
	 */
	public void stop()
	{
		if (pollThread != null)
		{
			pollThread.running = false;
			ThreadUtil.notifyAll(mutex);
			pollThread = null;
		}
	}

	private class PollThread extends Thread
	{
		/**
		 * 
		 */
		public PollThread()
		{
			super("NetPoll_" + threadCount++);
			running = true;

		}

		protected boolean running;

		/**
		 * @see java.lang.Object#toString()
		 * @return
		*/
		@Override
		public String toString()
		{
			return "PollThread: idle: " + idleWait + " busy: " + busyWait + " running: " + running + "\n" + poller;
		}

		/**
		 * @see java.lang.Thread#run()
		*/
		@Override
		public void run()
		{
			while (running)
			{
				IPollDetails details = poll();
				PollResult result;
				try
				{
					result = poller.handlePoll(details);
				}
				catch (Exception x)
				{
					result = null;
				}
				if (result == null || !PollResult.success.equals(result))
				{
					ThreadUtil.wait(mutex, idleWait);
				}
				else
				{
					ThreadUtil.wait(mutex, busyWait);
				}
			}
		}
	}

	/**
	 * @return
	 */
	public IPollDetails poll()
	{
		UrlPart p = UrlUtil.writeToURL(url, null, method, contentType, null);
		return p;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method)
	{
		this.method = method;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		return "NetPoll: " + url + " method: " + method + " content-type: " + contentType + "\n" + pollThread;
	}
}
