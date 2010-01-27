/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPosition;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class XJDFLayoutStripTest extends XJDFCreatorTest
{
	private SetHelper losh;
	private SetHelper bssh;

	/**
	 * 
	 */
	public void testStripLayout_BSSep()
	{
		bssh = theHelper.getCreateSet("Parameter", ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		PartitionHelper bsh = bssh.appendPartition(new JDFAttributeMap(), true);
		JDFBinderySignature bs = (JDFBinderySignature) bsh.getCreateResource();
		initBS(bs);

		for (int k = 0; k < 2; k++)
		{
			JDFAttributeMap sheetMap = getSheetMap(1);
			sheetMap.put("Side", k == 0 ? "Front" : "Back");
			PartitionHelper loh = losh.appendPartition(sheetMap, true);
			JDFLayout lo = (JDFLayout) loh.getResource();

			for (int i = 0; i < 8; i++)
			{
				initContentObject(lo, k * 8 + i);
			}
		}
		PartitionHelper loh = losh.appendPartition(getSheetMap(1), true);
		JDFLayout lo = (JDFLayout) loh.getResource();
		for (int i = 0; i < 2; i++)
		{
			JDFPosition pos = (JDFPosition) lo.appendElement(ElementName.POSITION);
			pos.setID("pos" + i);
			if (i == 0)
				pos.setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));
			else
				pos.setRelativeBox(new JDFRectangle(0.5, 0, 1, 1));

			pos.setAttribute("AssemblyIDs", "CutSheet1");
			pos.setAttribute("BinderySignatureRef", bsh.getPartition().getID());
		}

		theHelper.writeToFile(sm_dirTestDataTemp + "loStrip.xjdf");
	}

	/**
	 * @param lo
	 * @param i
	 * @return 
	 */
	private JDFContentObject initContentObject(JDFLayout lo, int i)
	{
		JDFContentObject co = lo.appendContentObject();
		co.setAttribute("PositionRef", "pos" + (i % 8) / 4);
		co.setAttribute("PageIndex", i, null);
		return co;
	}

	/**
	 * @param bs
	 */
	private void initBS(JDFBinderySignature bs)
	{
		bs.setFoldCatalog("F8-4");
		bs.appendElement("StripCellParams");
	}

	/**
	 * 
	 */
	public void testStripLayout_AllinOne()
	{

		for (int k = 0; k < 2; k++)
		{
			JDFAttributeMap sheetMap = getSheetMap(1);
			sheetMap.put("Side", k == 0 ? "Front" : "Back");
			PartitionHelper loh = losh.appendPartition(sheetMap, true);
			JDFLayout lo = (JDFLayout) loh.getResource();

			for (int i = 0; i < 8; i++)
			{
				initContentObject(lo, k * 8 + i);
			}
		}
		PartitionHelper loh = losh.appendPartition(getSheetMap(1), true);
		JDFLayout lo = (JDFLayout) loh.getResource();
		JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
		initBS(bs);

		for (int i = 0; i < 2; i++)
		{
			JDFPosition pos = (JDFPosition) bs.appendElement(ElementName.POSITION);
			pos.setID("pos" + i);
			if (i == 0)
				pos.setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));
			else
				pos.setRelativeBox(new JDFRectangle(0.5, 0, 1, 1));

			pos.setAttribute("AssemblyIDs", "CutSheet1");
		}

		theHelper.writeToFile(sm_dirTestDataTemp + "loStrip1.xjdf");
	}

	/**
	 * 
	 */
	public void testStripLayout_verbose()
	{
		for (int k = 0; k < 2; k++)
		{
			JDFAttributeMap sheetMap = getSheetMap(1);
			sheetMap.put("Side", k == 0 ? "Front" : "Back");
			PartitionHelper loh = losh.appendPartition(sheetMap, true);
			JDFLayout lo = (JDFLayout) loh.getResource();

			for (int i = 0; i < 8; i++)
			{
				JDFContentObject co = initContentObject(lo, k * 8 + i);
				co.appendElement("StripCellParams");
				co.appendElement("SignatureCell");
				co.setXMLComment("we would probably simply merge signaturecell ans stripcellparams into each co");

			}
		}
		PartitionHelper loh = losh.appendPartition(getSheetMap(1), true);
		JDFLayout lo = (JDFLayout) loh.getResource();
		JDFBinderySignature bs = (JDFBinderySignature) lo.appendElement(ElementName.BINDERYSIGNATURE);
		initBS(bs);
		bs.removeChild("StripCellParams", null, 0);

		for (int i = 0; i < 2; i++)
		{
			JDFPosition pos = (JDFPosition) bs.appendElement(ElementName.POSITION);
			pos.setID("pos" + i);
			if (i == 0)
				pos.setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));
			else
				pos.setRelativeBox(new JDFRectangle(0.5, 0, 1, 1));

			pos.setAttribute("AssemblyIDs", "CutSheet1");
		}

		theHelper.writeToFile(sm_dirTestDataTemp + "loStripVerbose.xjdf");
	}

	/**
	 * @param i
	 * @return
	 */
	private JDFAttributeMap getSheetMap(int i)
	{
		JDFAttributeMap map = new JDFAttributeMap();
		map.put("SheetName", "Sheet" + i);
		return map;
	}

	/**
	 * @see org.cip4.jdflib.extensions.XJDFCreatorTest#setUp()
	 * @throws Exception
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		theHelper.getRoot().setXMLComment("create a stripping in a layout\n Stripping now consumes a layout");
		theHelper.getRoot().setAttribute("Types", "Imposition");

		losh = theHelper.getCreateSet("Parameter", ElementName.LAYOUT, EnumUsage.Input);
		bssh = null;
	}

}
