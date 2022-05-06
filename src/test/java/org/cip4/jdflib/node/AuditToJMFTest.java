/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
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
 */
package org.cip4.jdflib.node;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.util.StatusCounter;
import org.cip4.jdflib.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         14.11.2008
 */
public class AuditToJMFTest extends JDFTestCaseBase
{
	JDFNode node = null;

	/**
	 *
	 */
	@Test
	public void testGetLocalJMFs()
	{
		final AuditToJMF aj = new AuditToJMF(node, null, true);
		final VElement vJMF = aj.getLocalJMFs(EnumAuditType.PhaseTime);
		Assertions.assertEquals(vJMF.size(), 3);
		for (int i = 0; i < vJMF.size(); i++)
		{
			final JDFJMF jmf = (JDFJMF) vJMF.get(i);
			jmf.getOwnerDocument_JDFElement().write2File(sm_dirTestDataTemp + "auditToJMF.jmf", 2, false);
			Assertions.assertTrue(jmf.isValid(EnumValidationLevel.Complete));
		}
	}

	/**
	 * @throws Exception any exception
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		// get the JDF document root element
		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		node = jdfDoc.getJDFRoot();
		node.setJobID("j1");
		node.setType(EnumType.ImageSetting);
		node.addResource("ExposedMedia", EnumUsage.Output);
		node.addResource("Media", EnumUsage.Input);

		final StatusCounter sc = new StatusCounter(node, null, null);
		sc.setPhase(EnumNodeStatus.Setup, "s1", EnumDeviceStatus.Setup, "sd1");
		sc.addPhase("Input", 3, 0, false);
		sc.setPhase(EnumNodeStatus.InProgress, "ip1", EnumDeviceStatus.Running, "r1");
		sc.addPhase("Input", 4, 0, false);
		sc.setEvent("e1", "foobar", StringUtil.getRandomString());
		sc.setPhase(EnumNodeStatus.InProgress, "ip2", EnumDeviceStatus.Running, "r1");
		sc.addPhase("Input", 4, 0, false);
		sc.setPhase(EnumNodeStatus.Completed, "done", EnumDeviceStatus.Idle, "done");

	}

}
