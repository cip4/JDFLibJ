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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.resource.process.JDFCutBlock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WalkCutBlockTest extends JDFTestCaseBase
{

	/**
	 *
	 */
	@Test
	public void testWalk()
	{
		final JDFCutBlock cbo = (JDFCutBlock) new JDFDoc(ElementName.CUTBLOCK).getRoot();
		final JDFXYPair size = new JDFXYPair(10, 20);
		cbo.setBlockSize(size);
		final WalkCutBlock walkCutBlock = new WalkCutBlock();
		walkCutBlock.setParent(new JDFToXJDF());
		final KElement root = new JDFDoc(ElementName.RESOURCE).getRoot();
		walkCutBlock.walk(cbo, root);
		final JDFCutBlock cb = (JDFCutBlock) root.getElement(ElementName.CUTBLOCK);
		final JDFRectangle box = JDFRectangle.createRectangle(cb.getNonEmpty(AttributeName.BOX));
		Assertions.assertEquals(box.getSize(), size);
		Assertions.assertEquals(box.getLL(), new JDFXYPair());
		Assertions.assertNull(cb.getBlockTrf());
		Assertions.assertNull(cb.getBlockSize());
	}

	/**
	 *
	 */
	@Test
	public void testWalkTRF()
	{
		final JDFCutBlock cbo = (JDFCutBlock) new JDFDoc(ElementName.CUTBLOCK).getRoot();
		final JDFXYPair size = new JDFXYPair(10, 20);
		cbo.setBlockSize(size);
		final JDFMatrix m = JDFMatrix.getUnitMatrix();
		final JDFXYPair shift = new JDFXYPair(400, 600);
		m.shift(shift);
		cbo.setBlockTrf(m);
		final WalkCutBlock walkCutBlock = new WalkCutBlock();
		walkCutBlock.setParent(new JDFToXJDF());
		final KElement root = new JDFDoc(ElementName.RESOURCE).getRoot();
		walkCutBlock.walk(cbo, root);
		final JDFCutBlock cb = (JDFCutBlock) root.getElement(ElementName.CUTBLOCK);
		final JDFRectangle box = JDFRectangle.createRectangle(cb.getNonEmpty(AttributeName.BOX));
		Assertions.assertEquals(box.getSize(), size);
		Assertions.assertEquals(box.getLL(), shift);
		Assertions.assertNull(cb.getBlockTrf());
		Assertions.assertNull(cb.getBlockSize());
	}

	/**
	 *
	 */
	@Test
	public void testWalkDescName()
	{
		final JDFCutBlock cbo = (JDFCutBlock) new JDFDoc(ElementName.CUTBLOCK).getRoot();
		final JDFXYPair size = new JDFXYPair(10, 20);
		cbo.setBlockSize(size);
		final JDFMatrix m = JDFMatrix.getUnitMatrix();
		final JDFXYPair shift = new JDFXYPair(400, 600);
		m.shift(shift);
		cbo.setBlockTrf(m);
		cbo.setDescriptiveName("desc name");
		cbo.setProductID("pid");
		final WalkCutBlock walkCutBlock = new WalkCutBlock();
		walkCutBlock.setParent(new JDFToXJDF());
		final KElement root = new JDFDoc(ElementName.RESOURCE).getRoot();
		walkCutBlock.walk(cbo, root);
		final JDFCutBlock cb = (JDFCutBlock) root.getElement(ElementName.CUTBLOCK);
		Assertions.assertEquals("pid", cb.getAttribute(XJDFConstants.ExternalID));
		Assertions.assertEquals("desc name", cb.getDescriptiveName());
	}

	/**
	 *
	 */
	@Test
	public void testMatches()
	{
		final JDFCutBlock cbo = (JDFCutBlock) new JDFDoc(ElementName.CUTBLOCK).getRoot();
		final WalkCutBlock wa = new WalkCutBlock();
		wa.setParent(new JDFToXJDF());
		Assertions.assertTrue(wa.matches(cbo));
	}

	/**
	 *
	 */
	@Test
	public void testGetElementNames()
	{
		final JDFCutBlock cbo = (JDFCutBlock) new JDFDoc(ElementName.CUTBLOCK).getRoot();
		final WalkCutBlock wa = new WalkCutBlock();
		Assertions.assertTrue(wa.getElementNames().contains(cbo.getLocalName()));
	}
}
