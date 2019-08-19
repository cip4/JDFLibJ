/*
 *
 * The CIP4 Software License, Version 1.0
 *
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
package org.cip4.jdflib.util.thread;

import java.util.HashSet;
import java.util.Set;

import org.cip4.jdflib.util.MyPair;

/**
 * class to run multiple tasks in parallel while ensuring that all tasks that belong to one job remain in order
 *
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *
 */
public class MultiJobTaskQueue extends MultiTaskQueue
{
	class RunPair extends MyPair<Runnable, Object> implements Runnable
	{

		public RunPair(final Runnable ap, final Object bp)
		{
			super(ap, bp);
		}

		@Override
		public void run()
		{
			a.run();
			setMutex.remove(b);
		}

	}

	final Set<Object> setMutex = new HashSet<>();

	/**
	 *
	 * grab the queue
	 *
	 * @param name - must not be null
	 * @param maxParallel ignored if <=0
	 * @return the queue to fill with tasks
	 */
	public static MultiJobTaskQueue getCreateJobQueue(String name, final int maxParallel)
	{
		name = getThreadName(name);
		synchronized (theMap)
		{
			OrderedTaskQueue orderedTaskQueue = theMap.get(name);
			if (!(orderedTaskQueue instanceof MultiJobTaskQueue))
			{
				orderedTaskQueue = new MultiJobTaskQueue(name);
				theMap.put(name, orderedTaskQueue);
			}
			final MultiJobTaskQueue multiTaskQueue = (MultiJobTaskQueue) orderedTaskQueue;
			multiTaskQueue.setMaxParallel(maxParallel);
			return multiTaskQueue;
		}
	}

	/**
	 *
	 * @param name
	 */
	MultiJobTaskQueue(final String name)
	{
		super(name);
	}

	/**
	 * @see org.cip4.jdflib.util.thread.MultiTaskQueue#getFirstTask()
	 */
	@Override
	synchronized TaskRunner getFirstTask()
	{
		final TaskRunner firstTask = getCurrentRunning() >= getMaxParallel() ? null : getNonRunning();
		if (firstTask == null)
		{
			idle.set(0);
		}
		else
		{
			addTask(firstTask);
		}
		return firstTask;
	}

	/**
	 *
	 * @return
	 */
	TaskRunner getNonRunning()
	{
		synchronized (queue)
		{
			int n = 0;
			for (final TaskRunner r : queue)
			{
				if (!setMutex.contains(getObject(r)))
				{
					return queue.remove(n);
				}
				n++;
			}
		}
		return null;
	}

	/**
	 *
	 * @param firstTask
	 */
	void addTask(final TaskRunner firstTask)
	{
		setMutex.add(getObject(firstTask));
	}

	/**
	 *
	 * @param firstTask
	 * @return
	 */
	Object getObject(final TaskRunner firstTask)
	{
		return ((RunPair) firstTask.theTask).b;
	}

	/**
	 *
	 * @param task
	 * @param mutex all tasks wit the same mutex will be run in sequence
	 * @return
	 */
	public boolean queue(final Runnable task, final Object mutex)
	{
		final RunPair rp = new RunPair(task, mutex);
		return super.queue(rp);
	}

	/**
	 * ensure we don't accidentally queue non-runpairs
	 *
	 * @see org.cip4.jdflib.util.thread.OrderedTaskQueue#queue(java.lang.Runnable)
	 */
	@Override
	public boolean queue(final Runnable task)
	{
		return queue(task, new MyMutex());
	}

}