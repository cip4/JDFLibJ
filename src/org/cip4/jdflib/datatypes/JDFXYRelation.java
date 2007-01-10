/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFXYRelation.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import org.cip4.jdflib.core.JDFConstants;

/**
 * all constants of the JDF library
 */
//TODO need implementation
public class JDFXYRelation
{
	String m_Relation = JDFConstants.EMPTYSTRING;
	
	public JDFXYRelation(String s)
	{
	    m_Relation = s;
	}
    
    public String toString()
    {
        return m_Relation;
    }
}