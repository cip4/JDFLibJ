/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2023 The International Cooperation for the Integration of
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
package org.cip4.jdflib.extensions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.ifaces.IStreamWriter;
import org.cip4.jdflib.ifaces.IURLSetter;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFQueueSubmissionParams;
import org.cip4.jdflib.jmf.JDFResubmissionParams;
import org.cip4.jdflib.jmf.JDFReturnQueueEntryParams;
import org.cip4.jdflib.util.NumberFormatter;
import org.cip4.jdflib.util.StreamUtil;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

public class XJDFZipWriter implements IStreamWriter
{

	public XJDFZipWriter()
	{
		super();
		xjmf = null;
		vxjdf = new ArrayList<>();
		auxMap = new HashMap<>();
		commandType = EnumType.SubmitQueueEntry;
	}

	/**
	 * @param xjmf the xjmf to set
	 */
	public void setXjmf(final XJMFHelper xjmf)
	{
		this.xjmf = xjmf;
	}

	/**
	 * @param xjdf the xjdf to add
	 */
	public void addXJDF(final XJDFHelper xjdf)
	{
		addXJDF(xjdf, false);
	}

	/**
	 * @param xjdf the xjdf to add
	 */
	public void addXJDF(final XJDFHelper xjdf, final boolean addReferenced)
	{
		vxjdf.add(xjdf);
		if (addReferenced)
		{
			final VElement v = xjdf.getRoot().getChildrenByTagName(null);
			for (final KElement e : v)
			{
				if (e instanceof IURLSetter)
				{
					final IURLSetter u = (IURLSetter) e;
					final InputStream is = u.getURLInputStream();
					if (is != null)
					{
						String url = u.getURL();
						url = updateUrl(url);
						u.setURL(url);
						addAux(url, is);
					}
				}
			}
		}
	}

	String updateUrl(final String url)
	{
		return "content/" + UrlUtil.getFileName(url, null);
	}

	/**
	 *
	 * @param path the local path where we want the stream copy in the zip file
	 * @param inStream the stream...
	 */
	public void addAux(final String path, final InputStream inStream)
	{
		final String newPath = UrlUtil.getSecurePath(path, false);
		if (inStream != null)
		{
			auxMap.put(newPath, inStream);
		}
	}

	private static final Log log = LogFactory.getLog(XJDFZipWriter.class);

	private XJMFHelper xjmf;
	private final ArrayList<XJDFHelper> vxjdf;
	private final Map<String, InputStream> auxMap;
	private EnumType commandType;
	private String qeID;

	void writeAux(final ZipOutputStream zos)
	{
		for (final String path : auxMap.keySet())
		{
			writeAux(zos, path);
		}
	}

	/**
	 * 
	 * @return
	 */
	public int numAux()
	{
		return auxMap.size();
	}

	/**
	 *
	 * @param zos
	 *
	 */
	void writeXJMF(final ZipOutputStream zos)
	{
		ensureXJMF();

		try
		{
			final ZipEntry ze = new ZipEntry("root.xjmf");
			zos.putNextEntry(ze);
			xjmf.writeToStream(zos);
			zos.closeEntry();
		}
		catch (final Exception x)
		{
			log.error("oops: ", x);
		}
	}

	/**
	 *
	 * @param zos
	 * @param h
	 * @param i
	 */
	void writeXJDF(final ZipOutputStream zos, final XJDFHelper h, final int i)
	{
		try
		{
			final ZipEntry ze = new ZipEntry(getXJDFPath(i));
			zos.putNextEntry(ze);
			h.writeToStream(zos);
			zos.closeEntry();
		}
		catch (final Exception x)
		{
			log.error("oops: ", x);
		}
	}

	/**
	 *
	 * @param zos
	 * @param path
	 */
	void writeAux(final ZipOutputStream zos, final String path)
	{
		try
		{
			final ZipEntry ze = new ZipEntry(path);
			zos.putNextEntry(ze);
			IOUtils.copy(auxMap.get(path), zos);
			zos.closeEntry();
		}
		catch (final Exception x)
		{
			log.error("oops: ", x);
		}
	}

	/**
	 *
	 * @param zos
	 */
	void writeXJDFs(final ZipOutputStream zos)
	{
		int i = 0;
		for (final XJDFHelper h : vxjdf)
		{
			writeXJDF(zos, h, i++);
		}

	}

	XJMFHelper ensureXJMF()
	{
		if (xjmf == null)
		{
			xjmf = new XJMFHelper();
		}
		final MessageHelper mh = xjmf.getCreateMessage(EnumFamily.Command, commandType, 0);
		final IURLSetter qsp;
		if (EnumType.SubmitQueueEntry.equals(commandType))
		{
			qsp = (JDFQueueSubmissionParams) mh.getCreateElement(ElementName.QUEUESUBMISSIONPARAMS);
		}
		else if (EnumType.ResubmitQueueEntry.equals(commandType))
		{
			qsp = (JDFResubmissionParams) mh.getCreateElement(ElementName.RESUBMISSIONPARAMS);
			((JDFResubmissionParams) qsp).setQueueEntryID(qeID);
		}
		else if (EnumType.ReturnQueueEntry.equals(commandType))
		{
			qsp = (JDFReturnQueueEntryParams) mh.getCreateElement(ElementName.RETURNQUEUEENTRYPARAMS);
			((JDFReturnQueueEntryParams) qsp).setQueueEntryID(qeID);
		}
		else
		{
			// can never get here
			return null;
		}
		if (vxjdf.size() == 1)
		{
			qsp.setURL(getXJDFPath(0));
		}
		else if (vxjdf.size() > 1)
		{
			qsp.setURL("xjdf");
		}
		return xjmf;
	}

	protected String getXJDFPath(final int i)
	{
		if (i >= 0 && i < vxjdf.size())
		{
			final XJDFHelper xjdfHelper = vxjdf.get(i);
			String jobPartID = xjdfHelper.getJobPartID();
			if (StringUtil.isEmpty(jobPartID))
			{
				jobPartID = new NumberFormatter().formatInt(i, 2);
			}
			else
			{
				jobPartID = new NumberFormatter().formatInt(i, 2) + "." + jobPartID;
			}
			return "xjdf/" + xjdfHelper.getJobID() + "." + jobPartID + ".xjdf";
		}
		else
		{
			return null;
		}
	}

	@Override
	public void writeStream(final OutputStream os) throws IOException
	{
		final ZipOutputStream zos = new ZipOutputStream(os);
		writeXJMF(zos);
		writeXJDFs(zos);
		writeAux(zos);
		zos.close();

		StreamUtil.close(os);
	}

	@Override
	public String toString()
	{
		return "XJDFZipWriter [commandType=" + commandType + ", qeID=" + qeID + ", xjmf=" + xjmf + ", vxjdf=" + vxjdf.size() + "]";
	}

	public EnumType getCommandType()
	{
		return commandType;
	}

	/**
	 * 
	 * @param commandType
	 * @throws IllegalArgumentException if commandType is invalid
	 */
	public void setCommandType(final EnumType commandType)
	{
		if (!EnumType.SubmitQueueEntry.equals(commandType) && !EnumType.ResubmitQueueEntry.equals(commandType) && !EnumType.ReturnQueueEntry.equals(commandType))
			throw new IllegalArgumentException("Invalid command type " + commandType);
		this.commandType = commandType;
	}

	public String getQeID()
	{
		return qeID;
	}

	public void setQeID(final String qeID)
	{
		this.qeID = qeID;
	}

}
