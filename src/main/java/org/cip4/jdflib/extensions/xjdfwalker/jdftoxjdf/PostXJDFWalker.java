/*
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoBinderySignature.EnumBinderySignatureType;
import org.cip4.jdflib.auto.JDFAutoStripCellParams.EnumSides;
import org.cip4.jdflib.auto.JDFAutoStrippingParams.EnumWorkStyle;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.elementwalker.IWalker;
import org.cip4.jdflib.extensions.BaseXJDFHelper;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.jmf.JDFMessageService;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFHeadBandApplicationParams;
import org.cip4.jdflib.resource.JDFLaminatingParams;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.intent.JDFArtDelivery;
import org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFMediaIntent;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFAssemblySection;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFDrop;
import org.cip4.jdflib.resource.process.JDFDropItem;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFIdentical;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFSignatureCell;
import org.cip4.jdflib.resource.process.JDFStation;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.cip4.jdflib.resource.process.postpress.JDFStitchingParams;
import org.cip4.jdflib.resource.process.postpress.JDFThreadSewingParams;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.ListMap;
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

	private static final StringArray metaKeys = new StringArray(new String[] { AttributeName.COMMANDRESULT, AttributeName.JOBID, AttributeName.JOBPARTID, AttributeName.LEVEL,
			AttributeName.MODULEID, AttributeName.QUEUEENTRYID, AttributeName.SCOPE, AttributeName.SPEED, AttributeName.TOTALAMOUNT, AttributeName.TYPES });

	/**
	 * @return
	 */
	boolean isMergeLayout()
	{
		return mergeLayout;
	}

	/**
	 * @param mergeLayout
	 */
	void setMergeLayout(final boolean mergeLayout)
	{
		this.mergeLayout = mergeLayout;
	}

	private EnumVersion newVersion;

	/**
	 * @return the newVersion
	 */
	public EnumVersion getNewVersion()
	{
		return newVersion;
	}

	/**
	 * /**
	 *
	 * @param newVersion the newVersion to set
	 */
	public void setNewVersion(final EnumVersion newVersion)
	{
		this.newVersion = newVersion == null ? BaseXJDFHelper.getDefaultVersion() : newVersion;
	}

	/**
	 * @return
	 */
	boolean isIntentPartition()
	{
		return bIntentPartition;
	}

	/**
	 * @param bIntentPartition
	 */
	void setIntentPartition(final boolean bIntentPartition)
	{
		this.bIntentPartition = bIntentPartition;
	}

	/**
	 * @return
	 */
	boolean isDeliveryIntent()
	{
		return bDeliveryIntent;
	}

	/**
	 * @param bDeliveryIntent
	 */
	void setDeliveryIntent(final boolean bDeliveryIntent)
	{
		this.bDeliveryIntent = bDeliveryIntent;
	}

	private boolean removeSignatureName;

	/**
	 * @return
	 */
	boolean isRemoveSignatureName()
	{
		return removeSignatureName;
	}

	/**
	 * @param removeSignatureName
	 */
	void setRemoveSignatureName(final boolean removeSignatureName)
	{
		this.removeSignatureName = removeSignatureName;
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
			{
				return null;
			}
			if ("glossy".equalsIgnoreCase(coating))
			{
				return "Gloss";
			}
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

		protected void ensureNmtoken(final KElement e, final String name)
		{
			final String token = e.getNonEmpty(name);
			final String newToken = StringUtil.replaceChar(token, ' ', "_", 0);
			e.setAttribute(name, newToken);
		}

		/**
		 * rename hook
		 *
		 * @param xjdf
		 */
		void updateAttributes(final KElement xjdf)
		{
			// NOP
		}

		/**
		 * @param xjdf
		 */
		void updateNamespaces(final KElement xjdf)
		{
			if (xjdf.hasAttribute(AttributeName.XMLNS))
			{
				xjdf.removeAttribute(AttributeName.XMLNS);
			}
			if (xjdf.getNamespaceURI().equals(JDFElement.getSchemaURL()))
			{
				xjdf.setNamespaceURI(JDFElement.getSchemaURL(newVersion));
			}
		}

	}

	/**
	 * class that ensures that we do not have signaturename partitions
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
			{
				return;
			}

			if (isRemoveSignatureName())
			{
				part.removeAttribute(AttributeName.SIGNATURENAME);
			}

			ensureNmtoken(part, AttributeName.SHEETNAME);
			part.renameAttribute(AttributeName.BINDERYSIGNATURENAME, XJDFConstants.BinderySignatureID);
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
			part.renameAttribute(AttributeName.RUNPAGE, AttributeName.PAGENUMBER);
			part.renameAttribute(AttributeName.RUNPAGERANGE, AttributeName.PAGENUMBER);
			String pn = part.getNonEmpty(AttributeName.PAGENUMBER);
			if (pn != null)
			{
				JDFIntegerList pns = JDFIntegerList.createIntegerList(pn);
				if (pns != null)
				{
					int[] ia = pns.getIntArray();
					JDFIntegerRangeList pnr = new JDFIntegerRangeList(ia);
					if (pnr.size() == 1)
					{
						JDFIntegerRange r = (JDFIntegerRange) pnr.elementAt(0);
						part.setAttribute(AttributeName.PAGENUMBER, r.getXJDFString(0));
					}
				}
			}
			part.removeAttribute(AttributeName.RUNSET);
			part.removeAttribute(AttributeName.RUNTAGS);
			part.removeAttribute(AttributeName.SECTIONINDEX);
			part.removeAttribute(AttributeName.SETCOPIES);
			part.renameAttribute(AttributeName.SETDOCINDEX, AttributeName.DOCINDEX);
			part.renameAttribute(AttributeName.SETRUNINDEX, AttributeName.RUNINDEX);
			part.renameAttribute(AttributeName.SETSHEETINDEX, AttributeName.SHEETINDEX);

			part.removeAttribute(AttributeName.SUBRUN);
			part.renameAttribute(AttributeName.WEBPRODUCT, AttributeName.PRODUCT);
			part.removeAttribute(AttributeName.WEBSETUP);

			final String name = part.getNonEmpty(XJDFConstants.TransferCurveName);
			if ("Paper".equals(name))
			{
				part.setAttribute(XJDFConstants.TransferCurveName, "Substrate");
			}

			super.updateAttributes(part);
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			final KElement walk = super.walk(xjdf, dummy);
			if (!retainAll)
			{
				splitPartVersion(xjdf, walk);
				splitIndex(xjdf, walk, AttributeName.DOCINDEX);
				splitIndex(xjdf, walk, AttributeName.RUNINDEX);
				splitIndex(xjdf, walk, AttributeName.SETINDEX);
				splitIndex(xjdf, walk, AttributeName.SHEETINDEX);
			}
			return walk;
		}

		void splitPartVersion(final KElement xjdf, final KElement walk)
		{
			final VString pv = StringUtil.tokenize(walk.getAttribute(AttributeName.PARTVERSION), null, false);
			final KElement parent = xjdf.getParentNode_KElement();
			int size = parent == null ? 0 : ContainerUtil.size(pv);
			if (size > 1)
			{
				pv.unify();
				size = pv.size();
				for (int i = size - 1; i > 0; i--)
				{
					final KElement clone = parent.copyElement(xjdf, xjdf);
					clone.setAttribute(AttributeName.PARTVERSION, pv.remove(0));
				}
				xjdf.setAttribute(AttributeName.PARTVERSION, pv.remove(0));
			}
		}

		void splitIndex(final KElement xjdf, final KElement walk, final String attribute)
		{
			final VString indexes = StringUtil.tokenize(walk.getAttribute(attribute), null, false);
			final KElement parent = xjdf.getParentNode_KElement();
			final int size = parent == null ? 0 : ContainerUtil.size(indexes);
			if (size > 2)
			{
				for (int i = size - 2; i > 0; i -= 2)
				{
					final String single = indexes.remove(0) + " " + indexes.remove(0);
					final KElement clone = parent.copyElement(xjdf, xjdf);
					clone.setAttribute(attribute, single);
				}
				final String single = indexes.remove(0) + " " + indexes.remove(0);
				xjdf.setAttribute(attribute, single);
			}
		}
	}

	/**
	 * class that cleans up redundant partition keys
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
				moveToResource(partAmount);
				removeRedundantPartKeys(partAmount);
			}
			return super.walk(partAmount, dummy);
		}

		void moveToResource(final JDFPartAmount partAmount)
		{
			if (partAmount.hasNonEmpty(AttributeName.TRANSFORMATION) || partAmount.hasNonEmpty(AttributeName.ORIENTATION))
			{
				final KElement parentRes = partAmount.getDeepParent(XJDFConstants.Resource, 0);
				final List<JDFPartAmount> vPA = partAmount.getParentNode_KElement().getChildArrayByClass(JDFPartAmount.class, false, 0);
				for (int i = 1; i < vPA.size(); i++)
				{
					final KElement parentResClone = parentRes.getParentNode_KElement().copyElement(parentRes, parentRes.getNextSiblingElement());
					final KElement ap = parentResClone.getElement(ElementName.AMOUNTPOOL);
					ap.removeChildrenByClass(JDFPartAmount.class);
					final JDFPartAmount paClone = (JDFPartAmount) ap.moveElement(partAmount, null);
					moveToResource(paClone);
				}
				parentRes.moveAttribute(AttributeName.TRANSFORMATION, partAmount);
				parentRes.moveAttribute(AttributeName.ORIENTATION, partAmount);
				final ResourceHelper resourceHelper = new ResourceHelper(parentRes);
				final VJDFAttributeMap map = resourceHelper.getPartMapVector();
				final VJDFAttributeMap partMap = partAmount.getPartMapVector();
				final VJDFAttributeMap combined = map.getOrMaps(partMap);
				resourceHelper.setPartMapVector(combined);
				partAmount.setPartMap(null);
			}

		}

		/**
		 * @param partAmount
		 */
		void removeRedundantPartKeys(final JDFPartAmount partAmount)
		{
			final VElement parentParts = getParentParts(partAmount);
			if (parentParts != null)
			{
				final VString keys = new VString();
				for (final KElement e : parentParts)
				{
					final JDFPart p = (JDFPart) e;
					final JDFAttributeMap partMap = p.getPartMap();
					final Collection<String> nextKeys = partMap.keySet();
					ContainerUtil.appendUnique(keys, nextKeys);
				}
				VJDFAttributeMap vPA = partAmount.getPartMapVector();
				if (vPA == null)
				{
					vPA = new VJDFAttributeMap();
				}
				if (vPA != null && keys != null && keys.size() > 0)
				{
					vPA.removeKeys(keys);
				}
				if (vPA.isEmpty())
				{
					vPA = null;
				}

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
		 * @return
		 */
		KElement copyToPlaceObject(final KElement xjdf)
		{
			final KElement po = xjdf.getParentNode_KElement().insertBefore(XJDFConstants.PlacedObject, xjdf, null);
			final StringArray poAttribs = JDFToXJDFDataCache.getPlacedObjectAttribs();
			for (final String att : poAttribs)
			{
				po.moveAttribute(att, xjdf);
			}
			moveToStripMark(xjdf);
			po.moveElement(xjdf, null);
			return po;
		}

		void moveToStripMark(KElement xjdf)
		{
			for (KElement child : xjdf.getChildList())
			{
				if (JDFToXJDFDataCache.getStripMarkElements().contains(child.getLocalName()))
				{
					KElement layout = xjdf.getDeepParent(ElementName.LAYOUT, 0);
					if (layout != null)
					{
						KElement sm = layout.appendElement(ElementName.STRIPMARK);
						sm.moveElement(child, null);
					}
				}
			}

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
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
		 * @author Rainer Prosi, Heidelberger Druckmaschinen
		 */
	}

	private static StringArray noPrint = new StringArray(
			new String[] { EnumType.ImageSetting.getName(), EnumType.Imposition.getName(), EnumType.Interpreting.getName(), EnumType.Rendering.getName() });

	private static StringArray print = new StringArray(new String[] { EnumType.ConventionalPrinting.getName(), EnumType.DigitalPrinting.getName() });

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 */
	protected class WalkMediaSet extends WalkResourceSet
	{
		/**
		 *
		 */
		public WalkMediaSet()
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
			return !retainAll && toCheck.getLocalName().equals(SetHelper.RESOURCE_SET) && ElementName.MEDIA.equals(toCheck.getAttribute(AttributeName.NAME));
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
			KElement parent = xjdf.getParentNode_KElement();
			if (isRemoveUsage(parent))
			{
				xjdf.removeAttribute(AttributeName.USAGE);
			}
			return super.walk(xjdf, dummy);
		}

		boolean isRemoveUsage(KElement parent)
		{
			boolean isXJDF = parent != null && XJDFConstants.XJDF.equals(parent.getLocalName());
			VString types = newRootHelper.getTypes();
			isXJDF = isXJDF && ContainerUtil.containsAny(types, print);
			return isXJDF && !ContainerUtil.containsAny(types, noPrint);
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 */
	protected class WalkRunListSet extends WalkResourceSet
	{
		/**
		 *
		 */
		public WalkRunListSet()
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
			return !retainAll && toCheck.getLocalName().equals(SetHelper.RESOURCE_SET) && ElementName.RUNLIST.equals(toCheck.getAttribute(AttributeName.NAME));
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
			splitPages(xjdf);
			return super.walk(xjdf, dummy);
		}

		/**
		 * @author Rainer Prosi, Heidelberger Druckmaschinen
		 */

		void splitPages(KElement xjdf)
		{
			SetHelper sh = SetHelper.getHelper(xjdf);
			if (sh.size() > 0)
			{
				int i = 0;
				for (ResourceHelper rh : sh.getPartitionList())
				{
					JDFRunList ruli = (JDFRunList) rh.getResource();
					JDFIntegerList pages = ruli == null ? null : JDFIntegerList.createIntegerList(ruli.getNonEmpty(AttributeName.PAGES));
					ResourceHelper rh2 = rh;
					int lastNPage = 0;
					if (pages != null)
					{
						if (pages.size() > 2)
						{
							for (int pos = 0; pos < pages.size(); pos += 2)
							{
								rh2 = (pos > 0) ? rh2.clonePartition() : rh;
								VJDFAttributeMap vParts = rh2.getPartMapVector();
								if (!VJDFAttributeMap.isEmpty(vParts))
								{
									vParts.put("Run", "SplitRun" + i++);
									rh2.setPartMapVector(vParts);
								}
								else
								{
									rh2.setPartMap(new JDFAttributeMap("Run", "SplitRun" + i++));
								}
								JDFRunList ruli2 = (JDFRunList) rh2.getResource();
								ruli2.setAttribute(AttributeName.PAGES, pages.getInt(pos) + " " + pages.getInt(pos + 1));
								int nPage = 1 + Math.abs(pages.getInt(pos) - pages.getInt(pos + 1));
								ruli2.setAttribute(AttributeName.NPAGE, "" + nPage);
								if (ruli2.hasNonEmpty(AttributeName.LOGICALPAGE) && lastNPage > 0)
								{
									ruli2.addAttribute(AttributeName.LOGICALPAGE, lastNPage, null);
								}
								lastNPage = nPage;
							}
						}
					}
				}
			}
		}
	}

	/**
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
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public class WalkDeliveryParamsSet extends WalkResourceSet
	{
		/**
		 *
		 */
		public WalkDeliveryParamsSet()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkIntentSet#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && ElementName.DELIVERYPARAMS.equals(toCheck.getAttribute("Name"));
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
			final SetHelper sh = SetHelper.getHelper(xjdf);

			final List<ResourceHelper> partitionList = sh.getPartitionList();

			for (final ResourceHelper rh : partitionList)
			{
				final JDFDeliveryParams dp = (JDFDeliveryParams) rh.getResource();
				int i = 0;
				for (final JDFDrop drop : dp.getAllDrop())
				{
					String dropid = drop.getDropID();
					if (StringUtil.isEmpty(dropid))
					{
						dropid = "DROP_" + i++;
					}
					final ResourceHelper rh2 = StringUtil.isEmpty(dropid) ? sh.getCreateExactPartition(null, false)
							: sh.getCreateExactPartition(new JDFAttributeMap(AttributeName.DROPID, dropid), false);
					JDFDeliveryParams dp2 = (JDFDeliveryParams) rh2.getResource();
					if (dp2 == null)
					{
						dp2 = (JDFDeliveryParams) rh2.getRoot().copyElement(dp, null);
						dp2.setAttributes(drop);
						dp2.removeChildren(ElementName.DROP, null);
					}
					for (final JDFDropItem dropitem : drop.getAllDropItem())
					{
						dropitem.renameAttribute("ProductRef", XJDFConstants.ItemRef);
						dropitem.renameAttribute("ComponentRef", XJDFConstants.ItemRef);
						if (!dropitem.hasNonEmpty(XJDFConstants.ItemRef))
						{
							String productRef = rh.getPartKey(XJDFConstants.Product);
							if (productRef != null)
							{
								final XJDFHelper xh = sh.getXJDF();
								final ProductHelper p = xh.getProductByExternalID(productRef);
								if (p != null)
								{
									productRef = p.ensureID();
								}
							}
							dropitem.setAttribute(XJDFConstants.ItemRef, productRef);
						}
						if (dropitem.hasNonEmpty(XJDFConstants.ItemRef))
						{
							dp2.moveElement(dropitem, null);
						}
					}
				}
				rh.deleteNode();
			}
			return super.walk(xjdf, dummy);
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public class WalkStation extends WalkElement
	{
		/**
		 *
		 */
		public WalkStation()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return VString.getVString(ElementName.STATION, null);
		}

		/**
		 * we zapp dropitems that don't reference anything
		 *
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			final int sa = ((JDFStation) xjdf).getStationAmount();
			final KElement ret = super.walk(xjdf, dummy);
			for (int i = 1; i < sa; i++)
			{
				ret.getParentNode_KElement().copyElement(ret, ret);
			}
			return ret;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#updateAttributes(org.cip4.jdflib.core.KElement)
		 */
		@Override
		void updateAttributes(final KElement xjdf)
		{
			xjdf.removeAttribute(AttributeName.STATIONAMOUNT);
			super.updateAttributes(xjdf);
			xjdf.renameAttribute(XJDFConstants.BinderySignatureID, XJDFConstants.BinderySignatureIDs);
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
			moveGeneralIDs(xjdf);
			return ret;
		}

		protected void moveGeneralIDs(final KElement xjdf)
		{
			final VElement v = xjdf.getChildElementVector(ElementName.GENERALID, null);
			for (final KElement e : v)
			{
				xjdf.getParentNode_KElement().moveElement(e, null);
			}
		}

		/**
		 * @param xjdf
		 */
		void moveToMisconsumable(final KElement xjdf)
		{
			// nop hook
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
			return XJDFHelper.RESOURCE.equals(toCheck.getLocalName());
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
			final ResourceHelper rh = new ResourceHelper(xjdf);
			moveAmountToPool(rh);
			final KElement ret = super.walk(xjdf, dummy);
			return ret;
		}

		private void moveAmountToPool(final ResourceHelper rh)
		{
			final KElement res = rh.getRoot();
			final int amount = res.getIntAttribute(AttributeName.AMOUNT, null, 0);
			res.removeAttribute(AttributeName.AMOUNT);
			if (amount > 0 && rh.getAmountPool() == null)
			{
				rh.setAmount(amount, null, true);
			}

		}

		/**
		 * @param xjdf
		 */
		void moveToSet(final KElement xjdf)
		{
			final KElement set = xjdf == null ? null : xjdf.getParentNode_KElement();
			if (set != null)
			{
				if (xjdf.hasNonEmpty(AttributeName.UNIT))
				{
					set.moveAttribute(AttributeName.UNIT, xjdf);
				}
				final String desc = xjdf.getNonEmpty(AttributeName.DESCRIPTIVENAME);
				final String setdesc = set.getNonEmpty(AttributeName.DESCRIPTIVENAME);
				if (setdesc != null && StringUtil.equals(desc, setdesc))
				{
					set.removeAttribute(AttributeName.DESCRIPTIVENAME);
				}
			}
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 */
	protected class WalkResourceSet extends WalkElement
	{
		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			moveFromSet(xjdf);
			return super.walk(xjdf, dummy);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return XJDFConstants.ResourceSet.equals(toCheck.getLocalName());
		}

		/**
		 * @param xjdf
		 */
		void moveFromSet(final KElement xjdf)
		{
			for (final String a : new String[] { AttributeName.ORIENTATION, AttributeName.TRANSFORMATION })
			{
				final String val = xjdf.getNonEmpty(a);
				if (val != null)
				{
					xjdf.removeAttribute(a);
					final VElement v = xjdf.getChildElementVector(ElementName.RESOURCE, null);
					for (final KElement r : v)
					{
						r.setAttribute(a, val);
					}
				}
			}

		}

		/**
		 *
		 */
		public WalkResourceSet()
		{
			super();
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
			return XJDFConstants.Intent.equals(toCheck.getLocalName());
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#updateAttributes(org.cip4.jdflib.core.KElement)
		 */
		@Override
		void updateAttributes(final KElement xjdf)
		{
			xjdf.removeAttribute(AttributeName.STATUS);
			xjdf.removeAttribute(AttributeName.STATUSDETAILS);
			xjdf.removeAttribute(AttributeName.ID);
			super.updateAttributes(xjdf);
		}

	}

	/**
	 * @param newRoot
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
		newVersion = EnumVersion.Version_2_0;
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
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
		 */
		private void reorderSets(final JDFElement xjdf)
		{
			final Vector<SetHelper> v = new XJDFHelper(xjdf).getSets();
			if (v == null)
			{
				return; // nothing to do
			}

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
	}

	/**
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
				final IntentHelper insert = ph.getIntent(XJDFConstants.AssemblingIntent);
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
	 * @author rainer prosi
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
	 * @author rainer prosi
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
	 * @author rainer prosi
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
	 * @author rainer prosi
	 */
	public class WalkNodeInfo extends WalkResourceElement
	{
		/**
		 *
		 */
		public WalkNodeInfo()
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
			return new VString(ElementName.NODEINFO, null);
		}

		void moveEmployee(final KElement xjdf)
		{
			final JDFContact c = ((JDFNodeInfo) xjdf).getContact();
			if (c != null)
			{
				final SetHelper contactSet = newRootHelper.getCreateSet(ElementName.CONTACT, EnumUsage.Input);
				final ResourceHelper newPart = contactSet.getCreatePartition(XJDFConstants.ContactType, "Employee", false);
				if (newPart.getResource() == null)
				{
					newPart.getRoot().moveElement(c, null);
					newPart.getRoot().moveAttribute(XJDFConstants.ExternalID, c);
				}
				else
				{
					c.deleteNode();
				}
			}
		}

		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			moveEmployee(xjdf);
			return super.walk(xjdf, dummy);
		}

	}

	/**
	 * @author rainer prosi
	 */
	public class WalkAssembly extends WalkResourceElement
	{
		/**
		 *
		 */
		public WalkAssembly()
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
			return new VString(ElementName.ASSEMBLY, null);
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			updateSections((JDFAssembly) xjdf);
			return super.walk(xjdf, dummy);
		}

		private void updateSections(final JDFAssembly assembly)
		{
			final EnumOrder o = assembly.getOrder();
			final JDFAssemblySection ass = assembly.getElementByClass(JDFAssemblySection.class, 0, false);
			if (ass != null)
			{
				if (EnumOrder.Collecting.equals(o))
				{
					final List<JDFAssemblySection> l = assembly.getChildArrayByClass(JDFAssemblySection.class, false, 0);
					final int size = ContainerUtil.size(l);
					if (size > 1)
					{
						for (int i = 1; i < size; i++)
						{
							l.get(i - 1).moveElement(l.get(i), null);
						}
					}
				}
				assembly.setOrder(EnumOrder.List);
			}
		}
	}

	/**
	 * @author rainer prosi
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

	/**
	 * @author rainer prosi
	 */
	public class WalkDigitalPrintingParams extends WalkResourceElement
	{
		/**
		 *
		 */
		public WalkDigitalPrintingParams()
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
			return new VString(ElementName.DIGITALPRINTINGPARAMS, null);
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#moveToMisconsumable(org.cip4.jdflib.core.KElement, java.lang.String)
		 */
		void moveToStacking(final KElement xjdf)
		{
			final String bin = xjdf.getNonEmpty(AttributeName.OUTPUTBIN);
			final String sa = xjdf.getNonEmpty(AttributeName.STACKAMOUNT);
			if (bin != null || sa != null)
			{
				if (newRootHelper.indexOfType(JDFConstants.TYPE_STACKING, 0) < 0)
				{
					newRootHelper.addType(EnumType.Stacking);
				}
				final SetHelper sh = newRootHelper.getCreateSet(ElementName.STACKINGPARAMS, EnumUsage.Input);
				final ResourceHelper rh2 = sh.getCreateVPartition(ResourceHelper.getHelper(xjdf).getPartMapVector(), true);
				final KElement sp = rh2.getCreateResource();
				sp.moveAttribute(AttributeName.OUTPUTBIN, xjdf);
				sp.moveAttribute(AttributeName.STACKAMOUNT, xjdf);
			}
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			moveToStacking(xjdf);
			updateMedia(xjdf);
			return super.walk(xjdf, dummy);
		}

		void updateMedia(final KElement xjdf)
		{
			final String ref = xjdf.getNonEmpty("MediaRef");
			if (ref != null)
			{
				xjdf.removeAttribute(XJDFConstants.MediaRef);
				final ResourceHelper helper = ResourceHelper.getHelper(xjdf);
				final VJDFAttributeMap maps = helper == null ? null : helper.getPartMapVector();
				if (!ContainerUtil.isEmpty(maps))
				{
					// first check for automagically created component
					KElement m = newRootHelper.getRoot().getChildWithAttribute(XJDFConstants.Resource, AttributeName.ID, null, "Comp." + ref, 0, false);
					if (m == null)
					{
						m = newRootHelper.getRoot().getChildWithAttribute(XJDFConstants.Resource, AttributeName.ID, null, ref, 0, false);
					}
					final ResourceHelper medHelp = ResourceHelper.getHelper(m);
					if (medHelp != null)
					{
						VJDFAttributeMap vPartMedia = medHelp.getPartMapVector();
						vPartMedia.appendUnique(maps);
						medHelp.setPartMapVector(vPartMedia);
					}
				}
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
			fixReferencedContactParts(xjdf);
			xjdf.removeAttribute(XJDFConstants.ContactRefs);
		}

		public void fixReferencedContactParts(final KElement xjdf)
		{
			final ResourceHelper rh = ResourceHelper.getHelper(xjdf);
			final VJDFAttributeMap vMap = rh == null ? null : rh.getPartMapVector();
			final JDFAttributeMap map = vMap == null ? null : vMap.getCommonMap();
			final String pp = map == null ? null : map.get(JDFConstants.PRODUCT);
			final StringArray a = pp == null ? null : StringArray.getVString(xjdf.getNonEmpty(XJDFConstants.ContactRefs), null);
			if (a != null)
			{
				final SetHelper cs = newRootHelper.getSet(ElementName.CONTACT, null);
				for (final String id : a)
				{
					final ResourceHelper c = cs == null ? null : cs.getPartition(id);
					if (c != null)
					{
						c.ensurePart(JDFConstants.PRODUCT, pp);
					}
				}
			}
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
	 */
	protected class WalkResourceMessage extends WalkMessage
	{

		/**
		 *
		 */
		public WalkResourceMessage()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			mergeResourceInfos(xjdf);
			return super.walk(xjdf, dummy);
		}

		private void mergeResourceInfos(final KElement xjdf)
		{
			final List<JDFResourceInfo> vr = xjdf.getChildArrayByClass(JDFResourceInfo.class, false, 0);

			if (!ContainerUtil.isEmpty(vr))
			{
				final ListMap<JDFAttributeMap, SetHelper> setMap = new ListMap<>();
				for (final JDFResourceInfo ri : vr)
				{
					final JDFAttributeMap metaMap = getMetaMap(ri);
					final Collection<KElement> childArray = ri.getChildList(XJDFConstants.ResourceSet, null);
					for (final KElement e : childArray)
					{
						setMap.putOne(metaMap, new SetHelper(e));
					}
				}

				for (final List<SetHelper> sets : setMap.values())
				{
					combineSameSets(sets);
				}
				for (final JDFResourceInfo ri : vr)
				{
					if (!ri.hasChildElement(XJDFConstants.ResourceSet, null))
					{
						ri.deleteNode();
					}
				}
			}

		}

		private JDFAttributeMap getMetaMap(final JDFResourceInfo ri)
		{
			final JDFAttributeMap m = ri.getAttributeMap();
			m.reduceMap(metaKeys);
			return m;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(final KElement e)
		{
			return super.matches(e) && e.getLocalName().endsWith(XJDFConstants.Resource);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString("SignalResource ResponseResource");
		}

	}

	/**
	 * class that ensures that we do not have signaturename partitions
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 */
	protected class WalkKnownMessageResponse extends WalkMessage
	{

		/**
		 *
		 */
		public WalkKnownMessageResponse()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			splitMessageServices(xjdf);
			return super.walk(xjdf, dummy);
		}

		void splitMessageServices(KElement xjdf)
		{
			List<JDFMessageService> v = xjdf.getChildArrayByClass(JDFMessageService.class, false, 0);
			for (JDFMessageService ms : v)
			{
				splitMessageService(xjdf, ms);
			}
		}

		void splitMessageService(KElement xjdf, JDFMessageService ms)
		{
			int i = 0;
			boolean c = ms.getCommand();
			boolean s = ms.getSignal();
			boolean q = ms.getQuery();
			String t = ms.getType();
			ms.removeAttributes(new StringArray("Command Query Signal"));
			if (c)
			{
				ms.setType(ElementName.COMMAND + t);
				i++;
			}
			if (s)
			{
				JDFMessageService ms0 = (JDFMessageService) (i > 0 ? xjdf.copyElement(ms, ms) : ms);
				ms0.setType(ElementName.SIGNAL + t);
				i++;
			}
			if (q)
			{
				JDFMessageService ms0 = (JDFMessageService) (i > 0 ? xjdf.copyElement(ms, ms) : ms);
				ms0.setType(ElementName.QUERY + t);
			}

		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return new VString("ResponseKnownMessages");
		}

	}

	/**
	 * class that ensures that we do not have signaturename partitions
	 *
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
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
			SetHelper layoutseth = newRootHelper.getSet(ElementName.LAYOUT, EnumUsage.Input);
			if (layoutseth == null)
			{
				layoutseth = newRootHelper.getCreateSet(ElementName.LAYOUT, EnumUsage.Output);
			}

			final VJDFAttributeMap vmap = new ResourceHelper(strippingParams.getParentNode_KElement()).getPartMapVector();
			mergeStrippingParamsLayout((JDFStrippingParams) strippingParams, layoutseth, vmap);

			super.walk(strippingParams, dummy);
			return null; // stop after merging

		}

		/**
		 * @param strippingParams
		 * @param layoutseth
		 * @param layoutMap
		 * @return
		 */
		private JDFAttributeMap mergeStrippingParamsLayout(final JDFStrippingParams strippingParams, final SetHelper layoutseth, final VJDFAttributeMap layoutMaps)
		{
			final JDFAttributeMap layoutMap = VJDFAttributeMap.isEmpty(layoutMaps) ? new JDFAttributeMap() : layoutMaps.get(0);
			if (isRemoveSignatureName())
			{
				layoutMap.remove(AttributeName.SIGNATURENAME);
			}

			final String bsResID = strippingParams.getNonEmpty("BinderySignatureRef");
			String bsID = getBSID(strippingParams, null);
			String newBSID = bsID;
			if (StringUtil.isEmpty(bsID))
			{
				final String bsName = getBSName(strippingParams, layoutMap, bsResID);
				bsID = getBSID(strippingParams, bsName);
				newBSID = getNewBSID(bsID, layoutseth);
			}
			final String cellIndex = layoutMap.remove(AttributeName.CELLINDEX);
			layoutMap.remove(AttributeName.BINDERYSIGNATURENAME);
			layoutMap.remove(XJDFConstants.BinderySignatureID);
			final ResourceHelper layoutPartitionH = layoutseth.getCreateVPartition(layoutMaps, true);
			final JDFLayout layoutPartition = (JDFLayout) layoutPartitionH.getResource();

			ensureLayoutPositions(strippingParams, layoutPartition, newBSID);

			final VElement childElementVector = updateCells(strippingParams, cellIndex);

			final ResourceHelper bsHelper = getBSHelper(bsID, newBSID, bsResID, strippingParams);

			final JDFBinderySignature bs = (JDFBinderySignature) bsHelper.getCreateResource();
			moveStripCells(bs, childElementVector);
			moveBSFromStripping(bs, strippingParams);
			final SetHelper niSet = newRootHelper.getSet(ElementName.NODEINFO, 0);
			final ResourceHelper ni = niSet == null ? null : niSet.getPartition(layoutMap);
			moveGangSourceFromStripping(ni, bsID, strippingParams);

			updatePositions(newBSID, layoutPartition);
			strippingParams.removeAttribute(ElementName.BINDERYSIGNATURE + JDFConstants.REF);
			strippingParams.removeAttribute(XJDFConstants.BinderySignatureID);
			if (layoutPartition.hasChildElement(ElementName.FILESPEC, null))
			{
				strippingParams.removeChildrenByClass(JDFFileSpec.class);
			}
			layoutPartition.copyInto(strippingParams, false);
			return layoutMap;
		}

		protected void updatePositions(final String bsID, final JDFLayout layoutPartition)
		{
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
		}

		protected VElement updateCells(final JDFStrippingParams strippingParams, final String cellIndex)
		{
			final VElement childElementVector = strippingParams.getChildElementVector(ElementName.SIGNATURECELL, null);
			if (childElementVector != null)
			{
				for (final KElement kid : childElementVector)
				{
					final JDFStripCellParams scp = (JDFStripCellParams) kid;
					scp.setAttribute(AttributeName.CELLINDEX, cellIndex);
				}
			}
			return childElementVector;
		}

		protected ResourceHelper getBSHelper(final String bsID, final String newBSID, final String bsResID, final JDFStrippingParams strippingParams)
		{
			SetHelper bsSet = newRootHelper.getSet(ElementName.BINDERYSIGNATURE, 0);
			if (bsSet == null)
			{
				bsSet = newRootHelper.getCreateSet(ElementName.BINDERYSIGNATURE, EnumUsage.Input);
			}

			ResourceHelper bsHelper = bsSet.getPartition(XJDFConstants.BinderySignatureID, bsID);

			if (bsHelper == null || !StringUtil.equals(bsID, bsHelper.getPartKey(XJDFConstants.BinderySignatureID)))
			{
				bsHelper = bsSet.getExactPartition(new JDFAttributeMap(XJDFConstants.BinderySignatureID, bsID));
			}
			if (bsHelper == null || !StringUtil.equals(bsID, bsHelper.getPartKey(XJDFConstants.BinderySignatureID)))
			{
				bsHelper = bsSet.getPartition(bsResID);
			}
			if (bsHelper == null)
			{
				bsHelper = bsSet.getPartition(0);
			}
			final JDFAttributeMap bsMap = StringUtil.isEmpty(newBSID) ? null : new JDFAttributeMap(XJDFConstants.BinderySignatureID, newBSID);
			if (bsHelper == null)
			{
				bsHelper = bsSet.appendPartition(bsMap, true);
				final JDFBinderySignature binderySignature = (JDFBinderySignature) bsHelper.getResource();
				binderySignature.setBinderySignatureType(EnumBinderySignatureType.Grid);
				binderySignature.setNumberUp(1, 1);
			}
			else if (!StringUtil.equals(newBSID, bsHelper.getPartKey(XJDFConstants.BinderySignatureID))
					&& !StringUtil.isEmpty(bsHelper.getPartKey(XJDFConstants.BinderySignatureID)) && !StringUtil.equals(bsResID, newBSID))
			{
				bsHelper = new ResourceHelper(bsSet.copyHelper(bsHelper));
				bsHelper.setPartMap(bsMap);
				bsHelper.removeAttribute(AttributeName.ID, null);
			}
			else
			{
				bsHelper.ensurePart(XJDFConstants.BinderySignatureID, newBSID);
			}
			return bsHelper;
		}

		String getNewBSID(final String bsID, final SetHelper layoutSet)
		{
			int index = 1;
			String bsIDNew = bsID;

			KElement foundpos = layoutSet.getRoot().getChildByTagName(ElementName.POSITION, null, 0, new JDFAttributeMap(XJDFConstants.BinderySignatureID, bsID), false, false);
			while (foundpos != null)
			{
				bsIDNew = bsID + "." + index++;
				foundpos = layoutSet.getRoot().getChildByTagName(ElementName.POSITION, null, 0, new JDFAttributeMap(XJDFConstants.BinderySignatureID, bsIDNew), false, false);
			}

			return bsIDNew;
		}

		/**
		 * @param strippingParams
		 * @param bsName
		 * @param bsResID
		 * @return
		 */
		private String getBSID(final JDFStrippingParams strippingParams, final String bsName)
		{
			final String bsID = strippingParams.getNonEmpty(XJDFConstants.BinderySignatureID);
			return (bsID == null) ? bsName : bsID;
		}

		private void moveGangSourceFromStripping(final ResourceHelper nih, final String bsName, final JDFStrippingParams strippingParams)
		{
			final String jobID = strippingParams.getNonEmpty(AttributeName.JOBID);
			if (jobID != null)
			{
				if (nih == null)
				{
					strippingParams.removeAttribute(AttributeName.JOBID);
				}
				else
				{
					final KElement ni = nih.getCreateResource();
					final KElement gangSrc = ni.getCreateChildWithAttribute(ElementName.GANGSOURCE, XJDFConstants.BinderySignatureID, null, bsName, 0);
					if (!gangSrc.hasNonEmpty(AttributeName.JOBID))
					{
						gangSrc.moveAttribute(AttributeName.JOBID, strippingParams);
					}
					if (!gangSrc.hasNonEmpty(AttributeName.COPIES))
					{
						final ProductHelper ph = getProduct(jobID, bsName);
						if (ph != null)
						{
							gangSrc.setAttribute(AttributeName.COPIES, ph.getAmount(), null);
						}
						else
						{
							gangSrc.setAttribute(AttributeName.COPIES, 0, null);
						}
					}
				}
			}
		}

		protected ProductHelper getProduct(final String jobID, final String bsName)
		{
			final Vector<ProductHelper> vph = newRootHelper.getProductHelpers();
			if (vph != null)
			{
				for (final ProductHelper ph : vph)
				{
					for (final String s : new VString("ID ExternalID", null))
					{
						final String attribute = ph.getAttribute(s);
						if (attribute != null && (attribute.indexOf(bsName) >= 0 || attribute.indexOf(jobID) >= 0))
						{
							return ph;
						}
					}
				}
			}
			return null;
		}

		/**
		 * @param strippingParams
		 * @param map
		 * @param bsResID
		 * @return
		 */
		String getBSName(final JDFStrippingParams strippingParams, final JDFAttributeMap map, final String bsResID)
		{
			String bsName = map == null ? null : map.remove(AttributeName.BINDERYSIGNATURENAME);
			if (bsName == null)
			{
				bsName = map == null ? null : map.remove(XJDFConstants.BinderySignatureID);
			}
			if (bsName == null)
			{
				bsName = strippingParams.getNonEmpty(XJDFConstants.BinderySignatureIDs);
			}
			if (bsName == null)
			{
				final SetHelper bsSet = newRootHelper.getSet(ElementName.BINDERYSIGNATURE, 0);
				if (bsSet != null)
				{
					final ResourceHelper bsHelper = bsSet.getPartition(bsResID);
					if (bsHelper != null)
					{
						bsName = bsHelper.getPartKey(AttributeName.BINDERYSIGNATURENAME);
						if (bsName == null)
						{
							bsName = bsHelper.getPartKey(XJDFConstants.BinderySignatureID);
						}
					}
				}
				if (bsName == null)
				{
					bsName = StringUtil.isEmpty(bsResID) ? "BS" : bsResID;
				}
			}
			return bsName;
		}

		/**
		 * @param strippingParams
		 * @param layoutPartition
		 * @param bsID
		 */
		private void ensureLayoutPositions(final JDFStrippingParams strippingParams, final JDFLayout layoutPartition, final String bsID)
		{
			Collection<KElement> positions = strippingParams.getChildList(ElementName.POSITION, null);
			if (!ContainerUtil.isEmpty(positions))
			{
				layoutPartition.moveArray(positions, null);
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
				position.setAttribute(XJDFConstants.BinderySignatureID, bsID);
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
			if (bs == null)
			{
				strippingParams.removeAttribute(AttributeName.INNERMOSTSHINGLING);
				strippingParams.removeAttribute(AttributeName.OUTERMOSTSHINGLING);
			}
			else
			{
				bs.moveAttribute(AttributeName.INNERMOSTSHINGLING, strippingParams);
				bs.moveAttribute(AttributeName.OUTERMOSTSHINGLING, strippingParams);
				final EnumWorkStyle ws = strippingParams.getWorkStyle();
				if (EnumWorkStyle.Simplex.equals(ws))
				{
					bs.getCreateSignatureCell(0);
					final Collection<JDFSignatureCell> scs = bs.getAllSignatureCell();
					for (final JDFSignatureCell sc : scs)
					{
						if (!sc.hasNonEmpty(AttributeName.SIDES))
						{
							sc.setAttribute(AttributeName.SIDES, EnumSides.OneSided.getName());
						}
					}
				}

			}
			// TODO where to move stripmarks? - stay in strippingparams or move to the appropriate binderysignature, stripcell or strippingparams
		}

		/**
		 * move and merge stripcellparams and signaturecells
		 *
		 * @param bindSig
		 * @param childElementVector
		 */
		private void moveStripCells(final JDFBinderySignature bindSig, final VElement childElementVector)
		{
			if (childElementVector == null)
			{
				return;
			}
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
					{
						vRet.add(sc);
					}
				}
			}
			if (vRet.size() == 0)
			{
				vRet.add(bindSig.appendSignatureCell());
			}
			return vRet;
		}

		/**
		 * @param sc
		 * @param il
		 * @return
		 */
		private boolean matchesIndex(final JDFSignatureCell sc, JDFIntegerList il)
		{
			if (il == null)
			{
				return true;
			}

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
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFStrippingParams;
		}

	}

	/**
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
			{
				sender.setAttribute(AttributeName.DEVICEID, "dummy");
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

	void combineSameSets()
	{
		final KElement root = newRootHelper.getRoot();
		final IWalker w = getFactory().getWalker(root);
		if (w instanceof WalkXJDF)
		{
			combineSameSets((JDFElement) root);
		}

	}

	/**
	 * @param v
	 */
	void combineSameSets(final JDFElement xjdf)
	{
		if (!isRetainAll())
		{
			final Vector<SetHelper> v = new XJDFHelper(xjdf).getSets();
			if (v != null)
			{
				combineSameSets(v);
			}
		}
	}

	void combineSameSets(final List<SetHelper> v)
	{
		while (v.size() > 1)
		{
			final ArrayList<SetHelper> sameSets = new ArrayList<>();
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
				final List<ResourceHelper> parts = sameSets.get(j).getPartitionList();
				for (final ResourceHelper ph : parts)
				{
					root.copyElement(ph.getRoot(), null);
				}
				sameSets.get(j).getRoot().deleteNode();
			}
		}
	}

	/**
	 * @param firstSet
	 * @param next
	 * @return
	 */
	private boolean sameSetType(final SetHelper firstSet, final SetHelper next)
	{
		boolean same = firstSet.getName().equals(next.getName());
		if (same)
		{
			same = StringUtil.equals(firstSet.getProcessUsage(), next.getProcessUsage());
		}
		if (same)
		{
			same = ContainerUtil.equals(firstSet.getUsage(), next.getUsage());
		}
		if (same)
		{
			same = ContainerUtil.equals(firstSet.getXPathValue("@CombinedProcessIndex"), next.getXPathValue("@CombinedProcessIndex"));
		}
		if (same)
		{
			same = ContainerUtil.equals(firstSet.getXPathValue("Resource/Media/@MediaType"), next.getXPathValue("Resource/Media/@MediaType"));
		}

		return same;
	}
}
