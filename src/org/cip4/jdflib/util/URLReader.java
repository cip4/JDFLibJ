package org.cip4.jdflib.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import java.util.zip.ZipEntry;

import javax.mail.BodyPart;
import javax.mail.Multipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.util.zip.ZipReader;

/**
 * 
 *  
 * @author rainerprosi
 * @date Feb 1, 2012
 */
public class URLReader
{
	private final String urlString;
	private BodyPart bodypart;
	private ZipReader zip;
	private final Vector<File> localRoots;
	private final Log log;
	private File notRelative;

	/**
	 * @param urlString 
	 * 
	 */
	public URLReader(final String urlString)
	{
		this.urlString = urlString;
		localRoots = new Vector<File>();
		log = LogFactory.getLog(getClass());
		notRelative = null;
	}

	/**
	 * @param urlString 
	 * @param doc 
	 * 
	 */
	public URLReader(final String urlString, XMLDoc doc)
	{
		this(urlString);
		bodypart = doc.getBodyPart();
		zip = doc.getZipReader();
		String orig = doc.getOriginalFileName();
		if (orig != null)
		{
			File f = new File(orig);
			File parent = f.getParentFile();
			if (parent != null)
			{
				addLocalRoot(parent);
			}
		}
	}

	/**
	 *  
	 * @param bodyPart
	 */
	public void setBodyPart(BodyPart bodyPart)
	{
		this.bodypart = bodyPart;
	}

	/**
	 * add a root for local files 
	 * @param root
	 */
	public void addLocalRoot(File root)
	{
		if (root == null)
		{
			log.error("cannot add null root");
		}
		else
		{
			localRoots.add(root);
			ContainerUtil.unify(localRoots);
		}
	}

	/**
	 *  
	 * @param zip
	 */
	public void setZipReader(ZipReader zip)
	{
		this.zip = zip;
	}

	/**
	 * get the opened input stream for a given url string
	 * 
	 *  
	 * @return
	 */
	InputStream getBodyPartInputStream()
	{
		InputStream retStream = null;
		if (bodypart != null)
		{
			final Multipart multipart = bodypart.getParent();
			retStream = UrlUtil.getCidURLStream(urlString, multipart);
		}
		return retStream;
	}

	/**
	 * get the opened input stream for a given url string
	 * 
	 *  
	 * @return
	 */
	InputStream getZipInputStream()
	{
		InputStream retStream = null;
		if (zip != null)
		{
			ZipEntry e = zip.getEntry(urlString);
			retStream = e == null ? null : zip.getInputStream();
		}
		return retStream;
	}

	/**
	 *  
	 * @return
	 */
	public InputStream getURLInputStream()
	{
		InputStream retStream = getHTTPInputStream();
		if (retStream == null)
			retStream = getBodyPartInputStream();
		if (retStream == null)
			retStream = getZipInputStream();
		if (retStream == null)
			retStream = getAbsoluteFileInputStream();
		if (retStream == null)
			retStream = getRelativeFileInputStream();
		return retStream;
	}

	/**
	 *  
	 * @return
	 */
	public XMLDoc getXMLDoc()
	{
		InputStream retStream = getURLInputStream();
		XMLDoc doc = XMLDoc.parseStream(retStream);
		applyDoc(doc);
		return doc;
	}

	/**
	 *  
	 * @return
	 */
	public JDFDoc getJDFDoc()
	{
		InputStream retStream = getURLInputStream();
		JDFDoc doc = JDFDoc.parseStream(retStream);
		applyDoc(doc);
		return doc;
	}

	/**
	 * 
	 *  
	 * @param doc
	 */
	private void applyDoc(XMLDoc doc)
	{
		if (doc != null)
		{
			doc.setBodyPart(bodypart);
			doc.setZipReader(zip);
			String filename = notRelative == null ? urlString : notRelative.getAbsolutePath();
			doc.setOriginalFileName(filename);
		}
	}

	/**
	 * 
	 * @return
	 */
	InputStream getHTTPInputStream()
	{
		InputStream retStream = null;
		if (UrlUtil.isHttp(urlString))
		{
			try
			{
				final URL url = new URL(urlString);
				final URLConnection urlConnection = url.openConnection();
				retStream = urlConnection.getInputStream();
			}
			catch (final MalformedURLException x)
			{
				//
			}
			catch (final IOException x)
			{
				//
			}
		}
		return retStream;
	}

	/**
	 * 
	 * @return
	 */
	InputStream getAbsoluteFileInputStream()
	{
		InputStream retStream = null;

		final File f = UrlUtil.urlToFile(urlString);
		if ((f != null) && f.canRead())
		{
			retStream = FileUtil.getBufferedInputStream(f);
		}
		return retStream;
	}

	/**
	 * 
	 * @return
	 */
	InputStream getRelativeFileInputStream()
	{
		InputStream retStream = null;
		if (UrlUtil.isRelativeURL(urlString))
		{
			final File fLocal = UrlUtil.urlToFile(urlString);
			for (File root : localRoots)
			{
				File f = FileUtil.getFileInDirectory(root, fLocal);
				if ((f != null) && f.canRead())
				{
					retStream = FileUtil.getBufferedInputStream(f);
					notRelative = f;
					break;
				}
			}
		}
		return retStream;
	}
}