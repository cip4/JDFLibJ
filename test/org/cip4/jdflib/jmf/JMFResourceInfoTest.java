package org.cip4.jdflib.jmf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFMedia;

/**
 * @author Rainer Prosi
 *
 * Test of the ResourceInfo JMF element
 */
public class JMFResourceInfoTest extends JDFTestCaseBase
{
    private JDFResourceInfo ri;

    public void testGetResource()
    {
        assertNull(ri.getResource(ElementName.MEDIA));
        JDFMedia m=(JDFMedia) ri.appendResource(ElementName.MEDIA);
        JDFMedia m2=(JDFMedia) ri.getResource(ElementName.MEDIA);
        assertEquals(m,m2);
        assertTrue(ri.isValid(EnumValidationLevel.Complete));
    }       
    /////////////////////////////////////////////////////////////////////

    public void testGetResourceNull()
    {
        JDFMedia m=(JDFMedia) ri.appendResource(ElementName.MEDIA);
        JDFMedia m2=(JDFMedia) ri.getResource(null);
        assertEquals(m,m2);
        assertTrue(ri.isValid(EnumValidationLevel.Complete));
    }       
    /////////////////////////////////////////////////////////////////////
    public void testGetResourceName()
    {
        JDFMedia m=(JDFMedia) ri.appendResource(ElementName.MEDIA);
        String name= ri.getResourceName();
        assertEquals(name,ElementName.MEDIA);
    }       
    /////////////////////////////////////////////////////////////////////
    public void testGetResourceID()
    {
        JDFMedia m=(JDFMedia) ri.appendResource(ElementName.MEDIA);
        String name= ri.getResourceID();
        assertEquals(name,m.getID());
    }       
    ////////////////////////////////////////////////////////////////////////
    public void testGetProductID()
    {
        JDFMedia m=(JDFMedia) ri.appendResource(ElementName.MEDIA);
        m.setProductID("p1");
        String name= ri.getProductID();
        assertEquals(name,m.getProductID());
    }       
    /////////////////////////////////////////////////////////////////////
    public void testGetResStatus()
    {
        JDFMedia m=(JDFMedia) ri.appendResource(ElementName.MEDIA);
        EnumResStatus name= ri.getResStatus();
        assertEquals(name,m.getResStatus(false));
    }       
    /////////////////////////////////////////////////////////////////////

    /* (non-Javadoc)
     * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        JDFDoc doc = new JDFDoc(ElementName.JMF);
        JDFJMF jmf=doc.getJMFRoot();

        JDFResponse r=jmf.appendResponse();
        jmf.setSenderID("DeviceSenderID");
        r.setType(EnumType.Resource);
        ri=r.getCreateResourceInfo(0);

    }
}