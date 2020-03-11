/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
/**
 * Created on Jul 5, 2006, 11:45:44 AM org.cip4.jdflib.util.MimeUtil.java Project Name: mimeutil
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Vector;

import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.util.mime.BodyPartHelper;
import org.cip4.jdflib.util.mime.MimeHelper;
import org.cip4.jdflib.util.mime.MimeReader;
import org.cip4.jdflib.util.mime.MimeWriter;

/**
 * MIME utilities for reading and writing MIME/MULTIPART/RELATED streams
 *
 * @author Markus Nyman, (markus.cip4@myman.se)
 */
public class MimeUtil extends UrlUtil
{

	/**
	 * helper class to set mime details
	 *
	 * @author prosirai
	 */
	public static class MIMEDetails
	{
		/**
		 * transfer encoding used when streaming body parts, May be null to specify java default behavior
		 */
		public static String defaultTransferEncoding = BINARY;
		/**
		 * http details
		 */
		public org.cip4.jdflib.util.net.HTTPDetails httpDetails = new org.cip4.jdflib.util.net.HTTPDetails();
		/**
		 *
		 */
		public String transferEncoding = defaultTransferEncoding;
		/**
		 * if true, the stream will be parsed for a semicolon after the mime multipart and said semicolon will be replaced with a cr/lf
		 */
		public boolean modifyBoundarySemicolon = false;
	}

	/**
	 * data source for binary files
	 *
	 * @author prosirai
	 */
	public static class ByteArrayDataSource implements DataSource
	{
		String contentType;
		ByteArrayIOStream ioStream;

		/**
		 * create a data source from a byte array
		 *
		 * @param _ioStream the ByteArrayIOStream to use
		 * @param _contentType the content type of the contents
		 */
		public ByteArrayDataSource(final ByteArrayIOStream _ioStream, final String _contentType)
		{
			contentType = _contentType;
			ioStream = _ioStream;
		}

		/**
		 * * (non-Javadoc)
		 *
		 * @see javax.activation.DataSource#getContentType()
		 */
		@Override
		public String getContentType()
		{
			return contentType;
		}

		/**
		 * @see javax.activation.DataSource#getInputStream()
		 */
		@Override
		public InputStream getInputStream()
		{
			return ioStream.getInputStream();
		}

		/**
		 * @see javax.activation.DataSource#getName()
		 */
		@Override
		public String getName()
		{
			// not needed
			return null;
		}

		/**
		 * @see javax.activation.DataSource#getOutputStream()
		 */
		@Override
		public OutputStream getOutputStream()
		{
			return ioStream;
		}

	}

	/**
	 * commonly used strings
	 */

	public static final String MULTIPART_RELATED = "multipart/related";

	/**
	 * @param bp
	 * @param cid
	 *
	 */
	public static void setContentID(final BodyPart bp, final String cid)
	{
		new BodyPartHelper(bp).setContentID(cid);
	}

	/**
	 * set the filename header of a bodypart to a string
	 *
	 * @param bp the bodypart
	 * @param path the path to set
	 */
	public static void setFileName(final BodyPart bp, final String path)
	{
		new BodyPartHelper(bp).setFileName(path);
	}

	/**
	 * get the filename header of a bodypart a string if no file name is set, a unique filename is generated from cid and content type
	 *
	 * @param bp the bodypart
	 * @return the file name, null if bp is null
	 */
	public static String getFileName(final BodyPart bp)
	{
		return new BodyPartHelper(bp).getFileName();
	}

	/**
	 * get the ContentID header of a bodypart a string
	 *
	 * @param bp the bodypart
	 * @return the cid, null if there was an error
	 */
	public static String getContentID(final BodyPart bp)
	{
		return new BodyPartHelper(bp).getContentID();
	}

	/**
	 * Extracts all the parts of a multipart MIME message and returns an array of InputStream for each of the separate MIME parts.
	 *
	 * @param mimeStream
	 * @return
	 */
	public static BodyPart[] extractMultipartMime(final InputStream mimeStream)
	{
		final MimeReader mr = new MimeReader(mimeStream);
		return mr.getBodyParts();
	}

	/**
	 * get all the parts of of a multipart an
	 *
	 * @param mp the multiPart to extract
	 * @return the array of parts, null if snafu...
	 */
	public static BodyPart[] getBodyParts(final Multipart mp)
	{
		return new MimeHelper(mp).getBodyParts();
	}

	/**
	 * get the MIME BodyPart from a multiPart package with a given cid
	 *
	 * @param mp the multipart package to search in
	 * @param cid the cid of the requested bodypart
	 * @return BodyPart the matching BodyPart, null if none is found
	 */
	public static BodyPart getPartByCID(final Multipart mp, final String cid)
	{
		return new MimeHelper(mp).getPartByCID(cid);
	}

	/**
	 * get the MIME BodyPart from a multiPart package with a given cid create one if it does not exist;
	 *
	 * @param mp the multipart package to search in
	 * @param cid the cid of the requested bodypart
	 * @return BodyPart the matching BodyPart, null if none is found
	 */
	public static BodyPart getCreatePartByCID(final Multipart mp, final String cid)
	{
		return new MimeWriter(mp).getCreatePartByCID(cid);
	}

	/**
	 * get the JDF Doc from a stream, also handle non mime streams gracefully
	 *
	 * @param stream the stream to search in
	 * @param index the index of the body part to search
	 * @return JDFDoc the parsed xml JDFDoc, null if stream does not contain xml
	 */
	public static JDFDoc getJDFDoc(final InputStream stream, final int index)
	{
		final MimeReader mr = new MimeReader();
		return mr.getJDFDoc(stream, index);
	}

	/**
	 * get the JDF Doc from a given body part
	 *
	 * @param bp the BodyPart to search in
	 * @return JDFDoc the parsed xml JDFDoc, null if bp does not contain xml
	 */
	public static JDFDoc getJDFDoc(final BodyPart bp)
	{
		return new BodyPartHelper(bp).getJDFDoc();
	}

	/**
	 * check if a BodyPart matches a given cid
	 *
	 * @param bp the bodyPart to check
	 * @param cid the cid string any '<' '>' or 'cid:' prefixes are removed if null, anything matches
	 * @return true if this bp matches the cid
	 */
	public static boolean matchesCID(final BodyPart bp, String cid)
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

		final String s = getContentID(bp);
		if (s == null)
		{
			return false;
		}

		return cid.equalsIgnoreCase(s);
	}

	/**
	 * helper to create a root multipart from a file
	 *
	 * @param fileName the name of the file used as input
	 * @return MultiPart the Multipart that represents the root mime, null if something went wrong
	 */
	public static Multipart getMultiPart(final String fileName)
	{
		final MimeReader r = new MimeReader(fileName);
		return r.getMultiPart();
	}

	/**
	 * create a root multipart from an input stream
	 *
	 * @param mimeStream the input stream
	 * @return MultiPart the Multipart that represents the root mime, null if something went wrong
	 */
	public static Multipart getMultiPart(final InputStream mimeStream)
	{
		return new MimeReader(mimeStream).getMultiPart();
	}

	/**
	 * checkst whether the mime type corresponds to one of "application/vnd.cip4-jdf+xml"; "application/vnd.cip4-jmf+xml"; "text/xml";
	 *
	 * @param fileName the string to test
	 * @return the mime type
	 * @deprecated use UrlUtil.getMimeTypeFromURL(fileName);
	 */
	@Deprecated
	public static String getMimeTypeFromExt(final String fileName)
	{
		return UrlUtil.getMimeTypeFromURL(fileName);

	}

	/**
	 * checks whether the mime type corresponds to one of "application/vnd.cip4-jdf+xml"; "application/vnd.cip4-jmf+xml"; "text/xml";
	 *
	 * @param mimeType the string to test
	 * @return true if matches
	 */
	public static boolean isJDFMimeType(String mimeType)
	{

		if (mimeType == null)
		{
			return false;
		}
		mimeType = StringUtil.token(mimeType, 0, ";");
		return JDFConstants.MIME_JDF.equalsIgnoreCase(mimeType) || JDFConstants.MIME_JMF.equalsIgnoreCase(mimeType) || JDFConstants.MIME_TEXTXML.equalsIgnoreCase(mimeType)
				|| UrlUtil.APPLICATION_XML.equalsIgnoreCase(mimeType);
	}

	/**
	 * checks whether the mime type corresponds to mimetyoe
	 *
	 * @param toCheck the string to check against
	 * @param mimeType the mime type
	 * @return true if matches
	 */
	public static boolean isMimeType(String toCheck, final String mimeType)
	{
		if (toCheck == null)
		{
			return false;
		}
		toCheck = StringUtil.token(toCheck, 0, ";");
		return mimeType.equalsIgnoreCase(toCheck);
	}

	/**
	 * @param docJMF
	 * @param docJDF
	 * @return
	 * @deprecated use 3 parameter version
	 */
	@Deprecated
	static public Multipart buildMimePackage(final JDFDoc docJMF, final JDFDoc docJDF)
	{
		return buildMimePackage(docJMF, docJDF, true);
	}

	/**
	 * build a MIME package that contains all references in all FileSpecs of a given JDFDoc the doc is modified so that all URLs are cids
	 *
	 * @param docJMF the JDFDoc representation of the JMF that references the jdf to package, if null only the jdf is packaged note that the URL of docJDF must already be specified as a CID
	 * @param docJDF the JDFDoc representation of the JDF to package
	 * @param extendReferenced if true, also package any further referenced files
	 * @return a Message representing the resulting MIME package, null if an error occurred
	 */
	static public Multipart buildMimePackage(final JDFDoc docJMF, final XMLDoc docJDF, final boolean extendReferenced)
	{
		// Create a MIME package
		final MimeWriter mimeWriter = new MimeWriter();
		mimeWriter.buildMimePackage(docJMF, docJDF, extendReferenced);
		return mimeWriter.getMultiPart();
	}

	/**
	 * @param urlString
	 * @return
	 */
	public static String urlToCid(String urlString)
	{
		if (urlString == null)
		{
			return null;
		}

		if (urlString.startsWith("<"))
		{
			urlString = urlString.substring(1);
		}

		if (urlString.toLowerCase().startsWith("cid:"))
		{
			urlString = urlString.substring(4);
		}

		if (urlString.endsWith(">"))
		{
			urlString = urlString.substring(0, urlString.length() - 1);
		}

		return "cid:" + StringUtil.normalize(new File(urlString).getName(), false, JDFConstants.UNDERSCORE); //
	}

	/**
	 * Builds a MIME package.
	 *
	 * @param vXMLDocs the Vector of XMLDoc representing the JMF and JDFs to be stored as the first part of the package t
	 * @return a Message representing the resulting MIME package, null if an error occured
	 */
	static public Multipart buildMimePackage(final Vector<? extends XMLDoc> vXMLDocs)
	{
		final MimeWriter mimeWriter = new MimeWriter();
		mimeWriter.buildMimePackage(vXMLDocs);
		return mimeWriter.getMultiPart();
	}

	/**
	 * @param multipart
	 * @param xmlDoc
	 * @param cid
	 * @return
	 */
	public static BodyPart updateXMLMultipart(final Multipart multipart, final XMLDoc xmlDoc, final String cid)
	{
		return new MimeWriter(multipart).updateXMLMultipart(xmlDoc, cid);
	}

	/**
	 * sets the content of a bodypart to the xmlDoc - correctly handling non-ascii features and setting the correct content type
	 *
	 * @param messageBodyPart the BodyPart to fill
	 * @param xmlDoc the xmlDoc to fill in
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void setContent(final BodyPart messageBodyPart, final XMLDoc xmlDoc) throws MessagingException, IOException
	{
		new BodyPartHelper(messageBodyPart).setContent(xmlDoc);
	}

	// //////////////////////////////////////////////////////////////////////////
	// /////////////////

	/**
	 * write a Multipart to an output URL File: and http: are currently supported Use HttpURLConnection.getInputStream() to retrieve the http response
	 *
	 * @param mp the mime MultiPart to write
	 * @param strUrl the URL to write to
	 * @return {@link HttpURLConnection} the opened http connection, null in case of error or file
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static HttpURLConnection writeToURL(final Multipart mp, final String strUrl) throws IOException, MessagingException
	{
		return writeToURL(mp, strUrl, null);
	}

	/**
	 * write a Multipart to an output URL File: and http: are currently supported Use HttpURLConnection.getInputStream() to retrieve the http response
	 *
	 * @param mp the mime MultiPart to write
	 * @param strUrl the URL to write to
	 * @param mimeDetails
	 * @return {@link HttpURLConnection} the opened http connection, null in case of error or file
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static HttpURLConnection writeToURL(final Multipart mp, final String strUrl, final MIMEDetails mimeDetails) throws IOException, MessagingException
	{
		final MimeWriter mimeWriter = new MimeWriter(mp);
		mimeWriter.setMIMEDetails(mimeDetails);
		final UrlPart part = mimeWriter.writeToURL(strUrl);
		return (part != null && (part.getConnection() instanceof HttpURLConnection)) ? (HttpURLConnection) part.getConnection() : null;
	}

	/**
	 * submit a multipart file to a queue
	 *
	 * @param docJMF the jmf document containing the submitqueueentry or resubmitqueueentry
	 * @param docJDF the jdf to submit
	 * @param strUrl the url to submit to
	 * @param urlDet url details
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static JDFDoc writeToQueue(final JDFDoc docJMF, final JDFDoc docJDF, final String strUrl, final MIMEDetails urlDet) throws IOException, MessagingException
	{
		final MimeWriter mw = new MimeWriter();
		return mw.writeToQueue(docJMF, docJDF, strUrl);
	}

	/**
	 * @deprecated
	 * @param m
	 * @param fileName
	 * @return the File that was written
	 */
	@Deprecated
	public static File writeToFile(final Multipart m, final String fileName)
	{
		return writeToFile(m, fileName, null);
	}

	/**
	 * write a Multipart to an output file
	 *
	 * @param m the mime MultiPart to write
	 * @param fileName the file name
	 * @param md
	 * @return
	 */
	public static File writeToFile(final Multipart m, final String fileName, final MIMEDetails md)
	{
		final MimeWriter mw = new MimeWriter(m);
		mw.setMIMEDetails(md);
		return mw.writeToFile(fileName);
	}

	/**
	 * @deprecated
	 * @param m
	 * @param outStream
	 * @throws IOException
	 * @throws MessagingException
	 */
	@Deprecated
	public static void writeToStream(final Multipart m, final OutputStream outStream) throws IOException, MessagingException
	{
		writeToStream(m, outStream, null);
	}

	/**
	 * write a Multipart to a Stream
	 *
	 * @param m the mime MultiPart to write
	 * @param outStream the existing output stream, note that a buffered output stream is created in case outStream is unbuffered
	 * @param md details for messaging
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static void writeToStream(final Multipart m, final OutputStream outStream, final MIMEDetails md) throws IOException, MessagingException
	{
		final MimeWriter mimeWriter = new MimeWriter(m);
		mimeWriter.setMIMEDetails(md);
		mimeWriter.writeToStream(outStream);
	}

	/**
	 * write a Message to a directory
	 *
	 * @param mp the mime Message to write
	 * @param directory the directory to use as '.' for writing the mime parts
	 * @throws MessagingException
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static void writeToDir(final Multipart mp, final File directory) throws MessagingException, IOException
	{
		new MimeWriter(mp).writeToDir(directory);
	}

	/**
	 * @param bp
	 * @param directory
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void writeBodyPartToFile(final BodyPart bp, final File directory) throws IOException, MessagingException
	{
		new BodyPartHelper(bp).writeToDir(directory);
	}

	/**
	 * gets the JMF document of a submitqueueentry or returnqueuentry and the attached jdf document
	 *
	 * @param mp the Multipart to search
	 * @return one or two JDFDocs: bp[0] is the jmf, bp[1] is the jdf, if a JDF is referenced;
	 */
	public static JDFDoc[] getJMFSubmission(final Multipart mp)
	{
		final BodyPart bp[] = getBodyParts(mp);
		if (bp == null || bp.length < 1)
		{
			return null;
		}
		final JDFDoc jmf = getJDFDoc(bp[0]);
		final JDFJMF jmfRoot = jmf == null ? null : jmf.getJMFRoot();
		if (jmfRoot == null)
		{
			return null;
		}
		final String subURL = jmfRoot.getSubmissionURL();

		if (subURL == null)
		{
			return new JDFDoc[] { jmf };
		}
		final BodyPart bpJDF = getPartByCID(mp, subURL);
		final JDFDoc docs[] = new JDFDoc[2];
		docs[0] = jmf;
		docs[1] = getJDFDoc(bpJDF);
		if (docs[1] == null)
		{
			return new JDFDoc[] { jmf };
		}
		return docs;
	}

	/**
	 * @param contentType
	 * @return
	 */
	public static boolean isMimeMultiPart(String contentType)
	{
		contentType = StringUtil.token(contentType, 0, ";");
		if (contentType != null)
			contentType = contentType.trim();
		return MULTIPART_RELATED.equalsIgnoreCase(StringUtil.token(contentType, 0, ";"));
	}
}
