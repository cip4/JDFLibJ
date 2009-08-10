/**
 * 
 */
package org.cip4.jdflib.util.mime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.MimeUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.cip4.jdflib.util.MimeUtil.ByteArrayDataSource;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * Jul 24, 2009
 */
public class BodyPartHelper
{
	protected BodyPart theBodyPart;

	/**
	 * @param bp
	 * 
	 */
	public BodyPartHelper(final BodyPart bp)
	{
		super();
		theBodyPart = bp;
	}

	/**
	 * creates a new bodypart
	 */
	public BodyPartHelper()
	{
		super();
		createBodyPart();
	}

	/**
	 * @return
	 */
	public BodyPart getBodyPart()
	{
		return theBodyPart;
	}

	/**
	 * 
	 */
	public void createBodyPart()
	{
		theBodyPart = new MimeBodyPart();
	}

	/**
	 * @param cid
	 * 
	 */
	public void setContentID(final String cid)
	{
		if (cid == null)
		{
			return;
		}

		try
		{
			theBodyPart.setHeader(UrlUtil.CONTENT_ID, "<" + MimeUtil.urlToCid(cid).substring(4) + ">");
		}
		catch (final MessagingException x)
		{
			// nop
		}
	}

	/**
	 * set the filename header of a bodypart to a string
	 * @param path the path to set
	 */
	public void setFileName(final String path)
	{
		if (path == null)
		{
			return;
		}
		try
		{
			theBodyPart.setFileName(new File(path).getName());
		}
		catch (final MessagingException x)
		{
			// nop
		}
	}

	/**
	 * get the filename header of a bodypart a string if no file name is set, a unique filename is generated from cid and content type
	 * @return the file name, null if bp is null
	 */
	public String getFileName()
	{
		if (theBodyPart == null)
		{
			return null; // duh
		}
		String s = null;
		try
		{
			s = StringUtil.getNonEmpty(theBodyPart.getFileName());
			if (s != null)
			{
				return s;
			}
		}
		catch (final MessagingException x)
		{
			// nop
		}
		s = getContentID();
		return s;
	}

	/**
	 * check if a BodyPart matches a given cid
	 * @param cid the cid string any '<' '>' or 'cid:' prefixes are removed if null, anything matches
	 * @return true if this bp matches the cid
	 */
	public boolean matchesCID(String cid)
	{
		if (cid == null)
		{
			return true; // wildcard
		}

		if (cid.startsWith("<"))
		{
			cid = cid.substring(1);
		}

		if (cid.toLowerCase().startsWith("cid:"))
		{
			cid = cid.substring(4);
		}

		if (cid.endsWith(">"))
		{
			cid = cid.substring(0, cid.length() - 1);
		}

		final String s = getContentID();
		if (s == null)
		{
			return false;
		}

		return cid.equalsIgnoreCase(s);
	}

	/**
	 * get the ContentID header of a bodypart a string
	 * @return the cid, null if there was an error
	 */
	public String getContentID()
	{
		if (theBodyPart == null)
		{
			return null;
		}
		String[] cids = null;
		try
		{
			cids = theBodyPart.getHeader(UrlUtil.CONTENT_ID);
		}
		catch (final MessagingException e)
		{
			return null;
		}
		final String s = StringUtil.setvString(cids, null, null, null);
		if (s == null)
		{
			return s;
		}

		return MimeUtil.urlToCid(s).substring(4);
	}

	/**
	 * sets the content of a bodypart to the xmlDoc - correctly handling non-ascii features and setting the correct content type
	 * @param xmlDoc the xmlDoc to fill in
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void setContent(final XMLDoc xmlDoc) throws MessagingException, IOException
	{
		if (theBodyPart == null || xmlDoc == null)
		{
			throw new MessagingException("null parameters in setContent");
		}

		// TODO better performing solution for multibyte this quick hack makes
		// quite a few copies...
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		xmlDoc.write2Stream(ios, 0, true);
		final ByteArrayDataSource ds = new ByteArrayDataSource(ios, "text/xml");
		theBodyPart.setDataHandler(new DataHandler(ds));
		xmlDoc.setBodyPart(theBodyPart);
		final KElement root = xmlDoc.getRoot();
		if (root instanceof JDFJMF)
		{
			theBodyPart.setHeader(UrlUtil.CONTENT_TYPE, JDFConstants.MIME_JMF); // JMF
		}
		else if (root instanceof JDFNode)
		{
			theBodyPart.setHeader(UrlUtil.CONTENT_TYPE, JDFConstants.MIME_JDF); // JDF
		}

	}

	/**
	 * @param urlString
	 * @return
	 */
	public BodyPart createFromURL(final String urlString)
	{
		try
		{
			DataSource dataSrc = null;
			final File f = UrlUtil.urlToFile(urlString);
			if (f != null && f.canRead())
			{
				dataSrc = new FileDataSource(f);
			}

			if (dataSrc == null)
			{
				return null; // no data source
			}

			theBodyPart.setDataHandler(new DataHandler(dataSrc));

			setFileName(f == null ? null : f.getAbsolutePath());
			// messageBodyPart.setHeader("Content-Type",
			// JMFServlet.JDF_CONTENT_TYPE); // JDF:
			// application/vnd.cip4-jdf+xml
			setContentID(urlString);
		}
		catch (final MessagingException e1)
		{
			return null;
		}
		return theBodyPart;
	}

	/**
	 * get the JDF Doc from a given body part
	 * @return JDFDoc the parsed xml JDFDoc, null if bp does not contain xml
	 */
	public JDFDoc getJDFDoc()
	{
		if (theBodyPart == null)
		{
			return null;
		}

		try
		{
			final String mimeType = theBodyPart.getContentType();
			if (!MimeUtil.isJDFMimeType(mimeType))
			{
				return null;
			}
			final InputStream is = theBodyPart.getInputStream();
			final JDFParser p = new JDFParser();
			final JDFDoc doc = p.parseStream(is);
			if (doc != null)
			{
				doc.setBodyPart(theBodyPart);
			}
			return doc;
		}
		catch (final IOException e)
		{
			return null; // snafu
		}
		catch (final MessagingException e)
		{
			return null; // snafu
		}
	}

	/**
	 * @param directory
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void writeToDir(final File directory) throws IOException, MessagingException
	{
		boolean exists = directory.exists();
		if (!exists)
		{
			exists = directory.mkdirs();
		}

		if (!exists)
		{
			throw new FileNotFoundException();
		}

		final String fileName = getFileName();
		final File outFile = new File(directory.getPath() + File.separator + fileName);
		FileUtil.streamToFile(getInputStream(), outFile);
	}

	/**
	 * @return
	 */
	public InputStream getInputStream()
	{
		try
		{
			return theBodyPart == null ? null : theBodyPart.getInputStream();
		}
		catch (final IOException e)
		{
			// nop
		}
		catch (final MessagingException e)
		{
			// nop
		}
		return null;
	}

}
