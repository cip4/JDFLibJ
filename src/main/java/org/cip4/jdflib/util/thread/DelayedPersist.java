/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.util.thread;

import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.MyLong;
import org.cip4.jdflib.util.ThreadUtil;

/**
 * class to persist stuff later
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class DelayedPersist extends Thread
{
	private final HashMap<IPersistable, MyLong> persistQueue;
	private boolean stop;
	private static DelayedPersist theDelayed = null;
	private MyMutex waitMutex;
	private final Log log;

	private DelayedPersist()
	{
		super("DelayedPersist");
		log = LogFactory.getLog(getClass());
		persistQueue = new HashMap<IPersistable, MyLong>();
		stop = false;
		waitMutex = new MyMutex();
		start();
	}

	/**
	 * 
	 * @return
	 */
	public static DelayedPersist getDelayedPersist()
	{
		if (theDelayed == null)
			theDelayed = new DelayedPersist();
		return theDelayed;
	}

	/**
	 * 
	 */
	public static void shutDown()
	{
		if (theDelayed == null)
		{
			LogFactory.getLog(DelayedPersist.class).warn("Cannot shutdown null DelayedPersist, bailing out");
			return;
		}
		theDelayed._shutDown();
		theDelayed = null;
	}

	/**
	 * 
	 */
	private void _shutDown()
	{
		log.info("shutting down delayed persist");
		stop = true;
		persistQueues();
		ThreadUtil.notifyAll(waitMutex);
		ThreadUtil.sleep(10); // sleep a short while to enable thread control to be passed along
		if (waitMutex != null)
		{
			log.info("waiting for persist of delayed persist");
			if (waitMutex != null) // just in case the log opened the time slot
				ThreadUtil.wait(waitMutex, 120000); // we should never need more than 2 minutes to shut down
			log.info("finished waiting for persist of delayed persist");
		}
		theDelayed = null;
	}

	/**
	 * 
	 * @param persistable the thing to send off
	 * @param deltaTime max wait time in milliseconds - if<=null persist immediately
	 */
	public void queue(IPersistable persistable, long deltaTime)
	{
		synchronized (persistQueue)
		{
			MyLong l = persistQueue.get(persistable);
			long t = System.currentTimeMillis();
			if (l == null)
			{
				persistQueue.put(persistable, new MyLong(t + deltaTime));
			}
			else if (t + deltaTime < l.i)
			{
				// we want it sooner
				l.i = t + deltaTime;
			}
		}
		ThreadUtil.notifyAll(waitMutex);
	}

	/**
	 * @see java.lang.Thread#run()
	*/
	@Override
	public void run()
	{
		log.info("starting queue persist loop");
		while (true)
		{
			int tWait = 10000;
			try
			{
				tWait = persistQueues();
			}
			catch (Exception e)
			{
				log.error("whazzup queueing delayedPersist ", e);
			}
			if (stop)
			{
				log.info("end of queue persist loop");
				ThreadUtil.notifyAll(waitMutex);
				waitMutex = null;
				break;
			}
			if (tWait > 0 && !ThreadUtil.wait(waitMutex, tWait))
			{
				_shutDown();
				break;
			}
		}
	}

	/**
	 * @return the recommended next wait time
	 */
	private int persistQueues()
	{
		long t0 = 424242;
		if (persistQueue.size() == 0)
			return (int) t0;

		long t = System.currentTimeMillis();
		Vector<IPersistable> theList = new Vector<IPersistable>();

		synchronized (persistQueue)
		{
			Vector<IPersistable> v = ContainerUtil.getKeyVector(persistQueue);
			if (v == null)
				return (int) t0;

			for (IPersistable qp : v)
			{
				MyLong l = persistQueue.get(qp);
				if (stop || l.i < t)
				{
					theList.add(qp);
					persistQueue.remove(qp);
				}
				else if (l.i - t < t0)
				{
					t0 = l.i - t;
				}
			}
		}

		boolean gotOne = false;
		// now the unsynchronized stuff
		for (IPersistable qp : theList)
		{
			qp.persist();
			gotOne = true;
		}
		if (gotOne)
		{
			System.gc();
			t0 -= System.currentTimeMillis() - t;
		}
		return (int) t0;
	}

	/**
	 * @see java.lang.Thread#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		return "DelayedPersist Thread " + stop + " queue: " + persistQueue;
	}
}