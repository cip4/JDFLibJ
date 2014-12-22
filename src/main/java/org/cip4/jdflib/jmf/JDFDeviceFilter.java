/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of
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
package org.cip4.jdflib.jmf;

import java.util.Collection;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoDeviceFilter;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFDevice;
import org.cip4.jdflib.resource.JDFDeviceList;
import org.cip4.jdflib.util.EnumUtil;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * < July 6, 2009
 */
public class JDFDeviceFilter extends JDFAutoDeviceFilter
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFDeviceFilter
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 */
	public JDFDeviceFilter(final CoreDocumentImpl myOwnerDocument, final String qualifiedName)
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFDeviceFilter
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 */
	public JDFDeviceFilter(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFDeviceFilter
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 */
	public JDFDeviceFilter(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString()
	 * 
	 * @see org.cip4.jdflib.auto.JDFAutoDeviceFilter#toString()
	 */
	@Override
	public String toString()
	{
		return "JDFDeviceFilter[  --> " + super.toString() + " ]";
	}

	/**
	 * apply this filter to a devicelist
	 * @param deviceList
	 */
	public void applyTo(final JDFDeviceList deviceList)
	{
		if (deviceList == null)
		{
			return;
		}
		final Collection<JDFDeviceInfo> v = deviceList.getAllDeviceInfo();
		if (v == null)
		{
			return;
		}
		for (JDFDeviceInfo di : v)
		{
			JDFDeviceInfo di2 = applyTo(di);
			if (di2 == null)
			{
				deviceList.removeChild(di);
			}
		}
	}

	/**
	 * apply this filter to a device element, potentially deleting it
	 * @param deviceInfo
	 */
	private JDFDeviceInfo applyTo(final JDFDeviceInfo deviceInfo)
	{
		if (deviceInfo != null)
		{
			final EnumDeviceDetails det = getDeviceDetails();
			if (EnumUtil.aLessThanB(det, EnumDeviceDetails.Capability))
			{
				JDFDevice dev = deviceInfo.getDevice();
				if (dev != null)
				{
					dev.removeChildren(ElementName.DEVICECAP, null, null);
				}
			}
			if (EnumUtil.aLessEqualsThanB(det, EnumDeviceDetails.Brief))
			{
				deviceInfo.removeChildren(ElementName.MODULESTATUS, null, null);
			}
			if (EnumUtil.aLessEqualsThanB(det, EnumDeviceDetails.Brief))
			{
				deviceInfo.removeChildren(ElementName.DEVICE, null, null);
			}
			if (EnumUtil.aLessEqualsThanB(det, EnumDeviceDetails.None))
			{
				final JDFAttributeMap map = new JDFAttributeMap();
				map.putNotNull(AttributeName.DEVICEID, deviceInfo.getDeviceID());
				map.putNotNull(AttributeName.DEVICESTATUS, deviceInfo.getDeviceStatus());
				map.putNotNull(AttributeName.DESCRIPTIVENAME, deviceInfo.getDescriptiveName());
				deviceInfo.removeAttributes(null);
				deviceInfo.setAttributes(map);
			}
		}
		return deviceInfo;
	}
}
