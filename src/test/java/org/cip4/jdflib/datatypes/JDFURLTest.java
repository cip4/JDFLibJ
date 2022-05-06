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
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for JDFURL.
 * 
 * @author <a href="mailto:Michael.Kohn@heidelberg.com">Michael Kohn</a>,
 *         Heidelberger Druckmaschinen AG, Tel. 3538
 * 
 */
public class JDFURLTest {

	@Test
	public final void testAmpersand()
	{
		JDFDoc d = new JDFDoc("JDF");
		JDFNode n = d.getJDFRoot();
		n.setType("Interpreting", true);
		JDFFileSpec fs = (JDFFileSpec) n.addResource(ElementName.FILESPEC,
				null, EnumUsage.Input, null, null, null, null);
		String url = "File:///a&b.pdf";
		fs.setURL(url);
		String s = d.write2String(2);

		JDFParser p = new JDFParser();
		JDFDoc dNew = p.parseString(s);
		String newUrl = ((JDFFileSpec) dNew.getJDFRoot().getResourcePool()
				.getElement(ElementName.FILESPEC, null, 0)).getURL();

		Assert.assertEquals("url=url", url, newUrl);

	}

}
