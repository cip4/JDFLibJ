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

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFContentObject;

/**
 * class to maintain data from typesafe library
 *
 * @author rainer prosi
 *
 */
class JDFToXJDFDataCache
{
	private static JDFToXJDFDataCache theCache;

	/**
	 *
	 * @return
	 */
	static JDFToXJDFDataCache getCache()
	{
		if (theCache == null)
			theCache = new JDFToXJDFDataCache();
		return theCache;
	}

	/**
	 *
	 */
	private JDFToXJDFDataCache()
	{
		resAttribs = generateResourceAttributes();
		elemAttribs = generateElementAttributes();
		inlineSet = generateInlineSet();
		placedObjectAttribs = generatePlacedObjectAttributes();
		amountAttribs = new VString("Amount,ActualAmount,MinAmount,MaxAmount", ",");
	}

	/**
	 *
	 * @return
	 */
	private VString generatePlacedObjectAttributes()
	{
		final JDFContentObject co = (JDFContentObject) new JDFDoc(ElementName.CONTENTOBJECT).getRoot();
		final JDFMarkObject mo = (JDFMarkObject) new JDFDoc(ElementName.MARKOBJECT).getRoot();
		VString vco = co.knownAttributes();
		VString vmo = mo.knownAttributes();
		return vmo.getOverlapping(vco);
	}

	private final VString elemAttribs;
	private final HashSet<String> inlineSet;
	private final VString resAttribs;
	private final VString placedObjectAttribs;
	final private VString amountAttribs;

	/**
	 *
	 *
	 * @return
	 */
	private VString generateElementAttributes()
	{
		final JDFResourcePool dummyResPool = (JDFResourcePool) new JDFDoc(ElementName.RESOURCEPOOL).getRoot();
		return dummyResPool.knownAttributes();
	}

	/**
	 *
	 * @return
	 */
	private HashSet<String> generateInlineSet()
	{
		HashSet<String> set = new HashSet<String>();
		set.add(ElementName.OBJECTRESOLUTION);
		set.add(ElementName.BARCODECOMPPARAMS);
		set.add(ElementName.BARCODEREPROPARAMS);
		set.add(ElementName.COMCHANNEL);
		set.add(ElementName.INTERPRETEDPDLDATA);
		set.add(ElementName.BYTEMAP);
		set.add(ElementName.ADDRESS);
		set.add(ElementName.COSTCENTER);
		set.add(ElementName.COLORANTALIAS);
		set.add(ElementName.COMPANY);
		set.add(ElementName.PERSON);
		set.add(ElementName.DEVICE);
		set.add(ElementName.DEVICENSPACE);
		set.add(ElementName.EMPLOYEE);
		set.add(ElementName.GLUELINE);
		set.add(ElementName.GLUEAPPLICATION);
		set.add(ElementName.CIELABMEASURINGFIELD);
		set.add(ElementName.REGISTERMARK);
		set.add(ElementName.FITPOLICY);
		set.add(ElementName.CUTBLOCK);
		set.add(ElementName.ELEMENTCOLORPARAMS);
		set.add(ElementName.CUT);
		set.add(ElementName.PDLRESOURCEALIAS);
		set.add(ElementName.HOLELIST);
		set.add(ElementName.HOLE);
		set.add(ElementName.MISDETAILS);
		set.add(ElementName.HOLELINE);
		set.add(ElementName.JOBFIELD);
		set.add(ElementName.AUTOMATEDOVERPRINTPARAMS);
		set.add(ElementName.EXTERNALIMPOSITIONTEMPLATE);
		set.add(ElementName.PRODUCTIONPATH);
		set.add(ElementName.SHAPE);
		set.add(ElementName.SCAVENGERAREA);
		set.add(ElementName.TRAPREGION);
		set.add(ElementName.TRANSFERCURVE);
		set.add(ElementName.COLORCONTROLSTRIP);
		set.add(ElementName.LAYERLIST);
		set.add(ElementName.PAGECONDITION);
		set.add(ElementName.CONTENTOBJECT);
		set.add(ElementName.MARKOBJECT);
		set.add(ElementName.LAYERDETAILS);
		set.add(ElementName.FILESPEC);
		set.add(ElementName.IDENTIFICATIONFIELD);
		set.add(ElementName.LAYOUTELEMENT);
		return set;
	}

	/**
	 *
	 *
	 * @return
	 */
	private VString generateResourceAttributes()
	{
		VString resAttribs = new VString();
		final JDFResourcePool dummyResPool = (JDFResourcePool) new JDFDoc(ElementName.RESOURCEPOOL).getRoot();
		final JDFResource intRes = dummyResPool.appendResource("intent", EnumResourceClass.Intent, null);
		final JDFResource physRes = dummyResPool.appendResource("physical", EnumResourceClass.Consumable, null);
		final JDFResource paramRes = dummyResPool.appendResource("param", EnumResourceClass.Parameter, null);
		final JDFPart part = (JDFPart) dummyResPool.appendElement(ElementName.PART);
		resAttribs = paramRes.knownAttributes();
		resAttribs.appendUnique(physRes.knownAttributes());
		resAttribs.appendUnique(intRes.knownAttributes());
		resAttribs.appendUnique(part.knownAttributes());

		resAttribs.appendUnique(XJDFConstants.ExternalID);
		return resAttribs;
	}

	/**
	 * @return the elemAttribs
	 */
	static VString getElemAttribs()
	{
		return getCache().elemAttribs;
	}

	/**
	 * @return the inlineSet
	 */
	static HashSet<String> getInlineSet()
	{
		return getCache().inlineSet;
	}

	/**
	 * @return the resAttribs
	 */
	static VString getResAttribs()
	{
		return getCache().resAttribs;
	}

	/**
	 * @return the placedObjectAttribs
	 */
	static VString getPlacedObjectAttribs()
	{
		return getCache().placedObjectAttribs;
	}

	/**
	 *
	 * @return
	 */
	static VString getAmountAttribs()
	{
		return getCache().amountAttribs;
	}
}