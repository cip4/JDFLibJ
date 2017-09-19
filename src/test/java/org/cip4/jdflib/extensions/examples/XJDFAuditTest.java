/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.AuditPoolHelper;
import org.cip4.jdflib.extensions.MessageResourceHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFAuditTest extends JDFTestCaseBase
{
	/**
	 *
	 */
	@Test
	public void testResourceAudit()
	{
		XJDFHelper xjdfHelper = new XJDFHelper("PaperAudit", null, null);
		xjdfHelper.setTypes(EnumType.ConventionalPrinting.getName());
		SetHelper shMedia = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.MEDIA, null);
		SetHelper shComp = xjdfHelper.getCreateSet(XJDFConstants.Resource, ElementName.COMPONENT, EnumUsage.Input);

		ResourceHelper rhM = shMedia.appendPartition(AttributeName.SHEETNAME, "S1", true);
		ResourceHelper rhC = shComp.appendPartition(AttributeName.SHEETNAME, "S1", true);
		rhC.setAmount(400, null, true);
		JDFMedia m = (JDFMedia) rhM.getResource();
		m.setWeight(80);
		m.setMediaType(EnumMediaType.Paper);
		JDFComponent comp = (JDFComponent) rhC.getResource();
		comp.setAttribute("MediaRef", rhM.ensureID());

		xjdfHelper.writeToFile(sm_dirTestDataTemp + "/xjdf/PaperAudit.xjdf");

		AuditPoolHelper ah = xjdfHelper.getCreateAuditPool();
		MessageResourceHelper crh = ah.getCreateMessageResourceHelper(shComp);
		SetHelper aSet = crh.getSet();
		rhC = aSet.getCreatePartition(AttributeName.SHEETNAME, "S1", true);
		rhC.setAmount(400, null, true);
		rhC.setAmount(21, null, false);

		MessageResourceHelper mrh = ah.getCreateMessageResourceHelper(shMedia);
		m = (JDFMedia) mrh.getSet().getCreatePartition(null, true).getResource();
		m.setWeight(90);
		m.setMediaType(EnumMediaType.Paper);
		xjdfHelper.cleanUp();

		setSnippet(xjdfHelper, true);
		setSnippet(xjdfHelper.getSet(ElementName.NODEINFO, 0), false);
		writeTest(xjdfHelper, "structure/PaperAuditActual.xjdf");
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
	}
}
