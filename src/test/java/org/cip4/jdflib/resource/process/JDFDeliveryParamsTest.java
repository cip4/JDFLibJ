/**
 * 
 */
package org.cip4.jdflib.resource.process;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.resource.intent.JDFArtDeliveryIntent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFDeliveryParamsTest extends JDFTestCaseBase
{

	/**
	 * 
	 */
	@Test
	void testSetFromArtDelivery()
	{
		JDFArtDeliveryIntent adi = (JDFArtDeliveryIntent) new JDFDoc(ElementName.ARTDELIVERYINTENT).getRoot();
		adi.appendContact();
		adi.appendArtDelivery();
		JDFDeliveryParams dp = (JDFDeliveryParams) new JDFDoc(ElementName.DELIVERYPARAMS).getRoot();
		dp.setFromArtDelivery(adi);
		Assertions.assertNotNull(dp.getContact());
	}
}
