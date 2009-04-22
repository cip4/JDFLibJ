/**
 * 
 */
package org.cip4.jdflib.extensions;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoExposedMedia.EnumPlateType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.prepress.JDFColorSpaceSubstitute;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class XJDFTest extends JDFTestCaseBase
{
	JDFNode n = null;
	KElement e = null;

	/**
 * 
 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ConventionalPrinting);

		final JDFResource r = n.addResource("ExposedMedia", EnumUsage.Input);
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SignatureName, "sig1");
		final JDFResource r3 = r2.addPartition(EnumPartIDKey.SheetName, "s1");
		r3.setProductID("P1");
		final JDFExposedMedia xm0 = (JDFExposedMedia) r3;
		xm0.setPlateType(EnumPlateType.Dummy);
		final JDFCustomerInfo ci = n.appendCustomerInfo();
		ci.setCustomerJobName("foo");
	}

	/**
	 */
	public void testToXJDF()
	{
		e = new XJDF20().makeNewJDF(n, null);

		final JDFNode n2 = new JDFDoc("JDF").getJDFRoot();
		n2.setType(EnumType.ConventionalPrinting);

		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(n2.getOwnerDocument_JDFElement());
		final JDFDoc d2 = xCon.convert(e);
		assertNotNull(d2);
		final JDFNode nConv = d2.getJDFRoot();
		assertNotNull(nConv);
		JDFExposedMedia xm = (JDFExposedMedia) nConv.getResource("ExposedMedia", EnumUsage.Input, 0);
		final JDFResourceLink rl = nConv.getLink(xm, null);
		assertNotNull(rl);
		xm = (JDFExposedMedia) rl.getTarget();
		assertEquals(xm.getProductID(), "P1");
		assertEquals(xm.getPlateType(), EnumPlateType.Dummy);
	}

	/**
	 */
	public void testToXJDFWithProduct() throws Exception
	{
		final JDFNode nP = new JDFDoc("JDF").getJDFRoot();
		nP.setType(EnumType.Product);
		nP.addResource("LayoutIntent", EnumUsage.Input);
		n = (JDFNode) nP.copyElement(n, null);
		final JDFResource c = n.addResource("Component", EnumUsage.Output);
		nP.linkResource(c, EnumUsage.Output, null);

		e = new XJDF20().makeNewJDF(nP, null);
		assertNotNull(e.getXPathElement("ProductList/Product/IntentSet[@Name=\"LayoutIntent\"]"));
	}

	/**
	 * @throws Exception
	 */
	public void testNamedFeatures() throws Exception
	{

		n.setNamedFeatures(new VString("k1 v1 k2 v2", null));
		e = new XJDF20().makeNewJDF(n, null);
		assertEquals(e.numChildElements(ElementName.GENERALID, null), 2);
		for (int i = 1; i < 3; i++)
		{
			final JDFGeneralID gi = (JDFGeneralID) e.getElement(ElementName.GENERALID, null, i - 1);
			assertEquals(gi.getIDUsage(), "k" + i);
			assertEquals(gi.getIDValue(), "v" + i);
		}
		assertNull(e.getAttribute(AttributeName.NAMEDFEATURES, null, null));

	}

	/**
	 * @throws Exception
	 */
	public void testColorPool() throws Exception
	{

		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("Black", null).setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		cp.appendColorWithName("Yellow", null).setCMYK(new JDFCMYKColor(0, 0, 1, 0));
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ParameterSet[@Name=\"Color\"]/Parameter/Part[@Separation=\"Black\"]"));
	}

	/**
	 * @throws Exception
	 */
	public void testColorPoolRef() throws Exception
	{
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, null);
		cp.appendColorWithName("Black", null).setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		cp.appendColorWithName("Yellow", null).setCMYK(new JDFCMYKColor(0, 0, 1, 0));
		n.getResource("ExposedMedia", null, 0).refElement(cp);
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ParameterSet[@Name=\"Color\"]/Parameter/Part[@Separation=\"Black\"]"));
		assertEquals(e.getXPathElement("ParameterSet[@Name=\"Color\"]").numChildElements("Parameter", null), 2);
	}

	/**
	 * @throws Exception
	 */
	public void testRefElement() throws Exception
	{
		final JDFMedia m = (JDFMedia) n.addResource(ElementName.MEDIA, null);
		n.getResource("ExposedMedia", null, 0).refElement(m);
		final JDFCustomerInfo ci = n.getCustomerInfo();
		final JDFResource r = ci.appendCompany().makeRootResource(null, null, true);

		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ResourceSet[@Name=\"Media\"]"));
		assertEquals(e.getXPathAttribute("ResourceSet[@Name=\"Media\"]/Resource/@ID", null), e.getXPathAttribute("ResourceSet[@Name=\"ExposedMedia\"]/Resource/ExposedMedia/@MediaRef", null));

		assertNull(e.getXPathElement("ParameterSet[@Name=\"Company\"]"));
		assertNotNull(e.getXPathElement("ParameterSet[@Name=\"CustomerInfo\"]/Parameter/CustomerInfo/Company"));

	}

	/**
	 * @throws Exception
	 */
	public void testSeparationList() throws Exception
	{
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("Black", null).setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		cp.appendColorWithName("Yellow", null).setCMYK(new JDFCMYKColor(0, 0, 1, 0));
		final JDFColorantControl cc = (JDFColorantControl) n.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		cc.appendColorantOrder().setSeparations(new VString("Cyan a b CC", null));
		e = new XJDF20().makeNewJDF(n, null);
		assertEquals("Cyan a b CC", e.getXPathAttribute("ParameterSet[@Name=\"ColorantControl\"]/Parameter/ColorantControl/@ColorantOrder", null));
	}

	/**
	 * @throws Exception
	 */
	public void testColorspaceSubstitute() throws Exception
	{
		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("Black", null).setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		cp.appendColorWithName("Yellow", null).setCMYK(new JDFCMYKColor(0, 0, 1, 0));
		final JDFColorantControl cc = (JDFColorantControl) n.addResource(ElementName.COLORANTCONTROL, EnumUsage.Input);
		final JDFColorSpaceSubstitute css = cc.appendColorSpaceSubstitute();
		css.appendSeparationSpec().setName("Spot1");
		css.appendPDLResourceAlias();
		e = new XJDF20().makeNewJDF(n, null);
		assertEquals("Spot1", e.getXPathAttribute("ParameterSet[@Name=\"ColorantControl\"]/Parameter/ColorantControl/ColorSpaceSubstitute/@SeparationSpec", null));
	}

	/**
	 * @throws Exception
	 */
	public void testContainer() throws Exception
	{

		final JDFFileSpec fs1 = (JDFFileSpec) n.addResource("FileSpec", EnumUsage.Input);
		final JDFFileSpec fsc = fs1.appendContainer().appendFileSpec();
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathAttribute("ParameterSet[@Usage=\"Input\"]/Parameter/FileSpec/@ContainerRef", null));

	}

	/**
	 * @throws Exception
	 */
	public void testDependencies() throws Exception
	{

		final JDFLayoutElement le = (JDFLayoutElement) n.addResource(ElementName.LAYOUTELEMENT, EnumUsage.Input);
		final JDFLayoutElement fsc = le.appendDependencies().appendLayoutElement();
		fsc.setDescriptiveName("dep");
		le.getDependencies().appendLayoutElement();
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathAttribute("ParameterSet[@Usage=\"Input\"]/Parameter/LayoutElement/@Dependencies", null));

	}

	/**
	 * @throws Exception
	 */
	public void testToXJDFCustomerInfo() throws Exception
	{
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ParameterSet/Parameter/CustomerInfo"));

	}

	/**
	 */
	public void testFromXJDF()
	{
		final JDFDoc d = new JDFDoc("JDF");
		// final XJDFToJDFConverter xc =
		new XJDFToJDFConverter(d);
	}

}
