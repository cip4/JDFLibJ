/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2015 The International Cooperation for the Integration of 
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
package org.cip4.jdflib.extensions;

import org.cip4.jdflib.auto.JDFAutoValue.EnumValueUsage;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.jmf.JDFDeviceInfo;
import org.cip4.jdflib.resource.devicecapability.JDFBooleanState;
import org.cip4.jdflib.resource.devicecapability.JDFDateTimeState;
import org.cip4.jdflib.resource.devicecapability.JDFDeviceCap;
import org.cip4.jdflib.resource.devicecapability.JDFNumberState;
import org.cip4.jdflib.resource.devicecapability.JDFStringState;
import org.cip4.jdflib.util.JDFDate;
import org.cip4.jdflib.util.StringUtil;

public class CapabilitiesCreator extends BaseElementWalker
{
	/**
	 * the URL walker
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen 
	 */
	public class WalkElement extends BaseWalker
	{

		/**
		 * 
		 */
		public WalkElement()
		{
			super(getFactory());
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#walk(org.cip4.jdflib.core.KElement, org.cip4.jdflib.core.KElement)
		 * @param e
		 * @param trackElem
		 * @return
		 */
		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			String xpath = e.buildXPath(null, 0);
			KElement elmState = trackElem.getCreateChildWithAttribute("ElementState", AttributeName.XPATH, null, xpath, 0);
			elmState.setAttribute(AttributeName.MINOCCURS, "1");
			JDFAttributeMap map = e.getAttributeMap();
			if (map != null)
			{
				for (String key : map.keySet())
				{
					walkAttribute(key, e, trackElem);
				}
			}
			return trackElem;
		}

		/**
		 * 
		 * @param key
		 * @param e
		 * @param trackElem
		 */
		private void walkAttribute(String key, KElement e, KElement trackElem)
		{
			String xpath = e.buildXPath(null, 0) + "/@" + key;
			String val = StringUtil.getNonEmpty(e.getAttribute(key));
			if (val == null)
				return;
			String elmType = getStateType(val);
			KElement elmState = trackElem.getCreateChildWithAttribute(elmType, AttributeName.XPATH, null, xpath, 0);
			updateValue(elmState, val);
		}

		/**
		 * 
		 * @param elmState
		 * @param val
		 */
		private void updateValue(KElement elmState, String val)
		{
			if (elmState instanceof JDFStringState)
			{
				updateStringState((JDFStringState) elmState, val);
			}
			else if (elmState instanceof JDFNumberState)
			{
				updateNumberState((JDFNumberState) elmState, val);
			}
			else if (elmState instanceof JDFBooleanState)
			{
				updateBooleanState((JDFBooleanState) elmState, val);
			}
			else if (elmState instanceof JDFDateTimeState)
			{
				updateDateTimeState((JDFDateTimeState) elmState, val);
			}

		}

		/**
		 * 
		 * @param elmState
		 * @param val
		 */
		private void updateStringState(JDFStringState elmState, String val)
		{
			String regexp = elmState.getAllowedRegExp();
			if (regexp.endsWith("+"))
			{
				regexp = regexp.substring(1);
				regexp = StringUtil.leftStr(regexp, -2);
			}
			VString vr = VString.getVString(regexp, "|");
			VString vVal = VString.getVString(val, " ");

			if (vr == null)
				vr = new VString();
			for (String val2 : vVal)
			{
				val2 = StringUtil.escape(val2, "{}()[].+*?^$\\|", "\\", 0, 0, -1, -1);
				vr.appendUnique("(" + val2 + ")");
			}
			boolean bList = vr.size() > 1;
			if (bList)
				vr.appendUnique("( )");
			String newExp = StringUtil.setvString(vr, "|", bList ? "(" : null, bList ? ")+" : null);

			elmState.setAllowedRegExp(newExp);
		}

		/**
		 * 
		 * @param elmState
		 * @param val
		 */
		private void updateBooleanState(JDFBooleanState elmState, String val)
		{
			elmState.setAttribute(AttributeName.ALLOWEDVALUELIST, "true false");
		}

		/**
		 * 
		 * @param elmState
		 * @param val
		 */
		private void updateNumberState(JDFNumberState elmState, String val)
		{
			double dVal = StringUtil.parseDouble(val, Double.NaN);
			if (!Double.isNaN(dVal))
			{
				double min = Double.NaN;
				if (elmState.hasAttribute(AttributeName.ALLOWEDVALUEMIN))
				{
					min = elmState.getAllowedValueMin();
					min = Math.min(min, dVal);
				}
				else
				{
					min = dVal;
				}
				elmState.setAllowedValueMin(min);

				double max = Double.NaN;
				if (elmState.hasAttribute(AttributeName.ALLOWEDVALUEMAX))
				{
					max = elmState.getAllowedValueMax();
					max = Math.max(min, dVal);
				}
				else
				{
					max = dVal;
				}
				elmState.setAllowedValueMax(max);
			}
		}

		/**
		 * 
		 * @param elmState
		 * @param val
		 */
		private void updateDateTimeState(JDFDateTimeState elmState, String val)
		{
			JDFDate date = JDFDate.createDate(val);
			if (date != null)
			{
				KElement dtv = elmState.getCreateElement("DateTimeValue");
				dtv.setAttribute(AttributeName.VALUEUSAGE, EnumValueUsage.Allowed.getName());
				JDFDate min = date;
				if (dtv.hasAttribute("MinValue"))
				{
					String minDate = dtv.getAttribute("MinValue");
					min = JDFDate.createDate(minDate);
					min = date.before(min) ? date : min;
				}
				dtv.setAttribute("MinValue", min.getDateTimeISO());

				JDFDate max = date;
				if (dtv.hasAttribute("MaxValue"))
				{
					String maxDate = dtv.getAttribute("MaxValue");
					max = JDFDate.createDate(maxDate);
					max = date.after(min) ? date : max;
				}
				dtv.setAttribute("MaxValue", max.getDateTimeISO());
			}
		}

		/**
		 * 
		 * @param val
		 * @return
		 */
		private String getStateType(String val)
		{
			if (StringUtil.isNumber(val))
				return "NumberState";
			if (StringUtil.isBoolean(val))
				return "BooleanState";
			if (JDFDate.createDate(val) != null)
				return "DateTimeState";
			return "StringState";
		}

	}

	static final String STATEPOOL = "StatePool";

	/**
	 * 
	 */
	public CapabilitiesCreator()
	{
		super(new BaseWalkerFactory());
	}

	/**
	 * 
	 * @param xjdf
	 * @return
	 */
	public JDFDeviceCap createCaps(KElement xjdf)
	{
		JDFDeviceCap dc = createDeviceCap();
		walkTree(xjdf, dc.getElement(STATEPOOL));
		return dc;
	}

	/**
	 * 
	 * @return
	 */
	JDFDeviceCap createDeviceCap()
	{
		JDFDoc doc = new JDFDoc(ElementName.DEVICEINFO);
		doc.setInitOnCreate(false);
		JDFDeviceInfo di = (JDFDeviceInfo) doc.getRoot();
		di.appendDevice().setDeviceID("devID");
		JDFDeviceCap dc = (JDFDeviceCap) di.appendElement(ElementName.DEVICECAP);
		dc.appendTestPool();
		dc.appendElement(STATEPOOL);
		return dc;
	}
}
