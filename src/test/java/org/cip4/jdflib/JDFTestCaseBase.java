/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.auto.JDFAutoComChannel.EnumChannelType;
import org.cip4.jdflib.core.DocumentJDFImpl;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.extensions.BaseXJDFHelper;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPerson;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.logging.LogConfigurator;
import org.cip4.jdflib.util.net.ProxyUtil;
import org.cip4.jdflib.util.net.UrlCheck;
import org.cip4.jdflib.util.thread.RegularJanitor;
import org.junit.After;
import org.junit.Before;
import org.w3c.dom.Comment;
import org.w3c.dom.Node;

import junit.framework.TestCase;

/**
 * base class for JDFLib test case classes
 *
 * @author prosirai
 *
 */
public abstract class JDFTestCaseBase extends TestCase
{

	/**
	 *
	 * @return
	 */
	public static String getXJDFSchema()
	{
		final String file = StringUtil.replaceToken(sm_dirTestSchema, -1, File.separator, "Version_2_0") + "JDF20.xsd";
		return UrlUtil.normalize(file);
	}

	/**
	 *
	 * @return
	 */
	protected static JDFParser getXJDFSchemaParser()
	{
		JDFParser parser = new JDFParser();
		parser.setSchemaLocation(JDFElement.getSchemaURL(2, 0), getXJDFSchema());
		return parser;
	}

	/**
	 *
	 */
	public JDFTestCaseBase()
	{
		super();
		LogConfigurator.configureLog(null, null);
		if (!StringUtil.parseBoolean(System.getProperty("jdf.testnetwork"), true))
		{
			setTestNetwork(false);
		}
		ProxyUtil.setUseSystemDefault(true);
		if (!netWorkChecked)
		{
			setTestNetwork(new UrlCheck("http://www.example.com").pingRC(1000) == 200);
		}
	}

	static protected final String sm_dirTestData = getTestDataDir();
	static protected final EnumVersion defaultVersion = JDFElement.getDefaultJDFVersion();
	static protected final String sm_dirTestSchema = sm_dirTestData + "schema" + File.separator + "Version_1_5" + File.separator;
	static protected final String sm_dirTestDataTemp = sm_dirTestData + "temp" + File.separator;

	private static String getTestDataDir()
	{
		String path = null;
		URL resource = JDFTestCaseBase.class.getResource("/data");
		if (resource != null)
		{
			path = resource.getPath();
		}
		if (path == null || StringUtil.hasToken(path, "cmpINFRA_CommonParsers", "/", 0) || StringUtil.hasToken(path, "cmpINFRA_Common", "/", 0))
		{
			// legacy - pre maven file structure support
			path = "test" + File.separator + "data";
		}
		path = FilenameUtils.normalize(path) + File.separator;
		return path;
	}

	/**
	 *
	 * check a node against the schema
	 * @param root
	 * @param level
	 */
	protected void checkSchema(JDFElement root, EnumValidationLevel level)
	{
		String string = root.getOwnerDocument_JDFElement().write2String(2);
		JDFParser jdfParser = getSchemaParser();
		JDFDoc doc = jdfParser.parseString(string);
		assertEquals(doc.getValidationResult().getRoot().getAttribute("ValidationResult"), "Valid");
		assertTrue(((JDFElement) doc.getRoot()).isValid(level));
	}

	/**
	 *
	 * create a doc with exposedmedia for tests
	 * @return
	 */
	protected static JDFDoc creatXMDoc()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode n = doc.getJDFRoot();
		n.setJobPartID("P1");
		n.setJobID("J1");
		n.setType("ConventionalPrinting", true);
		n.appendElement("NS:Foobar", "www.foobar.com");

		n.appendMatchingResource("Layout", JDFNode.EnumProcessUsage.AnyInput, null);
		final JDFComponent comp = (JDFComponent) n.appendMatchingResource(ElementName.COMPONENT, JDFNode.EnumProcessUsage.AnyOutput, null);
		final JDFExposedMedia xm = (JDFExposedMedia) n.appendMatchingResource(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.Plate, null);
		final JDFNodeInfo ni = n.appendNodeInfo();
		final JDFMedia m = xm.appendMedia();
		m.appendElement("NS:FoobarMedia", "www.foobar.com");

		final VString vs = new VString();
		vs.add("SignatureName");
		vs.add("SheetName");
		vs.add("Side");

		final JDFAttributeMap mPart1 = new JDFAttributeMap("SignatureName", "Sig1");
		mPart1.put("SheetName", "S1");
		mPart1.put("Side", "Front");
		xm.getCreatePartition(mPart1, vs);
		ni.getCreatePartition(mPart1, vs);
		comp.getCreatePartition(mPart1, vs);

		mPart1.put("Side", "Back");
		xm.getCreatePartition(mPart1, vs);
		ni.getCreatePartition(mPart1, vs);
		comp.getCreatePartition(mPart1, vs);

		mPart1.put("SheetName", "S2");
		mPart1.put("Side", "Front");
		xm.getCreatePartition(mPart1, vs);
		ni.getCreatePartition(mPart1, vs);
		comp.getCreatePartition(mPart1, vs);

		mPart1.put("Side", "Back");
		xm.getCreatePartition(mPart1, vs);
		ni.getCreatePartition(mPart1, vs);
		comp.getCreatePartition(mPart1, vs);

		mPart1.put("SignatureName", "Sig2");
		mPart1.put("SheetName", "S1");
		mPart1.put("Side", "Front");
		xm.getCreatePartition(mPart1, vs);
		ni.getCreatePartition(mPart1, vs);
		comp.getCreatePartition(mPart1, vs);
		comp.appendElement("foo:bar", "www.foobar.com");

		mPart1.put("Side", "Back");
		xm.getCreatePartition(mPart1, vs);
		ni.getCreatePartition(mPart1, vs);
		comp.getCreatePartition(mPart1, vs);

		mPart1.put("SheetName", "S2");
		mPart1.put("Side", "Front");
		xm.getCreatePartition(mPart1, vs);
		ni.getCreatePartition(mPart1, vs);
		comp.getCreatePartition(mPart1, vs);

		mPart1.put("Side", "Back");
		xm.getCreatePartition(mPart1, vs);
		ni.getCreatePartition(mPart1, vs);
		comp.getCreatePartition(mPart1, vs);
		return doc;
	}

	protected String agentName;
	protected String agentVersion;
	protected String author;
	protected String senderID;
	protected long mem;
	protected Log log;
	private static boolean netWorkChecked = false;
	private static boolean bTestNetwork;

	// //////////////////////////////////////////////////////////////////////////
	/**
	 *
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	@Before
	protected void setUp() throws Exception
	{
		JDFElement.setDefaultJDFVersion(defaultVersion);
		senderID = "TestSender";
		JDFJMF.setTheSenderID(senderID);
		KElement.uniqueID(1);
		JDFIntegerRange.setDefaultDef(0);
		agentName = JDFAudit.getStaticAgentName();
		agentVersion = JDFAudit.getStaticAgentVersion();
		author = JDFAudit.getStaticAuthor();
		mem = getCurrentMem();
		log = LogFactory.getLog(getClass());
	}

	/**
	 * get the currently used memory
	 * @return the used memory
	 */
	protected long getCurrentMem()
	{
		System.gc();
		final Runtime rt = Runtime.getRuntime();
		return rt.totalMemory() - rt.freeMemory();
	}

	/**
	 *  general cleanup after each test
	 *
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	@After
	protected void tearDown() throws Exception
	{
		KElement.setLongID(true);
		JDFElement.setDefaultJDFVersion(defaultVersion);
		JDFAudit.setStaticAgentName(agentName);
		JDFAudit.setStaticAgentVersion(agentVersion);
		JDFAudit.setStaticAuthor(author);
		JDFNodeInfo.setDefaultWorkStepID(false);
		DocumentJDFImpl.setStaticStrictNSCheck(true);
		RegularJanitor.feierabend();
	}

	/**
	 *
	 * @param h
	 * @param startFirst
	 */
	protected void setSnippet(BaseXJDFHelper h, boolean startFirst)
	{
		if (h != null)
		{
			setSnippet(h.getRoot(), startFirst);
		}
	}

	/**
	 *
	 * @param e
	 * @param startFirst if true include the enclosing element, if false exclude it
	 */
	protected void setSnippet(KElement e, boolean startFirst)
	{
		if (e != null)
		{
			Node parent = e.getParentNode();
			String start = " START SNIPPET ";
			String end = " END SNIPPET ";
			Comment newChild = e.getOwnerDocument().createComment(startFirst ? start : end);
			parent.insertBefore(newChild, e);
			newChild = e.getOwnerDocument().createComment(startFirst ? end : start);
			parent.insertBefore(newChild, e.getNextSibling());
		}
	}

	protected void cleanSnippets(XJDFHelper h)
	{
		if (h == null || h.getRoot() == null)
			return;
		h.cleanUp();
		setSnippet(h, true);
		setSnippet(h.getAuditPool(), false);
		setSnippet(h.getSet(ElementName.NODEINFO, 0), false);
	}

	/**
	 *
	 * @param d
	 * @param filename
	 */
	protected void writeTest(JDFDoc d, String filename)
	{
		writeTest(d.getRoot(), filename, true);
	}

	/**
	 *
	 * @param d
	 * @param filename
	 */
	protected void writeTest(BaseXJDFHelper d, String filename)
	{
		writeTest(d.getRoot(), filename, true);
	}

	/**
	 *
	 * write an element to the standard test directory sm_dirTestDataTemp
	 * @param e
	 * @param filename
	 * @param convertX
	 */
	protected XMLDoc writeTest(KElement e, String filename, boolean convertX)
	{
		String ext = UrlUtil.extension(filename);
		if (ext.startsWith("x"))
		{
			if (e.getParentNode_KElement() == null)
				e.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "xjdfexamples/" + filename, 2, false);
			else
				e.write2File(sm_dirTestDataTemp + "xjdfexamples/" + filename);
		}
		else
		{
			if (e.getParentNode_KElement() == null)
				e.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "jdfexamples/" + filename, 2, false);
			else
				e.write2File(sm_dirTestDataTemp + "jdfexamples/" + filename);
			if (convertX)
			{
				ext = "x" + ext;
				KElement x = convertToXJDF(e);
				cleanSnippets(XJDFHelper.getHelper(x));
				filename = UrlUtil.newExtension(filename, ext);
				String xjdfFile = sm_dirTestDataTemp + "xjdfexamples/" + filename;
				x.getOwnerDocument_KElement().write2File(xjdfFile, 2, false);
			}
		}
		if (convertX)
		{
			String xjdfFile = sm_dirTestDataTemp + "xjdfexamples/" + filename;
			JDFParser p = getXJDFSchemaParser();
			JDFDoc xParsed = p.parseFile(xjdfFile);
			XMLDoc dVal = xParsed.getValidationResult();
			String valResult = dVal.getRoot().getAttribute("ValidationResult");
			if (!"Valid".equals(valResult))
			{
				dVal.write2File(UrlUtil.newExtension(xjdfFile, "val.xml"), 2, false);
			}
			assertEquals(valResult, "Valid");
			return xParsed;

		}
		return null;
	}

	protected KElement convertToXJDF(KElement e)
	{
		JDFToXJDF conv = new JDFToXJDF();
		conv.setTrackAudits(false);
		KElement x = conv.convert(e);
		return x;
	}

	/**
	 * write convert and unconvert
	 *
	 * @param root the jdf node or jmf root
	 * @param fileBase the filename without extension
	 */
	protected static void writeRoundTrip(final JDFElement root, String fileBase)
	{
		root.write2File(sm_dirTestDataTemp + fileBase + ".jdf");
		assertTrue(fileBase + ".jdf", root.isValid(EnumValidationLevel.Complete));

		XJDF20 xjdfConv = new XJDF20();
		KElement xjdfRoot = xjdfConv.convert(root);
		String tmpXJDF = sm_dirTestDataTemp + fileBase + ".xjdf";
		xjdfRoot.getOwnerDocument_KElement().write2File(tmpXJDF, 2, false);

		JDFParser p = getXJDFSchemaParser();
		JDFDoc docXJDF = p.parseFile(tmpXJDF);
		XMLDoc dVal = docXJDF.getValidationResult();
		String valResult = dVal.getRoot().getAttribute("ValidationResult");
		if (!"Valid".equals(valResult))
		{
			dVal.write2File(sm_dirTestDataTemp + fileBase + ".val.xml", 2, false);
		}
		assertEquals(valResult, "Valid");

		XJDFToJDFConverter jdfConverter = new XJDFToJDFConverter(null);
		JDFDoc converted = jdfConverter.convert(xjdfRoot);
		converted.write2File(sm_dirTestDataTemp + fileBase + ".xjdf.jdf", 2, false);
		JDFElement jxRoot = converted.getJDFRoot();
		if (jxRoot == null)
			jxRoot = converted.getJMFRoot();
		assertTrue(fileBase + ".xjdf.jdf", jxRoot.isValid(EnumValidationLevel.Complete));
	}

	/**
	 * create a standard customerInfo
	 * @param doc the doc to prepare
	 * @return the new customerInfo
	 */
	protected JDFCustomerInfo prepareCustomerInfo(final JDFDoc doc)
	{
		final JDFNode n = doc.getJDFRoot();
		final JDFCustomerInfo info = n.appendCustomerInfo();
		info.setCustomerID("MISCustomerID");
		final VString vct = new VString();
		vct.add("Customer");
		final JDFContact contact = info.appendContact();
		contact.setContactTypes(vct);
		final JDFPerson p = contact.appendPerson();
		p.setFamilyName("LastName");
		p.setFirstName("FirstName");
		p.appendComChannel().setPhoneNumber("+49 123 4567", null, EnumChannelType.Phone);
		p.appendComChannel().setPhoneNumber("+49 123 4568", null, EnumChannelType.Fax);
		p.appendComChannel().setEMailLocator("customer@company.com");
		info.setCustomerJobName("Customer Job Identifier or Name");
		return info;
	}

	/**
	 *
	 * @param doc
	 * @return
	 */
	protected JDFNodeInfo prepareNodeInfo(final JDFDoc doc)
	{
		final JDFNode n = doc.getJDFRoot();
		final JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFDate date = new JDFDate();
		ni.setFirstStart(date);
		date.addOffset(0, 0, 0, 5);
		ni.setLastEnd(date);
		ni.setDescriptiveName("must be done 5 days after start");
		return ni;
	}

	/**
	 * @see junit.framework.TestCase#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return "[" + StringUtil.token(this.getClass().getName(), -1, ".") + " Version:  " + defaultVersion + " " + new File(sm_dirTestData).getAbsolutePath() + " ]\n";
	}

	/**
	 * Setter for bTestNetwork attribute.
	 * @param bTestNetwork the bTestNetwork to set
	 */
	public void setTestNetwork(boolean bTestNetwork)
	{
		JDFTestCaseBase.bTestNetwork = bTestNetwork;
		netWorkChecked = true;
	}

	/**
	 * Getter for bTestNetwork attribute.
	 * @return the bTestNetwork
	 */
	public boolean isTestNetwork()
	{
		return bTestNetwork;
	}

	/**
	 *
	 * @return
	 */
	protected JDFParser getSchemaParser()
	{
		JDFParser parser = new JDFParser();
		final File jdfxsd = new File(sm_dirTestSchema + File.separator + "JDF.xsd");
		assertTrue(jdfxsd.canRead());
		parser.setJDFSchemaLocation(jdfxsd);
		return parser;
	}

}