package org.cip4.jdflib.resource.process;

import org.cip4.jdflib.auto.JDFAutoCutBlock.EnumBlockType;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
public class JDFCutBlockTest extends TestCase
{
	/**
	 * tests the separationlist class
	 * 
	 */
	@Test
	public final void testBlockName()
	{
		JDFDoc doc = new JDFDoc("JDF");
		JDFNode root = doc.getJDFRoot();
		JDFResourcePool resPool = root.getCreateResourcePool();
		KElement kElem = resPool.appendResource(ElementName.CUTBLOCK, null,
				null);
		Assert.assertTrue(kElem instanceof JDFCutBlock);
		JDFCutBlock block = (JDFCutBlock) kElem;
		block.setBlockName("Foo");
		block.setBlockSize(new JDFXYPair(10, 10));
		block.setBlockType(EnumBlockType.CutBlock);
		Assert.assertTrue(block.isValid(EnumValidationLevel.Complete));

	}

	// //////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////
}
