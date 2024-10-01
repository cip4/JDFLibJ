/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 *
 * Copyright (c) 2003 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * ElementName.java
 *
 * All kind of Element names used in the JDFLib and outside of it
 *
 */
package org.cip4.jdflib.core;

/**
 *
 * list of xml element names for JDF
 *
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         July 23, 2009
 */
public final class ElementName
{
	private ElementName()
	{
		// to prohibit instantiation of this constant only class
	}

	/** */
	public static final String ABORTQUEUEENTRYPARAMS = "AbortQueueEntryParams";
	/** */
	public static final String ACCEPTED = "Accepted";
	/** */
	public static final String ACKNOWLEDGE = "Acknowledge";
	/** */
	public static final String ACTION = "Action";
	/** */
	public static final String ACTIONPOOL = "ActionPool";
	/** */
	public static final String ACTIVITY = "Activity";
	/** */
	public static final String ADDED = "Added";
	/** */
	public static final String ADDRESS = "Address";
	/** */
	public static final String ADHESIVEBINDING = "AdhesiveBinding";
	/** */
	public static final String ADHESIVEBINDINGPARAMS = "AdhesiveBindingParams";
	/** */
	public static final String ADVANCEDPARAMS = "AdvancedParams";
	/** */
	public static final String AMOUNT = "Amount";
	/** */
	public static final String AMOUNTPOOL = "AmountPool";
	/** */
	public static final String ANCESTOR = "Ancestor";
	/** */
	public static final String ANCESTORPOOL = "AncestorPool";
	/** */
	public static final String AND = "and";
	/** */
	public static final String APPROVAL = "Approval";
	/** */
	public static final String APPROVALDETAILS = "ApprovalDetails";
	/** */
	public static final String APPROVALPARAMS = "ApprovalParams";
	/** */
	public static final String APPROVALPERSON = "ApprovalPerson";
	/** */
	public static final String APPROVALSUCCESS = "ApprovalSuccess";
	/** */
	public static final String ARGUMENTVALUE = "ArgumentValue";
	/** */
	public static final String ARTDELIVERY = "ArtDelivery";
	/** */
	public static final String ARTDELIVERYDATE = "ArtDeliveryDate";
	/** */
	public static final String ARTDELIVERYDURATION = "ArtDeliveryDuration";
	/** */
	public static final String ARTDELIVERYINTENT = "ArtDeliveryIntent";
	/** */
	public static final String ARTDELIVERYTYPE = "ArtDeliveryType";
	/** */
	public static final String ARTHANDLING = "ArtHandling";
	/** */
	public static final String ASSEMBLY = "Assembly";
	/** */
	public static final String ASSEMBLYSECTION = "AssemblySection";
	/** */
	public static final String ASSETCOLLECTION = "AssetCollection";
	/** */
	public static final String ASSETLISTCREATION = "AssetListCreation";
	/** */
	public static final String ASSETLISTCREATIONPARAMS = "AssetListCreationParams";
	/** */
	public static final String AUDIT = "Audit";
	/** */
	public static final String AUDITPOOL = "AuditPool";
	/** */
	public static final String AUTHENTICATIONCMDPARAMS = "AuthenticationCmdParams";
	/** */
	public static final String AUTHENTICATIONQUPARAMS = "AuthenticationQuParams";
	/** */
	public static final String AUTHENTICATIONRESP = "AuthenticationResp";
	/** */
	public static final String AUTOMATEDOVERPRINTPARAMS = "AutomatedOverPrintParams";

	/** */
	public static final String BACKCOATINGS = "BackCoatings";
	/** */
	public static final String BACKCOVERCOLOR = "BackCoverColor";
	/** */
	public static final String BACKCOVERCOLORDETAILS = "BackCoverColorDetails";
	/** */
	public static final String BAND = "Band";
	/** */
	public static final String BARCODE = "Barcode";
	/** */
	public static final String BARCODECOMPPARAMS = "BarcodeCompParams";
	/** */
	public static final String BARCODEDETAILS = "BarcodeDetails";
	/** */
	public static final String BARCODEPRODUCTIONPARAMS = "BarcodeProductionParams";
	/** */
	public static final String BARCODEREPROPARAMS = "BarcodeReproParams";
	/** */
	public static final String BASICPREFLIGHTTEST = "BasicPreflightTest";
	/** */
	public static final String BENDINGPARAMS = "BendingParams";
	/** */
	public static final String BINDERBRAND = "BinderBrand";
	/** */
	public static final String BINDERMATERIAL = "BinderMaterial";
	/** */
	public static final String BINDERYSIGNATURE = "BinderySignature";
	/** */
	public static final String BINDINGCOLOR = "BindingColor";
	/** */
	public static final String BINDINGCOLORDETAILS = "BindingColorDetails";
	/** */
	public static final String BINDINGINTENT = "BindingIntent";
	/** */
	public static final String BINDINGLENGTH = "BindingLength";
	/** */
	public static final String BINDINGQUALITYMEASUREMENT = "BindingQualityMeasurement";
	/** */
	public static final String BINDINGQUALITYPARAMS = "BindingQualityParams";
	/** */
	public static final String BINDINGSIDE = "BindingSide";
	/** */
	public static final String BINDINGTYPE = "BindingType";
	/** */
	public static final String BINDITEM = "BindItem";
	/** */
	public static final String BINDLIST = "BindList";
	/** */
	public static final String BLEED = "Bleed";
	/** */
	public static final String BLOCKPREPARATION = "BlockPreparation";
	/** */
	public static final String BLOCKPREPARATIONPARAMS = "BlockPreparationParams";
	/** */
	public static final String BLOCKTHREADSEWING = "BlockThreadSewing";
	/** */
	public static final String BOOKCASE = "BookCase";
	/** */
	public static final String BOOLEANEVALUATION = "BooleanEvaluation";
	/** */
	public static final String BOOLEANSTATE = "BooleanState";
	/** */
	public static final String BOXAPPLICATION = "BoxApplication";
	/** */
	public static final String BOXARGUMENT = "BoxArgument";
	/** */
	public static final String BOXEDQUANTITY = "BoxedQuantity";
	/** */
	public static final String BOXFOLDACTION = "BoxFoldAction";
	/** */
	public static final String BOXFOLDINGPARAMS = "BoxFoldingParams";
	/** */
	public static final String BOXPACKING = "BoxPacking";
	/** */
	public static final String BOXPACKINGPARAMS = "BoxPackingParams";
	/** */
	public static final String BOXSHAPE = "BoxShape";
	/** */
	public static final String BOXTOBOXDIFFERENCE = "BoxToBoxDifference";
	/** */
	public static final String BRANDNAME = "BrandName";
	/** */
	public static final String BRIGHTNESS = "Brightness";
	/** */
	public static final String BUFFER = "Buffer";
	/** */
	public static final String BUFFERPARAMS = "BufferParams";
	/** */
	public static final String BUNDLE = "Bundle";
	/** */
	public static final String BUNDLEITEM = "BundleItem";
	/** */
	public static final String BUNDLING = "Bundling";
	/** */
	public static final String BUNDLINGPARAMS = "BundlingParams";
	/** */
	public static final String BUSINESSINFO = "BusinessInfo";
	/** */
	public static final String BUYERSUPPLIED = "BuyerSupplied";
	/** */
	public static final String BYTEMAP = "ByteMap";

	/** */
	public static final String CALL = "call";
	/** */
	public static final String CARTONMAXWEIGHT = "CartonMaxWeight";
	/** */
	public static final String CARTONQUANTITY = "CartonQuantity";
	/** */
	public static final String CARTONSHAPE = "CartonShape";
	/** */
	public static final String CARTONSTRENGTH = "CartonStrength";
	/** */
	public static final String CASEMAKING = "CaseMaking";
	/** */
	public static final String CASEMAKINGPARAMS = "CaseMakingParams";
	/** */
	public static final String CASINGIN = "CasingIn";
	/** */
	public static final String CASINGINPARAMS = "CasingInParams";
	/** */
	public static final String CCITTFAXPARAMS = "CCITTFaxParams";
	/** */
	public static final String CERTIFICATE = "Certificate";
	public static final String CERTIFICATION = "Certification";
	/** */
	public static final String CHANGEDATTRIBUTE = "ChangedAttribute";
	/** */
	public static final String CHANGEDPATH = "ChangedPath";
	/** */
	public static final String CHANNELBINDING = "ChannelBinding";
	/** */
	public static final String CHANNELBINDINGPARAMS = "ChannelBindingParams";
	/** */
	public static final String CHANNELBRAND = "ChannelBrand";
	/** */
	public static final String CHOICE = "choice";
	/** */
	public static final String CIELABMEASURINGFIELD = "CIELABMeasuringField";
	/** */
	public static final String CIRCULATION = "Circulation";
	/** */
	public static final String COATINGS = "Coatings";
	/** */
	public static final String COILBINDING = "CoilBinding";
	/** */
	public static final String COILBINDINGPARAMS = "CoilBindingParams";
	/** */
	public static final String COILBRAND = "CoilBrand";
	/** */
	public static final String COILMATERIAL = "CoilMaterial";
	/** */
	public static final String COLLATINGITEM = "CollatingItem";
	/** */
	public static final String COLLECTING = "Collecting";
	/** */
	public static final String COLLECTINGPARAMS = "CollectingParams";
	/** */
	public static final String COLOR = "Color";
	/** */
	public static final String COLORANTALIAS = "ColorantAlias";
	/** */
	public static final String COLORANTCONTROL = "ColorantControl";
	/** */
	public static final String COLORANTCONVERTPROCESS = "ColorantConvertProcess";
	/** */
	public static final String COLORANTORDER = "ColorantOrder";
	/** */
	public static final String COLORANTPARAMS = "ColorantParams";
	/** */
	public static final String COLORANTZONEDETAILS = "ColorantZoneDetails";
	/** */
	public static final String COLORCONTROLSTRIP = "ColorControlStrip";
	/** */
	public static final String COLORCORRECTION = "ColorCorrection";
	/** */
	public static final String COLORCORRECTIONOP = "ColorCorrectionOp";
	/** */
	public static final String COLORCORRECTIONPARAMS = "ColorCorrectionParams";
	/** */
	public static final String COLORICCSTANDARD = "ColorICCStandard";
	/** */
	public static final String COLORINTENT = "ColorIntent";
	public static final String COLORMEASUREMENT = "ColorMeasurement";
	public static final String COLORMEASUREMENTCONDITIONS = "ColorMeasurementConditions";
	public static final String COLORNAME = "ColorName";
	public static final String COLORNAMEDETAILS = "ColorNameDetails";
	public static final String COLORPOOL = "ColorPool";
	public static final String COLORSCONSTRAINTSPOOL = "ColorsConstraintsPool";
	/** @deprecated COLORSPACECONVERSION */
	@Deprecated
	/** */
	public static final String COLORSPACECONVERSION = "ColorSpaceConversion";
	/** */
	public static final String COLORSPACECONVERSIONOP = "ColorSpaceConversionOp";
	/** */
	public static final String COLORSPACECONVERSIONPARAMS = "ColorSpaceConversionParams";
	/** */
	public static final String COLORSPACESUBSTITUTE = "ColorSpaceSubstitute";
	/** */
	public static final String COLORSRESULTSPOOL = "ColorsResultsPool";
	/** */
	public static final String COLORSTANDARD = "ColorStandard";
	/** */
	public static final String COLORSUSED = "ColorsUsed";
	/** */
	public static final String COLORTYPE = "ColorType";
	/** */
	public static final String COMBBRAND = "CombBrand";
	/** @deprecated use COMBINE EnumType.xxx.getName() */
	@Deprecated
	/** */
	public static final String COMBINE = "Combine";
	/** @deprecated COMBINED use EnumType.xxx.getName() */
	@Deprecated
	/** */
	public static final String COMBINED = "Combined";
	/** */
	public static final String COMCHANNEL = "ComChannel";
	/** */
	public static final String COMMAND = "Command";
	/** */
	public static final String COMMENT = "Comment";
	/** */
	public static final String COMPANY = "Company";
	/** */
	public static final String COMPONENT = "Component";
	/** */
	public static final String CONSTRAINTVALUE = "ConstraintValue";
	/** */
	public static final String CONTACT = "Contact";
	/** @deprecated CONTACTCOPYING use EnumType.xxx.getName() */
	@Deprecated
	/** */
	public static final String CONTACTCOPYING = "ContactCopying";
	/** */
	public static final String CONTACTCOPYPARAMS = "ContactCopyParams";
	/** */
	public static final String CONTAINER = "Container";
	/** */
	public static final String CONTENTDATA = "ContentData";
	/** */
	public static final String CONTENTLIST = "ContentList";
	/** */
	public static final String CONTENTMETADATA = "ContentMetadata";
	/** */
	public static final String CONTENTOBJECT = "ContentObject";
	/** */
	public static final String CONTONECALIBRATION = "ContoneCalibration";
	/** */
	public static final String CONTROLLERFILTER = "ControllerFilter";
	/** @deprecated CONVENTIONALPRINTING */
	@Deprecated
	/** */
	public static final String CONVENTIONALPRINTING = "ConventionalPrinting";
	/** */
	public static final String CONVENTIONALPRINTINGPARAMS = "ConventionalPrintingParams";
	/** */
	public static final String CONVERTINGCONFIG = "ConvertingConfig";
	/** */
	public static final String COSTCENTER = "CostCenter";
	/** */
	public static final String COUNTERRESET = "CounterReset";
	/** */
	public static final String COVER = "Cover";
	/** */
	public static final String COVERAGE = "Coverage";
	/** @deprecated COVERAPPLICATION */
	@Deprecated
	/** */
	public static final String COVERAPPLICATION = "CoverApplication";
	/** */
	public static final String COVERAPPLICATIONPARAMS = "CoverApplicationParams";
	/** */
	public static final String COVERCOLOR = "CoverColor";
	/** */
	public static final String COVERCOLORDETAILS = "CoverColorDetails";
	/** */
	public static final String COVERSTYLE = "CoverStyle";
	/** */
	public static final String CREASE = "Crease";
	/** */
	public static final String CREASING = "Creasing";
	/** */
	public static final String CREASINGPARAMS = "CreasingParams";
	/** */
	public static final String CREATED = "Created";
	/** */
	public static final String CREATELINK = "CreateLink";
	/** */
	public static final String CREATERESOURCE = "CreateResource";
	/** */
	public static final String CREDITCARD = "CreditCard";
	/** */
	public static final String CUSTOMERINFO = "CustomerInfo";
	/** */
	public static final String CUSTOMERMESSAGE = "CustomerMessage";
	/** */
	public static final String CUT = "Cut";
	/** */
	public static final String CUTBLOCK = "CutBlock";
	/** */
	public static final String CUTLINES = "CutLines";
	/** */
	public static final String CUTMARK = "CutMark";
	/** */
	public static final String CUTTING = "Cutting";
	/** */
	public static final String CUTTINGPARAMS = "CuttingParams";
	/** */
	public static final String CUTTYPE = "CutType";
	/** */
	public static final String CYLINDERLAYOUT = "CylinderLayout";
	/** */
	public static final String CYLINDERLAYOUTPREPARATIONPARAMS = "CylinderLayoutPreparationParams";
	/** */
	public static final String CYLINDERPOSITION = "CylinderPosition";

	/** */
	public static final String DATETIMEEVALUATION = "DateTimeEvaluation";
	/** */
	public static final String DATETIMESTATE = "DateTimeState";
	/** */
	public static final String DBDOCTEMPLATELAYOUT = "DBDocTemplateLayout";
	/** */
	public static final String DBMERGEPARAMS = "DBMergeParams";
	/** */
	public static final String DBRULES = "DBRules";
	/** */
	public static final String DBSCHEMA = "DBSchema";
	/** */
	public static final String DBSELECTION = "DBSelection";
	/** */
	public static final String DBTEMPLATEMERGING = "DBTemplateMerging";
	/** */
	public static final String DCTPARAMS = "DCTParams";
	public static final String DEFECT = "Defect";
	public static final String DELETED = "Deleted";

	public static final String DELIVERY = "Delivery";
	public static final String DELIVERYCHARGE = "DeliveryCharge";
	public static final String DELIVERYINTENT = "DeliveryIntent";
	public static final String DELIVERYPARAMS = "DeliveryParams";

	public static final String DENSITYMEASURINGFIELD = "DensityMeasuringField";
	/** */
	public static final String DEPENDENCIES = "Dependencies";
	/** */
	public static final String DEVCAP = "DevCap";
	/** */
	public static final String DEVCAPPOOL = "DevCapPool";
	/** */
	public static final String DEVCAPS = "DevCaps";
	/** */
	public static final String DEVELOPINGPARAMS = "DevelopingParams";
	/** */
	public static final String DEVICE = "Device";
	/** */
	public static final String DEVICECAP = "DeviceCap";
	/** */
	public static final String DEVICECOLORANTORDER = "DeviceColorantOrder";
	/** */
	public static final String DEVICEFILTER = "DeviceFilter";
	/** */
	public static final String DEVICEINFO = "DeviceInfo";
	/** */
	public static final String DEVICELIST = "DeviceList";
	/** */
	public static final String DEVICEMARK = "DeviceMark";
	/** */
	public static final String DEVICENCOLOR = "DeviceNColor";
	/** */
	public static final String DEVICENSPACE = "DeviceNSpace";
	/** */
	public static final String DIELAYOUT = "DieLayout";
	/** */
	public static final String DIELAYOUTPRODUCTIONPARAMS = "DieLayoutProductionParams";
	/**
	 * @deprecated DIGITALDELIVERY use EnumType.Delivery.getName();
	 */
	@Deprecated
	/** */
	public static final String DIGITALDELIVERY = "DigitalDelivery";
	/** */
	public static final String DIGITALDELIVERYPARAMS = "DigitalDeliveryParams";
	/** */
	public static final String DIGITALMEDIA = "DigitalMedia";
	/**
	 * @deprecated DIGITALPRINTING use EnumType.DigitalPrinting.getName();
	 */
	@Deprecated
	/** */
	public static final String DIGITALPRINTING = "DigitalPrinting";
	/** */
	public static final String DIGITALPRINTINGPARAMS = "DigitalPrintingParams";
	/** */
	public static final String DIMENSIONS = "Dimensions";
	/** */
	public static final String DIRECTION = "Direction";
	/** */
	public static final String DISJOINTING = "Disjointing";
	/** */
	public static final String DISPLAYGROUP = "DisplayGroup";
	/** */
	public static final String DISPLAYGROUPPOOL = "DisplayGroupPool";
	/** */
	public static final String DISPOSITION = "Disposition";
	/** */
	public static final String DIVIDING = "Dividing";
	/** */
	public static final String DIVIDINGPARAMS = "DividingParams";
	/** */
	public static final String DOCUMENTCONSTRAINTSPOOL = "DocumentConstraintsPool";
	/** */
	public static final String DOCUMENTRESULTSPOOL = "DocumentResultsPool";
	/** */
	public static final String DOTSIZE = "DotSize";
	/** */
	public static final String DROP = "Drop";
	/** */
	public static final String DROPINTENT = "DropIntent";
	/** */
	public static final String DROPITEM = "DropItem";
	/** */
	public static final String DROPITEMINTENT = "DropItemIntent";
	/** */
	public static final String DURATIONEVALUATION = "DurationEvaluation";
	/** */
	public static final String DURATIONSTATE = "DurationState";
	/** */
	public static final String DYNAMICFIELD = "DynamicField";
	/** */
	public static final String DYNAMICINPUT = "DynamicInput";

	/** */
	public static final String EARLIEST = "Earliest";
	/** */
	public static final String EARLIESTDURATION = "EarliestDuration";
	/** */
	public static final String EDGEANGLE = "EdgeAngle";
	/** */
	public static final String EDGEGLUE = "EdgeGlue";
	/** */
	public static final String EDGEGLUING = "EdgeGluing";
	/** */
	public static final String EDGESHAPE = "EdgeShape";
	/** */
	public static final String ELEMENTCOLORPARAMS = "ElementColorParams";
	/** */
	public static final String EMBOSS = "Emboss";
	/** */
	public static final String EMBOSSING = "Embossing";
	/** */
	public static final String EMBOSSINGINTENT = "EmbossingIntent";
	/** */
	public static final String EMBOSSINGITEM = "EmbossingItem";
	/** */
	public static final String EMBOSSINGPARAMS = "EmbossingParams";
	/** */
	public static final String EMBOSSINGTYPE = "EmbossingType";
	/** */
	public static final String EMPLOYEE = "Employee";
	/** */
	public static final String EMPLOYEEDEF = "EmployeeDef";
	/** */
	public static final String ENDSHEET = "EndSheet";
	/** */
	public static final String ENDSHEETGLUING = "EndSheetGluing";
	/** */
	public static final String ENDSHEETGLUINGPARAMS = "EndSheetGluingParams";
	/** */
	public static final String ENDSHEETS = "EndSheets";
	/** */
	public static final String ENUMERATIONEVALUATION = "EnumerationEvaluation";
	/** */
	public static final String ENUMERATIONSTATE = "EnumerationState";
	/** */
	public static final String ERROR = "Error";
	/** */
	public static final String ERRORDATA = "ErrorData";
	/** */
	public static final String EVENT = "Event";
	/** */
	public static final String EXPOSEDMEDIA = "ExposedMedia";
	/** */
	public static final String EXPR = "Expr";
	/** */
	public static final String EXTENDEDADDRESS = "ExtendedAddress";
	/** */
	public static final String EXTERNALIMPOSITIONTEMPLATE = "ExternalImpositionTemplate";
	/** */
	public static final String EXTRAVALUES = "ExtraValues";

	/** */
	public static final String FCNKEY = "FCNKey";
	/** */
	public static final String FEATUREATTRIBUTE = "FeatureAttribute";
	/** */
	public static final String FEATUREPOOL = "FeaturePool";
	/** */
	public static final String FEEDER = "Feeder";
	/** */
	public static final String FEEDERQUALITYPARAMS = "FeederQualityParams";
	/** */
	public static final String FEEDING = "Feeding";
	/** */
	public static final String FEEDINGPARAMS = "FeedingParams";
	/** */
	public static final String FILEALIAS = "FileAlias";
	/** */
	public static final String FILESPEC = "FileSpec";
	/** */
	public static final String FILESPECOUT = "FileSpecOut";
	/** */
	public static final String FILETYPECONSTRAINTSPOOL = "FileTypeConstraintsPool";
	/** */
	public static final String FILETYPERESULTSPOOL = "FileTypeResultsPool";
	/** */
	public static final String FILLCOLOR = "FillColor";

	/** */
	public static final String FILLMARK = "FillMark";

	/** */
	public static final String FILMTOPLATECOPYING = "FilmToPlateCopying";
	/** */
	public static final String FINISHEDDIMENSIONS = "FinishedDimensions";
	/** */
	public static final String FINISHEDGRAINDIRECTION = "FinishedGrainDirection";
	/** */
	public static final String FITPOLICY = "FitPolicy";
	/** */
	public static final String FLATEPARAMS = "FlateParams";
	/** */
	public static final String FLUSHEDRESOURCES = "FlushedResources";
	/** */
	public static final String FLUSHQUEUEINFO = "FlushQueueInfo";
	/** */
	public static final String FLUSHQUEUEPARAMS = "FlushQueueParams";
	/** */
	public static final String FLUSHRESOURCEPARAMS = "FlushResourceParams";
	/** */
	public static final String FLUTE = "Flute";
	/** */
	public static final String FLUTEDIRECTION = "FluteDirection";
	/** */
	public static final String FOILCOLOR = "FoilColor";
	/** */
	public static final String FOILCOLORDETAILS = "FoilColorDetails";
	/** */
	public static final String FOLD = "Fold";
	/** */
	public static final String FOLDERPRODUCTION = "FolderProduction";
	/** */
	public static final String FOLDERSUPERSTRUCTUREWEBPATH = "FolderSuperStructureWebPath";
	/** */
	public static final String FOLDING = "Folding";
	/** */
	public static final String FOLDINGCATALOG = "FoldingCatalog";
	/** */
	public static final String FOLDINGINTENT = "FoldingIntent";
	/** */
	public static final String FOLDINGPARAMS = "FoldingParams";
	/** */
	public static final String FOLDINGWIDTH = "FoldingWidth";
	/** */
	public static final String FOLDINGWIDTHBACK = "FoldingWidthBack";
	/** */
	public static final String FOLDOPERATION = "FoldOperation";
	/** */
	public static final String FONTPARAMS = "FontParams";
	/** */
	public static final String FONTPOLICY = "FontPolicy";
	/** */
	public static final String FONTSCONSTRAINTSPOOL = "FontsConstraintsPool";
	/** */
	public static final String FONTSRESULTSPOOL = "FontsResultsPool";
	/** */
	public static final String FORMATCONVERSION = "FormatConversion";
	/** */
	public static final String FORMATCONVERSIONPARAMS = "FormatConversionParams";
	/** */
	public static final String FREQUENCY = "Frequency";
	/** */
	public static final String FREQUENCYSELECTION = "FrequencySelection";
	/** */
	public static final String FRONTCOATINGS = "FrontCoatings";

	/** */
	public static final String GANGCMDFILTER = "GangCmdFilter";
	public static final String GANGELEMENT = "GangElement";
	public static final String GANGINFO = "GangInfo";
	public static final String GANGPREPARATIONPARAMS = "GangPreparationParams";
	public static final String GANGQUFILTER = "GangQuFilter";
	public static final String GANGSOURCE = "GangSource";
	/** */
	public static final String GATHERING = "Gathering";
	/** */
	public static final String GATHERINGPARAMS = "GatheringParams";
	/** */
	public static final String GENERALID = "GeneralID";
	/** */
	public static final String GLUE = "Glue";
	/** */
	public static final String GLUEAPPLICATION = "GlueApplication";
	/** */
	public static final String GLUELINE = "GlueLine";
	/** */
	public static final String GLUEPROCEDURE = "GlueProcedure";
	/** */
	public static final String GLUETYPE = "GlueType";
	/** */
	public static final String GLUING = "Gluing";
	/** */
	public static final String GLUINGPARAMS = "GluingParams";
	/** */
	public static final String GRADE = "Grade";
	/** */
	public static final String GRAINDIRECTION = "GrainDirection";

	/** */
	public static final String HALFTONE = "HalfTone";
	/** */
	public static final String HARDCOVERBINDING = "HardCoverBinding";
	/** */
	public static final String HEADBANDAPPLICATION = "HeadBandApplication";
	/** */
	public static final String HEADBANDAPPLICATIONPARAMS = "HeadBandApplicationParams";
	/** */
	public static final String HEADBANDCOLOR = "HeadBandColor";
	/** */
	public static final String HEADBANDCOLORDETAILS = "HeadBandColorDetails";
	/** */
	public static final String HEADBANDS = "HeadBands";
	/** */
	public static final String HEIGHT = "Height";
	/** */
	public static final String HOLDQUEUEENTRYPARAMS = "HoldQueueEntryParams";
	/** */
	public static final String HOLE = "Hole";
	/** */
	public static final String HOLECOUNT = "HoleCount";
	/** */
	public static final String HOLELINE = "HoleLine";
	/** */
	public static final String HOLELIST = "HoleList";
	/** */
	public static final String HOLEMAKING = "HoleMaking";
	/** */
	public static final String HOLEMAKINGINTENT = "HoleMakingIntent";
	/** */
	public static final String HOLEMAKINGPARAMS = "HoleMakingParams";
	/** */
	public static final String HOLETYPE = "HoleType";

	/** */
	public static final String ICON = "Icon";
	/** */
	public static final String ICONLIST = "IconList";
	/** */
	public static final String IDENTICAL = "Identical";
	/** */
	public static final String IDENTIFICATIONFIELD = "IdentificationField";
	/** */
	public static final String IDINFO = "IDInfo";
	/** */
	public static final String IDPCOVER = "IDPCover";
	/** */
	public static final String IDPFINISHING = "IDPFinishing";
	/** */
	public static final String IDPFOLDING = "IDPFolding";
	/** */
	public static final String IDPHOLEMAKING = "IDPHoleMaking";
	/** */
	public static final String IDPIMAGESHIFT = "IDPImageShift";
	/** */
	public static final String IDPJOBSHEET = "IDPJobSheet";
	/** */
	public static final String IDPLAYOUT = "IDPLayout";
	/** */
	public static final String IDPRINTING = "IDPrinting";
	/** */
	public static final String IDPRINTINGPARAMS = "IDPrintingParams";
	/** */
	public static final String IDPSTITCHING = "IDPStitching";
	/** */
	public static final String IDPTRIMMING = "IDPTrimming";
	/** */
	public static final String IMAGECOMPRESSION = "ImageCompression";
	/** */
	public static final String IMAGECOMPRESSIONPARAMS = "ImageCompressionParams";
	/** */
	public static final String IMAGEENHANCEMENTOP = "ImageEnhancementOp";
	/** */
	public static final String IMAGEENHANCEMENTPARAMS = "ImageEnhancementParams";
	/** */
	public static final String IMAGEREPLACEMENT = "ImageReplacement";
	/** */
	public static final String IMAGEREPLACEMENTPARAMS = "ImageReplacementParams";
	/** */
	public static final String IMAGESCONSTRAINTSPOOL = "ImagesConstraintsPool";
	/** */
	public static final String IMAGESETTERPARAMS = "ImageSetterParams";
	/** */
	public static final String IMAGESETTING = "ImageSetting";
	/** */
	public static final String IMAGESHIFT = "ImageShift";
	/** */
	public static final String IMAGESIZE = "ImageSize";
	/** */
	public static final String IMAGESRESULTSPOOL = "ImagesResultsPool";
	/** */
	public static final String IMAGESTRATEGY = "ImageStrategy";
	/** */
	public static final String IMPOSITION = "Imposition";
	/** */
	public static final String INK = "Ink";
	/** */
	public static final String INKMANUFACTURER = "InkManufacturer";
	/** */
	public static final String INKZONECALCULATION = "InkZoneCalculation";
	/** */
	public static final String INKZONECALCULATIONPARAMS = "InkZoneCalculationParams";
	/** */
	public static final String INKZONEPROFILE = "InkZoneProfile";
	/** */
	public static final String INSERT = "Insert";
	/** */
	public static final String INSERTING = "Inserting";
	/** */
	public static final String INSERTINGINTENT = "InsertingIntent";
	/** */
	public static final String INSERTINGPARAMS = "InsertingParams";
	/** */
	public static final String INSERTLIST = "InsertList";
	/** */
	public static final String INSERTSHEET = "InsertSheet";
	/** */
	public static final String INTEGEREVALUATION = "IntegerEvaluation";
	/** */
	public static final String INTEGERSTATE = "IntegerState";
	/** */
	public static final String INTENTRESOURCE = "IntentResource";
	/** */
	public static final String INTERPRETEDPDLDATA = "InterpretedPDLData";
	/** */
	public static final String INTERPRETING = "Interpreting";
	/** */
	public static final String INTERPRETINGDETAILS = "InterpretingDetails";
	/** */
	public static final String INTERPRETINGPARAMS = "InterpretingParams";
	public static final String INSPECTION = "Inspection";
	public static final String ISOPAPERSUBSTRATE = "ISOPaperSubstrate";
	/** */
	public static final String ISPRESENTEVALUATION = "IsPresentEvaluation";
	/** */
	public static final String ISSUEDATE = "IssueDate";
	/** */
	public static final String ISSUENAME = "IssueName";
	/** */
	public static final String ISSUETYPE = "IssueType";

	/** */
	public static final String JACKET = "Jacket";
	/** */
	public static final String JACKETFOLDINGWIDTH = "JacketFoldingWidth";
	/** */
	public static final String JACKETING = "Jacketing";
	/** */
	public static final String JACKETINGPARAMS = "JacketingParams";
	/** */
	public static final String JAPANBIND = "JapanBind";
	/** */
	public static final String JBIG2PARAMS = "JBIG2Params";
	/** */
	public static final String JDF = "JDF";
	/** */
	public static final String JDFCONTROLLER = "JDFController";
	/** */
	public static final String JDFSERVICE = "JDFService";
	/** */
	@Deprecated
	public static final String JDFSJDFCONTROLLERERVICE = JDFSERVICE;
	/** */
	public static final String JMF = "JMF";
	/** */
	public static final String JOBFIELD = "JobField";
	/** */
	public static final String JOBPHASE = "JobPhase";
	/** */
	public static final String JOBSHEET = "JobSheet";
	/** */
	public static final String JPEG2000PARAMS = "JPEG2000Params";

	/** */
	public static final String KNOWNMSGQUPARAMS = "KnownMsgQuParams";

	/** */
	public static final String LABELING = "Labeling";
	/** */
	public static final String LABELINGPARAMS = "LabelingParams";
	/** */
	public static final String LAMINATED = "Laminated";
	/** */
	public static final String LAMINATING = "Laminating";
	/** */
	public static final String LAMINATINGINTENT = "LaminatingIntent";
	/** */
	public static final String LAMINATINGPARAMS = "LaminatingParams";
	/** */
	public static final String LAYERDETAILS = "LayerDetails";
	/** */
	public static final String LAYERLIST = "LayerList";
	/** */
	public static final String LAYOUT = "Layout";
	/** */
	public static final String LAYOUTELEMENT = "LayoutElement";
	/** */
	public static final String LAYOUTELEMENTPART = "LayoutElementPart";
	/** */
	public static final String LAYOUTELEMENTPRODUCTION = "LayoutElementProduction";
	/** */
	public static final String LAYOUTELEMENTPRODUCTIONPARAMS = "LayoutElementProductionParams";
	/** */
	public static final String LAYOUTINTENT = "LayoutIntent";
	/** */
	public static final String LAYOUTPREPARATION = "LayoutPreparation";
	/** */
	public static final String LAYOUTPREPARATIONPARAMS = "LayoutPreparationParams";
	/** */
	public static final String LAYOUTSHIFT = "LayoutShift";
	/** */
	public static final String LEVEL = "Level";
	/** */
	public static final String LOC = "Loc";
	/** */
	public static final String LOCATION = "Location";
	/** */
	public static final String LOGICALSTACKPARAMS = "LogicalStackParams";
	/** */
	public static final String LONGFOLD = "LongFold";
	/** */
	public static final String LONGGLUE = "LongGlue";
	/** */
	public static final String LONGITUDINALRIBBONOPERATIONPARAMS = "LongitudinalRibbonOperationParams";
	/** */
	public static final String LONGITUDINALRIBBONOPERATIONS = "LongitudinalRibbonOperations";
	/** */
	public static final String LONGPERFORATE = "LongPerforate";
	/** */
	public static final String LONGSLIT = "LongSlit";
	/** */
	public static final String LOT = "Lot";
	/** */
	public static final String LZWPARAMS = "LZWParams";

	/** */
	public static final String MACRO = "macro";
	/** */
	public static final String MACROPOOL = "MacroPool";
	/** */
	public static final String MANUALLABOR = "ManualLabor";
	/** */
	public static final String MANUALLABORPARAMS = "ManualLaborParams";
	/** */
	public static final String MARKACTIVATION = "MarkActivation";
	public static final String MARKCOLOR = "MarkColor";
	/** */
	public static final String MARKOBJECT = "MarkObject";
	/** */
	public static final String MATERIAL = "Material";
	/** */
	public static final String MATRIXEVALUATION = "MatrixEvaluation";
	/** */
	public static final String MATRIXSTATE = "MatrixState";
	/** */
	public static final String MEDIA = "Media";
	/** */
	public static final String MEDIACOLOR = "MediaColor";
	/** */
	public static final String MEDIACOLORDETAILS = "MediaColorDetails";
	/** */
	public static final String MEDIAINTENT = "MediaIntent";
	/** */
	public static final String MEDIALAYERS = "MediaLayers";
	/** */
	public static final String MEDIAQUALITY = "MediaQuality";
	/** */
	public static final String MEDIASOURCE = "MediaSource";
	/** */
	public static final String MEDIATYPE = "MediaType";
	/** */
	public static final String MEDIATYPEDETAILS = "MediaTypeDetails";
	/** */
	public static final String MEDIAUNIT = "MediaUnit";
	/** */
	public static final String MERGED = "Merged";
	/** */
	public static final String MESSAGE = "Message";
	/** */
	public static final String MESSAGESERVICE = "MessageService";
	/** */
	public static final String METADATAMAP = "MetadataMap";
	/** */
	public static final String METHOD = "Method";
	/** */
	public static final String MILESTONE = "Milestone";
	/** */
	public static final String MISCCONSUMABLE = "MiscConsumable";
	/** */
	public static final String MISDETAILS = "MISDetails";
	/** */
	public static final String MODIFIED = "Modified";
	/** */
	public static final String MODIFYNODECMDPARAMS = "ModifyNodeCmdParams";
	/** */
	public static final String MODULE = "Module";
	/** */
	public static final String MODULECAP = "ModuleCap";

	public static final String MODULEINFO = "ModuleInfo";
	public static final String MODULEPHASE = "ModulePhase";
	/** */
	public static final String MODULEPOOL = "ModulePool";
	/** */
	public static final String MODULESTATUS = "ModuleStatus";
	/** */
	public static final String MOVERESOURCE = "MoveResource";
	/** */
	public static final String MSGFILTER = "MsgFilter";

	/** */
	public static final String NAMEEVALUATION = "NameEvaluation";
	/** */
	public static final String NAMESTATE = "NameState";
	/** */
	public static final String NEWCOMMENT = "NewComment";
	public static final String NETWORKHEADER = "NetworkHeader";
	/** */
	public static final String NEWJDFCMDPARAMS = "NewJDFCmdParams";
	/** */
	public static final String NEWJDFQUPARAMS = "NewJDFQuParams";
	/** */
	public static final String NODEINFO = "NodeInfo";
	/** */
	public static final String NODEINFOCMDPARAMS = "NodeInfoCmdParams";
	/** */
	public static final String NODEINFOQUPARAMS = "NodeInfoQuParams";
	/** */
	public static final String NODEINFORESP = "NodeInfoResp";
	/** */
	public static final String NOT = "not";
	/** */
	public static final String NOTIFICATION = "Notification";
	/** */
	public static final String NOTIFICATIONDEF = "NotificationDef";
	/** */
	public static final String NOTIFICATIONFILTER = "NotificationFilter";
	/** */
	public static final String NUMBEREVALUATION = "NumberEvaluation";
	/** */
	public static final String NUMBERING = "Numbering";
	/** */
	public static final String NUMBERINGINTENT = "NumberingIntent";
	/** */
	public static final String NUMBERINGPARAM = "NumberingParam";
	/** */
	public static final String NUMBERINGPARAMS = "NumberingParams";
	/** */
	public static final String NUMBERITEM = "NumberItem";
	/** */
	public static final String NUMBERSTATE = "NumberState";

	/** */
	public static final String OBJECTMODEL = "ObjectModel";
	/** */
	public static final String OBJECTRESOLUTION = "ObjectResolution";
	/** */
	public static final String OBSERVATIONTARGET = "ObservationTarget";
	/** */
	public static final String OCCUPATION = "Occupation";
	/** */
	public static final String OCGCONTROL = "OCGControl";
	/** */
	public static final String OFFERRANGE = "OfferRange";
	/** */
	public static final String OPACITY = "Opacity";
	/** */
	public static final String OPACITYLEVEL = "OpacityLevel";
	/** */
	public static final String OR = "or";
	/** */
	public static final String ORDERING = "Ordering";
	/** */
	public static final String ORDERINGPARAMS = "OrderingParams";
	/** */
	public static final String ORGANIZATIONALUNIT = "OrganizationalUnit";
	/** */
	public static final String ORIENTATION = "Orientation";
	/** */
	public static final String OTHERWISE = "otherwise";
	/** */
	public static final String OVERAGE = "Overage";

	/** */
	public static final String PACKING = "Packing";
	/** */
	public static final String PACKINGINTENT = "PackingIntent";
	/** */
	public static final String PACKINGPARAMS = "PackingParams";
	/** */
	public static final String PAGEASSIGNEDLIST = "PageAssignedList";
	/** */
	public static final String PAGEASSIGNPARAMS = "PageAssignParams";
	/** */
	public static final String PAGECELL = "PageCell";
	/** */
	public static final String PAGECONDITION = "PageCondition";
	/** */
	public static final String PAGEDATA = "PageData";
	/** */
	public static final String PAGEELEMENT = "PageElement";
	/** */
	public static final String PAGELIST = "PageList";
	/** */
	public static final String PAGES = "Pages";
	/** */
	public static final String PAGESCONSTRAINTSPOOL = "PagesConstraintsPool";
	/** */
	public static final String PAGESRESULTSPOOL = "PagesResultsPool";
	/** */
	public static final String PAGEVARIANCE = "PageVariance";
	/** */
	public static final String PALLET = "Pallet";
	/** */
	public static final String PALLETCORNERBOARDS = "PalletCornerBoards";
	/** */
	public static final String PALLETIZING = "Palletizing";
	/** */
	public static final String PALLETIZINGPARAMS = "PalletizingParams";
	/** */
	public static final String PALLETMAXHEIGHT = "PalletMaxHeight";
	/** */
	public static final String PALLETMAXWEIGHT = "PalletMaxWeight";
	/** */
	public static final String PALLETQUANTITY = "PalletQuantity";
	/** */
	public static final String PALLETSIZE = "PalletSize";
	/** */
	public static final String PALLETTYPE = "PalletType";
	/** */
	public static final String PALLETWRAPPING = "PalletWrapping";
	/** */
	public static final String PARENT = "Parent";
	/** */
	public static final String PART = "Part";
	/** */
	public static final String PARTAMOUNT = "PartAmount";
	/** */
	public static final String PARTSTATUS = "PartStatus";
	public static final String PATCH = "Patch";
	public static final String PAYMENT = "Payment";
	/** */
	public static final String PAYTERM = "PayTerm";
	/** */
	public static final String PDFINTERPRETINGPARAMS = "PDFInterpretingParams";
	/** */
	public static final String PDFPATHEVALUATION = "PDFPathEvaluation";
	/** */
	public static final String PDFPATHSTATE = "PDFPathState";
	/** */
	public static final String PDFTOPSCONVERSION = "PDFToPSConversion";
	/** */
	public static final String PDFTOPSCONVERSIONPARAMS = "PDFToPSConversionParams";
	/** */
	public static final String PDFXPARAMS = "PDFXParams";
	/** */
	public static final String PDLCREATIONPARAMS = "PDLCreationParams";
	/** */
	public static final String PDLRESOURCEALIAS = "PDLResourceAlias";
	/** */
	public static final String PERFORATE = "Perforate";
	/** */
	public static final String PERFORATING = "Perforating";
	/** */
	public static final String PERFORATINGPARAMS = "PerforatingParams";
	/** */
	public static final String PERFORMANCE = "Performance";
	/** */
	public static final String PERSON = "Person";
	/** */
	public static final String PHASETIME = "PhaseTime";
	/** */
	public static final String PIPEPARAMS = "PipeParams";
	/** */
	public static final String PIXELCOLORANT = "PixelColorant";
	/** */
	public static final String PLACEHOLDERRESOURCE = "PlaceHolderResource";
	/** */
	public static final String PLASTICCOMBBINDING = "PlasticCombBinding";
	/** */
	public static final String PLASTICCOMBBINDINGPARAMS = "PlasticCombBindingParams";
	/** */
	public static final String PLASTICCOMBTYPE = "PlasticCombType";
	/** */
	public static final String PLATECOPYPARAMS = "PlateCopyParams";
	/** */
	public static final String POSITION = "Position";
	/** */
	public static final String POSITIONOBJ = "PositionObj";
	/** */
	public static final String POSTPRESSCOMPONENTPATH = "PostPressComponentPath";
	/** */
	public static final String PREFLIGHT = "Preflight";
	/** */
	public static final String PREFLIGHTACTION = "PreflightAction";
	/** */
	public static final String PREFLIGHTANALYSIS = "PreflightAnalysis";
	/** */
	public static final String PREFLIGHTARGUMENT = "PreflightArgument";
	/** */
	public static final String PREFLIGHTCONSTRAINT = "PreflightConstraint";
	/** */
	public static final String PREFLIGHTCONSTRAINTSPOOL = "PreflightConstraintsPool";
	/** */
	public static final String PREFLIGHTDETAIL = "PreflightDetail";
	/** */
	public static final String PREFLIGHTINFORMATION = "PreflightInformation";
	/** */
	public static final String PREFLIGHTINSTANCE = "PreflightInstance";
	/** */
	public static final String PREFLIGHTINSTANCEDETAIL = "PreflightInstanceDetail";
	/** */
	public static final String PREFLIGHTINVENTORY = "PreflightInventory";
	/** */
	public static final String PREFLIGHTPARAMS = "PreflightParams";
	/** */
	public static final String PREFLIGHTPROFILE = "PreflightProfile";
	/** */
	public static final String PREFLIGHTPROFILECONSTRAINTSPOOL = "PreflightProfileConstraintsPool";
	/** */
	public static final String PREFLIGHTREPORT = "PreflightReport";
	/** */
	public static final String PREFLIGHTREPORTRULEPOOL = "PreflightReportRulePool";
	/** */
	public static final String PREFLIGHTRESULTSPOOL = "PreflightResultsPool";
	/** */
	public static final String PRERROR = "PRError";
	/** */
	public static final String PREVIEW = "Preview";
	/** */
	public static final String PREVIEWGENERATION = "PreviewGeneration";
	/** */
	public static final String PREVIEWGENERATIONPARAMS = "PreviewGenerationParams";
	/** */
	public static final String PRGROUP = "PRGroup";
	/** */
	public static final String PRGROUPOCCURRENCE = "PRGroupOccurrence";
	/** */
	public static final String PRICING = "Pricing";
	/** */
	public static final String PRINTCONDITION = "PrintCondition";
	/** */
	public static final String PRINTCONDITIONCOLOR = "PrintConditionColor";
	/** */
	public static final String PRINTINGUNITWEBPATH = "PrintingUnitWebPath";
	/** */
	public static final String PRINTPREFERENCE = "PrintPreference";
	/** */
	public static final String PRINTPROCESS = "PrintProcess";
	/** */
	public static final String PRINTROLLING = "PrintRolling";
	/** */
	public static final String PRINTROLLINGPARAMS = "PrintRollingParams";
	/** */
	public static final String PRITEM = "PRItem";
	/** */
	public static final String PROCCURRENCE = "PROccurrence";
	/** @deprecated use EnumType.xxx.getName() */
	@Deprecated
	/** */
	public static final String PROCESSGROUP = "ProcessGroup";
	/** */
	public static final String PROCESSRUN = "ProcessRun";
	/** */
	public static final String PROCESSTYPE_UNKNOWN = "Unknown";
	/** @deprecated use EnumType.xxx.getName() */
	@Deprecated
	/** */
	public static final String PRODUCT = "Product";
	/** */
	public static final String PRODUCTIONINTENT = "ProductionIntent";
	/** */
	public static final String PRODUCTIONPATH = "ProductionPath";
	/** */
	public static final String PRODUCTIONSUBPATH = "ProductionSubPath";
	/** */
	public static final String PROOFING = "Proofing";
	/** */
	public static final String PROOFINGINTENT = "ProofingIntent";
	/** */
	public static final String PROOFINGPARAMS = "ProofingParams";
	/** */
	public static final String PROOFITEM = "ProofItem";
	/** */
	public static final String PROOFTYPE = "ProofType";
	/** */
	public static final String PROPERTIES = "Properties";
	/** */
	public static final String PRRULE = "PRRule";
	/** */
	public static final String PRRULEATTR = "PRRuleAttr";
	/** */
	public static final String PSTOPDFCONVERSION = "PSToPDFConversion";
	/** */
	public static final String PSTOPDFCONVERSIONPARAMS = "PSToPDFConversionParams";
	/** */
	public static final String PUBLISHINGINTENT = "PublishingIntent";

	/** */
	public static final String QUALITYCONTROL = "QualityControl";
	/** */
	public static final String QUALITYCONTROLPARAMS = "QualityControlParams";
	/** */
	public static final String QUALITYCONTROLRESULT = "QualityControlResult";
	/** */
	public static final String QUALITYMEASUREMENT = "QualityMeasurement";
	/** */
	public static final String QUERY = "Query";
	/** */
	public static final String QUEUE = "Queue";
	/** */
	public static final String QUEUEENTRY = "QueueEntry";
	/** */
	public static final String QUEUEENTRYDEF = "QueueEntryDef";
	/** */
	public static final String QUEUEENTRYDEFLIST = "QueueEntryDefList";
	/** */
	public static final String QUEUEENTRYPOSPARAMS = "QueueEntryPosParams";
	/** */
	public static final String QUEUEENTRYPRIPARAMS = "QueueEntryPriParams";
	/** */
	public static final String QUEUEFILTER = "QueueFilter";
	/** */
	public static final String QUEUESUBMISSIONPARAMS = "QueueSubmissionParams";

	/** */
	public static final String RANGE = "Range";
	/** */
	public static final String RASTERREADINGPARAMS = "RasterReadingParams";
	/** */
	public static final String RECTANGLEEVALUATION = "RectangleEvaluation";
	/** */
	public static final String RECTANGLESTATE = "RectangleState";
	/** */
	public static final String RECYCLED = "Recycled";
	/** */
	public static final String RECYCLEDPERCENTAGE = "RecycledPercentage";
	/** */
	public static final String REFANCHOR = "RefAnchor";
	/** */
	public static final String REFELEMENT = "RefElement";
	/** */
	public static final String REFERENCEXOBJPARAMS = "ReferenceXObjParams";
	/** */
	public static final String REGISTERMARK = "RegisterMark";
	public static final String REGISTERRIBBON = "RegisterRibbon";
	/** */
	public static final String REGISTRATION = "Registration";
	public static final String REGISTRATIONQUALITY = "RegistrationQuality";
	public static final String REJECTED = "Rejected";
	/** */
	public static final String REMOVED = "Removed";
	/** */
	public static final String REMOVELINK = "RemoveLink";
	/** */
	public static final String REMOVEQUEUEENTRYPARAMS = "RemoveQueueEntryParams";
	/** */
	public static final String RENDERING = "Rendering";
	/** */
	public static final String RENDERINGPARAMS = "RenderingParams";
	/** */
	public static final String REPEATDESC = "RepeatDesc";
	/** */
	public static final String REQUESTQUEUEENTRYPARAMS = "RequestQueueEntryParams";
	/** */
	public static final String REQUIRED = "Required";
	/** */
	public static final String REQUIREDDURATION = "RequiredDuration";
	/** */
	public static final String RESOURCE = "Resource";
	/** */
	public static final String RESOURCEAUDIT = "ResourceAudit";
	/** */
	public static final String RESOURCECMDPARAMS = "ResourceCmdParams";
	/** */
	public static final String RESOURCEDEFINITION = "ResourceDefinition";
	/** */
	public static final String RESOURCEDEFINITIONPARAMS = "ResourceDefinitionParams";
	/** */
	public static final String RESOURCEINFO = "ResourceInfo";
	/** */
	public static final String RESOURCELINK = "ResourceLink";
	/** */
	public static final String RESOURCELINKPOOL = "ResourceLinkPool";
	/** */
	public static final String RESOURCEPARAM = "ResourceParam";
	/** */
	public static final String RESOURCEPOOL = "ResourcePool";
	/** */
	public static final String RESOURCEPULLPARAMS = "ResourcePullParams";
	/** */
	public static final String RESOURCEQUPARAMS = "ResourceQuParams";
	/** */
	public static final String RESPONSE = "Response";
	/** */
	public static final String RESUMEQUEUEENTRYPARAMS = "ResumeQueueEntryParams";
	/** */
	public static final String RESUBMISSIONPARAMS = "ResubmissionParams";
	/** */
	public static final String RETURNMETHOD = "ReturnMethod";
	/** */
	public static final String RETURNQUEUEENTRYPARAMS = "ReturnQueueEntryParams";
	/** */
	public static final String RINGBINDING = "RingBinding";
	/** */
	public static final String RINGBINDINGPARAMS = "RingBindingParams";
	/** */
	public static final String RINGDIAMETER = "RingDiameter";
	/** */
	public static final String RINGMECHANIC = "RingMechanic";
	/** */
	public static final String RINGSHAPE = "RingShape";
	/** */
	public static final String RINGSYSTEM = "RingSystem";
	/** */
	public static final String RIVETSEXPOSED = "RivetsExposed";
	/** */
	public static final String ROLLSTAND = "RollStand";
	/** */
	public static final String RULELENGTH = "RuleLength";
	/** */
	public static final String RUNLIST = "RunList";

	/** */
	public static final String SADDLESTITCHING = "SaddleStitching";
	/** */
	public static final String SADDLESTITCHINGPARAMS = "SaddleStitchingParams";
	/** */
	public static final String SCANNING = "Scanning";
	/** */
	public static final String SCANPARAMS = "ScanParams";
	/** */
	public static final String SCAVENGERAREA = "ScavengerArea";
	/** */
	public static final String SCORE = "Score";
	/** */
	public static final String SCORING = "Scoring";
	/** */
	public static final String SCREENING = "Screening";
	/** */
	public static final String SCREENINGINTENT = "ScreeningIntent";
	/** */
	public static final String SCREENINGPARAMS = "ScreeningParams";
	/** */
	public static final String SCREENINGTYPE = "ScreeningType";
	/** */
	public static final String SCREENSELECTOR = "ScreenSelector";
	/** */
	public static final String SEALING = "Sealing";
	/** */
	public static final String SEARCHPATH = "SearchPath";
	/** */
	public static final String SEPARATION = "Separation";
	/** */
	public static final String SEPARATIONCONTROLPARAMS = "SeparationControlParams";
	/** */
	public static final String SEPARATIONLIST = "SeparationList";
	/** */
	public static final String SEPARATIONLISTBACK = "SeparationListBack";
	/** */
	public static final String SEPARATIONLISTFRONT = "SeparationListFront";
	/** */
	public static final String SEPARATIONSPEC = "SeparationSpec";
	public static final String SEPARATIONTINT = "SeparationTint";
	public static final String SERVICELEVEL = "ServiceLevel";
	/** */
	public static final String SET = "set";
	/** */
	public static final String SHAPE = "Shape";
	/** */
	public static final String SHAPECUT = "ShapeCut";
	/** */
	public static final String SHAPECUTTING = "ShapeCutting";
	/** */
	public static final String SHAPECUTTINGINTENT = "ShapeCuttingIntent";
	/** */
	public static final String SHAPECUTTINGPARAMS = "ShapeCuttingParams";
	/** */
	public static final String SHAPEDEF = "ShapeDef";
	/** */
	public static final String SHAPEDEFPRODUCTIONPARAMS = "ShapeDefProductionParams";
	/** */
	public static final String SHAPEDEPTH = "ShapeDepth";
	/** */
	public static final String SHAPEELEMENT = "ShapeElement";
	/** */
	public static final String SHAPEEVALUATION = "ShapeEvaluation";
	/** */
	public static final String SHAPESTATE = "ShapeState";
	/** */
	public static final String SHAPETEMPLATE = "ShapeTemplate";
	/** */
	public static final String SHAPETYPE = "ShapeType";
	/** */
	public static final String SHEET = "Sheet";
	/** */
	public static final String SHEETCONDITION = "SheetCondition";
	/** */
	public static final String SHEETOPTIMIZINGPARAMS = "SheetOptimizingParams";
	/** */
	public static final String SHIFTPOINT = "ShiftPoint";
	/** */
	public static final String SHRINKING = "Shrinking";
	/** */
	public static final String SHRINKINGPARAMS = "ShrinkingParams";
	/** */
	public static final String SHUTDOWNCMDPARAMS = "ShutDownCmdParams";
	/** */
	public static final String SIDESEWING = "SideSewing";
	/** */
	public static final String SIDESEWINGPARAMS = "SideSewingParams";
	/** */
	public static final String SIDESTITCHING = "SideStitching";
	/** */
	public static final String SIGNAL = "Signal";
	/** */
	public static final String SIGNATURE = "Signature";
	/** */
	public static final String SIGNATURECELL = "SignatureCell";
	/** */
	public static final String SIZEINTENT = "SizeIntent";
	/** */
	public static final String SIZEPOLICY = "SizePolicy";
	/** */
	public static final String SOFTCOVERBINDING = "SoftCoverBinding";
	/** */
	public static final String SOFTPROOFING = "SoftProofing";
	/** */
	public static final String SOURCERESOURCE = "SourceResource";
	/** */
	public static final String SPAWNED = "Spawned";
	/** */
	public static final String SPINEBRUSHING = "SpineBrushing";
	/** */
	public static final String SPINEFIBERROUGHING = "SpineFiberRoughing";
	/**
	 * @deprecated typo use SPINEFIBERROUGHING
	 */
	@Deprecated
	/** */
	public static final String SPINEFIBREROUGHING = SPINEFIBERROUGHING;
	/** */
	public static final String SPINEGLUE = "SpineGlue";
	/** */
	public static final String SPINELEVELLING = "SpineLevelling";
	/** */
	public static final String SPINEMILLING = "SpineMilling";
	/** */
	public static final String SPINENOTCHING = "SpineNotching";
	/** */
	public static final String SPINEPREPARATION = "SpinePreparation";
	/** */
	public static final String SPINEPREPARATIONPARAMS = "SpinePreparationParams";
	/** */
	public static final String SPINESANDING = "SpineSanding";
	/** */
	public static final String SPINESHREDDING = "SpineShredding";
	/** */
	public static final String SPINETAPING = "SpineTaping";
	/** */
	public static final String SPINETAPINGPARAMS = "SpineTapingParams";
	/** */
	public static final String SPLIT = "Split";
	/** */
	public static final String STACK = "Stack";
	/** */
	public static final String STACKING = "Stacking";
	/** */
	public static final String STACKINGPARAMS = "StackingParams";
	/** */
	public static final String STATION = "Station";
	/** */
	public static final String STATICBLOCKINGPARAMS = "StaticBlockingParams";
	/** */
	public static final String STATUSPOOL = "StatusPool";
	/** */
	public static final String STATUSQUPARAMS = "StatusQuParams";
	/** */
	public static final String STITCHING = "Stitching";
	/** */
	public static final String STITCHINGPARAMS = "StitchingParams";
	/** */
	public static final String STITCHNUMBER = "StitchNumber";
	/** */
	public static final String STOCKBRAND = "StockBrand";
	/** */
	public static final String STOCKTYPE = "StockType";
	/** */
	public static final String STOPPERSCHPARAMS = "StopPersChParams";
	/** */
	public static final String STRAP = "Strap";
	/** */
	public static final String STRAPPING = "Strapping";
	/** */
	public static final String STRAPPINGPARAMS = "StrappingParams";
	/** */
	public static final String STRINGEVALUATION = "StringEvaluation";
	/** */
	public static final String STRINGLISTVALUE = "StringListValue";
	/** */
	public static final String STRINGSTATE = "StringState";
	/** */
	public static final String STRIPBINDING = "StripBinding";
	/** */
	public static final String STRIPBINDINGPARAMS = "StripBindingParams";
	/** */
	public static final String STRIPBINDINGPARAMSUPDATE = "StripBindingParamsUpdate";
	/** */
	public static final String STRIPCELLPARAMS = "StripCellParams";
	/** */
	public static final String STRIPMARK = "StripMark";
	/** */
	public static final String STRIPMATERIAL = "StripMaterial";
	/** */
	public static final String STRIPPING = "Stripping";
	/** */
	public static final String STRIPPINGPARAMS = "StrippingParams";
	/** */
	public static final String SUBMISSIONMETHODS = "SubmissionMethods";
	/** */
	public static final String SUBSCRIPTION = "Subscription";
	/** */
	public static final String SUBSCRIPTIONINFO = "SubscriptionInfo";
	/** */
	public static final String SUBSCRIPTIONFILTER = "SubscriptionFilter";
	/** */
	public static final String SURFACE = "Surface";
	/** */
	public static final String SURPLUSHANDLING = "SurplusHandling";
	/** */
	public static final String SUSPENDQUEUEENTRYPARAMS = "SuspendQueueEntryParams";
	/** */
	public static final String SYSTEMTIMESET = "SystemTimeSet";

	/** */
	public static final String TABBINDMYLAR = "TabBindMylar";
	/** */
	public static final String TABBODYCOPY = "TabBodyCopy";
	/** */
	public static final String TABBRAND = "TabBrand";
	/** */
	public static final String TABDIMENSIONS = "TabDimensions";
	/** */
	public static final String TABEXTENSIONDISTANCE = "TabExtensionDistance";
	/** */
	public static final String TABEXTENSIONMYLAR = "TabExtensionMylar";
	/** */
	public static final String TABMYLARCOLOR = "TabMylarColor";
	/** */
	public static final String TABMYLARCOLORDETAILS = "TabMylarColorDetails";
	/** */
	public static final String TABS = "Tabs";
	/** */
	public static final String TAPE = "Tape";
	/** */
	public static final String TAPEBINDING = "TapeBinding";
	/** */
	public static final String TAPECOLOR = "TapeColor";
	/** */
	public static final String TECHNOLOGY = "Technology";
	/** */
	public static final String TEETHPERDIMENSION = "TeethPerDimension";
	/** */
	public static final String TEMPERATURE = "Temperature";
	/** */
	public static final String TERM = "Term";
	/** */
	public static final String TEST = "Test";
	/** */
	public static final String TESTPOOL = "TestPool";
	/** */
	public static final String TESTREF = "TestRef";
	/** */
	public static final String TEXTURE = "Texture";
	/** */
	public static final String THICKNESS = "Thickness";
	/** */
	public static final String THINPDFPARAMS = "ThinPDFParams";
	/** */
	public static final String THREADSEALING = "ThreadSealing";
	/** */
	public static final String THREADSEALINGPARAMS = "ThreadSealingParams";
	/** */
	public static final String THREADSEWING = "ThreadSewing";
	/** */
	public static final String THREADSEWINGPARAMS = "ThreadSewingParams";
	/** */
	public static final String TIFFEMBEDDEDFILE = "TIFFEmbeddedFile";
	/** */
	public static final String TIFFFORMATPARAMS = "TIFFFormatParams";
	/** */
	public static final String TIFFTAG = "TIFFtag";
	/** */
	public static final String TIGHTBACKING = "TightBacking";
	/** */
	public static final String TILE = "Tile";
	/** */
	public static final String TILING = "Tiling";
	/** */
	public static final String TOOL = "Tool";
	/** */
	public static final String TRACKFILTER = "TrackFilter";
	/** */
	public static final String TRACKRESULT = "TrackResult";
	/** */
	public static final String TRANSFER = "Transfer";
	/** */
	public static final String TRANSFERCURVE = "TransferCurve";
	/** */
	public static final String TRANSFERCURVEPOOL = "TransferCurvePool";
	/** */
	public static final String TRANSFERCURVESET = "TransferCurveSet";
	/** */
	public static final String TRANSFERFUNCTIONCONTROL = "TransferFunctionControl";
	/** */
	public static final String TRAPPING = "Trapping";
	/** */
	public static final String TRAPPINGDETAILS = "TrappingDetails";
	/** */
	public static final String TRAPPINGORDER = "TrappingOrder";
	/** */
	public static final String TRAPPINGPARAMS = "TrappingParams";
	/** */
	public static final String TRAPREGION = "TrapRegion";
	/** */
	public static final String TRIGGER = "Trigger";
	/** */
	public static final String TRIMMING = "Trimming";
	/** */
	public static final String TRIMMINGPARAMS = "TrimmingParams";

	/** */
	public static final String UNDERAGE = "Underage";
	/** */
	public static final String UPDATE = "Update";
	/** */
	public static final String UPDATEJDFCMDPARAMS = "UpdateJDFCmdParams";
	/** */
	public static final String URL = "URL";
	/** */
	public static final String USAGECOUNTER = "UsageCounter";
	/** */
	public static final String USWEIGHT = "USWeight";

	/** */
	public static final String VALUE = "Value";
	/** */
	public static final String VALUELOC = "ValueLoc";
	/** */
	public static final String VARNISHINGPARAMS = "VarnishingParams";
	public static final String VARIABLEINTENT = "VariableIntent";

	/** */
	public static final String VELOBINDING = "VeloBinding";
	/** */
	public static final String VERIFICATION = "Verification";
	/** */
	public static final String VERIFICATIONPARAMS = "VerificationParams";
	/** */
	public static final String VIEWBINDER = "ViewBinder";

	/** */
	public static final String WAKEUPCMDPARAMS = "WakeUpCmdParams";
	/** */
	public static final String WEBINLINEFINISHINGPARAMS = "WebInlineFinishingParams";
	/** */
	public static final String WEIGHT = "Weight";
	/** */
	public static final String WHEN = "when";
	/** */
	public static final String WINDINGPARAMS = "WindingParams";
	/** */
	public static final String WIRECOMBBINDING = "WireCombBinding";
	/** */
	public static final String WIRECOMBBINDINGPARAMS = "WireCombBindingParams";
	/** */
	public static final String WIRECOMBBRAND = "WireCombBrand";
	/** */
	public static final String WIRECOMBMATERIAL = "WireCombMaterial";
	/** */
	public static final String WIRECOMBSHAPE = "WireCombShape";
	/** */
	public static final String WRAPPEDQUANTITY = "WrappedQuantity";
	/** */
	public static final String WRAPPING = "Wrapping";
	/** */
	public static final String WRAPPINGMATERIAL = "WrappingMaterial";
	/** */
	public static final String WRAPPINGPARAMS = "WrappingParams";

	/** */
	public static final String XOR = "xor";
	/** */
	public static final String XPOSITION = "XPosition";
	/** */
	public static final String XYPAIREVALUATION = "XYPairEvaluation";
	/** */
	public static final String XYPAIRSTATE = "XYPairState";

	/** */
	public static final String YPOSITION = "YPosition";

	// JDF 1.6 + 1.7 updates
	public static final String CUTDEPTH = "CutDepth";
	public static final String AREA = "Area";
	public static final String AVERAGEPAGES = "AveragePages";
	public static final String MAXPAGES = "MaxPages";
	public static final String MINPAGES = "MinPages";
	public static final String NUMBEROFCOPIES = "NumberOfCopies";
	public static final String VARIABLETYPE = "VariableType";
	public static final String VARIABLEQUALITY = "VariableQuality";
	public static final String PREFLIGHTITEM = "PreflightItem";
	public static final String ADDRESSLINE = "AddressLine";
	public static final String ADHESIVENOTE = "AdhesiveNote";
	public static final String STAPLESHAPE = AttributeName.STAPLESHAPE;
}