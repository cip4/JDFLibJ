/*
 * Created on Jul 19, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.generator.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/**
 * @author MatternK
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AddXSDDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private GeneratorUI motherComp;
    
    private JList list;
    private JButton but_add;
    private JButton but_remove;
    private JButton but_close;
    
    private final JFileChooser fc = new JFileChooser();
    DefaultListModel listmodel;
    
    public AddXSDDialog(GeneratorUI gui)
    {
        motherComp = gui;
        setModal(true);
        JFrame.setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        init();
    }
    
    private void init()
    {
        listmodel = new DefaultListModel();
        ArrayList xsdList = motherComp.getExternalXSDs();   
        for(int i = 0; i < xsdList.size(); i ++)
        {
            String actualLib = ((File)xsdList.get(i)).getAbsolutePath();
            File fileJar    = new File(actualLib);
            if(fileJar.canRead())
            {
                listmodel.addElement(((File)xsdList.get(i)).getAbsolutePath());
            }
        }
        
        list = new JList(listmodel);
        list.setCellRenderer(new MyCellRenderer());
        if(listmodel.size() > 0)
        {
            list.setVisibleRowCount(listmodel.size());
        }
        else
        {
            list.setPreferredSize(new Dimension(200, 400));
        }
        setSize(list.getSize());
        but_add     = new JButton("Add external xsd");
        but_remove  = new JButton("Remove selected xsd");
        but_close   = new JButton("Close");
        
        JPanel p1 = new JPanel(new GridLayout(0,2));
        p1.add(but_add);
        p1.add(but_remove);
        JPanel p2 = new JPanel(new GridLayout(0,1));
        p2.add(p1);
        p2.add(but_close);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(p2, BorderLayout.SOUTH);
        getContentPane().add(list, BorderLayout.CENTER);
        
        but_add.addActionListener(this);
        but_remove.addActionListener(this);
        but_close.addActionListener(this);
        
    }

    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == but_close)
        {
            dispose();
        }
        if(e.getSource() == but_remove)
        {
            int[] selection = list.getSelectedIndices();
            for(int i = selection.length; i > 0; i--)
            {
                motherComp.removeExternalXSDs(selection[i-1]); 
                listmodel.remove(selection[i-1]);
            }
        }
        if(e.getSource() == but_add)
        {
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY );
            fc.setMultiSelectionEnabled(true);
            fc.setFileFilter(new XSDFilter());
            
            int returnVal = fc.showOpenDialog(this);
            
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File[] files = fc.getSelectedFiles();
                String strFilePathandName = null;
                for(int i = 0; i < files.length; i++)
                {
                    if(files[i].isFile() && files[i].canRead() && files[i].getName().endsWith(".xsd"))
                    {
                        strFilePathandName = files[i].getAbsolutePath();
                        listmodel.addElement(strFilePathandName);
                        motherComp.addExternalXSD(files[i]);
                    }
                }
            }
            if(listmodel.size() > 0)
            {
                list.setVisibleRowCount(listmodel.size());
            }
            validate();
        }
        
    }
    
    static class MyCellRenderer extends DefaultListCellRenderer
    {
        private static final long serialVersionUID = 1L;

        @Override
		public void paint(Graphics g)
        {
            super.paint(g);
            int x           = getWidth();
            int y           = getHeight();
            Graphics2D  g2d = (Graphics2D)g;
            g2d.setColor(new Color(0xffeae9e2));
            g2d.drawLine(0, y-1, x, y-1);
        }
    }
        
    
    static class XSDFilter extends javax.swing.filechooser.FileFilter 
    {
        @Override
		public boolean accept(File f) 
        {
            boolean bRet = false; 
            if(f.getName().endsWith(".xsd")){ bRet = true; }
            else if(f.isDirectory()){ bRet = true; }
            return bRet;
        }
        @Override
		public String getDescription() 
        {
            return "XSD Files";
        }
    }
}
