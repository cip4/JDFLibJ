/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2011 The International Cooperation for the Integration of 
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

import org.cip4.jdflib.auto.JDFAutoDigitalPrintingParams.EnumSides;
import org.cip4.jdflib.auto.JDFAutoUsageCounter.EnumScope;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFInterpretingParams;
import org.cip4.jdflib.resource.JDFLayoutPreparationParams;
import org.cip4.jdflib.resource.process.JDFDigitalPrintingParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFUsageCounter;

/**
 * @author Rainer Prosi class that generates golden tickets based on ICS levels etc
 */
public class IDPGoldenTicket extends MISGoldenTicket
{
	boolean bUsageCounter;

	/**
	 * @param previous
	 * @param _vparts
	 */
	public IDPGoldenTicket(final IDPGoldenTicket previous, final VJDFAttributeMap _vparts)
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
		bUsageCounter = true;
	}

	/**
	 * 
	 */
	@Override
	protected void fillCatMaps()
	{
		super.fillCatMaps();
		catMap.put("IDP.DigitalPrinting", new VString("LayoutPreparation Interpreting Rendering DigitalPrinting", null));
		setCategory("IDP.DigitalPrinting");
	}

	/**
	 * create a BaseGoldenTicket
	 * @param parent
	 */
	public IDPGoldenTicket(final MISGoldenTicket parent)
	{
		super(parent);
		grayBox = false;
		bUsageCounter = true;
	}

	/**
	 * create a BaseGoldenTicket
	 * @param _icsLevel the level to init to (1,2 or 3)
	 */
	public IDPGoldenTicket(final int _icsLevel)
	{
		super(1, null, 2);
		grayBox = false;
		bUsageCounter = true;
		icsLevel = _icsLevel;
	}

	/**
	 * initializes this node to a given ICS version
	 */
	@Override
	public void init()
	{
		final String icsTag = "IDP_L" + icsLevel + "-" + theVersion.getName();
		theNode.appendAttribute(AttributeName.ICSVERSIONS, icsTag, null, " ", true);
		if (!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
		{
			theNode.setDescriptiveName("IDP Golden Ticket Example Job - version: " + JDFAudit.software());
		}
		super.init();
		setActivePart(vParts, true);
		initDocumentRunList();
		initOutputComponent();
		initInterpretingParams();
		initUsageCounter();
		JDFMedia m = initPaperMedia();
		initDigitalPrintingParams(m);
		initLayoutPrep();
	}

	/**
	 * TODO Please insert comment!
	 */
	private JDFLayoutPreparationParams initLayoutPrep()
	{
		JDFLayoutPreparationParams layPP = (JDFLayoutPreparationParams) theNode.getCreateResource(ElementName.LAYOUTPREPARATIONPARAMS, EnumUsage.Input, 0);
		layPP.setSides(JDFLayoutPreparationParams.EnumSides.TwoSidedFlipY);
		return layPP;
	}

	/**
	 * @return 
	 * 
	 */
	private JDFInterpretingParams initInterpretingParams()
	{
		return (JDFInterpretingParams) theNode.getCreateResource(ElementName.INTERPRETINGPARAMS, EnumUsage.Input, 0);
	}

	/**
	 * @return 
	 * 
	 */
	private JDFUsageCounter initUsageCounter()
	{
		if (!bUsageCounter)
			return null;
		JDFUsageCounter usageCounter = (JDFUsageCounter) theNode.getCreateResource(ElementName.USAGECOUNTER, EnumUsage.Input, 0);
		usageCounter.setScope(EnumScope.Job);
		return usageCounter;
	}

	/**
	 * @param m 
	 * @return 
	 * 
	 */
	private JDFDigitalPrintingParams initDigitalPrintingParams(JDFMedia m)
	{
		JDFDigitalPrintingParams digiParams = (JDFDigitalPrintingParams) theNode.getCreateResource(ElementName.DIGITALPRINTINGPARAMS, EnumUsage.Input, 0);
		digiParams.setSides(EnumSides.TwoSidedFlipY);
		if (m != null)
			digiParams.refElement(m);
		return digiParams;
	}

	/**
	 * 
	 */
	@Override
	protected void initJDF()
	{
		super.initJDF();
	}

	/**
	 * simulate execution of this node the internal node will be modified to reflect the execution
	 */
	@Override
	public void execute(final VJDFAttributeMap parts, final boolean outputAvailable, final boolean bFirst)
	{
		VJDFAttributeMap partsLocal = parts;

		partsLocal = null; // alwways execute all in pp
		setActivePart(partsLocal, bFirst);
		super.execute(partsLocal, outputAvailable, bFirst);
	}

	/*
	 * (non-Javadoc)
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

	@Override
	protected JDFMedia initPaperMedia()
	{
		super.initPaperMedia();
		paperMedia.setDimensionInch(new JDFXYPair(8.5, 11));
		return paperMedia;
	}

}
