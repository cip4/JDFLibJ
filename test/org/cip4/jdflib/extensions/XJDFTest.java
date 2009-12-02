/**
 * 
 */
package org.cip4.jdflib.extensions;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoExposedMedia.EnumPlateType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFResourceInfo;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
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
		final JDFResource m = n.addResource("Media", null);
		final JDFResource m2 = m.addPartition(EnumPartIDKey.SignatureName, "sig1");
		final JDFMedia m3 = (JDFMedia) m2.addPartition(EnumPartIDKey.SheetName, "s1");
		m3.setMediaType(EnumMediaType.Plate);
		r3.refElement(m3);

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
		e.setAttribute("JobPartID", null);

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
	public void testXJDFRunList()
	{
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.bMergeRunList = true;
		n = new JDFDoc("JDF").getJDFRoot();
		JDFRunList rl = (JDFRunList) n.addResource("RunList", EnumUsage.Input);
		JDFRunList rlr1 = rl.addPDF("test.pdf", 0, 2);
		rlr1.getLayoutElement().setElementType(JDFLayoutElement.EnumElementType.Page);

		rl.addPDF("test2.pdf", 3, 6);
		e = xjdf20.makeNewJDF(n, null);
		KElement rlSet = e.getXPathElement("ParameterSet[@Name=\"RunList\"]");
		assertNotNull(rlSet);
		KElement rl2 = rlSet.getXPathElement("Parameter/RunList");
		assertNotNull(rl2);
		assertNull(rl2.getElement("LayoutElement"));

		XJDFToJDFConverter xc = new XJDFToJDFConverter(null);
		JDFDoc d = xc.convert(e);
		n = d.getJDFRoot();

	}

	/**
	 */
	public void testMergeStripping()
	{
		n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.Stripping);
		JDFRunList rl = (JDFRunList) n.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
		JDFStrippingParams sp = (JDFStrippingParams) n.appendMatchingResource(ElementName.STRIPPINGPARAMS, EnumProcessUsage.AnyInput, null);
		JDFBinderySignature bs = (JDFBinderySignature) n.addResource(ElementName.BINDERYSIGNATURE, null, null, null, null, null, null);
		JDFLayout lo = (JDFLayout) n.addResource("Layout", EnumUsage.Output);
		sp.refBinderySignature(bs);

		XJDF20 xjdf20 = new XJDF20();
		xjdf20.bMergeLayout = true;

		e = xjdf20.makeNewJDF(n, null);
		assertEquals(e.getXPathElementVector("//StrippingParams", -1).size(), 0);
		assertEquals(e.getXPathElementVector("//Layout", -1).size(), 1);

	}

	/**
	 */
	public void testJMFToXJDF()
	{
		final JDFJMF jmf = JDFJMF.createJMF(EnumFamily.Signal, org.cip4.jdflib.jmf.JDFMessage.EnumType.Resource);
		final JDFResourceInfo ri = jmf.getCreateSignal(0).appendResourceInfo();
		ri.setResourceName("ExposedMedia");
		final JDFExposedMedia xm = (JDFExposedMedia) ri.appendResource("ExposedMedia");
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("SignatureName", "Sig1");
		partMap.put("SheetName", "S1");
		partMap.put("Side", "Front");
		partMap.put("Separation", "Black");
		ri.appendAmountPool().appendPartAmount(partMap).setActualAmount(1, null);
		final JDFExposedMedia xmPart = (JDFExposedMedia) xm.getCreatePartition(partMap, new VString("SignatureName SheetName Side Separation", null));
		xmPart.appendMedia();
		e = new XJDF20().makeNewJMF(jmf);
		final JDFPart pNew = (JDFPart) e.getXPathElement("Signal/ResourceInfo/ResourceSet/Resource/Part");
		assertEquals(pNew.getPartMap(), partMap);

	}

	/**
	 * 
	 */
	public void xjdfSchemaTest()
	{
		final XJDFSchemaWalker sw = new XJDFSchemaWalker();
		final File in = new File(sm_dirTestSchema + "JDFResource.xsd");
		final File out = new File(sm_dirTestDataTemp + "schema/xjdf/JDFResource.xsd");
		sw.newFile(in, out);
	}

	/**
	*
	 */
	public void testToXJDFWithProduct()
	{
		final JDFNode nP = new JDFDoc("JDF").getJDFRoot();
		nP.setType(EnumType.Product);
		nP.setDescriptiveName("desc");
		nP.addResource("LayoutIntent", EnumUsage.Input);
		n = (JDFNode) nP.copyElement(n, null);
		final JDFResource c = n.addResource("Component", EnumUsage.Output);
		nP.linkResource(c, EnumUsage.Output, null);

		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ProductList/Product/IntentSet[@Name=\"LayoutIntent\"]"));
		assertEquals(e.getXPathAttribute("ProductList/Product/@DescriptiveName", null), "desc");
	}

	/**
	 *  
	 */
	public void testFromXJDFWithProduct()
	{
		testToXJDFWithProduct();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(e);
		final JDFNode root = d.getJDFRoot();
		assertEquals(root.getType(), "Product");
		assertEquals(root.getDescriptiveName(), "desc");
	}

	/**
	 *  
	 */
	public void testFromXJDFMedia()
	{
		testColorPool();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		final JDFDoc d = xCon.convert(e);
		d.write2File(sm_dirTestDataTemp + "Media.xjdf.jdf", 2, false);
		final JDFNode root = d.getJDFRoot();
		JDFResourceLink rl = root.getLink(0, "ExposedMedia", null, null);
		JDFExposedMedia xm = (JDFExposedMedia) rl.getTarget();
		JDFMedia m = xm.getMedia();
		assertNotNull(m);
		assertNotSame(m, m.getResourceRoot());
		assertNotNull(((JDFMedia) m.getResourceRoot()).getMediaType());
	}

	/**
	 *  
	 */
	public void testFromXJDFWithProductMulti()
	{
		n.setJobPartID("p1");
		testToXJDFWithProduct();
		final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
		JDFDoc d = xCon.convert(e);
		JDFNode root = d.getJDFRoot();
		assertEquals(root.getType(), "Product");
		n.setJobPartID("p2");
		testToXJDFWithProduct();
		d = xCon.convert(e);
		root = d.getJDFRoot();
	}

	/**
	 *  
	 */
	public void testNamedFeatures()
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
	*
	 */
	public void testColorPool()
	{

		final JDFColorPool cp = (JDFColorPool) n.addResource(ElementName.COLORPOOL, EnumUsage.Input);
		cp.appendColorWithName("Black", null).setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		cp.appendColorWithName("Yellow", null).setCMYK(new JDFCMYKColor(0, 0, 1, 0));
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ParameterSet[@Name=\"Color\"]/Parameter/Part[@Separation=\"Black\"]"));
	}

	/**
	 *  
	 */
	public void testColorPoolRef()
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
		// final JDFResource r =
		ci.appendCompany().makeRootResource(null, null, true);

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
		// final JDFFileSpec fsc =
		fs1.appendContainer().appendFileSpec();
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
		XJDF20 xjdf20 = new XJDF20();
		xjdf20.bMergeRunList = false;
		e = xjdf20.makeNewJDF(n, null);
		assertNotNull(e.getXPathAttribute("ParameterSet[@Usage=\"Input\"]/Parameter/LayoutElement/@Dependencies", null));

		xjdf20 = new XJDF20();
		xjdf20.bMergeRunList = true;
		e = xjdf20.makeNewJDF(n, null);
		assertNotNull(e.getXPathAttribute("ParameterSet[@Usage=\"Input\"]/Parameter/RunList/@Dependencies", null));
	}

	/**
	 *  
	 */
	public void testToXJDFCustomerInfo()
	{
		e = new XJDF20().makeNewJDF(n, null);
		assertNotNull(e.getXPathElement("ParameterSet/Parameter/CustomerInfo"));

	}

	/**
	 *  
	 */
	public void testFromXJDF()
	{
		testColorPool();
		final JDFDoc d2 = new XJDFToJDFConverter(null).convert(e);
		assertNotNull(d2);
	}

	/**
	 *  
	 */
	public void testFromXJDF2()
	{
		testColorPool();
		KElement eNext = e.getOwnerDocument_KElement().clone().getRoot();
		eNext.setAttribute("JobPartID", "eNext");
		e.setXPathAttribute("ProductList/Product/@JobID", "jid");
		final XJDFToJDFConverter converter = new XJDFToJDFConverter(null);
		final KElement e2 = e.getOwnerDocument_KElement().clone().getRoot();
		final VElement v = e2.getChildrenByTagName(null, null, new JDFAttributeMap("SheetName", "s1"), false, false, -1);
		for (int i = 0; i < v.size(); i++)
		{
			v.get(i).setAttribute("SheetName", "s2");
		}
		final JDFDoc d2a = converter.convert(e);
		final JDFDoc d2 = converter.convert(e2);
		assertNotNull(d2);
		assertEquals(d2a.getRoot(), d2.getRoot());
		final JDFDoc d3 = converter.convert(eNext);
		d3.write2File(sm_dirTestDataTemp + "xjdf2.jdf", 2, false);
	}

	/**
	 *  
	 */
	public void testRefFirst()
	{
		final JDFNode n0 = n = new JDFDoc("JDF").getJDFRoot();
		n.setType(EnumType.ProcessGroup);
		n = n0.addJDFNode(EnumType.ImageSetting);

		final JDFResource r = n.addResource("ExposedMedia", null, EnumUsage.Output, null, n0, null, null);
		final JDFResource r2 = r.addPartition(EnumPartIDKey.SignatureName, "sig1");
		final JDFResource r3 = r2.addPartition(EnumPartIDKey.SheetName, "s1");
		r3.setProductID("P1");
		final JDFExposedMedia xm0 = (JDFExposedMedia) r3;
		xm0.setPlateType(EnumPlateType.Dummy);
		final JDFResource m = n.addResource("Media", EnumUsage.Input);
		r.refElement(m);
		final KElement out = new XJDF20().makeNewJDF(n, null);
		assertEquals("Input", out.getXPathAttribute("ResourceSet[@Name=\"Media\"]/@Usage", null), "Input");

	}

}
