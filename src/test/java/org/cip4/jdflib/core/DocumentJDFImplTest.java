/*
 *
 * The CIP4 Software License, Version 1.0
 *
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
/*
 * DocumentJDFImplTest.java 
 * 
 * @author Dietrich Mucha
 *
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFShapeElement;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFSurface;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFSpanHoleType;
import org.cip4.jdflib.span.JDFSpanMethod;
import org.cip4.jdflib.span.JDFSpanShape;
import org.cip4.jdflib.span.JDFSpanSurface;
import org.cip4.jdflib.span.JDFStringSpan;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class DocumentJDFImplTest extends JDFTestCaseBase
{
	/**
	 * 
	 */
	@Test
	void testContextSensitiveElementNames()
	{ // HoleType, Method, Shape and Surface are context sensitive elements
		// The type casts below should all succeed
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final KElement rootNode = doc.getRoot();

		final JDFStringSpan kelem21 = (JDFStringSpan) rootNode.appendElement("HoleType", null);
		kelem21.setAttribute("Type", "org.cip4.jdflib.span.JDFStringSpan");
		final KElement kelem22 = rootNode.appendElement("RingBinding", null);
		final JDFSpanHoleType kelem23 = (JDFSpanHoleType) kelem22.appendElement("HoleType", null);
		kelem23.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanHoleType");

		final JDFNameSpan kelem11 = (JDFNameSpan) rootNode.appendElement("Method", null);
		kelem11.setAttribute("Type", "org.cip4.jdflib.span.JDFNameSpan");
		final KElement kelem12 = rootNode.appendElement("InsertingIntent", null);
		final JDFSpanMethod kelem13 = (JDFSpanMethod) kelem12.appendElement("Method", null);
		kelem13.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanMethod");

		final JDFShapeElement kelem4 = (JDFShapeElement) rootNode.appendElement("Shape", null);
		kelem4.setAttribute("Type", "org.cip4.jdflib.resource.JDFShapeElement");
		final KElement kelem5 = rootNode.appendElement("BookCase", null);
		final JDFSpanShape kelem6 = (JDFSpanShape) kelem5.appendElement("Shape", null);
		kelem6.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanShape");

		final JDFSurface kelem7 = (JDFSurface) rootNode.appendElement("Surface", null);
		kelem7.setAttribute("Type", "org.cip4.jdflib.resource.process.JDFSurface");
		final KElement kelem8 = rootNode.appendElement("LaminatingIntent", null);
		final JDFSpanSurface kelem9 = (JDFSpanSurface) kelem8.appendElement("Surface", null);
		kelem9.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanSurface");

	}

	/**
	 * 
	 */
	@Test
	void testPrivateResources()
	{ // HoleType, Method, Shape and Surface are context sensitive elements
		// The type casts below should all succeed
		final JDFDoc doc = new JDFDoc(ElementName.JDF);
		final JDFNode n = doc.getJDFRoot();
		final JDFResourcePool rp = n.getCreateResourcePool();
		final KElement e = rp.appendElement("test:res", "www.test.org");
		assertTrue(e instanceof JDFResource);
	}

	/**
	 * 
	 */
	@Test
	void testGetClasses()
	{
		final List<Class<?>> l = DocumentJDFImpl.getClasses(JDFResource.class);
		assertTrue(l.contains(JDFResource.class));
		assertTrue(l.contains(JDFConventionalPrintingParams.class));
		assertFalse(l.contains(JDFNode.class));
	}

}
