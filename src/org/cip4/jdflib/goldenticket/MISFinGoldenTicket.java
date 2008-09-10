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

	protected int icsLevel;

	/**
	 * create a BaseGoldenTicket
	 * 
	 * @param icsLevel the level to init to (1,2 or 3)
	 * @param jdfVersion the version to generate a golden ticket for
	 * @param jmfLevel level of jmf ICS to support
	 * @param misLevel level of MIS ICS to support
	 * @param isGrayBox if true, write a grayBox
	 */
	public MISFinGoldenTicket(int _icsLevel, EnumVersion jdfVersion, int _jmfLevel, int _misLevel, 
			@SuppressWarnings("unused") boolean isGrayBox, VJDFAttributeMap vPartMap)
	{
		super(_misLevel, jdfVersion, _jmfLevel);

		catMap.put(MISFIN_SHEETFIN, new VString("Cutting Folding", null));
		partIDKeys = new VString("SignatureName,SheetName", ",");
		vParts = vPartMap;
		icsLevel = _icsLevel;
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
	public MISFinGoldenTicket(MISFinGoldenTicket parent)
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
	}

	@Override
	public void setActivePart(VJDFAttributeMap vp, boolean bFirst)
	{
		amountLinks = null;
		if (bFirst)
			addAmountLink("Componenet:Input");
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
			theNode.linkResource(outComp, EnumUsage.Output, null);

		JDFResourceLink rl = theNode.getLink(outComp, EnumUsage.Output);
		if (vParts != null)
		{
			VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation", " "));
			final int size = reducedMap == null ? 0 : reducedMap.size();
			for (int i = 0; i < size; i++)
			{
				final JDFAttributeMap part = reducedMap.elementAt(i);
				JDFResource partComp = outComp.getCreatePartition(part, partIDKeys);
				partComp.setDescriptiveName("Description for Component part# " + i);
				JDFAttributeMap newMap = new JDFAttributeMap(part);
				newMap.put(AttributeName.CONDITION, "Good");
				rl.setAmount(good, newMap);
			}
		}
		else
		{
			outComp.setDescriptiveName("MIS-Fin output Component");
		}
		// outComp.getCreateLayout();
		JDFMedia inMedia = (JDFMedia) theNode.getResource(ElementName.MEDIA, EnumUsage.Input, 0);
		outComp.setDimensions(inMedia.getDimension());
		return outComp;

	}

	@Override
	protected void runphases(int pgood, int pwaste, boolean bOutAvail, boolean bFirst)
	{
		theStatusCounter.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, "Printing");
		runSinglePhase(pgood, pwaste, bOutAvail, bFirst);
		finalize(); // prior to processRun
		theStatusCounter.setPhase(EnumNodeStatus.Completed, "Done", EnumDeviceStatus.Idle, "Waiting");
	}

	@Override
	public void assign(JDFNode node)
	{
		super.assign(node);
		theNode.getCreateNodeInfo().setPartIDKeys(partIDKeys);
	}
}
