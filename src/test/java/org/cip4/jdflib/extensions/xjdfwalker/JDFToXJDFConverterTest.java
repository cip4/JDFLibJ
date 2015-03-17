/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.extensions.xjdfwalker;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.JDFToXJDF;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.junit.Test;

/**
 * @author rainer prosi
 *  
 */
public class JDFToXJDFConverterTest extends JDFTestCaseBase
{
	/**
	 * 
	 *  
	 */
	@Test
	public void testRefMediaFromInline()
	{
		JDFToXJDF conv = new JDFToXJDF();
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ImageSetting);
		JDFExposedMedia xm = (JDFExposedMedia) n.addResource(ElementName.EXPOSEDMEDIA, EnumUsage.Output);
		xm.appendMedia().setMediaSetCount(42);
		KElement xjdf = conv.convert(n);
		assertNotNull(new XJDFHelper(xjdf).getSet("Media", 0));
	}

	/**
	*
	 */
	@Test
	public KElement testDeliveryIntent()
	{
		final JDFNode nP = new JDFDoc("JDF").getJDFRoot();
		nP.setType(EnumType.Product);
		nP.setDescriptiveName("desc");
		JDFDeliveryIntent di = (JDFDeliveryIntent) nP.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFComponent c = (JDFComponent) nP.addResource("Component", EnumUsage.Output);
		nP.getLink(c, null).setAmount(66);
		JDFDropItemIntent dropItemIntent = di.appendDropIntent().appendDropItemIntent();
		dropItemIntent.refComponent(c);
		dropItemIntent.setAmount(42);
		JDFDropItemIntent dropItemIntent2 = di.appendDropIntent().appendDropItemIntent();
		dropItemIntent2.refComponent(c);
		dropItemIntent2.setAmount(63);
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.setSingleNode(true);
		KElement xjdf = xjdf20.makeNewJDF(nP, null);
		xjdf.write2File(sm_dirTestDataTemp + "delTest2.xjdf");
		assertNotNull(xjdf);
		assertEquals(xjdf.getXPathAttribute("ParameterSet/Parameter/DeliveryParams/DropItem/@Amount", null), "42");
		assertEquals(xjdf.getXPathAttribute("ParameterSet/Parameter[2]/DeliveryParams/DropItem/@Amount", null), "63");
		return xjdf;
	}

	/**
	 * 
	 *  
	 */
	@Test
	public void testColorIntent()
	{
		JDFToXJDF conv = new JDFToXJDF();
		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.Product);
		n.addResource(ElementName.COMPONENT, EnumUsage.Output);
		JDFColorIntent ci = (JDFColorIntent) n.getCreateResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		JDFColorIntent cif = (JDFColorIntent) ci.addPartition(EnumPartIDKey.Side, "Front");
		cif.appendColorsUsed().setCMYK();
		cif.appendCoatings().setActual("Varnish");
		JDFColorIntent cib = (JDFColorIntent) ci.addPartition(EnumPartIDKey.Side, "Back");
		cib.appendCoatings().setActual("Mess");
		cib.setNumColors(1);
		KElement xjdf = conv.convert(n);
		ProductHelper ph = new XJDFHelper(xjdf).getProductHelpers().get(0);
		assertNotNull(ph);
		IntentHelper ih = ph.getIntent("ColorIntent");
		KElement e = ih.getResource();
		assertEquals(e.getChildElementVector("SurfaceColor", null).size(), 2);
	}

	/**
	*  
	*  
	*/
	@Test
	public void testMultiBackwardProduct()
	{
		XJDFHelper h = new XJDFHelper("j", "root", null);
		for (int i = 0; i < 2; i++)
		{
			ProductHelper cover = h.appendProduct();
			ProductHelper body = h.appendProduct();
			ProductHelper book = h.appendProduct();
			book.setRoot();
			book.setChild(cover, 1);
			book.setChild(body, 1);
		}
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		JDFDoc d = xCon.convert(h);
		assertEquals(d.getJDFRoot().getJobPartID(true), "root");

		JDFToXJDF conv = new JDFToXJDF();
		KElement newRoot = conv.convert(d.getJDFRoot());
		XJDFHelper h2 = new XJDFHelper(newRoot);
		assertEquals(h2.numProductHelpers(true), 2);
		assertEquals(h2.numProductHelpers(false), 6);
	}

	/**
	 * 
	 */
	@Test
	public void testPipeJMF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Command, JDFMessage.EnumType.PipeClose);
		JDFToXJDF conv = new JDFToXJDF();
		KElement xjmf = conv.makeNewJMF(jmf);
		assertEquals(xjmf.getXPathAttribute("CommandPipeControl/PipeParams/@Operation", null), "PipeClose");
		final JDFJMF jmfResp = JDFJMF.createJMF(EnumFamily.Response, JDFMessage.EnumType.PipeClose);
		xjmf = conv.makeNewJMF(jmfResp);
		assertEquals(xjmf.getElement(null).getLocalName(), "ResponsePipeControl");
	}

	/**
	 * 
	 * @see org.cip4.jdflib.JDFTestCaseBase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception
	{
		XJMFTypeMap.shutDown();
		super.tearDown();
	}

}
