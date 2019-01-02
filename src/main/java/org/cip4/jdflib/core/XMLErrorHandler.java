/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
 * XMLErrorHandler.java - implementation of the error handler
 *
 * @author Elena Skobchenko
 */

package org.cip4.jdflib.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.util.StringUtil;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XMLErrorHandler implements ErrorHandler
{

	private static final String FATAL_ERROR = "FatalError";
	private static final String ERROR = "Error";
	private static final String WARNING = "Warning";
	public static final String VALIDATION_RESULT = "ValidationResult";
	private KElement root;
	XMLParser parser;
	InputSource src;
	private final Log log;
	private static boolean wantLog = true;

	/**
	 * @return the wantLog
	 */
	protected static boolean isWantLog()
	{
		return wantLog;
	}

	/**
	 * @param wantLog the wantLog to set
	 */
	protected static void setWantLog(final boolean wantLog)
	{
		XMLErrorHandler.wantLog = wantLog;
	}

	/**
	 *
	 */
	public XMLErrorHandler()
	{
		super();
		reset();
		log = LogFactory.getLog(getClass());
	}

	/**
	 * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
	 * @param exception
	 */
	@Override
	public void warning(final SAXParseException exception)
	{
		final String warn = getErrorMsg(exception);
		final KElement kEl = root.appendElement(WARNING);
		kEl.setAttribute("Message", warn);
		parser.m_lastExcept = exception;
		if (wantLog)
		{
			log.warn(warn);
		}
	}

	/**
	 * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
	 * @param exception
	 */
	@Override
	public void error(final SAXParseException exception)
	{
		final String er = getErrorMsg(exception);
		parser.m_lastExcept = exception;
		if ((er.indexOf("http://www.CIP4.org/JDFSchema") != -1) || (er.indexOf("is not declared for") == -1))
		{
			final KElement kEl = root.appendElement(ERROR);
			kEl.setAttribute("Message", er);
			if (wantLog)
			{
				log.error("Parser error: " + er);
			}
		}
	}

	/**
	 * @param exception SAXParseException
	 * @throws JDFException - if fatal error occurs
	 */
	@Override
	public void fatalError(final SAXParseException exception)
	{
		final String er = getErrorMsg(exception);
		final KElement kEl = root.appendElement(FATAL_ERROR);
		kEl.setAttribute("Message", er);
		parser.m_lastExcept = exception;
		if (wantLog)
		{
			log.fatal(er);
		}
		throw new JDFException("Fatal error in the Parser: " + er);
	}

	/**
	 *
	 * @param exception
	 * @return
	 */
	private String getErrorMsg(final SAXParseException exception)
	{
		String er = exception.getMessage();
		if (src != null)
		{
			final String sysID = StringUtil.getNonEmpty(src.getSystemId());
			er += " src=" + sysID;
		}
		return er;
	}

	/**
	 * @return
	 */
	public XMLDoc getXMLOutput()
	{
		return root.getOwnerDocument_KElement();
	}

	/**
	 * remove duplicate warnings from the list and set schemaloction
	 *
	 * @param schemaLocation
	 */
	public void cleanXML(final String schemaLocation)
	{
		final VElement v = root.getChildElementVector(null, null, null, true, 0, false);
		final int vSize = v.size();
		v.unifyElement();
		final int vSizeAfter = v.size();
		if (vSizeAfter < vSize)
		{
			root.removeChildren(null, null, null);
			for (final KElement e : v)
			{
				root.appendChild(e);
			}
		}
		if (schemaLocation == null)
		{
			root.setAttribute(VALIDATION_RESULT, "NotPerformed");
		}
		else
		{
			root.setAttribute("SchemaLocation", schemaLocation);
			if (root.hasChildElement(FATAL_ERROR, null))
				root.setAttribute(VALIDATION_RESULT, FATAL_ERROR);
			else if (root.hasChildElement(ERROR, null))
				root.setAttribute(VALIDATION_RESULT, ERROR);
			else if (root.hasChildElement(WARNING, null))
				root.setAttribute(VALIDATION_RESULT, WARNING);
			else
				root.setAttribute(VALIDATION_RESULT, "Valid");
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// //////////

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "XMLErrorHandler: " + root + " input= " + src;
	}

	/**
	 * Setter for parser attribute.
	 *
	 * @param parser the parser to set
	 */
	public void setParser(final XMLParser parser)
	{
		this.parser = parser;
	}

	/**
	 *
	 * @param inSource
	 */
	public void setInputSource(final InputSource inSource)
	{
		src = inSource;
	}

	public void reset()
	{
		root = new XMLDoc("SchemaValidationOutput", null).getRoot();
		src = null;
	}
}
