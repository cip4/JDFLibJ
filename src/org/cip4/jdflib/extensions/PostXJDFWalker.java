/**
 * 
 */
package org.cip4.jdflib.extensions;

import java.util.zip.DataFormatException;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
class PostXJDFWalker extends BaseElementWalker
{
	/**
	 * if true merge stripping and layout
	 */
	boolean mergeLayout = true;
	KElement newRoot;

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkElement extends BaseWalker
	{
		@SuppressWarnings("synthetic-access")
		public WalkElement()
		{
			super(getFactory());
		}

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement xjdf, final KElement dummy)
		{
			return xjdf;
		}
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkStrippingParams extends WalkElement
	{
		/**
		 * 
		 */
		public WalkStrippingParams()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck.getLocalName().equals(ElementName.STRIPPINGPARAMS);
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		*/
		@Override
		public KElement walk(KElement xjdf, KElement dummy)
		{
			if (!mergeLayout)
			{
				return xjdf; // nuff done
			}
			//TODO multiple lower level stripparams partitions
			KElement newLayout = newRoot.getXPathElement("ParameterSet[@Name=\"Layout\"]");
			if (newLayout != null)
			{
				VJDFAttributeMap vmap = new PartitionHelper(xjdf.getParentNode_KElement()).getPartMapVector();
				PartitionHelper layoutPartitionH = new SetHelper(newLayout).getPartition(vmap);
				KElement layoutPartition = layoutPartitionH == null ? null : layoutPartitionH.getPartition();
				if (layoutPartition != null)
				{
					layoutPartition = layoutPartition.getCreateElement("Layout");
					layoutPartition.mergeElement(xjdf, false);
				}
			}
			return null; // stop after merging

		}

	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkStrippingSet extends WalkElement
	{
		/**
		 * 
		 */
		public WalkStrippingSet()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck.getLocalName().equals("ParameterSet") && ElementName.STRIPPINGPARAMS.equals(toCheck.getAttribute(AttributeName.NAME));
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		*/
		@Override
		public KElement walk(KElement xjdf, KElement dummy)
		{
			if (mergeLayout)
			{
				xjdf.deleteNode();
			}

			return mergeLayout ? null : xjdf; // stop after merging

		}

	}

	/**
	 * @param newRoot 
	 *  
	 */
	public PostXJDFWalker(KElement newRoot)
	{
		super(new BaseWalkerFactory());
		this.newRoot = newRoot;
	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkXJDF extends WalkElement
	{
		/**
		 * 
		 */
		public WalkXJDF()
		{
			super();
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			return toCheck.getLocalName().equals(XJDF20.rootName);
		}

		/**
		 * @see org.cip4.jdflib.extensions.XJDF20.WalkResource#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param xjdf
		 * @param dummy
		 * @return
		*/
		@Override
		public KElement walk(KElement xjdf, KElement dummy)
		{
			reorderSets(xjdf);
			return xjdf;
		}

		/**
		 * @param xjdf 
		 * 
		 */
		private void reorderSets(KElement xjdf)
		{
			VElement v = new XJDFHelper(xjdf).getSets();
			if (v == null)
				return; // nothing to do

			int n = v.size();
			int j = 0;
			while (n > 0)
			{
				for (int i = 0; i < v.size(); i++)
				{
					KElement e = v.get(i);
					if (e != null)
					{
						JDFIntegerList lcpi = null;
						String cpi = e.getAttribute(AttributeName.COMBINEDPROCESSINDEX, null, null);
						try
						{
							lcpi = cpi == null ? null : new JDFIntegerList(cpi);
						}
						catch (final DataFormatException dfe)
						{
							//nop
						}

						// have a matching cpi entry - place set here and remove from further tests
						if (lcpi == null || lcpi.contains(j))
						{
							v.set(i, null);
							e.removeAttribute(AttributeName.COMBINEDPROCESSINDEX);
							n--;
						}
						else
						{
							xjdf.moveElement(e, null);
						}
					}
				}
				j++;
			}
			//TODO treat outputs backwards...
		}

	}

}
