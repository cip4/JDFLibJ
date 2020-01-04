/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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
	 *
	 *
	 * @author rainer prosi
	 * @date Nov 14, 2012
	 * @deprecated use org.cip4.jdflib.util.thread.MyMutex
	 */
	@Deprecated
	public static class MyMutex extends org.cip4.jdflib.util.thread.MyMutex
	{

		/**
		 *
		 */
		public MyMutex()
		{
			super();
		}

	}

	/**
	 * abstract class to run uninteruptable stuff in an interruptable thread
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 *
	 * 11.12.2008
	 * @param <a> the returned object data type
	 * @deprecated use the local class
	 */
	@Deprecated
	public static abstract class WaitTimeout<a> extends org.cip4.jdflib.util.thread.WaitTimeout<a>
	{

		public WaitTimeout(final int millis)
		{
			super(millis);
		}

	}

	/**
	 * simple sleep wrapper that catches its exception
	 *
	 * @param millis
	 * @return true if normal exit, false if interupted
	 */
	public static boolean sleep(final int millis)
	{
		if (millis <= 0)
		{
			return true;
		}
		try
		{
			Thread.sleep(millis);
			return true;
		}
		catch (final InterruptedException x)
		{
			return false;
		}
	}

	/**
	 * simple join wrapper that catches its exception
	 * @param thread the thread to join
	 * @param millis
	 * @return true if normal exit, false if interupted
	 */
	public static boolean join(final Thread thread, final int millis)
	{
		if (thread == null)
		{
			return true;
		}
		try
		{
			if (millis <= 0)
			{
				thread.join();
			}
			else
			{
				thread.join(millis);
			}
			return true;
		}
		catch (final InterruptedException x)
		{
			return false;
		}
	}

	/**
	 * simple wait wrapper that synchronizes catches its exception
	 *
	 * @param mutex the object that will wait, if null we assume we need not wait
	 * @param millis milliseconds to wait, 0 or lower: indefinite wait
	 * @return true if normal exit, false if interupted or mutex is null
	 */
	public static boolean wait(final Object mutex, int millis)
	{
		if (millis < 0)
		{
			millis = 0;
		}
		if (mutex == null)
		{
			return false;
		}
		try
		{
			synchronized (mutex)
			{
				mutex.wait(millis);
			}
			return true;
		}
		catch (final InterruptedException x)
		{
			return false;
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
