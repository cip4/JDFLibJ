/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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

package org.cip4.jdflib.validate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.util.FileUtil;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         Jul 16, 2010
 */
class JDFValidatorTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testInlineColorPool()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFValidator validator = getValidator();
		final JDFNode node = doc.getJDFRoot();
		final JDFResource cc = node.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		final JDFColorPool cp = (JDFColorPool) cc.appendElement(ElementName.COLORPOOL);
		cp.appendColorWithName("B", null);
		cp.appendColorWithName("C", null);
		assertTrue(validator.isValid(doc));
	}

	/**
	 *
	 */
	@Test
	void testInlineMultiColorPool()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFValidator validator = getValidator();
		final JDFNode node = doc.getJDFRoot();
		final JDFResource cc = node.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		final JDFColorPool cp = (JDFColorPool) cc.appendElement(ElementName.COLORPOOL);
		cp.appendColorWithName("B", null).setActualColorName("A");
		cp.appendColorWithName("C", null).setActualColorName("A");

		assertFalse(validator.isValid(doc));
	}

	/**
	 *
	 */
	@Test
	void testInlineEmptyColorPool()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFValidator validator = getValidator();
		final JDFNode node = doc.getJDFRoot();
		final JDFResource cc = node.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		final JDFColorPool cp = (JDFColorPool) cc.appendElement(ElementName.COLORPOOL);
		assertTrue(validator.isValid(doc));
	}

	/**
	 *
	 */
	@Test
	void testExamples()
	{
		for (final Object l : EnumValidationLevel.getEnumList())
		{
			final JDFValidator validator = getValidator();
			validator.level = (EnumValidationLevel) l;
			for (final File f : FileUtil.listFilesInTree(new File(sm_dirTestData), "*.jdf"))
			{
				final JDFDoc d = JDFDoc.parseFile(f);
				if (f.length() < 1000000 && d != null)
				{
					assertNotNull(d);
					final boolean ok = validator.isValid(d);
					log.info(f.getPath() + " " + ok);
				}
			}
		}
	}

	/**
	 *
	 */
	@Test
	void testJMFExamples()
	{
		for (final Object l : EnumValidationLevel.getEnumList())
		{
			final JDFValidator validator = getValidator();
			validator.level = (EnumValidationLevel) l;
			for (final File f : FileUtil.listFilesInTree(new File(sm_dirTestData), "*.jmf"))
			{
				final JDFDoc d = JDFDoc.parseFile(f);
				if (d != null)
				{
					assertNotNull(d);
					final boolean ok = validator.isValid(d);
					log.info(f.getPath() + " " + ok);
				}
			}
		}
	}

	/**
	 *
	 */
	@Test
	void testResourceAuditLinks()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFValidator validator = getValidator();
		final JDFNode node = doc.getJDFRoot();
		final JDFResource media = node.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResourceAudit ra = node.getCreateAuditPool().addResourceAudit("dummy");
		final JDFResourceLink rl = ra.addNewOldLink(true, media, EnumUsage.Input);
		rl.setActualAmount(42, null);
		validator.setWarning(false);
		assertNotNull(rl.getInvalidAttributes(EnumValidationLevel.Incomplete, false, 0));
		assertTrue(validator.isValid(doc));
	}

	/**
	 *
	 */
	@Test
	void testTypo()
	{
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFValidator validator = getValidator();
		final JDFNode node = doc.getJDFRoot();
		final KElement e = node.appendElement("cip4:bar", "www.cip4.org");
		validator.setWarning(false);
		assertTrue(validator.isValid(doc));
		validator.setWarning(true);
		assertTrue(validator.isValid(doc));
	}

	private JDFValidator getValidator()
	{
		final JDFValidator validator = new JDFValidator();
		validator.setPrint(false);
		return validator;
	}

	/**
	 *
	 */
	@Test
	void testResourceInfo()
	{
		final JDFJMF jmf = JDFDoc.parseFile(sm_dirTestData + "ResourceInfo.jmf").getJMFRoot();
		final JDFResourceInfo resourceInfo = jmf.getResponse(0).getResourceInfo(0);
		assertTrue(resourceInfo.isValid(EnumValidationLevel.Complete));
		assertTrue(jmf.isValid(EnumValidationLevel.Complete));
	}

	/**
	 *
	 */
	@Test
	void testPrintURL()
	{
		final JDFValidator validator = getValidator();
		final KElement e = KElement.createRoot("abc");
		final KElement t = KElement.createRoot("test");
		validator.printURL(e, 0, t);
		e.setAttribute("URL", "blub");
		validator.printURL(e, 0, t);
		assertNotNull(t);
	}

	/**
	 *
	 */
	@Test
	void testMissingAll()
	{
		final JDFValidator validator = getValidator();
		final JDFNode empty = JDFNode.createRoot();
		validator.processSingleDocument(empty.getOwnerDocument_JDFElement());
	}

	/**
	 *
	 */
	@Test
	void testTypes()
	{
		final JDFValidator validator = getValidator();
		validator.level = EnumValidationLevel.Complete;
		final JDFNode empty = JDFNode.createRoot();
		empty.setType(org.cip4.jdflib.node.JDFNode.EnumType.ConventionalPrinting);
		final XMLDoc d = validator.processSingleDocument(empty.getOwnerDocument_JDFElement());
		assertNotNull(d);
	}

	/**
	 *
	 */
	@Test
	void testMultiID()
	{
		final JDFValidator validator = getValidator();
		final JDFNode empty = JDFNode.createRoot();
		empty.setType("foo", false);
		final JDFResource r = empty.addResource(ElementName.COMPONENT, EnumUsage.Input);
		empty.addResource(ElementName.COMPONENT, EnumUsage.Input).setID(r.getID());

		final XMLDoc d = validator.processSingleDocument(empty.getOwnerDocument_JDFElement());
		assertNotNull(d);
	}

	/**
	 *
	 */
	@Test
	void testBadParent()
	{
		final JDFValidator validator = getValidator();
		final JDFNode empty = JDFNode.createRoot();
		empty.setType("Product", false);

		empty.addProduct();
		empty.addProduct();
		empty.setType("ProcessGroup", false);
		final XMLDoc d = validator.processSingleDocument(empty.getOwnerDocument_JDFElement());
		assertNotNull(d);
	}

	/**
	 *
	 */
	@Test
	void testDeprecatedElem()
	{
		final JDFValidator validator = getValidator();
		final JDFNode empty = JDFNode.createRoot();
		empty.setVersion(EnumVersion.Version_1_6);
		empty.setType("Product", false);
		empty.appendElement(ElementName.CUSTOMERINFO);
		empty.appendElement(ElementName.NODEINFO);
		final XMLDoc d = validator.processSingleDocument(empty.getOwnerDocument_JDFElement());
		assertNotNull(d);
	}

	/**
	 *
	 */
	@Test
	void testDangleref()
	{
		final JDFValidator validator = getValidator();
		final JDFNode empty = JDFNode.createRoot();
		empty.setVersion(EnumVersion.Version_1_6);
		empty.setType("Product", false);
		final JDFResource c1 = empty.addProduct().addResource(ElementName.COMPONENT, EnumUsage.Input);
		final JDFResource c2 = empty.addProduct().addResource(ElementName.COMPONENT, EnumUsage.Input);
		final JDFResource c3 = empty.addProduct().addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResource c4 = empty.addResource(ElementName.MEDIA, EnumUsage.Input);
		c2.appendElement("MediaRef").setAttribute(AttributeName.RREF, c3.appendAnchor(null));
		c1.appendElement("FooRef").setAttribute(AttributeName.RREF, c4.appendAnchor(null));
		final XMLDoc d = validator.processSingleDocument(empty.getOwnerDocument_JDFElement());
		assertNotNull(d);
	}

	/**
	 *
	 */
	@Test
	void testMultiJPID()
	{
		final JDFNode empty = JDFNode.createRoot();
		empty.setType("Product", false);

		empty.addJDFNode(org.cip4.jdflib.node.JDFNode.EnumType.Product).setJobPartID(null);
		empty.addJDFNode(org.cip4.jdflib.node.JDFNode.EnumType.Product).setJobPartID("P1");
		empty.addJDFNode(org.cip4.jdflib.node.JDFNode.EnumType.Product).setJobPartID("P1");
		final JDFValidator validator = getValidator();
		final XMLDoc d = validator.processSingleDocument(empty.getOwnerDocument_JDFElement());
		assertNotNull(d);
	}

	/**
	 *
	 */
	@Test
	void testMissingLinks()
	{
		final JDFNode empty = JDFNode.createRoot();
		empty.setType("Product", false);

		empty.addJDFNode(org.cip4.jdflib.node.JDFNode.EnumType.Product).setJobPartID(null);
		empty.addJDFNode(org.cip4.jdflib.node.JDFNode.EnumType.Product).setJobPartID("P1");
		empty.addJDFNode(org.cip4.jdflib.node.JDFNode.EnumType.Product).setJobPartID("P1");
		final JDFValidator validator = getValidator();
		final XMLDoc d = validator.processSingleDocument(empty.getOwnerDocument_JDFElement());
		assertNotNull(d);
	}

	/**
	 *
	 */
	@Test
	void testColorPool()
	{
		final JDFNode empty = JDFNode.createRoot();
		empty.setType("Product", false);
		final JDFColorPool cp = (JDFColorPool) empty.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.getCreateColorWithName("ab", "ab");
		final JDFColor c = cp.appendColorWithName("abc", "abc");
		c.setActualColorName("ab");
		c.setName("ab");
		final JDFValidator validator = getValidator();
		final XMLDoc d = validator.processSingleDocument(empty.getOwnerDocument_JDFElement());
		assertNotNull(d);
	}

	/**
	 *
	 */
	@Test
	void testDeprecated()
	{
		final JDFValidator validator = getValidator();
		final JDFJMF jmf = JDFDoc.parseFile(sm_dirTestData + "ResourceInfo.jmf").getJMFRoot();
		final JDFResourceInfo resourceInfo = jmf.getResponse(0).getResourceInfo(0);
		final KElement root = KElement.createRoot("foo");
		validator.checkDeprecated(0, root, "ResourceInfo", validator.getTestElement(resourceInfo, root), resourceInfo, false);
		validator.checkDeprecated(0, root, "ResourceInfo", validator.getTestElement(resourceInfo, root), resourceInfo, true);
		validator.setWarning(true);
		validator.checkDeprecated(0, root, "ResourceInfo", validator.getTestElement(resourceInfo, root), resourceInfo, false);
		validator.checkDeprecated(0, root, "ResourceInfo", validator.getTestElement(resourceInfo, root), resourceInfo, true);
		root.setAttribute("DeprecatedElements", ElementName.RESOURCEINFO);
		validator.checkDeprecated(0, root, "ResourceInfo", validator.getTestElement(resourceInfo, root), resourceInfo, true);
	}

}
