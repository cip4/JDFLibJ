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
import java.io.PrintStream;

/**
 * PrintStream which outputs primitive types using C <tt>printf</tt> style formatting. For each primitive type (float,
 * double, char, int, long, String), there is a <tt>printf</tt> method which takes (as a first argument) either a
 * <tt>printf</tt> style format string, or a PrintfFormat object. Using the latter can be more efficient because it
 * permits an application to prorate the overhead of parsing a format string.
 * 
 * <p>
 * Because Java does not permit variable numbers of arguments, each <tt>printf</tt> methodName accepts only one
 * primitive type, and the format can correspondingly contain only one conversion sequence.
 * 
 * @see PrintfFormat
 * @see PrintfWriter
 * @.author John E. Lloyd
 */
public class PrintfStream extends PrintStream
{
	// ~ Constructors
	// ///////////////////////////////////////////////////////////

	/**
	 * Creates a PrintfStream, without automatic line flushing, from an existing OutputStream.
	 * 
	 * @param out An output stream
	 */
	public PrintfStream(OutputStream out)
	{
		super(out);
	}

	/**
	 * Creates a PrintfStream from an existing OutputStream.
	 * 
	 * @param out An output stream
	 * @param autoFlush If true, specifies that output flushing will automatically occur when the println() methods are
	 *            called, a byte array is written, or a new line character or byte is encountered in the output.
	 */
	public PrintfStream(OutputStream out, boolean autoFlush)
	{
		super(out, autoFlush);
	}

	// ~ Methods
	// ////////////////////////////////////////////////////////////////

	/**
	 * Prints a double in accordance with the supplied format string.
	 * 
	 * @param fs Format string
	 * @param x Double to output
	 * @throws IllegalArgumentException Malformed format string
	 */
	public void printf(String fs, double x)
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
	public void printf(String fs, float x)
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
	public void printf(String fs, long x)
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
	public void printf(String fs, int x)
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
	public void printf(String fs, String x)
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
	public void printf(String fs, char x)
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
	public void printf(PrintfFormat fmt, double x)
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
	public void printf(PrintfFormat fmt, float x)
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
	public void printf(PrintfFormat fmt, long x)
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
	public void printf(PrintfFormat fmt, int x)
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
	public void printf(PrintfFormat fmt, String x)
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
	public void printf(PrintfFormat fmt, char x)
	{
		print(fmt.tostr(x));
	}
}

// /////////////////////////////////////////////////////////////////////////////
// END OF FILE.
// /////////////////////////////////////////////////////////////////////////////

// /////////////////////////////////////////////////////////////////////////////
// END OF FILE.
// /////////////////////////////////////////////////////////////////////////////
