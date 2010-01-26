/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * VJDFNode.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.node;

import java.util.Vector;

/**
 * 
 * Description: This class represents a vector of JDFNode
 * 
 * @author Torsten Kaehlert
 * 
 * @version 1.0 2002-01-24
 * @deprecated use Vector instead
 * 
 */
@Deprecated
@SuppressWarnings("unchecked")
public class VJDFNode
{
	// **************************************** Attributes
	// ******************************************
	private Vector m_vec = new Vector();

	/**
	 * constructor
	 */
	public VJDFNode()
	{
		// default super constructor
	}

	/**
	 * constructor
	 * 
	 * @param v
	 */
	public VJDFNode(Vector v)
	{
		m_vec = v;
	}

	/**
	 * Method size.
	 * 
	 * @return int
	 */
	public int size()
	{
		return m_vec.size();
	}

	/**
	 * Method elementAt: get JDFNode at <code>index</code>
	 * 
	 * @param index the index
	 * @return JDFNode: the JDFNode
	 */
	public JDFNode elementAt(int index)
	{
		return (JDFNode) m_vec.elementAt(index);
	}

	/**
	 * add a JDFNode
	 * 
	 * @param node the node to add
	 */
	public void addElement(JDFNode node)
	{
		m_vec.addElement(node);
	}

}
