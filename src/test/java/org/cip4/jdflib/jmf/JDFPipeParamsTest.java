/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.jmf;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFCoilBindingParams;
import org.cip4.jdflib.resource.JDFHeadBandApplicationParams;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * Jun 11, 2009
 */
public class JDFPipeParamsTest {
	private JDFPipeParams pp;

	/**
	 * 
	 */
	@Test
	void testAppendResourceLink()
	{
		JDFResourceLink rl = pp.appendResourceLink("Dummy", true);
		Assertions.assertEquals(rl.getUsage(), EnumUsage.Input);
		try
		{
			rl = pp.appendResourceLink("Dummy2", true);
			Assertions.fail("max 1 rl");
		}
		catch (final Exception e)
		{
			// nop
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// /////////////////

	/**
	 * 
	 */
	@Test
	void testAppendResource()
	{
		final JDFCoilBindingParams cbp = (JDFCoilBindingParams) pp.appendResource(ElementName.COILBINDINGPARAMS);
		Assertions.assertEquals(cbp.getResourceClass(), EnumResourceClass.Parameter);
		final JDFHeadBandApplicationParams hap = (JDFHeadBandApplicationParams) pp.appendResource(ElementName.HEADBANDAPPLICATIONPARAMS);
		Assertions.assertEquals(hap.getResourceClass(), EnumResourceClass.Parameter);
	}

	// //////////////////////////////////////////////////////////////////////////
	// /////////////////

	/**
	 * 
	 */
	@Test
	void testGetResourceLink()
	{
		final JDFExposedMedia xm = (JDFExposedMedia) pp.appendResource(ElementName.EXPOSEDMEDIA);
		Assertions.assertEquals(xm.getResourceClass(), EnumResourceClass.Handling);
		final JDFMedia m = (JDFMedia) pp.appendResource(ElementName.MEDIA);
		Assertions.assertEquals(m.getResourceClass(), EnumResourceClass.Consumable);
		final JDFRefElement rm = xm.refElement(m);
		Assertions.assertEquals(rm.getTarget(), m);
		final JDFResourceLink rl = pp.appendResourceLink(ElementName.EXPOSEDMEDIA, true);
		rl.setrRef(xm.getID());
		Assertions.assertEquals(rl.getTarget(), xm);
		Assertions.assertEquals(rl, pp.getResourceLink());
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	@Test
	void testGetResource()
	{
		final JDFExposedMedia xm = (JDFExposedMedia) pp.appendResource(ElementName.EXPOSEDMEDIA);
		Assertions.assertEquals(xm.getResourceClass(), EnumResourceClass.Handling);
		final JDFMedia m = (JDFMedia) pp.appendResource(ElementName.MEDIA);
		Assertions.assertEquals(m.getResourceClass(), EnumResourceClass.Consumable);
		final JDFRefElement rm = xm.refElement(m);
		Assertions.assertEquals(rm.getTarget(), m);
		Assertions.assertEquals(pp.getResource(ElementName.EXPOSEDMEDIA), xm);
		Assertions.assertEquals(pp.getResource(null), xm);
		Assertions.assertEquals(pp.getResource(ElementName.MEDIA), m);
		try
		{
			Assertions.assertNull(pp.getResource("MediaLink"));
			Assertions.fail();
		}
		catch (final JDFException e)
		{
			// nop
		}

		final JDFResourceLink rl = pp.appendResourceLink(ElementName.EXPOSEDMEDIA, true);
		rl.setrRef(xm.getID());
		Assertions.assertEquals(xm, pp.getResource(null));
		Assertions.assertEquals(rl.getTarget(), pp.getResource(null));

		rl.setPartMap(new JDFAttributeMap("Run", "r1"));
		Assertions.assertEquals(xm, pp.getResource(null));
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@BeforeEach
    public void setUp() throws Exception
	{
        final JDFDoc doc = new JDFDoc(ElementName.JMF);
		final JDFJMF jmf = doc.getJMFRoot();
		final JDFCommand c = jmf.appendCommand(EnumType.PipePull);
		pp = c.appendPipeParams();
	}

	/**
	 * 
	 */
	@Test
	void testApplyPipeToNode()
	{
		JDFResourceLink rl = pp.appendResourceLink("Component", true);
		rl.setActualAmount(33, null);
		rl.setrRef("rl_c");

		JDFNode n = new JDFDoc("JDF").getJDFRoot();
		JDFResource r = n.addResource("Component", null);
		r.setID("rl_c");
		JDFResourceLink rl2 = n.ensureLink(r, EnumUsage.Input, null);

		pp.applyPipeToNode(n);

		Assertions.assertEquals(rl2.getActualAmount(null), 33, 0);
	}
}