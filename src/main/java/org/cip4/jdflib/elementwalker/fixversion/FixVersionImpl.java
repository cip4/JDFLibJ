/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of
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
package org.cip4.jdflib.elementwalker.fixversion;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.elementwalker.PackageElementWalker;
import org.cip4.jdflib.util.EnumUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG<br/>
 * fixes versions within JDF 1.x June 7, 2009<br/>
 * uses heuristics to modify this element and its children to be compatible with a given version<br>
 * in general, it will be able to move from low to high versions, but potentially fail when attempting to move from higher to lower versions
 *
 * This class is the result of refactoring the recursive fixVersion routines from the dom node tree into one class
 *
 */
public class FixVersionImpl extends PackageElementWalker
{
	protected boolean bFixIDs;
	protected boolean bZappInvalid;
	protected boolean bZappDeprecated;
	protected boolean bFixNewDuplicate;

	/**
	 * @return the bFixNewDuplicate
	 */
	public boolean isFixNewDuplicate()
	{
		return bFixNewDuplicate;
	}

	/**
	 * @param bFixNewDuplicate the bFixNewDuplicate to set
	 */
	public void setFixNewDuplicate(final boolean bFixNewDuplicate)
	{
		this.bFixNewDuplicate = bFixNewDuplicate;
	}

	// hours for missing times in dates
	int firsthour;
	int lasthour;

	/**
	 * @return the bZappDeprecated
	 */
	public boolean isZappDeprecated()
	{
		return bZappDeprecated;
	}

	/**
	 * @param bZappDeprecated the bZappDeprecated to set
	 */
	public void setZappDeprecated(final boolean bZappDeprecated)
	{
		this.bZappDeprecated = bZappDeprecated;
	}

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
	public FixVersionImpl(final EnumVersion _version)
	{
		super(new BaseWalkerFactory());
		new BaseWalker(getFactory()); // need a default walker
		bFixIDs = true;
		bZappInvalid = false;
		bZappDeprecated = false;
		version = _version;
		bOK = true;
		fixICSVersions = false;
		bLayoutPrepToStripping = false;
		bFixNewDuplicate = true;
		firsthour = 6;
		lasthour = 18;
	}

	/**
	 * @param fixVersion
	 */
	public FixVersionImpl(final FixVersionImpl fixVersion)
	{
		super(new BaseWalkerFactory());
		version = fixVersion.version;
		bZappInvalid = fixVersion.bZappInvalid;
		bZappDeprecated = fixVersion.bZappDeprecated;
		bFixIDs = fixVersion.bFixIDs;
		fixICSVersions = fixVersion.fixICSVersions;
		bLayoutPrepToStripping = fixVersion.bLayoutPrepToStripping;
		bFixNewDuplicate = fixVersion.bFixNewDuplicate;
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
	public boolean convert(final KElement e)
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
	protected boolean lessThanVersion(final EnumVersion v)
	{
		return EnumUtil.aLessThanB(version, v);
	}

	/**
	 * @param zappInvalid the bZappInvalid to set
	 */
	public void setBZappInvalid(final boolean zappInvalid)
	{
		bZappInvalid = zappInvalid;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.PackageElementWalker#constructWalker(java.lang.String)
	 */
	@Override
	protected BaseWalker constructWalker(final String name)
	{
		final WalkElement constructWalker = (WalkElement) super.constructWalker(name);
		if (constructWalker != null)
			constructWalker.setParent(this);
		return constructWalker;
	}

	/**
	 *
	 * @return
	 */
	public boolean isXJDF()
	{
		return version != null && version.isXJDF();
	}

	/**
	 * @param firsthour the firsthour to set
	 */
	public void setFirsthour(final int firsthour)
	{
		this.firsthour = firsthour;
	}

	/**
	 * @param lasthour the lasthour to set
	 */
	public void setLasthour(final int lasthour)
	{
		this.lasthour = lasthour;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FixVersion [bFixIDs=" + bFixIDs + ", bZappInvalid=" + bZappInvalid + ", bZappDeprecated=" + bZappDeprecated + ", bFixNewDuplicate=" + bFixNewDuplicate
				+ ", firsthour=" + firsthour + ", lasthour=" + lasthour + ", " + (version != null ? "version=" + version + ", " : "") + "fixICSVersions=" + fixICSVersions
				+ ", bLayoutPrepToStripping=" + bLayoutPrepToStripping + "]";
	}
}
