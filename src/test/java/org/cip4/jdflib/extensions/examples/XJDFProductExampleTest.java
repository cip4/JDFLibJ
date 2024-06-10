/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of
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

import org.cip4.jdflib.auto.JDFAutoLayoutIntent.EnumSpreadType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.extensions.ColorIntentHelper;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.ProductHelper.eProductType;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.span.JDFSpanBindingType.EnumSpanBindingType;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
class XJDFProductExampleTest extends ExampleTest
{
	/**
	 *
	 */
	@Test
	void testPoster()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("Simple_Poster", null, null);
		xjdfHelper.setTypes("Product");
		prepareNodeInfo(xjdfHelper);

		final ProductHelper ph = xjdfHelper.getCreateRootProduct(0);
		ph.setProductType(eProductType.Poster);
		ph.setAmount(42);
		final IntentHelper mih = ph.getCreateIntent(ElementName.MEDIAINTENT);
		final KElement mir = mih.getCreateResource();
		mir.setAttribute(AttributeName.WEIGHT, 130, null);
		mir.setAttribute(ElementName.MEDIATYPE, EnumMediaType.Paper.getName());

		final IntentHelper lih = ph.getCreateIntent(ElementName.LAYOUTINTENT);
		final JDFElement lir = (JDFElement) lih.getCreateResource();
		lir.setAttribute(ElementName.FINISHEDDIMENSIONS, new JDFShape(800, 500, 0).scaleFromMM(1), null);

		final ColorIntentHelper cih = (ColorIntentHelper) ph.getCreateIntent(ElementName.COLORINTENT);
		cih.setNumColors(4, 4);

		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		writeRoundTripX(xjdfHelper, "product/poster", EnumValidationLevel.Incomplete);
	}

	private void prepareNodeInfo(final XJDFHelper xjdfHelper)
	{
		final SetHelper sh1 = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.NODEINFO, EnumUsage.Input);
		final JDFDate jdfDate = new JDFDate().setTime(13, 0, 0);
		sh1.getPartition((JDFAttributeMap) null).getResource().setAttribute(AttributeName.END, jdfDate.getDateTimeISO());
	}

	/**
	 *
	 */
	@Test
	void testBrochure()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("Brochure4_16", null, null);
		xjdfHelper.setTypes("Product");
		prepareNodeInfo(xjdfHelper);

		final ProductHelper ph = xjdfHelper.getCreateRootProduct(0);
		ph.setProductType(eProductType.Brochure);
		ph.setAmount(42);

		final IntentHelper bih = ph.getCreateIntent(ElementName.BINDINGINTENT);
		final KElement bir = bih.getCreateResource();
		bir.setAttribute(ElementName.BINDINGTYPE, EnumSpanBindingType.SaddleStitch.getName(), null);

		final IntentHelper lih = ph.getCreateIntent(ElementName.LAYOUTINTENT);
		final JDFElement lir = (JDFElement) lih.getCreateResource();
		lir.setAttribute(ElementName.FINISHEDDIMENSIONS, new JDFShape(210, 297, 8).scaleFromMM(1), null);

		final ProductHelper phCover = xjdfHelper.appendProduct();
		phCover.setProductType(eProductType.Cover);
		ph.setChild(phCover);

		final IntentHelper mich = phCover.getCreateIntent(ElementName.MEDIAINTENT);
		final KElement micr = mich.getCreateResource();
		micr.setAttribute(AttributeName.WEIGHT, 130, null);
		micr.setAttribute(ElementName.MEDIATYPE, EnumMediaType.Paper.getName());

		final ColorIntentHelper cich = (ColorIntentHelper) phCover.getCreateIntent(ElementName.COLORINTENT);
		cich.setNumColors(4, 4);

		final IntentHelper lich = phCover.getCreateIntent(ElementName.LAYOUTINTENT);
		final JDFElement licr = (JDFElement) lich.getCreateResource();
		licr.setAttribute(ElementName.PAGES, 2, null);
		licr.setAttribute(AttributeName.SPREADTYPE, EnumSpreadType.Spread.getName(), null);

		final ProductHelper phBody = xjdfHelper.appendProduct();
		phBody.setProductType(eProductType.Body);
		ph.setChild(phBody);
		final IntentHelper mibh = phBody.getCreateIntent(ElementName.MEDIAINTENT);
		final KElement mibr = mibh.getCreateResource();
		mibr.setAttribute(AttributeName.WEIGHT, 90, null);
		mibr.setAttribute(ElementName.MEDIATYPE, EnumMediaType.Paper.getName());

		final ColorIntentHelper cibh = (ColorIntentHelper) phBody.getCreateIntent(ElementName.COLORINTENT);
		cibh.setNumColors(1, 1);

		final IntentHelper libh = phBody.getCreateIntent(ElementName.LAYOUTINTENT);
		final JDFElement libr = (JDFElement) libh.getCreateResource();
		libr.setAttribute(ElementName.PAGES, 16, null);
		libr.setAttribute(AttributeName.SPREADTYPE, EnumSpreadType.SinglePage.getName(), null);

		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getAuditPool(), false);
		writeRoundTripX(xjdfHelper, "product/brochure", EnumValidationLevel.Incomplete);
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}
}
