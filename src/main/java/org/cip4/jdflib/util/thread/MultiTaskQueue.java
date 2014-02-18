/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of 
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

import org.cip4.jdflib.util.ThreadUtil;

/**
 * class to run heavy tasks one at a time
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class MultiTaskQueue extends OrderedTaskQueue
{
	private int maxParallel;
	private final Vector<NextRunner> current;
	private int totalParallel;

	private class NextRunner extends Thread
	{
		TaskRunner worker;

		/**
		 * 
		 * @param worker
		 */
		NextRunner(TaskRunner worker)
		{
			super(MultiTaskQueue.this.getName() + "_" + (++totalParallel) + "_" + current.size());
			this.worker = worker;
		}

		/**
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run()
		{
			try
			{
				worker.run();
			}
			finally
			{
				current.remove(this);
				ThreadUtil.notifyAll(mutex);
			}
		}
	}

	/**
	 * 
	 * grab the queue
	 * @param name - must not be null
	 * @param maxParallel  ignored if <=0 
	 * @return the queue to fill with tasks
	 */
	public static MultiTaskQueue getCreateQueue(String name, int maxParallel)
	{
		name = getThreadName(name);
		synchronized (theMap)
		{
			OrderedTaskQueue orderedTaskQueue = theMap.get(name);
			if (!(orderedTaskQueue instanceof MultiTaskQueue) || orderedTaskQueue.stop)
			{
				orderedTaskQueue = new MultiTaskQueue(name);
				theMap.put(name, orderedTaskQueue);
			}
			MultiTaskQueue multiTaskQueue = (MultiTaskQueue) orderedTaskQueue;
			multiTaskQueue.setMaxParallel(maxParallel);
			return multiTaskQueue;
		}
	}

	/**
	 *  
	 * @param maxParallel
	 */
	public void setMaxParallel(int maxParallel)
	{
		if (maxParallel > 0)
			this.maxParallel = maxParallel;
	}

	/**
	 * 
	 * get the number of currently running tasks
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
	public boolean interruptCurrent(int minAge)
	{
		Vector<NextRunner> currentCopy = new Vector<NextRunner>();
		currentCopy.addAll(current);
		boolean bRet = false;
		for (NextRunner next : currentCopy)
		{
			if (next.worker.getRunTime() >= minAge)
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
	public boolean interruptTask(Runnable theRunner)
	{
		Vector<NextRunner> currentCopy = new Vector<NextRunner>();
		currentCopy.addAll(current);
		boolean bRet = false;
		for (NextRunner next : currentCopy)
		{
			if (next.worker.theTask == theRunner)
			{
				interruptRunner(next);
				bRet = !current.contains(next);
				break;
			}
		}
		return bRet;
	}

	private void interruptRunner(NextRunner next)
	{
		int n = 0;
		while (current.contains(next))
		{
			next.interrupt();
			if (!ThreadUtil.sleep(++n) || n > 10)
				break;
		}
	}

	/**
	* @param minAge minimum age to interrupt
	* @return true if we successfully interrupted or no entries were running
	* 
	*/
	Vector<Runnable> getCurrent(int minAge)
	{
		Vector<NextRunner> currentCopy = new Vector<NextRunner>();
		currentCopy.addAll(current);

		Vector<Runnable> vReturn = new Vector<Runnable>();
		for (NextRunner next : currentCopy)
		{
			if (next.worker.getRunTime() >= minAge)
			{
				vReturn.add(next.worker.theTask);
			}
		}
		return vReturn;
	}

	/**
	 * @param name 
	 * 
	 */
	private MultiTaskQueue(String name)
	{
		super(name);
		current = new Vector<NextRunner>();
		totalParallel = 0;
		maxParallel = 2;
	}

	/**
	 * @see java.lang.Thread#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		return "MultiTaskQueue " + getName() + " " + stop + " queue: " + queue;
	}

	/**
	 * @see org.cip4.jdflib.util.thread.OrderedTaskQueue#getFirstTask()
	 */
	@Override
	synchronized TaskRunner getFirstTask()
	{
		if (getCurrentRunning() >= maxParallel)
			return null;
		TaskRunner r = super.getFirstTask();
		return r;
	}

	/**
	 */
	@Override
	void runTask(TaskRunner r)
	{
		NextRunner nextRunner = new NextRunner(r);
		current.add(nextRunner);
		nextRunner.start();
	}

}