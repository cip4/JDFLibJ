/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of 
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
 */

/**
 * ========================================================================== 
 * class JDFProcessRun extends JDFAutoProcessRun
 * created 2001-09-06T10:02:57GMT+02:00 
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * Author: sabjon@topmail.de   using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 * Revision history:   ...
 */

package org.cip4.jdflib.resource;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoProcessRun;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.w3c.dom.DOMException;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * < July 20, 2009
 */
public class JDFProcessRun extends JDFAutoProcessRun
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFProcessRun
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFProcessRun(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFProcessRun
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFProcessRun(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFProcessRun
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFProcessRun(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFProcessRun[  --> " + super.toString() + " ]";
	}

	/**
	 * set the duration in seconds
	 * 
	 * @param seconds the value to set
	 * @throws JDFException
	 */
	public void setDurationSeconds(final int seconds) throws JDFException
	{
		if (seconds < 0)
		{
			throw new JDFException("parameter must be >= 0");
		}
		final JDFDuration d = new JDFDuration();
		d.setDuration(seconds);
		setAttribute("Duration", d.getDurationISO(), JDFConstants.EMPTYSTRING);
	}

	// //////////////////////////////////////////////////////////////////////////
	/**
	 * get the duration in seconds
	 * 
	 * @return int: the duration value in seconds, 0 if duration does not exist
	 */
	public int getDurationSeconds()
	{
		final JDFDuration d = getDuration();
		if (d == null)
		{
			return 0;
		}
		return d.getDuration();
	}

	// //////////////////////////////////////////////////////////////////////////

	/**
	 * get the explicit or implied duration specified by Start and End
	 * 
	 * @return JDFDuration the duration
	 */
	@Override
	public JDFDuration getDuration()
	{
		JDFDuration dur = super.getDuration();
		if (dur != null)
		{
			return dur;
		}

		final JDFDate dStart = getStart();
		final JDFDate dEnd = getEnd();
		if (dStart == null || dEnd == null)
		{
			return null;
		}
		dur = new JDFDuration(dStart, dEnd);
		return dur;
	}

	/**
	 * set all parts to those defined in vParts
	 * 
	 * @param vParts vector of attribute maps for the parts
	 */
	@Override
	public void setPartMapVector(final VJDFAttributeMap vParts)
	{
		super.setPartMapVector(vParts);
	}

	/**
	 * set all parts to those defined by mPart
	 * 
	 * @param mPart attribute map for the part to set
	 */
	@Override
	public void setPartMap(final JDFAttributeMap mPart)
	{
		super.setPartMap(mPart);
	}

	/**
	 * remove the part defined by mPart
	 * 
	 * @param mPart attribute map for the part to remove
	 */
	@Override
	public void removePartMap(final JDFAttributeMap mPart)
	{
		super.removePartMap(mPart);
	}

	/**
	 * check whether the part defined by mPart is included
	 * 
	 * @param mPart attribute map to look for
	 * @return boolean - returns true if the part exists
	 */
	@Override
	public boolean hasPartMap(final JDFAttributeMap mPart)
	{
		return super.hasPartMap(mPart);
	}

	// ////////////////////////////////////////////////////////

	/**
	 * add the active times in the PhaseTime pt to this processrun
	 * 
	 * @param pt the PhaseTimes to add
	 */
	public void addPhase(final JDFPhaseTime pt)
	{
		if (pt == null)
		{
			return;
		}

		final EnumNodeStatus status = pt.getStatus();
		if (status == null || status.equals(EnumNodeStatus.Ready) || status.equals(EnumNodeStatus.Completed) || status.equals(EnumNodeStatus.FailedTestRun) || status.equals(EnumNodeStatus.Spawned)
				|| status.equals(EnumNodeStatus.Stopped) || status.equals(EnumNodeStatus.Suspended) || status.equals(EnumNodeStatus.Waiting))
		{
			return;
		}

		final JDFDuration dur = pt.getDuration();
		if (dur != null)
		{
			addDuration(dur.getDuration());
		}

		final JDFDate start = pt.getStart();
		if (start != null)
		{
			if (start.before(getStart()))
			{
				setStart(start);
			}
		}

		final JDFDate end = pt.getEnd();
		if (end != null)
		{
			if (end.after(getEnd()))
			{
				setEnd(end);
			}
		}

	}

	/**
	 * ensure that duration matches end-start, <br/>
	 * i.e. that duration is never longer than the full preiod between start and end
	 * 
	 */
	public void ensureNotLonger()
	{
		final JDFDate start = getStart();
		final JDFDate end = getEnd();
		if (start != null && end != null)
		{
			final JDFDuration total = new JDFDuration(start, end);
			if (total.compareTo(getDuration()) < 0)
			{
				setDuration(total);
			}
		}
	}

	// ////////////////////////////////////////////////////////

	/**
	 * add delta seconds to duration and set the updated attribute value
	 * 
	 * @param seconds duration to add in seconds
	 */
	public void addDuration(final int seconds)
	{
		final JDFDuration dur = getDuration();
		final int l = dur == null ? 0 : dur.getDuration();
		setDurationSeconds(l + seconds);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * (11) set attribute SubmissionTime
	 * 
	 * @param value : the value to set the attribute to or null if null, set to the current time
	 */
	@Override
	public void setSubmissionTime(final JDFDate value)
	{
		JDFDate valueLocal = value;

		if (valueLocal == null)
		{
			valueLocal = new JDFDate();
		}

		setAttribute(AttributeName.SUBMISSIONTIME, valueLocal.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute SubmissionTime
	 * 
	 * @return JDFDate the value of the attribute
	 */
	@Override
	public JDFDate getSubmissionTime()
	{
		final String str = getAttribute(AttributeName.SUBMISSIONTIME, null, null);
		if (str == null)
		{
			return null;
		}
		try
		{
			return new JDFDate(str);
		}
		catch (final DataFormatException dfe)
		{
			throw new JDFException("not a valid date string. Malformed JDF");
		}
	}

	/**
	 * (11) set attribute ReturnTime
	 * 
	 * @param value : the value to set the attribute to or null if null, set to the current time
	 */
	@Override
	public void setReturnTime(final JDFDate value)
	{
		JDFDate valueLocal = value;

		if (valueLocal == null)
		{
			valueLocal = new JDFDate();
		}

		setAttribute(AttributeName.RETURNTIME, valueLocal.getDateTimeISO(), null);
	}

	/**
	 * (12) get JDFDate attribute ReturnTime
	 * 
	 * @return JDFDate the value of the attribute
	 */
	@Override
	public JDFDate getReturnTime()
	{
		final String str = getAttribute(AttributeName.RETURNTIME, null, null);
		if (str == null)
		{
			return null;
		}
		try
		{
			return new JDFDate(str);
		}
		catch (final DataFormatException dfe)
		{
			throw new JDFException("not a valid date string. Malformed JDF");
		}
	}

	/**
	 * also sets an end time for this
	 * @see org.cip4.jdflib.core.JDFAudit#init()
	 */
	@Override
	public boolean init()
	{
		setEnd(null);
		return super.init();
	}

	/**
	 * returns true if audit belongs to this processrun
	 * 
	 * @param audit
	 * @return
	 */
	public boolean matches(final JDFAudit audit)
	{
		if (audit == null)
		{
			return false;
		}
		// must be in same pool
		if (!ContainerUtil.equals(audit.getParentNode(), getParentNode()))
		{
			return false;
		}
		KElement prev = getPreviousSiblingElement();
		while (prev != null)
		{
			if (prev instanceof JDFProcessRun)
			{
				prev = null; // we found the previous pr - jump out
				break;
			}
			if (prev == audit)
			{
				break; // it is in the chain - we are ok
			}

			prev = prev.getPreviousSiblingElement();
		}
		if (prev != audit)
		{
			return false;
		}
		if (!ContainerUtil.equals(getQueueEntryID(), audit.getQueueEntryID()))
		{
			return false;
		}
		final VJDFAttributeMap vMap = getPartMapVector();
		if (vMap != null && !vMap.overlapsMap(audit.getPartMapVector()))
		{
			return false;
		}
		return true;

	}

}

// class JDFProcessRun
// ==========================================================================
