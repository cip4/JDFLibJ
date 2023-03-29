/*
 * The CIP4 Software License, Version 1.0
 *
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
/*
 * JDFExampleDocTest.java
 *
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFDieLayout;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StatusCounter;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen tests for CAD production
 */
public class CADTest extends JDFTestCaseBase
{
	/**
	 * @see junit.framework.TestCase#toString()
	 * @return the String
	 */
	@Override
	public String toString()
	{
		String s = super.toString();
		if (n != null)
			s += "\n" + n.toString();
		return s;
	}

	private JDFNode n;
	private JDFDoc d;

	/**
	 * tests the creation of the initial shapedefproduction (one up) process
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCompletedJMF()
	{
		createShapeDefProduction();
		final StatusCounter sc = new StatusCounter(null, null, null);
		sc.setDeviceID("EngView");
		JDFDoc dJMF = sc.getDocJMFPhaseTime();
		writeTest(dJMF, "Idle.jmf");
		sc.setActiveNode(n, null, null);
		sc.setPhase(EnumNodeStatus.InProgress, "Processing", EnumDeviceStatus.Running, "inUse");
		dJMF = sc.getDocJMFPhaseTime();
		writeTest(dJMF, "Running.jmf");
		sc.setPhase(EnumNodeStatus.Completed, null, EnumDeviceStatus.Idle, null);
		dJMF = sc.getDocJMFPhaseTime();
		writeTest(dJMF, "Completed.jmf");
		sc.setActiveNode(null, null, null);

		writeTest(dJMF, "CompletedShapeDef.jmf");

	}

	/**
	 * tests the creation of the initial shapedefproduction (one up) process
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShapeDefProduction()
	{
		createShapeDefProduction();

		writeTest(n, "CAD_ShapeDefProduction.jdf", true, null);
	}

	/**
	 * tests the creation of the initial shapedefproduction (one up) process
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShapeDefLWD()
	{
		d = new JDFDoc(ElementName.JDF);
		n = d.getJDFRoot();
		n.setType("ShapeDefProduction", false);
		final JDFResource sdpp = n.addResource("ShapeDefProductionParams", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		sdpp.setDescriptiveName("Box123");
		setSnippet(sdpp, true);
		final JDFShape shape = new JDFShape(20 * 72 / 2.54, 25 * 72 / 2.54, 10 * 72 / 2.54);
		final JDFElement shapeTemplate = (JDFElement) sdpp.appendElement("ShapeTemplate");
		shapeTemplate.setAttribute("InnerDimensions", shape.toString());
		shapeTemplate.setDescriptiveName("20 * 25 * 10 cm shape");
		shapeTemplate.setAttribute("Standard", "ECMA");

		shapeTemplate.appendGeneralID("L", StringUtil.formatDouble(shape.getX(), 2));
		shapeTemplate.appendGeneralID("W", StringUtil.formatDouble(shape.getZ(), 2));
		shapeTemplate.appendGeneralID("D", StringUtil.formatDouble(shape.getY(), 2));

		writeTest(n, "resources/LWD_ShapeDefProduction.jdf", true, null);
	}

	/**
	 *
	 */
	private void createShapeDefProduction()
	{
		n.setType("ShapeDefProduction", false);
		n.setDescriptiveName("This process describes one up cad production");
		final JDFResource sdpp = n.addResource("ShapeDefProductionParams", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		setSnippet(sdpp, true);
		final int nOptions = 3;
		final JDFShape shape = new JDFShape(10 * 72 / 2.54, 20 * 72 / 2.54, 30 * 72 / 2.54);
		final JDFElement shapeTemplate = (JDFElement) sdpp.appendElement("ShapeTemplate");
		shapeTemplate.setAttribute("InnerDimensions", shape.toString());
		shapeTemplate.setDescriptiveName("10 * 20 * 30 cm shape");
		shapeTemplate.setAttribute("Standard", "ECMA");
		for (int i = 1; i < 4; i++)
		{
			final JDFGeneralID genID = (JDFGeneralID) shapeTemplate.appendElement(ElementName.GENERALID);
			genID.setIDUsage("EcmaParameter" + i);
			genID.setIDValue("123" + i);
			genID.setDescriptiveName("Name and Value of a parameter");
		}
		final JDFFileSpec filespec = (JDFFileSpec) shapeTemplate.appendElement(ElementName.FILESPEC);
		filespec.setURL(UrlUtil.fileToUrl(new File("//host/share/dir1/dir2/File with �.cff2"), false));
		filespec.setDescriptiveName("This is the optional location of the input cff2 (or evd), Note the escaping of Blanks. - chars > 127 may but need not be encoded as utf-8");

		for (int i = 0; i < nOptions; i++)
			createShapeDef(EnumUsage.Output, i, nOptions);
	}

	/**
	 * tests the creation of the initial shapedefproduction (one up) process
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDieLayoutProduction()
	{
		n.setType("DieLayoutProduction", false);
		n.setDescriptiveName("This process describes placement of one-up shapes on a sheet");

		createShapeDef(EnumUsage.Input, 0, 1);

		final JDFResource dlpp = n.addResource("DieLayoutProductionParams", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);

		dlpp.setAttribute(AttributeName.ESTIMATE, true, null);
		dlpp.setAttribute("Position", "Center");

		final JDFElement convertingConfig = (JDFElement) dlpp.appendElement("ConvertingConfig");
		final JDFNumberRange range = new JDFNumberRange();
		range.setLeft(90 * 72 / 2.54);
		range.setRight(110 * 72 / 2.54);
		convertingConfig.setAttribute("SheetWidth", range, null);
		range.setLeft(60 * 72 / 2.54);
		range.setRight(80 * 72 / 2.54);
		convertingConfig.setAttribute("SheetHeight", range, null);
		convertingConfig.setDescriptiveName("range of height and width - may also add margins");

		final JDFElement repeatDesc = (JDFElement) dlpp.appendElement("RepeatDesc");
		repeatDesc.setDescriptiveName("details the step and repeat parameters");
		repeatDesc.setAttribute("AllowedRotate", "None");
		repeatDesc.setAttribute("LayoutStyle", "StraightNest");

		final JDFDieLayout dl = createDieLayout(EnumUsage.Output, "//host/share/dir1/dir2/dieLayout.cff2");
		dl.setDescriptiveName("the abstract die layout ");

		writeTest(n, "CAD_DieLayoutProduction.jdf", true, null);
	}

	/**
	 * tests the creation of the initial shapedefproduction (one up) process
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDieDesign()
	{
		createDieDesign();
		writeTest(n, "CAD_DieDesign_out.jdf", true, null);
	}

	/**
	 *
	 */
	private void createDieDesign()
	{
		n.setType("DieDesign", false);
		n.setDescriptiveName("This process describes detailing the various die cut toolsets from a general description");
		final JDFComment comment = n.appendComment();
		comment.setName("Description");
		comment.setText("Multi line descriptions of the overall process are placed here\nLine feeds must be preserved for display");

		JDFDieLayout dl = createDieLayout(EnumUsage.Input, "//host/share/dir1/dir2/dieLayout.cff2");
		dl.setDescriptiveName("the abstract die layout ");
		dl = createDieLayout(EnumUsage.Output, "//host/share/dir1/dir2/dieLayoutUpper.cff2");
		dl.setDescriptiveName("the upper die layout ");
		dl = createDieLayout(EnumUsage.Output, "//host/share/dir1/dir2/dieLayoutLower.cff2");
		dl.setDescriptiveName("the lower die layout ");

		writeTest(n, "CAD_DieDesign.jdf", true, null);
		setAudits();
	}

	/**
	 * @param inOut
	 * @return the dielayout
	 *
	 */
	private JDFDieLayout createDieLayout(final EnumUsage inOut, final String fileName)
	{
		final JDFDieLayout dieLayout = (JDFDieLayout) n.addResource("DieLayout", EnumResourceClass.Parameter, inOut, null, null, null, null);
		dieLayout.setAttribute("Waste", "22");
		final JDFFileSpec fsDie = dieLayout.appendFileSpec();
		fsDie.makeRootResource(null, null, true);
		fsDie.setURL(UrlUtil.fileToUrl(new File(fileName), false));
		return dieLayout;
	}

	/**
	 * note that this may be calle multiple times to describe multiple one-ups per sheet
	 * 
	 * @param inOut input or output
	 * @param option
	 * @param nOptions
	 * @return
	 */
	private JDFResource createShapeDef(final EnumUsage inOut, final int option, final int nOptions)
	{
		// now the output
		JDFResource shapeDefRoot;
		if (option == 0)
			shapeDefRoot = n.addResource("ShapeDef", EnumResourceClass.Parameter, inOut, null, null, null, null);
		else
			shapeDefRoot = n.getResource("ShapeDef", inOut, 0);

		JDFResource shapeDef;
		if (nOptions == 1)
		{
			shapeDef = shapeDefRoot;
		}
		else
		{
			shapeDef = shapeDefRoot.addPartition(EnumPartIDKey.Option, "Option" + option);
		}
		final JDFFileSpec filespecOut = (JDFFileSpec) shapeDef.appendElement(ElementName.FILESPEC);
		filespecOut.setURL(UrlUtil.fileToUrl(new File("//host/share/dir1/dir2/OutFile with ü.cff2"), false));
		filespecOut.setDescriptiveName("This is the requested location of the output cff2 (or evd)");
		shapeDef.setDescriptiveName("Additional parameters may be filled by CAD - note also that this shapeDef will be the input of the DieLayoutProduction process");
		shapeDef.setAttribute("Area", "0.3");
		shapeDef.setAttribute("GrainDirection", "XDirection");
		shapeDef.setAttribute("FluteDirection", "YDirection");
		return shapeDef;
	}

	private void setAudits()
	{
		final JDFAuditPool ap = n.getAuditPool();
		final JDFPhaseTime pt = ap.addPhaseTime(EnumNodeStatus.InProgress, "Whoever", null);
		final JDFDate dat = new JDFDate();
		dat.addOffset(0, 0, 5, 0);
		pt.setStart(dat);
		dat.addOffset(0, 0, 3, 0);
		pt.setEnd(dat);
		pt.setDescriptiveName("3 hours work ");
	}

	/**
	 *
	 */
	@Override
	@BeforeEach
	public void setUp() throws Exception
	{
		super.setUp();
		KElement.setLongID(false);
		d = new JDFDoc(ElementName.JDF);
		prepareCustomerInfo(d);
		prepareNodeInfo(d);
		n = d.getJDFRoot();
		n.setJobID("CAD");
	}

}
