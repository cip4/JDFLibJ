package org.cip4.jdflib.resource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.junit.jupiter.api.Test;

class JDFVarnishingParamsTest extends JDFTestCaseBase
{

	@Test
	void testIsPress()
	{
		JDFVarnishingParams vp = (JDFVarnishingParams) JDFElement.createRoot(ElementName.VARNISHINGPARAMS);
		assertFalse(vp.isPressModule());
		vp.setModuleType("printmodule");
		assertTrue(vp.isPressModule());
	}

}
