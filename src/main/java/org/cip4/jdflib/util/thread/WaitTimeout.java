/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of 
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

import org.cip4.jdflib.util.ThreadUtil;

/**
 * abstract class to run uninteruptable stuff in an interruptable thread
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 11.12.2008
 * @param <a> the returned object data type
 */
public abstract class WaitTimeout<a> implements Runnable
{
	/**
	 * this is the handle routine that may take longer <br/>
	 * it should return the expected object when completed
	 * 
	 * @return the object that this whole class is about
	 */
	protected abstract a handle();

	/**
	 * called prior to starting thread, overwrite to initialize in the constructor
	 */
	protected void setup()
	{
		baseName = "WaitThread";
	}

	private MyMutex mutex;
	private long t0;
	private final int waitMillis;
	private a theObject;
	private static int threadNumber = 0;
	private Thread myThread = null;
	protected String baseName;

	/**
	 * @param millis wait timeout in milliseconds
	 */
	public WaitTimeout(final int millis)
	{
		waitMillis = millis;
		theObject = null;
		setup();
		mutex = new MyMutex();
		myThread = new Thread(this, baseName + threadNumber++);
		myThread.setDaemon(true);
		t0 = System.currentTimeMillis();
	}

	/**
	 * 
	 * start the thread
	 */
	public final void start()
	{
		myThread.start();
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public final void run()
	{
		t0 = System.currentTimeMillis();
		theObject = handle();
		myThread = null;
		MyMutex tmp = mutex;
		mutex = null;
		ThreadUtil.notifyAll(tmp);
	}

	/**
	 * @return the object that you waited for, null if the timeout is reached
	 */
	public final a getWaitedObject()
	{
		long t1 = System.currentTimeMillis();
		if ((t1 - t0 < waitMillis) && (mutex != null))
		{
			long dt = waitMillis - (t1 - t0);
			ThreadUtil.wait(mutex, (int) dt);
			if (mutex != null && myThread != null)
			{
				myThread.interrupt();
			}
			mutex = null;
		}
		return theObject;
	}

	/**
	 * @return the object that you waited for, if it has been calculated
	 */
	public final a peekWaitedObject()
	{
		return theObject;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "WaitTimeout [t0=" + t0 + ", waitMillis=" + waitMillis + ", theObject=" + theObject + "]";
	}

}