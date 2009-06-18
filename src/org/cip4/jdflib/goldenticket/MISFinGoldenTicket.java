/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
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

import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.postpress.JDFFoldingParams;
import org.cip4.jdflib.resource.process.postpress.JDFStitchingParams;
import org.cip4.jdflib.resource.process.postpress.JDFTrimmingParams;

/**
 * @author Rainer Prosi class that generates golden tickets based on ICS levels etc
 */
public class MISFinGoldenTicket extends MISGoldenTicket
{
	/**
     * 
     */
	public static final String MISFIN_SHEETFIN = "MISFIN.SheetFin";
	/**
	 * MIS FIN GB Type
	 */
	public static final String MISFIN_BOXMAKING = "MISFIN.BoxMaking";
	/**
	 * MIS FIN GB Type
	 */
	public static final String MISFIN_INSERTFIN = "MISFIN.InsertFin";
	/**
	 * MIS FIN GB Type
	 */
	public static final String MISFIN_STITCHFIN = "MISFIN.StitchFin";
	/**
	 * MIS FIN GB Type
	 */
	public static final String MISFIN_SOFTCOVERFIN = "MISFIN.SoftcoverFin";
	/**
	 * MIS FIN GB Type
	 */
	public static final String MISFIN_HARDCOVERFIN = "MISFIN.HardcoverFin";

	/**
	 * 
	 */
	public String foldCatalog = "F8-2";

	/**
	 * create a BaseGoldenTicket
	 * @param _icsLevel
	 * @param jdfVersion
	 * @param _jmfLevel
	 * @param _misLevel
	 * @param vPartMap
	 * 
	 */
	public MISFinGoldenTicket(final int _icsLevel, final EnumVersion jdfVersion, final int _jmfLevel, final int _misLevel, final VJDFAttributeMap vPartMap)
	{
		super(_misLevel, jdfVersion, _jmfLevel);
		partIDKeys = new VString("SignatureName,SheetName", ",");
		vParts = vPartMap;
		icsLevel = _icsLevel;
		grayBox = true;
	}

	/**
     * 
     */
	@Override
	protected void fillCatMaps()
	{
		super.fillCatMaps();
		catMap.put(MISFIN_BOXMAKING, new VString(EnumType.BoxFolding.getName(), null));

		catMap.put(MISFIN_HARDCOVERFIN, new VString("BlockPreparation CaseMaking CasingIn", null));

		catMap.put(MISFIN_INSERTFIN, new VString("Inserting Trimming", null));
		catMap.put(MISFIN_SHEETFIN, new VString("Folding", null));
		catMap.put(MISFIN_SOFTCOVERFIN, new VString("Gathering CoverApplication Trimming", null));
		catMap.put(MISFIN_STITCHFIN, new VString("Stitching Trimming", null));
	}

	/**
	 * create a BaseGoldenTicket
	 * 
	 * @param parent the previous node, may be null
	 */
	public MISFinGoldenTicket(final MISFinGoldenTicket parent)
	{
		super(parent);
	}

	/**
	 * initializes this node to a given ICS version
	 * 
	 * @param icsLevel the level to init to (1,2 or 3)
	 */
	@Override
	public void init()
	{
		super.init();
		initFolding();
		initTrimming();
		initStitching();
		initInputComponent();
		initOutputComponent();

	}

	/**
	 * 
	 */
	private void initStitching()
	{
		if (theNode.getTypes().contains("Stitching"))
		{
			final JDFStitchingParams sp = (JDFStitchingParams) theNode.getCreateResource(ElementName.STITCHINGPARAMS, EnumUsage.Input, 0);
			sp.setStapleShape(org.cip4.jdflib.auto.JDFAutoStitchingParams.EnumStapleShape.Butted);
			sp.setStitchWidth(36);
		}

	}

	/**
	 * 
	 */
	private void initFolding()
	{
		if (theNode.getTypes().contains("Folding"))
		{
			final JDFFoldingParams fp = (JDFFoldingParams) theNode.getCreateResource(ElementName.FOLDINGPARAMS, EnumUsage.Input, 0);
			fp.setFoldCatalog(foldCatalog);
		}
	}

	/**
	 * 
	 */
	private void initTrimming()
	{
		if (theNode.getTypes().contains("Trimming"))
		{
			final JDFTrimmingParams tp = (JDFTrimmingParams) theNode.getCreateResource(ElementName.TRIMMINGPARAMS, EnumUsage.Input, 0);
			tp.setHeight(72 * 12);
			tp.setWidth(72 * 6);
		}
	}

	/**
	 * @see org.cip4.jdflib.goldenticket.BaseGoldenTicket#setActivePart(org.cip4.jdflib.datatypes.VJDFAttributeMap, boolean)
	 */
	@Override
	public void setActivePart(final VJDFAttributeMap vp, final boolean bFirst)
	{
		amountLinks = null;
		if (bFirst)
		{
			addAmountLink("Component:Input");
		}
		addAmountLink("Component:Output");
		super.setActivePart(vp, bFirst);
	}

	/**
	 * 
	 */
	@Override
	protected JDFComponent initOutputComponent()
	{
		if (thePreviousNode != null)
		{
			final JDFResource parentOutComp = thePreviousNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			if (parentOutComp != null)
			{
				theNode.linkResource(parentOutComp, EnumUsage.Input, null);
			}
		}
		JDFComponent outComp = (JDFComponent) (theParentNode != null ? theParentNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0) : null);
		if (outComp == null)
		{
			outComp = (JDFComponent) theNode.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			outComp.setComponentType(EnumComponentType.FinalProduct, EnumComponentType.Sheet);
			outComp.setProductType("Unknown");
		}
		else
		{
			theNode.linkResource(outComp, EnumUsage.Output, null);
		}

		final JDFResourceLink rl = theNode.getLink(outComp, EnumUsage.Output);
		if (vParts != null && MISFIN_SHEETFIN.equals(category))
		{
			final VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation", " "));
			if (reducedMap != null)
			{
				final int size = reducedMap.size();
				for (int i = 0; i < size; i++)
				{
					final JDFAttributeMap part = reducedMap.elementAt(i);
					final JDFResource partComp = outComp.getCreatePartition(part, partIDKeys);
					partComp.setDescriptiveName("Description for Component part# " + i);
					final JDFAttributeMap newMap = new JDFAttributeMap(part);
					newMap.put(AttributeName.CONDITION, "Good");
					rl.setAmount(good, newMap);
				}
			}
		}
		else
		{
			outComp.setDescriptiveName("MIS-Fin output Component");
		}

		// outComp.getCreateLayout();
		final JDFMedia inMedia = (JDFMedia) theNode.getResource(ElementName.MEDIA, EnumUsage.Input, 0);
		if (inMedia != null)
		{
			outComp.setDimensions(inMedia.getDimension());
		}

		return outComp;

	}

	/**
	 * 
	 */
	protected JDFComponent initInputComponent()
	{

		JDFComponent comp = (JDFComponent) (theParentNode != null ? theParentNode.getResource(ElementName.COMPONENT, EnumUsage.Input, 0) : null);
		if (comp == null)
		{
			comp = (JDFComponent) theNode.getCreateResource(ElementName.COMPONENT, EnumUsage.Input, 0);
			comp.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);
			comp.setProductType("Unknown");
		}
		else
		{
			theNode.linkResource(comp, EnumUsage.Input, null);
		}

//		final JDFResourceLink rl = 
			theNode.getLink(comp, EnumUsage.Input);
		return comp;

	}

	@Override
	protected void runphases(final int pgood, final int pwaste, final boolean bOutAvail, final boolean bFirst)
	{
		theStatusCounter.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, "Printing");
		runSinglePhase(pgood, pwaste, bOutAvail, bFirst);
		finalize(); // prior to processRun
		theStatusCounter.setPhase(EnumNodeStatus.Completed, "Done", EnumDeviceStatus.Idle, "Waiting");
	}

	/**
	 * @see org.cip4.jdflib.goldenticket.MISGoldenTicket#assign(org.cip4.jdflib.node.JDFNode)
	 */
	@Override
	public void assign(final JDFNode node)
	{
		super.assign(node);
		theNode.getCreateNodeInfo().setPartIDKeys(partIDKeys);
	}
}
