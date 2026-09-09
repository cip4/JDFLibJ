/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.IntentHelper.EIntentType;
import org.cip4.jdflib.extensions.PartitionHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.intent.JDFInsertingIntent;
import org.cip4.jdflib.span.JDFSpanBase;
import org.junit.jupiter.api.Test;

class WalkSpanTest extends JDFTestCaseBase
{
	@Test
	void testRoundTrip()
	{
		final JDFElement root = runRoundTrip("walkspanj3");
		assertNotNull(root);
		assertTrue(root.isValid(EnumValidationLevel.Complete));
	}

	@Test
	void testWalk()
	{
		final JDFInsertingIntent insertingIntent = (JDFInsertingIntent) new JDFDoc(ElementName.INSERTINGINTENT).getRoot();
		final JDFSpanBase method = insertingIntent.appendMethod();
		method.setAttribute(AttributeName.ACTUAL, "BlowIn");

		final JDFToXJDF converter = new JDFToXJDF();
		converter.setSpanAsAttribute(true);
		final WalkSpan walker = new WalkSpan();
		walker.setParent(converter);

		assertTrue(walker.matches(method));
		assertNull(walker.getElementNames());

		final KElement xjdfIntent = new JDFDoc("AssemblingIntent").getRoot();
		final KElement walked = walker.walk(method, xjdfIntent);
		assertNull(walked);
		assertEquals("BlowIn", xjdfIntent.getAttribute(method.getLocalName()));
	}

	@Test
	void testWalkInvertSpan()
	{
		final JDFInsertingIntent insertingIntent = (JDFInsertingIntent) new JDFDoc(ElementName.INSERTINGINTENT).getRoot();
		final JDFSpanBase method = insertingIntent.appendMethod();
		method.setDataType(JDFSpanBase.EnumDataType.NameSpan);
		method.setAttribute(AttributeName.ACTUAL, "SaddleStitch");

		final JDFToXJDF converter = new JDFToXJDF();
		converter.setSpanAsAttribute(false);
		converter.setRetainAll(false);
		final WalkSpan walker = new WalkSpan();
		walker.setParent(converter);

		final KElement xjdfIntent = new JDFDoc("AssemblingIntent").getRoot();
		final KElement walked = walker.walk(method, xjdfIntent);

		assertNotNull(walked);
		assertNotNull(walked.getLocalName());
		assertEquals(method.getLocalName(), walked.getAttribute(AttributeName.NAME));
		assertEquals("SaddleStitch", walked.getAttribute(AttributeName.ACTUAL));
		assertFalse(walked.hasAttribute(AttributeName.DATATYPE));
	}

	@Test
	void testWalkRetainAllFallsBackToSuper()
	{
		final JDFInsertingIntent insertingIntent = (JDFInsertingIntent) new JDFDoc(ElementName.INSERTINGINTENT).getRoot();
		final JDFSpanBase method = insertingIntent.appendMethod();
		method.setAttribute(AttributeName.ACTUAL, "Wire");

		final JDFToXJDF converter = new JDFToXJDF();
		converter.setSpanAsAttribute(false);
		converter.setRetainAll(true);
		final WalkSpan walker = new WalkSpan();
		walker.setParent(converter);

		final KElement xjdfIntent = new JDFDoc("AssemblingIntent").getRoot();
		final KElement walked = walker.walk(method, xjdfIntent);

		assertNotNull(walked);
		assertEquals(method.getLocalName(), walked.getLocalName());
	}

	@Test
	void testWalkSpanAsAttributeWithoutActual()
	{
		final JDFInsertingIntent insertingIntent = (JDFInsertingIntent) new JDFDoc(ElementName.INSERTINGINTENT).getRoot();
		final JDFSpanBase method = insertingIntent.appendMethod();

		final JDFToXJDF converter = new JDFToXJDF();
		converter.setSpanAsAttribute(true);
		final WalkSpan walker = new WalkSpan();
		walker.setParent(converter);

		final KElement xjdfIntent = new JDFDoc("AssemblingIntent").getRoot();
		final KElement walked = walker.walk(method, xjdfIntent);

		assertNull(walked);
		assertFalse(xjdfIntent.hasAttribute(method.getLocalName()));
	}

	@SuppressWarnings("deprecation")
	private JDFElement runRoundTrip(final String fileBase)
	{
		final XJDFHelper helper = new XJDFHelper(fileBase, "p1");
		helper.setTypes(EnumType.Product.getName());
		final IntentHelper intentHelper = helper.getCreateRootProduct(0).getCreateIntent(EIntentType.LaminatingIntent);
		intentHelper.getCreateResource().setAttribute(AttributeName.SURFACE, "Front");

		final SetHelper setHelper = helper.getCreateSet(ElementName.NODEINFO, EnumUsage.Input, null);
		final ResourceHelper resourceHelper = setHelper.appendPartition(null, true);
		final PartitionHelper partitionHelper = new PartitionHelper(resourceHelper.getRoot());
		assertNotNull(partitionHelper.getCreateResource());

		return writeRoundTripX(helper, fileBase, EnumValidationLevel.Complete, true);
	}
}
