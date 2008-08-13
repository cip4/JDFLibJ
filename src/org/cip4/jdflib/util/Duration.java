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
/*
 * Duration.java
 * @author Dietrich Mucha
 * 
 * Copyright (C) 2004 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.util;

/**
 * @deprecated use the JDFDuration class only
 * @author prosirai
 * 
 */
public interface Duration
{

	static final long serialVersionUID = 1L;

	// private static final String REGEX_DURATION_RESTRICTED is a
	// RegularExpression
	// for a validation of incoming duration Strings, where is important that
	// values of seconds, minutes do not exceed 59, hours do not exceed 23...
	// It's a restricted form of a REGEX_DURATION
	//
	// Formatted string looks like "PyYmMdDThHmMsS"
	//
	// date Part "yYmMdD"
	// year (0?[0-9]|[1-9][0-9]) -- 0(00) - 99 are valid years
	// month (0?[0-9]|1[01]) -- 0(00) - 11 are valid months
	// day (0?[0-9]|[12][0-9]|3[0]) -- 0(00) - 30 are valid days
	// 
	// time Part "hHmMsS"
	// hour (0?[0-9]|1[0-9]|2[0123]|) -- 0(00) - 23 are valid hours
	// min (0?[0-9]|[1-5][0-9]) -- 0(00) - 59 are valid seconds
	// sec (0?[0-9]|[1-5][0-9]) -- 0(00) - 59 are valid minutes
	// regual expressions to validate incoming duration Strings

	// private static final String REGEX_DURATION_RESTRICTED =
	// "[P]((0?[0-9]|[1-9][0-9])[Y])?((0?[0-9]|1[01])[M])?((0?[0-9]|[12][0-9]|3[0])[D])?"
	// +
	// "([T]((0?[0-9]|1[0-9]|2[0123])[H])?((0?[0-9]|[1-5][0-9])[M])?((0?[0-9]|[1-5][0-9])[S])?)?"
	// ;

	// private static final String REGEX_DURATION is a RegularExpression
	// for a validation of incoming duration Strings
	// Formatted string looks like "PyYmMdDThHmMsS"
	// y,m,d,h,m,s are any int values.
	// E.g. expressions "P60D" that is equal 60 days or "PT68H" that is equal
	// 68hours are allowed

	static final String REGEX_DURATION = "[P](((\\d)*)[Y])?((\\d)*[M])?((\\d)*[D])?"
			+ "([T]((\\d)*[H])?((\\d)*[M])?((\\d)*[S])?)?";

	/**
	 * Format and return the duration set by 'setDuration(int i)' or 'setDurationString(String a_aDuration)' as an ISO
	 * conform String. For Exmaple: 'P1Y2M3DT10H30M'
	 * 
	 * @return String the duration formated as an ISO 8601 conform String if duration is '0' return value is 'PT00M'
	 */
	String getDurationISO();

	/**
	 * Set a duration. Durations are not bound to time or date and can be set independently
	 * 
	 * @return true the duration was set false the duration was not set, because a NumberFormatException was thrown (->
	 *         parseInt())
	 * 
	 * @param a_aDuration formatted duration string 'P1Y2M3DT10H30M'
	 */
	boolean setDurationISO(String a_aDuration);

	/**
	 * setDuration sets a duration for this in seconds. This duration is used in multiple classes of the jdf. Heating
	 * time for example.
	 * 
	 * @param i the duration in seconds. Values below '0' are set to '0'
	 */
	void setDuration(int i);

	/**
	 * the duration in seconds
	 * 
	 * @return int the duration in seconds; '0' default
	 */
	int getDuration();

	/**
	 * isLess - tests if the duration of this JDFDuration is longer then the duration of the specified JDFDuration.
	 * 
	 * @param x - the JDFDuration object you whant to compare with 'this' JDFDuration object
	 * @return boolean - true if the duration of this JDFDuration is longer then the duration of the JDFDuration 'x'.
	 */
	boolean isLonger(Duration x);

	/**
	 * isShorter - tests if the duration of this JDFDuration is less then the duration of the specified JDFDuration.
	 * 
	 * @param x - the JDFDuration object that duration you whant to compare with duration of 'this' JDFDuration object
	 * @return boolean - true if the duration of this JDFDuration is shorter then the duration of the JDFDuration 'x'.
	 */
	boolean isShorter(Duration x);

}
