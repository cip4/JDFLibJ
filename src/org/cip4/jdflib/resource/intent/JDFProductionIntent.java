/**
 * ========================================================================== 
 * class JDFProductionIntent extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 * Revision history:   ...
 */

package org.cip4.jdflib.resource.intent;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoProductionIntent;
import org.cip4.jdflib.core.JDFElement;
import org.w3c.dom.DOMException;

public class JDFProductionIntent extends JDFAutoProductionIntent
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFProductionIntent
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFProductionIntent(CoreDocumentImpl myOwnerDocument,
			String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFProductionIntent
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFProductionIntent(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFProductionIntent
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFProductionIntent(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName, String myLocalName)
			throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * unknown elements
	 * 
	 * @param nMax
	 *            maximum number of elements to return
	 * @return
	 */
	public Vector getUnknownElements(boolean bIgnorePrivate, int nMax)
	{
		if (bIgnorePrivate)
			bIgnorePrivate = false; // dummy to fool compiler
		return getUnknownPoolElements(JDFElement.EnumPoolType.ProductionIntent,
				nMax);
	}

	public String toString()
	{
		return "JDFProductionIntent[  --> " + super.toString() + " ]";
	}
} // class JDFIDPLayout
// ==========================================================================
