/*--------------------------------------------------------------------------------------------------
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
 */
/**
 ==========================================================================
 class JDFResourceQuParams extends JDFResource
 ==========================================================================
 @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
 ALL RIGHTS RESERVED
 @Author: sabjon@topmail.de   using a code generator
 Warning! very preliminary test version. Interface subject to change without prior notice!
 Revision history:    ...
 **/

package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoResourceQuParams;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.INodeIdentifiable;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.NodeIdentifier;
import org.cip4.jdflib.resource.JDFResource;

public class JDFResourceQuParams extends JDFAutoResourceQuParams implements INodeIdentifiable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFResourceQuParams
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFResourceQuParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFResourceQuParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFResourceQuParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFResourceQuParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFResourceQuParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFResourceQuParams[  --> " + super.toString() + " ]";
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
	public void setPartMapVector(VJDFAttributeMap vParts)
	{
		super.setPartMapVector(vParts);
	}

	/**
	 * set all parts to those defined by mPart
	 * 
	 * @param mPart attribute map for the part to set
	 */
	@Override
	public void setPartMap(JDFAttributeMap mPart)
	{
		super.setPartMap(mPart);
	}

	/**
	 * remove the part defined by mPart
	 * 
	 * @param mPart attribute map for the part to remove
	 */
	@Override
	public void removePartMap(JDFAttributeMap mPart)
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
	public boolean hasPartMap(JDFAttributeMap mPart)
	{
		return super.hasPartMap(mPart);
	}

	/**
	 * sets all relevant parameters of <code>this</code> to the values specified in resourceLink or its linked resource
	 * or JDF node
	 * 
	 * @param resourceLink the resourceLink to extract the information from
	 */
	public void setLink(JDFResourceLink resourceLink)
	{
		if (resourceLink == null)
			return;
		final JDFResource r = resourceLink.getTarget();

		final JDFNode parentJDF = resourceLink.getParentJDF();
		setJDF(parentJDF);
		setProcessUsage(resourceLink.getEnumProcessUsage());

		if (r.hasAttribute(AttributeName.PRODUCTID))
			setProductID(r.getProductID());
		setResourceID(r.getID());
		setResourceName(r.getLocalName());
		setUsage(resourceLink.getUsage());
		setPartMapVector(resourceLink.getPartMapVector());

	}

	/**
	 * sets all relevant parameters of this to the values specified in the JDF node
	 * 
	 * @param parentJDF
	 */
	public void setJDF(final JDFNode parentJDF)
	{
		if (parentJDF == null)
			return;
		setJobID(parentJDF.getJobID(true));
		setJobPartID(parentJDF.getJobPartID(true));
	}

	/**
	 * set ProcessUsage to the enum processusage
	 * 
	 * @param processUsage
	 */
	public void setProcessUsage(EnumProcessUsage processUsage)
	{
		setAttribute(AttributeName.PROCESSUSAGE, processUsage == null ? null : processUsage.getName(), null);
	}

	/**
	 * gets the NodeIdetifier that matches this
	 * 
	 * @return {@link NodeIdentifier} the matching NodeIdentifier
	 */
	public NodeIdentifier getIdentifier()
	{
		return new NodeIdentifier(getJobID(), getJobPartID(), getPartMapVector());
	}

	/**
	 * @see org.cip4.jdflib.ifaces.INodeIdentifiable#setIdentifier(org.cip4.jdflib.node.JDFNode.NodeIdentifier)
	 * @param ni
	 */
	public void setIdentifier(NodeIdentifier ni)
	{
		NodeIdentifier niLocal = ni;
		
		if (niLocal == null)
			niLocal = new NodeIdentifier();

		setJobID(niLocal.getJobID());
		setJobPartID(niLocal.getJobPartID());
		setPartMapVector(niLocal.getPartMapVector());
	}

}
