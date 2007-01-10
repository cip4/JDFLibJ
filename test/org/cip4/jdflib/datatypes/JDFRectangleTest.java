/**
 * JDFRectangleRangeTest.java
 *
 * @author Elena Skobchenko
 * 
 * Copyright (c) 2001-2004 The International Cooperation for the Integration 
 * of Processes in  Prepress, Press and Postpress (CIP4).  All rights reserved.
 */
package org.cip4.jdflib.datatypes;

import java.util.Iterator;
import java.util.Vector;

import org.cip4.jdflib.core.JDFConstants;

import junit.framework.TestCase;


public class JDFRectangleTest extends TestCase {

    public final void testRectangle() {
        JDFAttributeMap map0  = new JDFAttributeMap();
        JDFAttributeMap map1  = new JDFAttributeMap();
        JDFAttributeMap map2  = new JDFAttributeMap();
        JDFAttributeMap map3  = new JDFAttributeMap();
        JDFAttributeMap map4  = new JDFAttributeMap();
        JDFAttributeMap map5  = new JDFAttributeMap();

        map0.put("a", "111");
        map0.put("b", "222");
        map0.put("c", "333");

        map1.put("a", "111");
        map1.put("b", "222");
        map1.put("c", "333");
        map1.put("d", "444");

        map2.put("a", "111");
        map2.put("b", "222");
        map2.put("c", "333");
        map2.put("d", "444");
        map2.put("e", "555");

        map3.put("a", "111");
        map3.put("b", "222");
        map3.put("c", "333");
        map3.put("d", "444");
        map3.put("e", "555");
        map3.put("f", "666");

        map4.put("a", "111");
        map4.put("b", "222");
        map4.put("c", "333");
        map4.put("d", "444");
        map4.put("e", "555");
        map4.put("f", "666");
        map4.put("g", "777");
        map4.put("h", "888");

        map5.put("a", "111");
        map5.put("b", "222");
        map5.put("x", "ksjhf");

        VJDFAttributeMap vMap = new VJDFAttributeMap();
        vMap.addElement(map0);
        vMap.addElement(map1);
        vMap.addElement(map2);
        vMap.addElement(map3);

        System.out.println("pos 1-------------------------------------");
        
        for (int i = 0; i < vMap.size(); i++)
        {
            Iterator mapKeys    = vMap.elementAt(i).getKeyIterator();
            String key, value   = JDFConstants.EMPTYSTRING;
            while (mapKeys.hasNext())
            {
                key = (String)mapKeys.next();
                value = vMap.elementAt(i).get(key);
                System.out.println("map["+i+"]key: " + key + " value: " + value);
            }
        }

        Vector vec = new Vector();
        vec.add("a");
        vec.add("b");

        vMap.reduceKey(vec);

        System.out.println("pos 2-------------------------------------");
        
        for (int i = 0; i < vMap.size(); i++)
        {
            Iterator mapKeys    = vMap.elementAt(i).getKeyIterator();
            String key, value   = JDFConstants.EMPTYSTRING;
            while (mapKeys.hasNext())
            {
                key = (String) mapKeys.next();
                value = vMap.elementAt(i).get(key);
                System.out.println("map["+i+"]key: " + key + " value: " + value);
            }
        }

    }
}
