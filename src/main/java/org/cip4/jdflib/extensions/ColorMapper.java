/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

package org.cip4.jdflib.extensions;

import java.util.HashSet;
import java.util.Set;

import org.cip4.jdflib.util.StringUtil;

/**
 * class to generate named colors from RGB or JDF named colors
 *
 * @author rainer prosi
 *
 *         TODO RGB matching TODO JDF enum matching
 */
public class ColorMapper
{
	private static ColorMapper theMapper = new ColorMapper();
	private final Set<String> myColors;

	private ColorMapper()
	{
		myColors = getColors();
	}

	/**
	 *
	 * @return
	 */
	Set<String> getColors()
	{
		final Set<String> colors = new HashSet<>();

		colors.add("aliceblue");

		colors.add("antiquewhite");

		colors.add("aqua");

		colors.add("aquamarine");

		colors.add("azure");

		colors.add("beige");

		colors.add("bisque");

		colors.add("black");

		colors.add("blanchedalmond");

		colors.add("blue");

		colors.add("blueviolet");

		colors.add("brown");

		colors.add("burlywood");

		colors.add("cadetblue");

		colors.add("chartreuse");

		colors.add("chocolate");

		colors.add("coral");

		colors.add("cornflowerblue");

		colors.add("cornsilk");

		colors.add("crimson");

		colors.add("cyan");

		colors.add("darkblue");

		colors.add("darkcyan");

		colors.add("darkgoldenrod");

		colors.add("darkgray");

		colors.add("darkgreen");

		colors.add("darkgrey");

		colors.add("darkkhaki");

		colors.add("darkmagenta");

		colors.add("darkolivegreen");

		colors.add("darkorange");

		colors.add("darkorchid");

		colors.add("darkred");

		colors.add("darksalmon");

		colors.add("darkseagreen");

		colors.add("darkslateblue");

		colors.add("darkslategray");

		colors.add("darkslategrey");

		colors.add("darkturquoise");

		colors.add("darkviolet");

		colors.add("deeppink");

		colors.add("deepskyblue");

		colors.add("dimgray");

		colors.add("dimgrey");

		colors.add("dodgerblue");

		colors.add("firebrick");

		colors.add("floralwhite");

		colors.add("forestgreen");

		colors.add("fuchsia");

		colors.add("gainsboro");

		colors.add("ghostwhite");

		colors.add("gold");

		colors.add("goldenrod");

		colors.add("gray");

		colors.add("grey");

		colors.add("green");

		colors.add("greenyellow");

		colors.add("honeydew");

		colors.add("hotpink");

		colors.add("indianred");

		colors.add("indigo");

		colors.add("ivory");

		colors.add("khaki");

		colors.add("lavender");

		colors.add("lavenderblush");

		colors.add("lawngreen");

		colors.add("lemonchiffon");

		colors.add("lightblue");

		colors.add("lightcoral");

		colors.add("lightcyan");

		colors.add("lightgoldenrodyellow");

		colors.add("lightgray");

		colors.add("lightgreen");

		colors.add("lightgrey");

		colors.add("lightpink");

		colors.add("lightsalmon");

		colors.add("lightseagreen");

		colors.add("lightskyblue");

		colors.add("lightslategray");

		colors.add("lightslategrey");

		colors.add("lightsteelblue");

		colors.add("lightyellow");

		colors.add("lime");

		colors.add("limegreen");

		colors.add("linen");

		colors.add("magenta");

		colors.add("maroon");

		colors.add("mediumaquamarine");

		colors.add("mediumblue");

		colors.add("mediumorchid");

		colors.add("mediumpurple");

		colors.add("mediumseagreen");

		colors.add("mediumslateblue");

		colors.add("mediumspringgreen");

		colors.add("mediumturquoise");

		colors.add("mediumvioletred");

		colors.add("midnightblue");

		colors.add("mintcream");

		colors.add("mistyrose");

		colors.add("moccasin");

		colors.add("navajowhite");

		colors.add("navy");

		colors.add("oldlace");

		colors.add("olive");

		colors.add("olivedrab");

		colors.add("orange");

		colors.add("orangered");

		colors.add("orchid");

		colors.add("palegoldenrod");

		colors.add("palegreen");

		colors.add("paleturquoise");

		colors.add("palevioletred");

		colors.add("papayawhip");

		colors.add("peachpuff");

		colors.add("peru");

		colors.add("pink");

		colors.add("plum");

		colors.add("powderblue");

		colors.add("purple");

		colors.add("red");

		colors.add("rosybrown");

		colors.add("royalblue");

		colors.add("saddlebrown");

		colors.add("salmon");

		colors.add("sandybrown");

		colors.add("seagreen");

		colors.add("seashell");

		colors.add("sienna");

		colors.add("silver");

		colors.add("skyblue");

		colors.add("slateblue");

		colors.add("slategray");

		colors.add("slategrey");

		colors.add("snow");

		colors.add("springgreen");

		colors.add("steelblue");

		colors.add("tan");

		colors.add("teal");

		colors.add("thistle");

		colors.add("tomato");

		colors.add("turquoise");

		colors.add("violet");

		colors.add("wheat");

		colors.add("white");

		colors.add("whitesmoke");

		colors.add("yellow");

		colors.add("yellowgreen");
		return colors;
	}

	public static String getMatchingColor(final String color)
	{
		return theMapper.getMatchingColorImpl(color);
	}

	String getMatchingColorImpl(final String color)
	{
		if (StringUtil.isEmpty(color))
			return null;
		if (myColors.contains(color.toLowerCase()))
			return color.toLowerCase();
		return null;
	}
}
