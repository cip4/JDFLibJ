/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFLayoutPreparationParams.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoLayoutPreparationParams;
import org.cip4.jdflib.auto.JDFAutoAssembly.EnumOrder;
import org.cip4.jdflib.auto.JDFAutoStripMark.EnumMarkSide;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFAssembly;
import org.cip4.jdflib.resource.process.JDFBinderySignature;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.JDFPosition;
import org.cip4.jdflib.resource.process.JDFStripMark;
import org.w3c.dom.DOMException;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * way before June 4, 2009
 */
public class JDFLayoutPreparationParams extends JDFAutoLayoutPreparationParams
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFLayoutPreparationParams
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 * 
	 */
	public JDFLayoutPreparationParams(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFLayoutPreparationParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * 
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFLayoutPreparationParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFLayoutPreparationParams
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 * 
	 */
	public JDFLayoutPreparationParams(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFLayoutPreparationParams[  --> " + super.toString() + " ]";
	}

	/**
	 * convert this to stripping - also remove this and replace LayoutPrep in the node type list
	 * @param n the node to convert
	 * @return the converter which can be queried for the modified resources
	 */
	public StrippingConverter convertToStripping(JDFNode n)
	{
		final StrippingConverter c = new StrippingConverter(n);
		c.convert();
		return c;
	}

	/**
	 * sub-class that converts LayoutPreparationParams to the corresponding StrippingParams, Assembly and BinderySignature
	 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
	 * 
	 * June 4, 2009
	 */
	public class StrippingConverter
	{

		private JDFAssembly assembly = null;
		private JDFBinderySignature binderySignature = null;
		private JDFStrippingParams strippingParams = null;
		private final JDFNode parent;

		/**
		 * @param n
		 */
		public StrippingConverter(JDFNode n)
		{
			parent = n == null ? getParentJDF() : n;
		}

		/**
		 * @return the binderySignature
		 */
		public JDFBinderySignature getBinderySignature()
		{
			return binderySignature;
		}

		/**
		 * 
		 */
		public void convert()
		{
			convertParentNode();
			convertStrippingParams();
			convertBinderySignature();
			convertAssembly();

			removeObsolete();
		}

		/**
		 * remove this and any references to myself
		 */
		private void removeObsolete()
		{
			final VElement v = getLinksAndRefs(true, true);
			if (v != null)
			{
				for (int i = 0; i < v.size(); i++)
				{
					v.get(i).deleteNode();
				}
			}

			deleteNode(); // zapp myself in the end
		}

		/**
		 * convert all the attributes that go to strippingparams and related elements
		 */
		private void convertStrippingParams()
		{
			strippingParams = (JDFStrippingParams) parent.addResource(ElementName.STRIPPINGPARAMS, EnumUsage.Input);
			final JDFMedia media = getMedia();
			if (media != null)
			{
				media.makeRootResource(null, null, true);
				strippingParams.refElement(media);
			}
			setPosition();
			setStripMarks(getFrontMarkList(), EnumMarkSide.Front);
			setStripMarks(getBackMarkList(), EnumMarkSide.Back);
		}

		/**
		 * creates a position element for the binderysignature
		 */
		private void setPosition()
		{
			final JDFPosition position = strippingParams.appendPosition();
			position.setRelativeBox(new JDFRectangle(0, 0, 1, 1));
			position.copyAttribute(AttributeName.ORIENTATION, JDFLayoutPreparationParams.this, AttributeName.ROTATE, null, null);
		}

		/**
		 * @param marks
		 * @param side
		 */
		private void setStripMarks(final VString marks, final EnumMarkSide side)
		{
			for (String markName : marks)
			{
				final JDFStripMark sm = strippingParams.appendStripMark();
				sm.setMarkName(markName);
				sm.setMarkSide(side);
			}
		}

		/**
		 * convert all the attributes that go to binderysignature and related elements
		 */
		private void convertBinderySignature()
		{
			binderySignature = strippingParams.appendBinderySignature();
			binderySignature.makeRootResource(null, null, true);
			binderySignature.copyAttribute(AttributeName.BINDINGEDGE, JDFLayoutPreparationParams.this);
			binderySignature.copyAttribute(AttributeName.NUMBERUP, JDFLayoutPreparationParams.this);
			binderySignature.copyAttribute(AttributeName.FOLDCATALOG, JDFLayoutPreparationParams.this);

		}

		/**
		 * convert all the attributes that go to binderysignature and related elements
		 */
		private void convertAssembly()
		{
			assembly = (JDFAssembly) parent.addResource(ElementName.ASSEMBLY, EnumUsage.Input);
			assembly.copyAttribute(AttributeName.BINDINGSIDE, JDFLayoutPreparationParams.this, AttributeName.BINDINGEDGE, null, null);

			final EnumFinishingOrder fo = getFinishingOrder();
			if (EnumFinishingOrder.FoldCollect.equals(fo))
			{
				assembly.setOrder(EnumOrder.Collecting);
			}
			else if (EnumFinishingOrder.FoldGather.equals(fo))
			{
				assembly.setOrder(EnumOrder.Gathering);
			}
			else if (EnumFinishingOrder.Gather.equals(fo))
			{
				assembly.setOrder(EnumOrder.Gathering);
			}
			else if (EnumFinishingOrder.GatherFold.equals(fo))
			{
				assembly.setOrder(EnumOrder.Collecting);
			}
		}

		/**
		 * @return the parent node
		 */
		private JDFNode convertParentNode()
		{
			final VString types = parent.getTypes();
			if (types == null && EnumType.LayoutPreparation.equals(parent.getEnumType()))
			{
				parent.setType(EnumType.Stripping);
			}
			else if (types != null)
			{
				final int n = types.index(EnumType.LayoutPreparation.getName());
				if (n >= 0)
				{
					types.set(n, EnumType.Stripping.getName());
					parent.setTypes(types);
				}
			}
			final JDFModified mod = parent.getCreateAuditPool().addModified(null, null);
			mod.setDescriptiveName("Automatic LayoutPrep to Stripping Conversion");
			return parent;
		}

		/**
		 * @return the assembly
		 */
		public JDFAssembly getAssembly()
		{
			return assembly;
		}

		/**
		 * @return the strippingParams
		 */
		public JDFStrippingParams getStrippingParams()
		{
			return strippingParams;
		}

	}
}
