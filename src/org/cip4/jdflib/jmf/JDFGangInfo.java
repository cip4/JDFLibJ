/**
 ==========================================================================
 class JDFGangInfo extends JDFResource
 ==========================================================================
 @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
 ALL RIGHTS RESERVED
 @Author: sabjon@topmail.de   using a code generator
 Warning! very preliminary test version. Interface subject to change without prior notice!
 Revision history:    ...
 **/

package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoGangInfo;

//----------------------------------
/**
 * GangInfo is a placeholder for future gang related information <br>
 * that only returns the gang names in JDF 1.3
 */
public class JDFGangInfo extends JDFAutoGangInfo
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFGangInfo
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFGangInfo(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFGangInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFGangInfo(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFGangInfo
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFGangInfo(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoGangInfo#toString()
	 * @return String
	 */
	public String toString()
	{
		return "JDFGangInfo[  --> " + super.toString() + " ]";
	}
}
