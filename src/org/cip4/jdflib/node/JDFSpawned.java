/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpawned.java
 *
 * Last changes
 *
 * 2002-07-02 JG - added NewSpawnID handling
 * 2002-07-02 JG - Removed NewSpawnID and URL handling -> now done by JDFAutoSpawned
 *
 */
package org.cip4.jdflib.node;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoSpawned;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;

/**
 * 
 * Description: This class represents a vector of JDFAttributeMap
 * 
 * @author Torsten Kaehlert
 * 
 * @version 1.0 2002-01-24
 * 
 */
public class JDFSpawned extends JDFAutoSpawned
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFSpawned
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFSpawned(CoreDocumentImpl myOwnerDocument, String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFSpawned
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFSpawned(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFSpawned
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFSpawned(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	// *********************************************

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
	 * @param vParts vector of attribute maps for the parts
	 */
	@Override
	public void setPartMapVector(VJDFAttributeMap vParts)
	{
		super.setPartMapVector(vParts);
	}

	/**
	 * set all parts to those define in vParts
	 * 
	 * @param mPart attribute map for the part to set
	 */
	@Override
	public void setPartMap(JDFAttributeMap mPart)
	{
		super.setPartMap(mPart);
	}

	/**
	 * remove the part defined in mPart
	 * 
	 * @param mPart attribute map for the part to remove
	 */
	@Override
	public void removePartMap(JDFAttributeMap mPart)
	{
		super.removePartMap(mPart);
	}

	/**
	 * check whether the part defined in mPart is included
	 * 
	 * @param mPart attribute map for the part to remove
	 * @return boolean - returns true if the part exists
	 */
	@Override
	public boolean hasPartMap(JDFAttributeMap mPart)
	{
		return super.hasPartMap(mPart);
	}

	/**
	 * Typesafe attribute adder of rRefsROCopied
	 * 
	 * @param rRefs the reference list
	 */
	public void appendrRefsROCopied(String rRefs)
	{
		appendAttribute(JDFConstants.RREFSROCOPIED, rRefs, JDFConstants.EMPTYSTRING, JDFConstants.BLANK, true);
	}

	/**
	 * Typesafe attribute adder of rRefsRWCopied
	 * 
	 * @param KString & rRefs the reference list
	 */
	public void appendrRefsRWCopied(String rRefs)
	{
		appendAttribute(JDFConstants.RREFSRWCOPIED, rRefs, JDFConstants.EMPTYSTRING, JDFConstants.BLANK, true);
	}

	/**
	 * Typesafe attribute adder of rRefsROCopied
	 * 
	 * @param rRefs the reference list
	 */
	public void appendrRefsROCopied(VString rRefs)
	{
		VString v0 = getrRefsROCopied();
		v0.appendUnique(rRefs);
		setrRefsROCopied(v0);
	}

	/**
	 * Typesafe attribute adder of rRefsRWCopied
	 * 
	 * @param KString & rRefs the reference list
	 */
	public void appendrRefsRWCopied(VString rRefs)
	{
		VString v0 = getrRefsRWCopied();
		v0.appendUnique(rRefs);
		setrRefsRWCopied(v0);

	}

}
