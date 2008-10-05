package org.cip4.jdflib.util;

/**
 * very simple and fast mutable integer class
 * 
 * @author prosirai
 * 
 */
public class MyInteger
{
	/**
	 * @param _i
	 */
	public MyInteger(int _i)
	{
		super();
		this.i = _i;
	}

	/**
	 * the int value
	 */
	public int i = 0;

	/**
	 * @see java.lang.Object#toString()
	 * @return the value of int as a string
	 */
	@Override
	public String toString()
	{
		return String.valueOf(i);
	}
}