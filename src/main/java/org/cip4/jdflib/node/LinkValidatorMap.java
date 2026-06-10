/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * implementation of the link validation routines
 *
 * @author rainer prosi
 * @date May 27, 2014
 */
public class LinkValidatorMap
{
	private static LinkValidatorMap theLinkValidator = null;
	/**
	 * Member Variables
	 */
	private final HashMap<String, LinkInfoMap> m_LinkInfoMap = new HashMap<>();

	private final String[] m_GenericLinkInfo = { JDFConstants.INPUT_ZEROTOINFINITY, // APPROVALSUCCESS
			JDFConstants.INPUT_ZEROTOONE, // COLORPOOL
			JDFConstants.INPUT_ZEROTOONE, // CUSTOMERINFO
			JDFConstants.INPUT_ZEROTOINFINITY, // DEVICE
			JDFConstants.INPUT_ZEROTOINFINITY, // EMPLOYEE
			JDFConstants.INPUT_ZEROTOINFINITY, // MISCCONSUMABLE
			JDFConstants.INPUT_ZEROTOONE, // NODEINFO
			JDFConstants.INPUT_ZEROTOINFINITY, // PREFLIGHTREPORT
			JDFConstants.INPUT_ZEROTOINFINITY, // PREVIEW
			JDFConstants.INPUT_ZEROTOINFINITY, // TOOL
			JDFConstants.INPUT_ZEROTOINFINITY // USAGECOUNTER
	};

	private final String[] m_GenericLinkNames = { ElementName.APPROVALSUCCESS, ElementName.COLORPOOL, ElementName.CUSTOMERINFO, ElementName.DEVICE,
			ElementName.EMPLOYEE, ElementName.MISCCONSUMABLE, ElementName.NODEINFO, ElementName.PREFLIGHTREPORT, ElementName.PREVIEW, ElementName.TOOL,
			ElementName.USAGECOUNTER };

	private HashSet<String> nameSet = null;

	/**
	 * get the singleton validator map
	 *
	 * @return
	 */
	public static LinkValidatorMap getLinkValidatorMap()
	{
		if (theLinkValidator == null)
		{
			theLinkValidator = new LinkValidatorMap();
		}
		return theLinkValidator;
	}

	/**
	 * add new entries to m_strGenericLinkNames and m_GenericLinkInfo
	 *
	 * @param key       key for the new entry
	 * @param nameAddon value of the new entry in m_strGenericLinkNames
	 * @param linkAddon value of the new entry in m_GenericLinkInfo
	 */
	private void mapPut(final String key, final String nameAddon, final String linkAddon)
	{
		final LinkInfoMap newMap = new LinkInfoMap();
		final int genericPos = 0;
		for (final String name : m_GenericLinkNames)
		{
			final LinkInfo li = new LinkInfo(m_GenericLinkInfo[genericPos]);
			newMap.put(name, li);
		}

		final VString vNames = StringUtil.tokenize(nameAddon, JDFCoreConstants.COMMA, false);
		final VString vInfos = StringUtil.tokenize(linkAddon, JDFCoreConstants.COMMA, false);
		if (vNames != null && vInfos != null && vNames.size() == vInfos.size())
		{
			int pos = 0;
			for (final String name : vNames)
			{
				final LinkInfo li = new LinkInfo(vInfos.get(pos));
				newMap.put(name, li);
				pos++;
			}
		}

		m_LinkInfoMap.put(key, newMap);
	}

	/**
	 *
	 */
	private LinkValidatorMap()
	{
		initMaps();
	}

	// Note: This MUST be behind the enum declaration because the enums are used
	private void initMaps()
	{
		mapPut(EnumType.Product.name(),
				",Component,ArtDeliveryIntent,BindingIntent,ColorIntent,DeliveryIntent,EmbossingIntent,FoldingIntent,HoleMakingIntent,InsertingIntent,LaminatingIntent,"
						+ "LayoutIntent,MediaIntent,NumberingIntent,PackingIntent,ProductionIntent,ProofingIntent,ScreeningIntent,ShapeCuttingIntent,SizeIntent,VariableIntent",
				",o+ i* i*Cover i?Jacket i?Parent i*EndSheet,i?,i?,i?,i?,i?,i?,i?,i?,i?,i?,i?,i?,i?,i?,i?,i?,i?,i?,i?");
		mapPut("*", "", "");
		mapPut(EnumType.ProcessGroup.name(), ",*", ",i* o*");
		mapPut(EnumType.Combined.name(), "", "");

		// ----- general -----
		mapPut(EnumType.Approval.name(), ",*,ApprovalSuccess,ApprovalParams", ",o*Rejected o*Accepted i*,o_,i_");
		mapPut(EnumType.Buffer.name(), ",*,BufferParams", ",o_ i_,i_");
		mapPut(EnumType.Combine.name(), ",*", ",o_ i+");
		mapPut(EnumType.Delivery.name(), ",*,DeliveryParams", ",o+ i?,i_");
		mapPut(EnumType.ManualLabor.name(), ",*,ManualLaborParams", ",o* i*,i_");
		mapPut(EnumType.Ordering.name(), ",*,OrderingParams", ",o+,i_");
		mapPut(EnumType.Packing.name(), ",*,PackingParams", ",o_ i_,i_");
		mapPut(EnumType.QualityControl.name(), ",*,QualityControlResult,QualityControlParams", ",o_ i_,o_,i_");
		mapPut(EnumType.ResourceDefinition.name(), ",*,ResourceDefinitionParams", ",o+ i*,i?");
		mapPut(EnumType.Split.name(), ",*", ",o+ i_");
		mapPut(EnumType.Verification.name(), ",*,DBSelection,ApprovalSuccess,VerificationParams,IdentificationField,DBSchema,FileSpec",
				",o? i?,o? i?,o?,i_,i*,i?,i? o*");
		// ----- prepress ----
		mapPut(EnumType.AssetListCreation.name(), ",AssetListCreationParams,RunList", ",i_,i_ o_");
		mapPut(EnumType.Bending.name(), ",BendingParams,ExposedMedia,Media", ",i_,i? o_,i?");
		mapPut(EnumType.ColorCorrection.name(), ",ColorantControl,ColorCorrectionParams,RunList", ",i?,i_,o_ i_");
		mapPut(EnumType.ColorSpaceConversion.name(), ",ColorantControl,ColorSpaceConversionParams,RunList", ",i?,i_,o_ i_");
		mapPut(EnumType.ContactCopying.name(), ",ContactCopyParams,DevelopingParams,ExposedMedia,Media,PlateCopyParams", ",i_,i?,o_ i+,i_,i?");
		mapPut(EnumType.ContoneCalibration.name(), ",RunList,ScreeningParams,TransferFunctionControl", ",o_ i_,i?,i?");
		mapPut(EnumType.CylinderLayoutPreparation.name(), ",CylinderLayoutPreparationParams,Layout,RunList,CylinderLayout", ",i?,i_,i_,o_");
		mapPut(EnumType.DBDocTemplateLayout.name(), ",DBRules,DBSchema,LayoutElement", ",i_,i_,o* i*");
		mapPut(EnumType.DBTemplateMerging.name(), ",DBMergeParams,DBSelection,LayoutElement,RunList", ",i_,i_,i*,o_");
		mapPut(EnumType.DieDesign.name(), ",DieLayout", ",i_ o+");
		mapPut(EnumType.DieLayoutProduction.name(), ",ShapeDef,DieLayout,DieLayoutProductionParams", ",i+,o+,i_");
		mapPut(EnumType.DigitalDelivery.name(), ",DigitalDeliveryParams,RunList", ",i_,o+ i*");
		mapPut(EnumType.FilmToPlateCopying.name(), ",DevelopingParams,ExposedMedia,Media,PlateCopyParams", ",i?,o_ i_,i_,i_");
		mapPut(EnumType.FormatConversion.name(), ",FormatConversionParams,RunList", ",i_,o_ i_");
		mapPut(EnumType.ImageReplacement.name(), ",ImageCompressionParams,ImageReplacementParams,RunList", ",i?,i_,o_ i_");
		mapPut(EnumType.ImageEnhancement.name(), ",ImageEnhancementParams,RunList", ",i_,o_ i_");
		mapPut(EnumType.ImageSetting.name(), ",ColorantControl,DevelopingParams,ImageSetterParams,Media,RunList,TransferCurvePool,ExposedMedia",
				",i?,i?,i?,i?,i_,i?,o_ i?");
		mapPut(EnumType.Imposition.name(), ",Layout,RunList", ",i_,o_ i?Marks i_Document");
		mapPut(EnumType.InkZoneCalculation.name(), ",InkZoneCalculationParams,InkZoneProfile,Layout,TransferCurvePool,Sheet,Preview", ",i?,o_,i?,i?,i?,i_");
		mapPut(EnumType.Interpreting.name(), ",ColorantControl,FontPolicy,InterpretedPDLData,InterpretingParams,PDLResourceAlias,RunList",
				",i?,i?,o?,i_,i*,o? i_");
		mapPut(EnumType.LayoutElementProduction.name(), ",LayoutElement,RunList,LayoutElementProductionParams", ",o? i*,i* o?,i?");
		mapPut(EnumType.LayoutPreparation.name(), ",LayoutPreparationParams,RunList,Layout,TransferCurvePool", ",i_,o?Marks i?Marks i?Document,o_,o?");
		mapPut(EnumType.PageAssigning.name(), ",PageAssignParams,RunList", ",i_,o_ i+");
		mapPut(EnumType.PDFToPSConversion.name(), ",PDFToPSConversionParams,RunList", ",i_,o_ i_");
		mapPut(EnumType.PDLCreation.name(), ",ImageCompressionParams,PDLCreationParams,RunList", ",i?,i?,o_ i_");
		mapPut(EnumType.Preflight.name(), ",PreflightParams,PreflightReportRulePool,RunList,PreflightReport", ",i_,i_,i_,o_");
		mapPut(EnumType.PreviewGeneration.name(), ",ColorantControl,ExposedMedia,PreviewGenerationParams,RunList,TransferCurvePool,Preview",
				",i?,i?,i_,i?,i?,o_ i?");
		mapPut(EnumType.Proofing.name(), ",ColorantControl,ColorSpaceConversionParams,ExposedMedia,Layout,Media,ProofingParams,RunList",
				",i?,i?,o_,i?,i_,i_,i?Marks i_Document");
		mapPut(EnumType.PSToPDFConversion.name(), ",FontParams,ImageCompressionParams,PSToPDFConversionParams,RunList", ",i?,i?,i?,o_ i_");
		mapPut(EnumType.RasterReading.name(), ",RasterReadingParams,RunList", ",i?,o_ i_");
		mapPut(EnumType.Rendering.name(), ",InterpretedPDLData,Media,RenderingParams,RunList,ImageCompressionParams", ",i?,i?,i?,o_ i?,i?");
		mapPut(EnumType.Scanning.name(), ",ExposedMedia,ScanParams,RunList", ",i_,i_,o_");
		mapPut(EnumType.Screening.name(), ",RunList,ScreeningParams", ",o_ i_,i_");
		mapPut(EnumType.Separation.name(), ",ColorantControl,RunList,SeparationControlParams", ",i?,o_ i_,i_");
		mapPut(EnumType.SheetOptimizing.name(), ",SheetOptimizingParams,Assembly,BinderySignature,StrippingParams", ",i?,i*,i?,o_");
		mapPut(EnumType.SoftProofing.name(), ",ColorantControl,ColorSpaceConversionParams,Layout,ProofingParams,RunList", ",i?,i?,i?,i_,i?Marks i_Document");
		mapPut(EnumType.Stripping.name(), ",RunList,Layout,Assembly,BinderySignature,TransferCurvePool,StrippingParams,ColorantControl",
				",o?Marks o?Document i?Document,o_,i+,i?,i?,i_,i?");
		mapPut(EnumType.Tiling.name(), ",RunList,Tile", ",o_ i?Marks i_Surface,i_");
		mapPut(EnumType.Trapping.name(), ",ColorantControl,RunList,TrappingDetails,FontPolicy", ",i?,o_ i_,i_,i?");

		// ----- press -----
		mapPut(EnumType.ConventionalPrinting.name(),
				",ColorantControl,Component,ConventionalPrintingParams,ExposedMedia,Ink,InkZoneProfile,Layout,Media,PrintCondition,Sheet,TransferCurvePool",
				",i?,o?Waste o_ i?Proof i?Input i?,i_,i?Plate i?Cylinder i?Proof,i?,i?,i?,i?,i?,i?,i? i?MountingTape");
		mapPut(EnumType.DigitalPrinting.name(),
				",ColorantControl,Component,DigitalPrintingParams,ExposedMedia,Ink,PrintCondition,Media,RunList,Layout,Sheet,TransferCurvePool",
				",i?,o?Waste o_ i?Proof i*Input i*,i_,i?,i?,i?,i*,i_,i?,i?,i?");
		mapPut(EnumType.IDPrinting.name(),
				",ColorantControl,Component,ExposedMedia,FontPolicy,Ink,InterpretingParams,IDPrintingParams,Media,RenderingParams,RunList,ScreeningParams,TransferFunctionControl",
				",i?,o?Waste o_Good i?Proof i?Input i?Cover,i?,i?,i?,i*,i?,i?,i?,i_,i?,i?");
		mapPut(EnumType.Varnishing.name(), ",Component,ExposedMedia,Ink,Media,VarnishingParams", ",i? o_,o*,i?,i?,i?");

		// ----- postpress ----
		mapPut(EnumType.AdhesiveBinding.name(), ",AdhesiveBindingParams,Component", ",i_,o_ i?Cover i_BookBlock");
		mapPut(EnumType.BlockPreparation.name(), ",Component,BlockPreparationParams", ",o_ i_,i_");
		mapPut(EnumType.BoxFolding.name(), ",Component,BoxFoldingParams", ",o_ i*Application i_,i_");
		mapPut(EnumType.BoxPacking.name(), ",Component,BoxPackingParams", ",o_ i?Box i_,i_");
		mapPut(EnumType.Bundling.name(), ",Component,BundlingParams,Media", ",o_ i_,i_,i?");
		mapPut(EnumType.CaseMaking.name(), ",Component,CaseMakingParams,Media", ",o_ i?CoverMaterial,i_,i?SpineBoard i_CoverBoard i?CoverMaterial");
		mapPut(EnumType.CasingIn.name(), ",Component,CasingInParams", ",o_ i_Case i_,i_");
		mapPut(EnumType.ChannelBinding.name(), ",ChannelBindingParams,Component", ",i_,o_ i?Cover i_BookBlock");
		mapPut(EnumType.CoilBinding.name(), ",CoilBindingParams,Component", ",i_,o_ i_");
		mapPut(EnumType.Collecting.name(), ",CollectingParams,Component,DBRules,DBSelection,IdentificationField,Assembly", ",i?,o_ i+,i*,i?,i?,i?");
		mapPut(EnumType.CoverApplication.name(), ",Component,CoverApplicationParams", ",o_ i_Cover i_,i_");
		mapPut(EnumType.Creasing.name(), ",CreasingParams,Component", ",i_,o_ i_");
		mapPut(EnumType.Cutting.name(), ",Component,CutBlock,CutMark,CuttingParams,Media", ",o* i?,i*,i*,i_,o* i?");
		mapPut(EnumType.Dividing.name(), ",Component,DividingParams", ",o_ i_,i_");
		mapPut(EnumType.Embossing.name(), ",Component,EmbossingParams,Media,Tool", ",o_ i_,i_,i?,i?");
		mapPut(EnumType.EndSheetGluing.name(), ",Component,EndSheetGluingParams", ",o_ i?FrontEndSheet i_BookBlock i?BackEndSheet,i_");
		mapPut(EnumType.Feeding.name(), ",Component,FeedingParams,Media", ",o* i*,i_,o* i*");
		mapPut(EnumType.Folding.name(), ",Component,FoldingParams", ",o_ i_,i_");
		mapPut(EnumType.Gathering.name(), ",Assembly,Component,DBRules,DBSelection,GatheringParams,IdentificationField", ",i?,o_ i+,i*,i?,i_,i?");
		mapPut(EnumType.Gluing.name(), ",Component,GluingParams", ",o_ i_,i_");
		mapPut(EnumType.HeadBandApplication.name(), ",Component,HeadBandApplicationParams", ",o_ i_,i_");
		mapPut(EnumType.HoleMaking.name(), ",Component,HoleMakingParams", ",o_ i_,i_");
		mapPut(EnumType.Inserting.name(), ",Component,DBRules,DBSelection,IdentificationField,InsertingParams", ",o_ i_Child i?Mother i?,i?,i?,i?,i_");
		mapPut(EnumType.Jacketing.name(), ",Component,JacketingParams", ",o_ i_Jacket i_Book,i_");
		mapPut(EnumType.Labeling.name(), ",Component,LabelingParams", ",o_ i?Label i_,i_");
		mapPut(EnumType.Laminating.name(), ",Component,LaminatingParams,Media", ",o_ i_,i_,i?");
		mapPut(EnumType.LongitudinalRibbonOperations.name(), ",Component,LongitudinalRibbonOperationParams", ",o+ i_,i_");
		mapPut(EnumType.Numbering.name(), ",Component,NumberingParams", ",o_ i_,i_");
		mapPut(EnumType.Palletizing.name(), ",Component,PalletizingParams,Pallet", ",o_ i_,i_,i_");
		mapPut(EnumType.Perforating.name(), ",PerforatingParams,Component", ",i_,o_ i_");
		mapPut(EnumType.PlasticCombBinding.name(), ",Component,PlasticCombBindingParams", ",o_ i_,i_");
		mapPut(EnumType.PrintRolling.name(), ",Component,PrintRollingParams,RollStand", ",o_ i_,i?,i?");
		mapPut(EnumType.RingBinding.name(), ",Component,RingBindingParams", ",o_ i?RingBinder i_BookBlock,i_");
		mapPut(EnumType.SaddleStitching.name(), ",Component,SaddleStitchingParams", ",o_ i_,i_");
		mapPut(EnumType.ShapeCutting.name(), ",Component,ShapeCuttingParams,Tool", ",o+ i_,i?,i*");
		mapPut(EnumType.ShapeDefProduction.name(), ",LayoutElement,ShapeDefProductionParams,ShapeDef", ",i?,i_,o+");
		mapPut(EnumType.Shrinking.name(), ",Component,ShrinkingParams", ",o_ i_,i_");
		mapPut(EnumType.SideSewing.name(), ",Component,SideSewingParams", ",o_ i_,i_");
		mapPut(EnumType.SpinePreparation.name(), ",Component,SpinePreparationParams", ",o_ i_,i_");
		mapPut(EnumType.SpineTaping.name(), ",Component,SpineTapingParams", ",o_ i_,i_");
		mapPut(EnumType.Stacking.name(), ",Component,StackingParams", ",o_ i_,i_");
		mapPut(EnumType.StaticBlocking.name(), ",Component,StaticBlockingParams", ",o_ i_,i_");
		mapPut(EnumType.Stitching.name(), ",Component,StitchingParams", ",o_ i_,i_");
		mapPut(EnumType.Strapping.name(), ",Component,StrappingParams,Strap", ",o_ i_,i_,i?");
		mapPut(EnumType.StripBinding.name(), ",Component,StripBindingParams", ",o_ i_,i_");
		mapPut(EnumType.ThreadSealing.name(), ",Component,ThreadSealingParams", ",o_ i_,i_");
		mapPut(EnumType.ThreadSewing.name(), ",Component,ThreadSewingParams", ",o_ i_,i_");
		mapPut(EnumType.Trimming.name(), ",Component,TrimmingParams", ",o_ i_,i_");
		mapPut(EnumType.WebInlineFinishing.name(), ",Assembly,Component,ProductionPath,StrippingParams,WebInlineFinishingParams", ",i?,o_ i_,i?,i?,i?");
		mapPut(EnumType.Winding.name(), ",Component,Media,WindingParams", ",i_ o_,i? i?Core,i?");
		mapPut(EnumType.WireCombBinding.name(), ",Component,WireCombBindingParams", ",o_ i_,i_");
		mapPut(EnumType.Wrapping.name(), ",Component,WrappingParams,Media", ",o_ i_ i?Wrapper,i_,i?");

		// Prepress gray boxes
		mapPut(EnumType.PrepressPreparation.name(), ",RunList,*", ",i_Document o_Document,i* o*");
		mapPut(EnumType.ImpositionPreparation.name(), ",Layout,RunList,*", ",o_,i?Document o?Document o_Marks,,i* o*");
		mapPut(EnumType.RIPing.name(), ",InterpretingParams,RenderingParams,RunList,*", ",i?,i?,o_ i_Document i?Marks,i* o*");
		mapPut(EnumType.PlateSetting.name(), ",*,ExposedMedia,Preview", ",i* o*,o_,o*");
		mapPut(EnumType.PlateMaking.name(), ",*,RunList,ExposedMedia,Preview,Media", ",i* o*,i_Document i?Marks,o_,o*,i_");
		mapPut(EnumType.ProofAndPlateMaking.name(), ",*,RunList,ExposedMedia,Preview,Media", ",i* o*,i_Document i?Marks,o+,o*,i_");
		mapPut(EnumType.ImpositionProofing.name(), ",*,RunList,ExposedMedia,Media", ",i* o*,i_Document i?Marks,o+,i_");
		mapPut(EnumType.PageProofing.name(), ",*,RunList,ExposedMedia,Media", ",i* o*,i_Document i?Marks,o+,i_");
		mapPut(EnumType.PageSoftProofing.name(), ",*,RunList", ",i* o*,i_Document i?Marks");
		mapPut(EnumType.ProofImaging.name(), ",InterpretingParams,RenderingParams,RunList,*", ",i?,i?,i? i?Document i?Marks,i* o*");

	}

	// ////////////////////////////////////////////////////////////////////
	/**
	 * definition of resource link names in the JDF namespace
	 *
	 * @param typ
	 * @return String list of resource names that may be linked
	 */
	Vector<String> typeLinkNames(final EnumType typ)
	{
		if (typ == null)
		{
			return null;
		}
		final LinkInfoMap map = m_LinkInfoMap.get(typ.name());
		return ContainerUtil.getKeyVector(map);
	}

	/**
	 * Getter for m_strGenericLinkNames attribute.
	 *
	 * @return the m_strGenericLinkNames
	 */
	HashSet<String> getGenericLinkNames()
	{
		if (nameSet == null)
		{
			nameSet = new HashSet<>();
			nameSet.addAll(new VString(m_GenericLinkNames));
		}
		return nameSet;
	}

	/**
	 * definition of resource link usage, cardinality and ProcessUsage in the JDF namespace for a given EnumType
	 *
	 * @param typeNum EnumType to get LinkInfo for
	 * @param addStar if true, also get the generic map
	 * @return String list of resource information usages that may be linked for this EnumType
	 */
	LinkInfoMap getTypeMap(final EnumType typeNum, final boolean addStar)
	{
		LinkInfoMap info = typeNum == null ? null : m_LinkInfoMap.get(typeNum.name());
		if (info == null && addStar)
		{
			info = m_LinkInfoMap.get("*");
		}

		return new LinkInfoMap(info);
	}

	/**
	 * @param typ
	 * @param vTypes
	 * @return
	 */
	public LinkInfoMap getLinkInfoMap(final EnumType typ, final VString vTypes)
	{
		if (typ == null)
		{
			return getTypeMap(EnumType.ProcessGroup, false);
		}

		if (typ.equals(EnumType.Combined) || (typ == EnumType.ProcessGroup && vTypes != null))
		{
			if (vTypes == null)
			{
				return null;
			}
			final LinkInfoMap ret = new LinkInfoMap(m_LinkInfoMap.get("*"));

			for (final String s : vTypes)
			{
				EnumType t = EnumType.getEnum(s);
				if (t == null)
				{
					t = EnumType.ProcessGroup;
				}
				final LinkInfoMap typeLinkInfo = getTypeMap(t, false);
				ret.merge(typeLinkInfo);
			}
			return ret;
		}
		else
		{
			return getTypeMap(typ, true);
		}
	}

	/**
	 * @param typ
	 * @param vTypes
	 * @return
	 */
	public VString getLinkNames(final EnumType typ, final VString vTypes)
	{
		final LinkInfoMap map = getLinkInfoMap(typ, vTypes);
		return new VString(ContainerUtil.getKeyVector(map));
	}

	@Override
	public String toString()
	{
		return "LinkValidatorMap [m_LinkInfoMap=" + m_LinkInfoMap + "]";
	}

}
