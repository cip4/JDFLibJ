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
package org.cip4.jdflib.extensions;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.enums.ValuedEnum;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoMarkObject.EnumAnchor;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumFrontCoatings;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNamedColor;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFElement.EnumOrientation;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.elementwalker.IWalker;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.devicecapability.JDFAbstractState;
import org.cip4.jdflib.resource.devicecapability.JDFAbstractState.EnumUserDisplay;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap.EnumAvailability;
import org.cip4.jdflib.resource.devicecapability.JDFEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFTerm;
import org.cip4.jdflib.resource.process.JDFColorPool;
import org.cip4.jdflib.resource.process.JDFSeparationSpec;
import org.cip4.jdflib.span.JDFSpanBase;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.EnumUtil;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * class to generate a jdf 2.0 schema from the jdf 1.x java library
 * 
 * concepts:
 * keep things - mainly data type declarations - local whenever possible
 * remove all deprecated elements
 * 
 * TODO devcaps
 * TODO add all generic elements where appropriate
 *  
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XJDFSchemaCreator extends BaseElementWalker
{
	/**
	 * if true, spans are made to a simple attribute rather than retained as span
	 */
	public boolean bSpanAsAttribute = true;
	protected final HashMap<String, ValuedEnum> enumMap;

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
			String localName = e.getLocalName();
			boolean b = ignoreNames.contains(localName);
			b = b || myNodes.contains(localName);
			b = b || localName.startsWith("IDP");

			b = b || (e2 instanceof JDFSeparationList);
			b = b || (e2 instanceof JDFSeparationSpec);
			b = b || (e2 instanceof JDFColorPool);
			b = b || (bSpanAsAttribute && (e2 instanceof JDFSpanBase));
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

		protected class VAttributeDescriptor extends Vector<AttributeDescriptor>
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * 
			 * @param s
			 */
			public void remove(String s)
			{
				AttributeDescriptor ad = get(s);
				if (ad != null)
				{
					remove(ad);
				}
			}

			/**
			 * 
			 * @param s
			 * @return
			 */
			public AttributeDescriptor get(String s)
			{
				for (int i = 0; i < size(); i++)
				{
					AttributeDescriptor ad = get(i);
					if (ad.equals(s))
					{
						return ad;
					}
				}
				return null;
			}
		}

		protected class AttributeDescriptor
		{
			/**
			 * @param name
			 * @param typ
			 * @param required
			 * @param valuedEnum
			 */
			public AttributeDescriptor(String name, EnumAttributeType typ, boolean required, ValuedEnum valuedEnum)
			{
				super();
				this.name = name;
				this.typ = typ;
				this.required = required;
				this.valuedEnum = valuedEnum;
			}

			/**
			 * @param attName
			 */
			public AttributeDescriptor(String attName)
			{
				this.name = attName;
				this.typ = null;
				this.required = false;
				this.valuedEnum = null;
			}

			String name;
			EnumAttributeType typ;
			boolean required;
			ValuedEnum valuedEnum;

			/**
			 * @return the name
			 */
			public String getName()
			{
				return name;
			}

			/**
			 * @param name the name to set
			 */
			public void setName(String name)
			{
				this.name = name;
			}

			/**
			 * @return the typ
			 */
			public EnumAttributeType getTyp()
			{
				return typ;
			}

			/**
			 * @param typ the typ to set
			 */
			public void setTyp(EnumAttributeType typ)
			{
				this.typ = typ;
			}

			/**
			 * @return the required
			 */
			public boolean isRequired()
			{
				return required;
			}

			/**
			 * @param required the required to set
			 */
			public void setRequired(boolean required)
			{
				this.required = required;
			}

			/**
			 * @return the valuedEnum
			 */
			public ValuedEnum getValuedEnum()
			{
				return valuedEnum;
			}

			/**
			 * @param valuedEnum the valuedEnum to set
			 */
			public void setValuedEnum(ValuedEnum valuedEnum)
			{
				this.valuedEnum = valuedEnum;
			}

			/**
			 * @see java.lang.Object#toString()
			 * @return
			*/
			@Override
			public String toString()
			{
				return "AttributeDescriptor: " + name + " " + typ + " " + required;
			}

			/**
			 * @see java.lang.Object#equals(java.lang.Object)
			 * @param obj
			 * @return
			*/
			@Override
			public boolean equals(Object obj)
			{
				return name.equals(obj);
			}

			/**
			 * @see java.lang.Object#hashCode()
			 * @return
			*/
			@Override
			public int hashCode()
			{
				return name.hashCode();
			}
		}

		protected KElement complexType;
		protected KElement complexElement;
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
			complexElement = null;
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
			baseAttribs.add(AttributeName.NAMEDFEATURES);
			baseAttribs.add(AttributeName.TEMPLATE);
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
			if (complexType == null)
				complexType = setComplexType(out, name);
			if (baseElms == null)
				createbaseElms();
			if (baseAttribs == null)
				createbaseAttribs();

			Vector<AttributeDescriptor> knownAtts = getKnownAtts();
			VString knownElms = getKnownElms();
			if (knownAtts.size() + knownElms.size() == 0)
			{
				complexType.deleteNode();
				//				System.out.println("deleting empty content: " + name);
				return null;
			}
			for (int i = 0; i < knownElms.size(); i++)
			{
				setXSElement(complexType, knownElms.get(i));
			}
			for (int i = 0; i < knownAtts.size(); i++)
			{
				AttributeDescriptor ad = knownAtts.get(i);
				setXSAttribute(ad);
			}

			return null;
		}

		/**
		 * @param ad
		 * @return 
		 */
		protected KElement setXSAttribute(AttributeDescriptor ad)
		{
			ValuedEnum valuedEnum = ad.getValuedEnum();
			String typ = getTypeName(ad.getTyp());
			String name = ad.getName();
			String enumName = getEnumName(valuedEnum);
			if (valuedEnum != null && enumMap.containsKey(enumName))
			{
				typ = enumName;
				valuedEnum = null;
			}
			return setXSAttribute(complexType, name, typ, ad.isRequired(), valuedEnum);
		}

		/**
		 * @return
		 */
		private VAttributeDescriptor getKnownAttsBase()
		{
			VAttributeDescriptor vDesc = new VAttributeDescriptor();
			if (!(e2 instanceof JDFElement))
				return vDesc;

			JDFElement je = (JDFElement) e2;
			AttributeInfo ai = je.getAttributeInfo();
			VString knownAtts = ai.knownAttribs();

			for (int i = knownAtts.size() - 1; i >= 0; i--)
			{
				String attName = knownAtts.get(i);

				if (baseAttribs.contains(attName) || ignoreNames.contains(attName))
				{
					continue;
				}
				else if (ai.getLastVersion(attName).getValue() <= EnumVersion.Version_1_4.getValue())
				{
					continue;
				}

				AttributeDescriptor desc = new AttributeDescriptor(getNewName(attName));
				ValuedEnum ve = ai.getAttributeEnum(attName);
				EnumAttributeType typ = ai.getAttributeType(attName);
				desc.setValuedEnum(ve);
				desc.setTyp(typ);
				vDesc.add(desc);
			}
			VString knownRefs = je.knownElements();
			for (int i = 0; i < knownRefs.size(); i++)
			{
				AttributeDescriptor desc = null;
				String elmName = getNewName(knownRefs.get(i));

				if (baseAttribs.contains(elmName) || ignoreNames.contains(elmName))
				{
					continue;
				}

				if (refsElms.contains(elmName))
				{
					desc = new AttributeDescriptor(elmName + "Refs");
					desc.setTyp(EnumAttributeType.IDREFS);
				}
				else if (isRefElem(elmName))
				{
					desc = new AttributeDescriptor(elmName + "Ref");
					desc.setTyp(EnumAttributeType.IDREF);
				}
				if (desc != null)
					vDesc.add(desc);

			}
			return vDesc;
		}

		/**
		 * @param elmName
		 * @return
		 */
		private boolean isRefElem(String elmName)
		{
			return refElms.contains(elmName) || elmName.endsWith("Params");
		}

		/**
		 * locally map attnames and elmnames
		 * @param string
		 * @return
		 */
		protected String getNewName(String string)
		{
			if (ElementName.COLORPOOL.equals(string))
				return "Color";
			return string;
		}

		/**
		 * @return
		 */
		private VString getKnownElmsBase()
		{
			VString ret = new VString();
			if (!(e2 instanceof JDFElement))
				return ret;
			JDFElement je = (JDFElement) e2;
			VString knownElms = je.knownElements();
			for (int i = knownElms.size() - 1; i >= 0; i--)
			{
				String elmName = knownElms.get(i);
				if (baseElms.contains(elmName) || isRefElem(elmName) || refsElms.contains(elmName))
				{
					continue;
				}
				else if (je.getLastVersion(elmName, true).getValue() < EnumVersion.Version_1_4.getValue())
				{
					continue;
				}
				else if (ignoreNames.contains(elmName))
				{
					continue;
				}
				ret.add(getNewName(elmName));
			}
			return ret;
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
			refElms.add(ElementName.LAYOUT);
			refElms.add(ElementName.COLOR);
			refElms.add(ElementName.COLORPOOL);
			refElms.add(ElementName.DEVICE);
			refElms.add(ElementName.EXTERNALIMPOSITIONTEMPLATE);

			refsElms = new HashSet<String>();
			refsElms.add(ElementName.SOURCERESOURCE);

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
			return myNodes == null || myNodes.contains(nodeName) && !ignoreNames.contains(nodeName);
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
			e2 = jdfRoot.getCreateElement(e.getLocalName());
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
			KElement att = root.getChildWithAttribute("xs:attribute", "name", null, attName, 0, true);
			if (att == null)
				att = root.appendElement("xs:attribute");
			att.setAttribute("use", required ? "required" : "optional");
			att.setAttribute("name", attName);
			String typName = typ.getName();
			att.setAttribute("type", typName);
		}

		/**
		 * 
		 * @param root
		 * @param attName
		 * @param typName 
		 * @param required
		 * @param ve a valued enum to generate values
		 * @return 
		 */
		protected KElement setXSAttribute(KElement root, String attName, String typName, boolean required, ValuedEnum ve)
		{
			KElement att = root.appendElement("xs:attribute");
			att.setAttribute("use", required ? "required" : "optional");
			att.setAttribute("name", attName);
			boolean bList = typName != null && (typName.equals(getTypeName(EnumAttributeType.NMTOKENS)) || typName.startsWith("enumerations"));
			if (typName == null || typName.startsWith("enumeration"))
			{
				if (typName == null)
					typName = "_" + attName;
				else
					typName += "_" + attName;
				if (enumMap.get(typName) == null)
				{
					if (typName.startsWith("enumerations"))
						typName = getTypeName(EnumAttributeType.NMTOKENS);
					else
						typName = getTypeName(EnumAttributeType.NMTOKEN);
				}
			}
			if (ve != null)
			{
				if (bList)
				{
					KElement list = appendSimpleTypeList(att, null, null);
					appendSimpleType(list, null, "xs:string", getEnumVector(ve));
				}
			}
			else
				att.setAttribute("type", typName);

			return att;
		}

		/**
		 * @param complexType 
		 * @param bID 
		 * 
		 */
		protected void setGeneric(KElement complexType, boolean bID)
		{
			setXSElement(complexType, ElementName.GENERALID);
			setXSElement(complexType, ElementName.COMMENT);
			if (bID)
				setXSAttribute(complexType, "ID", EnumAttributeType.ID, true);
			setXSAttribute(complexType, AttributeName.DESCRIPTIVENAME, EnumAttributeType.string, false);
		}

		/**
		 * @param out
		 * @param name
		 * @return 
		 */
		protected KElement setComplexType(final KElement out, String name)
		{
			String typeForName = getTypeForName(name);
			if (typeForName != null)
			{
				complexElement = out.appendElement("xs:element");
				complexElement.setAttribute("name", name);
			}
			else
				complexElement = null;
			boolean bLocal = name.equals(typeForName);
			KElement compType;
			if (bLocal)
			{
				compType = complexElement.appendElement("xs:complexType");
			}
			else
			{
				compType = out.appendElement("xs:complexType");
				if (typeForName != null)
				{
					compType.setAttribute("name", typeForName);
				}
				else
				{
					compType.setAttribute("name", name);
				}

			}
			KElement root = (complexElement != null) ? complexElement : compType;
			root.setXMLComment(" ** Complex type definition for " + name + " ** ");

			return compType;
		}

		/**
		 * 
		 * @param root
		 * @param elmName
		 */
		protected void setXSElement(KElement root, String elmName)
		{
			KElement seq = root.getElement("xs:sequence");
			if (seq == null)
			{
				KElement att = root.getElement("xs:attribute");
				seq = root.insertBefore("xs:sequence", att, null);
			}
			seq.setAttribute("minOccurs", "0");
			seq.setAttribute("maxOccurs", "unbounded");
			KElement choice = seq.getCreateElement("xs:choice");
			KElement elem = choice.appendElement("xs:element");
			elem.setAttribute("minOccurs", "0");
			boolean bRef = elmName.equals(getTypeForName(elmName));
			if (bRef)
				elem.setAttribute("ref", getTypeForName(elmName));
			else
			{
				elem.setAttribute("name", elmName);
				elem.setAttribute("type", getTypeForName(elmName));
			}
		}

		/**
		 * @param elmName
		 * @return
		 */
		protected String getTypeForName(String elmName)
		{
			if (ElementName.FOLDERSUPERSTRUCTUREWEBPATH.equals(elmName))
				return "ProductionSubPath";
			if (ElementName.POSTPRESSCOMPONENTPATH.equals(elmName))
				return "ProductionSubPath";
			if (ElementName.PRINTINGUNITWEBPATH.equals(elmName))
				return "ProductionSubPath";
			if (ElementName.EXTENDEDADDRESS.equals(elmName))
				return "TextElement";
			if (ElementName.ORGANIZATIONALUNIT.equals(elmName))
				return "TextElement";
			if (ElementName.PRODUCTIONSUBPATH.equals(elmName))
				return null;

			return elmName;
		}

		/**
		 * @param s 
		 * @return
		 */
		protected AttributeDescriptor getSpanAttDesc(String s)
		{
			KElement e = e2.appendElement(s);
			AttributeDescriptor desc = null;
			if (e instanceof JDFSpanBase && bSpanAsAttribute)
			{
				desc = new AttributeDescriptor(s);
				AttributeInfo ai = ((JDFElement) e).getAttributeInfo();
				desc.setTyp(ai.getAttributeType("Actual"));
				desc.setValuedEnum(ai.getAttributeEnum("Actual"));
			}
			else if (e instanceof JDFSeparationList)
			{
				desc = new AttributeDescriptor(s);
				desc.setTyp(EnumAttributeType.NMTOKENS);
			}
			else if (e instanceof JDFSeparationSpec)
			{
				desc = new AttributeDescriptor("SeparationNames");
				desc.setTyp(EnumAttributeType.NMTOKENS);
			}
			return desc;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#getKnownAtts()
		 * @return
		*/
		protected VAttributeDescriptor getKnownAtts()
		{
			VAttributeDescriptor va = getKnownAttsBase();
			Vector<String> vElem = getKnownElmsBase();
			for (int i = vElem.size() - 1; i >= 0; i--)
			{
				String s = vElem.get(i);
				AttributeDescriptor spanAttDesc = getSpanAttDesc(s);
				if (spanAttDesc != null)
				{
					va.add(spanAttDesc);
				}
			}
			return va;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#getKnownElms()
		 * @return
		*/
		protected VString getKnownElms()
		{
			VString vElem = getKnownElmsBase();
			for (int i = vElem.size() - 1; i >= 0; i--)
			{
				String s = vElem.get(i);
				if (getSpanAttDesc(s) != null)
				{
					vElem.remove(i);
				}
			}
			return vElem;
		}

		/**
		 * @param out
		 * @param name
		 */
		protected void createSubstitutionBase(final KElement out, String name)
		{
			KElement e = out.appendElement("xs:element");
			e.setAttribute("name", name + "Type");
			e.setAttribute("type", name + "Type");
			e.setAttribute("abstract", true, null);
			e.setXMLComment("** abstract type for substitution only ** ");
			e = out.appendElement("xs:complexType");
			e.setAttribute("name", name + "Type");
		}

		/**
		 * @param schemaRoot
		 * @param typeName
		 * @param baseType
		 * @return the created list
		 */
		protected KElement appendSimpleTypeList(KElement schemaRoot, String typeName, String baseType)
		{
			KElement typ = schemaRoot.appendElement("xs:simpleType");
			typ.setAttribute("name", typeName);
			KElement list = typ.appendElement("xs:list");
			list.setAttribute("itemType", baseType);
			return list;
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
			xjdf.setAttribute("targetNamespace", "http://www.CIP4.org/JDFSchema_1_1");
			xjdf.setAttribute("xmlns", "http://www.CIP4.org/JDFSchema_1_1");
			appendSimpleTypes(xjdf);
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

		/**
		 * @param schemaRoot
		 */
		protected void appendSimpleTypes(KElement schemaRoot)
		{
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.boolean_), "xs:boolean", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.string), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.shortString), getTypeName(EnumAttributeType.string), null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.NMTOKEN), "xs:NMTOKEN", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.NMTOKENS), "xs:NMTOKENS", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.ID), "xs:ID", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.IDREF), "xs:IDREF", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.IDREFS), "xs:IDREFS", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.JDFJMFVersion), "xs:string", new VString("2.0", null));
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.language), "xs:string", null);
			appendSimpleTypeList(schemaRoot, getTypeName(EnumAttributeType.languages), getTypeName(EnumAttributeType.language));
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.hexBinary), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.dateTime), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.DateTimeRangeList), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.PDFPath), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.duration), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.DurationRangeList), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.rectangle), "xs:double", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.RectangleRangeList), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.integer), "xs:integer", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.IntegerRangeList), "xs:integer", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.IntegerRange), "xs:integer", null);
			appendSimpleTypeList(schemaRoot, getTypeName(EnumAttributeType.IntegerList), getTypeName(EnumAttributeType.integer));
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.double_), "xs:double", null);
			appendSimpleTypeList(schemaRoot, getTypeName(EnumAttributeType.NumberList), getTypeName(EnumAttributeType.double_));
			appendSimpleTypeList(schemaRoot, getTypeName(EnumAttributeType.NumberRangeList), getTypeName(EnumAttributeType.double_));
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.shape), getTypeName(EnumAttributeType.NumberList), null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.ShapeRangeList), getTypeName(EnumAttributeType.NumberList), null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.URI), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.URL), getTypeName(EnumAttributeType.URI), null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.XPath), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.RegExp), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.XYPair), getTypeName(EnumAttributeType.NumberList), null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.XYPairRangeList), getTypeName(EnumAttributeType.NumberList), null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.XYRelation), "xs:NMTOKEN", new VString("gt ge ne eq lt le", null));
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.matrix), getTypeName(EnumAttributeType.NumberList), null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.Any), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.LabColor), getTypeName(EnumAttributeType.NumberList), null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.RGBColor), getTypeName(EnumAttributeType.NumberList), null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.CMYKColor), getTypeName(EnumAttributeType.NumberList), null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.TransferFunction), getTypeName(EnumAttributeType.NumberList), null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.NameRangeList), "xs:string", null);
			appendSimpleType(schemaRoot, getTypeName(EnumAttributeType.unbounded), "xs:string", null);
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkDeviceCap extends WalkElement
	{
		public WalkDeviceCap()
		{
			super();
			myNodes = new VString("DeviceCap", null).getSet();
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#createbaseAttribs()
		*/
		@Override
		protected void createbaseAttribs()
		{
			super.createbaseAttribs();
			baseAttribs.add("GenericAttributes");
			baseAttribs.add("ExecutionPolicy");
			baseAttribs.add("CombinedMethod");
			baseAttribs.add("Types");
			baseAttribs.add("TypeExpression");
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param in
		 * @param out
		 * @return
		*/
		@Override
		public KElement walk(KElement in, KElement out)
		{
			KElement walk = super.walk(in, out);
			setXSElement(complexType, "ElementState");
			createSubstitutionBase(out, "Term");

			return walk;
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkResourceElement extends WalkElement
	{
		EnumResourceClass c = null;

		public WalkResourceElement()
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
			KElement cc = complexElement.appendElement("xs:complexType").appendElement("xs:complexContent");
			if (EnumResourceClass.Parameter.equals(c))
			{
				complexElement.setAttribute("substitutionGroup", "ParameterType");
				complexType.setAttribute("base", "ParameterType");
			}
			else if (EnumResourceClass.Intent.equals(c))
			{
				complexElement.setAttribute("substitutionGroup", "IntentType");
				complexType.setAttribute("base", "IntentType");
			}
			else
			{
				complexElement.setAttribute("substitutionGroup", "ResourceType");
				complexType.setAttribute("base", "ResourceType");
			}
			KElement ext = cc.moveElement(complexType, null);
			ext.renameElement("xs:extension", null);
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
			boolean b = e2 instanceof JDFResource;
			if (b)
			{
				JDFResource resource = (JDFResource) e2;
				resource.init();
				c = resource.getResourceClass();
				if (c == null)
				{
					b = false;
					e2.deleteNode();
					e2 = null;
				}
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

			baseAttribs.add("QualityControlResult");
			baseAttribs.add("SourceResource");

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

			setXSElement(complexType, StringUtil.leftStr(setName, -3));
			setXSElement(complexType, "Dependent");
			setGeneric(complexType, true);
			setXSAttribute(complexType, "Name", EnumAttributeType.NMTOKEN, true);
			setXSAttribute(complexType, "Usage", null, false, EnumUsage.getEnum(0));
			setXSAttribute(complexType, "ProcessUsage", EnumAttributeType.NMTOKEN, false);
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
			setXSElement(complexType, XJDFConstants.ChildProduct);
			setXSElement(complexType, "Intent");
			setXSAttribute(complexType, "Amount", getTypeName(EnumAttributeType.integer), false, null);
			setXSAttribute(complexType, "ProductID", getTypeName(EnumAttributeType.shortString), false, null);
			return null;
		}
	}

	/**
	 * the class for XJDF Resource Parameter etc elements
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkResource extends WalkElement
	{

		public WalkResource()
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
			e2.deleteNode();
			String name = in.getLocalName();
			EnumResourceClass clazz = EnumResourceClass.getEnum(name);
			if (clazz == null)
				clazz = EnumResourceClass.Quantity;
			e2 = jdfRoot.addResource(name, clazz, null, null, null, null, null);
			complexType = setComplexType(out, name);
			//		setGeneric(complexType, true);
			// substitution group!
			super.walk(in, out);
			setXSElement(complexType, name + "Type");
			setXSElement(complexType, "Part");
			setGeneric(complexType, true);
			// now create abstract substitution group type
			createSubstitutionBase(out, name);

			return null;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#getKnownAtts()
		 * @return
		*/
		@Override
		protected VAttributeDescriptor getKnownAtts()
		{
			VAttributeDescriptor knownAtts = super.getKnownAtts();
			knownAtts.remove("Locked");
			knownAtts.remove("Class");
			knownAtts.remove("PartIDKeys");
			knownAtts.remove("PartUsage");
			knownAtts.remove("PipePartIDKeys");
			return knownAtts;
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
			JDFNodeInfo ni = (JDFNodeInfo) jdfRoot.addResource(ElementName.NODEINFO, null);
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
			complexType = setComplexType(out, XJDFHelper.XJDF);
			e2 = jdfRoot.appendElement("JDF");
			setGeneric(complexType, true);
			setXSElement(complexType, ProductHelper.PRODUCTLIST);
			setXSElement(complexType, SetHelper.RESOURCE_SET);
			setXSAttribute(complexType, AttributeName.TYPES, EnumAttributeType.NMTOKENS, true);
			setXSAttribute(complexType, AttributeName.COMMENTURL, EnumAttributeType.URL, false);
			super.walk(in, out);
			return null;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#getKnownAtts()
		 * @return
		*/
		@Override
		protected VAttributeDescriptor getKnownAtts()
		{
			VAttributeDescriptor knownAtts = super.getKnownAtts();
			AttributeDescriptor ad = knownAtts.get("Version");
			ad.setRequired(true);
			return knownAtts;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkEnumerationType extends WalkElement
	{
		public WalkEnumerationType()
		{
			super();
			myNodes = null;
		}

		/**
		 * @param in
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement in, final KElement out)
		{
			String setName = in.getLocalName();
			complexType = setSimpleType(out, setName);

			return null;
		}

		/**
		 * @param out
		 * @param name
		 * @return 
		 */
		protected KElement setSimpleType(final KElement out, String name)
		{
			ValuedEnum ve = enumMap.get(name);
			KElement typ = appendSimpleType(out, name, "xs:string", getEnumVector(ve));
			return typ;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#prepareWalk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e
		 * @param trackElem
		*/
		@Override
		public void prepareWalk(KElement e, KElement trackElem)
		{
			super.prepareWalk(e, trackElem);
			if (myNodes == null)
			{
				myNodes = new VString(ContainerUtil.getKeyVector(enumMap)).getSet();
			}
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkChildProduct extends WalkElement
	{
		public WalkChildProduct()
		{
			super();
			myNodes = new VString("ChildProduct", null).getSet();
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

			setXSAttribute(complexType, "ChildRef", getTypeName(EnumAttributeType.IDREF), true, null);
			setXSAttribute(complexType, "Amount", getTypeName(EnumAttributeType.integer), false, null);
			setXSAttribute(complexType, "ProductUsage", getTypeName(EnumAttributeType.NMTOKEN), false, null);

			return null;
		}
	}

	private static class XJDFSchemaCreatorWalkerFactory extends BaseWalkerFactory
	{
		/**
		 * 
		 * get the appropriate walker for a given element
		 * @see org.cip4.jdflib.elementwalker.IWalkerFactory#getWalker(org.cip4.jdflib .core.KElement)
		 */
		@Override
		public IWalker getWalker(final KElement toCheck)
		{
			for (BaseWalker w : vBaseWalker)
			{
				w.prepareWalk(toCheck, null);
				if (w.matches(toCheck))
				{
					return w;
				}
			}
			return null;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkTextElement extends WalkElement
	{
		public WalkTextElement()
		{
			super();
			myNodes = new VString("TextElement", null).getSet();
		}

		/**
		 * @param in
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement in, final KElement out)
		{
			String name = in.getLocalName();
			//			complexElement = out.appendElement("xs:element");
			//			complexElement.setAttribute("name", name);
			//			appendSimpleType(complexElement, null, getTypeName(EnumAttributeType.string), null);
			appendSimpleType(out, name, getTypeName(EnumAttributeType.string), null);

			return null;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkState extends WalkElement
	{
		public WalkState()
		{
			super();
			myNodes = null;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#createbaseAttribs()
		*/
		@Override
		protected void createbaseAttribs()
		{
			super.createbaseAttribs();
			baseAttribs.add("Name");
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#matches(org.cip4.jdflib.core.KElement)
		 * @param e
		 * @return
		*/
		@Override
		public boolean matches(KElement e)
		{
			String nodeName = e.getNodeName();
			if (e2 == null)
				e2 = jdfRoot.appendElement(nodeName);
			return e2 instanceof JDFAbstractState || "ElementState".equals(nodeName);
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#getKnownAtts()
		 * @return
		*/
		@Override
		protected VAttributeDescriptor getKnownAtts()
		{
			VAttributeDescriptor knownAtts = super.getKnownAtts();
			knownAtts.add(new AttributeDescriptor("XPath", EnumAttributeType.XPath, true, null));
			knownAtts.add(new AttributeDescriptor("XPathRoot", EnumAttributeType.XPath, false, null));
			knownAtts.add(new AttributeDescriptor(AttributeName.DESCRIPTIVENAME, EnumAttributeType.string, false, null));
			if (knownAtts.get(AttributeName.MINOCCURS) == null)
				knownAtts.add(new AttributeDescriptor(AttributeName.MINOCCURS, EnumAttributeType.integer, false, null));
			if (knownAtts.get(AttributeName.MAXOCCURS) == null)
				knownAtts.add(new AttributeDescriptor(AttributeName.MAXOCCURS, EnumAttributeType.integer, false, null));

			return knownAtts;
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkEvaluation extends WalkTerm
	{
		public WalkEvaluation()
		{
			super();
			myNodes = null;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#createbaseAttribs()
		*/
		@Override
		protected void createbaseAttribs()
		{
			super.createbaseAttribs();
			baseAttribs.add("rRef");
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#matches(org.cip4.jdflib.core.KElement)
		 * @param e
		 * @return
		*/
		@Override
		public boolean matches(KElement e)
		{
			String nodeName = e.getNodeName();
			if (e2 == null)
				e2 = jdfRoot.appendElement(nodeName);
			return e2 instanceof JDFEvaluation;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#getKnownAtts()
		 * @return
		*/
		@Override
		protected VAttributeDescriptor getKnownAtts()
		{
			VAttributeDescriptor knownAtts = super.getKnownAtts();
			knownAtts.add(new AttributeDescriptor("XPath", EnumAttributeType.XPath, true, null));
			knownAtts.add(new AttributeDescriptor("XPathRoot", EnumAttributeType.XPath, false, null));
			return knownAtts;
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkTerm extends WalkElement
	{
		public WalkTerm()
		{
			super();
			myNodes = null;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#matches(org.cip4.jdflib.core.KElement)
		 * @param e
		 * @return
		*/
		@Override
		public boolean matches(KElement e)
		{
			if (!super.matches(e))
				return false;
			String nodeName = e.getNodeName();
			if (e2 == null)
				e2 = jdfRoot.appendElement(nodeName);
			return e2 instanceof JDFTerm;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param in
		 * @param out
		 * @return
		*/
		@Override
		public KElement walk(KElement in, KElement out)
		{
			KElement e = super.walk(in, out);
			KElement cc = complexElement.appendElement("xs:complexType").appendElement("xs:complexContent");
			complexElement.setAttribute("substitutionGroup", "TermType");
			complexType.setAttribute("base", "TermType");
			KElement ext = cc.moveElement(complexType, null);
			ext.renameElement("xs:extension", null);
			return e;
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#getKnownElms()
		 * @return
		*/
		@Override
		protected VString getKnownElms()
		{
			VString v = super.getKnownElms();
			boolean bAdd = false;
			for (int i = v.size() - 1; i >= 0; i--)
			{
				KElement ee = e2.appendElement(v.get(i));
				if (ee instanceof JDFTerm)
				{
					v.remove(i);
					bAdd = true;
				}
			}
			if (bAdd)
				v.add("TermType");
			return v;
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkTest extends WalkTerm
	{
		public WalkTest()
		{
			super();
			myNodes = new VString("Test", null).getSet();
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param in
		 * @param out
		 * @return
		*/
		@Override
		public KElement walk(KElement in, KElement out)
		{
			KElement e = super.walk(in, out);
			if (complexType != null)
			{
				complexType.setAttribute("substitutionGroup", null); // undo last
			}
			return e;
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkJobPhase extends WalkElement
	{
		public WalkJobPhase()
		{
			super();
			myNodes = new VString("JobPhase", null).getSet();
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#getKnownElms()
		 * @return
		*/
		@Override
		protected VString getKnownElms()
		{
			VString knownElms = super.getKnownElms();
			knownElms.remove("JDF");
			return knownElms;
		}

	}

	/**
	 * the class for XJDF Resource Parameter etc elements
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkResourcePhysical extends WalkResource
	{

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkElement#getKnownAtts()
		 * @return
		*/
		@Override
		protected VAttributeDescriptor getKnownAtts()
		{
			VAttributeDescriptor knownAtts = super.getKnownAtts();
			knownAtts.remove("AmountProduced");
			knownAtts.remove("AmountRequired");
			knownAtts.remove("Amount");
			knownAtts.add(new AttributeDescriptor("AmountGood", EnumAttributeType.double_, false, null));
			knownAtts.add(new AttributeDescriptor("AmountWaste", EnumAttributeType.double_, false, null));
			return knownAtts;
		}

		public WalkResourcePhysical()
		{
			super();
			myNodes = new VString("Resource", null).getSet();
		}

	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkDependent extends WalkElement
	{
		public WalkDependent()
		{
			super();
			myNodes = new VString("Dependent", null).getSet();
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

			setXSAttribute(complexType, "JobID", getTypeName(EnumAttributeType.shortString), true, null);
			setXSAttribute(complexType, "JobPartID", getTypeName(EnumAttributeType.shortString), true, null);
			setXSAttribute(complexType, AttributeName.JMFURL, getTypeName(EnumAttributeType.URL), false, null);
			return null;
		}
	}

	/**
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkNiCi extends WalkResourceElement
	{
		public WalkNiCi()
		{
			super();
			myNodes = new VString("NodeInfo CustomerInfo Preview", null).getSet();
		}

		/**
		 * @param in
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement in, final KElement out)
		{
			c = EnumResourceClass.Parameter;
			return super.walk(in, out);
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDFSchemaCreator.WalkResourceElement#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return
		*/
		@Override
		public boolean matches(KElement toCheck)
		{
			String localName = toCheck.getLocalName();
			return (localName.equals(ElementName.NODEINFO)) || localName.equals(ElementName.CUSTOMERINFO) || localName.equals(ElementName.PREVIEW);
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
		super(new XJDFSchemaCreatorWalkerFactory());
		this.baseDir = baseDir;
		this.output = output;
		JDFDoc d1 = new JDFDoc("JDF");
		d1.setInitOnCreate(false);
		jdfRoot = d1.getJDFRoot();
		createIgnoreNames();
		enumMap = new HashMap<String, ValuedEnum>();
	}

	/**
	 * 
	 */
	void createIgnoreNames()
	{
		if (ignoreNames == null)
		{
			ignoreNames = new VString("Audit RefElement AttributeMap Element ResourceLink ResourceLinkPool ResourcePool AncestorPool Ancestor Spawned Merged" + " "
					+ "BusinessInfo Identical Doc DocumentBuilder Exception PartStatus PartAmount AmountPool PlaceHolder Node BindItem" + " "
					+ "DevCap DevCaps DevCapPool DisplayGroupPool FeaturePool CreateLink CreateResource NewComment UpdateJDFCmdParams MoveResource Sheet Surface", null).getSet();

			ignoreNames.add("TargetRoute");
			ignoreNames.add("Route");
			ignoreNames.add("IPPVersion");
			// TODO rethink preflight
			ignoreNames.add(ElementName.PRGROUP);
			ignoreNames.add(ElementName.PRGROUPOCCURRENCE);
			ignoreNames.add(ElementName.PROCCURRENCE);

			ignoreNames.add(ElementName.PRODUCTIONINTENT);

			ignoreNames.add(ElementName.ARTDELIVERYINTENT);

			ignoreNames.add(ElementName.DELIVERYINTENT);
			ignoreNames.add(ElementName.DROPINTENT);
			ignoreNames.add(ElementName.DROPITEMINTENT);
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
		schema.write2File(output, 2, false);
	}

	/**
	 * @return 
	 */
	private KElement createTree()
	{
		Vector<File> files = FileUtil.listFilesInTree(baseDir, "*.java");
		XMLDoc tree = new XMLDoc("classes", null);
		KElement treeRoot = tree.getRoot();
		addSimpleTypes(treeRoot);
		addNewTypes(treeRoot);
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
				treeRoot.getCreateElement(name);
			}
		}
		return treeRoot;
	}

	/**
	 * @param treeRoot
	 */
	private void addSimpleTypes(KElement treeRoot)
	{
		putPair(EnumAnchor.getEnum(0), treeRoot);
		putPair(EnumNamedColor.getEnum(0), treeRoot);
		putPair(EnumNodeStatus.getEnum(0), treeRoot);
		putPair(EnumResStatus.getEnum(0), treeRoot);
		putPair(EnumOrientation.getEnum(0), treeRoot);
		putPair(EnumWorkStyle.getEnum(0), treeRoot);
		putPair(EnumFrontCoatings.getEnum(0), treeRoot);
		//TODO combine stati
		putPair(EnumDeviceStatus.getEnum(0), treeRoot);
		putPair(EnumListType.getEnum(0), treeRoot);
		putPair(EnumAvailability.getEnum(0), treeRoot);
		putPair(EnumUserDisplay.getEnum(0), treeRoot);
	}

	/**
	 * @param treeRoot
	 */
	private void addNewTypes(KElement treeRoot)
	{
		treeRoot.getCreateElement("Resource");
		treeRoot.getCreateElement("ResourceSet");
		treeRoot.getCreateElement("Parameter");
		treeRoot.getCreateElement("ParameterSet");
		treeRoot.getCreateElement("Intent");
		//		treeRoot.getCreateElement("IntentSet");
		treeRoot.getCreateElement("ChildProduct");
		treeRoot.getCreateElement("Product");
		treeRoot.getCreateElement("ProductList");
		treeRoot.getCreateElement("TextElement");
		treeRoot.getCreateElement("ElementState");
		treeRoot.getCreateElement("Dependent");

	}

	/**
	 * @param en 
	 * @param treeRoot 
	 * 
	 */
	private void putPair(ValuedEnum en, KElement treeRoot)
	{
		String name = getEnumName(en);
		enumMap.put(name, en);
		treeRoot.appendElement(name);
	}

	/**
	 * @param valuedEnum
	 * @return
	 */
	protected String getEnumName(ValuedEnum valuedEnum)
	{
		if (valuedEnum == null)
			return null;
		String s = EnumUtil.getEnumName(valuedEnum).substring(4);
		if (AttributeName.SOURCEWORKSTYLE.equals(s))
			s = AttributeName.WORKSTYLE;
		if (AttributeName.BACKCOATINGS.equals(s) || AttributeName.FRONTCOATINGS.equals(s))
			s = "Coatings";
		return "Enum" + s;
	}

	/**
	 * @param typ 
	 * @return
	 */
	protected String getTypeName(EnumAttributeType typ)
	{
		return typ.getName();
	}

	/**
	 * @param baseElem
	 * @param typName
	 * @param baseType 
	 * @param v
	 * @return 
	 */
	protected KElement appendSimpleType(KElement baseElem, String typName, String baseType, VString v)
	{
		KElement typ = baseElem.appendElement("xs:simpleType");
		typ.setAttribute("name", typName);
		typ = typ.appendElement("xs:restriction");
		typ.setAttribute("base", baseType);
		if (v != null)
		{
			for (int i = 0; i < v.size(); i++)
			{
				KElement enu = typ.appendElement("xs:enumeration");
				enu.setAttribute("value", v.get(i));
			}
		}
		return typ;
	}

	/**
	 * @param ve
	 * @return
	 */
	protected VString getEnumVector(ValuedEnum ve)
	{
		Class<? extends ValuedEnum> class1 = ve.getClass();
		VString v = EnumUtil.getNamesVector(class1);
		String className = class1.getName();
		int pos = className.indexOf("$Enum");
		if (pos > 0)
			className = className.substring(pos + 5);
		if (AttributeName.STATUS.equals(className) || AttributeName.NODESTATUS.equals(className) || AttributeName.ENDSTATUS.equals(className))
		{
			v.remove(EnumNodeStatus.FailedTestRun.getName());
			v.remove(EnumNodeStatus.Ready.getName());
			v.remove(EnumNodeStatus.TestRunInProgress.getName());
			v.remove(EnumNodeStatus.Spawned.getName());
			v.remove(EnumNodeStatus.Part.getName());
			v.remove(EnumNodeStatus.Pool.getName());
		}
		else if (AttributeName.VERSION.equals(className))
		{
			return new VString("2.0", null);
		}

		return v;
	}

}
