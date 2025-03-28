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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.extensions.MessageHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.XJMFHelper;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.jupiter.api.Test;

class XJDFPrepWalkerTest
{

	/**
	 *
	 */
	@Test
	void testResourceInfo()
	{
		final XJMFHelper h = new XJMFHelper();
		final MessageHelper mh = h.appendMessage(EnumFamily.Response, EnumType.Resource);
		final JDFResourceInfo ri = (JDFResourceInfo) mh.appendElement(ElementName.RESOURCEINFO);
		ri.setScope(org.cip4.jdflib.auto.JDFAutoResourceInfo.EnumScope.Present);

		final SetHelper sh = new SetHelper(ri.appendElement(XJDFConstants.ResourceSet));
		sh.setName(ElementName.MEDIA);
		sh.setUsage(EnumUsage.Input);
		for (int i = 0; i < 2; i++)
		{
			final ResourceHelper p = sh.appendPartition(null, true);
			p.setDescriptiveName("paper " + i);
			final JDFMedia m = (JDFMedia) p.getResource();
			m.setMediaType(EnumMediaType.Paper);
			m.setWeight(80 + 20 * i);
		}
		final XJDFPrepWalker w = new XJDFPrepWalker(h);
		w.convert();
		assertEquals(2, mh.getRoot().numChildElements(ElementName.RESOURCEINFO, null));
	}

	/**
	 *
	 */
	@Test
	void testRoot()
	{
		final XJMFHelper h = new XJMFHelper();
		h.setVersion(EnumVersion.Version_1_1);

		final XJDFPrepWalker w = new XJDFPrepWalker(h);
		w.convert();
		assertNull(h.getRoot().getNonEmpty(AttributeName.VERSION));
		h.setVersion(EnumVersion.Version_2_0);
		w.convert();
		assertEquals(EnumVersion.Version_2_0, h.getVersion());

	}

	/**
	 *
	 */
	@Test
	void testRoot2()
	{
		final XJDFHelper h = new XJDFHelper("j1", "p1", null);
		h.setVersion(EnumVersion.Version_1_1);

		final XJDFPrepWalker w = new XJDFPrepWalker(h);
		w.convert();
		assertNull(h.getRoot().getNonEmpty(AttributeName.VERSION));
		h.setVersion(EnumVersion.Version_2_0);
		w.convert();
		assertEquals(EnumVersion.Version_2_0, h.getVersion());

	}

}
