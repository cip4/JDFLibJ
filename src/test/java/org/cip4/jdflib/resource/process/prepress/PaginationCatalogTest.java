/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.resource.process.prepress;

import java.util.Collection;

import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.ContainerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaginationCatalogTest
{

	/**
	 *
	 */
	@Test
	void testconstruct()
	{
		Assertions.assertNotNull(PaginationCatalog.instance());
	}

	/**
	 *
	 */
	@Test
	void testToString()
	{
		Assertions.assertNotNull(PaginationCatalog.instance().toString());
	}

	/**
	 *
	 */
	@Test
	void testgetFrontPages()
	{
		Assertions.assertNotNull(PaginationCatalog.instance().getFrontPages("F4-2"));
		Assertions.assertNull(PaginationCatalog.instance().getFrontPages("ffff"));
	}

	/**
	 *
	 */
	@Test
	void testNUP()
	{
		Assertions.assertEquals(new JDFXYPair(1, 1), PaginationCatalog.instance().getNUp("f2-1"));
		Assertions.assertEquals(new JDFXYPair(2, 1), PaginationCatalog.instance().getNUp("f4-1"));
		Assertions.assertEquals(new JDFXYPair(4, 2), PaginationCatalog.instance().getNUp("f16-7"));
	}

	/**
	 *
	 */
	@Test
	void testComplete()
	{
		final PaginationCatalog pc = PaginationCatalog.instance();
		final Collection<String> keys = pc.getKeys();
		for (final String key : keys)
		{
			final JDFIntegerList p = pc.getBackPages(key);
			p.addAll(pc.getFrontPages(key));
			ContainerUtil.unify(p);
			final JDFXYPair nUp = pc.getNUp(key);
			final double size = 2 * nUp.getX() * nUp.getY();
			Assertions.assertEquals(size, p.size(), 0, key);
			for (int i = 0; i < size; i++)
			{
				Assertions.assertTrue(p.doubleAt(i) >= 0);
				Assertions.assertTrue(p.doubleAt(i) < size);
			}
		}
	}

}
