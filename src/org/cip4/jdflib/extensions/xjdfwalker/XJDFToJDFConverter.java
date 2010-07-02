/**
 * 
 */
package org.cip4.jdflib.extensions.xjdfwalker;

import java.util.Iterator;
import java.util.Map;

import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.extensions.IntentHelper;
import org.cip4.jdflib.extensions.ProductHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.xjdfwalker.IDFinder.IDPart;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFBindItem;
import org.cip4.jdflib.resource.JDFEdgeGluing;
import org.cip4.jdflib.resource.JDFEmbossingItem;
import org.cip4.jdflib.resource.JDFHardCoverBinding;
import org.cip4.jdflib.resource.JDFInsert;
import org.cip4.jdflib.resource.JDFNumberItem;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFProofItem;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFSoftCoverBinding;
import org.cip4.jdflib.resource.JDFStripBinding;
import org.cip4.jdflib.resource.JDFStrippingParams;
import org.cip4.jdflib.resource.JDFTabs;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.intent.JDFArtDelivery;
import org.cip4.jdflib.resource.intent.JDFDropIntent;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.intent.JDFIntentResource;
import org.cip4.jdflib.resource.intent.JDFShapeCut;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.resource.process.postpress.JDFChannelBinding;
import org.cip4.jdflib.resource.process.postpress.JDFCoilBinding;
import org.cip4.jdflib.resource.process.postpress.JDFPlasticCombBinding;
import org.cip4.jdflib.resource.process.postpress.JDFRingBinding;
import org.cip4.jdflib.resource.process.postpress.JDFSaddleStitching;
import org.cip4.jdflib.resource.process.postpress.JDFSideSewing;
import org.cip4.jdflib.resource.process.postpress.JDFThreadSealing;
import org.cip4.jdflib.resource.process.postpress.JDFThreadSewing;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class XJDFToJDFConverter extends BaseElementWalker
{
	JDFDoc jdfDoc;
	// JDFNode theNode;
	Map<String, IDPart> idMap;
	boolean firstConvert;
	protected JDFNode currentJDFNode = null;
	/**
	 * if true, create the product, else ignore it
	 */
	public boolean createProduct = true;
	/**
	 * 
	 */
	private EnumVersion version = EnumVersion.Version_1_3;

	/**
	 * @param template the jdfdoc to fill this into
	 * 
	 */
	public XJDFToJDFConverter(final JDFDoc template)
	{
		super(new BaseWalkerFactory());
		firstConvert = true;
		jdfDoc = template == null ? null : template.clone();
		// theNode = null;
		idMap = null;
	}

	/**
	 * @param xjdf
	 * @return the converted jdf
	 */
	public JDFDoc convert(final KElement xjdf)
	{
		if (xjdf == null)
		{
			return null;
		}
		if (jdfDoc == null)
		{
			jdfDoc = new JDFDoc("JDF");
			jdfDoc.setBodyPart(xjdf.getOwnerDocument_KElement().getBodyPart());
		}
		if (!firstConvert)
		{
			JDFNode root = jdfDoc.getJDFRoot();
			if (!"Product".equals(root.getType()))
			{
				root = createProductRoot(root);
			}
		}
		final JDFNode theNode = findNode(xjdf, true);
		if (theNode == null)
		{
			return null;
		}

		idMap = new IDFinder().getMap(xjdf);
		walkTree(xjdf, theNode);
		final JDFNode root = jdfDoc.getJDFRoot();
		if ("Product".equals(root.getType()))
		{
			mergeProductLinks(theNode, root);
		}
		cleanResources(theNode);
		firstConvert = false;
		return jdfDoc;
	}

	/**
	 * @param theNode
	 */
	private void cleanResources(final JDFNode theNode)
	{
		final JDFResourceLinkPool rlp = theNode.getResourceLinkPool();
		final VElement vRes = rlp == null ? null : rlp.getPoolChildren(null, null, null);
		if (vRes != null)
		{
			for (int i = 0; i < vRes.size(); i++)
			{
				final JDFResourceLink rl = (JDFResourceLink) vRes.get(i);
				final JDFResource r = rl.getLinkRoot();
				if (r != null)
				{
					final EnumResStatus s = r.getStatusFromLeaves(false);
					if (s != null)
					{
						r.setResStatus(s, false);
					}
				}
			}
		}
	}

	/**
	 * @param xjdf
	 * @return true if the element can be converted
	 */
	public boolean canConvert(final KElement xjdf)
	{
		return xjdf == null ? false : XJDF20.rootName.equals(xjdf.getLocalName());
	}

	/**
	 * find and optionally create the appropriate node
	 * @param xjdf
	 * @param create if true, creat the new node
	 * @return the node
	 */
	private JDFNode findNode(KElement xjdf, final boolean create)
	{
		if (xjdf != null)
		{
			xjdf = xjdf.clone();
		}
		final JDFNode root = jdfDoc.getJDFRoot();
		final String jpID = xjdf.getAttribute(AttributeName.JOBPARTID, null, null);
		JDFNode n = root.getJobPart(jpID, null);
		if (n == null)
		{
			if (!root.hasAttribute(AttributeName.TYPE))
			{
				return root;
			}
			if (jpID == null)
			{
				final VElement nodes = root.getvJDFNode(null, null, false);
				final VString xTypes = StringUtil.tokenize(xjdf.getAttribute(AttributeName.TYPES), null, false);
				for (int i = 0; i < nodes.size(); i++)
				{
					final JDFNode n2 = (JDFNode) nodes.get(i);
					final VString vtypes = n2.getAllTypes();
					if (vtypes.containsAll(xTypes))
					{
						return n2;
					}
				}
			}
		}
		if (n == null && create)
		{
			n = root.addProcessGroup(new VString(xjdf.getAttribute(AttributeName.TYPES), null));
		}
		return n;
	}

	/**
	 * @param toCheck
	 * @return
	 */
	boolean isXResourceElement(final KElement toCheck)
	{
		boolean bReturn = false;

		if (toCheck != null)
		{
			KElement parent = toCheck.getParentNode_KElement();
			if (parent == null)
			{
				return bReturn;
			}

			KElement parent2 = parent.getParentNode_KElement();
			if (parent2 == null)
			{
				return bReturn;
			}

			String parentName = parent2.getLocalName();
			boolean bL1 = parentName.endsWith("Set") && toCheck.getLocalName().equals(parent2.getAttribute("Name"));
			bL1 = bL1 || parentName.equals("Product") && toCheck.getLocalName().equals(parent.getAttribute("Name"));
			bReturn = bL1;
		}

		return bReturn;
	}

	/**
	 * @param toCheck
	 * @return
	 */
	boolean isXResource(final KElement toCheck)
	{
		final KElement parent = toCheck.getParentNode_KElement();
		if (parent == null)
		{
			return false;
		}

		final boolean bL1 = parent.getLocalName().endsWith("Set");
		return bL1 && parent.hasAttribute(AttributeName.NAME);
	}

	/**
	 * make sure we have a product in case we have multiple nodes
	 * @param theNode
	 * @return
	 */
	protected JDFNode createProductRoot(JDFNode theNode)
	{
		final JDFNode parent = (JDFNode) jdfDoc.createElement("JDF");
		parent.setType(EnumType.Product);
		theNode = (JDFNode) parent.moveElement(theNode, null);
		jdfDoc.appendChild(parent);

		parent.moveAttribute(AttributeName.JOBID, theNode);
		parent.moveAttribute(AttributeName.VERSION, theNode);
		parent.setJobPartID("rootPart");
		parent.moveElement(theNode.getResourcePool(), null);

		final JDFComponent c = (JDFComponent) parent.addResource(ElementName.COMPONENT, EnumUsage.Output);
		c.setDescriptiveName("dummy output");
		c.setComponentType(EnumComponentType.FinalProduct, null);

		mergeProductLinks(theNode, parent);
		firstConvert = true;
		return parent;
	}

	/**
	 * @param theNode
	 * @param parent
	 */
	private void mergeProductLinks(final JDFNode theNode, final JDFNode parent)
	{
		mergeProductLink(theNode, parent, ElementName.CUSTOMERINFO, EnumUsage.Input);
		mergeProductLink(theNode, parent, ElementName.NODEINFO, EnumUsage.Input);
		final JDFResource r = parent.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
		if (r != null && "dummy outout".equals(r.getDescriptiveName()))
		{
			final JDFResource rNode = theNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			if (rNode != null)
			{
				parent.getLink(r, EnumUsage.Output).deleteNode();
				r.deleteNode();
			}
		}
		mergeProductLink(theNode, parent, ElementName.COMPONENT, EnumUsage.Output);
	}

	/**
	 * @param theNode
	 * @param parent
	 * @param resName
	 * @param enumUsage
	 */
	private void mergeProductLink(final JDFNode theNode, final JDFNode parent, final String resName, final EnumUsage enumUsage)
	{
		final JDFResource r = parent.getResource(resName, enumUsage, 0);

		if (r == null)
		{
			final JDFResourceLink link = theNode.getLink(0, resName, new JDFAttributeMap("Usage", enumUsage), null);
			if (link != null)
			{
				parent.ensureLink(link.getLinkRoot(), enumUsage, null);
			}
		}
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(final EnumVersion version)
	{
		this.version = version;
	}

	/**
	 * @return the version
	 */
	public EnumVersion getVersion()
	{
		return version;
	}

	/**
	 * 
	 * @param e
	 * @param trackElem
	 */
	protected void attributesToSpan(final KElement e, final KElement trackElem)
	{
		final JDFAttributeMap map = e.getAttributeMap();
		final JDFElement ir = (JDFElement) trackElem;
		final VString keys = map.getKeys();
		final VString knownElements = ir.knownElements();
		for (final String name : keys)
		{
			if (knownElements.contains(name))
			{
				ir.appendElement(name).setAttribute("Actual", map.get(name));
				e.removeAttribute(name);
			}
		}
	}

	/**
	 * @param rl
	 * @param partmap
	 * @param map
	 * @param a
	 */
	protected void moveToLink(JDFResourceLink rl, final JDFAttributeMap partmap, final JDFAttributeMap map, final String a)
	{
		if (map == null || map.isEmpty())
			return; // nop
		final VString vGW = new VString("Good Waste", null);
		for (String gw : vGW)
		{
			final JDFAttributeMap pm = new JDFAttributeMap(partmap);
			pm.put("Condition", gw);
			if (map.get(a + gw) != null)
			{
				if (rl != null)
				{
					rl.setAmountPoolAttribute(a, map.get(a + gw), null, pm);
				}
				map.remove(a + gw);
			}
		}
	}

	/**
	 * @param partmap
	 * @param map
	 * @param rl
	 */
	protected void moveAmountsToLink(JDFAttributeMap partmap, final JDFAttributeMap map, final JDFResourceLink rl)
	{
		moveToLink(rl, partmap, map, AttributeName.AMOUNT);
		moveToLink(rl, partmap, map, AttributeName.ACTUALAMOUNT);
		moveToLink(rl, partmap, map, AttributeName.MAXAMOUNT);
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkXElement extends BaseWalker
	{

		/**
		 *  
		 */
		@SuppressWarnings("synthetic-access")
		public WalkXElement()
		{
			super(getFactory());
		}

		/**
		 * @param e
		 * @return element to continue with if must continue
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final VElement v = trackElem.getChildElementVector(null, null);
			for (int i = 0; i < v.size(); i++)
			{
				final KElement kk = v.get(i);
				if (e.isEqual(kk))
				{
					return null;
				}
			}
			cleanRefs(e, trackElem);
			final KElement e2 = trackElem.copyElement(e, null);
			e2.removeChildren(null, null, null); // will be copied later
			return e2;
		}

		/**
		 * @param val
		 * @return
		 */
		protected String getRefName(final String val)
		{
			final String refName = val.endsWith("Refs") ? StringUtil.leftStr(val, -1) : val;
			return refName;
		}

		/**
		 * @param e
		 * @param trackElem
		 */
		protected void cleanRefs(final KElement e, final KElement trackElem)
		{
			final JDFAttributeMap map = e.getAttributeMap();
			if (map == null)
			{
				return;
			}
			final VString keys = map.getKeys();
			if (keys != null)
			{
				final int keySize = keys.size();
				for (int i = 0; i < keySize; i++)
				{
					final String val = keys.get(i);
					if ((val.endsWith("Ref") || val.endsWith("Refs")) && !val.equals("rRef"))
					{
						final String values = map.get(val);
						VString vValues = StringUtil.tokenize(values, null, false);
						for (String value : vValues)
						{
							final IDPart p = idMap.get(value);
							if (p != null)
							{
								final String refName = getRefName(val);
								if (refName != null)
								{
									final KElement refOld = trackElem != null ? trackElem.getElement(refName) : null;
									final KElement ref = e.appendElement(refName);
									ref.setAttribute("rRef", p.getID());

									final VJDFAttributeMap vpartmap = p.getPartMap();
									if (vpartmap != null)
									{
										for (int j = 0; j < vpartmap.size(); j++)
										{
											ref.appendElement(ElementName.PART).setAttributes(vpartmap.get(j));
										}
									}
									// we've been here already
									if (ref.isEqual(refOld))
									{
										ref.deleteNode();
									}
								}
								e.removeAttribute(val);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the xjdf root
	 */
	public class WalkXJDF extends WalkXElement
	{
		// ///////////////////////////////////////////////////////////////////////////////
		/**
		 * @param e
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			currentJDFNode = (JDFNode) trackElem;
			currentJDFNode.setAttributes(e);
			currentJDFNode.setVersion(getVersion());
			removeInheritedJobID();
			currentJDFNode.setType(EnumType.ProcessGroup);
			return currentJDFNode;
		}

		/**
		 *  
		 */
		private void removeInheritedJobID()
		{
			final JDFNode parent = currentJDFNode.getParentJDF();
			if (parent != null)
			{
				final String jobID = StringUtil.getNonEmpty(parent.getJobID(true));
				final String myJobID = StringUtil.getNonEmpty(currentJDFNode.getJobID(false));
				if (myJobID != null && myJobID.equals(jobID))
				{
					currentJDFNode.removeAttribute(AttributeName.JOBID);
				}
			}
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && XJDF20.rootName.equals(toCheck.getLocalName());
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the xjdf root
	 */
	public class WalkSpan extends WalkXElement
	{
		// ///////////////////////////////////////////////////////////////////////////////
		/**
		 * invert XXXSpan/@Datatype=foo to FooSpan/@Name=Datatype
		 * @param e
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement eNew = trackElem.appendElement(e.getAttribute("Name"));
			eNew.setAttributes(e);
			eNew.removeAttribute(AttributeName.NAME);
			eNew.setAttribute(AttributeName.DATATYPE, e.getLocalName());
			return eNew;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck.getLocalName().endsWith("Span");
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkSet extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFNode parent = (JDFNode) trackElem;
			final JDFNode root = parent.getJDFRoot();
			EnumUsage inOut = EnumUsage.getEnum(e.getAttribute(AttributeName.USAGE));
			String id = e.getAttribute(AttributeName.ID, null, null);
			if (inOut == null && "ParameterSet".equals(e.getLocalName()))
			{
				final SetHelper h = new SetHelper(e);
				final String name = h.getName();
				if (!ElementName.CONTACT.equals(name))
				{
					inOut = EnumUsage.Input;
				}
			}

			JDFResource r = null;
			final KElement idElem = root.getCreateResourcePool().getChildWithAttribute(null, "ID", null, id, 0, true);
			if (idElem instanceof JDFResource)
			{
				r = (JDFResource) idElem;
			}
			else
			{
				r = (JDFResource) root.getChildWithAttribute(null, "ID", null, id, 0, false);
				if (r != null)
				{
					final JDFResourcePool rp = root.getCreateResourcePool();
					if (!rp.equals(r.getParentNode_KElement()))
					{
						rp.moveElement(r, null);
					}
				}
			}
			if (r == null)
			{
				SetHelper h = new SetHelper(e);
				String name = h.getName();
				if (name != null)
				{
					r = root.addResource(name, null);
					r.removeAttribute(AttributeName.STATUS); // don't want the default
				}
			}
			if (r != null)
			{
				r.setAttributes(e);
				if (r.getResourceClass() == null)
				{
					final String name = StringUtil.leftStr(e.getLocalName(), -3);
					r.setResourceClass("Parameter".equals(name) ? EnumResourceClass.Parameter : EnumResourceClass.Handling);
				}
				if (inOut != null)
				{
					final JDFResourceLink rl = parent.ensureLink(r, inOut, null);
					if (rl != null)
					{
						rl.setrRef(id);
						r.removeAttribute(AttributeName.USAGE);
						rl.moveAttribute(AttributeName.PROCESSUSAGE, r);
						rl.moveAttribute(AttributeName.AMOUNT, r);
						rl.moveAttribute(AttributeName.ACTUALAMOUNT, r);
						rl.moveAttribute(AttributeName.MAXAMOUNT, r);
						rl.moveAttribute(AttributeName.MINAMOUNT, r);
					}
				}

				// not linked are also available - they will typically be referenced resources
				if (!r.hasAttribute(AttributeName.STATUS))
					r.setResStatus(EnumUsage.Output.equals(inOut) ? EnumResStatus.Unavailable : EnumResStatus.Available, true);

				r.removeAttribute(AttributeName.NAME);
				r.removeAttribute(AttributeName.USAGE);
			}
			return r;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			final KElement parent = toCheck.getParentNode_KElement();
			final boolean bL1 = parent != null && (parent.getLocalName().equals(XJDF20.rootName) || parent.getLocalName().equals("Product"));
			String localName = toCheck.getLocalName();
			return bL1 && super.matches(toCheck) && localName.endsWith("Set")
					&& (toCheck.hasAttribute(AttributeName.NAME) || toCheck.getElement(StringUtil.leftStr(localName, -3)) != null);
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkResource extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			cleanRefs(e, trackElem);
			e.removeAttribute("Class");
			trackElem.setAttributes(e);
			return trackElem;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			// test on grandparent
			return super.matches(toCheck) && isXResourceElement(toCheck);
		}
	}

	/**
	 * 
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * Mar 17, 2010
	 */
	public class WalkIntentResource extends WalkResource
	{
		/**
		 * @param e
		 * @return thr created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			attributesToSpan(e, trackElem);
			return super.walk(e, trackElem);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && (toCheck instanceof JDFIntentResource);
		}
	}

	/**
	 * 
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * Mar 17, 2010
	 */
	public class WalkIntentElement extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created element
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			attributesToSpan(e, trackElem);
			return super.walk(e, trackElem);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return (toCheck instanceof JDFDropIntent) || (toCheck instanceof JDFDropItemIntent) || (toCheck instanceof JDFArtDelivery) || (toCheck instanceof JDFBindItem)
					|| (toCheck instanceof JDFChannelBinding) || (toCheck instanceof JDFCoilBinding) || (toCheck instanceof JDFEdgeGluing)
					|| (toCheck instanceof JDFHardCoverBinding) || (toCheck instanceof JDFPlasticCombBinding) || (toCheck instanceof JDFRingBinding)
					|| (toCheck instanceof JDFSaddleStitching) || (toCheck instanceof JDFSideSewing) || (toCheck instanceof JDFSoftCoverBinding)
					|| (toCheck instanceof JDFStripBinding) || (toCheck instanceof JDFTabs) || (toCheck instanceof JDFThreadSealing) || (toCheck instanceof JDFThreadSewing)
					|| (toCheck instanceof JDFEmbossingItem) || (toCheck instanceof JDFInsert) || (toCheck instanceof JDFNumberItem) || (toCheck instanceof JDFProofItem)
					|| (toCheck instanceof JDFShapeCut);
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkReplace extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			trackElem.removeChildren(e.getNodeName(), null, null);
			return super.walk(e, trackElem);
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && (toCheck instanceof JDFAuditPool);
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkProduct extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			JDFNode theNode = (JDFNode) trackElem;
			if ("Product".equals(theNode.getType()) || theNode != currentJDFNode)
			{
				theNode = theNode.addProduct();
			}
			else
			{
				theNode = createProductRoot(theNode);
			}
			theNode.setAttributes(e);
			fixComponent(theNode, e);
			return theNode;
		}

		/**
		 * @param theNode
		 * @param xjdfProduct 
		 */
		private void fixComponent(final JDFNode theNode, KElement xjdfProduct)
		{
			JDFComponent c = (JDFComponent) theNode.getResource(ElementName.COMPONENT, EnumUsage.Output, 0);
			if (c == null)
			{
				c = (JDFComponent) theNode.addResource(ElementName.COMPONENT, EnumUsage.Output);
				EnumComponentType partialFinal = new ProductHelper(xjdfProduct).isRootProduct() ? EnumComponentType.FinalProduct : EnumComponentType.PartialProduct;
				c.setComponentType(partialFinal, null);
			}
			c.moveAttribute(AttributeName.PRODUCTTYPE, theNode);
			c.moveAttribute(AttributeName.PRODUCTTYPEDETAILS, theNode);
			final JDFResourceLink rl = theNode.getLink(c, EnumUsage.Output);
			if (rl != null)
			{
				rl.moveAttribute(AttributeName.AMOUNT, theNode);
			}
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && (toCheck.getLocalName().equals("Product"));
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkProductList extends WalkXElement
	{
		/**
		 * 
		 */
		public WalkProductList()
		{
			super();
			foundProduct = false;
		}

		boolean foundProduct;

		/**
		 * @param e
		 * @return the root, else null if we are in a second pass
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			e.deleteNode();
			final boolean bFirst = foundProduct;
			foundProduct = true;
			// only convert products in the first pass
			// TODO rethink product conversion switch
			if (createProduct && !bFirst && e.numChildElements("Product", null) > 1)
			{
				createProductRoot(currentJDFNode);
			}
			return createProduct && !bFirst ? jdfDoc.getJDFRoot() : null;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && (toCheck.getLocalName().equals("ProductList"));
		}
	}

	/**
	 * continue walking on these withot copying e
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkContinue extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			return trackElem;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			// final String name = toCheck.getLocalName();
			return false;
		}

	}

	/**
	 * simply stop walking on these
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkIgnore extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			return null;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && (toCheck instanceof JDFPart) && isXResource(toCheck.getParentNode_KElement());
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkXJDFColorResource extends WalkXJDFResource
	{
		/**
		 * @param e
		 * @param trackElem
		 * @return
		 */
		@Override
		protected JDFResource createPartition(final KElement e, final KElement trackElem, final JDFPart part)
		{
			final JDFNode theNode = ((JDFElement) trackElem).getParentJDF();
			final JDFResource r = (JDFResource) trackElem;
			final String sep = part.getSeparation();
			final KElement col = r.getChildWithAttribute("Color", "Name", null, sep, 0, true);
			if (col != null)
			{
				return null; // been here already
			}
			final JDFResource rPart = r.getCreatePartition(part.getPartMap(), part.guessPartIDKeys());
			final JDFResourceLink rll = theNode.getLink(r, null);
			if (rll != null)
			{
				rll.removeChildren(ElementName.PART, null, null);
			}
			rPart.renameElement(ElementName.COLOR, null);
			rPart.renameAttribute(AttributeName.SEPARATION, AttributeName.NAME, null, null);
			r.removeFromAttribute(AttributeName.PARTIDKEYS, AttributeName.SEPARATION, null, " ", -1);
			return rPart;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			final KElement parent = toCheck.getParentNode_KElement();
			if (parent == null)
			{
				return false;
			}

			final boolean bL1 = parent.getLocalName().endsWith("Set");
			return bL1 && super.matches(toCheck) && ElementName.COLOR.equals(parent.getAttribute(AttributeName.NAME));
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement rPart = super.walk(e, trackElem);
			if (rPart != null)
			{
				rPart.removeAttribute(AttributeName.STATUS);
			}
			return rPart;

		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkXJDFResource extends WalkXElement
	{

		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFNode theNode = currentJDFNode == null ? ((JDFElement) trackElem).getParentJDF() : currentJDFNode;
			final JDFPart part = (JDFPart) e.getElement(ElementName.PART);
			JDFAttributeMap partmap = null;
			final JDFResource newPartition;
			if (part != null)
			{
				newPartition = createPartition(e, trackElem, part);
				partmap = part.getPartMap();
			}
			else
			{
				newPartition = (JDFResource) trackElem;
			}
			if (newPartition == null)
			{
				return null;
			}

			final JDFAttributeMap map = e.getAttributeMap();
			map.remove(AttributeName.ID);
			final JDFResourceLink rl = theNode.getLink(newPartition, null);
			moveAmountsToLink(partmap, map, rl);
			newPartition.setAttributes(map);

			return newPartition;
		}

		/**
		 * @param e
		 * @param trackElem
		 * @param part
		 * @return
		 */
		protected JDFResource createPartition(final KElement e, final KElement trackElem, final JDFPart part)
		{
			final JDFNode theNode = currentJDFNode == null ? ((JDFElement) trackElem).getParentJDF() : currentJDFNode;
			final JDFResource r = (JDFResource) trackElem;
			final JDFAttributeMap partMap = part.getPartMap();
			final JDFResource rPart = r.getCreatePartition(partMap, part.guessPartIDKeys());
			final JDFResourceLink rll = theNode.getLink(r, null);
			final VJDFAttributeMap partMapVector = rll != null ? rll.getPartMapVector() : null;
			if (rll != null && (partMapVector == null || !partMapVector.contains(partMap)))
			{
				rll.moveElement(part, null);
			}
			return rPart;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && isXResource(toCheck);
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkXJDFColorSet extends WalkSet
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && ElementName.COLOR.equals(toCheck.getAttribute(AttributeName.NAME));
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFNode theNode = (JDFNode) trackElem;
			final KElement k2 = super.walk(e, trackElem);
			final JDFResource r = (JDFResource) k2;
			final JDFResourceLink rl = theNode.getLink(r, null);
			r.renameElement(ElementName.COLORPOOL, null);
			if (rl != null)
			{
				rl.renameElement("ColorPoolLink", null);
			}
			return k2;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
	 */
	public class WalkStrippingParams extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFStrippingParams;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXElement#getRefName(java.lang.String)
		 */
		@Override
		protected String getRefName(final String val)
		{
			if ("PaperRef".equals(val) || "PlateRef".equals(val) || "ProofRef".equals(val))
			{
				return "MediaRef";
			}
			return super.getRefName(val);
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
	 */
	public class WalkMedia extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFMedia;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement rPart = super.walk(e, trackElem);
			if (rPart != null)
			{
				final JDFResource root = ((JDFResource) rPart).getResourceRoot();
				if (root != null && root != rPart && !root.hasAttribute(AttributeName.MEDIATYPE))
				{
					root.copyAttribute(AttributeName.MEDIATYPE, rPart);
				}
			}
			return rPart;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
	 */
	public class WalkColorantControl extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFColorantControl;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement rPart = super.walk(e, trackElem);
			createSeparationList(rPart, ElementName.COLORANTPARAMS);
			createSeparationList(rPart, ElementName.COLORANTORDER);
			createSeparationList(rPart, ElementName.DEVICECOLORANTORDER);
			return rPart;
		}

		/**
		 * @param rPart
		 * @param elem
		 */
		private void createSeparationList(final KElement rPart, final String elem)
		{
			final JDFColorantControl cc = (JDFColorantControl) rPart;
			final String c = rPart.getAttribute(elem, null, null);
			if (c != null)
			{
				((JDFSeparationList) cc.getCreateElement(elem)).setSeparations(new VString(c, null));
				rPart.removeAttribute(elem);
			}
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
	 */
	public class WalkRunList extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFRunList;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			splitLayoutElem(e);
			return super.walk(e, trackElem);
		}

		/**
		 * split a RunList into a RunList and a RunList/LayoutElement
		 * @param e
		 */
		private void splitLayoutElem(final KElement e)
		{
			if (!e.hasChildElement(ElementName.LAYOUTELEMENT, null))
			{
				final KElement loe = e.appendElement(ElementName.LAYOUTELEMENT);
				final VString vAtt = loe.knownAttributes();
				final JDFAttributeMap map = e.getAttributeMap();
				final Iterator<String> it = map.getKeyIterator();
				while (it.hasNext())
				{
					final String s = it.next();
					if (vAtt.contains(s))
					{
						loe.setAttribute(s, map.get(s));
						e.removeAttribute(s);
					}
				}
				final VString vElm = loe.knownElements();
				final VElement vMyElm = e.getChildElementVector(null, null);
				for (int i = 0; i < vMyElm.size(); i++)
				{
					if (vElm.contains(vMyElm.get(i).getLocalName()))
					{
						loe.moveElement(vMyElm.get(i), null);
					}
				}
			}
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for Media elements
	 */
	public class WalkNodeInfo extends WalkResource
	{
		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck instanceof JDFNodeInfo;
		}

		/**
		 * @see org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter.WalkXJDFResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFNode theNode = currentJDFNode == null ? ((JDFElement) trackElem).getParentJDF() : currentJDFNode;
			JDFNodeInfo ni = (JDFNodeInfo) trackElem;
			JDFAttributeMap partmap = ni.getPartMap();

			final JDFAttributeMap map = e.getAttributeMap();
			final JDFAttributeMap map2 = map.clone();
			final JDFResourceLink rl = theNode.getLink(ni, null);
			moveAmountsToLink(partmap, map, rl);
			map2.removeKeys(map.getKeys());
			e.removeAttributes(map2.getKeys());

			return super.walk(e, trackElem);
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for non partitioned intent elements
	 */
	public class WalkIntent extends WalkXElement
	{
		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final JDFNode parent = (JDFNode) trackElem;
			final JDFNode root = parent.getJDFRoot();
			EnumUsage inOut = EnumUsage.getEnum(e.getAttribute(AttributeName.USAGE));
			if (inOut == null)
			{
				inOut = EnumUsage.Input;
			}
			String id = e.getAttribute(AttributeName.ID, null, null);
			if (id == null)
			{
				// we need an id to copy. technically no id is invalid, but... whatever
				id = "r" + KElement.uniqueID(0);
				e.setAttribute(AttributeName.ID, id);
			}
			JDFResource r = null;
			final KElement idElem = parent.getCreateResourcePool().getChildWithAttribute(null, "ID", null, id, 0, true);
			if (idElem instanceof JDFResource)
			{
				r = (JDFResource) idElem;
			}
			else
			{
				r = (JDFResource) root.getChildWithAttribute(null, "ID", null, id, 0, false);
				if (r != null)
				{
					final JDFResourcePool rp = root.getCreateResourcePool();
					if (!rp.equals(r.getParentNode_KElement()))
					{
						rp.moveElement(r, null);
					}
				}
			}
			if (r == null)
			{
				IntentHelper h = new IntentHelper(e);
				String name = h.getName();
				if (name != null)
				{
					r = parent.addResource(name, null);
					r.removeAttribute(AttributeName.STATUS);
				}
			}
			if (r != null)
			{
				r.setAttributes(e);
				if (r.getResourceClass() == null)
				{
					r.setResourceClass(EnumResourceClass.Intent);
				}
				final JDFResourceLink rl = parent.ensureLink(r, inOut, null);
				if (rl != null)
				{
					rl.setrRef(id);
					r.removeAttribute(AttributeName.USAGE);
					rl.moveAttribute(AttributeName.PROCESSUSAGE, r);
					rl.moveAttribute(AttributeName.AMOUNT, r);
					rl.moveAttribute(AttributeName.ACTUALAMOUNT, r);
					rl.moveAttribute(AttributeName.MAXAMOUNT, r);
					rl.moveAttribute(AttributeName.MINAMOUNT, r);
				}
				r.removeAttribute(AttributeName.NAME);
				r.removeAttribute(AttributeName.USAGE);
				if (!r.hasAttribute(AttributeName.STATUS))
					r.setResStatus(EnumResStatus.Available, true);
			}
			return r;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			final KElement parent = toCheck.getParentNode_KElement();
			final boolean bL1 = parent != null && parent.getLocalName().equals("Product");
			return bL1 && super.matches(toCheck) && toCheck.getLocalName().equals("Intent");
		}

	}
}
