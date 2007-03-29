/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2007 The International Cooperation for the Integration of 
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

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.time.FastDateFormat;
import org.cip4.jdflib.core.JDFConstants;


public class JDFDate implements Comparable
{
    private static final long serialVersionUID  = 1L;
    private long lTimeInMillis                  = 0;
    private int m_TimeZoneOffsetInMillis        = 0;      // in miliseconds from GMT-time

    /**
     * Allocates a <code>JDFDate</code> object and initializes it so that 
     * it represents the time at which it was allocated, measured to the 
     * nearest millisecond. 
     */
    public JDFDate()
    {
        lTimeInMillis = System.currentTimeMillis();
    }

    /**
     * Allocates a <code>JDFDate</code> object and initializes it so that 
     * it represents the time point, expressed in milliseconds 
     * after January 1, 1970, 0:00:00 GMT.
     * 
     * @param iTime   current time in milliseconds after January 1, 1970, 0:00:00 GMT.
     * Use JDFDuration instead. This class will be modified to handle only JDFDate objects
     */
    public JDFDate(final long iTime)
    {
        lTimeInMillis = iTime;
    }



    /**
     * Allocates a <code>JDFDate</code> object and initializes it so that
     * the JDFDate represents a date set by <code>strDateTime</code>
     * Format of <code>strDateTime</code><p>
     * Valid DataTime Strings are:
     * <li>"yyyy-mm-ddThh:mm:ss.sss+hh:00"</li>  
     * <li>"yyyy-mm-ddThh:mm:ss+hh:00"</li>
     * <li>"yyyy-mm-ddThh:mm:ss-hh:00"</li>
     * <li>"yyyy-mm-ddThh:mm:ssZ"</li>
     * <p>
     * Attention!<br>
     * you can enter milliseconds, but <code>getDateTimeISO()</code> still returns the time rounded to full seconds.
     * Only <code>long getTimeInMillis()</code> returns the exact time
     *
     * @param strDateTime formatted date and time
     * @throws DataFormatException if strDateTime is not a valid DateTime
     * 
     * Attention!
     * you can enter milliseconds, but getDateTimeISO() still returns the time rounded to full seconds
     * only long getTimeInMillis() returns the exact time
     */
    public JDFDate(final String strDateTime) throws DataFormatException
    {
        init(strDateTime);
    }



    /** for debug purpose  
     * 
     * @return  Object informations
    @Override
     */
    public String toString()
    {
        return "JDFDate[ "+ getDateTimeISO()+ " m_TimeZoneOffsetInMillis=(" + getTimeZoneOffsetInMillis() + ")  --> "  + " ]";
    }



    /**
     * init - initializes a JDFDate object with a formatted ISO DateTime value<br>
     * Method init handles Strings of type: <br>
     * <li>yyyy-mm-ddThh:mm:ss+hh:00</li> 
     * <li>yyyy-mm-ddThh:mm:ss-hh:00</li>
     * <li>yyyy-mm-ddThh:mm:ssZ </li>
     * <li>yyyy-mm-dd</li>
     * <p>
     * The values for month, time etc must be valid time values (e.g. 27 hours or 87 sec are invalid)
     * <p>
     * Use JDFDuration instead. This class will be modified to handle only JDFDate objects
     * Deprecated are strings of the following type (express duration): 
     * <li>"P1Y2M3DT10H30M"</li>
     * <li>"PM8T12M"</li>
     * <li>"PT30M"</li>
     * 
     * @param strDateTime formatted date and time
     */
    private void init(String strDateTime) throws DataFormatException
    {
        if(strDateTime == null || strDateTime.equals(JDFConstants.EMPTYSTRING))
        {
            lTimeInMillis = System.currentTimeMillis();
            return;
        }

        try
        {

            if(strDateTime.indexOf("T")==-1)
                strDateTime+="T00:00:00Z";

            // check for zulu style time zone
            final int length = strDateTime.length();
            String lastChar=strDateTime.substring(length-1);

            // not necessarily valid but let's not be too picky
            if((lastChar.compareTo("a")>=0) && (lastChar.compareTo("z")<=0))
            {
                lastChar=lastChar.toUpperCase();
            }

            final int iCmp=lastChar.compareTo("A");
            final boolean bZulu = (iCmp>=0) && (iCmp<=25);
            // The last character is a ZULU style timezone
            if(bZulu)
            {
                final String strBuffer    = strDateTime.substring(0, length - 1);
                String bias=null;
                if(iCmp>=0 && iCmp<=8) //A-I
                {
                    bias="+0"+String.valueOf(iCmp+1);
                }
                else if(iCmp==9) //J
                {
                    throw new DataFormatException("JDFDate.init: invalid date String " +  strDateTime);
                }
                else if(iCmp>=10 && iCmp<=12) //K-M
                {
                    bias="+"+String.valueOf(iCmp);
                }
                else if(iCmp>=13 && iCmp<=21) //N-V
                {
                    bias="-0"+String.valueOf(iCmp-12);
                }
                else if(iCmp>=22 && iCmp<=24) //W-Y
                {
                    bias="-1"+String.valueOf(iCmp-22);
                }
                else if(iCmp==25 ) //Z
                {
                    bias="+00";
                }
                bias+=":00";

                strDateTime         = strBuffer +bias; //add the alphabetical timezone            
            }

            int decimalLength=0;
            final int indexOfDecimal = strDateTime.indexOf('.');
            if(indexOfDecimal!=-1){
                if(indexOfDecimal!=19){
                    // ignore for now
                }
                else
                {
                    decimalLength++;
                    while("0123456789".indexOf(strDateTime.charAt(indexOfDecimal+decimalLength))!=-1)
                    {
                        decimalLength++;
                    }
                }
            }

            //if the time looks like 2004-07-14T18:21:47 
            //check if there is an +xx:00 or -xx:00 at the end specifying the timezone
            if (   (strDateTime.indexOf('+', 19) == -1) && (strDateTime.indexOf('-', 19) == - 1) )  
            {
                //no + or - timezone found
                strDateTime += "+00:00"; //add the zulu timezone
            }


            setTimeZoneOffsetInMillis(0);
            // handle sign explicitly, because "+02" is no valid Integer, while "-02" and "02" are valid Integer
            setTimeZoneOffsetInMillis(3600 * 1000 * new Integer (strDateTime.substring(20+decimalLength,22+decimalLength)).intValue());

            if (strDateTime.charAt(19+decimalLength) == '-')
            {
                setTimeZoneOffsetInMillis(- getTimeZoneOffsetInMillis());
            }


            // interprete string
            final int iYear  = new Integer (strDateTime.substring( 0, 4)).intValue();
            final int iMonth = new Integer (strDateTime.substring( 5, 7)).intValue() - 1; // months are zero based in Java
            final int iDay   = new Integer (strDateTime.substring( 8,10)).intValue();
            final int iHour  = new Integer (strDateTime.substring(11,13)).intValue();
            final int iMinute= new Integer (strDateTime.substring(14,16)).intValue();
            final int iSecond= new Integer (strDateTime.substring(17,19)).intValue();
            if(!strDateTime.substring(4,5).equals("-") ||
                    !strDateTime.substring(7,8).equals("-") ||    
                    !strDateTime.substring(10,11).equals("T") ||    
                    !strDateTime.substring(13,14).equals(":") ||    
                    !strDateTime.substring(16,17).equals(":") ||
                    strDateTime.length()-decimalLength!=25) // 6 digit tz
            {
                throw new DataFormatException("JDFDate.init: invalid date String " +  strDateTime);
            }
            // set seconds, minutes, hours, days, years to GregorianCalendar
            final GregorianCalendar gregcal = new GregorianCalendar();
            gregcal.setTimeZone(new SimpleTimeZone(getTimeZoneOffsetInMillis(), JDFConstants.EMPTYSTRING));
            gregcal.clear();  // so milliseconds are set to zero
            gregcal.set ( iYear, iMonth, iDay, iHour, iMinute, iSecond );
            lTimeInMillis = gregcal.getTimeInMillis();
            if(decimalLength>1){ // now handle fractions of seconds
                if(decimalLength==2)
                {
                    lTimeInMillis+=new Integer(strDateTime.substring(20,21)).intValue()*100;
                }
                else if(decimalLength==3)
                {
                    lTimeInMillis+=new Integer(strDateTime.substring(20,22)).intValue()*10;
                }
                else
                {
                    lTimeInMillis+=new Integer(strDateTime.substring(20,23)).intValue();
                }
//              gregcal.setTimeInMillis(lTimeInMillis);
            }
        }
        catch(final IndexOutOfBoundsException ie)
        {
            //now that we no longer check the string for validation we have no catch this 
            throw new DataFormatException("JDFDate.init: invalid date String " +  strDateTime);
        }
        catch(final NumberFormatException ne)
        {
            throw new DataFormatException("JDFDate.init: invalid date String " +  strDateTime);
        }
    }



    /**
     * returns the date and time of this in none ISO pattern 'yyyyMMddHHmmss'
     * @return String - the date in pattern yyyyMMddHHmmss
     */
    public String getDateTime()
    {

        return FastDateFormat.getInstance("yyyyMMddHHmmss").format(this);
    }


    /**
     * setOffset: set the offset to this time. Note: The time stored in this is not resetted
     * if you want an offset based on current time use 'public MyDate(int iOffset)'
     *  
     * @param iOffset   offset time in seconds
     * @deprecated use addOffset
     */
    public void setOffset(int iOffset)
    {
        addOffset(iOffset, 0, 0, 0);
    }

    /**
     * add a given offset to this
     * multiple calls stack
     * 
     * @param seconds seconds to add to this
     * @param minutes minutes to add to this
     * @param hours hours to add to this
     * @param days days to add to this
     */
    public void addOffset(int seconds, int minutes, int hours, int days)
    {
        lTimeInMillis += 1000*(seconds + 60*minutes +3600* hours +3600*24*days);
    }    



    /**
     * format the date and time as an ISO 8601 conform String
     * 
     * @return date and time as String of form yyyy-mm-ddThh:mm:ss+hh:00
     */
    public String  getDateTimeISO()
    {
        final String timePattern = "yyyy'-'MM'-'dd'T'HH:mm:ssZZ";
        return getDateFormat(timePattern).format(createCalendar());
    }

    /**
     * @param timePattern
     * @return
     */
    private FastDateFormat getDateFormat(final String timePattern)
    {
        return FastDateFormat.getInstance(timePattern, new SimpleTimeZone(getTimeZoneOffsetInMillis(), JDFConstants.EMPTYSTRING));
    }

    /**
     * the date formated as defined in ISO 8601
     * 
     * @return String: the date of this of form yyyy-mm-dd   
     */
    public String  getDateISO()
    {
        final String timePattern = "yyyy'-'MM'-'dd";
        return getDateFormat(timePattern).format(createCalendar());
    }

    /**
     * format the time into a ISO conform String 
     * 
     * @return String: the time of this ISO 8601 in format hh:mm:ss
     */
    public String getTimeISO() 
    {
        final String timePattern = "HH:mm:ss";
        return getDateFormat(timePattern).format(createCalendar());
    }

    /**
     * the TimeZone as spezified in ISO 8601 +/-10:00 for example
     * @return String: the timezone
     */
    public String getTimeZoneISO() 
    {
        final String timePattern = "ZZ";
        return getDateFormat(timePattern).format(createCalendar());
    }

    /**
     * Tests if this date is after the specified date. 
     * 
     * @param x the date you wish to know if it is later than this
     * @return true if and only if the instant represented by this 
     * Date object is strictly later than the instant represented by x; false otherwise.
     */
    public boolean isLater(final JDFDate x)
    {
        return lTimeInMillis > x.getTimeInMillis();
    }

    /**
     * Tests if this date is before the specified date.
     * 
     * @param x the date you wish to know if it is eariler than this 
     * @return true if and only if the instant of time represented by this 
     * Date object is strictly earlier than the instant represented by x; false otherwise. 
     */
    public boolean isEarlier(final JDFDate x)
    {
        return lTimeInMillis < x.getTimeInMillis();
    }

    /**
     * get the time in milliseconds 
     * @return long - the time in milliseconds
     */
    public long getTimeInMillis()
    {
        return lTimeInMillis;
    }

    /**
     * set this time milliseconds
     * @param l time in milliseconds
     */
    public void setTimeInMillis(final long l)
    {
        lTimeInMillis = l;
    }


    private long createCalendar()
    {
        return lTimeInMillis;
    }

    public GregorianCalendar getCalendar()
    {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(new SimpleTimeZone(m_TimeZoneOffsetInMillis,JDFConstants.EMPTYSTRING));
        gregorianCalendar.setTimeInMillis(getTimeInMillis());
        return gregorianCalendar;
    }

    /**
     * true, if this is before other, also true if other==null
     * @param other JDFDate to compare
     * @return
     */
    public boolean before(final JDFDate other )
    {
        if(other==null)
            return true;
        return lTimeInMillis < other.lTimeInMillis;
    }

    /**
     * true, if this is after other, also true if other==null
     * @param other
     * @return
     */
    public boolean after( final JDFDate other )
    {
        if(other==null)
            return true;
        return lTimeInMillis > other.lTimeInMillis;
    }

    public Date getTime()
    {
        return new Date(lTimeInMillis);
    }

    public void setTime( final Date date )
    {
        lTimeInMillis = date.getTime();
    }

    /**
     * Compares two JDFDates for equality.<br>
     * The result is <code>true</code> if and only if the argument is 
     * not <code>null</code> and is a <code>JDFDate</code> object that 
     * represents the same point in time, to the millisecond, as this object.
     * <p>
     * Thus, two <code>JDFDate</code> objects are equal if and only if the 
     * <code>getTimeInMillis</code> method returns the same <code>long</code> 
     * value for both.
     */ 
    public boolean equals(final Object other)
    {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (other.getClass() != getClass())
            return false;

        return (this.getTimeInMillis()/1000 == ((JDFDate) other).getTimeInMillis()/1000); 
    }

    /**
     * hashCode complements equals() to fulfill the equals/hashCode contract
     */
    public int hashCode()
    {
        return HashUtil.hashCode(super.hashCode(), getTimeZoneOffsetInMillis());
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * the value 0 if the argument is a Date equal to this Date; 
     * a value less than 0 if the argument is a Date after this Date; 
     * and a value greater than 0 if the argument is a Date before this Date.
     */
    public int compareTo(final Object arg0)
    {        
        return (int)(getTimeInMillis()/1000)-(int)(((JDFDate)arg0).getTimeInMillis()/1000);
    }

    /**
     * @param timeZoneOffsetInMillis The timeZoneOffsetInMillis to set.
     */
    private void setTimeZoneOffsetInMillis(final int timeZoneOffsetInMillis)
    {
        m_TimeZoneOffsetInMillis = timeZoneOffsetInMillis;
    }

    /**
     * @return Returns the timeZoneOffsetInMillis.
     */
    private int getTimeZoneOffsetInMillis()
    {
        return m_TimeZoneOffsetInMillis;
    }





    /**
     * Makes a copy of the<code>JDFDate</code> object
     * @param JDFDate d - the date object to make a copy of
     * deprecated - Only usage of JDFDate as a duration object was deprecated. 
     * Use JDFDuration instead. This class will be modified to handle only JDFDate objects
     */
//  public JDFDate(JDFDate d)
//  {
//  lTimeInMillis = d.getTimeInMillis();
//  }

    /**
     * @deprecated  Use class JDFDuration instead 
     * setDuration sets a duration for this in seconds. This duration is used in  
     * multiple classes of the jdf. Heating time for example.  
     * 
     * @param i the duration in seconds. Values below '0' are set to '0'
     */
//  public void setDuration(int i)
//  {
//  m_lDuration = (i >= 0 ? i : 0);
//  }



    /**
     * * @deprecated  Use class JDFDuration instead 
     * Format and return the duration set by 'setDuration(int i)' or
     * 'setDurationString(String a_aDuration)' as an ISO conform String.
     * For Exmaple: 'P1Y2M3DT10H30M'
     * 
     * @return String   the duration formated as an ISO 8601 conform String
     *                  if duration is '0' return value is 'PT00M'
     */
//  public String getDurationISO()
//  {
//  if (m_lDuration == 0)
//  return "PT00M";

//  int i;
//  int temp = m_lDuration;
//  StringBuffer iso = new StringBuffer(20);
//  iso.append("P"); //P is the indicator that 'iso' is a duration

//  i = m_lDuration/(60*60*24*365);
//  if(i!=0) 
//  {
//  iso.append(i).append("Y"); // string with years
//  temp = m_lDuration - (i*60*60*24*365);
//  }
//  i = temp;
//  i = i/(60*60*24*30);
//  if(i!=0) 
//  {
//  iso.append(i).append("M"); // string with months
//  temp = temp -(i*60*60*24*30);
//  }
//  i = temp%(60*60*24*30);
//  i = i/(60*60*24);
//  if(i!=0) 
//  {
//  iso.append(i).append("D"); // string with days
//  }
//  iso.append("T");

//  i=m_lDuration%(60*60*24);
//  i=i/(60*60);
//  if(i!=0) 
//  {
//  iso.append(i).append("H"); // string with hours
//  }
//  i = m_lDuration%(60*60);
//  i = i/(60);
//  if(i!=0) 
//  {
//  iso.append(i).append("M"); // string with minutes
//  }
//  i = m_lDuration%(60); 
//  if(i!=0) 
//  {
//  iso.append(i).append("S"); // string with seconds
//  }

//  int lastIndex=iso.length()-1;
//  if (iso.charAt(lastIndex)=='T')
//  iso.deleteCharAt(lastIndex);

//  return iso.toString();
//  }



    /**
     * deprecated Use class JDFDuration instead
     */ 
//  private int m_lDuration                     = 0;      // duration in seconds

    /**
     * @deprecated  Use class JDFDuration instead 
     * Set a duration. Durations are not bound to time or date and can be set independently
     *  
     * @param a_aDuration a String of the form 'P1Y2M3DT10H30M' 
     */
//  public void setDurationISO(String a_aDuration) throws DataFormatException
//  {
//  String strPeriod    = JDFConstants.EMPTYSTRING;
//  String strDate      = JDFConstants.EMPTYSTRING;
//  String strTime      = JDFConstants.EMPTYSTRING;
//  int iYears          = 0;
//  int iMonths         = 0;
//  int iDays           = 0;
//  int iHours          = 0;
//  int iMinutes        = 0;
//  int iSeconds        = 0;
//  int iduration       = 0;
//  int iTimeLastPos    = 0;
//  int iDateLastPos    = 0;

//  if (a_aDuration.indexOf(JDFConstants.BLANK)!=-1) {
//  throw new DataFormatException("JDFDate illegal string: "+ a_aDuration);
//  }

//  int iPPos = a_aDuration.indexOf("P");

//  strPeriod = a_aDuration.substring(++iPPos, a_aDuration.length());

//  // devide periodInstant into date and time part, which are separated by 'T'
//  int iTPos = strPeriod.indexOf("T");

//  if (iTPos >= 0) 
//  {
//  if (iTPos == 0) 
//  { // e.g. if durationInstant looks like "PT10H30M" - without date part
//  strTime = strPeriod.substring(1, strPeriod.length());
//  } 
//  else 
//  { // e.g. if durationInstant looks like "P1Y2M3DT10H30M"
//  strDate = strPeriod.substring(0, iTPos);
//  strTime = strPeriod.substring(++iTPos, strPeriod.length());
//  }
//  }
//  else 
//  { // e.g. if durationInstant looks like "P1Y2M3D" - without time part
//  strDate = strPeriod;
//  }

//  if (strDate.length() > 0)
//  {
//  int iYPos = strDate.indexOf("Y");
//  if (iYPos > 0)
//  {
//  iYears       = Integer.parseInt(strDate.substring (0, iYPos));
//  iduration   += iYears * 365*24*60*60; 
//  iDateLastPos = ++iYPos; 
//  }

//  int iMPos = strDate.indexOf("M");
//  if (iMPos > 0)
//  {
//  iMonths    = Integer.parseInt(strDate.substring (iDateLastPos, iMPos));
//  iduration += iMonths * 30*24*60*60;
//  iDateLastPos = ++iMPos;
//  }

//  int iDPos = strDate.indexOf("D");
//  if (iDPos > 0)
//  {
//  iDays      = Integer.parseInt(strDate.substring (iDateLastPos, iDPos));
//  iduration += iDays * 24*60*60;
//  }
//  }

//  if (strTime.length() > 0)
//  {
//  int iHPos = strTime.indexOf("H");
//  if (iHPos > 0)
//  {
//  iHours = Integer.parseInt(strTime.substring (0, iHPos));
//  iduration += iHours * 60*60; 
//  iTimeLastPos = ++iHPos;
//  }
//  int iMPos = strTime.indexOf("M");
//  if (iMPos > 0)
//  {
//  iMinutes     = Integer.parseInt(strTime.substring (iTimeLastPos, iMPos));
//  iduration   += iMinutes * 60;
//  iTimeLastPos = ++iMPos; 
//  }

//  int iSPos = strTime.indexOf("S");
//  if (iSPos > 0)
//  {
//  iSeconds = Integer.parseInt(strTime.substring (iTimeLastPos, iSPos));
//  iduration += iSeconds;
//  }
//  }
//  m_lDuration = iduration;
//  }

    /**
     * @deprecated  Use class JDFDuration instead 
     * 
     * isLonger - tests if the duration of this JDFDate is longer then
     * the duration of the specified JDFDate. 
     * 
     * @param x - the JDFDate object that duration you whant  
     * to compare with duration of 'this' JDFDate object 
     * @return boolean - true if the duration of this JDFDate is longer then
     * the duration of the JDFDate 'x'. 
     */
//  public boolean isLonger(JDFDate x)
//  {
//  return this.m_lDuration > x.m_lDuration;
//  }

    /**
     * @deprecated  Use class JDFDuration instead 
     * 
     * isShorter - tests if the duration of this JDFDate is less then
     * the duration of the specified JDFDate. 
     * 
     * @param x - the JDFDate object that duration you whant  
     * to compare with duration of 'this' JDFDate object 
     * @return boolean - true if the duration of this JDFDate is shorter then
     * the duration of the JDFDate 'x'. 
     */
//  public boolean isShorter(JDFDate x)
//  {
//  return this.m_lDuration < x.m_lDuration;
//  }

    /**
     * @deprecated  Use class JDFDuration instead 
     * the duration in seconds
     * 
     * @return int   the duration in seconds; '0' default
     */
//  public int getDuration()
//  {
//  return m_lDuration;
//  }

}