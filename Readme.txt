___________________________________________________________


Label JDFLIBJ_2.1.4BLD580 (07.10.2009)

Signa resourclink linking for postpress 
+ cleanup of javadoc
+ minor schema fixes
+ additional test classes for Gallus label print
+ added parse to XMLDoc (not only JDFDoc)

.\src\org\cip4\jdflib\auto\JDFAutoAssembly.java@@\main\36
.\src\org\cip4\jdflib\auto\JDFAutoAssemblySection.java@@\main\36
.\src\org\cip4\jdflib\auto\JDFAutoConventionalPrintingParams.java@@\main\80
.\src\org\cip4\jdflib\core\JDFElement.java@@\main\261
.\src\org\cip4\jdflib\core\JDFResourceLink.java@@\main\155
.\src\org\cip4\jdflib\core\XMLDoc.java@@\main\103
.\src\org\cip4\jdflib\datatypes\JDFIntegerList.java@@\main\22
.\src\org\cip4\jdflib\datatypes\JDFNumList.java@@\main\38
.\src\org\cip4\jdflib\elementwalker\FixVersion.java@@\main\3
.\src\org\cip4\jdflib\elementwalker\LinkRefFinder.java@@\main\2
.\src\org\cip4\jdflib\extensions\XJDF20.java@@\main\8
.\src\org\cip4\jdflib\goldenticket\BaseGoldenTicket.java@@\main\21
.\src\org\cip4\jdflib\goldenticket\MISCPGoldenTicket.java@@\main\17
.\src\org\cip4\jdflib\goldenticket\MISGoldenTicket.java@@\main\19
.\src\org\cip4\jdflib\node\JDFNode.java@@\main\287
.\src\org\cip4\jdflib\pool\JDFAuditPool.java@@\main\113
.\src\org\cip4\jdflib\pool\JDFResourceLinkPool.java@@\main\90
.\src\org\cip4\jdflib\resource\process\JDFEmployee.java@@\main\22
.\src\org\cip4\jdflib\util\FileUtil.java@@\main\17
.\src\org\cip4\jdflib\util\StatusCounter.java@@\main\26
.\test\org\cip4\jdflib\core\JDFResourceLinkTest.java@@\main\39
.\test\org\cip4\jdflib\core\XMLDocTest.java@@\main\61
.\test\org\cip4\jdflib\node\JDFNodeTest.java@@\main\133
.\test\org\cip4\jdflib\util\FileUtilTest.java@@\main\14

___________________________________________________________


Label JDFLIBJ_2.1.4BLD572 (07.10.2009) (dummy - aus versehen gelabelt)
___________________________________________________________

Label JDFLIBJ_2.1.4BLD571 (10.09.2009)


Kommentar: JDFNode.getPartStatus – evaluate lower leaves


Revision: 4100
Author: prosi
Date: 18:47:58, Mittwoch, 9. September 2009
Message:

----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java

Revision: 4097
Author: prosi
Date: 17:43:50, Mittwoch, 9. September 2009
Message:
JDFNode.getPartStatus() now evaluates intermediate nodes all the way down to the input partmap
utf-8 test changes
javadoc comments
----
Modified : /trunk/JDFLibJ/.classpath
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/SpawnJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/xjdfwalker/XJDFToJDFConverter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/EnumUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/extensions/XJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFFilespecTest.java


>ct find . -version "{lbtype(JDFLIBJ_2.1.4BLD571) && !lbtype(JDFLIBJ_2.1.4BLD570)}" -print
.\extensions\XJDF20.java@@\main\7
.\extensions\xjdfwalker\XJDFToJDFConverter.java@@\main\5
.\goldenticket\MISGoldenTicket.java@@\main\18
.\goldenticket\PackagingGoldenTicket.java@@\main\2
.\goldenticket\ProductGoldenTicket.java@@\main\9
.\jmf\JDFQueue.java@@\main\40
.\node\JDFNode.java@@\main\286
.\util\EnumUtil.java@@\main\7
.\util\JDFMerge.java@@\main\36
___________________________________________________________


Label JDFLIBJ_2.1.4BLD570 (04.09.2009)

There was no label 560 (only 56 on cip4 subversion) !!!

Revision: 4063
Author: prosi
Date: 18:26:49, Donnerstag, 3. September 2009
Message:
new class LinkRefFinder.java
javadoc cleanup
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/LinkRefFinder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/AllJDFLibTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/VElementTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/elementwalker/LinkRefFinderTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFResourceTest.java

Revision: 4060
Author: prosi
Date: 16:01:34, Donnerstag, 3. September 2009
Message:
update of tests to UTF-8
javadoc cleanup
----
Modified : /trunk/JDFLibJ/build.xml
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFConstants.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFCustomerMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFPartAmount.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFRefElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ThreadUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/VElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFFilespecTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/QueueHotFolderTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/UrlUtilTest.java

Revision: 4059
Author: StefanMeissner
Date: 11:22:07, Donnerstag, 3. September 2009
Message:

----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MyArgs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/PlatformUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/AllJDFLibTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/HotFolderTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MyArgsTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/PlatformUtilTest.java

Revision: 4058
Author: StefanMeissner
Date: 17:13:02, Mittwoch, 2. September 2009
Message:
made unit tests unix compatible using an ubuntu 9.04 desktop system;
extended PlatformUtil
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/PlatformUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/JDFValidator.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/CheckJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFFilespecTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/HotFolderTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/util/PlatformUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/UrlUtilTest.java

Revision: 4055
Author: prosi
Date: 21:00:25, Montag, 10. August 2009
Message:
refactor mime support
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/mime
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/mime/BodyPartHelper.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/mime/MimeHelper.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/mime/MimeReader.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/mime/MimeWriter.java

Revision: 4051
Author: prosi
Date: 20:40:41, Montag, 10. August 2009
Message:
clean up warnings
add better buffered stream detection in StreamUtil
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/TestJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AtrInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AtrInfoTable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourceLinkPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourcePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFBooleanState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFDropItemIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFProductionIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCutBlock.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFileSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRunList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSourceResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTransferCurve.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ByteArrayIOStream.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FastFiFo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HashUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDuration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StreamUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/JDFValidator.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/AutomatedLayoutTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFFilespecTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/ByteArrayIOStreamTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/UrlUtilTest.java

Revision: 4032
Author: mucha
Date: 17:16:26, Donnerstag, 23. Juli 2009
Message:

----
Modified : /trunk/JDFLibJ/version.properties

Revision: 4030
Author: prosi
Date: 17:06:43, Donnerstag, 23. Juli 2009
Message:

----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreview.java

Revision: 4029
Author: prosi
Date: 16:52:31, Donnerstag, 23. Juli 2009
Message:
removed partusage
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreview.java

Revision: 4028
Author: mucha
Date: 16:24:19, Donnerstag, 23. Juli 2009
Message:

----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMetadataMap.java

Revision: 4027
Author: mucha
Date: 16:18:06, Donnerstag, 23. Juli 2009
Message:
cosmetic for icsLevel
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/IDPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java

Revision: 4026
Author: mucha
Date: 16:16:31, Donnerstag, 23. Juli 2009
Message:
add BASEORDRESET
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeName.java

Revision: 4025
Author: mucha
Date: 16:14:04, Donnerstag, 23. Juli 2009
Message:
new auto files which correspond to current schema
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElementName.java

Revision: 4024
Author: mucha
Date: 16:12:46, Donnerstag, 23. Juli 2009
Message:
new auto files which correspond to current schema
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/ComplexTypeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAcknowledge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoActionPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdded.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAddress.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdvancedParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalSuccess.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArgumentValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDelivery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssembly.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssemblySection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssetListCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAuthenticationCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAuthenticationQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAuthenticationResp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAutomatedOverPrintParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeCompParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeReproParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBasicPreflightTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBendingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBinderySignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBlockPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBookCase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBooleanEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxToBoxDifference.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBufferParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundleItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundlingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBusinessInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoByteMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCCITTFaxParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCIELABMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCaseMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCasingInParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollatingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollectingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorControlStrip.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorMeasurementConditions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceSubstitute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantZoneDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorsUsed.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComChannel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCommand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCompany.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContact.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContactCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContainer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentMetaData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoControllerFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoConventionalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoConvertingConfig.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCostCenter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCounterReset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoverApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCrease.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreasingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreated.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreditCard.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutBlock.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBMergeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBRules.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSchema.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSelection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDCTParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDateTimeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeleted.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDensityMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDependencies.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCapPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevelopingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNSpace.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDieLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDieLayoutProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisjointing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroupPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisposition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDividingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDrop.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItemIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDurationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicInput.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEdgeGluing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoElementColorParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmboss.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployeeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheetGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEnumerationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoErrorData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEvent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExposedMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExternalImpositionTemplate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExtraValues.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFCNKey.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeatureAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeaturePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeeder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeederQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeedingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFitPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlateParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushResourceParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushedResources.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldOperation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFolderProduction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFormatConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangCmdFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangQuFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGatheringParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGeneralID.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHardCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHeadBandApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHole.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFinishing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFolding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPHoleMaking.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPTrimming.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIcon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIconList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentical.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentificationField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompression.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompressionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageReplacementParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageSetterParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInk.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneCalculationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsert.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIntegerEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretedPDLData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIsPresentEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJBIG2Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFController.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJPEG2000Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJacketingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoKnownMsgQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLZWParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLabelingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLocation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongSlit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongitudinalRibbonOperationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMISDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMacroPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoManualLaborParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkActivation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMatrixEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaLayers.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaSource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMerged.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMilestone.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMiscConsumable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModified.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModifyNodeCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMoveResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMsgFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNameEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoResp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOCGControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObjectModel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObjectResolution.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObservationTarget.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOccupation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOrderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFPathEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFToPSConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFXParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLResourceAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroupOccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPROccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRuleAttr.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPSToPDFConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageAssignParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageAssignedList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPallet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPalletizingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPartStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPayment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerformance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPixelColorant.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlaceHolderResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlateCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPositionObj.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAnalysis.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightConstraint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstanceDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInventory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReport.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReportRulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreview.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreviewGenerationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPricing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintConditionColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintRollingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionSubPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPublishingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityMeasurement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQuery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDefList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPosParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPriParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRectangleEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRefAnchor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterRibbon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegistration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoveLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoved.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRenderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRepeatDesc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceDefinitionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourcePullParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResponse.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoReturnQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRollStand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRuleLength.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRunList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScanParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScavengerArea.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScore.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreenSelector.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeDefProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeTemplate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShiftPoint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShrinkingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShutDownCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignatureCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSizeIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSoftCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpawned.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpinePreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpineTapingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStopPersChParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringListValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripCellParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrippingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubmissionMethods.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscription.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscriptionFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscriptionInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSystemTimeSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFEmbeddedFile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFFormatParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFtag.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabDimensions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestRef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThinPDFParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurve.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurvePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurveSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferFunctionControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrapRegion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingOrder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrigger.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrimmingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUpdateJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUsageCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValueLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoVerificationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWakeUpCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWebInlineFinishingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoXYPairEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutocall.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutochoice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutomacro.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutonot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutootherwise.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutowhen.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoxor.java

Revision: 4023
Author: prosi
Date: 15:50:02, Donnerstag, 23. Juli 2009
Message:
added metadatamap
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMetadataMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRunList.java

Revision: 4022
Author: prosi
Date: 15:49:27, Donnerstag, 23. Juli 2009
Message:
added metadatamap
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElementName.java

Revision: 4020
Author: prosi
Date: 14:42:07, Donnerstag, 23. Juli 2009
Message:

----
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java

Revision: 4015
Author: prosi
Date: 12:35:39, Donnerstag, 23. Juli 2009
Message:

----
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java

Revision: 4014
Author: prosi
Date: 11:42:01, Donnerstag, 23. Juli 2009
Message:
minor cleanup + version tag
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFParser.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/SkipInputStream.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ThreadUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/SkipInputStreamTest.java

Revision: 4009
Author: prosi
Date: 19:04:35, Mittwoch, 22. Juli 2009
Message:
schema fixes + updates from bambi
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeName.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/VJDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/xjdfwalker/XJDFToJDFConverter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ByteArrayIOStream.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FastFiFo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/ProcessRunTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/ByteArrayIOStreamTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFDateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java

Revision: 4003
Author: prosi
Date: 10:58:56, Donnerstag, 16. Juli 2009
Message:
keepalive http enhancements + merge bug in nested spawn
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFResourceTest.java

Revision: 3999
Author: prosi
Date: 11:08:36, Dienstag, 14. Juli 2009
Message:
Bambi Messaging enhancements
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java

Revision: 3998
Author: prosi
Date: 11:02:24, Dienstag, 14. Juli 2009
Message:

----
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFDeviceFilterTest.java

Revision: 3997
Author: prosi
Date: 10:26:10, Dienstag, 14. Juli 2009
Message:
Bambi Messaging enhancements
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/TestJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFCustomerInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/FixVersion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/xjdfwalker/XJDFToJDFConverter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFIDInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNewJDFQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIdentical.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/EnumUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ThreadUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/AllJDFLibTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFCustomerInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFAttributeMapTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFDeviceInfoTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFIDInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueFilterTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/UrlUtilTest.java

Revision: 3985
Author: prosi
Date: 15:13:05, Montag, 6. Juli 2009
Message:
all unit tests ok now
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFDeviceCapTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java

Revision: 3982
Author: prosi
Date: 14:02:44, Montag, 6. Juli 2009
Message:
Bambi and editor related enhancements
Quefiler/UpdateGranularity implemented
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFAcknowledge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResponse.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ThreadUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFJMFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueFilterTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFPersonTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFDateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java

Revision: 3975
Author: prosi
Date: 18:33:46, Donnerstag, 18. Juni 2009
Message:
fix array out of bounds
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceInfo.java



>ct find . -version "{lbtype(JDFLIBJ_2.1.4BLD570) && !lbtype(JDFLIBJ_2.1.4BLD551)}" -print
.\auto@@\main\47
.\auto\JDFAutoAcknowledge.java@@\main\56
.\auto\JDFAutoAction.java@@\main\31
.\auto\JDFAutoActionPool.java@@\main\25
.\auto\JDFAutoAdded.java@@\main\36
.\auto\JDFAutoAddress.java@@\main\65
.\auto\JDFAutoAdhesiveBinding.java@@\main\57
.\auto\JDFAutoAdhesiveBindingParams.java@@\main\58
.\auto\JDFAutoAdvancedParams.java@@\main\64
.\auto\JDFAutoAmountPool.java@@\main\46
.\auto\JDFAutoAncestor.java@@\main\70
.\auto\JDFAutoAncestorPool.java@@\main\46
.\auto\JDFAutoand.java@@\main\16
.\auto\JDFAutoApprovalDetails.java@@\main\23
.\auto\JDFAutoApprovalParams.java@@\main\51
.\auto\JDFAutoApprovalPerson.java@@\main\70
.\auto\JDFAutoApprovalSuccess.java@@\main\63
.\auto\JDFAutoArgumentValue.java@@\main\27
.\auto\JDFAutoArtDelivery.java@@\main\79
.\auto\JDFAutoArtDeliveryIntent.java@@\main\75
.\auto\JDFAutoAssembly.java@@\main\35
.\auto\JDFAutoAssemblySection.java@@\main\35
.\auto\JDFAutoAssetListCreationParams.java@@\main\32
.\auto\JDFAutoAuthenticationCmdParams.java@@\main\2
.\auto\JDFAutoAuthenticationQuParams.java@@\main\2
.\auto\JDFAutoAuthenticationResp.java@@\main\2
.\auto\JDFAutoAutomatedOverPrintParams.java@@\main\64
.\auto\JDFAutoBand.java@@\main\47
.\auto\JDFAutoBarcode.java@@\main\42
.\auto\JDFAutoBarcodeCompParams.java@@\main\17
.\auto\JDFAutoBarcodeDetails.java@@\main\12
.\auto\JDFAutoBarcodeProductionParams.java@@\main\4
.\auto\JDFAutoBarcodeReproParams.java@@\main\21
.\auto\JDFAutoBasicPreflightTest.java@@\main\34
.\auto\JDFAutoBendingParams.java@@\main\14
.\auto\JDFAutoBinderySignature.java@@\main\36
.\auto\JDFAutoBindingIntent.java@@\main\70
.\auto\JDFAutoBindingQualityParams.java@@\main\21
.\auto\JDFAutoBindItem.java@@\main\51
.\auto\JDFAutoBindList.java@@\main\45
.\auto\JDFAutoBlockPreparationParams.java@@\main\60
.\auto\JDFAutoBookCase.java@@\main\56
.\auto\JDFAutoBooleanEvaluation.java@@\main\27
.\auto\JDFAutoBoxApplication.java@@\main\17
.\auto\JDFAutoBoxArgument.java@@\main\27
.\auto\JDFAutoBoxFoldAction.java@@\main\21
.\auto\JDFAutoBoxFoldingParams.java@@\main\23
.\auto\JDFAutoBoxPackingParams.java@@\main\48
.\auto\JDFAutoBoxToBoxDifference.java@@\main\26
.\auto\JDFAutoBufferParams.java@@\main\49
.\auto\JDFAutoBundle.java@@\main\60
.\auto\JDFAutoBundleItem.java@@\main\58
.\auto\JDFAutoBundlingParams.java@@\main\23
.\auto\JDFAutoBusinessInfo.java@@\main\43
.\auto\JDFAutoByteMap.java@@\main\76
.\auto\JDFAutocall.java@@\main\21
.\auto\JDFAutoCaseMakingParams.java@@\main\50
.\auto\JDFAutoCasingInParams.java@@\main\53
.\auto\JDFAutoCCITTFaxParams.java@@\main\22
.\auto\JDFAutoChangedAttribute.java@@\main\45
.\auto\JDFAutoChangedPath.java@@\main\27
.\auto\JDFAutoChannelBinding.java@@\main\53
.\auto\JDFAutoChannelBindingParams.java@@\main\54
.\auto\JDFAutochoice.java@@\main\24
.\auto\JDFAutoCIELABMeasuringField.java@@\main\73
.\auto\JDFAutoCoilBinding.java@@\main\53
.\auto\JDFAutoCoilBindingParams.java@@\main\60
.\auto\JDFAutoCollatingItem.java@@\main\31
.\auto\JDFAutoCollectingParams.java@@\main\37
.\auto\JDFAutoColor.java@@\main\79
.\auto\JDFAutoColorantAlias.java@@\main\65
.\auto\JDFAutoColorantControl.java@@\main\74
.\auto\JDFAutoColorantZoneDetails.java@@\main\58
.\auto\JDFAutoColorControlStrip.java@@\main\70
.\auto\JDFAutoColorCorrectionOp.java@@\main\52
.\auto\JDFAutoColorCorrectionParams.java@@\main\69
.\auto\JDFAutoColorIntent.java@@\main\55
.\auto\JDFAutoColorMeasurementConditions.java@@\main\56
.\auto\JDFAutoColorPool.java@@\main\67
.\auto\JDFAutoColorSpaceConversionOp.java@@\main\64
.\auto\JDFAutoColorSpaceConversionParams.java@@\main\74
.\auto\JDFAutoColorSpaceSubstitute.java@@\main\61
.\auto\JDFAutoColorsUsed.java@@\main\23
.\auto\JDFAutoComChannel.java@@\main\67
.\auto\JDFAutoCommand.java@@\main\27
.\auto\JDFAutoComment.java@@\main\65
.\auto\JDFAutoCompany.java@@\main\66
.\auto\JDFAutoComponent.java@@\main\83
.\auto\JDFAutoContact.java@@\main\68
.\auto\JDFAutoContactCopyParams.java@@\main\58
.\auto\JDFAutoContainer.java@@\main\21
.\auto\JDFAutoContentData.java@@\main\17
.\auto\JDFAutoContentList.java@@\main\16
.\auto\JDFAutoContentMetaData.java@@\main\2
.\auto\JDFAutoContentObject.java@@\main\72
.\auto\JDFAutoControllerFilter.java@@\main\2
.\auto\JDFAutoConventionalPrintingParams.java@@\main\79
.\auto\JDFAutoConvertingConfig.java@@\main\2
.\auto\JDFAutoCostCenter.java@@\main\58
.\auto\JDFAutoCounterReset.java@@\main\41
.\auto\JDFAutoCoverApplicationParams.java@@\main\53
.\auto\JDFAutoCrease.java@@\main\64
.\auto\JDFAutoCreasingParams.java@@\main\48
.\auto\JDFAutoCreated.java@@\main\44
.\auto\JDFAutoCreateLink.java@@\main\15
.\auto\JDFAutoCreateResource.java@@\main\16
.\auto\JDFAutoCreditCard.java@@\main\41
.\auto\JDFAutoCustomerInfo.java@@\main\57
.\auto\JDFAutoCustomerMessage.java@@\main\27
.\auto\JDFAutoCut.java@@\main\66
.\auto\JDFAutoCutBlock.java@@\main\61
.\auto\JDFAutoCutMark.java@@\main\72
.\auto\JDFAutoCuttingParams.java@@\main\49
.\auto\JDFAutoCylinderLayout.java@@\main\18
.\auto\JDFAutoCylinderLayoutPreparationParams.java@@\main\14
.\auto\JDFAutoCylinderPosition.java@@\main\15
.\auto\JDFAutoDateTimeEvaluation.java@@\main\28
.\auto\JDFAutoDBMergeParams.java@@\main\52
.\auto\JDFAutoDBRules.java@@\main\37
.\auto\JDFAutoDBSchema.java@@\main\52
.\auto\JDFAutoDBSelection.java@@\main\49
.\auto\JDFAutoDCTParams.java@@\main\30
.\auto\JDFAutoDeleted.java@@\main\23
.\auto\JDFAutoDeliveryIntent.java@@\main\81
.\auto\JDFAutoDeliveryParams.java@@\main\68
.\auto\JDFAutoDensityMeasuringField.java@@\main\70
.\auto\JDFAutoDependencies.java@@\main\24
.\auto\JDFAutoDevCap.java@@\main\53
.\auto\JDFAutoDevCapPool.java@@\main\15
.\auto\JDFAutoDevCaps.java@@\main\58
.\auto\JDFAutoDevelopingParams.java@@\main\52
.\auto\JDFAutoDevice.java@@\main\73
.\auto\JDFAutoDeviceCap.java@@\main\55
.\auto\JDFAutoDeviceFilter.java@@\main\56
.\auto\JDFAutoDeviceInfo.java@@\main\68
.\auto\JDFAutoDeviceList.java@@\main\47
.\auto\JDFAutoDeviceMark.java@@\main\52
.\auto\JDFAutoDeviceNColor.java@@\main\45
.\auto\JDFAutoDeviceNSpace.java@@\main\67
.\auto\JDFAutoDieLayout.java@@\main\17
.\auto\JDFAutoDieLayoutProductionParams.java@@\main\2
.\auto\JDFAutoDigitalDeliveryParams.java@@\main\34
.\auto\JDFAutoDigitalMedia.java@@\main\29
.\auto\JDFAutoDigitalPrintingParams.java@@\main\79
.\auto\JDFAutoDisjointing.java@@\main\76
.\auto\JDFAutoDisplayGroup.java@@\main\24
.\auto\JDFAutoDisplayGroupPool.java@@\main\24
.\auto\JDFAutoDisposition.java@@\main\36
.\auto\JDFAutoDividingParams.java@@\main\58
.\auto\JDFAutoDrop.java@@\main\78
.\auto\JDFAutoDropIntent.java@@\main\72
.\auto\JDFAutoDropItem.java@@\main\66
.\auto\JDFAutoDropItemIntent.java@@\main\59
.\auto\JDFAutoDurationEvaluation.java@@\main\28
.\auto\JDFAutoDynamicField.java@@\main\61
.\auto\JDFAutoDynamicInput.java@@\main\54
.\auto\JDFAutoEdgeGluing.java@@\main\43
.\auto\JDFAutoElementColorParams.java@@\main\37
.\auto\JDFAutoEmboss.java@@\main\55
.\auto\JDFAutoEmbossingIntent.java@@\main\46
.\auto\JDFAutoEmbossingItem.java@@\main\44
.\auto\JDFAutoEmbossingParams.java@@\main\49
.\auto\JDFAutoEmployee.java@@\main\65
.\auto\JDFAutoEmployeeDef.java@@\main\40
.\auto\JDFAutoEndSheet.java@@\main\64
.\auto\JDFAutoEndSheetGluingParams.java@@\main\62
.\auto\JDFAutoEnumerationEvaluation.java@@\main\27
.\auto\JDFAutoError.java@@\main\48
.\auto\JDFAutoErrorData.java@@\main\4
.\auto\JDFAutoEvent.java@@\main\21
.\auto\JDFAutoExposedMedia.java@@\main\77
.\auto\JDFAutoExternalImpositionTemplate.java@@\main\15
.\auto\JDFAutoExtraValues.java@@\main\12
.\auto\JDFAutoFCNKey.java@@\main\39
.\auto\JDFAutoFeatureAttribute.java@@\main\28
.\auto\JDFAutoFeaturePool.java@@\main\18
.\auto\JDFAutoFeeder.java@@\main\32
.\auto\JDFAutoFeederQualityParams.java@@\main\27
.\auto\JDFAutoFeedingParams.java@@\main\26
.\auto\JDFAutoFileAlias.java@@\main\65
.\auto\JDFAutoFileSpec.java@@\main\84
.\auto\JDFAutoFitPolicy.java@@\main\53
.\auto\JDFAutoFlateParams.java@@\main\22
.\auto\JDFAutoFlushedResources.java@@\main\16
.\auto\JDFAutoFlushQueueInfo.java@@\main\11
.\auto\JDFAutoFlushQueueParams.java@@\main\21
.\auto\JDFAutoFlushResourceParams.java@@\main\30
.\auto\JDFAutoFold.java@@\main\71
.\auto\JDFAutoFolderProduction.java@@\main\14
.\auto\JDFAutoFoldingIntent.java@@\main\66
.\auto\JDFAutoFoldingParams.java@@\main\74
.\auto\JDFAutoFoldOperation.java@@\main\9
.\auto\JDFAutoFontParams.java@@\main\68
.\auto\JDFAutoFontPolicy.java@@\main\60
.\auto\JDFAutoFormatConversionParams.java@@\main\50
.\auto\JDFAutoGangCmdFilter.java@@\main\11
.\auto\JDFAutoGangInfo.java@@\main\11
.\auto\JDFAutoGangQuFilter.java@@\main\11
.\auto\JDFAutoGatheringParams.java@@\main\47
.\auto\JDFAutoGeneralID.java@@\main\16
.\auto\JDFAutoGlue.java@@\main\73
.\auto\JDFAutoGlueApplication.java@@\main\70
.\auto\JDFAutoGlueLine.java@@\main\69
.\auto\JDFAutoGluingParams.java@@\main\51
.\auto\JDFAutoHardCoverBinding.java@@\main\48
.\auto\JDFAutoHeadBandApplicationParams.java@@\main\60
.\auto\JDFAutoHole.java@@\main\66
.\auto\JDFAutoHoleLine.java@@\main\50
.\auto\JDFAutoHoleList.java@@\main\62
.\auto\JDFAutoHoleMakingIntent.java@@\main\69
.\auto\JDFAutoHoleMakingParams.java@@\main\77
.\auto\JDFAutoIcon.java@@\main\50
.\auto\JDFAutoIconList.java@@\main\43
.\auto\JDFAutoIdentical.java@@\main\10
.\auto\JDFAutoIdentificationField.java@@\main\70
.\auto\JDFAutoIDInfo.java@@\main\26
.\auto\JDFAutoIDPCover.java@@\main\61
.\auto\JDFAutoIDPFinishing.java@@\main\54
.\auto\JDFAutoIDPFolding.java@@\main\58
.\auto\JDFAutoIDPHoleMaking.java@@\main\58
.\auto\JDFAutoIDPImageShift.java@@\main\46
.\auto\JDFAutoIDPJobSheet.java@@\main\59
.\auto\JDFAutoIDPLayout.java@@\main\60
.\auto\JDFAutoIDPrintingParams.java@@\main\77
.\auto\JDFAutoIDPStitching.java@@\main\70
.\auto\JDFAutoIDPTrimming.java@@\main\61
.\auto\JDFAutoImageCompression.java@@\main\64
.\auto\JDFAutoImageCompressionParams.java@@\main\60
.\auto\JDFAutoImageReplacementParams.java@@\main\76
.\auto\JDFAutoImageSetterParams.java@@\main\63
.\auto\JDFAutoImageShift.java@@\main\64
.\auto\JDFAutoInk.java@@\main\66
.\auto\JDFAutoInkZoneCalculationParams.java@@\main\65
.\auto\JDFAutoInkZoneProfile.java@@\main\62
.\auto\JDFAutoInsert.java@@\main\68
.\auto\JDFAutoInsertingIntent.java@@\main\55
.\auto\JDFAutoInsertingParams.java@@\main\61
.\auto\JDFAutoInsertList.java@@\main\57
.\auto\JDFAutoInsertSheet.java@@\main\76
.\auto\JDFAutoIntegerEvaluation.java@@\main\28
.\auto\JDFAutoInterpretedPDLData.java@@\main\36
.\auto\JDFAutoInterpretingParams.java@@\main\62
.\auto\JDFAutoIsPresentEvaluation.java@@\main\24
.\auto\JDFAutoJacketingParams.java@@\main\43
.\auto\JDFAutoJBIG2Params.java@@\main\12
.\auto\JDFAutoJDFController.java@@\main\38
.\auto\JDFAutoJDFService.java@@\main\48
.\auto\JDFAutoJMF.java@@\main\67
.\auto\JDFAutoJobField.java@@\main\50
.\auto\JDFAutoJobPhase.java@@\main\70
.\auto\JDFAutoJPEG2000Params.java@@\main\17
.\auto\JDFAutoKnownMsgQuParams.java@@\main\44
.\auto\JDFAutoLabelingParams.java@@\main\51
.\auto\JDFAutoLaminatingIntent.java@@\main\59
.\auto\JDFAutoLaminatingParams.java@@\main\56
.\auto\JDFAutoLayerDetails.java@@\main\41
.\auto\JDFAutoLayerList.java@@\main\47
.\auto\JDFAutoLayout.java@@\main\68
.\auto\JDFAutoLayoutElement.java@@\main\79
.\auto\JDFAutoLayoutElementPart.java@@\main\17
.\auto\JDFAutoLayoutElementProductionParams.java@@\main\16
.\auto\JDFAutoLayoutIntent.java@@\main\69
.\auto\JDFAutoLayoutPreparationParams.java@@\main\67
.\auto\JDFAutoLayoutShift.java@@\main\2
.\auto\JDFAutoLoc.java@@\main\22
.\auto\JDFAutoLocation.java@@\main\65
.\auto\JDFAutoLongFold.java@@\main\57
.\auto\JDFAutoLongGlue.java@@\main\65
.\auto\JDFAutoLongitudinalRibbonOperationParams.java@@\main\62
.\auto\JDFAutoLongPerforate.java@@\main\57
.\auto\JDFAutoLongSlit.java@@\main\57
.\auto\JDFAutoLot.java@@\main\15
.\auto\JDFAutoLZWParams.java@@\main\22
.\auto\JDFAutomacro.java@@\main\25
.\auto\JDFAutoMacroPool.java@@\main\24
.\auto\JDFAutoManualLaborParams.java@@\main\43
.\auto\JDFAutoMarkActivation.java@@\main\2
.\auto\JDFAutoMarkObject.java@@\main\79
.\auto\JDFAutoMatrixEvaluation.java@@\main\28
.\auto\JDFAutoMediaIntent.java@@\main\67
.\auto\JDFAutoMediaLayers.java@@\main\15
.\auto\JDFAutoMediaSource.java@@\main\74
.\auto\JDFAutoMerged.java@@\main\54
.\auto\JDFAutoMessage.java@@\main\54
.\auto\JDFAutoMessageService.java@@\main\61
.\auto\JDFAutoMilestone.java@@\main\3
.\auto\JDFAutoMiscConsumable.java@@\main\17
.\auto\JDFAutoMISDetails.java@@\main\29
.\auto\JDFAutoModified.java@@\main\41
.\auto\JDFAutoModifyNodeCmdParams.java@@\main\22
.\auto\JDFAutoModule.java@@\main\18
.\auto\JDFAutoModuleCap.java@@\main\17
.\auto\JDFAutoModulePhase.java@@\main\83
.\auto\JDFAutoModulePool.java@@\main\15
.\auto\JDFAutoModuleStatus.java@@\main\60
.\auto\JDFAutoMoveResource.java@@\main\11
.\auto\JDFAutoMsgFilter.java@@\main\60
.\auto\JDFAutoNameEvaluation.java@@\main\27
.\auto\JDFAutoNewComment.java@@\main\18
.\auto\JDFAutoNewJDFCmdParams.java@@\main\25
.\auto\JDFAutoNewJDFQuParams.java@@\main\23
.\auto\JDFAutoNodeInfo.java@@\main\81
.\auto\JDFAutoNodeInfoCmdParams.java@@\main\35
.\auto\JDFAutoNodeInfoQuParams.java@@\main\25
.\auto\JDFAutoNodeInfoResp.java@@\main\30
.\auto\JDFAutonot.java@@\main\16
.\auto\JDFAutoNotification.java@@\main\63
.\auto\JDFAutoNotificationDef.java@@\main\45
.\auto\JDFAutoNotificationFilter.java@@\main\51
.\auto\JDFAutoNumberEvaluation.java@@\main\29
.\auto\JDFAutoNumberingIntent.java@@\main\46
.\auto\JDFAutoNumberingParam.java@@\main\46
.\auto\JDFAutoNumberingParams.java@@\main\60
.\auto\JDFAutoNumberItem.java@@\main\51
.\auto\JDFAutoObjectResolution.java@@\main\64
.\auto\JDFAutoObservationTarget.java@@\main\46
.\auto\JDFAutoOccupation.java@@\main\53
.\auto\JDFAutoOCGControl.java@@\main\12
.\auto\JDFAutoor.java@@\main\16
.\auto\JDFAutoOrderingParams.java@@\main\53
.\auto\JDFAutootherwise.java@@\main\24
.\auto\JDFAutoPackingIntent.java@@\main\57
.\auto\JDFAutoPackingParams.java@@\main\56
.\auto\JDFAutoPageAssignedList.java@@\main\12
.\auto\JDFAutoPageAssignParams.java@@\main\2
.\auto\JDFAutoPageCell.java@@\main\57
.\auto\JDFAutoPageCondition.java@@\main\2
.\auto\JDFAutoPageData.java@@\main\33
.\auto\JDFAutoPageElement.java@@\main\13
.\auto\JDFAutoPageList.java@@\main\33
.\auto\JDFAutoPallet.java@@\main\49
.\auto\JDFAutoPalletizingParams.java@@\main\47
.\auto\JDFAutoPart.java@@\main\56
.\auto\JDFAutoPartStatus.java@@\main\49
.\auto\JDFAutoPayment.java@@\main\43
.\auto\JDFAutoPDFInterpretingParams.java@@\main\59
.\auto\JDFAutoPDFPathEvaluation.java@@\main\29
.\auto\JDFAutoPDFToPSConversionParams.java@@\main\70
.\auto\JDFAutoPDFXParams.java@@\main\28
.\auto\JDFAutoPDLCreationParams.java@@\main\16
.\auto\JDFAutoPDLResourceAlias.java@@\main\62
.\auto\JDFAutoPerforate.java@@\main\65
.\auto\JDFAutoPerforatingParams.java@@\main\47
.\auto\JDFAutoPerformance.java@@\main\39
.\auto\JDFAutoPerson.java@@\main\54
.\auto\JDFAutoPhaseTime.java@@\main\64
.\auto\JDFAutoPipeParams.java@@\main\55
.\auto\JDFAutoPixelColorant.java@@\main\43
.\auto\JDFAutoPlaceHolderResource.java@@\main\24
.\auto\JDFAutoPlasticCombBinding.java@@\main\53
.\auto\JDFAutoPlasticCombBindingParams.java@@\main\61
.\auto\JDFAutoPlateCopyParams.java@@\main\54
.\auto\JDFAutoPosition.java@@\main\31
.\auto\JDFAutoPositionObj.java@@\main\2
.\auto\JDFAutoPreflightAction.java@@\main\26
.\auto\JDFAutoPreflightAnalysis.java@@\main\46
.\auto\JDFAutoPreflightArgument.java@@\main\23
.\auto\JDFAutoPreflightConstraint.java@@\main\66
.\auto\JDFAutoPreflightDetail.java@@\main\67
.\auto\JDFAutoPreflightInstance.java@@\main\67
.\auto\JDFAutoPreflightInstanceDetail.java@@\main\63
.\auto\JDFAutoPreflightInventory.java@@\main\46
.\auto\JDFAutoPreflightParams.java@@\main\28
.\auto\JDFAutoPreflightProfile.java@@\main\47
.\auto\JDFAutoPreflightReport.java@@\main\36
.\auto\JDFAutoPreflightReportRulePool.java@@\main\29
.\auto\JDFAutoPRError.java@@\main\29
.\auto\JDFAutoPreview.java@@\main\71
.\auto\JDFAutoPreviewGenerationParams.java@@\main\63
.\auto\JDFAutoPRGroup.java@@\main\29
.\auto\JDFAutoPRGroupOccurrence.java@@\main\14
.\auto\JDFAutoPricing.java@@\main\68
.\auto\JDFAutoPrintCondition.java@@\main\31
.\auto\JDFAutoPrintConditionColor.java@@\main\33
.\auto\JDFAutoPrintRollingParams.java@@\main\22
.\auto\JDFAutoPRItem.java@@\main\29
.\auto\JDFAutoPROccurrence.java@@\main\15
.\auto\JDFAutoProcessRun.java@@\main\60
.\auto\JDFAutoProductionIntent.java@@\main\59
.\auto\JDFAutoProductionPath.java@@\main\19
.\auto\JDFAutoProductionSubPath.java@@\main\13
.\auto\JDFAutoProofingIntent.java@@\main\56
.\auto\JDFAutoProofingParams.java@@\main\73
.\auto\JDFAutoProofItem.java@@\main\55
.\auto\JDFAutoPRRule.java@@\main\27
.\auto\JDFAutoPRRuleAttr.java@@\main\21
.\auto\JDFAutoPSToPDFConversionParams.java@@\main\74
.\auto\JDFAutoPublishingIntent.java@@\main\14
.\auto\JDFAutoQualityControlParams.java@@\main\30
.\auto\JDFAutoQualityControlResult.java@@\main\29
.\auto\JDFAutoQualityMeasurement.java@@\main\32
.\auto\JDFAutoQuery.java@@\main\49
.\auto\JDFAutoQueue.java@@\main\62
.\auto\JDFAutoQueueEntry.java@@\main\66
.\auto\JDFAutoQueueEntryDef.java@@\main\38
.\auto\JDFAutoQueueEntryDefList.java@@\main\38
.\auto\JDFAutoQueueEntryPosParams.java@@\main\45
.\auto\JDFAutoQueueEntryPriParams.java@@\main\44
.\auto\JDFAutoQueueFilter.java@@\main\39
.\auto\JDFAutoQueueSubmissionParams.java@@\main\54
.\auto\JDFAutoRectangleEvaluation.java@@\main\34
.\auto\JDFAutoRefAnchor.java@@\main\2
.\auto\JDFAutoRegisterMark.java@@\main\74
.\auto\JDFAutoRegisterRibbon.java@@\main\53
.\auto\JDFAutoRegistration.java@@\main\15
.\auto\JDFAutoRemoved.java@@\main\35
.\auto\JDFAutoRemoveLink.java@@\main\15
.\auto\JDFAutoRenderingParams.java@@\main\75
.\auto\JDFAutoRepeatDesc.java@@\main\2
.\auto\JDFAutoRequestQueueEntryParams.java@@\main\34
.\auto\JDFAutoResourceAudit.java@@\main\54
.\auto\JDFAutoResourceCmdParams.java@@\main\58
.\auto\JDFAutoResourceDefinitionParams.java@@\main\61
.\auto\JDFAutoResourceInfo.java@@\main\66
.\auto\JDFAutoResourceParam.java@@\main\48
.\auto\JDFAutoResourcePullParams.java@@\main\38
.\auto\JDFAutoResourceQuParams.java@@\main\61
.\auto\JDFAutoResponse.java@@\main\52
.\auto\JDFAutoResubmissionParams.java@@\main\39
.\auto\JDFAutoReturnQueueEntryParams.java@@\main\22
.\auto\JDFAutoRingBinding.java@@\main\52
.\auto\JDFAutoRingBindingParams.java@@\main\59
.\auto\JDFAutoRollStand.java@@\main\28
.\auto\JDFAutoRuleLength.java@@\main\2
.\auto\JDFAutoRunList.java@@\main\82
.\auto\JDFAutoSaddleStitching.java@@\main\42
.\auto\JDFAutoSaddleStitchingParams.java@@\main\56
.\auto\JDFAutoScanParams.java@@\main\75
.\auto\JDFAutoScavengerArea.java@@\main\54
.\auto\JDFAutoScore.java@@\main\57
.\auto\JDFAutoScreeningIntent.java@@\main\21
.\auto\JDFAutoScreeningParams.java@@\main\67
.\auto\JDFAutoScreenSelector.java@@\main\69
.\auto\JDFAutoSeparationControlParams.java@@\main\48
.\auto\JDFAutoSeparationList.java@@\main\26
.\auto\JDFAutoSeparationSpec.java@@\main\46
.\auto\JDFAutoset.java@@\main\25
.\auto\JDFAutoShapeCut.java@@\main\64
.\auto\JDFAutoShapeCuttingIntent.java@@\main\56
.\auto\JDFAutoShapeCuttingParams.java@@\main\54
.\auto\JDFAutoShapeDef.java@@\main\2
.\auto\JDFAutoShapeElement.java@@\main\47
.\auto\JDFAutoShapeEvaluation.java@@\main\29
.\auto\JDFAutoShiftPoint.java@@\main\2
.\auto\JDFAutoShrinkingParams.java@@\main\57
.\auto\JDFAutoShutDownCmdParams.java@@\main\30
.\auto\JDFAutoSideSewing.java@@\main\30
.\auto\JDFAutoSideSewingParams.java@@\main\57
.\auto\JDFAutoSideStitching.java@@\main\36
.\auto\JDFAutoSignal.java@@\main\52
.\auto\JDFAutoSignatureCell.java@@\main\30
.\auto\JDFAutoSizeIntent.java@@\main\67
.\auto\JDFAutoSoftCoverBinding.java@@\main\44
.\auto\JDFAutoSpawned.java@@\main\57
.\auto\JDFAutoSpinePreparationParams.java@@\main\47
.\auto\JDFAutoSpineTapingParams.java@@\main\59
.\auto\JDFAutoStackingParams.java@@\main\53
.\auto\JDFAutoStation.java@@\main\15
.\auto\JDFAutoStatusPool.java@@\main\51
.\auto\JDFAutoStatusQuParams.java@@\main\54
.\auto\JDFAutoStitchingParams.java@@\main\69
.\auto\JDFAutoStopPersChParams.java@@\main\45
.\auto\JDFAutoStrap.java@@\main\55
.\auto\JDFAutoStrappingParams.java@@\main\52
.\auto\JDFAutoStringEvaluation.java@@\main\29
.\auto\JDFAutoStringListValue.java@@\main\27
.\auto\JDFAutoStripBinding.java@@\main\37
.\auto\JDFAutoStripBindingParams.java@@\main\54
.\auto\JDFAutoStripCellParams.java@@\main\29
.\auto\JDFAutoStripMark.java@@\main\22
.\auto\JDFAutoStrippingParams.java@@\main\37
.\auto\JDFAutoSubmissionMethods.java@@\main\47
.\auto\JDFAutoSubscription.java@@\main\49
.\auto\JDFAutoSubscriptionFilter.java@@\main\2
.\auto\JDFAutoSubscriptionInfo.java@@\main\3
.\auto\JDFAutoSystemTimeSet.java@@\main\47
.\auto\JDFAutoTabDimensions.java@@\main\2
.\auto\JDFAutoTabs.java@@\main\61
.\auto\JDFAutoTape.java@@\main\47
.\auto\JDFAutoTest.java@@\main\20
.\auto\JDFAutoTestPool.java@@\main\27
.\auto\JDFAutoTestRef.java@@\main\21
.\auto\JDFAutoThinPDFParams.java@@\main\57
.\auto\JDFAutoThreadSealing.java@@\main\30
.\auto\JDFAutoThreadSealingParams.java@@\main\55
.\auto\JDFAutoThreadSewing.java@@\main\43
.\auto\JDFAutoThreadSewingParams.java@@\main\63
.\auto\JDFAutoTIFFEmbeddedFile.java@@\main\25
.\auto\JDFAutoTIFFFormatParams.java@@\main\37
.\auto\JDFAutoTIFFtag.java@@\main\25
.\auto\JDFAutoTile.java@@\main\64
.\auto\JDFAutoTool.java@@\main\52
.\auto\JDFAutoTrackFilter.java@@\main\50
.\auto\JDFAutoTrackResult.java@@\main\47
.\auto\JDFAutoTransferCurve.java@@\main\64
.\auto\JDFAutoTransferCurvePool.java@@\main\62
.\auto\JDFAutoTransferCurveSet.java@@\main\67
.\auto\JDFAutoTransferFunctionControl.java@@\main\68
.\auto\JDFAutoTrappingDetails.java@@\main\69
.\auto\JDFAutoTrappingOrder.java@@\main\57
.\auto\JDFAutoTrappingParams.java@@\main\77
.\auto\JDFAutoTrapRegion.java@@\main\53
.\auto\JDFAutoTrigger.java@@\main\46
.\auto\JDFAutoTrimmingParams.java@@\main\69
.\auto\JDFAutoUpdateJDFCmdParams.java@@\main\15
.\auto\JDFAutoUsageCounter.java@@\main\20
.\auto\JDFAutoValue.java@@\main\34
.\auto\JDFAutoValueLoc.java@@\main\26
.\auto\JDFAutoVerificationParams.java@@\main\49
.\auto\JDFAutoWakeUpCmdParams.java@@\main\16
.\auto\JDFAutoWebInlineFinishingParams.java@@\main\16
.\auto\JDFAutowhen.java@@\main\24
.\auto\JDFAutoWireCombBinding.java@@\main\53
.\auto\JDFAutoWireCombBindingParams.java@@\main\59
.\auto\JDFAutoWrappingParams.java@@\main\49
.\auto\JDFAutoxor.java@@\main\16
.\auto\JDFAutoXYPairEvaluation.java@@\main\33
.\core\AtrInfo.java@@\main\9
.\core\AtrInfoTable.java@@\main\13
.\core\AttributeName.java@@\main\53
.\core\DocumentJDFImpl.java@@\main\101
.\core\ElementName.java@@\main\45
.\core\JDFAudit.java@@\main\98
.\core\JDFConstants.java@@\main\76
.\core\JDFCustomerInfo.java@@\main\45
.\core\JDFCustomerMessage.java@@\main\10
.\core\JDFDoc.java@@\main\86
.\core\JDFElement.java@@\main\260
.\core\JDFNodeInfo.java@@\main\63
.\core\JDFParser.java@@\main\53
.\core\JDFPartAmount.java@@\main\32
.\core\JDFRefElement.java@@\main\67
.\core\JDFResourceLink.java@@\main\154
.\core\KElement.java@@\main\277
.\core\VElement.java@@\main\38
.\core\XMLDoc.java@@\main\102
.\datatypes\JDFAttributeMap.java@@\main\44
.\datatypes\VJDFAttributeMap.java@@\main\39
.\elementwalker@@\main\5
.\elementwalker\FixVersion.java@@\main\2
.\extensions\XJDF20.java@@\main\6
.\extensions\xjdfwalker\XJDFToJDFConverter.java@@\main\4
.\goldenticket@@\main\5
.\goldenticket\BaseGoldenTicket.java@@\main\20
.\goldenticket\IDPGoldenTicket.java@@\main\5
.\goldenticket\MISCPGoldenTicket.java@@\main\16
.\goldenticket\MISGoldenTicket.java@@\main\17
.\goldenticket\MISPreGoldenTicket.java@@\main\9
.\goldenticket\ProductGoldenTicket.java@@\main\8
.\jmf\JDFAcknowledge.java@@\main\24
.\jmf\JDFDeviceFilter.java@@\main\13
.\jmf\JDFDeviceInfo.java@@\main\34
.\jmf\JDFIDInfo.java@@\main\12
.\jmf\JDFJMF.java@@\main\76
.\jmf\JDFMessage.java@@\main\83
.\jmf\JDFNewJDFQuParams.java@@\main\12
.\jmf\JDFPipeParams.java@@\main\26
.\jmf\JDFQueue.java@@\main\39
.\jmf\JDFQueueEntry.java@@\main\36
.\jmf\JDFQueueFilter.java@@\main\15
.\jmf\JDFResourceCmdParams.java@@\main\34
.\jmf\JDFResourceInfo.java@@\main\37
.\jmf\JDFResponse.java@@\main\34
.\jmf\JDFResubmissionParams.java@@\main\13
.\jmf\JDFSignal.java@@\main\27
.\node\JDFNode.java@@\main\285
.\pool\JDFAmountPool.java@@\main\38
.\pool\JDFPool.java@@\main\40
.\pool\JDFResourceLinkPool.java@@\main\89
.\pool\JDFResourcePool.java@@\main\75
.\resource\devicecapability\JDFBooleanState.java@@\main\30
.\resource\devicecapability\JDFDeviceCap.java@@\main\59
.\resource\intent\JDFDropItemIntent.java@@\main\22
.\resource\intent\JDFProductionIntent.java@@\main\23
.\resource\JDFCuttingParams.java@@\main\17
.\resource\JDFDevice.java@@\main\14
.\resource\JDFPhaseTime.java@@\main\36
.\resource\JDFProcessRun.java@@\main\38
.\resource\JDFResource.java@@\main\243
.\resource\JDFResourceAudit.java@@\main\38
.\resource\process@@\main\27
.\resource\process\JDFColorantControl.java@@\main\27
.\resource\process\JDFCutBlock.java@@\main\17
.\resource\process\JDFFileSpec.java@@\main\35
.\resource\process\JDFIdentical.java@@\main\7
.\resource\process\JDFPerson.java@@\main\22
.\resource\process\JDFRunList.java@@\main\60
.\resource\process\JDFSourceResource.java@@\main\12
.\resource\process\JDFTransferCurve.java@@\main\26
.\util@@\main\31
.\util\ByteArrayIOStream.java@@\main\3
.\util\DumpDir.java@@\main\10
.\util\EnumUtil.java@@\main\6
.\util\FastFiFo.java@@\main\2
.\util\FileUtil.java@@\main\16
.\util\HashUtil.java@@\main\6
.\util\HotFolder.java@@\main\9
.\util\JDFDate.java@@\main\56
.\util\JDFDuration.java@@\main\12
.\util\JDFMerge.java@@\main\35
.\util\JDFSpawn.java@@\main\37
.\util\MimeUtil.java@@\main\36
.\util\MyArgs.java@@\main\33
.\util\SkipInputStream.java@@\main\3
.\util\StatusCounter.java@@\main\25
.\util\StringUtil.java@@\main\81
.\util\ThreadUtil.java@@\main\4
.\util\UrlUtil.java@@\main\27
.\validate\JDFValidator.java@@\main\8

___________________________________________________________


Label JDFLIBJ_2.1.4BLD551 (18.06.2009)

#BF Array out of bounds in JDFResourceInfo.getResourceVector()


>ct find . -version "{lbtype(JDFLIBJ_2.1.4BLD551) && !lbtype(JDFLIBJ_2.1.4BLD550)}" -print
.\jmf\JDFResourceInfo.java@@\main\36

___________________________________________________________


Label JDFLIBJ_2.1.4BLD550 (18.06.2009)

Revision: 3966
Author: mucha
Date: 15:13:59, Donnerstag, 18. Juni 2009
Message:
cosmetics to reduce warnings
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/FixVersion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDFSchemaWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/xjdfwalker/XJDFToJDFConverter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/JMFGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISFinGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/CheckJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFParserTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFSchemaTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/extensions/XJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAmountPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFPageListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FastFiFoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFDateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java

Revision: 3965
Author: prosi
Date: 14:14:05, Donnerstag, 18. Juni 2009
Message:

----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java

Revision: 3964
Author: mucha
Date: 14:11:48, Donnerstag, 18. Juni 2009
Message:

----
Modified : /trunk/JDFLibJ/version.properties

Revision: 3962
Author: mucha
Date: 14:05:07, Donnerstag, 18. Juni 2009
Message:
cosmetics
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/GeneratorUtil.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/JavaCoreStringUtil.java

Revision: 3961
Author: prosi
Date: 13:41:16, Donnerstag, 18. Juni 2009
Message:

----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java

Revision: 3960
Author: prosi
Date: 13:34:39, Donnerstag, 18. Juni 2009
Message:
speed up of unify
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VString.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/VJDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/VJDFAttributeMapTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java

Revision: 3954
Author: prosi
Date: 19:15:01, Dienstag, 16. Juni 2009
Message:
Cleanup + bambi enhancements
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/VJDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/AttributeReplacer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalkerFactory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/FixVersion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/BiHashMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/JDFValidator.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/AllJDFLibTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFSchemaTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/elementwalker/AttributeReplacerTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/extensions/XJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFMessageTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFPipeParamsTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFDateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java

Revision: 3949
Author: prosi
Date: 13:32:17, Montag, 8. Juni 2009
Message:
added scschemawalker prototype
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDFSchemaWalker.java

Revision: 3948
Author: prosi
Date: 13:30:32, Montag, 8. Juni 2009
Message:
added FixVersion class
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/FixVersion.java

Revision: 3946
Author: prosi
Date: 11:48:31, Montag, 8. Juni 2009
Message:
refactor fixversion to its own class
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFCustomerInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFParser.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFAncestor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourcePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFTool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFAbstractState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFApprovalSuccess.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanBase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/AllJDFLibTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/FixVersionTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFParserTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFSchemaTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFTestType.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java

Revision: 3944
Author: prosi
Date: 20:04:50, Donnerstag, 4. Juni 2009
Message:
added LayoutPrep to Stripping converter
some more JDF 1.4 conversion enhancements
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseElementWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/ElementWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/IWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFEmployee.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/AllJDFLibTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/extensions/XJDFTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFLayoutPreparationParamsTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFEmployeeTest.java

Revision: 3941
Author: prosi
Date: 17:29:38, Mittwoch, 3. Juni 2009
Message:
added fast fifo utility
cleanup for bambi
fixes for JDF 1.4
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssembly.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssemblySection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscriptionInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/EnumUtil.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FastFiFo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MyArgs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MyInteger.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/AllJDFLibTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFAuditTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFDeviceInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFMessageTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAmountPoolTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FastFiFoTest.java

Revision: 3934
Author: prosi
Date: 17:23:14, Freitag, 15. Mai 2009
Message:
Bambi updates; improvements to the unlinkwalker
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFIntegerRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumberRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/UnLinkFinder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFConvertingConfig.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRepeatDesc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/VectorMap.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/JDFTestCaseBase.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFIntegerRangeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/elementwalker/UnlinkFinderTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/BaseGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/GoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISPreGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/ContainerUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/VectorMapTest.java

Revision: 3928
Author: prosi
Date: 19:29:45, Donnerstag, 7. Mai 2009
Message:
Bambi updates ; fix for collapse
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/TestJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFIntegerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/BiHashMap.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFIntegerListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFJobPhaseTest.java

Revision: 3919
Author: prosi
Date: 10:29:49, Dienstag, 5. Mai 2009
Message:
src cleanup
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java

Revision: 3916
Author: prosi
Date: 10:06:42, Dienstag, 5. Mai 2009
Message:
fix KElement comparator to set null as smallest
output some info in DumpJDFServlet get()
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/ContainerUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java

Revision: 3914
Author: prosi
Date: 13:31:17, Mittwoch, 29. April 2009
Message:
sort queues by attributes
----
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java

Revision: 3912
Author: prosi
Date: 12:50:30, Mittwoch, 29. April 2009
Message:
new queuesorter by attribute
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/xjdfwalker/XJDFToJDFConverter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java

Revision: 3910
Author: prosi
Date: 18:10:44, Dienstag, 28. April 2009
Message:
fix npe in AmountMap constructor + cosmetics
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/xjdfwalker/XJDFToJDFConverter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/extensions/XJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAmountPoolTest.java

Revision: 3909
Author: prosi
Date: 15:27:33, Mittwoch, 22. April 2009
Message:
clean up unit tests
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRunList.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAmountPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFRunListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java

Revision: 3908
Author: prosi
Date: 11:20:51, Mittwoch, 22. April 2009
Message:
JDFLib updates from Montreal
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFConstants.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFCMYKColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/xjdfwalker/XJDFToJDFConverter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/IDPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISFinGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFTrappingOrder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/CheckJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/MISFinTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/extensions/XJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/IDPGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StatusCounterTest.java


>ct find . -version "{lbtype(JDFLIBJ_2.1.4BLD550) && !lbtype(JDFLIBJ_2.1.4BLD544)}" -print
.\auto\JDFAutoAssembly.java@@\main\34
.\auto\JDFAutoAssemblySection.java@@\main\34
.\auto\JDFAutoSubscriptionInfo.java@@\main\2
.\core\JDFAudit.java@@\main\97
.\core\JDFComment.java@@\main\32
.\core\JDFConstants.java@@\main\75
.\core\JDFCustomerInfo.java@@\main\44
.\core\JDFElement.java@@\main\259
.\core\JDFNodeInfo.java@@\main\62
.\core\JDFParser.java@@\main\52
.\core\JDFResourceLink.java@@\main\153
.\core\KElement.java@@\main\276
.\core\VElement.java@@\main\37
.\core\VString.java@@\main\45
.\core\XMLDoc.java@@\main\101
.\datatypes\JDFAttributeMap.java@@\main\43
.\datatypes\JDFCMYKColor.java@@\main\12
.\datatypes\JDFIntegerList.java@@\main\21
.\datatypes\JDFIntegerRange.java@@\main\35
.\datatypes\JDFNumberRange.java@@\main\27
.\datatypes\VJDFAttributeMap.java@@\main\38
.\elementwalker@@\main\4
.\elementwalker\AttributeReplacer.java@@\main\2
.\elementwalker\BaseElementWalker.java@@\main\3
.\elementwalker\BaseWalker.java@@\main\5
.\elementwalker\BaseWalkerFactory.java@@\main\3
.\elementwalker\ElementWalker.java@@\main\3
.\elementwalker\FixVersion.java@@\main\1
.\elementwalker\IWalker.java@@\main\4
.\elementwalker\UnLinkFinder.java@@\main\5
.\extensions@@\main\2
.\extensions\XJDF20.java@@\main\5
.\extensions\XJDFSchemaWalker.java@@\main\1
.\extensions\xjdfwalker\XJDFToJDFConverter.java@@\main\3
.\goldenticket\BaseGoldenTicket.java@@\main\19
.\goldenticket\IDPGoldenTicket.java@@\main\4
.\goldenticket\JMFGoldenTicket.java@@\main\5
.\goldenticket\MISFinGoldenTicket.java@@\main\5
.\goldenticket\MISGoldenTicket.java@@\main\16
.\jmf\JDFDeviceInfo.java@@\main\33
.\jmf\JDFJMF.java@@\main\75
.\jmf\JDFJobPhase.java@@\main\29
.\jmf\JDFMessage.java@@\main\82
.\jmf\JDFPipeParams.java@@\main\25
.\jmf\JDFQueueEntry.java@@\main\35
.\jmf\JDFQueueSubmissionParams.java@@\main\23
.\jmf\JDFRequestQueueEntryParams.java@@\main\16
.\jmf\JDFResourceCmdParams.java@@\main\33
.\jmf\JDFResourceInfo.java@@\main\35
.\jmf\JDFSignal.java@@\main\26
.\node\JDFAncestor.java@@\main\36
.\node\JDFNode.java@@\main\284
.\pool\JDFAmountPool.java@@\main\37
.\pool\JDFAuditPool.java@@\main\112
.\pool\JDFResourcePool.java@@\main\74
.\resource\devicecapability\JDFAbstractState.java@@\main\59
.\resource\JDFDevice.java@@\main\13
.\resource\JDFLayoutPreparationParams.java@@\main\10
.\resource\JDFResource.java@@\main\242
.\resource\JDFTool.java@@\main\15
.\resource\process\JDFApprovalSuccess.java@@\main\23
.\resource\process\JDFColor.java@@\main\31
.\resource\process\JDFComponent.java@@\main\24
.\resource\process\JDFContentObject.java@@\main\26
.\resource\process\JDFConvertingConfig.java@@\main\2
.\resource\process\JDFEmployee.java@@\main\21
.\resource\process\JDFLayout.java@@\main\25
.\resource\process\JDFRepeatDesc.java@@\main\2
.\resource\process\JDFRunList.java@@\main\59
.\resource\process\prepress\JDFTrappingOrder.java@@\main\20
.\span\JDFSpanBase.java@@\main\39
.\util@@\main\29
.\util\BiHashMap.java@@\main\3
.\util\ContainerUtil.java@@\main\12
.\util\DumpDir.java@@\main\9
.\util\EnumUtil.java@@\main\5
.\util\FastFiFo.java@@\main\1
.\util\JDFDate.java@@\main\55
.\util\JDFMerge.java@@\main\34
.\util\JDFSpawn.java@@\main\36
.\util\MyArgs.java@@\main\32
.\util\MyInteger.java@@\main\3
.\util\StatusCounter.java@@\main\24
.\util\StringUtil.java@@\main\80
.\util\UrlUtil.java@@\main\26
.\util\VectorMap.java@@\main\7
.\validate\JDFValidator.java@@\main\7

___________________________________________________________


Label JDFLIBJ_2.1.4BLD544 (24.03.2009)

fix for mergeSpawnIDs(...)

>ct find . -version "{lbtype(JDFLIBJ_2.1.4BLD544) && !lbtype(JDFLIBJ_2.1.4BLD543)}" -print
.\util\JDFMerge.java@@\main\33
__________________________________________________________


Label JDFLIBJ_2.1.4BLD543 (17.02.2009)

added functionality for fast building of dom trees without namespace validation
JDFResource.expand now correctly works on single partition leaves
add missing ShapeDefProductionParams et al.
fixed missing JDFAutoShrinkingParams.java
undo KElement toXML cleanup
speed up validation


Revision: 3858
Author: prosi
Date: 10:32:35, Dienstag, 17. Februar 2009
Message:

----
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/elementwalker/AttributeReplacerTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/elementwalker/XPathWalkerTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/QueueHotFolderTest.java

Revision: 3855
Author: prosi
Date: 09:33:59, Dienstag, 17. Februar 2009
Message:
added functionality for fast building of dom trees without namespace validation
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/JDFTestCaseBase.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java

Revision: 3852
Author: prosi
Date: 16:04:44, Freitag, 13. Februar 2009
Message:
cosmetics
JDFResource.expand now correctly works on single partition leaves
Golden ticket additional features
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFCMYKColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFIntegerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFLabColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFMatrix.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumberList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRGBColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRectangle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFShape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFTransferFunction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFXYPair.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFComChannel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDieLayoutProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ThreadUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFParserTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFNumListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/extensions/XJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/BaseGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/QueueHotFolderTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StatusCounterTest.java

Revision: 3839
Author: mucha
Date: 15:43:45, Dienstag, 10. Februar 2009
Message:
add missing ShapeDefProductionParams et al.
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObjectModel.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeDefProductionParams.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeTemplate.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDieLayoutProductionParams.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFObjectModel.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFShapeDefProductionParams.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFShapeTemplate.java

Revision: 3838
Author: mucha
Date: 15:42:21, Dienstag, 10. Februar 2009
Message:
add missing ShapeDefProductionParams et al.
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/ComplexTypeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeName.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElementName.java

Revision: 3837
Author: mucha
Date: 17:12:10, Montag, 9. Februar 2009
Message:
fixed missing JDFAutoShrinkingParams.java
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/ComplexTypeList.java

Revision: 3831
Author: mucha
Date: 15:09:54, Montag, 9. Februar 2009
Message:

----
Modified : /trunk/JDFLibJ/Readme.txt

Revision: 3827
Author: mucha
Date: 15:11:13, Donnerstag, 29. Januar 2009
Message:
cosmetics
----
Modified : /trunk/JDFLibJ/.classpath
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HotFolder.java


>ct find . -version "{lbtype(JDFLIBJ_2.1.4BLD543) && !lbtype(JDFLIBJ_2.1.4BLD542)}" -print
.\auto@@\main\46
.\auto\JDFAutoObjectModel.java@@\main\1
.\auto\JDFAutoShapeDefProductionParams.java@@\main\1
.\auto\JDFAutoShapeTemplate.java@@\main\1
.\core\AttributeName.java@@\main\52
.\core\DocumentJDFImpl.java@@\main\99
.\core\ElementName.java@@\main\44
.\core\JDFDoc.java@@\main\85
.\core\KElement.java@@\main\274
.\datatypes\JDFCMYKColor.java@@\main\11
.\datatypes\JDFIntegerList.java@@\main\20
.\datatypes\JDFLabColor.java@@\main\9
.\datatypes\JDFMatrix.java@@\main\22
.\datatypes\JDFNumberList.java@@\main\11
.\datatypes\JDFNumList.java@@\main\37
.\datatypes\JDFRectangle.java@@\main\24
.\datatypes\JDFRGBColor.java@@\main\10
.\datatypes\JDFShape.java@@\main\16
.\datatypes\JDFTransferFunction.java@@\main\11
.\datatypes\JDFXYPair.java@@\main\22
.\extensions\XJDF20.java@@\main\4
.\goldenticket\BaseGoldenTicket.java@@\main\18
.\goldenticket\MISGoldenTicket.java@@\main\15
.\goldenticket\MISPreGoldenTicket.java@@\main\8
.\jmf\JDFQueueSubmissionParams.java@@\main\22
.\resource\JDFResource.java@@\main\241
.\resource\process@@\main\26
.\resource\process\JDFComChannel.java@@\main\19
.\resource\process\JDFDieLayoutProductionParams.java@@\main\2
.\resource\process\JDFObjectModel.java@@\main\1
.\resource\process\JDFShapeDefProductionParams.java@@\main\1
.\resource\process\JDFShapeTemplate.java@@\main\1
.\util\JDFMerge.java@@\main\32
.\util\MimeUtil.java@@\main\34
.\util\StringUtil.java@@\main\79
.\util\ThreadUtil.java@@\main\3
___________________________________________________________


Label JDFLIBJ_2.1.4BLD542 (29.01.2009)

add synchronized in getChildElementVector_JDFElement()

Revision: 3826
Author: prosi
Date: 11:48:58, Donnerstag, 29. Januar 2009
Message:
undo KElement toXML cleanup
speed up validation
----
Modified : /trunk/JDFLibJ/.classpath
Modified : /trunk/JDFLibJ/.project
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/SizeWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/QueueHotFolder.java

Revision: 3825
Author: prosi
Date: 11:22:00, Donnerstag, 29. Januar 2009
Message:
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java

Revision: 3790
Author: prosi
Date: 18:50:46, Freitag, 23. Januar 2009
Message:
Bambi improvements
typesafe collection cleanup
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeName.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFCMYKColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRGBColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java

Revision: 3789
Author: prosi
Date: 18:50:10, Freitag, 23. Januar 2009
Message:
Bambi improvements
typesafe collection cleanup
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java

Revision: 3771
Author: prosi
Date: 14:15:25, Donnerstag, 22. Januar 2009
Message:
enhanced identical element handling
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/xjdfwalker/XJDFToJDFConverter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIdentical.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java


>ct find . -version "{lbtype(JDFLIBJ_2.1.4BLD542) && !lbtype(JDFLIBJ_2.1.4BLD541)}" -print
.\cformat\ScanfMatchException.java@@\main\3
.\core\AttributeInfo.java@@\main\37
.\core\AttributeName.java@@\main\51
.\core\DocumentJDFImpl.java@@\main\97
.\core\JDFDoc.java@@\main\84
.\core\JDFElement.java@@\main\258
.\core\JDFResourceLink.java@@\main\152
.\core\KElement.java@@\main\273
.\core\VResource.java@@\main\18
.\core\XMLDoc.java@@\main\99
.\datatypes\JDFAttributeMap.java@@\main\42
.\datatypes\JDFBaseDataTypes.java@@\main\12
.\datatypes\JDFCMYKColor.java@@\main\10
.\datatypes\JDFNameRangeList.java@@\main\16
.\datatypes\JDFNumList.java@@\main\36
.\datatypes\JDFRange.java@@\main\19
.\datatypes\JDFRGBColor.java@@\main\9
.\datatypes\VJDFAttributeMap.java@@\main\37
.\elementwalker\BaseWalkerFactory.java@@\main\2
.\elementwalker\SizeWalker.java@@\main\2
.\extensions\XJDF20.java@@\main\3
.\extensions\xjdfwalker\XJDFToJDFConverter.java@@\main\2
.\goldenticket\IDPGoldenTicket.java@@\main\3
.\ifaces\ICapabilityElement.java@@\main\3
.\ifaces\IDeviceCapable.java@@\main\3
.\ifaces\IJMFSubscribable.java@@\main\2
.\ifaces\IMatches.java@@\main\2
.\ifaces\IPlacedObject.java@@\main\3
.\jmf\JDFResourceQuParams.java@@\main\22
.\pool\JDFAmountPool.java@@\main\36
.\pool\JDFResourceLinkPool.java@@\main\88
.\resource\JDFPart.java@@\main\33
.\resource\JDFResource.java@@\main\240
.\resource\process\JDFColor.java@@\main\30
.\resource\process\JDFIdentical.java@@\main\6
.\util\ByteArrayIOStream.java@@\main\2
.\util\EnumUtil.java@@\main\4
.\util\HotFolder.java@@\main\8
.\util\HotFolderListener.java@@\main\2
.\util\JDFSpawn.java@@\main\35
.\util\MimeUtil.java@@\main\33
.\util\MyPair.java@@\main\2
.\util\QueueHotFolder.java@@\main\10
.\util\QueueHotFolderListener.java@@\main\3
.\util\RollingBackupFile.java@@\main\2
.\util\StringUtil.java@@\main\78
.\validate\ICheckValidator.java@@\main\2
.\validate\ICheckValidatorFactory.java@@\main\2
___________________________________________________________


Label JDFLIBJ_2.1.4BLD541 (21.01.2009)

remove synchronized in getChildElementVector_JDFElement()

>ct find . -version "{lbtype(JDFLIBJ_2.1.4BLD541) && !lbtype(JDFLIBJ_2.1.4BLD540)}" -print
.\core\JDFElement.java@@\main\257
___________________________________________________________


Label JDFLIBJ_2.1.4BLD540 (16.01.2009)

First version conforming to JDF Spec 1.4

!!! The Label name prefix was changed from JDFLIBJ_2.1.3 to JDFLIBJ_2.1.4 !!!



Revision: 3755
Author: prosi
Date: 17:09:33, Freitag, 16. Januar 2009
Message:
set more bits when cloning DocumentImpl
xslt for JDF
URLUtil fix for windows c:\ style paths
optionally ignore identical elements when spawning
shorten length of ids in messages
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRegisterMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java

Revision: 3747
Author: mucha
Date: 16:55:42, Donnerstag, 15. Januar 2009
Message:

----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java

Revision: 3746
Author: mucha
Date: 16:54:50, Donnerstag, 15. Januar 2009
Message:
add 1.4 auto files
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterRibbon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegistration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoveLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoved.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRenderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRepeatDesc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceDefinitionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourcePullParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResponse.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoReturnQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRollStand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRuleLength.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRunList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScanParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScavengerArea.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScore.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreenSelector.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShiftPoint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShrinkingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShutDownCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignatureCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSizeIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSoftCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpawned.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpinePreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpineTapingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStopPersChParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringListValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripCellParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrippingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubmissionMethods.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscription.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscriptionFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscriptionInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSystemTimeSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFEmbeddedFile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFFormatParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFtag.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabDimensions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestRef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThinPDFParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurve.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurvePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurveSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferFunctionControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrapRegion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingOrder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrigger.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrimmingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUpdateJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUsageCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValueLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoVerificationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWakeUpCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWebInlineFinishingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoXYPairEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutowhen.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoxor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeName.java

Revision: 3745
Author: mucha
Date: 16:53:32, Donnerstag, 15. Januar 2009
Message:

----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInk.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneCalculationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsert.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIntegerEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretedPDLData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIsPresentEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJBIG2Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFController.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJPEG2000Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJacketingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoKnownMsgQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLZWParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLabelingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLocation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongSlit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongitudinalRibbonOperationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMISDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMacroPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoManualLaborParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkActivation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMatrixEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaLayers.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaSource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMerged.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMilestone.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMiscConsumable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModified.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModifyNodeCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMoveResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMsgFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNameEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoResp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOCGControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObjectResolution.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObservationTarget.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOccupation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOrderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFPathEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFToPSConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFXParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLResourceAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroupOccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPROccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRuleAttr.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPSToPDFConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageAssignParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageAssignedList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPallet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPalletizingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPartStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPayment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerformance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPixelColorant.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlaceHolderResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlateCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPositionObj.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAnalysis.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightConstraint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstanceDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInventory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReport.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReportRulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreview.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreviewGenerationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPricing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintConditionColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintRollingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionSubPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPublishingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityMeasurement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQuery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDefList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPosParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPriParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRectangleEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRefAnchor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutomacro.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutonot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutootherwise.java

Revision: 3744
Author: mucha
Date: 16:51:54, Donnerstag, 15. Januar 2009
Message:
add 1.4 auto files
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContact.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContactCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContainer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentMetaData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoControllerFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoConventionalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoConvertingConfig.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCostCenter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCounterReset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoverApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCrease.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreasingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreated.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreditCard.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutBlock.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBMergeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBRules.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSchema.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSelection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDCTParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDateTimeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeleted.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDensityMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDependencies.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCapPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevelopingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNSpace.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDieLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDieLayoutProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisjointing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroupPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisposition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDividingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDrop.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItemIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDurationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicInput.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEdgeGluing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoElementColorParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmboss.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployeeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheetGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEnumerationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoErrorData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEvent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExposedMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExternalImpositionTemplate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExtraValues.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFCNKey.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeatureAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeaturePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeeder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeederQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeedingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFitPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlateParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushResourceParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushedResources.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldOperation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFolderProduction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFormatConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangCmdFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangQuFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGatheringParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGeneralID.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHardCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHeadBandApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHole.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFinishing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFolding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPHoleMaking.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPTrimming.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIcon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIconList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentical.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentificationField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompression.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompressionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageReplacementParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageSetterParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageShift.java

Revision: 3743
Author: mucha
Date: 16:50:22, Donnerstag, 15. Januar 2009
Message:
add 1.4 auto files
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAcknowledge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoActionPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdded.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAddress.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdvancedParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalSuccess.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArgumentValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDelivery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssembly.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssemblySection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssetListCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAuthenticationCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAuthenticationQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAuthenticationResp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAutomatedOverPrintParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeCompParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeReproParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBasicPreflightTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBendingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBinderySignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBlockPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBookCase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBooleanEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxToBoxDifference.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBufferParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundleItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundlingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBusinessInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoByteMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCCITTFaxParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCIELABMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCaseMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCasingInParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollatingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollectingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorControlStrip.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorMeasurementConditions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceSubstitute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantZoneDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorsUsed.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComChannel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCommand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCompany.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutocall.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutochoice.java

Revision: 3737
Author: mucha
Date: 10:03:48, Dienstag, 13. Januar 2009
Message:

----
Deleted : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAutoLayoutElement.java

Revision: 3736
Author: prosi
Date: 13:31:58, Montag, 12. Januar 2009
Message:

----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/XJDF20.java

Revision: 3735
Author: prosi
Date: 13:30:50, Montag, 12. Januar 2009
Message:

----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/xjdfwalker
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/extensions/xjdfwalker/XJDFToJDFConverter.java

Revision: 3733
Author: prosi
Date: 11:35:35, Montag, 12. Januar 2009
Message:
make FileUtil 1.5 compliant again
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java

Revision: 3723
Author: prosi
Date: 15:23:33, Freitag, 12. Dezember 2008
Message:
jfd 1.4 new files
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/AttributeReplacer.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/SizeWalker.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/XPathWalker.java

Revision: 3722
Author: prosi
Date: 15:17:20, Freitag, 12. Dezember 2008
Message:
jfd 1.4 new files
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/node/AuditToJMF.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRepeatDesc.java

Revision: 3721
Author: prosi
Date: 15:14:26, Freitag, 12. Dezember 2008
Message:
jfd 1.4 new files
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MyPair.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/RollingBackupFile.java

Revision: 3720
Author: prosi
Date: 15:11:46, Freitag, 12. Dezember 2008
Message:
jfd 1.4 new files
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFConvertingConfig.java

Revision: 3719
Author: prosi
Date: 15:06:39, Freitag, 12. Dezember 2008
Message:
jfd 1.4 new files
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFAuthenticationCmdParams.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFAuthenticationQuParams.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFAuthenticationResp.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFCertificate.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFControllerFilter.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFSubscriptionFilter.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFSubscriptionInfo.java

Revision: 3716
Author: prosi
Date: 14:45:50, Freitag, 12. Dezember 2008
Message:
update to new xerces and mail api
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/TestJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VString.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/VJDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourceLinkPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/BiHashMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MyInteger.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/QueueHotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ThreadUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java

Revision: 3707
Author: prosi
Date: 18:43:10, Mittwoch, 26. November 2008
Message:
Performance updates for spawning
update to new xerces and mail api
----
Modified : /trunk/JDFLibJ/lib/mailapi.jar
Modified : /trunk/JDFLibJ/lib/xercesImpl.jar
Modified : /trunk/JDFLibJ/lib/xml-apis.jar
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java

Revision: 3706
Author: prosi
Date: 17:40:26, Donnerstag, 20. November 2008
Message:
JDF1.4 updated version
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java

Revision: 3703
Author: prosi
Date: 17:36:52, Mittwoch, 19. November 2008
Message:
JDF1.4 updated version
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/ComplexTypeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeName.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElemInfoTable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElementName.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFKnownMsgQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourcePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDieLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreview.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java

Revision: 3702
Author: prosi
Date: 17:35:39, Mittwoch, 19. November 2008
Message:
JDF1.4 updated version
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAuthenticationCmdParams.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAuthenticationQuParams.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAuthenticationResp.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoControllerFilter.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoConvertingConfig.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDieLayoutProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobPhase.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRepeatDesc.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscriptionFilter.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscriptionInfo.java

Revision: 3698
Author: prosi
Date: 20:00:30, Freitag, 14. November 2008
Message:
JDF1.4 initial version
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/SpawnJDF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/ISignalAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayoutShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java

Revision: 3696
Author: mucha
Date: 16:56:30, Freitag, 14. November 2008
Message:
fix 1.4
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/ComplexTypeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAcknowledge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCommand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJMF.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessage.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageAssignParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQuery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegistration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResponse.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShiftPoint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElementName.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayoutShift.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPageAssignParams.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFShiftPoint.java

Revision: 3692
Author: mucha
Date: 11:04:22, Freitag, 14. November 2008
Message:
fix auto file generator for JDF 1.4
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/GeneratorUtil.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/JavaCoreStringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAcknowledge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoActionPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdded.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAddress.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdvancedParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalSuccess.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArgumentValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDelivery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssembly.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssemblySection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssetListCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAutomatedOverPrintParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeCompParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeReproParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBasicPreflightTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBendingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBinderySignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBlockPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBookCase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBooleanEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxToBoxDifference.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBufferParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundleItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundlingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBusinessInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoByteMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCCITTFaxParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCIELABMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCaseMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCasingInParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollatingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollectingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorControlStrip.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorMeasurementConditions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceSubstitute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantZoneDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorsUsed.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComChannel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCommand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCompany.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContact.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContactCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContainer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentMetaData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoConventionalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCostCenter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCounterReset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoverApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCrease.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreasingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreated.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreditCard.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutBlock.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBMergeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBRules.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSchema.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSelection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDCTParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDateTimeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeleted.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDensityMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDependencies.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCapPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevelopingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNSpace.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDieLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisjointing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroupPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisposition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDividingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDrop.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItemIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDurationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicInput.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEdgeGluing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoElementColorParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmboss.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployeeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheetGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEnumerationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoErrorData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEvent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExposedMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExternalImpositionTemplate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExtraValues.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFCNKey.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeatureAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeaturePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeeder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeederQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeedingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFitPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlateParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushResourceParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushedResources.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldOperation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFolderProduction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFormatConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangCmdFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangQuFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGatheringParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGeneralID.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHardCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHeadBandApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHole.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFinishing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFolding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPHoleMaking.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPTrimming.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIcon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIconList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentical.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentificationField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompression.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompressionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageReplacementParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageSetterParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInk.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneCalculationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsert.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIntegerEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretedPDLData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIsPresentEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJBIG2Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFController.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJPEG2000Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJacketingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoKnownMsgQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLZWParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLabelingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLocation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongSlit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongitudinalRibbonOperationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMISDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMacroPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoManualLaborParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkActivation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMatrixEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaLayers.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaSource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMerged.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMilestone.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMiscConsumable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModified.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModifyNodeCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMoveResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMsgFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNameEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoResp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOCGControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObjectResolution.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObservationTarget.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOccupation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOrderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFPathEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFToPSConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFXParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLResourceAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroupOccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPROccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRuleAttr.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPSToPDFConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageAssignedList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPallet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPalletizingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPartStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPayment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerformance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPixelColorant.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlaceHolderResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlateCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPositionObj.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAnalysis.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightConstraint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstanceDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInventory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReport.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReportRulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreview.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreviewGenerationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPricing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintConditionColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintRollingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionSubPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPublishingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityMeasurement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQuery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDefList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPosParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPriParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRectangleEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRefAnchor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterRibbon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegistration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoveLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoved.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRenderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceDefinitionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourcePullParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResponse.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoReturnQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRollStand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRuleLength.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRunList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScanParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScavengerArea.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScore.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreenSelector.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShrinkingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShutDownCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignatureCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSizeIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSoftCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpawned.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpinePreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpineTapingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStopPersChParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringListValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripCellParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrippingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubmissionMethods.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscription.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSystemTimeSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFEmbeddedFile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFFormatParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFtag.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabDimensions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestRef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThinPDFParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurve.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurvePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurveSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferFunctionControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrapRegion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingOrder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrigger.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrimmingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUpdateJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUsageCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValueLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoVerificationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWakeUpCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWebInlineFinishingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoXYPairEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutocall.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutochoice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutomacro.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutonot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutootherwise.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutowhen.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoxor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAutoLayoutElement.java

Revision: 3691
Author: mucha
Date: 15:19:27, Donnerstag, 13. November 2008
Message:
add @override
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAcknowledge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoActionPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdded.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAddress.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdvancedParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalSuccess.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArgumentValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDelivery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssembly.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssemblySection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssetListCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAutomatedOverPrintParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeCompParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeReproParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBasicPreflightTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBendingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBinderySignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBlockPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBookCase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBooleanEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxToBoxDifference.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBufferParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundleItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundlingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBusinessInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoByteMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCCITTFaxParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCIELABMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCaseMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCasingInParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollatingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollectingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorControlStrip.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorMeasurementConditions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceSubstitute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantZoneDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorsUsed.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComChannel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCommand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCompany.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContact.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContactCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContainer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentMetaData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoConventionalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCostCenter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCounterReset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoverApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCrease.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreasingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreated.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreditCard.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutBlock.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBMergeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBRules.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSchema.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSelection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDCTParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDateTimeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeleted.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDensityMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDependencies.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCapPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevelopingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNSpace.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisjointing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroupPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisposition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDividingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDrop.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItemIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDurationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicInput.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEdgeGluing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoElementColorParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmboss.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployeeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheetGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEnumerationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoErrorData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEvent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExposedMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExternalImpositionTemplate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExtraValues.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFCNKey.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeatureAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeaturePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeeder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeederQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeedingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFitPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlateParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushResourceParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushedResources.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldOperation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFolderProduction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFormatConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangCmdFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangQuFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGatheringParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHardCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHeadBandApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHole.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFinishing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFolding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPHoleMaking.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPTrimming.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIcon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIconList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentical.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentificationField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompression.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompressionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageReplacementParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageSetterParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInk.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneCalculationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsert.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIntegerEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretedPDLData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIsPresentEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJBIG2Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFController.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJPEG2000Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJacketingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoKnownMsgQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLZWParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLabelingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLocation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongSlit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongitudinalRibbonOperationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMISDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMacroPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoManualLaborParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkActivation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMatrixEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaLayers.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaSource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMilestone.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMiscConsumable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModified.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModifyNodeCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMoveResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMsgFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNameEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoResp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOCGControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObjectResolution.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObservationTarget.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOccupation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOrderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFPathEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFToPSConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFXParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLResourceAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroupOccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPROccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRuleAttr.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPSToPDFConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageAssignedList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPallet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPalletizingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPartStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPayment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerformance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPixelColorant.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlaceHolderResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlateCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAnalysis.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightConstraint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstanceDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInventory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReport.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReportRulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreview.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreviewGenerationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPricing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintConditionColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintRollingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionSubPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityMeasurement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQuery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDefList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPosParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPriParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRectangleEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRefAnchor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterRibbon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegistration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoveLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoved.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRenderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceDefinitionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourcePullParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResponse.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoReturnQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRollStand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRuleLength.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRunList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScanParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScavengerArea.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScore.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreenSelector.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShrinkingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShutDownCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignatureCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSizeIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSoftCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpawned.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpinePreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpineTapingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStopPersChParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringListValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripCellParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrippingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubmissionMethods.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscription.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSystemTimeSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFEmbeddedFile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFFormatParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFtag.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabDimensions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestRef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThinPDFParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurve.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurvePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurveSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferFunctionControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrapRegion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingOrder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrigger.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrimmingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUpdateJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUsageCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValueLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoVerificationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWakeUpCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWebInlineFinishingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoXYPairEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutocall.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutochoice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutomacro.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutonot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutootherwise.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutowhen.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoxor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAutoLayoutElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTile.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/JDFTestCaseBase.java

Revision: 3689
Author: mucha
Date: 10:47:08, Donnerstag, 13. November 2008
Message:
fixes and additions for spec 1.4
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentMetaData.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkActivation.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCondition.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPositionObj.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRefAnchor.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRuleLength.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeDef.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabDimensions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeName.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElementName.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceQuParams.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFMarkActivation.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPageCondition.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFRefAnchor.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAutoLayoutElement.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContentMetaData.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPositionObj.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRuleLength.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFShapeDef.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTabDimensions.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanFluteDirection.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/auto/DirectoryInstantiateVisitor.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFLayoutTest.java

Revision: 3688
Author: mucha
Date: 10:44:48, Donnerstag, 13. November 2008
Message:
prepare auto files for 1.4, handcrafted, as the auto file generator is not fixed yet
----
Modified : /trunk/JDFLibJ/Readme.txt
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAcknowledge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoActionPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdded.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAddress.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdvancedParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalSuccess.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArgumentValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDelivery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssembly.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssemblySection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssetListCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAutomatedOverPrintParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeCompParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeReproParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBasicPreflightTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBendingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBinderySignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBlockPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBookCase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBooleanEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxToBoxDifference.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBufferParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundleItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundlingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBusinessInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoByteMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCCITTFaxParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCIELABMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCaseMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCasingInParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollatingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollectingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorControlStrip.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorMeasurementConditions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceSubstitute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantZoneDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorsUsed.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComChannel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCommand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCompany.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContact.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContactCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContainer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoConventionalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCostCenter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCounterReset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoverApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCrease.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreasingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreated.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreditCard.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutBlock.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBMergeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBRules.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSchema.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSelection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDCTParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDateTimeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeleted.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDensityMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDependencies.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCapPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevelopingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNSpace.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDieLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisjointing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroupPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisposition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDividingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDrop.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItemIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDurationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicInput.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEdgeGluing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoElementColorParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmboss.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployeeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheetGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEnumerationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoErrorData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEvent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExposedMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExternalImpositionTemplate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExtraValues.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFCNKey.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeatureAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeaturePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeeder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeederQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeedingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFitPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlateParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushResourceParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushedResources.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldOperation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFolderProduction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFormatConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangCmdFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangQuFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGatheringParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGeneralID.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHardCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHeadBandApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHole.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFinishing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFolding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPHoleMaking.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPTrimming.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIcon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIconList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentical.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentificationField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompression.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompressionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageReplacementParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageSetterParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInk.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneCalculationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsert.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIntegerEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretedPDLData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIsPresentEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJBIG2Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFController.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJPEG2000Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJacketingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoKnownMsgQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLZWParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLabelingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLocation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongSlit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongitudinalRibbonOperationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMISDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMacroPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoManualLaborParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMatrixEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaLayers.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaSource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMerged.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMilestone.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMiscConsumable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModified.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModifyNodeCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMoveResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMsgFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNameEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoResp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOCGControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObjectResolution.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObservationTarget.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOccupation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOrderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFPathEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFToPSConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFXParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLResourceAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroupOccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPROccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRuleAttr.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPSToPDFConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageAssignedList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPallet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPalletizingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPartStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPayment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerformance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPixelColorant.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlaceHolderResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlateCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAnalysis.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightConstraint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstanceDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInventory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReport.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReportRulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreview.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreviewGenerationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPricing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintConditionColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintRollingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionSubPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPublishingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityMeasurement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQuery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDefList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPosParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPriParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRectangleEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterRibbon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegistration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoveLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoved.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRenderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceDefinitionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourcePullParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResponse.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoReturnQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRollStand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRunList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScanParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScavengerArea.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScore.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreenSelector.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShrinkingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShutDownCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignatureCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSizeIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSoftCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpawned.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpinePreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpineTapingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStopPersChParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringListValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripCellParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrippingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubmissionMethods.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscription.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSystemTimeSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFEmbeddedFile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFFormatParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFtag.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestRef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThinPDFParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurve.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurvePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurveSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferFunctionControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrapRegion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingOrder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrigger.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrimmingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUpdateJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUsageCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValueLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoVerificationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWakeUpCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWebInlineFinishingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoXYPairEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutocall.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutochoice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutomacro.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutonot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutootherwise.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutowhen.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoxor.java



>ct find . -version "{lbtype(JDFLIBJ_2.1.4BLD540) && !lbtype(JDFLIBJ_2.1.3BLD530)}" -print
.@@\main\26
.\auto@@\main\45
.\auto\JDFAutoAcknowledge.java@@\main\55
.\auto\JDFAutoAction.java@@\main\30
.\auto\JDFAutoActionPool.java@@\main\24
.\auto\JDFAutoAdhesiveBindingParams.java@@\main\57
.\auto\JDFAutoAmountPool.java@@\main\45
.\auto\JDFAutoAncestorPool.java@@\main\45
.\auto\JDFAutoApprovalParams.java@@\main\50
.\auto\JDFAutoApprovalSuccess.java@@\main\62
.\auto\JDFAutoArgumentValue.java@@\main\26
.\auto\JDFAutoArtDelivery.java@@\main\78
.\auto\JDFAutoArtDeliveryIntent.java@@\main\74
.\auto\JDFAutoAssembly.java@@\main\33
.\auto\JDFAutoAssemblySection.java@@\main\33
.\auto\JDFAutoAssetListCreationParams.java@@\main\31
.\auto\JDFAutoAuthenticationCmdParams.java@@\main\1
.\auto\JDFAutoAuthenticationQuParams.java@@\main\1
.\auto\JDFAutoAuthenticationResp.java@@\main\1
.\auto\JDFAutoBand.java@@\main\46
.\auto\JDFAutoBarcodeReproParams.java@@\main\20
.\auto\JDFAutoBasicPreflightTest.java@@\main\33
.\auto\JDFAutoBinderySignature.java@@\main\35
.\auto\JDFAutoBindingIntent.java@@\main\69
.\auto\JDFAutoBindList.java@@\main\44
.\auto\JDFAutoBlockPreparationParams.java@@\main\59
.\auto\JDFAutoBooleanEvaluation.java@@\main\26
.\auto\JDFAutoBoxApplication.java@@\main\16
.\auto\JDFAutoBoxFoldAction.java@@\main\20
.\auto\JDFAutoBoxFoldingParams.java@@\main\22
.\auto\JDFAutoBoxPackingParams.java@@\main\47
.\auto\JDFAutoBundle.java@@\main\59
.\auto\JDFAutoByteMap.java@@\main\75
.\auto\JDFAutoCasingInParams.java@@\main\52
.\auto\JDFAutoChannelBindingParams.java@@\main\53
.\auto\JDFAutochoice.java@@\main\23
.\auto\JDFAutoCoilBindingParams.java@@\main\59
.\auto\JDFAutoColor.java@@\main\78
.\auto\JDFAutoColorantAlias.java@@\main\64
.\auto\JDFAutoColorantControl.java@@\main\73
.\auto\JDFAutoColorControlStrip.java@@\main\69
.\auto\JDFAutoColorCorrectionOp.java@@\main\51
.\auto\JDFAutoColorCorrectionParams.java@@\main\68
.\auto\JDFAutoColorPool.java@@\main\66
.\auto\JDFAutoColorSpaceConversionOp.java@@\main\63
.\auto\JDFAutoColorSpaceConversionParams.java@@\main\73
.\auto\JDFAutoColorSpaceSubstitute.java@@\main\60
.\auto\JDFAutoColorsUsed.java@@\main\22
.\auto\JDFAutoCommand.java@@\main\26
.\auto\JDFAutoCompany.java@@\main\65
.\auto\JDFAutoComponent.java@@\main\82
.\auto\JDFAutoContact.java@@\main\67
.\auto\JDFAutoContentData.java@@\main\16
.\auto\JDFAutoContentList.java@@\main\15
.\auto\JDFAutoContentMetaData.java@@\main\1
.\auto\JDFAutoContentObject.java@@\main\71
.\auto\JDFAutoControllerFilter.java@@\main\1
.\auto\JDFAutoConventionalPrintingParams.java@@\main\78
.\auto\JDFAutoConvertingConfig.java@@\main\1
.\auto\JDFAutoCoverApplicationParams.java@@\main\52
.\auto\JDFAutoCreasingParams.java@@\main\47
.\auto\JDFAutoCreated.java@@\main\43
.\auto\JDFAutoCreateResource.java@@\main\15
.\auto\JDFAutoCustomerInfo.java@@\main\56
.\auto\JDFAutoCustomerMessage.java@@\main\26
.\auto\JDFAutoCut.java@@\main\65
.\auto\JDFAutoCutBlock.java@@\main\60
.\auto\JDFAutoCutMark.java@@\main\71
.\auto\JDFAutoCuttingParams.java@@\main\48
.\auto\JDFAutoCylinderLayout.java@@\main\17
.\auto\JDFAutoDateTimeEvaluation.java@@\main\27
.\auto\JDFAutoDeleted.java@@\main\22
.\auto\JDFAutoDeliveryIntent.java@@\main\80
.\auto\JDFAutoDeliveryParams.java@@\main\67
.\auto\JDFAutoDependencies.java@@\main\23
.\auto\JDFAutoDevCap.java@@\main\52
.\auto\JDFAutoDevCapPool.java@@\main\14
.\auto\JDFAutoDevCaps.java@@\main\57
.\auto\JDFAutoDevice.java@@\main\72
.\auto\JDFAutoDeviceCap.java@@\main\54
.\auto\JDFAutoDeviceFilter.java@@\main\55
.\auto\JDFAutoDeviceInfo.java@@\main\67
.\auto\JDFAutoDeviceList.java@@\main\46
.\auto\JDFAutoDeviceMark.java@@\main\51
.\auto\JDFAutoDeviceNSpace.java@@\main\66
.\auto\JDFAutoDieLayout.java@@\main\16
.\auto\JDFAutoDieLayoutProductionParams.java@@\main\1
.\auto\JDFAutoDigitalDeliveryParams.java@@\main\33
.\auto\JDFAutoDigitalMedia.java@@\main\28
.\auto\JDFAutoDigitalPrintingParams.java@@\main\78
.\auto\JDFAutoDisjointing.java@@\main\75
.\auto\JDFAutoDisplayGroup.java@@\main\23
.\auto\JDFAutoDisplayGroupPool.java@@\main\23
.\auto\JDFAutoDrop.java@@\main\77
.\auto\JDFAutoDropIntent.java@@\main\71
.\auto\JDFAutoDropItem.java@@\main\65
.\auto\JDFAutoDurationEvaluation.java@@\main\27
.\auto\JDFAutoDynamicInput.java@@\main\53
.\auto\JDFAutoElementColorParams.java@@\main\36
.\auto\JDFAutoEmboss.java@@\main\54
.\auto\JDFAutoEmbossingIntent.java@@\main\45
.\auto\JDFAutoEmbossingItem.java@@\main\43
.\auto\JDFAutoEmbossingParams.java@@\main\48
.\auto\JDFAutoEndSheetGluingParams.java@@\main\61
.\auto\JDFAutoEnumerationEvaluation.java@@\main\26
.\auto\JDFAutoError.java@@\main\47
.\auto\JDFAutoExposedMedia.java@@\main\76
.\auto\JDFAutoExternalImpositionTemplate.java@@\main\14
.\auto\JDFAutoFeedingParams.java@@\main\25
.\auto\JDFAutoFileSpec.java@@\main\83
.\auto\JDFAutoFoldingIntent.java@@\main\65
.\auto\JDFAutoFoldingParams.java@@\main\73
.\auto\JDFAutoGeneralID.java@@\main\15
.\auto\JDFAutoGluingParams.java@@\main\50
.\auto\JDFAutoHardCoverBinding.java@@\main\47
.\auto\JDFAutoHeadBandApplicationParams.java@@\main\59
.\auto\JDFAutoHoleList.java@@\main\61
.\auto\JDFAutoHoleMakingParams.java@@\main\76
.\auto\JDFAutoIconList.java@@\main\42
.\auto\JDFAutoIdentificationField.java@@\main\69
.\auto\JDFAutoIDPCover.java@@\main\60
.\auto\JDFAutoIDPFinishing.java@@\main\53
.\auto\JDFAutoIDPFolding.java@@\main\57
.\auto\JDFAutoIDPHoleMaking.java@@\main\57
.\auto\JDFAutoIDPJobSheet.java@@\main\58
.\auto\JDFAutoIDPLayout.java@@\main\59
.\auto\JDFAutoIDPrintingParams.java@@\main\76
.\auto\JDFAutoIDPStitching.java@@\main\69
.\auto\JDFAutoIDPTrimming.java@@\main\60
.\auto\JDFAutoImageCompression.java@@\main\63
.\auto\JDFAutoImageCompressionParams.java@@\main\59
.\auto\JDFAutoImageReplacementParams.java@@\main\75
.\auto\JDFAutoImageSetterParams.java@@\main\62
.\auto\JDFAutoInk.java@@\main\65
.\auto\JDFAutoInsert.java@@\main\67
.\auto\JDFAutoInsertingParams.java@@\main\60
.\auto\JDFAutoInsertList.java@@\main\56
.\auto\JDFAutoIntegerEvaluation.java@@\main\27
.\auto\JDFAutoInterpretingParams.java@@\main\61
.\auto\JDFAutoIsPresentEvaluation.java@@\main\23
.\auto\JDFAutoJDFController.java@@\main\37
.\auto\JDFAutoJMF.java@@\main\66
.\auto\JDFAutoJobField.java@@\main\49
.\auto\JDFAutoJobPhase.java@@\main\69
.\auto\JDFAutoKnownMsgQuParams.java@@\main\43
.\auto\JDFAutoLayerDetails.java@@\main\40
.\auto\JDFAutoLayerList.java@@\main\46
.\auto\JDFAutoLayout.java@@\main\67
.\auto\JDFAutoLayoutElement.java@@\main\78
.\auto\JDFAutoLayoutElementPart.java@@\main\16
.\auto\JDFAutoLayoutElementProductionParams.java@@\main\15
.\auto\JDFAutoLayoutPreparationParams.java@@\main\66
.\auto\JDFAutoLayoutShift.java@@\main\1
.\auto\JDFAutoLocation.java@@\main\64
.\auto\JDFAutoLongitudinalRibbonOperationParams.java@@\main\61
.\auto\JDFAutomacro.java@@\main\24
.\auto\JDFAutoMacroPool.java@@\main\23
.\auto\JDFAutoMarkActivation.java@@\main\1
.\auto\JDFAutoMarkObject.java@@\main\78
.\auto\JDFAutoMatrixEvaluation.java@@\main\27
.\auto\JDFAutoMedia.java@@\main\83
.\auto\JDFAutoMediaIntent.java@@\main\66
.\auto\JDFAutoMediaLayers.java@@\main\14
.\auto\JDFAutoMerged.java@@\main\53
.\auto\JDFAutoMessage.java@@\main\53
.\auto\JDFAutoMessageService.java@@\main\60
.\auto\JDFAutoMiscConsumable.java@@\main\16
.\auto\JDFAutoModified.java@@\main\40
.\auto\JDFAutoModifyNodeCmdParams.java@@\main\21
.\auto\JDFAutoModule.java@@\main\17
.\auto\JDFAutoModulePhase.java@@\main\82
.\auto\JDFAutoModulePool.java@@\main\14
.\auto\JDFAutoModuleStatus.java@@\main\59
.\auto\JDFAutoMsgFilter.java@@\main\59
.\auto\JDFAutoNameEvaluation.java@@\main\26
.\auto\JDFAutoNewComment.java@@\main\17
.\auto\JDFAutoNodeInfo.java@@\main\80
.\auto\JDFAutoNodeInfoCmdParams.java@@\main\34
.\auto\JDFAutoNodeInfoResp.java@@\main\29
.\auto\JDFAutoNotification.java@@\main\62
.\auto\JDFAutoNotificationFilter.java@@\main\50
.\auto\JDFAutoNumberEvaluation.java@@\main\28
.\auto\JDFAutoNumberingIntent.java@@\main\45
.\auto\JDFAutoNumberingParams.java@@\main\59
.\auto\JDFAutoNumberItem.java@@\main\50
.\auto\JDFAutoObjectResolution.java@@\main\63
.\auto\JDFAutoOccupation.java@@\main\52
.\auto\JDFAutoOrderingParams.java@@\main\52
.\auto\JDFAutootherwise.java@@\main\23
.\auto\JDFAutoPageAssignParams.java@@\main\1
.\auto\JDFAutoPageCondition.java@@\main\1
.\auto\JDFAutoPageData.java@@\main\32
.\auto\JDFAutoPageElement.java@@\main\12
.\auto\JDFAutoPageList.java@@\main\32
.\auto\JDFAutoPallet.java@@\main\48
.\auto\JDFAutoPalletizingParams.java@@\main\46
.\auto\JDFAutoPart.java@@\main\55
.\auto\JDFAutoPDFInterpretingParams.java@@\main\58
.\auto\JDFAutoPDFPathEvaluation.java@@\main\28
.\auto\JDFAutoPerforatingParams.java@@\main\46
.\auto\JDFAutoPerson.java@@\main\53
.\auto\JDFAutoPhaseTime.java@@\main\63
.\auto\JDFAutoPipeParams.java@@\main\54
.\auto\JDFAutoPlasticCombBindingParams.java@@\main\60
.\auto\JDFAutoPositionObj.java@@\main\1
.\auto\JDFAutoPreflightArgument.java@@\main\22
.\auto\JDFAutoPreflightConstraint.java@@\main\65
.\auto\JDFAutoPreflightDetail.java@@\main\66
.\auto\JDFAutoPreflightInstance.java@@\main\66
.\auto\JDFAutoPreflightInstanceDetail.java@@\main\62
.\auto\JDFAutoPreflightParams.java@@\main\27
.\auto\JDFAutoPreflightReport.java@@\main\35
.\auto\JDFAutoPreflightReportRulePool.java@@\main\28
.\auto\JDFAutoPreview.java@@\main\70
.\auto\JDFAutoPRGroup.java@@\main\28
.\auto\JDFAutoPRGroupOccurrence.java@@\main\13
.\auto\JDFAutoPricing.java@@\main\67
.\auto\JDFAutoPrintCondition.java@@\main\30
.\auto\JDFAutoPrintConditionColor.java@@\main\32
.\auto\JDFAutoPRItem.java@@\main\28
.\auto\JDFAutoPROccurrence.java@@\main\14
.\auto\JDFAutoProcessRun.java@@\main\59
.\auto\JDFAutoProductionIntent.java@@\main\58
.\auto\JDFAutoProductionPath.java@@\main\18
.\auto\JDFAutoProofingIntent.java@@\main\55
.\auto\JDFAutoProofItem.java@@\main\54
.\auto\JDFAutoPRRule.java@@\main\26
.\auto\JDFAutoPublishingIntent.java@@\main\13
.\auto\JDFAutoQualityControlResult.java@@\main\28
.\auto\JDFAutoQuery.java@@\main\48
.\auto\JDFAutoQueue.java@@\main\61
.\auto\JDFAutoQueueEntry.java@@\main\65
.\auto\JDFAutoQueueEntryDefList.java@@\main\37
.\auto\JDFAutoQueueFilter.java@@\main\38
.\auto\JDFAutoRectangleEvaluation.java@@\main\33
.\auto\JDFAutoRefAnchor.java@@\main\1
.\auto\JDFAutoRegisterMark.java@@\main\73
.\auto\JDFAutoRegisterRibbon.java@@\main\52
.\auto\JDFAutoRegistration.java@@\main\14
.\auto\JDFAutoRenderingParams.java@@\main\74
.\auto\JDFAutoRepeatDesc.java@@\main\1
.\auto\JDFAutoRequestQueueEntryParams.java@@\main\33
.\auto\JDFAutoResourceAudit.java@@\main\53
.\auto\JDFAutoResourceCmdParams.java@@\main\57
.\auto\JDFAutoResourceDefinitionParams.java@@\main\60
.\auto\JDFAutoResourceInfo.java@@\main\65
.\auto\JDFAutoResourcePullParams.java@@\main\37
.\auto\JDFAutoResourceQuParams.java@@\main\60
.\auto\JDFAutoResponse.java@@\main\51
.\auto\JDFAutoRingBindingParams.java@@\main\58
.\auto\JDFAutoRollStand.java@@\main\27
.\auto\JDFAutoRuleLength.java@@\main\1
.\auto\JDFAutoRunList.java@@\main\81
.\auto\JDFAutoScavengerArea.java@@\main\53
.\auto\JDFAutoScreeningParams.java@@\main\66
.\auto\JDFAutoScreenSelector.java@@\main\68
.\auto\JDFAutoSeparationControlParams.java@@\main\47
.\auto\JDFAutoSeparationList.java@@\main\25
.\auto\JDFAutoShapeCuttingIntent.java@@\main\55
.\auto\JDFAutoShapeCuttingParams.java@@\main\53
.\auto\JDFAutoShapeDef.java@@\main\1
.\auto\JDFAutoShapeElement.java@@\main\46
.\auto\JDFAutoShapeEvaluation.java@@\main\28
.\auto\JDFAutoShiftPoint.java@@\main\1
.\auto\JDFAutoSignal.java@@\main\51
.\auto\JDFAutoSignatureCell.java@@\main\29
.\auto\JDFAutoSpawned.java@@\main\56
.\auto\JDFAutoSpineTapingParams.java@@\main\58
.\auto\JDFAutoStackingParams.java@@\main\52
.\auto\JDFAutoStation.java@@\main\14
.\auto\JDFAutoStatusPool.java@@\main\50
.\auto\JDFAutoStatusQuParams.java@@\main\53
.\auto\JDFAutoStitchingParams.java@@\main\68
.\auto\JDFAutoStopPersChParams.java@@\main\44
.\auto\JDFAutoStrap.java@@\main\54
.\auto\JDFAutoStringEvaluation.java@@\main\28
.\auto\JDFAutoStringListValue.java@@\main\26
.\auto\JDFAutoStripBindingParams.java@@\main\53
.\auto\JDFAutoStripMark.java@@\main\21
.\auto\JDFAutoStrippingParams.java@@\main\36
.\auto\JDFAutoSubmissionMethods.java@@\main\46
.\auto\JDFAutoSubscription.java@@\main\48
.\auto\JDFAutoSubscriptionFilter.java@@\main\1
.\auto\JDFAutoSubscriptionInfo.java@@\main\1
.\auto\JDFAutoTabDimensions.java@@\main\1
.\auto\JDFAutoTabs.java@@\main\60
.\auto\JDFAutoTape.java@@\main\46
.\auto\JDFAutoTestPool.java@@\main\26
.\auto\JDFAutoThreadSewingParams.java@@\main\62
.\auto\JDFAutoTIFFFormatParams.java@@\main\36
.\auto\JDFAutoTile.java@@\main\63
.\auto\JDFAutoTool.java@@\main\51
.\auto\JDFAutoTrackFilter.java@@\main\49
.\auto\JDFAutoTrackResult.java@@\main\46
.\auto\JDFAutoTransferCurvePool.java@@\main\61
.\auto\JDFAutoTransferCurveSet.java@@\main\66
.\auto\JDFAutoTrappingDetails.java@@\main\68
.\auto\JDFAutoTrappingOrder.java@@\main\56
.\auto\JDFAutoTrappingParams.java@@\main\76
.\auto\JDFAutoTrigger.java@@\main\45
.\auto\JDFAutoUpdateJDFCmdParams.java@@\main\14
.\auto\JDFAutoValue.java@@\main\33
.\auto\JDFAutoValueLoc.java@@\main\25
.\auto\JDFAutoWebInlineFinishingParams.java@@\main\15
.\auto\JDFAutowhen.java@@\main\23
.\auto\JDFAutoWireCombBindingParams.java@@\main\58
.\auto\JDFAutoXYPairEvaluation.java@@\main\32
.\core\AttributeName.java@@\main\50
.\core\DocumentJDFImpl.java@@\main\96
.\core\ElementName.java@@\main\43
.\core\ElemInfoTable.java@@\main\6
.\core\JDFAudit.java@@\main\96
.\core\JDFDoc.java@@\main\83
.\core\JDFElement.java@@\main\256
.\core\JDFNodeInfo.java@@\main\61
.\core\JDFResourceLink.java@@\main\151
.\core\KElement.java@@\main\272
.\core\VElement.java@@\main\36
.\core\VString.java@@\main\44
.\core\XMLDoc.java@@\main\98
.\datatypes\JDFAttributeMap.java@@\main\41
.\datatypes\VJDFAttributeMap.java@@\main\36
.\elementwalker@@\main\3
.\elementwalker\AttributeReplacer.java@@\main\1
.\elementwalker\SizeWalker.java@@\main\1
.\elementwalker\XPathWalker.java@@\main\1
.\extensions@@\main\1
.\extensions\XJDF20.java@@\main\2
.\extensions\xjdfwalker@@\main\1
.\extensions\xjdfwalker\XJDFToJDFConverter.java@@\main\1
.\goldenticket\BaseGoldenTicket.java@@\main\17
.\goldenticket\MISPreGoldenTicket.java@@\main\7
.\ifaces@@\main\9
.\ifaces\ISignalAudit.java@@\main\1
.\jmf@@\main\12
.\jmf\JDFAuthenticationCmdParams.java@@\main\1
.\jmf\JDFAuthenticationQuParams.java@@\main\1
.\jmf\JDFAuthenticationResp.java@@\main\1
.\jmf\JDFCertificate.java@@\main\1
.\jmf\JDFControllerFilter.java@@\main\1
.\jmf\JDFDeviceInfo.java@@\main\32
.\jmf\JDFKnownMsgQuParams.java@@\main\13
.\jmf\JDFMessage.java@@\main\81
.\jmf\JDFQueue.java@@\main\38
.\jmf\JDFQueueEntry.java@@\main\34
.\jmf\JDFQueueSubmissionParams.java@@\main\21
.\jmf\JDFResourceCmdParams.java@@\main\32
.\jmf\JDFResourceInfo.java@@\main\34
.\jmf\JDFResourceQuParams.java@@\main\21
.\jmf\JDFSubscriptionFilter.java@@\main\1
.\jmf\JDFSubscriptionInfo.java@@\main\1
.\node@@\main\11
.\node\AuditToJMF.java@@\main\1
.\node\JDFNode.java@@\main\283
.\pool\JDFAmountPool.java@@\main\35
.\pool\JDFAuditPool.java@@\main\111
.\pool\JDFResourceLinkPool.java@@\main\87
.\pool\JDFResourcePool.java@@\main\73
.\resource@@\main\35
.\resource\JDFMarkActivation.java@@\main\1
.\resource\JDFNotification.java@@\main\23
.\resource\JDFPageCondition.java@@\main\1
.\resource\JDFPart.java@@\main\32
.\resource\JDFPhaseTime.java@@\main\35
.\resource\JDFProcessRun.java@@\main\37
.\resource\JDFRefAnchor.java@@\main\1
.\resource\JDFResource.java@@\main\239
.\resource\JDFResourceAudit.java@@\main\37
.\resource\JDFSignature.java@@\main\26
.\resource\process@@\main\25
.\resource\process\JDFComponent.java@@\main\23
.\resource\process\JDFContentMetaData.java@@\main\1
.\resource\process\JDFConvertingConfig.java@@\main\1
.\resource\process\JDFDieLayout.java@@\main\5
.\resource\process\JDFDieLayoutProductionParams.java@@\main\1
.\resource\process\JDFLayoutShift.java@@\main\1
.\resource\process\JDFPageAssignParams.java@@\main\1
.\resource\process\JDFPositionObj.java@@\main\1
.\resource\process\JDFPreview.java@@\main\20
.\resource\process\JDFRegisterMark.java@@\main\20
.\resource\process\JDFRepeatDesc.java@@\main\1
.\resource\process\JDFRuleLength.java@@\main\1
.\resource\process\JDFShapeDef.java@@\main\1
.\resource\process\JDFShiftPoint.java@@\main\1
.\resource\process\JDFTabDimensions.java@@\main\1
.\resource\process\JDFTile.java@@\main\20
.\resource\process\postpress\JDFSheet.java@@\main\44
.\span@@\main\12
.\span\JDFSpanFluteDirection.java@@\main\1
.\util@@\main\28
.\util\BiHashMap.java@@\main\2
.\util\ContainerUtil.java@@\main\11
.\util\FileUtil.java@@\main\15
.\util\HotFolder.java@@\main\7
.\util\JDFSpawn.java@@\main\34
.\util\MimeUtil.java@@\main\32
.\util\MyInteger.java@@\main\2
.\util\MyPair.java@@\main\1
.\util\QueueHotFolder.java@@\main\9
.\util\RollingBackupFile.java@@\main\1
.\util\StringUtil.java@@\main\77
.\util\ThreadUtil.java@@\main\2
.\util\UrlUtil.java@@\main\25

___________________________________________________________


Label JDFLIBJ_2.1.3BLD530 (10.11.2008)


Files with real changes (no cosmetics like @override) :

Y:\JDFLibJ\src\org\cip4\jdflib\core\JDFAudit.java
Y:\JDFLibJ\src\org\cip4\jdflib\core\JDFElement
Y:\JDFLibJ\src\org\cip4\jdflib\core\KElement
Y:\JDFLibJ\src\org\cip4\jdflib\core\XMLDoc

Y:\JDFLibJ\src\org\cip4\jdflib\datatypes\JDFIntegerList.java
Y:\JDFLibJ\src\org\cip4\jdflib\datatypes\JDFIntegerRange.java
Y:\JDFLibJ\src\org\cip4\jdflib\datatypes\JDFNumList.java
Y:\JDFLibJ\src\org\cip4\jdflib\datatypes\VJDFAttributeMap.java

Y:\JDFLibJ\src\org\cip4\jdflib\elementwalker\BaseElementWalker.java
Y:\JDFLibJ\src\org\cip4\jdflib\elementwalker\BaseWalker.java
Y:\JDFLibJ\src\org\cip4\jdflib\elementwalker\ElementWalker.java
Y:\JDFLibJ\src\org\cip4\jdflib\elementwalker\IWalker.java
Y:\JDFLibJ\src\org\cip4\jdflib\elementwalker\UnLinkFinder.java

Y:\JDFLibJ\src\org\cip4\jdflib\goldenticket\BaseGoldenTicket.java
Y:\JDFLibJ\src\org\cip4\jdflib\goldenticket\MISCPGoldenTicket.java
Y:\JDFLibJ\src\org\cip4\jdflib\goldenticket\MISGoldenTicket.java

Y:\JDFLibJ\src\org\cip4\jdflib\jmf\JDFDeviceInfo.java
Y:\JDFLibJ\src\org\cip4\jdflib\jmf\JDFJMF.java
Y:\JDFLibJ\src\org\cip4\jdflib\jmf\JDFMessage.java
Y:\JDFLibJ\src\org\cip4\jdflib\jmf\JDFMessageService.java
Y:\JDFLibJ\src\org\cip4\jdflib\jmf\JDFQueueSubmissionParams.java
Y:\JDFLibJ\src\org\cip4\jdflib\jmf\JDFResponse.java

Y:\JDFLibJ\src\org\cip4\jdflib\node\JDFNode.java
Y:\JDFLibJ\src\org\cip4\jdflib\pool\JDFAmountPool.java

Y:\JDFLibJ\src\org\cip4\jdflib\resource\JDFNotification.java
Y:\JDFLibJ\src\org\cip4\jdflib\resource\JDFPart.java
Y:\JDFLibJ\src\org\cip4\jdflib\resource\JDFResource.java

Y:\JDFLibJ\src\org\cip4\jdflib\util\ContainerUtil.java
Y:\JDFLibJ\src\org\cip4\jdflib\util\DumpDir.java
Y:\JDFLibJ\src\org\cip4\jdflib\util\FileUtil.java
Y:\JDFLibJ\src\org\cip4\jdflib\util\HotFolder.java
Y:\JDFLibJ\src\org\cip4\jdflib\util\JDFSpawn.java
Y:\JDFLibJ\src\org\cip4\jdflib\util\MimeUtil.java
Y:\JDFLibJ\src\org\cip4\jdflib\util\MyInteger.java
Y:\JDFLibJ\src\org\cip4\jdflib\util\QueueHotFolder.java
Y:\JDFLibJ\src\org\cip4\jdflib\util\StatusCounter.java
Y:\JDFLibJ\src\org\cip4\jdflib\util\StringUtil.java
Y:\JDFLibJ\src\org\cip4\jdflib\util\ThreadUtil.java
Y:\JDFLibJ\src\org\cip4\jdflib\util\UrlUtil.java

Y:\JDFLibJ\src\org\cip4\jdflib\validate\JDFValidator.java





Revision: 3677
Author: mucha
Date: 15:27:26, Montag, 10. November 2008
Message:
cosmetics, adding @override
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElemInfoTable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDocumentBuilder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFDateTimeRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFDurationRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFXYRelation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java

Revision: 3669
Author: prosi
Date: 12:55:47, Montag, 10. November 2008
Message:
Last JDF 1.3 compatible update
----
Modified : /trunk/JDFLibJ/build.xml
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/QueueHotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFResourceTest.java

Revision: 3665
Author: mucha
Date: 11:37:21, Freitag, 7. November 2008
Message:

----
Modified : /trunk/JDFLibJ/Readme.txt
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java
Modified : /trunk/JDFLibJ/version.properties

Revision: 3663
Author: mucha
Date: 13:51:11, Mittwoch, 5. November 2008
Message:
cosmetics
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFIntegerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/CheckJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/JDFTestCaseBase.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFParserTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAncestorPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFColorPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java

Revision: 3659
Author: mucha
Date: 17:10:42, Dienstag, 4. November 2008
Message:
cleanup : add @Override
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAcknowledge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoActionPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdded.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAddress.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdvancedParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalSuccess.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArgumentValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDelivery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssembly.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssemblySection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssetListCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAutomatedOverPrintParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeCompParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeReproParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBasicPreflightTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBendingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBinderySignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBlockPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBookCase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBooleanEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxToBoxDifference.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBufferParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundleItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundlingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBusinessInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoByteMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCCITTFaxParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCIELABMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCaseMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCasingInParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollatingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollectingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorControlStrip.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorMeasurementConditions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceSubstitute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantZoneDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorsUsed.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComChannel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCommand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCompany.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContact.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContactCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContainer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoConventionalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCostCenter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCounterReset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoverApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCrease.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreasingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreated.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreditCard.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutBlock.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBMergeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBRules.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSchema.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSelection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDCTParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDateTimeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeleted.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDensityMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDependencies.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCapPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevelopingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNSpace.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDieLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisjointing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroupPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisposition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDividingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDrop.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItemIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDurationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicInput.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEdgeGluing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoElementColorParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmboss.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployeeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheetGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEnumerationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoErrorData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEvent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExposedMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExternalImpositionTemplate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExtraValues.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFCNKey.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeatureAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeaturePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeeder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeederQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeedingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFitPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlateParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushResourceParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushedResources.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldOperation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFolderProduction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFormatConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangCmdFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangQuFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGatheringParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGeneralID.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHardCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHeadBandApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHole.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFinishing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFolding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPHoleMaking.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPTrimming.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIcon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIconList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentical.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentificationField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompression.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompressionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageReplacementParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageSetterParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInk.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneCalculationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsert.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIntegerEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretedPDLData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIsPresentEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJBIG2Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFController.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJPEG2000Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJacketingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoKnownMsgQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLZWParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLabelingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLocation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongSlit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongitudinalRibbonOperationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMISDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMacroPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoManualLaborParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMatrixEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaLayers.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaSource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMerged.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMilestone.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMiscConsumable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModified.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModifyNodeCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMoveResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMsgFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNameEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoResp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOCGControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObjectResolution.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObservationTarget.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOccupation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOrderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFPathEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFToPSConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFXParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLResourceAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroupOccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPROccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRuleAttr.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPSToPDFConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageAssignedList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPallet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPalletizingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPartStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPayment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerformance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPixelColorant.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlaceHolderResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlateCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAnalysis.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightConstraint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstanceDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInventory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReport.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReportRulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreview.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreviewGenerationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPricing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintConditionColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintRollingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionSubPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPublishingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityMeasurement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQuery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDefList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPosParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPriParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRectangleEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterRibbon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegistration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoveLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoved.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRenderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceDefinitionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourcePullParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResponse.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoReturnQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRollStand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRunList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScanParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScavengerArea.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScore.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreenSelector.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShrinkingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShutDownCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignatureCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSizeIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSoftCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpawned.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpinePreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpineTapingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStopPersChParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringListValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripCellParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrippingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubmissionMethods.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscription.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSystemTimeSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFEmbeddedFile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFFormatParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFtag.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestRef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThinPDFParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurve.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurvePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurveSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferFunctionControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrapRegion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingOrder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrigger.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrimmingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUpdateJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUsageCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValueLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoVerificationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWakeUpCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWebInlineFinishingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoXYPairEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutocall.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutochoice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutomacro.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutonot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutootherwise.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutowhen.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoxor.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/CheckJDFTest.java

Revision: 3658
Author: mucha
Date: 16:45:09, Dienstag, 4. November 2008
Message:
cleanup : add @Override
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/SchemaComplexType.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/SchemaElement.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/AddJarDialog.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/AddXSDDialog.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/GeneratorUI.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/ListButtonPanel.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/OrganizeImports.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/StatusTable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AtrInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AtrInfoTable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElemInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFCustomerMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFPartStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFSeparationList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFCMYKColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFLabColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFMatrix.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumberList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRGBColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRectangle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFTransferFunction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFXYPair.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/JMFGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFAcknowledge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFAdded.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFChangedPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFCommand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFCreateLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFCreateResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFEmployeeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFFlushQueueParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFFlushResourceParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFFlushedResources.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFGangCmdFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFGangInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFGangQuFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFIDInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJDFController.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJDFService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFKnownMsgQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFModifyNodeCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMoveResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMsgFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNewComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNewJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNewJDFQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNodeInfoCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNodeInfoQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNodeInfoResp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNotificationDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFOccupation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQuery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntryDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntryPosParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntryPriParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFRegistration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFRemoveLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourcePullParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFReturnQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFShutDownCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFStopPersChParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFSubmissionMethods.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFSubscription.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFTrackFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFTrackResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFTrigger.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFUpdateJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFWakeUpCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFSpawned.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFStatusPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFAdhesiveBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBarcode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBindItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBindList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBlockPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBoxPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBufferParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBundle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBundleItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCaseMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCasingInParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFChangedAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCoilBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFColorMeasurementConditions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFColorsUsed.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFContactCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCounterReset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCoverApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCoverColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCreasingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCreated.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCreditCard.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDBSchema.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDeleted.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDevelopingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDeviceList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDeviceMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDimensions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEdgeGluing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEmboss.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEmbossingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEmbossingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEndSheetGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFErrorData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEvent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFFCNKey.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFFitPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFFormatConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFGatheringParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFHardCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFHeadBandApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFHoleLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFIDPCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFIDPImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFIDPJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFIcon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFIconList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFImageCompression.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFInsert.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFInsertList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFInsertingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFJacketingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFJobField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLabelingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLaminatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLayerDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLayerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLocation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFMarkObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFMerged.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFMilestone.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFModified.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFModulePhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFModuleStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFNumberItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFNumberingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFObservationTarget.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPageCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPageList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPages.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPallet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPalletizingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPayment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPerforatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPerformance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPlaceHolderResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPreflightAnalysis.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFProofItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFQueueEntryDefList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFRegisterRibbon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFRemoved.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResourceParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFScavengerArea.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFShapeCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFShapeElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFShrinkingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSoftCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSpinePreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSpineTapingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFStackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFStrap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFStrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFStripBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFStrippingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSystemTimeSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFTabs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFTape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFThreadSealingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFTool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFTransferFunctionControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFVerificationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFWeight.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFWrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFActionPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFBasicPreflightTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDateTimeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDateTimeState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCapPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDisplayGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDisplayGroupPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDurationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDurationState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEnumerationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFFeatureAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFFeaturePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFIntegerEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFIntegerState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFIsPresentEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFMacroPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFModule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFModuleCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFNumberEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFNumberState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFPDFPathEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFPDFPathState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFRectangleEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFRectangleState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFShapeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFShapeState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFStringEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFStringState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFTerm.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFTestPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFValueLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFXYPairEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFXYPairState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFcall.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFchoice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFmacro.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFotherwise.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFwhen.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFArtDelivery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFArtDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFArtDeliveryType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFBindingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFBookCase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFColorIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFDropIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFEmbossingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFFoldingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFHoleMakingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFInsertingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFLaminatingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFLayoutIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFMediaIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFNumberingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFPackingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFPricing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFProofingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFPublishingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFScreeningIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFShapeCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFShapeCuttingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAddress.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAdvancedParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFApprovalDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFApprovalParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFApprovalPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFApprovalSuccess.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFArgumentValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAssembly.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAssemblySection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAutomatedOverPrintParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBarcodeCompParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBarcodeDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBarcodeProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBarcodeReproParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBendingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBinderySignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBindingQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBoxApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBoxArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBoxFoldAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBoxFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBoxToBoxDifference.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBusinessInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFByteMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCCITTFaxParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCIELABMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCollatingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCollectingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorControlStrip.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorantAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorantZoneDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorsResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCompany.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFConstraintValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContainer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContentData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContentList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFConventionalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCostCenter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCylinderLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCylinderLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCylinderPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDBMergeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDBRules.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDBSelection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDCTParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDensityMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDependencies.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDeviceNColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDeviceNSpace.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDieLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDigitalMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDigitalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDisjointing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDisposition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDividingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDocumentResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDrop.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDropItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDynamicField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDynamicInput.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFElementColorParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFExternalImpositionTemplate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFExtraValues.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFeeder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFeederQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFeedingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFileAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFlateParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFoldOperation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFolderProduction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFontParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFontPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFGeneralID.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPFinishing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPFolding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPHoleMaking.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPTrimming.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIdentical.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIdentificationField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFImageCompressionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFImageReplacementParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFImageSetterParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFImagesResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFInsertSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFInterpretedPDLData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFJBIG2Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFJPEG2000Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLZWParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayoutElementPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayoutElementProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLongFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLongGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLongPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLongSlit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLongitudinalRibbonOperationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMISDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFManualLaborParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMediaLayers.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMediaSource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMiscConsumable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFNotificationFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFNumberingParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFOCGControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFObjectResolution.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFOrderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPDFXParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPDLCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRGroupOccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPROccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRRule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRRuleAttr.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPageAssignedList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPageData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPageElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPixelColorant.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPlateCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreflightAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreflightArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreflightParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreflightReport.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreflightReportRulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreview.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPrintConditionColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPrintRollingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFProductionPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFProductionSubPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFProofingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFQualityControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFQualityControlResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFQualityMeasurement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRegisterMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFResourceDefinitionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRollStand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFScreenSelector.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSealing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSeparationControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSeparationSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSignatureCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFStation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFStringListValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFStripCellParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFStripMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTIFFEmbeddedFile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTIFFFormatParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTIFFtag.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFThinPDFParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTransferCurvePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTransferCurveSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTrapRegion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFUsageCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFAdhesiveBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFBundlingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFChannelBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFChannelBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFCoilBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFCrease.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFCutMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFEndSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFGlueApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFGlueLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFHole.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFHoleList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFHoleMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFPlasticCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFPlasticCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFRingBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFRingBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSaddleStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSaddleStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFScore.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSideSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSideSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSideStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFStripBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFThreadSealing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFThreadSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFThreadSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFTrimmingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFVeloBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFWebInlineFinishingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFWireCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFWireCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFColorCorrectionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFColorCorrectionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFColorSpaceConversionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFColorSpaceConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFColorSpaceSubstitute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFDigitalDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFFileTypeResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFFontsResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFInk.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFInkZoneCalculationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFInkZoneProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPDFToPSConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPDLResourceAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPSToPDFConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPagesResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightConstraint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightInstance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightInstanceDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightInventory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreviewGenerationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFRenderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFScanParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFScreeningParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFTrappingDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFTrappingOrder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFTrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/press/JDFIDPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/press/JDFPrintCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFDurationSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFEnumerationSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFIntegerSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFNameSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFNumberSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFOptionSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFShapeSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanArtHandling.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanBindingType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanHoleType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanNamedColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanWireCombShape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFTimeSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFXYPairSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/SScanf.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/FixVersionTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFPartAmountTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFTestType.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFIntegerRangeListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFPathTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFDurationStateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFIntegerStateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFMatrixStateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFNumberStateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/AutomatedLayoutTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/PreflightTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/RIPTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/StrippingTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFDeviceInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFJobPhaseTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueEntryTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFStatusTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFStrippingTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFColorTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFColorantControlTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFContentObjectTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFDieLayoutTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFPageDataTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MyArgsTest.java

Revision: 3657
Author: mucha
Date: 16:02:50, Dienstag, 4. November 2008
Message:
cosmetics for getChildElementVector_JDFElement
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java

Revision: 3654
Author: mucha
Date: 10:08:48, Dienstag, 4. November 2008
Message:
cosmetics, 1.4 preparations
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeName.java
Modified : /trunk/JDFLibJ/Readme.txt
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/gui/ComplexTypeList.java

Revision: 3646
Author: prosi
Date: 12:55:48, Donnerstag, 30. Oktober 2008
Message:
Bambi updates from Tokyo testing
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAuditPoolTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/DigiPrintTest.java

Revision: 3635
Author: prosi
Date: 04:13:22, Sonntag, 12. Oktober 2008
Message:

----
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java

Revision: 3634
Author: prosi
Date: 01:09:04, Sonntag, 12. Oktober 2008
Message:
Bambi updates from Tokyo testing
----
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/elementwalker/UnlinkFinderTest.java

Revision: 3633
Author: prosi
Date: 01:07:53, Sonntag, 12. Oktober 2008
Message:
Bambi updates from Tokyo testing
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/UnLinkFinder.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ThreadUtil.java

Revision: 3631
Author: prosi
Date: 10:50:02, Freitag, 10. Oktober 2008
Message:
Bambi updates from Tokyo testing
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/IDPGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISCPGoldenTicketTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/IDPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFDeviceInfoTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFJobPhaseTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/BaseGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFLayoutTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/HotFolderTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/QueueHotFolder.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/PrefixInputStream.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/QueueHotFolderTest.java

Revision: 3609
Author: prosi
Date: 03:25:16, Sonntag, 5. Oktober 2008
Message:
MyInteger is now a top-level class
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MyInteger.java

Revision: 3608
Author: prosi
Date: 03:20:17, Sonntag, 5. Oktober 2008
Message:
added heuristic partition key sequence finder to JDFPart
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalkerFactory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/ElementWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/IWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/UnLinkFinder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/elementwalker/BaseWalkerTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/ContainerUtilTest.java
Modified : /trunk/Bambi/.classpath
Modified : /trunk/Bambi/src/lib/JDFLibJ-2.1.3.jar
Modified : /trunk/JDFLibC/tests/TestWrapper/TestWrapperCore.cpp
Modified : /trunk/JDFEditor/src/java/org/cip4/jdfeditor/JDFTreeModel.java
Modified : /trunk/JDFEditor/.classpath
Modified : /trunk/JDFEditor/src/java/org/cip4/jdfeditor/PopUpRightClick.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPart.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/VStringTest.java

Revision: 3605
Author: prosi
Date: 13:36:20, Donnerstag, 2. Oktober 2008
Message:
queue thread test timing
----
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java

Revision: 3603
Author: prosi
Date: 13:03:52, Donnerstag, 2. Oktober 2008
Message:
unit test fix for hotfolder longer rest times
----
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/HotFolderTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/QueueHotFolderTest.java

Revision: 3602
Author: prosi
Date: 12:34:05, Donnerstag, 2. Oktober 2008
Message:

----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java

Revision: 3601
Author: prosi
Date: 11:23:33, Donnerstag, 2. Oktober 2008
Message:
JDFNode.setpartstatus fix for nodeinfos that are linked by one part
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/ElementWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java
Added : /trunk/JDFLibJ/test/data/dir1
Added : /trunk/JDFLibJ/test/data/dir1/dir2Not.txt
Added : /trunk/JDFLibJ/test/data/dir1/dir2a
Added : /trunk/JDFLibJ/test/data/dir1/dir2a/test3a.txt
Added : /trunk/JDFLibJ/test/data/dir1/dir2a/test3b.txt
Added : /trunk/JDFLibJ/test/data/dir1/dir2b
Added : /trunk/JDFLibJ/test/data/dir1/dir2b/test3a.txt
Added : /trunk/JDFLibJ/test/data/dir1/dir2b/test3b.txt
Added : /trunk/JDFLibJ/test/data/dir1/test2.txt



>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD530) && !lbtype(JDFLIBJ_2.1.3BLD522)}" -print
.\auto\JDFAutoAcknowledge.java@@\main\54
.\auto\JDFAutoAction.java@@\main\29
.\auto\JDFAutoActionPool.java@@\main\23
.\auto\JDFAutoAdded.java@@\main\35
.\auto\JDFAutoAddress.java@@\main\64
.\auto\JDFAutoAdhesiveBinding.java@@\main\56
.\auto\JDFAutoAdhesiveBindingParams.java@@\main\56
.\auto\JDFAutoAdvancedParams.java@@\main\63
.\auto\JDFAutoAmountPool.java@@\main\44
.\auto\JDFAutoAncestor.java@@\main\69
.\auto\JDFAutoAncestorPool.java@@\main\44
.\auto\JDFAutoand.java@@\main\15
.\auto\JDFAutoApprovalDetails.java@@\main\22
.\auto\JDFAutoApprovalParams.java@@\main\49
.\auto\JDFAutoApprovalPerson.java@@\main\69
.\auto\JDFAutoApprovalSuccess.java@@\main\61
.\auto\JDFAutoArgumentValue.java@@\main\25
.\auto\JDFAutoArtDelivery.java@@\main\77
.\auto\JDFAutoArtDeliveryIntent.java@@\main\73
.\auto\JDFAutoAssembly.java@@\main\32
.\auto\JDFAutoAssemblySection.java@@\main\32
.\auto\JDFAutoAssetListCreationParams.java@@\main\30
.\auto\JDFAutoAutomatedOverPrintParams.java@@\main\63
.\auto\JDFAutoBand.java@@\main\45
.\auto\JDFAutoBarcode.java@@\main\41
.\auto\JDFAutoBarcodeCompParams.java@@\main\16
.\auto\JDFAutoBarcodeDetails.java@@\main\11
.\auto\JDFAutoBarcodeProductionParams.java@@\main\3
.\auto\JDFAutoBarcodeReproParams.java@@\main\19
.\auto\JDFAutoBasicPreflightTest.java@@\main\32
.\auto\JDFAutoBendingParams.java@@\main\13
.\auto\JDFAutoBinderySignature.java@@\main\34
.\auto\JDFAutoBindingIntent.java@@\main\68
.\auto\JDFAutoBindingQualityParams.java@@\main\20
.\auto\JDFAutoBindItem.java@@\main\50
.\auto\JDFAutoBindList.java@@\main\43
.\auto\JDFAutoBlockPreparationParams.java@@\main\58
.\auto\JDFAutoBookCase.java@@\main\55
.\auto\JDFAutoBooleanEvaluation.java@@\main\25
.\auto\JDFAutoBoxApplication.java@@\main\15
.\auto\JDFAutoBoxArgument.java@@\main\26
.\auto\JDFAutoBoxFoldAction.java@@\main\19
.\auto\JDFAutoBoxFoldingParams.java@@\main\21
.\auto\JDFAutoBoxPackingParams.java@@\main\46
.\auto\JDFAutoBoxToBoxDifference.java@@\main\25
.\auto\JDFAutoBufferParams.java@@\main\48
.\auto\JDFAutoBundle.java@@\main\58
.\auto\JDFAutoBundleItem.java@@\main\57
.\auto\JDFAutoBundlingParams.java@@\main\22
.\auto\JDFAutoBusinessInfo.java@@\main\42
.\auto\JDFAutoByteMap.java@@\main\74
.\auto\JDFAutocall.java@@\main\20
.\auto\JDFAutoCaseMakingParams.java@@\main\49
.\auto\JDFAutoCasingInParams.java@@\main\51
.\auto\JDFAutoCCITTFaxParams.java@@\main\21
.\auto\JDFAutoChangedAttribute.java@@\main\44
.\auto\JDFAutoChangedPath.java@@\main\26
.\auto\JDFAutoChannelBinding.java@@\main\52
.\auto\JDFAutoChannelBindingParams.java@@\main\52
.\auto\JDFAutochoice.java@@\main\22
.\auto\JDFAutoCIELABMeasuringField.java@@\main\72
.\auto\JDFAutoCoilBinding.java@@\main\52
.\auto\JDFAutoCoilBindingParams.java@@\main\58
.\auto\JDFAutoCollatingItem.java@@\main\30
.\auto\JDFAutoCollectingParams.java@@\main\36
.\auto\JDFAutoColor.java@@\main\77
.\auto\JDFAutoColorantAlias.java@@\main\63
.\auto\JDFAutoColorantControl.java@@\main\72
.\auto\JDFAutoColorantZoneDetails.java@@\main\57
.\auto\JDFAutoColorControlStrip.java@@\main\68
.\auto\JDFAutoColorCorrectionOp.java@@\main\50
.\auto\JDFAutoColorCorrectionParams.java@@\main\67
.\auto\JDFAutoColorIntent.java@@\main\54
.\auto\JDFAutoColorMeasurementConditions.java@@\main\55
.\auto\JDFAutoColorPool.java@@\main\65
.\auto\JDFAutoColorSpaceConversionOp.java@@\main\62
.\auto\JDFAutoColorSpaceConversionParams.java@@\main\72
.\auto\JDFAutoColorSpaceSubstitute.java@@\main\59
.\auto\JDFAutoColorsUsed.java@@\main\21
.\auto\JDFAutoComChannel.java@@\main\66
.\auto\JDFAutoCommand.java@@\main\25
.\auto\JDFAutoComment.java@@\main\64
.\auto\JDFAutoCompany.java@@\main\64
.\auto\JDFAutoComponent.java@@\main\81
.\auto\JDFAutoContact.java@@\main\66
.\auto\JDFAutoContactCopyParams.java@@\main\57
.\auto\JDFAutoContainer.java@@\main\20
.\auto\JDFAutoContentData.java@@\main\15
.\auto\JDFAutoContentList.java@@\main\14
.\auto\JDFAutoContentObject.java@@\main\70
.\auto\JDFAutoConventionalPrintingParams.java@@\main\77
.\auto\JDFAutoCostCenter.java@@\main\57
.\auto\JDFAutoCounterReset.java@@\main\40
.\auto\JDFAutoCoverApplicationParams.java@@\main\51
.\auto\JDFAutoCrease.java@@\main\63
.\auto\JDFAutoCreasingParams.java@@\main\46
.\auto\JDFAutoCreated.java@@\main\42
.\auto\JDFAutoCreateLink.java@@\main\14
.\auto\JDFAutoCreateResource.java@@\main\14
.\auto\JDFAutoCreditCard.java@@\main\40
.\auto\JDFAutoCustomerInfo.java@@\main\55
.\auto\JDFAutoCustomerMessage.java@@\main\25
.\auto\JDFAutoCut.java@@\main\64
.\auto\JDFAutoCutBlock.java@@\main\59
.\auto\JDFAutoCutMark.java@@\main\70
.\auto\JDFAutoCuttingParams.java@@\main\47
.\auto\JDFAutoCylinderLayout.java@@\main\16
.\auto\JDFAutoCylinderLayoutPreparationParams.java@@\main\13
.\auto\JDFAutoCylinderPosition.java@@\main\14
.\auto\JDFAutoDateTimeEvaluation.java@@\main\26
.\auto\JDFAutoDBMergeParams.java@@\main\51
.\auto\JDFAutoDBRules.java@@\main\36
.\auto\JDFAutoDBSchema.java@@\main\51
.\auto\JDFAutoDBSelection.java@@\main\48
.\auto\JDFAutoDCTParams.java@@\main\29
.\auto\JDFAutoDeleted.java@@\main\21
.\auto\JDFAutoDeliveryIntent.java@@\main\79
.\auto\JDFAutoDeliveryParams.java@@\main\66
.\auto\JDFAutoDensityMeasuringField.java@@\main\69
.\auto\JDFAutoDependencies.java@@\main\22
.\auto\JDFAutoDevCap.java@@\main\51
.\auto\JDFAutoDevCapPool.java@@\main\13
.\auto\JDFAutoDevCaps.java@@\main\56
.\auto\JDFAutoDevelopingParams.java@@\main\51
.\auto\JDFAutoDevice.java@@\main\71
.\auto\JDFAutoDeviceCap.java@@\main\53
.\auto\JDFAutoDeviceFilter.java@@\main\54
.\auto\JDFAutoDeviceInfo.java@@\main\66
.\auto\JDFAutoDeviceList.java@@\main\45
.\auto\JDFAutoDeviceMark.java@@\main\50
.\auto\JDFAutoDeviceNColor.java@@\main\44
.\auto\JDFAutoDeviceNSpace.java@@\main\65
.\auto\JDFAutoDieLayout.java@@\main\15
.\auto\JDFAutoDigitalDeliveryParams.java@@\main\32
.\auto\JDFAutoDigitalMedia.java@@\main\27
.\auto\JDFAutoDigitalPrintingParams.java@@\main\77
.\auto\JDFAutoDisjointing.java@@\main\74
.\auto\JDFAutoDisplayGroup.java@@\main\22
.\auto\JDFAutoDisplayGroupPool.java@@\main\22
.\auto\JDFAutoDisposition.java@@\main\35
.\auto\JDFAutoDividingParams.java@@\main\57
.\auto\JDFAutoDrop.java@@\main\76
.\auto\JDFAutoDropIntent.java@@\main\70
.\auto\JDFAutoDropItem.java@@\main\64
.\auto\JDFAutoDropItemIntent.java@@\main\58
.\auto\JDFAutoDurationEvaluation.java@@\main\26
.\auto\JDFAutoDynamicField.java@@\main\60
.\auto\JDFAutoDynamicInput.java@@\main\52
.\auto\JDFAutoEdgeGluing.java@@\main\42
.\auto\JDFAutoElementColorParams.java@@\main\35
.\auto\JDFAutoEmboss.java@@\main\53
.\auto\JDFAutoEmbossingIntent.java@@\main\44
.\auto\JDFAutoEmbossingItem.java@@\main\42
.\auto\JDFAutoEmbossingParams.java@@\main\47
.\auto\JDFAutoEmployee.java@@\main\64
.\auto\JDFAutoEmployeeDef.java@@\main\39
.\auto\JDFAutoEndSheet.java@@\main\63
.\auto\JDFAutoEndSheetGluingParams.java@@\main\60
.\auto\JDFAutoEnumerationEvaluation.java@@\main\25
.\auto\JDFAutoError.java@@\main\46
.\auto\JDFAutoErrorData.java@@\main\3
.\auto\JDFAutoEvent.java@@\main\20
.\auto\JDFAutoExposedMedia.java@@\main\75
.\auto\JDFAutoExternalImpositionTemplate.java@@\main\13
.\auto\JDFAutoExtraValues.java@@\main\11
.\auto\JDFAutoFCNKey.java@@\main\38
.\auto\JDFAutoFeatureAttribute.java@@\main\27
.\auto\JDFAutoFeaturePool.java@@\main\17
.\auto\JDFAutoFeeder.java@@\main\31
.\auto\JDFAutoFeederQualityParams.java@@\main\26
.\auto\JDFAutoFeedingParams.java@@\main\24
.\auto\JDFAutoFileAlias.java@@\main\64
.\auto\JDFAutoFileSpec.java@@\main\82
.\auto\JDFAutoFitPolicy.java@@\main\52
.\auto\JDFAutoFlateParams.java@@\main\21
.\auto\JDFAutoFlushedResources.java@@\main\15
.\auto\JDFAutoFlushQueueInfo.java@@\main\10
.\auto\JDFAutoFlushQueueParams.java@@\main\20
.\auto\JDFAutoFlushResourceParams.java@@\main\29
.\auto\JDFAutoFold.java@@\main\70
.\auto\JDFAutoFolderProduction.java@@\main\13
.\auto\JDFAutoFoldingIntent.java@@\main\64
.\auto\JDFAutoFoldingParams.java@@\main\72
.\auto\JDFAutoFoldOperation.java@@\main\8
.\auto\JDFAutoFontParams.java@@\main\67
.\auto\JDFAutoFontPolicy.java@@\main\59
.\auto\JDFAutoFormatConversionParams.java@@\main\49
.\auto\JDFAutoGangCmdFilter.java@@\main\10
.\auto\JDFAutoGangInfo.java@@\main\10
.\auto\JDFAutoGangQuFilter.java@@\main\10
.\auto\JDFAutoGatheringParams.java@@\main\46
.\auto\JDFAutoGeneralID.java@@\main\14
.\auto\JDFAutoGlue.java@@\main\72
.\auto\JDFAutoGlueApplication.java@@\main\69
.\auto\JDFAutoGlueLine.java@@\main\68
.\auto\JDFAutoGluingParams.java@@\main\49
.\auto\JDFAutoHardCoverBinding.java@@\main\46
.\auto\JDFAutoHeadBandApplicationParams.java@@\main\58
.\auto\JDFAutoHole.java@@\main\65
.\auto\JDFAutoHoleLine.java@@\main\49
.\auto\JDFAutoHoleList.java@@\main\60
.\auto\JDFAutoHoleMakingIntent.java@@\main\68
.\auto\JDFAutoHoleMakingParams.java@@\main\75
.\auto\JDFAutoIcon.java@@\main\49
.\auto\JDFAutoIconList.java@@\main\41
.\auto\JDFAutoIdentical.java@@\main\9
.\auto\JDFAutoIdentificationField.java@@\main\68
.\auto\JDFAutoIDInfo.java@@\main\25
.\auto\JDFAutoIDPCover.java@@\main\59
.\auto\JDFAutoIDPFinishing.java@@\main\52
.\auto\JDFAutoIDPFolding.java@@\main\56
.\auto\JDFAutoIDPHoleMaking.java@@\main\56
.\auto\JDFAutoIDPImageShift.java@@\main\45
.\auto\JDFAutoIDPJobSheet.java@@\main\57
.\auto\JDFAutoIDPLayout.java@@\main\58
.\auto\JDFAutoIDPrintingParams.java@@\main\75
.\auto\JDFAutoIDPStitching.java@@\main\68
.\auto\JDFAutoIDPTrimming.java@@\main\59
.\auto\JDFAutoImageCompression.java@@\main\62
.\auto\JDFAutoImageCompressionParams.java@@\main\58
.\auto\JDFAutoImageReplacementParams.java@@\main\74
.\auto\JDFAutoImageSetterParams.java@@\main\61
.\auto\JDFAutoImageShift.java@@\main\63
.\auto\JDFAutoInk.java@@\main\64
.\auto\JDFAutoInkZoneCalculationParams.java@@\main\64
.\auto\JDFAutoInkZoneProfile.java@@\main\61
.\auto\JDFAutoInsert.java@@\main\66
.\auto\JDFAutoInsertingIntent.java@@\main\54
.\auto\JDFAutoInsertingParams.java@@\main\59
.\auto\JDFAutoInsertList.java@@\main\55
.\auto\JDFAutoInsertSheet.java@@\main\75
.\auto\JDFAutoIntegerEvaluation.java@@\main\26
.\auto\JDFAutoInterpretedPDLData.java@@\main\35
.\auto\JDFAutoInterpretingParams.java@@\main\60
.\auto\JDFAutoIsPresentEvaluation.java@@\main\22
.\auto\JDFAutoJacketingParams.java@@\main\42
.\auto\JDFAutoJBIG2Params.java@@\main\11
.\auto\JDFAutoJDFController.java@@\main\36
.\auto\JDFAutoJDFService.java@@\main\47
.\auto\JDFAutoJMF.java@@\main\65
.\auto\JDFAutoJobField.java@@\main\48
.\auto\JDFAutoJobPhase.java@@\main\68
.\auto\JDFAutoJPEG2000Params.java@@\main\16
.\auto\JDFAutoKnownMsgQuParams.java@@\main\42
.\auto\JDFAutoLabelingParams.java@@\main\50
.\auto\JDFAutoLaminatingIntent.java@@\main\58
.\auto\JDFAutoLaminatingParams.java@@\main\55
.\auto\JDFAutoLayerDetails.java@@\main\39
.\auto\JDFAutoLayerList.java@@\main\45
.\auto\JDFAutoLayout.java@@\main\66
.\auto\JDFAutoLayoutElement.java@@\main\77
.\auto\JDFAutoLayoutElementPart.java@@\main\15
.\auto\JDFAutoLayoutElementProductionParams.java@@\main\14
.\auto\JDFAutoLayoutIntent.java@@\main\68
.\auto\JDFAutoLayoutPreparationParams.java@@\main\65
.\auto\JDFAutoLoc.java@@\main\21
.\auto\JDFAutoLocation.java@@\main\63
.\auto\JDFAutoLongFold.java@@\main\56
.\auto\JDFAutoLongGlue.java@@\main\64
.\auto\JDFAutoLongitudinalRibbonOperationParams.java@@\main\60
.\auto\JDFAutoLongPerforate.java@@\main\56
.\auto\JDFAutoLongSlit.java@@\main\56
.\auto\JDFAutoLot.java@@\main\14
.\auto\JDFAutoLZWParams.java@@\main\21
.\auto\JDFAutomacro.java@@\main\23
.\auto\JDFAutoMacroPool.java@@\main\22
.\auto\JDFAutoManualLaborParams.java@@\main\42
.\auto\JDFAutoMarkObject.java@@\main\77
.\auto\JDFAutoMatrixEvaluation.java@@\main\26
.\auto\JDFAutoMedia.java@@\main\82
.\auto\JDFAutoMediaIntent.java@@\main\65
.\auto\JDFAutoMediaLayers.java@@\main\13
.\auto\JDFAutoMediaSource.java@@\main\73
.\auto\JDFAutoMerged.java@@\main\52
.\auto\JDFAutoMessage.java@@\main\52
.\auto\JDFAutoMessageService.java@@\main\59
.\auto\JDFAutoMilestone.java@@\main\2
.\auto\JDFAutoMiscConsumable.java@@\main\15
.\auto\JDFAutoMISDetails.java@@\main\28
.\auto\JDFAutoModified.java@@\main\39
.\auto\JDFAutoModifyNodeCmdParams.java@@\main\20
.\auto\JDFAutoModule.java@@\main\16
.\auto\JDFAutoModuleCap.java@@\main\16
.\auto\JDFAutoModulePhase.java@@\main\81
.\auto\JDFAutoModulePool.java@@\main\13
.\auto\JDFAutoModuleStatus.java@@\main\58
.\auto\JDFAutoMoveResource.java@@\main\10
.\auto\JDFAutoMsgFilter.java@@\main\58
.\auto\JDFAutoNameEvaluation.java@@\main\25
.\auto\JDFAutoNewComment.java@@\main\16
.\auto\JDFAutoNewJDFCmdParams.java@@\main\24
.\auto\JDFAutoNewJDFQuParams.java@@\main\22
.\auto\JDFAutoNodeInfo.java@@\main\79
.\auto\JDFAutoNodeInfoCmdParams.java@@\main\33
.\auto\JDFAutoNodeInfoQuParams.java@@\main\24
.\auto\JDFAutoNodeInfoResp.java@@\main\28
.\auto\JDFAutonot.java@@\main\15
.\auto\JDFAutoNotification.java@@\main\61
.\auto\JDFAutoNotificationDef.java@@\main\44
.\auto\JDFAutoNotificationFilter.java@@\main\49
.\auto\JDFAutoNumberEvaluation.java@@\main\27
.\auto\JDFAutoNumberingIntent.java@@\main\44
.\auto\JDFAutoNumberingParam.java@@\main\45
.\auto\JDFAutoNumberingParams.java@@\main\58
.\auto\JDFAutoNumberItem.java@@\main\49
.\auto\JDFAutoObjectResolution.java@@\main\62
.\auto\JDFAutoObservationTarget.java@@\main\45
.\auto\JDFAutoOccupation.java@@\main\51
.\auto\JDFAutoOCGControl.java@@\main\11
.\auto\JDFAutoor.java@@\main\15
.\auto\JDFAutoOrderingParams.java@@\main\51
.\auto\JDFAutootherwise.java@@\main\22
.\auto\JDFAutoPackingIntent.java@@\main\56
.\auto\JDFAutoPackingParams.java@@\main\55
.\auto\JDFAutoPageAssignedList.java@@\main\11
.\auto\JDFAutoPageCell.java@@\main\56
.\auto\JDFAutoPageData.java@@\main\31
.\auto\JDFAutoPageElement.java@@\main\11
.\auto\JDFAutoPageList.java@@\main\31
.\auto\JDFAutoPallet.java@@\main\47
.\auto\JDFAutoPalletizingParams.java@@\main\45
.\auto\JDFAutoPart.java@@\main\54
.\auto\JDFAutoPartStatus.java@@\main\48
.\auto\JDFAutoPayment.java@@\main\42
.\auto\JDFAutoPDFInterpretingParams.java@@\main\57
.\auto\JDFAutoPDFPathEvaluation.java@@\main\27
.\auto\JDFAutoPDFToPSConversionParams.java@@\main\69
.\auto\JDFAutoPDFXParams.java@@\main\27
.\auto\JDFAutoPDLCreationParams.java@@\main\15
.\auto\JDFAutoPDLResourceAlias.java@@\main\61
.\auto\JDFAutoPerforate.java@@\main\64
.\auto\JDFAutoPerforatingParams.java@@\main\45
.\auto\JDFAutoPerformance.java@@\main\38
.\auto\JDFAutoPerson.java@@\main\52
.\auto\JDFAutoPhaseTime.java@@\main\62
.\auto\JDFAutoPipeParams.java@@\main\53
.\auto\JDFAutoPixelColorant.java@@\main\42
.\auto\JDFAutoPlaceHolderResource.java@@\main\23
.\auto\JDFAutoPlasticCombBinding.java@@\main\52
.\auto\JDFAutoPlasticCombBindingParams.java@@\main\59
.\auto\JDFAutoPlateCopyParams.java@@\main\53
.\auto\JDFAutoPosition.java@@\main\30
.\auto\JDFAutoPreflightAction.java@@\main\25
.\auto\JDFAutoPreflightAnalysis.java@@\main\45
.\auto\JDFAutoPreflightArgument.java@@\main\21
.\auto\JDFAutoPreflightConstraint.java@@\main\64
.\auto\JDFAutoPreflightDetail.java@@\main\65
.\auto\JDFAutoPreflightInstance.java@@\main\65
.\auto\JDFAutoPreflightInstanceDetail.java@@\main\61
.\auto\JDFAutoPreflightInventory.java@@\main\45
.\auto\JDFAutoPreflightParams.java@@\main\26
.\auto\JDFAutoPreflightProfile.java@@\main\46
.\auto\JDFAutoPreflightReport.java@@\main\34
.\auto\JDFAutoPreflightReportRulePool.java@@\main\27
.\auto\JDFAutoPRError.java@@\main\28
.\auto\JDFAutoPreview.java@@\main\69
.\auto\JDFAutoPreviewGenerationParams.java@@\main\62
.\auto\JDFAutoPRGroup.java@@\main\27
.\auto\JDFAutoPRGroupOccurrence.java@@\main\12
.\auto\JDFAutoPricing.java@@\main\66
.\auto\JDFAutoPrintCondition.java@@\main\29
.\auto\JDFAutoPrintConditionColor.java@@\main\31
.\auto\JDFAutoPrintRollingParams.java@@\main\21
.\auto\JDFAutoPRItem.java@@\main\27
.\auto\JDFAutoPROccurrence.java@@\main\13
.\auto\JDFAutoProcessRun.java@@\main\58
.\auto\JDFAutoProductionIntent.java@@\main\57
.\auto\JDFAutoProductionPath.java@@\main\17
.\auto\JDFAutoProductionSubPath.java@@\main\12
.\auto\JDFAutoProofingIntent.java@@\main\54
.\auto\JDFAutoProofingParams.java@@\main\72
.\auto\JDFAutoProofItem.java@@\main\53
.\auto\JDFAutoPRRule.java@@\main\25
.\auto\JDFAutoPRRuleAttr.java@@\main\20
.\auto\JDFAutoPSToPDFConversionParams.java@@\main\73
.\auto\JDFAutoPublishingIntent.java@@\main\12
.\auto\JDFAutoQualityControlParams.java@@\main\29
.\auto\JDFAutoQualityControlResult.java@@\main\27
.\auto\JDFAutoQualityMeasurement.java@@\main\31
.\auto\JDFAutoQuery.java@@\main\47
.\auto\JDFAutoQueue.java@@\main\60
.\auto\JDFAutoQueueEntry.java@@\main\64
.\auto\JDFAutoQueueEntryDef.java@@\main\37
.\auto\JDFAutoQueueEntryDefList.java@@\main\36
.\auto\JDFAutoQueueEntryPosParams.java@@\main\44
.\auto\JDFAutoQueueEntryPriParams.java@@\main\43
.\auto\JDFAutoQueueFilter.java@@\main\37
.\auto\JDFAutoQueueSubmissionParams.java@@\main\53
.\auto\JDFAutoRectangleEvaluation.java@@\main\32
.\auto\JDFAutoRegisterMark.java@@\main\72
.\auto\JDFAutoRegisterRibbon.java@@\main\51
.\auto\JDFAutoRegistration.java@@\main\13
.\auto\JDFAutoRemoved.java@@\main\34
.\auto\JDFAutoRemoveLink.java@@\main\14
.\auto\JDFAutoRenderingParams.java@@\main\73
.\auto\JDFAutoRequestQueueEntryParams.java@@\main\32
.\auto\JDFAutoResourceAudit.java@@\main\52
.\auto\JDFAutoResourceCmdParams.java@@\main\56
.\auto\JDFAutoResourceDefinitionParams.java@@\main\59
.\auto\JDFAutoResourceInfo.java@@\main\64
.\auto\JDFAutoResourceParam.java@@\main\47
.\auto\JDFAutoResourcePullParams.java@@\main\36
.\auto\JDFAutoResourceQuParams.java@@\main\59
.\auto\JDFAutoResponse.java@@\main\50
.\auto\JDFAutoResubmissionParams.java@@\main\38
.\auto\JDFAutoReturnQueueEntryParams.java@@\main\21
.\auto\JDFAutoRingBinding.java@@\main\51
.\auto\JDFAutoRingBindingParams.java@@\main\57
.\auto\JDFAutoRollStand.java@@\main\26
.\auto\JDFAutoRunList.java@@\main\80
.\auto\JDFAutoSaddleStitching.java@@\main\41
.\auto\JDFAutoSaddleStitchingParams.java@@\main\55
.\auto\JDFAutoScanParams.java@@\main\74
.\auto\JDFAutoScavengerArea.java@@\main\52
.\auto\JDFAutoScore.java@@\main\56
.\auto\JDFAutoScreeningIntent.java@@\main\20
.\auto\JDFAutoScreeningParams.java@@\main\65
.\auto\JDFAutoScreenSelector.java@@\main\67
.\auto\JDFAutoSeparationControlParams.java@@\main\46
.\auto\JDFAutoSeparationList.java@@\main\24
.\auto\JDFAutoSeparationSpec.java@@\main\45
.\auto\JDFAutoset.java@@\main\24
.\auto\JDFAutoShapeCut.java@@\main\63
.\auto\JDFAutoShapeCuttingIntent.java@@\main\54
.\auto\JDFAutoShapeCuttingParams.java@@\main\52
.\auto\JDFAutoShapeElement.java@@\main\45
.\auto\JDFAutoShapeEvaluation.java@@\main\27
.\auto\JDFAutoShrinkingParams.java@@\main\56
.\auto\JDFAutoShutDownCmdParams.java@@\main\29
.\auto\JDFAutoSideSewing.java@@\main\29
.\auto\JDFAutoSideSewingParams.java@@\main\56
.\auto\JDFAutoSideStitching.java@@\main\35
.\auto\JDFAutoSignal.java@@\main\50
.\auto\JDFAutoSignatureCell.java@@\main\28
.\auto\JDFAutoSizeIntent.java@@\main\66
.\auto\JDFAutoSoftCoverBinding.java@@\main\43
.\auto\JDFAutoSpawned.java@@\main\55
.\auto\JDFAutoSpinePreparationParams.java@@\main\46
.\auto\JDFAutoSpineTapingParams.java@@\main\57
.\auto\JDFAutoStackingParams.java@@\main\51
.\auto\JDFAutoStation.java@@\main\13
.\auto\JDFAutoStatusPool.java@@\main\49
.\auto\JDFAutoStatusQuParams.java@@\main\52
.\auto\JDFAutoStitchingParams.java@@\main\67
.\auto\JDFAutoStopPersChParams.java@@\main\43
.\auto\JDFAutoStrap.java@@\main\53
.\auto\JDFAutoStrappingParams.java@@\main\51
.\auto\JDFAutoStringEvaluation.java@@\main\27
.\auto\JDFAutoStringListValue.java@@\main\25
.\auto\JDFAutoStripBinding.java@@\main\36
.\auto\JDFAutoStripBindingParams.java@@\main\52
.\auto\JDFAutoStripCellParams.java@@\main\28
.\auto\JDFAutoStripMark.java@@\main\20
.\auto\JDFAutoStrippingParams.java@@\main\35
.\auto\JDFAutoSubmissionMethods.java@@\main\45
.\auto\JDFAutoSubscription.java@@\main\47
.\auto\JDFAutoSystemTimeSet.java@@\main\46
.\auto\JDFAutoTabs.java@@\main\59
.\auto\JDFAutoTape.java@@\main\45
.\auto\JDFAutoTest.java@@\main\19
.\auto\JDFAutoTestPool.java@@\main\25
.\auto\JDFAutoTestRef.java@@\main\20
.\auto\JDFAutoThinPDFParams.java@@\main\56
.\auto\JDFAutoThreadSealing.java@@\main\29
.\auto\JDFAutoThreadSealingParams.java@@\main\54
.\auto\JDFAutoThreadSewing.java@@\main\42
.\auto\JDFAutoThreadSewingParams.java@@\main\61
.\auto\JDFAutoTIFFEmbeddedFile.java@@\main\24
.\auto\JDFAutoTIFFFormatParams.java@@\main\35
.\auto\JDFAutoTIFFtag.java@@\main\24
.\auto\JDFAutoTile.java@@\main\62
.\auto\JDFAutoTool.java@@\main\50
.\auto\JDFAutoTrackFilter.java@@\main\48
.\auto\JDFAutoTrackResult.java@@\main\45
.\auto\JDFAutoTransferCurve.java@@\main\63
.\auto\JDFAutoTransferCurvePool.java@@\main\60
.\auto\JDFAutoTransferCurveSet.java@@\main\65
.\auto\JDFAutoTransferFunctionControl.java@@\main\67
.\auto\JDFAutoTrappingDetails.java@@\main\67
.\auto\JDFAutoTrappingOrder.java@@\main\55
.\auto\JDFAutoTrappingParams.java@@\main\75
.\auto\JDFAutoTrapRegion.java@@\main\52
.\auto\JDFAutoTrigger.java@@\main\44
.\auto\JDFAutoTrimmingParams.java@@\main\68
.\auto\JDFAutoUpdateJDFCmdParams.java@@\main\13
.\auto\JDFAutoUsageCounter.java@@\main\19
.\auto\JDFAutoValue.java@@\main\32
.\auto\JDFAutoValueLoc.java@@\main\24
.\auto\JDFAutoVerificationParams.java@@\main\48
.\auto\JDFAutoWakeUpCmdParams.java@@\main\15
.\auto\JDFAutoWebInlineFinishingParams.java@@\main\14
.\auto\JDFAutowhen.java@@\main\22
.\auto\JDFAutoWireCombBinding.java@@\main\52
.\auto\JDFAutoWireCombBindingParams.java@@\main\57
.\auto\JDFAutoWrappingParams.java@@\main\48
.\auto\JDFAutoxor.java@@\main\15
.\auto\JDFAutoXYPairEvaluation.java@@\main\31
.\core\AtrInfo.java@@\main\8
.\core\AtrInfoTable.java@@\main\12
.\core\AttributeName.java@@\main\49
.\core\ElementName.java@@\main\42
.\core\ElemInfo.java@@\main\4
.\core\ElemInfoTable.java@@\main\5
.\core\JDFAudit.java@@\main\95
.\core\JDFComment.java@@\main\31
.\core\JDFCustomerMessage.java@@\main\9
.\core\JDFDocumentBuilder.java@@\main\5
.\core\JDFElement.java@@\main\254
.\core\JDFPartStatus.java@@\main\26
.\core\JDFSeparationList.java@@\main\26
.\core\KElement.java@@\main\269
.\core\XMLDoc.java@@\main\97
.\datatypes\JDFCMYKColor.java@@\main\9
.\datatypes\JDFDateTimeRange.java@@\main\16
.\datatypes\JDFDurationRange.java@@\main\17
.\datatypes\JDFIntegerList.java@@\main\19
.\datatypes\JDFIntegerRange.java@@\main\34
.\datatypes\JDFLabColor.java@@\main\8
.\datatypes\JDFMatrix.java@@\main\21
.\datatypes\JDFNumberList.java@@\main\10
.\datatypes\JDFNumList.java@@\main\35
.\datatypes\JDFRectangle.java@@\main\23
.\datatypes\JDFRGBColor.java@@\main\8
.\datatypes\JDFTransferFunction.java@@\main\10
.\datatypes\JDFXYPair.java@@\main\21
.\datatypes\JDFXYRelation.java@@\main\3
.\datatypes\VJDFAttributeMap.java@@\main\35
.\elementwalker\BaseElementWalker.java@@\main\2
.\elementwalker\BaseWalker.java@@\main\4
.\elementwalker\ElementWalker.java@@\main\2
.\elementwalker\IWalker.java@@\main\3
.\elementwalker\UnLinkFinder.java@@\main\4
.\goldenticket\BaseGoldenTicket.java@@\main\16
.\goldenticket\JMFGoldenTicket.java@@\main\4
.\goldenticket\MISCPGoldenTicket.java@@\main\15
.\goldenticket\MISGoldenTicket.java@@\main\14
.\jmf\JDFAcknowledge.java@@\main\23
.\jmf\JDFAdded.java@@\main\5
.\jmf\JDFChangedPath.java@@\main\9
.\jmf\JDFCommand.java@@\main\24
.\jmf\JDFCreateLink.java@@\main\6
.\jmf\JDFCreateResource.java@@\main\5
.\jmf\JDFDeviceFilter.java@@\main\12
.\jmf\JDFDeviceInfo.java@@\main\31
.\jmf\JDFEmployeeDef.java@@\main\12
.\jmf\JDFFlushedResources.java@@\main\10
.\jmf\JDFFlushQueueParams.java@@\main\11
.\jmf\JDFFlushResourceParams.java@@\main\11
.\jmf\JDFGangCmdFilter.java@@\main\5
.\jmf\JDFGangInfo.java@@\main\5
.\jmf\JDFGangQuFilter.java@@\main\5
.\jmf\JDFIDInfo.java@@\main\11
.\jmf\JDFJDFController.java@@\main\12
.\jmf\JDFJDFService.java@@\main\12
.\jmf\JDFJMF.java@@\main\74
.\jmf\JDFKnownMsgQuParams.java@@\main\12
.\jmf\JDFMessage.java@@\main\80
.\jmf\JDFMessageService.java@@\main\21
.\jmf\JDFModifyNodeCmdParams.java@@\main\5
.\jmf\JDFMoveResource.java@@\main\5
.\jmf\JDFMsgFilter.java@@\main\18
.\jmf\JDFNewComment.java@@\main\5
.\jmf\JDFNewJDFCmdParams.java@@\main\10
.\jmf\JDFNewJDFQuParams.java@@\main\11
.\jmf\JDFNodeInfoCmdParams.java@@\main\16
.\jmf\JDFNodeInfoQuParams.java@@\main\15
.\jmf\JDFNodeInfoResp.java@@\main\16
.\jmf\JDFNotificationDef.java@@\main\14
.\jmf\JDFOccupation.java@@\main\18
.\jmf\JDFQuery.java@@\main\23
.\jmf\JDFQueueEntryDef.java@@\main\15
.\jmf\JDFQueueEntryPosParams.java@@\main\12
.\jmf\JDFQueueEntryPriParams.java@@\main\15
.\jmf\JDFQueueSubmissionParams.java@@\main\20
.\jmf\JDFRegistration.java@@\main\6
.\jmf\JDFRemoveLink.java@@\main\5
.\jmf\JDFRequestQueueEntryParams.java@@\main\15
.\jmf\JDFResourcePullParams.java@@\main\16
.\jmf\JDFResponse.java@@\main\33
.\jmf\JDFResubmissionParams.java@@\main\12
.\jmf\JDFReturnQueueEntryParams.java@@\main\10
.\jmf\JDFShutDownCmdParams.java@@\main\11
.\jmf\JDFStopPersChParams.java@@\main\18
.\jmf\JDFSubmissionMethods.java@@\main\12
.\jmf\JDFSubscription.java@@\main\13
.\jmf\JDFTrackFilter.java@@\main\19
.\jmf\JDFTrackResult.java@@\main\18
.\jmf\JDFTrigger.java@@\main\12
.\jmf\JDFUpdateJDFCmdParams.java@@\main\5
.\jmf\JDFWakeUpCmdParams.java@@\main\11
.\node\JDFNode.java@@\main\282
.\node\JDFSpawned.java@@\main\29
.\pool\JDFAmountPool.java@@\main\34
.\pool\JDFStatusPool.java@@\main\48
.\resource\devicecapability\JDFAction.java@@\main\18
.\resource\devicecapability\JDFActionPool.java@@\main\9
.\resource\devicecapability\JDFBasicPreflightTest.java@@\main\6
.\resource\devicecapability\JDFcall.java@@\main\6
.\resource\devicecapability\JDFchoice.java@@\main\6
.\resource\devicecapability\JDFDateTimeEvaluation.java@@\main\18
.\resource\devicecapability\JDFDateTimeState.java@@\main\31
.\resource\devicecapability\JDFDevCapPool.java@@\main\12
.\resource\devicecapability\JDFDisplayGroup.java@@\main\6
.\resource\devicecapability\JDFDisplayGroupPool.java@@\main\6
.\resource\devicecapability\JDFDurationEvaluation.java@@\main\16
.\resource\devicecapability\JDFDurationState.java@@\main\30
.\resource\devicecapability\JDFEnumerationEvaluation.java@@\main\19
.\resource\devicecapability\JDFFeatureAttribute.java@@\main\6
.\resource\devicecapability\JDFFeaturePool.java@@\main\7
.\resource\devicecapability\JDFIntegerEvaluation.java@@\main\17
.\resource\devicecapability\JDFIntegerState.java@@\main\32
.\resource\devicecapability\JDFIsPresentEvaluation.java@@\main\8
.\resource\devicecapability\JDFLoc.java@@\main\7
.\resource\devicecapability\JDFmacro.java@@\main\6
.\resource\devicecapability\JDFMacroPool.java@@\main\6
.\resource\devicecapability\JDFModule.java@@\main\7
.\resource\devicecapability\JDFModuleCap.java@@\main\3
.\resource\devicecapability\JDFNumberEvaluation.java@@\main\18
.\resource\devicecapability\JDFNumberState.java@@\main\32
.\resource\devicecapability\JDFotherwise.java@@\main\6
.\resource\devicecapability\JDFPDFPathEvaluation.java@@\main\14
.\resource\devicecapability\JDFPDFPathState.java@@\main\27
.\resource\devicecapability\JDFRectangleEvaluation.java@@\main\20
.\resource\devicecapability\JDFRectangleState.java@@\main\33
.\resource\devicecapability\JDFset.java@@\main\6
.\resource\devicecapability\JDFShapeEvaluation.java@@\main\18
.\resource\devicecapability\JDFShapeState.java@@\main\33
.\resource\devicecapability\JDFStringEvaluation.java@@\main\16
.\resource\devicecapability\JDFStringState.java@@\main\28
.\resource\devicecapability\JDFTerm.java@@\main\8
.\resource\devicecapability\JDFTestPool.java@@\main\9
.\resource\devicecapability\JDFValueLoc.java@@\main\6
.\resource\devicecapability\JDFwhen.java@@\main\6
.\resource\devicecapability\JDFXYPairEvaluation.java@@\main\19
.\resource\devicecapability\JDFXYPairState.java@@\main\30
.\resource\intent\JDFArtDelivery.java@@\main\24
.\resource\intent\JDFArtDeliveryIntent.java@@\main\20
.\resource\intent\JDFArtDeliveryType.java@@\main\14
.\resource\intent\JDFBindingIntent.java@@\main\20
.\resource\intent\JDFBookCase.java@@\main\19
.\resource\intent\JDFColorIntent.java@@\main\19
.\resource\intent\JDFDeliveryIntent.java@@\main\19
.\resource\intent\JDFDropIntent.java@@\main\22
.\resource\intent\JDFEmbossingIntent.java@@\main\9
.\resource\intent\JDFFoldingIntent.java@@\main\20
.\resource\intent\JDFHoleMakingIntent.java@@\main\19
.\resource\intent\JDFInsertingIntent.java@@\main\19
.\resource\intent\JDFLaminatingIntent.java@@\main\19
.\resource\intent\JDFLayoutIntent.java@@\main\18
.\resource\intent\JDFMediaIntent.java@@\main\17
.\resource\intent\JDFNumberingIntent.java@@\main\9
.\resource\intent\JDFPackingIntent.java@@\main\18
.\resource\intent\JDFPricing.java@@\main\17
.\resource\intent\JDFProofingIntent.java@@\main\19
.\resource\intent\JDFPublishingIntent.java@@\main\4
.\resource\intent\JDFScreeningIntent.java@@\main\4
.\resource\intent\JDFShapeCut.java@@\main\18
.\resource\intent\JDFShapeCuttingIntent.java@@\main\18
.\resource\JDFAdhesiveBindingParams.java@@\main\10
.\resource\JDFBand.java@@\main\20
.\resource\JDFBarcode.java@@\main\10
.\resource\JDFBindItem.java@@\main\13
.\resource\JDFBindList.java@@\main\9
.\resource\JDFBlockPreparationParams.java@@\main\9
.\resource\JDFBoxPackingParams.java@@\main\9
.\resource\JDFBufferParams.java@@\main\9
.\resource\JDFBundle.java@@\main\11
.\resource\JDFBundleItem.java@@\main\12
.\resource\JDFCaseMakingParams.java@@\main\9
.\resource\JDFCasingInParams.java@@\main\9
.\resource\JDFChangedAttribute.java@@\main\9
.\resource\JDFCoilBindingParams.java@@\main\9
.\resource\JDFColorMeasurementConditions.java@@\main\11
.\resource\JDFColorsUsed.java@@\main\9
.\resource\JDFContactCopyParams.java@@\main\10
.\resource\JDFCounterReset.java@@\main\9
.\resource\JDFCoverApplicationParams.java@@\main\11
.\resource\JDFCoverColor.java@@\main\10
.\resource\JDFCreasingParams.java@@\main\9
.\resource\JDFCreated.java@@\main\10
.\resource\JDFCreditCard.java@@\main\9
.\resource\JDFDBSchema.java@@\main\9
.\resource\JDFDeleted.java@@\main\4
.\resource\JDFDevelopingParams.java@@\main\10
.\resource\JDFDevice.java@@\main\12
.\resource\JDFDeviceList.java@@\main\9
.\resource\JDFDeviceMark.java@@\main\11
.\resource\JDFDimensions.java@@\main\11
.\resource\JDFEdgeGluing.java@@\main\9
.\resource\JDFEmboss.java@@\main\11
.\resource\JDFEmbossingItem.java@@\main\10
.\resource\JDFEmbossingParams.java@@\main\9
.\resource\JDFEndSheetGluingParams.java@@\main\9
.\resource\JDFError.java@@\main\9
.\resource\JDFErrorData.java@@\main\5
.\resource\JDFEvent.java@@\main\4
.\resource\JDFFCNKey.java@@\main\9
.\resource\JDFFitPolicy.java@@\main\11
.\resource\JDFFormatConversionParams.java@@\main\9
.\resource\JDFGatheringParams.java@@\main\9
.\resource\JDFGluingParams.java@@\main\9
.\resource\JDFHardCoverBinding.java@@\main\9
.\resource\JDFHeadBandApplicationParams.java@@\main\9
.\resource\JDFHoleLine.java@@\main\9
.\resource\JDFIcon.java@@\main\11
.\resource\JDFIconList.java@@\main\10
.\resource\JDFIDPCover.java@@\main\9
.\resource\JDFIDPImageShift.java@@\main\9
.\resource\JDFIDPJobSheet.java@@\main\9
.\resource\JDFImageCompression.java@@\main\22
.\resource\JDFImageShift.java@@\main\18
.\resource\JDFInsert.java@@\main\18
.\resource\JDFInsertingParams.java@@\main\9
.\resource\JDFInsertList.java@@\main\18
.\resource\JDFInterpretingParams.java@@\main\10
.\resource\JDFJacketingParams.java@@\main\9
.\resource\JDFJobField.java@@\main\9
.\resource\JDFJobSheet.java@@\main\9
.\resource\JDFLabelingParams.java@@\main\9
.\resource\JDFLaminatingParams.java@@\main\13
.\resource\JDFLayerDetails.java@@\main\9
.\resource\JDFLayerList.java@@\main\10
.\resource\JDFLayoutPreparationParams.java@@\main\9
.\resource\JDFLocation.java@@\main\16
.\resource\JDFMarkObject.java@@\main\25
.\resource\JDFMerged.java@@\main\14
.\resource\JDFMilestone.java@@\main\3
.\resource\JDFModified.java@@\main\11
.\resource\JDFModulePhase.java@@\main\9
.\resource\JDFModuleStatus.java@@\main\10
.\resource\JDFNotification.java@@\main\22
.\resource\JDFNumberingParams.java@@\main\9
.\resource\JDFNumberItem.java@@\main\10
.\resource\JDFObservationTarget.java@@\main\9
.\resource\JDFPageCell.java@@\main\9
.\resource\JDFPageList.java@@\main\6
.\resource\JDFPages.java@@\main\10
.\resource\JDFPallet.java@@\main\9
.\resource\JDFPalletizingParams.java@@\main\9
.\resource\JDFPart.java@@\main\31
.\resource\JDFPayment.java@@\main\9
.\resource\JDFPDFInterpretingParams.java@@\main\9
.\resource\JDFPerforatingParams.java@@\main\9
.\resource\JDFPerformance.java@@\main\9
.\resource\JDFPlaceHolderResource.java@@\main\7
.\resource\JDFPreflightAnalysis.java@@\main\9
.\resource\JDFProofItem.java@@\main\10
.\resource\JDFQueueEntryDefList.java@@\main\9
.\resource\JDFRegisterRibbon.java@@\main\11
.\resource\JDFRemoved.java@@\main\9
.\resource\JDFResource.java@@\main\238
.\resource\JDFResourceParam.java@@\main\10
.\resource\JDFScavengerArea.java@@\main\9
.\resource\JDFShapeCuttingParams.java@@\main\9
.\resource\JDFShapeElement.java@@\main\9
.\resource\JDFShrinkingParams.java@@\main\9
.\resource\JDFSoftCoverBinding.java@@\main\9
.\resource\JDFSpinePreparationParams.java@@\main\11
.\resource\JDFSpineTapingParams.java@@\main\11
.\resource\JDFStackingParams.java@@\main\9
.\resource\JDFStrap.java@@\main\9
.\resource\JDFStrappingParams.java@@\main\9
.\resource\JDFStripBinding.java@@\main\9
.\resource\JDFStrippingParams.java@@\main\4
.\resource\JDFSystemTimeSet.java@@\main\9
.\resource\JDFTabs.java@@\main\14
.\resource\JDFTape.java@@\main\9
.\resource\JDFThreadSealingParams.java@@\main\9
.\resource\JDFTool.java@@\main\14
.\resource\JDFTransferFunctionControl.java@@\main\9
.\resource\JDFVerificationParams.java@@\main\9
.\resource\JDFWeight.java@@\main\10
.\resource\JDFWrappingParams.java@@\main\10
.\resource\process\JDFAddress.java@@\main\20
.\resource\process\JDFAdvancedParams.java@@\main\21
.\resource\process\JDFApprovalDetails.java@@\main\4
.\resource\process\JDFApprovalParams.java@@\main\9
.\resource\process\JDFApprovalPerson.java@@\main\9
.\resource\process\JDFApprovalSuccess.java@@\main\22
.\resource\process\JDFArgumentValue.java@@\main\6
.\resource\process\JDFAssembly.java@@\main\6
.\resource\process\JDFAssemblySection.java@@\main\7
.\resource\process\JDFAutomatedOverPrintParams.java@@\main\17
.\resource\process\JDFBarcodeCompParams.java@@\main\4
.\resource\process\JDFBarcodeDetails.java@@\main\4
.\resource\process\JDFBarcodeProductionParams.java@@\main\3
.\resource\process\JDFBarcodeReproParams.java@@\main\4
.\resource\process\JDFBendingParams.java@@\main\4
.\resource\process\JDFBinderySignature.java@@\main\10
.\resource\process\JDFBindingQualityParams.java@@\main\6
.\resource\process\JDFBoxApplication.java@@\main\4
.\resource\process\JDFBoxArgument.java@@\main\7
.\resource\process\JDFBoxFoldAction.java@@\main\4
.\resource\process\JDFBoxFoldingParams.java@@\main\4
.\resource\process\JDFBoxToBoxDifference.java@@\main\6
.\resource\process\JDFBusinessInfo.java@@\main\9
.\resource\process\JDFByteMap.java@@\main\22
.\resource\process\JDFCCITTFaxParams.java@@\main\5
.\resource\process\JDFCIELABMeasuringField.java@@\main\18
.\resource\process\JDFCollatingItem.java@@\main\6
.\resource\process\JDFCollectingParams.java@@\main\9
.\resource\process\JDFColorantAlias.java@@\main\18
.\resource\process\JDFColorantZoneDetails.java@@\main\19
.\resource\process\JDFColorControlStrip.java@@\main\18
.\resource\process\JDFColorsResultsPool.java@@\main\18
.\resource\process\JDFCompany.java@@\main\16
.\resource\process\JDFConstraintValue.java@@\main\12
.\resource\process\JDFContainer.java@@\main\6
.\resource\process\JDFContentData.java@@\main\5
.\resource\process\JDFContentList.java@@\main\4
.\resource\process\JDFContentObject.java@@\main\25
.\resource\process\JDFConventionalPrintingParams.java@@\main\18
.\resource\process\JDFCostCenter.java@@\main\17
.\resource\process\JDFCover.java@@\main\13
.\resource\process\JDFCylinderLayout.java@@\main\4
.\resource\process\JDFCylinderLayoutPreparationParams.java@@\main\4
.\resource\process\JDFCylinderPosition.java@@\main\4
.\resource\process\JDFDBMergeParams.java@@\main\9
.\resource\process\JDFDBRules.java@@\main\9
.\resource\process\JDFDBSelection.java@@\main\9
.\resource\process\JDFDCTParams.java@@\main\5
.\resource\process\JDFDeliveryParams.java@@\main\9
.\resource\process\JDFDensityMeasuringField.java@@\main\18
.\resource\process\JDFDependencies.java@@\main\7
.\resource\process\JDFDeviceNColor.java@@\main\17
.\resource\process\JDFDeviceNSpace.java@@\main\19
.\resource\process\JDFDieLayout.java@@\main\4
.\resource\process\JDFDigitalMedia.java@@\main\6
.\resource\process\JDFDigitalPrintingParams.java@@\main\18
.\resource\process\JDFDisjointing.java@@\main\16
.\resource\process\JDFDisposition.java@@\main\6
.\resource\process\JDFDividingParams.java@@\main\19
.\resource\process\JDFDocumentResultsPool.java@@\main\18
.\resource\process\JDFDrop.java@@\main\9
.\resource\process\JDFDropItem.java@@\main\18
.\resource\process\JDFDynamicField.java@@\main\19
.\resource\process\JDFDynamicInput.java@@\main\18
.\resource\process\JDFElementColorParams.java@@\main\6
.\resource\process\JDFEmployee.java@@\main\20
.\resource\process\JDFExternalImpositionTemplate.java@@\main\4
.\resource\process\JDFExtraValues.java@@\main\4
.\resource\process\JDFFeeder.java@@\main\6
.\resource\process\JDFFeederQualityParams.java@@\main\5
.\resource\process\JDFFeedingParams.java@@\main\5
.\resource\process\JDFFileAlias.java@@\main\18
.\resource\process\JDFFlateParams.java@@\main\5
.\resource\process\JDFFolderProduction.java@@\main\4
.\resource\process\JDFFoldOperation.java@@\main\4
.\resource\process\JDFFontParams.java@@\main\19
.\resource\process\JDFFontPolicy.java@@\main\18
.\resource\process\JDFGeneralID.java@@\main\5
.\resource\process\JDFIdentical.java@@\main\5
.\resource\process\JDFIdentificationField.java@@\main\18
.\resource\process\JDFIDPFinishing.java@@\main\15
.\resource\process\JDFIDPFolding.java@@\main\18
.\resource\process\JDFIDPHoleMaking.java@@\main\18
.\resource\process\JDFIDPLayout.java@@\main\13
.\resource\process\JDFIDPStitching.java@@\main\18
.\resource\process\JDFIDPTrimming.java@@\main\19
.\resource\process\JDFImageCompressionParams.java@@\main\19
.\resource\process\JDFImageReplacementParams.java@@\main\18
.\resource\process\JDFImageSetterParams.java@@\main\9
.\resource\process\JDFImagesResultsPool.java@@\main\19
.\resource\process\JDFInsertSheet.java@@\main\17
.\resource\process\JDFInterpretedPDLData.java@@\main\9
.\resource\process\JDFJBIG2Params.java@@\main\4
.\resource\process\JDFJPEG2000Params.java@@\main\4
.\resource\process\JDFLayoutElementPart.java@@\main\5
.\resource\process\JDFLayoutElementProductionParams.java@@\main\5
.\resource\process\JDFLongFold.java@@\main\19
.\resource\process\JDFLongGlue.java@@\main\19
.\resource\process\JDFLongitudinalRibbonOperationParams.java@@\main\19
.\resource\process\JDFLongPerforate.java@@\main\19
.\resource\process\JDFLongSlit.java@@\main\19
.\resource\process\JDFLot.java@@\main\5
.\resource\process\JDFLZWParams.java@@\main\5
.\resource\process\JDFManualLaborParams.java@@\main\5
.\resource\process\JDFMedia.java@@\main\28
.\resource\process\JDFMediaLayers.java@@\main\4
.\resource\process\JDFMediaSource.java@@\main\17
.\resource\process\JDFMiscConsumable.java@@\main\4
.\resource\process\JDFMISDetails.java@@\main\6
.\resource\process\JDFNotificationFilter.java@@\main\17
.\resource\process\JDFNumberingParam.java@@\main\9
.\resource\process\JDFObjectResolution.java@@\main\16
.\resource\process\JDFOCGControl.java@@\main\4
.\resource\process\JDFOrderingParams.java@@\main\9
.\resource\process\JDFPackingParams.java@@\main\9
.\resource\process\JDFPageAssignedList.java@@\main\4
.\resource\process\JDFPageData.java@@\main\6
.\resource\process\JDFPageElement.java@@\main\4
.\resource\process\JDFPDFXParams.java@@\main\6
.\resource\process\JDFPDLCreationParams.java@@\main\4
.\resource\process\JDFPerforate.java@@\main\20
.\resource\process\JDFPerson.java@@\main\21
.\resource\process\JDFPixelColorant.java@@\main\19
.\resource\process\JDFPlateCopyParams.java@@\main\9
.\resource\process\JDFPosition.java@@\main\6
.\resource\process\JDFPreflightAction.java@@\main\6
.\resource\process\JDFPreflightArgument.java@@\main\7
.\resource\process\JDFPreflightParams.java@@\main\6
.\resource\process\JDFPreflightReport.java@@\main\8
.\resource\process\JDFPreflightReportRulePool.java@@\main\6
.\resource\process\JDFPRError.java@@\main\6
.\resource\process\JDFPreview.java@@\main\19
.\resource\process\JDFPRGroup.java@@\main\7
.\resource\process\JDFPRGroupOccurrence.java@@\main\8
.\resource\process\JDFPrintConditionColor.java@@\main\6
.\resource\process\JDFPrintRollingParams.java@@\main\5
.\resource\process\JDFPRItem.java@@\main\8
.\resource\process\JDFPROccurrence.java@@\main\9
.\resource\process\JDFProductionPath.java@@\main\6
.\resource\process\JDFProductionSubPath.java@@\main\5
.\resource\process\JDFProofingParams.java@@\main\19
.\resource\process\JDFPRRule.java@@\main\6
.\resource\process\JDFPRRuleAttr.java@@\main\6
.\resource\process\JDFQualityControlParams.java@@\main\5
.\resource\process\JDFQualityControlResult.java@@\main\6
.\resource\process\JDFQualityMeasurement.java@@\main\6
.\resource\process\JDFRegisterMark.java@@\main\19
.\resource\process\JDFResourceDefinitionParams.java@@\main\9
.\resource\process\JDFRollStand.java@@\main\6
.\resource\process\JDFScreenSelector.java@@\main\31
.\resource\process\JDFSealing.java@@\main\11
.\resource\process\JDFSeparationControlParams.java@@\main\9
.\resource\process\JDFSeparationSpec.java@@\main\17
.\resource\process\JDFSignatureCell.java@@\main\6
.\resource\process\JDFStation.java@@\main\4
.\resource\process\JDFStringListValue.java@@\main\6
.\resource\process\JDFStripCellParams.java@@\main\5
.\resource\process\JDFStripMark.java@@\main\4
.\resource\process\JDFThinPDFParams.java@@\main\19
.\resource\process\JDFTIFFEmbeddedFile.java@@\main\4
.\resource\process\JDFTIFFFormatParams.java@@\main\6
.\resource\process\JDFTIFFtag.java@@\main\4
.\resource\process\JDFTile.java@@\main\19
.\resource\process\JDFTransferCurvePool.java@@\main\17
.\resource\process\JDFTransferCurveSet.java@@\main\20
.\resource\process\JDFTrapRegion.java@@\main\10
.\resource\process\JDFUsageCounter.java@@\main\6
.\resource\process\postpress\JDFAdhesiveBinding.java@@\main\19
.\resource\process\postpress\JDFBundlingParams.java@@\main\5
.\resource\process\postpress\JDFChannelBinding.java@@\main\14
.\resource\process\postpress\JDFChannelBindingParams.java@@\main\9
.\resource\process\postpress\JDFCoilBinding.java@@\main\14
.\resource\process\postpress\JDFCrease.java@@\main\19
.\resource\process\postpress\JDFCut.java@@\main\19
.\resource\process\postpress\JDFCutMark.java@@\main\18
.\resource\process\postpress\JDFEndSheet.java@@\main\30
.\resource\process\postpress\JDFFold.java@@\main\20
.\resource\process\postpress\JDFFoldingParams.java@@\main\19
.\resource\process\postpress\JDFGlue.java@@\main\20
.\resource\process\postpress\JDFGlueApplication.java@@\main\9
.\resource\process\postpress\JDFGlueLine.java@@\main\17
.\resource\process\postpress\JDFHole.java@@\main\16
.\resource\process\postpress\JDFHoleList.java@@\main\19
.\resource\process\postpress\JDFHoleMakingParams.java@@\main\21
.\resource\process\postpress\JDFPlasticCombBinding.java@@\main\15
.\resource\process\postpress\JDFPlasticCombBindingParams.java@@\main\9
.\resource\process\postpress\JDFRingBinding.java@@\main\15
.\resource\process\postpress\JDFRingBindingParams.java@@\main\9
.\resource\process\postpress\JDFSaddleStitching.java@@\main\12
.\resource\process\postpress\JDFSaddleStitchingParams.java@@\main\9
.\resource\process\postpress\JDFScore.java@@\main\28
.\resource\process\postpress\JDFSideSewing.java@@\main\12
.\resource\process\postpress\JDFSideSewingParams.java@@\main\9
.\resource\process\postpress\JDFSideStitching.java@@\main\12
.\resource\process\postpress\JDFStitchingParams.java@@\main\19
.\resource\process\postpress\JDFStripBindingParams.java@@\main\10
.\resource\process\postpress\JDFThreadSealing.java@@\main\12
.\resource\process\postpress\JDFThreadSewing.java@@\main\14
.\resource\process\postpress\JDFThreadSewingParams.java@@\main\9
.\resource\process\postpress\JDFTrimmingParams.java@@\main\19
.\resource\process\postpress\JDFVeloBinding.java@@\main\11
.\resource\process\postpress\JDFWebInlineFinishingParams.java@@\main\4
.\resource\process\postpress\JDFWireCombBinding.java@@\main\15
.\resource\process\postpress\JDFWireCombBindingParams.java@@\main\9
.\resource\process\prepress\JDFColorCorrectionOp.java@@\main\20
.\resource\process\prepress\JDFColorCorrectionParams.java@@\main\23
.\resource\process\prepress\JDFColorSpaceConversionOp.java@@\main\18
.\resource\process\prepress\JDFColorSpaceConversionParams.java@@\main\19
.\resource\process\prepress\JDFColorSpaceSubstitute.java@@\main\19
.\resource\process\prepress\JDFDigitalDeliveryParams.java@@\main\5
.\resource\process\prepress\JDFFileTypeResultsPool.java@@\main\19
.\resource\process\prepress\JDFFontsResultsPool.java@@\main\19
.\resource\process\prepress\JDFInk.java@@\main\21
.\resource\process\prepress\JDFInkZoneCalculationParams.java@@\main\19
.\resource\process\prepress\JDFInkZoneProfile.java@@\main\19
.\resource\process\prepress\JDFPagesResultsPool.java@@\main\19
.\resource\process\prepress\JDFPDFToPSConversionParams.java@@\main\19
.\resource\process\prepress\JDFPDLResourceAlias.java@@\main\19
.\resource\process\prepress\JDFPreflightConstraint.java@@\main\20
.\resource\process\prepress\JDFPreflightDetail.java@@\main\20
.\resource\process\prepress\JDFPreflightInstance.java@@\main\20
.\resource\process\prepress\JDFPreflightInstanceDetail.java@@\main\20
.\resource\process\prepress\JDFPreflightInventory.java@@\main\9
.\resource\process\prepress\JDFPreflightProfile.java@@\main\9
.\resource\process\prepress\JDFPreviewGenerationParams.java@@\main\9
.\resource\process\prepress\JDFPSToPDFConversionParams.java@@\main\19
.\resource\process\prepress\JDFRenderingParams.java@@\main\19
.\resource\process\prepress\JDFScanParams.java@@\main\20
.\resource\process\prepress\JDFScreeningParams.java@@\main\20
.\resource\process\prepress\JDFTrappingDetails.java@@\main\19
.\resource\process\prepress\JDFTrappingOrder.java@@\main\19
.\resource\process\prepress\JDFTrappingParams.java@@\main\18
.\resource\process\press\JDFIDPrintingParams.java@@\main\19
.\resource\process\press\JDFPrintCondition.java@@\main\7
.\span\JDFDurationSpan.java@@\main\23
.\span\JDFEnumerationSpan.java@@\main\36
.\span\JDFIntegerSpan.java@@\main\24
.\span\JDFNameSpan.java@@\main\32
.\span\JDFNumberSpan.java@@\main\30
.\span\JDFOptionSpan.java@@\main\26
.\span\JDFShapeSpan.java@@\main\22
.\span\JDFSpanArtHandling.java@@\main\15
.\span\JDFSpanBindingType.java@@\main\15
.\span\JDFSpanHoleType.java@@\main\17
.\span\JDFSpanNamedColor.java@@\main\16
.\span\JDFSpanWireCombShape.java@@\main\16
.\span\JDFTimeSpan.java@@\main\28
.\span\JDFXYPairSpan.java@@\main\28
.\util@@\main\27
.\util\ContainerUtil.java@@\main\10
.\util\DumpDir.java@@\main\8
.\util\FileUtil.java@@\main\14
.\util\HotFolder.java@@\main\6
.\util\JDFSpawn.java@@\main\31
.\util\MimeUtil.java@@\main\31
.\util\MyInteger.java@@\main\1
.\util\PrefixInputStream.java@@\main\2
.\util\QueueHotFolder.java@@\main\8
.\util\SkipInputStream.java@@\main\2
.\util\SScanf.java@@\main\2
.\util\StatusCounter.java@@\main\23
.\util\StringUtil.java@@\main\76
.\util\ThreadUtil.java@@\main\1
.\util\UrlUtil.java@@\main\23
.\validate\JDFValidator.java@@\main\6
___________________________________________________________


Label JDFLIBJ_2.1.3BLD522 (02.10.2008)

Jdfnode #BF 2008/12546 : fix JDF-PPF-Merge: Sequenzen werden nicht angezogen 

>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD522) && !lbtype(JDFLIBJ_2.1.3BLD521)}" -print
.\src\org\cip4\jdflib\node\JDFNode.java@@\main\280
___________________________________________________________


Label JDFLIBJ_2.1.3BLD521 (24.09.2008)

#BF fix deadlock : remove synchronize from getChildElementVector_JDFElement

>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD521) && !lbtype(JDFLIBJ_2.1.3BLD520)}" -print
.\core\JDFElement.java@@\main\253
___________________________________________________________


Label JDFLIBJ_2.1.3BLD520 (15.09.2008)


cleanup warnings "Potential null pointer access"
cleanup warnings "assignment to parameter"
Layout get and set by name
getQueueEntryIDMap in queue
undo stream modification in MimeUtil
URL handling for non-ascii characters


Revision: 3564
Author: mucha
Date: 18:37:20, Freitag, 12. September 2008
Message:
cleanup warnings "Potential null pointer access"
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFPartAmount.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/VJDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/UnLinkFinder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISFinGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFFlushQueueInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourceLinkPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFModulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRunList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/JDFValidator.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/cformat/PrintfFormatTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/cformat/ScanfFormatTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/cformat/ScanfReaderTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFElementTest.java

Revision: 3561
Author: prosi
Date: 09:59:04, Freitag, 12. September 2008
Message:
remove orphaned subscriptions in bambi queue
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/IWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFRunListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/UrlUtilTest.java

Revision: 3558
Author: mucha
Date: 18:08:09, Mittwoch, 10. September 2008
Message:
cleanup warnings "assignment to parameter"
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/GeneratorUtil.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/JavaCoreStringUtil.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/SchemaAttribute.java
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/SchemaDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/PrintfFormat.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/ScanfReader.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFParser.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VString.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDocUserData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFIntegerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFIntegerRangeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/IDPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourceLinkPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourcePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFTestRef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFnot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFxor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFDropItemIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFProductionIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFComChannel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSourceResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSurface.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFAssetListCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HashUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDuration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MyArgs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/QueueHotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/VectorMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/JDFValidator.java

Revision: 3557
Author: mucha
Date: 14:10:13, Mittwoch, 10. September 2008
Message:
cleanup warnings
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/PrintfFormat.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/PrintfStream.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/PrintfWriter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/ScanfReader.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFRefElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISFinGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFAncestor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSourceResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/JDFValidator.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFParserTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/ContentCreationTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/DigiPrintTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/GoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAmountPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/ContainerUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java

Revision: 3554
Author: prosi
Date: 08:26:40, Dienstag, 9. September 2008
Message:
Layout get and set by name
getQueueEntryIDMap in queue
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFParser.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFParserTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFLayoutTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFRunListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StatusCounterTest.java

Revision: 3541
Author: prosi
Date: 09:24:30, Mittwoch, 3. September 2008
Message:
undo stream modification in MimeUtil
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java

Revision: 3540
Author: prosi
Date: 08:30:39, Mittwoch, 3. September 2008
Message:
Bambi Message Sender Persistency
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFParser.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFParserTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java

Revision: 3534
Author: prosi
Date: 08:24:30, Dienstag, 2. September 2008
Message:
Bambi upgrades
URL handling for non-ascii characters
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/INodeIdentifiable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MyArgs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFJobPhaseTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/UrlUtilTest.java


>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD520) && !lbtype(JDFLIBJ_2.1.3BLD514)}" -print
.\cformat\PrintfFormat.java@@\main\7
.\cformat\PrintfStream.java@@\main\2
.\cformat\PrintfWriter.java@@\main\2
.\cformat\ScanfReader.java@@\main\7
.\core\JDFAudit.java@@\main\94
.\core\JDFDoc.java@@\main\82
.\core\JDFElement.java@@\main\252
.\core\JDFNodeInfo.java@@\main\60
.\core\JDFParser.java@@\main\51
.\core\JDFPartAmount.java@@\main\31
.\core\JDFRefElement.java@@\main\66
.\core\JDFResourceLink.java@@\main\150
.\core\KElement.java@@\main\268
.\core\VString.java@@\main\43
.\core\XMLDoc.java@@\main\96
.\core\XMLDocUserData.java@@\main\32
.\datatypes\JDFAttributeMap.java@@\main\40
.\datatypes\JDFIntegerList.java@@\main\18
.\datatypes\JDFIntegerRangeList.java@@\main\36
.\datatypes\JDFNumList.java@@\main\34
.\datatypes\VJDFAttributeMap.java@@\main\34
.\elementwalker\BaseWalker.java@@\main\3
.\elementwalker\IWalker.java@@\main\2
.\elementwalker\UnLinkFinder.java@@\main\3
.\goldenticket\BaseGoldenTicket.java@@\main\15
.\goldenticket\IDPGoldenTicket.java@@\main\2
.\goldenticket\MISCPGoldenTicket.java@@\main\14
.\goldenticket\MISFinGoldenTicket.java@@\main\4
.\goldenticket\MISGoldenTicket.java@@\main\13
.\goldenticket\MISPreGoldenTicket.java@@\main\6
.\goldenticket\ProductGoldenTicket.java@@\main\7
.\ifaces\INodeIdentifiable.java@@\main\2
.\jmf\JDFDeviceInfo.java@@\main\30
.\jmf\JDFFlushQueueInfo.java@@\main\6
.\jmf\JDFJMF.java@@\main\73
.\jmf\JDFJobPhase.java@@\main\28
.\jmf\JDFMessage.java@@\main\79
.\jmf\JDFMessageService.java@@\main\20
.\jmf\JDFPipeParams.java@@\main\24
.\jmf\JDFQueue.java@@\main\37
.\jmf\JDFQueueEntry.java@@\main\33
.\jmf\JDFQueueFilter.java@@\main\14
.\jmf\JDFResourceCmdParams.java@@\main\31
.\jmf\JDFResourceInfo.java@@\main\33
.\jmf\JDFResourceQuParams.java@@\main\20
.\jmf\JDFStatusQuParams.java@@\main\20
.\node\JDFAncestor.java@@\main\35
.\node\JDFNode.java@@\main\279
.\pool\JDFAmountPool.java@@\main\33
.\pool\JDFAuditPool.java@@\main\110
.\pool\JDFPool.java@@\main\39
.\pool\JDFResourceLinkPool.java@@\main\86
.\pool\JDFResourcePool.java@@\main\72
.\resource\devicecapability\JDFand.java@@\main\12
.\resource\devicecapability\JDFDevCap.java@@\main\60
.\resource\devicecapability\JDFDevCaps.java@@\main\46
.\resource\devicecapability\JDFDeviceCap.java@@\main\58
.\resource\devicecapability\JDFEvaluation.java@@\main\31
.\resource\devicecapability\JDFModulePool.java@@\main\10
.\resource\devicecapability\JDFnot.java@@\main\14
.\resource\devicecapability\JDFor.java@@\main\13
.\resource\devicecapability\JDFTest.java@@\main\28
.\resource\devicecapability\JDFTestRef.java@@\main\16
.\resource\devicecapability\JDFxor.java@@\main\14
.\resource\intent\JDFDropItemIntent.java@@\main\21
.\resource\intent\JDFProductionIntent.java@@\main\22
.\resource\JDFNotification.java@@\main\21
.\resource\JDFPhaseTime.java@@\main\34
.\resource\JDFProcessRun.java@@\main\36
.\resource\JDFResource.java@@\main\237
.\resource\JDFResourceAudit.java@@\main\36
.\resource\JDFSignature.java@@\main\25
.\resource\process\JDFColor.java@@\main\29
.\resource\process\JDFColorPool.java@@\main\31
.\resource\process\JDFComChannel.java@@\main\18
.\resource\process\JDFLayout.java@@\main\24
.\resource\process\JDFRunList.java@@\main\58
.\resource\process\JDFSourceResource.java@@\main\11
.\resource\process\JDFSurface.java@@\main\39
.\resource\process\prepress\JDFAssetListCreationParams.java@@\main\4
.\util\ContainerUtil.java@@\main\8
.\util\DumpDir.java@@\main\7
.\util\FileUtil.java@@\main\12
.\util\HashUtil.java@@\main\5
.\util\HotFolder.java@@\main\5
.\util\JDFDate.java@@\main\54
.\util\JDFDuration.java@@\main\11
.\util\JDFMerge.java@@\main\31
.\util\JDFSpawn.java@@\main\30
.\util\MimeUtil.java@@\main\29
.\util\MyArgs.java@@\main\31
.\util\QueueHotFolder.java@@\main\6
.\util\StatusCounter.java@@\main\22
.\util\StringUtil.java@@\main\74
.\util\UrlUtil.java@@\main\22
.\util\VectorMap.java@@\main\6
.\validate\JDFValidator.java@@\main\5
___________________________________________________________


Label JDFLIBJ_2.1.3BLD514 (27.08.2008)


performance enhancements in JDFDate, RangeLists
synchronization when importing nodes
fix node status when applying resource command with NodeInfo
more on memory leaks in clone
added Statusdetails to setPartStatus()
added AmountPool.AmountMap for quick loops over large AmountPools
remove "" attributes in merge element



Revision: 3517
Author: prosi
Date: 16:09:22, Mittwoch, 27. August 2008
Message:
performance enhancements in JDFDate, RangeLists
synchronization when importing nodes
fix node status when applying resource command with NodeInfo
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRangeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourceLinkPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDate.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/EmptyNamespace.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/JDFTestCaseBase.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFRefElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFSourceResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFPageListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFContentDataTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/ContainerUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFDateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StatusCounterTest.java

Revision: 3514
Author: prosi
Date: 13:47:51, Montag, 25. August 2008
Message:
Bambi Proxy Enhancements
more on memory leaks in clone
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFException.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/AllJDFLibTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFElementTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFExceptionTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/AmountTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/DigiPrintTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/RIPTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StatusCounterTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java

Revision: 3496
Author: prosi
Date: 09:44:03, Donnerstag, 21. August 2008
Message:
Bambi Proxy updates
Bambi UI improvements
added Statusdetails to setPartStatus()
imprved Editor.sendToDevice to allow JMF
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/VJDFAttributeMap.java

Revision: 3495
Author: prosi
Date: 09:43:29, Donnerstag, 21. August 2008
Message:
Bambi Proxy updates
Bambi UI improvements
added Statusdetails to setPartStatus()
imprved Editor.sendToDevice to allow JMF
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java

Revision: 3494
Author: prosi
Date: 09:42:37, Donnerstag, 21. August 2008
Message:
Bambi Proxy updates
Bambi UI improvements
added Statusdetails to setPartStatus()
imprved Editor.sendToDevice to allow JMF
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFNodeInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFAttributeMapTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/VJDFAttributeMapTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/NColorTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFLayerListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StringUtilTest.java

Revision: 3477
Author: prosi
Date: 13:52:33, Dienstag, 19. August 2008
Message:
added AmountPool.AmountMap for quick loops over large AmountPools
remove "" attributes in merge element
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/VJDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/IAmountPoolContainer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/VectorMap.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/JDFExampleDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAmountPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/VectorMapTest.java



>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD514) && !lbtype(JDFLIBJ_2.1.3BLD513)}" -print
.\core\DocumentJDFImpl.java@@\main\95
.\core\JDFException.java@@\main\12
.\core\KElement.java@@\main\265
.\core\XMLDoc.java@@\main\95
.\datatypes\JDFAttributeMap.java@@\main\39
.\datatypes\JDFRangeList.java@@\main\20
.\datatypes\VJDFAttributeMap.java@@\main\33
.\goldenticket\BaseGoldenTicket.java@@\main\12
.\goldenticket\MISCPGoldenTicket.java@@\main\12
.\jmf\JDFQueueEntry.java@@\main\32
.\jmf\JDFResourceCmdParams.java@@\main\28
.\node\JDFNode.java@@\main\275
.\pool\JDFAmountPool.java@@\main\31
.\pool\JDFAuditPool.java@@\main\107
.\pool\JDFResourceLinkPool.java@@\main\84
.\util\FileUtil.java@@\main\9
.\util\JDFDate.java@@\main\53
.\util\JDFMerge.java@@\main\28
.\util\JDFSpawn.java@@\main\28
.\util\MimeUtil.java@@\main\25
.\util\StatusCounter.java@@\main\19
.\util\StatusUtil.java@@\main\13
.\util\StringUtil.java@@\main\72
___________________________________________________________


Label JDFLIBJ_2.1.3BLD513 (21.08.2008)

Revision: 3477
Author: prosi
Date: 13:52:33, Dienstag, 19. August 2008
Message:
added AmountPool.AmountMap for quick loops over large AmountPools
remove "" attributes in merge element
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/VJDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/IAmountPoolContainer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/VectorMap.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/JDFExampleDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAmountPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/VectorMapTest.java

Revision: 3471
Author: mucha
Date: 14:28:47, Montag, 18. August 2008
Message:
added methods returning a Collection to the generated auto files
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLZWParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutElementProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLocation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongSlit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLongitudinalRibbonOperationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMISDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMacroPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoManualLaborParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMarkObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMatrixEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaLayers.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMediaSource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMerged.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMilestone.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMiscConsumable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModified.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModifyNodeCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModuleStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMoveResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMsgFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNameEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNewJDFQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfoResp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNotificationFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNumberingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOCGControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObjectResolution.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoObservationTarget.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOccupation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoOrderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFPathEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFToPSConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDFXParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPDLResourceAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRGroupOccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPROccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPRRuleAttr.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPSToPDFConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageAssignedList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPageList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPallet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPalletizingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPartStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPayment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerforatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerformance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPixelColorant.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlaceHolderResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlasticCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPlateCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightAnalysis.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightConstraint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInstanceDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightInventory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReport.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreflightReportRulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreview.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPreviewGenerationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPricing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintCondition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintConditionColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPrintRollingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProductionSubPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProofingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPublishingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityControlResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityMeasurement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQuery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryDefList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPosParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntryPriParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRectangleEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegisterRibbon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRegistration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoveLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRemoved.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRenderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceDefinitionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourcePullParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResponse.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoResubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoReturnQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRingBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRollStand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoRunList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSaddleStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScanParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScavengerArea.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScore.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreenSelector.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoScreeningParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSeparationSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShapeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShrinkingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoShutDownCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSideStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSignatureCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSizeIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSoftCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpawned.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpinePreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSpineTapingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStopPersChParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStringListValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripCellParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStripMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoStrippingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubmissionMethods.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSubscription.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSystemTimeSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFEmbeddedFile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFFormatParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTIFFtag.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTabs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTestRef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThinPDFParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSealingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoThreadSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrackResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurve.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurvePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferCurveSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTransferFunctionControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrapRegion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingOrder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrigger.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoTrimmingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUpdateJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoUsageCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoValueLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoVerificationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWakeUpCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWebInlineFinishingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWireCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoWrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoXYPairEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutomacro.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutonot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutootherwise.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutowhen.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoxor.java

Revision: 3470
Author: mucha
Date: 14:26:11, Montag, 18. August 2008
Message:
added methods returning a Collection to the generated auto files
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDependencies.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCapPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevelopingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceNSpace.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDieLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDigitalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisjointing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisplayGroupPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisposition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDividingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDrop.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDropItemIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDurationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDynamicInput.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEdgeGluing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoElementColorParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmboss.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmbossingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEmployeeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEndSheetGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEnumerationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoErrorData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoEvent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExposedMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExternalImpositionTemplate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoExtraValues.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFCNKey.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeatureAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeaturePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeeder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeederQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFeedingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFitPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlateParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushQueueParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushResourceParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFlushedResources.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldOperation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFolderProduction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFontPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFormatConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangCmdFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGangQuFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGatheringParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGeneralID.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGlueLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHardCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHeadBandApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHole.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoHoleMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFinishing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPFolding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPHoleMaking.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPTrimming.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIDPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIcon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIconList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentical.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIdentificationField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompression.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageCompressionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageReplacementParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageSetterParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInk.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneCalculationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInkZoneProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsert.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInsertingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIntegerEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretedPDLData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoIsPresentEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJBIG2Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFController.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJDFService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJPEG2000Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJacketingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoKnownMsgQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLabelingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLaminatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoLayerList.java

Revision: 3469
Author: mucha
Date: 14:22:45, Montag, 18. August 2008
Message:
added methods returning a Collection to the generated auto files
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAncestorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoApprovalSuccess.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArgumentValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDelivery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoArtDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssembly.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssemblySection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAssetListCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAutomatedOverPrintParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeCompParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBarcodeReproParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBasicPreflightTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBendingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBinderySignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBindingQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBlockPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBookCase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBooleanEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBoxToBoxDifference.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBufferParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundleItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBundlingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoBusinessInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoByteMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCCITTFaxParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCIELABMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCaseMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCasingInParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChangedPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoChannelBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoilBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollatingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCollectingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorControlStrip.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorCorrectionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorMeasurementConditions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorSpaceSubstitute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorantZoneDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoColorsUsed.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComChannel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCommand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCompany.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContact.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContactCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContainer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoConventionalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCostCenter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCounterReset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCoverApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCrease.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreasingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreateResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreated.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCreditCard.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCustomerMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutBlock.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCutMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoCylinderPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBMergeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBRules.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSchema.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDBSelection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDCTParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDateTimeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeleted.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDensityMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutocall.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutochoice.java

Revision: 3468
Author: mucha
Date: 18:04:02, Freitag, 15. August 2008
Message:
added methods returning a Collection to the generated auto files
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/JavaCoreStringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAcknowledge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoActionPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdded.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAddress.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdhesiveBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAdvancedParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoAmountPool.java

Revision: 3467
Author: mucha
Date: 16:36:53, Freitag, 15. August 2008
Message:
reduce warnings
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/JavaCoreStringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoFileSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java

Revision: 3466
Author: mucha
Date: 15:57:04, Freitag, 15. August 2008
Message:
reduce warnings
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/CheckJDFTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFAuditTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFNodeInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFParserTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFRefElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFResourceLinkTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFDurationTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFMatrixTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFNumListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFXYPairTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFDevCapTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFDevCapsTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFDeviceCapTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFEvaluationTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFNameStateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFStateBaseTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/devicecapability/JDFStringStateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/AmountTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/DigiPrintTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/GoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISCPGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/MISPreGoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFMessageServiceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFPipeParamsTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueFilterTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JDFQueueSubmissionParamsTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/JMFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/node/JDFNodeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAmountPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFAuditPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFColorPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/pool/JDFResourceLinkPoolTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFDeviceListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFFilespecTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFLayerListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFLayoutTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFNotificationTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFResourceTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/PhaseTimeTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/ProcessRunTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFComChannelTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFContactTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFPersonTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFRunListTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFUsageCounterTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/BiHashMapTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/ByteArrayIOStreamTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/FileUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFDateTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/SScanfTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/StatusCounterTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/UrlUtilTest.java

Revision: 3464
Author: mucha
Date: 15:01:56, Freitag, 15. August 2008
Message:
reduce warnings
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElementName.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFConstants.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFException.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFParser.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFVersions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNameRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFShape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/IWalkerFactory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISFinGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFSizeIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContact.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayoutElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanBase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/XMLstrm.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFNodeInfoTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/JDFParserTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/XMLDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/NColorTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/goldenticket/GoldenTicketTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/jmf/QueueTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/MimeUtilTest.java

Revision: 3463
Author: prosi
Date: 14:29:31, Freitag, 15. August 2008
Message:
jdfattributemap null test
convenience media getters in layout, exposedmedia, component
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFExposedMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayout.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/AllJDFLibTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/JDFAttributeMapTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/examples/JDFExampleDocTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/JDFLayoutTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFComponentTest.java
Added : /trunk/JDFLibJ/test/org/cip4/jdflib/resource/process/JDFExposedMediaTest.java

Revision: 3462
Author: mucha
Date: 13:57:34, Freitag, 15. August 2008
Message:
reduce warnings
----
Modified : /trunk/JDFLibJ/apps/org/cip4/jdflib/generator/JavaCoreStringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDisposition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoDrop.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoModulePhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoMsgFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQualityMeasurement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/auto/JDFAutoSystemTimeSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/VJDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/Duration.java

Revision: 3459
Author: prosi
Date: 11:33:39, Donnerstag, 14. August 2008
Message:
Bambi Memory leaks + add percentCompleted; removed IStatusListener interface
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/VJDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/core/KElementTest.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/datatypes/VJDFAttributeMapTest.java

Revision: 3456
Author: prosi
Date: 11:29:57, Mittwoch, 13. August 2008
Message:
added utility interfaces
----
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/IAmountPoolContainer.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/INodeIdentifiable.java

Revision: 3453
Author: prosi
Date: 11:09:17, Mittwoch, 13. August 2008
Message:

----
Modified : /trunk/JDFLibJ/build.xml
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/PrintfFormat.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/PrintfStream.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/PrintfWriter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/ScanfFormat.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/ScanfMatchException.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/cformat/ScanfReader.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AtrInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AtrInfoTable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/AttributeName.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/DocumentJDFImpl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElemInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElemInfoTable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElementInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/ElementName.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFConstants.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFCustomerInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFCustomerMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFDocumentBuilder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFException.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFNodeInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFParser.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFPartAmount.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFPartStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFRefElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFResourceLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFSeparationList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/JDFVersions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/KElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/VString.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLDocUserData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/core/XMLErrorHandler.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFAttributeMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFBaseDataTypes.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFCMYKColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFDateTimeRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFDateTimeRangeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFDurationRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFDurationRangeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFIntegerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFIntegerRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFIntegerRangeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFLabColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFMatrix.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNameRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNameRangeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumberList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumberRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFNumberRangeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRGBColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRangeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRectangle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRectangleRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFRectangleRangeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFShape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFShapeRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFShapeRangeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFTransferFunction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFXYPair.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFXYPairRange.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFXYPairRangeList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/JDFXYRelation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/datatypes/VJDFAttributeMap.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseElementWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/BaseWalkerFactory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/ElementWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/IWalker.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/IWalkerFactory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/elementwalker/UnLinkFinder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/BaseGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/IDPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/JMFGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISCPGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISFinGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/MISPreGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/goldenticket/ProductGoldenTicket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/ICapabilityElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/IDeviceCapable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/IJMFSubscribable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/IMatches.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/ifaces/IPlacedObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFAcknowledge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFAdded.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFChangedPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFCommand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFCreateLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFCreateResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFDeviceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFEmployeeDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFFlushQueueInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFFlushQueueParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFFlushResourceParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFFlushedResources.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFGangCmdFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFGangInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFGangQuFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFIDInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJDFController.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJDFService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJobPhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFKnownMsgQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessage.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMessageService.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFModifyNodeCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMoveResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFMsgFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNewComment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNewJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNewJDFQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNodeInfoCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNodeInfoQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNodeInfoResp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFNotificationDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFOccupation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFPipeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQuery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntryDef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntryPosParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntryPriParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueSubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFRegistration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFRemoveLink.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFRequestQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourcePullParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResourceQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResponse.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFResubmissionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFReturnQueueEntryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFShutDownCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFSignal.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFStatusQuParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFStopPersChParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFSubmissionMethods.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFSubscription.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFTrackFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFTrackResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFTrigger.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFUpdateJDFCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFWakeUpCmdParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFAncestor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/JDFSpawned.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/node/VJDFNode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAmountPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAncestorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFAuditPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFPreflightConstraintsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFPreflightResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourceLinkPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFResourcePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/pool/JDFStatusPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFDurationSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFEnumerationSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFIntegerSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFNameSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFNumberSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFOptionSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFShapeSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanArtHandling.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanBase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanBindingLength.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanBindingSide.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanBindingType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanCoatings.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanCoilMaterial.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanColorType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanCutType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanDeliveryCharge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanDirection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanEdgeShape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanFinishedGrainDirection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanFrequencySelection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanGlueProcedure.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanGlueType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanGrainDirection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanHoleType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanImageStrategy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanJacket.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanLevel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanMediaType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanMediaUnit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanMethod.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanNamedColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanOpacity.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanPrintPreference.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanPrintProcess.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanProofType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanScoring.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanScreeningType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanShape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanShapeType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanSizePolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanStripMaterial.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanSurface.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanSurplusHandling.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanTemperature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanTightBacking.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanTransfer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanWireCombMaterial.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFSpanWireCombShape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFStringSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFTimeSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/span/JDFXYPairSpan.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/BiHashMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ByteArrayIOStream.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/ContainerUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/DumpDir.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/Duration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/EnumUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/FileUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HashUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/HotFolderListener.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFDuration.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFSpawn.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MimeUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MultiModuleStatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/MyArgs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/PrefixInputStream.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/QueueHotFolder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/QueueHotFolderListener.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/SScanf.java
Added : /trunk/JDFLibJ/src/org/cip4/jdflib/util/SkipInputStream.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StatusUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/StringUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/UrlUtil.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/VectorMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/XMLstrm.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/ICheckValidator.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/ICheckValidatorFactory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/validate/JDFValidator.java

Revision: 3452
Author: prosi
Date: 11:07:25, Mittwoch, 13. August 2008
Message:
formatting + mem leaks in clone
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFAdhesiveBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBarcode.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBindItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBindList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBlockPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBoxPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBufferParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBundle.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFBundleItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCaseMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCasingInParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFChangedAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCoilBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFColorMeasurementConditions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFColorsUsed.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFContactCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCounterReset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCoverApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCoverColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCreasingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCreated.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCreditCard.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDBSchema.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDeleted.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDevelopingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDevice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDeviceList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDeviceMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFDimensions.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEdgeGluing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEmboss.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEmbossingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEmbossingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEndSheetGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFErrorData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFEvent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFFCNKey.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFFitPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFFormatConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFGatheringParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFGluingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFHardCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFHeadBandApplicationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFHoleLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFIDPCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFIDPImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFIDPJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFIcon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFIconList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFImageCompression.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFImageShift.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFInsert.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFInsertList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFInsertingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFJacketingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFJobField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFJobSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLabelingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLaminatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLayerDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLayerList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFLocation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFMarkObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFMerged.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFMilestone.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFModified.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFModulePhase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFModuleStatus.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFNotification.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFNumberItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFNumberingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFObservationTarget.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPDFInterpretingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPageCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPageList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPages.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPallet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPalletizingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPayment.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPerforatingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPerformance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPhaseTime.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPlaceHolderResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFPreflightAnalysis.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFProcessRun.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFProofItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFQueueEntryDefList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFRegisterRibbon.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFRemoved.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResourceAudit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFResourceParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFScavengerArea.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFShapeCuttingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFShapeElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFShrinkingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSoftCoverBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSpinePreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSpineTapingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFStackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFStrap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFStrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFStripBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFStrippingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFSystemTimeSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFTabs.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFTape.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFThreadSealingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFTool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFTransferFunctionControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFVerificationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFWeight.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/JDFWrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFAbstractState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFActionPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFBasicPreflightTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFBooleanEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFBooleanState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDateTimeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDateTimeState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCapPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDevCaps.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDeviceCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDisplayGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDisplayGroupPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDurationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFDurationState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEnumerationEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEnumerationState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFFeatureAttribute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFFeaturePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFIntegerEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFIntegerState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFIsPresentEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFMacroPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFMatrixEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFMatrixState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFModule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFModuleCap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFModulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFNameEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFNameState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFNodeTerm.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFNumberEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFNumberState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFPDFPathEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFPDFPathState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFRectangleEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFRectangleState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFShapeEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFShapeState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFStringEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFStringState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFTerm.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFTest.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFTestPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFTestRef.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFValueLoc.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFXYPairEvaluation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFXYPairState.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFcall.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFchoice.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFmacro.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFnot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFotherwise.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFset.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFwhen.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/devicecapability/JDFxor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFArtDelivery.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFArtDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFArtDeliveryType.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFBindingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFBookCase.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFColorIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFDeliveryIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFDropIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFDropItemIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFEmbossingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFFoldingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFHoleMakingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFInsertingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFIntentResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFLaminatingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFLayoutIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFMediaIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFNumberingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFPackingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFPricing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFProductionIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFProofingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFPublishingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFScreeningIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFShapeCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFShapeCuttingIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/intent/JDFSizeIntent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAddress.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAdvancedParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFApprovalDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFApprovalParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFApprovalPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFApprovalSuccess.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFArgumentValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAssembly.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAssemblySection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFAutomatedOverPrintParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBarcodeCompParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBarcodeDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBarcodeProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBarcodeReproParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBendingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBinderySignature.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBindingQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBoxApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBoxArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBoxFoldAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBoxFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBoxToBoxDifference.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFBusinessInfo.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFByteMap.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCCITTFaxParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCIELABMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCollatingItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCollectingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorControlStrip.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorantAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorantControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorantZoneDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFColorsResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFComChannel.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCompany.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFComponent.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFConstraintValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContact.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContainer.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContentData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContentList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFContentObject.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFConventionalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCostCenter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCover.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCutBlock.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCylinderLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCylinderLayoutPreparationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFCylinderPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDBMergeParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDBRules.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDBSelection.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDCTParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDensityMeasuringField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDependencies.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDeviceNColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDeviceNSpace.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDieLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDigitalMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDigitalPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDisjointing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDisposition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDividingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDocumentResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDrop.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDropItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDynamicField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFDynamicInput.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFElementColorParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFEmployee.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFExposedMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFExternalImpositionTemplate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFExtraValues.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFeeder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFeederQualityParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFeedingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFileAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFileSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFlateParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFoldOperation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFolderProduction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFontParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFFontPolicy.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFGeneralID.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPFinishing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPFolding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPHoleMaking.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIDPTrimming.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIdentical.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFIdentificationField.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFImageCompressionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFImageReplacementParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFImageSetterParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFImagesResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFInsertSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFInterpretedPDLData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFJBIG2Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFJPEG2000Params.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLZWParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayout.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayoutElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayoutElementPart.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLayoutElementProductionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLongFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLongGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLongPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLongSlit.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLongitudinalRibbonOperationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFLot.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMISDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFManualLaborParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMedia.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMediaLayers.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMediaSource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFMiscConsumable.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFNotificationFilter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFNumberingParam.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFOCGControl.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFObjectResolution.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFOrderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPDFXParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPDLCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRError.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRGroup.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRGroupOccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRItem.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPROccurrence.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRRule.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPRRuleAttr.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPackingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPageAssignedList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPageData.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPageElement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPerforate.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPerson.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPixelColorant.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPlateCopyParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPosition.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreflightAction.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreflightArgument.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreflightParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreflightReport.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreflightReportRulePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPreview.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPrintConditionColor.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFPrintRollingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFProductionPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFProductionSubPath.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFProofingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFQualityControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFQualityControlResult.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFQualityMeasurement.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRegisterMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFResourceDefinitionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRollStand.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFRunList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFScreenSelector.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSealing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSeparationControlParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSeparationSpec.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSignatureCell.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSourceResource.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFStation.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFStringListValue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFStripCellParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFStripMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFSurface.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTIFFEmbeddedFile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTIFFFormatParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTIFFtag.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFThinPDFParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTransferCurve.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTransferCurvePool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTransferCurveSet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFTrapRegion.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/JDFUsageCounter.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFAdhesiveBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFBundlingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFChannelBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFChannelBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFCoilBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFCrease.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFCut.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFCutMark.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFEndSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFFold.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFFoldingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFGlue.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFGlueApplication.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFGlueLine.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFHole.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFHoleList.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFHoleMakingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFPlasticCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFPlasticCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFRingBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFRingBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSaddleStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSaddleStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFScore.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSheet.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSideSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSideSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFSideStitching.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFStitchingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFStripBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFThreadSealing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFThreadSewing.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFThreadSewingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFTrimmingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFVeloBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFWebInlineFinishingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFWireCombBinding.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/postpress/JDFWireCombBindingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFAssetListCreationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFColorCorrectionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFColorCorrectionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFColorSpaceConversionOp.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFColorSpaceConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFColorSpaceSubstitute.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFDigitalDeliveryParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFFileTypeResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFFontsResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFInk.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFInkZoneCalculationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFInkZoneProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPDFToPSConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPDLResourceAlias.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPSToPDFConversionParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPagesResultsPool.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightConstraint.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightInstance.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightInstanceDetail.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightInventory.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreflightProfile.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFPreviewGenerationParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFRenderingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFScanParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFScreeningParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFTrappingDetails.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFTrappingOrder.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/prepress/JDFTrappingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/press/JDFIDPrintingParams.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/resource/process/press/JDFPrintCondition.java

Revision: 3442
Author: prosi
Date: 17:42:54, Donnerstag, 31. Juli 2008
Message:
make comparator generic
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFQueueEntry.java

Revision: 3441
Author: prosi
Date: 14:54:11, Donnerstag, 31. Juli 2008
Message:
Amount handling in merge of unpartitioned resources
----
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/jmf/JDFJMF.java
Modified : /trunk/JDFLibJ/src/org/cip4/jdflib/util/JDFMerge.java
Modified : /trunk/JDFLibJ/test/org/cip4/jdflib/util/JDFSpawnTest.java



>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD513) && !lbtype(JDFLIBJ_2.1.3BLD512)}" -print
.\auto\JDFAutoAction.java@@\main\28
.\auto\JDFAutoActionPool.java@@\main\22
.\auto\JDFAutoAdhesiveBindingParams.java@@\main\55
.\auto\JDFAutoAmountPool.java@@\main\43
.\auto\JDFAutoAncestorPool.java@@\main\43
.\auto\JDFAutoApprovalParams.java@@\main\48
.\auto\JDFAutoApprovalSuccess.java@@\main\60
.\auto\JDFAutoArgumentValue.java@@\main\24
.\auto\JDFAutoArtDelivery.java@@\main\76
.\auto\JDFAutoArtDeliveryIntent.java@@\main\72
.\auto\JDFAutoAssembly.java@@\main\31
.\auto\JDFAutoAssemblySection.java@@\main\31
.\auto\JDFAutoAssetListCreationParams.java@@\main\29
.\auto\JDFAutoBarcodeReproParams.java@@\main\18
.\auto\JDFAutoBinderySignature.java@@\main\33
.\auto\JDFAutoBindList.java@@\main\42
.\auto\JDFAutoBlockPreparationParams.java@@\main\57
.\auto\JDFAutoBooleanEvaluation.java@@\main\24
.\auto\JDFAutoBoxApplication.java@@\main\14
.\auto\JDFAutoBoxFoldAction.java@@\main\18
.\auto\JDFAutoBoxFoldingParams.java@@\main\20
.\auto\JDFAutoBundle.java@@\main\57
.\auto\JDFAutoByteMap.java@@\main\73
.\auto\JDFAutoCasingInParams.java@@\main\50
.\auto\JDFAutochoice.java@@\main\21
.\auto\JDFAutoColor.java@@\main\76
.\auto\JDFAutoColorantAlias.java@@\main\62
.\auto\JDFAutoColorantControl.java@@\main\71
.\auto\JDFAutoColorControlStrip.java@@\main\67
.\auto\JDFAutoColorCorrectionOp.java@@\main\49
.\auto\JDFAutoColorCorrectionParams.java@@\main\66
.\auto\JDFAutoColorPool.java@@\main\64
.\auto\JDFAutoColorSpaceConversionOp.java@@\main\61
.\auto\JDFAutoColorSpaceConversionParams.java@@\main\71
.\auto\JDFAutoColorSpaceSubstitute.java@@\main\58
.\auto\JDFAutoColorsUsed.java@@\main\20
.\auto\JDFAutoComment.java@@\main\63
.\auto\JDFAutoCompany.java@@\main\63
.\auto\JDFAutoComponent.java@@\main\80
.\auto\JDFAutoContact.java@@\main\65
.\auto\JDFAutoContentData.java@@\main\14
.\auto\JDFAutoContentList.java@@\main\13
.\auto\JDFAutoConventionalPrintingParams.java@@\main\76
.\auto\JDFAutoCoverApplicationParams.java@@\main\50
.\auto\JDFAutoCreasingParams.java@@\main\45
.\auto\JDFAutoCreateResource.java@@\main\13
.\auto\JDFAutoCustomerInfo.java@@\main\54
.\auto\JDFAutoCustomerMessage.java@@\main\24
.\auto\JDFAutoCutMark.java@@\main\69
.\auto\JDFAutoCuttingParams.java@@\main\46
.\auto\JDFAutoCylinderLayout.java@@\main\15
.\auto\JDFAutoDateTimeEvaluation.java@@\main\25
.\auto\JDFAutoDeliveryIntent.java@@\main\78
.\auto\JDFAutoDeliveryParams.java@@\main\65
.\auto\JDFAutoDependencies.java@@\main\21
.\auto\JDFAutoDevCap.java@@\main\50
.\auto\JDFAutoDevCapPool.java@@\main\12
.\auto\JDFAutoDevCaps.java@@\main\55
.\auto\JDFAutoDevice.java@@\main\70
.\auto\JDFAutoDeviceCap.java@@\main\52
.\auto\JDFAutoDeviceFilter.java@@\main\53
.\auto\JDFAutoDeviceInfo.java@@\main\65
.\auto\JDFAutoDeviceList.java@@\main\44
.\auto\JDFAutoDeviceNSpace.java@@\main\64
.\auto\JDFAutoDieLayout.java@@\main\14
.\auto\JDFAutoDigitalDeliveryParams.java@@\main\31
.\auto\JDFAutoDigitalMedia.java@@\main\26
.\auto\JDFAutoDisjointing.java@@\main\73
.\auto\JDFAutoDisplayGroup.java@@\main\21
.\auto\JDFAutoDisplayGroupPool.java@@\main\21
.\auto\JDFAutoDisposition.java@@\main\34
.\auto\JDFAutoDrop.java@@\main\75
.\auto\JDFAutoDropIntent.java@@\main\69
.\auto\JDFAutoDropItem.java@@\main\63
.\auto\JDFAutoDurationEvaluation.java@@\main\25
.\auto\JDFAutoElementColorParams.java@@\main\34
.\auto\JDFAutoEmbossingIntent.java@@\main\43
.\auto\JDFAutoEmbossingParams.java@@\main\46
.\auto\JDFAutoEndSheetGluingParams.java@@\main\59
.\auto\JDFAutoEnumerationEvaluation.java@@\main\24
.\auto\JDFAutoError.java@@\main\45
.\auto\JDFAutoExposedMedia.java@@\main\74
.\auto\JDFAutoExternalImpositionTemplate.java@@\main\12
.\auto\JDFAutoFeedingParams.java@@\main\23
.\auto\JDFAutoFileSpec.java@@\main\81
.\auto\JDFAutoFoldingIntent.java@@\main\63
.\auto\JDFAutoFoldingParams.java@@\main\71
.\auto\JDFAutoGluingParams.java@@\main\48
.\auto\JDFAutoHardCoverBinding.java@@\main\45
.\auto\JDFAutoHeadBandApplicationParams.java@@\main\57
.\auto\JDFAutoHoleList.java@@\main\59
.\auto\JDFAutoHoleMakingParams.java@@\main\74
.\auto\JDFAutoIconList.java@@\main\40
.\auto\JDFAutoIDPCover.java@@\main\58
.\auto\JDFAutoIDPFinishing.java@@\main\51
.\auto\JDFAutoIDPFolding.java@@\main\55
.\auto\JDFAutoIDPHoleMaking.java@@\main\55
.\auto\JDFAutoIDPJobSheet.java@@\main\56
.\auto\JDFAutoIDPLayout.java@@\main\57
.\auto\JDFAutoIDPrintingParams.java@@\main\74
.\auto\JDFAutoIDPStitching.java@@\main\67
.\auto\JDFAutoIDPTrimming.java@@\main\58
.\auto\JDFAutoImageCompression.java@@\main\61
.\auto\JDFAutoImageCompressionParams.java@@\main\57
.\auto\JDFAutoImageReplacementParams.java@@\main\73
.\auto\JDFAutoInk.java@@\main\63
.\auto\JDFAutoInsert.java@@\main\65
.\auto\JDFAutoInsertingParams.java@@\main\58
.\auto\JDFAutoInsertList.java@@\main\54
.\auto\JDFAutoIntegerEvaluation.java@@\main\25
.\auto\JDFAutoInterpretingParams.java@@\main\59
.\auto\JDFAutoIsPresentEvaluation.java@@\main\21
.\auto\JDFAutoJMF.java@@\main\64
.\auto\JDFAutoJobPhase.java@@\main\67
.\auto\JDFAutoLayerList.java@@\main\44
.\auto\JDFAutoLayout.java@@\main\65
.\auto\JDFAutoLayoutElement.java@@\main\76
.\auto\JDFAutoLayoutElementPart.java@@\main\14
.\auto\JDFAutoLayoutElementProductionParams.java@@\main\13
.\auto\JDFAutoLayoutPreparationParams.java@@\main\64
.\auto\JDFAutoLocation.java@@\main\62
.\auto\JDFAutoLongitudinalRibbonOperationParams.java@@\main\59
.\auto\JDFAutomacro.java@@\main\22
.\auto\JDFAutoMacroPool.java@@\main\21
.\auto\JDFAutoMarkObject.java@@\main\76
.\auto\JDFAutoMatrixEvaluation.java@@\main\25
.\auto\JDFAutoMedia.java@@\main\81
.\auto\JDFAutoMediaLayers.java@@\main\12
.\auto\JDFAutoMerged.java@@\main\51
.\auto\JDFAutoMessage.java@@\main\51
.\auto\JDFAutoMessageService.java@@\main\58
.\auto\JDFAutoMiscConsumable.java@@\main\14
.\auto\JDFAutoModifyNodeCmdParams.java@@\main\19
.\auto\JDFAutoModule.java@@\main\15
.\auto\JDFAutoModulePhase.java@@\main\80
.\auto\JDFAutoModulePool.java@@\main\12
.\auto\JDFAutoModuleStatus.java@@\main\57
.\auto\JDFAutoMsgFilter.java@@\main\57
.\auto\JDFAutoNameEvaluation.java@@\main\24
.\auto\JDFAutoNodeInfo.java@@\main\78
.\auto\JDFAutoNodeInfoCmdParams.java@@\main\32
.\auto\JDFAutoNodeInfoResp.java@@\main\27
.\auto\JDFAutoNotification.java@@\main\60
.\auto\JDFAutoNotificationFilter.java@@\main\48
.\auto\JDFAutoNumberEvaluation.java@@\main\26
.\auto\JDFAutoNumberingIntent.java@@\main\43
.\auto\JDFAutoNumberingParams.java@@\main\57
.\auto\JDFAutoOccupation.java@@\main\50
.\auto\JDFAutoOrderingParams.java@@\main\50
.\auto\JDFAutootherwise.java@@\main\21
.\auto\JDFAutoPageData.java@@\main\30
.\auto\JDFAutoPageList.java@@\main\30
.\auto\JDFAutoPallet.java@@\main\46
.\auto\JDFAutoPDFInterpretingParams.java@@\main\56
.\auto\JDFAutoPDFPathEvaluation.java@@\main\26
.\auto\JDFAutoPerforatingParams.java@@\main\44
.\auto\JDFAutoPerson.java@@\main\51
.\auto\JDFAutoPhaseTime.java@@\main\61
.\auto\JDFAutoPipeParams.java@@\main\52
.\auto\JDFAutoPreflightArgument.java@@\main\20
.\auto\JDFAutoPreflightConstraint.java@@\main\63
.\auto\JDFAutoPreflightDetail.java@@\main\64
.\auto\JDFAutoPreflightInstance.java@@\main\64
.\auto\JDFAutoPreflightInstanceDetail.java@@\main\60
.\auto\JDFAutoPreflightParams.java@@\main\25
.\auto\JDFAutoPreflightReport.java@@\main\33
.\auto\JDFAutoPreflightReportRulePool.java@@\main\26
.\auto\JDFAutoPRGroup.java@@\main\26
.\auto\JDFAutoPRGroupOccurrence.java@@\main\11
.\auto\JDFAutoPricing.java@@\main\65
.\auto\JDFAutoPrintCondition.java@@\main\28
.\auto\JDFAutoPrintConditionColor.java@@\main\30
.\auto\JDFAutoPRItem.java@@\main\26
.\auto\JDFAutoPROccurrence.java@@\main\12
.\auto\JDFAutoProcessRun.java@@\main\57
.\auto\JDFAutoProductionIntent.java@@\main\56
.\auto\JDFAutoProductionPath.java@@\main\16
.\auto\JDFAutoProofingIntent.java@@\main\53
.\auto\JDFAutoProofItem.java@@\main\52
.\auto\JDFAutoPRRule.java@@\main\24
.\auto\JDFAutoQualityControlResult.java@@\main\26
.\auto\JDFAutoQualityMeasurement.java@@\main\30
.\auto\JDFAutoQueue.java@@\main\59
.\auto\JDFAutoQueueEntry.java@@\main\63
.\auto\JDFAutoQueueEntryDefList.java@@\main\35
.\auto\JDFAutoQueueFilter.java@@\main\36
.\auto\JDFAutoRectangleEvaluation.java@@\main\31
.\auto\JDFAutoRegisterMark.java@@\main\71
.\auto\JDFAutoRegisterRibbon.java@@\main\50
.\auto\JDFAutoRenderingParams.java@@\main\72
.\auto\JDFAutoRequestQueueEntryParams.java@@\main\31
.\auto\JDFAutoResourceAudit.java@@\main\51
.\auto\JDFAutoResourceCmdParams.java@@\main\55
.\auto\JDFAutoResourceDefinitionParams.java@@\main\58
.\auto\JDFAutoResourceInfo.java@@\main\63
.\auto\JDFAutoResourcePullParams.java@@\main\35
.\auto\JDFAutoResourceQuParams.java@@\main\58
.\auto\JDFAutoRollStand.java@@\main\25
.\auto\JDFAutoRunList.java@@\main\79
.\auto\JDFAutoScavengerArea.java@@\main\51
.\auto\JDFAutoScreeningParams.java@@\main\64
.\auto\JDFAutoSeparationControlParams.java@@\main\45
.\auto\JDFAutoSeparationList.java@@\main\23
.\auto\JDFAutoShapeCuttingIntent.java@@\main\53
.\auto\JDFAutoShapeCuttingParams.java@@\main\51
.\auto\JDFAutoShapeEvaluation.java@@\main\26
.\auto\JDFAutoSpawned.java@@\main\54
.\auto\JDFAutoSpineTapingParams.java@@\main\56
.\auto\JDFAutoStatusPool.java@@\main\48
.\auto\JDFAutoStatusQuParams.java@@\main\51
.\auto\JDFAutoStopPersChParams.java@@\main\42
.\auto\JDFAutoStrap.java@@\main\52
.\auto\JDFAutoStringEvaluation.java@@\main\26
.\auto\JDFAutoStringListValue.java@@\main\24
.\auto\JDFAutoStrippingParams.java@@\main\34
.\auto\JDFAutoSubscription.java@@\main\46
.\auto\JDFAutoSystemTimeSet.java@@\main\45
.\auto\JDFAutoTestPool.java@@\main\24
.\auto\JDFAutoThreadSewingParams.java@@\main\60
.\auto\JDFAutoTIFFFormatParams.java@@\main\34
.\auto\JDFAutoTool.java@@\main\49
.\auto\JDFAutoTrackFilter.java@@\main\47
.\auto\JDFAutoTrackResult.java@@\main\44
.\auto\JDFAutoTransferCurvePool.java@@\main\59
.\auto\JDFAutoTransferCurveSet.java@@\main\64
.\auto\JDFAutoTrappingDetails.java@@\main\66
.\auto\JDFAutoTrappingOrder.java@@\main\54
.\auto\JDFAutoTrappingParams.java@@\main\74
.\auto\JDFAutoTrigger.java@@\main\43
.\auto\JDFAutoUpdateJDFCmdParams.java@@\main\12
.\auto\JDFAutoValue.java@@\main\31
.\auto\JDFAutoValueLoc.java@@\main\23
.\auto\JDFAutoWebInlineFinishingParams.java@@\main\13
.\auto\JDFAutowhen.java@@\main\21
.\auto\JDFAutoXYPairEvaluation.java@@\main\30
.\cformat\PrintfFormat.java@@\main\5
.\cformat\ScanfFormat.java@@\main\3
.\cformat\ScanfReader.java@@\main\5
.\core\AtrInfo.java@@\main\7
.\core\AtrInfoTable.java@@\main\11
.\core\AttributeInfo.java@@\main\36
.\core\DocumentJDFImpl.java@@\main\94
.\core\ElementInfo.java@@\main\11
.\core\ElementName.java@@\main\41
.\core\ElemInfo.java@@\main\3
.\core\ElemInfoTable.java@@\main\4
.\core\JDFAudit.java@@\main\93
.\core\JDFComment.java@@\main\30
.\core\JDFConstants.java@@\main\74
.\core\JDFCustomerInfo.java@@\main\43
.\core\JDFCustomerMessage.java@@\main\8
.\core\JDFDoc.java@@\main\81
.\core\JDFElement.java@@\main\249
.\core\JDFException.java@@\main\11
.\core\JDFNodeInfo.java@@\main\59
.\core\JDFParser.java@@\main\49
.\core\JDFPartAmount.java@@\main\30
.\core\JDFPartStatus.java@@\main\25
.\core\JDFRefElement.java@@\main\65
.\core\JDFResourceLink.java@@\main\147
.\core\JDFSeparationList.java@@\main\25
.\core\JDFVersions.java@@\main\11
.\core\KElement.java@@\main\264
.\core\VElement.java@@\main\35
.\core\XMLDoc.java@@\main\94
.\core\XMLDocUserData.java@@\main\31
.\core\XMLErrorHandler.java@@\main\5
.\datatypes\JDFAttributeMap.java@@\main\38
.\datatypes\JDFDateTimeRange.java@@\main\15
.\datatypes\JDFDateTimeRangeList.java@@\main\13
.\datatypes\JDFDurationRange.java@@\main\16
.\datatypes\JDFDurationRangeList.java@@\main\13
.\datatypes\JDFIntegerRangeList.java@@\main\35
.\datatypes\JDFMatrix.java@@\main\20
.\datatypes\JDFNameRange.java@@\main\17
.\datatypes\JDFNumberRange.java@@\main\26
.\datatypes\JDFNumberRangeList.java@@\main\23
.\datatypes\JDFNumList.java@@\main\33
.\datatypes\JDFPath.java@@\main\20
.\datatypes\JDFRectangle.java@@\main\22
.\datatypes\JDFRectangleRange.java@@\main\13
.\datatypes\JDFRectangleRangeList.java@@\main\13
.\datatypes\JDFShape.java@@\main\15
.\datatypes\JDFShapeRange.java@@\main\13
.\datatypes\JDFShapeRangeList.java@@\main\13
.\datatypes\JDFXYPair.java@@\main\20
.\datatypes\JDFXYPairRange.java@@\main\19
.\datatypes\JDFXYPairRangeList.java@@\main\17
.\datatypes\VJDFAttributeMap.java@@\main\32
.\elementwalker@@\main\2
.\elementwalker\BaseElementWalker.java@@\main\1
.\elementwalker\BaseWalker.java@@\main\2
.\elementwalker\IWalkerFactory.java@@\main\2
.\elementwalker\UnLinkFinder.java@@\main\2
.\goldenticket\BaseGoldenTicket.java@@\main\11
.\goldenticket\MISCPGoldenTicket.java@@\main\11
.\goldenticket\MISFinGoldenTicket.java@@\main\2
.\goldenticket\MISPreGoldenTicket.java@@\main\4
.\goldenticket\ProductGoldenTicket.java@@\main\6
.\ifaces@@\main\6
.\ifaces\IAmountPoolContainer.java@@\main\1
.\ifaces\INodeIdentifiable.java@@\main\1
.\jmf\JDFAcknowledge.java@@\main\22
.\jmf\JDFAdded.java@@\main\4
.\jmf\JDFChangedPath.java@@\main\8
.\jmf\JDFCommand.java@@\main\23
.\jmf\JDFCreateLink.java@@\main\5
.\jmf\JDFCreateResource.java@@\main\4
.\jmf\JDFDeviceFilter.java@@\main\11
.\jmf\JDFDeviceInfo.java@@\main\27
.\jmf\JDFEmployeeDef.java@@\main\11
.\jmf\JDFFlushedResources.java@@\main\9
.\jmf\JDFFlushQueueInfo.java@@\main\5
.\jmf\JDFFlushQueueParams.java@@\main\10
.\jmf\JDFFlushResourceParams.java@@\main\10
.\jmf\JDFGangCmdFilter.java@@\main\4
.\jmf\JDFGangInfo.java@@\main\4
.\jmf\JDFGangQuFilter.java@@\main\4
.\jmf\JDFIDInfo.java@@\main\10
.\jmf\JDFJDFController.java@@\main\11
.\jmf\JDFJDFService.java@@\main\11
.\jmf\JDFJMF.java@@\main\71
.\jmf\JDFJobPhase.java@@\main\26
.\jmf\JDFKnownMsgQuParams.java@@\main\11
.\jmf\JDFMessage.java@@\main\78
.\jmf\JDFMessageService.java@@\main\19
.\jmf\JDFModifyNodeCmdParams.java@@\main\4
.\jmf\JDFMoveResource.java@@\main\4
.\jmf\JDFMsgFilter.java@@\main\17
.\jmf\JDFNewComment.java@@\main\4
.\jmf\JDFNewJDFCmdParams.java@@\main\9
.\jmf\JDFNewJDFQuParams.java@@\main\10
.\jmf\JDFNodeInfoCmdParams.java@@\main\15
.\jmf\JDFNodeInfoQuParams.java@@\main\14
.\jmf\JDFNodeInfoResp.java@@\main\15
.\jmf\JDFNotificationDef.java@@\main\13
.\jmf\JDFOccupation.java@@\main\17
.\jmf\JDFPipeParams.java@@\main\23
.\jmf\JDFQuery.java@@\main\22
.\jmf\JDFQueue.java@@\main\35
.\jmf\JDFQueueEntry.java@@\main\31
.\jmf\JDFQueueEntryDef.java@@\main\14
.\jmf\JDFQueueEntryPosParams.java@@\main\11
.\jmf\JDFQueueEntryPriParams.java@@\main\14
.\jmf\JDFQueueFilter.java@@\main\13
.\jmf\JDFQueueSubmissionParams.java@@\main\19
.\jmf\JDFRegistration.java@@\main\5
.\jmf\JDFRemoveLink.java@@\main\4
.\jmf\JDFRequestQueueEntryParams.java@@\main\14
.\jmf\JDFResourceCmdParams.java@@\main\27
.\jmf\JDFResourceInfo.java@@\main\32
.\jmf\JDFResourcePullParams.java@@\main\15
.\jmf\JDFResourceQuParams.java@@\main\18
.\jmf\JDFResponse.java@@\main\32
.\jmf\JDFResubmissionParams.java@@\main\11
.\jmf\JDFReturnQueueEntryParams.java@@\main\9
.\jmf\JDFShutDownCmdParams.java@@\main\10
.\jmf\JDFSignal.java@@\main\25
.\jmf\JDFStatusQuParams.java@@\main\18
.\jmf\JDFStopPersChParams.java@@\main\17
.\jmf\JDFSubmissionMethods.java@@\main\11
.\jmf\JDFSubscription.java@@\main\12
.\jmf\JDFTrackFilter.java@@\main\18
.\jmf\JDFTrackResult.java@@\main\17
.\jmf\JDFTrigger.java@@\main\11
.\jmf\JDFUpdateJDFCmdParams.java@@\main\4
.\jmf\JDFWakeUpCmdParams.java@@\main\10
.\node\JDFAncestor.java@@\main\34
.\node\JDFNode.java@@\main\274
.\node\JDFSpawned.java@@\main\28
.\node\VJDFNode.java@@\main\11
.\pool\JDFAmountPool.java@@\main\30
.\pool\JDFAncestorPool.java@@\main\49
.\pool\JDFAuditPool.java@@\main\106
.\pool\JDFPool.java@@\main\38
.\pool\JDFPreflightConstraintsPool.java@@\main\24
.\pool\JDFPreflightResultsPool.java@@\main\24
.\pool\JDFResourceLinkPool.java@@\main\83
.\pool\JDFResourcePool.java@@\main\71
.\pool\JDFStatusPool.java@@\main\47
.\resource\devicecapability\JDFAbstractState.java@@\main\58
.\resource\devicecapability\JDFAction.java@@\main\17
.\resource\devicecapability\JDFActionPool.java@@\main\8
.\resource\devicecapability\JDFand.java@@\main\11
.\resource\devicecapability\JDFBasicPreflightTest.java@@\main\5
.\resource\devicecapability\JDFBooleanEvaluation.java@@\main\17
.\resource\devicecapability\JDFBooleanState.java@@\main\29
.\resource\devicecapability\JDFcall.java@@\main\5
.\resource\devicecapability\JDFchoice.java@@\main\5
.\resource\devicecapability\JDFDateTimeEvaluation.java@@\main\17
.\resource\devicecapability\JDFDateTimeState.java@@\main\30
.\resource\devicecapability\JDFDevCap.java@@\main\58
.\resource\devicecapability\JDFDevCapPool.java@@\main\11
.\resource\devicecapability\JDFDevCaps.java@@\main\45
.\resource\devicecapability\JDFDeviceCap.java@@\main\56
.\resource\devicecapability\JDFDisplayGroup.java@@\main\5
.\resource\devicecapability\JDFDisplayGroupPool.java@@\main\5
.\resource\devicecapability\JDFDurationEvaluation.java@@\main\15
.\resource\devicecapability\JDFDurationState.java@@\main\29
.\resource\devicecapability\JDFEnumerationEvaluation.java@@\main\18
.\resource\devicecapability\JDFEnumerationState.java@@\main\32
.\resource\devicecapability\JDFEvaluation.java@@\main\30
.\resource\devicecapability\JDFFeatureAttribute.java@@\main\5
.\resource\devicecapability\JDFFeaturePool.java@@\main\6
.\resource\devicecapability\JDFIntegerEvaluation.java@@\main\16
.\resource\devicecapability\JDFIntegerState.java@@\main\31
.\resource\devicecapability\JDFIsPresentEvaluation.java@@\main\7
.\resource\devicecapability\JDFLoc.java@@\main\6
.\resource\devicecapability\JDFmacro.java@@\main\5
.\resource\devicecapability\JDFMacroPool.java@@\main\5
.\resource\devicecapability\JDFMatrixEvaluation.java@@\main\19
.\resource\devicecapability\JDFMatrixState.java@@\main\30
.\resource\devicecapability\JDFModule.java@@\main\6
.\resource\devicecapability\JDFModuleCap.java@@\main\2
.\resource\devicecapability\JDFModulePool.java@@\main\9
.\resource\devicecapability\JDFNameEvaluation.java@@\main\19
.\resource\devicecapability\JDFNameState.java@@\main\35
.\resource\devicecapability\JDFNodeTerm.java@@\main\11
.\resource\devicecapability\JDFnot.java@@\main\13
.\resource\devicecapability\JDFNumberEvaluation.java@@\main\17
.\resource\devicecapability\JDFNumberState.java@@\main\31
.\resource\devicecapability\JDFor.java@@\main\12
.\resource\devicecapability\JDFotherwise.java@@\main\5
.\resource\devicecapability\JDFPDFPathEvaluation.java@@\main\13
.\resource\devicecapability\JDFPDFPathState.java@@\main\26
.\resource\devicecapability\JDFRectangleEvaluation.java@@\main\19
.\resource\devicecapability\JDFRectangleState.java@@\main\32
.\resource\devicecapability\JDFset.java@@\main\5
.\resource\devicecapability\JDFShapeEvaluation.java@@\main\17
.\resource\devicecapability\JDFShapeState.java@@\main\32
.\resource\devicecapability\JDFStringEvaluation.java@@\main\15
.\resource\devicecapability\JDFStringState.java@@\main\27
.\resource\devicecapability\JDFTerm.java@@\main\7
.\resource\devicecapability\JDFTest.java@@\main\27
.\resource\devicecapability\JDFTestPool.java@@\main\8
.\resource\devicecapability\JDFTestRef.java@@\main\15
.\resource\devicecapability\JDFValueLoc.java@@\main\5
.\resource\devicecapability\JDFwhen.java@@\main\5
.\resource\devicecapability\JDFxor.java@@\main\13
.\resource\devicecapability\JDFXYPairEvaluation.java@@\main\18
.\resource\devicecapability\JDFXYPairState.java@@\main\29
.\resource\intent\JDFArtDelivery.java@@\main\23
.\resource\intent\JDFArtDeliveryIntent.java@@\main\19
.\resource\intent\JDFArtDeliveryType.java@@\main\13
.\resource\intent\JDFBindingIntent.java@@\main\19
.\resource\intent\JDFBookCase.java@@\main\18
.\resource\intent\JDFColorIntent.java@@\main\18
.\resource\intent\JDFDeliveryIntent.java@@\main\18
.\resource\intent\JDFDropIntent.java@@\main\21
.\resource\intent\JDFDropItemIntent.java@@\main\20
.\resource\intent\JDFEmbossingIntent.java@@\main\8
.\resource\intent\JDFFoldingIntent.java@@\main\19
.\resource\intent\JDFHoleMakingIntent.java@@\main\18
.\resource\intent\JDFInsertingIntent.java@@\main\18
.\resource\intent\JDFIntentResource.java@@\main\34
.\resource\intent\JDFLaminatingIntent.java@@\main\18
.\resource\intent\JDFLayoutIntent.java@@\main\17
.\resource\intent\JDFMediaIntent.java@@\main\16
.\resource\intent\JDFNumberingIntent.java@@\main\8
.\resource\intent\JDFPackingIntent.java@@\main\17
.\resource\intent\JDFPricing.java@@\main\16
.\resource\intent\JDFProductionIntent.java@@\main\21
.\resource\intent\JDFProofingIntent.java@@\main\18
.\resource\intent\JDFPublishingIntent.java@@\main\3
.\resource\intent\JDFScreeningIntent.java@@\main\3
.\resource\intent\JDFShapeCut.java@@\main\17
.\resource\intent\JDFShapeCuttingIntent.java@@\main\17
.\resource\intent\JDFSizeIntent.java@@\main\19
.\resource\JDFAdhesiveBindingParams.java@@\main\9
.\resource\JDFBand.java@@\main\19
.\resource\JDFBarcode.java@@\main\9
.\resource\JDFBindItem.java@@\main\12
.\resource\JDFBindList.java@@\main\8
.\resource\JDFBlockPreparationParams.java@@\main\8
.\resource\JDFBoxPackingParams.java@@\main\8
.\resource\JDFBufferParams.java@@\main\8
.\resource\JDFBundle.java@@\main\10
.\resource\JDFBundleItem.java@@\main\11
.\resource\JDFCaseMakingParams.java@@\main\8
.\resource\JDFCasingInParams.java@@\main\8
.\resource\JDFChangedAttribute.java@@\main\8
.\resource\JDFCoilBindingParams.java@@\main\8
.\resource\JDFColorMeasurementConditions.java@@\main\10
.\resource\JDFColorsUsed.java@@\main\8
.\resource\JDFContactCopyParams.java@@\main\9
.\resource\JDFCounterReset.java@@\main\8
.\resource\JDFCoverApplicationParams.java@@\main\10
.\resource\JDFCoverColor.java@@\main\9
.\resource\JDFCreasingParams.java@@\main\8
.\resource\JDFCreated.java@@\main\9
.\resource\JDFCreditCard.java@@\main\8
.\resource\JDFCuttingParams.java@@\main\16
.\resource\JDFDBSchema.java@@\main\8
.\resource\JDFDeleted.java@@\main\3
.\resource\JDFDevelopingParams.java@@\main\9
.\resource\JDFDevice.java@@\main\11
.\resource\JDFDeviceList.java@@\main\8
.\resource\JDFDeviceMark.java@@\main\10
.\resource\JDFDimensions.java@@\main\10
.\resource\JDFEdgeGluing.java@@\main\8
.\resource\JDFEmboss.java@@\main\10
.\resource\JDFEmbossingItem.java@@\main\9
.\resource\JDFEmbossingParams.java@@\main\8
.\resource\JDFEndSheetGluingParams.java@@\main\8
.\resource\JDFError.java@@\main\8
.\resource\JDFErrorData.java@@\main\4
.\resource\JDFEvent.java@@\main\3
.\resource\JDFFCNKey.java@@\main\8
.\resource\JDFFitPolicy.java@@\main\10
.\resource\JDFFormatConversionParams.java@@\main\8
.\resource\JDFGatheringParams.java@@\main\8
.\resource\JDFGluingParams.java@@\main\8
.\resource\JDFHardCoverBinding.java@@\main\8
.\resource\JDFHeadBandApplicationParams.java@@\main\8
.\resource\JDFHoleLine.java@@\main\8
.\resource\JDFIcon.java@@\main\10
.\resource\JDFIconList.java@@\main\9
.\resource\JDFIDPCover.java@@\main\8
.\resource\JDFIDPImageShift.java@@\main\8
.\resource\JDFIDPJobSheet.java@@\main\8
.\resource\JDFImageCompression.java@@\main\21
.\resource\JDFImageShift.java@@\main\17
.\resource\JDFInsert.java@@\main\17
.\resource\JDFInsertingParams.java@@\main\8
.\resource\JDFInsertList.java@@\main\17
.\resource\JDFInterpretingParams.java@@\main\9
.\resource\JDFJacketingParams.java@@\main\8
.\resource\JDFJobField.java@@\main\8
.\resource\JDFJobSheet.java@@\main\8
.\resource\JDFLabelingParams.java@@\main\8
.\resource\JDFLaminatingParams.java@@\main\12
.\resource\JDFLayerDetails.java@@\main\8
.\resource\JDFLayerList.java@@\main\9
.\resource\JDFLayoutPreparationParams.java@@\main\8
.\resource\JDFLocation.java@@\main\15
.\resource\JDFMarkObject.java@@\main\24
.\resource\JDFMerged.java@@\main\13
.\resource\JDFMilestone.java@@\main\2
.\resource\JDFModified.java@@\main\10
.\resource\JDFModulePhase.java@@\main\8
.\resource\JDFModuleStatus.java@@\main\9
.\resource\JDFNotification.java@@\main\20
.\resource\JDFNumberingParams.java@@\main\8
.\resource\JDFNumberItem.java@@\main\9
.\resource\JDFObservationTarget.java@@\main\8
.\resource\JDFPageCell.java@@\main\8
.\resource\JDFPageList.java@@\main\5
.\resource\JDFPages.java@@\main\9
.\resource\JDFPallet.java@@\main\8
.\resource\JDFPalletizingParams.java@@\main\8
.\resource\JDFPart.java@@\main\30
.\resource\JDFPayment.java@@\main\8
.\resource\JDFPDFInterpretingParams.java@@\main\8
.\resource\JDFPerforatingParams.java@@\main\8
.\resource\JDFPerformance.java@@\main\8
.\resource\JDFPhaseTime.java@@\main\33
.\resource\JDFPlaceHolderResource.java@@\main\6
.\resource\JDFPreflightAnalysis.java@@\main\8
.\resource\JDFProcessRun.java@@\main\35
.\resource\JDFProofItem.java@@\main\9
.\resource\JDFQueueEntryDefList.java@@\main\8
.\resource\JDFRegisterRibbon.java@@\main\10
.\resource\JDFRemoved.java@@\main\8
.\resource\JDFResource.java@@\main\235
.\resource\JDFResourceAudit.java@@\main\35
.\resource\JDFResourceParam.java@@\main\9
.\resource\JDFScavengerArea.java@@\main\8
.\resource\JDFShapeCuttingParams.java@@\main\8
.\resource\JDFShapeElement.java@@\main\8
.\resource\JDFShrinkingParams.java@@\main\8
.\resource\JDFSignature.java@@\main\22
.\resource\JDFSoftCoverBinding.java@@\main\8
.\resource\JDFSpinePreparationParams.java@@\main\10
.\resource\JDFSpineTapingParams.java@@\main\10
.\resource\JDFStackingParams.java@@\main\8
.\resource\JDFStrap.java@@\main\8
.\resource\JDFStrappingParams.java@@\main\8
.\resource\JDFStripBinding.java@@\main\8
.\resource\JDFStrippingParams.java@@\main\3
.\resource\JDFSystemTimeSet.java@@\main\8
.\resource\JDFTabs.java@@\main\13
.\resource\JDFTape.java@@\main\8
.\resource\JDFThreadSealingParams.java@@\main\8
.\resource\JDFTool.java@@\main\13
.\resource\JDFTransferFunctionControl.java@@\main\8
.\resource\JDFValue.java@@\main\26
.\resource\JDFVerificationParams.java@@\main\8
.\resource\JDFWeight.java@@\main\9
.\resource\JDFWrappingParams.java@@\main\9
.\resource\process\JDFAddress.java@@\main\19
.\resource\process\JDFAdvancedParams.java@@\main\20
.\resource\process\JDFApprovalDetails.java@@\main\3
.\resource\process\JDFApprovalParams.java@@\main\8
.\resource\process\JDFApprovalPerson.java@@\main\8
.\resource\process\JDFApprovalSuccess.java@@\main\21
.\resource\process\JDFArgumentValue.java@@\main\5
.\resource\process\JDFAssembly.java@@\main\5
.\resource\process\JDFAssemblySection.java@@\main\6
.\resource\process\JDFAutomatedOverPrintParams.java@@\main\16
.\resource\process\JDFBarcodeCompParams.java@@\main\3
.\resource\process\JDFBarcodeDetails.java@@\main\3
.\resource\process\JDFBarcodeProductionParams.java@@\main\2
.\resource\process\JDFBarcodeReproParams.java@@\main\3
.\resource\process\JDFBendingParams.java@@\main\3
.\resource\process\JDFBinderySignature.java@@\main\9
.\resource\process\JDFBindingQualityParams.java@@\main\5
.\resource\process\JDFBoxApplication.java@@\main\3
.\resource\process\JDFBoxArgument.java@@\main\6
.\resource\process\JDFBoxFoldAction.java@@\main\3
.\resource\process\JDFBoxFoldingParams.java@@\main\3
.\resource\process\JDFBoxToBoxDifference.java@@\main\5
.\resource\process\JDFBusinessInfo.java@@\main\8
.\resource\process\JDFByteMap.java@@\main\21
.\resource\process\JDFCCITTFaxParams.java@@\main\4
.\resource\process\JDFCIELABMeasuringField.java@@\main\17
.\resource\process\JDFCollatingItem.java@@\main\5
.\resource\process\JDFCollectingParams.java@@\main\8
.\resource\process\JDFColor.java@@\main\28
.\resource\process\JDFColorantAlias.java@@\main\17
.\resource\process\JDFColorantControl.java@@\main\26
.\resource\process\JDFColorantZoneDetails.java@@\main\18
.\resource\process\JDFColorControlStrip.java@@\main\17
.\resource\process\JDFColorPool.java@@\main\30
.\resource\process\JDFColorsResultsPool.java@@\main\17
.\resource\process\JDFComChannel.java@@\main\17
.\resource\process\JDFCompany.java@@\main\15
.\resource\process\JDFComponent.java@@\main\22
.\resource\process\JDFConstraintValue.java@@\main\11
.\resource\process\JDFContact.java@@\main\23
.\resource\process\JDFContainer.java@@\main\5
.\resource\process\JDFContentData.java@@\main\4
.\resource\process\JDFContentList.java@@\main\3
.\resource\process\JDFContentObject.java@@\main\24
.\resource\process\JDFConventionalPrintingParams.java@@\main\17
.\resource\process\JDFCostCenter.java@@\main\16
.\resource\process\JDFCover.java@@\main\12
.\resource\process\JDFCutBlock.java@@\main\16
.\resource\process\JDFCylinderLayout.java@@\main\3
.\resource\process\JDFCylinderLayoutPreparationParams.java@@\main\3
.\resource\process\JDFCylinderPosition.java@@\main\3
.\resource\process\JDFDBMergeParams.java@@\main\8
.\resource\process\JDFDBRules.java@@\main\8
.\resource\process\JDFDBSelection.java@@\main\8
.\resource\process\JDFDCTParams.java@@\main\4
.\resource\process\JDFDeliveryParams.java@@\main\8
.\resource\process\JDFDensityMeasuringField.java@@\main\17
.\resource\process\JDFDependencies.java@@\main\6
.\resource\process\JDFDeviceNColor.java@@\main\16
.\resource\process\JDFDeviceNSpace.java@@\main\18
.\resource\process\JDFDieLayout.java@@\main\3
.\resource\process\JDFDigitalMedia.java@@\main\5
.\resource\process\JDFDigitalPrintingParams.java@@\main\17
.\resource\process\JDFDisjointing.java@@\main\15
.\resource\process\JDFDisposition.java@@\main\5
.\resource\process\JDFDividingParams.java@@\main\18
.\resource\process\JDFDocumentResultsPool.java@@\main\17
.\resource\process\JDFDrop.java@@\main\8
.\resource\process\JDFDropItem.java@@\main\17
.\resource\process\JDFDynamicField.java@@\main\18
.\resource\process\JDFDynamicInput.java@@\main\17
.\resource\process\JDFElementColorParams.java@@\main\5
.\resource\process\JDFEmployee.java@@\main\19
.\resource\process\JDFExposedMedia.java@@\main\20
.\resource\process\JDFExternalImpositionTemplate.java@@\main\3
.\resource\process\JDFExtraValues.java@@\main\3
.\resource\process\JDFFeeder.java@@\main\5
.\resource\process\JDFFeederQualityParams.java@@\main\4
.\resource\process\JDFFeedingParams.java@@\main\4
.\resource\process\JDFFileAlias.java@@\main\17
.\resource\process\JDFFileSpec.java@@\main\34
.\resource\process\JDFFlateParams.java@@\main\4
.\resource\process\JDFFolderProduction.java@@\main\3
.\resource\process\JDFFoldOperation.java@@\main\3
.\resource\process\JDFFontParams.java@@\main\18
.\resource\process\JDFFontPolicy.java@@\main\17
.\resource\process\JDFGeneralID.java@@\main\4
.\resource\process\JDFIdentical.java@@\main\4
.\resource\process\JDFIdentificationField.java@@\main\17
.\resource\process\JDFIDPFinishing.java@@\main\14
.\resource\process\JDFIDPFolding.java@@\main\17
.\resource\process\JDFIDPHoleMaking.java@@\main\17
.\resource\process\JDFIDPLayout.java@@\main\12
.\resource\process\JDFIDPStitching.java@@\main\17
.\resource\process\JDFIDPTrimming.java@@\main\18
.\resource\process\JDFImageCompressionParams.java@@\main\18
.\resource\process\JDFImageReplacementParams.java@@\main\17
.\resource\process\JDFImageSetterParams.java@@\main\8
.\resource\process\JDFImagesResultsPool.java@@\main\18
.\resource\process\JDFInsertSheet.java@@\main\16
.\resource\process\JDFInterpretedPDLData.java@@\main\8
.\resource\process\JDFJBIG2Params.java@@\main\3
.\resource\process\JDFJPEG2000Params.java@@\main\3
.\resource\process\JDFLayout.java@@\main\23
.\resource\process\JDFLayoutElement.java@@\main\29
.\resource\process\JDFLayoutElementPart.java@@\main\4
.\resource\process\JDFLayoutElementProductionParams.java@@\main\4
.\resource\process\JDFLongFold.java@@\main\18
.\resource\process\JDFLongGlue.java@@\main\18
.\resource\process\JDFLongitudinalRibbonOperationParams.java@@\main\18
.\resource\process\JDFLongPerforate.java@@\main\18
.\resource\process\JDFLongSlit.java@@\main\18
.\resource\process\JDFLot.java@@\main\4
.\resource\process\JDFLZWParams.java@@\main\4
.\resource\process\JDFManualLaborParams.java@@\main\4
.\resource\process\JDFMedia.java@@\main\27
.\resource\process\JDFMediaLayers.java@@\main\3
.\resource\process\JDFMediaSource.java@@\main\16
.\resource\process\JDFMiscConsumable.java@@\main\3
.\resource\process\JDFMISDetails.java@@\main\5
.\resource\process\JDFNotificationFilter.java@@\main\16
.\resource\process\JDFNumberingParam.java@@\main\8
.\resource\process\JDFObjectResolution.java@@\main\15
.\resource\process\JDFOCGControl.java@@\main\3
.\resource\process\JDFOrderingParams.java@@\main\8
.\resource\process\JDFPackingParams.java@@\main\8
.\resource\process\JDFPageAssignedList.java@@\main\3
.\resource\process\JDFPageData.java@@\main\5
.\resource\process\JDFPageElement.java@@\main\3
.\resource\process\JDFPDFXParams.java@@\main\5
.\resource\process\JDFPDLCreationParams.java@@\main\3
.\resource\process\JDFPerforate.java@@\main\19
.\resource\process\JDFPerson.java@@\main\20
.\resource\process\JDFPixelColorant.java@@\main\18
.\resource\process\JDFPlateCopyParams.java@@\main\8
.\resource\process\JDFPosition.java@@\main\5
.\resource\process\JDFPreflightAction.java@@\main\5
.\resource\process\JDFPreflightArgument.java@@\main\6
.\resource\process\JDFPreflightParams.java@@\main\5
.\resource\process\JDFPreflightReport.java@@\main\7
.\resource\process\JDFPreflightReportRulePool.java@@\main\5
.\resource\process\JDFPRError.java@@\main\5
.\resource\process\JDFPreview.java@@\main\18
.\resource\process\JDFPRGroup.java@@\main\6
.\resource\process\JDFPRGroupOccurrence.java@@\main\7
.\resource\process\JDFPrintConditionColor.java@@\main\5
.\resource\process\JDFPrintRollingParams.java@@\main\4
.\resource\process\JDFPRItem.java@@\main\7
.\resource\process\JDFPROccurrence.java@@\main\8
.\resource\process\JDFProductionPath.java@@\main\5
.\resource\process\JDFProductionSubPath.java@@\main\4
.\resource\process\JDFProofingParams.java@@\main\18
.\resource\process\JDFPRRule.java@@\main\5
.\resource\process\JDFPRRuleAttr.java@@\main\5
.\resource\process\JDFQualityControlParams.java@@\main\4
.\resource\process\JDFQualityControlResult.java@@\main\5
.\resource\process\JDFQualityMeasurement.java@@\main\5
.\resource\process\JDFRegisterMark.java@@\main\18
.\resource\process\JDFResourceDefinitionParams.java@@\main\8
.\resource\process\JDFRollStand.java@@\main\5
.\resource\process\JDFRunList.java@@\main\57
.\resource\process\JDFScreenSelector.java@@\main\30
.\resource\process\JDFSealing.java@@\main\10
.\resource\process\JDFSeparationControlParams.java@@\main\8
.\resource\process\JDFSeparationSpec.java@@\main\16
.\resource\process\JDFSignatureCell.java@@\main\5
.\resource\process\JDFSourceResource.java@@\main\9
.\resource\process\JDFStation.java@@\main\3
.\resource\process\JDFStringListValue.java@@\main\5
.\resource\process\JDFStripCellParams.java@@\main\4
.\resource\process\JDFStripMark.java@@\main\3
.\resource\process\JDFSurface.java@@\main\38
.\resource\process\JDFThinPDFParams.java@@\main\18
.\resource\process\JDFTIFFEmbeddedFile.java@@\main\3
.\resource\process\JDFTIFFFormatParams.java@@\main\5
.\resource\process\JDFTIFFtag.java@@\main\3
.\resource\process\JDFTile.java@@\main\18
.\resource\process\JDFTransferCurve.java@@\main\25
.\resource\process\JDFTransferCurvePool.java@@\main\16
.\resource\process\JDFTransferCurveSet.java@@\main\19
.\resource\process\JDFTrapRegion.java@@\main\9
.\resource\process\JDFUsageCounter.java@@\main\5
.\resource\process\postpress\JDFAdhesiveBinding.java@@\main\18
.\resource\process\postpress\JDFBundlingParams.java@@\main\4
.\resource\process\postpress\JDFChannelBinding.java@@\main\13
.\resource\process\postpress\JDFChannelBindingParams.java@@\main\8
.\resource\process\postpress\JDFCoilBinding.java@@\main\13
.\resource\process\postpress\JDFCrease.java@@\main\18
.\resource\process\postpress\JDFCut.java@@\main\18
.\resource\process\postpress\JDFCutMark.java@@\main\17
.\resource\process\postpress\JDFEndSheet.java@@\main\29
.\resource\process\postpress\JDFFold.java@@\main\19
.\resource\process\postpress\JDFFoldingParams.java@@\main\18
.\resource\process\postpress\JDFGlue.java@@\main\19
.\resource\process\postpress\JDFGlueApplication.java@@\main\8
.\resource\process\postpress\JDFGlueLine.java@@\main\16
.\resource\process\postpress\JDFHole.java@@\main\15
.\resource\process\postpress\JDFHoleList.java@@\main\18
.\resource\process\postpress\JDFHoleMakingParams.java@@\main\20
.\resource\process\postpress\JDFPlasticCombBinding.java@@\main\14
.\resource\process\postpress\JDFPlasticCombBindingParams.java@@\main\8
.\resource\process\postpress\JDFRingBinding.java@@\main\14
.\resource\process\postpress\JDFRingBindingParams.java@@\main\8
.\resource\process\postpress\JDFSaddleStitching.java@@\main\11
.\resource\process\postpress\JDFSaddleStitchingParams.java@@\main\8
.\resource\process\postpress\JDFScore.java@@\main\27
.\resource\process\postpress\JDFSheet.java@@\main\43
.\resource\process\postpress\JDFSideSewing.java@@\main\11
.\resource\process\postpress\JDFSideSewingParams.java@@\main\8
.\resource\process\postpress\JDFSideStitching.java@@\main\11
.\resource\process\postpress\JDFStitchingParams.java@@\main\18
.\resource\process\postpress\JDFStripBindingParams.java@@\main\9
.\resource\process\postpress\JDFThreadSealing.java@@\main\11
.\resource\process\postpress\JDFThreadSewing.java@@\main\13
.\resource\process\postpress\JDFThreadSewingParams.java@@\main\8
.\resource\process\postpress\JDFTrimmingParams.java@@\main\18
.\resource\process\postpress\JDFVeloBinding.java@@\main\10
.\resource\process\postpress\JDFWebInlineFinishingParams.java@@\main\3
.\resource\process\postpress\JDFWireCombBinding.java@@\main\14
.\resource\process\postpress\JDFWireCombBindingParams.java@@\main\8
.\resource\process\prepress\JDFAssetListCreationParams.java@@\main\3
.\resource\process\prepress\JDFColorCorrectionOp.java@@\main\19
.\resource\process\prepress\JDFColorCorrectionParams.java@@\main\22
.\resource\process\prepress\JDFColorSpaceConversionOp.java@@\main\17
.\resource\process\prepress\JDFColorSpaceConversionParams.java@@\main\18
.\resource\process\prepress\JDFColorSpaceSubstitute.java@@\main\18
.\resource\process\prepress\JDFDigitalDeliveryParams.java@@\main\4
.\resource\process\prepress\JDFFileTypeResultsPool.java@@\main\18
.\resource\process\prepress\JDFFontsResultsPool.java@@\main\18
.\resource\process\prepress\JDFInk.java@@\main\20
.\resource\process\prepress\JDFInkZoneCalculationParams.java@@\main\18
.\resource\process\prepress\JDFInkZoneProfile.java@@\main\18
.\resource\process\prepress\JDFPagesResultsPool.java@@\main\18
.\resource\process\prepress\JDFPDFToPSConversionParams.java@@\main\18
.\resource\process\prepress\JDFPDLResourceAlias.java@@\main\18
.\resource\process\prepress\JDFPreflightConstraint.java@@\main\19
.\resource\process\prepress\JDFPreflightDetail.java@@\main\19
.\resource\process\prepress\JDFPreflightInstance.java@@\main\19
.\resource\process\prepress\JDFPreflightInstanceDetail.java@@\main\19
.\resource\process\prepress\JDFPreflightInventory.java@@\main\8
.\resource\process\prepress\JDFPreflightProfile.java@@\main\8
.\resource\process\prepress\JDFPreviewGenerationParams.java@@\main\8
.\resource\process\prepress\JDFPSToPDFConversionParams.java@@\main\18
.\resource\process\prepress\JDFRenderingParams.java@@\main\18
.\resource\process\prepress\JDFScanParams.java@@\main\19
.\resource\process\prepress\JDFScreeningParams.java@@\main\19
.\resource\process\prepress\JDFTrappingDetails.java@@\main\18
.\resource\process\prepress\JDFTrappingOrder.java@@\main\18
.\resource\process\prepress\JDFTrappingParams.java@@\main\17
.\resource\process\press\JDFIDPrintingParams.java@@\main\18
.\resource\process\press\JDFPrintCondition.java@@\main\6
.\span\JDFDurationSpan.java@@\main\22
.\span\JDFEnumerationSpan.java@@\main\35
.\span\JDFIntegerSpan.java@@\main\23
.\span\JDFNameSpan.java@@\main\31
.\span\JDFNumberSpan.java@@\main\29
.\span\JDFOptionSpan.java@@\main\25
.\span\JDFShapeSpan.java@@\main\21
.\span\JDFSpan.java@@\main\31
.\span\JDFSpanArtHandling.java@@\main\14
.\span\JDFSpanBase.java@@\main\38
.\span\JDFSpanBindingLength.java@@\main\14
.\span\JDFSpanBindingSide.java@@\main\15
.\span\JDFSpanBindingType.java@@\main\14
.\span\JDFSpanCoatings.java@@\main\11
.\span\JDFSpanCoilMaterial.java@@\main\14
.\span\JDFSpanColorType.java@@\main\14
.\span\JDFSpanCutType.java@@\main\14
.\span\JDFSpanDeliveryCharge.java@@\main\14
.\span\JDFSpanDirection.java@@\main\12
.\span\JDFSpanEdgeShape.java@@\main\14
.\span\JDFSpanFinishedGrainDirection.java@@\main\12
.\span\JDFSpanFrequencySelection.java@@\main\12
.\span\JDFSpanGlue.java@@\main\10
.\span\JDFSpanGlueProcedure.java@@\main\14
.\span\JDFSpanGlueType.java@@\main\13
.\span\JDFSpanGrainDirection.java@@\main\12
.\span\JDFSpanHoleType.java@@\main\16
.\span\JDFSpanImageStrategy.java@@\main\12
.\span\JDFSpanJacket.java@@\main\14
.\span\JDFSpanLevel.java@@\main\14
.\span\JDFSpanMediaType.java@@\main\12
.\span\JDFSpanMediaUnit.java@@\main\14
.\span\JDFSpanMethod.java@@\main\14
.\span\JDFSpanNamedColor.java@@\main\15
.\span\JDFSpanOpacity.java@@\main\14
.\span\JDFSpanPrintPreference.java@@\main\14
.\span\JDFSpanPrintProcess.java@@\main\14
.\span\JDFSpanProofType.java@@\main\14
.\span\JDFSpanScoring.java@@\main\14
.\span\JDFSpanScreeningType.java@@\main\12
.\span\JDFSpanShape.java@@\main\15
.\span\JDFSpanShapeType.java@@\main\14
.\span\JDFSpanSizePolicy.java@@\main\11
.\span\JDFSpanStripMaterial.java@@\main\14
.\span\JDFSpanSurface.java@@\main\14
.\span\JDFSpanSurplusHandling.java@@\main\14
.\span\JDFSpanTemperature.java@@\main\14
.\span\JDFSpanTightBacking.java@@\main\14
.\span\JDFSpanTransfer.java@@\main\14
.\span\JDFSpanWireCombMaterial.java@@\main\14
.\span\JDFSpanWireCombShape.java@@\main\15
.\span\JDFStringSpan.java@@\main\38
.\span\JDFTimeSpan.java@@\main\27
.\span\JDFXYPairSpan.java@@\main\27
.\util@@\main\26
.\util\ContainerUtil.java@@\main\7
.\util\DumpDir.java@@\main\6
.\util\Duration.java@@\main\4
.\util\FileUtil.java@@\main\8
.\util\HashUtil.java@@\main\4
.\util\JDFDate.java@@\main\52
.\util\JDFDuration.java@@\main\10
.\util\JDFMerge.java@@\main\27
.\util\JDFSpawn.java@@\main\27
.\util\MimeUtil.java@@\main\24
.\util\MultiModuleStatusCounter.java@@\main\4
.\util\MyArgs.java@@\main\29
.\util\SkipInputStream.java@@\main\1
.\util\StatusCounter.java@@\main\18
.\util\StatusUtil.java@@\main\12
.\util\StringUtil.java@@\main\71
.\util\UrlUtil.java@@\main\19
.\util\VectorMap.java@@\main\5
.\util\XMLstrm.java@@\main\9
.\validate\JDFValidator.java@@\main\2
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
