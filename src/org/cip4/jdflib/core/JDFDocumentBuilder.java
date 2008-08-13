/*
 * JDFDocumentBuilder.java
 *
 * @author schielke
 *
 * Created on Jun 23, 2005
 *
 */

package org.cip4.jdflib.core;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * Class JDFDocumentBuilder
 * 
 */

public class JDFDocumentBuilder extends DocumentBuilder
{
	private JDFParser m_parser;

	/**
	 * Constructor JDFDocumentBuilder
	 * 
	 * 
	 */

	/**
	 * constructor
	 */
	public JDFDocumentBuilder()
	{
		init(new JDFParser());
	}

	/**
	 * Method init
	 * 
	 * @param parser
	 */

	private void init(JDFParser parser)
	{
		m_parser = parser;
		m_parser.m_DocumentClass = "org.cip4.jdflib.core.DocumentJDFImpl";
	}

	/**
	 * Method getParser
	 * 
	 * @return
	 */
	public JDFParser getParser()
	{
		return (m_parser);
	}

	/**
	 * Method isNamespaceAware
	 * 
	 * @see javax.xml.parsers.DocumentBuilder#isNamespaceAware()
	 * 
	 * @return
	 */
	public boolean isNamespaceAware()
	{
		return (true);
	}

	/**
	 * Method isValidating
	 * 
	 * @see javax.xml.parsers.DocumentBuilder#isValidating()
	 * 
	 * @return
	 */
	public boolean isValidating()
	{
		boolean isValidating = false;

		try
		{
			isValidating = m_parser.getFeature("http://xml.org/sax/features/validation");
		}

		catch (SAXNotRecognizedException e)
		{
			e.printStackTrace();
		}

		catch (SAXNotSupportedException e)
		{
			e.printStackTrace();
		}

		return (isValidating);
	}

	/**
	 * Method getDOMImplementation
	 * 
	 * @see javax.xml.parsers.DocumentBuilder#getDOMImplementation()
	 * 
	 * @return
	 */
	public DOMImplementation getDOMImplementation()
	{
		throw new JDFException("JDFDocumentBuilder.getDOMImplementation is not implemented");
	}

	/**
	 * Method newDocument
	 * 
	 * @see javax.xml.parsers.DocumentBuilder#newDocument()
	 * 
	 * @return
	 */
	public Document newDocument()
	{
		return (new DocumentJDFImpl());
	}

	/**
	 * Method setEntityResolver
	 * 
	 * @see javax.xml.parsers.DocumentBuilder#setEntityResolver(org.xml.sax.EntityResolver)
	 * 
	 * @param er
	 */
	public void setEntityResolver(EntityResolver er)
	{
		m_parser.setEntityResolver(er);
	}

	/**
	 * Method setErrorHandler
	 * 
	 * @see javax.xml.parsers.DocumentBuilder#setErrorHandler(org.xml.sax.ErrorHandler)
	 * 
	 * @param eh
	 */
	public void setErrorHandler(ErrorHandler eh)
	{
		m_parser.setErrorHandler(eh);
	}

	/**
	 * Method parse
	 * 
	 * @see javax.xml.parsers.DocumentBuilder#parse(org.xml.sax.InputSource)
	 * 
	 * @param is
	 * 
	 * @return
	 */
	public Document parse(InputSource is)
	{
		m_parser.parse(is);

		Document doc = m_parser.getDocument();

		m_parser.reset();

		return (doc);
	}
}
