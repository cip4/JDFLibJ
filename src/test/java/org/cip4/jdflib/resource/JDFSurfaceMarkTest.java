package org.cip4.jdflib.resource;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.junit.jupiter.api.Test;

class JDFSurfaceMarkTest extends JDFTestCaseBase
{

	@Test
	void testValid()
	{
		final JDFComponent c = (JDFComponent) JDFNode.createRoot().addResource(ElementName.COMPONENT, EnumUsage.Output);
		final JDFSurfaceMark sc = c.appendSurfaceMark();
		assertTrue(sc.getInvalidAttributes(EnumValidationLevel.Complete, true, 0).contains(AttributeName.FACE));
	}

}
