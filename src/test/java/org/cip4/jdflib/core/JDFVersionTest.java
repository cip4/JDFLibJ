package org.cip4.jdflib.core;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for JDFVersion.
 */
public class JDFVersionTest {

    @Test
     public void testArtifactId() {

        // arrange
        String expected = "JDFLibJ";

        // act
        String actual = JDFVersion.LIB_ARTIFACT_ID;

        // assert
        Assert.assertEquals("ArtifactID number is wrong.", expected, actual);
        System.out.println("JDFLibJ ArtifactID: " + actual);
    }

    @Test
    public void testName() {

        // arrange
        String expected = "CIP4 JDF Writer Java";

        // act
        String actual = JDFVersion.LIB_NAME;

        // assert
        Assert.assertEquals("Name number is wrong.", expected, actual);
        System.out.println("JDFLibJ Name: " + actual);
    }

    @Test
    public void testReleaseDate() {

        // arrange

        // act
        String actual = JDFVersion.LIB_RELEASE_DATE;

        // assert
        Assert.assertTrue("ReleaseDate is wrong.", actual.matches("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})"));
        System.out.println("JDFLibJ Release Date: " + actual);
    }

    @Test
    public void testVersion() {

        // arrange

        // act
        String actual = JDFVersion.LIB_VERSION;

        // assert
        Assert.assertFalse("Version is wrong.", StringUtils.isEmpty(actual));
        System.out.println("JDFLibJ Version: " + actual);
    }
}