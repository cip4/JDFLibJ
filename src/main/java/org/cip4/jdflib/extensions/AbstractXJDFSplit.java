/**
 * 
 */
package org.cip4.jdflib.extensions;

import java.util.Collection;

import org.cip4.jdflib.ifaces.IXJDFSplit;

/**
 * @author rainerprosi
 *
 */
public abstract class AbstractXJDFSplit implements IXJDFSplit
{

	/**
	 * @see org.cip4.jdflib.ifaces.IXJDFSplit#splitXJDF(org.cip4.jdflib.extensions.XJDFHelper)
	 */
	@Override
	public abstract Collection<XJDFHelper> splitXJDF(XJDFHelper root);

	/**
	 * update the Usage of resource links according to the value of types
	 * 
	 * @param xjdf
	 */
	protected void fixInOutLinks(XJDFHelper xjdf)
	{

	}
}
