package org.cip4.jdflib.resource.process;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.KElement.EnumValidationLevel;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.util.StringUtil;

public class JDFColorantControlTest extends JDFTestCaseBase
{
    private JDFNode elem;
    private JDFColorantControl colControl;
    private JDFSeparationList colParams;
    private JDFDoc d;

    /**
     * tests the separationlist class
     *
     */
    public final void testColorantAlias()
    {
        JDFColorantAlias ca=colControl.appendColorantAlias();
        ca.setXMLComment("ColorantAlias that maps the predefined separation Black");
        ca.setReplacementColorantName("Black");
        assertTrue(ca.isValid(EnumValidationLevel.Incomplete));
        assertFalse(ca.isValid(EnumValidationLevel.Complete));
        ca.setSeparations(new VString("Schwarz schwarz",null));
        assertTrue(ca.isValid(EnumValidationLevel.Complete));
        
        
        colParams.setSeparations(new VString("Acme Aqua",","));
        ca=colControl.appendColorantAlias();
        ca.setXMLComment("ColorantAlias that maps the separation Spot1");

        ca.setReplacementColorantName("Spot1");
        assertTrue(ca.isValid(EnumValidationLevel.Incomplete));
        assertFalse(ca.isValid(EnumValidationLevel.Complete));
        ca.setSeparations(new VString("Acme Aqua",","));
        assertTrue(ca.isValid(EnumValidationLevel.Complete));
        
        d.write2File(sm_dirTestDataTemp+"ColorantAlias.jdf",2,false);
        
    }

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
        VElement vSepSpec=co.getChildElementVector(ElementName.SEPARATIONSPEC, null, null, true, 0, true);
        assertEquals(vSepSpec.size(), seps.size());
        for(int i=0;i<vSepSpec.size();i++)
        {
            assertFalse(vSepSpec.item(i).hasAttribute(AttributeName.CLASS));
            assertFalse(vSepSpec.item(i) instanceof JDFResource);
        }

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
        JDFElement.setLongID(false);
        d = new JDFDoc(ElementName.JDF);
        elem = d.getJDFRoot();
        JDFResourcePool rpool = elem.appendResourcePool();
        colControl = (JDFColorantControl) rpool.appendResource(ElementName.COLORANTCONTROL, EnumResourceClass.Parameter, null);
        colControl.setProcessColorModel("DeviceCMYK");
        colControl.setResStatus(EnumResStatus.Available, true);
        colParams = colControl.appendColorantParams();
    }

}
