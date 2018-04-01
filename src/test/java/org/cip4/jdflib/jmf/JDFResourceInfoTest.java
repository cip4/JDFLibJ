/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of
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
package org.cip4.jdflib.jmf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoResourceInfo;
import org.cip4.jdflib.auto.JDFAutoResourceLink;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 * @date Jan 22, 2013
 */
public class JDFResourceInfoTest extends JDFTestCaseBase
{
	/**
	 *
	 *
	 */
	@Test
	public void testCreateCopy()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFLayout l = (JDFLayout) n.addResource("Layout", EnumUsage.Input);
		l.appendMedia().makeRootResource(null, null, true);
		final JDFResourceLink rlLO = n.getLink(l, null);

		final JDFJMF jmf = new JDFDoc("JMF").getJMFRoot();
		final JDFSignal s = jmf.appendSignal(EnumType.Resource);

		final JDFResourceInfo ri = JDFResourceInfo.createResourceInfo(s, rlLO, true);
		assertEquals(ri.getResourceName(), "Layout");
		assertNotNull(ri.getResource(null).getElement("Media"));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testCreateNoCopy()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		final JDFLayout l = (JDFLayout) n.addResource("Layout", EnumUsage.Input);
		l.appendMedia().makeRootResource(null, null, true);
		final JDFResourceLink rlLO = n.getLink(l, null);

		final JDFJMF jmf = new JDFDoc("JMF").getJMFRoot();
		final JDFSignal s = jmf.appendSignal(EnumType.Resource);

		final JDFResourceInfo ri = JDFResourceInfo.createResourceInfo(s, rlLO, false);
		assertEquals(ri.getResourceName(), "Layout");
		assertNull(ri.getResource(null));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testOrientation()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(m, null);
		rl.setOrientation(JDFAutoResourceLink.EnumOrientation.Rotate270);
		assertTrue(rl.isValid(EnumValidationLevel.Incomplete));
		final JDFJMF jmf = new JMFBuilder().buildResourceSignal(true, rl);
		final JDFResourceInfo ri = jmf.getSignal(0).getResourceInfo(0);
		assertTrue(ri.isValid(EnumValidationLevel.Incomplete));
		assertEquals(JDFAutoResourceInfo.EnumOrientation.Rotate270, ri.getOrientation());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSetLink()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(m, null);
		rl.setOrientation(JDFAutoResourceLink.EnumOrientation.Rotate270);
		final JDFJMF jmf = new JMFBuilder().buildResourceSignal(true, null);
		final JDFResourceInfo ri = jmf.getSignal(0).getCreateResourceInfo(0);
		ri.setLink(rl, false);
		assertEquals(JDFAutoResourceInfo.EnumOrientation.Rotate270, ri.getOrientation());
	}

	/**
	 *
	 *
	 */
	@Test
	public void testSetLinkUsage()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResourceLink rl = n.getLink(m, null);
		rl.setOrientation(JDFAutoResourceLink.EnumOrientation.Rotate270);
		final JDFJMF jmf = new JMFBuilder().buildResourceSignal(true, null);
		final JDFResourceInfo ri = jmf.getSignal(0).getCreateResourceInfo(0);
		ri.setLink(rl, false);
		assertEquals(EnumUsage.Input, ri.getUsage());
	}
}
