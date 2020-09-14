package org.cip4.jdflib.extensions.xjdfwalker.xjdftojdf;

import static org.junit.Assert.assertEquals;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFContact.EnumContactType;
import org.junit.Ignore;
import org.junit.Test;

public class XJDFToJDFImplTest extends JDFTestCaseBase
{

	/**
	*
	*
	*/
	@Test
	@Ignore
	public void testDropIDContactPartition()
	{
		final XJDFHelper h = new XJDFHelper("j1", null);
		final SetHelper csh = h.getCreateSet(ElementName.CONTACT, EnumUsage.Input);
		for (int i = 0; i < 2; i++)
		{
			final JDFAttributeMap partmap = new JDFAttributeMap(XJDFConstants.ContactType, EnumContactType.Delivery.getName());
			partmap.put(AttributeName.DROPID, "DROP_" + i);
			final ResourceHelper ch = csh.appendPartition(partmap, true);
			final JDFContact co = (JDFContact) ch.getResource();
			co.appendAddress().setStreet("S" + i);
		}

		final XJDFToJDFImpl impl = new XJDFToJDFImpl(null);
		impl.xjdf = h;
		// impl.unpartition();
		assertEquals(2, h.getSets(ElementName.CONTACT, EnumUsage.Input).size());
	}

}
