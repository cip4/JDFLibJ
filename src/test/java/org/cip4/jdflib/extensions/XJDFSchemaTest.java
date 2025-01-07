/*
 * The CIP4 Software License, Version 1.0
 *
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.validation.SchemaFactory;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoGeneralID.EnumDataType;
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
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class XJDFSchemaTest extends JDFTestCaseBase
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
			final String date = new JDFDate().getFormattedDateTime(JDFDate.DATEISO);
			final String commentText = " XJDF 2." + minor + " job submission schema updated on " + date + " ";
			final XMLDoc d0 = XMLDoc.parseFile(getXJDFSchema(2, minor));
			d0.getRoot().setXMLComment(commentText, false);
			d0.write2File(sm_dirTestDataTemp + "schema/xjdf.2_" + minor + "." + date + ".xsd", 2, false);
			d0.write2File(sm_dirTestDataTemp + "schema/xjdf.job.2_" + minor + "/xjdf.xsd", 2, false);

			final XMLDoc ds = XMLDoc.parseFile(getXJDFSchema(2, minor));
			ds.getRoot().setXMLComment(" strict " + commentText, false);
			final VElement vlax = ds.getRoot().getChildrenByTagName(null, null, new JDFAttributeMap("processContents", "lax"), false, false, 0);
			for (final KElement e : vlax)
				e.setAttribute("processContents", "strict");
			ds.write2File(sm_dirTestDataTemp + "schema/xjdf.strict.2_" + minor + "." + date + ".xsd", 2, false);

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
			root.setXMLComment(" XJDF 2." + minor + " changeorder schema updated on " + date + " ", false);
			d.write2File(sm_dirTestDataTemp + "schema/xjdf.changeorder.2_" + minor + "." + date + ".xsd", 2, false);
			d.write2File(sm_dirTestDataTemp + "schema/xjdf.changeorder.2_" + minor + "/xjdf.xsd", 2, false);
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

	void cleancontext(final int minor)
	{
		final String date = new JDFDate().getFormattedDateTime(JDFDate.DATEISO);
		final String commentText = " XJDF 2." + minor + " job submission schema updated on " + date + " ";
		final XMLDoc d0 = XMLDoc.parseFile(getXJDFSchema(2, minor));
		d0.getRoot().setXMLComment(commentText, false);
		d0.write2File(sm_dirTestDataTemp + "schema/xjdf.2_" + minor + "." + date + ".xsd", 2, false);
		d0.write2File(sm_dirTestDataTemp + "schema/xjdf.job.2_" + minor + "/xjdf.xsd", 2, false);

		final XMLDoc d = XMLDoc.parseFile(getXJDFSchema(2, minor));
		final KElement root = d.getRoot();
		final VElement elems = root.getChildrenByTagName("xs:element", null, null, false, true, 0);
		final VElement complex = root.getChildrenByTagName("xs:element", null, null, false, true, 0);
		final VElement rootelems = root.getChildElementVector("xs:element", null);
		final VElement complexelems = root.getChildElementVector("xs:complexType", null);
		for (final KElement e : rootelems)
		{

			if (!e.getBoolAttribute("abstract", null, false))
			{
				final String t = e.getAttribute("type");
				final String n = e.getAttribute("name");
				if (t != null && t.equals(n))
				{
					for (final KElement c : complexelems)
					{
						if (n.equals(c.getAttribute("name")))
						{
							c.removeAttribute("name");
							e.removeAttribute("type");
							e.moveElement(c, null);
						}
					}
				}
			}
		}
		root.setXMLComment(" XJDF 2." + minor + " changeorder schema updated on " + date + " ", false);
		d.write2File(sm_dirTestDataTemp + "schema/xjdf.cleanup.2_" + minor + "/xjdf.xsd", 2, false);
	}

	void cleansubst(final int minor)
	{
		final String date = new JDFDate().getFormattedDateTime(JDFDate.DATEISO);
		final String commentText = " XJDF 2." + minor + " job submission schema updated on " + date + " ";
		final XMLDoc d0 = XMLDoc.parseFile(getXJDFSchema(2, minor));
		d0.getRoot().setXMLComment(commentText, false);
		d0.write2File(sm_dirTestDataTemp + "schema/xjdf.2_" + minor + "." + date + ".xsd", 2, false);
		d0.write2File(sm_dirTestDataTemp + "schema/xjdf.sub.2_" + minor + "/xjdf.xsd", 2, false);

		final XMLDoc d = XMLDoc.parseFile(getXJDFSchema(2, minor));
		final KElement root = d.getRoot();
		final VElement elems = root.getChildrenByTagName("xs:element", null, null, false, true, 0);
		for (final KElement elem : elems)
			elem.removeAttribute("substitutionGroup");
		root.setXMLComment(" XJDF 2." + minor + " changeorder schema updated on " + date + " ", false);
		d.write2File(sm_dirTestDataTemp + "schema/xjdf.cleanup.2_" + minor + "/xjdf.xsd", 2, false);
	}

	/**
	 *
	 */
	@Test
	void testGetXJDFSchema()
	{
		assertNotNull(XMLDoc.parseFile(getXJDFSchema()));
	}

	/**
	 *
	 */
	@Test
	void testGetXJDFSchema21()
	{
		assertNotNull(XMLDoc.parseFile(getXJDFSchema(2, 1)));
	}

	/**
	 *
	 */
	@Test
	void testBaseValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		final JDFDoc doc = getXJDFSchemaParser().parseString(root.toXML());
		assertNotNull(doc.getValidationResult());
	}

	/**
	 *
	 */
	@Test
	void testSimpleValidate()
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
	void testVersion20()
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
	void testChange()
	{
		for (int i = 0; i < 3; i++)
		{
			// new ChangeOrderMaker().createChangeOrder(i);
			cleancontext(i);
			// cleansubst(i);
		}
	}

	/**
	 *
	 */
	@Test
	void testVersion21()
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
	@Disabled
	void testSamples()
	{
		final List<File> l = FileUtil.listFilesInTree(new File("/gitreps/schema/xjdf/src/main/resources/samples"), "*.xj*");
		for (final File f : l)
		{
			if (!"further".equalsIgnoreCase(f.getParentFile().getName()))
			{
				final JDFDoc doc = getXJDFSchemaParser(2, 1).parseFile(f);
				assertNotNull(doc, f.getName());
				assertEquals("Valid", doc.getValidationResult().getRoot().getAttribute("ValidationResult"), f.getAbsolutePath());
			}
		}
	}

	/**
	 *
	 */
	@Test
	void testProductPV()
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
	@Disabled
	void testLocalSchema() throws SAXNotRecognizedException, SAXNotSupportedException
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
		// TODO update linked xerces
		SchemaFactory.newInstance("http://www.w3.org/XML/XMLSchema/v1.1");
		assertNotNull(parser.parseString(root.toXML()));
	}

	/**
	 *
	 */
	@Test
	void testProductPVBind()
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
	void testProductChicagoBind()
	{
		final KElement root = new XJDFHelper(EnumVersion.Version_2_3, "j1").getRoot();
		final XJDFHelper xjdfHelper = new XJDFHelper(root);
		final ProductHelper createRootProduct = xjdfHelper.getCreateRootProduct(0);
		createRootProduct.setID("i1");
		final KElement createResource = createRootProduct.appendIntent(ElementName.BINDINGINTENT).getCreateResource();
		createResource.setAttribute(ElementName.BINDINGTYPE, "ScrewBinding");
		final KElement sbd = createResource.appendElement(XJDFConstants.LooseBinding).appendElement(XJDFConstants.ScrewBinding);
		sbd.setAttribute(AttributeName.MATERIAL, "ColorCoatedSteel");
		xjdfHelper.setTypes(JDFConstants.PRODUCT);
		xjdfHelper.cleanUp();
		writeTest(root, "../Chicago.xjdf", true, null);
	}

	/**
	 *
	 */
	@Test
	void testCMYKValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"Color\"]/Resource/Color/@CMYK", "1 0.2 0.3 0.4");
		root.setAttribute("Types", "ConventionalPrinting");
		assertTrue(reparse(root, 2, -1));
		root.setXPathAttribute("ResourceSet[@Name=\"Color\"]/Resource/Color/@CMYK", "1 0.2 0.3");
		assertFalse(reparse(root, 2, -1));
		root.setXPathAttribute("ResourceSet[@Name=\"Color\"]/Resource/Color/@CMYK", "1 0.2 0.3 0.4 0.5");
		assertFalse(reparse(root, 2, -1));
		root.setXPathAttribute("ResourceSet[@Name=\"Color\"]/Resource/Color/@CMYK", "1 0,2 0,3 0,4");
		assertFalse(reparse(root, 2, -1));
	}

	/**
	 *
	 */
	@Test
	void testDeviceValidate()
	{
		final XJDFHelper h = new XJDFHelper("j1", "p", null);
		h.addType(org.cip4.jdflib.node.JDFNode.EnumType.Product);
		final JDFDevice d = (JDFDevice) h.getCreateSet(ElementName.DEVICE, EnumUsage.Input).getCreateResource().getResource();
		d.setDeviceID("id");
		final KElement root = h.getRoot();
		assertTrue(reparse(root, 2, -1));

	}

	/**
	 *
	 */
	@Test
	void testGeneralID()
	{
		for (final Object o : EnumDataType.getEnumList())
		{
			final EnumDataType dt = (EnumDataType) o;
			final XJDFHelper h = new XJDFHelper("j1", "p", null);
			h.addType(org.cip4.jdflib.node.JDFNode.EnumType.Product);
			final JDFGeneralID gid = h.setGeneralID("foo", "bar");
			gid.setXJDFDataType(dt);
			final KElement root = h.getRoot();
			h.cleanUp();
			assertTrue(reparse(root, 2, 3));
		}

	}

	/**
	 *
	 */
	@Test
	void testCountryCodeValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setAttribute("Types", "ConventionalPrinting");
		root.setXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Contact/Address/@CountryCode", "DE");
		assertTrue(reparse(root, 2, -1));
		root.setXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Contact/Address/@CountryCode", "D2");
		assertFalse(reparse(root, 2, -1));
		root.setXPathAttribute("ResourceSet[@Name=\"Contact\"]/Resource/Contact/Address/@CountryCode", "987");
		assertFalse(reparse(root, 2, -1));
	}

	/**
	 *
	 */
	@Test
	void testFileSpecValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/@CheckSum", StringUtil.setHexBinaryBytes(new byte[] { 1, 2, 3 }, -1));
		root.setAttribute("Types", "ConventionalPrinting");
		assertTrue(reparse(root, 2, -1));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/@CheckSum", "123");
		assertFalse(reparse(root, 2, -1));
	}

	/**
	 *
	 */
	@Test
	void testFileSpecValidateLong()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/@FileSize", "123456789012345");
		root.setAttribute("Types", "ConventionalPrinting");
		assertTrue(reparse(root, 2, -1));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/@FileSize", "123456789012345.12");
		assertFalse(reparse(root, 2, -1));
	}

	/**
	 *
	 */
	@Test
	void testNetworkHeader()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/@CheckSum", StringUtil.setHexBinaryBytes(new byte[] { 1, 2, 3 }, -1));
		root.setAttribute("Types", "ConventionalPrinting");
		assertTrue(reparse(root, 2, -1));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/NetworkHeader/@Name", "HeaderName");
		assertFalse(reparse(root, 2, -1));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/NetworkHeader/@Value", "HeaderName");
		assertTrue(reparse(root, 2, -1));
		assertFalse(reparse(root, 2, 0));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/NetworkHeader[2]/@Name", "HeaderName");
		assertFalse(reparse(root, 2, -1));
		root.setXPathAttribute("ResourceSet[@Name=\"RunList\"]/Resource/RunList/FileSpec/NetworkHeader[2]/@Value", "HeaderName");
		assertTrue(reparse(root, 2, -1));
	}

	/**
	 *
	 */
	@Test
	void testDateTimeValidate()
	{
		final KElement root = new XJDFHelper("j1", "p", null).getRoot();
		root.setXPathAttribute("ResourceSet[@Name=\"NodeInfo\"]/Resource/NodeInfo/@Start", "12345");
		root.setAttribute("Types", "ConventionalPrinting");
		final String xml = root.getOwnerDocument_KElement().write2String(2);
		final JDFParser p = getXJDFSchemaParser();
		final JDFDoc xParsed = p.parseString(xml);
		assertFalse(xParsed.isSchemaValid());
		root.setXPathAttribute("ResourceSet[@Name=\"NodeInfo\"]/Resource/NodeInfo/@Start", new JDFDate().getDateTimeISO());
		final String xml2 = root.getOwnerDocument_KElement().write2String(2);
		final JDFParser p2 = getXJDFSchemaParser();
		final JDFDoc xParsed2 = p2.parseString(xml2);
		assertTrue(xParsed2.isSchemaValid());
	}

	/**
	 *
	 */
	@Test
	void testDurationValidate()
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
		final JDFParser p2 = getXJDFSchemaParser();
		final JDFDoc xParsed2 = p2.parseString(xml2);
		assertTrue(xParsed2.isSchemaValid());
	}

	/**
	 *
	 */
	@Test
	void testEmptyAuditPool()
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
	void testAuditExtensions()
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
	void testMessageExtensions()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper mh = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.KnownMessages);
		xjmfHelper.setVersion(EnumVersion.Version_2_1);
		mh.getRoot().appendElement("foo:bar", "www.foo.com");
		xjmfHelper.cleanUp();
		writeTest(xjmfHelper, "../jmf.ext.xjmf");

	}

}
