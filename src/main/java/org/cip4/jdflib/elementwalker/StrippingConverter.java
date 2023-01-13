/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.elementwalker;

import java.util.List;

import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoBinderySignature.EnumBinderySignatureType;
import org.cip4.jdflib.auto.JDFAutoContentObject.EnumAnchor;
import org.cip4.jdflib.auto.JDFAutoImageShift.EnumPositionX;
import org.cip4.jdflib.auto.JDFAutoImageShift.EnumPositionY;
import org.cip4.jdflib.auto.JDFAutoLayoutPreparationParams.EnumFinishingOrder;
import org.cip4.jdflib.auto.JDFAutoLayoutPreparationParams.EnumRotate;
import org.cip4.jdflib.auto.JDFAutoLayoutPreparationParams.EnumSides;
import org.cip4.jdflib.auto.JDFAutoStripCellParams;
import org.cip4.jdflib.auto.JDFAutoStripMark.EnumMarkSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFImageShift;
import org.cip4.jdflib.resource.JDFLayoutPreparationParams;
import org.cip4.jdflib.resource.JDFModified;
import org.cip4.jdflib.resource.JDFPageCell;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFExternalImpositionTemplate;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFSignatureCell;
import org.cip4.jdflib.resource.process.JDFStripMark;
import org.cip4.jdflib.util.StringUtil;

/**
 * sub-class that converts LayoutPreparationParams to the corresponding StrippingParams, Assembly and BinderySignature
 *
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 *         June 4, 2009
 */
public class StrippingConverter
{

	/**
	 *
	 */
	private final JDFLayoutPreparationParams layPrepParams;
	private JDFAssembly assembly = null;
	private JDFBinderySignature binderySignature = null;
	private JDFStrippingParams strippingParams = null;
	private final JDFNode parent;

	/**
	 * @param layoutPreparationParams
	 * @param n
	 */
	public StrippingConverter(final JDFLayoutPreparationParams layoutPreparationParams, final JDFNode n)
	{
		layPrepParams = layoutPreparationParams;
		parent = n == null ? layPrepParams.getParentJDF() : n;
	}

	/**
	 * @return the binderySignature
	 */
	public JDFBinderySignature getBinderySignature()
	{
		return binderySignature != null ? binderySignature : strippingParams.getBinderySignature();
	}

	/**
	 *
	 */
	public void convert()
	{
		convertParentNode();
		convertAssembly();
		strippingParams = (JDFStrippingParams) parent.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
		strippingParams.setAutomated(true);
		strippingParams.clonePartitions(layPrepParams, null);
		final List<JDFResource> vThis = layPrepParams.getLeafArray(false);
		for (final KElement e : vThis)
		{
			new StrippingConverter((JDFLayoutPreparationParams) e, parent).convertLeaf();
		}
	}

	/**
	 *
	 *
	 */
	protected void convertLeaf()
	{
		strippingParams = (JDFStrippingParams) parent.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		final JDFAttributeMap partMap = layPrepParams.getPartMap();
		if (partMap != null && partMap.size() > 0)
			strippingParams = (JDFStrippingParams) strippingParams.getPartition(partMap, null);
		convertStrippingParams();
		convertBinderySignature();
		setSignatureCell();
		setStripCellParams();

		removeObsolete();
	}

	/**
	 * remove this and any references to myself
	 */
	private void removeObsolete()
	{
		final VElement v = layPrepParams.getLinksAndRefs(true, true);
		if (v != null)
		{
			for (final KElement e : v)
			{
				e.deleteNode();
			}
		}

		layPrepParams.deleteNode(); // zapp myself in the end
	}

	/**
	 * convert all the attributes that go to strippingparams and related elements
	 */
	private void convertStrippingParams()
	{
		final JDFMedia media = layPrepParams.getMedia();
		if (media != null)
		{
			media.makeRootResource(null, null, true);
			strippingParams.refElement(media);
		}
		final JDFExternalImpositionTemplate template = layPrepParams.getExternalImpositionTemplate();
		strippingParams.copyElement(template, null);
		strippingParams.copyElement(layPrepParams.getFitPolicy(), null);
		setPosition();
		setStripMarks(layPrepParams.getFrontMarkList(), EnumMarkSide.Front);
		setStripMarks(layPrepParams.getBackMarkList(), EnumMarkSide.Back);
	}

	/**
	 *
	 */
	void setStripCellParams()
	{
		final JDFPageCell pageCell = layPrepParams.getPageCell();
		if (pageCell != null)
		{
			final JDFXYPair ts = pageCell.getTrimSize();
			if (ts != null)
			{
				strippingParams.getCreateStripCellParams().setTrimSize(ts);
			}
		}
		final EnumSides sides = layPrepParams.getSides();
		if (EnumSides.TwoSidedFlipY.equals(sides))
		{
			strippingParams.getCreateStripCellParams().setSides(JDFAutoStripCellParams.EnumSides.TwoSidedHeadToHead);
		}
		else if (EnumSides.TwoSidedFlipX.equals(sides))
		{
			strippingParams.getCreateStripCellParams().setSides(JDFAutoStripCellParams.EnumSides.TwoSidedHeadToFoot);
		}
		else if (EnumSides.OneSidedBackFlipX.equals(sides) || EnumSides.OneSidedBackFlipY.equals(sides))
		{
			strippingParams.getCreateStripCellParams().setSides(JDFAutoStripCellParams.EnumSides.OneSidedBack);
		}
		else
		{
			strippingParams.getCreateStripCellParams().setSides(JDFAutoStripCellParams.EnumSides.OneSided);
		}

	}

	/**
	 * copy data from PageCell to SignatureCell
	 */
	void setSignatureCell()
	{
		final JDFPageCell pageCell = layPrepParams.getPageCell();
		if (pageCell == null)
			return;
		final JDFSignatureCell signatureCell = getBinderySignature().getCreateSignatureCell(0);
		signatureCell.setOrientation(getSigCellOrientation(pageCell.getRotate()));
		convertImageShift(pageCell, signatureCell);
		signatureCell.copyElement(pageCell.getFitPolicy(), null);
	}

	/**
	 *
	 * convert the image shift to
	 *
	 * @param pageCell
	 * @param signatureCell
	 */
	private void convertImageShift(final JDFPageCell pageCell, final JDFSignatureCell signatureCell)
	{
		final JDFImageShift imageShift = pageCell.getImageShift();
		if (imageShift == null)
			return;
		final EnumPositionX posX = imageShift.getPositionX();
		final EnumPositionY posY = imageShift.getPositionY();
		if (posX == null || posY == null)
			return;

		String anchor = posY.getName() + posX.getName();
		if ("CenterCenter".equals(anchor))
			anchor = "Center";
		final EnumAnchor ea = EnumAnchor.getEnum(anchor);
		if (ea != null)
			signatureCell.setAttribute(AttributeName.ANCHOR, anchor);

	}

	/**
	 *
	 * @param rotate
	 * @return
	 */
	private org.cip4.jdflib.auto.JDFAutoSignatureCell.EnumOrientation getSigCellOrientation(final org.cip4.jdflib.auto.JDFAutoPageCell.EnumRotate rotate)
	{
		if (org.cip4.jdflib.auto.JDFAutoPageCell.EnumRotate.Rotate90.equals(rotate))
			return org.cip4.jdflib.auto.JDFAutoSignatureCell.EnumOrientation.Left;
		else if (org.cip4.jdflib.auto.JDFAutoPageCell.EnumRotate.Rotate180.equals(rotate))
			return org.cip4.jdflib.auto.JDFAutoSignatureCell.EnumOrientation.Down;
		else if (org.cip4.jdflib.auto.JDFAutoPageCell.EnumRotate.Rotate270.equals(rotate))
			return org.cip4.jdflib.auto.JDFAutoSignatureCell.EnumOrientation.Right;

		return org.cip4.jdflib.auto.JDFAutoSignatureCell.EnumOrientation.Up;
	}

	/**
	 * creates a position element for the binderysignature
	 */
	private void setPosition()
	{
		final JDFIntegerList sr = layPrepParams.getStepRepeat();
		if (sr == null)
		{
			addSinglePosition(1, 1, 0, null);
		}
		else
		{
			final int iX = sr.getInt(0);
			final int iY = sr.getInt(1);
			final JDFXYPair numberUp = layPrepParams.getNumberUp();
			final int total = (int) (numberUp == null ? 1 : numberUp.getX() * numberUp.getY());
			for (int i = 0; i < total; i++)
			{
				addSinglePosition(iX, iY, i, numberUp);
			}
		}
	}

	/**
	 *
	 * add a single position to the respective strippingparams
	 *
	 * @param x
	 * @param y
	 * @param n
	 * @param numberUp
	 */
	JDFPosition addSinglePosition(final int x, final int y, final int n, final JDFXYPair numberUp)
	{
		final String bsName = getBSName(x, y, n, numberUp);
		final JDFStrippingParams sp = (JDFStrippingParams) (bsName == null ? strippingParams
				: strippingParams.getCreatePartition(new JDFAttributeMap(AttributeName.BINDERYSIGNATURENAME, bsName), null));
		final JDFPosition position = sp.appendPosition();
		position.setRelativeBox(getRelativeBox(x, y, n, numberUp));
		EnumRotate r = layPrepParams.getRotate();
		JDFMatrix m = new JDFMatrix(org.cip4.jdflib.core.JDFElement.EnumOrientation.getEnum(r.getName()), 0, 0);
		final EnumSides sides = layPrepParams.getSides();
		if (EnumSides.OneSidedBackFlipX.equals(sides))
		{
			m.concat(new JDFMatrix(org.cip4.jdflib.core.JDFElement.EnumOrientation.Flip0, 0, 0));
		}
		else if (EnumSides.OneSidedBackFlipY.equals(sides))
		{
			m.concat(new JDFMatrix(org.cip4.jdflib.core.JDFElement.EnumOrientation.Flip180, 0, 0));
		}
		position.setAttribute(AttributeName.ORIENTATION, m.getOrientation().getName());
		return position;
	}

	/**
	 *
	 *
	 * @param srx
	 * @param sry
	 * @param n
	 * @param numberUp
	 * @return
	 */
	private JDFRectangle getRelativeBox(final int srx, final int sry, final int n, final JDFXYPair numberUp)
	{
		final int total = (int) (numberUp == null ? 1 : numberUp.getX() * numberUp.getY());

		if (total == 1 || numberUp == null)
			return new JDFRectangle(0, 0, 1, 1);
		final double dx = 1.0 / numberUp.getX();
		final double dy = 1.0 / numberUp.getY();
		double x = n % (int) numberUp.getX();
		x /= numberUp.getX();
		double y = n / (int) numberUp.getX();
		y /= numberUp.getY();
		return new JDFRectangle(x, y, x + dx, y + dy);
	}

	/**
	 * get binderysignaturename partition key based on steprepeat
	 *
	 * @param srx
	 * @param sry
	 * @param n
	 * @param numberUp
	 * @return
	 */
	private String getBSName(final int srx, final int sry, final int n, final JDFXYPair numberUp)
	{
		final int total = (int) (numberUp == null ? 1 : numberUp.getX() * numberUp.getY());
		if (total == 1)
			return null;
		int x = n % (int) numberUp.getX();
		x /= srx;
		int y = n / (int) numberUp.getX();
		y /= sry;
		return "BS_" + (1 + x) + "_" + (1 + y);
	}

	/**
	 * @param marks
	 * @param side
	 */
	private void setStripMarks(final VString marks, final EnumMarkSide side)
	{
		for (final String markName : marks)
		{
			final JDFStripMark sm = strippingParams.appendStripMark();
			sm.setMarkName(markName);
			sm.setMarkSide(side);
		}
	}

	/**
	 * convert all the attributes that go to binderysignature and related elements
	 */
	private void convertBinderySignature()
	{
		binderySignature = strippingParams.appendBinderySignature();
		binderySignature.makeRootResource(null, null, true);
		binderySignature.copyAttribute(AttributeName.BINDINGEDGE, layPrepParams);
		// steprepeat requires n binderysignatures
		if (layPrepParams.hasAttribute(AttributeName.STEPREPEAT))
			binderySignature.setNumberUp(1, 1);
		else
			binderySignature.copyAttribute(AttributeName.NUMBERUP, layPrepParams);

		binderySignature.copyAttribute(AttributeName.FOLDCATALOG, layPrepParams);

		final String pageDistribution = layPrepParams.getPageDistributionScheme();
		final String foldcatalog = StringUtil.getNonEmpty(layPrepParams.getFoldCatalog());
		if ("Sequential".equals(pageDistribution) && (foldcatalog == null || "F2-1".equals(foldcatalog)))
			binderySignature.setBinderySignatureType(EnumBinderySignatureType.Grid);
		else
			binderySignature.setBinderySignatureType(EnumBinderySignatureType.Fold);

	}

	/**
	 * convert all the attributes that go to binderysignature and related elements
	 */
	private void convertAssembly()
	{
		assembly = (JDFAssembly) parent.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
		assembly.copyAttribute(AttributeName.BINDINGSIDE, layPrepParams, AttributeName.BINDINGEDGE, null, null);

		final EnumFinishingOrder fo = layPrepParams.getFinishingOrder();
		if (EnumFinishingOrder.FoldCollect.equals(fo))
		{
			assembly.setOrder(EnumOrder.Collecting);
		}
		else if (EnumFinishingOrder.FoldGather.equals(fo))
		{
			assembly.setOrder(EnumOrder.Gathering);
		}
		else if (EnumFinishingOrder.Gather.equals(fo))
		{
			assembly.setOrder(EnumOrder.Gathering);
		}
		else if (EnumFinishingOrder.GatherFold.equals(fo))
		{
			final String pageDistribution = layPrepParams.getPageDistributionScheme();
			if ("Saddle".equals(pageDistribution))
				assembly.setOrder(EnumOrder.Collecting);
			else
				assembly.setOrder(EnumOrder.Gathering);
		}
	}

	/**
	 * @return the parent node
	 */
	private JDFNode convertParentNode()
	{
		final VString types = parent.getTypes();
		if (types == null && EnumType.LayoutPreparation.equals(parent.getEnumType()))
		{
			parent.setType(EnumType.Stripping);
		}
		else if (types != null)
		{
			final int n = types.index(EnumType.LayoutPreparation.getName());
			if (n >= 0)
			{
				types.set(n, EnumType.Stripping.getName());
				parent.setTypes(types);
			}
		}
		final JDFModified mod = parent.getCreateAuditPool().addModified(null, null);
		mod.setDescriptiveName("Automatic LayoutPrep to Stripping Conversion");
		return parent;
	}

	/**
	 * @return the assembly
	 */
	public JDFAssembly getAssembly()
	{
		return assembly;
	}

	/**
	 * @return the strippingParams
	 */
	public JDFStrippingParams getStrippingParams()
	{
		return strippingParams;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "StrippingConverter [layPrepParams=" + layPrepParams + ", strippingParams=" + strippingParams + ", assembly=" + assembly + "]";
	}

}