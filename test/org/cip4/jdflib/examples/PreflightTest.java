/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoAction.EnumSeverity;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.auto.JDFAutoBoxArgument.EnumBox;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.JDFXYPairRange;
import org.cip4.jdflib.datatypes.JDFXYPairRangeList;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.resource.devicecapability.JDFAction;
import org.cip4.jdflib.resource.devicecapability.JDFActionPool;
import org.cip4.jdflib.resource.devicecapability.JDFBasicPreflightTest;
import org.cip4.jdflib.resource.devicecapability.JDFBooleanEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFEnumerationEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFIntegerEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFNumberEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFRectangleEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFStringEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFXYPairEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFnot;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.resource.process.JDFPRItem;
import org.cip4.jdflib.resource.process.JDFPreflightParams;
import org.cip4.jdflib.resource.process.JDFPreflightReport;
import org.cip4.jdflib.resource.process.JDFPreflightReportRulePool;
import org.cip4.jdflib.resource.process.JDFRunList;


public class PreflightTest extends JDFTestCaseBase
{
    protected JDFActionPool aPool;
    protected JDFNode n;
    protected JDFPreflightParams preparms;
    protected JDFPreflightReportRulePool prrp;
    protected JDFRunList inRun;
    
    /* (non-Javadoc)
     * @see org.cip4.jdflib.JDFTestCaseBase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        JDFElement.setLongID(false);
    }

    
    public void testPreflightReport() throws Exception
    {
        testPreflightProfile();
        JDFPreflightReport prp=(JDFPreflightReport) n.appendMatchingResource(ElementName.PREFLIGHTREPORT,EnumProcessUsage.AnyOutput,null);
        prp.refElement(preparms);
        prp.refElement(inRun);
        prp.refElement(prrp);
       
        JDFAttributeMap prMap=new JDFAttributeMap();
        VString groupBy=new VString();
        
        prMap.put("CompressionTypes","JPEG");
        prMap.put("PageNumber","2");
        groupBy.add("PageNumber");
        JDFPRItem pi=prp.setPR(aPool.getAction(0), 2, prMap, groupBy);
        assertNotNull(pi);
        assertEquals(pi.getOccurrences(), 1);
        prMap.put("CompressionTypes","JPEG2000");
        JDFPRItem pi2=prp.setPR(aPool.getAction(0), 2, prMap, groupBy);
        assertNotNull(pi2);
        assertEquals(pi2.getOccurrences(), 2);
        assertEquals(pi, pi2);
        prMap.put("PageNumber","3");
        JDFPRItem pi3=prp.setPR(aPool.getAction(0), 3, prMap, groupBy);
        assertNotNull(pi3);
        assertEquals(pi3.getOccurrences(), 3);
        assertEquals(pi, pi3);
        assertEquals(pi.getPageSet(), new JDFIntegerRangeList("2 ~ 3"));
        JDFPRItem pi4 = prp.setPR(aPool.getAction(1), 3, null, null);
        assertEquals(pi4.getOccurrences(), 1);
        assertNotSame(pi, pi4);
        assertEquals(pi4.getPageSet(), new JDFIntegerRangeList("3"));
       
        assertEquals(prp.numChildElements(ElementName.PRITEM, null), 2);
        n.getOwnerDocument_KElement().write2File(sm_dirTestDataTemp+File.separator+"PreflightReport.jdf",2,false);
   }
    
    
    /**
     * test preflight profile
     * @return
     */
    public void testPreflightProfile()
    {
        
         JDFDoc d=new JDFDoc("JDF");
        n=d.getJDFRoot();
        n.setType(EnumType.Preflight);
        
        inRun=(JDFRunList)n.appendMatchingResource(ElementName.RUNLIST,EnumProcessUsage.AnyInput,null);
        inRun.setFileURL("input.pdf");
        
        preparms=(JDFPreflightParams) n.appendMatchingResource(ElementName.PREFLIGHTPARAMS,EnumProcessUsage.AnyInput,null);
        prrp=(JDFPreflightReportRulePool) n.appendMatchingResource(ElementName.PREFLIGHTREPORTRULEPOOL,EnumProcessUsage.AnyInput,null);
        
        // new
        aPool=(JDFActionPool) preparms.appendElement(ElementName.ACTIONPOOL);
        
        
        // now some simple tests...
        appendTransparency();
        appendNumPagesAction();
        appendSeparationAction();
        appendBWSeparationAction();
        appendTrimBoxAction();
        appendResolutionAction();
        appendResolutionActionBitMap();
        appendLineWeightAction();
        
        d.write2File(sm_dirTestDataTemp+File.separator+"PreflightProfile.jdf",2,false);
        
        
    }
    protected void appendResolutionActionBitMap()
    {
        {
            JDFAction a=aPool.appendActionSetTest(EnumTerm.XYPairEvaluation,EnumTerm.IntegerEvaluation,true);
            a.setSeverity(EnumSeverity.Warning);
            JDFXYPairEvaluation resolution=(JDFXYPairEvaluation) a.getTestTerm();
            resolution.appendBasicPreflightTest("EffectiveResolution");
            resolution.setValueList(new JDFXYPairRangeList(new JDFXYPairRange(new JDFXYPair(0,0),new JDFXYPair(1200, 1200))));
            JDFEvaluation setEval=(JDFEvaluation) a.getPreflightActionSetTerm();
            setEval.appendBasicPreflightTest("BitsPerSample");
            
            a.setDescriptiveName("Warn when effective resolution<1200 dpi");
        }
    }
    /////////////////////////////////////////////////////////////////////////  
   /////////////////////////////////////////////////////////////////////////  
    
    protected void appendResolutionAction()
    {
        {
            JDFAction a=aPool.appendActionTest(EnumTerm.XYPairEvaluation,true);
            a.setSeverity(EnumSeverity.Warning);
            JDFXYPairEvaluation resolution=(JDFXYPairEvaluation) a.getTestTerm();
            resolution.appendBasicPreflightTest("EffectiveResolution");
            resolution.setValueList(new JDFXYPairRangeList(new JDFXYPairRange(new JDFXYPair(0,0),new JDFXYPair(300, 300))));
            
            a.setDescriptiveName("Warn when effective resolution<300 dpi");
        }
    }
    /////////////////////////////////////////////////////////////////////////  
    
    protected void appendTransparency()
    {
        {
            JDFAction a=aPool.appendActionTest(EnumTerm.BooleanEvaluation,true);
            a.setSeverity(EnumSeverity.Error);
            JDFBooleanEvaluation transparency=(JDFBooleanEvaluation) a.getTestTerm();
            transparency.appendBasicPreflightTest("TransparencyFlag");
            transparency.setValueList(false);
            
            a.setDescriptiveName("Error when objects with transparency exist");
        }
    }
    
/////////////////////////////////////////////////////////////////////////  
    protected void appendTrimBoxAction()
    {
        {
            JDFAction a=aPool.appendActionSetTest(EnumTerm.RectangleEvaluation,EnumTerm.EnumerationEvaluation,false);
            a.setSeverity(EnumSeverity.Error);
            JDFRectangleEvaluation trimBox=(JDFRectangleEvaluation) ((JDFnot)a.getTestTerm()).getTerm(null,0);
            trimBox.appendBasicPreflightTest("PageBoxSize");
            trimBox.setValueList(new JDFRectangle(0,0,8.5*72,11*72));
            JDFEnumerationEvaluation setEval=(JDFEnumerationEvaluation) a.getPreflightActionSetTerm();
            setEval.appendBasicPreflightTest("PageBoxName");
            setEval.setValueList(new VString(EnumBox.TrimBox.getName()," "));
            
            a.setDescriptiveName("set TrimBox to 8.5*11 Method 2");
        }
    }
/////////////////////////////////////////////////////////////////////////  
    protected void appendLineWeightAction()
    {
        JDFAction a=aPool.appendActionTest(EnumTerm.NumberEvaluation,true);
        a.setSeverity(EnumSeverity.Error);
        JDFNumberEvaluation hairLine=(JDFNumberEvaluation) a.getTestTerm();
        hairLine.setValueList(new JDFNumberRangeList(new JDFNumberRange(0.0,0.216)));
        hairLine.appendBasicPreflightTest("StrokeThickness");

        a.setDescriptiveName("set minimum stroke width to 0.216");
    }
    /////////////////////////////////////////////////////////////////////////  
    protected void appendBWSeparationAction()
    {
        JDFAction a=aPool.appendActionSetTest(EnumTerm.StringEvaluation,EnumTerm.IntegerEvaluation,false);
        a.setSeverity(EnumSeverity.Error);

        JDFStringEvaluation numSeparations=(JDFStringEvaluation) ((JDFnot)a.getTestTerm()).getTerm(null,0);
        numSeparations.appendBasicPreflightTest("SeparationList");
        a.setDescriptiveName("separation to black only on page 1 and 2");
        numSeparations.appendValueValue("Black");

        JDFIntegerEvaluation setEval=(JDFIntegerEvaluation) a.getPreflightActionSetTerm();
        setEval.appendBasicPreflightTest("PageNumber");
        setEval.appendValueList(1);
        setEval.appendValueList(2);
     }
    
    /////////////////////////////////////////////////////////////////////////  
    /////////////////////////////////////////////////////////////////////////  
    protected void appendNumPagesAction()
    {
        {
            JDFAction a=aPool.appendActionTest(EnumTerm.IntegerEvaluation,false);
            a.setSeverity(EnumSeverity.Error);
            JDFIntegerEvaluation numPages=(JDFIntegerEvaluation) ((JDFnot)a.getTestTerm()).getTerm(null, 0);
            numPages.appendBasicPreflightTest("NumberOfPages");
            numPages.appendValueList(4);
            a.setDescriptiveName("set number of pages to 4");
        }
    }

    
    /////////////////////////////////////////////////////////////////////////  
    protected void appendSeparationAction()
    {
        {
            JDFAction a=aPool.appendActionSetTest(EnumTerm.StringEvaluation,EnumTerm.IntegerEvaluation,false);
            a.setSeverity(EnumSeverity.Error);
            
            JDFStringEvaluation numSeparations=(JDFStringEvaluation) ((JDFnot)a.getTestTerm()).getTerm(null,0);
            JDFBasicPreflightTest testSeps=numSeparations.appendBasicPreflightTest("SeparationList");
            a.setDescriptiveName("set number of separations to 6 on page 0 and 3");
            testSeps.setMinOccurs(6);
            testSeps.setMaxOccurs(6);
            testSeps.setListType(EnumListType.UniqueList);
            
            JDFIntegerEvaluation setEval=(JDFIntegerEvaluation) a.getPreflightActionSetTerm();
            setEval.appendBasicPreflightTest("PageNumber");
            setEval.appendValueList(0);
            setEval.appendValueList(3);
        }
    }
    /////////////////////////////////////////////////////////////////////////  
}
