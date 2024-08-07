/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.List;
import java.util.Set;

import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNameRange;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.resource.JDFResource;
import org.w3c.dom.Node;

/**
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class WalkElement extends BaseWalker
{
	/**
	 *
	 */
	protected JDFToXJDF jdfToXJDF;

	/**
	 *
	 */
	public WalkElement()
	{
		super();
	}

	/**
	 * fills this into the factory
	 *
	 * @param parent
	 *
	 */
	public void setParent(final JDFToXJDF parent)
	{
		jdfToXJDF = parent;
	}

	/**
	 * @param xjdf
	 * @return true if must continue
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		String nsURI = jdf.getNamespaceURI();
		if (JDFConstants.JDFNAMESPACE.equals(nsURI))
		{
			nsURI = jdfToXJDF.getSchemaURL(null);
		}
		final String nodeName = getXJDFName(jdf);
		final KElement eNew = nodeName == null ? xjdf : xjdf.appendElement(nodeName, nsURI);

		setAttributes(jdf, eNew);
		eNew.setText(jdf.getText());
		Node before = null;
		for (int i = 0; true; i++)
		{
			final String xmlComment = jdf.getXMLComment(i);
			if (xmlComment == null)
				break;
			final Node comment = eNew.appendXMLComment(xmlComment, null);
			if (before == null)
			{
				before = comment.getNextSibling();
			}
			else
			{
				eNew.insertBefore(comment, before);
			}
		}
		if (!jdfToXJDF.isRetainAll())
		{
			removeUnusedElements(jdf);
		}
		return eNew;
	}

	/**
	 * the new name
	 *
	 * @param jdf
	 * @return
	 */
	protected String getXJDFName(final KElement jdf)
	{
		return jdf.getNodeName();
	}

	/**
	 *
	 * @param jdf
	 * @param eNew
	 */
	protected void setAttributes(final KElement jdf, final KElement eNew)
	{
		final JDFAttributeMap map = (jdf instanceof JDFElement) ? convertRanges((JDFElement) jdf) : jdf.getAttributeMap_KElement();
		if (map != null)
		{
			final Set<String> keySet = map.keySet();
			for (final String key : keySet)
			{
				final String prefix = KElement.xmlnsPrefix(key);
				if (prefix != null)
				{
					String uri = eNew.getNamespaceURIFromPrefix(prefix);
					if (uri == null)
					{
						uri = jdf.getNamespaceURIFromPrefix(prefix);
						if (uri != null)
						{
							eNew.addNameSpace(prefix, uri);
						}
					}
				}
			}
		}

		if (!jdfToXJDF.isRetainAll() && map != null)
		{
			updateAttributes(map);
		}

		eNew.setAttributes(map);
	}

	/**
	 *
	 * @param map
	 */
	protected void updateAttributes(final JDFAttributeMap map)
	{
		if (!jdfToXJDF.isRetainAll())
		{
			map.renameKey(AttributeName.PRODUCTID, XJDFConstants.ExternalID);
			map.renameKey(AttributeName.ASSEMBLYIDS, XJDFConstants.BinderySignatureID);
			map.remove(AttributeName.XSITYPE);
		}
	}

	/**
	 * @param refLocalName
	 * @return true if must inline refLocalName
	 */
	protected boolean mustInline(final String refLocalName)
	{
		return JDFToXJDFDataCache.getInlineSet().contains(refLocalName);
	}

	/**
	 * remove tildes from ranges and rangelists
	 *
	 * @param jdf
	 * @return
	 */
	JDFAttributeMap convertRanges(final JDFElement jdf)
	{
		final JDFAttributeMap map = jdf.getAttributeMap_KElement();
		return convertRanges(map, jdf);
	}

	JDFAttributeMap convertRanges(final JDFAttributeMap map, final JDFElement jdf)
	{
		if (jdfToXJDF.isConvertTilde() && map != null)
		{
			final List<String> keys = map.getKeyList();
			for (final String key : keys)
			{
				if (EnumAttributeType.isRange(jdf.getAtrType(key)))
				{
					final JDFNameRangeList rl = JDFNameRangeList.createNameRangeList(map.get(key));

					if (rl != null)
					{
						final int size = rl.size();
						final StringBuilder buf = new StringBuilder();
						for (int i = 0; i < size; i++)
						{
							final JDFNameRange r = (JDFNameRange) rl.at(i);
							if (i > 0)
							{
								buf.append(JDFConstants.BLANK);
							}
							buf.append(r.getLeft());
							buf.append(JDFConstants.BLANK);
							buf.append(r.getRight());
						}
						map.put(key, buf.toString());
					}
				}
			}
		}
		return map;
	}

	/**
	 * zapp unused elements
	 *
	 * @param jdf
	 */
	protected void removeUnusedElements(final KElement jdf)
	{
		// nop
	}

	/**
	 *
	 * @param original the original element
	 * @param newName the new name
	 * @return
	 */
	protected KElement safeRename(final KElement original, final String newName)
	{
		if (original == null)
			return null;
		if (original instanceof JDFResource)
		{
			final List<? extends KElement> leaves = ((JDFResource) original).getDirectPartitionArray();
			for (final KElement leaf : leaves)
			{
				safeRename(leaf, newName);
			}
		}
		final KElement newElement = original.getParentNode_KElement().insertBefore(newName, original, null);
		newElement.copyInto(original, true);
		original.deleteNode();
		return newElement;
	}

}