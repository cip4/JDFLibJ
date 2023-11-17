/**
 *==========================================================================
 * class JDFImageCompression==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   
 * using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 * Revision history:   ...
 */

package org.cip4.jdflib.resource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoImageCompression;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.w3c.dom.DOMException;

public class JDFImageCompression extends JDFAutoImageCompression
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFImageCompression
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFImageCompression(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFImageCompression
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFImageCompression(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFImageCompression
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFImageCompression(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFImageCompression[  --> " + super.toString() + " ]";
	}

	public static class EnumImageFilter extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumImageFilter(String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumImageFilter getEnum(String enumName)
		{
			return (EnumImageFilter) getEnum(EnumImageFilter.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumImageFilter getEnum(int enumValue)
		{
			return (EnumImageFilter) getEnum(EnumImageFilter.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumImageFilter.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumImageFilter.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumImageFilter.class);
		}

		/**
		 * @return
		 */

		// enums accordng to JDF spec 3.1.2, Table 3-3 Status
		public static final EnumImageFilter Unknown = new EnumImageFilter(JDFConstants.IMAGEFILTER_UNKNOWN);
		public static final EnumImageFilter CCITTFaxEncode = new EnumImageFilter(JDFConstants.IMAGEFILTER_CCITTFAXENCODE);
		public static final EnumImageFilter DCTEncode = new EnumImageFilter(JDFConstants.IMAGEFILTER_DCTENCODE);
		public static final EnumImageFilter FlateEncode = new EnumImageFilter(JDFConstants.IMAGEFILTER_FLATENCODE);
	}

	/**
	 * set attribute ImageFilter
	 * 
	 * @param value enum value to set the attribute to
	 */
	public void setEnumImageFilter(EnumImageFilter value)
	{
		setAttribute(AttributeName.IMAGEFILTER, value, null);
	}

	// /////////////////////////////////////////////////////////////////////
	/**
	 * get enum attribute ImageFilter
	 * 
	 * @return the enum value
	 */
	public EnumImageFilter getEnumImageFilter()
	{
		return EnumImageFilter.getEnum(getAttribute(AttributeName.IMAGEFILTER, null, null));
	}

} // class JDFIDPLayout
	// ==========================================================================
