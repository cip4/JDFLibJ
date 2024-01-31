/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERN
 }ATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 }
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.node;

import org.cip4.jdflib.core.KElement;
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
		_jobID = KElement.isWildCard(jobID) ? null : jobID;
		_jobPartID = KElement.isWildCard(jobPartID) ? null : jobPartID;
		_partMapVector = VJDFAttributeMap.isEmpty(partMapVector) ? null : partMapVector;
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
	@Override
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
	 *        if o is a String, check for match with jobID
	 * 
	 * @return true, if this matches o
	 */
	@Override
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
		boolean b = KElement.isWildCard(niInput._jobID) || ContainerUtil.equals(niInput._jobID, _jobID);
		b = b && ((KElement.isWildCard(niInput._jobPartID) || ContainerUtil.equals(niInput._jobPartID, _jobPartID))
				|| (_jobPartID != null && niInput._jobPartID != null && _jobPartID.startsWith(niInput._jobPartID + ".")));
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
	@Override
	public NodeIdentifier getIdentifier()
	{
		return this;
	}

}