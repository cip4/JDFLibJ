/*
*
* The CIP4 Software License, Version 1.0
*
*
* Copyright (c) 2001-2007 The International Cooperation for the Integration of 
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
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 * 
 * @author Elena Skobchenko
 *
 * JDFModulePool.java
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import java.util.HashMap;
import java.util.Map;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoModulePool;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.ifaces.ICapabilityElement;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap.EnumAvailability;


public class JDFModulePool extends JDFAutoModulePool
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFModulePool 
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFModulePool(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFModulePool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFModulePool(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFModulePool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFModulePool(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFModulePool[  --> " + super.toString() + " ]" ;
    }
    
    
    /**
     * get the minimum availability
     * @param vModuleRefs the list of module ids that are evaluated
     * @return the minimum availability, null in case of an error, for instance if no modulerefs are specified
     */
    public EnumAvailability getMinAvailability(VString vModuleRefs)
    {
        Map m=getModuleMap();
        if(vModuleRefs==null || m==null || vModuleRefs.size()==0)
            return null; // error exit
        JDFDeviceCap.EnumAvailability minAvail=JDFDeviceCap.EnumAvailability.Installed;
        for(int i=0;i<vModuleRefs.size();i++)
        {
            JDFModuleCap mc=(JDFModuleCap) m.get(vModuleRefs.stringAt(i));
            if(mc==null)
                return null;
            EnumAvailability a=mc.getAvailability();
            if(a==null || EnumAvailability.Module.equals(a)) // module is not valid recursively
                return null;
            if(minAvail.compareTo(a)>0)
                minAvail=a;
            
        }
        return minAvail;
    }
    
    /**
     * get a hashmap that uses ModuleCap/@ID as a key and has the ModuleCap as a value
     * @return the hashmap, null if no modulecaps exist
     */
    public Map getModuleMap()
    {
        VElement v=getChildElementVector(ElementName.MODULECAP, null, null, true, 0,true);
        int siz=v==null ? 0 : v.size();
        
        HashMap h=new HashMap();
        for(int i=0;i<siz;i++)
        {
            JDFModuleCap mc=(JDFModuleCap) v.elementAt(i);
            String id=mc.getID();
            if(!isWildCard(id))
            {
                h.put(id, mc);
            }
        }        
        return h.size()==0 ? null : h;
    }

    /**
     * get the module availability based on modulerefs and availability
     * @param caps either A State, devcap or devcaps
     * @return
     */
    public static EnumAvailability getModuleAvailability(ICapabilityElement caps)
    {
        EnumAvailability a=caps.getAvailability();
        if(!EnumAvailability.Module.equals(a))
            return a;
        JDFModulePool mp=caps.getModulePool();
        if(mp==null)
            return null;
        return mp.getMinAvailability(caps.getModuleRefs());
    }
    
    /**
     * @param caps the element to append the ref to
     * 
     * @return {@link JDFModuleCap} the Modulecap that id refers tp
     */
    public static JDFModuleCap appendModuleRef(ICapabilityElement caps, String id)
    {
        caps.setAvailability(EnumAvailability.Module);
        JDFModulePool mp=caps.getCreateModulePool();
        ((KElement)caps).appendAttribute(AttributeName.MODULEREFS, id, null, null, true);
        return mp.getCreateModuleCap(id);
    }

    /**
     * get a modulecap with a given id
     * 
     * @param id the modulecap id
     * @return the modulecap
     */
    public JDFModuleCap getModuleCap(String id)
    {
        return (JDFModuleCap) getChildWithAttribute(ElementName.MODULECAP, AttributeName.ID, null, id, 0, true);
    }
    
    /**
     * get a modulecap with a given id, create it if it does not exist
     * 
     * @param id the modulecap id
     * @return the modulecap
     */
    public JDFModuleCap getCreateModuleCap(String id)
    {
        JDFModuleCap mc=getModuleCap(id);
        if(mc==null)
        {
            mc=appendModuleCap();
            mc.setID(id);
        }
        return mc;
    }
 
}



