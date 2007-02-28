package org.cip4.jdflib.jmf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.resource.process.JDFMedia;

/**
 * @author Rainer Prosi
 *
 * Test of the ResourceInfo JMF element
 */
public class JMFResourceInfoTest extends JDFTestCaseBase
{
    public void testGetResource()
    {
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();

        JDFResponse r=jmf.appendResponse();
        jmf.setSenderID("DeviceSenderID");
        r.setType(EnumType.Resource);
        JDFResourceInfo ri=r.getCreateResourceInfo(0);
        assertNull(ri.getResource(ElementName.MEDIA));
        JDFMedia m=(JDFMedia) ri.appendResource(ElementName.MEDIA);
        JDFMedia m2=(JDFMedia) ri.getResource(ElementName.MEDIA);
        assertEquals(m,m2);
        assertTrue(ri.isValid(EnumValidationLevel.Complete));
    }       
    /////////////////////////////////////////////////////////////////////
}