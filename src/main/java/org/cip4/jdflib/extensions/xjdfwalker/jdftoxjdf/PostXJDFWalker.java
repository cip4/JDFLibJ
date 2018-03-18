/*
 * The CIP4 Software License, Version 1.0
 *
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.extensions.AuditPoolHelper;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.MessageResourceHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFHeadBandApplicationParams;
import org.cip4.jdflib.resource.JDFLaminatingParams;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.intent.JDFArtDelivery;
import org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFDrop;
import org.cip4.jdflib.resource.process.JDFIdentical;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFSignatureCell;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.cip4.jdflib.resource.process.postpress.JDFStitchingParams;
import org.cip4.jdflib.resource.process.postpress.JDFThreadSewingParams;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * some generic postprocessing that is better done on the XJDF after JDF to XJDF Conversion such as merging stripping and Layout
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class PostXJDFWalker extends BaseElementWalker
{
	/**
	 * if true merge stripping and layout
	 */
	private boolean mergeLayout;
	protected XJDFHelper newRootHelper;
	/**
	 * if false, intents are never partitioned
	 */
	private boolean bIntentPartition;
	/**
	 * if false, all deliveryintents and artdeliveryintents are converted to the respective process resources
	 */
	private boolean bDeliveryIntent;
	private boolean retainAll;

	/**
	 *
	 * @return
	 */
	boolean isMergeLayout()
	{
		return mergeLayout;
	}

	/**
	 *
	 * @param mergeLayout
	 */
	void setMergeLayout(final boolean mergeLayout)
	{
		this.mergeLayout = mergeLayout;
	}

	/**
	 *
	 * @return
	 */
	boolean isIntentPartition()
	{
		return bIntentPartition;
	}

	/**
	 *
	 * @param bIntentPartition
	 */
	void setIntentPartition(final boolean bIntentPartition)
	{
		this.bIntentPartition = bIntentPartition;
	}

	/**
	 *
	 * @return
	 */
	boolean isDeliveryIntent()
	{
		return bDeliveryIntent;
	}

	/**
	 *
	 * @param bDeliveryIntent
	 */
	void setDeliveryIntent(final boolean bDeliveryIntent)
	{
		this.bDeliveryIntent = bDeliveryIntent;
	}

	private boolean removeSignatureName;

	/**
	 *
	 * @return
	 */
	boolean isRemoveSignatureName()
	{
		return removeSignatureName;
	}

	/**
	 *
	 * @param removeSignatureName
	 */
	void setRemoveSignatureName(final boolean removeSignatureName)
	{
		this.removeSignatureName = removeSignatureName;
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkElement extends BaseWalker
	{
		@SuppressWarnings("synthetic-access")
		public WalkElement()
		{
			super(getFactory());
		}

		public String getCoating(final String coating)
		{
			if (coating == null)
				return null;
			if ("glossy".equalsIgnoreCase(coating))
				return "Gloss";
			return coating;
		}

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			updateNamespaces(xjdf);
			if (!retainAll)
			{
				updateAttributes(xjdf);
			}
			return xjdf;
		}

		/**
		 * rename hook
		 *
		 * @param xjdf
		 */
		void updateAttributes(final KElement xjdf)
		{
			// nop
		}

		/**
		 *
		 * @param xjdf
		 */
		void updateNamespaces(final KElement xjdf)
		{
			if (xjdf.hasAttribute(AttributeName.XMLNS))
				xjdf.removeAttribute(AttributeName.XMLNS);
			if (xjdf.getNamespaceURI().equals(JDFElement.getSchemaURL()))
				xjdf.setNamespaceURI(XJDF20.getSchemaURL());
		}

	}

	/**
	 * class that ensures that we do not have signaturename partitions
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkPart extends WalkElement
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return VString.getVString(ElementName.PART, null);
		}

		/**
		 *
		 */
		public WalkPart()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#updateAttributes(org.cip4.jdflib.core.KElement)
		 */
		@Override
		void updateAttributes(final KElement part)
		{
			if (retainAll)
				return;

			if (isRemoveSignatureName())
			{
				part.removeAttribute(AttributeName.SIGNATURENAME);
			}

			part.removeAttribute(AttributeName.BINDERYSIGNATUREPAGINATIONINDEX);
			part.removeAttribute(AttributeName.BUNDLEITEMINDEX);
			part.removeAttribute(AttributeName.CELLINDEX);
			part.removeAttribute(AttributeName.CONDITION);
			part.removeAttribute(AttributeName.DOCCOPIES);
			part.renameAttribute(AttributeName.DOCRUNINDEX, AttributeName.RUNINDEX);
			part.renameAttribute(AttributeName.DOCSHEETINDEX, AttributeName.SHEETINDEX);

			part.removeAttribute(AttributeName.DELIVERYUNIT0);
			part.removeAttribute(AttributeName.DELIVERYUNIT1);
			part.removeAttribute(AttributeName.DELIVERYUNIT2);
			part.removeAttribute(AttributeName.DELIVERYUNIT3);
			part.removeAttribute(AttributeName.DELIVERYUNIT4);
			part.removeAttribute(AttributeName.DELIVERYUNIT5);
			part.removeAttribute(AttributeName.DELIVERYUNIT6);
			part.removeAttribute(AttributeName.DELIVERYUNIT7);
			part.removeAttribute(AttributeName.DELIVERYUNIT8);
			part.removeAttribute(AttributeName.DELIVERYUNIT9);
			part.removeAttribute(AttributeName.DOCTAGS);
			part.removeAttribute(AttributeName.EDITION);
			part.removeAttribute(AttributeName.EDITIONVERSION);
			part.removeAttribute(AttributeName.FOUNTAINNUMBER);
			part.removeAttribute(AttributeName.ITEMNAMES);
			part.removeAttribute(AttributeName.LAYERIDS);

			part.renameAttribute(AttributeName.METADATA0, AttributeName.METADATA);
			part.removeAttribute(AttributeName.METADATA1);
			part.removeAttribute(AttributeName.METADATA2);
			part.removeAttribute(AttributeName.METADATA3);
			part.removeAttribute(AttributeName.METADATA4);
			part.removeAttribute(AttributeName.METADATA5);
			part.removeAttribute(AttributeName.METADATA6);
			part.removeAttribute(AttributeName.METADATA7);
			part.removeAttribute(AttributeName.METADATA8);
			part.removeAttribute(AttributeName.METADATA9);

			part.removeAttribute(AttributeName.PAGETAGS);
			part.removeAttribute(AttributeName.PLATELAYOUT);
			part.removeAttribute(AttributeName.PREFLIGHTRULE);
			part.removeAttribute(AttributeName.RUNPAGE);
			part.removeAttribute(AttributeName.RUNPAGERANGE);
			part.removeAttribute(AttributeName.RUNSET);
			part.removeAttribute(AttributeName.RUNTAGS);
			part.removeAttribute(AttributeName.SECTIONINDEX);
			part.removeAttribute(AttributeName.SETCOPIES);
			part.renameAttribute(AttributeName.SETDOCINDEX, AttributeName.DOCINDEX);
			part.renameAttribute(AttributeName.SETRUNINDEX, AttributeName.RUNINDEX);
			part.renameAttribute(AttributeName.SETSHEETINDEX, AttributeName.SHEETINDEX);

			part.removeAttribute(AttributeName.SUBRUN);
			part.renameAttribute(AttributeName.WEBPRODUCT, AttributeName.PRODUCTPART);
			part.removeAttribute(AttributeName.WEBSETUP);
			super.updateAttributes(part);
		}
	}

	/**
	 * class that cleans up redundant partition keys
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkPartAmount extends WalkElement
	{
		/**
		 *
		 */
		public WalkPartAmount()
		{
			super();
		}

		/**
		 * @param partAmount
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement pa, final KElement dummy)
		{
			final JDFPartAmount partAmount = (JDFPartAmount) pa;
			if (!retainAll)
			{
				removeRedundantPartKeys(partAmount);
			}
			return super.walk(partAmount, dummy);
		}

		/**
		 *
		 * @param partAmount
		 */
		private void removeRedundantPartKeys(final JDFPartAmount partAmount)
		{
			final VElement parentParts = getParentParts(partAmount);
			if (parentParts != null)
			{
				final VString keys = new VString();
				for (final KElement e : parentParts)
				{
					final JDFPart p = (JDFPart) e;
					final JDFAttributeMap partMap = p.getPartMap();
					final VString nextKeys = partMap.getKeys();
					keys.appendUnique(nextKeys);
				}
				VJDFAttributeMap vPA = partAmount.getPartMapVector();
				if (vPA == null)
					vPA = new VJDFAttributeMap();
				if (vPA != null && keys != null && keys.size() > 0)
				{
					vPA.removeKeys(keys);
				}
				if (vPA.isEmpty())
					vPA = null;

				partAmount.setPartMapVector(vPA);
				// the parts are new and need to be moved into the correct namespace
				final VElement v = partAmount.getParts();
				for (final KElement e : v)
				{
					updateNamespaces(e);
				}
			}
		}

		/**
		 *
		 * @param part
		 * @return
		 */
		private VElement getParentParts(final KElement part)
		{
			return part.getXPathElementVector("../../Part", 0);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return VString.getVString(ElementName.PARTAMOUNT, null);
		}

		/**
		 *
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(final KElement e)
		{
			return (e instanceof JDFPartAmount);
		}
	}

	/**
	 * class that cleans up redundant partition keys
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkAmountPool extends WalkElement
	{
		/**
		 *
		 */
		public WalkAmountPool()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return VString.getVString(ElementName.AMOUNTPOOL, null);
		}

		/**
		 * @param ap
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement ap, final KElement dummy)
		{
			final JDFAmountPool pool = (JDFAmountPool) ap;
			if (!retainAll)
			{
				moveActualToAudit(pool);
			}
			return super.walk(pool, dummy);
		}

		/**
		 *
		 * @param partAmount
		 */
		void moveActualToAudit(final JDFAmountPool partAmount)
		{
			if (partAmount.getDeepParent(ElementName.AUDITPOOL, 0) == null && partAmount.getDeepParent(XJDFConstants.XJMF, 0) == null)
			{
				final AuditPoolHelper ah = newRootHelper.getCreateAuditPool();
				final KElement resource = partAmount.getDeepParent(XJDFConstants.Resource, 0);
				final ResourceHelper ph = resource == null ? null : new ResourceHelper(resource);
				final SetHelper sh = ph == null ? null : ph.getSet();
				if (sh != null)
				{
					final MessageResourceHelper arh = ah.getCreateMessageResourceHelper(sh);
					final SetHelper shNew = arh.getSet();
					final ResourceHelper phNew = shNew.getCreateVPartition(sh.getPartMapVector(), false);
					phNew.getRoot().copyElement(partAmount, null);
					walkTree(shNew.getRoot(), null);
				}
				final VElement vpa = partAmount.getChildElementVector(ElementName.PARTAMOUNT, null);
				for (final KElement pa : vpa)
				{
					pa.removeAttribute(AttributeName.ACTUALAMOUNT);
					pa.removeAttribute("ActualWaste");
				}

			}
			else
			{
				final VElement vpa = partAmount.getChildElementVector(ElementName.PARTAMOUNT, null);
				for (final KElement pa : vpa)
				{
					fixPartAmount(pa);
				}
			}
		}

		public void fixPartAmount(final KElement pa)
		{
			pa.removeAttribute(AttributeName.AMOUNT);
			pa.renameAttribute(AttributeName.ACTUALAMOUNT, AttributeName.AMOUNT);
			pa.removeAttribute(AttributeName.WASTE);
			pa.renameAttribute("ActualWaste", AttributeName.WASTE);
		}

		/**
		 *
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(final KElement e)
		{
			return (e instanceof JDFAmountPool);
		}
	}

	/**
	 *
	 *
	 */
	public class WalkPlacedObject extends WalkElement
	{
		/**
		 *
		 */
		public WalkPlacedObject()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFContentObject) || (toCheck instanceof JDFMarkObject);
		}

		/**
		 *
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkRefElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			copyToPlaceObject(xjdf);
			return super.walk(xjdf, dummy);
		}

		/**
		 * we do everything on the jdf side so that all other tests are done by the call to super
		 *
		 * @param xjdf
		 *
		 * @return
		 */
		private KElement copyToPlaceObject(final KElement xjdf)
		{
			final KElement po = xjdf.getParentNode_KElement().insertBefore(XJDFConstants.PlacedObject, xjdf, null);
			final VString poAttribs = JDFToXJDFDataCache.getPlacedObjectAttribs();
			for (final String att : poAttribs)
			{
				po.moveAttribute(att, xjdf);
			}
			po.moveElement(xjdf, null);
			return po;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(new String[] { ElementName.CONTENTOBJECT, ElementName.MARKOBJECT });
		}
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkMediaIntent extends WalkIntent
	{
		/**
		 *
		 */
		public WalkMediaIntent()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return !retainAll && (toCheck instanceof JDFMediaIntent);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return VString.getVString(ElementName.MEDIAINTENT, null);
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkIntent#updateAttributes(org.cip4.jdflib.core.KElement)
		 */
		@Override
		void updateAttributes(final KElement xjdf)
		{
			final String coating = xjdf.getNonEmpty("FrontCoatings");
			if (xjdf != null)
			{
				xjdf.removeAttribute("FrontCoatings");
				xjdf.setAttribute(XJDFConstants.Coating, getCoating(coating));
			}
			super.updateAttributes(xjdf);
		}

	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkStripCellParams extends WalkElement
	{
		/**
		 *
		 */
		public WalkStripCellParams()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFStripCellParams;
		}

		/**
		 *
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement stripCellParams, final KElement dummy)
		{
			if (!mergeLayout)
			{
				super.walk(stripCellParams, dummy);
				return stripCellParams; // nuff done
			}
			stripCellParams.renameElement(ElementName.SIGNATURECELL, null);
			super.walk(stripCellParams, dummy);
			return stripCellParams; // stop after merging
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#updateAttributes(org.cip4.jdflib.core.KElement)
		 */
		@Override
		void updateAttributes(final KElement xjdf)
		{
			xjdf.renameAttribute(AttributeName.SPINE, XJDFConstants.TrimSpine);
			super.updateAttributes(xjdf);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(ElementName.STRIPCELLPARAMS, null);
		}
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkSurfaceColor extends WalkElement
	{
		/**
		 *
		 */
		public WalkSurfaceColor()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#updateAttributes(org.cip4.jdflib.core.KElement)
		 */
		@Override
		void updateAttributes(final KElement xjdf)
		{
			xjdf.renameAttribute(ElementName.COLORICCSTANDARD, XJDFConstants.PrintStandard);
			xjdf.renameAttribute(ElementName.COLORSTANDARD, XJDFConstants.PrintStandard);
			super.updateAttributes(xjdf);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(XJDFConstants.SurfaceColor, null);
		}
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkStrippingParams extends WalkResourceElement
	{
		/**
		 *
		 */
		public WalkStrippingParams()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(ElementName.STRIPPINGPARAMS, null);
		}

		/**
		 *
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement strippingParams, final KElement dummy)
		{
			if (!mergeLayout)
			{
				super.walk(strippingParams, dummy);
				return strippingParams; // nuff done
			}
			// TODO multiple lower level stripparams partitions
			final SetHelper layoutseth = newRootHelper.getCreateSet(XJDFConstants.Resource, ElementName.LAYOUT, EnumUsage.Input);

			final VJDFAttributeMap vmap = new ResourceHelper(strippingParams.getParentNode_KElement()).getPartMapVector();
			JDFAttributeMap map = vmap.size() == 0 ? null : vmap.get(0);
			map = mergeStrippingParamsLayout((JDFStrippingParams) strippingParams, layoutseth, map);

			mergeSurfaces(map);
			super.walk(strippingParams, dummy);
			return null; // stop after merging

		}

		/**
		 * @param strippingParams
		 * @param layoutseth
		 * @param map
		 * @return
		 */
		private JDFAttributeMap mergeStrippingParamsLayout(final JDFStrippingParams strippingParams, final SetHelper layoutseth, final JDFAttributeMap map)
		{
			if (isRemoveSignatureName() && map != null)
			{
				map.remove(AttributeName.SIGNATURENAME);
			}
			String bsName = getBSID(strippingParams, map);
			final String cellIndex = map == null ? null : map.remove(AttributeName.CELLINDEX);
			final ResourceHelper layoutPartitionH = layoutseth.getCreatePartition(map, true);
			final JDFLayout layoutPartition = (JDFLayout) layoutPartitionH.getResource();
			ensureLayoutPositions(strippingParams, layoutPartition, bsName);

			final JDFBinderySignature bsOld = (JDFBinderySignature) layoutPartition.getChildWithAttribute(ElementName.BINDERYSIGNATURE, AttributeName.BINDERYSIGNATURENAME, null, bsName, 0, true);
			final VElement childElementVector = strippingParams.getChildElementVector(ElementName.SIGNATURECELL, null);
			if (childElementVector != null)
			{
				for (final KElement kid : childElementVector)
				{
					final JDFStripCellParams scp = (JDFStripCellParams) kid;
					scp.setAttribute(AttributeName.CELLINDEX, cellIndex);
				}
			}
			if (bsOld != null)
			{
				moveStripCells(bsOld, childElementVector);
			}
			else
			{
				final String bsRef = strippingParams.getNonEmpty(ElementName.BINDERYSIGNATURE + "Ref");
				final ResourceHelper bsHelper = XJDFHelper.getHelper(layoutPartition).getPartition(bsRef);
				if (bsHelper != null)
				{
					JDFAttributeMap partMap = bsHelper.getPartMap();
					if (partMap == null)
					{
						partMap = new JDFAttributeMap();
					}
					final String bsID = partMap.get(XJDFConstants.BinderySignatureID);
					if (bsID == null)
					{
						final SetHelper sh = bsHelper.getSet();
						if (bsName == null)
						{
							bsName = "BS_" + sh.indexOf(bsHelper);
						}
						partMap.put(XJDFConstants.BinderySignatureID, bsName);
						bsHelper.setPartMap(partMap);
					}
					else if (!bsID.equals(bsName) && bsName != null)
					{
						final VJDFAttributeMap partMaps = bsHelper.getPartMapVector();
						final JDFAttributeMap map2 = partMap.clone();
						map2.put(XJDFConstants.BinderySignatureID, bsName);
						partMaps.appendUnique(map2);
						bsHelper.setPartMapVector(partMaps);
					}
					final JDFBinderySignature bs = (JDFBinderySignature) bsHelper.getResource();
					if (bs != null)
					{
						moveStripCells(bs, childElementVector);
						moveBSFromStripping(bs, strippingParams);
						final VElement positions = layoutPartition.getChildElementVector(ElementName.POSITION, null);
						if (positions != null)
						{
							for (final KElement position : positions)
							{
								if (!position.hasAttribute(XJDFConstants.BinderySignatureID))
								{
									position.setAttribute(XJDFConstants.BinderySignatureID, bsID);
								}
							}
						}
						strippingParams.removeAttribute(ElementName.BINDERYSIGNATURE + "Ref");
					}
				}
				layoutPartition.copyInto(strippingParams, false);
			}
			return map;
		}

		/**
		 *
		 * @param strippingParams
		 * @param map
		 * @return
		 */
		String getBSID(final JDFStrippingParams strippingParams, final JDFAttributeMap map)
		{
			String bsName = map == null ? null : map.remove(AttributeName.BINDERYSIGNATURENAME);
			final String bsn2 = strippingParams.getNonEmpty(XJDFConstants.BinderySignatureIDs);
			if (bsn2 != null)
				bsName = bsn2;
			return bsName;
		}

		/**
		 *
		 *
		 * @param strippingParams
		 * @param layoutPartition
		 * @param bsName
		 */
		private void ensureLayoutPositions(final JDFStrippingParams strippingParams, final JDFLayout layoutPartition, final String bsName)
		{
			VElement positions = strippingParams.getChildElementVector(ElementName.POSITION, null);
			if (positions != null && positions.size() > 0)
			{
				layoutPartition.moveElements(positions, null);
			}
			else
			{
				final JDFPosition newPos = (JDFPosition) layoutPartition.appendElement(ElementName.POSITION);
				newPos.setRelativeBox(new JDFRectangle(0, 0, 1, 1));
				positions = new VElement();
				positions.add(newPos);
			}
			final String attribute = AttributeName.STACKDEPTH;
			for (final KElement position : positions)
			{
				position.setAttribute(XJDFConstants.BinderySignatureID, bsName);
				position.copyAttribute(attribute, strippingParams);
			}
			strippingParams.removeAttribute(attribute);
		}

		/**
		 * move appropriate stuff from StrippingParams to BinderySignature
		 *
		 * @param bs
		 * @param strippingParams
		 */
		private void moveBSFromStripping(final JDFBinderySignature bs, final JDFStrippingParams strippingParams)
		{
			if (strippingParams == null || bs == null)
				return;
			bs.moveAttribute(XJDFConstants.BinderySignatureID, bs, AttributeName.ASSEMBLYIDS, null, null);
			bs.moveAttribute(AttributeName.JOBID, bs);
			bs.moveAttribute(AttributeName.INNERMOSTSHINGLING, bs);
			bs.moveAttribute(AttributeName.OUTERMOSTSHINGLING, bs);
			// TODO where to move stripmarks? - stay in strippingparams or move to the appropriate binderysignature, stripcell or strippingparams
		}

		/**
		 *
		 * move and merge stripcellparams and signaturecells
		 *
		 * @param bindSig
		 * @param childElementVector
		 */
		private void moveStripCells(final JDFBinderySignature bindSig, final VElement childElementVector)
		{
			if (childElementVector == null)
				return;
			for (final KElement sigCell : childElementVector)
			{
				final String cellindex = sigCell.getAttribute(AttributeName.CELLINDEX, null, null);
				final JDFIntegerList il = JDFIntegerList.createIntegerList(cellindex);
				final Vector<JDFSignatureCell> vbsCell = getSigCellForIndex(bindSig, il);
				sigCell.removeAttribute(AttributeName.CELLINDEX);
				if (vbsCell != null)
				{
					for (final JDFSignatureCell bsCell : vbsCell)
					{
						bsCell.mergeElement(sigCell, false);
					}
				}
				sigCell.deleteNode();
			}
		}

		/**
		 *
		 * @param bindSig
		 * @param il
		 * @return
		 */
		private Vector<JDFSignatureCell> getSigCellForIndex(final JDFBinderySignature bindSig, final JDFIntegerList il)
		{
			final Vector<JDFSignatureCell> v = bindSig.getChildrenByClass(JDFSignatureCell.class, true, 0);
			final Vector<JDFSignatureCell> vRet = new Vector<>();
			if (v == null || v.size() == 0)
			{
				vRet.add(bindSig.appendSignatureCell());
			}
			else
			{
				for (final JDFSignatureCell sc : v)
				{
					if (matchesIndex(sc, il))
						vRet.add(sc);
				}
			}
			if (vRet.size() == 0)
			{
				vRet.add(bindSig.appendSignatureCell());
			}
			return vRet;
		}

		/**
		 *
		 * @param sc
		 * @param il
		 * @return
		 */
		private boolean matchesIndex(final JDFSignatureCell sc, JDFIntegerList il)
		{
			if (il == null)
				return true;

			try
			{
				il = new JDFIntegerList(il);
			}
			catch (final DataFormatException e)
			{
				return false;
			}
			// TODO simplex duplex evaluation
			il.scale(2);
			final JDFIntegerList fp = sc.getFrontPages();
			// we assume that any match is valid for all
			return il.contains(fp);
		}

		/**
		 * @param map
		 */
		private void mergeSurfaces(final JDFAttributeMap map)
		{
			// TODO merge surfaces that match map
			// 2 options
			// - either add @Side to the respective content / mark objects + dynamic marks
			// add Surface elements
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFStrippingParams;
		}

	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkStrippingSet extends WalkResourceSet
	{
		/**
		 *
		 */
		public WalkStrippingSet()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return !retainAll && toCheck.getLocalName().equals(SetHelper.RESOURCE_SET) && ElementName.STRIPPINGPARAMS.equals(toCheck.getAttribute(AttributeName.NAME));
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return null or super depending on the value of mergelayout
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			if (mergeLayout)
			{
				xjdf.deleteNode();
				return null;
			}
			else
			{
				return super.walk(xjdf, dummy);
			}
		}
		/**
		 *
		 * @author Rainer Prosi, Heidelberger Druckmaschinen
		 *
		 */
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public class WalkArtDeliveryIntentSet extends WalkIntentSet
	{
		/**
		 *
		 */
		public WalkArtDeliveryIntentSet()
		{
			super();
		}

		/**
		 *
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkIntentSet#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return !retainAll && super.matches(toCheck) && ElementName.ARTDELIVERYINTENT.equals(toCheck.getAttribute("Name"));
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			final KElement intent = super.walk(xjdf, dummy);
			if (intent != null)
			{
				final XJDFHelper h = new XJDFHelper(intent.getDeepParent(XJDFConstants.XJDF, 0));
				final SetHelper artDelResHelper = h.getCreateSet(XJDFConstants.Resource, ElementName.DELIVERYPARAMS, EnumUsage.Input);
				final ResourceHelper ph = artDelResHelper.appendPartition(null, true);
				final JDFDeliveryParams dp = (JDFDeliveryParams) ph.getResource();
				setFromArtDelivery(dp, (JDFArtDeliveryIntent) intent.getElement(ElementName.ARTDELIVERYINTENT));
				intent.deleteNode();
				return null;
			}
			return intent;
		}

		private void setFromArtDelivery(final JDFDeliveryParams dp, final JDFArtDeliveryIntent adi)
		{
			final Vector<JDFArtDelivery> vAD = adi == null ? null : adi.getChildrenByClass(JDFArtDelivery.class, false, 0);
			if (vAD != null)
			{
				for (final JDFArtDelivery ad : vAD)
				{
					dp.setMethod(StringUtil.getNonEmpty(ad.getArtDeliveryType()));
					final KElement dropItem = dp.appendElement(ElementName.DROPITEM);
					dropItem.copyAttribute(XJDFConstants.ItemRef, ad, "RunListRef", null, null);
					dropItem.setAttribute(AttributeName.AMOUNT, 1, null);
				}
			}

		}
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public class WalkDeliveryIntentSet extends WalkIntentSet
	{
		/**
		 *
		 */
		public WalkDeliveryIntentSet()
		{
			super();
		}

		/**
		 *
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkIntentSet#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return !bDeliveryIntent && (super.matches(toCheck) && ElementName.DELIVERYINTENT.equals(toCheck.getAttribute("Name")));
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			final KElement intent = super.walk(xjdf, dummy);
			if (intent != null)
			{
				final XJDFHelper h = new XJDFHelper(xjdf.getDeepParent(XJDFConstants.XJDF, 0));
				final SetHelper delResHelper = h.getCreateSet(XJDFConstants.Resource, ElementName.DELIVERYPARAMS, EnumUsage.Input);
				final ResourceHelper ph = delResHelper.appendPartition(null, true);
				final JDFDeliveryParams dp = (JDFDeliveryParams) ph.getResource();
				dp.setFromDeliveryIntent((JDFDeliveryIntent) intent.getElement(ElementName.DELIVERYINTENT));
			}
			return intent;
		}
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public class WalkDeliveryParams extends WalkResourceElement
	{
		/**
		 *
		 */
		public WalkDeliveryParams()
		{
			super();
		}

		/**
		 *
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkIntentSet#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return !retainAll && toCheck instanceof JDFDeliveryParams;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			final KElement delParams = super.walk(xjdf, dummy);
			if (delParams != null)
			{
				final Vector<JDFDrop> vDrop = delParams.getChildrenByClass(JDFDrop.class, false, 0);
				final int size = vDrop == null ? 0 : vDrop.size();
				final KElement param = delParams.getParentNode_KElement();
				final KElement set = param == null ? null : param.getParentNode_KElement();
				if (set != null)
				{
					final ResourceHelper ph = new ResourceHelper(param);
					final SetHelper sh = new SetHelper(set);
					final JDFAttributeMap partMap = ph.getPartMap();
					partMap.put(XJDFConstants.DROP_ID, "DROP_0");
					ph.setPartMap(partMap);

					delParams.removeChildren(ElementName.DROP, null, null);
					for (int j = 0; j < size; j++)
					{
						final int i = (j + 1) % size;
						partMap.put(XJDFConstants.DROP_ID, "DROP_" + i);
						KElement newDrop;
						ResourceHelper newParam;
						if (i != 0)
						{
							newParam = sh.getCreatePartition(partMap, true);
							newDrop = newParam.getResource();
							newDrop.copyInto(delParams, false);
						}
						else
						{
							newDrop = delParams;
							newParam = ph;
						}
						newDrop.copyInto(vDrop.get(i), false);
					}
				}
			}
			return delParams;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return VString.getVString(ElementName.DELIVERYPARAMS, null);
		}
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkIntentSet extends WalkElement
	{
		/**
		 *
		 */
		public WalkIntentSet()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return "IntentSet".equals(toCheck.getLocalName());
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			final KElement intent = xjdf.getElement(XJDFConstants.Intent);
			if (!bIntentPartition && intent != null)
			{
				intent.copyAttribute(AttributeName.NAME, xjdf);
				xjdf.getParentNode_KElement().moveElement(intent, xjdf);
				xjdf.deleteNode();
			}
			else
			{
				super.walk(xjdf, dummy);
			}
			return intent;
		}
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkResourceElement extends WalkElement
	{
		/**
		 *
		 */
		public WalkResourceElement()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return ResourceHelper.isResourceElement(toCheck);
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			final KElement ret = super.walk(xjdf, dummy);
			moveToMisconsumable(xjdf);
			xjdf.eraseEmptyNodes(true);
			if (xjdf.getFirstChild() == null && xjdf.getAttributeMap().size() == 0)
			{
				xjdf.deleteNode();
				return null;
			}
			return ret;
		}

		/**
		 *
		 * @param xjdf
		 */
		void moveToMisconsumable(final KElement xjdf)
		{
			// nop hook
		}

	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkResource extends WalkElement
	{
		/**
		 *
		 */
		public WalkResource()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return XJDFHelper.RESOURCE.equals(toCheck.getLocalName()) || "Parameter".equals(toCheck.getLocalName());
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			moveToSet(xjdf);
			final KElement ret = super.walk(xjdf, dummy);
			xjdf.eraseEmptyNodes(true);
			final KElement set = xjdf.getParentNode_KElement();
			final String name = set == null ? null : StringUtil.getNonEmpty(set.getAttribute(AttributeName.NAME));
			if (name == null)
			{
				return null;
			}
			/*
			 * KElement realRes = xjdf.getElement(name); if (realRes != null) return ret; KElement aPool = xjdf.getElement(ElementName.AMOUNTPOOL); if (aPool != null) return ret; KElement comment =
			 * xjdf.getElement(ElementName.COMMENT); if (comment != null) return ret; JDFAttributeMap map = xjdf.getAttributeMap(); map.remove(AttributeName.ID); map.remove(AttributeName.STATUS); if
			 * (map.size() == 0) { xjdf.deleteNode(); return null; }
			 */
			return ret;
		}

		/**
		 *
		 * @param xjdf
		 */
		void moveToSet(final KElement xjdf)
		{
			final KElement set = xjdf.getParentNode_KElement();
			if (set != null && xjdf.hasNonEmpty(AttributeName.UNIT))
			{
				set.moveAttribute(AttributeName.UNIT, xjdf);
			}

		}

	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkResourceSet extends WalkElement
	{
		/**
		 *
		 */
		public WalkResourceSet()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return SetHelper.isSet(toCheck);
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param set
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement set, final KElement dummy)
		{
			final KElement ret = super.walk(set, dummy);
			set.eraseEmptyNodes(true);
			final KElement res = set.getElement(StringUtil.leftStr(set.getLocalName(), -3));
			if (res != null)
				return ret;
			final KElement comment = set.getElement(ElementName.COMMENT);
			if (comment != null)
				return ret;
			final JDFAttributeMap map = set.getAttributeMap();
			map.remove(AttributeName.ID);
			map.remove(AttributeName.NAME);
			map.remove(AttributeName.PROCESSUSAGE);
			map.remove(AttributeName.USAGE);
			map.remove(AttributeName.COMBINEDPROCESSINDEX);
			if (map.size() == 0)
			{
				set.deleteNode();
				return null;
			}
			return ret;
		}

	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkIntent extends WalkElement
	{
		/**
		 *
		 */
		public WalkIntent()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return IntentHelper.INTENT.equals(toCheck.getLocalName());
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#updateAttributes(org.cip4.jdflib.core.KElement)
		 */
		@Override
		void updateAttributes(final KElement xjdf)
		{
			xjdf.removeAttribute(AttributeName.DESCRIPTIVENAME);
			xjdf.removeAttribute(AttributeName.STATUS);
			xjdf.removeAttribute(AttributeName.STATUSDETAILS);
			xjdf.removeAttribute(AttributeName.ID);
			super.updateAttributes(xjdf);
		}

	}

	/**
	 * @param newRoot
	 *
	 */
	PostXJDFWalker(final JDFElement newRoot)
	{
		super(new BaseWalkerFactory());
		newRootHelper = new XJDFHelper(newRoot);
		bDeliveryIntent = false;
		bIntentPartition = false;
		mergeLayout = true;
		removeSignatureName = true;
		retainAll = false;
	}

	/**
	 * @return the retainAll
	 */
	protected boolean isRetainAll()
	{
		return retainAll;
	}

	/**
	 * @param retainAll the retainAll to set
	 */
	protected void setRetainAll(final boolean retainAll)
	{
		this.retainAll = retainAll;
		if (retainAll)
		{
			removeSignatureName = !retainAll;
			mergeLayout = !retainAll;
			bDeliveryIntent = retainAll;
		}
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkXJDF extends WalkElement
	{
		/**
		 *
		 */
		public WalkXJDF()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(XJDFConstants.XJDF, null);
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			if (!retainAll)
			{
				reorderSets((JDFElement) xjdf);
				reorderProductList(xjdf);
			}
			super.walk(xjdf, dummy);
			return xjdf;
		}

		protected void reorderProductList(final KElement xjdf)
		{
			final KElement productList = xjdf.getElement(ProductHelper.PRODUCTLIST);
			final KElement auditPool = xjdf.getElement(ElementName.AUDITPOOL);
			if (productList != null && auditPool != null)
			{
				xjdf.moveElement(productList, auditPool);
			}
		}

		/**
		 * @param xjdf
		 *
		 */
		private void reorderSets(final JDFElement xjdf)
		{
			final Vector<SetHelper> v = new XJDFHelper(xjdf).getSets();
			if (v == null)
				return; // nothing to do

			int n = v.size();
			int j = 0;
			while (n > 0)
			{
				for (int i = 0; i < v.size(); i++)
				{
					final SetHelper setHelper = v.get(i);
					final KElement e = setHelper == null ? null : setHelper.getSet();
					if (e != null)
					{
						JDFIntegerList lcpi = null;
						final String cpi = e.getAttribute(AttributeName.COMBINEDPROCESSINDEX, null, null);
						try
						{
							lcpi = cpi == null ? null : new JDFIntegerList(cpi);
						}
						catch (final DataFormatException dfe)
						{
							// nop
						}

						// have a matching cpi entry - place set here and remove from further tests
						if (lcpi == null || lcpi.contains(j))
						{
							v.set(i, null);
							n--;
						}
						else
						{
							xjdf.moveElement(e, null);
						}
					}
				}
				j++;
			}
			// TODO treat outputs backwards...
			combineSameSets(xjdf);
		}

		/**
		 *
		 * @param v
		 */
		private void combineSameSets(final JDFElement xjdf)
		{
			final Vector<SetHelper> v = new XJDFHelper(xjdf).getSets();

			while (v.size() > 0)
			{
				final Vector<SetHelper> sameSets = new Vector<>();
				final SetHelper firstSet = v.remove(0);
				sameSets.add(firstSet);
				for (int j = 0; j < v.size(); j++)
				{
					final SetHelper next = v.get(j);
					if (sameSetType(firstSet, next))
					{
						sameSets.add(next);
						v.remove(j);
						j--;
					}
				}

				final KElement root = firstSet.getRoot();
				for (int j = 1; j < sameSets.size(); j++)
				{
					final Vector<ResourceHelper> parts = sameSets.get(j).getPartitions();
					for (final ResourceHelper ph : parts)
					{
						root.copyElement(ph.getRoot(), null);
					}
					sameSets.get(j).getRoot().deleteNode();
				}
			}

		}

		/**
		 *
		 * @param firstSet
		 * @param next
		 * @return
		 */
		private boolean sameSetType(final SetHelper firstSet, final SetHelper next)
		{
			boolean same = firstSet.getName().equals(next.getName());
			if (same)
				same = StringUtil.equals(firstSet.getProcessUsage(), next.getProcessUsage());
			if (same)
				same = ContainerUtil.equals(firstSet.getUsage(), next.getUsage());
			if (same)
				same = ContainerUtil.equals(firstSet.getXPathValue("@CombinedProcessIndex"), next.getXPathValue("@CombinedProcessIndex"));
			if (same)
				same = ContainerUtil.equals(firstSet.getXPathValue("Resource/Media/@MediaType"), next.getXPathValue("Resource/Media/@MediaType"));

			return same;
		}
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public class WalkProductList extends WalkElement
	{
		/**
		 *
		 */
		public WalkProductList()
		{
			super();
		}

		/**
		 *
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkIntentSet#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return ProductHelper.PRODUCTLIST.equals(toCheck.getLocalName());
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			final KElement firstProduct = xjdf.getElement(ProductHelper.PRODUCT);
			if (firstProduct == null)
			{
				xjdf.deleteNode();
				return null;
			}
			else
			{
				return super.walk(xjdf, dummy);
			}
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(ProductHelper.PRODUCTLIST, null);
		}

	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public class WalkProduct extends WalkElement
	{
		/**
		 *
		 */
		public WalkProduct()
		{
			super();
		}

		/**
		 *
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkIntentSet#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return ProductHelper.PRODUCT.equals(toCheck.getLocalName());
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			fixChildRefs(xjdf);
			return super.walk(xjdf, dummy);
		}

		/**
		 *
		 * @param xjdf
		 */
		private void fixChildRefs(final KElement xjdf)
		{
			final ProductHelper ph = new ProductHelper(xjdf);
			final IntentHelper bind = ph.getIntent(ElementName.BINDINGINTENT);
			if (bind != null)
			{
				bind.getCreateResource().moveAttribute(XJDFConstants.ChildRefs, ph.getRoot());
			}
			else
			{
				// TODO fix to subelements
				final IntentHelper insert = ph.getIntent(ElementName.INSERTINGINTENT);
				if (insert != null)
				{
					insert.getCreateResource().moveAttribute(XJDFConstants.ChildRefs, ph.getRoot());
				}
			}

		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(ProductHelper.PRODUCT, null);
		}
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public class WalkIdentical extends WalkElement
	{
		/**
		 *
		 */
		public WalkIdentical()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			if (xjdf instanceof JDFIdentical)
			{
				final JDFIdentical id = (JDFIdentical) xjdf;
				final KElement xjdfRes = xjdf.getDeepParent(XJDFConstants.Resource, 0);
				final KElement set = xjdfRes == null ? null : xjdfRes.getDeepParent(XJDFConstants.ResourceSet, 0);
				if (set != null)
				{
					final JDFAttributeMap trgMap = id.getPartMap();
					final SetHelper sh = new SetHelper(set);
					final ResourceHelper targetHelper = sh.getPartition(trgMap);
					if (targetHelper != null)
					{
						targetHelper.appendPartMap(new ResourceHelper(xjdfRes).getPartMap());
					}
					xjdfRes.deleteNode();
				}
			}
			return null;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(ElementName.IDENTICAL, null);
		}
	}

	/**
	 *
	 * @author rainer prosi
	 *
	 */
	public class WalkHeadBandApplicationParams extends WalkResourceElement
	{
		/**
		 *
		 */
		public WalkHeadBandApplicationParams()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return !isRetainAll() && (toCheck instanceof JDFHeadBandApplicationParams);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(ElementName.HEADBANDAPPLICATIONPARAMS, null);
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#moveToMisconsumable(org.cip4.jdflib.core.KElement, java.lang.String)
		 */
		@Override
		void moveToMisconsumable(final KElement xjdf)
		{
			moveHeadBand(xjdf);
			moveBackStrip(xjdf);
		}

		private void moveHeadBand(final KElement xjdf)
		{
			final JDFHeadBandApplicationParams hap = (JDFHeadBandApplicationParams) xjdf;
			final String col = hap.getNonEmpty(AttributeName.TOPCOLOR);
			final String brand = hap.getNonEmpty(AttributeName.TOPBRAND);
			if (col != null || brand != null)
			{
				// TODO worry about top and bottom
				final MiscConsumableMaker mm = new MiscConsumableMaker(ResourceHelper.getHelper(xjdf));
				mm.create("HeadBand", null);
				mm.setColor(col);
				mm.setColorDetails(hap.getNonEmpty(AttributeName.TOPCOLORDETAILS));
				mm.setBrand(brand);
				hap.removeAttribute(AttributeName.TOPCOLOR);
				hap.removeAttribute(AttributeName.TOPCOLORDETAILS);
				hap.removeAttribute(AttributeName.TOPBRAND);
				hap.removeAttribute(AttributeName.BOTTOMCOLOR);
				hap.removeAttribute(AttributeName.BOTTOMCOLORDETAILS);
				hap.removeAttribute(AttributeName.BOTTOMBRAND);

			}
		}

		private void moveBackStrip(final KElement xjdf)
		{
			final JDFHeadBandApplicationParams hap = (JDFHeadBandApplicationParams) xjdf;
			final String mat = hap.getNonEmpty(AttributeName.STRIPMATERIAL);
			if (mat != null)
			{
				final MiscConsumableMaker mm = new MiscConsumableMaker(ResourceHelper.getHelper(xjdf));
				mm.create("BackStrip", null);
				mm.setTypeDetails(mat);
				hap.removeAttribute(AttributeName.STRIPMATERIAL);
			}
		}

	}

	/**
	 *
	 * @author rainer prosi
	 *
	 */
	public class WalkThreadSewingParams extends WalkResourceElement
	{
		/**
		 *
		 */
		public WalkThreadSewingParams()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return !isRetainAll() && (toCheck instanceof JDFThreadSewingParams);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(ElementName.THREADSEWINGPARAMS, null);
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#moveToMisconsumable(org.cip4.jdflib.core.KElement, java.lang.String)
		 */
		@Override
		void moveToMisconsumable(final KElement xjdf)
		{
			final JDFThreadSewingParams tsp = (JDFThreadSewingParams) xjdf;
			final String core = tsp.getNonEmpty(AttributeName.COREMATERIAL);
			final String cast = core == null ? tsp.getNonEmpty(AttributeName.CASTINGMATERIAL) : core;
			if (cast != null)
			{
				final MiscConsumableMaker mm = new MiscConsumableMaker(ResourceHelper.getHelper(xjdf));
				mm.create("Thread", null);
				mm.setTypeDetails(cast);
				tsp.removeAttribute(AttributeName.COREMATERIAL);
				tsp.removeAttribute(AttributeName.CASTINGMATERIAL);
			}
		}

	}

	/**
	 *
	 * @author rainer prosi
	 *
	 */
	public class WalkLaminatingParams extends WalkResourceElement
	{
		/**
		 *
		 */
		public WalkLaminatingParams()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return !isRetainAll();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(ElementName.LAMINATINGPARAMS, null);
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#moveToMisconsumable(org.cip4.jdflib.core.KElement, java.lang.String)
		 */
		@Override
		void moveToMisconsumable(final KElement xjdf)
		{
			final JDFLaminatingParams lp = (JDFLaminatingParams) xjdf;
			final String adhesive = lp.getNonEmpty(AttributeName.ADHESIVETYPE);
			if (adhesive != null)
			{
				final MiscConsumableMaker mm = new MiscConsumableMaker(ResourceHelper.getHelper(xjdf));
				mm.create("Glue", null);
				mm.setTypeDetails(adhesive);
				lp.removeAttribute(AttributeName.ADHESIVETYPE);
			}
			final String hard = lp.getNonEmpty(AttributeName.HARDENERTYPE);
			if (hard != null)
			{
				final MiscConsumableMaker mm = new MiscConsumableMaker(ResourceHelper.getHelper(xjdf));
				mm.create("Hardener", null);
				mm.setTypeDetails(hard);
				lp.removeAttribute(AttributeName.HARDENERTYPE);
			}

		}

	}

	/**
	 *
	 * @author rainer prosi
	 *
	 */
	public class WalkStitchingParams extends WalkResourceElement
	{
		/**
		 *
		 */
		public WalkStitchingParams()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return !isRetainAll();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(ElementName.STITCHINGPARAMS, null);
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#moveToMisconsumable(org.cip4.jdflib.core.KElement, java.lang.String)
		 */
		@Override
		void moveToMisconsumable(final KElement xjdf)
		{
			final JDFStitchingParams sp = (JDFStitchingParams) xjdf;
			final String brand = sp.getNonEmpty(AttributeName.WIREBRAND);
			if (brand != null)
			{
				final MiscConsumableMaker mm = new MiscConsumableMaker(ResourceHelper.getHelper(xjdf));
				mm.create("Wire", null);
				mm.setBrand(brand);
				final String gauge = sp.getNonEmpty(AttributeName.WIREGAUGE);
				mm.setTypeDetails(gauge);
				sp.removeAttribute(AttributeName.WIREBRAND);
				sp.removeAttribute(AttributeName.WIREGAUGE);
			}
		}

	}

	public class WalkCustomerInfo extends WalkResourceElement
	{

		/**
		 *
		 */
		public WalkCustomerInfo()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return !isRetainAll();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(ElementName.CUSTOMERINFO, null);
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#updateAttributes(org.cip4.jdflib.core.KElement)
		 */
		@Override
		void updateAttributes(final KElement xjdf)
		{
			super.updateAttributes(xjdf);
			xjdf.removeAttribute("ContactRefs");
		}

	}

	/**
	 *
	 *
	 *
	 */
	public class WalkLooseBindindingParams extends WalkResourceElement
	{

		/**
		 *
		 */
		public WalkLooseBindindingParams()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return !isRetainAll();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString(XJDFConstants.LooseBindingParams, null);
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#moveToMisconsumable(org.cip4.jdflib.core.KElement, java.lang.String)
		 */
		@Override
		void moveToMisconsumable(final KElement xjdf)
		{
			final MiscConsumableMaker mm = new MiscConsumableMaker(ResourceHelper.getHelper(xjdf));
			final String typ = xjdf.getAttribute(ElementName.BINDINGTYPE);
			if ("CoilBinding".equals(typ))
			{
				moveFromCoil(xjdf, mm);
			}
			else if ("ChannelBinding".equals(typ))
			{
				moveFromChannel(xjdf, mm);
			}
		}

		private void moveFromCoil(final KElement xjdf, final MiscConsumableMaker mm)
		{
			mm.create("Coil", "Spine");
			mm.setTypeDetails(xjdf.getNonEmpty(AttributeName.MATERIAL));
			mm.setColor(xjdf.getNonEmpty(AttributeName.COLOR));
			mm.setColorDetails(xjdf.getNonEmpty(AttributeName.COLORDETAILS));
			mm.setBrand(xjdf.getNonEmpty(AttributeName.BRAND));
			xjdf.removeAttribute(AttributeName.MATERIAL);
			xjdf.removeAttribute(AttributeName.COLOR);
			xjdf.removeAttribute(AttributeName.COLORDETAILS);
			xjdf.removeAttribute(AttributeName.BRAND);
		}

		private void moveFromChannel(final KElement xjdf, final MiscConsumableMaker mm)
		{
			mm.create("ChannelBinder", "Spine");
			mm.setColor(xjdf.getNonEmpty(AttributeName.CLAMPCOLOR));
			mm.setColorDetails(xjdf.getNonEmpty(AttributeName.CLAMPCOLORDETAILS));
			mm.setBrand(xjdf.getNonEmpty(AttributeName.BRAND));
			xjdf.removeAttribute(AttributeName.CLAMPCOLOR);
			xjdf.removeAttribute(AttributeName.CLAMPCOLORDETAILS);
			xjdf.removeAttribute(AttributeName.BRAND);
		}
	}

	/**
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public class WalkProcessList extends WalkElement
	{
		/**
		 *
		 */
		public WalkProcessList()
		{
			super();
		}

		/**
		 *
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkIntentSet#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return XJDFConstants.ProcessList.equals(toCheck.getLocalName());
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{

			if (xjdf.numChildElements("Process", null) < 2)
			{
				xjdf.deleteNode();
				return null;
			}
			else
			{
				return super.walk(xjdf, dummy);
			}
		}
	}

	/**
	 * class that ensures that we do not have signaturename partitions
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkXJMF extends WalkElement
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return VString.getVString(XJDFConstants.XJMF, null);
		}

		/**
		 *
		 */
		public WalkXJMF()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			moveToSender(xjdf);
			return super.walk(xjdf, dummy);
		}

	}

	/**
	 * class that ensures that we do not have signaturename partitions
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkMessage extends WalkElement
	{

		/**
		 *
		 */
		public WalkMessage()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			moveToSender(xjdf);
			return super.walk(xjdf, dummy);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(final KElement e)
		{
			final String localName = e.getLocalName();
			return localName.startsWith(ElementName.QUERY) || localName.startsWith(ElementName.SIGNAL) || localName.startsWith(ElementName.RESPONSE)
					|| localName.startsWith(ElementName.COMMAND);
		}

	}

	/**
	 * class that ensures that we do not have signaturename partitions
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 *
	 */
	protected class WalkAudit extends WalkElement
	{

		/**
		 *
		 */
		public WalkAudit()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			moveToSender(xjdf);
			return super.walk(xjdf, dummy);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(final KElement e)
		{
			final String localName = e.getLocalName();
			return localName.startsWith(ElementName.AUDIT) && !ElementName.AUDITPOOL.equals(localName);
		}

	}

	/**
	 *
	 * @param xjdf
	 */
	void moveToSender(final KElement xjdf)
	{
		if (xjdf != null)
		{
			final JDFElement sender = (JDFElement) xjdf.getCreateElement(XJDFConstants.Header);
			sender.moveAttribute(AttributeName.AGENTNAME, xjdf);
			sender.moveAttribute(AttributeName.AGENTVERSION, xjdf);
			sender.moveAttribute(AttributeName.AUTHOR, xjdf);
			sender.moveAttribute(AttributeName.DEVICEID, xjdf);
			sender.moveAttribute(AttributeName.TIME, xjdf);
			sender.moveAttribute(AttributeName.ICSVERSIONS, xjdf);
			sender.moveAttribute(AttributeName.PERSONALID, xjdf);
			sender.moveAttribute(AttributeName.ID, xjdf);
			sender.moveAttribute(AttributeName.REFID, xjdf);
			if (!sender.hasAttribute(AttributeName.DEVICEID))
				sender.setAttribute(AttributeName.DEVICEID, "dummy");
			final VElement v = xjdf.getChildrenByTagName(null, null, new JDFAttributeMap(AttributeName.ID, JDFConstants.STAR), false, true, 0);
			if (v != null)
			{
				for (final KElement e : v)
				{
					if (!XJDFConstants.Header.equals(e.getLocalName()))
					{
						e.removeAttribute(AttributeName.ID);
					}
				}
			}
			// ensure clean namespaces
			getFactory().getWalker(sender).walk(sender, null);
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PostXJDFWalker [mergeLayout=" + mergeLayout + ", bIntentPartition=" + bIntentPartition + ", bDeliveryIntent=" + bDeliveryIntent + ", retainAll=" + retainAll
				+ ", removeSignatureName=" + removeSignatureName + ", newRoot=" + newRootHelper.getRoot() + "]";
	}
}
