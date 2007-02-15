/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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
package org.cip4.jdflib;

import junit.framework.TestSuite;

import org.cip4.jdflib.auto.AutoTest;
import org.cip4.jdflib.auto.JDFClassInstantiationTest;
import org.cip4.jdflib.core.AttrInfoTest;
import org.cip4.jdflib.core.ElemInfoTest;
import org.cip4.jdflib.core.FactoryTest;
import org.cip4.jdflib.core.FixVersionTest;
import org.cip4.jdflib.core.IterationTest;
import org.cip4.jdflib.core.JDFAuditTest;
import org.cip4.jdflib.core.JDFCustomerInfoTest;
import org.cip4.jdflib.core.JDFDocTest;
import org.cip4.jdflib.core.JDFElementTest;
import org.cip4.jdflib.core.JDFNodeInfoTest;
import org.cip4.jdflib.core.JDFRefElementTest;
import org.cip4.jdflib.core.JDFResourceLinkTest;
import org.cip4.jdflib.core.JDFSourceResourceTest;
import org.cip4.jdflib.core.KElementTest;
import org.cip4.jdflib.core.VElementTest;
import org.cip4.jdflib.core.VStringTest;
import org.cip4.jdflib.core.XMLDocTest;
import org.cip4.jdflib.datatypes.JDFAttributeMapTest;
import org.cip4.jdflib.datatypes.JDFDateTimeRangeTest;
import org.cip4.jdflib.datatypes.JDFDurationRangeTest;
import org.cip4.jdflib.datatypes.JDFEnumerationTest;
import org.cip4.jdflib.datatypes.JDFIntegerRangeListTest;
import org.cip4.jdflib.datatypes.JDFIntegerRangeTest;
import org.cip4.jdflib.datatypes.JDFNumListTest;
import org.cip4.jdflib.datatypes.JDFNumberRangeListTest;
import org.cip4.jdflib.datatypes.JDFPathTest;
import org.cip4.jdflib.datatypes.JDFRectangleRangeListTest;
import org.cip4.jdflib.datatypes.JDFRectangleRangeTest;
import org.cip4.jdflib.datatypes.JDFRectangleTest;
import org.cip4.jdflib.datatypes.JDFShapeRangeListTest;
import org.cip4.jdflib.datatypes.JDFURLTest;
import org.cip4.jdflib.datatypes.JDFXYPairRangeListTest;
import org.cip4.jdflib.datatypes.JDFXYPairTest;
import org.cip4.jdflib.datatypes.VJDFAttributeMapTest;
import org.cip4.jdflib.devicecapability.JDFActionPoolTest;
import org.cip4.jdflib.devicecapability.JDFDevCapTest;
import org.cip4.jdflib.devicecapability.JDFDevCapsTest;
import org.cip4.jdflib.devicecapability.JDFDeviceCapTest;
import org.cip4.jdflib.devicecapability.JDFEvaluationTest;
import org.cip4.jdflib.devicecapability.JDFIntegerStateTest;
import org.cip4.jdflib.devicecapability.JDFNumberStateTest;
import org.cip4.jdflib.devicecapability.JDFStateBaseTest;
import org.cip4.jdflib.devicecapability.JDFTestTest;
import org.cip4.jdflib.examples.AmountTest;
import org.cip4.jdflib.examples.ContentCreationTest;
import org.cip4.jdflib.examples.DigiPrintTest;
import org.cip4.jdflib.examples.JDFExampleDocTest;
import org.cip4.jdflib.examples.MISFinTest;
import org.cip4.jdflib.examples.NColorTest;
import org.cip4.jdflib.examples.VarnishTest;
import org.cip4.jdflib.examples.WebTest;
import org.cip4.jdflib.jmf.JDFMessageTest;
import org.cip4.jdflib.jmf.JDFPipeParamsTest;
import org.cip4.jdflib.jmf.JMFResourceTest;
import org.cip4.jdflib.jmf.JMFStatusTest;
import org.cip4.jdflib.jmf.JMFTest;
import org.cip4.jdflib.jmf.QueueTest;
import org.cip4.jdflib.node.JDFNodeProductTest;
import org.cip4.jdflib.node.JDFNodeTest;
import org.cip4.jdflib.pool.JDFAmountPoolTest;
import org.cip4.jdflib.pool.JDFAncestorPoolTest;
import org.cip4.jdflib.pool.JDFAuditPoolTest;
import org.cip4.jdflib.pool.JDFColorPoolTest;
import org.cip4.jdflib.pool.JDFResourceLinkPoolTest;
import org.cip4.jdflib.pool.JDFResourcePoolTest;
import org.cip4.jdflib.resource.JDFAssemblySectionTest;
import org.cip4.jdflib.resource.JDFAutoResourceTest;
import org.cip4.jdflib.resource.JDFFilespecTest;
import org.cip4.jdflib.resource.JDFLayerListTest;
import org.cip4.jdflib.resource.JDFLayoutTest;
import org.cip4.jdflib.resource.JDFMediaColorTest;
import org.cip4.jdflib.resource.JDFResourceTest;
import org.cip4.jdflib.resource.PhaseTimeTest;
import org.cip4.jdflib.resource.ProcessRunTest;
import org.cip4.jdflib.resource.process.JDFColorConversionParamsTest;
import org.cip4.jdflib.resource.process.JDFColorantControlTest;
import org.cip4.jdflib.resource.process.JDFCutBlockTest;
import org.cip4.jdflib.resource.process.JDFMediaTest;
import org.cip4.jdflib.resource.process.JDFPRItemTest;
import org.cip4.jdflib.resource.process.JDFRunListTest;
import org.cip4.jdflib.resource.process.JDFTileTest;
import org.cip4.jdflib.resource.process.JDFUsageCounterTest;
import org.cip4.jdflib.span.JDFEnumerationSpanTest;
import org.cip4.jdflib.util.HashUtilTest;
import org.cip4.jdflib.util.JDFDateTest;
import org.cip4.jdflib.util.JDFSpawnTest;
import org.cip4.jdflib.util.MimeUtilTest;
import org.cip4.jdflib.util.StringUtilTest;
import org.cip4.jdflib.util.UrlUtilTest;

/**
 * @author MuchaD
 *
 */
public class AllJDFLibTest extends JDFTestCaseBase
{
    public static TestSuite suite()
    {
        TestSuite suite = new TestSuite("Tests for org.cip4.jdflib.*");

        suite.addTestSuite(JDFClassInstantiationTest.class);
        
        suite.addTestSuite(CheckJDFTest.class);
        suite.addTestSuite(EmptyNamespace.class);
        
        suite.addTestSuite(AutoTest.class);
 
        // core
        suite.addTestSuite(AttrInfoTest.class);
        suite.addTestSuite(ElemInfoTest.class);
        suite.addTestSuite(FactoryTest.class);
        suite.addTestSuite(FixVersionTest.class);
        suite.addTestSuite(IterationTest.class);
        suite.addTestSuite(JDFAuditTest.class);
        suite.addTestSuite(JDFCustomerInfoTest.class);
        suite.addTestSuite(JDFDocTest.class);
        suite.addTestSuite(JDFElementTest.class);
        suite.addTestSuite(JDFNodeInfoTest.class);
        suite.addTestSuite(JDFResourceLinkTest.class);
        suite.addTestSuite(JDFRefElementTest.class);
        suite.addTestSuite(JDFSourceResourceTest.class);
        suite.addTestSuite(KElementTest.class);
        suite.addTestSuite(VElementTest.class);
        suite.addTestSuite(VStringTest.class);
        suite.addTestSuite(XMLDocTest.class);
        
        // datatype
        suite.addTestSuite(JDFAttributeMapTest.class);
        suite.addTestSuite(JDFDateTimeRangeTest.class);
        suite.addTestSuite(JDFDurationRangeTest.class);
        suite.addTestSuite(JDFEnumerationTest.class);
        suite.addTestSuite(JDFIntegerRangeTest.class);
        suite.addTestSuite(JDFIntegerRangeListTest.class);
        suite.addTestSuite(JDFNumberRangeListTest.class);
        suite.addTestSuite(JDFNumListTest.class);
        suite.addTestSuite(JDFPathTest.class);
        suite.addTestSuite(JDFURLTest.class);
        suite.addTestSuite(JDFRectangleRangeListTest.class);
        suite.addTestSuite(JDFRectangleRangeTest.class);
        suite.addTestSuite(JDFRectangleTest.class);
        suite.addTestSuite(JDFShapeRangeListTest.class);
        suite.addTestSuite(JDFXYPairRangeListTest.class);
        suite.addTestSuite(JDFXYPairTest.class);
        suite.addTestSuite(VJDFAttributeMapTest.class);
        
        // capabilities and preflight
        suite.addTestSuite(JDFActionPoolTest.class);
        suite.addTestSuite(JDFDeviceCapTest.class);
        suite.addTestSuite(JDFDevCapTest.class);
        suite.addTestSuite(JDFDevCapsTest.class);
        suite.addTestSuite(JDFEvaluationTest.class);
        suite.addTestSuite(JDFIntegerStateTest.class);
        suite.addTestSuite(JDFNumberStateTest.class);
        suite.addTestSuite(JDFStateBaseTest.class);
        suite.addTestSuite(JDFTestTest.class);
        
        //examples
        suite.addTestSuite(AmountTest.class);
        suite.addTestSuite(ContentCreationTest.class);
        suite.addTestSuite(DigiPrintTest.class);
        suite.addTestSuite(JDFExampleDocTest.class);
        suite.addTestSuite(MISFinTest.class);
        suite.addTestSuite(NColorTest.class);
        suite.addTestSuite(VarnishTest.class);
        suite.addTestSuite(WebTest.class);

        // JMF
        suite.addTestSuite(JDFMessageTest.class);
        suite.addTestSuite(JDFPipeParamsTest.class);
        suite.addTestSuite(JMFResourceTest.class);
        suite.addTestSuite(JMFStatusTest.class);
        suite.addTestSuite(JMFTest.class);
        suite.addTestSuite(QueueTest.class);
        
        // Node
        suite.addTestSuite(JDFNodeProductTest.class);
        suite.addTestSuite(JDFNodeTest.class);
        
        // pool
        suite.addTestSuite(JDFAmountPoolTest.class);
        suite.addTestSuite(JDFAncestorPoolTest.class);
        suite.addTestSuite(JDFAuditPoolTest.class);
        suite.addTestSuite(JDFColorPoolTest.class);
        suite.addTestSuite(JDFResourceLinkPoolTest.class);
        suite.addTestSuite(JDFResourcePoolTest.class);
        
        //resource
        suite.addTestSuite(ProcessRunTest.class);
        suite.addTestSuite(PhaseTimeTest.class);
        suite.addTestSuite(JDFAutoResourceTest.class);
        suite.addTestSuite(JDFFilespecTest.class);
        suite.addTestSuite(JDFAssemblySectionTest.class);
        suite.addTestSuite(JDFLayerListTest.class);
        suite.addTestSuite(JDFLayoutTest.class);
        suite.addTestSuite(JDFMediaColorTest.class);
        suite.addTestSuite(JDFResourceTest.class);
 
        // resource.process
        suite.addTestSuite(JDFColorantControlTest.class);
        suite.addTestSuite(JDFColorConversionParamsTest.class);
        suite.addTestSuite(JDFCutBlockTest.class);
        suite.addTestSuite(JDFMediaTest.class);
        suite.addTestSuite(JDFPRItemTest.class);
        suite.addTestSuite(JDFRunListTest.class);
        suite.addTestSuite(JDFTileTest.class);
        suite.addTestSuite(JDFUsageCounterTest.class);
        
        // util
        suite.addTestSuite(MimeUtilTest.class);
        suite.addTestSuite(HashUtilTest.class);
        suite.addTestSuite(JDFDateTest.class);
        suite.addTestSuite(JDFSpawnTest.class);
        suite.addTestSuite(StringUtilTest.class);
        suite.addTestSuite(UrlUtilTest.class);
        
        suite.addTestSuite(JDFEnumerationSpanTest.class);
        
        return suite;
    }
}