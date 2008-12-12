package org.cip4.jdflib.util;

/**
 * trivial typesafe pair class
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * 11.12.2008
 * @param <aData> datatype of a
 * @param <bData> datatype of b
 */
public class MyPair<aData, bData>
{

	/**
	 * @param a aData value
	 * @param b bData value
	 */
	public MyPair(final aData a, final bData b)
	{
		super();
		this.a = a;
		this.b = b;
	}

	/**
	 * the aData value
	 */
	public aData a;
	/**
	 * the bData value
	 */
	public bData b;

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Pair" + a + "," + b;
	}
}