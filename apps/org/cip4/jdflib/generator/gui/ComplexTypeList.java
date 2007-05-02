/*
 * Created on Jul 12, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.generator.gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



/**
 * @author matternk
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ComplexTypeList extends JPanel implements ListSelectionListener, MouseListener, ActionListener
{
    private static final long serialVersionUID = 1L;
    private JList myList;
    private static DefaultListModel listModel           = new DefaultListModel();
    private static DefaultListModel defaultlistModel    = new DefaultListModel();
    private static DefaultListModel loadedListModel     = new DefaultListModel();
    private final String lineSep = System.getProperty("line.separator");
    private final GeneratorUI motherComp;
    private ListButtonPanel lbp;
    private final ArrayList doubleClickedListener = new ArrayList();
    private JMenuItem popup_load;
    private JMenuItem popup_safe;
    private JMenuItem popup_removeSel;
    private JMenuItem popup_removeall;
    private JMenuItem popup_addtolist;
    
    public ComplexTypeList(GeneratorUI mother)
    {
        motherComp = mother;
        init();
    }
    
    private void init()
    {
        for(int i = 0; i < defaultlistModel.size(); i++)
        {
            String strElementName = (String)defaultlistModel.get(i); 
            listModel.addElement(strElementName);
        }
        setLayout(new BorderLayout());
        lbp             = new ListButtonPanel(this);
        popup_load      = new JMenuItem("Open file list...");
        popup_safe      = new JMenuItem("Safe file list...");
        popup_removeSel = new JMenuItem("Remove selected items");
        popup_removeall = new JMenuItem("Remove all items");
        popup_addtolist = new JMenuItem("Add Items to list...");
        
        popup_load.addActionListener(this);
        popup_safe.addActionListener(this);
        popup_removeSel.addActionListener(this);
        popup_removeall.addActionListener(this);
        popup_addtolist.addActionListener(this);
        
        final JPopupMenu popmenu = new JPopupMenu();
       
        popmenu.add(popup_load);
        popmenu.add(popup_safe);
        popmenu.addSeparator();
        popmenu.add(popup_removeSel);
        popmenu.add(popup_removeall);
        popmenu.add(popup_addtolist);
        
        myList = new JList(listModel);
        myList.addListSelectionListener(this);
        myList.addMouseListener(this);
        myList.addMouseListener(new MouseAdapter() 
        {
            @Override
			public void mousePressed(MouseEvent evt) 
            {
                if (evt.isPopupTrigger()) 
                {
                    popmenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
            @Override
			public void mouseReleased(MouseEvent evt) 
            {
                if (evt.isPopupTrigger()) 
                {
                    popmenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        });
        myList.add(popmenu);
        myList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        myList.setSelectedIndex(0);
        JScrollPane jsc = new JScrollPane(myList);
        add(jsc, BorderLayout.CENTER);
        add(lbp, BorderLayout.SOUTH);
    }
    
    public GeneratorUI getMainFrame()
    {
        return motherComp;
    }
    
    public JList getList()
    {
        return myList;
    }
    
    public ListButtonPanel getListButtonPanel()
    {
        return lbp;
    }
    
    public static DefaultListModel getDefaultListModel()
    {
        return listModel;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        int i = e.getClickCount();
        if(i % 2 == 0)
        {
            int j = myList.locationToIndex(new Point(e.getX(), e.getY()));
            fireListDoubleClickedEvent((String)listModel.get(j));
        }
    }
    
    public void addListDoubleClickedListener(ListDoubleClickedListener aListener)
    {
        doubleClickedListener.add(aListener);
    }
    
    public void removeListDoubleClickedListener(ListDoubleClickedListener aListener)
    {
        doubleClickedListener.remove(aListener);
    }
    
    protected void fireListDoubleClickedEvent(String strName)
    {
        for(int i = 0; i < doubleClickedListener.size(); i++)
        {
            ListDoubleClickedEvent event = new ListDoubleClickedEvent(this, strName);
            ((ListDoubleClickedListener)doubleClickedListener.get(i)).listDoubleClicked(event);
        }
    }
    
    public void valueChanged(ListSelectionEvent e)
    {
        e.hashCode(); //just to remomve the e is never used warning
        myList.ensureIndexIsVisible(myList.getSelectedIndex());
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == popup_load)
        {
            JFileChooser fc = new JFileChooser();
            fc.setVisible(true);

            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                File toGenerateFile = fc.getSelectedFile();

                loadFileList(toGenerateFile);
            }
        }
        else if (e.getSource() == popup_safe)
        {
            JFileChooser fc = new JFileChooser();
            fc.setVisible(true);
            File toGenerateFile = null;
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                BufferedWriter bw = null;
                toGenerateFile = fc.getSelectedFile();
                try
                {
                    StringBuffer strBuff = new StringBuffer(500);
                    bw = new BufferedWriter(new FileWriter(toGenerateFile));
                    String lineSeperator = System.getProperty("line.separator");

                    for (int i = 0; i < listModel.size(); i++)
                    {
                        strBuff.append((String) listModel.get(i));
                        strBuff.append(lineSeperator);
                    }

                    DefaultTableModel dtm = motherComp.getStatusPanel().getDefaultTableModel();
                    dtm.insertRow(0, new Object[] { "Writing " + toGenerateFile.getName(),
                            "Working..." });
                    bw.write(strBuff.toString());

                    try
                    {
                        dtm.setValueAt("Done", 0, 1);
                    }
                    catch (ArrayIndexOutOfBoundsException ae)
                    {
                        // do nothing
                    }
                }
                catch (IOException ioe)
                {
                    // do nothing
                }
                finally
                {
                    try
                    {
                        if (bw != null)
                            bw.close();
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                }
            }
        }
        else if (e.getSource() == popup_removeSel)
        {
            int[] selected = myList.getSelectedIndices();
            for (int i = selected.length; i > 0; i--)
            {
                listModel.remove(selected[i - 1]);
            }
        }
        else if (e.getSource() == popup_removeall)
        {
            listModel.removeAllElements();
        }
        else if (e.getSource() == popup_addtolist)
        {
            AddAutoFileDialog addAuto = new AddAutoFileDialog();
            addAuto.pack();
            addAuto.setLocationRelativeTo(this);
            addAuto.setVisible(true);
        }
    }
    
    /**
     * @param toGenerateFile
     */
    public void loadFileList(File toGenerateFile)
    {
        if(toGenerateFile.canRead())
        {
            DefaultTableModel dtm = motherComp.getStatusPanel().getDefaultTableModel();
            dtm.insertRow(0, new Object[]{"Loading " + toGenerateFile.getName(), "Working..."});
            BufferedReader buffreader = null;
            try
            {
                buffreader = new BufferedReader(new FileReader(toGenerateFile));
                String strLineOfFile = null;
                while((strLineOfFile = buffreader.readLine()) != null)
                {
                    if(!strLineOfFile.startsWith("JDFAuto") || !strLineOfFile.endsWith(".java"))
                    {
                        JOptionPane.showMessageDialog(this,
                                                      strLineOfFile 
                                                    + lineSep + "is a malformed filename." 
                                                    + lineSep + "Filenames must start with \"JDFAuto\" and end with \".java\"" 
                                                    + lineSep + lineSep + " Cant read file",
                                                      "Malformed Filename in User-filelist",
                                                      JOptionPane.ERROR_MESSAGE);
                        
                        //TODO resolve this foobar
                        loadedListModel.removeAllElements();
                        for(int i = 0; i < defaultlistModel.size(); i++)
                        {
                            String strElementName = (String)defaultlistModel.get(i); 
                            loadedListModel.addElement(strElementName);
                        }
                        //break;
                    }
                    else
                    {
                        loadedListModel.addElement(strLineOfFile);
                    }
                }
                listModel.removeAllElements();
                for(int i = 0; i < loadedListModel.size(); i++)
                {
                    String strElementName = (String)loadedListModel.get(i); 
                    listModel.addElement(strElementName);
                }
                validate();
            }
            catch(FileNotFoundException fnfe)
            {
                JOptionPane.showMessageDialog(this, "The file was not found", "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
            }
            catch(IOException ioe)
            {
                JOptionPane.showMessageDialog(this, "Error while parsing file", "IOException", JOptionPane.ERROR_MESSAGE);
            }
            finally
            {
                if(buffreader != null)
                {
                    try
                    {
                        buffreader.close();
                    }
                    catch(IOException ioe)
                    {
                        ioe.hashCode(); // to remove the ioe is never used warning
                    }
                }
                try
                {
                    dtm.setValueAt("Done", 0, 1);
                }catch(ArrayIndexOutOfBoundsException ae)
                {
                    ae.hashCode(); // to remove the e is never used warning
                }
            }
            
        }
    }

    public void mousePressed(MouseEvent e)
    {
        e.getID(); // to remove the e is never used warning
        //do nothing
    }
    public void mouseReleased(MouseEvent e)
    {
        e.getID(); // to remove the e is never used warning
        //do nothing
    }
    public void mouseEntered(MouseEvent e)
    {
        e.getID(); // to remove the e is never used warning
        //do nothing
    }
    public void mouseExited(MouseEvent e)
    {
        e.getID(); // to remove the e is never used warning
        //do nothing
    }
    
    static
    {
        defaultlistModel.addElement("JDFAutoAcknowledge.java");
        defaultlistModel.addElement("JDFAutoAction.java");
        defaultlistModel.addElement("JDFAutoActionPool.java");
        defaultlistModel.addElement("JDFAutoAdded.java");
        defaultlistModel.addElement("JDFAutoAddress.java");
        defaultlistModel.addElement("JDFAutoAdhesiveBinding.java");
        defaultlistModel.addElement("JDFAutoAdhesiveBindingParams.java");
        defaultlistModel.addElement("JDFAutoAdvancedParams.java");
        defaultlistModel.addElement("JDFAutoAmountPool.java");
        defaultlistModel.addElement("JDFAutoAncestor.java");
        defaultlistModel.addElement("JDFAutoAncestorPool.java");
        defaultlistModel.addElement("JDFAutoand.java");
        defaultlistModel.addElement("JDFAutoApprovalDetails.java");
        defaultlistModel.addElement("JDFAutoApprovalParams.java");
        defaultlistModel.addElement("JDFAutoApprovalPerson.java");
        defaultlistModel.addElement("JDFAutoApprovalSuccess.java");
        defaultlistModel.addElement("JDFAutoArgumentValue.java");
        defaultlistModel.addElement("JDFAutoArtDelivery.java");
        defaultlistModel.addElement("JDFAutoArtDeliveryIntent.java");
        defaultlistModel.addElement("JDFAutoAssembly.java");
        defaultlistModel.addElement("JDFAutoAssemblySection.java");
        defaultlistModel.addElement("JDFAutoAssetCollectionParams.java");
        defaultlistModel.addElement("JDFAutoAssetListCreationParams.java");
        defaultlistModel.addElement("JDFAutoAutomatedOverPrintParams.java");
        defaultlistModel.addElement("JDFAutoBackPreparation.java");
        defaultlistModel.addElement("JDFAutoBand.java");
        defaultlistModel.addElement("JDFAutoBarcode.java");
        defaultlistModel.addElement("JDFAutoBarcodeCompParams.java");
        defaultlistModel.addElement("JDFAutoBarcodeDetails.java");
        defaultlistModel.addElement("JDFAutoBarcodeReproParams.java");
        defaultlistModel.addElement("JDFAutoBarcodeProductionParams.java");
        defaultlistModel.addElement("JDFAutoBasicPreflightTest.java");
        defaultlistModel.addElement("JDFAutoBendingParams.java");
        defaultlistModel.addElement("JDFAutoBinderySignature.java");
        defaultlistModel.addElement("JDFAutoBindingIntent.java");
        defaultlistModel.addElement("JDFAutoBindingQualityParams.java");
        defaultlistModel.addElement("JDFAutoBindItem.java");
        defaultlistModel.addElement("JDFAutoBindList.java");
        defaultlistModel.addElement("JDFAutoBlockPreparationParams.java");
        defaultlistModel.addElement("JDFAutoBookCase.java");
        defaultlistModel.addElement("JDFAutoBooleanEvaluation.java");
        defaultlistModel.addElement("JDFAutoBoxApplication.java");
        defaultlistModel.addElement("JDFAutoBoxArgument.java");
        defaultlistModel.addElement("JDFAutoBoxFoldAction.java");
        defaultlistModel.addElement("JDFAutoBoxFoldingParams.java");
        defaultlistModel.addElement("JDFAutoBoxPackingParams.java");
        defaultlistModel.addElement("JDFAutoBoxToBoxDifference.java");
        defaultlistModel.addElement("JDFAutoBufferParams.java");
        defaultlistModel.addElement("JDFAutoBundle.java");
        defaultlistModel.addElement("JDFAutoBundleItem.java");
        defaultlistModel.addElement("JDFAutoBundlingParams.java");
        defaultlistModel.addElement("JDFAutoBusinessInfo.java");
        defaultlistModel.addElement("JDFAutoByteMap.java");
        defaultlistModel.addElement("JDFAutocall.java");
        defaultlistModel.addElement("JDFAutoCaseMakingParams.java");
        defaultlistModel.addElement("JDFAutoCasingInParams.java");
        defaultlistModel.addElement("JDFAutoCCITTFaxParams.java");
        defaultlistModel.addElement("JDFAutoChangedAttribute.java");
        defaultlistModel.addElement("JDFAutoChangedPath.java");
        defaultlistModel.addElement("JDFAutoChannelBinding.java");
        defaultlistModel.addElement("JDFAutoChannelBindingParams.java");
        defaultlistModel.addElement("JDFAutochoice.java");
        defaultlistModel.addElement("JDFAutoCIELABMeasuringField.java");
        defaultlistModel.addElement("JDFAutoCoilBinding.java");
        defaultlistModel.addElement("JDFAutoCoilBindingParams.java");
        defaultlistModel.addElement("JDFAutoCollatingItem.java");
        defaultlistModel.addElement("JDFAutoCollectingParams.java");
        defaultlistModel.addElement("JDFAutoColor.java");
        defaultlistModel.addElement("JDFAutoColorantAlias.java");
        defaultlistModel.addElement("JDFAutoColorantControl.java");
        defaultlistModel.addElement("JDFAutoColorantZoneDetails.java");
        defaultlistModel.addElement("JDFAutoColorControlStrip.java");
        defaultlistModel.addElement("JDFAutoColorCorrectionOp.java");
        defaultlistModel.addElement("JDFAutoColorCorrectionParams.java");
        defaultlistModel.addElement("JDFAutoColorIntent.java");
        defaultlistModel.addElement("JDFAutoColorMeasurementConditions.java");
        defaultlistModel.addElement("JDFAutoColorPool.java");
        defaultlistModel.addElement("JDFAutoColorSpaceConversionOp.java");
        defaultlistModel.addElement("JDFAutoColorSpaceConversionParams.java");
        defaultlistModel.addElement("JDFAutoColorSpaceSubstitute.java");
        defaultlistModel.addElement("JDFAutoColorsUsed.java");
        defaultlistModel.addElement("JDFAutoComChannel.java");
        defaultlistModel.addElement("JDFAutoCommand.java");
        defaultlistModel.addElement("JDFAutoComment.java");
        defaultlistModel.addElement("JDFAutoCompany.java");
        defaultlistModel.addElement("JDFAutoComponent.java");
        defaultlistModel.addElement("JDFAutoContact.java");
        defaultlistModel.addElement("JDFAutoContactCopyParams.java");
        defaultlistModel.addElement("JDFAutoContainer.java");
        defaultlistModel.addElement("JDFAutoContentData.java");
        defaultlistModel.addElement("JDFAutoContentList.java");
        defaultlistModel.addElement("JDFAutoContentObject.java");
        defaultlistModel.addElement("JDFAutoConventionalPrintingParams.java");
        defaultlistModel.addElement("JDFAutoCostCenter.java");
        defaultlistModel.addElement("JDFAutoCounterReset.java");
        defaultlistModel.addElement("JDFAutoCoverApplicationParams.java");
        defaultlistModel.addElement("JDFAutoCrease.java");
        defaultlistModel.addElement("JDFAutoCreasingParams.java");
        defaultlistModel.addElement("JDFAutoCreated.java");
        defaultlistModel.addElement("JDFAutoCreateLink.java");
        defaultlistModel.addElement("JDFAutoCreateResource.java");
        defaultlistModel.addElement("JDFAutoCreditCard.java");
        defaultlistModel.addElement("JDFAutoCustomerInfo.java");
        defaultlistModel.addElement("JDFAutoCustomerMessage.java");
        defaultlistModel.addElement("JDFAutoCut.java");
        defaultlistModel.addElement("JDFAutoCutBlock.java");
        defaultlistModel.addElement("JDFAutoCutMark.java");
        defaultlistModel.addElement("JDFAutoCuttingParams.java");
        defaultlistModel.addElement("JDFAutoCylinderLayout.java");
        defaultlistModel.addElement("JDFAutoCylinderLayoutPreparationParams.java");
        defaultlistModel.addElement("JDFAutoCylinderPosition.java");
        defaultlistModel.addElement("JDFAutoDateTimeEvaluation.java");
        defaultlistModel.addElement("JDFAutoDBMergeParams.java");
        defaultlistModel.addElement("JDFAutoDBRules.java");
        defaultlistModel.addElement("JDFAutoDBSchema.java");
        defaultlistModel.addElement("JDFAutoDBSelection.java");
        defaultlistModel.addElement("JDFAutoDCTParams.java");
        defaultlistModel.addElement("JDFAutoDeleted.java");
        defaultlistModel.addElement("JDFAutoDeliveryIntent.java");
        defaultlistModel.addElement("JDFAutoDeliveryParams.java");
        defaultlistModel.addElement("JDFAutoDensityMeasuringField.java");
        defaultlistModel.addElement("JDFAutoDependencies.java");
        defaultlistModel.addElement("JDFAutoDevCap.java");
        defaultlistModel.addElement("JDFAutoDevCapPool.java");
        defaultlistModel.addElement("JDFAutoDevCaps.java");
        defaultlistModel.addElement("JDFAutoDevelopingParams.java");
        defaultlistModel.addElement("JDFAutoDevice.java");
        defaultlistModel.addElement("JDFAutoDeviceCap.java");
        defaultlistModel.addElement("JDFAutoDeviceFilter.java");
        defaultlistModel.addElement("JDFAutoDeviceInfo.java");
        defaultlistModel.addElement("JDFAutoDeviceList.java");
        defaultlistModel.addElement("JDFAutoDeviceMark.java");
        defaultlistModel.addElement("JDFAutoDeviceNColor.java");
        defaultlistModel.addElement("JDFAutoDeviceNSpace.java");
        defaultlistModel.addElement("JDFAutoDieLayout.java");
        defaultlistModel.addElement("JDFAutoDigitalDeliveryParams.java");
        defaultlistModel.addElement("JDFAutoDigitalMedia.java");
        defaultlistModel.addElement("JDFAutoDigitalPrintingParams.java");
        defaultlistModel.addElement("JDFAutoDisjointing.java");
        defaultlistModel.addElement("JDFAutoDisplayGroup.java");
        defaultlistModel.addElement("JDFAutoDisplayGroupPool.java");
        defaultlistModel.addElement("JDFAutoDisposition.java");
        defaultlistModel.addElement("JDFAutoDividingParams.java");
        defaultlistModel.addElement("JDFAutoDrop.java");
        defaultlistModel.addElement("JDFAutoDropIntent.java");
        defaultlistModel.addElement("JDFAutoDropItem.java");
        defaultlistModel.addElement("JDFAutoDropItemIntent.java");
        defaultlistModel.addElement("JDFAutoDurationEvaluation.java");
        defaultlistModel.addElement("JDFAutoDynamicField.java");
        defaultlistModel.addElement("JDFAutoDynamicInput.java");
        defaultlistModel.addElement("JDFAutoEdgeGluing.java");
        defaultlistModel.addElement("JDFAutoElementColorParams.java");
        defaultlistModel.addElement("JDFAutoEmboss.java");
        defaultlistModel.addElement("JDFAutoEmbossingIntent.java");
        defaultlistModel.addElement("JDFAutoEmbossingItem.java");
        defaultlistModel.addElement("JDFAutoEmbossingParams.java");
        defaultlistModel.addElement("JDFAutoEmployee.java");
        defaultlistModel.addElement("JDFAutoEmployeeDef.java");
        defaultlistModel.addElement("JDFAutoEndSheet.java");
        defaultlistModel.addElement("JDFAutoEndSheetGluingParams.java");
        defaultlistModel.addElement("JDFAutoEnumerationEvaluation.java");
//        defaultlistModel.addElement("JDFAutoEnumerationSpan.java");  // all JDFxxxSpan inherit from JDFSpanBase
        defaultlistModel.addElement("JDFAutoError.java");
        defaultlistModel.addElement("JDFAutoErrorData.java");
        defaultlistModel.addElement("JDFAutoEvent.java");
        defaultlistModel.addElement("JDFAutoExposedMedia.java");
        defaultlistModel.addElement("JDFAutoExternalImpositionTemplate.java");
        defaultlistModel.addElement("JDFAutoExtraValues.java");
        defaultlistModel.addElement("JDFAutoFCNKey.java");
        defaultlistModel.addElement("JDFAutoFeatureAttribute.java");
        defaultlistModel.addElement("JDFAutoFeaturePool.java");
        defaultlistModel.addElement("JDFAutoFeeder.java");
        defaultlistModel.addElement("JDFAutoFeederQualityParams.java");
        defaultlistModel.addElement("JDFAutoFeedingParams.java");
        defaultlistModel.addElement("JDFAutoFileAlias.java");
        defaultlistModel.addElement("JDFAutoFileSpec.java");
        defaultlistModel.addElement("JDFAutoFitPolicy.java");
        defaultlistModel.addElement("JDFAutoFlateParams.java");
        defaultlistModel.addElement("JDFAutoFlushedResources.java");
        defaultlistModel.addElement("JDFAutoFlushQueueInfo.java");
        defaultlistModel.addElement("JDFAutoFlushQueueParams.java");
        defaultlistModel.addElement("JDFAutoFlushResourceParams.java");
        defaultlistModel.addElement("JDFAutoFold.java");
        defaultlistModel.addElement("JDFAutoFolderProduction.java");
        defaultlistModel.addElement("JDFAutoFoldingIntent.java");
        defaultlistModel.addElement("JDFAutoFoldingParams.java");
        defaultlistModel.addElement("JDFAutoFoldOperation.java");
        defaultlistModel.addElement("JDFAutoFontParams.java");
        defaultlistModel.addElement("JDFAutoFontPolicy.java");
        defaultlistModel.addElement("JDFAutoFormatConversionParams.java");
        defaultlistModel.addElement("JDFAutoGangCmdFilter.java");
        defaultlistModel.addElement("JDFAutoGangInfo.java");
        defaultlistModel.addElement("JDFAutoGangQuFilter.java");
        defaultlistModel.addElement("JDFAutoGatheringParams.java");
        defaultlistModel.addElement("JDFAutoGeneralID.java");
        defaultlistModel.addElement("JDFAutoGlue.java");
        defaultlistModel.addElement("JDFAutoGlueApplication.java");
        defaultlistModel.addElement("JDFAutoGlueLine.java");
        defaultlistModel.addElement("JDFAutoGluingParams.java");
        defaultlistModel.addElement("JDFAutoHardCoverBinding.java");
        defaultlistModel.addElement("JDFAutoHeadBandApplicationParams.java");
        defaultlistModel.addElement("JDFAutoHole.java");
        defaultlistModel.addElement("JDFAutoHoleLine.java");
        defaultlistModel.addElement("JDFAutoHoleList.java");
        defaultlistModel.addElement("JDFAutoHoleMakingIntent.java");
        defaultlistModel.addElement("JDFAutoHoleMakingParams.java");
        defaultlistModel.addElement("JDFAutoIcon.java");
        defaultlistModel.addElement("JDFAutoIconList.java");
        defaultlistModel.addElement("JDFAutoIdentical.java");
        defaultlistModel.addElement("JDFAutoIdentificationField.java");
        defaultlistModel.addElement("JDFAutoIDInfo.java");
        defaultlistModel.addElement("JDFAutoIDPCover.java");
        defaultlistModel.addElement("JDFAutoIDPFinishing.java");
        defaultlistModel.addElement("JDFAutoIDPFolding.java");
        defaultlistModel.addElement("JDFAutoIDPHoleMaking.java");
        defaultlistModel.addElement("JDFAutoIDPImageShift.java");
        defaultlistModel.addElement("JDFAutoIDPJobSheet.java");
        defaultlistModel.addElement("JDFAutoIDPLayout.java");
        defaultlistModel.addElement("JDFAutoIDPrintingParams.java");
        defaultlistModel.addElement("JDFAutoIDPStitching.java");
        defaultlistModel.addElement("JDFAutoIDPTrimming.java");
        defaultlistModel.addElement("JDFAutoImageCompression.java");
        defaultlistModel.addElement("JDFAutoImageCompressionParams.java");
        defaultlistModel.addElement("JDFAutoImageReplacementParams.java");
        defaultlistModel.addElement("JDFAutoImageSetterParams.java");
        defaultlistModel.addElement("JDFAutoImageShift.java");
        defaultlistModel.addElement("JDFAutoInk.java");
        defaultlistModel.addElement("JDFAutoInkZoneCalculationParams.java");
        defaultlistModel.addElement("JDFAutoInkZoneProfile.java");
        defaultlistModel.addElement("JDFAutoInsert.java");
        defaultlistModel.addElement("JDFAutoInsertingIntent.java");
        defaultlistModel.addElement("JDFAutoInsertingParams.java");
        defaultlistModel.addElement("JDFAutoInsertList.java");
        defaultlistModel.addElement("JDFAutoInsertSheet.java");
        defaultlistModel.addElement("JDFAutoIntegerEvaluation.java");
        defaultlistModel.addElement("JDFAutoInterpretedPDLData.java");
        defaultlistModel.addElement("JDFAutoInterpretingParams.java");
        defaultlistModel.addElement("JDFAutoIsPresentEvaluation.java");
        defaultlistModel.addElement("JDFAutoJacketingParams.java");
        defaultlistModel.addElement("JDFAutoJBIG2Params.java");
        defaultlistModel.addElement("JDFAutoJDFController.java");
        defaultlistModel.addElement("JDFAutoJDFService.java");
        defaultlistModel.addElement("JDFAutoJMF.java");
        defaultlistModel.addElement("JDFAutoJobField.java");
        defaultlistModel.addElement("JDFAutoJobPhase.java");
        defaultlistModel.addElement("JDFAutoJPEG2000Params.java");
        defaultlistModel.addElement("JDFAutoKnownMsgQuParams.java");
        defaultlistModel.addElement("JDFAutoLabelingParams.java");
        defaultlistModel.addElement("JDFAutoLaminatingIntent.java");
        defaultlistModel.addElement("JDFAutoLaminatingParams.java");
        defaultlistModel.addElement("JDFAutoLayerDetails.java");
        defaultlistModel.addElement("JDFAutoLayerList.java");
        defaultlistModel.addElement("JDFAutoLayout.java");
        defaultlistModel.addElement("JDFAutoLayoutElement.java");
        defaultlistModel.addElement("JDFAutoLayoutElementPart.java");
        defaultlistModel.addElement("JDFAutoLayoutElementProductionParams.java");
        defaultlistModel.addElement("JDFAutoLayoutIntent.java");
        defaultlistModel.addElement("JDFAutoLayoutPreparationParams.java");
        defaultlistModel.addElement("JDFAutoLoc.java");
        defaultlistModel.addElement("JDFAutoLocation.java");
        defaultlistModel.addElement("JDFAutoLongFold.java");
        defaultlistModel.addElement("JDFAutoLongGlue.java");
        defaultlistModel.addElement("JDFAutoLongitudinalRibbonOperationParams.java");
        defaultlistModel.addElement("JDFAutoLongPerforate.java");
        defaultlistModel.addElement("JDFAutoLongSlit.java");
        defaultlistModel.addElement("JDFAutoLot.java");
        defaultlistModel.addElement("JDFAutoLZWParams.java");
        defaultlistModel.addElement("JDFAutomacro.java");
        defaultlistModel.addElement("JDFAutoMacroPool.java");
        defaultlistModel.addElement("JDFAutomacros.java");
        defaultlistModel.addElement("JDFAutoManualLaborParams.java");
        defaultlistModel.addElement("JDFAutoMarkObject.java");
        defaultlistModel.addElement("JDFAutoMatrixEvaluation.java");
        defaultlistModel.addElement("JDFAutoMedia.java");
        defaultlistModel.addElement("JDFAutoMediaIntent.java");
        defaultlistModel.addElement("JDFAutoMediaLayers.java");
        defaultlistModel.addElement("JDFAutoMediaSource.java");
        defaultlistModel.addElement("JDFAutoMerged.java");
        defaultlistModel.addElement("JDFAutoMessage.java");
        defaultlistModel.addElement("JDFAutoMessageService.java");
        defaultlistModel.addElement("JDFAutoMiscConsumable.java");
        defaultlistModel.addElement("JDFAutoMISDetails.java");
        defaultlistModel.addElement("JDFAutoModified.java");
        defaultlistModel.addElement("JDFAutoModifyNodeCmdParams.java");
        defaultlistModel.addElement("JDFAutoModule.java");
        defaultlistModel.addElement("JDFAutoModuleCap.java");
        defaultlistModel.addElement("JDFAutoModulePhase.java");
        defaultlistModel.addElement("JDFAutoModulePool.java");
        defaultlistModel.addElement("JDFAutoModuleStatus.java");
        defaultlistModel.addElement("JDFAutoMoveResource.java");
        defaultlistModel.addElement("JDFAutoMsgFilter.java");
        defaultlistModel.addElement("JDFAutoNameEvaluation.java");
        defaultlistModel.addElement("JDFAutonot.java");
        defaultlistModel.addElement("JDFAutoNewComment.java");
        defaultlistModel.addElement("JDFAutoNewJDFCmdParams.java");
        defaultlistModel.addElement("JDFAutoNewJDFQuParams.java");
        defaultlistModel.addElement("JDFAutoNodeInfo.java");
        defaultlistModel.addElement("JDFAutoNodeInfoCmdParams.java");
        defaultlistModel.addElement("JDFAutoNodeInfoQuParams.java");
        defaultlistModel.addElement("JDFAutoNodeInfoResp.java");
        defaultlistModel.addElement("JDFAutoNotification.java");
        defaultlistModel.addElement("JDFAutoNotificationDef.java");
        defaultlistModel.addElement("JDFAutoNotificationFilter.java");
        defaultlistModel.addElement("JDFAutoNumberEvaluation.java");
        defaultlistModel.addElement("JDFAutoNumberingIntent.java");
        defaultlistModel.addElement("JDFAutoNumberingParam.java");
        defaultlistModel.addElement("JDFAutoNumberingParams.java");
        defaultlistModel.addElement("JDFAutoNumberItem.java");
        defaultlistModel.addElement("JDFAutoObjectResolution.java");
        defaultlistModel.addElement("JDFAutoObservationTarget.java");
        defaultlistModel.addElement("JDFAutoOccupation.java");
        defaultlistModel.addElement("JDFAutoOCGControl.java");
        defaultlistModel.addElement("JDFAutoor.java");
        defaultlistModel.addElement("JDFAutoOrderingParams.java");
        defaultlistModel.addElement("JDFAutootherwise.java");
        defaultlistModel.addElement("JDFAutoPackingIntent.java");
        defaultlistModel.addElement("JDFAutoPackingParams.java");
        defaultlistModel.addElement("JDFAutoPageAssignedList.java");
        defaultlistModel.addElement("JDFAutoPageCell.java");
        defaultlistModel.addElement("JDFAutoPageData.java");
        defaultlistModel.addElement("JDFAutoPageElement.java");
        defaultlistModel.addElement("JDFAutoPageList.java");
        defaultlistModel.addElement("JDFAutoPallet.java");
        defaultlistModel.addElement("JDFAutoPalletizingParams.java");
        defaultlistModel.addElement("JDFAutoPart.java");
        defaultlistModel.addElement("JDFAutoPartStatus.java");
        defaultlistModel.addElement("JDFAutoPayment.java");
        defaultlistModel.addElement("JDFAutoPDFInterpretingParams.java");
        defaultlistModel.addElement("JDFAutoPDFPathEvaluation.java");
        defaultlistModel.addElement("JDFAutoPDFToPSConversionParams.java");
        defaultlistModel.addElement("JDFAutoPDFXParams.java");
        defaultlistModel.addElement("JDFAutoPDLCreationParams.java");
        defaultlistModel.addElement("JDFAutoPDLResourceAlias.java");
        defaultlistModel.addElement("JDFAutoPerforate.java");
        defaultlistModel.addElement("JDFAutoPerforatingParams.java");
        defaultlistModel.addElement("JDFAutoPerformance.java");
        defaultlistModel.addElement("JDFAutoPerson.java");
        defaultlistModel.addElement("JDFAutoPhaseTime.java");
        defaultlistModel.addElement("JDFAutoPipeParams.java");
        defaultlistModel.addElement("JDFAutoPixelColorant.java");
        defaultlistModel.addElement("JDFAutoPlaceHolderResource.java");
        defaultlistModel.addElement("JDFAutoPlasticCombBinding.java");
        defaultlistModel.addElement("JDFAutoPlasticCombBindingParams.java");
        defaultlistModel.addElement("JDFAutoPlateCopyParams.java");
        defaultlistModel.addElement("JDFAutoPosition.java");
        defaultlistModel.addElement("JDFAutoPreflightAction.java");
        defaultlistModel.addElement("JDFAutoPreflightAnalysis.java");
        defaultlistModel.addElement("JDFAutoPreflightArgument.java");
        defaultlistModel.addElement("JDFAutoPreflightConstraint.java");
        defaultlistModel.addElement("JDFAutoPreflightDetail.java");
        defaultlistModel.addElement("JDFAutoPreflightInformation.java");
        defaultlistModel.addElement("JDFAutoPreflightInstance.java");
        defaultlistModel.addElement("JDFAutoPreflightInstanceDetail.java");
        defaultlistModel.addElement("JDFAutoPreflightInventory.java");
        defaultlistModel.addElement("JDFAutoPreflightParams.java");
        defaultlistModel.addElement("JDFAutoPreflightProfile.java");
        defaultlistModel.addElement("JDFAutoPreflightResultsPool.java");
        defaultlistModel.addElement("JDFAutoPreflightReport.java");
        defaultlistModel.addElement("JDFAutoPreflightReportRulePool.java");
        defaultlistModel.addElement("JDFAutoPreflightResultsPool.java");
//        defaultlistModel.addElement("JDFAutoPreflightValue.java");
        defaultlistModel.addElement("JDFAutoPRError.java");
        defaultlistModel.addElement("JDFAutoPreview.java");
        defaultlistModel.addElement("JDFAutoPreviewGenerationParams.java");
        defaultlistModel.addElement("JDFAutoPRGroup.java");
        defaultlistModel.addElement("JDFAutoPRGroupOccurrence.java");
//        defaultlistModel.addElement("JDFAutoPRGroupOccurrenceBase.java");
        defaultlistModel.addElement("JDFAutoPricing.java");
        defaultlistModel.addElement("JDFAutoPrintCondition.java");
        defaultlistModel.addElement("JDFAutoPrintConditionColor.java");
        defaultlistModel.addElement("JDFAutoPrintRollingParams.java");
        defaultlistModel.addElement("JDFAutoPRItem.java");
        defaultlistModel.addElement("JDFAutoPROccurrence.java");
        defaultlistModel.addElement("JDFAutoProcessRun.java");
        defaultlistModel.addElement("JDFAutoProductionIntent.java");
        defaultlistModel.addElement("JDFAutoProductionPath.java");
        defaultlistModel.addElement("JDFAutoProductionSubPath.java");
        defaultlistModel.addElement("JDFAutoProofingIntent.java");
        defaultlistModel.addElement("JDFAutoProofingParams.java");
        defaultlistModel.addElement("JDFAutoProofItem.java");
        defaultlistModel.addElement("JDFAutoProperties.java");
        defaultlistModel.addElement("JDFAutoPRRule.java");
        defaultlistModel.addElement("JDFAutoPRRuleAttr.java");
        defaultlistModel.addElement("JDFAutoPSToPDFConversionParams.java");
        defaultlistModel.addElement("JDFAutoPublishingIntent.java");
        defaultlistModel.addElement("JDFAutoQualityControlParams.java");
        defaultlistModel.addElement("JDFAutoQualityControlResult.java");
        defaultlistModel.addElement("JDFAutoQualityMeasurement.java");
        defaultlistModel.addElement("JDFAutoQuery.java");
        defaultlistModel.addElement("JDFAutoQueue.java");
        defaultlistModel.addElement("JDFAutoQueueEntry.java");
        defaultlistModel.addElement("JDFAutoQueueEntryDef.java");
        defaultlistModel.addElement("JDFAutoQueueEntryDefList.java");
        defaultlistModel.addElement("JDFAutoQueueEntryPosParams.java");
        defaultlistModel.addElement("JDFAutoQueueEntryPriParams.java");
        defaultlistModel.addElement("JDFAutoQueueFilter.java");
        defaultlistModel.addElement("JDFAutoQueueSubmissionParams.java");
        defaultlistModel.addElement("JDFAutoRectangleEvaluation.java");
        defaultlistModel.addElement("JDFAutoRegisterMark.java");
        defaultlistModel.addElement("JDFAutoRegisterRibbon.java");
        defaultlistModel.addElement("JDFAutoRegistration.java");
        defaultlistModel.addElement("JDFAutoRemoved.java");
        defaultlistModel.addElement("JDFAutoRemoveLink.java");
        defaultlistModel.addElement("JDFAutoRenderingParams.java");
        defaultlistModel.addElement("JDFAutoRequestQueueEntryParams.java");
        defaultlistModel.addElement("JDFAutoResourceAudit.java");
        defaultlistModel.addElement("JDFAutoResourceCmdParams.java");
        defaultlistModel.addElement("JDFAutoResourceDefinitionParams.java");
        defaultlistModel.addElement("JDFAutoResourceInfo.java");
        defaultlistModel.addElement("JDFAutoResourceParam.java");
        defaultlistModel.addElement("JDFAutoResourcePullParams.java");
        defaultlistModel.addElement("JDFAutoResourceQuParams.java");
        defaultlistModel.addElement("JDFAutoResponse.java");
        defaultlistModel.addElement("JDFAutoResubmissionParams.java");
        defaultlistModel.addElement("JDFAutoReturnQueueEntryParams.java");
        defaultlistModel.addElement("JDFAutoRingBinding.java");
        defaultlistModel.addElement("JDFAutoRingBindingParams.java");
        defaultlistModel.addElement("JDFAutoRollStand.java");
        defaultlistModel.addElement("JDFAutoRunList.java");
        defaultlistModel.addElement("JDFAutoSaddleStitching.java");
        defaultlistModel.addElement("JDFAutoSaddleStitchingParams.java");
        defaultlistModel.addElement("JDFAutoScanParams.java");
        defaultlistModel.addElement("JDFAutoScavengerArea.java");
        defaultlistModel.addElement("JDFAutoScore.java");
        defaultlistModel.addElement("JDFAutoScreeningIntent.java");
        defaultlistModel.addElement("JDFAutoScreeningParams.java");
        defaultlistModel.addElement("JDFAutoScreenSelector.java");
        defaultlistModel.addElement("JDFAutoSeparationControlParams.java");
        defaultlistModel.addElement("JDFAutoSeparationList.java");
        defaultlistModel.addElement("JDFAutoSeparationSpec.java");
        defaultlistModel.addElement("JDFAutoset.java");
        defaultlistModel.addElement("JDFAutoShapeCut.java");
        defaultlistModel.addElement("JDFAutoShapeCuttingIntent.java");
        defaultlistModel.addElement("JDFAutoShapeCuttingParams.java");
        defaultlistModel.addElement("JDFAutoShapeElement.java");
        defaultlistModel.addElement("JDFAutoShapeEvaluation.java");
        defaultlistModel.addElement("JDFAutoShrinkingParams.java");
        defaultlistModel.addElement("JDFAutoShutDownCmdParams.java");
        defaultlistModel.addElement("JDFAutoSideSewing.java");
        defaultlistModel.addElement("JDFAutoSideSewingParams.java");
        defaultlistModel.addElement("JDFAutoSideStitching.java");
        defaultlistModel.addElement("JDFAutoSignal.java");
        defaultlistModel.addElement("JDFAutoSignatureCell.java");
        defaultlistModel.addElement("JDFAutoSizeIntent.java");
        defaultlistModel.addElement("JDFAutoSoftCoverBinding.java");
//        defaultlistModel.addElement("JDFAutoSourceResource.java"); // not generated, too simple
        defaultlistModel.addElement("JDFAutoSpawned.java");
        defaultlistModel.addElement("JDFAutoSpinePreparationParams.java");
        defaultlistModel.addElement("JDFAutoSpineTaping.java");
        defaultlistModel.addElement("JDFAutoSpineTapingParams.java");
        defaultlistModel.addElement("JDFAutoStackingParams.java");
        defaultlistModel.addElement("JDFAutoStation.java");
        defaultlistModel.addElement("JDFAutoStatusPool.java");
        defaultlistModel.addElement("JDFAutoStatusQuParams.java");
        defaultlistModel.addElement("JDFAutoStitchingParams.java");
        defaultlistModel.addElement("JDFAutoStopPersChParams.java");
        defaultlistModel.addElement("JDFAutoStorageDuration.java");
        defaultlistModel.addElement("JDFAutoStrap.java");
        defaultlistModel.addElement("JDFAutoStrappingParams.java");
        defaultlistModel.addElement("JDFAutoStringEvaluation.java");
        defaultlistModel.addElement("JDFAutoStringListValue.java");
        defaultlistModel.addElement("JDFAutoStripBinding.java");
        defaultlistModel.addElement("JDFAutoStripBindingParams.java");
        defaultlistModel.addElement("JDFAutoStripCellParams.java");
        defaultlistModel.addElement("JDFAutoStripMark.java");
        defaultlistModel.addElement("JDFAutoStrippingParams.java");
        defaultlistModel.addElement("JDFAutoSubmissionMethods.java");
        defaultlistModel.addElement("JDFAutoSubscription.java");
        defaultlistModel.addElement("JDFAutoSystemTimeSet.java");
        defaultlistModel.addElement("JDFAutoTabs.java");
        defaultlistModel.addElement("JDFAutoTape.java");
        defaultlistModel.addElement("JDFAutoTest.java");
        defaultlistModel.addElement("JDFAutoTestPool.java");
        defaultlistModel.addElement("JDFAutoTestRef.java");
        defaultlistModel.addElement("JDFAutoThinPDFParams.java");
        defaultlistModel.addElement("JDFAutoThreadSealing.java");
        defaultlistModel.addElement("JDFAutoThreadSealingParams.java");
        defaultlistModel.addElement("JDFAutoThreadSewing.java");
        defaultlistModel.addElement("JDFAutoThreadSewingParams.java");
        defaultlistModel.addElement("JDFAutoTIFFEmbeddedFile.java");
        defaultlistModel.addElement("JDFAutoTIFFFormatParams.java");
        defaultlistModel.addElement("JDFAutoTIFFtag.java");
        defaultlistModel.addElement("JDFAutoTile.java");
        defaultlistModel.addElement("JDFAutoTool.java");
        defaultlistModel.addElement("JDFAutoTrackFilter.java");
        defaultlistModel.addElement("JDFAutoTrackResult.java");
        defaultlistModel.addElement("JDFAutoTransferCurve.java");
        defaultlistModel.addElement("JDFAutoTransferCurvePool.java");
        defaultlistModel.addElement("JDFAutoTransferCurveSet.java");
        defaultlistModel.addElement("JDFAutoTransferFunctionControl.java");
        defaultlistModel.addElement("JDFAutoTrappingDetails.java");
        defaultlistModel.addElement("JDFAutoTrappingOrder.java");
        defaultlistModel.addElement("JDFAutoTrappingParams.java");
        defaultlistModel.addElement("JDFAutoTrapRegion.java");
        defaultlistModel.addElement("JDFAutoTrigger.java");
        defaultlistModel.addElement("JDFAutoTrimmingParams.java");
        defaultlistModel.addElement("JDFAutoUpdateJDFCmdParams.java");
        defaultlistModel.addElement("JDFAutoUsageCounter.java");
        defaultlistModel.addElement("JDFAutoValue.java");
        defaultlistModel.addElement("JDFAutoValueLoc.java");
        defaultlistModel.addElement("JDFAutoVerificationParams.java");
        defaultlistModel.addElement("JDFAutoWakeUpCmdParams.java");
        defaultlistModel.addElement("JDFAutoWebInlineFinishingParams.java");
        defaultlistModel.addElement("JDFAutowhen.java");
        defaultlistModel.addElement("JDFAutoWireCombBinding.java");
        defaultlistModel.addElement("JDFAutoWireCombBindingParams.java");
        defaultlistModel.addElement("JDFAutoWrappingParams.java");
        defaultlistModel.addElement("JDFAutoxor.java");
        defaultlistModel.addElement("JDFAutoXYPairEvaluation.java");
    }
}



