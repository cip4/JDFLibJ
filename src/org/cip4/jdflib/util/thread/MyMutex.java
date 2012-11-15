package org.cip4.jdflib.util.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * placeholder for future use
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 11.12.2008
 */
public class MyMutex
{

	/**
	 * 
	 */
	public MyMutex()
	{
		super();
		iMutex = nMutex.incrementAndGet();
		ownerThread = Thread.currentThread().getName();
	}

	final private int iMutex;
	private static AtomicInteger nMutex = new AtomicInteger(0);
	private final String ownerThread;

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MyMutex: " + iMutex + " [" + ownerThread + "]";
	}
}