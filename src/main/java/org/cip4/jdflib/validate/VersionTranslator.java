/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.validate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.elementwalker.FixVersion;
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.extensions.xjdfwalker.XJDFToJDFConverter;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.JavaEnumUtil;
import org.cip4.jdflib.util.MyArgs;
import org.cip4.jdflib.util.UrlUtil;

/**
 * class to translate jdf to and from other jdf versions including but not limited to JDF 2.0 based on contents
 *
 * @author rainer prosi
 * @date Nov 16, 2010
 */
public class VersionTranslator
{

	private final EnumVersion version;

	public static int main(final String[] argv)
	{
		final MyArgs myArgs = new MyArgs(argv, "?", "ov", "v");
		if (myArgs.hasParameter('?'))
		{
			myArgs.usage("JDF / XJDF Version Converter");
			return 1;
		}
		else if (myArgs.missingArgs() != null)
		{
			myArgs.usage("JDF / XJDF Version Converter - missing required switches: " + myArgs.missingArgs());
			return 2;
		}
		final String version = myArgs.parameter('v');
		if (EnumVersion.getEnum(version) == null)
		{
			myArgs.usage("JDF / XJDF Version Converter - invalid versions: " + version);
			return 2;
		}
		if (myArgs.nargs() == 0)
		{
			myArgs.usage("JDF / XJDF Version Converter -missing input: ");
			return 2;
		}
		final String out = myArgs.parameter('o');
		final File outDir = UrlUtil.urlToFile(out);
		final VersionTranslator translator = new VersionTranslator(myArgs);
		for (int i = 0; i < myArgs.nargs(); i++)
		{
			translator.convertFile(UrlUtil.urlToFile(myArgs.argument(i)), outDir);
		}
		return 0;
	}

	void convertFile(final File urlToFile, File outDir)
	{
		final InputStream stream = FileUtil.getBufferedInputStream(urlToFile);
		if (stream == null)
		{
			return;
		}
		if (outDir == null)
		{
			outDir = urlToFile.getParentFile();
		}
		File outFile = FileUtil.getFileInDirectory(outDir, new File(urlToFile.getName()));
		outFile = FileUtil.newExtension(outFile, getExtension());
		final Vector<InputStream> converted = convertStream(stream);
		if (converted != null)
		{
			if (converted.size() == 1)
			{
				FileUtil.streamToFile(converted.get(0), outFile);
			}
			else
			{
				for (int i = 0; i < converted.size(); i++)
				{
					FileUtil.streamToFile(converted.get(0), FileUtil.newExtension(outFile, i + "." + getExtension()));
				}
			}
		}
	}

	private String getExtension()
	{
		return JavaEnumUtil.aLessThanB(version, EnumVersion.Version_2_0) ? "jdf" : "xjdf";
	}

	/**
	 * @param version the version to convert to
	 */
	public VersionTranslator(final EnumVersion version)
	{
		super();
		this.version = version;
	}

	VersionTranslator(final MyArgs myArgs)
	{
		super();
		final String version = myArgs.parameter('v');
		this.version = EnumVersion.getEnum(version);
	}

	/**
	 * convert a stream
	 *
	 * @param stream the input stream to convert
	 * @return the converted stream
	 */
	public Vector<InputStream> convertStream(final InputStream stream)
	{
		final JDFDoc d = JDFDoc.parseStream(stream);
		final KElement root = getRoot(d);
		if (root == null)
		{
			return null;
		}
		final KElement newRoot = convertElement(root);
		if (newRoot == null)
		{
			return null;
		}
		ByteArrayIOStream bios = new ByteArrayIOStream();
		try
		{
			newRoot.getOwnerDocument_KElement().write2Stream(bios, 2, true);
		}
		catch (final IOException e)
		{
			bios = null;
		}
		final Vector<InputStream> v;
		if (bios == null)
		{
			v = null;
		}
		else
		{
			v = new Vector<>();
			v.add(bios.getInputStream());
		}
		return v;
	}

	/**
	 * convert an element
	 *
	 * @param root the element to convert
	 * @return the converted element
	 */
	public KElement convertElement(final KElement root)
	{
		final EnumVersion inVersion = getVersion(root);
		KElement retElem;
		if (JavaEnumUtil.aLessThanB(inVersion, EnumVersion.Version_2_0))
		{
			retElem = convertFromJDF(root);
		}
		else
		{
			retElem = convertFromXJDF(root);
		}
		return retElem;
	}

	/**
	 * @param root
	 * @return
	 */
	KElement convertFromXJDF(final KElement root)
	{
		KElement retElem;
		if (JavaEnumUtil.aLessThanB(version, EnumVersion.Version_2_0))
		{
			final XJDFToJDFConverter xCon = new XJDFToJDFConverter(null);
			final JDFDoc dOut = xCon.convert(root);
			retElem = dOut == null ? null : dOut.getRoot();
		}
		else
		{
			// nop 2.0 -> 2.0 is null
			retElem = root;
		}
		return retElem;
	}

	/**
	 * @param root
	 * @return
	 */
	KElement convertFromJDF(final KElement root)
	{
		KElement retElem;
		if (JavaEnumUtil.aLessThanB(version, EnumVersion.Version_2_0))
		{
			final FixVersion fix = new FixVersion(version);
			fix.walkTree(root, null);
			retElem = root;
		}
		else
		{
			final XJDF20 xCon = getJDFToXJDFConverter();
			retElem = xCon.convert(root);
		}
		return retElem;
	}

	/**
	 * @return
	 */
	XJDF20 getJDFToXJDFConverter()
	{
		final XJDF20 xCon = new XJDF20();
		xCon.setSingleNode(false);
		return xCon;
	}

	/**
	 * get the version based on root
	 *
	 * @param root the element to check
	 * @return the version of the element
	 */
	private EnumVersion getVersion(final KElement root)
	{
		EnumVersion vers;
		if (root instanceof JDFNode)
		{
			vers = ((JDFNode) root).getVersion(true);
		}
		else if (root instanceof JDFJMF)
		{
			vers = ((JDFJMF) root).getVersion(true);
		}
		else
		{
			vers = EnumVersion.Version_2_0;
		}
		return vers;
	}

	/**
	 * get the jdf, jmf or xjdf root from a document
	 *
	 * @param d the document
	 * @return the root, null if no matching element exists
	 */
	private KElement getRoot(final JDFDoc d)
	{
		if (d == null)
		{
			return null;
		}
		KElement root = d.getJDFRoot();
		if (root == null)
		{
			root = d.getJMFRoot();
		}
		if (root == null)
		{
			final XJDFHelper helper = XJDFHelper.getHelper(d);
			root = helper == null ? null : helper.getRoot();
		}
		return root;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "VersionTranslator [version=" + version + "]";
	}
}
