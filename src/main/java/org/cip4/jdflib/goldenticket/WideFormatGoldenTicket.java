/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of 
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

import org.cip4.jdflib.auto.JDFAutoDigitalPrintingParams;
import org.cip4.jdflib.auto.JDFAutoDigitalPrintingParams.EnumSides;
import org.cip4.jdflib.auto.JDFAutoFitPolicy;
import org.cip4.jdflib.auto.JDFAutoFitPolicy.EnumSizePolicy;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumBackCoatings;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumFrontCoatings;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFFitPolicy;
import org.cip4.jdflib.resource.JDFInterpretingParams;
import org.cip4.jdflib.resource.process.JDFDigitalPrintingParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFTile;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceConversionParams;
import org.cip4.jdflib.resource.process.prepress.JDFRenderingParams;

/**
 * @author Rainer Prosi class that generates golden tickets based on ICS levels etc
 */
public class WideFormatGoldenTicket extends MISGoldenTicket
{

	/**
	 * @param previous
	 * @param _vparts
	 */
	public WideFormatGoldenTicket(final WideFormatGoldenTicket previous, final VJDFAttributeMap _vparts)
	{
		super(previous.misICSLevel, previous.theVersion, previous.jmfICSLevel);
		grayBox = false;
		partIDKeys = new VString(previous.partIDKeys);
		vParts = _vparts == null ? new VJDFAttributeMap(previous.vParts) : _vparts;
		icsLevel = previous.icsLevel;
		nCols = previous.nCols;
		workStyle = previous.workStyle;
		thePreviousNode = previous.theNode;
		theParentNode = previous.theParentNode;
		bUsageCounter = icsLevel == 3;
	}

	/**
	 * 
	 */
	@Override
	protected void fillCatMaps()
	{
		super.fillCatMaps();
		catMap.put("DPW.WideFormat", new VString("Interpreting ColorSpaceConversion Rendering Tiling DigitalPrinting", null));
		setCategory("DPW.WideFormat");
	}

	/**
	 * create a BaseGoldenTicket
	 * @param parent
	 */
	public WideFormatGoldenTicket(final MISGoldenTicket parent)
	{
		super(parent);
		grayBox = false;
		bUsageCounter = icsLevel == 3;
	}

	/**
	 * create a BaseGoldenTicket
	 * @param _icsLevel the level to init to (1,2 or 3)
	 */
	public WideFormatGoldenTicket(final int _icsLevel)
	{
		this(_icsLevel, null);
	}

	/**
	 * create a BaseGoldenTicket
	 * @param _icsLevel the level to init to (1,2 or 3)
	 * @param version
	 */
	public WideFormatGoldenTicket(final int _icsLevel, EnumVersion version)
	{
		super(1, version, 2);
		grayBox = false;
		icsLevel = _icsLevel;
		bUsageCounter = _icsLevel == 3;
	}

	/**
	 * initializes this node to a given ICS version
	 */
	@Override
	public void init()
	{
		final String icsTag = "DPW_L" + icsLevel + "-" + theVersion.getName();
		theNode.appendAttribute(AttributeName.ICSVERSIONS, icsTag, null, " ", true);
		if (!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
		{
			theNode.setDescriptiveName("Wide Format Golden Ticket Example Job - version: " + JDFAudit.software());
		}
		super.init();
		setActivePart(vParts, true);
		initDocumentRunList();
		initOutputComponent();
		initInterpretingParams();
		initRenderingParams();
		initUsageCounter();
		initTile();
		initPaperMedia();
		initDigitalPrintingParams(null);
		initColorantControl();
		initColorspaceConversion();
	}

	private void initColorspaceConversion()
	{
		JDFColorSpaceConversionParams cscp = (JDFColorSpaceConversionParams) theNode.getCreateResource(ElementName.COLORSPACECONVERSIONPARAMS, EnumUsage.Input, 0);

	}

	private JDFTile initTile()
	{
		JDFTile tile = (JDFTile) theNode.getCreateResource(ElementName.TILE, EnumUsage.Input, 0);
		tile.setCTM(JDFMatrix.unitMatrix);
		tile.setClipBox(new JDFRectangle(0, 0, 444, 666));
		return tile;
	}

	/**
	 * @return 
	 * 
	 */
	private JDFInterpretingParams initInterpretingParams()
	{
		JDFInterpretingParams interpretingParams = (JDFInterpretingParams) theNode.getCreateResource(ElementName.INTERPRETINGPARAMS, EnumUsage.Input, 0);
		JDFFitPolicy fitPolicy = interpretingParams.appendFitPolicy();
		fitPolicy.setRotatePolicy(JDFAutoFitPolicy.EnumRotatePolicy.NoRotate);
		fitPolicy.setSizePolicy(EnumSizePolicy.ClipToMaxPage);
		return interpretingParams;
	}

	/**
	 * @return 
	 * 
	 */
	private JDFRenderingParams initRenderingParams()
	{
		JDFRenderingParams renderingParams = (JDFRenderingParams) theNode.getCreateResource(ElementName.RENDERINGPARAMS, EnumUsage.Input, 0);
		return renderingParams;
	}

	/**
	 * @param m 
	 * @return 
	 * 
	 */
	private JDFDigitalPrintingParams initDigitalPrintingParams(JDFMedia m)
	{
		JDFDigitalPrintingParams digiParams = (JDFDigitalPrintingParams) theNode.getCreateResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input, 0);
		digiParams.setSides(EnumSides.OneSidedFront);
		digiParams.setPageDelivery(JDFAutoDigitalPrintingParams.EnumPageDelivery.SameOrderFaceUp);
		if (m != null)
			digiParams.refElement(m);
		return digiParams;
	}

	/**
	 * simulate execution of this node the internal node will be modified to reflect the execution
	 */
	@Override
	public void execute(final VJDFAttributeMap parts, final boolean outputAvailable, final boolean bFirst)
	{
		setActivePart(null, bFirst);
		super.execute(null, outputAvailable, bFirst);
	}

	/**
	 *  
	 * 
	 * @see org.cip4.jdflib.goldenticket.BaseGoldenTicket#initDocumentRunList()
	 */
	@Override
	protected JDFRunList initDocumentRunList()
	{
		final JDFRunList rl = super.initDocumentRunList();
		theNode.getLink(rl, EnumUsage.Input).setProcessUsage((EnumProcessUsage) null);

		return rl;
	}

	/**
	 * 
	 * @see org.cip4.jdflib.goldenticket.BaseGoldenTicket#initPaperMedia()
	 */
	@Override
	protected JDFMedia initPaperMedia()
	{
		super.initPaperMedia();
		paperMedia.setDimensionCM(new JDFXYPair(42, 0));
		paperMedia.setBackCoatings(EnumBackCoatings.None);
		paperMedia.setFrontCoatings(EnumFrontCoatings.HighGloss);
		paperMedia.setPrintingTechnology("Latex");
		paperMedia.setUnit("m2");
		paperMedia.setMediaTypeDetails("Backlit");
		theNode.ensureLink(paperMedia, EnumUsage.Input, null);
		return paperMedia;
	}

}
