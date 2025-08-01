/*
 *
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
/**
 *
 * Copyright (c) 2001-2014 flyeralarm GmbH, All Rights Reserved.
 */
package org.cip4.jdflib.core;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * This class provides all version and build details of the library. Attention - this only works in case the library is built with maven
 *
 */
public class JDFVersion
{

	/**
	 * The maven ArtifactID of the library.
	 */
	public final static String LIB_ARTIFACT_ID = getBuildProp("lib.artifactId");

	/**
	 * The name of the JDF Library.
	 */
	public final static String LIB_NAME = getBuildProp("lib.name");

	/**
	 * The version of the JDF Library.
	 */
	public final static String LIB_VERSION = getBuildProp("lib.version");

	/**
	 * The build date of the JDF Library.
	 */
	public final static String LIB_RELEASE_DATE = getBuildProp("lib.release.date");

	/**
	 * The major version number of the JDF Library.
	 */
	public final static String LIB_MAJOR_VERSION = getMajorVersion(LIB_VERSION);

	/**
	 * The minor version number of the JDF Library.
	 */
	public final static String LIB_MINOR_VERSION = getMinorVersion(LIB_VERSION);

	/**
	 * The current JDF Version.
	 */
	public final static String JDF_VERSION = getBuildProp("jdf.version");

	private static final String RES_BUILD_PROPS = "/org/cip4/jdflib/build.properties";

	private static Properties props = null;

	/**
	 * Private constructor. This class cannot be instantiated.
	 */
	private JDFVersion()
	{
	}

	/**
	 * Read and returns a build property by key.
	 * 
	 * @param key The key of the build property.
	 * @return The value of the build property by key.
	 */
	private static String getBuildProp(final String key)
	{

		if (props == null)
		{
			props = new Properties();

			try
			{
				final InputStream is = JDFVersion.class.getResourceAsStream(RES_BUILD_PROPS);
				props.load(is);
			}
			catch (final Exception e)
			{

				props = null;
				return null;
			}
		}

		return props.getProperty(key);
	}

	/**
	 * Generates and returns the major version number from the maven version number.
	 * 
	 * @return The major version number as String.
	 */
	private static String getMajorVersion(final String mvnVersion)
	{
		if (mvnVersion == null)
			return null;
		// extract pure version
		final String version = StringUtils.substringBefore(mvnVersion, "-");

		// extract major version
		final int i = version.lastIndexOf(".");
		return version.substring(0, i);
	}

	/**
	 * Generates and returns the minor version number from the maven version number.
	 * 
	 * @return The major version number as String.
	 */
	private static String getMinorVersion(final String mvnVersion)
	{
		if (mvnVersion == null)
			return null;

		// extract pure version
		final String version = StringUtils.substringBefore(mvnVersion, "-");

		// extract major version
		final int i = version.lastIndexOf(".");
		return version.substring(i + 1);
	}
}
