package org.cip4.jdflib.core;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * JUnit test case for JDFVersion.
 */
public class JDFVersionTest {

    @Test
     public void testLibArtifactId() {

        // arrange
        String expected = "JDFLibJ";

        // act
        String actual = JDFVersion.LIB_ARTIFACT_ID;

        // assert
        Assert.assertEquals("ArtifactID number is wrong.", expected, actual);
        System.out.println("JDFLibJ ArtifactID: " + actual);
    }

    @Test
    public void testLibName() {

        // arrange
        String expected = "CIP4 JDF Writer Java";

        // act
        String actual = JDFVersion.LIB_NAME;

        // assert
        Assert.assertEquals("Name number is wrong.", expected, actual);
        System.out.println("JDFLibJ Name: " + actual);
    }

    @Test
    public void testLibReleaseDate() {

        // arrange

        // act
        String actual = JDFVersion.LIB_RELEASE_DATE;

        // assert
        Assert.assertTrue("ReleaseDate is wrong.", actual.matches("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})"));
        System.out.println("JDFLibJ Release Date: " + actual);
    }

    @Test
    public void testLibVersion() {

        // arrange

        // act
        String actual = JDFVersion.LIB_VERSION;

        // assert
        Assert.assertFalse("Version is wrong.", StringUtils.isEmpty(actual));
        System.out.println("JDFLibJ Version: " + actual);
    }

    @Test
    public void testLibMajorVersion() {

        // arrange

        // act
        String actual = JDFVersion.LIB_MAJOR_VERSION;

        // assert
        Assert.assertFalse("Version is wrong.", StringUtils.isEmpty(actual));
        Assert.assertEquals("Major Version is wrong.", 3, StringUtils.split(actual, ".").length);
        Assert.assertEquals("Major Version is wrong.", 1, StringUtils.split(actual, "-").length);
        System.out.println("JDFLibJ Major Version: " + actual);
    }

    @Test
    public void testLibMinorVersion() {

        // arrange

        // act
        String actual = JDFVersion.LIB_MINOR_VERSION;

        // assert
        Assert.assertFalse("Version is wrong.", StringUtils.isEmpty(actual));
        Assert.assertEquals("Major Version is wrong.", 1, StringUtils.split(actual, ".").length);
        Assert.assertEquals("Major Version is wrong.", 1, StringUtils.split(actual, "-").length);
        System.out.println("JDFLibJ Major Version: " + actual);
    }

    @Test
    public void testJdfVersion() {

        // arrange
        String expected = "1.5";

        // act
        String actual = JDFVersion.JDF_VERSION;

        // assert
        Assert.assertEquals("JDF Version number is wrong.", expected, actual);
        System.out.println("JDF Version: " + actual);
    }

    @Test
    public void testJdfVersion_LibVersion() {

        // arrange
        String jdfVersion = JDFVersion.JDF_VERSION;
        String libVersion = JDFVersion.LIB_VERSION;

        // act
        int i = libVersion.indexOf(".") + 1;
        int n = libVersion.lastIndexOf(".");

        String extractedVersion = libVersion.substring(i, n);

        // assert
        Assert.assertEquals("JDF Version doesn't match the Lib Version.", jdfVersion, extractedVersion);
        System.out.println(String.format("JDF Version: %s - Lib Version: %s (OK)", jdfVersion, libVersion));
    }

    @Test
    public void testMinorVersion_1() throws Exception {

        // arrange
        Method method = JDFVersion.class.getDeclaredMethod("getMinorVersion", String.class);
        method.setAccessible(true);

        String version = "2.1.4a.69";
        String expected = "69";

        // act
        String actual = (String) method.invoke(null, version);

        // assert
        Assert.assertEquals("Minor Version is wrong.", expected, actual);
    }

    @Test
    public void testMinorVersion_2() throws Exception {

        // arrange
        Method method = JDFVersion.class.getDeclaredMethod("getMinorVersion", String.class);
        method.setAccessible(true);

        String version = "2.1.5.80-SNAPSHOT";
        String expected = "80";

        // act
        String actual = (String) method.invoke(null, version);

        // assert
        Assert.assertEquals("Minor Version is wrong.", expected, actual);
    }

    @Test
    public void testMajorVersion_1() throws Exception {

        // arrange
        Method method = JDFVersion.class.getDeclaredMethod("getMajorVersion", String.class);
        method.setAccessible(true);

        String version = "2.1.4a.69";
        String expected = "2.1.4a";

        // act
        String actual = (String) method.invoke(null, version);

        // assert
        Assert.assertEquals("Major Version is wrong.", expected, actual);
    }

    @Test
    public void testMajorVersion_2() throws Exception {

        // arrange
        Method method = JDFVersion.class.getDeclaredMethod("getMajorVersion", String.class);
        method.setAccessible(true);

        String version = "2.1.5.80-SNAPSHOT";
        String expected = "2.1.5";

        // act
        String actual = (String) method.invoke(null, version);

        // assert
        Assert.assertEquals("Major Version is wrong.", expected, actual);
    }
}