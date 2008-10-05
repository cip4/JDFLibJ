/**
 * 
 */
package org.cip4.jdflib.elementwalker;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;

/**
 * 
 * elementwalker class that allows you to traverse a dom tree starting at a given root
 * 
 * @author prosirai
 * 
 */
public class ElementWalker
{
	protected IWalkerFactory theFactory;

	/**
	 * @param _theFactory used to find the individual instances for the children
	 */
	public ElementWalker(IWalkerFactory _theFactory)
	{
		super();
		this.theFactory = _theFactory;
	}

	/**
	 * walk the tree starting at e.
	 * 
	 * @param e the root element to walk
	 * @param trackElem TODO
	 * @return n the number of traversed elements
	 */
	public int walkTree(KElement e, KElement trackElem)
	{
		if (e == null)
			return 0;
		int n = 0;

		IWalker w = theFactory.getWalker(e);
		KElement b = null;
		if (w != null)
		{
			n++;
			b = w.walk(e, trackElem);
		}
		if (b != null) // follow kids if still alive or no walker found
		{
			// do not follow refelements
			VElement v = e.getChildElementVector_KElement(null, null, null, true, -1);
			final int size = v.size();
			for (int i = 0; i < size; i++)
			{
				KElement e2 = v.elementAt(i);
				n += walkTree(e2, b);
			}
		}
		return n;
	}

}
