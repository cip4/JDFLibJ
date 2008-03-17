/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of 
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of 
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration 
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software 
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * KString.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.enums.EnumUtils;
import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.cformat.PrintfFormat;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.JDFXYPairRange;
import org.cip4.jdflib.datatypes.JDFXYPairRangeList;

import com.sun.imageio.metadata.XmlNames;



public class StringUtil
{
    //**************************************** Constants *******************************************
    private static final String m_sep   = JDFConstants.BLANK;
    /**
     * @deprecated use {@link UrlUtil}.m_URIEscape
     */
    @Deprecated
	public static final String m_URIEscape = UrlUtil.m_URIEscape;


//  ****************************************    Methods   ******************************************

    /**
     * returns a random string
     * really important routine - written on a friday afternoon ;-)
     * please add more at your leisure....
     * parts (c) Monty Python
     * @return
     */
    private static String[] strings={
        "Randomly inserted error",
        "fooBar",
        "Snafu",
        "Bad Karma",
        "What do you expect from a simulator",
        "Paper Jam",
        "Strawberry jam",
        "Elderberry jam",
        "Your mother was a hamster and your father smelt of elderberries!",
        "I'm French! Why do think I have this outrageous accent, you silly king-a?!",
        "You don't frighten us, English pig-dogs!",
        "Go and boil your bottom, sons of a silly person.",
        "I blow my nose at you, so-called Arthur King, you and all your silly English k-nnnnniggets. Thpppppt! Thppt! Thppt!",
        "I don't wanna talk to you no more, you empty headed animal food trough wiper!",
        "I fart in your general direction!",
        "C'est un lapin, lapin de bois.",
        "Quoi? Un cadeau. What? A present. Oh, un cadeau. Oui, oui. Hurry. What? Let's go. Oh. On y va. Bon magne. Over here...",
        "Oh. Oh, I see. Running away, eh? You yellow bastards! Come back here and take what's coming to you. I'll bite your legs off!",
        "You're using coconuts!",
        "The swallow may fly south with the sun or the house martin or the plover may seek warmer climes in winter, yet these are not strangers to our land?",
        "Are you suggesting coconuts migrate?",
        "It's not a question of where he grips it! It's a simple question of weight ratios! A five ounce bird could not carry a one pound coconut.",
        "Listen. In order to maintain air-speed velocity, a swallow needs to beat its wings forty-three times every second, right?",
        "Oh, King, eh, very nice. And how d'you get that, eh? By exploiting the workers! By 'anging on to outdated imperialist dogma which perpetuates the economic and social differences in our society. If there's ever going to be any progress with the--",
        "Oh! Come and see the violence inherent in the system! Help! Help! I'm being repressed!",
        "Here I am, brain the size of a planet, and they ask me to take you to the bridge. Call that job satisfaction, 'cause I don't.",
        "Ghastly, isn't it? All the doors on this spaceship have been programmed to have a cheery and sunny disposition.",
        "whazzap?",
        "Explain again how sheep's bladders may be employed to prevent earthquakes.",
        "Pardon me for breathing, which I never do anyway so I don't know why I even bothered to say it. Oh god, I'm so depressed.",
        "I've got this pain in all the diodes down my left side",
        "I would like to say that it is a very great pleasure, honour and privilege for me to open this bridge, but I can't because my lying circuits are all out of commission",
        "Do you want me to sit in the corner and rust, or just fall apart where I'm standing?"
        
};

    public static String getRandomString()
    {
        int pos=(int)(strings.length*Math.random()*0.99999);
        return strings[pos];
    }
    
    /**
     * Returns a string with deleted whitespaces near 'delim'
     * and from the both ends of the string (if they were there)<br>
     * 
     * tokenizes a given string 'str' into tokens without separators.
     * Trims every token from both sides to remove the whitespaces 
     * and builds a new string from these tokens separated by 'delim'.
     * 
     * @param str   working string
     * @param delim the delimiter
     *
     * @return String - the modified string
     */
    public static String zappTokenWS(String str, String delim)
    {
        String s = JDFConstants.EMPTYSTRING;

        VString vs=new VString(str, delim);
        int size=vs.size();

        if (size > 0)
        {
            s = (vs.elementAt(0)).trim();

            for (int i=1; i<size; i++)
            {
                s = s + delim + (vs.elementAt(i)).trim();
            }
        }
        return s;
    }

    /**
     * format a string using C++ sprintf functionality
     * 
     * @param format the format to print, see C++ spec for details
     * @param template - comma separated string - the values are parsed and the appropriate objects are created 
     *  more objects exist in template than the number of '%' tokens in format, the remainder of objects is ignored
     *  duplicate '\\,' is taken as literal ','
     * @return String the formatted string
     * @throws IllegalArgumentException in case format and o do not match, i.e. not eough objects are passed to fill format 
     */
    public static String sprintf(String format,String template)
    {
        if(template==null || format==null)
            return null;
        template=StringUtil.replaceString(template, "\\,", "__comma__Äﬂ-eher selten"); // quick hack ;-)

        VString vTemplate=tokenize(template, ",", false);
        Object[] vObj=new Object[vTemplate.size()];
        for(int i=0;i<vObj.length;i++)
        {
            String s=vTemplate.stringAt(i);
            if (isInteger(s))
                vObj[i]=new Integer(parseInt(s, 0));
            else if (isNumber(s))
            {
                vObj[i]=new Double(parseDouble(s, 0));
            }
            else 
            {
                vObj[i]= StringUtil.replaceString(s,"__comma__Äﬂ-eher selten",","); // quick hack ;-)
            }
        }
        return sprintf(format, vObj);
    }   
    


    /**
     * format a string using C++ sprintf functionality
     * 
     * @param format the format to print, see C++ spec for details
     * @param objects the array of objects, either String, Double, Integer or ValuedEnum, 
     * if objects is longer than the number of '%' tokens in format, the remainder of objects is ignored
     * The method works fairly loosely typed, thus doubles are printed as integers, Strings are converted to numbers, if possible etc.
     *  
     * @return String the formatted string
     * @throws IllegalArgumentException in case format and o do not match, i.e. not eough objects are passed to fill format 
     */
    public static String sprintf(String format,Object[] objects)
    {
        if(objects==null || format==null)
            return null;
        format=StringUtil.replaceString(format, "%%", "__percent__Äﬂ-eher selten"); // quick hack ;-)
        boolean bStart=format.startsWith("%");
        VString tokens=tokenize(format, "%", false);
        final int nStart = (bStart ? 0 : 1);
        if(tokens.size()>objects.length + nStart)
            throw new IllegalArgumentException("not enough tokens to satisfy format");

        // tokenize does not return an empty token if we start with %
        String s=bStart ? "" : tokens.stringAt(0);
        PrintfFormat f=new PrintfFormat("");

        for(int i=nStart; i<tokens.size(); i++)
        {
            f.set("%"+tokens.stringAt(i));
            Object ob=objects[i-nStart];
            if(ob instanceof String)
                s+=f.tostr((String)ob);
            else if(ob instanceof Integer)
                s+=f.tostr((Integer)ob);
            else if(ob instanceof Double)
                s+=f.tostr((Double)ob);
            else if(ob instanceof ValuedEnum)
                s+=f.tostr(((ValuedEnum)ob).getName());

        }
        return replaceString(s,"__percent__Äﬂ-eher selten","%"); // undo quick hack ;-)
    }

    /**
     * create a string from an array of tokens
     * 
     * @param v     the token array
     * @param sep   the separator between the tokens
     * @param front the front end of the string
     * @param back  the back  end of the string
     * @return String - the vector as String
     * 
     * default: setvString(v, JDFConstants.BLANK, null, null)
     */
    public static String setvString(String[] a, String sep, String front, String back)
    {
        if(a==null)
            return null;
        VString v=new VString(a);
        return setvString(v, sep, front, back);
    }

    /**
     * create a string from a vector of tokens
     * <p>
     * default: setvString(v, JDFConstants.BLANK, null, null)
     * 
     * @param v the token vector
     * 
     * @return String - the vector as String
     */
    public static String setvString(Vector v)
    {
        return setvString(v, m_sep, null, null);
    }

    /**
     * create a string from a vector of tokens
     * 
     * @param v     the token vector
     * @param sep   the separator between the tokens
     * @param front the front end of the string
     * @param back  the back  end of the string
     * 
     * @return String - the vector as String
     * 
     * default: setvString(v, JDFConstants.BLANK, null, null)
     */
    public static String setvString(Vector v, String sep, String front, String back)
    {
        if(v==null)
            return null;

        final int siz = v.size();
        StringBuffer buf=new StringBuffer(siz*16); // guestimat 16 chars per token max

        if(front!=null)
            buf.append(front);

        for (int i = 0; i < siz; i++)
        {
            if (i > 0 && sep!=null)
            {
                buf.append(sep);
            }
            final Object elementAt = v.elementAt(i);
            if(elementAt instanceof String)
            {
                buf.append( (String) elementAt);
            }
            else if(elementAt instanceof ValuedEnum)
            {
                buf.append(((ValuedEnum)elementAt).getName());
            }
            else
                throw new IllegalArgumentException("illegal vector contents");


        }

        if (back != null)
            buf.append(back);

        return buf.toString();
    }

    /**
     * n > 0    substring(0, n)             take the first n chars (leftmost)
     * n < 0    substring(0, s.length()+n)  take the string and cut n chars on the right
     * example:    string = "abcdefgh"
     *   string.leftStr( 2) = "ab"
     *   string.leftStr(-3) = "abcde"
     * 
     * @param strWork the string to work on
     * @param n number of characters to cut (negative) or retain (positive)
     * @return the modified string
     */
    public static String leftStr(String strWork, int n)
    {
        if(strWork==null)
            return null;
        if (n < 0)
        {
            n = strWork.length() + n;
        }
        if (n <= 0)
        {
            return null;
        }
        return strWork.substring(0, n <= strWork.length() ? n : strWork.length());
    }

    /**
     * get the end of a string
     n > 0    str.substring(str.length() - n)   take the rightmost n chars
     n < 0    substring(-n)                     take the string and cut n chars on the left
     example:    string = "abcdefgh"
        string.rightStr( 2) = "gh"
        string.rightStr(-3) = "defgh"
     * @param strWork the string to work on
     * @param n number of characters to cut (negative) or retain (positive)
     * @return the modified string
     */
    public static String rightStr(String strWork, int n)
    {
        if(strWork==null)
            return null;
        if (n < 0)
        {
            n = strWork.length()+n;
        }

        if(n <= 0)
        {
            return null;
        }
        if (n > strWork.length())
        {
            return strWork;
        }
        return strWork.substring(strWork.length() - n);
    }

    /**
     * return a vector of individual tokens<br>
     * Multiple consequtive delimitors are treated as one (similar to whitespace handling). 
     * <p>
     * default: tokenize(strWork, delim, false)
     * 
     * @param strWork the string to tokenize
     * @param delim       the delimiter, if null use whitespace
     * @param delim2token should a delimiter be a token?
     * @return the vector of strings
     */
    public static VString tokenize(String strWork, String delim, boolean delim2token)
    {
        delim=delim==null ? JDFConstants.BLANK : delim;
        VString v = new VString();
        if(strWork != null)
        {
            if(delim.length()==1 && strWork.indexOf(delim)<0)
            {
                v.add(strWork);
                return v;
            }
            StringTokenizer st = new StringTokenizer(strWork, delim, delim2token);
            while (st.hasMoreTokens())
            {
                v.add(st.nextToken());
            }
        }

        return v;
    }

    /**
     * check whether a String contains a given token
     * <p>
     * default: hasToken(strWork, token, delim, 0)
     * 
     * @param strWork the string to work on
     * @param token   the token to search for
     * @param delim   the delimiter of the tokens
     * @param iSkip   the number of matching tokens to skip before returning true
     * @return boolean - true if <code>strWork</code> contains <code>token</code>
     */
    public static boolean hasToken(String strWork, String token, String delim, int iSkip)
    {
        if(strWork != null)
        {
            int posToken1=strWork.indexOf(token);
            if(posToken1<0)
                return false;
            if(posToken1>0)
                posToken1--; // go back one in case the char before the first token was not the deliminator
            StringTokenizer st = new StringTokenizer(strWork.substring(posToken1), delim, false);
            int n=0;
            while (st.hasMoreTokens())
            {
                if(st.nextToken().equals(token))
                {
                    if(n++>=iSkip)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * check whether a vector of Strings contains a given token
     * <p>
     * default: hasToken(strWork, token, 0)
     * 
     * @param strWork the vector of strings string to work on
     * @param token   the token to search for
     * @param iSkip   the number of matching tokens to skip before returning true
     * @return true, if <code>strWork</code> contains <code>token</code>
     */
    public static boolean hasToken(String strWork[], String token, int iSkip)
    {
        if(strWork != null)
        {
            int n=0;
            for(int i=0;i<strWork.length;i++)
            {
                if(strWork[i].equals(token))
                {
                    if(n++>=iSkip)
                        return true;
                }
            }
        }
        return false;
    }
    /**
     * get a single token from a String
     * <p>
     * default: Token(strWork, index," \t\n")
     * 
     * @param strWork the String to work on
     * @param index   index of the token to return<br>
     *                if<0 return from end (e.g. -1 is the last token)
     * @param delim   the delimiter
     * @return the single token (<code>null</code> if no token found)
     */
    public static String token(String strWork, int index, String delim)
    {   
        if(strWork==null)
            return null; // null bleibt null
        if(delim==null)
            delim=JDFConstants.BLANK;
        
        final int pos=delim.length()==1 ? strWork.indexOf(delim) : 0;
        if(pos<0) // speed up incase we only have one entry
        {
            return (index==-1 || index==0) ? strWork : null;
        }
        
        if(index<0)
        {
             VString v = StringUtil.tokenize(strWork, delim, false);            
            index=v.size()+index;
            if(index<0)
                return null;       
            if (index<v.size())
                return v.stringAt(index);
            return null;
        }
        // index >0 don't need to calculate # of tokens
        StringTokenizer st = new StringTokenizer(strWork, delim, false);
        int n=0;
        String s=null;
        while (st.hasMoreTokens())
        {
            s=st.nextToken();
            if(n++==index)
                return s;
        }
        return null;        
    }

    /**
     * replace a character in a given String
     * <p>
     * default: replaceChar(strWork, c, s, 0)
     * 
     * @param strWork String to work on
     * @param c       characters to replace
     * @param replaceString       String to insert for c
     * @param offset
     * @return the String with replaced characters
     */
    public static String replaceCharSet(String strWork, String charSet, String replaceString, int offset)
    {
        if(charSet==null)
            return strWork;
        for(int i=0;i<charSet.length();i++)
            strWork= replaceChar(strWork,charSet.charAt(i),replaceString,offset);
        return strWork;
    }

    /**
     * replace any of a set of characters in a given String
     * <p>
     * default: replaceChar(strWork, c, s, 0)
     * 
     * @param strWork String to work on
     * @param c       characters to replace
     * @param replaceString       String to insert for c
     * @param offset
     * @return the String with replaced characters
     */
    public static String replaceChar(String strWork, char c, String replaceString, int offset)
    {
        if(strWork==null)
            return null;
        if(offset>strWork.length())
            return strWork;

        StringBuffer b = new StringBuffer(strWork.length()*2);
        int lastPos=offset;
        b.append(strWork.substring(0, offset));
        while(lastPos>=0)
        {
            int pos=strWork.indexOf(c, lastPos);
            if(pos>=0)
            {
                b.append(strWork.substring(lastPos, pos));
                if(replaceString!=null)
                    b.append(replaceString);
            }
            else
            {
                b.append(strWork.substring(lastPos));
            }
            lastPos=pos>=0 ? pos+1 : pos;
        }

        return b.toString();
    }
 
    /**
     * replace a string in a given String
     * <p>
     * default: replaceChar(strWork, c, s)
     * 
     * @param strWork         String to work on
     * @param toReplace       String to match and replace
     * @param replaceBy       String to insert for toReplace, null if nothing should be inserted
     * @return the String with replaced characters
     */
    public static String replaceString(String strWork, String toReplace, String replaceBy)
    {
        if(strWork==null)
            return strWork;
        int indexOf = strWork.indexOf(toReplace);
        if( indexOf<0)
            return strWork;

        int len = toReplace.length();
        StringBuffer b=new StringBuffer(strWork.length()*2);
        do
        {
            b.append(strWork.substring(0,indexOf));
            if(replaceBy!=null)
                b.append(replaceBy);
            strWork=strWork.substring(indexOf+len);
            indexOf = strWork.indexOf(toReplace);
        }
        while(indexOf>=0);
        b.append(strWork);

        return b.toString();
    }

    public static String xmlNameEscape(String strWork)
    {
        strWork = replaceChar(strWork, '*', "_star_", 0);
        strWork = replaceChar(strWork, '&', "_and_", 0);
        return strWork;
    }

    /**
     * the filename extension of pathName
     * @param pathName
     * @return
     * @deprecated use URLUtil.extension
     */
    @Deprecated
	public static String extension(String pathName)
    {
        return UrlUtil.extension(pathName);
    }

    public static String prefix(String strWork)
    {
        String ext = UrlUtil.extension(strWork);
        if(ext==null)
            return strWork;

        return strWork.substring(0, strWork.length() - ext.length() - 1);
    }

    /**
     * replace the .extension of a file name
     * @param strWork the file path
     * @param newExt the new extension (works with or without the initial "."
     * @return the strWork with a replaced extension
     */
    public static String newExtension(String strWork, String newExt)
    {
        if(newExt==null)
            return StringUtil.prefix(strWork);

        if(!newExt.startsWith("."))
            newExt="."+newExt;
        return StringUtil.prefix(strWork)+newExt;
    }

    /**
     * @deprecated 060314 use KElement.xmlnsprefix
     * @param strWork
     * @return String
     */
    @Deprecated
	public static String xmlNameSpace(String strWork)
    {
        return KElement.xmlnsPrefix(strWork);
    }

    ///////////////////////////////////////////////////////////////////

    /**
     * get the mime type for a given extension
     * @param strWork String to work in
     */
    public static String mime(String strWork)
    {
        String extension =UrlUtil.extension(strWork);
        if(extension==null)
            return JDFConstants.MIME_TEXTUNKNOWN;

        extension=extension.toLowerCase();

        if ("pdf".equals(extension))
        {
            return JDFConstants.MIME_PDF;
        }
        else if ("png".equals(extension))
        {
            return JDFConstants.MIME_PNG;
        }
        else if ("tif".equals(extension))
        {
            return JDFConstants.MIME_TIFF;
        }
        else if ("jdf".equals(extension))
        {
            return JDFConstants.MIME_JDF;
        }
        else if ("jdf".equals(extension))
        {
            return JDFConstants.MIME_JMF;
        }
        else if ("xml".equals(extension))
        {
            return JDFConstants.MIME_TEXTXML;
        }
        else if ("jpg".equals(extension) || "jpeg".equals(extension))
        {
            return JDFConstants.MIME_JPG;
        }
        else
        {
            return JDFConstants.MIME_TEXTUNKNOWN;
        }
    }

    /**
     * checks whether a string is a NMTOKEN
     * @param strWork the string to check
     * @return boolean - true if strWork is a NMTOKEN
     */    
    public static boolean isNMTOKEN(String strWork)
    {
        // TODO add more exceptions
        if(strWork==null)
            return false;
        if(strWork.length()>=64)
            return false;

        // if an error occurs for XmlNames do an "Organize Imports" (different packages in Java 1.4 and 5.0)
        return XmlNames.isNmtoken(strWork);		
    }

    /**
     * checks whether a string is an ID
     * @param strWork the string to check
     * @return boolean - true if strWork is an ID
     */    
    public static boolean isID(String strWork)
    {
        if(strWork==null || strWork.length()==0)
            return false;
        if(StringUtils.isNumeric(strWork.substring(0,1)))
            return false;
        return isNMTOKEN(strWork);
    }
    
    /**
     * return true if d1 and d2 are within a range of epsilon
     * or close enough to be serialized identically
     * @param d1
     * @param d2
     * @return true if (almost) identical
     */
    public static boolean isEqual(double d1, double d2)
    {
        if(d1==d2)
            return true;
        if(Math.abs(d1-d2)<JDFBaseDataTypes.EPSILON)
            return true;
        if(d1!=0 && Math.abs((d2/d1)-1.0)<JDFBaseDataTypes.EPSILON)
            return true;
        
        return false;
    }
    /**
     * return -1 if d1 < d2 , 0 if d1==d2 ; +1 if d1>d2 are within a range of epsilon
     * or close enough to be serialized identically
     * @param d1
     * @param d2
     * @return int 1,0 or -1
     */
    public static int compareTo(double d1, double d2)
    {
        if(isEqual(d1, d2))
            return 0;
        return  d1<d2 ? -1 : 1;

    }

    /**
     * checks whether a string is matches an NMTOKENS list
     * @param strWork the string to check
     * @return boolean - true if strWork is an NMTOKENS list
     * @deprecated 060309 use isNMTOKENS(strWork,false)
     */    
    @Deprecated
	public static boolean isNMTOKENS(String strWork)
    {
        return isNMTOKENS(strWork,false);
    }

    /**
     * checks whether a string is a NMTOKENS list
     * @param strWork the string to check
     * @param bID if true, also check that each individual token matches the pattern for an ID
     * @return boolean true if strWork is a NMTOKENS list
     */    
    public static boolean isNMTOKENS(String strWork, boolean bID)
    {
        if(strWork==null)
            return false;
        VString vs = StringUtil.tokenize(strWork, "\t ", false);
        int s = vs.size();
        if(s == 0)
        {
            return true; // tbd is an empty list an NMTOKENS ?
        }
        for(int i=0; i < s; i++)
        {
            if(   (bID && !StringUtil.isID(vs.stringAt(i)))
                    || !StringUtil.isNMTOKEN(vs.stringAt(i)) )
            {
                return false;
            }
        }
        return true;
    }

    /////////////////////////////////////////////////////////////////////

    /**
     * checks whether a string matches the boolean values "true" or "false"
     * @param strWork the string to check
     * @return boolean true if strWork is represents boolean value
     */
    public static boolean isBoolean(String strWork)
    {
        return "true".equals(strWork) || "false".equals(strWork);
    }

    /**
     * checks whether a string is a number
     * @param strWork the string to check
     * @return boolean true if strWork is a number
     */    
    public static boolean isNumber(String str)
    {
        if(str==null)
            return false;
        String dStr = str.trim();
        if (dStr.length()==0)
            return false;

        // if I get the default in both cases it is really snafu
        if((parseDouble(str,-1234567.987)==-1234567.987)
                &&(parseDouble(str,9876.473)==9876.473))
            return false;
        return true;
    }

    /**
     * find the last character in strwork that is not in strNotList
     * 
     * @param strWork the string to search
     * @param strNotList the list of characters to ignore
     * @return position of the last matching char, -1 if all strWork only contains chars from strNotList
     */
    public static int find_last_not_of(String strWork, String strNotList)
    {
        if(strWork==null)
            return -1;
        if(strNotList==null || strNotList.length()==0)
            return strWork.length()-1;

        for(int i =strWork.length()-1; i>=0; i--)
        {
            if(strNotList.indexOf(strWork.charAt(i))<0)
                return i;
        }

        return -1;
    }

    /**
     * returns the position of the token, if it is in the String.<br>
     * The separator is excluded from the tokens. 
     * Multiple consecutive separators are treated as one (similar to whitespace handling).
     * @param name      the token to search
     * @param separator separator
     * @param iSkip     number of tokens to skip before accepting 
     *                  (if 0 -> take the first etc., -1 -> first as well)
     * @return int - 0 based position if the token exists, else -1
     */
    public static int posOfToken(String strWork, String name, String separator, int iSkip)
    {
        int posOfToken = -1;
        VString vNames = new VString();
        if (!name.equals(JDFConstants.EMPTYSTRING))
        {
            vNames.addAll(StringUtil.tokenize(strWork, separator, false));
        }

        if(iSkip == -1 || iSkip == 0)
        {
            posOfToken = vNames.indexOf(name);
        }
        else
        {
            int occurence = 0;
            for(int i = 0; i < vNames.size(); i++)
            {
                String strName = vNames.elementAt(i);
                if(strName.equals(name))
                {
                    if(occurence++ == iSkip)
                    {
                        posOfToken = i;
                        break;
                    }
                }
            }
        }
        return posOfToken;
    }


    /**
     * check whether a string contains a complete token 
     * <p>
     * default: hasToken(strWork, token, delim)
     * 
     * @param strWork the string to work on
     * @param typ     the token to search for
     * @param delim   the delimiter of the tokens
     * @return boolean - 
     * @deprecated 060420 use hasToken(strWork, token, delim, 0)
     */
    @Deprecated
	public static boolean hasToken(String strWork, String typ, String delim)
    {
        return hasToken(strWork,typ,delim,0);
    }



    /**
     * set this to the raw bytes specified in buffer, bypassing all transcoders
     * @param buffer the buffer to assign to <code>this</code>
     * @param len
     */
    public static String setRawBytes(byte[] buffer, int len)
    {

        if(len < 0)
        {
            len = buffer.length;
        }

        char[] target = null;

        if (!(buffer.length < 0))
        {

            if (len > 0)
            {
                target = new char[len];

                for (int i = 0; i < len; i++)
                {
                    target[i] = (char)buffer[i];
                }
            }
            else
            {
                target = new char[0]; //should never reached
            }
        }
        else
        {
            target = new char[0];
        }
        return new String(target);
    }

    /**
     * get the raw bytes specified in strUnicode, bypassing all transcoders<br>
     * any character values above 255 is truncated (c=c&0xff)
     * @return char array of the raw bytes assigned to this
     */
    public static byte[] getRawBytes(String strUnicode)
    {
        char[] pBuf = strUnicode.toCharArray();

        int len = pBuf.length;

        byte[] pc = new byte[len];

        for (int i = 0; i < len; i++)
        {
            pc[i] = (byte)(pBuf[i] & 0x00ff);
        }
        return pc;
    }

    /**
     * get buffer as HexBinary <br>
     * any character values above 255 is truncated
     * @param buffer  the String which you want to encode to HexBinary
     * @param len     the length of the buffer. <br>
     *                If<0, default is -1. In this case the lenght of the char array will be used.
     */

    public static String setHexBinaryBytes(byte[] buffer, int len)
    {
        char[] target = null;

        if (buffer.length >= 0)
        {
            if (len < 0)
            {
                len = buffer.length;
                target = new char[len * 2];
            }
            if (len > 0)
            {
                target = new char[len * 2];
                for (int i = 0; i < len; i++)
                {
                    char c = (char)((buffer[i] & 0x00f0) >> 4);
                    target[2 * i] = (c >= 10) ? (char)('A' - 10 + c) : (char)('0' + c);
                    c = (char)(buffer[i] & 0x000f);
                    target[2 * i + 1] = (c >= 10) ? (char)('A' - 10 + c) : (char)('0' + c);
                }
            }
            else
            {
                target = new char[0];
            }
        }
        else
        {
            target = new char[0];
        }
        return new String(target);
    }

    /**
     * Decode a HexBinary encoded byte array back to Unicode
     * @param unicodeArray array which stores the HexBinary
     * @return array of byte holding the unicode chars 
     */
    public static byte[] getHexBinaryBytes(byte[]  unicodeArray)
    {
        byte emptyArray[] = new byte[0];
        int len = unicodeArray.length;

        //  check if there is at least one 16Bit unicode char
        if (len%2 > 0)
        {
            return emptyArray; 
        }

        //this will be the container for output                
        byte pc[] = new byte[len/2];
        byte c = '0'; 

        for (int i = 0; i < len/2; i++)
        {
            //maskiert das obere Byte
            int p   = unicodeArray[i * 2] & 0x00ff;

            //System.out.println((int)'0');

            if (p >= '0' && p <= '9')
            {
                c = (byte)(p - '0');
            }
            else
            {
                if (p >= 'A' && p <= 'F')
                {
                    c = (byte)(10 + p - 'A');
                }
                else
                {
                    if (p >= 'a' && p <= 'f')
                    {
                        c = (byte)(10 + p - 'a');
                    }
                    else
                    {
                        return emptyArray;
                    }
                }
            }

            pc[i] = (byte)(c << 4);


            p = unicodeArray[i * 2 + 1] & 0x00ff;

            if (p >= '0' && p <= '9')
            {
                c = (byte)(p - '0');
            }
            else
            {
                if (p >= 'A' && p <= 'F')
                {
                    c = (byte)(10 + p - 'A');
                }
                else
                {
                    if (p >= 'a' && p <= 'f')
                    {
                        c = (byte)(10 + p - 'a');
                    }
                    else
                    {
                        return emptyArray;
                    }
                }
            }
            pc[i] += c;
        }
        return pc;
    }




    /**
     * return the UTF8 String <code>strUnicode</code> as Unicode byte array
     * @param strUnicode the unicode string to transcode to utf8
     * @return a byte array[] representing the utf-8 code of the input string, 
     *         <code>null</code> if an error occurred
     */
    public static byte[] setUTF8String(String strUnicode)
    {
        if (strUnicode != null && !strUnicode.equals(JDFConstants.EMPTYSTRING))
        {
            try
            {
                return strUnicode.getBytes("UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                return null;
            }
        }
        return null;
    }


    /**
     * get the unicode string representing the UTF8 representation of the byte buffer
     *
     * @return String - the unicode string representation of the utf8 bytes assigned to this, 
     *         <code>null</code> if an error occurrred
     */
    public static String getUTF8String(byte utf8[])
    {
        if (utf8 != null && utf8.length != 0)
        {
            try
            {
                return new String(utf8, "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
                return null;
            } 
        }
        return null;
    }

    /**
     * returns a formatted double. 
     * Truncates to 8 digits after the "." <br>
     * If the double is representable as an integer, any ".0" is stripped.
     * 
     * @param d the double to format
     * @return the formatted string that represents d
     * TBD handle exp format
     */
    public static String formatDouble(double d)
    {
        String s;
        if(d==Double.MAX_VALUE)
        {
            s=JDFConstants.POSINF;
        }
        else if(d==-Double.MAX_VALUE)
        {
            s=JDFConstants.NEGINF;
        }
        else
        {
            s=String.valueOf(d);
            if(s.endsWith(".0"))
                s=s.substring(0,s.length()-2);
            if(s.indexOf("E")>=0)
            {
                Double[] ad={new Double(d)};
                s=sprintf("%10.10f", ad);
            }

            if(s.length()>10)
            {
                int posDot=s.indexOf(JDFConstants.DOT);
                if(posDot>=0)
                {
                    int l=s.length();
                    if(l-posDot>8)
                    {
                        l=posDot+9;
                        s=s.substring(0,l);
                        if(s.endsWith("999"))
                            return formatDouble(d+0.000000004);

                        int n;
                        for(n=l;n>posDot;n--)
                        {
                            if(!s.substring(n-1,n).equals("0"))
                                break;
                        }
                        s=s.substring(0,n);
                    }
                }                
            }
            if(s.endsWith("."))
            {
                s=leftStr(s, -1);                
            }
            if("-0".equals(s))
                s="0";
        }
        return s;
    }

    /**
     * returns a formatted integer, 
     * replaces string constants with according int constants
     * @param i the integer to format
     * @return the formatted string that represents i
     * TBD handle exp format
     */
    public static String formatInteger(int i)
    {
        String s=null;

        if(i==Integer.MAX_VALUE)
        {
            s=JDFConstants.POSINF;
        }
        else if(i==Integer.MIN_VALUE)
        {
            s=JDFConstants.NEGINF;
        }
        else
        {
            s=String.valueOf(i);
        }
        return s;
    }    

    /**
     * checks whether <code>str</code> reprents an integer
     * @param str the String to check
     * @return boolean - true if the string represents an integer number
     */
    public static boolean isInteger(String str)
    {
        if(str==null)
            return false;
        String intStr = str.trim();
        if (intStr.length()==0)
            return false;

        if (intStr.equals(JDFConstants.POSINF))
            return true;

        if (intStr.equals(JDFConstants.NEGINF))
            return true;
        // hack for xml schema conformance, which uses unbounded to define + infinity
        if (intStr.equals("unbounded"))
            return true;

        try
        {
            new Integer(intStr);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    /**
     * escape a string by prepending escapeChar and a numerical representation of the string.
     * Characters to be escaped are defined by toEscape, escapeBelow and escapeAbove
     * <p>
     * default: escape(String toEscape, null, 0, 0, 0, 256); //Note that an escaped character can't be 
     *          unescaped without the knowledge of the escapelength   
     * 
     * @param strToEscape the String to escape
     * @param strCharSet the set of characters that should be escaped eg "‹÷ƒ¸ˆ‰"
     * @param strEscapeChar the character sequence that marks an escape sequence. 
     *                    If <code>null</code>, "\\" is used
     * 
     * @param iRadix      the numerical representation base of the escaped chars, 
     *                    e.g. 8 for octal, 16 for hex<br>
     *                    if radix == 0 the escape char is merely inserted<br>
     *                    if radix <0 the escape char is replaced by the prefix<br>
     *        valid radix: -1,0,2,8,10,16
     * 
     * @param iEscapeLen  the number of digits per escaped char, not including escapeChar
     * 
     * @param iEscapeBelow all characters with an encoding below escapeBelow should also be escaped
     * 
     * @param iEscapeAbove all characters with an encoding above escapeAbove should also be escaped
     * 
     * @return the string where all illegal sequences have been replaced by their escaped representation
     */
    public static String escape(String strToEscape, String strCharSet, String strEscapeChar, int iRadix, int iEscapeLen, int iEscapeBelow, int iEscapeAbove)
    {
        if(strEscapeChar == null) 
        { 
            strEscapeChar = "\\"; 
        }

        if(iEscapeAbove<0)
            iEscapeAbove=0x7fffffff;

        //       String escapedString = JDFConstants.EMPTYSTRING;
        byte[] a_toEscape = strToEscape.getBytes();
        int l = a_toEscape.length;
        int cToEscape;
        byte[] escaped=new byte[a_toEscape.length*4];
        int posE=0;
        byte[]escapeCharbytes=strEscapeChar.getBytes();

        for(int i=0;i<l;i++)
        {
            cToEscape = a_toEscape[i];
            if(cToEscape<0)
                cToEscape=256+cToEscape;

            if ((cToEscape > iEscapeAbove)||(cToEscape < iEscapeBelow)||(strCharSet!=null && strCharSet.indexOf(cToEscape) != -1))
            {   // the character must be escaped
                for(int ee=0;ee<escapeCharbytes.length;ee++)
                {
                    escaped[posE]=escapeCharbytes[ee];
                    posE++;
                }
                if (iRadix > 0)
                {   // radix is a flag to convert to octal, hex etc.
                    StringBuffer buf = new StringBuffer();

                    if (iRadix == 2)
                    {
                        buf.append(Integer.toBinaryString(cToEscape)); 
                    }

                    else if (iRadix == 8)
                    {
                        buf.append(Integer.toOctalString(cToEscape)); 
                    }
                    else if (iRadix == 10)
                    {
                        buf.append(Integer.toString(cToEscape));
                    }
                    else if (iRadix == 16)
                    {
                        buf.append(Integer.toHexString(cToEscape));
                    }
                    else
                    {
                        throw new IllegalArgumentException("StringUtil.escape radix out of range");
                    }

                    if (iEscapeLen > 0)
                    {   //check if the length of the buffer is smaler then the ordered escape length. If this is the case
                        //insert some 0 in front of. for Example buf = 12345 iEscapeLen is 7. The result String is 0012345
                        int lenBuf = buf.length();
                        if (lenBuf < iEscapeLen)
                        { 
                            for(int j = 0; j < iEscapeLen - lenBuf; j++)
                            {
                                buf.insert(j, '0');
                            }
                        }
                    }

                    byte[] bufbytes= buf.toString().getBytes();
                    for(int ee=0;ee<bufbytes.length;ee++)
                    {
                        escaped[posE]=bufbytes[ee];
                        posE++;
                    }

                    //empty StringBuffer
                    buf.delete(0, buf.length());
                }
                else if (iRadix < 0)
                {
                    // noop
                }
                else
                { // radix = 0; just insert the escape character in front of the actual char
                    escaped[posE]=a_toEscape[i];
                    posE++;
                }
            }
            else
            { // no escape necessary --> just copy it
                escaped[posE]=a_toEscape[i];
                posE++;
            }
        }
        String escapedString = new String(escaped,0,posE);
        return escapedString;
    }

    /**
     * unescape a String which was escaped with the Java StringUtil.escape method
     * 
     * @param strToUnescape the String to unescape. For example <code>zz\d6\zzz\c4\\dc\z\d6\\24\\3f\zz‰z</code>
     * @param strEscapeChar the char which indicates a escape sequenze "\\" in this case (thats also the default)
     * @param iRadix        the radix of the escape sequenze. 16 in this example.
     * @param escapeLen     the number of digits per escaped char, not including strEscapeChar
     * 
     * @return the unescaped String. <code>zz÷zzzƒ‹z÷$?zz‰z</code> in this example
     */
    public static String unEscape(String strToUnescape, String strEscapeChar, int iRadix, int escapeLen)
    {
        byte[] byteUnEscape=strToUnescape.getBytes();
        byte[] byteEscape=new byte[byteUnEscape.length];
        byte escapeChar=strEscapeChar.getBytes()[0]; // dont even dream of using Ä as an escape char
        int n=0;
        byte[] escapeSeq=new byte[escapeLen];

        for(int i=0;i<byteUnEscape.length;i++)
        {
            if(byteUnEscape[i]!=escapeChar)
            {
                byteEscape[n++]=byteUnEscape[i];
            }
            else
            {
                for(int j=0;j<escapeLen;j++)
                    escapeSeq[j]=byteUnEscape[++i];

                String strIsEscaped = new String(escapeSeq);            //get the escaped str 'd6'
                Integer integer = Integer.valueOf(strIsEscaped, iRadix);//and get the int value 
                byteEscape[n++]=(byte) integer.intValue();
            }
        }
        byte[]stringByte=null;
        if(n==byteEscape.length)
            stringByte=byteEscape;
        else
        {
            stringByte=new byte[n];
            for(int i=0;i<n;i++)
                stringByte[i]=byteEscape[i];
        }
        return new String(stringByte);
    }

    /**
     * converts a VString to a single string represents all members of the VString concatenated together
     * @deprecated use vs.getString(" ",null,null)
     * @return String - the unicode string representation of the utf8 bytes assigned to this, null if an error occurrred
     */
    @Deprecated
	public static String vStringToString(VString vs)
    {
        return StringUtil.setvString(vs," ",null,null);
    }

    /**
     * parses a string to double and catches any format exception
     * @param s the string to parse
     * @param def the default to return in case of error
     * @return the parsed double of s
     */
    public static double parseDouble(String s, double def){
        if(s==null)
            return def;
        double d=def;
        s=s.trim();
        if(s.equals(JDFConstants.POSINF))
            return Double.MAX_VALUE;
        if(s.equals(JDFConstants.NEGINF))
            return -Double.MAX_VALUE;
        try
        {
            d = Double.parseDouble(s);
        }
        catch (NumberFormatException nfe)
        {
            d=def;
        }
        return d;	
    }
    /**
     * parses a string to double and catches any format exception
     * @param s the string to parse
     * @param def the default to return in case of error
     * @return the parsed double of s
     */
    public static boolean parseBoolean(String s, boolean def){
        if(s==null)
            return def;
        s=s.trim().toLowerCase();
        if("false".equals(s))
            return false;
        if("true".equals(s))
            return true;
        return def; 
    }

    /**
     * parses a string to double and catches any format exception
     * @param s the string to parse
     * @param def the default to return in case of error
     * @return the parsed double of s
     */
    public static int parseInt(String s, int def)
    {
        if(s==null)
            return def;
        int i=def;
        s=s.trim();
        if(s.equals(JDFConstants.POSINF))
            return Integer.MAX_VALUE;
        if(s.equals(JDFConstants.NEGINF))
            return Integer.MIN_VALUE;

        try
        {
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException nfe)
        {
            i=def;
        }
        return i;	
    }

    /**
     * Convert a UNC path to a valid file URL or IRL
     * note that some internal functions use network protocol and therefor performance may be non-optimal
     * 
     * @param unc The UNC string to parse, may also be used for local characters
     * @param bEscape128 if true, escape non -ascii chars (URI), if false, don't (IRI)
     * @return the URL string
     */
    public static String uncToUrl(String unc, boolean bEscape128)
    {
        return UrlUtil.fileToUrl(new File(unc), bEscape128);
    }


    /**
     * gets the file name from a path - regardless of the OS syntax that the path is declared in
     * @param pathName
     * @return
     */
    public static String pathToName(String pathName)
    {
        if(UrlUtil.isWindowsLocalPath(pathName)||UrlUtil.isUNC(pathName))
            return token(pathName,-1,"\\");
        return token(pathName,-1,"/");            
    }



    /**
     * @deprecated use UrlUtil.isWindowsLocalPath(pathName);
     */
    @Deprecated
	public static boolean isWindowsLocalPath(String pathName)
    {
        return UrlUtil.isWindowsLocalPath(pathName);

    }

/////////////////////////////////////////////////////////////////    
    /**
     * @deprecated use URLUtil.isUNC(pathName)
     */
    @Deprecated
	public static boolean isUNC(String pathName)
    {
        return UrlUtil.isUNC(pathName);
    }

    /**
     * checks whether smallAtt is a matching subset of bigAtt depending on datatype
     * 
     * @param smallAtt the small att to test
     * @param bigAtt the big att, e.g. list to test against
     * @param dataType the datatype of the big att
     * 
     * @return true if smallAtt is a matching subset of bigAtt
     */
    public static boolean matchesAttribute(String smallAtt, final String bigAtt, AttributeInfo.EnumAttributeType dataType)
    {
        final boolean bAny = dataType==null || dataType.equals(AttributeInfo.EnumAttributeType.Any);
        if(bAny)
            return bigAtt.equals(smallAtt);

        if ((dataType.equals(AttributeInfo.EnumAttributeType.NMTOKENS)) || 
                (dataType.equals(AttributeInfo.EnumAttributeType.enumerations)) ||
                (dataType.equals(AttributeInfo.EnumAttributeType.IDREFS)))
        {           
            // check for matching individual NMTOKEN
            VString vSmall=StringUtil.tokenize(smallAtt,JDFConstants.BLANK,false);
            for(int i=0;i<vSmall.size();i++)
            {
                if (!StringUtil.hasToken(bigAtt,vSmall.stringAt(i),JDFConstants.BLANK, 0))
                {
                    return false;
                }
            }
            return true;
        }

        if (dataType.equals(AttributeInfo.EnumAttributeType.NumberRange))
        {
            try
            {
                final JDFNumberRange r = new JDFNumberRange(bigAtt);
                if (r.inRange(Double.parseDouble(smallAtt)))
                { 
                    return true;
                }
            }
            catch(DataFormatException nfe)
            {
                //do nothing
            }
            catch (JDFException jdfe)
            {
                //do nothing
            }
            catch (IllegalArgumentException iae)
            {
                //do nothing
            }
        }

        if (dataType.equals(AttributeInfo.EnumAttributeType.NumberRangeList))
        {
            try
            {
                final JDFNumberRangeList r = new JDFNumberRangeList(bigAtt);
                if (r.inRange(Double.parseDouble(smallAtt))) 
                {
                    return true;
                }
            }
            catch(DataFormatException nfe)
            {
                //do nothing
            }
            catch (JDFException jdfe)
            {
                //do nothing
            }
            catch (IllegalArgumentException iae)
            {
                //do nothing
            }
        }

        if (dataType.equals(AttributeInfo.EnumAttributeType.IntegerList))
        {       
            try
            {
                final JDFIntegerList rBig = new JDFIntegerList(bigAtt);
                final Integer i=new Integer(smallAtt);
                if (rBig.contains(i)) 
                {
                    return true;
                }
            }
            catch(DataFormatException nfe)
            {
                //do nothing
            }
            catch (JDFException jdfe)
            {
                //do nothing
            }
            catch (IllegalArgumentException iae)
            {
                //do nothing
            }
        }
        if (dataType.equals(AttributeInfo.EnumAttributeType.IntegerRange))
        {       
            try
            {
                final JDFIntegerRange rBig = new JDFIntegerRange(bigAtt, Integer.MAX_VALUE);
                final JDFIntegerRange rSmal = new JDFIntegerRange(smallAtt, Integer.MAX_VALUE);
                if (rBig.isPartOfRange(rSmal)) 
                {
                    return true;
                }
            }
            catch(DataFormatException nfe)
            {
                //do nothing
            }
            catch (JDFException jdfe)
            {
                //do nothing
            }
            catch (IllegalArgumentException iae)
            {
                //do nothing
            }
        }

        if (dataType.equals(AttributeInfo.EnumAttributeType.IntegerRangeList))
        {       
            try
            {
                final JDFIntegerRangeList rBig = new JDFIntegerRangeList(bigAtt, Integer.MAX_VALUE);
                final JDFIntegerRangeList rSmal = new JDFIntegerRangeList(smallAtt, Integer.MAX_VALUE);
                if (rBig.isPartOfRange(rSmal)) 
                {
                    return true;
                }
            }
            catch(DataFormatException nfe)
            {
                //do nothing
            }
            catch (JDFException jdfe)
            {
                //do nothing
            }
            catch (IllegalArgumentException iae)
            {
                //do nothing
            }
        }

        if (dataType.equals(AttributeInfo.EnumAttributeType.XYPairRange))
        {
            try
            {
                final JDFXYPair xypair = new JDFXYPair(smallAtt);
                final JDFXYPairRange r = new JDFXYPairRange(bigAtt);
                if (r.inRange(xypair))
                {    
                    return true; 
                } 
            }
            catch (DataFormatException x)
            {
                //do nothing
            }
            catch (IllegalArgumentException x1)
            {
                //do nothing
            }
        }

        if (dataType.equals(AttributeInfo.EnumAttributeType.XYPairRangeList))
        {
            try
            {
                final JDFXYPair xypair = new JDFXYPair(smallAtt);
                final JDFXYPairRangeList r = new JDFXYPairRangeList(bigAtt);
                if (r.inRange(xypair))
                {    
                    return true; 
                } 
            }
            catch (DataFormatException x)
            {
                //do nothing
            }
            catch (IllegalArgumentException x1)
            {
                //do nothing
            }
        }
        return false;
    }

    /**
     * match a regular expression using String.matches(), but also catch
     * exceptions and handle simplified regexp.
     * The <code>null</code> expression is assumed to match anything.
     * 
     * @param str the string to match 
     * @param regExp the expression to match against
     * @return true, if str matches regExp or regexp is empty
     */
    public static boolean matches(String str, String regExp)
    {
        if(str==null)
            return false;
        // the null expression is assumed to match anything
        if((regExp==null)||(regExp.length()==0))
            return true;
        // this is a really common mistake
        if(regExp.equals("*"))
            regExp=".*";

        boolean b;
        try
        {
            b=str.matches(regExp);
        }
        catch (PatternSyntaxException e)
        {
            b=false;
        }
        return b;
    }

    /**
     * match a regular expression using ignoring cases using String.matches(), but also catch
     * exceptions and handle simplified regexp.
     * The <code>null</code> expression is assumed to match anything.
     * 
     * @param str the string to match 
     * @param regExp the expression to match against
     * @return true, if str matches regExp or regexp is empty
     */
    public static boolean matchesIgnoreCase(String str, String regExp)
    {
        return matches(str==null ? null : str.toLowerCase(), regExp==null ? null : regExp.toLowerCase());
    }

    /**
     * add the string appendString to all Strings in VString vs
     * @param vS the string vector
     * @param appendString the string to append
     */
    public static void concatStrings(VString vS, String appendString)
    {
        if(vS!=null)
        {
            for(int i=0;i<vS.size();i++)
            {
                String s=vS.stringAt(i);
                s+=appendString;
                vS.setElementAt(s,i);                  
            }
        }
    }       
    ////////////////////////////////////////////////////////////////////////

    /**
     * returns the relative URL of a file relative to the current working directory
     * @param f the file to get the relative url for
     * @param baseDir the file that describes cwd, if null cwd is calculated
     * @return
     * @deprecated use getRelativeURL(File f, File fCWD, boolean bEscape128)
     */
    @Deprecated
	public static String getRelativeURL(File f, File baseDir)
    {
        return UrlUtil.getRelativeURL(f, baseDir, true);
    }

    /**
     * returns the relative URL of a file relative to the current workin directory
     * @param f the file to get the relative url for
     * @param baseDir the file that describes cwd, if null cwd is calculated
     * @param bEscape128 if true, escape > 128 (URL) else retain (IRL)
     * @return
     *@deprecated use URLUtil.getRelativeURL
     */
    @Deprecated
	public static String getRelativeURL(File f, File baseDir, boolean bEscape128)
    {
        return UrlUtil.getRelativeURL(f, baseDir, bEscape128);
    }

    /**
     * returns the relative URL of a file relative to the current working directory<br>
     * this includes escaping of %20 etc.
     * 
     * @param f the file to get the relative path for
     * @param fCWD the file that describes cwd, if <code>null</code> cwd is calculated
     * @return
     * @deprecated use URLUtil.getRelativePath(f, fCWD);
     */
    @Deprecated
	public static String getRelativePath(File f, File fCWD)
    {
        return UrlUtil.getRelativePath(f, fCWD);        
    }

    /**
     * get a vector of names in an iteration
     * @param e any member of the enum to iterate over
     * @return VString - the vector of enum names
     */
    public static VString getNamesVector(Class e)
    {
        final VString namesVector = new VString();
        final Iterator it = EnumUtils.iterator(e);
        while (it.hasNext())
        {
            namesVector.addElement(((ValuedEnum)it.next()).getName());
        }

        return namesVector;
    }

    //////////////////////////////////////////////////////////////////////////////////

    /**
     * get a vector of elements in an iteration
     * @param e any member of the enum to iterate over
     * @return Vector - the vector of enum instances
     */
    public static Vector getEnumsVector(Class e)
    {
        final Vector v = new Vector();
        final Iterator it = EnumUtils.iterator(e);
        while (it.hasNext())
        {
            v.addElement(it.next());
        }       
        return v;
    }



    /**
     * @param f
     * @param b
     * @return
     * @deprecated use UrlUtil.fileToUrl(f, b);
     */
    @Deprecated
	public static String fileToUrl(File f, boolean b)
    {
        return UrlUtil.fileToUrl(f, b);
    }

    /**
     * strip a prefix, if it is there else return the string
     * 
     * @param str the string to strip
     * @param prefix the prefix to strip
     * @param bIgnoreCase if true ignore the case of the prefix
     * @return
     */
    public static String stripPrefix(String str, String prefix, boolean bIgnoreCase)
    {
        if(str==null)
            return null;
        if(prefix==null)
            return str;
        if(bIgnoreCase)
        {
            str=str.toLowerCase();
            prefix=prefix.toLowerCase();
        }
        if(str.startsWith(prefix))
        {
            str=StringUtil.rightStr(str, -prefix.length());
        }
        return str;
    }
    
    /**
     * returns a new string that has all characters stripped from work that are not in keepChars
     * @param work
     * @param keepChars
     * @return
     */
    public static String stripNot(String work, String keepChars)
    {
        if(work==null || keepChars==null)
            return null;
        StringBuffer b=new StringBuffer(work.length());
        for(int i=0;i<work.length();i++)
        {
            if(keepChars.indexOf(work.charAt(i))>=0)
                b.append(work.charAt(i));
            
        }
        return b.length()>0 ? b.toString() : null;
    }


    //////////////////////////////////////////////////////////////////////////////////

}
