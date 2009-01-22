/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFShape;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFDieLayout;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StatusCounter;
import org.cip4.jdflib.util.UrlUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * tests for CAD production 
 */
public class CADTest extends JDFTestCaseBase
{
	/**
	 * @see junit.framework.TestCase#toString()
	 * @return the String
	 */
	@Override
	public String toString()
	{
		String s = super.toString();
		if (n != null)
			s += "\n" + n.toString();
		return s;
	}

	private JDFNode n;
	private JDFDoc d;

	/**
	 * tests the creation of the initial shapedefproduction (one up) process
	 * @throws Exception
	 */
	public void testStatusJMF()
	{
		createShapeDefProduction();
		StatusCounter sc = new StatusCounter(null, null, null);
		sc.setDeviceID("EngView");
		JDFDoc dJMF = sc.getDocJMFPhaseTime();
		dJMF.write2File(sm_dirTestDataTemp + File.separator + "Idle.jmf", 2, false);
		sc.setActiveNode(n, null, null);
		sc.setPhase(EnumNodeStatus.InProgress, "Processing", EnumDeviceStatus.Running, "inUse");
		dJMF = sc.getDocJMFPhaseTime();
		dJMF.write2File(sm_dirTestDataTemp + File.separator + "Running.jmf", 2, false);
		sc.setPhase(EnumNodeStatus.Completed, null, EnumDeviceStatus.Idle, null);
		dJMF = sc.getDocJMFPhaseTime();
		dJMF.write2File(sm_dirTestDataTemp + File.separator + "Completed.jmf", 2, false);
		sc.setActiveNode(null, null, null);

		d.write2File(sm_dirTestDataTemp + File.separator + "CompletedShapeDef.jdf", 2, false);

	}

	// /////////////////////////////////////////////////////////////////
	/**
	 * tests the creation of the initial shapedefproduction (one up) process
	 * @throws Exception
	 */
	public void testShapeDefProduction()
	{
		createShapeDefProduction();

		d.write2File(sm_dirTestDataTemp + File.separator + "CAD_ShapeDefProduction.jdf", 2, false);
	}

	/**
	 * 
	 */
	private void createShapeDefProduction()
	{
		n.setType("ShapeDefProduction", false);
		n.setDescriptiveName("This process describes one up cad production");
		JDFResource sdpp = n.addResource("ShapeDefProductionParams", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);

		int nOptions = 3;
		JDFShape shape = new JDFShape(10 * 72 / 2.54, 20 * 72 / 2.54, 30 * 72 / 2.54);
		JDFElement shapeTemplate = (JDFElement) sdpp.appendElement("ShapeTemplate");
		shapeTemplate.setAttribute("InnerDimensions", shape.toString());
		shapeTemplate.setDescriptiveName("10 * 20 * 30 cm shape");
		shapeTemplate.setAttribute("Standard", "ECMA");
		for (int i = 1; i < 4; i++)
		{
			JDFGeneralID genID = (JDFGeneralID) shapeTemplate.appendElement(ElementName.GENERALID);
			genID.setIDUsage("EcmaParameter" + i);
			genID.setIDValue("123" + i);
			genID.setDescriptiveName("Name and Value of a parameter");
		}
		JDFFileSpec filespec = (JDFFileSpec) shapeTemplate.appendElement(ElementName.FILESPEC);
		filespec.setURL(UrlUtil.fileToUrl(new File("\\\\host\\share\\dir1\\dir2\\File with €.cff2"), false));
		filespec.setDescriptiveName("This is the optional location of the input cff2 (or evd), Note the escaping of Blanks. - chars > 127 may but need not be encoded as utf-8");

		for (int i = 0; i < nOptions; i++)
			createShapeDef(EnumUsage.Input, i, nOptions);
	}

	/**
	 * tests the creation of the initial shapedefproduction (one up) process
	 * @throws Exception
	 */
	public void testDieLayoutProduction()
	{
		n.setType("DieLayoutProduction", false);
		n.setDescriptiveName("This process describes placement of one-up shapes on a sheet");

		createShapeDef(EnumUsage.Input, 0, 1);

		JDFResource dlpp = n.addResource("DieLayoutProductionParams", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);

		dlpp.setAttribute("Esimate", true, null);
		dlpp.setAttribute("Anchor", "Center");

		JDFElement convertingConfig = (JDFElement) dlpp.appendElement("ConvertingConfig");
		JDFNumberRange range = new JDFNumberRange();
		range.setLeft(90 * 72 / 2.54);
		range.setRight(110 * 72 / 2.54);
		convertingConfig.setAttribute("SheetWidth", range, null);
		range.setLeft(60 * 72 / 2.54);
		range.setRight(80 * 72 / 2.54);
		convertingConfig.setAttribute("SheetHeight", range, null);
		convertingConfig.setDescriptiveName("range of height and width - may also add margins");

		JDFElement repeatDesc = (JDFElement) dlpp.appendElement("RepeatDesc");
		repeatDesc.setDescriptiveName("details the step and repeat parameters");
		repeatDesc.setAttribute("AllowedRotate", "None");
		repeatDesc.setAttribute("LayoutStyle", "StraightNest");

		JDFDieLayout dl = createDieLayout(EnumUsage.Output, "\\\\host\\share\\dir1\\dir2\\dieLayout.cff2");
		dl.setDescriptiveName("the abstract die layout ");

		d.write2File(sm_dirTestDataTemp + File.separator + "CAD_DieLayoutProduction.jdf", 2, false);
	}

	/**
	 * tests the creation of the initial shapedefproduction (one up) process
	 * @throws Exception
	 */
	public void testDieDesign()
	{
		createDieDesign();
		d.write2File(sm_dirTestDataTemp + File.separator + "CAD_DieDesign_out.jdf", 2, false);
	}

	/**
	 * 
	 */
	private void createDieDesign()
	{
		n.setType("DieDesign", false);
		n.setDescriptiveName("This process describes detailing the various die cut toolsets from a general description");
		JDFComment comment = n.appendComment();
		comment.setName("Description");
		comment.setText("Multi line descriptions of the overall process are placed here\nLine feeds must be preserved for display");

		JDFDieLayout dl = createDieLayout(EnumUsage.Input, "\\\\host\\share\\dir1\\dir2\\dieLayout.cff2");
		dl.setDescriptiveName("the abstract die layout ");
		dl = createDieLayout(EnumUsage.Input, "\\\\host\\share\\dir1\\dir2\\dieLayoutUpper.cff2");
		dl.setDescriptiveName("the upper die layout ");
		dl = createDieLayout(EnumUsage.Input, "\\\\host\\share\\dir1\\dir2\\dieLayoutLower.cff2");
		dl.setDescriptiveName("the lower die layout ");

		d.write2File(sm_dirTestDataTemp + File.separator + "CAD_DieDesign.jdf", 2, false);
		setAudits();
	}

	/**
	 * @param inOut 
	 * @return the dielayout
	 * 
	 */
	private JDFDieLayout createDieLayout(EnumUsage inOut, String fileName)
	{
		JDFDieLayout dieLayout = (JDFDieLayout) n.addResource("DieLayout", EnumResourceClass.Parameter, inOut, null, null, null, null);
		dieLayout.setAttribute("Waste", "22");
		JDFFileSpec fsDie = dieLayout.appendFileSpec();
		fsDie.makeRootResource(null, null, true);
		fsDie.setURL(UrlUtil.fileToUrl(new File(fileName), false));
		return dieLayout;
	}

	/**
	 * note that this may be calle multiple times to describe multiple one-ups per sheet
	 * @param inOut input or output
	 * @param option 
	 * @param nOptions 
	 * @return 
	 */
	private JDFResource createShapeDef(EnumUsage inOut, int option, int nOptions)
	{
		// now the output
		JDFResource shapeDefRoot;
		if (option == 0)
			shapeDefRoot = n.addResource("ShapeDef", EnumResourceClass.Parameter, inOut, null, null, null, null);
		else
			shapeDefRoot = n.getResource("ShapeDef", inOut, 0);

		JDFResource shapeDef;
		if (nOptions == 1)
		{
			shapeDef = shapeDefRoot;
		}
		else
		{
			shapeDef = shapeDefRoot.addPartition(EnumPartIDKey.Option, "Option" + option);
		}
		JDFFileSpec filespecOut = (JDFFileSpec) shapeDef.appendElement(ElementName.FILESPEC);
		filespecOut.setURL(UrlUtil.fileToUrl(new File("\\\\host\\share\\dir1\\dir2\\OutFile with €.cff2"), false));
		filespecOut.setDescriptiveName("This is the requested location of the output cff2 (or evd)");
		shapeDef.setDescriptiveName("Additional parameters may be filled by CAD - note also that this shapeDef will be the input of the DieLayoutProduction process");
		shapeDef.setAttribute("Area", "0.3");
		shapeDef.setAttribute("GrainDirection", "XDirection");
		shapeDef.setAttribute("FluteDirection", "YDirection");
		return shapeDef;
	}

	private void setAudits()
	{
		JDFAuditPool ap = n.getAuditPool();
		JDFPhaseTime pt = ap.addPhaseTime(EnumNodeStatus.InProgress, "Whoever", null);
		JDFDate dat = new JDFDate();
		dat.addOffset(0, 0, 5, 0);
		pt.setStart(dat);
		dat.addOffset(0, 0, 3, 0);
		pt.setEnd(dat);
		pt.setDescriptiveName("3 hours work ");
	}

	/**
	 * 
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		JDFElement.setLongID(false);
		d = new JDFDoc("JDF");
		prepareCustomerInfo(d);
		prepareNodeInfo(d);
		n = d.getJDFRoot();
	}

	// /////////////////////////////////////////////////////////////////

	// /////////////////////////////////////////////////////////////////

}
