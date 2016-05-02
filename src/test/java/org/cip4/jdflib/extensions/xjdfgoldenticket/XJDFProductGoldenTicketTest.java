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
package org.cip4.jdflib.extensions.xjdfgoldenticket;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.junit.Before;
import org.junit.Test;

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
	public void testSimple()
	{
		XJDFBaseGoldenTicket bt = new XJDFBaseGoldenTicket(1, EnumVersion.Version_2_0);
		bt.getXJDFHelper().writeToFile(sm_dirTestDataTemp + "xjdf/GTSimple.xjdf");
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testBrochureSimple()
	{
		XJDFBaseGoldenTicket bt = new XJDFProductGoldenTicket(1, EnumVersion.Version_2_0);
		XJDFHelper xjdfHelper = bt.getXJDFHelper();
		ProductHelper ph = xjdfHelper.getCreateRootProduct(0);
		ph.setAmount(10);
		ProductHelper phc = xjdfHelper.getCreateProduct("IDCover");
		phc.setProductType("Brochure");
		phc.setAmount(1);
		ProductHelper phb = xjdfHelper.getCreateProduct("IDBody");
		phb.setAmount(1);
		IntentHelper ih = ph.getCreateIntent(ElementName.BINDINGINTENT);
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
	public void testNotebook()
	{
		XJDFBaseGoldenTicket bt = new XJDFProductGoldenTicket(1, EnumVersion.Version_2_0);
		XJDFHelper xjdfHelper = bt.getXJDFHelper();
		ProductHelper ph = xjdfHelper.getCreateRootProduct(0);
		ph.setAmount(10);
		ph.setProductType("Notebook");
		ProductHelper phc = xjdfHelper.getCreateProduct("IDCover");
		phc.setAmount(1);
		phc.setProductType("FrontCover");
		ProductHelper phb = xjdfHelper.getCreateProduct("IDBody");
		phb.setAmount(50);
		phb.setProductType("BookBlock");
		ProductHelper phcb = xjdfHelper.getCreateProduct("IDBack");
		phcb.setProductType("BackCover");
		phcb.setAmount(1);
		IntentHelper ih = ph.getCreateIntent(ElementName.BINDINGINTENT);
		ih.getCreateResource().setAttribute(ElementName.BINDINGTYPE, "EdgeGluing");
		ih.getCreateResource().setAttribute(ElementName.BINDINGSIDE, "Top");
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDCover", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBody", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBack", null, null, false);
		xjdfHelper.writeToFile(sm_dirTestDataTemp + "xjdf/GTNotebook.xjdf");
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testMultiVariable()
	{
		XJDFBaseGoldenTicket bt = new XJDFProductGoldenTicket(1, EnumVersion.Version_2_0);
		XJDFHelper xjdfHelper = bt.getXJDFHelper();
		ProductHelper ph = xjdfHelper.getCreateRootProduct(0);
		ph.setAmount(10000);
		IntentHelper ih = ph.getCreateIntent("VariableIntent");
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBrochure", null, null, false);
		ih.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBook", null, null, false);
		ProductHelper phh = xjdfHelper.getCreateProduct("IDBook");
		phh.setAmount(1000);
		phh.setProductType("Book");

		ProductHelper phhc = xjdfHelper.getCreateProduct("IDBookCover");
		phhc.setProductType("Cover");
		phhc.setAmount(1);

		ProductHelper phb = xjdfHelper.getCreateProduct("IDBody");
		phb.setAmount(1);

		IntentHelper ihb = phh.getCreateIntent(ElementName.BINDINGINTENT);
		ihb.getCreateResource().setAttribute(ElementName.BINDINGTYPE, "HardCover");
		ihb.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBookCover", null, null, false);
		ihb.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBody", null, null, false);

		ProductHelper phs = xjdfHelper.getCreateProduct("IDBrochure");
		phs.setAmount(9000);

		ProductHelper phsc = xjdfHelper.getCreateProduct("IDBrochureCover");
		phsc.setProductType("Cover");
		phsc.setAmount(1);

		IntentHelper ihs = phs.getCreateIntent(ElementName.BINDINGINTENT);
		ihs.getCreateResource().setAttribute(ElementName.BINDINGTYPE, "SaddleStitch");
		ihs.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBrochureCover", null, null, false);
		ihs.getResource().appendAttribute(XJDFConstants.ChildRefs, "IDBody", null, null, false);

		xjdfHelper.writeToFile(sm_dirTestDataTemp + "xjdf/GTVariable1.xjdf");
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	@Before
	protected void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}

}
