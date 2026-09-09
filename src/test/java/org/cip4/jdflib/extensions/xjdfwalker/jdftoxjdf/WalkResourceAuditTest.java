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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.lang.reflect.Method;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoResourceAudit.EnumReason;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WalkResourceAuditTest extends JDFTestCaseBase
{
	@Test
	void testGetElementNames()
	{
		final WalkResourceAudit w = new WalkResourceAudit();
		Assertions.assertTrue(w.getElementNames().contains(ElementName.RESOURCEAUDIT));
	}

	@Test
	@SuppressWarnings("deprecation")
	void testWalk()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFMedia media = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResourceAudit audit = n.getCreateAuditPool().addResourceAudit("writer");
		audit.setReason(EnumReason.ProcessResult);
		final JDFResourceLink newLink = audit.addNewOldLink(true, media, EnumUsage.Input);
		newLink.setAmount(33.0);

		final JDFToXJDF conv = new JDFToXJDF();
		conv.newRoot = JDFElement.createRoot("XJDF");
		final WalkResourceAudit w = new WalkResourceAudit();
		w.setParent(conv);
		Assertions.assertTrue(w.matches(audit));

		final KElement target = conv.newRoot.appendElement(ElementName.AUDITPOOL);
		final KElement converted = w.walk(audit, target);

		Assertions.assertNotNull(converted);
		Assertions.assertEquals(XJDFConstants.AuditResource, converted.getLocalName());
		Assertions.assertNull(converted.getNonEmpty(AttributeName.REASON));
		Assertions.assertNotNull(converted.getElement(ElementName.RESOURCEINFO));
	}

	@Test
	void testMatchesRetainAll()
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFResourceAudit audit = n.getCreateAuditPool().addResourceAudit("writer");

		final JDFToXJDF conv = new JDFToXJDF();
		conv.setRetainAll(true);
		final WalkResourceAudit w = new WalkResourceAudit();
		w.setParent(conv);

		Assertions.assertFalse(w.matches(audit));
	}

	@Test
	void testUpdateLinkMovesPartMap() throws Exception
	{
		final JDFNode n = JDFNode.createRoot();
		n.setType(EnumType.ConventionalPrinting);
		final JDFMedia media = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResourceAudit audit = n.getCreateAuditPool().addResourceAudit("writer");
		audit.setPartMap(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"));
		final JDFResourceLink newLink = audit.addNewOldLink(true, media, EnumUsage.Input);
		newLink.setAmount(1.0);

		final JDFToXJDF conv = new JDFToXJDF();
		final WalkResourceAudit w = new WalkResourceAudit();
		w.setParent(conv);

		final Method m = WalkResourceAudit.class.getDeclaredMethod("updateLink", JDFResourceAudit.class);
		m.setAccessible(true);
		final JDFResourceLink updated = (JDFResourceLink) m.invoke(w, audit);

		Assertions.assertNotNull(updated);
		Assertions.assertNotNull(updated.getPart(0));
		Assertions.assertNull(audit.getPart(0));
	}

	@Test
	void testRoundTrip()
	{
		final XJDFHelper h = new XJDFHelper("WalkResourceAudit", null);
		h.setTypes(EnumType.ConventionalPrinting.getName());
		final SetHelper outSet = h.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
		outSet.getCreatePartition(0, true).setAmount(10, null, true);
		final SetHelper inSet = h.getCreateSet(ElementName.COMPONENT, EnumUsage.Input);
		inSet.getCreatePartition(0, true).getResource();

		final JDFElement jdf = writeRoundTripX(h, "walkresourceaudit", EnumValidationLevel.Incomplete, true);
		Assertions.assertNotNull(jdf);
	}
}
