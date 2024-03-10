/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfgoldenticket;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoLayoutIntent.EnumSides;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.intent.JDFFoldingIntent;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date Jun 22, 2014
 */
public class XJDFProductGoldenTicketTest extends JDFTestCaseBase
{

	/**
	 *
	 *
	 */
	@Test
	void testSimple()
	{
		final XJDFBaseGoldenTicket bt = new XJDFBaseGoldenTicket(1, EnumVersion.Version_2_0);
		bt.getXJDFHelper().writeToFile(sm_dirTestDataTemp + "xjdf/GTSimple.xjdf");
	}

	/**
	 *
	 *
	 */
	@Test
	void testBrochureSimple()
	{
		final XJDFBaseGoldenTicket bt = new XJDFProductGoldenTicket(1, EnumVersion.Version_2_0);
		final XJDFHelper xjdfHelper = bt.getXJDFHelper();
		final ProductHelper ph = xjdfHelper.getCreateRootProduct(0);
		ph.setProductType("Brochure");
		ph.setAmount(10);
		final ProductHelper phc = xjdfHelper.getCreateProduct("IDCover", null);
		phc.setProductType("Cover");
		phc.setAmount(1);
		final ProductHelper phb = xjdfHelper.getCreateProduct("IDBody", null);
		phb.setAmount(1);
		final IntentHelper ih = ph.getCreateIntent(ElementName.BINDINGINTENT);
		ih.getCreateResource().setAttribute(ElementName.BINDINGTYPE, "SaddleStitch");
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDCover", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBody", null, null, false);
		xjdfHelper.writeToFile(sm_dirTestDataTemp + "xjdf/GTBrochure.xjdf");
	}

	/**
	 *
	 *
	 */
	@Test
	void testComplexVTSimple()
	{
		final XJDFBaseGoldenTicket bt = new XJDFProductGoldenTicket(1, EnumVersion.Version_2_0);
		final XJDFHelper xjdfHelper = bt.getXJDFHelper();

		final ProductHelper ph = xjdfHelper.getCreateRootProduct(0);
		ph.setAmount(10);

		final ProductHelper phc = xjdfHelper.getCreateProduct("IDCover", null);
		createCover(phc);

		final ProductHelper pht = xjdfHelper.getCreateProduct("IDText", null);
		createText(pht);

		final ProductHelper phCol = xjdfHelper.getCreateProduct("IDColor", null);
		createColorText(phCol);

		final ProductHelper phMap = xjdfHelper.getCreateProduct("IDMap", null);
		createMap(phMap);

		final ProductHelper phCol2 = xjdfHelper.getCreateProduct("IDColor2", null);
		createColorText(phCol2);

		final ProductHelper pht2 = xjdfHelper.getCreateProduct("IDText2", null);
		createText(pht2);

		createBook(ph);

		final ProductHelper phPos = xjdfHelper.getCreateRootProduct(1);
		createPoster(phPos);

		xjdfHelper.cleanUp();
		writeRoundTripX(xjdfHelper.getRoot(), "GTComplexVT", null);
		// writeRoundTripX(xjdfHelper.getRoot(), "GTComplexVT", EnumValidationLevel.NoWarnComplete);
	}

	void createBook(final ProductHelper ph)
	{
		final IntentHelper ih = ph.getCreateIntent(ElementName.BINDINGINTENT);
		ih.getCreateResource().setAttribute(ElementName.BINDINGTYPE, "HardCover");
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDCover", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDText", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDColor", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDMap", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDColor2", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDText2", null, null, false);

		final IntentHelper loi = ph.appendIntent(ElementName.LAYOUTINTENT);
		final JDFLayoutIntent theLoi = (JDFLayoutIntent) loi.getResource();
		theLoi.setAttribute(ElementName.FINISHEDDIMENSIONS, new JDFShape(5.5, 7.5, 1).scale(72).getString(1));

	}

	void createCover(final ProductHelper phc)
	{
		phc.setAmount(1);
		phc.setProductType("Cover");

		final IntentHelper loi = phc.appendIntent(ElementName.LAYOUTINTENT);
		final JDFLayoutIntent theLoi = (JDFLayoutIntent) loi.getResource();
		theLoi.setSides(EnumSides.OneSided);
		theLoi.setAttribute(AttributeName.SPREADTYPE, "Spread");
		theLoi.setAttribute(ElementName.DIMENSIONS, new JDFXYPair(12.5, 7.5).scale(72).getString(1));

		final IntentHelper mii = phc.appendIntent(ElementName.MEDIAINTENT);
		final JDFMediaIntent theMedia = (JDFMediaIntent) mii.getResource();
		theMedia.setAttribute(AttributeName.MEDIAQUALITY, "BookCloth");
		theMedia.setAttribute(AttributeName.WEIGHT, "400");
		theMedia.setAttribute(AttributeName.MEDIATYPE, "Paper");
	}

	void createText(final ProductHelper phc)
	{
		phc.setAmount(1);
		phc.setProductType("Body");

		final IntentHelper loi = phc.appendIntent(ElementName.LAYOUTINTENT);
		final JDFLayoutIntent theLoi = (JDFLayoutIntent) loi.getResource();
		theLoi.setSides(EnumSides.TwoSidedHeadToHead);
		theLoi.setAttribute(AttributeName.SPREADTYPE, "SinglePage");
		theLoi.setAttribute(ElementName.FINISHEDDIMENSIONS, new JDFShape(5, 7, 0).scale(72).getString(1));

		final IntentHelper mii = phc.appendIntent(ElementName.MEDIAINTENT);
		final JDFMediaIntent theMedia = (JDFMediaIntent) mii.getResource();
		theMedia.setAttribute(AttributeName.MEDIAQUALITY, "FSC120Coated");
		theMedia.setAttribute(AttributeName.WEIGHT, "120");
		theMedia.setAttribute(XJDFConstants.Coating, "Coated");
		theMedia.setAttribute(AttributeName.ISOPAPERSUBSTRATE, "PS3");
		theMedia.setAttribute(AttributeName.MEDIATYPE, "Paper");
	}

	void createColorText(final ProductHelper phc)
	{
		phc.setAmount(1);
		phc.setProductType("Body");

		final IntentHelper loi = phc.appendIntent(ElementName.LAYOUTINTENT);
		final JDFLayoutIntent theLoi = (JDFLayoutIntent) loi.getResource();
		theLoi.setSides(EnumSides.TwoSidedHeadToHead);
		theLoi.setAttribute(AttributeName.SPREADTYPE, "SinglePage");
		theLoi.setAttribute(ElementName.FINISHEDDIMENSIONS, new JDFShape(5, 7, 0).scale(72).getString(1));

		final IntentHelper mii = phc.appendIntent(ElementName.MEDIAINTENT);
		final JDFMediaIntent theMedia = (JDFMediaIntent) mii.getResource();
		theMedia.setAttribute(AttributeName.MEDIAQUALITY, "FSC150Coated");
		theMedia.setAttribute(AttributeName.WEIGHT, "150");
		theMedia.setAttribute(XJDFConstants.Coating, "Coated");
		theMedia.setAttribute(AttributeName.ISOPAPERSUBSTRATE, "PS2");
		theMedia.setAttribute(AttributeName.MEDIATYPE, "Paper");
	}

	void createPoster(final ProductHelper phc)
	{
		phc.setAmount(1);
		phc.setProductType("Poster");

		final IntentHelper loi = phc.appendIntent(ElementName.LAYOUTINTENT);
		final JDFLayoutIntent theLoi = (JDFLayoutIntent) loi.getResource();
		theLoi.setSides(EnumSides.OneSided);
		theLoi.setAttribute(AttributeName.SPREADTYPE, "SinglePage");
		theLoi.setAttribute(ElementName.FINISHEDDIMENSIONS, new JDFShape(13.5, 18, 0).scale(72).getString(1));

		final IntentHelper mii = phc.appendIntent(ElementName.MEDIAINTENT);
		final JDFMediaIntent theMedia = (JDFMediaIntent) mii.getResource();
		theMedia.setAttribute(AttributeName.MEDIAQUALITY, "FSC200Premium");
		theMedia.setAttribute(AttributeName.WEIGHT, "200");
		theMedia.setAttribute(XJDFConstants.Coating, "Coated");
		theMedia.setAttribute(AttributeName.ISOPAPERSUBSTRATE, "PS1");
		theMedia.setAttribute(AttributeName.MEDIATYPE, "Paper");
	}

	void createMap(final ProductHelper phc)
	{
		phc.setAmount(1);
		phc.setProductType("Map");

		final IntentHelper loi = phc.appendIntent(ElementName.LAYOUTINTENT);
		final JDFLayoutIntent theLoi = (JDFLayoutIntent) loi.getResource();
		theLoi.setSides(EnumSides.TwoSidedHeadToHead);
		theLoi.setAttribute(AttributeName.SPREADTYPE, "Spread");
		theLoi.setAttribute(ElementName.FINISHEDDIMENSIONS, new JDFShape(9, 7, 0).scale(72).getString(1));

		final IntentHelper mii = phc.appendIntent(ElementName.MEDIAINTENT);
		final JDFMediaIntent theMedia = (JDFMediaIntent) mii.getResource();
		theMedia.setAttribute(AttributeName.MEDIAQUALITY, "FSC150Coated");
		theMedia.setAttribute(AttributeName.WEIGHT, "150");
		theMedia.setAttribute(XJDFConstants.Coating, "Coated");
		theMedia.setAttribute(AttributeName.ISOPAPERSUBSTRATE, "PS2");
		theMedia.setAttribute(AttributeName.MEDIATYPE, "Paper");

		final IntentHelper fi = phc.appendIntent(ElementName.FOLDINGINTENT);
		final JDFFoldingIntent theFold = (JDFFoldingIntent) fi.getResource();
		theFold.setAttribute(AttributeName.FOLDCATALOG, "F6-7");
	}

	/**
	*
	*
	*/
	@Test
	void testNotebook()
	{
		final XJDFBaseGoldenTicket bt = new XJDFProductGoldenTicket(1, EnumVersion.Version_2_0);
		final XJDFHelper xjdfHelper = bt.getXJDFHelper();
		xjdfHelper.setTypes(EnumType.Product.getName());
		final ProductHelper ph = xjdfHelper.getCreateRootProduct(0);
		ph.setAmount(10);
		ph.setProductType("Notebook");
		final ProductHelper phc = xjdfHelper.getCreateProduct("ICover", null);
		phc.setAmount(1);
		phc.setProductType("FrontCover");
		final ProductHelper phb = xjdfHelper.getCreateProduct("IBody", null);
		phb.setAmount(50);
		phb.setProductType("BookBlock");
		final ProductHelper phcb = xjdfHelper.getCreateProduct("IBack", null);
		phcb.setProductType("BackCover");
		phcb.setAmount(1);
		final IntentHelper ih = ph.getCreateIntent(ElementName.BINDINGINTENT);
		ih.getCreateResource().setAttribute(ElementName.BINDINGTYPE, "EdgeGluing");
		ih.getCreateResource().setAttribute(ElementName.BINDINGSIDE, "Top");
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IBack", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IBody", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "ICover", null, null, false);
		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper.getRoot().getElement(XJDFConstants.ProductList), true);
		writeTest(xjdfHelper, "intents/Notebook.xjdf");
	}

	/**
	 *
	 *
	 */
	@Test
	void testAssembleEnvelope()
	{
		final XJDFBaseGoldenTicket bt = new XJDFProductGoldenTicket(1, EnumVersion.Version_2_0);
		final XJDFHelper xjdfHelper = bt.getXJDFHelper();
		xjdfHelper.setTypes(EnumType.Product.getName());
		final ProductHelper ph = xjdfHelper.getCreateRootProduct(0);
		ph.setAmount(10);
		ph.setProductType("FilledEnvelope");
		final ProductHelper phe = xjdfHelper.getCreateProduct("ID_Envelope", null);
		phe.setAmount(1);
		phe.setProductType("Envelope");
		phe.setAttribute(XJDFConstants.ExternalID, "MISID_Envelope");
		final ProductHelper phl = xjdfHelper.getCreateProduct("ID_Letter", null);
		phl.setAmount(1);
		phl.setProductType("Letter");
		final IntentHelper ih = ph.getCreateIntent(XJDFConstants.AssemblingIntent);
		ih.getCreateResource().setAttribute(XJDFConstants.Container, "ID_Envelope");
		ih.getCreateResource().setXPathAttribute("BlowIn/@ChildRef", "ID_Letter");

		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper.getRoot().getElement(XJDFConstants.ProductList), true);
		writeTest(xjdfHelper, "intents/AssembleEnvelope.xjdf");
	}

	/**
	 *
	 *
	 */
	@Test
	void testMultiVariable()
	{
		final XJDFBaseGoldenTicket bt = new XJDFProductGoldenTicket(1, EnumVersion.Version_2_0);
		final XJDFHelper xjdfHelper = bt.getXJDFHelper();
		final ProductHelper ph = xjdfHelper.getCreateRootProduct(0);
		ph.setAmount(10000);
		final IntentHelper ih = ph.getCreateIntent("VariableIntent");
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBrochure", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBook", null, null, false);
		ih.getResource().setAttribute(XJDFConstants.VariableType, "Area");
		final ProductHelper phh = xjdfHelper.getCreateProduct("IDBook", null);
		phh.setAmount(1000);
		phh.setProductType("Book");

		final ProductHelper phhc = xjdfHelper.getCreateProduct("IDBookCover", null);
		phhc.setProductType("Cover");
		phhc.setAmount(1);

		final ProductHelper phb = xjdfHelper.getCreateProduct("IDBody", null);
		phb.setAmount(1);

		final IntentHelper ihb = phh.getCreateIntent(ElementName.BINDINGINTENT);
		ihb.getCreateResource().setAttribute(ElementName.BINDINGTYPE, "HardCover");
		ihb.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBookCover", null, null, false);
		ihb.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBody", null, null, false);

		final ProductHelper phs = xjdfHelper.getCreateProduct("IDBrochure", null);
		phs.setAmount(9000);

		final ProductHelper phsc = xjdfHelper.getCreateProduct("IDBrochureCover", null);
		phsc.setProductType("Cover");
		phsc.setAmount(1);

		final IntentHelper ihs = phs.getCreateIntent(ElementName.BINDINGINTENT);
		ihs.getCreateResource().setAttribute(ElementName.BINDINGTYPE, "SaddleStitch");
		ihs.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBrochureCover", null, null, false);
		ihs.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBody", null, null, false);
		xjdfHelper.cleanUp();
		setSnippet(xjdfHelper.getRoot().getElement(XJDFConstants.ProductList), true);

		writeTest(xjdfHelper, "intents/VariableAmounts.xjdf");
	}

	/**
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}

}
