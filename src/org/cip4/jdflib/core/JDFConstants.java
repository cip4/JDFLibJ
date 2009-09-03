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
/**
 *
 * Copyright (c) 2001-2003 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFConstants.java
 */
package org.cip4.jdflib.core;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * before August 20, 2009
 */
public abstract class JDFConstants
{
	/**
	 * cip4 namespace uri for any jdf
	 */
	public static final String JDFNAMESPACE = "http://www.CIP4.org/JDFSchema_1_1";

	/**
	 * lower case flag for misspelt jdf namespace uris
	 */
	public static final String CIP4ORG = ".cip4.org";
	/**
	 * @deprecated use null for wildcard
	 */
	@Deprecated
	public static final String WILDCARD = "*";
	/**
	 * 
	 */
	public static final String EMPTYSTRING = "";
	/**
	 * @deprecated use null for no namespace check
	 */
	@Deprecated
	/** * */public static final String NONAMESPACE = EMPTYSTRING;
	/** @deprecated use null */
	@Deprecated
	/** * */public static final String IMPROBABLE_STRING = "\"\'";
	/** * */
	public static final String COMMA = ",";
	/** * */
	public static final String BLANK = " ";
	/** * */
	public static final String COLON = ":";
	/** * */
	public static final String HYPHEN = "-";
	/** * */
	public static final char CHAR_COLON = ':';
	/** * */
	public static final String QUOTE = "\"";
	/** * */
	public static final String SLASH = "/";
	/** * */
	public static final String DOT = ".";
	/** * */
	public static final String DOTSLASH = "./";
	/** * */
	public static final String DOTDOTSLASH = "../";
	/** * */
	public static final String AET = "@";
	/** * */
	public static final String TILDE = "~";
	/** * */
	public static final String STAR = "*";
	/** * */
	public static final String UNDERSCORE = "_";

	/** * */
	public static final String XMLNS = "xmlns";
	/** * */
	public static final String XSI = "xsi";

	/** @deprecated use null (not "null") */
	@Deprecated
	/** * */public static final String NULL = "null";
	/** * */
	public static final String TRUE = "true";
	/** * */
	public static final String FALSE = "false";

	/** * */
	public static final String NEGINF = "-INF";
	/** * */
	public static final String POSINF = "INF";

	/** * */
	public static final String INTERNAL = "Internal";

	// the positive INF value 0x7FEDCBAA
	/** @deprecated */
	@Deprecated
	/** * */public static final long POSINF_HEX = 0x7FEDCBAA;
	// the negative INF value 0x80123456
	/** @deprecated */
	@Deprecated
	/** * */public static final long NEGINF_HEX = 0x80123456;

	/** * */
	public static final String INPUT_ZEROTOINFINITY = "i*";
	/** * */
	public static final String INPUT_ONETOINFINITY = "i+";
	/** * */
	public static final String INPUT_ZEROTOONE = "i?";
	/** * */
	public static final String INPUT_EXACTLYONE = "i_";

	/** * */
	public static final String OUTPUT_ZEROTOINFINITY = "o*";
	/** * */
	public static final String OUTPUT_ONETOINFINITY = "o+";
	/** * */
	public static final String OUTPUT_ZEROTOONE = "o?";
	/** * */
	public static final String OUTPUT_EXACTLYONE = "o_";

	/** * */
	public static final String VERSION_1_0 = "1.0";
	/** * */
	public static final String VERSION_1_1 = "1.1";
	/** * */
	public static final String VERSION_1_2 = "1.2";
	/** * */
	public static final String VERSION_1_3 = "1.3";
	/** * */
	public static final String VERSION_1_4 = "1.4";
	/** * */
	public static final String VERSION_1_5 = "1.5";
	/** * */
	public static final String VERSION_1_6 = "1.6";
	/** * */
	public static final String VERSION_1_7 = "1.7";
	/** * */
	public static final String VERSION_1_8 = "1.8";
	/** * */
	public static final String VERSION_1_9 = "1.9";
	/** * */
	public static final String VERSION_2_0 = "2.0";

	// status stuff
	/** * */
	public static final String UNKNOWN = "Unknown";

	// EnumNodeStatus : enums accordng to JDF spec 3.1.2, Table 3-3 Status (see
	// also Table 4-2)
	/** * */
	public static final String WAITING = "Waiting";
	/** * */
	public static final String TESTRUNINPROGRESS = "TestRunInProgress";
	/** * */
	public static final String READY = "Ready";
	/** * */
	public static final String FAILEDTESTRUN = "FailedTestRun";
	/** * */
	public static final String SETUP = "Setup";
	/** * */
	public static final String INPROGRESS = "InProgress";
	/** * */
	public static final String CLEANUP = "Cleanup";
	/** * */
	public static final String SPAWNED = "Spawned";
	/** * */
	public static final String SUSPENDED = "Suspended";
	/** * */
	public static final String STOPPED = "Stopped";
	/** * */
	public static final String COMPLETED = "Completed";
	/** * */
	public static final String ABORTED = "Aborted";
	/** * */
	public static final String POOL = "Pool";
	/** * */
	public static final String PART = "Part";

	// EnumSpawnStatus : enums accordng to JDF spec 3.7, Table 3-11 SpawnStatus
	/** * */
	public static final String NOTSPAWNED = "NotSpawned";
	/** * */
	public static final String SPAWNEDRO = "SpawnedRO";
	/** * */
	public static final String SPAWNEDRW = "SpawnedRW";

	// EnumResStatus : enums accordng to JDF spec 3.7, Table 3-11 Status
	/** * */
	public static final String INCOMPLETE = "Incomplete";
	/** * */
	public static final String REJECTED = "Rejected";
	/** * */
	public static final String UNAVAILABLE = "Unavailable";
	/** * */
	public static final String INUSE = "InUse";
	/** * */
	public static final String DRAFT = "Draft";
	/** * */
	public static final String COMPLETE = "Complete";
	/** * */
	public static final String AVAILABLE = "Available";

	// EnumDeviceStatus : enums accordng to JDF spec 3.10.1.3, Table 3-33, 5-43,
	// 5-45 DeviceStatus
	// final static String UNKNOWN = "Unknown";
	/** * */
	public static final String IDLE = "Idle";
	/** * */
	public static final String DOWN = "Down";
	// final static String SETUP = "Setup";
	/** * */
	public static final String RUNNING = "Running";
	// final static String CLEANUP = "Cleanup";
	// final static String STOPPED = "Stopped";

	// EnumQueueStatus : enums accordng to JDF spec 5.6.4, Table 5-78 Status of
	// a Queue
	/** * */
	public static final String BLOCKED = "Blocked";
	/** * */
	public static final String CLOSED = "Closed";
	/** * */
	public static final String FULL = "Full";
	// final static String RUNNING = "Running";
	// final static String WAITING = "Waiting";
	/** * */
	public static final String HELD = "Held";
	// additional stati
	/** * */
	public static final String UNREACHABLE = "Unreachable";
	/** * */
	public static final String NEEDSATTENTION = "NeedsAttention";

	// EnumQueueEntryStatus : enums accordng to JDF spec 5.6.4, Table 5-79
	// Status of a QueueEntry
	// final static String RUNNING = "Running";
	// final static String WAITING = "Waiting";
	// final static String HELD = "Held";
	/** * */
	public static final String REMOVED = "Removed";

	// TODO deprecate duplicates that exist in ElementName and AttributeName
	/** * */
	public static final String AUDIT = "Audit";
	/** * */
	public static final String BUSINESSID = "BusinessID";
	/** * */
	public static final String BUSINESSREFID = "BusinessRefID";
	/** * */
	public static final String COLORSPACECONVERSION = "ColorSpaceConversion";
	/** * */
	public static final String COMBINED = "Combined";
	/** * */
	public static final String CONVENTIONALPRINTING = "ConventionalPrinting";
	/** * */
	public static final String CUTTING = "Cutting";
	/** * */
	public static final String DEPENDENCIES = "Dependencies";
	/** * */
	public static final String DEPENDENCY = "Dependency";
	/** * */
	public static final String DIGITALPRINTING = "DigitalPrinting";
	/** * */
	public static final String DRAFTOK = "DraftOK";
	/** * */
	public static final String FILENAME = "FileName";
	/** * */
	public static final String FOLDING = "Folding";
	/** * */
	public static final String IMAGEREPLACEMENT = "ImageReplacement";
	/** * */
	public static final String IMAGESETTING = "ImageSetting";
	/** * */
	public static final String IMPOSITION = "Imposition";
	/** * */
	public static final String INPUT = "Input";
	/** * */
	public static final String INTERPRETING = "Interpreting";
	/** * */
	public static final String LAYOUTELEMENTPRODUCTION = "LayoutElementProduction";
	/** * */
	public static final String LINK = "Link";
	/** * */
	public static final String JDFHERING = "JDFhering";
	/** * */
	public static final String JREF = "jRef";
	/** * */
	public static final String MACHINE = "Machine";
	/** * */
	public static final String MERGEID = "MergeID";
	/** * */
	public static final String MIME = "Mime";
	/** * */
	public static final String NEWSPAWNID = "NewSpawnID";
	/** * */
	public static final String NODEID = "NodeID";
	/** * */
	public static final String OPERATOR = "Operator";
	/** * */
	public static final String OUTPUT = "Output";
	/** * */
	public static final String PAGEELEMENTS = "PageElements";
	/** * */
	public static final String PROCESSGROUP = "ProcessGroup";
	/** * */
	public static final String PRODUCT = "Product";
	/** * */
	public static final String PROJECTID = "ProjectID";
	/** * */
	public static final String RENDERING = "Rendering";
	/** * */
	public static final String RIPPING = "Ripping";
	/** * */
	public static final String REF = "Ref"; // important: upper case "R"
	// see AttributeName.REF for "ref" as attribute name
	/** * */
	public static final String RREF = "rRef";
	/** * */
	public static final String RREFSOVERWRITTEN = "rRefsOverwritten";
	/** * */
	public static final String RREFSROCOPIED = "rRefsROCopied";
	/** * */
	public static final String RREFSRWCOPIED = "rRefsRWCopied";
	/** * */
	public static final String RSUBREF = "rSubRef";
	/** * */
	public static final String SCANNING = "Scanning";
	/** * */
	public static final String SCREENING = "Screening";
	/** * */
	public static final String SPAWNID = "SpawnID";
	/** * */
	public static final String SEVERITY = "Severity";
	/** * */
	public static final String TITLE = "Title";
	/** * */
	public static final String TRIMMING = "Trimming";
	/** * */
	public static final String UNBOUNDED = "unbounded";
	/** * */
	public static final String UPDATE = "Update";

	// notification - states
	/** * */
	public static final String NOTIFICATION_EVENT = "Event";
	/** * */
	public static final String NOTIFICATION_INFORMATION = "Information";
	/** * */
	public static final String NOTIFICATION_WARNING = "Warning";
	/** * */
	public static final String NOTIFICATION_ERROR = "Error";
	/** * */
	public static final String NOTIFICATION_FATAL = "Fatal";

	/**
	 * Constants EnumActivation Used by e.g. JDFNode - Table 3-3
	 */
	/** @deprecated */
	@Deprecated
	/** * */public static final String ACTIVATION_UNKNOWN = "Unknown";
	/** * */
	public static final String ACTIVATION_INACTIVE = "Inactive";
	/** * */
	public static final String ACTIVATION_INFORMATIVE = "Informative";
	/** * */
	public static final String ACTIVATION_HELD = "Held";
	/** * */
	public static final String ACTIVATION_ACTIVE = "Active";
	/** * */
	public static final String ACTIVATION_TESTRUN = "TestRun";
	/** * */
	public static final String ACTIVATION_TESTRUNANDGO = "TestRunAndGo";

	/**
	 * Constants EnumPartUsage Used by e.g. JDFResource
	 */
	/** @deprecated */
	@Deprecated
	/** * */public static final String PARTUSAGE_UNKNOWN = "Unknown";
	/** * */
	public static final String PARTUSAGE_EXPLICIT = "Explicit";
	/** * */
	public static final String PARTUSAGE_IMPLICIT = "Implicit";
	/** * */
	public static final String PARTUSAGE_SPARSE = "Sparse";

	/**
	 * Constants EnumLotControl Used by JDFResource
	 */
	/** @deprecated */
	@Deprecated
	/** * */public static final String LOTCONTROL_UNKNOWN = "Unknown";
	/** * */
	public static final String LOTCONTROL_CONTROLLED = "Controlled";
	/** * */
	public static final String LOTCONTROL_NOTCONTROLLED = "NotControlled";

	/**
	 * Enumeration for partition keys Used by e.g. JDFResource
	 */
	/** @deprecated */
	@Deprecated
	/** * */public static final String PARTIDKEY_UNKNOWN = "Unknown";
	/** * */
	public static final String PARTIDKEY_BINDERYSIGNATURENAME = "BinderySignatureName";
	/** * */
	public static final String PARTIDKEY_BLOCKNAME = "BlockName";
	/** * */
	public static final String PARTIDKEY_BUNDLEITEMINDEX = "BundleItemIndex";
	/** * */
	public static final String PARTIDKEY_CELLINDEX = "CellIndex";
	/** * */
	public static final String PARTIDKEY_CONDITION = "Condition";
	/** * */
	public static final String PARTIDKEY_DOCCOPIES = "DocCopies";
	/** * */
	public static final String PARTIDKEY_DOCINDEX = "DocIndex";
	/** * */
	public static final String PARTIDKEY_DOCRUNINDEX = "DocRunIndex";
	/** * */
	public static final String PARTIDKEY_DOCSHEETINDEX = "DocSheetIndex";
	/** * */
	public static final String PARTIDKEY_FOUNTAINNUMBER = "FountainNumber";
	/** * */
	public static final String PARTIDKEY_ITEMNAMES = "ItemNames";
	/** * */
	public static final String PARTIDKEY_LAYERIDS = "LayerIDs";
	/** * */
	public static final String PARTIDKEY_LOCATION = "Location";
	/** * */
	public static final String PARTIDKEY_OPTION = "Option";
	/** * */
	public static final String PARTIDKEY_PAGENUMBER = "PageNumber";
	/** * */
	public static final String PARTIDKEY_PARTVERSION = "PartVersion";
	/** * */
	public static final String PARTIDKEY_PREFLIGHTRULE = "PreflightRule";
	/** * */
	public static final String PARTIDKEY_PREVIEWTYPE = "PreviewType";
	/** * */
	public static final String PARTIDKEY_RIBBONNAME = "RibbonName";
	/** * */
	public static final String PARTIDKEY_RUN = "Run";
	/** * */
	public static final String PARTIDKEY_RUNINDEX = "RunIndex";
	/** * */
	public static final String PARTIDKEY_RUNTAGS = "RunTags";
	/** * */
	public static final String PARTIDKEY_RUNPAGE = "RunPage";
	/** * */
	public static final String PARTIDKEY_SEPARATION = "Separation";
	/** * */
	public static final String PARTIDKEY_SECTIONINDEX = "SectionIndex";
	/** * */
	public static final String PARTIDKEY_SETDOCINDEX = "SetDocIndex";
	/** * */
	public static final String PARTIDKEY_SETSHEETINDEX = "SetSheetIndex";
	/** * */
	public static final String PARTIDKEY_SETINDEX = "SetIndex";
	/** * */
	public static final String PARTIDKEY_SETRUNINDEX = "SetRunIndex";
	/** * */
	public static final String PARTIDKEY_SHEETINDEX = "SheetIndex";
	/** * */
	public static final String PARTIDKEY_SHEETNAME = "SheetName";
	/** * */
	public static final String PARTIDKEY_SIDE = "Side";
	/** * */
	public static final String PARTIDKEY_SIGNATURENAME = "SignatureName";
	/** * */
	public static final String PARTIDKEY_TILEID = "TileID";
	/** * */
	public static final String PARTIDKEY_WEBNAME = "WebName";

	/**
	 * Enumeration for DataType 7.1.1 Used by e.g. JDFSpanBase
	 */

	/** @deprecated */
	@Deprecated
	/** * */public static final String DATATYPE_UNKNOWN = "Unknown";
	/** * */
	public static final String DATATYPE_DURATION = "DurationSpan";
	/** * */
	public static final String DATATYPE_ENUMERATION = "EnumerationSpan";
	/** * */
	public static final String DATATYPE_INTEGER = "IntegerSpan";
	/** * */
	public static final String DATATYPE_NAME = "NameSpan";
	/** * */
	public static final String DATATYPE_NUMBER = "NumberSpan";
	/** * */
	public static final String DATATYPE_OPTION = "OptionSpan";
	/** * */
	public static final String DATATYPE_SHAPE = "ShapeSpan";
	/** * */
	public static final String DATATYPE_STRING = "StringSpan";
	/** * */
	public static final String DATATYPE_TIME = "TimeSpan";
	/** * */
	public static final String DATATYPE_XYPAIR = "XYPairSpan";

	/**
	 * Enumeration for Priority 7.1.1.1 Used by e.g. JDFSpanBase
	 */
	/** @deprecated */
	@Deprecated
	/** * */public static final String PRIORITY_UNKNOWN = "Unknown";
	/** * */
	public static final String PRIORITY_NONE = "None";
	/** * */
	public static final String PRIORITY_SUGGESTED = "Suggested";
	/** * */
	public static final String PRIORITY_REQUIRED = "Required";

	/**
	 * Enumeration for ProcessUsage Used by e.g JDFNode
	 */
	/** @deprecated */
	@Deprecated
	/** * */public static final String PROCESSUSAGE_UNKNOWN = "Unknown";
	/** * */
	public static final String PROCESSUSAGE_ANYINPUT = "AnyInput";
	/** * */
	public static final String PROCESSUSAGE_ANYOUTPUT = "AnyOutput";
	/** * */
	public static final String PROCESSUSAGE_ANY = "Any";
	/** * */
	public static final String PROCESSUSAGE_REJECTED = "Rejected";
	/** * */
	public static final String PROCESSUSAGE_ACCEPTED = "Accepted";
	/** * */
	public static final String PROCESSUSAGE_APPLICATION = "Application";
	/** * */
	public static final String PROCESSUSAGE_MARKS = "Marks";
	/** * */
	public static final String PROCESSUSAGE_DOCUMENT = "Document";
	/** * */
	public static final String PROCESSUSAGE_SURFACE = "Surface";
	/** * */
	public static final String PROCESSUSAGE_WASTE = "Waste";
	/** * */
	public static final String PROCESSUSAGE_GOOD = "Good";
	/** * */
	public static final String PROCESSUSAGE_PROOF = "Proof";
	/** * */
	public static final String PROCESSUSAGE_INPUT = "Input";
	/** * */
	public static final String PROCESSUSAGE_PLATE = "Plate";
	/** * */
	public static final String PROCESSUSAGE_COVER = "Cover";
	/** * */
	public static final String PROCESSUSAGE_BOOKBLOCK = "BookBlock";
	/** * */
	public static final String PROCESSUSAGE_BOX = "Box";
	/** * */
	public static final String PROCESSUSAGE_COVERMATERIAL = "CoverMaterial";
	/** * */
	public static final String PROCESSUSAGE_SPINEBOARD = "SpineBoard";
	/** * */
	public static final String PROCESSUSAGE_COVERBOARD = "CoverBoard";
	/** * */
	public static final String PROCESSUSAGE_CASE = "Case";
	/** * */
	public static final String PROCESSUSAGE_FRONTENDSHEET = "FrontEndSheet";
	/** * */
	public static final String PROCESSUSAGE_BACKENDSHEET = "BackEndSheet";
	/** * */
	public static final String PROCESSUSAGE_CHILD = "Child";
	/** * */
	public static final String PROCESSUSAGE_MOTHER = "Mother";
	/** * */
	public static final String PROCESSUSAGE_JACKET = "Jacket";
	/** * */
	public static final String PROCESSUSAGE_BOOK = "Book";
	/** * */
	public static final String PROCESSUSAGE_LABEL = "Label";
	/** * */
	public static final String PROCESSUSAGE_RINGBINDER = "RingBinder";
	/** * */
	public static final String PROCESSUSAGE_ANCESTOR = "Ancestor";

	/**
	 * Enumeration for Type Used by JDFNode
	 */
	/** * */
	public static final String EnumType_Prefix = "Type_";

	/** @deprecated */
	@Deprecated
	/** * */public static final String TYPE_UNKNOWN = "Unknown";
	/** * */
	public static final String TYPE_PRODUCT = "Product";
	/** * */
	public static final String TYPE_APPROVAL = "Approval";
	/** * */
	public static final String TYPE_BUFFER = "Buffer";
	/** * */
	public static final String TYPE_COMBINE = "Combine";
	/** * */
	public static final String TYPE_DELIVERY = "Delivery";
	/** * */
	public static final String TYPE_MANUALLABOR = "ManualLabor";
	/** * */
	public static final String TYPE_ORDERING = "Ordering";
	/** * */
	public static final String TYPE_QUALITYCONTROL = "QualityControl";
	/** * */
	public static final String TYPE_PACKING = "Packing";
	/** * */
	public static final String TYPE_RESOURCEDEFINITION = "ResourceDefinition";
	/** * */
	public static final String TYPE_SPLIT = "Split";
	/** * */
	public static final String TYPE_VERIFICATION = "Verification";
	/** * */
	public static final String TYPE_ASSETLISTCREATION = "AssetListCreation";
	/** * */
	public static final String TYPE_ASSETCOLLECTION = "AssetCollection";
	/** * */
	public static final String TYPE_BENDING = "Bending";
	/** * */
	public static final String TYPE_COLORCORRECTION = "ColorCorrection";
	/** * */
	public static final String TYPE_COLORSPACECONVERSION = "ColorSpaceConversion";
	/** * */
	public static final String TYPE_CONTACTCOPYING = "ContactCopying";
	/** * */
	public static final String TYPE_CONTONECALIBRATION = "ContoneCalibration";
	/** * */
	public static final String TYPE_CYLINDERLAYOUTPREPARATION = "CylinderLayoutPreparation";
	/** * */
	public static final String TYPE_DBDOCTEMPLATELAYOUT = "DBDocTemplateLayout";
	/** * */
	public static final String TYPE_DBTEMPLATEMERGING = "DBTemplateMerging";
	/** * */
	public static final String TYPE_DIGITALDELIVERY = "DigitalDelivery";
	/** * */
	public static final String TYPE_FILMTOPLATECOPYING = "FilmToPlateCopying";
	/** * */
	public static final String TYPE_FORMATCONVERSION = "FormatConversion";
	/** * */
	public static final String TYPE_IMAGEREPLACEMENT = "ImageReplacement";
	/** * */
	public static final String TYPE_IMAGESETTING = "ImageSetting";
	/** * */
	public static final String TYPE_IMPOSITION = "Imposition";
	/** * */
	public static final String TYPE_INKZONECALCULATION = "InkZoneCalculation";
	/** * */
	public static final String TYPE_INTERPRETING = "Interpreting";
	/** * */
	public static final String TYPE_LAYOUTELEMENTPRODUCTION = "LayoutElementProduction";
	/** * */
	public static final String TYPE_LAYOUTPREPARATION = "LayoutPreparation";
	/** * */
	public static final String TYPE_PDFTOPSCONVERSION = "PDFToPSConversion";
	/** * */
	public static final String TYPE_PDLCREATION = "PDLCreation";
	/** * */
	public static final String TYPE_PREFLIGHT = "Preflight";
	/** * */
	public static final String TYPE_PREVIEWGENERATION = "PreviewGeneration";
	/** * */
	public static final String TYPE_PROOFING = "Proofing";
	/** * */
	public static final String TYPE_PSTOPDFCONVERSION = "PSToPDFConversion";
	/** * */
	public static final String TYPE_RASTERREADING = "RasterReading";
	/** * */
	public static final String TYPE_RENDERING = "Rendering";
	/** * */
	public static final String TYPE_SCANNING = "Scanning";
	/** * */
	public static final String TYPE_SCREENING = "Screening";
	/** * */
	public static final String TYPE_SEPARATION = "Separation";
	/** * */
	public static final String TYPE_SOFTPROOFING = "SoftProofing";
	/** * */
	public static final String TYPE_STRIPPING = "Stripping";
	/** * */
	public static final String TYPE_TILING = "Tiling";
	/** * */
	public static final String TYPE_TRAPPING = "Trapping";
	/** * */
	public static final String TYPE_CONVENTIONALPRINTING = "ConventionalPrinting";
	/** * */
	public static final String TYPE_DIGITALPRINTING = "DigitalPrinting";
	/** * */
	public static final String TYPE_IDPRINTING = "IDPrinting";
	/** * */
	public static final String TYPE_ADHESIVEBINDING = "AdhesiveBinding";
	/** * */
	public static final String TYPE_BLOCKPREPARATION = "BlockPreparation";
	/** * */
	public static final String TYPE_BOXPACKING = "BoxPacking";
	/** * */
	public static final String TYPE_BOXFOLDING = "BoxFolding";
	/** * */
	public static final String TYPE_BUNDLING = "Bundling";
	/** * */
	public static final String TYPE_CASEMAKING = "CaseMaking";
	/** * */
	public static final String TYPE_CASINGIN = "CasingIn";
	/** * */
	public static final String TYPE_CHANNELBINDING = "ChannelBinding";
	/** * */
	public static final String TYPE_COILBINDING = "CoilBinding";
	/** * */
	public static final String TYPE_COLLECTING = "Collecting";
	/** * */
	public static final String TYPE_COVERAPPLICATION = "CoverApplication";
	/** * */
	public static final String TYPE_CREASING = "Creasing";
	/** * */
	public static final String TYPE_CUTTING = "Cutting";
	/** * */
	public static final String TYPE_DIVIDING = "Dividing";
	/** * */
	public static final String TYPE_EMBOSSING = "Embossing";
	/** * */
	public static final String TYPE_ENDSHEETGLUING = "EndSheetGluing";
	/** * */
	public static final String TYPE_FEEDING = "Feeding";
	/** * */
	public static final String TYPE_FOLDING = "Folding";
	/** * */
	public static final String TYPE_GATHERING = "Gathering";
	/** * */
	public static final String TYPE_GLUING = "Gluing";
	/** * */
	public static final String TYPE_HEADBANDAPPLICATION = "HeadBandApplication";
	/** * */
	public static final String TYPE_HOLEMAKING = "HoleMaking";
	/** * */
	public static final String TYPE_INSERTING = "Inserting";
	/** * */
	public static final String TYPE_JACKETING = "Jacketing";
	/** * */
	public static final String TYPE_LABELING = "Labeling";
	/** * */
	public static final String TYPE_LAMINATING = "Laminating";
	/** * */
	public static final String TYPE_LONGITUDINALRIBBONOPERATIONS = "LongitudinalRibbonOperations";
	/** * */
	public static final String TYPE_NUMBERING = "Numbering";
	/** * */
	public static final String TYPE_PALLETIZING = "Palletizing";
	/** * */
	public static final String TYPE_PERFORATING = "Perforating";
	/** * */
	public static final String TYPE_PLASTICCOMBBINDING = "PlasticCombBinding";
	/** * */
	public static final String TYPE_PRINTROLLING = "PrintRolling";
	/** * */
	public static final String TYPE_RINGBINDING = "RingBinding";
	/** * */
	public static final String TYPE_SADDLESTITCHING = "SaddleStitching";
	/** * */
	public static final String TYPE_SHAPECUTTING = "ShapeCutting";
	/** * */
	public static final String TYPE_SHRINKING = "Shrinking";
	/** * */
	public static final String TYPE_SIDESEWING = "SideSewing";
	/** * */
	public static final String TYPE_SPINEPREPARATION = "SpinePreparation";
	/** * */
	public static final String TYPE_SPINETAPING = "SpineTaping";
	/** * */
	public static final String TYPE_STACKING = "Stacking";
	/** * */
	public static final String TYPE_STITCHING = "Stitching";
	/** * */
	public static final String TYPE_STRAPPING = "Strapping";
	/** * */
	public static final String TYPE_STRIPBINDING = "StripBinding";
	/** * */
	public static final String TYPE_THREADSEALING = "ThreadSealing";
	/** * */
	public static final String TYPE_THREADSEWING = "ThreadSewing";
	/** * */
	public static final String TYPE_TRIMMING = "Trimming";
	/** * */
	public static final String TYPE_WEBINLINEFINISHING = "WebInlineFinishing";
	/** * */
	public static final String TYPE_WIRECOMBBINDING = "WireCombBinding";
	/** * */
	public static final String TYPE_WRAPPING = "Wrapping";
	/** * */
	public static final String TYPE_COMBINED = "Combined";
	/** * */
	public static final String TYPE_PROCESSGROUP = "ProcessGroup";

	/**
	 * Enumeration for NameColor Used by JDFElement
	 */
	/** * */
	public static final String NAMEDCOLOR_UNKNOWN = "Unknown";
	/** * */
	public static final String NAMEDCOLOR_WHITE = "White";
	/** * */
	public static final String NAMEDCOLOR_BLACK = "Black";
	/** * */
	public static final String NAMEDCOLOR_GRAY = "Gray";
	/** * */
	public static final String NAMEDCOLOR_RED = "Red";
	/** * */
	public static final String NAMEDCOLOR_YELLOW = "Yellow";
	/** * */
	public static final String NAMEDCOLOR_GREEN = "Green";
	/** * */
	public static final String NAMEDCOLOR_BLUE = "Blue";
	/** * */
	public static final String NAMEDCOLOR_TURQUOISE = "Turquoise";
	/** * */
	public static final String NAMEDCOLOR_VIOLET = "Violet";
	/** * */
	public static final String NAMEDCOLOR_ORANGE = "Orange";
	/** * */
	public static final String NAMEDCOLOR_BROWN = "Brown";
	/** * */
	public static final String NAMEDCOLOR_GOLD = "Gold";
	/** * */
	public static final String NAMEDCOLOR_SILVER = "Silver";
	/** * */
	public static final String NAMEDCOLOR_PINK = "Pink";
	/** * */
	public static final String NAMEDCOLOR_BUFF = "Buff";
	/** * */
	public static final String NAMEDCOLOR_IVORY = "Ivory";
	/** * */
	public static final String NAMEDCOLOR_GOLDENROD = "Goldenrod";
	/** * */
	public static final String NAMEDCOLOR_DARKWHITE = "DarkWhite";
	/** * */
	public static final String NAMEDCOLOR_DARKBLACK = "DarkBlack";
	/** * */
	public static final String NAMEDCOLOR_DARKGRAY = "DarkGray";
	/** * */
	public static final String NAMEDCOLOR_DARKRED = "DarkRed";
	/** * */
	public static final String NAMEDCOLOR_DARKYELLOW = "DarkYellow";
	/** * */
	public static final String NAMEDCOLOR_DARKGREEN = "DarkGreen";
	/** * */
	public static final String NAMEDCOLOR_DARKBLUE = "DarkBlue";
	/** * */
	public static final String NAMEDCOLOR_DARKTURQUOISE = "DarkTurquoise";
	/** * */
	public static final String NAMEDCOLOR_DARKVIOLET = "DarkViolet";
	/** * */
	public static final String NAMEDCOLOR_DARKORANGE = "DarkOrange";
	/** * */
	public static final String NAMEDCOLOR_DARKBROWN = "DarkBrown";
	/** * */
	public static final String NAMEDCOLOR_DARKGOLD = "DarkGold";
	/** * */
	public static final String NAMEDCOLOR_DARKSILVER = "DarkSilver";
	/** * */
	public static final String NAMEDCOLOR_DARKPINK = "DarkPink";
	/** * */
	public static final String NAMEDCOLOR_DARKBUFF = "DarkBuff";
	/** * */
	public static final String NAMEDCOLOR_DARKIVORY = "DarkIvory";
	/** * */
	public static final String NAMEDCOLOR_DARKGOLDENROD = "DarkGoldenrod";
	/** * */
	public static final String NAMEDCOLOR_DARKMUSTARD = "DarkMustard";
	/** * */
	public static final String NAMEDCOLOR_LIGHTWHITE = "LightWhite";
	/** * */
	public static final String NAMEDCOLOR_LIGHTBLACK = "LightBlack";
	/** * */
	public static final String NAMEDCOLOR_LIGHTGRAY = "LightGray";
	/** * */
	public static final String NAMEDCOLOR_LIGHTRED = "LightRed";
	/** * */
	public static final String NAMEDCOLOR_LIGHTYELLOW = "LightYellow";
	/** * */
	public static final String NAMEDCOLOR_LIGHTGREEN = "LightGreen";
	/** * */
	public static final String NAMEDCOLOR_LIGHTBLUE = "LightBlue";
	/** * */
	public static final String NAMEDCOLOR_LIGHTTURQUOISE = "LightTurquoise";
	/** * */
	public static final String NAMEDCOLOR_LIGHTVIOLET = "LightViolet";
	/** * */
	public static final String NAMEDCOLOR_LIGHTORANGE = "LightOrange";
	/** * */
	public static final String NAMEDCOLOR_LIGHTBROWN = "LightBrown";
	/** * */
	public static final String NAMEDCOLOR_LIGHTGOLD = "LightGold";
	/** * */
	public static final String NAMEDCOLOR_LIGHTSILVER = "LightSilver";
	/** * */
	public static final String NAMEDCOLOR_LIGHTPINK = "LightPink";
	/** * */
	public static final String NAMEDCOLOR_LIGHTBUFF = "LightBuff";
	/** * */
	public static final String NAMEDCOLOR_LIGHTIVORY = "LightIvory";
	/** * */
	public static final String NAMEDCOLOR_LIGHTGOLDENROD = "LightGoldenrod";
	/** * */
	public static final String NAMEDCOLOR_LIGHTMUSTARD = "LightMustard";
	/** * */
	public static final String NAMEDCOLOR_CLEARWHITE = "ClearWhite";
	/** * */
	public static final String NAMEDCOLOR_CLEARBLACK = "ClearBlack";
	/** * */
	public static final String NAMEDCOLOR_CLEARGRAY = "ClearGray";
	/** * */
	public static final String NAMEDCOLOR_CLEARRED = "ClearRed";
	/** * */
	public static final String NAMEDCOLOR_CLEARGREEN = "ClearGreen";
	/** * */
	public static final String NAMEDCOLOR_CLEARBLUE = "ClearBlue";
	/** * */
	public static final String NAMEDCOLOR_CLEARTURQUOISE = "ClearTurquoise";
	/** * */
	public static final String NAMEDCOLOR_CLEARVIOLET = "ClearViolet";
	/** * */
	public static final String NAMEDCOLOR_CLEARORANGE = "ClearOrange";
	/** * */
	public static final String NAMEDCOLOR_CLEARBROWN = "ClearBrown";
	/** * */
	public static final String NAMEDCOLOR_CLEARGOLD = "ClearGold";
	/** * */
	public static final String NAMEDCOLOR_CLEARSILVER = "ClearSilver";
	/** * */
	public static final String NAMEDCOLOR_CLEARPINK = "ClearPink";
	/** * */
	public static final String NAMEDCOLOR_CLEARBUFF = "ClearBuff";
	/** * */
	public static final String NAMEDCOLOR_CLEARIVORY = "ClearIvory";
	/** * */
	public static final String NAMEDCOLOR_CLEARGOLDENROD = "ClearGoldenrod";
	/** * */
	public static final String NAMEDCOLOR_CLEARMUSTARD = "ClearMustard";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKWHITE = "ClearDarkWhite";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKBLACK = "ClearDarkBlack";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKGRAY = "ClearDarkGray";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKRED = "ClearDarkRed";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKYELLOW = "ClearDarkYellow";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKGREEN = "ClearDarkGreen";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKBLUE = "ClearDarkBlue";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKTURQUOISE = "ClearDarkTurquoise";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKVIOLET = "ClearDarkViolet";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKORANGE = "ClearDarkOrange";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKBROWN = "ClearDarkBrown";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKGOLD = "ClearDarkGold";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKSILVER = "ClearDarkSilver";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKPINK = "ClearDarkPink";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKBUFF = "ClearDarkBuff";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKIVORY = "ClearDarkIvory";
	/** * */
	public static final String NAMEDCOLOR_CLEARDARKGOLDENROD = "ClearDarkGoldenrod";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTWHITE = "ClearLightWhite";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTBLACK = "ClearLightBlack";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTGRAY = "ClearLightGray";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTRED = "ClearLightRed";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTYELLOW = "ClearLightYellow";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTGREEN = "ClearLightGreen";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTBLUE = "ClearLightBlue";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTTURQUOISE = "ClearLightTurquoise";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTVIOLET = "ClearLightViolet";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTORANGE = "ClearLightOrange";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTBROWN = "ClearLightBrown";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTGOLD = "ClearLightGold";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTSILVER = "ClearLightSilver";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTPINK = "ClearLightPink";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTBUFF = "ClearLightBuff";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTIVORY = "ClearLightIvory";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTGOLDENROD = "ClearLightGoldenrod";
	/** * */
	public static final String NAMEDCOLOR_CLEARLIGHTMUSTARD = "ClearLightMustard";
	/** * */
	public static final String NAMEDCOLOR_MULTICOLOR = "MultiColor";
	/** * */
	public static final String NAMEDCOLOR_NOCOLOR = "NoColor";

	/**
	 * Enumeration for CleanUpMerge Used by JDFNode
	 */
	/** * */
	public static final String CLEANUPMERGE_NONE = "None";
	/** * */
	public static final String CLEANUPMERGE_REMOVERREFS = "RemoverRefs";
	/** * */
	public static final String CLEANUPMERGE_REMOVEALL = "RemoveAll";

	/** * */
	public static final String SETTINGSPOLICY_UNKNOWN = "Unknown";
	/** * */
	public static final String SETTINGSPOLICY_BESTEFFORT = "BestEffort";
	/** * */
	public static final String SETTINGSPOLICY_MUSTHONOR = "MustHonor";
	/** * */
	public static final String SETTINGSPOLICY_OPERATORINTERVENTION = "OperatorIntervention";

	/** * */
	public static final String ATTRIBUTETYPE_UNKNOWN = "Unknown";
	/** * */
	public static final String ATTRIBUTETYPE_ANY = "Any";
	/** * */
	public static final String ATTRIBUTETYPE_BOOLEAN = "boolean_";
	/** * */
	public static final String ATTRIBUTETYPE_DOUBLE = "double_";
	/** * */
	public static final String ATTRIBUTETYPE_INTEGER = "integer";
	/** * */
	public static final String ATTRIBUTETYPE_NMTOKEN = "NMTOKEN";
	/** * */
	public static final String ATTRIBUTETYPE_NMTOKENS = "NMTOKENS";
	/** * */
	public static final String ATTRIBUTETYPE_ENUMERATION = "enumeration";
	/** * */
	public static final String ATTRIBUTETYPE_ENUMERATIONS = "enumerations";
	/** * */
	public static final String ATTRIBUTETYPE_NUMBERRANGE = "NumberRange";
	/** * */
	public static final String ATTRIBUTETYPE_NUMBERRANGELIST = "NumberRangeList";
	/** * */
	public static final String ATTRIBUTETYPE_STRING = "string";
	/** * */
	public static final String ATTRIBUTETYPE_SHORTSTRING = "shortString";
	/** * */
	public static final String ATTRIBUTETYPE_INTEGERRANGE = "IntegerRange";
	/** * */
	public static final String ATTRIBUTETYPE_INTEGERLIST = "IntegerList";
	/** * */
	public static final String ATTRIBUTETYPE_INTEGERRANGELIST = "IntegerRangeList";
	/** * */
	public static final String ATTRIBUTETYPE_MATRIX = "matrix";
	/** * */
	public static final String ATTRIBUTETYPE_RECTANGLE = "rectangle";
	/** * */
	public static final String ATTRIBUTETYPE_XYPAIR = "XYPair";
	/** * */
	public static final String ATTRIBUTETYPE_URL = "URL";
	/** * */
	public static final String ATTRIBUTETYPE_ID = "ID";
	/** * */
	public static final String ATTRIBUTETYPE_DATETIME = "dateTime";
	/** * */
	public static final String ATTRIBUTETYPE_DURATION = "duration";
	/** * */
	public static final String ATTRIBUTETYPE_SHAPE = "shape";
	/** * */
	public static final String ATTRIBUTETYPE_IDREFS = "IDREFS";
	/** * */
	public static final String ATTRIBUTETYPE_IDREF = "IDREF";
	/** * */
	public static final String ATTRIBUTETYPE_RECTANGLERANGE = "RectangleRange";
	/** * */
	public static final String ATTRIBUTETYPE_RECTANGLERANGELIST = "RectangleRangeList";
	/** * */
	public static final String ATTRIBUTETYPE_DURATIONRANGE = "DurationRange";
	/** * */
	public static final String ATTRIBUTETYPE_DURATIONRANGELIST = "DurationRangeList";
	/** * */
	public static final String ATTRIBUTETYPE_DATETIMERANGE = "DateTimeRange";
	/** * */
	public static final String ATTRIBUTETYPE_DATETIMERANGELIST = "DateTimeRangeList";
	/** * */
	public static final String ATTRIBUTETYPE_SHAPERANGE = "ShapeRange";
	/** * */
	public static final String ATTRIBUTETYPE_SHAPERANGELIST = "ShapeRangeList";
	/** * */
	public static final String ATTRIBUTETYPE_NAMERANGE = "NameRange";
	/** * */
	public static final String ATTRIBUTETYPE_NAMERANGELIST = "NameRangeList";
	/** * */
	public static final String ATTRIBUTETYPE_URI = "URI";
	/** * */
	public static final String ATTRIBUTETYPE_REGEXP = "RegExp";
	/** * */
	public static final String ATTRIBUTETYPE_PDFPATH = "PDFPath";
	/** * */
	public static final String ATTRIBUTETYPE_NUMBERLIST = "NumberList";
	/** * */
	public static final String ATTRIBUTETYPE_XYPAIRRANGE = "XYPairRange";
	/** * */
	public static final String ATTRIBUTETYPE_XYPAIRRANGELIST = "XYPairRangeList";
	/** * */
	public static final String ATTRIBUTETYPE_XYRELATION = "XYRelation";
	/** * */
	public static final String ATTRIBUTETYPE_JDFJMFVERSION = "JDFJMFVersion";
	/** * */
	public static final String ATTRIBUTETYPE_LANGUAGE = "language";
	/** * */
	public static final String ATTRIBUTETYPE_CMYKCOLOR = "CMYKColor";
	/** * */
	public static final String ATTRIBUTETYPE_HEXBINARY = "hexBinary";
	/** * */
	public static final String ATTRIBUTETYPE_LABCOLOR = "LabColor";
	/** * */
	public static final String ATTRIBUTETYPE_LANGUAGES = "languages";
	/** * */
	public static final String ATTRIBUTETYPE_RGBCOLOR = "RGBColor";
	/** * */
	public static final String ATTRIBUTETYPE_TRANSFERFUNCTION = "TransferFunction";
	/** * */
	public static final String ATTRIBUTETYPE_XPATH = "XPath";

	/** * */
	public static final String CHANNELTYPE_PHONE = "Phone";
	/** * */
	public static final String CHANNELTYPE_EMAIL = "Email";
	/** * */
	public static final String CHANNELTYPE_FAX = "Fax";
	/** * */
	public static final String CHANNELTYPE_WWW = "WWW";
	/** * */
	public static final String CHANNELTYPE_JMF = "JMF";
	/** * */
	public static final String CHANNELTYPE_PRIVATEDIRECTORY = "PrivateDirectory";
	/** * */
	public static final String CHANNELTYPE_INSTANTMESSAGING = "InstantMessaging";

	/** * */
	public static final String CONTACTTYPES_ACCOUNTING = "Accounting";
	/** * */
	public static final String CONTACTTYPES_ADMINISTRATOR = "Administrator";
	/** * */
	public static final String CONTACTTYPES_APPROVER = "Approver";
	/** * */
	public static final String CONTACTTYPES_ARTRETURN = "ArtReturn";
	/** * */
	public static final String CONTACTTYPES_BILLING = "Billing";
	/** * */
	public static final String CONTACTTYPES_CUSTOMER = "Customer";
	/** * */
	public static final String CONTACTTYPES_DELIVERY = "Delivery";
	/** * */
	public static final String CONTACTTYPES_DELIVERYCHARGE = "DeliveryCharge";
	/** * */
	public static final String CONTACTTYPES_OWNER = "Owner";
	/** * */
	public static final String CONTACTTYPES_PICKUP = "Pickup";
	/** * */
	public static final String CONTACTTYPES_SENDER = "Sender";
	/** * */
	public static final String CONTACTTYPES_SUPPLIER = "Supplier";
	/** * */
	public static final String CONTACTTYPES_SURPLUSRETURN = "SurplusReturn";

	/**
	 * Enumeration for EnumPoolType Used by JDFElement
	 */
	/** * */
	public static final String POOLTYPE_UNKNOWN = "Unknown";
	/** * */
	public static final String POOLTYPE_RESOURCEPOOL = "ResourcePool";
	/** * */
	public static final String POOLTYPE_RESOURCELINKPOOL = "ResourceLinkPool";
	/** * */
	public static final String POOLTYPE_ANCESTORPOOL = "AncestorPool";
	/** * */
	public static final String POOLTYPE_AUDITPOOL = "AuditPool";
	/** * */
	public static final String POOLTYPE_RESOURCECMDPARAMS = "ResourceCmdParams";
	/** * */
	public static final String POOLTYPE_RESOURCEINFO = "ResourceInfo";
	/** * */
	public static final String POOLTYPE_PHASETIME = "PhaseTime";
	/** * */
	public static final String POOLTYPE_PRODUCTIONINTENT = "ProductionIntent";

	/** * */
	public static final String IMAGEFILTER_UNKNOWN = "Unknown";
	/** * */
	public static final String IMAGEFILTER_CCITTFAXENCODE = "CCITTFaxEncode";
	/** * */
	public static final String IMAGEFILTER_DCTENCODE = "DCTEncode";
	/** * */
	public static final String IMAGEFILTER_FLATENCODE = "FlateEncode";

	// MIME types
	/** * */
	public static final String MIME_PNG = "image/x-png";
	/** * */
	public static final String MIME_TIFF = "image/tiff";
	/** * */
	public static final String MIME_PDF = "application/pdf";
	/** * */
	public static final String MIME_JPG = "image/jpeg";
	/** * */
	public static final String MIME_PS = "application/postscript";
	/** * */
	public static final String MIME_EPS = "application/postscript";
	/** * */
	public static final String MIME_JDF = "application/vnd.cip4-jdf+xml";
	/** * */
	public static final String MIME_JMF = "application/vnd.cip4-jmf+xml";
	/** * */
	public static final String MIME_CIP3 = "application/vnd.cip3-ppf";
	/** * */
	public static final String MIME_PPML = "application/vnd.podi-ppml+xml";
	/** * */
	public static final String MIME_TEXTUNKNOWN = "text/unknown";
	/** * */
	public static final String MIME_TEXTXML = "text/xml";

	// JDFSheet Surface definitions
	/** * */
	public static final String SIDE_FRONT = "Front";
	/** * */
	public static final String SIDE_BACK = "Back";

	/** * */
	public static final String ORIENTATION_UNKNOWN = "Unknown";
	/** * */
	public static final String ORIENTATION_FLIP0 = "Flip0";
	/** * */
	public static final String ORIENTATION_FLIP90 = "Flip90";
	/** * */
	public static final String ORIENTATION_FLIP180 = "Flip180";
	/** * */
	public static final String ORIENTATION_FLIP270 = "Flip270";
	/** * */
	public static final String ORIENTATION_ROTATE0 = "Rotate0";
	/** * */
	public static final String ORIENTATION_ROTATE90 = "Rotate90";
	/** * */
	public static final String ORIENTATION_ROTATE180 = "Rotate180";
	/** * */
	public static final String ORIENTATION_ROTATE270 = "Rotate270";

	/** * */
	public static final String XYRELATION_UNKNOWN = "Unknown";
	/** * */
	public static final String XYRELATION_GT = "gt";
	/** * */
	public static final String XYRELATION_GE = "ge";
	/** * */
	public static final String XYRELATION_EQ = "eq";
	/** * */
	public static final String XYRELATION_LE = "le";
	/** * */
	public static final String XYRELATION_LT = "lt";
	/** * */
	public static final String XYRELATION_NE = "ne";

	/** * */
	public static final String SEPARATION_UNKNOWN = "Unknown";
	/** * */
	public static final String SEPARATION_CYAN = "cyan";
	/** * */
	public static final String SEPARATION_MAGENTA = "magenta";
	/** * */
	public static final String SEPARATION_YELLOW = "yellow";
	/** * */
	public static final String SEPARATION_BLACK = "black";
	/** * */
	public static final String SEPARATION_RED = "red";
	/** * */
	public static final String SEPARATION_GREEN = "green";
	/** * */
	public static final String SEPARATION_BLUE = "blue";
	/** * */
	public static final String SEPARATION_ORANGE = "orange";
	/** * */
	public static final String SEPARATION_SPOT = "spot";
	/** * */
	public static final String SEPARATION_VARNISH = "varnish";

	/** * */
	public static final String FITSVALUE_UNKNOWN = "Unknown";
	/** * */
	public static final String FITSVALUE_ALLOWED = "Allowed";
	/** * */
	public static final String FITSVALUE_PRESENT = "Present";

	/** * */
	public static final String CLASS_UNKNOWN = "Unknown";
	/** * */
	public static final String CLASS_CONSUMABLE = "Consumable";
	/** * */
	public static final String CLASS_HANDLING = "Handling";
	/** * */
	public static final String CLASS_INTENT = "Intent";
	/** * */
	public static final String CLASS_IMPLEMENTATION = "Implementation";
	/** * */
	public static final String CLASS_PARAMETER = "Parameter";
	/** * */
	public static final String CLASS_PLACEHOLDER = "PlaceHolder";
	/** * */
	public static final String CLASS_QUANTITY = "Quantity";

	/** * */
	public static final String ATTRIBUTEVALIDITY_UNKNOWN = "Unknown";
	/** * */
	public static final String ATTRIBUTEVALIDITY_NONE = "None";
	/** * */
	public static final String ATTRIBUTEVALIDITY_REQUIRED = "Required";
	/** * */
	public static final String ATTRIBUTEVALIDITY_OPTIONAL = "Optional";
	/** * */
	public static final String ATTRIBUTEVALIDITY_DEPRECATED = "Deprecated";
	/** * */
	public static final String ATTRIBUTEVALIDITY_MISSINGOPTIONAL = "MissingOptional";
	/** * */
	public static final String ATTRIBUTEVALIDITY_MISSINGREQUIRED = "MissingRequired";
	/** * */
	public static final String ATTRIBUTEVALIDITY_ANY = "Any";

	/** * */
	public static final String ELEMENTVALIDITY_UNKNOWN = "Unknown";
	/** * */
	public static final String ELEMENTVALIDITY_NONE = "None";
	/** * */
	public static final String ELEMENTVALIDITY_REQUIRED = "Required";
	/** * */
	public static final String ELEMENTVALIDITY_OPTIONAL = "Optional";
	/** * */
	public static final String ELEMENTVALIDITY_DEPRECATED = "Deprecated";
	/** * */
	public static final String ELEMENTVALIDITY_SINGLEOPTIONAL = "SingleOptional";
	/** * */
	public static final String ELEMENTVALIDITY_SINGLEREQUIRED = "SingleRequired";
	/** * */
	public static final String ELEMENTVALIDITY_SINGLEDEPRECATED = "SingleDeprecated";
	/** * */
	public static final String ELEMENTVALIDITY_DUMMY = "Dummy";

	/** * */
	public static final String VALIDATIONLEVEL_UNKNOWN = "Unknown";
	/** * */
	public static final String VALIDATIONLEVEL_NONE = "None";
	/** * */
	public static final String VALIDATIONLEVEL_CONSTRUCT = "Construct";
	/** * */
	public static final String VALIDATIONLEVEL_COMPLETE = "Complete";
	/** * */
	public static final String VALIDATIONLEVEL_INCOMPLETE = "Incomplete";
	/** * */
	public static final String VALIDATIONLEVEL_RECURSIVEINCOMPLETE = "RecursiveIncomplete";
	/** * */
	public static final String VALIDATIONLEVEL_RECURSIVECOMPLETE = "RecursiveComplete";

	/** * */
	public static final String WORKTYPEDETAILS_USERERROR = "UserError";
	/** * */
	public static final String WORKTYPEDETAILS_EQUIPMENTMALFUNCTION = "EquipmentMalfunction";
	/** * */
	public static final String WORKTYPEDETAILS_RESOURCEDAMAGED = "ResourceDamaged";
	/** * */
	public static final String WORKTYPEDETAILS_INTERNALCHANGE = "InternalChange";
	/** * */
	public static final String WORKTYPEDETAILS_CUSTOMERREQUEST = "CustomerRequest";

	/** * */
	public static final String BOOLEAN_UNKNOWN = "Unknown";
	/** * */
	public static final String BOOLEAN_TRUE = "true";
	/** * */
	public static final String BOOLEAN_FALSE = "false";

	/** * */
	public static final String REGEXP_HEXBINARY = "([0-9a-fA-F]{2})+";
	/** * */
	public static final String REGEXP_EMAIL = "(mailto:)?([_.a-zA-Z0-9\\-])+[@]([_.a-zA-Z0-9\\-])+[.]([_.a-zA-Z0-9\\-])+";
	/** * */
	public static final String REGEXP_PHONE = "(tel:)?([+])?(([0-9./\\-])|[(]([0-9./\\-])[)])+";

	/** * */
	public static final String JDFELEMENT = "JDFElement";
	/** * */
	public static final String JDFNODE = "JDFNode";

}
