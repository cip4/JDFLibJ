package org.cip4.jdflib.cformat;

import java.io.StringReader;

/**
 * Testing class for ScanfReader. Run the <tt>main</tt> method to test the
 * class.
 * 
 * @see ScanfReader
 * @.author John E. Lloyd
 */
public class ScanfReaderTest
{
	// ~ Instance fields
	// ////////////////////////////////////////////////////////

	ScanfReader reader;

	// ~ Constructors
	// ///////////////////////////////////////////////////////////

	// private static void assert (boolean ok)
	// { if (!ok)
	// { Throwable e = new Throwable();
	// System.out.println ("Assertion failed:");
	// e.printStackTrace();
	// System.exit(1);
	// }
	// }
	ScanfReaderTest(String input)
	{
		reader = new ScanfReader(new StringReader(input));
	}

	// ~ Methods
	// ////////////////////////////////////////////////////////////////

	/**
	 * Tests the class ScanfReader. If everything is OK, the string "Passed" is
	 * printed, and the program exits with status 0. Otherwise, diagnostics and
	 * a stack trace are printed, and the program exits with status 1.
	 */
	public static void main(String[] args)
	{
		ScanfReaderTest test = new ScanfReaderTest(
				"/gfhghda 0 0123 123 0xbeef 0XBeeF -45 +67"
						+ " 45 45. .45 4.5 1.2E-0 1.33e+6");

		test.checkString("%s", "/gfhghda", null);
		test.checkInt('i', "%i", 0, null);
		test.checkInt('i', "%i", 83, null);
		test.checkInt('i', "%i", 123, null);
		test.checkInt('i', "%i", 0xbeef, null);
		test.checkInt('i', "%i", 0xbeef, null);
		test.checkInt('i', "%i", -45, null);
		test.checkInt('i', "%i", 67, null);
		test.checkDouble("%f", 45, null);
		test.checkDouble("%f", 45, null);
		test.checkDouble("%f", .45, null);
		test.checkDouble("%f", 4.5, null);
		test.checkDouble("%f", 1.2, null);
		test.checkDouble("%f", 1.33e6, null);

		test.checkString("%s", null, "EOF");
		test.checkInt('i', "%i", 0, "EOF");
		test.checkDouble("%f", 0, "EOF");
		test.checkChars("%[123]", null, "EOF");

		test = new ScanfReaderTest(
				"hello \n \r \r\n \n\rfoo\n1234.0bar\r\n1\r\na\r\n\ra\n");

		test.checkString("%s", "hello", null);
		test.checkString("%s", "foo", null);
		test.checkLineAndCharNums(18, 6);
		test.checkRead(4, "\n123");
		test.checkLineAndCharNums(22, 7);
		test.checkDouble("%f", 4.0, null);
		test.checkLineAndCharNums(25, 7);
		test.checkRead(4, "bar\r");
		test.checkLineAndCharNums(29, 8);
		test.checkInt('i', "%i", 1, null);
		test.checkLineAndCharNums(31, 8);
		test.checkChars("%1c", "\r", null);
		test.checkLineAndCharNums(32, 9);
		test.checkRead(5, "\na\r\n\r");
		test.checkLineAndCharNums(37, 11);
		test.checkString("%s", "a", null);
		test.checkRead(1, "\n");
		test.checkLineAndCharNums(39, 12);
		test.checkRead(4, null);
		test.checkRead(4, null);

		test = new ScanfReaderTest("\n\rhi there");

		test.checkRead(1, "\n");
		test.checkRead(3, "\rhi");
		test.checkString("%s", "there", null);
		test.checkRead(4, null);
		test.checkRead(4, null);
		test.checkLineAndCharNums(10, 3);

		test = new ScanfReaderTest(
				"abcde   12345   abcde  foobar  abcdef \t hithere" + "xyz");

		test.checkString("ab%s", "cde", null);
		test.checkString(" 12%2s5", "34", null);
		test.checkString(" a%2s", "bc", null);
		test.checkString("%s", "de", null);
		test.checkString("%1s", "f", null);
		test.checkString("%1s", "o", null);
		test.checkString("ob%1sr", "a", null);
		test.checkString(" %6s ", "abcdef", null);
		test.checkChars("%2cthere", "hi", null);

		test.checkString("%c", null, "Illegal conversion character 'c'");
		test.checkString(" %s", null,
				"No white space to match white space in format");
		test.checkString("y%s", null,
				"Char 'x' does not match char 'y' in format");

		test.checkChar("%c", 'x', null);

		test.checkChar("%x", '\000', "Illegal conversion character 'x'");
		test.checkChar(" %c", '\000',
				"No white space to match white space in format");
		test.checkChar("z%c", '\000',
				"Char 'y' does not match char 'z' in format");

		test.checkChar("%c", 'y', null);

		test.checkChars("%x", null, "Illegal conversion character 'x'");
		test.checkChars(" %c", null,
				"No white space to match white space in format");
		test.checkChar("y%c", '\000',
				"Char 'z' does not match char 'y' in format");

		test.checkChars("%1c", "z", null);

		test = new ScanfReaderTest("0123456789 ]-^-bdfmwax");

		// tests of [] character notation
		test.checkChar("0%[012]2", '1', null);
		test.checkChar("%[012]", '\000',
				"Input char '3' does not match '[012]'");
		test
				.checkChars("%[012]", null,
						"Input char '3' does not match '[012]'");
		test.checkChar(" %[34]", '\000',
				"No white space to match white space in format");
		test.checkChar("%[34]", '3', null);
		test.checkChars("%[34]", "4", null);
		test.checkChars("%3[345678]", "567", null);
		test.checkChars("%[abc4-9]", "8", null);
		test.checkChars("%[^abc4-9]", null,
				"Input char '9' does not match '[^abc4-9]'");

		test.checkChar("%[^abc]", '9', null);
		test.checkChar("%[^ ]", '\000', "Input char ' ' does not match '[^ ]'");
		test.checkChar("%[]]", '\000', "Input char ' ' does not match '[]]'");
		test.checkChar("%[0-9-]", '\000',
				"Input char ' ' does not match '[0-9-]'");
		test.checkChar("%[^-]", ' ', null);
		test.checkChars("%3[]^-]", "]-^", null);
		test.checkChars("%[0-9-]", "-", null);
		test.checkChars("%5[b-f-w]", "bdfmw", null);
		test.checkChars("%[b-f-w]", null,
				"Input char 'a' does not match '[b-f-w]'");
		test.checkChars("%[a^b]", "a", null);
		test.checkChars("%[b-f-w]", null,
				"Input char 'x' does not match '[b-f-w]'");
		test
				.checkChars("%[z-y]", null,
						"Input char 'x' does not match '[z-y]'");
		test
				.checkChars("%[w-q]", null,
						"Input char 'x' does not match '[w-q]'");
		test.checkChars("%[z-w]", "x", null);

		// integer read tests
		test = new ScanfReaderTest("0 012 123 123456 0 012 123 2345 0 "
				+ "deadbeef DEADBeeF deadbeef " + "0 012 123 0xbeef 1230xbe012"
				+ " xy123-34-67 +45+98 123z beef"
				+ " xy123-34-67 +45+54 123z beef"
				+ " xy123-34-67 +45+54 123z g" + " 12389beef"
				+ " xy123-34-67 +45+98 123z b0x123 -0x345 +0 -0 0 -0111 +012 "
				+ "-0x1230x0x x123z x012z x-0x45z x+67z");

		test.checkInt('d', "%d", 0, null);
		test.checkInt('d', "%d", 12, null);
		test.checkInt('d', "%d", 123, null);
		test.checkInt('d', "%3d", 123, null);
		test.checkInt('d', "%3d", 456, null);
		test.checkInt('o', "%o", 0, null);
		test.checkInt('o', "%o", 012, null);
		test.checkInt('o', "%o", 0123, null);
		test.checkInt('o', "%1o", 02, null);
		test.checkInt('o', "%3o", 0345, null);
		test.checkInt('x', "%x", 0, null);
		test.checkInt('x', "%x", 0xdeadbeef, null);
		test.checkInt('x', "%x", 0xdeadbeef, null);
		test.checkInt('x', "%4x", 0xdead, null);
		test.checkInt('x', "%4x", 0xbeef, null);
		test.checkInt('i', "%i", 0, null);
		test.checkInt('i', "%i", 012, null);
		test.checkInt('i', "%i", 123, null);
		test.checkInt('i', "%i", 0xbeef, null);
		test.checkInt('i', "%2i", 12, null);
		test.checkInt('i', "%1i", 3, null);
		test.checkInt('i', "%4i", 0xbe, null);
		test.checkInt('i', "%3i", 012, null);

		test.checkChars("%2c", " x", null);

		test.checkInt('d', "%x", 0, "Illegal conversion character 'x'");
		test.checkInt('d', " %d", 0,
				"No white space to match white space in format");
		test.checkInt('d', "yz%d", 0,
				"Char '1' does not match char 'z' in format");
		test.checkInt('d', "%d", 123, null);
		test.checkInt('d', "%d", -34, null);
		test.checkInt('d', "-%d", 67, null);
		test.checkInt('d', "%d", 45, null);
		test.checkInt('d', "%d", 98, null);
		test.checkInt('d', "%dzz", 123,
				"Char ' ' does not match char 'z' in format");
		test.checkInt('d', "%d", 0, "Malformed decimal integer");

		test.checkChars("%6c", "beef x", null);

		test.checkInt('o', "%x", 0, "Illegal conversion character 'x'");
		test.checkInt('o', " %o", 0,
				"No white space to match white space in format");
		test.checkInt('o', "yz%o", 0,
				"Char '1' does not match char 'z' in format");
		test.checkInt('o', "%o", 0123, null);
		test.checkInt('o', "-%o", 034, null);
		test.checkInt('o', "-%o", 067, null);
		test.checkInt('o', " +%o", 045, null);
		test.checkInt('o', "+%o", 054, null);
		test.checkInt('o', "%ozz", 0123,
				"Char ' ' does not match char 'z' in format");
		test.checkInt('o', "%o", 0, "Malformed octal integer");

		test.checkChars("%6c", "beef x", null);

		test.checkInt('x', "%o", 0, "Illegal conversion character 'o'");
		test.checkInt('x', " %x", 0,
				"No white space to match white space in format");
		test.checkInt('x', "yz%x", 0,
				"Char '1' does not match char 'z' in format");
		test.checkInt('x', "%x", 0x123, null);
		test.checkInt('x', "-%x", 0x34, null);
		test.checkInt('x', "-%x", 0x67, null);
		test.checkInt('x', " +%x", 0x45, null);
		test.checkInt('x', "+%x", 0x54, null);
		test.checkInt('x', "%xzz", 0x123,
				"Char ' ' does not match char 'z' in format");
		test.checkInt('x', "%x", 0, "Malformed hex integer");

		test.checkChar("%c", 'g', null);
		test.checkInt('o', "%o", 0123, null);
		test.checkInt('d', "%d", 89, null);
		test.checkInt('x', "%x", 0xbeef, null);

		test.checkChars("%2c", " x", null);

		test.checkInt('i', "%g", 0, "Illegal conversion character 'g'");
		test.checkInt('i', " %i", 0,
				"No white space to match white space in format");
		test.checkInt('i', "yz%i", 0,
				"Char '1' does not match char 'z' in format");
		test.checkInt('i', "%i", 123, null);
		test.checkInt('i', "%i", -34, null);
		test.checkInt('i', "%i", -67, null);
		test.checkInt('i', "%i", 45, null);
		test.checkInt('i', "%i", 98, null);
		test.checkInt('i', "%izz", 123,
				"Char ' ' does not match char 'z' in format");
		test.checkInt('i', "%i", 0, "Malformed decimal integer");
		test.checkChar("%c", 'b', null);
		test.checkInt('i', "%i", 0x123, null);
		test.checkInt('i', "%i", -0x345, null);
		test.checkInt('i', "%i", 0, null);
		test.checkInt('i', "%i", -0, null);
		test.checkInt('i', "%i", 0, null);
		test.checkInt('i', "%i", -0111, null);
		test.checkInt('i', "%i", 012, null);

		test.checkInt('i', "%1i", 0, "Malformed integer");
		test.checkInt('i', "%2i", -0, null);
		test.checkInt('i', "x%2i", 12, null);
		test.checkInt('i', "%1i", 3, null);
		test.checkInt('i', "%2i", 0, "Malformed hex integer");
		test.checkInt('i', "x%i", 0, "Malformed hex integer");

		test.checkInt('i', " x%iz", 123, null);
		test.checkInt('i', " x%iz", 012, null);
		test.checkInt('i', " x%iz", -0x45, null);
		test.checkInt('i', " x%iz", 67, null);

		// Floating point tests
		test = new ScanfReaderTest("0 1 123 1234 b12pp0.1 10.33 .44 "
				+ "0.0 0.1e-4 1e+3 1e-4 1e5 1.1e-6"
				+ "xy2.3. 11 .e4 1.4e-  1.5e 3.4g" + "yy12.345 2 .456e-1.53e20");

		test.checkDouble("%f", 0, null);
		test.checkDouble("%f", 1, null);
		test.checkDouble("%f", 123, null);
		test.checkDouble(" 1%2f4", 23, null);
		test.checkDouble(" b%f", 12, null);
		test.checkDouble("pp%f", 0.1, null);
		test.checkDouble("%f", 10.33, null);
		test.checkDouble("%f", .44, null);
		test.checkDouble("%f", 0.0, null);
		test.checkDouble("%f", 0.1e-4, null);
		test.checkDouble("%f", 1e+3, null);
		test.checkDouble("%f", 1e-4, null);
		test.checkDouble("%f", 1e5, null);
		test.checkDouble("%f", 1.1e-6, null);

		test.checkDouble("%z", 0, "Illegal conversion character 'z'");
		test.checkChar("%c", 'x', null);
		test.checkDouble(" %f", 0,
				"No white space to match white space in format");
		test
				.checkDouble("x%f", 0,
						"Char 'y' does not match char 'x' in format");
		test.checkDouble("y%f", 2.3, null);
		test.checkDouble("%f", 0, "Malformed floating point number: no digits");
		test.checkDouble("%f", 11, null);
		test.checkDouble("%f", 0, "Malformed floating point number: no digits");
		test.checkInt('i', "e%i", 4, null);
		test.checkDouble("%f", 0,
				"Malformed floating point number: no digits in exponent");
		test.checkDouble("%f", 0,
				"Malformed floating point number: no digits in exponent");
		test.checkDouble("%f", 3.4, null);
		test.checkChar("%c", 'g', null);

		test.checkDouble("yy%4f45", 12.3, null);
		test.checkDouble("%1f", 2, null);
		test
				.checkDouble("%1f", 0,
						"Malformed floating point number: no digits");
		test.checkDouble("%2f", 45, null);
		test.checkDouble("%3f", 0,
				"Malformed floating point number: no digits in exponent");
		test.checkDouble("%3f", 1.5, null);
		test.checkDouble("%3f", 3e2, null);
		test.checkChar("%c", '0', null);

		System.out.println("\nPassed\n");
	}

	void checkChar(String s, char val, String emsg)
	{
		Exception ex = null;
		char res = 0;

		try
		{
			res = reader.scanChar(s);
		} catch (Exception e)
		{
			ex = e;
		}

		checkException(ex, emsg);

		if (emsg != null)
		{
			return;
		}

		if (res != val)
		{
			System.out.println("Expected char " + val + ", got " + res);
			(new Throwable()).printStackTrace();
			System.exit(1);
		}
	}

	void checkChars(String s, String valStr, String emsg)
	{
		Exception ex = null;
		char[] res = null;
		String resStr;

		try
		{
			res = reader.scanChars(s);
		} catch (Exception e)
		{
			ex = e;
		}

		checkException(ex, emsg);

		if (emsg != null)
		{
			return;
		}

		resStr = String.valueOf(res);

		if (!resStr.equals(valStr))
		{
			System.out.println("Expected chars '" + valStr + "', got '"
					+ resStr + "'");
			(new Throwable()).printStackTrace();
			System.exit(1);
		}
	}

	void checkDouble(String s, double val, String emsg)
	{
		Exception ex = null;
		double res = 0;

		try
		{
			res = reader.scanDouble(s);
		} catch (Exception e)
		{
			ex = e;
		}

		checkException(ex, emsg);

		if (emsg != null)
		{
			return;
		}

		if (res != val)
		{
			System.out.println("Expected double " + val + ", got " + res);
			(new Throwable()).printStackTrace();
			System.exit(1);
		}
	}

	void checkInt(char code, String s, int val, String emsg)
	{
		Exception ex = null;
		int res = 0;

		try
		{
			switch (code)
			{
			case 'i':
			{
				res = reader.scanInt(s);

				break;
			}

			case 'd':
			{
				res = (int) reader.scanDec(s);

				break;
			}

			case 'x':
			{
				res = (int) reader.scanHex(s);

				break;
			}

			case 'o':
			{
				res = (int) reader.scanOct(s);

				break;
			}
			}
		} catch (Exception e)
		{
			ex = e;
		}

		checkException(ex, emsg);

		if (emsg != null)
		{
			return;
		}

		if (res != val)
		{
			System.out.println("Expected '" + code + "' " + val + ", got "
					+ res);
			(new Throwable()).printStackTrace();
			System.exit(1);
		}
	}

	void checkString(String s, String val, String emsg)
	{
		Exception ex = null;
		String res = null;

		try
		{
			res = reader.scanString(s);
		} catch (Exception e)
		{
			ex = e;
		}

		checkException(ex, emsg);

		if (emsg != null)
		{
			return;
		}

		if (!res.equals(val))
		{
			System.out.println("Expected string " + val + ", got " + res);
			(new Throwable()).printStackTrace();
			System.exit(1);
		}
	}

	private void checkException(Exception e, String emsg)
	{
		if ((emsg == null) && (e != null))
		{
			System.out.println("Unexpected exception:");
			e.printStackTrace();
			System.exit(1);
		} else if ((emsg != null) && (e == null))
		{
			System.out.println("Expected exception:\n" + emsg
					+ "\nbut got none");
			(new Throwable()).printStackTrace();
			System.exit(1);
		} else if ((emsg != null) && (e != null))
		{
			if (!emsg.equals(e.getMessage()))
			{
				System.out.println("Expected exception:\n" + emsg
						+ "\nbut got:\n" + e.getMessage());
				(new Throwable()).printStackTrace();
				System.exit(1);
			}
		}
	}

	private void checkLineAndCharNums(int numc, int numl)
	{
		boolean pass = false;

		if (reader.getLineNumber() != numl)
		{
			System.out.println("line number is " + reader.getLineNumber()
					+ ", expected " + numl);
		} else if (reader.getCharNumber() != numc)
		{
			System.out.println("char number is " + reader.getCharNumber()
					+ ", expected " + numc);
		} else
		{
			pass = true;
		}

		if (!pass)
		{
			new Throwable().printStackTrace();
			System.exit(1);
		}
	}

	private void checkRead(int n, String s)
	{
		char[] buf = new char[n];
		boolean pass = false;
		int k = 0;

		try
		{
			k = reader.read(buf, 0, n);
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		if ((k == -1) && (s != null))
		{
			System.out.println("read returns -1, expected " + s.length());
		} else if ((s == null) && (k != -1))
		{
			System.out.println("read returns " + k + ", expected -1");
		} else if ((s == null) && (k == -1))
		{
			pass = true;
		} else if (s.length() != k)
		{
			System.out
					.println("read returns " + k + ", expected " + s.length());
		} else
		{
			String res = new String(buf, 0, k);

			if (!res.equals(s))
			{
				System.out.println("read returns '" + res + "', expected '" + s
						+ "'");
			} else
			{
				pass = true;
			}
		}

		if (!pass)
		{
			new Throwable().printStackTrace();
			System.exit(1);
		}
	}
}

// /////////////////////////////////////////////////////////////////////////////
// END OF FILE.
// /////////////////////////////////////////////////////////////////////////////

// /////////////////////////////////////////////////////////////////////////////
// END OF FILE.
// /////////////////////////////////////////////////////////////////////////////
