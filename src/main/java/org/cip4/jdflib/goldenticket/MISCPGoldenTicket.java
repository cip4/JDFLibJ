/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.goldenticket;

import java.io.File;

import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumPrintingType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoPreview.EnumPreviewUsage;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.ICSConstants;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFPreview.EnumPreviewFileType;
import org.cip4.jdflib.resource.process.prepress.JDFInk;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * @author Rainer Prosi class that generates golden tickets based on ICS levels etc
 */
public class MISCPGoldenTicket extends MISGoldenTicket
{
	/**
	 *
	 */
	public static final String MISCPS_PRINTING = ICSConstants.MISCPS_PRINTING;
	/**
	 *
	 */
	public boolean splitSheets = false;

	/**
	 *
	 */
	public VString inks = null;
	/**
	 *
	 */
	public VString inkProductIDs = null;

	/**
	 * if true, previews are linked
	 */
	public boolean previewAvailable = false;
	/**
	 * file location of preview files
	 */
	public String previewShare = null;
	/**
	 * if true: ppf, else png preview names are generated
	 */
	public boolean bPPF = false;

	/**
	 * create a BaseGoldenTicket
	 *
	 * @param _icsLevel the level to init to (1,2 or 3)
	 * @param jdfVersion the version to generate a golden ticket for
	 * @param _jmfLevel level of jmf ICS to support
	 * @param _misLevel level of MIS ICS to support
	 * @param isGrayBox if true, write a grayBox
	 * @param vPartMap the partmap vector for this node
	 */
	public MISCPGoldenTicket(final int _icsLevel, final EnumVersion jdfVersion, final int _jmfLevel, final int _misLevel, final boolean isGrayBox, final VJDFAttributeMap vPartMap)
	{
		super(_misLevel, jdfVersion, _jmfLevel);
		icsLevel = _icsLevel;
		vParts = vPartMap;
		setup();
		grayBox = isGrayBox;
	}

	@Override
	protected void fillCatMaps()
	{
		super.fillCatMaps();
		catMap.put(ICSConstants.MISCPS_PRINTING, new VString("InkZoneCalculation ConventionalPrinting", null));
	}

	/**
	 * create a BaseGoldenTicket
	 *
	 * @param parent
	 *
	 */
	public MISCPGoldenTicket(final MISCPGoldenTicket parent)
	{
		super(parent);
		grayBox = parent.grayBox;
		setup();
	}

	/**
	 *
	 */
	private void setup()
	{
		if (grayBox)
		{
			setCategory(ICSConstants.MISCPS_PRINTING);
		}
		// theStatusCounter.addIgnorePart(EnumPartIDKey.Side);
		theStatusCounter.addIgnorePart(EnumPartIDKey.Separation);
		previewShare = UrlUtil.fileToUrl(UrlUtil.urlToFile("file:/host/root/preview"), false);
	}

	/**
	 * @see org.cip4.jdflib.goldenticket.BaseGoldenTicket#getICSVersions()
	 * @return the ics versions
	 */
	@Override
	public VString getICSVersions()
	{
		final VString v = super.getICSVersions();
		final String icsTag = "MISCPS_L" + icsLevel + "-" + theVersion.getName();
		v.appendUnique(icsTag);
		return v;
	}

	/**
	 * initializes this node to a given ICS version
	 *
	 *
	 */
	@Override
	public void init()
	{
		initColsFromParent();
		initAmountsFromParent();
		if (partIDKeys == null)
		{
			partIDKeys = new VString("SignatureName SheetName Side Separation", null);
		}

		// put level methods?

		final int ncols = getNCols();
		while (cols.size() > ncols && ncols > 0)
		{
			cols.remove(ncols);
		}

		if (icsLevel < 0)
		{
			return;
		}

		if (!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
		{
			theNode.setDescriptiveName("MISCPS Golden Ticket Example Job - version: " + JDFAudit.software());
		}

		if (!grayBox)
		{
			theNode.setType(EnumType.ConventionalPrinting);
		}
		initColorantControl();
		initConventionalPrintingParams();
		final JDFMedia m = initPaperMedia();
		initPlateXM(EnumUsage.Input);
		initDevice(thePreviousNode);
		final JDFComponent c = initOutputComponent();
		initInk();
		super.init();
		initPreview();
		setActivePart(vParts, true);
		theStatusCounter.setTrackWaste(c.getID(), true);
		theStatusCounter.setTrackWaste(m.getID(), true);
	}

	/**
	 * recalculate ncols from parent color intent if it exists
	 */
	private void initColsFromParent()
	{
		if (theParentProduct == null)
		{
			return;
		}
		final JDFColorIntent ci = (JDFColorIntent) theParentProduct.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
		if (ci == null)
		{
			return;
		}
		final int c = ci.getNumColors();
		if (c > 0)
		{
			nCols[0] = nCols[1] = c;
		}
	}

	/**
	 * recalculate ncols from parent color intent if it exists
	 */
	private void initAmountsFromParent()
	{
		if (theParentProduct == null)
		{
			return;
		}
		final JDFComponent c = (JDFComponent) theParentProduct.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
		final JDFResourceLink cl = theParentProduct.getLink(c, EnumUsage.Output);
		if (cl == null)
		{
			return;
		}
		final double amount = cl.getAmount(null);
		if (amount > 0)
		{
			good = (int) amount;
			waste = (int) (good * 0.1);
		}
	}

	/**
	 * @see org.cip4.jdflib.goldenticket.BaseGoldenTicket#setActivePart(org.cip4.jdflib.datatypes.VJDFAttributeMap, boolean)
	 * @param vp
	 * @param bFirst
	 */
	@Override
	public void setActivePart(final VJDFAttributeMap vp, final boolean bFirst)
	{
		amountLinks = null;
		addAmountLink("Media:Input");
		addAmountLink("Component:Output");
		super.setActivePart(vp, bFirst);
	}

	/**
	 *
	 */
	@Override
	protected JDFMedia initPaperMedia()
	{
		final JDFMedia m = super.initPaperMedia();
		JDFResourceLink rl = theNode.getLink(m, null);
		if (rl == null)
		{
			rl = theNode.linkResource(m, EnumUsage.Input, null);
		}
		if (vParts != null)
		{
			final VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation", " "));
			if (reducedMap != null)
			{
				final int size = reducedMap.size();
				for (int i = 0; i < size; i++)
				{
					final JDFAttributeMap part = reducedMap.elementAt(i);
					final JDFAttributeMap newMap = new JDFAttributeMap(part);
					newMap.put(AttributeName.CONDITION, "Good");
					rl.setAmount(good, newMap);
					rl.setMaxAmount(good + waste, newMap);
					newMap.put(AttributeName.CONDITION, "Waste");
					rl.setMaxAmount(waste, newMap);
				}
			}

		}

		return m;
	}

	protected void initInk()
	{
		if (inks == null)
		{
			return;
		}
		final JDFInk ink = (JDFInk) theNode.getCreateResource(ElementName.INK, EnumUsage.Input, 0);
		final int ncols = getNCols();
		for (int j = 0; j < ncols; j++)
		{
			final JDFInk inkp = (JDFInk) ink.addPartition(EnumPartIDKey.Separation, cols.get(j));
			inkp.setInkName(inks.elementAt(j));
			if ((cols.get(j).toLowerCase().indexOf("varnish") >= 0) || (inks.get(j).toLowerCase().indexOf("varnish") >= 0))
			{
				inkp.setFamily("Varnish");
			}
			if (inkProductIDs != null)
			{
				inkp.setProductID(inkProductIDs.get(j));
			}
		}
	}

	/**
	 *
	 */
	protected void initPreview()
	{
		JDFResourceLink rlP = null;
		if (theNode.getCombinedProcessIndex(EnumType.InkZoneCalculation, 0) < 0)
		{
			return;
		}
		if (thePreviousNode != null)
		{
			rlP = theNode.linkResource(thePreviousNode.getResource(ElementName.PREVIEW, EnumUsage.Output, 0), EnumUsage.Input, null);
		}
		if (rlP == null && theParentNode != null)
		{
			rlP = theNode.linkResource(theParentNode.getResource(ElementName.PREVIEW, EnumUsage.Input, 0), EnumUsage.Input, null);
		}

		final JDFPreview pv = (JDFPreview) theNode.getCreateResource(ElementName.PREVIEW, EnumUsage.Input, 0);
		pv.setResStatus(EnumResStatus.Incomplete, false);
		pv.setPreviewUsage(EnumPreviewUsage.Separation);
		pv.setPartUsage(EnumPartUsage.Explicit);
		pv.setPreviewFileType(bPPF ? EnumPreviewFileType.CIP3Single : EnumPreviewFileType.PNG);

		final VJDFAttributeMap reducedMap = bPPF ? getReducedMap(new VString("Side Separation", " ")) : vParts;
		if (reducedMap != null)
		{
			final int size = reducedMap.size();
			for (int i = 0; i < size; i++)
			{
				final JDFAttributeMap part = new JDFAttributeMap(reducedMap.elementAt(i));
				final JDFPreview previewPartition = (JDFPreview) pv.getCreatePartition(part, partIDKeys);
				if (bPPF)
				{
					preparePreview(previewPartition);
				}
				else
				{
					for (int j = 0; j < getNCols(); j++)
					{
						part.put(EnumPartIDKey.Separation, cols.get(j));
						final JDFPreview sepPreview = (JDFPreview) previewPartition.getCreatePartition(part, partIDKeys);
						preparePreview(sepPreview);
					}
				}
			}
		}
	}

	/**
	 * prepare a preview partition
	 *
	 * @param previewPartition
	 */
	private void preparePreview(final JDFPreview previewPartition)
	{
		if (previewAvailable)
		{
			setPreviewURL(previewPartition);
			previewPartition.setResStatus(EnumResStatus.Available, false);
		}
		else
		{
			previewPartition.setResStatus(EnumResStatus.Incomplete, false);
		}
	}

	/**
	 *
	 */
	protected void initConventionalPrintingParams()
	{
		if (theParentNode != null)
		{
			theNode.linkResource(theParentNode.getResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input, 0), EnumUsage.Input, null);
		}
		final JDFConventionalPrintingParams cpp = (JDFConventionalPrintingParams) theNode.getCreateResource(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input, 0);
		cpp.setPrintingType(EnumPrintingType.SheetFed);
		cpp.setWorkStyle(workStyle);
		cpp.setResStatus(EnumResStatus.Available, false);
	}

	@Override
	protected JDFDevice initDevice(final JDFNode reuseNode)
	{
		if (misICSLevel < 2)
		{
			return null;
		}

		super.initDevice(reuseNode);

		final JDFDevice dev = (JDFDevice) theNode.getCreateResource(ElementName.DEVICE, EnumUsage.Input, 0);
		final VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation", " "));
		if (reducedMap != null)
		{
			if (devID != null && splitSheets)
			{
				final int size = reducedMap.size();
				for (int i = 0; i < size; i++)
				{
					final JDFAttributeMap part = reducedMap.elementAt(i);
					final JDFDevice devPart = (JDFDevice) dev.getCreatePartition(part, partIDKeys);

					devPart.setResStatus(EnumResStatus.Available, false);
					devPart.setDeviceID(devID);
				}
			}
		}

		return dev;
	}

	/**
	 *
	 */
	protected void makeReadyColorantControl()
	{
		final JDFColorantControl cc = (JDFColorantControl) theExpandedNode.getCreateResource(ElementName.COLORANTCONTROL, EnumUsage.Input, 0);
		final JDFColorPool cp = cc.getCreateColorPool();
		for (int i = 0; i < getNCols(); i++)
		{
			final String name = cols.get(i);
			final JDFColor c = cp.getCreateColorWithName(name, null);
			c.setActualColorName(colsActual.get(i));
		}
	}

	/**
	 * prepare an mis fuzzy node and make it runnable by the device
	 *
	 */
	@Override
	public void makeReady()
	{
		super.makeReady();

		makeReadyPreview();
		makeReadyColorantControl();
	}

	/**
	 *
	 */
	private void makeReadyPreview()
	{
		final JDFPreview pv = (JDFPreview) theNode.getResource(ElementName.PREVIEW, EnumUsage.Input, 0);
		final VElement v = pv == null ? new VElement() : pv.getLeaves(false);
		for (int i = 0; i < v.size(); i++)
		{
			final JDFPreview pvp = (JDFPreview) v.elementAt(i);
			setPreviewURL(pvp);
			pvp.setResStatus(EnumResStatus.Available, true);
		}
	}

	/**
	 * @param previewLeaf
	 */
	private void setPreviewURL(final JDFPreview previewLeaf)
	{
		final File share = UrlUtil.urlToFile(previewShare);
		File file;
		if (bPPF)
		{
			file = new File(previewLeaf.getSheetName() + ".ppf");
		}
		else
		{
			file = new File(previewLeaf.getSheetName() + "_" + previewLeaf.getSide().getName().substring(0, 1) + "_" + previewLeaf.getSeparation() + ".png");

		}
		file = FileUtil.getFileInDirectory(share, file);
		previewLeaf.setURL(UrlUtil.fileToUrl(file, false));
		previewLeaf.setPreviewUsage(EnumPreviewUsage.Separation);
		previewLeaf.setPreviewFileType(EnumPreviewFileType.CIP3Single);
	}

	/**
	 *
	 * @return
	 */
	@Override
	protected JDFNodeInfo initNodeInfo()
	{
		final JDFNodeInfo ni = super.initNodeInfo();
		if (vParts != null)
		{
			final VJDFAttributeMap reducedMap = new VJDFAttributeMap(vParts);
			final VString reduceKeys = new VString(partIDKeys);
			// simplex and perfecting are one run only
			if (EnumWorkStyle.Simplex.equals(workStyle) || EnumWorkStyle.Perfecting.equals(workStyle))
			{
				reduceKeys.remove(AttributeName.SIDE);
			}
			reducedMap.reduceMap(reduceKeys.getSet());
			theNode.setPartStatus(reducedMap, EnumNodeStatus.Waiting, null);
			for (int i = 0; i < reducedMap.size(); i++)
			{
				final JDFAttributeMap part = reducedMap.elementAt(i);
				final JDFNodeInfo niPart = (JDFNodeInfo) ni.getCreatePartition(part, partIDKeys);
				niPart.setDescriptiveName("Printing for" + part.toString());
			}
		}
		return ni;
	}

	@Override
	protected void runphases(final int pgood, final int pwaste, final boolean bOutAvail, final boolean bFirst)
	{
		theStatusCounter.setPhase(EnumNodeStatus.InProgress, "Good", EnumDeviceStatus.Running, "Printing");
		runSinglePhase(pgood, pwaste, bOutAvail, bFirst);
		finalizeProcess(); // prior to processRun
		theStatusCounter.setPhase(EnumNodeStatus.Completed, "Done", EnumDeviceStatus.Idle, "Waiting");
	}

	/**
	 * @see org.cip4.jdflib.goldenticket.MISGoldenTicket#assign(org.cip4.jdflib.node.JDFNode)
	 * @param node
	 */
	@Override
	public void assign(final JDFNode node)
	{

		super.assign(node);
		theNode.getCreateNodeInfo().setPartIDKeys(partIDKeys);
	}

	/**
	 * zapp any direct links to colorpool
	 */
	@Override
	protected void initColorantControl()
	{
		super.initColorantControl();
		final JDFColorPool cp = (JDFColorPool) theNode.getResource(ElementName.COLORPOOL, EnumUsage.Input, 0);
		if (cp != null)
		{
			final JDFResourceLink rl = theNode.getLink(cp, EnumUsage.Input);
			if (rl != null)
			{
				rl.deleteNode();
			}
		}
	}
}
