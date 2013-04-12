/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of 
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
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.auto.JDFAutoResourceCmdParams.EnumUpdateMethod;
import org.cip4.jdflib.auto.JDFAutoUsageCounter.EnumScope;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.NodeIdentifier;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFUsageCounter;
import org.junit.Assert;
import org.junit.Test;
/**
 * @author Rainer Prosi
 * 
 * Test of the Resource JMF
 */
public class JMFResourceTest extends JDFTestCaseBase
{
	/**
	 * 
	 */
	@Test
	public void testResourceQuParams()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();

		final JDFQuery c = jmf.appendQuery();
		jmf.setSenderID("MISSenderID");
		c.setType(EnumType.Resource);
		final JDFResourceQuParams rqp = c.getCreateResourceQuParams(0);
		final Vector<EnumResourceClass> vClasses = new Vector<EnumResourceClass>();
		vClasses.add(EnumResourceClass.Consumable);
		vClasses.add(EnumResourceClass.Handling);
		rqp.setClasses(vClasses);
		Assert.assertEquals(rqp.getClasses().toString(), vClasses.toString());
	}

	// //////////////////////////////////////////
	/**
	 * test for nodeidentifier
	 */
	@Test
	public void testGetIdentifier()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();

		final JDFQuery c = jmf.appendQuery();
		jmf.setSenderID("MISSenderID");
		c.setType(EnumType.Resource);
		final JDFResourceQuParams rqp = c.getCreateResourceQuParams(0);
		rqp.setJobID("J1");
		rqp.setJobPartID("p2");
		Assert.assertEquals(rqp.getIdentifier(), new NodeIdentifier("J1", "p2", null));

	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testUsageCounter()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();

		final JDFSignal s = jmf.appendSignal();
		jmf.setSenderID("DeviceSenderID");

		s.setType(EnumType.Resource);
		final JDFResourceQuParams rqp = s.appendResourceQuParams();
		rqp.setJobID("JobID");
		rqp.setJobPartID("JobPartID");
		rqp.setResourceName(new VString(ElementName.USAGECOUNTER, null));

		final JDFResourceInfo ri = s.appendResourceInfo();
		ri.setActualAmount(42);
		final JDFUsageCounter uc = (JDFUsageCounter) ri.appendElement(ElementName.USAGECOUNTER);
		uc.setID("UsageCounterID");
		uc.setScope(EnumScope.Job);
		uc.setCounterID("DeviceCounterID");
		uc.setResStatus(EnumResStatus.Available, true);
		uc.setCounterTypes(new VString("NormalSize", " "));
		doc.write2File(sm_dirTestDataTemp + File.separator + "JMFResourceSignal.jmf", 2, false);
		Assert.assertTrue(jmf.isValid(EnumValidationLevel.Complete));
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testMedia()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();

		final JDFSignal s = jmf.appendSignal();
		jmf.setSenderID("DeviceSenderID");

		s.setType(EnumType.Resource);
		final JDFResourceQuParams rqp = s.appendResourceQuParams();
		rqp.setJobID("JobID");
		rqp.setJobPartID("JobPartID");
		rqp.setResourceName(new VString(ElementName.MEDIA, null));

		final JDFResourceInfo ri = s.appendResourceInfo();
		ri.getCreateAmountPool();
		// TODO continue
	}

	/**
	 * 
	 */
	@Test
	public void testMediaCatalog()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		jmf.setSenderID("DeviceSenderID");

		final JDFQuery q = jmf.appendQuery(EnumType.Resource);
		q.setXMLComment("This is the query for a catalog");

		final JDFResourceQuParams rqp = q.appendResourceQuParams();
		rqp.setExact(true);
		rqp.setXMLComment("Scope=Allowed is a new attribute to describe that we want a complet list of all known resources");
		rqp.setResourceName(new VString(ElementName.MEDIA, null));
		// rqp.setAttribute("Scope", "Allowed");

		JDFResponse r = q.createResponse().getResponse(0);
		r = (JDFResponse) jmf.moveElement(r, null);
		r.setXMLComment("This is the response to the query - generally it will be in it's own jmf...\nThe list of ResourceInfo + the ResourceQuParams could also be specified in a Signal.");

		for (int i = 1; i < 5; i++)
		{
			final JDFResourceInfo ri = r.appendResourceInfo();
			ri.setResourceName("Media");
			final JDFMedia m = (JDFMedia) ri.appendResource("Media");
			m.setDescriptiveName("Description of Media #" + i);
			m.setDimensionCM(new JDFXYPair(i * 10, 13 + i % 2 * 20));
			m.setBrand("Brand #" + i);
			m.setProductID("ProductID_" + i);
			m.setMediaType(EnumMediaType.Paper);
			m.setResStatus((i < 2 ? EnumResStatus.Available : EnumResStatus.Unavailable), false);
			ri.setXMLComment("More attributes can be added as needed; Available = loaded");
		}
		doc.write2File(sm_dirTestDataTemp + "MediaCatalog.jmf", 2, false);
		Assert.assertTrue(jmf.isValid(EnumValidationLevel.Complete));
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	public void testMediaRef()
	{

		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();

		final JDFSignal s = jmf.appendSignal();
		jmf.setSenderID("DeviceSenderID");

		s.setType(EnumType.Resource);
		final JDFResourceQuParams rqp = s.appendResourceQuParams();
		rqp.setJobID("JobID");
		rqp.setJobPartID("JobPartID");
		rqp.setResourceName(new VString(ElementName.EXPOSEDMEDIA, null));

		final JDFResourceInfo ri = s.appendResourceInfo();
		final JDFExposedMedia xm = (JDFExposedMedia) ri.appendElement("ExposedMedia");

		final JDFResourceInfo ri2 = s.appendResourceInfo();
		final JDFMedia m = (JDFMedia) ri2.appendElement("Media");
		final JDFRefElement rm = xm.refElement(m);

		Assert.assertEquals("works initially ", m, rm.getTarget());
		Assert.assertEquals("also works with cache", m, rm.getTarget());
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * apply a resource cmd
	 */
	@Test
	public void testApplyResourceCmdNodeInfo()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();

		final JDFCommand c = jmf.appendCommand();
		jmf.setSenderID("DeviceSenderID");

		c.setType(EnumType.Resource);
		final JDFResourceCmdParams rqp = c.appendResourceCmdParams();
		rqp.setJobID("JobID");
		rqp.setJobPartID("JobPartID");
		rqp.setResourceName(ElementName.NODEINFO);
		rqp.setUsage(EnumUsage.Input);
		final JDFNodeInfo niRQP = (JDFNodeInfo) rqp.appendElement(ElementName.NODEINFO);

		JDFAttributeMap sheetMap = new JDFAttributeMap("SheetName", "S1");
		rqp.setPartMap(sheetMap);
		final JDFNodeInfo niRQPS1 = (JDFNodeInfo) niRQP.getCreatePartition(sheetMap, null);
		niRQPS1.setNodeStatus(EnumNodeStatus.Aborted);
		final JDFDoc docJDF = new JDFDoc(ElementName.JDF);
		final JDFNode jdf = docJDF.getJDFRoot();
		jdf.setType(org.cip4.jdflib.node.JDFNode.EnumType.ConventionalPrinting);
		jdf.setStatus(EnumNodeStatus.Waiting);
		jdf.setJobID("JobID");
		jdf.setJobPartID("JobPartID");

		Assert.assertEquals(jdf.getPartStatus(null, 0), EnumNodeStatus.Waiting);
		Assert.assertEquals(jdf.getStatus(), EnumNodeStatus.Waiting);
		rqp.applyResourceCommand(jdf);
		Assert.assertEquals(jdf.getStatus(), EnumNodeStatus.Part);
		Assert.assertEquals(jdf.getNodeInfo().getNodeStatus(), EnumNodeStatus.Waiting);
		Assert.assertEquals(jdf.getPartStatus(sheetMap, 0), EnumNodeStatus.Aborted);

		sheetMap = new JDFAttributeMap("SheetName", "S2");
		rqp.setPartMap(sheetMap);
		niRQPS1.setAttributes(sheetMap);
		niRQPS1.setNodeStatus(EnumNodeStatus.Completed);

		rqp.applyResourceCommand(jdf);
		Assert.assertEquals(jdf.getStatus(), EnumNodeStatus.Part);
		Assert.assertEquals(jdf.getNodeInfo().getNodeStatus(), EnumNodeStatus.Waiting);
		Assert.assertEquals(jdf.getPartStatus(sheetMap, 0), EnumNodeStatus.Completed);
	}

	/**
	 * apply a resource cmd
	 */
	@Test
	public void testApplyResourceCmd()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();

		final JDFCommand c = jmf.appendCommand();
		jmf.setSenderID("DeviceSenderID");

		c.setType(EnumType.Resource);
		final JDFResourceCmdParams rqp = c.appendResourceCmdParams();
		rqp.setJobID("JobID");
		rqp.setJobPartID("JobPartID");
		rqp.setResourceName("Media");
		final JDFMedia mediaRQP = (JDFMedia) rqp.appendElement("Media");
		mediaRQP.setDimension(new JDFXYPair(20, 30));

		final JDFDoc docJDF = new JDFDoc(ElementName.JDF);
		final JDFNode jdf = docJDF.getJDFRoot();
		jdf.setType(org.cip4.jdflib.node.JDFNode.EnumType.ConventionalPrinting);
		final JDFMedia mediaJDF = (JDFMedia) jdf.addResource("Media", null, EnumUsage.Input, null, null, null, null);
		mediaJDF.setDimension(new JDFXYPair(40, 60));
		rqp.setJobID(jdf.getJobID(true));
		rqp.setJobPartID(jdf.getJobPartID(true));

		rqp.applyResourceCommand(jdf);
		final JDFMedia m2 = (JDFMedia) jdf.getMatchingResource("Media", EnumProcessUsage.AnyInput, null, 0);
		Assert.assertEquals(m2.getDimension(), new JDFXYPair(20, 30));

		final JDFAttributeMap sheetMap = new JDFAttributeMap("SheetName", "S1");
		rqp.setPartMap(sheetMap);
		mediaRQP.setDimension(new JDFXYPair(200, 300));

		final JDFMedia m2Sheet = (JDFMedia) m2.addPartition(EnumPartIDKey.SheetName, "S1");
		rqp.applyResourceCommand(jdf);
		Assert.assertEquals("retained root dimension", m2.getDimension(), new JDFXYPair(20, 30));
		Assert.assertEquals("overwrote leaf root dimension", m2Sheet.getDimension(), new JDFXYPair(200, 300));

		sheetMap.put(EnumPartIDKey.SheetName, "S2");
		rqp.setPartMap(sheetMap);
		mediaRQP.setDimension(new JDFXYPair(300, 400));

		rqp.applyResourceCommand(jdf);
		final JDFMedia m2Sheet2 = (JDFMedia) m2.getPartition(sheetMap, null);
		Assert.assertNotNull(m2Sheet2);
		Assert.assertEquals("retained root dimension", m2.getDimension(), new JDFXYPair(20, 30));
		Assert.assertEquals("overwrote leaf root dimension", m2Sheet2.getDimension(), new JDFXYPair(300, 400));

		final JDFMedia mPartRQP = (JDFMedia) mediaRQP.addPartition(EnumPartIDKey.SheetName, "S3");
		sheetMap.put(EnumPartIDKey.SheetName, "S3");
		rqp.setPartMap(sheetMap);
		mPartRQP.setDimension(new JDFXYPair(400, 600));

		rqp.applyResourceCommand(jdf);
		final JDFMedia m2Sheet3 = (JDFMedia) m2.getPartition(sheetMap, null);
		Assert.assertEquals("retained root dimension", m2.getDimension(), new JDFXYPair(20, 30));
		Assert.assertEquals("overwrote leaf root dimension", m2Sheet3.getDimension(), new JDFXYPair(400, 600));
		Assert.assertFalse(m2Sheet3.hasAttribute_KElement("ID", null, false));

		mPartRQP.setAttribute(AttributeName.DIMENSION, "");
		mediaRQP.removeAttribute(AttributeName.DIMENSION);
		rqp.applyResourceCommand(jdf);
		//		final JDFMedia m2Sheet4 = (JDFMedia) 
		m2.getPartition(sheetMap, null);
		Assert.assertEquals("retained root dimension", m2.getDimension(), new JDFXYPair(20, 30));
		// Assert.assertFalse("removed leaf dimension", m2Sheet4.hasAttribute_KElement(AttributeName.DIMENSION, null, false));
	}

	/**
	 * Method testResourceCommand
	 * 
	 */
	@Test
	public void testResourceCommand()
	{
		final JDFDoc jdfDoc = JDFDoc.parseFile(sm_dirTestData + "ResourceCommandTest.jdf");
		final JDFNode root = jdfDoc.getJDFRoot();
		JDFResourceCmdParams params;

		final JDFAttributeMap amAttr = new JDFAttributeMap();

		amAttr.put("Start", "2006-11-02T14:13:18+01:00");
		amAttr.put("End", "2006-11-02T15:13:18+01:00");
		String partID, resID;
		for (int i = 0; i < 2; i++)
		{

			final JDFAttributeMap amParts = new JDFAttributeMap();
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
			final JDFNode n = root.getJobPart(partID, null);
			params.applyResourceCommand(n);
			Assert.assertNotNull(n);
			final JDFNodeInfo ni = (JDFNodeInfo) n.getChildWithAttribute(ElementName.NODEINFO, "ID", null, resID, 0, false);
			Assert.assertNotNull(ni);
			final JDFNodeInfo nip = (JDFNodeInfo) ni.getPartition(amParts, null);
			Assert.assertNotNull(nip);
			Assert.assertFalse(nip.hasAttribute_KElement("ID", null, false));
			Assert.assertFalse(nip.hasAttribute_KElement("SheetName", null, false));
		}
	}

	/**
	 * Method testResourceCommandPartIDKeys
	 * 
	 */
	@Test
	public void testResourceCommandPartIDKeys()
	{
		final JDFDoc jdfDoc = JDFDoc.parseFile(sm_dirTestData + "ResourceCmd.jdf");
		final JDFDoc jdfDocJMF = JDFDoc.parseFile(sm_dirTestData + "ResourceCmd.jmf");
		final JDFJMF jmf = jdfDocJMF.getJMFRoot();
		for (int i = 0; true; i++)
		{
			final JDFCommand cmd = (JDFCommand) jmf.getMessageElement(JDFMessage.EnumFamily.Command, JDFMessage.EnumType.Resource, i);
			if (cmd == null)
			{
				break;
			}
			final JDFResourceCmdParams params = cmd.getResourceCmdParams(0);
			params.applyResourceCommand(jdfDoc.getJDFRoot());
		}
	}

	/**
	 * Method testResourceCommandPartIDKeys
	 * 
	 */
	@Test
	public void testResourceCommandIdentical()
	{
		final JDFDoc jdfDoc = JDFDoc.parseFile(sm_dirTestData + "ResourceCommandTest.jdf");
		final JDFNode root = jdfDoc.getJDFRoot();

		final JDFAttributeMap amAttr = new JDFAttributeMap();

		amAttr.put("Start", "2006-11-02T14:13:18+01:00");
		amAttr.put("End", "2006-11-02T15:13:18+01:00");
		String partID, resID;

		final JDFAttributeMap amParts = new JDFAttributeMap();
		amParts.put("SignatureName", "Sig001");
		amParts.put("SheetName", "FB 001");
		amParts.put("Side", "Front");
		partID = "SFP0.C";
		resID = "Link49087948_000508";
		final JDFNode n = root.getJobPart(partID, null);
		final JDFNodeInfo ni = (JDFNodeInfo) n.getChildWithAttribute(ElementName.NODEINFO, "ID", null, resID, 0, false);
		Assert.assertNotNull(ni);
		JDFResource niPart = ni.getPartition(amParts, null);
		JDFAttributeMap map2 = amParts.clone();
		map2.put("Side", "Back");
		Assert.assertNotNull(niPart);
		JDFResource niBack = ni.getCreatePartition(map2, null);
		niBack.setIdentical(niPart);

		JDFResourceCmdParams params = createResourceParams(partID, resID, map2, amAttr);
		params.applyResourceCommand(n);
		Assert.assertNotNull(n);
		final JDFNodeInfo nip = (JDFNodeInfo) ni.getPartition(amParts, null);
		Assert.assertNotNull(nip);
		Assert.assertFalse(nip.hasAttribute_KElement("ID", null, false));
		Assert.assertFalse(nip.hasAttribute_KElement("SheetName", null, false));
		Assert.assertEquals(EnumSide.Back, niBack.getSide());
		Assert.assertEquals(EnumSide.Front, niPart.getSide());
	}

	private JDFResourceCmdParams createResourceParams(final String strJobPartID, final String strResourceID, final JDFAttributeMap amParts, final JDFAttributeMap amAttr)
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.Resource);

		final JDFCommand cmd = jmf.getCommand(0);

		final JDFResourceCmdParams params = cmd.appendResourceCmdParams();

		final String strJobID = "RefJob-1";
		final String strPartIDKeys = "SignatureName SheetName Side";

		params.setJobID(strJobID);
		params.setJobPartID(strJobPartID);
		params.setResourceID(strResourceID);
		params.setResourceName("NodeInfo");
		params.setUpdateMethod(EnumUpdateMethod.Incremental);

		params.setPartMap(amParts);

		final JDFResource nodeInfo = params.appendResource("NodeInfo");

		final JDFResource resLeaf = nodeInfo.getCreatePartition(amParts, new VString(strPartIDKeys, null));

		resLeaf.setAttributes(amAttr);

		return (params);
	}

}