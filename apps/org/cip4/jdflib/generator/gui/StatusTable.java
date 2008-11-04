/*
 * Created on Jul 13, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.generator.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * @author MatternK
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StatusTable extends JTable implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private Component motherComp; 
    private DefaultTableModel dtm;
    private JMenuItem itemClear;
    private JMenuItem itemClose;
    
    public StatusTable(Component mother)
    {
        motherComp = mother;
        init();
    }
    
    private void init()
    {
        dtm = new DefaultTableModel();
        dtm.setColumnCount(2);
        dtm.setColumnIdentifiers(new Object[] { "Activity", "Status" });
        setModel(dtm);
        
        getColumnModel().getColumn(0).setPreferredWidth(550);
        getColumnModel().getColumn(1).setPreferredWidth(50);
        
        setColumnSelectionAllowed(false);
        setRowSelectionAllowed(false);
        setEnabled(false);
        
        final JPopupMenu menu = new JPopupMenu();
        
        itemClear = new JMenuItem("Clear");
        itemClose = new JMenuItem("Exit");
        itemClear.addActionListener(this);
        itemClose.addActionListener(this);
        menu.add(itemClear);
        menu.add(itemClose);
        
        this.addMouseListener(new MouseAdapter() 
        {
            @Override
			public void mousePressed(MouseEvent evt) 
            {
                if (evt.isPopupTrigger()) 
                {
                    menu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
            @Override
			public void mouseReleased(MouseEvent evt) 
            {
                if (evt.isPopupTrigger()) 
                {
                    menu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        });
    }
    
    public JTable getGeneratorTable()
    {
        return this;
    }
    
    public DefaultTableModel getDefaultTableModel()
    {
        return dtm;
    }

 
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == itemClear)
        {
            while(dtm.getRowCount() != 0)
            {
                dtm.removeRow(0);
            }
            
        }
        if(e.getSource() == itemClose)
        {
            ((GeneratorUI)motherComp).saveProperties();
            System.exit(0);
            
        }
            
    }
}
