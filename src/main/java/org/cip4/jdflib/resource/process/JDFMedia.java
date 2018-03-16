/*
 *
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFMedia
 *
 * Last changes
 *
 * 2002-07-02 JG added ValidMediaType() to correctly handle explicit Unknown enumeration
 * 2002-07-02 JG de-inlined copy constructor
 *
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoMedia;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.util.StringUtil;

/**
 *
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFMedia extends JDFAutoMedia implements IMatches
{
	private static final long serialVersionUID = 1L;

	/**
	 * implementation of spec table Translation of Paper grades between [ISO12647-2:2004] and [ISO12647-2:2013]
	 * @param iso
	 * @return 1-5 if valid; else null
	 */
	public static EnumISOPaperSubstrate getIsoPaperFromGrade(final int grade)
	{
		if (grade == 1)
		{
			return EnumISOPaperSubstrate.PS1;
		}
		else if (grade == 2)
		{
			return EnumISOPaperSubstrate.PS4;
		}
		else if (grade == 3)
		{
			return EnumISOPaperSubstrate.PS3;
		}
		else if (grade == 4)
		{
			return EnumISOPaperSubstrate.PS6;
		}
		else if (grade == 5)
		{
			return EnumISOPaperSubstrate.PS8;
		}
		else
		{
			return null;
		}
	}

	/**
	 * implementation of spec table Translation of Paper grades between [ISO12647-2:2004] and [ISO12647-2:2013]
	 * @param iso
	 * @return 1-5 if valid; else null
	 */
	public static int getGradeFromIsoPaper(final EnumISOPaperSubstrate iso)
	{
		if (iso == null)
		{
			return 0;
		}
		else if (EnumISOPaperSubstrate.PS1.equals(iso))
		{
			return 1;
		}
		else if (EnumISOPaperSubstrate.PS2.equals(iso) || EnumISOPaperSubstrate.PS3.equals(iso))
		{
			return 3;
		}
		else if (EnumISOPaperSubstrate.PS4.equals(iso))
		{
			return 2;
		}
		else if (EnumISOPaperSubstrate.PS5.equals(iso) || EnumISOPaperSubstrate.PS6.equals(iso) || EnumISOPaperSubstrate.PS7.equals(iso))
		{
			return 4;
		}
		else if (EnumISOPaperSubstrate.PS8.equals(iso))
		{
			return 5;
		}
		else
		{
			//???
			return 0;
		}
	}

	/**
	 * Constructor for JDFMedia
	 * @param myOwnerDocument
	 * @param qualifiedName
	 *
	 */
	public JDFMedia(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFMedia
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 *
	 * @param qualifiedName
	 */
	public JDFMedia(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFMedia
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 *
	 */
	public JDFMedia(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 *
	 * @see org.cip4.jdflib.auto.JDFAutoMedia#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return "JDFMedia[  --> " + super.toString() + " ]";
	}

	/**
	 * calculates paper thickness from weight, if and only if weight exists but
	 * not thickness
	 *
	 * @param bLocal
	 *            if true, only evaluate locally set attributes in this
	 *            partition, else check inherited attributes
	 * @param bRecurse
	 *            if true, do for all children, grandchildren rtc, else only
	 *            local
	 */
	public void setThicknessFromWeight(final boolean bLocal, final boolean bRecurse)
	{
		final EnumMediaType mT = getMediaType();
		if (!EnumMediaType.Paper.equals(mT))
			return; // only useful for paper
		if (bRecurse)
		{
			final VElement v = getLeaves(true);
			final int size = v.size();
			for (int i = 0; i < size; i++)
				((JDFMedia) v.get(i)).setThicknessFromWeight(bLocal, false);
		}
		else
		{
			final String w = bLocal ? getAttribute_KElement(AttributeName.WEIGHT) : getAttribute(AttributeName.WEIGHT);
			if (isWildCard(w))
				return; // no weight to use
			final String t = bLocal ? getAttribute_KElement(AttributeName.THICKNESS) : getAttribute(AttributeName.THICKNESS);
			if (!isWildCard(t))
				return; // no weight to use
			setThickness(getWeight() * 1.25); // assume average density of 0.8
			// g/cm^3
			// TODO improve calculation based on grade etc.
		}
	}

	/**
	 * Set attribute Dimension (in point)
	 *
	 * @param  value the value (in centimeter) to set the dimension to
	 */
	public void setDimensionCM(final JDFXYPair value)
	{
		final JDFXYPair xyp = new JDFXYPair(value); // don't change the original
		xyp.scale(72.0 / 2.54);
		setDimension(xyp);
	}

	/**
	 * Get attribute Dimension in centimeter
	 *
	 * @return JDFXYPair the dimension in centimeter
	 */
	public JDFXYPair getDimensionCM()
	{
		final JDFXYPair xyp = getDimension();
		if (xyp != null)
			xyp.scaleToCM();
		return xyp;
	}

	/**
	 * Set attribute Dimension (in point)
	 *
	 * @param   value  the value (in inch) to set the dimension to
	 */
	public void setDimensionInch(final JDFXYPair value)
	{
		final JDFXYPair xyp = new JDFXYPair(value); // don't change the original
		xyp.scale(72.0);
		setDimension(xyp);
	}

	/**
	 * Get attribute Dimension in inch
	 *
	 * @return JDFXYPair the dimension in inch
	 */
	public JDFXYPair getDimensionInch()
	{
		final JDFXYPair xyp = getDimension();
		if (xyp != null)
			xyp.scale(1.0 / 72.0);
		return xyp;
	}

	/**
	 * Get the ISO grade of the back side based on backCoatings
	 *
	 * @return 1-5: the grade of the back 0 if no grade value is specified
	 * note that front is always assumed to have a better coating
	 */
	public int getBackGrade()
	{
		final int frontGrade = getGrade();
		if (frontGrade == 0 || frontGrade >= 4)
			return frontGrade; // uncoated or web crap paper

		final EnumBackCoatings coatings = super.getBackCoatings();
		if (coatings == null)
			return frontGrade; // no back details
		if (EnumBackCoatings.None.equals(coatings))
			return 4; // uncoated
		if (EnumBackCoatings.Matte.equals(coatings))
			return 2; // matte

		return frontGrade;
	}

	@Override
	public boolean matches(final Object subset)
	{
		boolean matches = false;
		if (subset instanceof String)
		{
			final String subString = StringUtil.normalize((String) subset, true);
			matches = subString == null ? false : subString.equalsIgnoreCase(getProductID());
		}
		else if (subset instanceof JDFMedia)
		{
			final JDFMedia other = (JDFMedia) subset;
			final String productID = StringUtil.normalize(getProductID(), true);
			final String otherProductID = StringUtil.normalize(other.getProductID(), true);
			if (productID != null && otherProductID != null)
			{
				matches = matches(otherProductID);
			}
			else
			{
				matches = StringUtil.getDistance(getBrand(), other.getBrand(), true, true, true) == 0;
				matches = matches && StringUtil.getDistance(getMediaQuality(), other.getMediaQuality(), true, true, true) == 0;
				matches = matches
						&& StringUtil.getDistance(getAttribute(AttributeName.ISOPAPERSUBSTRATE), other.getAttribute(AttributeName.ISOPAPERSUBSTRATE), true, true, true) == 0;
				matches = matches && getGrade() == 0 || other.getGrade() == 0 || other.getGrade() == getGrade();
				matches = matches && getBackGrade() == 0 || other.getBackGrade() == 0 || other.getBackGrade() == getBackGrade();
				if (matches)
				{
					final JDFXYPair dim = getDimension();
					final JDFXYPair otherDim = other.getDimension();
					matches = dim == null || otherDim == null || dim.matches(otherDim, 5);
				}
			}
		}
		return matches;
	}

	/**
	 * return true if we are a media used for printing
	 * @return
	 */
	public boolean isComponentMedia()
	{
		final EnumMediaType typ = getMediaType();
		return EnumMediaType.Paper.equals(typ) || EnumMediaType.CorrugatedBoard.equals(typ) || EnumMediaType.SelfAdhesive.equals(typ) || EnumMediaType.Transparency.equals(typ)
				|| EnumMediaType.Vinyl.equals(typ);
	}
}