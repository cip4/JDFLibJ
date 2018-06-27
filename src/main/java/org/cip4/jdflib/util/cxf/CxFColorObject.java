/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.util.cxf;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.datatypes.JDFLabColor;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.datatypes.JDFRGBColor;
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.util.StringUtil;

public class CxFColorObject extends CxFBase
{

	/**
	 *
	 * @param root
	 */
	CxFColorObject(final KElement object)
	{
		super(object);
	}

	/**
	 *
	 * @return
	 */
	public JDFColor getJDFColor()
	{
		final JDFColor c = (JDFColor) JDFElement.createRoot(ElementName.COLOR);
		if (root != null)
		{
			c.copyAttribute(AttributeName.NAME, root);
			c.setLab(getCieLab());
			c.setsRGB(getRGBColor());
			c.setCMYK(getCMYKColor());
			c.setAttribute("Spectrum", getSpectrum().toString());
		}
		return c;
	}

	/**
	 *
	 * @return
	 */
	public JDFLabColor getCieLab()
	{
		final KElement cie = getXPathElement("ColorValues/ColorCIELab");
		if (cie == null)
			return null;
		final double l = StringUtil.parseDouble(cie.getXPathAttribute("L", null), 0);
		final double a = StringUtil.parseDouble(cie.getXPathAttribute("A", null), 0);
		final double b = StringUtil.parseDouble(cie.getXPathAttribute("B", null), 0);
		return new JDFLabColor(l, a, b);
	}

	/**
	 *
	 * @return
	 */
	public JDFRGBColor getRGBColor()
	{
		final KElement cie = getXPathElement("ColorValues/ColorSRGB");
		if (cie == null)
			return null;
		final double range = StringUtil.parseDouble(cie.getXPathAttribute("MaxRange", null), 255);
		final double r = StringUtil.parseDouble(cie.getXPathAttribute("R", null), 0);
		final double g = StringUtil.parseDouble(cie.getXPathAttribute("G", null), 0);
		final double b = StringUtil.parseDouble(cie.getXPathAttribute("B", null), 0);
		return new JDFRGBColor(r / range, g / range, b / range);
	}

	/**
	 *
	 * @return
	 */
	public JDFTransferFunction getSpectrum()
	{
		final KElement spectrum = getXPathElement("ColorValues/ReflectanceSpectrum");
		if (spectrum == null)
			return null;
		final double start = spectrum.getRealAttribute("StartWL", null, 0);
		if (start == 0)
			return null;
		final JDFNumberList l = JDFNumberList.createNumberList(spectrum.getText());
		if (l == null)
			return null;
		final JDFTransferFunction tf = new JDFTransferFunction();
		tf.set(start, 10, l.getDoubleVector());
		return tf;
	}

	/**
	 *
	 * @return
	 */
	public JDFCMYKColor getCMYKColor()
	{
		final KElement cie = getXPathElement("DeviceColorValues/ColorCMYK");
		if (cie == null)
			return null;
		final double range = StringUtil.parseDouble(cie.getXPathAttribute("MaxRange", null), 255);
		final double c = StringUtil.parseDouble(cie.getXPathAttribute("Cyan", null), 255);
		final double m = StringUtil.parseDouble(cie.getXPathAttribute("Magenta", null), 0);
		final double y = StringUtil.parseDouble(cie.getXPathAttribute("Yellow", null), 0);
		final double k = StringUtil.parseDouble(cie.getXPathAttribute("Black", null), 0);
		return new JDFCMYKColor(c / range, m / range, y / range, k / range);
	}
}
