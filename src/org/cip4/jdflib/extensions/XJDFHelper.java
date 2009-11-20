/**
 * 
 */
package org.cip4.jdflib.extensions;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class XJDFHelper
{
	/**
	 * @param xjdf
	 */
	public XJDFHelper(KElement xjdf)
	{
		super();
		this.theXJDF = xjdf;
	}

	/**
	 * @return the vector of parametersets and resourcesets
	 */
	public VElement getSets()
	{
		VElement v = new VElement();
		KElement e = theXJDF.getFirstChildElement();
		while (e != null)
		{
			if (e.getLocalName().endsWith("Set"))
				v.add(e);
			e = e.getNextSiblingElement();
		}
		return v.size() == 0 ? null : v;

	}

	protected KElement theXJDF;

}
