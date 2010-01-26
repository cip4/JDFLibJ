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
/*
 * DocumentJDFImplTest.java 
 * 
 * @author Dietrich Mucha
 *
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.core;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.resource.JDFShapeElement;
import org.cip4.jdflib.resource.process.JDFSurface;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFSpanHoleType;
import org.cip4.jdflib.span.JDFSpanMethod;
import org.cip4.jdflib.span.JDFSpanShape;
import org.cip4.jdflib.span.JDFSpanSurface;
import org.cip4.jdflib.span.JDFStringSpan;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class DocumentJDFImplTest extends JDFTestCaseBase
{
	/**
	 * 
	 */
	public void testContextSensitiveElementNames()
	{ // HoleType, Method, Shape and Surface are context sensitive elements
		// The type casts below should all succeed
		JDFDoc doc = new JDFDoc(ElementName.JDF);
		KElement rootNode = doc.getRoot();

		JDFStringSpan kelem21 = (JDFStringSpan) rootNode.appendElement("HoleType", null);
		kelem21.setAttribute("Type", "org.cip4.jdflib.span.JDFStringSpan");
		KElement kelem22 = rootNode.appendElement("RingBinding", null);
		JDFSpanHoleType kelem23 = (JDFSpanHoleType) kelem22.appendElement("HoleType", null);
		kelem23.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanHoleType");

		JDFNameSpan kelem11 = (JDFNameSpan) rootNode.appendElement("Method", null);
		kelem11.setAttribute("Type", "org.cip4.jdflib.span.JDFNameSpan");
		KElement kelem12 = rootNode.appendElement("InsertingIntent", null);
		JDFSpanMethod kelem13 = (JDFSpanMethod) kelem12.appendElement("Method", null);
		kelem13.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanMethod");

		JDFShapeElement kelem4 = (JDFShapeElement) rootNode.appendElement("Shape", null);
		kelem4.setAttribute("Type", "org.cip4.jdflib.resource.JDFShapeElement");
		KElement kelem5 = rootNode.appendElement("BookCase", null);
		JDFSpanShape kelem6 = (JDFSpanShape) kelem5.appendElement("Shape", null);
		kelem6.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanShape");

		JDFSurface kelem7 = (JDFSurface) rootNode.appendElement("Surface", null);
		kelem7.setAttribute("Type", "org.cip4.jdflib.resource.process.JDFSurface");
		KElement kelem8 = rootNode.appendElement("LaminatingIntent", null);
		JDFSpanSurface kelem9 = (JDFSpanSurface) kelem8.appendElement("Surface", null);
		kelem9.setAttribute("Type", "org.cip4.jdflib.span.JDFSpanSurface");

	}
}
