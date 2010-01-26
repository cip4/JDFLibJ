/**
 * HashUtil.java
 * 
 * Copyright (c) 2004 Heidelberger Druckmaschinen AG, All Rights Reserved.
 */
package org.cip4.jdflib.util;

/**
 * This class provides some hashCode calculation utilities. Use the static methods of this class to generate
 * 
 * <pre>
 * hashCode()
 * </pre>
 * 
 * values in data objects. For example, to calculate the hashCode of a data object, use the methods of this class as follows:
 * 
 * <pre>
 * int myIntField;
 * Object myObject;
 * 
 * public int hashCode()
 * {
 * 	//        int hash = super.hashCode();    // use when not extending Object
 * 	int hash = 0; // use when extending Object
 * 	hash = HashUtil.hashCode(hash, myIntField);
 * 	hash = HashUtil.hashCode(hash, myObject);
 * 	return hash;
 * }
 * </pre>
 * 
 * <br>
 * Hint: Start your hashCode calculation depending on the object your data object extends. If you extend Object initialize your hash value to 0. Otherwise
 * initialize hash to super.hashCode(). See the example code.
 * 
 * @author Manfred Steinbach
 */
public class HashUtil extends Object
{
	/**
	 * 
	 */
	public static final int PRIME = 1000003;

	private HashUtil()
	{ // Prevent an instantiation
		super();
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final boolean x)
	{
		return PRIME * source + (x ? 1 : 0);
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final int x)
	{
		return PRIME * source + x;
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final long x)
	{
		return PRIME * source + (int) (PRIME * (x >>> 32) + (x & 0xFFFFFFFF));
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final float x)
	{
		return hashCode(source, ((new Float(x).equals(new Float(0.0))) ? 0 : Float.floatToIntBits(x)));
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final double x)
	{
		return hashCode(source, ((new Double(x).equals(new Double(0.0))) ? 0L : Double.doubleToLongBits(x)));
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final Object x)
	{
		return (null == x) ? 0 : PRIME * source + x.hashCode();
	}

	/**
	 * @param source
	 * @param x
	 * @return
	 */
	public static final int hashCode(final int source, final Object[] x)
	{
		int sourceLocal = source;

		if (null != x)
		{
			final int len = x.length;
			for (int i = 0; i < len; i++)
			{
				sourceLocal = hashCode(sourceLocal, x[i]);
			}
		}

		return sourceLocal;
	}
}
