package org.cip4.jdflib.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.mail.BodyPart;
import javax.mail.MessagingException;

import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.XMLParser;
import org.cip4.jdflib.util.net.IPollDetails;

/**
 * simple struct to contain the stream and type of a bodypart
 * 
 * @author prosirai
 * 
 */
public class UrlPart implements IPollDetails
{
	/**
	 * 
	 * @return the response code
	 */
	public int getResponseCode()
	{
		return rc;
	}

	/**
	 * @param connection
	 * @throws IOException
	 */
	public UrlPart(final HttpURLConnection connection) throws IOException
	{
		rc = connection.getResponseCode();
		this.connection = connection;
		contentType = connection.getContentType();
		contentLength = connection.getContentLength();

		try
		{
			inStream = connection.getInputStream();
		}
		catch (IOException x)
		{
			inStream = null;
		}
		if (inStream == null)
			inStream = (connection).getErrorStream();
		bufferStream = null;
	}

	/**
	 * @param part
	 * @throws MessagingException
	 * @throws IOException
	 */
	public UrlPart(final BodyPart part) throws MessagingException, IOException
	{
		inStream = part.getInputStream();
		contentLength = part.getSize();
		contentType = part.getContentType();
		connection = null;
		bufferStream = null;
		rc = 200;
	}

	/**
	 * 
	 * @param f
	 * @throws IOException
	 */
	public UrlPart(final File f) throws IOException
	{
		inStream = FileUtil.getBufferedInputStream(f);
		contentLength = f == null ? 0 : f.length();
		contentType = null;
		connection = null;
		rc = f == null ? 500 : 200;
		bufferStream = null;
	}

	private final int rc;
	/**
	 * the input stream of this UrlPart
	 */
	private InputStream inStream;
	/**
	 * the content type of this UrlPart
	 */
	private final String contentType;
	private ByteArrayIOStream bufferStream;

	/**
	 * @return the contentType
	 */
	public String getContentType()
	{
		return contentType;
	}

	/**
	 * @param inStream the inStream to set
	 */
	public void setInStream(InputStream inStream)
	{
		this.inStream = inStream;
		bufferStream = null;
	}

	/**
	 * @see org.cip4.jdflib.util.net.IPollDetails#getResponseStream()
	 * @return the response stream
	*/
	public InputStream getResponseStream()
	{
		return bufferStream == null ? inStream : bufferStream.getInputStream();
	}

	/**
	 * the content length of this UrlPart
	 */
	public long contentLength;
	private final HttpURLConnection connection;

	/**
	 * returns an xmldoc corresponding to this part
	 * @return the doc, null if not xml
	 */
	public XMLDoc getXMLDoc()
	{
		final XMLParser p = new XMLParser();
		final XMLDoc d = p.parseStream(inStream);
		return d;
	}

	/**
	 * buffer my input stream
	 *   
	 */
	public void buffer()
	{
		if (bufferStream == null)
			bufferStream = new ByteArrayIOStream(inStream);
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return the string representation
	*/
	@Override
	public String toString()
	{
		return "URLPart: " + contentType + " length=" + contentLength + " rc=" + rc + "\n" + ((bufferStream == null) ? " <not buffered>" : bufferStream);
	}

	/**
	 * @return the connection
	 */
	public HttpURLConnection getConnection()
	{
		return connection;
	}
}