/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.elementwalker.FixVersion;
import org.cip4.jdflib.elementwalker.IWalker;
import org.cip4.jdflib.elementwalker.PackageElementWalker;
import org.cip4.jdflib.elementwalker.RemoveEmpty;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.XJMFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.RemoveEmptyXJDF;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG <br/>
 *         conversion class to convert JDF 1.x to the experimental JDF 2.0<br/>
 *         very experimental and subject to change without notice
 *
 *         15.01.2009
 */
public class JDFToXJDF extends PackageElementWalker
{

	/**
	 *
	 */
	public JDFToXJDF()
	{
		super(new BaseWalkerFactory());
		wantProduct = true;
		rootID = null;
		KElement.uniqueID(-1000); // don't start at zero to avoid collisions in short ID scenarios
		componentProductMap = new JDFAttributeMap();
		resourceAlias = new HashSet<>();
		wantDependent = true;
	}

	/**
	 * @return the resourceAlias
	 */
	protected Set<String> getResourceAlias()
	{
		return resourceAlias;
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.PackageElementWalker#constructWalker(java.lang.String)
	 */
	@Override
	protected BaseWalker constructWalker(final String name)
	{
		final WalkElement constructWalker = (WalkElement) super.constructWalker(name);
		if (constructWalker != null)
			constructWalker.setParent(this);
		return constructWalker;
	}

	/**
	 *
	 *
	 * @param r
	 * @return
	 */
	protected String getClassName(final JDFResource r)
	{
		final WalkResource w = getWalker(r);
		return (w == null) ? null : w.getClassName(r);
	}

	/**
	 *
	 * @param r
	 * @return
	 */
	protected WalkResource getWalker(final JDFResource r)
	{
		if (r == null)
			return null;
		final IWalker walker = theFactory.getWalker(r);
		return (walker instanceof WalkResource) ? (WalkResource) walker : null;
	}

	/**
	 *
	 * if true, add a modified audit
	 *
	 * @param trackAudits
	 */
	public void setTrackAudits(final boolean trackAudits)
	{
		this.trackAudits = trackAudits;
	}

	/**
	 * the root node name if NOT typesafe
	 */
	public final static String rootName = XJDFConstants.XJDF;
	/**
	 * the root JMF name
	 */
	public final static String rootJMF = "JMF";

	/**
	 * returns the official JDF schema URI for 2.0
	 *
	 *
	 * @return the URL that fits to majorVersion and minorVersion - null if not supported
	 */
	public static String getSchemaURL()
	{
		return JDFElement.getSchemaURL(2, 0);
	}

	/**
	 *
	 */
	private boolean trackAudits = true;
	protected KElement newRoot = null;
	protected JDFNode oldRoot = null;
	protected Set<String> first = new HashSet<>();
	/**
	 * if true merge explicitly call out waste
	 */
	private boolean bExplicitWaste = true;

	/**
	 * Getter for bExplicitWaste attribute.
	 *
	 * @return the bExplicitWaste
	 */
	public boolean isExplicitWaste()
	{
		return bExplicitWaste;
	}

	/**
	 * Setter for bExplicitWaste attribute. if true PartAmount has explicit amounts for good and waste rather than partitions
	 *
	 * @param bExplicitWaste the bExplicitWaste to set
	 */
	public void setExplicitWaste(final boolean bExplicitWaste)
	{
		this.bExplicitWaste = bExplicitWaste;
	}

	/**
	 * if true we retain as much of the initial JDF details as possible and do as little nice to have conversion as possible this setting is useful e.g. for jdf xml display
	 */
	private boolean bRetainAll = false;
	private boolean bCleanup = true;
	/**
	 * if true merge stripping and layout
	 */
	private boolean bMergeLayout = true;
	/**
	 * if true merge layoutprep and stripping (may cascade to layout)
	 */
	private boolean bMergeLayoutPrep = true;
	/**
	 * if true merge runlist and LayoutElement
	 */
	private boolean bMergeRunList = true;
	/**
	 * set to retain spawn information
	 */
	private boolean bRetainSpawnInfo = false;
	/**
	 * set to update single node update
	 */
	private boolean bSingleNode = false;
	/**
	 * set to update version stamps
	 */
	private boolean bUpdateVersion = true;
	/**
	 * set to define type safe messages
	 */
	private boolean bTypeSafeMessage = true;
	/**
	 * set to define one type for query, command and registration
	 */
	private boolean bAbstractMessage = false;

	/**
	 * if true, spans are made to a simple attribute rather than retained as span
	 */
	private boolean bSpanAsAttribute = true;
	/**
	 * if true, Intents are partitioned
	 */
	private boolean bIntentPartition = false;
	/**
	 * if true, parameters and resources are generated; else everything is a resource
	 */
	private boolean bParameterSet = false;

	/**
	 * @return the bParameterSet
	 */
	public boolean isParameterSet()
	{
		return bParameterSet;
	}

	/**
	 * @param bParameterSet the bParameterSet to set
	 */
	public void setParameterSet(final boolean bParameterSet)
	{
		this.bParameterSet = bParameterSet;
	}

	/**
	 * if true, we want a productList from the kids
	 */
	boolean wantProduct;

	final private JDFAttributeMap componentProductMap;
	final Set<String> resourceAlias;

	/**
	 *
	 * @return
	 */
	public boolean isWantProduct()
	{
		return wantProduct;
	}

	/**
	 *
	 * @param wantProduct
	 */
	public void setWantProduct(final boolean wantProduct)
	{
		this.wantProduct = wantProduct;
	}

	/**
	 * if true add an htmlcolor attribute to color elements for xsl display purposes
	 */
	private boolean bHTMLColor = false;
	/**
	 * if false tildes are retained as range delimitors
	 */
	private boolean bConvertTilde = true;
	String rootID;

	private boolean removeSignatureName = true;

	private EnumProcessPartition processPartition = EnumProcessPartition.processTypes;
	private boolean wantDependent;

	/**
	 * @param bProcessList the ProcessList to set
	 */
	public void setProcessPart(final EnumProcessPartition process)
	{
		this.processPartition = process;
	}

	/**
	 *
	 * @return
	 */
	public boolean isRemoveSignatureName()
	{
		return removeSignatureName;
	}

	/**
	 *
	 * @param removeSignatureName
	 */
	public void setRemoveSignatureName(final boolean removeSignatureName)
	{
		this.removeSignatureName = removeSignatureName;
	}

	/**
	 * @param root the jdf or jmf to transform
	 * @return the root of the XJDF document
	 */
	public KElement convert(final KElement root)
	{
		if (root instanceof JDFJMF)
		{
			return makeNewJMF((JDFJMF) root);
		}
		if (root instanceof JDFNode)
		{
			return makeNewJDF((JDFNode) root, null);
		}
		return null;
	}

	/**
	 * @param jmf the jmf to transform
	 * @return the root of the XJDF document
	 */
	public KElement makeNewJMF(final JDFJMF jmf)
	{
		prepareNewDoc(true);
		if (jmf != null)
		{
			final JDFJMF root = (JDFJMF) jmf.cloneNewDoc();
			preFixVersion(root);

			walkTree(root, newRoot);
			postWalk(true);
		}
		else
		{
			newRoot = null;
		}
		return newRoot;
	}

	/**
	 * @param node the node to transform
	 * @param vMap the partmap to transform, null if all
	 * @return the root of the XJDF document
	 */
	public KElement makeNewJDF(final JDFNode node, final VJDFAttributeMap vMap)
	{
		prepareNewDoc(false);
		if (node != null)
		{
			rootID = node.appendAnchor(null);
			final JDFNode root = (JDFNode) node.getJDFRoot().cloneNewDoc();
			preFixVersion(root);

			final String id = StringUtil.getNonEmpty(node.getID());
			oldRoot = id == null ? root : (JDFNode) root.getChildWithAttribute(null, "ID", null, id, 0, false);
			if (oldRoot == null)
			{
				oldRoot = root;
			}
			// we are a simple - not spawned jdf
			if (oldRoot == root && root.getElement(ElementName.JDF) == null && root.getAncestorPool() == null)
			{
				setSingleNode(true);
			}

			prepareRoot(root);
			loopNodes(root);

			postWalk(false);
			newRoot.getOwnerDocument_KElement().copyMeta(node.getOwnerDocument_KElement());
		}
		else
		{
			newRoot = null;
		}
		return newRoot;
	}

	/**
	 *
	 * @param root
	 */
	void preFixVersion(final JDFElement root)
	{
		final FixVersion vers = new FixVersion(EnumVersion.Version_2_0);
		vers.setLayoutPrepToStripping(bMergeLayoutPrep);
		vers.setZappDeprecated(true);
		vers.addIgnore(ElementName.ACTIVITY, AttributeName.ROLES);

		vers.walkTree(root, null);
	}

	/**
	 *
	 * @param bJMF
	 */
	private void postWalk(final boolean bJMF)
	{
		final PostXJDFWalker pw = new PostXJDFWalker((JDFElement) newRoot);
		pw.setMergeLayout(bMergeLayout);
		pw.setIntentPartition(bIntentPartition);
		pw.setRemoveSignatureName(removeSignatureName);
		pw.setRetainAll(bRetainAll);

		pw.walkTreeKidsFirst(newRoot);
		if (bJMF)
		{
			if (newRoot.numChildElements(null, null) == newRoot.numChildElements(XJDFConstants.Header, null))
			{
				log.info("erased empty jmf");
				newRoot = null;
			}
			else if (!isRetainAll())
			{
				new XJMFHelper(newRoot).cleanUp();
			}

		}
		else
		{
			if (trackAudits)
			{
				final JDFAuditPool auditPool = (JDFAuditPool) newRoot.getCreateElement(ElementName.AUDITPOOL);
				final boolean hasCreated = auditPool.hasChildElement(ElementName.CREATED, null) || auditPool.hasChildElement("AuditCreated", null);
				if (!hasCreated)
				{
					final KElement c = auditPool.appendElement("AuditCreated");
					final KElement header = c.appendElement(XJDFConstants.Header);
					header.setAttribute(AttributeName.AGENTNAME, "JDF To XJDF Converter");
					header.setAttribute(AttributeName.AGENTVERSION, JDFAudit.getStaticAgentVersion());
					header.setAttribute(AttributeName.TIME, new JDFDate().getDateTimeISO());
				}
			}
			if (isCleanup())
			{
				new XJDFHelper(newRoot).cleanUp();
			}
		}
		final RemoveEmpty removeEmpty = new RemoveEmptyXJDF();
		removeEmpty.removEmptyElement(newRoot);
	}

	/**
	 * prepares the root so that inherited stuff from the ancestorpool does not get lost
	 *
	 * @param node
	 */
	private void prepareRoot(final JDFNode node)
	{
		if (node != null)
		{
			node.ensureLink(node.getInheritedCustomerInfo(null), EnumUsage.Input, null);
			node.ensureLink(node.getInheritedNodeInfo(null), EnumUsage.Input, null);
		}
	}

	/**
	 * @param node
	 */
	private void loopNodes(final JDFNode node)
	{
		// the loop is implicit due to the break condition in JDFWalker
		walkTree(node, newRoot);
	}

	/**
	 * @param bJMF if true, create a jmf
	 *
	 */
	private void prepareNewDoc(final boolean bJMF)
	{
		final JDFDoc newDoc = new JDFDoc(bJMF ? (bTypeSafeMessage ? XJDFConstants.XJMF : rootJMF) : XJDFConstants.XJDF, EnumVersion.Version_2_0);
		newDoc.setInitOnCreate(false);
		newRoot = newDoc.getRoot();
		newRoot.setNamespaceURI(getSchemaURL());
		first = new HashSet<>();
	}

	/**
	 *
	 * @param compID
	 * @param productID
	 */
	protected void putComponentProduct(final String compID, final String productID)
	{
		componentProductMap.put(compID, productID);
	}

	/**
	 *
	 * @param compID
	 * @return
	 */
	protected String getProduct(final String compID)
	{
		return componentProductMap.get(compID);
	}

	/**
	 * calculate a file extension name based of rootName
	 *
	 * @return String
	 */
	public static String getExtension()
	{
		return XJDFConstants.XJDF.toLowerCase();
	}

	/**
	 * @param fileName the filename of the zip file to save to
	 * @param rootNode the root jdf to save
	 * @param replace if true, overwrite existing files
	 */
	public void saveZip(final String fileName, final JDFNode rootNode, final boolean replace)
	{
		new MultiJDFToXJDF(this).saveZip(fileName, rootNode, replace);
	}

	/**
	 *
	 * @param os the output stream
	 * @param rootNode the root jdf to save
	 * @param jmf the submission or return jmf
	 */
	public void writeStream(final OutputStream os, final JDFNode rootNode, final JDFJMF jmf)
	{
		try
		{
			new MultiJDFToXJDF(this).getZipWriter(rootNode).writeStream(os);
		}
		catch (final IOException e)
		{
			log.error("oops", e);
		}
	}

	/**
	 *
	 * @param root
	 * @return
	 */
	public Vector<XJDFHelper> getXJDFs(final JDFNode root)
	{
		return new MultiJDFToXJDF(this).getXJDFs(root);
	}

	/**
	 * @param res
	 * @return omaMaps
	 */
	protected static VJDFAttributeMap getPartMapVector(final KElement res)
	{
		VJDFAttributeMap omaMaps = null;
		final VElement parts = res.getChildElementVector(ElementName.PART, null, null, true, 0, false);
		if (parts != null && parts.size() > 0)
		{
			omaMaps = new VJDFAttributeMap();
			for (int i = 0; i < parts.size(); i++)
			{
				omaMaps.add(((JDFPart) parts.get(i)).getPartMap());
			}
		}
		return omaMaps;
	}

	/**
	 * set to keep as much of the original structure as possible - used e.g. for xslt display of JDF nodes
	 *
	 * @deprecated
	 */
	@Deprecated
	public void retainAll()
	{
		setRetainAll(true);
	}

	/**
	 * Setter for bUpdateVersion attribute.
	 *
	 * @param bUpdateVersion the bUpdateVersion to set
	 */
	public void setUpdateVersion(final boolean bUpdateVersion)
	{
		this.bUpdateVersion = bUpdateVersion;
	}

	/**
	 * Getter for bMergeLayout attribute.
	 *
	 * @return the bMergeLayout
	 */
	public boolean isMergeLayout()
	{
		return bMergeLayout;
	}

	/**
	 * Setter for bMergeLayout attribute.
	 *
	 * @param bMergeLayout the bMergeLayout to set
	 */
	public void setMergeLayout(final boolean bMergeLayout)
	{
		this.bMergeLayout = bMergeLayout;
	}

	/**
	 * Getter for bMergeLayoutPrep attribute.
	 *
	 * @return the bMergeLayoutPrep
	 */
	public boolean isMergeLayoutPrep()
	{
		return bMergeLayoutPrep;
	}

	/**
	 * Setter for bMergeLayoutPrep attribute.
	 *
	 * @param bMergeLayoutPrep the bMergeLayoutPrep to set
	 */
	public void setMergeLayoutPrep(final boolean bMergeLayoutPrep)
	{
		this.bMergeLayoutPrep = bMergeLayoutPrep;
	}

	/**
	 * Getter for bMergeRunList attribute.
	 *
	 * @return the bMergeRunList
	 */
	public boolean isMergeRunList()
	{
		return bMergeRunList;
	}

	/**
	 * Setter for bMergeRunList attribute.
	 *
	 * @param bMergeRunList the bMergeRunList to set
	 */
	public void setMergeRunList(final boolean bMergeRunList)
	{
		this.bMergeRunList = bMergeRunList;
	}

	/**
	 * Getter for bRetainSpawnInfo attribute.
	 *
	 * @return the bRetainSpawnInfo
	 */
	public boolean isRetainSpawnInfo()
	{
		return bRetainSpawnInfo;
	}

	/**
	 * Setter for bRetainSpawnInfo attribute.
	 *
	 * @param bRetainSpawnInfo the bRetainSpawnInfo to set
	 */
	public void setRetainSpawnInfo(final boolean bRetainSpawnInfo)
	{
		this.bRetainSpawnInfo = bRetainSpawnInfo;
	}

	/**
	 * Getter for bSingleNode attribute.
	 *
	 * @return the bSingleNode
	 */
	public boolean isSingleNode()
	{
		return bSingleNode;
	}

	/**
	 * Setter for bSingleNode attribute.
	 *
	 * @param bSingleNode the bSingleNode to set
	 */
	public void setSingleNode(final boolean bSingleNode)
	{
		this.bSingleNode = bSingleNode;
	}

	/**
	 * Getter for bUpdateVersion attribute.
	 *
	 * @return the bUpdateVersion
	 */
	public boolean isUpdateVersion()
	{
		return bUpdateVersion;
	}

	/**
	 * Getter for bTypeSafeMessage attribute.
	 *
	 * @return the bTypeSafeMessage
	 */
	public boolean isTypeSafeMessage()
	{
		return bTypeSafeMessage;
	}

	/**
	 * Setter for bTypeSafeMessage attribute. also switches the JMF Root element name to XJMF
	 *
	 * @param bTypeSafeMessage the bTypeSafeMessage to set
	 */
	public void setTypeSafeMessage(final boolean bTypeSafeMessage)
	{
		this.bTypeSafeMessage = bTypeSafeMessage;
	}

	/**
	 * Getter for bAbstractMessage attribute.
	 *
	 * @return the bAbstractMessage
	 */
	public boolean isAbstractMessage()
	{
		return bAbstractMessage;
	}

	/**
	 * Setter for bAbstractMessage attribute.
	 *
	 * @param bAbstractMessage the bAbstractMessage to set
	 */
	public void setAbstractMessage(final boolean bAbstractMessage)
	{
		this.bAbstractMessage = bAbstractMessage;
	}

	/**
	 * Getter for bSpanAsAttribute attribute.
	 *
	 * @return the bSpanAsAttribute
	 */
	public boolean isSpanAsAttribute()
	{
		return bSpanAsAttribute;
	}

	/**
	 * Setter for bSpanAsAttribute attribute.
	 *
	 * @param bSpanAsAttribute the bSpanAsAttribute to set
	 */
	public void setSpanAsAttribute(final boolean bSpanAsAttribute)
	{
		this.bSpanAsAttribute = bSpanAsAttribute;
	}

	/**
	 * Getter for bIntentPartition attribute.
	 *
	 * @return the bIntentPartition
	 */
	public boolean isIntentPartition()
	{
		return bIntentPartition;
	}

	/**
	 * Setter for bIntentPartition attribute.
	 *
	 * @param bIntentPartition the bIntentPartition to set
	 */
	public void setIntentPartition(final boolean bIntentPartition)
	{
		this.bIntentPartition = bIntentPartition;
	}

	/**
	 * Getter for bHTMLColor attribute.
	 *
	 * @return the bHTMLColor
	 */
	public boolean isHTMLColor()
	{
		return bHTMLColor;
	}

	/**
	 * Setter for bHTMLColor attribute.
	 *
	 * @param bHTMLColor the bHTMLColor to set
	 */
	public void setHTMLColor(final boolean bHTMLColor)
	{
		this.bHTMLColor = bHTMLColor;
	}

	/**
	 * Getter for bConvertTilde attribute. if true, we zapp tilde
	 *
	 * @return the bConvertTilde
	 */
	public boolean isConvertTilde()
	{
		return bConvertTilde;
	}

	/**
	 * Setter for bConvertTilde attribute.
	 *
	 * @param bConvertTilde the bConvertTilde to set
	 */
	public void setConvertTilde(final boolean bConvertTilde)
	{
		this.bConvertTilde = bConvertTilde;
	}

	/**
	 *
	 * @param r
	 * @return
	 */
	protected String getSetName(final JDFResource r)
	{
		final WalkResource w = getWalker(r);
		return (w == null) ? null : w.getXJDFName(r);
	}

	/**
	 *
	 * @param linkTarget
	 * @return
	 */
	protected boolean isProductResource(final JDFResource linkTarget)
	{
		final WalkResource w = getWalker(linkTarget);
		return w == null ? false : w.isProductResource(linkTarget);
	}

	/**
	 *
	 * @return
	 */
	public boolean isRetainAll()
	{
		return bRetainAll;
	}

	/**
	 *
	 * @param bRetainAll
	 */
	public void setRetainAll(final boolean bRetainAll)
	{
		this.bRetainAll = bRetainAll;
		if (bRetainAll)
		{
			setAbstractMessage(false);
			setConvertTilde(false);
			setExplicitWaste(false);
			setHTMLColor(false);
			setMergeLayout(false);
			setMergeLayoutPrep(false);
			setMergeRunList(false);
			setRemoveSignatureName(false);
			setRetainSpawnInfo(true);
			setTypeSafeMessage(false);
			setUpdateVersion(false);
			setCleanup(false);
		}
	}

	public enum EnumProcessPartition
	{
		zip, processList, processTypes, jobPartID, processUsageJobPartID
	}

	/**
	 *
	 * @return
	 */
	public EnumProcessPartition getProcessPart()
	{
		return processPartition;
	}

	/**
	 *
	 * @return
	 */
	public boolean isWantProcessList()
	{
		return EnumProcessPartition.processList.equals(processPartition);
	}

	/**
	 * @return the bCleanup
	 */
	public boolean isCleanup()
	{
		return bCleanup;
	}

	/**
	 * @param bCleanup the bCleanup to set
	 */
	public void setCleanup(final boolean bCleanup)
	{
		this.bCleanup = bCleanup;
	}

	/**
	 *
	 * @param node
	 * @return
	 */
	public XJDFHelper getCombined(final JDFNode node)
	{
		final boolean oldCleanup = isCleanup();
		setCleanup(false);
		setWantDependent(false);
		final Vector<XJDFHelper> v = getXJDFs(node);

		final XJDFHelper combinedHelper = new MultiXJDFCombiner(v).getCombinedHelper();
		setCleanup(oldCleanup);
		if (isCleanup() && combinedHelper != null)
		{
			combinedHelper.cleanUp();
		}
		return combinedHelper;
	}

	public boolean wantDependent()
	{
		return wantDependent;
	}

	/**
	 * @param wantDependent the wantDependent to set
	 */
	public void setWantDependent(final boolean wantDependent)
	{
		this.wantDependent = wantDependent;
	}
}
