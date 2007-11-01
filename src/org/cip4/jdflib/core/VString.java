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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.util.StringUtil;

/**
 *
 */
public class VString extends Vector<String>
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
     * @param m
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
    @Deprecated
	public VString (String strIn)
    {
        this(strIn, null);
    }

    /**
     * 
     * constructs a VString by tokenizing a string
     * @param strIn  the string to tokenize
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
        return super.elementAt(index);
    }

    /**
     * toString
     *
     * @return String
     */
    @Override
	public String toString()
    {
        return "vString[ --> " + super.toString() + " ]";
    }
    
    /**
     * Method getAllStrings - returns all strings concatenated together
     * @param strSep separation between the strings
     * @return String
     * @deprecated use getString(strSep,null,null)
     */
    @Deprecated
	public String getAllStrings (String strSep)
    {
        return StringUtil.setvString(this,strSep,null,null);
    }

    /**
     * @return all strings separated by a blank
     * @deprecated use getString(JDFConstants.BLANK,null,null)
     */
    @Deprecated
	public String getAllStrings()
    {
        return StringUtil.setvString(this,JDFConstants.BLANK,null,null);
    }
    
    /**
     * Method setAllStrings - put a separated string into the vString<br>
     *                        e.g.  "asdf asdf asdf asdf"
     * @param strIn  separated string
     * @param strSep string separator
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
    * hasString - is 's' a member of <code>this</code>?
    * 
    * @param s string to find
    * 
    * @return boolean - true, if 's' is included in <code>this</code>
    * @deprecated 2005-02-14 use contains ...
    */
    @Deprecated
	public boolean hasString(String s)
    {
        return index(s)>=0;
    }
    
    /**
     * AppendUnique - append a string but ignore multiple entries
     *
     * @param v the string to append
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
     * @param v the vector to append
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
     * @param v
     * @deprecated use removeStrings(v, Integer.MAX_VALUE);
     */
    @Deprecated
	public void removeStrings(VString v)
    {
        removeStrings(v, Integer.MAX_VALUE);
    }


    /**
     * removeStrings - remove all occurrences of a string
     *
     * @param v    the vector of strings to remove from <code>this</code>
     * @param nMax the max number of strings to remove
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
     * @deprecated use removeStrings(s, Integer.MAX_VALUE);
     */
    @Deprecated
	public void removeStrings(String s)
    {
        removeStrings(s, Integer.MAX_VALUE);
    }
    
    /**
     * removeStrings - remove nMax occurrences of a string
     *
     * @param s    the string to remove
     * @param nMax remove s max. nMax times
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
    * @param sep   separator between strings
    * @param front string before the first entry
    * @param back  string after the last entry
    * 
    * @return a tokenized string
    * @deprecated use StringUtil setVString
    * default: GetString(sep, JDFConstants.EMPTYSTRING, JDFConstants.EMPTYSTRING)
    */
    @Deprecated
	public String getString(String sep, String front, String back)
    {
        return StringUtil.setvString(this,sep,front,back);
    }

    /**
     * create a string from a vector of tokens
     * @param v     vector of tokens
     * @param sep   separator between tokens
     * @param front prefix to string (before the first token)
     * @param end   suffix to string (after the last token)
     * @return condensed string of tokens separated by sep
     * @deprecated use getString
     */
    @Deprecated
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
     * get a string from <code>this</code> 
     * @param s the String you are looking for
     * @return the String if found or null if <code>this</code> does not contain s
     */
    public String get(String s)
    {
        if(contains(s))
        {
            int i = indexOf(s);
            return get(i);
        }
        
        return null;
    }
    
    /** 
     * gets a set with all entries of the Vstring
     * @return
     */
    public Set getSet()
    {
        HashSet set = new LinkedHashSet();
        Iterator<String> it=iterator();
        while(it.hasNext())
            set.add(it.next());
        
        return set;
    }

    /**
     * sort <code>this</code> lexically
     */
    public void sort()
    {
      Object[] array=this.elementData;
      Arrays.sort(array,0,size());       
    }

    /**
     * appends all strings of an array to <code>this</code>
     * @param strings the array of strings to append to <code>this</code>
     */
    public void addAll(String[] strings)
    {
        ensureCapacity(size()+strings.length);
        for(int i=0;i<strings.length;i++)
            add(strings[i]);       
    }
    
    /**
     * checks whether at least one of a given vector of strings is contained 
     * in <code>this</code>
     * 
     * @param other the VSTring of values to test
     * @return true if at least one String in other is in <code>this</code>
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

    /**
     * appends enumType to <code>this</code><br>
     * if enumType is a ValuedEnum, the name is appended
     * 
     * @param enumType the object to append
     */
	public boolean add(EnumType enumType)
    {
		return super.add(((ValuedEnum) enumType).getName());
	}

}
