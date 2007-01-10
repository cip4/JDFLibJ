/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.core;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.auto.JDFAutoAction.EnumSeverity;
import org.cip4.jdflib.auto.JDFAutoBasicPreflightTest.EnumListType;
import org.cip4.jdflib.auto.JDFAutoBoxArgument.EnumBox;
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
import org.cip4.jdflib.resource.devicecapability.JDFEnumerationEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFIntegerEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFRectangleEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFStringEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFXYPairEvaluation;
import org.cip4.jdflib.resource.devicecapability.JDFTerm.EnumTerm;
import org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams;
import org.cip4.jdflib.resource.process.JDFRunList;


public class ContentCreationTest extends JDFTestCaseBase
{
    /**
     * test iteration
     * @return
     */
    public void testLayoutPreflight()
    {
        JDFElement.setLongID(false);
        JDFDoc d=new JDFDoc("JDF");
        JDFNode n=d.getJDFRoot();
        n.setType(EnumType.LayoutElementProduction);
        
        JDFRunList outRun=(JDFRunList)n.appendMatchingResource(ElementName.RUNLIST,EnumProcessUsage.AnyOutput,null);
        outRun.setFileURL("output.pdf");
        
        JDFLayoutElementProductionParams lep=(JDFLayoutElementProductionParams) n.appendMatchingResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS,EnumProcessUsage.AnyInput,null);
        
        // new
        JDFActionPool aPool=(JDFActionPool) lep.appendElement(ElementName.ACTIONPOOL);
        
        // now some simple tests...
        appendNumPagesAction(aPool);
        appendSeparationAction(aPool);
        appendBWSeparationAction(aPool);
        appendTrimBoxAction(aPool);
        appendResolutionAction(aPool);
        
        d.write2File(sm_dirTestDataTemp+fileSeparator+"LayoutPreflight.jdf",2,false);
        
        
    }
    /////////////////////////////////////////////////////////////////////////  
    
    private void appendResolutionAction(JDFActionPool aPool)
    {
        {
            JDFAction a=aPool.appendActionTest(EnumTerm.XYPairEvaluation);
            a.setSeverity(EnumSeverity.Warning);
            JDFXYPairEvaluation resolution=(JDFXYPairEvaluation) a.getTestTerm();
            JDFBasicPreflightTest testresolution=resolution.appendBasicPreflightTest("EffectiveResolution");
            resolution.setValueList(new JDFXYPairRangeList(new JDFXYPairRange(new JDFXYPair(300,300),new JDFXYPair(Double.MAX_VALUE,Double.MAX_VALUE))));
            
            a.setDescriptiveName("Warn when effective resolution<300 dpi");
        }
    }
    
    /////////////////////////////////////////////////////////////////////////  
    private void appendTrimBoxAction(JDFActionPool aPool)
    {
        {
            JDFAction a=aPool.appendActionSetTest(EnumTerm.RectangleEvaluation,EnumTerm.EnumerationEvaluation);
            a.setSeverity(EnumSeverity.Error);
            JDFRectangleEvaluation trimBox=(JDFRectangleEvaluation) a.getTestTerm();
            JDFBasicPreflightTest testTrimBox=trimBox.appendBasicPreflightTest("PageBoxSize");
            trimBox.setValueList(new JDFRectangle(0,0,8.5*72,11*72));
            JDFEnumerationEvaluation setEval=(JDFEnumerationEvaluation) a.getPreflightActionSetTerm();
            JDFBasicPreflightTest setBox=setEval.appendBasicPreflightTest("PageBoxName");
            setEval.setValueList(new VString(EnumBox.TrimBox.getName()," "));
            
            a.setDescriptiveName("set TrimBox to 8.5*11 Method 2");
        }
    }
    
    /////////////////////////////////////////////////////////////////////////  
    private void appendBWSeparationAction(JDFActionPool aPool)
    {
        {
            JDFAction a=aPool.appendActionSetTest(EnumTerm.StringEvaluation,EnumTerm.IntegerEvaluation);
            a.setSeverity(EnumSeverity.Error);
            
            JDFStringEvaluation numSeparations=(JDFStringEvaluation) a.getTestTerm();
            JDFBasicPreflightTest testSeps=numSeparations.appendBasicPreflightTest("SeparationList");
            a.setDescriptiveName("separation to black only on page 1 and 2");
            numSeparations.appendValueValue("Black");
            
            JDFIntegerEvaluation setEval=(JDFIntegerEvaluation) a.getPreflightActionSetTerm();
            JDFBasicPreflightTest setPages=setEval.appendBasicPreflightTest("PageNumber");
            setEval.appendValueList(1);
            setEval.appendValueList(2);
        }
    }
    
    /////////////////////////////////////////////////////////////////////////  
    private void appendNumPagesAction(JDFActionPool aPool)
    {
        {
            JDFAction a=aPool.appendActionTest(EnumTerm.IntegerEvaluation);
            a.setSeverity(EnumSeverity.Error);
            JDFIntegerEvaluation numPages=(JDFIntegerEvaluation) a.getTestTerm();
            JDFBasicPreflightTest testNumPages=numPages.appendBasicPreflightTest("NumberOfPages");
            numPages.appendValueList(4);
            a.setDescriptiveName("set number of pages to 4");
        }
    }
    
    /////////////////////////////////////////////////////////////////////////  
    private void appendSeparationAction(JDFActionPool aPool)
    {
        {
            JDFAction a=aPool.appendActionSetTest(EnumTerm.StringEvaluation,EnumTerm.IntegerEvaluation);
            a.setSeverity(EnumSeverity.Error);
            
            JDFStringEvaluation numSeparations=(JDFStringEvaluation) a.getTestTerm();
            JDFBasicPreflightTest testSeps=numSeparations.appendBasicPreflightTest("SeparationList");
            a.setDescriptiveName("set number of separations to 6 on page 0 and 3");
            testSeps.setMinOccurs(6);
            testSeps.setMaxOccurs(6);
            testSeps.setListType(EnumListType.UniqueList);
            
            JDFIntegerEvaluation setEval=(JDFIntegerEvaluation) a.getPreflightActionSetTerm();
            JDFBasicPreflightTest setPages=setEval.appendBasicPreflightTest("PageNumber");
            setEval.appendValueList(0);
            setEval.appendValueList(3);
        }
    }
    /////////////////////////////////////////////////////////////////////////  
}
