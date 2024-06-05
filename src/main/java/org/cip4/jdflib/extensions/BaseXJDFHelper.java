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
package org.cip4.jdflib.extensions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.util.ContainerUtil;

/**
 *
 * @author rainer prosi
 * @date Feb 17, 2012
 */
public abstract class BaseXJDFHelper
{

	/**
	 * factory to create a helper from a doc
	 *
	 * @param doc the xmldoc to parse
	 * @return the helper
	 */
	public static BaseXJDFHelper getBaseHelper(final XMLDoc doc)
	{
		if (doc == null)
			return null;
		final KElement root = doc.getRoot();
		return getBaseHelper(root);
	}

	/**
	 * factory to create a helper from an element
	 *
	 * @param root the element to parse if not an XJDF - search in ancestors of element
	 * @return the helper
	 */
	public static BaseXJDFHelper getBaseHelper(final KElement root)
	{
		BaseXJDFHelper h = AuditPoolHelper.getHelper(root);
		if (h == null)
			h = ResourceHelper.getHelper(root);
		if (h == null)
			h = SetHelper.getHelper(root);
		if (h == null)
			h = ProductHelper.getHelper(root);
		if (h == null)
			h = XJDFHelper.getHelper(root);
		if (h == null)
			h = XJMFHelper.getHelper(root);
		return h;
	}

	protected final static Log log = LogFactory.getLog(BaseXJDFHelper.class);
	private static EnumVersion defaultVersion = EnumVersion.Version_2_1;

	/**
	 * @return the defaultVersion
	 */
	public static EnumVersion getDefaultVersion()
	{
		return defaultVersion;
	}

	/**
	 * @param defaultVersion the defaultVersion to set
	 */
	public static void setDefaultVersion(final EnumVersion defaultVersion)
	{
		BaseXJDFHelper.defaultVersion = defaultVersion;
	}

	/**
	 *
	 */
	protected BaseXJDFHelper()
	{
		this(null);
	}

	/**
	 *
	 */
	protected BaseXJDFHelper(final KElement theElement)
	{
		super();
		this.theElement = theElement;
	}

	/**
	 *
	 * @param xpath
	 * @return
	 */
	public String getXPathValue(final String xpath)
	{
		return theElement == null ? null : theElement.getXPathAttribute(xpath, null);
	}

	/**
	 *
	 * @param xpath
	 * @return
	 */
	public KElement getXPathElement(final String xpath)
	{
		return theElement == null ? null : theElement.getXPathElement(xpath);
	}

	/**
	 *
	 * @param attName
	 * @return
	 */
	public String getAttribute(final String attName)
	{
		return theElement == null ? null : theElement.getNonEmpty(attName);
	}

	/**
	 *
	 * @param attName
	 * @return
	 */
	public boolean hasAttribute(final String attName)
	{
		return theElement == null ? false : theElement.hasNonEmpty(attName);
	}

	/**
	 *
	 * @param attrib
	 * @param nameSpaceURI
	 */
	public void removeAttribute(final String attrib, final String nameSpaceURI)
	{
		if (theElement != null)
		{
			theElement.removeAttribute(attrib, nameSpaceURI);
		}
	}

	/**
	 *
	 * @param attName
	 * @param value
	 * @return
	 */
	public void setAttribute(final String attName, final String value)
	{
		if (theElement != null)
		{
			theElement.setNonEmpty(attName, value);
		}
	}

	/**
	 *
	 * @param attName
	 * @param value
	 * @return
	 */
	public void setAttribute(final String attName, final int value)
	{
		setAttribute(attName, Integer.toString(value));
	}

	/**
	 *
	 *
	 * @param xpath
	 * @param value
	 */
	public void setXPathValue(final String xpath, final String value)
	{
		if (theElement != null)
		{
			theElement.setXPathValue(xpath, value);
		}
	}

	/**
	 *
	 * generic cleanup routine
	 */
	public void cleanUp()
	{
		final XJDFCleanupComparator comparator = new XJDFCleanupComparator();
		theElement.sortChildren(comparator, true);
	}

	protected KElement theElement;

	/**
	 *
	 * @return the underlying element
	 */
	public KElement getRoot()
	{
		return theElement;
	}

	/**
	 *
	 * @return the underlying element
	 */
	public JDFDoc getRootDoc()
	{
		return theElement == null ? null : new JDFDoc(theElement.getOwnerDocument());
	}

	/**
	 *
	 * @return the underlying element
	 */
	public BaseXJDFHelper getXRoot()
	{
		return getBaseHelper(theElement);

	}

	/**
	 *
	 * @return the underlying parent XJDF element
	 */
	public XJDFHelper getXJDFRoot()
	{
		return XJDFHelper.getHelper(theElement);
	}

	/**
	 *
	 * @return
	 */
	public KElement deleteNode()
	{
		final KElement ret = theElement;
		if (theElement != null)
		{
			theElement.deleteNode();
			theElement = null;
		}
		return ret;
	}

	/**
	 *
	 * @return
	 */
	public boolean isEqual(final BaseXJDFHelper other)
	{
		if (theElement == null)
			return other == null || other.theElement == null;
		return theElement.isEqual(other.theElement);
	}

	/**
	 * get the ID from the generic Parameter or Resource element
	 *
	 * @return the ID , may be null
	 */
	public String getID()
	{
		return getAttribute(JDFConstants.ID);
	}

	/**
	 * get the ID from the generic Parameter or Resource element
	 *
	 * @return the ID , may never be null
	 */
	public String ensureID()
	{
		return theElement.appendAnchor(null);
	}

	/**
	 *
	 * @param newID
	 */
	public void setID(final String newID)
	{
		setAttribute(AttributeName.ID, newID);
	}

	/**
	 *
	 * @param newID
	 */
	void setExternalID(final String newID)
	{
		setAttribute(XJDFConstants.ExternalID, newID);
	}

	/**
	 *
	 * @param description
	 */
	void setDescriptiveName(final String description)
	{
		setAttribute(AttributeName.DESCRIPTIVENAME, description);
	}

	/**
	 * equals and hash are based on the xml element that this helper represents
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 42;
		result = prime * result + ((theElement == null) ? 0 : theElement.hashCode());
		return result;
	}

	/**
	 * equals and hash are based on the xml element that this helper represents
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BaseXJDFHelper other = (BaseXJDFHelper) obj;
		return ContainerUtil.equals(theElement, other.theElement);
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + ": " + theElement;
	}

	/**
	 * @param elementName
	 * @return
	 * @see org.cip4.jdflib.core.KElement#appendElement(java.lang.String)
	 */
	public KElement appendElement(final String elementName)
	{
		return theElement == null ? null : theElement.appendElement(elementName);
	}

	/**
	 * copy the helper src into this
	 * 
	 * @param src
	 * @return
	 */
	public KElement copyHelper(final BaseXJDFHelper src)
	{
		return theElement == null || src == null ? null : theElement.copyElement(src.getRoot(), null);
	}

	/**
	 * @param nodeName
	 * @return
	 * @see org.cip4.jdflib.core.KElement#getCreateElement(java.lang.String)
	 */
	public KElement getCreateElement(final String nodeName)
	{
		return theElement == null ? null : theElement.getCreateElement(nodeName);
	}

	/**
	 *
	 * @return the local name of the root
	 */
	public String getLocalName()
	{
		return theElement == null ? null : theElement.getLocalName();
	}

	/**
	 *
	 * @param idUsage
	 * @param idValue
	 */
	void setGeneralID(final String idUsage, final String idValue)
	{
		if (theElement instanceof JDFElement)
		{
			((JDFElement) theElement).setGeneralID(idUsage, idValue);
		}
	}

	/**
	 *
	 * @param idUsage
	 * @param idValue
	 */
	public String getGeneralID(final String idUsage)
	{
		if (theElement instanceof JDFElement)
		{
			return ((JDFElement) theElement).getGeneralID(idUsage, 0);
		}
		else
		{
			return null;
		}
	}

	/**
	 *
	 * @return the comment text
	 */
	public String getComment(final int i)
	{
		final KElement root = getRoot();
		final KElement c = root == null ? null : root.getElement(ElementName.COMMENT, null, i);
		return c == null ? null : c.getText();
	}

	/**
	 *
	 * @return the comment
	 */
	public JDFComment setComment(final String text)
	{
		final KElement root = getRoot();
		if (root != null)
		{
			if (text == null)
			{
				root.removeChildren(ElementName.COMMENT, null, null);
			}
			else
			{
				final JDFComment c = (JDFComment) root.getCreateElement(ElementName.COMMENT);
				c.setText(text);
				return c;
			}
		}
		return null;
	}

	/**
	 * @return the productID of the product
	 *
	 */
	public String getExternalID()
	{
		return getAttribute(XJDFConstants.ExternalID);
	}

	/**
	 * @return the descriptive name of the product
	 *
	 */
	public String getDescriptiveName()
	{
		return getAttribute(AttributeName.DESCRIPTIVENAME);
	}

	public JDFAttributeMap getAttributeMap()
	{
		return theElement == null ? null : theElement.getAttributeMap();
	}

	public KElement appendElement(final String elementName, final String nameSpaceURI)
	{
		return theElement == null ? null : theElement.appendElement(elementName, nameSpaceURI);
	}

}
