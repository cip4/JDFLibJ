/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2026 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.junit.jupiter.api.Test;

class WalkEmployeeTest extends JDFTestCaseBase
{
	@Test
	void testGetElementNames()
	{
		final WalkEmployee walker = new WalkEmployee();
		assertTrue(walker.getElementNames().contains(ElementName.EMPLOYEE));
	}

	@Test
	void testWalkRoleToRoles()
	{
		final WalkEmployee walker = new WalkEmployee();
		walker.setParent(new XJDFToJDFImpl(null));

		final KElement employee = new JDFDoc(ElementName.EMPLOYEE).getRoot();
		employee.setAttribute(XJDFConstants.Role, "CSR");
		final KElement nodeInfo = new JDFDoc(ElementName.NODEINFO).getRoot();
		final KElement walked = walker.walk(employee, nodeInfo);
		assertNotNull(walked);
		assertEquals("CSR", walked.getAttribute(AttributeName.ROLES));
	}

	@Test
	void testRoundTrip()
	{
		final XJDFHelper helper = new XJDFHelper("WalkEmployee", "p1", null);
		helper.setTypes("Product");
		helper.getCreateRootProduct(0);

		final SetHelper setContact = helper.getCreateSet(ElementName.CONTACT, EnumUsage.Input);
		final ResourceHelper contactResource = setContact.getCreatePartition(new JDFAttributeMap(XJDFConstants.ContactType, "Employee"), true);
		contactResource.setExternalID("P1");
		final KElement contact = contactResource.getCreateResource();
		contact.setAttribute(AttributeName.CONTACTTYPEDETAILS, "CSR");
		contact.appendElement(ElementName.PERSON).setAttribute(AttributeName.FIRSTNAME, "First");

		final SetHelper setNodeInfo = helper.getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
		final ResourceHelper nodeInfoResource = setNodeInfo.getCreatePartition(0, true);
		nodeInfoResource.getResource().setAttribute(AttributeName.PERSONALID, "P1");

		helper.cleanUp();
		final JDFElement root = new XJDFToJDFConverter(null).convert(helper.getRoot()).getJDFRoot();
		assertNotNull(root);
		assertTrue(root instanceof JDFNode);
		final KElement nodeInfo = ((JDFNode) root).getResource(ElementName.NODEINFO, EnumUsage.Input, 0);
		assertNotNull(nodeInfo);
		final KElement employee = nodeInfo.getElement(ElementName.EMPLOYEE);
		assertNotNull(employee);
		assertEquals("P1", employee.getAttribute(AttributeName.PERSONALID));
	}
}
