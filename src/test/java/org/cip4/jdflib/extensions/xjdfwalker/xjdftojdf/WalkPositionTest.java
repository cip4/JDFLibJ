/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.PartitionHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.junit.jupiter.api.Test;

class WalkPositionTest extends JDFTestCaseBase
{
	@Test
	void testWalkAndEnsurePartition()
	{
		final WalkPosition walker = new WalkPosition();
		walker.setParent(new XJDFToJDFImpl(null));

		final JDFPosition position = (JDFPosition) new JDFDoc(ElementName.POSITION).getRoot();
		position.setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		position.setID("pos1");
		position.appendElement(ElementName.BINDERYSIGNATURE + "Ref").setAttribute("rRef", "idBS1");
		final JDFStrippingParams strippingParams = (JDFStrippingParams) new JDFDoc(ElementName.STRIPPINGPARAMS).getRoot();

		final JDFStrippingParams partition = walker.ensurePartition(position, strippingParams);
		assertNotNull(partition);
		final KElement walked = walker.walk(position, partition);
		assertNotNull(walked);
		assertNull(walked.getNonEmpty(XJDFConstants.BinderySignatureID));
		assertNull(walked.getNonEmpty(AttributeName.ID));
	}

	@Test
	void testEnsurePartitionNullForNonStrippingParams()
	{
		final WalkPosition walker = new WalkPosition();
		walker.setParent(new XJDFToJDFImpl(null));
		final JDFPosition position = (JDFPosition) new JDFDoc(ElementName.POSITION).getRoot();
		final KElement nonStrippingParent = new JDFDoc(ElementName.RUNLIST).getRoot();
		assertNull(walker.ensurePartition(position, nonStrippingParent));
		assertNull(walker.walk(position, nonStrippingParent));
	}

	@Test
	void testWalkCreatesBinderySignatureRefFromBinderySignatureID()
	{
		final XJDFHelper helper = new XJDFHelper("walkposition-ensure-ref", "Part_1", null);
		helper.setTypes("Stripping");
		final SetHelper bsSet = helper.getCreateSet(ElementName.BINDERYSIGNATURE, org.cip4.jdflib.core.JDFResourceLink.EnumUsage.Input, null);
		final ResourceHelper bsPartition = bsSet.appendPartition(XJDFConstants.BinderySignatureID, "BS2", true);
		bsPartition.getResource().appendElement(ElementName.SIGNATURECELL).setAttribute(AttributeName.PAGEINDEX, "0");
		final String binderySetID = bsSet.ensureID();

		final XJDFToJDFImpl impl = new XJDFToJDFImpl(null);
		impl.xjdf = helper;
		final WalkPosition walker = new WalkPosition();
		walker.setParent(impl);

		final JDFPosition position = (JDFPosition) new JDFDoc(ElementName.POSITION).getRoot();
		position.setAttribute(XJDFConstants.BinderySignatureID, "BS2");
		final JDFStrippingParams stripParams = (JDFStrippingParams) new JDFDoc(ElementName.STRIPPINGPARAMS).getRoot();
		final KElement walked = walker.walk(position, stripParams);

		assertNotNull(walked);
		final KElement binderyRef = stripParams.getChildByTagName(ElementName.BINDERYSIGNATURE + "Ref", null, 0, null, false, true);
		assertNotNull(binderyRef);
		assertEquals(binderySetID, binderyRef.getAttribute(AttributeName.RREF));
		assertEquals("BS2", binderyRef.getElement(ElementName.PART).getAttribute(AttributeName.BINDERYSIGNATURENAME));
		assertTrue(((JDFStrippingParams) binderyRef.getParentNode_KElement()).getAssemblyIDs().contains("BS2"));
		assertNull(position.getNonEmpty(XJDFConstants.BinderySignatureID));
	}

	@Test
	void testRoundTrip()
	{
		final XJDFHelper helper = createLayoutHelper("walkposition");
		final JDFElement converted = new XJDFToJDFConverter(null).convert(helper).getJDFRoot();
		assertNotNull(converted);
		assertTrue(converted instanceof JDFNode);
		final KElement strippingParams = ((JDFNode) converted).getResource(ElementName.STRIPPINGPARAMS,
				org.cip4.jdflib.core.JDFResourceLink.EnumUsage.Input, 0);
		assertNotNull(strippingParams);
		final KElement position = strippingParams.getChildByTagName(ElementName.POSITION, null, 0, null, false, true);
		assertNotNull(position);
		assertNull(position.getNonEmpty(XJDFConstants.BinderySignatureID));
		assertNull(position.getNonEmpty(AttributeName.ID));
		final KElement binderyRef = strippingParams.getChildByTagName(ElementName.BINDERYSIGNATURE + "Ref", null, 0, null, false, true);
		assertNotNull(binderyRef);
		assertNotNull(binderyRef.getNonEmpty(AttributeName.RREF));
	}

	@Test
	void testRoundTripSplitsSignatureCellsToStripCellParams()
	{
		final XJDFHelper helper = createLayoutHelper("walkposition-cells");
		final SetHelper bsSet = helper.getSet(ElementName.BINDERYSIGNATURE, 0);
		assertNotNull(bsSet);
		final ResourceHelper bsPartition = bsSet.getPartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, "BS1"));
		assertNotNull(bsPartition);
		bsPartition.getResource().appendElement(ElementName.SIGNATURECELL).setAttribute(AttributeName.TRIMSIZE, "20 30");
		final KElement position = helper.getRoot().getChildByTagName(ElementName.POSITION, null, 0, null, false, true);
		assertNotNull(position);
		position.setAttribute(ElementName.BINDERYSIGNATURE + "Ref", bsPartition.ensureID());

		final JDFElement converted = new XJDFToJDFConverter(null).convert(helper).getJDFRoot();
		assertNotNull(converted);
		assertTrue(converted instanceof JDFNode);
		final KElement strippingParams = ((JDFNode) converted).getResource(ElementName.STRIPPINGPARAMS,
				org.cip4.jdflib.core.JDFResourceLink.EnumUsage.Input, 0);
		assertNotNull(strippingParams);

		final KElement stripCell0 = strippingParams.getChildByTagName(ElementName.STRIPCELLPARAMS, null, 0, null, false, true);
		assertNotNull(stripCell0);
		assertEquals("20 30", stripCell0.getAttribute(AttributeName.TRIMSIZE));
		assertNull(strippingParams.getChildByTagName(ElementName.STRIPCELLPARAMS, null, 1, null, false, true));

		final KElement remainingSignatureCell = converted.getChildByTagName(ElementName.SIGNATURECELL, null, 0, null, false, true);
		assertNull(remainingSignatureCell);
	}

	@SuppressWarnings("deprecation")
	private XJDFHelper createLayoutHelper(final String jobId)
	{
		final XJDFHelper helper = new XJDFHelper(jobId, "Part_1", null);
		helper.setTypes("Stripping");

		final SetHelper bsSet = helper.getCreateSet(ElementName.BINDERYSIGNATURE, org.cip4.jdflib.core.JDFResourceLink.EnumUsage.Input, null);
		final ResourceHelper bsPartition = bsSet.appendPartition(XJDFConstants.BinderySignatureID, "BS1", true);
		final org.cip4.jdflib.resource.process.JDFBinderySignature bs = (org.cip4.jdflib.resource.process.JDFBinderySignature) bsPartition.getResource();
		bs.setFoldCatalog("F16-6");
		bs.setAttribute(AttributeName.BINDERYSIGNATURETYPE, "Fold");

		final SetHelper layoutSet = helper.getCreateSet(ElementName.LAYOUT, org.cip4.jdflib.core.JDFResourceLink.EnumUsage.Input, null);
		final ResourceHelper layoutPartition = layoutSet.appendPartition(AttributeName.SHEETNAME, "S1", true);
		final PartitionHelper partitionHelper = new PartitionHelper(layoutPartition.getRoot());
		final org.cip4.jdflib.resource.process.JDFLayout layout = (org.cip4.jdflib.resource.process.JDFLayout) partitionHelper.getCreateResource();
		layout.setAttribute(AttributeName.WORKSTYLE, "WorkAndBack");
		final KElement position = layout.appendElement(ElementName.POSITION);
		position.setAttribute(XJDFConstants.BinderySignatureID, "BS1");
		position.setAttribute(AttributeName.ID, "Pos1");
		position.setAttribute(AttributeName.RELATIVEBOX, "0 0 100 100");
		final KElement placedObject = layout.appendElement(XJDFConstants.PlacedObject);
		placedObject.setAttribute(AttributeName.CTM, "1 0 0 1 0 0");
		placedObject.setAttribute(XJDFConstants.PositionRef, "Pos1");
		placedObject.appendElement(ElementName.CONTENTOBJECT);

		final SetHelper runListSet = helper.getCreateSet(ElementName.RUNLIST, org.cip4.jdflib.core.JDFResourceLink.EnumUsage.Input, null);
		final ResourceHelper runListPartition = runListSet.appendPartition(AttributeName.RUN, "r1", true);
		final PartitionHelper runListHelper = new PartitionHelper(runListPartition.getRoot());
		final org.cip4.jdflib.resource.process.JDFRunList runList = (org.cip4.jdflib.resource.process.JDFRunList) runListHelper.getCreateResource();
		runList.setAttribute(AttributeName.PAGES, "0 -1");
		runList.appendElement(ElementName.FILESPEC).setAttribute(AttributeName.URL, "File:///in/" + jobId + ".pdf");
		return helper;
	}
}
