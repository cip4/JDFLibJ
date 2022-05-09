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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoHole.EnumShape;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFHoleLine;
import org.cip4.jdflib.resource.intent.JDFFoldingIntent;
import org.cip4.jdflib.resource.intent.JDFHoleMakingIntent;
import org.cip4.jdflib.resource.intent.JDFInsertingIntent;
import org.cip4.jdflib.resource.intent.JDFProofingIntent;
import org.cip4.jdflib.resource.process.postpress.JDFHole;
import org.cip4.jdflib.resource.process.postpress.JDFHoleList;
import org.cip4.jdflib.span.JDFSpanMethod.EnumSpanMethod;
import org.cip4.jdflib.span.JDFSpanProofType.EnumSpanProofType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WalkIntentTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public void testProofingIntent()
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		JDFProofingIntent pi = (JDFProofingIntent) n.addResource(ElementName.PROOFINGINTENT, EnumUsage.Input);
		pi.appendProofItem().appendProofType().setPreferred(EnumSpanProofType.Page);
		JDFToXJDF conv = new JDFToXJDF();

		KElement xjdf = conv.makeNewJDF(n, null);
		Assertions.assertNull(xjdf.getXPathElement("ProductList/Product/Intent/ProofingIntent"));
		Assertions.assertEquals(xjdf.getXPathAttribute("ProductList/Product/Intent/ContentCheckIntent/ProofItem/@ProofType", null), EnumSpanProofType.Page.getName());
	}

	/**
	 *
	 */
	@Test
	public void testFoldingIntent()
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		JDFFoldingIntent fi = (JDFFoldingIntent) n.addResource(ElementName.FOLDINGINTENT, EnumUsage.Input);
		fi.appendFoldingCatalog().setActual("F4-1");
		JDFToXJDF conv = new JDFToXJDF();

		KElement xjdf = conv.makeNewJDF(n, null);
		Assertions.assertNull(xjdf.getXPathAttribute("ProductList/Product/Intent/FoldingIntent/@FoldingCatalog", null), "F4-1");
		Assertions.assertEquals("F4-1", xjdf.getXPathAttribute("ProductList/Product/Intent/FoldingIntent/@FoldCatalog", null));
	}

	/**
	 *
	 */
	@Test
	public void testHoleMakingIntent()
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		JDFHoleMakingIntent hi = (JDFHoleMakingIntent) n.addResource(ElementName.HOLEMAKINGINTENT, EnumUsage.Input);
		hi.setExtent(new JDFXYPair(21, 44));
		hi.appendHoleType().setActual("R2i-US-b");
		JDFToXJDF conv = new JDFToXJDF();

		KElement xjdf = conv.makeNewJDF(n, null);
		Assertions.assertNull(xjdf.getXPathAttribute("ProductList/Product/Intent/HoleMakingIntent/@Extent", null));
		Assertions.assertEquals("21 44", xjdf.getXPathAttribute("ProductList/Product/Intent/HoleMakingIntent/HolePattern/@Extent", null));
		Assertions.assertEquals("R2i-US-b", xjdf.getXPathAttribute("ProductList/Product/Intent/HoleMakingIntent/HolePattern/@Pattern", null));
	}

	/**
	 *
	 */
	@Test
	public void testHoleMakingIntentLine()
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		JDFHoleMakingIntent hi = (JDFHoleMakingIntent) n.addResource(ElementName.HOLEMAKINGINTENT, EnumUsage.Input);
		JDFHoleLine hl = hi.appendHoleList().appendHoleLine();
		hl.setPitch(3);
		JDFHole h = hl.appendHole();
		h.setExtent(new JDFXYPair(3, 5));
		h.setShape(EnumShape.Round);
		hi.appendHoleType().setActual("explicit");
		JDFToXJDF conv = new JDFToXJDF();

		KElement xjdf = conv.makeNewJDF(n, null);
		Assertions.assertNull(xjdf.getXPathAttribute("ProductList/Product/Intent/HoleMakingIntent/@Extent", null));
		Assertions.assertEquals("3 5", xjdf.getXPathAttribute("ProductList/Product/Intent/HoleMakingIntent/HolePattern/@Extent", null));
		Assertions.assertEquals("3", xjdf.getXPathAttribute("ProductList/Product/Intent/HoleMakingIntent/HolePattern/@Pitch", null));
		Assertions.assertNull(xjdf.getXPathAttribute("ProductList/Product/Intent/HoleMakingIntent/HolePattern/@Pattern", null));
	}

	/**
	 *
	 */
	@Test
	public void testHoleMakingIntentList()
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		JDFHoleMakingIntent hi = (JDFHoleMakingIntent) n.addResource(ElementName.HOLEMAKINGINTENT, EnumUsage.Input);
		JDFHoleList hl = hi.appendHoleList();
		JDFHole h = hl.appendHole();
		h.setExtent(new JDFXYPair(3, 5));
		h.setShape(EnumShape.Round);
		hi.appendHoleType().setActual("explicit");
		JDFToXJDF conv = new JDFToXJDF();

		KElement xjdf = conv.makeNewJDF(n, null);
		Assertions.assertNull(xjdf.getXPathAttribute("ProductList/Product/Intent/HoleMakingIntent/@Extent", null));
		Assertions.assertEquals("3 5", xjdf.getXPathAttribute("ProductList/Product/Intent/HoleMakingIntent/HolePattern/@Extent", null));
		Assertions.assertNull(xjdf.getXPathAttribute("ProductList/Product/Intent/HoleMakingIntent/HolePattern/@Pattern", null));
	}

	/**
	 * @throws DataFormatException
	 * @throws JDFException
	 *
	 */
	@Test
	public void testInsertingIntent() throws JDFException, DataFormatException
	{
		JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Product);
		JDFInsertingIntent ii = (JDFInsertingIntent) n.addResource(ElementName.INSERTINGINTENT, EnumUsage.Input);
		ii.appendMethod().setPreferred(EnumSpanMethod.BlowIn);
		ii.appendInsertList().appendInsert().setFolio(new JDFIntegerRangeList("1 ~ 4"));
		JDFToXJDF conv = new JDFToXJDF();

		KElement xjdf = conv.makeNewJDF(n, null);
		Assertions.assertNull(xjdf.getXPathAttribute("ProductList/Product/Intent/InsertingIntent", null));
		Assertions.assertEquals(xjdf.getXPathAttribute("ProductList/Product/Intent/AssemblingIntent/BlowIn/@FolioFrom", null), "1");
		Assertions.assertEquals(xjdf.getXPathAttribute("ProductList/Product/Intent/AssemblingIntent/BlowIn/@FolioTo", null), "4");
	}
}
