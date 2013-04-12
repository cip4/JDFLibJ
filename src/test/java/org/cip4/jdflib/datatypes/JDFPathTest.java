/*
 * Copyright (C) 2005 Heidelberger Druckmaschinen AG. All Rights Reserved.
 *
 * Project:     Printready
 * Subproject:  InfraStructure
 * Package:     org.cip4.jdflib.datatypes
 * File Name:   JDFPathTest.java
 *
 * Created:     Jan 18, 2005
 *
 * Author:      Michael Kohn
 *
 */
package org.cip4.jdflib.datatypes;

import java.awt.geom.AffineTransform;

import junit.framework.TestCase;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.process.JDFContentObject;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for JDFPath.
 * 
 * @author <a href="mailto:Michael.Kohn@heidelberg.com">Michael Kohn</a>,
 *         Heidelberger Druckmaschinen AG, Tel. 3538
 * 
 */
public class JDFPathTest extends JDFTestCaseBase {
	private static final String PACKAGEDATA = "TestClipPfadCorr.jdf";
	// "PackageData-Top.jdf";

	private String m_strPath;

	/*
	 * @see TestCase#setUp()
	 */
	@Override
	public void setUp() throws Exception {
		super.setUp();

		JDFParser p = new JDFParser();
		JDFDoc jdfDoc = p.parseFile(sm_dirTestData + PACKAGEDATA);

		JDFNode root = (JDFNode) jdfDoc.getRoot();
		JDFContentObject contObj = (JDFContentObject) root.getChildByTagName(
				"ContentObject", "", 0, null, false, true);

		m_strPath = contObj.getSourceClipPath();
	}

	@Test
	public final void testTransform() {
		JDFPath path = new JDFPath(m_strPath);

		path.transform(new AffineTransform());

		System.out
				.println("JDFPathTest.java: The following two paths should be approximately (6-7 significant digits) equal:");
		System.out.println("Original    : " + m_strPath);
		System.out.println("Transformed : " + path.getPath());
		// Assert.assertEquals(
		// "The path should be the same after applying the identity transformation"
		// ,
		// m_strPath, path.getPath() );
	}

}
