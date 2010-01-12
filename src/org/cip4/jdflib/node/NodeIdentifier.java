package org.cip4.jdflib.node;

import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.IMatches;
import org.cip4.jdflib.ifaces.INodeIdentifiable;
import org.cip4.jdflib.util.ContainerUtil;

/**
 * class to identify nodes even after parsing, e.g in hashmaps <br/>
 * uses JobID, JobPartID and the partMapVector as identifier
 * 
 */
public final class NodeIdentifier implements IMatches, INodeIdentifiable
{
	private String _jobID;
	private String _jobPartID;
	private VJDFAttributeMap _partMapVector;

	/**
	 * @param jobID
	 * @param jobPartID
	 * @param partMapVector
	 */
	public NodeIdentifier(final String jobID, final String jobPartID, final VJDFAttributeMap partMapVector)
	{
		setTo(jobID, jobPartID, partMapVector);
	}

	/**
	 * @param jobID
	 * @param jobPartID
	 * @param partMapVector
	 */
	public void setTo(final String jobID, final String jobPartID, final VJDFAttributeMap partMapVector)
	{
		_jobID = JDFNode.isWildCard(jobID) ? null : jobID;
		_jobPartID = JDFNode.isWildCard(jobPartID) ? null : jobPartID;
		_partMapVector = partMapVector;
	}

	/**
	 * 
	 */
	public NodeIdentifier()
	{
		setTo(null, null, null);

	}

	/**
	 * sets a NodeIdentifier to a given JDF node identifier uses the AncestorPool or NodeInfo or output resource in that sequence to determine the partmap
	 * 
	 * @param n
	 */
	public void setIdentifier(final NodeIdentifier n)
	{
		if (n == null)
		{
			setTo(null, null, null);
		}
		else
		{
			setTo(n._jobID, n._jobPartID, n._partMapVector);
		}
	}

	/**
	 * sets a NodeIdentifier to a given JDF node
	 * 
	 * @param qe
	 */
	public void setTo(final INodeIdentifiable qe)
	{
		if (qe == null)
		{
			setTo(null, null, null);
		}
		else
		{
			setIdentifier(qe.getIdentifier());
		}
	}

	/**
	 * creates a NodeIdentifier from a given QueueEntry
	 * 
	 * @param ni the INodeIdentifiable to set this to
	 */
	public NodeIdentifier(final INodeIdentifiable ni)
	{
		this();
		setTo(ni);
	}

	/**
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object inObject)
	{
		if (!(inObject instanceof INodeIdentifiable))
		{
			return false;
		}
		final NodeIdentifier mt = ((INodeIdentifiable) inObject).getIdentifier();
		boolean b = ContainerUtil.equals(mt._jobID, _jobID);
		b = b && ContainerUtil.equals(mt._jobPartID, _jobPartID);
		return b && ContainerUtil.equals(mt._partMapVector, _partMapVector);

	}

	/**
	 * return true if the nodeIdentifier matches this, i.e. if all parameters match or o has matching wildcards, or o==null
	 * 
	 * @see IMatches
	 * @param o the nodeidentifier that this should match<br/>
	 * if o is a String, check for match with jobID
	 * 
	 * @return true, if this matches o
	 */
	public boolean matches(final Object o)
	{
		if (o == null)
		{
			return true;
		}
		if (o instanceof String)
		{
			return ContainerUtil.equals(_jobID, o);
		}

		if (!(o instanceof INodeIdentifiable))
		{
			return false;
		}
		final NodeIdentifier niInput = ((INodeIdentifiable) o).getIdentifier();
		boolean b = JDFNode.isWildCard(niInput._jobID) || ContainerUtil.equals(niInput._jobID, _jobID);
		b = b
				&& ((JDFNode.isWildCard(niInput._jobPartID) || ContainerUtil.equals(niInput._jobPartID, _jobPartID)) || (_jobPartID != null && niInput._jobPartID != null && _jobPartID.startsWith(niInput._jobPartID
						+ ".")));
		return b && ((_partMapVector == null) || (_partMapVector != null && _partMapVector.overlapsMap(niInput._partMapVector)));
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return (_jobID == null ? 0 : _jobID.hashCode()) + (_jobPartID == null ? 0 : _jobPartID.hashCode()) + (_partMapVector == null ? 0 : _partMapVector.hashCode());
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NodeIdentifier :" + _jobID + " " + _jobPartID + "\n" + _partMapVector;
	}

	/**
	 * @return
	 */
	public String getJobID()
	{
		return _jobID;
	}

	/**
	 * @return
	 */
	public String getJobPartID()
	{
		return _jobPartID;
	}

	/**
	 * @return
	 */
	public VJDFAttributeMap getPartMapVector()
	{
		return _partMapVector;
	}

	/**
	 * formalism so zhat we can use this as a {@link INodeIdentifiable}
	 * 
	 * @see org.cip4.jdflib.ifaces.INodeIdentifiable#getIdentifier()
	 */
	public NodeIdentifier getIdentifier()
	{
		return this;
	}

}