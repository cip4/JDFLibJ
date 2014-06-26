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

import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.auto.JDFAutoLayoutIntent.EnumSides;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.intent.JDFBindingIntent;
import org.cip4.jdflib.resource.intent.JDFColorIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.intent.JDFFoldingIntent;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.intent.JDFPackingIntent;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.cip4.jdflib.span.JDFIntegerSpan;
import org.cip4.jdflib.span.JDFSpanBindingType.EnumSpanBindingType;
import org.cip4.jdflib.span.JDFSpanCoatings.EnumSpanCoatings;
import org.cip4.jdflib.util.JDFDate;

/**
 * @author Rainer Prosi class that generates golden tickets based on ICS levels etc
 */
public class ProductGoldenTicket extends MISGoldenTicket
{
	// private JDFMediaIntent mediaIntent;

	/**
	 * @param _icsLevel
	 * @param jdfVersion
	 * @param _jmfLevel
	 * @param _misLevel
	 */
	public ProductGoldenTicket(final int _icsLevel, final EnumVersion jdfVersion, final int _jmfLevel, final int _misLevel)
	{
		super(_misLevel, jdfVersion, _jmfLevel);
		cols.set(4, "Blue"); // want hd blue here
		icsLevel = _icsLevel;
		devID = null;
	}

	/**
	 * initializes this node to a given ICS version
	 * 
	 *  
	 */
	@Override
	public void init()
	{

		if (icsLevel < 0)
		{
			return;
		}
		// String icsTag="MISCPS_L"+icsLevel+"-"+theVersion.getName();
		// theNode.appendAttribute(AttributeName.ICSVERSIONS, icsTag, null, " ",
		// true);
		if (!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
		{
			theNode.setDescriptiveName("Product Golden Ticket Example Job - version: " + JDFAudit.software());
		}
		theNode.setType(EnumType.Product);
		JDFComponent out = (JDFComponent) theNode.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, 0);
		if (theNode.isJDFRoot())
		{
			out.setComponentType(EnumComponentType.FinalProduct, null);
		}
		else
		{
			out.setComponentType(EnumComponentType.PartialProduct, null);
		}

		super.init();
	}

	/**
	 * @param childNode 
	 * @param li 
	 * @param productType 
	 * @return 
	 *  
	 */
	protected JDFComponent initOutputComponent(final JDFNode childNode, final JDFLayoutIntent li, final String productType)
	{
		final JDFComponent outComp = (JDFComponent) childNode.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, 0);
		if (productType == null)
		{
			outComp.setComponentType(EnumComponentType.FinalProduct, EnumComponentType.Sheet);

		}
		else
		{
			outComp.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);
			outComp.setProductType(productType);

		}
		theNode.getResource(ElementName.LAYOUTINTENT, null, 0);
		final JDFShape s = li.getFinishedDimensions().getActual();
		outComp.setDimensions(s);
		if (theNode != childNode)
		{
			theNode.getResourcePool().moveElement(outComp, null);
			theNode.linkResource(outComp, EnumUsage.Input, null);
		}
		return outComp;

	}

	/**
	 * initialize deliveryintent and also output component
	 * 
	 * @param amount
	 * @return 
	 */
	protected JDFDeliveryIntent initDeliveryIntent(final int amount)
	{
		final JDFDeliveryIntent di = (JDFDeliveryIntent) theNode.addResource(ElementName.DELIVERYINTENT, EnumUsage.Input);
		final JDFDate d = new JDFDate();
		d.addOffset(0, 0, 0, 7);
		di.appendRequired().setPreferred(d);
		if (amount > 0)
		{
			final JDFComponent outComp = (JDFComponent) theNode.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			final JDFDropItemIntent dit = di.appendDropIntent().appendDropItemIntent();
			dit.refElement(outComp);
			dit.setAmount(amount);
			final JDFResourceLink rl = theNode.getLink(outComp, null);
			rl.setAmount(amount, null);
		}

		di.setResStatus(EnumResStatus.Available, false);
		di.preferredToActual();
		return di;
	}

	/**
	 */
	@Override
	protected JDFNodeInfo initNodeInfo()
	{
		super.initNodeInfo();
		final JDFNodeInfo ni = theNode.getCreateNodeInfo();
		ni.setStart(null);
		return ni;
	}

	/**
	 * @param firstame 
	 * @param lastame 
	 * @param companyName 
	 * @param jobName 
	 * @return 
	 */
	protected JDFCustomerInfo initCustomerInfo(final String firstame, final String lastame, final String companyName, final String jobName)
	{
		final JDFCustomerInfo ci = theNode.getCreateCustomerInfo();
		ci.setCustomerJobName(jobName);
		JDFContact c = ci.getContactWithContactType(EnumContactType.Customer.getName(), 0);
		if (c == null)
		{
			c = ci.appendContact(EnumContactType.Customer);
		}
		c.setPerson(firstame, lastame);
		if (companyName != null)
		{
			c.getCreateCompany().setOrganizationName(companyName);
		}
		return ci;
	}

	/**
	 * @param node 
	 * @param gsm 
	 * @param coating 
	 * @param brand TODO
	 * @return 
	 * 
	 */
	protected JDFMediaIntent initMediaIntent(final JDFNode node, final double gsm, final EnumSpanCoatings coating, final String brand)
	{
		final JDFMediaIntent mi = (JDFMediaIntent) node.addResource(ElementName.MEDIAINTENT, EnumUsage.Input);
		mi.getCreateWeight().setPreferred(gsm);
		mi.getCreateFrontCoatings().setPreferred(coating);
		mi.setBrand(brand);
		mi.setResStatus(EnumResStatus.Available, false);
		mi.preferredToActual();
		return mi;
	}

	/**
	 * @param node 
	 * @param front 
	 * @param back 
	 * @param coatings 
	 * @return 
	 * 
	 */
	protected JDFColorIntent initColorIntent(final JDFNode node, final int front, final int back, final String coatings)
	{
		final JDFColorIntent ci = (JDFColorIntent) node.addResource(ElementName.COLORINTENT, EnumUsage.Input);
		final VElement vci = new VElement();
		nCols[0] = front;
		nCols[1] = back;
		if (front != back && back != 0)
		{
			vci.add(ci.addPartition(EnumPartIDKey.Side, EnumSide.Front));
			vci.add(ci.addPartition(EnumPartIDKey.Side, EnumSide.Back));
		}
		else
		{
			vci.add(ci);
		}

		for (int i = 0; i < vci.size(); i++)
		{
			final int colors = i == 0 ? front : back;
			final VString newCols = new VString();
			for (int j = 0; j < colors; j++)
			{
				newCols.add(cols.elementAt(j));
			}
			final JDFColorIntent cip = (JDFColorIntent) vci.elementAt(i);
			cip.appendColorsUsed().setSeparations(newCols);
		}
		ci.setResStatus(EnumResStatus.Available, false);
		ci.preferredToActual();
		return ci;
	}

	/**
	 * @param node 
	 * @param xCM 
	 * @param yCM 
	 * @param pages 
	 * @param sides 
	 * @return 
	 * 
	 */
	protected JDFLayoutIntent initLayoutIntent(final JDFNode node, final double xCM, final double yCM, final int pages, final int sides)
	{
		final JDFLayoutIntent loi = (JDFLayoutIntent) node.addResource(ElementName.LAYOUTINTENT, EnumUsage.Input);
		loi.getCreateFinishedDimensions().setPreferred(xCM * 72 / 2.54, yCM * 72 / 2.54, 0);
		loi.getCreatePages().setPreferred(pages);
		loi.setSides(sides == 2 ? EnumSides.TwoSidedHeadToHead : EnumSides.OneSided);
		loi.setResStatus(EnumResStatus.Available, false);
		loi.preferredToActual();
		return loi;
	}

	/**
	 * @param node 
	 * @param foldCatalog 
	 * @return 
	 * 
	 */
	protected JDFFoldingIntent initFoldingIntent(final JDFNode node, final String foldCatalog)
	{
		final JDFFoldingIntent fi = (JDFFoldingIntent) node.addResource(ElementName.FOLDINGINTENT, EnumUsage.Input);
		fi.appendFoldingCatalog().setPreferred(foldCatalog);
		fi.setResStatus(EnumResStatus.Available, false);
		fi.preferredToActual();
		return fi;
	}

	/**
	 * 
	 */
	public void createPostCards()
	{
		initCustomerInfo(null, null, "Volkswagen AG", "Freeway postcards");
		theNode.setDescriptiveName("7.5.1   Postcards 4c/4c");
		initMediaIntent(theNode, 300, EnumSpanCoatings.Coated, null);
		final JDFLayoutIntent li = initLayoutIntent(theNode, 14.8, 10.5, 16, 2);
		initColorIntent(theNode, 4, 4, null);
		initOutputComponent(theNode, li, null);
		initDeliveryIntent(5000);
	}

	/**
	 * 
	 */
	public void createMultiLabels()
	{
		theNode.setDescriptiveName("Multi Label Product");
		final JDFDeliveryIntent diBig = initDeliveryIntent(0);
		theNode.removeResource(ElementName.CUSTOMERINFO, 0);

		final ProductGoldenTicket gtLabel1 = new ProductGoldenTicket(0, EnumVersion.Version_1_3, 0, 0);
		final JDFNode n1 = theNode.addProduct();
		gtLabel1.assign(n1);
		addKid(gtLabel1);

		gtLabel1.initCustomerInfo("Johann", "ReweEinkäufer", "Rewe", "Mineralwasser label");
		initMediaIntent(n1, 24.4, EnumSpanCoatings.Coated, "38DL247 38");
		JDFLayoutIntent li = initLayoutIntent(n1, 92, 28.3, 1, 1);
		initColorIntent(n1, 6, 0, null);
		initOutputComponent(n1, li, "Label");
		JDFDeliveryIntent di = gtLabel1.initDeliveryIntent(5000);
		diBig.moveElement(di.getDropIntent(0), null);
		gtLabel1.getNode().removeResource("DeliveryIntent", 0);

		final ProductGoldenTicket gtLabel2 = new ProductGoldenTicket(0, EnumVersion.Version_1_3, 0, 0);

		final JDFNode n2 = theNode.addProduct();
		gtLabel2.assign(n2);
		addKid(gtLabel2);

		gtLabel2.initCustomerInfo("Franzi", "KulmbachEinkäufer", "Kulmbach", "Weissbier label");
		initMediaIntent(n2, 24.4, EnumSpanCoatings.Coated, "38DL247 38");
		li = initLayoutIntent(n2, 11.4, 5, 1, 1);
		gtLabel2.cols = new VString("Green Gold Black Yellow Gold Thermo", null);
		gtLabel2.initColorIntent(n2, 6, 0, null);
		initOutputComponent(n2, li, "Label");
		di = gtLabel2.initDeliveryIntent(10000);
		diBig.moveElement(di.getDropIntent(0), null);
		gtLabel2.getNode().removeResource("DeliveryIntent", 0);
	}

	/**
	 * 
	 */
	public void createHarley()
	{
		initCustomerInfo(null, null, "ABC Promotions Company", "Speed-Point Harley Poster");
		theNode.setDescriptiveName("7.5.4   Poster 4c/0c");
		initMediaIntent(theNode, 170, EnumSpanCoatings.Glossy, null);
		final JDFLayoutIntent li = initLayoutIntent(theNode, 43, 32.6, 1, 1);
		initColorIntent(theNode, 4, 0, null);
		initOutputComponent(theNode, li, null);
		initDeliveryIntent(5000);
	}

	/**
	 * @param cover
	 * @param body
	 * @param numStitches 
	 * @return
	 */
	protected JDFBindingIntent initBindingIntent(final JDFComponent cover, final JDFComponent body, final int numStitches)
	{
		final JDFBindingIntent bi = (JDFBindingIntent) theNode.addResource(ElementName.BINDINGINTENT, EnumUsage.Input);
		if (numStitches > 0)
		{
			bi.appendSaddleStitching().appendStitchNumber().setActual(numStitches);
			bi.appendBindingType().setActual(EnumSpanBindingType.SaddleStitch);
		}
		theNode.linkResource(cover, EnumUsage.Input, EnumProcessUsage.Cover);
		theNode.linkResource(body, EnumUsage.Input, null);
		bi.setResStatus(EnumResStatus.Available, false);
		bi.preferredToActual();
		return bi;

	}

	/**
	 * 
	 */
	public void createAddressBook()
	{
		initCustomerInfo(null, null, "Art Point", "Address Pocketbook");
		theNode.setDescriptiveName("7.5.2   A5 brochure 4c/4c, 4pg Cover, 32 pg Text");
		final JDFNode cover = addJDFNode(theNode, EnumType.Product);
		cover.setDescriptiveName("Address Book Cover");

		initMediaIntent(cover, 200, EnumSpanCoatings.Glossy, null);
		final JDFLayoutIntent li = initLayoutIntent(cover, 14.8, 21, 4, 2);
		final JDFColorIntent ci = initColorIntent(cover, 4, 4, null);
		final JDFComponent cCover = initOutputComponent(cover, li, "Cover");
		cCover.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);

		final JDFNode body = addJDFNode(theNode, EnumType.Product);
		body.setDescriptiveName("Address Book Body");
		body.linkResource(ci, EnumUsage.Input, null);
		initMediaIntent(body, 135, EnumSpanCoatings.Coated, null);
		initLayoutIntent(body, 14.8, 21, 32, 2);
		final JDFComponent cBody = initOutputComponent(body, li, "Body");
		cBody.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);

		initBindingIntent(cCover, cBody, 2);

		initOutputComponent(theNode, li, null);
		initDeliveryIntent(5000);
	}

	/**
	 * 
	 */
	public void createHDCity()
	{
		initCustomerInfo(null, null, "Heidelberger Druckmaschinen AG", "Heidelberg A4 brochure");
		theNode.setDescriptiveName("7.5.5   A4 brochure with spot colors, 4pg Cover 6c/4c, 32 pg Text 4c/4c");
		final JDFNode cover = addJDFNode(theNode, EnumType.Product);
		cover.setDescriptiveName("HD Brochure Cover");

		initMediaIntent(cover, 200, EnumSpanCoatings.Glossy, null);
		final JDFLayoutIntent li = initLayoutIntent(cover, 21, 29.7, 4, 2);
		initColorIntent(cover, 6, 4, null);
		final JDFComponent cCover = initOutputComponent(cover, li, "Cover");
		cCover.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);

		final JDFNode body = addJDFNode(theNode, EnumType.Product);
		body.setDescriptiveName("HD Brochure Body");
		initColorIntent(body, 4, 4, null);
		initMediaIntent(body, 135, EnumSpanCoatings.Coated, null);
		initLayoutIntent(body, 21, 29.7, 32, 2);
		final JDFComponent cBody = initOutputComponent(body, li, "Body");
		cBody.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);

		initBindingIntent(cCover, cBody, 2);

		initOutputComponent(theNode, li, null);
		initDeliveryIntent(5000);
	}

	/**
	 * 
	 */
	public void createWatches()
	{
		initCustomerInfo(null, null, "ABC Promotions Company", "Sinn watches double flap");
		theNode.setDescriptiveName("7.5.3 Flyer with special fold 4c/4c");

		initMediaIntent(theNode, 170, EnumSpanCoatings.Coated, null);
		final JDFLayoutIntent li = initLayoutIntent(theNode, 21, 29.7, 6, 2);
		initColorIntent(theNode, 4, 4, null);
		final JDFFoldingIntent fi = initFoldingIntent(theNode, "F6-3");
		fi.setDescriptiveName("F6-3 should be the gate fold");
		initOutputComponent(theNode, li, null);
		initDeliveryIntent(5000);
	}

	/**
	 * product intent for the drupa flower job
	 * 
	 * @throws Exception
	 */
	public void createDrupaFlower() throws Exception
	{
		initCustomerInfo("Jane", "Customer", "Messe Düsseldorf", "CIP4 Drupa Flower Demo Job");
		theNode.setDescriptiveName("Drupa Flower Brochure, 4pg Cover 5c5c, 48 pg Text 5c5c");

		final JDFNode cover = addJDFNode(theNode, EnumType.Product);
		cover.setDescriptiveName("Drupa Flower Brochure Cover");

		initMediaIntent(cover, 200, EnumSpanCoatings.Glossy, "Luxocard 2 SB");
		final JDFLayoutIntent li = initLayoutIntent(cover, 23.3, 21.6, 4, 2);
		initColorIntent(cover, 5, 5, null);
		final JDFComponent cCover = initOutputComponent(cover, li, "Cover");
		cCover.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);

		final JDFNode body = addJDFNode(theNode, EnumType.Product);
		body.setDescriptiveName("Drupa Flower Brochure Body");
		initColorIntent(body, 5, 5, "Cyan Magenta Yellow Black Blue");
		initMediaIntent(body, 150, EnumSpanCoatings.Glossy, "Scheufelen BVS dull BB");
		initLayoutIntent(body, 23.3, 21.6, 48, 2);
		final JDFComponent cBody = initOutputComponent(body, li, "Body");
		cBody.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);

		initBindingIntent(cCover, cBody, 2);

		initOutputComponent(theNode, li, null);
		initDeliveryIntent(5000);
		final JDFPackingIntent pi = (JDFPackingIntent) theNode.getCreateResource(ElementName.PACKINGINTENT, EnumUsage.Input, 0);
		pi.setDescriptiveName("want cartons of products");
		final JDFIntegerSpan is = pi.appendCartonQuantity();
		is.setRange(new JDFIntegerRangeList(new JDFIntegerRange(50, 500)));
	}

	@Override
	protected void runphases(final int pgood, final int pwaste, final boolean bOutAvail, final boolean bFirst)
	{
		// nop for products
	}

}
