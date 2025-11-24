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

import org.cip4.jdflib.auto.JDFAutoDeviceInfo.EnumDeviceStatus;
import org.cip4.jdflib.auto.JDFAutoJobPhase.EnumDeadLine;
import org.cip4.jdflib.auto.JDFAutoMISDetails.EnumDeviceOperationMode;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.JDFElement.EnumNodeStatus;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.extensions.MessageHelper.EFamily;
import org.cip4.jdflib.extensions.MessageHelper.EType;
import org.cip4.jdflib.extensions.XSDConstants.eAttributeUse;

public class XJDFToJDFSchema extends BaseElementWalker
{
	private final KElement xjdfXSD;

	public XJDFToJDFSchema(KElement xjdfXsd)
	{
		super(new BaseWalkerFactory());
		this.xjdfXSD = xjdfXsd;
	}

	public KElement update()
	{
		final KElement ret = createRoot();
		walkTree(xjdfXSD, ret);
		return ret;
	}

	KElement createRoot()
	{
		final KElement ret = KElement.createRoot(xjdfXSD.getNodeName(), xjdfXSD.getNamespaceURI());
		ret.setAttributes(xjdfXSD);
		ret.setAttribute(XSDConstants.TARGET_NAMESPACE, JDFElement.getSchemaURL());
		ret.setAttribute(XSDConstants.XMLNS, JDFElement.getSchemaURL());
		return ret;
	}

	public class WalkElement extends BaseWalker
	{
		public WalkElement()
		{
			super(getFactory());
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement a = trackElem.appendElement(e.getNodeName(), e.getNamespaceURI());
			a.setAttributes(e);
			return a;
		}

	}

	public class WalkRoot extends WalkElement
	{
		@Override
		public VString getElementNames()
		{
			return new VString("schema");
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			return trackElem;
		}

	}

	public class WalkSimpleType extends WalkElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return XSDConstants.XS_SIMPLE_TYPE.equals(e.getNodeName());
		}

	}

	public class WalkSequence extends WalkElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return XSDConstants.XS_SEQUENCE.equals(e.getNodeName());
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = trackElem.insertBefore(e.getNodeName(), trackElem.getFirstChild(), e.getNamespaceURI());
			copy.setAttributes(e);

			return copy;
		}

		@Override
		public void postWalk(final KElement e, final KElement trackElem)
		{

			if (e.numChildElements(XSDConstants.XS_ELEMENT, null) > 1)
			{
				e.setAttribute(XSDConstants.MIN_OCCURS, "0");
				e.setAttribute(XSDConstants.MAX_OCCURS, "unbounded");
			}
		}

	}

	public class WalkVersion extends WalkSimpleType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && AttributeName.VERSION.equals(e.getAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			XSDUtil.updateRestrictions(copy, new StringArray("1.2 1.3 1.4 1.5 1.6 1.7 1.8"));
			return null;
		}
	}

	public class WalkNodeStatus extends WalkSimpleType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && AttributeName.NODESTATUS.equals(e.getAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			XSDUtil.updateRestrictions(copy, EnumNodeStatus.class);
			return null;
		}
	}

	public class WalkDeviceStatus extends WalkSimpleType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && AttributeName.DEVICESTATUS.equals(e.getAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			XSDUtil.updateRestrictions(copy, EnumDeviceStatus.class);
			return null;
		}
	}

	public class WalkCostCenterID extends WalkXSAttribute
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && AttributeName.COSTCENTERID.equals(e.getAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement seq = trackElem.getCreateElement(XSDConstants.XS_SEQUENCE);
			if (seq.numChildElements(XSDConstants.XS_ELEMENT, null) > 0)
			{
				seq.setAttribute(XSDConstants.MIN_OCCURS, "0");
				seq.setAttribute(XSDConstants.MAX_OCCURS, "unbounded");
			}
			final KElement cs = seq.getCreateChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null, ElementName.COSTCENTER, 0);
			cs.setAttribute(XSDConstants.MIN_OCCURS, "0");
			cs.setAttribute(XSDConstants.MAX_OCCURS, "1");
			final KElement ct = cs.getCreateElement(XSDConstants.XS_COMPLEX_TYPE);
			XSDUtil.setXSAttribute(ct, AttributeName.COSTCENTERID, XSDConstants.XS_STRING, true);
			XSDUtil.setXSAttribute(ct, AttributeName.NAME, XSDConstants.XS_STRING, false);
			return null;
		}
	}

	public class WalkDeviceInfo extends WalkSequence
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && ElementName.DEVICEINFO.equals(e.getInheritedAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			updateDevice(copy, trackElem);
			return copy;
		}

		void updateDevice(KElement xsseq, KElement trackElem)
		{
			final KElement ref = xsseq.getCreateChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.REF, null, ElementName.DEVICE, 0);
			ref.setAttribute(XSDConstants.MIN_OCCURS, 0, null);
			ref.setAttribute(XSDConstants.MAX_OCCURS, 1, null);
			KElement newDev = xsseq.getDocRoot().getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null, ElementName.DEVICE, 0, false);
			if (newDev == null)
			{
				final KElement oldDev = trackElem.getDocRoot().getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null, ElementName.DEVICE, 0,
						false);
				if (oldDev == null)
				{
					newDev = trackElem.getDocRoot().getCreateChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null, ElementName.DEVICE, 0);
					final KElement newDevC = newDev.appendElement(XSDConstants.XS_COMPLEX_TYPE);
					XSDUtil.setXSAttribute(newDevC, AttributeName.DEVICEID, XSDConstants.XS_NMTOKEN, true);
					XSDUtil.setXSAttribute(newDevC, AttributeName.DEVICETYPE, XSDConstants.XS_STRING, false);
					XSDUtil.setXSAttribute(newDevC, AttributeName.PRODUCTID, XSDConstants.XS_NMTOKEN, false);
					XSDUtil.setXSAttribute(newDevC, AttributeName.MANUFACTURER, XSDConstants.XS_STRING, false);
					XSDUtil.setXSAttribute(newDevC, AttributeName.FRIENDLYNAME, XSDConstants.XS_STRING, false);
					XSDUtil.setXSAttribute(newDevC, AttributeName.SERIALNUMBER, XSDConstants.XS_STRING, false);

				}

			}
		}
	}

	public class WalkNotificationSeq extends WalkSequence
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && ElementName.NOTIFICATION.equals(e.getInheritedAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			updateEmployee(copy, trackElem);
			return copy;
		}

		void updateEmployee(KElement xsseq, KElement trackElem)
		{
			final KElement ref = xsseq.getCreateChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.REF, null, ElementName.EMPLOYEE, 0);
			ref.setAttribute(XSDConstants.MIN_OCCURS, 0, null);
			ref.setAttribute(XSDConstants.MAX_OCCURS, 1, null);
			KElement newEmployee = xsseq.getDocRoot().getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null, ElementName.EMPLOYEE, 0, false);
			if (newEmployee == null)
			{
				final KElement oldEmployee = trackElem.getDocRoot().getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null,
						ElementName.EMPLOYEE, 0, false);
				if (oldEmployee == null)
				{
					newEmployee = trackElem.getDocRoot().getCreateChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null, ElementName.EMPLOYEE, 0);
					final KElement newEmployeeC = newEmployee.appendElement(XSDConstants.XS_COMPLEX_TYPE);
					new WalkEmployeeInfoType().walk(newEmployeeC, newEmployeeC);
				}

			}
		}
	}

	public class WalkSignalStatusSeq extends WalkSequence
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && "SignalStatus".equals(e.getInheritedAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			addNotification(copy, trackElem);
			addStatusQuParams(copy, trackElem);
			final KElement di = e.getCreateChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.REF, null, ElementName.DEVICEINFO, 0);
			di.setAttribute(XSDConstants.MIN_OCCURS, 0, null);
			return copy;
		}

		void addNotification(KElement xsseq, KElement trackElem)
		{
			final KElement ref = xsseq.getCreateChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.REF, null, ElementName.NOTIFICATION, 0);
			ref.setAttribute(XSDConstants.MIN_OCCURS, 0, null);
			ref.setAttribute(XSDConstants.MAX_OCCURS, 1, null);

		}

		void addStatusQuParams(KElement xsseq, KElement trackElem)
		{
			final KElement ref = xsseq.getCreateChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.REF, null, ElementName.STATUSQUPARAMS, 0);
			ref.setAttribute(XSDConstants.MIN_OCCURS, 0, null);
			ref.setAttribute(XSDConstants.MAX_OCCURS, 1, null);
			KElement oldSQP = xjdfXSD.getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null, ElementName.STATUSQUPARAMS, 0, false);
			if (oldSQP == null)
			{
				final KElement oldDev = trackElem.getDocRoot().getChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null,
						ElementName.STATUSQUPARAMS, 0, false);
				if (oldDev == null)
				{
					oldSQP = xsseq.getDocRoot().getCreateChildWithAttribute(XSDConstants.XS_ELEMENT, XSDConstants.NAME, null, ElementName.STATUSQUPARAMS, 0);
					final KElement complex = oldSQP.appendElement(XSDConstants.XS_COMPLEX_TYPE);
					XSDUtil.setXSAttribute(complex, AttributeName.DEVICEDETAILS, XSDConstants.XS_NMTOKEN, false);
					XSDUtil.setXSAttribute(complex, AttributeName.JOBDETAILS, XSDConstants.XS_NMTOKEN, false);
					XSDUtil.setXSAttribute(complex, AttributeName.EMPLOYEEINFO, XSDConstants.XS_BOOLEAN, false);
					XSDUtil.setXSAttribute(complex, AttributeName.QUEUEINFO, XSDConstants.XS_BOOLEAN, false);
					XSDUtil.setXSAttribute(complex, AttributeName.JOBID, XSDConstants.XS_STRING, false);
					XSDUtil.setXSAttribute(complex, AttributeName.JOBPARTID, XSDConstants.XS_STRING, false);
				}
			}
			xsseq.setAttribute(XSDConstants.MIN_OCCURS, 0, null);
			xsseq.setAttribute(XSDConstants.MAX_OCCURS, XSDConstants.UNBOUNDED);

		}
	}

	public class WalkComplexType extends WalkElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return XSDConstants.XS_COMPLEX_TYPE.equals(e.getNodeName());
		}

		@Override
		public void postWalk(KElement b, KElement trackElem)
		{
			XSDUtil.setXSAttribute(b, AttributeName.DESCRIPTIVENAME, XSDConstants.XS_STRING, false);
		}
	}

	public class WalkExtension extends WalkElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return XSDConstants.XS_EXTENSION.equals(e.getNodeName());
		}

	}

	public class WalkAbstractMessageExtension extends WalkExtension
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && EFamily.getEnum(e.getInheritedAttribute(XSDConstants.NAME, null, null)) != null
					&& EType.getEnum(e.getInheritedAttribute(XSDConstants.NAME, null, null)) == null;
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			copy.setAttribute(XSDConstants.BASE, "Message");
			return copy;
		}

	}

	public class WalkMessageExtension extends WalkExtension
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && EFamily.getEnum(e.getInheritedAttribute(XSDConstants.NAME, null, null)) != null
					&& EType.getEnum(e.getInheritedAttribute(XSDConstants.NAME, null, null)) != null;
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			copy.setAttribute(XSDConstants.BASE, "Message");
			final KElement at = XSDUtil.setXSAttribute(copy, AttributeName.TYPE, null, true);
			final KElement st = at.appendElement(XSDConstants.XS_SIMPLE_TYPE);
			XSDUtil.updateRestrictions(st, new StringArray(EType.getEnum(e.getInheritedAttribute(XSDConstants.NAME)).name()));

			return copy;
		}

	}

	public class WalkMessage extends WalkComplexType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && "Message".equals(e.getAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			copyAttribute(copy, AttributeName.AGENTNAME);
			copyAttribute(copy, AttributeName.AGENTVERSION);
			copyAttribute(copy, AttributeName.REFID);
			copyAttribute(copy, AttributeName.DESCRIPTIVENAME);
			copyAttribute(copy, AttributeName.DEVICEID, AttributeName.SENDERID);
			XSDUtil.setXSAttribute(copy, AttributeName.SENDERID, XSDConstants.XS_STRING, false);
			copyAttribute(copy, AttributeName.TIME, AttributeName.TIME, false);
			final KElement v = copyAttribute(copy, AttributeName.VERSION);
			if (v != null)
			{
				v.setAttribute(XSDConstants.NAME, AttributeName.MAXVERSION);
				v.setAttribute(XSDConstants.USE, eAttributeUse.optional, null);
			}
			copyAttribute(copy, AttributeName.ID).setAttribute(XSDConstants.USE, eAttributeUse.optional, null);
			return null;
		}

	}

	public class WalkXSElement extends WalkElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return XSDConstants.XS_ELEMENT.equals(e.getNodeName());
		}

	}

	public class WalkXSAttribute extends WalkElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return XSDConstants.XS_ATTRIBUTE.equals(e.getNodeName());
		}

	}

	public class WalkSheetName extends WalkXSAttribute
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && AttributeName.SHEETNAME.equals(e.getAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			copy.setAttribute(XSDConstants.TYPE, XSDConstants.XS_STRING);
			final KElement sig = copy.getParentNode_KElement().copyElement(copy, null);
			sig.setAttribute(XSDConstants.NAME, AttributeName.SIGNATURENAME);
			return copy;
		}

	}

	private static final StringArray stringList = new StringArray(new String[] { AttributeName.JOBID, AttributeName.JOBPARTID, AttributeName.SENDERID });

	public class WalkStringType extends WalkXSAttribute
	{
		@Override
		public boolean matches(KElement e)
		{
			final String name = e.getAttribute(XSDConstants.NAME);
			return super.matches(e) && stringList.contains(name);
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			copy.setAttribute(XSDConstants.TYPE, XSDConstants.XS_STRING);
			return copy;
		}

	}

	public class WalkAbstractMessageElement extends WalkXSElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && EFamily.getEnum(e.getAttribute(XSDConstants.NAME)) != null && EType.getEnum(e.getAttribute(XSDConstants.NAME)) == null;
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			return null;
		}

	}

	public class WalkAbstractMessageType extends WalkComplexType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && EFamily.getEnum(e.getAttribute(XSDConstants.NAME)) != null && EType.getEnum(e.getAttribute(XSDConstants.NAME)) == null;
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			return null;
		}

	}

	public class WalkHeader extends WalkXSElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e)
					&& (XJDFConstants.Header.equals(e.getAttribute(XSDConstants.NAME)) || XJDFConstants.Header.equals(e.getAttribute(XSDConstants.REF)));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			return null;
		}

	}

	public class WalkXJMFElement extends WalkXSElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && XJDFConstants.XJMF.equals(e.getAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			copy.setAttribute(XSDConstants.NAME, ElementName.JMF);
			return copy;
		}

	}

	public class WalkXJMFComplex extends WalkComplexType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && XJDFConstants.XJMF.equals(e.getInheritedAttribute(XSDConstants.NAME, null, null));
		}

		@Override
		public void postWalk(final KElement copy, final KElement trackElem)
		{
			copyAttribute(copy, AttributeName.AGENTNAME);
			copyAttribute(copy, AttributeName.AGENTVERSION);
			copyAttribute(copy, AttributeName.REFID);
			copyAttribute(copy, AttributeName.DESCRIPTIVENAME);
			copyAttribute(copy, AttributeName.ICSVERSIONS);
			final KElement deviceID = copyAttribute(copy, AttributeName.DEVICEID, AttributeName.SENDERID);
			deviceID.setAttribute(XSDConstants.TYPE, XSDConstants.XS_STRING);

			copyAttribute(copy, AttributeName.TIME, AttributeName.TIMESTAMP);
			copyAttribute(copy, AttributeName.ID);
			final KElement v = copyAttribute(copy, AttributeName.VERSION, AttributeName.MAXVERSION);
			v.setAttribute(XSDConstants.USE, eAttributeUse.optional, null);

		}

	}

	public class WalkMessageElement extends WalkXSElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && EFamily.getEnum(e.getAttribute(XSDConstants.NAME)) != null && EType.getEnum(e.getAttribute(XSDConstants.NAME)) != null;
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			if ("SignalNotification".equals(e.getAttribute(XSDConstants.TYPE)))
			{
				return null;
			}
			final KElement ret = super.walk(e, trackElem);
			ret.setAttribute(XSDConstants.SUBSTITUTION_GROUP, "Message");
			ret.setAttribute(XSDConstants.NAME, EFamily.getEnum(e.getAttribute(XSDConstants.NAME)), null);
			return ret;
		}

	}

	public class WalkModuleInfoElem extends WalkXSElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && XJDFConstants.ModuleInfo.equals(e.getAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement ret = super.walk(e, trackElem);
			ret.setAttribute(XSDConstants.NAME, ElementName.MODULESTATUS);
			return ret;
		}

	}

	public class WalkModuleInfoType extends WalkComplexType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && XJDFConstants.ModuleInfo.equals(e.getInheritedAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			XSDUtil.setXSAttribute(copy, AttributeName.DEVICESTATUS, AttributeName.DEVICESTATUS, true);
			XSDUtil.createPatternType(copy.getDocRoot(), JDFConstants.ATTRIBUTETYPE_INTEGERRANGELIST, "(([-+]?\\d+|INF)(\\s*\\~\\s*([-+]?\\d+|INF))?\\s*)+");
			XSDUtil.setXSAttribute(copy, AttributeName.MODULEINDEX, JDFConstants.ATTRIBUTETYPE_INTEGERRANGELIST, false);
			XSDUtil.setXSAttribute(copy, AttributeName.MODULEID, XSDConstants.XS_NMTOKEN, false);
			XSDUtil.removeAttribute(e, XJDFConstants.ModuleStatus);
			XSDUtil.removeAttribute(e, AttributeName.MODULEID);
			return copy;
		}

	}

	public class WalkDeviceeInfoType extends WalkComplexType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && ElementName.DEVICEINFO.equals(e.getInheritedAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			XSDUtil.setXSAttribute(copy, AttributeName.DEVICESTATUS, AttributeName.DEVICESTATUS, true);
			XSDUtil.setXSAttribute(copy, AttributeName.DESCRIPTIVENAME, XSDConstants.XS_STRING, false);
			XSDUtil.removeAttribute(e, AttributeName.STATUS);
			return copy;
		}

	}

	public class WalkJobPhaseType extends WalkComplexType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && ElementName.JOBPHASE.equals(e.getInheritedAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			XSDUtil.setXSAttribute(copy, AttributeName.DESCRIPTIVENAME, XSDConstants.XS_STRING, false);
			XSDUtil.setXSAttribute(copy, AttributeName.RESTTIME, XSDConstants.XS_DURATION, false);
			final KElement deadline = XSDUtil.setXSAttribute(copy, AttributeName.DEADLINE, null, false);
			XSDUtil.createEnumType(deadline, null, EnumDeadLine.class);
			return copy;
		}

		@Override
		public void postWalk(final KElement copy, final KElement trackElem)
		{
			copyAttribute(copy, AttributeName.SPEED);
			copyAttribute(copy, AttributeName.AMOUNT, AttributeName.PHASEAMOUNT);
			copyAttribute(copy, AttributeName.WASTE, AttributeName.PHASEWASTE);
			copyAttribute(copy, AttributeName.STARTTIME, AttributeName.PHASESTARTTIME);
		}

	}

	public class WalkMISDetails extends WalkComplexType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && ElementName.MISDETAILS.equals(e.getInheritedAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			final KElement md = XSDUtil.setXSAttribute(copy, AttributeName.DEVICEOPERATIONMODE, null, false);
			XSDUtil.createEnumType(md, null, EnumDeviceOperationMode.class);
			return copy;
		}

	}

	public class WalkNotification extends WalkComplexType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && ElementName.NOTIFICATION.equals(e.getInheritedAttribute(XSDConstants.NAME));
		}

		@Override
		public void postWalk(final KElement copy, final KElement trackElem)
		{
			XSDUtil.setXSAttribute(copy, AttributeName.TYPE, XSDConstants.XS_NMTOKEN, false);
		}

	}

	public class WalkEmployeeInfoType extends WalkComplexType
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && ElementName.EMPLOYEE.equals(e.getInheritedAttribute(XSDConstants.NAME));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement copy = super.walk(e, trackElem);
			XSDUtil.setXSAttribute(copy, AttributeName.ROLES, XSDConstants.XS_NMTOKENS, false);
			XSDUtil.setXSAttribute(copy, AttributeName.PRODUCTID, XSDConstants.XS_NMTOKEN, false);
			return copy;
		}

	}

	public class WalkModuleInfoRef extends WalkXSElement
	{

		@Override
		public boolean matches(KElement e)
		{
			return super.matches(e) && XJDFConstants.ModuleInfo.equals(e.getAttribute(XSDConstants.REF));
		}

		@Override
		public KElement walk(final KElement e, final KElement trackElem)
		{
			final KElement ret = super.walk(e, trackElem);
			ret.setAttribute(XSDConstants.REF, ElementName.MODULESTATUS);
			return ret;
		}

	}

	KElement copyAttribute(KElement root, String name)
	{
		return copyAttribute(root, name, name);
	}

	KElement copyAttribute(KElement root, String name, String newName)
	{
		KElement copy = root.getChildWithAttribute(XSDConstants.XS_ATTRIBUTE, XSDConstants.NAME, null, newName, 0, true);
		if (copy != null)
		{
			return copy;
		}
		copy = xjdfXSD.getChildWithAttribute(XSDConstants.XS_ATTRIBUTE, XSDConstants.NAME, null, name, 0, false);
		if (copy != null)
		{
			copy = root.copyElement(copy, null);
			copy.setAttribute(XSDConstants.NAME, newName);
		}
		return copy;
	}

	KElement copyAttribute(KElement root, String name, String newName, boolean required)
	{
		final KElement copy = copyAttribute(root, name, newName);
		copy.setAttribute(XSDConstants.USE, required ? eAttributeUse.required : eAttributeUse.optional, null);
		return copy;
	}

}
