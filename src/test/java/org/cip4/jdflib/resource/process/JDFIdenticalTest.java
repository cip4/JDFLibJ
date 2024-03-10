/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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
package org.cip4.jdflib.resource.process;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JDFIdenticalTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testGetTargetRoot()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource r = n.addResource(ElementName.EXPOSEDMEDIA, null);
		r.appendIdentical();
		Assertions.assertEquals(r, r.getIdentical().getTarget());
	}

	/**
	 *
	 */
	@Test
	void testGetTargetLeafGood()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource r = n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		r1.setIdentical(r2);
		Assertions.assertEquals(r2, r.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName, "s1"), null));
		Assertions.assertEquals(r2, r.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName, "s2"), null));
	}

	/**
	 *
	 */
	@Test
	void testGetTargetLeafBad()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource r = n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		r1.setIdentical(r2);
		r1.getIdentical().setPartMap(new JDFAttributeMap(EnumPartIDKey.SheetName, "s1"));
		Assertions.assertEquals(r1, r.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName, "s1"), null));
		Assertions.assertEquals(r2, r.getPartition(new JDFAttributeMap(EnumPartIDKey.SheetName, "s2"), null));
	}

	/**
	 *
	 */
	@Test
	void testGetLeaves()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource r = n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		r2.setDescriptiveName("foo");
		r1.setIdentical(r2);
		Assertions.assertEquals("", ((JDFElement) r.getLeaves(false).get(0)).getDescriptiveName());
		Assertions.assertEquals("foo", ((JDFElement) r.getLeaves(false).get(1)).getDescriptiveName());
	}

	/**
	 *
	 */
	@Test
	void testRemoveIdentical()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource r = n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		r2.setDescriptiveName("foo");
		r1.setIdentical(r2);
		VElement leaves = r.getLeaves(false);
		leaves = JDFIdentical.removeIdenticals(leaves);
		Assertions.assertEquals("foo", ((JDFElement) leaves.get(0)).getDescriptiveName());
		Assertions.assertEquals(1, leaves.size());
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	void testCollapseIdentical()
	{
		final JDFNode n = JDFNode.createRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResource sig = dpp.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource f = sh.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = sh.addPartition(EnumPartIDKey.Side, "Back");
		f.setDescriptiveName("s");
		b.setIdentical(f);
		sh.setDescriptiveName("sh");
		dpp.collapse(false, false);
		Assertions.assertEquals("sh", sh.getDescriptiveName());
	}

	/**
	 * test expand and collapse methods
	 */
	@Test
	void testUpdateFromLeavesIdentical()
	{
		final JDFNode n = JDFNode.createRoot();

		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		final JDFResource sig = dpp.addPartition(EnumPartIDKey.SignatureName, "s1");
		final JDFResource sh = sig.addPartition(EnumPartIDKey.SheetName, "sh1");
		final JDFResource f = sh.addPartition(EnumPartIDKey.Side, "Front");
		final JDFResource b = sh.addPartition(EnumPartIDKey.Side, "Back");
		f.setDescriptiveName("s");
		b.setIdentical(f);
		sh.setDescriptiveName("sh");
		dpp.updateAttributeFromLeaves(AttributeName.DESCRIPTIVENAME, null, false);
		Assertions.assertEquals("sh", sh.getDescriptiveName());
	}

	/**
	 *
	 */
	@Test
	void testGetTargetBad()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource r = n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		r1.setIdentical(r2);
		final JDFIdentical identical = r1.getIdentical();
		identical.setPartMap(new JDFAttributeMap(EnumPartIDKey.SheetName, "s1"));
		Assertions.assertEquals(r1, identical.getTarget());
	}

	/**
	 *
	 */
	@Test
	void testGetTargetGood()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource r = n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		r1.setIdentical(r2);
		final JDFIdentical identical = r1.getIdentical();
		Assertions.assertEquals(r2, identical.getTarget());
	}

	/**
	*
	*/
	@Test
	void testResStatus()
	{
		final JDFNode n = JDFNode.createRoot();
		final JDFResource r = n.addResource(ElementName.EXPOSEDMEDIA, null);
		final JDFResource r1 = r.addPartition(EnumPartIDKey.SheetName, "s1");
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SheetName, "s2");
		r1.setIdentical(r2);
		r.setResStatus(EnumResStatus.Unavailable, true);
		r2.setResStatus(EnumResStatus.Available, true);
		Assertions.assertEquals(EnumResStatus.Available, r1.getResStatus(false));
	}

}
