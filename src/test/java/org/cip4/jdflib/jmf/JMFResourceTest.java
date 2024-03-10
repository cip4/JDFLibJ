/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.*;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
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
import org.cip4.jdflib.resource.process.JDFShapeDefProductionParams;
import org.cip4.jdflib.resource.process.JDFShapeTemplate;
import org.cip4.jdflib.resource.process.JDFUsageCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi
 *
 *         Test of the Resource JMF
 */
public class JMFResourceTest extends JDFTestCaseBase
{
	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}

	/**
	 *
	 */
	@Test
	void testResourceQuParams()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();

		final JDFQuery c = jmf.appendQuery();
		jmf.setSenderID("MISSenderID");
		c.setType(EnumType.Resource);
		final JDFResourceQuParams rqp = c.getCreateResourceQuParams(0);
		final Vector<EnumResourceClass> vClasses = new Vector<>();
		vClasses.add(EnumResourceClass.Consumable);
		vClasses.add(EnumResourceClass.Handling);
		rqp.setClasses(vClasses);
		Assertions.assertEquals(rqp.getClasses().toString(), vClasses.toString());
	}

	// //////////////////////////////////////////
	/**
	 * test for nodeidentifier
	 */
	@Test
	void testGetIdentifier()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();

		final JDFQuery c = jmf.appendQuery();
		jmf.setSenderID("MISSenderID");
		c.setType(EnumType.Resource);
		final JDFResourceQuParams rqp = c.getCreateResourceQuParams(0);
		rqp.setJobID("J1");
		rqp.setJobPartID("p2");
		Assertions.assertEquals(rqp.getIdentifier(), new NodeIdentifier("J1", "p2", null));

	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testUsageCounter()
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
		Assertions.assertTrue(jmf.isValid(EnumValidationLevel.Complete));
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testMedia()
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
	void testMediaCatalog()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		jmf.setSenderID("DeviceSenderID");

		final JDFQuery q = jmf.appendQuery(EnumType.Resource);
		q.setXMLComment("This is the query for a catalog", true);

		final JDFResourceQuParams rqp = q.appendResourceQuParams();
		rqp.setExact(true);
		rqp.setXMLComment("Scope=Allowed is a new attribute to describe that we want a complet list of all known resources", true);
		rqp.setResourceName(new VString(ElementName.MEDIA, null));
		// rqp.setAttribute("Scope", "Allowed");

		JDFResponse r = q.createResponse().getResponse(0);
		r = (JDFResponse) jmf.moveElement(r, null);
		r.setXMLComment("This is the response to the query - generally it will be in it's own jmf...\nThe list of ResourceInfo + the ResourceQuParams could also be specified in a Signal.", true);

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
			m.setMediaQuality("Qual_" + (1 + i % 3));
			m.setResStatus((i < 2 ? EnumResStatus.Available : EnumResStatus.Unavailable), false);
			ri.setXMLComment("More attributes can be added as needed; Available = loaded", true);
		}
		doc.write2File(sm_dirTestDataTemp + "MediaCatalog.jmf", 2, false);
		Assertions.assertTrue(jmf.isValid(EnumValidationLevel.Complete));
	}

	/**
	 *
	 */
	@Test
	void testMediaCatalogSignal()
	{
		JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		jmf.setSenderID("DeviceSenderID");

		final JDFQuery q = jmf.appendQuery(EnumType.Resource);
		q.setXMLComment("This is the query for a catalog", true);

		final JDFResourceQuParams rqp = q.appendResourceQuParams();
		rqp.setExact(true);
		rqp.setXMLComment("Scope=Allowed is a new attribute to describe that we want a complet list of all known resources", true);
		rqp.setResourceName(new VString(ElementName.MEDIA, null));
		// rqp.setAttribute("Scope", "Allowed");

		final JDFSignal signal = q.createSignal().getSignal(0);
		doc = signal.getOwnerDocument_JDFElement();
		signal.setXMLComment("This is a signal based on the query - generally it will be in it's own jmf...\nThe list of ResourceInfo + the ResourceQuParams could also be specified in a Signal.", true);

		for (int i = 1; i < 5; i++)
		{
			final JDFResourceInfo ri = signal.appendResourceInfo();
			ri.setResourceName("Media");
			final JDFMedia m = (JDFMedia) ri.appendResource("Media");
			m.setDescriptiveName("Description of Media #" + i);
			m.setDimensionCM(new JDFXYPair(i * 10, 13 + i % 2 * 20));
			m.setBrand("Brand #" + i);
			m.setProductID("ProductID_" + i);
			m.setMediaType(EnumMediaType.Paper);
			m.setMediaQuality("Qual_" + (1 + i % 3));
			m.setResStatus((i < 2 ? EnumResStatus.Available : EnumResStatus.Unavailable), false);
			ri.setXMLComment("More attributes can be added as needed; Available = loaded", true);
		}
		doc.write2File(sm_dirTestDataTemp + "MediaCatalogSignal.jmf", 2, false);
		Assertions.assertTrue(jmf.isValid(EnumValidationLevel.Complete));
	}

	/**
	 *
	 */
	@Test
	void testEcmaList()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		jmf.setSenderID("DeviceSenderID");

		final JDFQuery q = jmf.appendQuery(EnumType.Resource);
		q.setXMLComment("This is the query for a catalog", true);

		final JDFResourceQuParams rqp = q.appendResourceQuParams();
		rqp.setExact(true);
		rqp.setXMLComment("Scope=Allowed is a new attribute to describe that we want a complete list of all known resources", true);
		rqp.setResourceName(new VString(ElementName.MEDIA, null));
		// rqp.setAttribute("Scope", "Allowed");

		JDFResponse r = q.createResponse().getResponse(0);
		r = (JDFResponse) jmf.moveElement(r, null);
		r.setXMLComment("This is the response to the query - generally it will be in it's own jmf...\nThe list of ResourceInfo + the ResourceQuParams could also be specified in a Signal.", true);

		for (int i = 1; i < 5; i++)
		{
			final JDFResourceInfo ri = r.appendResourceInfo();
			ri.setResourceName(ElementName.SHAPEDEFPRODUCTIONPARAMS);
			final JDFShapeDefProductionParams m = (JDFShapeDefProductionParams) ri.appendResource(null);
			m.setDescriptiveName("Description of ShapeTemplate #" + i);
			final JDFShapeTemplate shapeTemp = m.appendShapeTemplate();
			final String standard = i < 3 ? "FEFCO" : "ECMA";
			shapeTemp.setStandard(standard);
			shapeTemp.setName(standard + "_" + i);

			ri.setXMLComment("More attributes can be added as needed; Available = loaded", true);
		}
		doc.write2File(sm_dirTestDataTemp + "EcmaCatalog.jmf", 2, false);
		Assertions.assertTrue(jmf.isValid(EnumValidationLevel.Complete));
	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////

	/**
	 *
	 */
	@Test
	void testMediaRef()
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

		Assertions.assertEquals(m, rm.getTarget(), "works initially ");
		Assertions.assertEquals(m, rm.getTarget(), "also works with cache");
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * apply a resource cmd
	 */
	@Test
	void testApplyResourceCmdNodeInfo()
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
		jdf.setType(JDFNode.EnumType.ConventionalPrinting);
		jdf.setStatus(EnumNodeStatus.Waiting);
		jdf.setJobID("JobID");
		jdf.setJobPartID("JobPartID");

		Assertions.assertEquals(jdf.getPartStatus(null, 0), EnumNodeStatus.Waiting);
		Assertions.assertEquals(jdf.getStatus(), EnumNodeStatus.Waiting);
		rqp.applyResourceCommand(jdf);
		Assertions.assertEquals(jdf.getStatus(), EnumNodeStatus.Part);
		Assertions.assertEquals(jdf.getNodeInfo().getNodeStatus(), EnumNodeStatus.Waiting);
		Assertions.assertEquals(jdf.getPartStatus(sheetMap, 0), EnumNodeStatus.Aborted);

		sheetMap = new JDFAttributeMap("SheetName", "S2");
		rqp.setPartMap(sheetMap);
		niRQPS1.setAttributes(sheetMap);
		niRQPS1.setNodeStatus(EnumNodeStatus.Completed);
		niRQP.clearPartitions();

		rqp.applyResourceCommand(jdf);
		Assertions.assertEquals(jdf.getStatus(), EnumNodeStatus.Part);
		Assertions.assertEquals(jdf.getNodeInfo().getNodeStatus(), EnumNodeStatus.Waiting);
		Assertions.assertEquals(jdf.getPartStatus(sheetMap, 0), EnumNodeStatus.Completed);
	}

	/**
	 * apply a resource cmd
	 */
	@Test
	void testApplyResourceCmd()
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
		jdf.setType(JDFNode.EnumType.ConventionalPrinting);
		final JDFMedia mediaJDF = (JDFMedia) jdf.addResource("Media", null, EnumUsage.Input, null, null, null, null);
		mediaJDF.setDimension(new JDFXYPair(40, 60));
		rqp.setJobID(jdf.getJobID(true));
		rqp.setJobPartID(jdf.getJobPartID(true));

		rqp.applyResourceCommand(jdf);
		final JDFMedia m2 = (JDFMedia) jdf.getMatchingResource("Media", EnumProcessUsage.AnyInput, null, 0);
		Assertions.assertEquals(m2.getDimension(), new JDFXYPair(20, 30));

		final JDFAttributeMap sheetMap = new JDFAttributeMap("SheetName", "S1");
		rqp.setPartMap(sheetMap);
		mediaRQP.setDimension(new JDFXYPair(200, 300));

		final JDFMedia m2Sheet = (JDFMedia) m2.addPartition(EnumPartIDKey.SheetName, "S1");
		rqp.applyResourceCommand(jdf);
		Assertions.assertEquals(m2.getDimension(), new JDFXYPair(20, 30), "retained root dimension");
		Assertions.assertEquals(m2Sheet.getDimension(), new JDFXYPair(200, 300), "overwrote leaf root dimension");

		sheetMap.put(EnumPartIDKey.SheetName, "S2");
		rqp.setPartMap(sheetMap);
		mediaRQP.setDimension(new JDFXYPair(300, 400));

		rqp.applyResourceCommand(jdf);
		final JDFMedia m2Sheet2 = (JDFMedia) m2.getPartition(sheetMap, null);
		Assertions.assertNotNull(m2Sheet2);
		Assertions.assertEquals(m2.getDimension(), new JDFXYPair(20, 30), "retained root dimension");
		Assertions.assertEquals(m2Sheet2.getDimension(), new JDFXYPair(300, 400), "overwrote leaf root dimension");

		final JDFMedia mPartRQP = (JDFMedia) mediaRQP.addPartition(EnumPartIDKey.SheetName, "S3");
		sheetMap.put(EnumPartIDKey.SheetName, "S3");
		rqp.setPartMap(sheetMap);
		mPartRQP.setDimension(new JDFXYPair(400, 600));

		rqp.applyResourceCommand(jdf);
		final JDFMedia m2Sheet3 = (JDFMedia) m2.getPartition(sheetMap, null);
		Assertions.assertEquals(m2.getDimension(), new JDFXYPair(20, 30), "retained root dimension");
		Assertions.assertEquals(m2Sheet3.getDimension(), new JDFXYPair(400, 600), "overwrote leaf root dimension");
		Assertions.assertFalse(m2Sheet3.hasAttribute_KElement("ID", null, false));

		mPartRQP.setAttribute(AttributeName.DIMENSION, "");
		mediaRQP.removeAttribute(AttributeName.DIMENSION);
		rqp.applyResourceCommand(jdf);
		// final JDFMedia m2Sheet4 = (JDFMedia)
		m2.getPartition(sheetMap, null);
		Assertions.assertEquals(m2.getDimension(), new JDFXYPair(20, 30), "retained root dimension");
		// assertFalse("removed leaf dimension", m2Sheet4.hasAttribute_KElement(AttributeName.DIMENSION, null, false));
	}

	/**
	 * Method testResourceCommand
	 *
	 */
	@Test
	void testResourceCommand()
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
			Assertions.assertNotNull(n);
			final JDFNodeInfo ni = (JDFNodeInfo) n.getChildWithAttribute(ElementName.NODEINFO, "ID", null, resID, 0, false);
			Assertions.assertNotNull(ni);
			final JDFNodeInfo nip = (JDFNodeInfo) ni.getPartition(amParts, null);
			Assertions.assertNotNull(nip);
			Assertions.assertFalse(nip.hasAttribute_KElement("ID", null, false));
			Assertions.assertFalse(nip.hasAttribute_KElement("SheetName", null, false));
		}
	}

	/**
	 * Method testResourceCommandPartIDKeys
	 *
	 */
	@Test
	void testResourceCommandPartIDKeys()
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
	void testResourceCommandIdentical()
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
		Assertions.assertNotNull(ni);
		final JDFResource niPart = ni.getPartition(amParts, null);
		final JDFAttributeMap map2 = amParts.clone();
		map2.put("Side", "Back");
		Assertions.assertNotNull(niPart);
		final JDFResource niBack = ni.getCreatePartition(map2, null);
		niBack.setIdentical(niPart);

		final JDFResourceCmdParams params = createResourceParams(partID, resID, map2, amAttr);
		params.applyResourceCommand(n);
		Assertions.assertNotNull(n);
		final JDFNodeInfo nip = (JDFNodeInfo) ni.getPartition(amParts, null);
		Assertions.assertNotNull(nip);
		Assertions.assertFalse(nip.hasAttribute_KElement("ID", null, false));
		Assertions.assertFalse(nip.hasAttribute_KElement("SheetName", null, false));
		Assertions.assertEquals(EnumSide.Back, niBack.getSide());
		Assertions.assertEquals(EnumSide.Front, niPart.getSide());
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