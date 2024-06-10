/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.jmf.JDFSignal;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WalkSignalResourceTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testRQP()
	{
		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Signal, EnumType.Resource);
		JDFSignal sig = jmf.getSignal(0);
		sig.appendResourceQuParams().setJobID("j1");
		JDFResourceInfo ri = sig.appendResourceInfo();

		new WalkSignalResource().moveFromQuParams(sig);
		Assertions.assertNull(sig.getResourceQuParams());
		Assertions.assertEquals("j1", ri.getAttribute(AttributeName.JOBID));
	}

	/**
	 *
	 */
	@Test
	void testJobID()
	{
		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Signal, EnumType.Resource);
		JDFSignal sig = jmf.getSignal(0);
		JDFResourceQuParams rqp = sig.appendResourceQuParams();
		rqp.setJobID("j1");
		rqp.setJobPartID("p1");
		sig.appendResourceInfo();

		KElement xjmf = new JDFToXJDF().convert(jmf);

		Assertions.assertNull(xjmf.getXPathAttribute("SignalResource/ResourceInfo/ResourceSet/@JobID", null));
		Assertions.assertNull(xjmf.getXPathAttribute("SignalResource/ResourceInfo/ResourceSet/@JobPartID", null));
		Assertions.assertEquals("j1", xjmf.getXPathAttribute("SignalResource/ResourceInfo/@JobID", null));
		Assertions.assertEquals("p1", xjmf.getXPathAttribute("SignalResource/ResourceInfo/@JobPartID", null));
	}

	/**
	 *
	 */
	@Test
	void testExposedMedia()
	{
		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Signal, EnumType.Resource);

		JDFSignal sig = jmf.getSignal(0);
		sig.appendResourceQuParams().setJobID("j1");

		JDFResourceInfo ri = sig.appendResourceInfo();
		JDFExposedMedia xm = (JDFExposedMedia) ri.appendResource(ElementName.EXPOSEDMEDIA);
		xm.setProductID("pid");
		JDFMedia media = xm.appendMedia();
		media.setBrand("plate");

		JDFToXJDF jdfToXJDF = new JDFToXJDF();
		KElement xjmf = jdfToXJDF.convert(jmf);

		KElement sr = xjmf.getElement("SignalResource");
		Assertions.assertEquals(sr.numChildElements(ElementName.RESOURCEINFO, null), 2);
		Assertions.assertEquals(sr.getChildrenByTagName(XJDFConstants.ResourceSet, null, null, false, true, 0).size(), 2);
	}

	/**
	 *
	 */
	@Test
	void testConvert()
	{
		JDFJMF jmf = JMFBuilderFactory.getJMFBuilder(null).createJMF(EnumFamily.Signal, EnumType.Resource);
		JDFSignal sig = jmf.getSignal(0);
		sig.appendResourceQuParams().setJobID("j1");
		sig.appendResourceInfo();

		KElement xjmf = new JDFToXJDF().convert(jmf);

		Assertions.assertNull(xjmf.getXPathElement("SignalResource/ResourceQuParams"));
		Assertions.assertEquals("j1", xjmf.getXPathAttribute("SignalResource/ResourceInfo/@JobID", null));
	}

}
