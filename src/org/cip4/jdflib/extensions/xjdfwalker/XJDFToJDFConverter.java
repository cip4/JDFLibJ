/**
 * 
 */
package org.cip4.jdflib.extensions.xjdfwalker;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class XJDFToJDFConverter extends BaseElementWalker
{
	JDFDoc jdfDoc;
	JDFNode theNode;

	/**
	 * @param template the jdfdoc to fill this into
	 * 
	 */
	public XJDFToJDFConverter(final JDFDoc template)
	{
		super(new BaseWalkerFactory());
		jdfDoc = template == null ? null : (JDFDoc) template.clone();
	}

	/**
	 * @param xjdf
	 * @return the converted jdf
	 */
	public JDFDoc convert(final KElement xjdf)
	{
		if (jdfDoc == null)
		{
			jdfDoc = new JDFDoc("JDF");
		}
		theNode = findNode(xjdf, true);
		if (theNode == null)
		{
			return null;
		}
		walkTree(xjdf, null);
		return jdfDoc;
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
	 * @return the node
	 */
	private JDFNode findNode(final KElement xjdf, final boolean create)
	{
		final JDFNode root = jdfDoc.getJDFRoot();
		JDFNode n = root.getJobPart(xjdf.getAttribute(AttributeName.JOBPARTID), null);
		if (n == null)
		{
			if (!root.hasAttribute(AttributeName.TYPE))
			{
				return root;
			}
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

			parent = parent.getParentNode_KElement();
			if (parent == null)
			{
				return bReturn;
			}

			final boolean bL1 = parent.getLocalName().endsWith("Set");
			bReturn = bL1 && toCheck.getLocalName().equals(parent.getAttribute("Name"));
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
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkXElement extends BaseWalker
	{

		/**
		 * @param factory
		 */
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
			cleanRefs(e);
			final KElement e2 = trackElem.copyElement(e, null);
			e2.removeChildren(null, null, null); // will be copied later
			return e2;
		}

		/**
		 * @param e
		 */
		protected void cleanRefs(final KElement e)
		{
			final JDFAttributeMap map = e.getAttributeMap();
			if (map != null)
			{
				final VString keys = map.getKeys();
				if (keys != null)
				{
					final int keySize = keys.size();
					for (int i = 0; i < keySize; i++)
					{
						final String val = keys.get(i);
						if (val.endsWith("Ref") && !val.equals("rRef"))
						{
							e.appendElement(val).setAttribute("rRef", map.get(val));
							e.removeAttribute(val);
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
			theNode.setType(EnumType.ProcessGroup);
			theNode.setAttributes(e);
			if (e.hasChildElement("ProductList", null))
			{
				final JDFNode n = theNode.addJDFNode(EnumType.ProcessGroup);
				theNode.setType(EnumType.Product);
				theNode = n;
			}
			return theNode;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return super.matches(toCheck) && "XJDF".equals(toCheck.getLocalName());
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
			EnumUsage inOut = EnumUsage.getEnum(e.getAttribute(AttributeName.USAGE));
			if (inOut == null) // heuristics for linking
			{
				final String localName = e.getLocalName();
				if (localName != null && (localName.startsWith("Parameter") || localName.startsWith("Intent")))
				{
					inOut = EnumUsage.Input;
				}
			}
			final JDFResource r = theNode.addResource(e.getAttribute("Name"), inOut);
			if (r == null)
			{
				return null;
			}
			if (inOut == null)
			{
				inOut = EnumResourceClass.Consumable.equals(r.getResourceClass()) ? EnumUsage.Input : EnumUsage.Output;
				theNode.linkResource(r, inOut, null);
			}
			final JDFResourceLink rl = theNode.getLink(r, inOut);
			r.setAttributes(e);
			r.removeAttribute(AttributeName.NAME);
			if (rl != null)
			{
				final String id = e.getAttribute(AttributeName.ID, null, null);
				if (id != null)
				{
					rl.setrRef(id);
				}
				r.removeAttribute(AttributeName.USAGE);
				rl.moveAttribute(AttributeName.PROCESSUSAGE, r);
				rl.moveAttribute(AttributeName.AMOUNT, r);
				rl.moveAttribute(AttributeName.ACTUALAMOUNT, r);
				rl.moveAttribute(AttributeName.MAXAMOUNT, r);
				rl.moveAttribute(AttributeName.MINAMOUNT, r);
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
			final boolean bL1 = parent != null && parent.getLocalName().equals("XJDF");
			return bL1 && super.matches(toCheck) && toCheck.getLocalName().endsWith("Set") && toCheck.hasAttribute(AttributeName.NAME);
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkResource extends WalkXElement
	{
		/**
		 * @param e
		 * @return thr created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			cleanRefs(e);
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
			final String name = toCheck.getLocalName();
			return "ProductList".equals(name);
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
			final JDFResource r = (JDFResource) trackElem;
			final JDFResource rPart = r.getCreatePartition(part.getPartMap(), part.guessPartIDKeys());
			final JDFResourceLink rll = theNode.getLink(r, null);
			rll.removeChildren(ElementName.PART, null, null);
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
			rPart.removeAttribute(AttributeName.STATUS);
			return rPart;

		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
	 */
	public class WalkXJDFResource extends WalkXElement
	{
		private JDFResourceLink rl = null;

		/**
		 * @param e
		 * @return the created resource
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			JDFResource p;
			final JDFPart part = (JDFPart) e.getElement(ElementName.PART);
			final JDFAmountPool ap = (JDFAmountPool) e.getElement(ElementName.AMOUNTPOOL);
			JDFAttributeMap partmap = null;
			if (part != null)
			{
				p = createPartition(e, trackElem, part);
				partmap = part.getPartMap();
			}
			else
			{
				p = (JDFResource) trackElem;
			}
			final JDFAttributeMap map = e.getAttributeMap();
			map.remove(AttributeName.ID);
			rl = theNode.getLink(p, null);
			if (rl != null)
			{
				moveToLink(partmap, map, AttributeName.AMOUNT);
				moveToLink(partmap, map, AttributeName.ACTUALAMOUNT);
				moveToLink(partmap, map, AttributeName.MAXAMOUNT);
				if (ap != null)
				{
					final JDFAmountPool rlAP = rl.getCreateAmountPool();
					rlAP.mergeElement(ap, false);
					ap.deleteNode();
				}

			}
			p.setAttributes(map);

			return p;
		}

		/**
		 * @param partmap
		 * @param map
		 * @param a
		 */
		private void moveToLink(final JDFAttributeMap partmap, final JDFAttributeMap map, final String a)
		{
			if (map.get(a) != null)
			{
				rl.setAmountPoolAttribute(a, map.get(a), null, partmap);
				map.remove(a);
			}
		}

		/**
		 * @param e
		 * @param trackElem
		 * @return
		 */
		protected JDFResource createPartition(final KElement e, final KElement trackElem, final JDFPart part)
		{
			final JDFResource r = (JDFResource) trackElem;
			final JDFResource rPart = r.getCreatePartition(part.getPartMap(), part.guessPartIDKeys());
			final JDFResourceLink rll = theNode.getLink(r, null);
			if (rll != null)
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
			final KElement k2 = super.walk(e, trackElem);
			final JDFResource r = (JDFResource) k2;
			final JDFResourceLink rl = theNode.getLink(r, null);
			r.renameElement(ElementName.COLORPOOL, null);
			rl.renameElement("ColorPoolLink", null);
			return k2;
		}
	}

}
