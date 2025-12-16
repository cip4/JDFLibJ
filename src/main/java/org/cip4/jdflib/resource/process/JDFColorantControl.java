/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFColorantControl.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.process;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoColorantControl;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFSeparationList;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.util.JavaEnumUtil;
import org.w3c.dom.DOMException;

/**
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 *         Aug 10, 2009
 */
public class JDFColorantControl extends JDFAutoColorantControl
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFColorantControl
	 *
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFColorantControl(final CoreDocumentImpl myOwnerDocument, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFColorantControl
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFColorantControl(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFColorantControl
	 *
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFColorantControl(final CoreDocumentImpl myOwnerDocument, final String myNamespaceURI, final String qualifiedName, final String myLocalName)
			throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	public enum EProcessColorModel
	{
		DeviceCMY, DeviceCMYK, DeviceGray, DeviceN, DeviceRGB, None;

		public static EProcessColorModel getEnum(String val)
		{
			return JavaEnumUtil.getEnumIgnoreCase(EProcessColorModel.class, val, null);
		}

	}

	/**
	 * (36) set attribute ProcessColorModel
	 *
	 * @param value the value to set the attribute to
	 */
	public void setProcessColorModel(EProcessColorModel model)
	{
		setAttribute(AttributeName.PROCESSCOLORMODEL, model, null);
	}

	/**
	 * (36) set attribute ProcessColorModel
	 *
	 * @param value the value to set the attribute to
	 */
	public EProcessColorModel getEProcessColorModel()
	{
		return EProcessColorModel.getEnum(getAttribute(AttributeName.PROCESSCOLORMODEL));
	}

	/**
	 * toString
	 *
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFColorantControl[  --> " + super.toString() + " ]";
	}

	/**
	 * get a list of all partition keys that this resource may be implicitly partitioned by e.g. RunIndex for RunList...
	 *
	 * @return vector of EnumPartIDKey
	 */

	@Override
	public Vector<EnumPartIDKey> getImplicitPartitions()
	{
		Vector<EnumPartIDKey> v = super.getImplicitPartitions();
		if (v == null)
		{
			v = new Vector<>();
		}
		v.add(EnumPartIDKey.Separation);
		return v;
	}

	/**
	 * get the list of separations that this colorantcontrol describes - adds the separations that are implied by ProcessColorModel uses devicecolorantorder if it is specified,
	 * else calls getColorantOrderSeparations()
	 *
	 * @return
	 */
	public VString getDeviceColorantOrderSeparations()
	{
		final StringArray tmp = getSeparations(ElementName.DEVICECOLORANTORDER);
		return tmp == null ? getColorantOrderSeparations() : new VString(tmp);
	}

	public VString getColorantParamSeparations()
	{
		final StringArray tmp = getSeparations(ElementName.COLORANTPARAMS);
		return tmp == null ? getColorantOrderSeparations() : new VString(tmp);
	}

	/**
	 * get the list of separations that this colorantcontrol describes - adds the separations that are implied by ProcessColorModel uses devicecolorantorder if it is specified,
	 * else calls getColorantOrderSeparations()
	 *
	 * @return
	 */
	StringArray getSeparations(final String listName)
	{
		if (hasChildElement(listName, null))
		{
			final JDFSeparationList sl = (JDFSeparationList) getElement(listName);
			return sl.getSeparationList();
		}
		else if (hasNonEmpty(listName))
		{
			return new StringArray(getNonEmpty(listName));
		}
		else
		{
			return null;
		}
	}

	/**
	 * get the list of separations that this colorantcontrol describes - adds the separations that are implied by ProcessColorModel uses devicecolorantorder if it is specified,
	 * else calls getColorantOrderSeparations()
	 *
	 * @return
	 */
	void setSeparations(final String listName, List<String> seps)
	{
		if (isXJDF())
		{
			setAttribute(listName, seps, null);
		}
		else
		{
			final JDFSeparationList sl = (JDFSeparationList) getCreateElement_JDFElement(listName, null, 0);
			sl.setSeparations(seps);
		}
	}

	public void setDeviceColorantOrderSeparations(List<String> seps)
	{
		setSeparations(ElementName.DEVICECOLORANTORDER, seps);
	}

	public void setColorantOrderSeparations(List<String> seps)
	{
		setSeparations(ElementName.COLORANTORDER, seps);
	}

	public void setColorantParamSeparations(List<String> seps)
	{
		setSeparations(ElementName.COLORANTPARAMS, seps);
	}

	/**
	 * get the list of separations that this colorantcontrol describes - adds the separations that are implied by ProcessColorModel uses colorantorder if it is specified, else
	 * calls getSeparations()
	 *
	 * @return
	 */
	public VString getColorantOrderSeparations()
	{
		final StringArray tmp = getSeparations(ElementName.COLORANTORDER);
		return tmp == null ? getSeparations() : new VString(tmp);
	}

	/**
	 * get the list of separations that this colorantcontrol describes adds the separations that are implied by ProcessColorModel
	 *
	 * @return VString the complete list of process and spot colors
	 */
	public VString getAllSeparations()
	{
		final List<JDFResource> e = getLeafArray(false);
		if (e == null)
		{
			return null;
		}
		final VString allCols = new VString();
		for (final JDFResource element : e)
		{
			allCols.addAll(((JDFColorantControl) element).getSeparations());
		}
		allCols.unify();
		return allCols;
	}

	/**
	 * get the list of separations that this colorantcontrol describes - adds the separations that are implied by ProcessColorModel ignores colorantorder and devicecolorantorder
	 *
	 * @return VString the complete list of process and spot colors
	 */
	public VString getSeparations()
	{
		if (hasAttribute(ElementName.COLORANTPARAMS) && !hasChildElement(ElementName.COLORANTPARAMS, null))
		{
			return new VString(getSeparations(ElementName.COLORANTPARAMS));
		}
		final VString vName = getProcessSeparations();
		vName.appendUnique(getSeparations(ElementName.COLORANTPARAMS));
		return vName;
	}

	/**
	 * get the list of separations that the value of ProcessColorModel implies adds the separations that are implied by ProcessColorModel ignores colorantorder and
	 * devicecolorantorder
	 *
	 * @return VString the complete list of process and spot colors
	 */
	public VString getProcessSeparations()
	{
		VString vName = new VString();
		final EProcessColorModel model = getEProcessColorModel();
		if (EProcessColorModel.DeviceCMY.equals(model))
		{
			vName.add(JDFConstants.SEPARATION_CYAN);
			vName.add(JDFConstants.SEPARATION_MAGENTA);
			vName.add(JDFConstants.SEPARATION_YELLOW);
		}
		else if (EProcessColorModel.DeviceCMYK.equals(model))
		{
			vName.add(JDFConstants.SEPARATION_CYAN);
			vName.add(JDFConstants.SEPARATION_MAGENTA);
			vName.add(JDFConstants.SEPARATION_YELLOW);
			vName.add(JDFConstants.SEPARATION_BLACK);
		}
		else if (EProcessColorModel.DeviceGray.equals(model))
		{
			vName.add(JDFConstants.SEPARATION_BLACK);
		}
		else if (EProcessColorModel.DeviceRGB.equals(model))
		{
			vName.add(JDFConstants.SEPARATION_RED);
			vName.add(JDFConstants.SEPARATION_GREEN);
			vName.add(JDFConstants.SEPARATION_BLUE);
		}
		else if (EProcessColorModel.DeviceN.equals(model))
		{
			final JDFDeviceNSpace deviceNSpace = getDeviceNSpace(0);
			if (deviceNSpace != null)
			{
				vName = deviceNSpace.getSeparations();
			}
		}

		return vName;
	}

	/**
	 * always reuse a colorpool
	 */
	@Override
	public JDFColorPool getCreateColorPool()
	{
		final JDFColorPool cp = getColorPool();
		return cp == null ? super.getCreateColorPool() : cp;
	}

	/**
	 * @param sourceColor the source color to search
	 * @return
	 */
	public JDFColorantAlias getColorantAlias(final String sourceColor)
	{
		final Collection<JDFColorantAlias> vcc = getChildArrayByClass(JDFColorantAlias.class, false, 0);
		for (final JDFColorantAlias ca : vcc)
		{
			final VString seps = ca.getSeparations();
			if (seps.contains(sourceColor))
			{
				return ca;
			}
		}
		return null;
	}

	/**
	 * @param replacementColor the source color to search
	 * @return
	 */
	public JDFColorantAlias getColorantAliasForReplacement(final String replacementColor)
	{
		return getChildWithAttribute(JDFColorantAlias.class, AttributeName.REPLACEMENTCOLORANTNAME, replacementColor);
	}

	/**
	 * @param replacementColor the source color to search
	 * @return
	 */
	public JDFColorantAlias getCreateColorantAliasForReplacement(final String replacementColor)
	{
		JDFColorantAlias c = getColorantAliasForReplacement(replacementColor);
		if (c == null)
		{
			c = appendColorantAlias();
			c.setReplacementColorantName(replacementColor);
		}
		return c;
	}

	/**
	 * @param sourceColor the source color to search
	 * @return
	 */
	public JDFAttributeMap getColorantAliasMap()
	{
		final JDFAttributeMap map = new JDFAttributeMap();
		final Collection<JDFColorantAlias> vcc = getChildArrayByClass(JDFColorantAlias.class, false, 0);
		for (final JDFColorantAlias ca : vcc)
		{
			final VString seps = ca.getSeparations();
			final String target = ca.getReplacementColorantName();
			for (final String sep : seps)
			{
				map.put(sep, target);
			}
		}
		return map;
	}

	/**
	 * remove implied process colorors from the params list
	 */
	public void removeProcessColors()
	{
		final VString processSeps = getProcessSeparations();
		if (!processSeps.isEmpty())
		{
			final JDFSeparationList params = getColorantParams();
			if (params != null)
			{
				final VString oldList = params.getSeparations();
				oldList.removeAll(processSeps);
				if (oldList.isEmpty())
				{
					params.deleteNode();
				}
				else
				{
					params.setSeparations(oldList);
				}
			}
		}
	}

	/**
	 * @param source
	 * @param replacement
	 * @return
	 */
	public JDFColorantAlias appendColorantAlias(final String source, final String replacement)
	{
		final JDFColorantAlias ca = getCreateColorantAliasForReplacement(replacement);
		ca.setReplacementColorantName(replacement);
		ca.appendSeparationSpec().setName(source);
		return ca;
	}

}
