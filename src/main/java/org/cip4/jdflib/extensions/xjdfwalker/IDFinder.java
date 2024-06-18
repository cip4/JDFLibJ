/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2022 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
/**
 *
 */
package org.cip4.jdflib.extensions.xjdfwalker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNameRange;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi finds all ids
 */
public class IDFinder extends BaseElementWalker
{
	final Map<String, IDPart> theMap;
	final Map<String, String> indexMap;
	boolean needSplitPart;

	/**
	 * ensure that we always have a SIGNATURENAME partition in case we have a SHEETNAME
	 *
	 * @param part the partmap
	 *
	 * @return
	 */
	public static JDFAttributeMap getPartMap(final JDFPart part)
	{
		if (part == null)
			return new JDFAttributeMap();
		final JDFAttributeMap p = part.getAttributeMap();
		if (!p.isEmpty())
		{
			p.renameKey(XJDFConstants.BinderySignatureID, AttributeName.BINDERYSIGNATURENAME);
			final String sheetName = p.getNonEmpty(AttributeName.SHEETNAME);
			String signatureName = p.getNonEmpty(AttributeName.SIGNATURENAME);
			if (sheetName != null && signatureName == null)
			{
				signatureName = XJDFToJDFConverter.SIG + sheetName;
				p.put(AttributeName.SIGNATURENAME, signatureName);
				part.setSignatureName(signatureName);
			}
			p.renameKey(AttributeName.METADATA, AttributeName.METADATA0);
			final List<String> keys = p.getKeyList();
			for (final String key : keys)
			{
				checkKey(p, key);
			}
		}
		return p;
	}

	static void checkKey(final JDFAttributeMap p, final String key)
	{
		final EnumPartIDKey eKey = EnumPartIDKey.getEnum(key);
		if (eKey == null || eKey.isXJDF())
		{
			p.remove(key);
		}
		else if (key.endsWith("Index"))
		{
			final String val = p.getNonEmpty(key);
			if (val != null)
			{
				final VString v = new VString(val, null);
				if (v.size() % 2 == 0)
				{
					final JDFNameRangeList nrl = new JDFNameRangeList();
					for (int i = 0; i < v.size(); i += 2)
					{
						nrl.append(new JDFNameRange(v.get(i), v.get(i + 1)));
					}
					final String newVal = nrl.getString(0);
					p.put(key, newVal);
				}
			}
		}
	}

	/**
	 *
	 */
	public IDFinder()
	{
		super(new BaseWalkerFactory());
		theMap = new HashMap<>();
		indexMap = new HashMap<>();
		needSplitPart = false;
		new BaseWalker(getFactory()); // need a default walker
	}

	/**
	 * get a vector of all links and references of n and its children
	 *
	 * @param n the element to walk
	 * @return the vector of unlinked resourcerefs and resourceLinks
	 */
	public Map<String, IDPart> getMap(final KElement n)
	{
		theMap.clear();
		walkTree(n, null);
		return theMap;
	}

	/**
	 * the link and ref walker
	 *
	 * @author prosirai
	 *
	 */
	public class WalkResource extends BaseWalker
	{
		/**
		 * fills this into the factory
		 */
		public WalkResource()
		{
			super(getFactory());
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e
		 * @param trackElem
		 * @return
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			String id = e.getNonEmpty(AttributeName.ID);
			if (id == null)
			{
				id = e.generateDotID(AttributeName.ID, null);
				e.setID(id);
			}
			final VElement vPart = e.getChildElementVector(ElementName.PART, null, null, true, 0, false);

			final String idParent = getParentID(e, vPart);
			theMap.put(id, new IDPart(idParent, vPart));
			return e;
		}

		private String getParentID(final KElement e, final VElement parts)
		{
			String idParent = e.getParentNode_KElement().getAttribute(AttributeName.ID, null, null);
			final KElement part = parts == null ? null : parts.get(0);
			if (part != null && needSplitPart)
			{
				final String productID = StringUtil.getNonEmpty(part.getAttribute(AttributeName.PRODUCTPART));
				if (productID != null)
				{
					idParent += "." + productID;
				}
			}

			return idParent;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			final boolean b = super.matches(toCheck);
			if (!b)
			{
				return false;
			}
			final String localName = toCheck.getLocalName();
			return (localName.equals(XJDFConstants.Resource) || localName.equals("Parameter"));
		}

	}

	/**
	 * the link and ref walker
	 *
	 * @author prosirai
	 *
	 */
	public class WalkSet extends BaseWalker
	{
		/**
		 * fills this into the factory
		 */
		public WalkSet()
		{
			super(getFactory());
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e
		 * @param trackElem
		 * @return
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			String id = e.getNonEmpty(AttributeName.ID);
			if (id == null)
			{
				id = "IDF" + KElement.uniqueID(0);
				e.setID(id);
			}
			final int n = e.numChildElements(XJDFConstants.Resource, null);
			if (n > 1)
			{
				final SetHelper sh = new SetHelper(e);
				final VJDFAttributeMap parts = sh.getPartMapVector();
				parts.removeKey(AttributeName.PRODUCTPART);
				needSplitPart = parts.size() < n;
			}
			else
			{
				needSplitPart = false;
			}

			theMap.put(id, new IDPart(id, null));
			return e;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			final boolean b = super.matches(toCheck);
			if (!b)
			{
				return false;
			}
			return SetHelper.isSet(toCheck);
		}
	}
}
