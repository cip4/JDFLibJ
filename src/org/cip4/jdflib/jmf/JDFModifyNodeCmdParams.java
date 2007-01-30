/**
 ==========================================================================
 class JDFModifyNodeCmdParams extends JDFResource
 ==========================================================================
 @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
 ALL RIGHTS RESERVED
 @Author: sabjon@topmail.de   using a code generator
 Warning! very preliminary test version. Interface subject to change without prior notice!
 Revision history:    ...
 **/





package org.cip4.jdflib.jmf;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoModifyNodeCmdParams;



//----------------------------------
public class JDFModifyNodeCmdParams extends JDFAutoModifyNodeCmdParams
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFModifyNodeCmdParams
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFModifyNodeCmdParams(
			CoreDocumentImpl myOwnerDocument,
			String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFModifyNodeCmdParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFModifyNodeCmdParams(
			CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI,
			String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFModifyNodeCmdParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFModifyNodeCmdParams(
			CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI,
			String qualifiedName,
			String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}
	
	/* (non-Javadoc)
	 * @see org.cip4.jdflib.auto.JDFAutoModifyNodeCmdParams#toString()
	 */
	public String toString()
	{
		return "JDFModifyNodeCmdParams[  --> " + super.toString() + " ]";
	}
}



