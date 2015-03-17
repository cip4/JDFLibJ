/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFDeliveryIntent;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFDrop;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFSignatureCell;
import org.cip4.jdflib.resource.process.JDFStripCellParams;
import org.cip4.jdflib.util.StringUtil;

/**
 * some generic postprocessing that is better done on the XJDF such as merging stripping and Layout
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class PostXJDFWalker extends BaseElementWalker
{
	/**
	 * if true merge stripping and layout
	 */
	public boolean mergeLayout = true;
	protected JDFElement newRoot;
	/**
	 * if false, intents are never partitioned
	 */
	public boolean bIntentPartition = false;
	/**
	 * if false, all deliveryintents and artdeliveryintents are converted to the respective process resources
	 */
	public boolean bDeliveryIntent = false;

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
			if (xjdf.hasAttribute(AttributeName.XMLNS))
				xjdf.removeAttribute(AttributeName.XMLNS);
			if (xjdf.getNamespaceURI().equals(JDFElement.getSchemaURL()))
				xjdf.setNamespaceURI(XJDF20.getSchemaURL());
			return xjdf;
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
			part.removeAttribute(AttributeName.SIGNATURENAME);
			return super.walk(part, dummy);
		}

		@Override
		public boolean matches(KElement e)
		{
			return (e instanceof JDFPart);
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
		 * @see org.cip4.jdflib.extensions.PostXJDFWalker.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
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
		 * @see org.cip4.jdflib.extensions.PostXJDFWalker.WalkResourceElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
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
			SetHelper layoutseth = h.getCreateSet("Parameter", "Layout", EnumUsage.Input);

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
			bs.moveAttribute(AttributeName.ASSEMBLYID, bs, AttributeName.ASSEMBLYIDS, null, null);
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
		 * TODO Please insert comment!
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
		 * TODO Please insert comment!
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
			return toCheck.getLocalName().equals(SetHelper.PARAMETER_SET) && ElementName.STRIPPINGPARAMS.equals(toCheck.getAttribute(AttributeName.NAME));
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
				return super.walk(xjdf, dummy);
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
		 * @see org.cip4.jdflib.extensions.PostXJDFWalker.WalkIntentSet#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && ElementName.ARTDELIVERYINTENT.equals(toCheck.getAttribute("Name"));
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
				XJDFHelper h = new XJDFHelper(xjdf.getDeepParent(XJDFHelper.XJDF, 0));
				SetHelper artDelResHelper = h.getCreateSet("Parameter", ElementName.DELIVERYPARAMS, EnumUsage.Input);
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
		 * @see org.cip4.jdflib.extensions.PostXJDFWalker.WalkIntentSet#matches(org.cip4.jdflib.core.KElement)
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
				XJDFHelper h = new XJDFHelper(xjdf.getDeepParent(XJDFHelper.XJDF, 0));
				SetHelper delResHelper = h.getCreateSet("Parameter", ElementName.DELIVERYPARAMS, EnumUsage.Input);
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
		 * @see org.cip4.jdflib.extensions.PostXJDFWalker.WalkIntentSet#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFDeliveryParams;
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
					partMap.put(ElementName.DROP, "DROP_0");
					ph.setPartMap(partMap);

					delParams.removeChildren(ElementName.DROP, null, null);
					for (int j = 0; j < size; j++)
					{
						int i = (j + 1) % size;
						partMap.put(ElementName.DROP, "DROP_" + i);
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
			return toCheck.getLocalName().equals("IntentSet");
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
			KElement intent = xjdf.getElement("Intent");
			if (!bIntentPartition && intent != null)
			{
				intent.copyAttribute("ID", xjdf);
				intent.copyAttribute("Name", xjdf);
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
			return "Resource".equals(toCheck.getLocalName()) || "Parameter".equals(toCheck.getLocalName());
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
			xjdf.removeAttribute(AttributeName.STATUS);
			xjdf.removeAttribute(AttributeName.STATUSDETAILS);
			KElement ret = super.walk(xjdf, dummy);
			return ret;
		}
	}

	/**
	 * @param newRoot 
	 *  
	 */
	public PostXJDFWalker(JDFElement newRoot)
	{
		super(new BaseWalkerFactory());
		this.newRoot = newRoot;
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
			return toCheck.getLocalName().equals(XJDFHelper.XJDF);
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
			reorderSets((JDFElement) xjdf);
			reorderProductList(xjdf);
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
		 * @see org.cip4.jdflib.extensions.PostXJDFWalker.WalkIntentSet#matches(org.cip4.jdflib.core.KElement)
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
	}
}
