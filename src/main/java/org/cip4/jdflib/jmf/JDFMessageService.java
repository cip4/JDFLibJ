/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of
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
 ==========================================================================
 class JDFMessageService extends JDFResource
 ==========================================================================
 @COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
 ALL RIGHTS RESERVED
 Warning! very preliminary test version. Interface subject to change without prior notice!
 Revision history:    ...
 **/
package org.cip4.jdflib.jmf;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDevCaps.EnumContext;
import org.cip4.jdflib.auto.JDFAutoMessageService;
import org.cip4.jdflib.auto.JDFAutoSignal.EnumChannelMode;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElemInfoTable;
import org.cip4.jdflib.core.ElementInfo;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.extensions.XJDFConstants;
import org.cip4.jdflib.ifaces.ICapabilityElement;
import org.cip4.jdflib.ifaces.IDeviceCapable;
import org.cip4.jdflib.jmf.JDFMessage.EnumFamily;
import org.cip4.jdflib.jmf.JDFMessage.EnumType;
import org.cip4.jdflib.resource.devicecapability.JDFDevCaps;
import org.cip4.jdflib.util.ContainerUtil;
import org.cip4.jdflib.util.JavaEnumUtil;

//----------------------------------
/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 */
public class JDFMessageService extends JDFAutoMessageService implements IDeviceCapable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFMessageService
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFMessageService(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFMessageService
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFMessageService(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	private static ElemInfoTable[] elemInfoTable = new ElemInfoTable[5];
	static
	{
		elemInfoTable[0] = new ElemInfoTable(ElementName.INTEGERSTATE, 0x66661111);
		elemInfoTable[1] = new ElemInfoTable(ElementName.NAMESTATE, 0x66661111);
		elemInfoTable[2] = new ElemInfoTable(ElementName.STRINGSTATE, 0x66661111);
		elemInfoTable[3] = new ElemInfoTable(ElementName.DURATIONSTATE, 0x66661111);
		elemInfoTable[4] = new ElemInfoTable(ElementName.ENUMERATIONSTATE, 0x66661111);
	}

	@Override
	protected ElementInfo getTheElementInfo()
	{
		return super.getTheElementInfo().updateReplace(elemInfoTable);
	}

	/**
	 * Constructor for JDFMessageService
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFMessageService(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * @see org.cip4.jdflib.auto.JDFAutoMessageService#toString()
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "JDFMessageService[  --> " + super.toString() + " ]";
	}

	/**
	 * Typesafe enumerated attribute Type
	 *
	 * @return EnumType: the enumeration value of the attribute
	 */
	public EnumType getEnumType()
	{
		return EnumType.getEnum(getNonEmpty(AttributeName.TYPE));
	}

	/**
	 * Set attribute Type
	 *
	 * @param value the value to set the attribute to
	 */
	public void setType(final EnumType value)
	{
		final String typeName = value == null ? null : value.getName();
		setType(typeName);
	}

	public enum EResponseMode
	{
		FireAndForget, Reliable, Response, Poll;

		public static EResponseMode getEnum(final String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EResponseMode.class, val);
		}

		public static List<EResponseMode> getEnums(final String val)
		{
			return JavaEnumUtil.getEnumList(EResponseMode.class, val, true);
		}

	}

	/**
	 * Typesafe enumerated attribute Type
	 *
	 * @return EnumType: the enumeration value of the attribute
	 */
	public List<EResponseMode> getResponseModes()
	{
		return EResponseMode.getEnums(getAttribute(XJDFConstants.ResponseModes));
	}

	/**
	 * Set attribute Type
	 *
	 * @param value the value to set the attribute to
	 */
	public void setResponseModes(final EResponseMode value)
	{
		setAttribute(XJDFConstants.ResponseModes, value, null);
	}

	/**
	 * Set attribute Type
	 *
	 * @param value the value to set the attribute to
	 */
	public void setResponseModes(final Collection<EResponseMode> values)
	{
		setAttribute(XJDFConstants.ResponseModes, JavaEnumUtil.getNameList(values, true), null);
	}

	/**
	 * Set attribute Type
	 *
	 * @param value the value to set the attribute to
	 */
	public void addResponseModes(final EResponseMode value)
	{
		final List<EResponseMode> l = getResponseModes();
		ContainerUtil.appendUnique(l, value);
		setResponseModes(value);
	}

	/**
	 * Method isCommand.
	 *
	 * @return boolean
	 * @deprecated use getCommand
	 */
	@Deprecated
	public boolean isCommand()
	{
		return getCommand();
	}

	/*
	 * --------------------------------------------------------------------- Methods for Attribute ChannelMode ---------------------------------------------------------------------
	 */
	/**
	 * (5) set attribute ChannelMode
	 *
	 * @param enumVar the enumVar to set the attribute to
	 */
	public void setChannelMode(final EnumChannelMode enumVar)
	{
		setAttribute(AttributeName.CHANNELMODE, enumVar == null ? null : enumVar.getName(), null);
	}

	/**
	 * Method isQuery.
	 *
	 * @return boolean
	 * @deprecated use getQuery
	 */
	@Deprecated
	public boolean isQuery()
	{
		return getQuery();
	}

	/**
	 * append a devcaps for this and set its context to JMF
	 */
	@Override
	public JDFDevCaps appendDevCaps()
	{
		final JDFDevCaps dcs = super.appendDevCaps();
		dcs.setContext(EnumContext.JMF);
		return dcs;
	}

	/**
	 * @see org.cip4.jdflib.ifaces.IDeviceCapable#getNamePathVector()
	 * @return the vector of name paths
	 */
	@Override
	public final VString getNamePathVector()
	{
		final VString vResult = new VString();
		final Vector<EnumFamily> families = getFamilies();
		if (families != null)
		{
			final int siz = families.size();
			for (int i = 0; i < siz; i++)
			{
				vResult.add(families.get(i).getName());
			}
		}

		return vResult;
	}

	/**
	 * get the list of supported families
	 *
	 * @return EnumFamily[] the list of supported families
	 */
	public Vector<EnumFamily> getFamilies()
	{
		final Vector<EnumFamily> fams = new Vector<EnumFamily>();
		if (getCommand())
		{
			fams.add(EnumFamily.Command);
		}
		if (getSignal())
		{
			fams.add(EnumFamily.Signal);
		}
		if (getQuery())
		{
			fams.add(EnumFamily.Query);
		}
		if (getRegistration())
		{
			fams.add(EnumFamily.Registration);
		}
		if (getAcknowledge())
		{
			fams.add(EnumFamily.Acknowledge);
		}
		return fams.size() == 0 ? null : fams;
	}

	/**
	 * set the list of supported families
	 *
	 * @param fams the Vector of EnumFamily of supported families
	 */
	public void setFamilies(final Vector<EnumFamily> fams)
	{
		setCommand(false);
		setSignal(false);
		setQuery(false);
		setRegistration(false);
		setAcknowledge(false);
		setPersistent(false);

		if (fams == null)
		{
			return;
		}
		for (int i = 0; i < fams.size(); i++)
		{
			try
			{
				final EnumFamily family = fams.elementAt(i);
				setFamily(family);
				if (EnumFamily.Signal.equals(family))
				{
					setPersistent(true);
				}
			}
			catch (final JDFException x)
			{ /* nop */
			}
		}
	}

	/**
	 * set the value of the family name to true
	 *
	 * @param family
	 */
	public void setFamily(final EnumFamily family)
	{
		if (family == null || EnumFamily.Response.equals(family))
		{
			throw new JDFException("setFamily: illegal family:" + family);
		}
		setAttribute(family.getName(), true, null);
	}

	/**
	 * @param id
	 * @return the capabilty description
	 */
	@Override
	public ICapabilityElement getTargetCap(final String id)
	{
		final KElement e = getTarget(id, null);
		if (e instanceof ICapabilityElement)
		{
			return (ICapabilityElement) e;
		}
		return null;
	}

}
