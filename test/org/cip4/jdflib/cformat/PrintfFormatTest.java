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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.cip4.jdflib.JDFTestCaseBase;

/**
 * Testing class for PrintfFormat. Run the <tt>main</tt> method to test the
 * class.
 * 
 * @see PrintfFormat
 * @.author John E. Lloyd
 */
public class PrintfFormatTest extends JDFTestCaseBase
{
	// ~ Static fields/initializers
	// /////////////////////////////////////////////

	static double dval;
	static long lval;
	static char cval;
	static String sval;
	static boolean VERBOSE = true;

	// ~ Instance fields
	// ////////////////////////////////////////////////////////

	String fmt;
	String res;
	int type;

	// ~ Methods
	// ////////////////////////////////////////////////////////////////

	/**
	 * Tests the class PrintfFormat. If everything is OK, the string "Passed" is
	 * printed, and the program exits with status 0. Otherwise, diagnostics and
	 * a stack trace are printed, and the program exits with status 1.
	 * 
	 * @param args
	 *            Program arguments.<br>
	 *            "-help" prints a usage message.<br>
	 *            "-timing" compares how long it takes to output a double
	 *            compared to the regular Java string conversion.
	 */
	static public void main(String[] args)
	{
		boolean testTiming = false;

		for (int i = 0; i < args.length; i++)
		{
			if (args[i].equals("-timing"))
			{
				testTiming = true;
			} else if (args[i].equals("-help"))
			{
				printUsage();
				System.exit(0);
			} else
			{
				printUsage();
				System.exit(1);
			}
		}

		if (testTiming)
		{
			try
			{
				int i;
				int k;
				int nsamp = 10;
				int niter = 1000;
				long t0;
				long t1;
				double texec;

				// call once to init
				t0 = System.currentTimeMillis();

				for (i = 0; i < niter; i++)
				{
					for (k = 0; k < nsamp; k++)
					{
						Double.toString(k * 1.111111 * Math.pow(10, k));
					}
				}

				t1 = System.currentTimeMillis();
				texec = (1000 * (t1 - t0)) / (double) (niter * nsamp);
				System.out.println("Typical time for Double.toString(): "
						+ texec + " usec");

				PrintfFormat fmt = new PrintfFormat("%g");
				t0 = System.currentTimeMillis();

				for (i = 0; i < niter; i++)
				{
					for (k = 0; k < nsamp; k++)
					{
						fmt.tostr(k * 1.111111 * Math.pow(10, k));
					}
				}

				t1 = System.currentTimeMillis();
				texec = (1000 * (t1 - t0)) / (double) (niter * nsamp);
				System.out
						.println("Typical time for PrintfFormat.tostr(double): "
								+ texec + " usec");
			} catch (Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		} else
		{
			try
			{
				testLots();
			} catch (Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}

			System.out.println("\nPassed\n");
			System.exit(0);
		}
	}

	/**
	 * @throws TestException
	 */
	public static void testLots() throws TestException
	{
		test("foo%gbar", 'g', "foo", "bar", 0, -1, false, false, false, false,
				false);
		test("fo%%o%#0- +10.4gbar%%", 'g', "fo%o", "bar%", 10, 4, true, false,
				true, false, true);
		test("foo%%%9.0d%%bar", 'd', "foo%", "%bar", 9, 0, false, false, false,
				false, false);
		test("foo%%%9d%%bar", 'd', "foo%", "%bar", 9, -1, false, false, false,
				false, false);
		test("%9d", 'd', "", "", 9, -1, false, false, false, false, false);
		test("%X", 'X', "", "", 0, -1, false, false, false, false, false);
		test("%#X", 'X', "", "", 0, -1, true, false, false, false, false);
		test("%0X", 'X', "", "", 0, -1, false, true, false, false, false);
		test("%-X", 'X', "", "", 0, -1, false, false, true, false, false);
		test("% X%%%%", 'X', "", "%%", 0, -1, false, false, false, true, false);
		test("%%%+X%%", 'X', "%", "%", 0, -1, false, false, false, false, true);
		test("%045X", 'X', "", "", 45, -1, false, true, false, false, false);

		// test("foo%gbar%", "Format string terminates with '%'");
		// test("bar%", "Format string terminates with '%'");
		// test("foo%%%9.d%%bar",
		// "'.' in conversion spec not followed by precision value");
		// test("foo%%%9.0", "Format string ends prematurely");
		// test("foo%qbar",
		// "Conversion character 'q' not one of 'diouxXeEfFgGaAcs'");
		// test("foo%dbar%g",
		// "Format string has more than one conversion spec");
		double a = 1.2816119594740609E-5;
		double x = 1.23456789012;
		double y = 123;
		double z = 1.2345e30;
		double w = 1.02;
		double u = 1.234e-5;
		double v = 29.999999999999996;

		// test fixed point conversion:
		test(0.0, "%8.3f", "   0.000");
		test(0.0, "%6.1f", "   0.0");
		test(0.0, "%6.0f", "     0");
		test(0.0, "%1.0f", "0");

		test(1.0, "%8.3f", "   1.000");
		test(1.0, "%6.1f", "   1.0");
		test(1.0, "%6.0f", "     1");
		test(1.0, "%1.0f", "1");
		test(1.0, "% 1.0f", " 1");
		test(1.0, "%+2.0f", "+1");
		test(1.0, "%-2.0f", "1 ");
		test(1.0, "%+#2.0f", "+1.");
		test(1.0, "%-+#3.0f", "+1.");
		test(1.0, "%-+#4.0f", "+1. ");
		test(1.0, "% -+#4.0f", "+1. ");

		test(-1.0, "%8.3f", "  -1.000");
		test(-1.0, "%6.1f", "  -1.0");
		test(-1.0, "%6.0f", "    -1");
		test(-1.0, "%1.0f", "-1");
		test(-1.0, "% 1.0f", "-1");
		test(-1.0, "%+2.0f", "-1");
		test(-1.0, "%-2.0f", "-1");
		test(-1.0, "%+#2.0f", "-1.");
		test(-1.0, "%-+#3.0f", "-1.");
		test(-1.0, "%-+#4.0f", "-1. ");
		test(-1.0, "% -+#4.0f", "-1. ");

		// test out rounding
		test(99.9995, "%7.4f", "99.9995");
		test(99.9994, "%7.3f", " 99.999");
		test(99.9995, "%7.3f", "100.000");
		test(99.9996, "%7.3f", "100.000");

		// now do some meatier things ...
		test(0.5, "b=|%8.4f|\n", "b=|  0.5000|\n");
		test(123.541, "|%8.4f|", "|123.5410|");
		test(123444.541, "|%8.4f|", "|123444.5410|");
		test(123.541, "|%6.2f|", "|123.54|");
		test(0.000003, "|%6.2f|", "|  0.00|");
		test(a, "a=|%22.20f|", "a=|0.00001281611959474061|");
		test(v, "v=|%10.3f|", "v=|    30.000|");

		test(w, "w=|%4.2f|", "w=|1.02|");
		test(-w, "-w=|%4.2f|", "-w=|-1.02|");
		test(v, "v=|%10.3f|", "v=|    30.000|");
		test(x, "x=|%f|", "x=|1.234568|");
		test(u, "u=|%20f|", "u=|            0.000012|");
		test(x, "x=|% .5f|", "x=| 1.23457|");
		test(w, "w=|%20.5f|", "w=|             1.02000|");
		test(x, "x=|%020.5f|", "x=|00000000000001.23457|");
		test(x, "x=|%+20.5f|", "x=|            +1.23457|");
		test(x, "x=|%+020.5f|", "x=|+0000000000001.23457|");
		test(x, "x=|% 020.5f|", "x=| 0000000000001.23457|");
		test(y, "y=|%#+20.5f|", "y=|          +123.00000|");
		test(y, "y=|%-+20.5f|", "y=|+123.00000          |");
		test(z, "z=|%20.5f|", "z=|1234500000000000000000000000000.00000|");

		// %e tests
		test(0.0, "%e", "0.000000e+00");
		test(0.0, "%E", "0.000000E+00");
		test(-0.0, "%e", "-0.000000e+00");
		test(-0.0, "%E", "-0.000000E+00");
		test(x, "x=|%e|", "x=|1.234568e+00|");
		test(9.999, "|%5.3e|", "|9.999e+00|");
		test(9.999, "|%4.2e|", "|1.00e+01|");
		test(u, "u=|%20e|", "u=|        1.234000e-05|");
		test(x, "x=|% .5e|", "x=| 1.23457e+00|");
		test(w, "w=|%20.5e|", "w=|         1.02000e+00|");
		test(x, "x=|%020.5e|", "x=|0000000001.23457e+00|");
		test(x, "x=|%+20.5e|", "x=|        +1.23457e+00|");
		test(x, "x=|%+020.5e|", "x=|+000000001.23457e+00|");
		test(x, "x=|% 020.5e|", "x=| 000000001.23457e+00|");
		test(y, "y=|%#+20.5e|", "y=|        +1.23000e+02|");
		test(y, "y=|%-+20.5e|", "y=|+1.23000e+02        |");

		// %g tests
		test(0.0, "%g", "0");
		test(0.0, "%#g", "0.00000");
		test(-0.0, "%g", "-0");
		test(-0.0, "%#g", "-0.00000");
		test(x, "x=|%g|", "x=|1.23457|");
		test(z, "z=|%g|", "z=|1.2345e+30|");
		test(w, "w=|%g|", "w=|1.02|");
		test(u, "u=|%g|", "u=|1.234e-05|");
		test(y, "y=|%.2g|", "y=|1.2e+02|");
		test(y, "y=|%#.2g|", "y=|1.2e+02|");
		test(0.0, "|%.0g|", "|0|");
		test(123.0, "|%.0g|", "|1e+02|");
		test(123.0, "|%.1g|", "|1e+02|");
		test(123.0, "|%.2g|", "|1.2e+02|");
		test(123.0, "|%.2G|", "|1.2E+02|");
		test(123.0, "|%.3g|", "|123|");
		test(123.0, "|%#.3g|", "|123.|");

		// %a tests
		test(0.0, "%a", "0x0p+0");
		test(0.0, "%A", "0X0P+0");
		test(-0.0, "%a", "-0x0p+0");
		test(-0.0, "%A", "-0X0P+0");
		test(x, "x=|%a|", "x=|0x1.3c0ca428c1d2bp+0|");
		test(x, "x=|%A|", "x=|0X1.3C0CA428C1D2BP+0|");
		test(9.999, "|%5.3a|", "|0x1.3ffp+3|");
		test(9.999, "|%4.2a|", "|0x1.40p+3|");
		test(u, "u=|%20a|", "u=|0x1.9e0fcaf9380fcp-17|");
		test(u, "u=|%23a|", "u=|  0x1.9e0fcaf9380fcp-17|");
		test(x, "x=|% .5a|", "x=| 0x1.3c0cap+0|");
		test(u, "w=|%20.5a|", "w=|       0x1.9e0fdp-17|");
		test(u, "w=|%020.5a|", "w=|00000000x1.9e0fdp-17|");
		test(u, "w=|%+20.5a|", "w=|      +0x1.9e0fdp-17|");
		test(u, "w=|%+020.5a|", "w=|+0000000x1.9e0fdp-17|");
		test(u, "w=|% 020.5a|", "w=| 0000000x1.9e0fdp-17|");
		test(u, "x=|%#+20.5a|", "x=|      +0x1.9e0fdp-17|");
		test(u, "x=|%-+20.5a|", "x=|+0x1.9e0fdp-17      |");
		test(0.0, "x=|%#a|", "x=|0x0.p+0|");
		test(255.0, "x=|%a|", "x=|0x1.fep+7|");
		test(255.0, "x=|%.1a|", "x=|0x1.0p+8|");
		test(2047.0, "x=|%.2a|", "x=|0x1.00p+11|");
		test(2043.0, "x=|%.2a|", "x=|0x1.ffp+10|");

		// %d tests
		int d = 0xCAFE;

		test(-1, "%d", "-1");
		test(-1, "%3d", " -1");
		test(-1, "% 3d", " -1");
		test(-1, "%03d", "-01");
		test(0, "%d", "0");
		test(0, "%3d", "  0");
		test(0, "%+3d", " +0");
		test(0, "%+03d", "+00");
		test(d, "d = |%d|\n", "d = |51966|\n");
		test(d, "d=|%20d|", "d=|               51966|");
		test(d, "d=|%020d|", "d=|00000000000000051966|");
		test(d, "d=|%+20d|", "d=|              +51966|");
		test(d, "d=|% 020d|", "d=| 0000000000000051966|");
		test(d, "d=|% +020d|", "d=|+0000000000000051966|");
		test(d, "d=|%-20d|", "d=|51966               |");
		test(d, "d=|%20.8d|", "d=|            00051966|");
		test(d, "d=|%020.8d|", "d=|            00051966|");
		test(0, "d=|%020.0d|", "d=|                    |");
		test(0, "d=|%+020.0d|", "d=|                   +|");
		test(0, "d=|%+-020.0d|", "d=|+                   |");

		// %x tests
		test(d, "d=|%x|", "d=|cafe|");
		test(d, "d=|%20X|", "d=|                CAFE|");
		test(d, "d=|%#20x|", "d=|              0xcafe|");
		test(d, "d=|%020X|", "d=|0000000000000000CAFE|");
		test(d, "d=|%20.8x|", "d=|            0000cafe|");
		test(d, "d=|%020.8x|", "d=|            0000cafe|");
		test(d, "d=|%o|", "d=|145376|");
		test(0xfffffffffffffffL, "d=|%x|", "d=|fffffffffffffff|");
		test(-1L, "d=|%x|", "d=|ffffffffffffffff|");
		test(-1, "d=|%x|", "d=|ffffffff|");
		test(0, "d=|%020.0x|", "d=|                    |");
		test(0, "d=|%+020.0x|", "d=|                    |");
		test(0, "d=|%+-020.0x|", "d=|                    |");

		// %u tests
		test(d, "u=|%u|", "u=|51966|");
		test(d, "u=|%20u|", "u=|               51966|");
		test(d, "u=|%#20u|", "u=|               51966|");
		test(d, "u=|%020u|", "u=|00000000000000051966|");
		test(d, "u=|%20.8u|", "u=|            00051966|");
		test(d, "u=|%020.8u|", "u=|            00051966|");
		test(0xffffffffffffffffL, "u=|%u|", "u=|18446744073709551615|");
		test(-123L, "u=|%u|", "u=|18446744073709551493|");
		test(-123, "u=|%u|", "u=|4294967173|");
		test(-1, "u=|%u|", "u=|4294967295|");
		test(0, "d=|%020.0u|", "d=|                    |");
		test(0, "d=|%+020.0u|", "d=|                    |");
		test(0, "d=|%+-020.0u|", "d=|                    |");

		// %o tests
		test(d, "d=|%020o|", "d=|00000000000000145376|");
		test(d, "d=|%#20o|", "d=|             0145376|");
		test(d, "d=|%#020o|", "d=|00000000000000145376|");
		test(d, "d=|%20.12o|", "d=|        000000145376|");
		test(d, "d=|%020.12o|", "d=|        000000145376|");
		test(0, "d=|%020.0d|", "d=|                    |");
		test(0, "d=|%+020.0o|", "d=|                    |");
		test(0, "d=|%+-020.0o|", "d=|                    |");

		test(0, "|%o|", "|0|");

		double nz = -0.0;
		double pz = 0.0;

		// exceptional tests
		test(1 / pz, ">>%g<<", ">>inf<<");
		test(1 / pz, ">>%7g<<", ">>    inf<<");
		test(1 / nz, ">>%7g<<", ">>   -inf<<");
		test(pz / nz, ">>%7g<<", ">>    nan<<");
		test(pz / nz, ">>%+7g<<", ">>   +nan<<");

		// %s and %c tests
		test("Hello", "%s", "Hello");
		test("Hello", "s=|%-20s|", "s=|Hello               |");
		test('!', "%c", "!");
		test('!', "s=|%-20c|", "s=|!                   |");
	}

	static void check(int type, String fmt, String res) throws TestException
	{
		ByteArrayOutputStream bs = new ByteArrayOutputStream(256);
		PrintStream ps = new PrintStream(bs);

		try
		{
			switch (type)
			{
			case 'd':
			{
				ps.print(new PrintfFormat(fmt).tostr(dval));

				if (VERBOSE)
				{
					System.out.println(dval + " with " + fmt + " --> '" + res
							+ "'");
				}

				break;
			}

			case 'l':
			{
				ps.print(new PrintfFormat(fmt).tostr(lval));

				if (VERBOSE)
				{
					System.out.println(lval + " with " + fmt + " --> '" + res
							+ "'");
				}

				break;
			}

			case 'i':
			{
				ps.print(new PrintfFormat(fmt).tostr((int) lval));

				if (VERBOSE)
				{
					System.out.println(lval + " with " + fmt + " --> '" + res
							+ "'");
				}

				break;
			}

			case 'c':
			{
				ps.print(new PrintfFormat(fmt).tostr(cval));

				if (VERBOSE)
				{
					System.out.println(cval + " with " + fmt + " --> '" + res
							+ "'");
				}

				break;
			}

			case 's':
			{
				ps.print(new PrintfFormat(fmt).tostr(sval));

				if (VERBOSE)
				{
					System.out.println(sval + " with " + fmt + " --> '" + res
							+ "'");
				}

				break;
			}
			}
		} catch (IllegalArgumentException e)
		{
			if (res != null)
			{
				throw new TestException("Expecting '" + res
						+ "' but got an exception instead");
			}
		}

		if (!bs.toString().equals(res))
		{
			throw new TestException("Expecting '" + res + "', got '"
					+ bs.toString() + "'");
		}
	}

	static void test(double d, String f, String r) throws TestException
	{
		dval = d;
		check('d', f, r);
	}

	static void test(long l, String f, String r) throws TestException
	{
		lval = l;
		check('l', f, r);
	}

	static void test(int i, String f, String r) throws TestException
	{
		lval = i;
		check('i', f, r);
	}

	static void test(char c, String f, String r) throws TestException
	{
		cval = c;
		check('c', f, r);
	}

	static void test(String s, String f, String r) throws TestException
	{
		sval = s;
		check('s', f, r);
	}

	static void test(String s, char type, String pfx, String sfx, int w, int p,
			boolean alt, boolean zeropad, boolean left, boolean blank,
			boolean sign)
	{
		PrintfFormat fmt = null;

		try
		{
			fmt = new PrintfFormat(s);
		} 
		catch (Exception e)
		{
			fail("bad expression");
		}

		if (fmt != null)
		{
			assertEquals("type is '" + fmt.type + "' vs. '" + type + "'", fmt.type, type);
			assertEquals("prefix is '" + fmt.prefix + "' vs. '" + pfx + "'", fmt.prefix, pfx);
			assertEquals("suffix is '" + fmt.suffix + "' vs. '" + sfx + "'", fmt.suffix, sfx);
			assertEquals("width is '" + fmt.width + "' vs. '" + w + "'", fmt.width, w);
			assertEquals("prec is '" + fmt.prec + "' vs. '" + p + "'", fmt.prec, p);
			assertEquals("alternate is '" + fmt.alternate + "' vs. '" + alt + "'", fmt.alternate, alt);
			assertEquals("zeropad is '" + fmt.zeropad + "' vs. '" + zeropad + "'", fmt.zeropad, zeropad);
			assertEquals("leftAdjust is '" + fmt.leftAdjust + "' vs. '" + left + "'", fmt.leftAdjust, left);
			assertEquals("addBlank is '" + fmt.addBlank + "' vs. '" + blank + "'", fmt.addBlank, blank);
			assertEquals("addSign is '" + fmt.addSign + "' vs. '" + sign + "'", fmt.addSign, sign);
		}
	}

	private static void printUsage()
	{
		System.out.println("Usage: FormatTest [-timing] [-help]");
	}

	// ~ Inner Classes
	// //////////////////////////////////////////////////////////

	// private static void test(String s, String emsg)
	// {
	// boolean tripped = false;
	//
	// try
	// {
	// PrintfFormat fmt = new PrintfFormat(s);
	// }
	// catch (IllegalArgumentException e)
	// {
	// if (!e.getMessage().equals(emsg))
	// {
	// System.out.println("format '" + s + "'");
	// e.printStackTrace();
	// System.exit(1);
	// }
	//
	// tripped = true;
	// }
	// catch (Exception e)
	// {
	// System.out.println("format '" + s + "'");
	// e.printStackTrace();
	// System.exit(1);
	// }
	//
	// if (!tripped)
	// {
	// System.out.println("format '" + s + "'");
	// System.out.println("no error generated");
	// System.exit(1);
	// }
	// }
	private static class TestException extends Exception
	{
		private static final long serialVersionUID = -857552775825506243L;

		TestException(String msg)
		{
			super(msg);
		}
	}
}

// /////////////////////////////////////////////////////////////////////////////
// END OF FILE.
// /////////////////////////////////////////////////////////////////////////////

// /////////////////////////////////////////////////////////////////////////////
// END OF FILE.
// /////////////////////////////////////////////////////////////////////////////
