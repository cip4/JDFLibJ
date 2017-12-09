/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the
 * following acknowledgment: "This product includes software developed by the The International
 * Cooperation for the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)"
 * Alternately, this acknowledgment may appear in the software itself, if and wherever such
 * third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress" must not be used to endorse or promote products derived from this
 * software without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their
 * name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please
 * consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN
 * PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The
 * International Cooperation for the Integration of Processes in Prepress, Press and Postpress and
 * was originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
/*
 * JDFExampleDocTest.java
 *
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaUnit;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFPartAmount;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.jmf.JDFDeviceFilter;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFQuery;
import org.cip4.jdflib.jmf.JDFRegistration;
import org.cip4.jdflib.jmf.JDFResourceCmdParams;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFDeviceList;
import org.cip4.jdflib.resource.JDFLaminatingParams;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.JDFNotification;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFShapeCuttingParams;
import org.cip4.jdflib.resource.JDFSignature;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.intent.JDFArtDelivery;
import org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent;
import org.cip4.jdflib.resource.intent.JDFLayoutIntent;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFCostCenter;
import org.cip4.jdflib.resource.process.JDFDigitalPrintingParams;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFLayoutElement;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.JDFSurface;
import org.cip4.jdflib.resource.process.postpress.JDFSheet;
import org.cip4.jdflib.span.JDFIntegerSpan;
import org.cip4.jdflib.span.JDFXYPairSpan;
import org.cip4.jdflib.util.MyArgs;
import org.cip4.jdflib.util.StringUtil;
import org.junit.Test;

/**
 * some simple examples
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class JDFExampleDocTest extends JDFTestCaseBase
{
	private JDFDoc m_doc = null;

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentName(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentVersion(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID(null);
		KElement.setLongID(false);
	}

	/**
	 * a simple generic main routine for a dumb console app
	 *
	 * switches: -a: actions to perform - DoAll calls all test programs -i input
	 * JDF file -o output JDF File
	 *
	 */
	@Test
	public void testAll()
	{
		final String[] argV = new String[0];

		// trivial argument handling
		final MyArgs args = new MyArgs(argV, "", "aio", null);

		if (argV.length > 1 && argV[1].equals("-?"))
		{
			// Watch for special case help request
			final String usage = "JDFExample; example usages of the JDF Library\n" + "Arguments: -a: actions to perform \n" + "           -i input JDF file\n"
					+ "           -o output JDF File\n";

			System.out.println(args.usage(usage));
			return;
		}

		// setup indentation for the output - 2 blanks/level

		// get action, input and output settings from the command line
		String action = args.parameter('a');
		if (action == null || action.equals(JDFConstants.EMPTYSTRING))
		{
			action = "DoAll";
		}

		final String strDocType = action.endsWith("Message") ? ElementName.JMF // 1 = JMF document root
				: ElementName.JDF; // 0 = JDF document root

		// use JDFExampleDoc as a container that holds the various example routines
		doExample(strDocType, action, args.parameter('i'), args.parameter('o'));
	}

	/**
	 * dispatcher to the individual example tasks
	 *
	 * @param String
	 *            action the routine to call
	 * @param String
	 *            infile name of the input JDF file to parse
	 * @param String
	 *            outfile name of the output JDF file to write
	 * @return >=0 if successful
	 */
	public int doExample(final String strDocType, final String action, final String inFile, final String outFile)
	{
		JDFElement root = null;
		// parse the input, if it exists
		if (inFile != null && !inFile.equals(JDFConstants.EMPTYSTRING))
		{
			m_doc = JDFDoc.parseFile(sm_dirTestDataTemp + inFile);
			root = (JDFElement) m_doc.getRoot();
		}
		else
		{ // clean root if it was not parsed
			m_doc = new JDFDoc(strDocType);
			root = (JDFElement) m_doc.getRoot();
		}

		int iReturn = -1;
		try
		{
			// select subroutine depending on action
			if (action.equals("CreateSimple"))
			{
				iReturn = createSimple();
			}
			else if (action.equals("CreateBrochure"))
			{
				iReturn = createBrochure();
			}
			else if (action.equals("CreateRIP"))
			{
				iReturn = createRIP();
			}
			else if (action.equals("Reprint"))
			{
				iReturn = reprint();
			}
			else if (action.equals("CreateDigitalPrinting"))
			{
				iReturn = createDigitalPrinting();
			}
			else if (action.equals("CreateNarrowWeb"))
			{
				iReturn = createNarrowWeb();
			}
			else if (action.equals("CreateImposition"))
			{
				iReturn = createImposition();
			}
			else if (action.equals("ParseNodes"))
			{
				iReturn = parseNodes();
			}
			else if (action.equals("DoRunList"))
			{
				iReturn = doRunList();
			}
			else if (action.equals("DoAudit"))
			{
				iReturn = doAudit();
			}
			else if (action.equals("DoValid"))
			{
				iReturn = doValid();
			}
			else if (action.equals("ProcessMessage"))
			{
				iReturn = processMessage(writeMessage());
			}
			else if (action.equals("DoAll"))
			{
				iReturn = doAll();
			}
			else
			{
				// oops we don't know this one
				System.out.println("JDFExampleDoc: unknown action " + action);
				return -1;
			}

		}
		catch (final JDFException e)
		{
			System.out.println("Caught a JDF exception :" + e.getMessage());
		}

		// write to output if requested
		if (outFile != null && !outFile.equals(""))
		{
			// remove whitespace only nodes before writing
			root.eraseEmptyNodes(true);
			//		writeRoundTrip((JDFElement) m_doc.getRoot(), UrlUtil.newExtension(outFile, null));
			m_doc.write2File(sm_dirTestDataTemp + outFile, 0, true);
		}
		else
		{
			// no output file -> Send to console
			System.out.println(">>>>>>>>>>>>>>> output of " + action);
			System.out.println(this);
		}

		return iReturn;
	}

	/**
	 * Example 0: call all other examples
	 */
	private int doAll()
	{
		doExample(ElementName.JDF, "CreateSimple", "", "Simple.jdf");
		doExample(ElementName.JDF, "CreateRIP", "", "RIP.jdf");
		doExample(ElementName.JDF, "CreateBrochure", "", "Brochure.jdf");
		doExample(ElementName.JDF, "ParseNodes", "Brochure.jdf", "");
		doExample(ElementName.JDF, "DoRunList", "", "RunList.jdf");
		doExample(ElementName.JDF, "DoAudit", "RunList.jdf", "RunlistAudit.jdf");
		doExample(ElementName.JDF, "DoValid", "RunList.jdf", "");
		doExample(ElementName.JDF, "CreateImposition", "", "Impose.jdf");
		doExample(ElementName.JDF, "CreateDigitalPrinting", "", "DigitalPrinting.jdf");
		doExample(ElementName.JDF, "CreateNarrowWeb", "", "NarrowWeb.jdf");
		doExample(ElementName.JDF, "Reprint", "RIP.jdf", "Reprint.jdf");

		doExample(ElementName.JMF, "ProcessMessage", "", "ProcessMessage.jdf");

		return 0;
	}

	// the actual examples start here

	/**
	 * Example 1: create an incomplete product node for a simple 8.5 * 11
	 * brochure from scratch
	 */
	private int createSimple()
	{
		// get the JDF document root element
		final JDFNode productNode = (JDFNode) m_doc.getRoot();
		productNode.setType(JDFNode.EnumType.Product.getName(), false);

		// Add an intent resource
		final JDFLayoutIntent layoutIntent = (JDFLayoutIntent) productNode.appendMatchingResource(ElementName.LAYOUTINTENT, JDFNode.EnumProcessUsage.AnyInput, null);

		final JDFComponent c = (JDFComponent) productNode.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c.setComponentType(EnumComponentType.FinalProduct, EnumComponentType.Sheet);
		// set some span elements in the intent resource
		final JDFIntegerSpan pages = layoutIntent.appendPages();
		pages.setPreferred(16);

		final JDFXYPairSpan dimensions = layoutIntent.appendDimensions();
		dimensions.setPreferred(8.5 * 72., 11. * 72.);

		return 1;
	}

	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	/**
	 * Example 2: create a brochure structure with cover and insert from scratch
	 */

	private int createBrochure()
	{
		// set up the root process
		final JDFNode root = (JDFNode) m_doc.getRoot();
		final JDFNode productNode = root;
		productNode.setType(JDFNode.EnumType.Product.getName(), false);

		productNode.setJobPartID("Part1");
		// define the complete output component (false -> Usage=output
		final JDFComponent compBrochure = (JDFComponent) productNode.appendMatchingResource(ElementName.COMPONENT, JDFNode.EnumProcessUsage.AnyOutput, null);
		Vector<EnumComponentType> vComType = new Vector<EnumComponentType>();
		vComType.add(JDFComponent.EnumComponentType.FinalProduct);
		compBrochure.setComponentType(vComType);
		compBrochure.setDescriptiveName("complete 16-page Brochure");
		// set the amount of the output component
		compBrochure.setAmount(10000);

		// you can now add all kinds of attributes to bindingIntent
		productNode.appendMatchingResource(ElementName.BINDINGINTENT, JDFNode.EnumProcessUsage.AnyInput, null);

		// add the component for the cover
		final JDFNode prodCover = productNode.addProduct();
		prodCover.setDescriptiveName("Cover");
		prodCover.setJobPartID("Part2");

		// Add a component for the cover that resides in the ResourceLinkPool of
		// productNode
		final JDFComponent compCover = (JDFComponent) prodCover.appendMatchingResource(ElementName.COMPONENT, JDFNode.EnumProcessUsage.AnyOutput, productNode);

		compCover.setDescriptiveName("Cover Component");

		vComType = new Vector<EnumComponentType>();
		vComType.add(JDFComponent.EnumComponentType.PartialProduct);
		compCover.setComponentType(vComType);

		// Add an ArtDeliveryIntent to define the input files for the cover
		JDFArtDeliveryIntent artDeliveryIntent = (JDFArtDeliveryIntent) prodCover.appendMatchingResource(ElementName.ARTDELIVERYINTENT, JDFNode.EnumProcessUsage.AnyInput, null);

		final JDFArtDelivery artDeliveryCover = artDeliveryIntent.appendArtDelivery();
		artDeliveryCover.setArtDeliveryType("DigitalNetwork");

		final JDFRunList runListCover = artDeliveryCover.appendRunList();
		final JDFFileSpec fileSpec = runListCover.appendLayoutElement().appendFileSpec();
		fileSpec.setURL("File:./cover.pdf");
		fileSpec.setApplication("Acrobat");

		// link the cover to the brochure node as input
		productNode.linkMatchingResource(compCover, JDFNode.EnumProcessUsage.AnyInput, new JDFAttributeMap());

		final JDFLayoutIntent layoutIntent = (JDFLayoutIntent) prodCover.appendMatchingResource(ElementName.LAYOUTINTENT, JDFNode.EnumProcessUsage.AnyInput, null);

		layoutIntent.setNumberUp(new JDFXYPair(1, 1));
		layoutIntent.setSides(JDFLayoutIntent.EnumSides.OneSided);

		prodCover.appendMatchingResource(ElementName.COLORINTENT, JDFNode.EnumProcessUsage.AnyInput, null);

		// Add a component for the insert that resides in the ResourceLinkPool
		// of productNode
		final JDFNode prodInsert = productNode.addProduct();
		prodInsert.setDescriptiveName("Insert");
		prodInsert.setJobPartID("Part3");

		// link the insert to the brochure node as input
		final JDFComponent compInsert = (JDFComponent) prodInsert.appendMatchingResource(ElementName.COMPONENT, JDFNode.EnumProcessUsage.AnyOutput, productNode);

		compInsert.setDescriptiveName("Insert Component");

		vComType = new Vector<EnumComponentType>();
		vComType.add(JDFComponent.EnumComponentType.PartialProduct);
		compInsert.setComponentType(vComType);
		productNode.linkMatchingResource(compInsert, JDFNode.EnumProcessUsage.AnyInput, new JDFAttributeMap());

		// add some intent resources to be filled in later
		final JDFLayoutIntent insertLayoutIntent = (JDFLayoutIntent) prodInsert.appendMatchingResource(ElementName.LAYOUTINTENT, JDFNode.EnumProcessUsage.AnyInput, null);

		insertLayoutIntent.setNumberUp(new JDFXYPair(1, 1));
		insertLayoutIntent.setSides(JDFLayoutIntent.EnumSides.TwoSidedHeadToHead);
		prodInsert.appendMatchingResource(ElementName.COLORINTENT, JDFNode.EnumProcessUsage.AnyInput, null);

		// Add an ArtDeliveryIntent to define the input files for the inser
		artDeliveryIntent = (JDFArtDeliveryIntent) prodInsert.appendMatchingResource(ElementName.ARTDELIVERYINTENT, JDFNode.EnumProcessUsage.AnyInput, null);

		final JDFArtDelivery artDeliveryInsert = artDeliveryIntent.appendArtDelivery();

		artDeliveryInsert.setArtDeliveryType("DigitalNetwork");

		final JDFRunList runListInsert = artDeliveryInsert.appendRunList();
		final JDFFileSpec insertFileSpec = runListInsert.appendLayoutElement().appendFileSpec();
		insertFileSpec.setURL("File:./insert.pdf");
		insertFileSpec.setApplication("Acrobat");

		return 0;
	}

	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	/**
	 * Example 3: parse a JDF or PrintTalk and print the node type + ID
	 *
	 */

	private int parseNodes()
	{
		// set up the root process
		final JDFNode root = (JDFNode) m_doc.getRoot();
		// get a vector of all JDF nodes
		final VElement vNode = root.getvJDFNode(null, null, false);
		// print summary
		System.out.println("ParseNodes found " + vNode.size() + " Nodes");
		// loop over all nodes and print
		for (int i = 0; i < vNode.size(); i++)
		{
			final JDFNode node = (JDFNode) vNode.elementAt(i);
			System.out.println("JFD Node type: " + node.getType() + " ID:" + node.getID());
		}
		System.out.println("end ParseNodes");

		return 0;
	}

	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	/**
	 * Example 4: create an Imposition process node with a runlist the Runlist
	 * has various separated, combined and referenced files
	 */

	private int doRunList()
	{
		// set up the root process node, which is an imposition node
		final JDFNode impositionNode = (JDFNode) m_doc.getRoot();
		impositionNode.setType(JDFNode.EnumType.Imposition.getName(), false);

		// add the runlist
		final JDFRunList runList = (JDFRunList) impositionNode.appendMatchingResource(ElementName.RUNLIST, JDFNode.EnumProcessUsage.Document, null);

		// set Npage to 10
		runList.setNPage(10);

		// separation names
		final String separationList = "Cyan Magenta Yellow Black SpotGreen";

		// runPart is used to reference the partitioned runlist leaves
		JDFRunList runPart = runList.addSepRun(StringUtil.tokenize("Cyan.pdf Magenta.pdf Yellow.pdf Black.pdf Green.pdf", " ", false), StringUtil.tokenize(separationList, " ", false), 0, 1, true);

		// add a JDF Comment
		runPart.insertAt("Comment", 0, "", JDFElement.getSchemaURL(), JDFElement.getSchemaURL()).appendText("Preseparated Runs in multiple files\nAll LayoutElements are inline resources");

		final VString v = new VString();
		v.add("PreSepCMYKG.pdf");
		// add a preseparated run
		runPart = runList.addSepRun(v, StringUtil.tokenize(separationList, " ", false), 0, 2, true);
		runPart.insertAt("Comment", 0, "", JDFElement.getSchemaURL(), JDFElement.getSchemaURL()).appendText("Preseparated Runs in one file CMYKGCMYKG\nLayoutElements are inter-resource links");

		runPart = runList.addSepRun(v, StringUtil.tokenize("Cyan Yellow Black Green", " ", false), 10, 1, true);

		runPart.insertAt("Comment", 0, "", JDFElement.getSchemaURL(), JDFElement.getSchemaURL()).appendText("No Magenta, the missing sep does not exist as a page");

		// add a preseparated run
		runPart = runList.addSepRun(v, StringUtil.tokenize(separationList, " ", false), 14, 2, true);
		runPart.insertAt("Comment", 0, "", JDFElement.getSchemaURL(), JDFElement.getSchemaURL()).appendText("Continuation of Preseparated Runs in one file CMYKGCMYKG - "
				+ "the missing sep of the previous page does not exist as a page");

		v.setElementAt("PreSepCCMMYYKKGG.pdf", 0);

		// add a preseparated run
		runPart = runList.addSepRun(v, StringUtil.tokenize(separationList, " ", false), 0, 2, false);
		runPart.insertAt("Comment", 0, "", JDFElement.getSchemaURL(), JDFElement.getSchemaURL()).appendText("Preseparated Runs in one file CCMMYYKKGG");

		runPart = runList.addRun("Combined.pdf", 0, 1);
		runPart.insertAt("Comment", 0, "", JDFElement.getSchemaURL(), JDFElement.getSchemaURL()).appendText("Combined Runs in one file ");

		return 0;

	}

	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	/**
	 * Example 5: parse a JDF and simulate processing it also add some audit
	 * elements
	 *
	 */

	private int doAudit()
	{
		// set up the root process
		final JDFNode root = (JDFNode) m_doc.getRoot();

		// get the node audit pool
		final JDFAuditPool auditPool = root.getAuditPool();

		// / do something between these calls
		auditPool.setPhase(JDFElement.EnumNodeStatus.Setup, null, null, null);
		auditPool.setPhase(JDFElement.EnumNodeStatus.InProgress, null, null, null);
		auditPool.setPhase(JDFElement.EnumNodeStatus.InProgress, null, null, null);
		auditPool.setPhase(JDFElement.EnumNodeStatus.Cleanup, null, null, null);
		assertEquals(auditPool.getPoolChildren("PhaseTime", null).size(), 3);

		// get the input runlist
		final VElement inOutLinks = root.getResourceLinkPool().getInOutLinks(EnumUsage.Input, false, "RunList", null);
		if (inOutLinks != null)
		{
			final JDFRunList rl = (JDFRunList) inOutLinks.elementAt(0);

			// pretend that cleanup modifies a resource and create a
			// ResourceAudit
			final JDFResourceAudit resourceAudit = auditPool.addResourceAudit("");
			resourceAudit.addNewOldLink(true, rl, EnumUsage.Input);
			resourceAudit.setContentsModified(true);
			auditPool.setPhase(JDFElement.EnumNodeStatus.Completed, null, null, null);

			// / fill the processrun
			auditPool.addProcessRun(JDFElement.EnumNodeStatus.Completed, null, null);
		}

		return 0;
	}

	/**
	 * Example 6: parse a JDF and validate the runlist
	 *
	 */

	private int doValid()
	{
		System.out.println("start DoValid");
		// set up the root process
		final JDFNode root = (JDFNode) m_doc.getRoot();

		final VElement inOutLinks = root.getResourceLinkPool().getInOutLinks(EnumUsage.Input, false, "RunList", null);
		if (inOutLinks != null)
		{
			// get the input runlist
			final JDFRunList rl = (JDFRunList) inOutLinks.elementAt(0);

			// is the Runlist Valid?
			boolean bValid = rl.isValid(EnumValidationLevel.Complete);

			// print out validity information
			System.out.println("DoValid runlist is " + (bValid ? "" : "in") + "valid !");

			// cross check with an artificially invalidated runlist
			rl.setAttribute("InvalidAttribute", "Something really Invalid", "");

			// recheck
			bValid = rl.isValid(EnumValidationLevel.Complete);

			// print out new validity information
			System.out.println("DoValid runlist is " + (bValid ? "" : "in") + "valid !");
			System.out.println("the following attributes are inValid: ");

			// get the vector of invalid attributes
			final VString vInvalidAttributes = rl.getInvalidAttributes(EnumValidationLevel.Complete, true, 9999999);

			// print out the details
			for (int i = 0; i < vInvalidAttributes.size(); i++)
			{
				System.out.println("key: " + vInvalidAttributes.elementAt(i) + " value: " + rl.getAttribute(vInvalidAttributes.elementAt(i), "", ""));
			}

			System.out.println("end DoValid");
		}

		return 0;
	}

	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	/**
	 * Example 7:create an imposition node from from scratch
	 */

	private int createImposition()
	{
		// set up the root process node, which is an imposition node
		final JDFNode impositionNode = (JDFNode) m_doc.getRoot();
		impositionNode.setType(JDFNode.EnumType.Imposition.getName(), false);

		// add the appropriate resources
		final JDFLayout layout = (JDFLayout) impositionNode.appendMatchingResource(ElementName.LAYOUT, JDFNode.EnumProcessUsage.AnyInput, null);
		final JDFSignature signature = layout.appendSignature();
		final JDFSheet sheet = signature.appendSheet();

		// set the surfac contents box
		try
		{
			sheet.setSurfaceContentsBox(new JDFRectangle("0.0 0.0 1842.5197 1417.322800"));
		}
		catch (final DataFormatException e)
		{
			// add exception handling here
		}

		// Add the front Surface
		final JDFSurface surface = sheet.appendFrontSurface();

		// append a markobject and set some default values
		final JDFMarkObject markObject = surface.appendMarkObject();

		try
		{
			markObject.setCTM(new JDFMatrix("1 0 0 1 0 0"));
		}
		catch (final DataFormatException e)
		{
			// add exception handling here
		}
		markObject.setOrd(0);

		// append a contentobject
		final JDFContentObject contentObject = surface.appendContentObject();

		try
		{
			contentObject.setCTM(new JDFMatrix("1 0 0 1 0 0"));
		}
		catch (final DataFormatException e)
		{
			// add exception handling here
		}

		contentObject.setOrd(0);

		// add the document runlist
		final JDFRunList docList = (JDFRunList) impositionNode.appendMatchingResource(ElementName.RUNLIST, JDFNode.EnumProcessUsage.Document, null);
		docList.addRun("Document1.pdf", 0, -1);

		docList.addRun("Document2.pdf", 0, -1);

		// separation names
		final String separationList = "Cyan Magenta Yellow Black";
		// runPart is used to reference the partitioned runlist leaves
		docList.addSepRun(StringUtil.tokenize("Cyan.pdf Magenta.pdf Yellow.pdf Black.pdf", " ", false), StringUtil.tokenize(separationList, " ", false), 0, 1, true);

		// add the marks runlist
		final JDFRunList markList = (JDFRunList) impositionNode.appendMatchingResource(ElementName.RUNLIST, JDFNode.EnumProcessUsage.Marks, null);

		// Append a LayoutElement to the marks runlist
		final JDFLayoutElement markle = markList.appendLayoutElement();
		// Append a FileSpec to the marks LayoutElement
		final JDFFileSpec markfilespec = markle.appendFileSpec();
		// set some parameters
		markfilespec.setURL("marks.pdf");

		// add the output runlist
		final JDFRunList outList = (JDFRunList) impositionNode.appendMatchingResource(ElementName.RUNLIST, JDFNode.EnumProcessUsage.AnyOutput, null);

		// Append a LayoutElement to the output runlist
		final JDFLayoutElement outle = outList.appendLayoutElement();
		// Append a FileSpec to the output LayoutElement
		final JDFFileSpec outfilespec = outle.appendFileSpec();
		// set some parameters
		outfilespec.setURL("output.pdf");

		// append an empty generic ApprovalSuccess
		impositionNode.appendMatchingResource(ElementName.APPROVALSUCCESS, JDFNode.EnumProcessUsage.AnyInput, null);

		return 0;

	}

	/**
	 * Example 7.5: create an combined RIP node for from scratch
	 */
	private int createRIP()
	{

		// get the JDF document root element
		final JDFNode ripNode = (JDFNode) m_doc.getRoot();

		final VString vTypes = new VString();
		vTypes.setAllStrings("Interpreting Rendering ImageSetting", " ");
		ripNode.setType("Combined", true);
		ripNode.setTypes(vTypes);

		// now append the various resources
		// cast to the individual node types and append the appropriate resources
		ripNode.appendMatchingResource(ElementName.INTERPRETINGPARAMS, JDFNode.EnumProcessUsage.AnyInput, null);

		final JDFRunList inRunList = (JDFRunList) ripNode.appendMatchingResource(ElementName.RUNLIST, JDFNode.EnumProcessUsage.AnyInput, null);
		inRunList.addRun("File1.pdf", 0, 1);
		inRunList.addRun("File2.pdf", 0, 1);

		final JDFColorantControl colorantControl = (JDFColorantControl) ripNode.appendMatchingResource(ElementName.COLORANTCONTROL, JDFNode.EnumProcessUsage.AnyInput, null);
		colorantControl.setProcessColorModel("DeviceCMYK");

		ripNode.appendMatchingResource(ElementName.RENDERINGPARAMS, JDFNode.EnumProcessUsage.AnyInput, null);

		ripNode.appendMatchingResource(ElementName.IMAGESETTERPARAMS, JDFNode.EnumProcessUsage.AnyInput, null);

		// set up the media
		final JDFMedia media = (JDFMedia) ripNode.appendMatchingResource(ElementName.MEDIA, JDFNode.EnumProcessUsage.AnyInput, null);
		media.setResStatus(JDFMedia.EnumResStatus.Available, false);
		media.setMediaType(EnumMediaType.Plate);
		final JDFResourceLink mediaLink = ripNode.getLink(media, null);
		mediaLink.setAmount(4 * 4, new JDFAttributeMap()); // 4 seps * 8 pages

		// set up the expose output media
		final JDFExposedMedia exposedMedia = (JDFExposedMedia) ripNode.appendMatchingResource(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.AnyOutput, null);
		exposedMedia.refMedia(media);

		// set up one partition / page
		final VElement vExposedMediaPages = exposedMedia.addPartitions(JDFResource.EnumPartIDKey.RunIndex, new VString("0 1 2 3", null));

		for (int iPage = 0; iPage < vExposedMediaPages.size(); iPage++)
		{
			// now 4 separations per page
			final JDFExposedMedia exposedMediaPage = (JDFExposedMedia) vExposedMediaPages.elementAt(iPage);
			exposedMediaPage.addPartitions(JDFResource.EnumPartIDKey.Separation, new VString("Cyan Magenta Yellow Black", null));
		}

		return 1;
	}

	////////////////////////////////////////////////////////////////////////////
	// /
	/**
	 * Example 7.7: modify a combined RIP node for reprint of one plate from
	 * scratch
	 */
	private int reprint()
	{

		// get the JDF document root element
		final JDFNode ripNode = (JDFNode) m_doc.getRoot();

		final JDFResourceLink inRunListLink = ripNode.getMatchingLink(ElementName.RUNLIST, JDFNode.EnumProcessUsage.AnyInput, 0);

		final JDFResource rr = ripNode.getMatchingResource(ElementName.RUNLIST, JDFNode.EnumProcessUsage.AnyInput, new JDFAttributeMap(), 0);

		int i;
		System.out.println(inRunListLink.getTarget());

		for (i = 0; i < inRunListLink.getTargetVector(-1).size(); i++)
		{
			System.out.println();
			System.out.println(i);
			System.out.println(inRunListLink.getTargetVector(-1).elementAt(i));
		}

		// set up the input resource link for the run list
		final JDFAttributeMap partMap = new JDFAttributeMap();
		partMap.put("Run", "Run0012");
		partMap.put("RunIndex", "3");
		partMap.put("Separation", "Cyan");
		inRunListLink.setPartMap(partMap);
		System.out.println(inRunListLink.getTarget());

		for (i = 0; i < inRunListLink.getTargetVector(-1).size(); i++)
		{
			System.out.println();
			System.out.println(i);
			System.out.println(inRunListLink.getTargetVector(-1).elementAt(i));
		}

		rr.setPartUsage(JDFResource.EnumPartUsage.Implicit);

		System.out.println(inRunListLink.getTarget());

		for (i = 0; i < inRunListLink.getTargetVector(-1).size(); i++)
		{
			System.out.println();
			System.out.println(i);
			System.out.println(inRunListLink.getTargetVector(-1).elementAt(i));
		}

		final JDFResourceLink exposedMediaLink = ripNode.getMatchingLink(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.AnyOutput, 0);
		exposedMediaLink.setPartMap(partMap);

		partMap.clear();
		partMap.put("Separation", "Cyan");

		final JDFResourceLink colorantControlLink = ripNode.getMatchingLink(ElementName.COLORANTCONTROL, JDFNode.EnumProcessUsage.AnyInput, 0);
		colorantControlLink.setPartMap(partMap);

		return 1;
	}

	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	/**
	 * Example 8:write a JMF message from scratch and fill it with various
	 * signals, queries and commands return the message as a string this
	 * simulates the JMF post input within an http server
	 */

	private String writeMessage()
	{
		// set up the root process
		final JDFJMF jmf = m_doc.getJMFRoot();

		jmf.init();
		jmf.setSenderID("MessageSender");
		// append a generic query to root and cast to its type
		final JDFQuery queryKnownDevices = (JDFQuery) jmf.appendMessageElement(JDFMessage.EnumFamily.Query, JDFQuery.EnumType.KnownDevices);
		final JDFDeviceFilter deviceFilter = queryKnownDevices.appendDeviceFilter();
		final JDFDevice device = deviceFilter.appendDevice();
		device.setDeviceID("DeviceID1");

		// serialize this document to a string
		return m_doc.write2String(0);

	}

	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	/**
	 * Example 9:read the JMF message from example 8 and create the appropriate
	 * responses and acknowledges
	 */

	private int processMessage(final String inputMessage)
	{
		final JDFParser p = new JDFParser();

		// parse the message into a document
		final JDFDoc inMessageDoc = p.parseString(inputMessage);

		// get the input document root element
		final JDFJMF jmfIn = inMessageDoc.getJMFRoot();
		// get the output document root element
		final JDFJMF jmfOut = inMessageDoc.getJMFRoot();
		// get all queries as a vector of elements
		final VElement vMessages = jmfIn.getMessageVector(JDFMessage.EnumFamily.Query, null);

		// loop over all queries
		for (int i = 0; i < vMessages.size(); i++)
		{
			final JDFQuery query = (JDFQuery) vMessages.elementAt(i);
			// append a response to this docoment
			final JDFResponse response = (JDFResponse) jmfOut.appendMessageElement(JDFMessage.EnumFamily.Response, null);
			// set the appropriate query information to the response
			response.setQuery(query);
			final String typ = query.getType();

			// fill in the response according to query type. -> example only
			if (typ.equals("KnownDevices"))
			{
				// read in the data of the query
				final JDFDeviceFilter deviceFilter = query.getDeviceFilter(0);
				final JDFDevice deviceIn = deviceFilter.getDevice(0);

				// modify the response
				final JDFDeviceList deviceOutList = response.appendDeviceList();
				final JDFDeviceInfo deviceInfo = deviceOutList.appendDeviceInfo();
				final JDFDevice deviceOut = deviceInfo.appendDevice();
				deviceOut.setDeviceID(deviceIn.getDeviceID());
				deviceOut.setDeviceType("Coffee Machine");
				final JDFCostCenter cs = deviceOut.appendCostCenter();
				cs.setCostCenterID("1234");
				cs.setName("Java Bean Cost Center");
				// ...
			}
			else
			{
				// query not implemented!
				response.setAttribute("ReturnCode", 5, "");
				final JDFNotification note = response.appendNotification();
				note.setClass(JDFNotification.EnumClass.Error);
				response.setReturnCode(5);
			}
		}

		System.out.println(inMessageDoc.write2String(0));
		return 0;

	}

	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	/**
	 * Example 10:create a DigitalPrinting node from from scratch
	 */

	private int createDigitalPrinting()
	{
		// set up the root process node, which is an imposition node
		final JDFNode printNode = (JDFNode) m_doc.getRoot();
		printNode.setType(JDFNode.EnumType.DigitalPrinting.getName(), false);

		// add the appropriate resources
		final JDFMedia media = (JDFMedia) printNode.appendMatchingResource(ElementName.MEDIA, JDFNode.EnumProcessUsage.AnyInput, null);

		// partition for Runindex=cover and RunIndex=insert
		media.addPartitions(JDFResource.EnumPartIDKey.RunIndex, new VString("0 -1,1~-2", ","));

		final JDFResourceLink mediaLink = printNode.getMatchingLink(ElementName.MEDIA, JDFNode.EnumProcessUsage.AnyInput, 0);

		final JDFAmountPool aPool = mediaLink.appendAmountPool();

		final JDFAttributeMap mapParts = new JDFAttributeMap();
		mapParts.put(JDFResource.EnumPartIDKey.RunIndex.getName(), "0 -1");

		JDFPartAmount partAmount = aPool.appendPartAmount(mapParts);
		partAmount.setOrientation(JDFResourceLink.EnumOrientation.Flip180);
		mapParts.clear();

		mapParts.put(JDFResource.EnumPartIDKey.RunIndex.getName(), "1~-2");

		partAmount = aPool.appendPartAmount(mapParts);
		partAmount.setOrientation(JDFResourceLink.EnumOrientation.Rotate0);

		// get the MediaLink and set the attributes
		final JDFDigitalPrintingParams dpp = (JDFDigitalPrintingParams) printNode.appendMatchingResource(ElementName.DIGITALPRINTINGPARAMS, JDFNode.EnumProcessUsage.AnyInput, null);

		dpp.addPartitions(JDFResource.EnumPartIDKey.RunIndex, new VString("0 -1,1~-2", ","));

		final JDFAttributeMap m = new JDFAttributeMap(JDFResource.EnumPartIDKey.RunIndex.getName(), "0 -1");
		JDFDigitalPrintingParams dppLeaf = (JDFDigitalPrintingParams) dpp.getPartition(m, EnumPartUsage.Implicit);

		m.put(JDFResource.EnumPartIDKey.RunIndex.getName(), dppLeaf.refElement(media.getPartition(m, EnumPartUsage.Implicit)).getrRef());

		m.put(JDFResource.EnumPartIDKey.RunIndex.getName(), "1 ~ -2");
		dppLeaf = (JDFDigitalPrintingParams) dpp.getPartition(m, EnumPartUsage.Implicit);

		dppLeaf.refElement(media.getPartition(m, EnumPartUsage.Implicit));

		return 0;

	}

	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	////////////////////////////////////////////////////////////////////////////
	// /
	/**
	 * Example 10:create a narrow web node from from scratch
	 */

	private int createNarrowWeb()
	{
		// set up the root process node, which is an imposition node
		final JDFNode printNode = (JDFNode) m_doc.getRoot();
		printNode.setType(JDFNode.EnumType.Combined.getName(), false);
		final VString types = new VString();
		types.add("ConventionalPrinting");
		types.add("ConventionalPrinting");
		types.add("Laminating");
		types.add("ShapeCutting");

		printNode.setTypes(types);
		// add the appropriate resources
		final JDFMedia media = (JDFMedia) printNode.appendMatchingResource(ElementName.MEDIA, JDFNode.EnumProcessUsage.AnyInput, null);
		media.setDescriptiveName("Input Media");
		media.setMediaType(EnumMediaType.Paper);
		media.setMediaUnit(EnumMediaUnit.Roll);

		final JDFComponent outComp = (JDFComponent) printNode.appendMatchingResource(ElementName.COMPONENT, JDFNode.EnumProcessUsage.AnyOutput, null);

		final JDFConventionalPrintingParams cpOffset = (JDFConventionalPrintingParams) printNode.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, JDFNode.EnumProcessUsage.AnyInput, null);
		cpOffset.setDescriptiveName("Offset parameters");

		final JDFExposedMedia xmOffset = (JDFExposedMedia) printNode.appendMatchingResource(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.Plate, null);
		xmOffset.setDescriptiveName("Offset Plate");
		xmOffset.addPartition(EnumPartIDKey.Side, "Front");
		xmOffset.addPartition(EnumPartIDKey.Side, "Back");

		final JDFConventionalPrintingParams cpFlexo = (JDFConventionalPrintingParams) printNode.appendMatchingResource(ElementName.CONVENTIONALPRINTINGPARAMS, JDFNode.EnumProcessUsage.AnyInput, null);
		cpFlexo.setDescriptiveName("Flexo parameters");

		final JDFExposedMedia xmFlexo = (JDFExposedMedia) printNode.appendMatchingResource(ElementName.EXPOSEDMEDIA, JDFNode.EnumProcessUsage.Plate, null);
		xmFlexo.setDescriptiveName("Flexo Plate");

		final JDFLaminatingParams lamParm = (JDFLaminatingParams) printNode.appendMatchingResource(ElementName.LAMINATINGPARAMS, JDFNode.EnumProcessUsage.AnyInput, null);
		lamParm.setDescriptiveName("laminating parameters");

		final JDFShapeCuttingParams cutParm = (JDFShapeCuttingParams) printNode.appendMatchingResource(ElementName.SHAPECUTTINGPARAMS, JDFNode.EnumProcessUsage.AnyInput, null);
		cutParm.setDescriptiveName("cutting parameters");

		// fix resource links
		JDFResourceLink rl = printNode.getLink(cpOffset, null);
		final JDFIntegerList il = new JDFIntegerList();
		il.add(0);
		rl.setCombinedProcessIndex(il);

		rl = printNode.getLink(xmOffset, null);
		rl.setCombinedProcessIndex(il);

		il.clear();
		il.add(1);
		rl = printNode.getLink(cpFlexo, null);
		rl.setCombinedProcessIndex(il);

		rl = printNode.getLink(xmFlexo, null);
		rl.setCombinedProcessIndex(il);

		rl = printNode.getLink(outComp, null);
		rl.setAmount(500, null);

		return 0;

	}

	/**
	 * create a simple stripping node for 2 user jobs in a gang job
	 *
	 */
	@Test
	public void testDigitalDelivery()
	{
		KElement.setLongID(false);

		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode pgNode = d.getJDFRoot();
		pgNode.setType(EnumType.ProcessGroup);
		final JDFNode delNode = creatDeliveryNode(pgNode);
		final JDFRunList rl = (JDFRunList) delNode.getMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null, 0);

		final JDFNode impNode = createImposition(pgNode);
		impNode.linkMatchingResource(rl, EnumProcessUsage.Document, null);

		d.write2File(sm_dirTestDataTemp + File.separator + "DigitalDelivery.jdf", 2, false);

	}

	/**
	 *
	 */
	@Test
	public void testComment()
	{
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc(ElementName.JDF);
		final JDFNode pgNode = d.getJDFRoot();
		pgNode.setType(EnumType.ManualLabor);
		final JDFComment comment = pgNode.appendComment();
		comment.setText("Multiline text\n\twith white space\n\n\nand empty lines");
		comment.setName("Instruction");
		setSnippet(comment, true);
		writeTest(pgNode, "subelements/comment.jdf", true, "Comment");

	}

	/**
	 * create a simple stripping node for 2 user jobs in a gang job
	 *
	 */
	@Test
	public void testGangDigitalDelivery()
	{
		KElement.setLongID(false);

		final JDFNode delNode1 = creatDeliveryNode(null);
		delNode1.setJobID("Del1ID");
		final JDFDoc delDoc1 = new JDFDoc(delNode1.getOwnerDocument());
		final JDFRunList rl1 = (JDFRunList) delNode1.getMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null, 0);
		rl1.setProductID("RL1ID");
		final JDFRegistration reg = (JDFRegistration) delNode1.getNodeInfo().getJMF(0).getMessageElement(EnumFamily.Registration, JDFMessage.EnumType.Resource, 0);
		final JDFResourceCmdParams rc = reg.getResourceCmdParams(0);
		rc.setProductID("RL1ID");
		reg.appendSubscription();

		final JDFNode delNode2 = creatDeliveryNode(null);
		final JDFDoc delDoc2 = new JDFDoc(delNode2.getOwnerDocument());
		final JDFRunList rl2 = (JDFRunList) delNode2.getMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null, 0);
		rl2.setProductID("RL2ID");
		final JDFRegistration reg2 = (JDFRegistration) delNode2.getNodeInfo().getJMF(0).getMessageElement(EnumFamily.Registration, JDFMessage.EnumType.Resource, 0);
		final JDFResourceCmdParams rc2 = reg2.getResourceCmdParams(0);
		reg2.appendSubscription();
		delNode2.setJobID("Del2ID");
		rc2.setProductID("RL2ID");

		final JDFDoc dGang = new JDFDoc("JDF");
		final JDFNode pgNode = dGang.getJDFRoot();
		pgNode.setJobID("GangJobID");
		pgNode.setType(EnumType.ProcessGroup);
		final JDFNodeInfo pgNI = pgNode.getCreateNodeInfo();

		final JDFNode combNode = createCombine(pgNode);
		final JDFRunList rl = (JDFRunList) combNode.getMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null, 0);

		final JDFNode impNode = createImposition(pgNode);
		impNode.linkMatchingResource(rl, EnumProcessUsage.Document, null);
		pgNode.setTypes(new VString("Combine,Imposition", ","));

		pgNI.appendGeneralID("GangChildID", delNode1.getJobID(true));
		pgNI.appendGeneralID("GangChildID", delNode2.getJobID(true));

		delDoc1.write2File(sm_dirTestDataTemp + File.separator + "GangDelivery1.jdf", 2, false);
		delDoc2.write2File(sm_dirTestDataTemp + File.separator + "GangDelivery2.jdf", 2, false);
		dGang.write2File(sm_dirTestDataTemp + File.separator + "GangImpose.jdf", 2, false);

	}

	// /////////////////////////////////////////////////

	private JDFNode createImposition(final JDFNode pgNode)
	{
		final JDFNode impNode = ceateNewNode(EnumType.Imposition, pgNode);
		impNode.appendMatchingResource(ElementName.LAYOUT, EnumProcessUsage.AnyInput, null);
		return impNode;
	}

	// /////////////////////////////////////////////////

	private JDFNode createCombine(final JDFNode pgNode)
	{
		final JDFNode combNode = ceateNewNode(EnumType.Combine, pgNode);
		JDFRunList rl = (JDFRunList) combNode.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
		rl.setProductID("RL1ID");

		rl = (JDFRunList) combNode.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyInput, null);
		rl.setProductID("RL2ID");

		rl = (JDFRunList) combNode.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, pgNode);

		JDFRunList rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.RunSet, "RL1ID");
		rlp.setDescriptiveName("Partition for first input");

		rlp = (JDFRunList) rl.addPartition(EnumPartIDKey.RunSet, "RL2ID");
		rlp.setDescriptiveName("Partition for second input");

		rl.setResStatus(EnumResStatus.Unavailable, true);

		return combNode;
	}

	// /////////////////////////////////////////////////

	private JDFNode creatDeliveryNode(final JDFNode pgNode)
	{
		JDFNode delNode = null;
		delNode = ceateNewNode(EnumType.DigitalDelivery, pgNode);
		delNode.appendMatchingResource(ElementName.DIGITALDELIVERYPARAMS, EnumProcessUsage.AnyInput, null);
		final JDFRunList rl = (JDFRunList) delNode.appendMatchingResource(ElementName.RUNLIST, EnumProcessUsage.AnyOutput, null);
		rl.setResStatus(EnumResStatus.Unavailable, false);
		final JDFNodeInfo ni = delNode.getCreateNodeInfo();
		final JDFJMF jmf = ni.appendJMF();
		final JDFRegistration reg = (JDFRegistration) jmf.appendMessageElement(EnumFamily.Registration, JDFMessage.EnumType.Resource);
		final JDFResourceCmdParams resCmd = reg.appendResourceCmdParams();
		resCmd.setResourceName(ElementName.RUNLIST);
		resCmd.setJobID("GangJobID");
		return delNode;
	}

	// /////////////////////////////////////////////////

	private JDFNode ceateNewNode(final EnumType enumType, final JDFNode parentNode)
	{
		JDFNode delNode;
		if (parentNode == null)
		{
			final JDFDoc d = new JDFDoc("JDF");
			delNode = d.getJDFRoot();
			delNode.setType(enumType);
		}
		else
		{
			delNode = parentNode.addJDFNode(enumType);
		}
		return delNode;
	}

	/**
	 * create a simple stripping node for 2 user jobs in a gang job
	 *
	 * @return
	 */
	@Test
	public void testMISGang() throws Exception
	{
		KElement.setLongID(false);
		// set up the root process node, which is an imposition node
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode misNode = d.getJDFRoot();
		misNode.setJobID("GangJob");
		misNode.setType(EnumType.Stripping);
		final JDFAssembly as1 = (JDFAssembly) misNode.appendMatchingResource(ElementName.ASSEMBLY, EnumProcessUsage.AnyInput, null);
		as1.setAssemblyIDs(new VString("As1", null));
		as1.setOrder(EnumOrder.Collecting);
		as1.setJobID("job1");
		final JDFAssembly as2 = (JDFAssembly) misNode.appendMatchingResource(ElementName.ASSEMBLY, EnumProcessUsage.AnyInput, null);
		as2.setAssemblyIDs(new VString("As2", null));
		as2.setOrder(EnumOrder.Collecting);
		as2.setJobID("job2");
		final JDFStrippingParams sp = (JDFStrippingParams) misNode.appendMatchingResource(ElementName.STRIPPINGPARAMS, EnumProcessUsage.AnyInput, null);
		final JDFStrippingParams sp1 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.BinderySignatureName, "BS1");
		sp1.setAssemblyIDs(new VString("As1", null));
		sp1.setJobID("job1");

		JDFBinderySignature bs1 = sp1.appendBinderySignature();
		bs1 = (JDFBinderySignature) bs1.makeRootResource(null, null, true);
		final JDFPosition pos1 = sp1.appendPosition();
		try
		{
			pos1.setRelativeBox(new JDFRectangle("0 0 0.5 1"));
		}
		catch (final DataFormatException e)
		{
			fail("rectangle");
		}

		final JDFStrippingParams sp2 = (JDFStrippingParams) sp.addPartition(EnumPartIDKey.BinderySignatureName, "BS2");
		sp2.setAssemblyIDs(new VString("As2", null));
		sp2.setJobID("job2");

		sp2.refElement(bs1);
		final JDFPosition pos2 = sp2.appendPosition();
		try
		{
			pos2.setRelativeBox(new JDFRectangle("0.5 0 1 1"));
		}
		catch (final DataFormatException e)
		{
			fail("rectangle");
		}
		misNode.addResource(ElementName.LAYOUT, EnumUsage.Output);
		//TODO		writeRoundTrip(misNode, "StrippingGang");
	}

}
