/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFResourcePool.java
 *
 * Last changes
 *
 * 2001-07-30   Torsten Kaehlert - delete isNull() and throwNull() methods in parent class KNode
 *              TKAE20010730
 * 2002-07-02   JG - RP now inherits from JDFAutoResourcePool
 * 2002-07-02   JG - back to inheriting from JDFPool
 * 2002-07-02   JG - CopyResource: setting the SpawnStatus after copying the node
 * 2002-07-02   JG - CopyResource: don't add partitions in unpartitioned resources
 *                                 to set the spawn status
 * 2002-07-02   JG - CopyResource  fixed HRefs handling
 * 2002-07-02   JG - CopyResource added spawnID parameter
 * 2002-07-02   JG - CopyResource does not add new partitions any more
 * 2002-07-02   JG - removed AddDevice, AddOperator, AddScan and AddPDF
 * 2002-07-02   JG - added GetPoolChild, GetPoolChildren
 *
 */
package org.cip4.jdflib.pool;

import java.util.HashSet;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDocUserData;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource;
import org.w3c.dom.Attr;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 *         long before June 7, 2009
 */
public class JDFResourcePool extends JDFPool
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFResourcePool
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFResourcePool(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFResourcePool
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFResourcePool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFResourcePool
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFResourcePool(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	// **************************************** Methods
	// *********************************************
	/**
	 * this table contains only non standard versioned elements, i.e. first>=1.1 or deprecated
	 */
	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[91];
	static
	{
		int i = 0;
		elemInfoTable[i++] = new ElemInfoTable(ElementName.EMBOSSINGINTENT, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PUBLISHINGINTENT, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.SIZEINTENT, 0x44444443);

		elemInfoTable[i++] = new ElemInfoTable(ElementName.ADHESIVEBINDINGPARAMS, 0x44444443);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.ASSEMBLY, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.ASSETLISTCREATIONPARAMS, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.BARCODECOMPPARAMS, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.BARCODEREPROPARAMS, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.BENDINGPARAMS, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.BINDERYSIGNATURE, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.BLOCKPREPARATIONPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.BOXFOLDINGPARAMS, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.BOXPACKINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.BUFFERPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.BUNDLE, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.BUNDLINGPARAMS, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.CASEMAKINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.CASINGINPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.COLORMEASUREMENTCONDITIONS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.CONTACTCOPYPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.COVERAPPLICATIONPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.CREASINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.CUSTOMERINFO, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.CUTTINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.CYLINDERLAYOUT, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.CYLINDERLAYOUTPREPARATIONPARAMS, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.DEVELOPINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.DEVICEMARK, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.DIELAYOUT, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.DIGITALDELIVERYPARAMS, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.DIGITALMEDIA, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.DIVIDINGPARAMS, 0x44444443);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.ELEMENTCOLORPARAMS, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.EMBOSSINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.EXTERNALIMPOSITIONTEMPLATE, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.FEEDINGPARAMS, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.FITPOLICY, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.FOLD, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.FORMATCONVERSIONPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.GLUINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.HEADBANDAPPLICATIONPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.HOLELINE, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.HOLELIST, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.IDPRINTINGPARAMS, 0x44444443);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.JOBFIELD, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.LABELINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.LAYOUTPREPARATIONPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.LONGITUDINALRIBBONOPERATIONPARAMS, 0x44444443);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.MANUALLABORPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.MEDIASOURCE, 0x44444443);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.MISCCONSUMABLE, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.NODEINFO, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PACKINGPARAMS, 0x44444443);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PAGELIST, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PALLET, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PALLETIZINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PDLCREATIONPARAMS, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PERFORATINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PREFLIGHTPARAMS, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PREFLIGHTPROFILE, 0x44444433);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PREFLIGHTREPORT, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PREFLIGHTREPORTRULEPOOL, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PREVIEW, 0x33333333);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PRINTCONDITION, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PRINTROLLINGPARAMS, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PRODUCTIONPATH, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.PROOFINGPARAMS, 0x44444433);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.QUALITYCONTROLPARAMS, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.RASTERREADINGPARAMS, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.REGISTERRIBBON, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.ROLLSTAND, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.SADDLESTITCHINGPARAMS, 0x44444443);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.SCAVENGERAREA, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.SHAPECUTTINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.SHEET, 0x44444333);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.SHRINKINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.SIDESEWINGPARAMS, 0x44444443);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.SPINEPREPARATIONPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.SPINETAPINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.STACKINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.STRAP, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.STRAPPINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.STRIPBINDINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.STRIPPINGPARAMS, 0x33333311);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.SURFACE, 0x44444333);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.THREADSEALINGPARAMS, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.TOOL, 0x33333331);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.USAGECOUNTER, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.WEBINLINEFINISHINGPARAMS, 0x33333111);
		elemInfoTable[i++] = new ElemInfoTable(ElementName.WRAPPINGPARAMS, 0x33333331);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * gets the elemeinfotable for the resourcelinkpool based on this pool<br>
	 * Note that the cardinality is not updated and that the pool should be used for version checks only
	 * 
	 * @return ElemInfoTable[] the resourcelinkpools elemeninfotable
	 */
	public static ElemInfoTable[] getLinkInfoTable()
	{
		final ElemInfoTable[] elemInfoTableLink = new ElemInfoTable[elemInfoTable.length];
		for (int i = 0; i < elemInfoTableLink.length; i++)
		{
			elemInfoTableLink[i] = new ElemInfoTable(elemInfoTable[i].getElementName() + JDFConstants.LINK, elemInfoTable[i].getValidityStatus());
		}
		return elemInfoTableLink;
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFResourcePool[ --> " + super.toString() + " ]";
	}

	/**
	 * GetResIds - returns a set of all IDs of the resources that are in this pool
	 * 
	 * @return Vector - all IDs of the pool childs
	 */
	public VString getResIds()
	{
		final VString setID = new VString();

		final VElement nl = getPoolChildren(null, null, null);

		final int l = nl.size();
		for (int i = 0; i < l; i++)
		{
			final String s = ((JDFResource) nl.elementAt(i)).getAttribute(AttributeName.ID);
			if (s.equals(JDFConstants.EMPTYSTRING))
			{
				continue;
			}
			setID.add(s);
		}
		return setID;
	}

	/**
	 * Method GetResource.
	 * <p>
	 * default: GetResource(name, 0, JDFConstants.EMPTYSTRING)
	 * 
	 * @param strName name of the resource to look for
	 * @param i the index of the child, or -1 to create a new one
	 * @param nameSpaceURI the namespace to search in
	 * 
	 * @return JDFResource: the resource you were looking for, <code>null</code> if not found
	 */
	public JDFResource getResource(final String strName, final int i, final String nameSpaceURI)
	{
		final KElement poolChild = getPoolChild(i, strName, null, nameSpaceURI);

		if (poolChild != null)
		{
			return (JDFResource) poolChild;
		}

		return null;
	}

	/**
	 * append an existing resource by moving it here
	 * 
	 * @param res name of the resource to append
	 * 
	 * @return JDFResource: the appended resource
	 */
	public JDFResource appendResource(final JDFResource res)
	{
		return (JDFResource) moveElement(res, null);
	}

	/**
	 * append a resource
	 * <p>
	 * default: AppendResource(name, resClass, null)
	 * 
	 * @param strName name of the resource to append
	 * @param resClass class of the resource to append
	 * @param nameSpaceURI the namespace for the resource
	 * 
	 * @return JDFResource: the appended resource
	 */
	public JDFResource appendResource(final String strName, final JDFResource.EnumResourceClass resClass, final String nameSpaceURI)
	{
		final KElement appElement = appendElement(strName, nameSpaceURI);
		if (appElement instanceof JDFResource)
		{
			final JDFResource r = (JDFResource) appElement;
			if (!r.hasAttribute(AttributeName.CLASS) && resClass != null)
			{
				r.setResourceClass(resClass);
			}
			return r;
		}
		return null;
	}

	/**
	 * copies a resource recursively, optionally fixes status flags and locks in the source resource
	 * <p>
	 * default: copyResource(r, null, new VJDFAttributeMap(), null)
	 * 
	 * @param r the resource to copy into this
	 * @param copyStatus rw or ro
	 * @param vParts part map vector of the partitions to copy
	 * @param spawnID the spawnID of the spawning that initiated the copy
	 * @return VString: vector of resource names that have been copied
	 * @deprecated use JDFNode.copySpawnedResources
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public VString copyResource(final JDFResource r, final JDFResource.EnumSpawnStatus copyStatus, final VJDFAttributeMap vParts, final String spawnID)
	{
		final Vector ss = getResIds();
		final VString v = new VString();

		// r is not yet here copy r
		if (!ss.contains(r.getID()))
		{
			JDFResource rNew = null;
			// if spawning, fix stati and locks
			if (copyStatus == JDFResource.EnumSpawnStatus.SpawnedRO)
			{
				// copy the complete resource as RO - no need to reduce
				// partitions
				r.appendSpawnIDs(spawnID);
				rNew = (JDFResource) copyElement(r, null);
				rNew.setLocked(true);
				r.setSpawnStatus(copyStatus);

			}
			else if (copyStatus == JDFResource.EnumSpawnStatus.SpawnedRW)
			{
				if (vParts.size() == 0)
				{ // just copy the whole thing - no parts specified
					r.appendSpawnIDs(spawnID);
					rNew = (JDFResource) copyElement(r, null);
					r.setSpawnStatus(copyStatus);
				}
				else
				{
					rNew = (JDFResource) copyElement(r, null);
					rNew.reducePartitions(vParts);
					// reduce any unneeded leaves
					// loop over all part maps to get best matching resource
					for (int i = 0; i < vParts.size(); i++)
					{
						final JDFResource pLeaf = r.getPartition(vParts.elementAt(i), null);
						final JDFResource pNewLeaf = rNew.getPartition(vParts.elementAt(i), null);
						if (pLeaf != null)
						{
							pLeaf.setSpawnStatus(copyStatus);
							pLeaf.appendSpawnIDs(spawnID);
						}
						if (pNewLeaf != null)
						{
							pNewLeaf.appendSpawnIDs(spawnID);
						}
					}
				}
			}

			if (rNew != null)
			{
				v.addElement(rNew.getID());
			}
		}

		final VString vs = r.getHRefs(null, false, true);
		// add recursively copied resources
		for (int i = 0; i < vs.size(); i++)
		{
			final String id = vs.elementAt(i);
			// the referenced resource is in this pool - continue
			if (ss.contains(id))
			{
				continue;
			}

			// the referenced resource has already been merged in - continue
			if (v.contains(id))
			{
				continue;
			}

			JDFResource next = (JDFResource) getDocRoot().getTarget(id, AttributeName.ID);
			if (next == null)
			{
				// 071101 RP added r is by definition in the original document
				// which also contains the rrefs elements
				next = (JDFResource) r.getDocRoot().getTarget(id, AttributeName.ID);
				// and now all those interlinked resources
				final VString vv = copyResource(next, copyStatus, vParts, spawnID);
				v.addAll(vv);
			}
		}

		return v;
	}

	/**
	 * Method GetPoolChildren Gets all children with the attribute name,mAttrib, nameSpaceURI out of the pool
	 * 
	 * @param strName - name of the child
	 * @param mAttrib - a attribute to search for
	 * @param nameSpaceURI
	 * @return VElement - a vector with all elements in the pool matching the conditions
	 * 
	 *         default: GetPoolChildren(null, null, null)
	 */
	public VElement getPoolChildren(final String strName, final JDFAttributeMap mAttrib, final String nameSpaceURI)
	{
		return getPoolChildren_JDFResourcePool(strName, mAttrib, nameSpaceURI);
	}

	/**
	 * Method getPoolChildren_JDFResourcePool<br>
	 * Gets all children with the attributes <code>name, mAttrib, nameSpaceURI</code> from the pool
	 * 
	 * @param strName name of the child
	 * @param mAttrib attribute to search for
	 * @param nameSpaceURI namespace to search in
	 * @return VElement - a vector with all elements in the pool matching the conditions
	 */
	private VElement getPoolChildren_JDFResourcePool(final String strName, final JDFAttributeMap mAttrib, final String nameSpaceURI)
	{
		final VElement v = getPoolChildrenGeneric(strName, mAttrib, nameSpaceURI);

		for (int i = v.size() - 1; i >= 0; i--)
		{
			if (!(v.elementAt(i) instanceof JDFResource))
			{
				v.removeElementAt(i);
			}
		}
		return v;
	}

	/**
	 * Method getPoolChild<br>
	 * get a child resource from the pool matching the parameters
	 * <p>
	 * default: GetPoolChild(i, null, null, null)
	 * 
	 * @param i the index of the child or -1 to make a new one.
	 * @param strName the name of the element
	 * @param mAttrib the attribute of the element
	 * @param nameSpaceURI the namespace to search in
	 * @return JDFResource: the pool child matching the conditions above
	 */
	public JDFResource getPoolChild(final int i, final String strName, final JDFAttributeMap mAttrib, final String nameSpaceURI)
	{
		return getPoolChild_JDFResourcePool(i, strName, mAttrib, nameSpaceURI);
	}

	/**
	 * Method getPoolChild_JDFResourcePool<br>
	 * get a child resource from the pool matching the parameters
	 * 
	 * @param i the index of the child or -1 to make a new one.
	 * @param strName the name of the element
	 * @param mAttrib the attribute of the element
	 * @param nameSpaceURI the namespace to search in
	 * @return JDFResource: the pool child matching the above conditions
	 */
	private JDFResource getPoolChild_JDFResourcePool(final int i, final String strName, final JDFAttributeMap mAttrib, final String nameSpaceURI)
	{
		int iLocal = i;

		final VElement v = getPoolChildren(strName, mAttrib, nameSpaceURI);
		if (iLocal < 0)
		{
			iLocal = v.size() + iLocal;
			if (iLocal < 0)
			{
				return null;
			}
		}
		if (v.size() <= iLocal)
		{
			return null;
		}

		final JDFResource jdfResource = (JDFResource) v.elementAt(iLocal);
		return jdfResource;
	}

	/**
	 * return a vector of unknown element nodenames
	 * <p>
	 * default: GetUnknownElements(true, 99999999)
	 * 
	 * @param bIgnorePrivate ignores private elements. !Must be here to provide correct validation
	 * @param nMax - max entries in vector
	 * @return Vector of unknown element nodenames
	 */
	@Override
	public VString getUnknownElements(final boolean bIgnorePrivate, final int nMax)
	{
		return getUnknownPoolElements(JDFElement.EnumPoolType.ResourcePool, nMax);

	}

	/**
	 * returns a the resource with ID=<code>id</code>, if it is in this pool
	 * 
	 * @param id the ID of the requested resource
	 * @return JDFResource: the resource, empty element if it does not exist
	 */
	public JDFResource getResourceByID(final String id)
	{
		final XMLDocUserData userData = getXMLDocUserData();
		if (userData != null)
		{
			KElement kRet = userData.getTarget(id);
			if (kRet != null && kRet.getParentNode_KElement() != this)
			{
				kRet = null; // it is somewhere else, not a child of this!
			}
			if (kRet != null && (kRet instanceof JDFResource))
			{
				return (JDFResource) kRet;
			}
		}
		KElement e = getFirstChildElement();
		while (e != null)
		{
			final Attr attr = e.getAttributeNode(AttributeName.ID);
			if (attr != null)
			{
				if (userData != null)
				{
					userData.setTarget(e, attr.getValue());
				}
				if (id.equals(attr.getValue()))
				{
					break;
				}
			}
			e = e.getNextSiblingElement();
		}
		return (e instanceof JDFResource) ? (JDFResource) e : null;

	}

	/**
	 * get refElements
	 * 
	 * @param vDoneRefs used internally for recursion
	 * @param bRecurse if true, also return recursively linked IDs
	 * @return HashSet: the vector of referenced resource IDs
	 */
	@Override
	public HashSet<JDFElement> getAllRefs(HashSet<JDFElement> vDoneRefs, final boolean bRecurse)
	{

		final VElement vResources = getPoolChildren(null, null, null);
		final int size = vResources.size();
		for (int i = 0; i < size; i++)
		{
			final JDFResource r = (JDFResource) vResources.elementAt(i);
			vDoneRefs = r.getResourceRoot().getAllRefs(vDoneRefs, bRecurse);
		}

		return vDoneRefs;
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * get all completely unlinked resources
	 * 
	 * @return V the vector of unlinked resources.
	 */
	public VElement getUnlinkedResources()
	{
		final VElement v = getPoolChildren(null, null, null);
		if (v == null || v.size() == 0)
		{
			return null;
		}
		for (int i = v.size() - 1; i >= 0; i--)
		{
			final JDFResource r = (JDFResource) v.elementAt(i);
			final VElement v2 = r.getLinksAndRefs(true, true);
			if (v2 != null)
			{
				v.removeElementAt(i);
			}
		}
		return v.size() == 0 ? null : v;
	}

}