/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
package org.cip4.jdflib.pool;

import junit.framework.TestCase;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;

/**
 * @author MuchaD
 * 
 *         This implements the first fixture with unit tests for class
 *         JDFElement.
 */
public class JDFColorPoolTest extends TestCase
{
	JDFColorPool cp;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	public void setUp()
	{
		try
		{
			super.setUp();
		}
		catch (Exception e)
		{
			//
		}
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.setType("Interpreting", false);
		cp = (JDFColorPool) n.addResource("ColorPool", null, EnumUsage.Input, null, null, null, null);
		cp.appendColorWithName("Cyan", "true");
		cp.appendColorWithName("Grün", "Grün");
		((JDFColor) cp.appendElement("jdf:Color", JDFElement.getSchemaURL())).setName("foo");
	}

	/**
	 * Method testIncludesAttribute.
	 * 
	 * @throws Exception
	 */
	public void testGetColorWithName() throws Exception
	{
		assertNotNull("grün", cp.getColorWithName("Grün"));
		boolean caught = false;
		try
		{
			cp.appendColorWithName("Grün", "Grün");
		}
		catch (JDFException e)
		{
			caught = true;
		}
		assertTrue("noappend", caught);
	}

	/**
	 * Method testIncludesAttribute.
	 * 
	 * @throws Exception
	 */
	public void testGetCreateColorWithName() throws Exception
	{
		assertNotNull("grün", cp.getCreateColorWithName("Grün", null));
	}

	/**
	 * 
	 */
	public void testRemoveColor()
	{
		assertEquals("num", cp.numChildElements("Color", null), 3);
		cp.removeColor("bar");
		assertEquals("num", cp.numChildElements("Color", null), 3);
		cp.removeColor("Grün");
		assertEquals("num", cp.numChildElements("Color", null), 2);
		cp.removeColor("foo");
		assertEquals("num", cp.numChildElements("Color", null), 1);

	}

	/**
	 * 
	 */
	public void testDuplicateColor()
	{
		assertNull(cp.getDuplicateColors());
		JDFColor c = cp.appendColorWithName("grun", "grun");
		assertNull(cp.getDuplicateColors());
		c.set8BitNames("Grün".getBytes());
		c.setName("grun");
		assertTrue(cp.getDuplicateColors().contains("grun"));
	}

	/**
	 * @see junit.framework.TestCase#toString()
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return cp == null ? null : cp.toString();
	}

}