/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import org.cip4.jdflib.util.ThreadUtil;

/**
 * class to run heavy tasks one at a time
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class MultiTaskQueue extends OrderedTaskQueue
{
	private int maxParallel;
	private ThreadPoolExecutor executor;
	private final Vector<TaskRunner> current;
	int nThread;

	private class NextRunner extends TaskRunner
	{

		NextRunner(final TaskRunner r)
		{
			super(r.theTask);
			tQueue = r.tQueue;
			tStart = r.tStart;
			tEnd = r.tEnd;
		}

		/**
		 * @see org.cip4.jdflib.util.thread.OrderedTaskQueue.TaskRunner#run()
		 */
		@Override
		public void run()
		{
			super.run();
			current.remove(this);
			ThreadUtil.notifyAll(mutex);
		}

	}

	/**
	 *
	 * grab the queue
	 *
	 * @param name - must not be null
	 * @param maxParallel ignored if <=0
	 * @return the queue to fill with tasks
	 */
	public static MultiTaskQueue getCreateQueue(String name, final int maxParallel)
	{
		name = getThreadName(name);
		synchronized (theMap)
		{
			OrderedTaskQueue orderedTaskQueue = theMap.get(name);
			if (!(orderedTaskQueue instanceof MultiTaskQueue))
			{
				orderedTaskQueue = new MultiTaskQueue(name);
				theMap.put(name, orderedTaskQueue);
			}
			final MultiTaskQueue multiTaskQueue = (MultiTaskQueue) orderedTaskQueue;
			multiTaskQueue.setMaxParallel(maxParallel);
			return multiTaskQueue;
		}
	}

	/**
	 *
	 * @param maxParallel
	 */
	public void setMaxParallel(final int maxParallel)
	{
		if (maxParallel > 0 && maxParallel != this.maxParallel)
		{
			this.maxParallel = maxParallel;
			if (executor != null)
				executor.shutdown();
			executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxParallel, new MyThreadFactory());
		}
	}

	class MyThreadFactory implements ThreadFactory
	{

		@Override
		public Thread newThread(final Runnable r)
		{
			final Thread t = new Thread(r, MultiTaskQueue.this.getName() + "_" + (nThread++));
			t.setDaemon(true);
			return t;
		}

	}

	/**
	 *
	 * get the number of currently running tasks
	 *
	 * @return
	 */
	public int getCurrentRunning()
	{
		return current.size();
	}

	/**
	 * @param minAge minimum age to interrupt
	 * @return true if we successfully interrupted or no entries were running
	 *
	 */
	@Override
	public boolean interruptCurrent(final int minAge)
	{
		final Vector<TaskRunner> currentCopy = new Vector<>();
		currentCopy.addAll(current);
		boolean bRet = false;
		for (final TaskRunner next : currentCopy)
		{
			if (next.getRunTime() >= minAge)
			{
				interruptRunner(next);
				bRet = bRet || !current.contains(next);
			}
		}
		return bRet;
	}

	/**
	 * @param theRunner runner to zapp
	 * @return true if we successfully interrupted or no entries were running
	 *
	 */
	public boolean interruptTask(final Runnable theRunner)
	{
		final Vector<TaskRunner> currentCopy = new Vector<>();
		currentCopy.addAll(current);
		boolean bRet = false;
		for (final TaskRunner next : currentCopy)
		{
			if (next.theTask == theRunner)
			{
				interruptRunner(next);
				bRet = !current.contains(next);
				break;
			}
		}
		return bRet;
	}

	private void interruptRunner(final TaskRunner next)
	{
		int n = 1;
		if (next.myThread == null)
		{
			current.remove(next);
			log.info("Zapped idle " + next);
		}
		else
		{
			while (current.contains(next))
			{
				next.interrupt();
				if (!ThreadUtil.sleep(++n) || n > 10)
					break;

			}
		}
	}

	/**
	 * @param minAge minimum age to interrupt
	 * @return true if we successfully interrupted or no entries were running
	 *
	 */
	Vector<Runnable> getCurrent(final int minAge)
	{
		final Vector<TaskRunner> currentCopy = new Vector<>();
		currentCopy.addAll(current);

		final Vector<Runnable> vReturn = new Vector<>();
		for (final TaskRunner next : currentCopy)
		{
			if (next.getRunTime() >= minAge)
			{
				vReturn.add(next.theTask);
			}
		}
		return vReturn;
	}

	/**
	 * @param name
	 *
	 */
	MultiTaskQueue(final String name)
	{
		super(name);
		current = new Vector<>();
		maxParallel = 0;
		setMaxParallel(2);
		nThread = 1;
	}

	/**
	 * @see java.lang.Thread#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + " " + getName() + " " + idle + " queue: " + queue;
	}

	/**
	 */
	@Override
	void runTask(final TaskRunner r)
	{
		idle = 0;
		final NextRunner nr = new NextRunner(r);
		current.add(nr);
		executor.submit(nr);
	}

	/**
	 * @return the maxParallel
	 */
	public int getMaxParallel()
	{
		return maxParallel;
	}

	/**
	 * @see org.cip4.jdflib.util.thread.OrderedTaskQueue#size()
	 */
	@Override
	public int size()
	{
		final int n = executor == null ? 0 : executor.getActiveCount();
		return super.size() + Math.max(n, current.size());
	}

	/**
	 * @see org.cip4.jdflib.util.thread.OrderedTaskQueue#shutDown()
	 */
	@Override
	public void shutDown()
	{
		super.shutDown();
		if (executor != null)
			executor.shutdown();
		executor = null;
	}

}