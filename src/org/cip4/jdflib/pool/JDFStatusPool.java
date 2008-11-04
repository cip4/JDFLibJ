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
/**
 *
 * Copyright (c) 2001-2003 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * String myLocalName)
 *
 * Last changes
 *
 */
package org.cip4.jdflib.pool;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoStatusPool;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFPartStatus;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;

/**
 * This class represents a JDF-Status Pool. THe status pool describes the status of a JDF node that processes
 * partitioned resources. StatusPool elements are only valid if the nodes Status="Pool", otherwise the nodes status is
 * valid for all parts, regardless of the contents of StatusPool.
 */
public class JDFStatusPool extends JDFAutoStatusPool
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFStatusPool
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFStatusPool(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFStatusPool
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFStatusPool(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFStatusPool
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFStatusPool(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
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
		return "JDFStatusPool[ -->" + super.toString() + "]";
	}

	/**
	 * getElementStatus - get the status of a part defined in StatusPool
	 * 
	 * @param mPart filter for the part to get the status
	 * 
	 * @return EnumNodeStatus - the status for the filter defined in mPart
	 */
	public JDFElement.EnumNodeStatus getStatus(JDFAttributeMap mPart)
	{
		final JDFNode n = getParentJDF();

		if (!(n.getStatus().equals(JDFElement.EnumNodeStatus.Pool)))
		{
			return n.getStatus();
		}

		final JDFPartStatus ps = getPartStatus(mPart);

		// default to status of this
		if (ps == null)
		{
			return getStatus();
		}

		return ps.getStatus();
	}

	/**
	 * getPartStatus - get a PartStatus that fits to the filter defined by mPart
	 * 
	 * @param mPart the filter for the part to set the status
	 * 
	 * @return JDFPartStatus - the PartStatus that fits
	 */
	public JDFPartStatus getPartStatus(JDFAttributeMap mPart)
	{
		final VElement vPartStatus = getChildElementVector(ElementName.PARTSTATUS, null, null, true, 0, false);
		int nSep = 0;
		JDFPartStatus retPartStatus = null;

		for (int i = vPartStatus.size() - 1; i >= 0; i--)
		{
			JDFPartStatus ps = (JDFPartStatus) vPartStatus.elementAt(i);
			final JDFAttributeMap mapPart = ps.getPartMap();

			if (mPart != null && mPart.subMap(mapPart))
			{
				if (mapPart.size() > nSep)
				{
					nSep = mapPart.size();
					retPartStatus = ps; // mPart is a subset of of mapPart
				}
			}
		}

		return retPartStatus;
	}

	/**
	 * getCreatePartStatus - get a PartStatus that fits to the filter defined by mPart<br>
	 * create it if it does not exist
	 * 
	 * @param mPart the filter for the part to set the status
	 * 
	 * 
	 * @return JDFPartStatus - the PartStatus that fits
	 */
	public JDFPartStatus getCreatePartStatus(JDFAttributeMap mPart)
	{
		JDFPartStatus p = getPartStatus(mPart);

		if (p == null)
		{
			p = appendPartStatus();
			p.setPartMap(mPart);
		}

		return p;
	}

	/**
	 * getPartStatusVector - get a vector of PartStatus that fits to the filter defined by mPart
	 * 
	 * @param mPart the filter vector for the part to set the status.If null, return all.
	 * 
	 * @return VElement - the vector of PartStatus that fit
	 */
	public VElement getPartStatusVector(Vector vmPart)
	{
		if (vmPart == null)
			return getChildElementVector(ElementName.PARTSTATUS, null, null, true, -1, false);

		final VElement vPartStatus = new VElement();

		for (int i = 0; i < vmPart.size(); i++)
		{
			final JDFPartStatus ps = getPartStatus((JDFAttributeMap) vmPart.elementAt(i));

			if (ps != null)
			{
				vPartStatus.add(ps);
			}
		}
		return vPartStatus;
	}

	/**
	 * get matching part status vector
	 * 
	 * @param JDFAttributeMap mPart
	 * @return VElement - vector of JDFPartStatus
	 */
	public VElement getMatchingPartStatusVector(JDFAttributeMap mPart)
	{
		final VElement vPartStatus = getChildElementVector(ElementName.PARTSTATUS, null, null, true, 0, false);
		final VElement vPS = new VElement();

		for (int i = 0; i < vPartStatus.size(); i++)
		{
			final JDFPartStatus ps = (JDFPartStatus) vPartStatus.elementAt(i);
			final JDFAttributeMap mapPart = ps.getPartMap();

			if (mapPart.subMap(mPart))
			{
				vPS.add(ps); // mPart is a subset of of mapPart
			}
		}
		return vPS;
	}

	/**
	 * getCreatePartStatusVector - get a vector of PartStatus that fits to the filter defined by mPart
	 * 
	 * @param mPart the filter vector for the part to set the status
	 * 
	 * @return VElement - vector of JDFPartStatus
	 */
	public VElement getCreatePartStatusVector(Vector vmPart)
	{
		final VElement vPartStatus = new VElement();

		for (int i = 0; i < vmPart.size(); i++)
		{
			final JDFPartStatus ps = getCreatePartStatus((JDFAttributeMap) vmPart.elementAt(i));

			if (ps != null)
			{
				vPartStatus.add(ps);
			}
		}

		return vPartStatus;
	}

	/**
	 * get pool children with attributes definded by <code>mAttrib</code>
	 * 
	 * @param mAttrib attribute map
	 * @return VElement
	 */
	public VElement getPoolChildren(JDFAttributeMap mAttrib)
	{
		return getPoolChildrenGeneric(ElementName.PARTSTATUS, mAttrib, JDFConstants.EMPTYSTRING);
	}

	// //////////////////////////////////////////////////////////////////////////
	// //////////////////

	/**
	 * get pool child
	 * 
	 * @param i the index of the child, or <code>-1</code> to create a new one
	 * @param mAttrib the attribute of the child
	 * @return JDFPartStatus: the pool child matching the given conditions
	 */
	public JDFPartStatus getPoolChild(int i, JDFAttributeMap mAttrib)
	{
		return (JDFPartStatus) getPoolChildGeneric(i, ElementName.PARTSTATUS, mAttrib, JDFConstants.EMPTYSTRING);
	}

	/**
	 * Set the status of the entire StatusPool
	 * 
	 * @param s the status to set
	 */
	@Override
	public void setStatus(JDFElement.EnumNodeStatus s)
	{
		final JDFNode n = (JDFNode) getParentNode_KElement();
		n.setStatus(JDFElement.EnumNodeStatus.Pool);
		super.setStatus(s);
	}

	// ////////////////////////////////////////////////////////////////////
	/**
	 * Set the status of a part defined in StatusPool
	 * <p>
	 * default setStatus(vmPart, s, JDFConstants.EMPTYSTRING)
	 * 
	 * @param vmPart
	 * @param s
	 * @param statusDetails
	 */
	public void setStatus(VJDFAttributeMap vmPart, JDFElement.EnumNodeStatus s, String statusDetails)
	{
		for (int i = 0; i < vmPart.size(); i++)
		{
			setStatus(vmPart.elementAt(i), s, statusDetails);
		}
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * Set the status of a part defined in StatusPool
	 * <p>
	 * default setStatus(mPart, s, JDFConstants.EMPTYSTRING)
	 * 
	 * @param mPart
	 * @param s
	 * @param statusDetails
	 */
	public void setStatus(JDFAttributeMap mPart, JDFElement.EnumNodeStatus s, String statusDetails)
	{
		if ((mPart != null) && !mPart.isEmpty())
		{
			final JDFPartStatus ps = getCreatePartStatus(mPart);
			ps.setStatus(s);
			if (statusDetails != null && !statusDetails.equals(JDFConstants.EMPTYSTRING))
			{
				ps.setStatusDetails(statusDetails);
			}
		}
		else
		{
			setStatus(s);
			if (statusDetails != null && !statusDetails.equals(JDFConstants.EMPTYSTRING))
			{
				setStatusDetails(statusDetails);
			}
		}
	}

	/**
	 * check whether the status is valid
	 * 
	 * @return true if status is valid
	 */
	public boolean validStatus()
	{
		if (!super.validAttribute(AttributeName.STATUS, null, KElement.EnumValidationLevel.Complete))
		{
			return false;
		}
		if (getStatus().equals(JDFElement.EnumNodeStatus.Pool))
		{
			return false;
		}
		return true;
	}
}
