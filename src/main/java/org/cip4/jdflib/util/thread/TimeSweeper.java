/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.util.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.JDFDate;

/**
 * class that sweeps at a regular interval - useful in case multiple sweepers with differing timing requirements are run
 *
 * @author rainer prosi
 * @date Dec 10, 2011
 */
public class TimeSweeper implements Sweeper
{
	protected Log log;
	protected long t0;
	protected long nSweep;
	private long interval;
	protected Runnable runner;

	/**
	 * @param interval the time interval in seconds
	 *
	 */
	protected TimeSweeper(final int interval)
	{
		super();
		log = LogFactory.getLog(getClass());
		t0 = -1;
		nSweep = 0;
		setInterval(interval);
		runner = (Runnable) ((this instanceof Runnable) ? this : null);
	}

	/**
	 * @param interval the time interval in seconds
	 * @param runner the runnable to run
	 *
	 */
	public TimeSweeper(final int interval, final Runnable runner)
	{
		this(interval);
		this.runner = runner;
	}

	/**
	 *
	 *
	 * @param interval in seconds
	 */
	public void setInterval(final int interval)
	{
		this.interval = 1000l * interval;
	}

	/**
	 *
	 *
	 * @param interval in seconds
	 */
	public void setFirstInterval(final int interval)
	{
		this.t0 = System.currentTimeMillis() + 1000 * interval;
	}

	/**
	 *
	 * get the subclass or class of the runner
	 *
	 * @return
	 */
	public Class<?> getRunnerClass()
	{
		return runner == null ? getClass() : runner.getClass();
	}

	/**
	 * if true we want to sweep
	 *
	 * @see org.cip4.jdflib.util.thread.Sweeper#needSweep()
	 */
	@Override
	public boolean needSweep()
	{
		final long t = System.currentTimeMillis();
		final boolean needsweep = t > t0;
		if (needsweep)
			t0 = t + interval;
		return needsweep;
	}

	/**
	 * default is nop or runner.run
	 *
	 * @see org.cip4.jdflib.util.thread.Sweeper#sweep()
	 */
	@Override
	public boolean sweep()
	{
		if (runner != null)
		{
			nSweep++;
			if (nSweep < 10 || nSweep % 1000 == 0)
			{
				log.info(getClass().getSimpleName() + " Start sweeping# " + nSweep);
			}
			runner.run();
			if (nSweep < 10 || nSweep % 1000 == 0)
			{
				log.info(getClass().getSimpleName() + " Completed sweeping# " + nSweep);
			}
		}
		t0 = System.currentTimeMillis() + interval;
		return runner != null;
	}

	/**
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + " interval=" + interval + " nSweep: " + nSweep + " t0: " + new JDFDate(t0).getDateTimeISO();
	}

}
