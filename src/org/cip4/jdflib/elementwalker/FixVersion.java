/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of
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
/**
 * 
 */
package org.cip4.jdflib.elementwalker;

import java.util.Iterator;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.StringUtils;
import org.cip4.jdflib.auto.JDFAutoApprovalDetails.EnumApprovalState;
import org.cip4.jdflib.auto.JDFAutoColor.EnumMappingSelection;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.JDFElement.EnumSettingsPolicy;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFPool;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFLayoutPreparationParams;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFTool;
import org.cip4.jdflib.resource.JDFLayoutPreparationParams.StrippingConverter;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.devicecapability.JDFAbstractState;
import org.cip4.jdflib.resource.process.JDFApprovalDetails;
import org.cip4.jdflib.resource.process.JDFApprovalSuccess;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.span.JDFSpanBase;
import org.cip4.jdflib.span.JDFSpanBase.EnumPriority;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG<br/>
 * fixes versions within JDF 1.x June 7, 2009<br/>
 * uses heuristics to modify this element and its children to be compatible with a given version<br>
 * in general, it will be able to move from low to high versions, but potentially fail when attempting to move from higher to lower versions
 * 
 * This class is the result of refactoring the recursive fixVersion routines from the dom node tree into one class
 * 
 */
public class FixVersion extends BaseElementWalker
{
	protected boolean bFixIDs;
	protected final EnumVersion version;
	protected boolean bOK;
	protected boolean fixICSVersions;
	protected boolean bLayoutPrepToStripping;

	/**
	 * @return the fixICSVersions
	 */
	public boolean isFixICSVersions()
	{
		return fixICSVersions;
	}

	/**
	 * @param pFixICSVersions the fixICSVersions to set
	 */
	public void setFixICSVersions(final boolean pFixICSVersions)
	{
		this.fixICSVersions = pFixICSVersions;
	}

	/**
	 * @return the bFixVersionIDFix if true then invalid, i.e. numeric ID, IDREF and IDREFS are prefixed with an '_' when calling fixVersion
	 */
	public boolean isFixVersionIDFix()
	{
		return bFixIDs;
	}

	/**
	 * any problems on the way?
	 * @return the bOK
	 */
	public boolean isOK()
	{
		return bOK;
	}

	/**
	 * @param fixVersionIDFix the bFixVersionIDFix to set if true then invalid, i.e. numeric ID, IDREF and IDREFS are prefixed with an '_' when calling
	 * fixVersion
	 */
	public void setFixVersionIDFix(final boolean fixVersionIDFix)
	{
		bFixIDs = fixVersionIDFix;
	}

	/**
	 * @param _version
	 * 
	 */
	public FixVersion(final EnumVersion _version)
	{
		super(new BaseWalkerFactory());
		new BaseWalker(getFactory()); // need a default walker
		bFixIDs = true;
		version = _version;
		bOK = true;
		fixICSVersions = false;
		bLayoutPrepToStripping = false;
	}

	/**
	 * @param fixVersion
	 */
	public FixVersion(final FixVersion fixVersion)
	{
		this(fixVersion.version);
		bFixIDs = fixVersion.bFixIDs;
		fixICSVersions = fixVersion.fixICSVersions;
		bLayoutPrepToStripping = fixVersion.bLayoutPrepToStripping;
	}

	@Override
	protected BaseWalkerFactory getFactory()
	{
		return (BaseWalkerFactory) theFactory;
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
			final JDFElement el = (JDFElement) e1;
			// fix any spurious ns typos
			if (!JDFConstants.JDFNAMESPACE.equals(el.getNamespaceURI()))
			{
				el.setNamespaceURI(JDFConstants.JDFNAMESPACE);
			}

			// replace all "~" with " ~ "
			final JDFAttributeMap m = el.getAttributeMap();
			final Iterator<String> it = m.getKeyIterator();
			final AttributeInfo ai = el.getAttributeInfo();
			while (it.hasNext())
			{
				final String key = it.next();
				final String value = m.get(key);
				final EnumAttributeType attType = ai.getAttributeType(key);

				if (EnumAttributeType.isRange(attType))
				{
					fixRange(el, key, value);
				}
				else if (EnumAttributeType.duration.equals(attType))
				{
					fixDuration(el, key, value);
				}
				if (bFixIDs && value.length() > 0 && StringUtils.isNumeric(value.substring(0, 1)))
				{
					fixIDs(el, ai, key, value);
				}
				if (AttributeName.ICSVERSIONS.equals(key))
				{
					fixICSVesions(el, value);
				}
			}
			return el;
		}

		/**
		 * @param el
		 * @param value
		 */
		private void fixICSVesions(final JDFElement el, final String value)
		{
			if (!fixICSVersions)
			{
				return;
			}
			final VString v = StringUtil.tokenize(value, null, false);
			if (v == null)
			{
				return;
			}
			final int minor = version.getMinorVersion();
			for (int i = 0; i < v.size(); i++)
			{
				String icsToken = v.get(i);
				if (".".equals(StringUtil.substring(icsToken, -2, -1)) && StringUtil.isInteger(StringUtil.rightStr(icsToken, 1)))
				{
					icsToken = StringUtil.leftStr(icsToken, -1) + minor;
					v.set(i, icsToken);
				}
			}
			el.setAttribute(AttributeName.ICSVERSIONS, v, null);
		}

		/**
		 * @param el
		 * @param key
		 * @param value
		 */
		private void fixRange(final JDFElement el, final String key, final String value)
		{
			try
			{
				final JDFNameRangeList nrl = new JDFNameRangeList(value);
				el.setAttribute(key, nrl, null);
			}
			catch (final JDFException e)
			{
				// do nothing
			}
			catch (final DataFormatException e)
			{
				// do nothing
			}
		}

		/**
		 * @param el
		 * @param key
		 * @param value
		 */
		private void fixDuration(final JDFElement el, final String key, final String value)
		{
			try
			{
				el.setAttribute(key, new JDFDuration(value).getDurationISO());
			}
			catch (final DataFormatException ex)
			{
				// nop - continue
			}
		}

		/**
		 * @param el
		 * @param ai
		 * @param key
		 * @param value
		 */
		private void fixIDs(final JDFElement el, final AttributeInfo ai, final String key, String value)
		{
			final EnumAttributeType atType = ai.getAttributeType(key);
			if (atType != null)
			{
				if (atType.equals(EnumAttributeType.ID) || atType.equals(EnumAttributeType.IDREF))
				{
					value = "_" + value;
					el.setAttribute(key, value);
				}
				else if (atType.equals(EnumAttributeType.IDREFS))
				{
					final VString vvalues = new VString(value, " ");
					for (int i = 0; i < vvalues.size(); i++)
					{
						String s = vvalues.stringAt(i);
						if (s.length() > 0 && StringUtils.isNumeric(s.substring(0, 1)))
						{
							s = "_" + s;
							vvalues.setElementAt(s, i);
						}
					}
					el.setAttribute(key, vvalues, null);
				}
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
			final boolean b = super.matches(toCheck);
			if (!b)
			{
				return false;
			}
			return (toCheck instanceof JDFElement);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkResLink extends WalkElement
	{
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
			return (toCheck instanceof JDFResourceLink);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFResourceLink rl = (JDFResourceLink) e1;
			if (version != null)
			{
				if (version.getValue() >= EnumVersion.Version_1_3.getValue())
				{
					if (rl.hasAttribute(AttributeName.DRAFTOK))
					{
						if (!rl.hasAttribute(AttributeName.MINSTATUS))
						{
							rl.setMinStatus(EnumResStatus.Draft);
						}
						rl.removeAttribute(AttributeName.DRAFTOK);
					}
				}
				else
				{
					if (rl.hasAttribute(AttributeName.MINSTATUS))
					{
						if (!rl.hasAttribute(AttributeName.DRAFTOK))
						{
							rl.setDraftOK(true);
						}
						rl.removeAttribute(AttributeName.MINSTATUS);
					}
					rl.removeAttribute(AttributeName.MINLATESTATUS);
				}
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkComment extends WalkElement
	{
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
			return (toCheck instanceof JDFComment);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFComment c = (JDFComment) e1;
			if (version != null)
			{
				if (version.getValue() >= EnumVersion.Version_1_3.getValue() && c.getLocalName().equals(ElementName.COMMENT))
				{
					c.appendAnchor(null);
				}
				else
				{
					c.removeAttribute(AttributeName.ID);
					c.removeAttribute(AttributeName.AGENTNAME);
					c.removeAttribute(AttributeName.AGENTVERSION);
					c.removeAttribute(AttributeName.AUTHOR);
					c.removeAttribute(AttributeName.TIMESTAMP);
				}
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkAudit extends WalkElement
	{
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
			return (toCheck instanceof JDFAudit);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFAudit audit = (JDFAudit) e1;
			if (version != null)
			{
				final int value = version.getValue();
				fixID(audit, value);
				final String author = audit.getAuthor();
				if (value <= EnumVersion.Version_1_1.getValue())
				{
					mergeAuthor(audit, author);
				}
				else if (author.length() > 0) // version>=1.2 and has author
				{
					splitAuthor(audit, author);
				}
				authorToEmployee(audit, value);
			}
			return super.walk(e1, trackElem);
		}

		/**
		 * @param audit
		 * @param value
		 */
		private void fixID(final JDFAudit audit, final int value)
		{
			if (value >= EnumVersion.Version_1_3.getValue())
			{
				audit.appendAnchor(null);
			}
			else
			{
				audit.removeAttribute(AttributeName.ID);
			}
		}

		/**
		 * @param audit
		 * @param author
		 */
		private void mergeAuthor(final JDFAudit audit, String author)
		{
			String tmp = audit.getAgentName();
			boolean b = false;
			if (tmp.length() != 0)
			{
				author += "_|_" + tmp;
				b = true;
			}
			tmp = audit.getAgentVersion();
			if (tmp.length() != 0)
			{
				if (!b)
				{
					author += "_|_ ";
				}

				author += "_|_" + tmp;
				b = true;
			}
			audit.removeAttribute(AttributeName.AGENTNAME);
			audit.removeAttribute(AttributeName.AGENTVERSION);
			if (b)
			{
				audit.setAuthor(author);
			}
		}

		/**
		 * @param audit
		 * @param author
		 */
		private void splitAuthor(final JDFAudit audit, final String author)
		{
			final VString tokens = StringUtil.tokenize(author, "_|_", false);
			if (tokens.size() == 3)
			{ // it was previously fixed
				String tmp = tokens.stringAt(0);
				if (!tmp.equals(JDFConstants.EMPTYSTRING) && !tmp.equals(JDFConstants.BLANK))
				{
					audit.setAuthor(tmp);
				}
				tmp = tokens.stringAt(1);
				if (!tmp.equals(JDFConstants.EMPTYSTRING) && !tmp.equals(JDFConstants.BLANK))
				{
					audit.setAgentName(tmp);
				}
				tmp = tokens.stringAt(2);
				if (!tmp.equals(JDFConstants.EMPTYSTRING) && !tmp.equals(JDFConstants.BLANK))
				{
					audit.setAgentVersion(tmp);
				}
			}
		}

		/**
		 * @param audit
		 * @param value
		 */
		private void authorToEmployee(final JDFAudit audit, final int value)
		{
			if (value >= EnumVersion.Version_1_4.getValue())
			{
				final String finalAuthor = StringUtil.getNonEmpty(audit.getAuthor());
				if (finalAuthor != null && !audit.hasChildElement(ElementName.EMPLOYEE, null))
				{
					audit.appendEmployee().setDescriptiveName(finalAuthor);
				}
				audit.removeAttribute(AttributeName.AUTHOR);
			}
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkJMF extends WalkElement
	{
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
			return (toCheck instanceof JDFJMF);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFJMF jmf = (JDFJMF) e1;
			if (version != null)
			{
				jmf.setVersion(version);
				jmf.setMaxVersion(version);
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkJMFMessage extends WalkElement
	{
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
			return (toCheck instanceof JDFMessage);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFMessage jmf = (JDFMessage) e1;
			if (jmf.hasAttribute(AttributeName.TYPE) && !jmf.hasAttribute(AttributeName.XSITYPE, AttributeName.XSIURI, false))
			{
				jmf.setAttribute(AttributeName.XSITYPE, jmf.getLocalName() + jmf.getType(), AttributeName.XSIURI);
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkAncestor extends WalkElement
	{
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
			return (toCheck instanceof JDFAncestor);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFAncestor a = (JDFAncestor) e1;
			if (version != null)
			{
				a.setVersion(version);
				a.setMaxVersion(version);
				if (version.getValue() < EnumVersion.Version_1_3.getValue())
				{
					a.inlineRefElements(null, null, true);
				}
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkJDF extends WalkElement
	{
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
			return (toCheck instanceof JDFNode);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFNode n = (JDFNode) e1;
			if (version != null)
			{
				n.setVersion(version);
				n.setMaxVersion(version);
				n.fixNiCi(version);
			}
			if (!n.hasAttribute(AttributeName.JOBPARTID))
			{
				n.setJobPartID(n.generateDotID(AttributeName.JOBPARTID, null));
			}

			if (n.isJDFRoot() && !n.hasAncestorAttribute(AttributeName.JOBID, null))
			{
				n.setJobID(n.generateDotID(AttributeName.JOBID, null));
			}
			final EnumType enumType = n.getEnumType();
			if (enumType != null)
			{
				n.setType(enumType); // fixes xsi:type stuff
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkPool extends WalkElement
	{
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
			return (toCheck instanceof JDFPool);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFPool p = (JDFPool) e1;
			if (p.hasAttribute(AttributeName.RREFS))
			{
				p.removeAttribute(AttributeName.RREFS);
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkResource extends WalkElement
	{
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
			return (toCheck instanceof JDFResource);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFResource r = (JDFResource) e1;
			if (!r.isResourceRootRoot())
			{
				// removeAttribute(AttributeName.STATUS);
				if (!r.isResourceElement())
				{
					r.removeAttribute(AttributeName.CLASS);
				}
			}
			else
			{
				if (r.hasAttribute(AttributeName.RREFS))
				{
					r.removeAttribute(AttributeName.RREFS);
				}

				r.init();
			}
			if (version != null)
			{
				if (!r.isResourceRootRoot())
				{
					if (version.getValue() >= EnumVersion.Version_1_2.getValue())
					{
						r.removeAttribute(AttributeName.ID);
					}
				}
				else
				{
					if (version.getValue() <= EnumVersion.Version_1_2.getValue())
					{
						if (r.getPartUsage() == EnumPartUsage.Sparse)
						{
							r.setPartUsage(EnumPartUsage.Implicit);
						}
					}
				}
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkState extends WalkElement
	{
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
			return (toCheck instanceof JDFAbstractState);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFAbstractState a = (JDFAbstractState) e1;
			if (JDFConstants.UNBOUNDED.equals(a.getAttribute(AttributeName.MAXOCCURS, null, null)))
			{
				a.setAttribute(AttributeName.MAXOCCURS, JDFConstants.POSINF);
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkDevice extends WalkResource
	{
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
			return (toCheck instanceof JDFDevice);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFDevice d = (JDFDevice) e1;
			if (!d.hasAttribute(AttributeName.DESCRIPTIVENAME))
			{
				d.setDescriptiveName(StringUtil.getNonEmpty(d.getFriendlyName()));
			}
			if (EnumUtil.aLessThanB(EnumVersion.Version_1_3, version))
			{
				d.removeAttribute(AttributeName.FRIENDLYNAME);
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkTool extends WalkResource
	{
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
			return (toCheck instanceof JDFTool);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFTool t = (JDFTool) e1;
			if (version != null && version.getValue() >= EnumVersion.Version_1_3.getValue())
			{
				if (t.hasAttribute(AttributeName.TOOLID) && !t.hasAttribute(AttributeName.PRODUCTID))
				{
					t.setProductID(t.getToolID());
				}
				t.removeAttribute(AttributeName.TOOLID, null);
			}
			else if (version != null && version.getValue() < EnumVersion.Version_1_3.getValue())
			{
				if (t.hasAttribute(AttributeName.PRODUCTID) && !t.hasAttribute(AttributeName.TOOLID))
				{
					t.setToolID(t.getProductID());
				}
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkComponent extends WalkResource
	{
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
			return (toCheck instanceof JDFComponent);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFComponent c = (JDFComponent) e1;
			if (version != null)
			{
				if (version.getValue() >= EnumVersion.Version_1_4.getValue())
				{
					c.removeAttribute(AttributeName.ISWASTE);
				}
				if (version.getValue() >= EnumVersion.Version_1_3.getValue())
				{
					fixFromSourceSheet(c);
				}
				else
				{
					fixToSourceSheet(c);
				}
			}
			return super.walk(e1, trackElem);
		}

		/**
		 * @param c
		 */
		private void fixToSourceSheet(final JDFComponent c)
		{
			final JDFLayout layout = c.getLayout();
			if (layout != null)
			{
				final String sourcesheet = layout.getSheetName();
				c.setSourceSheet(sourcesheet);
				final JDFRefElement layoutRef = (JDFRefElement) c.getElement_KElement("LayoutRef", null, 0);
				// JDF 1.2 layout should be unpartitioned
				if (layoutRef != null)
				{
					// JDF 1.2 layout should be unpartitioned
					layoutRef.removeChild(ElementName.PART, null, 0);
				}
			}
		}

		/**
		 * @param c
		 */
		private void fixFromSourceSheet(final JDFComponent c)
		{
			if (c.hasAttribute(AttributeName.SOURCESHEET))
			{
				final String sourceSheet = c.getSourceSheet();

				final JDFRefElement layoutRef = (JDFRefElement) c.getElement_KElement("LayoutRef", null, 0);
				if (layoutRef != null)
				{
					JDFLayout lo = (JDFLayout) layoutRef.getLinkRoot(layoutRef.getrRef());
					if (lo != null)
					{
						lo.fixVersion(version);
					}

					layoutRef.setPartMap(new JDFAttributeMap(AttributeName.SHEETNAME, sourceSheet));
					lo = (JDFLayout) layoutRef.getTarget();
					layoutRef.setPartMap(lo.getPartMap());
				}
				c.removeAttribute(AttributeName.SOURCESHEET);
			}
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG * fixes an ApprovalSuccess by moving the appropriate elements in and out of ApprovalDetails
	 * elements June 7, 2009
	 */
	public class WalkApprovalSuccess extends WalkResource
	{
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
			return (toCheck instanceof JDFApprovalSuccess);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFApprovalSuccess as = (JDFApprovalSuccess) e1;
			if (version != null)
			{
				// create ApprovalDetails
				if (version.getValue() >= EnumVersion.Version_1_3.getValue())
				{
					if (as.hasChildElement(ElementName.CONTACT, null) || as.hasChildElement(ElementName.FILESPEC, null))
					{
						final JDFApprovalDetails ad = as.appendApprovalDetails();
						ad.setApprovalState(EnumApprovalState.Approved);
						ad.moveElement(as.getContact(), null);
						ad.moveElement(as.getFileSpec(), null);
					}
				}
				else
				{ // remove ApprovalDetails
					for (int i = 0; i < 99999; i++)
					{
						final JDFApprovalDetails ad = as.getApprovalDetails(i);
						int iAccept = 0;
						if (ad == null)
						{
							if (iAccept == 0)
							{
								as.setResStatus(EnumResStatus.Rejected, false);
							}
							break; // none left
						}

						if (ad.getApprovalState() == EnumApprovalState.Rejected)
						{
							// semantics of reject are not available in 1.2 and below ignore
							bOK = false;
							ad.deleteNode();
							continue;
						}
						iAccept++;
						as.moveElement(ad.getContact(), null);
						as.moveElement(ad.getFileSpec(), null);
						ad.deleteNode();
					}
				}
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkColor extends WalkResource
	{
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
			return (toCheck instanceof JDFColor);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFColor c = (JDFColor) e1;
			if (c.hasAttribute(AttributeName.USEPDLALTERNATECS))
			{
				if (!c.hasAttribute(AttributeName.MAPPINGSELECTION))
				{
					c.setMappingSelection(c.getUsePDLAlternateCS() ? EnumMappingSelection.UsePDLValues : EnumMappingSelection.UseProcessColorValues);
				}
				c.removeAttribute(AttributeName.USEPDLALTERNATECS);
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkEmployee extends WalkResource
	{
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
			return (toCheck instanceof JDFEmployee);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFEmployee e = (JDFEmployee) e1;
			if (!e.hasAttribute(AttributeName.PRODUCTID))
			{
				e.setProductID(StringUtil.getNonEmpty(e.getPersonalID()));
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkLayout extends WalkResource
	{
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
			return (toCheck instanceof JDFLayout);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFLayout l = (JDFLayout) e1;
			if (l.isResourceRoot() && version != null)
			{
				final int vers = version.getValue();
				final boolean newLayout = JDFLayout.isNewLayout(l);
				if (vers >= EnumVersion.Version_1_3.getValue() && !newLayout)
				{
					bOK = l.toNewLayout() && bOK;
				}
				else if (vers < EnumVersion.Version_1_3.getValue() && newLayout)
				{
					bOK = l.fromNewLayout() && bOK;
				}
				if (vers >= EnumVersion.Version_1_4.getValue())
				{
					if (l.hasAttribute(AttributeName.NAME))
					{
						l.renameAttribute(AttributeName.NAME, AttributeName.DESCRIPTIVENAME, null, null);
					}
				}

			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkSpanBase extends WalkElement
	{
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
			return (toCheck instanceof JDFSpanBase);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFSpanBase b = (JDFSpanBase) e1;
			if (version != null)
			{
				if (version.getValue() >= EnumVersion.Version_1_2.getValue())
				{
					if (b.hasAttribute(AttributeName.PRIORITY))
					{
						if (b.getPriority() == EnumPriority.Required)
						{
							b.setSettingsPolicy(EnumSettingsPolicy.MustHonor);
						}
						else
						{
							b.setSettingsPolicy(EnumSettingsPolicy.BestEffort);
						}
						b.removeAttribute(AttributeName.PRIORITY);
					}
				}
				else
				{
					if (b.getSettingsPolicy(true) == EnumSettingsPolicy.BestEffort)
					{
						b.setPriority(EnumPriority.Required);
					}
					else
					{
						b.setPriority(EnumPriority.Suggested);
					}
				}
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkNodeInfo extends WalkResource
	{
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
			return (toCheck instanceof JDFNodeInfo);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFNodeInfo ni = (JDFNodeInfo) e1;
			if (ni.hasAttribute(AttributeName.RREFS))
			{
				ni.removeAttribute(AttributeName.RREFS);
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkCustomerInfo extends WalkResource
	{
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
			return (toCheck instanceof JDFCustomerInfo);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFCustomerInfo ci = (JDFCustomerInfo) e1;
			if (ci.hasAttribute(AttributeName.RREFS))
			{
				ci.removeAttribute(AttributeName.RREFS);
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkLayoutPrep extends WalkResource
	{
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
			return (toCheck instanceof JDFLayoutPreparationParams);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFLayoutPreparationParams lpp = (JDFLayoutPreparationParams) e1;
			if (lpp.isResourceRoot() && bLayoutPrepToStripping)
			{
				final StrippingConverter sc = lpp.convertToStripping();
				// the new elements are NOT in the original and must therefore be called individually
				new FixVersion(FixVersion.this).walkTree(sc.getAssembly(), null);
				new FixVersion(FixVersion.this).walkTree(sc.getStrippingParams(), null);
				new FixVersion(FixVersion.this).walkTree(sc.getBinderySignature(), null);
				return null; // stop here, we zapped lpp
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @param layoutPrepToStripping the bLayoutPrepToStripping to set
	 */
	public void setLayoutPrepToStripping(final boolean layoutPrepToStripping)
	{
		bLayoutPrepToStripping = layoutPrepToStripping;
	}

}
