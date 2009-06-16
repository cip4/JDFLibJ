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
/**
 ==========================================================================
 class JDFResourceInfo extends JDFResource
 ==========================================================================
 @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
 ALL RIGHTS RESERVED
 @Author sabjon@topmail.de   using a code generator
 Warning! very preliminary test version. Interface subject to change without prior notice!
 Revision history:    ...
 **/

package org.cip4.jdflib.jmf;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoResourceInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.IAmountPoolContainer;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAmountPool.AmountPoolHelper;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen ResourceInfo element class
 */
public class JDFResourceInfo extends JDFAutoResourceInfo implements IAmountPoolContainer
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFResourceInfo
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFResourceInfo(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFResourceInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFResourceInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFResourceInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFResourceInfo(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
	static
	{
		int i = 0;
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PREVIEW, 0x33333333);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * toString()
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFResourceInfo[  --> " + super.toString() + " ]";
	}

	/**
	 * get the resource defined by <code>resName</code>
	 * 
	 * @param resName name of the resource to get/create
	 * @return JDFCostCenter The element
	 */
	public JDFResource getCreateResource(final String resName)
	{
		final KElement e = getCreateElement(resName, JDFConstants.EMPTYSTRING, 0);
		if (e instanceof JDFResource)
		{
			return (JDFResource) e;
		}
		throw new JDFException("JDFResouceInfo.getCreateResource tried to create a JDFElement instead of a JDFResource");
	}

	/**
	 * get resource defined by <code>resName</code>
	 * 
	 * @param resName name of the resource to get
	 * @return JDFResource: the element
	 */
	public JDFResource getResource(final String resName)
	{
		for (int i = 0; true; i++)
		{
			final KElement e = getElement(resName, null, i);
			if (e == null)
			{
				return null;
			}
			if (e instanceof JDFResource)
			{
				return (JDFResource) e;
			}
		}
	}

	/**
	 * get all resources
	 * 
	 * @return VElement: the vector of resources
	 */
	public VElement getResourceVector()
	{
		final VElement v0 = getChildElementVector(null, null);
		if (v0 == null)
		{
			return null;
		}
		for (int i = v0.size(); i >= 0; i--)
		{
			final KElement e = v0.get(i);
			if (!(e instanceof JDFResource))
			{
				v0.remove(i);
			}
		}
		return v0.size() == 0 ? null : v0;
	}

	/**
	 * append resource
	 * 
	 * @param resName name of the resource to append
	 */
	public JDFResource appendResource(final String resName)
	{
		final KElement e = appendElement(resName, null);
		if (e instanceof JDFResource)
		{
			return (JDFResource) e;
		}
		throw new JDFException("JDFResouceInfo.appendResource tried to append a JDFElement instead of a JDFResource");
	}

	/**
	 * return a vector of unknown element nodenames
	 * <p>
	 * default: getUnknownElements(true, 999999)
	 * 
	 * @param bIgnorePrivate used by JDFElement during the validation
	 * @param nMax maximum number of elements to get
	 * 
	 * @return Vector - vector of unknown element nodenames
	 */
	@Override
	public Vector getUnknownElements(final boolean bIgnorePrivate, final int nMax)
	{
		return getUnknownPoolElements(JDFElement.EnumPoolType.ResourcePool, nMax);
	}

	/**
	 * Method getInvalidElements
	 * <p>
	 * default: GetInvalidElements(level, true, 999999)
	 * 
	 * @param level validation level
	 * @param bIgnorePrivate
	 * @param nMax maximum number of elements to get
	 * 
	 * @return VString - vector of names of invalid elements
	 */
	@Override
	public VString getInvalidElements(final EnumValidationLevel level, final boolean bIgnorePrivate, final int nMax)
	{
		final VString s = getInvalidElements_JDFElement(level, bIgnorePrivate, nMax);
		if (s.size() > nMax || hasAttribute(AttributeName.RESOURCENAME))
		{
			// are
			// allowed
			// with
			// resourcename
			// set
			return s;
		}

		final VElement v = getChildElementVector(null, null, null, true, 0, false);
		int size = v.size();
		if (size > 1)
		{
			// remove anything but resources
			for (int i = size - 1; i >= 0; i--)
			{
				if (!(v.elementAt(i) instanceof JDFResource))
				{
					v.remove(i);
				}
			}
			size = v.size(); // must refresh size due to removes
			// more than one resource --> evil!
			if (size > 1)
			{
				for (int j = 0; j < size; j++)
				{
					s.appendUnique(v.item(j).getLocalName());
				}
			}
		}
		return s;
	}

	/**
	 * get part map vector
	 * 
	 * @return VJDFAttributeMap: vector of attribute maps, one for each part
	 */
	@Override
	public VJDFAttributeMap getPartMapVector()
	{
		return super.getPartMapVector();
	}

	/**
	 * set all parts to those defined by vParts
	 * 
	 * @param vParts vector of attribute maps for the parts
	 */
	@Override
	public void setPartMapVector(final VJDFAttributeMap vParts)
	{
		super.setPartMapVector(vParts);
	}

	/**
	 * set all parts to those defined by mPart
	 * 
	 * @param mPart attribute map for the part to set
	 */
	@Override
	public void setPartMap(final JDFAttributeMap mPart)
	{
		super.setPartMap(mPart);
	}

	/**
	 * remove the part defined in mPart
	 * 
	 * @param mPart attribute map for the part to remove
	 */
	@Override
	public void removePartMap(final JDFAttributeMap mPart)
	{
		super.removePartMap(mPart);
	}

	/**
	 * check whether the part defined in mPart is included
	 * 
	 * @param mPart attribute map to look for
	 * @return boolean - returns true if the part exists
	 */
	@Override
	public boolean hasPartMap(final JDFAttributeMap mPart)
	{
		return super.hasPartMap(mPart);
	}

	/**
	 * sets all relevant parameters of this to the values specified in resourceLink or its linked resource or JDF node
	 * 
	 * @param resourceLink the resourceLink to extract the information from
	 * @param rqp parameters
	 */
	public void setLink(final JDFResourceLink resourceLink, final JDFResourceQuParams rqp)
	{
		if (resourceLink == null)
		{
			return;
		}
		final JDFAmountPool ap = resourceLink.getAmountPool();
		if (ap != null)
		{
			copyElement(ap, null);
		}
		else
		{
			if (resourceLink.hasAttribute(AttributeName.ACTUALAMOUNT))
			{
				setActualAmount(resourceLink.getActualAmount(null));
			}
			if (resourceLink.hasAttribute(AttributeName.AMOUNT))
			{
				setAmount(resourceLink.getAmount(null));
			}
		}
		setProcessUsage(resourceLink.getEnumProcessUsage());

		final JDFResource r = resourceLink.getTarget();
		if (r == null && rqp != null)
		{
			rqp.setExact(false);
		}

		final boolean bExact = rqp == null ? false : rqp.getExact();
		if (!bExact || r == null) // if we do not have a resource let's limp
		// along and provide as much as we have
		{
			setResourceName(resourceLink.getLinkedResourceName());
			setAttribute(AttributeName.RESOURCEID, resourceLink.getrRef());
			final EnumUsage usage = resourceLink.getUsage();
			if (usage != null)
			{
				setAttribute(AttributeName.USAGE, usage.getName());
			}
			if (r != null && r.hasAttribute(AttributeName.PRODUCTID))
			{
				setProductID(r.getProductID());
			}
		}
		else
		{
			// create a copy of the resource in the original jdf
			final JDFResource rr = (JDFResource) r.getParentNode_KElement().copyElement(r, null);
			rr.inlineRefElements(null, null, true);
			// move resource copy from the original node into this document
			moveElement(rr, null);
		}
	}

	/**
	 * set ProcessUsage to the enum processusage
	 * 
	 * @param processUsage
	 */
	public void setProcessUsage(final EnumProcessUsage processUsage)
	{
		setAttribute(AttributeName.PROCESSUSAGE, processUsage == null ? null : processUsage.getName(), null);
	}

	/**
	 * if a Resource is available, return it's ProductID<br/>
	 * if no productID is available, return null
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoResourceInfo#getProductID()
	 */
	@Override
	public String getProductID()
	{
		String _name = super.getProductID();
		if (isWildCard(_name))
		{
			final JDFResource r = getResource(null);
			if (r == null)
			{
				return null;
			}
			_name = r.getProductID();
		}
		return StringUtil.getNonEmpty(_name);
	}

	/**
	 * if a Resource is available, return it's ID
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoResourceInfo#getResourceID()
	 */
	@Override
	public String getResourceID()
	{
		String _name = super.getResourceID();
		if (isWildCard(_name))
		{
			final JDFResource r = getResource(null);
			if (r == null)
			{
				return null;
			}
			_name = r.getID();
		}
		return _name;
	}

	/**
	 * if a Resource is available, return it's name, null if none is availabel
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoResourceInfo#getResourceName()
	 */
	@Override
	public String getResourceName()
	{

		String _name = super.getResourceName();
		if (isWildCard(_name))
		{
			final JDFResource r = getResource(null);
			if (r == null)
			{
				return null;
			}
			_name = r.getLocalName();
		}
		return _name;
	}

	/**
	 * 
	 * if a Resource is available, return it's status
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoResourceInfo#getResStatus()
	 */
	@Override
	public EnumResStatus getResStatus()
	{
		EnumResStatus s = super.getResStatus();
		if (s == null)
		{
			final JDFResource r = getResource(null);
			if (r == null)
			{
				return null;
			}
			s = r.getResStatus(false);
		}
		return s;
	}

	/**
	 * getLinkRoot - gets the root resource of the target based on ResourceName, if available
	 * 
	 * @return JDFResource
	 */
	public JDFResource getLinkRoot()
	{
		return getResource(getResourceName());
	}

	/**
	 * Set attribute ActualAmount in the AmountPool or in the link, depending on the value of mPart
	 * 
	 * @param value the value to set ActualAmount to
	 * @param mPart the part map of AmountPool/PartAmount
	 */
	public void setActualAmount(final double value, final JDFAttributeMap mPart)
	{
		setAmountPoolAttribute("ActualAmount", StringUtil.formatDouble(value), null, mPart);
	}

	/**
	 * setAmount in PartAmount or in this if partAmount=null
	 * 
	 * @param value amount to set
	 * @param mPart partition map to set amount for
	 * 
	 * @default setAmount(double value, null)
	 */
	public void setAmount(final double value, final JDFAttributeMap mPart)
	{
		setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(value), null, mPart);
	}

	/**
	 * sets the attribute occurence in the appropriate PartAmount when called for a resourceLink and creates the AmountPool and/or PartAmount if it is not yet
	 * there
	 * 
	 * @param attrib the attribute name
	 * @param value value to set in string form.
	 * @param nameSpaceURI the XML-namespace
	 * @param mPart defines which part of this ResourceLink the Amount belongs to, if empty set the ResourceLink root attribute
	 * @throws JDFException when called directly on a PartAmount
	 * @since 071103
	 */
	public void setAmountPoolAttribute(final String attrib, final String value, final String nameSpaceURI, final JDFAttributeMap mPart)
	{
		AmountPoolHelper.setAmountPoolAttribute(this, attrib, value, nameSpaceURI, mPart);
	}

	/**
	 * sets the attribute occurence in the appropriate PartAmount when called for a resourceLink and creates the AmountPool and/or PartAmount(s) if they are not
	 * yet there
	 * 
	 * @param attrib the attribute name
	 * @param value value to set in string form.
	 * @param nameSpaceURI the XML-namespace
	 * @param vPart defines which part of this ResourceLink the Amount belongs to, if empty set the ResourceLink root attribute.
	 * @throws JDFException when called directly on a PartAmount
	 * @since 060630
	 */
	public void setAmountPoolAttribute(final String attrib, final String value, final String nameSpaceURI, final VJDFAttributeMap vPart)
	{
		AmountPoolHelper.setAmountPoolAttribute(this, attrib, value, nameSpaceURI, vPart);
	}

	/**
	 * returns the attribute occurence in PartAmount, or the default in the ResourceLink
	 * 
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @param mPart defines which part of this ResourceLink the Amount belongs to. If empty get the ResourceLink root attribute.
	 * @param iSkip
	 * @return value of attribute found, null if not available
	 * @since 071103
	 */
	public String getAmountPoolAttribute(final String attrib, final String nameSpaceURI, final JDFAttributeMap mPart, final int iSkip)
	{
		return AmountPoolHelper.getAmountPoolAttribute(this, attrib, nameSpaceURI, mPart, iSkip);
	}

	/**
	 * returns the attribute occurence in PartAmount, or the default in the ResourceLink
	 * 
	 * @param attrib the attribute name
	 * @param nameSpaceURI the XML-namespace
	 * @param vPart defines which part of this ResourceLink the Amount belongs to. If null get the ResourceLink root attribute.
	 * @return value of attribute found, null if not available
	 * @since 071103
	 */
	public String getAmountPoolAttribute(final String attrib, final String nameSpaceURI, final VJDFAttributeMap vPart)
	{
		return AmountPoolHelper.getAmountPoolAttribute(this, attrib, nameSpaceURI, vPart);
	}

	/**
	 * get the sum of all matching AmountPool/PartAmount/@attName as a double PartAmounts match if all attributes match those in PartAmount, i.e. mPart is a
	 * submap of the searche PartAmount elements
	 * 
	 * 
	 * @param attName the Attribute name , e.g Amount, ActualAmount
	 * @param mPart
	 * @return double - the element
	 * @throws JDFException if the element can not be cast to double
	 */
	public double getAmountPoolDouble(final String attName, final JDFAttributeMap mPart)
	{
		return AmountPoolHelper.getAmountPoolDouble(this, attName, mPart);
	}

	/**
	 * get the exactly matching AmountPool/PartAmount/@AttName as a double
	 * 
	 * @param attName
	 * @param vPart
	 * @return double -
	 * @throws JDFException if the element can not be cast to double
	 */
	public double getAmountPoolDouble(final String attName, final VJDFAttributeMap vPart)
	{
		return AmountPoolHelper.getAmountPoolDouble(this, attName, vPart);
	}

	// //////////////////////////////////////////////////////////////////////////
	// ////

	/**
	 * @param attName
	 * @param vPart
	 * @return
	 */
	public double getAmountPoolSumDouble(final String attName, final VJDFAttributeMap vPart)
	{
		return AmountPoolHelper.getAmountPoolSumDouble(this, attName, vPart);
	}

}
