/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress (CIP4). All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must include the
 * following acknowledgment: "This product includes software developed by the The International
 * Cooperation for the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)"
 * Alternately, this acknowledgment may appear in the software itself, if and wherever such
 * third-party acknowledgments normally appear.
 * 
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress" must not be used to endorse or promote products derived from this
 * software without prior written permission. For written permission, please contact info@cip4.org.
 * 
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their
 * name, without prior written permission of the CIP4 organization
 * 
 * Usage of this software in commercial products is subject to restrictions. For details please
 * consult info@cip4.org.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN
 * PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The
 * International Cooperation for the Integration of Processes in Prepress, Press and Postpress and
 * was originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in
 * Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 *//**
 *
 * Copyright (c) 2001-2005 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFResourceLink.java
 *
     * Last changes 2002-07-02 JG - added Get/SetProcessUsage 2002-07-02 JG - MyString -> KString :
     * all strings now 16 bit 2002-07-02 JG - now inherits from JDFElement 2002-07-02 JG -
     * GetProcessUsage and GetLinkedResourceName are now 2 sepaarte functions 2002-07-02 JG -
     * completely removed selector handling 2002-07-02 JG - HasResourcePartMap bug fix if no parts
     * in this - now returns true for no parts in this 2002-07-02 JG - removed JDFResource
     * GetPartition(boolean bCreate=false, int i=0); 2002-07-02 JG - added AppendPart 2002-07-02 JG -
     * added CombinedProcessIndex, PipeProtocol support 2002-07-02 JG - added AmountPool 2002-07-02
     * JG - added Transformation + Orientation support 2002-07-02 JG - removed GetAmount(boolean
     * bSelector) 2002-07-02 JG - removed GetPartTarget(int iPart=0,int iSelector=-1); 2002-07-02 JG -
     * modified GetNamedProcessUsage to default to xxx:Input / xxx:Output respectively 2002-07-02 JG -
     * SetPartition() now uses JDFResource::EnumPartIDKey 2002-07-02 JG - added GetTarget 2002-07-02
     * JG - GetTargetVector is now const 2002-07-02 JG - added GetTarget() 22-10-2003 KM -
     * IsExecutable() added bCheckChildren 22-10-2003 KM - IsExecutable() fixed bCheckChildren
     * 22-10-2003 KM - GetTarget() now returns the lowest common denominator resource if all leaves
     * are available
 */

package org.cip4.jdflib.core;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerList;
import org.cip4.jdflib.datatypes.JDFMatrix;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode.EnumProcessUsage;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumPartIDKey;
import org.cip4.jdflib.resource.JDFResource.EnumResStatus;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;


public class JDFResourceLink extends JDFElement
{
    private static final long serialVersionUID = 1L;
    
    private static AtrInfoTable[] atrInfoTable_Abstract = new AtrInfoTable[12];
    static 
    {
        atrInfoTable_Abstract[0] = new AtrInfoTable(AttributeName.COMBINEDPROCESSINDEX, 0x33333331,
                AttributeInfo.EnumAttributeType.IntegerList, null, null);
        atrInfoTable_Abstract[1] = new AtrInfoTable(AttributeName.COMBINEDPROCESSTYPE, 0x44444443,
                AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_Abstract[2] = new AtrInfoTable(AttributeName.DRAFTOK, 0x44444333,
                AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable_Abstract[3] = new AtrInfoTable(AttributeName.MINLATESTATUS, 0x33333111,
                AttributeInfo.EnumAttributeType.enumeration, EnumResStatus.getEnum(0), null);
        atrInfoTable_Abstract[4] = new AtrInfoTable(AttributeName.MINSTATUS, 0x33333111,
                AttributeInfo.EnumAttributeType.enumeration, EnumResStatus.getEnum(0), null);
        atrInfoTable_Abstract[5] = new AtrInfoTable(AttributeName.PIPEPARTIDKEYS, 0x33333333,
                AttributeInfo.EnumAttributeType.enumerations, EnumPartIDKey.getEnum(0), null);
        atrInfoTable_Abstract[6] = new AtrInfoTable(AttributeName.PIPEPROTOCOL, 0x33333331,
                AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_Abstract[7] = new AtrInfoTable(AttributeName.PIPEURL, 0x33333333,
                AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable_Abstract[8] = new AtrInfoTable(AttributeName.PROCESSUSAGE, 0x33333333,
                AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Abstract[9] = new AtrInfoTable(AttributeName.RREF, 0x22222222,
                AttributeInfo.EnumAttributeType.IDREF, null, null);
        atrInfoTable_Abstract[10] = new AtrInfoTable(AttributeName.RSUBREF, 0x44444433,
                AttributeInfo.EnumAttributeType.IDREF, null, null);
        atrInfoTable_Abstract[11] = new AtrInfoTable(AttributeName.USAGE, 0x22222222,
                AttributeInfo.EnumAttributeType.enumeration, EnumUsage.getEnum(0), null);
    }
    
    private static AtrInfoTable[] atrInfoTable_Physical = new AtrInfoTable[10];
    static 
    {
        atrInfoTable_Physical[1] = new AtrInfoTable(AttributeName.ACTUALAMOUNT, 0x33333311,
                AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[0] = new AtrInfoTable(AttributeName.AMOUNT, 0x33333333,
                AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[9] = new AtrInfoTable(AttributeName.MAXAMOUNT, 0x33333111,
                AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[8] = new AtrInfoTable(AttributeName.MINAMOUNT, 0x33333111,
                AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[3] = new AtrInfoTable(AttributeName.ORIENTATION, 0x33333331,
                AttributeInfo.EnumAttributeType.enumeration, EnumOrientation.getEnum(0), null);
        atrInfoTable_Physical[4] = new AtrInfoTable(AttributeName.PIPEPAUSE, 0x33333333,
                AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[5] = new AtrInfoTable(AttributeName.PIPERESUME, 0x33333333,
                AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[6] = new AtrInfoTable(AttributeName.REMOTEPIPEENDPAUSE, 0x33333333,
                AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[7] = new AtrInfoTable(AttributeName.REMOTEPIPEENDRESUME, 0x33333333,
                AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[2] = new AtrInfoTable(AttributeName.TRANSFORMATION, 0x33333331,
                AttributeInfo.EnumAttributeType.matrix, null, null);
    }
    
    private static AtrInfoTable[] atrInfoTable_Implement = new AtrInfoTable[4];
    static 
    {
        atrInfoTable_Implement[0] = new AtrInfoTable(AttributeName.DURATION, 0x33333333,
                AttributeInfo.EnumAttributeType.duration, null, null);
        atrInfoTable_Implement[1] = new AtrInfoTable(AttributeName.RECOMMENDATION, 0x44444433,
                AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable_Implement[2] = new AtrInfoTable(AttributeName.START, 0x33333333,
                AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable_Implement[3] = new AtrInfoTable(AttributeName.STARTOFFSET, 0x33333333,
                AttributeInfo.EnumAttributeType.duration, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo() 
    {
        AttributeInfo ai = super.getTheAttributeInfo().updateReplace(atrInfoTable_Abstract);
        if (isPhysical() || getLocalName().equals(ElementName.PARTAMOUNT))
        {
            ai.updateAdd(atrInfoTable_Physical);
        }
        else if (isImplementation() || getLocalName().equals(ElementName.PARTAMOUNT)) 
        {
            ai.updateAdd(atrInfoTable_Implement);
        }
        return ai;
    }
    
    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[2];
    static 
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.AMOUNTPOOL, 0x66666661);
        elemInfoTable[1] = new ElemInfoTable(ElementName.PART,       0x33333333);
    }
    
    private static ElemInfoTable[] physInfoTable = new ElemInfoTable[1];
    static 
    {
        physInfoTable[0] = new ElemInfoTable(ElementName.LOT,  0x33333111);        
        elemInfoTable[1] = new ElemInfoTable(ElementName.PART, 0x33333333);
    }
 
    ////////////////////////////////////////////////////////////////
    
    protected ElementInfo getTheElementInfo() 
    {
        ElementInfo ei = super.getTheElementInfo().updateReplace(elemInfoTable);
        if (this.isPhysical())
            ei.updateAdd(physInfoTable);
        return ei;
    }
    
    ////////////////////////////////////////////////////////////////
    
    /**
     * Constructor for JDFResourceLink
     * 
     * @param ownerDocument
     * @param qualifiedName
     */
    public JDFResourceLink (CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }
    
    /**
     * Constructor for JDFResourceLink
     * 
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     */
    public JDFResourceLink (CoreDocumentImpl myOwnerDocument, String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }
    
    /**
     * Constructor for JDFResourceLink
     * 
     * @param ownerDocument
     * @param namespaceURI
     * @param qualifiedName
     * @param localName
     */
    public JDFResourceLink (CoreDocumentImpl myOwnerDocument, String myNamespaceURI,
            String qualifiedName, String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }
    
    public static final class EnumOrientation extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;

        private static int m_startValue = 0;
        
        private EnumOrientation (String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumOrientation getEnum(String enumName)
        {
            return (EnumOrientation) getEnum(EnumOrientation.class, enumName);
        }
        
        public static EnumOrientation getEnum(int enumValue)
        {
            return (EnumOrientation) getEnum(EnumOrientation.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumOrientation.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumOrientation.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumOrientation.class);
        }
        
        public static final EnumOrientation Flip0 = new EnumOrientation("Flip0");

        public static final EnumOrientation Flip90 = new EnumOrientation("Flip90");

        public static final EnumOrientation Flip180 = new EnumOrientation("Flip180");

        public static final EnumOrientation Flip270 = new EnumOrientation("Flip270");

        public static final EnumOrientation Rotate0 = new EnumOrientation("Rotate0");

        public static final EnumOrientation Rotate90 = new EnumOrientation("Rotate90");

        public static final EnumOrientation Rotate180 = new EnumOrientation("Rotate180");

        public static final EnumOrientation Rotate270 = new EnumOrientation("Rotate270");

        public static final EnumOrientation Matrix = new EnumOrientation("Matrix");
    }
    
    public static final class EnumUsage extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;

        private static int m_startValue = 0;
        
        private EnumUsage (String name)
        {
            super(name, m_startValue++);
        }
        
        public static EnumUsage getEnum(String enumName)
        {
            return (EnumUsage) getEnum(EnumUsage.class, enumName);
        }
        
        public static EnumUsage getEnum(int enumValue)
        {
            return (EnumUsage) getEnum(EnumUsage.class, enumValue);
        }
        
        public static Map getEnumMap()
        {
            return getEnumMap(EnumUsage.class);
        }
        
        public static List getEnumList()
        {
            return getEnumList(EnumUsage.class);
        }
        
        public static Iterator iterator()
        {
            return iterator(EnumUsage.class);
        }

        /**
         * @deprecated use getEnumList
         * @return
         */
        public static Vector getNamesVector()
        {
            final Vector namesVector = new Vector();
            final Iterator it = iterator(EnumUsage.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        public static final EnumUsage Input = new EnumUsage("Input");

        public static final EnumUsage Output = new EnumUsage("Output");
        
    }
    
    /**
     * Enumeration strings for Usage
     * 
     * @deprecated
     */
    public static String usageString()
    {
        String s = JDFConstants.EMPTYSTRING;
        final Vector namesVector = EnumUsage.getNamesVector();
        for (int i = 0; i < namesVector.size(); i++)
        {
            s += JDFConstants.COMMA + (String) namesVector.elementAt(i);
        }
        return s.equals(JDFConstants.EMPTYSTRING) ? null : s;
    }
    
    // **************************************** Methods
    // *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFResourceLink[ --> " + super.toString() + " ]";
    }
    
    /**
     * version fixing routine for JDF
     *
     * uses heuristics to modify this element and its children to be compatible with a given version
     * in general, it will be able to move from low to high versions but potentially fail when
     * attempting to move from higher to lower versions
     *
     * @param version:
     *            version that the resulting element should correspond to
     * @return true if successful
     */
    public boolean fixVersion(EnumVersion version)
    {
        boolean bRet = true;
        if (version != null)
        {
            if (version.getValue() >= EnumVersion.Version_1_3.getValue())
            {
                if (hasAttribute(AttributeName.DRAFTOK))
                {
                    if (!hasAttribute(AttributeName.MINSTATUS))
                        setMinStatus(EnumResStatus.Draft);
                    removeAttribute(AttributeName.DRAFTOK);
                }
            }
            else
            {
                if (hasAttribute(AttributeName.MINSTATUS))
                {
                    if (!hasAttribute(AttributeName.DRAFTOK))
                        setDraftOK(true);
                    removeAttribute(AttributeName.MINSTATUS);
                }
                removeAttribute(AttributeName.MINLATESTATUS);            
            }
        }
        return super.fixVersion(version) && bRet;
        
    }

    /**
     * setTarget - sets the link to the target defined by partLeaf. Automatically generates a part
     * subelement, if partleaf is not the root resource
     * 
     * @param resourceTarget the resource that this ResourceLink shoud refer to
     *
     * @throws JDFException if an attempt is made to link to a resource sub-element
     * @return boolean - always true
     */
    public boolean setTarget(JDFResource resourceTarget)
    {
        if(resourceTarget.isResourceElement())
            throw new JDFException("attempting to link to a resource subelement");
        
        appendHRef(resourceTarget.getResourceRoot(), JDFConstants.RREF, null);
        
        if (!resourceTarget.isResourceRoot())
        {
            removeChildren(ElementName.PART, null, null);
            final JDFAttributeMap mPart = resourceTarget.getPartMap();
            if (mPart != null && mPart.size() > 0)
            {
                final JDFElement part = appendPart();
                part.setAttributes(mPart);
            }
        }
        
        return true;
    }
    
    /**
     * get double attribute Amount defaults to the value of Amount for the linked partition
     * 
     * @param mPart
     *            partition map to retrieve Amount for
     * @return the amount, -1 if none is specified
     * 
     * @default getAmount(null)
     */
    public double getAmount(JDFAttributeMap mPart)
    {
        final String w = getAmountPoolAttribute(AttributeName.AMOUNT, null, mPart);
        if (w==null)
        {
            JDFResource target = getTarget();
            if (target != null)
            {
                target = target.getPartition(mPart, null);
                if (target != null)
                    return target.getAmount();
            }
        }
        else
        {
            return StringUtil.parseDouble(w, -1.);
        }
        
        return -1.;
    }
    
    /**
     * get double attribute MinAmount, defaults to getAmount if MinAmount is not set
     * 
     * @param mPart
     *            partition map to retrieve MinAmount for
     * @return the MinAmount value
     * @default getAmount(null)
     */
    public double getMinAmount(JDFAttributeMap mPart)
    {
        String s = getAmountPoolAttribute(AttributeName.MINAMOUNT, null, mPart);
        if (s==null)
            return getAmount(mPart);
        return StringUtil.parseDouble(s, -1.);
    }

    /**
     * get double attribute MaxAmount, defaults to getAmount if MinAmount is not set
     * 
     * @param mPart
     *            partition map to retrieve MaxAmount for
     * @return the MaxAmount value
     * @default getAmount(null)
     */
    public double getMaxAmount(JDFAttributeMap mPart)
    {
        String s = getAmountPoolAttribute(AttributeName.MAXAMOUNT, null, mPart);
        if (s==null)
            return getAmount(mPart);
        return StringUtil.parseDouble(s, -1.);
    }
    
    /**
     * getLinkRoot - gets the root resource of the target
     *
     * @return JDFResource
     */
    public JDFResource getLinkRoot()
    {
        if (getLocalName().equals(ElementName.PARTAMOUNT))
            return null;
        
        JDFResource r = null;
        JDFResource eLink = super.getLinkRoot(null);
        if (eLink != null)
        {
            r = eLink;
            
            if (!(r.getNodeName().equals(getLinkedResourceName())))
            {
                return null;
            }
        }
        return r;
    }
    
    /**
     * getLinkTarget
     *
     * @return JDFResource
     * @deprecated - never used
     */
    public JDFResource getLinkTarget()
    {
        return getTarget();
    }
    
    /**
     * setQuantity
     *
     * @param int
     *            quant
     */
    public void setQuantity(int quant)
    {
        setAttribute(AttributeName.AMOUNT, quant, null);
    }
    
    /**
     * setAmount in PartAmount or in this if partAmount=null
     *
     * @param amount
     * @param mPart
     *            partition map to set amount for
     * 
     * @default setAmount(double value, null)
     */
    public void setAmount(double value, JDFAttributeMap mPart)
    {
        setAmountPoolAttribute(AttributeName.AMOUNT, StringUtil.formatDouble(value), null, mPart);
    }
    
    /**
     * set MinAmount in PartAmount or in this if partAmount=null
     *
     * @param amount
     * @param mPart
     *            partition map to set amount for
     * 
     * @default setAmount(double value, null)
     */
    public void setMinAmount(double value, JDFAttributeMap mPart)
    {
        setAmountPoolAttribute(AttributeName.MINAMOUNT, StringUtil.formatDouble(value), null, mPart);
    }
    
    /**
     * set MaxAmount in PartAmount or in this if partAmount=null
     *
     * @param amount
     * @param mPart
     *            partition map to set amount for
     * 
     * @default setAmount(double value, null)
     */
    public void setMaxAmount(double value, JDFAttributeMap mPart)
    {
        
        setAmountPoolAttribute(AttributeName.MAXAMOUNT, StringUtil.formatDouble(value), null, mPart);
    }
    
    /**
     * getStatus
     *
     * @return JDFResource.EnumResStatus
     */
    public JDFResource.EnumResStatus getStatusJDF()
    {
        return JDFResource.EnumResStatus.getEnum(getLinkRoot().getResStatus(false).getName());
    }
    
    /**
     * setStatus
     *
     * @param JDFResource.EnumResStatus
     *            s
     */
    public void setStatus(JDFResource.EnumResStatus s)
    {
        final VElement targets = getTargetVector(-1);
        for (int i = 0; i < targets.size(); i++)
        {
            ((JDFResource) targets.elementAt(i)).setResStatus(s, true);
        }
    }
    
    /**
     * IsLocal
     *
     * @return boolean
     */
    public boolean isLocal()
    {
        final JDFElement linkParent = getParentJDF();
        final JDFElement resParent = getTarget().getParentJDF();
        return resParent.isEqual(linkParent);
    }
    
    /**
     * getPart
     *
     * @param int
     *            i
     *
     * @return JDFResource
     * 
     * default: getPart(0)
     */
    public JDFPart getPart(int i)
    {
        return (JDFPart) getElement(ElementName.PART, null, i);
    }
    
    /**
     * getCreatePart
     *
     * @param int
     *            i
     *
     * @return JDFResource
     * 
     * default: getCreatePart(0)
     */
    public JDFPart getCreatePart(int i)
    {
        return (JDFPart) getCreateElement_KElement(ElementName.PART, null, i);
    }
    
    /**
     * getAuditString
     *
     * @return String
     */
    public String getAuditString()
    {
        final String s = getNodeName();
        return s.substring(3, s.length()) + JDFConstants.AUDIT;
    }
    
    /**
     * getParts - get the vector of part elements, note that a resource link with multiple part
     * elements is effectively an OR of these parts
     *
     * @return VElement
     */
    public VElement getParts()
    {
        return getChildElementVector(ElementName.PART, null, null, true, 0, false);
    }
    
    /**
     * setPart - shorthand if only one part is required and should be set to kay = value
     *
     * @param String
     *            key
     * @param String
     *            value
     */
    public void setPart(String key, String value)
    {
        final JDFPart part = getCreatePart(0);
        part.setAttribute(key, value, null);
    }
    
    /**
     * shorthand if only one part is required and should be set to kay = value
     * 
     * @param JDFResource::EnumPartIDKey
     *            key the partition key
     * @param KString
     *            value the partition value
     */
    public void setPartition(JDFResource.EnumPartIDKey key, String value)
    {
        while (hasChildElement(ElementName.PART, null))
        {
            removePart(0);
        }
        
        final JDFPart part = getCreatePart(0);
        part.setAttribute(key.getName(), value, null);
    }
    
    /**
     * remove element Part
     * 
     * @param int
     *            iSkip number of elements to skip
     * 
     * default: removePart(0)
     */
    public void removePart(int iSkip)
    {
        removeChild(ElementName.PART, null, iSkip);
    }
    
    /**
     * isExecutable - checks whether the resource link links to a resource that is in a state that
     * will allow a node to execute
     *
     * @param JDFAttributeMap
     *            partMap
     *
     * @return boolean
     * 
     * default: isExecutable(null, true)
     */
    public boolean isExecutable(JDFAttributeMap partMap, boolean bCheckChildren)
    {
        if (!hasResourcePartMap(partMap, false))
        {
            return false;
        }
        
        VElement leaves = new VElement();
        boolean bExec = false;
        
        if (bCheckChildren)
        {
            final VElement leaves2 = getTargetVector(-1);
            for (int i = 0; i < leaves2.size(); i++)
            {
                final JDFResource p = (JDFResource) leaves2.elementAt(i);
                if (p == null)
                {    
                    continue;
                }
                final VElement targetVector = p.getLeaves(false);
                leaves.addAll(targetVector);
            }
        }
        
        else
        { // calculate availability directly
            leaves = getTargetVector(-1);
        }
        
        for (int i = 0; i < leaves.size(); i++)
        {
            final JDFResource leaf = (JDFResource) leaves.elementAt(i);
            
            if (partMap != null && !partMap.isEmpty())
            {
                if (!partMap.overlapMap(leaf.getPartMap()))
                {
                    continue;
                }
            }
            
            final JDFResource.EnumResStatus status = leaf.getResStatus(true);
            
            if (status.equals(JDFResource.EnumResStatus.InUse))
            {
                return false;
            }
            
            // TODO MinStatus stuff 
            if (getUsage().equals(EnumUsage.Input))
            {
                if (getDraftOK())
                {
                    bExec = status.equals(JDFResource.EnumResStatus.Available)
                    || status.equals(JDFResource.EnumResStatus.Draft);
                }
                else
                {
                    bExec = status.equals(JDFResource.EnumResStatus.Available);
                }
                if (!bExec)
                {
                    // tbd pipe handling
                }
            }
            else
            {
                bExec = status.getValue() > JDFResource.EnumResStatus.Incomplete.getValue();
            }
            
            // any leaf not executable --> the whole thing is not executable
            if (!bExec)
            {
                return false;
            }
        }		
        return true;
    }
    
    /**
     * getResourceLinkPool
     *
     * @return JDFResourceLinkPool
     */
    protected JDFResourceLinkPool getResourceLinkPool()
    {
        if (getParentNode_KElement() != null)
        {
            return (JDFResourceLinkPool) getParentNode_KElement();
        }
        
        return null;
    }
    
    
    /**
     * gets the 1st resource leaf that this resourcelink refers to for details, see the description
     * of
     * 
     * @see getTargetVector
     * @since 102103 GetTarget returns the lowest common denominator if all children of a resource
     *        are referenced
     * @return JDFResource the first leaf that is referenced by this ResourceLink 
     */
    public JDFResource getTarget()
    {
        VElement v = getTargetVector(-1);
        if (v == null)
        {
            return null;
        }
        return (JDFResource) v.getCommonAncestor();
    }
    
    /**
     * Method getTargetVector gets the resource nodes that this resourcelink refers to skips links
     * that do not exist or where the name mangling is illegal<br>
     * the behavior varies according to the value of PartUsage of the referenced resource:<br>
     * if PartUsage="Explicit", all elements that are referenced in PartIDKeys and the ResourceLink
     * must exist and fit<br>
     * if PartUsage="Implicit", the best fitting intermediate node of the partitioned resource is
     * returned<br>
     * attributes in the Part elements that are not referenced in PartIDKeys are assumed to be
     * logical attributes (e.g. RunIndex of a RunList) and ignored when searching the part
     * 
     * @param int
     *            nMax maximum number of requested resources; -1= all
     * 
     * @return VElement  the set of leaves that are referenced by this ResourceLink 
     * 
     * default: getTargetVector(-1)
     */
    public VElement getTargetVector(int nMax)
    {
        // split it into leaves
        // 221003 changed GetResourcePartMapVector to GetPartMapVector
        final VJDFAttributeMap vmParts = getPartMapVector();
        return getMapTargetVector(vmParts, nMax);
    }
    
    /**
     * Gets the resource nodes that this resourcelink refers to skips links that do not exist or
     * where the name mangling is illegal<br>
     * the behavior varies according to the value of PartUsage of the referenced resource:<br>
     * if PartUsage="Explicit", all elements that are referenced in PartIDKeys and the ResourceLink
     * must exist and fit<br>
     * if PartUsage="Implicit", the best fitting intermediate node of the partitioned resource is
     * returned<br>
     * attributes in the Part elements that are not referenced in PartIDKeys are assumed to be
     * logical attributes (e.g. RunIndex of a RunList) and ignored when searching the part
     * 
     * @param vmAttribute
     *            vmParts target map to use
     * @param int
     *            nMax maximum number of requested resources; -1= all
     * @return VElement the set of leaves that are referenced by this ResourceLink 
     */
    private VElement getMapTargetVector(VJDFAttributeMap vmParts, int nMax)
    {
        final VElement v = new VElement();        
        // get the resource root
        final JDFResource resRoot = getLinkRoot();        
        if (resRoot == null)
        {
            return v;
        }
        if (vmParts==null || vmParts.isEmpty())
        {
            v.addElement(resRoot);
            return v;
        }
        
        
        // get the value of PartUsage
        final JDFResource.EnumPartUsage partUsage = resRoot.getPartUsage();
        
        if (partUsage.equals(JDFResource.EnumPartUsage.Implicit))
        {
            vmParts.reduceKey(resRoot.getPartIDKeys());
        }
        
        for (int i = 0; i < vmParts.size(); i++)
        {
            final VElement vr = resRoot.getPartitionVector(vmParts.elementAt(i), partUsage);
            if (vr != null && !vr.isEmpty())
            {
                v.addAll(vr);
                // we have enough!
                if (v.size() == nMax)
                {
                    break;
                }
            }
        }
        return v;
    }
    
    /**
     * getPool
     *
     * @return JDFPool
     */
    public JDFPool getPool()
    {
        if (getDeepParentNotName(getNodeName()) != null)
        {
            return (JDFPool) getDeepParentNotName(getNodeName());
        }
        
        return null;
    }
    
    /**
     * checks whether a given partMap is compatible with the this link i.e. whether this link will
     * 
     * @param JDFAttributeMap
     *            partMap the map of parts that this link is compared to
     * @param boolean
     *            bCheckResource if true also recurse into the resource and check if the parts exist
     * 
     * @return boolean true if this is compatible with partMap
     * 
     * default: HasResourcePartMap(partMap, false)
     */
    public boolean hasResourcePartMap(JDFAttributeMap partMap, boolean bCheckResource)
    {
        // Attention !!!
        // Don't change this method without checking if routing is still working !
        // The C++ method is different and is not used, the java method is used for routing.
        VJDFAttributeMap vPart;
        
        if (bCheckResource)
        {
            vPart = getResourcePartMapVector();
        }
        else
        {
            vPart = getPartMapVector();
        }
        
        boolean bImplicit = JDFResource.EnumPartUsage.Implicit.equals(getLinkRoot().getPartUsage());
        
        if ((partMap == null || partMap.isEmpty()) && (vPart == null || vPart.isEmpty()))
        {
            return true;
        }

        final int siz = vPart == null  ? 0 : vPart.size();
        
        if (bImplicit)
        {
            if (siz == 0)
            {
                return true;
            }
            
            for (int i = 0; i < siz; i++)
            {
                if (vPart.elementAt(i).overlapMap(partMap))
                    return true;
            }
        }
        else
        { // explicit
            if (siz == 0 && !bCheckResource)
            {
                return true;
            }
            
            for (int i = 0; i < siz; i++)
            {
                // RP 050120 swap of vPart[i] and partmap
                if (partMap.subMap(vPart.elementAt(i)))
                    return true;
            }
        }
        
        return false;
    }
    
    public boolean overlapsResourcePartMap(JDFAttributeMap partMap)
    {
        if (partMap.isEmpty())
        {
            return true; // speed up...
        }
        
        VJDFAttributeMap vPart = getPartMapVector();
        
        final int siz = vPart==null ? 0 : vPart.size();
        // if no part, any resource that is linked is valid
        if (siz == 0)
        {
            return true;
        }
        
        for (int i = 0; i < siz; i++)
        {
            if (vPart.elementAt(i).overlapMap(partMap))
                return true;
        }
        
        return false;
    }
    
    public boolean isResourceSelected(JDFResource resourceToCheck)
    {
        // For the decision, compare the leaves of the Resource with the Leaves pointed to by the 
        // resource link. If all leaves of the Resource are pointed to by the ResourceLink, then the 
        // ResourceLink selects the Resource (partition). This method checks if the leaves
        // represented by the
        // Resource are a subset of the leaves represented by the ResourceLink
        boolean b_ResurceIsSelected = false;
        
        // get the resource leaves from resource and resource link
        final VElement resourceLeavesFromResource  = resourceToCheck.getLeaves(false);
        final VElement resourceLeavesFromLink      = getTargetVector(-1);
        
        // number of resources found
        final int i_NoResourceLeavesFromResource  = resourceLeavesFromResource.size();
        final int i_NoResourceLeavesFromLink      = resourceLeavesFromLink.size();
        
        // compare Resource Vectors if they contain the same Resources (here ResourceLeaves)
        // Ordering
        // of verctors is not important
        // Note: a method vResource::IsSubSet(vResource) would help here; the following is an
        // implementation
        // for this
        
        int i_CurrentLeafFromResource = 0;
        int i_CurrentLeafFromLink = 0;
        
        // look if every Resource leaf from the ResourceLeavesFromResource is in the
        // ResourceLeavesFromLink
        // vector
        boolean b_SelectionIsPossible = true;
        while (b_SelectionIsPossible && i_CurrentLeafFromResource < i_NoResourceLeavesFromResource)
        {
            // get ResourceLeaf from Resource Vector to compare
            final JDFResource currentLeafFromResource = (JDFResource) resourceLeavesFromResource
                    .elementAt(i_CurrentLeafFromResource);
            
            // compare with ResourceLeaf in ResourceLink vector till the Resource is found
            // iterate the vector with leaves from ResourceLinks
            boolean b_ResourceFoundInLink = false;
            while (!b_ResourceFoundInLink && i_CurrentLeafFromLink < i_NoResourceLeavesFromLink)
            {
                final JDFResource currentLeafFromLink = (JDFResource) resourceLeavesFromLink
                        .elementAt(i_CurrentLeafFromLink);
                b_ResourceFoundInLink = currentLeafFromResource == currentLeafFromLink;
                i_CurrentLeafFromLink++;
            }
            
            // if value is false, one partition is not selected => whole resource is not selected
            b_SelectionIsPossible = b_ResourceFoundInLink;
            i_CurrentLeafFromResource++;
        }
        
        b_ResurceIsSelected = b_SelectionIsPossible;
        
        return b_ResurceIsSelected;
    }
    
    /**
     * get part map vector as defined by the linked resource
     * 
     * @return vector of mAttribute, one for each part
     */
    // vmAttribute getResourcePartMapVector()const;
    public VJDFAttributeMap getResourcePartMapVector()
    {
        final VJDFAttributeMap vMap = new VJDFAttributeMap();
        final VJDFAttributeMap vPartMap = getPartMapVector();
        final int nPartChildren = vPartMap.size();
        final JDFResource root = getLinkRoot();
        final VElement leaves = root.getLeaves(false);
        // loop over resource leaves
        for (int i = 0; i < leaves.size(); i++)
        {
            final JDFAttributeMap leafMap = ((JDFResource) leaves.elementAt(i)).getPartMap();
            if (nPartChildren > 0)
            { // it's reduced
                for (int j = 0; j < nPartChildren; j++)
                {
                    final JDFAttributeMap mPart = vPartMap.elementAt(j);
                    if (!mPart.overlapMap(leafMap))
                    {
                        continue;
                    }
                    // got one; break both loops and continue with the next leaf
                    vMap.addElement(leafMap.orMap(mPart));
                }
            }
            else
            {
                // no parts in the resourcelink -> simply append the resources partmap
                vMap.addElement(leafMap);
            }
        }
        return vMap;
    }
    
    /**
     * Returns the linked resource name
     *
     * @return String - the name
     */
    public String getLinkedResourceName()
    {
        final String nodeName = getNodeName();
        final int length = nodeName.length();
        if(length<=4)
            throw new JDFException("invalid nodename for resource link");
        return nodeName.substring(0, length - JDFConstants.LINK.length());
        
    }
    
    /**
     * Method getNamedProcessUsage.
     * 
     * @return String
     */
    public String getNamedProcessUsage()
    {
        String s = getProcessUsage();
        // 030502 RP modified to default tx xxx:Input / xxx:Output respectively
        if (s == null || s.equals(JDFConstants.EMPTYSTRING))
        {
            // 200602 RP need the string type - don't cycle to and from enum type...
            s = getAttribute(AttributeName.USAGE, null, JDFConstants.EMPTYSTRING);
            
        }
        
        s = getLinkedResourceName() + JDFConstants.COLON + s;
        
        return s;
    }
    
    /**
     * Method ValidResourcePosition.
     * 
     * @return boolean
     */
    public boolean validResourcePosition()
    {
        return validResourcePosition(getLinkRoot());
    }
    
    public boolean isValid(EnumValidationLevel level)
    {
        if(level==null)
            level=EnumValidationLevel.Complete;
        
        boolean b=super.isValid(level);
        if(!b) 
            return false;
        if(this instanceof JDFPartAmount)
            return true;
        
        if((level!=EnumValidationLevel.Complete) && (level!=EnumValidationLevel.Incomplete) && (level!=EnumValidationLevel.RecursiveIncomplete))
            return true;
        
        if(!validResourcePosition())
            return false;

        VElement vRes=getTargetVector(0);
        if((vRes==null || vRes.isEmpty())&&((level==EnumValidationLevel.Complete)||(level==EnumValidationLevel.RecursiveComplete))){
            // if any partition points to nirvana and we are validating complete, the entire resource is invalid
            return false;
        }
        if(level.equals(EnumValidationLevel.Complete))
            return true;
        
        for(int iRes=0;iRes<vRes.size();iRes++){
            JDFResource r=(JDFResource)vRes.elementAt(iRes);
            // reslinks that point to nothing may be valid
            
            // but they certainly aren't valid if they point to the wrong resource
            if(!getNodeName().equals(r.getLinkString())) 
                return false;
                                    
            if(level.getValue()>=EnumValidationLevel.RecursiveIncomplete.getValue()){
                EnumValidationLevel valDown=(level==EnumValidationLevel.RecursiveIncomplete) ? EnumValidationLevel.Incomplete : EnumValidationLevel.Complete;
                
                if(!r.isValid(valDown))
                    return false;
            }
        }
        return true;
    }    
    
    public boolean isPhysical()
    {
        boolean fIsPhysical = false;
        final JDFResource resource = getLinkRoot();
        if (resource != null)
        {
            fIsPhysical = resource.isPhysical();
        }
        
        return fIsPhysical;
    }
    
    public boolean isImplementation()
    {
        boolean fIsImplementation = false;
        final JDFResource linkRoot = getLinkRoot();
        if (linkRoot != null)
        {
            if (linkRoot.getResourceClass() == JDFResource.EnumResourceClass.Implementation)
            {
                fIsImplementation = true;
            }
        }
        return fIsImplementation;
    }
    
    /** 
     * Append Element Part
     */
    public JDFPart appendPart()
    {
        return (JDFPart) appendElement(ElementName.PART, null);
    }
    
    public JDFAmountPool getAmountPool()
    {
        return (JDFAmountPool) getElement(ElementName.AMOUNTPOOL, null, 0);
    }
    
    public JDFAmountPool getCreateAmountPool()
    {
        if (this instanceof JDFPartAmount)
        {
            throw new JDFException(
                    "JDFResourceLinkPool.getCreateAmountPool: calling method on PartAmount object");
        }
        return (JDFAmountPool) getCreateElement_KElement(ElementName.AMOUNTPOOL,null, 0);
    }
    
    public JDFAmountPool appendAmountPool()
    {
        // ideally the method would be hidden in PartAmount
        if (this instanceof JDFPartAmount)
        {
            throw new JDFException(
                    "JDFResourceLinkPool.appendAmountPool: calling method on PartAmount object");
        }
        return (JDFAmountPool) appendElementN(ElementName.AMOUNTPOOL, 1, null);
    }

    /**
     * reduce the parts to the canonical representation if all children of a parent node are in
     * defined in parts, they are replaced by their parent for example the canonical representation
     * of all leaves is the root
     */
    public void reduceParts()
    {
        final VJDFAttributeMap vParts = getPartMapVector();
        if (vParts.isEmpty()) // nothing to do
        {
            return;
        }
        
        final VJDFAttributeMap vReducedParts = getLinkRoot().reducePartVector(vParts);
        
        if (vParts != vReducedParts)
        {
            setPartMapVector(vReducedParts);
        }
    }
    
    /**
     * Expand the target resource to contain all parts specified inthe link If
     * PartUsage==Explicit or bForce==true
     * 
     * @attrib bForce if true, implicitly referenced partitions are also expanded
     */
    public void expandTarget(boolean bForce) 
    {
        JDFResource r = getLinkRoot();
        //  loop over parts for partusage explicit
        if (r.getPartUsage() == JDFResource.EnumPartUsage.Explicit || bForce)
        {
            VJDFAttributeMap apParts = getPartMapVector();
            if(apParts!=null)
            {
                for (int i = 0; i < apParts.size(); i++)
                    r.getCreatePartition(apParts.elementAt(i), null);
            }
        }
    }
    
    /**
     * returns  the minimum value of attribute occurence in PartAmount, 
     * 
     * @param attrib:
     *            the attribute name
     * @param nameSpaceURI:
     *            the XML-namespace
     * @param mPart
     *            which part of this ResourceLink the Amount belongs to, if empty get the
     *            ResourceLink root attribute
     *            @param def the default value id none is found
     * @return double - the value of attribute found, def if no matches found
     * @since 060630 
     */
    public double getMinAmountPoolAttribute(String attrib, String nameSpaceURI, JDFAttributeMap mPart, int def)
    {
        final JDFAmountPool ap=getAmountPool();
        if(ap==null)
            return def;
        final VElement vPartAmount=ap.getMatchingPartAmountVector(mPart);
        
        boolean bMatch=false;
        double d=Double.MAX_VALUE;
        final int size = vPartAmount.size();
        for(int i=0;i<size;i++)
        {
            final JDFPartAmount pa=(JDFPartAmount) vPartAmount.elementAt(i);
            final double realAttribute = pa.getRealAttribute(attrib,nameSpaceURI,def);
            if(realAttribute!=def && realAttribute<d)
            {
                bMatch=true;
                d=realAttribute;
            }
        }
        return bMatch ? d : def;
    }
    
    /**
     * returns  the  attribute occurence in PartAmount, or the default in the ResourceLink
     * 
     * @param attrib:
     *            the attribute name
     * @param nameSpaceURI:
     *            the XML-namespace
     * @param mPart
     *            which part of this ResourceLink the Amount belongs to, if empty get the
     *            ResourceLink root attribute
     * @return value of attribute found, null if not available
     * @since 071103 
     */
    public String getAmountPoolAttribute(String attrib, String nameSpaceURI, JDFAttributeMap mPart)
    {
        // simply return the attribute for null map
        if ((mPart == null) || mPart.isEmpty())
        {
            return getAttribute(attrib, nameSpaceURI, null);
        }
        // want a map but already in a partamount - snafu
        if (this instanceof JDFPartAmount)
        {
            throw new JDFException(
                    "JDFResourceLinkPool.getAmountPoolAttribute: calling method on PartAmount object");
        }
        // default to attribute if no amountpool
        final JDFAmountPool amountPool = getAmountPool();
        if (amountPool == null)
        {
            return getAttribute(attrib, nameSpaceURI, null);
        }
        final JDFPartAmount pa = amountPool.getPartAmount(mPart);
        if (pa != null)
            return pa.getAttribute(attrib, nameSpaceURI, null);
        
        return null;
    }
    
    /**
     * returns true if the  attribute occurrs
     * 
     * @param attrib:
     *            the attribute name
     * @param nameSpaceURI:
     *            the XML-namespace
     * @param mPart
     *            which part of this ResourceLink the Amount belongs to, if empty get the
     *            ResourceLink root attribute
     * @return  true if  available
     * @deprecated 060601 use getAmountPoolAttribute(attrib,nameSpaceURI,mPart)!=null;
     * @since 071103 
     */
    public boolean hasAmountPoolAttribute(String attrib, String nameSpaceURI, JDFAttributeMap mPart)
    {
       return getAmountPoolAttribute(attrib,nameSpaceURI,mPart)!=null;
    }

    /** 
     * sets  the  attribute occurence in PartAmount 
     * 
     * @param attrib: the attribute name
     * @param value: value to set in string form.
     * @param nameSpaceURI: the XML-namespace
     * @param vPart  which part of this ResourceLink the Amount belongs to, if empty set the
     *            ResourceLink root attribute
     * @since 060630
     */
    public void setAmountPoolAttribute(String attrib, String value, String nameSpaceURI, VJDFAttributeMap vPart)
    {
        // ideally the method would be hidden in PartAmount
        if ((vPart == null) || (vPart.isEmpty()))
        {
            setAttribute(attrib, value, nameSpaceURI);
            return;
        }
        removeAttribute(attrib, nameSpaceURI); // either in the pool or the link, not both
        JDFAmountPool ap=getCreateAmountPool();
        JDFPartAmount pa0=null;
        for(int i=0;i<vPart.size();i++)
        {
            final JDFAttributeMap map = vPart.elementAt(i);
            JDFPartAmount pa=ap.getPartAmount(map);
            if(pa!=null)
            {
                if(pa0!=null && pa!=pa0)
                    throw new JDFException("inconsistent partamounts");
                pa0=pa;
            }
        }
        if(pa0==null)
            pa0=ap.appendPartAmount(vPart);
        pa0.setPartMapVector(vPart);    
        pa0.setAttribute(attrib, value, nameSpaceURI);
    }
    /** 
     * sets  the  attribute occurence in PartAmount 
     * 
     * @param attrib: the attribute name
     * @param value: value to set in string form.
     * @param nameSpaceURI: the XML-namespace
     * @param mPart  which part of this ResourceLink the Amount belongs to, if empty set the
     *            ResourceLink root attribute
     * @since 071103
     */
    public void setAmountPoolAttribute(String attrib, String value, String nameSpaceURI,
            JDFAttributeMap mPart)
    {
        // ideally the method would be hidden in PartAmount
        if ((mPart == null) || (mPart.isEmpty()))
        {
            setAttribute(attrib, value, nameSpaceURI);
            return;
        }
        if (this instanceof JDFPartAmount)
        {
            throw new JDFException(
                    "JDFResourceLinkPool.setAmountPoolAttribute: calling method on PartAmount object");
        }
        final VJDFAttributeMap v=new VJDFAttributeMap();
        v.add(mPart);
        setAmountPoolAttribute(attrib, value, nameSpaceURI, v);
    }
    
    ////////////////////////////////////////////////////////////////////////////////
    
    private double getAmountPoolDouble(String attName, JDFAttributeMap mPart)
    {
        double d = 0;
        final String w = getAmountPoolAttribute(attName, null, mPart);
        if (w == null)
        {
            return d;
        }
        d = StringUtil.parseDouble(w, -1.234567);
        if (d == -1.234567)
        {
            throw new JDFException(
            "JDFResourceLink.getAmountPoolDouble: Attribute "+attName+" has an invalid value");
        }
        return d;
    }
    
    /**
     * Set attribute ActualAmount in the AmountPool or in the link, depending on the value of mPart
     * @param value the value to set ActualAmount to
     * @param mPart the part map of AmountPool/PartAmount
     */
    public void setActualAmount(double value, JDFAttributeMap mPart)
    {
        setAmountPoolAttribute("ActualAmount", StringUtil.formatDouble(value), null, mPart);
    }
    
    public double getActualAmount(JDFAttributeMap  mPart)  
    {
        return getAmountPoolDouble(AttributeName.ACTUALAMOUNT, mPart);
    }
    
    /**
     * setProcessUsage
     *
     * @param String
     * @deprecated use the enum method
     */
    public void setProcessUsage(String s)
    {
        setAttribute(AttributeName.PROCESSUSAGE, s, null);
    }
    
    /**
     * getProcessUsage
     *
     * @return String
     */
    public String getProcessUsage()
    {
        return getAttribute(AttributeName.PROCESSUSAGE, null, JDFConstants.EMPTYSTRING);
    }   
    
    /**
     * getProcessUsage
     *
     * @return EnumProcessUsage
     */
    public EnumProcessUsage getEnumProcessUsage()
    {
        return EnumProcessUsage.getEnum(getAttribute(AttributeName.PROCESSUSAGE, null, null));
    }    
    /**
     * setProcessUsage
     *
     * @param processUsage
     */
    public void setProcessUsage(EnumProcessUsage processUsage)
    {
        setAttribute(AttributeName.PROCESSUSAGE, processUsage==null ? null : processUsage.getName(), null);
    }    
    
    /**
     * Method setUsage.
     * 
     * @param value
     */
    public void setUsage(EnumUsage value)
    {
        setAttribute(AttributeName.USAGE, value==null ? null : value.getName(), null);
    }
    
    /**
     * getUsage - get the usage of the resourcelink in a JDF node. If no usage is available, default
     * to the resource name
     *
     * @return EnumUsage
     */
    public EnumUsage getUsage()
    {
        return EnumUsage.getEnum(getAttribute(AttributeName.USAGE, null, null));
    }
    
    /**
     * Method setUsage.
     * 
     * @param value
     */
    public void setMinStatus(JDFResource.EnumResStatus value)
    {
        setAttribute(AttributeName.MINSTATUS, value.getName(), null);
    }
    
    /**
     * getMinStatus - get the minimum status of the resourcelink in a JDF node. 
     * If usage is input or not available also check DraftOK
     *
     * @return the status of the resourcelink
     */
    public JDFResource.EnumResStatus getMinStatus()
    {
        final EnumResStatus returnEnum;
        if (hasAttribute(AttributeName.MINSTATUS))
        {
            returnEnum = JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.MINSTATUS));
        }
        else
        {
            if (getUsage() == EnumUsage.Output)
            {
                returnEnum = JDFResource.EnumResStatus.Unavailable;
            }
            else if (getBoolAttribute(AttributeName.DRAFTOK, null, false))
            {
                returnEnum = JDFResource.EnumResStatus.Draft;
            }
            else
            {
                returnEnum = JDFResource.EnumResStatus.Available;
            }
        }
        
        return returnEnum;
    }

    /**
     * Method setUsage.
     * 
     * @param value
     */
    public void setMinLateStatus(JDFResource.EnumResStatus value)
    {
        setAttribute(AttributeName.MINLATESTATUS, value.getName(), null);
    }
    
    /**
     * getUsage - get the usage of the resourcelink in a JDF node. If no usage is available, default
     * to the resource name
     *
     * @return EnumUsage
     */
    public JDFResource.EnumResStatus getMinLateStatus()
    {
        if (!this.hasAttribute(AttributeName.MINLATESTATUS))
            return getMinStatus();
        
        return JDFResource.EnumResStatus.getEnum(getAttribute(AttributeName.MINLATESTATUS, null, null));
    }

    /**
     * Sets the value of PipePartIDKeys
     * 
     * @param keys -
     *            vector of values to set
     * @deprecated use setPipePartIDKeys(Vector enum)
     */
    public void setPipePartIDKeys(VString keys)
    {
        Vector vEnum = new Vector();
        for (int i = 0; i < keys.size(); i++)
        {
            vEnum.add(EnumPartIDKey.getEnum((String) keys.elementAt(i)));
        }
        setPipePartIDKeys(vEnum);
    }
    
    /**
     * Sets the value of PipePartIDKeys 
     *
     * @param keys -
     *            vector of values to set
     */
    public void setPipePartIDKeys(Vector keys)
    {
        setEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS, keys, null);
        
    }

    /**
     * Gets a list of all valid pipe part keys for this resource
     *
     * @return VString list of all PipePartIDKeys
     * @deprecated
     */
    public VString getPipePartIDKeys()
    {
        VString vPipePartIDKeys  = new VString();
        Vector v = getPipePartIDKeysEnum();
        for (int i = 0; i < v.size(); i++)
            vPipePartIDKeys.add(((EnumPartIDKey) v.elementAt(i)).getName());
        
        return vPipePartIDKeys;
    }
    
    public Vector getPipePartIDKeysEnum()
    {
        Vector v = null;
        
        JDFResource res = getTarget();
        VString vPartIDKeys = res.getPartIDKeys();
        if (hasAttribute(AttributeName.PIPEPARTIDKEYS))
        {
            v = getEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS, null, EnumPartIDKey.getEnum(0),
                    false);
        }
        else
        {
            v = res.getPipePartIDKeysEnum();
        }
        for (int i = 0; i < v.size(); i++)
        {
            if (!vPartIDKeys.contains(((EnumPartIDKey) v.elementAt(i)).getName()))
            {
                throw new JDFException("JDFResourceLink.getPipePartIDKeys: key " + v.elementAt(i)
                        + " is not subset of PartIDKey");
            }
        }
        return v;
    }

    /**
     * sets attribute CombinedProcessIndex
     *
     * @param JDFIntegerList
     *            value - attribute value to set
     */
    public void setCombinedProcessIndex(JDFIntegerList value)
    {
        setAttribute(AttributeName.COMBINEDPROCESSINDEX, value, null);
    }
    
    /**
     * gets attribute CombinedProcessIndex
     *
     * @return JDFIntegerList - attribute value, null if no CombinedProcessIndex is set
     * @throws JDFException
     *             if attribute has not a type JDFIntegerList
     */
    public JDFIntegerList getCombinedProcessIndex()
    {
        try
        {
            final String cpi = getAttribute(AttributeName.COMBINEDPROCESSINDEX,null,null);
            return cpi==null ? null : new JDFIntegerList(cpi);
        }
        catch (DataFormatException dfe)
        {
            throw new JDFException(
                    "JDFResourceLink.getCombinedProcessIndex: not capable to create JDFIntegerList");
        }
    }
    
    /**
     * sets attribute CombinedProcessType
     *
     * @param String
     *            value - attribute value to set
     */
    public void setCombinedProcessType(String value)
    {
        setAttribute(AttributeName.COMBINEDPROCESSTYPE, value);
    }
    
    /**
     * gets attribute CombinedProcessType
     *
     * @return String - attribute value
     */
    public String getCombinedProcessType()  
    {
        return getAttribute(AttributeName.COMBINEDPROCESSTYPE);
    }
    
    /**
     * sets attribute DraftOK if version>=1.3, set MinStatus=Draft instead of DraftOK=true
     * 
     * @param boolean
     *            value - attribute value to set
     */
    public void setDraftOK(boolean value)
    {
        EnumVersion eVer = getVersion(true);
        if (eVer.getValue() < EnumVersion.Version_1_3.getValue())
        {
            setAttribute(AttributeName.DRAFTOK, value, null);
        }
        else if (value == true)
        {
            setMinStatus(JDFResource.EnumResStatus.Draft);
        }
        else // 1.3 DraftOK=false
        {
            setMinStatus(EnumUsage.Output.equals(getUsage()) ? JDFResource.EnumResStatus.Unavailable :JDFResource.EnumResStatus.Available);            
        }
    }
    
    /**
     * gets attribute DraftOK
     *
     * @return boolean - attribute value. Default is false
     */
    public boolean getDraftOK()
    {
        if (hasAttribute(AttributeName.DRAFTOK))
        {
            return getBoolAttribute(AttributeName.DRAFTOK, null, false);
        }
        
        // try to infer draftok from the JDF 1.3 MinStatus flag
        if (!hasAttribute(AttributeName.MINSTATUS))
            return false;
        
        return getMinStatus().getValue() <= JDFResource.EnumResStatus.Draft.getValue();
    }
    
    /**
     * sets attribute PipeProtocol
     * 
     * @param String
     *            value - attribute value to set
     */ 
    public void setPipeProtocol(String value)
    {
        setAttribute(AttributeName.PIPEPROTOCOL, value);
    }
    
    /**
     * gets string attribute PipeProtocol
     * 
     * @return String - attribute value.
     */
    public String getPipeProtocol()
    {
        String str = JDFConstants.EMPTYSTRING;
        if (!hasAttribute(AttributeName.PIPEPROTOCOL))
        {
            JDFResource res = getTarget();
            if (res != null)
            {
                str = res.getPipeProtocol();
            }
        }
        else 
        {
            str = getAttribute(AttributeName.PIPEPROTOCOL);
        }
        return str;
    }
    
    /**
     * sets attribute PipeURL
     * 
     * @param String
     *            value - attribute value to set
     */   
    public void setPipeURL(String value)
    {
        setAttribute(AttributeName.PIPEURL, value, null);
    }
    
    /**
     * gets string attribute PipeURL
     * 
     * @return String - attribute value.
     */
    public String getPipeURL()
    {
        String str = JDFConstants.EMPTYSTRING;
        if (!hasAttribute(AttributeName.PIPEURL))
        {
            JDFResource res = getTarget();
            if (res != null)
            {
                str = res.getPipeURL();
            }
        }
        else 
        {
            str = getAttribute(AttributeName.PIPEURL);
        }
        return str;
    }
    
    /**
     * sets attribute rRef
     * 
     * @param String
     *            value - attribute value to set
     */
    public void setrRef(String value)
    {
        setAttribute(AttributeName.RREF, value, null);
    }
    
    /**
     * gets string attribute rRef
     * 
     * @return String - attribute value.
     */
    public String getrRef() 
    {
        return getAttribute(AttributeName.RREF);
    }
    
    /**
     * sets attribute rSubRef
     * 
     * @param String
     *            value - attribute value to set
     */
    public void setrSubRef(String value)
    {
        setAttribute(AttributeName.RSUBREF, value);
    }
    
    /**
     * gets string attribute rSubRef
     * 
     * @return String - attribute value.
     */
    public String getrSubRef()
    {
        return getAttribute(AttributeName.RSUBREF);
    }
    
    /**
     * sets attribute PipePause
     *
     * @param double
     *            value - attribute value to set
     */
    public void setPipePause(double value)
    {
        setAttribute(AttributeName.PIPEPAUSE, value, null);
    }
    
    /**
     * gets attribute PipePause
     *
     * @return double - attribute value. 
     */
    public double getPipePause() 
    {
        return getRealAttribute(AttributeName.PIPEPAUSE, null, 0.0);
    }
    
    /**
     * sets attribute PipeResume
     *
     * @param double
     *            value - attribute value to set
     */
    public void setPipeResume(double value)
    {
        setAttribute(AttributeName.PIPERESUME, value, null);
    }
    
    /**
     * gets attribute PipeResume
     *
     * @return double - attribute value. 
     */
    public double getPipeResume() 
    {
        return getRealAttribute(AttributeName.PIPERESUME, null, 0.0);
    }                  
    
    /**
     * sets attribute Orientation
     * 
     * @param EnumOrientation
     *            value - attribute value to set
     */ 
    public void setOrientation(EnumOrientation value)
    {
        setAttribute(AttributeName.ORIENTATION, value.getName(), null);
    }   
    
    /**
     * gets string attribute Orientation
     * 
     * @return EnumOrientation - attribute value
     */
    public EnumOrientation getOrientation()
    {
        return EnumOrientation.getEnum(getAttribute(AttributeName.ORIENTATION, null, null));
    }
    
    /**
     * sets attribute RemotePipeEndPause
     *
     * @param double
     *            value - attribute value to set
     */        
    public void setRemotePipeEndPause(double value)
    {
        setAttribute(AttributeName.REMOTEPIPEENDPAUSE, value, null);
    }
    
    /**
     * gets attribute RemotePipeEndPause
     *
     * @return double - attribute value. 
     */
    public double getRemotePipeEndPause() 
    {
        return getRealAttribute(AttributeName.REMOTEPIPEENDPAUSE, null, 0.0);
    }
    
    /**
     * sets attribute RemotePipeEndResume
     *
     * @param double
     *            value - attribute value to set
     */ 
    public void setRemotePipeEndResume(double value)
    {
        setAttribute(AttributeName.REMOTEPIPEENDRESUME, value, null);
    }
    
    /**
     * gets attribute RemotePipeEndResume
     *
     * @return double - attribute value. 
     */
    public double getRemotePipeEndResume()
    {
        return getRealAttribute(AttributeName.REMOTEPIPEENDRESUME, null, 0.0);
    }
    
    /**
     * sets attribute Transformation(
     * 
     * @param JDFMatrix
     *            value - attribute value to set
     */        
    public void setTransformation(JDFMatrix value)
    {
        setAttribute(AttributeName.TRANSFORMATION, value.toString());
    }
    
    /**
     * gets string attribute Transformation
     * 
     * @return JDFMatrix - attribute value
     * @throws JDFException
     *             if attribute has not a type JDFMatrix
     */
    public JDFMatrix getTransformation()
    {
        try
        {
            return new JDFMatrix(getAttribute(AttributeName.TRANSFORMATION));
        }
        catch (DataFormatException dfe)
        {
            throw new JDFException(
                    "JDFResourceLink.getTransformation: not capable to create JDFMatrix");
        }
    }
    
    /**
     * sets attribute Duration
     *
     * @param JDFDuration
     *            value - attribute value to set
     */
    public void setDuration(JDFDuration value)
    {
        if (value == null)
        {
            value = new JDFDuration();
        }
        setAttribute(AttributeName.DURATION, value.getDurationISO());
    }
    
    /**
     * gets attribute Duration
     *
     * @return JDFDuration - attribute value.
     * @throws JDFException
     *             if attribute has not a type JDFDuration
     */
    public JDFDuration getDuration()
    {
        try
        {
            return new JDFDuration(getAttribute(AttributeName.DURATION));
        }
        catch (DataFormatException dfe)
        {
            throw new JDFException("JDFResourceLink.getDuration: not capable to create JDFDuration");   
        }
    }
    
    /**
     * sets attribute Recommendation
     *
     * @param boolean
     *            value - attribute value to set
     */
    public void setRecommendation(boolean value)
    {
        setAttribute(AttributeName.RECOMMENDATION, value, null);
    }
    
    /**
     * gets attribute Recommendation
     *
     * @return boolean - attribute value.
     */
    public boolean getRecommendation()
    {
        return getBoolAttribute(AttributeName.RECOMMENDATION, null, false);
    }
    
    /**
     * sets attribute Start
     *
     * @param JDFDate
     *            value - attribute value to set
     */
    public void setStart(JDFDate value)
    {
        if (value == null)
        {
            value = new JDFDate();
        }
        setAttribute(AttributeName.START, value.getDateTimeISO());
    }
    
    /**
     * gets attribute Start
     *
     * @return JDFDate - attribute value
     * @throws JDFException
     *             if attribute has not a type JDFDate
     */
    public JDFDate getStart()
    {
        try
        {
            return new JDFDate(getAttribute(AttributeName.START, null, (new JDFDate())
                    .getDateTimeISO()));
        }
        catch (DataFormatException dfe)
        {
            throw new JDFException("JDFResourceLink.getStart: not capable to create JDFDate");   
        }
    }
    
    /**
     * sets attribute StartOffset
     *
     * @param JDFDuration
     *            value - attribute value to set
     */
    public void setStartOffset(JDFDuration value)
    {
        if (value == null)
        {
            value = new JDFDuration();
        }
        setAttribute(AttributeName.STARTOFFSET, value.getDurationISO());
    }
    
    /**
     * gets attribute StartOffset
     *
     * @return JDFDuration - attribute value
     * @throws JDFException
     *             if attribute has not a type JDFDuration
     */
    public JDFDuration getStartOffset() 
    {
        try
        {
            return new JDFDuration(getAttribute(AttributeName.STARTOFFSET, null,
                    (new JDFDuration()).getDurationISO()));
        }
        catch (DataFormatException dfe)
        {
            throw new JDFException(
                    "JDFResourceLink.getStartOffset: not capable to create JDFDuration");
        }
    }
    
    /**
     * get part map vector
     * 
     * @return VJDFAttributeMap: vector of attribute maps, one for each part
     */
    public VJDFAttributeMap getPartMapVector()
    {
        return super.getPartMapVector();
    }
    
    /**
     * set all parts to those define in vParts
     * 
     * @param VJDFAttributeMap
     *            vParts: vector of attribute maps for the parts
     */
    public void setPartMapVector(VJDFAttributeMap vParts)
    {
        super.setPartMapVector(vParts);
    }
    
    /**
     * set all parts to those define in vParts
     * 
     * @param JDFAttributeMap
     *            mPart: attribute map for the part to set
     */
    public void setPartMap(JDFAttributeMap mPart)
    {
        super.setPartMap(mPart);
    }
    
    /**
     * remove the part defined in mPart
     * 
     * @param JDFAttributeMap
     *            mPart: attribute map for the part to remove
     */
    public void removePartMap(JDFAttributeMap mPart)
    {
        super.removePartMap(mPart);
    }
    
    /**
     * check whether the part defined in mPart is included
     * 
     * @param JDFAttributeMap
     *            mPart: attribute map for the part to remove
     * @return boolean - returns true if the part exists
     */
    public boolean hasPartMap(JDFAttributeMap mPart)
    {
        return super.hasPartMap(mPart);
    }    
}
