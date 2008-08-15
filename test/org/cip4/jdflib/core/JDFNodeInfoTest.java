/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
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

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.util.JDFDuration;

/**
 * @author MuchaD
 * 
 *         This implements the first fixture with unit tests for class JDFAudit.
 */
public class JDFNodeInfoTest extends JDFTestCaseBase
{
	public void testDuration() throws Exception
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFDuration duration = new JDFDuration("PT1H20M30S");
		ni.setTotalDuration(duration);
		assertEquals(ni.getTotalDuration(), duration);
		try
		{
			ni.setCleanupDuration(new JDFDuration("PS1L20M30S"));
			fail("bad duration");
		} catch (Exception x)
		{
			// nop
		}
	}

	// ///////////////////////////////////////////////////////////////////
	public void testPartUsage()
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		JDFNodeInfo ni = n.getCreateNodeInfo();
		final JDFAttributeMap map = new JDFAttributeMap(
				EnumPartIDKey.Separation, "Cyan");
		n.setPartStatus(map, EnumNodeStatus.Ready);
		JDFNodeInfo niPart = (JDFNodeInfo) ni.getPartition(map, null);
		assertNotNull(niPart);
		assertNull(niPart.getAttribute_KElement(AttributeName.PARTUSAGE, null,
				null));
	}

	// ///////////////////////////////////////////////////////////////////
	public void testWorkstepID()
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("ConventionalPrinting", true);
		JDFNodeInfo.setDefaultWorkStepID(true);
		JDFNodeInfo ni = n.getCreateNodeInfo();
		assertTrue(ni.hasAttribute("WorkStepID"));
		final JDFAttributeMap map = new JDFAttributeMap(
				EnumPartIDKey.Separation, "Cyan");
		n.setPartStatus(map, EnumNodeStatus.Ready);
		JDFNodeInfo niPart = (JDFNodeInfo) ni.getPartition(map, null);
		assertNotNull(niPart);
		assertTrue(niPart.hasAttribute("WorkStepID"));
		d.write2File(sm_dirTestDataTemp + "workstepidtest.jdf", 2, false);
//		VString v = 
			ni.getInvalidAttributes(EnumValidationLevel.Incomplete, true, -1);
		assertTrue(ni.isValid(EnumValidationLevel.Incomplete));

	}

	// ///////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////
	public void testCPI()
	{
		JDFDoc d = new JDFDoc(ElementName.JDF);
		JDFNode n = d.getJDFRoot();
		n.setType("Combined", true);
		n.setTypes(new VString("ConventionalPrinting Folding", " "));

		JDFNodeInfo ni = n.getCreateNodeInfo();
		JDFResourceLink rl = n.getLink(ni, null);
		assertFalse(rl.hasAttribute(AttributeName.COMBINEDPROCESSINDEX));
	}

}