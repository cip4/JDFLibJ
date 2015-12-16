/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of 
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

package org.cip4.jdflib.resource.process;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFTool;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG 13.11.2008
 */
public class JDFTileTest extends JDFTestCaseBase
{

	/**
	 * Test method 
	 */
	@Test
	public final void testMarkObject()
	{
		final JDFDoc doc = new JDFDoc("JDF");
		final JDFNode root = doc.getJDFRoot();
		root.setVersion(EnumVersion.Version_1_4);
		root.setType(EnumType.Tiling);
		final JDFResourcePool resPool = root.getCreateResourcePool();
		final KElement kElem = resPool.appendResource(ElementName.TILE, null, null);
		assertTrue(kElem instanceof JDFTile);
		final JDFTile tile = ((JDFTile) kElem);
		try
		{
			tile.setClipBox(new JDFRectangle("0 0 123 123"));
			tile.setCTM(new JDFMatrix("1 0 0 1 0 0"));
			final JDFMarkObject m = tile.appendMarkObject();
			m.setOrd(0);
			m.setCTM(new JDFMatrix("1.2 0 0 1.4 1 444."));
			assertTrue(m.isValid(EnumValidationLevel.Complete));
			final JDFMarkObject m2 = tile.appendMarkObject();
			m2.setOrd(0);
			m2.setCTM(new JDFMatrix("1.234 0 0 1.4 1 444."));
			assertTrue(m2.isValid(EnumValidationLevel.Complete));
			assertEquals(m2, tile.getMarkObject(1));
			assertEquals(m2, tile.getCreateMarkObject(1));
			assertTrue(tile.isValid(EnumValidationLevel.Complete));
		}
		catch (final DataFormatException e)
		{
			fail("bad unit matrix");
		}
	}

	/**
	 * 
	 *  
	 */
	public void testPartition()
	{
		final JDFNode root = new JDFDoc("JDF").getJDFRoot();
		root.setVersion(EnumVersion.Version_1_5);
		root.setType(EnumType.Tiling);
		JDFTile tile = (JDFTile) root.appendMatchingResource(ElementName.TILE, EnumUsage.Input);
		tile.appendMedia();
		tile.appendMarkObject().setCTM(JDFMatrix.unitMatrix);
		tile.setClipBox(new JDFRectangle(11, 1, 1, 1));
		tile.setCTM(JDFMatrix.unitMatrix);
		tile.setTrimBox(new JDFRectangle(11, 1, 1, 1));
		for (int i = 0; i < 16; i++)
		{
			JDFTile partTile = (JDFTile) tile.addPartition(EnumPartIDKey.TileID, new JDFXYPair(i % 4, i / 4).getString(0));
			assertNotNull(partTile);
			partTile.appendMarkObject().setCTM(JDFMatrix.unitMatrix);
			partTile.appendMedia();
			partTile.setClipBox(new JDFRectangle(11, 1, 1, 1));
			partTile.setCTM(JDFMatrix.unitMatrix);
			partTile.setTrimBox(new JDFRectangle(11, 1, 1, 1));
		}
		checkSchema(root, EnumValidationLevel.Incomplete);
	}

	/**
	* 
	*  
	*/
	public void testPartitionTool()
	{
		final JDFNode root = new JDFDoc("JDF").getJDFRoot();
		root.setVersion(EnumVersion.Version_1_4);
		root.setType(EnumType.Tiling);
		JDFTool tile = (JDFTool) root.appendMatchingResource(ElementName.TOOL, EnumUsage.Input);
		for (int i = 0; i < 16; i++)
		{
			JDFTool partTile = (JDFTool) tile.addPartition(EnumPartIDKey.TileID, new JDFXYPair(i % 4, i / 4).getString(0));
			assertNotNull(partTile);
		}
		String string = root.getOwnerDocument_JDFElement().write2String(2);
		JDFParser jdfParser = getSchemaParser();
		JDFDoc d = jdfParser.parseString(string);
		assertEquals(d.getValidationResult().getRoot().getAttribute("ValidationResult"), "Valid");
	}
}
