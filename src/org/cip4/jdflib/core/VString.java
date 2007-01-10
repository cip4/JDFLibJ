/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * vString.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.RootPaneContainer;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 */
public class VString extends Vector
{
    private static final long serialVersionUID = 1L;
    final public static VString emptyVector = new VString();
    
    //**************************************** Constructors ****************************************
    /**
     * constructor
     */
    public VString()
    {
        super();
    }

    /**
     * constructor
     *
     * @param Vector m
     */
    public VString (Vector m)
    {
        super();
        if(m!=null)
            addAll(m);
    }

    /**
     * constructs a VString by tokenizing a string
     * @param strIn the string to tokenize by blank
     * @deprecated use VString (String strIn, null)
     */
    public VString (String strIn)
    {
        this(strIn, null);
    }

    /**
     * 
     * constructs a VString by tokenizing a string
     * @param strIn the string to tokenize
     * @param strSep the separator character
     */
    public VString (String strIn, String strSep)
    {
        super();
        this.clear();
        
        if (strIn != null)
        {
            if(strSep==null)
                strSep=JDFConstants.BLANK;
            
            final StringTokenizer sToken = new StringTokenizer (strIn, strSep);
    
            while (sToken.hasMoreTokens())
            {
                this.addElement(sToken.nextToken());
            }
        }
    }


    /**
     * creates a VString from an array of Strings
     * @param a the array
     */
    public VString(String[] a)
    {
        super(a.length);
        for(int i=0;i<a.length;i++)
            add(a[i]);
    }

    //**************************************** Methods *********************************************
    public String stringAt(int index)
    {
        return (String) super.elementAt(index);
    }

    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "vString[ --> " + super.toString() + " ]";
    }
    
    /**
     * Method getAllStrings - returns all strings concatenated together
     * @param strSep separation between the strings
     * @return String
     * @deprecated use getString(JDFConstants.BLANK,null,null)
     */
    public String getAllStrings (String strSep)
    {
        return getString(strSep,null,null);
    }

    /**
     * 
     * @return all strings separated by a blank
    * @deprecated use getString(JDFConstants.BLANK,null,null)
     */
    public String getAllStrings()
    {
        return getString(JDFConstants.BLANK,null,null);
    }
    
    /**
     * Method setAllStrings - put a separated string into the vString
     *                        e.g.  "asdf asdf asdf asdf"
     * @param strIn  - separated string
     * @param strSep - string separator
     */
    public void setAllStrings (String strIn, String strSep)
    {
        if( (strIn != null) && (strSep != null) )
        {
            this.clear();
            final StringTokenizer sToken = new StringTokenizer (strIn, strSep);
    
            while (sToken.hasMoreTokens())
            {
                this.addElement(sToken.nextToken());
            }
        }
    }

    /**
     * index - get the index of s in the vector
     *
     * @param String s
     *
     * @return int
     */
    public int index(String s)
    {
        if(s==null)
            return -1;
        int siz=size();
        for (int i = 0; i < siz; i++)
        {
            if (s.equals(stringAt(i)))
            {
                return i;
            }
        }
        return -1;
    }

    /**
    * hasString - is 's' a member of this?
    * 
    * @param String s - string to find
    * 
    * @return boolean - true, if 's' is included in this
    * @deprecated 2005-02-14 use contains ...
    */
    public boolean hasString(String s)
    {
        return index(s)>=0;
    }
    
    /**
     * AppendUnique - append a string but ignore multiple entries
     *
     * @param String v
     */
    public void appendUnique(String v)
    {
        if(v==null)
            return;
        
        if (!contains(v))
        {
            addElement(v);
        }
    }

    /**
     * AppendUnique - append a vector but ignore multiple entries
     *
     * @param VString v
     */
    public void appendUnique(VString v)
    {
        if(v==null)
            return;
        final int size = v.size();
        for(int i=0;i<size;i++)
            this.add(v.elementAt(i));
        
        unify();
    }

    /**
     * removeStrings - remove all occurrences of a string
     *
     * @param VString v
     */
    public void removeStrings(VString v)
    {
        removeStrings(v, Integer.MAX_VALUE);
    }


    /**
     * removeStrings - remove all occurrences of a string
     *
     * @param VString v
     */
    public void removeStrings(VString v, int nMax)
    {
        if(v==null)
            return;
            
        for (int i=this.size()-1; i>=0 && nMax > 0; i--)
        {
            if (v.contains(this.elementAt(i)))
            {
                this.removeElementAt(i);
                nMax --;
            }
        }
    }


    /**
     * removeStrings - remove all occurrences of a string
     *
     * @param String s
     */
    public void removeStrings(String s)
    {
        removeStrings(s, Integer.MAX_VALUE);
    }
    
    /**
     * removeStrings - remove all occurrences of a string
     *
     * @param String s
     */
    public void removeStrings(String s, int nMax)
    {
        for (int i=this.size()-1; i>=0 && nMax > 0; i--)
        {
            if (this.contains(s))
            {
                this.removeElementAt(this.index(s));
                nMax --;
            }
        }
    }
    
    /**
    * serialize to a string
    * @param WString sep separator between strings
    * @param String front string before the first entry
    * @param String back string after the last entry
    * 
    * @return a tokenized string
    * @deprected use StringUtil setVString
    * default: GetString(sep, JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING)
    */
    public String getString(String sep, String front, String back)
    {
        
        return StringUtil.setvString(this,sep,front,back);
    }

    /**
     * create a string from a vector of tokens
     * @param VString v vector of tokens
     * @param String sep separator between tokens
     * @param String front prefix to string (before the first token)
     * @param String end suffix to string (after the last token)
     * @return condensed string of tokens separated by sep
     * @deprecated use getString
     */
    public String setvString(VString v, String sep, String front , String end)
    {
        String s = front==null ? JDFConstants.EMPTYSTRING : front;
        final int siz = v.size();
        for(int i = 0; i < siz; i++)
        {
            if(i != 0)//add seperator after every add
            {
                s+=sep;
            }
            s += v.elementAt(i);
        }
        if(end!=null)
        	s += end;
        return s;
    }


    /**
     * unify - make VString unique, retaining initial order
     */
    public void unify()
    {
        HashSet set=new HashSet();
        int size=size();
        for (int i=0;i<size;i++)
        {
            final String s = this.stringAt(i);
            if(set.contains(s))
            {
                this.removeElementAt(i);
                i--;
                size--;
            }
            else
            {
                set.add(s);
            }
        }        
    }
    
    /**
     * 
     * @param s the String you are looking for
     * @return the String if found or null if this does not contain s
     */
    public String get(String s)
    {
        if(contains(s))
        {
            int i = indexOf(s);
            return (String)get(i);
        }
        
        return null;
    }

    /**
     * sort this lexically
     *
     */
    public void sort()
    {
      Object[] array=this.elementData;
      Arrays.sort(array,0,size());       
    }

    /**
     * appends all strings of an array to this
     * @param strings
     */
    public void addAll(String[] strings)
    {
        ensureCapacity(size()+strings.length);
        for(int i=0;i<strings.length;i++)
            add(strings[i]);       
    }
    
    /**
     * appends o to this
     * if o is a String, o is appended directly
     * if o is a ValuedEnum, the name is appended
     * 
     * @param o the object to append
     * @throws IllegalArgumentException it o is neiter a String or an enum
     */
    public boolean add(Object o)
    {
        if(o instanceof String)
            return super.add(o);
        else if (o instanceof ValuedEnum)
            return super.add(((ValuedEnum)o).getName());
        throw new IllegalArgumentException();
    }

    /**
     * true if one of rootPartIDKeys is in this
     * @param other the VSTring of values to test
     * @return true if at least one String in other is in this
     */
    public boolean containsAny(VString other)
    {
        if(other==null)
            return false;
        final int size = other.size();
        for(int i=0;i<size;i++)
        {
            if(contains(other.elementAt(i)))
                return true;
        }
        return false;
    }

    //////////////////////////////////////////////////////////
}
