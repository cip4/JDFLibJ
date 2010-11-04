/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2010 The International Cooperation for the Integration of
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

package org.cip4.jdflib.resource.intent;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.JDFComment;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.span.JDFDurationSpan;
import org.cip4.jdflib.span.JDFEnumerationSpan;
import org.cip4.jdflib.span.JDFIntegerSpan;
import org.cip4.jdflib.span.JDFNameSpan;
import org.cip4.jdflib.span.JDFNumberSpan;
import org.cip4.jdflib.span.JDFOptionSpan;
import org.cip4.jdflib.span.JDFShapeSpan;
import org.cip4.jdflib.span.JDFSpanBase;
import org.cip4.jdflib.span.JDFSpanBase.EnumDataType;
import org.cip4.jdflib.span.JDFStringSpan;
import org.cip4.jdflib.span.JDFTimeSpan;
import org.cip4.jdflib.span.JDFXYPairSpan;
import org.w3c.dom.DOMException;

/**
 * 
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class JDFIntentResource extends JDFResource
{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFIntentResource(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFIntentResource
	 * @param myOwnerDocument 
	 * @param myNamespaceURI 
	 * @param qualifiedName 
	 * 
		 * @throws DOMException
	 */
	public JDFIntentResource(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFIntentResource
	 * @param myOwnerDocument 
	 * @param myNamespaceURI 
	 * @param qualifiedName 
	 * @param myLocalName 
	 * @throws DOMException 
	 * 
	 */
	public JDFIntentResource(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * 
	 * @see org.cip4.jdflib.resource.JDFResource#toString()
	 * @return
	 */
	@Override
	public String toString()
	{
		return "JDFIntent[  --> " + super.toString() + " ]";
	}

	/**
	 * get the best match actual value from any element that may contain spans
	 * @param root the parent element
	 * @param spanName the span element name
	 * @return
	 */
	public static String guessActual(JDFElement root, String spanName)
	{
		if (root == null)
			return null;
		KElement span = root.getElement(spanName);
		if (span instanceof JDFSpanBase)
		{
			return ((JDFSpanBase) span).guessActual();
		}
		return null;
	}

	/**
	 * set all actual values to the preset defined in preferred
	 * 
	 * @return number of elements modified
	 */
	public int preferredToActual()
	{
		return preferredToActual(null);
	}

	/**
	 * set actual values to the preset defined in preferred
	 * 
	 * @param  key the key of the span resource to modify, if null do all
	 * @return number of elements modified
	 */
	public int preferredToActual(String key)
	{
		int nDone = 0;
		if (!isLeaf())
		{
			VElement leaves = getLeaves(false);
			for (int i = 0; i < leaves.size(); i++)
			{
				JDFIntentResource ri = (JDFIntentResource) leaves.elementAt(i);
				nDone += ri.preferredToActual(key);
			}
			return nDone;
		}
		VString vKeys = new VString();
		if (KElement.isWildCard(key))
		{
			VElement v = getChildrenByTagName(null, null, new JDFAttributeMap(AttributeName.DATATYPE, (String) null), true, true, 0);
			for (int i = 0; i < v.size(); i++)
			{
				vKeys.add(v.elementAt(i).getNodeName());
			}
		}
		else
		{
			vKeys.add(key);
		}

		for (int i = 0; i < vKeys.size(); i++)
		{
			JDFSpanBase base = (JDFSpanBase) getElement(vKeys.elementAt(i), null, 0);
			if (base.preferredToActual())
			{
				nDone++;
			}
		}
		return nDone;
	}

	/**
	 * get a list of all span resources
	 * 
	 * @return VElement all Span elements of this
	 */
	public VElement getSpans()
	{
		VElement v = getChildElementVector(null, null, null, true, 0, false);

		for (int i = v.size() - 1; i >= 0; i--)
		{
			JDFElement e = (JDFElement) v.elementAt(i);
			if (e instanceof JDFComment)
			{
				v.remove(i);
			}
		}
		return v;
	}

	/**
	 * Typesafe attribute validation of Class
	 * 
	 * @return true if class is valid
	 */
	@Override
	public EnumResourceClass getValidClass()
	{
		return JDFResource.EnumResourceClass.Intent;
	}

	/**
	 * default initialization
	 * 
	 * @return true if successful
	 */
	@Override
	public boolean init()
	{
		boolean b = super.init();
		this.setResourceClass(EnumResourceClass.Intent);
		return b;
	}

	/**
	 * get a number span
	 * 
	 * @param strName name of the span element
	 * @return JDFNumberSpan the JDFNumberSpan
	 */
	public JDFNumberSpan getCreateNumberSpan(String strName)
	{
		return (JDFNumberSpan) getCreateSpan(strName, JDFSpanBase.EnumDataType.NumberSpan);
	}

	/**
	 * get an option (boolean) span
	 * 
	 * @param   strName name of the span element
	 * @return JDFOptionSpan the JDFOptionSpan
	 */
	public JDFOptionSpan getCreateOptionSpan(String strName)
	{
		return (JDFOptionSpan) getCreateSpan(strName, JDFSpanBase.EnumDataType.OptionSpan);
	}

	/**
	 * get an integer span
	 * 
	 * @param   strName name of the span element
	 *            exist
	 * @return JDFIntegerSpan the JDFIntegerSpan
	 */
	public JDFIntegerSpan getCreateIntegerSpan(String strName)
	{
		return (JDFIntegerSpan) getCreateSpan(strName, JDFSpanBase.EnumDataType.IntegerSpan);
	}

	/**
	 * get a namespan
	 * 
	 * @param strName name of the span element
	 * @return JDFNameSpan the JDFNameSpan
	 */
	public JDFNameSpan getCreateNameSpan(String strName)
	{
		return (JDFNameSpan) getCreateSpan(strName, JDFSpanBase.EnumDataType.NameSpan);
	}

	/**
	 * get an Enumeration span
	 * 
	 * @param  strName name of the span element
	 * @return JDFEnumerationSpan the JDFEnumerationSpan
	 */
	public JDFEnumerationSpan getCreateEnumerationSpan(String strName)
	{
		return (JDFEnumerationSpan) getCreateSpan(strName, JDFSpanBase.EnumDataType.EnumerationSpan);
	}

	/**
	 * get a string span
	 * 
	 * @param  strName name of the span element
	 * @return JDFStringSpan the JDFStringSpan
	 */
	public JDFStringSpan getCreateStringSpan(String strName)
	{
		return (JDFStringSpan) getCreateSpan(strName, JDFSpanBase.EnumDataType.StringSpan);
	}

	/**
	 * get a duration span
	 * 
	 * @param strName name of the span element
	 * @return getDurationSpan the getDurationSpan
	 */
	public JDFDurationSpan getCreateDurationSpan(String strName)
	{
		return (JDFDurationSpan) getCreateSpan(strName, JDFSpanBase.EnumDataType.DurationSpan);
	}

	/**
	 * get a time span
	 * 
	 * @param  strName name of the span element
	 * @return getTimeSpan the getTimeSpan
	 */
	public JDFTimeSpan getCreateTimeSpan(String strName)
	{
		return (JDFTimeSpan) getCreateSpan(strName, JDFSpanBase.EnumDataType.TimeSpan);
	}

	/**
	 * get a XYPair span
	 * 
	 * @param  strName name of the span element
	 * @return JDFOptionSpan the JDFOptionSpan
	 */
	public JDFXYPairSpan getCreateCreateXYPairSpan(String strName)
	{
		return (JDFXYPairSpan) getCreateSpan(strName, JDFSpanBase.EnumDataType.XYPairSpan);
	}

	/**
	 * get a Shape span
	 * 
	 * @param  strName name of the span element
	 * @return JDFOptionSpan the JDFOptionSpan
	 */
	public JDFShapeSpan getCreateCreateShapeSpan(String strName)
	{
		return (JDFShapeSpan) getCreateSpan(strName, JDFSpanBase.EnumDataType.ShapeSpan);
	}

	/**
	 * get a number span
	 * 
	 * @param   strName name of the span element
	 * @return JDFNumberSpan the JDFNumberSpan
	 */
	public JDFNumberSpan getNumberSpan(String strName)
	{
		return (JDFNumberSpan) getSpan(strName, EnumDataType.NumberSpan);
	}

	/**
	 * get an option (boolean) span
	 * 
	 * @param   strName name of the span element
	 * @return JDFOptionSpan the JDFOptionSpan
	 */
	public JDFOptionSpan getOptionSpan(String strName)
	{
		return (JDFOptionSpan) getSpan(strName, EnumDataType.OptionSpan);
	}

	/**
	 * get an integer span
	 * 
	 * @param  strName name of the span element
	 * @return getIntegerSpan the getIntegerSpan
	 */
	public JDFIntegerSpan getIntegerSpan(String strName)
	{
		return (JDFIntegerSpan) getSpan(strName, EnumDataType.IntegerSpan);
	}

	/**
	 * get a namespan
	 * 
	 * @param strName name of the span element
	 * @return JDFNameSpan the JDFNameSpan
	 */
	public JDFNameSpan getNameSpan(String strName)
	{
		return (JDFNameSpan) getSpan(strName, EnumDataType.NameSpan);
	}

	/**
	 * get an Enumeration span
	 * 
	 * @param  strName name of the span element
	 * @return getEnumerationSpan the getEnumerationSpan
	 */
	public JDFEnumerationSpan getEnumerationSpan(String strName)
	{
		return (JDFEnumerationSpan) getSpan(strName, EnumDataType.EnumerationSpan);
	}

	/**
	 * get a string span
	 * 
	 * @param  strName name of the span element
	 * @return JDFStringSpan the JDFStringSpan
	 */
	public JDFStringSpan getStringSpan(String strName)
	{
		return (JDFStringSpan) getSpan(strName, EnumDataType.StringSpan);
	}

	/**
	 * get a duration span
	 * 
	 * @param  strName name of the span element
	 * @return getDurationSpan the getDurationSpan
	 */
	public JDFDurationSpan getDurationSpan(String strName)
	{
		return (JDFDurationSpan) getSpan(strName, EnumDataType.DurationSpan);
	}

	/**
	 * get a time span
	 * 
	 * @param  strName name of the span element
	 * @return getTimeSpan the getTimeSpan
	 */
	public JDFTimeSpan getTimeSpan(String strName)
	{
		return (JDFTimeSpan) getSpan(strName, EnumDataType.TimeSpan);
	}

	/**
	 * get a XYPair span
	 * 
	 * @param  strName name of the span element
	 * @return JDFXYPairSpan the JDFXYPairSpan
	 */
	public JDFXYPairSpan getXYPairSpan(String strName)
	{
		return (JDFXYPairSpan) getSpan(strName, EnumDataType.XYPairSpan);
	}

	/**
	 * get a Shape span
	 * 
	 * @param  strName name of the span element
	 * @return JDFShapeSpan the JDFShapeSpan
	 */
	public JDFShapeSpan getShapeSpan(String strName)
	{
		return (JDFShapeSpan) getSpan(strName, EnumDataType.ShapeSpan);
	}

	/**
	 * Append a number span if it does not yet exist, else return the existing
	 * element
	 * 
	 * @param  strName name of the span element
	 * @return JDFNumberSpan the JDFNumberSpan
	 */
	public JDFNumberSpan appendNumberSpan(String strName)
	{
		return (JDFNumberSpan) appendSpan(strName, JDFSpanBase.EnumDataType.NumberSpan);
	}

	/**
	 * Append an option (boolean) span
	 * 
	 * @param  strName name of the span element
	 * @return JDFOptionSpan the JDFOptionSpan
	 */
	public JDFOptionSpan appendOptionSpan(String strName)
	{
		return (JDFOptionSpan) appendSpan(strName, JDFSpanBase.EnumDataType.OptionSpan);
	}

	/**
	 * Append an integer span if it does not yet exist, else return the existing
	 * element
	 * 
	 * @param strName name of the span element
	 * @return JDFIntegerSpan the JDFIntegerSpan
	 */
	public JDFIntegerSpan appendIntegerSpan(String strName)
	{
		return (JDFIntegerSpan) appendSpan(strName, JDFSpanBase.EnumDataType.IntegerSpan);
	}

	/**
	 * Append a name span if it does not yet exist, else return the existing
	 * element
	 * 
	 * @param strName name of the span element
	 * @return JDFNameSpan the JDFNameSpan
	 */
	public JDFNameSpan appendNameSpan(String strName)
	{
		return (JDFNameSpan) appendSpan(strName, JDFSpanBase.EnumDataType.NameSpan);
	}

	/**
	 * Append an Enumeration span if it does not yet exist, else return the
	 * existing element
	 * 
	 * @param  strName name of the span element
	 * @return JDFEnumerationSpan the JDFEnumerationSpan
	 */
	public JDFEnumerationSpan appendEnumerationSpan(String strName)
	{
		return (JDFEnumerationSpan) appendSpan(strName, JDFSpanBase.EnumDataType.EnumerationSpan);
	}

	/**
	 * Append a string span if it does not yet exist, else return the existing
	 * element
	 * 
	 * @param   strName name of the span element
	 * @return JDFStringSpan the JDFStringSpan
	 */
	public JDFStringSpan appendStringSpan(String strName)
	{
		return (JDFStringSpan) appendSpan(strName, JDFSpanBase.EnumDataType.StringSpan);
	}

	/**
	 * Append a duration span if it does not yet exist, else return the existing
	 * element
	 * 
	 * @param strName name of the span element
	 * @return JDFDurationSpan the JDFDurationSpan
	 */
	public JDFDurationSpan appendDurationSpan(String strName)
	{
		return (JDFDurationSpan) appendSpan(strName, JDFSpanBase.EnumDataType.DurationSpan);
	}

	/**
	 * Append a time span if it does not yet exist, else return the existing
	 * element
	 * 
	 * @param  strName name of the span element
	 * @return JDFTimeSpan the JDFTimeSpan
	 */
	public JDFTimeSpan appendTimeSpan(String strName)
	{
		return (JDFTimeSpan) appendSpan(strName, JDFSpanBase.EnumDataType.TimeSpan);
	}

	/**
	 * Append a XYPair span if it does not yet exist, else return the existing
	 * element
	 * 
	 * @param  strName name of the span element
	 * @return JDFXYPairSpan the JDFXYPairSpan
	 */
	public JDFXYPairSpan appendXYPairSpan(String strName)
	{
		return (JDFXYPairSpan) appendSpan(strName, JDFSpanBase.EnumDataType.XYPairSpan);
	}

	/**
	 * Append a Shape span if it does not yet exist, else return the existing
	 * element
	 * 
	 * @param  strName name of the span element
	 * @return JDFShapeSpan the JDFShapeSpan
	 */
	public JDFShapeSpan appendShapeSpan(String strName)
	{
		return (JDFShapeSpan) appendSpan(strName, JDFSpanBase.EnumDataType.ShapeSpan);
	}

	/**
	 * get a span, create it if it does not exist
	 * 
	 * @param   strName name of the span element
	 * @param nType datatype of the new span
	 * @return JDFSpanBase the JDFSpanBase
	 */
	JDFSpanBase getCreateSpan(String strName, JDFSpanBase.EnumDataType nType)
	{
		// / note that this is the inherited version from JDFResource!
		JDFSpanBase e = (JDFSpanBase) this.getCreateElement_JDFResource(strName, JDFConstants.EMPTYSTRING, 0);
		e.setDataType(nType);
		return e;
	}

	/**
	 * get a span
	 * 
	 * @param  strName name of the span element
	 * @param nType datatype of the new span
	 * @return JDFSpanBase the JDFSpanBase
	 */
	public JDFSpanBase getSpan(String strName, JDFSpanBase.EnumDataType nType)
	{
		// / note that this is the inherited version from JDFResource!
		JDFSpanBase e = (JDFSpanBase) getElement(strName, JDFConstants.EMPTYSTRING, 0);
		if (e != null && nType != null)
		{
			JDFSpanBase.EnumDataType dataType = e.getDataType();
			if (!dataType.equals(nType))
			{
				throw new JDFException("JDFIntentResource.getSpan incompatible datatypes" + e.getAttribute(AttributeName.DATATYPE));
			}
		}
		return e;
	}

	/**
	 * Append a span if it does not yet exist, else return the existing element
	 * 
	 * @param  strName name of the span element
	 * @param  nType datatype of the new span
	 * @return JDFSpanBase the JDFSpanBase
	 */
	public JDFSpanBase appendSpan(String strName, JDFSpanBase.EnumDataType nType)
	{
		// / note that this is the inherited version from JDFResource!
		JDFSpanBase e = (JDFSpanBase) appendElement(strName, JDFConstants.EMPTYSTRING);
		if (nType != null)
			e.setDataType(nType);
		return e;
	}
} // class JDFIntentResource
