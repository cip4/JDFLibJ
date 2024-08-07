/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.resource.process.JDFAssemblySection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WalkAssemblySectionTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	void testWalkElements()
	{
		final JDFAssemblySection as = (JDFAssemblySection) new JDFDoc(ElementName.ASSEMBLYSECTION).getRoot();
		as.appendAssemblySection();
		as.appendPageAssignedList();
		final WalkAssemblySection wa = new WalkAssemblySection();
		wa.setParent(new JDFToXJDF());
		final KElement root = new JDFDoc(ElementName.ASSEMBLY).getRoot();
		wa.walk(as, root);
		final JDFAssemblySection as2 = (JDFAssemblySection) root.getElement(ElementName.ASSEMBLYSECTION);
		Assertions.assertNotNull(as2);
		final JDFAssemblySection ass2 = as.getAssemblySection(0);
		Assertions.assertNotNull(ass2);
		Assertions.assertNull(as2.getPageAssignedList(0));
	}

	/**
	 *
	 */
	@Test
	void testWalkAttributes()
	{
		final JDFAssemblySection as = (JDFAssemblySection) new JDFDoc(ElementName.ASSEMBLYSECTION).getRoot();
		as.setJobID("j");
		final WalkAssemblySection wa = new WalkAssemblySection();
		wa.setParent(new JDFToXJDF());
		final KElement root = new JDFDoc(ElementName.ASSEMBLYSECTION).getRoot();
		wa.walk(as, root);
		final JDFAssemblySection as2 = (JDFAssemblySection) root.getElement(ElementName.ASSEMBLYSECTION);
		Assertions.assertNull(as2.getNonEmpty(AttributeName.JOBID));
	}

	/**
	 *
	 */
	@Test
	void testWalkAttributesDesc()
	{
		final JDFAssemblySection as = (JDFAssemblySection) new JDFDoc(ElementName.ASSEMBLYSECTION).getRoot();
		as.setJobID("j");
		as.setDescriptiveName("D");
		as.setAttribute(AttributeName.PRODUCTID, "X");
		final WalkAssemblySection wa = new WalkAssemblySection();
		wa.setParent(new JDFToXJDF());
		final KElement root = new JDFDoc(ElementName.ASSEMBLYSECTION).getRoot();
		wa.walk(as, root);
		final JDFAssemblySection as2 = (JDFAssemblySection) root.getElement(ElementName.ASSEMBLYSECTION);
		Assertions.assertNull(as2.getNonEmpty(AttributeName.JOBID));
		Assertions.assertEquals("D", as2.getDescriptiveName());
		Assertions.assertEquals("X", as2.getAttribute(XJDFConstants.ExternalID));
	}

	/**
	 *
	 */
	@Test
	void testMatches()
	{
		final JDFAssemblySection as = (JDFAssemblySection) new JDFDoc(ElementName.ASSEMBLYSECTION).getRoot();
		final WalkAssemblySection wa = new WalkAssemblySection();
		wa.setParent(new JDFToXJDF());
		Assertions.assertTrue(wa.matches(as));
	}

	/**
	 *
	 */
	@Test
	void testGetElementNames()
	{
		final JDFAssemblySection as = (JDFAssemblySection) new JDFDoc(ElementName.ASSEMBLYSECTION).getRoot();
		final WalkAssemblySection wa = new WalkAssemblySection();
		wa.setParent(new JDFToXJDF());
		Assertions.assertTrue(wa.matches(as));
		Assertions.assertTrue(wa.getElementNames().contains(as.getLocalName()));
	}

}
