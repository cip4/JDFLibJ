/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.examples;

import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoResourceQuParams.EnumScope;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumDeviceDetails;
import org.cip4.jdflib.auto.JDFAutoStatusQuParams.EnumJobDetails;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.jmf.JDFDeviceFilter;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFJobPhase;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFQuery;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.jmf.JMFBuilder;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.junit.Test;

/**
 * jmf example file test
 * 
 * @author rainer prosi
 * @date Nov 22, 2011
 */
public class JMFExampleTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	public JMFExampleTest()
	{
		super();
	}

	/**
	 *
	 * new activity element in JobPhase
	 */
	@Test
	public void testActivity()
	{
		final JMFBuilder b = JMFBuilderFactory.getJMFBuilder(null);
		final JDFJMF jmf = b.buildStatusSignal(EnumDeviceDetails.Full, EnumJobDetails.MIS);
		final JDFSignal signal = jmf.getSignal(0);
		final JDFDeviceInfo di = signal.getDeviceInfo(0);
		{
			final KElement activity = di.appendElement("Activity");
			activity.setAttribute("PersonalID", "P1");
			activity.setAttribute("ActivityName", "Polishing");
			activity.setAttribute("ActivityID", "ID1234");
			activity.setXMLComment("The following activity is NOT job related (direct child of deviceInfo) \ndo we need both cost center and MISDetails here?", true);
			di.appendElement(ElementName.EMPLOYEE).setAttribute("PersonalID", "P1");
			di.appendElement(ElementName.EMPLOYEE).setAttribute("PersonalID", "P2");
			di.appendElement(ElementName.EMPLOYEE).setAttribute("PersonalID", "P3");
		}
		{
			final JDFJobPhase jp = di.getJobPhase(0);
			jp.setJobID("j1");
			jp.setJobPartID("p1");
			jp.setStatus(EnumNodeStatus.InProgress);
			KElement activity = jp.appendElement("Activity");
			activity.setAttribute("ActivityName", "Washup");
			activity.setAttribute("ActivityID", "ID1234");
			activity.setAttribute("PersonalID", "P2");
			activity.setXMLComment("The following activity is job related (direct child of jobphase) \ndo we need both cost center and MISDetails here?", true);
			activity = jp.appendElement("Activity");
			activity.setAttribute("ActivityName", "NosePoking");
			activity.setAttribute("ActivityID", "ID1236");
			activity.setAttribute("PersonalID", "P3");
			activity.setXMLComment("The following 2nd activity is job related (direct child of jobphase) \ndo we need both cost center and MISDetails here?", true);
		}

		writeTest(jmf, "Activity.jmf", true, null);
	}

	/**
	 *
	 * new activity element in JobPhase
	 */
	@Test
	public void testKnownDevices()
	{
		final JMFBuilder b = JMFBuilderFactory.getJMFBuilder(null);
		final JDFJMF jmf = b.buildKnownDevicesQuery(JDFDeviceFilter.EnumDeviceDetails.Brief);
		writeTest(jmf, "introduction/knowndevices.jmf", true, null);
	}

	/**
	 *
	 * new activity element in JobPhase
	 */
	@Test
	public void testLotQuery()
	{
		final JMFBuilder b = JMFBuilderFactory.getJMFBuilder(null);
		final JDFJMF jmf = b.buildResourceQuery(true);
		final Vector<EnumResourceClass> v = new Vector<>();
		v.add(EnumResourceClass.Consumable);
		final JDFQuery query = jmf.getQuery(0);
		query.setID("q1");

		query.getResourceQuParams().setClasses(v);
		query.getResourceQuParams().setScope(EnumScope.Allowed);
		writeTest(jmf, "jmf/lotquery.jmf", true, null);
	}

	/**
	 *
	 * new lot
	 */
	@Test
	public void testLot()
	{
		final JMFBuilder b = JMFBuilderFactory.getJMFBuilder(null);
		final JDFJMF jmf = b.createJMF(EnumFamily.Response, EnumType.Resource);

		final JDFResponse response = jmf.getResponse(0);
		response.setID("r1");
		response.setrefID("q1");
		final JDFResourceInfo ri = response.getCreateResourceInfo(0);
		ri.appendResource(ElementName.MEDIA).setDescriptiveName("more about the paper here");
		final JDFAmountPool ap = ri.appendAmountPool();
		for (int i = 1; i < 3; i++)
		{
			final JDFAttributeMap map = new JDFAttributeMap();
			map.put(AttributeName.SIGNATURENAME, "Sig1");
			map.put(AttributeName.SHEETNAME, "Sheet1");
			map.put(AttributeName.LOTID, "Lot_" + i);
			ap.appendPartAmount(map).setActualAmount(42 * i);
		}

		writeTest(jmf, "jmf/lotresponse.jmf", true, null);
	}

	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentName(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentVersion(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID(null);
		KElement.setLongID(false);

	}
}
