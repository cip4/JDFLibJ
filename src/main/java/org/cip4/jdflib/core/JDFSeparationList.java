/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSeparationList
 *
 * Last changes
 *
 * 2002-07-02 JG - newly created
 */

package org.cip4.jdflib.core;

import java.util.Collection;
import java.util.List;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoSeparationList;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;

/**
 * This class represents a list of SeparationSpec elements it allows high level string manipulation of the separation names by hiding the fact that the separations are written in
 * SeparationSpec/@Name
 */
public class JDFSeparationList extends JDFAutoSeparationList
{
	public static final VString SEPARATIONS_CMYK = JDFConstants.SEPARATIONS_CMYK;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSeparationList
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFSeparationList(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSeparationList
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFSeparationList(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSeparationList
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFSeparationList(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFSeparationList[ -->" + super.toString() + "]";
	}

	/**
	 * Get a list of all separation names in the SeparationSpec elements
	 *
	 * @return the vector of separation names - empty VString if no separations are present
	 */
	public VString getSeparations()
	{
		final VString vName = new VString();
		final Collection<JDFSeparationSpec> v = getAllSeparationSpec();
		for (final JDFSeparationSpec sep : v)
		{
			final String sepName = sep.getName();
			vName.add(sepName);
		}
		return vName;
	}

	/**
	 * Get a list of all separation names in the SeparationSpec elements
	 *
	 * @return the vector of separation names - empty VString if no separations are present
	 */
	public StringArray getSeparationList()
	{
		final StringArray vName = new StringArray();
		final Collection<JDFSeparationSpec> v = getAllSeparationSpec();
		for (final JDFSeparationSpec sep : v)
		{
			vName.add(sep.getName());
		}
		return vName;
	}

	/**
	 * set all separation names in the SeparationSpec elements, remove any prior elements
	 *
	 * @param vSeps the vector of separation names to set
	 */
	public void setSeparations(final List<String> vSeps)
	{
		removeChildren(ElementName.SEPARATIONSPEC, null, null);
		appendSeparations(vSeps);
	}

	/**
	 * set all separation names in the SeparationSpec elements, remove any prior elements
	 *
	 * @param vSeps the vector of separation names to set
	 */
	public void setSeparations(final VString vSeps)
	{
		setSeparations((List<String>) vSeps);
	}

	public void appendSeparations(final VString vSeps)
	{
		appendSeparations((List<String>) vSeps);
	}

	/**
	 * append all separation names in the SeparationSpec elements without removing any prior elements
	 *
	 * @param vSeps the vector of separation names to append
	 */
	public void appendSeparations(final List<String> vSeps)
	{
		if (vSeps != null)
			for (final String sep : vSeps)
			{
				appendSeparation(sep);
			}
	}

	public void ensureSeparations(final VString vSeps)
	{
		ensureSeparations((List<String>) vSeps);
	}

	/**
	 * ensure all separation names in the SeparationSpec elements without removing any prior elements
	 *
	 * @param vSeps the vector of separation names to append
	 */
	public void ensureSeparations(final List<String> vSeps)
	{
		if (vSeps != null)
		{
			for (final String sep : vSeps)
			{
				getCreateSeparation(sep);
			}
		}
	}

	/**
	 * convenience utility to set to cmyk
	 *
	 *
	 */
	public void setCMYK()
	{
		setSeparations(SEPARATIONS_CMYK);
	}

	/**
	 * append a separationspec with a given name to this if it does not yet exist
	 *
	 * @param sep the separation name
	 */
	public JDFSeparationSpec getCreateSeparation(final String sep)
	{
		final JDFSeparationSpec separationSpec = getSeparationSpec(sep);
		if (separationSpec == null)
		{
			return appendSeparation(sep);
		}
		return separationSpec;
	}

	/**
	 * append a separationspec with a given name to this
	 *
	 * @param sep the separation name
	 */
	public JDFSeparationSpec appendSeparation(final String sep)
	{
		final JDFSeparationSpec separationSpec = appendSeparationSpec();
		separationSpec.setName(sep);
		return separationSpec;
	}

	/**
	 * get the separationSpec for a given separartion
	 *
	 * @param sep
	 * @return
	 */
	public JDFSeparationSpec getSeparationSpec(final String sep)
	{
		return (JDFSeparationSpec) getChildWithAttribute(ElementName.SEPARATIONSPEC, AttributeName.NAME, null, sep, 0, true);
	}

	/**
	 * remove a separationspec with a given name from this
	 *
	 * @param sep the separation name
	 * @return int the index of the removed separation; -1 if none found
	 */
	public int removeSeparation(final String sep)
	{
		final VString vs = getSeparations();
		final int index = vs.index(sep);
		if (index >= 0)
		{
			getSeparationSpec(index).deleteNode();
		}
		return index;
	}

	/**
	 * Get the n'th separation name in the SeparationSpec elements
	 *
	 * @param iSkip the index of the SeparationSpec
	 * @return separation names, null if iSkip > nSeparations
	 */
	public String getSeparation(final int iSkip)
	{
		final JDFSeparationSpec ss = getSeparationSpec(iSkip);
		if (ss == null)
		{
			return null;
		}
		return ss.getName();
	}

	/**
	 *
	 * ensure that all separations are unique
	 *
	 * @return the unified vector of separation names
	 */
	public VString unify()
	{
		final VString v = getSeparations();
		final int l = v.size();
		v.unify();
		if (v.size() != l)
			setSeparations(v);
		return v;
	}

	/**
	 *
	 * @param seps
	 */
	public void removeSeparations(final List<String> seps)
	{
		if (seps != null)
		{
			for (final String sep : seps)
			{
				removeSeparation(sep);
			}
		}

	}
}
