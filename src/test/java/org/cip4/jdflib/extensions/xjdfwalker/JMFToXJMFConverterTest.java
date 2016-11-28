/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.xjdfwalker;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFQuery;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.process.JDFPerson;
import org.junit.Test;

/**
 * @author rainer prosi
 *  
 */
public class JMFToXJMFConverterTest extends JDFTestCaseBase
{
	/**
	 * 
	 */
	@Test
	public void testPipeJMF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.PipeClose);
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals(xjmf.getXPathAttribute("CommandPipeControl/PipeParams/@Operation", null), "PipeClose");
		final JDFJMF jmfResp = JDFJMF.createJMF(EnumFamily.Response, JDFMessage.EnumType.PipeClose);
		xjmf = conv.makeNewJMF(jmfResp);
		assertEquals(xjmf.getElement(null).getLocalName(), "ResponsePipeControl");
	}

	/**
	 * 
	 */
	@Test
	public void testSubscriptionJMF()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildStatusSubscription("url", 42, 21, "qe33");
		jmf.getQuery(0).getSubscription().appendObservationTarget().setAttributes(new VString("a", null));
		jmf.getQuery(0).getStatusQuParams().setQueueInfo(true);
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		assertNull(xjmf.getChildByTagName(ElementName.OBSERVATIONTARGET, null, 0, null, false, false));
		KElement subscription = xjmf.getChildByTagName(ElementName.SUBSCRIPTION, null, 0, null, false, false);
		assertNotNull(subscription);
		assertFalse(subscription.hasAttribute(AttributeName.REPEATSTEP));
	}

	/**
	 * 
	 */
	@Test
	public void testStatusJMF()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildStatusSubscription("url", 42, 21, "qe33");
		jmf.getQuery(0).getSubscription().appendObservationTarget().setAttributes(new VString("a", null));
		jmf.getQuery(0).getStatusQuParams().setQueueInfo(true);
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		KElement statusquparams = xjmf.getChildByTagName(ElementName.STATUSQUPARAMS, null, 0, null, false, false);
		assertFalse(statusquparams.hasAttribute(AttributeName.QUEUEINFO));
	}

	/**
	 * 
	 */
	@Test
	public void testMoveToSender()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceQuery(true);
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		KElement sender = xjmf.getElement(XJDFConstants.SENDER);
		assertNotNull(sender);
		assertEquals(jmf.getID(), sender.getID());
	}

	/**
	 * 
	 */
	@Test
	public void testMoveToSenderEmployee()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceQuery(true);
		JDFQuery q = jmf.getQuery(0);
		q.appendEmployee().setPersonalID("P123");
		JDFPerson person = q.getEmployee(0).appendPerson();
		person.setFirstName("Franz");
		person.setFamilyName("meier");
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		KElement sender = xjmf.getXPathElement("QueryResource/Sender");
		assertNotNull(sender);
		assertEquals(q.getEmployee(0).getPersonalID(), sender.getAttribute(AttributeName.PERSONALID));
		assertEquals(q.getEmployee(0).getDescriptiveName(), sender.getAttribute(AttributeName.AUTHOR));
	}

	/**
	 * 
	 */
	@Test
	public void testMoveToSenderTime()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceQuery(true);
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		KElement sender = xjmf.getElement(XJDFConstants.SENDER);
		assertNotNull(sender);
		assertEquals(jmf.getTimeStamp().getDateTimeISO(), sender.getAttribute(AttributeName.TIME));
	}

	/**
	 * 
	 */
	@Test
	public void testMoveToSenderSenderID()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceQuery(true);
		jmf.setSenderID("s1");
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		KElement sender = xjmf.getElement(XJDFConstants.SENDER);
		assertNotNull(sender);
		assertEquals(jmf.getSenderID(), sender.getAttribute(AttributeName.DEVICEID));
	}

	/**
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception
	{
		XJMFTypeMap.shutDown();
		super.tearDown();
	}

	/**
	 * 
	 * test ink resource signal
	 */
	@Test
	public void testBuildResourceSignalInkLot()
	{
		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).buildResourceSignal(false, null);

		JDFSignal signal = jmf.getSignal(0);
		JDFResourceQuParams rqp = signal.getCreateResourceQuParams(0);
		rqp.setJobID("job1");
		rqp.setJobPartID("ConvPrint.1");
		JDFResourceInfo ri = signal.getCreateResourceInfo(0);
		ri.setResourceName(ElementName.INK);
		ri.setUnit("g");
		JDFAmountPool ap = ri.getCreateAmountPool();
		JDFAttributeMap map = new JDFAttributeMap();
		map.put(AttributeName.SIGNATURENAME, "sig1");
		map.put(AttributeName.SHEETNAME, "s1");
		map.put(AttributeName.SIDE, "Front");
		for (int i = 1; i < 3; i++)
		{
			for (String sep : new VString("Cyan Magenta Yellow Black Grün", null))
			{
				map.put(AttributeName.SEPARATION, sep);
				map.put(AttributeName.LOTID, "Los_" + i + "_" + sep);
				ap.appendPartAmount(map).setActualAmount(125 + (i * 42 * sep.hashCode()) % 123);
			}
		}
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		assertNotNull(xjmf.getXPathAttribute("SignalResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/Part/@LotID", null));
		assertEquals(xjmf.getXPathAttribute("SignalResource/ResourceInfo/ResourceSet/Resource/AmountPool/PartAmount/Part/@LotID", null), jmf.getXPathAttribute("Signal/ResourceInfo/AmountPool/PartAmount/Part/@LotID", null));
		xjmf.write2File(sm_dirTestDataTemp + "resourceInk.xjmf");
	}

}
