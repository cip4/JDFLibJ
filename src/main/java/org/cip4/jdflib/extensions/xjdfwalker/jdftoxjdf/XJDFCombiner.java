package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.Vector;

import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;

class XJDFCombiner
{

	private final XJDFHelper mainHelper;
	int typeIndex[];

	XJDFCombiner(final XJDFHelper helper)
	{
		super();
		mainHelper = helper;
		typeIndex = null;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "XJDFCombiner [" + (mainHelper != null ? "mainHelper=" + mainHelper : "") + "]";
	}

	public void combine(final XJDFHelper h)
	{
		typeIndex = combineTypes(h);
		if (typeIndex != null)
		{
			final Vector<SetHelper> v = h.getSets();
			if (v != null)
			{
				for (final SetHelper s : v)
				{
					combineSet(s);
				}
			}
		}

	}

	private void combineSet(final SetHelper s)
	{
		final SetHelper mainSet = mainHelper.getSet(s.getID());
		if (mainSet == null)
		{
			copySet(s);
		}
		else
		{
			mergeSet(mainSet, s);
		}

	}

	private void mergeSet(final SetHelper mainSet, final SetHelper s)
	{
		final Vector<ResourceHelper> v = s.getPartitions();
		for (final ResourceHelper r : v)
		{
			final ResourceHelper partition = mainSet.getPartition(r.getPartMapVector());
			if (partition != null)
			{
				partition.getRoot().setAttributes(r.getRoot());
				final KElement r2 = r.getResource();
				if (r2 != null)
				{
					partition.getCreateResource().mergeElement(r2, false);
				}
			}
			else
			{
				mainSet.copyHelper(r);
			}
		}

	}

	void copySet(final SetHelper s)
	{
		final SetHelper copied = new SetHelper(mainHelper.copyHelper(s));
		if (typeIndex != null)
		{
			final JDFIntegerList cpi = getMainCPI(copied);
			copied.setCombinedProcessIndex(cpi);
		}
	}

	protected JDFIntegerList getMainCPI(final SetHelper copied)
	{
		JDFIntegerList cpi = copied.getCombinedProcessIndex();
		if (cpi != null)
		{
			for (int i = 0; i < cpi.size(); i++)
			{
				final int index = cpi.getInt(i);
				if (index >= 0 && index < typeIndex.length)
				{
					cpi.set(i, typeIndex[index]);
				}
			}
		}
		else
		{
			cpi = new JDFIntegerList(typeIndex);
		}
		return cpi;
	}

	int[] combineTypes(final XJDFHelper h)
	{
		final VString types = h.getTypes();
		final VString oldTypes = mainHelper.getTypes();
		if (types != null && types.size() > 0)
		{
			final int typeList[] = new int[types.size()];
			if (oldTypes == null || oldTypes.size() == 0)
			{
				for (int i = 0; i < types.size(); i++)
				{
					typeList[i] = i;
				}
			}
			else
			{
				int i = 0;
				for (final String t : types)
				{
					final int pos = oldTypes.indexOf(t);
					if (pos >= 0)
					{
						typeList[i++] = pos;
					}
					else
					{
						typeList[i++] = oldTypes.size();
						oldTypes.add(t);
						mainHelper.setTypes(oldTypes);
					}
				}
			}
			return typeList;
		}
		else
		{
			return null;
		}
	}
}
