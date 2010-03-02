/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of 
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of 
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of 
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration 
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software 
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */

/**
 * ==========================================================================
 * class JDFDate 
 * 
 * JDFDate additionally stores the timezone offset of the original date, 
 * so that after mDate = new JDFDate("1999-09-26T11:43:10+03:00") the following
 * equation holds: mDate.dateTimeISO() == "1999-09-26T11:43:10+03:00"
 * independent of the default timezone
 * ==========================================================================
 * COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2003. ALL RIGHTS RESERVED 
 **/

package org.cip4.jdflib.util;

import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.time.FastDateFormat;
import org.cip4.jdflib.core.JDFConstants;

/**
 * class to manipulate date and time according to ISO 8601<br/>
 * the date and time are in local time with the respective time zone specified
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class JDFDate implements Comparable<Object>, Cloneable, Comparator<JDFDate>
{
	private static final long serialVersionUID = 1L;
	private long lTimeInMillis = 0;
	private int m_TimeZoneOffsetInMillis = 0; // in miliseconds from GMT-time
	private static FastCalendar fastCalendar = new FastCalendar();

	private static class FastCalendar
	{
		private final GregorianCalendar gregcal;
		private final TimeZone tz;

		/**
		 * class to reuse one gregorian calendar and synchronize it
		 */
		protected FastCalendar()
		{
			super();
			gregcal = new GregorianCalendar();
			tz = TimeZone.getDefault();
		}

		/**
		 * @param timeInMillis 
		 * @return 
		 * 
		 */
		protected synchronized int getTimeZoneOffset(long timeInMillis)
		{
			return tz.getOffset(timeInMillis);
		}

		/**
		 * @param b
		 * @param decimalLength 
		 * @param tzInMillis 
		 * @return
		 */
		protected synchronized long getTimeInMillis(final byte[] b, int decimalLength, int tzInMillis)
		{
			final int iYear = getIntFromPos(b, 0, 4);
			final int iMonth = getIntFromPos(b, 5, 7) - 1; // months are zero based in Java
			final int iDay = getIntFromPos(b, 8, 10);
			final int iHour = getIntFromPos(b, 11, 13);
			final int iMinute = getIntFromPos(b, 14, 16);
			final int iSecond = getIntFromPos(b, 17, 19);
			// set seconds, minutes, hours, days, years to GregorianCalendar
			gregcal.setTimeZone(new SimpleTimeZone(tzInMillis, JDFConstants.EMPTYSTRING));
			gregcal.clear(); // so milliseconds are set to zero
			gregcal.set(iYear, iMonth, iDay, iHour, iMinute, iSecond);
			long lTimeInMillis = gregcal.getTimeInMillis();
			if (decimalLength > 1)
			{ // now handle fractions of seconds
				if (decimalLength == 2)
				{
					lTimeInMillis += getIntFromPos(b, 20, 21) * 100;
				}
				else if (decimalLength == 3)
				{
					lTimeInMillis += getIntFromPos(b, 20, 22) * 10;
				}
				else
				{
					lTimeInMillis += getIntFromPos(b, 20, 23);
				}
			}
			return lTimeInMillis;
		}

		/**
		 * parse a substring for integers - this is a bit faster then the original substring.intvalue...
		 * @param strDateTime
		 * @param pos1
		 * @param pos2
		 * @return the parsed integer value
		 */
		private int getIntFromPos(final byte[] strDateTime, final int pos1, final int pos2)
		{
			int ret = 0;
			int f = 1;
			for (int i = pos2 - 1; i >= pos1; i--)
			{
				ret += f * (strDateTime[i] - '0');
				f *= 10;
			}
			return ret;
		}

	}

	/**
	 * Allocates a <code>JDFDate</code> object and initializes it so that it represents the time at which it was allocated, measured to the nearest millisecond.
	 * Also sets the current time zone to the system default time zone
	 */
	public JDFDate()
	{
		this(System.currentTimeMillis());
	}

	/**
	 * Allocates a <code>JDFDate</code> object and initializes it so that it represents the time point, expressed in milliseconds after January 1, 1970, 0:00:00
	 * GMT. Also sets the current time zone to the system default time zone
	 * 
	 * @param iTime current time in milliseconds after January 1, 1970, 0:00:00 GMT. Use JDFDuration instead. This class will be modified to handle only JDFDate
	 * objects
	 */
	public JDFDate(final long iTime)
	{
		lTimeInMillis = iTime;
		m_TimeZoneOffsetInMillis = fastCalendar.getTimeZoneOffset(lTimeInMillis);
	}

	/**
	 * @param other the date to clone
	 */
	public JDFDate(final JDFDate other)
	{
		this();
		if (other != null)
		{
			lTimeInMillis = other.lTimeInMillis;
			m_TimeZoneOffsetInMillis = other.m_TimeZoneOffsetInMillis;
		}
	}

	/**
	 * Allocates a <code>JDFDate</code> object and initializes it so that the JDFDate represents a date set by <code>strDateTime</code> Format of
	 * <code>strDateTime</code>
	 * <p>
	 * Valid DataTime Strings are:
	 * <li>"yyyy-mm-ddThh:mm:ss.sss+hh:00"</li>
	 * <li>"yyyy-mm-ddThh:mm:ss+hh:00"</li>
	 * <li>"yyyy-mm-ddThh:mm:ss-hh:00"</li>
	 * <li>"yyyy-mm-ddThh:mm:ssZ"</li>
	 * <p>
	 * Attention!<br>
	 * you can enter milliseconds, but <code>getDateTimeISO()</code> still returns the time rounded to full seconds. Only <code>long getTimeInMillis()</code>
	 * returns the exact time
	 * 
	 * @param strDateTime formatted date and time
	 * @throws DataFormatException if strDateTime is not a valid DateTime
	 * 
	 * Attention! you can enter milliseconds, but getDateTimeISO() still returns the time rounded to full seconds only long getTimeInMillis() returns the exact
	 * time
	 */
	public JDFDate(final String strDateTime) throws DataFormatException
	{
		lTimeInMillis = 0;
		m_TimeZoneOffsetInMillis = 0;
		init(strDateTime);
	}

	/**
	 * factory style constructor that catches all exceptions and returns null if date is invalid
	 * @param date the formatted date string
	 * @return the JDFDate , null if date is not a valid string
	 */
	public static JDFDate createDate(final String date)
	{
		if (date == null)
		{
			return null;
		}
		try
		{
			return new JDFDate(date);
		}
		catch (final DataFormatException e)
		{
			return null;
		}
	}

	/**
	 * for debug purpose
	 * 
	 * @return Object informations
	 * @Override
	 */
	@Override
	public String toString()
	{
		return "JDFDate[ " + getDateTimeISO() + " m_TimeZoneOffsetInMillis=(" + getTimeZoneOffsetInMillis() + ")  --> " + " ]";
	}

	/**
	 * init - initializes a JDFDate object with a formatted ISO DateTime value<br>
	 * Method init handles Strings of type: <br>
	 * <li>yyyy-mm-ddThh:mm:ss+hh:00</li> <li>yyyy-mm-ddThh:mm:ss-hh:00</li> <li>
	 * yyyy-mm-ddThh:mm:ssZ</li> <li>yyyy-mm-dd</li>
	 * <p>
	 * The values for month, time etc must be valid time values (e.g. 27 hours or 87 sec are invalid)
	 * <p>
	 * Use JDFDuration instead. This class will be modified to handle only JDFDate objects Deprecated are strings of the following type (express duration):
	 * <li>"P1Y2M3DT10H30M"</li>
	 * <li>"PM8T12M"</li>
	 * <li>"PT30M"</li>
	 * 
	 * @param strDateTime formatted date and time
	 * @throws DataFormatException thrown if the string is not a reasonable iso date or date time
	 */
	private void init(String strDateTime) throws DataFormatException
	{
		if (strDateTime == null || strDateTime.equals(JDFConstants.EMPTYSTRING))
		{
			lTimeInMillis = System.currentTimeMillis();
			return;
		}

		try
		{
			strDateTime = handleString(strDateTime);
		}
		catch (final IndexOutOfBoundsException ie)
		{
			// now that we no longer check the string for validation we have no
			// catch this
			throw new DataFormatException("JDFDate.init: invalid date String " + strDateTime);
		}
		catch (final NumberFormatException ne)
		{
			throw new DataFormatException("JDFDate.init: invalid date String " + strDateTime);
		}
	}

	/**
	 * @param strDateTime
	 * @return
	 * @throws DataFormatException
	 */
	private String handleString(String strDateTime) throws DataFormatException
	{
		if (strDateTime.indexOf("T") == -1)
		{
			setTimeZoneOffsetInMillis(fastCalendar.getTimeZoneOffset(lTimeInMillis));
			strDateTime += "T00:00:00" + getTimeZoneISO();
		}

		// check for zulu style time zone
		strDateTime = handleZulu(strDateTime);

		int decimalLength = 0;
		final int indexOfDecimal = strDateTime.indexOf('.');
		if (indexOfDecimal != -1)
		{
			if (indexOfDecimal != 19)
			{
				// ignore for now
			}
			else
			{
				decimalLength++;
				while ("0123456789".indexOf(strDateTime.charAt(indexOfDecimal + decimalLength)) != -1)
				{
					decimalLength++;
				}
			}
		}

		// if the time looks like 2004-07-14T18:21:47
		// check if there is an +xx:00 or -xx:00 at the end specifying the
		// timezone
		if ((strDateTime.indexOf('+', 19) == -1) && (strDateTime.indexOf('-', 19) == -1))
		{
			setTimeZoneOffsetInMillis(fastCalendar.getTimeZoneOffset(lTimeInMillis));
		}
		else
		{
			// handle sign explicitly, because "+02" is no valid Integer,
			// while "-02" and "02" are valid Integer
			setTimeZoneOffsetInMillis(3600 * 1000 * new Integer(strDateTime.substring(20 + decimalLength, 22 + decimalLength)).intValue());
			if (strDateTime.charAt(19 + decimalLength) == '-')
			{
				setTimeZoneOffsetInMillis(-getTimeZoneOffsetInMillis());
			}
		}

		// interpret the string - low level enhances performance quite a bit...
		final byte[] b = strDateTime.getBytes();
		if (b[4] != '-' || b[7] != '-' || b[10] != 'T' || b[13] != ':' || b[16] != ':' || strDateTime.length() - decimalLength != 25) // 6 digit tz
		{
			throw new DataFormatException("JDFDate.init: invalid date String " + strDateTime);
		}

		lTimeInMillis = fastCalendar.getTimeInMillis(b, decimalLength, getTimeZoneOffsetInMillis());
		return strDateTime;
	}

	/**
	 * @param strDateTime
	 * @return
	 * @throws DataFormatException
	 */
	private String handleZulu(String strDateTime) throws DataFormatException
	{
		final int length = strDateTime.length();
		char lastChar = strDateTime.charAt(length - 1);

		if (lastChar >= 'a' && lastChar <= 'z')
			lastChar += 'A' - 'a';

		final int iCmp = lastChar - 'A';
		final boolean bZulu = (iCmp >= 0) && (iCmp <= 25);
		// The last character is a ZULU style timezone
		if (bZulu)
		{
			final String strBuffer = strDateTime.substring(0, length - 1);
			String bias = null;
			if (iCmp >= 0 && iCmp <= 8) // A-I
			{
				bias = "+0" + String.valueOf(iCmp + 1);
			}
			else if (iCmp == 9) // J
			{
				throw new DataFormatException("JDFDate.init: invalid date String " + strDateTime);
			}
			else if (iCmp >= 10 && iCmp <= 12) // K-M
			{
				bias = "+" + String.valueOf(iCmp);
			}
			else if (iCmp >= 13 && iCmp <= 21) // N-V
			{
				bias = "-0" + String.valueOf(iCmp - 12);
			}
			else if (iCmp >= 22 && iCmp <= 24) // W-Y
			{
				bias = "-1" + String.valueOf(iCmp - 22);
			}
			else if (iCmp == 25) // Z
			{
				bias = "+00";
			}

			bias += ":00";

			strDateTime = strBuffer + bias; // add the alphabetical timezone
		}
		return strDateTime;
	}

	/**
	 * returns the date and time of this in nonean arbitrary pattern
	 * 
	 * @param format the format string using {@link FastDateFormat} formatting
	 * @return String - the date as specified by the pattern
	 * @throws IllegalArgumentException
	 */
	public String getFormattedDateTime(final String format)
	{

		return getDateFormat(format).format(getCalendar());
	}

	/**
	 * returns the date and time of this in none ISO pattern 'yyyyMMddHHmmss'
	 * 
	 * @return String - the date in pattern yyyyMMddHHmmss
	 */
	public String getDateTime()
	{
		return getFormattedDateTime("yyyyMMddHHmmss");
	}

	/**
	 * setOffset: set the offset to this time. Note: The time stored in this is not resetted if you want an offset based on current time use 'public MyDate(int
	 * iOffset)'
	 * 
	 * @param iOffset offset time in seconds
	 * @deprecated use addOffset
	 */
	@Deprecated
	public void setOffset(final int iOffset)
	{
		addOffset(iOffset, 0, 0, 0);
	}

	/**
	 * add a given offset to this multiple calls stack
	 * 
	 * @param seconds seconds to add to this
	 * @param minutes minutes to add to this
	 * @param hours hours to add to this
	 * @param days days to add to this
	 */
	public void addOffset(final int seconds, final int minutes, final int hours, final int days)
	{
		lTimeInMillis += 1000 * (seconds + 60 * minutes + 3600 * hours + 3600 * 24 * days);
	}

	/**
	 * format the date and time as an ISO 8601 conform String<br/>
	 * the date and time are in local time with the respective time zone specified
	 * 
	 * @return date and time as String of form yyyy-mm-ddThh:mm:ss+zz:00
	 */
	public String getDateTimeISO()
	{
		final String timePattern = "yyyy'-'MM'-'dd'T'HH:mm:ssZZ";
		return getDateFormat(timePattern).format(lTimeInMillis);
	}

	/**
	 * format the date with no time added
	 * @return date and time as String of form yyyy-mm-dd
	 */
	public String getDateTimeISOBD()
	{
		final String timePatternBD = "yyyy'-'MM'-'dd'";
		return getDateFormat(timePatternBD).format(lTimeInMillis);
	}

	/**
	 * @param timePattern
	 * @return a date formatter
	 */
	private FastDateFormat getDateFormat(final String timePattern)
	{
		return FastDateFormat.getInstance(timePattern, new SimpleTimeZone(getTimeZoneOffsetInMillis(), JDFConstants.EMPTYSTRING));
	}

	/**
	 * the date formated as defined in ISO 8601<br/>
	 * the date is in local time with the respective time zone specified
	 * @return String: the date of this of form yyyy-mm-dd
	 */
	public String getDateISO()
	{
		final String timePattern = "yyyy'-'MM'-'dd";
		return getDateFormat(timePattern).format(lTimeInMillis);
	}

	/**
	 * format the time into a ISO conform String<br/>
	 * the time is in local time
	 * 
	 * @return String: the time of this ISO 8601 in format hh:mm:ss
	 */
	public String getTimeISO()
	{
		final String timePattern = "HH:mm:ss";
		return getDateFormat(timePattern).format(lTimeInMillis);
	}

	/**
	 * the TimeZone as spezified in ISO 8601 +/-10:00 for example
	 * 
	 * @return String: the timezone
	 */
	public String getTimeZoneISO()
	{
		final String timePattern = "ZZ";
		return getDateFormat(timePattern).format(lTimeInMillis);
	}

	/**
	 * Tests if this date is after the specified date.
	 * 
	 * @param x the date you wish to know if it is later than this
	 * @return true if and only if the instant represented by this Date object is strictly later than the instant represented by x; false otherwise.
	 */
	public boolean isLater(final JDFDate x)
	{
		return lTimeInMillis > x.getTimeInMillis();
	}

	/**
	 * Tests if this date is before the specified date.
	 * 
	 * @param x the date you wish to know if it is eariler than this
	 * @return true if and only if the instant of time represented by this Date object is strictly earlier than the instant represented by x; false otherwise.
	 */
	public boolean isEarlier(final JDFDate x)
	{
		return lTimeInMillis < x.getTimeInMillis();
	}

	/**
	 * get the time in milliseconds
	 * 
	 * @return long - the time in milliseconds
	 */
	public long getTimeInMillis()
	{
		return lTimeInMillis;
	}

	/**
	 * set this time milliseconds
	 * 
	 * @param l time in milliseconds
	 */
	public void setTimeInMillis(final long l)
	{
		lTimeInMillis = l;
	}

	/**
	 * @return the GregorianCalendar for this date
	 */
	public GregorianCalendar getCalendar()
	{
		final GregorianCalendar gregorianCalendar = new GregorianCalendar(new SimpleTimeZone(getTimeZoneOffsetInMillis(), JDFConstants.EMPTYSTRING));
		gregorianCalendar.setTimeInMillis(getTimeInMillis());
		return gregorianCalendar;
	}

	/**
	 * true, if this is before other, also true if other==null
	 * 
	 * @param other JDFDate to compare
	 * @return true if this is before other
	 */
	public boolean before(final JDFDate other)
	{
		if (other == null)
		{
			return true;
		}
		return lTimeInMillis < other.lTimeInMillis;
	}

	/**
	 * true, if this is after other, also true if other==null
	 * 
	 * @param other
	 * @return
	 */
	public boolean after(final JDFDate other)
	{
		if (other == null)
		{
			return true;
		}
		return lTimeInMillis > other.lTimeInMillis;
	}

	/**
	 * why the %&$/ is this called getTime, when it returns a date???
	 * @return
	 * @deprecated
	 */
	@Deprecated
	public Date getTime()
	{
		return new Date(lTimeInMillis);
	}

	/**
	 * why the %&$/ is this called setTime, when it uses a date???
	 * @param date
	 * @deprecated
	 */
	@Deprecated
	public void setTime(final Date date)
	{
		lTimeInMillis = date.getTime();
	}

	/**
	 * Compares two JDFDates for equality.<br>
	 * The result is <code>true</code> if and only if the argument is not <code>null</code> and is a <code>JDFDate</code> object that represents the same point
	 * in time, to the millisecond, as this object.
	 * <p>
	 * Thus, two <code>JDFDate</code> objects are equal if and only if the <code>getTimeInMillis</code> method returns the same <code>long</code> value for
	 * both.
	 */
	@Override
	public boolean equals(final Object other)
	{
		if (this == other)
		{
			return true;
		}
		if (other == null)
		{
			return false;
		}
		if (other.getClass() != getClass())
		{
			return false;
		}

		return (this.getTimeInMillis() / 1000 == ((JDFDate) other).getTimeInMillis() / 1000);
	}

	/**
	 * hashCode complements equals() to fulfill the equals/hashCode contract
	 */
	@Override
	public int hashCode()
	{
		return (int) (getTimeInMillis() / 1000l);
	}

	/**
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object) the value 0 if the argument is a Date equal to this Date; a value less than 0 if the argument is a
	 * Date after this Date; and a value greater than 0 if the argument is a Date before this Date.
	 */
	public int compareTo(final Object arg0)
	{
		if (arg0 instanceof String)
		{
			final String s = (String) arg0;
			try
			{
				return compareTo(new JDFDate(s));
			}
			catch (final DataFormatException x)
			{
				return 1;
			}

		}
		if (!(arg0 instanceof JDFDate))
		{
			return 1;
		}
		return (int) (getTimeInMillis() / 100) - (int) (((JDFDate) arg0).getTimeInMillis() / 100);
	}

	/**
	 * @param timeZoneOffsetInMillis The timeZoneOffsetInMillis to set.
	 */
	public void setTimeZoneOffsetInMillis(final int timeZoneOffsetInMillis)
	{
		m_TimeZoneOffsetInMillis = timeZoneOffsetInMillis;
	}

	/**
	 * @return Returns the timeZoneOffsetInMillis.
	 */
	public int getTimeZoneOffsetInMillis()
	{
		return m_TimeZoneOffsetInMillis;
	}

	/**
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public JDFDate clone()
	{
		return new JDFDate(this);
	}

	/**
	 * 
	 * @param d0
	 * @param d1
	 * @return
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(final JDFDate d0, final JDFDate d1)
	{
		return ContainerUtil.compare(d0, d1);
	}
}