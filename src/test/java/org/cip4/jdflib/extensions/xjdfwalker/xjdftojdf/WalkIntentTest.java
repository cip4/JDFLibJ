/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.intent.JDFFoldingIntent;
import org.cip4.jdflib.resource.intent.JDFHoleMakingIntent;
import org.cip4.jdflib.resource.intent.JDFInsertingIntent;
import org.cip4.jdflib.resource.intent.JDFProofingIntent;
import org.cip4.jdflib.span.JDFSpanProofType.EnumSpanProofType;
import org.junit.Test;

public class WalkIntentTest extends JDFTestCaseBase
{
	/**
	 * 
	 */
	@Test
	public void testProofingIntent()
	{
		XJDFHelper h = new XJDFHelper("j1", "p1", null);
		h.setXPathValue("ProductList/Product/Intent[@Name=\"ContentCheckIntent\"]/ContentCheckIntent/ProofItem/@ProofType", EnumSpanProofType.Page.getName());
		XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		JDFDoc dJDF = c.convert(h);
		JDFNode jdfRoot = dJDF.getJDFRoot();
		JDFProofingIntent pi = (JDFProofingIntent) jdfRoot.getResource(ElementName.PROOFINGINTENT, EnumUsage.Input, 0);
		assertEquals(pi.getProofItem(0).getProofType().guessActual(), "Page");
	}

	/**
	 * @throws DataFormatException 
	 * @throws JDFException 
	 * 
	 */
	@Test
	public void testInsertingIntent() throws JDFException, DataFormatException
	{
		XJDFHelper h = new XJDFHelper("j1", "p1", null);
		h.setXPathValue("ProductList/Product/Intent[@Name=\"AssemblingIntent\"]/AssemblingIntent/BlowIn/@FolioFrom", "1");
		h.setXPathValue("ProductList/Product/Intent[@Name=\"AssemblingIntent\"]/AssemblingIntent/BlowIn/@FolioTo", "4");

		XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		JDFDoc dJDF = c.convert(h);
		JDFNode jdfRoot = dJDF.getJDFRoot();
		JDFInsertingIntent ii = (JDFInsertingIntent) jdfRoot.getResource(ElementName.INSERTINGINTENT, EnumUsage.Input, 0);
		assertEquals(ii.getInsertList().getInsert(0).getFolio(), new JDFIntegerRangeList("1 ~ 4"));
	}

	/**
	 * 
	 */
	@Test
	public void testHoleMakingIntent()
	{
		XJDFHelper h = new XJDFHelper("j1", "p1", null);

		h.setXPathValue("ProductList/Product/Intent/HoleMakingIntent/HolePattern/@Extent", "44 55");
		h.setXPathValue("ProductList/Product/Intent/HoleMakingIntent/HolePattern/@Pattern", "R2i-US-b");

		XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		JDFDoc dJDF = c.convert(h);
		JDFNode jdfRoot = dJDF.getJDFRoot();
		JDFHoleMakingIntent hi = (JDFHoleMakingIntent) jdfRoot.getResource(ElementName.HOLEMAKINGINTENT, EnumUsage.Input, 0);
		assertEquals("R2i-US-b", hi.getHoleType().getActual());
	}

	/**
	 * 
	 */
	@Test
	public void testFoldingIntent()
	{
		XJDFHelper h = new XJDFHelper("j1", "p1", null);

		h.setXPathValue("ProductList/Product/Intent/FoldingIntent/@FoldCatalog", "F4-1");

		XJDFToJDFConverter c = new XJDFToJDFConverter(null);
		JDFDoc dJDF = c.convert(h);
		JDFNode jdfRoot = dJDF.getJDFRoot();
		JDFFoldingIntent fi = (JDFFoldingIntent) jdfRoot.getResource(ElementName.FOLDINGINTENT, EnumUsage.Input, 0);
		assertEquals("F4-1", fi.getFoldingCatalog().getActual());

	}

}
