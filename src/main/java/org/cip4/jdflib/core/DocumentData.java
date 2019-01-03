/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.core;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;

import org.cip4.jdflib.extensions.XJDFConstants;

/**
 * this is a singlton data container for the parser
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class DocumentData
{
	private static final String JDFLIB = "org.cip4.jdflib.";
	private static final String LAYOUT = JDFLIB + "resource.process.JDFLayout";
	private static final String SEPARATION_LIST = JDFLIB + "core.JDFSeparationList";
	private static final String JDF_COMMENT = JDFLIB + "core.JDFComment";
	private static final String SPAN_NAMED_COLOR = JDFLIB + "span.JDFSpanNamedColor";
	private static final String NUMBER_SPAN = JDFLIB + "span.JDFNumberSpan";
	private static final String SHAPE_SPAN = JDFLIB + "span.JDFShapeSpan";
	private static final String OPTION_SPAN = JDFLIB + "span.JDFOptionSpan";
	private static final String NAME_SPAN = JDFLIB + "span.JDFNameSpan";
	private static final String STRING_SPAN = JDFLIB + "span.JDFStringSpan";
	private static final String DURATION_SPAN = JDFLIB + "span.JDFDurationSpan";
	private static final String TIME_SPAN = JDFLIB + "span.JDFTimeSpan";
	private static final String INTEGER_SPAN = JDFLIB + "span.JDFIntegerSpan";

	/**
	 * register new custom class in the factory
	 *
	 * @param strElement local name
	 * @param packagepath package path
	 */
	void registerCustomClass(final String strElement, final String packagepath)
	{
		synchronized (DocumentJDFImpl.mutex)
		{
			sm_PackageNames.put(strElement, packagepath);
			sm_ClassAlreadyInstantiated.remove(strElement);
			sm_hashCtorElementNS.remove(strElement);
		}
	}

	private void fillContextSensitive()
	{
		contextSensitive.add(ElementName.HOLETYPE);
		contextSensitive.add(ElementName.METHOD);
		contextSensitive.add(ElementName.SHAPE);
		contextSensitive.add(ElementName.POSITION);
		contextSensitive.add(ElementName.SURFACE);
		contextSensitive.add(DocumentJDFImpl.CORE_JDFELEMENT);
		contextSensitive.add(DocumentJDFImpl.CORE_KELEMENT);
	}

	private void fillPackages()
	{
		sm_PackageNames.put("ResDefault", DocumentJDFImpl.CORE_JDFRESOURCE);
		sm_PackageNames.put("EleDefault", DocumentJDFImpl.CORE_JDFELEMENT);
		sm_PackageNames.put("OtherNSDefault", DocumentJDFImpl.CORE_KELEMENT);

		// root elements
		sm_PackageNames.put(ElementName.JDF, JDFLIB + "node.JDFNode");
		sm_PackageNames.put(ElementName.JMF, JDFLIB + "jmf.JDFJMF");
		sm_PackageNames.put(XJDFConstants.XJDF, DocumentJDFImpl.CORE_JDFELEMENT);
		sm_PackageNames.put(XJDFConstants.XJMF, DocumentJDFImpl.CORE_JDFELEMENT);
		sm_PackageNames.put("PrintTalk", DocumentJDFImpl.CORE_JDFELEMENT);

		sm_PackageNames.put(ElementName.ABORTQUEUEENTRYPARAMS, JDFLIB + "jmf.JDFAbortQueueEntryParams");
		sm_PackageNames.put(ElementName.ACKNOWLEDGE, JDFLIB + "jmf.JDFAcknowledge");
		sm_PackageNames.put(ElementName.ACTION, JDFLIB + "resource.devicecapability.JDFAction");
		sm_PackageNames.put(ElementName.ACTIONPOOL, JDFLIB + "resource.devicecapability.JDFActionPool");
		sm_PackageNames.put(ElementName.ACTIVITY, JDFLIB + "node.JDFActivity");
		sm_PackageNames.put(ElementName.ADDED, JDFLIB + "jmf.JDFAdded");
		sm_PackageNames.put(ElementName.ADDRESS, JDFLIB + "resource.process.JDFAddress");
		sm_PackageNames.put(ElementName.ADHESIVEBINDING, JDFLIB + "resource.process.postpress.JDFAdhesiveBinding");
		sm_PackageNames.put(ElementName.ADHESIVEBINDINGPARAMS, JDFLIB + "resource.JDFAdhesiveBindingParams");
		sm_PackageNames.put(ElementName.ADVANCEDPARAMS, JDFLIB + "resource.process.JDFAdvancedParams");
		sm_PackageNames.put(ElementName.AMOUNT, INTEGER_SPAN);
		sm_PackageNames.put(ElementName.AMOUNTPOOL, JDFLIB + "pool.JDFAmountPool");
		sm_PackageNames.put(ElementName.ANCESTOR, JDFLIB + "node.JDFAncestor");
		sm_PackageNames.put(ElementName.ANCESTORPOOL, JDFLIB + "pool.JDFAncestorPool");
		sm_PackageNames.put(ElementName.AND, JDFLIB + "resource.devicecapability.JDFand");
		sm_PackageNames.put(ElementName.APPROVALDETAILS, JDFLIB + "resource.process.JDFApprovalDetails");
		sm_PackageNames.put(ElementName.APPROVALPARAMS, JDFLIB + "resource.process.JDFApprovalParams");
		sm_PackageNames.put(ElementName.APPROVALPERSON, JDFLIB + "resource.process.JDFApprovalPerson");
		sm_PackageNames.put(ElementName.APPROVALSUCCESS, JDFLIB + "resource.process.JDFApprovalSuccess");
		sm_PackageNames.put(ElementName.ARGUMENTVALUE, JDFLIB + "resource.process.JDFArgumentValue");
		sm_PackageNames.put(ElementName.ARTDELIVERY, JDFLIB + "resource.intent.JDFArtDelivery");
		sm_PackageNames.put(ElementName.ARTDELIVERYDATE, TIME_SPAN);
		sm_PackageNames.put(ElementName.ARTDELIVERYDURATION, DURATION_SPAN);
		sm_PackageNames.put(ElementName.ARTDELIVERYINTENT, JDFLIB + "resource.intent.JDFArtDeliveryIntent");
		sm_PackageNames.put(ElementName.ARTDELIVERYTYPE, JDFLIB + "resource.intent.JDFArtDeliveryType");
		sm_PackageNames.put(ElementName.ARTHANDLING, JDFLIB + "span.JDFSpanArtHandling");
		sm_PackageNames.put(ElementName.ASSEMBLY, JDFLIB + "resource.process.JDFAssembly");
		sm_PackageNames.put(ElementName.ASSEMBLYSECTION, JDFLIB + "resource.process.JDFAssemblySection");
		sm_PackageNames.put(ElementName.ASSETLISTCREATIONPARAMS, JDFLIB + "resource.process.prepress.JDFAssetListCreationParams");
		sm_PackageNames.put(ElementName.AUDIT, JDFLIB + "core.JDFAudit");
		sm_PackageNames.put(ElementName.AUDITPOOL, JDFLIB + "pool.JDFAuditPool");
		sm_PackageNames.put(ElementName.AUTHENTICATIONCMDPARAMS, JDFLIB + "jmf.JDFAuthenticationCmdParams");
		sm_PackageNames.put(ElementName.AUTHENTICATIONQUPARAMS, JDFLIB + "jmf.JDFAuthenticationQuParams");
		sm_PackageNames.put(ElementName.AUTHENTICATIONRESP, JDFLIB + "jmf.JDFAuthenticationResp");
		sm_PackageNames.put(ElementName.AUTOMATEDOVERPRINTPARAMS, JDFLIB + "resource.process.JDFAutomatedOverPrintParams");
		sm_PackageNames.put(ElementName.BACKCOATINGS, JDFLIB + "span.JDFSpanCoatings");
		sm_PackageNames.put(ElementName.BACKCOVERCOLOR, SPAN_NAMED_COLOR);
		sm_PackageNames.put(ElementName.BACKCOVERCOLORDETAILS, STRING_SPAN);
		sm_PackageNames.put(ElementName.BAND, JDFLIB + "resource.JDFBand");
		sm_PackageNames.put(ElementName.BARCODE, JDFLIB + "resource.JDFBarcode");
		sm_PackageNames.put(ElementName.BARCODECOMPPARAMS, JDFLIB + "resource.process.JDFBarcodeCompParams");
		sm_PackageNames.put(ElementName.BARCODEDETAILS, JDFLIB + "resource.process.JDFBarcodeDetails");
		sm_PackageNames.put(ElementName.BARCODEREPROPARAMS, JDFLIB + "resource.process.JDFBarcodeReproParams");
		sm_PackageNames.put(ElementName.BARCODEPRODUCTIONPARAMS, JDFLIB + "resource.process.JDFBarcodeProductionParams");
		sm_PackageNames.put(ElementName.BASICPREFLIGHTTEST, JDFLIB + "resource.devicecapability.JDFBasicPreflightTest");
		sm_PackageNames.put(ElementName.BENDINGPARAMS, JDFLIB + "resource.process.JDFBendingParams");
		sm_PackageNames.put(ElementName.BINDERMATERIAL, NAME_SPAN);
		sm_PackageNames.put(ElementName.BINDERBRAND, STRING_SPAN);
		sm_PackageNames.put(ElementName.BINDERYSIGNATURE, JDFLIB + "resource.process.JDFBinderySignature");
		sm_PackageNames.put(ElementName.BINDINGCOLOR, SPAN_NAMED_COLOR);
		sm_PackageNames.put(ElementName.BINDINGCOLORDETAILS, STRING_SPAN);
		sm_PackageNames.put(ElementName.BINDINGINTENT, JDFLIB + "resource.intent.JDFBindingIntent");
		sm_PackageNames.put(ElementName.BINDINGLENGTH, JDFLIB + "span.JDFSpanBindingLength");
		sm_PackageNames.put(ElementName.BINDINGQUALITYMEASUREMENT, JDFLIB + "resource.process.JDFBindingQualityMeasurement");
		sm_PackageNames.put(ElementName.BINDINGQUALITYPARAMS, JDFLIB + "resource.process.JDFBindingQualityParams");
		sm_PackageNames.put(ElementName.BINDINGSIDE, JDFLIB + "span.JDFSpanBindingSide");
		sm_PackageNames.put(ElementName.BINDINGTYPE, JDFLIB + "span.JDFSpanBindingType");
		sm_PackageNames.put(ElementName.BINDITEM, JDFLIB + "resource.JDFBindItem");
		sm_PackageNames.put(ElementName.BINDLIST, JDFLIB + "resource.JDFBindList");
		sm_PackageNames.put(ElementName.BLOCKPREPARATIONPARAMS, JDFLIB + "resource.JDFBlockPreparationParams");
		sm_PackageNames.put(ElementName.BLOCKTHREADSEWING, OPTION_SPAN);
		sm_PackageNames.put(ElementName.BOOKCASE, JDFLIB + "resource.intent.JDFBookCase");
		sm_PackageNames.put(ElementName.BOOLEANEVALUATION, JDFLIB + "resource.devicecapability.JDFBooleanEvaluation");
		sm_PackageNames.put(ElementName.BOOLEANSTATE, JDFLIB + "resource.devicecapability.JDFBooleanState");
		sm_PackageNames.put(ElementName.BOXAPPLICATION, JDFLIB + "resource.process.JDFBoxApplication");
		sm_PackageNames.put(ElementName.BOXARGUMENT, JDFLIB + "resource.process.JDFBoxArgument");
		sm_PackageNames.put(ElementName.BOXEDQUANTITY, INTEGER_SPAN);
		sm_PackageNames.put(ElementName.BOXFOLDACTION, JDFLIB + "resource.process.JDFBoxFoldAction");
		sm_PackageNames.put(ElementName.BOXFOLDINGPARAMS, JDFLIB + "resource.process.JDFBoxFoldingParams");
		sm_PackageNames.put(ElementName.BOXPACKINGPARAMS, JDFLIB + "resource.JDFBoxPackingParams");
		sm_PackageNames.put(ElementName.BOXSHAPE, SHAPE_SPAN);
		sm_PackageNames.put(ElementName.BOXTOBOXDIFFERENCE, JDFLIB + "resource.process.JDFBoxToBoxDifference");
		sm_PackageNames.put(ElementName.BRANDNAME, STRING_SPAN);
		sm_PackageNames.put(ElementName.BRIGHTNESS, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.BUFFERPARAMS, JDFLIB + "resource.JDFBufferParams");
		sm_PackageNames.put(ElementName.BUNDLE, JDFLIB + "resource.JDFBundle");
		sm_PackageNames.put(ElementName.BUNDLEITEM, JDFLIB + "resource.JDFBundleItem");
		sm_PackageNames.put(ElementName.BUNDLINGPARAMS, JDFLIB + "resource.process.postpress.JDFBundlingParams");
		sm_PackageNames.put(ElementName.BUSINESSINFO, JDFLIB + "resource.process.JDFBusinessInfo");
		sm_PackageNames.put(ElementName.BUYERSUPPLIED, OPTION_SPAN);
		sm_PackageNames.put(ElementName.BYTEMAP, JDFLIB + "resource.process.JDFByteMap");

		sm_PackageNames.put(ElementName.CALL, JDFLIB + "resource.devicecapability.JDFcall");
		sm_PackageNames.put(ElementName.CARTONMAXWEIGHT, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.CARTONQUANTITY, INTEGER_SPAN);
		sm_PackageNames.put(ElementName.CARTONSHAPE, SHAPE_SPAN);
		sm_PackageNames.put(ElementName.CARTONSTRENGTH, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.CASEMAKINGPARAMS, JDFLIB + "resource.JDFCaseMakingParams");
		sm_PackageNames.put(ElementName.CASINGINPARAMS, JDFLIB + "resource.JDFCasingInParams");
		sm_PackageNames.put(ElementName.CCITTFAXPARAMS, JDFLIB + "resource.process.JDFCCITTFaxParams");
		sm_PackageNames.put(ElementName.CERTIFICATE, JDFLIB + "jmf.JDFCertificate");
		sm_PackageNames.put(ElementName.CHANGEDATTRIBUTE, JDFLIB + "resource.JDFChangedAttribute");
		sm_PackageNames.put(ElementName.CHANGEDPATH, JDFLIB + "jmf.JDFChangedPath");
		sm_PackageNames.put(ElementName.CHANNELBINDING, JDFLIB + "resource.process.postpress.JDFChannelBinding");
		sm_PackageNames.put(ElementName.CHANNELBINDINGPARAMS, JDFLIB + "resource.process.postpress.JDFChannelBindingParams");
		sm_PackageNames.put(ElementName.CHANNELBRAND, STRING_SPAN);
		sm_PackageNames.put(ElementName.CHOICE, JDFLIB + "resource.devicecapability.JDFchoice");
		sm_PackageNames.put(ElementName.CIELABMEASURINGFIELD, JDFLIB + "resource.process.JDFCIELABMeasuringField");
		sm_PackageNames.put(ElementName.CIRCULATION, INTEGER_SPAN);
		sm_PackageNames.put(ElementName.COATINGS, STRING_SPAN);
		sm_PackageNames.put(ElementName.COILBINDING, JDFLIB + "resource.process.postpress.JDFCoilBinding");
		sm_PackageNames.put(ElementName.COILBINDINGPARAMS, JDFLIB + "resource.JDFCoilBindingParams");
		sm_PackageNames.put(ElementName.COILBRAND, STRING_SPAN);
		sm_PackageNames.put(ElementName.COILMATERIAL, JDFLIB + "span.JDFSpanCoilMaterial");
		sm_PackageNames.put(ElementName.COLLATINGITEM, JDFLIB + "resource.process.JDFCollatingItem");
		sm_PackageNames.put(ElementName.COLLECTINGPARAMS, JDFLIB + "resource.process.JDFCollectingParams");
		sm_PackageNames.put(ElementName.COLOR, JDFLIB + "resource.process.JDFColor");
		sm_PackageNames.put(ElementName.COLORANTALIAS, JDFLIB + "resource.process.JDFColorantAlias");
		sm_PackageNames.put(ElementName.COLORANTCONTROL, JDFLIB + "resource.process.JDFColorantControl");
		sm_PackageNames.put(ElementName.COLORANTCONVERTPROCESS, SEPARATION_LIST);
		sm_PackageNames.put(ElementName.COLORANTORDER, SEPARATION_LIST);
		sm_PackageNames.put(ElementName.COLORANTPARAMS, SEPARATION_LIST);
		sm_PackageNames.put(ElementName.COLORANTZONEDETAILS, JDFLIB + "resource.process.JDFColorantZoneDetails");
		sm_PackageNames.put(ElementName.COLORCONTROLSTRIP, JDFLIB + "resource.process.JDFColorControlStrip");
		sm_PackageNames.put(ElementName.COLORCORRECTIONOP, JDFLIB + "resource.process.prepress.JDFColorCorrectionOp");
		sm_PackageNames.put(ElementName.COLORCORRECTIONPARAMS, JDFLIB + "resource.process.prepress.JDFColorCorrectionParams");
		sm_PackageNames.put(ElementName.COLORICCSTANDARD, STRING_SPAN);
		sm_PackageNames.put(ElementName.COLORINTENT, JDFLIB + "resource.intent.JDFColorIntent");
		sm_PackageNames.put(ElementName.COLORMEASUREMENTCONDITIONS, JDFLIB + "resource.JDFColorMeasurementConditions");
		sm_PackageNames.put(ElementName.COLORNAME, SPAN_NAMED_COLOR);
		sm_PackageNames.put(ElementName.COLORNAMEDETAILS, STRING_SPAN);
		sm_PackageNames.put(ElementName.COLORPOOL, JDFLIB + "resource.process.JDFColorPool");
		sm_PackageNames.put(ElementName.COLORSPACECONVERSIONOP, JDFLIB + "resource.process.prepress.JDFColorSpaceConversionOp");
		sm_PackageNames.put(ElementName.COLORSPACECONVERSIONPARAMS, JDFLIB + "resource.process.prepress.JDFColorSpaceConversionParams");
		sm_PackageNames.put(ElementName.COLORSPACESUBSTITUTE, JDFLIB + "resource.process.prepress.JDFColorSpaceSubstitute");
		sm_PackageNames.put(ElementName.COLORSRESULTSPOOL, JDFLIB + "resource.process.JDFColorsResultsPool");
		sm_PackageNames.put(ElementName.COLORSTANDARD, NAME_SPAN);
		sm_PackageNames.put(ElementName.COLORSUSED, SEPARATION_LIST);
		sm_PackageNames.put(ElementName.COLORTYPE, JDFLIB + "span.JDFSpanColorType");
		sm_PackageNames.put(ElementName.COMBBRAND, STRING_SPAN);
		sm_PackageNames.put(ElementName.COMCHANNEL, JDFLIB + "resource.process.JDFComChannel");
		sm_PackageNames.put(ElementName.COMMAND, JDFLIB + "jmf.JDFCommand");
		sm_PackageNames.put(ElementName.COMMENT, JDF_COMMENT);
		sm_PackageNames.put(ElementName.COMPANY, JDFLIB + "resource.process.JDFCompany");
		sm_PackageNames.put(ElementName.COMPONENT, JDFLIB + "resource.process.JDFComponent");
		sm_PackageNames.put(ElementName.CONSTRAINTVALUE, JDFLIB + "resource.process.JDFConstraintValue");
		sm_PackageNames.put(ElementName.CONTACT, JDFLIB + "resource.process.JDFContact");
		sm_PackageNames.put(ElementName.CONTACTCOPYPARAMS, JDFLIB + "resource.JDFContactCopyParams");
		sm_PackageNames.put(ElementName.CONTAINER, JDFLIB + "resource.process.JDFContainer");
		sm_PackageNames.put(ElementName.CONTENTDATA, JDFLIB + "resource.process.JDFContentData");
		sm_PackageNames.put(ElementName.CONTENTLIST, JDFLIB + "resource.process.JDFContentList");
		sm_PackageNames.put(ElementName.CONTENTMETADATA, JDFLIB + "resource.process.JDFContentMetaData");
		sm_PackageNames.put(ElementName.CONTENTOBJECT, JDFLIB + "resource.process.JDFContentObject");
		sm_PackageNames.put(ElementName.CONTROLLERFILTER, JDFLIB + "jmf.JDFControllerFilter");
		sm_PackageNames.put(ElementName.CONVENTIONALPRINTINGPARAMS, JDFLIB + "resource.process.JDFConventionalPrintingParams");
		sm_PackageNames.put(ElementName.CONVERTINGCONFIG, JDFLIB + "resource.process.JDFConvertingConfig");
		sm_PackageNames.put(ElementName.COSTCENTER, JDFLIB + "resource.process.JDFCostCenter");
		sm_PackageNames.put(ElementName.COUNTERRESET, JDFLIB + "resource.JDFCounterReset");
		sm_PackageNames.put(ElementName.COVER, JDFLIB + "resource.process.JDFCover");
		sm_PackageNames.put(ElementName.COVERAGE, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.COVERAPPLICATIONPARAMS, JDFLIB + "resource.JDFCoverApplicationParams");
		sm_PackageNames.put(ElementName.COVERCOLOR, SPAN_NAMED_COLOR);
		sm_PackageNames.put(ElementName.COVERCOLORDETAILS, STRING_SPAN);
		sm_PackageNames.put(ElementName.COVERSTYLE, NAME_SPAN);
		sm_PackageNames.put(ElementName.CREASE, JDFLIB + "resource.process.postpress.JDFCrease");
		sm_PackageNames.put(ElementName.CREASINGPARAMS, JDFLIB + "resource.JDFCreasingParams");
		sm_PackageNames.put(ElementName.CREATED, JDFLIB + "resource.JDFCreated");
		sm_PackageNames.put(ElementName.CREATELINK, JDFLIB + "jmf.JDFCreateLink");
		sm_PackageNames.put(ElementName.CREATERESOURCE, JDFLIB + "jmf.JDFCreateResource");
		sm_PackageNames.put(ElementName.CREDITCARD, JDFLIB + "resource.JDFCreditCard");
		sm_PackageNames.put(ElementName.CUSTOMERINFO, JDFLIB + "core.JDFCustomerInfo");
		sm_PackageNames.put(ElementName.CUSTOMERMESSAGE, JDFLIB + "core.JDFCustomerMessage");
		sm_PackageNames.put(ElementName.CUT, JDFLIB + "resource.process.postpress.JDFCut");
		sm_PackageNames.put(ElementName.CUTBLOCK, JDFLIB + "resource.process.JDFCutBlock");
		sm_PackageNames.put(ElementName.CUTMARK, JDFLIB + "resource.process.postpress.JDFCutMark");
		sm_PackageNames.put(ElementName.CUTTINGPARAMS, JDFLIB + "resource.JDFCuttingParams");
		sm_PackageNames.put(ElementName.CUTTYPE, JDFLIB + "span.JDFSpanCutType");
		sm_PackageNames.put(ElementName.CYLINDERLAYOUT, JDFLIB + "resource.process.JDFCylinderLayout");
		sm_PackageNames.put(ElementName.CYLINDERLAYOUTPREPARATIONPARAMS, JDFLIB + "resource.process.JDFCylinderLayoutPreparationParams");
		sm_PackageNames.put(ElementName.CYLINDERPOSITION, JDFLIB + "resource.process.JDFCylinderPosition");
		sm_PackageNames.put(ElementName.DATETIMEEVALUATION, JDFLIB + "resource.devicecapability.JDFDateTimeEvaluation");
		sm_PackageNames.put(ElementName.DATETIMESTATE, JDFLIB + "resource.devicecapability.JDFDateTimeState");
		sm_PackageNames.put(ElementName.DCTPARAMS, JDFLIB + "resource.process.JDFDCTParams");
		sm_PackageNames.put(ElementName.DBMERGEPARAMS, JDFLIB + "resource.process.JDFDBMergeParams");
		sm_PackageNames.put(ElementName.DBRULES, JDFLIB + "resource.process.JDFDBRules");
		sm_PackageNames.put(ElementName.DBSCHEMA, JDFLIB + "resource.JDFDBSchema");
		sm_PackageNames.put(ElementName.DBSELECTION, JDFLIB + "resource.process.JDFDBSelection");
		sm_PackageNames.put(ElementName.DELETED, JDFLIB + "resource.JDFDeleted");
		sm_PackageNames.put(ElementName.DELIVERYCHARGE, JDFLIB + "span.JDFSpanDeliveryCharge");
		sm_PackageNames.put(ElementName.DELIVERYINTENT, JDFLIB + "resource.intent.JDFDeliveryIntent");
		sm_PackageNames.put(ElementName.DELIVERYPARAMS, JDFLIB + "resource.process.JDFDeliveryParams");
		sm_PackageNames.put(ElementName.DENSITYMEASURINGFIELD, JDFLIB + "resource.process.JDFDensityMeasuringField");
		sm_PackageNames.put(ElementName.DEPENDENCIES, JDFLIB + "resource.process.JDFDependencies");
		sm_PackageNames.put(ElementName.DEVCAP, JDFLIB + "resource.devicecapability.JDFDevCap");
		sm_PackageNames.put(ElementName.DEVCAPPOOL, JDFLIB + "resource.devicecapability.JDFDevCapPool");
		sm_PackageNames.put(ElementName.DEVCAPS, JDFLIB + "resource.devicecapability.JDFDevCaps");
		sm_PackageNames.put(ElementName.DEVELOPINGPARAMS, JDFLIB + "resource.JDFDevelopingParams");
		sm_PackageNames.put(ElementName.DEVICE, JDFLIB + "resource.JDFDevice");
		sm_PackageNames.put(ElementName.DEVICECAP, JDFLIB + "resource.devicecapability.JDFDeviceCap");
		sm_PackageNames.put(ElementName.DEVICECOLORANTORDER, SEPARATION_LIST);
		sm_PackageNames.put(ElementName.DEVICEFILTER, JDFLIB + "jmf.JDFDeviceFilter");
		sm_PackageNames.put(ElementName.DEVICEINFO, JDFLIB + "jmf.JDFDeviceInfo");
		sm_PackageNames.put(ElementName.DEVICELIST, JDFLIB + "resource.JDFDeviceList");
		sm_PackageNames.put(ElementName.DEVICEMARK, JDFLIB + "resource.JDFDeviceMark");
		sm_PackageNames.put(ElementName.DEVICENCOLOR, JDFLIB + "resource.process.JDFDeviceNColor");
		sm_PackageNames.put(ElementName.DEVICENSPACE, JDFLIB + "resource.process.JDFDeviceNSpace");
		sm_PackageNames.put(ElementName.DIELAYOUT, JDFLIB + "resource.process.JDFDieLayout");
		sm_PackageNames.put(ElementName.DIELAYOUTPRODUCTIONPARAMS, JDFLIB + "resource.process.JDFDieLayoutProductionParams");
		sm_PackageNames.put(ElementName.DIGITALDELIVERYPARAMS, JDFLIB + "resource.process.prepress.JDFDigitalDeliveryParams");
		sm_PackageNames.put(ElementName.DIGITALMEDIA, JDFLIB + "resource.process.JDFDigitalMedia");
		sm_PackageNames.put(ElementName.DIGITALPRINTINGPARAMS, JDFLIB + "resource.process.JDFDigitalPrintingParams");
		sm_PackageNames.put(ElementName.DIMENSIONS, JDFLIB + "span.JDFXYPairSpan");
		sm_PackageNames.put(ElementName.DIRECTION, JDFLIB + "span.JDFSpanDirection");
		sm_PackageNames.put(ElementName.DISJOINTING, JDFLIB + "resource.process.JDFDisjointing");
		sm_PackageNames.put(ElementName.DISPLAYGROUP, JDFLIB + "resource.devicecapability.JDFDisplayGroup");
		sm_PackageNames.put(ElementName.DISPLAYGROUPPOOL, JDFLIB + "resource.devicecapability.JDFDisplayGroupPool");
		sm_PackageNames.put(ElementName.DISPOSITION, JDFLIB + "resource.process.JDFDisposition");
		sm_PackageNames.put(ElementName.DIVIDINGPARAMS, JDFLIB + "resource.process.JDFDividingParams");
		sm_PackageNames.put(ElementName.DOCUMENTRESULTSPOOL, JDFLIB + "resource.process.JDFDocumentResultsPool");
		sm_PackageNames.put(ElementName.DOTSIZE, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.DROP, JDFLIB + "resource.process.JDFDrop");
		sm_PackageNames.put(ElementName.DROPINTENT, JDFLIB + "resource.intent.JDFDropIntent");
		sm_PackageNames.put(ElementName.DROPITEM, JDFLIB + "resource.process.JDFDropItem");
		sm_PackageNames.put(ElementName.DROPITEMINTENT, JDFLIB + "resource.intent.JDFDropItemIntent");
		sm_PackageNames.put(ElementName.DURATIONEVALUATION, JDFLIB + "resource.devicecapability.JDFDurationEvaluation");
		sm_PackageNames.put(ElementName.DURATIONSTATE, JDFLIB + "resource.devicecapability.JDFDurationState");
		sm_PackageNames.put(ElementName.DYNAMICFIELD, JDFLIB + "resource.process.JDFDynamicField");
		sm_PackageNames.put(ElementName.DYNAMICINPUT, JDFLIB + "resource.process.JDFDynamicInput");

		sm_PackageNames.put(ElementName.EARLIEST, TIME_SPAN);
		sm_PackageNames.put(ElementName.EARLIESTDURATION, DURATION_SPAN);
		sm_PackageNames.put(ElementName.EDGEANGLE, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.EDGEGLUE, JDFLIB + "span.JDFSpanGlue");
		sm_PackageNames.put(ElementName.EDGEGLUING, JDFLIB + "resource.JDFEdgeGluing");
		sm_PackageNames.put(ElementName.EDGESHAPE, JDFLIB + "span.JDFSpanEdgeShape");
		sm_PackageNames.put(ElementName.ELEMENTCOLORPARAMS, JDFLIB + "resource.process.JDFElementColorParams");
		sm_PackageNames.put(ElementName.EMBOSS, JDFLIB + "resource.JDFEmboss");
		sm_PackageNames.put(ElementName.EMBOSSINGINTENT, JDFLIB + "resource.intent.JDFEmbossingIntent");
		sm_PackageNames.put(ElementName.EMBOSSINGITEM, JDFLIB + "resource.JDFEmbossingItem");
		sm_PackageNames.put(ElementName.EMBOSSINGPARAMS, JDFLIB + "resource.JDFEmbossingParams");
		sm_PackageNames.put(ElementName.EMBOSSINGTYPE, STRING_SPAN);
		sm_PackageNames.put(ElementName.EMPLOYEE, JDFLIB + "resource.process.JDFEmployee");
		sm_PackageNames.put(ElementName.EMPLOYEEDEF, JDFLIB + "jmf.JDFEmployeeDef");
		sm_PackageNames.put(ElementName.ENDSHEET, JDFLIB + "resource.process.postpress.JDFEndSheet");
		sm_PackageNames.put(ElementName.ENDSHEETGLUINGPARAMS, JDFLIB + "resource.JDFEndSheetGluingParams");
		sm_PackageNames.put(ElementName.ENDSHEETS, OPTION_SPAN);
		sm_PackageNames.put(ElementName.ENUMERATIONEVALUATION, JDFLIB + "resource.devicecapability.JDFEnumerationEvaluation");
		sm_PackageNames.put(ElementName.ENUMERATIONSTATE, JDFLIB + "resource.devicecapability.JDFEnumerationState");
		sm_PackageNames.put(ElementName.ERROR, JDFLIB + "resource.JDFError");
		sm_PackageNames.put(ElementName.ERRORDATA, JDFLIB + "resource.JDFErrorData");
		sm_PackageNames.put(ElementName.EVENT, JDFLIB + "resource.JDFEvent");
		sm_PackageNames.put(ElementName.EXPOSEDMEDIA, JDFLIB + "resource.process.JDFExposedMedia");
		sm_PackageNames.put(ElementName.EXPR, JDFLIB + "resource.process.JDFExpr");
		sm_PackageNames.put(ElementName.EXTENDEDADDRESS, JDF_COMMENT);
		sm_PackageNames.put(ElementName.EXTERNALIMPOSITIONTEMPLATE, JDFLIB + "resource.process.JDFExternalImpositionTemplate");
		sm_PackageNames.put(ElementName.EXTRAVALUES, JDFLIB + "resource.process.JDFExtraValues");

		sm_PackageNames.put(ElementName.FCNKEY, JDFLIB + "resource.JDFFCNKey");
		sm_PackageNames.put(ElementName.FEATUREATTRIBUTE, JDFLIB + "resource.devicecapability.JDFFeatureAttribute");
		sm_PackageNames.put(ElementName.FEATUREPOOL, JDFLIB + "resource.devicecapability.JDFFeaturePool");
		sm_PackageNames.put(ElementName.FEEDER, JDFLIB + "resource.process.JDFFeeder");
		sm_PackageNames.put(ElementName.FEEDERQUALITYPARAMS, JDFLIB + "resource.process.JDFFeederQualityParams");
		sm_PackageNames.put(ElementName.FEEDINGPARAMS, JDFLIB + "resource.process.JDFFeedingParams");
		sm_PackageNames.put(ElementName.FILEALIAS, JDFLIB + "resource.process.JDFFileAlias");
		sm_PackageNames.put(ElementName.FILESPEC, JDFLIB + "resource.process.JDFFileSpec");
		sm_PackageNames.put(ElementName.FILETYPERESULTSPOOL, JDFLIB + "resource.process.prepress.JDFFileTypeResultsPool");
		sm_PackageNames.put(ElementName.FILLCOLOR, JDFLIB + "resource.process.JDFFillColor");
		sm_PackageNames.put(ElementName.FILLMARK, JDFLIB + "resource.process.JDFFillMark");
		sm_PackageNames.put(ElementName.FINISHEDDIMENSIONS, SHAPE_SPAN);
		sm_PackageNames.put(ElementName.FINISHEDGRAINDIRECTION, JDFLIB + "span.JDFSpanFinishedGrainDirection");
		sm_PackageNames.put(ElementName.FITPOLICY, JDFLIB + "resource.JDFFitPolicy");
		sm_PackageNames.put(ElementName.FLATEPARAMS, JDFLIB + "resource.process.JDFFlateParams");
		sm_PackageNames.put(ElementName.FLUSHEDRESOURCES, JDFLIB + "jmf.JDFFlushedResources");
		sm_PackageNames.put(ElementName.FLUSHQUEUEINFO, JDFLIB + "jmf.JDFFlushQueueInfo");
		sm_PackageNames.put(ElementName.FLUSHQUEUEPARAMS, JDFLIB + "jmf.JDFFlushQueueParams");
		sm_PackageNames.put(ElementName.FLUSHRESOURCEPARAMS, JDFLIB + "jmf.JDFFlushResourceParams");
		sm_PackageNames.put(ElementName.FLUTE, NAME_SPAN);
		sm_PackageNames.put(ElementName.FLUTEDIRECTION, JDFLIB + "span.JDFSpanFluteDirection");
		sm_PackageNames.put(ElementName.FOILCOLOR, SPAN_NAMED_COLOR);
		sm_PackageNames.put(ElementName.FOILCOLORDETAILS, STRING_SPAN);
		sm_PackageNames.put(ElementName.FOLD, JDFLIB + "resource.process.postpress.JDFFold");
		sm_PackageNames.put(ElementName.FOLDERPRODUCTION, JDFLIB + "resource.process.JDFFolderProduction");
		sm_PackageNames.put(ElementName.FOLDINGCATALOG, NAME_SPAN);
		sm_PackageNames.put(ElementName.FOLDINGINTENT, JDFLIB + "resource.intent.JDFFoldingIntent");
		sm_PackageNames.put(ElementName.FOLDINGPARAMS, JDFLIB + "resource.process.postpress.JDFFoldingParams");
		sm_PackageNames.put(ElementName.FOLDINGWIDTH, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.FOLDINGWIDTHBACK, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.FOLDOPERATION, JDFLIB + "resource.process.JDFFoldOperation");
		sm_PackageNames.put(ElementName.FONTPARAMS, JDFLIB + "resource.process.JDFFontParams");
		sm_PackageNames.put(ElementName.FONTPOLICY, JDFLIB + "resource.process.JDFFontPolicy");
		sm_PackageNames.put(ElementName.FONTSRESULTSPOOL, JDFLIB + "resource.process.prepress.JDFFontsResultsPool");
		sm_PackageNames.put(ElementName.FORMATCONVERSIONPARAMS, JDFLIB + "resource.JDFFormatConversionParams");
		sm_PackageNames.put(ElementName.FREQUENCY, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.FREQUENCYSELECTION, JDFLIB + "span.JDFSpanFrequencySelection");
		sm_PackageNames.put(ElementName.FRONTCOATINGS, JDFLIB + "span.JDFSpanCoatings");

		sm_PackageNames.put(ElementName.GANGCMDFILTER, JDFLIB + "jmf.JDFGangCmdFilter");
		sm_PackageNames.put(ElementName.GANGELEMENT, JDFLIB + "resource.process.JDFGangElement");
		sm_PackageNames.put(ElementName.GANGINFO, JDFLIB + "jmf.JDFGangInfo");
		sm_PackageNames.put(ElementName.GANGQUFILTER, JDFLIB + "jmf.JDFGangQuFilter");
		sm_PackageNames.put(ElementName.GATHERINGPARAMS, JDFLIB + "resource.JDFGatheringParams");
		sm_PackageNames.put(ElementName.GENERALID, JDFLIB + "resource.process.JDFGeneralID");
		sm_PackageNames.put(ElementName.GLUE, JDFLIB + "resource.process.postpress.JDFGlue");
		sm_PackageNames.put(ElementName.GLUEAPPLICATION, JDFLIB + "resource.process.postpress.JDFGlueApplication");
		sm_PackageNames.put(ElementName.GLUELINE, JDFLIB + "resource.process.postpress.JDFGlueLine");
		sm_PackageNames.put(ElementName.GLUEPROCEDURE, JDFLIB + "span.JDFSpanGlueProcedure");
		sm_PackageNames.put(ElementName.GLUETYPE, JDFLIB + "span.JDFSpanGlueType");
		sm_PackageNames.put(ElementName.GLUINGPARAMS, JDFLIB + "resource.JDFGluingParams");
		sm_PackageNames.put(ElementName.GRADE, INTEGER_SPAN);
		sm_PackageNames.put(ElementName.GRAINDIRECTION, JDFLIB + "span.JDFSpanGrainDirection");
		sm_PackageNames.put(ElementName.HALFTONE, OPTION_SPAN);
		sm_PackageNames.put(ElementName.HARDCOVERBINDING, JDFLIB + "resource.JDFHardCoverBinding");
		sm_PackageNames.put(ElementName.HEADBANDAPPLICATIONPARAMS, JDFLIB + "resource.JDFHeadBandApplicationParams");
		sm_PackageNames.put(ElementName.HEADBANDCOLOR, SPAN_NAMED_COLOR);
		sm_PackageNames.put(ElementName.HEADBANDCOLORDETAILS, STRING_SPAN);
		sm_PackageNames.put(ElementName.HEADBANDS, OPTION_SPAN);
		sm_PackageNames.put(ElementName.HEIGHT, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.HOLDQUEUEENTRYPARAMS, JDFLIB + "jmf.JDFHoldQueueEntryParams");
		sm_PackageNames.put(ElementName.HOLE, JDFLIB + "resource.process.postpress.JDFHole");
		sm_PackageNames.put(ElementName.HOLECOUNT, INTEGER_SPAN);
		sm_PackageNames.put(ElementName.HOLELINE, JDFLIB + "resource.JDFHoleLine");
		sm_PackageNames.put(ElementName.HOLELIST, JDFLIB + "resource.process.postpress.JDFHoleList");
		sm_PackageNames.put(ElementName.HOLEMAKINGINTENT, JDFLIB + "resource.intent.JDFHoleMakingIntent");
		sm_PackageNames.put(ElementName.HOLEMAKINGPARAMS, JDFLIB + "resource.process.postpress.JDFHoleMakingParams");
		// "HoleType" is context sensitive, see handleOtherElements() and
		// putConstructorToHashMap()
		sm_PackageNames.put(ElementName.ICON, JDFLIB + "resource.JDFIcon");
		sm_PackageNames.put(ElementName.ICONLIST, JDFLIB + "resource.JDFIconList");
		sm_PackageNames.put(ElementName.IDENTICAL, JDFLIB + "resource.process.JDFIdentical");
		sm_PackageNames.put(ElementName.IDENTIFICATIONFIELD, JDFLIB + "resource.process.JDFIdentificationField");
		sm_PackageNames.put(ElementName.IDINFO, JDFLIB + "jmf.JDFIDInfo");
		sm_PackageNames.put(ElementName.IDPCOVER, JDFLIB + "resource.JDFIDPCover");
		sm_PackageNames.put(ElementName.IDPFINISHING, JDFLIB + "resource.process.JDFIDPFinishing");
		sm_PackageNames.put(ElementName.IDPFOLDING, JDFLIB + "resource.process.JDFIDPFolding");
		sm_PackageNames.put(ElementName.IDPHOLEMAKING, JDFLIB + "resource.process.JDFIDPHoleMaking");
		sm_PackageNames.put(ElementName.IDPIMAGESHIFT, JDFLIB + "resource.JDFIDPImageShift");
		sm_PackageNames.put(ElementName.IDPJOBSHEET, JDFLIB + "resource.JDFIDPJobSheet");
		sm_PackageNames.put(ElementName.IDPLAYOUT, JDFLIB + "resource.process.JDFIDPLayout");
		sm_PackageNames.put(ElementName.IDPRINTINGPARAMS, JDFLIB + "resource.process.press.JDFIDPrintingParams");
		sm_PackageNames.put(ElementName.IDPSTITCHING, JDFLIB + "resource.process.JDFIDPStitching");
		sm_PackageNames.put(ElementName.IDPTRIMMING, JDFLIB + "resource.process.JDFIDPTrimming");
		sm_PackageNames.put(ElementName.IMAGECOMPRESSION, JDFLIB + "resource.JDFImageCompression");
		sm_PackageNames.put(ElementName.IMAGECOMPRESSIONPARAMS, JDFLIB + "resource.process.JDFImageCompressionParams");
		sm_PackageNames.put(ElementName.IMAGEREPLACEMENTPARAMS, JDFLIB + "resource.process.JDFImageReplacementParams");
		sm_PackageNames.put(ElementName.IMAGEENHANCEMENTOP, JDFLIB + "resource.process.JDFImageEnhancementOp");
		sm_PackageNames.put(ElementName.IMAGEENHANCEMENTPARAMS, JDFLIB + "resource.process.JDFImageEnhancementParams");
		sm_PackageNames.put(ElementName.IMAGESETTERPARAMS, JDFLIB + "resource.process.JDFImageSetterParams");
		sm_PackageNames.put(ElementName.IMAGESHIFT, JDFLIB + "resource.JDFImageShift");
		sm_PackageNames.put(ElementName.IMAGESIZE, JDFLIB + "span.JDFXYPairSpan");
		sm_PackageNames.put(ElementName.IMAGESRESULTSPOOL, JDFLIB + "resource.process.JDFImagesResultsPool");
		sm_PackageNames.put(ElementName.IMAGESTRATEGY, JDFLIB + "span.JDFSpanImageStrategy");
		sm_PackageNames.put(ElementName.INK, JDFLIB + "resource.process.prepress.JDFInk");
		sm_PackageNames.put(ElementName.INKMANUFACTURER, NAME_SPAN);
		sm_PackageNames.put(ElementName.INKZONECALCULATIONPARAMS, JDFLIB + "resource.process.prepress.JDFInkZoneCalculationParams");
		sm_PackageNames.put(ElementName.INKZONEPROFILE, JDFLIB + "resource.process.prepress.JDFInkZoneProfile");
		sm_PackageNames.put(ElementName.INSERT, JDFLIB + "resource.JDFInsert");
		sm_PackageNames.put(ElementName.INSERTINGINTENT, JDFLIB + "resource.intent.JDFInsertingIntent");
		sm_PackageNames.put(ElementName.INSERTINGPARAMS, JDFLIB + "resource.JDFInsertingParams");
		sm_PackageNames.put(ElementName.INSERTLIST, JDFLIB + "resource.JDFInsertList");
		sm_PackageNames.put(ElementName.INSERTSHEET, JDFLIB + "resource.process.JDFInsertSheet");
		sm_PackageNames.put(ElementName.INTEGEREVALUATION, JDFLIB + "resource.devicecapability.JDFIntegerEvaluation");
		sm_PackageNames.put(ElementName.INTEGERSTATE, JDFLIB + "resource.devicecapability.JDFIntegerState");
		sm_PackageNames.put(ElementName.INTENTRESOURCE, JDFLIB + "resource.intent.JDFIntentResource");
		sm_PackageNames.put(ElementName.INTERPRETEDPDLDATA, JDFLIB + "resource.process.JDFInterpretedPDLData");
		sm_PackageNames.put(ElementName.INTERPRETINGDETAILS, JDFLIB + "resource.process.prepress.JDFInterpretingDetails");
		sm_PackageNames.put(ElementName.INTERPRETINGPARAMS, JDFLIB + "resource.JDFInterpretingParams");
		sm_PackageNames.put(ElementName.ISOPAPERSUBSTRATE, JDFLIB + "span.JDFSpanISOPaperSubstrate");
		sm_PackageNames.put(ElementName.ISPRESENTEVALUATION, JDFLIB + "resource.devicecapability.JDFIsPresentEvaluation");
		sm_PackageNames.put(ElementName.ISSUEDATE, TIME_SPAN);
		sm_PackageNames.put(ElementName.ISSUENAME, STRING_SPAN);
		sm_PackageNames.put(ElementName.ISSUETYPE, NAME_SPAN);

		sm_PackageNames.put(ElementName.JACKET, JDFLIB + "span.JDFSpanJacket");
		sm_PackageNames.put(ElementName.JACKETFOLDINGWIDTH, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.JACKETINGPARAMS, JDFLIB + "resource.JDFJacketingParams");
		sm_PackageNames.put(ElementName.JAPANBIND, OPTION_SPAN);
		sm_PackageNames.put(ElementName.JBIG2PARAMS, JDFLIB + "resource.process.JDFJBIG2Params");
		sm_PackageNames.put(ElementName.JDFCONTROLLER, JDFLIB + "jmf.JDFJDFController");
		sm_PackageNames.put(ElementName.JDFSERVICE, JDFLIB + "jmf.JDFJDFService");
		sm_PackageNames.put(ElementName.JOBFIELD, JDFLIB + "resource.JDFJobField");
		sm_PackageNames.put(ElementName.JOBPHASE, JDFLIB + "jmf.JDFJobPhase");
		sm_PackageNames.put(ElementName.JOBSHEET, JDFLIB + "resource.JDFJobSheet");
		sm_PackageNames.put(ElementName.JPEG2000PARAMS, JDFLIB + "resource.process.JDFJPEG2000Params");
		sm_PackageNames.put(ElementName.KNOWNMSGQUPARAMS, JDFLIB + "jmf.JDFKnownMsgQuParams");
		sm_PackageNames.put(ElementName.LABELINGPARAMS, JDFLIB + "resource.JDFLabelingParams");
		sm_PackageNames.put(ElementName.LAMINATED, OPTION_SPAN);
		sm_PackageNames.put(ElementName.LAMINATINGINTENT, JDFLIB + "resource.intent.JDFLaminatingIntent");
		sm_PackageNames.put(ElementName.LAMINATINGPARAMS, JDFLIB + "resource.JDFLaminatingParams");
		sm_PackageNames.put(ElementName.LAYERDETAILS, JDFLIB + "resource.JDFLayerDetails");
		sm_PackageNames.put(ElementName.LAYERLIST, JDFLIB + "resource.JDFLayerList");
		sm_PackageNames.put(ElementName.LAYOUT, LAYOUT);
		sm_PackageNames.put(ElementName.LAYOUTELEMENT, JDFLIB + "resource.process.JDFLayoutElement");
		sm_PackageNames.put(ElementName.LAYOUTELEMENTPART, JDFLIB + "resource.process.JDFLayoutElementPart");
		sm_PackageNames.put(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS, JDFLIB + "resource.process.JDFLayoutElementProductionParams");
		sm_PackageNames.put(ElementName.LAYOUTINTENT, JDFLIB + "resource.intent.JDFLayoutIntent");
		sm_PackageNames.put(ElementName.LAYOUTPREPARATIONPARAMS, JDFLIB + "resource.JDFLayoutPreparationParams");
		sm_PackageNames.put(ElementName.LAYOUTSHIFT, JDFLIB + "resource.process.JDFLayoutShift");
		sm_PackageNames.put(ElementName.LEVEL, JDFLIB + "span.JDFSpanLevel");
		sm_PackageNames.put(ElementName.LOC, JDFLIB + "resource.devicecapability.JDFLoc");
		sm_PackageNames.put(ElementName.LOCATION, JDFLIB + "resource.JDFLocation");
		sm_PackageNames.put(ElementName.LOGICALSTACKPARAMS, JDFLIB + "resource.process.JDFLogicalStackParams");
		sm_PackageNames.put(ElementName.LONGFOLD, JDFLIB + "resource.process.JDFLongFold");
		sm_PackageNames.put(ElementName.LONGGLUE, JDFLIB + "resource.process.JDFLongGlue");
		sm_PackageNames.put(ElementName.LONGITUDINALRIBBONOPERATIONPARAMS, JDFLIB + "resource.process.JDFLongitudinalRibbonOperationParams");
		sm_PackageNames.put(ElementName.LONGPERFORATE, JDFLIB + "resource.process.JDFLongPerforate");
		sm_PackageNames.put(ElementName.LONGSLIT, JDFLIB + "resource.process.JDFLongSlit");
		sm_PackageNames.put(ElementName.LOT, JDFLIB + "resource.process.JDFLot");
		sm_PackageNames.put(ElementName.LZWPARAMS, JDFLIB + "resource.process.JDFLZWParams");

		sm_PackageNames.put(ElementName.MACRO, JDFLIB + "resource.devicecapability.JDFmacro");
		sm_PackageNames.put(ElementName.MACROPOOL, JDFLIB + "resource.devicecapability.JDFMacroPool");
		sm_PackageNames.put(ElementName.MANUALLABORPARAMS, JDFLIB + "resource.process.JDFManualLaborParams");
		sm_PackageNames.put(ElementName.MARKCOLOR, JDFLIB + "resource.process.JDFMarkColor");
		sm_PackageNames.put(ElementName.MARKOBJECT, JDFLIB + "resource.JDFMarkObject");
		sm_PackageNames.put(ElementName.MARKACTIVATION, JDFLIB + "resource.JDFMarkActivation");
		sm_PackageNames.put(ElementName.MATERIAL, STRING_SPAN);
		sm_PackageNames.put(ElementName.MATRIXEVALUATION, JDFLIB + "resource.devicecapability.JDFMatrixEvaluation");
		sm_PackageNames.put(ElementName.MATRIXSTATE, JDFLIB + "resource.devicecapability.JDFMatrixState");
		sm_PackageNames.put(ElementName.MEDIA, JDFLIB + "resource.process.JDFMedia");
		sm_PackageNames.put(ElementName.MEDIACOLOR, SPAN_NAMED_COLOR);
		sm_PackageNames.put(ElementName.MEDIACOLORDETAILS, STRING_SPAN);
		sm_PackageNames.put(ElementName.MEDIAINTENT, JDFLIB + "resource.intent.JDFMediaIntent");
		sm_PackageNames.put(ElementName.MEDIALAYERS, JDFLIB + "resource.process.JDFMediaLayers");
		sm_PackageNames.put(ElementName.MEDIAQUALITY, STRING_SPAN);
		sm_PackageNames.put(ElementName.MEDIASOURCE, JDFLIB + "resource.process.JDFMediaSource");
		sm_PackageNames.put(ElementName.MEDIATYPE, JDFLIB + "span.JDFSpanMediaType");
		sm_PackageNames.put(ElementName.MEDIATYPEDETAILS, STRING_SPAN);
		sm_PackageNames.put(ElementName.MEDIAUNIT, JDFLIB + "span.JDFSpanMediaUnit");
		sm_PackageNames.put(ElementName.MERGED, JDFLIB + "resource.JDFMerged");
		sm_PackageNames.put(ElementName.MESSAGE, JDFLIB + "jmf.JDFMessage");
		sm_PackageNames.put(ElementName.MESSAGESERVICE, JDFLIB + "jmf.JDFMessageService");
		sm_PackageNames.put(ElementName.METADATAMAP, JDFLIB + "resource.process.JDFMetadataMap");
		// "Method" is context sensitive, see handleOtherElements() and
		// putConstructorToHashMap()
		sm_PackageNames.put(ElementName.MILESTONE, JDFLIB + "resource.JDFMilestone");
		sm_PackageNames.put(ElementName.MISCCONSUMABLE, JDFLIB + "resource.process.JDFMiscConsumable");
		sm_PackageNames.put(ElementName.MISDETAILS, JDFLIB + "resource.process.JDFMISDetails");
		sm_PackageNames.put(ElementName.MODIFIED, JDFLIB + "resource.JDFModified");
		sm_PackageNames.put(ElementName.MODIFYNODECMDPARAMS, JDFLIB + "jmf.JDFModifyNodeCmdParams");
		sm_PackageNames.put(ElementName.MODULE, JDFLIB + "resource.devicecapability.JDFModule");
		sm_PackageNames.put(ElementName.MODULECAP, JDFLIB + "resource.devicecapability.JDFModuleCap");
		sm_PackageNames.put(ElementName.MODULEPHASE, JDFLIB + "resource.JDFModulePhase");
		sm_PackageNames.put(ElementName.MODULEPOOL, JDFLIB + "resource.devicecapability.JDFModulePool");
		sm_PackageNames.put(ElementName.MODULESTATUS, JDFLIB + "resource.JDFModuleStatus");
		sm_PackageNames.put(ElementName.MOVERESOURCE, JDFLIB + "jmf.JDFMoveResource");
		sm_PackageNames.put(ElementName.MSGFILTER, JDFLIB + "jmf.JDFMsgFilter");
		sm_PackageNames.put(ElementName.NAMEEVALUATION, JDFLIB + "resource.devicecapability.JDFNameEvaluation");
		sm_PackageNames.put(ElementName.NAMESTATE, JDFLIB + "resource.devicecapability.JDFNameState");
		sm_PackageNames.put(ElementName.NEWCOMMENT, JDFLIB + "jmf.JDFNewComment");
		sm_PackageNames.put(ElementName.NEWJDFCMDPARAMS, JDFLIB + "jmf.JDFNewJDFCmdParams");
		sm_PackageNames.put(ElementName.NEWJDFQUPARAMS, JDFLIB + "jmf.JDFNewJDFQuParams");
		sm_PackageNames.put(ElementName.NODEINFO, JDFLIB + "core.JDFNodeInfo");
		sm_PackageNames.put(ElementName.NODEINFOCMDPARAMS, JDFLIB + "jmf.JDFNodeInfoCmdParams");
		sm_PackageNames.put(ElementName.NODEINFOQUPARAMS, JDFLIB + "jmf.JDFNodeInfoQuParams");
		sm_PackageNames.put(ElementName.NODEINFORESP, JDFLIB + "jmf.JDFNodeInfoResp");
		sm_PackageNames.put(ElementName.NOT, JDFLIB + "resource.devicecapability.JDFnot");
		sm_PackageNames.put(ElementName.NOTIFICATION, JDFLIB + "resource.JDFNotification");
		sm_PackageNames.put(ElementName.NOTIFICATIONDEF, JDFLIB + "jmf.JDFNotificationDef");
		sm_PackageNames.put(ElementName.NOTIFICATIONFILTER, JDFLIB + "resource.process.JDFNotificationFilter");
		sm_PackageNames.put(ElementName.NUMBEREVALUATION, JDFLIB + "resource.devicecapability.JDFNumberEvaluation");
		sm_PackageNames.put(ElementName.NUMBERINGINTENT, JDFLIB + "resource.intent.JDFNumberingIntent");
		sm_PackageNames.put(ElementName.NUMBERINGPARAM, JDFLIB + "resource.process.JDFNumberingParam");
		sm_PackageNames.put(ElementName.NUMBERINGPARAMS, JDFLIB + "resource.JDFNumberingParams");
		sm_PackageNames.put(ElementName.NUMBERITEM, JDFLIB + "resource.JDFNumberItem");
		sm_PackageNames.put(ElementName.NUMBERSTATE, JDFLIB + "resource.devicecapability.JDFNumberState");

		sm_PackageNames.put(ElementName.OBJECTMODEL, JDFLIB + "resource.process.JDFObjectModel");
		sm_PackageNames.put(ElementName.OBJECTRESOLUTION, JDFLIB + "resource.process.JDFObjectResolution");
		sm_PackageNames.put(ElementName.OBSERVATIONTARGET, JDFLIB + "resource.JDFObservationTarget");
		sm_PackageNames.put(ElementName.OCCUPATION, JDFLIB + "jmf.JDFOccupation");
		sm_PackageNames.put(ElementName.OCGCONTROL, JDFLIB + "resource.process.JDFOCGControl");
		sm_PackageNames.put(ElementName.OFFERRANGE, JDF_COMMENT);
		sm_PackageNames.put(ElementName.OPACITY, JDFLIB + "span.JDFSpanOpacity");
		sm_PackageNames.put(ElementName.OPACITYLEVEL, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.OR, JDFLIB + "resource.devicecapability.JDFor");
		sm_PackageNames.put(ElementName.ORDERINGPARAMS, JDFLIB + "resource.process.JDFOrderingParams");
		sm_PackageNames.put(ElementName.ORGANIZATIONALUNIT, JDF_COMMENT);
		sm_PackageNames.put(ElementName.ORIENTATION, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.OTHERWISE, JDFLIB + "resource.devicecapability.JDFotherwise");
		sm_PackageNames.put(ElementName.OVERAGE, NUMBER_SPAN);

		sm_PackageNames.put(ElementName.PACKINGINTENT, JDFLIB + "resource.intent.JDFPackingIntent");
		sm_PackageNames.put(ElementName.PACKINGPARAMS, JDFLIB + "resource.process.JDFPackingParams");
		sm_PackageNames.put(ElementName.PAGEASSIGNEDLIST, JDFLIB + "resource.process.JDFPageAssignedList");
		sm_PackageNames.put(ElementName.PAGEASSIGNPARAMS, JDFLIB + "resource.process.JDFPageAssignParams");
		sm_PackageNames.put(ElementName.PAGECELL, JDFLIB + "resource.JDFPageCell");
		sm_PackageNames.put(ElementName.PAGECONDITION, JDFLIB + "resource.JDFPageCondition");
		sm_PackageNames.put(ElementName.PAGEDATA, JDFLIB + "resource.process.JDFPageData");
		sm_PackageNames.put(ElementName.PAGEELEMENT, JDFLIB + "resource.process.JDFPageElement");
		sm_PackageNames.put(ElementName.PAGELIST, JDFLIB + "resource.JDFPageList");
		sm_PackageNames.put(ElementName.PAGES, INTEGER_SPAN);
		sm_PackageNames.put(ElementName.PAGESRESULTSPOOL, JDFLIB + "resource.process.prepress.JDFPagesResultsPool");
		sm_PackageNames.put(ElementName.PAGEVARIANCE, INTEGER_SPAN);
		sm_PackageNames.put(ElementName.PALLET, JDFLIB + "resource.JDFPallet");
		sm_PackageNames.put(ElementName.PALLETCORNERBOARDS, NAME_SPAN);
		sm_PackageNames.put(ElementName.PALLETIZINGPARAMS, JDFLIB + "resource.JDFPalletizingParams");
		sm_PackageNames.put(ElementName.PALLETMAXHEIGHT, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.PALLETMAXWEIGHT, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.PALLETQUANTITY, INTEGER_SPAN);
		sm_PackageNames.put(ElementName.PALLETSIZE, JDFLIB + "span.JDFXYPairSpan");
		sm_PackageNames.put(ElementName.PALLETTYPE, NAME_SPAN);
		sm_PackageNames.put(ElementName.PALLETWRAPPING, NAME_SPAN);
		sm_PackageNames.put(ElementName.PART, JDFLIB + "resource.JDFPart");
		sm_PackageNames.put(ElementName.PARTAMOUNT, JDFLIB + "core.JDFPartAmount");
		sm_PackageNames.put(ElementName.PARTSTATUS, JDFLIB + "core.JDFPartStatus");
		sm_PackageNames.put(ElementName.PAYMENT, JDFLIB + "resource.JDFPayment");
		sm_PackageNames.put(ElementName.PAYTERM, JDF_COMMENT);
		sm_PackageNames.put(ElementName.PDFINTERPRETINGPARAMS, JDFLIB + "resource.JDFPDFInterpretingParams");
		sm_PackageNames.put(ElementName.PDFPATHEVALUATION, JDFLIB + "resource.devicecapability.JDFPDFPathEvaluation");
		sm_PackageNames.put(ElementName.PDFPATHSTATE, JDFLIB + "resource.devicecapability.JDFPDFPathState");
		sm_PackageNames.put(ElementName.PDFTOPSCONVERSIONPARAMS, JDFLIB + "resource.process.prepress.JDFPDFToPSConversionParams");
		sm_PackageNames.put(ElementName.PDFXPARAMS, JDFLIB + "resource.process.JDFPDFXParams");
		sm_PackageNames.put(ElementName.PDLCREATIONPARAMS, JDFLIB + "resource.process.JDFPDLCreationParams");
		sm_PackageNames.put(ElementName.PDLRESOURCEALIAS, JDFLIB + "resource.process.prepress.JDFPDLResourceAlias");
		sm_PackageNames.put(ElementName.PERFORATE, JDFLIB + "resource.process.JDFPerforate");
		sm_PackageNames.put(ElementName.PERFORATINGPARAMS, JDFLIB + "resource.JDFPerforatingParams");
		sm_PackageNames.put(ElementName.PERFORMANCE, JDFLIB + "resource.JDFPerformance");
		sm_PackageNames.put(ElementName.PERSON, JDFLIB + "resource.process.JDFPerson");
		sm_PackageNames.put(ElementName.PHASETIME, JDFLIB + "resource.JDFPhaseTime");
		sm_PackageNames.put(ElementName.PIPEPARAMS, JDFLIB + "jmf.JDFPipeParams");
		sm_PackageNames.put(ElementName.PIXELCOLORANT, JDFLIB + "resource.process.JDFPixelColorant");
		sm_PackageNames.put(ElementName.PLACEHOLDERRESOURCE, JDFLIB + "resource.JDFPlaceHolderResource");
		sm_PackageNames.put(ElementName.PLASTICCOMBBINDING, JDFLIB + "resource.process.postpress.JDFPlasticCombBinding");
		sm_PackageNames.put(ElementName.PLASTICCOMBBINDINGPARAMS, JDFLIB + "resource.process.postpress.JDFPlasticCombBindingParams");
		sm_PackageNames.put(ElementName.PLASTICCOMBTYPE, NAME_SPAN);
		sm_PackageNames.put(ElementName.PLATECOPYPARAMS, JDFLIB + "resource.process.JDFPlateCopyParams");
		sm_PackageNames.put(ElementName.POSITION, JDFLIB + "resource.process.JDFPosition");
		sm_PackageNames.put(ElementName.POSITIONOBJ, JDFLIB + "resource.process.JDFPositionObj");
		sm_PackageNames.put(ElementName.PREFLIGHTACTION, JDFLIB + "resource.process.JDFPreflightAction");
		sm_PackageNames.put(ElementName.PREFLIGHTANALYSIS, JDFLIB + "resource.JDFPreflightAnalysis");
		sm_PackageNames.put(ElementName.PREFLIGHTARGUMENT, JDFLIB + "resource.process.JDFPreflightArgument");
		sm_PackageNames.put(ElementName.PREFLIGHTCONSTRAINT, JDFLIB + "resource.process.prepress.JDFPreflightConstraint");
		sm_PackageNames.put(ElementName.PREFLIGHTCONSTRAINTSPOOL, JDFLIB + "pool.JDFPreflightConstraintsPool");
		sm_PackageNames.put(ElementName.PREFLIGHTDETAIL, JDFLIB + "resource.process.prepress.JDFPreflightDetail");
		sm_PackageNames.put(ElementName.PREFLIGHTINSTANCE, JDFLIB + "resource.process.prepress.JDFPreflightInstance");
		sm_PackageNames.put(ElementName.PREFLIGHTINSTANCEDETAIL, JDFLIB + "resource.process.prepress.JDFPreflightInstanceDetail");
		sm_PackageNames.put(ElementName.PREFLIGHTINVENTORY, JDFLIB + "resource.process.prepress.JDFPreflightInventory");
		sm_PackageNames.put(ElementName.PREFLIGHTPARAMS, JDFLIB + "resource.process.JDFPreflightParams");
		sm_PackageNames.put(ElementName.PREFLIGHTPROFILE, JDFLIB + "resource.process.prepress.JDFPreflightProfile");
		sm_PackageNames.put(ElementName.PREFLIGHTREPORT, JDFLIB + "resource.process.JDFPreflightReport");
		sm_PackageNames.put(ElementName.PREFLIGHTREPORTRULEPOOL, JDFLIB + "resource.process.JDFPreflightReportRulePool");
		sm_PackageNames.put(ElementName.PREFLIGHTRESULTSPOOL, JDFLIB + "pool.JDFPreflightResultsPool");
		sm_PackageNames.put(ElementName.PRERROR, JDFLIB + "resource.process.JDFPRError");
		sm_PackageNames.put(ElementName.PREVIEW, JDFLIB + "resource.process.JDFPreview");
		sm_PackageNames.put(ElementName.PREVIEWGENERATIONPARAMS, JDFLIB + "resource.process.prepress.JDFPreviewGenerationParams");
		sm_PackageNames.put(ElementName.PRGROUP, JDFLIB + "resource.process.JDFPRGroup");
		sm_PackageNames.put(ElementName.PRGROUPOCCURRENCE, JDFLIB + "resource.process.JDFPRGroupOccurrence");
		sm_PackageNames.put(ElementName.PRICING, JDFLIB + "resource.intent.JDFPricing");
		sm_PackageNames.put(ElementName.PRINTCONDITION, JDFLIB + "resource.process.press.JDFPrintCondition");
		sm_PackageNames.put(ElementName.PRINTCONDITIONCOLOR, JDFLIB + "resource.process.JDFPrintConditionColor");
		sm_PackageNames.put(ElementName.PRINTPREFERENCE, JDFLIB + "span.JDFSpanPrintPreference");
		sm_PackageNames.put(ElementName.PRINTPROCESS, NAME_SPAN);
		sm_PackageNames.put(ElementName.PRINTROLLINGPARAMS, JDFLIB + "resource.process.JDFPrintRollingParams");
		sm_PackageNames.put(ElementName.PRITEM, JDFLIB + "resource.process.JDFPRItem");
		sm_PackageNames.put(ElementName.PROCCURRENCE, JDFLIB + "resource.process.JDFPROccurrence");
		sm_PackageNames.put(ElementName.PROCESSRUN, JDFLIB + "resource.JDFProcessRun");
		sm_PackageNames.put(ElementName.PRODUCTIONINTENT, JDFLIB + "resource.intent.JDFProductionIntent");
		sm_PackageNames.put(ElementName.PRODUCTIONPATH, JDFLIB + "resource.process.JDFProductionPath");

		sm_PackageNames.put(ElementName.PRODUCTIONSUBPATH, JDFLIB + "resource.process.JDFProductionSubPath");
		sm_PackageNames.put(ElementName.PROOFINGINTENT, JDFLIB + "resource.intent.JDFProofingIntent");
		sm_PackageNames.put(ElementName.PROOFINGPARAMS, JDFLIB + "resource.process.JDFProofingParams");
		sm_PackageNames.put(ElementName.PROOFITEM, JDFLIB + "resource.JDFProofItem");
		sm_PackageNames.put(ElementName.PROOFTYPE, JDFLIB + "span.JDFSpanProofType");
		sm_PackageNames.put(ElementName.PRRULE, JDFLIB + "resource.process.JDFPRRule");
		sm_PackageNames.put(ElementName.PRRULEATTR, JDFLIB + "resource.process.JDFPRRuleAttr");
		sm_PackageNames.put(ElementName.PSTOPDFCONVERSIONPARAMS, JDFLIB + "resource.process.prepress.JDFPSToPDFConversionParams");
		sm_PackageNames.put(ElementName.PUBLISHINGINTENT, JDFLIB + "resource.intent.JDFPublishingIntent");
		sm_PackageNames.put(ElementName.QUALITYCONTROLPARAMS, JDFLIB + "resource.process.JDFQualityControlParams");
		sm_PackageNames.put(ElementName.QUALITYCONTROLRESULT, JDFLIB + "resource.process.JDFQualityControlResult");
		sm_PackageNames.put(ElementName.QUALITYMEASUREMENT, JDFLIB + "resource.process.JDFQualityMeasurement");
		sm_PackageNames.put(ElementName.QUERY, JDFLIB + "jmf.JDFQuery");
		sm_PackageNames.put(ElementName.QUEUE, JDFLIB + "jmf.JDFQueue");
		sm_PackageNames.put(ElementName.QUEUEENTRY, JDFLIB + "jmf.JDFQueueEntry");
		sm_PackageNames.put(ElementName.QUEUEENTRYDEF, JDFLIB + "jmf.JDFQueueEntryDef");
		sm_PackageNames.put(ElementName.QUEUEENTRYDEFLIST, JDFLIB + "resource.JDFQueueEntryDefList");
		sm_PackageNames.put(ElementName.QUEUEENTRYPOSPARAMS, JDFLIB + "jmf.JDFQueueEntryPosParams");
		sm_PackageNames.put(ElementName.QUEUEENTRYPRIPARAMS, JDFLIB + "jmf.JDFQueueEntryPriParams");
		sm_PackageNames.put(ElementName.QUEUEFILTER, JDFLIB + "jmf.JDFQueueFilter");
		sm_PackageNames.put(ElementName.QUEUESUBMISSIONPARAMS, JDFLIB + "jmf.JDFQueueSubmissionParams");

		sm_PackageNames.put(ElementName.RANGE, JDF_COMMENT);
		sm_PackageNames.put(ElementName.RASTERREADINGPARAMS, JDFLIB + "resource.process.prepress.JDFRasterReadingParams");
		sm_PackageNames.put(ElementName.RECTANGLEEVALUATION, JDFLIB + "resource.devicecapability.JDFRectangleEvaluation");
		sm_PackageNames.put(ElementName.RECTANGLESTATE, JDFLIB + "resource.devicecapability.JDFRectangleState");
		sm_PackageNames.put(ElementName.RECYCLED, OPTION_SPAN);
		sm_PackageNames.put(ElementName.RECYCLEDPERCENTAGE, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.REFANCHOR, JDFLIB + "resource.JDFRefAnchor");
		sm_PackageNames.put(ElementName.REFELEMENT, JDFLIB + "core.JDFRefElement");
		sm_PackageNames.put(ElementName.REFERENCEXOBJPARAMS, JDFLIB + "resource.process.JDFReferenceXObjParams");
		sm_PackageNames.put(ElementName.REGISTERMARK, JDFLIB + "resource.process.JDFRegisterMark");
		sm_PackageNames.put(ElementName.REGISTERRIBBON, JDFLIB + "resource.JDFRegisterRibbon");
		sm_PackageNames.put(ElementName.REGISTRATION, JDFLIB + "jmf.JDFRegistration");
		sm_PackageNames.put(ElementName.REMOVED, JDFLIB + "resource.JDFRemoved");
		sm_PackageNames.put(ElementName.REMOVELINK, JDFLIB + "jmf.JDFRemoveLink");
		sm_PackageNames.put(ElementName.REMOVEQUEUEENTRYPARAMS, JDFLIB + "jmf.JDFRemoveQueueEntryParams");
		sm_PackageNames.put(ElementName.RENDERINGPARAMS, JDFLIB + "resource.process.prepress.JDFRenderingParams");
		sm_PackageNames.put(ElementName.REPEATDESC, JDFLIB + "resource.process.JDFRepeatDesc");
		sm_PackageNames.put(ElementName.REQUESTQUEUEENTRYPARAMS, JDFLIB + "jmf.JDFRequestQueueEntryParams");
		sm_PackageNames.put(ElementName.REQUIRED, TIME_SPAN);
		sm_PackageNames.put(ElementName.REQUIREDDURATION, DURATION_SPAN);
		sm_PackageNames.put(ElementName.RESOURCE, DocumentJDFImpl.CORE_JDFRESOURCE);
		sm_PackageNames.put(ElementName.RESOURCEAUDIT, JDFLIB + "resource.JDFResourceAudit");
		sm_PackageNames.put(ElementName.RESOURCECMDPARAMS, JDFLIB + "jmf.JDFResourceCmdParams");
		sm_PackageNames.put(ElementName.RESOURCEDEFINITIONPARAMS, JDFLIB + "resource.process.JDFResourceDefinitionParams");
		sm_PackageNames.put(ElementName.RESOURCEINFO, JDFLIB + "jmf.JDFResourceInfo");
		sm_PackageNames.put(ElementName.RESOURCELINK, JDFLIB + "core.JDFResourceLink");
		sm_PackageNames.put(ElementName.RESOURCELINKPOOL, JDFLIB + "pool.JDFResourceLinkPool");
		sm_PackageNames.put(ElementName.RESOURCEPARAM, JDFLIB + "resource.JDFResourceParam");
		sm_PackageNames.put(ElementName.RESOURCEPOOL, JDFLIB + "pool.JDFResourcePool");
		sm_PackageNames.put(ElementName.RESOURCEPULLPARAMS, JDFLIB + "jmf.JDFResourcePullParams");
		sm_PackageNames.put(ElementName.RESOURCEQUPARAMS, JDFLIB + "jmf.JDFResourceQuParams");
		sm_PackageNames.put(ElementName.RESPONSE, JDFLIB + "jmf.JDFResponse");
		sm_PackageNames.put(ElementName.RESUBMISSIONPARAMS, JDFLIB + "jmf.JDFResubmissionParams");
		sm_PackageNames.put(ElementName.RESUMEQUEUEENTRYPARAMS, JDFLIB + "jmf.JDFResumeQueueEntryParams");
		sm_PackageNames.put(ElementName.RETURNMETHOD, NAME_SPAN);
		sm_PackageNames.put(ElementName.RETURNQUEUEENTRYPARAMS, JDFLIB + "jmf.JDFReturnQueueEntryParams");
		sm_PackageNames.put(ElementName.RINGBINDING, JDFLIB + "resource.process.postpress.JDFRingBinding");
		sm_PackageNames.put(ElementName.RINGBINDINGPARAMS, JDFLIB + "resource.process.postpress.JDFRingBindingParams");
		sm_PackageNames.put(ElementName.RINGDIAMETER, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.RINGMECHANIC, OPTION_SPAN);
		sm_PackageNames.put(ElementName.RINGSHAPE, NAME_SPAN);
		sm_PackageNames.put(ElementName.RINGSYSTEM, NAME_SPAN);
		sm_PackageNames.put(ElementName.RIVETSEXPOSED, OPTION_SPAN);
		sm_PackageNames.put(ElementName.ROLLSTAND, JDFLIB + "resource.process.JDFRollStand");
		sm_PackageNames.put(ElementName.RULELENGTH, JDFLIB + "resource.process.JDFRuleLength");
		sm_PackageNames.put(ElementName.RUNLIST, JDFLIB + "resource.process.JDFRunList");

		sm_PackageNames.put(ElementName.SADDLESTITCHING, JDFLIB + "resource.process.postpress.JDFSaddleStitching");
		sm_PackageNames.put(ElementName.SADDLESTITCHINGPARAMS, JDFLIB + "resource.process.postpress.JDFSaddleStitchingParams");
		sm_PackageNames.put(ElementName.SCANPARAMS, JDFLIB + "resource.process.prepress.JDFScanParams");
		sm_PackageNames.put(ElementName.SCAVENGERAREA, JDFLIB + "resource.JDFScavengerArea");
		sm_PackageNames.put(ElementName.SCORE, JDFLIB + "resource.process.postpress.JDFScore");
		sm_PackageNames.put(ElementName.SCORING, JDFLIB + "span.JDFSpanScoring");
		sm_PackageNames.put(ElementName.SCREENINGPARAMS, JDFLIB + "resource.process.prepress.JDFScreeningParams");
		sm_PackageNames.put(ElementName.SCREENINGINTENT, JDFLIB + "resource.intent.JDFScreeningIntent");
		sm_PackageNames.put(ElementName.SCREENINGTYPE, JDFLIB + "span.JDFSpanScreeningType");
		sm_PackageNames.put(ElementName.SCREENSELECTOR, JDFLIB + "resource.process.JDFScreenSelector");
		sm_PackageNames.put(ElementName.SEALING, JDFLIB + "resource.process.JDFSealing");
		sm_PackageNames.put(ElementName.SEARCHPATH, JDF_COMMENT);
		sm_PackageNames.put(ElementName.SEPARATIONCONTROLPARAMS, JDFLIB + "resource.process.JDFSeparationControlParams");
		sm_PackageNames.put(ElementName.SEPARATIONLIST, SEPARATION_LIST);
		sm_PackageNames.put(ElementName.SEPARATIONSPEC, JDFLIB + "resource.process.JDFSeparationSpec");
		sm_PackageNames.put(ElementName.SERVICELEVEL, STRING_SPAN);
		sm_PackageNames.put(ElementName.SET, JDFLIB + "resource.devicecapability.JDFset");
		// "Shape" is context sensitive, see handleOtherElements() and
		// putConstructorToHashMap()
		sm_PackageNames.put(ElementName.SHAPECUT, JDFLIB + "resource.intent.JDFShapeCut");
		sm_PackageNames.put(ElementName.SHAPECUTTINGINTENT, JDFLIB + "resource.intent.JDFShapeCuttingIntent");
		sm_PackageNames.put(ElementName.SHAPECUTTINGPARAMS, JDFLIB + "resource.JDFShapeCuttingParams");
		sm_PackageNames.put(ElementName.SHAPEDEF, JDFLIB + "resource.process.JDFShapeDef");
		sm_PackageNames.put(ElementName.SHAPEDEFPRODUCTIONPARAMS, JDFLIB + "resource.process.JDFShapeDefProductionParams");
		sm_PackageNames.put(ElementName.SHAPEDEPTH, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.SHAPEEVALUATION, JDFLIB + "resource.devicecapability.JDFShapeEvaluation");
		sm_PackageNames.put(ElementName.SHAPESTATE, JDFLIB + "resource.devicecapability.JDFShapeState");
		sm_PackageNames.put(ElementName.SHAPETEMPLATE, JDFLIB + "resource.process.JDFShapeTemplate");
		sm_PackageNames.put(ElementName.SHAPETYPE, JDFLIB + "span.JDFSpanShapeType");
		sm_PackageNames.put(ElementName.SHEET, LAYOUT);
		sm_PackageNames.put(ElementName.SHEETCONDITION, JDFLIB + "resource.JDFSheetCondition");
		sm_PackageNames.put(ElementName.SHEETOPTIMIZINGPARAMS, JDFLIB + "resource.process.JDFSheetOptimizingParams");
		sm_PackageNames.put(ElementName.SHIFTPOINT, JDFLIB + "resource.process.JDFShiftPoint");
		sm_PackageNames.put(ElementName.SHRINKINGPARAMS, JDFLIB + "resource.JDFShrinkingParams");
		sm_PackageNames.put(ElementName.SHUTDOWNCMDPARAMS, JDFLIB + "jmf.JDFShutDownCmdParams");
		sm_PackageNames.put(ElementName.SIDESEWING, JDFLIB + "resource.process.postpress.JDFSideSewing");
		sm_PackageNames.put(ElementName.SIDESEWINGPARAMS, JDFLIB + "resource.process.postpress.JDFSideSewingParams");
		sm_PackageNames.put(ElementName.SIDESTITCHING, JDFLIB + "resource.process.postpress.JDFSideStitching");
		sm_PackageNames.put(ElementName.SIGNAL, JDFLIB + "jmf.JDFSignal");
		sm_PackageNames.put(ElementName.SIGNATURE, LAYOUT);
		sm_PackageNames.put(ElementName.SIGNATURECELL, JDFLIB + "resource.process.JDFSignatureCell");
		sm_PackageNames.put(ElementName.SIZEINTENT, JDFLIB + "resource.intent.JDFSizeIntent");
		sm_PackageNames.put(ElementName.SIZEPOLICY, JDFLIB + "span.JDFSpanSizePolicy");
		sm_PackageNames.put(ElementName.SOFTCOVERBINDING, JDFLIB + "resource.JDFSoftCoverBinding");
		sm_PackageNames.put(ElementName.SOURCERESOURCE, JDFLIB + "resource.process.JDFSourceResource");
		sm_PackageNames.put(ElementName.SPAWNED, JDFLIB + "node.JDFSpawned");
		sm_PackageNames.put(ElementName.SPINEBRUSHING, OPTION_SPAN);
		sm_PackageNames.put(ElementName.SPINEFIBERROUGHING, OPTION_SPAN);
		sm_PackageNames.put(ElementName.SPINEGLUE, JDFLIB + "span.JDFSpanGlue");
		sm_PackageNames.put(ElementName.SPINELEVELLING, OPTION_SPAN);
		sm_PackageNames.put(ElementName.SPINEMILLING, OPTION_SPAN);
		sm_PackageNames.put(ElementName.SPINENOTCHING, OPTION_SPAN);
		sm_PackageNames.put(ElementName.SPINEPREPARATIONPARAMS, JDFLIB + "resource.JDFSpinePreparationParams");
		sm_PackageNames.put(ElementName.SPINESANDING, OPTION_SPAN);
		sm_PackageNames.put(ElementName.SPINESHREDDING, OPTION_SPAN);
		sm_PackageNames.put(ElementName.SPINETAPINGPARAMS, JDFLIB + "resource.JDFSpineTapingParams");
		sm_PackageNames.put(ElementName.STACK, JDFLIB + "resource.process.JDFStack");
		sm_PackageNames.put(ElementName.STACKINGPARAMS, JDFLIB + "resource.JDFStackingParams");
		sm_PackageNames.put(ElementName.STATION, JDFLIB + "resource.process.JDFStation");
		sm_PackageNames.put(ElementName.STATICBLOCKINGPARAMS, JDFLIB + "resource.process.postpress.JDFStaticBlockingParams");
		sm_PackageNames.put(ElementName.STATUSPOOL, JDFLIB + "pool.JDFStatusPool");
		sm_PackageNames.put(ElementName.STATUSQUPARAMS, JDFLIB + "jmf.JDFStatusQuParams");
		sm_PackageNames.put(ElementName.STITCHINGPARAMS, JDFLIB + "resource.process.postpress.JDFStitchingParams");
		sm_PackageNames.put(ElementName.STITCHNUMBER, INTEGER_SPAN);
		sm_PackageNames.put(ElementName.STOCKBRAND, STRING_SPAN);
		sm_PackageNames.put(ElementName.STOCKTYPE, NAME_SPAN);
		sm_PackageNames.put(ElementName.STOPPERSCHPARAMS, JDFLIB + "jmf.JDFStopPersChParams");
		sm_PackageNames.put(ElementName.STRAP, JDFLIB + "resource.JDFStrap");
		sm_PackageNames.put(ElementName.STRAPPINGPARAMS, JDFLIB + "resource.JDFStrappingParams");
		sm_PackageNames.put(ElementName.STRINGEVALUATION, JDFLIB + "resource.devicecapability.JDFStringEvaluation");
		sm_PackageNames.put(ElementName.STRINGLISTVALUE, JDFLIB + "resource.process.JDFStringListValue");
		sm_PackageNames.put(ElementName.STRINGSTATE, JDFLIB + "resource.devicecapability.JDFStringState");
		sm_PackageNames.put(ElementName.STRIPBINDING, JDFLIB + "resource.JDFStripBinding");
		sm_PackageNames.put(ElementName.STRIPBINDINGPARAMS, JDFLIB + "resource.process.postpress.JDFStripBindingParams");
		sm_PackageNames.put(ElementName.STRIPCELLPARAMS, JDFLIB + "resource.process.JDFStripCellParams");
		sm_PackageNames.put(ElementName.STRIPMARK, JDFLIB + "resource.process.JDFStripMark");
		sm_PackageNames.put(ElementName.STRIPMATERIAL, JDFLIB + "span.JDFSpanStripMaterial");
		sm_PackageNames.put(ElementName.STRIPPINGPARAMS, JDFLIB + "resource.JDFStrippingParams");
		sm_PackageNames.put(ElementName.SUBMISSIONMETHODS, JDFLIB + "jmf.JDFSubmissionMethods");
		sm_PackageNames.put(ElementName.SUBSCRIPTION, JDFLIB + "jmf.JDFSubscription");
		sm_PackageNames.put(ElementName.SUBSCRIPTIONFILTER, JDFLIB + "jmf.JDFSubscriptionFilter");
		sm_PackageNames.put(ElementName.SUBSCRIPTIONINFO, JDFLIB + "jmf.JDFSubscriptionInfo");
		sm_PackageNames.put(ElementName.SURPLUSHANDLING, JDFLIB + "span.JDFSpanSurplusHandling");
		sm_PackageNames.put(ElementName.SUSPENDQUEUEENTRYPARAMS, JDFLIB + "jmf.JDFSuspendQueueEntryParams");
		sm_PackageNames.put(ElementName.SYSTEMTIMESET, JDFLIB + "resource.JDFSystemTimeSet");
		sm_PackageNames.put(ElementName.TABBINDMYLAR, OPTION_SPAN);
		sm_PackageNames.put(ElementName.TABBODYCOPY, OPTION_SPAN);
		sm_PackageNames.put(ElementName.TABBRAND, STRING_SPAN);
		sm_PackageNames.put(ElementName.TABDIMENSIONS, JDFLIB + "resource.process.JDFTabDimensions");
		sm_PackageNames.put(ElementName.TABEXTENSIONDISTANCE, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.TABEXTENSIONMYLAR, OPTION_SPAN);
		sm_PackageNames.put(ElementName.TABMYLARCOLOR, SPAN_NAMED_COLOR);
		sm_PackageNames.put(ElementName.TABMYLARCOLORDETAILS, STRING_SPAN);
		sm_PackageNames.put(ElementName.TABS, JDFLIB + "resource.JDFTabs");
		sm_PackageNames.put(ElementName.TAPE, JDFLIB + "resource.JDFTape");
		sm_PackageNames.put(ElementName.TAPEBINDING, OPTION_SPAN);
		sm_PackageNames.put(ElementName.TAPECOLOR, SPAN_NAMED_COLOR);
		sm_PackageNames.put(ElementName.TECHNOLOGY, NAME_SPAN);
		sm_PackageNames.put(ElementName.TEETHPERDIMENSION, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.TEMPERATURE, JDFLIB + "span.JDFSpanTemperature");
		sm_PackageNames.put(ElementName.TEST, JDFLIB + "resource.devicecapability.JDFTest");
		sm_PackageNames.put(ElementName.TESTPOOL, JDFLIB + "resource.devicecapability.JDFTestPool");
		sm_PackageNames.put(ElementName.TESTREF, JDFLIB + "resource.devicecapability.JDFTestRef");
		sm_PackageNames.put(ElementName.TEXTURE, NAME_SPAN);
		sm_PackageNames.put(ElementName.THICKNESS, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.THINPDFPARAMS, JDFLIB + "resource.process.JDFThinPDFParams");
		sm_PackageNames.put(ElementName.THREADSEALING, JDFLIB + "resource.process.postpress.JDFThreadSealing");
		sm_PackageNames.put(ElementName.THREADSEALINGPARAMS, JDFLIB + "resource.JDFThreadSealingParams");
		sm_PackageNames.put(ElementName.THREADSEWING, JDFLIB + "resource.process.postpress.JDFThreadSewing");
		sm_PackageNames.put(ElementName.THREADSEWINGPARAMS, JDFLIB + "resource.process.postpress.JDFThreadSewingParams");
		sm_PackageNames.put(ElementName.TIFFEMBEDDEDFILE, JDFLIB + "resource.process.JDFTIFFEmbeddedFile");
		sm_PackageNames.put(ElementName.TIFFFORMATPARAMS, JDFLIB + "resource.process.JDFTIFFFormatParams");
		sm_PackageNames.put(ElementName.TIFFTAG, JDFLIB + "resource.process.JDFTIFFtag");
		sm_PackageNames.put(ElementName.TIGHTBACKING, JDFLIB + "span.JDFSpanTightBacking");
		sm_PackageNames.put(ElementName.TILE, JDFLIB + "resource.process.JDFTile");
		sm_PackageNames.put(ElementName.TOOL, JDFLIB + "resource.JDFTool");
		sm_PackageNames.put(ElementName.TRACKFILTER, JDFLIB + "jmf.JDFTrackFilter");
		sm_PackageNames.put(ElementName.TRACKRESULT, JDFLIB + "jmf.JDFTrackResult");
		sm_PackageNames.put(ElementName.TRANSFER, JDFLIB + "span.JDFSpanTransfer");
		sm_PackageNames.put(ElementName.TRANSFERCURVE, JDFLIB + "resource.process.JDFTransferCurve");
		sm_PackageNames.put(ElementName.TRANSFERCURVEPOOL, JDFLIB + "resource.process.JDFTransferCurvePool");
		sm_PackageNames.put(ElementName.TRANSFERCURVESET, JDFLIB + "resource.process.JDFTransferCurveSet");
		sm_PackageNames.put(ElementName.TRANSFERFUNCTIONCONTROL, JDFLIB + "resource.JDFTransferFunctionControl");
		sm_PackageNames.put(ElementName.TRAPPINGDETAILS, JDFLIB + "resource.process.prepress.JDFTrappingDetails");
		sm_PackageNames.put(ElementName.TRAPPINGORDER, JDFLIB + "resource.process.prepress.JDFTrappingOrder");
		sm_PackageNames.put(ElementName.TRAPPINGPARAMS, JDFLIB + "resource.process.prepress.JDFTrappingParams");
		sm_PackageNames.put(ElementName.TRAPREGION, JDFLIB + "resource.process.JDFTrapRegion");
		sm_PackageNames.put(ElementName.TRIGGER, JDFLIB + "jmf.JDFTrigger");
		sm_PackageNames.put(ElementName.TRIMMINGPARAMS, JDFLIB + "resource.process.postpress.JDFTrimmingParams");

		sm_PackageNames.put(ElementName.UNDERAGE, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.UPDATEJDFCMDPARAMS, JDFLIB + "jmf.JDFUpdateJDFCmdParams");
		sm_PackageNames.put(ElementName.USAGECOUNTER, JDFLIB + "resource.process.JDFUsageCounter");
		sm_PackageNames.put(ElementName.USWEIGHT, NUMBER_SPAN);

		sm_PackageNames.put(ElementName.VALUE, JDFLIB + "resource.JDFValue");
		sm_PackageNames.put(ElementName.VALUELOC, JDFLIB + "resource.devicecapability.JDFValueLoc");
		sm_PackageNames.put(ElementName.VARNISHINGPARAMS, JDFLIB + "resource.JDFVarnishingParams");
		sm_PackageNames.put(ElementName.VELOBINDING, JDFLIB + "resource.process.postpress.JDFVeloBinding");
		sm_PackageNames.put(ElementName.VERIFICATIONPARAMS, JDFLIB + "resource.JDFVerificationParams");
		sm_PackageNames.put(ElementName.VIEWBINDER, NAME_SPAN);

		sm_PackageNames.put(ElementName.WAKEUPCMDPARAMS, JDFLIB + "jmf.JDFWakeUpCmdParams");
		sm_PackageNames.put(ElementName.WEBINLINEFINISHINGPARAMS, JDFLIB + "resource.process.postpress.JDFWebInlineFinishingParams");
		sm_PackageNames.put(ElementName.WEIGHT, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.WHEN, JDFLIB + "resource.devicecapability.JDFwhen");
		sm_PackageNames.put(ElementName.WINDINGPARAMS, JDFLIB + "resource.process.postpress.JDFWindingParams");
		sm_PackageNames.put(ElementName.WIRECOMBBINDING, JDFLIB + "resource.process.postpress.JDFWireCombBinding");
		sm_PackageNames.put(ElementName.WIRECOMBBINDINGPARAMS, JDFLIB + "resource.process.postpress.JDFWireCombBindingParams");
		sm_PackageNames.put(ElementName.WIRECOMBBRAND, STRING_SPAN);
		sm_PackageNames.put(ElementName.WIRECOMBMATERIAL, JDFLIB + "span.JDFSpanWireCombMaterial");
		sm_PackageNames.put(ElementName.WIRECOMBSHAPE, JDFLIB + "span.JDFSpanWireCombShape");
		sm_PackageNames.put(ElementName.WRAPPEDQUANTITY, INTEGER_SPAN);
		sm_PackageNames.put(ElementName.WRAPPINGMATERIAL, NAME_SPAN);
		sm_PackageNames.put(ElementName.WRAPPINGPARAMS, JDFLIB + "resource.JDFWrappingParams");

		sm_PackageNames.put(ElementName.XOR, JDFLIB + "resource.devicecapability.JDFxor");
		sm_PackageNames.put(ElementName.XPOSITION, NUMBER_SPAN);
		sm_PackageNames.put(ElementName.XYPAIREVALUATION, JDFLIB + "resource.devicecapability.JDFXYPairEvaluation");
		sm_PackageNames.put(ElementName.XYPAIRSTATE, JDFLIB + "resource.devicecapability.JDFXYPairState");

		sm_PackageNames.put(ElementName.YPOSITION, NUMBER_SPAN);
	}

	/**
	 *
	 */
	public DocumentData()
	{
		super();
		fillPackages();
		fillContextSensitive();
	}

	/** Caches default package name classes of files. */
	final HashMap<String, String> sm_PackageNames = new HashMap<>();
	/** Caches default package name classes of files. */
	final HashSet<String> contextSensitive = new HashSet<>();

	/** Caches Classes */
	final HashMap<String, Class<?>> sm_ClassAlreadyInstantiated = new HashMap<>();

	/** Caches JDF element constructors (namespace variant) */
	final HashMap<String, Constructor<?>> sm_hashCtorElementNS = new HashMap<>();
	/** Caches JDF element classes based on class paths to avoid heavy use of forName */
	final HashMap<String, Class<?>> sm_hashPathToClass = new HashMap<>();
}