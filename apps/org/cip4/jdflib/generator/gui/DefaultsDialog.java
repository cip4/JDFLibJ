

package org.cip4.jdflib.generator.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;



class DefaultsDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private JButton but_SchemaPath;
    private JButton but_OutputPath;
    private JButton but_serializePath;
    private JButton but_fileListPath;
    
    protected JTextField textF_SchemaPath;
    protected JTextField textF_OutputPath;
    protected JTextField textF_serializedPath;
    protected JTextField textF_FileListPath;
    
    private JComboBox versionList;
    
    private final JFileChooser fc = new JFileChooser();
    
    protected String strSchemaLocation;
    protected String strOutputLocation;
    protected String strSerializeLocation;
    protected String strFileListPath;
    
    private String strBuffSchemaLoc;
    private String strBuffOutputLoc;
    private String strBuffSerializeLoc;
    private String strBuffFileListLoc;
    
    public DefaultsDialog(Frame aFrame, String[] paths, String strVer)
    {
        super(aFrame, true);
        
        strBuffSchemaLoc    = paths[0];
        strBuffOutputLoc    = paths[1];
        strBuffSerializeLoc = paths[2];
        strBuffFileListLoc  = paths[3];
        
        setTitle("Default values for input, output and schema paths");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //setUndecorated(true);
        getContentPane().setLayout(new BorderLayout());
        
        JPanel p = new JPanel(); 
        GridBagLayout layout_gridBag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        p.setLayout(layout_gridBag);
        //setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        
        final JLabel jlabel_SchemaPath      = new JLabel("Schema Path: ");
        final JLabel jlabel_SchemaVersion   = new JLabel("Schema Version: ");
        final JLabel jlabel_OutputPath      = new JLabel("Output Path: ");
        final JLabel jLabel_SerializePath   = new JLabel("Serialize Path: ");
        final JLabel jLabel_FileListPath    = new JLabel("FileList Path: ");
        
        strSchemaLocation       = paths[0];
        strOutputLocation       = paths[1];
        strSerializeLocation    = paths[2];
        strFileListPath         = paths[3];
        
        textF_SchemaPath       = new JTextField(40);
        textF_SchemaPath.setText(strSchemaLocation);
        textF_SchemaPath.setCaretPosition(0);
        textF_SchemaPath.setToolTipText(strSchemaLocation);
        textF_OutputPath       = new JTextField(40);
        textF_OutputPath.setText(strOutputLocation);
        textF_OutputPath.setCaretPosition(0);
        textF_OutputPath.setToolTipText(strOutputLocation);
        textF_FileListPath    = new JTextField(40);
        textF_FileListPath.setText(strFileListPath);
        textF_FileListPath.setCaretPosition(0);
        textF_FileListPath.setToolTipText(strOutputLocation);
        textF_serializedPath   = new JTextField(40);
        textF_serializedPath.setText(strSerializeLocation);
        textF_serializedPath.setCaretPosition(0);
        textF_serializedPath.setToolTipText(strSerializeLocation);
        
        but_SchemaPath    = new JButton("Browse...");
        but_OutputPath    = new JButton("Browse...");
        but_serializePath = new JButton("Browse...");
        but_fileListPath  = new JButton("Browse...");
        
        but_SchemaPath.addActionListener(this);
        but_OutputPath.addActionListener(this);
        but_serializePath.addActionListener(this);
        but_fileListPath.addActionListener(this);
        
        String[] strVersions        = { "Version 20", 
                                        "Version 21",
                                        "Version 22",
                                        "Version 23",
                                        "Version 24",
                                        "Version 25",
                                        "Version 26",
                                        "Version 27"};
        
        versionList                 = new JComboBox(strVersions);
        
        int iSelected = strVersions.length - 1; //latest Version as default 
        for(int i = 0; i < strVersions.length; i++)
        {
            if(strVersions[i].endsWith(strVer))
            {
                iSelected = i;
            }
        }
        versionList.setSelectedIndex(iSelected);
     
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        
        gbc.gridwidth  = 1;
        gbc.gridheight = 1;
        layout_gridBag.setConstraints(jlabel_SchemaPath, gbc);
        p.add(jlabel_SchemaPath);
        
        
        gbc.gridwidth  = 4;
        layout_gridBag.setConstraints(textF_SchemaPath, gbc);
        p.add(textF_SchemaPath);
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        layout_gridBag.setConstraints(but_SchemaPath, gbc);
        p.add(but_SchemaPath);
        
        gbc.gridwidth  = 1;
        layout_gridBag.setConstraints(jlabel_SchemaVersion, gbc);
        p.add(jlabel_SchemaVersion);
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        layout_gridBag.setConstraints(versionList, gbc);
        p.add(versionList);
        
        gbc.gridwidth  = 1;
        layout_gridBag.setConstraints(jlabel_OutputPath, gbc);
        p.add(jlabel_OutputPath);
        gbc.gridwidth  = 4;
        layout_gridBag.setConstraints(textF_OutputPath, gbc);
        p.add(textF_OutputPath);
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        layout_gridBag.setConstraints(but_OutputPath, gbc);
        p.add(but_OutputPath);
        
        
        
        gbc.gridwidth  = 1;
        layout_gridBag.setConstraints(jLabel_FileListPath, gbc);
        p.add(jLabel_FileListPath);
        
        gbc.gridwidth  = 4;
        layout_gridBag.setConstraints(textF_FileListPath, gbc);
        p.add(textF_FileListPath);
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        layout_gridBag.setConstraints(but_fileListPath, gbc);
        p.add(but_fileListPath);
        
        
        
        
        
        gbc.gridwidth  = 1;
        layout_gridBag.setConstraints(jLabel_SerializePath, gbc);
        p.add(jLabel_SerializePath);
        
        gbc.gridwidth  = 4;
        layout_gridBag.setConstraints(textF_serializedPath, gbc);
        p.add(textF_serializedPath);
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        layout_gridBag.setConstraints(but_serializePath, gbc);
        p.add(but_serializePath);
        
        
        JPanel seperator = new JPanel();
        seperator.setBackground(new Color(0xFDFFF3));
        seperator.setForeground(new Color(0xFDFFF3));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        layout_gridBag.setConstraints(seperator, gbc);
        p.add(seperator);

        JPanel but_panel = new JPanel();
        but_panel.setLayout(new GridLayout(0, 2));
        JButton jbut_Apply  = new JButton("Apply");
        JButton jbut_Cancel = new JButton("Cancel");
        
        but_panel.add(jbut_Apply);
        but_panel.add(jbut_Cancel);
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        layout_gridBag.setConstraints(but_panel, gbc);
        p.add(but_panel);

        
        this.getContentPane().add(p);
        
        jbut_Apply.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                e.getID(); //to remove e never used warning
                strSchemaLocation       = textF_SchemaPath.getText();
                strOutputLocation       = textF_OutputPath.getText();
                strSerializeLocation    = textF_serializedPath.getText();
                strFileListPath        = textF_FileListPath.getText();
                setVisible(false);
            }
           
        });
        
        jbut_Cancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                e.getID(); //to remove e never used warning
                setVisible(false);
            }
        });
        
    }
    
    public String[] getNewlySelectedPaths()
    {
        String[] paths  = new String[5];
        paths[0]        = strSchemaLocation;
        paths[1]        = strOutputLocation;
        paths[2]        = strSerializeLocation;
        paths[3]        = (String)versionList.getSelectedItem();
        paths[4]        = strFileListPath;
        
        return paths;
    }

    public void actionPerformed(ActionEvent e)
    {
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        if(e.getSource() == but_SchemaPath)
        {
            fc.setDialogTitle("Set path to schema");
            fc.setCurrentDirectory(new File(strSchemaLocation));
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File schemaDir = fc.getSelectedFile();
                 
                if(schemaDir.canRead())
                {
                    strBuffSchemaLoc = schemaDir.getAbsolutePath();
                    textF_SchemaPath.setText(strBuffSchemaLoc);
                }
            }
        }
        if(e.getSource() == but_OutputPath)
        {
            fc.setDialogTitle("Set path to ouput folder");
            fc.setCurrentDirectory(new File(strSchemaLocation));
            int returnVal = fc.showOpenDialog(this);
            
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File schemaDir = fc.getSelectedFile();
                 
                if(schemaDir.canWrite())
                {
                    strBuffOutputLoc = schemaDir.getAbsolutePath();
                    textF_OutputPath.setText(strBuffOutputLoc);
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "Cant write to " + schemaDir.getAbsolutePath(),
                            "Write access error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(e.getSource() == but_serializePath)
        {
            fc.setDialogTitle("Set path for serialized data");
            fc.setCurrentDirectory(new File(strSchemaLocation));
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File schemaDir = fc.getSelectedFile();
                 
                if(schemaDir.canRead())
                {
                    strBuffSerializeLoc = schemaDir.getAbsolutePath();
                    textF_serializedPath.setText(strBuffSerializeLoc);
                }
            }
        }
        if(e.getSource() == but_fileListPath)
        {
            fc.setDialogTitle("Set path to saved file list (javac)");
            fc.setCurrentDirectory(new File(strFileListPath));
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                File fileList = fc.getSelectedFile();
                 
                if(fileList.canRead())
                {
                    strBuffFileListLoc = fileList.getAbsolutePath();
                    textF_FileListPath.setText(strBuffFileListLoc);
                }
            }
            
        }
    }
}