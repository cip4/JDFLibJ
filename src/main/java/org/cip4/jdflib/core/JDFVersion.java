/**
 *
 * Copyright (c) 2001-2014 flyeralarm GmbH, All Rights Reserved.
 */
package org.cip4.jdflib.core;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class provides all version and build details of the library.
 */
public class JDFVersion {

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
    public final static String LIB_MINOR_VERSION = getMinorVersion(LIB_VERSION);;

    /**
     * The current JDF Version.
     */
    public final static String JDF_VERSION = getBuildProp("jdf.version");

    private static final String RES_BUILD_PROPS = "/org/cip4/jdflib/build.properties";

    private static Properties props = null;

    /**
     * Private constructor. This class cannot be instantiated.
     */
    private JDFVersion() {
    }

    /**
     * Read and returns a build property by key.
     * @param key The key of the build property.
     * @return The value of the build property by key.
     */
    private static String getBuildProp(String key) {

        if(props == null) {
            props = new Properties();

            try {
                InputStream is = JDFVersion.class.getResourceAsStream(RES_BUILD_PROPS);
                props.load(is);
            } catch (IOException e) {

                props = null;
                return null;
            }
        }

        return props.getProperty(key);
    }

    /**
     * Generates and returns the major version number from the maven version number.
     * @return The major version number as String.
     */
    private static String getMajorVersion(String mvnVersion) {

        // extract pure version
        String version = StringUtils.substringBefore(mvnVersion, "-");

        // extract major version
        int i = version.lastIndexOf(".");
        return version.substring(0, i);
    }

    /**
     * Generates and returns the minor version number from the maven version number.
     * @return The major version number as String.
     */
    private static String getMinorVersion(String mvnVersion) {

        // extract pure version
        String version = StringUtils.substringBefore(mvnVersion, "-");

        // extract major version
        int i = version.lastIndexOf(".");
        return version.substring(i + 1);
    }
}
