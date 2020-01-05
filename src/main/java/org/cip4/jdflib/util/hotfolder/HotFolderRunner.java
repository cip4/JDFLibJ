/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util.hotfolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.hotfolder.HotFolder.HotFileRunner;
import org.cip4.jdflib.util.thread.MultiTaskQueue;
import org.cip4.jdflib.util.thread.MyMutex;

class HotFolderRunner extends Thread
{

	List<HotFolder> hotfolders;
	MyMutex mutex;
	private static Log log = LogFactory.getLog(HotFolderRunner.class);
	static final AtomicReference<HotFolderRunner> theRunner = new AtomicReference<>(null);

	public static void shutDown()
	{
		if (getTherunner() != null)
			getTherunner().quit();
	}

	/**
	 * stop this thread;
	 *
	 */
	void quit()
	{
		final String name = getName();
		log.info("Stopping hot folder runner: " + name);
		interrupt = true;
		ThreadUtil.notifyAll(mutex);
		MultiTaskQueue.shutDown(name);
		ThreadUtil.notifyAll(this);
		log.info("Finished stopping hot folder: " + name);
		theRunner.set(null);
	}

	/**
	 *
	 * @param hotFolder
	 */
	public synchronized void add(final HotFolder hotFolder)
	{
		hotfolders.remove(hotFolder);
		hotfolders.add(hotFolder);
		if (maxConcurrent < hotfolders.size() && maxConcurrent < 13)
			maxConcurrent = Math.min(13, hotfolders.size());
		ThreadUtil.notifyAll(mutex);
	}

	/**
	 *
	 * @param hotFolder
	 */
	public synchronized void remove(final HotFolder hotFolder)
	{
		hotfolders.remove(hotFolder);
		if (hotfolders.isEmpty())
		{
			quit();
		}

	}

	public HotFolderRunner()
	{
		super("HotFolderRunner");
		hotfolders = new ArrayList<>();
		setDaemon(true);
		log.info("Starting hotfolder runner thread");
		interrupt = false;
		mutex = new MyMutex();
		start();
	}

	/**
	 * @param maxConcurrent the maxConcurrent to set
	 */
	public void setMaxConcurrent(int maxConcurrent)
	{
		if (maxConcurrent < 1)
			maxConcurrent = 1;
		if (maxConcurrent > this.maxConcurrent)
		{
			if (maxConcurrent > 13)
			{
				maxConcurrent = 13;
			}
			this.maxConcurrent = maxConcurrent;
		}
	}

	boolean interrupt;
	int maxConcurrent = 1;

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		log.info("start of loop " + this);
		while (!interrupt)
		{
			final long t0 = System.currentTimeMillis();
			boolean mod = false;
			final ArrayList<HotFolder> local = new ArrayList<>(hotfolders);
			for (final HotFolder folder : local)
			{
				mod = folder.loop() || mod;
				if (interrupt)
					break;
			}
			if (!interrupt)
			{
				final long t1 = System.currentTimeMillis();
				final int millis = mod ? 0 : HotFolder.getDefaultStabilizeTime() - (int) (t1 - t0);
				ThreadUtil.wait(mutex, Math.max(100, millis));
			}
		}
		log.info("end of loop " + this);
	}

	/**
	 * @return the therunner
	 */
	static HotFolderRunner getCreateTherunner()
	{
		synchronized (theRunner)
		{
			if (theRunner.get() == null)
				theRunner.set(new HotFolderRunner());
			return theRunner.get();
		}
	}

	/**
	 * @return the therunner
	 */
	static HotFolderRunner getTherunner()
	{
		synchronized (theRunner)
		{
			final HotFolderRunner hotFolderRunner = theRunner.get();

			return hotFolderRunner == null || hotFolderRunner.interrupt ? null : hotFolderRunner;
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "HotFolderRunner [" + (hotfolders != null ? "hotfolder size=" + hotfolders.size() + ", " : "") + "interrupt=" + interrupt + ", maxConcurrent=" + maxConcurrent + "]";
	}

	/**
	 * @return the maxConcurrent
	 */
	public int getMaxConcurrent()
	{
		return maxConcurrent;
	}

	boolean runFile(final HotFileRunner runner)
	{
		if (getMaxConcurrent() == 1)
		{
			runner.run();
		}
		else
		{
			final MultiTaskQueue taskQueue = MultiTaskQueue.getCreateQueue(getName(), getMaxConcurrent());
			return taskQueue.queue(runner);
		}
		return true;
	}
}