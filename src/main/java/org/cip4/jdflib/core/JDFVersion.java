package org.cip4.jdflib.core;

import java.io.IOException;
import java.util.Properties;

/**
 * This class provides all version and build details of the library.
 */
public class JDFVersion {

    /**
     * The maven ArtifactID of the library.
     */
    public final static String LIB_ARTIFACT_ID = getBuildProp("artifactId");

    /**
     * The name of the JDF Library.
     */
    public final static String LIB_NAME = getBuildProp("name");

    /**
     * The version of the JDF Library.
     */
    public final static String LIB_VERSION = getBuildProp("version");

    /**
     * The build date of the JDF Library.
     */
    public final static String LIB_RELEASE_DATE = getBuildProp("release.date");

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
                props.load(JDFVersion.class.getResourceAsStream(RES_BUILD_PROPS));
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }

        String value = props.getProperty(key);
        return value;
    }
}
