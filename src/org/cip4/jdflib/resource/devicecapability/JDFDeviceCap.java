/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of 
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
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of 
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration 
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software 
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */

/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFDeviceCap.java
 *
 * @author Elena Skobchenko
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDevCaps.EnumContext;
import org.cip4.jdflib.auto.JDFAutoDeviceCap;
import org.cip4.jdflib.auto.JDFAutoMessageService.EnumJMFRole;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes.EnumFitsValue;
import org.cip4.jdflib.ifaces.ICapabilityElement;
import org.cip4.jdflib.ifaces.IDeviceCapable;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessageService;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.VectorMap;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * before Aug 10, 2009
 */
public class JDFDeviceCap extends JDFAutoDeviceCap implements IDeviceCapable
{
	/**
	 * 
	 */
	public static final String FITS_TYPE = "FitsType";
	private static final long serialVersionUID = 1L;
	private boolean ignoreExtensions = false;
	private boolean ignoreDefaults = false;
	private VectorMap setResMap = null;

	/**
	 * Constructor for JDFDeviceCap
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFDeviceCap(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDeviceCap
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFDeviceCap(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDeviceCap
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFDeviceCap(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[13];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.BOOLEANSTATE, 0x33333111);
		elemInfoTable[1] = new ElemInfoTable(ElementName.DATETIMESTATE, 0x33333111);
		elemInfoTable[2] = new ElemInfoTable(ElementName.DURATIONSTATE, 0x33333111);
		elemInfoTable[3] = new ElemInfoTable(ElementName.ENUMERATIONSTATE, 0x33333111);
		elemInfoTable[4] = new ElemInfoTable(ElementName.INTEGERSTATE, 0x33333111);
		elemInfoTable[5] = new ElemInfoTable(ElementName.MATRIXSTATE, 0x33333111);
		elemInfoTable[6] = new ElemInfoTable(ElementName.NAMESTATE, 0x33333111);
		elemInfoTable[7] = new ElemInfoTable(ElementName.NUMBERSTATE, 0x33333111);
		elemInfoTable[8] = new ElemInfoTable(ElementName.PDFPATHSTATE, 0x33333111);
		elemInfoTable[9] = new ElemInfoTable(ElementName.RECTANGLESTATE, 0x33333111);
		elemInfoTable[10] = new ElemInfoTable(ElementName.SHAPESTATE, 0x33333111);
		elemInfoTable[11] = new ElemInfoTable(ElementName.STRINGSTATE, 0x33333111);
		elemInfoTable[12] = new ElemInfoTable(ElementName.XYPAIRSTATE, 0x33333111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * toString - StringRepresentation of JDFNode
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFDeviceCap[  --> " + super.toString() + " ]";
	}

	/**
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * Aug 10, 2009
	 */
	@SuppressWarnings("unchecked")
	public static class EnumAvailability extends ValuedEnum
	{
		private static final long serialVersionUID = 1L;
		private static int m_startValue = 0;

		private EnumAvailability(final String name)
		{
			super(name, m_startValue++);
		}

		/**
		 * @param enumName
		 * @return
		 */
		public static EnumAvailability getEnum(final String enumName)
		{
			return (EnumAvailability) getEnum(EnumAvailability.class, enumName);
		}

		/**
		 * @param enumValue
		 * @return
		 */
		public static EnumAvailability getEnum(final int enumValue)
		{
			return (EnumAvailability) getEnum(EnumAvailability.class, enumValue);
		}

		/**
		 * @return
		 */
		public static Map getEnumMap()
		{
			return getEnumMap(EnumAvailability.class);
		}

		/**
		 * @return
		 */
		public static List getEnumList()
		{
			return getEnumList(EnumAvailability.class);
		}

		/**
		 * @return
		 */
		public static Iterator iterator()
		{
			return iterator(EnumAvailability.class);
		}

		/** * */
		public static final EnumAvailability NotInstalled = new EnumAvailability("NotInstalled");
		/** * */
		public static final EnumAvailability NotLicensed = new EnumAvailability("NotLicensed");
		/** * */
		public static final EnumAvailability Disabled = new EnumAvailability("Disabled");
		/** * */
		public static final EnumAvailability Installed = new EnumAvailability("Installed");
		/** * */
		public static final EnumAvailability Module = new EnumAvailability("Module");
	}

	/**
	 * Gets of this string attribute <code>TypeExpression</code> if it exists, otherwise returns the literal string defined in Types
	 * 
	 * @return String - TypeExpression attribute value
	 */
	@Override
	public String getTypeExpression()
	{
		if (hasAttribute(AttributeName.TYPEEXPRESSION))
		{
			return super.getTypeExpression();
		}
		return getAttribute(AttributeName.TYPES);
	}

	/**
	 * (9.2) get CombinedMethod attribute <code>CombinedMethod</code>
	 * 
	 * @return Vector of the enumerations
	 */
	@Override
	public Vector getCombinedMethod()
	{
		Vector<ValuedEnum> v = (Vector<ValuedEnum>) getEnumerationsAttribute(AttributeName.COMBINEDMETHOD, null, EnumCombinedMethod.None, false);
		if (v == null)
		{
			v = new Vector<ValuedEnum>();
			v.add(EnumCombinedMethod.None);
		}
		return v;
	}

	public final VString getNamePathVector()
	{
		final VString vResult = new VString();
		vResult.add(ElementName.JDF);

		return vResult;
	}

	/*
	 * // FitsValue Methods
	 */

	/**
	 * Gets of jdfRoot a vector of all executable nodes (jdf root or children nodes that this Device may execute)
	 * 
	 * @param jdfRoot the node we test
	 * @param testlists testlists that are specified for the State elements (FitsValue_Allowed or FitsValue_Present)<br>
	 * Will be used in fitsValue method of the State class.
	 * @param level validation level
	 * @return VElement - vector of executable JDFNodes, null if none found
	 */
	public final VElement getExecutableJDF(final JDFNode jdfRoot, final EnumFitsValue testlists, final EnumValidationLevel level)
	{
		final VElement execNodes = new VElement();
		final EnumExecutionPolicy execPolicy = getExecutionPolicy();

		// here vNodes is jdfRoot + all children
		VElement vNodes = null;
		if (execPolicy.equals(EnumExecutionPolicy.RootNode))
		{
			vNodes = new VElement();
			vNodes.add(jdfRoot);
		}
		else
		{
			vNodes = jdfRoot.getvJDFNode(null, null, false);
		}
		final XMLDoc d = new XMLDoc("dummy", null);
		for (int i = 0; i < vNodes.size(); i++)
		{
			final JDFNode n = (JDFNode) vNodes.elementAt(i);
			final KElement root = d.getRoot();

			try
			{
				final KElement nOutput = report(n, testlists, level, root); // if
				// report
				// throws
				// exception
				// - n
				// is
				// non-
				// executable
				// Node
				if (nOutput == null)
				{
					execNodes.addElement(n);
				}
			}
			catch (final JDFException jdfe)
			{
				// nop
			}
		}
		return execNodes.isEmpty() ? null : execNodes;
	}

	/**
	 * Composes a BugReport in XML form for the given JDFNode 'jdfRoot'. Gives a list of error messages for 'jdfRoot' and every child rejected Node.<br>
	 * Returns <code>null</code> if there are no errors.
	 * 
	 * @param jdfRoot the node to test
	 * @param testlists testlists that are specified for the State elements (FitsValue_Allowed or FitsValue_Present)<br>
	 * Will be used in fitsValue method of the State class.
	 * @param level validation level
	 * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc is null there are no errors.
	 */
	public final XMLDoc getBadJDFInfo(final JDFNode jdfRoot, final EnumFitsValue testlists, final EnumValidationLevel level)
	{
		XMLDoc bugReport = new XMLDoc("BugReport", null);
		final KElement outputRoot = bugReport.getRoot();
		final VElement vNodes = jdfRoot.getvJDFNode(null, null, false);

		final int size = vNodes.size();
		for (int i = 0; i < size; i++)
		{
			final JDFNode n = (JDFNode) vNodes.elementAt(i);
			KElement report = null;
			try
			{
				report = report(n, testlists, level, outputRoot);
			}
			catch (final JDFException jdfe)
			{
				report = outputRoot.appendElement("RejectedNode");
				report.setAttribute("CaughtException", jdfe.getMessage());
				report.setAttribute("ID", n.getID());
				report.setAttribute("XPath", n.buildXPath(null, 1));
			}
		}

		if (!outputRoot.hasChildNodes())
		{
			bugReport = null;
		}

		return bugReport;
	}

	/**
	 * Composes a BugReport in XML form for the given JMF message 'jmfRoot'. Gives a list of error messages for 'jmfRoot' and every child rejected element.<br>
	 * Returns <code>null</code> if there are no errors.
	 * 
	 * @param jdfRoot the node to test
	 * @param testlists testlists that are specified for the State elements (FitsValue_Allowed or FitsValue_Present)<br>
	 * Will be used in fitsValue method of the State class.
	 * @param level validation level
	 * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc is null there are no errors.
	 */
	static public XMLDoc getJMFInfo(final JDFJMF jmfRoot, final JDFResponse knownMessagesResp, final EnumFitsValue testlists, final EnumValidationLevel level, final boolean ignoreExtensions)
	{
		final XMLDoc bugReport = new XMLDoc("JMFReport", null);
		final KElement parentRoot = bugReport.getRoot();

		int nBad = 0;
		if (!jmfRoot.isValid(level))
		{
			parentRoot.setAttribute("IsValid", false, null);
		}
		final VElement messages = jmfRoot.getMessageVector(null, null);

		for (int i = 0; i < messages.size(); i++)
		{
			final KElement messageReport = parentRoot.appendElement("InvalidMessage");
			final JDFMessage m = (JDFMessage) messages.elementAt(i);
			final String typeJMF = m.getType();
			messageReport.setAttribute("MessageType", typeJMF);
			messageReport.setAttribute("XPath", m.buildXPath(null, 1));
			messageReport.setAttribute("ID", m.getID());
			final JDFMessageService ms = getMessageServiceForJMFType(m, knownMessagesResp);
			if (ms != null)
			{
				messageReport.setAttribute(FITS_TYPE, true, null);
				invalidDevCaps(ms, m, testlists, level, messageReport, ignoreExtensions);
				actionPoolReport(ms, m, messageReport);
			}
			else
			{
				messageReport.setAttribute(FITS_TYPE, false, null);
				// TODO root.setAttribute("CapsType",typeExp);
				messageReport.setAttribute("Message", "JMF  Type: " + typeJMF + " does not match capabilities type: ");
			}

			if (!messageReport.hasChildElements() && messageReport.getBoolAttribute(FITS_TYPE, null, true))
			{
				messageReport.renameElement("ValidMessage", null);
			}
			else
			{
				nBad++;
			}
		}
		parentRoot.setAttribute("IsValid", nBad == 0, null);
		return bugReport;
	}

	/**
	 * @param m the message to test
	 * @param knownMessagesResp the Response that contains the relevant devcap fo the jmf
	 * 
	 * @return the JMFMessageService element for this message based on family and type
	 */
	public static JDFMessageService getMessageServiceForJMFType(final JDFMessage m, final JDFResponse knownMessagesResp)
	{
		if (knownMessagesResp == null || !knownMessagesResp.getType().equals("KnownMessages") || m == null || m.getType().equals(""))
		{
			return null;
		}
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.TYPE, m.getType());
		// now add the family selection method
		final EnumFamily fam = m.getFamily();
		if (fam != null)
		{
			if (EnumFamily.Response.equals(fam))
			{
				map.put("JMFRole", EnumJMFRole.Sender);
			}
			else
			{
				map.put(fam.getName(), "true");
			}

		}
		return (JDFMessageService) knownMessagesResp.getChildByTagName(ElementName.MESSAGESERVICE, null, 0, map, true, true);
	}

	/**
	 * Checks if Device can execute the given JDFNode 'jdfRoot'.<br>
	 * First validates 'jdfRoot' and checks if its Type/Types attributes fit the values of DeviceCap/@Types and DeviceCap/@CombinedMethod. If Node is invalid or
	 * Type/Types don't fit it doesn't check it more detailed.<br>
	 * If Type/Types fit, the whole JDFNode - all elements and attributes - will be tested iot check if a Device can accept it.<br>
	 * This method composes a detailed report of the found errors in XML form, if jdfRoot is rejected.<br>
	 * If XMLDoc is null, there are no errors and 'jdfRoot' is accepted
	 * 
	 * @param jdfRoot the node to test
	 * @param fitsValue testlists that are specified for the State elements (FitsValue_Allowed or FitsValue_Present)<br>
	 * Will be used in fitsValue method of the State class.
	 * @param level validation level
	 * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc is <code>null</code> there are no errors, 'jdfRoot' is accepted
	 * 
	 * @throws JDFException if DeviceCapabilities file is invalid: illegal value of Types(TypeExpression) attribute (if CombinedMethod is None and Types
	 * contains more than 1 process)
	 * @throws JDFException if DeviceCapabilities file is invalid: illegal value of CombinedMethod attribute
	 */
	private final KElement report(final JDFNode jdfRoot, final EnumFitsValue fitsValue, final EnumValidationLevel level, final KElement parentRoot)
	{
		KElement root = parentRoot.appendElement("RejectedNode");
		root.setAttribute("XPath", jdfRoot.buildXPath(null, 1));
		root.setAttribute("ID", jdfRoot.getID());
		final String typeExp = getTypeExpression();

		if (!jdfRoot.isValid(level))
		{
			root.setAttribute("IsValid", false, null);
		}
		final String badState = misMatchingStates(jdfRoot);
		if (!matchesType(jdfRoot, true) || badState != null)
		{
			final String typeNode = jdfRoot.getType();
			reportTypeMatch(root, false, typeNode, typeExp);
			if (badState != null)
			{
				root.setAttribute("BadStateName", badState);
				root.setAttribute("BadStateValue", jdfRoot.getAttribute(badState, null, null));
				root.copyElement(getState(badState), null);
			}
			return root;
		}

		root = groupReport(jdfRoot, fitsValue, level, root);
		// TODO ???
		if (!root.hasChildElements() && root.getBoolAttribute(FITS_TYPE, null, true))
		{
			root.deleteNode();
			root = null;
		}
		return root;
	}

	/**
	 * @param jdfRoot
	 * @return
	 */
	private String misMatchingStates(final JDFNode jdfRoot)
	{
		final VElement vStates = getStates();
		if (vStates == null)
		{
			return null; // no additional matching
		}
		for (int i = 0; i < vStates.size(); i++)
		{
			final JDFAbstractState state = (JDFAbstractState) vStates.get(i);
			final String attName = state.getName();
			if (!state.fitsValue(jdfRoot.getAttribute(attName, null, null), EnumFitsValue.Present))
			{
				return attName;
			}
		}
		return null; // all matched
	}

	/**
	 * test whether a given node has the corect Types and Type Attribute
	 * 
	 * @param testRoot the JDF or JMF to test
	 * @param bLocal if true, only check the root of this, else check children as well
	 * 
	 * @return boolean - true if this DeviceCaps TypeExpression fits testRoot/@Type and testRoot/@Types
	 * 
	 */
	public boolean matchesType(final JDFNode testRoot, final boolean bLocal)
	{
		final VElement v = getMatchingTypeNodeVector(testRoot);
		if (v == null)
		{
			return false;
		}
		if (bLocal)
		{
			return v.contains(testRoot);
		}
		return true;
	}

	/**
	 * test whether a given node has the corect Types and Type Attribute
	 * 
	 * @param testRoot the JDF or JMF to test
	 * 
	 * @return VElement - the list of matching JDF nodes, null if none found
	 * 
	 */
	public VElement getMatchingTypeNodeVector(final JDFNode testRoot)
	{
		if (testRoot == null || !testRoot.hasAttribute(AttributeName.TYPE))
		{
			return null;
		}
		final VElement v = new VElement();
		final String typeNode = testRoot.getType();

		final Vector<ValuedEnum> vCombMethod = getCombinedMethod();
		final String typeExp = getTypeExpression();
		for (int j = 0; j < vCombMethod.size(); j++)
		{
			final EnumCombinedMethod combMethod = (EnumCombinedMethod) vCombMethod.elementAt(j);

			if (combMethod.equals(EnumCombinedMethod.None)) // node is an
			// individual
			// process
			{
				if (StringUtil.matches(typeNode, typeExp))
				{
					v.add(testRoot);
				}
			}
			else if (combMethod.equals(EnumCombinedMethod.Combined) || combMethod.equals(EnumCombinedMethod.CombinedProcessGroup) && typeNode.equals("Combined"))
			{
				if (fitsTypes(testRoot.getAllTypes(), false))
				{
					v.add(testRoot);
				}
			}
			else if (combMethod.equals(EnumCombinedMethod.GrayBox) || combMethod.equals(EnumCombinedMethod.CombinedProcessGroup) && typeNode.equals("ProcessGroup")
					&& !testRoot.isGroupNode())
			{
				if (fitsTypes(testRoot.getAllTypes(), true))
				{
					v.add(testRoot);
				}
			}
			else if (combMethod.equals(EnumCombinedMethod.ProcessGroup) || combMethod.equals(EnumCombinedMethod.CombinedProcessGroup) && typeNode.equals("ProcessGroup"))
			{
				final VElement vNodes = testRoot.getvJDFNode(null, null, false);
				final int size = vNodes.size();
				for (int i = 0; i < size - 1; i++) // note the 1 which skips
				// this
				{
					final JDFNode node = (JDFNode) vNodes.elementAt(i);
					if (node.isGroupNode())
					{
						final VElement matchingTypeNodeVector = getMatchingTypeNodeVector(node);
						if (matchingTypeNodeVector != null)
						{
							v.addAll(matchingTypeNodeVector);
						}
					}
					else if (fitsTypes(node.getAllTypes(), true))
					{
						v.add(node);
					}
				}
			}
			else
			{
				throw new JDFException("JDFDeviceCap.report: Invalid DeviceCap: illegal value of CombinedMethod attribute");
			}
		}
		v.unify();
		return v.size() == 0 ? null : v;
	}

	private void reportTypeMatch(final KElement report, final boolean matches, final String typeNode, final String typeExp)
	{
		report.setAttribute(FITS_TYPE, matches, null);
		report.setAttribute("NodeType", typeNode);
		report.setAttribute("CapsType", typeExp);
		report.copyAttribute(AttributeName.DESCRIPTIVENAME, this, null, null, null);
		report.setAttribute("CapXPath", buildXPath(null, 2));

		if (!matches)
		{
			report.setAttribute("Message", "Node Type: " + typeNode + " does not match capabilities type: " + typeExp);
		}
	}

	/**
	 * Tests JDFNode/@Types (or its equivalent of Types in the ProcessGroupNodes - the concatenated string of all Type attributes in the children Nodes) iot
	 * check whether it matches DeviceCap/@Types or DeviceCap/@TypeExpression
	 * 
	 * @param typesNode attribute Types of the tested JDFNode
	 * @param bSubset if true, a match is sufficient if a subset is specified
	 * @return boolean - true if JDFNode/@Types fits DeviceCap/@Types or DeviceCap/@TypeExpression
	 * @throws JDFException if DeviceCap is invalid: both @Types and @TypeExpression are missing
	 */
	private final boolean fitsTypes(final VString typesNode, final boolean bSubset)
	{
		if (typesNode == null || typesNode.isEmpty())
		{
			return false;
		}
		if (!bSubset)
		{
			if (hasAttribute(AttributeName.TYPEEXPRESSION))
			{
				final String typeExp = getTypeExpression();
				final String typesNodeStr = StringUtil.setvString(typesNode, JDFConstants.BLANK, null, null);
				return StringUtil.matches(typesNodeStr, typeExp);
			}
			return typesNode.equals(getTypes());
		}

		final VString dcTypes = getTypes();
		for (int i = 0; i < typesNode.size(); i++)
		{
			final String type = typesNode.stringAt(i);
			if (!dcTypes.contains(type))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether a device can execute the given ProcessGroup JDFNode 'jdfRoot' (JDFNode/@Type=ProcessGroup). If JDFNode/@Types fits DeviceCap/@Types, the
	 * whole JDFNode - all elements and attributes - is tested iot check whether a device can accept it.<br>
	 * Composes a detailed report of the found errors in XML form, if JDFNode is rejected.
	 * 
	 * @param jdfRoot the node to test
	 * @param testlists testlists that are specified for the State elements (FitsValue_Allowed or FitsValue_Present)<br>
	 * Will be used in fitsValue method of the State class.
	 * @param level validation level
	 * 
	 * @return XMLDoc - XMLDoc output of the error messages. <br>
	 * If XMLDoc is <code>null</code> there are no errors, 'jdfRoot' is accepted
	 */
	private final KElement groupReport(final JDFNode jdfRoot, final EnumFitsValue testlists, final EnumValidationLevel level, final KElement parentRoot)
	{
		parentRoot.setAttribute("XPath", jdfRoot.buildXPath(null, 1));
		parentRoot.setAttribute("ID", jdfRoot.getID());

		final VElement vNodes = getMatchingTypeNodeVector(jdfRoot);
		if (vNodes == null)
		{
			parentRoot.setAttribute(FITS_TYPE, false, null);
		}
		else
		{
			parentRoot.setAttribute(FITS_TYPE, true, null);

			// check the status of all child nodes
			for (int i = 0; i < vNodes.size() - 1; i++)
			{
				final JDFNode n = (JDFNode) vNodes.elementAt(i);
				final KElement childRoot = devCapsReport(n, testlists, level, parentRoot);
				if (childRoot != null)
				{
					childRoot.setAttribute("XPath", n.buildXPath(null, 1));
					childRoot.setAttribute("ID", n.getID());
				}
			}
			devCapsReport(jdfRoot, testlists, level, parentRoot);
		}
		return parentRoot;
	}

	/**
	 * devCapsReport - searches in JDFNode for appropriate elements for every DevCaps element that DeviceCap consists of, and tests them.<br>
	 * Composes a detailed report of the found errors in XML form.<br>
	 * If XMLDoc is <code>null</code> there are no errors
	 * 
	 * @param jdfRoot the node we test
	 * @param testlists testlists that are specified for the State elements (FitsValue_Allowed or FitsValue_Present)<br>
	 * Will be used in fitsValue method of the State class.
	 * @param level validation level
	 * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc is <code>null</code> there are no errors, 'jdfRoot' is accepted
	 */
	private final KElement devCapsReport(final JDFNode jdfRoot, final EnumFitsValue testlists, final EnumValidationLevel level, final KElement parentRoot)
	{
		// first test if there are in the JDFNode any ResourceLink or
		// NodeInfo/CustomerInfo
		// that are not described by DevCaps
		KElement root = parentRoot.appendElement("RejectedChildNode");

		if (!ignoreExtensions)
		{
			noFoundDevCaps(jdfRoot, root);
		}

		// if all resourceLinks and NodeInfo/CustomerInfo elements (optional)
		// are specified as DevCaps, we may test them.
		invalidDevCaps(this, jdfRoot, testlists, level, root, ignoreExtensions);
		actionPoolReport(this, jdfRoot, root);
		if (!root.hasChildElements())
		{
			root.deleteNode();
			root = null;
		}
		return root;
	}

	/**
	 * invalidDevCaps - tests if there are any invalid or missing Resources or NodeInfo/CustomerInfo elements in the JDFNode.<br>
	 * Composes a detailed report of the found errors in XML form. If XMLDoc is <code>null</code> there are no errors.
	 * 
	 * @param parent the devcaps parent element
	 * @param jdfRoot node or jmf message element we test
	 * @return boolean - true if invalid devcaps were found
	 * @throws JDFException if DeviceCap is invalid: has a wrong attribute Context value
	 */
	private static boolean invalidDevCaps(final KElement parent, final KElement jdfRoot, final EnumFitsValue testlists, final EnumValidationLevel level, final KElement parentReport, final boolean ignoreExtensions)
	{
		final KElement mrp = parentReport.appendElement((jdfRoot instanceof JDFNode) ? "MissingResources" : "MissingElements");
		final KElement irp = parentReport.appendElement((jdfRoot instanceof JDFNode) ? "InvalidResources" : "InvalidElements");
		final VElement vDevCaps = parent.getChildElementVector(ElementName.DEVCAPS, null, null, true, 0, false);
		final int size = vDevCaps.size();
		final HashSet goodElems = new HashSet();
		final HashMap badElems = new HashMap();

		for (int i = 0; i < size; i++)
		{
			final JDFDevCaps devCaps = (JDFDevCaps) vDevCaps.elementAt(i);
			devCaps.analyzeDevCaps(jdfRoot, testlists, level, mrp, irp, goodElems, badElems, ignoreExtensions);
		}

		final boolean bRet = mrp.hasChildElements() || irp.hasChildElements();
		if (!mrp.hasChildElements())
		{
			mrp.deleteNode();
		}

		if (!irp.hasChildElements())
		{
			irp.deleteNode();
		}

		return bRet;
	}

	/**
	 * missingDevCaps - tests if there are any Resources or NodeInfo/CustomerInfo elements in the JDFNode, which are not described by DevCaps.<br>
	 * If missing DevCaps are found, jdfRoot has elements unknown for this Device resources or elements.<br>
	 * Composes a detailed report of the found errors in XML form. If XMLDoc is <code>null</code> there are no errors.
	 * 
	 * @param jdfRoot node to test
	 * @return XMLDoc - XMLDoc output of the error messages. If XMLDoc is <code>null</code> there are no errors
	 */
	private final KElement noFoundDevCaps(final JDFNode jdfRoot, final KElement parentReport)
	{
		KElement root = parentReport.appendElement("UnknownResources");
		final VElement vLinks = jdfRoot.getResourceLinks(null);
		if (vLinks != null)
		{
			final int linkSize = vLinks.size();
			for (int j = 0; j < linkSize; j++)
			{
				final JDFResourceLink link = (JDFResourceLink) vLinks.elementAt(j);
				final String resName = link.getLinkedResourceName();
				final String processUsage = link.getProcessUsage();

				final JDFAttributeMap map = new JDFAttributeMap(AttributeName.NAME, resName);
				final VElement vDevCaps = getChildElementVector(ElementName.DEVCAPS, null, map, true, 0, false);

				boolean bFound = false;
				final int size = vDevCaps.size();
				for (int k = 0; k < size && !bFound; k++)
				{
					final JDFDevCaps dc = (JDFDevCaps) vDevCaps.elementAt(k);
					if ((!dc.hasAttribute(AttributeName.LINKUSAGE) || dc.getLinkUsage().getName().equals(link.getUsage().getName())) && (dc.getProcessUsage().equals(processUsage)))
					{
						bFound = true;
					}
				}
				if (!bFound)
				{ // no DevCaps with Name=resName and the corresponding LinkUsage
					// were found
					final KElement r = root.appendElement("UnknownResource");
					r.setAttribute("XPath", link.buildXPath(null, 1));
					r.setAttribute("Name", resName);
					if (link.hasAttribute(AttributeName.USAGE, null, false) && !link.getUsage().getName().equals("Unknown"))
					{
						r.setAttribute("Usage", link.getUsage().getName());
					}
					r.setAttribute("Message", "Found no DevCaps description for this resource");
				}
			}
		}

		checkNodeInfoCustomerInfo(jdfRoot, root, ElementName.NODEINFO);
		checkNodeInfoCustomerInfo(jdfRoot, root, ElementName.CUSTOMERINFO);
		checkNodeInfoCustomerInfo(jdfRoot, root, ElementName.STATUSPOOL);
		// checkNodeInfoCustomerInfo(jdfRoot, root, ElementName.AUDITPOOL);

		if (!root.hasChildElements())
		{
			root.deleteNode();
			root = null;
		}

		return root;
	}

	/**
	 * checkNodeInfoCustomerInfo - tests if there are JDFNode/NodeInfo or JDFNode/CustomerInfo elements that are not described by DevCaps. If missing DevCaps
	 * are found, jdfRoot has elements unknown for this Device resources or elements
	 * 
	 * @param jdfRoot node to test
	 * @param root root of the XMLDoc output
	 * @param elementName "NodeInfo" or "CustomerInfo" or "StatusPool"
	 */
	private final void checkNodeInfoCustomerInfo(final JDFNode jdfRoot, final KElement root, final String elementName)
	{
		final JDFAttributeMap map = new JDFAttributeMap();
		map.put(AttributeName.CONTEXT, EnumContext.Element.getName());
		map.put(AttributeName.NAME, elementName);
		final KElement devCaps = getChildByTagName(ElementName.DEVCAPS, null, 0, map, true, true);
		if ((jdfRoot.getElement(elementName, null, 0) != null) && (devCaps == null))
		{
			final KElement ue = root.appendElement("UnknownElement");
			ue.setAttribute("XPath", jdfRoot.getElement(elementName, null, 0).buildXPath(null, 1));
			ue.setAttribute("Name", elementName);
			ue.setAttribute("Message", "Found no DevCaps description with Context=\"Element\" for: " + elementName);
		}
		return;
	}

	/**
	 * actionPoolReport - tests if the JDFNode fits Actions from ActionPool of this DeviceCap.<br>
	 * Composes a detailed report of the found errors in XML form. If XMLDoc is <code>null</code> - there are no errors
	 * 
	 * @param jdfRoot node to test
	 * @return KElement - KElement output of the error messages. If KElement is <code>null</code> there are no errors, JDFNode fits the ActionPool of this
	 * DeviceCap and will be accepted by the device.
	 * @throws JDFException if DeviceCap is invalid: ActionPool refers to the non-existent TestPool
	 * @throws JDFException if DeviceCap is invalid: Action refers to the non-existent Test
	 */
	public static KElement actionPoolReport(final IDeviceCapable devCapable, final JDFElement jdfRootorMess, final KElement parentReport)
	{
		KElement root = parentReport.appendElement("ActionPoolReport");
		final JDFActionPool actionPool = devCapable.getActionPool();
		if (actionPool != null)
		{
			final JDFTestPool testPool = devCapable.getTestPool();
			if (testPool == null)
			{
				throw new JDFException("JDFDeviceCap.actionPoolReport: TestPool is required but was not found. Attempt to operate on a null element");
			}
			final VElement vActions = actionPool.getChildElementVector(ElementName.ACTION, null, null, true, 0, false);
			final VElement allElms = jdfRootorMess.getChildrenByTagName(null, null, null, false, true, 0);
			allElms.add(jdfRootorMess); // needed for local JDF test
			if (jdfRootorMess instanceof JDFMessage)
			{
				final JDFJMF jmf = jdfRootorMess.getJMFRoot();
				if (jmf != null)
				{
					allElms.add(jmf);
				}
			}
			final int elmSize = allElms.size();
			final int actionSize = vActions.size();
			for (int i = 0; i < elmSize; i++)
			{
				final KElement e = allElms.item(i);
				for (int j = 0; j < actionSize; j++)
				{
					final JDFAction action = (JDFAction) vActions.elementAt(j);
					final JDFTest test = action.getTest();
					if (test == null)
					{
						continue;
						// TODO add report of snafu
						// throw new JDFException(
						// "JDFDeviceCap.actionPoolReport: Test with ID=" +
						// action.getTestRef() +
						// " was not found. Attempt to operate on a null element"
						// );
					}
					// loop to check whether the test even applies
					if (!test.fitsContext(e))
					{
						continue;
					}

					final KElement ar = root.appendElement("ActionReport");
					if (test.fitsJDF(e, ar)) // If the Test referenced by
					// TestRef evaluates to true
					// the combination
					{ // of processes and attribute values described is not
						// allowed
						KElement arl = root.getChildWithAttribute("ActionReportList", "ID", null, action.getID(), 0, true);

						if (arl == null)
						{
							arl = root.appendElement("ActionReportList");
							arl.setAttribute("ID", action.getID());
							arl.setAttribute("Severity", action.getSeverity().getName());
						}

						arl.moveElement(ar, null);
						ar.setAttribute("XPath", e.buildXPath(null, 1));

						// __Lena__ TBD choose Loc element according to the
						// language settings
						final JDFLoc loc = action.getLoc(0);
						if (loc != null)
						{
							ar.setAttribute("Message", loc.getValue());
							final String helpText = loc.getHelpText();
							if (helpText.length() != 0)
							{
								ar.setAttribute("Help", helpText);
							}
						}
					}
					else
					{
						ar.deleteNode(); // zapp it
					}
				}
			}
		}
		root = cleanActionPoolReport(root);
		return root;
	}

	/**
	 * remove duplicate entries that are parents of lower level entries
	 * 
	 * @param testResult XMLDoc to clean
	 * @return XMLDoc - the cleaned doc
	 */
	private static KElement cleanActionPoolReport(final KElement actionPoolReport)
	{
		KElement actionPoolReportLocal = actionPoolReport;

		if (actionPoolReportLocal != null)
		{
			final VElement vARL = actionPoolReportLocal.getChildElementVector("ActionReportList", null, null, true, 0, false);
			for (int i = 0; i < vARL.size(); i++)
			{
				final VElement actionReportList = vARL.item(i).getChildElementVector("ActionReport", null, null, true, 0, false);
				for (int j = 1; j < actionReportList.size(); j++)
				{
					final KElement e1 = actionReportList.item(j);
					for (int k = 0; k < j; k++)
					{
						final KElement e2 = actionReportList.item(k);
						if (e2 == null)
						{
							continue;
						}
						if (e2.getAttribute("XPath").startsWith(e1.getAttribute("XPath")))
						{
							e1.deleteNode();
							actionReportList.setElementAt(null, j);
							break;
						}
						else if (e1.getAttribute("XPath").startsWith(e2.getAttribute("XPath")))
						{
							e2.deleteNode();
							actionReportList.setElementAt(null, k);
						}
					}
				}
			}

			if (!actionPoolReportLocal.hasChildElements())
			{
				actionPoolReportLocal.deleteNode();
				actionPoolReportLocal = null;
			}
		}
		return actionPoolReportLocal;
	}

	// //////////////////////////////////////////////////

	/**
	 * creates and links devcaps to modules
	 * 
	 * @param includeNameExpression regexp of names to include
	 */
	public void createModuleCaps(final String includeNameExpression)
	{
		final VElement devcaps = getChildElementVector(ElementName.DEVCAPS, null, null, true, 0, true);
		if (devcaps != null)
		{
			final int siz = devcaps.size();
			for (int i = 0; i < siz; i++)
			{
				final JDFDevCaps dcs = (JDFDevCaps) devcaps.elementAt(i);

				final String _name = dcs.getName();
				if (StringUtil.matches(_name, includeNameExpression))
				{
					final JDFModuleCap mc = dcs.appendModuleRef("Module_" + _name);
					mc.setAvailability(EnumAvailability.Installed);
					mc.setDescriptiveName("Module that implements the resource: " + _name);
				}
			}
		}
	}

	/**
	 * set the defaults of node to the values defined in the child DevCap and State elements
	 * 
	 * @param node the JDFNode in which to set defaults
	 * @param bLocal if true, set only in the local node, else recurse children
	 * @param bAll if false, only add if minOccurs>=1 and required=true or a default exists
	 */
	public boolean setDefaultsFromCaps(final JDFNode node, final boolean bLocal, final boolean bAll)
	{
		boolean success = false;
		final boolean bTestTypes = node.hasAttribute(AttributeName.TYPE);
		if (bLocal == false)
		{
			final VElement vNode = node.getvJDFNode(null, null, false);
			for (int i = 0; i < vNode.size(); i++)
			{
				final JDFNode nod = (JDFNode) vNode.elementAt(i);
				success = setDefaultsFromCaps(nod, true, bAll) || success;
			}
			return success;
		}
		if (bTestTypes && !matchesType(node, true))
		{
			return false;
		}
		if (hasAttribute(AttributeName.TYPES))
		{
			node.setType(EnumType.ProcessGroup);
			final Vector<ValuedEnum> cm = getCombinedMethod();
			if (cm != null && cm.contains(EnumCombinedMethod.Combined))
			{
				node.setType(EnumType.Combined);
			}

			node.setTypes(getTypes());
		}
		addResourcesFromDevCaps(node, bAll);
		int i;
		final VElement vDevCaps = getChildElementVector(ElementName.DEVCAPS, null, null, true, 99999, false);
		// step 1, create all missing resources etc
		final int size = vDevCaps.size();
		for (i = 0; i < size; i++)
		{
			final JDFDevCaps dcs = (JDFDevCaps) vDevCaps.elementAt(i);
			success = dcs.setDefaultsFromCaps(node, bAll) || success;
		}

		final VElement vStates = getStates();
		for (i = 0; i < vStates.size(); i++)
		{
			final JDFAbstractState state = (JDFAbstractState) vStates.elementAt(i);
			success = state.setDefaultsFromCaps(node, bAll) || success;
		}

		return success;
	}

	/**
	 * get all direct state elements of <this>
	 * 
	 * @return the vector of state elements
	 */
	public VElement getStates()
	{
		final VElement vStates = getChildrenByTagName(null, null, null, true, true, 0);
		for (int ii = vStates.size() - 1; ii >= 0; ii--)
		{
			if (!(vStates.elementAt(ii) instanceof JDFAbstractState))
			{
				vStates.remove(ii);
			}
		}
		return vStates;
	}

	/**
	 * add any missing resources, links or elements that are described by devcaps elements
	 * 
	 * @param bAll if false, only add if minOccurs>=1 and required=true or a default exists
	 * @param node
	 */
	private void addResourcesFromDevCaps(final JDFNode node, final boolean bAll)
	{
		final VElement vDevCaps = getChildElementVector(ElementName.DEVCAPS, null, null, true, 99999, false);
		// step 1, create all missing resources etc
		setResMap = new VectorMap();
		final int size = vDevCaps.size();
		// loop over all resources first so that we have hooks for the links
		for (int i = 0; i < size; i++)
		{
			final JDFDevCaps dcs = (JDFDevCaps) vDevCaps.elementAt(i);
			dcs.appendMatchingElementsToNode(node, bAll, setResMap, false);
		}
		// now loop over all context=link
		for (int i = 0; i < size; i++)
		{
			final JDFDevCaps dcs = (JDFDevCaps) vDevCaps.elementAt(i);
			dcs.appendMatchingElementsToNode(node, bAll, setResMap, true);
		}

	}

	/**
	 * get a DevCaps element by name and further restrictions. If an Enumerative restriction is null, the restriction is not checked.
	 * 
	 * @param devCapsName the Name attribute of the DevCaps
	 * @param context the Context attribute of the DevCaps
	 * @param linkUsage the LinkUsage attribute of the DevCaps
	 * @param processUsage the ProcessUsage attribute of the DevCaps
	 * @param iSkip the iSkip'th matching DevCaps
	 * @return JDFDevCaps the matching DevCaps, null if not there
	 */
	public JDFDevCaps getDevCapsByName(final String devCapsName, final EnumContext context, final EnumUsage linkUsage, final EnumProcessUsage processUsage, final int iSkip)
	{
		final JDFAttributeMap map = new JDFAttributeMap(AttributeName.NAME, devCapsName);
		if (context != null)
		{
			map.put(AttributeName.CONTEXT, context.getName());
		}
		if (linkUsage != null)
		{
			map.put(AttributeName.LINKUSAGE, linkUsage.getName());
		}
		if (processUsage != null)
		{
			map.put(AttributeName.PROCESSUSAGE, processUsage.getName());
		}
		return (JDFDevCaps) getChildByTagName(ElementName.DEVCAPS, null, iSkip, map, true, true);
	}

	/**
	 * set attribute <code>CombinedMethod</code> to an individual method
	 * 
	 * @param method the individual combined method to set
	 */
	public void setCombinedMethod(final EnumCombinedMethod method)
	{
		setAttribute(AttributeName.COMBINEDMETHOD, method.getName(), null);
	}

	/**
	 * set attribute <code>CombinedMethod</code> to an individual method
	 * 
	 * @param method the individual combined method to set
	 */
	@Override
	public void setCombinedMethod(final Vector vMethod)
	{
		setEnumerationsAttribute(AttributeName.COMBINEDMETHOD, vMethod, null);
	}

	/**
	 * @return the ignoreExtensions
	 */
	public boolean isIgnoreExtensions()
	{
		return ignoreExtensions;
	}

	/**
	 * @param _ignoreExtensions the ignoreExtensions to set
	 */
	public void setIgnoreExtensions(final boolean _ignoreExtensions)
	{
		this.ignoreExtensions = _ignoreExtensions;
	}

	/**
	 * @return the ignoreDefaults
	 */
	public boolean isIgnoreDefaults()
	{
		return ignoreDefaults;
	}

	/**
	 * @param _ignoreDefaults the ignoreDefaults to set
	 */
	public void setIgnoreDefaults(final boolean _ignoreDefaults)
	{
		this.ignoreDefaults = _ignoreDefaults;
	}

	/**
	 * 
	 * @see org.cip4.jdflib.ifaces.IDeviceCapable#getTargetCap(java.lang.String)
	 */
	public ICapabilityElement getTargetCap(final String id)
	{
		final KElement e = getTarget(id, null);
		if (e instanceof ICapabilityElement)
		{
			return (ICapabilityElement) e;
		}
		return null;
	}

	/**
	 * appends a BooleanState with @Name="name"
	 * 
	 * @param nam the name attribute of the newly appended BooleanState
	 * @return JDFBooleanState: the newly appended BooleanState
	 */
	public JDFBooleanState appendBooleanState(final String nam)
	{
		final JDFBooleanState s = (JDFBooleanState) appendElement(ElementName.BOOLEANSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a NumberState with @Name="name"
	 * 
	 * @param nam the name attribute of the newly appended NumberState
	 * @return JDFNumberState: the newly appended NumberState
	 */
	public JDFEnumerationState appendEnumerationState(final String nam)
	{
		final JDFEnumerationState s = (JDFEnumerationState) appendElement(ElementName.ENUMERATIONSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends an IntegerState with @Name="name"
	 * 
	 * @param nam the Name attribute of the newly appended IntegerState
	 * @return JDFIntegerState: the newly appended IntegerState
	 */
	public JDFIntegerState appendIntegerState(final String nam)
	{
		final JDFIntegerState s = (JDFIntegerState) appendElement(ElementName.INTEGERSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a NameState with @Name="name"
	 * 
	 * @param nam the name attribute of the newly appended NameState
	 * @return JDFNameState: the newly appended NameState
	 */
	public JDFNameState appendNameState(final String nam)
	{
		final JDFNameState s = (JDFNameState) appendElement(ElementName.NAMESTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * appends a StringState with @Name="name"
	 * 
	 * @param nam the Name attribute of the newly appended StringState
	 * @return JDFStringState: the newly appended StringState
	 */
	public JDFStringState appendStringState(final String nam)
	{
		final JDFStringState s = (JDFStringState) appendElement(ElementName.STRINGSTATE, null);
		s.setName(nam);
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing BooleanState with @Name="name"
	 * 
	 * @param nam the Name attribute of the newly appended BooleanState
	 * @return JDFBooleanState: the existing BooleanState
	 */
	public JDFBooleanState getBooleanState(final String nam)
	{
		return (JDFBooleanState) getChildWithAttribute(ElementName.BOOLEANSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a NumberState with @Name="name", appends it if it does not exist
	 * 
	 * @param nam the name attribute of the newly appended NumberState
	 * @return JDFNumberState: the existing or newly appended NumberState
	 */
	public JDFBooleanState getCreateBooleanState(final String nam)
	{
		JDFBooleanState s = getBooleanState(nam);
		if (s == null)
		{
			s = appendBooleanState(nam);
		}
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a EnumerationState with @Name="name", appends it if it does not exist
	 * 
	 * @param nam the name attribute of the newly appended EnumerationState
	 * @return JDFEnumerationState the existing or newly appended EnumerationState
	 */
	public JDFEnumerationState getCreateEnumerationState(final String nam)
	{
		JDFEnumerationState s = getEnumerationState(nam);
		if (s == null)
		{
			s = appendEnumerationState(nam);
		}
		return s;
	}

	/**
	 * gets an IntegerState with @Name="name", appends it if it does not yet exist
	 * 
	 * @param nam the name attribute of the newly appended IntegerState
	 * @return JDFIntegerState: the existing or newly appended IntegerState
	 */
	public JDFIntegerState getCreateIntegerState(final String nam)
	{
		JDFIntegerState s = getIntegerState(nam);
		if (s == null)
		{
			s = appendIntegerState(nam);
		}
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a NameState with @Name="name", appends it if it does not exist
	 * 
	 * @param nam the name attribute of the newly appended NameState
	 * @return JDFNameState: the existing or newly appended NameState
	 */
	public JDFNameState getCreateNameState(final String nam)
	{
		JDFNameState s = getNameState(nam);
		if (s == null)
		{
			s = appendNameState(nam);
		}
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets a StringState with @Name="name", appends it if it does not yet exist
	 * 
	 * @param nam the Name attribute of the newly appended StringState
	 * @return JDFStringState: the existing or newly appended StringState
	 */
	public JDFStringState getCreateStringState(final String nam)
	{
		JDFStringState s = getStringState(nam);
		if (s == null)
		{
			s = appendStringState(nam);
		}
		return s;
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing EnumerationState with @Name="name"
	 * 
	 * @param nam the Name attribute of the newly appended EnumerationState
	 * @return JDFEnumerationState: the existing EnumerationState
	 */
	public JDFEnumerationState getEnumerationState(final String nam)
	{
		return (JDFEnumerationState) getChildWithAttribute(ElementName.ENUMERATIONSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing IntegerState with @Name="name"
	 * 
	 * @param nam the name attribute of the newly appended IntegerState
	 * @return JDFIntegerState: the existing IntegerState
	 */
	public JDFIntegerState getIntegerState(final String nam)
	{
		return (JDFIntegerState) getChildWithAttribute(ElementName.INTEGERSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing NameState with @Name="name"
	 * 
	 * @param nam the Name attribute of the newly appended NameState
	 * @return JDFNameState: the existing NameState
	 */
	public JDFNameState getNameState(final String nam)
	{
		return (JDFNameState) getChildWithAttribute(ElementName.NAMESTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////
	/**
	 * gets an existing NumberState with @Name="name"
	 * 
	 * @param nam the Name attribute of the newly appended NumberState
	 * @return JDFNumberState: the existing NumberState
	 */
	public JDFNumberState getNumberState(final String nam)
	{
		return (JDFNumberState) getChildWithAttribute(ElementName.NUMBERSTATE, AttributeName.NAME, null, nam, 0, true);
	}

	// ///////////////////////////////////////////////////////////////////

	/**
	 * gets an existing State with @Name="name"
	 * 
	 * @param nam the Name attribute of the newly appended StringState
	 * @return JDFStringState: the existing StringState
	 */
	public JDFAbstractState getState(final String nam)
	{
		for (int i = 0; true; i++)
		{
			final KElement e = getChildWithAttribute(null, AttributeName.NAME, null, nam, i, true);
			if (e == null)
			{
				break;
			}
			if (e instanceof JDFAbstractState)
			{
				return (JDFAbstractState) e;
			}
		}
		return null;
	}

	/**
	 * gets an existing StringState with @Name="name"
	 * 
	 * @param nam the Name attribute of the newly appended StringState
	 * @return JDFStringState: the existing StringState
	 */
	public JDFStringState getStringState(final String nam)
	{
		return (JDFStringState) getChildWithAttribute(ElementName.STRINGSTATE, AttributeName.NAME, null, nam, 0, true);
	}

}
