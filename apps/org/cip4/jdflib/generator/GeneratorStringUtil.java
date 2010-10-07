/**
 *
 *  Copyright (c)   2002 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *  Author:         Kai Mattern
 *  Titel:          StringUtil.java
 *  Version:        0.1
 *  Description:    The xml Schema is partitioned into many "complex type's" 
 *                  these types have children named "attributes" and "elements"
 *                  this file is for describing all values a "element" can have.
 *
 *  History:        03-13-2002  Kai Mattern started file
 *
 *  TBD:            getMinOccurs should return  int
 *                  isAttributeToAdd - Attribute SettingsPolicy is defined where ??
 *                  isAttributeToAdd - Attribute Locked is defined where ??
 *                  isAttributeToAdd - CommentURL is a Attribute from ???? (its used in JDFElement)
 *                  isAttributeToAdd - DescriptiveName is a Attribute from ??? (its used in JEFElement)
 *                  isAttributeToAdd - xmlns is a Attribute from ???? (its defined in KElement)
 *                  getAllValidElements - COMPLETE TBD
 *                  GetEnumValues - COMPLETE TBD
 *                  getAllValidElements - add all values a element can have
 */

/*
*   NOTE: This is a little sequence out of the 'Schema' to make comments of the methods more clear
*
*   <xs:complexType name="PhaseTimeAudit">
*       <xs:complexContent>
*           <xs:extension base="jdf:AuditElement">
*               <xs:sequence minOccurs="0" maxOccurs="unbounded">
*                   <xs:group ref="jdf:GenericElements" minOccurs="0" />
*                   <xs:element name="Device" type="jdf:Device_r" minOccurs="0" />
*                   <xs:element name="DeviceRef" type="jdf:ResourceRef" minOccurs="0" />
*                   <xs:element name="Employee" type="jdf:Employee_re" minOccurs="0" />
*                   <xs:element name="EmployeeRef" type="jdf:ResourceRef" minOccurs="0" />
*                   <xs:element name="ModulePhase" minOccurs="0">
*                       <xs:complexType>
*                           <xs:sequence minOccurs="0" maxOccurs="unbounded">
*                               <xs:group ref="jdf:GenericElements" minOccurs="0" />
*                               <xs:element name="Employee" type="jdf:Employee_re" minOccurs="0" />
*                               <xs:element name="EmployeeRef" type="jdf:ResourceRef" minOccurs="0" />
*                           </xs:sequence>
*                           .
*                           .
*                           ...etc
*/

//package
package org.cip4.jdflib.generator;

//imports

//======================================================================================================
//     StringUtil
//======================================================================================================

public class GeneratorStringUtil
{
	static final String strLineEnd = "\n";
	static final String strDepthOne = "\t";
	static final String strDepthTwo = "\t\t";
	static final String strDepthThree = "\t\t\t";

	public static String getStrAllCopyrightInformation()
	{

		StringBuffer strCopyrightInfo = new StringBuffer(1000);

		strCopyrightInfo.append("/*").append(strLineEnd);
		strCopyrightInfo.append(" * The CIP4 Software License, Version 1.0").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" * Copyright (c) 2001-2010 The International Cooperation for the Integration of").append(strLineEnd);
		strCopyrightInfo.append(" * Processes in  Prepress, Press and Postpress (CIP4).  All rights").append(strLineEnd);
		strCopyrightInfo.append(" * reserved.").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" * Redistribution and use in source and binary forms, with or without").append(strLineEnd);
		strCopyrightInfo.append(" * modification, are permitted provided that the following conditions").append(strLineEnd);
		strCopyrightInfo.append(" * are met:").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" * 1. Redistributions of source code must retain the above copyright").append(strLineEnd);
		strCopyrightInfo.append(" *    notice, this list of conditions and the following disclaimer.").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" * 2. Redistributions in binary form must reproduce the above copyright").append(strLineEnd);
		strCopyrightInfo.append(" *    notice, this list of conditions and the following disclaimer in").append(strLineEnd);
		strCopyrightInfo.append(" *    the documentation and/or other materials provided with the").append(strLineEnd);
		strCopyrightInfo.append(" *    distribution.").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" * 3. The end-user documentation included with the redistribution,").append(strLineEnd);
		strCopyrightInfo.append(" *    if any, must include the following acknowledgment:").append(strLineEnd);
		strCopyrightInfo.append(" *       \"This product includes software developed by the").append(strLineEnd);
		strCopyrightInfo.append(" *        The International Cooperation for the Integration of").append(strLineEnd);
		strCopyrightInfo.append(" *        Processes in  Prepress, Press and Postpress (www.cip4.org)\"").append(strLineEnd);
		strCopyrightInfo.append(" *    Alternately, this acknowledgment may appear in the software itself,").append(strLineEnd);
		strCopyrightInfo.append(" *    if and wherever such third-party acknowledgments normally appear.").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" * 4. The names \"CIP4\" and \"The International Cooperation for the Integration of").append(strLineEnd);
		strCopyrightInfo.append(" *    Processes in  Prepress, Press and Postpress\" must").append(strLineEnd);
		strCopyrightInfo.append(" *    not be used to endorse or promote products derived from this").append(strLineEnd);
		strCopyrightInfo.append(" *    software without prior written permission. For written").append(strLineEnd);
		strCopyrightInfo.append(" *    permission, please contact info@cip4.org.").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" * 5. Products derived from this software may not be called \"CIP4\",").append(strLineEnd);
		strCopyrightInfo.append(" *    nor may \"CIP4\" appear in their name, without prior written").append(strLineEnd);
		strCopyrightInfo.append(" *    permission of the CIP4 organization").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" * Usage of this software in commercial products is subject to restrictions. For").append(strLineEnd);
		strCopyrightInfo.append(" * details please consult info@cip4.org.").append(strLineEnd);
		strCopyrightInfo.append("  *").append(strLineEnd);
		strCopyrightInfo.append(" * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED").append(strLineEnd);
		strCopyrightInfo.append(" * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES").append(strLineEnd);
		strCopyrightInfo.append(" * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE").append(strLineEnd);
		strCopyrightInfo.append(" * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR").append(strLineEnd);
		strCopyrightInfo.append(" * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR").append(strLineEnd);
		strCopyrightInfo.append(" * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,").append(strLineEnd);
		strCopyrightInfo.append(" * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT").append(strLineEnd);
		strCopyrightInfo.append(" * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF").append(strLineEnd);
		strCopyrightInfo.append(" * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND").append(strLineEnd);
		strCopyrightInfo.append(" * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,").append(strLineEnd);
		strCopyrightInfo.append(" * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT").append(strLineEnd);
		strCopyrightInfo.append(" * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF").append(strLineEnd);
		strCopyrightInfo.append(" * SUCH DAMAGE.").append(strLineEnd);
		strCopyrightInfo.append(" * ====================================================================").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" * This software consists of voluntary contributions made by many").append(strLineEnd);
		strCopyrightInfo.append(" * individuals on behalf of the The International Cooperation for the Integration").append(strLineEnd);
		strCopyrightInfo.append(" * of Processes in Prepress, Press and Postpress and was").append(strLineEnd);
		strCopyrightInfo.append(" * originally based on software").append(strLineEnd);
		strCopyrightInfo.append(" * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG").append(strLineEnd);
		strCopyrightInfo.append(" * copyright (c) 1999-2001, Agfa-Gevaert N.V.").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" * For more information on The International Cooperation for the").append(strLineEnd);
		strCopyrightInfo.append(" * Integration of Processes in  Prepress, Press and Postpress , please see").append(strLineEnd);
		strCopyrightInfo.append(" * <http://www.cip4.org/>.").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" *").append(strLineEnd);
		strCopyrightInfo.append(" */").append(strLineEnd);
		return strCopyrightInfo.toString();
	}

	public static String getStrCloseFile()
	{
		return "}";
	}
}
