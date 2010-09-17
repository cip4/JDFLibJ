/**
 * 
 */
package org.cip4.jdflib.validate;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResourceAudit;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * Jul 16, 2010
 */
public class JDFValidatorTest extends JDFTestCaseBase
{
	private JDFNode node;
	private JDFValidator validator;

	/**
	 * 
	 */
	public void testResourceAuditLinks()
	{
		final JDFResource media = node.addResource(ElementName.MEDIA, EnumUsage.Input);
		final JDFResourceAudit ra = node.getCreateAuditPool().addResourceAudit("dummy");
		final JDFResourceLink rl = ra.addNewOldLink(true, media, EnumUsage.Input);
		rl.setActualAmount(42, null);
		validator.setWarning(false);
		assertNotNull(rl.getInvalidAttributes(EnumValidationLevel.Incomplete, false, 0));
		assertTrue(node.isValid(EnumValidationLevel.Incomplete));
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		node = new JDFDoc("JDF").getJDFRoot();
		validator = new JDFValidator();
	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#toString()
	 */
	@Override
	public String toString()
	{
		return super.toString() + "\n" + node;
	}
}
