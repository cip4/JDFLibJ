/*
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
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFColorantControl.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoColorantControl;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.VString;
import org.w3c.dom.DOMException;


public class JDFColorantControl extends JDFAutoColorantControl
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for JDFColorantControl
     * @param ownerDocument
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFColorantControl(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    
    /**
     * Constructor for JDFColorantControl
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @throws DOMException
     */
    public JDFColorantControl(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFColorantControl
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     * @throws DOMException
     */
    public JDFColorantControl(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    throws DOMException
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFColorantControl[  --> " + super.toString() + " ]";
    }
    
    /** 
     * get a list of all partition keys that this resource may be implicitly partitioned by
     * e.g. RunIndex for RunList...
     *
     * @return vector of EnumPartIDKey
     */
    
    public Vector getImplicitPartitions()
    {
        Vector v = super.getImplicitPartitions();
        if(v==null)
            v=new Vector();
        v.add(EnumPartIDKey.Separation);
        return v;
    }
    
    /**
     * get the list of separations that this colorantcontrol
     * @return
     */
    public VString getSeparations()
    {
        VString vName = new VString();
        String model = getProcessColorModel();
        if ("DeviceCMY".equals(model))
        { 
            vName.add("Cyan");
            vName.add("Magenta");
            vName.add("Yellow");
        }
        else if ("DeviceCMYK".equals(model))
        {
            vName.add("Cyan");
            vName.add("Magenta");
            vName.add("Yellow");
            vName.add("Black");
        }
        else if ("DeviceGray".equals(model))
        {
            vName.add("Black");
        }
        else if ("DeviceRGB".equals(model))
        {
            vName.add("Red");
            vName.add("Green");
            vName.add("Blue");
        }
        else if ("DeviceN".equals(model))
        {
            vName = getDeviceNSpace(0).getSeparations();
        }
        
        JDFSeparationList colpar=getColorantParams();
        if(colpar!=null)
        {
            vName.appendUnique(colpar.getSeparations());
        }       
        return vName;
    }   
}
