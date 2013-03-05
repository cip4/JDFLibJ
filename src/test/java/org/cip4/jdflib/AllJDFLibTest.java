/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of
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

/**
 * @author MuchaD
 * 
 */
public class AllJDFLibTest extends JDFTestCaseBase
{
	/**
	 * the big suite
	 * @return this
	 */
	public static TestSuite suite()
	{
		final TestSuite suite = new TestSuite("Tests for org.cip4.jdflib.*");
		/*
				suite.addTestSuite(JDFClassInstantiationTest.class);

				suite.addTestSuite(EmptyNamespace.class);

				suite.addTestSuite(AutoTest.class);

				// core
				suite.addTestSuite(AttrInfoTest.class);
				suite.addTestSuite(DocumentJDFImplTest.class);
				suite.addTestSuite(ElemInfoTest.class);
				suite.addTestSuite(FactoryTest.class);
				suite.addTestSuite(FixVersionTest.class);
				suite.addTestSuite(JDFAuditTest.class);
				suite.addTestSuite(JDFCustomerInfoTest.class);
				suite.addTestSuite(JDFDocThreadTest.class);
				suite.addTestSuite(JDFDocTest.class);
				suite.addTestSuite(JDFElementTest.class);
				suite.addTestSuite(JDFExceptionTest.class);
				suite.addTestSuite(JDFNodeInfoTest.class);
				suite.addTestSuite(JDFParserTest.class);
				suite.addTestSuite(JDFPartAmountTest.class);
				suite.addTestSuite(JDFResourceLinkTest.class);
				suite.addTestSuite(JDFSchemaTest.class);
				suite.addTestSuite(JDFRefElementTest.class);
				suite.addTestSuite(JDFSeparationListTest.class);
				suite.addTestSuite(JDFSourceResourceTest.class);
				suite.addTestSuite(KElementTest.class);
				suite.addTestSuite(VElementTest.class);
				suite.addTestSuite(VStringTest.class);
				suite.addTestSuite(XMLDocTest.class);
				suite.addTestSuite(XMLDocUserDataTest.class);
				suite.addTestSuite(XMLParserTest.class);
				suite.addTestSuite(XPathHelperTest.class);

				// cformat
				suite.addTestSuite(PrintfFormatTest.class);

				// datatype
				suite.addTestSuite(JDFAttributeMapTest.class);
				suite.addTestSuite(JDFCMYKColorTest.class);
				suite.addTestSuite(JDFDateTimeRangeTest.class);
				suite.addTestSuite(JDFDurationTest.class);
				suite.addTestSuite(JDFDurationRangeTest.class);
				suite.addTestSuite(JDFEnumerationTest.class);
				suite.addTestSuite(JDFIntegerListTest.class);
				suite.addTestSuite(JDFIntegerRangeTest.class);
				suite.addTestSuite(JDFIntegerRangeListTest.class);
				suite.addTestSuite(JDFMatrixTest.class);
				suite.addTestSuite(JDFNumberRangeListTest.class);
				suite.addTestSuite(JDFNumListTest.class);
				suite.addTestSuite(JDFPathTest.class);
				suite.addTestSuite(JDFRGBColorTest.class);
				suite.addTestSuite(JDFURLTest.class);
				suite.addTestSuite(JDFRectangleRangeListTest.class);
				suite.addTestSuite(JDFRectangleRangeTest.class);
				suite.addTestSuite(JDFRectangleTest.class);
				suite.addTestSuite(JDFShapeTest.class);
				suite.addTestSuite(JDFShapeRangeListTest.class);
				suite.addTestSuite(JDFXYPairRangeListTest.class);
				suite.addTestSuite(JDFXYPairTest.class);
				suite.addTestSuite(VJDFAttributeMapTest.class);

				// capabilities and preflight
				suite.addTestSuite(JDFActionPoolTest.class);
				suite.addTestSuite(JDFDeviceCapTest.class);
				suite.addTestSuite(JDFDevCapTest.class);
				suite.addTestSuite(JDFDevCapsTest.class);
				suite.addTestSuite(JDFDurationStateTest.class);
				suite.addTestSuite(JDFEvaluationTest.class);
				suite.addTestSuite(JDFIntegerStateTest.class);
				suite.addTestSuite(JDFModulePoolTest.class);
				suite.addTestSuite(JDFNameStateTest.class);
				suite.addTestSuite(JDFNumberStateTest.class);
				suite.addTestSuite(JDFStateBaseTest.class);
				suite.addTestSuite(JDFStringStateTest.class);
				suite.addTestSuite(JDFTestTest.class);

				// elementWalker
				suite.addTestSuite(AttributeReplacerTest.class);
				suite.addTestSuite(BaseWalkerTest.class);
				suite.addTestSuite(EnsureElementUriTest.class);
				suite.addTestSuite(EnsureNSUriTest.class);
				suite.addTestSuite(LinkRefFinderTest.class);
				suite.addTestSuite(RemoveEmptyTest.class);
				suite.addTestSuite(RemovePrivateTest.class);
				suite.addTestSuite(ResourceIDFinderTest.class);
				suite.addTestSuite(UnlinkFinderTest.class);
				suite.addTestSuite(URLExtractorTest.class);
				suite.addTestSuite(URLMapperTest.class);
				suite.addTestSuite(XPathWalkerTest.class);

				// examples
				suite.addTestSuite(AmountTest.class);
				suite.addTestSuite(AutomatedLayoutTest.class);
				suite.addTestSuite(CADTest.class);
				suite.addTestSuite(ContentCreationTest.class);
				suite.addTestSuite(DigiPrintTest.class);
				suite.addTestSuite(IterationTest.class);
				suite.addTestSuite(JDFExampleDocTest.class);
				suite.addTestSuite(JMFExampleTest.class);
				suite.addTestSuite(JMFPipeTest.class);
				suite.addTestSuite(MISFinTest.class);
				suite.addTestSuite(NColorTest.class);
				suite.addTestSuite(PreflightTest.class);
				suite.addTestSuite(RIPTest.class);
				suite.addTestSuite(SheetOptimizeTest.class);
				suite.addTestSuite(StrippingTest.class);
				suite.addTestSuite(VarnishTest.class);
				suite.addTestSuite(WebTest.class);

				//extensions
				suite.addTestSuite(BaseXJDFHelperTest.class);
				suite.addTestSuite(PartitionHelperTest.class);
				suite.addTestSuite(ProductHelperTest.class);
				suite.addTestSuite(SetHelperTest.class);
				suite.addTestSuite(XJDFCreatorTest.class);
				suite.addTestSuite(XJDFGeneratorTest.class);
				suite.addTestSuite(XJDFHelperTest.class);
				suite.addTestSuite(XJDFLayoutStripTest.class);
				suite.addTestSuite(XJDFSchemaCreatorTest.class);
				suite.addTestSuite(XJDFTest.class);

				//extensions.examples
				suite.addTestSuite(XJDFSheetOptimizeTest.class);

				//extensions.xjdfwalker
				suite.addTestSuite(IDFinderTest.class);
				suite.addTestSuite(JDFCapsConverterTest.class);
				suite.addTestSuite(XJDFToJDFConverterTest.class);

				// Golden tickets
				suite.addTestSuite(GoldenTicketTest.class);
				suite.addTestSuite(MISCPGoldenTicketTest.class);
				suite.addTestSuite(MISPreGoldenTicketTest.class);
				suite.addTestSuite(IDPGoldenTicketTest.class);

				// JMF
				suite.addTestSuite(JDFDeviceFilterTest.class);
				suite.addTestSuite(JDFDeviceInfoTest.class);
				suite.addTestSuite(JDFIDInfoTest.class);
				suite.addTestSuite(JDFJMFTest.class);
				suite.addTestSuite(JDFJobPhaseTest.class);
				suite.addTestSuite(JDFMessageTest.class);
				suite.addTestSuite(JDFMessageServiceTest.class);
				suite.addTestSuite(JDFPipeParamsTest.class);
				suite.addTestSuite(JDFQueueEntryTest.class);
				suite.addTestSuite(JDFQueueFilterTest.class);
				suite.addTestSuite(JDFQueueSubmissionParamsTest.class);
				suite.addTestSuite(JDFResourceInfoTest.class);
				suite.addTestSuite(JDFSubscriptionInfoTest.class);
				suite.addTestSuite(JMFBuilderTest.class);
				suite.addTestSuite(JMFBuilderFactoryTest.class);
				suite.addTestSuite(JMFKnownMessagesTest.class);
				suite.addTestSuite(JMFResourceTest.class);
				suite.addTestSuite(JMFResourceInfoTest.class);
				suite.addTestSuite(JMFStatusTest.class);
				suite.addTestSuite(QueueTest.class);

				// Node
				suite.addTestSuite(AuditToJMFTest.class);
				suite.addTestSuite(JDFNodeProductTest.class);
				suite.addTestSuite(JDFNodeTest.class);

				// pool
				suite.addTestSuite(JDFAmountPoolTest.class);
				suite.addTestSuite(JDFAncestorPoolTest.class);
				suite.addTestSuite(JDFAuditPoolTest.class);
				suite.addTestSuite(JDFColorPoolTest.class);
				suite.addTestSuite(JDFResourceLinkPoolTest.class);
				suite.addTestSuite(JDFResourcePoolTest.class);

				// resource
				suite.addTestSuite(JDFAssemblyTest.class);
				suite.addTestSuite(ProcessRunTest.class);
				suite.addTestSuite(PhaseTimeTest.class);
				suite.addTestSuite(JDFAutoResourceTest.class);
				suite.addTestSuite(JDFDeviceListTest.class);
				suite.addTestSuite(JDFFilespecTest.class);
				suite.addTestSuite(JDFAssemblySectionTest.class);
				suite.addTestSuite(JDFLayerListTest.class);
				suite.addTestSuite(JDFLayoutTest.class);
				suite.addTestSuite(JDFLayoutPreparationParamsTest.class);
				suite.addTestSuite(JDFMediaColorTest.class);
				suite.addTestSuite(JDFNotificationTest.class);
				suite.addTestSuite(JDFPageListTest.class);
				suite.addTestSuite(JDFPartTest.class);
				suite.addTestSuite(JDFPhaseTimeTest.class);
				suite.addTestSuite(JDFResourceTest.class);
				suite.addTestSuite(JDFStrippingTest.class);

				// resource.devicecapability
				suite.addTestSuite(JDFMatrixEvaluationTest.class);

				// resource.intent
				suite.addTestSuite(JDFColorIntentTest.class);
				suite.addTestSuite(JDFDeliveryIntentTest.class);
				suite.addTestSuite(JDFIntentResourceTest.class);
				suite.addTestSuite(JDFProductionIntentTest.class);

				// resource.process
				suite.addTestSuite(JDFAddressTest.class);
				suite.addTestSuite(JDFColorTest.class);
				suite.addTestSuite(JDFColorantControlTest.class);
				suite.addTestSuite(JDFColorConversionParamsTest.class);
				suite.addTestSuite(JDFComChannelTest.class);
				suite.addTestSuite(JDFComponentTest.class);
				suite.addTestSuite(JDFContentDataTest.class);
				suite.addTestSuite(JDFContentObjectTest.class);
				suite.addTestSuite(JDFCutBlockTest.class);
				suite.addTestSuite(JDFContactTest.class);
				suite.addTestSuite(JDFCostCenterTest.class);
				suite.addTestSuite(JDFDeliveryParamsTest.class);
				suite.addTestSuite(JDFDieLayoutTest.class);
				suite.addTestSuite(JDFEmployeeTest.class);
				suite.addTestSuite(JDFExposedMediaTest.class);
				suite.addTestSuite(JDFGeneralIDTest.class);
				suite.addTestSuite(JDFMediaTest.class);
				suite.addTestSuite(JDFPageDataTest.class);
				suite.addTestSuite(JDFPersonTest.class);
				suite.addTestSuite(JDFPreviewTest.class);
				suite.addTestSuite(JDFPRItemTest.class);
				suite.addTestSuite(JDFRunListTest.class);
				suite.addTestSuite(JDFTileTest.class);
				suite.addTestSuite(JDFUsageCounterTest.class);

				// resource.process.postpress
				suite.addTestSuite(JDFStitchingParamsTest.class);

				// span
				suite.addTestSuite(JDFEnumerationSpanTest.class);
				suite.addTestSuite(JDFSpanBaseTest.class);

				// util
				suite.addTestSuite(BiHashMapTest.class);
				suite.addTestSuite(ByteArrayIOStreamTest.class);
				suite.addTestSuite(ContainerUtilTest.class);
				suite.addTestSuite(CPUTimerTest.class);
				suite.addTestSuite(EnumUtilTest.class);
				suite.addTestSuite(FastFiFoTest.class);
				suite.addTestSuite(FileUtilTest.class);
				suite.addTestSuite(HashUtilTest.class);
				suite.addTestSuite(JDFDateTest.class);
				suite.addTestSuite(JDFSpawnTest.class);
				suite.addTestSuite(MemorySpyTest.class);
				suite.addTestSuite(MimeUtilTest.class);
				suite.addTestSuite(MyArgsTest.class);
				suite.addTestSuite(MyPairTest.class);
				suite.addTestSuite(NumberFormatterTest.class);
				suite.addTestSuite(PlatformUtilTest.class);
				suite.addTestSuite(PrefixInputStreamTest.class);
				suite.addTestSuite(RollingFileTest.class);
				suite.addTestSuite(RollingBackupTest.class);
				suite.addTestSuite(ScaleUtilTest.class);
				suite.addTestSuite(SkipInputStreamTest.class);
				suite.addTestSuite(SScanfTest.class);
				suite.addTestSuite(StatusCounterTest.class);
				suite.addTestSuite(StreamUtilTest.class);
				suite.addTestSuite(StringUtilTest.class);
				suite.addTestSuite(UnitParserTest.class);
				suite.addTestSuite(UrlPartTest.class);
				suite.addTestSuite(UrlUtilTest.class);
				suite.addTestSuite(URLReaderTest.class);
				suite.addTestSuite(VectorMapTest.class);

				// util.file
				suite.addTestSuite(FileSorterTest.class);
				suite.addTestSuite(FileJanitorTest.class);
				suite.addTestSuite(RollingBackupDirectoryTest.class);
				suite.addTestSuite(RollingFileTest.class);
				suite.addTestSuite(RollingDateFileTest.class);

				// util.hotfolder
				suite.addTestSuite(HotFolderTest.class);
				suite.addTestSuite(QueueHotFolderTest.class);
				suite.addTestSuite(StorageHotFolderTest.class);
				// util.mime
				suite.addTestSuite(MimeWriterTest.class);

				// util.net
				suite.addTestSuite(NetPollTest.class);
				suite.addTestSuite(ProxyUtilTest.class);
				suite.addTestSuite(URLProxySelectorTest.class);
				suite.addTestSuite(UrlCheckTest.class);

				// util.thread
				suite.addTestSuite(DelayedPersistTest.class);
				suite.addTestSuite(OrderedTaskQueueTest.class);
				suite.addTestSuite(ThreadFilterTest.class);
				suite.addTestSuite(ThreadUtilTest.class);

				// util.xml
				suite.addTestSuite(XSLTransformHelperTest.class);

				// util.zip
				suite.addTestSuite(ZipReaderTest.class);

				suite.addTestSuite(JDFEnumerationSpanTest.class);

				// validate
				suite.addTestSuite(JDFValidatorTest.class);
				suite.addTestSuite(VersionTranslatorTest.class);
				*/
		return suite;
	}
}