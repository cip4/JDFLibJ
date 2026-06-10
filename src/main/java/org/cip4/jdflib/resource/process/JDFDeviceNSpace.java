/**
 * ==========================================================================
 * class JDFDeviceNSpace extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de    using a code generator
 * Warning! very preliminary test version.
 * Interface subject to change without prior notice!
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDeviceNSpace;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.w3c.dom.DOMException;

/**
 * @author rainer prosi
 * @date Oct 18, 2011
 */
public class JDFDeviceNSpace extends JDFAutoDeviceNSpace
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFDeviceNSpace
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFDeviceNSpace(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDeviceNSpace
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFDeviceNSpace(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDeviceNSpace
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFDeviceNSpace(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoDeviceNSpace#toString()
	 */
	@Override
	public String toString()
	{
		return "JDFDeviceNSpace[  --> " + super.toString() + " ]";
	}

	/**
	 * Get a list of all separation names in the SeparationSpec elements
	 *
	 * @return the vector of separation names
	 */
	public VString getSeparations()
	{
		final VString vName = new VString();
		final VElement v = getChildElementVector(ElementName.SEPARATIONSPEC, null, null, false, 0, false);
		final int nSep = v.size();
		for (int i = 0; i < nSep; i++)
		{
			final JDFSeparationSpec sep = (JDFSeparationSpec) v.elementAt(i);
			final String sepName = sep.getName();
			vName.appendUnique(sepName);
		}
		return vName;
	}

} // class JDFDeviceNSpace
	// ==========================================================================
