/**
 * KElementTest.java
 * 
 * @author Dietrich Mucha
 *
 * Copyright (C) 2002 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.core;

import junit.framework.TestCase;

public class VElementTest extends TestCase
{
    public void testAddAll()
    {
        XMLDoc d=new XMLDoc("doc",null);
        KElement e=d.getRoot();
        VElement v=new VElement();
        v.addAll((VElement)null);
        assertEquals(v.size(), 0);
        v.add(e);
        assertEquals(v.size(), 1);
        v.addAll(v);
        assertEquals(v.size(), 2);
        v.addAll(v);
        assertEquals(v.size(), 4);
        
    }
    
    public void testContainsElement()
    {
        XMLDoc d=new XMLDoc("doc",null);
        KElement e=d.getRoot();
        KElement e1=e.appendElement("e1");
        e1.setAttribute("a","b");
        VElement v=new VElement();
        v.appendUnique(e1);
        e1=e.appendElement("e1");
        e1.setAttribute("a","b");
        assertTrue("containsElement",v.containsElement(e1));
        assertFalse("contains",v.contains(e1));
        e1.setText("foo");
        assertFalse("containsElement",v.containsElement(e1));
        v.appendUnique(e1);
        assertEquals("size",v.size(),2);
        e1=e.appendElement("e1");
        e1.setAttribute("a","b");
        e1.setText("foo");
        assertTrue("containsElement",v.containsElement(e1));
        e1.setText("bar");
        assertFalse("containsElement",v.containsElement(e1));
       
    }
    
    public void testgetNodeNames()
    {
        XMLDoc d=new XMLDoc("doc",null);
        KElement e=d.getRoot();
        e.appendElement("a1");
        e.appendElement("b:a2","b");
        VElement v=e.getChildElementVector(null,null, null, true,0,true);
        VString s=v.getElementNameVector(false);
        assertEquals(s, new VString("a1 b:a2"," "));
        s=v.getElementNameVector(true);
        assertEquals(s, new VString("a1 a2"," "));
    }
    /////////////////////////////////////////////
    
    public void testUnify()
    {
        XMLDoc d=new XMLDoc("doc",null);
        KElement e=d.getRoot();
        KElement e1=e.appendElement("e1");
        e1.setAttribute("a","b");
        VElement v=new VElement();
        v.add(e1);
        v.add(e1);
        e1=e.appendElement("e1");
        e1.setAttribute("a","b");
        v.add(e1);
        assertEquals(v.size(),3);
        v.unify();
        assertEquals(v.size(),2);
        v.unifyElement();
        assertEquals(v.size(),1);
       
    } 

}
