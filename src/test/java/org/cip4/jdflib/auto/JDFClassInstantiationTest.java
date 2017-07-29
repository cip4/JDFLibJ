/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software
 * copyright (c) 1999-2006, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
/**
 * JDFClassInstantiationTest.java
 *
 * @author Dietrich Mucha
 *
 * Copyright (C) 2005-2006 Heidelberger Druckmaschinen AG. All Rights Reserved.
 */
package org.cip4.jdflib.auto;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileFilter;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.VString;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.DOMException;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFClassInstantiationTest extends JDFTestCaseBase
{
	File dir;

	private void traverseNormalClassesAndInstantiate(File dir, DirectoryVisitor visitor)
	{
		if (!dir.isDirectory())
		{
			throw new IllegalArgumentException("not a directory: " + dir.getName());
		}

		visitor.enterDirectory(dir);
		File[] entries = dir.listFiles(new FileFilter()
		{
			@Override
			public boolean accept(File pathname)
			{
				boolean acceptFile = false;

				String name = pathname.getName();

				if (pathname.isDirectory())
				{
					// ignore classes in directories "auto", "datatypes" and "util"
					acceptFile = !name.equals("auto") && !name.equals("datatypes") && !name.equals("extensions") && !name.equals("util") && !name.equals("validate");
				}
				else
				{
					// ignoreList : abstract classes, classes which are no jdf
					// elements ...
					VString ignoreList = new VString("JDFConstants.java JDFCoreConstants.java JDFDoc.java JDFDocumentBuilder.java "
							+ "JDFException.java JDFParser.java JDFVersions.java JDFAbstractState.java "
							+ "JDFEvaluation.java JDFNodeTerm.java JDFTerm.java JDFEnumerationSpan.java " + "JDFSpan.java JDFSpanBase.java "
							+ "JDFDurationSpan.java JDFIntegerSpan.java JDFNameSpan.java JDFNumberSpan.java " + "JDFOptionSpan.java JDFShapeSpan.java JDFSpanNamedColor.java "
							+ "JDFStringSpan.java JDFTimeSpan.java JDFXYPairSpan.java " + "JDFResourceLink.java " + "JDFPool.java " + "JDFCapsConverter.java", null);

					acceptFile = !ignoreList.contains(name) && name.startsWith("JDF") && name.toLowerCase().endsWith(".java");
				}

				return acceptFile;
			}
		});

		for (File entrie : entries)
		{
			if (entrie.isDirectory())
			{
				traverseNormalClassesAndInstantiate(entrie, visitor);
			}
			else
			{
				visitor.visitFile(entrie);
			}
		}

		visitor.leaveDirectory(dir);
	}

	/**
	 * get the fileName for every class JDFxxx below "./src/org/cip4/jdflib" which is not in ignoreList and extract from it elementName (=xxx) With elementName
	 * instantiate the corresponding class (using jdfRoot.appendElement(elementName) and factory DocumentJDFImpl.java)
	 *
	 * Then createdClass+".java" should be equal to fileName, i.e. the factory DocumentJDFImpl creates a class at the correct point in the hierarchy
	 *
	 * result = fileName.equals(createdClass + ".java") || (fileName.startsWith("JDFAuto") && createdClass.equals(JDFConstants.JDFELEMENT)) ||
	 * fileName.equals(JDFConstants.JDFNODE) || !createdClass.equals(JDFConstants.JDFELEMENT);
	 *
	 */
	@Test
	public void testDirectoryInstantiateVisitor()
	{

		try
		{
			// instantiate all classes starting with JDF
			traverseNormalClassesAndInstantiate(dir, new DirectoryInstantiateVisitor());
		}
		catch (DOMException e)
		{
			fail("DOMException : " + e.getMessage());
		}
	}

	private void traverseAutoClassesAndCheckForCorrespondingNormalClass(File dir, DirectoryVisitor visitor)
	{
		if (!dir.isDirectory())
		{
			throw new IllegalArgumentException("not a directory: " + dir.getName());
		}

		visitor.enterDirectory(dir);
		File[] entries = dir.listFiles(new FileFilter()
		{
			@Override
			public boolean accept(File pathname)
			{
				boolean acceptFile = false;

				String name = pathname.getName();

				if (pathname.isDirectory())
				{
					acceptFile = false;
				}
				else
				{
					acceptFile = name.startsWith("JDFAuto") && name.toLowerCase().endsWith(".java");
				}

				return acceptFile;
			}
		});

		File entry;
		for (File entrie : entries)
		{
			entry = entrie;
			if (entry.isDirectory())
			{
				traverseAutoClassesAndCheckForCorrespondingNormalClass(entry, visitor);
			}
			else
			{
				try
				{
					visitor.visitFile(entry);
				}
				catch (DOMException e)
				{
					System.out.println(e.getLocalizedMessage());
					throw e;
				}
			}
		}

		visitor.leaveDirectory(dir);
	}

	/**
	 *
	 */
	@Test
	public void testAutoClasses()
	{
		// check that every auto class has a corresponding class
		try
		{
			// instantiate all classes starting with JDF
			traverseAutoClassesAndCheckForCorrespondingNormalClass(dir, new AutoClassInstantiateVisitor());
		}
		catch (DOMException e)
		{
			fail("DOMException : " + e.getMessage());
		}
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	@Before
	protected void setUp() throws Exception
	{
		super.setUp();
		dir = new File("./src/main/java/org/cip4/jdflib/auto");
		if (!dir.isDirectory())
			dir = new File("./src/org/cip4/jdflib/auto");

	}

}
