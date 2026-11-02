package org.cip4.jdflib.util.xml;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.UrlUtil;
import org.junit.jupiter.api.Test;

class SecureURIResolverTest extends JDFTestCaseBase
{

	@Test
	void testResolve() throws TransformerException, IOException
	{
		final File rootDir = new File(sm_dirTestDataTemp + "res" + System.currentTimeMillis());
		SecureURIResolver.addRoot(rootDir);
		final SecureURIResolver res = new SecureURIResolver();
		FileUtil.createFile(new File(rootDir, "test.xml"));
		final Source s = res.resolve("test.xml", UrlUtil.fileToUrl(rootDir, false));
		assertNotNull(s);
	}

	@Test
	void testResolveAbs() throws TransformerException, IOException
	{
		final File rootDir = new File(sm_dirTestDataTemp + "res" + System.currentTimeMillis());
		SecureURIResolver.addRoot(rootDir);
		final SecureURIResolver res = new SecureURIResolver();
		final File abs = new File(rootDir, "test.xml");
		FileUtil.createFile(abs);
		final Source s = res.resolve(UrlUtil.fileToUrl(abs, false), UrlUtil.fileToUrl(rootDir, false));
		assertNotNull(s);
	}

	@Test
	void testResolveBad() throws TransformerException, IOException
	{
		final File rootDir = new File(sm_dirTestDataTemp + "res" + System.currentTimeMillis());
		SecureURIResolver.addRoot(rootDir);
		final SecureURIResolver res = new SecureURIResolver();
		FileUtil.createFile(new File(rootDir, "test.xml"));
		assertThrows(TransformerException.class, () -> res.resolve("../test.xml", UrlUtil.fileToUrl(rootDir, false)));

	}

}
