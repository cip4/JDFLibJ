/**
 * 
 */
package org.cip4.jdflib.util.file;

import java.io.File;
import java.io.IOException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.StringUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class RollingFileTest extends JDFTestCaseBase
{

	private RollingFile r;

	/**
	 * 
	 */
	public void testGetNewFile()
	{
		for (int i = 1; i < 100; i++)
		{
			assertEquals(r.getNewFile().getName(), StringUtil.sprintf("dummy%06i.tst", "" + i));
		}
	}

	/**
	 * @throws IOException 
	 * 
	 */
	public void testPreExist() throws IOException
	{
		FileUtil.getFileInDirectory(r, new File("dummy1234.tst")).createNewFile();
		r = new RollingFile(sm_dirTestDataTemp + "RollingFile", "dummy.tst");
		assertEquals(r.getNewFile().getName(), "dummy001235.tst");
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 * @throws Exception
	*/
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		File f = new File(sm_dirTestDataTemp + "RollingFile");
		FileUtil.deleteAll(f);
		f.mkdirs();
		r = new RollingFile(sm_dirTestDataTemp + "RollingFile", "dummy.tst");
	}
}
