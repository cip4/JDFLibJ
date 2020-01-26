/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

package org.cip4.jdflib.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFSignatureCell;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.junit.Test;

/**
 * all kinds of fun tests around Stripping also some tests for automated layout
 *
 */
public class JDFStrippingTest extends JDFTestCaseBase
{

	private JDFDoc doc = null;
	private JDFNode n = null;
	private JDFRunList rl = null;
	private JDFStrippingParams sp = null;
	private JDFBinderySignature bs = null;

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 * @throws Exception
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		doc = new JDFDoc("JDF");
		n = doc.getJDFRoot();
		n.setType(EnumType.Stripping);
		rl = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
		sp = (JDFStrippingParams) n.appendMatchingResource(ElementName.STRIPPINGPARAMS, EnumProcessUsage.AnyInput, null);
		bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, null, null, null, null, null, null);
		n.addResource("Layout", EnumUsage.Output);
		sp.refBinderySignature(bs);
	}

	/**
	 *
	 *
	 */
	@Test
	public void testAppendAssemblyID()
	{
		final JDFNode n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setType(EnumType.Stripping);
		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		sp.appendAssemblyIDs("BS1", true);
		sp.appendAssemblyIDs("BS2", true);
		sp.appendAssemblyIDs("BS2", true);
		assertTrue(sp.getAssemblyIDs().contains("BS1"));
		assertTrue(sp.getAssemblyIDs().contains("BS2"));
		assertEquals(2, sp.getAssemblyIDs().size());
		assertTrue(sp.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 *
	 *
	 */
	@Test
	public void testPositionValid()
	{
		final JDFNode n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.Stripping);
		final JDFStrippingParams sp = (JDFStrippingParams) n.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		sp.appendPosition();
		assertTrue(sp.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 * test how foldouts are generated
	 *
	 * @throws Exception
	 */
	@Test
	public void testFoldOut() throws Exception
	{
		// 0=1.3; 2=1.4
		for (int i = 0; i < 3; i++)
		{
			if (i == 1)
				continue; // rejected by wg
			setUp();
			n.setXMLComment("Stripping Foldout example corresponding to spec example n.6.5 - verion: "
					+ ((i == 0) ? "multi-Cell" : ((i == 1) ? "new attribute FoldOutTrimSize" : "new attribute FaceCells (Accepted for 1.4)")), true);
			rl.setNPage(6);
			sp.setResStatus(EnumResStatus.Available, true);
			bs.setResStatus(EnumResStatus.Available, true);
			JDFSignatureCell sc = bs.appendSignatureCell();
			sc.setXMLComment("this is the foldout foldout cell", true);
			sc.setFrontPages(new JDFIntegerList("4"));
			sc.setBackPages(new JDFIntegerList("3"));
			if (i == 0 || i == 2)
			{
				sc = bs.appendSignatureCell();
				String xmlComment = "this cell is the inner page of the foldout, i.e. the page that is attached to the spine";
				sc.setFrontPages(new JDFIntegerList("5"));
				sc.setBackPages(new JDFIntegerList("2"));
				if (i == 0)
				{
					sc.setFrontFacePages(new JDFIntegerList("4"));
					sc.setBackFacePages(new JDFIntegerList("3"));
				}
				else
				{
					xmlComment += "\nThe new attribute FaceCells refers to the cell(s) that describe the foldout; in this case the cell to the left.";
					xmlComment += "\nThe front and back pages of the foldout are listed in the respective cell(s)";
					sc.setAttribute("FaceCells", "0");
				}
				sc.setXMLComment(xmlComment, true);

			}

			sc = bs.appendSignatureCell();
			sc.setXMLComment("this is the cell that has no foldout", true);
			sc.setFrontPages(new JDFIntegerList("0"));
			sc.setBackPages(new JDFIntegerList("1"));

			JDFStrippingParams sp1 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.CellIndex, "0");
			JDFStripCellParams scp = sp1.appendStripCellParams();
			scp.setTrimSize(new JDFXYPair(200 + (i % 2) * 300, 400));
			if (i != 1)
				scp.setXMLComment("stripcell for the folded out foldout(front page=4)", true);
			else
				scp.setXMLComment(
						"stripcell for the entire foldout(front page=4, foldout page =5)\nthe TrimSize applies to the entire foldout spread (page 4 and 5)\n note the new FoldoutTrimSize attribute",
						true);

			if (i != 1)
			{
				sp1 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.CellIndex, "1");
				scp = sp1.appendStripCellParams();
				scp.setTrimSize(new JDFXYPair(300, 400));
				scp.setXMLComment("stripcell for the inner page of the foldout foldout(front page=5)", true);
			}
			else
			{
				scp.setAttribute("FoldoutTrimSize", "200 400");
			}

			sp1 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.CellIndex, i != 1 ? "2" : "1");
			scp = sp1.appendStripCellParams();
			scp.setTrimSize(new JDFXYPair(320, 400));
			scp.setXMLComment("stripcell for the inner page of the foldout foldout(front page=0)", true);

			doc.write2File(sm_dirTestDataTemp + "foldoutStrip" + i + ".jdf", 2, false);
		}

	}
}