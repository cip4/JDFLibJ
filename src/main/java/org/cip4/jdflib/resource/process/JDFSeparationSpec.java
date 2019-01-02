/**
 * ========================================================================== class JDFSeparationSpec ==========================================================================
 *
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de using a code generator Warning! very preliminary test version. Interface subject to change without prior notice!
 */

package org.cip4.jdflib.resource.process;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoSeparationSpec;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.w3c.dom.DOMException;

public class JDFSeparationSpec extends JDFAutoSeparationSpec
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSeparationSpec
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSeparationSpec(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSeparationSpec
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSeparationSpec(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSeparationSpec
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSeparationSpec(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	@Override
	public String toString()
	{
		return "JDFSeparationSpec[  --> " + super.toString() + " ]";
	}

	@Override
	protected AttributeInfo getTheAttributeInfo()
	{
		AttributeInfo ai = AttributeInfo.fixedMap.get("JDFSeparationSpec");
		if (ai != null)
			return ai;
		ai = super.getTheAttributeInfo();
		AttributeInfo.fixedMap.put("JDFSeparationSpec", ai);
		return ai;
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		ElementInfo ai = ElementInfo.getFixedmap().get(ElementName.SEPARATIONSPEC);
		if (ai != null)
			return ai;
		ai = super.getTheElementInfo();
		ElementInfo.getFixedmap().put(ElementName.SEPARATIONSPEC, ai);
		return ai;
	}

}