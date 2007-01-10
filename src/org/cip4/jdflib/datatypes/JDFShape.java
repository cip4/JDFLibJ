/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFShape.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.util.HashUtil;

/**
 * This class is a representation of a JDFShape. It is a blank separated list of double values
 * consisting of a height, a width and a lenght value.
 */
public class JDFShape extends JDFNumList
{
    //**************************************** Constructors ****************************************

    /**
     * constructor - constructs a shape with all values set to 0.0 Double
     */
    public JDFShape()
    {
        super(MAX_SHAPE_DIMENSION);
    }

    /**
     * constructor - constructs a shape with all values set via a Vector of Double objects
     *
     * @param Vector v - the given vector
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public JDFShape(Vector v) throws DataFormatException
    {
        super(v);
    }

    /**
     * constructor - constructs a shape with all values set via a String
     *
     * @param String s - the given String
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFShape(String s) throws DataFormatException
    {
        super(s);
    }

    /**
     * constructor - constructs a shape with all values set via a JDFShape
     *
     * @param JDFShape shape - the given shape
     *
     * @throws DataFormatException - if the JDFShape has not a valid format
     */
    public JDFShape(JDFShape shape)
    {
        super(MAX_SHAPE_DIMENSION);
        setHeight(shape.getHeight());
        setWidth(shape.getWidth());
        setLength(shape.getLength());
    }

    /**
     * constructor - constructs a shape with all values set via a JDFNumberList
     *
     * @param JDFNumberList nl - the given number list
     *
     * @throws DataFormatException - if the JDFNumberList has not a valid format
     */
    public JDFShape(JDFNumberList nl) throws DataFormatException
    {
        super(MAX_SHAPE_DIMENSION);
        if (nl.size()!=MAX_SHAPE_DIMENSION)
            throw new DataFormatException("JDFShape: can't construct JDFShape from this JDFNuberList value");
        getnumList().set(0, nl.getnumList().get(0));
        getnumList().set(1, nl.getnumList().get(1));
        getnumList().set(2, nl.getnumList().get(2));
    }

    /**
     * constructor - constructs a new JDFShape with the given double values
     *
     * @param double height - the height
     * @param double width  - the width
     * @param double length - the length
     */
    public JDFShape(double height, double width, double length)
    {
        super(MAX_SHAPE_DIMENSION);
        setHeight(height);
        setWidth(width);
        setLength(length);
    }

    /**
     * constructor - constructs a new JDFShape with the given 2 double values
     * third is default = 0. 
     *
     * @param double height - the height
     * @param double width  - the width
     * @param double length - the length  = 0.0
     */
    public JDFShape(double height, double width)
    {
        super(MAX_SHAPE_DIMENSION);
        setHeight(height);
        setWidth(width);
        setLength(0.0);
    }
    
    //**************************************** Methods *********************************************
    /**
     * isValid - true if the size of the vector is 3 and all instances are Double types
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public void isValid() throws DataFormatException
    {
        if (getnumList().size() != MAX_SHAPE_DIMENSION 
           && getnumList().size() != MAX_SHAPE_DIMENSION - 1) // Shape with default length = 0.0
        {
            throw new DataFormatException("Data format exception!");
        }

        for (int i = 0; i < getnumList().size(); i++)
        {
            if (!(getnumList().elementAt(i) instanceof Double))
            {
                throw new DataFormatException("Data format exception!");
            }
        }
        if (getnumList().size()==2) 
            getnumList().addElement(new Double(0.0));
    }

    /**
     * equals - returns true if both JDFShapes are equal, otherwise false
     *
     * @return boolean - true if equal otherwise false
     */
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null)
        {
            return false;
        }
        if (!other.getClass().equals(getClass()))
        {
            return false;
        }
            
        JDFShape shape = (JDFShape) other;
        
        return  (Math.abs(this.getHeight() - shape.getHeight())  <= EPSILON) &&
                (Math.abs(this.getWidth()  - shape.getWidth())   <= EPSILON) &&
                (Math.abs(this.getLength() - shape.getLength())  <= EPSILON) ;
    }
    
    /**
     * hashCode complements equals() to fulfill the equals/hashCode contract
     */
    public int hashCode()
    {
        return HashUtil.hashCode(super.hashCode(), this.toString());
    }
    
    /**
    * isGreaterOrEqual - equality operator >=
    * 
    * @param JDFShape x - the JDFShape object to compare to
    * @return boolean - true if this >= x  
    */
    public boolean isGreaterOrEqual(JDFShape x)
    {
        return ((getHeight()>=x.getHeight())&&(getWidth()>=x.getWidth())&&(getLength()>=x.getLength()));   
    }

    /**
    * isLessOrEqual - equality operator <=
    * 
    * @param JDFShape x - the JDFShape object to compare to
    * @return boolean - true if this <= x  
    */
    public boolean isLessOrEqual(JDFShape x)
    {
        return ((getHeight()<=x.getHeight())&&(getWidth()<=x.getWidth())&&(getLength()<=x.getLength()));
    }
        
    /**
    * isGreater - equality operator >
    * 
    * @param JDFShape x - the JDFShape object to compare to
    * @return boolean - true if this > x  
    */
    public boolean isGreater(JDFShape x)
    {
        return (!equals(x)&&(getHeight()>=x.getHeight())&&(getWidth()>=x.getWidth())&&(getLength()>=x.getLength()));   
    }
    
    /**
    * isLess - equality operator <
    * 
    * @param JDFShape x - the JDFShape object to compare to
    * @return boolean - true if this < x  
    */
    public boolean isLess(JDFShape x)
    {
        return (!equals(x)&&(getHeight()<=x.getHeight())&&(getWidth()<=x.getWidth())&&(getLength()<=x.getLength()));
    }
       
       
    /**
     * getHeight - returns the height
     *
     * @return double - the height
     */
    public double getHeight()
    {
        return ((Double)getnumList().elementAt(0)).doubleValue();
    }
    
    /**
     * setHeight - sets the height
     *
     * @param double height - the height
     */
    public void setHeight(double height)
    {
        getnumList().set(0, new Double(height));
    }

    /**
     * getWidth - returns the width
     *
     * @return double - the width
     */
    public double getWidth()
    {
        return ((Double)getnumList().elementAt(1)).doubleValue();
    }

    /**
     * setWidth - sets the width
     *
     * @param double width - the width
     */
    public void setWidth(double width)
    {
        getnumList().set(1, new Double(width));
    }

    /**
     * getLength - returns the length
     *
     * @return double - the length
     */
    public double getLength()
    {
        return ((Double)getnumList().elementAt(2)).doubleValue();
    }

    /**
     * setLength - sets the length
     *
     * @param double length - the length
     */
    public void setLength(double length)
    {
        getnumList().set(2, new Double(length));
    }

}


