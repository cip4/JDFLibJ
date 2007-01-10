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
    changed     getChildElementVector in JDFElement fügte auch dann REF Elemente dem Vector
                hinzu wenn diese eigentlich aufgelöst werden sollten. Dies macht die Methode nun
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
         bug fix        setAttribute() – throw a JDFException if two setAttribute using the same prefix but a different 
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
            calendar1.before(date1) returns always false (Calendar and Date can’t be mixed)
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
         changed        there was a NullpointerException if a resource for a Resourcelink wasn’t there. We decided to 
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
bug fix    fix for DOM Level 1 namespace bug in appendResource (…) 

JDFElement 
bug fix    fix for DOM Level 1 namespace bug in refElement (…) 


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
changed    getTypes(), setTypes(), validTypes() – correction for Process Group Nodes
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
    changed    getJDFDoc() returned the the nodename ‘JDF’ even if in wrong namespace.At the same
            time it returned null for the correct ‘jdf:JDF’ with jdf: = http://www.CIP4.org/JDFSchema_1_1
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
            and seperator you want to use. Binary radix (2) and an ‘Ö’ would be encoded as 11010110

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

