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
 * Testing class for ScanfFormat. Run the <tt>main</tt> method to test the
 * class.
 * 
 * @see ScanfFormat
 * @.author John E. Lloyd
 */
public class ScanfFormatTest
{
	// ~ Methods
	// ////////////////////////////////////////////////////////////////

	/**
	 * Tests the class ScanfFormat. If everything is OK, the string "Passed" is
	 * printed, and the program exits with status 0. Otherwise, diagnostics and
	 * a stack trace are printed, and the program exits with status 1.
	 */
	public static void main(String[] args)
	{
		test("foo %d bar", -1, 'd', null, "foo ", " bar");
		test("%d", -1, 'd', null, null, null);
		test("foo %% %123f bar", 123, 'f', null, "foo % ", " bar");
		test("f%% %% %12s %%bar", 12, 's', null, "f% % ", " %bar");
		test("xxx %[123e*&^] %%bar", -1, '[', "123e*&^", "xxx ", " %bar");

		test("xxx %[123e*&^] %%bar", -1, '[', "123e*&^", "xxx ", " %bar");
		test("x%[^]]%%bar", -1, '[', "^]", "x", "%bar");
		test("x%[^ ]]%%bar", -1, '[', "^ ", "x", "]%bar");
		test("x%[]]%%bar", -1, '[', "]", "x", "%bar");
		test("x%[ ]]%%bar", -1, '[', " ", "x", "]%bar");
		test("x%[^0-9-ad-8-]%%bar", -1, '[', "^0-9-ad-8-", "x", "%bar");

		// test("foo %", "Format string terminates with '%'");
		// test("foo %123", "Premature end of format string");
		// test("foo %0d", "Zero field width specified");
		// test("foo %w", "Illegal conversion character 'w'");
		// test("foo %[123sd", "Premature end of format string");
		// test("foo %[-123sd]", -1, '[', "-123sd", "foo ", null);
		// test("foo %[--123sd]",
		// "Misplaced '-' in character match spec '[--123sd]'");
		// test("foo %[^--123sd]",
		// "Misplaced '-' in character match spec '[^--123sd]'");
		// test("foo %[^1-23--sd]",
		// "Misplaced '-' in character match spec '[^1-23--sd]'");
		// test("foo %[^1-23sd--]",
		// "Misplaced '-' in character match spec '[^1-23sd--]'");
		// test("foo %d xx %x", "Extra '%' in format string");
		// test("foo %d xx %", "Extra '%' in format string");
		System.out.println("\nPassed\n");
	}

	// private static void myAssert(boolean ok)
	// {
	// if (!ok)
	// {
	// System.out.print("Assertion failed");
	// new Throwable().printStackTrace();
	// System.exit(1);
	// }
	// }
	private static boolean streq(String s1, String s2)
	{
		if ((s1 == null) && (s2 != null))
		{
			return false;
		} else if ((s1 != null) && (s2 == null))
		{
			return false;
		} else if ((s1 == null) && (s2 == null))
		{
			return true;
		} else
		{
			return s1.equals(s2);
		}
	}

	// private static void test(String s, String errMsg)
	// {
	// boolean tripped = false;
	//
	// try
	// {
	// ScanfFormat fmt = new ScanfFormat(s);
	// fmt = null;
	// }
	// catch (IllegalArgumentException e)
	// {
	// if (!e.getMessage().equals(errMsg))
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
	private static void test(String s, int width, int type, String cmatch,
			String prefix, String suffix)
	{
		try
		{
			ScanfFormat fmt = new ScanfFormat(s);

			if (fmt.width != width)
			{
				System.out.println("format '" + s + "'");
				System.out.println("width=" + fmt.width + " vs. " + width);
				System.exit(1);
			}

			if (fmt.type != type)
			{
				System.out.println("format '" + s + "'");
				System.out.println("type=" + (char) fmt.type + " vs. "
						+ (char) type);
				System.exit(1);
			}

			if (!streq(fmt.cmatch, cmatch))
			{
				System.out.println("format '" + s + "'");
				System.out.println("cmatch=" + fmt.cmatch + " vs. " + cmatch);
				System.exit(1);
			}

			if (!streq(fmt.prefix, prefix))
			{
				System.out.println("format '" + s + "'");
				System.out.println("prefix=" + fmt.prefix + " vs. " + prefix);
				System.exit(1);
			}

			if (!streq(fmt.suffix, suffix))
			{
				System.out.println("format '" + s + "'");
				System.out.println("suffix=" + fmt.suffix + " vs. " + suffix);
				System.exit(1);
			}
		} catch (Exception e)
		{
			System.out.println("Exception forming format " + s);
			e.printStackTrace();
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
