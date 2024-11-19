/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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

import java.util.HashSet;
import java.util.Set;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * 
 * the old implementattion was obsolete this implementation extracts more data from the xml schema that can be injected into the json schema
 * 
 */
public class XJDFSchemaPrune
{
	final Set<KElement> keep;
	private final KElement schema;
	private boolean checkAttributes;

	/**
	 * 
	 */
	public XJDFSchemaPrune(final XMLDoc rootSchema)
	{
		keep = new HashSet<>();
		schema = rootSchema.getRoot();
	}

	public KElement prune(final KElement... examples)
	{
		for (final KElement e : examples)
		{
			addPrune(e);
		}
		return getPrunedSchema(schema);
	}

	KElement getPrunedSchema(final KElement schemaElem)
	{
		for (final KElement e : schemaElem.getChildrenByTagName(null))
		{
			if (keep.contains(e))
			{
				getPrunedSchema(e);
			}
			else
			{
				e.deleteNode();
			}
		}
		return schemaElem;
	}

	void addPrune(final KElement example)
	{
		final VElement v = getElementsByRef(example);
		for (final KElement xsElement : v)
		{
			addSchemaElement(xsElement);
			addComplexContent(xsElement);

			addAttributes(xsElement, example);
		}
		final VElement kids = example.getChildElementVector(null, null);
		for (final KElement kid : kids)
		{
			addPrune(kid);
		}

	}

	void addComplexContent(final KElement xsElement)
	{
		final KElement cc = getExtension(xsElement);
		addSchemaElement(cc);
	}

	KElement getExtension(final KElement xsElement)
	{
		KElement cc = xsElement == null ? null : xsElement.getElement(XSDConstants.XS_COMPLEX_CONTENT);
		cc = cc == null ? null : cc.getElement(XSDConstants.XS_EXTENSION);
		return cc;
	}

	VElement getElementsByRef(final KElement example)
	{
		final VElement v = new VElement();
		final String nodeName = example.getNodeName();
		final KElement xsElement = getElementByName(nodeName);
		ContainerUtil.add(v, xsElement);
		KElement ct = xsElement.getElement(XSDConstants.XS_COMPLEX_TYPE);
		if (ct == null)
			ct = schema.getChildWithAttribute(XSDConstants.XS_COMPLEX_TYPE, XSDConstants.NAME, null, nodeName, 0, false);
		ContainerUtil.add(v, ct);
		final KElement parent = example.getParentNode_KElement();
		if (parent != null)
		{
			KElement schemaParent = getElementByName(parent.getNodeName());
			while (schemaParent != null)
			{

				KElement ref = schemaParent.getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.REF, null, nodeName, 0, false);
				ContainerUtil.add(v, ref);
				final String parenttyp = schemaParent.getNonEmpty(XSDConstants.TYPE);
				KElement schemaParentCT = parenttyp == null ? schemaParent.getElement(XSDConstants.XS_COMPLEX_TYPE)
						: schema.getChildWithAttribute(XSDConstants.XS_COMPLEX_TYPE, XSDConstants.NAME, null, parenttyp, 0, false);
				while (schemaParentCT != null)
				{
					ContainerUtil.add(v, schemaParentCT);
					ref = schemaParentCT.getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.REF, null, nodeName, 0, false);
					ContainerUtil.add(v, ref);
					final KElement ext = getExtension(schemaParentCT);
					final String base = ext == null ? null : ext.getNonEmpty(XSDConstants.BASE);
					schemaParentCT = base == null ? null : schema.getChildWithAttribute(XSDConstants.XS_COMPLEX_TYPE, XSDConstants.NAME, null, base, 0, false);
				}
				String subst = xsElement.getAttribute(XSDConstants.SUBSTITUTION_GROUP);
				while (!StringUtil.isEmpty(subst))
				{
					final KElement next = getElementByName(subst);
					ContainerUtil.add(v, next);
					KElement xsSubst = schemaParent.getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.REF, null, subst, 0, false);
					if (xsSubst == null)
					{
						xsSubst = schema.getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null, subst, 0, false);
					}
					ContainerUtil.add(v, xsSubst);
					final KElement ctSubst = schema.getChildWithAttribute(XSDConstants.XS_COMPLEX_TYPE, XSDConstants.NAME, null, subst, 0, false);
					ContainerUtil.add(v, ctSubst);
					subst = next == null ? null : next.getAttribute(XSDConstants.SUBSTITUTION_GROUP);
				}

				KElement extension = getExtension(ct);
				while (extension != null)
				{
					final String base = extension.getNonEmpty(XSDConstants.BASE);
					final KElement ctBase = schema.getChildWithAttribute(XSDConstants.XS_COMPLEX_TYPE, XSDConstants.NAME, null, base, 0, false);
					ContainerUtil.add(v, ctBase);
					extension = getExtension(ctBase);
				}

				final String parentSubst = schemaParent.getNonEmpty(XSDConstants.SUBSTITUTION_GROUP);
				schemaParent = parentSubst == null ? null : getElementByName(parentSubst);

			}
		}

		return v;

	}

	void addAttributes(final KElement root, final KElement example)
	{
		final VElement atts = root.getChildrenByTagName(XSDConstants.XS_ATTRIBUTE);
		for (final KElement e : atts)
		{
			if (!checkAttributes || example.hasAttribute(e.getAttribute(XSDConstants.NAME)))
				addAttribute(e);
		}

	}

	void addAttribute(final KElement e)
	{
		addTree(e);
		final String type = e.getNonEmpty(XSDConstants.TYPE);
		if (type != null)
		{
			addSimpleType(type);
		}
		else
		{
			final KElement stypeLocal = e.getElement(XSDConstants.XS_SIMPLE_TYPE);
			addSimpleType(stypeLocal);
		}
	}

	void addSimpleType(final String type)
	{
		if (!"xs:".equals(StringUtil.leftStr(type, 3)))
		{
			final KElement stType = schema.getChildWithAttribute(XSDConstants.XS_SIMPLE_TYPE, XSDConstants.NAME, null, type, 0, false);
			addSimpleType(stType);
		}
	}

	void addSimpleType(final KElement stType)
	{
		if (stType != null)
		{
			addTree(stType);
			final KElement list = stType.getElement(XSDConstants.XS_LIST);
			if (list != null)
			{
				addSimpleType(list.getAttribute(XSDConstants.ITEM_TYPE));
			}
			final KElement base = stType.getElement(XSDConstants.XS_RESTRICTION);
			if (base != null)
			{
				addSimpleType(base.getAttribute("base"));
			}
		}
	}

	void addTree(final KElement e)
	{
		for (final KElement c : e.getChildrenByTagName(null))
		{
			keep.add(c);
		}
		addSchemaElement(e);

	}

	void addSchemaElement(final KElement e)
	{
		KElement e2 = e;
		while (e2 != null)
		{
			keep.add(e2);
			e2 = e2.getParentNode_KElement();
		}
	}

	KElement getElementByName(final String nodeName)
	{
		return schema.getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null, nodeName, 0, false);
	}

	public boolean isCheckAttributes()
	{
		return checkAttributes;
	}

	public void setCheckAttributes(final boolean checkAttributes)
	{
		this.checkAttributes = checkAttributes;
	}

	@Override
	public String toString()
	{
		return "XJDFSchemaPrune [checkAttributes=" + checkAttributes + "]";
	}

}
