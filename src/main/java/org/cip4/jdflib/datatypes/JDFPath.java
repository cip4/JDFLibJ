/*
 * The CIP4 Software License, Version 1.0
 *
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFPath.java
 *
 */

package org.cip4.jdflib.datatypes;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;

/**
 * @author GonnermannJ
 *
 *         converts a PDFpath description into a GeneralPath-Object (Shape)
 */
public class JDFPath
{
	private String m_strPath;
	private final GeneralPath m_GPI = new GeneralPath();

	/*
	 * (non-Javadoc)
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString()
	{
		String path = JDFConstants.EMPTYSTRING;
		final AffineTransform at = new AffineTransform();
		at.setToIdentity();
		final PathIterator pi = getIterator(at);
		float seg[];

		int segNum = 0;
		while (!pi.isDone())
		{
			seg = new float[6];
			final int type = pi.currentSegment(seg);
			path += ("\nseg[" + segNum + "]= [");
			for (int j = 0; j < 5; j++)
			{
				path += (JDFConstants.BLANK + seg[j] + JDFConstants.COMMA);
			}
			path += (" type=[" + type + "] ]\n");
			pi.next();
			segNum++;
		}

		return "Path= [\n" + "\tbox=     " + m_GPI.getBounds() + "\n" + "\twinding= " + m_GPI.getWindingRule() + "\n" + "\tcurrent= " + m_GPI.getCurrentPoint() + "\n" + "segments:\n" + path + "]";
	}

	/**
	 * Method setPath.
	 *
	 * @param path
	 */
	private void setPath(final String path)
	{
		m_strPath = path;
	}

	/**
	 * Method getPath.
	 *
	 * @return String
	 */
	public String getPath()
	{
		return m_strPath;
	}

	/**
	 * Method JDFPath.
	 *
	 * @param pdfPath
	 */
	public JDFPath(final String pdfPath)
	{
		setPath(pdfPath);

		// split path
		final StringTokenizer st = new StringTokenizer(pdfPath);

		// fill m_GPI
		final Vector s = new Vector(4);
		double d = 0;
		String nt = JDFConstants.EMPTYSTRING;

		while (st.hasMoreTokens())
		{
			while (!(Double.valueOf(d)).equals(Double.valueOf(-1)))
			{
				if (st.hasMoreTokens())
				{
					nt = st.nextToken();
				}
				if (nt.startsWith("["))
				{
					nt = nt.substring(1);
				}
				if (nt.endsWith("]"))
				{
					nt = nt.substring(0, nt.length() - 1);

				}
				if (nt.equals(JDFConstants.EMPTYSTRING))
				{
					s.add(JDFConstants.EMPTYSTRING); // empty param eg []
					continue;
				}

				try
				{
					d = Double.parseDouble(nt);
					s.add(nt);
				}
				catch (final NumberFormatException e)
				{
					d = (-1);
				}
			}

			if (d == -1)
			{
				// Path Segment Operators
				if ("m".equals(nt))
				{
					addValues(nt, 2, s);
				}
				else if ("l".equals(nt))
				{
					addValues(nt, 2, s);
				}

				else if ("c".equals(nt))
				{
					addValues(nt, 6, s);
				}
				else if ("v".equals(nt))
				{
					addValues(nt, 4, s);
				}
				else if ("y".equals(nt))
				{
					addValues(nt, 4, s);
				}

				else if ("re".equals(nt))
				{
					addValues(nt, 4, s);
				}

				else if ("h".equals(nt))
				{
					addValues(nt, 0, s);
				}

				// Path Painting Operators
				else if ("n".equals(nt))
				{
					addValues(nt, 0, s);
				}

				else if ("S".equals(nt))
				{
					addValues(nt, 0, s);
				}
				else if ("s".equals(nt))
				{
					addValues(nt, 0, s);
				}

				else if ("f".equals(nt))
				{
					addValues(nt, 0, s);
				}
				else if ("F".equals(nt))
				{
					addValues(nt, 0, s);
				}
				else if ("f*".equals(nt))
				{
					addValues(nt, 0, s);
				}
				else if ("B".equals(nt))
				{
					addValues(nt, 0, s);
				}

				else if ("b".equals(nt))
				{
					addValues(nt, 0, s);
				}
				else if ("B*".equals(nt))
				{
					addValues(nt, 0, s);
				}
				else if ("b*".equals(nt))
				{
					addValues(nt, 0, s);
				}

				else if ("sh".equals(nt))
				{
					addValues(nt, 1, s);
				} // !!!! STRING-PARAM !!!

				// Path Clipping Operators
				else if ("W".equals(nt))
				{
					addValues(nt, 0, s);
				}
				else if ("W*".equals(nt))
				{
					addValues(nt, 0, s);
				}

				// reset
				d = 0;
				s.clear();

			} // if (d==-1){
		} // while ( st.hasMoreTokens() ) {
	} // public JDFPath(String pdfPath) {

	/**
	 * Method addValues.
	 *
	 * @param type
	 * @param paramNum
	 * @param s
	 * @return boolean
	 */
	private boolean addValues(final String type, final int paramNum, final Vector s)
	{
		if (s.size() != paramNum)
		{
			return false;
		}

		final float x[] = new float[paramNum];
		try
		{ // Parse Points in s
			for (int i = 0; i < paramNum; i++)
			{
				x[i] = Float.parseFloat(s.elementAt(i).toString());
			}
		}
		catch (final NumberFormatException e)
		{
			// nop
		}

		final float a[] = { 0, 0 };
		try
		{ // init actual point
			final Point2D currentPoint = m_GPI.getCurrentPoint();
			if (currentPoint != null)
			{
				a[0] = Float.parseFloat(JDFConstants.EMPTYSTRING + currentPoint.getX());
				a[1] = Float.parseFloat(JDFConstants.EMPTYSTRING + currentPoint.getY());
			}
		}
		catch (final NumberFormatException e)
		{
			if (!"m".equals(type))
			{
				a[0] = 0;
				a[1] = 0;
			}
		}

		try
		{ // set Path
			if ("m".equals(type))
			{
				m_GPI.moveTo(x[0], x[1]);
			}
			else if ("l".equals(type))
			{
				m_GPI.lineTo(x[0], x[1]);
			}

			else if ("c".equals(type))
			{
				m_GPI.curveTo(x[0], x[1], x[2], x[3], x[4], x[5]);
			}
			else if ("v".equals(type))
			{
				m_GPI.curveTo(a[0], a[1], x[0], x[1], x[2], x[3]);
			}
			else if ("y".equals(type))
			{
				m_GPI.curveTo(x[0], x[1], x[2], x[3], x[2], x[3]);
			}

			else if ("re".equals(type))
			{
				rectangle(x[0], x[1], x[2], x[3]);
			}

			else if ("h".equals(type))
			{
				m_GPI.closePath();
			}

			else
			{
				return false;
			}
		}
		catch (final Exception e)
		{
			// nop
		}

		return true;
	}

	/**
	 * Method rectangle.
	 *
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	private void rectangle(final float x, final float y, final float w, final float h)
	{
		final VString v = new VString();
		v.add(JDFConstants.EMPTYSTRING + x);
		v.add(JDFConstants.EMPTYSTRING + y);
		addValues("m", 2, v);
		v.clear();
		v.add(JDFConstants.EMPTYSTRING + (x + w));
		v.add(JDFConstants.EMPTYSTRING + y);
		addValues("l", 2, v);
		v.clear();
		v.add(JDFConstants.EMPTYSTRING + (x + w));
		v.add(JDFConstants.EMPTYSTRING + (y + h));
		addValues("l", 2, v);
		v.clear();
		v.add(JDFConstants.EMPTYSTRING + (x));
		v.add(JDFConstants.EMPTYSTRING + (y + h));
		addValues("l", 2, v);
		addValues("h", 0, v);
	}

	/**
	 * Method GetShape.
	 *
	 * @return Shape
	 */
	public Shape getShape()
	{
		return m_GPI;
	}

	/**
	 * Method GetIterator.
	 *
	 * @param at
	 * @return PathIterator
	 */
	public PathIterator getIterator(final AffineTransform at)
	{
		return m_GPI.getPathIterator(at);
	}

	/**
	 * Transforms the geometry of this path using the specified AffineTransform. The geometry is transformed in place.
	 *
	 * @param at the AffineTransform used to transform the path.
	 */
	public void transform(final AffineTransform at)
	{
		m_GPI.transform(at);
		setPath(calcPath(m_GPI));
	}

	/**
	 * Calculates the JDF string path from the awt shape.
	 *
	 * @param shape The shape.
	 *
	 * @return The path as string.
	 */
	private String calcPath(final Shape shape)
	{
		final StringBuffer buffer = new StringBuffer();

		if (null != shape)
		{
			final NumberFormat formatter = getFormatter();
			final PathIterator pi = shape.getPathIterator(new AffineTransform());
			final double seg[] = new double[6];
			int segNum = 0;

			while (!pi.isDone())
			{
				final int type = pi.currentSegment(seg);

				switch (type)
				{
				case PathIterator.SEG_LINETO:

					appendSegment(buffer, formatter, seg, 2, "l");
					break;

				case PathIterator.SEG_MOVETO:

					appendSegment(buffer, formatter, seg, 2, "m");
					break;

				case PathIterator.SEG_CUBICTO:
					appendSegment(buffer, formatter, seg, 6, "c");
					break;

				case PathIterator.SEG_QUADTO:

					appendSegment(buffer, formatter, seg, 4, "re");
					break;

				case PathIterator.SEG_CLOSE:

					buffer.append("h");
					break;

				default:
					// Should not appear!
					break;
				}
				pi.next();
				segNum++;
			}
		}

		return buffer.toString();
	}

	/**
	 * @param buffer
	 * @param formatter
	 * @param seg
	 * @param nLength
	 * @param strOrder
	 */
	private void appendSegment(final StringBuffer buffer, final NumberFormat formatter, final double[] seg, final int nLength, final String strOrder)
	{
		for (int i = 0; i < nLength; i++)
		{
			buffer.append(formatter.format(seg[i]));
			buffer.append(JDFConstants.BLANK);
		}
		buffer.append(strOrder);
		buffer.append(JDFConstants.BLANK);
	}

	/**
	 * Gets a formatter that is independend from any locale ( hopefully ).
	 *
	 * @return The formatter for the conversion of double to string.
	 */
	private NumberFormat getFormatter()
	{
		final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		symbols.setDecimalSeparator('.'); // only to be 100% sure!
		symbols.setGroupingSeparator(','); // only to be 100% sure!

		final String pattern = "###.######"; // A size of 6 for the mantissa
		// should be enough
		final DecimalFormat formatter = new DecimalFormat(pattern, symbols);

		return formatter;
	}

}