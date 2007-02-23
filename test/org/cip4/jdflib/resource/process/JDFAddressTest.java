package org.cip4.jdflib.resource.process;

import junit.framework.TestCase;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;

public class JDFAddressTest extends TestCase
{
    /**
     * tests the separationlist class
     *
     */
    public final void testExtendedAddress()
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
        KElement kElem = resPool.appendResource(ElementName.ADDRESS, null, null);
        assertTrue(kElem instanceof JDFAddress);
        JDFAddress ad=(JDFAddress)kElem;
        JDFComment c=(JDFComment) ad.appendExtendedAddress();
        assertFalse(c.hasAttribute(AttributeName.ID));
    }
}
