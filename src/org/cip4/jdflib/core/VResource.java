/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * vResource.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.core;

import java.util.Vector;

import org.cip4.jdflib.resource.JDFResource;

/**
 * @deprecated use VElement instead
 */
public class VResource extends VElement
{
    private static final long serialVersionUID = 1L;

    //**************************************** Constructors ****************************************
    /**
     * constructor
     */
    public VResource()
    {
        super();
    }

    /**
     * constructor
     *
     * @param m
     */
    public VResource(Vector m)
    {
        super();
        for (int i=0; i<m.size(); i++)
        {
            if (m.elementAt(i) instanceof JDFResource)
            {
                this.addElement(m.elementAt(i));
            }
        }
    }

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "vResource[ --> " + super.toString() + " ]";
    }

}
