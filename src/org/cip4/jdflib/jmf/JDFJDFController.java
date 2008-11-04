/**
==========================================================================
class JDFJDFController extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
 **/

package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoJDFController;

//----------------------------------
public class JDFJDFController extends JDFAutoJDFController
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFJDFController
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFJDFController(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFJDFController
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFJDFController(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFJDFController
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFJDFController(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoJDFController#toString()
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFJDFController[  --> " + super.toString() + " ]";
	}
}
