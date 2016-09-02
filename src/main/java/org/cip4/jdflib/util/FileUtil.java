/*
 *
 * The CIP4 Software License, Version 1.0
 *
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

package org.cip4.jdflib.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.ifaces.IStreamWriter;

/**
 * collection of helper routines to work with files
 * @author prosirai
 */
public class FileUtil
{

	/**
	 * list all files with a given extension (directories are skipped
	 * @param dir the directory to search
	 * @param extension comma separated list of extensions to check for (null = list all)
	 * @return Files[] the matching files, null if none are found
	 */
	public static File[] listFilesWithExtension(final File dir, final String extension)
	{
		if (dir == null)
		{
			return null;
		}
		final File[] files = dir.listFiles(new ExtensionFileFilter(extension));
		return (files == null || files.length == 0) ? null : files;
	}

	/**
	 * list all files matching given regexp
	 * @param dir the directory to search
	 * @param expression regular expression - uses the simplified syntax
	 * @return Files[] the matching files, null if none are found
	 */
	public static File[] listFilesWithExpression(final File dir, final String expression)
	{
		if (dir == null)
		{
			return null;
		}
		final File[] files = dir.listFiles(new ExpressionFileFilter(expression));
		return (files == null || files.length == 0) ? null : files;
	}

	/**
	 * list all files matching given regexp
	 * @param dir the directory to search
	 * @param filter the filter to apply to files 
	 * @return Vector<File>  the matching files, null if none are found
	 */
	public static Vector<File> listFilesInTree(final File dir, final FileFilter filter)
	{
		Vector<File> v = null;
		if (dir == null || filter == null)
		{
			return null;
		}
		final File[] f = dir.listFiles(filter);
		v = ContainerUtil.toVector(f);
		File[] dirs = listDirectories(dir);
		if (dirs != null)
		{
			for (File d : dirs)
			{
				Vector<File> v2 = listFilesInTree(d, filter);
				v = (Vector<File>) ContainerUtil.addAll(v, v2);
			}
		}
		return v;
	}

	/**
	 * list all files matching given regexp
	 * @param dir the directory to search
	 * @param expression comma separated list of regular expression of a tree with slashes separating directories
	 * @return Files[] the matching files, null if none are found
	 */
	public static Vector<File> listFilesInTree(final File dir, final String expression)
	{
		Vector<File> v = null;

		if (dir == null || expression == null)
		{
			return null;
		}

		final int posSlash = expression.indexOf('/');
		if (posSlash < 0)
		{
			final File[] f = listFilesWithExpression(dir, expression);
			v = ContainerUtil.toVector(f);
			File[] dirs = listDirectories(dir);
			if (dirs != null)
			{
				for (File d : dirs)
				{
					Vector<File> v2 = listFilesInTree(d, expression);
					v = (Vector<File>) ContainerUtil.addAll(v, v2);
				}
			}
		}
		else
		{
			final String nextDir = expression.substring(0, posSlash);
			final File[] files = listFilesWithExpression(dir, nextDir);
			if (files != null)
			{
				v = new Vector<File>();
				for (File file : files)
				{
					if (file.isDirectory())
					{
						v.add(file);
					}
				}

				if (v.size() == 0)
				{
					v = null;
				}

				if (posSlash + 1 != expression.length()) // last token ends with /
				{
					if (v != null)
					{
						final Vector<File> ret = new Vector<File>();
						final String next = expression.substring(posSlash + 1);
						for (int i = 0; i < v.size(); i++)
						{
							final Vector<File> v2 = listFilesInTree(v.get(i), next);
							if (v2 != null)
							{
								ret.addAll(v2);
							}
						}

						v = ret.size() == 0 ? null : ret;
					}
				}
			}
		}

		return v;
	}

	// //////////////////////////////////////////////////////////////////////////
	// //////
	/**
	 * list all direct child directories
	 * @param dir the directory to search
	 * @return Files[] the matching directories, null if none are found
	 */
	public static File[] listDirectories(final File dir)
	{
		if (dir == null)
		{
			return null;
		}
		final File[] files = dir.listFiles(new DirectoryFileFilter());
		return (files == null || files.length == 0) ? null : files;
	}

	// //////////////////////////////////////////////////////////////////////////

	/************************
	 * Inner class *********************** UtilFileFilter
	 ************************************************************/
	public static class ExtensionFileFilter implements FileFilter
	{
		private Set<String> m_extension;

		/**
		 * @param fileExtension comma separated list of valid regular expressions
		 */
		protected ExtensionFileFilter(final String fileExtension)
		{
			if (fileExtension != null)
			{
				final VString list = StringUtil.tokenize(fileExtension, ",", false);
				m_extension = new HashSet<String>();
				for (int i = 0; i < list.size(); i++)
				{
					String st = list.get(i);
					if (st.startsWith("."))
					{
						st = st.substring(1);
					}
					st = st.toLowerCase();
					m_extension.add(st);
				}
			}
		}

		/**
		 * @param fileExtensionVector Vector of valid regular expressions
		 */
		protected ExtensionFileFilter(final VString fileExtensionVector)
		{
			if (fileExtensionVector != null)
			{
				m_extension = new HashSet<String>();
				for (String st : fileExtensionVector)
				{
					if (st.startsWith("."))
					{
						st = st.substring(1);
					}
					st = st.toLowerCase();
					m_extension.add(st);
				}
			}
		}

		/**
		 * (non-Javadoc)
		 * @see java.io.FileFilter#accept(java.io.File)
		 */
		@Override
		public boolean accept(final File checkFile)
		{
			if ((checkFile == null) || !checkFile.isFile())
			{
				return false;
			}
			if (m_extension == null)
			{
				return true;
			}
			String xt = UrlUtil.extension(checkFile.getPath());
			if (xt == null)
			{
				xt = "";
			}
			else
			{
				xt = xt.toLowerCase();
			}

			return m_extension.contains(xt);
		}
	}

	/**
	 * simple file filter that lists all directories
	 * @author prosirai
	 */
	protected static class DirectoryFileFilter implements FileFilter
	{
		/**
		 *  
		 * 
		 * @see java.io.FileFilter#accept(java.io.File)
		 */
		@Override
		public boolean accept(final File checkFile)
		{
			return checkFile != null && checkFile.isDirectory();
		}
	}

	/**
	 * simple file filter that lists all files that match a regular expression
	 * @author Rainer Prosi
	 */
	protected static class ExpressionFileFilter implements FileFilter
	{
		private final String regExp;

		/**
		 * @param _regExp the simplified regular expression to match
		 */
		public ExpressionFileFilter(final String _regExp)
		{
			regExp = StringUtil.simpleRegExptoRegExp(_regExp);
		}

		/**
		 * true if a file matches a regular expression
		 * @see java.io.FileFilter#accept(java.io.File)
		 */
		@Override
		public boolean accept(final File checkFile)
		{
			return checkFile != null && StringUtil.matchesSimple(checkFile.getName(), regExp);
		}
	}

	/**
	 * very brutal tree zapper that will delete a file or directory tree recursively
	 * @param dirToZapp the file directory to utterly anihilate
	 * @return true if ciao
	 */
	public static boolean deleteAll(final File dirToZapp)
	{
		if (dirToZapp == null)
		{
			return false;
		}

		boolean b = true;
		if (dirToZapp.isDirectory())
		{
			final File[] ff = dirToZapp.listFiles();
			if (ff != null)
			{
				final int siz = ff.length;
				for (int i = 0; i < siz; i++)
				{
					b = deleteAll(ff[i]) && b;
				}
			}
		}

		return dirToZapp.delete() && b;
	}

	// //////////////////////////////////////////////////////////////////////////
	// //////
	/**
	 * dump a stream to a newly created file
	 * @param fis the inputstream to read
	 * @param fileName the file to stream to
	 * @return the file created by the stream, null if snafu
	 */
	public static File streamToFile(final InputStream fis, final String fileName)
	{
		if (fis == null)
		{
			return null;
		}
		final File tmp = UrlUtil.urlToFile(fileName);
		if (tmp == null)
		{
			return null;
		}
		return streamToFile(fis, tmp);
	}

	/**
	 * write to a file
	 * 
	 * @param file the file to write
	 * @param w the writer to write to
	 * 
	 * @return the file that was created, null if snafu
	 */
	public static File writeFile(IStreamWriter w, File file)
	{
		boolean b = FileUtil.createNewFile(file);
		if (!b)
			return null;

		try
		{
			OutputStream fos = FileUtil.getBufferedOutputStream(file);
			if (fos == null)
				return null;

			w.writeStream(fos);
			fos.flush();
			fos.close();
		}
		catch (FileNotFoundException e)
		{
			return null;
		}
		catch (IOException e)
		{
			return null;
		}
		return file;
	}

	/** 
	 * create a File object with a new extension
	 * @see UrlUtil#newExtension(String, String) for details of handling null etc.
	 * @param f the file, if null always returns null
	 * @param newExt the new extension
	 * @return the file with the new extension
	 */
	public static File newExtension(File f, String newExt)
	{
		if (f == null)
		{
			return null;
		}
		return new File(UrlUtil.newExtension(f.getPath(), newExt));
	}

	/**
	 * @param fis the InputStream to read - if null nothing happens
	 * @param fil the file to stream to
	 * @return the file created by the stream, null if snafu
	 */
	public static File streamToFile(final InputStream fis, final File fil)
	{
		if (fis == null)
			return null;
		createNewFile(fil);
		try
		{
			final OutputStream fos = getBufferedOutputStream(fil);
			if (fos == null)
				return null;
			IOUtils.copy(fis, fos);
			fos.flush();
			fos.close();
			fis.close();
		}
		catch (final IOException x)
		{
			return null;
		}
		return fil;
	}

	/**
	 * get an md5 from a file that reads at most 2*maxSize bytes of which maxSize are from the front and maxSize are from the back
	 * @param f
	 * @param maxSize if<=0 always use entir length
	 * @return
	 */
	public static byte[] getFastMD5(File f, int maxSize)
	{
		BufferedInputStream bufferedInputStream = getBufferedInputStream(f);
		if (bufferedInputStream == null || !f.canRead())
			return null;
		if (maxSize <= 0 || f.length() <= maxSize * 2)
		{
			return StreamUtil.getMD5(bufferedInputStream);
		}
		byte[] b = new byte[maxSize];
		MessageDigest md5;
		try
		{
			md5 = MessageDigest.getInstance(JDFConstants.MD5);
		}
		catch (NoSuchAlgorithmException e)
		{
			md5 = null;
		}
		try
		{
			synchronized (md5)
			{
				bufferedInputStream.read(b);
				md5.update(b);
				bufferedInputStream.skip(f.length() - 2l * maxSize);
				bufferedInputStream.read(b);
				md5.update(b);
				bufferedInputStream.close();
				return md5.digest();
			}
		}
		catch (IOException e)
		{
			return null;
		}
	}

	/**
	 * same as streanToFile but also calculates the md5 hash of the stream
	 * 
	 * @param fis the InputStream to read - if null nothing happens
	 * @param fil the file to stream to
	 * @return the file created by the stream, null if snafu
	 */
	public static MyPair<File, byte[]> streamToMD5File(final InputStream fis, final File fil)
	{
		if (fis == null)
			return null;

		MessageDigest md5;
		try
		{
			md5 = MessageDigest.getInstance(JDFConstants.MD5);
		}
		catch (NoSuchAlgorithmException e)
		{
			md5 = null;
		}
		synchronized (md5)
		{
			DigestInputStream dis = new DigestInputStream(fis, md5);
			File fRet = streamToFile(dis, fil);
			return fRet == null ? null : new MyPair<File, byte[]>(fRet, md5.digest());
		}
	}

	/**
	 * read a file into a byte array
	 * @param file the file to read into a byte array
	 * @return the correctly sized byte array, null if no bytes were read
	 */
	public static byte[] fileToByteArray(final File file)
	{
		if (file == null || !file.canRead())
		{
			return null;
		}
		try
		{
			final int len = (int) file.length();
			if (len <= 0)
			{
				return null;
			}
			InputStream fis = getBufferedInputStream(file);
			byte[] b = new byte[len];
			final int l = fis.read(b);
			fis.close();
			fis = null;
			if (l != len)
			{
				if (l == 0)
				{
					return null;
				}
				final byte[] b2 = new byte[l];
				for (int i = 0; i < l; i++)
				{
					b2[i] = b[i];
				}
				b = b2;
			}
			return b;

		}
		catch (final Throwable e)
		{
			return null;
		}
	}

	/**
	 * moves a File to directory by trying to rename, if this fails, a copy with subsequent delete is performed. if toFile exists, it is brutally overwritten
	 * @param fromFile the File to move
	 * @param toDir the Directory to move to
	 * @return File the moved File if success, else null, i.e. toFile exists with the contents of fromFile
	 */
	public static File moveFileToDir(final File fromFile, final File toDir)
	{
		if (fromFile == null || toDir == null)
		{
			return null;
		}
		if (!toDir.isDirectory())
		{
			toDir.mkdirs();
		}
		final File newFile = getFileInDirectory(toDir, new File(fromFile.getName()));
		final boolean b = moveFile(fromFile, newFile);
		return b ? newFile : null;
	}

	/**
	 * moves a File by trying to rename, if this fails, a copy with subsequent delete is performed. if toFile exists, it is brutally overwritten
	 * @param fromFile the File to move
	 * @param toFile the File to create
	 * @return boolean true if success, i.e. toFile exists with the contents of fromFile
	 */
	public static boolean moveFile(final File fromFile, final File toFile)
	{
		if (fromFile == null || toFile == null)
		{
			return false;
		}
		if (fromFile.equals(toFile))
		{
			return true;
		}
		if (fromFile.renameTo(toFile))
		{
			return true;
		}
		if (!copyFile(fromFile, toFile))
		{
			return false;
		}
		return fromFile.delete();

	}

	/**
	 * copy a buffer to the end of a file, creating it if necessary
	 * 
	 * @param buf the source buffer
	 * @param toFile the destination File
	 * @return true if success
	 */
	public static boolean copyBytes(final byte[] buf, final File toFile)
	{
		if (buf == null || toFile == null)
		{
			return false;
		}
		if (!toFile.canWrite())
		{
			createNewFile(toFile);
		}
		try
		{
			final FileOutputStream fileOutputStream = new FileOutputStream(toFile, true);
			fileOutputStream.write(buf);
			fileOutputStream.flush();
			fileOutputStream.close();
			return true;
		}
		catch (final FileNotFoundException e)
		{
			//
		}
		catch (final IOException e)
		{
			//
		}
		return false;
	}

	/**
	 * copy a file, unless fromFile and toFile are equal
	 * @param fromFile the source File
	 * @param toFile the destination File
	 * @return true if success
	 */
	public static boolean copyFile(final File fromFile, final File toFile)
	{
		if (fromFile == null || toFile == null || fromFile.equals(toFile))
		{
			return false;
		}
		try
		{
			return streamToFile(new FileInputStream(fromFile), toFile) != null;
		}
		catch (final FileNotFoundException e)
		{
			return false;
		}
	}

	/**
	 * copies a File to directory if and only if toFile does not exist
	 *
	 * @param fromFile the File to move
	 * @param toDir the Directory to move to
	 * @return File the destination File if success, 
	 */
	public static File ensureFileInDir(final File fromFile, final File toDir)
	{
		if (fromFile == null || toDir == null)
		{
			return null;
		}
		if (!toDir.isDirectory())
		{
			toDir.mkdirs();
		}
		final File newFile = getFileInDirectory(toDir, new File(fromFile.getName()));
		boolean b = newFile.canRead();
		if (!b)
			b = copyFile(fromFile, newFile);
		return b ? newFile : null;
	}

	/**
	 * remove any internal "../" "./" and "//" from a url
	 * 
	 * @param file the file to clean
	 * @return File - the clean file
	 */
	public static File cleanDots(final File file)
	{
		if (file == null)
		{
			return null;
		}
		String path = file.getPath();
		if (File.separator != "/")
		{
			path = StringUtil.replaceString(path, File.separator, "/");
			path = UrlUtil.cleanDots(path);
			path = StringUtil.replaceString(path, "/", File.separator);
		}
		else
		{
			path = UrlUtil.cleanDots(path);
		}
		return new File(path);
	}

	/**
	 * copies a File to directory if toFile exists, it is brutally overwritten unless fromFile equals toFile
	 * @param fromFile the File to move
	 * @param toDir the Directory to move to
	 * @return File the moved File if success, else null, i.e. toFile exists with the contents of fromFile
	 */
	public static File copyFileToDir(final File fromFile, final File toDir)
	{
		if (fromFile == null || toDir == null)
		{
			return null;
		}
		if (!toDir.isDirectory())
		{
			toDir.mkdirs();
		}
		final File newFile = getFileInDirectory(toDir, new File(fromFile.getName()));
		final boolean b = copyFile(fromFile, newFile);
		return b ? newFile : null;
	}

	/**
	 * returns a File object corresponding to an instance of localFile placed in dir - No OS calls are made and File is NOT created
	 * @param dir the File Object representing the directory
	 * @param localFile the local file to place in dir, note that only the path is copied - this does copy trees
	 * @return File the File object that represents localFile in Dir
	 */
	public static File getFileInDirectory(final File dir, final File localFile)
	{
		if (dir == null)
		{
			return localFile;
		}
		if (localFile == null)
		{
			return null;
		}
		String fullPath = dir.getPath() + File.separator + localFile.getPath();
		File fullFile = new File(fullPath);
		return cleanDots(fullFile);
	}

	/**
	 * forces deletion of a file
	 * @param file the file to delete
	 * @return true if the file no longer exists
	 */
	public static boolean forceDelete(final File file)
	{
		if (file == null)
		{
			return true;
		}
		if (!file.exists())
		{
			return true;
		}
		boolean bOK = file.delete();
		int i = 1;
		while (!bOK)
		{
			boolean bInterupt = !ThreadUtil.sleep(i * i * 100);
			bOK = file.delete();
			if (bInterupt || i++ > 6)
			{
				LogFactory.getLog(FileUtil.class).warn("cannot force delete of file: " + file.getAbsolutePath());
				break;
			}
		}
		return bOK;
	}

	/**
	 * create a new directory and return null if the directory could not be created
	 * 
	 * @param newDir the path or URL of the new directory
	 * @return
	 */
	public static File getCreateDirectory(final String newDir)
	{
		final File f = UrlUtil.urlToFile(newDir);
		if (f == null)
		{
			return null;
		}
		if (!f.exists())
		{
			f.mkdirs();
		}
		if (!f.isDirectory())
		{
			return null;
		}
		return f;
	}

	/**
	 * @param f the file to cleanup
	 * @return the cleaned file
	 */
	public static File cleanURL(final File f)
	{
		final String s = UrlUtil.fileToUrl(f, false);
		return UrlUtil.urlToFile(s);
	}

	/**
	 * check whether a file is a directory. If a unix file is a symbolic link to a directory, it is also assumed to be a directory
	 * @param f the file to test
	 * @return true if f is a directory or link to a directory
	 */
	public static boolean isDirectory(final File f)
	{
		if (f == null)
			return false;
		if (f.isDirectory())
			return true;

		try
		{
			File canonical = f.getCanonicalFile();
			return canonical.isDirectory();
		}
		catch (IOException e)
		{
			return false;
		}
	}

	/**
	 * check whether a file is a directory. If a unix file is a symbolic link to a directory, it is also assumed to be a directory
	 * @param s the file to test
	 * @return true if f is a directory or link to a directory
	 */
	public static boolean isDirectory(final String s)
	{
		return isDirectory(UrlUtil.urlToFile(s));
	}

	/**
	 * check whether a file is absolute
	 * @param f the file to test
	 * @return true if f is absolute;
	 */
	public static boolean isAbsoluteFile(final File f)
	{
		final String s = f == null ? null : f.getPath();
		return isAbsoluteFile(s);
	}

	/**
	 * @param f the file path to test
	 * @return true if s is absolute
	 */
	public static boolean isAbsoluteFile(String f)
	{
		if (f == null)
		{
			return false;
		}

		if (f.startsWith(File.separator))
		{
			return true;
		}

		final File[] roots = File.listRoots();
		if (roots != null)
		{
			f = f.toLowerCase();
			for (int i = 0; i < roots.length; i++)
			{
				if (f.startsWith(roots[i].getPath().toLowerCase()))
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * @param file
	 * @return the file extension
	 */
	public static String getExtension(final File file)
	{
		return file == null ? null : UrlUtil.extension(file.getName());
	}

	/**
	 * similar to File.createFile but also creates any required directories
	 * @param file the file to create
	 * @return true if the file exists after the call, else false
	 */
	public static boolean createNewFile(final File file)
	{
		if (file == null)
		{
			return false;
		}
		if (file.exists())
		{
			return true;
		}
		final File parent = file.getParentFile();
		if (parent != null)
		{
			parent.mkdirs();
		}
		try
		{
			return file.createNewFile();
		}
		catch (final IOException x)
		{
			return false;
		}
	}

	/**
	 * checks the equivalence of files - todo os specific behavior (just in case)
	 * @param file1
	 * @param file2
	 * @return
	 */
	public static boolean equals(final File file1, final File file2)
	{
		return ContainerUtil.equals(file1, file2);
	}

	/**
	 * create a buffered input stream for a file
	 * @param file
	 * @return the buffered input stream, null if snafu
	 */
	public static BufferedInputStream getBufferedInputStream(File file)
	{
		if (file == null)
		{
			LogFactory.getLog(FileUtil.class).warn("extracting stream from null file");
			return null;
		}
		FileInputStream fis;
		try
		{
			fis = new FileInputStream(file);
		}
		catch (FileNotFoundException x)
		{
			LogFactory.getLog(FileUtil.class).warn("extracting stream from non-existing file: " + file.getAbsolutePath(), x);
			return null;
		}
		BufferedInputStream bis = new BufferedInputStream(fis);
		return bis;
	}

	/**
	 * create a buffered output stream for a file
	 * @param file
	 * @return the buffered output stream, null if snafu
	 */
	public static BufferedOutputStream getBufferedOutputStream(File file)
	{
		return getBufferedOutputStream(file, false);
	}

	/**
	 * create a buffered output stream for a file
	 * @param file
	 * @param append 
	 * @return the buffered output stream, null if snafu
	 */
	public static BufferedOutputStream getBufferedOutputStream(File file, boolean append)
	{
		if (file == null)
			return null;

		if (!append || !file.exists())
			createNewFile(file);

		FileOutputStream fos;
		try
		{
			fos = new FileOutputStream(file, append);
		}
		catch (FileNotFoundException x)
		{
			return null;
		}
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		return bos;
	}

	/**
	 * 
	 * @return true if we are on a windows file system
	 */
	public static boolean isWindows()
	{
		return File.separator.equals("\\");
	}
}
