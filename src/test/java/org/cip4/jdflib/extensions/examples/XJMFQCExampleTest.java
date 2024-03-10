/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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
package org.cip4.jdflib.extensions.examples;

import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.datatypes.JDFLabColor;
import org.cip4.jdflib.datatypes.JDFTransferFunction;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.extensions.MessageHelper;
import org.cip4.jdflib.extensions.MessageResourceHelper;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJMFHelper;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.jmf.JDFResourceQuParams;
import org.cip4.jdflib.jmf.JDFSubscription;
import org.cip4.jdflib.jmf.JMFBuilderFactory;
import org.cip4.jdflib.resource.process.JDFQualityControlResult;
import org.cip4.jdflib.util.JDFDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJMFQCExampleTest extends ExampleTest
{

	private static final String SPECTRUM = "Spectrum";
	public static final String PATCH = "Patch";
	public static final String COLOR_MEASUREMENT = "ColorMeasurement";

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	@BeforeEach
	void setUp() throws Exception
	{
		super.setUp();
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentName(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setAgentVersion(null);
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID(null);
		KElement.setLongID(false);
	}

	/**
	*
	*
	*/
	@Test
	public final void testQualityControlSubscription()
	{
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper s = xjmfHelper.appendMessage(EnumFamily.Query, EnumType.Resource);
		s.getHeader().setID("QC1");
		s.getHeader().setAttribute(AttributeName.TIME, new JDFDate().setTime(17, 0, 0).getDateTimeISO());
		final JDFResourceQuParams rqp = (JDFResourceQuParams) s.appendElement(ElementName.RESOURCEQUPARAMS);
		rqp.setResourceName(ElementName.QUALITYCONTROLPARAMS);
		rqp.setAttribute(AttributeName.SCOPE, "Job");
		final JDFSubscription sub = s.subscribe("http://MIS:1234/xjmfurl");
		sub.setRepeatTime(120);
		xjmfHelper.cleanUp();
		setSnippet(xjmfHelper, true);
		writeRoundTripX(xjmfHelper.getRoot(), "QualityControlSubscription", EnumValidationLevel.Complete);
	}

	/**
	*
	*
	*/
	@Test
	public final void testQualityControlSignalCxF()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper s = xjmfHelper.appendMessage(EnumFamily.Signal, EnumType.Resource);
		s.getHeader().setID("S1");
		s.getHeader().setAttribute(AttributeName.REFID, "QC1");
		s.getHeader().setAttribute(AttributeName.TIME, new JDFDate().setTime(17, 0, 0).getDateTimeISO());
		final MessageResourceHelper mr = new MessageResourceHelper(s.getRoot());
		final SetHelper qqp = mr.appendSet(ElementName.QUALITYCONTROLRESULT);
		final ResourceHelper qpr = qqp.appendPartition(null, true);
		final KElement cxf = qpr.getRoot().appendElement("cc:CxF", "http://colorexchangeformat.com/CxF3-core");
		cxf.setText("CxF Measurement datas in the Object elements");
		cxf.setXPathAttribute("cc:Resources/cc:ObjectCollection/cc:Object/@Id", "patch1ID");
		cxf.setXPathAttribute("cc:Resources/cc:ObjectCollection/cc:Object[2]/@Id", "patch2ID");

		final JDFQualityControlResult qcr = (JDFQualityControlResult) qpr.getResource();
		qcr.setPassed(42);
		qcr.setFailed(3);
		setSnippet(xjmfHelper, true);
		writeRoundTripX(xjmfHelper.getRoot(), "QualityControlSignalCxF", EnumValidationLevel.Complete);

	}

	/**
	*
	*
	*/
	@Test
	public final void testQualityControlSignalRaw()
	{
		JMFBuilderFactory.getJMFBuilder(XJDFConstants.XJMF).setSenderID("DeviceID");
		final XJMFHelper xjmfHelper = new XJMFHelper();
		final MessageHelper s = xjmfHelper.appendMessage(EnumFamily.Signal, EnumType.Resource);
		s.getHeader().setID("S1");
		s.getHeader().setAttribute(AttributeName.REFID, "QC1");
		s.getHeader().setAttribute(AttributeName.TIME, new JDFDate().setTime(17, 0, 0).getDateTimeISO());
		final MessageResourceHelper mr = new MessageResourceHelper(s.getRoot());
		final SetHelper qqp = mr.appendSet(ElementName.QUALITYCONTROLRESULT);
		final ResourceHelper qpr = qqp.appendPartition(null, true);
		final JDFQualityControlResult qcr = (JDFQualityControlResult) qpr.getResource();
		final KElement cm = qcr.appendElement(COLOR_MEASUREMENT);
		cm.appendElement(ElementName.COLORMEASUREMENTCONDITIONS);
		final KElement ccs = cm.appendElement(ElementName.COLORCONTROLSTRIP);
		for (int i = 0; i < 2; i++)
		{
			final KElement patch = ccs.appendElement(PATCH);
			final JDFTransferFunction tf = new JDFTransferFunction();
			final Vector<Double> v = new Vector<>();
			for (int j = 1; j < 40; j++)
				v.add(Double.valueOf(60.345 * (i + 1) * j % 567));
			tf.set(380, 5, v);
			patch.setAttribute(SPECTRUM, tf.getString(2));
			patch.setAttribute(AttributeName.POSITION, new JDFXYPair(10, 10 + i * 30).getString(1));
			patch.setAttribute(AttributeName.CIELAB, new JDFLabColor(0, 0.6, 0.6).getString(4));

		}
		setSnippet(xjmfHelper, true);
		xjmfHelper.writeToFile(sm_dirTestDataTemp + "QualityControlSignalRaw.xjmf");
		// writeRoundTripX(xjmfHelper.getRoot(), "QualityControlSignalRaw", EnumValidationLevel.Complete);

	}

}
