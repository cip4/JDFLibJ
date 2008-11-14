/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2008 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.goldenticket;

import java.util.Vector;

import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFCMYKColor;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumPartUsage;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFColor;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StatusCounter;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * @author prosirai class that generates golden tickets based on ICS levels etc basegolden ticket should generally be the last in the cascade domain - mis - jmf
 * - base
 * 
 * To generate a new golden ticket, follow these steps 1.) construct the appropriate domain subclass, e.g. MISCPGoldenTicket for mis to conventional print 2.)
 * call .assign(null) (or your favorite hand-coded jdf node) 3.) retrieve the updated copy with .getNode()
 * 
 */
public class BaseGoldenTicket
{
	protected VString amountLinks = null;
	protected JDFNode theNode = null;
	protected JDFNode theExpandedNode = null;
	protected JDFNode thePreviousNode = null;
	protected JDFNode theParentNode = null;
	public JDFNode theParentProduct = null;
	protected EnumVersion theVersion = null;
	protected int baseICSLevel;
	protected StatusCounter theStatusCounter;
	public static String misURL = null;
	public static String deviceURL = null;
	private final Vector<BaseGoldenTicket> vKids = new Vector<BaseGoldenTicket>();
	public VJDFAttributeMap vParts = null;
	public VString cols = new VString("Black,Cyan,Magenta,Yellow,Spot1,Spot2,Spot3,Spot4", ",");
	public VString colsActual = new VString("Schwarz,Cyan,Magenta,Gelb,RIP 4711,RIP 4712,RIP 4713,RIP 4714", ",");
	public int[] nCols =
	{ 0, 0 };
	protected VString partIDKeys = null;
	public EnumWorkStyle workStyle = EnumWorkStyle.Simplex;
	public String devID = "DeviceID";
	/**
	 * good for plan and execute
	 */
	public int good = 1000;
	/**
	 * pwaste for plan and execute
	 */
	public int waste = 100;
	public int partsAtOnce = 0; // 0 = all
	public int partsForAvailable = 1; // 1=1 loop and all is available
	public boolean bExpandGrayBox = true;
	public boolean bPartitionedPlateMedia = false;
	public JDFMedia paperMedia;
	/**
	 * if set, the returnURL will be initialized
	 */
	public String returnURL = null;
	public boolean getNIFromParent = false;
	/**
	 * 
	 */
	public String m_pdfFile = "file:///here/file.pdf";

	/**
	 * create a BaseGoldenTicket
	 * 
	 * @param icsLevel the level to init to (1,2 or 3)
	 * @param jdfVersion the version to generate a golden ticket for
	 */
	public BaseGoldenTicket(final int icsLevel, final EnumVersion jdfVersion)
	{
		baseICSLevel = icsLevel;
		theVersion = jdfVersion == null ? EnumVersion.Version_1_3 : jdfVersion;
		theStatusCounter = new StatusCounter(null, null, null);
		JDFElement.setLongID(false);
	}

	/**
	 * create a BaseGoldenTicket
	 * 
	 * @param parent
	 * 
	 */
	public BaseGoldenTicket(final BaseGoldenTicket parent)
	{
		baseICSLevel = parent.baseICSLevel;
		theVersion = parent.theVersion;
		theStatusCounter = new StatusCounter(null, null, null);
		bExpandGrayBox = parent.bExpandGrayBox;
		bPartitionedPlateMedia = parent.bPartitionedPlateMedia;
		cols = new VString(parent.cols);
		colsActual = new VString(parent.colsActual);
		nCols = parent.nCols;
		devID = parent.devID;
		good = parent.good;
		waste = parent.waste;
		paperMedia = parent.paperMedia;
		partsAtOnce = parent.partsAtOnce;
		theParentNode = parent.getNode();
		vParts = new VJDFAttributeMap(parent.vParts);
		partIDKeys = new VString(parent.partIDKeys);
		workStyle = parent.workStyle;
		JDFElement.setLongID(false);
		parent.addKid(this);
	}

	/**
	 * @param icsLevel
	 */
	protected JDFNodeInfo initNodeInfo()
	{

		JDFNodeInfo ni = getNIFromParent && theParentNode != null ? theParentNode.getNodeInfo() : null;
		if (ni == null)
		{
			ni = theNode.getCreateNodeInfo();
			ni.setResStatus(EnumResStatus.Available, false);
		}
		else
		{
			theNode.linkResource(ni, EnumUsage.Input, null);
		}

		if (returnURL != null)
		{
			ni.setTargetRoute(returnURL);
		}
		return ni;
	}

	/**
	 * assign a node to this golden ticket instance
	 * 
	 * @param node the node to assign, if null a new conforming node is generated from scratch
	 */
	public void assign(final JDFNode node)
	{
		vKids.clear();
		vKids.add(this);
		theNode = node == null ? new JDFDoc("JDF").getJDFRoot() : node;
		theExpandedNode = theNode;
		if (theNode.getParentJDF() != null)
		{
			theParentNode = theNode.getParentJDF();
		}
		theParentProduct = theParentNode;
		while (theParentProduct != null && !EnumType.Product.equals(theParentProduct.getEnumType()))
		{
			theParentProduct = theParentProduct.getParentJDF();
		}
		setVersion();
		init();
	}

	/**
	 * assign a previous node to this golden ticket instance, e.g. an imagesetting node
	 * 
	 * @param node the node to assign, if null a new conforming node is generated from scratch
	 */
	public void setPreviousNode(final JDFNode node)
	{
		thePreviousNode = node;
	}

	/**
	 * add a kid to be makeready and executed
	 * 
	 * @param node the node to assign, if null a new conforming node is generated from scratch
	 */
	public void addKid(final BaseGoldenTicket bt)
	{
		if (!vKids.contains(bt))
		{
			vKids.add(bt);
		}
	}

	/**
	 * makeready for all kids
	 * 
	 */

	public void makeReadyAll()
	{
		for (int i = 0; i < vKids.size(); i++)
		{
			vKids.get(i).makeReady();
		}
	}

	/**
	 * simulate makeReady of this node the internal node will be modified to reflect the makeready all required resources should be available
	 */
	public void makeReady()
	{

		if (bExpandGrayBox && EnumType.ProcessGroup.equals(theNode.getEnumType()) && theNode.hasAttribute(AttributeName.TYPES))
		{
			theExpandedNode = theNode.addCombined(theNode.getTypes());
			final VElement resLinks = theNode.getResourceLinks(null);
			if (resLinks != null)
			{
				final int size = resLinks.size();
				for (int i = 0; i < size; i++)
				{
					((JDFResourceLink) resLinks.get(i)).removeAttribute(AttributeName.COMBINEDPROCESSINDEX);
				}
			}

			initAuditPool(theExpandedNode);
			theExpandedNode.copyElement(theNode.getResourceLinkPool(), null);
		}
		else
		{
			theExpandedNode = theNode;
		}
		final VElement nodeLinks = getNodeLinks();
		theStatusCounter.setActiveNode(theExpandedNode, null, nodeLinks);

		final VElement vResLinks = theExpandedNode.getResourceLinks(null);
		if (vResLinks != null)
		{
			final int siz = vResLinks.size();
			for (int i = 0; i < siz; i++)
			{
				final JDFResourceLink rl = (JDFResourceLink) vResLinks.elementAt(i);

				if (EnumUsage.Input.equals(rl.getUsage()))
				{
					final VElement vRes = rl.getTargetVector(-1);
					for (int j = 0; j < vRes.size(); j++)
					{
						final VElement leaves = ((JDFResource) vRes.elementAt(j)).getLeaves(false);
						for (int k = 0; k < leaves.size(); k++)
						{
							final JDFResource r = (JDFResource) leaves.elementAt(k);
							r.setResStatus(EnumResStatus.Available, true);
						}
					}
				}
			}
		}
	}

	public void setActivePart(final VJDFAttributeMap vp, @SuppressWarnings("unused") final boolean bFirst)
	{
		theStatusCounter.setActiveNode(theExpandedNode, vp, getNodeLinks());
	}

	/**
	 * execute for all kids
	 * 
	 */
	public void executeAll(final VJDFAttributeMap parts)
	{
		final Vector<VJDFAttributeMap> vvMap = new Vector<VJDFAttributeMap>();

		if (parts == null)
		{
			if (partsAtOnce > 0)
			{
				final int size = vParts == null ? 0 : vParts.size();
				VJDFAttributeMap vCurr = new VJDFAttributeMap();
				for (int i = 0; i < size; i++)
				{
					if (i % partsAtOnce == 0)
					{
						vCurr = new VJDFAttributeMap();
						vvMap.add(vCurr);
					}
					vCurr.add(vParts.elementAt(i));
				}
			}
			else
			{
				vvMap.add(vParts);
			}
		}
		else
		{
			vvMap.add(parts);
		}
		for (int i = 0; i < vvMap.size(); i++)
		{
			setActivePart(vvMap.get(i), i % partsForAvailable == 0);
			for (int j = 0; j < vKids.size(); j++)
			{
				vKids.get(j).execute(vvMap.get(i), i % partsForAvailable == (partsForAvailable - 1), i % partsForAvailable == 0);
			}
		}
	}

	/**
	 * simulate execution of this node the internal node will be modified to reflect the excution
	 */
	public void execute(final VJDFAttributeMap vMap, final boolean bOutAvail, final boolean bFirst)
	{
		theExpandedNode.setPartStatus(vMap, EnumNodeStatus.Completed, null);
		theNode.setPartStatus(vMap, EnumNodeStatus.Completed, null);
		runphases(good, waste, bOutAvail, bFirst);

		// VElement vResLinks=theExpandedNode.getResourceLinks(null);
		// int siz= vResLinks!=null ? vResLinks.size() : 0;
		// for(int i=0;i<siz;i++)
		// {
		// JDFResourceLink rl=(JDFResourceLink)vResLinks.elementAt(i);
		//
		// if(bOutAvail )
		// {
		// VElement vRes=rl.getTargetVector(-1);
		//
		// for(int j=0;j<vRes.size();j++)
		// {
		// VElement leaves=((JDFResource)vRes.elementAt(j)).getLeaves(false);
		// for(int k=0;k<leaves.size();k++)
		// {
		// JDFResource r=(JDFResource) leaves.elementAt(k);
		// JDFAttributeMap map=r.getPartMap();
		// if(vMap==null || vMap.overlapsMap(map))
		// {
		// r.setResStatus(EnumResStatus.Available, true);
		// EnumResourceClass rc=r.getResourceClass();
		// if(((EnumResourceClass.Handling.equals(rc)||EnumResourceClass.
		// Consumable.equals(rc)||EnumResourceClass.Quantity.equals(rc)) &&
		// EnumUsage.Output.equals(rl.getUsage()))
		// ||(EnumResourceClass.Consumable.equals(rc)
		// &&EnumUsage.Input.equals(rl.getUsage())))
		// {
		// if(good>=0)
		// {
		// map.put("Condition", "Good");
		// JDFPartAmount pa=rl.getCreateAmountPool().getPartAmount(map);
		// double preSet=pa==null ? 0 : pa.getActualAmount(null);
		// rl.setActualAmount(preSet+good, map);
		// }
		// if(waste>=0)
		// {
		// map.put("Condition", "Waste");
		// JDFPartAmount pa=rl.getCreateAmountPool().getPartAmount(map);
		// double preSet=pa==null ? 0 : pa.getActualAmount(null);
		// rl.setActualAmount(preSet+waste, map);
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		theExpandedNode.synchParentAmounts();
		// base requires no generic excute support
		// JDFProcessRun pr=(JDFProcessRun)
		// theNode.getCreateAuditPool().addAudit(EnumAuditType.ProcessRun,
		// null);

	}

	protected void runphases(final int pgood, final int pwaste, final boolean bOutAvail, final boolean bFirst)
	{
		theStatusCounter.setPhase(EnumNodeStatus.InProgress, "NodeDetails", EnumDeviceStatus.Running, "DeviceDetails");
		runSinglePhase(pgood, pwaste, bOutAvail, bFirst);
		finalize(); // prior to processRun
		theStatusCounter.setPhase(EnumNodeStatus.Completed, "NodeDetails", EnumDeviceStatus.Idle, "DeviceDetails");
	}

	/**
	 * schedule this node the nodeinfo will be modified
	 */
	public void schedule(final VJDFAttributeMap nodesToCombine, final int starthours, final int durationhours)
	{
		VJDFAttributeMap nodesToCombineLocal = nodesToCombine;

		theNode.setPartStatus(nodesToCombineLocal, EnumNodeStatus.Waiting, null);
		final JDFNodeInfo ni = theNode.getNodeInfo();
		if (nodesToCombineLocal == null)
		{
			nodesToCombineLocal = new VJDFAttributeMap();
			nodesToCombineLocal.add(null);
		}
		for (int i = 0; i < nodesToCombineLocal.size(); i++)
		{
			final JDFNodeInfo nip = (JDFNodeInfo) ni.getCreatePartition(nodesToCombineLocal.elementAt(i), null);
			final JDFDate d = new JDFDate();
			d.addOffset(0, 0, starthours, 0);
			nip.setStart(d);
			d.addOffset(0, 0, durationhours, 0);
			nip.setEnd(d);
		}
	}

	/**
	 * @param good
	 * @param waste
	 */
	final protected void runSinglePhase(final int pgood, final int pwaste, @SuppressWarnings("unused") final boolean bOutAvail, final boolean bFirst)
	{
		final VElement vResLinks = theExpandedNode.getResourceLinks(null);
		if (vResLinks != null)
		{
			final int siz = vResLinks.size();
			for (int i = 0; i < siz; i++)
			{
				int _good = pgood;
				final JDFResourceLink rl = (JDFResourceLink) vResLinks.elementAt(i);
				// only consume input for first set of runs
				if (!bFirst && EnumUsage.Input.equals(rl.getUsage()))
				{
					_good = 0;
				}
				theStatusCounter.addPhase(rl.getrRef(), _good, pwaste, true);
			}
		}
	}

	/**
	 * do the last steps prior to processrun
	 */
	@Override
	protected void finalize()
	{
		// handled by statuscounter
		// int siz =amountLinks==null ? 0 : amountLinks.size();
		// for(int i=0;i<siz;i++)
		// {
		// theStatusCounter.setResourceAudit(amountLinks.elementAt(i),
		// EnumReason.ProcessResult);
		// }
	}

	protected void setVersion()
	{
		if (theVersion == null)
		{
			theVersion = theNode.getVersion(true);
		}
		if (theVersion == null)
		{
			theVersion = JDFElement.getDefaultJDFVersion();
		}
	}

	/**
	 * initializes this node to a given ICS version
	 * 
	 * @param icsLevel the level to init to (1,2 or 3)
	 */
	public void init()
	{
		initJDF();
		initAuditPool(theNode);
		initDevice(null);

	}

	/**
	 * 
	 */
	protected JDFDevice initDevice(@SuppressWarnings("unused") final JDFNode previousNode)
	{
		JDFDevice dev = (JDFDevice) theNode.getResource(ElementName.DEVICE, EnumUsage.Input, 0);
		if (dev == null && devID != null)
		{
			JDFResourceLink rl = null;
			dev = (JDFDevice) (theParentNode != null ? theParentNode.getResource(ElementName.DEVICE, EnumUsage.Input, 0) : null);
			if (dev == null)
			{
				dev = (JDFDevice) theNode.getCreateResource(ElementName.DEVICE, EnumUsage.Input, 0);
				dev.setDeviceID(devID);
				rl = theNode.getLink(dev, EnumUsage.Input);
			}
			else
			{
				rl = theNode.getLink(dev, EnumUsage.Input);
				if (rl == null)
				{
					rl = theNode.linkResource(dev, EnumUsage.Input, null);
				}
			}
		}
		return dev;
	}

	/**
	 * 
	 */
	public void initAuditPool(final JDFNode node)
	{
		final JDFAuditPool auditPool = node.getCreateAuditPool();
		JDFAudit a = auditPool.getAudit(-1, EnumAuditType.Created, null, null);
		if (a == null)
		{
			a = auditPool.addAudit(EnumAuditType.Created, null);
		}
	}

	/**
	 * @param theNode
	 * @param product
	 * @return
	 */
	protected JDFNode addJDFNode(final JDFNode node, final EnumType t)
	{
		final JDFNode newNode = node.addJDFNode(t);
		newNode.setStatus(EnumNodeStatus.Waiting);
		initAuditPool(newNode);
		return newNode;
	}

	/**
	 * 
	 */
	protected void initJDF()
	{
		final String icsTag = StringUtil.setvString(getICSVersions(), " ", null, null);
		theNode.appendAttribute(AttributeName.ICSVERSIONS, icsTag, null, " ", true);
		theNode.setVersion(theVersion);
		theNode.setMaxVersion(theVersion);
		theNode.setStatus(EnumNodeStatus.Waiting);
		if (!theNode.hasAttribute(AttributeName.DESCRIPTIVENAME))
		{
			theNode.setDescriptiveName("Base Golden Ticket Example Job - version: " + JDFAudit.software());
		}

		if (theParentNode == null && !theNode.hasAttribute(AttributeName.COMMENTURL))
		{
			theNode.setCommentURL(UrlUtil.StringToURL("http://www.example.com").toExternalForm());
		}
	}

	/**
	 * @return
	 */
	public VString getICSVersions()
	{
		final String icsTag = "Base_L" + baseICSLevel + "-" + theVersion.getName();
		return new VString(icsTag, null);
	}

	/**
	 * @return
	 */
	protected VElement getNodeLinks()
	{
		VElement nodeLinks = null;
		if (amountLinks != null)
		{
			nodeLinks = new VElement();
			final VElement resLinks = theExpandedNode.getResourceLinks(null);
			if (resLinks != null)
			{
				final int resLinkSize = resLinks.size();
				for (int i = 0; i < amountLinks.size(); i++)
				{
					for (int j = 0; j < resLinkSize; j++)
					{
						final JDFResourceLink rl = (JDFResourceLink) resLinks.elementAt(j);
						if (rl.matchesString(amountLinks.elementAt(i)))
						{
							nodeLinks.add(rl);
						}
					}
				}
			}
		}

		return nodeLinks;
	}

	/**
	 * gets the current state of the node
	 * 
	 * @return the theNode
	 */
	public JDFNode getNode()
	{
		return theNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String s = "[" + this.getClass().getName() + " Version: " + EnumUtil.getName(theVersion) + "]";
		if (theNode != null)
		{
			s += theNode.toString();
		}
		return s;
	}

	/**
	 * @param string
	 * @param i
	 * @param b
	 */
	public void write2File(final String file, final int indent)
	{
		theNode.getOwnerDocument_KElement().write2File(file, indent, indent == 0);

	}

	public StatusCounter getStatusCounter()
	{
		return theStatusCounter;
	}

	public static String getDeviceURL()
	{
		return deviceURL;
	}

	public static void setDeviceURL(final String pdeviceURL)
	{
		BaseGoldenTicket.deviceURL = pdeviceURL;
	}

	public static String getMisURL()
	{
		return misURL;
	}

	public static void setMisURL(final String _misURL)
	{
		BaseGoldenTicket.misURL = _misURL;
	}

	/**
	 * add the type of amount link for resource audits etc
	 * 
	 * @param link
	 */
	public void addAmountLink(final String link)
	{
		if (amountLinks == null)
		{
			amountLinks = new VString();
		}
		amountLinks.appendUnique(link);
	}

	/**
	 * @param icsLevel
	 */
	protected void initColorantControl()
	{
		JDFResourceLink ccLink = null;
		if (thePreviousNode != null)
		{
			ccLink = theNode.linkResource(thePreviousNode.getResource(ElementName.COLORANTCONTROL, EnumUsage.Input, 0), EnumUsage.Input, null);
		}
		if (ccLink == null && theParentNode != null)
		{
			ccLink = theNode.linkResource(theParentNode.getResource(ElementName.COLORANTCONTROL, EnumUsage.Input, 0), EnumUsage.Input, null);
		}

		final JDFColorantControl cc = (JDFColorantControl) (ccLink == null ? (JDFColorantControl) theNode.getCreateResource(ElementName.COLORANTCONTROL, EnumUsage.Input, 0) : ccLink.getTarget());
		cc.setResStatus(EnumResStatus.Available, false);

		JDFColorPool cp = (JDFColorPool) theNode.getJDFRoot().getChildByTagName(ElementName.COLORPOOL, null, 0, null, false, false);
		if (cp == null)
		{
			cp = (JDFColorPool) theNode.getCreateResource(ElementName.COLORPOOL, EnumUsage.Input, 0);
			if (theParentNode != null)
			{
				theParentNode.getCreateResourcePool().moveElement(cp, null);
			}
		}

		cc.refColorPool(cp);
		for (int i = 4; i < getNCols(); i++)
		{
			cc.getCreateColorantParams().appendSeparation(cols.stringAt(i));
		}
		for (int i = 0; i < getNCols(); i++)
		{
			final String name = cols.stringAt(i);
			final JDFColor c = cp.getCreateColorWithName(name, null);
			if (i == 0)
			{
				c.setCMYK(new JDFCMYKColor(1, 0, 0, 0));
			}
			if (i == 1)
			{
				c.setCMYK(new JDFCMYKColor(0, 1, 0, 0));
			}
			if (i == 2)
			{
				c.setCMYK(new JDFCMYKColor(0, 0, 1, 0));
			}
			if (i == 3)
			{
				c.setCMYK(new JDFCMYKColor(0, 0, 0, 1));
			}
		}
		cc.setProcessColorModel("DeviceCMYK");
		if (nCols[0] != nCols[1])
		{
			for (int ii = 0; ii < 2; ii++)
			{
				final JDFColorantControl ccP = (JDFColorantControl) cc.addPartition(EnumPartIDKey.Side, ii == 0 ? "Front" : "Back");
				final VString colsP = new VString();
				for (int iii = 0; iii < nCols[ii]; iii++)
				{
					colsP.add(cols.stringAt(iii));
				}
				final JDFSeparationList co = ccP.getCreateColorantOrder();
				co.setSeparations(colsP);
			}
		}
		else
		{
			final JDFSeparationList co = cc.getCreateColorantOrder();
			co.setSeparations(cols);
		}
	}

	/**
	 * @param icsLevel
	 */
	protected JDFMedia initPaperMedia()
	{
		JDFResourceLink rlM = null;
		if (thePreviousNode != null)
		{
			JDFMedia media = (JDFMedia) thePreviousNode.getResource(ElementName.MEDIA, EnumUsage.Input, 0);
			if (media != null && !EnumMediaType.Paper.equals(media.getMediaType()))
			{
				media = (JDFMedia) thePreviousNode.getResource(ElementName.MEDIA, EnumUsage.Input, 1);
			}
			if (media != null && !EnumMediaType.Paper.equals(media.getMediaType()))
			{
				media = null;
			}

			if (media == null)
			{
				media = getMediaFromNode(thePreviousNode);
				if (media == null)
				{
					final VElement v = thePreviousNode.getPredecessors(true, false);
					if (v != null)
					{
						final int siz = v.size();
						for (int i = 0; i < siz; i++)
						{
							media = getMediaFromNode((JDFNode) v.get(i));
							if (media != null)
							{
								break;
							}
						}
					}
				}
			}
			rlM = theNode.linkResource(media, EnumUsage.Input, null);
		}

		if (rlM == null && theParentNode != null)
		{
			rlM = theNode.linkResource(theParentNode.getResource(ElementName.MEDIA, EnumUsage.Input, 0), EnumUsage.Input, null);
		}

		paperMedia = (JDFMedia) theNode.getCreateResource(ElementName.MEDIA, null, 0);
		paperMedia.setDescriptiveName("the paper to print on");
		paperMedia.setResStatus(EnumResStatus.Unavailable, false);
		paperMedia.setMediaType(EnumMediaType.Paper);
		paperMedia.setDimensionCM(new JDFXYPair(102, 70));
		paperMedia.setWeight(90);
		paperMedia.setThickness(90 / 0.8);
		return paperMedia;
	}

	/**
	 * @param sNode
	 * @return the media
	 */
	private JDFMedia getMediaFromNode(final JDFNode sNode)
	{
		if (sNode == null)
		{
			return null;
		}
		final JDFLayout lo = (JDFLayout) sNode.getResource(ElementName.LAYOUT, EnumUsage.Input, 0);
		if (lo != null)
		{
			final JDFMedia m = lo.getMedia(0);
			if (m != null)
			{
				return m;
			}
		}

		final JDFStrippingParams sp = (JDFStrippingParams) sNode.getResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input, 0);
		if (sp != null)
		{
			return sp.getMedia(0);
		}
		return null;
	}

	/**
	 * get a reduced partionmap missing the keys in reduceKeys
	 * 
	 * @param reduceKeys
	 * @return the reduced map
	 */
	protected VJDFAttributeMap getReducedMap(final VString reduceKeys)
	{
		if (vParts == null)
		{
			return null;
		}
		final VJDFAttributeMap reducedMap = new VJDFAttributeMap(vParts);
		reducedMap.removeKeys(reduceKeys.getSet());
		if ((reducedMap.size() == 0) || (reducedMap.size() == 1 && reducedMap.elementAt(0).size() == 0))
		{
			return null;
		}
		return reducedMap;
	}

	/**
	 * @param icsLevel
	 */
	protected void initPlateXM(final EnumUsage usage)
	{
		JDFResourceLink rl = null;
		if (thePreviousNode != null)
		{
			// plateset)
			rl = theNode.linkResource(thePreviousNode.getResource(ElementName.EXPOSEDMEDIA, null, 0), usage, null);
		}

		if (rl == null && theParentNode != null)
		{
			rl = theNode.ensureLink(theParentNode.getResource(ElementName.EXPOSEDMEDIA, null, 0), usage, null);
		}

		final JDFExposedMedia xm = (JDFExposedMedia) theNode.getCreateResource(ElementName.EXPOSEDMEDIA, usage, 0);
		xm.setPartUsage(EnumPartUsage.Explicit);
		rl = theNode.getLink(xm, null);

		JDFMedia m = ((JDFExposedMedia) xm.getLeaves(false).elementAt(0)).getMedia();
		if (m == null)
		{
			m = initPlateMedia();
		}
		else
		{
			m = (JDFMedia) m.getResourceRoot();
			if (theParentNode != null)
			{
				theNode.ensureLink(theParentNode.getResource("Media", EnumUsage.Input, 0), EnumUsage.Input, null);
			}
		}
		xm.setResStatus(EnumResStatus.Unavailable, false);
		if (!bPartitionedPlateMedia && xm.getMedia() == null)
		{
			xm.refElement(m);
		}
		if (EnumUsage.Input.equals(usage))
		{
			rl.setProcessUsage(EnumProcessUsage.Plate);
			final JDFResourceLink link = theNode.getLink(m, null);
			if (link != null)
			{
				link.deleteNode();
			}
		}

		if (vParts != null)
		{
			for (int i = 0; i < vParts.size(); i++)
			{
				final JDFAttributeMap part = new JDFAttributeMap(vParts.elementAt(i));
				final JDFResource xmp = xm.getCreatePartition(part, partIDKeys);
				final int ncols = "Front".equals(part.get("Side")) ? nCols[0] : nCols[1];

				for (int j = 0; j < ncols; j++)
				{
					part.put(EnumPartIDKey.Separation, cols.stringAt(j));
					xmp.getCreatePartition(part, partIDKeys);
				}
			}
			if (bPartitionedPlateMedia)
			{
				final VJDFAttributeMap vSheets = getReducedMap(new VString("Side Separation PartVersion", null));
				for (int i = 0; i < vSheets.size(); i++)
				{
					final JDFAttributeMap part = new JDFAttributeMap(vSheets.elementAt(i));
					final JDFExposedMedia xmp = (JDFExposedMedia) xm.getCreatePartition(part, partIDKeys);
					if (xmp.getMedia() == null)
					{
						xmp.refMedia((JDFMedia) m.getCreatePartition(part, null));
					}
				}
			}
		}
	}

	/**
	 * @return the Media
	 */
	protected JDFMedia initPlateMedia()
	{
		if (theParentNode != null)
		{
			theNode.ensureLink(theParentNode.getResource(ElementName.MEDIA, EnumUsage.Input, 0), EnumUsage.Input, null);
		}
		JDFMedia m = (JDFMedia) theNode.getCreateResource(ElementName.MEDIA, EnumUsage.Input, 0);
		if (EnumMediaType.Paper.equals(m.getMediaType()))
		{
			m = (JDFMedia) theNode.getCreateResource(ElementName.MEDIA, EnumUsage.Input, 1);
		}
		m.setResStatus(EnumResStatus.Available, false);
		m.makeRootResource(null, theNode.getJDFRoot(), true);
		theNode.getJDFRoot().getCreateResourcePool().moveElement(m, null);
		m.setDescriptiveName("the plates to use");
		m.setMediaType(EnumMediaType.Plate);
		m.setPartUsage(EnumPartUsage.Implicit);
		if (bPartitionedPlateMedia && vParts != null)
		{
			final VJDFAttributeMap vSheets = getReducedMap(new VString("Side Separation PartVersion", null));
			for (int i = 0; i < vSheets.size(); i++)
			{
				final JDFAttributeMap part = new JDFAttributeMap(vSheets.elementAt(i));
				// JDFResource mm=
				m.getCreatePartition(part, partIDKeys);
			}

		}
		else
		{
			m.setDimensionCM(new JDFXYPair(103, 71));
		}
		return m;
	}

	public int getNCols()
	{
		return nCols[0] == 0 ? cols.size() : Math.max(nCols[0], nCols[1]);
	}

	/**
	 * 
	 */
	protected JDFRunList initDocumentRunList()
	{
		JDFRunList rl = (JDFRunList) theNode.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, 0);
		JDFResourceLink rll = theNode.getLink(rl, null);
		if ("Marks".equals(rll.getProcessUsage()))
		{
			rl = (JDFRunList) theNode.getCreateResource(ElementName.RUNLIST, EnumUsage.Input, 1);
			rll = theNode.getLink(rl, null);
		}
		rll.setProcessUsage(EnumProcessUsage.Document);
		rl.addPDF(m_pdfFile, 0, -1);
		rl.setNPage(4);
		rl.setDescriptiveName("Description of this RunList");
		return rl;
	}

	/**
	 * @param icsLevel
	 */
	protected JDFComponent initOutputComponent()
	{
		if (thePreviousNode != null)
		{
			final JDFResource parentOutComp = thePreviousNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			if (parentOutComp != null)
			{
				theNode.linkResource(parentOutComp, EnumUsage.Input, null);
			}
		}
		JDFComponent outComp = (JDFComponent) (theParentNode != null ? theParentNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0) : null);
		if (outComp == null)
		{
			outComp = (JDFComponent) theNode.getCreateResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			outComp.setComponentType(EnumComponentType.FinalProduct, EnumComponentType.Sheet);
			outComp.setProductType("Unknown");
		}
		else
		{
			theNode.linkResource(outComp, EnumUsage.Output, null);
		}

		final JDFResourceLink rl = theNode.getLink(outComp, EnumUsage.Output);
		if (vParts != null)
		{
			final VJDFAttributeMap reducedMap = getReducedMap(new VString("Side Separation", " "));
			if (reducedMap != null)
			{
				final int size = reducedMap.size();
				for (int i = 0; i < size; i++)
				{
					final JDFAttributeMap part = reducedMap.elementAt(i);
					final JDFResource partComp = outComp.getCreatePartition(part, partIDKeys);
					partComp.setDescriptiveName("Description for Component part# " + i);
					final JDFAttributeMap newMap = new JDFAttributeMap(part);
					newMap.put(AttributeName.CONDITION, "Good");
					rl.setAmount(good, newMap);
				}
			}
		}
		else
		{
			outComp.setDescriptiveName("MIS-CP output Component");
		}
		// outComp.getCreateLayout();
		final JDFMedia inMedia = (JDFMedia) theNode.getResource(ElementName.MEDIA, EnumUsage.Input, 0);
		if (inMedia != null)
		{
			outComp.setDimensions(inMedia.getDimension());
		}
		return outComp;

	}

}
