/*
 *
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
 *
 */
package org.cip4.jdflib.resource.process;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 */
public class JDFDieLayoutTest extends JDFTestCaseBase
{
	private JDFNode n;
	private JDFDoc d;

	/**
	 * tests the proposed Color/@PDLName attribute
	 * 
	 * @throws Exception
	 *
	 */
	@Test
	public final void testStationMapStrip() throws Exception
	{
		n.setType(EnumType.Stripping);
		final JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, null);
		final JDFDieLayout dl = bs.appendDieLayout();
		JDFStation station = dl.appendStation();
		station.setStationName("Lid");
		station.setStationAmount(9);
		station = dl.appendStation();
		station.setStationName("Box");
		station.setStationAmount(3);
		final JDFResource sm = n.addResource("StationMap", EnumResourceClass.Parameter, null, null, null, null, null);
		sm.setXMLComment("The partition structure should reflect the product structure ", true);
		JDFResource sm1 = sm.addPartition(EnumPartIDKey.PartVersion, "Strawberry");
		sm1.setAttribute("StationName", "Lid");
		sm1.setAttribute("MapAmount", "3");
		sm1.setAttribute("StationIndex", "0 1 2");
		sm1.setXMLComment("3*Strawberry MUST be placed on station index 0-2", true);

		sm1 = sm.addPartition(EnumPartIDKey.PartVersion, "Blueberry");
		sm1.setAttribute("StationName", "Lid");
		sm1.setAttribute("MapAmount", "2");
		sm1.setXMLComment("2*Blueberry can be placed on any remaining lid station", true);

		sm1 = sm.addPartition(EnumPartIDKey.PartVersion, "Cherry");
		sm1.setAttribute("StationName", "Lid");
		sm1.setAttribute("MapAmount", "4");
		sm1.setXMLComment("4*Cherry can be placed on any remaining lid station", true);

		dl.refElement(sm);

		d.write2File(sm_dirTestDataTemp + "StationMapStripping.jdf", 2, false);

	}

	/**
	 *
	 *
	 */
	@Test
	void testStation()
	{
		final JDFDieLayout lo = (JDFDieLayout) n.addResource(ElementName.DIELAYOUT, EnumUsage.Input);
		lo.appendStation();
		Assertions.assertTrue(lo.toXML().indexOf("<Station") > 0);
	}

	/**
	 *
	 *
	 */
	@Test
	void testRuleLength()
	{
		final JDFDieLayout lo = (JDFDieLayout) n.addResource(ElementName.DIELAYOUT, EnumUsage.Input);
		lo.appendRuleLength();
		Assertions.assertTrue(lo.toXML().indexOf("<RuleLength") > 0);
	}

	/**
	 *
	 *
	 */
	@Test
	void testFileSpec()
	{
		final JDFDieLayout lo = (JDFDieLayout) n.addResource(ElementName.DIELAYOUT, EnumUsage.Input);
		final JDFFileSpec fs = lo.appendFileSpec();
		Assertions.assertEquals(fs, lo.getCreateFileSpec());
		Assertions.assertEquals(fs, lo.getCreateFileSpec(0));
	}

	/**
	 *
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		d = new JDFDoc(ElementName.JDF);
		n = d.getJDFRoot();

	}

}
