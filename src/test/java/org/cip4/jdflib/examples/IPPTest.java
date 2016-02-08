/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of
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
package org.cip4.jdflib.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDigitalPrintingParams.EnumSides;
import org.cip4.jdflib.auto.JDFAutoLayoutPreparationParams.EnumBindingEdge;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumBackCoatings;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumFrontCoatings;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoStitchingParams.EnumStitchType;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFLayoutPreparationParams;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFDigitalPrintingParams;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFFoldingParams;
import org.cip4.jdflib.resource.process.postpress.JDFStitchingParams;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class IPPTest extends JDFTestCaseBase
{
	private JDFNode n;
	private JDFComponent comp;
	private JDFRunList ruli;
	private JDFResourceLink rlComp;
	private JDFDigitalPrintingParams digiParams;
	private JDFMedia med;
	private JDFResourceLink rlMedia;
	private JDFLayoutPreparationParams layoutPrep;

	/**
	 * 
	 */
	@Override
	public void setUp()
	{
		KElement.setLongID(false);
		JDFAudit.setStaticAgentName(null);
		JDFAudit.setStaticAgentVersion(null);
		JDFAudit.setStaticAuthor(null);

		n = new JDFDoc(ElementName.JDF).getJDFRoot();
		n.setJobID("JobID");
		n.setType(EnumType.Combined);
		n.setTypes(new VString("Interpreting Rendering LayoutPreparation Imposition ColorSpaceConversion DigitalPrinting", null));
		comp = (JDFComponent) n.addResource(ElementName.COMPONENT, null, EnumUsage.Output, null, null, null, null);
		rlComp = n.getLink(comp, null);

		layoutPrep = (JDFLayoutPreparationParams) n.addResource(ElementName.LAYOUTPREPARATIONPARAMS, null, EnumUsage.Input, null, null, null, null);

		digiParams = (JDFDigitalPrintingParams) n.addResource(ElementName.DIGITALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);

		med = (JDFMedia) n.appendMatchingResource(ElementName.MEDIA, EnumProcessUsage.AnyInput, null);
		med.setResStatus(EnumResStatus.Available, false);
		med.setMediaType(EnumMediaType.Paper);
		med.setDescriptiveName("Input Media to print on");

		rlMedia = n.getLink(med, null);
		ruli = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
	}

	/**
	 * 1. Simplex A4 job (dead simple job)
	 */
	public void testSimpleA4()
	{
		med.setDimensionCM(new JDFXYPair(21, 29.7));
		med.setFrontCoatings(EnumFrontCoatings.None);
		med.setBackCoatings(EnumBackCoatings.None);

		digiParams.setSides(EnumSides.OneSidedFront);
		ruli.addPDF("file://host/dir/name.pdf", 0, -1);
		rlComp.setAmount(1);
		comp.refMedia(med);
		layoutPrep.setNumberUp(new JDFXYPair(1, 1));
		n.write2File(sm_dirTestDataTemp + "IPP_Simple.jdf");
	}

	/**
	 * 2. Duplex US Letter job on Glossy media with multiple copies, staple, and letter fold (covers media type, duplex, copies, and common finishing operations for billing/marketing)
	 */
	public void testLetterGloss()
	{
		n.addTypes(EnumType.Stitching);
		n.addTypes(EnumType.Folding);
		med.setDimensionInch(new JDFXYPair(8.5, 11));
		med.setFrontCoatings(EnumFrontCoatings.Glossy);
		med.setBackCoatings(EnumBackCoatings.Glossy);
		digiParams.setSides(EnumSides.TwoSided);
		ruli.addPDF("file://host/dir/name.pdf", 0, -1);
		rlComp.setAmount(42);
		comp.refMedia(med);
		layoutPrep.setNumberUp(new JDFXYPair(1, 1));
		layoutPrep.setSides(org.cip4.jdflib.auto.JDFAutoLayoutPreparationParams.EnumSides.TwoSidedFlipY);
		layoutPrep.setPageDistributionScheme("Sequential");

		JDFStitchingParams stitchParams = (JDFStitchingParams) n.addResource(ElementName.STITCHINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		stitchParams.setStitchType(EnumStitchType.Corner);
		JDFFoldingParams foldParams = (JDFFoldingParams) n.addResource(ElementName.FOLDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		foldParams.setFoldCatalog(6, 1);
		n.write2File(sm_dirTestDataTemp + "IPP_GlossLetter.jdf");
	}

	/**
	 * 3. Duplex A3 job with multiple copies, saddle stitch, booklet fold/imposition, and cover (more complex mapping for reports/small publications)
	 */
	public void testBooklet()
	{
		n.addTypes(EnumType.Stitching);

		String coverIndex = "0 1 -2 -1";
		JDFMedia mCover = (JDFMedia) med.addPartition(EnumPartIDKey.RunIndex, coverIndex);
		med.setDimensionCM(new JDFXYPair(42, 29.7));
		mCover.setFrontCoatings(EnumFrontCoatings.Glossy);
		mCover.setBackCoatings(EnumBackCoatings.Glossy);
		mCover.setDescriptiveName("Cover Paper");

		String bodyIndex = "2 ~ -3";
		JDFMedia mBody = (JDFMedia) med.addPartition(EnumPartIDKey.RunIndex, bodyIndex);
		mBody.setFrontCoatings(EnumFrontCoatings.None);
		mBody.setBackCoatings(EnumBackCoatings.None);
		mBody.setDescriptiveName("Body Paper");

		digiParams.setSides(EnumSides.TwoSided);
		JDFDigitalPrintingParams dpCover = (JDFDigitalPrintingParams) digiParams.addPartition(EnumPartIDKey.RunIndex, coverIndex);
		dpCover.refMedia(mCover);
		JDFDigitalPrintingParams dpBody = (JDFDigitalPrintingParams) digiParams.addPartition(EnumPartIDKey.RunIndex, bodyIndex);
		dpBody.refMedia(mBody);

		ruli.addPDF("file://host/dir/name.pdf", 0, -1);

		rlComp.setAmount(42);
		comp.setDimensions(new JDFShape(21, 29.7, 0.3));

		layoutPrep.setNumberUp(new JDFXYPair(2, 1));
		layoutPrep.setSides(org.cip4.jdflib.auto.JDFAutoLayoutPreparationParams.EnumSides.TwoSidedFlipY);
		layoutPrep.setPageDistributionScheme("Saddle");
		layoutPrep.setBindingEdge(EnumBindingEdge.Left);

		JDFLayoutPreparationParams lppCover = (JDFLayoutPreparationParams) layoutPrep.addPartition(EnumPartIDKey.RunIndex, coverIndex);
		lppCover.refMedia(mCover);
		JDFLayoutPreparationParams lppBody = (JDFLayoutPreparationParams) layoutPrep.addPartition(EnumPartIDKey.RunIndex, bodyIndex);
		lppBody.refMedia(mBody);

		JDFStitchingParams stitchParams = (JDFStitchingParams) n.addResource(ElementName.STITCHINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		stitchParams.setStitchType(EnumStitchType.Saddle);
		n.write2File(sm_dirTestDataTemp + "IPP_Booklet.jdf");
	}
}
