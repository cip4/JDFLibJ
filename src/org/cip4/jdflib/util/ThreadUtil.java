/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.util;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class ThreadUtil
{

	/**
	 * placeholder for future use
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * 11.12.2008
	 */
	public static class MyMutex
	{
		// placeholder
	}

	/**
	 * abstract class to run unteruptable stuff in an interruptable thread
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * 11.12.2008
	 * @param <a> the returned object data type
	 */
	public static abstract class WaitTimeout<a> implements Runnable
	{
		/**
		 * this is the handle routine that may take longer it should return the expected object when completed
		 * 
		 * @return the object that this whole class is about
		 */
		protected abstract a handle();

		/**
		 * called prior to starting thread, overwrite to initialize in the constructor
		 */
		protected void setup()
		{
			// nop
		}

		private MyMutex mutex;
		private final int waitMillis;
		private a theObject;
		private static int threadNumber = 0;
		private Thread myThread = null;

		/**
		 * @param millis wait timeout in milliseconds
		 */
		public WaitTimeout(final int millis)
		{

			waitMillis = millis;
			theObject = null;
			setup();
			mutex = new MyMutex();
			myThread = new Thread(this, "WaitThread" + threadNumber++);
			myThread.start();
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		public final void run()
		{
			theObject = handle();
			ThreadUtil.notifyAll(mutex);
			mutex = null;
		}

		/**
		 * @return the object that you waited for, null if the timeout is reached
		 */
		public final a getWaitedObject()
		{
			if (mutex != null)
			{
				ThreadUtil.wait(mutex, waitMillis);
				mutex = null;
			}
			return theObject;
		}

	}

	/**
	 * simple sleep wrapper that catches its exception
	 * 
	 * @param millis
	 */
	public static void sleep(final int millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch (final InterruptedException x)
		{
			// System.out.print(".");
		}
	}

	/**
	 * simple wait wrapper that synchronizes catches its exception
	 * 
	 * @param mutex the object that will wait
	 * @param millis milliseconds to wait, 0 or lower: indefinite wait
	 */
	public static void wait(Object mutex, int millis)
	{
		if (millis < 0)
		{
			millis = 0;
		}
		if (mutex == null)
		{
			mutex = new MyMutex();
		}
		try
		{
			synchronized (mutex)
			{
				mutex.wait(millis);
			}
		}
		catch (final InterruptedException x)
		{
			// nop
		}
	}

	/**
	 * simple notify that catches any and all exceptions
	 * @param mutex the mutex to notify
	 */
	public static void notifyAll(final Object mutex)
	{
		if (mutex != null)
		{
			synchronized (mutex)
			{
				mutex.notifyAll();
			}
		}
	}

	/**
	 * simple notify that catches any and all exceptions
	 * @param mutex the mutex to notify
	 */
	public static void notify(final Object mutex)
	{
		if (mutex != null)
		{
			synchronized (mutex)
			{
				mutex.notify();
			}
		}
	}
}
