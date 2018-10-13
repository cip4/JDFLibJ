/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.node;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.junit.Test;

public class LinkValidatorMapTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testGetLinkNames()
	{
		final VString linkInfo = LinkValidatorMap.getLinkValidatorMap().getLinkNames(EnumType.ConventionalPrinting, null);
		assertTrue(linkInfo.contains("ConventionalPrintingParams"));
		assertTrue(linkInfo.contains("Preview"));
		assertTrue(linkInfo.contains("Media"));
		assertTrue(linkInfo.contains("Component"));
	}

	/**
	 *
	 */
	@Test
	public void testGetLinkNamesStripping()
	{
		final VString linkInfo = LinkValidatorMap.getLinkValidatorMap().getLinkNames(EnumType.Stripping, null);
		assertTrue(linkInfo.contains("Device"));
		assertTrue(linkInfo.contains("BinderySignature"));
		assertTrue(linkInfo.contains("Assembly"));
	}

	/**
	 *
	 */
	@Test
	public void testGetLinkInfo()
	{
		final VString linkInfo = LinkValidatorMap.getLinkValidatorMap().getLinkNames(EnumType.ConventionalPrinting, null);
		final LinkInfoMap linkNames = LinkValidatorMap.getLinkValidatorMap().getLinkInfoMap(EnumType.ConventionalPrinting, null);
		assertEquals(linkInfo.size(), linkNames.size());
	}

	/**
	 *
	 */
	@Test
	public void testGetLinkInfoMap()
	{
		final HashMap<String, LinkInfo> linkInfo = LinkValidatorMap.getLinkValidatorMap().getLinkInfoMap(EnumType.ConventionalPrinting, null);
		final LinkInfo cp = linkInfo.get("ConventionalPrintingParams");
		assertTrue(cp.getVString().contains("i_"));
	}

	/**
	 *
	 */
	@Test
	public void testGetLinkInfoMapVerification()
	{
		final HashMap<String, LinkInfo> linkInfo = LinkValidatorMap.getLinkValidatorMap().getLinkInfoMap(EnumType.Verification, null);
		final LinkInfo cp = linkInfo.get(ElementName.FILESPEC);
		assertTrue(cp.getVString().contains("i?"));
	}

	/**
	 *
	 */
	@Test
	public void testGetLinkInfoMapVariableIntent()
	{
		final HashMap<String, LinkInfo> linkInfo = LinkValidatorMap.getLinkValidatorMap().getLinkInfoMap(EnumType.Product, null);
		final LinkInfo cp = linkInfo.get(ElementName.VARIABLEINTENT);
		assertTrue(cp.getVString().contains("i?"));
	}

	/**
	 *
	 */
	@Test
	public void testGetLinkInfoMapSheetOptimizing()
	{
		final HashMap<String, LinkInfo> linkInfo = LinkValidatorMap.getLinkValidatorMap().getLinkInfoMap(EnumType.SheetOptimizing, null);
		final LinkInfo cp = linkInfo.get(ElementName.ASSEMBLY);
		assertTrue(cp.getVString().contains("i*"));
	}

	/**
	 *
	 */
	@Test
	public void testGetLinkInfoMapPerformance()
	{
		final VString v = new VString(new String[] { JDFConstants.COLORSPACECONVERSION, JDFConstants.SCREENING, JDFConstants.INTERPRETING, JDFConstants.RENDERING, JDFConstants.IMPOSITION,
				JDFConstants.IMAGESETTING, JDFConstants.CONVENTIONALPRINTING, JDFConstants.FOLDING, JDFConstants.CUTTING });
		for (int i = 0; i < 1000; i++)
		{
			final HashMap<String, LinkInfo> linkInfo = LinkValidatorMap.getLinkValidatorMap().getLinkInfoMap(EnumType.Combined, v);
			final LinkInfo cp = linkInfo.get("ConventionalPrintingParams");
			assertTrue(cp.getVString().contains("i_"));
		}
	}
}
