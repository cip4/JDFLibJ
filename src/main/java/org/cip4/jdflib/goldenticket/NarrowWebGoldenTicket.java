/**
 * The CIP4 Software License, Version 1.0
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

import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumPrintingTechnology;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumPrintingType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFEmbossingParams;
import org.cip4.jdflib.resource.JDFLaminatingParams;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFShapeCuttingParams;
import org.cip4.jdflib.resource.JDFTool;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFMedia;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * Sep 23, 2009
 */
public class NarrowWebGoldenTicket extends MISGoldenTicket
{

	boolean bLaminate;
	boolean bEmboss;
	boolean bShapeCut;

	/**
	 * @param level
	 * @param jdfVersion
	 * @param jmfLevel
	 */
	public NarrowWebGoldenTicket(final int level, final EnumVersion jdfVersion, final int jmfLevel)
	{
		super(level, jdfVersion, jmfLevel);
		bPartitionedPlateMedia = true;
	}

	/**
	 * @return the bLaminate
	 */
	public boolean isLaminate()
	{
		return bLaminate;
	}

	/**
	 * @param laminate the bLaminate to set
	 */
	public void setLaminate(final boolean laminate)
	{
		bLaminate = laminate;
	}

	/**
	 * @return the bEmboss
	 */
	public boolean isEmboss()
	{
		return bEmboss;
	}

	/**
	 * @param emboss the bEmboss to set
	 */
	public void setEmboss(final boolean emboss)
	{
		bEmboss = emboss;
	}

	/**
	 * @return the bShapeCut
	 */
	public boolean isShapeCut()
	{
		return bShapeCut;
	}

	/**
	 * @param shapeCut the bShapeCut to set
	 */
	public void setShapeCut(final boolean shapeCut)
	{
		bShapeCut = shapeCut;
	}

	/**
	 * get the correct Types from category
	 * @return
	 */
	@Override
	public VString getTypes()
	{
		final VString v = new VString();
		v.add(EnumType.ConventionalPrinting.getName());
		if (bEmboss)
		{
			v.add(EnumType.Embossing.getName());
		}
		if (bLaminate)
		{
			v.add(EnumType.Laminating.getName());
		}
		if (bShapeCut)
		{
			v.add(EnumType.ShapeCutting.getName());
		}
		return v;
	}

	/**
	 * @see org.cip4.jdflib.goldenticket.MISGoldenTicket#init()
	 */
	@Override
	public void init()
	{
		cols = new VString("Black,Cyan,Magenta,Yellow,FlexoBlack,GravureBlack", ",");
		vParts = new VJDFAttributeMap();
		final JDFAttributeMap map = new JDFAttributeMap("SheetName", "Bogen1");
		map.put("Side", "Front");
		partIDKeys = new VString("SheetName Side", null);
		vParts.add(map);
		nCols[0] = cols.size();
		nCols[1] = 0;
		plateReduction = null;
		super.init();
		initColorantControl();
		initPlateXM(EnumUsage.Input);
		initConventionalPrinting();
		if (bEmboss)
		{
			initEmbossing();
		}
		if (bLaminate)
		{
			initLaminating();
		}
		if (bShapeCut)
		{
			initShapeCutting();
		}
	}

	/**
	 * 
	 */
	private void initConventionalPrinting()
	{
		final JDFConventionalPrintingParams cp = (JDFConventionalPrintingParams) theNode.addResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		cp.addPartitions(EnumPartIDKey.Separation, cols);
		final VElement cpLeaves = cp.getLeaves(false);
		for (int i = 0; i < cpLeaves.size(); i++)
		{
			final JDFConventionalPrintingParams cpLeaf = (JDFConventionalPrintingParams) cpLeaves.get(i);
			final EnumPrintingType pt = EnumPrintingType.WebSingle;
			EnumPrintingTechnology tech = EnumPrintingTechnology.Offset;
			if (cols.get(i).indexOf("Flexo") >= 0)
			{
				tech = EnumPrintingTechnology.Flexo;
			}
			if (cols.get(i).indexOf("Gravure") >= 0)
			{
				tech = EnumPrintingTechnology.Gravure;
			}
			if (cols.get(i).indexOf("Screen") >= 0)
			{
				tech = EnumPrintingTechnology.Screen;
			}
			cpLeaf.setPrintingType(pt);
			cpLeaf.setPrintingTechnology(tech);
			cpLeaf.setModuleIndex(new JDFIntegerRangeList(new JDFIntegerRange(i)));
		}
	}

	/**
	 * 
	 */
	private void initShapeCutting()
	{
		final JDFShapeCuttingParams sp = (JDFShapeCuttingParams) theNode.addResource(ElementName.SHAPECUTTINGPARAMS, EnumUsage.Input);
		//		final JDFResourceLink rl = theNode.getLink(sp, null);
		sp.setAttribute(AttributeName.MODULEINDEX, 8, null);
		sp.setDescriptiveName("Modulzuordnung Stanzen");
		final JDFTool tool = (JDFTool) theNode.addResource(ElementName.TOOL, EnumUsage.Input);
		tool.setToolType("ToolSet");
		tool.setProductID("Stanz_ID");
		tool.setDescriptiveName("Werkzeugsatz Stanzen");
		final JDFResourceLink rlt = theNode.getLink(tool, null);
		rlt.setCombinedProcessIndex(theNode.getCombinedProcessIndex(EnumType.ShapeCutting, 0));
	}

	/**
	 * 
	 */
	private void initEmbossing()
	{
		final JDFEmbossingParams ep = (JDFEmbossingParams) theNode.addResource(ElementName.EMBOSSINGPARAMS, EnumUsage.Input);
		//		final JDFResourceLink rl = theNode.getLink(ep, null);
		ep.setAttribute(AttributeName.MODULEINDEX, 7, null);
		ep.setDescriptiveName("Modulzuordnung Prägen");
		final JDFTool tool = (JDFTool) theNode.addResource(ElementName.TOOL, EnumUsage.Input);
		tool.setToolType("ToolSet");
		tool.setProductID("Praege_ID");
		tool.setDescriptiveName("Werkzeugsatz Prägen");
		final JDFResourceLink rlt = theNode.getLink(tool, null);
		rlt.setCombinedProcessIndex(theNode.getCombinedProcessIndex(EnumType.Embossing, 0));
	}

	/**
	 * 
	 */
	private void initLaminating()
	{
		final JDFLaminatingParams lp = (JDFLaminatingParams) theNode.addResource(ElementName.LAMINATINGPARAMS, EnumUsage.Input);
		lp.setAttribute(AttributeName.MODULEINDEX, 6, null);
		lp.setDescriptiveName("Modulzuordnung Laminieren");
		//		final JDFResourceLink rl = theNode.getLink(lp, null);
		final JDFMedia foil = (JDFMedia) theNode.addResource(ElementName.MEDIA, EnumUsage.Input);
		foil.setMediaType(EnumMediaType.LaminatingFoil);
	}

	/**
	 * @see org.cip4.jdflib.goldenticket.MISGoldenTicket#initDevice(org.cip4.jdflib.node.JDFNode)
	 */
	@Override
	protected JDFDevice initDevice(final JDFNode reuseNode)
	{
		return super.initDevice(reuseNode);
	}

}
