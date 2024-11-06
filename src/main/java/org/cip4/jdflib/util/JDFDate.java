/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */

/**
 * ========================================================================== class JDFDate
 *
 * JDFDate additionally stores the timezone offset of the original date, so that after mDate = new JDFDate("1999-09-26T11:43:10+03:00") the following equation holds: mDate.dateTimeISO() ==
 * "1999-09-26T11:43:10+03:00" independent of the default timezone ========================================================================== COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2003. ALL
 * RIGHTS RESERVED
 **/

package org.cip4.jdflib.util;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.time.FastDateFormat;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;

/**
 * class to manipulate date and time according to ISO 8601<br/>
 * the date and time are in local time with the respective time zone specified
 *
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class JDFDate implements Comparable<Object>, Cloneable, Comparator<JDFDate>
{
	private long lTimeInMillis;
	private int m_TimeZoneOffsetInMillis; // in milliseconds from GMT-tim
	private static int defaultHour = 12;
	private static boolean wantISOMilliseconds = false;

	/**
	 * @return the wantISOMilliseconds
	 */
	public static boolean isWantISOMilliseconds()
	{
		return wantISOMilliseconds;
	}

	/**
	 * @param wantISOMilliseconds the wantISOMilliseconds to set
	 */
	public static void setWantISOMilliseconds(final boolean wantISOMilliseconds)
	{
		JDFDate.wantISOMilliseconds = wantISOMilliseconds;
	}

	/**
	 * Setter for defaultHour attribute.
	 *
	 * @param defaultHour the defaultHour to set
	 */
	public static void setDefaultHour(final int defaultHour)
	{
		if (defaultHour >= 0 && defaultHour <= 23)
		{
			JDFDate.defaultHour = defaultHour;
		}
	}

	/**
	 * @return the defaultHour
	 */
	public static int getDefaultHour()
	{
		return defaultHour;
	}

	private static FastCalendar fastCalendar = new FastCalendar();
	/**
	 *
	 */
	public static final String DATEISO = "yyyy'-'MM'-'dd'";
	/**
	 *
	 */
	final static String TIMEISO = "HH:mm:ss";
	final static String TIMEISO_MILLI = "HH:mm:ss.SSS";
	/**
	 *
	 */
	public static final String DATETIMEISO = "yyyy'-'MM'-'dd'T'HH:mm:ssZZ";
	public static final String DATETIMEISO_MILLI = "yyyy'-'MM'-'dd'T'HH:mm:ss.SSSZZ";
	/**
	 * iso - seconds are 0
	 */
	public static final String DATETIMEISO_0 = "yyyy'-'MM'-'dd'T'HH:mm:'00'ZZ";
	/**
	 * iso - seconds + minutes are 0
	 */
	public static final String DATETIMEISO_00 = "yyyy'-'MM'-'dd'T'HH:'00':'00'ZZ";
	/**
	 * iso - seconds + minutes + hours are 0
	 */
	public static final String DATETIMEISO_000 = "yyyy'-'MM'-'dd'T00':'00':'00'ZZ";
	/**
	 *
	 */
	public static final String DATETIMEREADABLE = "dd MMM yyyy HH:mm";
	/**
	 *
	 */
	public static final String DATENUMERIC_DDMM = "dd/MM/yyyy";
	/**
	 *
	 */
	public static final String DATENUMERIC_MMDD = "MM/dd/yyyy";
	/**
	 *
	 */
	public static final String DATETIMENUMERIC_DDMM = "dd/MM/yyyy HH:mm";
	/**
	 *
	 */
	public static final String DATETIMENUMERIC_MMDD = "MM/dd/yyyy HH:mm";

	private static class FastCalendar
	{
		private final GregorianCalendar gregcal;

		/**
		 * class to reuse one gregorian calendar and synchronize it
		 */
		FastCalendar()
		{
			super();
			gregcal = new GregorianCalendar();
		}

		/**
		 * @param b
		 * @param decimalLength
		 * @param tzInMillis
		 * @return
		 */
		synchronized long getTimeInMillis(final byte[] b, final int decimalLength, final int tzInMillis)
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
		 *
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
	 * Allocates a <code>JDFDate</code> object and initializes it so that it represents the time at which it was allocated, measured to the nearest millisecond. Also sets the
	 * current time zone to the system default time zone
	 */
	public JDFDate()
	{
		this(System.currentTimeMillis());
	}

	/**
	 * Allocates a <code>JDFDate</code> object and initializes it so that it represents the time point, expressed in milliseconds after January 1, 1970, 0:00:00 GMT. Also sets the
	 * current time zone to the system default time zone
	 *
	 * @param iTime current time in milliseconds after January 1, 1970, 0:00:00 GMT. Use JDFDuration instead. This class will be modified to handle only JDFDate objects
	 */
	public JDFDate(final long iTime)
	{
		try
		{
			new StringHandler(iTime).handle();
		}
		catch (final DataFormatException e)
		{
			lTimeInMillis = iTime;
			m_TimeZoneOffsetInMillis = TimeZone.getDefault().getOffset(iTime);
		}
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
	 * @param other the date to clone
	 */
	// public JDFDate(final LocalDateTime dateTime)
	// {
	// this();
	// if (dateTime != null)
	// {
	// JDFDate date = createDate(dateTime.toString());
	// lTimeInMillis = date.getTimeInMillis();
	// m_TimeZoneOffsetInMillis = date.getTimeZoneOffsetInMillis();
	// }
	// }

	/**
	 * Allocates a <code>JDFDate</code> object and initializes it so that the JDFDate represents a date set by <code>strDateTime</code> Format of <code>strDateTime</code>
	 * <p>
	 * Valid DataTime Strings are:
	 * <li>"yyyy-mm-ddThh:mm:ss.sss+hh:00"</li>
	 * <li>"yyyy-mm-ddThh:mm:ss+hh:00"</li>
	 * <li>"yyyy-mm-ddThh:mm:ss-hh:00"</li>
	 * <li>"yyyy-mm-ddThh:mm:ssZ"</li>
	 * <p>
	 * Attention!<br>
	 * you can enter milliseconds, but <code>getDateTimeISO()</code> still returns the time rounded to full seconds. Only <code>long getTimeInMillis()</code> returns the exact time
	 *
	 * @param strDateTime formatted date and time
	 * @throws DataFormatException if strDateTime is not a valid DateTime
	 *
	 *         Attention! you can enter milliseconds, but getDateTimeISO() still returns the time rounded to full seconds only long getTimeInMillis() returns the exact time
	 */
	public JDFDate(final String strDateTime) throws DataFormatException
	{
		lTimeInMillis = 0;
		m_TimeZoneOffsetInMillis = 0;
		init(strDateTime, -1, -1);
	}

	public JDFDate(final String strDateTime, final int defaultHour, final int defaultMinute) throws DataFormatException
	{
		lTimeInMillis = 0;
		m_TimeZoneOffsetInMillis = 0;
		init(strDateTime, defaultHour, defaultMinute);
	}

	/**
	 * factory style constructor that catches all exceptions and returns null if date is invalid
	 *
	 * @param date the formatted date string
	 * @return the JDFDate , null if date is not a valid string
	 */
	public static JDFDate createDate(String date)
	{
		date = StringUtil.normalize(date, false, null);
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
		return "JDFDate[ " + getDateTimeISO() + "]";
	}

	/**
	 * init - initializes a JDFDate object with a formatted ISO DateTime value<br>
	 * Method init handles Strings of type: <br>
	 * <li>yyyy-mm-ddThh:mm:ss+hh:00</li>
	 * <li>yyyy-mm-ddThh:mm:ss-hh:00</li>
	 * <li>yyyy-mm-ddThh:mm:ssZ</li>
	 * <li>yyyy-mm-dd</li>
	 * <p>
	 * The values for month, time etc must be valid time values (e.g. 27 hours or 87 sec are invalid)
	 * <p>
	 * Use JDFDuration instead. This class will be modified to handle only JDFDate objects Deprecated are strings of the following type (express duration):
	 * <li>"P1Y2M3DT10H30M"</li>
	 * <li>"PM8T12M"</li>
	 * <li>"PT30M"</li>
	 *
	 * @param strDateTime formatted date and time
	 * @param h
	 * @param m default offset minutes
	 * @throws DataFormatException thrown if the string is not a reasonable iso date or date time
	 */
	private void init(final String strDateTime, final int h, final int m) throws DataFormatException
	{
		if (StringUtil.isEmpty(strDateTime))
		{
			lTimeInMillis = System.currentTimeMillis();
			m_TimeZoneOffsetInMillis = TimeZone.getDefault().getOffset(lTimeInMillis);
			return;
		}

		try
		{
			final StringHandler stringHandler = new StringHandler(strDateTime, h, m);

			stringHandler.handle();
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

	private class StringHandler
	{
		private String strDateTime;
		private long l;
		private boolean timezoneSet;
		private int defaultH;
		private int defaultM;

		/**
		 *
		 * @param strDateTime
		 * @param m
		 * @param h
		 */
		StringHandler(final String strDateTime, final int h, final int m)
		{
			super();
			this.strDateTime = strDateTime;
			timezoneSet = true;
			this.defaultH = h >= 0 ? h : defaultHour;
			this.defaultM = m >= 0 ? m : 0;
			l = strDateTime.indexOf('-') >= 0 ? -1 : StringUtil.parseLong(strDateTime, -1);
		}

		/**
		 *
		 * @param millis
		 */
		StringHandler(final long millis)
		{
			super();
			this.strDateTime = "";
			l = millis;
		}

		/**
		 * @param strDateTime
		 * @throws DataFormatException
		 */
		private void handle() throws DataFormatException
		{
			if (l > 5000)
			{
				handleLongValue();
			}
			else
			{
				handleISOValue();
			}
		}

		/**
		 * @param strDateTime
		 * @return
		 * @throws DataFormatException
		 */
		private String handleZulu(String strDateTime) throws DataFormatException
		{
			int length = strDateTime.length();
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
			else if (((strDateTime.charAt(length - 6) != '+') && (strDateTime.charAt(length - 6) != '-')))
			{
				if (lastChar == ':')
				{
					strDateTime += "00";
					length += 2;
				}
				final int posColon = strDateTime.lastIndexOf(':');
				final int posPlus = strDateTime.lastIndexOf('+');
				final int posMinus = strDateTime.lastIndexOf('-');
				final int posPM = Math.max(posMinus, posPlus);
				if (posPM == length - 5 && posColon > posPM)
				{
					strDateTime = StringUtil.leftStr(strDateTime, -4) + '0' + StringUtil.rightStr(strDateTime, 4);
				}
				if (posColon < posPM && length - posPM < 3)
				{
					strDateTime += ":00";
					if (posPM == length - 2)
					{
						strDateTime = StringUtil.leftStr(strDateTime, -4) + '0' + StringUtil.rightStr(strDateTime, 4);
					}
				}
			}
			return strDateTime;
		}

		private void handleISOValue() throws DataFormatException
		{
			addMissingTime();
			// check for zulu style time zone
			strDateTime = handleZulu(strDateTime);

			final int decimalLength = handleDecimal();
			handleTimeZone(decimalLength);

			// interpret the string - low level enhances performance quite a bit...
			byte[] b = strDateTime.getBytes();
			int n = 0;
			for (int i = 0; i < 16; i++)
			{
				if ((b[i] < '0' || b[i] > '9') && (n++ > 4))
				{
					break;
				}
			}

			if (n > 4 || b[4] != '-' || b[7] != '-' || b[10] != 'T' || b[13] != ':' || b[16] != ':' || strDateTime.length() - decimalLength != 25) // 6 digit tz
			{
				cleanDateTime();
				b = strDateTime.getBytes();
			}
			lTimeInMillis = fastCalendar.getTimeInMillis(b, decimalLength, getTimeZoneOffsetInMillis());
			if (!timezoneSet)
			{
				m_TimeZoneOffsetInMillis = TimeZone.getDefault().getOffset(lTimeInMillis);
				lTimeInMillis = fastCalendar.getTimeInMillis(b, decimalLength, getTimeZoneOffsetInMillis());
			}
		}

		private void cleanDateTime() throws DataFormatException
		{
			cleanDate();
			cleanTime();
		}

		/**
		 * 
		 *
		 * @throws DataFormatException
		 */
		private void cleanDate() throws DataFormatException
		{
			final String date = StringUtil.token(strDateTime, 0, "T");
			final VString dates = StringUtil.tokenize(date, "-", false);
			if (dates != null && dates.size() >= 3)
			{
				final int year = StringUtil.parseInt(dates.get(0), 0);
				if (year <= 0 || year > 9999)
					throw new DataFormatException("JDFDate.init: invalid date Year " + dates.get(0));
				final int month = StringUtil.parseInt(dates.get(1), 0);
				if (month <= 0 || month > 12)
					throw new DataFormatException("JDFDate.init: invalid date Month " + dates.get(1));

				final int day = StringUtil.parseInt(dates.get(2), 0);
				if (day <= 0 || day > 31)
					throw new DataFormatException("JDFDate.init: invalid date Day " + dates.get(2));
				final NumberFormatter nf = new NumberFormatter();
				final String newDate = nf.formatInt(year, 4) + "-" + nf.formatInt(month, 2) + "-" + nf.formatInt(day, 2);
				strDateTime = StringUtil.replaceToken(strDateTime, 0, "T", newDate);
			}
			else
			{
				throw new DataFormatException("JDFDate.init: invalid date String " + strDateTime);
			}
		}

		/**
		 *
		 * @throws DataFormatException
		 */
		private void cleanTime() throws DataFormatException
		{
			String time = StringUtil.token(strDateTime, 1, "T");
			final String[] pms = new String[] { "+", "-" };
			String timeZone = null;
			for (final String pm : pms)
			{
				timeZone = StringUtil.token(time, 1, pm);
				if (timeZone != null)
				{
					time = StringUtil.token(time, 0, pm);
					timeZone = pm + timeZone;
					break;
				}
			}
			if (timeZone == null)
				throw new DataFormatException("bad time zone ");

			final StringArray times = StringArray.getVString(time, ":");
			if (times != null)
			{
				int hours = StringUtil.parseInt(times.get(0), -1);
				if (hours < 0 || hours > 23)
					hours = defaultHour;
				int minutes = StringUtil.parseInt(times.get(1), -1);
				if (minutes < 0 || minutes >= 60)
					minutes = 0;

				int seconds = StringUtil.parseInt(times.get(2), 0);
				if (seconds < 0 || seconds >= 60)
					seconds = 0;
				final NumberFormatter nf = new NumberFormatter();

				final String newTime = nf.formatInt(hours, 2) + ":" + nf.formatInt(minutes, 2) + ":" + nf.formatInt(seconds, 2) + timeZone;
				strDateTime = StringUtil.replaceToken(strDateTime, 1, "T", newTime);
			}
			else
			{
				throw new DataFormatException("JDFDate.init: invalid time String " + strDateTime);
			}
		}

		private void handleTimeZone(final int decimalLength) throws DataFormatException
		{
			// if the time looks like 2004-07-14T18:21:47 check if there is an +xx:00 or -xx:00 at the end specifying the time zone
			int posPlus = strDateTime.indexOf('+', 15);
			final int posMinus = strDateTime.indexOf('-', 15);
			if (posPlus == -1 && posMinus == -1)
			{
				setTimeZoneOffsetInMillis(TimeZone.getDefault().getOffset(lTimeInMillis));
				strDateTime += getTimeZoneISO();
				timezoneSet = false;
			}
			else if (posPlus >= 0 && posMinus >= 0)
			{
				throw new DataFormatException("bad date time string: " + strDateTime);
			}
			else
			{
				int minfac = 60000;
				if (posMinus > posPlus)
				{
					posPlus = posMinus;
					minfac = -minfac;
				}

				final String tzValue = strDateTime.substring(posPlus, posPlus + 3);
				if (!StringUtil.isInteger(tzValue))
				{
					throw new DataFormatException("bad date time string: " + strDateTime);
				}
				final int parseInt = StringUtil.parseInt(tzValue, 0);
				int parseMin = 0;
				if (strDateTime.length() >= posPlus + 6)
				{
					final String tzMinutes = strDateTime.substring(posPlus + 4, posPlus + 6);
					parseMin = StringUtil.parseInt(tzMinutes, 0);
				}

				setTimeZoneOffsetInMillis(3600 * 1000 * parseInt + minfac * parseMin);
			}
		}

		private int handleDecimal()
		{
			final int indexOfDecimal = strDateTime.indexOf('.');
			int decimalLength = 0;
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
						if (indexOfDecimal + decimalLength == strDateTime.length())
							break;

					}
				}
			}
			return decimalLength;
		}

		private void addMissingTime()
		{
			if (strDateTime.indexOf("T") == -1)
			{
				timezoneSet = false;
				if (l > 1000 && l < 5000)
					strDateTime += "-01-01";
				if (strDateTime.length() == 7)
					strDateTime += "-01";
				// nothing said and we don't know whether earliest or latest - take default as best guess
				strDateTime += getDefaultTime() + getTimeZoneISO();
			}
			else if (strDateTime.length() < 16)
			{
				timezoneSet = false;
				String buffer = getDefaultTime() + getTimeZoneISO();
				buffer = buffer.substring(16 - strDateTime.length());
				strDateTime += buffer;
			}
		}

		private String getDefaultTime()
		{
			final NumberFormatter numberFormatter = new NumberFormatter();
			return "T" + numberFormatter.formatInt(defaultH, 2) + ":" + numberFormatter.formatInt(defaultM, 2) + ":00";
		}

		private void handleLongValue()
		{
			final long l0 = System.currentTimeMillis();
			if (l0 / l >= 900) // this is roughly 10 years back
				l *= 1000l; // assume seconds
			lTimeInMillis = l;
			m_TimeZoneOffsetInMillis = TimeZone.getDefault().getOffset(lTimeInMillis);
		}

		@Override
		public String toString()
		{
			return "StringHandler [strDateTime=" + strDateTime + ", l=" + l + "]";
		}
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
	 * setOffset: set the offset to this time. Note: The time stored in this is not resetted if you want an offset based on current time use 'public MyDate(int iOffset)'
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
	 * add a given offset to this <br/>
	 * note: multiple calls stack
	 *
	 * @param seconds seconds to add to this
	 * @param minutes minutes to add to this
	 * @param hours hours to add to this
	 * @param days days to add to this
	 */
	public JDFDate addOffset(final int seconds, final int minutes, final int hours, final int days)
	{
		lTimeInMillis += 1000l * (seconds + 60l * minutes + 3600l * hours + 3600l * 24l * days);
		return this;
	}

	/**
	 *
	 * create a date with a relative offset defined in duration
	 *
	 * @param duration
	 * @param hour the fixed hour, if -1 don't set
	 * @param minute the fixed minute, if -1 don't set
	 * @return
	 */
	public JDFDate createDateFromDuration(final JDFDuration duration, final int hour, final int minute)
	{
		final JDFDate d = new JDFDate(this);
		if (duration != null)
		{
			d.setTimeInMillis(d.getTimeInMillis() + duration.getDurationMillis());
		}
		if (hour >= 0 && minute >= 0)
		{
			d.setTime(hour, minute, 0);
		}
		return d;
	}

	/**
	 * format the date and time as an ISO 8601 conform String<br/>
	 * the date and time are in local time with the respective time zone specified
	 *
	 * @return date and time as String of form yyyy-mm-ddThh:mm:ss+zz:00
	 */
	public String getDateTimeISO()
	{
		return getFormattedDateTime(wantISOMilliseconds ? DATETIMEISO_MILLI : DATETIMEISO);
	}

	/**
	 * format the date with no time added
	 *
	 * @return date and time as String of form yyyy-mm-dd
	 * @deprecated use @see {@link JDFDate#getDateISO()}
	 */
	@Deprecated
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
	 *
	 * @return String: the date of this of form yyyy-mm-dd
	 */
	public String getDateISO()
	{
		return getFormattedDateTime(DATEISO);
	}

	/**
	 * format the time into a ISO conform String<br/>
	 * the time is in local time
	 *
	 * @return String: the time of this ISO 8601 in format hh:mm:ss
	 */
	public String getTimeISO()
	{
		return getFormattedDateTime(wantISOMilliseconds ? TIMEISO_MILLI : TIMEISO);
	}

	/**
	 * the TimeZone as spezified in ISO 8601 +/-10:00 for example
	 *
	 * @return String: the timezone
	 */
	public String getTimeZoneISO()
	{
		final String timePattern = "ZZ";
		long t = lTimeInMillis;
		if (t < 5000)
			t = System.currentTimeMillis();
		return getDateFormat(timePattern).format(t);
	}

	/**
	 * Tests if this date is after the specified date.
	 *
	 * @param x the date you wish to know if it is later than this
	 * @return true if and only if the instant represented by this Date object is strictly later than the instant represented by x; false otherwise.
	 */
	public boolean isLater(final JDFDate x)
	{
		final long timeInMillis = x == null ? 0 : x.getTimeInMillis();
		return lTimeInMillis > timeInMillis;
	}

	/**
	 * Tests if this date is before the specified date.
	 *
	 * @param x the date you wish to know if it is eariler than this
	 * @return true if and only if the instant of time represented by this Date object is strictly earlier than the instant represented by x; false otherwise.
	 */
	public boolean isEarlier(final JDFDate x)
	{
		final long timeInMillis = x == null ? 0 : x.getTimeInMillis();
		return lTimeInMillis < timeInMillis;
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
	public JDFDate setTimeInMillis(final long l)
	{
		lTimeInMillis = l;
		return this;
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
	 *
	 * set the time without modifying the date
	 *
	 * @param h
	 * @param m
	 * @param s
	 */
	public JDFDate setTime(final int h, final int m, final int s)
	{
		// round milliseconds
		lTimeInMillis = ((lTimeInMillis + 500l) / 1000l) * 1000l;
		final String st = getFormattedDateTime(DATETIMEISO_000);
		try
		{
			init(st, -1, -1);
		}
		catch (final DataFormatException e)
		{
			// NOP
		}
		addOffset(s, m, h, 0);
		return this;
	}

	/**
	 * true, if this is before other
	 *
	 * @param other the time in milliseconds since 1970 (e.g. from System.currentTimeMillis())
	 * @return true if this is before other
	 * @deprecated use isEarlier
	 */
	@Deprecated
	public boolean before(final JDFDate other)
	{
		return isEarlier(other);
	}

	/**
	 * true, if this is before other
	 *
	 * @param other the time in milliseconds since 1970 (e.g. from System.currentTimeMillis())
	 * @return true if this is before other
	 */
	public boolean before(final long other)
	{
		return lTimeInMillis < other;
	}

	/**
	 * true, if this is after other, also true if other==null
	 *
	 * @param other
	 * @return true if this date is after other
	 * @deprecated use isLater
	 */
	@Deprecated
	public boolean after(final JDFDate other)
	{
		return isLater(other);
	}

	/**
	 * true, if this is after other
	 *
	 * @param other the time in milliseconds since 1970 (e.g. from System.currentTimeMillis())
	 * @return true if this date is after other
	 */
	public boolean after(final long other)
	{
		return lTimeInMillis > other;
	}

	/**
	 * why the %&$/ is this called getTime, when it returns a date???
	 *
	 * @return
	 * @deprecated
	 */
	@Deprecated
	public Date getTime()
	{
		return new Date(lTimeInMillis);
	}

	/**
	 *
	 * get the month as an integer
	 *
	 * @return (1-12)
	 */
	public int getMonth()
	{
		return 1 + getCalendar().get(Calendar.MONTH);
	}

	/**
	 *
	 * get the year as an integer
	 *
	 * @return the year
	 */
	public int getYear()
	{
		return getCalendar().get(Calendar.YEAR);
	}

	/**
	 *
	 * get the day of month as an integer
	 *
	 * @return the year
	 */
	public int getDay()
	{
		return getCalendar().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * why the %&$/ is this called setTime, when it uses a date???
	 *
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
	 * The result is <code>true</code> if and only if the argument is not <code>null</code> and is a <code>JDFDate</code> object that represents the same point in time, to the
	 * millisecond, as this object.
	 * <p>
	 * Thus, two <code>JDFDate</code> objects are equal if and only if the <code>getTimeInMillis</code> method returns the same <code>long</code> value for both.
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
	 * @see java.lang.Comparable#compareTo(java.lang.Object) the value 0 if the argument is a Date equal to this Date; a value less than 0 if the argument is a Date after this
	 *      Date; and a value greater than 0 if the argument is a Date before this Date.
	 */
	@Override
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
	public JDFDate setTimeZoneOffsetInMillis(final int timeZoneOffsetInMillis)
	{
		m_TimeZoneOffsetInMillis = timeZoneOffsetInMillis;
		return this;
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
	@Override
	public int compare(final JDFDate d0, final JDFDate d1)
	{
		return ContainerUtil.compare(d0, d1);
	}

	/**
	 *
	 * @return
	 */
	public int getHour()
	{
		return getCalendar().get(Calendar.HOUR_OF_DAY);
	}

	/**
	 *
	 * @return
	 */
	public int getMinute()
	{
		return getCalendar().get(Calendar.MINUTE);
	}

	/**
	 *
	 * @return
	 */
	public int getSecond()
	{
		return getCalendar().get(Calendar.SECOND);
	}

	/**
	 * not performance optimized dirty hack...
	 * 
	 * @param newYear
	 */
	public void setYear(final int newYear)
	{
		String s = getDateTimeISO();
		s = StringUtil.replaceToken(s, 0, "-", StringUtil.rightStr("0000" + Integer.valueOf(newYear), 4));
		final JDFDate snew = createDate(s);
		if (snew != null)
			setTimeInMillis(snew.getTimeInMillis());
	}
}