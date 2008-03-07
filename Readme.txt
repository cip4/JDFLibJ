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
