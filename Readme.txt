___________________________________________________________


Label JDFLIBJ_2.1.3BLD470 (05.10.2007)

removed setChunkedStreamingMode(), this function is only Java 5
queue automation improvements
add http streaming support (allow chunked write2url)
modifications for multiple parts in AmountPool/PartAmount
get"the" resource in ResourceInfo automatically
resolved claes conflicts
improved mime performance http://www.cip4.org/jira/browse/JDFJ-115
JDFJ-114 MimeUtil now resolves relative URLs
namespace typo checker
npe when evaluating unlinked resourcelink
statuscounter update for multiple passes, deviceID handling, WorkType
change import for XmlNames to Java 1.4 conformant import org.apache.crimson.util.XmlNames;
npe in getNodeInfo when link has combinedProcessIndex, but types does not exist
minor cleanup
added sendjdf servlet
moved getURLDoc() to JDFElement
move goldenticket from test to lib
added getUrlDoc to JDFReturnQueueEntryParams (returns the jdf doc referenced by url)
added golden ticket generators
added sendjdf servlet
cleanup
- renamed JDFLib-J to JDFLibJ in ant
- excluded SubVersion files and folders from .classpath
- removed unnecessary imports
- added test/temp folder to source control (folder only, no files / allows unit tests to pass on the first run after fresh checkout)
use a metered input stream for keepalive=false; clean up destructors in http client
queue synchronization for automated queue
set default for keep-alive to false in http routines
some new tests
fix for isNMTOKEN
added sscanf utility function
FilterInputStream.cpp - fix return type to allow -1
JDFElement.java added getChildrenByTagName with option to follow RefS
fix for missing Location attribute in JDFAutoResourceInfo.java and in JDFAutoResourceQuParams.java
LAYOUT tests
queue cleanup
check for both CR and LF
minor fix: delete tailing carriage return from mime content type
updated DEPENDENCIES.txt
corrected test.working.dir
updated comment for spam target
JDFNode.fixversion minor bug fix
fixes for generating internal links from devcaps
synch mediaintent and media graindirection enumerations
add NumList.contains()
add xsi:type in fixversion
bambi fixes for dispatching, some npes
cleanup of http header for mime multipart
mime fixes for non-ascii chars in xml
added ByteArrayIOStream class
removed spurious clone() implementations

>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD470) && !lbtype(JDFLIBJ_2.1.3BLD461)}" -print
.\auto\JDFAutoResourceInfo.java@@\main\61
.\auto\JDFAutoResourceQuParams.java@@\main\56
.\cformat\ScanfFormat.java@@\main\2
.\core\AttributeName.java@@\main\46
.\core\JDFAudit.java@@\main\89
.\core\JDFDoc.java@@\main\73
.\core\JDFElement.java@@\main\239
.\core\JDFParser.java@@\main\43
.\core\JDFResourceLink.java@@\main\137
.\core\KElement.java@@\main\252
.\core\XMLDoc.java@@\main\88
.\datatypes\JDFCMYKColor.java@@\main\8
.\datatypes\JDFIntegerList.java@@\main\17
.\datatypes\JDFLabColor.java@@\main\7
.\datatypes\JDFMatrix.java@@\main\19
.\datatypes\JDFNumberList.java@@\main\9
.\datatypes\JDFNumList.java@@\main\32
.\datatypes\JDFRectangle.java@@\main\21
.\datatypes\JDFRGBColor.java@@\main\7
.\datatypes\JDFShape.java@@\main\14
.\datatypes\JDFTransferFunction.java@@\main\9
.\datatypes\JDFXYPair.java@@\main\19
.\datatypes\VJDFAttributeMap.java@@\main\28
.\goldenticket@@\main\1
.\goldenticket\BaseGoldenTicket.java@@\main\3
.\goldenticket\JMFGoldenTicket.java@@\main\2
.\goldenticket\MISCPGoldenTicket.java@@\main\3
.\goldenticket\MISGoldenTicket.java@@\main\2
.\ifaces\IPlacedObject.java@@\main\2
.\jmf\JDFDeviceInfo.java@@\main\22
.\jmf\JDFJMF.java@@\main\64
.\jmf\JDFQueue.java@@\main\26
.\jmf\JDFQueueEntry.java@@\main\22
.\jmf\JDFQueueFilter.java@@\main\9
.\jmf\JDFQueueSubmissionParams.java@@\main\14
.\jmf\JDFResourceInfo.java@@\main\30
.\jmf\JDFReturnQueueEntryParams.java@@\main\8
.\jmf\JDFSubscription.java@@\main\11
.\node\JDFNode.java@@\main\257
.\pool\JDFAmountPool.java@@\main\27
.\pool\JDFAuditPool.java@@\main\98
.\resource\devicecapability\JDFAbstractState.java@@\main\52
.\resource\devicecapability\JDFDevCaps.java@@\main\40
.\resource\devicecapability\JDFDeviceCap.java@@\main\51
.\resource\devicecapability\JDFIntegerState.java@@\main\30
.\resource\devicecapability\JDFShapeEvaluation.java@@\main\16
.\resource\devicecapability\JDFShapeState.java@@\main\31
.\resource\JDFMarkObject.java@@\main\23
.\resource\JDFProcessRun.java@@\main\31
.\resource\JDFResource.java@@\main\226
.\resource\process\JDFComponent.java@@\main\20
.\resource\process\JDFContentObject.java@@\main\22
.\resource\process\JDFPerson.java@@\main\18
.\util@@\main\22
.\util\ByteArrayIOStream.java@@\main\1
.\util\EnumUtil.java@@\main\1
.\util\FileUtil.java@@\main\3
.\util\JDFMerge.java@@\main\16
.\util\JDFSpawn.java@@\main\19
.\util\MimeUtil.java@@\main\11
.\util\SScanf.java@@\main\1
.\util\StatusCounter.java@@\main\7
.\util\StatusUtil.java@@\main\9
.\util\StringUtil.java@@\main\63
.\util\UrlUtil.java@@\main\11
___________________________________________________________


Label JDFLIBJ_2.1.3BLD461 (29.08.2007)

added 5 missing updates for build 460

>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD461) && !lbtype(JDFLIBJ_2.1.3BLD460)}" -print
.\util\JDFDate.java@@\main\47
.\util\JDFSpawn.java@@\main\18
.\util\MimeUtil.java@@\main\6
.\util\StatusCounter.java@@\main\4
.\util\UrlUtil.java@@\main\9
___________________________________________________________


Label JDFLIBJ_2.1.3BLD460 (28.08.2007)

updated version string
check for duplicate partition key values
fix for non-file urls with directory
fix for spawning "almost-matching" partitions
fix for element type generation
c++: remove some warnings
spawn fixes for links with parts
fixes for getresourceroot
removed temp from svn
JDFResource.java: Added "Occupation" to validParentNodeNames.
Moved Method getValueforNewAttribute from EditorUtils.java to JDFElement.java.
writetofile reverted NOT to escape url strings
added XMLDoc.WriteToFile(File)
updated handling for empty <Part> elements
fix id generation from senderID for JMF
added routine to generate evaluations for states (devcaps)
removed spurious init calls
fix statuscounter to allow null nodes (idle)
add switch to ignore defaults to editor
select system timezone for null jdf date

>ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD460) && !lbtype(JDFLIBJ_2.1.3BLD450)}" -print
.\core\AttributeName.java@@\main\45
.\core\JDFAudit.java@@\main\88
.\core\JDFElement.java@@\main\237
.\core\JDFResourceLink.java@@\main\135
.\core\KElement.java@@\main\249
.\core\XMLDoc.java@@\main\87
.\datatypes\JDFAttributeMap.java@@\main\35
.\datatypes\VJDFAttributeMap.java@@\main\27
.\ifaces@@\main\4
.\ifaces\ICapabilityElement.java@@\main\1
.\ifaces\IDeviceCapable.java@@\main\1
.\jmf\JDFDeviceInfo.java@@\main\21
.\jmf\JDFJMF.java@@\main\63
.\jmf\JDFMessage.java@@\main\72
.\jmf\JDFMessageService.java@@\main\15
.\jmf\JDFPipeParams.java@@\main\20
.\jmf\JDFQueue.java@@\main\22
.\jmf\JDFSignal.java@@\main\23
.\node\JDFNode.java@@\main\251
.\resource\devicecapability\JDFAbstractState.java@@\main\51
.\resource\devicecapability\JDFActionPool.java@@\main\7
.\resource\devicecapability\JDFBooleanState.java@@\main\27
.\resource\devicecapability\JDFDateTimeState.java@@\main\29
.\resource\devicecapability\JDFDevCap.java@@\main\53
.\resource\devicecapability\JDFDevCaps.java@@\main\39
.\resource\devicecapability\JDFDeviceCap.java@@\main\50
.\resource\devicecapability\JDFDurationState.java@@\main\26
.\resource\devicecapability\JDFEnumerationState.java@@\main\30
.\resource\devicecapability\JDFEvaluation.java@@\main\24
.\resource\devicecapability\JDFIntegerState.java@@\main\29
.\resource\devicecapability\JDFMatrixState.java@@\main\28
.\resource\devicecapability\JDFModulePool.java@@\main\8
.\resource\devicecapability\JDFNameState.java@@\main\32
.\resource\devicecapability\JDFNumberState.java@@\main\30
.\resource\devicecapability\JDFPDFPathState.java@@\main\25
.\resource\devicecapability\JDFRectangleState.java@@\main\31
.\resource\devicecapability\JDFShapeState.java@@\main\30
.\resource\devicecapability\JDFStringState.java@@\main\26
.\resource\devicecapability\JDFTerm.java@@\main\6
.\resource\devicecapability\JDFXYPairState.java@@\main\28
.\resource\JDFResource.java@@\main\223
.\resource\process\JDFRunList.java@@\main\54
___________________________________________________________


Label JDFLIBJ_2.1.3BLD450 (03.08.2007)

alces: fix illegalargumentexception
minor fixes, cleanup
added submitqueuentry handler, added getURLDoc to queuesubmissionParams, minor npe in alces
added missing NotificationDetails element Milestone
fix xpath searches for resources in editor
added containerutil.equals
make JDFLibJ Project name consistent

ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD450) && !lbtype(JDFLIBJ_2.1.3BLD442)}" -print
.\auto@@\main\44
.\auto\JDFAutoMilestone.java@@\main\1
.\core\AttributeName.java@@\main\44
.\core\DocumentJDFImpl.java@@\main\92
.\core\JDFAudit.java@@\main\87
.\core\KElement.java@@\main\248
.\core\XMLDoc.java@@\main\86
.\datatypes\JDFMatrix.java@@\main\17
.\jmf\JDFQueueSubmissionParams.java@@\main\12
.\resource@@\main\34
.\resource\devicecapability\JDFDevCap.java@@\main\52
.\resource\JDFMilestone.java@@\main\1
.\resource\JDFNotification.java@@\main\18
.\resource\JDFResource.java@@\main\222
.\util\ContainerUtil.java@@\main\3
.\util\JDFDate.java@@\main\46
.\util\StatusCounter.java@@\main\3
.\util\UrlUtil.java@@\main\8
___________________________________________________________


Label JDFLIBJ_2.1.3BLD442 (24.07.2007)

spawn and merge fix for mrging non partitioned deep nodes
queue automation for bambi
double formatting for exponential numbers
numlist cleanup
use specific attribute types for attributes of part elements (e.g. String --> JDFIntegerRangeList)
merging in varying sequences
removed JDFAutoResourceInfo.(getCreateResource,getResource,appendResource) as Resource is abstract
generator for auto files :
   don't create JDFAutoResourceInfo.(getCreateResource,getResource,appendResource) as Resource is abstract
getWorkStepID actually returns a string...


ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD442) && !lbtype(JDFLIBJ_2.1.3BLD441)}" -print
.\auto\JDFAutoPart.java@@\main\51
.\auto\JDFAutoResourceInfo.java@@\main\60
.\core\JDFDoc.java@@\main\71
.\core\JDFElement.java@@\main\236
.\core\KElement.java@@\main\246
.\core\XMLDoc.java@@\main\85
.\core\XMLDocUserData.java@@\main\28
.\datatypes\JDFBaseDataTypes.java@@\main\10
.\datatypes\JDFCMYKColor.java@@\main\7
.\datatypes\JDFIntegerList.java@@\main\15
.\datatypes\JDFNumList.java@@\main\29
.\datatypes\JDFRectangle.java@@\main\20
.\datatypes\JDFXYPair.java@@\main\18
.\ifaces@@\main\3
.\ifaces\IJMFSubscribable.java@@\main\1
.\jmf\JDFJMF.java@@\main\62
.\jmf\JDFMessage.java@@\main\71
.\jmf\JDFMessageService.java@@\main\14
.\jmf\JDFQuery.java@@\main\21
.\jmf\JDFQueue.java@@\main\21
.\jmf\JDFQueueEntry.java@@\main\19
.\jmf\JDFQueueSubmissionParams.java@@\main\11
.\jmf\JDFRegistration.java@@\main\4
.\jmf\JDFResponse.java@@\main\28
.\jmf\JDFSignal.java@@\main\22
.\node\JDFNode.java@@\main\250
.\pool\JDFAuditPool.java@@\main\97
.\resource\JDFPhaseTime.java@@\main\28
.\resource\JDFResource.java@@\main\221
.\util@@\main\20
.\util\ContainerUtil.java@@\main\2
.\util\FileUtil.java@@\main\2
.\util\JDFDate.java@@\main\45
.\util\JDFDuration.java@@\main\8
.\util\JDFMerge.java@@\main\15
.\util\JDFSpawn.java@@\main\17
.\util\MimeUtil.java@@\main\5
.\util\PrefixInputStream.java@@\main\1
.\util\StatusCounter.java@@\main\1
.\util\StatusUtil.java@@\main\7
.\util\StringUtil.java@@\main\61
.\util\VectorMap.java@@\main\1
___________________________________________________________


Label JDFLIBJ_2.1.3BLD441 (06.07.2007)

fix for setattributes(KElement) when applied to resources
added NodeInfo get/setWorkStepID
remove ".." in id generation


ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD441) && !lbtype(JDFLIBJ_2.1.3BLD440)}" -print
.\core\JDFElement.java@@\main\235
.\core\JDFNodeInfo.java@@\main\54
.\core\KElement.java@@\main\245
___________________________________________________________


Label JDFLIBJ_2.1.3BLD440 (05.07.2007)

correct handling of multiple elements in subnodes when calling getChildElementVector for resources
additional sanity check when merging partitions
url fixes
setAttribute(ns:foo,null,"www.foobar) now correctly removes ns:foo
move StreamToFile from urlutil to fileutil
implement negative durations
npe in isexecutable http://www.cip4.org/jira//browse/EDITOR-135
fix for xpath search in editor
xpath enhancements
add high level locator methods to comchannel (mailto: and tel:)
add stackwalker wrapper to c++ for memory leak checks
add stationName partiton key
add support for multiple nodeinfo by combinedprocessindex
npe fix
parallel spawn test
add module references in devcaps availability
modifications for modules in device capabilities
actualcolorname support
remove combinedprocessindex for JDFNode.linkResource


ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD440) && !lbtype(JDFLIBJ_2.1.3BLD432)}" -print
.\auto\JDFAutoColor.java@@\main\74
.\auto\JDFAutoDevCap.java@@\main\48
.\auto\JDFAutoDevCaps.java@@\main\53
.\auto\JDFAutoModuleCap.java@@\main\14
.\auto\JDFAutoPart.java@@\main\50
.\auto\JDFAutoStation.java@@\main\12
.\core\AttributeName.java@@\main\43
.\core\JDFConstants.java@@\main\70
.\core\JDFElement.java@@\main\234
.\core\JDFNodeInfo.java@@\main\53
.\core\JDFRefElement.java@@\main\61
.\core\JDFResourceLink.java@@\main\134
.\core\KElement.java@@\main\244
.\core\VElement.java@@\main\32
.\core\XMLDoc.java@@\main\83
.\ifaces@@\main\2
.\ifaces\IModuleCapability.java@@\main\1
.\jmf\JDFMessage.java@@\main\70
.\jmf\JDFQueue.java@@\main\20
.\node\JDFNode.java@@\main\249
.\resource\devicecapability\JDFAbstractState.java@@\main\50
.\resource\devicecapability\JDFDevCap.java@@\main\51
.\resource\devicecapability\JDFDevCaps.java@@\main\38
.\resource\devicecapability\JDFDeviceCap.java@@\main\49
.\resource\devicecapability\JDFModulePool.java@@\main\7
.\resource\JDFResource.java@@\main\220
.\resource\process\JDFColor.java@@\main\26
.\resource\process\JDFColorPool.java@@\main\27
.\resource\process\JDFComChannel.java@@\main\15
.\util@@\main\19
.\util\BiHashMap.java@@\main\1
.\util\FileUtil.java@@\main\1
.\util\JDFDuration.java@@\main\7
.\util\JDFMerge.java@@\main\13
.\util\MimeUtil.java@@\main\4
.\util\StatusUtil.java@@\main\6
.\util\StringUtil.java@@\main\60
.\util\UrlUtil.java@@\main\7
___________________________________________________________


Label JDFLIBJ_2.1.3BLD432 (08.06.2007)

made VJDFAttributemap.toString() more readable
fix in JDFNode.getExecutablePartition
changed for loops to iterator loops, cosmetic changes
updated printtalk spec
minor editor cleanup
new class ContainerUtil.java and ContainerUtilTest.java

ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD432) && !lbtype(JDFLIBJ_2.1.3BLD431)}" -print
.\cformat\ScanfReader.java@@\main\4
.\core\JDFDocumentBuilder.java@@\main\4
.\core\JDFElement.java@@\main\233
.\core\KElement.java@@\main\241
.\datatypes\VJDFAttributeMap.java@@\main\26
.\jmf\JDFMessage.java@@\main\69
.\jmf\JDFResourceCmdParams.java@@\main\24
.\node\JDFNode.java@@\main\246
.\pool\JDFAuditPool.java@@\main\96
.\resource\JDFResource.java@@\main\218
.\util@@\main\18
.\util\ContainerUtil.java@@\main\1
___________________________________________________________


Label JDFLIBJ_2.1.3BLD431 (30.05.2007)

JDFDevCaps errata fix - data type of getDevCapRef now VString
added some tests
duration fix for overflows
Add default maximum value support for IntegerRange
fix GetMinID (cleanup only in java)
fix id cache handling
improved validation of DevCaps
fixes + tests for spawning of non-identical resource partitions in / out / spawn
xpath fixes for partitioned resources
add validation of warnings
add editor normalize
fix dead loop in getpredecessors
add fractional Durations
fix bug that causes referenced rw resources to be spawned ro
add transfercurve to list of tested datatypes in attrinfo
pipeparams fix for finding THE resource, (the one selected by the pipe)
cleanup of isexecutable for resourcelink
better handling of partitions for the colorcodined process view in the Editor.java
added function to sort chidren of jdf whose order is irrelevant
npe fix


Changes due to schema fixes:

JDFAutoDevCaps : DEVCAPREF is EnumAttributeType.IDREFS, setDevCapRef() and getDevCapRef() use VString
JDFAutoDevice  : ICSVERSIONS is EnumAttributeType.NMTOKENS, setICSVersions() and getICSVersions() use VString
JDFAutoEmboss  : spelling fix EnumEmbossingType.BlindEmbossing, added EnumEmbossingType.Braille
JDFAutoLayoutElement : spelling fix EnumElementType.Auxiliary
JDFAutoMedia         : added EnumHoleType.S1_generic, EnumHoleType.S_generic
JDFAutoModulePhase   : attribute DEVICESTATUS ist optional, attribute START ist optional
JDFAutoPhaseTime     : attribute END is optional
JDFAutoPlasticCombBindingParams : added EnumType.S1_generic, EnumType.S_generic
JDFAutoResourceAudit : added optional attribute NODESTATUS

ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD431) && !lbtype(JDFLIBJ_2.1.3BLD430)}" -print
.\auto\JDFAutoDevCaps.java@@\main\52
.\auto\JDFAutoDevice.java@@\main\69
.\auto\JDFAutoEmboss.java@@\main\51
.\auto\JDFAutoLayoutElement.java@@\main\74
.\auto\JDFAutoMedia.java@@\main\79
.\auto\JDFAutoModulePhase.java@@\main\77
.\auto\JDFAutoPhaseTime.java@@\main\59
.\auto\JDFAutoPlasticCombBindingParams.java@@\main\57
.\auto\JDFAutoResourceAudit.java@@\main\49
.\core\AttributeInfo.java@@\main\34
.\core\JDFAudit.java@@\main\85
.\core\JDFElement.java@@\main\231
.\core\JDFResourceLink.java@@\main\132
.\core\KElement.java@@\main\240
.\datatypes\JDFIntegerRange.java@@\main\31
.\datatypes\JDFMatrix.java@@\main\16
.\jmf\JDFMessage.java@@\main\67
.\jmf\JDFPipeParams.java@@\main\19
.\node\JDFNode.java@@\main\244
.\pool\JDFAuditPool.java@@\main\95
.\resource@@\main\33
.\resource\devicecapability\JDFAbstractState.java@@\main\49
.\resource\devicecapability\JDFDateTimeState.java@@\main\28
.\resource\devicecapability\JDFDevCap.java@@\main\50
.\resource\devicecapability\JDFDevCaps.java@@\main\37
.\resource\JDFResource.java@@\main\216
.\util\Duration.java@@\main\3
.\util\JDFDuration.java@@\main\6
.\util\JDFMerge.java@@\main\12
.\util\JDFSpawn.java@@\main\16
.\util\MyArgs.java@@\main\25
.\util\StringUtil.java@@\main\59
___________________________________________________________


Label JDFLIBJ_2.1.3BLD430 (10.05.2007)

getpredecesoors dead loop fix
checkjdf add language
updates for JDFUtility webserver
nederlands xslt
added vector utilities to JDFAttributeMap
added JDFAudit.createUpdateAudit
some more tests
Improved Spawning Merging for parallel partitions (java and c++)
added Kelement.getMultipleIDs
minor bug fix in MyArgs.java
some more tests
JDFJ-105 : JDFAutoErrorData is missing and JDFErrorData inherits from JDFError - snafu...
capabilities enhancements for the Editor.java
one new  spawn test
xpath enhancements
handle filenames with spaces from the command line
buildXPath now has new option to include partition keys
editor can populate a node from a caps file
mime files are opened from the editor regardless of extension
minor buglets and cleanup and documentation
read schema location from xml file if it exists
Editor fixes for capabilities
bug fix SignatureCell/@SectionIndex Jira/Editor-118


Changed files:
ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD430) && !lbtype(JDFLIBJ_2.1.3BLD420)}" -print
.\auto@@\main\43
...
.\auto\JDFAutoError.java@@\main\43
.\auto\JDFAutoErrorData.java@@\main\1
...
.\cformat\PrintfFormat.java@@\main\4
.\cformat\ScanfMatchException.java@@\main\2
.\cformat\ScanfReader.java@@\main\3
.\core\AttributeInfo.java@@\main\33
.\core\AttributeName.java@@\main\41
.\core\DocumentJDFImpl.java@@\main\91
.\core\JDFAudit.java@@\main\84
.\core\JDFElement.java@@\main\230
.\core\JDFParser.java@@\main\42
.\core\JDFResourceLink.java@@\main\130
.\core\KElement.java@@\main\239
.\core\VElement.java@@\main\31
.\core\XMLDoc.java@@\main\82
.\core\XMLDocUserData.java@@\main\27
.\datatypes\JDFAttributeMap.java@@\main\34
.\datatypes\VJDFAttributeMap.java@@\main\25
.\node\JDFNode.java@@\main\243
.\pool\JDFAuditPool.java@@\main\94
.\resource\devicecapability\JDFAbstractState.java@@\main\48
.\resource\devicecapability\JDFBooleanState.java@@\main\26
.\resource\devicecapability\JDFDateTimeState.java@@\main\27
.\resource\devicecapability\JDFDevCap.java@@\main\49
.\resource\devicecapability\JDFDevCaps.java@@\main\36
.\resource\devicecapability\JDFDeviceCap.java@@\main\48
.\resource\devicecapability\JDFDurationEvaluation.java@@\main\14
.\resource\devicecapability\JDFDurationState.java@@\main\25
.\resource\devicecapability\JDFEnumerationState.java@@\main\29
.\resource\devicecapability\JDFEvaluation.java@@\main\23
.\resource\devicecapability\JDFIntegerState.java@@\main\28
.\resource\devicecapability\JDFMatrixState.java@@\main\27
.\resource\devicecapability\JDFNameState.java@@\main\31
.\resource\devicecapability\JDFNumberState.java@@\main\29
.\resource\devicecapability\JDFPDFPathState.java@@\main\24
.\resource\devicecapability\JDFRectangleState.java@@\main\30
.\resource\devicecapability\JDFShapeState.java@@\main\29
.\resource\devicecapability\JDFStringState.java@@\main\25
.\resource\devicecapability\JDFXYPairState.java@@\main\27
.\resource\JDFErrorData.java@@\main\3
.\resource\JDFResource.java@@\main\215
.\resource\JDFResourceAudit.java@@\main\33
.\resource\JDFValue.java@@\main\25
.\resource\process\JDFColorPool.java@@\main\26
.\resource\process\JDFRunList.java@@\main\53
.\util\JDFDuration.java@@\main\5
.\util\JDFMerge.java@@\main\11
.\util\JDFSpawn.java@@\main\15
.\util\MimeUtil.java@@\main\3
.\util\MyArgs.java@@\main\24
.\util\UrlUtil.java@@\main\6
___________________________________________________________


Label JDFLIBJ_2.1.3BLD421 (13.04.2007)

Fix JDFResourceLink.isexecutable to analyze minstatus
do not write resource/AgentName by default
bug when spawning / merging partitioned jdf1.3 nodeinfo
bug when creating partitioned spawn and merge audits
replace deprecated function call KElement buildXPath(null)) with buildXPath(null,true))
fix organize imports

Changed files:
ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD421) && !lbtype(JDFLIBJ_2.1.3BLD420)}" -print
.\cformat\PrintfFormat.java@@\main\3
.\cformat\ScanfReader.java@@\main\2
.\core\DocumentJDFImpl.java@@\main\91
.\core\JDFParser.java@@\main\42
.\core\XMLDocUserData.java@@\main\26
.\pool\JDFAuditPool.java@@\main\93
.\resource\devicecapability\JDFDevCap.java@@\main\47
.\resource\devicecapability\JDFDevCaps.java@@\main\34
.\resource\devicecapability\JDFDeviceCap.java@@\main\46
.\resource\devicecapability\JDFEvaluation.java@@\main\22
.\resource\JDFResource.java@@\main\212
.\resource\JDFResourceAudit.java@@\main\33
.\util\JDFMerge.java@@\main\10
.\util\JDFSpawn.java@@\main\14
___________________________________________________________


Label JDFLIBJ_2.1.3BLD420 (29.03.2007)

fix for JDFDate memory
added jmf to audit conversion to JDFAuditPool.html
some editor capabilities work
printf for java/org
some layout tests
fixes for GeneralID
layout high level 1.2 1.3 indifferent vector getters
automated layout tests
fixed smoothed JDFEditor.exe for windows
updated version ids
Fix for http://www.cip4.org/jira/browse/JDFJ-93 and http://www.cip4.org/jira/browse/EDITOR-110
minor bug fixes for the editor -

Changed files:
ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD420) && !lbtype(JDFLIBJ_2.1.3BLD410)}" -print
.@@\main\23
.\cformat@@\main\1
.\cformat\package.html@@\main\1
.\cformat\PrintfFormat.java@@\main\2
.\cformat\PrintfStream.java@@\main\1
.\cformat\PrintfWriter.java@@\main\1
.\cformat\ScanfFormat.java@@\main\1
.\cformat\ScanfMatchException.java@@\main\1
.\cformat\ScanfReader.java@@\main\1
.\core\DocumentJDFImpl.java@@\main\90
.\core\JDFAudit.java@@\main\82
.\core\JDFDoc.java@@\main\69
.\core\JDFElement.java@@\main\229
.\core\JDFParser.java@@\main\41
.\core\JDFResourceLink.java@@\main\128
.\core\KElement.java@@\main\237
.\jmf\JDFJobPhase.java@@\main\21
.\jmf\JDFMessage.java@@\main\66
.\node\JDFNode.java@@\main\241
.\pool\JDFAuditPool.java@@\main\92
.\resource\devicecapability\JDFDevCap.java@@\main\46
.\resource\devicecapability\JDFDevCaps.java@@\main\33
.\resource\devicecapability\JDFDeviceCap.java@@\main\45
.\resource\JDFResource.java@@\main\211
.\resource\JDFResourceAudit.java@@\main\32
.\resource\JDFSignature.java@@\main\21
.\resource\process\JDFColorantAlias.java@@\main\16
.\resource\process\JDFLayout.java@@\main\21
.\resource\process\postpress\JDFSheet.java@@\main\42
.\util\JDFDate.java@@\main\44
.\util\JDFSpawn.java@@\main\12
.\util\StringUtil.java@@\main\58
___________________________________________________________


Label JDFLIBJ_2.1.3BLD410 (28.02.2007)

remove minor warnings
Jira JDFJ-91 exception when a resource is missing
correctly update amounts of a resource when merging
add parameters to parser for factory configuration of non-JDF files
add some high level functions for seapartion access in colorantcomtrol
fix id for extendedaddress (don't add)
added filter for automatically generating AgentName and AgentVersion in JDFResource.java; default is now off.
remove eraseemptynodes for pools
editor devcap display
editor jmf capabilities check
 + additional unit tests
remove cpp header files from generator output (not implemented yet)
changed eclipse project name to "JDFLib-J (cip4)"
CheckJDFTest: fixed file locations
added extension schema test
fix processing of single streams from the command line
changed the needed libs to relative path instead of class path variable
fix processing of single streams from the command line
remove ResourceLink from auto files
Capabilities fixes for DefaultValue and CurrentValue Lists
Better namespace handling in KElement.setAttributes(KElement src)
Milestone added to valid subelements of JDFNotification
npe catch in Merge

Changed files:
ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD410) && !lbtype(JDFLIBJ_2.1.3BLD401)}" -print
.\auto\JDFAutoCreateLink.java@@\main\12
.\auto\JDFAutoPhaseTime.java@@\main\57
.\auto\JDFAutoPipeParams.java@@\main\49
.\auto\JDFAutoRemoveLink.java@@\main\12
.\auto\JDFAutoResourceAudit.java@@\main\47
.\core\DocumentJDFImpl.java@@\main\89
.\core\JDFComment.java@@\main\28
.\core\JDFElement.java@@\main\228
.\core\JDFParser.java@@\main\40
.\core\JDFResourceLink.java@@\main\127
.\core\KElement.java@@\main\234
.\jmf\JDFDeviceInfo.java@@\main\20
.\jmf\JDFJMF.java@@\main\61
.\jmf\JDFMessageService.java@@\main\13
.\jmf\JDFPipeParams.java@@\main\18
.\jmf\JDFResourceInfo.java@@\main\29
.\node\JDFNode.java@@\main\239
.\pool\JDFAncestorPool.java@@\main\46
.\pool\JDFAuditPool.java@@\main\91
.\pool\JDFStatusPool.java@@\main\45
.\resource\devicecapability\JDFAbstractState.java@@\main\46
.\resource\devicecapability\JDFDevCap.java@@\main\44
.\resource\devicecapability\JDFDevCaps.java@@\main\31
.\resource\devicecapability\JDFDeviceCap.java@@\main\43
.\resource\devicecapability\JDFIntegerState.java@@\main\27
.\resource\devicecapability\JDFNameState.java@@\main\30
.\resource\devicecapability\JDFNumberState.java@@\main\28
.\resource\devicecapability\JDFStringState.java@@\main\24
.\resource\JDFNotification.java@@\main\17
.\resource\JDFPart.java@@\main\27
.\resource\JDFResource.java@@\main\208
.\resource\JDFResourceAudit.java@@\main\31
.\resource\process\JDFColorantControl.java@@\main\22
.\resource\process\JDFFileSpec.java@@\main\31
.\resource\process\JDFUsageCounter.java@@\main\3
.\util\JDFMerge.java@@\main\9
.\util\JDFSpawn.java@@\main\11
.\util\MyArgs.java@@\main\22
.\util\StatusUtil.java@@\main\5
___________________________________________________________

Label JDFLIBJ_2.1.3BLD401 (14.02.2007)

devcap for editor
Updates for partitioned layout
npe catch for setAttribute of object types
some new example tests

Changed files:
ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD401) && !lbtype(JDFLIBJ_2.1.3BLD400)}" -print
.\core\JDFElement.java@@\main\227
.\core\JDFResourceLink.java@@\main\125
.\datatypes\VJDFAttributeMap.java@@\main\24
.\jmf\JDFJobPhase.java@@\main\20
.\node\JDFNode.java@@\main\236
.\resource\devicecapability\JDFDevCap.java@@\main\42
.\resource\JDFSignature.java@@\main\20
.\resource\process\JDFRegisterMark.java@@\main\17
.\resource\process\postpress\JDFSheet.java@@\main\41
.\util\JDFSpawn.java@@\main\10

___________________________________________________________

Label JDFLIBJ_2.1.3BLD400 (08.02.2007)


API change due to schema bug :
    rename AutomatedOverprintParams to AutomatedOverPrintParams etc.

bug fix for heuristics in KElement.getNamespaceURI()

added some sec examples to the example tests


Changed files:
ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD400) && !lbtype(JDFLIBJ_2.1.3BLD391)}" -print
.\auto@@\main\42
.\auto\JDFAutoAutomatedOverPrintParams.java@@\main\61
.\auto\JDFAutoElementColorParams.java@@\main\31
.\auto\JDFAutoRenderingParams.java@@\main\69
.\auto\JDFAutoSeparationControlParams.java@@\main\43
.\core\DocumentJDFImpl.java@@\main\88
.\core\ElementName.java@@\main\40
.\core\JDFAudit.java@@\main\81
.\core\JDFElement.java@@\main\226
.\core\KElement.java@@\main\232
.\datatypes\JDFXYPair.java@@\main\17
.\datatypes\VJDFAttributeMap.java@@\main\23
.\jmf\JDFPipeParams.java@@\main\17
.\pool\JDFStatusPool.java@@\main\44
.\resource\devicecapability\JDFDeviceCap.java@@\main\41
.\resource\devicecapability\JDFEnumerationEvaluation.java@@\main\17
.\resource\JDFResource.java@@\main\207
.\resource\process@@\main\24
.\resource\process\JDFAutomatedOverPrintParams.java@@\main\15
.\util\StatusUtil.java@@\main\4
.\util\StringUtil.java@@\main\56

___________________________________________________________

Label JDFLIBJ_2.1.3BLD391 (18.01.2007)


Bug fixes due to API change in Build 390 :
    JDFResourceLinkPool.getPoolChildren and JDFResourceLinkPool.getInOutLinks can return null

Changed files:
ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD391) && !lbtype(JDFLIBJ_2.1.3BLD390)}" -print

.\jdflib\node\JDFNode.java@@\main\235
.\jdflib\pool\JDFResourceLinkPool.java@@\main\78
.\jdflib\resource\devicecapability\JDFDevCaps.java@@\main\29
.\jdflib\util\JDFMerge.java@@\main\8

___________________________________________________________

Label JDFLIBJ_2.1.3BLD390 (15.01.2007)


Jira issues fixed since JDFLIBJ_2.1.2BLD380:

Improvement     JDFJ-85     ScreenSelector/@Separation
Bug         JDFJ-83     Device capabilities test does not always look for attributes in leaf nodes of partitioned resources


Changed files:
ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD390) && !lbtype(JDFLIBJ_2.1.3BLD381)}" -print

.\src\org\cip4\jdflib\auto\JDFAutoDensityMeasuringField.java@@\main\67
.\src\org\cip4\jdflib\auto\JDFAutoScreenSelector.java@@\main\64
.\src\org\cip4\jdflib\auto\JDFAutoTransferCurve.java@@\main\61
.\src\org\cip4\jdflib\core\DocumentJDFImpl.java@@\main\87
.\src\org\cip4\jdflib\core\JDFAudit.java@@\main\80
.\src\org\cip4\jdflib\core\JDFElement.java@@\main\225
.\src\org\cip4\jdflib\core\JDFParser.java@@\main\39
.\src\org\cip4\jdflib\core\KElement.java@@\main\229
.\src\org\cip4\jdflib\datatypes\JDFNumberRange.java@@\main\24
.\src\org\cip4\jdflib\jmf\JDFResourceCmdParams.java@@\main\23
.\src\org\cip4\jdflib\node\JDFNode.java@@\main\234
.\src\org\cip4\jdflib\pool\JDFAuditPool.java@@\main\90
.\src\org\cip4\jdflib\pool\JDFResourceLinkPool.java@@\main\77
.\src\org\cip4\jdflib\resource\devicecapability\JDFDevCap.java@@\main\41
.\src\org\cip4\jdflib\resource\devicecapability\JDFDevCaps.java@@\main\28
.\src\org\cip4\jdflib\resource\devicecapability\JDFDeviceCap.java@@\main\40
.\src\org\cip4\jdflib\resource\devicecapability\JDFEnumerationEvaluation.java@@\main\16
.\src\org\cip4\jdflib\resource\devicecapability\JDFEvaluation.java@@\main\21
.\src\org\cip4\jdflib\resource\devicecapability\JDFNameEvaluation.java@@\main\17
.\src\org\cip4\jdflib\resource\devicecapability\JDFNodeTerm.java@@\main\9
.\src\org\cip4\jdflib\resource\devicecapability\JDFTest.java@@\main\26
.\src\org\cip4\jdflib\resource\JDFDevice.java@@\main\9
.\src\org\cip4\jdflib\resource\JDFResource.java@@\main\205
.\src\org\cip4\jdflib\resource\JDFSignature.java@@\main\19
.\src\org\cip4\jdflib\resource\process\JDFCutBlock.java@@\main\14
.\src\org\cip4\jdflib\util\JDFMerge.java@@\main\7
.\src\org\cip4\jdflib\util\JDFSpawn.java@@\main\9

___________________________________________________________

Label JDFLIBJ_2.1.3BLD381 (05.12.2006)

Changes:  - Vermeidung doppelter Attribute beim Aufruf von SetAttributeNS

Changed files:

ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD381) && !lbtype(JDFLIBJ_2.1.3BLD380)}" -print
.\jdflib\core\AttributeName.java@@\main\39
.\jdflib\core\JDFComment.java@@\main\27
.\jdflib\core\JDFElement.java@@\main\224
.\jdflib\core\KElement.java@@\main\228
.\jdflib\datatypes\JDFAttributeMap.java@@\main\33
.\jdflib\node\JDFNode.java@@\main\233
.\jdflib\resource\devicecapability\JDFAbstractState.java@@\main\45
.\jdflib\resource\devicecapability\JDFDevCap.java@@\main\40
.\jdflib\resource\devicecapability\JDFDevCaps.java@@\main\27
.\jdflib\resource\devicecapability\JDFDeviceCap.java@@\main\39
.\jdflib\resource\devicecapability\JDFEnumerationState.java@@\main\28
.\jdflib\resource\devicecapability\JDFNameState.java@@\main\29
.\jdflib\resource\devicecapability\JDFStringState.java@@\main\23
.\jdflib\resource\devicecapability\JDFTest.java@@\main\25
.\jdflib\resource\intent\JDFIntentResource.java@@\main\31
.\jdflib\resource\JDFDevice.java@@\main\8
.\jdflib\resource\JDFResource.java@@\main\204
.\jdflib\resource\JDFSignature.java@@\main\18

___________________________________________________________

Label JDFLIBJ_2.1.3BLD380 (24.11.2006)

Changed files:

ct find . -version "{lbtype(JDFLIBJ_2.1.3BLD380) && !lbtype(JDFLIBJ_2.1.3BLD040)}" -print
.\jdflib\auto\JDFAutoResourceQuParams.java@@\main\54
.\jdflib\core\DocumentJDFImpl.java@@\main\86
.\jdflib\core\JDFAudit.java@@\main\79
.\jdflib\core\JDFComment.java@@\main\26
.\jdflib\core\JDFCustomerInfo.java@@\main\39
.\jdflib\core\JDFCustomerMessage.java@@\main\6
.\jdflib\core\JDFDoc.java@@\main\68
.\jdflib\core\JDFDocumentBuilder.java@@\main\3
.\jdflib\core\JDFElement.java@@\main\223
.\jdflib\core\JDFNodeInfo.java@@\main\52
.\jdflib\core\JDFParser.java@@\main\38
.\jdflib\core\JDFPartAmount.java@@\main\27
.\jdflib\core\JDFPartStatus.java@@\main\23
.\jdflib\core\JDFRefElement.java@@\main\60
.\jdflib\core\JDFResourceLink.java@@\main\124
.\jdflib\core\JDFSeparationList.java@@\main\23
.\jdflib\core\JDFVersions.java@@\main\10
.\jdflib\core\KElement.java@@\main\227
.\jdflib\core\VResource.java@@\main\16
.\jdflib\core\VString.java@@\main\39
.\jdflib\core\XMLDoc.java@@\main\81
.\jdflib\core\XMLDocUserData.java@@\main\25
.\jdflib\core\XMLErrorHandler.java@@\main\4
.\jdflib\datatypes\JDFAttributeMap.java@@\main\32
.\jdflib\datatypes\JDFCMYKColor.java@@\main\6
.\jdflib\datatypes\JDFDateTimeRange.java@@\main\13
.\jdflib\datatypes\JDFDateTimeRangeList.java@@\main\11
.\jdflib\datatypes\JDFDurationRange.java@@\main\14
.\jdflib\datatypes\JDFDurationRangeList.java@@\main\11
.\jdflib\datatypes\JDFIntegerList.java@@\main\14
.\jdflib\datatypes\JDFIntegerRange.java@@\main\30
.\jdflib\datatypes\JDFIntegerRangeList.java@@\main\32
.\jdflib\datatypes\JDFLabColor.java@@\main\6
.\jdflib\datatypes\JDFMatrix.java@@\main\15
.\jdflib\datatypes\JDFNameRange.java@@\main\15
.\jdflib\datatypes\JDFNameRangeList.java@@\main\14
.\jdflib\datatypes\JDFNumberList.java@@\main\7
.\jdflib\datatypes\JDFNumberRange.java@@\main\23
.\jdflib\datatypes\JDFNumberRangeList.java@@\main\21
.\jdflib\datatypes\JDFNumList.java@@\main\28
.\jdflib\datatypes\JDFPath.java@@\main\18
.\jdflib\datatypes\JDFRange.java@@\main\17
.\jdflib\datatypes\JDFRangeList.java@@\main\18
.\jdflib\datatypes\JDFRectangle.java@@\main\18
.\jdflib\datatypes\JDFRectangleRange.java@@\main\11
.\jdflib\datatypes\JDFRectangleRangeList.java@@\main\11
.\jdflib\datatypes\JDFRGBColor.java@@\main\6
.\jdflib\datatypes\JDFShape.java@@\main\13
.\jdflib\datatypes\JDFShapeRange.java@@\main\11
.\jdflib\datatypes\JDFShapeRangeList.java@@\main\11
.\jdflib\datatypes\JDFTransferFunction.java@@\main\8
.\jdflib\datatypes\JDFXYPair.java@@\main\16
.\jdflib\datatypes\JDFXYPairRange.java@@\main\17
.\jdflib\datatypes\JDFXYPairRangeList.java@@\main\15
.\jdflib\datatypes\VJDFAttributeMap.java@@\main\22
.\jdflib\jmf\JDFAcknowledge.java@@\main\20
.\jdflib\jmf\JDFAdded.java@@\main\2
.\jdflib\jmf\JDFChangedPath.java@@\main\6
.\jdflib\jmf\JDFCommand.java@@\main\21
.\jdflib\jmf\JDFCreateLink.java@@\main\4
.\jdflib\jmf\JDFCreateResource.java@@\main\3
.\jdflib\jmf\JDFDeviceFilter.java@@\main\10
.\jdflib\jmf\JDFDeviceInfo.java@@\main\19
.\jdflib\jmf\JDFEmployeeDef.java@@\main\10
.\jdflib\jmf\JDFFlushedResources.java@@\main\7
.\jdflib\jmf\JDFFlushQueueInfo.java@@\main\3
.\jdflib\jmf\JDFFlushQueueParams.java@@\main\8
.\jdflib\jmf\JDFFlushResourceParams.java@@\main\8
.\jdflib\jmf\JDFGangCmdFilter.java@@\main\3
.\jdflib\jmf\JDFGangInfo.java@@\main\3
.\jdflib\jmf\JDFGangQuFilter.java@@\main\3
.\jdflib\jmf\JDFIDInfo.java@@\main\8
.\jdflib\jmf\JDFJDFController.java@@\main\10
.\jdflib\jmf\JDFJDFService.java@@\main\10
.\jdflib\jmf\JDFJMF.java@@\main\60
.\jdflib\jmf\JDFJobPhase.java@@\main\19
.\jdflib\jmf\JDFKnownMsgQuParams.java@@\main\10
.\jdflib\jmf\JDFMessage.java@@\main\65
.\jdflib\jmf\JDFMessageService.java@@\main\12
.\jdflib\jmf\JDFModifyNodeCmdParams.java@@\main\3
.\jdflib\jmf\JDFMoveResource.java@@\main\3
.\jdflib\jmf\JDFMsgFilter.java@@\main\16
.\jdflib\jmf\JDFNewComment.java@@\main\3
.\jdflib\jmf\JDFNewJDFCmdParams.java@@\main\7
.\jdflib\jmf\JDFNewJDFQuParams.java@@\main\8
.\jdflib\jmf\JDFNodeInfoCmdParams.java@@\main\13
.\jdflib\jmf\JDFNodeInfoQuParams.java@@\main\12
.\jdflib\jmf\JDFNodeInfoResp.java@@\main\13
.\jdflib\jmf\JDFNotificationDef.java@@\main\12
.\jdflib\jmf\JDFOccupation.java@@\main\16
.\jdflib\jmf\JDFPipeParams.java@@\main\16
.\jdflib\jmf\JDFQuery.java@@\main\20
.\jdflib\jmf\JDFQueue.java@@\main\19
.\jdflib\jmf\JDFQueueEntry.java@@\main\18
.\jdflib\jmf\JDFQueueEntryDef.java@@\main\12
.\jdflib\jmf\JDFQueueEntryPosParams.java@@\main\10
.\jdflib\jmf\JDFQueueEntryPriParams.java@@\main\13
.\jdflib\jmf\JDFQueueFilter.java@@\main\8
.\jdflib\jmf\JDFQueueSubmissionParams.java@@\main\10
.\jdflib\jmf\JDFRegistration.java@@\main\3
.\jdflib\jmf\JDFRemoveLink.java@@\main\3
.\jdflib\jmf\JDFRequestQueueEntryParams.java@@\main\12
.\jdflib\jmf\JDFResourceCmdParams.java@@\main\22
.\jdflib\jmf\JDFResourceInfo.java@@\main\28
.\jdflib\jmf\JDFResourcePullParams.java@@\main\13
.\jdflib\jmf\JDFResourceQuParams.java@@\main\17
.\jdflib\jmf\JDFResponse.java@@\main\27
.\jdflib\jmf\JDFResubmissionParams.java@@\main\10
.\jdflib\jmf\JDFReturnQueueEntryParams.java@@\main\7
.\jdflib\jmf\JDFShutDownCmdParams.java@@\main\8
.\jdflib\jmf\JDFSignal.java@@\main\21
.\jdflib\jmf\JDFStatusQuParams.java@@\main\16
.\jdflib\jmf\JDFStopPersChParams.java@@\main\16
.\jdflib\jmf\JDFSubmissionMethods.java@@\main\10
.\jdflib\jmf\JDFSubscription.java@@\main\10
.\jdflib\jmf\JDFTrackFilter.java@@\main\17
.\jdflib\jmf\JDFTrackResult.java@@\main\16
.\jdflib\jmf\JDFTrigger.java@@\main\10
.\jdflib\jmf\JDFUpdateJDFCmdParams.java@@\main\3
.\jdflib\jmf\JDFWakeUpCmdParams.java@@\main\8
.\jdflib\node\JDFAncestor.java@@\main\33
.\jdflib\node\JDFNode.java@@\main\232
.\jdflib\node\JDFSpawned.java@@\main\26
.\jdflib\node\VJDFNode.java@@\main\10
.\jdflib\pool\JDFAmountPool.java@@\main\26
.\jdflib\pool\JDFAncestorPool.java@@\main\45
.\jdflib\pool\JDFAuditPool.java@@\main\89
.\jdflib\pool\JDFPool.java@@\main\36
.\jdflib\pool\JDFPreflightConstraintsPool.java@@\main\22
.\jdflib\pool\JDFPreflightResultsPool.java@@\main\23
.\jdflib\pool\JDFResourceLinkPool.java@@\main\76
.\jdflib\pool\JDFResourcePool.java@@\main\68
.\jdflib\pool\JDFStatusPool.java@@\main\43
.\jdflib\resource\devicecapability\JDFAbstractState.java@@\main\44
.\jdflib\resource\devicecapability\JDFAction.java@@\main\15
.\jdflib\resource\devicecapability\JDFand.java@@\main\10
.\jdflib\resource\devicecapability\JDFBooleanEvaluation.java@@\main\15
.\jdflib\resource\devicecapability\JDFBooleanState.java@@\main\25
.\jdflib\resource\devicecapability\JDFDateTimeEvaluation.java@@\main\16
.\jdflib\resource\devicecapability\JDFDateTimeState.java@@\main\26
.\jdflib\resource\devicecapability\JDFDevCap.java@@\main\39
.\jdflib\resource\devicecapability\JDFDevCapPool.java@@\main\9
.\jdflib\resource\devicecapability\JDFDevCaps.java@@\main\26
.\jdflib\resource\devicecapability\JDFDeviceCap.java@@\main\38
.\jdflib\resource\devicecapability\JDFDurationEvaluation.java@@\main\13
.\jdflib\resource\devicecapability\JDFDurationState.java@@\main\24
.\jdflib\resource\devicecapability\JDFEnumerationEvaluation.java@@\main\15
.\jdflib\resource\devicecapability\JDFEnumerationState.java@@\main\27
.\jdflib\resource\devicecapability\JDFEvaluation.java@@\main\20
.\jdflib\resource\devicecapability\JDFIntegerEvaluation.java@@\main\15
.\jdflib\resource\devicecapability\JDFIntegerState.java@@\main\26
.\jdflib\resource\devicecapability\JDFMatrixEvaluation.java@@\main\17
.\jdflib\resource\devicecapability\JDFMatrixState.java@@\main\26
.\jdflib\resource\devicecapability\JDFModulePool.java@@\main\6
.\jdflib\resource\devicecapability\JDFNameEvaluation.java@@\main\16
.\jdflib\resource\devicecapability\JDFNameState.java@@\main\28
.\jdflib\resource\devicecapability\JDFNodeTerm.java@@\main\8
.\jdflib\resource\devicecapability\JDFnot.java@@\main\12
.\jdflib\resource\devicecapability\JDFNumberEvaluation.java@@\main\16
.\jdflib\resource\devicecapability\JDFNumberState.java@@\main\27
.\jdflib\resource\devicecapability\JDFor.java@@\main\11
.\jdflib\resource\devicecapability\JDFPDFPathEvaluation.java@@\main\12
.\jdflib\resource\devicecapability\JDFPDFPathState.java@@\main\23
.\jdflib\resource\devicecapability\JDFRectangleEvaluation.java@@\main\18
.\jdflib\resource\devicecapability\JDFRectangleState.java@@\main\29
.\jdflib\resource\devicecapability\JDFShapeEvaluation.java@@\main\15
.\jdflib\resource\devicecapability\JDFShapeState.java@@\main\28
.\jdflib\resource\devicecapability\JDFStringEvaluation.java@@\main\14
.\jdflib\resource\devicecapability\JDFStringState.java@@\main\22
.\jdflib\resource\devicecapability\JDFTerm.java@@\main\5
.\jdflib\resource\devicecapability\JDFTest.java@@\main\24
.\jdflib\resource\devicecapability\JDFTestPool.java@@\main\6
.\jdflib\resource\devicecapability\JDFTestRef.java@@\main\14
.\jdflib\resource\devicecapability\JDFxor.java@@\main\12
.\jdflib\resource\devicecapability\JDFXYPairEvaluation.java@@\main\17
.\jdflib\resource\devicecapability\JDFXYPairState.java@@\main\26
.\jdflib\resource\JDFAdhesiveBindingParams.java@@\main\8
.\jdflib\resource\JDFBand.java@@\main\17
.\jdflib\resource\JDFBarcode.java@@\main\7
.\jdflib\resource\JDFBindItem.java@@\main\10
.\jdflib\resource\JDFCuttingParams.java@@\main\14
.\jdflib\resource\JDFImageCompression.java@@\main\20
.\jdflib\resource\JDFMarkObject.java@@\main\22
.\jdflib\resource\JDFMerged.java@@\main\11
.\jdflib\resource\JDFNotification.java@@\main\16
.\jdflib\resource\JDFPart.java@@\main\26
.\jdflib\resource\JDFPhaseTime.java@@\main\27
.\jdflib\resource\JDFProcessRun.java@@\main\30
.\jdflib\resource\JDFResource.java@@\main\203
.\jdflib\resource\JDFResourceAudit.java@@\main\29
.\jdflib\resource\JDFSignature.java@@\main\17
.\jdflib\resource\JDFTool.java@@\main\11
.\jdflib\resource\process\JDFFileSpec.java@@\main\30
.\jdflib\resource\process\JDFRunList.java@@\main\52
.\jdflib\resource\process\JDFUsageCounter.java@@\main\2
.\jdflib\util\JDFDate.java@@\main\42
.\jdflib\util\JDFDuration.java@@\main\4
.\jdflib\util\JDFMerge.java@@\main\6
.\jdflib\util\JDFSpawn.java@@\main\8
.\jdflib\util\StringUtil.java@@\main\55
.\jdflib\util\UrlUtil.java@@\main\5
.\jdflib\util\XMLstrm.java@@\main\8

___________________________________________________________


Label JDFLIBJ_2.1.2BLD040 (07.11.2006)

___________________________________________________________

How to find in JIRA the JDFLib-J issues which were fixed in a specific version ?

1. http://www.cip4.org/jira/  (no account needed)
2. on the left side look for section "Project: JDFLib-J" and click on "Filter Issues: All"
3. click on column header "Fix Version/s" to get a list sorted by version number

___________________________________________________________

Label JDFLIBJ_2.1.2BLD033 (xx.03.2006)

JavaDoc comments in auto files : removed type information

other changes see JIRA
___________________________________________________________

Label JDFLIBJ_2.1.2BLD032 (30.03.2006)

fixes for 1.3 Schema (see errata page)

other changes see JIRA
___________________________________________________________

Label JDFLIBJ_2.1.2BLD031 (15.03.2006)

fixes for 1.3 Schema

other changes see JIRA
___________________________________________________________

Label JDFLIBJ_2.1.2BLD029 (08.02.2006)

JDFNumList
    added       public void scale (double factor)

JDFMedia
    implemented get/setDimensionCM(), get/setDimensionInch()

JDFElementTest
    changed     schema location from test/data/SampleFiles to test/Schema

auto files
    fixed       AssemblyIDs is now of type NMTOKENS
    fixed       element + attribute version
    changed     now JDFAutoGeneralID extends JDFElement (was JDFResource)

Schema
JDFCapability.xsd
    renamed     type="jdf:DevCap_ce" --> type="jdf:DevCap_lr"
    changed     type="jdf:DevCap_lr" used in DeviceCap_DevCaps_lr and in DevCapPool
    changed     annotation for DeviceCap_re, StringState_Value_lr, choice, when, otherwise, set

JDFCore.xsd
    fixed       GeneralID, SourceResource
    fixed       ICSVersions added in Ancestor
    changed     annotation for AncestorPool_, CustomerInfo_, StatusPool_, StatusPool_PartStatus_l,

JDFMessage.xsd
    fixed       NodeInfoQuParams_m, NodeInfoCmdParams_m
    changed     annotation for ModifyNodeCmdParams, RequestQueueEntryParams, QueueFilter

JDFProcess.xsd
    changed     annotation for ProcessInputResources_

JDFResource.xsd
    fixed       GeneralID, SourceResource
    fixed       StationName added in BinderySignature_SignatureCell_lr
    fixed       ImplicitGutterMinimumLimit added in LayoutPreparationParamsAttribs_c
    changed     annotation for Resource, Assembly, Assembly_AssemblySection_lr.
                BinderySignature, BoxPackingParams, ColorSpaceConversionOp_re,
                CreasingParams, CuttingParams, DeviceNSpace, GluingParams,
                LaminatingParams, LayoutPreparationParams, PageList,
                ShapeCuttingParams, StackingParams, StrappingParams,
                StrippingParams, Tool, DeliveryIntent, DeliveryIntent_Pricing_lr
    changed     NMTOKEN --> NMTOKENS in AssemblyAttribs_c, Assembly_AssemblySection_lr,
                Assembly_AssemblySection_AssemblySection_lr, ComponentAttribs_c,
                CutBlockAttribs_c, PageListAttribs_c, PageList_PageData_lr,
                StrippingParamsAttribs_c


___________________________________________________________

Label JDFLIBJ_2.1.2BLD028 (18.01.2006)

KElement, KElementTest
                new unit test testTextMethods() and corresponding bug fixes in KElement

JDFDeviceCapTest
    new         testDeviceCapIsValid()

JDFAbstractState
    renamed     JDFStateBase --> JDFAbstractState

JDFDoc + XMLDoc
    changed     toString()

JDFDate
    changed     JDFDate(String strDateTime) allows milliseconds 3.123 ...
                but getDateTimeISO() still returns the time rounded to full seconds
                only long getTimeInMillis() returns the exact time

StringUrils
    added       String formatInteger(int i) can return JDFConstants.POSINF and JDFConstants.NEGINF ("INF" / "-INF")
    fixed       int parseInt(String s, int def) allows for JDFConstants.POSINF and JDFConstants.NEGINF


auto files
    removed     deprecated function from 2004-08-26
                i.e. getXXXVector(JDFAttributeMap mAttrib, boolean bAnd) and getXXXVector()
    fixed       element version
    fixed       attribute version in ContentObject and MarkObject
    fixed       attribute type in ReturnQueueEntryParams
    fixed       generated getAttribute(), def = JDFConstants.EMPTYSTRING
    fixed       attribute type .Any --> EnumAttributeType.boolean_ / .double_
    added       attributes ContentData : CatalogID, CatalogDetails, ProductID
                           Ink : Family
                           MsgFilter : Family
                           PageData : CatalogID, CatalogDetails, ProductID
                           ResourceCmdParams : ProductID
                           ResourceInfo : ProductID
                           ResourceQuParams : ProductID

___________________________________________________________

Label JDFLIBJ_2.1.2BLD027 (14.12.2005)

auto files : e.init() removed, validClass(xxx) --> validClass()

unit test file paths are now relative to the project directory
(before they were relative to project directory/test/data)

DocumentJDFImpl returns as minimal type now KElement (was JDFElement) !!!

JDFResource
    duplicated  setStatus(), getStatus() and renamed them to setResStatus(), getResStatus(),
                the old functions are deprecated now
___________________________________________________________

Label JDFLIBJ_2.1.2BLD025 (03.11.2005)
___________________________________________________________

Label JDFLIBJ_2.1.2BLD024 (24.08.2005)

This label marks a brand new validation concept that uses version dependent validation.

In addition, many routines were modified to allow null as input parameters
instead of empty vectors, maps and strings.


KElement
    changed     validation has been replaced by new version dependent algorithms

AttributeInfo
    added       validation has been replaced by new version dependent algorithms

ElementInfo
    added       validation has been replaced by new version dependent algorithms

JDFElement
    changed     validation has been replaced by new version dependent algorithms

JDFNode
    changed     validation has been replaced by new version dependent algorithms
    added       eraseemptynodes now clears empty pools
    added       checkspawnresources - spawn checks whether any resources would be spawned rw multiply


VNode
    deprecated  use VElement and a cast of elementAt() to JDFNode

JDFDocumentBuilder
    added       added a document builder class for javax support

JDFAttributeMap
    added       added JDFAttributeMap(string key,string value) as a utility helper

JDFSelector
    removed     JDFSelector has been dead (really dead, not deprecated) since JDF 1.1

All Auto files
    removed     ValidNodeNames() is not used, since it is an artifact from C++,
                where type (up) casts are more widespread.
                The parser factory in the Java version implicitly defines the valid node names.
___________________________________________________________

Label JDFLIBJ_2.1.2BLD022 (19.07.2005)

VJDFNode
    changed     deprecated replaced by vector

JDFNode
    changed     get- append- getcreate- NodeInfo/CustomerInfo implementation for 1.3
                get/setPartStatus implementation for 1.3

JDFElement
    API addition    public void removeChildren(String nodeName, String nameSpaceURI, JDFAttributeMap mAttrib)

MyArgs
    bug fix     initMyArgs : file names containing '-', options with value

CheckJDFTest
    changed     now uses CheckJDF, removed redundancy

JDFNodeTest
    bug fix     file path corrected
___________________________________________________________

Label JDFLIBJ_2.1.2BLD021 (09.06.2005)

DocumentJDFImpl
    changed     ExtendedAddress does now map to JDFComment.
                The file JDFExtendedAddress is obsolet and got deleted
                OrganizationalUnit does now map to JDFComment.
                The file JDF OrganizationalUnit is obsolet and got deleted
    added       NodeName "PayTerm" as a valid Comment name
    added       NodeName "Range" as a valid Comment name
                SearchPath does now map to JDFComment. The file
                JDF SearchPath is obsolet and got deleted
JDFElement
    changed     getChildElementVector in JDFElement fgte auch dann REF Elemente dem Vector
                hinzu wenn diese eigentlich aufgellst werden sollten. Dies macht die Methode nun
                nicht mehr.

JDFNode
    changed     While spawning more then one partition only the first part element was set to status spawned.
                (Wrong method call)

JDFComment
    changed     New nodenames are allowed for a comment. These are
                ExtendedAddress, OfferRange, OrganizationalUnit, PayTerm,Range, SearchPath
                See corresponding changes in DocumentJDFImpl
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD020 (13.05.2005)

JDFElement
    changed     version() the Version method now searches for the inherited Version Info.
                If none is found the default 1.2 is returned.

JDFNodeInfo/Customerinfo
    changed     changed init() method to set ID and Status of the resource if it
                is a child of the resourcepool
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD019 (04.05.2005)

JDFElement
         deleted        includesAttributes(), now KElement. includesAttributes() is used
                 for ranges and rangelists use includesMatchingAttributes (as before)

KElement
         deleted        includesAttributes_KElement(), use KElement. includesAttributes()

JDFDate        removed dependency from GregorianCalendar (performance issue)
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD018 (22.04.2005)

JDFSpawned
    added        optionalAttributes, getInvalidAttributes, validStatus, setStatus, getStatus

JDFNode
         bug fix        addSpawnedResources back to Label JDFLIBJ_2.1.2BLD016
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD017 (14.04.2005)

Bug fixes and changes from C++ Label JDFPARSER_2.1.2BLD063 up to 11.4.05 (NodeInfo as Resource in Spec 1.3)

JDFElement
    added        setAttribute(a, xxx, b) for xxx = JDFNameRange, JDFNumberRangeList,
JDFIntegerRangeList, JDFXYPairRange, JDFXYPairRangeList
    added        getChildWithMatchingAttribute()

JDFNodeInfo
    added        init, requiredAttributes, optionalAttributes, getInvalidAttributes, optionalElements,
requiredElements, getInvalidElements

JDFResourceLink
         bug fix        hasResourcePartMap()
    added        expandTarget(boolean bForce)

KElement
         bug fix        setAttribute()  throw a JDFException if two setAttribute using the same prefix but a different
namespace are used
JDFNode
    added        boolean setPartStatus (VJDFAttributeMap vmattr, EnumNodeStatus status)
         bug fix        boolean setPartStatus (JDFAttributeMap vmattr, EnumNodeStatus status)
         bug fix        addSpawnedResources, spawn, unSpawnNode, mergeJDF

JDFResource
    added        createPartitions(VJDFAttributeMap vPartMap, VString vPartIDKeys)
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD016 (08.04.2005)

JDFQueue
         changed        private int m_EntryCount  deleted and replaced by getEntryCount(), incrementEntryCount()
deleted

JDFDate
         info        Java functions Date.before() and Calendar.before() work perhaps unexpectedly:
            calendar1.before(date1) returns always false (Calendar and Date cant be mixed)
            should JDFDate override and implement a mixed type version of before() et al. ???

JDFNode
         changed        isActive() : use EnumActivation.getEnum(s) instead of stringToActivation(s).
         changed        deprecated getActivation() : use EnumActivation.getEnum(getActivationString())
         bug fix        spawnInformative() : call rootOut.getChildJDFNode(getID(),false) recursively
         bug fix        NPEs regarding the ancestor pool


Several Changes to the Device Capabilities and to the validation routines to make them JDFSpec 1.3
compatible
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD015 (xxxx)

JDFNode
         changed        there was a NullpointerException if a resource for a Resourcelink wasnt there. We decided to
continue in this case. So in the best case your spawned prozess is still valid but is missing a
audit for example. In the worst case your spawned job is as broken then the main jdf.
KElement
         changed        changed isDirty and deprecated it. This method should not be called from outside anyway
         changed    changed getDeepParentNotName internaly from NodeName to LocalName to work with
private Namespaces
         changed    appendElement(xyz:abc) will throw a JDFException if the namespace was not set before using
it. In this example you would get an exception if the namespace xyz was not set in this
element or any other element above it (root is top).


XMLUserData
         changed            added a EnumDirtyPolicy with default none. IF you want to change the DirtyPolicy use
setDirtyPolicy(EnumDirtyPolicy dirtPol). Valid values are None/ID/Xpath
         changed            getDirtyIDs(), returns an empty VString if Policy is set to None (Default!)
         added            getDirtyXPaths() returns an empty VString if Policy is not set to XPath
         changed            setDirty(String id). The id get only added if the Politcy is set to ID
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD014 (02.03.2005)

Check with findbugs 0.8.6, lots of warnings removed, the rest is
<pic removed>

JDFNode
         added        added informativeSpawn zu JDFNode
         added    NodeInfo CustomerInfo are now Resources if the Node is a 1.3 Node. Otherwise the are
treated as always. The inheritance of both changed to JDFResource, beware of virtual method
calls.
         Info    Handling of NodeInfo-Elemente of Ancestor Nodes in the AncestorPool are not in yet.
         added    changed spawn to allow spawning of RO Resources

JDFResourceLink
         bug fix        changed method hasResourcePartMap to be more restrictive (proper way)

JDFResource
added    JDFResource.addPartition throws a JDFException if you try to add a partition to a
ResourceElement.

JDFBooleanEvaluation
bug fix    ValueListString() --> valueListString(), ValueListVector() --> valueListVector()

JDFStateBase
bug fix    ListTypeString() --> listTypeString()

JDFArtDelivery
bug fix    GetArtHandling() --> getArtHandling()

JDFDuration, Duration
Api change    public void setDurationISO(String a_aDuration)
       --> public boolean setDurationISO(String a_aDuration)

JDFNode
change    inlined init_JDFNode() into init() and deleted init_JDFNode()
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD013 (14.02.2005)

KElement
bug fix    fix for DOM Level 1 namespace bug in setAttribute(String key, String value, String
nameSpaceURI) (copied from C++)

JDFResourceLinkPool
bug fix    fix for DOM Level 1 namespace bug in appendResource ()

JDFElement
bug fix    fix for DOM Level 1 namespace bug in refElement ()


This DOM Level 1 namespace bug will get a more global treatment in BLD014
with changes in KElement. For now the known problems are fixed.
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD012 (08.02.2005)

KElement
bug fix    fix for DOM Level 1 namespace bug in appendElement ( the incorrect call
appendElement("HDM:newElem", "") inserted a wrong namespace
xmlns:HDM="http://www.CIP4.org/JDFSchema_1_1".This was used in Printready. Now it tries
to find a predefined namespace HDM and throws if no one can be found)
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD011 (21.01.2005)

The greatest part of the changes was validation induced - making validation work in JDFEditor and
including device capabilities

Auto classes
changed    various wrong return types (i.e. JDFSpanSpineGlue --> JDFSpanGlue,
JDFSpanBackCoverColor --> JDFSpanNamedColor ...)
    various signature changes concerning validation methods
    various methods of attributes of type Enumerations now return a vector instead of EnumXYZ
    (before every time the attribute had more than one entry the method returned Unknown, now a
vector of Integer is returned)
    various methods of attributes of type NMTOKENS now return a VString instead of String

DocumentJDFImpl
bug fix    several jdf elements get mapped to new classes
    (BackCoatings --> JDFSpanCoatings, BackCoverColor --> JDFSpanNamedColor, SpineGlue -
-> JDFSpanGlue ...)

JDFDoc
bug fix    getJDFRoot(), getJMFRoot()

JDFElement
       bug fix        refElement()
       changed    signature of xyRelation() contains 2 additional tolerance params

KElement
changed    appendElement(String elementName, String nameSpaceURI) creates a DOM level 2 Element
in the default namespace when no namespace is given (JDFConstants.NONAMESPACE)
xmlns="http://www.CIP4.org/JDFSchema_1_1"

XMLDoc
changed    initRoot and setRoot create a DOM level 2 Element in the default namespace when no
namespace is given (JDFConstants.NONAMESPACE)
xmlns="http://www.CIP4.org/JDFSchema_1_1"
    If a user manages to enter a DOM level 1 Element into the tree and serializes it with
XMLDoc.write2XXX it will contain a namespace xmlns=""

JDFDurationRange
       changed    uses new class JDFDuration instead of JDFDate

JDFPath
       added        new method public void transform(AffineTransform at)

JDFQueue
added    public JDFQueueEntry getEntry (String strQEntryID)
    public VString findQueueEntries (String strJobID, String strJobPartID,
                                            VJDFAttributeMap vamParts, EnumQueueEntryStatus status)

JDFQueueEntryDef
deleted    public void setQueueEntryID(String strID), already defined in JDFAutoQueueEntryDef

JDFResourceCmdParams
changed    public Vector getUnknownElements(boolean bIgnorePrivate, int nMax) additional Parameter


JDFResourceInfo
changed    public Vector getUnknownElements(boolean bIgnorePrivate, int nMax) additional Parameter

JDFNode
added    init() : bug fix for missing ID in the JDF nodes
bug fix    mergeJDF()
bug fix    cleanUpMerge()
bug fix    getVersion()
changed    getMaxVersion(). Returns enumeration value instead String.
deprecated    getCategeory(boolean bInherit). Use getCategeory () instead
deprecated    GetNamedFeatures(boolean bInherit). Use getNamedFeatures() instead
deprecated    getStatusDetails(boolean bInherit). Use getStatusDetails () instead
bug fix    getJobPartID(). Default version of getJobPartID(boolean bInherit)
added    getJobPartID(boolean bInherit)
added    getSpawnID(boolean bInherit)
changed    getTypes(), setTypes(), validTypes()  correction for Process Group Nodes
added    getTemplate()

JDFResourceLinkPool
changed    getUnknownElements(boolean bIgnorePrivate, int nMax) additional Parameter

JDFResourcePool
changed    getUnknownElements(boolean bIgnorePrivate, int nMax) additional Parameter

JDFResource
changed    isResourceElement()

JDFDate
deprecated    all methods that use a duration. Use new JDFDuration class instead. In the moment both of
JDFDate and JDFDuration are inherited from the Duration interface.
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD010 (08.10.2004)

API changes

    All packages were renamed from com.heidelberg.JDFLib to org.cip4.jdflib
    In eclipse  just do an "Organize imports" on the root of your workspace

    All classes start now with an uppercase letter, so vString -> VString and so on.

        The convenience Methods hasXXX() have been removed,
        use hasAttribute(xxx, JDFConstants.NONAMESPACE, false) instead


JDFElement
changed    getEnumerationsAttribute had a bug where enumerations attributes where stored as a String
and not as tokens. Due to that it was impossible to find the corresponding enumeration. This
may change the behaviour.


DocumentJDFImpl.java
changed        old : MediaColor generates span.JDFSpanMediaColor
        new : MediaColor generates span.JDFSpanNamedColor
        to be consistent with C++ and in accordance with JDFSpec 7.1.12 and A.3.3.2

KElement.java
changed        toString() and toXML() : added serial.setNamespaces(true)
        so that the serializing behaviour in C++ and Java is the same

deprecated    appendElementNS(), use appendElementN() instead (same name as in C++)

added        appendElement(String elementName) - without namespace argument
        getCreateElement(String nodeName) - without namespace argument
        getElement(String nodeName) - without namespace argument

renamed        getChildText(n) -> getText(n)

deleted deprecated    EnumValidationLevel.toString()
        getChildElementVector(5 args) -> use the 6 args version with ", true"
        getChildElementVector_KElement(5 args) -> use the 6 args version with ", true"
        setUserData(String key, Object data) -> use setUserData(String key, Object data, null)
        getParentSibling() -> use getDeepParent(parentNode, 0).getCreateElement(siblingNode,
                              JDFConstants.NONAMESPACE, 0)

JDFConstants.java
deleted        CONTACTTYPES_APPROVAL - no valid contact type

JDFElement.java
deleted        removeStatus()

JDFParser.java
deleted deprecated    parseFile(String strFile, boolean bValidate) -> use parseFile(String strFile)
        initParser(String documentClassName, boolean bValidate, ErrorHandler errorHandler,
String schemaLocation) -> use initParser(schemaLocation, documentClassName, errorHandler)

XMLDoc.java
changed        toXML(), write2Stream(), write2StreamStatic() : added serial.setNamespaces(true)
        so that the serializing behaviour in C++ and Java is the same

vString.java
bug fix        removeStrings(s)
added        removeStrings(s, nMax)

JDFNode.java
deleted deprecated    addProcessGroup_JDFNode(String prodName) ->
            use JDFNodeProcessGroup.addProcessGroup()
delete        public EnumType getEnumType() -> use EnumType.getEnum(getType())
delete        public void setEnumType(EnumType typ) -> use setType(typ.getName(), false)
delete        public EnumType enumTypeFromString(String typ) -> use getEnum(String typ)
delete        public vString typeVector() -> use vString(EnumType.getNamesVector())
delete        public boolean init() -> use init_JDFNode(true, null)

JDFResource.java
API Change    classString() -> resourceClassString()
deleted        removeUpdateID(), removeLocked(), removeSeparation(), hasSeparation()
deleted        hasUpdateID(), removeRun(), hasRun(), removePartIDKeys(), removePartIDKeys(),
deleted        hasPartIDKeys(), getPartRoot(), hasResourceWeight(), hasSpawnIDs(),
deleted        getResourceClassString(), hasResourceClass(), isPrepared(boolean bRecurseRefs)
deleted        setDraft(boolean bDraft), removePartUsage(), hasPartUsage(),hasResStatus()
deleted
deleted        EnumPipePartIDKey -> use EnumPartIDKey
deleted        pipePartIDKeyString(EnumPipePartIDKey value) -> use value.getName()
deleted        pipePartIDKeyString() -> use partIDKeyString()
        multiple returns reduced to one, removed continue, break
        added comments

span.JDFSpanMediaColor.java
deleted        see DocumentJDFImpl above

JDFDate.java
deleted        constructor JDFDate(int) -> use JDFDate(long)

JDFSpanMediaColorTest.java
added        new unit test
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD009 (10.09.2004)

KElement.java
added        public String getAttribute(String strLocalName)
        public String getAttribute_KElement(String strLocalName)
        public void setAttribute(String key, String value)


JDFElement.java
added        public String getAttribute(String strLocalName)
        public String getAttribute_JDFElement(String strLocalName)
        public String getAttribute_JDFElement(String attrib, String nameSpaceURI, String def)

JDFJMF.java
added        EnumJMFReturnCode from Appendix I

JDFNode.java
added        public boolean isSuccessor (JDFNode proc)
        public vString getResourceIDs (boolean isInput)
        public vJDFAttributeMap
             getExecutablePartitions (JDFResourceLink link, JDFResource res, boolean isEnforced)
        public JDFNode [] getProcessNodes ()
        public boolean isProcessNode ()

JDFResource.java
added        public boolean isPartitioningCompatible (JDFResource other)
        public boolean isPartitioningCompatible (vString vsPartitions)
        public boolean isSpawnAllowed (JDFAttributeMap amPartMap)
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD008 (31.08.2004)

Autoclasses
       changed    all getXXXVector methods are deprecated. Use getChildElementVector instead

Autoclasses
    changed    all autoclasses are based on schema 1.2-001

JDFAudit.java
       changed    getPhase() : getEnumNodeStatus -> getStatus

JDFElement.java
       removed    getEnumNodeStatus, setNodeStatus, getNodeStatus, getElementStatus
                           setElementStatus, nodeStatusString, getElementStatusString, setElementStatusString

JDFNode.java
       removed    setStatusString, setStatus, setNodeStatus, getStatusString, getStatus
       changed    used setStatus/getStatus instead
       bug fix        mergeJDF, mergeStatusPool

JDFAuditPool.java
       changed    setPhase() : getEnumNodeStatus -> getStatus

JDFStatusPool.java
       removed    getElementStatus. setElementStatus used instead getStatus, setStatus

JDFPhaseTime.java
       changed    validStatus() : getEnumNodeStatus -> getStatus

JDFProcessRun.java
       changed    validEndStatus () : getEnumNodeStatus -> getStatus

JDFResource.java
       changed    getDeepPart() : semantics before : returned always a leaf, now returns a leaf or a node

KElementTest.java
       added        testNameSpace

JDFResourceTest.java
       changed    testgetPartMap() to reflect semantic change in JDFResource.getDeepPart()

JDFDate.java
       changed    Defaultconstructor use current time instead of 1970 always
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD007 (16.08.2004)

AttributeName.java
    changed    REF = "ref", the attribute starts with lower case "r", see JDFConstants.java for "Ref"

DocumentJDFImpl.java
    changed    getFactoryClass() has a second parameter namespaceURI and handles elements in
       namespace JDFNAMESPACE = "http://www.CIP4.org/JDFSchema_1_1" correctly
       Context sensitive element names (HoleType, Method, Shape, Surface) are handled
       correctly (see putConstructorToHashMap() and handleOtherElements())

JDFConstants.java
    changed    REF = "Ref", for parts of a name, see AttributeName for "ref"

KElement.java
    added        appendElementN()

JDFDoc.java
    changed    getJDFDoc() returned the the nodename JDF even if in wrong namespace.At the same
            time it returned null for the correct jdf:JDF with jdf: = http://www.CIP4.org/JDFSchema_1_1
getJDFDoc() does now look for the lokal name (everything past the first colon if there is a
colon) and checks for valid jdf namespaceURI.

XMLDoc.java
       added        write2URL()


resource.intent.JDFMediaColor.java
    removed


span.JDFSpanMediaColor.java
    added


StringUtil.java
    added        escape and unescape. You must spezify which characters you wish to escape, which radix
            and seperator you want to use. Binary radix (2) and an ? would be encoded as 11010110

DocumentJDFImplTest.java
    added        test for recognition heuristic of context sensitive elements
________________________________________________________________________________

Label JDFLIBJ_2.1.2BLD006 (30.06.2004)

Changes C++ Label 043 ---> 053


JDFElement.java
    new         EnumSeparation
    new         boolean XYRelation(EnumXYRelation value, double x, double y)
    new        boolean XYRelation(EnumXYRelation value, double x, double y)
       update        public boolean validAttribute(String key, EnumAttributeType iType,
          boolean bRequired, String nameSpaceURI)
       delete        public boolean validAttribute(String key, int iType,
          boolean bRequired, String nameSpaceURI)
       changed    changed length limit of uniqueID(int id) to 15 (old 12)

KElement.java
    update        removeAttributes : setDirty() added
    API change    moveElement : added second parameter beforeChild
    new        getNextSiblingElement

JDFNode.java
    delete        public boolean removeProcess(Vector vp, boolean bLeaveSubmit)
    delete        public boolean removeProcess(JDFNode p, boolean bLeaveSubmit)

MyDate.java  --> JDFDate.java
    removed     obsolete class JDFDate, renamed MyDate --> JDFDate

JDFDate.java

       note        to create a Date from a JDFDate use the inherited method getTime()
       note        to create a JDFDate from a Date, create a default JDFDate() and use the inherited
               method setTime(Date)
warning    The JDFDate after() method is not equal to the new one inherited from Calendar.
The new inherited after() method expects an Object.
So your compiler may not fail but it will return false always!
The new after() method expects a Calendar to work right.
See how to create a Calendar/JDFDate from a Date above.
       changed    toLocalString is deprecated since 1.1 if you still used it use the class DateFormat
               Example: DateFormat.getInstance().format(date);
               Please refer to DataFormat Java documentation for the formats available.
       changed    JDFDate extends GregorianCalendar no longer Date !!!
changed    Constructor JDFDate(int iOffset) expectes iOffset in seconds. This was always the case, just
the comment was wrong.
changed    the Constructor JDFDate(String strDateTime) throws a DataFormatException if strDateTime is
an invalid String. Valid Strings are
        "yyyy-mm-ddThh:mm:ss+hh:00"
        "yyyy-mm-ddThh:mm:ss-hh:00"

added        "yyyy-mm-ddThh:mm:ssZ" as valid parameter
added         Duration support. JDFDate(String strDateTime) now also accepts duration strings of form
"P1Y2M3DT10H30M"
                "PM8T12M"
API change    dateTime()  changed to getDateTime()
API change    durationISO() changed to getDurationISO()
API change    DateTimeISO changed to getDateTimeISO()
API change    dateISO() changed to getDateISO()
API change     timeISO() changed to getTimeISO()
API change    isoTimeZone() changed to getTimeZoneISO()
added        equals(JDFDate x)  Two JDFDates are equal if they specify the same moment in time.
14:00:00+03:00 is equal to 13:00:00:+02:00 because they descripe the same moment in time.
API change    changed setDurationString(String s) to setDurationISO(String s)

JDFParser.java
    API change    initParser(schemaLocation, documentClassName, errorHandler)
    API change    parseFile(String strFile)
    API change    parseString(String stringInput)
    API change    parseStream(InputStream inStream)
API change    parseInputSource(InputSource inSource,
                 String schemaLocation,
                 String documentClassName,
                 ErrorHandler errorHandler,
                 boolean bEraseEmpty,
                 boolean bDoNamespaces)
    API change


XMLDoc.java
new    all write methods got setPreserveSpace(). Carriage Returns in comments will no longer
converted to spaces.
    new        write2URL. Untested! Do not use

JDFDurationRange. java
    API change    isEquals(String s) throws DataFormatException if s is an invalid DurationRange

JDFResource.java
    changed    fixed a couple of bugs in makeRootResource()

JDFConstants.java
added    CONTACTTYPES_SENDER, CONTACTTYPES_DELIVERYCHARGE,
CONTACTTYPES_APPROVER
    set CONTACTTYPES_APPROVAL to deprecated
________________________________________________________________________________

Changes from xxx to JDFLIBJ_2.1.2BLD006 see above

JDFAudit.java
    AgentVersion = JDFLibVersion new global variable JDFLibVersion
________________________________________________________________________________

