/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2020 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.XJDFZipWriter;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StringUtil;

/**
 * class that generates a set of multiple JDF elements from the leaf nodes of a JDF
 *
 * @author rainer prosi
 *
 */
class MultiJDFToXJDF
{

	/**
	 *
	 */
	private final JDFToXJDF jdfToXJDF;

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
				final boolean ok = file.delete();
				if (!ok)
				{
					LogFactory.getLog(getClass()).warn("Cannot delet output: " + fileName);
				}
			}
			else
			{
				throw new JDFException("output file exists: " + file.getPath());
			}
		}
		FileUtil.writeFile(getZipWriter(rootNode), new File(fileName));
	}

	/**
	 * @param jmf
	 * @param rootNode
	 * @param rootNode
	 * @param fos
	 */
	XJDFZipWriter getZipWriter(final JDFNode rootNode)
	{
		this.jdfToXJDF.setSingleNode(true);
		final Vector<XJDFHelper> vXJDFs = getXJDFs(rootNode, true);
		final XJDFZipWriter w = new XJDFZipWriter();
		for (final XJDFHelper h : vXJDFs)
		{
			w.addXJDF(h);
		}
		return w;
	}

	/**
	 * @param jdfToXJDF TODO
	 *
	 */
	MultiJDFToXJDF(final JDFToXJDF jdfToXJDF)
	{
		super();
		this.jdfToXJDF = jdfToXJDF;
	}

	/**
	 *
	 * @param root
	 * @return
	 */
	Vector<XJDFHelper> getXJDFs(final JDFNode root, final boolean ordered)
	{
		if (root == null)
			return null;
		jdfToXJDF.setSingleNode(true);
		final Vector<XJDFHelper> vRet = new Vector<>();
		final VElement v = getProcessNodes(root);
		final boolean keepProduct = this.jdfToXJDF.wantProduct;
		jdfToXJDF.wantProduct = true;
		if (JDFConstants.PRODUCT.equals(root.getType()))
		{
			final XJDFHelper xjdfHelper = convertSingle(root);
			vRet.add(xjdfHelper);
		}
		jdfToXJDF.wantProduct = false;
		if (ordered)
		{
			reorderNodes(v);
		}
		for (final KElement n : v)
		{
			final XJDFHelper xjdfHelper = convertSingle(n);
			vRet.add(xjdfHelper);
		}
		this.jdfToXJDF.wantProduct = keepProduct;
		return vRet;
	}

	/**
	 *
	 * @param v
	 */
	void reorderNodes(final VElement v)
	{
		final HashMap<JDFNode, VElement> mapPred = new HashMap<>();
		final int size = v.size();
		for (int i = 0; i < size; i++)
		{
			final JDFNode ni = (JDFNode) v.get(i);
			mapPred.put(ni, ni.getPredecessors(true, false));
		}
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < i; j++)
			{
				final JDFNode ni = (JDFNode) v.get(i);
				final JDFNode nj = (JDFNode) v.get(j);
				if (mapPred.get(nj).contains(ni))
				{
					v.remove(ni);
					v.insertElementAt(ni, j);
				}
			}
		}
	}

	/**
	 *
	 * @param n
	 * @return
	 */
	private XJDFHelper convertSingle(final KElement n)
	{
		final KElement xjdf = this.jdfToXJDF.makeNewJDF((JDFNode) n, null);
		final XJDFHelper xjdfHelper = new XJDFHelper(xjdf);
		if (jdfToXJDF.isCleanup())
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
				ensureJobPartID(i, n);
			}
		}
		return v;
	}

	/**
	 *
	 * @param i
	 * @param n
	 */
	private void ensureJobPartID(final int i, final JDFNode n)
	{
		String nam = n.getJobPartID(false);
		if (StringUtil.getNonEmpty(nam) == null)
		{
			nam = "Part_" + i;
			n.setJobPartID(nam);
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MultiJDFToXJDF [" + (jdfToXJDF != null ? "jdfToXJDF=" + jdfToXJDF : "") + "]";
	}
}