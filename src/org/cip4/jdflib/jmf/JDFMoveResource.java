/**
 ==========================================================================
 class JDFMoveResource extends JDFResource
 ==========================================================================
 @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
 ALL RIGHTS RESERVED
 @Author: sabjon@topmail.de   using a code generator
 Warning! very preliminary test version. Interface subject to change without prior notice!
 Revision history:    ...
 **/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoMoveResource;



//----------------------------------
public class JDFMoveResource extends JDFAutoMoveResource
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for JDFMoveResource
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFMoveResource(
			CoreDocumentImpl myOwnerDocument,
			String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}
	
	
	/**
	 * Constructor for JDFMoveResource
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFMoveResource(
			CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI,
			String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}
	
	/**
	 * Constructor for JDFMoveResource
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFMoveResource(
			CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI,
			String qualifiedName,
			String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}
	
	public String toString()
	{
		return "JDFMoveResource[  --> " + super.toString() + " ]";
	}
}



