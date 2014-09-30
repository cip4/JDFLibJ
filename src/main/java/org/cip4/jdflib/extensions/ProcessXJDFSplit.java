package org.cip4.jdflib.extensions;

import java.util.Collection;
import java.util.Vector;

import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.StringUtil;

public class ProcessXJDFSplit extends AbstractXJDFSplit
{
	final Vector<VString> groups;

	/**
	 * 
	 */
	public ProcessXJDFSplit()
	{
		super();
		groups = new Vector<VString>();
	}

	/**
	 * 
	 * @see org.cip4.jdflib.extensions.AbstractXJDFSplit#splitXJDF(org.cip4.jdflib.extensions.XJDFHelper)
	 */
	@Override
	public Collection<XJDFHelper> splitXJDF(XJDFHelper root)
	{
		Vector<VString> newTypes = splitTypes(root);
		Vector<XJDFHelper> ret = new Vector<XJDFHelper>();
		if (newTypes != null)
		{
			for (VString types : newTypes)
			{
				XJDFHelper h = root.clone();
				h.setTypes(types);
				h.setJobPartID(root.getJobPartID() + StringUtil.setvString(types));
				fixInOutLinks(h);
				ret.add(h);
			}
			consolidateExchangeResources(ret);
		}
		else
		{
			ret.add(root);
		}
		return ret;
	}

	/**
	 * 
	 * @param root
	 * @return
	 */
	protected Vector<VString> splitTypes(XJDFHelper root)
	{
		Vector<VString> ret = new Vector<VString>();
		VString types = root.getTypes();
		while (types != null && types.size() > 0)
		{
			String first = types.get(0);
			VString overlap = null;
			for (VString group : groups)
			{
				if (group.contains(first))
				{
					overlap = types.getOverlapping(group);
					if (overlap != null)
					{
						break;
					}
				}
			}
			if (overlap == null)
			{
				overlap = new VString();
				overlap.add(first);
			}
			ret.add(overlap);
			types.removeAll(overlap);
		}
		return ret.size() == 0 ? null : ret;
	}

	/**
	 * add a group to split together
	 * @param group
	 */
	public void addGroup(VString group)
	{
		if (group != null && !group.isEmpty())
		{
			groups.add(group);
			ContainerUtil.unify(groups);
		}
	}
}
