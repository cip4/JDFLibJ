/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFSpan.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.span;

import java.util.zip.DataFormatException;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFNameRange;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFRangeList;
import org.w3c.dom.DOMException;

/**
 * @deprecated
 * defines the data type dependent parts of a ranged Span resource
 */
public abstract class JDFSpan extends JDFSpanBase
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFSpan
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
     public JDFSpan(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
        throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }


    /**
     * Constructor for JDFSpan
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFSpan(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
         throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFSpan
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFSpan(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
        throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Interfaces ******************************************
    interface IntegerSpan
    {
        void setRange(JDFIntegerRangeList o);
    }

    interface NumberSpan
    {
        void setRange(JDFNumberRangeList o);
    }

    interface NameSpan
    {
        void setRange(JDFNameRangeList o);
    }


    

    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFSpan[ --> " + super.toString() + " ]";
    }

    /**
     * SetPreferred
     *
     * @param Object o
     */
    public void setPreferred(Object o)
    {
        setAttribute (AttributeName.PREFERRED, o.toString(), JDFConstants.EMPTYSTRING);
    }

   
    /**
     * SetPreferred
     *
     * @param double o
     */
    public void setPreferred(double o)
    {
        setAttribute (AttributeName.PREFERRED, JDFConstants.EMPTYSTRING + o, JDFConstants.EMPTYSTRING);
    }

    /**
     * SetPreferred
     *
     * @param boolean o
     */
    public void setPreferred(boolean o)
    {
        setAttribute (AttributeName.PREFERRED, JDFConstants.EMPTYSTRING + o, JDFConstants.EMPTYSTRING);
    }

    /**
     * SetPreferred
     *
     * @param String o
     */
    public void setPreferred(String o)
    {
        setAttribute(AttributeName.PREFERRED, o, JDFConstants.EMPTYSTRING);
    }

    /**
     * SetActual
     *
     * @param Object o
     */
    public void setActual(Object o)
    {
        setAttribute (AttributeName.ACTUAL, o.toString(), JDFConstants.EMPTYSTRING);
    }

    /**
     * SetActual
     *
     * @param int o
     */
    public void setActual(int o)
    {
        setAttribute (AttributeName.ACTUAL, JDFConstants.EMPTYSTRING + o, JDFConstants.EMPTYSTRING);
    }

    /**
     * SetActual
     *
     * @param double o
     */
    public void setActual(double o)
    {
        setAttribute (AttributeName.ACTUAL, JDFConstants.EMPTYSTRING + o, JDFConstants.EMPTYSTRING);
    }

    /**
     * SetActual
     *
     * @param boolean o
     */
    public void setActual(boolean o)
    {
        setAttribute (AttributeName.ACTUAL, JDFConstants.EMPTYSTRING + o, JDFConstants.EMPTYSTRING);
    }

    /**
     * SetActual
     *
     * @param String o
     */
    public void setActual(String o)
    {
        setAttribute (AttributeName.ACTUAL, o, JDFConstants.EMPTYSTRING);
    }

    /**
     * SetRange
     *
     * @param String rs
     */
    public void setRange(String rs)
    {
        setAttribute (AttributeName.RANGE, rs, JDFConstants.EMPTYSTRING);
    }

    /**
     * SetRange
     *
     * @param JDFRangeList rl
     * @deprecated use specialized routines
     */
    public void setRange(JDFRangeList rl)
    {
        setAttribute ("Range", rl.toString(), JDFConstants.EMPTYSTRING);
    }

    /**
     * GetPreferred
     *
     * @return String
     */
    public String getPreferred()
    {
        return getAttribute (AttributeName.PREFERRED, JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING);
    }

    /**
     * GetActual
     *
     * @return String
     */
    public String getActual()
    {
        return getAttribute (AttributeName.ACTUAL, JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING);
    }

    /**
     * GetRange
     *
     * @return String
     */
    public String getRange()
    {
        return getAttribute (AttributeName.RANGE, JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING);
    }

    /**
     * AddRange
     *
     * @param Object xMin
     * @param Object xMax
     * default is both values are equal
     */
    public void addRange(String xMin, String xMax)
    {
        try
        {
            JDFNameRangeList rl = new JDFNameRangeList (getRange());
            rl.append (new JDFNameRange(xMin,xMax));
            setAttribute (AttributeName.RANGE, rl.toString(), JDFConstants.EMPTYSTRING);
        }
        catch (DataFormatException e)
        {
            new JDFException("JDFSpan.addRange: DataFormatExceptione while creating JDFNameRange");
        }
    }
}
