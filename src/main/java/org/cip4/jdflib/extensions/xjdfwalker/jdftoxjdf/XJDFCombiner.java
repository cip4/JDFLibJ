package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import java.util.List;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.ContainerUtil;

public class XJDFCombiner
{

	private final XJDFHelper mainHelper;
	private final XJDFHelper h;
	int typeIndex[];

	/**
	 *
	 * @param mainHelper
	 * @param helper
	 */
	public XJDFCombiner(final XJDFHelper mainHelper, final XJDFHelper helper)
	{
		super();
		this.mainHelper = mainHelper;
		this.h = helper;
		typeIndex = null;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "XJDFCombiner [" + (mainHelper != null ? "mainHelper=" + mainHelper : "") + (h != null ? "h=" + h : "") + "]";
	}

	/**
	 *
	 * @return
	 */
	public XJDFHelper combine()
	{
		typeIndex = combineTypes();
		if (typeIndex != null)
		{
			final Vector<SetHelper> v0 = mainHelper.getSets();
			if (v0 != null)
			{
				for (final SetHelper s : v0)
				{
					prepareSet(s);
				}
			}
			final Vector<SetHelper> v = h.getSets();
			if (v != null)
			{
				for (final SetHelper s : v)
				{
					combineSet(s);
				}
			}
		}
		return mainHelper;
	}

	private void prepareSet(final SetHelper s)
	{
		String pu = s.getProcessUsage();
		if (pu == null)
		{
			final String name = s.getName();
			if (ElementName.MEDIA.equals(name))
			{
				final ResourceHelper r = s.getPartition(0);
				final JDFMedia m = (JDFMedia) (r == null ? null : r.getResource());
				pu = m == null ? null : m.getNonEmpty(AttributeName.MEDIATYPE);
				if (pu != null)
				{
					s.setProcessUsage(pu);
				}
			}
		}
	}

	/**
	 *
	 * @param s
	 */
	void combineSet(final SetHelper s)
	{
		prepareSet(s);
		SetHelper mainSet = getMainSet(s);
		if (mainSet == null)
		{
			mainSet = copySet(s);
		}
		mergeSet(mainSet, s);

	}

	/**
	 *
	 * @param s
	 * @return
	 */
	SetHelper getMainSet(final SetHelper s)
	{
		SetHelper set = mainHelper.getSet(s.getID());
		if (set == null)
		{
			JDFIntegerList cpi = s.getCombinedProcessIndex();
			if (ContainerUtil.getNonEmpty(cpi) == null)
			{
				cpi = typeIndex == null ? null : new JDFIntegerList(typeIndex);
			}
			else
			{
				final JDFIntegerList il = new JDFIntegerList();
				for (int i = 0; i < cpi.size(); i++)
				{
					final int pos = cpi.getInt(i);
					if (pos >= 0 && pos < typeIndex.length)
					{
						il.add(typeIndex[pos]);
					}
				}
				cpi = il;
			}
			set = mainHelper.getSet(s.getName(), s.getUsage(), s.getProcessUsage(), cpi);
		}
		return set;
	}

	void mergeSet(final SetHelper mainSet, final SetHelper s)
	{
		final List<ResourceHelper> v = s.getPartitionList();
		for (final ResourceHelper r : v)
		{
			final ResourceHelper partition = mainSet.getPartition(getPartitions(r));
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
				new ResourceHelper(mainSet.copyHelper(r)).setPartMapVector(getPartitions(r));
			}
		}

	}

	/**
	 *
	 * @param r
	 * @return
	 */
	VJDFAttributeMap getPartitions(final ResourceHelper r)
	{
		VJDFAttributeMap partMapVector = r.getPartMapVector();
		if (VJDFAttributeMap.isEmpty(partMapVector))
		{
			partMapVector = h.getPartMapVector();
			if (VJDFAttributeMap.isEmpty(partMapVector))
			{
				final SetHelper component = h.getSet(ElementName.COMPONENT, EnumUsage.Output);
				if (component != null)
				{
					partMapVector = component.getPartMapVector();
				}
			}
		}
		return partMapVector;
	}

	SetHelper copySet(final SetHelper s)
	{
		final SetHelper copied = new SetHelper(mainHelper.copyHelper(s));
		if (typeIndex != null)
		{
			final JDFIntegerList cpi = getMainCPI(copied);
			copied.setCombinedProcessIndex(cpi);
		}
		copied.removePartitions();
		return copied;
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

	/**
	 *
	 * @return a list of the index into the combined types list for each index in h.types
	 */
	int[] combineTypes()
	{
		final VString types = h.getTypes();
		final VString mainTypes = mainHelper.getTypes();
		final VString oldTypes = mainHelper.getTypes();
		if (!ContainerUtil.isEmpty(oldTypes))
		{
			final int typeList[] = new int[types.size()];
			if (ContainerUtil.isEmpty(oldTypes))
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
						oldTypes.set(pos, JDFConstants.EMPTYSTRING);
					}
					else
					{
						typeList[i++] = mainTypes.size();
						mainTypes.add(t);
						mainHelper.setTypes(mainTypes);
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
