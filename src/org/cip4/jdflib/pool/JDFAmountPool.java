/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
package org.cip4.jdflib.pool;

import java.util.Set;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAmountPool;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.IAmountPoolContainer;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.VectorMap;

/**
 * This class represents a JDF-AuditPool
 */
public class JDFAmountPool extends JDFAutoAmountPool
{
	/**
	 * map of an amountpool that allows quick access to multiple amounts
	 * Class AmountMap
	 *
	 */
	public class AmountMap extends VectorMap<JDFAttributeMap, JDFPartAmount>
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 6548151023982113766L;

		/**
		 * Constructor AmountMap
		 * 
		 * @param vsPartIDKeys
		 */
		AmountMap(VString vsPartIDKeys)
		{
			final VElement vPartAmount = getChildElementVector(ElementName.PARTAMOUNT, null, null, true, 0, false);

			if (vPartAmount != null)
			{
				final int size = vPartAmount.size();
				for (int i = 0; i < size; i++)
				{
					final JDFPartAmount pa = (JDFPartAmount) vPartAmount.elementAt(i);
					final VJDFAttributeMap vamParts = pa.getPartMapVector();

					final int size2 = vamParts.size();
					for (int p = 0; p < size2; p++)
					{
						final JDFAttributeMap amPart = vamParts.elementAt(p);
						amPart.reduceMap(vsPartIDKeys);
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
		public double getAmountDouble(JDFAttributeMap amParts, String strAttributeName)
		{
			double dValue = -1.0;
			final Vector<JDFPartAmount> lpa = get(amParts);

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
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * static helper class that can be delegated from AmountPool containg classes, e.g. ResourceLink
	 */
	public static class AmountPoolHelper
	{

		/**
		 * returns the attribute occurrence in PartAmount, or the default in the ResourceLink
		 * @param poolParent the pool parent
		 * 
		 * @param attrib the attribute name
		 * @param nameSpaceURI the XML-namespace
		 * @param mPart defines which part of this ResourceLink the Amount belongs to. If empty get the ResourceLink
		 *            root attribute.
		 * @return value of attribute found, null if not available
		 * @param iSkip the index of the partAmount to check
		 * @since 071103
		 */
		public static String getAmountPoolAttribute(IAmountPoolContainer poolParent, final String attrib, final String nameSpaceURI, final JDFAttributeMap mPart, final int iSkip)
		{
			// want a map but already in a partamount - snafu
			if (poolParent instanceof JDFPartAmount)
			{
				if (mPart != null || iSkip > 1)
					throw new JDFException("JDFResourceLink.getAmountPoolAttribute: calling method on PartAmount object");
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
					ret = poolParent.getAttribute(attrib, nameSpaceURI, null);
			}

			return ret;
		}

		/**
		 * returns the attribute occurence in PartAmount, or the default in the ResourceLink
		 * 
		 * @param attrib the attribute name
		 * @param nameSpaceURI the XML-namespace
		 * @param vPart defines which part of this ResourceLink the Amount belongs to. If null get the ResourceLink root
		 *            attribute.
		 * @return value of attribute found, null if not available
		 * @since 071103
		 */
		public static String getAmountPoolAttribute(IAmountPoolContainer poolParent, final String attrib, final String nameSpaceURI, final VJDFAttributeMap vPart)
		{
			// want a map but already in a partamount - snafu
			if (poolParent instanceof JDFPartAmount)
			{
				if (vPart != null)
					throw new JDFException("JDFResourceLink.getAmountPoolAttribute: calling method on PartAmount object");
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
					return ret;
			}

			return poolParent.getAttribute(attrib, nameSpaceURI, null);
		}

		/**
		 * get the sum of all matching AmountPool/PartAmount/@attName as a double PartAmounts match if all attributes
		 * match those in PartAmount, i.e. mPart is a submap of the searche PartAmount elements
		 * 
		 * 
		 * @param attName the Attribute name , e.g Amount, ActualAmount
		 * @param mPart
		 * @return double - the element
		 * @throws JDFException if the element can not be cast to double
		 */
		public static double getAmountPoolDouble(IAmountPoolContainer poolParent, final String attName, final JDFAttributeMap mPart)
		{
			double d = 0;
			
			int n = 0;
			boolean bFound = false;
			JDFAmountPool ap = poolParent.getAmountPool();
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
					throw new JDFException("JDFResourceLink.getAmountPoolDouble: Attribute " + attName
							+ " has an invalid value");
				}
				
				d += dd;
				bFound = true;
				n++;
			}
		}

		/**
		 * get the exactly matching AmountPool/PartAmount/@AttName as a double
		 * 
		 * @param attName
		 * @param vPart
		 * @return double -
		 * @throws JDFException if the element can not be cast to double
		 */
		public static double getAmountPoolDouble(IAmountPoolContainer poolParent, final String attName, final VJDFAttributeMap vPart)
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
				throw new JDFException("JDFResourceLink.getAmountPoolDouble: Attribute " + attName
						+ " has an invalid value");
			}
			return d;
		}

		// //////////////////////////////////////////////////////////////////////

		/**
		 * gets the sum of all matching tags, with the assumpzion that no condition defaults to good
		 * 
		 * @param poolParent 
		 * @param attName 
		 * @param vPart 
		 * @return the sum
		 * 
		 */
		public static double getAmountPoolSumDouble(
				IAmountPoolContainer poolParent, final String attName, VJDFAttributeMap vPart)
		{
			VJDFAttributeMap vPartLocal = vPart;
			
			if (vPartLocal == null)
				vPartLocal = poolParent.getPartMapVector();
			
			if (poolParent.hasAttribute(attName))
				return poolParent.getRealAttribute(attName, null, 0);
			
			VJDFAttributeMap vm = vPartLocal == null ? null : new VJDFAttributeMap(vPartLocal);
			final JDFResource linkRoot = poolParent.getLinkRoot();
			if (linkRoot != null && vm != null)
			{
				final Set set = linkRoot.getPartIDKeys().getSet();
				set.add(AttributeName.CONDITION); // retain good / waste
				vm.reduceMap(set);
			}
			
			if (vm == null)
			{
				vm = new VJDFAttributeMap();
				vm.add((JDFAttributeMap) null);
			}
			
			double dd = 0;
			JDFAmountPool ap = poolParent.getAmountPool();
			if (ap == null)
				return poolParent.getRealAttribute(attName, null, 0.0);

			VElement vParts = ap.getChildElementVector(ElementName.PARTAMOUNT, null);

			if (vParts.isEmpty())
				return poolParent.getRealAttribute(attName, null, 0.0);
			
			boolean isWaste = vPartLocal != null && vPartLocal.subMap(new JDFAttributeMap(AttributeName.CONDITION, "Waste"));
			if (!isWaste && (vPartLocal == null || !vPartLocal.subMap(new JDFAttributeMap(AttributeName.CONDITION, "*"))))
			{
				vPartLocal = new VJDFAttributeMap(vPartLocal);
				vPartLocal.add(new JDFAttributeMap(AttributeName.CONDITION, "Good"));
			}
			
			for (int j = 0; j < vParts.size(); j++)
			{
				final JDFPartAmount pa = (JDFPartAmount) vParts.elementAt(j);
				VJDFAttributeMap partMapVector = pa.getPartMapVector();
				if (isWaste)
				{
					boolean hasCondition = partMapVector.subMap(new JDFAttributeMap(AttributeName.CONDITION, "*"));
					if (!hasCondition)
						continue;

				}
				
				if (!partMapVector.overlapsMap(vm))
					continue;
				
				String ret = null;
				ret = pa.getAttribute(attName, null, null);
				if (ret == null)
					ret = poolParent.getAttribute(attName, null, null);

				dd += StringUtil.parseDouble(ret, 0.0);
			}
			
			return dd;
		}

		/**
		 * get an amountmap for the Amountpool of poolParent
		 * @param poolParent the pool parent
		 * @param vPartIDKeys
		 * @return the AmountMap for the Amountpool, null if no amountpool exists
		 */
		public static AmountMap getAmountMap(IAmountPoolContainer poolParent, VString vPartIDKeys)
		{
			if (poolParent == null || poolParent.getAmountPool() == null)
				return null;
			return poolParent.getAmountPool().getAmountMap(vPartIDKeys);
		}

		/**
		 * get double attribute Amount, defaults to the value of Amount for the linked partition
		 * 
		 * @param mPart partition map to retrieve Amount for
		 * @return the amount, -1 if none is specified
		 * 
		 * @default getAmount(null)
		 */
		public static double getAmount(IAmountPoolContainer poolParent, final JDFAttributeMap mPart)
		{
			final double d = getAmountPoolDouble(poolParent, AttributeName.AMOUNT, mPart);
			if (d == -1)
			{
				JDFResource target = poolParent.getLinkRoot();
				if (target != null)
				{
					target = target.getPartition(mPart, null);
					if (target != null)
						return target.getAmount();
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
		 * @param mPart partition map to retrieve MinAmount for
		 * @return the MinAmount value
		 * @default getAmount(null)
		 */
		public static double getMinAmount(IAmountPoolContainer poolParent, final JDFAttributeMap mPart)
		{
			final double d = getAmountPoolDouble(poolParent, AttributeName.MINAMOUNT, mPart);
			if (d == -1)
				return getAmount(poolParent, mPart);
			return d;
		}

		/**
		 * get double attribute MaxAmount, defaults to getAmount if MinAmount is not set
		 * 
		 * @param mPart partition map to retrieve MinAmount for
		 * @return the MinAmount value
		 * @default getAmount(null)
		 */
		public static double getMaxAmount(IAmountPoolContainer poolParent, final JDFAttributeMap mPart)
		{
			final double d = getAmountPoolDouble(poolParent, AttributeName.MAXAMOUNT, mPart);
			if (d == -1)
				return getAmount(poolParent, mPart);
			return d;
		}

		/**
		 * sets the attribute occurence in the appropriate PartAmount when called for a resourceLink and creates the
		 * AmountPool and/or PartAmount(s) if they are not yet there
		 * 
		 * @param attrib the attribute name
		 * @param value value to set in string form.
		 * @param nameSpaceURI the XML-namespace
		 * @param vPart defines which part of this ResourceLink the Amount belongs to, if empty set the ResourceLink
		 *            root attribute.
		 * @throws JDFException when called directly on a PartAmount
		 * @since 060630
		 */
		public static void setAmountPoolAttribute(IAmountPoolContainer poolParent, final String attrib, final String value, final String nameSpaceURI, VJDFAttributeMap vPart)
		{
			// ideally the method would be hidden in PartAmount
			if ((vPart == null) || (vPart.isEmpty()) || vPart.size() == 1 && vPart.elementAt(0).size() == 0)
			{
				poolParent.setAttribute(attrib, value, nameSpaceURI);
				return;
			}
			poolParent.removeAttribute(attrib, nameSpaceURI); // either in the
			// pool or the
			// link, not both
			final JDFAmountPool ap = poolParent.getCreateAmountPool();
			JDFPartAmount pa0 = ap.getCreatePartAmount(vPart);
			pa0.setAttribute(attrib, value, nameSpaceURI);
		}

		/**
		 * sets the attribute occurence in the appropriate PartAmount when called for a resourceLink and creates the
		 * AmountPool and/or PartAmount if it is not yet there
		 * 
		 * @param attrib the attribute name
		 * @param value value to set in string form.
		 * @param nameSpaceURI the XML-namespace
		 * @param mPart defines which part of this ResourceLink the Amount belongs to, if empty set the ResourceLink
		 *            root attribute
		 * @throws JDFException when called directly on a PartAmount
		 * @since 071103
		 */
		public static void setAmountPoolAttribute(IAmountPoolContainer poolParent, final String attrib, final String value, final String nameSpaceURI, final JDFAttributeMap mPart)
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
			final VJDFAttributeMap v = new VJDFAttributeMap();
			v.add(mPart);
			setAmountPoolAttribute(poolParent, attrib, value, nameSpaceURI, v);
		}

	}

	// //////////////////////////////////////////////////////////////////////////
	// ////////////

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFAmountPool
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFAmountPool(CoreDocumentImpl myOwnerDocument, String qualifiedName)
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
	public JDFAmountPool(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
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
	public JDFAmountPool(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @param mPart
	 */
	public void removePartAmount(JDFAttributeMap mPart)
	{
		getPartAmount(mPart).deleteNode();
	}

	// **************************************** Methods
	// *********************************************
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
		final VElement vPartAmount = getChildElementVector(ElementName.PARTAMOUNT, null, null, true, 0, false);
		for (int i = vPartAmount.size() - 1; i >= 0; i--)
		{
			final JDFPartAmount partAmount = (JDFPartAmount) vPartAmount.elementAt(i);
			final VJDFAttributeMap vMapPart = partAmount.getPartMapVector();

			if (vMapPart.contains(mPart))
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
		final VElement vPartAmount = getChildElementVector(ElementName.PARTAMOUNT, null, null, true, 0, false);
		for (int i = vPartAmount.size() - 1; i >= 0; i--)
		{
			final JDFPartAmount partAmount = (JDFPartAmount) vPartAmount.elementAt(i);
			final VJDFAttributeMap vMapPart = partAmount.getPartMapVector();

			if (vMapPart != null && vMapPart.size() == 1 && vMapPart.elementAt(0).equals(mPart))
			{
				return partAmount; // exact match
			}
		}
		return null;
	}

	/**
	 * Get a PartAmount that fits to the filter defined by mPart
	 * 
	 * @param vPart filter for the part to set the status
	 * @return the PartAmount that fits
	 */
	public JDFPartAmount getPartAmount(VJDFAttributeMap vPart)
	{
		final VElement vPartAmount = getChildElementVector(ElementName.PARTAMOUNT, null, null, true, 0, false);
		for (int i = vPartAmount.size() - 1; i >= 0; i--)
		{
			final JDFPartAmount partAmount = (JDFPartAmount) vPartAmount.elementAt(i);
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
	public JDFPartAmount getPartAmount(JDFAttributeMap mPart, int iSkip)
	{
		final VElement vPartAmount = getChildElementVector(ElementName.PARTAMOUNT, null, null, true, 0, false);
		int n = 0;
		for (int i = vPartAmount.size() - 1; i >= 0; i--)
		{
			final JDFPartAmount partAmount = (JDFPartAmount) vPartAmount.elementAt(i);
			final VJDFAttributeMap vMapPart = partAmount.getPartMapVector();

			if (vMapPart.subMap(mPart) && ++n > iSkip)
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
	public JDFPartAmount getPartAmount(JDFAttributeMap mPart, boolean bCreate)
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
	 * @param mPart filter vector for the part to set the status
	 * @param bCreate
	 * @return the PartAmount that fits
	 * @deprecated use getMatchingPartAmountVector default: GetPartAmountVector(VJDFAttributeMap vmPart, false)
	 */
	@Deprecated
	public VElement getPartAmountVector(VJDFAttributeMap vmPart, boolean bCreate)
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
	public void reducePartAmounts(VJDFAttributeMap keepList)
	{
		if (keepList == null)
			return;

		final VElement v = getChildElementVector(ElementName.PARTAMOUNT, null, null, true, -1, true);
		for (int i = 0; i < v.size(); i++)
		{
			final JDFPartAmount pa = (JDFPartAmount) v.elementAt(i);
			final JDFAttributeMap map = pa.getPartMap();
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
				pa.deleteNode();
		}
	}

	/**
	 * get an AmountMap for this Amountpool
	 * @param vPartIDKeys
	 * @return the AmountMap for the Amountpool, null if no amountpool exists
	 */
	public AmountMap getAmountMap(VString vPartIDKeys)
	{
		return new AmountMap(vPartIDKeys);
	}

	/**
	 * Append JDFPartAmount element
	 * 
	 * @param mPart JDFAttributeMap to append
	 */
	public JDFPartAmount appendPartAmount(JDFAttributeMap mPart)
	{
		final JDFPartAmount p = super.appendPartAmount();
		p.setPartMap(mPart);
		return p;
	}

	/**
	 * Append JDFPartAmount elements
	 * 
	 * @param vPArt vector of partAmounts to append
	 */
	public JDFPartAmount appendPartAmount(VJDFAttributeMap vPart)
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
	public JDFPartAmount getCreatePartAmount(JDFAttributeMap mPart)
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
	public JDFPartAmount getCreatePartAmount(VJDFAttributeMap vPart)
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
	public VElement getMatchingPartAmountVector(JDFAttributeMap mPart)
	{
		VElement vPartAmount = getChildElementVector(ElementName.PARTAMOUNT, null, null, true, 0, false);
		final int size = vPartAmount.size();
		if (size == 0)
			return null;
		final VElement vPA = new VElement();
		for (int i = 0; i < size; i++)
		{
			JDFPartAmount ps = (JDFPartAmount) vPartAmount.elementAt(i);
			VJDFAttributeMap mm = ps.getPartMapVector();
			for (int j = 0; j < mm.size(); j++)
			{
				JDFAttributeMap m = mm.elementAt(j);
				if (m.subMap(mPart))
				{
					vPA.add(ps);
					break;
				}
			}
		}
		return vPA.size() == 0 ? null : vPA;
	}
	// /////////////////////////////////////////////////////////////////////
}
