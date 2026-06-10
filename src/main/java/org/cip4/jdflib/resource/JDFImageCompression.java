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

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoImageCompression;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.util.JavaEnumUtil;
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
	public JDFImageCompression(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
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
	public JDFImageCompression(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
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
	public JDFImageCompression(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
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

	public enum EnumImageFilter
	{
		Unknown, CCITTFaxEncode, DCTEncode, FlateEncode;

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumImageFilter getEnum(final String enumName)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EnumImageFilter.class, enumName, null);
		}
	}

	/**
	 * set attribute ImageFilter
	 *
	 * @param value enum value to set the attribute to
	 */
	public void setEnumImageFilter(final EnumImageFilter value)
	{
		setAttribute(AttributeName.IMAGEFILTER, JavaEnumUtil.getName(value), null);
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
