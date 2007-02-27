package org.cip4.jdflib.resource.process;

import junit.framework.TestCase;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.util.StringUtil;

public class JDFColorantControlTest extends TestCase
{
    private JDFNode elem;
    private JDFColorantControl colControl;
    private JDFSeparationList colParams;

    /**
     * tests the separationlist class
     *
     */
    public final void testSeparationList()
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
        KElement kElem = resPool.appendResource(ElementName.COLORANTCONTROL, null, null);
        assertTrue(kElem instanceof JDFColorantControl);
        JDFColorantControl cc = ((JDFColorantControl) kElem);
        JDFSeparationList co=cc.appendColorantOrder();
        final VString seps=StringUtil.tokenize("Cyan Magenta Yellow Black"," ",false);

        co.setSeparations(seps);
        assertEquals( co.getSeparations(),seps);

        assertEquals(co.getSeparation(0),"Cyan");
        co.removeSeparation("Magenta");
        assertEquals(co.getSeparation(0),"Cyan");
        assertEquals(co.getSeparation(1),"Yellow");
        assertEquals(co.getSeparation(2),"Black");
        assertNull(co.getSeparation(3));
    }


    /**
     * tests the separationlist class
     *
     */
    public final void testGetSeparations()
    {
        JDFDoc doc = new JDFDoc("JDF");
        JDFNode root = doc.getJDFRoot();
        JDFResourcePool resPool = root.getCreateResourcePool();
        KElement kElem = resPool.appendResource(ElementName.COLORANTCONTROL, null, null);
        assertTrue(kElem instanceof JDFColorantControl);
        JDFColorantControl cc = ((JDFColorantControl) kElem);
        cc.setProcessColorModel("DeviceCMYK");
        assertTrue(cc.getSeparations().contains("Cyan"));
        cc.appendColorantParams().appendSeparation("Snarf Blue");
        assertTrue(cc.getSeparations().contains("Snarf Blue"));
    } 


    ////////////////////////////////////////////////////////////////////////

    public void testColorantParams()
    {
        assertTrue(colParams.isValid(KElement.EnumValidationLevel.RecursiveComplete));
    }

    ////////////////////////////////////////////////////////////////////////

    public void testGetDeviceColorantOrderSeparations()
    {
        colParams.appendSeparation("Black");
        assertEquals(colControl.getDeviceColorantOrderSeparations(),colControl.getSeparations());
        assertEquals(colControl.getDeviceColorantOrderSeparations().size(),4);
        colParams.appendSeparation("Green");
        assertEquals(colControl.getDeviceColorantOrderSeparations(),colControl.getSeparations());
        assertEquals(colControl.getDeviceColorantOrderSeparations().size(),5);
        colControl.appendColorantOrder().appendSeparation("Green");
        assertEquals(colControl.getDeviceColorantOrderSeparations().size(),1);
        assertEquals(colControl.getDeviceColorantOrderSeparations().stringAt(0),"Green");
    }
    
    ////////////////////////////////////////////////////////////////////////

    public void testGetColorantOrderSeparations()
    {
        colParams.appendSeparation("Black");
        assertEquals(colControl.getColorantOrderSeparations(),colControl.getSeparations());
        assertEquals(colControl.getColorantOrderSeparations().size(),4);
        colParams.appendSeparation("Green");
        assertEquals(colControl.getColorantOrderSeparations(),colControl.getSeparations());
        assertEquals(colControl.getColorantOrderSeparations().size(),5);
    }

    /**
     * @return
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        JDFDoc doc = new JDFDoc(ElementName.JDF);
        elem = doc.getJDFRoot();
        JDFResourcePool rpool = elem.appendResourcePool();
        colControl = (JDFColorantControl) rpool.appendResource(ElementName.COLORANTCONTROL, EnumResourceClass.Parameter, null);
        colControl.setProcessColorModel("DeviceCMYK");
        colParams = colControl.appendColorantParams();
    }




}
