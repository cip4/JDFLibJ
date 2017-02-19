/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.HashSet;
import java.util.Set;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;

/**
 * any matching class will be removed with extreme prejudice...
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class WalkIgnore extends WalkElement
{
	private static Set<String> allwaysIgnore = null;

	/**
	 *
	 */
	public WalkIgnore()
	{
		super();
		depth += 42; // bump us up front so that we always get checked first
	}

	/**
	 * @param xjdf
	 * @return true if must continue
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		return null;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
	 */
	@Override
	public VString getElementNames()
	{
		VString v = new VString();
		v.add(ElementName.ACKNOWLEDGE);
		v.add(ElementName.ACTIONPOOL);
		v.add(ElementName.ADHESIVEBINDING);
		v.add(ElementName.ANCESTORPOOL);
		v.add(ElementName.ASSETLISTCREATIONPARAMS);
		v.add(ElementName.BINDLIST);
		v.add(ElementName.BOOKCASE);
		v.add(ElementName.BUSINESSINFO);
		v.add(ElementName.COLORMEASUREMENTCONDITIONS);
		v.add(ElementName.COLORSPACESUBSTITUTE);
		v.add(ElementName.CONTACTCOPYPARAMS);
		v.add(ElementName.CONTAINER);
		v.add(ElementName.CUSTOMERMESSAGE);
		v.add(ElementName.CYLINDERLAYOUT);
		v.add(ElementName.CYLINDERLAYOUTPREPARATIONPARAMS);
		v.add(ElementName.CYLINDERPOSITION);
		v.add(ElementName.DELETED);
		v.add(ElementName.DEVCAPPOOL);
		v.add(ElementName.DEVCAPS);
		v.add(ElementName.DEVCAP);
		v.add(ElementName.DEVICECAP);
		v.add(ElementName.DIGITALDELIVERYPARAMS);
		v.add(ElementName.ELEMENTCOLORPARAMS);
		v.add(ElementName.FILEALIAS);
		v.add(ElementName.FORMATCONVERSIONPARAMS);
		v.add(ElementName.IMAGEREPLACEMENTPARAMS);
		v.add(ElementName.INTERPRETEDPDLDATA);
		v.add(ElementName.MERGED);
		v.add(ElementName.MODIFIED);
		v.add(ElementName.MODULEPOOL);
		v.add(ElementName.OBSERVATIONTARGET);
		v.add(ElementName.PACKINGINTENT);
		v.add(ElementName.PAGEASSIGNEDLIST);
		v.add(ElementName.PAGEASSIGNPARAMS);
		v.add(ElementName.PARTAMOUNT);
		v.add(ElementName.PDFTOPSCONVERSIONPARAMS);
		v.add(ElementName.PDLRESOURCEALIAS);
		v.add(ElementName.PREFLIGHTREPORTRULEPOOL);
		v.add(ElementName.PRGROUP);
		v.add(ElementName.PRGROUPOCCURRENCE);
		v.add(ElementName.PRITEM);
		v.add(ElementName.PSTOPDFCONVERSIONPARAMS);

		v.add(ElementName.REGISTRATION);
		v.add(ElementName.RESOURCEDEFINITIONPARAMS);
		v.add(ElementName.RESOURCEPOOL);
		v.add(ElementName.SCANPARAMS);
		v.add(ElementName.SCREENINGINTENT);
		v.add(ElementName.SOURCERESOURCE);
		v.add(ElementName.SPAWNED);
		v.add(ElementName.TILE);
		v.add(ElementName.TESTPOOL);
		v.add(ElementName.TRIGGER);
		return v;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#getElementNames()
	 */

	Set<String> getAlwaysIgnore()
	{
		if (allwaysIgnore == null)
		{
			HashSet<String> v = new HashSet<String>();
			v.add(ElementName.ACTIONPOOL);
			v.add(ElementName.DELETED);
			v.add(ElementName.DEVCAPPOOL);
			v.add(ElementName.DEVCAPS);
			v.add(ElementName.DEVCAP);
			v.add(ElementName.DEVICECAP);
			v.add(ElementName.MERGED);
			v.add(ElementName.MODIFIED);
			v.add(ElementName.MODULEPOOL);
			v.add(ElementName.PARTAMOUNT);
			v.add(ElementName.RESOURCEPOOL);
			v.add(ElementName.SPAWNED);
			v.add(ElementName.TESTPOOL);
			allwaysIgnore = v;
		}
		return allwaysIgnore;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 */
	@Override
	public boolean matches(KElement e)
	{
		if (jdfToXJDF.isRetainAll())
		{
			return getAlwaysIgnore().contains(e.getLocalName());
		}
		else
		{
			return true;
		}
	}

}