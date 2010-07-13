/**
 * 
 */
package org.cip4.jdflib.util.file;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class FileSorter
{
	private final File[] list;

	/**
	 * 
	 * @param dir
	 */
	public FileSorter(File dir)
	{
		this(dir == null ? null : dir.listFiles());
	}

	/**
	 * 
	 * @param list
	 */
	public FileSorter(File[] list)
	{
		this.list = list;
	}

	/**
	 * sort this by modification date
	 * @param bNewFirst , if true newest entries are first, else oldest entries
	 * @return sort 
	 */
	public File[] sortLastModified(boolean bNewFirst)
	{
		if (list != null)
			Arrays.sort(list, new LastModifiedComparator(bNewFirst));
		return list;
	}

	/**
	 * get the number of entries in this FileSorter
	 * @return
	 */
	public int size()
	{
		return list == null ? 0 : list.length;
	}

	protected class LastModifiedComparator implements Comparator<File>
	{
		private final int iNewFirst;

		/**
		 * @param newFirst
		 */
		public LastModifiedComparator(boolean newFirst)
		{
			this.iNewFirst = newFirst ? 1 : -1;
		}

		public int compare(File o1, File o2)
		{
			final int n;
			if (o1.lastModified() > o2.lastModified())
			{
				n = -1;
			}
			else if (o1.lastModified() < o2.lastModified())
			{
				n = +1;
			}
			else
			{
				n = 0;
			}
			return n * iNewFirst;
		}
	}
}
