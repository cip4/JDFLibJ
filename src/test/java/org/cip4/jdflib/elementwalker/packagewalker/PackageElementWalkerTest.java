/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
package org.cip4.jdflib.elementwalker.packagewalker;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.elementwalker.FixVersion;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 * @date May 10, 2014
 */
class PackageElementWalkerTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 */
	@Test
	void testConstruct()
	{
		final PackageTestWalker w = new PackageTestWalker();
		Assertions.assertNotNull(w);
	}

	/**
	 *
	 *
	 */
	@Test
	void testMultiConstruct()
	{
		final FixVersion v = new FixVersion(EnumVersion.Version_1_1);
		final FixVersion v2 = new FixVersion(EnumVersion.Version_1_1);
		Assertions.assertNotNull(v2);
		Assertions.assertNotNull(v);
		Assertions.assertEquals(v.getFactory().getBaseWalkers().size(), v2.getFactory().getBaseWalkers().size());
	}

	/**
	 *
	 *
	 */
	@Test
	void testMultiConstructJDF()
	{
		final JDFDoc d = creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final XJDF20 conv = new XJDF20();
		final KElement xjdf = conv.convert(n);
		final XJDFToJDFConverter c2 = new XJDFToJDFConverter(null);
		final JDFDoc d2 = c2.convert(xjdf);
		final JDFNode n2 = d2.getJDFRoot();
		Assertions.assertNotNull(n2.getResource(ElementName.EXPOSEDMEDIA, null, 0));
	}

	/**
	 * test that the package classes are actually implemented
	 *
	 */
	@Test
	void testFoo()
	{
		final PackageTestWalker w = new PackageTestWalker();
		final BaseWalkerFactory factory = w.getFactory();
		Assertions.assertNotNull(factory);
		Assertions.assertEquals(WalkFoo.class, factory.getWalker(new XMLDoc("D", null).getRoot()).getClass());
	}

	/**
	 * test that the package classes are actually implemented
	 *
	 */
	@Test
	void testFoo2()
	{
		for (int i = 0; i < 10; i++)
		{
			final PackageTestWalker w = new PackageTestWalker();
			final BaseWalkerFactory factory = w.getFactory();
			Assertions.assertNotNull(factory);
			Assertions.assertEquals(WalkFoo.class, factory.getWalker(new XMLDoc("D", null).getRoot()).getClass());
		}
	}
}
