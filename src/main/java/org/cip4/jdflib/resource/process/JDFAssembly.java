/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
/**
 *
 * JDFAssembly.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import java.util.Collection;
import java.util.List;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAssembly;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.ContainerUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFAssembly extends JDFAutoAssembly
{
	private static final long serialVersionUID = 1L;

	private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[1];
	static
	{
		atrInfoTable[0] = new AtrInfoTable(AttributeName.ORDER, 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumOrder.getEnum(0), "Gathering");
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		return super.getTheAttributeInfo().updateReplace(atrInfoTable);
	}

	/**
	 * Constructor for JDFAssembly
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 *
	 */
	public JDFAssembly(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAssembly
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 *
	 */
	public JDFAssembly(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAssembly
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 *
	 */
	public JDFAssembly(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
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
		return "JDFAssembly[  --> " + super.toString() + " ]";
	}

	/**
	 * get a list of all attributes
	 *
	 * @param attName the attribute to collect
	 * @return the unified vector of attribute values, null if none found
	 */
	public VString getAssemblyAttributes(final String attName)
	{
		final VElement leaves = getLeaves(false);
		final VString v = new VString();
		for (final KElement e : leaves)
		{
			final VElement sections = e.getChildElementVector(ElementName.ASSEMBLYSECTION, null);
			String at = e.getAttribute(attName, null, null);
			if (at != null)
				v.add(at);
			if (sections != null)
			{
				for (final KElement es : sections)
				{
					at = es.getAttribute(attName, null, null);
					if (at != null)
						v.add(at);
				}
			}
		}
		ContainerUtil.unify(v);
		return v.size() == 0 ? null : v;
	}

	/**
	 * get a list of all attributes
	 *
	 * @param attName the attribute to collect
	 * @return the unified vector of attribute values, null if none found
	 */
	public StringArray getAllAssemblyIDs()
	{
		final List<JDFResource> leaves = getLeafArray(false);
		final StringArray v = new StringArray();
		for (final JDFResource e : leaves)
		{
			final JDFAssembly leaf = (JDFAssembly) e;
			v.addNonEmpty(leaf.getAssemblyID());
			final VString aids = leaf.getAssemblyIDs();
			v.addAll(aids);
			final Collection<JDFAssemblySection> asss = leaf.getAllAssemblySection();

			for (final JDFAssemblySection ass : asss)
			{
				v.addAll(ass.getAllAssemblyIDs());
			}
		}

		ContainerUtil.unify(v);
		return v.isEmpty() ? null : v;
	}
}
