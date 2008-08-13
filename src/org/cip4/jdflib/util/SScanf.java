/**
 * 
 */
package org.cip4.jdflib.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Vector;

import org.cip4.jdflib.cformat.ScanfFormat;
import org.cip4.jdflib.cformat.ScanfMatchException;
import org.cip4.jdflib.cformat.ScanfReader;
import org.cip4.jdflib.core.VString;

/**
 * @author prosirai
 * 
 */
public class SScanf extends ScanfReader implements Iterator
{
	/**
	 * creates a scanf reader for a given string and format and returns the approriate object
	 * 
	 * valid format identifiers %f - returns Double %i - returns Integer %d - returns Integer %x - returns Integer %o -
	 * returns Integer %c - returns String %s - returns String
	 * 
	 * @param theString the String to scan
	 * @param format the formatting String to apply according to c++ sscanf rools
	 */
	private VString vFmt;
	private int pos = 0;

	public SScanf(String theString, String format)
	{
		super(new StringReader(theString));
		String newFMT = StringUtil.replaceString(format, "%%", "__comma__€ß-eher selten");
		vFmt = StringUtil.tokenize(newFMT, "%", false);
		int siz = vFmt.size();
		// make sure we have exactly 1 element per "real" %
		String firstChar = "%";
		if (siz > 1 && !format.startsWith("%"))
		{
			vFmt.set(1, vFmt.stringAt(0) + "%" + vFmt.stringAt(1));
			vFmt.remove(0);
			siz--;
			firstChar = "";
		}
		for (int i = 0; i < siz; i++)
		{
			String fmtString = vFmt.stringAt(i);
			fmtString = StringUtil.replaceString(fmtString, "__comma__€ß-eher selten", "%%");
			vFmt.set(i, firstChar + fmtString);
			firstChar = "%";
		}

	}

	/**
	 * scan a string using C++ sscanf functionality
	 * 
	 * @param theString the string to scan
	 * @param format the format to scan, see C++ spec for details
	 */
	public Vector sscanf()
	{
		Vector v = new Vector();
		while (hasNext())
			v.add(next());
		return v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.cformat.ScanfReader#scanDouble(org.cip4.jdflib.cformat .ScanfFormat)
	 */
	public double scanDouble(ScanfFormat fmt) throws IOException, ScanfMatchException, IllegalArgumentException
	{
		if ("dxoi".indexOf(fmt.type) >= 0) // also gracefully handle int as
			// double
			return scanLong(fmt);
		return super.scanDouble(fmt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cip4.jdflib.cformat.ScanfReader#scanString(org.cip4.jdflib.cformat .ScanfFormat)
	 */
	public String scanString(ScanfFormat fmt) throws IOException, IllegalArgumentException
	{
		if ("di".indexOf(fmt.type) >= 0) // also gracefully handle int as double
			return Long.toString(scanLong(fmt), 10);
		if ("o".indexOf(fmt.type) >= 0) // also gracefully handle int as double
			return Long.toString(scanLong(fmt), 8);
		if ("x".indexOf(fmt.type) >= 0) // also gracefully handle int as double
			return Long.toString(scanLong(fmt), 16);
		if ("f".indexOf(fmt.type) >= 0) // also gracefully handle int as double
			return Double.toString(scanDouble(fmt));
		if ("c".indexOf(fmt.type) >= 0) // also gracefully handle int as double
			return new String(scanChars(fmt));
		return super.scanString(fmt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext()
	{
		return pos < vFmt.size();
	}

	/**
	 * returns the next Object (@see the constructor), null if the string has been completely parsed or an invalid
	 * format is scanned
	 */
	public Object next()
	{
		if (!hasNext())
			return null;
		String fmtString = vFmt.stringAt(pos++);
		ScanfFormat fmt = new ScanfFormat(fmtString);
		try
		{
			if ("dxoi".indexOf(fmt.type) >= 0)
				return new Integer(scanInt(fmt));
			if ("f".indexOf(fmt.type) >= 0)
				return new Double(scanDouble(fmt));
			return scanString(fmt);
		}
		catch (IllegalArgumentException x)
		{
			pos = 999999;
			return null;
		}
		catch (IOException x)
		{
			pos = 999999;
			return null;
		}

	}

	/**
	 * NOT IMPLEMENTED - the iterator is only forward
	 * 
	 * @see java.util.Iterator#remove()
	 */
	public void remove()
	{
		throw new IllegalArgumentException("remove not implemented!");

	}

	/**
	 * convenience static function - see the constructor for details
	 * 
	 * @param theString
	 * @param format
	 * @return Vector of scanned objects - see constructor for details
	 */
	public static Vector sscanf(String theString, String format)
	{
		return new SScanf(theString, format).sscanf();
	}
}
