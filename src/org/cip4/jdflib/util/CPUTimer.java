/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of 
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

import java.lang.management.ThreadMXBean;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;

import sun.management.ManagementFactory;

/**
 * class for cpu time measurements
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class CPUTimer
{

	private final long createT0;
	private long currentT0;
	private long cpuT0;
	private long totalT0;
	private long totalCpuT0;
	private final ThreadMXBean bean;
	private final boolean threadCpuTimeEnabled;
	private int nStartStop;
	private String name = null;

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @param bStart if true, start measuring now
	 * 
	 */
	public CPUTimer(boolean bStart)
	{
		super();
		bean = ManagementFactory.getThreadMXBean();
		threadCpuTimeEnabled = bean.isThreadCpuTimeEnabled();
		currentT0 = -1;
		cpuT0 = -1;
		totalCpuT0 = 0;
		totalT0 = 0;
		nStartStop = 0;
		createT0 = System.currentTimeMillis();
		if (bStart)
			start();
	}

	/**
	 * method to get the amount of cpu time since construction
	 * @return  amount of cpu time in nano seconds
	 * 
	 *
	 */
	public long getTotalCPUTime()
	{
		if (!threadCpuTimeEnabled)
			return -1;
		return totalCpuT0 + getCurrentCPUTime();
	}

	/**
	 * @return
	 */
	public long getCurrentCPUTime()
	{
		if (!threadCpuTimeEnabled)
			return -1;
		else if (cpuT0 > 0)
			return bean.getCurrentThreadCpuTime() - cpuT0;
		else
			return 0;
	}

	/**
	 * 
	 * @return  
	 * 
	 *
	 */
	public long getCreationTime()
	{
		return createT0;
	}

	/**
	 * method to get the amount of real time since construction in milliseconds
	 * @return amount of real in milliseconds
	 * 
	 *
	 */
	public long getTotalRealTime()
	{
		return totalT0 + getCurrentRealTime();
	}

	/**
	 * @return
	 */
	public long getCurrentRealTime()
	{
		if (currentT0 > 0)
			return System.currentTimeMillis() - currentT0;
		else
			return 0;
	}

	/**
	 * start measuring times
	 * 
	 *
	 */
	public void start()
	{
		if (currentT0 > 0)
			return;
		if (threadCpuTimeEnabled)
			cpuT0 = bean.getCurrentThreadCpuTime();
		currentT0 = System.currentTimeMillis();
		nStartStop++;
	}

	/**
	 * stop/pause measuring times
	 * 
	 *
	 */
	public void stop()
	{
		if (currentT0 <= 0)
			return;
		if (threadCpuTimeEnabled)
			totalCpuT0 = getTotalCPUTime();
		totalT0 = getTotalRealTime();
		cpuT0 = -1;
		currentT0 = -1;
	}

	/**
	 * @return
	 */
	public long getNumStarts()
	{
		return nStartStop;
	}

	/**
	 * @return
	 */
	public long getAverageRealTime()
	{
		return nStartStop == 0 ? 0 : getTotalRealTime() / nStartStop;
	}

	/**
	 * @return
	 */
	public long getAverageCPUTime()
	{
		return nStartStop == 0 ? 0 : getTotalCPUTime() / nStartStop;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		String label = "CPUTimer: ";
		if (name != null)
			label += getName();
		return label + " totalCPU=" + getTotalCPUTime() / 1000000000. + " currentCPU=" + getCurrentCPUTime() / 1000000000. + " totalT=" + getTotalRealTime() / 1000. + " currentT="
				+ getCurrentRealTime() / 1000. + " starts=" + nStartStop + " active=" + (currentT0 > 0);
	}

	/**
	 * 
	 * @return
	 */
	public KElement toXML()
	{
		KElement root = new XMLDoc("CPUTimer", null).getRoot();
		root.setAttribute("TotalRealTime", getTotalRealTime() / 1000., null);
		root.setAttribute("CurrentRealTime", getCurrentRealTime() / 1000., null);
		root.setAttribute("AverageRealTime", getAverageRealTime() / 1000., null);
		root.setAttribute("TotalCPUTime", getTotalCPUTime() / 1000000000., null);
		root.setAttribute("CurrentCPUTime", getCurrentCPUTime() / 1000000000., null);
		root.setAttribute("AverageCPUTime", getAverageCPUTime() / 1000000000., null);
		root.setAttribute("CreationTime", new JDFDate(getCreationTime()).getFormattedDateTime("hh:mm ss.sss"), null);
		root.setAttribute("StartStop", getNumStarts(), null);
		return root;
	}
}
