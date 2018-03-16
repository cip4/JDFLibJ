/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2018 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 *
 */
package org.cip4.jdflib.extensions.examples;

import java.util.Vector;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumValidationLevel;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.extensions.ResourceHelper;
import org.cip4.jdflib.extensions.SetHelper;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.extensions.XJDFHelper;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.util.JDFDate;
import org.junit.Test;

/**
 *
 * @author rainer prosi
 *
 */
public class XJDFAdditionalExampleTest extends JDFTestCaseBase
{
	/**
	*
	*
	*/
	@Test
	public final void testSeparationSplit()
	{
		final XJDFHelper xjdfHelper = new XJDFHelper("SepSplit", null, null);
		xjdfHelper.addType(EnumType.ConventionalPrinting.getName(), 0);

		final SetHelper cp = xjdfHelper.getCreateSet(ElementName.CONVENTIONALPRINTINGPARAMS, EnumUsage.Input);
		cp.getCreatePartition(null, true).getResource().setAttribute(AttributeName.WORKSTYLE, EnumWorkStyle.Simplex.getName());

		final SetHelper pm = xjdfHelper.getCreateSet(ElementName.MEDIA, null);
		final SetHelper dev = xjdfHelper.getCreateSet(ElementName.DEVICE, EnumUsage.Input);
		final ResourceHelper mediaPart = pm.getCreatePartition(null, true);
		final JDFMedia plateMedia = (JDFMedia) mediaPart.getResource();
		plateMedia.setDimensionCM(new JDFXYPair(130, 80));
		plateMedia.setMediaType(EnumMediaType.Plate);

		final SetHelper compH = xjdfHelper.getCreateSet(ElementName.COMPONENT, EnumUsage.Output);
		final ResourceHelper compR = compH.getCreatePartition(new JDFAttributeMap(AttributeName.SHEETNAME, "S1"), true);

		final SetHelper xmH = xjdfHelper.getCreateSet(ElementName.EXPOSEDMEDIA, EnumUsage.Input);
		final SetHelper colorH = xjdfHelper.getCreateSet(ElementName.COLOR, EnumUsage.Input);

		final SetHelper niH = xjdfHelper.getCreateSet(ElementName.NODEINFO, EnumUsage.Input);
		niH.removePartitions();

		final JDFAttributeMap surfaceMap = new JDFAttributeMap(AttributeName.SHEETNAME, "S1");
		surfaceMap.put(AttributeName.SIDE, "Back");
		final VJDFAttributeMap back = new VJDFAttributeMap(surfaceMap);
		back.extendMap(AttributeName.SEPARATION, new VString("sep1 sep2 sep3", null));

		surfaceMap.put(AttributeName.SIDE, "Front");
		final VJDFAttributeMap front1 = new VJDFAttributeMap(surfaceMap);
		front1.extendMap(AttributeName.SEPARATION, new VString("sep1 sep2 sep3", null));
		final VJDFAttributeMap front2 = new VJDFAttributeMap(surfaceMap);
		front2.extendMap(AttributeName.SEPARATION, new VString("sep4 sep5 sep6", null));
		final VJDFAttributeMap colors = new VJDFAttributeMap(surfaceMap);
		colors.extendMap(AttributeName.SEPARATION, new VString("sep1 sep2 sep3 sep4 sep5 sep6", null));

		colorH.getCreatePartitions(colors, true);
		final Vector<VJDFAttributeMap> vvv = new Vector<>();
		vvv.add(back);
		vvv.add(front1);
		vvv.add(front2);
		int n = 0;
		for (final VJDFAttributeMap vv : vvv)
		{
			n += 100;
			final Vector<ResourceHelper> vxm = xmH.getCreatePartitions(vv, true);
			for (final ResourceHelper xm : vxm)
			{
				xm.getResource().setAttribute("MediaRef", mediaPart.ensureID());
			}
			final ResourceHelper niRH = niH.getCreateVPartition(vv, true);
			niRH.setAttribute(XJDFConstants.ExternalID, "S1_WS" + (n / 100));
			((JDFNodeInfo) niRH.getResource()).setEnd(new JDFDate().addOffset(0, n, 0, 0));

			final ResourceHelper devRH = dev.getCreateVPartition(vv, true);
			devRH.getResource().setAttribute(AttributeName.DEVICEID, "XL" + n);

			compR.setVAmount(4000 - n, vv, true);
			compR.setVAmount(66 - n / 10, vv, false);
		}
		cleanSnippets(xjdfHelper);
		writeRoundTripX(xjdfHelper.getRoot(), "SplitSep", EnumValidationLevel.Complete);

	}

	/**
	 * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
	 */
	@Override
	public void setUp() throws Exception
	{
		JDFElement.setLongID(false);
		super.setUp();
	}
}
