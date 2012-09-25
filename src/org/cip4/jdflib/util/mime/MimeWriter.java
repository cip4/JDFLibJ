/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2012 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.util.mime;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.jmf.JDFResponse;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.ByteArrayIOStream.ByteArrayIOInputStream;
import org.cip4.jdflib.util.MimeUtil;
import org.cip4.jdflib.util.MimeUtil.MIMEDetails;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlPart;
import org.cip4.jdflib.util.UrlUtil;

/**
 * class to create and write mime files
 * 
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * July 24, 2009
 */
public class MimeWriter extends MimeHelper
{
	private final Log log;

	/**
	 * used for some after the fact cleanup - beware as it may hurt performance
	 * @author prosirai
	 */
	static class FixSemiColonStream extends BufferedOutputStream
	{
		private int pos = 0;

		private byte[] smallBuf = new byte[4000];

		/**
		 * @param _out the output stream to fix
		 */
		public FixSemiColonStream(final OutputStream _out)
		{
			super(_out);
		}

		/**  
		 * replaces replace 'content-type:foo;' with 'content-type:foo\n' if necessary
		 * @see java.io.BufferedOutputStream#write(int)
		 */
		@Override
		public synchronized void write(final int b) throws IOException
		{
			if (smallBuf != null) // replace ';' with '\n' if necessary
			{
				if (pos == smallBuf.length)
				{
					smallBuf = null;
				}
				else
				{
					smallBuf[pos++] = (byte) b;
					final int first = Math.max(0, pos - 50);
					if (b == ';')
					{
						final String s = new String(smallBuf, first, pos - 1);
						if (s.toLowerCase().indexOf("content-type:") > 0)
						{
							super.write('\n');
							super.write(b);
							smallBuf = null;
							return;
						}
					}
				}

			}
			super.write(b);
		}

		/** 
		 * @see java.io.BufferedOutputStream#write(byte[], int, int)
		 */
		@Override
		public synchronized void write(final byte[] b, final int off, final int len) throws IOException
		{
			if (smallBuf == null)
			{
				super.write(b, off, len);
			}
			else
			{
				for (int i = off; i < len; i++)
				{
					write(b[i]);
				}
			}
		}
	}

	private MIMEDetails md;

	/**
	 * @param _md
	 */
	public void setMIMEDetails(final MIMEDetails _md)
	{
		md = _md;
	}

	/**
	 * gets an input s
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	public InputStream getInputStream() throws IOException, MessagingException
	{
		ByteArrayIOStream bis = new ByteArrayIOStream();
		writeToStream(bis);
		return bis.getInputStream();
	}

	/**
	 * 
	 */
	public MimeWriter()
	{
		super();
		log = LogFactory.getLog(getClass());
		createMimePackage();
	}

	/**
	 * @param mp
	 * 
	 */
	public MimeWriter(final Multipart mp)
	{
		super();
		log = LogFactory.getLog(getClass());
		theMultipart = mp;
	}

	/**
	 * build an empty MIME package
	 */
	public void createMimePackage()
	{
		// Create a MIME package
		final Multipart multipart = new MimeMultipart("related");
		final Message message = new MimeMessage((Session) null);
		// Put parts in message
		try
		{
			message.setContent(multipart);
		}
		catch (final MessagingException x)
		{
			log.error("cannot create mime package", x);
			return;
		}
		theMultipart = multipart;
	}

	/**
	 * @param is
	 * @param cid
	 * @param contentType
	 * @return
	 */
	public BodyPartHelper updateMultipart(final InputStream is, final String cid, final String contentType)
	{
		if (is == null || StringUtil.getNonEmpty(cid) == null)
		{
			return null;
		}
		final BodyPart bp = getCreatePartByCID(cid);
		if (bp == null)
		{
			return null;
		}
		final ByteArrayIOStream ios = new ByteArrayIOStream(is);
		try
		{
			final ByteArrayDataSource ds = new ByteArrayDataSource(ios.getInputStream(), contentType);
			bp.setDataHandler(new DataHandler(ds));
		}
		catch (final MessagingException e)
		{
			log.error("cannot update mime package", e);
		}
		catch (IOException x)
		{
			log.error("cannot update mime package", x);
		}
		return new BodyPartHelper(bp);
	}

	/**
	 * @param xmlDoc
	 * @param cid
	 * @return
	 */
	public BodyPart updateXMLMultipart(final XMLDoc xmlDoc, String cid)
	{
		if (xmlDoc == null)
		{
			return null;
		}

		final String originalFileName = xmlDoc.getOriginalFileName();
		if (cid == null)
		{
			cid = originalFileName;
		}

		if (cid == null)
		{
			final KElement root = xmlDoc.getRoot();
			cid = "CID_" + ((root instanceof JDFNode && root.hasAttribute(AttributeName.ID)) ? ((JDFNode) root).getID() : KElement.uniqueID(0));
		}

		final BodyPart messageBodyPart = getCreatePartByCID(cid);
		final BodyPartHelper bph = new BodyPartHelper(messageBodyPart);
		try
		{
			bph.setFileName(originalFileName);
			bph.setContent(xmlDoc);
			bph.setContentID(cid);
		}
		catch (final MessagingException x)
		{
			log.error("cannot update mime package", x);
		}
		catch (final IOException x)
		{
			log.error("cannot update mime package", x);
		}

		return messageBodyPart;
	}

	/**
	 * write a Multipart to an output file
	 * @param fileName the file name
	 * @return
	 */
	public File writeToFile(final String fileName)
	{
		final File file = new File(fileName);
		try
		{
			final FileOutputStream fos = new FileOutputStream(file);
			writeToStream(fos);
			return file;
		}
		catch (final FileNotFoundException e)
		{
			log.error("cannot write mime package", e);
			return null;
		}
		catch (final IOException e)
		{
			log.error("cannot write mime package", e);
			return null;
		}
		catch (final MessagingException e)
		{
			log.error("cannot write mime package", e);
			return null;
		}
	}

	/**
	 * write a Message to a directory
	 * @param directory the directory to use as '.' for writing the mime parts
	 * @throws MessagingException
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void writeToDir(final File directory) throws MessagingException, IOException
	{
		boolean exists = directory.exists();
		if (!exists)
		{
			exists = directory.mkdirs();
		}

		if (!exists)
		{
			log.error("cannot create directory: " + directory);
			throw new FileNotFoundException("cannot create directory: " + directory);
		}

		if (!directory.canWrite())
		{
			log.error("cannot write to directory: " + directory);
			throw new IOException("cannot write to directory: " + directory);
		}

		final int parts = getCount();
		for (int i = 0; i < parts; i++)
		{
			final BodyPartHelper bph = getBodyPartHelper(i);
			bph.writeToDir(directory);
			// TODO update urls to the new file values
		}
	}

	/**
	 * write a Multipart to a Stream
	 * @param outStream the existing output stream, note that a buffered output stream is created in case outStream is unbuffered
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void writeToStream(OutputStream outStream) throws IOException, MessagingException
	{

		if (theMultipart == null)
		{
			throw new MessagingException("Multipart must be non null");
		}
		final MimeMessage mm = new MimeMessage((Session) null);
		mm.setContent(theMultipart);
		// buffers are good - the encoders decoders otherwise hit stream
		// read/write once per byte...
		if (!(outStream instanceof BufferedOutputStream) && !(outStream instanceof ByteArrayIOStream))
		{
			outStream = new BufferedOutputStream(outStream);
		}

		if (md != null && md.modifyBoundarySemicolon)
		{
			outStream = new FixSemiColonStream(outStream);
		}

		if (md != null && md.transferEncoding != null)
		{
			final BodyPart bp[] = getBodyParts();
			if (bp != null)
			{
				final int siz = bp.length;
				for (int i = 0; i < siz; i++)
				{
					bp[i].setHeader(UrlUtil.CONTENT_TRANSFER_ENCODING, md.transferEncoding);
				}
			}
		}

		mm.writeTo(outStream);
		outStream.flush();
		outStream.close();
	}

	/**
	 * write a Multipart to an output URL File: and http: are currently supported Use HttpURLConnection.getInputStream() to retrieve the http response
	 * @param strUrl the URL to write to
	 * @return {@link HttpURLConnection} the opened http connection, null in case of error or file
	 * @throws IOException
	 * @throws MessagingException
	 */
	public UrlPart writeToURL(final String strUrl) throws IOException, MessagingException
	{
		UrlPart p = null;
		final URL url = UrlUtil.stringToURL(strUrl);
		if ("File".equalsIgnoreCase(url.getProtocol()))
		{
			File outFile = writeToFile(UrlUtil.urlToFile(strUrl).getAbsolutePath());
			p = outFile == null ? null : new UrlPart(outFile);
		}
		else
		// assume http
		{
			ByteArrayIOStream bos = new ByteArrayIOStream();
			writeToStream(bos);
			p = UrlUtil.writeToURL(strUrl, bos.getInputStream(), UrlUtil.POST, theMultipart.getContentType(), md == null ? null : md.httpDetails);
		}
		return p;
	}

	/**
	 * Builds a MIME package.
	 * @param vXMLDocs the Vector of XMLDoc representing the JMF and JDFs to be stored as the first part of the package t
	 */
	public void buildMimePackage(final Vector<? extends XMLDoc> vXMLDocs)
	{
		// Add other body parts
		final int imax = vXMLDocs.size();
		for (int i = 0; i < imax; i++)
		{
			final XMLDoc d1 = vXMLDocs.elementAt(i);
			updateXMLMultipart(d1, null);
		}
	}

	/**
	 * build a MIME package that contains all references in all FileSpecs of a given JDFDoc the doc is modified so that all URLs are cids
	 * @param docJMF the JDFDoc representation of the JMF that references the jdf to package, if null only the jdf is packaged note that the URL of docJDF must
	 * already be specified as a CID
	 * @param docJDF the JDFDoc representation of the JDF to package
	 * @param extendReferenced if true, also package any further reeferenced files
	 */
	public void buildMimePackage(final JDFDoc docJMF, final XMLDoc docJDF, final boolean extendReferenced)
	{
		// Create a MIME package
		String cid = null;
		if (docJDF != null)
		{
			String originalFileName = docJDF.getOriginalFileName();
			if (KElement.isWildCard(originalFileName))
			{
				originalFileName = "TheJDF.jdf";
			}

			cid = MimeUtil.urlToCid(originalFileName);
		}

		if (docJMF != null && cid != null)
		{
			String originalFileName = docJMF.getOriginalFileName();
			if (KElement.isWildCard(originalFileName))
			{
				final JDFJMF jmf = docJMF.getJMFRoot();

				final JDFMessage m = jmf == null ? null : jmf.getMessageElement(null, null, 0);
				originalFileName = m == null ? "TheJMF.jmf" : m.getType() + ".jmf";
				docJMF.setOriginalFileName(originalFileName);
			}

			final KElement e = docJMF.getRoot();
			final VElement v = e.getChildrenByTagName(null, null, new JDFAttributeMap(AttributeName.URL, "*"), false, false, 0);
			if (v != null)
			{
				final int siz = v.size();
				for (int i = 0; i < siz; i++)
				{
					v.item(i).setAttribute(AttributeName.URL, cid);
				}
			}
		}
		updateXMLMultipart(docJMF, null);

		if (extendReferenced)
		{
			extendMultipart(docJDF, cid);
		}
		else
		{
			updateXMLMultipart(docJDF, cid);
		}
	}

	/**
	 * Adds a JDF document to a multipart. Any files referenced by the JDF document using FileSpec/@URL are also included in the multipart.
	 * 
	 * @param docJDF the JDF document
	 * @param cid the CID the JDF document should have in the multipart
	 * @return the number of files added to the multipart
	 */
	private int extendMultipart(final XMLDoc docJDF, final String cid)
	{
		int n = 0;

		if (docJDF == null)
		{
			log.error("cannot extend null JDF document");
			return 0;
		}

		// Get all FileSpec elements
		final KElement e = docJDF.getRoot();
		final VElement fileSpecs = e.getChildrenByTagName(ElementName.FILESPEC, null, new JDFAttributeMap(AttributeName.URL, "*"), false, false, 0);
		if (fileSpecs != null)
		{
			final int vSize = fileSpecs.size();

			final String[] urlStrings = listURLs(fileSpecs);
			for (int i = 0; i < urlStrings.length; i++)
			{
				if (urlStrings[i] != null)
				{
					// Convert URL to CID and update FileSpec
					File f = UrlUtil.urlToFile(urlStrings[i]);
					if (f != null && !f.isAbsolute())
					{
						// Resolve relative URLs
						if (docJDF.getOriginalFileName() != null)
						{
							final File jdfFile = new File(docJDF.getOriginalFileName());
							f = new File(jdfFile.getParent(), f.getPath());
							urlStrings[i] = UrlUtil.fileToUrl(f, false);
						}
					}
					if (f == null || !f.canRead())
					{
						// Ignore unreadable files
						urlStrings[i] = null;
					}
					else
					{
						// Update FileSpec's URL
						fileSpecs.item(i).setAttribute(AttributeName.URL, MimeUtil.urlToCid(urlStrings[i]), null);
					}
					// Set duplicate URLs to null so that the file is only added
					// once to multipart
					for (int j = 0; j < i; j++)
					{
						if (urlStrings[i] != null && urlStrings[i].equals(urlStrings[j]))
						{
							urlStrings[i] = null;
						}
					}
				}
			}

			updateXMLMultipart(docJDF, cid);

			// add a new body part for each url
			for (int i = 0; i < vSize; i++)
			{
				final String urlString = urlStrings[i];
				if (urlString != null)
				{
					final BodyPartHelper bph = new BodyPartHelper();
					final BodyPart bp = bph.createFromURL(urlString);
					if (bp != null)
					{
						addBodyPart(bph);
						n++;
					}
				}
			}
		}

		return n;
	}

	/**
	 * @param bph
	 */
	private void addBodyPart(final BodyPartHelper bph)
	{
		try
		{
			theMultipart.addBodyPart(bph.getBodyPart());
		}
		catch (final MessagingException e)
		{
			log.error("cannot add bodypart", e);
		}
	}

	/**
	 * Returns the values of the <i>URL</i> attribute of each element in the input list.
	 * @param fileSpecs a list of elements with <i>URL</i> attributes
	 * @return an array containing the value of the <i>URL</i> attribute of each element in the input list. The order of values in the returned array
	 * corresponds to the order of the elements in the input list.
	 */
	private static String[] listURLs(final VElement fileSpecs)
	{
		String[] urlStrings = new String[0];

		if (fileSpecs != null)
		{
			final int vSize = fileSpecs.size();
			urlStrings = new String[vSize];
			for (int i = 0; i < vSize; i++)
			{
				urlStrings[i] = fileSpecs.item(i).getAttribute(AttributeName.URL, null, null);
			}
		}

		return urlStrings;
	}

	/**
	 * submit a multipart file to a queue
	 * @param docJMF the jmf document containing the submitqueueentry or resubmitqueueentry
	 * @param docJDF the jdf to submit
	 * @param strUrl the url to submit to
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	public JDFDoc writeToQueue(final JDFDoc docJMF, final JDFDoc docJDF, final String strUrl) throws IOException, MessagingException
	{
		JDFDoc doc = null;

		buildMimePackage(docJMF, docJDF, true);
		final UrlPart uc = writeToURL(strUrl);
		if (uc == null)
		{
			return doc; // file
		}

		final int rc = uc.getResponseCode();
		final InputStream inputStream = uc.getResponseStream();
		if (rc == 200)
		{
			final ByteArrayIOInputStream bis = ByteArrayIOStream.getBufferedInputStream(inputStream);
			final MimeReader mr = new MimeReader(bis);
			final BodyPartHelper bph = mr.getBodyPartHelper(0);
			doc = bph == null ? null : bph.getJDFDoc();
			if (doc == null)
			{
				bis.reset();
				doc = new JDFParser().parseStream(bis);
				if (doc == null)
				{
					final JDFCommand c = docJMF.getJMFRoot().getCommand(0);
					final JDFJMF respJMF = c.createResponse();
					final JDFResponse r = respJMF.getResponse(0);
					r.setErrorText("Invalid attached JDF", null);
					r.setReturnCode(3); // TODO correct rcs
					doc = respJMF.getOwnerDocument_JDFElement();
				}
			}
		}
		else
		{
			final JDFCommand c = docJMF.getJMFRoot().getCommand(0);
			final JDFJMF respJMF = c.createResponse();
			final JDFResponse r = respJMF.getResponse(0);
			r.setErrorText(("Invalid http response - RC=" + rc), null);
			r.setReturnCode(3); // TODO correct rcs
			doc = respJMF.getOwnerDocument_JDFElement();
		}
		inputStream.close(); // always close the stream
		return doc;
	}
}
