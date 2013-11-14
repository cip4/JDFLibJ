/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of 
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

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.MyInteger;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.UrlPart;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.net.IPollHandler.PollResult;
import org.cip4.jdflib.util.thread.MyMutex;

/**
 * class to poll a network address
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class NetPoll
{
	private final Log log;

	/**
	 * @param urls the urls to poll - duh!
	 * @param poller 
	 * 
	 */
	public NetPoll(VString urls, IPollHandler poller)
	{
		super();
		this.vUrl = urls;
		idleWait = 15000; // milliseconds
		busyWait = 500; // milliSeconds
		pollThread = null;
		this.poller = poller;
		mutex = new MyMutex();
		method = UrlUtil.GET;
		contentType = UrlUtil.TEXT_UNKNOWN;
		log = LogFactory.getLog(getClass());
	}

	/**
	 * shorthand constructor for single String
	 * @param url the url to poll - duh!
	 * @param poller 
	 * 
	 */
	public NetPoll(String url, IPollHandler poller)
	{
		this(new VString(url, null), poller);
	}

	private final VString vUrl;
	protected int idleWait;
	protected int busyWait;
	protected PollThread pollThread;
	static int threadCount = 0;
	protected IPollHandler poller;
	protected MyMutex mutex;
	protected String method;
	protected String contentType;

	/**
	 * return true if we are running
	 * @return
	 */
	public boolean isRunning()
	{
		return pollThread != null && pollThread.running;
	}

	/**
	 * start the poll loop
	 */
	public void start()
	{
		if (pollThread == null)
		{
			pollThread = new PollThread();
			log.info("starting poll thread " + pollThread.getName());
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
			log.info("shutting down poll thread " + pollThread.getName());
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
			return "PollThread: idle: " + getIdleWait() + " busy: " + busyWait + " running: " + running + "\n" + poller;
		}

		/**
		 * @see java.lang.Thread#run()
		*/
		@Override
		public void run()
		{
			int n = 0;
			int nSinceBad = 0;
			HashMap<String, MyInteger> badMap = new HashMap<String, MyInteger>();
			if (vUrl == null || vUrl.size() == 0)
			{
				log.warn("polling 0 urls - bailing out");
				running = false;
			}
			while (running)
			{
				String url = vUrl.get(n);
				nSinceBad++;
				MyInteger bad = badMap.get(url);
				if (bad == null || nSinceBad > bad.i)
				{
					IPollDetails details = poll(url);
					PollResult result;
					try
					{
						result = poller.handlePoll(details, url);
					}
					catch (Exception x)
					{
						result = null;
					}
					if (result == null || !PollResult.success.equals(result))
					{
						if (PollResult.error.equals(result))
						{
							if (bad == null)
							{
								bad = new MyInteger(1);
								badMap.put(url, bad);
							}
							bad.i++;
							nSinceBad = 0;
						}
						else
						{
							badMap.remove(url);
						}
						if (!ThreadUtil.wait(mutex, getIdleWait()))
							break;

						// we only move on to the next in case nothing is waiting
						int size = getUrlSize();
						n = ++n % size;
					}
					else
					{
						if (!ThreadUtil.wait(mutex, getIdleWait()))
							break;
					}
				}
				else
				// skip errors more often
				{
					if (!ThreadUtil.wait(mutex, getIdleWait()))
						break;
				}
			}
			// clean up when we leave
			pollThread = null;
		}
	}

	protected int getUrlSize()
	{
		int size = vUrl.size();
		if (size <= 0)
			size = 1;
		return size;
	}

	/**
	 * @param baseUrl the url to write to 
	 * @return the details, null if no connection could be made
	 */
	protected IPollDetails poll(String baseUrl)
	{
		String url = getUrl(baseUrl);
		UrlPart p = UrlUtil.writeToURL(url, null, method, contentType, null);
		return p;
	}

	/**
	 * @param method the http transfer method to set (GET / POST)
	 */
	public void setMethod(String method)
	{
		this.method = method;
	}

	/**
	 * @param url the additional url to add
	 */
	public void addURL(String url)
	{
		vUrl.appendUnique(url);
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
		return "NetPoll: " + vUrl + " method: " + method + " content-type: " + contentType + "\n" + pollThread;
	}

	/**
	 * @param idleWait the number of wait milliseconds to set
	 */
	public void setIdleWait(int idleWait)
	{
		this.idleWait = idleWait;
	}

	/**
	 * @param busyWait the busyWait to set
	 */
	public void setBusyWait(int busyWait)
	{
		this.busyWait = busyWait;
	}

	/**
	 * 
	 * get the URL based on a baseurl
	 * @param baseUrl 
	 * @return
	 */
	protected String getUrl(String baseUrl)
	{
		return baseUrl;
	}

	/**
	 * 
	 * get the vector of urls
	 *  
	 * @return
	 */
	public VString getUrls()
	{
		return vUrl;
	}

	/**
	 * make sur we stop all threads prior to dying
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable
	{
		stop();
		super.finalize();
	}

	/**
	 * get the idle wait period divided by the # of urls to ping
	 *  
	 * @return
	 */
	protected int getIdleWait()
	{
		int iw = idleWait / getUrlSize();
		if (iw < busyWait)
			iw = busyWait;
		return iw;
	}
}
