/**
 * 
 */
package org.cip4.jdflib.elementwalker;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.MimeUtilTest;
import org.cip4.jdflib.util.mime.MimeReader;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class URLExtractorTest extends JDFTestCaseBase
{
	/**
	 * 
	 */
	public void testWalk()
	{
		try
		{
			new MimeUtilTest().testBuildMimePackageDoc();
		}
		catch (Exception x)
		{
			fail("no build");
		}
		final String mimeFile = sm_dirTestDataTemp + File.separator + "testMimePackageDoc.mjm";

		MimeReader mr = new MimeReader(mimeFile);
		JDFDoc d = mr.getBodyPartHelper(0).getJDFDoc();
		assertNotNull(d);
		File dumpDir = new File(sm_dirTestDataTemp + File.separator + "URLExtract");
		URLExtractor ex = new URLExtractor(dumpDir, "http://foo");
		ex.walkTree(d.getJDFRoot(), null);
		String write2String = d.write2String(0);
		assertTrue(write2String.indexOf("http://foo/url2.pdf") > 0);
		assertTrue(FileUtil.getFileInDirectory(dumpDir, new File("url2.pdf")).canRead());
	}
}
