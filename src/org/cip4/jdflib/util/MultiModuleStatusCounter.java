/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
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

import java.util.Vector;

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;

/**
 * @author prosirai module combining statuscounter simply update the child status counters regularly. call
 *         getStatusResponse to generate a new Response based on the data in the statuscounters
 * 
 */
public class MultiModuleStatusCounter
{
	private final Vector<StatusCounter> counters = new Vector<StatusCounter>();

	public MultiModuleStatusCounter()
	{
		super();
	}

	/**
	 * add a statuscounter representing a set of modules to this device status counter
	 * 
	 * @param theStatusCounter the statuscounter to add
	 */
	public void addModule(StatusCounter sc)
	{
		if (sc != null)
			counters.add(sc);
	}

	/**
	 * remove a statuscounter representing a set of modules to this device status counter
	 * 
	 * @param theStatusCounter the statuscounter to add
	 */
	public void removeModule(StatusCounter sc)
	{
		if (sc != null)
			counters.remove(sc);
	}

	/**
	 * return the jmf root of the status jmf that contains all modules, null if no modules are active
	 * 
	 * @return
	 */
	public JDFDoc getStatusResponse()
	{
		if (counters.size() == 0)
			return null;

		StatusCounter root = counters.elementAt(0);
		JDFDoc d = new JDFDoc("JMF");
		final JDFJMF jmf = d.getJMFRoot();
		jmf.mergeElement(root.getDocJMFPhaseTime().getJMFRoot(), false);
		JDFDeviceInfo di = jmf.getResponse(0).getDeviceInfo(0);
		for (int i = 1; i < counters.size(); i++)
		{
			StatusCounter counter = counters.elementAt(i);
			final JDFDoc docJMFPhaseTime = counter.getDocJMFPhaseTime();
			if (docJMFPhaseTime == null)
				continue;
			JDFDeviceInfo di2 = docJMFPhaseTime.getJMFRoot().getResponse(0).getDeviceInfo(0);
			VElement phases = di2.getChildElementVector(ElementName.JOBPHASE, null, null, true, -1, false);
			for (int j = 0; j < phases.size(); j++)
				di.copyElement(phases.elementAt(j), null);
			di.setDeviceStatus(getDeviceStatus());
		}
		return d;
	}

	/**
	 * @return the amalgamated device status
	 */
	public EnumDeviceStatus getDeviceStatus()
	{
		final int counterSize = counters.size();
		if (counterSize == 0)
			return EnumDeviceStatus.Idle;
		EnumDeviceStatus maxStatus = null;
		for (int i = 0; i < counterSize; i++)
		{
			maxStatus = (EnumDeviceStatus) EnumUtil.max(maxStatus, counters.elementAt(i).getStatus());
		}
		return maxStatus;
	}
}