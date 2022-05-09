/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author rainer prosi
 * @date Dec 23, 2012
 */
public class BaseXJDFHelperTest extends JDFTestCaseBase
{
	private XJDFHelper theHelper;

	/**
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		theHelper = new XJDFHelper(null);
		KElement.setLongID(false);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSetXPath()
	{
		theHelper.setTypes("Product");
		assertEquals("Product", theHelper.getTypes().get(0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetBaseRoot()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("a", "b", null);
		final KElement e = xjdfHelper.getRoot();
		assertEquals(xjdfHelper, BaseXJDFHelper.getBaseHelper(e));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetBaseRootJMF()
	{
		final XJMFHelper xjdfHelper = new XJMFHelper();
		final KElement e = xjdfHelper.getRoot();
		assertEquals(xjdfHelper, BaseXJDFHelper.getBaseHelper(e));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetXRoot()
	{
		final ProductHelper p = theHelper.appendProduct();
		assertEquals(theHelper, p.getXRoot());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGeneralID()
	{
		theHelper.setGeneralID("foo", "Product");
		assertEquals("Product", theHelper.getGeneralID("foo"));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testComment()
	{
		final SetHelper set = theHelper.getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
		set.setComment("foo");
		assertEquals("foo", set.getComment(0));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetAttribute()
	{
		Assertions.assertNull(theHelper.getAttribute("foo"));
		theHelper.setAttribute("foo", "bar");
		assertEquals("bar", theHelper.getAttribute("foo"));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testGetLocalName()
	{
		assertEquals(XJDFConstants.XJDF, theHelper.getLocalName());
	}

	/**
	*
	*
	*/
	@Test
	public void testSetAttribute()
	{
		theHelper.setAttribute("foo", "bar");
		assertEquals("bar", theHelper.getAttribute("foo"));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testEquals()
	{
		assertEquals(theHelper, theHelper);
		assertEquals(theHelper, new XJDFHelper(theHelper.getRoot()));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testClone()
	{
		assertEquals(theHelper, theHelper);
		Assertions.assertFalse(theHelper.equals(theHelper.clone()));
		assertEquals(theHelper.toString(), theHelper.clone().toString());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testCopy()
	{
		final XJDFHelper clone = theHelper.clone();
		final SetHelper s = clone.appendSet(null, "foo", null);
		theHelper.copyHelper(s);
		Assertions.assertNotNull(theHelper.getSet("foo", null));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testDeleteNode()
	{
		assertEquals(theHelper, theHelper);
		Assertions.assertNotNull(theHelper.getRoot());
		theHelper.deleteNode();
		assertEquals(theHelper, theHelper);
		Assertions.assertNull(theHelper.getRoot());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testHash()
	{
		assertEquals(theHelper.hashCode(), new XJDFHelper(theHelper.getRoot()).hashCode());
	}
}
