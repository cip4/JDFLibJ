/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.StringUtil;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class MultiXJDFCombinerTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testGetCombined()
	{
		final Vector<XJDFHelper> vh = new Vector<>();
		for (int i = 1; i < 3; i++)
		{
			final XJDFHelper h = new XJDFHelper("j1", "p1", new VJDFAttributeMap(new JDFAttributeMap(AttributeName.SHEETNAME, "S" + i)));
			h.addType(EnumType.Product);
			vh.add(h);
		}
		final XJDFHelper hc = new MultiXJDFCombiner(vh).getCombinedHelper();
		assertEquals(2, hc.getSet(ElementName.NODEINFO, EnumUsage.Input).getPartitions().size());
	}

	/**
	 *
	 */
	@Test
	public void testSammelCPI()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode root = JDFNode.parseFile(sm_dirTestData + "sammel18.jdf");
		final VElement v0 = root.getChildrenByTagName(null, null, null, false, true, 0);
		for (final KElement e : v0)
			e.removeAttribute(AttributeName.DESCRIPTIVENAME);
		final Vector<XJDFHelper> v = conv.getXJDFs(root);
		assertEquals(3, v.size());
		final XJDFHelper hc = new MultiXJDFCombiner(v).getCombinedHelper();
		assertEquals(0, hc.getSet(ElementName.NODEINFO, 0).getCombinedProcessIndex().get(0));
		assertEquals(1, hc.getSet(ElementName.NODEINFO, 1).getCombinedProcessIndex().get(0));
		assertEquals(2, hc.getSet(ElementName.NODEINFO, 2).getCombinedProcessIndex().get(0));
		writeRoundTripX(hc.getRoot(), "sammel18", null);
	}

	/**
	 *
	 */
	@Test
	public void testSammelSepSpec()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode root = JDFNode.parseFile(sm_dirTestData + "sammel18.jdf");
		final Vector<XJDFHelper> v = conv.getXJDFs(root);
		final XJDFHelper hc = new MultiXJDFCombiner(v).getCombinedHelper();
		assertNull(hc.getRoot().getElementByClass(JDFSeparationSpec.class, 0, true));
	}

	/**
	 *
	 */
	@Test
	@Ignore
	public void testSammel19()
	{
		for (int i = 0; i < 1; i++)
		{
			final JDFToXJDF conv = new JDFToXJDF();
			conv.setCleanup(false);
			conv.setWantDependent(false);
			final JDFNode root = JDFNode.parseFile(sm_dirTestData + "sammel19.jdf");
			final Vector<XJDFHelper> v = conv.getXJDFs(root);
			assertEquals(11, v.size());
			final XJDFHelper hc = new MultiXJDFCombiner(v).getCombinedHelper();
			writeRoundTripX(hc.getRoot(), "sammel19", null);
		}
	}

	/**
	 *
	 */
	@Test
	public void testSammelDrop()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		conv.setSingleNode(false);
		final JDFNode root = JDFNode.parseFile(sm_dirTestData + "sammel18.jdf");
		final KElement e = conv.convert(root);
		final XJDFHelper h = XJDFHelper.getHelper(e);
		final SetHelper delivery = h.getSet(ElementName.DELIVERYPARAMS, EnumUsage.Input);
		assertEquals(1, delivery.getPartitions().size());
		assertNotNull(delivery.getPartition(0).getXPathElement("DeliveryParams/DropItem[6]"));
	}

	/**
	 *
	 */
	@Test
	public void testSammelBindSig()
	{
		final JDFToXJDF conv = new JDFToXJDF();
		final JDFNode root = JDFNode.parseFile(sm_dirTestData + "sammel18.jdf").getJobPart("_180309_103033019_000111");
		final VElement v = root.getResourceLinks(null);
		for (final KElement e : v)
		{
			if (!ElementName.STRIPPINGPARAMS.equals(StringUtil.leftStr(e.getLocalName(), -4)))
			{
				e.deleteNode();
			}
		}
		final JDFNode s = new JDFSpawn(root).spawn();
		final KElement e = conv.convert(s);
		final XJDFHelper h = XJDFHelper.getHelper(e);
		final SetHelper bs = h.getSet(ElementName.BINDERYSIGNATURE, null);
		assertEquals(6, bs.getPartitions().size());
		writeRoundTripX(e, "testsig", null);
	}

}
