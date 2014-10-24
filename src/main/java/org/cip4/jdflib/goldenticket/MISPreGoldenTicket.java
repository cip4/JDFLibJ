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

import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoBinderySignature.EnumBinderySignatureType;
import org.cip4.jdflib.auto.JDFAutoIdentificationField.EnumEncoding;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoPreview.EnumPreviewUsage;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBarcodeProductionParams;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPreview;
import org.cip4.jdflib.resource.process.JDFPreview.EnumPreviewFileType;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.prepress.JDFPreviewGenerationParams;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi class that generates golden tickets based on ICS levels etc
 */
public class MISPreGoldenTicket extends MISGoldenTicket
{
	/** */
	public static final String MISPRE_CONTENTCREATION = "MISPRE.ContentCreation";
	/** */
	public static final String MISPRE_IMPOSITIONPREPARATION = "MISPRE.ImpositionPreparation";
	/** */
	public static final String MISPRE_PREPRESSPREPARATION = "MISPRE.PrePressPreparation";
	/** */
	public static final String MISPRE_IMPOSITIONRIPING = "MISPRE.ImpositionRIPing";
	/** */
	public static final String MISPRE_PLATEMAKING = "MISPRE.PlateMaking";
	/** */
	public static final String MISPRE_PLATESETTING = "MISPRE.PlateSetting";
	/** */
	public boolean bStripping = false;
	/** */
	public boolean bSingleSided = false;

	/**
	 * create a BaseGoldenTicket
	 * @param previous 
	 * @param _vparts 
	 * 
	 */
	public MISPreGoldenTicket(final MISPreGoldenTicket previous, final VJDFAttributeMap _vparts)
	{
		super(previous.misICSLevel, previous.theVersion, previous.jmfICSLevel);

		partIDKeys = new VString(previous.partIDKeys);
		vParts = _vparts == null ? new VJDFAttributeMap(previous.vParts) : _vparts;
		icsLevel = previous.icsLevel;
		nCols = previous.nCols;
		workStyle = previous.workStyle;

		thePreviousNode = previous.theNode;
		theParentNode = previous.theParentNode;
	}

	/**
	 * create a BaseGoldenTicket
	 * @param parent 
	 * 
	 */
	public MISPreGoldenTicket(final MISPreGoldenTicket parent)
	{
		super(parent);
	}

	/**
	 * 
	 */
	@Override
	protected void fillCatMaps()
	{
		super.fillCatMaps();
		catMap.put(MISPRE_CONTENTCREATION, new VString(EnumType.LayoutElementProduction.getName(), null));

		catMap.put(MISPRE_IMPOSITIONPREPARATION, new VString("ImpositionPreparation", null));

		if (!bStripping)
		{
			catMap.put(MISPRE_PREPRESSPREPARATION, new VString("PrePressPreparation", null));
		}
		else
		{
			catMap.put(MISPRE_PREPRESSPREPARATION, new VString("Stripping", null));
		}

		catMap.put(MISPRE_IMPOSITIONRIPING, new VString("Imposition RIPing PreviewGeneration", null));
		catMap.put(MISPRE_PLATEMAKING, new VString("Imposition RIPing PreviewGeneration ImageSetting", null));
		catMap.put(MISPRE_PLATESETTING, new VString("ImageSetting", null));
	}

	/**
	 * create a BaseGoldenTicket
	 * 
	 * @param _icsLevel the level to init to (1,2 or 3)
	 * @param jdfVersion the version to generate a golden ticket for
	 * @param _jmfLevel level of jmf ICS to support
	 * @param _misLevel level of MIS ICS to support
	 * @param vPartMap list of parts to process
	 */
	public MISPreGoldenTicket(final int _icsLevel, final EnumVersion jdfVersion, final int _jmfLevel, final int _misLevel, final VJDFAttributeMap vPartMap)
	{
		super(_misLevel, jdfVersion, _jmfLevel);

		partIDKeys = new VString("SignatureName,SheetName,Side,Separation", ",");
		vParts = vPartMap;
		icsLevel = _icsLevel;
	}

	/**
	 * initializes this node to a given ICS version
	 * 
	 *  
	 */
	@Override
	public void init()
	{
		theNode.setType(EnumType.ProcessGroup);
		initColsFromParent();
		theNode.linkOutputs(thePreviousNode);

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
		final String icsTag = "MISPre_L" + icsLevel + "-" + theVersion.getName();
		theNode.appendAttribute(AttributeName.ICSVERSIONS, icsTag, null, " ", true);
		if (!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
		{
			theNode.setDescriptiveName("MISPre Golden Ticket Example Job - version: " + JDFAudit.software());
		}

		final String cat = getCategory();
		if (MISPRE_CONTENTCREATION.equals(cat))
		{
			initGBContentCreation();
		}
		else if (MISPRE_PREPRESSPREPARATION.equals(cat))
		{
			initGBPrePressPreparation();
		}
		else if (MISPRE_IMPOSITIONPREPARATION.equals(cat))
		{
			initGBImpositionPreparation();
		}
		else if (MISPRE_IMPOSITIONRIPING.equals(cat))
		{
			initGBImpositionRIPing();
		}
		else if (MISPRE_PLATESETTING.equals(cat))
		{
			initGBPlateSetting();
		}
		else if (MISPRE_PLATEMAKING.equals(cat))
		{
			initGBPlateMaking();
		}
		super.init();
		setActivePart(vParts, true);
	}

	/**
	 * 
	 */
	private void initGBImpositionRIPing()
	{
		linkPrepressPrepRunLists();
		initColorantControl();
		initDevice(null);
		initPreviewSep();
		initPreviewComp();
		initConduitRunListOut();
	}

	/**
	 * 
	 */
	private void linkPrepressPrepRunLists()
	{
		final VElement vprepreviousNode = theNode.getJDFRoot().getvJDFNode(null, null, false);
		if (vprepreviousNode != null && vprepreviousNode.size() > 0)
		{
			for (int i = 0; i < vprepreviousNode.size(); i++)
			{
				final JDFNode node = (JDFNode) vprepreviousNode.get(i);
				final VString types = node.getTypes();
				if (types != null && types.contains("PrePressPreparation"))
				{
					theNode.linkOutputs(node);
				}
			}
		}
	}

	/**
	 * 
	 */
	private void initGBPlateMaking()
	{
		linkPrepressPrepRunLists();
		initColorantControl();
		initDevice(null);
		initPreviewSep();
		initPreviewComp();
		initPreviewGenerationParams();
		initPlateXM(EnumUsage.Output);
	}

	/**
	 * 
	 */
	private void initPreviewGenerationParams()
	{
		// no requirements

	}

	/**
	 * 
	 */
	private void initGBPlateSetting()
	{
		initColorantControl();
		initDevice(null);
		initPlateXM(EnumUsage.Output);
	}

	/**
	 * 
	 */
	private void initGBPrePressPreparation()
	{
		if (thePreviousNode != null)
		{
			theNode.linkOutputs(thePreviousNode);
		}
		else
		{
			initDocumentRunList();
		}
		final JDFRunList rl = initConduitRunListOut();
		theNode.getLink(rl, EnumUsage.Output).setProcessUsage(EnumProcessUsage.Document);
	}

	/**
	 * @param catalog 
	 * @return 
	 * 
	 */
	private JDFBinderySignature initBinderySignature(final String catalog)
	{
		final JDFBinderySignature bs = (JDFBinderySignature) theNode.addResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
		bs.setBinderySignatureType(EnumBinderySignatureType.Fold);
		bs.setFoldCatalog(catalog);
		final int f = StringUtil.parseInt(StringUtil.token(catalog, 0, "-").substring(1), 0) / 2;
		if (f > 0)
		{
			int fx = f;
			int fy = 1;
			if (f >= 8)
			{
				fx /= 2;
				fy *= 2;
			}
			bs.setNumberUp(new JDFXYPair(fx, fy));
		}

		return bs;
	}

	/**
	 * 
	 */
	private void initStrippingParams()
	{
		final JDFStrippingParams sp = (JDFStrippingParams) theNode.getCreateResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		sp.setDescriptiveName("Impositioning for job " + theNode.getJobID(true));
		sp.setWorkStyle(org.cip4.jdflib.auto.JDFAutoStrippingParams.EnumWorkStyle.getEnum(workStyle.getValue()));
		// VJDFAttributeMap reduceMap=
		getReducedMap(new VString("Separation PartVersion", null));
		final JDFBinderySignature bs0 = (JDFBinderySignature) theNode.getResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input, 0);
		JDFBinderySignature bs1 = (JDFBinderySignature) theNode.getResource(ElementName.BINDERYSIGNATURE, EnumUsage.Input, 1);
		if (bs1 == null)
		{
			bs1 = bs0;
		}

		if (vParts != null)
		{
			final VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation PartVersion", " "));
			if (reducedMap != null)
			{
				final int size = reducedMap.size();
				for (int i = 0; i < size; i++)
				{
					final JDFAttributeMap part = reducedMap.elementAt(i);
					final JDFResource partSP = sp.getCreatePartition(part, partIDKeys);
					final JDFBinderySignature bs = partSP.getSheetName().toLowerCase().contains("cover") ? bs0 : bs1;
					partSP.refElement(bs);
					final JDFResourceLink rl = theNode.getLink(bs, null);
					if (rl != null)
					{
						rl.deleteNode();
					}
				}
			}
		}
		else
		{
			sp.refElement(bs0);
		}

		sp.appendDevice().setDeviceID("Press_ID");
		sp.appendPosition().setRelativeBox(new JDFRectangle(0, 0, 0.5, 1));
		sp.appendPosition().setRelativeBox(new JDFRectangle(0.5, 1, 1, 1));

		sp.appendStripCellParams().setTrimSize(new JDFXYPair(8.5 * 72, 11 * 72));

		sp.refElement(initPaperMedia());
	}

	/**
	 * 
	 */
	private void initGBImpositionPreparation()
	{
		theNode.setTypes(new VString(bStripping ? "Stripping" : "ImpositionPreparation", null));

		if (bStripping)
		{
			initBinderySignature("F4-1");
			initBinderySignature("F16-2");
			initStrippingParams();
			initAssembly();
		}

		initOutputLayout();
		final JDFRunList rlMarks = initConduitRunListOut();
		theNode.getLink(rlMarks, EnumUsage.Output).setProcessUsage(EnumProcessUsage.Marks);

	}

	/**
	 * 
	 */
	private void initAssembly()
	{
		final JDFAssembly assem = (JDFAssembly) theNode.getCreateResource(ElementName.ASSEMBLY, EnumUsage.Input, 0);
		assem.setOrder(EnumOrder.Collecting);

	}

	/**
	 * 
	 */
	private void initOutputLayout()
	{
		final JDFLayout lo = (JDFLayout) theNode.getCreateResource(ElementName.LAYOUT, EnumUsage.Output, 0);
		lo.setDescriptiveName("Conduit Layout");
		lo.setResStatus(EnumResStatus.Unavailable, false);
		final VString vSigSheetSide = new VString("SignatureName SheetName Side", null);
		lo.setPartIDKeys(vSigSheetSide);
		for (int i = 0; i < vParts.size(); i++)
		{
			lo.getCreatePartition(vParts.elementAt(i), vSigSheetSide);
		}
	}

	/**
	 * 
	 */
	private void initGBContentCreation()
	{
		theNode.setTypes(new VString(EnumType.LayoutElementProduction.getName(), null));
		final JDFRunList ruli = initDocumentRunList();
		final JDFResourceLink rl = theNode.getLink(ruli, EnumUsage.Input);
		rl.setProcessUsage((EnumProcessUsage) null);
		initLayoutElementProductionParams();
		initConduitRunListOut();
	}

	/**
	 * @return 
	 * 
	 */
	private JDFRunList initConduitRunListOut()
	{
		final JDFRunList rl = (JDFRunList) theNode.getCreateResource(ElementName.RUNLIST, EnumUsage.Output, 0);
		rl.setResStatus(EnumResStatus.Unavailable, false);
		rl.setDescriptiveName("Marks RunList");
		return rl;
	}

	/**
	 * 
	 */
	private void initLayoutElementProductionParams()
	{
		final JDFLayoutElementProductionParams lep = (JDFLayoutElementProductionParams) theNode.getCreateResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS, EnumUsage.Input, 0);
		final JDFBarcodeProductionParams bp = lep.getCreateLayoutElementPart(0).appendBarcodeProductionParams();
		final JDFIdentificationField idf = bp.appendIdentificationField();
		idf.setEncoding(EnumEncoding.Barcode);
		idf.setEncodingDetails("EAN");
		idf.setValue("123456");
	}

	/**
	 * recalculate ncols from parent color intent if it exists
	 */
	private void initColsFromParent()
	{
		if (theParentNode == null)
		{
			return;
		}
		final JDFColorIntent ci = (JDFColorIntent) theParentNode.getResource(ElementName.COLORINTENT, EnumUsage.Input, 0);
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
	 * simulate execution of this node the internal node will be modified to reflect the excution
	 */
	@Override
	public void execute(final VJDFAttributeMap parts, final boolean outputAvailable, final boolean bFirst)
	{
		VJDFAttributeMap partsLocal = parts;

		partsLocal = null; // alwways execute all in pp
		setActivePart(partsLocal, bFirst);
		super.execute(partsLocal, outputAvailable, bFirst);
		if (MISPRE_IMPOSITIONPREPARATION.equals(getCategory()))
		{
			executeGBImpositionPreparation();
		}
	}

	/**
	 * 
	 */
	private void executeGBImpositionPreparation()
	{
		executeLayout();
		executeRunList(EnumUsage.Input);
		executeMarksRunList(EnumUsage.Output);

	}

	/**
	 * @param usage 
	 * 
	 */
	private void executeMarksRunList(final EnumUsage usage)
	{
		JDFRunList rl = (JDFRunList) theExpandedNode.getResource(ElementName.RUNLIST, usage, 0);
		if (!"Marks".equals(theExpandedNode.getLink(rl, usage).getProcessUsage()))
		{
			rl = (JDFRunList) theExpandedNode.getResource(ElementName.RUNLIST, usage, 1);
		}
		if (!"Marks".equals(theExpandedNode.getLink(rl, usage).getProcessUsage()))
		{
			rl = null;
		}

		if (rl != null && !rl.hasChildElement(ElementName.LAYOUTELEMENT, null))
		{
			rl.addPDF("./folder/TheMarks.pdf", 0, -1);
		}
	}

	/**
	 * emulate execution of a runlist by making it available
	 * @param usage 
	 */
	private void executeRunList(final EnumUsage usage)
	{
		final JDFRunList rl = (JDFRunList) theExpandedNode.getResource(ElementName.RUNLIST, usage, 0);
		if (rl == null)
		{
			return;
		}
		if (!rl.hasChildElement(ElementName.LAYOUTELEMENT, null))
		{
			rl.addPDF("./folder/Thedoc.pdf", 0, -1);
		}
		if (EnumUsage.Output.equals(usage))
		{
			rl.setResStatus(EnumResStatus.Available, true);
		}
	}

	/**
	 * 
	 */
	private void executeLayout()
	{
		final JDFLayout lo = (JDFLayout) theExpandedNode.getResource(ElementName.LAYOUT, EnumUsage.Output, 0);
		if (lo != null && vParts != null)
		{
			final VJDFAttributeMap reducedMap = getReducedMap(new VString("Separation PartVersion", " "));
			lo.setResStatus(EnumResStatus.Available, true);
			if (reducedMap != null)
			{
				final int size = reducedMap.size();
				for (int i = 0; i < size; i++)
				{
					final JDFAttributeMap part = reducedMap.elementAt(i);
					if (bSingleSided == true && "Back".equals(part.get("Side")))
					{
						continue;
					}

					final JDFLayout partLO = (JDFLayout) lo.getCreatePartition(part, partIDKeys);
					for (int j = 0; j < 4; j++)
					{
						final JDFContentObject co = partLO.appendContentObject();
						co.setCTM(new JDFMatrix(1 + 10 * j, 2 + 20 * j, 3 + 30 * j, 4 + 40 * j, 5 + 50 * j, 6 + 0 * j));
						co.setOrd(j + i * 4);
					}
				}
			}
		}
	}

	/**
	 *  
	 */
	protected void initPreviewSep()
	{
		if (theParentNode != null)
		{
			theNode.ensureLink(theParentNode.getResource(ElementName.PREVIEW, EnumUsage.Output, 0), EnumUsage.Output, null);
		}
		final JDFPreview pv = (JDFPreview) theNode.getCreateResource(ElementName.PREVIEW, EnumUsage.Output, 0);
		pv.setResStatus(EnumResStatus.Incomplete, false);
		pv.setPreviewUsage(EnumPreviewUsage.Separation);
		pv.setPartUsage(EnumPartUsage.Explicit);
		pv.setPreviewFileType(EnumPreviewFileType.PNG);

		if (vParts != null)
		{
			for (int i = 0; i < vParts.size(); i++)
			{
				final JDFAttributeMap part = vParts.elementAt(i);
				final JDFPreview pvp = (JDFPreview) pv.getCreatePartition(part, partIDKeys);
				final int ncols = "Front".equals(part.get("Side")) ? nCols[0] : nCols[1];

				for (int j = 0; j < ncols; j++)
				{
					pvp.getCreatePartition(EnumPartIDKey.Separation, cols.get(j), partIDKeys);
					pvp.setResStatus(EnumResStatus.Incomplete, false);
				}
			}
		}
	}

	/**
	 *  
	 */
	protected void initPreviewComp()
	{
		if (theParentNode != null)
		{
			theNode.ensureLink(theParentNode.getResource(ElementName.PREVIEW, EnumUsage.Output, 0), EnumUsage.Output, null);
		}
		final JDFPreview pv = (JDFPreview) theNode.getCreateResource(ElementName.PREVIEW, EnumUsage.Output, 0);
		pv.setResStatus(EnumResStatus.Incomplete, false);
		pv.setPreviewUsage(EnumPreviewUsage.Viewable);
		pv.setPartUsage(EnumPartUsage.Explicit);
		pv.setPreviewFileType(EnumPreviewFileType.PNG);

		if (vParts != null)
		{
			final VJDFAttributeMap vRedParts = getReducedMap(new VString("Separation", null));
			for (int i = 0; i < vRedParts.size(); i++)
			{
				final JDFAttributeMap part = vParts.elementAt(i);
				final JDFPreview pvp = (JDFPreview) pv.getCreatePartition(part, partIDKeys);
				pvp.setResStatus(EnumResStatus.Incomplete, false);

			}
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
		makeReadyPreviewGeneration();
	}

	/**
	 * 
	 */

	/**
	 * 
	 */
	private void makeReadyPreviewGeneration()
	{
		final VString v = theExpandedNode.getAllTypes();
		if (v != null && v.contains(EnumType.PreviewGeneration.getName()))
		{
			final JDFPreviewGenerationParams pgp = (JDFPreviewGenerationParams) theExpandedNode.getCreateResource(ElementName.PREVIEWGENERATIONPARAMS, EnumUsage.Input, 0);
			pgp.setPreviewUsage(JDFPreviewGenerationParams.EnumPreviewUsage.Separation);
		}
	}

	/**
	 * @return
	 */
	public JDFMedia getPlateMedia()
	{
		if (theNode == null)
		{
			return null;
		}
		for (int i = 0; i < 10; i++)
		{
			final JDFMedia plate = (JDFMedia) theNode.getResource(ElementName.MEDIA, EnumUsage.Input, i);
			if (plate == null)
			{
				return null;
			}
			if (EnumMediaType.Plate.equals(plate.getMediaType()))
			{
				return plate;
			}
		}
		return null;

	}

}
