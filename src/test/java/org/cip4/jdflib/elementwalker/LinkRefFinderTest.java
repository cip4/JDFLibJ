/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.elementwalker;

import java.util.List;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.ListMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 *         Sep 3, 2009
 */
public class LinkRefFinderTest extends JDFTestCaseBase
{

	JDFNode n;
	VElement vM;

	/**
	 * Test method for {@link org.cip4.jdflib.elementwalker.LinkRefFinder#getMap(org.cip4.jdflib.node.JDFNode)}.
	 */
	@Test
	public void testGetTheMap()
	{
		final LinkRefFinder lrf = new LinkRefFinder(true, true);
		final ListMap<String, KElement> m = lrf.getMap(n);
		Assertions.assertEquals(m, lrf.getTheMap());
	}

	/**
	 * Test method for {@link org.cip4.jdflib.elementwalker.LinkRefFinder#getMap(org.cip4.jdflib.node.JDFNode)}.
	 */
	@Test
	public void testGetMap()
	{
		final long t0 = System.currentTimeMillis();

		runIt(true, true);
		runIt(true, false);
		runIt(false, true);
		final long t1 = System.currentTimeMillis();
		System.out.println(t1 - t0);
	}

	/**
	 * @param n
	 * @param vM
	 */
	private void runIt(final boolean bRef, final boolean bLink)
	{
		int nV = 0;
		if (bRef)
		{
			nV++;
		}
		if (bLink)
		{
			nV++;
		}
		final LinkRefFinder lrf = new LinkRefFinder(bRef, bLink);
		final ListMap<String, KElement> vm = lrf.getMap(n);
		for (int i = 0; i < 1000; i++)
		{
			final JDFMedia m2 = (JDFMedia) vM.get(i);
			final List<KElement> vRef = vm.get(m2.getID());
			Assertions.assertNotNull(vRef);
			Assertions.assertEquals(vRef.size(), nV, "A link and a ref");
		}
	}

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.Product);
		vM = new VElement();
		for (int i = 0; i < 1000; i++)
		{
			final JDFNode n2 = n.addJDFNode(EnumType.ImageSetting);
			final JDFNode n3 = n.addJDFNode(EnumType.ConventionalPrinting);
			final JDFMedia m = (JDFMedia) n2.addResource("Media", null, EnumUsage.Input, null, n, null, null);
			final JDFExposedMedia xm = (JDFExposedMedia) n2.addResource("ExposedMedia", null, EnumUsage.Output, null, n, null, null);
			xm.refElement(m);
			n3.linkResource(xm, EnumUsage.Input, null);
			n.getAuditPool().addModified(null, n2);
			n.getAuditPool().addModified(null, n3);
			vM.add(m);
		}

	}
}
