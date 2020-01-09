/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.xml.validation.SchemaFactory;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFParserFactory;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XJDFSchemaTest extends JDFTestCaseBase
{

	class ChangeOrderMaker
	{

		protected HashSet<String> fillIgnore()
		{
			final HashSet<String> s = new HashSet<>();
			s.add("Intent");
			s.add("PartWaste");
			s.add("Dependent");
			s.add("XJDF");
			s.add("ResourceSet");
			s.add("DeviceInfo");
			s.add("GangInfo");
			s.add("JobPhase");
			s.add("Header");
			s.add("MessageService");
			s.add("PipeParams");
			s.add("QueueEntry");
			s.add("QueueSubmissionParams");
			s.add("RequestQueueEntryParams");
			s.add("ResourceCmdParams");
			s.add("ResourceQuParams");
			s.add("ResubmissionParams");
			s.add("ReturnQueueEntryParams");
			s.add("Subscription");
			s.add("SubscriptionInfo");
			s.add("BundleItem/@Amount");
			s.add("Event");
			s.add("Icon");
			s.add("Milestone");
			s.add("Notification");
			s.add("ProcessRun");
			s.add("Module/@ModuleID");
			s.add("ColorantAlias");
			s.add("ComChannel");
			s.add("GeneralID");

			s.add("AmountPool/PartAmount");
			s.add("ProductList/Product");
			s.add("XJMF");
			s.add("Message");
			s.add("ResourceInfo");
			s.add("Audit/Header");
			s.add("ModifyQueueEntryParams");
			s.add("");
			return s;
		}

		void createChangeOrder(final int minor)
		{

			final XMLDoc d = XMLDoc.parseFile(getXJDFSchema(2, minor));
			final KElement root = d.getRoot();
			final VElement attribs = root.getChildrenByTagName("xs:attribute", null, null, false, true, 0);
			for (final KElement a : attribs)
			{
				if (!isRequired(a))
				{
					log.info("making optional attribute: " + getPath(a) + "/@" + a.getAttribute("name"));
					a.setAttribute("use", "optional");
				}
			}
			final VElement elems = root.getChildrenByTagName("xs:element", null, null, false, true, 0);
			final VElement rootelems = root.getChildElementVector("xs:element", null);
			elems.removeAll(rootelems);
			for (final KElement e : elems)
			{
				if (!isRequiredElem(e))
				{
					log.info("making optional element: " + getPath(e) + "/" + e.getAttribute("name") + e.getAttribute("ref"));
					e.setAttribute("minOccurs", "0");
				}
			}
			root.setXMLComment(" XJDF 2." + minor + " Schema updated on " + new JDFDate().getFormattedDateTime(JDFDate.DATEISO) + " ", false);
			d.write2File(sm_dirTestDataTemp + "changeschema/xjdf2_" + minor + "/xjdf.xsd", 2, false);
		}

		Set<String> ignore;

		ChangeOrderMaker()
		{
			super();
			ignore = fillIgnore();
		}

		private boolean isRequiredElem(final KElement e)
		{
			final String parentPath = getPath(e);
			final String path = parentPath + "/" + e.getAttribute("name") + e.getAttribute("ref");

			if ("0".equals(getMinOccurs(e)) || ignore.contains(path) || ignore.contains(parentPath) || isMessage(parentPath))
				return true;
			return false;
		}

		private boolean isMessage(final String parentPath)
		{
			return MessageHelper.isMessage(new XMLDoc(parentPath, null).getRoot());
		}

		protected String getMinOccurs(final KElement e)
		{
			if (e == null)
				return "1";
			final String nonEmpty = e.getNonEmpty("minOccurs");
			if (nonEmpty == null)
				return getMinOccurs(e.getParentNode_KElement());
			return nonEmpty;
		}

		private boolean isRequired(final KElement a)
		{
			final String parentPath = getPath(a);
			final String path = parentPath + "/@" + a.getAttribute("name");

			if ("optional".equals(a.getNonEmpty("use")) || ignore.contains(path) || ignore.contains(parentPath))
				return true;
			return false;
		}

		private String getPath(final KElement a)
		{
			final KElement p = a.getParentNode_KElement();
			final String s = p.getAttribute("name") + p.getAttribute("ref");
			if (s.isEmpty())
				return getPath(p);
			else
				return s;
		}
	}

	/**
	 *
	 */
	@Test
	public void testGetXJDFSchema()
	{
		assertNotNull(XMLDoc.parseFile(getXJDFSchema()));
	}

	/**
	 *
	 */
	@Test
	public void testGetXJDFSchema21()
	{
		assertNotNull(XMLDoc.parseFile(getXJDFSchema(2, 1)));
	}

	/**
	 *
	 */
	@Test
	public void testBaseValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		final JDFDoc doc = getXJDFSchemaParser().parseString(root.toXML());
		assertNotNull(doc.getValidationResult());
	}

	/**
	 *
	 */
	@Test
	public void testSimpleValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"ConventionalPrintingParams\"]/Resource/ConventionalPrintingParams/@WorkStyle", "WorkAndTurn");
		root.setXPathAttribute("ResourceSet[@Name=\"ConventionalPrintingParams\"]/@Usage", "Input");
		root.setAttribute("Types", "ConventionalPrinting");
		writeTest(root, "../SimpleCP.xjdf", true, null);
	}

	/**
	 *
	 */
	@Test
	public void testVersion20()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"ConventionalPrintingParams\"]/Resource/ConventionalPrintingParams/@WorkStyle", "WorkAndTurn");
		root.setXPathAttribute("ResourceSet[@Name=\"ConventionalPrintingParams\"]/@Usage", "Input");
		root.setAttribute("Types", "ConventionalPrinting");
		root.setAttribute(AttributeName.VERSION, "2.0");
		writeTest(root, "../SimpleCP.xjdf", true, null);
	}

	/**
	 *
	 */
	@Test
	public void testChange()
	{
		for (int i = 0; i < 2; i++)
		{
			new ChangeOrderMaker().createChangeOrder(i);
		}
	}

	/**
	 *
	 */
	@Test
	public void testVersion21()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"ConventionalPrintingParams\"]/Resource/ConventionalPrintingParams/@WorkStyle", "WorkAndTurn");
		root.setXPathAttribute("ResourceSet[@Name=\"ConventionalPrintingParams\"]/@Usage", "Input");
		root.setAttribute("Types", "ConventionalPrinting");
		root.setAttribute(AttributeName.VERSION, "2.1");
		writeTest(root, "../SimpleCP.xjdf", true, null);
	}

	/**
	 *
	 */
	@Test
	public void testProductPV()
	{
		final KElement root = new XJDFHelper(EnumVersion.Version_2_1, "j1").getRoot();
		final XJDFHelper xjdfHelper = new XJDFHelper(root);
		xjdfHelper.getCreateRootProduct(0).setAttribute(AttributeName.PARTVERSION, "PV1");
		xjdfHelper.setTypes(JDFConstants.PRODUCT);
		xjdfHelper.cleanUp();
		assertTrue(reparse(root, 2, 1));
	}

	/**
	 * @throws SAXNotSupportedException
	 * @throws SAXNotRecognizedException
	 *
	 */
	@Test
	@Ignore
	public void testLocalSchema() throws SAXNotRecognizedException, SAXNotSupportedException
	{
		final KElement schema = KElement.parseFile(getXJDFSchema(2, 1));
		schema.setAttribute("vc:minVersion", "1.1", "http://www.w3.org/2007/XMLSchema-versioning");
		final KElement rect = schema.getChildWithAttribute("xs:simpleType", "name", null, "rectangle", 0, true);
		final KElement restrict = rect.getElement("xs:restriction");
		restrict.appendElement("xs:assert").setAttribute("test", "fn:starts-with($value, '0 0 ')");
		final String schemaUrl = sm_dirTestDataTemp + "testschema.xsd";
		schema.write2File(schemaUrl);
		final KElement root = new XJDFHelper(EnumVersion.Version_2_1, "j1").getRoot();
		final XJDFHelper xjdfHelper = new XJDFHelper(root);
		xjdfHelper.getCreateRootProduct(0).setAttribute(AttributeName.PARTVERSION, "PV1");
		xjdfHelper.setTypes(JDFConstants.PRODUCT);
		final KElement c = xjdfHelper.getCreateSet(XJDFConstants.Content, EnumUsage.Input).getCreatePartition(0, true).getResource();
		c.setAttribute(AttributeName.SOURCEBLEEDBOX, "0 0 1 2");
		xjdfHelper.cleanUp();
		final JDFParser parser = JDFParserFactory.getFactory().get();
		parser.setSchemaLocation(JDFElement.getSchemaURL(2, 1), UrlUtil.normalize(schemaUrl));
		//TODO update linked xerces
		SchemaFactory.newInstance("http://www.w3.org/XML/XMLSchema/v1.1");
		assertNotNull(parser.parseString(root.toXML()));
	}

	/**
	 *
	 */
	@Test
	public void testProductPVBind()
	{
		final KElement root = new XJDFHelper(EnumVersion.Version_2_1, "j1").getRoot();
		final XJDFHelper xjdfHelper = new XJDFHelper(root);
		final ProductHelper createRootProduct = xjdfHelper.getCreateRootProduct(0);
		createRootProduct.setID("i1");
		final KElement createResource = createRootProduct.appendIntent(ElementName.BINDINGINTENT).getCreateResource();
		createResource.setAttribute(XJDFConstants.ChildRefs, "i1 i1");
		createResource.setAttribute(ElementName.BINDINGTYPE, "SaddleStitch");
		createRootProduct.setAttribute(AttributeName.PARTVERSION, "PV1");
		xjdfHelper.setTypes(JDFConstants.PRODUCT);
		xjdfHelper.cleanUp();
		writeTest(root, "../ProductPV.xjdf", true, null);
	}

	/**
	 *
	 */
	@Test
	public void testCMYKValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"Color\"]/Resource/Color/@CMYK", "1 0.2 0.3 0.4");
		root.setAttribute("Types", "ConventionalPrinting");
		assertTrue(reparse(root, 2, 0));
		root.setXPathAttribute("ResourceSet[@Name=\"Color\"]/Resource/Color/@CMYK", "1 0.2 0.3");
		assertFalse(reparse(root, 2, 0));
		root.setXPathAttribute("ResourceSet[@Name=\"Color\"]/Resource/Color/@CMYK", "1 0.2 0.3 0.4 0.5");
		assertFalse(reparse(root, 2, 0));
		root.setXPathAttribute("ResourceSet[@Name=\"Color\"]/Resource/Color/@CMYK", "1 0,2 0,3 0,4");
		assertFalse(reparse(root, 2, 0));
	}

	/**
	 *
	 */
	@Test
	public void testCountryCodeValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setAttribute("Types", "ConventionalPrinting");
		root.setXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Contact/Address/@CountryCode", "DE");
		assertTrue(reparse(root, 2, 1));
		root.setXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Contact/Address/@CountryCode", "D2");
		assertFalse(reparse(root, 2, 1));
		root.setXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Contact/Address/@CountryCode", "987");
		assertFalse(reparse(root, 2, 1));
	}

	/**
	 *
	 */
	@Test
	public void testFileSpecValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/@CheckSum", StringUtil.setHexBinaryBytes(new byte[] { 1, 2, 3 }, -1));
		root.setAttribute("Types", "ConventionalPrinting");
		assertTrue(reparse(root, 2, 0));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/@CheckSum", "123");
		assertFalse(reparse(root, 2, 0));
	}

	/**
	 *
	 */
	@Test
	public void testFileSpecValidateLong()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/@FileSize", "123456789012345");
		root.setAttribute("Types", "ConventionalPrinting");
		assertTrue(reparse(root, 2, 0));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/@FileSize", "123456789012345.12");
		assertFalse(reparse(root, 2, 0));
	}

	/**
	 *
	 */
	@Test
	public void testNetworkHeader()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/@CheckSum", StringUtil.setHexBinaryBytes(new byte[] { 1, 2, 3 }, -1));
		root.setAttribute("Types", "ConventionalPrinting");
		assertTrue(reparse(root, 2, 1));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/NetworkHeader/@Name", "HeaderName");
		assertFalse(reparse(root, 2, 1));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/NetworkHeader/@Value", "HeaderName");
		assertTrue(reparse(root, 2, 1));
		assertFalse(reparse(root, 2, 0));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/NetworkHeader[2]/@Name", "HeaderName");
		assertFalse(reparse(root, 2, 1));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/NetworkHeader[2]/@Value", "HeaderName");
		assertTrue(reparse(root, 2, 1));
	}

	/**
	 *
	 */
	@Test
	public void testDateTimeValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"NodeInfo\"]/Resource/NodeInfo/@Start", "12345");
		root.setAttribute("Types", "ConventionalPrinting");
		final String xml = root.getOwnerDocument_KElement().write2String(2);
		final JDFParser p = getXJDFSchemaParser(2, 0);
		final JDFDoc xParsed = p.parseString(xml);
		assertFalse(xParsed.isSchemaValid());
		root.setXPathAttribute("ResourceSet[@Name=\"NodeInfo\"]/Resource/NodeInfo/@Start", new JDFDate().getDateTimeISO());
		final String xml2 = root.getOwnerDocument_KElement().write2String(2);
		final JDFParser p2 = getXJDFSchemaParser(2, 0);
		final JDFDoc xParsed2 = p2.parseString(xml2);
		assertTrue(xParsed2.isSchemaValid());
	}

	/**
	 *
	 */
	@Test
	public void testDurationValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"NodeInfo\"]/Resource/NodeInfo/@TotalDuration", "12345");
		root.setAttribute("Types", "ConventionalPrinting");
		final String xml = root.getOwnerDocument_KElement().write2String(2);
		final JDFParser p = getXJDFSchemaParser(2, 0);
		final JDFDoc xParsed = p.parseString(xml);
		assertFalse(xParsed.isSchemaValid());
		root.setXPathAttribute("ResourceSet[@Name=\"NodeInfo\"]/Resource/NodeInfo/@TotalDuration", new JDFDuration(999).getDurationISO());
		final String xml2 = root.getOwnerDocument_KElement().write2String(2);
		final JDFParser p2 = getXJDFSchemaParser(2, 0);
		final JDFDoc xParsed2 = p2.parseString(xml2);
		assertTrue(xParsed2.isSchemaValid());
	}

	/**
	 *
	 */
	@Test
	public void testEmptyAuditPool()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"ConventionalPrintingParams\"]/Resource/ConventionalPrintingParams/@WorkStyle", "WorkAndTurn");
		root.setXPathAttribute("ResourceSet[@Name=\"ConventionalPrintingParams\"]/@Usage", "Input");
		root.setAttribute("Types", "ConventionalPrinting");
		root.getElement(ElementName.AUDITPOOL).removeChildren(null, null, null);
		writeTest(root, "../NoAudit.xjdf", false, null);
	}

	/**
	 *
	 */
	@Test
	public void testAuditExtensions()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"ConventionalPrintingParams\"]/Resource/ConventionalPrintingParams/@WorkStyle", "WorkAndTurn");
		root.setXPathAttribute("ResourceSet[@Name=\"ConventionalPrintingParams\"]/@Usage", "Input");
		root.setAttribute("Types", "ConventionalPrinting");
		final XJDFHelper xjdfHelper = new XJDFHelper(root);
		xjdfHelper.setVersion(EnumVersion.Version_2_1);
		final AuditPoolHelper aph = xjdfHelper.getAuditPool();
		final AuditHelper ah = aph.appendAudit("AuditCreated");
		ah.getRoot().appendElement("foo:bar", "www.foo.com");
		xjdfHelper.cleanUp();
		writeTest(xjdfHelper, "../audit.ext.xjdf");

	}

	/**
	 *
	 */
	@Test
	public void testMessageExtensions()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.KnownMessages);
		xjmfHelper.setVersion(EnumVersion.Version_2_1);
		mh.getRoot().appendElement("foo:bar", "www.foo.com");
		xjmfHelper.cleanUp();
		writeTest(xjmfHelper, "../jmf.ext.xjmf");

	}

}
