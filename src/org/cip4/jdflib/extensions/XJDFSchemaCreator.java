/**
 * 
 */
package org.cip4.jdflib.extensions;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StringUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XJDFSchemaCreator extends BaseElementWalker
{
	/**
	 * any matching class will be removed with extreme prejudice...
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkIgnore extends WalkElement
	{

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#matches(org.cip4.jdflib.core.KElement)
		 * @param e
		 * @return
		*/
		@Override
		public boolean matches(KElement e)
		{
			boolean b = super.matches(e);
			b = b && e.getLocalName().startsWith("IDP");
			return b;
		}

		public WalkIgnore()
		{
			super();
			createIgnoreNames();
			myNodes = ignoreNames;
		}

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			return null;
		}

	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkElement extends BaseWalker
	{
		protected KElement complexType;
		protected Set<String> baseAttribs;
		protected Set<String> baseElms;
		protected Set<String> refElms;
		protected Set<String> refsElms;
		protected Set<String> myNodes;
		protected KElement e2;

		@SuppressWarnings("synthetic-access")
		public WalkElement()
		{
			super(getFactory());
			complexType = null;
			baseAttribs = null;
			baseElms = null;
			refElms = null;
			refsElms = null;
			myNodes = null;
		}

		/**
		 * 
		 */
		protected void createbaseAttribs()
		{
			JDFElement foobar = (JDFElement) jdfRoot.getCreateElement("fooBar");
			baseAttribs = foobar.knownAttributes().getSet();
			baseAttribs.add("xmlns");
			baseAttribs.add("SettingsPolicy");
			baseAttribs.add("MustHonorExceptions");
			baseAttribs.add("BestEffortExceptions");
			baseAttribs.add("OperatorInterventionExceptions");
			baseAttribs.add("SpawnIDs");
			baseAttribs.add("SpawnID");
			baseAttribs.add("SpawnStatus");
			baseAttribs.add("Sorting");
			baseAttribs.add("SortAmount");
			baseAttribs.add(AttributeName.XSITYPE);
		}

		/**
		 * @param in
		 * @param out
		 * @return not null if must continue
		 */
		@Override
		public KElement walk(final KElement in, final KElement out)
		{
			// TODO data types enumerations
			String name = in.getLocalName();
			if (e2 == null)
				e2 = jdfRoot.appendElement(name);
			if (e2.getClass().equals(JDFElement.class))
			{
				return null;
			}
			if (complexType == null)
				complexType = setComplexType(out, name);

			AttributeInfo ai = e2.getAttributeInfo();
			VString knownAtts = getKnownAtts();
			VString knownElms = getKnownElms();
			if (knownAtts.size() + knownElms.size() == 0)
			{
				complexType.deleteNode();
				System.out.println("deleting empty content: " + name);
				return null;
			}
			for (int i = 0; i < knownAtts.size(); i++)
			{
				String attName = knownAtts.get(i);
				setXSAttribute(complexType, attName, ai.getAttributeType(attName), false);
			}
			VString knownRefs = e2.knownElements();
			for (int i = 0; i < knownRefs.size(); i++)
			{
				String elmName = knownRefs.get(i);
				if (refsElms.contains(elmName))
					setXSAttribute(complexType, elmName + "Refs", EnumAttributeType.IDREFS, false);
				else if (refElms.contains(elmName))
					setXSAttribute(complexType, elmName + "Ref", EnumAttributeType.IDREF, false);

			}

			for (int i = 0; i < knownElms.size(); i++)
			{
				setXSElement(complexType, knownElms.get(i));
			}
			return null;
		}

		/**
		 * @return
		 */
		protected VString getKnownAtts()
		{
			AttributeInfo ai = e2.getAttributeInfo();
			VString knownAtts = ai.knownAttribs();
			if (baseAttribs == null)
				createbaseAttribs();
			for (int i = knownAtts.size() - 1; i >= 0; i--)
			{
				String attName = knownAtts.get(i);
				if (baseAttribs.contains(attName))
				{
					knownAtts.remove(i);
				}
				else if (ai.getLastVersion(attName).getValue() < EnumVersion.Version_1_4.getValue())
				{
					knownAtts.remove(i);
				}
			}
			return knownAtts;
		}

		/**
		 * @return
		 */
		protected VString getKnownElms()
		{
			VString knownElms = e2.knownElements();
			if (baseElms == null)
				createbaseElms();
			for (int i = knownElms.size() - 1; i >= 0; i--)
			{
				String elmName = knownElms.get(i);
				if (baseElms.contains(elmName) || refElms.contains(elmName) || refsElms.contains(elmName))
				{
					knownElms.remove(i);
				}
				else if (e2.getLastVersion(elmName, true).getValue() < EnumVersion.Version_1_4.getValue())
				{
					knownElms.remove(i);
				}
				else if (ignoreNames.contains(elmName))
				{
					knownElms.remove(i);
				}
			}
			if (knownElms.contains("ColorPool"))
			{
				knownElms.remove("ColorPool");
				knownElms.add("Color");
			}
			return knownElms;
		}

		/**
		 * 
		 */
		protected void createbaseElms()
		{
			JDFElement foobar = (JDFElement) jdfRoot.getCreateElement("fooBar");
			baseElms = foobar.knownElements().getSet();
			baseElms.add("Identical");
			baseElms.add(ElementName.QUALITYCONTROLRESULT);
			baseElms.add(ElementName.JMF);

			refElms = new HashSet<String>();
			refElms.add(ElementName.EXPOSEDMEDIA);
			refElms.add(ElementName.RUNLIST);
			refElms.add(ElementName.MEDIA);
			refElms.add(ElementName.FILESPEC);
			refElms.add(ElementName.COMPONENT);
			refElms.add(ElementName.PAGELIST);
			refElms.add(ElementName.CONTENTLIST);
			refElms.add(ElementName.CONTENTDATA);
			refElms.add(ElementName.BINDERYSIGNATURE);
			refElms.add(ElementName.DIELAYOUT);
			refElms.add(ElementName.COLOR);
			refElms.add(ElementName.COLORMEASUREMENTCONDITIONS);
			refElms.add(ElementName.APPROVALPARAMS);
			refElms.add(ElementName.APPROVALPERSON);
			refElms.add(ElementName.IDENTIFICATIONFIELD);
			refElms.add(ElementName.QUALITYCONTROLRESULT);
			refElms.add(ElementName.TOOL);

			refsElms = new HashSet<String>();
			refsElms.add(ElementName.SOURCERESOURCE);
			refsElms.add(ElementName.COLOR);

		}

		/**
		 * 
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param e
		 * @return
		 */
		@Override
		public boolean matches(KElement e)
		{
			String nodeName = e.getLocalName();
			return myNodes == null || myNodes.contains(nodeName);
		}

		/**
		 * 
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#finalizeWalk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e
		 * @param trackElem
		 */
		@Override
		public void finalizeWalk(final KElement e, final KElement trackElem)
		{
			if (e2 != null)
			{
				e2.deleteNode();
			}
			e2 = null;
			complexType = null;
			super.finalizeWalk(e, trackElem);
		}

		/**
		 * 
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#prepareWalk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e
		 * @param trackElem
		 */
		@Override
		public void prepareWalk(final KElement e, final KElement trackElem)
		{
			super.prepareWalk(e, trackElem);
			complexType = null;
			e2 = null;
		}
	}

	/**
	 * class for the classes list root - does nothing special
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkRoot extends WalkElement
	{

		public WalkRoot()
		{
			super();
		}

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			return xjdf;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck.getLocalName().equals("classes");
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkResource extends WalkElement
	{
		EnumResourceClass c = null;

		public WalkResource()
		{
			super();
		}

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			KElement ret = super.walk(jdf, xjdf);
			if (EnumResourceClass.Parameter.equals(c))
				complexType.setAttribute("substitutionGroup", "ParameterType");
			else if (EnumResourceClass.Intent.equals(c))
				complexType.setAttribute("substitutionGroup", "IntentType");
			else
				complexType.setAttribute("substitutionGroup", "ResourceType");
			return ret;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			String nodeName = toCheck.getNodeName();
			if (e2 == null)
				e2 = jdfRoot.appendElement(nodeName);
			boolean b = e2 instanceof JDFResource;
			if (b)
			{
				JDFResource resource = (JDFResource) e2;
				resource.init();
				c = resource.getResourceClass();
			}
			return b;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#createbaseAttribs()
		*/
		@Override
		protected void createbaseAttribs()
		{
			super.createbaseAttribs();
			final JDFResourcePool dummyResPool = (JDFResourcePool) new JDFDoc("ResourcePool").getRoot();
			final JDFResource intRes = dummyResPool.appendResource("intent", EnumResourceClass.Intent, null);
			final JDFResource physRes = dummyResPool.appendResource("physical", EnumResourceClass.Consumable, null);
			final JDFResource paramRes = dummyResPool.appendResource("param", EnumResourceClass.Parameter, null);
			final JDFPart part = (JDFPart) dummyResPool.appendElement(ElementName.PART);
			baseAttribs.addAll(paramRes.knownAttributes());
			baseAttribs.addAll(physRes.knownAttributes());
			baseAttribs.addAll(intRes.knownAttributes());
			baseAttribs.addAll(part.knownAttributes());

		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkResSet extends WalkElement
	{

		public WalkResSet()
		{
			super();
			myNodes = new VString("ResourceSet ParameterSet", null).getSet();
		}

		/**
		 * @param in
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement in, final KElement out)
		{

			String setName = in.getLocalName();
			complexType = setComplexType(out, setName);

			setXSAttribute(complexType, "Name", EnumAttributeType.NMTOKEN, true);
			setGeneric(complexType, true);
			setXSElement(complexType, StringUtil.leftStr(setName, -3));
			return null;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkProductList extends WalkElement
	{
		public WalkProductList()
		{
			super();
			myNodes = new VString("ProductList", null).getSet();
		}

		/**
		 * @param in
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement in, final KElement out)
		{

			String setName = in.getLocalName();
			complexType = setComplexType(out, setName);

			setXSAttribute(complexType, "RootProducts", EnumAttributeType.IDREFS, true);
			setGeneric(complexType, false);
			setXSElement(complexType, "Product");
			return null;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkProduct extends WalkElement
	{
		public WalkProduct()
		{
			super();
			myNodes = new VString("Product", null).getSet();
		}

		/**
		 * @param in
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement in, final KElement out)
		{
			String setName = in.getLocalName();
			complexType = setComplexType(out, setName);

			setGeneric(complexType, true);
			setXSElement(complexType, "ChildProduct");
			setXSElement(complexType, "IntentType");
			return null;
		}
	}

	/**
	 * the base of all schema creation walkers
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkRes extends WalkElement
	{

		public WalkRes()
		{
			super();
			myNodes = new VString("Resource Parameter Intent", null).getSet();
		}

		/**
		 * @param in
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement in, final KElement out)
		{

			String name = in.getLocalName();
			complexType = setComplexType(out, name);
			setGeneric(complexType, true);
			setXSElement(complexType, name + "Type");
			setXSElement(complexType, "Part");
			return null;
		}
	}

	/**
	 * the base of all schema creation walkers
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkNode extends WalkElement
	{

		public WalkNode()
		{
			super();
			myNodes = new VString("XJDF", null).getSet();
		}

		/**
		 * 
		 */
		@Override
		protected void createbaseAttribs()
		{
			super.createbaseAttribs();
			KElement ni = jdfRoot.addResource(ElementName.NODEINFO, null);
			baseAttribs.addAll(ni.knownAttributes().getSet());
			baseAttribs.add(AttributeName.STATUSDETAILS);
			baseAttribs.add(AttributeName.TYPE);
		}

		/**
		 * @param in
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement in, final KElement out)
		{

			complexType = setComplexType(out, "XJDF");
			e2 = jdfRoot.appendElement("JDF");
			setGeneric(complexType, true);
			setXSElement(complexType, "ProductList");
			setXSElement(complexType, "ParameterSet");
			setXSElement(complexType, "ResourceSet");
			super.walk(in, out);
			return null;
		}
	}

	File baseDir;
	File output;
	JDFNode jdfRoot;
	Set<String> ignoreNames;

	/**
	 * @param baseDir
	 * @param output 
	 */
	public XJDFSchemaCreator(File baseDir, File output)
	{
		super(new BaseWalkerFactory());
		this.baseDir = baseDir;
		this.output = output;
		JDFDoc d1 = new JDFDoc("JDF");
		d1.setInitOnCreate(false);
		jdfRoot = d1.getJDFRoot();
		createIgnoreNames();
	}

	/**
	 * 
	 */
	void createIgnoreNames()
	{
		if (ignoreNames == null)
		{
			ignoreNames = new VString("RefElement AttributeMap Element ResourceLink ResourceLinkPool ResourcePool AncestorPool Ancestor Spawned Merged"
					+ "Identical Doc DocumentBuilder Exception PartStatus PartAmount AmountPool PlaceHolder Node", null).getSet();
		}
	}

	/**
	 * 
	 */
	public void create()
	{
		KElement treeRoot = createTree();
		XMLDoc schema = new XMLDoc("xs:schema", "http://www.w3.org/2001/XMLSchema");
		KElement schemaRoot = schema.getRoot();
		walkTree(treeRoot, schemaRoot);
		appendRootElement(schemaRoot, "XJDF");
		appendRootElement(schemaRoot, "JMF");
		schema.write2File(output, 2, false);
	}

	/**
	 * @param schemaRoot
	 * @param name
	 */
	private void appendRootElement(KElement schemaRoot, String name)
	{
		KElement e = schemaRoot.appendElement("xs:element");
		e.setAttribute("type", name);
		e.setAttribute("name", name);
	}

	/**
	 * @return 
	 */
	private KElement createTree()
	{
		Vector<File> files = FileUtil.listFilesInTree(baseDir, "*.java");
		XMLDoc tree = new XMLDoc("classes", null);
		KElement treeRoot = tree.getRoot();
		treeRoot.appendElement("XJDF");
		for (int i = 0; i < files.size(); i++)
		{
			File file = files.get(i);
			File parent = file.getParentFile();
			String parentName = parent.getName();
			if (parentName.equals("auto"))
				continue;
			if (parentName.equals("ifaces"))
				continue;
			if (parentName.equals("goldenticket"))
				continue;
			String name = file.getName();
			if (name.startsWith("JDF"))
			{
				name = StringUtil.leftStr(name, -5);
				name = StringUtil.rightStr(name, -3);
				treeRoot.appendElement(name);
			}
		}
		treeRoot.appendElement("Resource");
		treeRoot.appendElement("ResourceSet");
		treeRoot.appendElement("Parameter");
		treeRoot.appendElement("ParameterSet");
		treeRoot.appendElement("Intent");
		treeRoot.appendElement("IntentSet");
		treeRoot.appendElement("ChildProduct");
		treeRoot.appendElement("Product");
		treeRoot.appendElement("ProductList");
		return treeRoot;
	}

	/**
	 * 
	 * @param root
	 * @param attName
	 * @param typ
	 * @param required
	 */
	protected void setXSAttribute(KElement root, String attName, EnumAttributeType typ, boolean required)
	{
		KElement att = root.appendElement("xs:attribute");
		att.setAttribute("use", required ? "required" : "optional");
		att.setAttribute("name", attName);
		att.setAttribute("type", typ.getName());
	}

	/**
	 * 
	 * @param root
	 * @param elmName
	 */
	protected void setXSElement(KElement root, String elmName)
	{
		KElement seq = root.getCreateElement("xs:sequence");
		seq.setAttribute("minoccurs", "0");
		seq.setAttribute("maxoccurs", "unbounded");
		KElement choice = seq.getCreateElement("xs:choice");
		KElement elem = choice.appendElement("xs:element");
		elem.setAttribute("minoccurs", "0");
		elem.setAttribute("ref", elmName);
	}

	/**
	 * @param out
	 * @param name
	 * @return 
	 */
	protected KElement setComplexType(final KElement out, String name)
	{
		KElement complexType = out.appendElement("xs:complextype");
		complexType.setAttribute("name", name);
		complexType.setXMLComment(" ** Complex type definition for " + name + " ** ");
		return complexType;
	}

	/**
	 * @param complexType 
	 * @param bID 
	 * 
	 */
	protected void setGeneric(KElement complexType, boolean bID)
	{
		if (bID)
			setXSAttribute(complexType, "ID", EnumAttributeType.ID, true);
		setXSAttribute(complexType, AttributeName.DESCRIPTIVENAME, EnumAttributeType.string, false);
		setXSElement(complexType, ElementName.GENERALID);
	}

}
