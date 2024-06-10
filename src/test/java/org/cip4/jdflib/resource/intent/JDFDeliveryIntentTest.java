/*
 *
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
package org.cip4.jdflib.resource.intent;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFTimeSpan;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class JDFDeliveryIntentTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testAppendOverage()
	{
		final JDFDeliveryIntent di = (JDFDeliveryIntent) new JDFDoc(ElementName.DELIVERYINTENT).getRoot();
		final JDFNumberSpan ns = di.appendOverage();
		Assertions.assertNotNull(ns);
	}

	/**
	 *
	 */
	@Test
	void testGetDropItemForComponent()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFDeliveryIntent di = (JDFDeliveryIntent) n.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFDropIntent drop = di.appendDropIntent();
		Assertions.assertNull(drop.getDropItemWithComponent(c));
		final JDFDropItemIntent dropItemIntent = drop.appendDropItemIntent();
		dropItemIntent.refComponent(c);
		Assertions.assertEquals(dropItemIntent, drop.getDropItemWithComponent(c));
	}

	/**
	 *
	 */
	@Test
	void testGetDropItemForComponentPart()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFDeliveryIntent di = (JDFDeliveryIntent) n.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFComponent c1 = (JDFComponent) c.addPartition(EnumPartIDKey.SheetName, "S1");
		final JDFComponent c2 = (JDFComponent) c.addPartition(EnumPartIDKey.SheetName, "S2");
		final JDFDropIntent drop = di.appendDropIntent();
		Assertions.assertNull(drop.getDropItemWithComponent(c1));
		final JDFDropItemIntent dropItemIntent = drop.appendDropItemIntent();
		dropItemIntent.refComponent(c1);
		Assertions.assertEquals(dropItemIntent, drop.getDropItemWithComponent(c1));
		Assertions.assertNull(drop.getDropItemWithComponent(c2));
		Assertions.assertNull(drop.getDropItemWithComponent(c));
	}

	/**
	 *
	 */
	@Test
	void testGetDropID()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFDeliveryIntent di = (JDFDeliveryIntent) n.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFDropIntent drop = di.appendDropIntent();
		drop.setDropID("d1");
		Assertions.assertEquals("d1", drop.getDropID());
	}

	/**
	 *
	 */
	@Test
	void testGetDropItemID()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFDeliveryIntent di = (JDFDeliveryIntent) n.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFDropIntent drop = di.appendDropIntent();
		final JDFDropItemIntent dii = drop.appendDropItemIntent();
		dii.setDropID("d1");
		Assertions.assertEquals("d1", dii.getDropID());
	}

	/**
	*
	*/
	@Test
	void testGetDropItemIDInherit()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFDeliveryIntent di = (JDFDeliveryIntent) n.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFDropIntent drop = di.appendDropIntent();
		final JDFDropItemIntent dii = drop.appendDropItemIntent();
		drop.setDropID("d1");
		Assertions.assertEquals("d1", dii.getDropID());
	}

	/**
	 *
	 */
	@Test
	void testGetDropDropItemForComponentPart()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFDeliveryIntent di = (JDFDeliveryIntent) n.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFComponent c1 = (JDFComponent) c.addPartition(EnumPartIDKey.SheetName, "S1");
		final JDFComponent c2 = (JDFComponent) c.addPartition(EnumPartIDKey.SheetName, "S2");
		final JDFDropIntent drop = di.appendDropIntent();
		Assertions.assertNull(di.getDropItemWithComponent(c1));
		final JDFDropItemIntent dropItemIntent = drop.appendDropItemIntent();
		dropItemIntent.refComponent(c1);
		Assertions.assertEquals(dropItemIntent, di.getDropItemWithComponent(c1));
		Assertions.assertNull(di.getDropItemWithComponent(c2));
		Assertions.assertNull(di.getDropItemWithComponent(c));
	}

	/**
	 *
	 */
	@Test
	void testGetCreateDropItemForComponent()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFDeliveryIntent di = (JDFDeliveryIntent) n.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFDropIntent drop = di.appendDropIntent();
		Assertions.assertNull(drop.getDropItemWithComponent(c));
		final JDFDropItemIntent dropItemIntent = drop.getCreateDropItemWithComponent(c);
		Assertions.assertEquals(dropItemIntent, drop.getDropItemWithComponent(c));
	}

	/**
	 *
	 */
	@Test
	void testGetCreateDropItemForComponentPart()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFDeliveryIntent di = (JDFDeliveryIntent) n.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFComponent c = (JDFComponent) n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFComponent c1 = (JDFComponent) c.addPartition(EnumPartIDKey.SheetName, "S1");
		final JDFComponent c2 = (JDFComponent) c.addPartition(EnumPartIDKey.SheetName, "S2");
		final JDFDropIntent drop = di.appendDropIntent();
		Assertions.assertNull(drop.getDropItemWithComponent(c1));
		JDFDropItemIntent dropItemIntent = drop.getCreateDropItemWithComponent(c1);
		Assertions.assertEquals(dropItemIntent, drop.getDropItemWithComponent(c1));
		Assertions.assertNull(drop.getDropItemWithComponent(c));
		Assertions.assertNull(drop.getDropItemWithComponent(c2));
		dropItemIntent = drop.getCreateDropItemWithComponent(c2);
		Assertions.assertEquals(dropItemIntent, drop.getDropItemWithComponent(c2));
	}

	/**
	*
	*/
	@Test
	void testAppendRequired()
	{
		final JDFDeliveryIntent di = (JDFDeliveryIntent) new JDFDoc(ElementName.DELIVERYINTENT).getRoot();
		final JDFTimeSpan req = di.appendRequired();
		Assertions.assertNull(req.getActual());
		Assertions.assertNull(req.getRange());
		final JDFDate date = new JDFDate();
		req.setActual(date);
		Assertions.assertEquals(req.getActual(), date);
	}
}
