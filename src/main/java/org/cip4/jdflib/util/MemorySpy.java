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
/**
 *
 */
package org.cip4.jdflib.util;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class MemorySpy
{
	private static final String PEAK = "Peak";
	public static final String CURRENT = "Current";
	public static final String TOTAL = "Total";
	public static final String FREE = "Free";
	private final List<MemoryPoolMXBean> memList;
	private final MemoryMXBean mainBean;
	static final long MEGA = 1024l * 1024l;
	private boolean wantMega;
	private static boolean defaultMega = false;

	/**
	 * @return the defaultMega
	 */
	public static boolean isDefaultMega()
	{
		return defaultMega;
	}

	/**
	 * @param defaultMega the defaultMega to set
	 */
	public static void setDefaultMega(final boolean defaultMega)
	{
		MemorySpy.defaultMega = defaultMega;
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen * the scope of the
	 */
	public static enum MemScope
	{
		/**		 */
		current,
		/**	 */
		peak,
		/**			 */
		init,
		/**			 */
		peakCommit,
		/**			 */
		commit
	}

	/**
	 *
	 */
	public MemorySpy()
	{
		memList = ManagementFactory.getMemoryPoolMXBeans();
		mainBean = ManagementFactory.getMemoryMXBean();
		wantMega = defaultMega;
	}

	/**
	 *
	 * @return
	 */
	public Map<String, Long> getSizeMap()
	{
		final HashMap<String, Long> map = new HashMap<>();
		map.put("heap", Long.valueOf(mainBean.getHeapMemoryUsage().getUsed()));
		map.put("non-heap", Long.valueOf(mainBean.getNonHeapMemoryUsage().getUsed()));
		final Iterator<MemoryPoolMXBean> it = memList.iterator();
		while (it.hasNext())
		{
			final MemoryPoolMXBean poolBean = it.next();
			map.put(poolBean.getName(), Long.valueOf(poolBean.getUsage().getUsed()));
			map.put("comitted " + poolBean.getName(), Long.valueOf(poolBean.getUsage().getCommitted()));
			map.put("peak " + poolBean.getName(), Long.valueOf(poolBean.getPeakUsage().getUsed()));
		}
		return map;
	}

	/**
	 *
	 * @param scope
	 * @return
	 */
	public long getHeapUsed(final MemScope scope)
	{
		final MemoryUsage usage = mainBean.getHeapMemoryUsage();
		return getMem(usage, scope) / getFactor();
	}

	/**
	 *
	 * @param scope
	 * @return
	 */
	public long getNonHeapUsed(final MemScope scope)
	{
		final MemoryUsage usage = mainBean.getNonHeapMemoryUsage();
		return getMem(usage, scope) / getFactor();
	}

	/**
	 *
	 * @param scope
	 * @return
	 */
	public long getPermGen(final MemScope scope)
	{
		return getMemFromPool("Perm Gen", scope);
	}

	/**
	 * @param name
	 * @return
	 */
	private MemoryPoolMXBean getBeanFromPool(final String name)
	{
		final Iterator<MemoryPoolMXBean> it = memList.iterator();
		while (it.hasNext())
		{
			final MemoryPoolMXBean poolBean = it.next();
			if (name.endsWith(poolBean.getName()))
				return poolBean;
		}
		return null;
	}

	private long getMem(final MemoryUsage usage, final MemScope scope)
	{
		if (MemScope.current.equals(scope) || MemScope.peak.equals(scope))
		{
			return usage.getUsed();
		}
		else if (MemScope.commit.equals(scope) || MemScope.peak.equals(scope))
		{
			return usage.getCommitted();
		}
		else if (MemScope.init.equals(scope))
		{
			return usage.getInit();
		}

		return -1;

	}

	/**
	 *
	 * @param name
	 * @param scope
	 * @return
	 */
	public long getMemFromPool(final String name, final MemScope scope)
	{
		final MemoryPoolMXBean bean = getBeanFromPool(name);
		if (bean == null)
			return -1;
		final boolean peak = MemScope.peak.equals(scope) || MemScope.peakCommit.equals(scope);
		final MemoryUsage usage = peak ? bean.getPeakUsage() : bean.getUsage();
		return getMem(usage, scope) / getFactor();
	}

	/**
	 *
	 * get a fast summary for debugging
	 *
	 * @return
	 */
	public String getSummary(final String delim)
	{
		final StringBuilder b = new StringBuilder();
		final Map<String, Long> map = getSummaryMap();
		final List<String> keyList = ContainerUtil.getKeyList(map);
		keyList.sort(null);
		for (final String s : keyList)
			b.append("Mem ").append(s).append(": ").append(map.get(s)).append(delim);
		return b.toString();
	}

	/**
	 *
	 * get a fast summary for debugging
	 *
	 * @return
	 */
	public String getSummary()
	{
		return getSummary("\n");
	}

	/**
	 * get the currently used memory
	 *
	 * @return the used memory
	 */
	public long getCurrentMem()
	{
		final Runtime rt = Runtime.getRuntime();
		return (rt.totalMemory() - rt.freeMemory()) / getFactor();
	}

	/**
	 *
	 * get a fast summary for debugging
	 *
	 * @return
	 */
	public Map<String, Long> getSummaryMap()
	{
		final HashMap<String, Long> map = new HashMap<>();
		map.put(FREE, getFreeMem());
		map.put(TOTAL, getTotalMemory());
		map.put(CURRENT, getCurrentMem());
		map.put(PEAK, getHeapUsed(MemScope.peak));
		return map;
	}

	/**
	 *
	 * @return
	 */
	public long getTotalMemory()
	{
		return Runtime.getRuntime().totalMemory() / getFactor();
	}

	/**
	 *
	 * @return
	 */
	public long getFreeMem()
	{
		return Runtime.getRuntime().freeMemory() / getFactor();
	}

	long getFactor()
	{
		final long div = wantMega ? MEGA : 1l;
		return div;
	}

	/**
	 *
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		final Map<String, Long> sizeMap = getSizeMap();
		final List<String> keys = ContainerUtil.getKeyList(sizeMap);
		Collections.sort(keys);
		final StringBuilder b = new StringBuilder("MemorySpy: \n");
		for (final String key : keys)
		{
			b.append(key);
			b.append(" = ");
			b.append(sizeMap.get(key));
			b.append("\n");
		}
		return b.toString();
	}

	/**
	 *
	 * @return
	 */
	public boolean isWantMega()
	{
		return wantMega;
	}

	/**
	 * if true we want it in Megabytes
	 *
	 * @param wantMega
	 */
	public void setWantMega(final boolean wantMega)
	{
		this.wantMega = wantMega;
	}
}
