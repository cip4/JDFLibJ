/**
 * ========================================================================== 
 * class JDFRenderingParams extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 */

package org.cip4.jdflib.resource.process.prepress;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoObjectResolution.EnumSourceObjects;
import org.cip4.jdflib.auto.JDFAutoRenderingParams;
import org.cip4.jdflib.resource.process.JDFObjectResolution;
import org.w3c.dom.DOMException;

/**
 * 
 * @author rainer prosi
 *
 */
public class JDFRenderingParams extends JDFAutoRenderingParams
{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFRenderingParams(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFRenderingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 *
	 * Constructor for JDFRenderingParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFRenderingParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	@Override
	public String toString()
	{
		return "JDFRenderingParams[  --> " + super.toString() + " ]";
	}

	public JDFObjectResolution getObjectResolution(EnumSourceObjects sourceObject, String objectTag)
	{
		Vector<JDFObjectResolution> v = getChildrenByClass(JDFObjectResolution.class, false, -1);
		if (v != null)
		{
			for (JDFObjectResolution or : v)
			{
				if (or.matches(sourceObject, objectTag))
				{
					return or;
				}
			}
		}
		return null;
	}
} // class JDFIDPLayout
// ==========================================================================
