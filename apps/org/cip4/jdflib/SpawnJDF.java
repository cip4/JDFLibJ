/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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
 }
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
//Titel:        JDF TestApplication
//Version:
//Copyright:    Copyright (c) 1999
//Autor:       Sabine Jonas, sjonas@topmail.de
//Firma:      BU/GH Wuppertal
//Beschreibung:  first Applications using the JDFLibrary
//package testApps;
package org.cip4.jdflib;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAncestorPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.MyArgs;
import org.cip4.jdflib.util.StringUtil;

/**
 * 
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class SpawnJDF
{
	private static boolean fileExists(final String name)
	{
		if (name == null)
		{
			return false;
		}
		boolean exists = false;

		final File file = new File(name);
		if (file.length() != 0.0)
		{
			exists = file.exists();
		}

		return exists;
	}

	/**
	 * 
	 * @param argv
	 */
	public static void main(final String argv[])
	{
		// -i bookintent.jdf -o spawned.jdf -p 4
		// -i bookintent.jdf -o spawned.jdf -p 0 -w r0007
		// -iic2.jdf -ospawnic.jdf -pp1 -wOutput
		final String prg = "SpawnJDF" + ":\t";
		System.out.print(prg + "START.");

		final MyArgs args = new MyArgs(argv, "adenvT", "iopkwt", null);
		System.out.println("MyArgs-args:" + args);

		final String usage = "SpawnJDF: JDF spawn processor;\n" + "-i input JDF;\n" + "-o output JDF;\n" + "-p jobpartId;\n" + "-T <Timing>;\n"
				+ "-k keys comma separated list of part keys\n" + "-w spawn global resources rw(default=ro)\n" + "-d delete node from original file" + "-n export as new jdf";

		final boolean doEscapes = args.boolParameter('e', false);
		final boolean bTime = args.boolParameter('T', false);
		System.out.println(prg + "e: doEscapes=" + doEscapes);

		final boolean useVDOMParser = args.boolParameter('v', false);
		System.out.println(prg + "v: useVDOMParser=" + useVDOMParser);

		final VString vRWResources = new VString(StringUtil.tokenize(args.parameter('w'), ",", false));
		System.out.println(prg + "w: resRW=" + args.parameter('w'));

		// -//HDM//-//Check command line and extract arguments.
		final String xmlFile = args.parameter('i');
		System.out.println(prg + "i: xmlFile=" + xmlFile);

		if (!fileExists(xmlFile))
		{
			System.err.println(args.usage(usage));
			System.exit(1);
		}

		final long lTime = System.currentTimeMillis();
		final String outFile = args.parameter('o');
		System.out.println(prg + "o: outFile=" + outFile);

		final String task = args.parameter('t');
		System.out.println(prg + "t: task=" + task);

		final boolean v = args.boolParameter('v', false);
		System.out.println(prg + "v: =" + v);

		final String strPartID = args.parameter('p');
		System.out.println(prg + "p: =" + strPartID);

		// start spawning
		// ==============
		final JDFParser p = new JDFParser();
		final JDFDoc docIn = p.parseFile(xmlFile);

		if (docIn == null)
		{
			System.err.println(args.usage(""));
			System.exit(1);
		}
		else
		{

			final JDFNode rootIn = (JDFNode) docIn.getRoot();
			// always assume jdf 1.3 or higher when spawning to jdf 2.0
			if (args.boolParameter('n', false))
			{
				rootIn.fixVersion(EnumVersion.Version_1_3);
			}

			JDFNode pCut;
			if (strPartID.equals(""))
			{
				pCut = rootIn;
			}
			else
			{
				pCut = rootIn.getJobPart(strPartID, "");
			}

			if (pCut == null)
			{
				System.err.println("No such JobPartID: " + strPartID);
				System.exit(1);
			}
			else
			{
				final VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();
				final JDFAttributeMap part1 = new JDFAttributeMap();
				final VString partKeys = StringUtil.tokenize(args.parameter('k'), ",", false);
				for (int iKey = 1; iKey < partKeys.size(); iKey += 2)
				{
					part1.put(partKeys.elementAt(iKey - 1), partKeys.elementAt(iKey));
				}

				if (part1.size() > 0)
					vSpawnParts.add(part1);
				final JDFSpawn spawn = new JDFSpawn(pCut);

				final JDFNode node = spawn.spawn(xmlFile, outFile, vRWResources, vSpawnParts, false, true, true, true);
				final KElement rootOut;

				if (args.boolParameter('n', false))
				{
					rootOut = makeNewJDF(node, rootIn);
				}
				else
				{
					rootOut = node;
				}

				// neu gespawntes File rausschreiben
				final XMLDoc docOut = rootOut.getOwnerDocument_KElement();
				docOut.write2File(outFile, 0, false);

				// verändertes Ausgangsfile rausschreiben
				final String strOutXMLFile = "_" + xmlFile;
				rootIn.eraseEmptyNodes(true);
				docIn.write2File(strOutXMLFile, 2, false);

				if (bTime)
				{
					System.out.println("Time (ms): " + (System.currentTimeMillis() - lTime));
				}
				System.out.print(prg + "ENDE.");
				System.exit(0);
			}
		}
	}

	/**
	 * @param node
	 * @param rootIn 
	 * @return
	 */
	private static KElement makeNewJDF(final JDFNode node, final JDFNode rootIn)
	{
		final JDFDoc newDoc = new JDFDoc("XJDF");
		final KElement newRoot = newDoc.getRoot();
		setRootAttributes(node, newRoot);
		setProduct(node, rootIn);
		setResources(newRoot, node, null);
		setElements(node, newRoot);
		return newRoot;
	}

	/**
	 * @param node
	 * @param newRoot
	 */
	private static void setElements(final JDFNode node, final KElement newRoot)
	{
		setAudits(newRoot, node);
		final VElement v = node.getChildElementVector(null, null, null, true, 0, false);
		for (int i = 0; i < v.size(); i++)
		{
			final KElement e = v.item(i);
			if (e instanceof JDFResourceLinkPool)
			{
				continue;
			}
			if (e instanceof JDFResourcePool)
			{
				continue;
			}
			if (e instanceof JDFAncestorPool)
			{
				continue;
			}
			if (e instanceof JDFAuditPool)
			{
				continue;
			}
			newRoot.copyElement(e, null);
		}
	}

	/**
	 * @param newRoot
	 * @param node
	 */
	private static void setAudits(final KElement newRoot, final JDFNode node)
	{
		final JDFAuditPool ap = node.getAuditPool();
		if (ap == null)
		{
			return;
		}
		final VElement audits = ap.getAudits(null, null, null);
		final KElement newPool = newRoot.appendElement("AuditPool");
		int n = 0;
		for (int i = 0; i < audits.size(); i++)
		{
			final JDFAudit audit = (JDFAudit) audits.elementAt(i);
			if (audit instanceof JDFSpawned)
			{
				continue;
			}
			if (audit instanceof JDFMerged)
			{
				continue;
			}
			newPool.copyElement(audit, null);
			n++;
		}
		if (n == 0)
		{
			newPool.deleteNode();
		}
	}

	/**
	 * @param node
	 * @param rootIn
	 * @return 
	 */
	private static String setProduct(final JDFNode node, final JDFNode rootIn)
	{
		if (rootIn == null)
		{
			return null;
		}
		if (!rootIn.getType().equals("Product"))
		{
			return null;
		}
		final KElement list = node.getCreateElement("ProductList");
		final KElement product = list.appendElement("Product");
		product.setAttributes(rootIn);
		setProductResources(product, rootIn);
		final VElement subProducts = rootIn.getvJDFNode("Product", null, true);
		for (int i = 0; i < subProducts.size(); i++)
		{
			final String childID = setProduct(node, (JDFNode) subProducts.elementAt(i));
			product.appendAttribute("Children", childID, null, " ", true);
		}
		return product.getAttribute("ID");
	}

	/**
	 * @param product
	 * @param rootIn
	 */
	private static void setProductResources(final KElement product, final JDFNode rootIn)
	{
		final VElement prodLinks = rootIn.getResourceLinks(null);
		final HashMap<String, String> componentMap = new HashMap<String, String>();
		for (int i = prodLinks.size() - 1; i >= 0; i--)
		{
			final JDFResourceLink rl = (JDFResourceLink) prodLinks.elementAt(i);
			final JDFResource linkRoot = rl.getLinkRoot();
			if (linkRoot instanceof JDFComponent)
			{
				prodLinks.remove(i);
				if (EnumUsage.Output.equals(rl.getUsage()))
				{
					linkRoot.setAttribute("tmp_id", linkRoot.getID());
					componentMap.put(linkRoot.getID(), rootIn.getID());
				}
			}
		}
		setResources(product, rootIn, prodLinks);
		final VElement vDropItems = product.getChildrenByTagName(ElementName.DROPITEMINTENT, null, null, false, true, 0);
		for (int i = 0; i < vDropItems.size(); i++)
		{
			final JDFDropItemIntent dropItemIntent = (JDFDropItemIntent) vDropItems.item(i);
			final JDFComponent c = dropItemIntent.getComponent();
			if (c != null)
			{
				final String id = componentMap.get(c.getAttribute("tmp_id", null, ""));
				if (id != null)
				{
					dropItemIntent.setAttribute("ProductRef", id);
					c.deleteNode();
				}
			}
		}
	}

	/**
	 * @param newRoot 
	 * @param rootIn 
	 * @param resLinks 
	 * 
	 */
	private static void setResources(final KElement newRoot, final JDFNode rootIn, final VElement resLinks)
	{
		final VElement vResLinks = resLinks == null ? rootIn.getResourceLinks(null) : resLinks;
		if (vResLinks == null)
		{
			return;
		}
		for (int i = 0; i < vResLinks.size(); i++)
		{
			final JDFResourceLink rl = (JDFResourceLink) vResLinks.elementAt(i);
			final JDFResource linkRoot = rl.getLinkRoot();
			setResource(newRoot, rl, linkRoot);
		}
		return;
	}

	/**
	 * @param newRoot
	 * @param rl
	 * @param linkRoot
	 */
	private static void setResource(final KElement newRoot, final JDFResourceLink rl, final JDFResource linkRoot)
	{
		final String className = getClassName(linkRoot);
		if (className == null)
		{
			return;
		}
		final KElement resourceSet = newRoot.appendElement(className + "Set");

		setLinkAttributes(resourceSet, rl, linkRoot);

		final VElement vRes = rl.getTargetVector(0);
		int dot = 0;
		final String resID = linkRoot.getID();
		for (int j = 0; j < vRes.size(); j++)
		{
			final JDFResource r = (JDFResource) vRes.elementAt(j);
			final VElement vLeaves = r.getLeaves(false);
			for (int k = 0; k < vLeaves.size(); k++)
			{
				final JDFResource leaf = (JDFResource) vLeaves.elementAt(k);
				// TODO this is just a quick hack - generating true id, idref
				// pairs would be better
				leaf.inlineRefElements(null, null, false);
				final KElement newLeaf = resourceSet.appendElement(className);
				setLeafAttributes(leaf, rl, newLeaf);
				newLeaf.setAttribute("ID", resID + "." + StringUtil.formatInteger(dot++));
			}
		}
	}

	/**
	 * @param leaf
	 * @param rl 
	 * @param newLeaf
	 */
	private static void setLeafAttributes(final JDFResource leaf, final JDFResourceLink rl, final KElement newLeaf)
	{
		final JDFAttributeMap partMap = leaf.getPartMap();
		// JDFAttributeMap attMap=leaf.getAttributeMap();
		// attMap.remove("ID");
		final JDFAmountPool ap = rl.getAmountPool();
		if (ap != null)
		{
			final VElement vPartAmounts = ap.getMatchingPartAmountVector(partMap);
			if (vPartAmounts != null)
			{
				final KElement amountPool = newLeaf.appendElement("AmountPool");
				for (int i = 0; i < vPartAmounts.size(); i++)
				{
					amountPool.copyElement(vPartAmounts.item(i), null);
				}
			}
		}
		if (partMap != null && partMap.size() > 0)
		{
			newLeaf.appendElement("Part").setAttributes(partMap);
			// attMap.removeKeys(partMap.keySet());
		}

		final KElement newResLeaf = newLeaf.copyElement(leaf, null);
		newResLeaf.removeAttribute(AttributeName.ID);
		newResLeaf.removeAttribute(AttributeName.SPAWNID);
		newResLeaf.removeAttribute(AttributeName.SPAWNIDS);
		newResLeaf.removeAttribute(AttributeName.SPAWNSTATUS);
		newResLeaf.removeAttribute(AttributeName.PARTUSAGE);

		newLeaf.moveAttribute("DescriptiveName", newResLeaf, null, null, null);
	}

	/**
	 * @param r
	 * @return 
	 */
	private static String getClassName(final JDFResource r)
	{
		if (r == null)
		{
			return null;
		}
		final EnumResourceClass resourceClass = r.getResourceClass();
		if (resourceClass == null)
		{
			return null;
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
	 * @param resourceSet 
	 * @param rl
	 * @param linkRoot 
	 */
	private static void setLinkAttributes(final KElement resourceSet, final KElement rl, final JDFResource linkRoot)
	{
		resourceSet.setAttribute("Name", linkRoot.getNodeName());
		resourceSet.copyAttribute("ID", linkRoot, null, null, null);
		resourceSet.setAttributes(rl);
		resourceSet.removeAttribute(AttributeName.RREF);
		resourceSet.removeAttribute(AttributeName.RSUBREF);
		if (rl instanceof JDFResourceLink)
		{
			final JDFResourceLink resLink = (JDFResourceLink) rl;
			final VElement vCreators = linkRoot.getCreator(EnumUsage.Input.equals(resLink.getUsage()));
			final Iterator<KElement> vCreatorsIterator = vCreators.iterator();
			while (vCreatorsIterator.hasNext())
			{
				final JDFNode depNode = (JDFNode) vCreatorsIterator.next();
				final KElement dependent = resourceSet.appendElement("Dependent");
				dependent.setAttribute(AttributeName.JOBID, depNode.getJobID(true));
				dependent.copyAttribute(AttributeName.JMFURL, depNode, null, null, null);
				dependent.copyAttribute(AttributeName.JOBPARTID, depNode, null, null, null);
			}
		}
	}

	/**
	 * @param node
	 * @param newRoot
	 */
	private static void setRootAttributes(final JDFNode node, final KElement newRoot)
	{
		newRoot.setAttributes(node);
	}

}
