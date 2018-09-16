/*
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */

package org.cip4.jdflib.extensions.xjdfwalker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.junit.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class IDRemoverTest extends JDFTestCaseBase
{

	KElement root;

	/**
	 * Test method for {@link org.cip4.jdflib.extensions.xjdfwalker.IDFinder#getMap(KElement)}.
	 */
	@Test
	public void testRemoveIDs()
	{
		IDRemover finder = new IDRemover();
		finder.removeIDs(root);
		assertEquals(root.toString().indexOf("ID="), -1);
	}

	/**
	 * Test method for {@link org.cip4.jdflib.extensions.xjdfwalker.IDFinder#getMap(KElement)}.
	 */
	@Test
	public void testHeader()
	{
		IDRemover finder = new IDRemover();
		XJDFHelper h = XJDFHelper.getHelper(root);
		h.getCreateAuditPool().appendMessage(XJDFConstants.AuditResource);
		finder.removeIDs(root);
		assertNotNull(h.getAuditPool().getMessageHelpers().get(0).getHeader().getID());
	}

	/**
	 * 	@Test
	 */
	public void testRefs()
	{
		IDRemover finder = new IDRemover();
		XJDFHelper h = XJDFHelper.getHelper(root);
		h.getCreateAuditPool().appendMessage(XJDFConstants.AuditResource);
		root.setXPathAttribute("foo/@blahRefs", "R1.1 R1.2");
		finder.removeIDs(root);
		assertNotSame(root.toString().indexOf("R1.1"), -1);
		assertNotSame(root.toString().indexOf("R1.2"), -1);

	}

	/**
	 * 	@Test
	 */
	public void testRef()
	{
		IDRemover finder = new IDRemover();
		XJDFHelper h = XJDFHelper.getHelper(root);
		h.getCreateAuditPool().appendMessage(XJDFConstants.AuditResource);
		root.setXPathAttribute("foo/@blahRef", "R1.1");
		finder.removeIDs(root);
		assertNotSame(root.toString().indexOf("R1.1"), -1);
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		root = new XMLDoc(XJDFConstants.XJDF, null).getRoot();
		for (int i = 1; i < 4; i++)
		{
			root.setXPathAttribute("ResourceSet[" + i + "]/@ID", "R" + i);
			for (int j = 1; j < 4; j++)
			{
				root.setXPathAttribute("ResourceSet[" + i + "]/Resource[" + j + "]/@ID", "R" + i + "." + j + "");
				root.setXPathAttribute("ResourceSet[" + i + "]/Resource[" + j + "]/Part/@SheetName", "S" + j + "");
			}
		}
	}
}
