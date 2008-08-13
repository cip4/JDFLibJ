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
import org.cip4.jdflib.datatypes.JDFCMYKColor;
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
	private JDFColorPool colPool;
	private JDFDoc d;

	/**
	 * tests the separationlist class
	 * 
	 */
	public final void testColorantAlias()
	{
		JDFColorantAlias ca = colControl.appendColorantAlias();
		ca
				.setXMLComment("ColorantAlias that maps the predefined separation Black");
		ca.setReplacementColorantName("Green");
		assertTrue(ca.isValid(EnumValidationLevel.Incomplete));
		assertFalse(ca.isValid(EnumValidationLevel.Complete));
		final VString vAlias = new VString("Grün grün", null);
		ca.setSeparations(vAlias);
		assertTrue(ca.isValid(EnumValidationLevel.Complete));
		byte[] b = vAlias.stringAt(0).getBytes();
		String rawNames = StringUtil.setHexBinaryBytes(b, -1) + " ";
		b = vAlias.stringAt(1).getBytes();
		rawNames += StringUtil.setHexBinaryBytes(b, -1);
		assertTrue(ca.isValid(EnumValidationLevel.Complete));
		ca.setAttribute("RawNames", rawNames);

		d.write2File(sm_dirTestDataTemp + "ColorantAlias.jdf", 2, false);
	}

	/**
	 * tests the proposed Color/@ActualColorName attribute
	 * 
	 */
	public final void testActualColorName()
	{

		colParams
				.setXMLComment("Note that all Strings in ColorantParams etc. use Color/@Name, NOT Color/@ActualColorName");
		colParams.setSeparations(new VString("Spot1,BlackText", ","));
		colControl
				.setXMLComment("Simple colorantcontrol from MIS: CMYK + 1 spot+ 1 black text version; knows no more");
		d.write2File(sm_dirTestDataTemp + "ActualColorName_MIS.jdf", 2, false);

		colControl
				.setXMLComment("ColorantControl after prepress has correctly set ActualColorName based on pdl content");
		JDFColor co = colPool.appendColorWithName("Black", null);
		co
				.setXMLComment("Color that maps the predefined separation Black\n"
						+ "ActualColorName is the new attribute that replaces ExposedMedia/@DescriptiveName as the \"Main\" PDL color");
		co.setCMYK(new JDFCMYKColor(0, 0, 0, 1));
		assertTrue(co.isValid(EnumValidationLevel.Incomplete));
		co.setAttribute("ActualColorName", "Schwarz");

		co = colPool.appendColorWithName("Yellow", null);
		co.setAttribute("ActualColorName", "Gelb");
		co.setCMYK(new JDFCMYKColor(0, 0, 1, 0));

		co = colPool.appendColorWithName("Cyan", null);
		co.setXMLComment("ActualColorName defaults to Name if not specified");
		co.setCMYK(new JDFCMYKColor(1, 0, 0, 0));

		co = colPool.appendColorWithName("Magenta", null);
		co = colPool.appendColorWithName("Spot1", null);
		co.setAttribute("ActualColorName", "Acme Aqua");
		co.setCMYK(new JDFCMYKColor(0.7, 0.2, 0.03, 0.1));

		co = colPool.appendColorWithName("BlackText", null);
		co.setAttribute("ActualColorName", "VersionsText");
		co.setCMYK(new JDFCMYKColor(0, 0, 0, 1));

		d.write2File(sm_dirTestDataTemp + "ActualColorName_Prepress.jdf", 2,
				false);

		JDFColorantAlias ca = colControl.appendColorantAlias();
		ca
				.setXMLComment("ColorantAlias that maps the additional representation (noir) to the predefined separation Black");
		ca.setReplacementColorantName("Black");
		assertTrue(ca.isValid(EnumValidationLevel.Incomplete));
		assertFalse(ca.isValid(EnumValidationLevel.Complete));
		final VString vAlias = new VString("noir schwärz", null);
		ca.setSeparations(vAlias);
		assertTrue(ca.isValid(EnumValidationLevel.Complete));
		byte[] b = vAlias.stringAt(0).getBytes();
		String rawNames = StringUtil.setHexBinaryBytes(b, -1) + " ";
		b = vAlias.stringAt(1).getBytes();
		rawNames += StringUtil.setHexBinaryBytes(b, -1);
		ca.setAttribute("RawNames", rawNames);
		d.write2File(sm_dirTestDataTemp + "ActualColorName_with_CA.jdf", 2,
				false);
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
		KElement kElem = resPool.appendResource(ElementName.COLORANTCONTROL,
				null, null);
		assertTrue(kElem instanceof JDFColorantControl);
		JDFColorantControl cc = ((JDFColorantControl) kElem);
		JDFSeparationList co = cc.appendColorantOrder();
		final VString seps = StringUtil.tokenize("Cyan Magenta Yellow Black",
				" ", false);

		co.setSeparations(seps);
		assertEquals(co.getSeparations(), seps);
		VElement vSepSpec = co.getChildElementVector(
				ElementName.SEPARATIONSPEC, null, null, true, 0, true);
		assertEquals(vSepSpec.size(), seps.size());
		for (int i = 0; i < vSepSpec.size(); i++)
		{
			assertFalse(vSepSpec.item(i).hasAttribute(AttributeName.CLASS));
			assertFalse(vSepSpec.item(i) instanceof JDFResource);
		}

		assertEquals(co.getSeparation(0), "Cyan");
		co.removeSeparation("Magenta");
		assertEquals(co.getSeparation(0), "Cyan");
		assertEquals(co.getSeparation(1), "Yellow");
		assertEquals(co.getSeparation(2), "Black");
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
		KElement kElem = resPool.appendResource(ElementName.COLORANTCONTROL,
				null, null);
		assertTrue(kElem instanceof JDFColorantControl);
		JDFColorantControl cc = ((JDFColorantControl) kElem);
		cc.setProcessColorModel("DeviceCMYK");
		assertTrue(cc.getSeparations().contains("Cyan"));
		cc.appendColorantParams().appendSeparation("Snarf Blue");
		assertTrue(cc.getSeparations().contains("Snarf Blue"));
	}

	// //////////////////////////////////////////////////////////////////////

	public void testColorantParams()
	{
		assertTrue(colParams
				.isValid(KElement.EnumValidationLevel.RecursiveComplete));
	}

	// //////////////////////////////////////////////////////////////////////

	public void testGetDeviceColorantOrderSeparations()
	{
		colParams.appendSeparation("Black");
		assertEquals(colControl.getDeviceColorantOrderSeparations(), colControl
				.getSeparations());
		assertEquals(colControl.getDeviceColorantOrderSeparations().size(), 4);
		colParams.appendSeparation("Green");
		assertEquals(colControl.getDeviceColorantOrderSeparations(), colControl
				.getSeparations());
		assertEquals(colControl.getDeviceColorantOrderSeparations().size(), 5);
		colControl.appendColorantOrder().appendSeparation("Green");
		assertEquals(colControl.getDeviceColorantOrderSeparations().size(), 1);
		assertEquals(
				colControl.getDeviceColorantOrderSeparations().stringAt(0),
				"Green");
	}

	// //////////////////////////////////////////////////////////////////////

	public void testGetColorantOrderSeparations()
	{
		colParams.appendSeparation("Black");
		assertEquals(colControl.getColorantOrderSeparations(), colControl
				.getSeparations());
		assertEquals(colControl.getColorantOrderSeparations().size(), 4);
		colParams.appendSeparation("Green");
		assertEquals(colControl.getColorantOrderSeparations(), colControl
				.getSeparations());
		assertEquals(colControl.getColorantOrderSeparations().size(), 5);
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
		colControl = (JDFColorantControl) rpool.appendResource(
				ElementName.COLORANTCONTROL, EnumResourceClass.Parameter, null);
		colControl.setProcessColorModel("DeviceCMYK");
		colControl.setResStatus(EnumResStatus.Available, true);
		colParams = colControl.appendColorantParams();
		colPool = colControl.appendColorPool();
		colPool.makeRootResource(null, null, true);
	}

}
