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

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;


/**
  * A Reader which implements C <tt>scanf</tt> functionality.
  * Once created, an application can read various primitive types
  * from the underlying stream using various <tt>scan</tt> methods
  * that implement <tt>scanf</tt> type input formatting.
  *
  * <p>
  * There are scan methods to read float, double, long, int, char,
  * char[], and String. The methods take as an argument either a
  * format string, a pre-allocated <tt>ScanfFormat</tt> object
  * which is created from a format string, or no argument (implying
  * a default format). The format string is modeled
  * after that accepted by the C <tt>scanf()</tt> methodName,
  * and is described in the documentation for the class
  * <tt>ScanfFormat</tt>.
  *
  * <p>
  * Because Java does not permit variable-length argument lists,
  * only one primitive type may be returned per method, and the
  * format used may contain only one conversion specification
  * (which must be appropriate to the type being scanned).
  *
  * <p>
  * Input errors in the underlying Reader result in a
  * <tt>java.io.IOException</tt> being thrown, while a
  * <tt>java.io.EOFException</tt> is thrown if the end of input is
  * reached before the scan can complete successfully. If the input does
  * not match the specified format, then a
  * <tt>ScanfMatchException</tt> is thrown. In the event of a match
  * error, scanning stops at the first character from which it can be
  * determined that the match will fail. This character is remembered by
  * the stream (see the discussion of the look-ahead character, below)
  * and will be the first character seen by the next <tt>scan</tt> or
  * <tt>read</tt> method which is called. Finally, an invalid
  * format string (or <tt>ScanfFormat</tt> object) will
  * trigger an <tt>InvalidArgumentException</tt>.
  *
  * <p>
  * The class keeps track of the current line number (accessible with
  * the methods <tt>getLineNumber</tt> and <tt>setLineNumber</tt>),
  * as well as the number of characters which have been consumed
  * (accesible with the methods <tt>getCharNumber</tt>
  * and <tt>setCharNumber</tt>).
  *
  * <p>
  * The class usually keeps one character of look-ahead which has been
  * read from the underlying reader but not yet consumed by any scan
  * method. If the underlying reader is used later in some other capacity,
  * this look-ahead character may have to be taken into account. If a
  * look-ahead character is actually being stored, the
  * <tt>lookAheadCharValid</tt> method will return <tt>true</tt>,
  * and the look-ahead character itself can then be obtained using the
  * <tt>getLookAheadChar</tt> method. The look-ahead character can
  * be cleared using the <tt>clearLookAheadChar</tt> method.
  */
public class ScanfReader extends Reader
{
    //~ Static fields/initializers /////////////////////////////////////////////

    private static String hexChars = "0123456789abcdefABCDEF";
    private static String octChars = "01234567";
    private static final int BUFSIZE = 1024;
    private static ScanfFormat defaultDoubleFmt = new ScanfFormat("%f");
    private static ScanfFormat defaultIntFmt = new ScanfFormat("%i");
    private static ScanfFormat defaultDecFmt = new ScanfFormat("%d");
    private static ScanfFormat defaultHexFmt = new ScanfFormat("%x");
    private static ScanfFormat defaultOctFmt = new ScanfFormat("%o");
    private static ScanfFormat defaultStringFmt = new ScanfFormat("%s");
    private static ScanfFormat defaultCharFmt = new ScanfFormat("%c");

    //~ Instance fields ////////////////////////////////////////////////////////

    private int bcnt;
    private final char[] buffer;
    private int charCnt = 0;
    private int curChar;
    private boolean curCharValid = false;
    private int lastChar;
    private int lineCnt = 1;

    private Reader reader = null;
    private boolean spacesCStandardFlag = true;

    //~ Constructors ///////////////////////////////////////////////////////////

    /**
      * Create a new ScanfReader from the given reader.
      *
      * @param reader         Underlying Reader
      */
    public ScanfReader(Reader in)
    {
        super();
        reader = in;
        curCharValid = false;
        charCnt = 0;
        lineCnt = 1;
        curChar = 0;
        lastChar = 0;

        // XXX XXX XXX fixed size buffer is a big hack
        buffer = new char[BUFSIZE];
        bcnt = 0;
    }

    //~ Methods ////////////////////////////////////////////////////////////////

    /**
      * Clears the look-ahead character.
      */
    public void clearLookAheadChar()
    {
        curCharValid = false;
    }

    /**
      * Closes the stream.
      *
      * @throws IOException An I/O error occurred
      */
    public void close() throws IOException
    {
        reader.close();
    }

    /**
      * Gets the current character number (equal to the
      * number of characters that have been consumed by
      * the stream).
      *
      * @return Current character number
      * @see ScanfReader#setCharNumber
      */
    public int getCharNumber()
    {
        return charCnt;
    }

    /**
      * Gets the current line number. The initial
      * value (when the Reader is created) is 1.
      * A new line is recorded upon reading a carriage return, a line
      * feed, or a carriage return immediately followed by a line feed.
      *
      * @return Current line number
      * @see ScanfReader#setLineNumber
      */
    public int getLineNumber()
    {
        return lineCnt;
    }

    /**
      * Returns the look-ahead character.
      *
      * @return Look-ahead character, -1 if EOF has been reached, or 0
      * if no look-ahead character is being stored.
      */
    public int getLookAheadChar()
    {
        if (curCharValid)
        {
            return curChar;
        }
        else
        {
            return 0;
        }
    }

    /**
      * Returns whether or not a look-ahead character
      * is currently begin stored.
      *
      * @return True if a look-ahead character is being stored.
      */
    public boolean lookAheadCharValid()
    {
        return curCharValid;
    }

    /**
      * Reads characters into a portion of a character array.
      * The method will block until input is available, an I/O
      * error occurs, or the end of the stream is reached.
      *
      * @param cbuf Buffer to write characters into
      * @param off Offset to start writing at
      * @param len Number of characters to read
      * @return The number of characters read, or -1 if the end of
      * the stream is reached.
      * @throws IOException An I/O error occurred
      */
    public int read(char[] cbuf, int off, int len) throws IOException
    {
        int n;
        int c;
        int n0 = 0;

        if (curCharValid)
        {
            consumeChar();

            if (curChar != -1)
            {
                cbuf[off++] = (char) curChar;
                len--;
                n0 = 1;
            }
            else
            {
                return -1;
            }
        }

        if (len > 0)
        {
            n = reader.read(cbuf, off, len);
        }
        else
        {
            return n0;
        }

        if (n == -1)
        {
            return -1;
        }
        else
        {
            for (int i = 0; i < n; i++)
            {
                c = cbuf[off + i];

                if ((c == '\r') || ((c == '\n') && (lastChar != '\r')))
                {
                    lineCnt++;
                }

                lastChar = c;
            }

            charCnt += n;

            return n + n0;
        }
    }

    /**
      * Scan and return a single character, using the default format
      * string "%c".
      *
      * @param s        Format string
      * @return Scanned character
      * @throws ScanfMatchException Input did not match format
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanChar(String)
      */
    public char scanChar() throws IOException, ScanfMatchException
    {
        char val = 0;

        try
        {
            val = scanChar(defaultCharFmt);
        }
        catch (IllegalArgumentException e)
        {
            // can't happen
            e.printStackTrace();
        }

        return val;
    }

    /**
      * Scan and return a single character.
      *
      * <p>
      * The format string <tt>s</tt> must have the form described
      * by the documentation for the class <tt>ScanfFormat</tt>,
      * and must contain the conversion character 'c' or '['. If the
      * conversion character is '[', then each character scanned must
      * match the sequence specified between the '[' and the closing
      * ']' (see the documentation for <tt>ScanfFormat</tt>).
      *
      * <p>
      * White space preceding the character is not skipped.
      *
      * @param s        Format string
      * @return Scanned character
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      */
    public char scanChar(String s) throws IOException, ScanfMatchException,
        IllegalArgumentException
    {
        return scanChar(new ScanfFormat(s));
    }

    /**
      * Scan and return a single character, using a pre-allocated
      * <tt>ScanfFormat</tt> object.
      * This saves the overhead of parsing the format from a string.
      *
      * @param fmt        Format object
      * @return Scanned character
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanChar(String)
      */
    public char scanChar(ScanfFormat fmt) throws IOException,
        IllegalArgumentException
    {
        char value = 0;

        checkTypeAndScanPrefix(fmt, "c[");
        initChar();

        if (curChar == -1)
        {
            throw new EOFException("EOF");
        }

        value = (char) curChar;

        if ((fmt.type == '[') && !fmt.matchChar(value))
        {
            throw new ScanfMatchException("Input char '" + value +
                "' does not match '[" + fmt.cmatch + "]'");
        }

        consumeAndReplaceChar();
        scanSuffix(fmt);

        return value;
    }

    /**
      * Scan and return a character array, whose size is determined
      * by the field width specified in the format string (with a
      * default width of 1 being assumed if no width is specified).
      *
      * <p>
      * The format string <tt>s</tt> must have the form described
      * by the documentation for the class <tt>ScanfFormat</tt>,
      * and must contain the conversion characters 'c' or '['. If the
      * conversion character is '[', then each character scanned must
      * match the sequence specified between the '[' and the closing
      * ']' (see the documentation for <tt>ScanfFormat</tt>).
      *
      * <p>
      * White space preceding the character sequence is not skipped.
      *
      * @param s        Format string
      * @return Scanned character array
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      */
    public char[] scanChars(String s) throws IOException, ScanfMatchException,
        IllegalArgumentException
    {
        return scanChars(new ScanfFormat(s));
    }

    /**
      * Scan and return a character array, using the default format
      * string "%c", with the field width (number of characters to
      * read) supplanted by the argument <tt>n</tt>.
      *
      * @param n        Number of characters to read
      * @return Scanned character array
      * @throws IllegalArgumentException <tt>n</tt> not a positive
      * number
      * @throws ScanfMatchException Input did not match format
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanChars(String)
      */
    public char[] scanChars(int n) throws IOException, ScanfMatchException,
        IllegalArgumentException
    {
        if (n <= 0)
        {
            throw new IllegalArgumentException("n is non-positive");
        }

        return scanChars(defaultCharFmt, n);
    }

    /**
      * Scan and return a character array, using a pre-allocated
      * <tt>ScanfFormat</tt> object.
      * This saves the overhead of parsing the format from a string.
      *
      * @param fmt        Format object
      * @return Scanned character array
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanChars(String)
      */
    public char[] scanChars(ScanfFormat fmt) throws IOException,
        IllegalArgumentException
    {
        return scanChars(fmt, fmt.width);
    }

    /**
      * Scan and return a signed decimal (long) integer, using the default format
      * string "%d".
      *
      * @param s        Format string
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanDec(String)
      */
    public long scanDec() throws IOException, ScanfMatchException
    {
        long val = 0;

        try
        {
            val = scanDec(defaultDecFmt, -1);
        }
        catch (IllegalArgumentException e)
        {
            // can't happen
            e.printStackTrace();
        }

        return val;
    }

    /**
      * Scan and return a signed decimal (long) integer.
      *
      * <p>
      * The format string <tt>s</tt> must have the form described
      * by the documentation for the class <tt>ScanfFormat</tt>,
      * and must contain the conversion character 'd'.
      * The integer itself must consist of an optional sign
      * ('+' or '-') followed by a sequence of digits. White space
      * preceding the number is skipped.
      *
      * @param s        Format string
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      */
    public long scanDec(String s) throws IOException, ScanfMatchException,
        IllegalArgumentException
    {
        return scanDec(new ScanfFormat(s));
    }

    /**
      * Scan and return a signed decimal (long) integer, using a pre-allocated
      * <tt>ScanfFormat</tt> object.
      * This saves the overhead of parsing the format from a string.
      *
      * @param fmt        Format object
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanDec(String)
      */
    public long scanDec(ScanfFormat fmt) throws IOException,
        IllegalArgumentException
    {
        return scanDec(fmt, fmt.width);
    }

    /**
      * Scan and return a double, using the default format string "%f".
      *
      * @return Scanned double value
      * @throws ScanfMatchException Input did not match format
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanDouble(String)
      */
    public double scanDouble() throws IOException, ScanfMatchException
    {
        double val = 0;

        try
        {
            val = scanDouble(defaultDoubleFmt);
        }
        catch (IllegalArgumentException e)
        {
            // can't happen
            e.printStackTrace();
        }

        return val;
    }

    /**
      * Scan and return a double.
      *
      * <p>
      * The format string <tt>s</tt> must have the form described
      * by the documentation for the class <tt>ScanfFormat</tt>,
      * and must contain the conversion character 'f'. The number itself
      * may consist of (a) an optional sign ('+' or '-'), (b) a sequence
      * of decimal digits, with an optional decimal point, (c) an
      * optional exponent ('e' or 'E'), which must by followed by an
      * optionally signed sequence of decimal digits. White space
      * immediately before the number is skipped.
      *
      * @param s        Format string
      * @return Scanned double value
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      */
    public double scanDouble(String s) throws IOException, ScanfMatchException,
        IllegalArgumentException
    {
        return scanDouble(new ScanfFormat(s));
    }

    /**
      * Scan and return a double, using a pre-allocated
      * <tt>ScanfFormat</tt> object.
      * This saves the overhead of parsing the format from a string.
      *
      * @param fmt        Format object
      * @return Scanned double value
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanDouble(String)
      */
    public double scanDouble(ScanfFormat fmt) throws IOException,
        ScanfMatchException, IllegalArgumentException
    {
        // parse [-][0-9]*[.][0-9]*[eE][-][0-9]*
        boolean hasDigits = false;

        boolean signed;
        double value = 0;
        int w;

        checkTypeAndScanPrefix(fmt, "f");

        if (curChar == -1)
        {
            throw new EOFException("EOF");
        }

        bcnt = 0;
        w = ((fmt.width == -1) ? 1000000000 : fmt.width);

        if (spacesCStandardFlag)
        {
            skipWhiteSpace();
        }
        else
        {
            int skippedSpaces = skipWhiteSpace(w);
            w -= skippedSpaces;
        }

        signed = (acceptChar('-', w) || acceptChar('+', w));

        if (acceptDigits(w))
        {
            hasDigits = true;
        }

        acceptChar('.', w);

        if (!hasDigits && ((bcnt == w) || !Character.isDigit((char) curChar)))
        {
            if (curCharValid && (curChar == -1))
            {
                throw new EOFException("EOF");
            }
            else
            {
                throw new ScanfMatchException(
                    "Malformed floating point number: no digits");
            }
        }

        acceptDigits(w);

        if (acceptChar('e', w) || acceptChar('E', w))
        {
            signed = (acceptChar('-', w) || acceptChar('+', w));

            if ((bcnt == w) || !Character.isDigit((char) curChar))
            {
                if (curCharValid && (curChar == -1))
                {
                    throw new EOFException("EOF");
                }
                else
                {
                    throw new ScanfMatchException(
                        "Malformed floating point number: no digits in exponent");
                }
            }

            acceptDigits(w);
        }

        try
        {
            value = Double.parseDouble(new String(buffer, 0, bcnt));
        }
        catch (NumberFormatException e)
        {
            throw new ScanfMatchException("Malformed floating point number");
        }

        scanSuffix(fmt);

        return value;
    }

    /**
      * Scan and return a float, using the default format string "%f".
      *
      * @return Scanned float value
      * @throws ScanfMatchException Input did not match format
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanDouble(String)
      */
    public float scanFloat() throws IOException, ScanfMatchException
    {
        return (float) scanDouble();
    }

    /**
      * Scan and return a float. The format string <tt>s</tt>
      * takes the same form as that described in the documentation
      * for <tt>scanDouble(String)</tt>.
      *
      * @param s        Format string
      * @return Scanned float value
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanDouble(String)
      */
    public float scanFloat(String s) throws IOException, ScanfMatchException,
        IllegalArgumentException
    {
        return (float) scanDouble(new ScanfFormat(s));
    }

    /**
      * Scan and return a float, using a pre-allocated
      * <tt>ScanfFormat</tt> object.
      * This saves the overhead of parsing the format from a string.
      *
      * @param fmt        Format object
      * @return Scanned float value
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanDouble(String)
      */
    public float scanFloat(ScanfFormat fmt) throws IOException,
        ScanfMatchException, IllegalArgumentException
    {
        return (float) scanDouble(fmt);
    }

    /**
      * Scan and return a hex (long) integer, using the default format
      * string "%x".
      *
      * @param s        Format string
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanHex(String)
      */
    public long scanHex() throws IOException, ScanfMatchException
    {
        long val = 0;

        try
        {
            val = scanHex(defaultHexFmt, -1);
        }
        catch (IllegalArgumentException e)
        {
            // can't happen
            e.printStackTrace();
        }

        return val;
    }

    /**
      * Scan and return a hex (long) integer.
      *
      * <p>
      * The format string <tt>s</tt> must have the form described
      * by the documentation for the class <tt>ScanfFormat</tt>,
      * and must contain the conversion character 'x'. The integer itself
      * must be formed from the characters [0-9a-fA-F], and white space
      * which immediately precedes it is skipped.
      *
      * @param s        Format string
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      */
    public long scanHex(String s) throws IOException, ScanfMatchException,
        IllegalArgumentException
    {
        return scanHex(new ScanfFormat(s));
    }

    /**
      * Scan and return a hex (long) integer, using a pre-allocated
      * <tt>ScanfFormat</tt> object.
      * This saves the overhead of parsing the format from a string.
      *
      * @param fmt        Format object
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanHex(String)
      */
    public long scanHex(ScanfFormat fmt) throws IOException,
        IllegalArgumentException
    {
        return scanHex(fmt, fmt.width);
    }

    /**
      * Scan and return a signed integer, using the default format
      * string "%i".
      *
      * @param s        Format string
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanInt(String)
      */
    public int scanInt() throws IOException, ScanfMatchException
    {
        return (int) scanLong();
    }

    /**
      * Scan and return a signed integer.
      *
      * <p>
      * The format string <tt>s</tt> must have the form described
      * by the documentation for the class <tt>ScanfFormat</tt>,
      * and must contain one of the conversion characters "doxi".
      *
      * <p>
      * Specifying the conversion characters 'd', 'o', or 'x' is
      * equivalent to calling (int versions of) <tt>scanDec</tt>,
      * <tt>scanOct</tt>, and <tt>scanHex</tt>, respectively.
      *
      * <p>
      * If the conversion character is 'i', then after an optional
      * sign ('+' or '-'), if the number begins with an
      * <tt>0x</tt>, then it is scanned as a hex number;
      * if it begins with an <tt>0</tt>, then it is scanned as an
      * octal number, and otherwise it is scanned as a decimal number.
      * White space preceding the number is skipped.
      *
      * @param s        Format string
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanDec(String)
      * @see ScanfReader#scanOct(String)
      * @see ScanfReader#scanHex(String)
      */
    public int scanInt(String s) throws IOException, ScanfMatchException,
        IllegalArgumentException
    {
        return (int) scanLong(s);
    }

    /**
      * Scan and return a signed integer, using a pre-allocated
      * <tt>ScanfFormat</tt> object.
      * This saves the overhead of parsing the format from a string.
      *
      * @param fmt        Format object
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanInt(String)
      */
    public int scanInt(ScanfFormat fmt) throws IOException,
        IllegalArgumentException
    {
        return (int) scanLong(fmt);
    }

    /**
      * Scan and return a signed (long) integer, using the default format
      * string "%i".
      *
      * @param s        Format string
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanInt(String)
      */
    public long scanLong() throws IOException, ScanfMatchException
    {
        long val = 0;

        try
        {
            val = scanLong(defaultIntFmt);
        }
        catch (IllegalArgumentException e)
        {
            // can't happen
            e.printStackTrace();
        }

        return val;
    }

    /**
      * Scan and return a signed (long) integer. Functionality
      * is identical to that for <tt>scanInt(String)</tt>.
      *
      * @param s        Format string
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfReader#scanInt(String)
      */
    public long scanLong(String s) throws IOException, ScanfMatchException,
        IllegalArgumentException
    {
        return scanLong(new ScanfFormat(s));
    }

    /**
      * Scan and return a signed (long) integer, using a pre-allocated
      * <tt>ScanfFormat</tt> object.
      *
      * @param fmt        Format object
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfReader#scanInt(String)
      */
    public long scanLong(ScanfFormat fmt) throws IOException,
        IllegalArgumentException
    {
        if (fmt.type == 'd')
        {
            return scanDec(fmt);
        }
        else if (fmt.type == 'x')
        {
            return scanHex(fmt);
        }
        else if (fmt.type == 'o')
        {
            return scanOct(fmt);
        }
        else // fmt.type == 'i';
        {
            long val = 0;
            int sign = 1;
            int ccnt = 0;
            int width = fmt.width;

            if (width == -1)
            {
                width = 1000000000;
            }

            checkTypeAndScanPrefix(fmt, "i");

            if (spacesCStandardFlag)
            {
                skipWhiteSpace();
            }
            else
            {
                int skippedSpaces = skipWhiteSpace(width);
                width -= skippedSpaces;
            }

            if ((curChar == '-') || (curChar == '+'))
            {
                if (width == 1)
                {
                    throw new ScanfMatchException("Malformed integer");
                }

                if (curChar == '-')
                {
                    sign = -1;
                }

                consumeAndReplaceChar();
                ccnt++;
            }

            if (curChar == -1)
            {
                throw new EOFException("EOF");
            }

            if (curChar == '0')
            {
                consumeAndReplaceChar();
                ccnt++;

                if (ccnt == width)
                {
                    val = 0;
                }
                else
                {
                    if ((curChar == 'x') || (curChar == 'X'))
                    {
                        if ((ccnt + 1) == width)
                        {
                            throw new ScanfMatchException(
                                "Malformed hex integer");
                        }
                        else
                        {
                            consumeAndReplaceChar();
                            ccnt++;

                            if (Character.isWhitespace((char) curChar))
                            {
                                throw new ScanfMatchException(
                                    "Malformed hex integer");
                            }
                            else
                            {
                                val = scanHex(defaultHexFmt, width - ccnt);
                            }
                        }
                    }
                    else
                    {
                        if (Character.isWhitespace((char) curChar))
                        {
                            val = 0;
                        }
                        else
                        {
                            val = scanOct(defaultOctFmt, width - ccnt);
                        }
                    }
                }
            }
            else
            { // scan unsigned decimal integer

                int i = 0;
                val = 0;

                if (!Character.isDigit((char) curChar))
                {
                    throw new ScanfMatchException("Malformed decimal integer");
                }

                while (Character.isDigit((char) curChar) &&
                        (i < (width - ccnt)))
                {
                    val = (val * 10) + (curChar - '0');
                    consumeAndReplaceChar();
                    i++;
                }
            }

            scanSuffix(fmt);

            return sign * val;
        }
    }

    /**
      * Scan and return an octal (long) integer, using the default format
      * string "%o".
      *
      * @param s        Format string
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanOct(String)
      */
    public long scanOct() throws IOException, ScanfMatchException
    {
        long val = 0;

        try
        {
            val = scanOct(defaultOctFmt, -1);
        }
        catch (IllegalArgumentException e)
        {
            // can't happen
            e.printStackTrace();
        }

        return val;
    }

    /**
      * Scan and return an octal (long) integer.
      *
      * <p>
      * The format string <tt>s</tt> must have the form described
      * by the documentation for the class <tt>ScanfFormat</tt>,
      * and must contain the conversion character 'o'. The integer itself
      * must be composed of the digits [0-7], and white space which
      * immediately precedes it is skipped.
      *
      * @param s        Format string
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      */
    public long scanOct(String s) throws IOException, ScanfMatchException,
        IllegalArgumentException
    {
        return scanOct(new ScanfFormat(s));
    }

    /**
      * Scan and return an octal (long) integer, using a pre-allocated
      * <tt>ScanfFormat</tt> object.
      * This saves the overhead of parsing the format from a string.
      *
      * @param fmt        Format object
      * @return Scanned integer
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanOct(String)
      */
    public long scanOct(ScanfFormat fmt) throws IOException,
        IllegalArgumentException
    {
        return scanOct(fmt, fmt.width);
    }

    /**
      * Scan and return a <tt>String</tt>, using the default format
      * string "%s".
      *
      * @param s        Format string
      * @return Scanned <tt>String</tt>
      * @throws ScanfMatchException Input did not match format
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanString(String)
      */
    public String scanString() throws IOException, ScanfMatchException
    {
        String val = null;

        try
        {
            val = scanString(defaultStringFmt);
        }
        catch (IllegalArgumentException e)
        {
            // can't happen
            e.printStackTrace();
        }

        return val;
    }

    /**
      * Scan and return a <tt>String</tt>.
      *
      * <p>
      * The format string <tt>s</tt> must have the form described
      * by the documentation for the class <tt>ScanfFormat</tt>,
      * and must contain the conversion character 's'. The string returned
      * corresponds to the next non-white-space sequence of characters
      * found in the input, with preceding white space skipped.
      *
      * @param s        Format string
      * @return Scanned <tt>String</tt>
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      */
    public String scanString(String s) throws IOException, ScanfMatchException,
        IllegalArgumentException
    {
        return scanString(new ScanfFormat(s));
    }

    /**
      * Scan and return a <tt>String</tt>, using a pre-allocated
      * <tt>ScanfFormat</tt> object.
      * This saves the overhead of parsing the format from a string.
      *
      * @param fmt        Format object
      * @return Scanned <tt>String</tt>
      * @throws ScanfMatchException Input did not match format
      * @throws IllegalArgumentException Error in format specification
      * @throws java.io.EOFException End of file
      * @throws java.io.IOException Other input error
      * @see ScanfFormat
      * @see ScanfReader#scanString(String)
      */
    public String scanString(ScanfFormat fmt) throws IOException,
        IllegalArgumentException
    {
        int blimit = BUFSIZE;

        if ((fmt.width != -1) && (fmt.width < blimit))
        {
            blimit = fmt.width;
        }

        checkTypeAndScanPrefix(fmt, "s");

        if (spacesCStandardFlag)
        {
            skipWhiteSpace();
        }
        else
        {
            int skippedSpaces = skipWhiteSpace(blimit);
            blimit -= skippedSpaces;
        }

        if (curChar == -1)
        {
            throw new EOFException("EOF");
        }

        bcnt = 0;

        while (!Character.isWhitespace((char) curChar) && (curChar != -1) &&
                (bcnt < blimit))
        {
            buffer[bcnt++] = (char) curChar;
            consumeAndReplaceChar();
        }

        scanSuffix(fmt);

        return new String(buffer, 0, bcnt);
    }

    /**
      * Sets the current character number.
      *
      * @param n New character number
      * @see ScanfReader#getCharNumber
      */
    public void setCharNumber(int n)
    {
        charCnt = n;
    }

    /**
      * Sets the current line number.
      *
      * @param n New line number
      * @see ScanfReader#setLineNumber
      */
    public void setLineNumber(int n)
    {
        lineCnt = n;
    }

    /**
     * White spaces are skipped at the beginning of a line if
     * flag is <tt>true</tt> otherwise spaces
     * are counted as valid characters.
     */
    public boolean useCstandard()
    {
        return spacesCStandardFlag;
    }

    /**
     * White spaces are skipped at the beginning of a line if
     * <tt>flag</tt> is <tt>true</tt> otherwise spaces
     * are counted as valid characters.
     */
    public void useCstandard(boolean flag)
    {
        spacesCStandardFlag = flag;
    }

    private final boolean acceptChar(char c, int width) throws IOException
    {
        if ((curChar == c) && (bcnt < width))
        {
            buffer[bcnt++] = (char) curChar;

            if (bcnt < width)
            {
                consumeAndReplaceChar();
            }
            else
            {
                consumeChar();
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    private final boolean acceptDigits(int width) throws IOException
    {
        boolean matched = false;

        while (Character.isDigit((char) curChar) && (bcnt < width))
        {
            buffer[bcnt++] = (char) curChar;
            matched = true;

            if (bcnt < width)
            {
                consumeAndReplaceChar();
            }
            else
            {
                consumeChar();
            }
        }

        return matched;
    }

    private final void checkTypeAndScanPrefix(ScanfFormat fmt, String type)
        throws IOException, IllegalArgumentException
    {
        if (fmt.type == -1)
        {
            throw new IllegalArgumentException("No conversion character");
        }

        if (type.indexOf(fmt.type) == -1)
        {
            throw new IllegalArgumentException(
                "Illegal conversion character '" + (char) fmt.type + "'");
        }

        if (fmt.prefix != null)
        {
            matchString(fmt.prefix);
        }
    }

    private final void consumeAndReplaceChar() throws IOException
    {
        if (curChar != -1)
        {
            charCnt++;

            if ((curChar == '\r') || ((curChar == '\n') && (lastChar != '\r')))
            {
                lineCnt++;
            }
        }

        lastChar = curChar;
        curChar = reader.read();
    }

    private final void consumeChar() throws IOException
    {
        if (curChar != -1)
        {
            charCnt++;

            if ((curChar == '\r') || ((curChar == '\n') && (lastChar != '\r')))
            {
                lineCnt++;
            }
        }

        lastChar = curChar;
        curCharValid = false;
    }

    private final void initChar() throws IOException
    {
        if (!curCharValid)
        {
            curChar = reader.read();
            curCharValid = true;
        }

        //         charCnt = 0;
    }

    private void matchString(String s) throws IOException, ScanfMatchException
    {
        initChar();

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (curChar == -1)
            {
                throw new EOFException("EOF");
            }

            if (Character.isWhitespace(c))
            {
                if (skipWhiteSpace() == false)
                //             { if (skipWhiteSpace(s.length()) == 0)
                {
                    throw new ScanfMatchException(
                        "No white space to match white space in format");
                }
            }
            else
            {
                if (curChar != c)
                {
                    throw new ScanfMatchException("Char '" + (char) curChar +
                        "' does not match char '" + c + "' in format");
                }

                consumeAndReplaceChar();
            }
        }
    }

    /**
      * Implementing methodName for scanChars.
      *
      * @see ScanfReader#scanChars(String)
      */
    private char[] scanChars(ScanfFormat fmt, int w) throws IOException,
        IllegalArgumentException
    {
        if (w == -1)
        {
            w = 1;
        }

        char[] value = new char[w];
        checkTypeAndScanPrefix(fmt, "c[");
        initChar();

        if (curChar == -1)
        {
            throw new EOFException("EOF");
        }

        for (int i = 0; i < w; i++)
        {
            value[i] = (char) curChar;

            if ((fmt.type == '[') && !fmt.matchChar(value[i]))
            {
                throw new ScanfMatchException("Input char '" + value[i] +
                    "' does not match '[" + fmt.cmatch + "]'");
            }

            consumeAndReplaceChar();
        }

        scanSuffix(fmt);

        return value;
    }

    /**
      * Implementing methodName for scanDec.
      *
      * @see ScanfReader#scanDec(String)
      */
    private long scanDec(ScanfFormat fmt, int width) throws IOException,
        IllegalArgumentException
    {
        if (width == -1)
        {
            width = 1000000000;
        }

        long val;
        int i;

        boolean negate = false;

        checkTypeAndScanPrefix(fmt, "d");

        if (spacesCStandardFlag)
        {
            skipWhiteSpace();
        }
        else
        {
            int skippedSpaces = skipWhiteSpace(width);
            width -= skippedSpaces;
        }

        if ((curChar == '-') || (curChar == '+'))
        {
            negate = (curChar == '-');
            consumeAndReplaceChar();
        }

        if (curChar == -1)
        {
            throw new EOFException("EOF");
        }

        if (!Character.isDigit((char) curChar))
        {
            throw new ScanfMatchException("Malformed decimal integer");
        }

        val = 0;
        i = 0;

        while ((Character.isDigit((char) curChar)) && (i < width))
        {
            val = (val * 10) + (curChar - '0');
            consumeAndReplaceChar();
            i++;
        }

        if (negate)
        {
            val = -val;
        }

        scanSuffix(fmt);

        return val;
    }

    /**
      * Implementing methodName for scanHex.
      *
      * @see ScanfReader#scanHex(String)
      */
    private long scanHex(ScanfFormat fmt, int width) throws IOException,
        IllegalArgumentException
    {
        if (width == -1)
        {
            width = 1000000000;
        }

        long val;
        int k;
        int i;

        checkTypeAndScanPrefix(fmt, "x");

        if (spacesCStandardFlag)
        {
            skipWhiteSpace();
        }
        else
        {
            int skippedSpaces = skipWhiteSpace(width);
            width -= skippedSpaces;
        }

        if (curChar == -1)
        {
            throw new EOFException("EOF");
        }

        if (hexChars.indexOf(curChar) == -1)
        {
            throw new ScanfMatchException("Malformed hex integer");
        }

        val = 0;
        i = 0;

        while (((k = hexChars.indexOf(curChar)) != -1) && (i < width))
        {
            if (k > 15)
            {
                k -= 6;
            }

            val = (val * 16) + k;
            consumeAndReplaceChar();
            i++;
        }

        scanSuffix(fmt);

        return val;
    }

    /**
      * Implementing methodName for scanOct.
      *
      * @see ScanfReader#scanOct(String)
      */
    private long scanOct(ScanfFormat fmt, int width) throws IOException,
        IllegalArgumentException
    {
        if (width == -1)
        {
            width = 1000000000;
        }

        long val;
        int k;
        int i;

        checkTypeAndScanPrefix(fmt, "o");

        if (spacesCStandardFlag)
        {
            skipWhiteSpace();
        }
        else
        {
            int skippedSpaces = skipWhiteSpace(width);
            width -= skippedSpaces;
        }

        if (curChar == -1)
        {
            throw new EOFException("EOF");
        }

        if (octChars.indexOf(curChar) == -1)
        {
            throw new ScanfMatchException("Malformed octal integer");
        }

        val = 0;
        i = 0;

        while (((k = octChars.indexOf(curChar)) != -1) && (i < width))
        {
            val = (val * 8) + k;
            consumeAndReplaceChar();
            i++;
        }

        scanSuffix(fmt);

        return val;
    }

    private final void scanPrefix(ScanfFormat fmt) throws IOException
    {
        if (fmt.prefix != null)
        {
            matchString(fmt.prefix);
        }
    }

    private final void scanSuffix(ScanfFormat fmt) throws IOException
    {
        if (fmt.suffix != null)
        {
            matchString(fmt.suffix);
        }
    }

    /**
      * Skip white spacew and count line numbers.
      */
    private boolean skipWhiteSpace() throws IOException
    {
        boolean encounterdWhiteSpace = false;

        initChar();

        while (Character.isWhitespace((char) curChar))
        {
            encounterdWhiteSpace = true;
            consumeAndReplaceChar();
        }

        return encounterdWhiteSpace ? true : false;
    }

    /**
      * Skip white spacew and count line numbers.
      *
      * @param limit the maximum of the number that will be skipped
      * @return the number of skipped white spaces
      */
    private int skipWhiteSpace(int limit) throws IOException
    {
        initChar();

        int spaces = 0;

        while (Character.isWhitespace((char) curChar) && (spaces <= limit))
        {
            spaces++;
            consumeAndReplaceChar();
        }

        return spaces;
    }
}

///////////////////////////////////////////////////////////////////////////////
//  END OF FILE.
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
//  END OF FILE.
///////////////////////////////////////////////////////////////////////////////
