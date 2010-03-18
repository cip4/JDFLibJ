/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.goldenticket;

import java.io.File;

import org.cip4.jdflib.auto.JDFAutoDieLayoutProductionParams.EnumPosition;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoRepeatDesc.EnumAllowedRotate;
import org.cip4.jdflib.auto.JDFAutoShapeDef.EnumGrainDirection;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFConvertingConfig;
import org.cip4.jdflib.resource.process.JDFDieLayout;
import org.cip4.jdflib.resource.process.JDFDieLayoutProductionParams;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRepeatDesc;
import org.cip4.jdflib.resource.process.JDFShapeDef;
import org.cip4.jdflib.resource.process.JDFShapeDefProductionParams;
import org.cip4.jdflib.resource.process.JDFShapeTemplate;

/**
 * @author Rainer Prosi class that generates golden tickets based on ICS levels etc
 */
public class PackagingGoldenTicket extends MISGoldenTicket
{
	/**
	 * filename for shapedefs
	 */
	public String shapeFile = "\\\\Server\\share\\dir\\CADOutput.cad";

	/**
	 * create a BaseGoldenTicket
	 * 
	 * @param _icsLevel the level to init to (1,2 or 3)
	 * @param jdfVersion the version to generate a golden ticket for
	 * @param _jmfLevel level of jmf ICS to support
	 * @param _misLevel level of MIS ICS to support
	 * @param vPartMap list of parts to process
	 */
	public PackagingGoldenTicket(final int _icsLevel, final EnumVersion jdfVersion, final int _jmfLevel, final int _misLevel, final VJDFAttributeMap vPartMap)
	{
		super(_misLevel, jdfVersion, _jmfLevel);

		partIDKeys = null;
		vParts = vPartMap;
		icsLevel = _icsLevel;
		scheduleHours = 24 * 7; // 7 days from now
		scheduleDuration = 48;
	}

	/**
	 * initializes this node to a given ICS version
	 * 
	 */
	@Override
	public void init()
	{

		// put level methods?

		super.init();
		theNode.setType(EnumType.ProcessGroup);
		setActivePart(vParts, true);
	}

	/**
	 * 
	 */
	public void createDieLayoutProduction()
	{
		theNode.addTypes(EnumType.DieLayoutProduction);
		makeShapeDef(EnumUsage.Input);

		final JDFDieLayoutProductionParams dlp = (JDFDieLayoutProductionParams) theNode.addResource(ElementName.DIELAYOUTPRODUCTIONPARAMS, EnumUsage.Input);
		dlp.setPosition(EnumPosition.TopCenter);

		final JDFConvertingConfig cc = dlp.appendConvertingConfig();
		cc.setSheetHeightMM(650, 750);
		cc.setSheetWidthMM(950, 1050);

		final JDFRepeatDesc rd = dlp.appendRepeatDesc();
		rd.setAllowedRotate(EnumAllowedRotate.Grain);
		rd.setLayoutStyle("StraightNest");

		final JDFDieLayout dl = (JDFDieLayout) theNode.addResource(ElementName.DIELAYOUT, EnumUsage.Output);
		dl.setDescriptiveName("this resource is filled in by the process");

	}

	/**
		 * 
		 */
	public void createShapeDefProduction()
	{
		theNode.addTypes(EnumType.ShapeDefProduction);
		final JDFShapeDefProductionParams sdpp = (JDFShapeDefProductionParams) theNode.addResource(ElementName.SHAPEDEFPRODUCTIONPARAMS, EnumUsage.Input);
		final JDFShapeTemplate st = sdpp.appendShapeTemplate();
		final JDFShape shape = new JDFShape(100, 500, 20);
		shape.scaleFromMM();
		st.setInnerDimensions(shape);
		st.setDescriptiveName("Box of size 100 * 500 * 20 millimeters");
		st.setStandard("FEFCO");
		st.setName("Type02");
		final JDFFileSpec fs = st.appendFileSpec();
		fs.setAbsoluteFileURL(new File("\\\\Server\\share\\dir\\CADFile für mich.cad"), true);

		final JDFShapeDef sdOut = makeShapeDef(EnumUsage.Output);
		final JDFMedia m = sdOut.appendMedia();
		m.setDescriptiveName("Details of the box media");
		m.setMediaType(EnumMediaType.Paper);
		m.setProductID("MediaProductID");
	}

	/**
	 * @param usage
	 * @return
	 */
	private JDFShapeDef makeShapeDef(final EnumUsage usage)
	{
		final JDFShapeDef shapeDef = (JDFShapeDef) theNode.addResource(ElementName.SHAPEDEF, usage);
		final JDFShape size = new JDFShape(100, 70, 30);
		size.scaleFromMM();
		shapeDef.setDimensions(size);
		shapeDef.setGrainDirection(EnumGrainDirection.YDirection);
		final JDFFileSpec fso = shapeDef.appendFileSpec();
		fso.setAbsoluteFileURL(new File(shapeFile), true);
		return shapeDef;
	}
}
