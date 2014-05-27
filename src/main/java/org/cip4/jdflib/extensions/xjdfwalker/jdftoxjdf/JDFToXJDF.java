/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.elementwalker.FixVersion;
import org.cip4.jdflib.elementwalker.PackageElementWalker;
import org.cip4.jdflib.extensions.PostXJDFWalker;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG <br/>
 * conversion class to convert JDF 1.x to the experimental JDF 2.0<br/>
 * very experimental and subject to change without notice
 * 
 * 15.01.2009
 */
public class JDFToXJDF extends PackageElementWalker
{

	/**
	 * 
	 */
	public JDFToXJDF()
	{
		super(new BaseWalkerFactory());
		KElement.uniqueID(-1000); // don't start at zero to avoid collisions in short ID scenarios
		trackAudits = true;
		init();
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.PackageElementWalker#constructWalker(java.lang.String)
	 */
	@Override
	protected BaseWalker constructWalker(String name)
	{
		WalkElement constructWalker = (WalkElement) super.constructWalker(name);
		if (constructWalker != null)
			constructWalker.setParent(this);
		return constructWalker;
	}

	/**
	 * 
	 * if true, add a modified audit
	 * @param trackAudits
	 */
	public void setTrackAudits(boolean trackAudits)
	{
		this.trackAudits = trackAudits;
	}

	/**
	 * the root node name
	 */
	public final static String rootName = "XJDF";
	/**
	 * the root JMF name
	 */
	public final static String rootJMF = "JMF";

	/**
	 * returns the official JDF schema URI for  2.0
	 * 
	 * 
	 * @return the URL that fits to majorVersion and minorVersion - null if not supported
	 */
	public static String getSchemaURL()
	{
		return JDFElement.getSchemaURL(2, 0);
	}

	final String m_spawnInfo = "SpawnInfo";
	/**
	 * 
	 */
	final public static VString amountAttribs = new VString("Amount,ActualAmount,MinAmount,MaxAmount", ",");
	private boolean trackAudits;
	protected VString resAttribs;
	protected KElement newRoot = null;
	protected JDFNode oldRoot = null;
	protected boolean walkingProduct = false;
	protected Set<String> first = new HashSet<String>();
	/**
	 * if true merge stripping and layout
	 */
	private boolean bExplicitWaste = false;

	/**
	 * Getter for bExplicitWaste attribute.
	 * @return the bExplicitWaste
	 */
	public boolean isExplicitWaste()
	{
		return bExplicitWaste;
	}

	/**
	 * Setter for bExplicitWaste attribute. if true PartAmount has explicit amounts for good and waste rather than partitions
	 * @param bExplicitWaste the bExplicitWaste to set
	 */
	public void setExplicitWaste(boolean bExplicitWaste)
	{
		this.bExplicitWaste = bExplicitWaste;
	}

	/**
	 * if true merge stripping and layout
	 */
	private boolean bMergeLayout = true;
	/**
	 * if true merge stripping and layout
	 */
	private boolean bMergeLayoutPrep = true;
	/**
	 * if true clean up runlist/LayoutElement
	 */
	private boolean bMergeRunList = true;
	/**
	 * set to retain spawn information
	 */
	boolean bRetainSpawnInfo = false;
	/**
	 * set to update version stamps
	 */
	private boolean bSingleNode = true;
	/**
	 * set to update version stamps
	 */
	private boolean bUpdateVersion = true;
	/**
	 * set to define type safe messages
	 */
	boolean bTypeSafeMessage = true;
	/**
	 * set to define one type for query, command and registration
	 */
	boolean bAbstractMessage = true;

	/**
	 * if true, spans are made to a simple attribute rather than retained as span
	 */
	private boolean bSpanAsAttribute = true;
	/**
	 * if true, Intents are partitioned
	 */
	boolean bIntentPartition = false;

	/**
	 * if true add an htmlcolor attribute to color elements for xsl display purposes
	 */
	boolean bHTMLColor = false;
	/**
	 * if true tildes are retained as range delimitors
	 */
	private boolean bConvertTilde = false;

	/**
	 * @param root the jdf or jmf to transform
	 * @return the root of the XJDF document
	 */
	public KElement convert(final KElement root)
	{
		if (root instanceof JDFJMF)
			return makeNewJMF((JDFJMF) root);
		if (root instanceof JDFNode)
			return makeNewJDF((JDFNode) root, null);
		return null;
	}

	/**
	 * @param jmf the jmf to transform
	 * @return the root of the XJDF document
	 */
	public KElement makeNewJMF(final JDFJMF jmf)
	{
		final JDFJMF root = (JDFJMF) jmf.cloneNewDoc();
		prepareNewDoc(true);
		walkTree(root, newRoot);
		newRoot.eraseEmptyNodes(true);
		postWalk();
		return newRoot;
	}

	/**
	 * @param node the node to transform
	 * @param vMap the partmap to transform, null if all
	 * @return the root of the XJDF document
	 */
	public KElement makeNewJDF(final JDFNode node, final VJDFAttributeMap vMap)
	{
		final JDFNode root = (JDFNode) node.getJDFRoot().cloneNewDoc();
		if (trackAudits)
			root.getCreateAuditPool().addCreated("XJDF Converter", null);
		FixVersion vers = new FixVersion(EnumVersion.Version_1_4);
		vers.setLayoutPrepToStripping(bMergeLayoutPrep);
		vers.walkTree(root, null);

		String id = StringUtil.getNonEmpty(node.getID());
		oldRoot = id == null ? root : (JDFNode) root.getChildWithAttribute(null, "ID", null, id, 0, false);
		if (oldRoot == null)
		{
			oldRoot = root;
		}
		walkingProduct = false;
		prepareNewDoc(false);

		loopNodes(oldRoot);

		walkingProduct = true;
		final KElement productList = newRoot.appendElement("ProductList");

		prepareRoot(root);
		walkTree(root, productList);
		if (productList.getElement("Product") == null)
		{
			productList.deleteNode();
		}
		walkingProduct = false;

		postWalk();
		newRoot.getOwnerDocument_KElement().copyMeta(node.getOwnerDocument_KElement());

		return newRoot;
	}

	private void postWalk()
	{
		PostXJDFWalker pw = new PostXJDFWalker((JDFElement) newRoot);
		pw.mergeLayout = bMergeLayout;
		pw.bIntentPartition = bIntentPartition;
		pw.walkTreeKidsFirst(newRoot);
		newRoot.eraseEmptyNodes(true);
	}

	/**
	 * prepares the root so that inherited stuff from the ancestorpool does not get lost
	 * @param node
	 */
	private void prepareRoot(JDFNode node)
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
	private void prepareNewDoc(boolean bJMF)
	{
		final JDFDoc newDoc = new JDFDoc(bJMF ? rootJMF : rootName);
		newDoc.setInitOnCreate(false);
		newRoot = newDoc.getRoot();
		newRoot.setNamespaceURI(getSchemaURL());
		first = new HashSet<String>();
	}

	/**
	 * 
	 */
	private void init()
	{
		resAttribs = generateResourceAttributes();
	}

	/**
	 * 
	 * TODO Please insert comment!
	 * @return
	 */
	protected VString generateResourceAttributes()
	{
		VString resAttribs = new VString();
		final JDFResourcePool dummyResPool = (JDFResourcePool) new JDFDoc("ResourcePool").getRoot();
		final JDFResource intRes = dummyResPool.appendResource("intent", EnumResourceClass.Intent, null);
		final JDFResource physRes = dummyResPool.appendResource("physical", EnumResourceClass.Consumable, null);
		final JDFResource paramRes = dummyResPool.appendResource("param", EnumResourceClass.Parameter, null);
		final JDFPart part = (JDFPart) dummyResPool.appendElement(ElementName.PART);
		resAttribs = paramRes.knownAttributes();
		resAttribs.appendUnique(physRes.knownAttributes());
		resAttribs.appendUnique(intRes.knownAttributes());
		resAttribs.appendUnique(part.knownAttributes());
		return resAttribs;
	}

	/**
	 * 
	 * TODO Please insert comment!
	 * @param name
	 * @return
	 */
	public String getClassName(final String name)
	{
		if (name == null)
			return null;
		KElement e = new JDFDoc(name).getRoot();

		String className = (e instanceof JDFResource) ? getClassName((JDFResource) e) : null;
		return className;
	}

	/**
	 * 
	 * TODO Please insert comment!
	 * @param r
	 * @return
	 */
	String getClassName(final JDFResource r)
	{
		if (r == null)
		{
			return null;
		}
		EnumResourceClass resourceClass = r.getResourceClass();
		if (resourceClass == null)
		{
			KElement r2 = new JDFDoc(r.getLocalName()).getRoot();
			if (r2 instanceof JDFResource)
			{
				r2.init();
				resourceClass = ((JDFResource) r2).getResourceClass();
			}
		}
		if (resourceClass == null)
		{
			return "Parameter"; // assume parameter if unknown 3rd party stuff
		}
		String className = "Resource";
		if (resourceClass.equals(EnumResourceClass.Parameter) || resourceClass.equals(EnumResourceClass.Intent))
		{
			className = resourceClass.getName();
		}
		if (resourceClass.equals(EnumResourceClass.PlaceHolder))
		{
			return null;
		}
		return className;
	}

	/**
	 * calculate a file extension name based of rootName
	 * @return String
	 */
	public static String getExtension()
	{
		return rootName.toLowerCase();
	}

	/**
	 * @param fileName the filename of the zip file to save to
	 * @param rootNode the root jdf to save
	 * @param replace if true, overwrite existing files
	 */
	public void saveZip(final String fileName, final JDFNode rootNode, final boolean replace)
	{
		final File file = new File(fileName);
		if (file.canRead())
		{
			if (replace)
			{
				file.delete();
			}
			else
			{
				throw new JDFException("output file exists: " + file.getPath());
			}
		}

		try
		{
			final VElement v = rootNode.getvJDFNode(null, null, false);
			final FileOutputStream fos = new FileOutputStream(fileName);
			final ZipOutputStream zos = new ZipOutputStream(fos);
			for (int i = 0; i < v.size(); i++)
			{
				final JDFNode n = (JDFNode) v.elementAt(i);
				String nam = n.getJobPartID(false);
				if (nam == "")
				{
					nam = "Node" + i;
				}
				try
				{
					nam += "." + rootName;
					final ZipEntry ze = new ZipEntry(nam);
					zos.putNextEntry(ze);
					final KElement newRootL = makeNewJDF(n, null);
					newRootL.getOwnerDocument_KElement().write2Stream(zos, 2, true);
					zos.closeEntry();

				}
				catch (final ZipException x)
				{
					log.error("oops: ", x);
				}
				catch (final IOException x)
				{
					log.error("oops: ", x);
				}
			}
			zos.close();
		}
		catch (final IOException x)
		{
			log.error("oops: ", x);
		}
	}

	/**
	 * 
	 *  
	 * @param rl
	 * @param newLeaf
	 * @param partMap
	 */
	void setAmountPool(final JDFElement rl, final KElement newLeaf, final JDFAttributeMap partMap)
	{
		if (rl == null)
		{
			return;
		}
		JDFAmountPool ap = (JDFAmountPool) rl.getElement(ElementName.AMOUNTPOOL);
		if (ap == null)
		{
			JDFAttributeMap amounts = rl.getAttributeMap().reduceMap(amountAttribs);
			if (amounts.size() > 0)
			{
				ap = (JDFAmountPool) newLeaf.appendElement(ElementName.AMOUNTPOOL);
				for (String key : amounts.keySet())
				{
					ap.setPartAttribute(key, amounts.get(key), null, partMap);
					rl.removeAttribute(key);
				}
			}
		}
		else
		{
			final VElement vPartAmounts = ap.getMatchingPartAmountVector(partMap);
			if (vPartAmounts != null && vPartAmounts.size() > 0)
			{
				ap = (JDFAmountPool) newLeaf.appendElement(ElementName.AMOUNTPOOL);
				for (KElement pa : vPartAmounts)
				{
					ap.copyElement(pa, null);
				}
			}
		}
	}

	/**
	 * @param rl 
	 * @param r 
	 * @param xjdfSet 
	 * @return 
	 * 
	 */
	private KElement setBaseResource(final JDFElement rl, final JDFResource r, final KElement xjdfSet)
	{
		final JDFAttributeMap map = r.getPartMap();
		SetHelper sh = new SetHelper(xjdfSet);
		KElement newLeaf = sh.getCreatePartition(map, false).getPartition();
		setLeafAttributes(r, rl, newLeaf);
		return newLeaf;
	}

	/**
	 * @param leaf
	 * @param rl 
	 * @param newLeaf
	 */
	private void setLeafAttributes(final JDFResource leaf, final JDFElement rl, final KElement newLeaf)
	{
		final JDFAttributeMap partMap = leaf.getPartMap();
		// JDFAttributeMap attMap=leaf.getAttributeMap();
		// attMap.remove("ID");
		setAmountPool(rl, newLeaf, partMap);

		// retain spawn information
		if (bRetainSpawnInfo && leaf.hasAttribute(AttributeName.SPAWNIDS))
		{
			final KElement spawnInfo = newLeaf.getDocRoot().getCreateElement(m_spawnInfo, null, 0);
			final KElement spawnID = spawnInfo.appendElement("SpawnID");
			spawnID.moveAttribute(AttributeName.SPAWNIDS, newLeaf, null, null, null);
			spawnID.moveAttribute(AttributeName.SPAWNSTATUS, newLeaf, null, null, null);
			spawnID.copyAttribute(AttributeName.RESOURCEID, newLeaf, AttributeName.ID, null, null);
		}
	}

	/**
	 * set the attributes of the set based on the resource and resourcelink
	 * 
	 * @param resourceSet
	 * @param rl
	 * @param linkRoot
	 */
	private void setSetAttributes(final KElement resourceSet, final KElement rl, final JDFResource linkRoot)
	{
		resourceSet.setAttribute("Name", linkRoot.getNodeName());
		resourceSet.setAttributes(rl);
		//TODO orientation + coordinate system stuff
		resourceSet.removeAttribute(AttributeName.RREF);
		resourceSet.removeAttribute(AttributeName.RSUBREF);
		resourceSet.removeAttribute(AttributeName.AMOUNT);
		resourceSet.removeAttribute(AttributeName.AMOUNTPRODUCED);
		resourceSet.removeAttribute(AttributeName.MAXAMOUNT);
		resourceSet.removeAttribute(AttributeName.ACTUALAMOUNT);

		if (rl instanceof JDFResourceLink)
		{
			final JDFResourceLink resLink = (JDFResourceLink) rl;
			final JDFNode rootIn = resLink.getJDFRoot();

			final JDFResource resInRoot = rootIn == null ? linkRoot : (JDFResource) rootIn.getChildWithAttribute(null, "ID", null, resLink.getrRef(), 0, false);
			if (resInRoot != null)
			{
				final VElement vCreators = resInRoot.getCreator(EnumUsage.Input.equals(resLink.getUsage()));
				if (vCreators != null)
				{
					final int size = vCreators.size();
					for (int i = 0; i < size; i++)
					{
						final JDFNode depNode = (JDFNode) vCreators.elementAt(i);
						final KElement dependent = resourceSet.appendElement("Dependent");
						dependent.setAttribute(AttributeName.JOBID, depNode.getJobID(true));
						dependent.copyAttribute(AttributeName.JMFURL, depNode, null, null, null);
						dependent.copyAttribute(AttributeName.JOBPARTID, depNode, null, null, null);
					}
				}
			}
		}
	}

	/**
	 * @param rl
	 * @param linkTarget
	 * @param xRoot
	 * @return the vector of partitions
	 */
	protected VElement setResource(final JDFElement rl, final JDFResource linkTarget, final KElement xRoot)
	{
		final VElement v = new VElement();
		final String className = getClassName(linkTarget);
		if (className == null)
		{
			return null;
		}
		linkTarget.expand(false);
		final String resID = linkTarget.getAttribute("ID");

		KElement resourceSet = xRoot.getChildWithAttribute(className + "Set", "ID", null, resID, 0, true);
		if (resourceSet == null)
		{
			resourceSet = xRoot.appendElement(className + "Set");
			resourceSet.setAttribute("ID", linkTarget.getID());
		}
		// TODO what if he have resources used as in and out in the same node?
		setSetAttributes(resourceSet, rl, linkTarget);
		int nLeaves = resourceSet.numChildElements(className, null);
		final VElement vRes = (rl instanceof JDFResourceLink) ? ((JDFResourceLink) rl).getTargetVector(0) : linkTarget.getLeaves(false);
		for (int j = 0; j < vRes.size(); j++)
		{
			final JDFResource r = (JDFResource) vRes.elementAt(j);
			final VElement vLeaves = r.getLeaves(false);
			for (int k = 0; k < vLeaves.size(); k++)
			{
				final JDFResource leaf = (JDFResource) vLeaves.elementAt(k);
				final KElement newBaseRes = setBaseResource(rl, leaf, resourceSet);
				final int nn = resourceSet.numChildElements(className, null);
				if (nn > nLeaves)
				{
					nLeaves = nn;
					walkTree(leaf, newBaseRes);
				}
				v.add(newBaseRes);
			}
		}
		return v;
	}

	// //////////////////////////////////////////////////////////////////////////////

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
	 */
	public void retainAll()
	{
		bUpdateVersion = false;
		bHTMLColor = false;
		bMergeLayout = false;
		bIntentPartition = false;
		bSpanAsAttribute = false;
		bMergeLayoutPrep = false;
		bMergeRunList = false;
		bTypeSafeMessage = false;
		bRetainSpawnInfo = true;
	}

	/**
	 * Setter for bUpdateVersion attribute.
	 * @param bUpdateVersion the bUpdateVersion to set
	 */
	public void setUpdateVersion(boolean bUpdateVersion)
	{
		this.bUpdateVersion = bUpdateVersion;
	}

	/**
	 * Getter for bMergeLayout attribute.
	 * @return the bMergeLayout
	 */
	public boolean isMergeLayout()
	{
		return bMergeLayout;
	}

	/**
	 * Setter for bMergeLayout attribute.
	 * @param bMergeLayout the bMergeLayout to set
	 */
	public void setMergeLayout(boolean bMergeLayout)
	{
		this.bMergeLayout = bMergeLayout;
	}

	/**
	 * Getter for bMergeLayoutPrep attribute.
	 * @return the bMergeLayoutPrep
	 */
	public boolean isMergeLayoutPrep()
	{
		return bMergeLayoutPrep;
	}

	/**
	 * Setter for bMergeLayoutPrep attribute.
	 * @param bMergeLayoutPrep the bMergeLayoutPrep to set
	 */
	public void setMergeLayoutPrep(boolean bMergeLayoutPrep)
	{
		this.bMergeLayoutPrep = bMergeLayoutPrep;
	}

	/**
	 * Getter for bMergeRunList attribute.
	 * @return the bMergeRunList
	 */
	public boolean isMergeRunList()
	{
		return bMergeRunList;
	}

	/**
	 * Setter for bMergeRunList attribute.
	 * @param bMergeRunList the bMergeRunList to set
	 */
	public void setMergeRunList(boolean bMergeRunList)
	{
		this.bMergeRunList = bMergeRunList;
	}

	/**
	 * Getter for bRetainSpawnInfo attribute.
	 * @return the bRetainSpawnInfo
	 */
	public boolean isRetainSpawnInfo()
	{
		return bRetainSpawnInfo;
	}

	/**
	 * Setter for bRetainSpawnInfo attribute.
	 * @param bRetainSpawnInfo the bRetainSpawnInfo to set
	 */
	public void setRetainSpawnInfo(boolean bRetainSpawnInfo)
	{
		this.bRetainSpawnInfo = bRetainSpawnInfo;
	}

	/**
	 * Getter for bSingleNode attribute.
	 * @return the bSingleNode
	 */
	public boolean isSingleNode()
	{
		return bSingleNode;
	}

	/**
	 * Setter for bSingleNode attribute.
	 * @param bSingleNode the bSingleNode to set
	 */
	public void setSingleNode(boolean bSingleNode)
	{
		this.bSingleNode = bSingleNode;
	}

	/**
	 * Getter for bUpdateVersion attribute.
	 * @return the bUpdateVersion
	 */
	public boolean isUpdateVersion()
	{
		return bUpdateVersion;
	}

	/**
	 * Getter for bTypeSafeMessage attribute.
	 * @return the bTypeSafeMessage
	 */
	public boolean isTypeSafeMessage()
	{
		return bTypeSafeMessage;
	}

	/**
	 * Setter for bTypeSafeMessage attribute.
	 * @param bTypeSafeMessage the bTypeSafeMessage to set
	 */
	public void setTypeSafeMessage(boolean bTypeSafeMessage)
	{
		this.bTypeSafeMessage = bTypeSafeMessage;
	}

	/**
	 * Getter for bAbstractMessage attribute.
	 * @return the bAbstractMessage
	 */
	public boolean isAbstractMessage()
	{
		return bAbstractMessage;
	}

	/**
	 * Setter for bAbstractMessage attribute.
	 * @param bAbstractMessage the bAbstractMessage to set
	 */
	public void setAbstractMessage(boolean bAbstractMessage)
	{
		this.bAbstractMessage = bAbstractMessage;
	}

	/**
	 * Getter for bSpanAsAttribute attribute.
	 * @return the bSpanAsAttribute
	 */
	public boolean isSpanAsAttribute()
	{
		return bSpanAsAttribute;
	}

	/**
	 * Setter for bSpanAsAttribute attribute.
	 * @param bSpanAsAttribute the bSpanAsAttribute to set
	 */
	public void setSpanAsAttribute(boolean bSpanAsAttribute)
	{
		this.bSpanAsAttribute = bSpanAsAttribute;
	}

	/**
	 * Getter for bIntentPartition attribute.
	 * @return the bIntentPartition
	 */
	public boolean isIntentPartition()
	{
		return bIntentPartition;
	}

	/**
	 * Setter for bIntentPartition attribute.
	 * @param bIntentPartition the bIntentPartition to set
	 */
	public void setIntentPartition(boolean bIntentPartition)
	{
		this.bIntentPartition = bIntentPartition;
	}

	/**
	 * Getter for bHTMLColor attribute.
	 * @return the bHTMLColor
	 */
	public boolean isHTMLColor()
	{
		return bHTMLColor;
	}

	/**
	 * Setter for bHTMLColor attribute.
	 * @param bHTMLColor the bHTMLColor to set
	 */
	public void setHTMLColor(boolean bHTMLColor)
	{
		this.bHTMLColor = bHTMLColor;
	}

	/**
	 * Getter for bConvertTilde attribute.
	 * @return the bConvertTilde
	 */
	public boolean isConvertTilde()
	{
		return bConvertTilde;
	}

	/**
	 * Setter for bConvertTilde attribute.
	 * @param bConvertTilde the bConvertTilde to set
	 */
	public void setConvertTilde(boolean bConvertTilde)
	{
		this.bConvertTilde = bConvertTilde;
	}

}
