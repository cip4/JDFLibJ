/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFRectangle.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.datatypes;

import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.util.HashUtil;

/**
 * This class represents a rectangle JDFRectangle) consisting of a lower left x value (llx),
 * a lower left y value (lly), an upper right x value (urx) and an upper right y value (ury)
 * all values are Double types.
 */
public class JDFRectangle extends JDFNumList
{
    //**************************************** Constructors ****************************************
    /**
     * constructs a rectangle with all 4 values set to 0.0 Double
     */
    public JDFRectangle()
    {
        super(MAX_RECTANGLE_DIMENSION);
    }

    /**
     * constructs a rectangle with all values set via a Vector of Double objects
     *
     * @param v the given Vector with MAX_RECTANGLE_DIMENSION objects of type Double
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public JDFRectangle(Vector v) throws DataFormatException
    {
        super(v);
    }

    /**
     * constructs a rectangle with all values set via a String
     *
     * @param s the given String, blank separated numbers
     *
     * @throws DataFormatException - if the String has not a valid format
     */
    public JDFRectangle(String s) throws DataFormatException
    {
        super(s);
    }

    /**
     * constructs a rectangle with all values set via a JDFRectangle
     *
     * @param rec the given rectangle
     */
    public JDFRectangle(JDFRectangle rec)
    {
        super(MAX_RECTANGLE_DIMENSION);
        setLlx(rec.getLlx());
        setLly(rec.getLly());
        setUrx(rec.getUrx());
        setUry(rec.getUry());
    }

    /**
     * constructs a rectangle with all values set via a JDFNumberList
     *
     * @param nl the given number list
     *
     * @throws DataFormatException - if the JDFNumberList has not a valid format
     */
    public JDFRectangle(JDFNumberList nl) throws DataFormatException
    {
        super(MAX_RECTANGLE_DIMENSION);
        if (nl.size()!=MAX_RECTANGLE_DIMENSION)
            throw new DataFormatException("JDFRectangle: can't construct JDFRectangle from this JDFNuberList value");
        getnumList().set(0, nl.getnumList().get(0));
        getnumList().set(1, nl.getnumList().get(1));
        getnumList().set(2, nl.getnumList().get(2));
        getnumList().set(3, nl.getnumList().get(3));
    }

    /**
     * constructs a new JDFRectangle with the given double values
     *
     * @param llx lower left x coordinate
     * @param lly lower left y coordinate
     * @param urx lower left x coordinate
     * @param ury lower left y coordinate
     */
    public JDFRectangle(double llx, double lly, double urx, double ury)
    {
        super(MAX_RECTANGLE_DIMENSION);
        setLlx(llx);
        setLly(lly);
        setUrx(urx);
        setUry(ury);
    }

    //**************************************** Methods *********************************************
    /**
     * isValid - true if the size of the vector is 4 and all instances are Double types
     *
     * @throws DataFormatException - if the Vector has not a valid format
     */
    public void isValid() throws DataFormatException
    {
        if (getnumList().size() != MAX_RECTANGLE_DIMENSION)
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
    }

    /**
     * equals - returns true if both JDFRectangles are equal, otherwise false
     *
     * @param other Object to compare
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
            
        JDFRectangle r = (JDFRectangle) other;
        
        return  (Math.abs(this.getLlx() - r.getLlx())  <= EPSILON) &&
                (Math.abs(this.getLly() - r.getLly())  <= EPSILON) &&
                (Math.abs(this.getUrx() - r.getUrx())  <= EPSILON) &&
                (Math.abs(this.getUry() - r.getUry())  <= EPSILON) ;
    }
    
    /**
     * hashCode complements equals() to fulfill the equals/hashCode contract
     * @return int
     */
    public int hashCode()
    {
        return HashUtil.hashCode(super.hashCode(), this.toString());
    }
    
    /**
    * isGreaterOrEqual - equality operator >=
    * 
    * @param r the JDFRectangle object to compare to
    * @return boolean - true if <code>this</this> >= r  
    */
    public boolean isGreaterOrEqual(JDFRectangle r)
    {
        return (getLlx()<=r.getLlx())&&(getLly()<=r.getLly())&&(getUrx()>=r.getUrx())&&(getUry()>=r.getUry());
    }

    /**
    * isLessOrEqual - equality operator <=
    * 
    * @param r the JDFRectangle object to compare to
    * @return boolean - true if <code>this</this> <= r  
    */
    public boolean isLessOrEqual(JDFRectangle r)
    {
        return (getLlx()>=r.getLlx())&&(getLly()>=r.getLly())&&(getUrx()<=r.getUrx())&&(getUry()<=r.getUry());
    }
    
    /**
    * isGreater - equality operator >
    * 
    * @param r the JDFRectangle object to compare to
    * @return boolean - true if <code>this</this> > r  
    */
    public boolean isGreater(JDFRectangle r)
    {
        return (!equals(r)&&(getLlx()<=r.getLlx())&&(getLly()<=r.getLly())&&(getUrx()>=r.getUrx())&&(getUry()>=r.getUry()));   
    }

    /**
    * isLess - equality operator <
    * 
    * @param r the JDFRectangle object to compare to
    * @return boolean - true if <code>this</this> < r  
    */
    public boolean isLess(JDFRectangle r)
    {
        return (!equals(r)&&(getLlx()>=r.getLlx())&&(getLly()>=r.getLly())&&(getUrx()<=r.getUrx())&&(getUry()<=r.getUry()));   
    }


    /**
     * getLlx - returns the lower left x coordinate
     *
     * @return double - the lower left x coordinate
     */
    public double getLlx()
    {
        return ((Double)getnumList().elementAt(0)).doubleValue();
    }

    /**
     * setLlx - sets the lower left x coordinate
     *
     * @param x the lower left x coordinate
     */
    public void setLlx(double x)
    {
        getnumList().set(0, new Double(x));
    }

    /**
     * getLly - returns the lower left y coordinate
     *
     * @return double - the lower left y coordinate
     */
    public double getLly()
    {
        return ((Double)getnumList().elementAt(1)).doubleValue();
    }

    /**
     * setLly - sets the lower left y coordinate
     *
     * @param y the lower left y coordinate
     */
    public void setLly(double y)
    {
        getnumList().set(1, new Double(y));
    }

    /**
     * getUrx - returns the upper right x coordinate
     *
     * @return double - the upper right x coordinate
     */
    public double getUrx()
    {
        return ((Double)getnumList().elementAt(2)).doubleValue();
    }

    /**
     * setUrx - sets the upper right x coordinate
     *
     * @param x the upper right x coordinate
     */
    public void setUrx(double x)
    {
        getnumList().set(2, new Double(x));
    }

    /**
     * getUry - returns the upper right y coordinate
     *
     * @return double - the upper right y coordinate
     */
    public double getUry()
    {
        return ((Double)getnumList().elementAt(3)).doubleValue();
    }

    /**
     * setUry - sets the upper right y coordinate
     *
     * @param y the upper right y coordinate
     */
    public void setUry(double y)
    {
        getnumList().set(3, new Double(y));
    }

    /**
     * getWidth - returns the width of the rectangle, the difference between upper right x value and
     * lower left x value as an absolute value
     *
     * @return double - the width of the rectangle
     */
    public double getWidth()
    {
        return Math.abs(((Double)getnumList().elementAt(2)).doubleValue() -
                        ((Double)getnumList().elementAt(0)).doubleValue());
    }

    /**
     * getHeight - returns the height of the rectangle, the difference between upper right y value
     * and lower left y value as an absolute value
     *
     * @return double - the height of the rectangle
     */
    public double getHeight()
    {
        return Math.abs(((Double)getnumList().elementAt(3)).doubleValue() -
                        ((Double)getnumList().elementAt(1)).doubleValue());
    }
}