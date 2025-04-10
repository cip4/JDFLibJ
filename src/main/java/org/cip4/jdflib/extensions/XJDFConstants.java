/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import org.cip4.jdflib.core.ElementName;

public abstract class XJDFConstants
{
	public static final String AreaUse = "AreaUse";
	public static final String AssemblingIntent = "AssemblingIntent";
	public static final String AssemblyItem = "AssemblyItem";

	public static final String AuditCreated = "AuditCreated";
	public static final String AuditNotification = "AuditNotification";
	public static final String AuditProcessRun = "AuditProcessRun";
	public static final String AuditResource = "AuditResource";
	public static final String AuditStatus = "AuditStatus";

	public static final String BackCIETint = "BackCIETint";
	public static final String BackCIEWhiteness = "BackCIEWhiteness";
	public static final String BackCoating = "BackCoating";
	public static final String BackLabColorValue = "BackLabColorValue";
	public static final String BackUse = "BackUse";

	public static final String BindIn = "BindIn";
	public static final String BindingScrewSet = "BindingScrewSet";
	public static final String BinderySignatureID = "BinderySignatureID";
	public static final String BinderySignatureIDs = "BinderySignatureIDs";
	public static final String BlowIn = "BlowIn";

	public static final String ChannelBindingDetails = "ChannelBindingDetails";
	public static final String ChildProduct = "ChildProduct";
	public static final String ChildRef = "ChildRef";
	public static final String ChildRefs = "ChildRefs";
	public static final String Coating = "Coating";
	public static final String CoatingDetail = "CoatingDetail";
	public static final String CoilBindingDetails = "CoilBindingDetails";
	public static final String ColorantName = "ColorantName";
	public static final String CompanyID = "CompanyID";
	public static final String ContactType = "ContactType";
	public static final String Container = "Container";
	public static final String ContactRefs = "ContactRefs";

	public static final String Content = "Content";
	public static final String ContentCheckIntent = "ContentCheckIntent";
	public static final String ContentRef = "ContentRef";
	public static final String ContentRefs = "ContentRefs";
	public static final String ContentStatus = "ContentStatus";
	public static final String Cover = "Cover";

	public static final String DateSpread = "DateSpread";
	public static final String Dependent = "Dependent";
	public static final String DropID = "DropID";
	/**
	 * @deprecated
	 */
	@Deprecated
	public static final String DROP_ID = DropID;

	public static final String EndCustomer = "EndCustomer";
	public static final String ExpansionBox = "ExpansionBox";
	public static final String ExternalID = "ExternalID";

	public static final String Face = "Face";
	public static final String FilmRef = "FilmRef";
	public static final String FolioFrom = "FolioFrom";
	public static final String FolioTo = "FolioTo";

	public static final String GlossValue = "GlossValue";

	public static final String Header = "Header";
	public static final String HolePattern = "HolePattern";

	public static final String InkType = "InkType";
	public static final String Intent = "Intent";
	public static final String IsRoot = "IsRoot";
	public static final String ItemRef = "ItemRef";

	@Deprecated
	public static final String LayerType = "LayerType";
	public static final String LooseBinding = "LooseBinding";
	public static final String LooseBindingParams = "LooseBindingParams";

	public static final String MediaRef = "MediaRef";
	public static final String Metadata = "Metadata";
	public static final String ModifyQueueEntry = "ModifyQueueEntry";
	public static final String ModifyQueueEntryParams = "ModifyQueueEntryParams";
	public static final String ModuleInfo = "ModuleInfo";
	public static final String ModuleIDs = "ModuleIDs";
	public static final String ModuleCondition = "ModuleCondition";
	public static final String ModuleStatus = "ModuleStatus";
	public static final String MultiPageFold = "MultiPageFold";

	public static final String OrderQuantity = "OrderQuantity";

	public static final String PaperRef = "PaperRef";
	public static final String Parent = "Parent";
	public static final String ParentID = "ParentID";
	public static final String PartContext = "PartContext";
	public static final String PartWaste = "PartWaste";
	public static final String Pattern = "Pattern";
	public static final String PlacedObject = "PlacedObject";
	public static final String PlacedQuantity = "PlacedQuantity";
	public static final String PlateRef = "PlateRef";

	public static final String PositionRef = "PositionRef";
	public static final String Positions = "Positions";
	public static final String PreflightItem = "PreflightItem";
	public static final String PrintableArea = "PrintableArea";
	public static final String PrintedWaste = "PrintedWaste";
	public static final String PrintStandard = "PrintStandard";
	public static final String Process = "Process";
	public static final String ProcessList = "ProcessList";
	@Deprecated
	public static final String ProcessTypes = "ProcessType"; // TODO remove
	public static final String Product = "Product";
	public static final String ProductList = "ProductList";
	public static final String ProductPart = "ProductPart";
	public static final String ProofPaperRef = "ProofPaperRef";

	public static final String QualityMeasurement = "QualityMeasurement";
	public static final String QueueEntryIDs = "QueueEntryIDs";

	public static final String ReinforceBind = "ReinforceBind";
	public static final String ReinforceColor = "ReinforceColor";
	public static final String ReinforceColorDetails = "ReinforceColorDetails";
	public static final String ReinforceTabs = "ReinforceTabs";
	public static final String Resource = "Resource";
	public static final String ResourceSet = "ResourceSet";
	public static final String ResponseModes = "ResponseModes";
	public static final String ResponseNotification = "ResponseNotification";
	public static final String ResponseResource = "ResponseResource";
	public static final String ResponseStatus = "ResponseStatus";
	public static final String RestApiBaseURL = "RestApiBaseURL";

	public static final String Role = "Role";

	public static final String ScrewBinding = "ScrewBinding";
	public static final String ScrewBindingDetails = "ScrewBindingDetails";
	public static final String Separations = "Separations";

	public static final String ShapeDefRef = "ShapeDefRef";
	public static final String ShapeDimension = "ShapeDimension";

	public static final String SheetHeightMax = "SheetHeightMax";
	public static final String SheetHeightMin = "SheetHeightMin";
	public static final String SheetWidthMax = "SheetWidthMax";
	public static final String SheetOptimizingReport = "SheetOptimizingReport";
	public static final String SheetWidthMin = "SheetWidthMin";

	public static final String SignalNotification = "SignalNotification";
	public static final String SignalResource = "SignalResource";
	public static final String SignalStatus = "SignalStatus";

	public static final String SourceFrequencyMax = "SourceFrequencyMax";
	public static final String SourceFrequencyMin = "SourceFrequencyMin";
	public static final String StickOn = "StickOn";
	public static final String Surface = ElementName.SURFACE;
	public static final String SurfaceColor = "SurfaceColor";

	public static final String TransferCurveName = "TransferCurveName";
	public static final String TrimSpine = "TrimSpine";
	public static final String TypeDetails = "TypeDetails";

	public static final String UniquePositions = "UniquePositions";
	public static final String UniqueUse = "UniqueUse";

	public static final String VariableType = "VariableType";
	public static final String VerificationResult = "VerificationResult";
	public static final String VolumeUse = "VolumeUse";

	public static final String Waste = "Waste";
	public static final String WasteDetails = "WasteDetails";
	public static final String WasteQuantity = "WasteQuantity";

	public static final String XJDF = "XJDF";
	public static final String XJDFPREFIX = XJDF.toLowerCase();
	public static final String XJMF = "XJMF";
	public static final String XJMFURL = "XJMFURL";

}
