/**
==========================================================================
class JDFResubmissionParams extends JDFResource
==========================================================================
@COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
ALL RIGHTS RESERVED
@Author: sabjon@topmail.de   using a code generator
Warning! very preliminary test version. Interface subject to change without prior notice!
Revision history:    ...
 **/

package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoResubmissionParams;

public class JDFResubmissionParams extends JDFAutoResubmissionParams
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFResubmissionParams
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFResubmissionParams(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFResubmissionParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFResubmissionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFResubmissionParams
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFResubmissionParams(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
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
		return "JDFResubmissionParams[  --> " + super.toString() + " ]";
	}
}
