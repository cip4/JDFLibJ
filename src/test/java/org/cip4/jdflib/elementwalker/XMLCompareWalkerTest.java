package org.cip4.jdflib.elementwalker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.util.MyPair;
import org.junit.Test;

public class XMLCompareWalkerTest extends JDFTestCaseBase
{

	@Test
	public void testSimple()
	{
		final KElement e1 = KElement.createRoot("a", null);
		final KElement e2 = KElement.createRoot("a", null);
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	public void testSimpleChild()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "b1");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "b2");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		final Map<String, MyPair<String, String>> m = w.compare();
		assertEquals(1, m.size());
		assertEquals(new MyPair<>("b1", "b2"), m.get("/a/@b"));
	}

	@Test
	public void testSimpleIgnore()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "b1");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "b2");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		w.addIgnore("b", true);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	public void testSimpleIgnoreValue()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "b1");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		w.addIgnore("b", true);
		assertFalse(w.compare().isEmpty());
		w.addIgnore("b", false);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	public void testSimpleChildSame()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "b1");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "b1");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	public void testSimpleChildPrecision()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "1.123");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "1.1234");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		w.setPrecision(0);
		assertFalse(w.compare().isEmpty());
		w.setPrecision(0.001);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	public void testSimpleChildPrecisionIntDouble()
	{
		final KElement e1 = KElement.createRoot("a", null);
		e1.setAttribute("b", "1");
		final KElement e2 = KElement.createRoot("a", null);
		e2.setAttribute("b", "1.000");
		final XMLCompareWalker w = new XMLCompareWalker(e1, e2);
		w.setPrecision(0);
		assertTrue(w.compare().isEmpty());
	}

	@Test
	public void testComplexSame()
	{
		final File[] fs = new File(sm_dirTestData + "SampleFiles").listFiles();
		for (final File f : fs)
		{
			final JDFDoc d = JDFDoc.parseFile(f);
			final XMLCompareWalker w = new XMLCompareWalker(d.getRoot(), d.getRoot());
			assertTrue(w.compare().isEmpty());
		}
	}

}
