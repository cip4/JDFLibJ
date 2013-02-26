/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of
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
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumSettingsPolicy;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.jmf.JDFAcknowledge;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFQuery;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFPool;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFLayoutPreparationParams;
import org.cip4.jdflib.resource.JDFLayoutPreparationParams.StrippingConverter;
import org.cip4.jdflib.resource.JDFPageList;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.JDFTool;
import org.cip4.jdflib.resource.devicecapability.JDFAbstractState;
import org.cip4.jdflib.resource.process.JDFApprovalDetails;
import org.cip4.jdflib.resource.process.JDFApprovalSuccess;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFAssemblySection;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFEmployee;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFPageData;
import org.cip4.jdflib.span.JDFSpanBase;
import org.cip4.jdflib.span.JDFSpanBase.EnumPriority;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.JDFDate;
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
	protected boolean bZappInvalid;
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
		bZappInvalid = false;
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
		super(new BaseWalkerFactory());
		version = fixVersion.version;
		bZappInvalid = fixVersion.bZappInvalid;
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

			// replace all "~" with " ~ "
			final JDFAttributeMap m = el.getAttributeMap();
			final Iterator<String> it = m.getKeyIterator();
			final AttributeInfo ai = el.getAttributeInfo();
			while (it.hasNext())
			{
				final String key = it.next();
				final String value = m.get(key);
				walkSingleAttribute(el, ai, key, value);
			}
			return el;
		}

		/**
		 * @param el
		 * @param ai
		 * @param key
		 * @param value
		 */
		private void walkSingleAttribute(final JDFElement el, final AttributeInfo ai, final String key, final String value)
		{
			final EnumAttributeType attType = ai.getAttributeType(key);

			if (EnumAttributeType.isRange(attType))
			{
				fixRange(el, key, value);
			}
			else if (EnumAttributeType.duration.equals(attType))
			{
				fixDuration(el, key, value);
			}
			else if (EnumAttributeType.dateTime.equals(attType))
			{
				fixDateTime(el, key, value);
			}
			if (bFixIDs && value.length() > 0 && StringUtils.isNumeric(value.substring(0, 1)))
			{
				fixIDs(el, ai, key, value);
			}
			if (AttributeName.ICSVERSIONS.equals(key))
			{
				fixICSVesions(el, value);
			}
			if (bZappInvalid && attType != null)
			{
				if (!AttributeInfo.validStringForType(value, attType, null))
					el.removeAttribute_KElement(key, null);
			}

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
		 * @param key
		 * @param value
		 */
		private void fixDateTime(final JDFElement el, final String key, final String value)
		{
			try
			{
				el.setAttribute(key, new JDFDate(value).getDateTimeISO());
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
				updateDraftOK(rl);
			}
			return super.walk(e1, trackElem);
		}

		/**
		 * 
		 *  
		 * @param rl
		 */
		private void updateDraftOK(final JDFResourceLink rl)
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
				fixID(audit);
				final String author = audit.getAuthor();
				if (lessThanVersion(EnumVersion.Version_1_2))
				{
					mergeAuthor(audit, author);
				}
				else if (author.length() > 0) // version>=1.2 and has author
				{
					splitAuthor(audit, author);
				}
				authorToEmployee(audit);
			}
			return super.walk(e1, trackElem);
		}

		/**
		 * @param audit
		 */
		private void fixID(final JDFAudit audit)
		{
			if (lessThanVersion(EnumVersion.Version_1_3))
			{
				audit.removeAttribute(AttributeName.ID);
			}
			else
			{
				audit.appendAnchor(null);
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
		 */
		private void authorToEmployee(final JDFAudit audit)
		{
			if (!lessThanVersion(EnumVersion.Version_1_4))
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
	 *  
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
				if (EnumUtil.aLessThanB(EnumVersion.Version_1_3, version))
				{
					if (!jmf.hasAttribute(AttributeName.AGENTNAME))
						jmf.setAgentName(JDFAudit.getStaticAgentName());
					if (!jmf.hasAttribute(AttributeName.AGENTVERSION))
						jmf.setAgentName(JDFAudit.getStaticAgentVersion());
				}
				else
				{
					jmf.removeAttribute(AttributeName.AGENTNAME);
					jmf.removeAttribute(AttributeName.AGENTVERSION);
				}
			}
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 *  
	 */
	public class WalkJMFQuery extends WalkJMFMessage
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFQuery);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 *  
	 */
	public class WalkJMFResponseAcknowledge extends WalkJMFMessage
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFResponse) || (toCheck instanceof JDFAcknowledge);
		}

		/**
		 * 
		 *  
		 * @param resp the response or acknowledge
		 */
		void fixQueue(JDFMessage resp)
		{
			if (!EnumUtil.aLessThanB(version, EnumVersion.Version_1_5))
			{
				resp.removeChild(ElementName.QUEUE, null, 0);
			}
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 *  
	 */
	public class WalkJMFCommand extends WalkJMFMessage
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFCommand);
		}

		/**
		 * 
		 *  
		 * @param c
		 */
		void fixQueueFilter(JDFCommand c)
		{
			if (!EnumUtil.aLessThanB(version, EnumVersion.Version_1_5))
			{
				c.removeChild(ElementName.QUEUEFILTER, null, 0);
			}
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 *  
	 */
	public class WalkJMFCommandQueueFilter extends WalkJMFCommand
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkJMFMessage#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(KElement e1, KElement trackElem)
		{
			fixQueueFilter((JDFCommand) e1);
			return super.walk(e1, trackElem);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			if (!super.matches(toCheck))
				return false;
			return isQueueFilterRemove(toCheck);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 *  
	 */
	public class WalkJMFCommandAbortQueueEntry extends WalkJMFCommandQueueFilter
	{

		/**
		* 
		* @see org.cip4.jdflib.elementwalker.FixVersion.WalkJMFCommandQueueFilter#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		*/
		@Override
		public KElement walk(KElement e1, KElement trackElem)
		{
			if (!EnumUtil.aLessThanB(version, EnumVersion.Version_1_5))
			{
				e1.getCreateElement("AbortQueueEntryParams").moveElement(((JDFMessage) e1).getQueueEntryDef(0), null);
			}
			return super.walk(e1, trackElem);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			if (!super.matches(toCheck))
				return false;
			return JDFMessage.EnumType.AbortQueueEntry.equals(((JDFMessage) toCheck).getEnumType());
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 *  
	 */
	public class WalkJMFResponseQueue extends WalkJMFResponseAcknowledge
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkJMFMessage#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(KElement e1, KElement trackElem)
		{
			fixQueue((JDFMessage) e1);
			return super.walk(e1, trackElem);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			if (!super.matches(toCheck))
				return false;
			return isQueueFilterRemove(toCheck);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 *  
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
				fixNamedFeatures(n, trackElem);
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

		/**
		 * move namedfeatures to generalID
		 * @param trackElem 
		 * @param n 
		 */
		private void fixNamedFeatures(JDFNode n, KElement trackElem)
		{
			if (EnumUtil.aLessThanB(EnumVersion.Version_1_4, version))
			{
				VString v = n.getNamedFeatures();
				if (v != null)
				{
					for (int i = 0; i < v.size() / 2; i++)
					{
						String key = v.get(i * 2);
						String val = v.get(i * 2 + 1);
						//TODO use typesafe when we have schema
						n.appendGeneralID(key, val).setAttribute("DataType", "NamedFeature");
					}
				}
				n.removeAttribute(AttributeName.NAMEDFEATURES);
			}
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
	public class WalkGeneralID extends WalkElement
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFGeneralID);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * 
	 */
	public class WalkGeneralIDNamedFeature extends WalkGeneralID
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			boolean b = super.matches(toCheck);
			b = b && (toCheck.getParentNode() instanceof JDFNode);
			return b && "NamedFeature".endsWith(toCheck.getAttribute(AttributeName.DATATYPE));
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(KElement e1, KElement trackElem)
		{
			if (EnumUtil.aLessThanB(version, EnumVersion.Version_1_5))
			{
				JDFNode n = (JDFNode) e1.getParentNode();
				JDFGeneralID gid = (JDFGeneralID) e1;
				n.appendAttribute(AttributeName.NAMEDFEATURES, gid.getIDUsage(), null, " ", false);
				n.appendAttribute(AttributeName.NAMEDFEATURES, gid.getIDValue(), null, " ", false);
				gid.deleteNode();
				return null;
			}
			else
			{
				return super.walk(e1, trackElem);
			}
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * 
	 */
	public class WalkAssembly extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFAssembly);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFAssembly e = (JDFAssembly) e1;
			updateAssemblyIDS(e);

			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkAssemblySection extends WalkElement
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFAssemblySection);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFAssemblySection e = (JDFAssemblySection) e1;
			updateAssemblyIDS(e);

			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkPageList extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFPageList);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFPageList e = (JDFPageList) e1;
			updateAssemblyIDS(e);
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkStrippingParams extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFStrippingParams);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFStrippingParams e = (JDFStrippingParams) e1;
			updateAssemblyIDS(e);
			return super.walk(e1, trackElem);
		}
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 7, 2009
	 */
	public class WalkPageData extends WalkElement
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFPageData);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.FixVersion.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement) version fixing routine
		 * for JDF uses heuristics to modify this element and its children to be compatible with a given version in general, it will be able to move from low to
		 * high versions but potentially fail when attempting to move from higher to lower versions
		 */
		@Override
		public KElement walk(final KElement e1, final KElement trackElem)
		{
			final JDFPageData e = (JDFPageData) e1;
			updateAssemblyIDS(e);
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
				VElement rls = lpp.getLinksAndRefs(true, false);
				if (rls == null)
				{
					return null;
				}
				rls.unify();
				VElement vn = new VElement();
				for (int i = 0; i < rls.size(); i++)
				{
					JDFResourceLink rl = (JDFResourceLink) rls.get(i);
					vn.add(rl.getParentJDF());
				}
				vn.unify();
				if (vn.size() <= 0)
					return null;

				JDFNode node = (JDFNode) vn.get(0);
				final StrippingConverter sc = lpp.convertToStripping(node);
				// the new elements are NOT in the original and must therefore be called individually
				new FixVersion(FixVersion.this).walkTree(sc.getAssembly(), null);
				new FixVersion(FixVersion.this).walkTree(sc.getStrippingParams(), null);
				new FixVersion(FixVersion.this).walkTree(sc.getBinderySignature(), null);
				for (int i = 1; i < vn.size(); i++)
				{
					JDFNode n = (JDFNode) vn.get(i);
					n.linkResource(sc.getAssembly(), EnumUsage.Input, null);
					n.linkResource(sc.getStrippingParams(), EnumUsage.Input, null);
				}

				for (int i = 0; i < rls.size(); i++)
				{
					rls.get(i).deleteNode();
				}
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

	/**
	 * convert the element e to whichever version has been set up here
	 * 
	 * @param e the element to convert - typically a JDF element
	 * @return true if all went well
	 */
	public boolean convert(KElement e)
	{
		walkTree(e, null);
		return isOK();
	}

	/**
	 * @param e the assembly or assemblysection to update
	 */
	protected void updateAssemblyIDS(final JDFElement e)
	{
		if (version != null)
		{
			if (lessThanVersion(EnumVersion.Version_1_3))
			{
				e.renameAttribute(AttributeName.ASSEMBLYIDS, AttributeName.ASSEMBLYID, null, null);
			}
			else
			{
				// note that AssemblyID is a String and this therefore is valid
				e.renameAttribute(AttributeName.ASSEMBLYID, AttributeName.ASSEMBLYIDS, null, null);
			}
		}
	}

	/**
	 * returns true if v is less than the target version 
	 * @param v 
	 * @return
	 */
	protected boolean lessThanVersion(EnumVersion v)
	{
		return EnumUtil.aLessThanB(version, v);
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		return "[FixVersion : version=" + (version == null ? "null" : version.getName()) + " LO->Strip" + bLayoutPrepToStripping;
	}

	/**
	 * @param zappInvalid the bZappInvalid to set
	 */
	public void setBZappInvalid(boolean zappInvalid)
	{
		bZappInvalid = zappInvalid;
	}

	/**
	 * 
	 * this is one of the message types where queue or queueufilter should be zapped in JDF1.5 and above
	 * @param toCheck
	 * @return
	 */
	boolean isQueueFilterRemove(final KElement toCheck)
	{
		JDFMessage.EnumType type = ((JDFMessage) toCheck).getEnumType();
		if (type == null)
			return false;
		boolean b = true;
		b = b || JDFMessage.EnumType.AbortQueueEntry.equals(type);
		b = b || JDFMessage.EnumType.RemoveQueueEntry.equals(type);
		b = b || JDFMessage.EnumType.HoldQueueEntry.equals(type);
		b = b || JDFMessage.EnumType.RemoveQueueEntry.equals(type);
		b = b || JDFMessage.EnumType.ResubmitQueueEntry.equals(type);
		b = b || JDFMessage.EnumType.SetQueueEntryPosition.equals(type);
		b = b || JDFMessage.EnumType.SetQueueEntryPriority.equals(type);
		b = b || JDFMessage.EnumType.CloseQueue.equals(type);
		b = b || JDFMessage.EnumType.OpenQueue.equals(type);
		b = b || JDFMessage.EnumType.HoldQueue.equals(type);
		b = b || JDFMessage.EnumType.ResumeQueue.equals(type);
		b = b || JDFMessage.EnumType.FlushQueue.equals(type);
		return b;
	}
}
