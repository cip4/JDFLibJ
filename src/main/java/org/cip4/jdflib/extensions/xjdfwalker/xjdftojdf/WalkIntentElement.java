/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.resource.JDFBindItem;
import org.cip4.jdflib.resource.JDFEdgeGluing;
import org.cip4.jdflib.resource.JDFEmbossingItem;
import org.cip4.jdflib.resource.JDFHardCoverBinding;
import org.cip4.jdflib.resource.JDFInsert;
import org.cip4.jdflib.resource.JDFNumberItem;
import org.cip4.jdflib.resource.JDFProofItem;
import org.cip4.jdflib.resource.JDFSoftCoverBinding;
import org.cip4.jdflib.resource.JDFStripBinding;
import org.cip4.jdflib.resource.JDFTabs;
import org.cip4.jdflib.resource.intent.JDFArtDelivery;
import org.cip4.jdflib.resource.intent.JDFDropIntent;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.intent.JDFShapeCut;
import org.cip4.jdflib.resource.process.postpress.JDFChannelBinding;
import org.cip4.jdflib.resource.process.postpress.JDFCoilBinding;
import org.cip4.jdflib.resource.process.postpress.JDFPlasticCombBinding;
import org.cip4.jdflib.resource.process.postpress.JDFRingBinding;
import org.cip4.jdflib.resource.process.postpress.JDFSaddleStitching;
import org.cip4.jdflib.resource.process.postpress.JDFSideSewing;
import org.cip4.jdflib.resource.process.postpress.JDFThreadSealing;
import org.cip4.jdflib.resource.process.postpress.JDFThreadSewing;

/**
 *
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 * Mar 17, 2010
 */
public class WalkIntentElement extends WalkXElement
{
	/**
	 *
	 */
	public WalkIntentElement()
	{
		super();
	}

	/**
	 * @param e
	 * @return the created element
	 */
	@Override
	public KElement walk(final KElement e, final KElement trackElem)
	{
		KElement ret = super.walk(e, trackElem);
		xjdfToJDFImpl.attributesToSpan(ret);
		return ret;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return (toCheck instanceof JDFDropIntent) || (toCheck instanceof JDFDropItemIntent) || (toCheck instanceof JDFArtDelivery) || (toCheck instanceof JDFBindItem)
				|| (toCheck instanceof JDFChannelBinding) || (toCheck instanceof JDFCoilBinding) || (toCheck instanceof JDFEdgeGluing) || (toCheck instanceof JDFHardCoverBinding)
				|| (toCheck instanceof JDFPlasticCombBinding) || (toCheck instanceof JDFRingBinding) || (toCheck instanceof JDFSaddleStitching)
				|| (toCheck instanceof JDFSideSewing) || (toCheck instanceof JDFSoftCoverBinding) || (toCheck instanceof JDFStripBinding) || (toCheck instanceof JDFTabs)
				|| (toCheck instanceof JDFThreadSealing) || (toCheck instanceof JDFThreadSewing) || (toCheck instanceof JDFEmbossingItem) || (toCheck instanceof JDFInsert)
				|| (toCheck instanceof JDFNumberItem) || (toCheck instanceof JDFProofItem) || (toCheck instanceof JDFShapeCut);
	}
}