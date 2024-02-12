/*
 * The CIP4 Software License, Version 1.0
 *
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
package org.cip4.jdflib.elementwalker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.process.JDFComChannel;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.span.JDFSpanBase;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG<br/>
 *         removes any empty or unlinked resources
 *
 */
public class RemoveEmpty extends BaseElementWalker
{
	boolean zappElements;
	final JDFPart part;
	final Collection<String> ignoreElements;
	LinkRefFinder rf;

	/**
	 *
	 */
	public RemoveEmpty()
	{
		super(new BaseWalkerFactory());
		ignoreElements = new HashSet<>();
		addIgnoreElement(ElementName.POSITION);
		zappElements = true;
		part = (JDFPart) new JDFDoc(ElementName.PART).getRoot();
		part.appendElement("foo");
		new BaseWalker(getFactory()); // need a default walker
		rf = null;
	}

	public void addIgnoreElement(final String name)
	{
		ignoreElements.add(name);
	}

	/**
	 *
	 * remove all unlinked crap and empty string attributes
	 *
	 * @param n
	 */
	public void removEmpty(final JDFNode n)
	{
		final UnLinkFinder unLinkFinder = new UnLinkFinder();
		unLinkFinder.eraseUnlinked(n);
		if (zappElements)
		{
			rf = new LinkRefFinder(true, true);
			rf.getMap(n);
		}

		int last = removEmptyElement(n);
		if (last > 0)
			last = unLinkFinder.eraseUnlinked(n);
		if (last > 0)
			removEmptyElement(n);
	}

	/**
	 *
	 *
	 * @param e
	 */
	public int removEmptyElement(final KElement e)
	{
		return walkTreeKidsFirst(e);
	}

	/**
	 *
	 * remove all empty string attributes
	 *
	 * @param e
	 */
	public void removEmptyAttributes(final KElement e)
	{
		final boolean keep = zappElements;
		zappElements = false;
		walkTreeKidsFirst(e);
		zappElements = keep;
	}

	/**
	 * the resource walker note the naming convention Walkxxx so that it is automagically instantiated by the super classes
	 *
	 * @author prosirai
	 *
	 */
	public class WalkElement extends BaseWalker
	{

		/**
		 * fills this into the factory
		 */
		public WalkElement()
		{
			super(getFactory());
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e1 - the element to track
		 * @param trackElem - always null
		 * @return the element to continue walking
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final boolean hasreq = zappElements ? hasRequiredChild(e1) : true;
			final boolean b = walkAttributes(e1);
			final boolean hasAny = hasreq && (b || hasChild(e1));
			if (!hasAny)
			{
				e1.deleteNode();
				return null;
			}
			else
			{
				return e1;
			}
		}

		/**
		 *
		 * @param e1
		 * @return
		 */
		protected boolean hasChild(final KElement e1)
		{
			return e1.getFirstChildElement() != null || e1.getText() != null || e1.getXMLComment(0) != null;
		}

		/*
		 * Ü
		 *
		 */
		protected boolean hasRequiredChild(final KElement e1)
		{
			return true;
		}

		/**
		 *
		 * @param e1
		 * @return true if something good was inside
		 */
		protected boolean walkAttributes(final KElement e1)
		{
			final JDFAttributeMap map = e1.getAttributeMap_KElement();
			final Set<String> allKeys = map.keySet();
			boolean hasGood = !zappElements;
			final StringArray dummy = zappElements ? getDummyAttributes() : null;
			for (final String key : allKeys)
			{
				if (StringUtil.isEmpty(map.get(key)))
				{
					e1.removeAttribute(key);
				}
				else if (!hasGood && !dummy.contains(key))
				{
					hasGood = true;
				}
			}
			return hasGood;
		}

		/**
		 *
		 * @return
		 */
		protected StringArray getDummyAttributes()
		{
			final StringArray dummy = new StringArray();
			dummy.add(AttributeName.ID);
			dummy.add(AttributeName.AGENTNAME);
			dummy.add(AttributeName.AGENTVERSION);
			return dummy;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return true;
		}
	}

	/**
	 * zapp me
	 *
	 * @author prosirai
	 *
	 */
	public class WalkGeneralID extends WalkElement
	{

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e - the element to track
		 * @param trackElem - always null
		 * @return the element to continue walking
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final String idUsage = ((JDFGeneralID) e).getIDUsage();
			final String idValue = ((JDFGeneralID) e).getIDValue();
			if (StringUtil.getNonEmpty(idUsage) == null || StringUtil.getNonEmpty(idValue) == null)
			{
				e.deleteNode();
				return null;
			}
			else
			{
				return super.walk(e, trackElem);
			}
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFGeneralID;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return VString.getVString(ElementName.GENERALID, null);
		}

	}

	/**
	 * zapp me
	 *
	 * @author prosirai
	 *
	 */
	public class WalkComment extends WalkElement
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e - the element to track
		 * @param trackElem - always null
		 * @return the element to continue walking
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final String text = StringUtil.getNonEmpty(e.getText());
			if (text == null)
			{
				e.deleteNode();
				return null;
			}
			else
			{
				return super.walk(e, trackElem);
			}
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFComment;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return VString.getVString(ElementName.COMMENT, null);
		}
	}

	/**
	 * zapp me
	 *
	 * @author prosirai
	 *
	 */
	public class WalkSpan extends WalkElement
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e1 - the element to track
		 * @param trackElem - always null
		 * @return the element to continue walking
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final boolean hasAny = walkAttributes(e1);
			if (!hasAny)
			{
				e1.deleteNode();
				return null;
			}
			else
			{
				return e1;
			}
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFSpanBase;
		}

		@Override
		protected StringArray getDummyAttributes()
		{
			final StringArray dummyAttributes = super.getDummyAttributes();
			dummyAttributes.add(AttributeName.DATATYPE);
			dummyAttributes.add(AttributeName.PRIORITY);
			return dummyAttributes;
		}

	}

	/**
	 * zapp me
	 *
	 * @author prosirai
	 *
	 */
	public class WalkResourceAudit extends WalkElement
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e - the element to track
		 * @param trackElem - always null
		 * @return the element to continue walking
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFResourceAudit ra = (JDFResourceAudit) e;
			if (ra.getNewLink() == null)
			{
				ra.deleteNode();
				return null;
			}
			else
			{
				return super.walk(e, trackElem);
			}
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return VString.getVString(ElementName.RESOURCEAUDIT, null);
		}
	}

	/**
	 * never zapp me
	 *
	 *
	 *
	 */
	public class WalkIgnore extends WalkElement
	{
		public WalkIgnore()
		{
			super();
			depth += 3;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e - the element to track
		 * @param trackElem - always null
		 * @return the element to continue walking
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
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
			return ignoreElements.contains(toCheck.getLocalName());
		}

	}

	/**
	 * zapp me
	 *
	 * @author prosirai
	 *
	 */
	public class WalkResource extends WalkElement
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.RemoveEmpty.WalkElement#getDummyAttributes()
		 */
		@Override
		protected StringArray getDummyAttributes()
		{
			final StringArray v = super.getDummyAttributes();
			v.add(AttributeName.CLASS);
			v.add(AttributeName.STATUS);
			v.add(AttributeName.PARTIDKEYS);
			v.add(AttributeName.PARTUSAGE);
			final VString partAttribs = part.knownAttributes();
			final JDFElement foo = (JDFElement) part.getElement("foo");
			final VString fooAttribs = foo.knownAttributes();
			partAttribs.removeStrings(fooAttribs, 0);

			v.appendUnique(partAttribs);
			return v;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFResource;
		}

		/**
		 *
		 * @see org.cip4.jdflib.elementwalker.RemoveEmpty.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			boolean hasGood = false;
			if (zappElements)
			{
				hasGood = walkAttributes(e1);
				final JDFResource r = (JDFResource) e1;
				if (!hasGood)
				{
					hasGood = checkRef(r);
				}
				if (!hasGood)
				{
					hasGood = checkLinks(r);
				}
			}
			return hasGood ? e1 : super.walk(e1, trackElem);
		}

		/**
		 *
		 * @param r
		 * @return
		 */
		boolean checkLinks(final JDFResource r)
		{
			boolean hasGood = false;
			final Collection<JDFResourceLink> links = getLinks(r);
			boolean hasIn = false;
			boolean hasOut = false;
			if (links != null)
			{
				for (final JDFResourceLink rl : links)
				{
					final EnumUsage usage = rl.getUsage();
					if (EnumUsage.Input.equals(usage))
					{
						hasIn = true;
					}
					else if (EnumUsage.Output.equals(usage))
					{
						hasOut = true;
					}
					removEmptyElement(rl);
					if (rl.getAmountPool() != null || rl.getPart(0) != null)
					{
						hasGood = true;
						break;
					}
					final JDFAttributeMap map = rl.getAttributeMap();
					map.removeKeys(super.getDummyAttributes());
					map.remove(AttributeName.RREF);
					map.remove(AttributeName.USAGE);
					if (hasIn && hasOut || !map.isEmpty())
					{
						hasGood = true;
						break;
					}
				}
			}
			return hasGood;
		}

		boolean checkRef(final JDFResource r)
		{
			boolean hasGood = false;
			final Collection<JDFRefElement> v = getRefs(r);
			if (v != null)
			{
				final JDFAttributeMap myMap = r.getPartMap();
				for (final JDFRefElement e : v)
				{
					final JDFAttributeMap m = e.getPartMap();
					if (myMap.overlapMap(m))
					{
						hasGood = true;
						break;
					}
				}
			}
			return hasGood;
		}

		/**
		 *
		 * @param r
		 * @return
		 */
		Collection<JDFRefElement> getRefs(final JDFResource r)
		{
			final List<KElement> v = rf == null ? null : rf.getTheMap().get(r.getID());
			if (v != null)
			{
				final ArrayList<JDFRefElement> vr = new ArrayList<>();
				for (final KElement e : v)
				{
					if (e instanceof JDFRefElement)
					{
						vr.add((JDFRefElement) e);
					}
				}
				return vr.isEmpty() ? null : vr;
			}
			return null;
		}

		/**
		 *
		 * @param r
		 * @return
		 */
		Collection<JDFResourceLink> getLinks(final JDFResource r)
		{
			final List<KElement> v = rf == null ? null : rf.getTheMap().get(r.getID());
			if (v != null)
			{
				final ArrayList<JDFResourceLink> vr = new ArrayList<>();
				for (final KElement e : v)
				{
					if (e instanceof JDFResourceLink)
					{
						vr.add((JDFResourceLink) e);
					}
				}
				return vr.isEmpty() ? null : vr;
			}
			return null;
		}
	}

	/**
	 * zapp me
	 *
	 * @author prosirai
	 *
	 */
	public class WalkComChannel extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.RemoveEmpty.WalkElement#getDummyAttributes()
		 */
		@Override
		protected StringArray getDummyAttributes()
		{
			final StringArray v = super.getDummyAttributes();
			// if only channeltype is specified, we have an empty dummy
			v.add(AttributeName.CHANNELTYPE);
			return v;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFComChannel;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
		 */
		@Override
		public VString getElementNames()
		{
			return VString.getVString(ElementName.COMCHANNEL, null);
		}
	}

	/**
	 * zapp me
	 *
	 * @author prosirai
	 *
	 */
	public class WalkMessage extends WalkElement
	{

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			final String localName = toCheck.getLocalName();
			return localName.startsWith(ElementName.RESPONSE) || localName.startsWith(ElementName.QUERY) || localName.startsWith(ElementName.COMMAND);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.RemoveEmpty.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			// always keep all
			return e1;
		}
	}

	/**
	 *
	 * if set to true, attributes with proprietary namespace prefixes are zapped, else kept
	 *
	 * @param zappElements if true, zapp 'em (the default) else keep 'em
	 */
	public void setZappElements(final boolean zappElements)
	{
		this.zappElements = zappElements;
	}

}
