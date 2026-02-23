package org.cip4.jdflib.extensions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.cip4.jdflib.core.XMLDoc;
import org.junit.jupiter.api.Test;

class XSDUtilTest
{

	@Test
	void testgetXSD()
	{
		final XMLDoc localXJDFSchemaDoc = XSDUtil.getLocalXJDFSchemaDoc(null);
		assertNotNull(localXJDFSchemaDoc);
		final XMLDoc docdef = XSDUtil.getLocalXJDFSchemaDoc(BaseXJDFHelper.getEDefaultVersion());
		assertEquals(localXJDFSchemaDoc.toXML(), docdef.toXML());
	}

}
