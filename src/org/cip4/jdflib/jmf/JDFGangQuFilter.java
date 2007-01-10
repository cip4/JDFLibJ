/**
 ==========================================================================
 class JDFGangQuFilter extends JDFResource
 ==========================================================================
 @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
 ALL RIGHTS RESERVED
 @Author: sabjon@topmail.de   using a code generator
 Warning! very preliminary test version. Interface subject to change without prior notice!
 Revision history:    ...
 **/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoGangQuFilter;



//----------------------------------
public class JDFGangQuFilter extends JDFAutoGangQuFilter
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFGangQuFilter
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFGangQuFilter(
			CoreDocumentImpl myOwnerDocument,
			String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFGangQuFilter
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFGangQuFilter(
			CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI,
			String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFGangQuFilter
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFGangQuFilter(
			CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI,
			String qualifiedName,
			String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}
	
	/**
	 * toString()
	 * @see org.cip4.jdflib.auto.JDFAutoGangQuFilter#toString()
	 * @return String
	 */
	public String toString()
	{
		return "JDFGangQuFilter[  --> " + super.toString() + " ]";
	}
}



