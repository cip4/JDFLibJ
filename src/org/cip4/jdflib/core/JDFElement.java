/*--------------------------------------------------------------------------------------------------
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
 */

/**
 *
 * Copyright (c) 2001-2004 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFElement.java
 *
 * Last changes
 *
 * 2001-07-30   Torsten Kaehlert - delete isNull() and throwNull() methods in parent class KNode
 *              TKAE20010730
 * 2002-07-02 JG add SettingsPolicy Support
 * 2002-07-02 JG removed AppendElement - it is now only in KElement
 * 2002-07-02 JG added IsJDFNode()
 * 2002-07-02 JG added RemoveChild() / RemoveChildren
 * 2002-07-02 JG GetEnumerationsAttribute() bug fix in loop counter
 * 2002-07-02 JG removed GetTarget(int id,const KString & attrib) const;
 * 2002-07-02 JG GetJDFRoot, GetJMFRoot now const
 * 2002-07-02 JG GetLinks added rSubRef check to map of checked attributes
 * 2002-07-02 JG added NamedColor support
 * 2002-07-02 JG GetElement - added a check that returns the refElement if a refElement is explicitly required
 */

package org.cip4.jdflib.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeInfo.EnumAttributeType;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFDateTimeRangeList;
import org.cip4.jdflib.datatypes.JDFDurationRangeList;
import org.cip4.jdflib.datatypes.JDFIntegerRange;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFNameRange;
import org.cip4.jdflib.datatypes.JDFNameRangeList;
import org.cip4.jdflib.datatypes.JDFNumList;
import org.cip4.jdflib.datatypes.JDFNumberRange;
import org.cip4.jdflib.datatypes.JDFNumberRangeList;
import org.cip4.jdflib.datatypes.JDFRectangleRangeList;
import org.cip4.jdflib.datatypes.JDFShapeRangeList;
import org.cip4.jdflib.datatypes.JDFXYPairRange;
import org.cip4.jdflib.datatypes.JDFXYPairRangeList;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFAncestor;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFPart;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.JDFDuration;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * @author Dietrich Mucha
 *
 * JDFElement contains generic JDF element functionality
 * in general only elements in the JDF namespace will inherit from JDFElement
 */
/**
 * @author boegerni
 *
 */
public class JDFElement extends KElement
{
    private static final long serialVersionUID = 1L;
    
    private static EnumVersion defaultVersion=EnumVersion.Version_1_3;
    
    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[6];
    static 
    { 
        atrInfoTable[0] = new AtrInfoTable(AttributeName.SETTINGSPOLICY,                 0x33333311, AttributeInfo.EnumAttributeType.enumeration, EnumSettingsPolicy.getEnum(0),null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.COMMENTURL,                     0x33333333, AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.DESCRIPTIVENAME,                0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.BESTEFFORTEXCEPTIONS,           0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.MUSTHONOREXCEPTIONS,            0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.OPERATORINTERVENTIONEXCEPTIONS, 0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
    }
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat ("yyMMdd_hhmmssSS");
    protected AttributeInfo getTheAttributeInfo() 
    {
        return getTheAttributeInfo_JDFElement();
    }
    
    protected AttributeInfo getTheAttributeInfo_JDFElement() 
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }
    
    private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[1];
    static 
    {
        elemInfoTable[0] = new ElemInfoTable(ElementName.COMMENT, 0x33333333);
    }
    
    protected ElementInfo getTheElementInfo() 
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }
    
    protected ElementInfo getTheElementInfo_JDFElement() 
    {
        return super.getTheElementInfo().updateReplace(elemInfoTable);
    }
    
    /**
     * Constructor for JDFElement
     * @param myOwnerDocument the DOM document where this elements will be inserted
     * @param qualifiedName the qualified name of the element (see www.w3.org/XML/)
     */
    public JDFElement(CoreDocumentImpl myOwnerDocument, String qualifiedName)
    {
        super(myOwnerDocument, getSchemaURL(), qualifiedName);
    }
    
    /**
     * Constructor for JDFElement
     * @param  myOwnerDocument the DOM document where this elements will be inserted
     * @param  myNamespaceURI  the namespace of the elements to (see www.w3.org/XML/)
     * @param  qualifiedName the qualified name of the element 
     */
    public JDFElement(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
         super(myOwnerDocument, myNamespaceURI==null ? getSchemaURL() : myNamespaceURI , qualifiedName);
    }
    
    /**
     * Constructor for JDFElement
     * @param myOwnerDocument  the DOM document where this elements will be inserted
     * @param myNamespaceURI   the namespace of the elements to create
     * @param qualifiedName  the qualified name of the element (see www.w3.org/XML/)
     * @param myLocalName      the local name of the element (see www.w3.org/XML/)
     */
    public JDFElement(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI==null ? getSchemaURL() : myNamespaceURI, qualifiedName, myLocalName);
    }
    
    
    /**
     * Boolean Enumeration from JDF Spec 
     * Orientation of a physical resource.
     */
    public static final class EnumBoolean extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumBoolean(String name)
        {
            super(name.toLowerCase(), m_startValue++);
        }
        
        /**
         * @param   enumName the name of the enum object to return    
         * @return  the enum object if enumName is valid. Otherwise null
         */
        public static EnumBoolean getEnum(String enumName)
        {
            return (EnumBoolean) getEnum(EnumBoolean.class, enumName.toLowerCase());
        }
        
        /**
         * @param  enumValue the value of the enum object to return
         * @return the enum object if enumName is valid. Otherwise null
         */
        public static EnumBoolean getEnum(int enumValue)
        {
            return (EnumBoolean) getEnum(EnumBoolean.class, enumValue);
        }
        
        /**
         * @return a map of all orientation enums
         */
        public static Map getEnumMap()
        {
            return getEnumMap(EnumBoolean.class);
        }
        
        /**
         * @return a list of all orientation enums
         */
        public static List getEnumList()
        {
            return getEnumList(EnumBoolean.class);
        }
        
        /**
         * @return an iterator over the enum objects
         */
        public static Iterator iterator()
        {
            return iterator(EnumBoolean.class);
        }
        
        // enums  for validation of booleans
        public static final EnumBoolean True       = new EnumBoolean(JDFConstants.BOOLEAN_TRUE);
        public static final EnumBoolean False      = new EnumBoolean(JDFConstants.BOOLEAN_FALSE);
    }
    
    /**
     * Orientation Enumeration <br>
     * Orientation of a physical resource.
     */
    public static final class EnumOrientation extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumOrientation(String name)
        {
            super(name, m_startValue++);
        }
        
        /**
         * @param   enumName the name of the enum object to return    
         * @return  the enum object if enumName is valid. Otherwise null
         */
        public static EnumOrientation getEnum(String enumName)
        {
            return (EnumOrientation) getEnum(EnumOrientation.class, enumName);
        }
        
        /**
         * @param  enumValue the value of the enum object to return
         * @return the enum object if enumName is valid. Otherwise null
         */
        public static EnumOrientation getEnum(int enumValue)
        {
            return (EnumOrientation) getEnum(EnumOrientation.class, enumValue);
        }
        
        /**
         * @return a map of all orientation enums
         */
        public static Map getEnumMap()
        {
            return getEnumMap(EnumOrientation.class);
        }
        
        /**
         * @return a list of all orientation enums
         */
        public static List getEnumList()
        {
            return getEnumList(EnumOrientation.class);
        }
        
        /**
         * @return an iterator over the enum objects
         */
        public static Iterator iterator()
        {
            return iterator(EnumOrientation.class);
        }
        /**
         * @return a Vector with all String representatives of the enums
         * @deprecated
         */
        public static Vector getNamesVector()
        {
            return StringUtil.getNamesVector(EnumOrientation.class);
        }
        
        // enums accordng to JDF spec A.3.3.3, Table 3-3 Orientation
        public static final EnumOrientation Flip0       = new EnumOrientation(JDFConstants.ORIENTATION_FLIP0);
        public static final EnumOrientation Flip90      = new EnumOrientation(JDFConstants.ORIENTATION_FLIP90);
        public static final EnumOrientation Flip180     = new EnumOrientation(JDFConstants.ORIENTATION_FLIP180);
        public static final EnumOrientation Flip270     = new EnumOrientation(JDFConstants.ORIENTATION_FLIP270);
        public static final EnumOrientation Rotate0     = new EnumOrientation(JDFConstants.ORIENTATION_ROTATE0);
        public static final EnumOrientation Rotate90    = new EnumOrientation(JDFConstants.ORIENTATION_ROTATE90);
        public static final EnumOrientation Rotate180   = new EnumOrientation(JDFConstants.ORIENTATION_ROTATE180);
        public static final EnumOrientation Rotate270   = new EnumOrientation(JDFConstants.ORIENTATION_ROTATE270);
    }
    
    /**
     * XYRelation Enumeration <br>
     * XML attributes of type XYRelation define the relationship between two ordered numbers. <br>
     * gt X>Y <br>
     * ge X>=Y <br>
     * eq X==Y <br>
     * le X<=Y <br>
     * lt X<Y <br>
     * ne X!=Y <br>
     * see JDF Spec (Appendix A.3.4) for latest changes
     */
    public static final class EnumXYRelation extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumXYRelation(String name)
        {
            super(name, m_startValue++);
        }
        
        /**
         * @param enumName the name of the enum object to return 
         * @return the enum object if enumName is valid. Otherwise null
         */
        public static EnumXYRelation getEnum(String enumName)
        {
            return (EnumXYRelation) getEnum(EnumXYRelation.class, enumName);
        }
        
        /**
         * @param  enumValue the value of the enum object to return
         * @return the enum object if enumName is valid. Otherwise null
         */
        public static EnumXYRelation getEnum(int enumValue)
        {
            return (EnumXYRelation) getEnum(EnumXYRelation.class, enumValue);
        }
        
        /**
         * @return a map of all XYRelation enums
         */
        public static Map getEnumMap()
        {
            return getEnumMap(EnumXYRelation.class);
        }
        
        /**
         * @return a list of all XYRelation enums
         */
        public static List getEnumList()
        {
            return getEnumList(EnumXYRelation.class);
        }
        
        /**
         * @return an iterator over the enum objects
         */
        public static Iterator iterator()
        {
            return iterator(EnumXYRelation.class);
        }
        /**
         * @return a Vector with all String representatives of the enums
         * @deprecated
         */
        public static Vector getNamesVector()
        {
            final Vector namesVector = new Vector();
            final Iterator it = iterator(EnumXYRelation.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        // enums accordng to JDF spec 3.1.2, Table 3-3 Status
        public static final EnumXYRelation gt       = new EnumXYRelation(JDFConstants.XYRELATION_GT);
        public static final EnumXYRelation ge       = new EnumXYRelation(JDFConstants.XYRELATION_GE);
        public static final EnumXYRelation eq       = new EnumXYRelation(JDFConstants.XYRELATION_EQ);
        public static final EnumXYRelation le       = new EnumXYRelation(JDFConstants.XYRELATION_LE);
        public static final EnumXYRelation lt       = new EnumXYRelation(JDFConstants.XYRELATION_LT);
        public static final EnumXYRelation ne       = new EnumXYRelation(JDFConstants.XYRELATION_NE);
        
        /** 
         * xyRelation - tests if relation x/y matches XYRelation enumeration value
         *
         * @param x x in XYRelation ( x/y )
         * @param y y in XYRelation ( x/y )
         * @param nt negative tolerance
         * @param pt positive tolerance
         * @return true, if relation x/y matches specified enumerated value 
         */
        public boolean evaluateXY(double x, double y, double nt, double pt) 
        {
            if (this == EnumXYRelation.gt)         return x+pt > y-nt; 
            else if (this == EnumXYRelation.ge)    return x+pt >= y-nt; 
            else if (this == EnumXYRelation.eq)    return x >= y-nt && x <= y+pt; 
            else if (this == EnumXYRelation.le)    return x-nt <= y+pt; 
            else if (this == EnumXYRelation.lt)    return x-nt < y+pt; 
            else if (this == EnumXYRelation.ne)    return x < y-nt || x > y+pt; 
            else                                   return true; 
        }
        
        
    }
    
    
    /**
     * Separation Enumeration identifies the separation name.<p>
     * Possible values include:<p>
     * <ul><li><b>Composite</b> – Non-separated resource.
     * <li><b>Separated</b> – The resource is separated, but the separation definition
     * is handled internally by the resource, such as a PDF file that contains
     * SeparationInfo dictionaries.
     * <li><b>Cyan</b> – Process color.
     * <li><b>Magenta</b> – Process color.
     * <li><b>Yellow</b> – Process color.
     * <li><b>Black</b> – Process color.
     * <li><b>Red</b> – Additional process color.
     * <li><b>Green</b> – Additional process color.
     * <li><b>Blue</b> – Additional process color.
     * <li><b>Orange</b> – Additional process color.
     * <li><b>Spot</b> – Generic spot color. Used when the exact nature of the spot color
     * is unknown.
     * <li><b>Varnish</b> – Varnish.</ul>
     * Other values may be any separation name defined in the Name attribute
     * of a {@link org.cip4.jdflib.resource.process.JDFColor} element 
     * in the {@link org.cip4.jdflib.resource.process.JDFColorPool}.
     * When Separation is applied to a ColorantControlLink, it defines
     * an implicit partition that selects a subset of separations for the process
     * that is described by the ColorantControl.
     */
    public static final class EnumSeparation extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSeparation(String name)
        {
            super(name, m_startValue++);
        }
        
        /**
         * @param   enumName the name of the enum object to return    
         * @return  the enum object if enumName is valid. Otherwise null
         */
        public static EnumSeparation getEnum(String enumName)
        {
            return (EnumSeparation) getEnum(EnumSeparation.class, enumName);
        }
        
        /**
         * @param  enumValue the value of the enum object to return
         * @return the enum object if enumName is valid. Otherwise null
         */
        public static EnumSeparation getEnum(int enumValue)
        {
            return (EnumSeparation) getEnum(EnumSeparation.class, enumValue);
        }
        
        /**
         * @return a map of all orientation enums
         */
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSeparation.class);
        }
        
        /**
         * @return a list of all orientation enums
         */
        public static List getEnumList()
        {
            return getEnumList(EnumSeparation.class);
        }
        
        /**
         * @return an iterator over the enum objects
         */
        public static Iterator iterator()
        {
            return iterator(EnumSeparation.class);
        }
        
        
        // enums accordng to JDF spec Table 3-28: Contents of the Part element
        public static final EnumSeparation Cyan     = new EnumSeparation(JDFConstants.SEPARATION_CYAN);
        public static final EnumSeparation Magenta  = new EnumSeparation(JDFConstants.SEPARATION_MAGENTA);
        public static final EnumSeparation Yellow   = new EnumSeparation(JDFConstants.SEPARATION_YELLOW);
        public static final EnumSeparation Black    = new EnumSeparation(JDFConstants.SEPARATION_BLACK);
        public static final EnumSeparation Red      = new EnumSeparation(JDFConstants.SEPARATION_RED);
        public static final EnumSeparation Green    = new EnumSeparation(JDFConstants.SEPARATION_GREEN);
        public static final EnumSeparation Blue     = new EnumSeparation(JDFConstants.SEPARATION_BLUE);
        public static final EnumSeparation Orange   = new EnumSeparation(JDFConstants.SEPARATION_ORANGE);
        public static final EnumSeparation Spot     = new EnumSeparation(JDFConstants.SEPARATION_SPOT);
        public static final EnumSeparation Varnish  = new EnumSeparation(JDFConstants.SEPARATION_VARNISH);
    }
    
    /**
     * Enumeration of various pool types
     */
    public static final class EnumPoolType extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumPoolType(String name)
        {
            super(name, m_startValue++);
        }
        
        /**
         * @param   enumName the name of the enum object to return    
         * @return  the enum object if enumName is valid. Otherwise null
         */
        public static EnumPoolType getEnum(String enumName)
        {
            return (EnumPoolType) getEnum(EnumPoolType.class, enumName);
        }
        
        /**
         * @param  enumValue the value of the enum object to return
         * @return the enum object if enumName is valid. Otherwise null
         */
        public static EnumPoolType getEnum(int enumValue)
        {
            return (EnumPoolType) getEnum(EnumPoolType.class, enumValue);
        }
        
        /**
         * @return a map of all orientation enums
         */
        public static Map getEnumMap()
        {
            return getEnumMap(EnumPoolType.class);
        }
        
        /**
         * @return a list of all orientation enums
         */
        public static List getEnumList()
        {
            return getEnumList(EnumPoolType.class);
        }
        
        /**
         * @return an iterator over the enum objects
         */
        public static Iterator iterator()
        {
            return iterator(EnumPoolType.class);
        }
        
        public static final EnumPoolType RefElement         = new EnumPoolType(ElementName.REFELEMENT);
        public static final EnumPoolType ResourcePool       = new EnumPoolType(ElementName.RESOURCEPOOL);
        public static final EnumPoolType PipeParams         = new EnumPoolType(ElementName.PIPEPARAMS);
        public static final EnumPoolType ResourceLinkPool   = new EnumPoolType(ElementName.RESOURCELINKPOOL);
        public static final EnumPoolType AncestorPool       = new EnumPoolType(ElementName.ANCESTORPOOL);
        public static final EnumPoolType AuditPool          = new EnumPoolType(ElementName.AUDITPOOL);
        public static final EnumPoolType ProductionIntent   = new EnumPoolType(ElementName.PRODUCTIONINTENT);
    }
    
    
    /**
     * Enumeration of valid nodestatus types
     * Identifies the status of the node. Possible values are:<p>
     * <ul><li><b>Waiting</b> – The node may be executed, but it has not completed a test run.
     * <li><b>TestRunInProgress</b> – The node is currently executing a test run.
     * <li><b>Ready</b> – As indicated by the successful completion of a test run, all
     * ResourceLinks are correct, required resources are available, and the
     * parameters of resources are valid. The node is ready to start.
     * <li><b>FailedTestRun</b> – An error occurred during the test run. Error information
     * is logged in the Notification element, which is an optional subelement
     * of the AuditPool element described in Section 3.9, AuditPool.
     * <li><b>Setup</b> – The process represented by this node is currently being set up.
     * <li><b>InProgress</b> – The node is currently executing.
     * <li><b>Cleanup</b> – The process represented by this node is currently being cleaned
     * up.
     * <li><b>Spawned</b> – The node is spawned in the form of a separate spawned JDF.
     * The status Spawned can only be assigned to the original instance of the
     * spawned job. For details, see Section 4.4, Spawning and Merging.
     * <li><b>Stopped</b> – Execution has been stopped. If a job is Stopped, running may
     * be resumed later. This status may indicate a break, a pause, maintenance, or
     * a breakdown — in short, any pause that does not lead the job to be aborted.
     * <li><b>Completed</b> – Indicates that the node has been executed correctly, and is
     * finished.
     * <li><b>Aborted</b> – Indicates that the process executing the node has been aborted,
     * which means that execution will not be resumed again.
     * <li><b>Pool</b> – Indicates that the node processes partitioned resources and that the
     * Status varies depending on the partition keys. Details are provided in the
     * StatusPool element of the node.<p>
     * Derivation of the Status of a parent node from the Status of child nodes
     * is non-trivial and implementation-dependent</ul>
     */
    public static final class EnumNodeStatus extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumNodeStatus(String name)
        {
            super(name, m_startValue++);
        }
        
        /**
         * @param   enumName the name of the enum object to return    
         * @return  the enum object if enumName is valid. Otherwise null
         */
        public static EnumNodeStatus getEnum(String enumName)
        {
            return (EnumNodeStatus) getEnum(EnumNodeStatus.class, enumName);
        }
        
        /**
         * @param  enumValue the value of the enum object to return
         * @return the enum object if enumName is valid. Otherwise null
         */
        public static EnumNodeStatus getEnum(int enumValue)
        {
            return (EnumNodeStatus) getEnum(EnumNodeStatus.class, enumValue);
        }
        
        /**
         * @return a map of all orientation enums
         */
        public static Map getEnumMap()
        {
            return getEnumMap(EnumNodeStatus.class);
        }
        
        /**
         * @return a list of all orientation enums
         */
        public static List getEnumList()
        {
            return getEnumList(EnumNodeStatus.class);
        }
        
        /**
         * @return an iterator over the enum objects
         */
        public static Iterator iterator()
        {
            return iterator(EnumNodeStatus.class);
        }
        
        /**
         * @return a Vector with all String representatives of the enums
         * @deprecated
         */
        public static Vector getNamesVector()
        {
            final Vector namesVector = new Vector();
            final Iterator it = iterator(EnumNodeStatus.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        
        // enums accordng to JDF spec Table 3-4 Contents of a node
        public static final EnumNodeStatus Waiting           = new EnumNodeStatus(JDFConstants.WAITING);
        public static final EnumNodeStatus TestRunInProgress = new EnumNodeStatus(JDFConstants.TESTRUNINPROGRESS);
        public static final EnumNodeStatus Ready             = new EnumNodeStatus(JDFConstants.READY);
        public static final EnumNodeStatus FailedTestRun     = new EnumNodeStatus(JDFConstants.FAILEDTESTRUN);
        public static final EnumNodeStatus Setup             = new EnumNodeStatus(JDFConstants.SETUP);
        public static final EnumNodeStatus InProgress        = new EnumNodeStatus(JDFConstants.INPROGRESS);
        public static final EnumNodeStatus Cleanup           = new EnumNodeStatus(JDFConstants.CLEANUP);
        public static final EnumNodeStatus Spawned           = new EnumNodeStatus(JDFConstants.SPAWNED);
        public static final EnumNodeStatus Suspended         = new EnumNodeStatus(JDFConstants.SUSPENDED);
        public static final EnumNodeStatus Stopped           = new EnumNodeStatus(JDFConstants.STOPPED);
        public static final EnumNodeStatus Completed         = new EnumNodeStatus(JDFConstants.COMPLETED);
        public static final EnumNodeStatus Aborted           = new EnumNodeStatus(JDFConstants.ABORTED);
        public static final EnumNodeStatus Part              = new EnumNodeStatus(JDFConstants.PART);
        public static final EnumNodeStatus Pool              = new EnumNodeStatus(JDFConstants.POOL);
    }
    
    
    
    /**
     * 
     * The policy for this element indicates what happens when unsupported
     * settings, (i.e., subelements, attributes or attribute values), are present
     * in the element. Possible values are:<p>
     * <ul><li><b>BestEffort</b> – Substitute or ignore unsupported attributes, attribute
     * values, default attribute values, or elements and continue processing
     * the job.
     * <li><b>MustHonor</b> – Reject the job when any unsupported attributes,
     * attribute values, or elements are present.
     * <li><b>OperatorIntervention</b> – Pause job and query the operator
     * when any unsupported attributes, attribute values, or elements are
     * present. If a device has no operator intervention capabilities,
     * OperatorIntervention is treated as MustHonor.<p>
     * If not specified, SettingsPolicy is inherited from the parent element,
     * and if not specified in the parent element or further superior element,
     * the default value defaults to “BestEffort”.
     * In JDF 1.1 SettingsPolicy was specified in "Contents of a JDF
     * node” and “Contents of the abstract Resource element”.
     * It has been removed from JDF node and Resource and been
     * promoted to all JDF elements.
     */
    public static final class EnumSettingsPolicy extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumSettingsPolicy(String name)
        {
            super(name, m_startValue++);
        }
        
        /**
         * @param   enumName the name of the enum object to return    
         * @return  the enum object if enumName is valid. Otherwise null
         */
        public static EnumSettingsPolicy getEnum(String enumName)
        {
            return (EnumSettingsPolicy) getEnum(
                    EnumSettingsPolicy.class,
                    enumName);
        }
        
        /**
         * @param  enumValue the value of the enum object to return
         * @return the enum object if enumName is valid. Otherwise null
         */
        public static EnumSettingsPolicy getEnum(int enumValue)
        {
            return (EnumSettingsPolicy) getEnum(
                    EnumSettingsPolicy.class,
                    enumValue);
        }
        
        /**
         * @return a map of all orientation enums
         */
        public static Map getEnumMap()
        {
            return getEnumMap(EnumSettingsPolicy.class);
        }
        
        /**
         * @return a list of all orientation enums
         */
        public static List getEnumList()
        {
            return getEnumList(EnumSettingsPolicy.class);
        }
        
        /**
         * @return an iterator over the enum objects
         */
        public static Iterator iterator()
        {
            return iterator(EnumSettingsPolicy.class);
        }
        
        public static final EnumSettingsPolicy BestEffort           = new EnumSettingsPolicy(JDFConstants.SETTINGSPOLICY_BESTEFFORT);
        public static final EnumSettingsPolicy MustHonor            = new EnumSettingsPolicy(JDFConstants.SETTINGSPOLICY_MUSTHONOR);
        public static final EnumSettingsPolicy OperatorIntervention = new EnumSettingsPolicy(JDFConstants.SETTINGSPOLICY_OPERATORINTERVENTION);
    }
    
    
    /**
     * Colors of preprocessed products such as Wire-O binders and cover leaflets. 
     * The entries in the following table may be prefixed by either “Dark” or “Light”. 
     * The result may additionally be prefixed by “Clear” to indicate translucent material.
     * For example, “ClearDarkBlue” indicates a translucent dark blue, “ClearBlue” a 
     * translucent blue and “Blue” indicates an opaque blue.
     * <ul><li>Black 
     * <li>Blue
     * <li>Brown
     * <li>Buff 
     * <li>Cyan <i>New in JDF 1.2</i>
     * <li>Gold 
     * <li>Goldenrod 
     * <li>Gray 
     * <li>Green 
     * <li>Ivory 
     * <li>Magenta <i>New in JDF 1.2</i>
     * <li>MultiColor <i>New in JDF 1.1</i>
     * <li>Mustard <i>New in JDF 1.1</i>
     * <li>NoColor 
     * <li>Orange 
     * <li>Pink 
     * <li>Red 
     * <li>Silver 
     * <li>Turquoise 
     * <li>Violet 
     * <li>White 
     * <li>Yellow</ul> 
     */
    public static final class EnumNamedColor extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        private EnumNamedColor(String name)
        {
            super(name, m_startValue++);
        }
        
        /**
         * @param   enumName the name of the enum object to return    
         * @return  the enum object if enumName is valid. Otherwise null
         */
        public static EnumNamedColor getEnum(String enumName)
        {
            return (EnumNamedColor) getEnum(EnumNamedColor.class, enumName);
        }
        
        /**
         * @param  enumValue the value of the enum object to return
         * @return the enum object if enumName is valid. Otherwise null
         */
        public static EnumNamedColor getEnum(int enumValue)
        {
            return (EnumNamedColor) getEnum(EnumNamedColor.class, enumValue);
        }
        
        /**
         * @return a map of all orientation enums
         */
        public static Map getEnumMap()
        {
            return getEnumMap(EnumNamedColor.class);
        }
        
        /**
         * @return a list of all orientation enums
         */
        public static List getEnumList()
        {
            return getEnumList(EnumNamedColor.class);
        }
        
        /**
         * @return an iterator over the enum objects
         */
        public static Iterator iterator()
        {
            return iterator(EnumNamedColor.class);
        }
        /**
         * @return a Vector with all String representatives of the enums
         * @deprecated
         */
        public static Vector getNamesVector()
        {
            final Vector namesVector = new Vector();
            final Iterator it = iterator(EnumNamedColor.class);
            while (it.hasNext())
            {
                namesVector.addElement(((ValuedEnum) it.next()).getName());
            }
            
            return namesVector;
        }
        public static final EnumNamedColor White                = new EnumNamedColor(JDFConstants.NAMEDCOLOR_WHITE);
        public static final EnumNamedColor Black                = new EnumNamedColor(JDFConstants.NAMEDCOLOR_BLACK);
        public static final EnumNamedColor Gray                 = new EnumNamedColor(JDFConstants.NAMEDCOLOR_GRAY);
        public static final EnumNamedColor Red                  = new EnumNamedColor(JDFConstants.NAMEDCOLOR_RED);
        public static final EnumNamedColor Yellow               = new EnumNamedColor(JDFConstants.NAMEDCOLOR_YELLOW);
        public static final EnumNamedColor Green                = new EnumNamedColor(JDFConstants.NAMEDCOLOR_GREEN);
        public static final EnumNamedColor Blue                 = new EnumNamedColor(JDFConstants.NAMEDCOLOR_BLUE);
        public static final EnumNamedColor Turquoise            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_TURQUOISE);
        public static final EnumNamedColor Violet               = new EnumNamedColor(JDFConstants.NAMEDCOLOR_VIOLET);
        public static final EnumNamedColor Orange               = new EnumNamedColor(JDFConstants.NAMEDCOLOR_ORANGE);
        public static final EnumNamedColor Brown                = new EnumNamedColor(JDFConstants.NAMEDCOLOR_BROWN);
        public static final EnumNamedColor Gold                 = new EnumNamedColor(JDFConstants.NAMEDCOLOR_GOLD);
        public static final EnumNamedColor Silver               = new EnumNamedColor(JDFConstants.NAMEDCOLOR_SILVER);
        public static final EnumNamedColor Pink                 = new EnumNamedColor(JDFConstants.NAMEDCOLOR_PINK);
        public static final EnumNamedColor Buff                 = new EnumNamedColor(JDFConstants.NAMEDCOLOR_BUFF);
        public static final EnumNamedColor Ivory                = new EnumNamedColor(JDFConstants.NAMEDCOLOR_IVORY);
        public static final EnumNamedColor Goldenrod            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_GOLDENROD);
        public static final EnumNamedColor DarkWhite            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKWHITE);
        public static final EnumNamedColor DarkBlack            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKBLACK);
        public static final EnumNamedColor DarkGray             = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKGRAY);
        public static final EnumNamedColor DarkRed              = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKRED);
        public static final EnumNamedColor DarkYellow           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKYELLOW);
        public static final EnumNamedColor DarkGreen            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKGREEN);
        public static final EnumNamedColor DarkBlue             = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKBLUE);
        public static final EnumNamedColor DarkTurquoise        = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKTURQUOISE);
        public static final EnumNamedColor DarkViolet           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKVIOLET);
        public static final EnumNamedColor DarkOrange           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKORANGE);
        public static final EnumNamedColor DarkBrown            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKBROWN);
        public static final EnumNamedColor DarkGold             = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKGOLD);
        public static final EnumNamedColor DarkSilver           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKSILVER);
        public static final EnumNamedColor DarkPink             = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKPINK);
        public static final EnumNamedColor DarkBuff             = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKBUFF);
        public static final EnumNamedColor DarkIvory            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKIVORY);
        public static final EnumNamedColor DarkGoldenrod        = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKGOLDENROD);
        public static final EnumNamedColor DarkMustard          = new EnumNamedColor(JDFConstants.NAMEDCOLOR_DARKMUSTARD);
        public static final EnumNamedColor LightWhite           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTWHITE);
        public static final EnumNamedColor LightBlack           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTBLACK);
        public static final EnumNamedColor LightGray            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTGRAY);
        public static final EnumNamedColor LightRed             = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTRED);
        public static final EnumNamedColor LightYellow          = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTYELLOW);
        public static final EnumNamedColor LightGreen           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTGREEN);
        public static final EnumNamedColor LightBlue            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTBLUE);
        public static final EnumNamedColor LightTurquoise       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTTURQUOISE);
        public static final EnumNamedColor LightViolet          = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTVIOLET);
        public static final EnumNamedColor LightOrange          = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTORANGE);
        public static final EnumNamedColor LightBrown           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTBROWN);
        public static final EnumNamedColor LightGold            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTGOLD);
        public static final EnumNamedColor LightSilver          = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTSILVER);
        public static final EnumNamedColor LightPink            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTPINK);
        public static final EnumNamedColor LightBuff            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTBUFF);
        public static final EnumNamedColor LightIvory           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTIVORY);
        public static final EnumNamedColor LightGoldenrod       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTGOLDENROD);
        public static final EnumNamedColor LightMustard         = new EnumNamedColor(JDFConstants.NAMEDCOLOR_LIGHTMUSTARD);
        public static final EnumNamedColor ClearWhite           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARWHITE);
        public static final EnumNamedColor ClearBlack           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARBLACK);
        public static final EnumNamedColor ClearGray            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARGRAY);
        public static final EnumNamedColor ClearRed             = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARRED);
        public static final EnumNamedColor ClearGreen           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARGREEN);
        public static final EnumNamedColor ClearBlue            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARBLUE);
        public static final EnumNamedColor ClearTurquoise       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARTURQUOISE);
        public static final EnumNamedColor ClearViolet          = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARVIOLET);
        public static final EnumNamedColor ClearOrange          = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARORANGE);
        public static final EnumNamedColor ClearBrown           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARBROWN);
        public static final EnumNamedColor ClearGold            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARGOLD);
        public static final EnumNamedColor ClearSilver          = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARSILVER);
        public static final EnumNamedColor ClearPink            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARPINK);
        public static final EnumNamedColor ClearBuff            = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARBUFF);
        public static final EnumNamedColor ClearIvory           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARIVORY);
        public static final EnumNamedColor ClearGoldenrod       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARGOLDENROD);
        public static final EnumNamedColor ClearMustard         = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARMUSTARD);
        public static final EnumNamedColor ClearDarkWhite       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKWHITE);
        public static final EnumNamedColor ClearDarkBlack       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKBLACK);
        public static final EnumNamedColor ClearDarkGray        = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKGRAY);
        public static final EnumNamedColor ClearDarkRed         = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKRED);
        public static final EnumNamedColor ClearDarkYellow      = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKYELLOW);
        public static final EnumNamedColor ClearDarkGreen       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKGREEN);
        public static final EnumNamedColor ClearDarkBlue        = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKBLUE);
        public static final EnumNamedColor ClearDarkTurquoise   = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKTURQUOISE);
        public static final EnumNamedColor ClearDarkViolet      = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKVIOLET);
        public static final EnumNamedColor ClearDarkOrange      = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKORANGE);
        public static final EnumNamedColor ClearDarkBrown       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKBROWN);
        public static final EnumNamedColor ClearDarkGold        = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKGOLD);
        public static final EnumNamedColor ClearDarkSilver      = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKSILVER);
        public static final EnumNamedColor ClearDarkPink        = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKPINK);
        public static final EnumNamedColor ClearDarkBuff        = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKBUFF);
        public static final EnumNamedColor ClearDarkIvory       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKIVORY);
        public static final EnumNamedColor ClearDarkGoldenrod   = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARDARKGOLDENROD);
        public static final EnumNamedColor ClearLightWhite      = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTWHITE);
        public static final EnumNamedColor ClearLightBlack      = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTBLACK);
        public static final EnumNamedColor ClearLightGray       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTGRAY);
        public static final EnumNamedColor ClearLightRed        = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTRED);
        public static final EnumNamedColor ClearLightYellow     = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTYELLOW);
        public static final EnumNamedColor ClearLightGreen      = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTGREEN);
        public static final EnumNamedColor ClearLightBlue       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTBLUE);
        public static final EnumNamedColor ClearLightTurquoise  = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTTURQUOISE);
        public static final EnumNamedColor ClearLightViolet     = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTVIOLET);
        public static final EnumNamedColor ClearLightOrange     = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTORANGE);
        public static final EnumNamedColor ClearLightBrown      = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTBROWN);
        public static final EnumNamedColor ClearLightGold       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTGOLD);
        public static final EnumNamedColor ClearLightSilver     = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTSILVER);
        public static final EnumNamedColor ClearLightPink       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTPINK);
        public static final EnumNamedColor ClearLightBuff       = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTBUFF);
        public static final EnumNamedColor ClearLightIvory      = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTIVORY);
        public static final EnumNamedColor ClearLightGoldenrod  = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTGOLDENROD);
        public static final EnumNamedColor ClearLightMustard    = new EnumNamedColor(JDFConstants.NAMEDCOLOR_CLEARLIGHTMUSTARD);
        public static final EnumNamedColor MultiColor           = new EnumNamedColor(JDFConstants.NAMEDCOLOR_MULTICOLOR);
        public static final EnumNamedColor NoColor              = new EnumNamedColor(JDFConstants.NAMEDCOLOR_NOCOLOR);
    }
    
    
    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFElement[  --> " + super.toString() + " ]";
    }
    
    
    /**
     * Mother of all validators
     * 
     * @param level validation level
     * <ul><li>level EnumValidationLevel.None: always return true;
     * <li>level EnumValidationLevel.Construct: incomplete and null elements are valid. 
     * <li>level EnumValidationLevel.Incomplete: incomplete  elements are valid
     * <li>level EnumValidationLevel.Complete: full validation
     * <li>level EnumValidationLevel.RecursiveIncomplete: incomplete validation but follow links
     * <li>level EnumValidationLevel.RecursiveComplete: full validation and follow links downward</ul>
     * 
     * @return boolean the validity of the node
     */
    public boolean isValid(EnumValidationLevel level)
    {
        return isValid_JDFElement(level);
    }
    
    /**
     * Mother of all validators; this method is for direct access to the JDFElement valid 
     * method and short-circuit all overriding virtual methods
     * 
     * @param level validation level
     * <ul><li>level EnumValidationLevel.None: always return true;
     * <li>level EnumValidationLevel.Construct: incomplete and null elements are valid. 
     * <li>level EnumValidationLevel.Incomplete: incomplete  elements are valid
     * <li>level EnumValidationLevel.Complete: full validation
     * <li>level EnumValidationLevel.RecursiveIncomplete: incomplete validation but follow links
     * <li>level EnumValidationLevel.RecursiveComplete: full validation and follow links downward</ul>
     * 
     * @return boolean the validity of the node
     */
    public boolean isValid_JDFElement(EnumValidationLevel level)
    {
        // there is no explicit extension type --> these are always assumed valid
        final Class class1 = this.getClass();
        if(class1==JDFElement.class || class1==JDFResource.class)
            return true;
        
        try
        {
            if (getInvalidAttributes(level, true, 1).size() > 0 ||
                getInvalidElements(level, true, 1).size() > 0)
            {
                return false;
            }
            
            if ((level.equals(EnumValidationLevel.RecursiveIncomplete))
                    || (level.equals(EnumValidationLevel.RecursiveComplete)))
            {
                final EnumValidationLevel valDown =
                    (level == EnumValidationLevel.RecursiveIncomplete)
                    ? EnumValidationLevel.Incomplete : EnumValidationLevel.Complete;
                
                final VElement v = getChildElementVector(null,null,null,true,0,false);
                final int size = v.size();
                for (int i = 0; i < size; i++)
                {
                    if (v.elementAt(i) instanceof JDFRefElement)
                    {
                        final JDFResource res = ((JDFRefElement) v.elementAt(i)).getTarget();
                        if (!res.isValid(valDown))
                        {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        catch (JDFException e)
        {
            // snafu --> it probably isn't valid...
            return false;
        }
    }
    
    /**
     * Mother of all version fixing routines
     *
     * uses heuristics to modify this element and its children to be compatible with a given version
     * in general, it will be able to move from low to high versions but potentially fail 
     * when attempting to move from higher to lower versions
     *
     * @param version version the resulting element should correspond to
     * @return true if successful
     */
    public boolean fixVersion(EnumVersion version){
        boolean bRet=true;
        VElement v=getChildElementVector_KElement(null,null,null,true,-1); // do not follow refelements
        final int size = v.size();
        for(int i=0;i<size;i++){
            KElement e=(KElement) v.elementAt(i);
            if (e instanceof JDFElement) // skip stuff in unknown namespaces
            {
                JDFElement j = (JDFElement) e;                
                bRet=j.fixVersion(version) && bRet;
            }
        }
        
        // replace all "~" with " ~ "
        JDFAttributeMap m=getAttributeMap();
        final Iterator it = m.getKeyIterator();
        AttributeInfo ai=getTheAttributeInfo();
        while (it.hasNext())
        {
            String key = (String)it.next();
            String value = m.get(key);
            
            if(value.indexOf(JDFConstants.TILDE)>=0){
                try{
                    JDFNumberRangeList nrl=new JDFNumberRangeList(value);
                    setAttribute(key,nrl,null);
                }catch(JDFException e){
                    // do nothing
                }catch (DataFormatException e) {
                    // do nothing
                }
            } 
            if(bFixVersionIDFix && value.length()>0 && StringUtils.isNumeric(value.substring(0,1)))
            {
                EnumAttributeType atType=ai.getAttributeType(key);
                if(atType!=null) 
                {
                    if(atType.equals(EnumAttributeType.ID) || atType.equals(EnumAttributeType.IDREF))
                    {
                        value="_"+value;
                        setAttribute(key, value);
                    }
                    else if(atType.equals(EnumAttributeType.IDREFS))
                    {
                        VString vvalues=new VString(value," ");
                        for(int i=0;i<vvalues.size();i++)
                        {
                            String s=vvalues.stringAt(i);
                            if(s.length()>0 && StringUtils.isNumeric(s.substring(0,1)))
                            {
                                s="_"+s;
                                vvalues.setElementAt(s, i);
                            }
                        }
                        setAttribute(key, vvalues,null);
                    }
                }
            }
        }
        return bRet;
    }
    
    /**
     * Check Existance of attribute SettingsPolicy
     * @param bInherit recurse through ancestors when searching
     * @return true if attribute exists
     * @deprecated use hasAttribute
     */
    public boolean hasSettingsPolicy(boolean bInherit)
    {
        return hasAttribute(AttributeName.SETTINGSPOLICY, null, bInherit);
    }
    
    /**
     * Remove attribute SettingsPolicy
     * @deprecated
     */
    public void removeSettingsPolicy()
    {
        removeAttribute(AttributeName.SETTINGSPOLICY, null);
    }
    
    /**
     * Remove attribute BestEffortExceptions
     */
    public void removeBestEffortExceptions()
    {
        removeAttribute(AttributeName.BESTEFFORTEXCEPTIONS, null);
    }
    
//    /**
//     * GetAttribute - get an attribute value also check ns JDF
//     *
//     * @param String attrib       - attribute name
//     * @param String nameSpaceURI - attribute namespace uri
//     * @param String def          - attribute default that is returned if no attribute exists
//     *
//     * @return String - the attribute
//     * @deprecated use KElement instead
//     * 
//     * default: getAttribute(attrib, null, JDFConstants.EMPTYSTRING)
//     */
//    
//  public String getAttribute_JDFElement(String attrib, String nameSpaceURI, String def){
    //       return super.getAttribute_KElement(attrib, nameSpaceURI, def);
    //}
    
    /**
     * Gets the one and reference to an ID
     *
     * @return String the rRef attribute
     * @deprecated use getrRef of the coresponding sub classes instead
     */
    public String getHRef()
    {
        return getAttribute(JDFConstants.RREF, null, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * append a reference to a target node automatically generate a unique ID
     * attribute for target, if it does not exist
     *
     * @param target       the element to reference
     * @param refAttribute name of the refering attribute, e.g. hRef, rRef
     * @param preSet       preset value of the ID attribute - defaults to autogenerated
     *
     * @return JDFElement
     * 
     * @default AppendHRef(target, null, JDFConstants.EMPTYSTRING)
     */
    public JDFElement appendHRef(
            JDFResource target,
            String refAttribute,
            String preSet)
    {
        if (target == null)
        {
            return null;
        }
        
        String idRef = target.getID();
        
        if (idRef==null || idRef.equals(JDFConstants.EMPTYSTRING))
        {
            target.appendAnchor(preSet);
            idRef = target.getID();
        }
        
        return appendHRef(idRef, refAttribute, null);
    }
    
    /**
     * append a reference with a specific id
     *
     * @param idRef        value of the ID-IDREF pair
     * @param refAttribute value of the IDREF tag
     * @param nameSpaceURI nameSpaceURI of the IDREF tag
     *
     * @return JDFElement
     * 
     * @default AppendHRef(idRef, JDFConstants.EMPTYSTRING, null)
     */
    public JDFElement appendHRef(
            String idRef,
            String refAttribute,
            String nameSpaceURI)
    {
        if (idRef==null || idRef.length() < 1)
        {
            return null;
        }
        if (refAttribute==null || refAttribute.equals(JDFConstants.EMPTYSTRING))
        {
            refAttribute = JDFConstants.RREF;
        }
        
        setAttribute(refAttribute, idRef, nameSpaceURI);
        
        return this;
    }
    
    
    
    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the JDFDuration to set
     * @param nameSpaceURI the nameSpace the attribute is in
     * 
     * default: setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFDuration value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key, value.getDurationISO(), nameSpaceURI);
    }
    
    /**
     * Sets an element attribute
     *
     * @param key           the name of the attribute to set
     * @param value         the JDFDurationRangeList to set
     * @param nameSpaceURI  the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFDurationRangeList value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key, value.toString(), nameSpaceURI);
    }
    
    /**
     * Sets an element attribute
     *
     * @param key           the name of the attribute to set
     * @param value         the JDFDateTimeRangeList to set
     * @param nameSpaceURI  the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFDateTimeRangeList value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key, value.toString(), nameSpaceURI);
    }
    
    /**
     * Sets an element attribute
     *
     * @param key           the name of the attribute to set
     * @param value         the JDFRectangleList to set
     * @param nameSpaceURI  the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFRectangleRangeList value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key, value.toString(), nameSpaceURI);
    }
    
    /**
     * setAttribute - Sets an element attribute
     *
     * @param key           the name of the attribute to set
     * @param value         the JDFShapeRangeList to set
     * @param nameSpaceURI  the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFShapeRangeList value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key, value.toString(), nameSpaceURI);
    }
    
    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the JDFNumList to set        
     * @param nameSpaceURI the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFNumList value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key, value.toString(), nameSpaceURI);
    }
    
    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the JDFNumberRange to set        
     * @param nameSpaceURI the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFNumberRange value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key, value.toString(), nameSpaceURI);
    }
    
    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the JDFIntegerRange to set
     * @param nameSpaceURI the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFIntegerRange value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key, value.toString(), nameSpaceURI);
    }
    
    
    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the JDFNameRangeList to set
     * @param nameSpaceURI the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFNameRangeList value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key,value.toString(),nameSpaceURI);
    }
    
    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the JDFNameRange to set
     * @param nameSpaceURI the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFNameRange value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key,value.toString(),nameSpaceURI);
    }   
    
    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the JDFNumberRangeList to set
     * @param nameSpaceURI the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFNumberRangeList value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key,value.toString(),nameSpaceURI);
    }
    
    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the JDFIntegerRangeList to set
     * @param nameSpaceURI the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFIntegerRangeList value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key,value.toString(),nameSpaceURI);
    }
    
    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the JDFXYpairRange to set
     * @param nameSpaceURI the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFXYPairRange value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key,value.toString(),nameSpaceURI);
    }
    
    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the JDFXYPairRangeList to set
     * @param nameSpaceURI the nameSpace the attribute is in
     * 
     * @default setAttribute(key, value, null)
     */
    public void setAttribute(String key, JDFXYPairRangeList value, String nameSpaceURI)
    {
        if(value==null)
            removeAttribute(key,nameSpaceURI);
        else
            super.setAttribute(key,value.toString(),nameSpaceURI);
    }
    
    /**
     * Sets an element attribute
     *
     * @param key          the name of the attribute to set
     * @param value        the JDFNameRange to set
     * @param nameSpaceURI the nameSpace the attribute is in
     * @deprecated
     * @default setvStringAttribute(key, value, null)
     */
    public void setvStringAttribute(
            String key,
            JDFNameRange value,
            String nameSpaceURI)
    {
        setAttribute(key, value.toString(), nameSpaceURI);
    }
    
    /** 
     * return true if no more than one of att1 or att2 exists or 
     * exactly one of att1 or att2 exists depending on level
     *
     * @param level validation level, if level allows optional, no attribute also returns true
     * @param att1 name of first attribute
     * @param att2 name of second attribute
     * @param att3 name of third optional attribute
     * @param att4 name of fourth optional attribute
     * @return boolean true combination is valid
     * @default exclusiveOneOfAttribute(EnumValidationLevel level, 
     String att1, 
     String att2,
     JDFConstants.EMPTYSTRING,
     JDFConstants.EMPTYSTRING)
     */
    public boolean exclusiveOneOfAttribute(EnumValidationLevel level, 
            String att1, 
            String att2,
            String att3,
            String att4)
    {
        int n = 0;
        boolean hasAtt1 = hasAttribute(att1);
        n += hasAtt1 ? 1 : 0;
        n += hasAttribute(att2) ? 1 : 0;
        if(!att3.equals(JDFConstants.EMPTYSTRING))
            n += hasAttribute(att3) ? 1 : 0;
        if(!att4.equals(JDFConstants.EMPTYSTRING))
            n += hasAttribute(att4) ? 1 : 0;
        
        if(requiredLevel(level))
        {
            // exactly one or more than one, but not this one
            return n==1 || ((!hasAtt1)&&(n>=1));
        } 
        // at most one is good or more than one, but not this one
        return n<=1 || !hasAtt1;
    }
    
    /** 
     * return true if no more than one of att1 or att2 exists or 
     * exactly one of att1 or att2 exists depending on level
     *
     * @param level validation level, if level allows optional, no attribute also returns true
     * @param elm1 name of first attribute
     * @param ns1 namespace URI of first attribute
     * @param elm2 name of first attribute
     * @param ns2 namespace URI of first attribute
     * @return boolean true combination is valid
     */
    public boolean exclusiveOneOfElement(EnumValidationLevel level, 
            String elm1, 
            String ns1,
            String elm2,
            String ns2)
    {
        int n = 0;
        n += hasChildElement(elm1, ns1) ? 1 : 0;
        n += hasChildElement(elm2, ns2) ? 1 : 0;
        if(requiredLevel(level))
        {
            // exactly one
            return n==1;
        }
        // at most one is good
        return n<=1;
    }
    
    
    /**
     * GetChildIds - get a set of all known id's in child nodes
     *
     * @param attrib       attribute name of the ID attribute, defaults to "ID"
     * @param element      name of the elements to be searched
     * @param nameSpaceURI attribute namespace uri of the elements to include in the list
     *
     * @return Vector - a vector of all known ID elements
     * 
     * @default GetChildIds(AttributeName.ID, null, null)
     */
    public Vector getChildIds(
            String attrib,
            String element,
            String nameSpaceURI)
    {
        final Vector setID = new Vector();
        
        final VElement nl = getElementsByTagName_KElement(element, nameSpaceURI);
        final int l = nl.size();
        
        if (l != 0)
        {
            nl.elementAt(l - 1);
        }
        
        for (int i = 0; i < l; i++)
        {
            final KElement kElem = (KElement) nl.elementAt(i);
            final String s       = kElem.getAttribute(
                    attrib, 
                    nameSpaceURI, 
                    JDFConstants.EMPTYSTRING);
            
            if (s.equals(JDFConstants.EMPTYSTRING))
            {
                continue;
            }
            setID.addElement(s);
        }
        
        return setID;
    }
    
    /**
     * Get the target element of a link (string id)
     * @return JDFElement - the element that this reference refers to
     * @deprecated use the respective subclasses getTarget functions
     */
    public JDFResource getTarget()
    {
        return (JDFResource) getTarget_JDFElement(getAttribute(AttributeName.RREF), AttributeName.ID);
    }
    
    /**
     * Get the target element of a link<br>
     * overwrites KElement
     * @param id value of the ID to search
     * @param attrib name of the ID tag, defaults to "ID"
     * @return JDFElement - the element that this reference refers to
     * 
     * default: GetTarget(GetHRef(), AttributeName.ID)
     */
    public JDFElement getTarget_JDFElement(String id, String attrib)
    {
        return (JDFElement) super.getTarget(id, attrib);
    }
    
    /**
     * Get the JDF root
     * @return JDFNode - The root of the JDF File
     */
    public JDFNode getJDFRoot()
    {
        return (JDFNode) getDeepParent(ElementName.JDF, Integer.MAX_VALUE);
    }
    
    /**
     * Get the JMF root
     * @return JDFJMF - the root of the JMF file
     */
    public JDFJMF getJMFRoot()
    {
        return (JDFJMF) getDeepParent(ElementName.JMF, Integer.MAX_VALUE);
    }
    
    /**
     * IsRefElement - is this thing a RefElement?
     *
     * @return true, if this is a refElement
     * @deprecated use instanceof JDFRefElement
     */
    public boolean isRefElement()
    {
        return this instanceof JDFRefElement;
    }
    
    /**
     * IsRefElement - is this thing a RefElement?
     *
     * @return true, if this is a refElement
     * @deprecated use instanceof JDFRefElement
     */
    public static boolean isRefElementStatic(KElement kElem)
    {
        return kElem instanceof JDFRefElement;
    }
    
    /**
     * tests whether this Element is a Resource.
     * @return boolean - true, if it is a Resource.
     * @deprecated use instanceof JDFResource instead
     */
    public boolean isResource()
    {
        return this instanceof JDFResource;
    }
    
    /**
     * tests whether this Element is a Resource.
     * @return boolean - true, if it is a Resource.
     * @deprecated use instanceof JDFResource instead
     */
    static public boolean isResourceStatic(KElement e)
    {
        return e instanceof JDFResource;
    }
    
    /**
     * is this thing a ResourceUpdate?
     * @return true, if this is a ResourceUpdate 
     */
    public boolean isResourceUpdate()
    {
        return this.getLocalName().endsWith(ElementName.UPDATE);
    }
    
    /**
     * Method IsResourceLink.
     * @return boolean
     * @deprecated use instanceof JDFResourceLink instead
     */
    public boolean isResourceLink()
    {
        return this instanceof JDFResourceLink;
    }
    
    /**
     * Method IsResourceLinkStatic.
     * @param kElem
     * @return boolean
     * @deprecated use instanceof JDFResourceLink instead
     * 
     */
    public static boolean isResourceLinkStatic(KElement kElem)
    {
        return kElem instanceof JDFResourceLink;
    }
    
    /**
     * Check, if this is an Comment element
     * @return boolean - true, if this is an Comment-Element, otherwise false
     * @deprecated use instanceof JDFResourceLink instead
     */
    public boolean isComment()
    {
        return this instanceof JDFComment;
    }
    
    /**
     * Method isCommentStatic.
     * @param kElem
     * @return boolean - true, if this is an Comment-Element, otherwise false
     * @deprecated use instanceof JDFComment instead
     */
    public static boolean isCommentStatic(KElement kElem)
    {
        return kElem instanceof JDFComment;
    }
    
    /**
     * is this thing a JDF Node?
     * @return true if this is a JDF Node
     * @deprecated use instanceof JDFNode instead
     */
    public boolean isJDFNode()
    {
        return this instanceof JDFNode;
    }
    
    /**
     * Method IsInJDFNameSpace.
     * @return boolean
     * @deprecated - use isInJDFNameSpaceStatic(kElem)
     */
    public boolean isInJDFNameSpace()
    {
        return isInJDFNameSpaceStatic(this);
    }
    
    /**
     * checks whether kElem is in the JDF namespace 
     * @param kElem the KElement to check
     * @return boolean - true, if kElem is in the JDF namespace
     */
    public static boolean isInJDFNameSpaceStatic(KElement kElem)
    {
        boolean fIsInJDFNameSpace = true;
        
        final String ns = kElem.getNamespaceURI();
        
        if (ns != null)
        {
            fIsInJDFNameSpace =
                ns.equals(JDFConstants.EMPTYSTRING)
                ||  (ns.compareToIgnoreCase("http://www.CIP4.org/JDFSchema_1_1") == 0)
                ||  (ns.compareToIgnoreCase("http://www.CIP4.org/JDFSchema_1") == 0);
        }
        
        return fIsInJDFNameSpace;
    }
    
    /**
     * Creates an inter resource link  to a target resource.
     * @param target - Target resource to link  to
     */
    public JDFRefElement refElement(JDFResource target)
    {
        final JDFRefElement newRef = 
            (JDFRefElement) appendElement(target.getNodeName() + JDFConstants.REF, target.getNamespaceURI());
        
        JDFResource root = target.getResourceRoot();
        
        // check whether it is a resource element
        if (target.isResourceElement())
        {
            newRef.appendHRef(target, AttributeName.RSUBREF, JDFConstants.EMPTYSTRING);
        }
        
        // check whether it is a resource leaf or root
        if (!target.isResourceRoot())
        {
            final JDFAttributeMap partMap = target.getPartMap();
            newRef.setPartMap(partMap);
        }
        // ID is appended to the resource root of target
        newRef.appendHRef(root, AttributeName.RREF, JDFConstants.EMPTYSTRING);
        
        //move the resource to the closest common ancestor if it is not already an ancestor of this
        JDFNode parent=root.getParentJDF();
        
        //move the resource to the closest common ancestor if it is not already an ancestor of this
        while (parent!=null && !parent.isAncestor(this))
        {
            parent=root.getParentJDF();
            if(parent==null)
                break;
            parent=parent.getParentJDF();
            if(parent==null)
            {
                newRef.deleteNode(); // cleanup
                throw new JDFException("appendRefElement resource is not in the same document");
            }
            
            root = (JDFResource)parent.getCreateResourcePool().moveElement(root, null);
        }        
        
        return newRef;
    }
    
    /**
     * Removes an inter resource link from this Element.
     * @param target Target to remove
     */
    public void removeRefElement(JDFResource target)
    {
        String id = target.getID();
        
        if (id.equals(JDFConstants.EMPTYSTRING))
        {
            throw new JDFException("RemoveRefElement: target has no id");
        }
        
        final VElement v = getChildElementVector(target.getRefString(), null, null, true, 0, false);
        for (int i = 0; i < v.size(); i++)
        {
            final JDFRefElement re = (JDFRefElement) v.elementAt(i);
            
            if (re.getrRef().equals(id) || re.getrSubRef().equals(id))
            {
                re.deleteNode();
                break;
            }
        }
    }
    
    
    /**
     * gets the vector of all RefElements
     * @return VElement - vector of JDFRefElements
     */
    public VElement getRefElements()
    {
        final VElement v = getChildElementVector(null,null,null, true,0,false);
        // loop backwords so that I don't invalidate the vector by deleting
        for (int i = v.size() - 1; i >= 0; i--)
        {
            final KElement e = v.item(i);
            //cant be null
            if (!(e instanceof JDFRefElement))
            {
                v.remove(i);
            }
        }
        return v;
    }
    
    /**
     * UpDates rRefs attribute of this Element, corresponding to the child
     * reference Elements of this Element.
     * @deprecated use KElement.fillHashSet(ElementName.RREF,null,hashSet)
     */
    public VString upDaterRefs()
    {
        final VString vrRefs = new VString();
        final VElement v =
            getChildrenByTagName(null, null, new JDFAttributeMap(JDFConstants.RREF, ""),false, true, 0);
        // grabemall
        
        final int size = v.size();
        for (int i = 0; i < size; i++)
        {
            final KElement el_i = (KElement) (v.elementAt(i));
            vrRefs.add(el_i.getAttribute(JDFConstants.RREF, null, JDFConstants.EMPTYSTRING));
        }
        vrRefs.unify();
        return vrRefs;
    }
    
    
    /**
     * inline refelements that match nodename and nameSpaceURI
     * @param nodeName name of the refelement (without the "Ref")
     * @param nameSpaceURI
     * @param bDirect if true, get direct children only, else recurse into all sub-elements
     */
    public void inlineRefElements( String nodeName,String nameSpaceURI, boolean bDirect)
    {
        VElement v     = getRefElements();
        int i;
        int vSize = v.size();
        for (i = 0; i < vSize; i++)
        {
            final JDFRefElement re = (JDFRefElement) v.elementAt(i);
            // it fits - inline it
            if (re.fitsName(nodeName, nameSpaceURI))
            {
                re.inlineRef();
            }
        }
        
        // now loop over all (!) children, to see if any descendants match
        if (!bDirect)
        {
            v = this.getChildElementVector_KElement(null,null,null,true,0);
            vSize = v.size();
            for (i = 0; i < vSize; i++)
            {           
                if(v.elementAt(i) instanceof JDFElement)
                {
                JDFElement e=(JDFElement)v.elementAt(i);
                e.inlineRefElements(nodeName, nameSpaceURI, bDirect);
            }
        }
    }
    }
    
    /** 
     * Get all children from the actual element matching the given conditions<br>
     * does NOT get refElement targets although the attributes are checked in the target elements in case of refElements
     *
     * @param nodeName       element name you are searching for
     * @param nameSpaceURI   nameSpace you are searching for
     * @param mAttrib        attributes you are lokking for
     * @param bAnd           if true, a child is only added 
     *                       if it has all attributes specified in Attributes mAttrib
     * @param maxSize        maximum size of the element vector
     * @param bResolveTarget if true, returns the targets of the refElements<br>
     *                       else the refElements are returned (if mAttrib != null), additionally 
     *                       the attributes of the target are checked)
     *
     * @return VElement      - vector with all elements found 
     * 
     * @see org.cip4.jdflib.core.KElement#getChildElementVector(
     *          java.lang.String, java.lang.String, 
     *          org.cip4.jdflib.datatypes.JDFAttributeMap, 
     *          boolean, int, boolean)
     * 
     * default: getChildElementVector(null, null,null, true, 0, false)
     */
    public VElement getChildElementVector(String nodeName,String nameSpaceURI,
            JDFAttributeMap mAttrib, boolean bAnd, int maxSize, boolean bResolveTarget)
    {
        return getChildElementVector_JDFElement(nodeName, nameSpaceURI, mAttrib, bAnd, maxSize, bResolveTarget);
    }
    
    public VElement getChildElementVector_JDFElement( String nodeName, String nameSpaceURI, JDFAttributeMap mAttrib,
            boolean bAnd, int maxSize, boolean bResolveTarget)
    {
        final VElement v = new VElement();
        if(isWildCard(nodeName))
            nodeName=null;
        if(isWildCard(nameSpaceURI))
            nameSpaceURI=null;
        if(mAttrib!=null && mAttrib.isEmpty())
            mAttrib=null;
        if(maxSize==0)
            maxSize=-1;

        final boolean bAlwaysFit = nodeName==null && nameSpaceURI==null;
        final boolean bMapEmpty = mAttrib == null;

        int iSize = 0;
        KElement kElem = getFirstChildElement();

        while (kElem != null)
        {
            if (bAlwaysFit || kElem.fitsName(nodeName, nameSpaceURI))
            {
                if (bResolveTarget && (kElem instanceof JDFRefElement))
                {
                    try
                    {
                        JDFRefElement ref = (JDFRefElement)kElem;
                        KElement target = ref.getTarget();

                        // in case there is no element for the REF, target will be null and will be skipped
                        if (target != null)
                        {  // we want the jdfElem version of IncludesAttributes, so we must upcast
                            if (bMapEmpty || target.includesAttributes(mAttrib, bAnd))
                            {
                                v.addElement(target);
                                iSize++;
                            }
                        }
                    }
                    catch (JDFException ex)
                    {
                        // simply skip invalid refelements
                    }
                }
                else if(bMapEmpty || kElem.includesAttributes(mAttrib, bAnd))
                {
                    v.addElement(kElem);
                    iSize++;
                }
                if (iSize == maxSize)
                {
                    break;
                }
            }       
            kElem = kElem.getNextSiblingElement();
        }        
        return v;
    }

    /* (non-Javadoc)
     * @see org.cip4.jdflib.core.KElement#getElementNameVector()
     */
    public VString getElementNameVector()
    {
        final VElement e = getChildElementVector(null,null,null,true,0,false);
        
        final VString v = new VString();
        for (int i = 0; i < e.size(); i++)
        {
            final KElement el = (KElement) e.elementAt(i);
            String s = el.getNodeName();
            if (el instanceof JDFRefElement)
            {
                s = s.substring(0, s.length() - AttributeName.REF.length());
            }
            v.appendUnique(s);
        }
        return v;
    }
    
    
    
    /* (non-Javadoc)
     * @see org.cip4.jdflib.core.KElement#GetElement(String, String, int)
     * 
     * @default getElement(nodeName, null, 0)
     */
    public KElement getElement(String nodeName, String nameSpaceURI, int iSkip)
    {
        return getElement_JDFElement(nodeName, nameSpaceURI, iSkip);
    }
    
    /**
     * same as KElement.GetElement, but follows references as well.<br>
     * Thus the returned value is the target of the refElement unless the requested element is explicitely a refElement,
     * which is specified by requesting an element with nodeName="XXXRef".<br>
     * Invalid refelements are simply skipped and are not filled into the vector
     *
     * @param nodeName       name of the child node to get
     * @param nameSpaceURI   namespace to search for
     * @param iSkip          get the iSkipth element that fits
     * 
     * @return KElement             the matching element
     * 
     * @default getElement_JDFElement(nodeName, null, 0)
     */
    public KElement getElement_JDFElement(
            String nodeName,
            String nameSpaceURI,
            int iSkip)
    {
        // loop over the list
        int i                       = 0;
        KElement jdfElem          = getFirstChildElement();
        final boolean bExplicitRefElement = (nodeName!=null) && (nodeName.endsWith(JDFConstants.REF));
        
        while (jdfElem != null)
        {
            if (jdfElem.fitsName(nodeName, nameSpaceURI))
            {
                // this guy is the one
                if (i++ == iSkip)
                {
                    // follow valid (!) refElements, invalid refelements are ignored
                    // 300502 RP added check for explicit refelements
                    if (jdfElem instanceof JDFRefElement && !bExplicitRefElement)
                    {
                        try
                        {
                            final JDFRefElement re = (JDFRefElement) jdfElem;
                            final KElement target = re.getTarget();
                            if (target != null)
                            {
                                return target;
                            }
                        }
                        catch (JDFException ex)
                        {
                            // simply ignore invalid refelements¬
                        }
                        i--; // this one was screwed up -> don't count it
                    }
                    else
                    { // not a refElement or explicitly want the refElement
                        return jdfElem;
                    }
                }
            }
            
            jdfElem = jdfElem.getNextSiblingElement();
        }
        
        // found no match
        return null;
    }
    
    /**
     * same as {@link KElement#numChildElements(String, String)}, but also follows references.<br>
     * Invalid refelements are simply skipped. 
     *
     * @param nodeName       the nodes to count
     * @param nameSpaceURI   the nameSpace to look in
     * @return int - the number of child elements
     * 
     * @default numChildElements(String nodeName, null)
     */
    public int numChildElements(String nodeName, String nameSpaceURI)
    {
        return getChildElementVector(nodeName, nameSpaceURI, null, true, 0, false).size();
    }
    
    public int numChildElements_JDFElement(String nodeName, String nameSpaceURI)
    {
        return getChildElementVector_JDFElement(nodeName, nameSpaceURI, null, true, 0, false).size();
    }
    /**
     * Remove children     that match nodeName and nameSpaceURI
     * @param nodeName     name of the child node to get, if empty or null remove all
     * @param nameSpaceURI namespace to search for
     * @param mAttrib      attribute map to search for
     */
    public void removeChildren(String nodeName, String nameSpaceURI, JDFAttributeMap mAttrib)
    {
        // the loop allows jdf resources to recursively remove all children
        while (true)
        {
            final VElement v = getChildElementVector_JDFElement(nodeName, nameSpaceURI, mAttrib,  true,  0,false);
            if (v.size() > 0)
            {
                for (int i = 0; i < v.size(); i++)
                {
                    // may NOT call removeChild since JDFResource calls to this routine may actually change the parent
                    final KElement kelem = (KElement) v.elementAt(i);
                    kelem.deleteNode();
                }
            }
            else
            {
                return;
            }
        }
    }
    
    /**
     * is the ressource r linkable by this?
     * used by ResorceLink and refElement
     * 
     * @param r the resource to link to
     * @return boolean - true if r is at a valid position
     */
    protected boolean validResourcePosition(JDFResource r)
    {
        if(r==null)
        {
            return false;
        }
        final JDFNode nodeResource = r.getParentJDF();
        final JDFNode nodeLink = getParentJDF();
        if((nodeResource!=null) && (nodeLink!=null))
        {            
            if (nodeResource.equals(nodeLink))
            {
                return true;
            }
            if (nodeResource.isAncestor(nodeLink))
            {
                return true;
            }
        }
        else if(getDeepParent(ElementName.JMF,0)!=null)
        {
            // they are in the same signal, command etc..
            return getDeepParentChild(ElementName.JMF)==r.getDeepParentChild(ElementName.JMF);
        }
        
        return false;
    }    
    /**
     * remove child node
     * @param node         name of the child node to remove, if empty or "*" remove all
     * @param nameSpaceURI namespace to search for
     * @param n            number of nodes to skip before deleting
     * @return KElement - the removed node
     */
    public KElement removeChild(String node, String nameSpaceURI, int n)
    {
        // use KElement because I do not want to remove the target
        final VElement v = getChildElementVector_JDFElement(node, nameSpaceURI, null , true, 0,false);
        
        if (n >= v.size())
        {
            return null;
        }
        // may NOT call removeChild since JDFResource calls to this routine may actually change the parent
        final KElement kelem = (KElement) v.elementAt(n);        
        return kelem.deleteNode();
    }
       
    /**
     * getIDPrefix
     *
     * @return the default ID prefix of non-overwritten JDF elements
     */
    protected String getIDPrefix()
    {
        return "l";
    }
    
    /**
     * create and append a unique id, keep the existing one if it already exists
     *
     * @return String - the value of the ID attribute after setting
     * 
     * @default appendAnchor(null)
     */
    public String appendAnchor(String strName)
    {
        if (hasAttribute(AttributeName.ID))
        {
            return this.getAttribute(AttributeName.ID, null, null);
        }
        else if ((strName==null)||strName.equals(JDFConstants.EMPTYSTRING))
        {
            strName = getIDPrefix() + uniqueID(0);
        }
        setAttribute(AttributeName.ID, strName, null);
        
        return strName;
    }
    
    /**
     * create a node with the current local time in ISO format
     * @deprecated use setAttributeNameTimeStamp(AttributeName.TIMESTAMP, null);
     */
    public void setTimeStamp()
    {
        setAttributeNameTimeStamp(AttributeName.TIMESTAMP, null);
    }
    
    /**
     * put a timestamp in an attribute of the current node 
     *
     * @param attributeName the attribute name to timestamp
     * @param timestamp     the timestamp
     */
    public void setAttributeNameTimeStamp(String attributeName, JDFDate timestamp)
    {
        if (timestamp == null) timestamp = new JDFDate();
        setAttribute(attributeName, timestamp.getDateTimeISO(), null);
    }
    
    /**
     * create a string link from an integer
     *
     * @param id the integer value to convert to an ID
     * @deprecated 060307 internal legacy method
     * @return String - a unique ID string
     */
    public String idString(int id)
    {
        String s  = JDFConstants.LINK;
        String strID = Integer.toString(id);
        
        for (int i = JDFConstants.LINK.length() - strID.length(); i > 0; i--)
        {
            s += "0";
        }
        
        s += strID;
        return s;
    }
    
    /**
     * static version of GetParentJDF - get the local JDF node that this element resides in. <br>
     * if e is a JDF node, return it's parent<br>
     * if e is the root JDF node, return a null reference
     * @param e the KElement (may be in foreign namespace)
     *
     * @return JDFNode - the local parent JDF of this element
     */
    public static JDFNode getParentJDF(KElement e)
    {
        KElement e2=e;
        if(e2 instanceof JDFNode)
            e2=e2.getParentNode_KElement();
        if(e2==null)
            return null;
        e2= e2.getDeepParent(ElementName.JDF, 0);
        return e2==e ? null : (JDFNode) e2;
    }
    
    /**
     * get the local JDF node that this element resides in<br>
     * if this is a JDF node, return it's parent<br>
     * if this is the root JDF node, return a null reference
     *
     * @return JDFNode - the local parent JDF of this element
     */
    public JDFNode getParentJDF()
    {
        return getParentJDF(this);
    }
    
    /**
     * get invalid attributes
     * @param level          {@linkplain KElement.EnumValidationLevel validation level}
     * @param bIgnorePrivate if true, do not worry about attributes in other namespaces
     * @param nMax           maximum size of the returned vector
     * @return VString - a vector of invalid attribute names
     * 
     * @default getInvalidAttributes(level, true, 9999999)
     */
    public VString getInvalidAttributes(
            EnumValidationLevel level,
            boolean bIgnorePrivate,
            int nMax) 
    {
        AttributeInfo ai=getTheAttributeInfo();
        return getInvalidAttributes_JDFElement(level, bIgnorePrivate, nMax,ai);
    }
    
    /**
     * get invalid attributes
     * @param level          {@link KElement#EnumValidationLevel validation level}
     * @param bIgnorePrivate if true, do not worry about attributes in other namespaces
     * @param nMax           maximum size of the returned vector
     * @return VString - a vector of invalid attribute names
     *  
     * @default getInvalidAttributes_JDFElement(level, true, 9999999)
     */
    private VString getInvalidAttributes_JDFElement(
            EnumValidationLevel level,
            boolean bIgnorePrivate,
            int nMax,
            AttributeInfo ai)
    {
        final VString vAttsReturn = new VString();
        int numAtts  = 0;
        Vector vAtts= getAttributeVector_KElement();
        for(int i=0;i<vAtts.size();i++){
            String key=(String)vAtts.elementAt(i);
            if(!ai.validAttribute(key,getAttribute(key,null,null),level)){
                vAttsReturn.add(key);
                if (++numAtts >= nMax)
                {
                    return vAttsReturn;
                }
            }
        }
        if (requiredLevel(level))
        {    
            vAttsReturn.addAll(getMissingAttributes(nMax)); 
        } 
        
        if (vAttsReturn.size() >= nMax)
        {    
            return vAttsReturn; 
        } 
        
        vAttsReturn.addAll(getUnknownAttributes(bIgnorePrivate,nMax));
        return vAttsReturn;
    }
    
    /**
     * get invalid elements
     * 
     * @param level          validation level
     * @param bIgnorePrivate if true, do not worry about elements in other namespaces
     * @param nMax           maximum size of the returned vector
     * @return VString - a vector of invalid element names
     * 
     * @default GetInvalidElements(level, true, 999999)
     */
    public VString getInvalidElements(EnumValidationLevel level, boolean bIgnorePrivate,int nMax)
    {
        return getInvalidElements_JDFElement(level, bIgnorePrivate, nMax);
    }
    
    /**
     * Method GetInvalidElements_JDFElement.
     * @param level          validation level
     * @param bIgnorePrivate if true, do not worry about elements in other namespaces
     * @param nMax           maximum size of the returned vector
     * @return VString - a vector of invalid element names
     */
    public VString getInvalidElements_JDFElement(
            EnumValidationLevel level,
            boolean bIgnorePrivate,
            int nMax)
    {
        final VString vBad = new VString();
        int i = 0;
        int num = 0;
        final VElement v = getChildElementVector(null, null,null, true, 0, false);
        
        for (i = 0; i < v.size(); i++)
        {
            KElement e=(KElement)v.elementAt(i);
            if (!e.isValid(level))
            {
                vBad.add(e.getLocalName());
                if (++num >= nMax)
                {
                    return vBad;
                }
            }
        }
        
        if (requiredLevel(level))
        {
            vBad.appendUnique(new VString(getMissingElements(nMax)));
        }
        vBad.appendUnique(new VString(getUnknownElements(bIgnorePrivate, nMax)));
        
        return vBad;
    }
    
    /**
     * Set attribute CommentURL
     * @param value the CommentURL value
     */
    public void setCommentURL(String value)
    {
        setAttribute(AttributeName.COMMENTURL, value, null);
    }
    
    /**
     * Get string attribute CommentURL
     * @return the value of the attribute commentURL
     */
    public String getCommentURL()
    {
        return getAttribute(
                AttributeName.COMMENTURL, 
                null, 
                JDFConstants.EMPTYSTRING);
    }
    
    
    
    
    /**
     * Get the string of a refelement that points to this, i.e.NodeName+"Ref"
     * 
     * @return the nodename of a refelement
     */
    protected String getRefString()
    {
        return getNodeName() + JDFConstants.REF;
    }
    
    /**
     * get the LDFLib version
     *
     * @return the JDFLib version
     */
    public String jdfVersion()
    {
        return "1.3";
    }
    
    /**
     * returns the official JDF schema URI for a particular version
     * @return the URL that fits to majorVersion and minorVersion - null if not supported
     */
    public static String getSchemaURL() // fool proof schema url as of November 5th, 2003
    {
        return getSchemaURL(1, 1);  // "http://www.CIP4.org/JDFSchema_1_1"
    }
    
    /**
     * @param majorVersion the major version - only 1 is supported
     * @param minorVersion the minor version - only 0 or 1 are supported
     * @return the URL that fits to majorVersion and minorVersion - null if not supported
     */
    public static String getSchemaURL(int majorVersion, int minorVersion)
    {
        if (majorVersion == 1)
        {
            if (minorVersion == 0)
            {
                return "http://www.CIP4.org/JDFSchema_1";
            }
            else if (minorVersion >= 1)
            {
                return "http://www.CIP4.org/JDFSchema_1_1";
            }
        }
        // not supported
        return null;
    }
    
    /**
     * returns the JDFElement::EnumVersion, where new elements will be generated in by default
     * @return the default version
     */
    public static EnumVersion getDefaultJDFVersion()
    {
        return defaultVersion;
    }
    //////////////////////////////////////////////////////////////////////
    /**
     * sets the JDFElement::EnumVersion, where new elements will be generated in by default<br>
     * Attention this is static and global. Therefore it should generally be be called on initialization 
     * 
     * @param vers the new default version  
     */
    public static synchronized void setDefaultJDFVersion(EnumVersion vers)
    {
        defaultVersion=vers;
    }
    
    
    
 
    
    /**
     * get Element ID prefix
     *
     * @return the ID prefix of JDFElement
     */
    public String getElementIDPrefix()
    {
        return "l";
    }
    
    /**
     * gets a new ID for the element
     *
     * @param lastID the highest ID that has been created before
     *
     * @return the unique ID string
     */
    public String newID(String lastID)
    {
        final String idPrefix=getIDPrefix();
        if (lastID==null || lastID.equals(JDFConstants.EMPTYSTRING))
            return idPrefix+uniqueID(0);
        
        JDFElement p = (JDFElement) getParentNode_KElement();
        if (p == null)
            return idPrefix+uniqueID(0);
        
        return generateDotID(AttributeName.ID, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * generate a unique id in the syntax newID=oldID.nn <br>
     * nn is a unique number, that is generated as the first integer higher than 
     * the number of sibling elements with the same name. <br>
     * Note that it is the responsibilty of the caller not to provide multiple siblings 
     * that use the same base IDs.
     * 
     * @param key the attribute that is to be set to this ID, e.g. jobpartid
     * @param nameSpaceURI the attribute namespace that is to be set to this ID, e.g. jobpartid
     * @return String - the newly generated ID in the syntax parentID.nn
     */
    public String generateDotID(String key, String nameSpaceURI)
    {
        final String nodeName = getLocalName();
        JDFElement p = (JDFElement) getParentNode_KElement();
        final String  idPrefix=getIDPrefix();
        if (p == null)
            return idPrefix+uniqueID(0);
        String parentID = p.getAttribute(key, nameSpaceURI, null);
        if (parentID==null)
            return idPrefix+uniqueID(0);
        
        VElement vn = p.getChildElementVector(nodeName, nameSpaceURI, null, true, 0, false);
        int siz = vn.size();
        parentID += JDFConstants.DOT;
        
        for (int i = siz; i < 10000; i++)
        {
            String nn = parentID + i;
            boolean bFound = false;
            for (int j = 0; j < siz; j++)
            {
                if (nn.equals(((JDFElement) vn.elementAt(j)).getAttribute(key, nameSpaceURI,null)))
                {
                    bFound = true;
                    break;
                }
            }
            // got an unused id matching out x.y algorithm - return it
            if (!bFound)
            {
                return nn;
            }
        }
        // panic exit!
        return idPrefix+uniqueID(0);
    }
    
    
    private static int m_lStoreID = 0;
    private static boolean bIDDate=true;
    private static boolean bFixVersionIDFix=false;
    
    /**
     * UniqueID - create a unique id with 12 digits<br>
     *            6 digits are calculated via time,
     *            the other 6 digits are taken from id
     *
     * @param id the starting id of the ID
     *
     * @return the ID string value
     * 
     * @default uniqueID(0)
     */
    public synchronized static String uniqueID(int id)
    {
        
        if (id != 0)
        {
            m_lStoreID = id;
        }
        
        final String s           = "00000" + Integer.toString(m_lStoreID);
        m_lStoreID=++m_lStoreID %100000;
        // time + 6 digits (ID) 
        if(bIDDate)
        {
            final String date = dateFormatter.format(new Date());
            return date +"_" + s.substring(s.length()-6);
        }
        return s.substring(s.length()-6);
    }
    
    /**
     * set the ID generation algorithm to  include a date 
     * @param bLong if true (default), the date and time is used to generate long IDs
     */
    static public void setLongID(boolean bLong)
    {
        bIDDate=bLong;
    }
    
    /**
     * defines an enumerated list of attributes ; used by the automated code generator
     * 
     * @param key          the attribute name
     * @param v            comma separated string of allowed values
     * @param nameSpaceURI attribute namespace uri
     * @param def          the default enum if it does not exist
     * @param bInherit     if true: recurse into parent elements when searching the attribute as well
     * 
     * @since 300402
     * 
     * @return int - the enumeration equivalent of the attribute of the attribute def if 
     * @deprecated use EnumXYZ.getEnum(getAttribute(key, namespaceURI, def)
     * @default getEnumAttribute(key, v, null, -1, false)
     */
    public int getEnumAttribute(
            String key, 
            Vector v, 
            String nameSpaceURI, 
            int def, 
            boolean bInherit)
    {
        String s = null;
        int i = def;
        
        if (bInherit)
        {
            s = getInheritedAttribute(key, nameSpaceURI, JDFConstants.EMPTYSTRING);
        }
        else
        {
            s = getAttribute(key, nameSpaceURI, JDFConstants.EMPTYSTRING);
        }
        
        if (!s.equals(JDFConstants.EMPTYSTRING) && v.contains(s))
        {
            i = v.indexOf(s);
        }
        
        return i;
    }
    
    /**
     * defines an enumerated list of attributes ; used by the automated code generator
     * 
     * @param key          the attribute name
     * @param v            comma separated string of allowed values
     * @param nameSpaceURI attribute namespace uri
     * @param def          the default enum if it does not exist
     * @param bInherit     if true: recurse into parent elements when searching the attribute as well
     * 
     * @return the Vector of enumeration equivalents of the attribute
     * @since 300402
     * @deprecated use getEnumerationsAttribute(key, nameSpaceURI, EnumXYZ.getEnum(0), bInherit)
     * @default getEnumerationsAttribute(key, allowedValues, null, -1, false)
     */
    public Vector getEnumerationsAttribute(
            String key,
            Vector v,
            String nameSpaceURI,
            int def,
            boolean bInherit)
    {
        final VString vAllowed = new VString();
        vAllowed.addAll(v);
        
        String strAtt = JDFConstants.EMPTYSTRING;
        VString vs    = new VString();
        Vector vAtts  = null;
        
        if (bInherit)
        {
            strAtt = getInheritedAttribute(key, nameSpaceURI, JDFConstants.EMPTYSTRING);
        }
        else
        {
            strAtt = getAttribute(key, nameSpaceURI, JDFConstants.EMPTYSTRING); 
        }
        if (strAtt.equals(JDFConstants.EMPTYSTRING))
            vs.add(strAtt);
        vAtts  = StringUtil.tokenize(strAtt, JDFConstants.BLANK, false);
        vs.addAll(vAtts);
        
        final Vector ret = new Vector();
        if (vs.isEmpty())
        {
            if (def >= 0)
            {
                ret.addElement(new Integer(def));
            }
            return ret;
        }
        
        // 080502 RP was vAllowed,size - oops
        for (int i = 0; i < vs.size(); i++)
        {
            final int enumIndex = vAllowed.index(vs.stringAt(i));
            if (enumIndex >= 0)
            {
                ret.addElement(new Integer(enumIndex));
            }
            else
            {
                // illegal value
                ret.addElement(new Integer(-1));
            }
        }
        
        return ret;
    }
    
    /**
     * defines an enumerated list of attributes; used by the automated code generator
     * 
     * @param key the attribute local name
     * @param nameSpaceURI the namespace URI
     * @param enu a dummy enumeration of the correct type, typically EnumXYZ.getEnum(0)
     * @param bInherit if true, also recurse into parent elements when searching the attribute
     * @return Vector of ValuedEnum, null if no enum was set
     */
    public Vector getEnumerationsAttribute( String key,String nameSpaceURI,
            ValuedEnum enu, boolean bInherit)
    {
        String strAtt=null;
        Vector vEnum=new Vector();
        
        if (bInherit)
        {
            strAtt = getInheritedAttribute(key, nameSpaceURI, null);
        }
        else
        {
            strAtt = getAttribute(key, nameSpaceURI, null); 
        }
        if (strAtt==null)
            return null;
        
        Vector vAtts  = StringUtil.tokenize(strAtt, JDFConstants.BLANK, false);
        
        try {
            Class methodArgs[] = {String.class};
            Method m = enu.getClass().getMethod("getEnum",methodArgs);
            for(int i=0;i<vAtts.size();i++){
                Object args[]={(String)vAtts.elementAt(i)};                        
                ValuedEnum ve =(ValuedEnum) m.invoke(null,args);
                // there was an invalid token
                if( ve!=null)
                {
                    vEnum.add(ve);
                    
                }
            }
        } catch (SecurityException e1) {
            // in case of exceptions, simply apply NMTOKENS rule
        } catch (NoSuchMethodException e1) {
            // in case of exceptions, simply apply NMTOKENS rule
        } catch (IllegalArgumentException e) {
            // in case of exceptions, simply apply NMTOKENS rule
        } catch (IllegalAccessException e) {
            // in case of exceptions, simply apply NMTOKENS rule
        } catch (InvocationTargetException e) {
            // in case of exceptions, simply apply NMTOKENS rule
        }
        // all were ok
        return vEnum.size()==0 ? null : vEnum;
    }
    
    
    
    /**
     * set an enumerated list of attributes; used by the automated code generator
     * @param  key the attribute name
     * @param  value the enumeration vector
     * @param String nameSpaceURI attribute namespace uri
     * @throws JDFException wrong data type in vector
     */
    protected void setEnumerationsAttribute(String key, Vector value, String nameSpaceURI)
    {
        String s = JDFConstants.EMPTYSTRING;
        int n = 0;
        final int size = value==null ? 0 : value.size();
        for (int i = 0; i < size; i++)
        {
            final Object o = value.elementAt(i);
            if (!(o instanceof ValuedEnum))
            {
                throw new JDFException("setEnumerationsAttribute: wrong data type in vector");
            }
            
            if (n++ > 0)
            {
                s += JDFConstants.BLANK;
            }
            
            s += ((ValuedEnum)o).getName();
        }
        if(size>=0)
            setAttribute(key, s, nameSpaceURI);
        else
            removeAttribute(key, nameSpaceURI);
    }
    
    
    
    
    /**
     * is the attribute valid and of type iType? <br>
     * iType is of type EnumAttributeType, but may be expanded in child classes
     *
     * @param key          the attribute name
     * @param iType        the attribute type
     * @param bRequired    true if this attribute is required
     * @param nameSpaceURI attribute namespace uri
     * 
     * @return boolean: true if the attribute is valid
     *
     * @tbd implement URL validation
     * @deprecated clean up attributeInfo tables instead
     *  use KElement public boolean validAttribute(String key,String nameSpaceURI, EnumValidationLevel level)
     * 
     * @default ValidAttribute(key, iType, bRequired, null)
     */
    public boolean validAttribute(
            String key,
            AttributeInfo.EnumAttributeType iType,
            boolean bRequired,
            String nameSpaceURI)
    {
        
        if (!hasAttribute(key, null, false))
        {
            return !bRequired;
        }
        final String val = getAttribute(key, nameSpaceURI, JDFConstants.EMPTYSTRING);
        return AttributeInfo.validStringForType(val,iType,null);
    }
    
    /**
     * ValidEnumAttribute - is the attribute valid and does it fit allowedValues, iType is of type
     * EnumAttributeType but may be expanded in child classes
     *
     * @param key           the attribute name
     * @param v             Vector with all valid enums       
     * @param bRequired     true if this attribute is required
     * @param nameSpaceURI  attribute namespace uri
     *
     * @return booelan - true if the attribute is valid
     * @deprecated use getTheAttributeInfo instead
     * @default ValidEnumAttribute(key, v, bRequired, null)
     */
    public boolean validEnumAttribute(
            String key,
            Vector v,
            boolean bRequired,
            String nameSpaceURI)
    {
        if (!hasAttribute(key, nameSpaceURI, false))
        {
            return !bRequired;
        }
        return getEnumAttribute(key, v, nameSpaceURI, -1, false) >= 0;
    }
    
    /**
     * is the attribute valid and does it fit allowedValues. 
     * iType is of type EnumAttributeType but may be expanded in child classes
     * @param key the attribute name
     * @param vs comma separated string of allowed values
     * @param bRequired true if this attribute is required
     * @param nameSpaceURI attribute namespace uri
     * @return true, if the attribute is valid
     * @deprecated use getTheAttributeInfo instead
     */
    public boolean validEnumerationsAttribute(
            String key,
            Vector vs,
            boolean bRequired,
            String nameSpaceURI)
    {
        if (!hasAttribute(key, nameSpaceURI, false))
        {
            return !bRequired;
        }
        
        final Vector v = getEnumerationsAttribute(key, vs, nameSpaceURI, -1, false);
        
        for (int i = 0; i < v.size(); i++)
        {
            if (((Integer) v.elementAt(i)).intValue() == -1)
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * get the string value from an enumerated set of values
     *
     * @param value         - the enum that is to be translated to a string
     * @param allowedValues - comma separated string of allowed values
     * @deprecated use EnumXYZ.getEnum(int)
     * @return String - the string for the enum
     */
    public String getEnumString(int value, String allowedValues)
    {
        final Vector vs = new Vector();
        vs.addAll(StringUtil.tokenize(allowedValues, JDFConstants.COMMA, false));
        
        if (value >= vs.size())
        {
            return JDFConstants.EMPTYSTRING;
        }
        
        return (String) vs.elementAt(value);
    }
    
    /**
     * map a node's localname to an enumeration defined by allowedValues
     *
     * @param allowedValues comma separated string of allowed values
     *
     * @return int - the enum that corresponds to the nodename
     */
    public int getEnumNodeName(Vector allowedValues)
    {
        // 050629 RP made localname. TBD actually check whether the namespace is ok
        final String s = getLocalName();
        
        for (int i = 0; i < allowedValues.size(); i++)
        {
            if (/*!*/
                    s.equalsIgnoreCase((String) allowedValues.elementAt(i)))
            {
                return i;
            } //von Thomas nachgezogen
        }
        
        return 0; // unknown or illegal
    }
    
    /**
     * GetPartMapVector returns a vector of partmaps, null if no parts are present
     *
     * @return Vector
     */
    protected VJDFAttributeMap getPartMapVector()
    {
        final VElement vE = getChildElementVector(ElementName.PART,  null,  null,  true,   0, false);
        
        final int size = vE.size();
        final VJDFAttributeMap v = size==0 ? null : new VJDFAttributeMap();
        for (int i = 0; i < size; i++)
        {
            final JDFPart part = (JDFPart) vE.elementAt(i);
            v.add(part.getPartMap());
        }
        return v;
    }
    
    /**
     * gets the part map 
     *
     * @return JDFAttributeMap, of the part element
     */
     protected JDFAttributeMap getPartMap()
     {
         JDFPart p=(JDFPart)getElement(ElementName.PART,null,0);
         if(p==null)
             return null;
         return p.getPartMap();
     }
    
    /**
     * sets all parts to those defined in vParts
     *
     * @param vPart vector of attribute maps for the parts
     */
    protected void setPartMapVector(VJDFAttributeMap vPart)
    {
        removeChildren(ElementName.PART, null,null);
        if(vPart==null)
            return;
        
        for (int i = 0; i < vPart.size(); i++)
        {
            final KElement p = appendElement(ElementName.PART, null);
            p.setAttributes(vPart.elementAt(i));
        }
    }
    
    
    /**
     * sets part to mPart
     *
     * @param mPart attribute map for the part to set
     */
    protected void setPartMap(JDFAttributeMap mPart)
    {
        removeChildren(ElementName.PART, null,null);
        if((mPart!=null)&&!mPart.isEmpty())
        {   
            final KElement p = appendElement(ElementName.PART, null);
            p.setAttributes(mPart);
        }
    }
    
    
    /**
     * removes the part defined in mPart
     *
     * @param mPart attribute map for the part to remove
     */
    protected void removePartMap(JDFAttributeMap mPart)
    {
        final VElement vE = getChildElementVector(ElementName.PART, null,  null,  true, 0, false);
        
        final int size = vE.size();
        for (int i = 0; i < size; i++)
        {
            final KElement e_i = (KElement) (vE.elementAt(i));
            final JDFAttributeMap a_Map = e_i.getAttributeMap();
            
            if (a_Map.subMap(mPart))
            {
                vE.remove(i);
            }
        }
    }
    
    /**
     * checks whether the part defined in mPart is included in <i>this</i> 
     * 
     * @param mPart Attribute map to check
     * @return true if <i>this</i> has a part containing mPart
     */
    protected boolean hasPartMap(JDFAttributeMap mPart)
    {
        final VElement vE = getChildElementVector(ElementName.PART, null, null, true, 0, false);
        
        final int size = vE.size();
        for (int i = 0; i < size; i++)
        {
            final KElement e_i = (KElement) (vE.elementAt(i));
            final JDFAttributeMap a_Map = e_i.getAttributeMap();
            
            if (a_Map.subMap(mPart))
            {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * returns true if the enumeration level is either Complete
     * or RecursiveComplete, i.e. if the parameter is required
     * @param level the level to check 
     * @return true if required
     */
    public static boolean requiredLevel(EnumValidationLevel level)
    {
        return (level == EnumValidationLevel.Complete)
        || (level == EnumValidationLevel.RecursiveComplete);
    }
    
    /**
     * GetHRefs - get inter-resource linked resource IDs
     *
     * @param vDoneRefs
     * @param bRecurse if true, recurse followed refs
     *
     * @return VString
     * @deprecated use getHRefs(VString vDoneRefs, boolean bRecurse, boolean bExpand)
     * @default GetHRefs(null, false);
     */
    public VString getHRefs(VString vDoneRefs, boolean bRecurse)
    {
        return getHRefs(vDoneRefs,bRecurse,false);
    }
    
    /**
     * GetHRefs - get inter-resource linked resource IDs
     *
     * @param vDoneRefs (use null by default)
     * @param bRecurse if true recurse followed refs
     * @param bExpand if true expand partitioned resources
     *
     * @return VString - the vector of referenced resource IDs
     * 
     * @default GetHRefs(null, false);
     */
    public VString getHRefs(VString vDoneRefs, boolean bRecurse, boolean bExpand)
    {
        
        if(vDoneRefs==null)
            vDoneRefs=new VString();
        
         HashSet h=new HashSet();
        
        if(bExpand&&(this instanceof JDFResource))
        {
            VElement vLeaves=((JDFResource) this).getLeaves(true);
            final int siz=vLeaves.size();
            for(int i=0;i<siz;i++)
                vLeaves.item(i).fillHashSet(AttributeName.RREF,null,h);
        }
        else
        {
            fillHashSet(AttributeName.RREF,null,h);
        }
        final int iFirstPos = vDoneRefs.size(); // get the previous size
        VString v2 = new VString();
        v2.addAll(h);
        
        vDoneRefs.appendUnique(v2); // get the new size
        if(bRecurse)
        {
            final int iLastPos  = vDoneRefs.size();
            
            // recurse only the new rrefs
            for (int i = iFirstPos; i < iLastPos; i++)
            {
                final String s     = (String) vDoneRefs.elementAt(i);
                final JDFElement e = (JDFElement) getTarget(s, AttributeName.ID);
                if (e != null)
                {
                    vDoneRefs = e.getHRefs(vDoneRefs, true, bExpand);
                }
            }
        }
        
        return vDoneRefs;
    }
    
    /**
     * get inter-resource linked resource vector
     * @param bRecurse
     * @deprecated use getvHRefRes(true,false);
     * @return VElement
     */
    public VElement getvHRefRes(boolean bRecurse)
    {
        return getvHRefRes(bRecurse,false);
    }
    
    /**
     * get inter-resource linked resource vector
     * @param bRecurse if true, recurse followed links
     * @param bExpand if true, expand partitioned resources 
     *        and follow the refs from the leaves
     *
     * @return VElement - the inter-resource linked resource vector
     */
    public VElement getvHRefRes(boolean bRecurse, boolean bExpand)
    {
        final VString sRefs = getHRefs(null, bRecurse, bExpand);
        final VElement v   = new VElement();
        
        for (int i = 0; i < sRefs.size(); i++)
        {
            KElement kEl = getTarget((String) sRefs.elementAt(i), AttributeName.ID);
            if (kEl != null)
                v.appendUnique(kEl);
        }
        
        return v;
    }
    
    /**
     * Append to attribute rRefs if it is not yet in the list
     * @param value the rRef token to append
     * @deprecated rRefs was deprecated in JDF 1.2
     */
    public void appendrRefs(String value)
    {
        appendAttribute(AttributeName.RREFS, value, null, JDFConstants.BLANK, true);
    }
    
    /**
     * Remove value from attribute rRefs if it is  in the list
     * @param value the rRef token to remove from the NMTOKENS list
     * @deprecated rRefs was deprecated in JDF 1.2
     */
    public int removeFromrRefs(String value)
    {
        return removeFromAttribute(
                AttributeName.RREFS, 
                value, 
                null, 
                JDFConstants.BLANK, 
                -1);
    }
    /**
     * Set attribute rRefs, i.e. combine the blank separed attribute list
     * @deprecated rRefs was deprecated in JDF 1.2
     */
    public void setrRefs(VString vStr)
    {
        setAttribute(
                AttributeName.RREFS, 
                StringUtil.setvString(vStr,JDFConstants.BLANK,null,null),null);
    }
    /**
     * Get string attribute rRefs, i.e. split the blank separed attribute list
     * @deprecated rRefs was deprecated in JDF 1.2
     */
    public VString getrRefs()
    {
        final VString vStr = new VString();
        vStr.setAllStrings(
                getAttribute_KElement(
                        AttributeName.RREFS, 
                        null, 
                        JDFConstants.EMPTYSTRING), 
                        JDFConstants.BLANK);
        
        return vStr;
    }
    
    /**
     * Gets the root resource of the target
     * returns a null JDFElement if it does not exist or the name mangling is illegal
     * 
     * @param  id the id of the linked root. If null, the id of <i>this</i> is used.
     *
     * @return JDFResource the resource root of the resource referenced by this resource link
     */
    public JDFResource getLinkRoot(String id)
    {
        JDFResource ret = null;
        
        final String myid = (id == null) ? getAttribute(AttributeName.RREF, null, null) : id;
        if (myid == null) // no ID, no resource!
            return null;
        
        boolean bSearching = true;
        XMLDocUserData ud = getXMLDocUserData();
        if (ud != null) // grab target from the cache
        {
            final KElement kOwner = ud.getTarget(myid);
            if (kOwner != null)
            {
                if (kOwner instanceof JDFResource)
                {
                    ret = (JDFResource) kOwner;
                    if (!ret.isResourceRootRoot())
                    {
                        ret = null;
                    }
                }
            }
            
            if (ret != null) // we found something in the cache
            {
                bSearching = false;
                if (!validResourcePosition(ret))
                {
                    bSearching = true;
                    ret = null;
                }
            }
        }
        
        if (ret == null)
        {
            
            // start with the first jdf node parent of this so that nod is initialized
            JDFNode nod = (JDFNode) getDeepParent(ElementName.JDF, 0);
            if (nod != null) // we are in a JDF, not in a JMF
            {
                while (bSearching)
                {
                    if (nod != null)
                    {
                        final JDFResourcePool rp = nod.getResourcePool();
                        if (rp != null)
                        {
                            ret = rp.getResourceByID(myid);
                            bSearching = ret == null;
                        }
                        
                        if (bSearching)
                        {
                            nod = nod.getParentJDF();
                            bSearching = nod != null;
                        }
                    }
                }
            }
            else
                // we may be in a JMF and have nothing in the cache
            {
                if (bSearching)
                {
                    KElement jmf = getDeepParent(ElementName.JMF, 0);
                    if (jmf != null)
                    {
                        ret = (JDFResource) jmf.getChildWithAttribute(
                                null, AttributeName.ID, null, myid, 0, false);
                    }
                }
            }
        }
        return ret;
    }
    
    /**
     * returns the official JDF version string
     *
     * @return String: the inherited version information or "1.3" if no valid 
     *                 version info was found
     * @deprecated use getDefaultDFVersion()
     */
    public final String version()
    {
        String ver = getInheritedAttribute(AttributeName.VERSION, null, JDFConstants.EMPTYSTRING);
        if(JDFConstants.EMPTYSTRING.equals(ver))
        {
            return JDFConstants.VERSION_1_3;
        }
        return JDFElement.EnumVersion.getEnum(ver).getName();
    }
    
    /**
     * set Version to enumVer
     * @param enumVer the EnumVersion to set 
     */
    public void setVersion(EnumVersion enumVer)
    {
        setAttribute(AttributeName.VERSION, enumVer.getName(), null);
    }
    
    /**
     * get EnumVersion attribute Version
     * @return EnumVersion - attribute value 
     * @deprecated 060505 use getVersion(boolean);
     */
    public EnumVersion getVersion()
    {
        return getVersion(true);
    }

    /**
     * get the version of this element
     * @param bInherit if true, check ancestor nodes
     * @return the version corresponding to this element
     */
    public EnumVersion getVersion(boolean bInherit)
    {
        String version;
        if (bInherit)
        {
            version = getInheritedAttribute(AttributeName.VERSION,  null, null);
        }
        else
        {
            version = getAttribute(AttributeName.VERSION,null,null);
        }
        
        return EnumVersion.getEnum(version);
    }
    
    /**
     * Enumeration strings for Version
	 * 
     * NOTE: If not specified the version defaults to Version 1.3 
     */
    public static final class EnumVersion extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;
        
        /**
         * @see java.lang.Object#toString()
         * @deprecated just for compiling PrintReady, to be removed afterwards
         */
        public String toString()
        {
            return getName();
        }
        
        private EnumVersion(String name)
        {
            super(name, m_startValue++);
        }
        
        /**
         * casts a String into a corresponding EnumVersion
         * @param enumName the name of the EnumVersion
         * @return the corresponding EnumVersion
         */
        public static EnumVersion getEnum(String enumName)
        {
            EnumVersion myVersion = (EnumVersion) getEnum(EnumVersion.class, enumName);
            if (myVersion == null)
            {
                myVersion = EnumVersion.Version_1_3;
            }
            return myVersion;
        }
        
        /**
         * casts a EnumVersion into its corresponding String
         * @param enumValue the EnumVersion to cast
         * @return the corresponding String
         */
        public static EnumVersion getEnum(int enumValue)
        {
            return (EnumVersion) getEnum(EnumVersion.class, enumValue);
        }
        
        /**
         * @return Map
         */
        public static Map getEnumMap()
        {
            return getEnumMap(EnumVersion.class);
        }
        
        /**
         * @return List
         */
        public static List getEnumList()
        {
            return getEnumList(EnumVersion.class);
        }
        
        /**
         * gets the EnumVersion iterator
         * @return Iterator
         */
        public static Iterator iterator()
        {
            return iterator(EnumVersion.class);
        }
        
        
        /**
         * @deprecated EnumVersion.Unknown
         */
        public static final EnumVersion Unknown = new EnumVersion(JDFConstants.UNKNOWN);
        
        public static final EnumVersion Version_1_0 = new EnumVersion(JDFConstants.VERSION_1_0);
        public static final EnumVersion Version_1_1 = new EnumVersion(JDFConstants.VERSION_1_1);
        public static final EnumVersion Version_1_2 = new EnumVersion(JDFConstants.VERSION_1_2);
        public static final EnumVersion Version_1_3 = new EnumVersion(JDFConstants.VERSION_1_3);
        public static final EnumVersion Version_1_4 = new EnumVersion(JDFConstants.VERSION_1_4);
        public static final EnumVersion Version_1_5 = new EnumVersion(JDFConstants.VERSION_1_5);
        public static final EnumVersion Version_1_6 = new EnumVersion(JDFConstants.VERSION_1_6);
        public static final EnumVersion Version_1_7 = new EnumVersion(JDFConstants.VERSION_1_7);
        public static final EnumVersion Version_1_8 = new EnumVersion(JDFConstants.VERSION_1_8);
        public static final EnumVersion Version_1_9 = new EnumVersion(JDFConstants.VERSION_1_9);
        
    }
    
    /**
     * @param enumName
     * @return EnumVersion
     * @deprecated use EnumVersion.getEnum
     */
    public static EnumVersion stringToVersion(String enumName)
    {
        return EnumVersion.getEnum(enumName);
    }
    
    
    
    /**
     * Method getChildElements.
     * @return JDFElement[]
     * @deprecated use {@link KElement#getChildElementArray KElement.getChildElementArray()}<br>
     * not typesafe in case of elements in foreign namespaces
     */
    public JDFElement[] getChildElements()
    {
        final JDFElement[] children = new JDFElement[getChildElementCount()];
        
        final NodeList allNodes = getChildNodes();
        
        final int cnt = allNodes.getLength();
        int runner = 0;
        
        for (int i = 0; i < cnt; i++)
        {
            final Node node = allNodes.item(i);
            
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                children[runner++] = (JDFElement) node;
            }
        }
        
        return children;
    }    
    
    /**
     * Method getChildrenCount.
     * @return int
     * @deprecated 060727 use numChildNodes(Node.ELEMENT_NODE);
     */
    public int getChildElementCount()
    {
       return numChildNodes(Node.ELEMENT_NODE);
    }
    
    /**
     * Method getChildElement
     * @param n Element index n (1 based)
     * @return JDFElement
     * 
     * @deprecated use getElement(null, null ,n)
     */
    public JDFElement getChildElement(int n)
    {
        JDFElement eReturn = null;
        
        final NodeList children = getChildNodes();
        
        final int cnt = children.getLength();
        
        for (int i = 0; i < cnt && eReturn == null; i++)
        {
            final Node node = children.item(i);
            
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                n--;
                
                if (n == 0)
                {
                    eReturn = (JDFElement) node;
                }
            }
        }
        
        return eReturn;
    } 
    
    /**
     * get unknown elements for a specific pool
     * 
     * @param poolType the PoolType to look for
     * @param nMax     max. number of elements to retrieve
     * @return         VString of unknown pool elements
     * @default getUnknownPoolElements(EnumPoolType poolType, 99999, null)
     */
    public VString getUnknownPoolElements(EnumPoolType poolType, int nMax)
    {
        final VElement v      = getChildElementVector(null, null, null, true, 0, false);
        final VString vElem   = new VString();
        int n = 0;
        final int siz = v.size();
        final VString knownElements = knownElements();
        VString badVersions=getTheElementInfo().prereleaseElements();
        badVersions.appendUnique(getTheElementInfo().deprecatedElements());
        
        for (int i = 0; i < siz; i++)
        {
            final KElement e = (KElement) v.elementAt(i);
            
            // check for known prerelease or deprecated elements
            if(badVersions.contains(e.getLocalName()))
            {
                vElem.add(e.getLocalName());           
                if (++n > nMax)
                {
                    return vElem;
                }
                continue;
            }
            
            if (e instanceof JDFComment)
            {
                continue;
            }
            
            if (((EnumPoolType.ResourcePool.equals(poolType)) 
                    || (EnumPoolType.ProductionIntent.equals(poolType))
                    || (EnumPoolType.PipeParams.equals(poolType)))
                    && e instanceof JDFResource)
            {
                continue;
            } 
            
            if (((EnumPoolType.ProductionIntent.equals(poolType))
                    || (EnumPoolType.RefElement.equals(poolType))
                    || (EnumPoolType.PipeParams.equals(poolType)))
                    && e instanceof JDFRefElement)
            {
                continue;
            } 
            
            if (((EnumPoolType.ResourceLinkPool.equals(poolType)) 
                    || (EnumPoolType.PipeParams.equals(poolType)))
                    && e instanceof JDFResourceLink)
            {
                continue; 
            } 
            
            if (EnumPoolType.AuditPool.equals(poolType) && e instanceof JDFAudit)
            {
                continue; 
            } 
            
            if (EnumPoolType.AncestorPool.equals(poolType) && e instanceof JDFAncestor)
            {
                continue;
            }
            
            if (knownElements.contains(e.getLocalName()))
            {
                continue; 
            } 
            if(e instanceof JDFRefElement)
            {
                String refName=((JDFRefElement)e).getRefLocalName();
                if (knownElements.contains(refName))
                {
                    continue; 
                } 
            }
            
            vElem.add(e.getLocalName());           
            if (++n > nMax)
            {
                return vElem;
            }
        }
        
        return vElem;
    }
    
    /**
     * Set attribute SettingsPolicy
     * @param value the SettingsPolicy to set
     */
    public void setSettingsPolicy(EnumSettingsPolicy value)
    {
        setAttribute(AttributeName.SETTINGSPOLICY,value.getName(),null);
    }
    
    /**
     * Typesafe enumerated attribute SettingsPolicy
     * @param bInherit recurse through ancestors when searching
     */
    public EnumSettingsPolicy getSettingsPolicy(boolean bInherit)
    {	
        String s=null;
        if(bInherit){
            s=getInheritedAttribute(AttributeName.SETTINGSPOLICY, null, null);	        
        }
        else
        {
            s=getAttribute(AttributeName.SETTINGSPOLICY, null, null);
        }
        return EnumSettingsPolicy.getEnum(s);
    }
    
    /**
     * Sets attribute BestEffortExceptions with the vector of values
     * @param value vector of BestEffortExceptions tokens
     */
    public void setBestEffortExceptions (VString value){
        setAttribute(AttributeName.BESTEFFORTEXCEPTIONS,value,null);
    }
    
    /**
     * Append a token to attribute BestEffortExceptions
     * @param value the new BestEffortExceptions token
     */
    public void appendBestEffortExceptions(String value)
    {
        appendAttribute(
                AttributeName.BESTEFFORTEXCEPTIONS, 
                value, 
                null, 
                JDFConstants.BLANK, 
                true);
    }
    
    /**
     * Remove a token from attribute BestEffortExceptions
     * @param value the BestEffortExceptions token to remove
     */
    public void removeFromBestEffortExceptions(String value)
    {
        removeFromAttribute(AttributeName.BESTEFFORTEXCEPTIONS, 
                value,  null, JDFConstants.BLANK, -1);
    }
    
    /**
     * Gets the value of attribute BestEffortExceptions
     * @return the attribute value
     */
    public String getBestEffortExceptions() {
        return getAttribute(AttributeName.BESTEFFORTEXCEPTIONS, null, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * Sets attribute MustHonorExceptions with the vector of values
     * @param value vector of MustHonorExceptions tokens
     */
    public void setMustHonorExceptions (VString value){
        setAttribute( AttributeName.MUSTHONOREXCEPTIONS,value,null);
    }
    
    /**
     * Append a token to attribute MustHonorExceptions
     * @param value the new of MustHonorExceptions token
     */
    public void appendMustHonorExceptions(String value)
    {
        appendAttribute( AttributeName.MUSTHONOREXCEPTIONS, 
                value,  null,  JDFConstants.BLANK,  true);
    }
    
    /**
     * Remove a token from attribute MustHonorExceptions
     * @param value the new of MustHonorExceptions token
     */
    public void removeFromMustHonorExceptions(String value)
    {
        removeFromAttribute(AttributeName.MUSTHONOREXCEPTIONS, 
                value,  null, JDFConstants.BLANK, -1);
    }
    
    /**
     * gets the value of attribute MustHonorExceptions
     * @return the attribute value
     */
    public String getMustHonorExceptions() {
        return getAttribute(AttributeName.MUSTHONOREXCEPTIONS, null, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * Sets attribute OperatorInterventionExceptions with the vector of values
     * @param value the vector of OperatorInterventionExceptions tokens
     */
    public void setOperatorInterventionExceptions (VString value){
        setAttribute( AttributeName.OPERATORINTERVENTIONEXCEPTIONS,value, null);
    }
    
    /**
     * Append a token to attribute OperatorInterventionExceptions
     * @param value the new of OperatorInterventionExceptions token
     */
    public void appendOperatorInterventionExceptions(String value)
    {
        appendAttribute(AttributeName.OPERATORINTERVENTIONEXCEPTIONS, 
                value,  null, JDFConstants.BLANK, true);
    }
    
    /**
     * Remove a token from attribute OperatorInterventionExceptions
     * @param value the new of OperatorInterventionExceptions token
     */
    public void removeFromOperatorInterventionExceptions(String value)
    {
        removeFromAttribute(AttributeName.OPERATORINTERVENTIONEXCEPTIONS, 
                value,  null, JDFConstants.BLANK,-1);
    }
    
    /**
     * gets the value of attribute OperatorInterventionExceptions
     * @return the attribute value
     */
    public String getOperatorInterventionExceptions() {
        return getAttribute(AttributeName.OPERATORINTERVENTIONEXCEPTIONS, null, JDFConstants.EMPTYSTRING);
    }
    
    /**
     * Set attribute DescriptiveName
     * @param value value to set the attribute to
     */
    public void setDescriptiveName(String value)
    {
        setAttribute(AttributeName.DESCRIPTIVENAME, value, null);
    }
    
    /**
     * Get string attribute DescriptiveName
     * @return the attribute value
     */    
    public String getDescriptiveName()
    {
        return getAttribute(AttributeName.DESCRIPTIVENAME, null, JDFConstants.EMPTYSTRING);
    }
    
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    
    /* ******************************************************
     // Element Getter / Setter
      **************************************************************** */
    
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    
    /**
     * Appends element Comment to the end of 'this'
     * @return the newly created JDFComment
     */
    public JDFComment appendComment()
    {
        return (JDFComment) appendElement(ElementName.COMMENT, null);
    }
    
    /**
     * Gets the iSkip-th element Comment. If doesn't exist, creates it
     * @param iSkip number of elements to skip
     * @return the newly created JDFComment
     */
    public JDFComment getCreateComment(int iSkip)
    {
        return (JDFComment) getCreateElement_KElement(ElementName.COMMENT,null,iSkip);
    }
    
    /**
     * Gets the iSkip-th element Comment
     * @param iSkip number of elements to skip
     * @return JDFComment - the matching element
     * 
     * @default getComment(0)
     */
    public JDFComment getComment(int iSkip)
    {
        return (JDFComment) getElement(ElementName.COMMENT, null, iSkip);
    }
     
    //////////////////////////////////////////////////////////////////////
    
    /**
     * Gets a child with matching attributes, also follows refelements
     *
     * @param nodeName      name of the child node to search for
     * @param attName       attribute name to search for
     * @param nameSpaceURI  namespace to search for
     * @param attVal        a special value to search for
     * @param index         if more then one child meets the condition, you can specify the one to return via an index
     * @param bDirect       if true, look only in direct children, else search through all children, grandchildren etc.
     * @param dataType      dataType to be matched 
     * 
     * @return JDFElement: the element which matches the above conditions
     * 
     * @default getChildWithMatchingAttribute(nodeName, attName, null, null, 0, true, EnumAttributeType.Any);
     */
    public JDFElement getChildWithMatchingAttribute(String nodeName, String attName, String nameSpaceURI, 
            String attVal, int index, boolean bDirect, AttributeInfo.EnumAttributeType dataType)
    {
        VElement v = getChildrenByTagName(nodeName, nameSpaceURI, null, bDirect, true, 0);
        final int siz = v.size();
        int n = 0;
        for (int i = 0; i < siz; i++)
        {
            JDFElement e = (JDFElement) v.elementAt(i);
            if (e.includesMatchingAttribute(attName, attVal, dataType))
            {
                if (n++ == index)
                    return e;
            }
        }
        return null;
    }
    
    /**
     * test whether the attributes described by attName and attVal exist<br<
     * also checking ranges and NMTOKENS etc.
     * @param attName  the name of the attribute to look for
     * @param attVal   the value of the attribute to look for; 
     *                       a values of "*" is treated as true if the attribute exists, 
     *                       regardless of its value
     * @param dataType the dataType to be matched
     * @tbd allow for regular expressions in attVal
     * @return true, if such attribute exists
     * 
     * @default includesMatchingAttribute(String attName, null, JDFElement.EnumAttributeType.Any)
     */
    public boolean includesMatchingAttribute(String attName, String attVal, AttributeInfo.EnumAttributeType dataType)
    {
        if (includesAttribute(attName,attVal))
        {
            return true;
        }
         
        final String thisVal = getAttribute(attName,null,null);
        if(thisVal==null)
            return false;
        
        return StringUtil.matchesAttribute(attVal, thisVal, dataType);
    }

 
    
    
    /**
     *Sets attribute Status
     *
     * @param s the status to set the attribute to 
     */
    public void setStatus(EnumNodeStatus s)
    {
        setAttribute(AttributeName.STATUS, s.getName(), null);
    }
    
    /**
     * gets the value of attribute Status
     *
     * @return EnumNodeStatus
     */
    public EnumNodeStatus getStatus()
    {
        return EnumNodeStatus.getEnum(getAttribute(AttributeName.STATUS, null, null));
    }
    
    /**
     * Gets inter-resource linked resource IDs
     *
     * @param vDoneRefs (use null as default value)                                    
     * @param bRecurse if true, return recursively linked IDs as well
     * @return the HashSet of all refElements
     * 
     * @default getAllRefs(null, false) 
     */
    public HashSet getAllRefs(HashSet vDoneRefs, boolean bRecurse)
    {     
        if(vDoneRefs.contains(this))
            return vDoneRefs;
        
        VElement v  = getChildElementVector_KElement(null, null, null, true, 0); // grabemall
        
        final int size = v.size();
        for(int i = 0; i < size; i++)
        {
            KElement e = (KElement)v.elementAt(i);
            if(e instanceof JDFRefElement)
            {
                final JDFRefElement ref = (JDFRefElement)e;
                if(!vDoneRefs.contains(ref))
                {
                    vDoneRefs.add(ref);
                    if(bRecurse)
                    {
                        JDFResource r = ref.getTarget();
                        if(r!=null)
                            vDoneRefs=r.getAllRefs(vDoneRefs, bRecurse);
                    }
                }
            }
            else if (e instanceof JDFElement)
            { // recurse tree
                vDoneRefs=((JDFElement)e).getAllRefs(vDoneRefs,bRecurse);
            }
        }
        return vDoneRefs;
    }  
    
    /**
     * gets attribute ID
     * @return the attribute value
     * @tbd use getID of the subclasses that may have id
     */
    public String getID()
    {
        return getAttribute(AttributeName.ID,null,JDFConstants.EMPTYSTRING);
    }
    

    /* (non-Javadoc)
     * @see org.cip4.jdflib.core.KElement#matchesPath(java.lang.String, boolean)
     */
    public boolean matchesPath(String path, boolean bFollowRefs)
    {
        if(path==null)
            return true;
        VString v =StringUtil.tokenize(path,"/",false);
        KElement e=this;
        KElement eLast=null;
        for(int i=v.size()-1;i>=0;i--)
        { 
            if(e==null)
                return false;
            final String locName = e.getLocalName();
            if(!locName.equals(v.stringAt(i)))
            {
                if(bFollowRefs && eLast!=null && locName.equals(ElementName.RESOURCEPOOL))
                { // now look for a refelement that points at this
                    if (eLast instanceof JDFResource)
                    {
                        JDFResource r=(JDFResource)eLast;
                        VElement vRefs=r.getLinks(r.getRefString(),null);
                        if(vRefs!=null)
                        {
                            String subPath=v.stringAt(0);
                            for(int k=1;k<=i+1;k++)
                                subPath+="/"+v.stringAt(k);
                            subPath+="Ref";
                            for(int j=0;j<vRefs.size();j++)
                            {
                                KElement eRef=vRefs.item(j);
                                boolean b=eRef.matchesPath(subPath, bFollowRefs);
                                if(b)
                                    return true;
                            }
                        }
                        
                    }
                }
                if(eLast!=null)
                {
                    if(eLast instanceof JDFResource)
                    {
                        if(locName.equals(eLast.getLocalName())){
                            e=e.getParentNode_KElement();
                            i++; // undo i--
                            continue;
                        }
                    }
                }
                return false;                
            }
            eLast=e;
            e=e.getParentNode_KElement();
        }  
        if(path.startsWith("/"))
            return e==null || path.startsWith("//"); // must be root
        return true; // any location
    }

    /**
     * @return the bFixVersionIDFix 
     * if true then invalid, i.e. numeric ID, IDREF and IDREFS are prefixed with an '_'
     * when calling fixVersion
     */
    public static boolean isFixVersionIDFix()
    {
        return bFixVersionIDFix;
    }

    /**
     * @param fixVersionIDFix the bFixVersionIDFix to set
     * if true then invalid, i.e. numeric ID, IDREF and IDREFS are prefixed with an '_'
     * when calling fixVersion
     */
    public static void setFixVersionIDFix(boolean fixVersionIDFix)
    {
        bFixVersionIDFix = fixVersionIDFix;
    }
   
    
}
