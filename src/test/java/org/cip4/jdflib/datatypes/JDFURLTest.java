/*
 * Copyright (C) 2005 Heidelberger Druckmaschinen AG. All Rights Reserved.
 *
 * Project:     Printready
 * Subproject:  InfraStructure
 * Package:     org.cip4.jdflib.datatypes
 * File Name:   JDFURLTest.java
 *
 * Created:     Jan 18, 2005
 *
 * Author:      Michael Kohn
 *
 */
package org.cip4.jdflib.datatypes;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFFileSpec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for JDFURL.
 *
 * @author <a href="mailto:Michael.Kohn@heidelberg.com">Michael Kohn</a>,
 *         Heidelberger Druckmaschinen AG, Tel. 3538
 */
class JDFURLTest
{

	@Test
	public final void testAmpersand()
	{
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		n.setType("Interpreting", true);
		final JDFFileSpec fs = (JDFFileSpec) n.addResource(ElementName.FILESPEC, null, EnumUsage.Input, null, null, null, null);
		final String url = "File:///a&b.pdf";
		fs.setURL(url);
		final String s = d.write2String(2);

		final JDFParser p = new JDFParser();
		final JDFDoc dNew = p.parseString(s);
		final String newUrl = ((JDFFileSpec) dNew.getJDFRoot().getResourcePool().getElement(ElementName.FILESPEC, null, 0)).getURL();

		Assertions.assertEquals(url, newUrl, "url=url");

	}

}
