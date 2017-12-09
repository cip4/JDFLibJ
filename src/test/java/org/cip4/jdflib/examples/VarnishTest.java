/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2017 The International Cooperation for the Integration of
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
package org.cip4.jdflib.examples;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoComponent.EnumComponentType;
import org.cip4.jdflib.auto.JDFAutoConventionalPrintingParams.EnumWorkStyle;
import org.cip4.jdflib.auto.JDFAutoMedia.EnumMediaType;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.process.JDFColorantControl;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.resource.process.JDFConventionalPrintingParams;
import org.cip4.jdflib.resource.process.JDFExposedMedia;
import org.cip4.jdflib.resource.process.JDFMedia;
import org.cip4.jdflib.resource.process.prepress.JDFInk;
import org.junit.Test;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *
 * 28.11.2008
 */
public class VarnishTest extends JDFTestCaseBase
{
	/**
	 * test varnish
	 *
	 * @throws Exception x
	 */
	@Test
	public void testCombinedVarnish() throws Exception
	{
		KElement.setLongID(false);
		final JDFDoc d = new JDFDoc("JDF");
		final JDFNode n = d.getJDFRoot();
		final VString vCombiNodes = new VString("ConventionalPrinting Varnishing", " ");
		final VString vSeparations = new VString("Cyan Magenta Yellow Black Varnish", " ");
		n.setCombined(vCombiNodes);

		final JDFConventionalPrintingParams cpp = (JDFConventionalPrintingParams) n.addResource(ElementName.CONVENTIONALPRINTINGPARAMS, null, EnumUsage.Input, null, null, null, null);
		cpp.setWorkStyle(EnumWorkStyle.Simplex);
		cpp.setXMLComment("Module 0 and 7 are varnishing modules, 1-4 are process colors and 6 is the ink module used to varnish");
		final JDFComponent c = (JDFComponent) n.appendMatchingResource("Component", JDFNode.EnumProcessUsage.AnyOutput, null);
		c.setComponentType(EnumComponentType.PartialProduct, EnumComponentType.Sheet);
		final JDFExposedMedia xm = (JDFExposedMedia) n.appendMatchingResource("ExposedMedia", JDFNode.EnumProcessUsage.Plate, null);
		n.appendNodeInfo();
		final JDFMedia media = xm.appendMedia();
		media.setMediaType(EnumMediaType.Plate);
		final JDFInk ink = (JDFInk) n.appendMatchingResource("Ink", JDFNode.EnumProcessUsage.AnyInput, null);

		final JDFResource vp = n.addResource("VarnishingParams", EnumResourceClass.Parameter, EnumUsage.Input, null, null, null, null);
		final JDFExposedMedia xmVarnish = (JDFExposedMedia) n.addResource("ExposedMedia", null, EnumUsage.Input, null, null, null, null);
		final JDFMedia mediaVarnish = xmVarnish.appendMedia();
		mediaVarnish.setAttribute("MediaType", "Sleeve");

		final JDFResourceLink rl = n.getLink(xmVarnish, null);
		final JDFColorantControl cc = (JDFColorantControl) n.appendMatchingResource(ElementName.COLORANTCONTROL, JDFNode.EnumProcessUsage.AnyInput, null);
		cc.getCreateDeviceColorantOrder().appendXMLComment("Should the VarnishingParams seps be excluded, as is shown here?", null);
		cc.getCreateDeviceColorantOrder().setSeparations(vSeparations);

		rl.setCombinedProcessIndex(new JDFIntegerList("1"));
		vSeparations.addAll(new VString("PreVarnish Varnish2", " "));

		for (int i = 0; i < vSeparations.size(); i++)
		{
			final String sep = vSeparations.get(i);
			ink.addPartition(EnumPartIDKey.Separation, sep);
			if (!sep.equals("PreVarnish") && !sep.equals("Varnish2"))
			{
				xm.addPartition(EnumPartIDKey.Separation, sep);
			}
			if (sep.equals("Varnish2"))
			{
				vp.appendXMLComment("full varnishing in a varnishing module with or wihtout a sleeve. Full varnishing means to cover the complete media surface.", null);
				xmVarnish.addPartition(EnumPartIDKey.Separation, sep);
				final JDFResource varnishPart = vp.addPartition(EnumPartIDKey.Separation, sep);
				varnishPart.setAttribute("ModuleIndex", "7");
				varnishPart.setAttribute("VarnishMethod", "Blanket");
				varnishPart.setAttribute("VarnishArea", "Spot");
			}
			else if (sep.equals("Varnish"))
			{
				vp.appendXMLComment("varnishing in a printing module only  with a mandatory plate. The plate may be exposed or not, for example,  for full varnihing. ", null);
				// xmVarnish.addPartition(EnumPartIDKey.Separation, sep);
				final JDFResource varnishPart = vp.addPartition(EnumPartIDKey.Separation, sep);
				varnishPart.setAttribute("ModuleIndex", "6");
				varnishPart.setAttribute("VarnishMethod", "Plate");
				varnishPart.setAttribute("VarnishArea", "Full");
			}
			else if (sep.equals("PreVarnish"))
			{
				vp.appendXMLComment("varnishing in a varnishing module only with a mandatory prepared sleeve ", null);
				xmVarnish.addPartition(EnumPartIDKey.Separation, sep);
				final JDFResource varnishPart = vp.addPartition(EnumPartIDKey.Separation, sep);
				varnishPart.setAttribute("ModuleIndex", "0");
				varnishPart.setAttribute("VarnishMethod", "Blanket");
				varnishPart.setAttribute("VarnishArea", "Full");
			}
		}
		writeRoundTrip(n, "varnishing");
		//d.write2File(sm_dirTestDataTemp + "varnishing.jdf", 2, true);
	}
}
