/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2024 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment mrSubRefay appear in the software itself, if and wherever such third-party
 * acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior writtenrestartProcesses() permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software restartProcesses() copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import org.cip4.jdflib.util.JavaEnumUtil;

public class XSDConstants
{
	private XSDConstants()
	{
		super();
	}

	static final String NAME = "name";
	static final String REF = "ref";
	static final String TARGET_NAMESPACE = "targetNamespace";
	static final String XMLNS = "xmlns";
	static final String TYPE = "type";
	static final String BASE = "base";
	static final String USE = "use";
	static final String VALUE = "value";
	static final String MIN_OCCURS = "minOccurs";
	static final String MAX_OCCURS = "maxOccurs";

	static final String ITEM_TYPE = "itemType";
	static final String SUBSTITUTION_GROUP = "substitutionGroup";

	static final String XS_ATTRIBUTE = "xs:attribute";
	static final String XS_ELEMENT = "xs:element";
	static final String XS_EXTENSION = "xs:extension";
	static final String XS_COMPLEX_CONTENT = "xs:complexContent";
	static final String XS_COMPLEX_TYPE = "xs:complexType";
	static final String XS_GROUP = "xs:group";
	static final String XS_LIST = "xs:list";
	static final String XS_RESTRICTION = "xs:restriction";
	static final String XS_SIMPLE_TYPE = "xs:simpleType";
	static final String XS_SCHEMA = "xs:schema";
	static final String XS_ANY = "xs:any";
	static final String XS_ANY_ATTRIBUTE = "xs:anyAttribute";
	static final String XS_SEQUENCE = "xs:sequence";
	public static final String XS_STRING = "xs:string";
	public static final String XS_DURATION = "xs:duration";
	public static final String XS_ENUMERATION = "xs:enumeration";
	public static final String XS_NMTOKEN = "xs:NMTOKEN";
	public static final String XS_NMTOKENS = "xs:NMTOKENS";
	public static final String XS_PATTERN = "xs:pattern";
	public static final String XS_BOOLEAN = "xs:boolean";
	public static final String UNBOUNDED = "unbounded";
	public static final String XS_ID = "xs:ID";
	public static final String XS_DATETIME = "xs:dateTime";

	enum eAttributeUse
	{
		optional, prohibited, required;

		static eAttributeUse getEnum(final String s)
		{
			return JavaEnumUtil.getEnumIgnoreCase(eAttributeUse.class, s, optional);
		}
	}

}
