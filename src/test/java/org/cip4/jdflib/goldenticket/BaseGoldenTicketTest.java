/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of
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
package org.cip4.jdflib.goldenticket;


import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.*;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.examples.ExampleTest;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public abstract class BaseGoldenTicketTest extends ExampleTest
{

	private static boolean defaultCheckSchema = true;

	/**
	 *
	 */
	public BaseGoldenTicketTest()
	{
		super();
	}

	/**
	 *
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		agentName = JDFAudit.getStaticAgentName();
		KElement.setLongID(false);
		JDFAudit.setStaticAgentName("JDF Base golden ticket generator");
		super.setUp();
	}

	/**
	 *
	 * @see JDFTestCaseBase#tearDown()
	 */
	@Override
	void tearDown() throws Exception
	{
		JDFAudit.setStaticAgentName(agentName);
		KElement.setLongID(true);
		super.tearDown();
	}

	/**
	 * create 3 files based on a gt
	 *
	 * @param goldenTicket the ticket to write
	 * @param templateName the file name root of the 3 files
	 */
	public static void write3GTFiles(final BaseGoldenTicket goldenTicket, final String templateName)
	{
		goldenTicket.write2File(JDFTestCaseBase.sm_dirTestDataTemp + "GoldenTicket_Manager_" + templateName + ".jdf", 2);
		Assertions.assertTrue(goldenTicket.getNode().isValid(EnumValidationLevel.Complete));

		goldenTicket.makeReadyAll();
		goldenTicket.write2File(JDFTestCaseBase.sm_dirTestDataTemp + "GoldenTicket_MakeReady_" + templateName + ".jdf", 2);
		Assertions.assertTrue(goldenTicket.getNode().isValid(EnumValidationLevel.Complete), JDFTestCaseBase.sm_dirTestDataTemp + "GoldenTicket_MakeReady_" + templateName + ".jdf");

		goldenTicket.executeAll(null);
		goldenTicket.write2File(JDFTestCaseBase.sm_dirTestDataTemp + "GoldenTicket_Worker_" + templateName + ".jdf", 2);
		Assertions.assertTrue(goldenTicket.getNode().isValid(EnumValidationLevel.Complete));
	}

	/**
	 * create 9 files - jdf xjdf and round trip based on a gt
	 *
	 * @param goldenTicket the ticket to write
	 * @param templateName the file name root of the 3 files
	 * @param checkSchema TODO
	 */
	public static void write9GTFiles(final BaseGoldenTicket goldenTicket, final String templateName, final Boolean checkSchema)
	{
		writeRoundTrip(goldenTicket, "GoldenTicket_Manager_", templateName, checkSchema);

		goldenTicket.bExpandGrayBox = false;
		goldenTicket.makeReadyAll();
		writeRoundTrip(goldenTicket, "GoldenTicket_MakeReady_", templateName, checkSchema);

		goldenTicket.executeAll(null);
		writeRoundTrip(goldenTicket, "GoldenTicket_Worker_", templateName, checkSchema);
	}

	/**
	 *
	 * @param goldenTicket
	 * @param gtType
	 * @param templateName
	 * @param checkSchema TODO
	 */
	protected static void writeRoundTrip(final BaseGoldenTicket goldenTicket, final String gtType, final String templateName, final Boolean checkSchema)
	{

		goldenTicket.write2File(sm_dirTestDataTemp + gtType + templateName + ".jdf", 2);
		Assertions.assertTrue(goldenTicket.getNode().isValid(EnumValidationLevel.Complete), gtType + templateName + ".jdf");

		final XJDF20 xjdfConv = new XJDF20();
		final JDFNode expandedNode = goldenTicket.getExpandedNode();
		xjdfConv.setWantProduct(JDFConstants.PRODUCT.equals(expandedNode.getType()));
		final KElement xjdfRoot = xjdfConv.convert(expandedNode);
		final String tmpXJDF = sm_dirTestDataTemp + gtType + templateName + ".xjdf";
		xjdfRoot.getOwnerDocument_KElement().write2File(tmpXJDF, 2, false);

		final JDFParser p = getXJDFSchemaParser();
		final JDFDoc docXJDF = p.parseFile(tmpXJDF);
		final XMLDoc dVal = docXJDF.getValidationResult();
		final String schemapath = sm_dirTestDataTemp + gtType + templateName + ".schema.xml";
		dVal.write2File(schemapath, 2, false);
		final boolean localCheckSchema = checkSchema == null ? defaultCheckSchema : checkSchema.booleanValue();
		if (localCheckSchema)
		{
			Assertions.assertEquals("Valid", dVal.getRoot().getAttribute("ValidationResult"), schemapath);
		}
		final XJDFToJDFConverter jdfConverter = new XJDFToJDFConverter(null);
		final JDFDoc converted = jdfConverter.convert(xjdfRoot);
		converted.write2File(sm_dirTestDataTemp + gtType + templateName + ".xjdf.jdf", 2, false);
		final boolean valid = converted.getJDFRoot().isValid(EnumValidationLevel.Complete);
		if (!valid)
		{
			printValid(converted);
		}
		Assertions.assertTrue(valid, gtType + templateName + ".xjdf.jdf");
	}

}