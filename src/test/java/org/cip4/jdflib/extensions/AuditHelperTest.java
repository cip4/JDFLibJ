/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.AuditHelper.eAudit;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
class AuditHelperTest
{

	/**
	 *
	 */
	@Test
	void testeAudit()
	{
		for (final eAudit e : eAudit.values())
		{
			assertEquals(e, eAudit.getEnum(e.name()));
			assertEquals(e, eAudit.getEnum(e.name().toLowerCase()));
			assertEquals(e, eAudit.getEnum("audit" + e.name()));
			assertEquals(e, eAudit.getEnum("auditaudit" + e.name()));
			assertEquals(null, eAudit.getEnum("audit " + e.name()));
		}
	}

	/**
	 *
	 */
	@Test
	void testeAuditName()
	{
		for (final eAudit e : eAudit.values())
		{
			assertEquals(e, eAudit.getEnum(e.getAuditName()));
		}
	}

	/**
	 *
	 */
	@Test
	void testCleanup()
	{
		final KElement audit = KElement.createRoot(ElementName.CREATED, null);
		final MessageHelper ah = new MessageHelper(audit);
		ah.appendElement(XJDFConstants.Header);
		ah.cleanUp();
		assertNotNull(ah);
	}

	/**
	 *
	 */
	@Test
	void testCleanupResource()
	{
		final KElement audit = KElement.createRoot(XJDFConstants.AuditResource, null);
		final MessageHelper ah = new MessageHelper(audit);
		final KElement header = ah.appendElement(XJDFConstants.Header);
		final KElement rs = ah.appendElement(XJDFConstants.ResourceSet);
		ah.cleanUp();
		assertEquals(header.getNextSiblingElement(), rs);
	}

	/**
	 *
	 */
	@Test
	void testIsAudit()
	{
		final KElement audit = KElement.createRoot(XJDFConstants.AuditResource, null);
		assertTrue(AuditHelper.isAudit(audit));
		final KElement audit2 = KElement.createRoot(XJDFConstants.AuditResource + "2", null);
		assertFalse(AuditHelper.isAudit(audit2));
	}

	/**
	 *
	 */
	@Test
	void testXJMFResource()
	{
		final XJDFHelper h = new XJDFHelper("j1", null);
		final AuditResourceHelper ah = (AuditResourceHelper) h.getCreateAuditPool().appendMessage(XJDFConstants.AuditResource);
		final SetHelper rs = ah.appendSet(ElementName.RUNLIST);
		rs.appendPartition("Run", "R1", true).getResource().appendElement(ElementName.FILESPEC).setAttribute(AttributeName.URL, "u");
		final XJMFHelper xjmf = ah.makeXJMFSignal();
		assertEquals("u", xjmf.getMessageHelper(0).getRoot().getXPathAttribute("ResourceInfo/ResourceSet/Resource/RunList/FileSpec/@URL", null));
	}

}
