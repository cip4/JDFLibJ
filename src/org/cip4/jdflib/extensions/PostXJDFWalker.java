/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFDeliveryParams;
import org.cip4.jdflib.resource.process.JDFStripCellParams;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class PostXJDFWalker extends BaseElementWalker
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
	public boolean bDeliveryIntent = true;

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
			return xjdf;
		}

	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkStrippingParams extends WalkElement
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
			return toCheck.getLocalName().equals(ElementName.STRIPPINGPARAMS);
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
			if (!mergeLayout)
			{
				return xjdf; // nuff done
			}
			//TODO multiple lower level stripparams partitions
			XJDFHelper h = new XJDFHelper(newRoot);
			SetHelper layoutseth = h.getCreateSet("Parameter", "Layout", EnumUsage.Input);

			VJDFAttributeMap vmap = new PartitionHelper(xjdf.getParentNode_KElement()).getPartMapVector();
			JDFAttributeMap map = vmap.size() == 0 ? null : vmap.get(0);
			map = mergeStrippingParamsLayout(xjdf, layoutseth, map);

			mergeSurfaces(map);
			return null; // stop after merging

		}

		/**
		 * @param xjdf
		 * @param layoutseth 
		 * @param map 
		 * @return 
		 */
		private JDFAttributeMap mergeStrippingParamsLayout(KElement xjdf, SetHelper layoutseth, JDFAttributeMap map)
		{
			String bsName = map.remove(AttributeName.BINDERYSIGNATURENAME);
			String cellIndex = map.remove(AttributeName.CELLINDEX);
			PartitionHelper layoutPartitionH = layoutseth.getCreatePartition(map, true);
			KElement layoutPartition = layoutPartitionH.getResource();
			JDFBinderySignature bsOld = (JDFBinderySignature) layoutPartition.getChildWithAttribute(ElementName.BINDERYSIGNATURE, AttributeName.BINDERYSIGNATURENAME, null, bsName, 0, true);
			VElement childElementVector = xjdf.getChildElementVector(ElementName.STRIPCELLPARAMS, null);
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
				bsOld.moveElements(childElementVector, null);
			}
			else
			{
				JDFBinderySignature bs = (JDFBinderySignature) xjdf.getElement(ElementName.BINDERYSIGNATURE);
				if (bs != null)
				{
					bs.setBinderySignatureName(bsName);
					bs.moveElements(xjdf.getChildElementVector(ElementName.POSITION, null), null);
					bs.moveElements(childElementVector, null);
				}
				layoutPartition.copyInto(xjdf, false);
			}
			return map;
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
	protected class WalkStrippingSet extends WalkElement
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
			return toCheck.getLocalName().equals("ParameterSet") && ElementName.STRIPPINGPARAMS.equals(toCheck.getAttribute(AttributeName.NAME));
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
				XJDFHelper h = new XJDFHelper(xjdf.getDeepParent(XJDF20.rootName, 0));
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
			return intent;
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
			return toCheck.getLocalName().equals(XJDF20.rootName);
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
			return xjdf;
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
}
