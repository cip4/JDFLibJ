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

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.intent.JDFColorIntent;

/**
 * TODO discuss and implement varying numcolors for front and back, e.g. 4/1
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class WalkColorIntent extends WalkIntentResource
{

	/**
	 * 
	 * 
	 */
	public WalkColorIntent()
	{
		super();
		backAtts = new VString("Coatings ColorStandard Coverage", null);
	}

	final VString backAtts;

	/**
	 * @param e
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement e, final KElement trackElem)
	{
		evaluateColorsUsed(e);
		if (e.hasAttribute("NumColors"))
		{
			evaluateNumColors(e, trackElem);
		}
		for (String att : backAtts)
		{
			evaluateBackAttribute(e, att);
		}

		KElement ret = super.walk(e, trackElem);
		repartitionSide(e, trackElem);
		return ret;
	}

	/**
	 * repartition in case stuff that is side dependent exists - note that trackEleme is the new element
	 * @param e
	 */
	private void repartitionSide(final KElement e, final KElement trackElem)
	{
		KElement cuBack = e.getElement("ColorsUsedBack");
		KElement cuFront = e.getElement("ColorsUsed");
		if (cuBack != null && cuFront != null)
		{
			VElement sepsFront = cuFront.getChildElementVector(ElementName.SEPARATIONSPEC, null);
			VElement sepsBack = cuBack.getChildElementVector(ElementName.SEPARATIONSPEC, null);
			if (!sepsFront.isEqual(sepsBack))
			{
				if (cuFront.getElement(ElementName.SEPARATIONSPEC) != null)
				{
					JDFResource ciFront = ((JDFResource) trackElem).getCreatePartition(EnumPartIDKey.Side, "Front", null);
					ciFront.moveElement(cuFront, null);
				}
				else
				{
					cuFront.deleteNode();
				}
				if (cuBack.getElement(ElementName.SEPARATIONSPEC) != null)
				{
					JDFResource ciBack = ((JDFResource) trackElem).getCreatePartition(EnumPartIDKey.Side, "Back", null);
					cuBack.renameElement("ColorsUsed", null);
					ciBack.moveElement(cuBack, null);
				}
				else
				{
					cuBack.deleteNode();
				}
			}
			else
			{
				cuBack.deleteNode();
			}

		}
		else if (cuBack == null && cuFront != null)
		{
			if (cuFront.getElement(ElementName.SEPARATIONSPEC) != null)
			{
				JDFResource ciFront = ((JDFResource) trackElem).getCreatePartition(EnumPartIDKey.Side, "Front", null);
				ciFront.moveElement(cuFront, null);
			}
			else
			{
				cuFront.deleteNode();
			}
		}
		else if (cuBack != null && cuFront == null)
		{
			if (cuBack.getElement(ElementName.SEPARATIONSPEC) != null)
			{
				JDFResource ciBack = ((JDFResource) trackElem).getCreatePartition(EnumPartIDKey.Side, "Back", null);
				ciBack.moveElement(cuBack, null);
			}
			else
			{
				cuBack.deleteNode();
			}
		}
		for (String att : backAtts)
		{
			String back = att + "Back";
			KElement coatBack = e.getElement(back);
			if (coatBack != null)
			{
				KElement coatings = e.getElement(att);
				if (coatings != null)
				{
					JDFResource ciFront = ((JDFResource) trackElem).getCreatePartition(EnumPartIDKey.Side, "Front", null);
					ciFront.moveElement(coatings, null);
				}
				JDFResource ciBack = ((JDFResource) trackElem).getCreatePartition(EnumPartIDKey.Side, "Back", null);
				ciBack.moveElement(e.getElement(back), null).renameElement(att, null);
			}
			evaluateBackAttribute(e, att);
		}
	}

	/**
	 * @param e
	 */
	private void evaluateColorsUsed(final KElement e)
	{
		JDFSeparationList sl = createSeparationList(e, "ColorsUsed");
		if (e.hasAttribute("ColorsUsedBack"))
		{
			if (sl != null)
				sl = (JDFSeparationList) sl.deleteNode();
			e.renameAttribute("ColorsUsedBack", "ColorsUsed", null, null);
			JDFSeparationList slBack = createSeparationList(e, "ColorsUsed");
			slBack.renameElement("ColorsUsedBack", null);
			if (sl != null)
			{
				e.moveElement(sl, slBack);
			}
		}
	}

	private void evaluateBackAttribute(final KElement e, String front)
	{
		String back = front + "Back";
		if (e.hasAttribute(back))
		{
			String frontVal = e.getAttribute(front, null, null);
			e.renameAttribute(back, front, null, null);
			KElement span = xjdfToJDFImpl.attributeToSpan(e, front);
			span.renameElement(back, null);
			e.setAttribute(front, frontVal);
		}
	}

	/**
	 * @param e
	 * @param trackElem
	 */
	private void evaluateNumColors(final KElement e, final KElement trackElem)
	{
		JDFXYPair xyp = JDFXYPair.createXYPair(e.getAttribute("NumColors", null, null));
		e.removeAttribute(AttributeName.NUMCOLORS);
		if (xyp != null)
		{
			int front = (int) xyp.getX();
			int back = (int) xyp.getY();
			if (front == back)
			{
				trackElem.setAttribute(AttributeName.NUMCOLORS, front, null);
			}
			else
			{
				JDFColorIntent ci = (JDFColorIntent) trackElem;
				if (front > 0)
				{
					JDFColorIntent cif = (JDFColorIntent) ci.getCreatePartition(new JDFAttributeMap("Side", "Front"), null);
					cif.setNumColors(front);
				}
				if (back > 0)
				{
					JDFColorIntent cib = (JDFColorIntent) ci.getCreatePartition(new JDFAttributeMap("Side", "Back"), null);
					cib.setNumColors(back);
				}
			}
		}
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return super.matches(toCheck) && (toCheck instanceof JDFColorIntent);
	}
}