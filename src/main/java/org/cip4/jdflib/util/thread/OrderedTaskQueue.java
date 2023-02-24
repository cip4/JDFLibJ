/*
 *
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
package org.cip4.jdflib.util.thread;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.ThreadUtil;

/**
 * class to run heavy tasks one at a time
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class OrderedTaskQueue extends Thread
{
	class TaskRunner implements Runnable
	{
		long tQueue;
		long tStart;
		long tEnd;
		Runnable theTask;
		Thread myThread;

		/**
		 *
		 * @param r
		 */
		TaskRunner(final Runnable r)
		{
			theTask = r;
			tQueue = System.currentTimeMillis();
			tStart = 0;
			tEnd = 0;
			myThread = null;
		}

		/**
		 *
		 */
		void interrupt()
		{
			if (myThread != null)
			{
				try
				{
					myThread.interrupt();
					log.info("Interrupted " + toString());
				}
				catch (final Throwable t)
				{
					// nop
				}
			}
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run()
		{
			tStart = System.currentTimeMillis();
			myThread = Thread.currentThread();
			started++;
			sumQueue += getWaitTime();
			try
			{
				theTask.run();
			}
			catch (final Throwable x)
			{
				log.error("Exception caught while running " + theTask, x);
			}
			finally
			{
				tEnd = System.currentTimeMillis();
				sumRun += getRunTime();
				done++;
				myThread = null;
				idle.set(0);
			}
		}

		/**
		 * get the time that this has been waiting
		 *
		 * @return wait time in milliseconds
		 */
		public long getWaitTime()
		{
			return tStart == 0 ? System.currentTimeMillis() - tQueue : tStart - tQueue;
		}

		/**
		 * get the time that this has started, 0 if waiting
		 *
		 * @return start time in milliseconds
		 */
		public long getStartTime()
		{
			return tStart;
		}

		/**
		 * get the time that this has been waiting
		 *
		 * @return run time in milliseconds
		 */
		public long getRunTime()
		{
			return tStart == 0 ? 0 : tEnd == 0 ? System.currentTimeMillis() - tStart : tEnd - tStart;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return "TaskRunner :" + getWaitTime() + " / " + getRunTime() + "\n" + theTask;
		}
	}

	final Vector<TaskRunner> queue;
	final AtomicInteger idle;
	final Log log;
	MyMutex mutex;
	int started;
	int done;
	long sumQueue;
	long sumRun;
	TaskRunner currentRunning;
	static Map<String, OrderedTaskQueue> theMap = new HashMap<>();

	/**
	 *
	 * grab the queue
	 *
	 * @param name - must not be null
	 * @return the queue to fill with tasks
	 */
	public static OrderedTaskQueue getCreateQueue(String name)
	{
		name = getThreadName(name);
		synchronized (theMap)
		{
			OrderedTaskQueue orderedTaskQueue = theMap.get(name);
			if (orderedTaskQueue == null)
			{
				orderedTaskQueue = new OrderedTaskQueue(name);
				theMap.put(name, orderedTaskQueue);
			}
			orderedTaskQueue.idle.set(0);
			return orderedTaskQueue;
		}
	}

	/**
	 *
	 * grab the queue
	 *
	 * @param name - must not be null
	 * @return the queue to fill with tasks
	 */
	public static void shutDown(String name)
	{
		name = getThreadName(name);
		synchronized (theMap)
		{
			final OrderedTaskQueue orderedTaskQueue = theMap.get(name);
			if (orderedTaskQueue != null)
			{
				orderedTaskQueue.shutDown();
			}
		}
	}

	/**
	 * @param name
	 *
	 */
	OrderedTaskQueue(final String name)
	{
		super(name);
		setDaemon(true);
		log = LogFactory.getLog(getClass());
		queue = new Vector<>();
		mutex = new MyMutex();
		started = done = 0;
		sumQueue = 0;
		sumRun = 0;
		idle = new AtomicInteger(0);
		start();
	}

	static String getThreadName(final String name)
	{
		return name == null ? "OrderedTaskQueue" : "OrderedTaskQueue_" + name;
	}

	/**
	 *
	 */
	public static void shutDownAll()
	{
		final int size = theMap.size();
		if (size > 0)
		{
			LogFactory.getLog(OrderedTaskQueue.class).info("shutting down " + size + " ordered queues");
			synchronized (theMap)
			{
				final Collection<String> v = ContainerUtil.getKeyArray(theMap);
				if (v != null)
				{
					for (final String key : v)
					{
						theMap.get(key).shutDown();
					}
				}
			}
		}
		else
		{
			LogFactory.getLog(OrderedTaskQueue.class).info("skipping shut down of " + size + " ordered queues");
		}
	}

	/**
	 *
	 */
	public void shutDown()
	{
		idle.set(-1);
		theMap.remove(getName());
		ThreadUtil.notifyAll(mutex);
	}

	/**
	 * @param minAge minimum age to interrupt
	 * @return true if we successfully interrupted or no entry was running; false if unsuccessful or current is younger than minAge
	 *
	 */
	public boolean interruptCurrent(final int minAge)
	{
		final TaskRunner cr = currentRunning;
		if (cr != null && cr.getRunTime() < minAge)
			return false;

		int n = 0;
		while (cr != null && cr == currentRunning)
		{
			interrupt();
			if (!ThreadUtil.sleep(++n) || n > 10)
				break;
		}
		return cr == null || cr != currentRunning;
	}

	/**
	 * size of the waiting queue
	 *
	 * @return
	 */
	public int size()
	{
		return waiting() + executing();
	}

	public int executing()
	{
		return (currentRunning == null) ? 0 : 1;
	}

	/**
	 * size of the waiting queue + currently executing
	 *
	 * @return
	 */
	public int waiting()
	{
		return queue.size();
	}

	/**
	 * number of already completed tasks
	 *
	 * @return
	 */
	public int getDone()
	{
		return done;
	}

	/**
	 *
	 * @return
	 */
	public boolean isLive()
	{
		return idle.get() >= 0;
	}

	/**
	 *
	 * @param task the thing to send off
	 * @return true if successfully queued
	 */
	public boolean queue(final Runnable task)
	{
		if (idle.get() < 0)
		{
			log.error("cannot queue task in stopped queue");
			return false;
		}
		synchronized (queue)
		{
			queue.add(new TaskRunner(task));
		}
		idle.set(0);
		ThreadUtil.notifyAll(mutex);
		return true;
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				runTasks();
			}
			catch (final Throwable e)
			{
				log.error("whazzup queueing ordered task ", e);
			}
			if (idle.get() < 0)
			{
				ThreadUtil.notifyAll(mutex);
				mutex = null;
				break;
			}
			if (idle.incrementAndGet() > 3)
			{
				shutDown();
			}
			if (!ThreadUtil.wait(mutex, 100000))
			{
				break;
			}
		}
	}

	/**
	 *
	 */
	private void runTasks()
	{
		while (idle.get() >= 0)
		{
			currentRunning = getFirstTask();

			// now the unsynchronized stuff
			if (currentRunning != null)
			{
				runTask(currentRunning);
				currentRunning = null;
			}
			else
			{
				break;
			}
		}
	}

	/**
	 *
	 *
	 * @param r
	 */
	void runTask(final TaskRunner r)
	{
		idle.set(0);
		r.run();
	}

	/**
	 *
	 *
	 * @return
	 */
	TaskRunner getFirstTask()
	{
		synchronized (queue)
		{
			if (queue.size() > 0)
			{
				idle.set(0);
				return queue.remove(0);
			}
		}
		return null;
	}

	/**
	 * @see java.lang.Thread#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return "OrderedTaskQueue " + getName() + " " + idle + " queue: " + queue;
	}

	public String shortString()
	{
		return getName() + " r:" + executing() + " all:" + size();
	}

	/**
	 * Getter for average time waiting
	 *
	 * @return the average sumQueue
	 */
	public long getAvQueue()
	{
		return done == 0 ? 0 : sumQueue / done;
	}

	/**
	 * Getter for sumQueue attribute. total time waiting
	 *
	 * @return the sumQueue
	 */
	public long getSumQueue()
	{
		return sumQueue;
	}

	/**
	 * Getter for average time running
	 *
	 * @return the average sumRun
	 */
	public long getAvRun()
	{
		return done == 0 ? 0 : sumRun / done;
	}

	/**
	 * Getter for sumRun attribute. total time running
	 *
	 * @return the sumRun
	 */
	public long getSumRun()
	{
		return sumRun;
	}
}