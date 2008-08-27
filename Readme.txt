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
