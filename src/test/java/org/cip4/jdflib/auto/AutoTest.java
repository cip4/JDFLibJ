package org.cip4.jdflib.auto;

import java.util.Vector;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFAdhesiveBindingParams;
import org.cip4.jdflib.resource.JDFMarkObject;
import org.cip4.jdflib.resource.process.JDFLayout;
import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
public class AutoTest extends TestCase {
	// Beware!
	// These tests are for checking versioning and JDFValidator internal details
	@Test
	public void testElementVersion() {
		Vector vPrerelease = null;
		Vector vOptional = null;
		Vector vDeprecated = null;

		JDFDoc jdfDoc = new JDFDoc("JDF");
		JDFNode root = jdfDoc.getJDFRoot();

		// check AdhesiveBindingParams/GlueApplication
		//
		root.setVersion(JDFElement.EnumVersion.Version_1_3);
		JDFAdhesiveBindingParams adhesiveBindingParam = (JDFAdhesiveBindingParams) root.addResource(ElementName.ADHESIVEBINDINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		vDeprecated = adhesiveBindingParam.getTheElementInfo().deprecatedElements();
		adhesiveBindingParam.appendGlueApplication();
		vDeprecated = adhesiveBindingParam.getDeprecatedElements(99999999);
		Assert.assertTrue(vDeprecated.contains(ElementName.GLUEAPPLICATION));

		root.setVersion(JDFElement.EnumVersion.Version_1_0);
		vDeprecated = adhesiveBindingParam.getDeprecatedElements(99999999);
		Assert.assertEquals(0, vDeprecated.size());

		// check MarkObject/DeviceMark
		//
		root.setVersion(JDFElement.EnumVersion.Version_1_0);
		JDFLayout layout = (JDFLayout) root.addResource(ElementName.LAYOUT, null, EnumUsage.Input, null, null, null, null);
		JDFMarkObject markObject = layout.appendMarkObject();
		markObject.appendDeviceMark();
		vPrerelease = markObject.getPrereleaseElements(99999999);
		Assert.assertTrue(vPrerelease.contains(ElementName.DEVICEMARK));

		root.setVersion(JDFElement.EnumVersion.Version_1_1);
		vOptional = markObject.getTheElementInfo().optionalElements();
		Assert.assertTrue(vOptional.contains(ElementName.DEVICEMARK));

		root.setVersion(JDFElement.EnumVersion.Version_1_2);
		vOptional = markObject.getTheElementInfo().optionalElements();
		Assert.assertTrue(vOptional.contains(ElementName.DEVICEMARK));

		// DeviceMark is again allowed in 1.4
		// root.setVersion(JDFElement.EnumVersion.Version_1_3);
		// vDeprecated = markObject.getTheElementInfo().deprecatedElements();
		// Assert.assertTrue(vDeprecated.contains(ElementName.DEVICEMARK));
	}

}
