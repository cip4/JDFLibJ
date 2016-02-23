/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2016 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.util.zip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.util.ByteArrayIOFileStream;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.ByteArrayIOStream.ByteArrayIOInputStream;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.SkipInputStream;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * class to read a zip file or stream
 * @author rainer prosi
 * @date Feb 1, 2012
 */
public class ZipReader
{
	final InputStream is;
	ByteArrayIOStream bios;
	ZipInputStream zis;
	final Log log;
	ZipEntry currentEntry;
	String rootEntry;
	int maxBuffer;

	public int getMaxBuffer()
	{
		return maxBuffer;
	}

	public void setMaxBuffer(int maxBuffer)
	{
		this.maxBuffer = maxBuffer;
	}

	/**
	 * Getter for rootEntry name attribute.
	 * @return the rootEntry name, null if not set 
	 */
	public String getRootEntry()
	{
		return rootEntry;
	}

	/**
	 * Setter for rootEntry attribute. use this to allow local searches relative to rootEntry
	 * @param rootEntry the rootEntry to set
	 */
	public void setRootEntry(String rootEntry)
	{
		this.rootEntry = StringUtil.getNonEmpty(rootEntry);
	}

	boolean caseSensitive;

	/**
	 * 
	 *return true if all checks are cese sensitive (the default)
	 * @return
	 */
	public boolean isCaseSensitive()
	{
		return caseSensitive;
	}

	/**
	 * 
	 * set the case sensitivity for matching strings and regexp
	 * @param caseSensitive
	 */
	public void setCaseSensitive(boolean caseSensitive)
	{
		this.caseSensitive = caseSensitive;
	}

	/**
	 * 
	 * @param inStream
	 * @param maxBuffer
	 */
	public ZipReader(InputStream inStream)
	{
		this(inStream, 10000000);
	}

	/**
	 * 
	 * @param inStream
	 * @param maxBuffer
	 */
	public ZipReader(InputStream inStream, int maxBuffer)
	{
		log = LogFactory.getLog(getClass());
		is = inStream;
		this.maxBuffer = maxBuffer;
		zis = new ZipInputStream(is);
		bios = null;
		caseSensitive = true;
	}

	/**
	 * 
	 * @param file the file to read
	 */
	public ZipReader(File file)
	{
		this(file, 10000000);
	}

	/**
	* 
	* @param file the file to read
	*/
	public ZipReader(File file, int maxBuffer)
	{
		log = LogFactory.getLog(getClass());
		this.maxBuffer = maxBuffer;
		zis = null;
		is = null;
		bios = new ByteArrayIOFileStream(file, maxBuffer, true);
		caseSensitive = true;
		//the local path is either really at null or in a directory root defined by filename (e.g. when using mac os compress of a directory)
		reset();
		if (file != null)
		{
			setRootEntry(UrlUtil.newExtension(file.getName(), null) + "/");
		}
	}

	/**
	 * 
	 * @param fileName
	 */
	public ZipReader(String fileName)
	{
		this(UrlUtil.urlToFile(fileName));
	}

	/**
	 * 
	 * get the vector of zip entries
	 * @return
	 */
	public Vector<ZipEntry> getEntries()
	{
		Vector<ZipEntry> vze = new Vector<ZipEntry>();
		ZipEntry ze = getNextEntry();
		while (ze != null)
		{
			vze.add(ze);
			ze = getNextEntry();
		}
		return vze;
	}

	/**
	 * get the next entry - this can be used in non-buffered mode
	 *returns null in case of snafu
	 * @return
	 */
	public ZipEntry getNextEntry()
	{
		try
		{
			ZipEntry nextEntry = zis.getNextEntry();
			currentEntry = nextEntry;
			return nextEntry;
		}
		catch (IOException e)
		{
			currentEntry = null;
			return null;
		}
	}

	/**
	 * unpack into a directory
	 *  
	 * @param dir the destination directory
	 * @return
	 */
	public int unPack(File dir)
	{
		if (dir == null)
			return 0;
		dir.mkdirs();
		if (!dir.isDirectory())
			return 0;
		int n = 0;
		ZipEntry ze = getNextEntry();
		while (ze != null)
		{
			boolean b = unPack(dir, ze);
			if (b)
				n++;
			ze = getNextEntry();
		}
		return n;
	}

	/**
	 * unpack an individual entry into a directory  
	 * @param dir
	 * @param ze
	 * @return
	 */
	public boolean unPack(File dir, ZipEntry ze)
	{
		if (dir == null)
			return false;
		if (currentEntry != ze)
		{
			log.warn("snafu with entries");
			return false;
		}
		String fileName = getEntryName(ze);
		File file = new File(fileName);
		File absoluteFile = FileUtil.getFileInDirectory(dir, file);
		try
		{
			if (ze.isDirectory())
			{
				absoluteFile.mkdirs();
			}
			else
			{
				File parent = absoluteFile.getParentFile();
				if (parent != null)
					parent.mkdirs();
				final OutputStream fos = new BufferedOutputStream(new FileOutputStream(absoluteFile));
				IOUtils.copy(zis, fos);
				fos.flush();
				fos.close();
			}
			zis.closeEntry();
		}
		catch (IOException e)
		{
			log.error("Snafu unpacking zip to: " + fileName, e);
		}
		return true;
	}

	/**
	 * retrieves the name and replaces any '\' with '/' and removes any spurious '.' or '..' in the path
	 * 
	 * @param ze
	 * @return
	 */
	public static String getEntryName(ZipEntry ze)
	{
		String fileName = ze.getName();
		fileName = StringUtil.replaceChar(fileName, '\\', "/", 0);
		return UrlUtil.cleanDots(fileName);
	}

	/**
	 * get a zip reader that searches itself for a valid zip header
	 * @param is
	 */
	public static ZipReader getZipReader(InputStream is)
	{
		Vector<ZipReader> v = getZipReaders(is, 1);
		return v != null && v.size() == 1 ? v.get(0) : null;
	}

	/**
	 * get a zip reader that searches itself for a valid zip header
	 * @param is
	 */
	public static Vector<ZipReader> getZipReaders(InputStream is, int max)
	{
		if (is == null)
			return null;
		ByteArrayIOInputStream bios = ByteArrayIOStream.getBufferedInputStream(is);
		SkipInputStream sis = new SkipInputStream("PK\03\04", bios, false);
		boolean nextAvailable = true;
		sis.mark(10000000);
		Vector<ZipReader> v = new Vector<ZipReader>();
		int n = 0;
		while (nextAvailable)
		{
			try
			{
				ZipReader newReader = new ZipReader(sis);
				newReader.buffer();
				ZipEntry nextEntry = newReader.getNextEntry();
				if (nextEntry != null && StringUtil.getNonEmpty(getEntryName(nextEntry)) != null)
				{
					v.add(newReader);
					if (++n == max)
					{
						break;
					}
					SkipInputStream sis2 = new SkipInputStream("PK\05\06", bios, false, 0);
					sis2.seek(sis.tell());
					boolean foundEnd = sis2.readToNextTag();
					if (foundEnd)
					{
						sis.seek(sis2.tell());
					}
					sis2.close();
					sis.mark(10000000);
					continue;
				}
			}
			catch (Exception x)
			{
				// next
			}
			sis.reset();
			nextAvailable = sis.readToNextTag();
			sis.mark(10000000);
		}
		return v.size() == 0 ? null : v;
	}

	/**
	 * get an entry by name - note that we need to buffer the entire file for this random access method
	 * 
	 * @param urlString the file path (case sensitive)
	 * @return
	 */
	public ZipEntry getEntry(String urlString)
	{
		buffer();
		if (urlString == null)
		{
			log.error("cannot retrieve null entry");
			return null;
		}
		urlString = UrlUtil.cleanDots(urlString);
		ZipEntry ze = getNextEntry();
		String urlUnEscaped = UrlUtil.unEscape(urlString);
		if (urlString.equals(urlUnEscaped))
			urlUnEscaped = null;

		while (ze != null)
		{
			String name = getEntryName(ze);
			boolean matches = caseSensitive ? urlString.equals(name) : urlString.equalsIgnoreCase(name);
			if (!matches && urlUnEscaped != null)
			{
				matches = caseSensitive ? urlUnEscaped.equals(name) : urlUnEscaped.equalsIgnoreCase(name);
			}
			if (!matches && rootEntry != null && name.startsWith(rootEntry))
			{
				name = StringUtil.rightStr(name, -rootEntry.length());
				if (name != null && name.length() > 0)
				{
					matches = caseSensitive ? urlString.equals(name) : urlString.equalsIgnoreCase(name);
					if (!matches && urlUnEscaped != null)
					{
						matches = caseSensitive ? urlUnEscaped.equals(name) : urlUnEscaped.equalsIgnoreCase(name);
					}
				}
			}
			if (!matches && !name.equals(StringUtil.token(name, -1, "/")))
			{
				name = StringUtil.token(name, -1, "/");
				if (name != null && name.length() > 0)
				{
					matches = caseSensitive ? urlString.equals(name) : urlString.equalsIgnoreCase(name);
					if (!matches && urlUnEscaped != null)
					{
						matches = caseSensitive ? urlUnEscaped.equals(name) : urlUnEscaped.equalsIgnoreCase(name);
					}
				}
			}

			if (matches)
				return ze;

			ze = getNextEntry();
		}
		return null;
	}

	/**
	 * get an entry by name - note that we need to buffer the entire file for this random access method
	 * 
	 * @param zeSet the zip entry to set
	 * @return
	 */
	public boolean setEntry(ZipEntry zeSet)
	{
		buffer();
		if (zeSet == null)
		{
			log.error("cannot set null entry");
			return false;
		}
		ZipEntry ze = getNextEntry();

		String setName = zeSet.getName();
		while (ze != null)
		{
			if (setName.equals(ze.getName()))
				return true;
			ze = getNextEntry();
		}
		return false;
	}

	/**
	 * get an entry by name - note that we need to buffer the entire file for this random access method
	 * 
	 * @param expr the regexp of the path (including directories) to match - simplified regexp is accepted
	 * @param iSkip how many to skip - default= 0
	 * @return
	 */
	public ZipEntry getMatchingEntry(String expr, int iSkip)
	{
		buffer();
		ZipEntry ze = getNextMatchingEntry(expr);
		int n = 0;

		while (ze != null)
		{
			if (n >= iSkip)
				return ze;
			else
				n++;
			ze = getNextMatchingEntry(expr);
		}
		return null;
	}

	/**
	 * get entries by name - note that we need to buffer the entire file for this random access method
	 * 
	 * @param expr the regexp of the path (including directories) to match - simplified regexp is accepted
	 *  
	 * @return
	 */
	public Vector<ZipEntry> getMatchingEntries(String expr, boolean sortName)
	{
		buffer();
		Vector<ZipEntry> vRet = new Vector<ZipEntry>();
		ZipEntry ze = getNextMatchingEntry(expr);

		while (ze != null)
		{
			vRet.add(ze);
			ze = getNextMatchingEntry(expr);
		}
		if (sortName)
		{
			Collections.sort(vRet, new NameComparator());
		}
		return vRet;
	}

	private class NameComparator implements Comparator<ZipEntry>
	{

		/**
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(ZipEntry o1, ZipEntry o2)
		{
			String s1 = getEntryName(o1);
			String s2 = getEntryName(o2);
			return ContainerUtil.compare(s1, s2);
		}

	}

	/**
	 * get the next entry by expression - note that we need to buffer the entire file for this random access method
	 * 
	 * @param expr the regexp to match - simplified regexp is accepted
	 * @return
	 */
	public ZipEntry getNextMatchingEntry(String expr)
	{
		ZipEntry ze = getNextEntry();
		String exprUnEscaped = UrlUtil.unEscape(expr);
		if (expr.equals(exprUnEscaped))
			exprUnEscaped = null;

		while (ze != null)
		{
			String name = getEntryName(ze);
			boolean matches = caseSensitive ? StringUtil.matchesSimple(name, expr) : StringUtil.matchesIgnoreCase(name, expr);
			if (!matches && exprUnEscaped != null)
			{
				matches = caseSensitive ? StringUtil.matchesSimple(name, exprUnEscaped) : StringUtil.matchesIgnoreCase(name, exprUnEscaped);
			}
			if (!matches && rootEntry != null && name.startsWith(rootEntry))
			{
				name = StringUtil.rightStr(name, -rootEntry.length());
				if (name != null && name.length() > 0)
				{
					matches = caseSensitive ? StringUtil.matchesSimple(name, expr) : StringUtil.matchesIgnoreCase(name, expr);
					if (!matches && exprUnEscaped != null)
					{
						matches = caseSensitive ? StringUtil.matchesSimple(name, exprUnEscaped) : StringUtil.matchesIgnoreCase(name, exprUnEscaped);
					}
				}
			}
			if (!matches && !name.equals(StringUtil.token(name, -1, "/")))
			{
				name = StringUtil.token(name, -1, "/");
				if (name != null && name.length() > 0)
				{
					matches = caseSensitive ? StringUtil.matchesSimple(name, expr) : StringUtil.matchesIgnoreCase(name, expr);
					if (!matches && exprUnEscaped != null)
					{
						matches = caseSensitive ? StringUtil.matchesSimple(name, exprUnEscaped) : StringUtil.matchesIgnoreCase(name, exprUnEscaped);
					}
				}
			}

			if (matches)
			{
				return ze;
			}

			ze = getNextEntry();
		}
		return null;
	}

	/**
	 * 
	 * get the stream to read this from; note that we must close manually
	 *
	 * @return
	 */
	public InputStream getInputStream()
	{
		return currentEntry == null ? null : zis;
	}

	/**
	 * 
	 * get the xmlDoc of the current entry - note not threadsafe!
	 *  
	 * @return
	 */
	public XMLDoc getXMLDoc()
	{
		if (currentEntry == null)
			return null;
		XMLDoc doc = XMLDoc.parseStream(zis);
		if (doc != null)
			doc.setZipReader(this);
		return doc;
	}

	/**
	 * 
	 * get the xmlDoc of the current entry - note not threadsafe!
	 *  
	 * @return
	 */
	public JDFDoc getJDFDoc()
	{
		if (currentEntry == null)
			return null;
		JDFDoc doc = JDFDoc.parseStream(zis);
		if (doc != null)
			doc.setZipReader(this);
		return doc;
	}

	/**
	 *  
	 */
	public void buffer()
	{
		if (bios == null)
		{
			bios = new ByteArrayIOFileStream(is, maxBuffer);
		}
		reset();
	}

	public void close()
	{
		if (bios != null)
			bios.close();
		if (zis != null)
		{
			try
			{
				zis.close();
			}
			catch (IOException e)
			{

			}
		}
	}

	/**
	 *  
	 */
	private void reset()
	{
		if (bios != null)
		{
			zis = new ZipInputStream(bios.getInputStream());
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ZipReader root=" + rootEntry;
	}

	/**
	 * 
	 * @param jarFile
	 * @return
	 */
	public static ZipReader getZipReader(File jarFile)
	{
		return getZipReader(FileUtil.getBufferedInputStream(jarFile));
	}

	/**
	 * 
	 * @throws Throwable
	 */
	@Override
	protected void finalize() throws Throwable
	{
		close();
		super.finalize();
	}
}
