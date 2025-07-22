package org.cip4.jdflib.resource.process.press;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.resource.process.press.JDFPrintCondition.ePrintQuality;
import org.junit.jupiter.api.Test;

class JDFPrintConditionTest extends JDFTestCaseBase
{

	@Test
	void testEnum()
	{
		final JDFPrintCondition pc = (JDFPrintCondition) JDFElement.createRoot(ElementName.PRINTCONDITION);
		for (final ePrintQuality pq : ePrintQuality.values())
		{
			pc.setPrintQuality(pq);
			assertEquals(pq, pc.getPrintQuality());
			pc.setAttribute("PrintQuality", pq.name().toUpperCase());
			assertEquals(pq, pc.getPrintQuality());
		}
	}

}
