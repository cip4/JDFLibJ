/*
 *
 * The CIP4 Software License, Version 1.0
 *
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
package org.cip4.jdflib.resource.process;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 *
 * @author rainer prosi
 * @date May 15, 2014
 */
class JDFComponentTest extends JDFTestCaseBase
{
	private JDFComponent c;
	private JDFNode root;
	private JDFDoc doc;

	/**
	 * tests the separationlist class
	 *
	 */
	////////////////////////////////////////////////////////////////////////////
	// /
	@Test
	public final void testSetDimensions()
	{
		c.setDimensions(new JDFXYPair(1, 2));
		Assertions.assertEquals(new JDFShape(1, 2, 0), c.getDimensions());
	}

	/**
	 *
	 * @see JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		doc = new JDFDoc("JDF");
		root = doc.getJDFRoot();
		c = (JDFComponent) root.addResource(ElementName.COMPONENT, EnumUsage.Input);
	}

	/**
	 * 	@Test
	 *
	 */
	void testSetComponentTypeAuto()
	{
		c.setComponentType(null);
		Assertions.assertFalse(c.hasAttribute(AttributeName.COMPONENTTYPE));
	}

	/**
	 * 	@Test
	 *
	 */
	void testIsComponentType()
	{
		c.setComponentType(EnumComponentType.FinalProduct, null);
		Assertions.assertTrue(c.isComponentType(EnumComponentType.FinalProduct));
		Assertions.assertFalse(c.isComponentType(EnumComponentType.PartialProduct));
		Assertions.assertFalse(c.isComponentType(EnumComponentType.Web));
		c.setComponentType(EnumComponentType.FinalProduct, EnumComponentType.Sheet);
		Assertions.assertTrue(c.isComponentType(EnumComponentType.FinalProduct));
		Assertions.assertTrue(c.isComponentType(EnumComponentType.Sheet));
		Assertions.assertFalse(c.isComponentType(EnumComponentType.PartialProduct));
		Assertions.assertFalse(c.isComponentType(EnumComponentType.Web));
	}

	/**
	 *
	 */
	@Test
	void testGetMediaLayout()
	{
		c.setComponentType(null);
		JDFLayout lo = c.appendLayout();
		JDFMedia m = lo.appendMedia();
		Assertions.assertEquals(m, c.getMedia());
		lo.makeRootResource(null, null, true);
		Assertions.assertEquals(m, c.getMedia());
	}

	/**
	 *
	 */
	@Test
	void testGetMedia()
	{
		c.setComponentType(null);
		JDFMedia m = (JDFMedia) c.appendElement(ElementName.MEDIA);
		Assertions.assertEquals(m, c.getMedia());
	}

	/**
	 * 	@Test
	 *
	 */
	@Test
	void testSetComponentType()
	{
		c.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);
		Assertions.assertTrue(c.hasAttribute(AttributeName.COMPONENTTYPE));
		Assertions.assertEquals(c.getComponentType().size(), 2);
		Assertions.assertTrue(c.getComponentType().contains(EnumComponentType.PartialProduct));
	}

	/**
	 * 	@Override
	 * @see JDFTestCaseBase#toString()
	 */
	@Override
	public String toString()
	{
		return c.toString();
	}

	/**
	 * 	@Test
	 */
	@Test
	void testComponentManifest()
	{
		root.getLink(c, null).setUsage(EnumUsage.Output);
		// TODO complete

		doc.write2File(sm_dirTestDataTemp + "ComponentManifest.jdf", 2, false);
	}
}
