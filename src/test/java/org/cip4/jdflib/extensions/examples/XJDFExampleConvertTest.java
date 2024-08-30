/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.examples;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.elementwalker.FixVersion;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
class XJDFExampleConvertTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	void testLayoutElementProduction()
	{
		final JDFNode n = JDFNode.parseFile(sm_dirTestData + "dielayoutproduction.jdf");
		final KElement x = convertToXJDF(n);
		final XJDFHelper h = XJDFHelper.getHelper(x);
		h.getSet(ElementName.NODEINFO, 0);
		h.getSet(ElementName.SHAPEDEF, 0).setUsage(null);
		h.cleanUp();
		setSnippet(h, true);
		setSnippet(h.getSet(ElementName.NODEINFO, 0), false);
		writeTest(x, "processes/dielayoutproduction.xjdf", true, null);
	}

	/**
	 *
	 */
	@Test
	void testUpdateExamples()
	{
		XMLDoc.setIndent(2);
		final int l = XMLDoc.getLineWidth();
		XMLDoc.setLineWidth(95);
		// " C:\\gitreps\\cip4\\XJDFSchema\\src\\main\\resources"

		// for (final File f : FileUtil.listFilesInTree(new File(sm_dirTestData + "resources"), (String) null))
		for (final File f : FileUtil.listFilesWithExtension(new File(sm_dirTestData + "xjdf"), "xjdf"))
		{
			if (!f.isDirectory())
			{
				final File b = updateExample(f, EnumVersion.Version_2_2, 2024);
				final JDFParser schemaParser = getXJDFSchemaParser(EnumVersion.Version_2_2);
				final JDFDoc d = schemaParser.parseFile(b);
				assertTrue(d.isSchemaValid());
			}
		}
		XMLDoc.setLineWidth(l);

	}

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}

	File updateExample(final File f, final EnumVersion version, final int i)
	{
		if (!f.isDirectory())
		{
			final String r1 = StringUtil.replaceString(f.getAbsolutePath(), "\\test\\data\\", "\\test\\data\\temp\\");
			final String r2 = StringUtil.replaceString(r1, "/test/data/", "/test/data/temp/");
			final File out = new File(r2);
			final JDFDoc d = JDFDoc.parseFile(f);
			if (d != null)
			{
				final JDFElement e = (JDFElement) d.getRoot();
				final FixVersion fv = new FixVersion(version);
				fv.setZappDeprecated(false);
				fv.setNewYear(new JDFDate().getYear());
				fv.setFixICSVersions(true);
				fv.convert(e);
				d.write2File(out);
				return out;
			}
		}
		return null;
	}
}
