/*
*
* The CIP4 Software License, Version 1.0
*
*
* Copyright (c) 2001-2006 The International Cooperation for the Integration of 
* Processes in  Prepress, Press and Postpress (CIP4).  All rights 
* reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
*
* 1. Redistributions of source code must retain the above copyright
*    notice, this list of conditions and the following disclaimer. 
*
* 2. Redistributions in binary form must reproduce the above copyright
*    notice, this list of conditions and the following disclaimer in
*    the documentation and/or other materials provided with the
*    distribution.
*
* 3. The end-user documentation included with the redistribution,
*    if any, must include the following acknowledgment:  
*       "This product includes software developed by the
*        The International Cooperation for the Integration of 
*        Processes in  Prepress, Press and Postpress (www.cip4.org)"
*    Alternately, this acknowledgment may appear in the software itself,
*    if and wherever such third-party acknowledgments normally appear.
*
* 4. The names "CIP4" and "The International Cooperation for the Integration of 
*    Processes in  Prepress, Press and Postpress" must
*    not be used to endorse or promote products derived from this
*    software without prior written permission. For written 
*    permission, please contact info@cip4.org.
*
* 5. Products derived from this software may not be called "CIP4",
*    nor may "CIP4" appear in their name, without prior written
*    permission of the CIP4 organization
*
* Usage of this software in commercial products is subject to restrictions. For
* details please consult info@cip4.org.
*
* THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
* OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
* THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
* ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
* SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
* LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
* USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
* OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
* OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
* SUCH DAMAGE.
* ====================================================================
*
* This software consists of voluntary contributions made by many
* individuals on behalf of the The International Cooperation for the Integration 
* of Processes in Prepress, Press and Postpress and was
* originally based on software 
* copyright (c) 1999-2001, Heidelberger Druckmaschinen AG 
* copyright (c) 1999-2001, Agfa-Gevaert N.V. 
*  
* For more information on The International Cooperation for the 
* Integration of Processes in  Prepress, Press and Postpress , please see
* <http://www.cip4.org/>.
*  
* 
*/
/*
 * Created on Jul 21, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.generator.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


/**
 * @author MatternK
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AddAutoFileDialog  extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private JLabel startOfFileName;
    private JLabel endOfFileName;
    
    private JButton but_add;
    private JButton but_close;
    
    private JTextField nameOfFile;
    
    public AddAutoFileDialog()
    {
        setModal(true);
        addWindowListener(new WindowAdapter()
                                {
                                    @SuppressWarnings("unused")
									public void WindowClosing()
                                    {
                                        dispose();
                                    }
                                }
        );
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("No double entry check is performed");
        init();
    }
    
    private void init()
    {
        GridBagLayout layout_gridBag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        getContentPane().setLayout(layout_gridBag);
        
        //setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        startOfFileName = new JLabel("JDFAuto");
        endOfFileName   = new JLabel(".java");
        nameOfFile      = new JTextField(40);
        but_add         = new JButton("Add filename");
        but_close       = new JButton("Close");
        
        but_add.addActionListener(this);
        but_close.addActionListener(this);
        nameOfFile.addActionListener(this);
        JPanel p        = new JPanel();
        
        p.setLayout(new GridLayout(0,2));
        
        gbc.gridwidth  = 1;
        layout_gridBag.setConstraints(startOfFileName, gbc);
        getContentPane().add(startOfFileName);
        
        gbc.gridwidth  = 4;
        layout_gridBag.setConstraints(nameOfFile, gbc);
        getContentPane().add(nameOfFile);
        
        gbc.gridwidth  = GridBagConstraints.REMAINDER;
        layout_gridBag.setConstraints(endOfFileName, gbc);
        getContentPane().add(endOfFileName);
        
        p.add(but_add); 
        p.add(but_close);
        
        gbc.gridwidth  = GridBagConstraints.REMAINDER;
        layout_gridBag.setConstraints(p, gbc);
        getContentPane().add(p);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == nameOfFile)
        {
            getTextFromTextField();
        }
        if(e.getSource() == but_add)
        {
            getTextFromTextField();
        }
        if(e.getSource() == but_close)
        {
            int iSize = ComplexTypeList.getDefaultListModel().size();
            Object[] objArray   = new Object[iSize];
            ComplexTypeList.getDefaultListModel().copyInto(objArray);
            Arrays.sort(objArray);
            ComplexTypeList.getDefaultListModel().removeAllElements();
            for(int i = 0; i < objArray.length; i++)
            {
                String strElementName = (String)objArray[i]; 
                ComplexTypeList.getDefaultListModel().addElement(strElementName);
            }
            dispose();
        }
    }

    /**
     * 
     */
    private void getTextFromTextField()
    {
        String s = nameOfFile.getText(); 
        nameOfFile.setText("");
        if(s.length() > 0)
        {    
            String filename = "JDFAuto" + s + ".java";
            ComplexTypeList.getDefaultListModel().addElement(filename);
            nameOfFile.requestFocus();
            setTitle(filename + " added to filelist");
        }
    }
    
    
}
