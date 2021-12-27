package org.cip4.jdflib.util.logging;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.Test;

public class LogConfiguratorTest extends JDFTestCaseBase
{

	@Test
	public void testConfigureLog()
	{
		LogConfigurator.configureLog(sm_dirTestDataTemp + "log", "testlog.log");
		log.info("test");
	}

}
