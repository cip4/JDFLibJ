/**
 * 
 */
package org.cip4.jdflib.elementwalker;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;

/**
 * 
 * elementwalker class that allows you to traverse a dom tree starting at a given root
 * @author prosirai
 *
 */
public class ElementWalker
{
    protected IWalkerFactory theFactory;
    public ElementWalker(IWalkerFactory _theFactory)
    {
        super();
        this.theFactory = _theFactory;
    }

    /**
     * walk the tree starting at e.
     * 
     * @param e the root element to walk
     * @return n the number of traversed elements
     */
    public int walk(KElement e)
    {
        if(e==null)
            return 0;
        int n=0;

        IWalker w=theFactory.getWalker(e);
        boolean b=true;
        if(w!=null)
        {
            n++;
            b=w.walk(e);
        }
        if(b) // follow kids if still alive
        {
            VElement v=e.getChildElementVector_KElement(null,null,null,true,-1); // do not follow refelements
            final int size = v.size();
            for(int i=0;i<size;i++)
            {
                KElement e2=v.elementAt(i);
                n+=walk(e2);
            }
        }
        return n;
    }
}
