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

package org.cip4.jdflib.core;

/**
 *
 * list of Strings that are universal and independent of JDF
 *
 * @author rainerprosi
 * @date Sep 15, 2010
 */
public abstract class JDFCoreConstants
{
	/**
	 * max difference between two double values to be equal
	 */
	public static final double EPSILON = 0.000001;
	/**
	 *
	 */
	public static final String EMPTYSTRING = "";
	public static final String COMMA = ",";
	/** * */
	public static final String BLANK = " ";
	/** * */
	public static final String WHITESPACE = " \t\n\r\f";
	public static final String COLON = ":";
	/** * */
	public static final String HYPHEN = "-";
	/** * */
	public static final char CHAR_COLON = ':';
	/** * */
	public static final String QUOTE = "\"";
	/** * */
	public static final String SLASH = "/";
	public static final String BACK_SLASH = "\\";
	public static final String DOT = ".";
	/** * */
	public static final String DOTSLASH = "./";
	/** * */
	public static final String DOTDOTSLASH = "../";
	/** * */
	public static final String AET = "@";
	/** * */
	public static final String TILDE = "~";
	/** * */
	public static final String STAR = "*";
	/** * */
	public static final String UNDERSCORE = "_";
	/** */
	public static final String ID = "ID";

	/** * */
	public static final String XMLNS = "xmlns";
	/** * */
	public static final String XSI = "xsi";
	/** */
	public static final String XSITYPE = "xsi:type";
	/** */
	public static final String XSIURI = "http://www.w3.org/2001/XMLSchema-instance";
	/**
	 *
	 */
	public static final String XMLNSURI = "http://www.w3.org/2000/xmlns/";
	/** */
	public static final String XMLNSXSI = "xmlns:xsi";
	/** */
	/** */
	public static final String SCHEMALOCATION = "schemaLocation";

	/** * */
	public static final String TRUE = "true";
	/** * */
	public static final String FALSE = "false";

	/** * */
	public static final String BOOLEAN_TRUE = TRUE;
	/** * */
	public static final String BOOLEAN_FALSE = FALSE;
	/** * */
	public static final String NEGINF = "-INF";
	/** * */
	public static final String POSINF = "INF";

	// MIME types
	/** * */
	public static final String MIME_TEXTUNKNOWN = "text/unknown";
	/** * */
	public static final String MIME_TEXTXML = "text/xml";
	/** * */
	public static final String MIME_PNG = "image/x-png";
	/** * */
	public static final String MIME_TIFF = "image/tiff";
	/** * */
	public static final String MIME_PDF = "application/pdf";
	/** * */
	public static final String MIME_JPG = "image/jpeg";
	/** * */
	public static final String MIME_PS = "application/postscript";
	/** * */
	public static final String MIME_EPS = "application/postscript";
	/** * */
	public static final String MIME_PTK = "application/vnd.cip4-ptk+xml";
	public static final String MIME_XJDF = "application/vnd.cip4-xjdf+xml";
	public static final String MIME_XJMF = "application/vnd.cip4-xjmf+xml";
	/** * */
	public static final String MIME_PTK_JSON = "application/vnd.cip4-ptk+json";
	public static final String MIME_XJDF_JSON = "application/vnd.cip4-xjdf+json";
	public static final String MIME_XJMF_JSON = "application/vnd.cip4-xjmf+json";
	/** * */
	public static final String MIME_JDF = "application/vnd.cip4-jdf+xml";
	/** * */
	public static final String MIME_JMF = "application/vnd.cip4-jmf+xml";
	/** * */
	public static final String MIME_CIP3 = "application/vnd.cip4-ppf";
	/** * */
	public static final String MIME_PPML = "application/vnd.podi-ppml+xml";

}
