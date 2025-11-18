/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.auto.JDFAutoComChannel.EnumChannelType;
import org.cip4.jdflib.core.AttributeName;
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
import org.cip4.jdflib.core.JDFParserFactory;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
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
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPerson;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.MyPair;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.ThreadUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.net.ProxyUtil;
import org.cip4.jdflib.util.net.UrlCheck;
import org.cip4.jdflib.util.thread.RegularJanitor;
import org.cip4.jdflib.validate.JDFValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.w3c.dom.Comment;
import org.w3c.dom.Node;

/**
 * base class for JDFLib test case classes
 *
 * @author prosirai
 */
public abstract class JDFTestCaseBase
{

	private static final String VALID = "Valid";
	protected static Lock sequential = new ReentrantLock();

	/**
	 * @param e
	 * @param major
	 * @param minor
	 * @return
	 */
	protected boolean reparse(final KElement e, final int major, int minor)
	{
		final String written = e.toXML();
		assertNotNull(written);
		if (minor == -1)
		{
			minor = major == 1 ? defaultVersion.getMinorVersion() : getDefaultXJDFVersion().getMinorVersion();
		}
		final JDFParser p = getSchemaParser(major, minor);
		final JDFDoc xParsed = p.parseString(written);
		xParsed.getValidationResult();
		return xParsed.isSchemaValid();
	}

	protected static JDFParser getSchemaParser(final int major, final int minor)
	{
		if (major == 1)
		{
			return getSchemaParser(EnumVersion.getEnum(major, minor));
		}
		else if (major == 2)
		{
			return getXJDFSchemaParser(major, minor);
		}
		return null;
	}

	/**
	 * @return
	 */
	public static String getXJDFSchema()
	{
		return getXJDFSchema(2, getDefaultXJDFVersion().getMinorVersion());
	}

	/**
	 * @return
	 */
	public static String getXJDFSchema(final int major, final int minor)
	{
		final String file = StringUtil.replaceToken(sm_dirTestSchema, -1, File.separator, "Version_" + major + "_" + minor) + "xjdf.xsd";
		return UrlUtil.normalize(file);
	}

	/**
	 * @return
	 */
	protected static JDFParser getXJDFSchemaParser()
	{
		return getXJDFSchemaParser(getDefaultXJDFVersion());
	}

	protected static JDFParser getXJDFSchemaParser(final EnumVersion xJDFVersion)
	{
		return getXJDFSchemaParser(xJDFVersion.getMajorVersion(), xJDFVersion.getMinorVersion());
	}

	protected static EnumVersion getDefaultXJDFVersion()
	{
		return defaultVersion.getXJDFVersion();
	}

	/**
	 * @return
	 */
	protected static JDFParser getXJDFSchemaParser(final int major, final int minor)
	{
		final JDFParser parser = JDFParserFactory.getFactory().get();
		parser.setSchemaLocation(JDFElement.getSchemaURL(major, minor), getXJDFSchema(major, minor));
		return parser;
	}

	/**
	 *
	 */
	public JDFTestCaseBase()
	{
		super();
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
	static protected final String sm_dirTestDataTemp = sm_dirTestData + "temp" + File.separator;
	static protected final EnumVersion defaultVersion = EnumVersion.Version_1_9;
	static protected final String sm_dirTestSchemaBase = sm_dirTestData + "schema" + File.separator + "Version_";
	static protected final String sm_dirTestSchema = sm_dirTestSchemaBase + "1_9" + File.separator;

	private static String getTestDataDir()
	{
		String path = null;
		final URL resource = JDFTestCaseBase.class.getResource("/data");
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
	 * check a node against the schema
	 *
	 * @param root
	 * @param level
	 */
	protected void checkSchema(final JDFElement root, final EnumValidationLevel level)
	{
		final String string = root.getOwnerDocument_JDFElement().write2String(2);
		final JDFParser jdfParser = getSchemaParser();
		final JDFDoc doc = jdfParser.parseString(string);
		JDFParserFactory.getFactory().push(jdfParser);
		assertEquals(doc.getValidationResult().getRoot().getAttribute("ValidationResult"), VALID);
		assertTrue(((JDFElement) doc.getRoot()).isValid(level));
	}

	/**
	 * create a doc with exposedmedia for tests
	 *
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

		final JDFLayout lo = (JDFLayout) n.appendMatchingResource("Layout", JDFNode.EnumProcessUsage.AnyInput, null);
		lo.setPartUsage(EnumPartUsage.Explicit);
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
	protected Log log = LogFactory.getLog(getClass());

	private static boolean netWorkChecked = false;
	private static boolean bTestNetwork;

	// //////////////////////////////////////////////////////////////////////////
	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@BeforeEach
	public void setUp() throws Exception
	{
		JDFElement.setDefaultJDFVersion(EnumVersion.Version_1_8);
		BaseXJDFHelper.setDefaultVersion(EnumVersion.Version_2_2);
		senderID = "TestSender";
		JDFJMF.setTheSenderID(senderID);
		KElement.uniqueID(1);
		JDFIntegerRange.setDefaultDef(0);
		agentName = JDFAudit.getStaticAgentName();
		agentVersion = JDFAudit.getStaticAgentVersion();
		author = JDFAudit.getStaticAuthor();
		mem = getCurrentMem();
	}

	/**
	 * get the currently used memory
	 *
	 * @return the used memory
	 */
	protected long getCurrentMem()
	{
		System.gc();
		ThreadUtil.sleep(3);

		final Runtime rt = Runtime.getRuntime();
		return rt.totalMemory() - rt.freeMemory();
	}

	/**
	 * general cleanup after each test
	 *
	 * @see JDFTestCaseBase#setUp()
	 */
	@AfterEach
	public void tearDown() throws Exception
	{
		KElement.setLongID(true);
		JDFElement.setDefaultJDFVersion(defaultVersion);
		KElement.setWantXsiType(true);
		JDFAudit.setStaticAgentName(agentName);
		JDFAudit.setStaticAgentVersion(agentVersion);
		JDFAudit.setStaticAuthor(author);
		JDFNodeInfo.setDefaultWorkStepID(false);
		DocumentJDFImpl.setStaticStrictNSCheck(true);
		JDFResource.setUnpartitiondImplicit(false);
		RegularJanitor.feierabend();
	}

	/**
	 * @param h
	 * @param startFirst
	 */
	protected void setSnippet(final BaseXJDFHelper h, final boolean startFirst)
	{
		if (h != null)
		{
			setSnippet(h.getRoot(), startFirst);
		}
	}

	/**
	 * @param e
	 * @param startFirst if true include the enclosing element, if false exclude it
	 */
	protected void setSnippet(final KElement e, final boolean startFirst)
	{
		if (e != null && e.getParentNode() != null)
		{
			final Node parent = e.getParentNode();
			final String start = " START SNIPPET ";
			final String end = " END SNIPPET ";
			Comment newChild = e.getOwnerDocument().createComment(startFirst ? start : end);
			parent.insertBefore(newChild, e);
			newChild = e.getOwnerDocument().createComment(startFirst ? end : start);
			parent.insertBefore(newChild, e.getNextSibling());
		}
	}

	/**
	 * @param h
	 */
	protected void cleanSnippets(final XJDFHelper h)
	{
		if (h == null || h.getRoot() == null)
		{
			return;
		}
		h.cleanUp();
		setSnippet(h, true);
		setSnippet(h.getAuditPool(), false);
		setSnippet(h.getSet(ElementName.NODEINFO, 0), false);
	}

	/**
	 * @param d
	 * @param filename
	 */
	protected void writeTest(final JDFDoc d, final String filename)
	{
		writeTest(d.getRoot(), filename, true, null);
	}

	protected void writeTest(final BaseXJDFHelper h, final String filename, final boolean parseSchema, final String snippetPath)
	{
		writeTest(h.getRoot(), filename, parseSchema, snippetPath);
	}

	/**
	 * @param d
	 * @param filename
	 */
	protected void writeTest(final BaseXJDFHelper d, final String filename)
	{
		writeTest(d.getRoot(), filename, true, null);
	}

	/**
	 * write an element to the standard test directory sm_dirTestDataTemp
	 *
	 * @param e
	 * @param filename
	 * @param parseSchema
	 * @param snippetPath TODO
	 */
	protected XMLDoc writeTest(final KElement e, final String filename, final boolean parseSchema, final String snippetPath)
	{
		return writeTestSnippets(e, filename, parseSchema, StringArray.getVString(snippetPath, snippetPath));
	}

	/**
	 * write an element to the standard test directory sm_dirTestDataTemp
	 *
	 * @param e
	 * @param filename
	 * @param parseSchema
	 * @param snippetPath TODO
	 */
	protected XMLDoc writeTestSnippets(final KElement e, String filename, final boolean parseSchema, final List<String> snippetPaths)
	{
		String ext = UrlUtil.extension(filename);
		int minor = 1;
		new File(sm_dirTestDataTemp + "xjdfexamples").mkdirs();
		new File(sm_dirTestDataTemp + "jdfexamples").mkdirs();
		if (ext.startsWith("x"))
		{
			if (e.getParentNode_KElement() == null)
			{
				e.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "xjdfexamples/" + filename, 2, false);
			}
			else
			{
				e.write2File(sm_dirTestDataTemp + "xjdfexamples/" + filename);
			}
			minor = getMinor(e);
		}
		else
		{
			if (snippetPaths != null)
			{
				for (final String snippetPath : snippetPaths)
				{
					final KElement snippet0 = e.getXPathElement(snippetPath);
					setSnippet(snippet0, true);
				}
			}
			if (e.getParentNode_KElement() == null)
			{
				e.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp + "jdfexamples/" + filename, 2, false);
			}
			else
			{
				e.write2File(sm_dirTestDataTemp + "jdfexamples/" + filename);
			}
			assertTrue(((JDFElement) e).isValid(EnumValidationLevel.NoWarnIncomplete));

			if (parseSchema)
			{
				ext = "x" + ext;
				final KElement x = convertToXJDF(e);
				if (ContainerUtil.isEmpty(snippetPaths))
				{
					cleanSnippets(XJDFHelper.getHelper(x));
				}
				else
				{
					for (final String snippetPath : snippetPaths)
					{
						final KElement snippet = x.getXPathElement(snippetPath);
						setSnippet(snippet, true);
					}
				}
				filename = UrlUtil.newExtension(filename, ext);
				final String xjdfFile = sm_dirTestDataTemp + "xjdfexamples/" + filename;
				x.getOwnerDocument_KElement().write2File(xjdfFile, 2, false);
				minor = getMinor(x);
			}
		}
		if (parseSchema)
		{
			final String xjdfFile = sm_dirTestDataTemp + "xjdfexamples/" + filename;
			final JDFParser p = getXJDFSchemaParser(2, minor);
			final JDFDoc xParsed = p.parseFile(xjdfFile);
			if (!xParsed.isSchemaValid())
			{
				final XMLDoc dVal = xParsed.getValidationResult();
				dVal.write2File(UrlUtil.newExtension(xjdfFile, "val.xml"), 2, false);
			}
			assertTrue(xParsed.isSchemaValid(), VALID);
			return xParsed;

		}
		return null;
	}

	int getMinor(final KElement e)
	{
		final String s = e.getInheritedAttribute(AttributeName.VERSION, null, "2.1");
		return StringUtil.parseInt(StringUtil.token(s, 1, "."), 0);
	}

	protected KElement convertToXJDF(final KElement e)
	{
		final JDFToXJDF conv = new JDFToXJDF();
		conv.setTrackAudits(false);

		final String sv = e.getInheritedAttribute(AttributeName.VERSION, null, null);
		final EnumVersion v = EnumVersion.getEnum(sv);
		if (v != null)
		{
			conv.setNewVersion(v.getXJDFVersion());
		}
		final KElement x = conv.convert(e);
		return x;
	}

	protected MyPair<BaseXJDFHelper, JDFElement> writeRoundTrip(final JDFElement root, final String fileBase)
	{
		return writeRoundTrip(root, fileBase, getDefaultXJDFVersion());
	}

	protected MyPair<BaseXJDFHelper, JDFElement> writeRoundTrip(final JDFElement root, final String fileBase, final EnumVersion version)
	{
		return writeRoundTrip(root, fileBase, version, EnumValidationLevel.Complete);
	}

	/**
	 * write convert and unconvert
	 *
	 * @param root     the jdf node or jmf root
	 * @param fileBase the filename without extension
	 * @param version  21
	 */
	protected MyPair<BaseXJDFHelper, JDFElement> writeRoundTrip(final JDFElement root, final String fileBase, final EnumVersion version,
			final EnumValidationLevel level)
	{
		final String tmpJDF = fileBase + ".jdf";
		final String tmpJDFPath = sm_dirTestDataTemp + tmpJDF;
		root.write2File(tmpJDFPath);
		boolean valid = root.isValid(level);
		if (!valid)
		{
			printValid(root.getOwnerDocument_JDFElement());
		}
		assertTrue(valid, tmpJDF);
		final JDFParser jdfparser = getSchemaParser(version);
		final JDFDoc docJDF = jdfparser.parseFile(tmpJDFPath);
		final XMLDoc dVal0 = docJDF.getValidationResult();
		final String valResult0 = dVal0.getRoot().getAttribute("ValidationResult");
		if (!VALID.equals(valResult0))
		{
			dVal0.write2File(sm_dirTestDataTemp + fileBase + ".jdf.val.xml", 2, false);
		}
		assertEquals(VALID, valResult0);

		final XJDF20 xjdfConv = new XJDF20();
		xjdfConv.setNewVersion(version);
		final KElement xjdfRoot = xjdfConv.convert(root);
		final String tmpXJDF = sm_dirTestDataTemp + fileBase + ".xjdf";
		xjdfRoot.getOwnerDocument_KElement().write2File(tmpXJDF, 2, false);

		JDFParserFactory.getFactory().push(jdfparser);
		final JDFParser p = getXJDFSchemaParser(2, getMinor(xjdfRoot));
		final JDFDoc docXJDF = p.parseFile(tmpXJDF);
		final XMLDoc dVal = docXJDF.getValidationResult();
		final String valResult = dVal.getRoot().getAttribute("ValidationResult");
		if (!VALID.equals(valResult))
		{
			dVal.write2File(sm_dirTestDataTemp + fileBase + ".val.xml", 2, false);
		}
		assertEquals(VALID, valResult);
		log.info("Successfully converted " + tmpXJDF);
		final XJDFToJDFConverter jdfConverter = new XJDFToJDFConverter(null);
		final JDFDoc converted = jdfConverter.convert(xjdfRoot);
		converted.write2File(sm_dirTestDataTemp + fileBase + ".xjdf.jdf", 2, false);
		JDFElement jxRoot = converted.getJDFRoot();
		if (jxRoot == null)
		{
			jxRoot = converted.getJMFRoot();
		}
		valid = jxRoot.isValid(level);
		if (!valid)
		{
			printValid(converted);
		}
		JDFParserFactory.getFactory().push(p);
		assertTrue(valid, fileBase + ".xjdf.jdf");
		return new MyPair<>(BaseXJDFHelper.getBaseHelper(xjdfRoot), jxRoot);
	}

	static protected XMLDoc printValid(final JDFDoc converted)
	{
		final JDFValidator jdfValidator = new JDFValidator();
		jdfValidator.level = EnumValidationLevel.Complete;
		return jdfValidator.processSingleDocument(converted);
	}

	/**
	 * @param h        convenience method for XJDFHelper
	 * @param fileBase the filename without extension
	 * @param level    the level to validate the returned JDF
	 * @return
	 */
	protected JDFElement writeRoundTripX(final BaseXJDFHelper h, final String fileBase, final EnumValidationLevel level)
	{
		return writeRoundTripX(h, fileBase, level, true);
	}

	/**
	 * @param h        convenience method for XJDFHelper
	 * @param fileBase the filename without extension
	 * @param level    the level to validate the returned JDF
	 * @return
	 */
	protected JDFElement writeRoundTripX(final BaseXJDFHelper h, final String fileBase, final EnumValidationLevel level, final boolean cleanup)
	{
		if (cleanup)
		{
			h.cleanUp();
		}
		return writeRoundTripX(h.getRoot(), fileBase, level);
	}

	/**
	 * write convert and unconvert
	 *
	 * @param xjdfRoot the xjdf node or xjmf root
	 * @param fileBase the filename without extension
	 * @param level    the level to validate the returned JDF
	 * @return
	 */
	protected JDFElement writeRoundTripX(final KElement xjdfRoot, final String fileBase, final EnumValidationLevel level)
	{
		final String tmpXJDF = sm_dirTestDataTemp + fileBase + ".xjdf";
		xjdfRoot.getOwnerDocument_KElement().write2File(tmpXJDF, 2, false);

		final JDFParser p = getXJDFSchemaParser(2, getMinor(xjdfRoot));
		JDFDoc docXJDF = p.parseFile(tmpXJDF);
		XMLDoc dVal = docXJDF.getValidationResult();
		String valResult = dVal.getRoot().getAttribute("ValidationResult");
		if (!VALID.equals(valResult))
		{
			dVal.write2File(sm_dirTestDataTemp + fileBase + ".val.xml", 2, false);
		}
		assertEquals(VALID, valResult);
		JDFElement jxRoot = null;
		if (level != null)
		{
			final XJDFToJDFConverter jdfConverter = new XJDFToJDFConverter(null);
			final JDFDoc converted = jdfConverter.convert(xjdfRoot);
			final String fileXJ = sm_dirTestDataTemp + fileBase + ".xjdf.jdf";
			converted.write2File(fileXJ, 2, false);
			jxRoot = converted.getJDFRoot();
			if (jxRoot == null)
			{
				jxRoot = converted.getJMFRoot();
			}
			final boolean valid = jxRoot.isValid(level);
			if (!valid)
			{
				printValid(converted);
			}
			assertTrue(valid, fileBase + ".xjdf.jdf");
			final String sv = jxRoot.getInheritedAttribute(AttributeName.VERSION, null, null);
			final EnumVersion v = EnumVersion.getEnum(sv);

			final JDFDoc schemaParsed = getSchemaParser(v).parseFile(fileXJ);
			dVal = schemaParsed.getValidationResult();
			valResult = dVal.getRoot().getAttribute("ValidationResult");
			assertEquals(valResult, VALID);

			final XJDF20 xjdfConv = new XJDF20();
			final KElement root = xjdfConv.convert(jxRoot);

			final String roundXjdf = sm_dirTestDataTemp + fileBase + ".jdf.xjdf";
			root.write2File(roundXjdf);
			final JDFParser pRound = getXJDFSchemaParser(2, getMinor(root));
			docXJDF = pRound.parseFile(roundXjdf);
			dVal = docXJDF.getValidationResult();
			valResult = dVal.getRoot().getAttribute("ValidationResult");
			if (!VALID.equals(valResult))
			{
				dVal.write2File(sm_dirTestDataTemp + fileBase + ".val.jdf.xml", 2, false);
			}
			assertEquals(VALID, valResult);
			JDFParserFactory.getFactory().push(pRound);
		}
		JDFParserFactory.getFactory().push(p);
		return jxRoot;
	}

	/**
	 * create a standard customerInfo
	 *
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
		return "[" + StringUtil.token(this.getClass().getName(), -1, ".") + " Version:  " + defaultVersion + " " + new File(sm_dirTestData).getAbsolutePath()
				+ " ]\n";
	}

	/**
	 * Setter for bTestNetwork attribute.
	 *
	 * @param bTestNetwork the bTestNetwork to set
	 */
	void setTestNetwork(final boolean bTestNetwork)
	{
		JDFTestCaseBase.bTestNetwork = bTestNetwork;
		netWorkChecked = true;
	}

	/**
	 * Getter for bTestNetwork attribute.
	 *
	 * @return the bTestNetwork
	 */
	public boolean isTestNetwork()
	{
		return bTestNetwork;
	}

	/**
	 * @return
	 */
	protected JDFParser getSchemaParser()
	{
		return getSchemaParser(defaultVersion);
	}

	/**
	 * @return
	 */
	protected static JDFParser getSchemaParser(EnumVersion version)
	{
		if (version == null)
		{
			version = JDFElement.getDefaultJDFVersion();
		}
		int minor = version.isXJDF() ? 6 : 0;
		minor += version.getMinorVersion();
		final JDFParser parser = JDFParserFactory.getFactory().get();
		final File jdfxsd = new File(sm_dirTestSchemaBase + "1_" + minor + File.separator + "JDF.xsd");
		assertTrue(jdfxsd.canRead());
		parser.setJDFSchemaLocation(jdfxsd);
		return parser;
	}

}