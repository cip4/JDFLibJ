/**
 * 
 */
package org.cip4.jdflib.util.file;

import java.io.File;
import java.io.IOException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.jdflib.util.ThreadUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class FileSorterTest extends JDFTestCaseBase
{

	/**
	 * @throws IOException 
	 * 
	 */
	public void testSortLastModified() throws IOException
	{
		File dir = new File(sm_dirTestDataTemp + "filesort");
		FileUtil.deleteAll(dir);
		dir.mkdirs();
		VString names = new VString("a c g b e", null);
		for (String s : names)
		{
			FileUtil.getFileInDirectory(dir, new File(s)).createNewFile();
			ThreadUtil.sleep(2000);
		}
		FileSorter fs = new FileSorter(dir);
		File[] list = fs.sortLastModified(false);
		for (int i = 0; i < names.size(); i++)
		{
			assertEquals(names.get(i), list[i].getName());
		}
		list = fs.sortLastModified(true);
		for (int i = 0; i < names.size(); i++)
		{
			assertEquals(names.get(names.size() - i - 1), list[i].getName());
		}
	}
}
