/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen walker for the various resource sets
 */
public class WalkJDFElement extends WalkElement
{
	/**
	 * 
	 */
	public WalkJDFElement()
	{
		super();
	}

	/**
	 * @param jdf
	 * @param xjdf
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement jdf, final KElement xjdf)
	{
		final JDFElement je = (JDFElement) jdf;
		makeRefElements(je);
		return super.walk(jdf, xjdf);
	}

	/**
	 * make all inline resources to refelements
	 * @param je
	 */
	private void makeRefElements(final JDFElement je)
	{
		final VElement v = je.getChildElementVector_KElement(null, null, null, true, 0);
		for (int i = 0; i < v.size(); i++)
		{
			final KElement e = v.get(i);
			if (e instanceof JDFResource)
			{
				final JDFResource r = (JDFResource) e;
				if (!mustInline(r.getLocalName()))
				{
					cleanRefs(je, r);
				}
			}
		}
	}

	/**
	 * @param je
	 * @param r
	 */
	private void cleanRefs(final JDFElement je, JDFResource r)
	{
		final JDFNode parentJDF = je.getParentJDF();
		if (parentJDF != null)
		{
			r = r.makeRootResource(null, parentJDF, false);
			r.setResStatus(EnumResStatus.Available, true);
			final JDFResourcePool prevPool = parentJDF.getResourcePool();
			if (prevPool != null)
			{
				r = removeDuplicateRefs(r, prevPool);
			}
			je.refElement(r);
		}
		else if (je.getJMFRoot() != null)
		{
			final JDFResource resourceRoot = r.getResourceRoot();
			final JDFElement parent = (JDFElement) (resourceRoot == null ? null : resourceRoot.getParentNode_KElement());
			r = r.makeRootResource(null, parent, false);
			r.setResStatus(EnumResStatus.Available, true);
			je.refElement(r);
		}
	}

	/**
	 * @param r
	 * @param prevPool
	 * @return
	 */
	private JDFResource removeDuplicateRefs(JDFResource r, final JDFResourcePool prevPool)
	{
		final JDFAttributeMap m = r.getAttributeMap();
		m.remove("ID");
		final VElement prevs = prevPool.getChildrenByTagName(r.getNodeName(), null, m, true, true, 0);
		if (prevs != null)
		{
			for (int j = 0; j < prevs.size(); j++)
			{
				final JDFResource prev = (JDFResource) prevs.get(j);
				if (r == prev)
				{
					continue;
				}
				final String pid = prev.getID();
				final String rid = r.getID();
				prev.removeAttribute("ID"); // for comparing
				r.removeAttribute("ID");
				if (r.isEqual(prev)) // found duplicate - remove and ref the original
				{
					r.deleteNode();
					r = prev;
					prev.setID(pid); // better put it back...
					break;
				}
				else
				{
					r.setID(rid);
					prev.setID(pid); // better put it back...
				}
			}
		}
		return r;
	}

	/**
	 * get the name for the attribute to become a reference - may add a "Refs" rather than ref for 
	 * @param re the refelement to name
	 * @return the name
	 */
	protected String getRefName(final JDFRefElement re)
	{
		return re.getLocalName();
	}

	/**
	 * @param re
	 * @return true if must inline re
	 */
	protected boolean mustInline(final JDFRefElement re)
	{
		return mustInline(re.getRefLocalName());
	}

	/**
	 * @param refLocalName
	 * @return true if must inline refLocalName
	 */
	protected boolean mustInline(final String refLocalName)
	{
		return ElementName.OBJECTRESOLUTION.equals(refLocalName) || ElementName.BARCODECOMPPARAMS.equals(refLocalName) || ElementName.BARCODEREPROPARAMS.equals(refLocalName)
				|| ElementName.COMCHANNEL.equals(refLocalName) || ElementName.INTERPRETEDPDLDATA.equals(refLocalName) || ElementName.BYTEMAP.equals(refLocalName)
				|| ElementName.COMPANY.equals(refLocalName) || ElementName.COSTCENTER.equals(refLocalName) || ElementName.ADDRESS.equals(refLocalName)
				|| ElementName.PERSON.equals(refLocalName) || ElementName.DEVICE.equals(refLocalName) || ElementName.DEVICENSPACE.equals(refLocalName)
				|| ElementName.COLORANTALIAS.equals(refLocalName) || ElementName.GLUELINE.equals(refLocalName) || ElementName.GLUEAPPLICATION.equals(refLocalName)
				|| ElementName.CIELABMEASURINGFIELD.equals(refLocalName) || ElementName.REGISTERMARK.equals(refLocalName) || ElementName.FITPOLICY.equals(refLocalName)
				|| ElementName.CUTBLOCK.equals(refLocalName) || ElementName.EMPLOYEE.equals(refLocalName) || ElementName.ELEMENTCOLORPARAMS.equals(refLocalName)
				|| ElementName.CUT.equals(refLocalName) || ElementName.PDLRESOURCEALIAS.equals(refLocalName) || ElementName.HOLELIST.equals(refLocalName)
				|| ElementName.HOLE.equals(refLocalName) || ElementName.MISDETAILS.equals(refLocalName) || ElementName.HOLELINE.equals(refLocalName)
				|| ElementName.JOBFIELD.equals(refLocalName) || ElementName.OBJECTRESOLUTION.equals(refLocalName) || ElementName.AUTOMATEDOVERPRINTPARAMS.equals(refLocalName)
				|| ElementName.EXTERNALIMPOSITIONTEMPLATE.equals(refLocalName) || ElementName.PRODUCTIONPATH.equals(refLocalName) || ElementName.SHAPE.equals(refLocalName)
				|| ElementName.SCAVENGERAREA.equals(refLocalName) || ElementName.SCAVENGERAREA.equals(refLocalName) || ElementName.TRAPREGION.equals(refLocalName)
				|| ElementName.TRANSFERCURVE.equals(refLocalName) || ElementName.COLORCONTROLSTRIP.equals(refLocalName) || ElementName.LAYERLIST.equals(refLocalName)
				|| ElementName.PAGECONDITION.equals(refLocalName) || ElementName.CONTENTOBJECT.equals(refLocalName) || ElementName.MARKOBJECT.equals(refLocalName)
				|| ElementName.FILESPEC.equals(refLocalName) || ElementName.LAYERDETAILS.equals(refLocalName) || ElementName.BINDERYSIGNATURE.equals(refLocalName);
	}

	/**
	 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
	 * @param toCheck
	 * @return true if it matches
	 */
	@Override
	public boolean matches(final KElement toCheck)
	{
		return toCheck instanceof JDFElement;
	}

	/**
	 * @see org.cip4.jdflib.extensions.XJDF20.WalkElement#removeUnused(org.cip4.jdflib.core.KElement)
	 * @param newRootP
	*/
	@Override
	protected void removeUnused(KElement newRootP)
	{
		newRootP.removeAttribute(AttributeName.SPAWNID);
		super.removeUnused(newRootP);
	}
}