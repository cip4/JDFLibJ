/*
 * Created on Jul 14, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.cip4.jdflib.generator.gui;

import java.util.EventObject;



/**
 * @author MatternK
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ListDoubleClickedEvent extends EventObject
{
    private static final long serialVersionUID = 1L;
    private String strNameAtClickPosition;
    
    public ListDoubleClickedEvent(ComplexTypeList eventSource, String strName)
    {
        super(eventSource);
        strNameAtClickPosition = strName;
    }
    
    public String getNameAtClickPosition()
    {
        return strNameAtClickPosition;
    }
}
