/**
 * 
 */
package org.cip4.jdflib.node;

import java.util.Collections;
import java.util.Vector;

import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFAudit.EnumAuditType;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.ifaces.ISignalAudit;
import org.cip4.jdflib.pool.JDFAuditPool;

/**
 * @author prosirai
 */
public class AuditToJMF
{
	private final JDFNode theNode;
	private final VJDFAttributeMap vPartMap;
	private final boolean bInlineUpdates;

	/**
	 * @param _theNode the jdf node to parse
	 * @param vParts the job part to search for, if null don't filter
	 * @param inlineUpdates replace all updated audits with the updated version
	 */
	public AuditToJMF(final JDFNode _theNode, final VJDFAttributeMap vParts, final boolean inlineUpdates)
	{
		super();
		this.theNode = _theNode;
		vPartMap = vParts;
		bInlineUpdates = inlineUpdates;
	}

	/**
	 * @param auditType the audit type to use
	 * @return vector of messages to send
	 */
	public VElement getLocalJMFs(final EnumAuditType auditType)
	{
		final JDFAuditPool ap = theNode == null ? null : theNode.getAuditPool();
		final VElement audits = ap == null ? null : ap.getAudits(auditType, null, vPartMap);
		if (bInlineUpdates)
		{
			inlineUpdates(audits);
		}
		if (audits == null || audits.size() == 0)
		{
			return null;
		}

		// we need a type safe list for sort
		final Vector<JDFAudit> va = new Vector<JDFAudit>();
		for (int i = 0; i < audits.size(); i++)
		{
			va.add((JDFAudit) audits.get(i));
		}
		Collections.sort(va, (JDFAudit) audits.elementAt(0));

		final VElement vJMF = new VElement();
		for (int i = 0; i < audits.size(); i++)
		{
			final JDFAudit audit = (JDFAudit) audits.get(i);
			if (audit instanceof ISignalAudit)
			{
				vJMF.add(((ISignalAudit) audit).toSignalJMF());
			}
		}
		return vJMF.size() == 0 ? null : vJMF;
	}

	/**
	 * remove all updated audits from the todo vector
	 * @param audits
	 */
	private void inlineUpdates(final VElement audits)
	{
		if (audits == null)
		{
			return;
		}
		for (int i = audits.size() - 1; i >= 0; i--)
		{
			JDFAudit audit = (JDFAudit) audits.elementAt(i);
			while (audit != null)
			{
				audit = audit.getUpdatedPreviousAudit();
				if (audit != null)
				{
					audits.remove(audit);
					i--; // decrement i, since we removed an element below
				}
			}
		}
	}
}
