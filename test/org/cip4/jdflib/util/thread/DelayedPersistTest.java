/**
 * 
 */
package org.cip4.jdflib.util.thread;

import java.io.File;
import java.io.IOException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.util.ThreadUtil;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class DelayedPersistTest extends JDFTestCaseBase
{
	File file;

	/**
	 * 
	 */
	public void testPersist()
	{
		file.delete();
		assertFalse(file.exists());
		DelayedPersist.getDelayedPersist().queue(new TestPersist(), 1555);
		assertFalse(file.exists());
		ThreadUtil.sleep(15000);
		assertTrue(file.exists());
	}

	/**
	 * 
	  * @author Rainer Prosi, Heidelberger Druckmaschinen *
	 */
	public class TestPersist implements IPersistable
	{

		/**
		 * @see org.cip4.jdflib.util.thread.IPersistable#persist()
		 * @return
		*/
		public boolean persist()
		{
			try
			{
				return file.createNewFile();
			}
			catch (IOException x)
			{
				return false;
			}
		}

	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 * @throws Exception
	*/
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		file = new File(sm_dirTestDataTemp + "TestPersist.txt");
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#toString()
	 * @return
	*/
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return super.toString();
	}
}
