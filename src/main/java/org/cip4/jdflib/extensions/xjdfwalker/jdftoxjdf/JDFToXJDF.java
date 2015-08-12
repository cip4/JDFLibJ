/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of
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
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.elementwalker.FixVersion;
import org.cip4.jdflib.elementwalker.IWalker;
import org.cip4.jdflib.elementwalker.PackageElementWalker;
import org.cip4.jdflib.extensions.PostXJDFWalker;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.util.FileUtil;
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
		wantProduct = true;
		rootID = null;
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
	 *  
	 * @param r
	 * @return
	 */
	protected String getClassName(final JDFResource r)
	{
		WalkResource w = getWalker(r);
		return (w == null) ? null : w.getClassName(r);
	}

	/**
	 * 
	 * @param r
	 * @return
	 */
	protected WalkResource getWalker(JDFResource r)
	{
		if (r == null)
			return null;
		IWalker walker = theFactory.getWalker(r);
		return (walker instanceof WalkResource) ? (WalkResource) walker : null;
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
	 * the root node name if NOT typesafe
	 */
	public final static String rootName = XJDFHelper.XJDF;
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
	 * set to update single node update
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
	boolean bAbstractMessage = false;

	/**
	 * if true, spans are made to a simple attribute rather than retained as span
	 */
	private boolean bSpanAsAttribute = true;
	/**
	 * if true, Intents are partitioned
	 */
	boolean bIntentPartition = false;

	/**
	 *  if true, we want a productList from the kids
	 */
	boolean wantProduct;

	private JDFAttributeMap componentProductMap;

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
	public void setWantProduct(boolean wantProduct)
	{
		this.wantProduct = wantProduct;
	}

	/**
	 * if true add an htmlcolor attribute to color elements for xsl display purposes
	 */
	boolean bHTMLColor = false;
	/**
	 * if false tildes are retained as range delimitors
	 */
	private boolean bConvertTilde = true;
	String rootID;
	protected VString elemAttribs;
	protected HashSet<String> inlineSet;

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
		final JDFJMF root = (JDFJMF) jmf.cloneNewDoc();
		prepareNewDoc(true);
		walkTree(root, newRoot);
		newRoot.eraseEmptyNodes(true);
		boolean tmpTrack = trackAudits;
		trackAudits = false;
		postWalk();
		trackAudits = tmpTrack;
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
		rootID = node.getID();
		FixVersion vers = new FixVersion(EnumVersion.Version_1_5);
		vers.setLayoutPrepToStripping(bMergeLayoutPrep);
		vers.walkTree(root, null);

		String id = StringUtil.getNonEmpty(node.getID());
		oldRoot = id == null ? root : (JDFNode) root.getChildWithAttribute(null, "ID", null, id, 0, false);
		if (oldRoot == null)
		{
			oldRoot = root;
		}
		prepareNewDoc(false);

		loopNodes(root);

		prepareRoot(root);

		postWalk();
		newRoot.getOwnerDocument_KElement().copyMeta(node.getOwnerDocument_KElement());

		return newRoot;
	}

	/**
	 * 
	 *  
	 */
	private void postWalk()
	{
		PostXJDFWalker pw = new PostXJDFWalker((JDFElement) newRoot);
		pw.mergeLayout = bMergeLayout;
		pw.bIntentPartition = bIntentPartition;
		pw.walkTreeKidsFirst(newRoot);
		if (trackAudits)
		{
			JDFAuditPool auditPool = (JDFAuditPool) newRoot.getCreateElement("AuditPool");
			auditPool.addCreated("XJDF Converter", null);
		}

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
		final JDFDoc newDoc = new JDFDoc(bJMF ? (bTypeSafeMessage ? XJDFHelper.XJMF : rootJMF) : XJDFHelper.XJDF, EnumVersion.Version_2_0);
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
		componentProductMap = new JDFAttributeMap();
		resAttribs = generateResourceAttributes();
		elemAttribs = generateElementAttributes();
		inlineSet = generateInlineSet();
	}

	private HashSet<String> generateInlineSet()
	{
		HashSet<String> set = new HashSet<String>();
		set.add(ElementName.OBJECTRESOLUTION);
		set.add(ElementName.BARCODECOMPPARAMS);
		set.add(ElementName.BARCODEREPROPARAMS);
		set.add(ElementName.COMCHANNEL);
		set.add(ElementName.INTERPRETEDPDLDATA);
		set.add(ElementName.BYTEMAP);
		set.add(ElementName.ADDRESS);
		set.add(ElementName.COSTCENTER);
		set.add(ElementName.COMPANY);
		set.add(ElementName.PERSON);
		set.add(ElementName.DEVICE);
		set.add(ElementName.DEVICENSPACE);
		set.add(ElementName.COLORANTALIAS);
		set.add(ElementName.GLUELINE);
		set.add(ElementName.GLUEAPPLICATION);
		set.add(ElementName.CIELABMEASURINGFIELD);
		set.add(ElementName.REGISTERMARK);
		set.add(ElementName.FITPOLICY);
		set.add(ElementName.CUTBLOCK);
		set.add(ElementName.EMPLOYEE);
		set.add(ElementName.ELEMENTCOLORPARAMS);
		set.add(ElementName.CUT);
		set.add(ElementName.PDLRESOURCEALIAS);
		set.add(ElementName.HOLELIST);
		set.add(ElementName.HOLE);
		set.add(ElementName.MISDETAILS);
		set.add(ElementName.HOLELINE);
		set.add(ElementName.JOBFIELD);
		set.add(ElementName.AUTOMATEDOVERPRINTPARAMS);
		set.add(ElementName.EXTERNALIMPOSITIONTEMPLATE);
		set.add(ElementName.PRODUCTIONPATH);
		set.add(ElementName.SHAPE);
		set.add(ElementName.SCAVENGERAREA);
		set.add(ElementName.TRAPREGION);
		set.add(ElementName.TRANSFERCURVE);
		set.add(ElementName.COLORCONTROLSTRIP);
		set.add(ElementName.LAYERLIST);
		set.add(ElementName.PAGECONDITION);
		set.add(ElementName.CONTENTOBJECT);
		set.add(ElementName.MARKOBJECT);
		set.add(ElementName.LAYERDETAILS);
		set.add(ElementName.FILESPEC);
		set.add(ElementName.IDENTIFICATIONFIELD);
		return set;
	}

	/**
	 * 
	 * @param compID
	 * @param productID
	 */
	protected void putComponentProduct(String compID, String productID)
	{
		componentProductMap.put(compID, productID);
	}

	/**
	 * 
	 * @param compID
	 * @return
	 */
	protected String getProduct(String compID)
	{
		return componentProductMap.get(compID);
	}

	/**
	 * 
	 *  
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
	 *  
	 * @return
	 */
	protected VString generateElementAttributes()
	{
		final JDFResourcePool dummyResPool = (JDFResourcePool) new JDFDoc("ResourcePool").getRoot();
		return dummyResPool.knownAttributes();
	}

	/**
	 * calculate a file extension name based of rootName
	 * @return String
	 */
	public static String getExtension()
	{
		return XJDFHelper.XJDF.toLowerCase();
	}

	/**
	 * @param fileName the filename of the zip file to save to
	 * @param rootNode the root jdf to save
	 * @param replace if true, overwrite existing files
	 */
	public void saveZip(final String fileName, final JDFNode rootNode, final boolean replace)
	{
		new MultiJDFToXJDF().saveZip(fileName, rootNode, replace);
	}

	/**
	 * 
	 * @param root
	 * @return
	 */
	public Vector<XJDFHelper> getXJDFs(JDFNode root)
	{
		return new MultiJDFToXJDF().getXJDFs(root);
	}

	/**
	 * 
	 * @author rainer prosi
	 *
	 */
	private class MultiJDFToXJDF
	{

		/**
		 * @param fileName the filename of the zip file to save to
		 * @param rootNode the root jdf to save
		 * @param replace if true, overwrite existing files
		 */
		void saveZip(final String fileName, final JDFNode rootNode, final boolean replace)
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
				final Vector<XJDFHelper> v = getXJDFs(rootNode);

				final OutputStream fos = FileUtil.getBufferedOutputStream(new File(fileName));
				final ZipOutputStream zos = new ZipOutputStream(fos);
				for (XJDFHelper h : v)
				{
					try
					{
						String nam = h.getJobPartID() + "." + XJDFHelper.XJDF;
						final ZipEntry ze = new ZipEntry(nam);
						zos.putNextEntry(ze);
						h.writeToStream(zos);
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
		 */
		MultiJDFToXJDF()
		{
			super();
		}

		/**
		 * 
		 * @param root
		 * @return
		 */
		Vector<XJDFHelper> getXJDFs(JDFNode root)
		{
			if (root == null)
				return null;
			Vector<XJDFHelper> vRet = new Vector<XJDFHelper>();
			VElement v = getProcessNodes(root);
			boolean keepProduct = wantProduct;
			wantProduct = true;
			if (JDFConstants.PRODUCT.equals(root.getType()))
			{
				XJDFHelper xjdfHelper = convertSingle(root);
				vRet.add(xjdfHelper);
			}
			wantProduct = false;
			for (KElement n : v)
			{
				XJDFHelper xjdfHelper = convertSingle(n);
				vRet.add(xjdfHelper);
			}
			wantProduct = keepProduct;
			return vRet;
		}

		/**
		 * 
		 * @param n
		 * @return
		 */
		private XJDFHelper convertSingle(KElement n)
		{
			final KElement xjdf = makeNewJDF((JDFNode) n, null);
			XJDFHelper xjdfHelper = new XJDFHelper(xjdf);
			xjdfHelper.cleanUp();
			return xjdfHelper;
		}

		/**
		 * 
		 * @param rootNode
		 * @return
		 */
		VElement getProcessNodes(final JDFNode rootNode)
		{
			final VElement v = rootNode.getvJDFNode(null, null, false);
			for (int i = v.size() - 1; i >= 0; i--)
			{
				final JDFNode n = (JDFNode) v.elementAt(i);
				if (!n.isProcessNode())
				{
					v.remove(i);
				}
				else
				{
					String nam = n.getJobPartID(false);
					if (StringUtil.getNonEmpty(nam) == null)
					{
						nam = "Part_" + i;
					}

				}
			}
			return v;
		}
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
	 * also switches the JMF Root element name to XJMF
	 * 
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
	 * Getter for bConvertTilde attribute. if true, we zapp tilde
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

	/**
	 * 
	 * @param r
	 * @return
	 */
	protected String getSetName(JDFResource r)
	{
		WalkResource w = getWalker(r);
		return (w == null) ? null : w.getXJDFName(r);
	}

	/**
	 * 
	 * @param linkTarget
	 * @return
	 */
	protected boolean isProductResource(final JDFResource linkTarget)
	{
		WalkResource w = getWalker(linkTarget);
		return w.isProductResource(linkTarget);
	}
}
