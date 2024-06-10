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
/**
 *
 */
package org.cip4.jdflib.elementwalker;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author prosirai
 *
 */
class BaseWalkerTest extends JDFTestCaseBase
{

	static class TestWalker extends BaseWalker
	{
		private String name;

		/**
		 * @param factory this call adds the testwalker to the factory
		 */
		public TestWalker(BaseWalkerFactory factory)
		{
			super(factory);
			this.name = null;
		}

		void setNames(String name)
		{
			this.name = name;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			VString strings = StringUtil.tokenize(name, null, false);
			return strings == null || strings.size() == 0 ? null : strings;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(KElement e)
		{
			return name == null || StringUtil.equals(e.getLocalName(), name);
		}

	}

	/**
	 *
	 */
	@Test
	void testDepth()
	{
		BaseWalkerFactory bf = new BaseWalkerFactory();
		BaseWalker b = new TestWalker(bf);
		Assertions.assertEquals(b.getDepth(), 1);
	}

	/**
	 *
	 */
	@Test
	void testDepthWalk()
	{
		BaseWalkerFactory bf = new BaseWalkerFactory();
		new TestWalker(bf);
		XMLDoc d = new XMLDoc("a", null);
		ElementWalker ew = new ElementWalker(bf);
		KElement root = d.getRoot();
		Assertions.assertEquals(ew.walkTree(root, null), 1);
		for (int i = 1; i <= 10; i++)
			root.getCreateXPathElement("b/c/d[" + i + "]");
		Assertions.assertEquals(ew.walkTree(root, null), 13, "a,b,c+10*d=13");
	}

	/**
	 * test to check that the speed up map works
	 */
	@Test
	void testElementName()
	{
		BaseWalkerFactory bf = new BaseWalkerFactory();
		TestWalker tw = new TestWalker(bf);
		KElement a = new XMLDoc("a", null).getRoot();
		Assertions.assertEquals(bf.getWalker(a), tw);
		tw.setNames("b");
		bf.addWalker(tw);
		Assertions.assertNull(bf.getWalker(a));
		tw.setNames("a");
		bf.addWalker(tw);
		Assertions.assertEquals(bf.getWalker(a), tw);
	}
}
