/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of
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
/**
 * JDFStrinStateTest.java
 *
 * @author Rainer Prosi
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.devicecapability;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.resource.devicecapability.JDFMatrixState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class JDFMatrixStateTest extends JDFTestCaseBase
{

	JDFMatrixState sState = null;

	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		JDFDoc doc = new JDFDoc("MatrixState");
		sState = (JDFMatrixState) doc.getRoot();

	}

	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////
	@Test
	public final void testAddValue() throws Exception
	{
		sState.appendValue(new JDFMatrix("1 2 3 4 5 6"), null);
		sState.addValue("6 5 4 3 2 1", EnumFitsValue.Allowed);
		Assertions.assertEquals(sState.getValueAllowedValue(1), new JDFMatrix(
				"6 5 4 3 2 1"));
	}

	@Test
	public final void testAppendValue() throws Exception
	{
		sState.appendValue(new JDFMatrix("10 2 3 4 5 6"), null);
		Assertions.assertEquals(sState.getValueAllowedValue(0), new JDFMatrix(
				"10 2 3 4 5 6"));
		sState
				.appendValue(new JDFMatrix("20 2 3 4 5 6"),
						EnumFitsValue.Present);
		Assertions.assertEquals(sState.getValueAllowedValue(1), new JDFMatrix(
				"20 2 3 4 5 6"));
		sState
				.appendValue(new JDFMatrix("30 2 3 4 5 6"),
						EnumFitsValue.Allowed);
		Assertions.assertEquals(sState.getValueAllowedValue(2), new JDFMatrix(
				"30 2 3 4 5 6"));
	}

	@Test
	public final void testFitsValue() throws Exception
	{
		sState.appendValue(new JDFMatrix("10 2 3 4 5 6"), null);
		sState
				.appendValue(new JDFMatrix("20 2 3 4 5 6"),
						EnumFitsValue.Present);
		sState
				.appendValue(new JDFMatrix("30 2 3 4 5 6"),
						EnumFitsValue.Allowed);
		Assertions.assertTrue(sState.fitsValue("30 2 3 4 5 6", EnumFitsValue.Allowed));
		Assertions.assertFalse(sState.fitsValue("30 2 3 4 5 6", EnumFitsValue.Present));
		Assertions.assertFalse(sState.fitsValue("20 2 3 4 5 6", EnumFitsValue.Allowed));
		Assertions.assertTrue(sState.fitsValue("20 2 3 4 5 6", EnumFitsValue.Present));
		Assertions.assertTrue(sState.fitsValue("10 2 3 4 5 6", EnumFitsValue.Present));
		Assertions.assertFalse(sState.fitsValue("40 2 3 4 5 6", EnumFitsValue.Present));
		Assertions.assertTrue(sState.fitsValue("10 2 3 4 5 6", EnumFitsValue.Allowed));
		Assertions.assertFalse(sState.fitsValue("40 2 3 4 5 6", EnumFitsValue.Allowed));
	}

}
