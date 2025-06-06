/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util.thread;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.MyLong;
import org.cip4.jdflib.util.ThreadUtil;

/**
 * class to persist stuff later
 *
 * either an IPersistable or Runnable may be queued
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class DelayedPersist extends Thread
{
	private final HashMap<IPersistable, MyLong> persistQueue;
	private boolean stop;
	private static DelayedPersist theDelayed = null;
	private MyMutex waitMutex;
	private final Log log;

	private static class RunnablePersist implements IPersistable
	{
		/**
		 *
		 * @param runner
		 */
		RunnablePersist(final Runnable runner)
		{
			super();
			this.runner = runner;
		}

		private final Runnable runner;

		@Override
		public boolean persist()
		{
			if (runner == null)
			{
				return false;
			}
			else
			{
				runner.run();
				return true;
			}
		}

		@Override
		public String toString()
		{
			return "RunablePersist [runner=" + runner + "]";
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((runner == null) ? 0 : runner.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final RunnablePersist other = (RunnablePersist) obj;

			return ContainerUtil.equals(other, this);
		}

	}

	/**
	 *
	 */
	private DelayedPersist()
	{
		super("DelayedPersist");
		log = LogFactory.getLog(getClass());
		persistQueue = new HashMap<>();
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
	 * @return
	 */
	public static DelayedPersist getCurrentDelayedPersist()
	{
		return theDelayed;
	}

	/**
	 *
	 */
	public static void shutDown()
	{
		if (theDelayed != null)
		{
			final DelayedPersist tmp = theDelayed;
			theDelayed = null;
			tmp._shutDown();
		}
	}

	/**
	 *
	 */
	private void _shutDown()
	{
		log.info("shutting down " + this);
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
	public void queueRunnable(final Runnable r, final long deltaTime)
	{
		queue(r == null ? null : new RunnablePersist(r), deltaTime);
	}

	/**
	 *
	 * @param persistable the thing to send off
	 * @param deltaTime max wait time in milliseconds - if<=null persist immediately
	 */
	public void queue(final IPersistable persistable, final long deltaTime)
	{
		if (persistable == null)
		{
			log.warn("Cannot queue null IPersistable");
			return;
		}

		synchronized (persistQueue)
		{
			final MyLong l = persistQueue.get(persistable);
			final long t = System.currentTimeMillis();
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
			catch (final Exception e)
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
	int persistQueues()
	{
		long t0 = 424242;
		if (persistQueue.size() == 0)
			return (int) t0;

		final long t = System.currentTimeMillis();
		final Vector<IPersistable> theList = new Vector<>();

		synchronized (persistQueue)
		{
			final Collection<IPersistable> v = ContainerUtil.getKeyArray(persistQueue);
			if (v == null)
				return (int) t0;

			for (final IPersistable qp : v)
			{
				final MyLong l = persistQueue.get(qp);
				if (l == null)
				{
					persistQueue.remove(qp);
					log.error("Snafu persisting mismatche key pair: " + qp.getClass().getCanonicalName());
					continue;
				}
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

		// now the unsynchronized stuff
		for (final IPersistable qp : theList)
		{
			qp.persist();
		}
		if (theList.size() > 0)
		{
			t0 -= System.currentTimeMillis() - t;
			// we modified and may have added something in the process - check again
			return persistQueues();
		}
		else
		{
			return (int) t0;
		}
	}

	/**
	 * @see java.lang.Thread#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return "DelayedPersist Stop=" + stop + " queue size: " + persistQueue.size();
	}
}