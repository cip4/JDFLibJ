/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2026 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.xjdfgoldenticket;

import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumSheetLay;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFPreview.EnumPreviewFileType;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.UnitParser.eParserUnit;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XJDFConvPrintICSGoldenTicket extends XJDFBaseGoldenTicket
{

	private boolean perfecting;
	private boolean previewGeneration;

	boolean isPreviewGeneration()
	{
		return previewGeneration;
	}

	void setPreviewGeneration(boolean previewGeneration)
	{
		this.previewGeneration = previewGeneration;
	}

	private ResourceHelper paperHelper;
	private ResourceHelper plateHelper;
	private int amount;
	private int waste;

	int getAmount()
	{
		return amount;
	}

	void setAmount(final int amount)
	{
		this.amount = amount;
	}

	int getWaste()
	{
		return waste;
	}

	void setWaste(final int waste)
	{
		this.waste = waste;
	}

	public boolean isPerfecting()
	{
		return perfecting;
	}

	public void setPerfecting(final boolean perfecting)
	{
		this.perfecting = perfecting;
	}

	/**
	 * @param pIcsLevel
	 * @param jdfVersion
	 * @param parts
	 */
	public XJDFConvPrintICSGoldenTicket(final int pIcsLevel, final EnumVersion jdfVersion, final VJDFAttributeMap parts)
	{
		super(pIcsLevel, jdfVersion, parts);
		setPerfecting(true);
		setPreviewGeneration(false);
	}

	@Override
	public VString getTypes()
	{
		final VString types = new VString(EnumType.ConventionalPrinting.name());
		if (previewGeneration)
		{
			types.insertElementAt(EnumType.PreviewGeneration.name(), 0);
		}
		return types;
	}

	@Override
	VString getWorkstepKeys()
	{
		final VString workstepKeys = super.getWorkstepKeys();
		if (perfecting)
		{
			workstepKeys.remove(AttributeName.SIDE);
		}
		workstepKeys.remove(AttributeName.SEPARATION);
		return workstepKeys;
	}

	/**
	 * @return
	 */
	@Override
	public VString getICSVersions(final int icsVersion)
	{
		final VString v = super.getICSVersions(icsVersion);
		v.add("MIS-CP_L" + icsVersion + "-" + getVersion().getJDFVersionName());
		return v;
	}

	@Override
	void addSets()
	{
		super.addSets();
		createColorSet();
		createColorantControl();
		createCPPSet();
		createDevice();
		plateHelper = createPlateMedia();
		createPlates();

		paperHelper = createPaper();

		final SetHelper csi = createComponent(EnumUsage.Input);
		final SetHelper cso = createComponent(EnumUsage.Output);
		createPreviewSet();
	}

	SetHelper createComponent(final EnumUsage usage)
	{
		final SetHelper createSet = helper.getCreateSet(ElementName.COMPONENT, usage);
		final VJDFAttributeMap workstepKeys = super.getWorkstepParts();
		workstepKeys.removeKey(AttributeName.SIDE);

		if (workstepKeys.isEmpty())
		{
			workstepKeys.add(new JDFAttributeMap());
		}
		for (final JDFAttributeMap part : workstepKeys)
		{

			final ResourceHelper comph = createSet.getCreateResource(part, true);
			if (amount > 0)
			{
				comph.setAmount(amount, null, true);
			}
			if (waste > 0)
			{
				comph.setAmount(amount, null, false);
			}
			final JDFComponent comp = (JDFComponent) comph.getResource();
			paperHelper.ensureReference(comp);
			final JDFMedia m = (JDFMedia) paperHelper.getResource();
			final JDFXYPair dim = m.getDimension();

			final double thick = m.getThickness() * eParserUnit.mu.getFactor();
			final JDFShape s = new JDFShape(dim);
			s.setZ(thick);
			comp.setDimensions(s);
		}
		return createSet;

	}

	SetHelper createPlates()
	{
		final SetHelper plateSet = helper.getCreateSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		final VJDFAttributeMap parts = getParts();
		for (final JDFAttributeMap part : parts)
		{
			final JDFExposedMedia xm = (JDFExposedMedia) plateSet.getCreateResource(part, true).getResource();
			plateHelper.ensureReference(xm);
		}

		return plateSet;
	}

	ResourceHelper createPlateMedia()
	{
		final SetHelper plh = helper.appendSet(ElementName.MEDIA, EnumUsage.Input);
		final ResourceHelper plateHelper = plh.getCreateResource();
		final JDFMedia plate = (JDFMedia) plateHelper.getCreateResource();
		plate.setMediaType(EnumMediaType.Plate);
		plate.setDimensionCM(105, 74);
		return plateHelper;
	}

	ResourceHelper createPaper()
	{
		final SetHelper pph = helper.appendSet(ElementName.MEDIA, null);
		final ResourceHelper ppr = pph.getCreateResource();
		final JDFMedia paper = (JDFMedia) ppr.getCreateResource();
		paper.setMediaType(EnumMediaType.Paper);
		paper.setDimensionCM(100, 70);
		return ppr;
	}

	SetHelper createColorantControl()
	{
		final SetHelper createSet = helper.getCreateSet(ElementName.COLORANTCONTROL, EnumUsage.Input);
		final VString partsf = getParts().getOverlapMaps(new JDFAttributeMap(AttributeName.SIDE, EnumSide.Front.name())).getPartValues(AttributeName.SEPARATION,
				true);
		final VString partsb = getParts().getOverlapMaps(new JDFAttributeMap(AttributeName.SIDE, EnumSide.Back.name())).getPartValues(AttributeName.SEPARATION,
				true);
		final VJDFAttributeMap wsmaps = getWorkstepParts();
		if (ContainerUtil.equals(partsf, partsb))
		{
			wsmaps.removeKey(AttributeName.SIDE);
		}
		if (wsmaps.getPartValues(AttributeName.SHEETNAME, true).size() <= 1)
		{
			wsmaps.removeKey(AttributeName.SHEETNAME);
		}
		if (wsmaps.isEmpty())
		{
			wsmaps.add(new JDFAttributeMap());
		}
		for (final JDFAttributeMap wsMap : wsmaps)
		{
			final ResourceHelper resh = createSet.getCreatePartition(wsMap, true);
			final JDFColorantControl cc = (JDFColorantControl) resh.getResource();
			final VString parts = EnumSide.Back.name().equals(wsMap.get(AttributeName.SIDE)) ? partsb : partsf;
			cc.setColorantParamSeparations(parts);
		}

		return createSet;
	}

	SetHelper createCPPSet()
	{
		final SetHelper cppSet = helper.getCreateSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		final VJDFAttributeMap workstepParts = getWorkstepParts();
		for (final JDFAttributeMap part : workstepParts)
		{
			final JDFConventionalPrintingParams cpp = (JDFConventionalPrintingParams) cppSet.getCreateResource(part, true).getResource();
			if (icsLevel >= 2)
			{
				cpp.setSheetLay(EnumSheetLay.Left);
			}
			cpp.setWorkStyle(perfecting ? EnumWorkStyle.Perfecting
					: (workstepParts.getPartValues(AttributeName.SIDE, true).size() == 1 ? EnumWorkStyle.Simplex : EnumWorkStyle.WorkAndBack));

		}
		return cppSet;
	}

	SetHelper createPreviewSet()
	{
		if (previewGeneration)
		{
			final SetHelper previewSet = helper.getCreateSet(ElementName.PREVIEW, EnumUsage.Input);
			final VJDFAttributeMap parts = getParts();
			for (final JDFAttributeMap part : parts)
			{
				final JDFPreview preview = (JDFPreview) previewSet.getCreateResource(part, true).getResource();
				preview.setPreviewFileType(EnumPreviewFileType.PNG);
				preview.setFileSpecURL("https://previewserver/previews/" + getJobID() + "/" + part.get(AttributeName.SIDE) + "."
						+ part.get(AttributeName.SEPARATION) + ".png");
			}
			return previewSet;
		}
		return null;

	}

	SetHelper createColorSet()
	{
		final SetHelper set = helper.getCreateSet(ElementName.COLOR, EnumUsage.Input);
		for (final String sep : getParts().getPartValues(AttributeName.SEPARATION, true))
		{
			final JDFColor c = (JDFColor) set.getCreateResource(new JDFAttributeMap(AttributeName.SEPARATION, sep), true).getResource();
			c.setActualColorName(sep);
			c.setCMYK(c.getCMYK());
		}
		return set;
	}

	String getJobID()
	{
		return jobID;
	}

	void setJobID(String jobID)
	{
		this.jobID = jobID;
	}

}
