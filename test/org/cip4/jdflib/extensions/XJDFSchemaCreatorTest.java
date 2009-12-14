/**
 * 
 */
package org.cip4.jdflib.extensions;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.FileUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XJDFSchemaCreatorTest extends JDFTestCaseBase
{

	/**
	 * create a jdf 2.0 schema based on the jdf library
	 */
	public void testCreate()
	{
		File baseDir = new File("./src/org/cip4/jdflib");
		System.out.println(baseDir.getAbsolutePath());
		XJDFSchemaCreator sc = new XJDFSchemaCreator(baseDir, FileUtil.getFileInDirectory(new File(sm_dirTestDataTemp), new File("xjdf.xsd")));

		sc.create();
	}

}
