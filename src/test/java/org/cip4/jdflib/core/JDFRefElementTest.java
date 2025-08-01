/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of
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
package org.cip4.jdflib.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumGrainDirection;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.jupiter.api.Test;

/**
 * @author MuchaD
 * 
 *         This implements the first fixture with unit tests for class JDFElement.
 */
class JDFRefElementTest extends JDFTestCaseBase
{
	/**
	 * 
	 * tests refelements pointing to non resources
	 */
	@Test
	void testGetBadTarget()
	{
		final JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(this).buildMilestone("Foo", "1234");
		final JDFNotification notification = jmf.getSignal(0).getNotification();
		final KElement foo = notification.appendElement("Fooo");
		final String id = foo.appendAnchor(null);
		final JDFRefElement re = (JDFRefElement) notification.appendElement("FoooRef");
		re.setrRef(id);
		assertNull(re.getTarget(), "non resources are null rather than class casts...");
		assertNull(re.getTargetRoot(), "non resources are null rather than class casts...");
	}

	/**
	 * Method testGetTarget
	 * 
	 * 
	 */
	@Test
	void testGetTarget()
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SignatureName", "S12234");
		mPart.put("SheetName", "S12");
		mPart.put("Side", "Front");
		JDFExposedMedia xmPart = (JDFExposedMedia) xm.getCreatePartition(mPart, null);
		JDFMedia m = xm.getMedia();
		m = (JDFMedia) m.makeRootResource(null, null, true);
		xmPart.refElement(m);
		assertEquals(xmPart.getMedia(), m);
		mPart.put("Side", "Back");
		xmPart = (JDFExposedMedia) xm.getCreatePartition(mPart, null);
		final JDFMedia medPart = (JDFMedia) m.getCreatePartition(mPart, xm.getPartIDKeys());
		xmPart.refElement(medPart);
		assertEquals(xmPart.getMedia(), medPart);
		final JDFRefElement re = (JDFRefElement) xmPart.getElement("MediaRef");
		assertEquals(re.getPartMap(), mPart);
		assertEquals(re.getTarget(), medPart);
	}

	/**
	 * Method testGetTargetRoot
	 * 
	 * 
	 */
	@Test
	void testGetTargetRoot()
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		JDFMedia m = xm.getMedia();
		m = (JDFMedia) m.makeRootResource(null, null, true);
		xm.refElement(m);
		final JDFRefElement re = (JDFRefElement) xm.getElement("MediaRef");
		assertEquals(re.getTargetRoot(), m);
	}

	/**
	 * Method testGetTargetRoot
	 * 
	 * 
	 */
	@Test
	void testGetRefName()
	{

		assertEquals("MediaRef", JDFRefElement.getRefName("Media"));
		assertEquals("MediaRefRef", JDFRefElement.getRefName("MediaRef"));
		assertEquals("Media", JDFRefElement.getRefLocalName("MediaRef"));
		assertEquals(null, JDFRefElement.getRefLocalName("Media"));
	}

	/**
	 * Method testInlineRefelement
	 * 
	 * 
	 */
	@Test
	void testInlineRefelement()
	{
		final JDFDoc d = JDFTestCaseBase.creatXMDoc();
		final JDFNode n = d.getJDFRoot();
		final JDFExposedMedia xm = (JDFExposedMedia) n.getMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.AnyInput, null, 0);
		final JDFAttributeMap mPart = new JDFAttributeMap("SignatureName", "Sig1");
		mPart.put("SignatureName", "S12234");
		mPart.put("SheetName", "S12");
		mPart.put("Side", "Front");
		JDFExposedMedia xmPart = (JDFExposedMedia) xm.getCreatePartition(mPart, null);
		JDFMedia m = xm.getMedia();
		m = (JDFMedia) m.makeRootResource(null, null, true);
		m.setGrainDirection(EnumGrainDirection.XDirection);
		xmPart.refElement(m);
		assertEquals(xmPart.getMedia(), m);
		mPart.put("Side", "Back");
		xmPart = (JDFExposedMedia) xm.getCreatePartition(mPart, null);
		final JDFMedia medPart = (JDFMedia) m.getCreatePartition(mPart, xm.getPartIDKeys());
		xmPart.refElement(medPart);
		assertEquals(xmPart.getMedia(), medPart);
		final JDFRefElement re = (JDFRefElement) xmPart.getElement("MediaRef");
		final JDFMedia inlineMedia = (JDFMedia) re.inlineRef();
		assertNull(xmPart.getElement_KElement("MediaRef", null, 0));
		assertEquals(inlineMedia.getGrainDirection(), EnumGrainDirection.XDirection);
		assertEquals(inlineMedia, xmPart.getMedia());
		assertFalse(inlineMedia.hasAttribute("ID"));

	}
}