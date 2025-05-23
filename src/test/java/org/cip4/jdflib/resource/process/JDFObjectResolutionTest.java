/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
import org.cip4.jdflib.auto.JDFAutoObjectResolution.EnumSourceObjects;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
class JDFObjectResolutionTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	void testMatches()
	{
		JDFObjectResolution or = (JDFObjectResolution) new JDFDoc(ElementName.OBJECTRESOLUTION).getRoot();
		or.addSourceObject(EnumSourceObjects.ImagePhotographic);
		Assertions.assertTrue(or.matches(EnumSourceObjects.ImagePhotographic));
		Assertions.assertTrue(or.matches(null));
	}

	/**
	 *
	 */
	@Test
	void testAddSourceObject()
	{
		JDFObjectResolution or = (JDFObjectResolution) new JDFDoc(ElementName.OBJECTRESOLUTION).getRoot();
		or.addSourceObject(EnumSourceObjects.All);
		Assertions.assertEquals(or.getSourceObjects().get(0), EnumSourceObjects.All);
		Assertions.assertEquals(or.getSourceObjects().size(), 1);
		or.addSourceObject(EnumSourceObjects.All);
		Assertions.assertEquals(or.getSourceObjects().get(0), EnumSourceObjects.All);
		Assertions.assertEquals(or.getSourceObjects().size(), 1);
		or.addSourceObject(EnumSourceObjects.ImagePhotographic);
		Assertions.assertEquals(or.getSourceObjects().get(0), EnumSourceObjects.All);
		Assertions.assertEquals(or.getSourceObjects().get(1), EnumSourceObjects.ImagePhotographic);
		Assertions.assertEquals(or.getSourceObjects().size(), 2);
	}

	/**
	 *
	 */
	@Test
	void testAddObjectTag()
	{
		JDFObjectResolution or = (JDFObjectResolution) new JDFDoc(ElementName.OBJECTRESOLUTION).getRoot();
		or.addObjectTag("foo");
		Assertions.assertEquals(or.getObjectTags().get(0), "foo");
		Assertions.assertEquals(or.getObjectTags().size(), 1);
		or.addObjectTag("foo");
		Assertions.assertEquals(or.getObjectTags().get(0), "foo");
		Assertions.assertEquals(or.getObjectTags().size(), 1);
		or.addObjectTag("bar");
		Assertions.assertEquals(or.getObjectTags().get(0), "foo");
		Assertions.assertEquals(or.getObjectTags().get(1), "bar");
		Assertions.assertEquals(or.getObjectTags().size(), 2);
	}

}
