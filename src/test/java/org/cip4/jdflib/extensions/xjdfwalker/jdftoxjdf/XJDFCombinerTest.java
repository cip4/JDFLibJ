/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFAssemblySection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFCombinerTest extends JDFTestCaseBase
{

	@Test
	void testCombineTypes()
	{
		final Vector<XJDFHelper> vh = new Vector<>();
		for (int i = 1; i < 3; i++)
		{
			final XJDFHelper h = new XJDFHelper("j1", "p1", new VJDFAttributeMap(new JDFAttributeMap(AttributeName.SHEETNAME, "S" + i)));
			h.addType(EnumType.Product);
			vh.add(h);
		}
		final XJDFCombiner c = new XJDFCombiner(vh.get(0), vh.get(1));
		final int[] ct = c.combineTypes();
		Assertions.assertEquals(1, ct.length);
		Assertions.assertEquals(0, ct[0]);
	}

	@Test
	void testCombineTypesMulti()
	{
		final XJDFHelper h = new XJDFHelper("j1", "p1", null);
		h.addType(EnumType.Product);

		for (int j = 0; j < 3; j++)
		{
			final XJDFHelper h2 = new XJDFHelper("j1", "p2", null);
			h2.addType(EnumType.Cutting);
			h2.addType(EnumType.Folding);
			h2.addType(EnumType.Cutting);

			final XJDFCombiner c = new XJDFCombiner(h, h2);
			final int[] ct = c.combineTypes();
			Assertions.assertEquals(3, ct.length);
			for (int i = 0; i < ct.length; i++)
				Assertions.assertEquals(i + 1, ct[i]);
		}
	}

	/**
	 *
	 */
	@Test
	void testGetMainSet()
	{
		final Vector<XJDFHelper> vh = new Vector<>();
		for (int i = 1; i < 3; i++)
		{
			final XJDFHelper h = new XJDFHelper("j1", "p1", new VJDFAttributeMap(new JDFAttributeMap(AttributeName.SHEETNAME, "S" + i)));
			h.addType(EnumType.Product);
			vh.add(h);
		}
		final XJDFHelper h0 = vh.get(0);
		final XJDFHelper h1 = vh.get(1);
		final XJDFCombiner c = new XJDFCombiner(h0, h1);
		final SetHelper mainSet = c.getMainSet(h1.getSet(ElementName.NODEINFO, 0));
		Assertions.assertEquals(h0.getSet(ElementName.NODEINFO, 0), mainSet);
	}

	/**
	 *
	 */
	@Test
	void testMergeMediaSet()
	{
		final Vector<XJDFHelper> vh = new Vector<>();
		for (int i = 1; i < 3; i++)
		{
			final XJDFHelper h = new XJDFHelper("j1", "p1", null);
			h.addType(EnumType.ConventionalPrinting);
			final JDFAttributeMap m = new JDFAttributeMap(AttributeName.SHEETNAME, "S" + i);
			final SetHelper s1 = h.appendResourceSet(ElementName.MEDIA, EnumUsage.Input);
			s1.appendPartition(m, true).getResource().setAttribute(AttributeName.MEDIATYPE, "Paper");
			s1.setCombinedProcessIndex(new JDFIntegerList(0));
			final SetHelper s2 = h.appendResourceSet(ElementName.MEDIA, EnumUsage.Input);
			s2.setCombinedProcessIndex(new JDFIntegerList(0));
			s2.appendPartition(m, true).getResource().setAttribute(AttributeName.MEDIATYPE, "Plate");

			vh.add(h);
		}
		final XJDFHelper h0 = vh.get(0);
		final XJDFHelper h1 = vh.get(1);
		final XJDFCombiner c = new XJDFCombiner(h0, h1);
		final XJDFHelper hc = c.combine();
		Assertions.assertEquals(2, hc.getSet(ElementName.MEDIA, 0).getPartitions().size());
		Assertions.assertEquals(2, hc.getSet(ElementName.MEDIA, 1).getPartitions().size());
		Assertions.assertNull(hc.getSet(ElementName.MEDIA, 2));

	}

	/**
	 *
	 */
	@Test
	void testMergeSet()
	{
		final Vector<XJDFHelper> vh = new Vector<>();
		for (int i = 1; i < 3; i++)
		{
			final XJDFHelper h = new XJDFHelper("j1", "p1", new VJDFAttributeMap(new JDFAttributeMap(AttributeName.SHEETNAME, "S" + i)));
			h.addType(EnumType.Product);
			vh.add(h);
		}
		final XJDFHelper h0 = vh.get(0);
		final XJDFHelper h1 = vh.get(1);
		final XJDFCombiner c = new XJDFCombiner(h0, h1);
		c.mergeSet(h0.getSet(ElementName.NODEINFO, 0), h1.getSet(ElementName.NODEINFO, 0));

		Assertions.assertEquals(2, h0.getSet(ElementName.NODEINFO, 0).getPartitions().size());
	}

	/**
	 *
	 */
	@Test
	void testMergeSetSame()
	{
		final Vector<XJDFHelper> vh = new Vector<>();
		for (int i = 1; i < 3; i++)
		{
			final XJDFHelper h = new XJDFHelper("j1", "p1", new VJDFAttributeMap(new JDFAttributeMap(AttributeName.SHEETNAME, "S1")));
			h.addType(EnumType.Product);
			vh.add(h);
		}
		final XJDFHelper h0 = vh.get(0);
		final XJDFHelper h1 = vh.get(1);
		final XJDFCombiner c = new XJDFCombiner(h0, h1);
		c.mergeSet(h0.getSet(ElementName.NODEINFO, 0), h1.getSet(ElementName.NODEINFO, 0));

		Assertions.assertEquals(1, h0.getSet(ElementName.NODEINFO, 0).getPartitions().size());
	}

	/**
	 *
	 */
	@Test
	void testAssemblySection()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Stripping);
		final JDFAssembly med = (JDFAssembly) n.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
		final JDFAssembly[] a = new JDFAssembly[2];
		a[0] = (JDFAssembly) med.addPartition(EnumPartIDKey.PartVersion, "loc1");
		a[1] = (JDFAssembly) med.addPartition(EnumPartIDKey.PartVersion, "loc2");
		for (final JDFAssembly as : a)
		{
			as.setOrder(EnumOrder.None);
			as.appendAssemblySection().setAssemblyID("a1");
			as.appendAssemblySection().setAssemblyID("a2");

		}

		final JDFToXJDF conv = new JDFToXJDF();
		final KElement xjdf = conv.convert(n);
		final XJDFHelper h0 = new XJDFHelper(xjdf);
		final XJDFHelper h1 = new XJDFHelper(xjdf.cloneNewDoc());
		final XJDFCombiner c = new XJDFCombiner(h0, h1);
		final SetHelper sh = h0.getSet(ElementName.ASSEMBLY, 0);
		c.mergeSet(sh, h1.getSet(ElementName.ASSEMBLY, 0));

		Assertions.assertEquals(4, sh.getRoot().getChildrenByClass(JDFAssemblySection.class, true, 0).size());

	}

	/**
	 *
	 */
	@Test
	void testgetCombinedComplex()
	{
		final JDFNode product = JDFNode.parseFile(sm_dirTestData + "SampleFiles/MISPrepress-ICS-Complex.jdf");
		final JDFNode cp1 = product.getJobPart("PCS_CVP1058430001");
		final JDFNode cp2 = product.getJobPart("PCS_CVP1058460009");
		final XJDFHelper h1 = new XJDFHelper(new JDFToXJDF().convert(cp1));
		final XJDFHelper h2 = new XJDFHelper(new JDFToXJDF().convert(cp2));
		final XJDFCombiner c = new XJDFCombiner(h1, h2);

		c.combine();

		Assertions.assertNull(h1.getSet(ElementName.CONVENTIONALPRINTINGPARAMS, 1));
	}

}
