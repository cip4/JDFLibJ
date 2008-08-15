/*--------------------------------------------------------------------------------------------------
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
 */
package org.cip4.jdflib.jmf;

import java.io.File;
import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoResourceCmdParams.EnumUpdateMethod;
import org.cip4.jdflib.auto.JDFAutoUsageCounter.EnumScope;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFUsageCounter;

/**
 * @author Rainer Prosi
 * 
 *         Test of the Resource JMF
 */
public class JMFResourceTest extends JDFTestCaseBase
{
	public void testResourceQuParams()
	{
		JDFDoc doc = new JDFDoc(ElementName.JMF);
		JDFJMF jmf = doc.getJMFRoot();

		JDFQuery c = jmf.appendQuery();
		jmf.setSenderID("MISSenderID");
		c.setType(EnumType.Resource);
		JDFResourceQuParams rqp = c.getCreateResourceQuParams(0);
		Vector vClasses = new Vector();
		vClasses.add(EnumResourceClass.Consumable);
		vClasses.add(EnumResourceClass.Handling);
		rqp.setClasses(vClasses);
		assertEquals(rqp.getClasses().toString(), vClasses.toString());

	}

	// //////////////////////////////////////////
	/**
	 * test for nodeidentifier
	 */
	public void testGetIdentifier()
	{
		JDFDoc doc = new JDFDoc(ElementName.JMF);
		JDFJMF jmf = doc.getJMFRoot();

		JDFQuery c = jmf.appendQuery();
		jmf.setSenderID("MISSenderID");
		c.setType(EnumType.Resource);
		JDFResourceQuParams rqp = c.getCreateResourceQuParams(0);
		rqp.setJobID("J1");
		rqp.setJobPartID("p2");
		assertEquals(rqp.getIdentifier(), new NodeIdentifier("J1", "p2", null));

	}

	// ///////////////////////////////////////////////////////////////////

	public void testUsageCounter()
	{
		JDFDoc doc = new JDFDoc(ElementName.JMF);
		JDFJMF jmf = doc.getJMFRoot();

		JDFSignal s = jmf.appendSignal();
		jmf.setSenderID("DeviceSenderID");

		s.setType(EnumType.Resource);
		JDFResourceQuParams rqp = s.appendResourceQuParams();
		rqp.setJobID("JobID");
		rqp.setJobPartID("JobPartID");
		rqp.setResourceName(ElementName.USAGECOUNTER);

		JDFResourceInfo ri = s.appendResourceInfo();
		ri.setActualAmount(42);
		JDFUsageCounter uc = (JDFUsageCounter) ri.appendElement(ElementName.USAGECOUNTER);
		uc.setID("UsageCounterID");
		uc.setScope(EnumScope.Job);
		uc.setCounterID("DeviceCounterID");
		uc.setResStatus(EnumResStatus.Available, true);
		uc.setCounterTypes(new VString("NormalSize", " "));
		doc.write2File(sm_dirTestDataTemp + File.separator + "JMFResourceSignal.jmf", 2, false);
		assertTrue(jmf.isValid(EnumValidationLevel.Complete));
	}

	// ///////////////////////////////////////////////////////////////////

	public void testMedia()
	{
		JDFDoc doc = new JDFDoc(ElementName.JMF);
		JDFJMF jmf = doc.getJMFRoot();

		JDFSignal s = jmf.appendSignal();
		jmf.setSenderID("DeviceSenderID");

		s.setType(EnumType.Resource);
		JDFResourceQuParams rqp = s.appendResourceQuParams();
		rqp.setJobID("JobID");
		rqp.setJobPartID("JobPartID");
		rqp.setResourceName(ElementName.MEDIA);

		JDFResourceInfo ri = s.appendResourceInfo();
		ri.getCreateAmountPool();
		// TODO continue
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	public void testMediaRef()
	{

		JDFDoc doc = new JDFDoc(ElementName.JMF);
		JDFJMF jmf = doc.getJMFRoot();

		JDFSignal s = jmf.appendSignal();
		jmf.setSenderID("DeviceSenderID");

		s.setType(EnumType.Resource);
		JDFResourceQuParams rqp = s.appendResourceQuParams();
		rqp.setJobID("JobID");
		rqp.setJobPartID("JobPartID");
		rqp.setResourceName(ElementName.EXPOSEDMEDIA);

		JDFResourceInfo ri = s.appendResourceInfo();
		JDFExposedMedia xm = (JDFExposedMedia) ri.appendElement("ExposedMedia");

		JDFResourceInfo ri2 = s.appendResourceInfo();
		JDFMedia m = (JDFMedia) ri2.appendElement("Media");
		JDFRefElement rm = xm.refElement(m);

		assertEquals("works initially ", m, rm.getTarget());
		assertEquals("also works with cache", m, rm.getTarget());
	}

	// ///////////////////////////////////////////////////////////////////

	public void testApplyResourceCmd()
	{
		JDFDoc doc = new JDFDoc(ElementName.JMF);
		JDFJMF jmf = doc.getJMFRoot();

		JDFCommand c = jmf.appendCommand();
		jmf.setSenderID("DeviceSenderID");

		c.setType(EnumType.Resource);
		JDFResourceCmdParams rqp = c.appendResourceCmdParams();
		rqp.setJobID("JobID");
		rqp.setJobPartID("JobPartID");
		rqp.setResourceName("Media");
		JDFMedia mediaRQP = (JDFMedia) rqp.appendElement("Media");
		mediaRQP.setDimension(new JDFXYPair(20, 30));

		JDFDoc docJDF = new JDFDoc(ElementName.JDF);
		JDFNode jdf = docJDF.getJDFRoot();
		jdf.setType(org.cip4.jdflib.node.JDFNode.EnumType.ConventionalPrinting);
		JDFMedia mediaJDF = (JDFMedia) jdf.addResource("Media", null, EnumUsage.Input, null, null, null, null);
		mediaJDF.setDimension(new JDFXYPair(40, 60));
		rqp.setJobID(jdf.getJobID(true));
		rqp.setJobPartID(jdf.getJobPartID(true));

		rqp.applyResourceCommand(jdf);
		JDFMedia m2 = (JDFMedia) jdf.getMatchingResource("Media", EnumProcessUsage.AnyInput, null, 0);
		assertEquals(m2.getDimension(), new JDFXYPair(20, 30));

		final JDFAttributeMap sheetMap = new JDFAttributeMap("SheetName", "S1");
		rqp.setPartMap(sheetMap);
		mediaRQP.setDimension(new JDFXYPair(200, 300));

		JDFMedia m2Sheet = (JDFMedia) m2.addPartition(EnumPartIDKey.SheetName, "S1");
		rqp.applyResourceCommand(jdf);
		assertEquals("retained root dimension", m2.getDimension(), new JDFXYPair(20, 30));
		assertEquals("overwrote leaf root dimension", m2Sheet.getDimension(), new JDFXYPair(200, 300));

		sheetMap.put(EnumPartIDKey.SheetName, "S2");
		rqp.setPartMap(sheetMap);
		mediaRQP.setDimension(new JDFXYPair(300, 400));

		rqp.applyResourceCommand(jdf);
		JDFMedia m2Sheet2 = (JDFMedia) m2.getPartition(sheetMap, null);
		assertNotNull(m2Sheet2);
		assertEquals("retained root dimension", m2.getDimension(), new JDFXYPair(20, 30));
		assertEquals("overwrote leaf root dimension", m2Sheet2.getDimension(), new JDFXYPair(300, 400));

		JDFMedia mPartRQP = (JDFMedia) mediaRQP.addPartition(EnumPartIDKey.SheetName, "S3");
		sheetMap.put(EnumPartIDKey.SheetName, "S3");
		rqp.setPartMap(sheetMap);
		mPartRQP.setDimension(new JDFXYPair(400, 600));

		rqp.applyResourceCommand(jdf);
		JDFMedia m2Sheet3 = (JDFMedia) m2.getPartition(sheetMap, null);
		assertEquals("retained root dimension", m2.getDimension(), new JDFXYPair(20, 30));
		assertEquals("overwrote leaf root dimension", m2Sheet3.getDimension(), new JDFXYPair(400, 600));
		assertFalse(m2Sheet3.hasAttribute_KElement("ID", null, false));
	}

	/**
	 * Method testResourceCommand
	 * 
	 * @throws Exception
	 */

	public void testResourceCommand()
	{
		JDFDoc jdfDoc = JDFDoc.parseFile(sm_dirTestData + "ResourceCommandTest.jdf");
		JDFNode root = jdfDoc.getJDFRoot();
		JDFResourceCmdParams params;

		JDFAttributeMap amAttr = new JDFAttributeMap();

		amAttr.put("Start", "2006-11-02T14:13:18+01:00");
		amAttr.put("End", "2006-11-02T15:13:18+01:00");
		String partID, resID;
		for (int i = 0; i < 2; i++)
		{

			JDFAttributeMap amParts = new JDFAttributeMap();
			if (i == 0)
			{

				amParts.put("SignatureName", "Sig001");
				amParts.put("SheetName", "FB 001");
				amParts.put("Side", "Front");
				partID = "SFP0.C";
				resID = "Link49087948_000508";
			}
			else
			{
				resID = "Link49165276_000704";
				amParts.put("SignatureName", "Sig002");
				amParts.put("SheetName", "FB 002");
				amParts.put("Side", "Back");
				partID = "SFP1.C";
			}
			params = createResourceParams(partID, resID, amParts, amAttr);
			JDFNode n = root.getJobPart(partID, null);
			params.applyResourceCommand(n);
			assertNotNull(n);
			JDFNodeInfo ni = (JDFNodeInfo) n.getChildWithAttribute(ElementName.NODEINFO, "ID", null, resID, 0, false);
			assertNotNull(ni);
			JDFNodeInfo nip = (JDFNodeInfo) ni.getPartition(amParts, null);
			assertNotNull(nip);
			assertFalse(nip.hasAttribute_KElement("ID", null, false));
			assertFalse(nip.hasAttribute_KElement("SheetName", null, false));
		}
	}

	/**
	 * Method createResourceParams
	 * 
	 * @param strJobPartID
	 * @param strResourceID
	 * @param amParts
	 * @param amAttr
	 * 
	 * @return
	 */
	public void testResourceCommandPartIDKeys()
	{
		JDFDoc jdfDoc = JDFDoc.parseFile(sm_dirTestData + "ResourceCmd.jdf");
		JDFDoc jdfDocJMF = JDFDoc.parseFile(sm_dirTestData + "ResourceCmd.jmf");
		JDFJMF jmf = jdfDocJMF.getJMFRoot();
		for (int i = 0; true; i++)
		{
			JDFCommand cmd = (JDFCommand) jmf.getMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.Resource, i);
			if (cmd == null)
				break;
			JDFResourceCmdParams params = cmd.getResourceCmdParams(0);
			params.applyResourceCommand(jdfDoc.getJDFRoot());
		}
	}

	private JDFResourceCmdParams createResourceParams(String strJobPartID, String strResourceID, JDFAttributeMap amParts, JDFAttributeMap amAttr)
	{
		JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.Resource);

		JDFCommand cmd = jmf.getCommand(0);

		JDFResourceCmdParams params = cmd.appendResourceCmdParams();

		final String strJobID = "RefJob-1";
		final String strPartIDKeys = "SignatureName SheetName Side";

		params.setJobID(strJobID);
		params.setJobPartID(strJobPartID);
		params.setResourceID(strResourceID);
		params.setResourceName("NodeInfo");
		params.setUpdateMethod(EnumUpdateMethod.Incremental);

		params.setPartMap(amParts);

		JDFResource nodeInfo = params.appendResource("NodeInfo");

		JDFResource resLeaf = nodeInfo.getCreatePartition(amParts, new VString(strPartIDKeys, null));

		resLeaf.setAttributes(amAttr);

		return (params);
	}

}