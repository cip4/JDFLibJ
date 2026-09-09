/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2026 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoQueueEntry.EnumQueueEntryStatus;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFQueue;
import org.cip4.jdflib.jmf.JDFQueueEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WalkQueueEntryTest extends JDFTestCaseBase
{
	@Test
	void testGetElementNames()
	{
		final WalkQueueEntry walkQueueEntry = new WalkQueueEntry();
		Assertions.assertTrue(walkQueueEntry.getElementNames().contains(ElementName.QUEUEENTRY));
	}

	@Test
	void testMatchesAndWalkStatusMap()
	{
		final WalkQueueEntry walkQueueEntry = new WalkQueueEntry();
		walkQueueEntry.setParent(new JDFToXJDF());

		final JDFQueue queue = (JDFQueue) new JDFDoc(ElementName.QUEUE).getRoot();
		Assertions.assertFalse(walkQueueEntry.matches(queue));

		assertMappedStatus(walkQueueEntry, queue, EnumQueueEntryStatus.Running, "InProgress", null, null);
		assertMappedStatus(walkQueueEntry, queue, EnumQueueEntryStatus.Held, "Waiting", "Held", null);
		assertMappedStatus(walkQueueEntry, queue, EnumQueueEntryStatus.Removed, "Completed", null, "Removed");
		assertMappedStatus(walkQueueEntry, queue, EnumQueueEntryStatus.PendingReturn, "Completed", null, "PendingReturn");
	}

	private void assertMappedStatus(final WalkQueueEntry walkQueueEntry, final JDFQueue queue, final EnumQueueEntryStatus srcStatus, final String targetStatus,
			final String targetActivation, final String targetStatusDetails)
	{
		final JDFQueueEntry queueEntry = queue.appendQueueEntry();
		queueEntry.setQueueEntryID("qe_" + srcStatus.getName());
		queueEntry.setQueueEntryStatus(srcStatus);

		Assertions.assertTrue(walkQueueEntry.matches(queueEntry));

		final KElement target = new JDFDoc(ElementName.RESOURCE).getRoot();
		final KElement walked = walkQueueEntry.walk(queueEntry, target);
		Assertions.assertNotNull(walked);
		Assertions.assertEquals(targetStatus, walked.getAttribute(AttributeName.STATUS));

		if (targetActivation == null)
		{
			Assertions.assertNull(walked.getNonEmpty(AttributeName.ACTIVATION));
		}
		else
		{
			Assertions.assertEquals(targetActivation, walked.getAttribute(AttributeName.ACTIVATION));
		}

		if (targetStatusDetails == null)
		{
			Assertions.assertNull(walked.getNonEmpty(AttributeName.STATUSDETAILS));
		}
		else
		{
			Assertions.assertEquals(targetStatusDetails, walked.getAttribute(AttributeName.STATUSDETAILS));
		}
	}

	@Test
	void testRoundTripX()
	{
		final JDFJMF jmf = new JDFDoc(ElementName.JMF).getJMFRoot();
		final JDFQueue queue = jmf.appendResponse(EnumType.QueueStatus).appendQueue();
		final JDFQueueEntry entryRunning = queue.appendQueueEntry();
		entryRunning.setQueueEntryID("qe_running");
		entryRunning.setQueueEntryStatus(EnumQueueEntryStatus.Running);
		final JDFQueueEntry entryHeld = queue.appendQueueEntry();
		entryHeld.setQueueEntryID("qe_held");
		entryHeld.setQueueEntryStatus(EnumQueueEntryStatus.Held);

		final KElement xjmf = new JDFToXJDF().convert(jmf);
		final String xml = xjmf.toXML();
		Assertions.assertTrue(xml.contains("InProgress"));
		Assertions.assertTrue(xml.contains("Waiting"));

		final JDFElement jmfRoundTrip = writeRoundTripX(xjmf, "walkqueueentry", EnumValidationLevel.Complete);
		Assertions.assertNotNull(jmfRoundTrip);
	}
}
