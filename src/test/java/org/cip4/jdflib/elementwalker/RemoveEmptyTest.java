/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.elementwalker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoComChannel.EnumChannelType;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.process.JDFComChannel;
import org.cip4.jdflib.resource.process.JDFContact;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date Oct 11, 2012
 */
public class RemoveEmptyTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 */
	@Test
	public void testRemove()
	{
		final JDFNode n = JDFDoc.parseFile(sm_dirTestData + "job4.jdf").getJDFRoot();
		final RemoveEmpty emp = new RemoveEmpty();
		emp.removEmpty(n);
		n.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "job4.jdf", 2, false);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveAttributes()
	{
		final XMLDoc d = new XMLDoc("doc", null);
		final KElement root = d.getRoot();
		root.setXPathAttribute("foo/@bar", "");
		final RemoveEmpty emp = new RemoveEmpty();
		assertNotNull(root.getXPathAttribute("foo/@bar", null));
		emp.removEmptyAttributes(root);
		assertNull(root.getXPathAttribute("foo/@bar", null));
		d.write2File(sm_dirTestDataTemp + "expty.xml", 2, false);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveExchangeResource()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(org.cip4.jdflib.node.JDFNode.EnumType.Product);

		final JDFNode n1 = n.addJDFNode("ProcessGroup");
		final JDFNode n2 = n.addJDFNode("ProcessGroup");
		final JDFResource as = n1.addResource(ElementName.APPROVALSUCCESS, EnumUsage.Output);
		n2.linkResource(as, EnumUsage.Input, null);
		final RemoveEmpty emp = new RemoveEmpty();
		emp.removEmpty(n);
		assertEquals(n, as.getParentJDF());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveResource()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.addResource(ElementName.LAYOUT, EnumUsage.Input).addPartition(EnumPartIDKey.SignatureName, "s1").addPartition(EnumPartIDKey.SheetName, "s1")
				.addPartition(EnumPartIDKey.Side, "Front");
		final RemoveEmpty emp = new RemoveEmpty();
		emp.removEmpty(n);
		assertFalse(n.toXML().contains(ElementName.LAYOUT));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveSpan()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFLayoutIntent loi = (JDFLayoutIntent) n.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		loi.appendFinishedDimensions();
		loi.appendDimensions().setPreferred(new JDFXYPair(2, 3));
		final RemoveEmpty emp = new RemoveEmpty();
		emp.removEmpty(n);
		assertNull(loi.getFinishedDimensions());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveSpan2()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFLayoutIntent loi = (JDFLayoutIntent) n.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		loi.appendFinishedDimensions();
		final RemoveEmpty emp = new RemoveEmpty();
		emp.removEmpty(n);
		assertNull(loi.getFinishedDimensions());
		assertNull(n.getResourceLinkPool());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveRef()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFResource m = n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResource xm = n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		xm.addPartition(EnumPartIDKey.SignatureName, "s1").addPartition(EnumPartIDKey.SheetName, "s1").addPartition(EnumPartIDKey.Side, "Front").setProductID("p");
		xm.refElement(m);
		final RemoveEmpty emp = new RemoveEmpty();
		emp.removEmpty(n);
		assertEquals(n.getResource(ElementName.MEDIA, EnumUsage.Input, 0), m);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveXMLComment()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.addResource(ElementName.LAYOUT, EnumUsage.Input).addPartition(EnumPartIDKey.SignatureName, "s1").addPartition(EnumPartIDKey.SheetName, "s1")
				.addPartition(EnumPartIDKey.Side, "Front").appendXMLComment("foo", null);
		final RemoveEmpty emp = new RemoveEmpty();
		emp.removEmpty(n);
		assertTrue(n.toXML().contains(ElementName.LAYOUT));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveComment()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		n.appendComment();
		final RemoveEmpty emp = new RemoveEmpty();
		emp.removEmpty(n);
		assertFalse(n.toXML().contains(ElementName.COMMENT));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemovePosition()
	{
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode n = d.getJDFRoot();
		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		sp.appendPosition();
		final RemoveEmpty emp = new RemoveEmpty();
		emp.removEmpty(n);
		final JDFStrippingParams sp2 = (JDFStrippingParams) n.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		assertNotNull(sp2.getPosition(0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveJMF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Response, EnumType.AbortQueueEntry);
		final RemoveEmpty emp = new RemoveEmpty();
		emp.removEmptyElement(jmf);
		assertNotNull(jmf.getResponse(0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveExtend()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.appendComment();
		n.appendElement("foo");
		final ExtendedRemoveEmpty emp = new ExtendedRemoveEmpty();
		emp.removEmpty(n);
		assertFalse(n.toXML().contains("<foo"));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testRemoveComChannel()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final JDFContact jdfContact = (JDFContact) n.addResource(ElementName.CONTACT, EnumUsage.Input);
		jdfContact.setDescriptiveName("c1");
		final JDFComChannel c = jdfContact.appendComChannel();
		c.setChannelType(EnumChannelType.Email);
		final RemoveEmpty emp = new RemoveEmpty();
		emp.removEmpty(n);
		assertFalse(n.toXML().contains(ElementName.COMCHANNEL));
	}
}
