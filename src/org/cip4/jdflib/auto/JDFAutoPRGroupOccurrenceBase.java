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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.zip.DataFormatException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AtrInfoTable;
import org.cip4.jdflib.core.AttributeInfo;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFRectangle;
import org.cip4.jdflib.datatypes.JDFXYPair;
import org.cip4.jdflib.util.JDFDate;
    /*
    *****************************************************************************
    class JDFAutoPRGroupOccurrenceBase : public JDFElement

    *****************************************************************************
    */

public abstract class JDFAutoPRGroupOccurrenceBase extends JDFElement
{

    private static final long serialVersionUID = 1L;

    private static AtrInfoTable[] atrInfoTable = new AtrInfoTable[125];
    static
    {
        atrInfoTable[0] = new AtrInfoTable(AttributeName.ANNOTATIONPRINTFLAG, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[1] = new AtrInfoTable(AttributeName.ANNOTATIONTYPE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[2] = new AtrInfoTable(AttributeName.TRAPNETANNOTATIONPDFX, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[3] = new AtrInfoTable(AttributeName.BOUNDINGBOX, 0x33333333, AttributeInfo.EnumAttributeType.rectangle, null, null);
        atrInfoTable[4] = new AtrInfoTable(AttributeName.DIFFERENTBOXSIZE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumDifferentBoxSize.getEnum(0), null);
        atrInfoTable[5] = new AtrInfoTable(AttributeName.INSIDEBOX, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[6] = new AtrInfoTable(AttributeName.OUTSIDEBOX, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[7] = new AtrInfoTable(AttributeName.CLASSNAME, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[8] = new AtrInfoTable(AttributeName.PROPERTYLIST, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, EnumPropertyList.getEnum(0), null);
        atrInfoTable[9] = new AtrInfoTable(AttributeName.ALIASSEPARATIONS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[10] = new AtrInfoTable(AttributeName.AMBIGUOUSSEPARATIONS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[11] = new AtrInfoTable(AttributeName.INKCOVERAGE, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[12] = new AtrInfoTable(AttributeName.SEPARATIONLIST, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[13] = new AtrInfoTable(AttributeName.AUTHOR, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[14] = new AtrInfoTable(AttributeName.BINDING, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumBinding.getEnum(0), null);
        atrInfoTable[15] = new AtrInfoTable(AttributeName.CREATIONDATE, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[16] = new AtrInfoTable(AttributeName.CREATIONDATEINDOCUMENT, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[17] = new AtrInfoTable(AttributeName.CREATIONID, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[18] = new AtrInfoTable(AttributeName.CREATOR, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[19] = new AtrInfoTable(AttributeName.DOCUMENTCOMPRESSION, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, EnumDocumentCompression.getEnum(0), null);
        atrInfoTable[20] = new AtrInfoTable(AttributeName.DOCUMENTCORRUPTION, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[21] = new AtrInfoTable(AttributeName.DOCUMENTENCODING, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumDocumentEncoding.getEnum(0), null);
        atrInfoTable[22] = new AtrInfoTable(AttributeName.DOCUMENTISGOODCOMPRESSION, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[23] = new AtrInfoTable(AttributeName.ENCRYPTEDDOCUMENT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[24] = new AtrInfoTable(AttributeName.ENCRYPTIONFILTER, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[25] = new AtrInfoTable(AttributeName.ENCRYPTIONLENGTH, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[26] = new AtrInfoTable(AttributeName.ENCRYPTIONRESTRICTIONS, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[27] = new AtrInfoTable(AttributeName.ENCRYPTIONSUBFILTER, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[28] = new AtrInfoTable(AttributeName.ENCRYPTIONV, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[29] = new AtrInfoTable(AttributeName.FILENAME, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[30] = new AtrInfoTable(AttributeName.FILESIZE, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[31] = new AtrInfoTable(AttributeName.KEYWORDS, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[32] = new AtrInfoTable(AttributeName.LINEARIZED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[33] = new AtrInfoTable(AttributeName.MODIFICATIONDATE, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[34] = new AtrInfoTable(AttributeName.MODIFICATIONDATEINDOCUMENT, 0x33333333, AttributeInfo.EnumAttributeType.dateTime, null, null);
        atrInfoTable[35] = new AtrInfoTable(AttributeName.MODIFICATIONID, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[36] = new AtrInfoTable(AttributeName.NUMBEROFPAGES, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[37] = new AtrInfoTable(AttributeName.OUTPUTINTENTCOLORSPACE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, "None");
        atrInfoTable[38] = new AtrInfoTable(AttributeName.OUTPUTINTENTSTANDARD, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[39] = new AtrInfoTable(AttributeName.PAGESHAVESAMEORIENTATION, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[40] = new AtrInfoTable(AttributeName.PDFXVERSION, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[41] = new AtrInfoTable(AttributeName.DOCUMENTPDLTYPE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[42] = new AtrInfoTable(AttributeName.PDLVERSION, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[43] = new AtrInfoTable(AttributeName.PRODUCER, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[44] = new AtrInfoTable(AttributeName.SEPARATIONFLAG, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[45] = new AtrInfoTable(AttributeName.SUBJECT, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[46] = new AtrInfoTable(AttributeName.TITLE, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[47] = new AtrInfoTable(AttributeName.TRAPPEDKEY, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumTrappedKey.getEnum(0), null);
        atrInfoTable[48] = new AtrInfoTable(AttributeName.FILLCOLORNAME, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[49] = new AtrInfoTable(AttributeName.FILLCOLORTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumFillColorType.getEnum(0), null);
        atrInfoTable[50] = new AtrInfoTable(AttributeName.HASFILLCOLOR, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[51] = new AtrInfoTable(AttributeName.EMBEDDINGRESTRICTIONFLAG, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[52] = new AtrInfoTable(AttributeName.FONTCORRUPTED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[53] = new AtrInfoTable(AttributeName.FONTCREATOR, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[54] = new AtrInfoTable(AttributeName.FONTEMBEDDED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[55] = new AtrInfoTable(AttributeName.FONTISSTANDARDLATIN, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[56] = new AtrInfoTable(AttributeName.FONTNAME, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[57] = new AtrInfoTable(AttributeName.FONTNOTUSED, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[58] = new AtrInfoTable(AttributeName.FONTSUBSET, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[59] = new AtrInfoTable(AttributeName.FONTTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumFontType.getEnum(0), "Other");
        atrInfoTable[60] = new AtrInfoTable(AttributeName.FONTVENDOR, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[61] = new AtrInfoTable(AttributeName.ISFONTSCREENONLY, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[62] = new AtrInfoTable(AttributeName.PSFONTNAME, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[63] = new AtrInfoTable(AttributeName.ALPHAISSHAPE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[64] = new AtrInfoTable(AttributeName.ALTERNATECOLORSPACE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumAlternateColorSpace.getEnum(0), null);
        atrInfoTable[65] = new AtrInfoTable(AttributeName.BELONGSTOANNOTATION, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[66] = new AtrInfoTable(AttributeName.BLACKGENERATION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumBlackGeneration.getEnum(0), null);
        atrInfoTable[67] = new AtrInfoTable(AttributeName.BLENDMODE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[68] = new AtrInfoTable(AttributeName.COLORSPACE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumColorSpace.getEnum(0), null);
        atrInfoTable[69] = new AtrInfoTable(AttributeName.EMBEDDEDPS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[70] = new AtrInfoTable(AttributeName.FLATNESS, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[71] = new AtrInfoTable(AttributeName.HASSOFTMASK, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[72] = new AtrInfoTable(AttributeName.HALFTONE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[73] = new AtrInfoTable(AttributeName.HALFTONEPHASE, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[74] = new AtrInfoTable(AttributeName.HASCOLORLUT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[75] = new AtrInfoTable(AttributeName.NUMBEROFCOLORSINLUT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[76] = new AtrInfoTable(AttributeName.OVERPRINTFLAG, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[77] = new AtrInfoTable(AttributeName.OVERPRINTMODE, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[78] = new AtrInfoTable(AttributeName.RENDERINGINTENT, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[79] = new AtrInfoTable(AttributeName.SMOOTHNESS, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[80] = new AtrInfoTable(AttributeName.TRANSFERFUNCTION, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumTransferFunction.getEnum(0), null);
        atrInfoTable[81] = new AtrInfoTable(AttributeName.TRANSPARENCYFLAG, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[82] = new AtrInfoTable(AttributeName.UNDERCOLORREMOVAL, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumUnderColorRemoval.getEnum(0), null);
        atrInfoTable[83] = new AtrInfoTable(AttributeName.ALTERNATEIMAGES, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[84] = new AtrInfoTable(AttributeName.BITSPERSAMPLE, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[85] = new AtrInfoTable(AttributeName.COMPRESSIONRATIO, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[86] = new AtrInfoTable(AttributeName.COMPRESSIONTYPES, 0x33333333, AttributeInfo.EnumAttributeType.enumerations, EnumCompressionTypes.getEnum(0), null);
        atrInfoTable[87] = new AtrInfoTable(AttributeName.EFFECTIVERESOLUTION, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[88] = new AtrInfoTable(AttributeName.ESTIMATEDJPEGQUALITY, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[89] = new AtrInfoTable(AttributeName.IMAGEFLIPPED, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumImageFlipped.getEnum(0), null);
        atrInfoTable[90] = new AtrInfoTable(AttributeName.IMAGEMASKTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumImageMaskType.getEnum(0), null);
        atrInfoTable[91] = new AtrInfoTable(AttributeName.IMAGEROTATION, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[92] = new AtrInfoTable(AttributeName.IMAGESCALINGRATIO, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[93] = new AtrInfoTable(AttributeName.IMAGESKEW, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[94] = new AtrInfoTable(AttributeName.ORIGINALRESOLUTION, 0x33333333, AttributeInfo.EnumAttributeType.XYPair, null, null);
        atrInfoTable[95] = new AtrInfoTable(AttributeName.PIXELHEIGHT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[96] = new AtrInfoTable(AttributeName.PIXELWIDTH, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[97] = new AtrInfoTable(AttributeName.COUNT, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[98] = new AtrInfoTable(AttributeName.PAGEBOXTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumPageBoxType.getEnum(0), null);
        atrInfoTable[99] = new AtrInfoTable(AttributeName.BLANKPAGE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[100] = new AtrInfoTable(AttributeName.BLENDCOLORSPACE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumBlendColorSpace.getEnum(0), null);
        atrInfoTable[101] = new AtrInfoTable(AttributeName.PAGEHASUNKNOWNOBJECTS, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[102] = new AtrInfoTable(AttributeName.REVERSEPAGENUMBER, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
        atrInfoTable[103] = new AtrInfoTable(AttributeName.PDLTYPE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[104] = new AtrInfoTable(AttributeName.EXTERNALREFERENCEMISSING, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[105] = new AtrInfoTable(AttributeName.HASEXTERNALREFERENCE, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[106] = new AtrInfoTable(AttributeName.HASOPI, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[107] = new AtrInfoTable(AttributeName.OPIMISSING, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[108] = new AtrInfoTable(AttributeName.OPITYPE, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKEN, null, null);
        atrInfoTable[109] = new AtrInfoTable(AttributeName.OPIVERSION, 0x33333333, AttributeInfo.EnumAttributeType.NMTOKENS, null, null);
        atrInfoTable[110] = new AtrInfoTable(AttributeName.SHADINGTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumShadingType.getEnum(0), null);
        atrInfoTable[111] = new AtrInfoTable(AttributeName.HASSTROKECOLOR, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[112] = new AtrInfoTable(AttributeName.STROKEALTERNATECOLORSPACE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumStrokeAlternateColorSpace.getEnum(0), null);
        atrInfoTable[113] = new AtrInfoTable(AttributeName.STROKECOLORNAME, 0x33333333, AttributeInfo.EnumAttributeType.string, null, null);
        atrInfoTable[114] = new AtrInfoTable(AttributeName.STROKECOLORSPACE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumStrokeColorSpace.getEnum(0), null);
        atrInfoTable[115] = new AtrInfoTable(AttributeName.STROKECOLORTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumStrokeColorType.getEnum(0), null);
        atrInfoTable[116] = new AtrInfoTable(AttributeName.STROKEOVERPRINTFLAG, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[117] = new AtrInfoTable(AttributeName.STROKESHADINGTYPE, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumStrokeShadingType.getEnum(0), null);
        atrInfoTable[118] = new AtrInfoTable(AttributeName.STROKETHICKNESS, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[119] = new AtrInfoTable(AttributeName.CHARACTERPROBLEM, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumCharacterProblem.getEnum(0), null);
        atrInfoTable[120] = new AtrInfoTable(AttributeName.MISSINGPRINTERFONT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[121] = new AtrInfoTable(AttributeName.MISSINGSCREENFONT, 0x33333333, AttributeInfo.EnumAttributeType.boolean_, null, null);
        atrInfoTable[122] = new AtrInfoTable(AttributeName.TEXTSIZE, 0x33333333, AttributeInfo.EnumAttributeType.double_, null, null);
        atrInfoTable[123] = new AtrInfoTable(AttributeName.USEARTIFICIALTEXTEFFECT, 0x33333333, AttributeInfo.EnumAttributeType.enumeration, EnumUseArtificialTextEffect.getEnum(0), null);
        atrInfoTable[124] = new AtrInfoTable(AttributeName.NUMBEROFPATHPOINTS, 0x33333333, AttributeInfo.EnumAttributeType.integer, null, null);
    }
    
    protected AttributeInfo getTheAttributeInfo()
    {
        return super.getTheAttributeInfo().updateReplace(atrInfoTable);
    }



    /**
     * Constructor for JDFAutoPRGroupOccurrenceBase
     * @param myOwnerDocument
     * @param qualifiedName
     */
    protected JDFAutoPRGroupOccurrenceBase(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPRGroupOccurrenceBase
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    protected JDFAutoPRGroupOccurrenceBase(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFAutoPRGroupOccurrenceBase
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    protected JDFAutoPRGroupOccurrenceBase(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }


    public String toString()
    {
        return " JDFAutoPRGroupOccurrenceBase[  --> " + super.toString() + " ]";
    }


        /**
        * Enumeration strings for DifferentBoxSize
        */

        public static class EnumDifferentBoxSize extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDifferentBoxSize(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDifferentBoxSize getEnum(String enumName)
            {
                return (EnumDifferentBoxSize) getEnum(EnumDifferentBoxSize.class, enumName);
            }

            public static EnumDifferentBoxSize getEnum(int enumValue)
            {
                return (EnumDifferentBoxSize) getEnum(EnumDifferentBoxSize.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDifferentBoxSize.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDifferentBoxSize.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDifferentBoxSize.class);
            }

            public static final EnumDifferentBoxSize ArtBox = new EnumDifferentBoxSize("ArtBox");
            public static final EnumDifferentBoxSize BleedBox = new EnumDifferentBoxSize("BleedBox");
            public static final EnumDifferentBoxSize CropBox = new EnumDifferentBoxSize("CropBox");
            public static final EnumDifferentBoxSize MarginsBox = new EnumDifferentBoxSize("MarginsBox");
            public static final EnumDifferentBoxSize MediaBox = new EnumDifferentBoxSize("MediaBox");
            public static final EnumDifferentBoxSize SlugBox = new EnumDifferentBoxSize("SlugBox");
            public static final EnumDifferentBoxSize TrimBox = new EnumDifferentBoxSize("TrimBox");
        }      



        /**
        * Enumeration strings for PropertyList
        */

        public static class EnumPropertyList extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPropertyList(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPropertyList getEnum(String enumName)
            {
                return (EnumPropertyList) getEnum(EnumPropertyList.class, enumName);
            }

            public static EnumPropertyList getEnum(int enumValue)
            {
                return (EnumPropertyList) getEnum(EnumPropertyList.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPropertyList.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPropertyList.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPropertyList.class);
            }

            public static final EnumPropertyList Annotation = new EnumPropertyList("Annotation");
            public static final EnumPropertyList Box = new EnumPropertyList("Box");
            public static final EnumPropertyList Class = new EnumPropertyList("Class");
            public static final EnumPropertyList Colorant = new EnumPropertyList("Colorant");
            public static final EnumPropertyList Document = new EnumPropertyList("Document");
            public static final EnumPropertyList Fill = new EnumPropertyList("Fill");
            public static final EnumPropertyList Font = new EnumPropertyList("Font");
            public static final EnumPropertyList Graphic = new EnumPropertyList("Graphic");
            public static final EnumPropertyList Image = new EnumPropertyList("Image");
            public static final EnumPropertyList Logical = new EnumPropertyList("Logical");
            public static final EnumPropertyList Page = new EnumPropertyList("Page");
            public static final EnumPropertyList PageBox = new EnumPropertyList("PageBox");
            public static final EnumPropertyList PDLObject = new EnumPropertyList("PDLObject");
            public static final EnumPropertyList Reference = new EnumPropertyList("Reference");
            public static final EnumPropertyList Shading = new EnumPropertyList("Shading");
            public static final EnumPropertyList Stroke = new EnumPropertyList("Stroke");
            public static final EnumPropertyList Text = new EnumPropertyList("Text");
            public static final EnumPropertyList Vector = new EnumPropertyList("Vector");
        }      



        /**
        * Enumeration strings for Binding
        */

        public static class EnumBinding extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumBinding(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumBinding getEnum(String enumName)
            {
                return (EnumBinding) getEnum(EnumBinding.class, enumName);
            }

            public static EnumBinding getEnum(int enumValue)
            {
                return (EnumBinding) getEnum(EnumBinding.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumBinding.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumBinding.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumBinding.class);
            }

            public static final EnumBinding Left = new EnumBinding("Left");
            public static final EnumBinding Right = new EnumBinding("Right");
        }      



        /**
        * Enumeration strings for DocumentCompression
        */

        public static class EnumDocumentCompression extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDocumentCompression(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDocumentCompression getEnum(String enumName)
            {
                return (EnumDocumentCompression) getEnum(EnumDocumentCompression.class, enumName);
            }

            public static EnumDocumentCompression getEnum(int enumValue)
            {
                return (EnumDocumentCompression) getEnum(EnumDocumentCompression.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDocumentCompression.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDocumentCompression.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDocumentCompression.class);
            }

            public static final EnumDocumentCompression ASCII85 = new EnumDocumentCompression("ASCII85");
            public static final EnumDocumentCompression ASCIIHex = new EnumDocumentCompression("ASCIIHex");
            public static final EnumDocumentCompression CCITT = new EnumDocumentCompression("CCITT");
            public static final EnumDocumentCompression JBIG2 = new EnumDocumentCompression("JBIG2");
            public static final EnumDocumentCompression JPEG = new EnumDocumentCompression("JPEG");
            public static final EnumDocumentCompression JPEG2000 = new EnumDocumentCompression("JPEG2000");
            public static final EnumDocumentCompression LZW = new EnumDocumentCompression("LZW");
            public static final EnumDocumentCompression None = new EnumDocumentCompression("None");
            public static final EnumDocumentCompression RunLength = new EnumDocumentCompression("RunLength");
            public static final EnumDocumentCompression ZIP = new EnumDocumentCompression("ZIP");
        }      



        /**
        * Enumeration strings for DocumentEncoding
        */

        public static class EnumDocumentEncoding extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumDocumentEncoding(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumDocumentEncoding getEnum(String enumName)
            {
                return (EnumDocumentEncoding) getEnum(EnumDocumentEncoding.class, enumName);
            }

            public static EnumDocumentEncoding getEnum(int enumValue)
            {
                return (EnumDocumentEncoding) getEnum(EnumDocumentEncoding.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumDocumentEncoding.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumDocumentEncoding.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumDocumentEncoding.class);
            }

            public static final EnumDocumentEncoding ASCII = new EnumDocumentEncoding("ASCII");
            public static final EnumDocumentEncoding Binary = new EnumDocumentEncoding("Binary");
        }      



        /**
        * Enumeration strings for TrappedKey
        */

        public static class EnumTrappedKey extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumTrappedKey(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumTrappedKey getEnum(String enumName)
            {
                return (EnumTrappedKey) getEnum(EnumTrappedKey.class, enumName);
            }

            public static EnumTrappedKey getEnum(int enumValue)
            {
                return (EnumTrappedKey) getEnum(EnumTrappedKey.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumTrappedKey.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumTrappedKey.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumTrappedKey.class);
            }

            public static final EnumTrappedKey Unknown = new EnumTrappedKey("Unknown");
            public static final EnumTrappedKey True = new EnumTrappedKey("True");
            public static final EnumTrappedKey False = new EnumTrappedKey("False");
        }      



        /**
        * Enumeration strings for FillColorType
        */

        public static class EnumFillColorType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFillColorType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFillColorType getEnum(String enumName)
            {
                return (EnumFillColorType) getEnum(EnumFillColorType.class, enumName);
            }

            public static EnumFillColorType getEnum(int enumValue)
            {
                return (EnumFillColorType) getEnum(EnumFillColorType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFillColorType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFillColorType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFillColorType.class);
            }

            public static final EnumFillColorType CMYGray = new EnumFillColorType("CMYGray");
            public static final EnumFillColorType CMYBlack = new EnumFillColorType("CMYBlack");
            public static final EnumFillColorType Other = new EnumFillColorType("Other");
            public static final EnumFillColorType PureBlack = new EnumFillColorType("PureBlack");
            public static final EnumFillColorType PureGray = new EnumFillColorType("PureGray");
            public static final EnumFillColorType RegistrationBlack = new EnumFillColorType("RegistrationBlack");
            public static final EnumFillColorType RegistrationGray = new EnumFillColorType("RegistrationGray");
            public static final EnumFillColorType RichBlack = new EnumFillColorType("RichBlack");
            public static final EnumFillColorType White = new EnumFillColorType("White");
        }      



        /**
        * Enumeration strings for FontType
        */

        public static class EnumFontType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumFontType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumFontType getEnum(String enumName)
            {
                return (EnumFontType) getEnum(EnumFontType.class, enumName);
            }

            public static EnumFontType getEnum(int enumValue)
            {
                return (EnumFontType) getEnum(EnumFontType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumFontType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumFontType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumFontType.class);
            }

            public static final EnumFontType CIDFontType0 = new EnumFontType("CIDFontType0");
            public static final EnumFontType CIDFontType1 = new EnumFontType("CIDFontType1");
            public static final EnumFontType CIDFontType2 = new EnumFontType("CIDFontType2");
            public static final EnumFontType CIDFontType3 = new EnumFontType("CIDFontType3");
            public static final EnumFontType CIDFontType4 = new EnumFontType("CIDFontType4");
            public static final EnumFontType OpenType = new EnumFontType("OpenType");
            public static final EnumFontType TrueType = new EnumFontType("TrueType");
            public static final EnumFontType Type0 = new EnumFontType("Type0");
            public static final EnumFontType Type1 = new EnumFontType("Type1");
            public static final EnumFontType Type1MultipleMaster = new EnumFontType("Type1MultipleMaster");
            public static final EnumFontType Type2C = new EnumFontType("Type2C");
            public static final EnumFontType Type3 = new EnumFontType("Type3");
            public static final EnumFontType PDFType3 = new EnumFontType("PDFType3");
            public static final EnumFontType Type42 = new EnumFontType("Type42");
            public static final EnumFontType Unknown = new EnumFontType("Unknown");
            public static final EnumFontType Other = new EnumFontType("Other");
        }      



        /**
        * Enumeration strings for AlternateColorSpace
        */

        public static class EnumAlternateColorSpace extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumAlternateColorSpace(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumAlternateColorSpace getEnum(String enumName)
            {
                return (EnumAlternateColorSpace) getEnum(EnumAlternateColorSpace.class, enumName);
            }

            public static EnumAlternateColorSpace getEnum(int enumValue)
            {
                return (EnumAlternateColorSpace) getEnum(EnumAlternateColorSpace.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumAlternateColorSpace.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumAlternateColorSpace.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumAlternateColorSpace.class);
            }

            public static final EnumAlternateColorSpace CalGray = new EnumAlternateColorSpace("CalGray");
            public static final EnumAlternateColorSpace CalRGB = new EnumAlternateColorSpace("CalRGB");
            public static final EnumAlternateColorSpace CIEBasedA = new EnumAlternateColorSpace("CIEBasedA");
            public static final EnumAlternateColorSpace CIEBasedABC = new EnumAlternateColorSpace("CIEBasedABC");
            public static final EnumAlternateColorSpace CIEBasedDEFG = new EnumAlternateColorSpace("CIEBasedDEFG");
            public static final EnumAlternateColorSpace DeviceCMYK = new EnumAlternateColorSpace("DeviceCMYK");
            public static final EnumAlternateColorSpace DeviceGray = new EnumAlternateColorSpace("DeviceGray");
            public static final EnumAlternateColorSpace DeviceN = new EnumAlternateColorSpace("DeviceN");
            public static final EnumAlternateColorSpace DeviceRGB = new EnumAlternateColorSpace("DeviceRGB");
            public static final EnumAlternateColorSpace ICCBased = new EnumAlternateColorSpace("ICCBased");
            public static final EnumAlternateColorSpace Lab = new EnumAlternateColorSpace("Lab");
        }      



        /**
        * Enumeration strings for BlackGeneration
        */

        public static class EnumBlackGeneration extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumBlackGeneration(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumBlackGeneration getEnum(String enumName)
            {
                return (EnumBlackGeneration) getEnum(EnumBlackGeneration.class, enumName);
            }

            public static EnumBlackGeneration getEnum(int enumValue)
            {
                return (EnumBlackGeneration) getEnum(EnumBlackGeneration.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumBlackGeneration.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumBlackGeneration.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumBlackGeneration.class);
            }

            public static final EnumBlackGeneration Identity = new EnumBlackGeneration("Identity");
            public static final EnumBlackGeneration Custom = new EnumBlackGeneration("Custom");
        }      



        /**
        * Enumeration strings for ColorSpace
        */

        public static class EnumColorSpace extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumColorSpace(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumColorSpace getEnum(String enumName)
            {
                return (EnumColorSpace) getEnum(EnumColorSpace.class, enumName);
            }

            public static EnumColorSpace getEnum(int enumValue)
            {
                return (EnumColorSpace) getEnum(EnumColorSpace.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumColorSpace.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumColorSpace.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumColorSpace.class);
            }

            public static final EnumColorSpace CalGray = new EnumColorSpace("CalGray");
            public static final EnumColorSpace CalRGB = new EnumColorSpace("CalRGB");
            public static final EnumColorSpace CIEBasedA = new EnumColorSpace("CIEBasedA");
            public static final EnumColorSpace CIEBasedABC = new EnumColorSpace("CIEBasedABC");
            public static final EnumColorSpace CIEBasedDEFG = new EnumColorSpace("CIEBasedDEFG");
            public static final EnumColorSpace DeviceCMYK = new EnumColorSpace("DeviceCMYK");
            public static final EnumColorSpace DeviceGray = new EnumColorSpace("DeviceGray");
            public static final EnumColorSpace DeviceN = new EnumColorSpace("DeviceN");
            public static final EnumColorSpace DeviceRGB = new EnumColorSpace("DeviceRGB");
            public static final EnumColorSpace ICCBased = new EnumColorSpace("ICCBased");
            public static final EnumColorSpace Lab = new EnumColorSpace("Lab");
        }      



        /**
        * Enumeration strings for TransferFunction
        */

        public static class EnumTransferFunction extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumTransferFunction(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumTransferFunction getEnum(String enumName)
            {
                return (EnumTransferFunction) getEnum(EnumTransferFunction.class, enumName);
            }

            public static EnumTransferFunction getEnum(int enumValue)
            {
                return (EnumTransferFunction) getEnum(EnumTransferFunction.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumTransferFunction.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumTransferFunction.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumTransferFunction.class);
            }

            public static final EnumTransferFunction Identity = new EnumTransferFunction("Identity");
            public static final EnumTransferFunction Custom = new EnumTransferFunction("Custom");
        }      



        /**
        * Enumeration strings for UnderColorRemoval
        */

        public static class EnumUnderColorRemoval extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumUnderColorRemoval(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumUnderColorRemoval getEnum(String enumName)
            {
                return (EnumUnderColorRemoval) getEnum(EnumUnderColorRemoval.class, enumName);
            }

            public static EnumUnderColorRemoval getEnum(int enumValue)
            {
                return (EnumUnderColorRemoval) getEnum(EnumUnderColorRemoval.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumUnderColorRemoval.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumUnderColorRemoval.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumUnderColorRemoval.class);
            }

            public static final EnumUnderColorRemoval Identity = new EnumUnderColorRemoval("Identity");
            public static final EnumUnderColorRemoval Custom = new EnumUnderColorRemoval("Custom");
        }      



        /**
        * Enumeration strings for CompressionTypes
        */

        public static class EnumCompressionTypes extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumCompressionTypes(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumCompressionTypes getEnum(String enumName)
            {
                return (EnumCompressionTypes) getEnum(EnumCompressionTypes.class, enumName);
            }

            public static EnumCompressionTypes getEnum(int enumValue)
            {
                return (EnumCompressionTypes) getEnum(EnumCompressionTypes.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumCompressionTypes.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumCompressionTypes.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumCompressionTypes.class);
            }

            public static final EnumCompressionTypes ASCII85 = new EnumCompressionTypes("ASCII85");
            public static final EnumCompressionTypes ASCIIHex = new EnumCompressionTypes("ASCIIHex");
            public static final EnumCompressionTypes CCITT = new EnumCompressionTypes("CCITT");
            public static final EnumCompressionTypes JBIG2 = new EnumCompressionTypes("JBIG2");
            public static final EnumCompressionTypes JPEG = new EnumCompressionTypes("JPEG");
            public static final EnumCompressionTypes JPEG2000 = new EnumCompressionTypes("JPEG2000");
            public static final EnumCompressionTypes LZW = new EnumCompressionTypes("LZW");
            public static final EnumCompressionTypes None = new EnumCompressionTypes("None");
            public static final EnumCompressionTypes RunLength = new EnumCompressionTypes("RunLength");
            public static final EnumCompressionTypes ZIP = new EnumCompressionTypes("ZIP");
        }      



        /**
        * Enumeration strings for ImageFlipped
        */

        public static class EnumImageFlipped extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumImageFlipped(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumImageFlipped getEnum(String enumName)
            {
                return (EnumImageFlipped) getEnum(EnumImageFlipped.class, enumName);
            }

            public static EnumImageFlipped getEnum(int enumValue)
            {
                return (EnumImageFlipped) getEnum(EnumImageFlipped.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumImageFlipped.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumImageFlipped.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumImageFlipped.class);
            }

            public static final EnumImageFlipped Horizontal = new EnumImageFlipped("Horizontal");
            public static final EnumImageFlipped Vertical = new EnumImageFlipped("Vertical");
            public static final EnumImageFlipped None = new EnumImageFlipped("None");
        }      



        /**
        * Enumeration strings for ImageMaskType
        */

        public static class EnumImageMaskType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumImageMaskType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumImageMaskType getEnum(String enumName)
            {
                return (EnumImageMaskType) getEnum(EnumImageMaskType.class, enumName);
            }

            public static EnumImageMaskType getEnum(int enumValue)
            {
                return (EnumImageMaskType) getEnum(EnumImageMaskType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumImageMaskType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumImageMaskType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumImageMaskType.class);
            }

            public static final EnumImageMaskType NoMask = new EnumImageMaskType("NoMask");
            public static final EnumImageMaskType BitmapMask = new EnumImageMaskType("BitmapMask");
            public static final EnumImageMaskType ColorKeyMask = new EnumImageMaskType("ColorKeyMask");
        }      



        /**
        * Enumeration strings for PageBoxType
        */

        public static class EnumPageBoxType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumPageBoxType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumPageBoxType getEnum(String enumName)
            {
                return (EnumPageBoxType) getEnum(EnumPageBoxType.class, enumName);
            }

            public static EnumPageBoxType getEnum(int enumValue)
            {
                return (EnumPageBoxType) getEnum(EnumPageBoxType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumPageBoxType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumPageBoxType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumPageBoxType.class);
            }

            public static final EnumPageBoxType ArtBox = new EnumPageBoxType("ArtBox");
            public static final EnumPageBoxType BleedBox = new EnumPageBoxType("BleedBox");
            public static final EnumPageBoxType CropBox = new EnumPageBoxType("CropBox");
            public static final EnumPageBoxType MarginsBox = new EnumPageBoxType("MarginsBox");
            public static final EnumPageBoxType MediaBox = new EnumPageBoxType("MediaBox");
            public static final EnumPageBoxType SlugBox = new EnumPageBoxType("SlugBox");
            public static final EnumPageBoxType TrimBox = new EnumPageBoxType("TrimBox");
        }      



        /**
        * Enumeration strings for BlendColorSpace
        */

        public static class EnumBlendColorSpace extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumBlendColorSpace(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumBlendColorSpace getEnum(String enumName)
            {
                return (EnumBlendColorSpace) getEnum(EnumBlendColorSpace.class, enumName);
            }

            public static EnumBlendColorSpace getEnum(int enumValue)
            {
                return (EnumBlendColorSpace) getEnum(EnumBlendColorSpace.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumBlendColorSpace.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumBlendColorSpace.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumBlendColorSpace.class);
            }

            public static final EnumBlendColorSpace CalGray = new EnumBlendColorSpace("CalGray");
            public static final EnumBlendColorSpace CalRGB = new EnumBlendColorSpace("CalRGB");
            public static final EnumBlendColorSpace CIEBasedA = new EnumBlendColorSpace("CIEBasedA");
            public static final EnumBlendColorSpace CIEBasedABC = new EnumBlendColorSpace("CIEBasedABC");
            public static final EnumBlendColorSpace CIEBasedDEFG = new EnumBlendColorSpace("CIEBasedDEFG");
            public static final EnumBlendColorSpace DeviceCMYK = new EnumBlendColorSpace("DeviceCMYK");
            public static final EnumBlendColorSpace DeviceGray = new EnumBlendColorSpace("DeviceGray");
            public static final EnumBlendColorSpace DeviceN = new EnumBlendColorSpace("DeviceN");
            public static final EnumBlendColorSpace DeviceRGB = new EnumBlendColorSpace("DeviceRGB");
            public static final EnumBlendColorSpace ICCBased = new EnumBlendColorSpace("ICCBased");
            public static final EnumBlendColorSpace Lab = new EnumBlendColorSpace("Lab");
        }      



        /**
        * Enumeration strings for ShadingType
        */

        public static class EnumShadingType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumShadingType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumShadingType getEnum(String enumName)
            {
                return (EnumShadingType) getEnum(EnumShadingType.class, enumName);
            }

            public static EnumShadingType getEnum(int enumValue)
            {
                return (EnumShadingType) getEnum(EnumShadingType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumShadingType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumShadingType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumShadingType.class);
            }

            public static final EnumShadingType Smooth = new EnumShadingType("Smooth");
            public static final EnumShadingType Vector = new EnumShadingType("Vector");
        }      



        /**
        * Enumeration strings for StrokeAlternateColorSpace
        */

        public static class EnumStrokeAlternateColorSpace extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumStrokeAlternateColorSpace(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumStrokeAlternateColorSpace getEnum(String enumName)
            {
                return (EnumStrokeAlternateColorSpace) getEnum(EnumStrokeAlternateColorSpace.class, enumName);
            }

            public static EnumStrokeAlternateColorSpace getEnum(int enumValue)
            {
                return (EnumStrokeAlternateColorSpace) getEnum(EnumStrokeAlternateColorSpace.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumStrokeAlternateColorSpace.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumStrokeAlternateColorSpace.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumStrokeAlternateColorSpace.class);
            }

            public static final EnumStrokeAlternateColorSpace CalGray = new EnumStrokeAlternateColorSpace("CalGray");
            public static final EnumStrokeAlternateColorSpace CalRGB = new EnumStrokeAlternateColorSpace("CalRGB");
            public static final EnumStrokeAlternateColorSpace CIEBasedA = new EnumStrokeAlternateColorSpace("CIEBasedA");
            public static final EnumStrokeAlternateColorSpace CIEBasedABC = new EnumStrokeAlternateColorSpace("CIEBasedABC");
            public static final EnumStrokeAlternateColorSpace CIEBasedDEFG = new EnumStrokeAlternateColorSpace("CIEBasedDEFG");
            public static final EnumStrokeAlternateColorSpace DeviceCMYK = new EnumStrokeAlternateColorSpace("DeviceCMYK");
            public static final EnumStrokeAlternateColorSpace DeviceGray = new EnumStrokeAlternateColorSpace("DeviceGray");
            public static final EnumStrokeAlternateColorSpace DeviceN = new EnumStrokeAlternateColorSpace("DeviceN");
            public static final EnumStrokeAlternateColorSpace DeviceRGB = new EnumStrokeAlternateColorSpace("DeviceRGB");
            public static final EnumStrokeAlternateColorSpace ICCBased = new EnumStrokeAlternateColorSpace("ICCBased");
            public static final EnumStrokeAlternateColorSpace Lab = new EnumStrokeAlternateColorSpace("Lab");
        }      



        /**
        * Enumeration strings for StrokeColorSpace
        */

        public static class EnumStrokeColorSpace extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumStrokeColorSpace(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumStrokeColorSpace getEnum(String enumName)
            {
                return (EnumStrokeColorSpace) getEnum(EnumStrokeColorSpace.class, enumName);
            }

            public static EnumStrokeColorSpace getEnum(int enumValue)
            {
                return (EnumStrokeColorSpace) getEnum(EnumStrokeColorSpace.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumStrokeColorSpace.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumStrokeColorSpace.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumStrokeColorSpace.class);
            }

            public static final EnumStrokeColorSpace CalGray = new EnumStrokeColorSpace("CalGray");
            public static final EnumStrokeColorSpace CalRGB = new EnumStrokeColorSpace("CalRGB");
            public static final EnumStrokeColorSpace CIEBasedA = new EnumStrokeColorSpace("CIEBasedA");
            public static final EnumStrokeColorSpace CIEBasedABC = new EnumStrokeColorSpace("CIEBasedABC");
            public static final EnumStrokeColorSpace CIEBasedDEFG = new EnumStrokeColorSpace("CIEBasedDEFG");
            public static final EnumStrokeColorSpace DeviceCMYK = new EnumStrokeColorSpace("DeviceCMYK");
            public static final EnumStrokeColorSpace DeviceGray = new EnumStrokeColorSpace("DeviceGray");
            public static final EnumStrokeColorSpace DeviceN = new EnumStrokeColorSpace("DeviceN");
            public static final EnumStrokeColorSpace DeviceRGB = new EnumStrokeColorSpace("DeviceRGB");
            public static final EnumStrokeColorSpace ICCBased = new EnumStrokeColorSpace("ICCBased");
            public static final EnumStrokeColorSpace Lab = new EnumStrokeColorSpace("Lab");
        }      



        /**
        * Enumeration strings for StrokeColorType
        */

        public static class EnumStrokeColorType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumStrokeColorType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumStrokeColorType getEnum(String enumName)
            {
                return (EnumStrokeColorType) getEnum(EnumStrokeColorType.class, enumName);
            }

            public static EnumStrokeColorType getEnum(int enumValue)
            {
                return (EnumStrokeColorType) getEnum(EnumStrokeColorType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumStrokeColorType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumStrokeColorType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumStrokeColorType.class);
            }

            public static final EnumStrokeColorType CMYGray = new EnumStrokeColorType("CMYGray");
            public static final EnumStrokeColorType CMYBlack = new EnumStrokeColorType("CMYBlack");
            public static final EnumStrokeColorType Other = new EnumStrokeColorType("Other");
            public static final EnumStrokeColorType PureBlack = new EnumStrokeColorType("PureBlack");
            public static final EnumStrokeColorType PureGray = new EnumStrokeColorType("PureGray");
            public static final EnumStrokeColorType RegistrationBlack = new EnumStrokeColorType("RegistrationBlack");
            public static final EnumStrokeColorType RegistrationGray = new EnumStrokeColorType("RegistrationGray");
            public static final EnumStrokeColorType RichBlack = new EnumStrokeColorType("RichBlack");
            public static final EnumStrokeColorType White = new EnumStrokeColorType("White");
        }      



        /**
        * Enumeration strings for StrokeShadingType
        */

        public static class EnumStrokeShadingType extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumStrokeShadingType(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumStrokeShadingType getEnum(String enumName)
            {
                return (EnumStrokeShadingType) getEnum(EnumStrokeShadingType.class, enumName);
            }

            public static EnumStrokeShadingType getEnum(int enumValue)
            {
                return (EnumStrokeShadingType) getEnum(EnumStrokeShadingType.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumStrokeShadingType.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumStrokeShadingType.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumStrokeShadingType.class);
            }

            public static final EnumStrokeShadingType Smooth = new EnumStrokeShadingType("Smooth");
            public static final EnumStrokeShadingType Vector = new EnumStrokeShadingType("Vector");
        }      



        /**
        * Enumeration strings for CharacterProblem
        */

        public static class EnumCharacterProblem extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumCharacterProblem(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumCharacterProblem getEnum(String enumName)
            {
                return (EnumCharacterProblem) getEnum(EnumCharacterProblem.class, enumName);
            }

            public static EnumCharacterProblem getEnum(int enumValue)
            {
                return (EnumCharacterProblem) getEnum(EnumCharacterProblem.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumCharacterProblem.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumCharacterProblem.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumCharacterProblem.class);
            }

            public static final EnumCharacterProblem Corrupted = new EnumCharacterProblem("Corrupted");
            public static final EnumCharacterProblem IncorrectEncoding = new EnumCharacterProblem("IncorrectEncoding");
            public static final EnumCharacterProblem Missing = new EnumCharacterProblem("Missing");
            public static final EnumCharacterProblem Others = new EnumCharacterProblem("Others");
        }      



        /**
        * Enumeration strings for UseArtificialTextEffect
        */

        public static class EnumUseArtificialTextEffect extends ValuedEnum
        {
            private static final long serialVersionUID = 1L;
            private static int m_startValue = 0;

            private EnumUseArtificialTextEffect(String name)
            {
                super(name, m_startValue++);
            }

            public static EnumUseArtificialTextEffect getEnum(String enumName)
            {
                return (EnumUseArtificialTextEffect) getEnum(EnumUseArtificialTextEffect.class, enumName);
            }

            public static EnumUseArtificialTextEffect getEnum(int enumValue)
            {
                return (EnumUseArtificialTextEffect) getEnum(EnumUseArtificialTextEffect.class, enumValue);
            }

            public static Map getEnumMap()
            {
                return getEnumMap(EnumUseArtificialTextEffect.class);
            }

            public static List getEnumList()
            {
                return getEnumList(EnumUseArtificialTextEffect.class);
            }

            public static Iterator iterator()
            {
                return iterator(EnumUseArtificialTextEffect.class);
            }

            public static final EnumUseArtificialTextEffect Bold = new EnumUseArtificialTextEffect("Bold");
            public static final EnumUseArtificialTextEffect Italic = new EnumUseArtificialTextEffect("Italic");
            public static final EnumUseArtificialTextEffect Outline = new EnumUseArtificialTextEffect("Outline");
            public static final EnumUseArtificialTextEffect Shadow = new EnumUseArtificialTextEffect("Shadow");
            public static final EnumUseArtificialTextEffect Underline = new EnumUseArtificialTextEffect("Underline");
        }      



/* ************************************************************************
 * Attribute getter / setter
 * ************************************************************************
 */
        
        /* ---------------------------------------------------------------------
        Methods for Attribute AnnotationPrintFlag
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AnnotationPrintFlag
          * @param value: the value to set the attribute to
          */
        public void setAnnotationPrintFlag(boolean value)
        {
            setAttribute(AttributeName.ANNOTATIONPRINTFLAG, value, null);
        }



        /**
          * (18) get boolean attribute AnnotationPrintFlag
          * @return boolean the value of the attribute
          */
        public boolean getAnnotationPrintFlag()
        {
            return getBoolAttribute(AttributeName.ANNOTATIONPRINTFLAG, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute AnnotationType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AnnotationType
          * @param value: the value to set the attribute to
          */
        public void setAnnotationType(String value)
        {
            setAttribute(AttributeName.ANNOTATIONTYPE, value, null);
        }



        /**
          * (23) get String attribute AnnotationType
          * @return the value of the attribute
          */
        public String getAnnotationType()
        {
            return getAttribute(AttributeName.ANNOTATIONTYPE, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute TrapnetAnnotationPDFX
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TrapnetAnnotationPDFX
          * @param value: the value to set the attribute to
          */
        public void setTrapnetAnnotationPDFX(VString value)
        {
            setAttribute(AttributeName.TRAPNETANNOTATIONPDFX, value, null);
        }



        /**
          * (21) get VString attribute TrapnetAnnotationPDFX
          * @return VString the value of the attribute
          */
        public VString getTrapnetAnnotationPDFX()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.TRAPNETANNOTATIONPDFX, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute BoundingBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BoundingBox
          * @param value: the value to set the attribute to
          */
        public void setBoundingBox(JDFRectangle value)
        {
            setAttribute(AttributeName.BOUNDINGBOX, value, null);
        }



        /**
          * (20) get JDFRectangle attribute BoundingBox
          * @return JDFRectanglethe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFRectangle
          */
        public JDFRectangle getBoundingBox()
        {
            String strAttrName = "";
            JDFRectangle nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.BOUNDINGBOX, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFRectangle(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute DifferentBoxSize
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DifferentBoxSize
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDifferentBoxSize(EnumDifferentBoxSize enumVar)
        {
            setAttribute(AttributeName.DIFFERENTBOXSIZE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute DifferentBoxSize
          * @return the value of the attribute
          */
        public EnumDifferentBoxSize getDifferentBoxSize()
        {
            return EnumDifferentBoxSize.getEnum(getAttribute(AttributeName.DIFFERENTBOXSIZE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute InsideBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute InsideBox
          * @param value: the value to set the attribute to
          */
        public void setInsideBox(boolean value)
        {
            setAttribute(AttributeName.INSIDEBOX, value, null);
        }



        /**
          * (18) get boolean attribute InsideBox
          * @return boolean the value of the attribute
          */
        public boolean getInsideBox()
        {
            return getBoolAttribute(AttributeName.INSIDEBOX, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute OutsideBox
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OutsideBox
          * @param value: the value to set the attribute to
          */
        public void setOutsideBox(boolean value)
        {
            setAttribute(AttributeName.OUTSIDEBOX, value, null);
        }



        /**
          * (18) get boolean attribute OutsideBox
          * @return boolean the value of the attribute
          */
        public boolean getOutsideBox()
        {
            return getBoolAttribute(AttributeName.OUTSIDEBOX, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ClassName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ClassName
          * @param value: the value to set the attribute to
          */
        public void setClassName(String value)
        {
            setAttribute(AttributeName.CLASSNAME, value, null);
        }



        /**
          * (23) get String attribute ClassName
          * @return the value of the attribute
          */
        public String getClassName()
        {
            return getAttribute(AttributeName.CLASSNAME, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PropertyList
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute PropertyList
          * @param v vector of the enumeration values
          */
        public void setPropertyList(Vector v)
        {
            setEnumerationsAttribute(AttributeName.PROPERTYLIST, v, null);
        }



        /**
          * (9.2) get PropertyList attribute PropertyList
          * @return Vector of the enumerations
          */
        public Vector getPropertyList()
        {
            return getEnumerationsAttribute(AttributeName.PROPERTYLIST, null, EnumPropertyList.getEnum(0), false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute AliasSeparations
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AliasSeparations
          * @param value: the value to set the attribute to
          */
        public void setAliasSeparations(boolean value)
        {
            setAttribute(AttributeName.ALIASSEPARATIONS, value, null);
        }



        /**
          * (18) get boolean attribute AliasSeparations
          * @return boolean the value of the attribute
          */
        public boolean getAliasSeparations()
        {
            return getBoolAttribute(AttributeName.ALIASSEPARATIONS, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute AmbiguousSeparations
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AmbiguousSeparations
          * @param value: the value to set the attribute to
          */
        public void setAmbiguousSeparations(boolean value)
        {
            setAttribute(AttributeName.AMBIGUOUSSEPARATIONS, value, null);
        }



        /**
          * (18) get boolean attribute AmbiguousSeparations
          * @return boolean the value of the attribute
          */
        public boolean getAmbiguousSeparations()
        {
            return getBoolAttribute(AttributeName.AMBIGUOUSSEPARATIONS, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute InkCoverage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute InkCoverage
          * @param value: the value to set the attribute to
          */
        public void setInkCoverage(double value)
        {
            setAttribute(AttributeName.INKCOVERAGE, value, null);
        }



        /**
          * (17) get double attribute InkCoverage
          * @return double the value of the attribute
          */
        public double getInkCoverage()
        {
            return getRealAttribute(AttributeName.INKCOVERAGE, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute SeparationList
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SeparationList
          * @param value: the value to set the attribute to
          */
        public void setSeparationList(String value)
        {
            setAttribute(AttributeName.SEPARATIONLIST, value, null);
        }



        /**
          * (23) get String attribute SeparationList
          * @return the value of the attribute
          */
        public String getSeparationList()
        {
            return getAttribute(AttributeName.SEPARATIONLIST, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Author
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Author
          * @param value: the value to set the attribute to
          */
        public void setAuthor(String value)
        {
            setAttribute(AttributeName.AUTHOR, value, null);
        }



        /**
          * (23) get String attribute Author
          * @return the value of the attribute
          */
        public String getAuthor()
        {
            return getAttribute(AttributeName.AUTHOR, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Binding
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute Binding
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setBinding(EnumBinding enumVar)
        {
            setAttribute(AttributeName.BINDING, enumVar.getName(), null);
        }



        /**
          * (9) get attribute Binding
          * @return the value of the attribute
          */
        public EnumBinding getBinding()
        {
            return EnumBinding.getEnum(getAttribute(AttributeName.BINDING, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute CreationDate
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute CreationDate
          * @deprecated 2005-12-02 use setCreationDate(null)
          */
        public void setCreationDate()
        {
            setAttribute(AttributeName.CREATIONDATE, new JDFDate().getDateTimeISO(), null);
        }



        /**
          * (11) set attribute CreationDate
          * @param value: the value to set the attribute to or null
          */
        public void setCreationDate(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.CREATIONDATE, value.getDateTimeISO(), null);
        }



        /**
          * (12) get JDFDate attribute CreationDate
          * @return JDFDate the value of the attribute
          */
        public JDFDate getCreationDate()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.CREATIONDATE, null, JDFConstants.EMPTYSTRING);
            if (!JDFConstants.EMPTYSTRING.equals(str))
            {
                try
                {
                    nMyDate = new JDFDate(str);
                }
                catch(DataFormatException dfe)
                {
                    throw new JDFException("not a valid date string. Malformed JDF");
                }
            }
            return nMyDate;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute CreationDateInDocument
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute CreationDateInDocument
          * @deprecated 2005-12-02 use setCreationDateInDocument(null)
          */
        public void setCreationDateInDocument()
        {
            setAttribute(AttributeName.CREATIONDATEINDOCUMENT, new JDFDate().getDateTimeISO(), null);
        }



        /**
          * (11) set attribute CreationDateInDocument
          * @param value: the value to set the attribute to or null
          */
        public void setCreationDateInDocument(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.CREATIONDATEINDOCUMENT, value.getDateTimeISO(), null);
        }



        /**
          * (12) get JDFDate attribute CreationDateInDocument
          * @return JDFDate the value of the attribute
          */
        public JDFDate getCreationDateInDocument()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.CREATIONDATEINDOCUMENT, null, JDFConstants.EMPTYSTRING);
            if (!JDFConstants.EMPTYSTRING.equals(str))
            {
                try
                {
                    nMyDate = new JDFDate(str);
                }
                catch(DataFormatException dfe)
                {
                    throw new JDFException("not a valid date string. Malformed JDF");
                }
            }
            return nMyDate;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute CreationID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CreationID
          * @param value: the value to set the attribute to
          */
        public void setCreationID(String value)
        {
            setAttribute(AttributeName.CREATIONID, value, null);
        }



        /**
          * (23) get String attribute CreationID
          * @return the value of the attribute
          */
        public String getCreationID()
        {
            return getAttribute(AttributeName.CREATIONID, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Creator
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Creator
          * @param value: the value to set the attribute to
          */
        public void setCreator(String value)
        {
            setAttribute(AttributeName.CREATOR, value, null);
        }



        /**
          * (23) get String attribute Creator
          * @return the value of the attribute
          */
        public String getCreator()
        {
            return getAttribute(AttributeName.CREATOR, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocumentCompression
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute DocumentCompression
          * @param v vector of the enumeration values
          */
        public void setDocumentCompression(Vector v)
        {
            setEnumerationsAttribute(AttributeName.DOCUMENTCOMPRESSION, v, null);
        }



        /**
          * (9.2) get DocumentCompression attribute DocumentCompression
          * @return Vector of the enumerations
          */
        public Vector getDocumentCompression()
        {
            return getEnumerationsAttribute(AttributeName.DOCUMENTCOMPRESSION, null, EnumDocumentCompression.getEnum(0), false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocumentCorruption
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DocumentCorruption
          * @param value: the value to set the attribute to
          */
        public void setDocumentCorruption(VString value)
        {
            setAttribute(AttributeName.DOCUMENTCORRUPTION, value, null);
        }



        /**
          * (21) get VString attribute DocumentCorruption
          * @return VString the value of the attribute
          */
        public VString getDocumentCorruption()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.DOCUMENTCORRUPTION, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocumentEncoding
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute DocumentEncoding
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setDocumentEncoding(EnumDocumentEncoding enumVar)
        {
            setAttribute(AttributeName.DOCUMENTENCODING, enumVar.getName(), null);
        }



        /**
          * (9) get attribute DocumentEncoding
          * @return the value of the attribute
          */
        public EnumDocumentEncoding getDocumentEncoding()
        {
            return EnumDocumentEncoding.getEnum(getAttribute(AttributeName.DOCUMENTENCODING, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocumentIsGoodCompression
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DocumentIsGoodCompression
          * @param value: the value to set the attribute to
          */
        public void setDocumentIsGoodCompression(boolean value)
        {
            setAttribute(AttributeName.DOCUMENTISGOODCOMPRESSION, value, null);
        }



        /**
          * (18) get boolean attribute DocumentIsGoodCompression
          * @return boolean the value of the attribute
          */
        public boolean getDocumentIsGoodCompression()
        {
            return getBoolAttribute(AttributeName.DOCUMENTISGOODCOMPRESSION, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute EncryptedDocument
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EncryptedDocument
          * @param value: the value to set the attribute to
          */
        public void setEncryptedDocument(boolean value)
        {
            setAttribute(AttributeName.ENCRYPTEDDOCUMENT, value, null);
        }



        /**
          * (18) get boolean attribute EncryptedDocument
          * @return boolean the value of the attribute
          */
        public boolean getEncryptedDocument()
        {
            return getBoolAttribute(AttributeName.ENCRYPTEDDOCUMENT, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute EncryptionFilter
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EncryptionFilter
          * @param value: the value to set the attribute to
          */
        public void setEncryptionFilter(String value)
        {
            setAttribute(AttributeName.ENCRYPTIONFILTER, value, null);
        }



        /**
          * (23) get String attribute EncryptionFilter
          * @return the value of the attribute
          */
        public String getEncryptionFilter()
        {
            return getAttribute(AttributeName.ENCRYPTIONFILTER, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute EncryptionLength
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EncryptionLength
          * @param value: the value to set the attribute to
          */
        public void setEncryptionLength(int value)
        {
            setAttribute(AttributeName.ENCRYPTIONLENGTH, value, null);
        }



        /**
          * (15) get int attribute EncryptionLength
          * @return int the value of the attribute
          */
        public int getEncryptionLength()
        {
            return getIntAttribute(AttributeName.ENCRYPTIONLENGTH, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute EncryptionRestrictions
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EncryptionRestrictions
          * @param value: the value to set the attribute to
          */
        public void setEncryptionRestrictions(VString value)
        {
            setAttribute(AttributeName.ENCRYPTIONRESTRICTIONS, value, null);
        }



        /**
          * (21) get VString attribute EncryptionRestrictions
          * @return VString the value of the attribute
          */
        public VString getEncryptionRestrictions()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.ENCRYPTIONRESTRICTIONS, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute EncryptionSubFilter
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EncryptionSubFilter
          * @param value: the value to set the attribute to
          */
        public void setEncryptionSubFilter(String value)
        {
            setAttribute(AttributeName.ENCRYPTIONSUBFILTER, value, null);
        }



        /**
          * (23) get String attribute EncryptionSubFilter
          * @return the value of the attribute
          */
        public String getEncryptionSubFilter()
        {
            return getAttribute(AttributeName.ENCRYPTIONSUBFILTER, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute EncryptionV
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EncryptionV
          * @param value: the value to set the attribute to
          */
        public void setEncryptionV(int value)
        {
            setAttribute(AttributeName.ENCRYPTIONV, value, null);
        }



        /**
          * (15) get int attribute EncryptionV
          * @return int the value of the attribute
          */
        public int getEncryptionV()
        {
            return getIntAttribute(AttributeName.ENCRYPTIONV, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FileName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FileName
          * @param value: the value to set the attribute to
          */
        public void setFileName(String value)
        {
            setAttribute(AttributeName.FILENAME, value, null);
        }



        /**
          * (23) get String attribute FileName
          * @return the value of the attribute
          */
        public String getFileName()
        {
            return getAttribute(AttributeName.FILENAME, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FileSize
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FileSize
          * @param value: the value to set the attribute to
          */
        public void setFileSize(int value)
        {
            setAttribute(AttributeName.FILESIZE, value, null);
        }



        /**
          * (15) get int attribute FileSize
          * @return int the value of the attribute
          */
        public int getFileSize()
        {
            return getIntAttribute(AttributeName.FILESIZE, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Keywords
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Keywords
          * @param value: the value to set the attribute to
          */
        public void setKeywords(String value)
        {
            setAttribute(AttributeName.KEYWORDS, value, null);
        }



        /**
          * (23) get String attribute Keywords
          * @return the value of the attribute
          */
        public String getKeywords()
        {
            return getAttribute(AttributeName.KEYWORDS, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Linearized
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Linearized
          * @param value: the value to set the attribute to
          */
        public void setLinearized(boolean value)
        {
            setAttribute(AttributeName.LINEARIZED, value, null);
        }



        /**
          * (18) get boolean attribute Linearized
          * @return boolean the value of the attribute
          */
        public boolean getLinearized()
        {
            return getBoolAttribute(AttributeName.LINEARIZED, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModificationDate
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute ModificationDate
          * @deprecated 2005-12-02 use setModificationDate(null)
          */
        public void setModificationDate()
        {
            setAttribute(AttributeName.MODIFICATIONDATE, new JDFDate().getDateTimeISO(), null);
        }



        /**
          * (11) set attribute ModificationDate
          * @param value: the value to set the attribute to or null
          */
        public void setModificationDate(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.MODIFICATIONDATE, value.getDateTimeISO(), null);
        }



        /**
          * (12) get JDFDate attribute ModificationDate
          * @return JDFDate the value of the attribute
          */
        public JDFDate getModificationDate()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.MODIFICATIONDATE, null, JDFConstants.EMPTYSTRING);
            if (!JDFConstants.EMPTYSTRING.equals(str))
            {
                try
                {
                    nMyDate = new JDFDate(str);
                }
                catch(DataFormatException dfe)
                {
                    throw new JDFException("not a valid date string. Malformed JDF");
                }
            }
            return nMyDate;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModificationDateInDocument
        --------------------------------------------------------------------- */
        /**
          * (10) set attribute ModificationDateInDocument
          * @deprecated 2005-12-02 use setModificationDateInDocument(null)
          */
        public void setModificationDateInDocument()
        {
            setAttribute(AttributeName.MODIFICATIONDATEINDOCUMENT, new JDFDate().getDateTimeISO(), null);
        }



        /**
          * (11) set attribute ModificationDateInDocument
          * @param value: the value to set the attribute to or null
          */
        public void setModificationDateInDocument(JDFDate value)
        {
            if (value == null) value = new JDFDate();
            setAttribute(AttributeName.MODIFICATIONDATEINDOCUMENT, value.getDateTimeISO(), null);
        }



        /**
          * (12) get JDFDate attribute ModificationDateInDocument
          * @return JDFDate the value of the attribute
          */
        public JDFDate getModificationDateInDocument()
        {
            JDFDate nMyDate = null;
            String str = JDFConstants.EMPTYSTRING;
            str = getAttribute(AttributeName.MODIFICATIONDATEINDOCUMENT, null, JDFConstants.EMPTYSTRING);
            if (!JDFConstants.EMPTYSTRING.equals(str))
            {
                try
                {
                    nMyDate = new JDFDate(str);
                }
                catch(DataFormatException dfe)
                {
                    throw new JDFException("not a valid date string. Malformed JDF");
                }
            }
            return nMyDate;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ModificationID
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ModificationID
          * @param value: the value to set the attribute to
          */
        public void setModificationID(String value)
        {
            setAttribute(AttributeName.MODIFICATIONID, value, null);
        }



        /**
          * (23) get String attribute ModificationID
          * @return the value of the attribute
          */
        public String getModificationID()
        {
            return getAttribute(AttributeName.MODIFICATIONID, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute NumberOfPages
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NumberOfPages
          * @param value: the value to set the attribute to
          */
        public void setNumberOfPages(int value)
        {
            setAttribute(AttributeName.NUMBEROFPAGES, value, null);
        }



        /**
          * (15) get int attribute NumberOfPages
          * @return int the value of the attribute
          */
        public int getNumberOfPages()
        {
            return getIntAttribute(AttributeName.NUMBEROFPAGES, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute OutputIntentColorSpace
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OutputIntentColorSpace
          * @param value: the value to set the attribute to
          */
        public void setOutputIntentColorSpace(String value)
        {
            setAttribute(AttributeName.OUTPUTINTENTCOLORSPACE, value, null);
        }



        /**
          * (23) get String attribute OutputIntentColorSpace
          * @return the value of the attribute
          */
        public String getOutputIntentColorSpace()
        {
            return getAttribute(AttributeName.OUTPUTINTENTCOLORSPACE, null, "None");
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute OutputIntentStandard
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OutputIntentStandard
          * @param value: the value to set the attribute to
          */
        public void setOutputIntentStandard(String value)
        {
            setAttribute(AttributeName.OUTPUTINTENTSTANDARD, value, null);
        }



        /**
          * (23) get String attribute OutputIntentStandard
          * @return the value of the attribute
          */
        public String getOutputIntentStandard()
        {
            return getAttribute(AttributeName.OUTPUTINTENTSTANDARD, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PagesHaveSameOrientation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PagesHaveSameOrientation
          * @param value: the value to set the attribute to
          */
        public void setPagesHaveSameOrientation(boolean value)
        {
            setAttribute(AttributeName.PAGESHAVESAMEORIENTATION, value, null);
        }



        /**
          * (18) get boolean attribute PagesHaveSameOrientation
          * @return boolean the value of the attribute
          */
        public boolean getPagesHaveSameOrientation()
        {
            return getBoolAttribute(AttributeName.PAGESHAVESAMEORIENTATION, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDFXVersion
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDFXVersion
          * @param value: the value to set the attribute to
          */
        public void setPDFXVersion(String value)
        {
            setAttribute(AttributeName.PDFXVERSION, value, null);
        }



        /**
          * (23) get String attribute PDFXVersion
          * @return the value of the attribute
          */
        public String getPDFXVersion()
        {
            return getAttribute(AttributeName.PDFXVERSION, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute DocumentPDLType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute DocumentPDLType
          * @param value: the value to set the attribute to
          */
        public void setDocumentPDLType(String value)
        {
            setAttribute(AttributeName.DOCUMENTPDLTYPE, value, null);
        }



        /**
          * (23) get String attribute DocumentPDLType
          * @return the value of the attribute
          */
        public String getDocumentPDLType()
        {
            return getAttribute(AttributeName.DOCUMENTPDLTYPE, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDLVersion
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDLVersion
          * @param value: the value to set the attribute to
          */
        public void setPDLVersion(String value)
        {
            setAttribute(AttributeName.PDLVERSION, value, null);
        }



        /**
          * (23) get String attribute PDLVersion
          * @return the value of the attribute
          */
        public String getPDLVersion()
        {
            return getAttribute(AttributeName.PDLVERSION, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Producer
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Producer
          * @param value: the value to set the attribute to
          */
        public void setProducer(String value)
        {
            setAttribute(AttributeName.PRODUCER, value, null);
        }



        /**
          * (23) get String attribute Producer
          * @return the value of the attribute
          */
        public String getProducer()
        {
            return getAttribute(AttributeName.PRODUCER, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute SeparationFlag
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute SeparationFlag
          * @param value: the value to set the attribute to
          */
        public void setSeparationFlag(boolean value)
        {
            setAttribute(AttributeName.SEPARATIONFLAG, value, null);
        }



        /**
          * (18) get boolean attribute SeparationFlag
          * @return boolean the value of the attribute
          */
        public boolean getSeparationFlag()
        {
            return getBoolAttribute(AttributeName.SEPARATIONFLAG, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Subject
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Subject
          * @param value: the value to set the attribute to
          */
        public void setSubject(String value)
        {
            setAttribute(AttributeName.SUBJECT, value, null);
        }



        /**
          * (23) get String attribute Subject
          * @return the value of the attribute
          */
        public String getSubject()
        {
            return getAttribute(AttributeName.SUBJECT, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Title
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Title
          * @param value: the value to set the attribute to
          */
        public void setTitle(String value)
        {
            setAttribute(AttributeName.TITLE, value, null);
        }



        /**
          * (23) get String attribute Title
          * @return the value of the attribute
          */
        public String getTitle()
        {
            return getAttribute(AttributeName.TITLE, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute TrappedKey
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute TrappedKey
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setTrappedKey(EnumTrappedKey enumVar)
        {
            setAttribute(AttributeName.TRAPPEDKEY, enumVar.getName(), null);
        }



        /**
          * (9) get attribute TrappedKey
          * @return the value of the attribute
          */
        public EnumTrappedKey getTrappedKey()
        {
            return EnumTrappedKey.getEnum(getAttribute(AttributeName.TRAPPEDKEY, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FillColorName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FillColorName
          * @param value: the value to set the attribute to
          */
        public void setFillColorName(String value)
        {
            setAttribute(AttributeName.FILLCOLORNAME, value, null);
        }



        /**
          * (23) get String attribute FillColorName
          * @return the value of the attribute
          */
        public String getFillColorName()
        {
            return getAttribute(AttributeName.FILLCOLORNAME, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FillColorType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute FillColorType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFillColorType(EnumFillColorType enumVar)
        {
            setAttribute(AttributeName.FILLCOLORTYPE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute FillColorType
          * @return the value of the attribute
          */
        public EnumFillColorType getFillColorType()
        {
            return EnumFillColorType.getEnum(getAttribute(AttributeName.FILLCOLORTYPE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute HasFillColor
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HasFillColor
          * @param value: the value to set the attribute to
          */
        public void setHasFillColor(boolean value)
        {
            setAttribute(AttributeName.HASFILLCOLOR, value, null);
        }



        /**
          * (18) get boolean attribute HasFillColor
          * @return boolean the value of the attribute
          */
        public boolean getHasFillColor()
        {
            return getBoolAttribute(AttributeName.HASFILLCOLOR, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute EmbeddingRestrictionFlag
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EmbeddingRestrictionFlag
          * @param value: the value to set the attribute to
          */
        public void setEmbeddingRestrictionFlag(boolean value)
        {
            setAttribute(AttributeName.EMBEDDINGRESTRICTIONFLAG, value, null);
        }



        /**
          * (18) get boolean attribute EmbeddingRestrictionFlag
          * @return boolean the value of the attribute
          */
        public boolean getEmbeddingRestrictionFlag()
        {
            return getBoolAttribute(AttributeName.EMBEDDINGRESTRICTIONFLAG, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FontCorrupted
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FontCorrupted
          * @param value: the value to set the attribute to
          */
        public void setFontCorrupted(boolean value)
        {
            setAttribute(AttributeName.FONTCORRUPTED, value, null);
        }



        /**
          * (18) get boolean attribute FontCorrupted
          * @return boolean the value of the attribute
          */
        public boolean getFontCorrupted()
        {
            return getBoolAttribute(AttributeName.FONTCORRUPTED, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FontCreator
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FontCreator
          * @param value: the value to set the attribute to
          */
        public void setFontCreator(String value)
        {
            setAttribute(AttributeName.FONTCREATOR, value, null);
        }



        /**
          * (23) get String attribute FontCreator
          * @return the value of the attribute
          */
        public String getFontCreator()
        {
            return getAttribute(AttributeName.FONTCREATOR, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FontEmbedded
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FontEmbedded
          * @param value: the value to set the attribute to
          */
        public void setFontEmbedded(boolean value)
        {
            setAttribute(AttributeName.FONTEMBEDDED, value, null);
        }



        /**
          * (18) get boolean attribute FontEmbedded
          * @return boolean the value of the attribute
          */
        public boolean getFontEmbedded()
        {
            return getBoolAttribute(AttributeName.FONTEMBEDDED, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FontIsStandardLatin
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FontIsStandardLatin
          * @param value: the value to set the attribute to
          */
        public void setFontIsStandardLatin(boolean value)
        {
            setAttribute(AttributeName.FONTISSTANDARDLATIN, value, null);
        }



        /**
          * (18) get boolean attribute FontIsStandardLatin
          * @return boolean the value of the attribute
          */
        public boolean getFontIsStandardLatin()
        {
            return getBoolAttribute(AttributeName.FONTISSTANDARDLATIN, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FontName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FontName
          * @param value: the value to set the attribute to
          */
        public void setFontName(String value)
        {
            setAttribute(AttributeName.FONTNAME, value, null);
        }



        /**
          * (23) get String attribute FontName
          * @return the value of the attribute
          */
        public String getFontName()
        {
            return getAttribute(AttributeName.FONTNAME, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FontNotUsed
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FontNotUsed
          * @param value: the value to set the attribute to
          */
        public void setFontNotUsed(boolean value)
        {
            setAttribute(AttributeName.FONTNOTUSED, value, null);
        }



        /**
          * (18) get boolean attribute FontNotUsed
          * @return boolean the value of the attribute
          */
        public boolean getFontNotUsed()
        {
            return getBoolAttribute(AttributeName.FONTNOTUSED, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FontSubset
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FontSubset
          * @param value: the value to set the attribute to
          */
        public void setFontSubset(boolean value)
        {
            setAttribute(AttributeName.FONTSUBSET, value, null);
        }



        /**
          * (18) get boolean attribute FontSubset
          * @return boolean the value of the attribute
          */
        public boolean getFontSubset()
        {
            return getBoolAttribute(AttributeName.FONTSUBSET, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FontType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute FontType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setFontType(EnumFontType enumVar)
        {
            setAttribute(AttributeName.FONTTYPE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute FontType
          * @return the value of the attribute
          */
        public EnumFontType getFontType()
        {
            return EnumFontType.getEnum(getAttribute(AttributeName.FONTTYPE, null, "Other"));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute FontVendor
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute FontVendor
          * @param value: the value to set the attribute to
          */
        public void setFontVendor(String value)
        {
            setAttribute(AttributeName.FONTVENDOR, value, null);
        }



        /**
          * (23) get String attribute FontVendor
          * @return the value of the attribute
          */
        public String getFontVendor()
        {
            return getAttribute(AttributeName.FONTVENDOR, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute IsFontScreenOnly
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute IsFontScreenOnly
          * @param value: the value to set the attribute to
          */
        public void setIsFontScreenOnly(boolean value)
        {
            setAttribute(AttributeName.ISFONTSCREENONLY, value, null);
        }



        /**
          * (18) get boolean attribute IsFontScreenOnly
          * @return boolean the value of the attribute
          */
        public boolean getIsFontScreenOnly()
        {
            return getBoolAttribute(AttributeName.ISFONTSCREENONLY, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PSFontName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PSFontName
          * @param value: the value to set the attribute to
          */
        public void setPSFontName(String value)
        {
            setAttribute(AttributeName.PSFONTNAME, value, null);
        }



        /**
          * (23) get String attribute PSFontName
          * @return the value of the attribute
          */
        public String getPSFontName()
        {
            return getAttribute(AttributeName.PSFONTNAME, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute AlphaIsShape
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AlphaIsShape
          * @param value: the value to set the attribute to
          */
        public void setAlphaIsShape(boolean value)
        {
            setAttribute(AttributeName.ALPHAISSHAPE, value, null);
        }



        /**
          * (18) get boolean attribute AlphaIsShape
          * @return boolean the value of the attribute
          */
        public boolean getAlphaIsShape()
        {
            return getBoolAttribute(AttributeName.ALPHAISSHAPE, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute AlternateColorSpace
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute AlternateColorSpace
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setAlternateColorSpace(EnumAlternateColorSpace enumVar)
        {
            setAttribute(AttributeName.ALTERNATECOLORSPACE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute AlternateColorSpace
          * @return the value of the attribute
          */
        public EnumAlternateColorSpace getAlternateColorSpace()
        {
            return EnumAlternateColorSpace.getEnum(getAttribute(AttributeName.ALTERNATECOLORSPACE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute BelongsToAnnotation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BelongsToAnnotation
          * @param value: the value to set the attribute to
          */
        public void setBelongsToAnnotation(boolean value)
        {
            setAttribute(AttributeName.BELONGSTOANNOTATION, value, null);
        }



        /**
          * (18) get boolean attribute BelongsToAnnotation
          * @return boolean the value of the attribute
          */
        public boolean getBelongsToAnnotation()
        {
            return getBoolAttribute(AttributeName.BELONGSTOANNOTATION, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute BlackGeneration
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute BlackGeneration
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setBlackGeneration(EnumBlackGeneration enumVar)
        {
            setAttribute(AttributeName.BLACKGENERATION, enumVar.getName(), null);
        }



        /**
          * (9) get attribute BlackGeneration
          * @return the value of the attribute
          */
        public EnumBlackGeneration getBlackGeneration()
        {
            return EnumBlackGeneration.getEnum(getAttribute(AttributeName.BLACKGENERATION, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute BlendMode
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BlendMode
          * @param value: the value to set the attribute to
          */
        public void setBlendMode(String value)
        {
            setAttribute(AttributeName.BLENDMODE, value, null);
        }



        /**
          * (23) get String attribute BlendMode
          * @return the value of the attribute
          */
        public String getBlendMode()
        {
            return getAttribute(AttributeName.BLENDMODE, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ColorSpace
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ColorSpace
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setColorSpace(EnumColorSpace enumVar)
        {
            setAttribute(AttributeName.COLORSPACE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute ColorSpace
          * @return the value of the attribute
          */
        public EnumColorSpace getColorSpace()
        {
            return EnumColorSpace.getEnum(getAttribute(AttributeName.COLORSPACE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute EmbeddedPS
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EmbeddedPS
          * @param value: the value to set the attribute to
          */
        public void setEmbeddedPS(boolean value)
        {
            setAttribute(AttributeName.EMBEDDEDPS, value, null);
        }



        /**
          * (18) get boolean attribute EmbeddedPS
          * @return boolean the value of the attribute
          */
        public boolean getEmbeddedPS()
        {
            return getBoolAttribute(AttributeName.EMBEDDEDPS, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Flatness
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Flatness
          * @param value: the value to set the attribute to
          */
        public void setFlatness(double value)
        {
            setAttribute(AttributeName.FLATNESS, value, null);
        }



        /**
          * (17) get double attribute Flatness
          * @return double the value of the attribute
          */
        public double getFlatness()
        {
            return getRealAttribute(AttributeName.FLATNESS, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute HasSoftMask
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HasSoftMask
          * @param value: the value to set the attribute to
          */
        public void setHasSoftMask(boolean value)
        {
            setAttribute(AttributeName.HASSOFTMASK, value, null);
        }



        /**
          * (18) get boolean attribute HasSoftMask
          * @return boolean the value of the attribute
          */
        public boolean getHasSoftMask()
        {
            return getBoolAttribute(AttributeName.HASSOFTMASK, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute HalfTone
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HalfTone
          * @param value: the value to set the attribute to
          */
        public void setHalfTone(String value)
        {
            setAttribute(AttributeName.HALFTONE, value, null);
        }



        /**
          * (23) get String attribute HalfTone
          * @return the value of the attribute
          */
        public String getHalfTone()
        {
            return getAttribute(AttributeName.HALFTONE, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute HalfTonePhase
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HalfTonePhase
          * @param value: the value to set the attribute to
          */
        public void setHalfTonePhase(JDFXYPair value)
        {
            setAttribute(AttributeName.HALFTONEPHASE, value, null);
        }



        /**
          * (20) get JDFXYPair attribute HalfTonePhase
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getHalfTonePhase()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.HALFTONEPHASE, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFXYPair(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute HasColorLUT
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HasColorLUT
          * @param value: the value to set the attribute to
          */
        public void setHasColorLUT(boolean value)
        {
            setAttribute(AttributeName.HASCOLORLUT, value, null);
        }



        /**
          * (18) get boolean attribute HasColorLUT
          * @return boolean the value of the attribute
          */
        public boolean getHasColorLUT()
        {
            return getBoolAttribute(AttributeName.HASCOLORLUT, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute NumberOfColorsInLUT
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NumberOfColorsInLUT
          * @param value: the value to set the attribute to
          */
        public void setNumberOfColorsInLUT(int value)
        {
            setAttribute(AttributeName.NUMBEROFCOLORSINLUT, value, null);
        }



        /**
          * (15) get int attribute NumberOfColorsInLUT
          * @return int the value of the attribute
          */
        public int getNumberOfColorsInLUT()
        {
            return getIntAttribute(AttributeName.NUMBEROFCOLORSINLUT, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute OverPrintFlag
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OverPrintFlag
          * @param value: the value to set the attribute to
          */
        public void setOverPrintFlag(boolean value)
        {
            setAttribute(AttributeName.OVERPRINTFLAG, value, null);
        }



        /**
          * (18) get boolean attribute OverPrintFlag
          * @return boolean the value of the attribute
          */
        public boolean getOverPrintFlag()
        {
            return getBoolAttribute(AttributeName.OVERPRINTFLAG, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute OverPrintMode
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OverPrintMode
          * @param value: the value to set the attribute to
          */
        public void setOverPrintMode(int value)
        {
            setAttribute(AttributeName.OVERPRINTMODE, value, null);
        }



        /**
          * (15) get int attribute OverPrintMode
          * @return int the value of the attribute
          */
        public int getOverPrintMode()
        {
            return getIntAttribute(AttributeName.OVERPRINTMODE, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute RenderingIntent
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute RenderingIntent
          * @param value: the value to set the attribute to
          */
        public void setRenderingIntent(String value)
        {
            setAttribute(AttributeName.RENDERINGINTENT, value, null);
        }



        /**
          * (23) get String attribute RenderingIntent
          * @return the value of the attribute
          */
        public String getRenderingIntent()
        {
            return getAttribute(AttributeName.RENDERINGINTENT, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Smoothness
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Smoothness
          * @param value: the value to set the attribute to
          */
        public void setSmoothness(double value)
        {
            setAttribute(AttributeName.SMOOTHNESS, value, null);
        }



        /**
          * (17) get double attribute Smoothness
          * @return double the value of the attribute
          */
        public double getSmoothness()
        {
            return getRealAttribute(AttributeName.SMOOTHNESS, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute TransferFunction
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute TransferFunction
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setTransferFunction(EnumTransferFunction enumVar)
        {
            setAttribute(AttributeName.TRANSFERFUNCTION, enumVar.getName(), null);
        }



        /**
          * (9) get attribute TransferFunction
          * @return the value of the attribute
          */
        public EnumTransferFunction getTransferFunction()
        {
            return EnumTransferFunction.getEnum(getAttribute(AttributeName.TRANSFERFUNCTION, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute TransparencyFlag
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TransparencyFlag
          * @param value: the value to set the attribute to
          */
        public void setTransparencyFlag(boolean value)
        {
            setAttribute(AttributeName.TRANSPARENCYFLAG, value, null);
        }



        /**
          * (18) get boolean attribute TransparencyFlag
          * @return boolean the value of the attribute
          */
        public boolean getTransparencyFlag()
        {
            return getBoolAttribute(AttributeName.TRANSPARENCYFLAG, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute UnderColorRemoval
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute UnderColorRemoval
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setUnderColorRemoval(EnumUnderColorRemoval enumVar)
        {
            setAttribute(AttributeName.UNDERCOLORREMOVAL, enumVar.getName(), null);
        }



        /**
          * (9) get attribute UnderColorRemoval
          * @return the value of the attribute
          */
        public EnumUnderColorRemoval getUnderColorRemoval()
        {
            return EnumUnderColorRemoval.getEnum(getAttribute(AttributeName.UNDERCOLORREMOVAL, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute AlternateImages
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute AlternateImages
          * @param value: the value to set the attribute to
          */
        public void setAlternateImages(VString value)
        {
            setAttribute(AttributeName.ALTERNATEIMAGES, value, null);
        }



        /**
          * (21) get VString attribute AlternateImages
          * @return VString the value of the attribute
          */
        public VString getAlternateImages()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.ALTERNATEIMAGES, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute BitsPerSample
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BitsPerSample
          * @param value: the value to set the attribute to
          */
        public void setBitsPerSample(int value)
        {
            setAttribute(AttributeName.BITSPERSAMPLE, value, null);
        }



        /**
          * (15) get int attribute BitsPerSample
          * @return int the value of the attribute
          */
        public int getBitsPerSample()
        {
            return getIntAttribute(AttributeName.BITSPERSAMPLE, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute CompressionRatio
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute CompressionRatio
          * @param value: the value to set the attribute to
          */
        public void setCompressionRatio(double value)
        {
            setAttribute(AttributeName.COMPRESSIONRATIO, value, null);
        }



        /**
          * (17) get double attribute CompressionRatio
          * @return double the value of the attribute
          */
        public double getCompressionRatio()
        {
            return getRealAttribute(AttributeName.COMPRESSIONRATIO, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute CompressionTypes
        --------------------------------------------------------------------- */
        /**
          * (5.2) set attribute CompressionTypes
          * @param v vector of the enumeration values
          */
        public void setCompressionTypes(Vector v)
        {
            setEnumerationsAttribute(AttributeName.COMPRESSIONTYPES, v, null);
        }



        /**
          * (9.2) get CompressionTypes attribute CompressionTypes
          * @return Vector of the enumerations
          */
        public Vector getCompressionTypes()
        {
            return getEnumerationsAttribute(AttributeName.COMPRESSIONTYPES, null, EnumCompressionTypes.getEnum(0), false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute EffectiveResolution
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EffectiveResolution
          * @param value: the value to set the attribute to
          */
        public void setEffectiveResolution(JDFXYPair value)
        {
            setAttribute(AttributeName.EFFECTIVERESOLUTION, value, null);
        }



        /**
          * (20) get JDFXYPair attribute EffectiveResolution
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getEffectiveResolution()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.EFFECTIVERESOLUTION, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFXYPair(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute EstimatedJPEGQuality
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute EstimatedJPEGQuality
          * @param value: the value to set the attribute to
          */
        public void setEstimatedJPEGQuality(int value)
        {
            setAttribute(AttributeName.ESTIMATEDJPEGQUALITY, value, null);
        }



        /**
          * (15) get int attribute EstimatedJPEGQuality
          * @return int the value of the attribute
          */
        public int getEstimatedJPEGQuality()
        {
            return getIntAttribute(AttributeName.ESTIMATEDJPEGQUALITY, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ImageFlipped
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ImageFlipped
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setImageFlipped(EnumImageFlipped enumVar)
        {
            setAttribute(AttributeName.IMAGEFLIPPED, enumVar.getName(), null);
        }



        /**
          * (9) get attribute ImageFlipped
          * @return the value of the attribute
          */
        public EnumImageFlipped getImageFlipped()
        {
            return EnumImageFlipped.getEnum(getAttribute(AttributeName.IMAGEFLIPPED, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ImageMaskType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ImageMaskType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setImageMaskType(EnumImageMaskType enumVar)
        {
            setAttribute(AttributeName.IMAGEMASKTYPE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute ImageMaskType
          * @return the value of the attribute
          */
        public EnumImageMaskType getImageMaskType()
        {
            return EnumImageMaskType.getEnum(getAttribute(AttributeName.IMAGEMASKTYPE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ImageRotation
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ImageRotation
          * @param value: the value to set the attribute to
          */
        public void setImageRotation(int value)
        {
            setAttribute(AttributeName.IMAGEROTATION, value, null);
        }



        /**
          * (15) get int attribute ImageRotation
          * @return int the value of the attribute
          */
        public int getImageRotation()
        {
            return getIntAttribute(AttributeName.IMAGEROTATION, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ImageScalingRatio
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ImageScalingRatio
          * @param value: the value to set the attribute to
          */
        public void setImageScalingRatio(double value)
        {
            setAttribute(AttributeName.IMAGESCALINGRATIO, value, null);
        }



        /**
          * (17) get double attribute ImageScalingRatio
          * @return double the value of the attribute
          */
        public double getImageScalingRatio()
        {
            return getRealAttribute(AttributeName.IMAGESCALINGRATIO, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ImageSkew
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ImageSkew
          * @param value: the value to set the attribute to
          */
        public void setImageSkew(double value)
        {
            setAttribute(AttributeName.IMAGESKEW, value, null);
        }



        /**
          * (17) get double attribute ImageSkew
          * @return double the value of the attribute
          */
        public double getImageSkew()
        {
            return getRealAttribute(AttributeName.IMAGESKEW, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute OriginalResolution
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OriginalResolution
          * @param value: the value to set the attribute to
          */
        public void setOriginalResolution(JDFXYPair value)
        {
            setAttribute(AttributeName.ORIGINALRESOLUTION, value, null);
        }



        /**
          * (20) get JDFXYPair attribute OriginalResolution
          * @return JDFXYPairthe value of the attribute, null if a the
          *         attribute value is not a valid to create a JDFXYPair
          */
        public JDFXYPair getOriginalResolution()
        {
            String strAttrName = "";
            JDFXYPair nPlaceHolder = null;
            strAttrName = getAttribute(AttributeName.ORIGINALRESOLUTION, null, JDFConstants.EMPTYSTRING);
            try
            {
                nPlaceHolder = new JDFXYPair(strAttrName);
            }
            catch(DataFormatException e)
            {
                return null;
            }
            return nPlaceHolder;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PixelHeight
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PixelHeight
          * @param value: the value to set the attribute to
          */
        public void setPixelHeight(int value)
        {
            setAttribute(AttributeName.PIXELHEIGHT, value, null);
        }



        /**
          * (15) get int attribute PixelHeight
          * @return int the value of the attribute
          */
        public int getPixelHeight()
        {
            return getIntAttribute(AttributeName.PIXELHEIGHT, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PixelWidth
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PixelWidth
          * @param value: the value to set the attribute to
          */
        public void setPixelWidth(int value)
        {
            setAttribute(AttributeName.PIXELWIDTH, value, null);
        }



        /**
          * (15) get int attribute PixelWidth
          * @return int the value of the attribute
          */
        public int getPixelWidth()
        {
            return getIntAttribute(AttributeName.PIXELWIDTH, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute Count
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute Count
          * @param value: the value to set the attribute to
          */
        public void setCount(int value)
        {
            setAttribute(AttributeName.COUNT, value, null);
        }



        /**
          * (15) get int attribute Count
          * @return int the value of the attribute
          */
        public int getCount()
        {
            return getIntAttribute(AttributeName.COUNT, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageBoxType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute PageBoxType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setPageBoxType(EnumPageBoxType enumVar)
        {
            setAttribute(AttributeName.PAGEBOXTYPE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute PageBoxType
          * @return the value of the attribute
          */
        public EnumPageBoxType getPageBoxType()
        {
            return EnumPageBoxType.getEnum(getAttribute(AttributeName.PAGEBOXTYPE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute BlankPage
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute BlankPage
          * @param value: the value to set the attribute to
          */
        public void setBlankPage(boolean value)
        {
            setAttribute(AttributeName.BLANKPAGE, value, null);
        }



        /**
          * (18) get boolean attribute BlankPage
          * @return boolean the value of the attribute
          */
        public boolean getBlankPage()
        {
            return getBoolAttribute(AttributeName.BLANKPAGE, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute BlendColorSpace
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute BlendColorSpace
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setBlendColorSpace(EnumBlendColorSpace enumVar)
        {
            setAttribute(AttributeName.BLENDCOLORSPACE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute BlendColorSpace
          * @return the value of the attribute
          */
        public EnumBlendColorSpace getBlendColorSpace()
        {
            return EnumBlendColorSpace.getEnum(getAttribute(AttributeName.BLENDCOLORSPACE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PageHasUnknownObjects
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PageHasUnknownObjects
          * @param value: the value to set the attribute to
          */
        public void setPageHasUnknownObjects(boolean value)
        {
            setAttribute(AttributeName.PAGEHASUNKNOWNOBJECTS, value, null);
        }



        /**
          * (18) get boolean attribute PageHasUnknownObjects
          * @return boolean the value of the attribute
          */
        public boolean getPageHasUnknownObjects()
        {
            return getBoolAttribute(AttributeName.PAGEHASUNKNOWNOBJECTS, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ReversePageNumber
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ReversePageNumber
          * @param value: the value to set the attribute to
          */
        public void setReversePageNumber(int value)
        {
            setAttribute(AttributeName.REVERSEPAGENUMBER, value, null);
        }



        /**
          * (15) get int attribute ReversePageNumber
          * @return int the value of the attribute
          */
        public int getReversePageNumber()
        {
            return getIntAttribute(AttributeName.REVERSEPAGENUMBER, null, 0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute PDLType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute PDLType
          * @param value: the value to set the attribute to
          */
        public void setPDLType(String value)
        {
            setAttribute(AttributeName.PDLTYPE, value, null);
        }



        /**
          * (23) get String attribute PDLType
          * @return the value of the attribute
          */
        public String getPDLType()
        {
            return getAttribute(AttributeName.PDLTYPE, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ExternalReferenceMissing
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute ExternalReferenceMissing
          * @param value: the value to set the attribute to
          */
        public void setExternalReferenceMissing(boolean value)
        {
            setAttribute(AttributeName.EXTERNALREFERENCEMISSING, value, null);
        }



        /**
          * (18) get boolean attribute ExternalReferenceMissing
          * @return boolean the value of the attribute
          */
        public boolean getExternalReferenceMissing()
        {
            return getBoolAttribute(AttributeName.EXTERNALREFERENCEMISSING, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute HasExternalReference
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HasExternalReference
          * @param value: the value to set the attribute to
          */
        public void setHasExternalReference(boolean value)
        {
            setAttribute(AttributeName.HASEXTERNALREFERENCE, value, null);
        }



        /**
          * (18) get boolean attribute HasExternalReference
          * @return boolean the value of the attribute
          */
        public boolean getHasExternalReference()
        {
            return getBoolAttribute(AttributeName.HASEXTERNALREFERENCE, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute HasOPI
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HasOPI
          * @param value: the value to set the attribute to
          */
        public void setHasOPI(boolean value)
        {
            setAttribute(AttributeName.HASOPI, value, null);
        }



        /**
          * (18) get boolean attribute HasOPI
          * @return boolean the value of the attribute
          */
        public boolean getHasOPI()
        {
            return getBoolAttribute(AttributeName.HASOPI, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute OPIMissing
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OPIMissing
          * @param value: the value to set the attribute to
          */
        public void setOPIMissing(boolean value)
        {
            setAttribute(AttributeName.OPIMISSING, value, null);
        }



        /**
          * (18) get boolean attribute OPIMissing
          * @return boolean the value of the attribute
          */
        public boolean getOPIMissing()
        {
            return getBoolAttribute(AttributeName.OPIMISSING, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute OPIType
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OPIType
          * @param value: the value to set the attribute to
          */
        public void setOPIType(String value)
        {
            setAttribute(AttributeName.OPITYPE, value, null);
        }



        /**
          * (23) get String attribute OPIType
          * @return the value of the attribute
          */
        public String getOPIType()
        {
            return getAttribute(AttributeName.OPITYPE, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute OPIVersion
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute OPIVersion
          * @param value: the value to set the attribute to
          */
        public void setOPIVersion(VString value)
        {
            setAttribute(AttributeName.OPIVERSION, value, null);
        }



        /**
          * (21) get VString attribute OPIVersion
          * @return VString the value of the attribute
          */
        public VString getOPIVersion()
        {
            VString vStrAttrib = new VString();
            String  s = getAttribute(AttributeName.OPIVERSION, null, JDFConstants.EMPTYSTRING);
            vStrAttrib.setAllStrings(s, " ");
            return vStrAttrib;
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute ShadingType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute ShadingType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setShadingType(EnumShadingType enumVar)
        {
            setAttribute(AttributeName.SHADINGTYPE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute ShadingType
          * @return the value of the attribute
          */
        public EnumShadingType getShadingType()
        {
            return EnumShadingType.getEnum(getAttribute(AttributeName.SHADINGTYPE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute HasStrokeColor
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute HasStrokeColor
          * @param value: the value to set the attribute to
          */
        public void setHasStrokeColor(boolean value)
        {
            setAttribute(AttributeName.HASSTROKECOLOR, value, null);
        }



        /**
          * (18) get boolean attribute HasStrokeColor
          * @return boolean the value of the attribute
          */
        public boolean getHasStrokeColor()
        {
            return getBoolAttribute(AttributeName.HASSTROKECOLOR, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute StrokeAlternateColorSpace
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute StrokeAlternateColorSpace
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setStrokeAlternateColorSpace(EnumStrokeAlternateColorSpace enumVar)
        {
            setAttribute(AttributeName.STROKEALTERNATECOLORSPACE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute StrokeAlternateColorSpace
          * @return the value of the attribute
          */
        public EnumStrokeAlternateColorSpace getStrokeAlternateColorSpace()
        {
            return EnumStrokeAlternateColorSpace.getEnum(getAttribute(AttributeName.STROKEALTERNATECOLORSPACE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute StrokeColorName
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StrokeColorName
          * @param value: the value to set the attribute to
          */
        public void setStrokeColorName(String value)
        {
            setAttribute(AttributeName.STROKECOLORNAME, value, null);
        }



        /**
          * (23) get String attribute StrokeColorName
          * @return the value of the attribute
          */
        public String getStrokeColorName()
        {
            return getAttribute(AttributeName.STROKECOLORNAME, null, JDFConstants.EMPTYSTRING);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute StrokeColorSpace
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute StrokeColorSpace
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setStrokeColorSpace(EnumStrokeColorSpace enumVar)
        {
            setAttribute(AttributeName.STROKECOLORSPACE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute StrokeColorSpace
          * @return the value of the attribute
          */
        public EnumStrokeColorSpace getStrokeColorSpace()
        {
            return EnumStrokeColorSpace.getEnum(getAttribute(AttributeName.STROKECOLORSPACE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute StrokeColorType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute StrokeColorType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setStrokeColorType(EnumStrokeColorType enumVar)
        {
            setAttribute(AttributeName.STROKECOLORTYPE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute StrokeColorType
          * @return the value of the attribute
          */
        public EnumStrokeColorType getStrokeColorType()
        {
            return EnumStrokeColorType.getEnum(getAttribute(AttributeName.STROKECOLORTYPE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute StrokeOverprintFlag
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StrokeOverprintFlag
          * @param value: the value to set the attribute to
          */
        public void setStrokeOverprintFlag(boolean value)
        {
            setAttribute(AttributeName.STROKEOVERPRINTFLAG, value, null);
        }



        /**
          * (18) get boolean attribute StrokeOverprintFlag
          * @return boolean the value of the attribute
          */
        public boolean getStrokeOverprintFlag()
        {
            return getBoolAttribute(AttributeName.STROKEOVERPRINTFLAG, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute StrokeShadingType
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute StrokeShadingType
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setStrokeShadingType(EnumStrokeShadingType enumVar)
        {
            setAttribute(AttributeName.STROKESHADINGTYPE, enumVar.getName(), null);
        }



        /**
          * (9) get attribute StrokeShadingType
          * @return the value of the attribute
          */
        public EnumStrokeShadingType getStrokeShadingType()
        {
            return EnumStrokeShadingType.getEnum(getAttribute(AttributeName.STROKESHADINGTYPE, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute StrokeThickness
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute StrokeThickness
          * @param value: the value to set the attribute to
          */
        public void setStrokeThickness(double value)
        {
            setAttribute(AttributeName.STROKETHICKNESS, value, null);
        }



        /**
          * (17) get double attribute StrokeThickness
          * @return double the value of the attribute
          */
        public double getStrokeThickness()
        {
            return getRealAttribute(AttributeName.STROKETHICKNESS, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute CharacterProblem
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute CharacterProblem
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setCharacterProblem(EnumCharacterProblem enumVar)
        {
            setAttribute(AttributeName.CHARACTERPROBLEM, enumVar.getName(), null);
        }



        /**
          * (9) get attribute CharacterProblem
          * @return the value of the attribute
          */
        public EnumCharacterProblem getCharacterProblem()
        {
            return EnumCharacterProblem.getEnum(getAttribute(AttributeName.CHARACTERPROBLEM, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute MissingPrinterFont
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MissingPrinterFont
          * @param value: the value to set the attribute to
          */
        public void setMissingPrinterFont(boolean value)
        {
            setAttribute(AttributeName.MISSINGPRINTERFONT, value, null);
        }



        /**
          * (18) get boolean attribute MissingPrinterFont
          * @return boolean the value of the attribute
          */
        public boolean getMissingPrinterFont()
        {
            return getBoolAttribute(AttributeName.MISSINGPRINTERFONT, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute MissingScreenFont
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute MissingScreenFont
          * @param value: the value to set the attribute to
          */
        public void setMissingScreenFont(boolean value)
        {
            setAttribute(AttributeName.MISSINGSCREENFONT, value, null);
        }



        /**
          * (18) get boolean attribute MissingScreenFont
          * @return boolean the value of the attribute
          */
        public boolean getMissingScreenFont()
        {
            return getBoolAttribute(AttributeName.MISSINGSCREENFONT, null, false);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute TextSize
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute TextSize
          * @param value: the value to set the attribute to
          */
        public void setTextSize(double value)
        {
            setAttribute(AttributeName.TEXTSIZE, value, null);
        }



        /**
          * (17) get double attribute TextSize
          * @return double the value of the attribute
          */
        public double getTextSize()
        {
            return getRealAttribute(AttributeName.TEXTSIZE, null, 0.0);
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute UseArtificialTextEffect
        --------------------------------------------------------------------- */
        /**
          * (5) set attribute UseArtificialTextEffect
          * @param enumVar: the enumVar to set the attribute to
          */
        public void setUseArtificialTextEffect(EnumUseArtificialTextEffect enumVar)
        {
            setAttribute(AttributeName.USEARTIFICIALTEXTEFFECT, enumVar.getName(), null);
        }



        /**
          * (9) get attribute UseArtificialTextEffect
          * @return the value of the attribute
          */
        public EnumUseArtificialTextEffect getUseArtificialTextEffect()
        {
            return EnumUseArtificialTextEffect.getEnum(getAttribute(AttributeName.USEARTIFICIALTEXTEFFECT, null, null));
        }



        
        /* ---------------------------------------------------------------------
        Methods for Attribute NumberOfPathPoints
        --------------------------------------------------------------------- */
        /**
          * (36) set attribute NumberOfPathPoints
          * @param value: the value to set the attribute to
          */
        public void setNumberOfPathPoints(int value)
        {
            setAttribute(AttributeName.NUMBEROFPATHPOINTS, value, null);
        }



        /**
          * (15) get int attribute NumberOfPathPoints
          * @return int the value of the attribute
          */
        public int getNumberOfPathPoints()
        {
            return getIntAttribute(AttributeName.NUMBEROFPATHPOINTS, null, 0);
        }



}// end namespace JDF
