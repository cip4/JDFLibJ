package org.cip4.jdflib.util.file;

import java.io.File;
import java.io.IOException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.FileUtil;

/**
 * TODO Please insert comment!
 * @author rainer prosi
 * @date July 10, 2012
 */
public class RollingBackupDirectoryTest extends JDFTestCaseBase
{
	File theDir;

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		theDir = new File(sm_dirTestDataTemp + "RollingBackupDir");
		FileUtil.deleteAll(theDir);
	}

	/**
	 * @throws IOException 
	 * 
	 *  
	 */
	public void testCreateSimple() throws IOException
	{
		RollingBackupDirectory dir = new RollingBackupDirectory(theDir, 42, "test.txt");
		for (int i = 0; i < 155; i++)
		{
			File newFile = dir.getNewFile();
			assertTrue(newFile.exists());
		}
	}

	/**
	 * @throws IOException 
	 * 
	 *  
	 */
	public void testCreateSimpleNoExt() throws IOException
	{
		RollingBackupDirectory dir = new RollingBackupDirectory(theDir, 42, "test");
		for (int i = 0; i < 55; i++)
		{
			File newFile = dir.getNewFile();
			assertTrue(newFile.exists());
		}
	}

	/**
	 * @throws IOException 
	 * 
	 *  
	 */
	public void testCreateNewExt() throws IOException
	{
		RollingBackupDirectory dir = new RollingBackupDirectory(theDir, 42, "test");
		for (int i = 0; i < 155; i++)
		{
			String ext = ".txt";
			if (i % 3 == 1)
				ext = "xml";
			else if (i % 3 == 2)
				ext = ".foo";
			File newFile = dir.getNewFileWithExt(ext);
			assertTrue(newFile.exists());
		}
	}

	/**
	 * @throws IOException 
	 * 
	 *  
	 */
	public void testCreateNewExtWitxExt() throws IOException
	{
		RollingBackupDirectory dir = new RollingBackupDirectory(theDir, 42, "test.txt");
		for (int i = 0; i < 155; i++)
		{
			String ext = ".txt";
			if (i % 3 == 1)
				ext = "xml";
			else if (i % 3 == 2)
				ext = ".foo";
			File newFile = dir.getNewFileWithExt(ext);
			assertTrue(newFile.exists());
		}
	}
}
