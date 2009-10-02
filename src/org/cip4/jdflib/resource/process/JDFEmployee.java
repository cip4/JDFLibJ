/*--------------------------------------------------------------------------------------------------
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
 */
/**
 * ==========================================================================
 * class JDFEmployee
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoEmployee;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.jmf.JDFEmployeeDef;
import org.cip4.jdflib.util.ContainerUtil;
import org.w3c.dom.DOMException;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * before June 4, 2009
 */
public class JDFEmployee extends JDFAutoEmployee implements IMatches
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFEmployee
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 * 
	 */
	public JDFEmployee(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFEmployee
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFEmployee(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFEmployee
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * 
	 * @throws DOMException
	 */
	public JDFEmployee(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoEmployee#toString()
	 */
	@Override
	public String toString()
	{
		return "JDFEmployee[  --> " + super.toString() + " ]";
	}

	/**
	 * returns true if the input object is equivalent to this or null</br> valid object types are:<br/>
	 * Employee<br/>
	 * EmployeeDef<br/>
	 * String - must match @PersonalID <br/>
	 * 
	 * @see org.cip4.jdflib.ifaces.IMatches#matches(java.lang.Object)
	 */
	public boolean matches(final Object subset)
	{
		if (subset == null)
		{
			return true; // ( matches contract requires true for null to allow
			// wildcards
		}

		if (subset instanceof JDFEmployee)
		{
			// TODO more criteria - person etc.
			final JDFEmployee employee = (JDFEmployee) subset;
			return ContainerUtil.equals(getPersonalID(), employee.getPersonalID());
		}
		else if (subset instanceof JDFEmployeeDef)
		{
			final JDFEmployeeDef ed = (JDFEmployeeDef) subset;
			return ContainerUtil.equals(getPersonalID(), ed.getPersonalID());
		}
		else if (subset instanceof String)
		{
			return ContainerUtil.equals(getPersonalID(), subset);
		}

		return false;
	}

	/**
	 * get the PersonalID, defaulting to productID
	 * @see org.cip4.jdflib.auto.JDFAutoEmployee#getPersonalID()
	 */
	@Override
	public String getPersonalID()
	{
		if (hasAttribute(AttributeName.PERSONALID))
		{
			return super.getPersonalID();
		}
		return super.getProductID();
	}

	/**
	 * get the productID, defaulting to PersonalID
	 * @see org.cip4.jdflib.auto.JDFAutoEmployee#getProductID()
	 */
	@Override
	public String getProductID()
	{
		if (hasAttribute(AttributeName.PRODUCTID))
		{
			return super.getProductID();
		}
		return super.getPersonalID();
	}
}
// ==========================================================================
