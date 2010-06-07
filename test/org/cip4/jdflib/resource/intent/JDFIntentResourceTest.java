/**
 * 
 */
package org.cip4.jdflib.resource.intent;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoDeliveryParams.EnumTransfer;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.span.JDFSpanTransfer;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFIntentResourceTest extends JDFTestCaseBase
{
	/**
	 * 
	 */
	public void testGuessActualStatic()
	{
		JDFDeliveryIntent di = (JDFDeliveryIntent) new JDFDoc(ElementName.DELIVERYINTENT).getRoot();
		assertNull(JDFIntentResource.guessActual(di, AttributeName.TRANSFER));
		JDFSpanTransfer transfer = di.appendTransfer();
		assertNull(JDFIntentResource.guessActual(di, AttributeName.TRANSFER));
		transfer.setPreferred(EnumTransfer.BuyerToPrinterDeliver);
		assertEquals(JDFIntentResource.guessActual(di, AttributeName.TRANSFER), EnumTransfer.BuyerToPrinterDeliver.getName());
		transfer.setActual(EnumTransfer.BuyerToPrinterPickup);
		assertEquals(JDFIntentResource.guessActual(di, AttributeName.TRANSFER), EnumTransfer.BuyerToPrinterPickup.getName());
	}
}
