/*
 * Created on Jul 12, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.generator.gui;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTextPane;

import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.datatypes.JDFAttributeMap;


/**
 * @author MatternK
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ResourceTextPane extends JTextPane implements ListDoubleClickedListener
{
    private static final long serialVersionUID = 1L;
    protected   Component motherComp;
    private     String    strStringToHighLight;
    private     String    strClickedAt;
    protected   String    fileSep = System.getProperty("file.separator");
    
    public ResourceTextPane(Component mother)
    {
        motherComp = mother;
        init();
    }
    
    public JTextPane getTextPane()
    {
        return this;
    }
    
    public void doSyntaxHighlighting()
    {
        new Thread()
        {
            @Override
			public void run()
            {
                setPriority(1);
                SyntaxHighlighter shl = new SyntaxHighlighter(getTextPane());
                shl.doSyntaxHighLight(getStringToHighlight());
            }
        }.start();
    }
    
    protected String getStringToHighlight()
    {
        return strStringToHighLight;
    }
    
    protected void setStringToHighlight(String s)
    {
        strStringToHighLight = s;
    }
    
    private void init()
    {
        setEditable(false);
        setHighlighter(null);
        ((GeneratorUI)motherComp).getComplexTypeList().addListDoubleClickedListener(this);
    }

    public void listDoubleClicked(ListDoubleClickedEvent e)
    {
        try
        {
            setNameClickPosition(e.getNameAtClickPosition());
            new Thread()
            {
                @Override
				public void run()
                {
                    String s        = getNameClickPosition();
                    String strName  = s.substring(7);
                    strName = strName.substring(0, strName.length() - 5);
                    ArrayList docs = ((GeneratorUI)motherComp).getComplexTypeList().getListButtonPanel().getSchemaDocs();
                    Thread loadThread = null;
                    if(docs.size() == 0)
                    {
                        String strSchemaLoc = ((GeneratorUI)motherComp).getDefaultSchemaLocation();
                        if(!strSchemaLoc.equals(""))
                        {
                            File schemaFile_JDFTypes         = new File(strSchemaLoc + fileSep + "JDFTypes.xsd");
                            File schemaFile_JDFResource      = new File(strSchemaLoc + fileSep + "JDFResource.xsd");
                            File schemaFile_JDFMessage       = new File(strSchemaLoc + fileSep + "JDFMessage.xsd");
                            File schemaFile_JDFCore          = new File(strSchemaLoc + fileSep + "JDFCore.xsd");
                            File schemaFile_JDFCapability    = new File(strSchemaLoc + fileSep + "JDFCapability.xsd");
                            
                            ArrayList schemaFiles = new ArrayList();
                            schemaFiles.add(schemaFile_JDFTypes);
                            schemaFiles.add(schemaFile_JDFResource);
                            schemaFiles.add(schemaFile_JDFMessage);
                            schemaFiles.add(schemaFile_JDFCore);
                            schemaFiles.add(schemaFile_JDFCapability);
                            schemaFiles.addAll(((GeneratorUI)motherComp).getExternalXSDs());
                            loadThread = ((GeneratorUI)motherComp).getComplexTypeList().getListButtonPanel().loadFiles(schemaFiles.toArray(), false);
                        }
                    }
                    if(loadThread != null)
                    {
                        while(loadThread.isAlive())
                        {
                            try
                            {
                                sleep(100);
                            }
                            catch(InterruptedException ire)
                            {
                                ire.hashCode(); //to remove ire is never read warning 
                            }
                        }
                    }
                    StringBuffer buff = new StringBuffer();
                    for(int j = 0; j < docs.size(); j++)
                    {
                        JDFDoc doc = (JDFDoc)docs.get(j);
                        if(doc != null)
                        {
                            KElement node = doc.getRoot();
                            VElement vComplexType   = node.getChildrenByTagName("xs:complexType", "", new JDFAttributeMap(), false, true, 0);
                            Vector v = getCorrelationNames(vComplexType, strName);
                            for(int i = 0; i < v.size(); i++)
                            {
                                buff.append(v.elementAt(i).toString());
                                buff.append("\n\n\n\n");
                            }
                        }
                    }
                    setStringToHighlight(buff.toString());
                    doSyntaxHighlighting();
                }
            }.start();
        }
        catch(NullPointerException ex)
        {
            ex.hashCode(); //to remove ex never read warning 
        }
    }
    
    private void setNameClickPosition(String s)
    {
        strClickedAt = s;
    }
    
    protected String getNameClickPosition()
    {
        return strClickedAt;
    }
    
    protected Vector getCorrelationNames(VElement vComplexType, String strName)
    {
        Vector v = new Vector();
        String strComplexTypeName;
        for(int i = 0; i < vComplexType.size(); i++)
        {
            KElement k = vComplexType.get(i);
            strComplexTypeName = k.getAttribute("name");
            int j = strComplexTypeName.indexOf(strName);
            if(j != -1)
            {
                v.add(k);
            }
                
        }
        return v;
    }
}
