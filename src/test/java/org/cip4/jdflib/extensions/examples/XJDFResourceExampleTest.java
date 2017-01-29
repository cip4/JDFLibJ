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
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFRGBColor;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorantAlias;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.StringUtil;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFResourceExampleTest extends JDFTestCaseBase
{
	/**
	* tests the separationlist class
	*
	*/
	@Test
	public final void testColorantAlias()
	{
		XJDFHelper xjdfHelper = new XJDFHelper("RawName", null, null);
		xjdfHelper.setTypes(EnumType.ColorSpaceConversion.getName());
		SetHelper cch = xjdfHelper.getCreateSet(ElementName.COLORANTCONTROL, EnumUsage.Input, null);
		ResourceHelper ccrh = cch.appendPartition(null, true);
		JDFColorantControl colControl = (JDFColorantControl) ccrh.getResource();

		JDFColorantAlias ca = colControl.appendColorantAlias();
		ca.setReplacementColorantName("Spot1");
		ca.setAttribute("ColorantName", "Grün");
		ca.setAttribute(AttributeName.RAWNAME, "4772FC6E");

		ca = (JDFColorantAlias) colControl.copyElement(ca, null);
		ca.setAttribute("ColorantName", "grün");
		ca.setAttribute(AttributeName.RAWNAME, "6772FC6E");

		SetHelper colh = xjdfHelper.getCreateSet(ElementName.COLOR, EnumUsage.Input, null);
		ResourceHelper colrh = colh.appendPartition(AttributeName.SEPARATION, "Spot1", true);
		JDFColor color = (JDFColor) colrh.getResource();
		color.setsRGB(new JDFRGBColor(0, 1, 0));
		color.setActualColorName("Green");
		color.setRawName(StringUtil.setHexBinaryBytes("Green".getBytes(), -1));

		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "resources/ColorantAlias.xjdf");
	}

	/**
	* tests the separationlist class
	*
	*/
	@Test
	public final void testMultiRunList()
	{
		XJDFHelper xjdfHelper = new XJDFHelper("RunList", null, null);
		xjdfHelper.setTypes(EnumType.Imposition.getName());
		SetHelper rlh = xjdfHelper.getCreateSet(ElementName.RUNLIST, EnumUsage.Input, null);
		ResourceHelper runh = rlh.appendPartition(AttributeName.RUN, "R1", true);
		JDFRunList rl = (JDFRunList) runh.getResource();
		rl.setNPage(6);
		rl.setAttribute(AttributeName.PAGES, "0 5");
		rl.appendElement(ElementName.FILESPEC).setAttribute(AttributeName.URL, "File:///File1.pdf");
		runh = rlh.appendPartition(AttributeName.RUN, "R2", true);
		rl = (JDFRunList) runh.getResource();
		rl.setAttribute(AttributeName.PAGES, "0 -1");
		rl.appendElement(ElementName.FILESPEC).setAttribute(AttributeName.URL, "File:///File2.pdf");

		cleanSnippets(xjdfHelper);
		writeTest(xjdfHelper, "resources/RunList2.xjdf");

	}

}
