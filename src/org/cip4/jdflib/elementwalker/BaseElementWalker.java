/**
 * 
 */
package org.cip4.jdflib.elementwalker;

import java.lang.reflect.Constructor;

import org.cip4.jdflib.util.StringUtil;

/**
 * 
 * elementwalker class that allows you to traverse a dom tree starting at a given root also handles the construction of
 * the walker classes by name, just make sure that your walker subclasses match the naming convention <name>$Walk, e.g.
 * if your class is called FixVersion, the subclasse must be call
 * 
 * @author prosirai
 * 
 */
public class BaseElementWalker extends ElementWalker
{
	public BaseElementWalker(BaseWalkerFactory _theFactory)
	{
		super(_theFactory);
		String name = this.getClass().getSimpleName();
		constructWalkers(name + "$Walk");
	}

	/**
	 * construct all walkers confirming to the naming convention public <classname>$Walk<xxx>
	 * 
	 * @param classPrefix
	 */
	protected void constructWalkers(String classPrefix)
	{
		Class[] cs = this.getClass().getDeclaredClasses();
		for (int i = 0; i < cs.length; i++)
		{
			String s = cs[i].getName();
			s = StringUtil.token(s, -1, ".");
			if (s.startsWith(classPrefix))
			{
				try
				{
					Constructor con = cs[i].getDeclaredConstructor(new Class[] { this.getClass() });
					con.newInstance(new Object[] { this });

				}
				catch (Exception x)
				{
					System.out.print("" + x);
				}
			}
		}
	}

	protected BaseWalkerFactory getFactory()
	{
		return (BaseWalkerFactory) theFactory;
	}

}
