___________________________________________________________


Label JDFLIBJ_2.1.3BLD512 (30.07.2008)

Revision: 3440
Author: prosi
Date: 11:05:19, Mittwoch, 30. Juli 2008
Message:
Partition handling in NodeIdentifier uses NodeInfo if no ancestorpool
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java

Revision: 3439
Author: mucha
Date: 09:27:07, Mittwoch, 30. Juli 2008
Message:
cosmetics
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourceLinkPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/press/JDFPrintCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/CheckJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/IDPGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISCPGoldenTicketTest.java

Revision: 3437
Author: prosi
Date: 13:28:47, Dienstag, 29. Juli 2008
Message:

----
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/IDPGoldenTicketTest.java

Revision: 3436
Author: prosi
Date: 13:25:53, Dienstag, 29. Juli 2008
Message:
new golden tickets
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/IDPGoldenTicket.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISFinGoldenTicket.java

Revision: 3433
Author: prosi
Date: 13:10:37, Dienstag, 29. Juli 2008
Message:
added elementwalker package
split checkJDF and validator
Bambi improvements
XMLDoc.cpp - fix spurious file io exceptions in write2File

part 2 due to svn problems
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/CheckJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFRefElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalker.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalkerFactory.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/ElementWalker.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/IWalker.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/IWalkerFactory.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/UnLinkFinder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourceLinkPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFInk.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/press/JDFPrintCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ByteArrayIOStream.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDuration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/validate
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/ICheckValidator.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/ICheckValidatorFactory.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/JDFValidator.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/AllJDFLibTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/CheckJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/auto/AutoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/auto/JDFClassInstantiationTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFAuditTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFDurationTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/elementwalker
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/elementwalker/BaseWalkerTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/elementwalker/UnlinkFinderTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISCPGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFDeviceInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFNotificationTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFMediaTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/ByteArrayIOStreamTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFDateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StatusCounterTest.java

Revision: 3428
Author: mucha
Date: 17:29:58, Mittwoch, 23. Juli 2008
Message:
cosmetics
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java

Revision: 3427
Author: mucha
Date: 16:26:02, Dienstag, 22. Juli 2008
Message:
cosmetics
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/TestJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueEntryTest.java

Revision: 3426
Author: prosi
Date: 16:05:09, Montag, 21. Juli 2008
Message:
proxy, xslt + queue enhancements for bambi
----
Modified : /trunk/Bambi/WebContent/core/deviceList.xsl
Modified : /trunk/Bambi/WebContent/core/queue2html.xsl
Modified : /trunk/Bambi/WebContent/core/showDevice.xsl
Modified : /trunk/Bambi/WebContent/proxy/WEB-INF/web.xml
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/AbstractDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/AbstractDeviceProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/BambiServlet.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/BambiServletRequest.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/BambiServletResponse.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/IDeviceProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/IGetHandler.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/MultiDeviceProperties.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/RootDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/SignalDispatcher.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/StatusListener.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/messaging/JMFBufferHandler.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/messaging/JMFFactory.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/messaging/MessageSender.java
Deleted : /trunk/Bambi/src/java/org/cip4/bambi/core/queues/IQueueProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/queues/QueueEntry.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/queues/QueueProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/AbstractProxyDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/AbstractProxyProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/IProxyProperties.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/ProxyDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/ProxyDeviceProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/ProxyProperties.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/workers/core/AbstractWorkerDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/workers/core/AbstractWorkerDeviceProcessor.java
Modified : /trunk/Bambi/test/org/cip4/bambi/BambiTestCase.java
Modified : /trunk/Bambi/test/org/cip4/bambi/ProxyTest.java
Modified : /trunk/JDFLibC/Projects/Win32/Vc8/TestMIMEMultiPart/TestMIMEMultiPart.vcproj
Modified : /trunk/JDFLibC/src/jdf/wrappercore/XMLDoc.cpp
Modified : /trunk/JDFLibC/tests/TestWrapper/TestThreadsCore.cpp
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFEmployeeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/AllJDFLibTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFAuditTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/VElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/IterationTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/JDFExampleDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFDeviceInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFJMFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFJobPhaseTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueEntryTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAuditPoolTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFEmployeeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/ContainerUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StatusCounterTest.java
Modified : /trunk/jdfutility/src/java/org/cip4/JDFUtility/DumpJDFServlet.java



>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD512) && !lbtype(JDFLIBJ_2.1.3BLD511)}" -print
.@@\main\25
.\core\JDFDoc.java@@\main\80
.\core\JDFElement.java@@\main\248
.\core\JDFRefElement.java@@\main\64
.\core\JDFResourceLink.java@@\main\146
.\core\KElement.java@@\main\263
.\core\VElement.java@@\main\34
.\elementwalker@@\main\1
.\elementwalker\BaseWalker.java@@\main\1
.\elementwalker\BaseWalkerFactory.java@@\main\1
.\elementwalker\ElementWalker.java@@\main\1
.\elementwalker\IWalker.java@@\main\1
.\elementwalker\IWalkerFactory.java@@\main\1
.\elementwalker\UnLinkFinder.java@@\main\1
.\goldenticket@@\main\4
.\goldenticket\BaseGoldenTicket.java@@\main\10
.\goldenticket\IDPGoldenTicket.java@@\main\1
.\goldenticket\MISCPGoldenTicket.java@@\main\10
.\goldenticket\MISFinGoldenTicket.java@@\main\1
.\goldenticket\MISGoldenTicket.java@@\main\11
.\goldenticket\MISPreGoldenTicket.java@@\main\3
.\jmf\JDFDeviceInfo.java@@\main\26
.\jmf\JDFJMF.java@@\main\69
.\jmf\JDFMessage.java@@\main\77
.\jmf\JDFQueue.java@@\main\34
.\jmf\JDFQueueEntry.java@@\main\29
.\jmf\JDFQueueSubmissionParams.java@@\main\18
.\node\JDFNode.java@@\main\273
.\pool\JDFAuditPool.java@@\main\105
.\pool\JDFResourceLinkPool.java@@\main\82
.\resource\JDFNotification.java@@\main\19
.\resource\JDFPhaseTime.java@@\main\32
.\resource\JDFProcessRun.java@@\main\34
.\resource\JDFResource.java@@\main\234
.\resource\process\JDFEmployee.java@@\main\18
.\resource\process\JDFMedia.java@@\main\26
.\resource\process\prepress\JDFInk.java@@\main\19
.\resource\process\press\JDFPrintCondition.java@@\main\5
.\util\ContainerUtil.java@@\main\6
.\util\DumpDir.java@@\main\5
.\util\FileUtil.java@@\main\7
.\util\JDFDate.java@@\main\51
.\util\JDFDuration.java@@\main\9
.\util\MimeUtil.java@@\main\23
.\util\StatusCounter.java@@\main\17
.\util\StatusUtil.java@@\main\11
.\util\StringUtil.java@@\main\70
.\util\UrlUtil.java@@\main\18
.\validate@@\main\1
.\validate\ICheckValidator.java@@\main\1
.\validate\ICheckValidatorFactory.java@@\main\1
.\validate\JDFValidator.java@@\main\1
___________________________________________________________


Label JDFLIBJ_2.1.3BLD511 (15.07.2008)

Revision: 3422
Author: prosi
Date: 10:23:15, Montag, 14. Juli 2008
Message:
proxy + queue enhancements for bambi
----
Modified : /trunk/Bambi/.classpath
Modified : /trunk/Bambi/WebContent/core/deviceList.xsl
Modified : /trunk/Bambi/WebContent/core/queue2html.xsl
Modified : /trunk/Bambi/WebContent/core/showDevice.xsl
Modified : /trunk/Bambi/build-core.xml
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/AbstractDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/BambiNSExtension.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/BambiServlet.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/messaging/JMFBufferHandler.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/messaging/JMFFactory.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/queues/IQueueProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/queues/QueueProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/AbstractProxyDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/AbstractProxyProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/ProxyDeviceProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/ProxyProperties.java
Modified : /trunk/Bambi/src/lib/JDFLibJ-2.1.3.jar
Modified : /trunk/JDFLibC/src/jdf/wrappercore/XMLDoc.cpp
Modified : /trunk/JDFLibC/tests/TestWrapper/TestThreadsCore.cpp
Modified : /trunk/JDFLibC/tests/TestWrapper/TestWrapperCore.cpp
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResponse.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueEntryTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java

Revision: 3420
Author: prosi
Date: 10:21:32, Donnerstag, 10. Juli 2008
Message:
updated version.properties
----
Modified : /trunk/JDFLibJ/version.properties



>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD511) && !lbtype(JDFLIBJ_2.1.3BLD510)}" -print
.\core\KElement.java@@\main\261
.\jmf\JDFQueue.java@@\main\33
.\jmf\JDFQueueSubmissionParams.java@@\main\17
.\jmf\JDFResponse.java@@\main\31
.\node\JDFNode.java@@\main\270
.\util\JDFMerge.java@@\main\25
___________________________________________________________


Label JDFLIBJ_2.1.3BLD510 (10.07.2008)

Revision: 3412
Author: prosi
Date: 08:41:32, Mittwoch, 9. Juli 2008
Message:

----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/IMatches.java

Revision: 3411
Author: prosi
Date: 08:41:15, Mittwoch, 9. Juli 2008
Message:
Fix handling of unc for relative paths in File.cpp
enable multiple retries in writetostream
----
Modified : /trunk/JDFLibC/src/jdf/io/Platforms/Win32/Win32FileSystem.cpp
Modified : /trunk/JDFLibC/src/jdf/io/Platforms/Win32/Win32FileSystem.h
Modified : /trunk/JDFLibC/src/jdf/wrappercore/XMLDoc.cpp
Modified : /trunk/JDFLibC/src/jdf/wrappercore/XMLDoc.h
Modified : /trunk/JDFLibC/tests/TestWrapper/TestWrapperCore.cpp
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/TestJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java

Revision: 3409
Author: prosi
Date: 17:09:10, Freitag, 4. Juli 2008
Message:
bambi proxy cleanup
dumpjdf servlet creates subdirectories
----
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/BambiNSExtension.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/BambiServlet.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/MultiDeviceProperties.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/messaging/JMFFactory.java
Added : /trunk/Bambi/src/java/org/cip4/bambi/proxy/AbstractProxyDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/AbstractProxyProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/ProxyDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/ProxyDeviceProcessor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFResourceLinkTest.java
Modified : /trunk/jdfutility/src/java/org/cip4/JDFUtility/DumpJDFServlet.java

Revision: 3407
Author: prosi
Date: 08:25:33, Freitag, 4. Juli 2008
Message:
spawning/merging of partitioned resources with identical elements
bambi xslt optimization
bambi proxy evolution
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/VectorMap.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/RIPTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFDeviceInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFJobPhaseTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueEntryTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/UrlUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/VectorMapTest.java

Revision: 3406
Author: prosi
Date: 08:24:41, Freitag, 4. Juli 2008
Message:
spawning/merging of partitioned resources with identical elements
bambi xslt optimization
bambi proxy evolution
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java

Revision: 3400
Author: prosi
Date: 10:35:45, Dienstag, 1. Juli 2008
Message:
Added Flush Queue support for JDFLib and Bambi
----
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/AbstractDeviceProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/SignalDispatcher.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/StatusListener.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/messaging/MessageSender.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/queues/QueueProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/ProxyDeviceProcessor.java
Modified : /trunk/Bambi/test/org/cip4/bambi/ProxyTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFPartAmount.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFFlushQueueInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueEntryTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAmountPoolTest.java

Revision: 3395
Author: prosi
Date: 15:16:30, Freitag, 27. Juni 2008
Message:
Improved Bambi Thread synchronization
cleanup callback for automated queues
----
Modified : /trunk/Bambi/WebContent/proxy/WEB-INF/web.xml
Modified : /trunk/Bambi/WebContent/proxy/config/devices.xml
Modified : /trunk/Bambi/WebContent/workers/sim/WEB-INF/web.xml
Modified : /trunk/Bambi/WebContent/workers/sim/config/job_sim001.xml
Modified : /trunk/Bambi/test/org/cip4/bambi/AsyncMessagingTest.java
Modified : /trunk/Bambi/test/org/cip4/bambi/BambiTestCase.java
Modified : /trunk/Bambi/test/org/cip4/bambi/JMFFactoryTest.java
Modified : /trunk/Bambi/test/org/cip4/bambi/ProxyTest.java
Modified : /trunk/Bambi/test/org/cip4/bambi/QueueEntryStatusTest.java
Modified : /trunk/Bambi/test/org/cip4/bambi/WebAppTest.java
Modified : /trunk/Bambi/test/org/cip4/bambi/allBambiTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAuditPoolTest.java

Revision: 3388
Author: mucha
Date: 10:52:24, Mittwoch, 25. Juni 2008
Message:
npe fix in auto files
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPSToPDFConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRectangleEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterRibbon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRenderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceDefinitionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourcePullParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRunList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScanParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreenSelector.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShrinkingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShutDownCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignatureCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSizeIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpineTapingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripCellParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrippingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSystemTimeSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFFormatParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferFunctionControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrimmingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUsageCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoXYPairEvaluation.java

Revision: 3387
Author: mucha
Date: 10:51:42, Mittwoch, 25. Juni 2008
Message:
npe fix in auto files
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJPEG2000Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLabelingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMISDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaSource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModifyNodeCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMsgFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFToPSConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFXParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlateCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReport.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreview.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreviewGenerationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintConditionColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingParams.java

Revision: 3386
Author: mucha
Date: 10:51:02, Mittwoch, 25. Juni 2008
Message:
npe fix in auto files
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComChannel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContactCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoConventionalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCrease.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutBlock.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSchema.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDCTParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisjointing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisposition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDrop.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoElementColorParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmboss.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoErrorData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExposedMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeatureAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeeder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeederQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFitPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushResourceParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFolderProduction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHeadBandApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHole.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentificationField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompression.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageReplacementParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageSetterParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingParams.java

Revision: 3385
Author: mucha
Date: 10:50:15, Mittwoch, 25. Juni 2008
Message:
npe fix in auto files
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdvancedParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDelivery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssembly.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssemblySection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssetListCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeCompParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeReproParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBasicPreflightTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBinderySignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBlockPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxToBoxDifference.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundleItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoByteMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCIELABMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollatingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorMeasurementConditions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionOp.java

Revision: 3384
Author: mucha
Date: 10:49:10, Mittwoch, 25. Juni 2008
Message:
cosmetics
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFProcessRun.java

Revision: 3377
Author: prosi
Date: 11:07:34, Montag, 23. Juni 2008
Message:
fix warnings in StatusCounter
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java

Revision: 3376
Author: prosi
Date: 11:00:56, Montag, 23. Juni 2008
Message:
better duplicate color checking in colorpool
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorPool.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFColorPoolTest.java

Revision: 3375
Author: prosi
Date: 10:41:59, Montag, 23. Juni 2008
Message:
Bambi Proxy fixes
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/TestJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorPool.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISCPGoldenTicketTest.java

Revision: 3373
Author: prosi
Date: 09:16:53, Montag, 23. Juni 2008
Message:
Bambi Proxy fixes
----
Modified : /trunk/Bambi/WebContent/core/css/styles_pc.css
Added : /trunk/Bambi/WebContent/core/deviceList.xsl
Modified : /trunk/Bambi/WebContent/proxy/WEB-INF/web.xml
Modified : /trunk/Bambi/WebContent/proxy/config/devices.xml
Deleted : /trunk/Bambi/WebContent/proxy/showProxyDevice.xsl
Modified : /trunk/Bambi/WebContent/workers/sim/WEB-INF/web.xml
Deleted : /trunk/Bambi/WebContent/workers/sim/showSimDevice.xsl
Modified : /trunk/Bambi/build-proxy.xml
Modified : /trunk/Bambi/build-simworker.xml
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/AbstractDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/AbstractDeviceProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/BambiNSExtension.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/BambiServlet.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/ISignalDispatcher.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/IStatusListener.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/RootDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/SignalDispatcher.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/StatusListener.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/messaging/MessageSender.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/queues/QueueProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/ProxyDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/proxy/ProxyDeviceProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/workers/core/AbstractWorkerDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/workers/core/AbstractWorkerDeviceProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/workers/sim/SimDevice.java
Modified : /trunk/Bambi/test/org/cip4/bambi/BambiTestCase.java
Modified : /trunk/JDFEditor/src/java/org/cip4/jdfeditor/EditorMenuBar.java
Modified : /trunk/JDFEditor/src/java/org/cip4/jdfeditor/JDFTreeNode.java
Modified : /trunk/JDFEditor/src/java/org/cip4/jdfeditor/ProcessPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFRefElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VString.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/VJDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourceLinkPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFInk.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/press/JDFPrintCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFResourceLinkTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/NColorTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/BaseGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/GoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISCPGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISPreGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAmountPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/ContainerUtilTest.java

Revision: 3326
Author: prosi
Date: 18:16:48, Montag, 26. Mai 2008
Message:
bambi fixes
golden ticket enhancements
----
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/messaging/JMFHandler.java
Modified : /trunk/JDFEditor/src/java/org/cip4/jdfeditor/JDFTreeNode.java
Modified : /trunk/JDFEditor/src/java/org/cip4/jdfeditor/ProcessPart.java
Modified : /trunk/JDFEditor/src/java/org/cip4/jdfeditor/SearchDialog.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/CheckJDF.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/SpawnJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFCustomerInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAncestorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFMediaIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/GoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFDateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java

Revision: 3289
Author: prosi
Date: 15:33:02, Mittwoch, 7. Mai 2008
Message:
better multiple id check in checkjdf and editor
----
Modified : /trunk/JDFEditor/src/java/org/cip4/jdfeditor/CheckJDFOutputRenderer.java
Modified : /trunk/JDFEditor/src/java/org/cip4/jdfeditor/JDFTreeModel.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/CheckJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISCPGoldenTicketTest.java

Revision: 3285
Author: prosi
Date: 10:17:39, Dienstag, 6. Mai 2008
Message:
Bambi Cleanup
Golden Ticket enhancements for gray box expansion
capability fixes for partitioned resources
created ids now start with _ to always ensure valid ids, also fix date format to use 24 h time format string
correct collection of spawned refelements
----
Modified : /trunk/Bambi/.classpath
Added : /trunk/Bambi/WebContent/core/logo.gif
Modified : /trunk/Bambi/WebContent/core/queue2html.xsl
Modified : /trunk/Bambi/WebContent/workers/sim/config/devices.xml
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/AbstractDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/AbstractDeviceProcessor.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/BambiServlet.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/RootDevice.java
Modified : /trunk/Bambi/src/java/org/cip4/bambi/core/queues/QueueProcessor.java
Modified : /trunk/Bambi/test/org/cip4/bambi/BambiTestCase.java
Modified : /trunk/JDFEditor/src/java/org/cip4/jdfeditor/JDFTreeNode.java
Modified : /trunk/JDFLibC/tests/TestWrapper/TestWrapperCore.cpp
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/JDFTestCaseBase.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFEvaluationTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/BaseGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/GoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISCPGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISPreGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java

Revision: 3284
Author: prosi
Date: 18:03:44, Montag, 5. Mai 2008
Message:
fix for spawning local refelements
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java



>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD510) && !lbtype(JDFLIBJ_2.1.3BLD500)}" -print
.\auto\JDFAutoAction.java@@\main\27
.\auto\JDFAutoAdvancedParams.java@@\main\62
.\auto\JDFAutoAncestor.java@@\main\68
.\auto\JDFAutoApprovalDetails.java@@\main\21
.\auto\JDFAutoApprovalPerson.java@@\main\68
.\auto\JDFAutoArtDelivery.java@@\main\75
.\auto\JDFAutoArtDeliveryIntent.java@@\main\71
.\auto\JDFAutoAssembly.java@@\main\30
.\auto\JDFAutoAssemblySection.java@@\main\30
.\auto\JDFAutoAssetListCreationParams.java@@\main\28
.\auto\JDFAutoBarcodeCompParams.java@@\main\15
.\auto\JDFAutoBarcodeReproParams.java@@\main\17
.\auto\JDFAutoBasicPreflightTest.java@@\main\31
.\auto\JDFAutoBinderySignature.java@@\main\32
.\auto\JDFAutoBindingIntent.java@@\main\67
.\auto\JDFAutoBlockPreparationParams.java@@\main\56
.\auto\JDFAutoBoxArgument.java@@\main\25
.\auto\JDFAutoBoxFoldAction.java@@\main\17
.\auto\JDFAutoBoxFoldingParams.java@@\main\19
.\auto\JDFAutoBoxToBoxDifference.java@@\main\24
.\auto\JDFAutoBundle.java@@\main\56
.\auto\JDFAutoBundleItem.java@@\main\56
.\auto\JDFAutoByteMap.java@@\main\72
.\auto\JDFAutoChangedPath.java@@\main\25
.\auto\JDFAutoChannelBindingParams.java@@\main\51
.\auto\JDFAutoCIELABMeasuringField.java@@\main\71
.\auto\JDFAutoCoilBindingParams.java@@\main\57
.\auto\JDFAutoCollatingItem.java@@\main\29
.\auto\JDFAutoColor.java@@\main\75
.\auto\JDFAutoColorMeasurementConditions.java@@\main\54
.\auto\JDFAutoColorSpaceConversionOp.java@@\main\60
.\auto\JDFAutoColorSpaceConversionParams.java@@\main\70
.\auto\JDFAutoComChannel.java@@\main\65
.\auto\JDFAutoComponent.java@@\main\79
.\auto\JDFAutoContactCopyParams.java@@\main\56
.\auto\JDFAutoContentObject.java@@\main\69
.\auto\JDFAutoConventionalPrintingParams.java@@\main\75
.\auto\JDFAutoCrease.java@@\main\62
.\auto\JDFAutoCut.java@@\main\63
.\auto\JDFAutoCutBlock.java@@\main\58
.\auto\JDFAutoCutMark.java@@\main\68
.\auto\JDFAutoCylinderPosition.java@@\main\13
.\auto\JDFAutoDBSchema.java@@\main\50
.\auto\JDFAutoDCTParams.java@@\main\28
.\auto\JDFAutoDeliveryIntent.java@@\main\77
.\auto\JDFAutoDeliveryParams.java@@\main\64
.\auto\JDFAutoDevCap.java@@\main\49
.\auto\JDFAutoDevCaps.java@@\main\54
.\auto\JDFAutoDeviceCap.java@@\main\51
.\auto\JDFAutoDeviceFilter.java@@\main\52
.\auto\JDFAutoDeviceInfo.java@@\main\64
.\auto\JDFAutoDeviceMark.java@@\main\49
.\auto\JDFAutoDigitalDeliveryParams.java@@\main\30
.\auto\JDFAutoDigitalPrintingParams.java@@\main\76
.\auto\JDFAutoDisjointing.java@@\main\72
.\auto\JDFAutoDisposition.java@@\main\33
.\auto\JDFAutoDrop.java@@\main\74
.\auto\JDFAutoElementColorParams.java@@\main\33
.\auto\JDFAutoEmboss.java@@\main\52
.\auto\JDFAutoError.java@@\main\44
.\auto\JDFAutoErrorData.java@@\main\2
.\auto\JDFAutoExposedMedia.java@@\main\73
.\auto\JDFAutoFeatureAttribute.java@@\main\26
.\auto\JDFAutoFeeder.java@@\main\30
.\auto\JDFAutoFeederQualityParams.java@@\main\25
.\auto\JDFAutoFileAlias.java@@\main\63
.\auto\JDFAutoFileSpec.java@@\main\80
.\auto\JDFAutoFitPolicy.java@@\main\51
.\auto\JDFAutoFlushResourceParams.java@@\main\28
.\auto\JDFAutoFold.java@@\main\69
.\auto\JDFAutoFolderProduction.java@@\main\12
.\auto\JDFAutoFoldingParams.java@@\main\70
.\auto\JDFAutoFontParams.java@@\main\66
.\auto\JDFAutoGlue.java@@\main\71
.\auto\JDFAutoGlueApplication.java@@\main\68
.\auto\JDFAutoGlueLine.java@@\main\67
.\auto\JDFAutoHeadBandApplicationParams.java@@\main\56
.\auto\JDFAutoHole.java@@\main\64
.\auto\JDFAutoHoleMakingIntent.java@@\main\67
.\auto\JDFAutoHoleMakingParams.java@@\main\73
.\auto\JDFAutoIdentificationField.java@@\main\67
.\auto\JDFAutoIDPCover.java@@\main\57
.\auto\JDFAutoIDPImageShift.java@@\main\44
.\auto\JDFAutoIDPJobSheet.java@@\main\55
.\auto\JDFAutoIDPLayout.java@@\main\56
.\auto\JDFAutoIDPrintingParams.java@@\main\73
.\auto\JDFAutoIDPStitching.java@@\main\66
.\auto\JDFAutoImageCompression.java@@\main\60
.\auto\JDFAutoImageReplacementParams.java@@\main\72
.\auto\JDFAutoImageSetterParams.java@@\main\60
.\auto\JDFAutoImageShift.java@@\main\62
.\auto\JDFAutoInsertingParams.java@@\main\57
.\auto\JDFAutoInsertSheet.java@@\main\74
.\auto\JDFAutoInterpretingParams.java@@\main\58
.\auto\JDFAutoJDFService.java@@\main\46
.\auto\JDFAutoJMF.java@@\main\63
.\auto\JDFAutoJobPhase.java@@\main\66
.\auto\JDFAutoJPEG2000Params.java@@\main\15
.\auto\JDFAutoLabelingParams.java@@\main\49
.\auto\JDFAutoLaminatingParams.java@@\main\54
.\auto\JDFAutoLayout.java@@\main\64
.\auto\JDFAutoLayoutElement.java@@\main\75
.\auto\JDFAutoLayoutIntent.java@@\main\67
.\auto\JDFAutoLayoutPreparationParams.java@@\main\63
.\auto\JDFAutoLongGlue.java@@\main\63
.\auto\JDFAutoLot.java@@\main\13
.\auto\JDFAutoMarkObject.java@@\main\75
.\auto\JDFAutoMedia.java@@\main\80
.\auto\JDFAutoMediaSource.java@@\main\72
.\auto\JDFAutoMessageService.java@@\main\57
.\auto\JDFAutoMISDetails.java@@\main\27
.\auto\JDFAutoModifyNodeCmdParams.java@@\main\18
.\auto\JDFAutoModuleCap.java@@\main\15
.\auto\JDFAutoModulePhase.java@@\main\79
.\auto\JDFAutoModuleStatus.java@@\main\56
.\auto\JDFAutoMsgFilter.java@@\main\56
.\auto\JDFAutoNewComment.java@@\main\15
.\auto\JDFAutoNodeInfo.java@@\main\77
.\auto\JDFAutoNodeInfoCmdParams.java@@\main\31
.\auto\JDFAutoNotification.java@@\main\59
.\auto\JDFAutoPackingParams.java@@\main\54
.\auto\JDFAutoPageCell.java@@\main\55
.\auto\JDFAutoPart.java@@\main\53
.\auto\JDFAutoPDFInterpretingParams.java@@\main\55
.\auto\JDFAutoPDFToPSConversionParams.java@@\main\68
.\auto\JDFAutoPDFXParams.java@@\main\26
.\auto\JDFAutoPerforate.java@@\main\63
.\auto\JDFAutoPipeParams.java@@\main\51
.\auto\JDFAutoPlasticCombBindingParams.java@@\main\58
.\auto\JDFAutoPlateCopyParams.java@@\main\52
.\auto\JDFAutoPosition.java@@\main\29
.\auto\JDFAutoPreflightAction.java@@\main\24
.\auto\JDFAutoPreflightReport.java@@\main\32
.\auto\JDFAutoPRError.java@@\main\27
.\auto\JDFAutoPreview.java@@\main\68
.\auto\JDFAutoPreviewGenerationParams.java@@\main\61
.\auto\JDFAutoPrintConditionColor.java@@\main\29
.\auto\JDFAutoProcessRun.java@@\main\56
.\auto\JDFAutoProofingParams.java@@\main\71
.\auto\JDFAutoPSToPDFConversionParams.java@@\main\72
.\auto\JDFAutoQueue.java@@\main\58
.\auto\JDFAutoQueueEntry.java@@\main\62
.\auto\JDFAutoQueueFilter.java@@\main\35
.\auto\JDFAutoQueueSubmissionParams.java@@\main\52
.\auto\JDFAutoRectangleEvaluation.java@@\main\30
.\auto\JDFAutoRegisterMark.java@@\main\70
.\auto\JDFAutoRegisterRibbon.java@@\main\49
.\auto\JDFAutoRenderingParams.java@@\main\71
.\auto\JDFAutoRequestQueueEntryParams.java@@\main\30
.\auto\JDFAutoResourceAudit.java@@\main\50
.\auto\JDFAutoResourceCmdParams.java@@\main\54
.\auto\JDFAutoResourceDefinitionParams.java@@\main\57
.\auto\JDFAutoResourceInfo.java@@\main\62
.\auto\JDFAutoResourceParam.java@@\main\46
.\auto\JDFAutoResourcePullParams.java@@\main\34
.\auto\JDFAutoResourceQuParams.java@@\main\57
.\auto\JDFAutoRingBindingParams.java@@\main\56
.\auto\JDFAutoRunList.java@@\main\78
.\auto\JDFAutoSaddleStitchingParams.java@@\main\54
.\auto\JDFAutoScanParams.java@@\main\73
.\auto\JDFAutoScreenSelector.java@@\main\66
.\auto\JDFAutoShapeCuttingParams.java@@\main\50
.\auto\JDFAutoShapeElement.java@@\main\44
.\auto\JDFAutoShrinkingParams.java@@\main\55
.\auto\JDFAutoShutDownCmdParams.java@@\main\28
.\auto\JDFAutoSideSewingParams.java@@\main\55
.\auto\JDFAutoSignatureCell.java@@\main\27
.\auto\JDFAutoSizeIntent.java@@\main\65
.\auto\JDFAutoSpineTapingParams.java@@\main\55
.\auto\JDFAutoStatusQuParams.java@@\main\50
.\auto\JDFAutoStitchingParams.java@@\main\66
.\auto\JDFAutoStrap.java@@\main\51
.\auto\JDFAutoStrappingParams.java@@\main\50
.\auto\JDFAutoStripBindingParams.java@@\main\51
.\auto\JDFAutoStripCellParams.java@@\main\27
.\auto\JDFAutoStripMark.java@@\main\19
.\auto\JDFAutoStrippingParams.java@@\main\33
.\auto\JDFAutoThreadSealingParams.java@@\main\53
.\auto\JDFAutoThreadSewingParams.java@@\main\59
.\auto\JDFAutoTIFFFormatParams.java@@\main\33
.\auto\JDFAutoTransferFunctionControl.java@@\main\66
.\auto\JDFAutoTrappingParams.java@@\main\73
.\auto\JDFAutoTrimmingParams.java@@\main\67
.\auto\JDFAutoUsageCounter.java@@\main\18
.\auto\JDFAutoValue.java@@\main\30
.\auto\JDFAutoWireCombBindingParams.java@@\main\56
.\auto\JDFAutoWrappingParams.java@@\main\47
.\auto\JDFAutoXYPairEvaluation.java@@\main\29
.\core\JDFCustomerInfo.java@@\main\42
.\core\JDFCustomerMessage.java@@\main\7
.\core\JDFDoc.java@@\main\79
.\core\JDFElement.java@@\main\247
.\core\JDFNodeInfo.java@@\main\58
.\core\JDFPartAmount.java@@\main\29
.\core\JDFPartStatus.java@@\main\24
.\core\JDFRefElement.java@@\main\63
.\core\JDFResourceLink.java@@\main\144
.\core\JDFSeparationList.java@@\main\24
.\core\KElement.java@@\main\260
.\core\VString.java@@\main\42
.\core\XMLDoc.java@@\main\93
.\datatypes\JDFDateTimeRange.java@@\main\14
.\datatypes\JDFDurationRange.java@@\main\15
.\datatypes\VJDFAttributeMap.java@@\main\31
.\goldenticket\BaseGoldenTicket.java@@\main\8
.\goldenticket\MISCPGoldenTicket.java@@\main\9
.\goldenticket\MISGoldenTicket.java@@\main\10
.\goldenticket\MISPreGoldenTicket.java@@\main\2
.\goldenticket\ProductGoldenTicket.java@@\main\5
.\ifaces@@\main\5
.\ifaces\IMatches.java@@\main\1
.\jmf\JDFAcknowledge.java@@\main\21
.\jmf\JDFAdded.java@@\main\3
.\jmf\JDFChangedPath.java@@\main\7
.\jmf\JDFCommand.java@@\main\22
.\jmf\JDFDeviceInfo.java@@\main\24
.\jmf\JDFFlushedResources.java@@\main\8
.\jmf\JDFFlushQueueInfo.java@@\main\4
.\jmf\JDFFlushQueueParams.java@@\main\9
.\jmf\JDFFlushResourceParams.java@@\main\9
.\jmf\JDFIDInfo.java@@\main\9
.\jmf\JDFJobPhase.java@@\main\25
.\jmf\JDFMessage.java@@\main\76
.\jmf\JDFMessageService.java@@\main\18
.\jmf\JDFNewJDFCmdParams.java@@\main\8
.\jmf\JDFNewJDFQuParams.java@@\main\9
.\jmf\JDFNodeInfoCmdParams.java@@\main\14
.\jmf\JDFNodeInfoQuParams.java@@\main\13
.\jmf\JDFNodeInfoResp.java@@\main\14
.\jmf\JDFQueue.java@@\main\32
.\jmf\JDFQueueEntry.java@@\main\28
.\jmf\JDFQueueFilter.java@@\main\12
.\jmf\JDFResourceInfo.java@@\main\31
.\jmf\JDFResourcePullParams.java@@\main\14
.\jmf\JDFShutDownCmdParams.java@@\main\9
.\jmf\JDFSignal.java@@\main\24
.\jmf\JDFWakeUpCmdParams.java@@\main\9
.\node\JDFNode.java@@\main\269
.\pool\JDFAmountPool.java@@\main\28
.\pool\JDFAncestorPool.java@@\main\48
.\pool\JDFAuditPool.java@@\main\103
.\pool\JDFPreflightConstraintsPool.java@@\main\23
.\pool\JDFResourceLinkPool.java@@\main\81
.\resource\devicecapability\JDFAction.java@@\main\16
.\resource\devicecapability\JDFBasicPreflightTest.java@@\main\4
.\resource\devicecapability\JDFcall.java@@\main\4
.\resource\devicecapability\JDFchoice.java@@\main\4
.\resource\devicecapability\JDFDevCapPool.java@@\main\10
.\resource\devicecapability\JDFDisplayGroup.java@@\main\4
.\resource\devicecapability\JDFDisplayGroupPool.java@@\main\4
.\resource\devicecapability\JDFEvaluation.java@@\main\29
.\resource\devicecapability\JDFFeatureAttribute.java@@\main\4
.\resource\devicecapability\JDFFeaturePool.java@@\main\5
.\resource\devicecapability\JDFLoc.java@@\main\5
.\resource\devicecapability\JDFmacro.java@@\main\4
.\resource\devicecapability\JDFMacroPool.java@@\main\4
.\resource\devicecapability\JDFotherwise.java@@\main\4
.\resource\devicecapability\JDFset.java@@\main\4
.\resource\devicecapability\JDFTestPool.java@@\main\7
.\resource\devicecapability\JDFwhen.java@@\main\4
.\resource\intent\JDFArtDelivery.java@@\main\22
.\resource\intent\JDFArtDeliveryIntent.java@@\main\18
.\resource\intent\JDFArtDeliveryType.java@@\main\12
.\resource\intent\JDFBindingIntent.java@@\main\18
.\resource\intent\JDFBookCase.java@@\main\17
.\resource\intent\JDFEmbossingIntent.java@@\main\7
.\resource\intent\JDFFoldingIntent.java@@\main\18
.\resource\intent\JDFMediaIntent.java@@\main\15
.\resource\intent\JDFNumberingIntent.java@@\main\7
.\resource\intent\JDFPublishingIntent.java@@\main\2
.\resource\JDFBand.java@@\main\18
.\resource\JDFBarcode.java@@\main\8
.\resource\JDFBindItem.java@@\main\11
.\resource\JDFBindList.java@@\main\7
.\resource\JDFBlockPreparationParams.java@@\main\7
.\resource\JDFBoxPackingParams.java@@\main\7
.\resource\JDFBufferParams.java@@\main\7
.\resource\JDFBundle.java@@\main\9
.\resource\JDFBundleItem.java@@\main\10
.\resource\JDFCaseMakingParams.java@@\main\7
.\resource\JDFCasingInParams.java@@\main\7
.\resource\JDFChangedAttribute.java@@\main\7
.\resource\JDFColorMeasurementConditions.java@@\main\9
.\resource\JDFColorsUsed.java@@\main\7
.\resource\JDFCounterReset.java@@\main\7
.\resource\JDFCoverApplicationParams.java@@\main\9
.\resource\JDFCoverColor.java@@\main\8
.\resource\JDFCreasingParams.java@@\main\7
.\resource\JDFCreated.java@@\main\8
.\resource\JDFCreditCard.java@@\main\7
.\resource\JDFDeviceMark.java@@\main\9
.\resource\JDFEdgeGluing.java@@\main\7
.\resource\JDFEmboss.java@@\main\9
.\resource\JDFEmbossingItem.java@@\main\8
.\resource\JDFEmbossingParams.java@@\main\7
.\resource\JDFError.java@@\main\7
.\resource\JDFEvent.java@@\main\2
.\resource\JDFFCNKey.java@@\main\7
.\resource\JDFFitPolicy.java@@\main\9
.\resource\JDFFormatConversionParams.java@@\main\7
.\resource\JDFGatheringParams.java@@\main\7
.\resource\JDFGluingParams.java@@\main\7
.\resource\JDFHardCoverBinding.java@@\main\7
.\resource\JDFHeadBandApplicationParams.java@@\main\7
.\resource\JDFHoleLine.java@@\main\7
.\resource\JDFIcon.java@@\main\9
.\resource\JDFIconList.java@@\main\8
.\resource\JDFIDPCover.java@@\main\7
.\resource\JDFIDPImageShift.java@@\main\7
.\resource\JDFIDPJobSheet.java@@\main\7
.\resource\JDFInterpretingParams.java@@\main\8
.\resource\JDFJacketingParams.java@@\main\7
.\resource\JDFJobField.java@@\main\7
.\resource\JDFJobSheet.java@@\main\7
.\resource\JDFLabelingParams.java@@\main\7
.\resource\JDFLaminatingParams.java@@\main\11
.\resource\JDFLayerDetails.java@@\main\7
.\resource\JDFLayerList.java@@\main\8
.\resource\JDFLayoutPreparationParams.java@@\main\7
.\resource\JDFMerged.java@@\main\12
.\resource\JDFModified.java@@\main\9
.\resource\JDFNumberItem.java@@\main\8
.\resource\JDFObservationTarget.java@@\main\7
.\resource\JDFPageCell.java@@\main\7
.\resource\JDFPageList.java@@\main\4
.\resource\JDFPallet.java@@\main\7
.\resource\JDFPalletizingParams.java@@\main\7
.\resource\JDFPayment.java@@\main\7
.\resource\JDFPDFInterpretingParams.java@@\main\7
.\resource\JDFPerforatingParams.java@@\main\7
.\resource\JDFPerformance.java@@\main\7
.\resource\JDFPhaseTime.java@@\main\31
.\resource\JDFPlaceHolderResource.java@@\main\5
.\resource\JDFProcessRun.java@@\main\33
.\resource\JDFProofItem.java@@\main\8
.\resource\JDFQueueEntryDefList.java@@\main\7
.\resource\JDFRegisterRibbon.java@@\main\9
.\resource\JDFRemoved.java@@\main\7
.\resource\JDFResource.java@@\main\233
.\resource\JDFResourceParam.java@@\main\8
.\resource\JDFScavengerArea.java@@\main\7
.\resource\JDFShapeCuttingParams.java@@\main\7
.\resource\JDFShapeElement.java@@\main\7
.\resource\JDFShrinkingParams.java@@\main\7
.\resource\JDFSoftCoverBinding.java@@\main\7
.\resource\JDFSpinePreparationParams.java@@\main\9
.\resource\JDFSpineTapingParams.java@@\main\9
.\resource\JDFStackingParams.java@@\main\7
.\resource\JDFStrap.java@@\main\7
.\resource\JDFStrappingParams.java@@\main\7
.\resource\JDFStripBinding.java@@\main\7
.\resource\JDFStrippingParams.java@@\main\2
.\resource\JDFSystemTimeSet.java@@\main\7
.\resource\JDFTape.java@@\main\7
.\resource\JDFThreadSealingParams.java@@\main\7
.\resource\JDFTool.java@@\main\12
.\resource\JDFTransferFunctionControl.java@@\main\7
.\resource\JDFVerificationParams.java@@\main\7
.\resource\JDFWrappingParams.java@@\main\8
.\resource\process\JDFAddress.java@@\main\18
.\resource\process\JDFAdvancedParams.java@@\main\19
.\resource\process\JDFApprovalDetails.java@@\main\2
.\resource\process\JDFApprovalParams.java@@\main\7
.\resource\process\JDFApprovalPerson.java@@\main\7
.\resource\process\JDFApprovalSuccess.java@@\main\20
.\resource\process\JDFArgumentValue.java@@\main\4
.\resource\process\JDFAssembly.java@@\main\4
.\resource\process\JDFBarcodeCompParams.java@@\main\2
.\resource\process\JDFBarcodeDetails.java@@\main\2
.\resource\process\JDFBarcodeReproParams.java@@\main\2
.\resource\process\JDFBendingParams.java@@\main\2
.\resource\process\JDFBinderySignature.java@@\main\8
.\resource\process\JDFBindingQualityParams.java@@\main\4
.\resource\process\JDFBoxApplication.java@@\main\2
.\resource\process\JDFBoxArgument.java@@\main\5
.\resource\process\JDFBoxFoldAction.java@@\main\2
.\resource\process\JDFBoxFoldingParams.java@@\main\2
.\resource\process\JDFBoxToBoxDifference.java@@\main\4
.\resource\process\JDFBusinessInfo.java@@\main\7
.\resource\process\JDFByteMap.java@@\main\20
.\resource\process\JDFCCITTFaxParams.java@@\main\3
.\resource\process\JDFCIELABMeasuringField.java@@\main\16
.\resource\process\JDFCollatingItem.java@@\main\4
.\resource\process\JDFCollectingParams.java@@\main\7
.\resource\process\JDFColor.java@@\main\27
.\resource\process\JDFColorantControl.java@@\main\25
.\resource\process\JDFColorantZoneDetails.java@@\main\17
.\resource\process\JDFColorControlStrip.java@@\main\16
.\resource\process\JDFColorPool.java@@\main\29
.\resource\process\JDFColorsResultsPool.java@@\main\16
.\resource\process\JDFCompany.java@@\main\14
.\resource\process\JDFConstraintValue.java@@\main\10
.\resource\process\JDFContainer.java@@\main\4
.\resource\process\JDFContentList.java@@\main\2
.\resource\process\JDFCostCenter.java@@\main\15
.\resource\process\JDFCover.java@@\main\11
.\resource\process\JDFCylinderLayout.java@@\main\2
.\resource\process\JDFCylinderLayoutPreparationParams.java@@\main\2
.\resource\process\JDFCylinderPosition.java@@\main\2
.\resource\process\JDFDBMergeParams.java@@\main\7
.\resource\process\JDFDBRules.java@@\main\7
.\resource\process\JDFDBSelection.java@@\main\7
.\resource\process\JDFDCTParams.java@@\main\3
.\resource\process\JDFDeliveryParams.java@@\main\7
.\resource\process\JDFDependencies.java@@\main\5
.\resource\process\JDFDieLayout.java@@\main\2
.\resource\process\JDFDigitalMedia.java@@\main\4
.\resource\process\JDFDisposition.java@@\main\4
.\resource\process\JDFDrop.java@@\main\7
.\resource\process\JDFElementColorParams.java@@\main\4
.\resource\process\JDFEmployee.java@@\main\17
.\resource\process\JDFExternalImpositionTemplate.java@@\main\2
.\resource\process\JDFExtraValues.java@@\main\2
.\resource\process\JDFFeeder.java@@\main\4
.\resource\process\JDFFeederQualityParams.java@@\main\3
.\resource\process\JDFFeedingParams.java@@\main\3
.\resource\process\JDFFlateParams.java@@\main\3
.\resource\process\JDFFolderProduction.java@@\main\2
.\resource\process\JDFFoldOperation.java@@\main\2
.\resource\process\JDFFontParams.java@@\main\17
.\resource\process\JDFGeneralID.java@@\main\3
.\resource\process\JDFIdentical.java@@\main\3
.\resource\process\JDFIDPTrimming.java@@\main\17
.\resource\process\JDFImageSetterParams.java@@\main\7
.\resource\process\JDFImagesResultsPool.java@@\main\17
.\resource\process\JDFInsertSheet.java@@\main\15
.\resource\process\JDFInterpretedPDLData.java@@\main\7
.\resource\process\JDFJBIG2Params.java@@\main\2
.\resource\process\JDFJPEG2000Params.java@@\main\2
.\resource\process\JDFLayoutElementPart.java@@\main\3
.\resource\process\JDFLayoutElementProductionParams.java@@\main\3
.\resource\process\JDFLongFold.java@@\main\17
.\resource\process\JDFLongGlue.java@@\main\17
.\resource\process\JDFLongitudinalRibbonOperationParams.java@@\main\17
.\resource\process\JDFLongPerforate.java@@\main\17
.\resource\process\JDFLongSlit.java@@\main\17
.\resource\process\JDFLot.java@@\main\3
.\resource\process\JDFLZWParams.java@@\main\3
.\resource\process\JDFManualLaborParams.java@@\main\3
.\resource\process\JDFMediaLayers.java@@\main\2
.\resource\process\JDFMediaSource.java@@\main\15
.\resource\process\JDFMiscConsumable.java@@\main\2
.\resource\process\JDFMISDetails.java@@\main\4
.\resource\process\JDFNumberingParam.java@@\main\7
.\resource\process\JDFObjectResolution.java@@\main\14
.\resource\process\JDFOCGControl.java@@\main\2
.\resource\process\JDFOrderingParams.java@@\main\7
.\resource\process\JDFPackingParams.java@@\main\7
.\resource\process\JDFPageAssignedList.java@@\main\2
.\resource\process\JDFPageElement.java@@\main\2
.\resource\process\JDFPDFXParams.java@@\main\4
.\resource\process\JDFPDLCreationParams.java@@\main\2
.\resource\process\JDFPerforate.java@@\main\18
.\resource\process\JDFPixelColorant.java@@\main\17
.\resource\process\JDFPlateCopyParams.java@@\main\7
.\resource\process\JDFPosition.java@@\main\4
.\resource\process\JDFPreflightAction.java@@\main\4
.\resource\process\JDFPreflightArgument.java@@\main\5
.\resource\process\JDFPreflightParams.java@@\main\4
.\resource\process\JDFPreflightReport.java@@\main\6
.\resource\process\JDFPreflightReportRulePool.java@@\main\4
.\resource\process\JDFPRError.java@@\main\4
.\resource\process\JDFPreview.java@@\main\17
.\resource\process\JDFPRGroup.java@@\main\5
.\resource\process\JDFPRGroupOccurrence.java@@\main\6
.\resource\process\JDFPrintConditionColor.java@@\main\4
.\resource\process\JDFPrintRollingParams.java@@\main\3
.\resource\process\JDFPRItem.java@@\main\6
.\resource\process\JDFPROccurrence.java@@\main\7
.\resource\process\JDFProductionPath.java@@\main\4
.\resource\process\JDFProductionSubPath.java@@\main\3
.\resource\process\JDFProofingParams.java@@\main\17
.\resource\process\JDFPRRule.java@@\main\4
.\resource\process\JDFPRRuleAttr.java@@\main\4
.\resource\process\JDFQualityControlParams.java@@\main\3
.\resource\process\JDFQualityControlResult.java@@\main\4
.\resource\process\JDFQualityMeasurement.java@@\main\4
.\resource\process\JDFResourceDefinitionParams.java@@\main\7
.\resource\process\JDFRollStand.java@@\main\4
.\resource\process\JDFSealing.java@@\main\9
.\resource\process\JDFSeparationControlParams.java@@\main\7
.\resource\process\JDFSeparationSpec.java@@\main\15
.\resource\process\JDFSignatureCell.java@@\main\4
.\resource\process\JDFStation.java@@\main\2
.\resource\process\JDFStringListValue.java@@\main\4
.\resource\process\JDFStripMark.java@@\main\2
.\resource\process\JDFThinPDFParams.java@@\main\17
.\resource\process\JDFTIFFEmbeddedFile.java@@\main\2
.\resource\process\JDFTIFFFormatParams.java@@\main\4
.\resource\process\JDFTIFFtag.java@@\main\2
.\resource\process\JDFTile.java@@\main\17
.\resource\process\JDFTransferCurvePool.java@@\main\15
.\resource\process\JDFTransferCurveSet.java@@\main\18
.\resource\process\JDFTrapRegion.java@@\main\8
.\resource\process\postpress\JDFAdhesiveBinding.java@@\main\17
.\resource\process\postpress\JDFChannelBinding.java@@\main\12
.\resource\process\postpress\JDFChannelBindingParams.java@@\main\7
.\resource\process\postpress\JDFCoilBinding.java@@\main\12
.\resource\process\postpress\JDFCrease.java@@\main\17
.\resource\process\postpress\JDFCut.java@@\main\17
.\resource\process\postpress\JDFCutMark.java@@\main\16
.\resource\process\postpress\JDFEndSheet.java@@\main\28
.\resource\process\postpress\JDFFold.java@@\main\18
.\resource\process\postpress\JDFFoldingParams.java@@\main\17
.\resource\process\postpress\JDFGlue.java@@\main\18
.\resource\process\postpress\JDFGlueApplication.java@@\main\7
.\resource\process\postpress\JDFGlueLine.java@@\main\15
.\resource\process\postpress\JDFHole.java@@\main\14
.\resource\process\postpress\JDFHoleList.java@@\main\17
.\resource\process\postpress\JDFHoleMakingParams.java@@\main\19
.\resource\process\postpress\JDFPlasticCombBinding.java@@\main\13
.\resource\process\postpress\JDFPlasticCombBindingParams.java@@\main\7
.\resource\process\postpress\JDFRingBinding.java@@\main\13
.\resource\process\postpress\JDFRingBindingParams.java@@\main\7
.\resource\process\postpress\JDFSaddleStitching.java@@\main\10
.\resource\process\postpress\JDFSaddleStitchingParams.java@@\main\7
.\resource\process\postpress\JDFScore.java@@\main\26
.\resource\process\postpress\JDFSideSewing.java@@\main\10
.\resource\process\postpress\JDFSideSewingParams.java@@\main\7
.\resource\process\postpress\JDFSideStitching.java@@\main\10
.\resource\process\postpress\JDFStitchingParams.java@@\main\17
.\resource\process\postpress\JDFThreadSealing.java@@\main\10
.\resource\process\postpress\JDFThreadSewing.java@@\main\12
.\resource\process\postpress\JDFThreadSewingParams.java@@\main\7
.\resource\process\postpress\JDFTrimmingParams.java@@\main\17
.\resource\process\postpress\JDFVeloBinding.java@@\main\9
.\resource\process\postpress\JDFWebInlineFinishingParams.java@@\main\2
.\resource\process\postpress\JDFWireCombBinding.java@@\main\13
.\resource\process\postpress\JDFWireCombBindingParams.java@@\main\7
.\resource\process\prepress\JDFColorCorrectionOp.java@@\main\18
.\resource\process\prepress\JDFColorCorrectionParams.java@@\main\21
.\resource\process\prepress\JDFColorSpaceConversionOp.java@@\main\16
.\resource\process\prepress\JDFColorSpaceConversionParams.java@@\main\17
.\resource\process\prepress\JDFColorSpaceSubstitute.java@@\main\17
.\resource\process\prepress\JDFFileTypeResultsPool.java@@\main\17
.\resource\process\prepress\JDFFontsResultsPool.java@@\main\17
.\resource\process\prepress\JDFInk.java@@\main\18
.\resource\process\prepress\JDFInkZoneCalculationParams.java@@\main\17
.\resource\process\prepress\JDFInkZoneProfile.java@@\main\17
.\resource\process\prepress\JDFPagesResultsPool.java@@\main\17
.\resource\process\prepress\JDFPDFToPSConversionParams.java@@\main\17
.\resource\process\prepress\JDFPDLResourceAlias.java@@\main\17
.\resource\process\prepress\JDFPreflightConstraint.java@@\main\18
.\resource\process\prepress\JDFPreflightDetail.java@@\main\18
.\resource\process\prepress\JDFPreflightInstance.java@@\main\18
.\resource\process\prepress\JDFPreflightInstanceDetail.java@@\main\18
.\resource\process\prepress\JDFPreflightInventory.java@@\main\7
.\resource\process\prepress\JDFPreflightProfile.java@@\main\7
.\resource\process\prepress\JDFPreviewGenerationParams.java@@\main\7
.\resource\process\prepress\JDFPSToPDFConversionParams.java@@\main\17
.\resource\process\prepress\JDFRenderingParams.java@@\main\17
.\resource\process\prepress\JDFScanParams.java@@\main\18
.\resource\process\prepress\JDFScreeningParams.java@@\main\18
.\resource\process\prepress\JDFTrappingDetails.java@@\main\17
.\resource\process\prepress\JDFTrappingOrder.java@@\main\17
.\resource\process\prepress\JDFTrappingParams.java@@\main\16
.\resource\process\press\JDFIDPrintingParams.java@@\main\17
.\resource\process\press\JDFPrintCondition.java@@\main\4
.\span\JDFSpanArtHandling.java@@\main\13
.\span\JDFSpanBindingType.java@@\main\13
.\span\JDFSpanHoleType.java@@\main\15
.\span\JDFSpanNamedColor.java@@\main\14
.\span\JDFSpanWireCombShape.java@@\main\14
.\util\ContainerUtil.java@@\main\5
.\util\DumpDir.java@@\main\3
.\util\JDFDate.java@@\main\49
.\util\JDFMerge.java@@\main\24
.\util\JDFSpawn.java@@\main\26
.\util\MimeUtil.java@@\main\21
.\util\StatusCounter.java@@\main\15
.\util\StringUtil.java@@\main\69
.\util\UrlUtil.java@@\main\17
.\util\VectorMap.java@@\main\4
___________________________________________________________


Label JDFLIBJ_2.1.3BLD500 (25.04.2008)

Revision: 3260
Author: prosi
Date: 11:42:58, Freitag, 25. April 2008
Message:

----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java


Revision: 3256
Author: prosi
Date: 10:20:17, Freitag, 25. April 2008
Message:
added dumpdir
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java


Revision: 3255
Author: prosi
Date: 19:52:56, Donnerstag, 24. April 2008
Message:
DeviceCapabilities fix for ListType=Range
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFAbstractState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFNameState.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFNameStateTest.java


Revision: 3254
Author: prosi
Date: 18:56:38, Donnerstag, 24. April 2008
Message:
KElement.copyAttribute with namespaces now works
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFResourceLinkTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISCPGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java


Revision: 3253
Author: mucha
Date: 15:47:48, Donnerstag, 24. April 2008
Message:
fix for validation of ModuleIndex and ModuleID
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFModuleStatus.java


Revision: 3252
Author: mucha
Date: 14:50:07, Donnerstag, 24. April 2008
Message:
fix for JDFAutoPart.java (BlockName)
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPart.java


Revision: 3251
Author: mucha
Date: 14:49:18, Donnerstag, 24. April 2008
Message:
fix for JDFAutoPart.java
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/GeneratorUtil.java


Revision: 3240
Author: prosi
Date: 08:50:18, Mittwoch, 23. April 2008
Message:
bambi development, including proxy
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFResourceLinkTest.java


Revision: 3238
Author: prosi
Date: 15:22:25, Montag, 21. April 2008
Message:
devcap fix for resources
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCaps.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFDeviceCapTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFDateTest.java


Revision: 3235
Author: prosi
Date: 20:09:29, Freitag, 18. April 2008
Message:
proxy and bambi improvements
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MultiModuleStatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StatusCounterTest.java


Revision: 3233
Author: prosi
Date: 17:08:24, Donnerstag, 17. April 2008
Message:
http://www.cip4.org/jira/browse/CHECK-40
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/CheckJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/CheckJDFTest.java


Revision: 3232
Author: prosi
Date: 14:30:36, Donnerstag, 17. April 2008
Message:
Bambi simulator upgrades
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFResourceLinkTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueEntryTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFDateTest.java


Revision: 3230
Author: prosi
Date: 20:42:53, Freitag, 11. April 2008
Message:
enhanced control when writing xmldoc to http
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/TestJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourceLinkPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/JDFTestCaseBase.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/NColorTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISCPGoldenTicketTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISPreGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFResourceLinkPoolTest.java


Revision: 3224
Author: prosi
Date: 15:05:57, Montag, 7. April 2008
Message:
getLink fix for non-jdf namespace resources
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourceLinkPool.java


Revision: 3223
Author: prosi
Date: 19:21:19, Freitag, 4. April 2008
Message:
bambi updates to write resource audits
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFUsageCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFUsageCounterTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StatusCounterTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java


Revision: 3221
Author: prosi
Date: 10:50:52, Donnerstag, 3. April 2008
Message:
add control of part encoding in mime
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueSubmissionParamsTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java


Revision: 3205
Author: prosi
Date: 18:22:22, Donnerstag, 20. März 2008
Message:
Bambi update
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFResourceLinkTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java


Revision: 3200
Author: prosi
Date: 17:04:47, Dienstag, 18. März 2008
Message:
fix devcap bug for schema required
bambi upgrades
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java


Revision: 3199
Author: prosi
Date: 15:14:32, Montag, 17. März 2008
Message:
checkjdf fixes from interop
add new JDFDoc/XMLDoc.write2HTTPURL()
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/CheckJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFParser.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFStatusPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/VectorMap.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/CheckJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/ContentCreationTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/StrippingTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFLayoutTest.java


Revision: 3182
Author: mucha
Date: 11:42:36, Freitag, 7. März 2008
Message:

----
Modified : /trunk/JDFLibJ/Readme.txt


Revision: 3180
Author: prosi
Date: 08:10:02, Freitag, 7. März 2008
Message:
either / or for ModuleID ModuleIndex in ModuleStatus
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFModuleStatus.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/NColorTest.java


Revision: 3175
Author: prosi
Date: 14:03:02, Mittwoch, 5. März 2008
Message:
Added partition reduction to statuscounter (e.g. surface for components)
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java


Revision: 3172
Author: prosi
Date: 10:12:19, Mittwoch, 5. März 2008
Message:
complete merge of manual and sim into sim
minor devcap fix for action tests
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEvaluation.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFResourceLinkTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISCPGoldenTicketTest.java


Revision: 3169
Author: prosi
Date: 17:08:24, Montag, 3. März 2008
Message:
minor fix for addExecutablePartitions
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java


Revision: 3162
Author: prosi
Date: 17:59:18, Freitag, 29. Februar 2008
Message:
new http to file jmf servlet
----
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFPartTest.java


Revision: 3161
Author: mucha
Date: 17:52:03, Freitag, 29. Februar 2008
Message:
change version number to 49
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java
Modified : /trunk/JDFLibJ/version.properties


Revision: 3160
Author: prosi
Date: 16:49:44, Freitag, 29. Februar 2008
Message:
npe fix in fitslisttype
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFAbstractState.java


Revision: 3159
Author: prosi
Date: 13:30:28, Freitag, 29. Februar 2008
Message:
General speedup, part consistency test
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VString.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDocUserData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java


Revision: 3151
Author: mucha
Date: 11:28:00, Donnerstag, 28. Februar 2008
Message:
replace call to deprecated function
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEvaluation.java


Revision: 3150
Author: mucha
Date: 11:04:09, Donnerstag, 28. Februar 2008
Message:
cosmetics
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/NColorTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFDeviceInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueEntryTest.java




>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD500) && !lbtype(JDFLIBJ_2.1.3BLD490)}" -print
.\auto\JDFAutoMessageService.java@@\main\56
.\auto\JDFAutoModuleStatus.java@@\main\55
.\auto\JDFAutoPart.java@@\main\52
.\auto\JDFAutoQueueFilter.java@@\main\34
.\core\JDFAudit.java@@\main\92
.\core\JDFDoc.java@@\main\78
.\core\JDFElement.java@@\main\246
.\core\JDFParser.java@@\main\48
.\core\JDFResourceLink.java@@\main\142
.\core\KElement.java@@\main\259
.\core\VString.java@@\main\41
.\core\XMLDoc.java@@\main\92
.\core\XMLDocUserData.java@@\main\30
.\goldenticket@@\main\3
.\goldenticket\BaseGoldenTicket.java@@\main\7
.\goldenticket\JMFGoldenTicket.java@@\main\3
.\goldenticket\MISCPGoldenTicket.java@@\main\8
.\goldenticket\MISGoldenTicket.java@@\main\8
.\goldenticket\MISPreGoldenTicket.java@@\main\1
.\goldenticket\ProductGoldenTicket.java@@\main\4
.\jmf\JDFJMF.java@@\main\68
.\jmf\JDFMessage.java@@\main\75
.\jmf\JDFPipeParams.java@@\main\22
.\jmf\JDFQueue.java@@\main\31
.\jmf\JDFQueueEntry.java@@\main\27
.\jmf\JDFQueueSubmissionParams.java@@\main\16
.\node\JDFNode.java@@\main\267
.\pool\JDFResourceLinkPool.java@@\main\80
.\pool\JDFStatusPool.java@@\main\46
.\resource\devicecapability\JDFAbstractState.java@@\main\57
.\resource\devicecapability\JDFDevCap.java@@\main\57
.\resource\devicecapability\JDFDevCaps.java@@\main\44
.\resource\devicecapability\JDFDeviceCap.java@@\main\55
.\resource\devicecapability\JDFEvaluation.java@@\main\28
.\resource\devicecapability\JDFNameState.java@@\main\34
.\resource\JDFModuleStatus.java@@\main\8
.\resource\JDFPhaseTime.java@@\main\30
.\resource\JDFResource.java@@\main\232
.\resource\process\JDFAssemblySection.java@@\main\5
.\resource\process\JDFColorantControl.java@@\main\24
.\resource\process\JDFUsageCounter.java@@\main\4
.\util@@\main\25
.\util\ContainerUtil.java@@\main\4
.\util\DumpDir.java@@\main\1
.\util\JDFMerge.java@@\main\22
.\util\JDFSpawn.java@@\main\24
.\util\MimeUtil.java@@\main\19
.\util\MultiModuleStatusCounter.java@@\main\3
.\util\QueueHotFolderListener.java@@\main\2
.\util\StatusCounter.java@@\main\13
.\util\StringUtil.java@@\main\68
.\util\UrlUtil.java@@\main\15
.\util\VectorMap.java@@\main\3
___________________________________________________________


Label JDFLIBJ_2.1.3BLD490 (28.02.2008)

Revision: 3151
Author: mucha
Date: 11:28:00, Donnerstag, 28. Februar 2008
Message:
replace call to deprecated function
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEvaluation.java


Revision: 3148
Author: prosi
Date: 19:14:05, Mittwoch, 27. Februar 2008
Message:
bug fix for xpath with //
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java


Revision: 3147
Author: prosi
Date: 15:31:48, Mittwoch, 27. Februar 2008
Message:
editor fixes for evaluating states in Devicecaps
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java


Revision: 3146
Author: prosi
Date: 11:15:53, Mittwoch, 27. Februar 2008
Message:
fix Lot handling in partamount
fix handling of direct attributes of JMF in capabilities
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFPartAmount.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java


Revision: 3141
Author: prosi
Date: 17:23:01, Dienstag, 26. Februar 2008
Message:
fix for resourcmdparams.applyResourceCommand (JDFNode parentNode) when partitioned
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java


Revision: 3138
Author: prosi
Date: 10:19:31, Montag, 25. Februar 2008
Message:
removal of requirement for processusage of the "Spine" resources
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/CheckJDF.java
Added : /trunk/JDFLibJ/apps/org/cip4/jdflib/checkjdf.xsl
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java


Revision: 3137
Author: biskey
Date: 20:21:09, Samstag, 23. Februar 2008
Message:
Cleaned up the code when choosing different ICS levels.
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java


Revision: 3134
Author: prosi
Date: 17:54:21, Donnerstag, 21. Februar 2008
Message:
Bambi proxy update
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java


Revision: 3132
Author: prosi
Date: 08:45:13, Donnerstag, 21. Februar 2008
Message:
Bambi proxy update
caps for jmf cce fix
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/ICapabilityElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFAbstractState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java


Revision: 3130
Author: prosi
Date: 08:25:52, Mittwoch, 20. Februar 2008
Message:
Bambi Cleanup
----
DModified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFParser.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java


Revision: 3124
Author: prosi
Date: 18:59:02, Freitag, 15. Februar 2008
Message:
radical bambi overhaul - intermediate step
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFConstants.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java


Revision: 3122
Author: prosi
Date: 18:52:29, Donnerstag, 14. Februar 2008
Message:
added JDFElement.getComment(String name)
fixed cce in device capabilities
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/IDeviceCapable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/VectorMap.java


Revision: 3119
Author: prosi
Date: 14:58:25, Mittwoch, 13. Februar 2008
Message:
BlankDimensionsX / Y swap
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeName.java


Revision: 3114
Author: prosi
Date: 11:04:55, Montag, 11. Februar 2008
Message:
yet another inconsistent spawn update
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java


Revision: 3113
Author: biskey
Date: 22:23:18, Freitag, 8. Februar 2008
Message:
User can now enter Base, MIS, and JMF default levels under the Golden Ticet tab.
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java


Revision: 3112
Author: prosi
Date: 09:14:20, Freitag, 8. Februar 2008
Message:
addexecutablepartitions fix
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java


Revision: 3107
Author: prosi
Date: 18:35:27, Donnerstag, 7. Februar 2008
Message:
pathological spawnrw if inconsistently spawned parts
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java


Revision: 3106
Author: prosi
Date: 15:28:56, Donnerstag, 7. Februar 2008
Message:

----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java


Revision: 3105
Author: prosi
Date: 10:44:48, Donnerstag, 7. Februar 2008
Message:
see http://www.cip4.org/jira/browse/EDITOR-161
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java


Revision: 3104
Author: prosi
Date: 09:23:33, Donnerstag, 7. Februar 2008
Message:
JDFNode speedup for getpartstatus
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HotFolder.java


Revision: 3103
Author: prosi
Date: 15:57:45, Mittwoch, 6. Februar 2008
Message:
better cleanup fix for incorrectly nested merges
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java


Revision: 3101
Author: prosi
Date: 13:15:11, Mittwoch, 6. Februar 2008
Message:
speedup JDFResource.getChildElementVector
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFSpawned.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java


Revision: 3100
Author: prosi
Date: 11:00:50, Mittwoch, 6. Februar 2008
Message:
cleanup fix for incorrectly nested merges
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java


Revision: 3099
Author: prosi
Date: 13:41:37, Dienstag, 5. Februar 2008
Message:
organize imports
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java


Revision: 3093
Author: biskey
Date: 18:55:55, Freitag, 1. Februar 2008
Message:
Cleaned up some warning messages by organizing imports.
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFPartAmount.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/EnumUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MultiModuleStatusCounter.java


Revision: 3091
Author: prosi
Date: 23:53:46, Donnerstag, 31. Januar 2008
Message:
add action tests for jmf
remove full jmf value from queueentrydetails
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCaps.java


Revision: 3090
Author: prosi
Date: 22:06:58, Donnerstag, 31. Januar 2008
Message:
add action tests for jmf
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/IDeviceCapable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java


Revision: 3089
Author: prosi
Date: 20:47:03, Donnerstag, 31. Januar 2008
Message:
validation AmountPool - PartAmount - ResLink/@Amount
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java


Revision: 3088
Author: prosi
Date: 06:07:49, Donnerstag, 31. Januar 2008
Message:
http://www.cip4.org/jira//browse/JDFJ-127
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/QueueHotFolder.java


Revision: 3083
Author: prosi
Date: 15:31:09, Freitag, 25. Januar 2008
Message:
more golden ticket and editor improvements
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFColorIntent.java



>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD490) && !lbtype(JDFLIBJ_2.1.3BLD488)}" -print
.\auto\JDFAutoMessageService.java@@\main\55
.\core\AttributeName.java@@\main\48
.\core\JDFComment.java@@\main\29
.\core\JDFConstants.java@@\main\73
.\core\JDFDoc.java@@\main\77
.\core\JDFElement.java@@\main\245
.\core\JDFParser.java@@\main\47
.\core\JDFPartAmount.java@@\main\28
.\core\JDFResourceLink.java@@\main\140
.\core\KElement.java@@\main\257
.\core\XMLDoc.java@@\main\90
.\datatypes\JDFIntegerRange.java@@\main\33
.\datatypes\JDFIntegerRangeList.java@@\main\34
.\goldenticket\BaseGoldenTicket.java@@\main\5
.\goldenticket\MISCPGoldenTicket.java@@\main\6
.\goldenticket\MISGoldenTicket.java@@\main\6
.\goldenticket\ProductGoldenTicket.java@@\main\3
.\ifaces\ICapabilityElement.java@@\main\2
.\ifaces\IDeviceCapable.java@@\main\2
.\jmf\JDFJMF.java@@\main\67
.\jmf\JDFJobPhase.java@@\main\23
.\jmf\JDFMessageService.java@@\main\17
.\jmf\JDFQueue.java@@\main\30
.\jmf\JDFQueueEntry.java@@\main\25
.\jmf\JDFQueueFilter.java@@\main\11
.\jmf\JDFRequestQueueEntryParams.java@@\main\13
.\jmf\JDFResourceCmdParams.java@@\main\26
.\jmf\JDFStatusQuParams.java@@\main\17
.\node\JDFNode.java@@\main\265
.\node\JDFSpawned.java@@\main\27
.\resource\devicecapability\JDFAbstractState.java@@\main\55
.\resource\devicecapability\JDFDevCap.java@@\main\56
.\resource\devicecapability\JDFDevCaps.java@@\main\43
.\resource\devicecapability\JDFDeviceCap.java@@\main\54
.\resource\devicecapability\JDFEvaluation.java@@\main\27
.\resource\intent\JDFColorIntent.java@@\main\17
.\resource\JDFDevice.java@@\main\10
.\resource\JDFResource.java@@\main\231
.\resource\process\JDFColorPool.java@@\main\28
.\resource\process\JDFContentData.java@@\main\3
.\resource\process\JDFPageData.java@@\main\4
.\util\EnumUtil.java@@\main\3
.\util\FileUtil.java@@\main\6
.\util\HotFolder.java@@\main\3
.\util\JDFDate.java@@\main\48
.\util\JDFMerge.java@@\main\21
.\util\JDFSpawn.java@@\main\23
.\util\MimeUtil.java@@\main\18
.\util\MultiModuleStatusCounter.java@@\main\2
.\util\QueueHotFolder.java@@\main\5
.\util\StatusCounter.java@@\main\11
.\util\StringUtil.java@@\main\67
.\util\UrlUtil.java@@\main\14
.\util\VectorMap.java@@\main\2
___________________________________________________________


Label JDFLIBJ_2.1.3BLD488 (25.01.2008)

bug fix: AmountProduced wird falsch berechnet 2008/00944

>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD488) && !lbtype(JDFLIBJ_2.1.3BLD487)}" -print
.\datatypes\JDFAttributeMap.java@@\main\37
.\resource\JDFResource.java@@\main\230
___________________________________________________________


Label JDFLIBJ_2.1.3BLD487 (18.01.2008)

NPE in JDFResource.updateAmount when type is a proprietary type
cleanup and detect namespace typos
add frame archive for Spec/Archive

>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD487) && !lbtype(JDFLIBJ_2.1.3BLD486)}" -print
.\org\cip4\jdflib\core\JDFConstants.java@@\main\72
.\org\cip4\jdflib\core\JDFParser.java@@\main\46
.\org\cip4\jdflib\resource\JDFResource.java@@\main\229
___________________________________________________________


Label JDFLIBJ_2.1.3BLD486 (11.01.2008)

Performance JDF-Forwarding for resources with many partitions (Jan Hoppe)

>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD486) && !lbtype(JDFLIBJ_2.1.3BLD485)}" -print
.\core\JDFResourceLink.java@@\main\139
___________________________________________________________


Label JDFLIBJ_2.1.3BLD485 (09.01.2008)

bug fix: did not work with umlaut in path names (Bernd Neumann)
(bei urltofile im falle von Misserfolg zusätzlich ein new File(inputstring) versuchen)


>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD485) && !lbtype(JDFLIBJ_2.1.3BLD484)}" -print
.\util\UrlUtil.java@@\main\13
___________________________________________________________


Label JDFLIBJ_2.1.3BLD484 (04.01.2008)

correctly handle nested rw spawns (Part 2)
deprecated JMF elements not caught correctly
checkjdf commandline: now correctly handles check for completeness
Editor: cleanup of devcap output for JMF
cleanup statuscounter so that phaseXXX is written whenever XXX is written
add fixNPage to RunList to fix NPage values at all levels
fix cce in capabilities when states are direct children of devicecap rather than devcap
add KElement.removeExtensions to remove specifc ns traits
fix for non jdf namespace roots in getjdfroot
fixed fitsValue if no allowedvaluelist is specified

>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD484) && !lbtype(JDFLIBJ_2.1.3BLD483)}" -print
.\core\AttributeName.java@@\main\47
.\core\JDFConstants.java@@\main\71
.\core\JDFDoc.java@@\main\76
.\core\JDFElement.java@@\main\244
.\core\JDFNodeInfo.java@@\main\57
.\core\KElement.java@@\main\256
.\datatypes\VJDFAttributeMap.java@@\main\30
.\goldenticket\BaseGoldenTicket.java@@\main\4
.\goldenticket\MISCPGoldenTicket.java@@\main\5
.\goldenticket\MISGoldenTicket.java@@\main\5
.\jmf\JDFDeviceInfo.java@@\main\23
.\jmf\JDFJMF.java@@\main\66
.\jmf\JDFJobPhase.java@@\main\22
.\jmf\JDFMessage.java@@\main\74
.\jmf\JDFQueueEntry.java@@\main\24
.\jmf\JDFQueueEntryDef.java@@\main\13
.\jmf\JDFQueueFilter.java@@\main\10
.\jmf\JDFResponse.java@@\main\30
.\resource\devicecapability\JDFAbstractState.java@@\main\54
.\resource\devicecapability\JDFDevCap.java@@\main\55
.\resource\devicecapability\JDFDevCaps.java@@\main\42
.\resource\devicecapability\JDFDeviceCap.java@@\main\53
.\resource\devicecapability\JDFDurationState.java@@\main\28
.\resource\JDFPhaseTime.java@@\main\29
.\resource\process\JDFFileSpec.java@@\main\33
.\resource\process\JDFRunList.java@@\main\56
.\util@@\main\24
.\util\EnumUtil.java@@\main\2
.\util\MimeUtil.java@@\main\17
.\util\MultiModuleStatusCounter.java@@\main\1
.\util\MyArgs.java@@\main\28
.\util\QueueHotFolder.java@@\main\4
.\util\StatusCounter.java@@\main\10
.\util\StatusUtil.java@@\main\10
___________________________________________________________


Label JDFLIBJ_2.1.3BLD483 (04.01.2008)

correctly handle nested rw spawns (Part 1)
add gray box types to JDFNode.EnumType

>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD483) && !lbtype(JDFLIBJ_2.1.3BLD482)}" -print
.\node\JDFNode.java@@\main\263
.\util\JDFMerge.java@@\main\20
___________________________________________________________
