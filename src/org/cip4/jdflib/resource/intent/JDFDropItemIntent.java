/**
 * ========================================================================== 
 * class JDFDropItemIntent extends JDFResource
 * ==========================================================================
 * @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001 ALL RIGHTS RESERVED
 * @Author: sabjon@topmail.de   using a code generator 
 * Warning! very preliminary test version. 
 * Interface subject to change without prior notice! 
 * Revision history:   ...
 */

package org.cip4.jdflib.resource.intent;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDropItemIntent;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.w3c.dom.DOMException;

public class JDFDropItemIntent extends JDFAutoDropItemIntent
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFDropItemIntent
	 * 
	 * @param ownerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFDropItemIntent(CoreDocumentImpl myOwnerDocument,
			String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDropItemIntent
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFDropItemIntent(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDropItemIntent
	 * 
	 * @param ownerDocument
	 * @param namespaceURI
	 * @param qualifiedName
	 * @param localName
	 * @throws DOMException
	 */
	public JDFDropItemIntent(CoreDocumentImpl myOwnerDocument,
			String myNamespaceURI, String qualifiedName, String myLocalName)
			throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	public String toString()
	{
		return "JDFDropItemIntent[  --> " + super.toString() + " ]";
	}

	/**
	 * return a vector of unknown element nodenames
	 * 
	 * @default getUnknownElements(bIgnorePrivate, 99999999)
	 */
	public Vector getUnknownElements(boolean bIgnorePrivate, int nMax)
	{
		if (bIgnorePrivate)
			bIgnorePrivate = false; // dummy to fool compiler
		return getUnknownPoolElements(JDFElement.EnumPoolType.ProductionIntent,
				nMax);
	}

	/**
	 * Get parent node of 'this' - node DeliveryIntent
	 * 
	 * @return JDFDeliveryIntent: DeliveryIntent node
	 */
	public JDFDeliveryIntent getParentDeliveryIntent()
	{
		return (JDFDeliveryIntent) getParentNode();
	}

	/**
	 * Get of 'this' the value of attribute AdditionalAmount. If not specified,
	 * get the default value of attribute AdditionalAmount, that is specified in
	 * it's parent element (node DeliveryIntent)
	 * 
	 * @return WString: attribute value
	 */
	// TODO AdditionalAmount in super hat anderen return type! deshalt _Integer
	public int getAdditionalAmount_Integer()
	{
		if (hasAttribute(AttributeName.ADDITIONALAMOUNT))
		{
			try
			{
				Integer i = new Integer(super.getAdditionalAmount());
				return i.intValue();
			} catch (NumberFormatException nfe)
			{
				throw new JDFException(
						"DropItemIntent.getAdditionalAmount: Ammount is nit a int value");
			}
		}
		return getParentDeliveryIntent().getAdditionalAmount();
	}

	/**
	 * Get of 'this' the value of element ServiceLevel. If not specified, get
	 * the default value of element ServiceLevel, that is specified in it's
	 * parent element (node DeliveryIntent)
	 * 
	 * @return JDFStringSpan: element value
	 */
	// TODO gibts nicht mehr?
	// public JDFStringSpan getServiceLevel()
	// {
	// if (hasChildElement(ElementName.SERVICELEVEL, JDFConstants.NONAMESPACE))
	// {
	// return super.getServiceLevel();
	// }
	// return getParentDeliveryIntent().getServiceLevel(0);
	// }
}