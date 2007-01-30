/**
 * ==========================================================================
 * class JDFDate extends Date
 * 
 * JDFDate additionally stores the timezone offset of the original date, 
 * so that after mDate = new JDFDate("1999-09-26T11:43:10+03:00") the following
 * equation holds: mDate.dateTimeISO() == "1999-09-26T11:43:10+03:00"
 * independent of the default timezone
 * ==========================================================================
 * COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2003. ALL RIGHTS RESERVED 
**/

package org.cip4.jdflib.util;


import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.JDFConstants;


public class JDFDuration implements Duration
{
    private static final long serialVersionUID = 1L;

    private int m_lDuration                     = 0;                            // in seconds
    
    // private static final String REGEX_DURATION_RESTRICTED is a RegularExpression
    // for a validation of incoming duration Strings, where is important that 
    // values of seconds, minutes do not exceed 59, hours do not exceed 23...
    // It's a restricted form of a REGEX_DURATION 
    //
    // Formatted string looks like "PyYmMdDThHmMsS"
    //
    // date Part "yYmMdD"
    // year  (0?[0-9]|[1-9][0-9])               --  0(00) - 99 are valid years
    // month (0?[0-9]|1[01])                    --  0(00) - 11 are valid months 
    // day   (0?[0-9]|[12][0-9]|3[0])           --  0(00) - 30 are valid days
    // 
    // time Part "hHmMsS"
    // hour  (0?[0-9]|1[0-9]|2[0123]|)          --  0(00) - 23 are valid hours
    // min   (0?[0-9]|[1-5][0-9])               --  0(00) - 59 are valid seconds
    // sec   (0?[0-9]|[1-5][0-9])               --  0(00) - 59 are valid minutes
    //regual expressions to validate incoming duration Strings
    
     
    //private static final String REGEX_DURATION_RESTRICTED = "[P]((0?[0-9]|[1-9][0-9])[Y])?((0?[0-9]|1[01])[M])?((0?[0-9]|[12][0-9]|3[0])[D])?" +
    //                                             "([T]((0?[0-9]|1[0-9]|2[0123])[H])?((0?[0-9]|[1-5][0-9])[M])?((0?[0-9]|[1-5][0-9])[S])?)?";
    
    
    // private static final String REGEX_DURATION is a RegularExpression
    // for a validation of incoming duration Strings
    // Formatted string looks like "PyYmMdDThHmMsS"
    // y,m,d,h,m,s are any int values. 
    // E.g. expressions "P60D" that is equal 60 days or "PT68H" that is equal 68hours are allowed

    
    private static final String REGEX_DURATION = "[P](((\\d)*)[Y])?((\\d)*[M])?((\\d)*[D])?" +
                                                 "([T]((\\d)*[H])?((\\d)*[M])?((\\d)*[S])?)?";
    
    
    /**
     * Allocates a <code>JDFDuration</code> object and initializes it with 0
     */
    public JDFDuration()
    {
        m_lDuration = 0;
    }
    
    /**
     * Makes a copy of the<code>JDFDuration</code> object 'd'
     */
    public JDFDuration(JDFDuration d)
    {
        m_lDuration = d.m_lDuration;
    }
    
    /**
     * Allocates a <code>JDFDuration</code> object and initializes it with 's'
     * @param s duration in seconds
     */
    public JDFDuration(int s)
    {
        m_lDuration = s;
    }
    
    /**
     * Allocates a <code>JDFDuration</code> object and initializes it with a
     * value of <code>strDuration</code>, represented as a formatted duration string. <br>
     * Duration examples: 
     * <li>"P1Y2M3DT10H30M"</li>
     * <li>"PM8T12M"</li>
     *
     * @param strDuration - formatted duration
     * @throws DataFormatException if strDuration is not a valid string 
     * representation of JDFDuration
     */
    public JDFDuration(String strDuration) throws DataFormatException
    {
        init(strDuration);
    }

    
    
    /** for debug purposes
     * 
     * @return  Object informations
     */
    public String toString()
    {
        return "JDFDuration[ m_lDuration=(" + m_lDuration + ")  --> " + super.toString() + " ]";
    }

   
    /**
     * Method init handles Strings of type: <br>
     * <li>"P1Y2M3DT10H30M"</li>
     * <li>"PM8T12M"</li>
     * <li>"PT30M"</li>
     * 
     * @param strDuration 
     * @throws DataFormatException
     */
    private void init(String strDuration) throws DataFormatException
    {

        boolean bComplete      = strDuration.matches(REGEX_DURATION);
        m_lDuration            = 0;
        
        if (bComplete)
        {
            setDurationISO(strDuration);
        }
        else
        {
            //its not a DateTime nor a Duration
            throw new DataFormatException("JDFDuration.init: invalid duration String " +  strDuration);
        }
    }

    
    /**
     * Format and return the duration set by 'setDuration(int i)' or
     * 'setDurationString(String a_aDuration)' as an ISO conforming String.<br>
     * For example: 'P1Y2M3DT10H30M'
     * 
     * @return String - the duration formatted as an ISO 8601 conforming String
     *                  if duration is '0' return value is 'PT00M'
     */
    public String getDurationISO()
    {
        if (m_lDuration == 0)
            return "PT00M";

        int i;
        int temp = m_lDuration;
        StringBuffer iso = new StringBuffer(20);
        iso.append("P"); //P is the indicator that 'iso' is a duration
        
        i = m_lDuration/(60*60*24*365);
        if(i!=0) 
        {
            iso.append(i).append("Y"); // string with years
            temp = m_lDuration - (i*60*60*24*365);
        }
        i = temp;
        i = i/(60*60*24*30);
        if(i!=0) 
        {
            iso.append(i).append("M"); // string with months
            temp = temp -(i*60*60*24*30);
        }
        i = temp%(60*60*24*30);
        i = i/(60*60*24);
        if(i!=0) 
        {
            iso.append(i).append("D"); // string with days
        }
        iso.append("T");
        
        i=m_lDuration%(60*60*24);
        i=i/(60*60);
        if(i!=0) 
        {
            iso.append(i).append("H"); // string with hours
        }
        i = m_lDuration%(60*60);
        i = i/(60);
        if(i!=0) 
        {
            iso.append(i).append("M"); // string with minutes
        }
        i = m_lDuration%(60); 
        if(i!=0) 
        {
            iso.append(i).append("S"); // string with seconds
        }
            
        int lastIndex=iso.length()-1;
        if (iso.charAt(lastIndex)=='T')
            iso.deleteCharAt(lastIndex);
        
        return iso.toString();
    }
    
            
    /**
     * Set a duration. Durations are not bound to time or date and can be set independently
     * 
     * @return true  - the duration was set<br>
     *         false - the duration was not set, because a NumberFormatException was thrown (-> parseInt())
     *  
     * @param a_aDuration formatted duration string 'P1Y2M3DT10H30M' 
     */
    public boolean setDurationISO(String a_aDuration)
    {
        boolean result      = true;
        
        String strPeriod    = JDFConstants.EMPTYSTRING;
        String strDate      = JDFConstants.EMPTYSTRING;
        String strTime      = JDFConstants.EMPTYSTRING;
        int iYears          = 0;
        int iMonths         = 0;
        int iDays           = 0;
        int iHours          = 0;
        int iMinutes        = 0;
        int iSeconds        = 0;
        int iduration       = 0;
        int iTimeLastPos    = 0;
        int iDateLastPos    = 0;
        
        int iPPos = a_aDuration.indexOf("P");
        
        strPeriod = a_aDuration.substring(++iPPos, a_aDuration.length());
            
        // devide periodInstant into date and time part, which are separated by 'T'
        int iTPos = strPeriod.indexOf("T");

        if (iTPos >= 0) 
        {
            if (iTPos == 0) 
            { // e.g. if durationInstant looks like "PT10H30M" - without date part
                strTime = strPeriod.substring(1, strPeriod.length());
            } 
            else 
            { // e.g. if durationInstant looks like "P1Y2M3DT10H30M"
                strDate = strPeriod.substring(0, iTPos);
                strTime = strPeriod.substring(++iTPos, strPeriod.length());
            }
        }
        else 
        { // e.g. if durationInstant looks like "P1Y2M3D" - without time part
            strDate = strPeriod;
        }

        try
        {
            if (strDate.length() > 0)
            {
                int iYPos = strDate.indexOf("Y");
                if (iYPos > 0)
                {
                    iYears = Integer.parseInt(strDate.substring(0, iYPos));
                    iduration += iYears * 365 * 24 * 60 * 60;
                    iDateLastPos = ++iYPos;
                }

                int iMPos = strDate.indexOf("M");
                if (iMPos > 0)
                {
                    iMonths = Integer.parseInt(strDate.substring(iDateLastPos, iMPos));
                    iduration += iMonths * 30 * 24 * 60 * 60;
                    iDateLastPos = ++iMPos;
                }

                int iDPos = strDate.indexOf("D");
                if (iDPos > 0)
                {
                    iDays = Integer.parseInt(strDate.substring(iDateLastPos, iDPos));
                    iduration += iDays * 24 * 60 * 60;
                }
            }

            if (strTime.length() > 0)
            {
                int iHPos = strTime.indexOf("H");
                if (iHPos > 0)
                {
                    iHours = Integer.parseInt(strTime.substring(0, iHPos));
                    iduration += iHours * 60 * 60;
                    iTimeLastPos = ++iHPos;
                }
                int iMPos = strTime.indexOf("M");
                if (iMPos > 0)
                {
                    iMinutes = Integer.parseInt(strTime.substring(iTimeLastPos, iMPos));
                    iduration += iMinutes * 60;
                    iTimeLastPos = ++iMPos;
                }

                int iSPos = strTime.indexOf("S");
                if (iSPos > 0)
                {
                    iSeconds = Integer.parseInt(strTime.substring(iTimeLastPos, iSPos));
                    iduration += iSeconds;
                }
            }
            
            m_lDuration = iduration;
        }
        catch (NumberFormatException e)
        {
            result = false;
        }
        
        return result;
    }
    
    /**
     * setDuration: sets a duration for <code>this</code> in seconds. 
     * This duration is used in multiple classes of the JDF (e.g. Heating time).  
     * 
     * @param i the duration in seconds. Values below '0' are set to '0'
     */
    public void setDuration(int i)
    {
        m_lDuration = (i > 0 ? i : 0);
    }
    
    
    /**
     * the duration in seconds
     * 
     * @return int - the duration in seconds; '0' default
     */
    public int getDuration()
    {
        return m_lDuration;
    }
    
    /**
     * isLess - tests if the duration of this JDFDuration is longer than
     * the duration of the specified JDFDuration. 
     * 
     * @param x the JDFDuration object to compare to <code>this</code>
     * @return boolean - true if the duration of this JDFDuration is longer than
     * the duration of the JDFDuration 'x'. 
     */
    public boolean isLonger(Duration x)
    {
        return this.getDuration() > x.getDuration();
    }
    
       
    /**
     * isShorter - tests if the duration of this JDFDuration is less than
     * the duration of the specified JDFDuration. 
     * 
     * @param x the JDFDuration object to compare to <code>this</code>
     * @return boolean - true if the duration of this JDFDuration is shorter than
     * the duration of the JDFDuration 'x'. 
     */
    public boolean isShorter(Duration x)
    {
        return this.getDuration() < x.getDuration();
    }
    
  
   /**
    * Compares two JDFDuration objects for equality.<br>
    * The result is <code>true</code> if and only if the argument is 
    * not <code>null</code> and is a <code>JDFDuration</code> object that 
    * represents the same duration, as this object.
    * <p>
    */ 
    public boolean equals(Object other)
    {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (other.getClass() != getClass())
            return false;

        return (this.m_lDuration == ((JDFDuration) other).m_lDuration);
    }
    
    /**
     * hashCode: complements equals() to fulfill the equals/hashCode contract
     */
    public int hashCode()
    {
        return HashUtil.hashCode(0, m_lDuration);
    }
    
    
}