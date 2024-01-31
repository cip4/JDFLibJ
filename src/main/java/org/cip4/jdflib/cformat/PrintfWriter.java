/*
 * Copyright John E. Lloyd, 2000. All rights reserved. Permission
 * to use, copy, and modify, without fee, is granted for non-commercial
 * and research purposes, provided that this copyright notice appears
 * in all copies.
 *
 * This  software is distributed "as is", without any warranty, including
 * any implied warranty of merchantability or fitness for a particular
 * use. The authors assume no responsibility for, and shall not be liable
 * for, any special, indirect, or consequential damages, or any damages
 * whatsoever, arising out of or in connection with the use of this
 * software.
 */
package org.cip4.jdflib.cformat;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Writer class to output primitive types using C <tt>printf</tt> style formatting. For each primitive type (float, double, char, int, long, String), there is a <tt>printf</tt>
 * method which takes (as a first argument) either a <tt>printf</tt> style format string, or a PrintfFormat object. Using the latter can be more efficient because it permits an
 * application to prorate the overhead of parsing a format string.
 *
 * <p>
 * Because Java does not permit variable numbers of arguments, each <tt>printf</tt> methodName accepts only one primitive type, and the formats can correspondingly contain only one
 * conversion sequence.
 *
 * @see PrintfFormat
 * @see PrintfStream
 * @.author John E. Lloyd
 * @deprecated use standard java writers
 */
@Deprecated
public class PrintfWriter extends PrintWriter
{
	// ~ Constructors ///////////////////////////////////////////////////////////

	/**
	 * Creates a PrintfWriter, without automatic line flushing, from an existing OutputStream.
	 *
	 * @param out An output stream
	 */
	public PrintfWriter(final OutputStream pout)
	{
		super(pout);
	}

	/**
	 * Creates a PrintfWriter, without automatic line flushing, from an existing Writer.
	 *
	 * @param out A writer
	 */
	public PrintfWriter(final Writer pout)
	{
		super(pout);
	}

	/**
	 * Creates a PrintfWriter from an existing OutputStream.
	 *
	 * @param out An output stream
	 * @param autoFlush If true, specifies that output flushing will automatically occur when the println() methods are called.
	 */
	public PrintfWriter(final OutputStream pout, final boolean autoFlush)
	{
		super(pout, autoFlush);
	}

	/**
	 * Creates a PrintfWriter from an existing Writer.
	 *
	 * @param out A writer
	 * @param autoFlush If true, specifies that output flushing will automatically occur when the println() methods are called.
	 */
	public PrintfWriter(final Writer pout, final boolean autoFlush)
	{
		super(pout, autoFlush);
	}

	// ~ Methods ////////////////////////////////////////////////////////////////

	/**
	 * Prints a double in accordance with the supplied format string.
	 *
	 * @param fs Format string
	 * @param x Double to output
	 * @throws IllegalArgumentException Malformed format string
	 */
	public void printf(final String fs, final double x)
	{
		print(new PrintfFormat(fs).tostr(x));
	}

	/**
	 * Prints a float in accordance with the supplied format string.
	 *
	 * @param fs Format string
	 * @param x Float to output
	 * @throws IllegalArgumentException Malformed format string
	 */
	public void printf(final String fs, final float x)
	{
		print(new PrintfFormat(fs).tostr(x));
	}

	/**
	 * Prints a long in accordance with the supplied format string.
	 *
	 * @param fs Format string
	 * @param x Long to output
	 * @throws IllegalArgumentException Malformed format string
	 */
	public void printf(final String fs, final long x)
	{
		print(new PrintfFormat(fs).tostr(x));
	}

	/**
	 * Prints an int in accordance with the supplied format string.
	 *
	 * @param fs Format string
	 * @param x Int to output
	 * @throws IllegalArgumentException Malformed format string
	 */
	public void printf(final String fs, final int x)
	{
		print(new PrintfFormat(fs).tostr(x));
	}

	/**
	 * Prints a String in accordance with the supplied format string.
	 *
	 * @param fs Format string
	 * @param x String to output
	 * @throws IllegalArgumentException Malformed format string
	 */
	public void printf(final String fs, final String x)
	{
		print(new PrintfFormat(fs).tostr(x));
	}

	/**
	 * Prints a char in accordance with the supplied format string.
	 *
	 * @param fs Format string
	 * @param x Char to output
	 * @throws IllegalArgumentException Malformed format string
	 */
	public void printf(final String fs, final char x)
	{
		print(new PrintfFormat(fs).tostr(x));
	}

	/**
	 * Prints a double in accordance with the supplied PrintfFormat object.
	 *
	 * @param fmt Formatting object
	 * @param x Double to output
	 * @see PrintfFormat
	 */
	public void printf(final PrintfFormat fmt, final double x)
	{
		print(fmt.tostr(x));
	}

	/**
	 * Prints a float in accordance with the supplied PrintfFormat object.
	 *
	 * @param fmt Formatting object
	 * @param x Float to output
	 * @see PrintfFormat
	 */
	public void printf(final PrintfFormat fmt, final float x)
	{
		print(fmt.tostr(x));
	}

	/**
	 * Prints a long in accordance with the supplied PrintfFormat object.
	 *
	 * @param fmt Formatting object
	 * @param x Long to output
	 * @see PrintfFormat
	 */
	public void printf(final PrintfFormat fmt, final long x)
	{
		print(fmt.tostr(x));
	}

	/**
	 * Prints an int in accordance with the supplied PrintfFormat object.
	 *
	 * @param fmt Formatting object
	 * @param x Int to output
	 * @see PrintfFormat
	 */
	public void printf(final PrintfFormat fmt, final int x)
	{
		print(fmt.tostr(x));
	}

	/**
	 * Prints a String in accordance with the supplied PrintfFormat object.
	 *
	 * @param fmt Formatting object
	 * @param x String to output
	 * @see PrintfFormat
	 */
	public void printf(final PrintfFormat fmt, final String x)
	{
		print(fmt.tostr(x));
	}

	/**
	 * Prints a char in accordance with the supplied PrintfFormat object.
	 *
	 * @param fmt Formatting object
	 * @param x Char to output
	 * @see PrintfFormat
	 */
	public void printf(final PrintfFormat fmt, final char x)
	{
		print(fmt.tostr(x));
	}
}

///////////////////////////////////////////////////////////////////////////////
// END OF FILE.
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
// END OF FILE.
///////////////////////////////////////////////////////////////////////////////
