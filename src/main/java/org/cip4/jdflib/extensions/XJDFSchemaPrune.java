/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.extensions.XSDConstants.eAttributeUse;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.ListMap;
import org.cip4.jdflib.util.StringUtil;

/**
 * the old implementattion was obsolete this implementation extracts more data from the xml schema that can be injected into the json schema
 */
public class XJDFSchemaPrune
{
	final Set<KElement> keep;
	final ListMap<String, String> keepValues;
	private final KElement schema;
	private boolean checkAttributes;
	private boolean checkAttributeValues;

	public boolean isCheckAttributeValues()
	{
		return checkAttributeValues;
	}

	public void setCheckAttributeValues(boolean checkAttributeValues)
	{
		this.checkAttributeValues = checkAttributeValues;
	}

	private boolean removeForeign;
	private String prefix;
	Log log = LogFactory.getLog(XJDFSchemaPrune.class);

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}

	public boolean isRemoveForeign()
	{
		return removeForeign;
	}

	public void setRemoveForeign(final boolean removeForeign)
	{
		this.removeForeign = removeForeign;
	}

	/**
	 *
	 */
	public XJDFSchemaPrune(final XMLDoc rootSchema)
	{
		keep = new HashSet<>();
		schema = rootSchema.getRoot().cloneNewDoc();
		checkAttributes = true;
		checkAttributeValues = false;
		removeForeign = true;
		prefix = "xjdf";
		keepValues = new ListMap<>();
	}

	String ensureprefix(String in)
	{
		if (!StringUtil.isEmpty(in) && StringUtil.isEmpty(KElement.xmlnsPrefix(in)))
		{
			return prefix + ":" + in;
		}
		return in;
	}

	public KElement prune(final KElement... examples)
	{
		for (final KElement e : examples)
		{
			addPrune(e);
		}
		return getPrunedSchema(schema);
	}

	public KElement prune(final BaseXJDFHelper... examples)
	{
		for (final BaseXJDFHelper h : examples)
		{
			addPrune(h.getRoot());
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
		if (checkAttributeValues)
		{
			cleanupValues(schemaElem);
		}
		return schemaElem;
	}

	void cleanupValues(KElement schemaElem)
	{
		for (final KElement en : schemaElem.getChildrenByTagName(XSDConstants.XS_ENUMERATION))
		{
			final String name = en.getInheritedAttribute(XSDConstants.NAME);
			final List<String> valsArray = keepValues.get(name);
			if (!ContainerUtil.isEmpty(valsArray) && !valsArray.contains(en.getAttribute(XSDConstants.VALUE)))
			{
				en.deleteNode();
			}
		}

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
		final String xsitype = example.getAttribute(JDFConstants.XSITYPE);
		final KElement xsElement = getElementByRef(nodeName, xsitype);
		if (xsElement != null && !StringUtil.isEmpty(nodeName))
		{

			final String type = xsElement.getAttribute(XSDConstants.TYPE);
			addKeep(v, xsElement);
			final KElement ct = getComplexType(xsElement, nodeName, type);
			addComplexType(v, ct);
			checkGroup(v, ct);
			final KElement ct2 = getComplexType(null, null, xsitype);
			addComplexType(v, ct2);
			checkGroup(v, ct2);
			final KElement parent = example.getParentNode_KElement();
			if (parent != null)
			{
				KElement schemaParent = getElementByName(parent.getAttribute(JDFConstants.XSITYPE));
				if (schemaParent == null)
				{
					schemaParent = getElementByName(parent.getNodeName());
				}
				while (schemaParent != null)
				{

					KElement ref = getElementByName(schemaParent, XSDConstants.XS_ELEMENT, XSDConstants.REF, ensureprefix(xsitype));
					if (ref == null)
					{
						ref = getElementByName(schemaParent, XSDConstants.XS_ELEMENT, XSDConstants.REF, ensureprefix(nodeName));
					}
					addKeep(v, ref);
					checkSchemaParent(v, nodeName, schemaParent, parent.getAttribute(JDFConstants.XSITYPE));
					checkSubstitution(v, xsElement, schemaParent);
					checkExtension(v, ct);
					checkGroup(v, ct);
					checkExtension(v, ct2);
					checkGroup(v, ct2);

					final String parentSubst = schemaParent.getNonEmpty(XSDConstants.SUBSTITUTION_GROUP);
					schemaParent = parentSubst == null ? null : getElementByName(parentSubst);

				}
			}
		}
		v.unify();
		return v;

	}

	KElement getElementByRef(String nodeName, String xsitype)
	{
		KElement ret = null;
		if (!StringUtil.isEmpty(xsitype))
		{
			ret = getElementByName(schema, XSDConstants.XS_ELEMENT, XSDConstants.REF, ensureprefix(xsitype));
			if (ret == null)
			{
				ret = getElementByName(ensureprefix(xsitype));
			}
		}
		if (ret == null)
		{
			ret = getElementByName(nodeName);
		}
		return ret;
	}

	KElement getComplexType(final KElement xsElement, final String nodeName, String type)
	{
		KElement ct = xsElement == null ? null : xsElement.getElement(XSDConstants.XS_COMPLEX_TYPE);
		if (ct == null && !StringUtil.isEmpty(type))
		{
			ct = schema.getChildWithAttribute(XSDConstants.XS_COMPLEX_TYPE, XSDConstants.NAME, null, ensureprefix(type), 0, false);
		}
		if (ct == null && !StringUtil.isEmpty(type))
		{
			ct = schema.getChildWithAttribute(XSDConstants.XS_COMPLEX_TYPE, XSDConstants.NAME, null, KElement.xmlnsLocalName(type), 0, false);
		}
		if (ct == null && !StringUtil.isEmpty(nodeName))
		{
			ct = schema.getChildWithAttribute(XSDConstants.XS_COMPLEX_TYPE, XSDConstants.NAME, null, nodeName, 0, false);
		}
		if (ct == null && !StringUtil.isEmpty(type))
		{
			final KElement elem = getElementByName(type);
			final String newType = elem == null ? null : elem.getAttribute(XSDConstants.TYPE);
			if (!StringUtil.isEmpty(newType) && !type.equals(newType))
			{
				ct = getComplexType(null, null, newType);
			}
		}
		return ct;
	}

	void addComplexType(final VElement v, final KElement ct)
	{
		addKeep(v, ct);
		if (ct != null && !removeForeign)
		{
			addSchemaElement(ct.getElement(XSDConstants.XS_ANY));

			addSchemaElement(ct.getElement(XSDConstants.XS_ANY_ATTRIBUTE));
			final KElement seq = ct.getElement(XSDConstants.XS_SEQUENCE);
			if (seq != null)
			{
				addSchemaElement(seq.getElement(XSDConstants.XS_ANY));
			}
		}

	}

	Collection<KElement> addKeep(final VElement v, final KElement ct)
	{
		if (ct != null && !XSDConstants.XS_SCHEMA.equals(ct.getNodeName()))
		{
			ContainerUtil.add(v, ct);
			return addKeep(v, ct.getParentNode_KElement());
		}
		return v;
	}

	void checkSchemaParent(final VElement v, String nodeName, final KElement schemaParent, String xsitype)
	{
		final String localtype = schemaParent.getNonEmpty(XSDConstants.TYPE);
		final String parenttyp = StringUtil.isEmpty(xsitype) ? localtype : xsitype;
		KElement schemaParentCT = getComplexType(schemaParent, null, parenttyp);
		while (schemaParentCT != null)
		{
			addComplexType(v, schemaParentCT);
			final KElement ref = getElementByName(schemaParentCT, XSDConstants.XS_ELEMENT, XSDConstants.REF, ensureprefix(nodeName));
			addKeep(v, ref);
			final KElement refCT = getComplexType(null, nodeName, ensureprefix(nodeName));
			addKeep(v, refCT);
			final KElement ext = getExtension(schemaParentCT);
			final String base = ext == null ? null : ext.getNonEmpty(XSDConstants.BASE);
			schemaParentCT = getComplexType(null, null, base);
		}
	}

	void checkExtension(final VElement v, final KElement ct)
	{
		KElement extension = getExtension(ct);
		while (extension != null)
		{
			final String base = extension.getNonEmpty(XSDConstants.BASE);
			final KElement ctBase = getComplexType(null, null, base);
			addComplexType(v, ctBase);
			extension = getExtension(ctBase);
		}
	}

	void checkSubstitution(final VElement v, final KElement xsElement, final KElement schemaParent)
	{
		String subst = xsElement.getAttribute(XSDConstants.SUBSTITUTION_GROUP);
		while (!StringUtil.isEmpty(subst))
		{
			final KElement next = getElementByName(subst);
			addKeep(v, next);
			KElement xsSubst = schemaParent.getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.REF, null, subst, 0, false);
			if (xsSubst == null)
			{
				xsSubst = schema.getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null, subst, 0, false);
			}
			addKeep(v, xsSubst);
			final KElement ctSubst = getComplexType(null, subst, subst);
			addKeep(v, ctSubst);
			subst = next == null ? null : next.getAttribute(XSDConstants.SUBSTITUTION_GROUP);
		}
	}

	void checkGroup(final VElement v, final KElement xsComplexType)
	{
		for (int i = 0; i < 10; i++)
		{
			final KElement group = xsComplexType == null ? null
					: xsComplexType.getChildWithAttribute(XSDConstants.XS_GROUP, XSDConstants.REF, null, "*", i, false);
			final String groupref = group == null ? null : group.getNonEmpty(XSDConstants.REF);
			if (groupref != null)
			{
				final KElement next = getElementByName(XSDConstants.XS_GROUP, groupref);

				final VElement elements = next == null ? null : next.getChildrenByTagName(XSDConstants.XS_ELEMENT);
				if (elements != null)
				{
					addKeep(v, next);
					addKeep(v, group);
					for (final KElement element : elements)
					{
						addKeep(v, element);
						final KElement ctSubst = getComplexType(null, element.getAttribute(XSDConstants.NAME), element.getAttribute(XSDConstants.REF));
						addKeep(v, ctSubst);
					}
				}
			}
			else
			{
				break;
			}

		}
	}

	void addAttributes(final KElement root, final KElement example)
	{
		final VElement atts = root.getChildrenByTagName(XSDConstants.XS_ATTRIBUTE);
		for (final KElement e : atts)
		{
			if (!checkAttributes || example.hasAttribute(e.getAttribute(XSDConstants.NAME)) || isRequired(e))
			{
				addAttribute(e, example.getNonEmpty(e.getAttribute(XSDConstants.NAME)));
			}
		}

	}

	boolean isRequired(final KElement e)
	{
		return eAttributeUse.required.equals(eAttributeUse.getEnum(e.getAttribute(XSDConstants.USE)));
	}

	void addAttribute(final KElement e, String val)
	{
		addTree(e);
		final String type = e.getNonEmpty(XSDConstants.TYPE);
		if (type != null)
		{
			addSimpleType(type, val);
		}
		else
		{
			final KElement stypeLocal = e.getElement(XSDConstants.XS_SIMPLE_TYPE);
			addSimpleType(stypeLocal, val);
		}
	}

	void addSimpleType(final String type, String val)
	{
		if (!"xs:".equals(StringUtil.leftStr(type, 3)))
		{
			final KElement stType = schema.getChildWithAttribute(XSDConstants.XS_SIMPLE_TYPE, XSDConstants.NAME, null, type, 0, false);
			addSimpleType(stType, val);
		}
	}

	void addSimpleType(final KElement stType, String val)
	{
		if (stType != null)
		{
			addTree(stType);
			if (checkAttributeValues && !StringUtil.isEmpty(val))
			{
				for (final String val0 : StringUtil.tokenize(val, null, false))
				{
					keepValues.putOne(stType.getInheritedAttribute(XSDConstants.NAME), val0);
				}
			}
			final KElement list = stType.getElement(XSDConstants.XS_LIST);
			if (list != null)
			{
				addSimpleType(list.getAttribute(XSDConstants.ITEM_TYPE), val);
			}
			final KElement base = stType.getElement(XSDConstants.XS_RESTRICTION);
			if (base != null)
			{
				addSimpleType(base.getAttribute("base"), val);
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
			final boolean added = keep.add(e2);
			if (added)
			{
				log.info("added " + XSDUtil.shortString(e2));
			}
			e2 = added ? e2.getParentNode_KElement() : null;
		}
	}

	KElement getElementByName(String xstype, final String nodeName)
	{
		return getElementByName(schema, xstype, XSDConstants.NAME, nodeName);
	}

	KElement getElementByName(KElement base, String xstype, String xsAttName, final String nodeName)
	{
		if (StringUtil.isEmpty(nodeName))
		{
			return null;
		}
		KElement kid = base.getChildWithAttribute(xstype, xsAttName, null, nodeName, 0, false);
		if (kid == null)
		{
			kid = base.getChildWithAttribute(xstype, xsAttName, null, KElement.xmlnsLocalName(nodeName), 0, false);
		}
		return kid;
	}

	KElement getElementByName(final String nodeName)
	{
		return getElementByName(XSDConstants.XS_ELEMENT, nodeName);
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
		return "XJDFSchemaPrune [checkAttributes=" + checkAttributes + ", removeForeign=" + removeForeign + " " + keep.size();
	}

}
