/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2019 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.util.net;

import java.net.HttpURLConnection;

import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.util.StringUtil;
import org.cip4.jdflib.util.UrlUtil;

/**
 * helper class to set mime details
 *
 * @author prosirai
 *
 */
public class HTTPDetails
{
	public static final String BEARER = "Bearer";

	/**
	 *
	 */
	public HTTPDetails()
	{
		super();
		chunkSize = defaultChunkSize;
		bKeepAlive = true;
		redirect = 0;
		bearerToken = null;
		connectionTimeout = UrlUtil.getConnectionTimeout();
		headers = new JDFAttributeMap();
	}

	/**
	 * @return the connectionTimeout
	 */
	public int getConnectionTimeout()
	{
		return connectionTimeout;
	}

	/**
	 * @param connectionTimeout the connectionTimeout to set
	 */
	public void setConnectionTimeout(final int connectionTimeout)
	{
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * size of http chunks to be written, if <=0 no chunks
	 *
	 */
	private int chunkSize;
	private boolean bKeepAlive;
	private String bearerToken;
	private final JDFAttributeMap headers;

	/**
	 * @return the bearerToken
	 */
	public String getBearerToken()
	{
		return bearerToken;
	}

	/**
	 * @param bearerToken the bearerToken to set must be completely encoded
	 */
	public void setBearerToken(final String bearerToken)
	{
		this.bearerToken = bearerToken;
	}

	public int getRedirect()
	{
		return redirect;
	}

	public void setRedirect(final int redirect)
	{
		this.redirect = redirect;
	}

	private int redirect;
	private int connectionTimeout;

	/**
	 * Getter for chunkSize attribute.
	 *
	 * @return the chunkSize
	 */
	public int getChunkSize()
	{
		return chunkSize;
	}

	/**
	 * Setter for chunkSize attribute.
	 *
	 * @param chunkSize the chunkSize to set
	 */
	public void setChunkSize(final int chunkSize)
	{
		this.chunkSize = chunkSize;
	}

	/**
	 * Getter for bKeepAlive attribute.
	 *
	 * @return the bKeepAlive
	 */
	public boolean isbKeepAlive()
	{
		return bKeepAlive;
	}

	/**
	 * Setter for bKeepAlive attribute.
	 *
	 * @param bKeepAlive the bKeepAlive to set
	 */
	public void setbKeepAlive(final boolean bKeepAlive)
	{
		this.bKeepAlive = bKeepAlive;
	}

	/**
	 * the default chnk size; -1= don't chunk
	 */
	public static int defaultChunkSize = -1; // don't chunk by default

	/**
	 * apply these details to the connection specified
	 *
	 * @param urlCon
	 */
	public void applyTo(final HttpURLConnection urlCon)
	{
		if (urlCon != null)
		{
			if (chunkSize > 0)
			{
				urlCon.setChunkedStreamingMode(chunkSize);
			}
			setHeader(UrlUtil.CONNECTION, bKeepAlive ? UrlUtil.KEEPALIVE : UrlUtil.CLOSE);
			if (!StringUtil.isEmpty(bearerToken))
			{
				setHeader(UrlUtil.AUTHORIZATION, BEARER + " " + bearerToken);
			}
			urlCon.setConnectTimeout(getConnectionTimeout());
			for (final String key : headers.keySet())
			{
				urlCon.setRequestProperty(key, headers.get(key));
			}
		}
	}

	/**
	 *
	 * @param key
	 * @param val
	 */
	public void setHeader(final String key, final String val)
	{
		if (!StringUtil.isEmpty(key))
		{
			if (StringUtil.isEmpty(val))
				headers.remove(key);
			else
				headers.put(key, val);
		}

	}

	/**
	 * get a redirect incremented by 1, if null create o defaut first
	 *
	 * @param details
	 * @return
	 */
	public static HTTPDetails getRedirect(HTTPDetails details)
	{
		if (details == null)
		{
			details = new HTTPDetails();
		}
		details.setRedirect(details.getRedirect() + 1);
		return details;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "HTTPDetails [chunkSize=" + chunkSize + ", bKeepAlive=" + bKeepAlive + ", bearerToken=" + bearerToken + ", redirect=" + redirect + ", connectionTimeout=" + connectionTimeout
				+ "headers: " + headers.showKeys(null) + "]";
	}

	/**
	 * @param key
	 * @return
	 * 
	 */
	public String getHeader(final String key)
	{
		return headers.get(key);
	}

	/**
	 * @return
	 *
	 */
	public VString getHeaders()
	{
		return headers.getKeys();
	}

}