package org.cip4.jdflib.util.hotfolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.ThreadUtil;
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

	public void add(final HotFolder hotFolder)
	{
		hotfolders.remove(hotFolder);
		hotfolders.add(hotFolder);
		if (maxConcurrent < hotfolders.size() && maxConcurrent < 13)
			maxConcurrent = Math.min(13, hotfolders.size());
		ThreadUtil.notifyAll(mutex);
	}

	public void remove(final HotFolder hotFolder)
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
		log.info("Starting hotfolder runner");
		interrupt = false;
		start();
		mutex = new MyMutex();
	}

	/**
	 * @param maxConcurrent the maxConcurrent to set
	 */
	public void setMaxConcurrent(int maxConcurrent)
	{
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
	static HotFolderRunner getTherunner()
	{
		synchronized (theRunner)
		{
			if (theRunner.get() == null)
				theRunner.set(new HotFolderRunner());
			return theRunner.get();
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
}