/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
 *
 */
/**
 *
 * Copyright (c) 2002 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * XMLDocUserData.java
 *
 * Last changes
 *
 * 2002-05-23   Joerg Gonnermann - create the file (port from C++)
 *
 */

package org.cip4.jdflib.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.w3c.dom.Attr;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XMLDocUserData
{
	/**
	 * additional userdata that is attached by applications
	 */
	private Object m_userData;
	private static boolean useIDCache = true;

	/**
	 * vKString vDirtyID the vector of dirty IDs
	 */
	private final VString m_vDirtyID;
	private final DocumentJDFImpl m_Parent;

	/**
	 * map of ID KElement pairs
	 */
	private final HashMap<String, KElement> m_mapTarget;

	private EnumDirtyPolicy dirtyPolicy;

	/**
	 * constructor
	 * @param parent 
	 */
	public XMLDocUserData(DocumentJDFImpl parent)
	{
		m_mapTarget = new HashMap<String, KElement>(); // default is on
		m_vDirtyID = new VString();

		m_userData = null;
		m_Parent = parent;
		setDirtyPolicy(EnumDirtyPolicy.None);
		clearDirtyIDs();
		clearTargets();
	}

	/**
	 * switch on or off the caching method for ids
	 * 
	 * @param bCache if true, the ids will be cached
	 */
	public void setIDCache(boolean bCache)
	{
		useIDCache = bCache;
	}

	/**
	 * get the status of the caching method for ids
	 * 
	 * @return if true, the ids will be cached
	 */
	public boolean getIDCache()
	{
		return useIDCache;
	}

	/**
	 * is target cashing enabled
	 * 
	 * @return true if cashing is enabled
	 */
	public boolean hasTargetCache()
	{
		return m_mapTarget != null;
	}

	/**
	 * Enumeration of various policies
	 */
	@SuppressWarnings("unchecked")
	public static final class EnumDirtyPolicy extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumDirtyPolicy(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * EnumDirtyPolicy
		 * 
		 * @param enumName the name of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null.
		 */
		public static EnumDirtyPolicy getEnum(String enumName)
		{
			return (EnumDirtyPolicy) getEnum(EnumDirtyPolicy.class, enumName);
		}

		/**
		 * EnumDirtyPolicy
		 * 
		 * @param enumValue the value of the enum object to return
		 * @return the enum object if enumName is valid. Otherwise null
		 */
		public static EnumDirtyPolicy getEnum(int enumValue)
		{
			return (EnumDirtyPolicy) getEnum(EnumDirtyPolicy.class, enumValue);
		}

		/**
		 * get a map of all orientation enums
		 * 
		 * @return a map of all orientation enums
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumDirtyPolicy.class);
		}

		/**
		 * get a list of all orientation enums
		 * 
		 * @return a list of all orientation enums
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumDirtyPolicy.class);
		}

		/**
		 * get an iterator over the enum objects
		 * 
		 * @return an iterator over the enum objects
		 */
		public static Iterator iterator()
		{
			return iterator(EnumDirtyPolicy.class);
		}

		/**
		 *  none is default instead of unknown
		 */
		public static final EnumDirtyPolicy None = new EnumDirtyPolicy("None");
		/**
		 * 
		 */
		public static final EnumDirtyPolicy ID = new EnumDirtyPolicy("ID");
		/**
		 * 
		 */
		public static final EnumDirtyPolicy Doc = new EnumDirtyPolicy("Doc");
		/**
		 * 
		 */
		public static final EnumDirtyPolicy XPath = new EnumDirtyPolicy("XPath");
	}

	/**
	 * Set the dirty policy to dirtPol
	 * 
	 * @param dirtPol the dirtyPolicy to set
	 */
	public void setDirtyPolicy(EnumDirtyPolicy dirtPol)
	{
		dirtyPolicy = dirtPol;
		m_Parent.bGlobalDirtyPolicy = EnumDirtyPolicy.None.equals(dirtPol);
	}

	/**
	 * Return the documents user data pointer.<br>
	 * User data allows application programs to attach extra data to JDF Documents and can be set using the function
	 * <code>JDFDoc::SetUserData(p)</code>.
	 * 
	 * @return The user data pointer.
	 */
	public Object getUserData()
	{
		return m_userData;
	}

	/**
	 * Set the user data for a document.<br>
	 * 
	 * User data allows application programs to attach extra data to DOM nodes, and can be retrieved using the function
	 * <code>DOM_Node::getUserData(p)</code>.
	 * <p>
	 * Deletion of the user data remains the responsibility of the application program; it will not be automatically
	 * deleted when the nodes themselves are reclaimed.
	 * 
	 * <p>
	 * Because DOM_Node is not designed to be subclassed, userdata provides an alternative means for extending the
	 * information kept with nodes by an application program.
	 * 
	 * @param objUserData the user data to be kept with the node.
	 */
	public void setUserData(Object objUserData)
	{
		m_userData = objUserData;
	}

	/**
	 * get a vector of all IDs of elements that are dirty
	 * 
	 * @return vKString - the vector of element IDs
	 */
	public VString getDirtyIDs()
	{
		if (dirtyPolicy == EnumDirtyPolicy.ID)
		{
			return m_vDirtyID;
		}

		return null;
	}

	/**
	 * get the vector of dirty XPaths
	 * 
	 * @return VString - vector of dirty XPaths
	 */
	public VString getDirtyXPaths()
	{
		if (dirtyPolicy == EnumDirtyPolicy.XPath)
		{
			return m_vDirtyID;
		}

		return null;
	}

	/**
	 * clear the vector of all IDs of elements that are dirty
	 */
	public void clearDirtyIDs()
	{
		m_Parent.bGlobalDirtyFlag = false;
		if (dirtyPolicy == EnumDirtyPolicy.ID)
		{
			m_vDirtyID.clear();
		}
		if (dirtyPolicy == EnumDirtyPolicy.XPath)
		{
			m_vDirtyID.clear();
		}
	}

	/**
	 * add string id uniquely to the vector of dirty ids
	 * 
	 * @param e the element to be added to the dirty list
	 * @param bAttribute if true, only attributes are dirty, else also sub-elements
	 * @return VString - the vector of element IDs after appending id
	 */
	VString setDirty(KElement e, boolean bAttribute)
	{

		m_Parent.bGlobalDirtyFlag = true;
		if (dirtyPolicy == EnumDirtyPolicy.XPath)
		{
			String x = e.buildXPath(null, 1);

			if (bAttribute)
			{
				x += "/@";
			}

			int i;
			final int size = m_vDirtyID.size();
			for (i = 0; i < size; i++)
			{
				String s = m_vDirtyID.elementAt(i);
				if (s.startsWith(x))
				{
					if (s.equals(x)) // e is already dirty
					{
						return m_vDirtyID;
					}
					m_vDirtyID.remove(i);
					i--;
				}
				else if (x.startsWith(s)) // we have a dirty parent, do
				// nothing
				{
					return m_vDirtyID;
				}
				else if (x.compareTo(s) > 0)// keep sorted
				{
					break;
				}
			}

			m_vDirtyID.insertElementAt(x, i);
		}
		else if (dirtyPolicy == EnumDirtyPolicy.ID)
		{
			m_vDirtyID.appendUnique(e.getInheritedAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING));
		}
		return m_vDirtyID;
	}

	/**
	 * checks if <code>element</code> is dirty
	 * 
	 * @param element element to check
	 * @return true, if <code>element</code> is dirty
	 */
	public boolean isDirty(KElement element)
	{
		if (element == null)
			return false;
		if (dirtyPolicy == EnumDirtyPolicy.Doc)
		{
			return m_Parent.bGlobalDirtyFlag;
		}
		else if (dirtyPolicy == EnumDirtyPolicy.ID)
		{
			String id = element.getInheritedAttribute("ID", null, null);
			return isDirty(id);
		}
		else if (dirtyPolicy == EnumDirtyPolicy.XPath)
		{
			String xPath = element.buildXPath(null, 1);
			return isDirty(xPath);
		}
		return false;
	}

	/**
	 * checks wheter the node with <code>strID</code> is dirty
	 * 
	 * @param strID the id of the node to be checked
	 * @return bool true if the node with ID=<code>strID</code> is dirty
	 */
	public boolean isDirty(String strID)
	{
		if (dirtyPolicy == EnumDirtyPolicy.ID)
		{
			if (strID == null)
				return m_vDirtyID.size() > 0;
			return m_vDirtyID.contains(strID); // was in C++ .hasString(id);
		}
		else if (dirtyPolicy == EnumDirtyPolicy.XPath)
		{
			final int size = m_vDirtyID.size();
			if (strID == null)
				return size > 0;

			for (int i = 0; i < size; i++)
			{
				final String s = m_vDirtyID.elementAt(i);
				if (strID.startsWith(s)) // we have a dirty parent, do nothing
				{
					return true;
				}
				else if (strID.compareTo(s) > 0)// sorted
				{
					break;
				}
			}
			return false;
		}
		return m_Parent.bGlobalDirtyFlag;

	}

	/**
	 * Set the target to target
	 * 
	 * @param targetElement the target element
	 * @param id
	 */
	public void setTarget(KElement targetElement, String id)
	{
		String idLocal = id;

		if (!useIDCache || m_mapTarget == null)
			return;

		if (idLocal == null)
			idLocal = targetElement.getAttribute(AttributeName.ID, null, null);

		if (idLocal != null)
		{
			m_mapTarget.put(idLocal, targetElement); // put the correct in
		}
	}

	/**
	 * remove the KElement from the target list
	 * 
	 * @param targetElement the element to remove
	 */
	public void removeTarget(KElement targetElement)
	{
		if (!useIDCache || m_mapTarget == null)
			return;

		final String id = targetElement.getAttribute("ID", null, null);
		if (id != null)
		{
			final KElement kelem = m_mapTarget.get(id);
			if (kelem != null)
			{ // element with key of id was found, so delete it
				m_mapTarget.remove(id);
			}
		}
	}

	/**
	 * remove the target id from the target list
	 * 
	 * @param id the target element id
	 */
	public void removeTarget(String id)
	{
		if (useIDCache && m_mapTarget != null)
			m_mapTarget.remove(id);
	}

	/**
	 * Get the target with ID=<code>strID</code>
	 * 
	 * @param strID the id of the target to search
	 * @return KElement target the target element
	 */
	public KElement getTarget(String strID)
	{
		// m_mapTarget=null; // uncomment this to ensure that the cache is off
		if (useIDCache && m_mapTarget != null && strID != null)
		{

			final KElement elem = m_mapTarget.get(strID);
			if (elem != null)
			{
				Attr a = elem.getAttributeNode(AttributeName.ID);
				if (a != null && strID.equals(a.getValue()))
				{
					return elem;
				}
				m_mapTarget.remove(strID);
				// oops this guy is inconsistent - zapp it
			}
		}
		return null;
	}

	/**
	 * clear the map of all targets
	 */
	public void clearTargets()
	{
		if (useIDCache && m_mapTarget != null)
			m_mapTarget.clear();
	}

	/**
	 * @return
	 */
	public Object getDirtyPolicy()
	{
		return dirtyPolicy;
	}

}