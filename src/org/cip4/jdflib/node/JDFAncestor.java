/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2005 The International Cooperation for the Integration of
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
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERN
 }ATIONAL COOPERATION FOR
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
 }
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFAncestorPool.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.node;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAncestor;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource;

/**
 * 
 * Description: This class represents an JDFAncestor
 * 
 * @author Torsten Kaehlert
 * 
 * @version 1.0 2002-01-28
 * 
 */
public class JDFAncestor extends JDFAutoAncestor
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFAncestor
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFAncestor(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAncestor
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFAncestor(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAncestor
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFAncestor(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	public void setVersion(EnumVersion enumVar)
	{
		setAttribute(AttributeName.VERSION, enumVar.getName(), null);
	}

	public EnumVersion getVersion()
	{
		return EnumVersion.getEnum(getAttribute(AttributeName.VERSION, null, null));
	}

	/**
	 * version fixing routine for JDF
	 * 
	 * uses heuristics to modify this element and its children to be compatible with a given version in general, it will
	 * be able to move from low to high versions but potentially fail when attempting to move from higher to lower
	 * versions
	 * 
	 * @param version version that the resulting element should correspond to
	 * @return true if successful
	 */
	public boolean fixVersion(EnumVersion version)
	{
		boolean bRet = true;
		if (version != null)
		{
			setVersion(version);
			setMaxVersion(version);
			if (version.getValue() < EnumVersion.Version_1_3.getValue())
			{
				inlineRefElements(null, null, true);
			}
		}

		return super.fixVersion(version) && bRet;
	}

	public void setCategory(String value)
	{
		setAttribute(AttributeName.CATEGORY, value);
	}

	public String getCategory()
	{
		return getAttribute(AttributeName.CATEGORY);
	}

	/**
	 * Get the linked resources matching some conditions
	 * 
	 * @param mResAtt map of Resource attributes to search for
	 * @param bFollowRefs true if internal references shall be followed
	 * @return vector with all elements matching the conditions default: GetLinkedResources(new JDFAttributeMap(),
	 *         false)
	 */
	public VElement getLinkedResources(JDFAttributeMap mResAtt, boolean bFollowRefs)
	{
		final VElement vChild = getChildElementVector(null, null, null, true, 99999, false);
		final VElement vElem = new VElement();
		for (int i = 0; i < vChild.size(); i++)
		{
			JDFResource r = null;
			final Object elem = vChild.elementAt(i);
			if ((elem instanceof JDFRefElement))
			{
				final JDFRefElement l = (JDFRefElement) elem;
				r = l.getLinkRoot(null);
			}
			else if (elem instanceof JDFResource)
			{
				r = (JDFResource) elem;
			}
			if (r != null && r.includesAttributes(mResAtt, true))
			{
				vElem.addElement(r); // vElem.push_back(r);
				if (bFollowRefs)
				{
					vElem.appendUnique(r.getvHRefRes(bFollowRefs, true));
				}
			}
		}

		return vElem;
	}
}
