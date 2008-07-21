/*
 * JDFExampleDocTest.java
 * 
 * @author muchadie
 */
package org.cip4.jdflib.examples;

import java.io.File;

import org.cip4.jdflib.JDFTestCaseBase;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.resource.JDFPhaseTime;
import org.cip4.jdflib.resource.JDFResourceAudit;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.resource.process.JDFApprovalParams;
import org.cip4.jdflib.resource.process.JDFApprovalSuccess;
import org.cip4.jdflib.resource.process.JDFLayoutElementProductionParams;
import org.cip4.jdflib.resource.process.JDFRunList;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;


public class IterationTest extends JDFTestCaseBase
{
    private static final String ITERATION_PAUSED = "IterationPaused";
    private JDFLayoutElementProductionParams iterLepp;
    private JDFApprovalParams iterApp;
    private JDFRunList iterRuli;
    private JDFRunList inRuli;
    private JDFNode iterNode;
    private JDFDoc iterDoc;
    private JDFApprovalSuccess iterAppSuccess;
    
    ///////////////////////////////////////////////////////////////////
    /**
     * test iteration
     * @return
     */
    public void testIterate()
    {
        JDFElement.setLongID(false);

        iterateSetup();
        iterateFirst();
        iterateSecond();
        iterateThird();
        iterateForth();
        iterateLast();
    }
    
    ///////////////////////////////////////////////////////////////////
    
    private void iterateSetup()
    {
        iterDoc = new JDFDoc("JDF");
        iterNode = iterDoc.getJDFRoot();
        iterNode.setCombined(new VString("LayoutElementProduction Approval"," "));
        iterNode.setStatus(EnumNodeStatus.Waiting);
        iterLepp = (JDFLayoutElementProductionParams) iterNode.appendMatchingResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS,EnumProcessUsage.AnyInput,null);
        iterLepp.setResStatus(EnumResStatus.Available,true);
        iterApp = (JDFApprovalParams) iterNode.appendMatchingResource(ElementName.APPROVALPARAMS,EnumProcessUsage.AnyInput,null);
        iterApp.setResStatus(EnumResStatus.Available,true);
        iterAppSuccess = (JDFApprovalSuccess) iterNode.appendMatchingResource(ElementName.APPROVALSUCCESS,EnumProcessUsage.AnyInput,null);
        iterAppSuccess.setResStatus(EnumResStatus.Unavailable,true);
        iterRuli = (JDFRunList) iterNode.appendMatchingResource(ElementName.RUNLIST,EnumProcessUsage.AnyOutput,null);
        iterRuli.setResStatus(EnumResStatus.Unavailable,true);
        inRuli = (JDFRunList) iterNode.addResource(ElementName.RUNLIST,null, EnumUsage.Input,null,null,null,null);
        inRuli.setResStatus(EnumResStatus.Available,true);
        
        iterRuli.addPDF(StringUtil.uncToUrl("C:\\local\\Myinput.pdf",true), 0, 3);
		iterRuli.setDescriptiveName("save in place - input equals output");
		inRuli.addPDF(StringUtil.uncToUrl("C:\\local\\Myinput.pdf",true), 0, 3);
		inRuli.addPDF(StringUtil.uncToUrl("C:\\local\\Image1.pdf",true), 0, 0);
		inRuli.addPDF(StringUtil.uncToUrl("C:\\local\\Image2.pdf",true), 0, 0);
        iterDoc.write2File(getIteration(0),2,false);
     }
    
///////////////////////////////////////////////////////////////////
    
    private void iterateRead(int i)
    {
        iterDoc=JDFDoc.parseFile(getIteration(i));
        assertNotNull(iterDoc);
        iterNode=iterDoc.getJDFRoot();
        assertNotNull(iterNode);
        iterLepp = (JDFLayoutElementProductionParams) iterNode.getMatchingResource(ElementName.LAYOUTELEMENTPRODUCTIONPARAMS,EnumProcessUsage.AnyInput,null,0);
        assertNotNull(iterLepp);
        iterApp = (JDFApprovalParams) iterNode.getMatchingResource(ElementName.APPROVALPARAMS,EnumProcessUsage.AnyInput,null,0);
        assertNotNull(iterApp);
        iterAppSuccess = (JDFApprovalSuccess) iterNode.getMatchingResource(ElementName.APPROVALSUCCESS,EnumProcessUsage.AnyInput,null,0);
        assertNotNull(iterAppSuccess);
        iterRuli = (JDFRunList) iterNode.getMatchingResource(ElementName.RUNLIST,EnumProcessUsage.AnyOutput,null,0);
        assertNotNull(iterRuli);
        
    }
    
///////////////////////////////////////////////////////////////////
    
    private void iterateFirst()
    {
        iterateRead(0);
        JDFPhaseTime pt=iterNode.getAuditPool().setPhase(EnumNodeStatus.InProgress, "First Iteration Ongoing", null,null);
        pt.setStart(new JDFDate(System.currentTimeMillis()-100000));
        pt.setEnd(new JDFDate(System.currentTimeMillis()));
        pt.appendEmployee().setPersonalID("Employee 1");
        pt.appendDevice().setDeviceID("Device 1");
        iterRuli.setResStatus(EnumResStatus.Draft,false);
        iterNode.setStatus(EnumNodeStatus.Suspended);
        iterNode.setAttribute("StatusDetails",ITERATION_PAUSED);
        iterDoc.write2File(getIteration(1),2,false);
    }
    
///////////////////////////////////////////////////////////////////
    
    private void iterateSecond()
    {
        iterateRead(1);
        JDFPhaseTime pt=iterNode.getAuditPool().setPhase(EnumNodeStatus.InProgress, "First Approval Ongoing", null,null);
        pt.setStart(new JDFDate(System.currentTimeMillis()+1000000));
        pt.setEnd(new JDFDate(System.currentTimeMillis()+1100000));
        pt.appendEmployee().setPersonalID("Employee 2");
        pt.appendDevice().setDeviceID("Approval Device 1");
        iterRuli.setResStatus(EnumResStatus.Rejected,false);
        iterNode.setStatus(EnumNodeStatus.Suspended);
        iterNode.setAttribute("StatusDetails",ITERATION_PAUSED);
        iterDoc.write2File(getIteration(2),2,false);
    }
    
///////////////////////////////////////////////////////////////////
    
    private void iterateThird()
    {
        iterateRead(2);
        JDFPhaseTime pt=iterNode.getAuditPool().setPhase(EnumNodeStatus.InProgress, "Second Iteration Ongoing", null,null);
        pt.setStart(new JDFDate(System.currentTimeMillis()+2000000));
        pt.setEnd(new JDFDate(System.currentTimeMillis()+2100000));
        pt.appendEmployee().setPersonalID("Employee 1");
        pt.appendDevice().setDeviceID("Device 2");
        iterRuli.setResStatus(EnumResStatus.Unavailable,false);
        JDFResourceAudit ra=iterNode.cloneResourceToModify(iterNode.getLink(iterRuli,null));
        iterRuli=(JDFRunList) ra.getNewLink().getTarget();
        iterRuli.setResStatus(EnumResStatus.Draft,false);
        iterRuli.setFileURL(StringUtil.uncToUrl("C:\\local\\MyUpdatedInOutput.pdf",false));
        
        iterNode.setStatus(EnumNodeStatus.Suspended);
        iterNode.setAttribute("StatusDetails",ITERATION_PAUSED);
        iterDoc.write2File(getIteration(3),2,false);
    }
    
///////////////////////////////////////////////////////////////////
    
    private void iterateForth()
    {
        iterateRead(3);
        JDFPhaseTime pt=iterNode.getAuditPool().setPhase(EnumNodeStatus.InProgress, "Second Approval Ongoing", null,null);
        pt.setStart(new JDFDate(System.currentTimeMillis()+3000000));
        pt.setEnd(new JDFDate(System.currentTimeMillis()+3100000));
        pt.appendEmployee().setPersonalID("Employee 3");
        pt.appendDevice().setDeviceID("Approval Device 1");
        iterRuli.setResStatus(EnumResStatus.Available,false);
        iterNode.setStatus(EnumNodeStatus.Suspended);
        iterNode.setAttribute("StatusDetails",ITERATION_PAUSED);
        iterAppSuccess.setResStatus(EnumResStatus.Available,true);
        iterDoc.write2File(getIteration(4),2,false);
    }
    
///////////////////////////////////////////////////////////////////
    
    private void iterateLast()
    {
        iterateRead(4);
        JDFPhaseTime pt=iterNode.getAuditPool().setPhase(EnumNodeStatus.InProgress, "Final Iteration Ongoing - final output", null,null);
        pt.setStart(new JDFDate(System.currentTimeMillis()+4000000));
        pt.setEnd(new JDFDate(System.currentTimeMillis()+4100000));
        pt.appendEmployee().setPersonalID("Employee 1");
        pt.appendDevice().setDeviceID("Device 1");
        iterNode.setStatus(EnumNodeStatus.Completed);
        iterDoc.write2File(getIteration(5),2,false);
    }
///////////////////////////////////////////////////////////////////
    
    private String getIteration(int i)
    {
        return sm_dirTestDataTemp+File.separator+"Interation_"+String.valueOf(i)+".jdf";
    }
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
    
 }
