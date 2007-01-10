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
 * Description:      This class represents a vector of JDFNode
 *
 * @author          Torsten Kaehlert
 *
 * @version 1.0     2002-01-24
 * @deprecated use Vector instead
 *
 */
public class VJDFNode
{
    //**************************************** Attributes ******************************************
    private Vector m_vec = new Vector();

    /**
     * constructor
     */
    public VJDFNode()
    {
        //default super constructor
    }
    public VJDFNode(Vector v)
    {
        m_vec = v;
    }

    /**
     * Method size.
     * @return int
     */
    public int size()
    {
        return m_vec.size();
    }

    /**
     * Method elementAt.
     * @param index
     * @return JDFNode
     */
    public JDFNode elementAt(int index)
    {
        return (JDFNode) m_vec.elementAt(index);
    }

    /**
     * Method addElement.
     * @param node
     */
    public void addElement(JDFNode node)
    {
        m_vec.addElement(node);
    }
	
}
