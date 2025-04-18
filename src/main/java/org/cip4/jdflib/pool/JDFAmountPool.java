/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.pool;

import java.util.List;
import java.util.Set;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAmountPool;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.IAmountPoolContainer;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.ListMap;
import org.cip4.jdflib.util.StringUtil;

/**
 * This class represents a JDF-AuditPool
 */
public class JDFAmountPool extends JDFAutoAmountPool
{
	/**
	 * map of an amountpool that allows quick access to multiple amounts Class AmountMap
	 *
	 */
	public class AmountMap extends ListMap<JDFAttributeMap, JDFPartAmount>
	{

		private final StringArray m_vsUsedPartIDKeys = new StringArray();

		/**
		 *
		 */
		private static final long serialVersionUID = 6548151023982113766L;

		/**
		 * Constructor AmountMap
		 *
		 * @param vsPartIDKeys
		 */
		AmountMap(final List<String> vsPartIDKeys)
		{
			final VElement vPartAmount = getChildElementVector(ElementName.PARTAMOUNT, null, null, true, 0, false);

			if (vPartAmount != null)
			{
				for (final KElement e : vPartAmount)
				{
					final JDFPartAmount pa = (JDFPartAmount) e;
					final VJDFAttributeMap vamParts = pa.getPartMapVector();

					final int size2 = vamParts == null ? 1 : vamParts.size();
					for (int p = 0; p < size2; p++)
					{
						final JDFAttributeMap amPart = vamParts == null ? new JDFAttributeMap() : vamParts.elementAt(p);
						amPart.reduceMap(vsPartIDKeys);
						m_vsUsedPartIDKeys.appendUnique(amPart.getKeyList());
						putOne(amPart, pa);
					}
				}
			}
		}

		/**
		 * Method getAmountDouble
		 *
		 * @param amParts
		 * @param strAttributeName
		 *
		 * @return the sum of all matching amounts
		 */
		public double getAmountDouble(final JDFAttributeMap amParts, final String strAttributeName)
		{
			double dValue = -1.0;
			final JDFAttributeMap amUsedParts = new JDFAttributeMap(amParts);
			amUsedParts.reduceMap(m_vsUsedPartIDKeys);
			final List<JDFPartAmount> lpa = get(amUsedParts);

			if (lpa != null)
			{
				double dTmp = 0.0;
				boolean isFound = false;
				for (final JDFPartAmount pa : lpa)
				{
					final String strValue = pa.getAttribute(strAttributeName, null, null);
					if (strValue != null)
					{
						final double dAdd = StringUtil.parseDouble(strValue, -1.);
						if (dAdd >= 0.0)
						{
							dTmp += dAdd;
							isFound = true;
						}
					}
				}

				if (isFound)
				{
					dValue = dTmp;
				}
			}

			return (dValue);
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen static helper class that can be delegated from AmountPool containg classes, e.g. ResourceLink
	 */
	public static class AmountPoolHelper
	{

		/**
		 * returns the attribute occurrence in PartAmount, or the default in the ResourceLink
		 *
		 * @param poolParent the pool parent
		 *
		 * @param attrib the attribute name
		 * @param nameSpaceURI the XML-namespace
		 * @param mPart defines which part of this ResourceLink the Amount belongs to. If empty get the ResourceLink root attribute.
		 * @return value of attribute found, null if not available
		 * @param iSkip the index of the partAmount to check
		 * @since 071103
		 */
		public static String getAmountPoolAttribute(final IAmountPoolContainer poolParent, final String attrib, final String nameSpaceURI, final JDFAttributeMap mPart, final int iSkip)
		{
			// want a map but already in a partamount - snafu
			if (poolParent instanceof JDFPartAmount)
			{
				if (mPart != null || iSkip > 1)
				{
					throw new JDFException("JDFResourceLink.getAmountPoolAttribute: calling method on PartAmount object");
				}
				return iSkip == 0 ? poolParent.getAttribute(attrib, nameSpaceURI, null) : null;
			}
			// default to attribute if no amountpool
			final JDFAmountPool amountPool = poolParent.getAmountPool();
			if (amountPool == null)
			{
				return iSkip > 0 ? null : poolParent.getAttribute(attrib, nameSpaceURI, null);
			}
			final JDFPartAmount pa = amountPool.getPartAmount(mPart, iSkip);
			String ret = null;
			if (pa != null) // we have a pa; if it has the attribute return its
			// value, else get the link attribute
			{
				ret = pa.getAttribute(attrib, nameSpaceURI, null);
				if (ret == null)
				{
					ret = poolParent.getAttribute(attrib, nameSpaceURI, null);
				}
			}

			return ret;
		}

		/**
		 * returns the attribute occurrence in PartAmount, or the default in the ResourceLink
		 *
		 * @param poolParent the parent pool to work on
		 * @param attrib the attribute name
		 * @param nameSpaceURI the XML-namespace
		 * @param vPart defines which part of this ResourceLink the Amount belongs to. If null get the ResourceLink root attribute.
		 * @return value of attribute found, null if not available
		 * @since 071103
		 */
		public static String getAmountPoolAttribute(final IAmountPoolContainer poolParent, final String attrib, final String nameSpaceURI, final VJDFAttributeMap vPart)
		{
			// want a map but already in a partamount - snafu
			if (poolParent instanceof JDFPartAmount)
			{
				if (vPart != null)
				{
					throw new JDFException("JDFResourceLink.getAmountPoolAttribute: calling method on PartAmount object");
				}
				return poolParent.getAttribute(attrib, nameSpaceURI, null);
			}
			// default to attribute if no amountpool
			final JDFAmountPool amountPool = poolParent.getAmountPool();
			if (amountPool == null || vPart == null)
			{
				return poolParent.getAttribute(attrib, nameSpaceURI, null);
			}
			final JDFPartAmount pa = amountPool.getPartAmount(vPart);
			if (pa != null) // we have a pa; if it has the attribute return its
			// value, else get the link attribute
			{
				final String ret = pa.getAttribute(attrib, nameSpaceURI, null);
				if (ret != null)
				{
					return ret;
				}
			}

			return poolParent.getAttribute(attrib, nameSpaceURI, null);
		}

		/**
		 * get the minimum value of all matching AmountPool/PartAmount/@attName as a double PartAmounts match if all attributes match those in PartAmount, i.e. mPart is a submap of the searched
		 * PartAmount elements
		 *
		 * @param poolParent
		 * @param attName the Attribute name , e.g Amount, ActualAmount
		 * @param mPart
		 * @return double - the element
		 * @throws JDFException if the element can not be cast to double
		 */
		public static double getAmountPoolMinDouble(final IAmountPoolContainer poolParent, final String attName, final JDFAttributeMap mPart)
		{
			double d = 0.;

			int n = 0;
			boolean bFound = false;
			final JDFAmountPool ap = poolParent.getAmountPool();
			while (true)
			{
				final String w = getAmountPoolAttribute(poolParent, attName, null, mPart, n);
				if (isWildCard(w))
				{
					if (ap == null || ap.getPartAmount(mPart, n) == null)
					{
						return bFound ? d : -1;
					}

					n++;
					continue;
				}

				final double dd = StringUtil.parseDouble(w, -1.234567);
				if (dd == -1.234567)
				{
					throw new JDFException("JDFResourceLink.getAmountPoolDouble: Attribute " + attName + " has an invalid value");
				}

				if (!bFound || dd < d)
				{
					d = dd;
					bFound = true;
				}
				n++;
			}
		}

		/**
		 * get the sum of all matching AmountPool/PartAmount/@attName as a double PartAmounts match if all attributes match those in PartAmount, i.e. mPart is a submap of the searched PartAmount
		 * elements
		 *
		 * @param poolParent
		 * @param attName the Attribute name , e.g Amount, ActualAmount
		 * @param mPart
		 * @return double - the element
		 * @throws JDFException if the element can not be cast to double
		 */
		public static double getAmountPoolDouble(final IAmountPoolContainer poolParent, final String attName, final JDFAttributeMap mPart)
		{
			double d = 0;

			int n = 0;
			boolean bFound = false;
			final JDFAmountPool ap = poolParent.getAmountPool();
			while (true)
			{
				final String w = getAmountPoolAttribute(poolParent, attName, null, mPart, n);
				if (isWildCard(w))
				{
					if (ap == null || ap.getPartAmount(mPart, n) == null)
					{
						return bFound ? d : -1;
					}

					n++;
					continue;
				}

				final double dd = StringUtil.parseDouble(w, -1.234567);
				if (dd == -1.234567)
				{
					throw new JDFException("JDFResourceLink.getAmountPoolDouble: Attribute " + attName + " has an invalid value");
				}

				d += dd;
				bFound = true;
				n++;
			}
		}

		/**
		 * get the exactly matching AmountPool/PartAmount/@AttName as a double
		 *
		 * @param poolParent the parent pool to work on
		 * @param attName
		 * @param vPart
		 * @return double -
		 * @throws JDFException if the element can not be cast to double
		 */
		public static double getAmountPoolDouble(final IAmountPoolContainer poolParent, final String attName, final VJDFAttributeMap vPart)
		{
			double d = 0;
			final String w = getAmountPoolAttribute(poolParent, attName, null, vPart);
			if (w == null)
			{
				return -1;
			}
			d = StringUtil.parseDouble(w, -1.234567);
			if (d == -1.234567)
			{
				throw new JDFException("JDFResourceLink.getAmountPoolDouble: Attribute " + attName + " has an invalid value");
			}
			return d;
		}

		// //////////////////////////////////////////////////////////////////////

		/**
		 * gets the sum of all matching tags, with the assumption that no condition defaults to good
		 *
		 * @param poolParent
		 * @param attName
		 * @param vPart
		 * @return the sum
		 *
		 */
		public static double getAmountPoolSumDouble(final IAmountPoolContainer poolParent, final String attName, VJDFAttributeMap vPart)
		{

			if (vPart == null)
			{
				vPart = poolParent.getPartMapVector();
			}

			VJDFAttributeMap vm = vPart == null ? null : new VJDFAttributeMap(vPart);
			final JDFResource linkRoot = poolParent.getLinkRoot();
			if (linkRoot != null && vm != null)
			{
				final Set<String> set = linkRoot.getPartIDKeys().getSet();
				set.add(AttributeName.CONDITION); // retain good / waste
				vm.reduceMap(set);
			}

			if (vm == null)
			{
				vm = new VJDFAttributeMap();
				vm.add((JDFAttributeMap) null);
			}

			double dd = 0;
			final JDFAmountPool ap = poolParent.getAmountPool();
			final VElement vParts = ap == null ? new VElement() : ap.getChildElementVector(ElementName.PARTAMOUNT, null);

			final boolean isWaste = vPart != null && vPart.subMap(new JDFAttributeMap(AttributeName.CONDITION, "Waste"));
			if (!isWaste && (vPart == null || !vPart.subMap(new JDFAttributeMap(AttributeName.CONDITION, "*"))))
			{
				vPart = new VJDFAttributeMap(vPart);
				vPart.add(new JDFAttributeMap(AttributeName.CONDITION, "Good"));
			}
			boolean gotOne = false;
			for (int j = 0; j < vParts.size(); j++)
			{
				final JDFPartAmount pa = (JDFPartAmount) vParts.elementAt(j);
				final VJDFAttributeMap partMapVector = pa.getPartMapVector();
				if (isWaste)
				{
					final boolean hasCondition = partMapVector != null && partMapVector.subMap(new JDFAttributeMap(AttributeName.CONDITION, "*"));
					if (!hasCondition)
					{
						continue;
					}

				}

				if (partMapVector != null && !partMapVector.overlapsMap(vm))
				{
					continue;
				}

				String ret = null;
				ret = pa.getAttribute(attName, null, null);
				if (ret == null)
				{
					ret = poolParent.getAttribute(attName, null, null);
				}

				dd += StringUtil.parseDouble(ret, 0.0);
				gotOne = true;
			}
			if (!gotOne && !isWaste)
			{
				dd = poolParent.getRealAttribute(attName, null, 0.0);
			}

			return dd;
		}

		/**
		 * get an amountmap for the Amountpool of poolParent
		 *
		 * @param poolParent the pool parent
		 * @param vPartIDKeys
		 * @return the AmountMap for the Amountpool, null if no amountpool exists
		 */
		public static AmountMap getAmountMap(final IAmountPoolContainer poolParent, final VString vPartIDKeys)
		{
			if (poolParent == null || poolParent.getAmountPool() == null)
			{
				return null;
			}
			return poolParent.getAmountPool().getAmountMap(vPartIDKeys);
		}

		/**
		 * get double attribute Amount, defaults to the value of Amount for the linked partition
		 *
		 * @param poolParent the parent pool to work on
		 * @param mPart partition map to retrieve Amount for
		 * @return the amount, -1 if none is specified
		 *
		 * @default getAmount(null)
		 */
		public static double getAmount(final IAmountPoolContainer poolParent, final JDFAttributeMap mPart)
		{
			final double d = getAmountPoolDouble(poolParent, AttributeName.AMOUNT, mPart);
			if (d == -1)
			{
				JDFResource target = poolParent.getLinkRoot();
				if (target != null)
				{
					target = target.getPartition(mPart, null);
					if (target != null)
					{
						return target.getAmount();
					}
				}
			}
			else
			{
				return d;
			}

			return -1.;
		}

		/**
		 * get double attribute MinAmount, defaults to getAmount if MinAmount is not set
		 *
		 * @param poolParent the parent pool to work on
		 * @param mPart partition map to retrieve MinAmount for
		 * @return the MinAmount value
		 * @default getAmount(null)
		 */
		public static double getMinAmount(final IAmountPoolContainer poolParent, final JDFAttributeMap mPart)
		{
			final double d = getAmountPoolDouble(poolParent, AttributeName.MINAMOUNT, mPart);
			if (d == -1)
			{
				return getAmount(poolParent, mPart);
			}
			return d;
		}

		/**
		 * get double attribute MaxAmount, defaults to getAmount if MinAmount is not set
		 *
		 * @param poolParent the parent pool to work on
		 * @param mPart partition map to retrieve MinAmount for
		 * @return the MinAmount value
		 * @default getAmount(null)
		 */
		public static double getMaxAmount(final IAmountPoolContainer poolParent, final JDFAttributeMap mPart)
		{
			final double d = getAmountPoolDouble(poolParent, AttributeName.MAXAMOUNT, mPart);
			if (d == -1)
			{
				return getAmount(poolParent, mPart);
			}
			return d;
		}

		/**
		 * sets the attribute occurence in the appropriate PartAmount when called for a resourceLink and creates the AmountPool and/or PartAmount(s) if they are not yet there
		 *
		 * @param poolParent the parent pool to work on
		 * @param attrib the attribute name
		 * @param value value to set in string form.
		 * @param nameSpaceURI the XML-namespace
		 * @param vPart defines which part of this ResourceLink the Amount belongs to, if empty set the ResourceLink root attribute.
		 * @throws JDFException when called directly on a PartAmount
		 * @since 060630
		 */
		public static void setAmountPoolAttribute(final IAmountPoolContainer poolParent, final String attrib, final String value, final String nameSpaceURI, final VJDFAttributeMap vPart)
		{
			// ideally the method would be hidden in PartAmount
			if ((vPart == null) || (vPart.isEmpty()) || vPart.size() == 1 && vPart.elementAt(0).size() == 0)
			{
				poolParent.setAttribute(attrib, value, nameSpaceURI);
				return;
			}
			poolParent.removeAttribute(attrib, nameSpaceURI); // either in the pool or the link, not both
			final JDFAmountPool ap = poolParent.getCreateAmountPool();
			ap.setPartAttribute(attrib, value, nameSpaceURI, vPart);
		}

		/**
		 * sets the attribute occurence in the appropriate PartAmount when called for a resourceLink and creates the AmountPool and/or PartAmount if it is not yet there
		 *
		 * @param poolParent the parent pool to work on
		 * @param attrib the attribute name
		 * @param value value to set in string form.
		 * @param nameSpaceURI the XML-namespace
		 * @param mPart defines which part of this ResourceLink the Amount belongs to, if empty set the ResourceLink root attribute
		 * @throws JDFException when called directly on a PartAmount
		 * @since 071103
		 */
		public static void setAmountPoolAttribute(final IAmountPoolContainer poolParent, final String attrib, final String value, final String nameSpaceURI, final JDFAttributeMap mPart)
		{
			// ideally the method would be hidden in PartAmount
			if ((mPart == null) || (mPart.isEmpty()))
			{
				poolParent.setAttribute(attrib, value, nameSpaceURI);
				return;
			}
			if (poolParent instanceof JDFPartAmount)
			{
				throw new JDFException("JDFResourceLink.setAmountPoolAttribute: calling method on PartAmount object");
			}
			final VJDFAttributeMap v = new VJDFAttributeMap(mPart);
			setAmountPoolAttribute(poolParent, attrib, value, nameSpaceURI, v);
		}

	}

	private static final long serialVersionUID = 1L;

	/**
	 * set an attribute in the appropriate PartAmount
	 *
	 * @param attrib
	 * @param value
	 * @param nameSpaceURI
	 * @param vPart
	 */
	public void setPartAttribute(final String attrib, final String value, final String nameSpaceURI, final VJDFAttributeMap vPart)
	{
		final JDFPartAmount pa0 = getCreatePartAmount(vPart);
		pa0.setAttribute(attrib, value, nameSpaceURI);
	}

	/**
	 *
	 *
	 * @param attrib
	 * @param nameSpaceURI
	 * @param part
	 * @return
	 */
	public String getPartAttribute(final String attrib, final String nameSpaceURI, final JDFAttributeMap part)
	{
		final JDFPartAmount pa0 = part == null ? getPartAmount(part) : getMatchingPartAmount(part);
		return pa0 == null ? null : pa0.getAttribute(attrib, nameSpaceURI, null);
	}

	/**
	 *
	 *
	 * @param attrib
	 * @param nameSpaceURI
	 * @param vPart
	 * @return
	 */
	public String getPartAttribute(final String attrib, final String nameSpaceURI, final VJDFAttributeMap vPart)
	{
		final JDFPartAmount pa0 = getPartAmount(vPart);
		return pa0 == null ? null : pa0.getAttribute(attrib, nameSpaceURI, null);
	}

	/**
	 *
	 *
	 * @param attrib
	 * @param value
	 * @param nameSpaceURI
	 * @param part
	 */
	public void setPartAttribute(final String attrib, final String value, final String nameSpaceURI, final JDFAttributeMap part)
	{
		final JDFPartAmount pa0 = getCreatePartAmount(part);
		pa0.setAttribute(attrib, value, nameSpaceURI);
	}

	/**
	 * Constructor for JDFAmountPool
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFAmountPool(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAmountPool
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFAmountPool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAmountPool
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFAmountPool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @param mPart
	 */
	public void removePartAmount(final JDFAttributeMap mPart)
	{
		final JDFPartAmount partAmount = getPartAmount(mPart);
		if (partAmount != null)
		{
			partAmount.deleteNode();
		}
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFAmountPool[ -->" + super.toString() + "]";
	}

	/**
	 * Get a PartAmount that fits to the filter defined by mPart
	 *
	 * @param mPart filter for the part to set the status
	 * @return the PartAmount that fits
	 */
	public JDFPartAmount getMatchingPartAmount(JDFAttributeMap mPart)
	{
		if (mPart != null && mPart.size() == 0)
			mPart = null;
		final List<JDFPartAmount> vPartAmount = getChildArrayByClass(JDFPartAmount.class, true, 0);
		for (final JDFPartAmount partAmount : vPartAmount)
		{
			final VJDFAttributeMap vMapPart = partAmount.getPartMapVector();

			if (vMapPart == null && mPart == null || mPart != null && vMapPart != null && vMapPart.contains(mPart))
			{
				return partAmount; // exact match
			}
		}
		return null;
	}

	/**
	 * Get a PartAmount that exactly equals the filter defined by mPart
	 *
	 * @param mPart filter for the part to set the status
	 * @return the PartAmount that fits
	 */
	public JDFPartAmount getPartAmount(JDFAttributeMap mPart)
	{
		if (mPart != null && mPart.size() == 0)
			mPart = null;

		final List<JDFPartAmount> vPartAmount = getChildArrayByClass(JDFPartAmount.class, true, 0);
		for (final JDFPartAmount partAmount : vPartAmount)
		{
			VJDFAttributeMap vMapPart = partAmount == null ? null : partAmount.getPartMapVector();
			if (vMapPart != null)
			{
				for (int i = vMapPart.size() - 1; i >= 0; i--)
				{
					if (vMapPart.get(i) == null || vMapPart.get(i).isEmpty())
					{
						vMapPart.remove(i);
					}
				}
			}
			if (vMapPart != null && vMapPart.isEmpty())
				vMapPart = null;

			if (vMapPart != null && vMapPart.size() == 1 && vMapPart.elementAt(0).equals(mPart) || vMapPart == null && mPart == null)
			{
				return partAmount; // exact match
			}
		}
		return null;
	}

	/**
	 * Get a PartAmount that fits to the filter defined by vPart
	 *
	 * @param vPart filter for the part to set the status
	 * @return the PartAmount that fits
	 */
	public JDFPartAmount getPartAmount(VJDFAttributeMap vPart)
	{
		if (vPart != null && vPart.size() == 1 && vPart.get(0).isEmpty())
		{
			vPart = null;
		}
		else if (vPart != null && vPart.isEmpty())
		{
			vPart = null;
		}
		final List<JDFPartAmount> vPartAmount = getChildArrayByClass(JDFPartAmount.class, true, 0);
		for (final JDFPartAmount partAmount : vPartAmount)
		{
			final VJDFAttributeMap vMapPart = partAmount.getPartMapVector();

			if (ContainerUtil.equals(vMapPart, vPart))
			{
				return partAmount; // exact match
			}
		}
		return null;
	}

	/**
	 * Get a PartAmount that fits to the filter defined by mPart
	 *
	 * @param mPart filter for the part to set the status
	 * @param iSkip the iSkip'th element to get
	 * @return the PartAmount that fits
	 */
	public JDFPartAmount getPartAmount(JDFAttributeMap mPart, final int iSkip)
	{
		int n = 0;
		if (mPart != null && mPart.size() == 0)
			mPart = null;
		final List<JDFPartAmount> vPartAmount = getChildArrayByClass(JDFPartAmount.class, true, 0);
		for (final JDFPartAmount partAmount : vPartAmount)
		{
			final VJDFAttributeMap vMapPart = partAmount.getPartMapVector();
			if (vMapPart != null && vMapPart.subMap(mPart) && ++n > iSkip)
			{
				return partAmount; // exact match
			}
		}
		return null;
	}

	/**
	 * Get a PartAmount that fits to the filter defined by mPart
	 *
	 * @param mPart filter for the part to set the status
	 * @param bCreate
	 * @return the PartAmount that fits
	 * @deprecated use either getPartAmount or getCreatePartAmount
	 */
	@Deprecated
	public JDFPartAmount getPartAmount(final JDFAttributeMap mPart, final boolean bCreate)
	{
		JDFPartAmount p = getPartAmount(mPart, 0);
		if (bCreate && p == null)
		{
			p = (JDFPartAmount) appendElement("PartAmount", JDFConstants.EMPTYSTRING);
			p.setPartMap(mPart);
		}
		return p;
	}

	/**
	 * Get a vector of PartAmount that fits to the filter defined by mPart
	 *
	 * @param vmPart filter vector for the part to set the status
	 * @param bCreate
	 * @return the PartAmount that fits
	 * @deprecated use getMatchingPartAmountVector default: GetPartAmountVector(VJDFAttributeMap vmPart, false)
	 */
	@Deprecated
	public VElement getPartAmountVector(final VJDFAttributeMap vmPart, final boolean bCreate)
	{
		final VElement vPartAmount = new VElement();
		for (int i = 0; i < vmPart.size(); i++)
		{
			final JDFPartAmount ps = getPartAmount(vmPart.elementAt(i), bCreate);
			if (ps != null)
			{
				vPartAmount.addElement(ps);
			}
		}
		return vPartAmount;
	}

	/**
	 * remove all partAmounts that are not specified in keepList
	 *
	 * @param keepList partAmounts to keep
	 */
	public void reducePartAmounts(final VJDFAttributeMap keepList)
	{
		if (keepList == null)
		{
			return;
		}

		final VElement v = getChildElementVector(ElementName.PARTAMOUNT, null, null, true, -1, true);
		for (int i = 0; i < v.size(); i++)
		{
			final JDFPartAmount pa = (JDFPartAmount) v.elementAt(i);
			JDFAttributeMap map = pa.getPartMap();
			if (map == null)
				map = new JDFAttributeMap();
			boolean ciao = true;
			for (int j = 0; j < keepList.size(); j++)
			{
				if (map.subMap(keepList.elementAt(j)))
				{
					ciao = false;
					break;
				}
			}
			if (ciao)
			{
				pa.deleteNode();
			}
		}
	}

	/**
	 * get an AmountMap for this Amountpool
	 *
	 * @param vPartIDKeys
	 * @return the AmountMap for the Amountpool, null if no amountpool exists
	 */
	public AmountMap getAmountMap(final VString vPartIDKeys)
	{
		return new AmountMap(vPartIDKeys);
	}

	/**
	 * Append JDFPartAmount element
	 *
	 * @param mPart JDFAttributeMap to append
	 * @return
	 */
	public JDFPartAmount appendPartAmount(final JDFAttributeMap mPart)
	{
		final JDFPartAmount p = super.appendPartAmount();
		p.setPartMap(mPart);
		return p;
	}

	/**
	 * Append JDFPartAmount elements
	 *
	 * @param vPart vector of partAmounts to append
	 * @return
	 */
	public JDFPartAmount appendPartAmount(final VJDFAttributeMap vPart)
	{
		final JDFPartAmount p = super.appendPartAmount();
		p.setPartMapVector(vPart);
		return p;
	}

	/**
	 * get JDFPartAmount specified by mPart, create a new one if it doesn't exist
	 *
	 * @param mPart JDFPartAmount to get/create
	 * @return
	 */
	public JDFPartAmount getCreatePartAmount(final JDFAttributeMap mPart)
	{
		JDFPartAmount p = getPartAmount(mPart);
		if (p == null)
		{
			p = (JDFPartAmount) appendElement(ElementName.PARTAMOUNT, null);
			p.setPartMap(mPart);
		}
		return p;
	}

	/**
	 * get JDFPartAmount specified by mPart, create a new one if it doesn't exist
	 *
	 * @param vPart JDFPartAmount to get/create
	 * @return
	 */
	public JDFPartAmount getCreatePartAmount(final VJDFAttributeMap vPart)
	{
		JDFPartAmount p = getPartAmount(vPart);
		if (p == null)
		{
			p = (JDFPartAmount) appendElement(ElementName.PARTAMOUNT, null);
			p.setPartMapVector(vPart);
		}
		return p;
	}

	/**
	 * Get a vector of PartAmounts which are supersets of the filter defined by mPart<br>
	 * i.e. mPart is a submap of all returned elements
	 *
	 * @param mPart filter vector for the part to set the status
	 *
	 * @return VElement - the vector of PartAmount elements that fit, null if nothing matches
	 */
	public VElement getMatchingPartAmountVector(final JDFAttributeMap mPart)
	{
		final List<JDFPartAmount> vPartAmount = getChildArrayByClass(JDFPartAmount.class, true, 0);
		final int size = vPartAmount.size();
		if (size == 0)
		{
			return null;
		}

		final VElement vPA = new VElement();
		for (int i = 0; i < size; i++)
		{
			final JDFPartAmount ps = vPartAmount.get(i);
			final VJDFAttributeMap mm = ps.getPartMapVector();
			if (mm != null)
			{
				final int mmSize = mm.size();
				for (int j = 0; j < mmSize; j++)
				{
					final JDFAttributeMap m = mm.elementAt(j);
					if (m.subMap(mPart))
					{
						vPA.add(ps);
						break;
					}
				}
			}
		}

		return vPA.size() == 0 ? null : vPA;
	}
	// /////////////////////////////////////////////////////////////////////

	/**
	 * @see org.cip4.jdflib.core.JDFElement#getPartMapVector()
	 */
	@Override
	public VJDFAttributeMap getPartMapVector()
	{
		final VElement vPartAmount = getChildElementVector(ElementName.PARTAMOUNT, null, null, true, 0, false);
		final VJDFAttributeMap ret = new VJDFAttributeMap();
		if (vPartAmount != null)
		{
			for (final KElement e : vPartAmount)
			{
				final JDFPartAmount pa = (JDFPartAmount) e;
				final VJDFAttributeMap vamParts = pa.getPartMapVector();
				if (vamParts != null && !vamParts.isEmpty())
					ret.addAll(vamParts);
			}
		}
		ret.unify();
		return ret;
	}
}
