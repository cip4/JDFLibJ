/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2005 The International Cooperation for the Integration of
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

package org.cip4.jdflib.auto;

import java.util.Collection;                        
import java.util.Vector;                            
import org.apache.xerces.dom.CoreDocumentImpl;      
import org.cip4.jdflib.core.*;                      
import org.cip4.jdflib.resource.process.*;

public abstract class JDFAutoStation extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[3];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ASSEMBLYIDS, 0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.STATIONAMOUNT, 0x33333111, AttributeInfo.EnumAttributeType.integer, null, "1");
        atrInfoTable[2] = new AtrInfoTable(AttributeName.STATIONNAME, 0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
    }
    
    @Override
	protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }


    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.SHAPEDEF, 0x33331111);
    }
    
    @Override
	protected ElementInfo getTheElementInfo()
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }



    /**
     * Constructor for JDFAutoStation
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoStation(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoStation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoStation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoStation
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoStation(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    @Override
	public String toString()
    {
        return " JDFAutoStation[  --> " + super.toString() + " ]";
    }


/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute AssemblyIDs
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AssemblyIDs
          * @param value: the value to set the attribute to
          */
        public void setAssemblyIDs(VString value)
        {
            setAttribute(AttributeName.ASSEMBLYIDS, value, null);
        }

        /**
          * (21) get VString attribute AssemblyIDs
          * @return VString the value of the attribute
          */
        public VString getAssemblyIDs()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.ASSEMBLYIDS, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StationAmount
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StationAmount
          * @param value: the value to set the attribute to
          */
        public void setStationAmount(int value)
        {
            setAttribute(AttributeName.STATIONAMOUNT, value, null);
        }

        /**
          * (15) get int attribute StationAmount
          * @return int the value of the attribute
          */
        public int getStationAmount()
        {
            return getIntAttribute(AttributeName.STATIONAMOUNT, null, 1);
        }

        
        /* ---------------------------------------------------------------------
        Methods for Attribute StationName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StationName
          * @param value: the value to set the attribute to
          */
        public void setStationName(String value)
        {
            setAttribute(AttributeName.STATIONNAME, value, null);
        }

        /**
          * (23) get String attribute StationName
          * @return the value of the attribute
          */
        public String getStationName()
        {
            return getAttribute(AttributeName.STATIONNAME, null, JDFConstants.EMPTYSTRING);
        }

/* ***********************************************************************
 * Element getter / setter
 * ***********************************************************************
 */

    /** (26) getCreateShapeDef
     * 
     * @param iSkip number of elements to skip
     * @return JDFShapeDef the element
     */
    public JDFShapeDef getCreateShapeDef(int iSkip)
    {
        return (JDFShapeDef)getCreateElement_KElement(ElementName.SHAPEDEF, null, iSkip);
    }

    /**
     * (27) const get element ShapeDef
     * @param iSkip number of elements to skip
     * @return JDFShapeDef the element
     * default is getShapeDef(0)     */
    public JDFShapeDef getShapeDef(int iSkip)
    {
        return (JDFShapeDef) getElement(ElementName.SHAPEDEF, null, iSkip);
    }

    /**
     * Get all ShapeDef from the current element
     * 
     * @return Collection<JDFShapeDef>, null if none are available
     */
    public Collection<JDFShapeDef> getAllShapeDef()
    {
        final VElement vc = getChildElementVector(ElementName.SHAPEDEF, null);
        if (vc == null || vc.size() == 0)
        {
            return null;
        }

        final Vector<JDFShapeDef> v = new Vector<JDFShapeDef>();
        for (int i = 0; i < vc.size(); i++)
        {
            v.add((JDFShapeDef) vc.get(i));
        }

        return v;
    }

    /**
     * (30) append element ShapeDef
     */
    public JDFShapeDef appendShapeDef() throws JDFException
    {
        return (JDFShapeDef) appendElement(ElementName.SHAPEDEF, null);
    }

    /**
      * (31) create inter-resource link to refTarget
      * @param refTarget the element that is referenced
      */
    public void refShapeDef(JDFShapeDef refTarget)
    {
        refElement(refTarget);
    }

}// end namespace JDF
