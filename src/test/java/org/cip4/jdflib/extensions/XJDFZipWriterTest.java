package org.cip4.jdflib.extensions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.zip.ZipReader;
import org.junit.Test;

public class XJDFZipWriterTest extends JDFTestCaseBase
{

	/**
	 *
	 * @throws IOException
	 */
	@Test
	public void testSimpleXJDF() throws IOException
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final XJDFZipWriter w = new XJDFZipWriter();
		w.addXJDF(h);
		final ByteArrayIOStream ios = new ByteArrayIOStream();
		w.writeStream(ios);
		assertTrue(ios.size() > 13);
	}

	/**
	 *
	 * @throws IOException
	 */
	@Test
	public void testEnsureXJMF() throws IOException
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final XJDFZipWriter w = new XJDFZipWriter();
		w.addXJDF(h);
		final XJMFHelper jmf = w.ensureXJMF();
		assertEquals("xjdf/j1.00.xjdf", jmf.getXPathValue("CommandSubmitQueueEntry/QueueSubmissionParams/@URL"));
	}

	/**
	 *
	 * @throws IOException
	 */
	@Test
	public void testAux() throws IOException
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final XJDFZipWriter w = new XJDFZipWriter();
		w.addXJDF(h);
		w.addAux("pdf/foo.pdf", FileUtil.getBufferedInputStream(new File(sm_dirTestData + "page.pdf")));
		final File file = new File(sm_dirTestDataTemp + "testaux.zip");
		file.delete();
		FileUtil.writeFile(w, file);
		final ZipReader zr = new ZipReader(file);
		assertNotNull(zr.getEntry("pdf/foo.pdf"));
		assertNotNull(zr.getInputStream());

	}

	/**
	 *
	 * @throws IOException
	 */
	@Test
	public void testSimpleXJDFFile() throws IOException
	{
		final XJDFHelper h = new XJDFHelper("j1", null, null);
		final XJDFZipWriter w = new XJDFZipWriter();
		w.addXJDF(h);
		final File file = new File(sm_dirTestDataTemp + "testx.zip");
		file.delete();
		FileUtil.writeFile(w, file);
		assertTrue(file.canRead());
		final ZipReader zr = new ZipReader(file);
		zr.getNextEntry();
		assertNotNull(zr.getXMLDoc());
	}

}
