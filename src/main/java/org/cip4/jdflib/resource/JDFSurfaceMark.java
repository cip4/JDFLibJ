/**
 *
 * Copyright (c) 2001-2025 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFCollectingParams.java
 *
 */
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoCutMark.EMarkType;
import org.cip4.jdflib.auto.JDFAutoSurfaceMark;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.resource.process.postpress.JDFCutMark;
import org.w3c.dom.DOMException;

public class JDFSurfaceMark extends JDFAutoSurfaceMark
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFCollectingParams
	 *
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSurfaceMark(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFCollectingParams
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFSurfaceMark(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFCollectingParams
	 *
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFSurfaceMark(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
			throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	public JDFCutMark appendCutMark(EMarkType mt, JDFXYPair pos)
	{
		JDFCutMark cm = appendCutMark();
		cm.setMarkType(mt);
		cm.setPosition(pos);
		return cm;

	}

}
