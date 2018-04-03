/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 }
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
//Titel:        check a jdf file, if is valid
//Version:
//Copyright:    Copyright (c) 2002
//Autor:        Joerg Gonnermann, Dietrich Mucha
//Firma:        Heidelberger Druckmaschinen AG
package org.cip4.jdflib.validate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.apache.commons.lang.ArrayUtils;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFParserFactory;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.XMLErrorHandler;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.MimeUtil;
import org.cip4.jdflib.util.MyArgs;
import org.cip4.jdflib.util.StreamUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 *
 * this is the non-commandline part of the original checkJDF and used both by the JDF Editor and checkJDF
 *
 * Refactored JDFValidator to be non-static in order to make it thread compatible. Previously, only one thread at a time could call JDFValidator from within the
 * same JVM. Now an instance of JDFValidator and the the method validate should be called. JDFValidator can still be called from the command line in the same
 * way as before.
 *
 * TODO Break out validation error handling logging so that new error handlers can easily be registered. For example, there should be an error handler for
 * logging to the XML log file and an error handler for logging to the <code>sysOut</code>. Perhaps <code>org.xml.sax.ErrorHandler</code> could be used?
 *
 * @author Claes Buckwalter (clabu@itn.liu.se)
 * @version 2008-01-02
 */
public class JDFValidator
{
	VString foundNameSpaces = new VString();
	VString vID = new VString();
	/**
	 *
	 */
	public VString vBadID = new VString();
	/**
	 *
	 */
	public VString vMultiID = null;
	VString vBadJobPartID = new VString();
	VString vJobPartID = new VString();
	VElement vResources = new VElement();
	VElement vLinkedResources = new VElement();
	VElement vBadResourceLinks = new VElement();
	VString vSeparations = new VString();
	VString vSeparations2 = new VString();
	VString vColorPoolSeparations = new VString();
	//	protected XMLDoc pOut = new XMLDoc("CheckOutput", "http://www.cip4.org/validate");
	protected XMLDoc pOut;
	// list of gray boxes that are ignored when checking types for extensions
	static final String[] aGBList = { "ImpositionRIPing", "PlateMaking", "ProofAndPlateMaking", "ImpositionProofing", "PageProofing", "RIPing", "PrePressPreparation",
			"ImpositionPreparation", "ProofImaging" };

	JDFDoc theDoc = null;
	protected String translation = null;
	private boolean bTryFormats = false;
	private ICheckValidatorFactory validatorFactory = null;

	public boolean bTiming = false;
	protected boolean bWarning = false;
	public boolean bQuiet = true;
	protected boolean bPrintNameSpace = true;
	public boolean bValidate = true;
	/**
	 * if true, warn on URL attributes that point to Nirvana
	 */
	public boolean bWarnDanglingURL = false;
	public EnumValidationLevel level = EnumValidationLevel.Incomplete;
	public VString allFiles = null;

	public String proxyHost = null;
	public String proxyPort = null;

	public String schemaLocation;
	public String xmlOutputName = null;
	public String xslStyleSheet = null;

	public String devCapFile = null;
	public EnumFitsValue testlists = EnumFitsValue.Allowed;
	public boolean bMultiID = false;
	private boolean inOutputLoop = false;

	final protected static String version = "JDFValidator: JDF validator; -- (c) 2001-2015 CIP4" + "\nJDF 1.5 compatible version\n" + "\nCode based on schema JDF_1.5.xsd\n"
			+ "Build version " + JDFAudit.software();

	/**
	 *
	 */
	public JDFValidator()
	{
		super();
		schemaLocation = null;
		pOut = new XMLDoc("CheckOutput", null);
		pOut.getMemberDocument().setIgnoreNSDefault(true);
		pOut.getMemberDocument().setStrictNSCheck(false);
		pOut.getRoot().setAttribute("Version", version);
	}

	/**
	 * @param checkOut
	 * @return
	 */
	public static String toMessageString(final KElement checkOut)
	{
		if (checkOut == null)
		{
			return null;
		}
		return checkOut.getAttribute("Message", null, null);
	}

	/**
	 *
	 * @param reportElem
	 * @param type
	 * @param message
	 * @param indent
	 */
	private void setErrorType(final KElement reportElem, final String type, final String message, final int indent)
	{
		if (reportElem == null)
		{
			return;
		}
		reportElem.setAttribute("IsValid", false, null);
		reportElem.setAttribute("ErrorType", type, null);
		reportElem.setAttribute("Message", message, null);
		if (indent > 0)
		{
			sysOut.println(indent(indent) + message);
		}
	}

	private void setErrorType(final KElement reportElem, final String type, final String message)
	{
		setErrorType(reportElem, type, message, -1);
	}

	/**
	 * set the JDFDoc (JDF or JMF) to set
	 *
	 * @param d the JDFDoc to set this to
	 */
	public void setDoc(final JDFDoc d)
	{
		theDoc = d;
	}

	protected MySysOut sysOut = new MySysOut();

	/**
	 * if bI
	 *
	 * @param bIgnore
	 */
	public void setIgnorePrivate(final boolean bIgnore)
	{
		bPrintNameSpace = !bIgnore;
	}

	/**
	 * sets the System.out print on or off
	 *
	 * @param b
	 */
	public void setPrint(final boolean b)
	{
		sysOut.setPrint(b);
	}

	/**
	 * simple helper to create an indented string with leading blanks
	 *
	 * @param indent number of leadingbla
	 */
	private String indent(final int indent)
	{
		String s = "";
		for (int i = 0; i < indent; i++)
		{
			s += " ";
		}
		return s;
	}

	/**
	 * the subroutine to print out invalid parts of a jdf.
	 *
	 * @param JDFElement jdfElement - the element to test
	 * @param bool bQuiet - flag what to do with valid elements; quiet if true
	 * @param indent - indent for println() to console window
	 * @param xmlParent - root for XML output (if '-x' set)
	 * @param bIsNodeRoot - is the jdfElement a root of a whole testNode or not
	 */

	private void printBad(final KElement kElement, final int indent, final KElement xmlParent, final boolean bIsNodeRoot)
	{
		final String id = kElement.getAttribute(AttributeName.ID, null, "");
		final String pref = kElement.getPrefix();
		final String elmName = kElement.getNodeName();
		final String nsURI = kElement.getNamespaceURI();

		final KElement testElement = getTestElement(kElement, xmlParent);

		if (kElement instanceof JDFNode)
		{
			final JDFNode node = (JDFNode) kElement;
			printNode(node, indent, testElement);

			if (bIsNodeRoot) // this will be executed only once - test of the whole Node
			{
				printNodeRoot(node, xmlParent);
			}
		}

		final boolean isJDFNS = JDFElement.isInJDFNameSpaceStatic(kElement);
		final boolean bTypo = false;
		if (!isJDFNS)
		{
			printNonNamespace(kElement, indent, xmlParent, pref, elmName, nsURI, testElement, isJDFNS, bTypo);
			return;
		}

		// the following code line is a copy from C++ version and
		// is used for identity of variable names for JDFValidator in Java and
		// C++.
		// In C++ here a factory object is created.
		final boolean bIsValid = privateValidation(kElement, testElement);

		if (!(kElement instanceof JDFElement))
		{
			return; // TODO more
		}
		final JDFElement jdfElement = printBadJDF(kElement, indent, xmlParent, bIsNodeRoot, id, elmName, testElement, bIsValid);

		// recurse through all child elements :
		final VElement ve = jdfElement.getChildElementVector(null, null, null, true, 0, false);
		for (final KElement e : ve)
		{
			printBad(e, indent + 2, testElement, false);
		}
	}

	protected JDFElement printBadJDF(final KElement kElement, final int indent, final KElement xmlParent, final boolean bIsNodeRoot, final String id, final String elmName, final KElement testElement, boolean bIsValid)
	{
		final JDFElement jdfElement = (JDFElement) kElement;
		bIsValid = isValidElement(bIsValid, jdfElement);

		final VString privateAttributes = new VString(jdfElement.getUnknownAttributes(false, 9999999));
		VString unknownAttributes = new VString();
		if (!bIsValid)
			unknownAttributes = jdfElement.getUnknownAttributes(true, 9999999);
		privateAttributes.removeStrings(unknownAttributes, Integer.MAX_VALUE);

		final VString privateElements = new VString(jdfElement.getUnknownElements(false, 9999999));
		VString unknownElements = new VString();
		if (!bIsValid)
			unknownElements = jdfElement.getUnknownElements(true, 9999999);
		privateElements.removeStrings(unknownElements, Integer.MAX_VALUE);

		if (bPrintNameSpace)
		{
			printPrivate(privateAttributes, privateElements, jdfElement, indent, testElement);
		}

		boolean bIsOK = true;

		if (jdfElement instanceof JDFResourceLinkPool)
		{// check typesafe node links
			bIsOK = true; // nop this is done in printnode
		}
		else if (jdfElement instanceof JDFRefElement)
		{
			bIsOK = printRefElement((JDFRefElement) jdfElement, indent, testElement);
		}
		else if (jdfElement instanceof JDFResourceLink)
		{
			bIsOK = printResourceLink((JDFResourceLink) jdfElement, indent, testElement);
		}
		else if (jdfElement instanceof JDFResource)
		{
			bIsOK = printResource((JDFResource) jdfElement, indent, testElement);
		}
		if (bWarnDanglingURL)
		{
			printURL(kElement, indent, testElement);
		}

		final boolean bValidID = id == null || id.equals(JDFConstants.EMPTYSTRING) ? true : !vBadID.contains(id);
		boolean bUnknownElem = false;

		if (testElement != null && xmlParent != null)
		{
			if (bIsOK)
			{
				final String invElems = xmlParent.getAttribute("PreReleaseElements");
				bIsOK = !StringUtil.hasToken(invElems, elmName, " ", 0);
				if (!bIsOK)
				{
					final EnumVersion v = ((JDFElement) jdfElement.getParentNode_KElement()).getFirstVersion(elmName, true);
					testElement.setAttribute("FirstVersion", v.getName());
					setErrorType(testElement, "PreReleaseElement", elmName + " is not valid in JDF Version" + jdfElement.getVersion(true).getName() + " First Valid version: "
							+ v.getName(), indent + 2);
				}
			}

			if (bIsOK && bWarning)
			{
				final String invElems = xmlParent.getAttribute("DeprecatedElements");
				bIsOK = !StringUtil.hasToken(invElems, elmName, " ", 0);
				if (!bIsOK)
				{
					final EnumVersion v = ((JDFElement) jdfElement.getParentNode_KElement()).getLastVersion(elmName, true);
					testElement.setAttribute("LastVersion", v.getName());
					setErrorType(testElement, "DeprecatedElement", elmName + " is not valid in JDF Version" + jdfElement.getVersion(true).getName() + " Last Valid version: "
							+ v.getName(), indent + 2);
				}
			}

			if (bIsOK)
			{
				final String invElems = xmlParent.getAttribute("PrivateElements");
				bIsOK = !StringUtil.hasToken(invElems, elmName, " ", 0);
				if (!bIsOK)
				{
					setErrorType(testElement, "PrivateElement", elmName + " is not a valid subelement");
				}
			}
			if (bIsOK)
			{
				final String swapElems = xmlParent.getAttribute("SwapElements");
				bIsOK = !StringUtil.hasToken(swapElems, elmName, " ", 0);
				if (!bIsOK)
				{
					setErrorType(testElement, "SwapElement", elmName + " is written as an Element");
				}
			}
			if (bIsOK)
			{
				final String unkElems = xmlParent.getAttribute("UnknownElements", null, null);
				if (unkElems != null)
				{
					bIsOK = !StringUtil.hasToken(unkElems, elmName, " ", 0);
					if (!bIsOK)
					{
						setErrorType(testElement, "UnknownElement", elmName + " is not defined in a " + xmlParent.getLocalName());
						bUnknownElem = true;
					}
				}
			}
		}

		if (bUnknownElem && testElement != null)
		{
			testElement.setAttribute("IsValid", false, null);
		}
		else if (bIsValid && bValidID && bIsOK)
		{
			// print out validity if not quiet
			if (!bQuiet)
			{
				sysOut.println(indent(indent) + "--- Valid:" + jdfElement.buildXPath(null, 1) + " " + id);
			}
			if (testElement != null)
			{
				testElement.setAttribute("IsValid", true, null);
			}
			if (bIsNodeRoot && xmlParent != null)
			{
				xmlParent.setAttribute("IsValid", true, null);
			}

		}
		else
		// this one is bad -> recurse to find a reason
		{
			if (bIsOK) // for the case "Logic is not OK", we printed this line
			// already.
			{
				sysOut.println(indent(indent) + "!!! InValid Element: " + kElement.buildXPath(null, 1) + " " + id + " !!! ");
			}
			if (testElement != null)
			{
				testElement.setAttribute("IsValid", false, null);
			}
			sysOut.println(indent(indent + 2) + "Invalid Element " + elmName + getInvalidText(jdfElement));
			if (testElement != null && !testElement.hasAttribute("ErrorType"))
			{
				setErrorType(testElement, "InvalidElement", elmName + getInvalidText(jdfElement), 2);
			}

			if (!bValidID && testElement != null)
			{
				final KElement e = testElement.appendElement("TestAttribute");
				setErrorType(e, "MultipleID", "Multiply defined ID = " + id, indent);
				e.setAttribute("NodeName", "ID");
				e.setAttribute("Value", id);
				e.setAttribute("XPath", jdfElement.buildXPath(null, 1) + "/@ID");
			}

			boolean printMissElms = true;
			if (jdfElement instanceof JDFResource)
			{
				final JDFResource r = (JDFResource) jdfElement;
				if (JDFResource.EnumResStatus.Incomplete.equals(r.getResStatus(false)) || (!r.isLeaf() && (!JDFResource.EnumPartUsage.Implicit.equals(r.getPartUsage()))))
				{
					printMissElms = false;
				}
			}

			final VString swapAtt = new VString();
			VString vTmp = jdfElement.knownElements();
			// compare missing elements with unknown attributes to find elem <->  attrib swaps
			for (final String unknownAttr : unknownAttributes)
			{
				if (vTmp.contains(unknownAttr))
				{
					swapAtt.appendUnique(unknownAttr);
				}
			}

			// compare missing attributes with unknown elements to find elem <->
			// attrib swaps
			final VString swapElem = new VString();
			vTmp = jdfElement.knownAttributes();
			for (final String unknownElem : unknownElements)
			{
				if (vTmp.contains(unknownElem))
				{
					swapElem.appendUnique(unknownElem);
				}
			}

			// get a list of missing and invalid attribute and element names
			final VString invalidAttributes = getInvalidAttributes(jdfElement);
			final VString invalidElements = jdfElement.getInvalidElements(level, true, 9999999);
			VString missingAttributes = new VString();
			VString missingElements = new VString();
			VString deprecatedAttributes = jdfElement.getDeprecatedAttributes(9999999);
			VString deprecatedElements = jdfElement.getDeprecatedElements(9999999);
			final VString prereleaseAttributes = jdfElement.getPrereleaseAttributes(9999999);
			final VString prereleaseElements = jdfElement.getPrereleaseElements(9999999);

			// unknown attributes are also invalid -> remove them from the print
			// list
			invalidAttributes.removeStrings(unknownAttributes, 99999);
			invalidAttributes.removeStrings(deprecatedAttributes, 99999);
			invalidAttributes.removeStrings(prereleaseAttributes, 99999);
			unknownAttributes.removeStrings(prereleaseAttributes, 99999);
			unknownAttributes.removeStrings(deprecatedAttributes, 99999);

			// unknown elements are also invalid -> remove them from the print
			// list
			invalidElements.removeStrings(unknownElements, 99999);
			invalidElements.removeStrings(deprecatedElements, 99999);
			invalidElements.removeStrings(prereleaseElements, 99999);
			unknownElements.removeStrings(deprecatedElements, 99999);
			unknownElements.removeStrings(prereleaseElements, 99999);

			// swapped elements are also invalid -> remove them from the print list
			unknownElements.removeStrings(swapElem, 99999);
			// swapped attributes are also invalid -> remove them from the print list
			unknownAttributes.removeStrings(swapAtt, 99999);

			// find missing elements and attributes
			if (level.getValue() >= EnumValidationLevel.Complete.getValue())
			{
				missingAttributes = new VString(jdfElement.getMissingAttributes(9999999));
				// missing attributes are also invalid -> remove them from the print list
				invalidAttributes.removeStrings(missingAttributes, 99999);
				missingElements = new VString(jdfElement.getMissingElements(9999999));
				// missing elements are also invalid -> remove them from the print list
				invalidElements.removeStrings(missingElements, 99999);
			}

			// remove all warning lists
			if (!bWarning)
			{
				deprecatedElements = null;
				deprecatedAttributes = null;
			}

			// remove all double entries before printing
			unknownElements.unify();
			missingElements.unify();

			// print the various snafus

			printAttributeList(indent, testElement, jdfElement, printMissElms, unknownAttributes, "Unknown", "Unknown Attribute");
			printAttributeList(indent, testElement, jdfElement, printMissElms, invalidAttributes, "Invalid", "Invalid attribute Value");
			printAttributeList(indent, testElement, jdfElement, printMissElms, deprecatedAttributes, "Deprecated", "Deprecated Attribute in JDF Version "
					+ jdfElement.getVersion(true).getName());
			printAttributeList(indent, testElement, jdfElement, printMissElms, prereleaseAttributes, "PreRelease", "Attribute not yet defined in JDF Version "
					+ jdfElement.getVersion(true).getName());
			printAttributeList(indent, testElement, jdfElement, printMissElms, missingAttributes, "Missing", "Missing Attribute");
			printAttributeList(indent, testElement, jdfElement, printMissElms, swapAtt, "Swap", "Element written as Attribute");

			for (final String swEl : swapElem)
			{
				sysOut.println(indent(indent + 2) + "Attribute is written as Element: " + swEl);
			}

			if (swapElem.size() > 0 && testElement != null)
			{
				testElement.setAttribute("SwapElements", StringUtil.setvString(swapElem, JDFConstants.BLANK, null, null));
			}

			if (printMissElms)
			{
				for (final String missEl : missingElements)
				{
					sysOut.println(indent(indent + 2) + "Missing Element: " + missEl);

					if (testElement != null)
					{
						final KElement e = testElement.appendElement("TestElement");
						setErrorType(e, "MissingElement", "Missing Element " + missEl);
						e.setAttribute("XPath", jdfElement.buildXPath(null, 1) + "/" + missEl + "[1]");
						e.setAttribute("NodeName", missEl);
					}
				}
				if (testElement != null && missingElements.size() > 0)
				{
					testElement.setAttribute("MissingElements", StringUtil.setvString(missingElements, JDFConstants.BLANK, null, null));
				}
			}
			for (final String elem : unknownElements)
			{
				sysOut.println(indent(indent + 2) + "Unknown Element: " + elem);
			}
			if (testElement != null && unknownElements.size() > 0)
			{
				testElement.setAttribute("UnknownElements", StringUtil.setvString(unknownElements, JDFConstants.BLANK, null, null));
			}

			printElementList(indent, testElement, jdfElement, invalidElements, "Invalid");
			printElementList(indent, testElement, jdfElement, deprecatedElements, "Deprecated");
			printElementList(indent, testElement, jdfElement, prereleaseElements, "PreRelease");
			printElementList(indent, testElement, jdfElement, privateElements, "Private");

			if (jdfElement instanceof JDFResource)
			{
				final JDFResource res = (JDFResource) jdfElement;

				if (!res.isLeaf())
				{ // handle partitioned resources
					final VElement vr = res.getLeaves(false);
					for (final KElement leaf : vr)
					{
						printBad(leaf, indent + 2, testElement, false);
					}
				}
			}
		}
		return jdfElement;
	}

	protected void printNonNamespace(final KElement kElement, final int indent, final KElement xmlParent, final String pref, final String elmName, final String nsURI, final KElement testElement, final boolean isJDFNS, boolean bTypo)
	{
		final String nameSpaceURI = kElement.getNamespaceURI();
		final String nsLower = nameSpaceURI.toLowerCase();
		if (nsLower.contains(JDFConstants.CIP4ORG) && !nsLower.equals(JDFConstants.JDFNAMESPACE))
		{
			sysOut.println("Probable namespace Typo: xmlns=" + nameSpaceURI + " should be:" + JDFConstants.JDFNAMESPACE);
			if (testElement != null)
			{
				bTypo = setTypo(pref, elmName, nsURI, testElement);
			}
		}
		if (bPrintNameSpace)
		{
			sysOut.print(indent(indent + 2));
			final String status = isJDFNS ? "Testing" : "Skipping";
			sysOut.print(status + " ");

			sysOut.println("Element that is not in JDF nameSpace: <" + kElement.getLocalName() + "> namespace:" + pref + "  uri: " + nsURI);
			setErrorType(testElement, "PrivateElement", "Element in Private NameSpace: " + elmName);
			if (testElement != null)
			{
				testElement.setAttribute("NSPrefix", pref);
				testElement.setAttribute("NSURI", nsURI);
				testElement.setAttribute("IsPrivate", true, null);
				testElement.setAttribute("Status", "Skipping");
			}
		}
		if (kElement instanceof JDFResourceLink)
		{
			printResourceLink((JDFResourceLink) kElement, indent, testElement);
		}

		if (kElement instanceof JDFRefElement)
		{
			printRefElement((JDFRefElement) kElement, indent, testElement);
		}

		if (kElement instanceof JDFResource)
		{
			printResource((JDFResource) kElement, indent, testElement);
		}

		if (!bPrintNameSpace && !bTypo && xmlParent != null && testElement != null && !testElement.hasChildElements())
		{
			testElement.deleteNode();
		}
	}

	protected KElement getTestElement(final KElement kElement, final KElement xmlParent)
	{
		KElement testElement = null;

		if (xmlParent != null)
		{
			testElement = xmlParent.appendElement("TestElement");
			testElement.setAttribute("XPath", kElement.buildXPath(null, 1));
			testElement.setAttribute("NodeName", kElement.getNodeName());
			final String strID = kElement.getAttribute(AttributeName.ID, null, null);
			if (strID != null)
			{
				testElement.setAttribute("ID", strID);
			}
		}
		return testElement;
	}

	protected String getInvalidText(final JDFElement jdfElement)
	{
		if (jdfElement instanceof JDFColorPool)
		{
			final VString duplicateColors = ((JDFColorPool) jdfElement).getDuplicateColors();
			if (duplicateColors != null)
			{
				return " duplicate colors in colorPool: " + StringUtil.setvString(duplicateColors);
			}
		}
		return " is not valid, see child elements for details";
	}

	private boolean setTypo(final String pref, final String elmName, final String nsURI, final KElement testElement)
	{
		boolean bTypo;
		bTypo = true;
		testElement.setAttribute("NSPrefix", pref);
		testElement.setAttribute("NSURI", nsURI);
		testElement.setAttribute("IsPrivate", true, null);
		testElement.setAttribute("Status", "Skipping");
		setErrorType(testElement, "PrivateElement", "Element in Private NameSpace - probable Namespace Typo in: " + elmName + " Correct ns URI=" + JDFConstants.JDFNAMESPACE);
		return bTypo;
	}

	/**
	 * @param jdfElement
	 * @return
	 */
	private VString getInvalidAttributes(final JDFElement jdfElement)
	{
		final VString invalidAttributes = new VString(jdfElement.getInvalidAttributes(level, true, 9999999));
		if (jdfElement instanceof JDFResource)
		{
			JDFResource r = (JDFResource) jdfElement;
			while (!r.isResourceRoot() && !r.isResourceElement())
			{
				r = (JDFResource) r.getParentNode_KElement();
				invalidAttributes.appendUnique(r.getInvalidAttributes(level, true, 9999));
			}
		}
		return invalidAttributes;
	}

	/**
	 * @param bIsValid
	 * @param jdfElement
	 * @return
	 */
	private boolean isValidElement(boolean bIsValid, final JDFElement jdfElement)
	{
		bIsValid = jdfElement.isValid(level) && bIsValid;
		if (bIsValid && (jdfElement instanceof JDFResource))
		{
			JDFResource r = (JDFResource) jdfElement;
			if (!r.isResourceRoot() && !r.isResourceElement())
			{
				r = (JDFResource) r.getParentNode();
				return (isValidElement(bIsValid, r));
			}

		}
		return bIsValid;
	}

	/**
	 * @param element
	 * @return
	 */
	private boolean privateValidation(final KElement toCheck, final KElement report)
	{
		if (validatorFactory == null)
		{
			return true;
		}
		final ICheckValidator v = validatorFactory.getValidator(toCheck);
		if (v == null)
		{
			return true;
		}
		return v.validate(toCheck, report);
	}

	/**
	 * @param element
	 * @param indent
	 * @param testElement
	 */
	private void printURL(final KElement element, final int indent, final KElement testElement)
	{
		if (!element.hasAttribute(AttributeName.URL))
		{
			return;
		}
		final String url = element.getAttribute(AttributeName.URL);
		if (UrlUtil.getURLInputStream(url, element.getOwnerDocument_KElement().getBodyPart()) == null)
		{
			// found bad url
			final KElement e = testElement.appendElement("TestAttribute");
			setErrorType(e, "DanglingURL", "Dangling URL points to Nirvana: " + url, indent);
			e.setAttribute("NodeName", "URL");
			e.setAttribute("Value", url);
			e.setAttribute("XPath", element.buildXPath(null, 1) + "/@URL");
		}
	}

	private void printElementList(final int indent, final KElement testElement, final JDFElement part, final VString elementVector, final String whatType)
	{
		if (elementVector == null)
		{
			return;
		}

		elementVector.unify();
		int j;
		// String potDup=" : ";
		String potRef = " : ";
		if (whatType.equals("Invalid"))
		{
			// potDup="- (potential duplicate): ";
			potRef = "- (potential reference to invalid element): ";
		}

		for (j = 0; j < elementVector.size(); j++)
		{
			final String invalidElem = elementVector.get(j);
			if (part.numChildElements(invalidElem, "") > 1)
			{
				sysOut.println(indent(indent + 2) + whatType + " Element " + potRef + invalidElem);
			}
			final KElement ePart = part;
			KElement eInv = ePart.getElement(invalidElem, null, 0);
			if (eInv == null)
			{
				eInv = ePart.getElement(invalidElem + "Ref", null, 0);
				if (eInv != null)
				{
					sysOut.println(indent(indent + 2) + whatType + " Element " + potRef + invalidElem + "Ref");
				}
			}
		}
		if (testElement != null && elementVector.size() > 0)
		{
			testElement.setAttribute(whatType + "Elements", StringUtil.setvString(elementVector, JDFConstants.BLANK, null, null));
		}
	}

	private void printAttributeList(final int indent, final KElement testElement, final JDFElement part, final boolean printMissElms, final VString attributeVector, final String whatType, String message)
	{
		if (attributeVector == null)
		{
			return;
		}

		attributeVector.unify();
		final String originalMessage = message;
		int j;
		for (j = 0; j < attributeVector.size(); j++)
		{
			message = originalMessage;
			final String invalidAt = attributeVector.elementAt(j);
			if (!((KElement) part).hasAttribute_KElement(invalidAt, "", false)) // exactly
			// this node ( e.g. ResourceElement or Leaf )
			{
				if (EnumPartIDKey.getEnum(invalidAt) != null)
				{
					// missing PartIDKeys are written into invalidAttributes vector
					if (part.getAttribute(invalidAt, null, null) == null)
					{
						if (printMissElms)
						{
							final KElement e = testElement.appendElement("TestAttribute");
							setErrorType(e, "MissingAttribute", "Missing Partition key: " + invalidAt, indent + 2);
							e.setAttribute("NodeName", invalidAt);
							e.setAttribute("XPath", part.buildXPath(null, 1) + "/@" + invalidAt);
						}
					}
					else
					{
						final KElement e = testElement.appendElement("TestAttribute");
						setErrorType(e, "InvalidAttribute", "Incorrectly placed Partition key: " + invalidAt, indent + 2);
						e.setAttribute("NodeName", invalidAt);
						e.setAttribute("XPath", part.buildXPath(null, 1) + "/@" + invalidAt);
					}
				}
				else if (!part.hasAttribute(invalidAt, null, false)) // if the
				// resourceRoot doesn`t have it as well
				{
					if (printMissElms)
					{
						final KElement e = testElement.appendElement("TestAttribute");
						setErrorType(e, "MissingAttribute", "Missing required attribute: " + invalidAt, indent + 2);
						e.setAttribute("NodeName", invalidAt);
						e.setAttribute("XPath", part.buildXPath(null, 1) + "/@" + invalidAt);
					}
				}
			}
			else
			{
				sysOut.println(indent(indent + 2) + whatType + " Attribute: " + invalidAt + " = " + part.getAttribute(invalidAt));
				final KElement e = testElement.appendElement("TestAttribute");
				if (whatType.equals("PreRelease"))
				{
					final EnumVersion v = part.getFirstVersion(invalidAt, false);
					if (v != null)
					{
						e.setAttribute("FirstVersion", v.getName());
						message += " First valid Version: " + v.getName();
					}
				}
				else if (whatType.equals("Deprecated"))
				{
					final EnumVersion v = part.getLastVersion(invalidAt, false);
					if (v != null)
					{
						e.setAttribute("LastVersion", v.getName());
						message += " Last valid Version: " + v.getName();
					}
				}

				setErrorType(e, whatType + "Attribute", invalidAt + " " + message);
				e.setAttribute("NodeName", invalidAt);
				e.setAttribute("XPath", part.buildXPath(null, 1) + "/@" + invalidAt);
				e.setAttribute("Value", part.getAttribute(invalidAt));

			}
		}

		if (attributeVector.size() > 0)
		{
			testElement.setAttribute(whatType + "Attributes", StringUtil.setvString(attributeVector, JDFConstants.BLANK, null, null));
		}
	}

	/**
	 * For all subnodes that 'root' consist of makes the total check of Links, Resources and separations fill in the vectors 'vLinkedResources', 'vResources',
	 * 'vBadResourceLinks', 'vColorPoolSeparations', 'vSeparations'. Print the warnings
	 *
	 * @param root - Node root we test
	 * @param bQuiet - Mode '-q' quiet
	 * @param level - validation level
	 * @param xmlParent - xmlParent for yml output of this check
	 */
	private void printNodeRoot(final JDFNode root, final KElement xmlParent)
	{
		// get a vector with all jdf nodes and loop over all jdf nodes

		final VElement vProcs = root.getvJDFNode(null, null, false);
		int i, j;
		final int size = vProcs.size();
		for (i = 0; i < size; i++)
		{
			final JDFNode n = (JDFNode) vProcs.elementAt(i);

			vLinkedResources.appendUnique(n.getLinkedResources(null, true));

			// find unlinked resources in ResourcePool
			final JDFResourcePool rp = n.getResourcePool();
			if (rp != null)
			{
				final VElement resources = rp.getPoolChildren(null, null, null);
				vResources.addAll(resources);
				final int resSize = resources.size();
				for (j = 0; j < resSize; j++)
				{
					final JDFResource jdfResource = (JDFResource) resources.elementAt(j);
					final VElement vjdfResource = jdfResource.getvHRefRes(true, true);
					vResources.addAll(vjdfResource);
				}
				vResources.unify();
			}
			// add all invalid resource links in ResourcePool to
			// vBadResourceLinks
			final JDFResourceLinkPool rlp = n.getResourceLinkPool();
			if (rlp != null)
			{
				final VElement vLinks = rlp.getPoolChildren(null, null, null);
				if (vLinks != null)
				{
					final int size2 = vLinks.size();
					for (j = size2 - 1; j >= 0; j--)
					{
						final JDFResourceLink rl = (JDFResourceLink) vLinks.elementAt(j);
						if (!n.isValidLink(level, rl))
						{
							vBadResourceLinks.add(rl);
						}
						else
						{
							final JDFElement target = rl.getTarget();
							if (target == null)
							{
								vBadResourceLinks.add(rl);
							}
						}
					}
					vBadResourceLinks.unify();
				}
			}
		}

		final VElement vr = new VElement();
		for (i = 0; i < vResources.size(); i++)
		{
			final JDFResource res = (JDFResource) vResources.elementAt(i);
			vr.appendUnique(res.getResourceRoot());
		}
		vResources = new VElement(vr);

		vr.clear();
		for (i = 0; i < vLinkedResources.size(); i++)
		{
			if ((vLinkedResources.elementAt(i)) instanceof JDFResource)
			{
				final JDFResource linkedRes = (JDFResource) vLinkedResources.elementAt(i);
				vr.appendUnique(linkedRes.getResourceRoot());
			}
		}
		vLinkedResources = new VElement(vr);

		KElement sepPool = null;
		if (xmlParent != null)
		{
			sepPool = xmlParent.appendElement("SeparationPool");
		}

		if (!bQuiet)
		{
			for (i = 0; i < vSeparations2.size(); i++)
			{
				sysOut.println("Separation Name: " + vSeparations2.elementAt(i));
				if (sepPool != null)
				{
					final KElement sep = sepPool.appendElement("Separation");
					sep.setAttribute("SeparationName", vSeparations2.get(i));
				}
			}
		}
		for (final String sep : vSeparations)
		{
			sysOut.println("Warning: Separation Name not in ColorPool: " + sep);
			if (sepPool != null)
			{
				final KElement warn = sepPool.appendElement("Warning");
				setErrorType(warn, "MissingSeparation", "Separation Name " + sep + " is not in ColorPool");
				warn.setAttribute("Separation", sep);
			}
		}
		for (final String sep : vColorPoolSeparations)
		{
			sysOut.println("Warning: Unreferenced Separation Name    : " + sep);
			if (sepPool != null)
			{
				final KElement warn = sepPool.appendElement("Warning");
				setErrorType(warn, "UnreferencedSeparation", "Unreferenced Separation Name: " + sep + " in ColorPool");
				warn.setAttribute("Separation", sep);
			}
		}

		if (sepPool != null && !sepPool.hasChildElements() && xmlParent != null)
		{
			xmlParent.removeChild(sepPool);
		}

	}

	/**
	 * Print private contents
	 *
	 * @param privateAttributes - vector of private attributes
	 * @param privateElements - vector of private elements
	 * @param jdfElement - jdfElement we test
	 * @param indent - indent for println() to console window
	 * @param testElement - test element of the XML output (if '-x' set) we "stand in"
	 */
	private void printPrivate(final VString privateAttributes, final VString privateElements, final KElement jdfElement, final int indent, final KElement testElement)
	{
		int j;

		if (!privateAttributes.isEmpty() || !privateElements.isEmpty())
		{
			sysOut.println(indent(indent) + "Element with private contents:   " + jdfElement.buildXPath(null, 1) + " " + jdfElement.getAttribute(AttributeName.ID, null, ""));

			if (testElement != null)
			{
				setErrorType(testElement, "PrivateContents", "Element with private contents");
				if (privateElements.size() > 0)
				{
					testElement.setAttribute("PrivateElements", StringUtil.setvString(privateElements, JDFConstants.BLANK, null, null));
				}
			}
		}
		for (j = 0; j < privateAttributes.size(); j++)
		{ // print all private parameters
			final String privateAttribute = privateAttributes.elementAt(j);
			final String prefix = StringUtil.token(privateAttribute, 0, ":");
			final String localname = StringUtil.token(privateAttribute, 1, ":");
			if (prefix != null && prefix.equals("xmlns"))
			{
				if (!foundNameSpaces.contains(privateAttribute))
				{
					sysOut.println(indent(indent + 2) + "Foreign namespace found: " + localname + " " + jdfElement.getAttribute(privateAttribute));

					if (testElement != null)
					{
						final KElement e = testElement.appendElement("ForeignNSFound");
						e.setAttribute("NSPrefix", localname);
						e.setAttribute("NSURI", jdfElement.getAttribute(privateAttribute));
					}
					foundNameSpaces.addElement(privateAttribute);
				}
			}
			else
			{
				sysOut.println(indent(indent + 2) + "Private Attribute:     " + prefix + " " + localname + " = " + jdfElement.getAttribute(privateAttribute));

				if (testElement != null)
				{
					final KElement e = testElement.appendElement("TestAttribute");
					e.setAttribute("IsPrivate", "true");
					e.setAttribute("NSPrefix", prefix);
					e.setAttribute("NSURI", jdfElement.getNamespaceURIFromPrefix(prefix));
					e.setAttribute("ErrorType", "PrivateAttribute");
					e.setAttribute("NodeName", privateAttribute);
					e.setAttribute("Value", jdfElement.getAttribute(privateAttribute));
					e.setAttribute("XPath", jdfElement.buildXPath(null, 1) + "/@" + privateAttribute);
				}
			}
		}

		for (j = 0; j < privateElements.size(); j++)
		{
			sysOut.println(indent(indent + 2) + "Private Element:       " + privateElements.get(j));

		}
	}

	/**
	 * Check a whole Node and print the problems if exist
	 *
	 * @param jdfNode - JDFNode we check
	 * @param level - validation level
	 * @param indent - indent for println() to console window
	 * @param testElement - test element of the XML output (if '-x' set) we "stand in"
	 * @return boolean - true if valid
	 */
	private boolean printNode(final JDFNode jdfNode, final int indent, final KElement testElement)
	{
		boolean isValid = true;
		final String jobPartID = jdfNode.getJobPartID(false);

		final VString vMissingLinks = EnumValidationLevel.isRequired(level) ? jdfNode.getMissingLinkVector(9999999) : null;
		final VString vInvalidLinks = jdfNode.getInvalidLinks(level, 9999999);

		final boolean bValidJobPartID = !vBadJobPartID.contains(jobPartID);
		if (!bValidJobPartID)
		{
			final KElement e = testElement.appendElement("TestAttribute");
			if (jobPartID.equals(""))
			{
				setErrorType(e, "MissingAttribute", "Missing JobPartID - required by Base ICS", indent);
			}
			else
			{
				setErrorType(e, "MultipleID", "Multiply defined JobPartID = " + jobPartID, indent);
				e.setAttribute("Value", jobPartID);
			}
			e.setAttribute("NodeName", AttributeName.JOBPARTID);
			e.setAttribute("XPath", jdfNode.buildXPath(null, 1) + "/@JobPartID");
		}
		else
		{
			vJobPartID.add(jobPartID);
		}

		isValid = checkType(jdfNode, indent, testElement, isValid);

		if (vMissingLinks != null)
		{
			if (vInvalidLinks != null)
			{
				vInvalidLinks.removeStrings(vMissingLinks, 9999);
			}
			if (testElement != null && EnumValidationLevel.isRequired(level))
			{
				if (jdfNode.getElement(ElementName.RESOURCELINKPOOL, null, 0) == null)
				{
					final KElement pool = testElement.appendElement("TestElement");
					setErrorType(pool, "MissingElement", "Missing ResourceLinkPool");
					pool.setAttribute("NodeName", "ResourceLinkPool");
				}
				printResourceLinkPool(jdfNode.buildXPath(null, 1) + "/ResourceLinkPool[1]", testElement, vMissingLinks, "Missing");
			}
		}
		if (vInvalidLinks != null)
		{
			if (vInvalidLinks.size() > 0)
			{
				printResourceLinkPool(jdfNode.buildXPath(null, 1) + "/ResourceLinkPool[1]", testElement, vInvalidLinks, "Invalid");
			}
		}

		return isValid;
	}

	private boolean checkType(final JDFNode jdfNode, final int indent, final KElement testElement, final boolean isValid)
	{
		boolean isValidLocal = isValid;

		final String errMessage = indent(indent) + "!!! InValid Element: " + jdfNode.buildXPath(null, 1) + " " + jdfNode.getID() + " !!! ";
		if (jdfNode.hasAttribute(AttributeName.TYPE))
		{
			final String typeString = jdfNode.getType();
			String nodeType = indent(indent) + "Node Type = " + typeString;
			if (jdfNode.hasAttribute("Types", "", false))
			{
				nodeType += " - " + jdfNode.getAttribute("Types");
			}

			testElement.setAttribute("Type", typeString);
			if (jdfNode.hasAttribute("Types"))
			{
				testElement.setAttribute("Types", jdfNode.getAttribute("Types"));
				if (bPrintNameSpace && jdfNode.getEnumTypes() == null)
				{
					final VString vs = jdfNode.getTypes();
					String msg = "";
					int n = 0;
					if (vs == null)
					{
						// illegal type for types
					}
					else
					{
						for (int i = 0; i < vs.size(); i++)
						{
							final String t = vs.get(i);
							if (EnumType.getEnum(t) == null)
							{
								if (ArrayUtils.contains(aGBList, t))
								{
									continue;
								}

								if (n++ > 0)
								{
									msg += "; ";
								}

								msg += t;
							}
						}
					}
					if (n > 0)
					{
						final KElement e = testElement.appendElement("TestAttribute");
						setErrorType(e, "ExtensionType", "JDF/@Types contains extension types: " + msg, indent + 2);
						e.setAttribute("XPath", jdfNode.buildXPath(null, 1) + "/@Types");
						e.setAttribute("NodeName", "Types");
						e.setAttribute("Value", msg);
					}

				}
			}

			// check for virtual or extension types
			if (bPrintNameSpace && EnumType.getEnum(typeString) == null)
			{
				final KElement e = testElement.appendElement("TestAttribute");
				setErrorType(e, "ExtensionType", "Type is an extension type: " + typeString, indent + 2);
				e.setAttribute("XPath", jdfNode.buildXPath(null, 1) + "/@Type");
				e.setAttribute("NodeName", "Type");
				e.setAttribute("Value", typeString);
			}

			if (typeString.equals(JDFConstants.PRODUCT))
			{
				final JDFNode n = jdfNode.getParentJDF();
				if (n != null)
				{
					if (!JDFConstants.PRODUCT.equals(n.getType()))
					{
						isValidLocal = false;
						sysOut.println(errMessage);
						sysOut.println(nodeType);
						sysOut.println(indent(indent) + "Invalid Parent for JDF Product: Type= " + n.getType());

						setErrorType(testElement, "InvalidParentForProduct", "Invalid Parent for JDF Product: Type = " + n.getType());
						testElement.setAttribute("NodeName", "JDF");
						testElement.setAttribute("XPath", n.buildXPath(null, 1));
					}
				}
			}
		}

		return isValidLocal;
	}

	/**
	 * Check ResourceLinkPool and print the problem if it contains missing resourceLinks
	 *
	 * @param rlp - JDFResourceLinkPool we check
	 * @param level - validation level
	 * @param indent - indent for println() to console window
	 * @param testElement - test element of the XML output (if '-x' set) we "stand in"
	 * @return boolean - true if valid
	 */
	private void printResourceLinkPool(final String rlpXPath, final KElement testElement, final VString vLinks, final String missBad)
	{
		if (vLinks != null)
		{
			final int size = vLinks.size();
			for (int i = 0; i < size; i++)
			{
				final String missResLink = vLinks.get(0);
				if (testElement != null)
				{
					final KElement e = testElement.appendElement("TestElement");

					final String name = missResLink.indexOf(":") > 0 ? StringUtil.token(missResLink, 0, ":") : missResLink;
					String procUsage = missResLink.indexOf(":") > 0 ? StringUtil.token(missResLink, 1, ":") : "";
					if (procUsage.startsWith("Any"))
					{
						procUsage = procUsage.substring(3);
					}

					setErrorType(e, missBad + "ResourceLink", missBad + procUsage + " resourceLink ");
					e.setAttribute("NodeName", name);
					if (!procUsage.equals(JDFConstants.EMPTYSTRING))
					{
						e.setAttribute("ProcessUsage", procUsage);
					}

					e.setAttribute("XPath", rlpXPath + "/" + name + "[1]");
				}

				vLinks.removeElement(missResLink);
			}
		}
	}

	/**
	 * Check RefElements and print the problem if they are not valid
	 *
	 * @param r - refElement we check
	 * @param level - validation level
	 * @param indent - indent for println() to console window
	 * @param testElement - test element of the XML output (if '-x' set) we "stand in"
	 * @return boolean - true if valid
	 */
	private boolean printRefElement(final JDFRefElement re, final int indent, final KElement testElement)
	{
		boolean isValid = true;
		final String rRef = re.getrRef();
		final String refName = re.getNodeName();

		final String errMessage = indent(indent) + "!!! InValid Element: " + re.buildXPath(null, 1) + " !!! ";

		if (testElement != null)
		{
			testElement.setAttribute("rRef", rRef);
			if (re.hasAttribute(AttributeName.RSUBREF))
			{
				testElement.setAttribute("rSubRef", re.getrSubRef());
			}
		}

		if (vBadID.contains(rRef))
		{
			isValid = false;
			sysOut.println(errMessage);
			sysOut.println(indent(indent) + "Invalid RefElement: " + refName + ". Points to the element with multiply defined ID=" + rRef);
			setErrorType(testElement, "InvalidRefElement", "RefElement: " + refName + "Points to the multiply defined ID");
		}
		else
		{
			final JDFResource targEl = re.getTarget();

			if (targEl == null)
			{

				isValid = false;
				sysOut.println(errMessage);
				sysOut.println(indent(indent) + "Dangling RefElement: " + refName + " rRef=" + rRef);
				final JDFResource targRoot = re.getTargetRoot();
				if (targRoot != null)
				{
					sysOut.println(indent(indent) + "Refelement points to non-existing partition: " + re.getPartMap().toString());
				}

				if (testElement != null)
				{
					if (targRoot == null)
					{
						setErrorType(testElement, "DanglingRefElement", "RefElement points to nonexisting ID. rRef=" + rRef);
					}
					else
					{
						setErrorType(testElement, "DanglingPartRefElement", "RefElement points to nonexisting Partition. rRef=" + rRef);
						testElement.appendElement("Part").setAttributes(re.getPartMap());
						testElement.setAttribute("ResourcePartUsage", targRoot.getPartUsage().getName(), null);
					}
				}
			}
			else if (!re.isValid(level))
			{
				isValid = false;
				sysOut.println(errMessage);
				sysOut.println(indent(indent) + "Invalid RefElement: " + refName + " rRef=" + rRef
						+ (re.hasAttribute(AttributeName.RSUBREF) ? (" rSubRef=" + re.getrSubRef()) : JDFConstants.EMPTYSTRING) + ". Points to " + re.getRefNodeName() + " ID="
						+ targEl.getAttribute(AttributeName.ID, null, ""));

				if (!re.validResourcePosition())
				{
					final JDFNode targJDF = targEl.getParentJDF();
					final String targID = targJDF == null ? "" : targJDF.getID();
					final JDFNode refJDF = re.getParentJDF();
					final String refID = refJDF == null ? "" : refJDF.getID();
					setErrorType(testElement, "InvalidRefElement", "Invalid Context: Resource node (ID=" + targID + ") is not an ancestor of RefElement node (ID=" + refID
							+ ")", indent);
				}
			}
		}
		return isValid;
	}

	/**
	 * Check Resources if they are not in the vector of linked resources 'vLinkedResources', print the problem
	 *
	 * @param r - resource we check
	 * @param indent - indent for println() to console window
	 * @param testElement - test element of the XML output (if '-x' set) we "stand in"
	 * @return boolean - true if valid
	 */
	private boolean printResource(final JDFResource r, final int indent, final KElement testElement)
	{
		boolean isValid = true;

		if (vResources.contains(r))
		{
			if (!vLinkedResources.contains(r))
			{
				isValid = false;
				sysOut.println(indent(indent) + "!!! InValid Element: " + r.buildXPath(null, 1) + " " + r.getID() + " !!! ");

				sysOut.println(indent(indent) + "Unlinked Resource: " + r.getNodeName() + " " + r.getID());

				if (testElement != null)
				{
					setErrorType(testElement, "UnlinkedResource", "Resource is not linked or referenced");
				}
			}
			vResources.removeElement(r);
		}

		return isValid;
	}

	/**
	 * Check ResourceLinks if they are in the vector of bad resourceLinks 'vBadResourceLinks', print the problem
	 *
	 * @param rl - resource link we check
	 * @param level - validation level
	 * @param indent - indent for println() to console window
	 * @param testElement - test element of the XML output (if '-x' set) we "stand in"
	 * @return boolean - true if valid
	 */
	private boolean printResourceLink(final JDFResourceLink rl, final int indent, final KElement testElement)
	{
		boolean isValid = true;
		if (vBadResourceLinks.contains(rl))
		{
			final String rRef = rl.getrRef();
			final String resLinkName = rl.getNodeName();
			final String procUsage = (rl.hasAttribute(AttributeName.PROCESSUSAGE) && !rl.getProcessUsage().equals(JDFConstants.EMPTYSTRING)) ? "(ProcessUsage:"
					+ rl.getProcessUsage() + ")" : JDFConstants.EMPTYSTRING;

			final String errMessage = indent(indent) + "!!! InValid Element: " + rl.buildXPath(null, 1) + " !!! ";

			if (testElement != null)
			{
				setErrorType(testElement, "InvalidResourceLink", "Invalid ResourceLink");
				testElement.setAttribute("rRef", rRef);
				if (rl.getUsage() != null)
				{
					testElement.setAttribute("Usage", rl.getUsage().getName());
				}
				if (rl.hasAttribute(AttributeName.PROCESSUSAGE) && !rl.getProcessUsage().equals(JDFConstants.EMPTYSTRING))
				{
					testElement.setAttribute("ProcessUsage", rl.getProcessUsage());
				}
			}

			if (vBadID.contains(rRef))
			{
				isValid = false;
				sysOut.println(errMessage);
				sysOut.println(indent(indent) + "Invalid " + rl.getAttribute("Usage") + " ResLink: " + resLinkName + procUsage + "\nrRef points to the multiply defined ID=\""
						+ rRef + "\"");
				if (testElement != null)
				{
					setErrorType(testElement, "ResLinkMultipleID", "ResourceLink rRef points to the multiply defined ID:" + rRef);
				}
			}
			else
			// ID is ok
			{
				final JDFResource res = rl.getTarget();
				if (res == null)
				{ // print resource links which have no target
					isValid = false;
					sysOut.println(errMessage);
					sysOut.println(indent(indent) + "Dangling " + rl.getAttribute("Usage") + " ResLink: " + resLinkName + procUsage + " " + rRef);

					final JDFResource targRoot = rl.getLinkRoot();
					if (targRoot != null)
					{
						sysOut.println(indent(indent) + "Resource Link points to non-existing partition: " + rl.getPartMapVector().toString());
					}

					if (testElement != null)
					{
						if (targRoot == null)
						{
							setErrorType(testElement, "DanglingResLink", "Dangling ResourceLink; no Resource with ID = " + rRef);
						}
						else
						{
							setErrorType(testElement, "DanglingPartResLink", "ResourceLink points to nonexisting Partition. rRef=" + rRef);
							testElement.appendElement("Part").setAttributes(rl.getPartMapVector().elementAt(0));
							testElement.setAttribute("ResourcePartUsage", targRoot.getPartUsage().getName(), null);
						}
					}
				}
				else
				{
					// print resource links which have an invalid target
					isValid = false;
					sysOut.println(errMessage);
					sysOut.print(indent(indent) + "Invalid " + rl.getAttribute("Usage") + " ResLink: " + resLinkName + procUsage + " " + rRef + ". ");

					if (!rl.validResourcePosition())
					{
						final String errStr = "Points to: " + res.getNodeName() + ". Resource Node (ID=" + res.getParentJDF().getID() + ") is not an ancestor of ResLink Node (ID="
								+ rl.getParentJDF().getID() + ")";

						sysOut.print(errStr);
						setErrorType(testElement, "InvalidPosition", errStr);
					}
					else if (rl.isValid(level)) // but !isValidLink()
					{
						final String resName = res.getLocalName();
						final VString strLinkNames = res.getParentJDF().linkNames();
						if (strLinkNames != null && strLinkNames.indexOf(resName) == -1)
						{
							sysOut.print(" Unknown ResLink for this Type of Process");
							if (testElement != null)
							{
								setErrorType(testElement, "UnknownResourceLink", "Unknown ResourceLink for Process " + res.getParentJDF().getType());
							}
						}
						else
						{
							final JDFNode n = rl.getParentJDF();
							if (!n.isValidLink(level, rl))
							{
								boolean foundMissing = false;
								if (!rl.hasAttribute(AttributeName.PROCESSUSAGE))
								{
									final VString vMissingLinks = n.getMissingLinkVector(9999);
									if (vMissingLinks != null)
									{
										final int missLinkSize = vMissingLinks.size();
										for (int ii = 0; ii < missLinkSize; ii++)
										{
											final VString vs = new VString(StringUtil.tokenize(vMissingLinks.elementAt(ii), ":", false));
											if (vs.size() == 2)
											{
												final String linkName = vs.elementAt(0);
												if (linkName.equals(rl.getNodeName()))
												{
													sysOut.print(" (Potential missing ProcessUsage: " + vs.elementAt(1) + ")");
													setErrorType(testElement, "MissingProcessUsage", "Potential missing ProcessUsage: " + vs.elementAt(1));
													foundMissing = true;
												}
											}
										}
									}

									if (!foundMissing)
									{
										setErrorType(testElement, "UnknownResourceLink", "Incorrect ResourceLink @Usage or @ProcessUsage for Process " + n.getType());
									}
								}
							}
							else
							{
								sysOut.print(" (Potentially ResLink has a wrong cardinality)");
								if (testElement != null)
								{
									setErrorType(testElement, "ResLinkCardinality", "Potentially ResLink has a wrong cardinality");
								}
							}
						}
					}

					sysOut.println();
				}
			}

			vBadResourceLinks.removeElement(rl);
		}

		return isValid;
	}

	/**
	 * print Device Capabilities: executable nodes and bugReport if exist
	 *
	 * @param jdfRoot - node to test
	 * @param devCapFile - Device Capabilities fileName to test Node against
	 * @param testlists - Allowed or Present testLists (parameter '-P')
	 * @param level - validation level
	 * @param testElement - root of the XMLStructure for DeviceCap
	 */
	private void printDevCap(final JDFElement jdfRoot, final KElement testElement)
	{
		if (devCapFile == null || devCapFile.equals(JDFConstants.EMPTYSTRING) || !(jdfRoot instanceof JDFNode))
		{
			return;
		}

		final JDFNode jdfNode = (JDFNode) jdfRoot;
		final JDFDoc docDevCap = JDFDoc.parseFile(devCapFile);

		final JDFJMF jmfRoot = docDevCap.getJMFRoot();

		if (jmfRoot == null)
		{
			sysOut.println("JMFNode == null --> can't start Test");
			if (testElement != null)
			{
				final KElement kEl = testElement.appendElement("Error");
				kEl.setAttribute("Message", "JMFNode == null. Can't start Test");
			}
			return;
		}

		final JDFDeviceCap deviceCap = (JDFDeviceCap) jmfRoot.getChildByTagName("DeviceCap", null, 0, null, false, true);
		if (deviceCap == null)
		{
			sysOut.println("No DeviceCap element found --> can't start Test");
			if (testElement != null)
			{
				final KElement kEl = testElement.appendElement("Error");
				kEl.setAttribute("Message", "No DeviceCap element found. Can't start Test");
			}
			return;
		}

		deviceCap.setIgnoreExtensions(!bPrintNameSpace);
		sysOut.println("\n**********************************************************");
		sysOut.println("\nOutput of DeviceCapability test result follows:\n");

		final KElement execRoot = testElement.appendElement("ExecutableNodes");

		final VElement vExecNodes = deviceCap.getExecutableJDF(jdfNode, testlists, level);
		if (vExecNodes != null)
		{
			sysOut.println("\nExecutable Nodes are:");
			for (int j = 0; j < vExecNodes.size(); j++)
			{
				final JDFNode node = (JDFNode) vExecNodes.elementAt(j);
				final String id = node.getAttribute(AttributeName.ID);
				final String descrName = node.getAttribute(AttributeName.DESCRIPTIVENAME, null, null);
				final String xPath = node.buildXPath(null, 1);
				sysOut.println(xPath + " ID= " + id + " " + descrName);

				if (execRoot != null)
				{
					final KElement exNode = execRoot.appendElement("ExecutableNode");
					exNode.setAttribute("XPath", xPath);
					exNode.setAttribute("ID", id);
					exNode.setAttribute("DescriptiveName", descrName);
				}
			}
			sysOut.println();
		}
		else
		{
			sysOut.println("\nNo executable nodes that fit device capabilities were found");
			if (execRoot != null)
			{
				execRoot.setAttribute("Message", "No Executable Nodes were found");
			}
		}

		final XMLDoc testResult = deviceCap.getBadJDFInfo(jdfNode, testlists, level);
		if (testResult == null)
		{
			sysOut.println("\nResult of getBadJDFInfo: No bad JDF are found\n");
			final KElement bugReportRoot = testElement.appendElement("BugReport");
			bugReportRoot.setAttribute("Message", "No bad JDF were found");
		}
		else
		{
			testElement.copyElement(testResult.getRoot(), null);
		}
	}

	/**
	 * print Device Capabilities: executable nodes and bugReport if exist
	 *
	 * @param jdfRoot - node to test
	 * @param devCapFile - Device Capabilities fileName to test Node against
	 * @param testlists - Allowed or Present testLists (parameter '-P')
	 * @param level - validation level
	 * @param testElement - root of the XMLStructure for DeviceCap
	 */
	private void printJMFDevCap(final JDFElement jdfRoot, final KElement testElement)
	{
		if (devCapFile == null || devCapFile.equals(JDFConstants.EMPTYSTRING) || !(jdfRoot instanceof JDFJMF))
		{
			return;
		}

		final JDFJMF jdfNode = (JDFJMF) jdfRoot;
		final JDFParser p = new JDFParser();
		final JDFDoc docDevCap = p.parseFile(devCapFile);

		final JDFJMF jmfRoot = docDevCap.getJMFRoot();

		if (jmfRoot == null)
		{
			sysOut.println("JMFNode == null --> can't start Test");
			if (testElement != null)
			{
				final KElement kEl = testElement.appendElement("Error");
				kEl.setAttribute("Message", "JMFNode == null. Can't start Test");
			}
			return;
		}
		sysOut.println("\n**********************************************************");
		sysOut.println("\nOutput of DeviceCapability test result follows:\n");

		// final XMLDoc testResult = deviceCap.getBadJDFInfo(jdfNode, testlists,
		// level);

		final XMLDoc testResult = JDFDeviceCap.getJMFInfo(jdfNode, jmfRoot.getResponse(0), testlists, level, !bPrintNameSpace);

		if (testResult == null)
		{
			sysOut.println("\nResult of getBadJDFInfo: No bad JDF are found\n");
			final KElement bugReportRoot = testElement.appendElement("BugReport");
			bugReportRoot.setAttribute("Message", "No bad JDF were found");
		}
		else
		{
			final KElement jRoot = testElement.copyElement(testResult.getRoot(), null);
			if (!jRoot.getBoolAttribute("IsValid", null, true))
			{
				testElement.setAttribute("IsValid", false, null);
			}
			sysOut.println("\nResult of getBadJDFInfo: " + testResult.toString());

		}
	}

	/**
	 * Sets correct validation status of the nodes that has invalid entries. E.g. if ResourcePool has invalid children sets its status as invalid ["IsValid" =
	 * false].
	 *
	 * Check the Validation Status of all children for Mode [bQuiet=true] and if there are no invalid child nodes and it has no private contents - removes valid
	 * entries
	 *
	 * @param root - xml output root.
	 * @param bQuiet - mode is quiet set by "-q"
	 */
	private void removeValidEntriesIfQuiet(final KElement root, final boolean bRoot)
	{
		if (root == null)
		{
			return;
		}
		final JDFAttributeMap mInv = new JDFAttributeMap("IsValid", "false");
		final JDFAttributeMap mVal = new JDFAttributeMap("IsValid", "true");
		final VElement vEl = root.getChildElementVector_KElement(null, null, null, true, 0);
		boolean bValid = true;
		for (int i = vEl.size() - 1; i >= 0; i--)
		{
			final KElement el = vEl.elementAt(i);
			// ignore separationpools etc - only TestElement and TestAttribute
			// are important
			if (el == null || !el.getLocalName().startsWith("Test"))
			{
				continue;
			}

			if (el.hasAttribute("IsValid") && el.getBoolAttribute("IsValid", null, false)) // has
			// attr
			// "Valid"
			// and
			// Valid=
			// true
			{
				final KElement invChild = el.getChildByTagName(null, null, 0, mInv, false, true); // found
				// invalid
				// children
				if (invChild == null)
				{
					if (bQuiet)
					{
						final String eName = el.getLocalName();
						final JDFAttributeMap mPrivate = new JDFAttributeMap();
						mPrivate.put("HasPrivateContents", "true");
						mPrivate.put("IsPrivate", "true");

						removeValidEntriesIfQuiet(el, false);

						if (!el.getBoolAttribute("HasPrivateContents", null, false) && eName != "ForeignNSFound"
								&& (el.getChildByTagName(null, null, 0, mPrivate, false, false) == null))
						{
							el.deleteNode(); // if node is valid and bQuiet is
							// true - remove it
						}
					}
				}
				else
				{// if node "el" contains invalid nodes, set it as invalid.
					removeValidEntriesIfQuiet(el, false);
					final KElement valChild = el.getChildByTagName(null, null, 0, null, false, true);
					if (valChild != null && !el.hasAttribute("ErrorType"))
					{
						setErrorType(el, "InvalidElement", "Element is not valid, see child elements for details");
						el.setAttribute("IsValid", false, null);
						bValid = false;
					}
				}
			}
			else
			{
				bValid = false;
				KElement valChild = el.getChildByTagName(null, null, 0, mVal, false, true);
				if (valChild != null)
				{
					removeValidEntriesIfQuiet(el, false);
				}
				valChild = el.getChildByTagName(null, null, 0, mVal, false, true);
				if (valChild != null && !el.hasAttribute("ErrorType"))
				{
					setErrorType(el, "InvalidElement", "Element is not valid, see child elements for details");
				}
			}
		}
		if (!bValid && bRoot)
		{
			root.setAttribute("IsValid", false, null);
		}
	}

	/**
	 * Parses file and if schemaLocation is not null validates against Schema.
	 *
	 * @param xmlFile - File Name to parse
	 * @param schemaLocation - schema location
	 * @param errorHandler - error handler
	 * @return JDFDoc - parsed JDFDoc, if null - exception is caught
	 */
	private JDFDoc parseFile(final String xmlFile, final XMLErrorHandler errorHandler)
	{
		JDFDoc gd = null;
		final JDFParser p = JDFParserFactory.getFactory().get();
		p.setJDFSchemaLocation(UrlUtil.urlToFile(schemaLocation));
		p.m_ErrorHandler = errorHandler;

		gd = p.parseFile(xmlFile);
		if (gd == null && !bTryFormats)
		{
			sysOut.println("Error parsing File: " + xmlFile);
		}
		JDFParserFactory.getFactory().push(p);
		return gd;
	}

	/**
	 * this can be either a file or a network url
	 * @param _schemaLocation
	 */
	public void setJDFSchemaLocation(final String _schemaLocation)
	{
		schemaLocation = _schemaLocation;
	}

	/**
	 * @param _schemaLocation the schema location
	 */
	public void setJDFSchemaLocation(final File _schemaLocation)
	{
		final String fileToUrl = UrlUtil.fileToUrl(_schemaLocation, false);
		setJDFSchemaLocation(fileToUrl);
	}

	/**
	 * processes all files that have been placed into the public VString member JDFValidator.allFiles
	 *
	 * @return XMLDoc the xml output document
	 */
	public XMLDoc processAllFiles()
	{
		if (allFiles == null)
		{
			return null;
		}

		inOutputLoop = true;
		for (int arg = 0; arg < allFiles.size(); arg++)
		{
			String xmlFile = allFiles.get(arg);
			final VString vFiles = new VString();
			final File argFile = new File(xmlFile);
			if (argFile.isDirectory())
			{
				final File[] lFiles = argFile.listFiles();
				for (final File fil : lFiles)
				{
					if (fil.isDirectory())
					{
						continue;
					}
					if (fil.canRead())
					{
						vFiles.add(fil.getPath());
					}
				}
			}
			else if (xmlFile.toLowerCase().endsWith(".zip"))
			{
				processZipFile(argFile);
			}
			else if (xmlFile.toLowerCase().endsWith(".mjm"))
			{
				try
				{
					processMimeStream(new FileInputStream(argFile));
				}
				catch (final FileNotFoundException e)
				{
					// nop
				}
			}
			else
			{
				vFiles.add(xmlFile);
			}
			for (int ff = 0; ff < vFiles.size(); ff++)
			{
				xmlFile = vFiles.elementAt(ff);
				processSingleFile(xmlFile);
			}
		}
		inOutputLoop = false;
		finalizeOutput();
		return pOut;

	}

	private void finalizeOutput()
	{
		if (inOutputLoop)
		{
			return;
		}
		if (xmlOutputName != null && xmlOutputName.length() > 0)
		{
			if (xslStyleSheet != null)
			{
				pOut.setXSLTURL(xslStyleSheet);
				if (translation != null)
				{
					pOut.getRoot().setAttribute("Language", translation);
				}
			}
			pOut.write2File(xmlOutputName, 2, false);
		}
	}

	/**
	 * we may want to create something similar for a zip stream
	 *
	 * @param argFile
	 * @return XMLDoc the output file
	 */
	public XMLDoc processZipFile(final File argFile)
	{
		boolean bTryKeep = bTryFormats;

		try
		{
			final ZipFile zip = new ZipFile(argFile);
			final Enumeration<? extends ZipEntry> zipEnum = zip.entries();
			int n = 0;
			while (zipEnum.hasMoreElements())
			{
				// no use here - it looks as if the dom documents leak
				// if ((n % 10) == 9) // close all and gc!
				// {
				// zip.close();
				// zip = null;
				// System.gc();
				// zip = new ZipFile(argFile);
				//
				// zipEnum = zip.entries();
				// for (int i = 0; i < n; i++)
				// zipEnum.nextElement();
				// }

				final ZipEntry ze = zipEnum.nextElement();

				final String nam = ze.getName();
				// TODO handle non-ascii
				if (!ze.isDirectory())
				{
					if (!bQuiet)
					{
						sysOut.println(++n + " " + nam);
					}
					final InputStream inStream = zip.getInputStream(ze);
					processSingleStream(inStream, nam, null);
					if (inStream != null)
					{
						inStream.close();
					}

					bTryKeep = bTryKeep && bTryFormats;
				}
			}
		}
		catch (final ZipException e)
		{
			if (!bTryFormats)
			{
				KElement testFileRoot = pOut.getRoot().appendElement("TestFile");
				testFileRoot = testFileRoot.appendElement("Error");
				testFileRoot.setAttribute("Message", "Invalid zip file, Bailing out!");
				sysOut.println("Invalid zip file, Bailing out!");
			}
		}
		catch (final IOException e)
		{
			if (!bTryFormats)
			{
				KElement testFileRoot = pOut.getRoot().appendElement("TestFile");
				testFileRoot = testFileRoot.appendElement("Error");
				testFileRoot.setAttribute("Message", "I/O Exception on zip file, Bailing out!");
				sysOut.println("I/O Exception on zip file, Bailing out!");
			}
		}
		bTryFormats = bTryKeep;
		return pOut;
	}

	/**
	 * process a mime file
	 *
	 * @param argFile
	 * @return
	 */
	public XMLDoc processMimeStream(final InputStream inStream)
	{
		final Multipart multi = MimeUtil.getMultiPart(inStream);
		boolean bTryKeep = bTryFormats;
		if (multi == null)
		{
			return null;
		}
		int count;
		try
		{
			count = multi.getCount();
		}
		catch (final MessagingException e1)
		{
			// can't count - it must be crap
			return null;
		}

		for (int i = 0; i < count; i++)
		{
			try
			{
				final BodyPart bp = multi.getBodyPart(i);
				if (bp != null)
				{
					InputStream bpStream;
					bpStream = bp.getInputStream();
					final String fiName = bp.getFileName();
					final String contentType = bp.getContentType();
					if (MimeUtil.isJDFMimeType(contentType))
					{
						processSingleStream(bpStream, fiName, bp);
						bTryKeep = bTryKeep && bTryFormats;
					}
					else
					{
						sysOut.println("Mime extraction Skipping: " + fiName);
					}
				}
			}
			catch (final IOException e)
			{
				// nop and continue
			}
			catch (final MessagingException e)
			{
				// nop and continue
			}
		}
		bTryFormats = bTryKeep;
		return pOut;
	}

	/**
	 * process a single document as specified by doc if doc==null, reprocess the currently stored document
	 *
	 * @param doc the parsed document to process
	 * @return the xml output of the validation
	 */
	public XMLDoc processSingleDocument(final JDFDoc doc)
	{
		if (doc != null)
		{
			theDoc = doc;
		}
		return processSingleFile(null, null, null, null);
	}

	/**
	 * process a single document as specified by doc if doc==null, reprocess the currently stored document
	 *
	 * @param doc the parsed document to process
	 * @return the xml output of the validation
	 */
	public boolean isValid(final JDFDoc doc)
	{
		if (doc != null)
		{
			theDoc = doc;
		}
		final XMLDoc d = processSingleFile(null, null, null, null);
		if (d == null)
		{
			return false;
		}
		final KElement testFile = d.getRoot().getElement("TestFile", null, -1);
		if (testFile == null)
		{
			return false;
		}
		return "true".equals(testFile.getXPathAttribute("CheckJDFOutput/@IsValid", null));
	}

	/**
	 * process a single document as specified by doc
	 *
	 * @param stream the input stream
	 * @param url the url that the stream is sent to
	 *
	 * @return the xml output of the validation
	 */
	public XMLDoc processSingleURLStream(final InputStream stream, final String url)
	{
		theDoc = null;
		return processSingleFile(stream, url, null, null);
	}

	/**
	 * process a single document as specified by doc
	 *
	 * @param stream the input stream
	 * @param fileName the fileName that the stream originated from
	 * @param bp
	 *
	 * @return the xml output of the validation
	 */
	public XMLDoc processSingleStream(final InputStream stream, final String fileName, final BodyPart bp)
	{
		theDoc = null;
		return processSingleFile(stream, null, fileName, bp);
	}

	/**
	 * process a single file document as specified by fileName
	 *
	 * @param fileName the path of the file to parse and validate
	 * @return the xml output of the validation
	 */
	public XMLDoc processSingleFile(final String fileName)
	{
		theDoc = null;
		final File file = new File(fileName);
		bTryFormats = file.canRead();

		XMLDoc d = processSingleFile(null, null, fileName, null);
		if (!bTryFormats)
		{
			return d;
		}
		if (bTryFormats)
		{
			d = processZipFile(file);
		}
		if (bTryFormats)
		{
			final InputStream inStream = FileUtil.getBufferedInputStream(file);
			d = processMimeStream(inStream);
			StreamUtil.close(inStream);
		}
		return d;
	}

	/**
	 * processes a single file
	 *
	 * @param inStream
	 * @param url
	 * @param xmlFile
	 * @return
	 * @deprecated - use either processSingleDoc, processSingleStream or processSinglFile(String) this will be made private
	 */
	@Deprecated
	public XMLDoc processSingleFile(final InputStream inStream, final String url, final String xmlFile)
	{
		return processSingleFile(inStream, url, xmlFile, null);
	}

	/**
	 * processes a single file
	 *
	 * @param inStream
	 * @param url
	 * @param xmlFile
	 * @return
	 */
	protected XMLDoc processSingleFile(final InputStream inStream, final String url, final String xmlFile, final BodyPart bp)
	{
		KElement testFileRoot = pOut.getRoot().appendElement("TestFile");
		if (inStream == null && url == null && xmlFile == null && theDoc == null)
		{
			testFileRoot = testFileRoot.appendElement("Error");
			testFileRoot.setAttribute("Message", "No input URL, stream or file. Bailing out!");
			sysOut.println("No input URL, stream or file. Bailing out!");
			return pOut;
		}

		// reset all per file
		foundNameSpaces = new VString();
		vID.clear();
		vBadID.clear();
		vJobPartID.clear();
		vJobPartID.add(JDFConstants.EMPTYSTRING);
		vResources.clear();
		vLinkedResources.clear();
		vBadResourceLinks = new VElement();
		vSeparations = new VString();
		vSeparations2 = new VString();
		vColorPoolSeparations = new VString();

		// measure the time
		long lSchemaValidationTime = 0;
		long lCheckJDFTime = 0;
		long lTotalTime = 0;
		long lDevCapsTime = 0;
		final long lStartTime = System.currentTimeMillis();
		long lEndTime = 0;
		long lStartTime_SchemaValidation = 0;
		long lEndTime_SchemaValidation = 0;
		long lStartTime_InternalCheckJDF = 0;
		long lEndTime_InternalCheckJDF = 0;

		if (!bQuiet)
		{
			sysOut.println("\n**********************************************************");
			sysOut.println("       *** Checking " + xmlFile == null ? "JDFDoc" : xmlFile + " *** ");
			if (url != null && url.length() > 0)
			{
				sysOut.println("           " + url);
			}
			sysOut.println("**********************************************************\n");
		}

		if (xmlFile != null)
		{
			testFileRoot.setAttribute("FileName", xmlFile);
		}

		if (url != null && url.length() > 0)
		{
			testFileRoot.setAttribute("URL", url);
		}

		try
		{ // measure the time of parsing
			lStartTime_SchemaValidation = System.currentTimeMillis();

			if (bValidate)
			{
				sysOut.println("\n**********************************************************");
				sysOut.println("\nOutput of the XERCES schema validation follows:\n");
			}

			// Here we Parse file 'xmlFile' and validate it against Schema
			// 'schemaLocation'.
			// We will not use JDFDoc 'gd' for internal JDFValidator and DevCaps
			// test
			// but create new one JDFDoc 'doc' with schemaLocation = null,
			// otherwise we will have non-existent errors caused by schema
			// validation !!!
			if (theDoc == null)
			{
				if (inStream == null)
				{
					theDoc = parseFile(xmlFile, null);
				}
				else
				{
					final JDFParser p = new JDFParser();
					p.setJDFSchemaLocation(schemaLocation);
					theDoc = p.parseStream(inStream);
					if (theDoc != null)
					{
						theDoc.setBodyPart(bp);
					}
				}
			}
			bTryFormats = bTryFormats && theDoc == null;

			// measure the time of parsing
			lEndTime_SchemaValidation = System.currentTimeMillis();

			if (theDoc == null)
			{
				if (bTryFormats)
				{
					testFileRoot.deleteNode();
				}
				else
				{
					final KElement kEl = testFileRoot.appendElement("Error");
					kEl.setAttribute("Message", "File " + xmlFile + " not found or not parsed");
				}
			}
			else
			{
				final XMLDoc schemaValOutput = theDoc.getValidationResult();

				KElement schemaValRoot = null;
				if (schemaValOutput == null)
				{
					schemaValRoot = testFileRoot.appendElement("SchemaValidationOutput");
				}
				else
				{
					testFileRoot.copyElement(schemaValOutput.getRoot(), null);
					schemaValRoot = testFileRoot.getElement("SchemaValidationOutput", null, 0);
				}

				if (bTiming)
				{
					lSchemaValidationTime = lEndTime_SchemaValidation - lStartTime_SchemaValidation;

					if (schemaValRoot != null)
					{
						schemaValRoot.setAttribute("ValidationTime", lSchemaValidationTime + " ms");
					}
				}

				processURL(url);

				if (theDoc != null)
				{

					// measure the time of internal JDFValidator
					lStartTime_InternalCheckJDF = System.currentTimeMillis();

					if (bValidate)
					{
						sysOut.println("\n**********************************************************\n");
						sysOut.println("Output of checkJDF proper follows:\n");
					}

					final KElement checkJDFxmlRoot = testFileRoot.appendElement("CheckJDFOutput");

					// JDFDoc doc for internal JDFValidator and Test against
					// DevCaps. Important - WITHOUT SCHEMA VALIDATION!
					JDFNode root = theDoc.getJDFRoot();
					JDFJMF jmf = theDoc.getJMFRoot();

					if (root != null && jmf != null)
					{ // find the correct root, if there is a jdf and a jmf
						if (jmf.isAncestor(root))
						{
							root = null;
						}
						else
						{
							jmf = null;
						}
					}

					if (root == null)
					{ // no jdf, maybe jmf
						processNonJDF(url, xmlFile, checkJDFxmlRoot, jmf);
					}
					else
					{ // we have a jdf
						printMultipleIDs(url, xmlFile, root, checkJDFxmlRoot);

						final VElement allElms = root.getChildrenByTagName(null, null, null, false, true, 0);
						allElms.add(root);
						final int size = allElms.size();
						for (int i = 0; i < size; i++)
						{
							final KElement e = allElms.item(i);

							if (e.hasAttribute_KElement(AttributeName.ID, null, false))
							{
								final String id = e.getAttribute(AttributeName.ID, null, "");
								if (vID.contains(id))
								{
									vBadID.add(id);
								}
								else
								{
									vID.add(id);
								}
							}

							if (e.hasAttribute(AttributeName.SEPARATION))
							{
								final String separation = e.getAttribute(AttributeName.SEPARATION);
								if (!separation.equals("All"))
								{
									vSeparations.appendUnique(separation);
								}
							}

							if (e.getLocalName().equals(ElementName.SEPARATIONSPEC))
							{
								if (!e.getParentNode().getLocalName().equals(ElementName.COLORANTALIAS))
								{
									vSeparations.appendUnique(e.getAttribute(AttributeName.NAME));
								}
							}

							if (e.getLocalName().equals(ElementName.COLOR))
							{
								if (e.getParentNode().getLocalName().equals(ElementName.COLORPOOL))
								{
									vColorPoolSeparations.appendUnique(e.getAttribute(AttributeName.NAME));
								}
							}
							if (e instanceof JDFNode)
							{
								final JDFNode n = (JDFNode) e;
								final String jobPartID = n.getJobPartID(false);
								if (vJobPartID.contains(jobPartID))
								{
									vBadJobPartID.appendUnique(jobPartID);
								}
								else
								{
									vJobPartID.add(jobPartID);
								}
							}
						}

						vSeparations2 = new VString(vSeparations);
						vSeparations.removeStrings(vColorPoolSeparations, Integer.MAX_VALUE);
						vColorPoolSeparations.removeStrings(vSeparations2, Integer.MAX_VALUE);

						printBad(root, 0, checkJDFxmlRoot, true);

						lDevCapsTime = evalDevCaps(testFileRoot, lDevCapsTime, root);
					}

					// clean up the output
					removeValidEntriesIfQuiet(checkJDFxmlRoot, true);
					if (!checkJDFxmlRoot.hasAttributes() && !checkJDFxmlRoot.hasChildElements())
					{
						checkJDFxmlRoot.setAttribute("IsValid", true, null);
					}
					lEndTime_InternalCheckJDF = System.currentTimeMillis();

					if (bTiming)
					{
						lCheckJDFTime = lEndTime_InternalCheckJDF - lStartTime_InternalCheckJDF;
						checkJDFxmlRoot.setAttribute("InternalCheckJDFTime", lCheckJDFTime + " ms");
					}
				}
			}
		}
		catch (final JDFException e)
		{
			sysOut.println("Caught Exception: " + e.getMessage());
			final KElement er = testFileRoot.appendElement("Error");
			er.setAttribute("Message", "Caught Exception: " + e.getMessage());
		}

		sysOut.println("\n**********************************************************");

		// measure the total time for this File
		lEndTime = System.currentTimeMillis();

		if (bTiming)
		{
			sysOut.println("Schema Validation time = " + lSchemaValidationTime + " ms");
			sysOut.println("Internal checkJDF time = " + lCheckJDFTime + " ms");

			lTotalTime = lEndTime - lStartTime;
			sysOut.println("Total time = " + lTotalTime + " ms");

			testFileRoot.setAttribute("TotalTime", lTotalTime + " ms");
			if (lDevCapsTime > 0)
			{
				sysOut.println("DeviceCapabilities Test time = " + lDevCapsTime + " ms");
			}
		}
		finalizeOutput();
		return pOut;
	}

	private void processNonJDF(final String url, final String xmlFile, final KElement checkJDFxmlRoot, final JDFJMF jmf)
	{
		if (jmf != null)
		{
			printMultipleIDs(url, xmlFile, jmf, checkJDFxmlRoot);

			printBad(jmf, 0, checkJDFxmlRoot, true);
			printJMFDevCap(jmf, checkJDFxmlRoot);
		}
		else
		{
			checkJDFxmlRoot.setAttribute("FoundJDF", false, null);
		}
	}

	private long evalDevCaps(final KElement testFileRoot, final long lDevCapsTime, final JDFElement root)
	{
		long lDevCapsTimeLocal = lDevCapsTime;

		long lStartTime_TestDevCap;
		long lEndTime_TestDevCap;
		if (devCapFile != null)
		{
			// measure the time
			lStartTime_TestDevCap = System.currentTimeMillis();

			final KElement devCapTest = testFileRoot.appendElement("DeviceCapTest");

			printDevCap(root, devCapTest);

			lEndTime_TestDevCap = System.currentTimeMillis();
			lDevCapsTimeLocal = (lEndTime_TestDevCap - lStartTime_TestDevCap);
			if (bTiming && devCapTest != null)
			{
				devCapTest.setAttribute("DeviceCapTestTime", lDevCapsTimeLocal + " ms");
			}
		}

		return lDevCapsTimeLocal;
	}

	private void printMultipleIDs(final String url, final String xmlFile, final KElement root, final KElement outRoot)
	{
		KElement outRootLocal = outRoot;

		if (bMultiID)
		{
			if (bQuiet)
			{
				sysOut.println("\n**********************************************************");
				sysOut.println("       *** Checking " + xmlFile == null ? "JDFDoc" : xmlFile + " *** ");
				if (url != null && url.length() > 0)
				{
					sysOut.println("           " + url);
				}

				sysOut.println("**********************************************************\n");
			}

			vMultiID = root.getMultipleIDs("ID");
			if (vMultiID != null)
			{
				if (outRootLocal != null)
				{
					outRootLocal = outRootLocal.appendElement("MultiIDs");
					outRootLocal.setAttribute("IsValid", false, null);
				}

				sysOut.println("Multiple ID elements:\n");
				for (int i = 0; i < vMultiID.size(); i++)
				{
					processMultiID(root, outRootLocal, i);
				}
			}
			else
			{
				sysOut.println("No Multiple ID elements!");
			}
		}
	}

	private void processMultiID(final KElement root, final KElement outRootLocal, final int i)
	{
		final String id = vMultiID.get(i);
		final VElement v = root.getChildrenByTagName(null, null, new JDFAttributeMap("ID", id), false, true, 0);
		if (id.equals(root.getAttribute("ID")))
		{
			v.add(root);
		}

		for (int ii = 0; ii < v.size(); ii++)
		{
			final KElement e = v.item(ii);
			sysOut.println(id + " \t:" + e.buildXPath(null, 2));
			if (outRootLocal != null)
			{
				KElement idRoot = outRootLocal.getChildWithAttribute("MultiID", "ID", null, e.getAttribute("ID"), 0, true);
				if (idRoot == null)
				{
					idRoot = outRootLocal.appendElement("MultiID");
				}
				idRoot.setAttribute("ID", e.getAttribute("ID"));
				final KElement idInst = idRoot.appendElement("IDInstance");
				idInst.setAttribute("XPath", e.buildXPath(null, 2));
				idInst.setAttribute("Name", e.getNodeName());
			}
		}
	}

	// ////////////////////////////////////////////////////////////////////

	private void processURL(final String url)
	{
		if (url != null)
		{
			if (proxyHost != null)
			{
				System.setProperty("http.proxyHost", proxyHost);
				if (proxyPort == null)
				{
					proxyPort = "8080";
				}
				System.setProperty("http.proxyPort", proxyPort);
			}
			theDoc = (JDFDoc) theDoc.write2URL(url, theDoc.getContentType());
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// ////

	protected void setAllFiles(final MyArgs args)
	{
		for (int arg = 0; arg < args.nargs(); arg++)
		{
			final String xmlFile = args.argumentString(arg);
			addFile(xmlFile);
		}
	}

	public void addFile(final String xmlFile)
	{
		if (allFiles == null)
		{
			allFiles = new VString();
		}
		final File argFile = new File(xmlFile);
		if (argFile.canRead())
		{
			allFiles.appendUnique(xmlFile);
		}
		else
		{
			sysOut.println("File not found: " + argFile.getAbsolutePath());
			final KElement xmlRoot = pOut.getRoot();
			final KElement testFileRoot = xmlRoot.appendElement("TestFile");
			testFileRoot.setAttribute("FileName", xmlFile);
			testFileRoot.setAttribute("Message", "Could not find file: " + xmlFile);
		}
	}

	// ////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////
	/**
	 * This is just a quick and dirty class to centrally switch print on and off
	 *
	 * @author prosirai
	 *
	 */
	protected static class MySysOut
	{

		boolean wannaPrint = true;

		public void setPrint(final boolean b)
		{
			wannaPrint = b;
		}

		public void println(final String string)
		{
			if (wannaPrint)
			{
				System.out.println(string);
			}
		}

		public void println()
		{
			if (wannaPrint)
			{
				System.out.println();
			}
		}

		public void print(final String string)
		{
			if (wannaPrint)
			{
				System.out.println(string);
			}
		}
	}

	/**
	 * @return the bWarning
	 */
	public boolean isBWarning()
	{
		return bWarning;
	}

	/**
	 * @param warning the bWarning to set
	 */
	public void setWarning(final boolean warning)
	{
		bWarning = warning;
		level = EnumValidationLevel.setNoWarning(level, !warning);
	}

	/**
	 * @param validatorFactory the validatorFactory to set
	 */
	public void setValidatorFactory(final ICheckValidatorFactory pvalidatorFactory)
	{
		this.validatorFactory = pvalidatorFactory;
	}
}