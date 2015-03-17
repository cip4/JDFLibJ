/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of 
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

import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.ThreadUtil;

/**
 *asynchronous janitor class
 * @author rainer prosi
 * @date Dec 9, 2011
 */
public final class RegularJanitor
{
	private MyMutex theMutex;
	private final Log log;
	private int nThread;
	private static RegularJanitor theJanitor = null;
	private final Vector<Sweeper> vSweepers;
	private final Vector<Sweeper> tmpSweepers;
	private final Vector<Sweeper> zappSweepers;
	private int interval;

	/**
	 * 
	 * set the interval between sweeps in seconds
	 * @param interval
	 */
	public void setInterval(int interval)
	{
		if (interval < 1)
		{
			log.error("cannot set interval <1; setting to 1 second");
			interval = 1;
		}
		this.interval = interval;
	}

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RegularJanitor: interval=" + interval + " size=" + numSweepers();
	}

	/**
	 * 
	 *  
	 * @return
	 */
	int numSweepers()
	{
		return vSweepers.size() + tmpSweepers.size() - zappSweepers.size();
	}

	/**
	 * add a new sweeper
	 *  
	 * @param sweeper
	 * @param singleClass if true, make sure we only have one of sweeper of this type running at any given time
	 */
	public void addSweeper(Sweeper sweeper, boolean singleClass)
	{
		if (sweeper == null)
		{
			log.error("cannot add null sweeper");
			return;
		}
		// must use tnp to avoid race conditions while sweeping
		synchronized (tmpSweepers)
		{
			if (singleClass)
			{
				checkDuplicates(sweeper);
			}
			tmpSweepers.add(sweeper);
			log.info("adding sweeper " + sweeper);
		}
	}

	/**
	 * 
	 * @param sweeper
	 * @return
	 */
	public boolean hasSweeper(Object sweeper)
	{
		return getOldDuplicate(sweeper) != null;
	}

	private Sweeper getOldDuplicate(Object sweeper)
	{

		Vector<Sweeper> v = new Vector<Sweeper>();
		v.addAll(tmpSweepers);
		v.addAll(vSweepers);
		Class<?> newClass = getRunnerClass(sweeper);
		if (newClass == null)
			return null;

		for (Sweeper oldSweeper : v)
		{
			Class<?> oldClass = getRunnerClass(oldSweeper);
			if (oldClass.equals(newClass))
			{
				return oldSweeper;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param sweeper
	 */
	private void checkDuplicates(Sweeper sweeper)
	{
		Sweeper oldSweeper = getOldDuplicate(sweeper);
		if (oldSweeper != null)
		{
			log.info("removing duplicate tmp sweeper");
			zappSweepers.add(oldSweeper);
		}
	}

	/**
	 * 
	 *  
	 * @param oldSweeper
	 * @return
	 */
	private Class<? extends Object> getRunnerClass(Object sweeper)
	{
		if (sweeper instanceof Sweeper)
		{
			Sweeper oldSweeper = (Sweeper) sweeper;
			return (oldSweeper instanceof TimeSweeper) ? ((TimeSweeper) oldSweeper).getRunnerClass() : oldSweeper.getClass();
		}
		else if (sweeper instanceof Runnable)
		{
			return sweeper.getClass();
		}
		else
		{
			return null;
		}
	}

	/**
	 * 
	 *  
	 * @author rainer prosi
	 * @date Dec 9, 2011
	 */
	class JanitorThread extends Thread
	{
		private int firstInterval;

		/**
		 * 
		 */
		JanitorThread()
		{
			super("Janitor_" + (nThread++));
			firstInterval = interval;
			theMutex = new MyMutex();
		}

		@Override
		public void run()
		{
			log.info("Janitor starting");
			// no reason to start right away, while the connector may be busy - run every hour
			if (firstInterval > 0)
			{
				log.info("Janitor starting - initial pause: " + firstInterval + " seconds");
				ThreadUtil.wait(theMutex, 1000 * firstInterval);
			}
			if (theMutex != null)
			{
				log.info("Janitor starting - interval: " + interval + " seconds");
			}
			while (theMutex != null)
			{
				moveModifications();
				for (Sweeper sweeper : vSweepers)
				{
					sweep(sweeper);
					if (theMutex == null)
						break; // feierabend was called
				}
				if (!ThreadUtil.wait(theMutex, 1000 * interval))
				{
					break;
				}
			}
			log.info("Janitor over and out");
		}

		protected void moveModifications()
		{
			synchronized (tmpSweepers)
			{
				for (Sweeper oldSweeper : zappSweepers)
				{
					tmpSweepers.remove(oldSweeper);
					vSweepers.remove(oldSweeper);
				}
				vSweepers.addAll(tmpSweepers);
				tmpSweepers.clear();
				zappSweepers.clear();
			}
		}

		/**
		 * protected sweeper
		 * 
		 * @param sweeper the sweeper to execute
		 */
		private void sweep(Sweeper sweeper)
		{
			try
			{
				if (sweeper.needSweep())
				{
					sweeper.sweep();
				}
			}
			catch (Throwable x)
			{
				log.error("problems sweeping", x);
			}
		}

		/**
		 *  
		 * @param firstInterval the first interval prior to commencing in seconds
		 */
		void setFirstInterval(int firstInterval)
		{
			if (firstInterval < 0)
				firstInterval = interval;
			this.firstInterval = firstInterval;
		}
	}

	/**
	 * @param firstInterval the time in seconds to wait prior to the first sweep, if 0 then don't wait if<0 then use interval
	 * 
	 */
	public synchronized void startSweep(int firstInterval)
	{
		if (theMutex != null)
		{
			log.warn("Janitor already running - do nothing ");
			if (theJanitor != null)
				return;
		}
		else
		{
			log.info("starting sweeeper in seconds: " + firstInterval);
		}
		JanitorThread janitorThread = new JanitorThread();
		janitorThread.setFirstInterval(firstInterval);
		janitorThread.start();
	}

	/**
	 * 
	 */
	public static void feierabend()
	{
		if (theJanitor != null)
		{
			RegularJanitor tmp = theJanitor;
			theJanitor = null;
			tmp.shutdown();
		}
	}

	/**
	 * 
	 */
	private void shutdown()
	{
		if (theMutex == null)
		{
			log.warn("cannot shutdown sleeping janitor that has already been shut down");
		}
		MyMutex m = theMutex;
		theMutex = null;
		ThreadUtil.notifyAll(m);
		log.info("shutting down the janitor");
	}

	/**
	 * get the singleton janitor
	 * @return
	 */
	public static RegularJanitor getJanitor()
	{
		if (theJanitor == null)
			theJanitor = new RegularJanitor();
		return theJanitor;

	}

	/**
	 *
	 */
	protected RegularJanitor()
	{
		super();
		interval = 15;
		nThread = 0;
		log = LogFactory.getLog(getClass());
		vSweepers = new Vector<Sweeper>();
		tmpSweepers = new Vector<Sweeper>();
		zappSweepers = new Vector<Sweeper>();
		log.info("creating new janitor");
	}
}
