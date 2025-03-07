/*
 *
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

package org.cip4.jdflib.resource.process;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoPart.EnumPreviewType;
import org.cip4.jdflib.auto.JDFAutoPreview.EnumPreviewUsage;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         19.11.2008
 */
class JDFPreviewTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public final void testGenericPreview()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setVersion(EnumVersion.Version_1_4);
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, EnumUsage.Input);
		Assertions.assertTrue(m.isValid(EnumValidationLevel.Incomplete));
		final JDFPreview pv = m.appendPreview();
		Assertions.assertTrue(n.isValid(EnumValidationLevel.Incomplete));
		pv.makeRootResource(null, null, true);
		Assertions.assertTrue(n.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 */
	@Test
	public final void testSetPreviewType()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setVersion(EnumVersion.Version_1_4);
		final JDFPreview pv = (JDFPreview) n.addResource(ElementName.PREVIEW, EnumUsage.Input);
		final JDFResource pv1 = pv.addPartition(EnumPartIDKey.SignatureName, "v");
		pv.setPreviewType(EnumPreviewType.Separation);
		Assertions.assertEquals(1, pv.getPartIDKeys().size());
		pv1.setPreviewType(EnumPreviewType.Separation);
		Assertions.assertEquals(1, pv.getPartIDKeys().size());
	}

	/**
	 *
	 */
	@Test
	public final void testGetCreateFileSpec()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setVersion(EnumVersion.Version_1_4);
		final JDFPreview pv = (JDFPreview) n.addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv.setFileSpecURL("abc");
		Assertions.assertEquals("abc", pv.getFileSpec().getURL());
	}

	/**
	 *
	 */
	@Test
	public final void testSetPreviewFileType()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setVersion(EnumVersion.Version_1_4);
		final JDFPreview pv = (JDFPreview) n.addResource(ElementName.PREVIEW, EnumUsage.Input);
		pv.setPreviewFileType("AAA");
		Assertions.assertNull(pv.getEnumPreviewFileType());
	}

	/**
	 *
	 */
	@Test
	public final void testWriteDefault()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setVersion(EnumVersion.Version_1_4);
		final JDFPreview pv = n.appendPreview();
		pv.setPreviewUsage(EnumPreviewUsage.Separation);
		final String s = n.getOwnerDocument_JDFElement().write2String(2);
		Assertions.assertTrue(s.indexOf(EnumPreviewUsage.Separation.getName()) > 0);
	}

	/**
	 *
	 */
	@Test
	void testURLInput()
	{
		JDFPreview fs = (JDFPreview) JDFElement.createRoot(ElementName.PREVIEW);
		assertNull(fs.getURLInputStream());
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		JDFAudit.setStaticAuthor(null);
		super.setUp();
	}
}
