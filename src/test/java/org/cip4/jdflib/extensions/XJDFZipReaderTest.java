package org.cip4.jdflib.extensions;


import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.extensions.xjdfwalker.JDFToXJDFConverterTest;
import org.cip4.jdflib.util.FileUtil;
import org.junit.Test;

public class XJDFZipReaderTest extends JDFTestCaseBase{

	/**
	 * 
	 */
	@Test
	public void testSimpleZip() {
		new JDFToXJDFConverterTest().testMultiNode1();
		XJDFZipReader zr=new XJDFZipReader(new File(sm_dirTestDataTemp+"3files.xjdf.zip"));
		FileUtil.writeFile(zr, new File(sm_dirTestDataTemp+"3files.jdf"));
	}

}
