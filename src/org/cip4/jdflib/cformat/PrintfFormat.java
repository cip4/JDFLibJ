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

/**
 * Object for formatting output in the same way as the
 * C <tt>printf</tt> methodName.
 *
 * <p>
 * A <tt>printf</tt> style format string is specified in the
 * constructor. Once instantiated, the <tt>tostr</tt> methods of this
 * class may be used to convert primitives types (float, double, char,
 * int, long, String) into Strings. Alternatively, instances of this
 * class may be passed as arguments to the <tt>printf</tt> methods
 * of the <tt>PrintfWriter</tt> or <tt>PrintfStream</tt> classes.
 *
 * <p>
 * Examples:
 * <pre>
 *   double theta1 = 45.0;
 *   double theta2 = 85.0;
 *   PrintfFormat fmt = new PrintfFormat ("%7.2f\n");
 *
 *   System.out.println ("theta1=" + fmt.tostr(theta1) +
 *                       "theta2=" + fmt.tostr(theta2));
 *
 *   PrintfStream pfw = new PrintfStream (System.out, true);
 *   pfw.print ("theta1=");
 *   pfw.printf (fmt, theta1);
 *   pfw.print ("theta2=");
 *   pfw.printf (fmt, theta2);
 * </pre>
 *
 * @see PrintfWriter
 * @see PrintfStream
 * @.author John E. Lloyd
 */
public class PrintfFormat
{
    //~ Static fields/initializers /////////////////////////////////////////////

    private static char[] ddigits =
    {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static char[] xdigits =
    {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
        'd', 'e', 'f'
    };
    private static char[] Xdigits =
    {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
        'D', 'E', 'F'
    };

    //~ Instance fields ////////////////////////////////////////////////////////

    boolean addBlank = false;
    boolean addSign = false;
    boolean alternate = false;
    boolean leftAdjust = false;
    int prec = -1;

    String prefix = null;
    String suffix = null;
    char type = 0;
    int width = 0;
    boolean zeropad = false;
    private DecDouble dd;
    private int idx;
    private OutBuffer output;
    private String validTypes = "diouxXeEfFgGaAcs";

    //~ Constructors ///////////////////////////////////////////////////////////

    /**
     * Creates a new instance of PrintfFormat from the supplied
     * format string. The structure of the format string is described
     * in the documentation for the <tt>set</tt> method.
     *
     * @param fmt Format string
     * @throws IllegalArgumentException Malformed format string
     * @see PrintfFormat#set
     */
    public PrintfFormat(String fmt) throws IllegalArgumentException
    {
        output = new OutBuffer(1024);
        set(fmt);
    }

    //~ Methods ////////////////////////////////////////////////////////////////

    /**
     * Gets the prefix string associated with the format.
     * The prefix string is that part of the format
     * that appears before the conversion.
     * @return Prefix string
     * @see PrintfFormat#setPrefix
     */
    public String getPrefix()
    {
        return prefix;
    }

    /**
     * Gets the suffix string associated with the format.
     * The suffix string is that part of the format
     * that appears after the conversion.
     *
     * @return Suffix string
     * @see PrintfFormat#setSuffix
     */
    public String getSuffix()
    {
        return suffix;
    }

    /**
     * Sets the format characteristics according to the supplied
     * String.
     *
     * <p>
     * The format string has the same form as the one used by the C
     * <tt>printf</tt> methodName, except that only one conversion
     * sequence may be specified (because routines which
     * use PrintfFormat each convert only one object).
     *
     * <p>
     * The format string consists of an optional <em>prefix</em>
     * of regular characters, followed by a conversion sequence,
     * followed by an optional <em>suffix</em> of regular characters.
     *
     * <p>
     * The conversion sequence is introduced by a '%' character, and
     * is followed by any number of optional <em>flag</em> characters,
     * an optional unsigned decimal integer specifying a <em>field
     * width</em>, another optional unsigned decimal integer (preceded
     * by a '.' character) specifying a <em>precision</em>, and
     * finally a <tt>conversion character</tt>. To incorporate
     * a '%' character into either the prefix or suffix, one should
     * specify the sequence "%%".
     *
     * The allowed flag characters are:
     *
     * <dl>
     * <dt> #
     * <dd> The value is converted into an "alternate" form.
     *      For 'o' conversions, the output is always prefixed with a
     *      '0'. For 'x' and 'X' conversions, the output is prefixed
     *      with "0x" or "0X", respectively. For 'a', 'A', 'e', 'E',
     *      'f', 'g', and 'G' conversions, the result will always
     *      contain a decimal point. For 'g' and 'G' conversions,
     *      trailing zeros are not removed. There is no effect for
     *      other conversions.
     * <dt> 0
     * <dd> Use '0' to pad the field on the left, instead of blanks.
     *      If the conversion is 'd', 'i', 'o', 'u', 'x', or 'X',
     *      and a precision is given, then this flag is ignored.
     * <dt> -
     * <dd> The output is aligned with the left of the field
     *      boundary, and padded on the right with blanks.
     *      This flag overrides the '0' flag.
     * <dt> ' '
     * <dd> Leave a blank before a positive number produced by a
     *      signed conversion.
     * <dt> +
     * <dd> A '+' sign is placed before non-negative numbers produced
     *      by a signed conversion. This flag overrides the ' ' flag.
     * </dl>
     * <p>
     * The conversion character is one of:
     *
     * <dt> d,i
     * <dd> The integer argument is output as a signed decimal number.
     *      If a precision is given, it describes the minimum number
     *      of digits that must appear (default 1). If the precision
     *      exceeds the number of digits that would normally appear,
     *      the output is padded on the left with zeros. When 0 is
     *      printed with precision 0, the result is empty.
     * <dt> o,u,x,X
     * <dd> The integer argument is output as an unsigned number in
     *      either octal ('o'), decimal ('u'), or hexadecimal ('x' or
     *      'X'). The digits "abcdef" are used for 'x', and "ABCDEF"
     *      are used for 'X'. If a precision is given, it describes
     *      the minimum number of digits that must appear (default 1).
     *      If the precision exceeds the number of digits that would
     *      normally appear, the output is padded on the left with
     *      zeros. When 0 is printed with precision 0, the result is
     *      empty.
     * <dt> e,E
     * <dd> The floating point argument is output in the exponential
     *      form <tt>[-]d.ddde+dd</tt>, with the number of digits
     *      after the decimal point given by the precision. The
     *      default precision value is 6. No decimal point is output
     *      if the precision is 0. Conversion 'E' causes 'E' to be
     *      used as an exponent instead of 'e'. The exponent is
     *      always at least two characters.
     * <dt> f
     * <dd> The floating point argument is output in the form
     *      <tt>[-]ddd.ddd</tt>, with the number of digits
     *      after the decimal point given by the precision. The
     *      default precision value is 6. No decimal point is output
     *      if the precision is 0. If a decimal point appears, at
     *      least one digit appears before it.
     * <dt> g,G
     * <dd> The floating point argument is output in either the 'f'
     *      or 'e' style (or 'E' style of 'G' conversions). The
     *      precision gives the number of significant digits, with a
     *      default value of 6. Style 'e' is used if the resulting
     *      exponent is less than -4 or greater than or equal to the
     *      precision. Trailing zeros are removed from the fractional
     *      part of the result, and a decimal point appears if it is
     *      followed by at least one digit.
     * <dt> a,A
     * <dd> The floating point argument is output in the hexadecimal
     *      floating point form <tt>[-]0xh.hhhp+dd</tt>. The
     *      exponent is a decimal number and describes a power of 2.
     *      The 'A' style uses the prefix "0X", the hex digits
     *      "ABCDEF", and the exponent character 'P'. The number of
     *      digits after the decimal point is given by the precision.
     *      The default precision is enough for an exact
     *      representation of the value.
     * <dt> c
     * <dd> The single character argument is output as a character.
     * <dt> s
     * <dd> The string argument is output. If a precision is given,
     *      then the number of characters output is limited to this.
     * </dl>
     *
     * @param fmt Format string
     * @throws IllegalArgumentException Malformed format string
     */
    public void set(String fmt) throws IllegalArgumentException
    {
        char[] buf = new char[fmt.length()];
        int n;
        char c;

        prefix = "";
        suffix = "";
        idx = 0;
        n = scanRegularChars(buf, fmt);

        if (n > 0)
        {
            prefix = new String(buf, 0, n);
        }

        if (idx == fmt.length())
        {
            return;
        }

        // parse the flags
        boolean parsingFlags = true;

        do
        {
            c = fmt.charAt(idx);

            switch (c)
            {
                case '+':
                {
                    addSign = true;

                    break;
                }

                case ' ':
                {
                    addBlank = true;

                    break;
                }

                case '-':
                {
                    leftAdjust = true;

                    break;
                }

                case '#':
                {
                    alternate = true;

                    break;
                }

                case '0':
                {
                    zeropad = true;

                    break;
                }

                default:
                {
                    parsingFlags = false;

                    break;
                }
            }

            if (parsingFlags)
            {
                if (++idx == fmt.length())
                {
                    parsingFlags = false;
                }
            }
        }
        while (parsingFlags);

        if ((idx < fmt.length()) && Character.isDigit(fmt.charAt(idx)))
        {
            width = scanUnsignedInt(fmt);
        }

        if ((idx < fmt.length()) && (fmt.charAt(idx) == '.'))
        {
            if ((++idx < fmt.length()) && Character.isDigit(fmt.charAt(idx)))
            {
                prec = scanUnsignedInt(fmt);
            }
            else
            {
                throw new IllegalArgumentException(
                "'.' in conversion spec not followed by precision value");
            }
        }

        if (idx == fmt.length())
        {
            throw new IllegalArgumentException(
            "Format string ends prematurely");
        }

        type = fmt.charAt(idx++);

        switch (type)
        {
            case 'd':
            case 'i':
            case 'o':
            case 'u':
            case 'x':
            case 'X':
            {
                if ((prec != -1) && zeropad)
                {
                    zeropad = false;
                }

                break;
            }

            case 'g':
            case 'G':
            case 'f':
            case 'e':
            case 'E':
            case 'a':
            case 'A':
                break;

            case 'c':
                break;

            case 's':
                break;

            default:
            {
                if (validTypes.indexOf(type) == -1)
                {
                    throw new IllegalArgumentException("Conversion character '" +
                            type + "' not one of '" + validTypes + "'");
                }

                break;
            }
        }

        n = scanRegularChars(buf, fmt);

        if (n > 0)
        {
            suffix = new String(buf, 0, n);
        }

        if (idx != fmt.length())
        {
            throw new IllegalArgumentException(
            "Format string has more than one conversion spec");
        }

        if (leftAdjust && zeropad)
        {
            zeropad = false;
        }

        if (addSign && addBlank)
        {
            addBlank = false;
        }
    }

    /**
     * Sets the prefix string associated with the format.
     *
     * @param s New prefix string
     * @see PrintfFormat#getPrefix
     */
    public String setPrefix(String s)
    {
        String old = prefix;
        prefix = s;

        return old;
    }

    /**
     * Sets the suffix string associated with the format.
     *
     * @param s New suffix string
     * @see PrintfFormat#getSuffix
     */
    public String setSuffix(String s)
    {
        String old = suffix;
        suffix = s;

        return old;
    }

    /**
     * Formats a float into a string.
     *
     * @param x Float value to convert
     * @return Resulting string
     */
    public String tostr(float x)
    {
        return tostr((double) x);
    }

    /**
     * Formats a Double into a string.
     *
     * @param x Float value to convert
     * @return Resulting string
     */
    public String tostr(Double x)
    {
        return tostr(x.doubleValue());
    }

    /**
     * Formats an Integer into a string.
     *
     * @param x Float value to convert
     * @return Resulting string
     */
    public String tostr(Integer x)
    {
        return tostr(x.intValue());
    }

    /**
     * Formats a double into a string.
     *
     * @param x Double value to convert
     * @return Resulting string
     */
    public String tostr(double x)
    {
        if("diuoxX".indexOf(type)>-1)
            return tostr((long)x);
        
        if (dd == null)
        {
            dd = new DecDouble();
        }

        if ((type != 'a') && (type != 'A'))
        {
            dd.set(x);
        }
        else
        {
            dd.setSignAndAlt(x);
        }

        char p = '\0';

        output.init(width + 1);

        if (dd.alt != null)
        {
            output.append(dd.alt);
        }
        else if (type == 'f')
        {
            fixedFormat(dd, (prec < 0) ? 6 : prec);
        }
        else if ((type == 'e') || (type == 'E'))
        {
            expFormat(dd, (prec < 0) ? 6 : prec);
        }
        else if ((type == 'a') || (type == 'A'))
        {
            expHexFormat(x, prec);
        }
        else if ((type == 'g') || (type == 'G'))
        {
            freeFormat(dd, (prec < 0) ? 6 : prec);
        }
        else
        {
            System.out.print("f = " + type);
            throw new java.lang.IllegalArgumentException();
        }

        if (dd.sign == -1)
        {
            p = '-';
        }
        else if (addSign)
        {
            p = '+';
        }
        else if (addBlank)
        {
            p = ' ';
        }

        if (zeropad)
        {
            int nz = width - (output.kn - output.k0);

            if (p != '\0')
            {
                nz--;
            }

            output.prepend('0', nz);
        }

        if (p != '\0')
        {
            output.prepend(p);
        }

        return pad();
    }

    /**
     * Formats an int into a string.
     *
     * @param x Int value to convert
     * @return Resulting string
     */
    public String tostr(int x)
    {
        long lx = x;

        if ((type == 'd') || (type == 'i'))
        {
            return tostr(lx);
        }
        else
        { // unsigned; clear high bits

            return tostr(lx & 0xffffffffL);
        }
    }

    /**
     * Formats a long into a string.
     *
     * @param x Long value to convert
     * @return Resulting string
     */
    public String tostr(long x)
    {
        String p = null;

        output.init(Math.max(width, 32));

        if ((type == 'd') || (type == 'i'))
        {
            if (x < 0)
            {
                x = -x;
                p = "-";
            }
            else
            {
                if (addSign)
                {
                    p = "+";
                }
                else if (addBlank)
                {
                    p = " ";
                }
            }

            if ((prec != 0) || (x != 0))
            {
                output.append(Long.toString(x));
            }
        }
        else if (type == 'u')
        {
            uconv(x, 10, ddigits);
        }
        else if (type == 'o')
        {
            uconv(x, 8, ddigits);

            if (alternate && (output.buf[output.k0] != '0'))
            {
                p = "0";
            }
        }
        else if (type == 'x')
        {
            uconv(x, 16, xdigits);

            if (alternate)
            {
                p = "0x";
            }
        }
        else if (type == 'X')
        {
            uconv(x, 16, Xdigits);

            if (alternate)
            {
                p = "0X";
            }
        }
        else
        {
            throw new java.lang.IllegalArgumentException();
        }

        int nz = 0;

        if (zeropad)
        {
            nz = width - (output.kn - output.k0);
        }
        else if (prec > 0)
        {
            nz = prec - (output.kn - output.k0);
        }

        if (nz > 0)
        {
            if (p != null)
            {
                nz -= p.length();
            }

            output.prepend('0', nz);
        }

        if (p != null)
        {
            output.prepend(p);
        }

        return pad();
    }

    /**
     * Formats a char into a string.
     *
     * @param x Char value to convert
     * @return Resulting string
     */
    public String tostr(char x)
    {
        if (type != 'c')
        {
            throw new java.lang.IllegalArgumentException();
        }

        output.init(Math.max(width, 1));
        output.append(String.valueOf(x));

        return pad();
    }

    /**
     * Formats a String into a string.
     *
     * @param x String value to format
     * @return Resulting string
     */
    public String tostr(String x)
    {
        if (type != 's')
        {
            throw new java.lang.IllegalArgumentException();
        }

        output.init(Math.max(width, 1));

        if (prec >= 0)
        {
            output.append(x.substring(0, prec));
        }
        else
        {
            output.append(x);
        }

        return pad();
    }

    private void convert(long x, int n, int m, String d)
    {
        if (x == 0)
        {
            output.append('0');

            return;
        }

        while (x != 0)
        {
            output.prepend(d.charAt((int) (x & m)));
            x = x >>> n;
        }
    }

    private void expFormat(DecDouble dd, int p)
    {
        int i;

        i = 0;
        output.append(dd.digits[i++]);

        if ((p > 0) || alternate)
        {
            output.append('.');
        }

        if (p > 0)
        {
            int kend = p + output.k0 + 2;

            while ((i < dd.numd) && (output.kn < kend))
            {
                output.append(dd.digits[i++]);
            }

            if ((i == dd.numd) && (output.kn < kend) &&
                    (((type != 'g') && (type != 'G')) || alternate))
            {
                output.append('0', kend - output.kn);
            }
        }

        if ((i < dd.numd) && (dd.digits[i] >= '5'))
        {
            roundUpFixedOutput(output);

            if (output.buf[output.k0 + 1] == '0')
            {
                output.buf[output.k0 + 1] = '.';
                output.buf[output.k0 + 2] = '0';
                dd.exp++;
                output.kn--;
            }
        }

        output.append(((type == 'G') || (type == 'E')) ? 'E' : 'e');
        output.append((dd.exp >= 0) ? '+' : '-');

        String expStr = Long.toString(Math.abs(dd.exp));

        if (expStr.length() < 2)
        {
            output.append('0');
        }

        output.append(expStr);
    }

    private void expHexFormat(double d, int p)
    {
        char[] digits = null;
        long bits;
        int e;
        long m;

        bits = Double.doubleToLongBits(d);
        e = (int) ((bits >> 52) & 0x7ffL);
        m = (e == 0) ? ((bits & 0xfffffffffffffL) << 1)
                : ((bits & 0xfffffffffffffL) | 0x10000000000000L);

        if (m == 0)
        {
            e = 0;
        }
        else
        {
            e -= 1023;
        }

        if (m != 0)
        {
            while ((m & 0x10000000000000L) == 0)
            {
                m = m << 1;
                e--;
            }
        }

        if ((p > 0) && (p < 13))
        { // then round up if necessary

            if ((0xf & (m >> (4 * (12 - p)))) >= 8)
            {
                m += (1L << (4 * (13 - p)));

                if ((m & 0x20000000000000L) != 0)
                {
                    m = m >> 1;
                    e++;
                }
            }
        }

        output.append('0');

        if (type == 'A')
        {
            output.append('X');
            digits = Xdigits;
        }
        else
        {
            output.append('x');
            digits = xdigits;
        }

        output.append(((m & 0x10000000000000L) != 0) ? '1' : '0');

        if ((p > 0) || ((p == -1) && (m != 0)) || alternate)
        {
            output.append('.');
        }

        while ((p > 0) || ((p == -1) && ((m & 0xfffffffffffffL) != 0)))
        {
            output.append(digits[(int) ((m & 0xf000000000000L) >> 48)]);
            m = m << 4;

            if (p > 0)
            {
                p--;
            }
        }

        output.append((type == 'A') ? 'P' : 'p');
        output.append((e >= 0) ? '+' : '-');

        String expStr = Long.toString(Math.abs(e));
        output.append(expStr);
    }

    private void fixedFormat(DecDouble dd, int p)
    {
        int kend;
        boolean freeFormat = ((type == 'g') || (type == 'G'));

        int i = 0;

        if (dd.exp >= 0)
        {
            while ((output.kn <= (output.k0 + dd.exp)) && (i < dd.numd))
            {
                output.append(dd.digits[i++]);
            }

            if (output.kn <= (output.k0 + dd.exp))
            {
                output.append('0', (output.k0 + 1 + dd.exp) - output.kn);
            }
        }
        else
        {
            output.append('0');
        }

        if (((p > 0) && (!freeFormat || (i < dd.numd))) || alternate)
        {
            output.append('.');
        }

        if (p > 0)
        {
            kend = output.kn + p;

            // pad if needed
            if (dd.exp < -1)
            {
                output.append('0', Math.min(p, -dd.exp - 1));
            }

            while ((output.kn < kend) && (i < dd.numd))
            {
                output.append(dd.digits[i++]);
            }

            if ((output.kn < kend) && (!freeFormat || alternate))
            {
                output.append('0', kend - output.kn);
            }
        }

        if ((i < dd.numd) && (dd.digits[i] >= '5'))
        {
            roundUpFixedOutput(output);
        }
    }

    private void freeFormat(DecDouble dd, int prec)
    {
        int p = Math.max(1, prec);

        if ((dd.exp >= -4) && (dd.exp < p))
        {
            fixedFormat(dd, p - dd.exp - 1);
        }
        else
        {
            expFormat(dd, p - 1);
        }
    }

    private String pad()
    {
        int padcnt = width - (output.kn - output.k0);

        if (leftAdjust)
        {
            output.append(' ', padcnt);
        }
        else
        {
            output.prepend(' ', padcnt);
        }

        return prefix + output.getString() + suffix;
    }

    private void roundUpFixedOutput(OutBuffer out)
    {
        int i;

        for (i = out.kn - 1; i >= out.k0; i--)
        {
            if (out.buf[i] == '9')
            {
                out.buf[i] = '0';

                // carry by continuing
            }
            else if (out.buf[i] != '.')
            {
                out.buf[i] += 1;

                break;
            }
        }

        if (i < out.k0)
        {
            out.buf[--out.k0] = '1';
        }
    }

    private int scanRegularChars(char[] buf, String fmt)
    {
        int n = 0;
        char c;

        for (; idx < fmt.length(); idx++)
        {
            if ((c = fmt.charAt(idx)) == '%')
            {
                idx++;

                if (idx == fmt.length())
                {
                    throw new IllegalArgumentException(
                    "Format string terminates with '%'");
                }

                if ((c = fmt.charAt(idx)) != '%')
                {
                    break;
                }
            }

            buf[n++] = (char) c;
        }

        return n;
    }

    private int scanUnsignedInt(String fmt)
    {
        int value = 0;
        char c;

        for (c = fmt.charAt(idx); Character.isDigit(c); c = fmt.charAt(idx))
        {
            value = ((10 * value) + c) - '0';

            if (++idx == fmt.length())
            {
                break;
            }
        }

        return value;
    }

    private void uconv(long val, int radix, char[] digits)
    {
        if (val == 0)
        {
            if (prec != 0)
            {
                output.append('0');
            }

            return;
        }

        if (val < 0)
        { // have to compute the first val/radix and val%radix in a
            // complicated way

            long halfval;
            int mod;

            halfval = val >>> 1;
            mod = (int) ((2 * (halfval % radix)) + (val & 0x1));
            val = 2 * (halfval / radix);

            if (mod >= radix)
            {
                mod -= radix;
                val += 1;
            }

            output.prepend(digits[mod]);
        }

        while (val != 0)
        {
            output.prepend(digits[(int) (val % radix)]);
            val /= radix;
        }
    }

    //~ Inner Classes //////////////////////////////////////////////////////////

    /**
     * Stores the decimal representation of a double. Provides
     * a canonical form from which the rest of the formatting
     * is easy. We let the java Double.toString() method do
     * most of the work in creating the representation.
     */
    private class DecDouble
    {
        String alt = null;
        char[] digits = null;
        int exp = 0; // exponent if decimal follows 1st digit
        int numd = 0;
        int sign = 1;

        DecDouble()
        {
            init();
            set(0);
        }

        DecDouble(double d)
        {
            init();
            set(d);
        }

        void set(double d)
        {
            numd = 0;
            exp = 0;

            setSignAndAlt(d);

            if (alt != null)
            {
                return;
            }
            else if (d == 0)
            {
                digits[0] = '0';
                numd = 1;
            }
            else
            {
                if (d < 0)
                {
                    d = -d;
                }

                String s = Double.toString(d);

                int k;
                int len;
                int idec;
                char c;

                len = s.length();

                // skip leading 0's and look for dec point:
                idec = -1;

                for (k = 0; k < len; k++)
                {
                    if ((c = s.charAt(k)) == '.')
                    {
                        idec = k;
                    }
                    else if (c != '0')
                    {
                        break;
                    }
                }

                // now store digits
                numd = 0;

                for (; k < len; k++)
                {
                    if ((c = s.charAt(k)) == '.')
                    {
                        idec = k;
                    }
                    else if ((c == 'e') || (c == 'E'))
                    {
                        break;
                    }
                    else
                    {
                        digits[numd++] = c;
                    }
                }

                if (k < len) // then there is an exponent
                {
                    exp = Integer.parseInt(s.substring(k + 1));
                }
                else
                {
                    exp = 0;
                }

                // adjust exp so decimal follows the leading digit
                if (idec == -1) // no decimal in orig. string
                {
                    exp += (numd - 1);
                }
                else
                {
                    exp += (idec - (k - numd));
                }
            }
        }

        void setSignAndAlt(double d)
        {
            alt = null;
            sign = 1;

            if (Double.isNaN(d))
            {
                alt = "nan";
            }
            else if (d == Double.POSITIVE_INFINITY)
            {
                alt = "inf";
            }
            else if (d == Double.NEGATIVE_INFINITY)
            {
                alt = "inf";
                sign = -1;
            }
            else if (d == 0)
            {
                if ((Double.doubleToLongBits(d) & 0x8000000000000000L) == 0)
                {
                    sign = 1;
                }
                else
                {
                    sign = -1;
                }
            }
            else if (d < 0)
            {
                sign = -1;
            }
        }

        private void init()
        {
            digits = new char[256];
            numd = 0;
        }
    }

    /** OutBuffer is used to form the output character sequence.
     * We defined this because it makes things a little faster
     * than working with strings.
     */
    private class OutBuffer
    {
        char[] buf;
        int k0;
        int kn;

        OutBuffer(int n)
        {
            buf = new char[n];
            kn = k0 = 0;
        }

        final void append(char c)
        {
            buf[kn++] = c;
        }

        final int append(String s)
        {
            for (int i = 0; i < s.length(); i++)
            {
                buf[kn++] = s.charAt(i);
            }

            return kn;
        }

        final int append(char c, int n)
        {
            while (n-- > 0)
            {
                buf[kn++] = c;
            }

            return kn;
        }

        final String getString()
        {
            return String.valueOf(buf, k0, kn - k0);
        }

        final void init(int base)
        {
            kn = k0 = base;
        }

        final void prepend(char c)
        {
            buf[--k0] = c;
        }

        final int prepend(String s)
        {
            for (int i = s.length() - 1; i >= 0; i--)
            {
                buf[--k0] = s.charAt(i);
            }

            return k0;
        }

        final int prepend(char c, int n)
        {
            while (n-- > 0)
            {
                buf[--k0] = c;
            }

            return k0;
        }
    }
}

///////////////////////////////////////////////////////////////////////////////
//END OF FILE.
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////
//END OF FILE.
///////////////////////////////////////////////////////////////////////////////
