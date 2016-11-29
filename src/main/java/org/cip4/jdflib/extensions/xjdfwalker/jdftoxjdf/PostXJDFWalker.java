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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
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
import org.cip4.jdflib.extensions.AuditResourceHelper;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.PartitionHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFDrop;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFSignatureCell;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * some generic postprocessing that is better done on the XJDF after JDF to XJDF Conversion 
 * such as merging stripping and Layout
 * 
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class PostXJDFWalker extends BaseElementWalker
{
	/**
	 * if true merge stripping and layout
	 */
	private boolean mergeLayout;
	protected JDFElement newRoot;
	/**
	 * if false, intents are never partitioned
	 */
	private boolean bIntentPartition;
	/**
	 * if false, all deliveryintents and artdeliveryintents are converted to the respective process resources
	 */
	private boolean bDeliveryIntent;
	private boolean retainAll;
	private boolean reorderElements;

	/**
	 * @return the reorderElements
	 */
	protected boolean isReorderElements()
	{
		return reorderElements;
	}

	/**
	 * @param reorderElements the reorderElements to set
	 */
	protected void setReorderElements(boolean reorderElements)
	{
		this.reorderElements = reorderElements;
	}

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
	void setMergeLayout(boolean mergeLayout)
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
	void setIntentPartition(boolean bIntentPartition)
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
	void setDeliveryIntent(boolean bDeliveryIntent)
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
	void setRemoveSignatureName(boolean removeSignatureName)
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

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			updateNamespaces(xjdf);
			if (isReorderElements())
			{
				reorderElements(xjdf);
			}
			return xjdf;
		}

		void updateNamespaces(final KElement xjdf)
		{
			if (xjdf.hasAttribute(AttributeName.XMLNS))
				xjdf.removeAttribute(AttributeName.XMLNS);
			if (xjdf.getNamespaceURI().equals(JDFElement.getSchemaURL()))
				xjdf.setNamespaceURI(XJDF20.getSchemaURL());
		}

		/**
		 * 
		 * @param xjdf
		 */
		protected void reorderElements(KElement xjdf)
		{
			xjdf.sortChildren(new KElement.SimpleElementNameComparator());
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
		 * @param part
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement part, final KElement dummy)
		{
			if (isRemoveSignatureName())
			{
				part.removeAttribute(AttributeName.SIGNATURENAME);
			}
			return super.walk(part, dummy);
		}

		@Override
		public boolean matches(KElement e)
		{
			return (e instanceof JDFPart);
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
			JDFPartAmount partAmount = (JDFPartAmount) pa;
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
		private void removeRedundantPartKeys(JDFPartAmount partAmount)
		{
			VElement parentParts = getParentParts(partAmount);
			if (parentParts != null)
			{
				VString keys = new VString();
				for (KElement e : parentParts)
				{
					JDFPart p = (JDFPart) e;
					JDFAttributeMap partMap = p.getPartMap();
					VString nextKeys = partMap.getKeys();
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
				VElement v = partAmount.getParts();
				for (KElement e : v)
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
		private VElement getParentParts(KElement part)
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
		public boolean matches(KElement e)
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
			JDFAmountPool pool = (JDFAmountPool) ap;
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
		void moveActualToAudit(JDFAmountPool partAmount)
		{
			if (partAmount.getDeepParent(ElementName.AUDITPOOL, 0) == null)
			{
				AuditPoolHelper ah = new AuditPoolHelper(newRoot.getCreateElement(ElementName.AUDITPOOL));
				KElement resource = partAmount.getDeepParent(XJDFConstants.Resource, 0);
				PartitionHelper ph = resource == null ? null : new PartitionHelper(resource);
				SetHelper sh = ph == null ? null : ph.getSet();
				if (sh != null)
				{
					AuditResourceHelper arh = ah.getCreateAuditResourceHelper(sh);
					SetHelper shNew = arh.getSet();
					PartitionHelper phNew = shNew.getCreateVPartition(sh.getPartMapVector(), false);
					KElement newAmountPool = phNew.getRoot().copyElement(partAmount, null);
					VElement vpa = newAmountPool.getChildElementVector(ElementName.PARTAMOUNT, null);
					for (KElement pa : vpa)
					{
						pa.removeAttribute(AttributeName.AMOUNT);
						pa.renameAttribute(AttributeName.ACTUALAMOUNT, AttributeName.AMOUNT);
						pa.removeAttribute(AttributeName.WASTE);
						pa.renameAttribute("ActualWaste", AttributeName.WASTE);
					}
					walkTree(shNew.getRoot(), null);
				}
			}

		}

		/**
		 * 
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(KElement e)
		{
			return (e instanceof JDFAmountPool) && e.getDeepParent(ElementName.AUDITPOOL, 0) == null && e.getDeepParent(XJDFConstants.XJMF, 0) == null;
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
		 * @param xjdf
		 *  
		 * @return
		 */
		private KElement copyToPlaceObject(KElement xjdf)
		{
			KElement po = xjdf.getParentNode_KElement().insertBefore(XJDFConstants.PlacedObject, xjdf, null);
			VString poAttribs = JDFToXJDFDataCache.getPlacedObjectAttribs();
			for (String att : poAttribs)
			{
				po.moveAttribute(att, xjdf);
			}
			po.setAttribute(AttributeName.TYPE, xjdf.getLocalName());
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
		public KElement walk(KElement stripCellParams, KElement dummy)
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
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFStrippingParams;
		}

		/**
		 * 
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkResourceElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(KElement strippingParams, KElement dummy)
		{
			if (!mergeLayout)
			{
				super.walk(strippingParams, dummy);
				return strippingParams; // nuff done
			}
			//TODO multiple lower level stripparams partitions
			XJDFHelper h = new XJDFHelper(newRoot);
			SetHelper layoutseth = h.getCreateResourceSet(ElementName.LAYOUT, EnumUsage.Input);

			VJDFAttributeMap vmap = new PartitionHelper(strippingParams.getParentNode_KElement()).getPartMapVector();
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
		private JDFAttributeMap mergeStrippingParamsLayout(JDFStrippingParams strippingParams, SetHelper layoutseth, JDFAttributeMap map)
		{
			String bsName = map.remove(AttributeName.BINDERYSIGNATURENAME);
			String cellIndex = map.remove(AttributeName.CELLINDEX);
			PartitionHelper layoutPartitionH = layoutseth.getCreatePartition(map, true);
			JDFLayout layoutPartition = (JDFLayout) layoutPartitionH.getResource();
			ensureLayoutPositions(strippingParams, layoutPartition);

			JDFBinderySignature bsOld = (JDFBinderySignature) layoutPartition.getChildWithAttribute(ElementName.BINDERYSIGNATURE, AttributeName.BINDERYSIGNATURENAME, null, bsName, 0, true);
			VElement childElementVector = strippingParams.getChildElementVector(ElementName.SIGNATURECELL, null);
			if (childElementVector != null)
			{
				for (KElement kid : childElementVector)
				{
					JDFStripCellParams scp = (JDFStripCellParams) kid;
					scp.setAttribute(AttributeName.CELLINDEX, cellIndex);
				}
			}
			if (bsOld != null)
			{
				moveStripCells(bsOld, childElementVector);
			}
			else
			{
				String bsID = strippingParams.getAttribute(ElementName.BINDERYSIGNATURE + "Ref", null, null);
				PartitionHelper bsHelper = XJDFHelper.getHelper(layoutPartition).getPartition(bsID);
				JDFBinderySignature bs = (bsHelper == null) ? null : (JDFBinderySignature) bsHelper.getResource();
				if (bs != null)
				{
					bs.setBinderySignatureName(bsName);
					moveStripCells(bs, childElementVector);
					moveBSFromStripping(bs, strippingParams);
					VElement positions = layoutPartition.getChildElementVector(ElementName.POSITION, null);
					for (KElement position : positions)
					{
						position.setAttribute(ElementName.BINDERYSIGNATURE + "Ref", bsID);
					}
					strippingParams.removeAttribute(ElementName.BINDERYSIGNATURE + "Ref");
				}
				layoutPartition.copyInto(strippingParams, false);
			}
			return map;
		}

		/**
		 * 
		 *  
		 * @param strippingParams
		 * @param layoutPartition
		 */
		private void ensureLayoutPositions(JDFStrippingParams strippingParams, JDFLayout layoutPartition)
		{
			VElement positions = strippingParams.getChildElementVector(ElementName.POSITION, null);
			if (positions != null && positions.size() > 0)
			{
				layoutPartition.moveElements(positions, null);
			}
			else
			{
				JDFPosition newPos = (JDFPosition) layoutPartition.appendElement(ElementName.POSITION);
				newPos.setRelativeBox(new JDFRectangle(0, 0, 1, 1));
			}
		}

		/**
		 * move appropriate stuff from StrippingParams to BinderySignature
		 * @param bs
		 * @param strippingParams
		 */
		private void moveBSFromStripping(JDFBinderySignature bs, JDFStrippingParams strippingParams)
		{
			if (strippingParams == null || bs == null)
				return;
			bs.moveAttribute(XJDFConstants.BinderySignatureID, bs, AttributeName.ASSEMBLYIDS, null, null);
			bs.moveAttribute(AttributeName.JOBID, bs);
			bs.moveAttribute(AttributeName.INNERMOSTSHINGLING, bs);
			bs.moveAttribute(AttributeName.OUTERMOSTSHINGLING, bs);
			//TODO where to move stripmarks? - stay in strippingparams or move to the appropriate binderysignature, stripcell or strippingparams
		}

		/**
		 * 
		 * move and merge stripcellparams and signaturecells
		 * @param bindSig
		 * @param childElementVector
		 */
		private void moveStripCells(JDFBinderySignature bindSig, VElement childElementVector)
		{
			if (childElementVector == null)
				return;
			for (KElement sigCell : childElementVector)
			{
				String cellindex = sigCell.getAttribute(AttributeName.CELLINDEX, null, null);
				JDFIntegerList il = JDFIntegerList.createIntegerList(cellindex);
				Vector<JDFSignatureCell> vbsCell = getSigCellForIndex(bindSig, il);
				sigCell.removeAttribute(AttributeName.CELLINDEX);
				if (vbsCell != null)
				{
					for (JDFSignatureCell bsCell : vbsCell)
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
		private Vector<JDFSignatureCell> getSigCellForIndex(JDFBinderySignature bindSig, JDFIntegerList il)
		{
			Vector<JDFSignatureCell> v = bindSig.getChildrenByClass(JDFSignatureCell.class, true, 0);
			Vector<JDFSignatureCell> vRet = new Vector<JDFSignatureCell>();
			if (v == null || v.size() == 0)
			{
				vRet.add(bindSig.appendSignatureCell());
			}
			else
			{
				for (JDFSignatureCell sc : v)
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
		private boolean matchesIndex(JDFSignatureCell sc, JDFIntegerList il)
		{
			if (il == null)
				return true;

			try
			{
				il = new JDFIntegerList(il);
			}
			catch (DataFormatException e)
			{
				return false;
			}
			//TODO simplex duplex evaluation
			il.scale(2);
			JDFIntegerList fp = sc.getFrontPages();
			// we assume that any match is valid for all
			return il.contains(fp);
		}

		/**
		 * @param map
		 */
		private void mergeSurfaces(JDFAttributeMap map)
		{
			// TODO merge surfaces that match map
			// 2 options 
			//- either add @Side to the respective content / mark objects + dynamic marks
			// add Surface elements
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
		public KElement walk(KElement xjdf, KElement dummy)
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
		public KElement walk(KElement xjdf, KElement dummy)
		{
			KElement intent = super.walk(xjdf, dummy);
			if (intent != null)
			{
				XJDFHelper h = new XJDFHelper(xjdf.getDeepParent(XJDFConstants.XJDF, 0));
				SetHelper artDelResHelper = h.getCreateResourceSet(ElementName.DELIVERYPARAMS, EnumUsage.Input);
				PartitionHelper ph = artDelResHelper.appendPartition(null, true);
				JDFDeliveryParams dp = (JDFDeliveryParams) ph.getResource();
				dp.setFromArtDelivery((JDFArtDeliveryIntent) intent.getElement(ElementName.ARTDELIVERYINTENT));
			}
			return intent;
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
		public KElement walk(KElement xjdf, KElement dummy)
		{
			KElement intent = super.walk(xjdf, dummy);
			if (intent != null)
			{
				XJDFHelper h = new XJDFHelper(xjdf.getDeepParent(XJDFConstants.XJDF, 0));
				SetHelper delResHelper = h.getCreateResourceSet(ElementName.DELIVERYPARAMS, EnumUsage.Input);
				PartitionHelper ph = delResHelper.appendPartition(null, true);
				JDFDeliveryParams dp = (JDFDeliveryParams) ph.getResource();
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
		public KElement walk(KElement xjdf, KElement dummy)
		{
			KElement delParams = super.walk(xjdf, dummy);
			if (delParams != null)
			{
				Vector<JDFDrop> vDrop = delParams.getChildrenByClass(JDFDrop.class, false, 0);
				int size = vDrop == null ? 0 : vDrop.size();
				KElement param = delParams.getParentNode_KElement();
				KElement set = param == null ? null : param.getParentNode_KElement();
				if (set != null)
				{
					PartitionHelper ph = new PartitionHelper(param);
					SetHelper sh = new SetHelper(set);
					JDFAttributeMap partMap = ph.getPartMap();
					partMap.put(XJDFConstants.DROP_ID, "DROP_0");
					ph.setPartMap(partMap);

					delParams.removeChildren(ElementName.DROP, null, null);
					for (int j = 0; j < size; j++)
					{
						int i = (j + 1) % size;
						partMap.put(XJDFConstants.DROP_ID, "DROP_" + i);
						KElement newDrop;
						PartitionHelper newParam;
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
		public KElement walk(KElement xjdf, KElement dummy)
		{
			KElement intent = xjdf.getElement(XJDFConstants.INTENT);
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
			return PartitionHelper.isResourceElement(toCheck);
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		*/
		@Override
		public KElement walk(KElement xjdf, KElement dummy)
		{
			KElement ret = super.walk(xjdf, dummy);
			xjdf.eraseEmptyNodes(true);
			if (xjdf.getFirstChild() == null && xjdf.getAttributeMap().size() == 0)
			{
				xjdf.deleteNode();
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
		public KElement walk(KElement xjdf, KElement dummy)
		{
			moveToSet(xjdf);
			KElement ret = super.walk(xjdf, dummy);
			xjdf.eraseEmptyNodes(true);
			KElement set = xjdf.getParentNode_KElement();
			String name = set == null ? null : StringUtil.getNonEmpty(set.getAttribute(AttributeName.NAME));
			if (name == null)
			{
				return null;
			}
			KElement realRes = xjdf.getElement(name);
			if (realRes != null)
				return ret;
			KElement aPool = xjdf.getElement(ElementName.AMOUNTPOOL);
			if (aPool != null)
				return ret;
			KElement comment = xjdf.getElement(ElementName.COMMENT);
			if (comment != null)
				return ret;
			JDFAttributeMap map = xjdf.getAttributeMap();
			map.remove(AttributeName.ID);
			map.remove(AttributeName.STATUS);
			if (map.size() == 0)
			{
				xjdf.deleteNode();
				return null;
			}
			return ret;
		}

		private void moveToSet(KElement xjdf)
		{
			KElement set = xjdf.getParentNode_KElement();
			if (set != null && xjdf.hasNonEmpty(AttributeName.UNIT))
			{
				set.moveAttribute(AttributeName.UNIT, xjdf);
			}

		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#reorderElements(org.cip4.jdflib.core.KElement)
		 */
		@Override
		protected void reorderElements(KElement xjdf)
		{
			super.reorderElements(xjdf);
			new PartitionHelper(xjdf).cleanUp();
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
		public KElement walk(KElement set, KElement dummy)
		{
			KElement ret = super.walk(set, dummy);
			set.eraseEmptyNodes(true);
			KElement res = set.getElement(StringUtil.leftStr(set.getLocalName(), -3));
			if (res != null)
				return ret;
			KElement comment = set.getElement(ElementName.COMMENT);
			if (comment != null)
				return ret;
			JDFAttributeMap map = set.getAttributeMap();
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

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#reorderElements(org.cip4.jdflib.core.KElement)
		 */
		@Override
		protected void reorderElements(KElement xjdf)
		{
			// nop
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
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		*/
		@Override
		public KElement walk(KElement xjdf, KElement dummy)
		{
			xjdf.removeAttribute(AttributeName.DESCRIPTIVENAME);
			xjdf.removeAttribute(AttributeName.STATUS);
			xjdf.removeAttribute(AttributeName.STATUSDETAILS);
			xjdf.removeAttribute(AttributeName.ID);
			KElement ret = super.walk(xjdf, dummy);
			return ret;
		}
	}

	/**
	 * @param newRoot 
	 *  
	 */
	PostXJDFWalker(JDFElement newRoot)
	{
		super(new BaseWalkerFactory());
		this.newRoot = newRoot;
		bDeliveryIntent = false;
		bIntentPartition = false;
		mergeLayout = true;
		removeSignatureName = true;
		retainAll = false;
		reorderElements = true;
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
	protected void setRetainAll(boolean retainAll)
	{
		this.retainAll = retainAll;
		if (retainAll)
		{
			removeSignatureName = !retainAll;
			mergeLayout = !retainAll;
			bDeliveryIntent = retainAll;
			reorderElements = false;
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
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return XJDFConstants.XJDF.equals(toCheck.getLocalName());
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		*/
		@Override
		public KElement walk(KElement xjdf, KElement dummy)
		{
			if (!retainAll)
			{
				reorderSets((JDFElement) xjdf);
				reorderProductList(xjdf);
			}
			super.walk(xjdf, dummy);
			return xjdf;
		}

		protected void reorderProductList(KElement xjdf)
		{
			KElement productList = xjdf.getElement(ProductHelper.PRODUCTLIST);
			KElement auditPool = xjdf.getElement(ElementName.AUDITPOOL);
			if (productList != null && auditPool != null)
			{
				xjdf.moveElement(productList, auditPool);
			}
		}

		/**
		 * @param xjdf 
		 * 
		 */
		private void reorderSets(JDFElement xjdf)
		{
			Vector<SetHelper> v = new XJDFHelper(xjdf).getSets();
			if (v == null)
				return; // nothing to do

			int n = v.size();
			int j = 0;
			while (n > 0)
			{
				for (int i = 0; i < v.size(); i++)
				{
					SetHelper setHelper = v.get(i);
					KElement e = setHelper == null ? null : setHelper.getSet();
					if (e != null)
					{
						JDFIntegerList lcpi = null;
						String cpi = e.getAttribute(AttributeName.COMBINEDPROCESSINDEX, null, null);
						try
						{
							lcpi = cpi == null ? null : new JDFIntegerList(cpi);
						}
						catch (final DataFormatException dfe)
						{
							//nop
						}

						// have a matching cpi entry - place set here and remove from further tests
						if (lcpi == null || lcpi.contains(j))
						{
							v.set(i, null);
							e.removeAttribute(AttributeName.COMBINEDPROCESSINDEX);
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
			//TODO treat outputs backwards...
			combineSameSets(xjdf);
		}

		/**
		 * 
		 * @param v
		 */
		private void combineSameSets(JDFElement xjdf)
		{
			Vector<SetHelper> v = new XJDFHelper(xjdf).getSets();

			while (v.size() > 0)
			{
				Vector<SetHelper> sameSets = new Vector<SetHelper>();
				SetHelper firstSet = v.remove(0);
				sameSets.add(firstSet);
				for (int j = 0; j < v.size(); j++)
				{
					SetHelper next = v.get(j);
					if (sameSetType(firstSet, next))
					{
						sameSets.add(next);
						v.remove(j);
						j--;
					}
				}

				KElement root = firstSet.getRoot();
				for (int j = 1; j < sameSets.size(); j++)
				{
					Vector<PartitionHelper> parts = sameSets.get(j).getPartitions();
					for (PartitionHelper ph : parts)
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
		private boolean sameSetType(SetHelper firstSet, SetHelper next)
		{
			boolean same = firstSet.getName().equals(next.getName());
			if (same)
				same = StringUtil.equals(firstSet.getProcessUsage(), next.getProcessUsage());
			if (same)
				same = ContainerUtil.equals(firstSet.getUsage(), next.getUsage());
			if (same)
				same = ContainerUtil.equals(firstSet.getXPathValue("@JobPartID"), next.getXPathValue("@JobPartID"));
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
		public KElement walk(KElement xjdf, KElement dummy)
		{
			KElement firstProduct = xjdf.getElement(ProductHelper.PRODUCT);
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

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.PostXJDFWalker.WalkElement#reorderElements(org.cip4.jdflib.core.KElement)
		 */
		@Override
		protected void reorderElements(KElement xjdf)
		{
			//nop
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
		public KElement walk(KElement xjdf, KElement dummy)
		{
			fixChildRefs(xjdf);
			return super.walk(xjdf, dummy);
		}

		/**
		 * 
		 * @param xjdf
		 */
		private void fixChildRefs(KElement xjdf)
		{
			ProductHelper ph = new ProductHelper(xjdf);
			IntentHelper bind = ph.getIntent(ElementName.BINDINGINTENT);
			if (bind != null)
			{
				bind.getCreateResource().moveAttribute(XJDFConstants.ChildRefs, ph.getRoot());
			}
			else
			{
				//TODO fix to subelements
				IntentHelper insert = ph.getIntent(ElementName.INSERTINGINTENT);
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
		public KElement walk(KElement xjdf, KElement dummy)
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
		public KElement walk(KElement xjdf, KElement dummy)
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
		public KElement walk(KElement xjdf, KElement dummy)
		{
			moveToSender(xjdf);
			return super.walk(xjdf, dummy);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(KElement e)
		{
			String localName = e.getLocalName();
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
		public KElement walk(KElement xjdf, KElement dummy)
		{
			moveToSender(xjdf);
			return super.walk(xjdf, dummy);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 */
		@Override
		public boolean matches(KElement e)
		{
			String localName = e.getLocalName();
			return localName.startsWith(ElementName.AUDIT) && !ElementName.AUDITPOOL.equals(localName);
		}

	}

	/**
	 * 
	 * @param xjdf
	 */
	void moveToSender(KElement xjdf)
	{
		if (xjdf != null)
		{
			JDFElement sender = (JDFElement) xjdf.getCreateElement(XJDFConstants.SENDER);
			sender.moveAttribute(AttributeName.AGENTNAME, xjdf);
			sender.moveAttribute(AttributeName.AGENTVERSION, xjdf);
			sender.moveAttribute(AttributeName.AUTHOR, xjdf);
			sender.moveAttribute(AttributeName.DEVICEID, xjdf);
			sender.moveAttribute(AttributeName.TIME, xjdf);
			sender.moveAttribute(AttributeName.ICSVERSIONS, xjdf);
			sender.moveAttribute(AttributeName.PERSONALID, xjdf);
			sender.moveAttribute(AttributeName.ID, xjdf);
			sender.moveAttribute(AttributeName.REFID, xjdf);
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PostXJDFWalker [mergeLayout=" + mergeLayout + ", bIntentPartition=" + bIntentPartition + ", bDeliveryIntent=" + bDeliveryIntent + ", retainAll=" + retainAll
				+ ", reorderElements=" + reorderElements + ", removeSignatureName=" + removeSignatureName + ", newRoot=" + newRoot + "]";
	}
}
