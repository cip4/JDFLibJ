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

package org.cip4.jdflib.resource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoPart.EnumPreviewType;
import org.cip4.jdflib.auto.JDFAutoPart.EnumSide;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCustomerInfo;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.JDFNodeInfo;
import org.cip4.jdflib.core.JDFRefElement;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.JDFIntegerRangeList;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFNode.EnumType;
import org.cip4.jdflib.pool.JDFPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.process.JDFContact;
import org.cip4.jdflib.resource.process.JDFGeneralID;
import org.cip4.jdflib.resource.process.JDFIdentical;
import org.cip4.jdflib.resource.process.JDFIdentificationField;
import org.cip4.jdflib.resource.process.JDFQualityControlResult;
import org.cip4.jdflib.resource.process.JDFSourceResource;
import org.cip4.jdflib.util.JDFMerge;
import org.cip4.jdflib.util.StringUtil;
import org.w3c.dom.Node;


public class JDFResource extends JDFElement
{
    private static final long serialVersionUID = 1L;
    private static boolean autoAgent=false;

    private static AtrInfoTable[] atrInfoTable_Abstract = new AtrInfoTable[17];
    static
    {
        atrInfoTable_Abstract[0]  = new AtrInfoTable(AttributeName.AGENTNAME,      0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Abstract[1]  = new AtrInfoTable(AttributeName.AGENTVERSION,   0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Abstract[2]  = new AtrInfoTable(AttributeName.AUTHOR,         0x33333311, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Abstract[3]  = new AtrInfoTable(AttributeName.CATALOGID,      0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Abstract[4]  = new AtrInfoTable(AttributeName.CATALOGDETAILS, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Abstract[5]  = new AtrInfoTable(AttributeName.LOCKED,         0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, "false");
        atrInfoTable_Abstract[6]  = new AtrInfoTable(AttributeName.PIPEID,         0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Abstract[7]  = new AtrInfoTable(AttributeName.PIPEPROTOCOL,   0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_Abstract[8]  = new AtrInfoTable(AttributeName.PIPEURL,        0x33333311, AttributeInfo.EnumAttributeType.URL, null, null);
        atrInfoTable_Abstract[9]  = new AtrInfoTable(AttributeName.PRODUCTID,      0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Abstract[10] = new AtrInfoTable(AttributeName.RREFS,          0x44444433, AttributeInfo.EnumAttributeType.IDREFS, null, null);
        atrInfoTable_Abstract[11] = new AtrInfoTable(AttributeName.SPAWNSTATUS,    0x33333333, AttributeInfo.EnumAttributeType.enumeration,EnumSpawnStatus.getEnum(0),EnumSpawnStatus.NotSpawned.getName());
        atrInfoTable_Abstract[12] = new AtrInfoTable(AttributeName.SPAWNIDS,       0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable_Abstract[13] = new AtrInfoTable(AttributeName.SORTING,        0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_Abstract[14] = new AtrInfoTable(AttributeName.SORTAMOUNT,     0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable_Abstract[15] = new AtrInfoTable(AttributeName.PARTIDKEYS,     0x33333333, AttributeInfo.EnumAttributeType.enumerations,EnumPartIDKey.getEnum(0),null);
        atrInfoTable_Abstract[16] = new AtrInfoTable(AttributeName.PIPEPARTIDKEYS, 0x33333311, AttributeInfo.EnumAttributeType.enumerations,EnumPartIDKey.getEnum(0),null);
    }

    private static AtrInfoTable[] atrInfoTable_Physical = new AtrInfoTable[11];
    static
    {
        atrInfoTable_Physical[0] =  new AtrInfoTable(AttributeName.ALTERNATEBRAND, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Physical[1] =  new AtrInfoTable(AttributeName.AMOUNT,         0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[2] =  new AtrInfoTable(AttributeName.AMOUNTPRODUCED, 0x33333311, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[3] =  new AtrInfoTable(AttributeName.AMOUNTREQUIRED, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[4] =  new AtrInfoTable(AttributeName.BATCHID,        0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Physical[5] =  new AtrInfoTable(AttributeName.BRAND,          0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Physical[6] =  new AtrInfoTable(AttributeName.UNIT,           0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_Physical[7] =  new AtrInfoTable(AttributeName.RESOURCEWEIGHT, 0x33333331, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[8] =  new AtrInfoTable(AttributeName.GROSSWEIGHT,    0x33333111, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable_Physical[9] =  new AtrInfoTable(AttributeName.MANUFACTURER,   0x33333111, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_Physical[10]=  new AtrInfoTable(AttributeName.LOTCONTROL,     0x33333111, AttributeInfo.EnumAttributeType.enumeration,EnumLotControl.getEnum(0),null);
    }

    private static AtrInfoTable[] atrInfoTable_Param = new AtrInfoTable[1];
    static
    {
        atrInfoTable_Param[0] =  new AtrInfoTable(AttributeName.NOOP,           0x33333331, AttributeInfo.EnumAttributeType.boolean_, null, "false");
    }

    private static AtrInfoTable[] atrInfoTable_ID_Class_Required = new AtrInfoTable[3];
    static
    {
        atrInfoTable_ID_Class_Required[0] =  new AtrInfoTable(AttributeName.ID,             0x22222222, AttributeInfo.EnumAttributeType.ID, null, null);
        atrInfoTable_ID_Class_Required[1] =  new AtrInfoTable(AttributeName.CLASS,          0x22222222, AttributeInfo.EnumAttributeType.enumeration,EnumResourceClass.getEnum(0), null);
        atrInfoTable_ID_Class_Required[2] =  new AtrInfoTable(AttributeName.PARTUSAGE,      0x33333331, AttributeInfo.EnumAttributeType.enumeration,EnumPartUsage.getEnum(0), EnumPartUsage.Explicit.getName());

    }

    private static AtrInfoTable[] atrInfoTable_ID_Class_Optional = new AtrInfoTable[2];
    static
    {
        atrInfoTable_ID_Class_Optional[0] =  new AtrInfoTable(AttributeName.ID,             0x44444433, AttributeInfo.EnumAttributeType.ID, null, null);
        atrInfoTable_ID_Class_Optional[1] =  new AtrInfoTable(AttributeName.CLASS,          0x33333333, AttributeInfo.EnumAttributeType.ID, null, null);
    }
    private static AtrInfoTable[] atrInfoTable_ID_Class_Root = new AtrInfoTable[3];
    static
    {
        atrInfoTable_ID_Class_Root[0] =  new AtrInfoTable(AttributeName.ID,             0x33333333, AttributeInfo.EnumAttributeType.ID, null, null);
        atrInfoTable_ID_Class_Root[1] =  new AtrInfoTable(AttributeName.CLASS,          0x33333333, AttributeInfo.EnumAttributeType.ID, null, null);
        atrInfoTable_ID_Class_Root[2] =  new AtrInfoTable(AttributeName.PARTUSAGE,      0x33333331, AttributeInfo.EnumAttributeType.enumeration,EnumPartUsage.getEnum(0), null);
    }

    private static AtrInfoTable[] atrInfoTable_Status_Required = new AtrInfoTable[1];
    static
    {
        atrInfoTable_Status_Required[0] =  new AtrInfoTable(AttributeName.STATUS,           0x22222222, AttributeInfo.EnumAttributeType.enumeration,EnumResStatus.getEnum(0), null);
    }

    private static AtrInfoTable[] atrInfoTable_Status_Optional = new AtrInfoTable[1];
    static
    {
        atrInfoTable_Status_Optional[0] =  new AtrInfoTable(AttributeName.STATUS,           0x33333333, AttributeInfo.EnumAttributeType.enumeration,EnumResStatus.getEnum(0), null);
    }

    private static AtrInfoTable[] atrInfoTable_UpdateID_Optional = new AtrInfoTable[1];
    static
    {
        atrInfoTable_UpdateID_Optional[0] =  new AtrInfoTable(AttributeName.UPDATEID,       0x44444331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }

    private static AtrInfoTable[] atrInfoTable_UpdateID_Required = new AtrInfoTable[1];
    static
    {
        atrInfoTable_UpdateID_Required[0] =  new AtrInfoTable(AttributeName.UPDATEID,       0x44444221, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }

    private static AtrInfoTable[] atrInfoTable_PartIDKeys = new AtrInfoTable[55];
    static
    {
        atrInfoTable_PartIDKeys[0] =  new AtrInfoTable(AttributeName.BINDERYSIGNATURENAME, 0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[1] =  new AtrInfoTable(AttributeName.BLOCKNAME,            0x33333331, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[2] =  new AtrInfoTable(AttributeName.BUNDLEITEMINDEX,      0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[3] =  new AtrInfoTable(AttributeName.CELLINDEX,            0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[4] =  new AtrInfoTable(AttributeName.CONDITION,            0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[5] =  new AtrInfoTable(AttributeName.DOCCOPIES,            0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[6] =  new AtrInfoTable(AttributeName.DOCINDEX,             0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[7] =  new AtrInfoTable(AttributeName.DOCRUNINDEX,          0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[8] =  new AtrInfoTable(AttributeName.DOCSHEETINDEX,        0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[9] =  new AtrInfoTable(AttributeName.FOUNTAINNUMBER,       0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable_PartIDKeys[10] = new AtrInfoTable(AttributeName.ITEMNAMES,            0x33333311, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable_PartIDKeys[11] = new AtrInfoTable(AttributeName.LAYERIDS,             0x33333331, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[12] = new AtrInfoTable(AttributeName.LOCATION,             0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[13] = new AtrInfoTable(AttributeName.OPTION,               0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[14] = new AtrInfoTable(AttributeName.PAGENUMBER,           0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[15] = new AtrInfoTable(AttributeName.PARTVERSION,          0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable_PartIDKeys[16] = new AtrInfoTable(AttributeName.PREFLIGHTRULE,        0x33333311, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[17] = new AtrInfoTable(AttributeName.PREVIEWTYPE,          0x33333331, AttributeInfo.EnumAttributeType.enumeration, EnumPreviewType.getEnum(0), null);
        atrInfoTable_PartIDKeys[18] = new AtrInfoTable(AttributeName.RIBBONNAME,           0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[19] = new AtrInfoTable(AttributeName.RUN,                  0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[20] = new AtrInfoTable(AttributeName.RUNINDEX,             0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[21] = new AtrInfoTable(AttributeName.RUNTAGS,              0x33333331, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable_PartIDKeys[22] = new AtrInfoTable(AttributeName.RUNPAGE,              0x33333331, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable_PartIDKeys[23] = new AtrInfoTable(AttributeName.SECTIONINDEX,         0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[24] = new AtrInfoTable(AttributeName.SEPARATION,           0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_PartIDKeys[25] = new AtrInfoTable(AttributeName.SETDOCINDEX,          0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[26] = new AtrInfoTable(AttributeName.SETINDEX,             0x33333331, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[27] = new AtrInfoTable(AttributeName.SETRUNINDEX,          0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[28] = new AtrInfoTable(AttributeName.SETSHEETINDEX,        0x33333311, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[29] = new AtrInfoTable(AttributeName.SHEETINDEX,           0x33333333, AttributeInfo.EnumAttributeType.IntegerRangeList, null, null);
        atrInfoTable_PartIDKeys[30] = new AtrInfoTable(AttributeName.SHEETNAME,            0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_PartIDKeys[31] = new AtrInfoTable(AttributeName.SIDE,                 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumSide.getEnum(0), null);
        atrInfoTable_PartIDKeys[32] = new AtrInfoTable(AttributeName.SIGNATURENAME,        0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable_PartIDKeys[33] = new AtrInfoTable(AttributeName.TILEID,               0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable_PartIDKeys[34] = new AtrInfoTable(AttributeName.WEBNAME,              0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[35] = new AtrInfoTable(AttributeName.DELIVERYUNIT0,       0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[36] = new AtrInfoTable(AttributeName.DELIVERYUNIT1,       0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[37] = new AtrInfoTable(AttributeName.DELIVERYUNIT2,       0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[38] = new AtrInfoTable(AttributeName.DELIVERYUNIT3,       0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[39] = new AtrInfoTable(AttributeName.DELIVERYUNIT4,       0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[40] = new AtrInfoTable(AttributeName.DELIVERYUNIT5,       0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[41] = new AtrInfoTable(AttributeName.DELIVERYUNIT6,       0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[42] = new AtrInfoTable(AttributeName.DELIVERYUNIT7,       0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[43] = new AtrInfoTable(AttributeName.DELIVERYUNIT8,       0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[44] = new AtrInfoTable(AttributeName.DELIVERYUNIT9,       0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[45] = new AtrInfoTable(AttributeName.EDITION,             0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[46] = new AtrInfoTable(AttributeName.EDITIONVERSION,      0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[47] = new AtrInfoTable(AttributeName.PAGETAGS,            0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable_PartIDKeys[48] = new AtrInfoTable(AttributeName.PLATELAYOUT,         0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[49] = new AtrInfoTable(AttributeName.WEBSETUP,            0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[50] = new AtrInfoTable(AttributeName.RUNSET,              0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[51] = new AtrInfoTable(AttributeName.DOCTAGS,             0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable_PartIDKeys[52] = new AtrInfoTable(AttributeName.SETTAGS,             0x33333111, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable_PartIDKeys[53] = new AtrInfoTable(AttributeName.SUBRUN,              0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable_PartIDKeys[54] = new AtrInfoTable(AttributeName.WEBPRODUCT,          0x33333111, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
    }

    protected AttributeInfo getTheAttributeInfo()
    {
        AttributeInfo ai = super.getTheAttributeInfo().updateReplace(atrInfoTable_Abstract);

        if (isPhysical())
        {
            ai.updateAdd(atrInfoTable_Physical);
        }
        else if (isParameter())
        {
            ai.updateAdd(atrInfoTable_Param);
        }

        if(isResourceUpdate())
        {
            ai.updateAdd(atrInfoTable_UpdateID_Required);
        }
        else
        {
            ai.updateAdd(atrInfoTable_UpdateID_Optional);
        }

        if (isResourceRootRoot())
        {
            ai.updateAdd(atrInfoTable_ID_Class_Required);
            ai.updateAdd(atrInfoTable_Status_Required);
        }
        else
        {
            if(isResourceElement())
            {
                ai.updateAdd(atrInfoTable_ID_Class_Optional);
            }
            else if(isResourceRoot())
            {
                ai.updateAdd(atrInfoTable_ID_Class_Root);
                ai.updateAdd(atrInfoTable_Status_Optional);
            }
            else // resource partition
            {
                ai.updateAdd(atrInfoTable_Status_Optional);
            }
        }

        final VString partIDKeys = getPartIDKeys();
        for (int i = 0; i < partIDKeys.size(); i++)
        {
            String partIDKey = partIDKeys.stringAt(i);
            for  (int j = 0; j < atrInfoTable_PartIDKeys.length; j++)
            {
                AtrInfoTable keyTable = atrInfoTable_PartIDKeys[j];
                String key = keyTable.getAttributeName();
                if (key.equals(partIDKey))
                {
                    ai.updateAdd(keyTable);
                    break;
                }
            }
        }

        return ai;
    }

    private static ElemInfoTable[] elemInfoTable_Abstract = new ElemInfoTable[4];
    static
    {
        elemInfoTable_Abstract[0] = new ElemInfoTable(ElementName.QUALITYCONTROLRESULT, 0x33333311);
        elemInfoTable_Abstract[1] = new ElemInfoTable(ElementName.GENERALID, 0x33333111);
        elemInfoTable_Abstract[2] = new ElemInfoTable(ElementName.SOURCERESOURCE, 0x33333111);
        elemInfoTable_Abstract[3] = new ElemInfoTable(ElementName.IDENTICAL, 0x33333111);
    }

    private static ElemInfoTable[] elemInfoTable_Physical = new ElemInfoTable[3];
    static
    {
        elemInfoTable_Physical[0] = new ElemInfoTable(ElementName.LOCATION,             0x33333333);
        elemInfoTable_Physical[1] = new ElemInfoTable(ElementName.CONTACT,              0x33333333);
        elemInfoTable_Physical[2] = new ElemInfoTable(ElementName.IDENTIFICATIONFIELD,  0x33333331);
    }

    protected ElementInfo getTheElementInfo()
    {
        ElementInfo ei = super.getTheElementInfo().updateAdd(elemInfoTable_Abstract);

        if (isPhysical())
        {
            ei.updateAdd(elemInfoTable_Physical);
        }

        final JDFResource resRoot = getResourceRoot();

        if (resRoot != null && resRoot.hasAttribute(AttributeName.PARTIDKEYS, null, false))
        {
            ei.updateAdd(new ElemInfoTable(getNodeName(), 0x33333333));
        }

        return ei;
    }


    /*
     * These three constructors are defined first in ElementNSImpl
     * they correspond to the three createElement methods in DocumentJDFImpl
     * which are used to create the JDF elements during parsing
     *
     * they are necessary in every class, which is inherited from JDFElement
     */

    /**
     * Constructor for JDFResource
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFResource(
            CoreDocumentImpl myOwnerDocument,
            String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFResource
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFResource(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFResource
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFResource(
            CoreDocumentImpl myOwnerDocument,
            String myNamespaceURI,
            String qualifiedName,
            String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

    //**************************************** Enumerations *********************************************

    /**
     * Enumeration for the policy of merging the amounts from ResourceLinks <p>
     * <li><b>AmountMerge_none - </b>does not recalculate amounts</li>
     * <li><b>AmountMerge_LinkOnly - </b>calculates the Resource Amount based on the Amount values in the ResourceLinks only.<br>
     *                        The original Resource Amount is ignored</li>
     * <li><b>AmountMerge_UpdateLink - </b>calculates the Resource Amount based on the difference of previous
     *                        and current resource link amounts</li>
     */
    public static final class EnumAmountMerge extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        private EnumAmountMerge(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumAmountMerge getEnum(String enumName)
        {
            return (EnumAmountMerge) getEnum(EnumAmountMerge.class, enumName);
        }

        public static EnumAmountMerge getEnum(int enumValue)
        {
            return (EnumAmountMerge) getEnum(EnumAmountMerge.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumAmountMerge.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumAmountMerge.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumAmountMerge.class);
        }

        public static final EnumAmountMerge None        = new EnumAmountMerge("None");
        public static final EnumAmountMerge LinkOnly    = new EnumAmountMerge("LinkOnly");
        public static final EnumAmountMerge UpdateLink  = new EnumAmountMerge("UpdateLink");
    }


    /**
     * Enumeration for attribute Class
     */
    public static final class EnumResourceClass extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        private EnumResourceClass(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumResourceClass getEnum(String enumName)
        {
            return (EnumResourceClass) getEnum(EnumResourceClass.class, enumName);
        }

        public static EnumResourceClass getEnum(int enumValue)
        {
            return (EnumResourceClass) getEnum(EnumResourceClass.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumResourceClass.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumResourceClass.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumResourceClass.class);
        }


        public static final EnumResourceClass Parameter         = new EnumResourceClass("Parameter");
        public static final EnumResourceClass Handling          = new EnumResourceClass("Handling");
        public static final EnumResourceClass Consumable        = new EnumResourceClass("Consumable");
        public static final EnumResourceClass Quantity          = new EnumResourceClass("Quantity");
        public static final EnumResourceClass Implementation    = new EnumResourceClass("Implementation");
        public static final EnumResourceClass PlaceHolder       = new EnumResourceClass("PlaceHolder");
        public static final EnumResourceClass Intent            = new EnumResourceClass("Intent");
    }


    /**
     * Enumeration for attribute Status
     */
    public static final class EnumResStatus extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        private EnumResStatus(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumResStatus getEnum(String enumName)
        {
            return (EnumResStatus) getEnum(EnumResStatus.class, enumName);
        }

        public static EnumResStatus getEnum(int enumValue)
        {
            return (EnumResStatus) getEnum(EnumResStatus.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumResStatus.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumResStatus.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumResStatus.class);
        }


        // EnumResStatus : enums accordng to JDF spec 3.7, Table 3-11 Status
        public static final EnumResStatus Incomplete    = new EnumResStatus(JDFConstants.INCOMPLETE);
        public static final EnumResStatus Rejected      = new EnumResStatus(JDFConstants.REJECTED);
        public static final EnumResStatus Unavailable   = new EnumResStatus(JDFConstants.UNAVAILABLE);
        public static final EnumResStatus InUse         = new EnumResStatus(JDFConstants.INUSE);
        public static final EnumResStatus Draft         = new EnumResStatus(JDFConstants.DRAFT);
        public static final EnumResStatus Complete      = new EnumResStatus(JDFConstants.COMPLETE);
        public static final EnumResStatus Available     = new EnumResStatus(JDFConstants.AVAILABLE);
    }
    /**
     * Enumeration for attribute Status
     */
    public static final class EnumLotControl extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        private EnumLotControl(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumLotControl getEnum(String enumName)
        {
            return (EnumLotControl) getEnum(EnumLotControl.class, enumName);
        }

        public static EnumLotControl getEnum(int enumValue)
        {
            return (EnumLotControl) getEnum(EnumLotControl.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumLotControl.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumLotControl.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumLotControl.class);
        }


        // EnumLotControl : enums accordng to JDF spec 3.7, Table 3-11 Status
        public static final EnumLotControl Controlled         = new EnumLotControl(JDFConstants.LOTCONTROL_CONTROLLED);
        public static final EnumLotControl NotControlled      = new EnumLotControl(JDFConstants.LOTCONTROL_NOTCONTROLLED);
    }


    /**
     * Enumeration for attribute PartUsage
     */
    public static final class EnumPartUsage extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        private EnumPartUsage(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumPartUsage getEnum(String enumName)
        {
            return (EnumPartUsage) getEnum(EnumPartUsage.class, enumName);
        }

        public static EnumPartUsage getEnum(int enumValue)
        {
            return (EnumPartUsage) getEnum(EnumPartUsage.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumPartUsage.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumPartUsage.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumPartUsage.class);
        }

//       public static final EnumPartUsage Unknown   = new EnumPartUsage(JDFConstants.PARTUSAGE_UNKNOWN);
        public static final EnumPartUsage Explicit  = new EnumPartUsage(JDFConstants.PARTUSAGE_EXPLICIT);
        public static final EnumPartUsage Sparse    = new EnumPartUsage(JDFConstants.PARTUSAGE_SPARSE);
        public static final EnumPartUsage Implicit  = new EnumPartUsage(JDFConstants.PARTUSAGE_IMPLICIT);
    }


    /**
     * Enumeration for partition keys
     */
    public static final class EnumPartIDKey extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        /**
         * @see java.lang.Object#toString()
         * @deprecated [BLD009] just for compiling PrintReady, to be removed afterwards
         */
        public String toString()
        {
            return getName();
        }

        private EnumPartIDKey(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumPartIDKey getEnum(String enumName)
        {
            return (EnumPartIDKey) getEnum(EnumPartIDKey.class, enumName);
        }

        public static EnumPartIDKey getEnum(int enumValue)
        {
            return (EnumPartIDKey) getEnum(EnumPartIDKey.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumPartIDKey.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumPartIDKey.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumPartIDKey.class);
        }

        public static final EnumPartIDKey BinderySignatureName
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_BINDERYSIGNATURENAME);
        public static final EnumPartIDKey BlockName
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_BLOCKNAME);
        public static final EnumPartIDKey BundleItemIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_BUNDLEITEMINDEX);
        public static final EnumPartIDKey CellIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_CELLINDEX);
        public static final EnumPartIDKey Condition
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_CONDITION);
        public static final EnumPartIDKey DocCopies
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_DOCCOPIES);
        public static final EnumPartIDKey DocIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_DOCINDEX);
        public static final EnumPartIDKey DocRunIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_DOCRUNINDEX);
        public static final EnumPartIDKey DocSheetIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_DOCSHEETINDEX);
        public static final EnumPartIDKey FountainNumber
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_FOUNTAINNUMBER);
        public static final EnumPartIDKey ItemNames
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_ITEMNAMES);
        public static final EnumPartIDKey LayerIDs
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_LAYERIDS);
        public static final EnumPartIDKey Location
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_LOCATION);
        public static final EnumPartIDKey Option
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_OPTION);
        public static final EnumPartIDKey PageNumber
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_PAGENUMBER);
        public static final EnumPartIDKey PartVersion
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_PARTVERSION);
        public static final EnumPartIDKey PreflightRule
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_PREFLIGHTRULE);
        public static final EnumPartIDKey PreviewType
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_PREVIEWTYPE);
        public static final EnumPartIDKey RibbonName
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_RIBBONNAME);
        public static final EnumPartIDKey Run
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_RUN);
        public static final EnumPartIDKey RunIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_RUNINDEX);
        public static final EnumPartIDKey RunTags
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_RUNTAGS);
        public static final EnumPartIDKey RunPage
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_RUNPAGE);
        public static final EnumPartIDKey Separation
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_SEPARATION);
        public static final EnumPartIDKey SectionIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_SECTIONINDEX);
        public static final EnumPartIDKey SetDocIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_SETDOCINDEX);
        public static final EnumPartIDKey SetSheetIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_SETSHEETINDEX);
        public static final EnumPartIDKey SetIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_SETINDEX);
        public static final EnumPartIDKey SetRunIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_SETRUNINDEX);
        public static final EnumPartIDKey SheetIndex
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_SHEETINDEX);
        public static final EnumPartIDKey SheetName
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_SHEETNAME);
        public static final EnumPartIDKey Side
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_SIDE);
        public static final EnumPartIDKey SignatureName
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_SIGNATURENAME);
        public static final EnumPartIDKey TileID
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_TILEID);
        public static final EnumPartIDKey WebName
        = new EnumPartIDKey(JDFConstants.PARTIDKEY_WEBNAME);
        // new in JDF 1.3
        public static final EnumPartIDKey DeliveryUnit0= new EnumPartIDKey(AttributeName.DELIVERYUNIT0);
        public static final EnumPartIDKey DeliveryUnit1= new EnumPartIDKey(AttributeName.DELIVERYUNIT1);
        public static final EnumPartIDKey DeliveryUnit2= new EnumPartIDKey(AttributeName.DELIVERYUNIT2);
        public static final EnumPartIDKey DeliveryUnit3= new EnumPartIDKey(AttributeName.DELIVERYUNIT3);
        public static final EnumPartIDKey DeliveryUnit4= new EnumPartIDKey(AttributeName.DELIVERYUNIT4);
        public static final EnumPartIDKey DeliveryUnit5= new EnumPartIDKey(AttributeName.DELIVERYUNIT5);
        public static final EnumPartIDKey DeliveryUnit6= new EnumPartIDKey(AttributeName.DELIVERYUNIT6);
        public static final EnumPartIDKey DeliveryUnit7= new EnumPartIDKey(AttributeName.DELIVERYUNIT7);
        public static final EnumPartIDKey DeliveryUnit8= new EnumPartIDKey(AttributeName.DELIVERYUNIT8);
        public static final EnumPartIDKey DeliveryUnit9= new EnumPartIDKey(AttributeName.DELIVERYUNIT9);
        public static final EnumPartIDKey Edition= new EnumPartIDKey(AttributeName.EDITION);
        public static final EnumPartIDKey EditionVersion= new EnumPartIDKey(AttributeName.EDITIONVERSION);
        public static final EnumPartIDKey PageTags= new EnumPartIDKey(AttributeName.PAGETAGS);
        public static final EnumPartIDKey PlateLayout= new EnumPartIDKey(AttributeName.PLATELAYOUT);
        public static final EnumPartIDKey RunSet= new EnumPartIDKey(AttributeName.RUNSET);
        public static final EnumPartIDKey DocTags= new EnumPartIDKey(AttributeName.DOCTAGS);
        public static final EnumPartIDKey SetTags= new EnumPartIDKey(AttributeName.SETTAGS);
        public static final EnumPartIDKey SubRun= new EnumPartIDKey(AttributeName.SUBRUN);
        public static final EnumPartIDKey WebProduct= new EnumPartIDKey(AttributeName.WEBPRODUCT);
        public static final EnumPartIDKey StationName= new EnumPartIDKey(AttributeName.STATIONNAME); // jdf1.3 errata addition
        public static final EnumPartIDKey WebSetup= new EnumPartIDKey(AttributeName.WEBSETUP);
    }


    /**
     * Enumeration for attribute SpawnStatus
     */
    public static final class EnumSpawnStatus extends ValuedEnum
    {
        private static final long serialVersionUID = 1L;
        private static int m_startValue = 0;

        /**
         * @see java.lang.Object#toString()
         * @deprecated [BLD009] just for compiling PrintReady, to be removed afterwards
         */
        public String toString()
        {
            return getName();
        }

        private EnumSpawnStatus(String name)
        {
            super(name, m_startValue++);
        }

        public static EnumSpawnStatus getEnum(String enumName)
        {
            return (EnumSpawnStatus) getEnum(EnumSpawnStatus.class, enumName);
        }

        public static EnumSpawnStatus getEnum(int enumValue)
        {
            return (EnumSpawnStatus) getEnum(EnumSpawnStatus.class, enumValue);
        }

        public static Map getEnumMap()
        {
            return getEnumMap(EnumSpawnStatus.class);
        }

        public static List getEnumList()
        {
            return getEnumList(EnumSpawnStatus.class);
        }

        public static Iterator iterator()
        {
            return iterator(EnumSpawnStatus.class);
        }


        // EnumSpawnStatus : enums accordng to JDF spec 3.7, Table 3-11 SpawnStatus
        public static final EnumSpawnStatus NotSpawned  = new EnumSpawnStatus(JDFConstants.NOTSPAWNED);
        public static final EnumSpawnStatus SpawnedRO   = new EnumSpawnStatus(JDFConstants.SPAWNEDRO);
        public static final EnumSpawnStatus SpawnedRW   = new EnumSpawnStatus(JDFConstants.SPAWNEDRW);
    }




    //**************************************** Methods *********************************************
    /**
     * toString
     *
     * @return String
     */
    public String toString()
    {
        return "JDFResource[ --> " + super.toString() + " ]";
    }


    /**
     * Status related shorthand for really lazy people
     * Sets Status of resource as Available if bAvailable=true
     * or as Unavailable if bAvailable=false
     *
     * @param bAvailable
     * @deprecated use SetStatus(EnumResStatus)
     * default: setAvailable(true)
     */
    public void setAvailable(boolean bAvailable)
    {
        setStatus(bAvailable
                ? EnumResStatus.Available
                        : EnumResStatus.Unavailable);
    }

    /**
     * Tests whether Status of resource is Available
     *
     * @param  bRecurseRefs if bRecurseRefs is set to true, also recurses into all resources
     *         linked by rRefs and returns true if the minimum Status is Status_Available
     *
     * @return boolean true, if Status is Available
     * @deprecated use getStatus
     * default: IsAvailable(false)
     */
    public boolean isAvailable(boolean bRecurseRefs)
    {
        return getResStatus(bRecurseRefs).equals(EnumResStatus.Available);
    }

    /**
     * Tests, whether 'this' is root of partition (i.e. there is no element with the same name over 'this')
     *
     * @return boolean true, if 'this' is a root
     */
    public boolean isRootElement()
    {
        final KElement parent = getParentNode_KElement();

        if (parent == null)
        {
            throw new JDFException("JDFResource.IsRootElement: resource without parent");
        }

        return !getNodeName().equals(parent.getNodeName());
    }

    /**
     * Checks, whether this resourse is a quantity resource.
     * For quantity resource the class of 'this' must be either Quantity or Consumable
     *
     * @return boolean true, if 'this' is a quantity resource
     */
    public boolean isQuantity()
    {
        final EnumResourceClass c = getResourceClass();
        return c.equals(EnumResourceClass.Quantity) || c.equals(EnumResourceClass.Consumable);
    }

    /**
     * Checks, whether this resourse is a parameter resource
     *
     * @return boolean true, if 'this' is a parameter resource
     */
    public boolean isParameter()
    {
        return EnumResourceClass.Parameter == getResourceClass();
    }

    /**
     * getLock
     *
     * @return boolean
     *
     * @deprecated [BLD009] use getLocked
     */
    public boolean getLock()
    {
        return getBoolAttribute(AttributeName.LOCKED, null, false);
    }

    /**
     * Lock
     *
     * @param bLock
     *
     * @deprecated [BLD009] use setLocked()
     */
    public void lock(boolean bLock)
    {
        if (bLock)
        {
            setAttribute(AttributeName.LOCKED, true, null);
        }
        else
        {
            removeAttribute(AttributeName.LOCKED, null);
        }
    }


    /**
     * Checks, whether the resource is one of the physical resource classes
     *
     * @return boolean true, if the resource is one of the physical resource classes
     */
    public boolean isPhysical()
    {
        EnumResourceClass c= getResourceClass();

        return ( c!=null) && (c==EnumResourceClass.Consumable)||
        (c==EnumResourceClass.Quantity)   ||
        (c==EnumResourceClass.Handling);
    }


    /**
     * Gets the root resource of 'this'
     *
     * @return JDFResource - the root resource element
     *
     * @throws JDFException if GetResourceRoot ran into the JDF node while searching
     */
    public JDFResource getResourceRoot()
    {
        JDFResource res     = null;
        final JDFElement e  = (JDFElement) getDeepParent(getLocalName(), Integer.MAX_VALUE);

        if (e != null)
        {
            Node parentNode = e.getParentNode();
            if (parentNode != null)
            {
                String parentName = parentNode.getLocalName();

                if (parentName != null && !parentName.equals(JDFConstants.EMPTYSTRING) )
                {
                    if (StringUtil.hasToken(validParentNodeNames(), parentName,0))
                    {
                        res = (JDFResource) e;
                    }
                    else
                    {
                        while (parentNode!=null
                                && !(parentNode instanceof JDFResource)
                                && !(parentNode instanceof JDFNode)
                                && !(parentNode instanceof JDFJMF))
                        {
                            // find the first resource uptree
                            parentNode = parentNode.getParentNode();
                        }

                        if (((JDFElement) parentNode) instanceof JDFResource)
                        {   // e was a resource element --> search root of the parent element
                            return ((JDFResource) parentNode).getResourceRoot();
                        }

                        if (parentNode instanceof JDFNode)
                        {
                            if ((e instanceof JDFNodeInfo) || (e instanceof JDFCustomerInfo))
                            {
                                res = (JDFResource) e;
                            }
                            else
                            {
                                throw new JDFException("JDFResource.getResourceRoot ran into the JDF node while searching");
                            }
                        }
                        else if (parentNode instanceof JDFJMF)
                        {
                            throw new JDFException("JDFResource.getResourceRoot ran into the JMF node while searching");
                        }
                    }
                }
            }
            else // parentNode == null, this is a standalone resource
            {
                res=(JDFResource)e;
            }
        }

        return res;
    }

    /**
     * Call this function to get information about the node name of the 'this' resource
     *
     * @deprecated use getNodeName or getLocalName
     * @return String the node name of 'this' resource
     */
    public String getResourceType()
    {
        return getNodeName();
    }

    /**
     * Gets the resourcepool that 'this' lives in
     *
     * @return JDFResourcePool: the ResourcePool where 'this' lives
     *
     * @deprecated [BLD009] use GetResourcePool instead <br>
     */
    public JDFResourcePool getPool()
    {
        return (JDFResourcePool) getDeepParent(ElementName.RESOURCEPOOL, 0);
    }

    /**
     * default initialization
     *
     * @return boolean true, if successful
     *
     */
    public boolean init()
    {
        if (isResourceRootRoot())
        {
            appendAnchor(null);
            if(!hasAttribute(AttributeName.STATUS)) {
				setResStatus(EnumResStatus.Unavailable,false);
			}
            EnumVersion v=getVersion(true);
            if(v==null || v.getValue()>=EnumVersion.Version_1_2.getValue()&&autoAgent)
            {
                setAgentName(JDFAudit.getStaticAgentName());
                setAgentVersion(JDFAudit.getStaticAgentVersion());
            }

        }
        return true;
    }

    /**
     * Makes from 'this' resource subelement a root resource element (direct child) of the specified parentPool
     * or (in default case) of ResourcePool, where it lives. <br>
     *
     * The Status and SpawnStatus attribute values of the new root resource are taken from the old root resource.
     *
     * @param alias id attribute of the newly created resource
     * @param parentPool the pool where the newly created resource is stored <br>
     *                   if null the local pool is used. Must use JDFElement for the pool because of recursive #defines
     * @param bLinkHere if true, creates a refelement (link) to the newly created resource
     *                  at the position where 'this' originally resided.
     *
     * @return JDFRefElement link to the newly created resource from the position were it originally resided
     *
     * @default makeRootResource(null, null, true)
     */
    public JDFResource makeRootResource(  String alias,  JDFElement parentPool, boolean bLinkHere)
    {
        JDFResource retRes = this;

        //if this is already in the resource pool do nothing
        if (isResourceElement())
        {
            JDFElement link = null;

            if (bLinkHere)
            {
                //create a RefElement at the same (in front of) position as this
                link = (JDFElement) getParentNode_KElement().
                insertBefore(getNodeName() + JDFConstants.REF, this, null);
                if (isWildCard(alias))
                {
                    alias = getIDPrefix() + uniqueID(0);
                }
                link.appendHRef(this, null, alias);
            }

            // use the local pool if no other is specified
            JDFResourcePool rp =  null;
            if(parentPool instanceof JDFResourcePool) {
				rp=(JDFResourcePool) parentPool;
			}

            if (rp == null)
            {
                rp = getResourcePool();
            }

            if (rp == null)
            {
                rp = getJDFRoot().getCreateResourcePool();
            }

            final JDFResource oldRoot = getResourceRoot();
            JDFResource newRes = rp.appendResource(this);

            if (oldRoot.hasAttribute(AttributeName.STATUS))
            {
                newRes.setResStatus(oldRoot.getResStatus(false),false);
            }

            if (oldRoot.hasAttribute(AttributeName.SPAWNSTATUS))
            {
                newRes.setSpawnStatus(oldRoot.getSpawnStatus());
            }

            newRes.init();
            retRes = newRes;
        }

        return retRes;
    }

    /**
     * Gets the creators (bCreate=true) or consumers (bCreate=false) of this resource
     *
     * @param bCreate switcher for getter: if true gets creators, otherwise gets consumers
     *
     * @return VElement list of JDF nodes that create or consume this resource
     */
    public VElement getCreator(boolean bCreate)
    {
        // if !bCreate the return value is the consumer ;-)
        final VElement v     = getLinksAndRefs(true,false);
        final VElement vv    = new VElement();
        KElement kElem = null;
        if(v==null) {
			return null;
		}

        for (int i = 0; i < v.size(); i++)
        {
            kElem = (KElement) v.elementAt(i);
            if (kElem instanceof JDFResourceLink)
            {
                final JDFResourceLink l = (JDFResourceLink) kElem;
                if (JDFResourceLink.EnumUsage.Input.equals(l.getUsage()) != bCreate)
                {
                    final JDFPool pool = l.getPool();
                    if(pool!=null)
                    {
                        vv.add(pool.getParentNode_KElement());
                    }
                }
            }
        }
        vv.unify();
        return vv.size()>0 ? vv : null;
    }

    /**
     * Merges partitioned resources into this resource
     * uses PartIDKey to identify the correct resources
     *
     * @param resToMerge the resource leaf to merge into this
     * @param spawnID the spawnID of the spawning that will now be merged
     * @param amountPolicy how to clean up the Resource amounts after merging
     * @param bLocalResource must be true for the local resources in a spawned node and its subnodes, which default to RW
     *
     * @throws JDFException if here is an attempt to merge incompatible resources
     * @throws JDFException if here is an attempt to merge incompatible partitions
     * @deprecated used only by merge - moved there
     * @default mergePartition (resToMerge, spawnID, EnumAmountMerge.None, false);
     */
    public void mergePartition(JDFResource resToMerge, String spawnID, EnumAmountMerge amountPolicy, boolean bLocalResource)
    {
        JDFMerge.mergePartition(this,resToMerge,spawnID,amountPolicy,bLocalResource);
    }

    /**
     * set the partIDKeys attribute of the root of this
     * @param partIDKeys the value to set key to
     */
    public void setPartIDKeys(VString partIDKeys)
    {
        getResourceRoot().setAttribute(AttributeName.PARTIDKEYS,StringUtil.setvString(partIDKeys,JDFConstants.BLANK,null,null));
    }


    /**
     * Clone the resource element oldRes and merge it with this resource
     *
     * @param oldRes the resource element to clone and to merge with this resource
     *
     * @return JDFResource merged resource
     */
    public JDFResource mergeCloneResource(JDFResource oldRes)
    {
        final JDFAttributeMap m = getAttributeMap();  // get all preset attributes
        mergeElement(oldRes, false);            // clone oldRes onto this
        setAttributes(m);                       // reset all preset attributes

        return this;
    }


    /**
     * Gets from all referenced resources the int cast of the minimum value of enumerated attribute
     * e.g. used in IsAvailable()/GetStatus() to find in all referenced resources Status, which is the minimum
     *
     * @param key attribute name you are searching for
     * @param allowedValues string of allowed values in case of an enumerated attribute
     * @param def default value, if no explicit value exists
     * @param nameSpaceURI namespace of attribute key
     *
     * @return int the int cast of the minimum referenced value
     * @deprecated
     * @default getMinRefAttribute(key, allowedValues, 0, null)
     */
    public int getMinRefAttribute(
            String key,
            Vector allowedValues,
            int def,
            String nameSpaceURI)
    {
        int ret = getEnumAttribute(key, allowedValues, nameSpaceURI, def, false);
        final VElement v = new VElement(getvHRefRes(true));

        for (int i = 0; i < v.size() && ret != 0; i++)
        {
            final JDFResource rs = (JDFResource) v.elementAt(i);
            ret = Math.min(ret,rs.getEnumAttribute(key, allowedValues, nameSpaceURI, def, false));
        }

        return ret;
    }

    /**
     * Gets from all referenced resources the int cast of the maximum value of enumerated attribute
     *
     * @param key attribute name you are searching for
     * @param allowedValues string of allowed values in case of an enumerated attribute
     * @param def default value, if no explicit value exists
     * @param nameSpaceURI namespace of attribute key
     *
     * @return int: the int cast of the maximum referenced value
     * @deprecated never used
     * @default getMaxRefAttribute(key, allowedValues, 0, null)
     */
    public int getMaxRefAttribute(
            String key,
            Vector allowedValues,
            int def,
            String nameSpaceURI)
    {
        int ret = getEnumAttribute(key, allowedValues, nameSpaceURI, def, false);
        final VElement v = new VElement(getvHRefRes(true));

        for (int i = 0; i < v.size() && ret != 0; i++)
        {
            final JDFResource rs = (JDFResource) v.elementAt(i);
            ret = Math.max(ret, rs.getEnumAttribute(key, allowedValues, nameSpaceURI, def, false));
        }

        return ret;
    }

    /**
     * Gets all elements with name linkName, which contain resource links that point to this resource
     *
     * @param linkName defaults to any
     *
     * @return VElement vector of all found elements
     *
     * @default getLinks(null)
     * @deprecated [BLD009] use getLinks(linkName, null)
     */
    public VElement getLinks(String linkName)
    {
        return getLinks(linkName, null);
    }

    /**
     * Gets all elements with name linkName, which contain id/idrefs that point to this resource
     *
     * @param linkName defaults to any
     * @param nameSpaceURI attribute namespace you are searching in
     *
     * @return VElement - vector of all found elements
     *
     * @default getLinks(null, null)
     */
    public VElement getLinks(String linkName, String nameSpaceURI)
    {
        final JDFAttributeMap m = new JDFAttributeMap(AttributeName.RREF, getID());
        return getParentJDF().getChildrenByTagName(linkName, nameSpaceURI, m, false, false, 0);
    }

    /**
     * Gets all resourcelinks and refelements that link to this
     *
     * @return VElement -  vector of all found elements, null if none found
     * @deprecated use getLinksAndRefs(true,true);
     */
    public VElement getLinksAndRefs()
    {
        return getLinksAndRefs(true,true);
    }
        /**
         * Gets all resourcelinks and refelements that link to this
         *
         * @return VElement -  vector of all found elements, null if none found
         */
    public VElement getLinksAndRefs(boolean bLink, boolean bRef)
    {
        if(!bLink && !bRef) {
			return null;
		}
        final String resID = getID();
        
        final JDFAttributeMap mID = new JDFAttributeMap(AttributeName.RREF, resID);
        final VString refList=new VString();
        if(bLink) {
			refList.add(getLinkString());
		}
        if(bRef) {
			refList.add(getRefString());
		}

        final JDFNode n=getParentJDF();
        if(n==null) {
			return null;
		}
        VElement vRet=null;
        if(bRef)
        {
            vRet=n.getChildrenFromList(refList, mID, false, null);
        }
        else
        {
            VElement vNodes=n.getvJDFNode(null, null, false);
            vRet=new VElement();
            final int size = vNodes.size();
            for(int i=0;i<size;i++)
            {
                VElement vTmp=((JDFNode)vNodes.elementAt(i)).getResourceLinks(null);
                final int size2 = vTmp==null ? 0 : vTmp.size();
                for(int j=0;j<size2;j++)
                {
                    final JDFResourceLink link = (JDFResourceLink)vTmp.item(j);
                    if(resID.equals(link.getrRef()))
                        vRet.add(link);
                }              
            }
        }
        JDFAttributeMap mPart=getPartMap();
        if(mPart!=null && mPart.size()>0)
        {
            for (int i=vRet.size()-1;i>=0;i--)
            {
                KElement e=(KElement)vRet.elementAt(i);
                VJDFAttributeMap linkMapVector=null;
                if(e instanceof JDFResourceLink)
                {
                    linkMapVector=((JDFResourceLink)e).getPartMapVector();
                }
                else if(e instanceof JDFRefElement)
                {
                    final JDFAttributeMap partMap = ((JDFRefElement)e).getPartMap();
                    if(partMap!=null)
                    {
                        linkMapVector=new VJDFAttributeMap();
                        linkMapVector.add(partMap);
                    }
                }

                if(linkMapVector==null)
                {
                    continue; // the link refers to the root, thus also to this
                }
                int nZapp=0;
                final int size = linkMapVector.size();
                for(int j=0;j<size;j++)
                {
                    JDFAttributeMap m2=linkMapVector.elementAt(j);
                    if(!m2.overlapMap(mPart))
                    {
                        nZapp++;
                    }
                }
                if(nZapp==size) // no matching parts at all
                {
                    vRet.remove(i);
                }
            }
        }

        return vRet.size()>0 ? vRet : null;
    }

    /**
     * list of valid node names of potential parents for a resource
     *
     * @return String comma separated list of node names
     */
    public static String[] validParentNodeNames()
    {
        final String nodeNames[] = {
                "ResourcePool","PipeParams","ResourceInfo","ResourceCmdParams", // copy of validRootParentNodeNames
                "DeviceInfo","DropItemIntent","DropItem","ProductionIntent","CustomerInfo","NodeInfo","Ancestor",ElementName.PHASETIME};

        return nodeNames;
    }

    /**
     * list of valid node names of potential parents for a resource that impy a real resource root with class, id etc
     *
     * @return String[] of node names
     */
    public static String[] validRootParentNodeNames()
    {
        final String[] nodeNames = {"ResourcePool","PipeParams","ResourceInfo","ResourceCmdParams"}; // must also copy to validParentNodeNames
        return nodeNames;
    }

    /**
     * Tests, if the first ancestor with a name different from the node name is not one of
     * DropItemIntent,CustomerInfo,NodeInfo,ResourcePool,PipeParams,ResourceInfo,ResourceCmdParams. <br>
     * In other words: if this resource is a subelement, but not a resourceroot
     *
     * @return boolean true, if this is a subelement but not a root
     */
    public boolean isResourceElement()
    {
        final KElement e=getDeepParentNotName(getLocalName());
        if(e==null) {
			return false;
		}

        final String par = e.getLocalName();
        return !StringUtil.hasToken(validRootParentNodeNames(), par, 0);
    }


    /**
     * Gets the first part that matches mAttribute
     *
     * @param m the map of key-value partitions (where key - PartIDKey, value - its value)
     * @param bIncomplete if true, also accept nodes that are are not completely specified in the partmap, <br>
     *  e.g. if partitioned by run, RunPage and only Run is specified
     *
     * @return JDFResource - the first matching resource leaf or node
     * @deprecated use getPartition(JDFAttributeMap m, JDFResource.EnumPartUsage partUsage)
     * @default getPartition(m, true)
     */
    public JDFResource getPartition(JDFAttributeMap m, boolean bIncomplete)
    {
        JDFResource retRes = this;

        if (m!=null && !m.isEmpty())
        {
            retRes = getDeepPart(m, bIncomplete);
        }

        return retRes;
    }
    /**
     * Gets the first part that matches mAttribute
     *
     * @param m the map of key-value partitions (where key - PartIDKey, value - its value)
     * @param partUsage also accept nodes that are are not completely specified in the partmap,
     *  e.g. if partitioned by run, RunPage and only Run is specified
     *
     * @return JDFResource: the first matching resource leaf or node
     * @default getPartition(m, null)
     */
    public JDFResource getPartition(JDFAttributeMap m, JDFResource.EnumPartUsage partUsage)
    {
        if (m==null || m.isEmpty())
            return this;
        return  getDeepPart(m, partUsage);
     }


    /**
     * Gets the first part that matches key-value pair
     *
     * @param key the PartIDKey attribute name
     * @param value the string value of the partition key
     * @param bIncomplete if true, also accept nodes that are are not completely specified in the partmap,
     *                    e.g. if partitioned by run, RunPage and only Run is specified
     *
     * @return JDFResource the first matching resource leaf or node
     *
     * @deprecated use getPartition(JDFAttributeMap m, JDFResource.EnumPartUsage partUsage)
     * @default getPartition(key, value, true)
     */
    public JDFResource getPartition(
            EnumPartIDKey key,
            String value,
            boolean bIncomplete)
    {
        final JDFAttributeMap mp = new JDFAttributeMap();
        mp.put(key.toString(), value);

        return getPartition(mp, bIncomplete);
    }

    /**
     * Recursively adds the partition leaves defined in partMap
     *
     * @param partMap the map of part keys
     * @param vPartKeys the vector of partIDKeys strings of the resource.
     *                    If empty (the default), the Resource PartIDKeys attribute is used
     *
     * @return JDFResource the last created partition leaf
     *
     * @throws JDFException if there are in the partMap not matching partitions
     * @throws JDFException if there is an attempt to fill non-matching partIDKeys
     * @throws JDFException if by adding of last partition key there is
     *                      either non-continuous partmap or left more than one key
     *
     * @default getCreatePartition(partMap, null)
     */
    public JDFResource getCreatePartition(JDFAttributeMap partMap, VString vPartKeys)
    {
        if(partMap==null) {
			return getResourceRoot();
		}

        final JDFAttributeMap localPartMap    = new JDFAttributeMap(partMap); // create a copy because it might get modified
        boolean appendEnd               = true;
        VString vPartIDKeys = reorderPartKeys(vPartKeys);

        // check whether we are already ok
        int iMore=0;
        Iterator it=localPartMap.getKeyIterator();
        while(it.hasNext())
        {
            final String key = (String)it.next();
            if (!vPartIDKeys.contains(key))
            {
                iMore++;
            }
        }
        // only heuristically add stuff if needed...
        if(iMore>1){
            vPartIDKeys = expandKeysFromNode(localPartMap,vPartIDKeys);
        }

        final JDFAttributeMap thisMap = getPartMap();
        if (!JDFPart.overlapPartMap(thisMap,localPartMap))
        {
            throw new JDFException("JDFResource.GetCreatePartition: non-matching partitions");
        }

        if(thisMap!=null)
        {
            final VString thisKeys = thisMap.getKeys();
            final int siz = (thisKeys.size() < vPartIDKeys.size()) ? thisKeys.size() : vPartIDKeys.size();

            // remove the keys of this
            for (int i = 0; i < siz; i++)
            {
                final String key = (String)vPartIDKeys.elementAt(0);
                localPartMap.remove(key);

                vPartIDKeys.remove(0);
            }
        }

        boolean allParts = true;
        boolean creating = false;
        JDFResource leaf = this;
        // create all partitions
        for (int k = 0; k < vPartIDKeys.size(); k++)
        {
            final String key = (String)vPartIDKeys.elementAt(k);
            final EnumPartIDKey enumKey = EnumPartIDKey.getEnum(key);
            final String value = localPartMap.get(key);

            if (localPartMap.containsKey(key))
            {
                if (allParts)
                {
                    if (!creating)
                    {
                        JDFResource nextLeaf = leaf.getPartition(new JDFAttributeMap(key,value), EnumPartUsage.Explicit);
                        if (nextLeaf == null)
                        {
                            creating = true;
                        }
                        else
                        {
                            leaf = nextLeaf;
                        }
                    }
                    if (creating)
                    {
                        leaf = leaf.addPartition(enumKey,value);
                    }
                    localPartMap.remove(key);
                    if(localPartMap.size() == 0)
                    {
                        break; // nothing left to do
                    }
                }
                else
                {
                    throw new JDFException("GetCreatePartition: attempting to fill non-matching partIDKeys");
                }
            }
            else
            {
                allParts = false;
            }
        }

        // add last partition key
        final int partSize = localPartMap.size();
        if (appendEnd && allParts && partSize == 1)// one left and continuous, can add
        {
            final String key = (String)localPartMap.getKeys().elementAt(0);
            leaf = leaf.addPartition(EnumPartIDKey.getEnum(key), localPartMap.get(key));
        }
        else if (partSize>0)// either non - continuous or more than one left
        {
            throw new JDFException("AddPartitionMap: incompatible partmap");
        }
        return leaf;
    }
    /**
     * reorder the partIDKeys used for generating the new partition based on existing partIDKeys
     * @param vPartKeys
     * @return VString the reordered VString of partIDKeys
     */
    private VString reorderPartKeys(VString vPartKeys)
    {
         if (vPartKeys == null || vPartKeys.isEmpty())
        {
            return getPartIDKeys();
        }
         VString vPartIDKeys = new VString(vPartKeys);
         VString vExistingPartKeys=getPartIDKeys();
         VString vTmpPartIDKeys=new VString();
         if(vExistingPartKeys!=null && !vExistingPartKeys.isEmpty())
         {
             int n=vExistingPartKeys.size();
             if(vPartIDKeys.size()<n) {
				n=vPartIDKeys.size();
			}

             for(int i=0;i<n;i++)
             {
                 final Object partKey = vExistingPartKeys.elementAt(i);
                 if(!vPartIDKeys.contains(partKey)) // allow reordering of the existing partidkeys
                 {
                     throw new JDFException("getCreatePartiton: adding incompatible partitions");
                 }
                 vTmpPartIDKeys.add(partKey);
                 vPartIDKeys.remove(partKey);
             }
             for(int i=0;i<vPartIDKeys.size();i++)
             {
                 vTmpPartIDKeys.add(vPartIDKeys.elementAt(i));
             }
             vPartIDKeys=vTmpPartIDKeys;
         }
         return vPartIDKeys;
    }

    /**
     * heuristic guess of the best possible partidkey sequence
     * @param partMap the partmap that we want to create
     * @param vPartIDKeys the known base partidkeys
     * @return the best guess vector of partidkeys
     */
    private VString expandKeysFromNode(JDFAttributeMap partMap, VString vPartIDKeys)
    {
        JDFNode n = this.getParentJDF();
        if (n == null) {
			return vPartIDKeys;
		}

        VString nodeKeys = n.getPartIDKeys(partMap);
        int nodeKeySize = nodeKeys.size();

        int partKeySize = vPartIDKeys != null ? vPartIDKeys.size() : 0;
        if (nodeKeySize <= partKeySize) {
			return vPartIDKeys;
		}

        if (vPartIDKeys != null) {
	        final Iterator nodeKeysIterator    = nodeKeys.iterator();
	        final Iterator vPartIDKeysIterator = vPartIDKeys.iterator();
	        while (vPartIDKeysIterator.hasNext()) {
	            if (!vPartIDKeysIterator.next().equals(nodeKeysIterator.next())) {
					return vPartIDKeys; // nodekeys and partkeys are incompatible, return the input
				}
			}
        }

        // all beginning elements are equal but we have more - use these as a best guess
        return nodeKeys;
    }

    /**
     * Gets the first part that matches key-value
     * if it does not exist, create it
     *
     * @param key the PartIDKey attribute name
     * @param value the string value of the partition key
     * @param vPartIDKeys the vector of partIDKeys strings of the resource.
     *
     * @return JDFResource the matching resource
     *
     * @default getCreatePartition(key, value, null)
     */
    public JDFResource getCreatePartition(EnumPartIDKey key, String value, VString vPartIDKeys)
    {
        final JDFAttributeMap mp = new JDFAttributeMap(key.getName(), value);
        return getCreatePartition(mp, vPartIDKeys);
    }

    /**
     * version fixing routine
     *
     * uses heuristics to modify this element and its children to be compatible with a given version
     * in general, it will be able to move from low to high versions but potentially fail
     * when attempting to move from higher to lower versions
     *
     * @param version version that the resulting element should correspond to
     * @return true if successful
     */
    public boolean fixVersion(EnumVersion version){
        boolean bRet=true;
        if(!isResourceRootRoot())
        {
            removeAttribute(AttributeName.STATUS);
            if(!isResourceElement()) {
				removeAttribute(AttributeName.CLASS);
			}
        }
        else
        {
            if(hasAttribute(AttributeName.RREFS)) {
				removeAttribute(AttributeName.RREFS);
			}

            init();
        }
        if(version!=null)
        {
            if(!isResourceRootRoot())
            {
                if(version.getValue()>=EnumVersion.Version_1_2.getValue()){
                    removeAttribute(AttributeName.ID);
                }
            }
            else
            {
                if(version.getValue()<=EnumVersion.Version_1_2.getValue()){
                    if(getPartUsage()==EnumPartUsage.Sparse)
                    {
                        setPartUsage(EnumPartUsage.Implicit);
                    }
                }
            }
        }
        return super.fixVersion(version) && bRet;
    }

    /**
     * Tests, if this leaf has a consistent PartIDKey as specified by key
     *
     * @param key the PartIDKey attribute name
     * @param root
     * @param partIDKeys
     *
     * @return boolean true, if key exists in this leaf is in PartIDKeys
     */
    protected boolean consistentPartIDKeys(EnumPartIDKey key, JDFResource root, VString partIDKeys)
    {
        if(key==null) {
			return false;
		}
        Vector vImplicitKeys=getImplicitPartitions();
        if(vImplicitKeys!=null)
        {
            if(vImplicitKeys.contains(key)) {
				return false;
			}
        }

        String keyName = key.getName();
        int nDepth = 0;
        JDFResource r = this;
        // the key exists but is not in PartIDKeys, oops
        int index = partIDKeys.indexOf(keyName);
        if(index<0) {
			return !hasAttribute(keyName, null, false);
		}

        while(!r.equals(root))
        {
            nDepth++;
            r = (JDFResource) r.getParentNode();
            if(r == null) {
				break;
			}
        }
        if(partIDKeys.size() < nDepth) {
			return false;
		}

        KElement e = this;
        // loop down to the resource root, checking whether exactly those attributes required exist
        for(int i = nDepth-1; i>=-1; i--)
        {
            if((i==index) && !e.hasAttribute_KElement(keyName, null, false)) {
				return false;
			}
            if((i!=index) && e.hasAttribute_KElement(keyName, null, false)) {
				return false;
			}
            if(i>-1) {
				e = e.getParentNode_KElement();
			}
        }
        // all is well

        return true;
    }
    /**
     * Tests, if this leaf has a consistent PartIDKey as specified by key
     *
     * @param key the PartIDKey attribute name
     * @return boolean true, if key exists in this leaf is in PartIDKeys
     */
    public boolean consistentPartIDKeys(EnumPartIDKey key)
    {
        final JDFResource root = getResourceRoot();
        return consistentPartIDKeys(key,root,root.getPartIDKeys());
    }

/////////////////////////////////////////////////////////////////////////////////
    private void removeImplicitPartions(JDFAttributeMap m)
    {
        if(m==null) {
			return;
		}
        Vector v=getImplicitPartitions();
        if(v==null) {
			return;
		}
        for(int i=0;i<v.size();i++)
        {
            m.remove(((EnumPartIDKey)v.elementAt(i)).getName());
        }
    }
    /////////////////////////////////////////////////////////////////////////////////

    protected VElement getDeepPartVector(
            JDFAttributeMap m_in, EnumPartUsage partUsage, int matchingDepth, VString partIDKeys)
    {
        JDFAttributeMap m=new JDFAttributeMap(m_in);
        VElement vReturn = new VElement();
        removeImplicitPartions(m);
        if (partUsage == null)
        {
            partUsage = getPartUsage();
        }

        if (m.isEmpty())
        {
            vReturn.add(this);
            return vReturn;
        }

        int msiz = m.size();
        if (matchingDepth == -1) // first call - check validity of the map
        {
            matchingDepth = 0;
            JDFAttributeMap thisMap = getPartMap(partIDKeys);

            Iterator it = m.getKeyIterator();
            while (it.hasNext())// for(int i = 0; i < msiz; i++)
            {
                String strKey = (String) it.next();
                EnumPartIDKey partIDKey = EnumPartIDKey.getEnum(strKey);

                // check map and throw exception if bad
                if (partIDKey==null)
                {
                    throw new JDFException("GetPartIDKeyEnum: illegal key:" + strKey);
                }

                // check whether we are already in a leaf when initially calling
                String mMapValue = m.get(strKey);
                if (thisMap != null)
                {
                    String thisMapValue = thisMap.get(strKey);

                    if (thisMapValue != null && JDFPart.matchesPart(strKey, thisMapValue, mMapValue))
                    {
                        matchingDepth++;
                    }
                }
            }

            // check if we are incompatible from the start...
            if (!JDFPart.overlapPartMap(thisMap, m)) {
				return vReturn;
			}
        }

        // if we find an <Identical> element, we must clean up the map and merge in the values of
        // identical
        if (hasChildElement(ElementName.IDENTICAL, null))
        {
            JDFPart part = (JDFPart) getElement(ElementName.IDENTICAL, null, 0).getElement(ElementName.PART);
            if (part != null)
            {
                JDFAttributeMap identityMap = part.getPartMap();
                m.putAll(identityMap);
            }

            // the identity map is always complete from the root, we therefore can start searching
            // in the root
            return getResourceRoot().getDeepPartVector(m, partUsage, -1, partIDKeys);
        }

        if (msiz == matchingDepth)
        {
            vReturn.add(this);
            return vReturn;
        }

        String nodeName = getNodeName();
        // TODO cast not safe!
        JDFResource resourceElement = (JDFResource) getElement_KElement(nodeName, null, 0);

        if (resourceElement == null) // got a leaf or found no matching children
        {
            // 150802 RP removed different treatment for leaves and no matching elements
            if (partUsage.getValue() >= EnumPartUsage.Sparse.getValue()) // allow incomplete parts
            {
                vReturn.add(this);
            }

            return vReturn;
        }

        VElement toAppend = new VElement(); // we stick all recursively found candidates into this vector
        boolean hasBadChildren = false;     // loop over all valid elements and search downward
        boolean hasMatchingAttribute = false;
        boolean bSearchSame = true;         // boolean that controls whether to search the lower children.
        boolean bCompleteMatch = false;
        String matchingKey=null;
        String matchingValue=null;
        int nChildren = 0;
        int matchingKeyPos = -1;

        while (true)
        {
            boolean badChild = false;
            boolean bSnafu = false;

            if (matchingKeyPos >= 0) // all other elements except the first
                                     // may have a predefined matching key
            {
                String sTmp = resourceElement.getAttribute_KElement(matchingKey, null, null);
                if (sTmp != null) // found a matching key;
                {
                    if (sTmp.equals(matchingValue))
                    {
                        hasMatchingAttribute = true;
                    }
                    else
                    {
                        badChild = true;
                        hasBadChildren = true;
                    }
                }
                else
                {
                    bSnafu = true; // should never get here, but in case of a corrupt leaf structure,
                                   // it could happen and will be handled gracefully
                }
            }

            if ((nChildren++ == 0) || bSnafu) // must only search the first element, since only one key
            {                                 //  is allowed and all keys must be in the same sequence;
                                              // unless, of course, someone wrote crap JDF (bSnafu=true)
                Iterator it = m.getKeyIterator();
                int im = 0;
                while (it.hasNext())// for(int im = 0; im < msiz; im++)
                {
                    String strKey = (String) it.next();
                    String strValue = m.get(strKey);

                    String sTmp = resourceElement.getAttribute_KElement(strKey, null, null);

                    if (sTmp != null)// found a matching key;
                    {
                        if (JDFPart.matchesPart(strKey, sTmp, strValue))                       {
                            hasMatchingAttribute = true;
                        }
                        else
                        {
                            badChild = true;
                            hasBadChildren = true;
                        }

                        matchingKeyPos = im;
                        matchingKey = strKey;
                        matchingValue = strValue;
                        break;
                    }
                    im++;
                }
            }

            if (!badChild)
            {
                VElement dpv = resourceElement.getDeepPartVector(m, partUsage,
                        hasMatchingAttribute ? matchingDepth + 1 : matchingDepth, partIDKeys);

                if (dpv.size() > 0)
                {
                    toAppend.addAll(dpv);
                    bSearchSame = false; // not necessary since we have something deeper

                    if (hasMatchingAttribute && toAppend.size() == 1) // this is a potential complete match
                                                                      // - we may just stop
                    {
                        JDFResource root = getResourceRoot();
                        JDFElement closest = (JDFElement) toAppend.elementAt(0);
                        // move to root
                        int leafDepth = 0;

                        while (!closest.equals(root))
                        {
                            leafDepth++;
                            closest = (JDFElement) closest.getParentNode();
                        }

                        bCompleteMatch = (leafDepth == msiz);
                    }
                }
            }
            if (bCompleteMatch)
            {
                break; // nothing at all left to do; jump out of loop
            }

            KElement k = resourceElement.getNextSiblingElement(nodeName, JDFConstants.EMPTYSTRING);

            if (!(k instanceof JDFResource))
            {
                break;
            }

            resourceElement = (JDFResource) k;
        }

        // nothing complete was found, and we are at the end of the chain
        if (bSearchSame)
        {
            // check whether all sub elements of this are accepted completely
            int appSize = toAppend.size();
            boolean bSame = (!hasMatchingAttribute) && (nChildren == appSize);

            if (bSame)
            {
                // TODO cast not safe
                resourceElement = (JDFResource) getElement_KElement(nodeName,null, 0);

                for (int n = 0; n < appSize; n++)
                {
                    if (!resourceElement.equals(toAppend.elementAt(n)))
                    {
                        bSame = false;
                        break;
                    }

                    KElement k = resourceElement.getNextSiblingElement(nodeName,null);
                    if (!(k instanceof JDFResource))
                    {
                        break;
                    }
                    resourceElement = (JDFResource) k;
                }
            }

            if (!bSame) // something in the children warrants a closer look and we are at the end
            {
                // none match and this is the last with bad kids and we want incomplete stuff
                if (toAppend.isEmpty() && hasBadChildren
                        && (partUsage.equals(EnumPartUsage.Implicit))
                        && !hasMatchingAttribute)
                {
                    JDFResource root = getResourceRoot();
                    JDFResource closest = this;
                    boolean bClosest = false;

                    // move to root
                    while (!closest.equals(root))
                    {
                        // used for an explicit (non inherited) attribute check.
                        JDFElement closestElement = closest;

                        // check whether any parameters of map were found
                        Iterator it = m.getKeyIterator();
                        while (it.hasNext())// for(int i = 0; i < msiz; i++)
                        {
                            String strKey = (String) it.next();
                            if (closestElement.hasAttribute_KElement(strKey, null, false))
                            {
                                bClosest = true;
                                break;
                            }
                        }

                        // found one that has at least one attribute from m --> stop searching
                        if (bClosest)
                        {
                            break;
                        }

                        // this one is no closer than it's parent --> take the parent and retry
                        closest = (JDFResource) closest.getParentNode();
                    }
                    vReturn.add(closest);
                }
                else
                // this is the standard recursive case with no special handling
                {
                    vReturn.addAll(toAppend);
                }
            }
            else
            {
                // if all subelements were accepted, I am the correct root
                // note that this may recurse up closer to the root
                vReturn.add(this);
            }
        }
        else
        // this is the standard recursive case with no special handling
        {
            vReturn.addAll(toAppend);
        }

        return vReturn;
    }


    /**
     * Gets a matching part from somewhere down there returns the closest ancestor of all matching
     * elements within the target vector
     *
     * @param m
     * @param bIncomplete
     * @return JDFResource
     * @deprecated use the partUsage dependent version instead
     */
    public JDFResource getDeepPart(JDFAttributeMap m, boolean bIncomplete)
    {
        return getDeepPart(m,bIncomplete ? EnumPartUsage.Implicit : EnumPartUsage.Explicit);
    }


    /**
     * Gets a matching part from somewhere down there,<br>
     * returns the closest ancestor of all matching elements within the target vector
     * @param m          map of attributes that should fit
     * @param partUsage  lso accept nodes that are are not completely specified in the partmap,
     * e.g. if partitioned by run, RunPage and only Run is specified
     * @return the first found matching resource node or leaf
     */
    public JDFResource getDeepPart(JDFAttributeMap m, EnumPartUsage partUsage)
    {
        JDFResource retRes   = null;
        VString partIDKeys=getPartIDKeys();
        final VElement vRes = getDeepPartVector(m, partUsage, -1, partIDKeys);

        if (vRes != null)
        {
            final int siz = vRes.size();

            if (siz == 0)
            {
                // nothing fits at all
                return retRes;
            }
            else if (siz == 1)
            {   // only one, take it
                retRes = (JDFResource) vRes.elementAt(0);
            }
            else
            {   // multiple, get the closest ancestor
                JDFResource e = (JDFResource)vRes.elementAt(0);
                if(e.isResourceRoot()){
                    return e;
                }

                e = (JDFResource)e.getParentNode();
                do // if more than one, loop at leas once
                {
                    int i = 0;
                    for(i = siz-1; i > 0; i--) //e is always an ancestor of vElm[i];
                        //go backwards since the chance of mismatch is
                        //greater at the end of the list
                    {
                        if(!e.isAncestor((KElement)vRes.elementAt(i))) // found a misMatch
                        {
                            e = (JDFResource)e.getParentNode();
                            break;
                        }
                    }
                    if(i==0) // went through the entire loop with no mismatch --> heureka and ciao
                    {
                        retRes = e;
                        break; // while e!=this
                    }
                }while(e != this);

                if(e.isResourceRoot())
                {
                    return e; // the root is always ok
                }
            }
        }

        if(retRes==null) {
			return null;
		}

        int retSize=-1;
        JDFResource loopRes=retRes;
        Set vKeys=m.keySet();

        // loop unti we hit this or root, whichever is closer
        while(true){
            JDFAttributeMap returnMap=loopRes.getPartMap();
            // only check the keys, not the values since <Identical> elements may screw up the values...
            returnMap.reduceMap(vKeys);
            // we lost a key - break one prior to this
            if(returnMap.size()<retSize){
                return retRes; // the child of the tested element
            }else if(retSize==-1){ // first loop initialization
                retSize=returnMap.size();
            }
            // we hit the starting point - nothing left to do
            if((loopRes==this)||loopRes.isResourceRoot()) {
				return loopRes;
			}
            // no check failed - go one closer to the root
            retRes=loopRes;
            loopRes=(JDFResource) loopRes.getParentNode();
            if(loopRes==null) {
				throw(new JDFException("JDFResource::GetDeepPart ran into null element while searching tree"));
			}
        }
        // return retRes;
    }

    /**
     * Gets a list of all direct leaves
     *
     * @param bAll if true include all intermediate and leaf nodes including this<br>
     *          if false, include only the final leaves
     *
     * @return VElement - the vector of all leaves
     *
     * @default getLeaves(false)
     */
    public VElement getLeaves(boolean bAll)
    {
        // want possibly intermediate nodes, check the kids
        VElement vAllChildren = getChildElementVector_KElement(getNodeName(), null, null, true, 0);

        if (vAllChildren.isEmpty())
        {
            // got a leaf
            vAllChildren.addElement(this);
        }
        else
        {
            VElement vLeaves = new VElement();
            // recurse parts tree and sum up the results
            if (bAll)
            {
                vLeaves.add(this);
            }

            final int size = vAllChildren.size();
            for (int i = 0; i < size; i++)
            {
                final JDFResource pi = (JDFResource) vAllChildren.elementAt(i);
                final VElement v = pi.getLeaves(bAll);
                vLeaves.addAll(v);
            }

            vAllChildren = vLeaves;
        }

        return vAllChildren;
    }

    /**
     * Tests, whether 'this' is the end of a partition (i.e. there is no element with the same name directly below)
     *
     * @return boolean true, if 'this' is a leaf
     */
    public boolean isLeaf()
    {
        return getElement_JDFElement(getNodeName(), null, 0) == null;
    }

    /**
     * Gets a list of the values for attribute part type within the leaves
     *
     * @param partType the PartIDKey attribute name
     *
     * @return Vector - a list of values of the specifird partition key
     */
    public Vector getPartValues(EnumPartIDKey partType)
    {
        final VElement v = getLeaves(false);
        final Vector vs = new Vector();

        for (int i = 0; i < v.size(); i++)
        {
            final JDFResource p = (JDFResource) v.elementAt(i);
            final String s = p.getAttribute(partType.getName(), null, JDFConstants.EMPTYSTRING);

            if (s!=null && !s.equals(JDFConstants.EMPTYSTRING))
            {
                boolean bOK = true;
                for (int j = 0; j < vs.size() && bOK; j++)
                {
                    if (s.equals(vs.elementAt(j)))
                    {
                        bOK = false;
                    }
                }

                if (bOK)
                {
                    vs.addElement(s);
                }
            }
        }

        return vs;
    }

    /**
     * Gets an attribute value. Also follows partition parents to the resource root
     *
     * @param attrib       attribute name to get
     * @param nameSpaceURI namespace to search for
     * @param def          attribute default that is returned if no attribute exists
     *
     * @return WString - attribute value
     *
     * @default getAttribute(attrib, null, JDFConstants.EMPTYSTRING)
     */
    public String getAttribute(String attrib, String nameSpaceURI, String def)
    {
        String resultAttrib = super.getAttribute(attrib, nameSpaceURI, null);
        String nodeName=resultAttrib==null ? getNodeName() : null;
        KElement ke=this;
        while (resultAttrib == null)
        {
            ke = ke.getParentNode_KElement();
            if (ke == null || !ke.getNodeName().equals(nodeName))
            {
                return def;
            }
            resultAttrib = ke.getAttribute_KElement(attrib, nameSpaceURI, null);
        }

        return resultAttrib;
    }

  
    /**
     * Get the Attribute Map of the actual element also following inheritence
     *
     * @return JDFAttributeMap the attribute map of the actual element
     */
    public JDFAttributeMap getAttributeMap()
    {
        final KElement ke = getParentNode_KElement();
        JDFAttributeMap ret=null;
        if (ke != null && ke.getNodeName().equals(getNodeName()))
        {
            ret=((JDFResource)ke).getAttributeMap();
            ret.putAll(super.getAttributeMap());
        }
        else
        {
            ret=super.getAttributeMap();
        }
        return ret;
    }
    /**
     * Checks if the actual element has a specific attribute<br>
     * this version checks within the resource and its partitioned parent xml elements
     *
     * @param attrib the name of the attribute to look for
     * @param nameSpaceURI the nameSpace to look in
     * @param bInherit if true also check recursively in parent elements, regardless of partitioning
     *
     * @return boolean true, if the attribute is present
     *
     * @default hasAttribute(attrib, null, false)
     */
    public boolean hasAttribute(
            String attrib,
            String nameSpaceURI,
            boolean bInherit)
    {
        return hasAttribute_JDFResource(attrib, nameSpaceURI, bInherit);
    }

    /**
     * Checks if the actual element has a specific attribute<br>
     * this version checks within the resource and its partitioned parent xml elements
     * this was added in order to implement the c++ JDFResource::HasAttribute
     *
     * @param attrib       the name of the attribute to look for
     * @param nameSpaceURI the nameSpace to look in
     * @param bInherit     if true also check recursively in parent elements, regardless of partitioning
     *
     * @return boolean true, if the attribute is present
     *
     * @default hasAttribute_JDFResource(attrib, null, false)
     */
    private boolean hasAttribute_JDFResource(
            String attrib,
            String nameSpaceURI,
            boolean bInherit)
    {
        if (bInherit)
        {
            return getInheritedAttribute(attrib, nameSpaceURI, null)!=null;
        }
        return getAttribute(attrib, nameSpaceURI, null)!=null;
    }


    /**
     * The same as JDFElement.numChildElements but also follows References
     *
     * @param nodeName     the nodes to count
     * @param nameSpaceURI the nameSpace to look in
     * @return int - the number of child elements
     *
     * @default numChildElements(JDFConstants.EMPTYSTRING, null)
     */
    public int numChildElements(String nodeName, String nameSpaceURI)
    {
        int iNumChildElements = super.numChildElements(nodeName, nameSpaceURI);

        // elements do not override, i.e. if an element from a group exists, do not look below
        if (iNumChildElements == 0)
        {
            final JDFElement jdfRes = (JDFElement) getParentNode_KElement();

            if (jdfRes == null || !jdfRes.getNodeName().equals(getNodeName()))
            {
                iNumChildElements = 0;
            }
            else
            {
                if (jdfRes instanceof JDFResource)
                {
                    iNumChildElements = ((JDFResource)jdfRes).numChildElements(nodeName, nameSpaceURI);
                }
            }
        }

        return iNumChildElements;
    }

    /**
     * Recursive GetElement that also checks parent nodes up to the part root
     * this was added in order to implement the c++ JDFResource::GetCreateElement
     *
     * @param nodeName     name of the child node to get
     * @param nameSpaceURI namespace to search for
     * @param iSkip        get the iSkipth element that fits
     *
     * @return KElement - the matching element
     *
     * @default getCreateElement_JDFResource(nodeName, null, 0)
     */
    public KElement getCreateElement_JDFResource(
            String nodeName,
            String nameSpaceURI,
            int iSkip)
    {
        KElement resultKElement = getElement_JDFElement(nodeName, nameSpaceURI, iSkip);

        if (resultKElement == null)
        {
            // 250202 RP changed functionality to append in case the leaf
            // does not have it brand new for partitions!
            resultKElement = appendElement(nodeName, nameSpaceURI);
        }

        return resultKElement;
    }

    /**
     * same as KElement.getElement, but also follows references and searches parents
     *
     * @param nodeName     name of the child node to get
     * @param nameSpaceURI namespace to search for
     * @param iSkip        get the iSkipth element that fits
     *
     * @return KElement: the matching element
     *
     * default: getElement(nodeName, null, 0)
     */
    public KElement getElement(String nodeName, String nameSpaceURI, int iSkip)
    {
        return getElement_JDFResource(nodeName, nameSpaceURI, iSkip);
    }

    /**
     * same as KElement.getElement, but also follows references and searches parents<br>
     * this was added in order to implement the C++ JDFResource::GetElement
     *
     * @param nodeName     name of the child node to get
     * @param nameSpaceURI namespace to search for
     * @param iSkip        get the iSkipth element that fits
     *
     * @return KElement - the matching element
     *
     * @default getElement_JDFResource(nodeName, null, 0)
     */
    private KElement getElement_JDFResource(
            String nodeName,
            String nameSpaceURI,
            int iSkip)
    {
        KElement retEle = null;

        if (super.getElement(nodeName, nameSpaceURI, 0) != null)
        {
            retEle = super.getElement(nodeName, nameSpaceURI, iSkip);
        }
        else
        {
            final KElement parent = getParentNode_KElement();
            if (parent != null && parent.getNodeName().equals(getNodeName()))
            {
                if (!(parent instanceof JDFResource))
                {
                    throw new JDFException("getElement_JDFResource tried to" +
                    " return a JDFElement as a JDFResource");
                }

                retEle = ((JDFResource) parent).getElement_JDFResource(nodeName, nameSpaceURI, iSkip);
            }
        }
        return retEle;
    }

    /**
     * Creates parts of part type 'partType' with values as defined in 'values';
     * the number of values is defined by the number of elements in 'values'
     *
     * @param partType part type of a new part
     * @param values   its value
     *
     * @return VElement - vector of newly created parts
     */
    public VElement addPartitions(EnumPartIDKey partType, VString values)
    {
        if(isResourceElement())
        {
            throw new JDFException("Attempting to add partition to resource element: " + buildXPath(null,1));
        }

        final VElement v = new VElement();
        if (!hasAttribute(partType.getName(), null, false))
        {
            final VElement vLeaves = getLeaves(false);

            final int size = values.size();
            for (int i = 0; i < vLeaves.size(); i++)
            {
                for (int j = 0; j < size; j++)
                {
                    final JDFResource p = (JDFResource)vLeaves.elementAt(i);
                    v.add(p.addPartition(partType, values.stringAt(j)));
                }
            }
        }

        return v;
    }
    /**
     * Adds a new part to this node, also handles PartIDKeys in the root etc.
     * convenience method to allow for partIDKey enums rather than strings
     * @param partType part type of a new part
     * @param value    its value
     *
     * @return JDFResource - the newly created part
     */
    public JDFResource addPartition(EnumPartIDKey partType, ValuedEnum enumPart)
    {
        return addPartition(partType, enumPart.getName());
    }

    /**
     * Adds a new part to this node, also handles PartIDKeys in the root etc.
     *
     * @param partType part type of a new part
     * @param value    its value
     *
     * @return JDFResource - the newly created part
     */
    public JDFResource addPartition(EnumPartIDKey partType, String value)
    {
        if(isResourceElement())
        {
            throw new JDFException("Attempting to add partition to resource element: " + buildXPath(null,1));
        }
        if(partType==null)
        {
            throw new JDFException("Attempting to add null partition to resource: " + buildXPath(null,1));
        }


        VString vs=getPartIDKeys();
        int posOfType = vs==null ? -1 : vs.indexOf(partType.getName());
        if(posOfType<0)
        {
            if(!isLeaf()) {
				throw new JDFException("addPartion: adding inconsistent partition - parent must be a leaf");
			}
        }
        else if(posOfType==0)
        {
            if(!isResourceRootRoot()) {
				throw new JDFException("addPartion: adding inconsistent partition - must be root");
			}
        }
        else
        {
            final String parentPart=vs.stringAt(posOfType-1);
            if(!hasAttribute_KElement(parentPart,null,false)) {
				throw new JDFException("addPartion: adding inconsistent partition - parent must have partIDKey: "+parentPart);
			}
        }

        if (!hasAttribute(partType.getName(), null, false))
        {
            addPartIDKey(partType);
        }

        JDFResource p = getPartition(new JDFAttributeMap(partType, value),EnumPartUsage.Explicit);
        if(p!=null) {
			throw new JDFException("addPartion: adding duplicate partition "+partType+"="+value);
		}

        p=(JDFResource)appendElement(getNodeName(), getNamespaceURI());
        if (p != null)
        {
            p.setPartIDKey(partType, value);
        }

        return p;
    }


    /**
     * Gets all local attribute names as an vector of strings.<br>
     * Is called from KElement.getMissingAttributeVector() as a virtual method
     *
     * @return VString - the vector of attribute names
     */
    public VString getAttributeVector()
    {
        return getAttributeVector_JDFResource();
    }

    /**
     * Gets all local attribute names as an vector of strings
     *
     * @return VString the vector of attribute names
     */
    public VString getAttributeVector_JDFResource()
    {
        VString v = new VString();
        final KElement parent = getParentNode_KElement();

        v = super.getAttributeVector();
        if ((parent != null) &&parent.getNodeName().equals(getNodeName()))
        {
            final VString par = ((JDFResource) parent).getAttributeVector_JDFResource();
            for (int i = 0; i < par.size(); i++)
            {
                final String att = (String) par.elementAt(i);
                if (!v.contains(att))
                {
                    v.addElement(att);
                }
            }
        }
        return v;
    }

    /**
     * Gets the parent element that actually contains the attribute key in a partitioned resource
     *
     * @param key attribute key to look for
     * @return JDFResource - the parent element that actually contains the attribute key
     */
    public JDFResource getAttributePart(String key)
    {
        JDFResource result = null;

        if (super.hasAttribute(key, null, false))
        {
            result = this;
        }
        else
        {
            final JDFElement jdfRes = (JDFElement) getParentNode_KElement();
            if (jdfRes != null && jdfRes.getNodeName().equals(getNodeName()))
            {
                if (!(jdfRes instanceof JDFResource))
                {
                    throw new JDFException("getAttributePart tried to" +
                    " return a JDFElement as a JDFResource");
                }

                result = ((JDFResource) jdfRes).getAttributePart(key);
            }
        }

        return result;
    }

    /**
     * Gets a unique vector of resource leaf elements that actually contain the attribute key
     *
     * @param key attribute key to look for
     * @return VElement a vector of resource leaf elements that actually contain the attribute key
     */
    public VElement getAttributePartVector(String key)
    {
        final VElement leaves = getLeaves(false);
        final VElement v      = new VElement();

        for (int i = 0; i < leaves.size(); i++)
        {
            final JDFResource p = ((JDFResource)leaves.elementAt(i)).getAttributePart(key);

            if (p != null)
            {
                boolean bFound = false;

                for (int j = 0; j < v.size() && !bFound; j++)
                {
                    if (p.equals(v.elementAt(j)))
                    {
                        bFound = true;
                    }
                }

                if (!bFound)
                {
                    v.addElement(p);
                }
            }
        }

        return v;
    }

    /**
     * Gets the XPath full tree representation of 'this'
     * @param relativeTo  relative path to which to create an xpath
     * @param methCountSiblings, if 1 count siblings, i.e. add '[n]'
     *                           if 0, only specify the path of parents
     * @return String    the XPath representation of 'this' e.g.
     *                   <code>/root/parent/element</code><br>
     *                   <code>null</code> if parent of this is null
     *                   (e.g. called on rootnode)
     */
    public String buildXPath(String relativeTo, int methCountSiblings)
    {
        if((methCountSiblings!=2 && methCountSiblings!=3)|| isResourceElement() || isResourceRoot()) {
			return super.buildXPath(relativeTo, methCountSiblings);
		}

        String path = "/"+getLocalName(); // tbd handle namespaces
        String sKey = getLocalPartitionKey();
        if(sKey!=null)
        {
            path+="[@"+sKey+"=\""+getAttribute(sKey)+"\"]";
        }

        KElement parent=getParentNode_KElement();
        return parent.buildXPath(relativeTo, methCountSiblings)+path;
    }


    /**
     * get the local partition key of this leaf
     * @return the key, if one exists, null otherwise
     */
    public String getLocalPartitionKey()
    {
        VString partKeys=getPartIDKeys();
        String sKey=null;
        for(int i=0;i<partKeys.size();i++)
        {
            if(hasAttribute_KElement(partKeys.stringAt(i), null, false))
            {
                sKey=partKeys.stringAt(i);
                break;
            }
        }
        return sKey;
    }

    /**
     * remove any resource specific attribute when making this to an element
     */
    public void cleanResourceAttributes()
    {
        // clean up resource specific attributes
        removeAttribute(AttributeName.ID);
        removeAttribute(AttributeName.CLASS);
        removeAttribute(AttributeName.STATUS);
        removeAttribute(AttributeName.PARTUSAGE);
        removeAttribute(AttributeName.NOOP);
        VString v=getPartIDKeys();
        if(v!=null) {
			for(int i=0;i<v.size();i++) {
				removeAttribute(v.stringAt(i));
			}
		}
        removeAttribute(AttributeName.LOCKED);
        removeAttribute(AttributeName.PARTIDKEYS);
        removeAttribute(AttributeName.RREFS,null);
        removeAttribute(AttributeName.SPAWNIDS,null);
        removeAttribute(AttributeName.SPAWNSTATUS,null);

    }

   /**
     * Removes attributes
     *
     * @param attrib       the attribute key to remove
     * @param nameSpaceURI the attribute nameSpaceURI to remove
     *
     * @default removeAttribute(attrib, null)
     */
    public void removeAttribute(String attrib, String nameSpaceURI)
    {
        if (super.hasAttribute(attrib, nameSpaceURI, false))
        {
            if ((nameSpaceURI==null)||nameSpaceURI.equals(JDFConstants.EMPTYSTRING))
            {
                removeAttribute(attrib);
            }
            else
            {
                removeAttributeNS(nameSpaceURI, attrib);
            }
        }
    }
    /**
     * Removes attributes, also removes overwrites in any child parts
     *
     * @param attrib       the attribute key to remove
     * @param nameSpaceURI the attribute nameSpaceURI to remove
     *
     * @default removeAttribute(attrib, null)
     */
    public void removeAttributeFromLeaves(String attrib, String nameSpaceURI)
    {
        VElement v=getLeaves(true);
        v.removeAttribute(attrib,nameSpaceURI);
    }

    /**
     * Reduces partition so that only the parts that overlap with vResources remain
     *
     * @param vValidParts vector of partmaps that define the individual valid parts.<br>
     *        The individual PartMaps are ored to define the final resource.
     */
    public void reducePartitions(VJDFAttributeMap vValidParts)
    {
        final int size = vValidParts==null ? 0 : vValidParts.size();
        if (size != 0 && getPartIDKeys().size() > 0)
        {
            boolean bBadParents = false;
            final VElement leaves    = getLeaves(false);

            // loop over all leaves of this resource
            for (int i = 0; i < leaves.size(); i++)
            {
                boolean bOK = false;
                final JDFResource leaf = (JDFResource) leaves.elementAt(i);
                final JDFAttributeMap leafMap = leaf.getPartMap();

                for (int j = 0; j < size && !bOK; j++)
                {
                    // the partition of this resource is included in the part vector --> keep it
                    if (leafMap.overlapMap(vValidParts.elementAt(j)))
                    {
                        bOK = true;
                    }
                }

                if (!bOK)
                {   // don't keep this leaf
                    leaf.deleteNode();
                    bBadParents = true;
                }
            }

            // I removed some, lets see if some of the parents were also bad
            if (bBadParents)
            {
                reducePartitions(vValidParts);
            }
        }
    }

    /**
     * reduceParts
     *
     * @param vParts
     * @deprecated [BLD009] not in C++ anymore, not used internally here
     */
    public void reduceParts(Vector vParts)
    {
        if (!vParts.isEmpty() && getPartIDKeys().size() > 0)
        {
            final VElement leaves = getLeaves(false);
            final String nodeName = getNodeName();

            for (int i = 0; i < leaves.size(); i++)
            {
                boolean bOK = false;
                JDFResource leaf = (JDFResource) leaves.elementAt(i);
                final JDFAttributeMap leafMap = leaf.getPartMap();

                for (int j = 0; j < vParts.size() && !bOK; j++)
                {
                    if (leafMap.subMap((JDFAttributeMap) vParts.elementAt(j)))
                    {
                        bOK = true;
                    }
                }

                if (!bOK)
                {
                    KElement parent = leaf.getParentNode_KElement();

                    if (parent != null)
                    {
                        boolean bBreakWhile = false;
                        JDFResource parentNode = (JDFResource) parent;

                        while (nodeName.equals(parentNode.getNodeName()) && !bBreakWhile)
                        {
                            // still in the resource
                            if (parentNode.numChildElements(nodeName, null) == 1)
                            {
                                // it only has a leaf, which is invalid,
                                // thus the intermediate node is also invalid
                                leaf = parentNode;
                                parent = parentNode.getParentNode_KElement();

                                if (parent == null)
                                {
                                    bBreakWhile = true;
                                }

                                parentNode = (JDFResource) parent;
                            }
                            else
                            {
                                bBreakWhile = true;
                            }
                        }

                        leaf.deleteNode();
                    }
                }
            }
        }
    }

    /**
     * Gets a map of all partition key-value pairs for this leaf / node.
     * This includes a recursion to the part root.
     * @param ids
     * @return JDFAttributeMap - the part attribute map for 'this' leaf / node
     */
    public JDFAttributeMap getPartMap(VString ids)
    {
        final JDFAttributeMap m = new JDFAttributeMap();

        final Iterator idsIterator = ids.iterator();
        while (idsIterator.hasNext()) {
			String id = (String) idsIterator.next();
			final String atr = getAttribute(id, null, null);
            if (atr != null) {
                m.put(id, atr);
            }
        }

        return m;
    }


    /**
     * Gets a map of all partition key-value pairs for this leaf / node this includes a recursion to
     * the part root;
     *
     * @return JDFAttributeMap - the part attribute map for 'this' leaf / node
     */
    public JDFAttributeMap getPartMap()
    {
        return getPartMap(getPartIDKeys());
    }

    /**
     * Gets nodename of a ResourceLink that links to 'this'
     *
     * @return String - name of a link to 'this'
     */
    public String getLinkString()
    {
        return getNodeName() + JDFConstants.LINK;
    }


    /**
     * Merges the spawnIDs of the various partitions <br>
     * also updates SpawnStatus, if necessary <br>
     * this routine is needed to correctly handle nested spawning and merging
     *
     * @param resToMerge       the resource with potentially new spawnIDs
     * @param previousMergeIDs vector of already merged spawnIDs that may still be in a partition
     */
    public void mergeSpawnIDs(JDFResource resToMerge, VString previousMergeIDs)
    {
        if (!getID().equals(resToMerge.getID()))
        {
            throw new JDFException(
                    "JDFResource.mergeSpawnIDs  merging incompatible resources ID = "
                    + getID()
                    + " IDMerge = "
                    + resToMerge.getID());
        }

        final VElement allNodes = getLeaves(true);

        for (int i = 0; i < allNodes.size(); i++)
        {
            final JDFResource thisResNode = (JDFResource) allNodes.elementAt(i);
            final JDFResource mergeResNode =
                resToMerge.getPartition(thisResNode.getPartMap(), EnumPartUsage.Explicit);

            if (mergeResNode != null)
            {
                final VString vPartIDKeys = thisResNode.getSpawnIDs(false);
                final int siz = vPartIDKeys.size();
                vPartIDKeys.appendUnique(mergeResNode.getSpawnIDs(false));
                vPartIDKeys.removeStrings(previousMergeIDs,999999);

                if (vPartIDKeys.isEmpty())
                {
                    thisResNode.removeAttribute(AttributeName.SPAWNIDS);
                    thisResNode.removeAttribute(AttributeName.SPAWNSTATUS);
                }
                else
                {
                    // AppendUnique modified the vector
                    if (siz != vPartIDKeys.size())
                    {
                        thisResNode.setSpawnIDs(vPartIDKeys);

                        // one of the spawnstatus elements was rw, must also be valid here
                        if (mergeResNode.getSpawnStatus()
                                == EnumSpawnStatus.SpawnedRW)
                        {
                            thisResNode.setSpawnStatus(EnumSpawnStatus.SpawnedRW);
                        }
                    }
                }
            }
        }
    }



    /**
     * Expand so that each leaf is complete (except for ID)
     *
     * @param bDeleteFromNode removes all intermediate elements and attributes
     *
     * @default expand(false)
     */
    public void expand(boolean bDeleteFromNode)
    {
        final VElement leaves = getLeaves(false);
        if(leaves.size()==1 && leaves.elementAt(0)==this) {
			return; // this is a non partitioned root node
		}

        final VString parts = getRootPartAtts();

        final int leafSize = leaves.size();

        for (int i = 0; i < leafSize; i++)
        {
            final JDFResource leaf = (JDFResource) leaves.elementAt(i);
            final VString atts = new VString(leaf.getAttributeVector_JDFResource());
            int j = 0;

            final int attSize = atts.size();
            for (j = 0; j < attSize; j++)
            {
                final String aj = atts.stringAt(j);
                if (!parts.contains(aj))
                {
                    leaf.setAttribute(aj, leaf.getAttribute(aj, null, null), null);
                }
            }

            // expand sub-elements - since 190602
            final VElement vElm = leaf.getChildElementVector(null, null, null, true, 0, false);
            for (j = 0; j < vElm.size(); j++)
            {
                final String nodeName = ((KElement) vElm.elementAt(j)).getNodeName();
                // copy non existing element to leaf
                if (leaf.getElement_JDFElement(nodeName, null,0)==null)
                {
                    final VElement vCopy = leaf.getChildElementVector(nodeName, null, null, true, 0, false);

                    final int copySize = vCopy.size();
                    for (int k = 0; k < copySize; k++)
                    {
                        leaf.copyElement((KElement) vCopy.elementAt(k), null);
                    }
                }
            }
        }

        if (bDeleteFromNode)
        {
            final String nodeName = getNodeName();

            for (int i = 0; i < leafSize; i++)
            {
                final JDFResource res = (JDFResource) leaves.elementAt(i);
                JDFElement  r   = (JDFElement) res.getParentNode_KElement();

                while (r != null && r.getNodeName().equals(nodeName))
                {
                    final VString atts = new VString(r.getAttributeVector());
                    int j;
                    for (j = 0; j < atts.size(); j++)
                    {
                        final String aj = atts.stringAt(j);
                        if (!parts.contains(aj))
                        {
                            r.removeAttribute(aj, null);
                        }
                    }

                    // delete all intermediate elements
                    final VElement vElm = r.getChildElementVector_JDFElement(null,null, null, true, 0, false);
                    for (j = 0; j < vElm.size(); j++)
                    {
                        if (!((KElement) vElm.elementAt(j)).getNodeName().equals(nodeName))
                        {
                            ((KElement) vElm.elementAt(j)).deleteNode();
                        }
                    }

                    if(r==this) {
						break;
					}

                    r = (JDFElement) r.getParentNode_KElement();
                }
            }
        }
    }


    /**
     * get the list of attributes that are administrative only
     * @return the VString that lists all adminstrative and partition keys
     */
    public VString getRootPartAtts()
    {
        final VString parts = getPartIDKeys();
        parts.addElement(AttributeName.ID);
        parts.addElement(AttributeName.CLASS);
        parts.addElement(AttributeName.PARTIDKEYS);
        parts.addElement(AttributeName.AGENTNAME);
        parts.addElement(AttributeName.AGENTVERSION);
        parts.addElement(AttributeName.AUTHOR);
        parts.addElement(AttributeName.PARTUSAGE);
        return parts;
    }


    /**
     * collapse all redundant attributes and elements
     *
     * @param bCollapseToNode only collapse redundant attriutes and elements that pre-exist in the nodes
     *
     * @default Collapse(false)
     */
    public void collapse(boolean bCollapseToNode)
    {
        final VElement leaves = getLeaves(false);
        if(leaves.size()==1 && leaves.elementAt(0)==this) {
			return; // this is a non partitioned root node
		}

        final VString parts = getRootPartAtts();
        for (int i = 0; i < leaves.size(); i++)
        {
            JDFResource leaf = (JDFResource) leaves.elementAt(i);
            final VString atts =leaf.getAttributeVector_JDFResource();
            atts.removeStrings(parts, Integer.MAX_VALUE);
            JDFResource parent = (JDFResource) leaf.getParentNode_KElement();

            while (true)
            {
                final VElement localLeaves = parent.getChildElementVector_JDFElement(getNodeName(), null, null, true, 0, false);
                collapseAttributes(bCollapseToNode, leaf, atts, parent, localLeaves);
                // since 190602 also collapse elements
                collapseElements(bCollapseToNode, leaf, parent, localLeaves);
                if(parent.isResourceRoot()||parent==this) {
					break;
				}

                leaf = parent;
                parent = (JDFResource) parent.getParentNode_KElement();

            }
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void collapseAttributes(boolean bCollapseToNode, JDFResource leaf, final VString atts, JDFResource parent, final VElement localLeaves)
    {
        final int localSize = localLeaves.size();
        for (int j = 0; j < atts.size(); j++)
        {
            final String att = atts.stringAt(j);
            // reduce lower stuff
            if ((!bCollapseToNode) && (!parent.hasAttribute_KElement(att, null, false)))
            {
                final String attVal = leaf.getAttribute_KElement(att, null, JDFConstants.EMPTYSTRING);
                //Matt-Start
                if (!parent.getAttribute_KElement(att).equals(attVal))
                    //Matt-End
                {
                    // check all local children and grandchildren
                    boolean bAllSame = true;
                    for (int l = 0; l < localSize; l++)
                    {
                        if (!((KElement) localLeaves.elementAt(l))
                                .getAttribute(att, null, JDFConstants.EMPTYSTRING)
                                .equals(attVal))
                        {
                            bAllSame = false;
                            break;
                        }
                    }
                    //Matt-Start
                    if (bAllSame)
                    {
                        parent.setAttribute(att, attVal, null);
                        // remove from all leaves...
                        for (int l = 0; l < localSize; l++)
                        {
                            ((JDFElement)localLeaves.elementAt(l)).removeAttribute(att);
                        }

                    }
                }
            }
            // remove leaf element attribute if it is defined lower in the tree
            String parentAttribute = parent.getAttribute(att, null, null);
            if (parentAttribute!=null && parentAttribute.equals(leaf.getAttribute_KElement(att, null,null )))
            {
                leaf.removeAttribute(att, null);
            }
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void collapseElements(boolean bCollapseToNode, JDFResource leaf, JDFResource parent, final VElement localLeaves)
    {
        final int localSize = localLeaves.size();
        final VElement vElm = leaf.getChildElementVector_JDFElement(null, null, null, true, 0, false);
        for (int j = 0; j < vElm.size(); j++)
        {
            final String nodeName = ((KElement) vElm.elementAt(j)).getNodeName();
            final VElement vParentElm = parent.getChildElementVector(nodeName, null,null, true, 0, false);
            final VElement vLocalElm = leaf.getChildElementVector_JDFElement(nodeName, null, null, true, 0, false);
            // vector of elements for the first leaf
            // this is reused for comparison since all leaves must be equal
            final VElement localNamedElements0 = ((KElement) localLeaves.elementAt(0)).getChildElementVector(nodeName, null, null, true, 0,false);

            final int elm0Size = localNamedElements0.size();
            // true if all elements of all local leaves are equal and in the correct sequence
            // if elm0size==0 we have nothing to do - leave loop
            boolean bElmEqual = elm0Size > 0;
            // only collapse if pre-existing elements exist in the nodes
            if (bCollapseToNode && bElmEqual)
            {
                if (elm0Size == vParentElm.size())
                {
                    // loop over all elements of leaf 0 and compare with the parent leaf
                    for (int kk = 0; kk < elm0Size; kk++)
                    {
                        final KElement kelem1 = (KElement) localNamedElements0.elementAt(kk);
                        final KElement kelem2 = (KElement) vParentElm.elementAt(kk);
                        if (!kelem1.isEqual(kelem2))
                        {
                            bElmEqual = false;
                            break;
                        }
                    }
                }
                else
                {
                    bElmEqual = false;
                }
            }
            if (bElmEqual)
            {
                // loop over all local leaves except 0 (which is the one we compare to)
                for (int k = 1; k < localSize; k++)
                {
                    // vector of elements for leaf k.
                    final VElement localNamedElements = ((KElement) localLeaves.elementAt(k)).getChildElementVector(nodeName, null, null, true, 0,false);
                    // not equal if a different number of elements exists
                    if (localNamedElements.size() != elm0Size)
                    {
                        bElmEqual = false;
                        break;
                    }
                    // the number of elements is identical, now compare each one individually
                    // note that the sequence is important and
                    // thus we don't have to check ordering permutations
                    for (int kk = 0; kk < elm0Size; kk++)
                    {
                        if (!((KElement) localNamedElements0.elementAt(kk))
                                .isEqual((KElement) localNamedElements.elementAt(kk)))
                        {
                            bElmEqual = false;
                            break;
                        }
                    }
                    // rebreak if not equal
                    if (!bElmEqual)
                    {
                        break;
                    }
                }
            }
            // all are identical --> zapp em
            if (bElmEqual)
            {
                // delete all intermediate children before copying
                if (!bCollapseToNode)
                {
                    parent.removeChildren(nodeName, null, null);
                    for (int k = 0; k < elm0Size; k++)
                    {
                        parent.copyElement((KElement) localNamedElements0.elementAt(k), null);
                    }
                }
                for (int kk = 0; kk < localSize; kk++)
                {
                    ((KElement) localLeaves.elementAt(kk)).removeChildren(nodeName, null, null);
                }
                // not all children are equal, but maybe this one individual; if so -> ciao
            }
            else if (vParentElm.size() == vLocalElm.size())
            {
                boolean bZappEm = vParentElm.size() > 0;
                for (int k = 0; k < vParentElm.size(); k++)
                {
                    if (!((KElement) vParentElm.elementAt(k))
                            .isEqual((KElement) vLocalElm.elementAt(k)))
                    {
                        bZappEm = false;
                        break;
                    }
                }
                // this leaves elements are all identical and in the same sequence; we can inherit so zapp em
                if (bZappEm)
                {
                    leaf.removeChildren(nodeName, null, null);
                }
            }
        }
    }


    /**
     * Spawns a given partition for a given SpawnID
     *
     * @param spawnID     the SpawnID that it was spawned with
     * @param spawnStatus SpawnStatus to spawn this resource with
     * @param vParts      vector of partitions that it was spawned with
     * @param bStayInMain if true, the function is applied to the main JDF, else to the spawned JDF
     * @deprecated use JDFSpawn.spawnPart
     */
    public void spawnPart(
            String spawnID,
            EnumSpawnStatus spawnStatus,
            VJDFAttributeMap vParts,
            boolean bStayInMain)
    {
        if (vParts!=null && vParts.size() > 0)
        {
            final int size = vParts.size();
            // loop over all part maps to get best matching resource
            for (int j = 0; j < size; j++)
            {
                final JDFResource pLeaf = getPartition(vParts.elementAt(j), null);
                if (pLeaf != null)
                {
                    // set the lock of the leaf to true if it is RO, else unlock it
                    if (bStayInMain)
                    {
                        if( (spawnStatus==EnumSpawnStatus.SpawnedRW)
                                || (pLeaf.getSpawnStatus()!=EnumSpawnStatus.SpawnedRW))
                        {
                            pLeaf.setSpawnStatus(spawnStatus);
                            pLeaf.setLocked(spawnStatus == EnumSpawnStatus.SpawnedRW);
                        }
                    }
                    else
                    {
                        pLeaf.setLocked(spawnStatus != EnumSpawnStatus.SpawnedRW);
                    }

                    pLeaf.appendSpawnIDs(spawnID);
                }
            }
        }
        else
        {
            if (bStayInMain)
            {
                if( (spawnStatus==EnumSpawnStatus.SpawnedRW)
                        || (getSpawnStatus()!=EnumSpawnStatus.SpawnedRW))
                {
                    setSpawnStatus(spawnStatus);
                    setLocked(spawnStatus == EnumSpawnStatus.SpawnedRW);
                }
            }
            else
            {
                setLocked(spawnStatus != EnumSpawnStatus.SpawnedRW);
            }

            appendSpawnIDs(spawnID);
        }
    }

    /**
     * Find the appropriate partition for a given SpawnID and undo the spawn procedure
     *
     * @param spawnID       the SpawnID that it was spawned with
     * @param spawnStatus   SpawnStatus this resource was spawned with
     */
    public void unSpawnPart(String spawnID, EnumSpawnStatus spawnStatus)
    {
        final VElement vLeaves = getNodesWithSpawnID(spawnID);
        for (int i = 0; i < vLeaves.size(); i++)
        {
            final JDFResource leaf = (JDFResource) vLeaves.elementAt(i);

            leaf.removeFromSpawnIDs(spawnID);
            if (spawnStatus == EnumSpawnStatus.SpawnedRW)
            {
                leaf.removeAttribute(AttributeName.LOCKED, null);
            }

            if (!leaf.hasAttribute(AttributeName.SPAWNIDS, null, false))
            {
                leaf.removeAttribute(AttributeName.SPAWNSTATUS, null);
            }
            else if (spawnStatus == EnumSpawnStatus.SpawnedRW)
            {
                // we've removed the one and only rw, it can only be ro if anything is still left
                leaf.setSpawnStatus(EnumSpawnStatus.SpawnedRO);
            }
        }
    }
    /**
     * Gets of 'this' all leaves and intermediate nodes that have an explicit spawnID set
     *
     * @param spawnID the spawnID to look for
     * @return VElement - the vector of nodes or leaves of 'this' that contain spawnID
     */
    public VElement getNodesWithSpawnID(String spawnID)
    {
        final VElement v = new VElement(
                getChildrenByTagName(getNodeName(), null, new JDFAttributeMap(AttributeName.SPAWNIDS,(String) null),
                        false, true, 0));

        v.addElement(this);

        for (int i = v.size() - 1; i >= 0; i--)
        {
            final JDFElement e = (JDFElement) v.elementAt(i);
            if (!e.includesMatchingAttribute(AttributeName.SPAWNIDS, spawnID, AttributeInfo.EnumAttributeType.NMTOKENS))
            {
                v.remove(i);
            }
        }

        return v;
    }

    /**
     * Gets the vector of parts (resource leaves or nodes) that match mAttribute
     *
     * @param m           the map of key-value partitions (where key - PartIDKey, value - its value)
     * @param bIncomplete if true, also accept nodes that are are not completely specified in the partmap,<br>
     *  e.g. if partitioned by run, RunPage and only Run is specified
     *
     * @return VElement - the vector of matching resource leaves or nodes
     * @deprecated use getPartitionVector(JDFAttributeMap m, EnumPartUsage partUsage)
     *
     * @default getPartitionVector(m, true)
     */
    public VElement getPartitionVector(JDFAttributeMap m, boolean bIncomplete)
    {
        return getDeepPartVector(m, bIncomplete ? EnumPartUsage.Implicit : EnumPartUsage.Explicit , 0, getPartIDKeys());
    }

    /**
     * Gets the vector of parts (resource leaves or nodes) that match mAttribute
     *
     * @param m           the map of key-value partitions (where key - PartIDKey, value - its value)
     * @param partUsage   also accept nodes that are are not completely specified in the partmap,
     *  e.g. if partitioned by run, RunPage and only Run is specified
     *
     * @return VElement - the vector of matching resource leaves or nodes
     *
     * @default getPartitionVector(m, null)
     */
    public VElement getPartitionVector(JDFAttributeMap m, EnumPartUsage partUsage)
    {
        return getDeepPartVector(m, partUsage , -1, getPartIDKeys());
    }

    /**
     * Gets the vector of parts that matches specified key-value pair
     *
     * @param key   the PartIDKey attribute name
     * @param value the string value of the partition key
     *
     * @return VElement - the vector matching resource leaves or nodes
     * @deprecated use getPartitionVector(JDFAttributeMap m, EnumPartUsage partUsage)
     * @default getPartitionVector(key, value, true)
     */
    public VElement getPartitionVector(
            EnumPartIDKey key,
            String value,
            boolean bIncomplete)
    {
        final JDFAttributeMap mp = new JDFAttributeMap(key.getName(), value);
        return getPartitionVector(mp, bIncomplete);
    }

    /**
     * gets a prefix for ID creation for the element
     *
     * @return String - a prefix for ID creation
     */
    protected String getIDPrefix()
    {
        return "r";
    }

    /**
     * Gets a vector of maps of all partition attribute key-value pairs for this node and all its children
     *
     * @param bIntermediate     if true also includes intermediate nodes including this
     * @return VJDFAttributeMap - the vector of partition attribute maps for this leaf / node and all its children
     *
     * @default getPartMapVector(false)
     */
    public VJDFAttributeMap getPartMapVector(boolean bIntermediate)
    {
        final VElement allNodes = getLeaves(bIntermediate);
        final VJDFAttributeMap vReturn = new VJDFAttributeMap();
        final VString ids = getPartIDKeys();

        for (int j = 0; j < allNodes.size(); j++)
        {
            final JDFAttributeMap m = new JDFAttributeMap();
            final JDFResource r = (JDFResource) allNodes.elementAt(j);

            for (int i = 0; i < ids.size(); i++)
            {
                final String strIds = (String) ids.elementAt(i);

                if (r.hasAttribute(strIds, null, false))
                {
                    m.put(strIds, r.getAttribute(strIds, null, JDFConstants.EMPTYSTRING));
                }
            }
            vReturn.appendUnique(m);
        }

        return vReturn;
    }

    /**
     * Finds the canonical vector of parts that defines the vector of parts that fits to vParts.
     * If all children of a parent node are in vParts, they are replaced by their parent. <br>
     * for example the canonical vector of all leaves is the root
     *
     * @param vParts the vector of parts to check against 'this'
     * @return VJDFAttributeMap the canonical vector
     */
    public VJDFAttributeMap reducePartVector(VJDFAttributeMap vParts)
    {
        final VJDFAttributeMap vTest = new VJDFAttributeMap();
        vTest.setVector(vParts.getVector());

        // reduce vParts internally
        for (int i = 0; i < vTest.size(); i++)
        {
            final JDFAttributeMap partMapi = vTest.elementAt(i);
            for (int j = vTest.size() - 1; j > i; j--)
            {
                final JDFAttributeMap partMapj = vTest.elementAt(j);
                if (partMapj.subMap(partMapi))
                {
                    vTest.removeElementAt(j);
                }
                else if (partMapi.subMap(partMapj))
                {
                    vTest.removeElementAt(i);
                    i--; // we erased x(i) and now have to undo i++ of the loop
                    break;
                }
            }
        }
        // this loop allows for arbitrary ordering of the incoming maps and handles side effects
        while (true)
        {
            boolean bChanged;
            bChanged = false;

            // loop over all partitions of the vector
            for (int i = 0; i < vTest.size(); i++)
            {
                final JDFAttributeMap partMapi = vTest.elementAt(i);
                final JDFResource r = getPartition(partMapi, false);

                if (r == null)
                { // this partition does not exist; remove it
                    vTest.removeElementAt(i);
                    i--;
                    // we erased i which move i+1 to i which has to be checked
                    continue;
                }
                // if the root is included, all others are by defult also included
                if (r.isResourceRoot())
                {
                    vTest.clear();
                    vTest.appendUnique(new JDFAttributeMap());
                    return vTest;
                }

                // check whether all children of parent are included in vTest
                final JDFElement parentElm = (JDFElement) r.getParentNode_KElement();

                if (parentElm != null)
                {
                    // must be element, since the resource version of getChildElementVector skips partition nodes
                    final VElement vKids =
                        new VElement(
                                parentElm.getChildElementVector(
                                        getNodeName(),
                                        null,null, true, 0, false));

                    // remember idix of vtmp Vector of Integer (object type, not the simple datatype)
                    final Vector vTmp = new Vector();

                    for (int j = 0; j < vKids.size(); j++)
                    {
                        final JDFAttributeMap kidMap = ((JDFResource)vKids.elementAt(j)).getPartMap();
                        final int index = vTest.indexOf(kidMap);
                        if (index >= 0)
                        {
                            vTmp.add(new Integer(index));
                        }
                        else
                        {
                            // we found a child in the resource that is not in vTest, --> we cannot consolidate
                            vTmp.clear();
                            break;
                        }
                    }

                    // all children are accounted for; replace them with parent
                    if (!vTmp.isEmpty())
                    {
                        // we have to sort and go backwards; otherwise we invalidate the indices in vTmp
                        for (int l = vTmp.size() - 1; l >= 0; l--)
                        {
                            int mymax = -1;
                            int posMax = -1;
                            for (int kk = 0; kk < vTmp.size(); kk++)
                            {
                                if (((Integer) vTmp.elementAt(kk)).intValue() > mymax)
                                {
                                    mymax = ((Integer) vTmp.elementAt(kk)).intValue();
                                    posMax = kk;
                                }
                            }

                            // remove all kids
                            vTest.removeElementAt(mymax);
                            vTmp.removeElementAt(posMax);
                        }

                        // add parent
                        final JDFResource parent = (JDFResource) parentElm;
                        vTest.appendUnique(parent.getPartMap());

                        // we modified the vector and should recheck
                        bChanged = true;
                    }

                }
            }

            // we found nothing this time; done
            if (!bChanged)
            {
                break;
            }
        }
        return vTest;
    }


    /**
     * Generates the id of a modified resource
     *
     * @return String the new id
     *
     * @throws JDFException if there are too many equivalent modified resources
     */
    public String newModifiedID()
    {
        final String id          = getID();
        if(id.length()<9) {
			return id+"_old_001";
		}

        final String postFix     = id.substring(0, 8);
        final Vector sibling     = getResourcePool().getResIds();
        String preFix            = id;
        final VString siblingIDs = new VString(sibling);

        if (postFix.substring(0, "_old_".length()).equals("_old_"))
        {
            preFix = id.substring(0, id.length() -8);
        }

        final int siz              = siblingIDs.size();
        String buf           = JDFConstants.EMPTYSTRING;
        boolean bTooManyIDs   = true;
        String newModifiedID = JDFConstants.EMPTYSTRING;

        for (int i = 1; i < 1000 && bTooManyIDs; i++)
        {
            //sprintf(buf, "%.3i", i);
            buf = makeID("_old_", 3, i);

            newModifiedID = preFix + buf;
            boolean bFound = false;

            for (int j = 0; j < siz && !bFound; j++)
            {
                if (newModifiedID.equals(siblingIDs.elementAt(j)))
                {
                    bFound = true;
                }
            }

            if (!bFound)
            {
                bTooManyIDs = false;
            }
        }

        if (bTooManyIDs)
        {
            throw new JDFException(
                    " JDFResource.newModifiedID too many " +
                    "equivalent modified resources! Resource ID = " + id);
        }

        return newModifiedID;
    }

    /**
     * patch value on the left with 0 up to numberOfDigits and concatenate it to s
     *
     * @param s text         part of ID
     * @param numberOfDigits length of number part of ID
     * @param value          number part of ID
     * @return String ID
     *
     * @throws JDFException if numberOfValueDigits > numberOfDigits
     */
    private String makeID(String s, int numberOfDigits, int value)
    {
        String result = s;
        final Integer myValue = new Integer(value);

        final int numberOfValueDigits = myValue.toString().length();

        if (numberOfValueDigits > numberOfDigits)
        {
            throw new JDFException("Value is bigger then maxDiggits: Cant make String");
        }

        for (int i = 0; i < numberOfDigits - numberOfValueDigits; i++)
        {
            result += "0";
        }

        result += myValue.toString();

        return result;
    }



    /**
     * Gets the resourcepool that 'this' lives in
     *
     * @return JDFResourcePool the ResourcePool where 'this' lives<br>
     */
    public JDFResourcePool getResourcePool()
    {
        return (JDFResourcePool) getDeepParent(ElementName.RESOURCEPOOL, 0);
    }

    /**
     * Tests, whether the first ancestor of 'this' is in ValidParentNodeNames - must be one of:
     * DropItemIntent,CustomerInfo,NodeInfo,ResourcePool,PipeParams,ResourceInfo,ResourceCmdParams
     *
     * @return boolean true, if 'this' is a root resource
     */
    public boolean isResourceRoot()
    {
        KElement parentNode=this.getParentNode_KElement();
        if(parentNode==null) {
			return true;
		}
        if(parentNode.getNodeName().equals(getNodeName())) {
			return false;
		}

        // special handling for NI and CI as resources
        final String locName = parentNode.getLocalName();
        if(locName.equals(ElementName.NODEINFO)||locName.equals(ElementName.CUSTOMERINFO))
        {
            if (getResourcePool()!=null) {
				return false;
			}
        }

        return StringUtil.hasToken(validParentNodeNames(), locName, 0);
    }


    /**
     * Tests, whether the first ancestor of 'this' is in validRootParentNodeNames() <br>
     * must be one of: ResourcePool,PipeParams,ResourceInfo,ResourceCmdParams
     *
     * @return boolean - true if this lives as a root resource in the ResourcePool
     */
    public boolean isResourceRootRoot()
    {
        KElement parentNode=this.getParentNode_KElement();
        if(parentNode==null) {
			return true;
		}

        if(parentNode.getNodeName().equals(getNodeName())) {
			return false;
		}

        final String locName = parentNode.getLocalName();
        return StringUtil.hasToken(validRootParentNodeNames(), locName, 0);
    }


    /**
     * update the amount of a resource based on the connected resource links
     *
     * @param previousAmount the previous resource Amount
     */
    public void updateAmounts(double previousAmount)
    {
        double amount = getAmount();
        double amountProduced = 0;
        double amountRequired = 0;
        double deltaAmount = previousAmount;

        final JDFAttributeMap partMap = getPartMap();

        if (previousAmount < 0)
        {
            deltaAmount = getAmount();
        }

        // if no output resourcelinks exist, assume that
        boolean hasOutputLink = false;
        boolean mustWrite = hasAttribute(AttributeName.AMOUNT);

        final VElement resLinks = getLinks(getLinkString(), null);
        final int linkSize = resLinks==null ? 0 : resLinks.size();

        for (int i = 0; i < linkSize; i++)
        {
            final JDFResourceLink rl = (JDFResourceLink) resLinks.elementAt(i);

            final JDFNode n = rl.getParentJDF();
            if (n != null)
            {
                final JDFNode.EnumType typ = EnumType.getEnum(n.getType());
                if (!typ.equals(JDFNode.EnumType.ProcessGroup) && !typ.equals(JDFNode.EnumType.Product))
                {
                    double ca = rl.getActualAmount(partMap);
                    double a = rl.getAmount(partMap);

                    if (rl.getUsage() == JDFResourceLink.EnumUsage.Input)
                    {
                        if (ca > 0)
                        {
                            amount -= ca;
                        }

                        if (a > 0)
                        {
                            amountRequired += a;
                        }

                        if ((ca >= 0) || (a >= 0))
                        {
                            mustWrite = true;
                        }
                    }
                    else
                    {
                        if (ca >= 0)
                        {
                            mustWrite = true;
                            amount += ca;
                            amountProduced += ca;
                        }

                        hasOutputLink = true;
                    }
                }
            }

            // only update the amounts by the previous values if no onput creates the resource, e.g. consumables
            if (!hasOutputLink)
            {
                amount += deltaAmount;
            }

            if (mustWrite)
            {
                if (amount > 0)
                {
                    setAmount(amount);
                }

                if (amountProduced > 0)
                {
                    final Double d = new Double(amountProduced);
                    setAttribute(AttributeName.AMOUNTPRODUCED, d.toString());
                }

                if (amountRequired > 0)
                {
                    setAmountRequired(amountRequired);
                }
            }
        }
    }

    /**
     * Gets all children from the actual element matching the given conditions
     * also get the non-overwritten elements in the parents for partitioned resources
     *
     * @param element        elementname you are searching for
     * @param nameSpaceURI   nameSpace you are searching for
     * @param mAttrib        attributes you are lokking for
     * @param bAnd           if true, a child is only added if it has all attributes specified in Attributes mAttrib
     * @param maxSize        maximum size of the element vector
     * @param bResolveTarget if true, IDRef elements are followed, dummy at this level but needed in JDFElement
     *
     * @return VElement - vector with all found elements
     *
     * @default getChildElementVector(null, null, null, true, 0, false)
     */
    public VElement getChildElementVector(String element, String nameSpaceURI, JDFAttributeMap mAttrib, boolean bAnd, int maxSize, boolean bResolveTarget)
    {
        VElement v = super.getChildElementVector(element, nameSpaceURI, mAttrib, bAnd, 0, bResolveTarget);
        final String nodeName = getNodeName();

        // remove partitions
        for (int i = v.size()-1; i >= 0; i--)
        {
            if (nodeName.equals( (v.item(i)).getNodeName()) )
            {
                v.remove(i);
            }
        }

        if (v.size() == 0 || isWildCard(element))
        {
            // no direct kids, check parents
            final KElement n = getParentNode_KElement();
            if (n!=null && n.getNodeName().equals(getNodeName())&& (n instanceof JDFResource))
            {
                final JDFResource r = (JDFResource)n;
                // recurse into parents
                VElement v2=r.getChildElementVector(element,nameSpaceURI,mAttrib,bAnd,maxSize,bResolveTarget);
                VString nodeNames=v.getElementNameVector(false);
                for(int i=v2.size()-1;i>=0;i--)
                {
                    if(nodeNames.contains(v2.item(i).getLocalName()))
                        v2.remove(i);
                }
                v.addAll(v2);
            }
        }

        return v;
    }


    /**
     * Gets a list of all partition keys that this resource may be implicitly partitioned by,
     * e.g. RunIndex for RunList...<br>
     * gets overridden in subclasses
     *
     * @return Vector of EnumPartIDKey
     */
    public Vector getImplicitPartitions()
    {
        return null;
    }


    /**
     * Tests if the given resources are compatible regarding their partitioning.
     *
     * The resources are compatible if the PartIDKeys for
     * the common start sequence of the PartIDKeys vectors are the same.
     * The resources are not compatible if one has PartIDKeys and the other not.
     *
     * @param other the other resource to check.
     *
     * @return boolean - <code>true</code> if partitioning of the other resource is
     *                                   compatible with this resource.
     */

    public boolean isPartitioningCompatible (JDFResource other)
    {
        boolean isCompatible = false;

        // Node names must be equal.
        if (this.getNodeName().equals(other.getNodeName()))
        {
            isCompatible = isPartitioningCompatible (other.getPartIDKeys());
        }

        return isCompatible;
    }

    /**
     * Tests if the resource is compatible with the given partition keys.
     *
     * The resource is compatible if the PartIDKeys for
     * the common start sequence of the PartIDKeys vectors are the same.
     * The resource is not compatible if one has PartIDKeys and the other not.
     *
     * @param vsPartitions the given partition keys to compare
     *
     * @return boolean - <code>true</code> if partitioning is compatible with this resource.
     */

    public boolean isPartitioningCompatible (VString vsPartitions)
    {
        boolean isCompatible = true;

        VString vsPartIDKeys1 = this.getPartIDKeys  ();

        for (int i = 0; (i < vsPartIDKeys1.size ()) && (i < vsPartitions.size ()) && isCompatible; i++)
        {
            isCompatible = vsPartIDKeys1.stringAt (i).equals (vsPartitions.stringAt (i));
        }

        return (isCompatible);
    }

    /**
     * Tests if a spawn of the given partition of the resource is allowed
     * (by means of the JDF specification).
     *
     * @return boolean - true if spawn is allowed.
     */

    public boolean isSpawnAllowed()
    {
        final JDFAttributeMap amPartMap = getPartMap();
        boolean isSpawnAllowed    = true;

        if (amPartMap.size () > 0)     // tuning
        {
            // Find first part ID key in amPartMap.
            String strPartIDKey = null;
            final VString vsPartKeys  = this.getPartIDKeys();
            final int nPartKeys = vsPartKeys.size();

            // find a partIDKey, which is in the partMap too (start from the end)
            for (int i=nPartKeys-1; i >= 0 && strPartIDKey == null; i--)
            {
                String str = vsPartKeys.stringAt(i);

                if (amPartMap.containsKey(str))
                {
                    strPartIDKey = str;
                }
            }

            // Check found part ID key.
            if (strPartIDKey != null)
            {
                if ((strPartIDKey.equals (JDFConstants.PARTIDKEY_DOCINDEX))
                        || (strPartIDKey.equals (JDFConstants.PARTIDKEY_DOCCOPIES))
                        || (strPartIDKey.equals (JDFConstants.PARTIDKEY_DOCRUNINDEX))
                        || (strPartIDKey.equals (JDFConstants.PARTIDKEY_DOCSHEETINDEX))
                        || (strPartIDKey.equals (JDFConstants.PARTIDKEY_RUNINDEX))
                        || (strPartIDKey.equals (JDFConstants.PARTIDKEY_SHEETINDEX))
//                      values not allowed according to JDF 1.2, 3.8.2.4
//                      || (strPartIDKey.equals (AttributeName.SORTING))
//                      || (strPartIDKey.equals (AttributeName.SORTAMOUNT))
                )
                {
                    isSpawnAllowed = false;
                }
            }
        }

        return isSpawnAllowed;
    }


    /**
     * Gets of 'this' child Contact element,
     * optionally creates it, if it doesn't exist.
     *
     * @return JDFContact - the matching Contact element
     */
    public JDFContact getCreateContact()
    {
        return (JDFContact) getCreateElement(ElementName.CONTACT, null, 0);
    }

    /**
     * Gets of 'this' an existing child Contact element
     *
     * @return JDFContact the matching Contact element
     */
    public JDFContact getContact()
    {
        return (JDFContact) getElement(ElementName.CONTACT, null, 0);
    }

    /**
     * Appends new Contact element to the end of 'this'
     *
     * @return JDFContact - newly created child Contact element
     */
    public JDFContact appendContact()
    {
        return (JDFContact) appendElementN(ElementName.CONTACT, 1, null);
    }


    /**
     * Gets of 'this' child Location element,
     * optionally creates it, if it doesn't exist.
     *
     * @return JDFLocation - the matching Location element
     */
    public JDFLocation getCreateLocationElement()
    {
        return (JDFLocation) getCreateElement(ElementName.LOCATION, null, 0);
    }

    /**
     * Gets of 'this' an existing child Location element
     *
     * @return JDFLocation - element Location
     */
    public JDFLocation getLocationElement()
    {
        return (JDFLocation) getElement(ElementName.LOCATION, null, 0);
    }

    /**
     * Appends new child Location element to the end of 'this'
     *
     * @return JDFLocation - newly created child Location element
     */
    public JDFLocation appendLocationElement()
    {
        return (JDFLocation) appendElementN(ElementName.LOCATION, 1, null);
    }

    /**
     * create a sourceresource element that pints to source
     * @param source the resource to reference
     * @return JDFSourceResource - the element
     */
    public JDFSourceResource createSourceResource(JDFResource source)
    {
        JDFSourceResource sr=appendSourceResource();
        sr.refElement(source);
        return sr;
    }

    /**
     * appends a new SourceResource element
     * @return JDFSourceResource - the new sourceresource
     */
    public JDFSourceResource appendSourceResource()
    {
        return (JDFSourceResource) appendElement(ElementName.SOURCERESOURCE,null);
    }

    /**
     * gets an existing SourceResource element
     * @param i the i'th sourceResource to get, 0=first etc.
     * @return JDFSourceResource - the  sourceresource
     */
    public JDFSourceResource getSourceResource(int i)
    {
        return (JDFSourceResource) getElement(ElementName.SOURCERESOURCE,null,i);
    }


    /**
     * Gets of 'this' the iSkip-th IdentificationField element,
     * optionally creates it, if it doesn't exist.
     * If iSkip is more than one larger than the number of elements,
     * only one will be created and appended.
     *
     * @param iSkip number of child IdentificationField elements to skip
     * @return JDFIdentificationField - the matching IdentificationField element
     *
     * default: getIdentificationField(0)
     */
    public JDFIdentificationField getCreateIdentificationField(int iSkip)
    {
        return (JDFIdentificationField) getCreateElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
    }

    /**
     * Gets of 'this' the iSkip-th child IdentificationField element
     *
     * @param iSkip number of child IdentificationField elements to skip, default=0
     * @return JDFIdentificationField - the matching IdentificationField element
     *
     * @default getIdentificationField(0)
     */
    public JDFIdentificationField getIdentificationField(int iSkip)
    {
        return (JDFIdentificationField)
        getElement(ElementName.IDENTIFICATIONFIELD, null, iSkip);
    }

    /**
     * Appends new child IdentificationField element to the end of 'this'
     *
     * @return JDFIdentificationField - newly created child IdentificationField element
     */
    public JDFIdentificationField appendIdentificationField()
    {
        return (JDFIdentificationField)
        appendElement(ElementName.IDENTIFICATIONFIELD, null);
    }

    /**
     * return the PartMap of ./Identical/Part, or null if it does not exist
     * @return JDFAttributeMapthe - map of the part in the identical element
     */
    public JDFAttributeMap getIdenticalMap()
    {
        JDFIdentical ident=getIdentical();
        if(ident==null) {
			return null;
		}
        return ident.getPartMap();
    }

    /**
     * get the identical element,
     * @return JDFIdentical - the identical element, null if noen exists
     */
    public JDFIdentical getIdentical()
    {

        return (JDFIdentical) getElement(ElementName.IDENTICAL,null,0);
    }

    /**
     * get or create the identical element,
     * @return JDFIdentical - the identical element
     */
    public JDFIdentical getCreateIdentical()
    {

        return (JDFIdentical) getCreateElement(ElementName.IDENTICAL,null,0);
    }

    /**
     * append an identical element,
     * @return JDFIdentical - the identical element
     * @throws JDFException if an Identical already exists
     */
    public JDFIdentical appendIdentical()
    {
        return (JDFIdentical) appendElementN(ElementName.IDENTICAL,1,null);
    }


    /**
     * Sets the 1st-nth element as identical to the 0th elemennt ov vPartMap
     * i.e. the partition leaves that match vPartMap[1]...vPartMap[size-1] are
     * set identical to vPartMap[0]
     *
     * @param vPartMap VJDFAttributeMap to correspond to
     */
    public void setIdentical(VJDFAttributeMap vPartMap)
    {
        if(vPartMap==null || vPartMap.size()<2) {
			return;
		}
        final JDFResource target=getPartition(vPartMap.elementAt(0),null);
        for(int i=1;i<vPartMap.size();i++)
        {
            final JDFResource leaf=getPartition(vPartMap.elementAt(i),null);
            if(leaf!=null)
            {
                leaf.setIdentical(target);
            }
        }
    }
    /**
     * Appends new child Identifical element that refers to target
     * also removes all subelements and attributes
     * If an identical already exists, the part element is overwritten
     *
     * @param target the resource leaf that this leaf should reference as identical
     *
     */
    public void setIdentical(JDFResource target)
    {
        if(target==null) {
			throw new JDFException("setIdentical: cannot create Identical in null element");
		}
        if(isResourceRoot()) {
			throw new JDFException("setIdentical: cannot create Identical in root");
		}
        if(target.getResourceRoot()!=getResourceRoot()) {
			throw new JDFException("setIdentical: Identical must be in the same resource");
		}

        final JDFAttributeMap targetMap = target.getPartMap();
        JDFAttributeMap thisPart=getPartMap();
        if(thisPart.equals(targetMap)) {
			return; // dont set to this
		}

        JDFAttributeMap thisAllMap=getAttributeMap();
        if(thisAllMap!=null)
        {
            thisAllMap.removeKeys(thisPart.keySet());
            removeAttributes(thisAllMap.getKeys());
        }

        removeChildren(null,null,null);

        JDFIdentical ident=(JDFIdentical) appendElement(ElementName.IDENTICAL);
        ident.setPartMap(targetMap);
    }


    /**
     * Gets of 'this' the iSkip-th QualityControlResult element,
     * optionally creates it, if it doesn't exist.
     * If iSkip is more than one larger that the number of elements,
     * only one will be created and appended.
     *
     * @param iSkip number of child QualityControlResult elements to skip
     * @return JDFQualityControlResult - the matching QualityControlResult element
     *
     * @default getCreateQualityControlResult(0)
     */
    public JDFQualityControlResult getCreateQualityControlResult(int iSkip)
    {
        return (JDFQualityControlResult)
        getCreateElement(ElementName.QUALITYCONTROLRESULT,null, iSkip);
    }

    /**
     * Gets of 'this' the iSkip-th child QualityControlResult element
     *
     * @param iSkip number of child QualityControlResult elements to skip, default=0
     * @return JDFQualityControlResult the matching QualityControlResult element
     *
     * @default getQualityControlResult(0)
     */
    public JDFQualityControlResult getQualityControlResult(int iSkip)
    {
        return (JDFQualityControlResult)
        getElement(ElementName.QUALITYCONTROLRESULT, null, iSkip);
    }

    /**
     * Appends new child QualityControlResult element to the end of 'this'
     *
     * @return JDFQualityControlResult - newly created child QualityControlResult element
     */
    public JDFQualityControlResult appendQualityControlResult()
    {
        return (JDFQualityControlResult)
        appendElement(ElementName.QUALITYCONTROLRESULT, null);
    }


    //**********************************************************************************
    //********************** getters, setters, validators ******************************
    //**********************************************************************************


    /**
     * Gets of 'this' the iSkip-th child Update element
     *
     * @param iSkip number of child Update elements to skip
     * @return JDFResource the matching Resource Update element
     * @deprecated updates never really took off in JDF
     *
     * @default getUpdate(0)
     */
    public JDFResource getUpdate(int iSkip)
    {
        JDFResource updateResource = null;
        KElement updateElement = null;

        updateElement = getElement(getUpdateName(), null, iSkip);
        if (updateElement != null)
        {
            updateResource = (JDFResource) updateElement;
        }

        return updateResource;
    }

    /**
     * Gets of 'this' child Update element with an appropriate UpdateID
     *
     * @deprecated updates never really took off in JDF
     * @param updateID UpdateID of the element to get
     * @return JDFResource the matching Update element
     */
    public JDFResource getUpdate(String updateID)
    {
        JDFResource updateResource = null;
        KElement updateElement = null;

        updateElement = getChildWithAttribute(
                getUpdateName(),
                AttributeName.UPDATEID,
                null,
                updateID,
                0,
                true);

        if (updateElement != null)
        {
            updateResource = (JDFResource) updateElement;
        }

        return updateResource;
    }

    /**
     * Gets of 'this' a vector of all Update elements
     *
     * @deprecated updates never really took off in JDF
     * @return VElement vector of all Resource Update elements in 'this'
     */
    public VElement getUpdateVector()
    {
        return getChildElementVector(getUpdateName(),
                null,null, true,  0,false);
    }

    /**
     * Removes of 'this' child Update element with an appropriate UpdateID
     *
     * @deprecated updates never really took off in JDF
     * @param updateID UpdateID of the element to remove
     */
    public void removeUpdate(String updateID)
    {
        getUpdate(updateID).deleteNode();
    }

    /**
     * Removes of 'this' the iSkip-th child Update element
     *
     * @param iSkip number of child Update elements to skip
     *
     * @deprecated updates never really took off in JDF
     * @default removeUpdate(0)
     */
    public void removeUpdate(int iSkip)
    {
        getUpdate(iSkip).deleteNode();
    }

    /**
     * Appends to 'this' a resource Update element with an appropriate UpdateID
     *
     * @param updateID updateID of the new Update element
     * @return JDFResource newly created Resource Update element
     *
     * @throws JDFException if Update element with such ID already exists
     *
     * @deprecated updates never really took off in JDF
     * @default appendUpdate(JDFConstants.EMPTYSTRING)
     */
    public JDFResource appendUpdate(String updateID)
    {
        String uid = updateID;
        JDFResource r = null;
        if (isWildCard(updateID))
        {
            uid = "Up" + uniqueID(0);
        }
        else
        {
            r = getUpdate(updateID);
            if (r != null)
            {
                throw new JDFException("JDFResource.appendUpdate: update with id = " + updateID + " exists!");
            }
        }

        KElement k = null;
        k = appendElement(getUpdateName(), null);
        if (k != null)
        {
            r = (JDFResource) k;
            r.setUpdateID(uid);
        }

        return r;
    }

    /**
     * Gets of 'this' the number of child Update elements
     * @deprecated updates never really took off in JDF
     * @return int - number of Update elements in 'this'
     */
    public int numUpdates()
    {
        return numChildElements(getUpdateName(), null);
    }

    /**
     * Tests, whether in 'this' any child Update elements already exist
     *
     * @deprecated updates never really took off in JDF
     * @return boolean - true, if 'this' has already one or more Update elements
     */
    public boolean hasUpdate()
    {
        return numUpdates() > 0;
    }

    /**
     * Gets the qualified node name of resource Update based on 'this'
     *
     * @deprecated updates never really took off in JDF
     * @return String - the mangled node name
     */
    public String getUpdateName()
    {
        return getNodeName() + JDFConstants.UPDATE;
    }

    /**
     * Sets attribute AgentName
     *
     * @param value the value to set the attribute to
     */
    public void setAgentName(String value)
    {
        setAttribute(AttributeName.AGENTNAME, value);
    }

    /**
     * Gets string attribute AgentName
     *
     * @return String - the attribute value
     */
    public String getAgentName()
    {
        return getAttribute(AttributeName.AGENTNAME);
    }

    /**
     * Sets attribute AgentVersion
     *
     * @param value the value to set the attribute to
     */
    public void setAgentVersion(String value)
    {
        setAttribute(AttributeName.AGENTVERSION, value);
    }

    /**
     * Gets string attribute AgentVersion
     *
     * @return String the - attribute value
     */
    public String getAgentVersion()
    {
        return getAttribute(AttributeName.AGENTVERSION);
    }

    /**
     * Sets attribute AlternateBrand
     *
     * @param value value to set the attribute to
     */
    public void setAlternateBrand(String value)
    {
        setAttribute(AttributeName.ALTERNATEBRAND, value);
    }

    /**
     * Gets string attribute AlternateBrand
     *
     * @return String - the attribute value
     */
    public String getAlternateBrand()
    {
        return getAttribute(AttributeName.ALTERNATEBRAND);
    }


    /**
     * Sets attribute Amount
     *
     * @param amount value to set the attribute to
     */
    public void setAmount(double amount)
    {
        setAttribute(AttributeName.AMOUNT, amount, null);
    }

    /**
     * Gets double attribute Amount
     *
     * @return double - the attribute value
     */
    public double getAmount()
    {
        return getRealAttribute(AttributeName.AMOUNT, null, 0.0);
    }


    /**
     * Sets attribute AmountProduced
     *
     * @param value value to set the attribute to
     */
    public void setAmountProduced(double value)
    {
        setAttribute(AttributeName.AMOUNTPRODUCED, value,null);
    }

    /**
     * Gets double attribute AmountProduced
     *
     * @return double - the attribute value
     */
    public double getAmountProduced()
    {
        return getRealAttribute(AttributeName.AMOUNTPRODUCED, null, 0.0);
    }

    /**
     * append an empty GeneralID
     *
     * @return the newly created GeneralID
     */
    public JDFGeneralID appendGeneralID()
    {
        return (JDFGeneralID) appendElement(ElementName.GENERALID,null);
    }

    /**
     * append a GeneralID with idValue, duplicate entries are retained
     *
     * @param idUsage the IDUsage attribute of the generalID
     * @param idValue the IDValue attribute of the generalID
     * @return the newly created GeneralID
     */
    public JDFGeneralID appendGeneralID(String idUsage, String idValue)
    {
        JDFGeneralID gid = (JDFGeneralID) appendElement(ElementName.GENERALID);
        gid.setIDValue(idValue);
        gid.setIDUsage(idUsage);
        return gid;
    }

    /**
     * gets attribute GeneralID
     * @param i get the i'th element that fits
     * @return the attribute value
     */
    public JDFGeneralID getGeneralID(int i)
    {
        return (JDFGeneralID) getElement(ElementName.GENERALID,null,i);
    }

    /**
     * Creates or Updates a GeneralID with the IDUsage idUsage and IDValue=idValue
     * all entries with a duplicate idUsage are removed
     *
     * @param idUsage usage to set the attribute to
     * @param idValue   value to set the attribute to
     */
    public void setGeneralID(String idUsage, String idValue)
    {
        JDFGeneralID gid = null;

        VElement v = getChildElementVector_JDFElement(ElementName.GENERALID, null,
                            new JDFAttributeMap(AttributeName.IDUSAGE, idUsage), true, 0, true);
        if (v.size() == 0)
        {
            gid = (JDFGeneralID) appendElement(ElementName.GENERALID);
        }
        else if (v.size() >= 1)
        {
            gid = (JDFGeneralID) v.elementAt(0);

            for (int i = 1; i < v.size(); i++) {
				// remove any duplicates
                ((KElement) v.elementAt(i)).deleteNode();
			}
        }

        if (gid != null)
        {
            gid.setIDValue(idValue);
            gid.setIDUsage(idUsage);
        }
    }
    /**
     * removes GeneralID with the IDUsage idUsage
     *
     * @param idUsage value to set the attribute to
     */
    public void removeGeneralID(String idUsage)
    {
        VElement v=getChildElementVector_JDFElement(ElementName.GENERALID,null,new JDFAttributeMap(AttributeName.IDUSAGE,idUsage),true,0,true);
        for(int i=0;i<v.size();i++) {
			((KElement)v.elementAt(i)).deleteNode();
		}
    }

    /**
     * Gets IDValue of the GeneralID with IDUsage=idUsage
     * null, if none exists
     * @return double the attribute value
     */
    public String getGeneralID(String idUsage)
    {
        VElement v=getChildElementVector(ElementName.GENERALID,null,new JDFAttributeMap(AttributeName.IDUSAGE,idUsage),true,0,true);
        if(v.size()==0) {
			return null;
		}
        JDFGeneralID gid=(JDFGeneralID)v.elementAt(0);
        return gid.getIDValue();
    }

    /**
     * Sets attribute AmountRequired
     *
     * @param value value to set the attribute to
     */
    public void setAmountRequired(double value)
    {
        setAttribute(AttributeName.AMOUNTREQUIRED, value,null);
    }

    /**
     * Gets double attribute AmountRequired
     *
     * @return double - the attribute value
     */
    public double getAmountRequired()
    {
        return getRealAttribute(AttributeName.AMOUNTREQUIRED, null, 0.0);
    }

    /**
     * Sets attribute Author
     *
     * @param value the value to set the attribute to
     */
    public void setAuthor(String value){
        setAttribute(AttributeName.AUTHOR, value);
    }

    /**
     * Gets string attribute Author
     *
     * @return String - the attribute value
     */
    public String getAuthor()
    {
        return getAttribute(AttributeName.AUTHOR);
    }

    /**
     * Sets attribute BatchID
     *
     * @param value value to set the attribute to
     */
    public void setBatchID(String value)
    {
        setAttribute(AttributeName.BATCHID, value);
    }

    /**
     * Gets string attribute BatchID
     *
     * @return String - the attribute value
     */
    public String getBatchID()
    {
        return getAttribute(AttributeName.BATCHID);
    }


    /**
     * Sets attribute BinderySignatureName
     *
     * @param value the value to set the attribute to
     */
    public void setBinderySignatureName(String value)
    {
        setAttribute(AttributeName.BINDERYSIGNATURENAME, value);
    }

    /**
     * Gets string attribute BinderySignatureName
     *
     * @return String - the attribute value
     */
    public String getBinderySignatureName()
    {
        return getAttribute(AttributeName.BINDERYSIGNATURENAME, null, JDFConstants.EMPTYSTRING);
    }


    /**
     * Sets attribute BlockName
     *
     * @param value the value to set the attribute to
     */
    public void setBlockName(String value)
    {
        setAttribute(AttributeName.BLOCKNAME, value);
    }

    /**
     * Gets string attribute BlockName
     *
     * @return String - the attribute value
     */
    public String getBlockName()
    {
        return getAttribute(AttributeName.BLOCKNAME, null, JDFConstants.EMPTYSTRING);
    }


    /**
     * Sets attribute Brand
     *
     * @param value value to set the attribute to
     */
    public void setBrand(String value)
    {
        setAttribute(AttributeName.BRAND, value);
    }

    /**
     * Gets string attribute Brand
     *
     * @return String - the attribute value
     */
    public String getBrand()
    {
        return getAttribute(AttributeName.BRAND);
    }


    /**
     * Sets attribute BundleItemIndex
     *
     * @param value the value to set the attribute to
     */
    public void setBundleItemIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.BUNDLEITEMINDEX, value.toString());
    }

    /**
     * Gets range attribute BundleItemIndex
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getBundleItemIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.BUNDLEITEMINDEX, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }

    /**
     * Sets attribute CatalogDetails
     *
     * @param value the value to set the attribute to
     */
    public void setCatalogDetails(String value)
    {
        setAttribute(AttributeName.CATALOGDETAILS ,value);
    }

    /**
     * Gets string attribute CatalogDetails
     *
     * @return String - the attribute value
     */
    public String getCatalogDetails()
    {
        return getAttribute(AttributeName.CATALOGDETAILS);
    }

    /**
     * Sets attribute CatalogID
     *
     * @param value the value to set the attribute to
     */
    public void setCatalogID(String value)
    {
        setAttribute(AttributeName.CATALOGID, value);
    }

    /**
     * Gets string attribute CatalogID
     *
     * @return String - the attribute value
     */
    public String getCatalogID()
    {
        return getAttribute(AttributeName.CATALOGID);
    }


    /**
     * Sets attribute CellIndex
     *
     * @param value the value to set the attribute to
     */
    public void setCellIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.CELLINDEX, value.toString());
    }

    /**
     * Gets range attribute CellIndex
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getCellIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.CELLINDEX, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }


    /**
     * Sets attribute Condition
     *
     * @param value the value to set the attribute to
     */
    public void setCondition(String value)
    {
        setAttribute(AttributeName.CONDITION, value);
    }

    /**
     * Gets string attribute Condition
     *
     * @return String - the attribute value
     */
    public String getCondition()
    {
        return getAttribute(AttributeName.CONDITION);
    }


    /**
     * Sets attribute DocCopies
     *
     * @param value the value to set the attribute to
     */
    public void setDocCopies(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.DOCCOPIES, value.toString());
    }

    /**
     * Gets range attribute DocCopies
     *
     * @return JDFIntegerRangeList the attribute value
     */
    public JDFIntegerRangeList getDocCopies()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.DOCCOPIES, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }


    /**
     * Sets attribute DocIndex
     *
     * @param value the value to set the attribute to
     */
    public void setDocIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.DOCINDEX, value.toString());
    }

    /**
     * Gets range attribute DocIndex
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getDocIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.DOCINDEX, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }

    /**
     * Sets attribute DeliveryUnit
     *
     * @param iUnit a value between 0 and 9 to set DeliveryUnit<iUnit>
     * @param value the value to set the attribute to
     */
    public void setDeliveryUnit(int iUnit, String value)
    {
        if(iUnit<0 || iUnit>9) {
			throw new JDFException("setDeliveryUnit: invalid iUnit: "+String.valueOf(iUnit));
		}

        setAttribute(AttributeName.DELIVERYUNIT+String.valueOf(iUnit), value);
    }

    /**
     * Gets attribute DeliveryUnit
     *
     * @param iUnit a value between 0 and 9 to set DeliveryUnit<iUnit>
     *
     * @return String - the attribute value
     */
    public String getDeliveryUnit(int iUnit)
    {
        if(iUnit<0 || iUnit>9) {
			throw new JDFException("getDeliveryUnit: invalid iUnit: "+String.valueOf(iUnit));
		}
        return getAttribute(AttributeName.DELIVERYUNIT+String.valueOf(iUnit), null, JDFConstants.EMPTYSTRING);
    }

    /**
     * Sets attribute DocRunIndex
     *
     * @param value the value to set the attribute to
     */
    public void setDocRunIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.DOCRUNINDEX, value.toString());
    }

    /**
     * Gets range attribute DocRunIndex
     *
     * @return JDFIntegerRangeList the attribute value
     */
    public JDFIntegerRangeList getDocRunIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.DOCRUNINDEX, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }

    /**
     * Sets attribute DocSheetIndex
     *
     * @param value the value to set the attribute to
     */
    public void setDocSheetIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.DOCSHEETINDEX, value.toString());
    }

    /**
     * Gets range attribute DocSheetIndex
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getDocSheetIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.DOCSHEETINDEX, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }

    /**
     * Sets attribute FountainNumber
     *
     * @param value the value to set the attribute to
     */
    public void setFountainNumber(int value)
    {
        final Integer i = new Integer(value);
        setAttribute(AttributeName.FOUNTAINNUMBER, i.toString());
    }

    /**
     * Gets integer attribute FountainNumber
     *
     * @return int - the attribute value
     */
    public int getFountainNumber()
    {
        return getIntAttribute(AttributeName.FOUNTAINNUMBER, null, 0);
    }

    /**
     * Sets attribute ItemNames
     *
     * @param value the value to set the attribute to
     */
    public void setItemNames(String value)
    {
        setAttribute(AttributeName.ITEMNAMES, value);
    }

    /**
     * Gets string attribute ItemNames
     *
     * @return String - the attribute value
     */
    public String getItemNames()
    {
        return getAttribute(AttributeName.ITEMNAMES, null, JDFConstants.EMPTYSTRING);
    }


    /**
     * Sets attribute LayerIDs
     *
     * @param value the value to set the attribute to
     */
    public void setLayerIDs(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.LAYERIDS, value.toString());
    }

    /**
     * Gets range attribute LayerIDs
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getLayerIDs()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.LAYERIDS, null,  JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }

    /**
     * Sets attribute Location
     *
     * @param value the value to set the attribute to
     */
    public void setLocation(String value)
    {
        setAttribute(AttributeName.LOCATION, value);
    }

    /**
     * Gets string attribute Location
     *
     * @return - String the attribute value
     */
    public String getLocation()
    {
        return getAttribute(AttributeName.LOCATION, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * Sets attribute Locked
     *
     * @param value the value to set the attribute to
     */
    public void setLocked(boolean value)
    {
        String localLock=getAttribute_KElement(AttributeName.LOCKED,null,null);
        if(localLock!=null)
        {
            boolean b=localLock.equalsIgnoreCase(JDFConstants.TRUE);
            if(b==value) {
				return; // don't reset to current value - NOP
			}
            removeAttribute(AttributeName.LOCKED); // remove any  value so that we only get a true from an inherited value
        }
        if(value || getLocked())
        { // don't reset the default many times, but add false if true is inherited
            setAttribute(AttributeName.LOCKED, value,null);
        }
    }



    /**
     * Gets boolean attribute Locked; defaults to false.
     *
     * @return boolean the attribute value
     */
    public boolean getLocked()
    {
        return getBoolAttribute(AttributeName.LOCKED, null, false);
    }


    /**
     * Sets attribute NoOp
     *
     * @param value the value to set the attribute to
     */
    public void setNoOp(boolean value)
    {
        setAttribute(AttributeName.NOOP, value, null);
    }

    /**
     * Gets boolean attribute NoOp; defaults to false
     *
     * @return boolean - the attribute value
     */
    public boolean getNoOp()
    {
        return getBoolAttribute(AttributeName.NOOP, null, false);
    }

    /**
     * Sets attribute Option
     *
     * @param value the value to set the attribute to
     */
    public void setOption(String value)
    {
        setAttribute(AttributeName.OPTION, value);
    }

    /**
     * Gets string attribute Option
     *
     * @return String - the attribute value
     */
    public String getOption()
    {
        return getAttribute(AttributeName.OPTION, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * Sets attribute PageNumber
     *
     * @param value the value to set the attribute to
     */
    public void setPageNumber(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.PAGENUMBER, value.toString());
    }

    /**
     * Gets range attribute PageNumber
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getPageNumber()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.PAGENUMBER, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }


    /**
     * Adds a new PartIDKey to the root
     * first checks for existence
     *
     * @param partType new PartIDKey to add
     *
     * @throws JDFException if here is an attempt to add implicit partition
     */
    protected void addPartIDKey(EnumPartIDKey partType)
    {
        final String s = partType.getName();
        final JDFResource r = getResourceRoot();

        final Vector implicitPartitions = getImplicitPartitions();
        if (implicitPartitions!=null &&implicitPartitions.contains(partType))
        {
            throw new JDFException("AddPartIDKey: attempting to add implicit partition: "+s);
        }
        r.appendAttribute(AttributeName.PARTIDKEYS, s, null, JDFConstants.BLANK, true);
    }

    /**
     * Sets the value of attibute, specified by key
     *
     * @param key   the PartIDKey attribute name
     * @param value the value to set key to
     */
    public void setPartIDKey(EnumPartIDKey key, String value)
    {
        setAttribute(key.getName(), value, null);
        addPartIDKey(key);
    }


    /**
     * Gets a list of all valid part keys for this resource
     *
     * @return VString - list of all PartIDKeys
     */
    public VString getPartIDKeys()
    {
        final JDFResource partRoot = getResourceRoot();
        if (partRoot != null)
        {
            final String idKeys = partRoot.getAttribute(AttributeName.PARTIDKEYS, null, null);
            return StringUtil.tokenize(idKeys,JDFConstants.BLANK,false);
        }
        return new VString();
    }


    /**
     * Sets attribute PartUsage
     *
     * @param value enumeration value of the attribute PartUsage to be set
     */
    public void setPartUsage(EnumPartUsage value)
    {
        setAttribute( AttributeName.PARTUSAGE, value.getName(),null);
    }

    /**
     * Gets typesafe enumerated value of attribute PartUsage; defaults to PartUsage_Explicit
     *
     * @return EnumPartUsage - attribute enumeration value
     */
    public EnumPartUsage getPartUsage()
    {
        return EnumPartUsage.getEnum(getAttribute(AttributeName.PARTUSAGE,null,EnumPartUsage.Explicit.getName()));
    }


    /**
     * Sets attribute PartUsage
     *
     * @param value enumeration value of the attribute PartUsage to be set
     */
    public void setLotControl(EnumLotControl value)
    {
        setAttribute(AttributeName.LOTCONTROL,value.getName(), null);
    }

    /**
     * Gets typesafe enumerated value of attribute LotControl; defaults to LotControl_Explicit
     *
     * @return EnumLotControl - attribute enumeration value
     */
    public EnumLotControl getLotControl()
    {
        return EnumLotControl.getEnum(getAttribute(AttributeName.LOTCONTROL,null, null));
    }


    /**
     * Sets attribute PartVersion
     *
     * @param value the value to set the attribute to
     */
    public void setPartVersion(String value)
    {
        setAttribute(AttributeName.PARTVERSION, value);
    }

    /**
     * Gets string attribute PartVersion
     *
     * @return String - the attribute value
     */
    public String getPartVersion()
    {
        return getAttribute(AttributeName.PARTVERSION, null, JDFConstants.EMPTYSTRING);
    }


    /**
     * Sets attribute PipeID
     *
     * @param value the value to set the attribute to
     */
    public void setPipeID(String value)
    {
        setAttribute(AttributeName.PIPEID,value);
    }

    /**
     * Gets string attribute PipeID
     *
     * @return String - the attribute value
     */
    public String getPipeID()
    {
        return getAttribute(AttributeName.PIPEID);
    }


    /**
     * Tests, if this leaf has a PipePartIDKey as specified by key
     *
     * @param key the PipePartIDKey attribute name
     * @return boolean - true, if key exists in this leaf or below
     *
     * @throws JDFException if the specified key is illegal
     */
    public boolean hasPipePartIDKey(EnumPartIDKey key)
    {
        return hasAttribute(key.getName());
    }

    /**
     * Tests, if this leaf has a consistent PartIDKey as specified by key
     *
     * @param key the PipePartIDKey attribute name
     * @return boolean - true, if key exists in this leaf is in PipePartIDKeys
     */
    public boolean consistentPipePartIDKeys(EnumPartIDKey key)
    {
        final String s = key.getName();
        if (!hasAttribute(s))
        {
            return true;
        }

        // the key exists but is not in PipePartIDKeys, oops
        final String strPipe = getResourceRoot().getAttribute(AttributeName.PIPEPARTIDKEYS);
        if (!StringUtil.hasToken(strPipe,s,JDFConstants.BLANK,0))
        {
            return false;
        }
        // all is well
        return true;
    }


    /**
     * Adds a new PipePartIDKey to the root, first checks for existence
     *
     * @param partType new PipePartIDKey to add
     *
     * @throws JDFException if here is an attempt to add implicit partition
     */
    public void addPipePartIDKey(EnumPartIDKey partType)
    {
        getResourceRoot().appendAttribute(AttributeName.PIPEPARTIDKEYS,
                partType.getName(), null, JDFConstants.BLANK, true);
    }

    /**
     * Sets the value of attibute, specified by key
     *
     * @param key   the PipePartIDKey attribute name
     * @param value the value to set key to
     */
    public void setPipePartIDKey(EnumPartIDKey key, String value)
    {
        setAttribute(key.getName(), value);
        addPipePartIDKey(key);
    }

    /**
     * Gets a list of all valid pipe part key enums  for this resource
     * @return Vector - list of all PipePartIDKey enums
     */
    public Vector getPipePartIDKeysEnum()
    {
        Vector v=null;

        VString vPartIDKeys = getPartIDKeys();
        v = getEnumerationsAttribute(AttributeName.PIPEPARTIDKEYS,null,EnumPartIDKey.getEnum(0),false);
        for (int i=0; i < v.size(); i++)
        {
            if (!vPartIDKeys.contains(((EnumPartIDKey)v.elementAt(i)).getName()))
            {
                throw new JDFException("JDFResource.getPipePartIDKeys: key " + v.elementAt(i) + " is not subset of PartIDKey");
            }
        }
        return v;
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
        Vector v=getPipePartIDKeysEnum();
        for(int i=0;i<v.size();i++) {
			vPipePartIDKeys.add(((EnumPartIDKey)v.elementAt(i)).getName());
		}

        return vPipePartIDKeys;
    }


    /**
     * Set attribute PipeProtocol
     *
     * @param value the value to set the attribute to
     */
    public void setPipeProtocol(String value)
    {
        setAttribute(AttributeName.PIPEPROTOCOL, value);
    }

    /**
     * Get string attribute PipeProtocol
     *
     * @return String - the attribute value
     */
    public String getPipeProtocol()
    {
        return getAttribute(AttributeName.PIPEPROTOCOL);
    }

    /**
     * Sets attribute PipeURL
     *
     * @param value the value to set the attribute to
     */
    public void setPipeURL(String value)
    {
        setAttribute(AttributeName.PIPEURL, value);
    }

    /**
     * Gets string attribute PipeURL
     *
     * @return String - the attribute value
     */
    public String getPipeURL()
    {
        return getAttribute(AttributeName.PIPEURL);
    }


    /**
     * Sets attribute PreflightRule
     *
     * @param value the value to set the attribute to
     */
    public void setPreflightRule(String value)
    {
        setAttribute(AttributeName.PREFLIGHTRULE, value);
    }

    /**
     * Gets string attribute PreflightRule
     *
     * @return String the attribute value
     */
    public String getPreflightRule()
    {
        return getAttribute(AttributeName.PREFLIGHTRULE, null, JDFConstants.EMPTYSTRING);
    }


    /**
     * Sets attribute PreviewType
     *
     * @param value enumeration value of attribute PreviewType to be set
     */
    public void setPreviewType(JDFPart.EnumPreviewType value)
    {
        setAttribute(AttributeName.PREVIEWTYPE, value.getName(),null);
    }

    /**
     * Gets typesafe enumerated value of attribute PreviewType
     *
     * @return JDFPart.EnumPreviewType - the enumeration value of attribute
     */
    public JDFPart.EnumPreviewType getPreviewType()
    {
        return JDFPart.EnumPreviewType.getEnum(getAttribute(AttributeName.PREVIEWTYPE, null, null));
    }

    /**
     * Sets attribute ProductID
     *
     * @param value value to set the attribute to
     */
    public void setProductID(String value)
    {
        setAttribute(AttributeName.PRODUCTID, value);
    }

    /**
     * Gets string attribute ProductID
     *
     * @return String - the attribute value
     */
    public String getProductID()
    {
        return getAttribute(AttributeName.PRODUCTID);
    }

    /**
     * Sets attribute Class
     *
     * corresponds to C++ JDFResource::SetClass()
     *
     * @param value enumeration value of the attribute Class to be set
     *
     * @throws JDFException if here is attempt to set value as Class_Unknown or invalid class value
     */
    public void setResourceClass(EnumResourceClass value)
    {
        if (value==null)
        {
            // what a stupid thing to set it to - Ciao
            throw new JDFException("JDFResource.SetResourceClass: Invalid class value ");
        }
        // only set class for the root
        if(isResourceRootRoot()||isResourceElement())
        {
            setAttribute(AttributeName.CLASS, value.getName(),  null);
        }
        else // just in case, clean up
        {
            removeAttribute(AttributeName.CLASS, null);
        }
    }

    /**
     * Gets typesafe enumerated value of attribute Class
     *
     * corresponds to C++ JDFResource::GetClass(), getClass() already exists in Java
     *
     * @return EnumResourceClass - attribute enumeration value
     */
    public EnumResourceClass getResourceClass()
    {
        return EnumResourceClass.getEnum(getAttribute(AttributeName.CLASS, null, null));
    }

    /**
     * Typesafe attribute validation of Class
     *
     * corresponds to C++ JDFResource::ValidClass()
     *
     * @param level level of attribute validation
     * @return boolean - true, if valid
     */
    public boolean validResourceClass(EnumValidationLevel level)
    {

        boolean b=validAttribute(AttributeName.CLASS, null, level);
        if(!b) {
			return false;
		}
        // don't need to check for completeness - already done in the standard validAttribute call
        if(isResourceRootRoot()) {
			return validClass();
		}
        return true;
    }

    /**
     * Typesafe attribute validation of Class
     *
     * corresponds to C++ JDFResource::ValidClass()
     *
     * @return boolean true, if valid
     */
    final public boolean validClass()
    {
        EnumResourceClass c=getValidClass();
        if(c==null) {
			return true;
		}
        if(!hasAttribute_KElement(AttributeName.CLASS,null,false)) {
			return true;
		}
        return c==getResourceClass();
    }

    /**
     * get the fixed class for this resource,
     * @return EnumResourceClass - the class of this resource, null if no fixed class is known
     */
    public EnumResourceClass getValidClass()
    {
        return null;
    }


    /**
     * Sets attribute GrossWeight
     *
     * @param value value to set the attribute to
     */
    public void setGrossWeight(double value)
    {
        setAttribute(AttributeName.GROSSWEIGHT, Double.toString(value));
    }

    /**
     * Gets double attribute GrossWeight
     *
     * @return double - the attribute value
     */
    public double getGrossWeight()
    {
        return getRealAttribute(AttributeName.GROSSWEIGHT, null, 0.0);
    }


    /**
     * Sets attribute ResourceWeight
     *
     * @param value value to set the attribute to
     */
    public void setResourceWeight(double value)
    {
        setAttribute(AttributeName.GROSSWEIGHT, Double.toString(value));
    }

    /**
     * Gets double attribute ResourceWeight
     *
     * @return double - the attribute value
     */
    public double getResourceWeight()
    {
        return getRealAttribute(AttributeName.GROSSWEIGHT, null, 0.0);
    }


    /**
     * Sets attribute RibbonName
     *
     * @param value the value to set the attribute to
     */
    public void setRibbonName(String value)
    {
        setAttribute(AttributeName.RIBBONNAME, value);
    }

    /**
     * Gets string attribute RibbonName
     *
     * @return String the - attribute value
     */
    public String getRibbonName()
    {
        return getAttribute(AttributeName.RIBBONNAME, null, JDFConstants.EMPTYSTRING);
    }


    /**
     * Sets attribute Run
     *
     * @param value the value to set the attribute to
     */
    public void setRun(String value)
    {
        setAttribute(AttributeName.RUN, value, null);
    }

    /**
     * Gets string attribute Run
     *
     * @return String - the attribute value
     */
    public String getRun()
    {
        return getAttribute(AttributeName.RUN, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * Sets attribute RunIndex
     *
     * @param value the value to set the attribute to
     */
    public void setRunIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.RUNINDEX, value.toString());
    }

    /**
     * Gets range attribute RunIndex
     *
     * @return JDFIntegerRangeList the attribute value
     */
    public JDFIntegerRangeList getRunIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.RUNINDEX, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }

    /**
     * Sets attribute RunPage
     *
     * @param value the value to set the attribute to
     */
    public void setRunPage(int value)
    {
        setAttribute(AttributeName.RUNPAGE, value, null);
    }

    /**
     * Gets integer attribute RunPage
     *
     * @return int - the attribute value
     */
    public int getRunPage()
    {
        return getIntAttribute(AttributeName.RUNPAGE, null, 0);
    }

    /**
     * Sets attribute RunTags
     *
     * @param value the value to set the attribute to
     */
    public void setRunTags(VString value)
    {
        final StringBuffer strBuff = new StringBuffer(100);
        for (int i = 0; i < value.size(); i++)
        {
            strBuff.append(value.elementAt(i));
        }
        setAttribute(AttributeName.RUNTAGS, strBuff.toString(), null);
    }

    /**
     * Gets NMTOKENS attribute RunTags
     *
     * @return VString - the value of the attribute
     */
    public VString getRunTags()
    {
        final String s = getAttribute(AttributeName.RUNTAGS, null, JDFConstants.EMPTYSTRING);
        final Vector v = StringUtil.tokenize(s, JDFConstants.BLANK, false);
        final VString vstr = new VString();
        vstr.addAll(v);
        return vstr;
    }

    /**
     * Sets attribute SectionIndex
     *
     * @param value the value to set the attribute to
     */
    public void setSectionIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.SECTIONINDEX, value.toString());
    }

    /**
     * Gets range attribute SectionIndex
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getSectionIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.SECTIONINDEX, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }

    /**
     * Sets attribute Separation
     *
     * @param value the value to set the attribute to
     */
    public void setSeparation(String value)
    {
        setAttribute(AttributeName.SEPARATION, value, null);
    }

    /**
     * Gets string attribute Separation
     *
     * @return String - the attribute value
     */
    public String getSeparation()
    {
        return getAttribute(AttributeName.SEPARATION, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * Sets attribute SetDocIndex
     *
     * @param value the value to set the attribute to
     */
    public void setSetDocIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.SETDOCINDEX, value.toString());
    }

    /**
     * Gets range attribute SetDocIndex
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getSetDocIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.SETDOCINDEX, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }

    /**
     * Sets attribute SetIndex
     *
     * @param value the value to set the attribute to
     */
    public void setSetIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.SETINDEX, value.toString());
    }

    /**
     * Gets range attribute SetIndex
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getSetIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.SETINDEX, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }



    /**
     * Sets attribute SetRunIndex
     *
     * @param value the value to set the attribute to
     */
    public void setSetRunIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.SETRUNINDEX,value.toString());
    }

    /**
     * Gets range attribute SetRunIndex
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getSetRunIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.SETRUNINDEX, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }


    /**
     * Sets attribute SetSheetIndex
     *
     * @param value the value to set the attribute to
     */
    public void setSetSheetIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.SETSHEETINDEX, value.toString());
    }

    /**
     * Gets range attribute SetSheetIndex
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getSetSheetIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.SETSHEETINDEX, null , JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }


    /**
     * Sets attribute SheetIndex
     *
     * @param value the value to set the attribute to
     */
    public void setSheetIndex(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.SHEETINDEX, value.toString());
    }

    /**
     * Gets range attribute SheetIndex
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getSheetIndex()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.SHEETINDEX, null , JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }

    /**
     * Sets attribute SheetName
     *
     * @param value the value to set the attribute to
     */
    public void setSheetName(String value)
    {
        setAttribute(AttributeName.SHEETNAME,value);
    }

    /**
     * Gets string attribute SheetName
     *
     * @return String - the attribute value
     */
    public String getSheetName()
    {
        return getAttribute(AttributeName.SHEETNAME, null, JDFConstants.EMPTYSTRING);
    }


    /**
     * Sets attribute Side
     *
     * @param value enumeration value of attribute Side to be set
     */
    public void setSide( JDFPart.EnumSide value)
    {
        setAttribute(AttributeName.SIDE, value.getName(), null);
    }

    /**
     * Gets typesafe enumerated value of attribute Side
     *
     * @return JDFPart.EnumSide - the enumeration value of the attribute
     */
    public JDFPart.EnumSide getSide()
    {
        return JDFPart.EnumSide.getEnum(getAttribute(AttributeName.SIDE,  null, null));
    }

    /**
     * Sets attribute SignatureName
     *
     * @param value the value to set the attribute to
     */
    public void setSignatureName(String value)
    {
        setAttribute(AttributeName.SIGNATURENAME,value);
    }

    /**
     * Gets string attribute SignatureName
     *
     * @return String - the attribute value
     */
    public String getSignatureName()
    {
        return getAttribute(AttributeName.SIGNATURENAME, null , JDFConstants.EMPTYSTRING);
    }

    /**
     * Gets string attribute StationName
     *
     * @return String - the attribute value
     */
    public String getStationName()
    {
        return getAttribute(AttributeName.STATIONNAME, null , JDFConstants.EMPTYSTRING);
    }

    /**
     * Sets attribute StationName
     *
     * @param value the value to set the attribute to
     */
    public void setStationName(String value)
    {
        setAttribute(AttributeName.STATIONNAME,value);
    }


    /**
     * Sets attribute SortAmount
     *
     * @param value value to set the attribute to
     */
    public void setSortAmount(boolean value)
    {
        setAttribute(AttributeName.SORTAMOUNT, value, null);
    }

    /**
     * Gets boolean attribute SortAmount
     *
     * @return boolean - the attribute value
     */
    public boolean getSortAmount()
    {
        return getBoolAttribute(AttributeName.SORTAMOUNT, null, false);
    }

    /**
     * Sets attribute Sorting
     *
     * @param value the value to set the attribute to
     */
    public void setSorting(JDFIntegerRangeList value)
    {
        setAttribute(AttributeName.SORTING, value.toString(), null);
    }


    /**
     * Gets range attribute Sorting
     *
     * @return JDFIntegerRangeList - the attribute value
     */
    public JDFIntegerRangeList getSorting()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFIntegerRangeList nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.SORTING, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFIntegerRangeList(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }
        return nPlaceHolder;
    }

    /**
     * Appends new SpawnID token ('value') to the list of values of SpawnIDs attribute,
     * if it is not yet in the list
     *
     * @param value the SpawnID token to append
     */
    public void appendSpawnIDs(String value)
    {
        appendAttribute(AttributeName.SPAWNIDS, value, null, JDFConstants.BLANK, true);
    }

    /**
     * Removes SpawnID token ('value') from the list of values of SpawnIDs attribute, if it is in the list
     *
     * @param value the SpawnID token to remove from the NMTOKENS list
     * @return int - the number of removed tokens
     */
    public int removeFromSpawnIDs(String value)
    {
        return removeFromAttribute(AttributeName.SPAWNIDS, value, null, JDFConstants.BLANK, -1);
    }

    /**
     * Gets string attribute SpawnIDs
     *
     * @param bInherit if true, searches through all leaves, else searches only this leaf/node
     * @return VString the vector of SpawnIDs
     *
     * @default getSpawnIDs(true)
     */
    public VString getSpawnIDs(boolean bInherit)
    {
        final VString vStrIDs = new VString();
        final String attribute = bInherit ?
                getAttribute(AttributeName.SPAWNIDS,null,null) :
                getAttribute_KElement(AttributeName.SPAWNIDS,null,null);

              vStrIDs.setAllStrings(attribute, JDFConstants.BLANK);

        return vStrIDs;
    }

    /**
     * Sets attribute SpawnIDs
     *
     * @param vStr the value to set the attribute to
     */
    public void setSpawnIDs(VString vStr)
    {
        setAttribute(AttributeName.SPAWNIDS, StringUtil.setvString(vStr,JDFConstants.BLANK,null,null), null);
    }


    /**
     * Sets attribute SpawnStatus
     *
     * @param s enumeration value of the attribute SpawnStatus to be set
     */
    public void setSpawnStatus(EnumSpawnStatus s)
    {
        setAttribute( AttributeName.SPAWNSTATUS,  s.getName(), null);
    }

    /**
     * Gets typesafe enumerated value of attribute SpawnStatus
     *
     * @return EnumSpawnStatus - attribute enumeration value
     */
    public EnumSpawnStatus getSpawnStatus()
    {
        return EnumSpawnStatus.getEnum(getAttribute(AttributeName.SPAWNSTATUS,
                null,EnumSpawnStatus.NotSpawned.getName()));
    }

    /**
     * Sets attribute Status
     *
     * @param value	enumeration value of the attribute Status to be set
     * @deprecated 	use setResStatus(value, false)
     */
    public void setStatus(EnumResStatus value)
    {
        setAttribute(AttributeName.STATUS, value.getName(), null);
    }

    /**
     * Sets attribute Status
     *
     * @param value         enumeration value of the attribute Status to be set
     * @param bCleanLeaves if true, remove Status attribute from any child leaves below this
     * @deprecated use setResStatus(value, bCleanLeaves)
     */
    public void setStatus(EnumResStatus value, boolean bCleanLeaves)
    {
        setResStatus(value,bCleanLeaves);
    }

    /**
     * Gets typesafe enumerated value of attribute Status
     *
     * @param bRecurseRefs if bRecurseRefs is set, also recurse into all resources
     *                     linked by rRefs and return the minimum status
     * @return EnumResStatus attribute enumeration value
     *
     * @default getStatus(false)
     * @deprecated use getResStatus(bRecurseRefs)
     */
    public JDFResource.EnumResStatus getStatus(boolean bRecurseRefs)
    {
        return getResStatus(bRecurseRefs);
    }

    /**
     * Sets attribute Status
     *
     * @param value        enumeration value of the attribute Status to be set
     * @param bCleanLeaves if true, remove Status attribute from any child leaves below this
     *
     * @default setResStatus(value, false)
     */
    public void setResStatus(EnumResStatus value, boolean bCleanLeaves)
    {
        if (bCleanLeaves)
        {
            this.removeAttributeFromLeaves(AttributeName.STATUS, null);
        }
        setAttribute(AttributeName.STATUS, value.getName(), null);
    }

    /**
     * Gets typesafe enumerated value of attribute Status
     *
     * @param bRecurseRefs if bRecurseRefs is set, also recurse into all resources
     *                     linked by rRefs and return the minimum status
     * @return EnumResStatus - attribute enumeration value
     *
     * @default getResStatus(false)
     */
    public JDFResource.EnumResStatus getResStatus(boolean bRecurseRefs)
    {
        if (bRecurseRefs)
        {
            EnumResStatus ret = getResStatus(false);
            final VElement v = getvHRefRes(true,true);

            for (int i = 0; i < v.size(); i++)
            {
                final JDFResource rs = (JDFResource) v.elementAt(i);
                final EnumResStatus rss = rs.getResStatus(false);
                if (rss != null)
                {
                    if ((ret == null) || (rss.getValue() < ret.getValue()))
                    {
                        ret = rss;
                    }
                }
            }

            return ret;
        }

        // local value
        return EnumResStatus.getEnum(getAttribute(AttributeName.STATUS, null, null));
    }

    /**
     * Sets attribute TileID
     *
     * @param value the value to set the attribute to
     */
    public void setTileID(JDFXYPair value)
    {
        setAttribute(AttributeName.TILEID, value.toString());
    }

    /**
     * Gets XYPair attribute TileID
     *
     * @return JDFXYPair - the attribute value
     */
    public JDFXYPair getTileID()
    {
        String strAttrName = JDFConstants.EMPTYSTRING;
        JDFXYPair nPlaceHolder = null;
        strAttrName = getAttribute(AttributeName.TILEID, null, JDFConstants.EMPTYSTRING);
        try
        {
            nPlaceHolder = new JDFXYPair(strAttrName);
        }
        catch (DataFormatException e)
        {
            //do nothing
        }

        return nPlaceHolder;
    }

    /**
     * Sets attribute Unit
     *
     * @param value value to set the attribute to
     */
    public void setUnit(String value)
    {
        setAttribute(AttributeName.UNIT, value);
    }

    /**
     * Gets string attribute Unit
     *
     * @return String - the attribute value
     */
    public String getUnit()
    {
        return getAttribute(AttributeName.UNIT);
    }

    /**
     * Sets attribute UpdateID
     *
     * @param value value to set the attribute to
     */
    public void setUpdateID(String value)
    {
        setAttribute(AttributeName.UPDATEID, value, null);
    }

    /**
     * Gets string attribute UpdateID
     *
     * @return String - the attribute value
     */
    public String getUpdateID()
    {
        return getAttribute(AttributeName.UPDATEID, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * Sets attribute WebName
     *
     * @param value the value to set the attribute to
     */
    public void setWebName(String value)
    {
        setAttribute(AttributeName.WEBNAME, value);
    }

    /**
     * Gets string attribute WebName
     *
     * @return String - the attribute value
     */
    public String getWebName()
    {
        return getAttribute(AttributeName.WEBNAME, null, JDFConstants.EMPTYSTRING);
    }

    /**
     * Sets attribute WebProduct
     *
     * @param value the value to set the attribute to
     */
    public void setWebProduct(String value)
    {
        setAttribute(AttributeName.WEBPRODUCT, value);
    }
    /**
     * Gets string attribute WebProduct
     *
     * @return String the attribute value
     */
    public String getWebProduct()
    {
        return getAttribute(AttributeName.WEBPRODUCT, null, JDFConstants.EMPTYSTRING);
    }
    /**
     * Sets attribute WebSetup
     *
     * @param value the value to set the attribute to
     */
    public void setWebSetup(String value)
    {
        setAttribute(AttributeName.WEBSETUP, value);
    }
    /**
     * Gets string attribute WebSetup
     *
     * @return String the attribute value
     */
    public String getWebSetup()
    {
        return getAttribute(AttributeName.WEBSETUP, null, JDFConstants.EMPTYSTRING);
    }
    /**
     * Recursively adds the partition leaves defined in vPartMap
     *
     * @param vPartMap    the vector of maps of part keys
     * @param vPartIDKeys the vector of partIDKeys strings of the resource.
     *   If empty (the default) the Resource PartIDKeys attribute is used
     * @return VElement - vector of newly created partitions
     *
     * @throws JDFException if there are in the partMap not matching partitions
     * @throws JDFException if there is an attempt to fill non-matching partIDKeys
     * @throws JDFException if by adding of last partition key there is either non-continuous partmap or left more than one key
     *
     * @default createPartitions(vPartMap, VString.emptyVector)
     */
    public VElement createPartitions(VJDFAttributeMap vPartMap, VString vPartIDKeys)
    {
        VElement v = new VElement();
        VString tmp=new VString();
        for (int i = 0; i < vPartMap.size(); i++)
        {
            JDFAttributeMap map = vPartMap.elementAt(i);
            VElement vExist=getPartitionVector(map, null);
            if(vExist.isEmpty())
            {
                tmp.clear();
                for(int j=0;j<vPartIDKeys.size();j++)
                {
                    if (map.containsKey(vPartIDKeys.elementAt(j)))
                    {
                        tmp.add(vPartIDKeys.elementAt(j));
                    }
                }
                v.add(getCreatePartition(map, tmp));
            }
            else
            {
                v.addAll(vExist);
            }
        }

        return v;
    }

    /**
     * Sets attribute ID
     *
     * @param value value to set the attribute to
     */
    public void setID(String value)
    {
        setAttribute(AttributeName.ID, value, null);
    }

    /**
     * Gets string attribute ID
     *
     * @return String - the attribute value
     */
    public String getID()
    {
        return this.getAttribute(AttributeName.ID, null, JDFConstants.EMPTYSTRING);
    }


    /**
     * calculate a new level based on the status
     *
     * @param level
     * @param bForce
     * @return EnumValidationLevel - the modified level
     */
    private EnumValidationLevel incompleteLevel(EnumValidationLevel level, boolean bForce)
    {
        EnumResStatus es = getResStatus(false);
        if ((es == EnumResStatus.Incomplete) || isResourceUpdate() || bForce)
        {
            level=EnumValidationLevel.incompleteLevel(level);
        }
        return level;
    }


    /**
     * Validator of 'this'
     *
     * @param level the valdation level
     * @return boolean - true, if 'this' is valid
     */
    public boolean isValid(EnumValidationLevel level)
    {
        boolean bRet = true;

        // it is supposed to be incomplete -> don't check for completeness
        level = incompleteLevel(level,false);

        final boolean bLeaf = isLeaf();
        final EnumPartUsage partUsage = getPartUsage();
        boolean bForceIncomplete=!(partUsage == EnumPartUsage.Implicit)||(partUsage == EnumPartUsage.Sparse);
        if(bLeaf)
        {
            if (!super.isValid(level))
            {
                return false;
            }
        }
        else if (isResourceUpdate())
        {
            final JDFResource r = getResourceRoot();
            if (r == null)
            { // tbd details of customerinfo + nodeinfo res elements
                return true;
            }
        }
        else
        {
            if (this.getInvalidAttributes(incompleteLevel(level,bForceIncomplete), true, 1).size() > 0)
            {
                return false;
            }
 //           final VElement v = getLeaves(false);
            final VElement v = getChildElementVector_KElement(getNodeName(), null, null, true, 0);
            for (int i = 0; i < v.size(); i++)
            {
                if (!((JDFResource)v.elementAt(i)).isValid(level))
                {
                    return false;
                }
            }
        }

        if (!isResourceRootRoot())
        {
            // PartIDKeys is only valid in the root
            if (hasAttribute_KElement(AttributeName.PARTIDKEYS, null, false))
            {
                return false;
            }

            if (!isResourceElement())
            {
                if (hasAttribute_KElement(AttributeName.CLASS, null, false))
                {
                    return false;
                }
            }
            // if partusage=implicit, the root must also be complete and valid by itself
        }
        else if( !bLeaf && !bForceIncomplete)
        {
            if(!super.isValid(level))
            {
                return false;
            }
        }
        return bRet;
    }


    /**
     * Typesafe validator. Gets a vector of invalid attributes
     *
     * @param level flag whether incomplete elements are valid
     * @param bIgnorePrivate if true, do not validate attributes in private name spaces
     * @param nMax maximum size of the returned vector. Stop validation after nMax invalid attributes
     *
     * @return vWString a vector of invalid attributes
     *
     * @default getInvalidAttributes(EnumValidationLevel.Complete, true, 9999999)
     */
    public VString getInvalidAttributes(
            EnumValidationLevel level,
            boolean bIgnorePrivate,
            int nMax)
    {
        final VString vAtts = super.getInvalidAttributes(level, bIgnorePrivate, nMax);

        int n = vAtts.size();
        if (n >= nMax)
        {
            return vAtts;
        }

        // added class check here
        if(!validResourceClass(level))
        {
            vAtts.appendUnique(AttributeName.CLASS);
            if (++n >= nMax)
            {
                return vAtts;
            }
        }
        // now check whether they are in PartIDKeys

        if(!isResourceElement())
        {
            final JDFResource root=getResourceRoot();
            final VString partIDKeys=root.getPartIDKeys();
            final int size = partIDKeys.size();
            for(int i=0;i<size;i++)
            {
                final String keyName = partIDKeys.stringAt(i);
                EnumPartIDKey nxt=EnumPartIDKey.getEnum(keyName);
                if (!consistentPartIDKeys(nxt,root,partIDKeys))
                {
                    vAtts.appendUnique(keyName);
                    if (++n >= nMax)
                    {
                        return vAtts;
                    }
                }
            }
        }
        if(!EnumValidationLevel.isNoWarn(level) && isResourceRoot())
        {
            EnumPartUsage pu=getPartUsage();
            if(EnumPartUsage.Sparse.equals(pu) && EnumVersion.Version_1_3.isGreater(getVersion(true)))
                vAtts.add(AttributeName.PARTUSAGE); 
        }
           

        return vAtts;
    }


    /**
     * deletes this if it is no longer linked by either resource refs or resource links
     * @return true if this has been deleted
     */
    public boolean deleteUnLinked()
    {
        boolean bRet=false;
        VElement vLinks = getLinksAndRefs(true,true);

        if (vLinks==null)
        {
            deleteNode();
            bRet=true;
        }
        return bRet;
    }


    /**
     * @return the autoAgent
     */
    public static boolean getAutoAgent()
    {
        return autoAgent;
    }


    /**
     * @param autoAgent the autoAgent to set
     */
    public static void setAutoAgent(boolean _autoAgent)
    {
        JDFResource.autoAgent = _autoAgent;
    }

}
