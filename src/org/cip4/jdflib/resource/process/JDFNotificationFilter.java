/**
 * ==========================================================================
 * class JDFNotificationFilter extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 **/
package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoNotificationFilter;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;

public class JDFNotificationFilter extends JDFAutoNotificationFilter
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFNotificationFilter
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 */
	public JDFNotificationFilter(CoreDocumentImpl myOwnerDocument,
			String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFNotificationFilter
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 */
	public JDFNotificationFilter(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFNotificationFilter
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 */
	public JDFNotificationFilter(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	@Override
	public String toString()
	{
		return "JDFNotificationFilter[  --> " + super.toString() + " ]";
	}

	/**
	 * get part map vector
	 * 
	 * @return VJDFAttributeMap: vector of attribute maps, one for each part
	 */
	@Override
	public VJDFAttributeMap getPartMapVector()
	{
		return super.getPartMapVector();
	}

	/**
	 * set all parts to those define in vParts
	 * 
	 * @param VJDFAttributeMap
	 *            vParts: vector of attribute maps for the parts
	 */
	@Override
	public void setPartMapVector(VJDFAttributeMap vParts)
	{
		super.setPartMapVector(vParts);
	}

	/**
	 * set all parts to those define in vParts
	 * 
	 * @param JDFAttributeMap
	 *            mPart: attribute map for the part to set
	 */
	@Override
	public void setPartMap(JDFAttributeMap mPart)
	{
		super.setPartMap(mPart);
	}

	/**
	 * remove the part defined in mPart
	 * 
	 * @param JDFAttributeMap
	 *            mPart: attribute map for the part to remove
	 */
	@Override
	public void removePartMap(JDFAttributeMap mPart)
	{
		super.removePartMap(mPart);
	}

	/**
	 * check whether the part defined in mPart is included
	 * 
	 * @param JDFAttributeMap
	 *            mPart: attribute map for the part to remove
	 * @return boolean - returns true if the part exists
	 */
	@Override
	public boolean hasPartMap(JDFAttributeMap mPart)
	{
		return super.hasPartMap(mPart);
	}

}
