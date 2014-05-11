/**
 * The CIP4 Software License, Version 1.0
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
package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFElement;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.ifaces.ICapabilityElement;
import org.cip4.jdflib.resource.devicecapability.JDFAbstractState;
import org.cip4.jdflib.resource.devicecapability.JDFDevCap;
import org.cip4.jdflib.resource.devicecapability.JDFEvaluation;
import org.cip4.jdflib.util.StringUtil;

/**
 * 
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 * 
 */
public class WalkDevcapElement extends WalkElement
{

	/**
	 * 
	 */
	public WalkDevcapElement()
	{
		super();
	}

	/**
	 * @param e
	 * @return the created resource
	 */
	@Override
	public KElement walk(final KElement e, final KElement trackElem)
	{
		return trackElem;
	}

	/**
	 * TODO Please insert comment!
	 * @param path
	 * @param old
	 * @return
	 */
	protected String getXPathRoot(String path, String old)
	{
		if (path == null || "/".equals(path) || "".equals(path))
			return path;
		String rootPath = StringUtil.replaceToken(path, -1, "/", null);
		return rootPath;
	}

	/**
	 * 
	 * 
	 * @param dc
	 * @param name
	 * @return
	 */
	protected VString getXPathVector(JDFElement dc, String name)
	{
		VString v = null;
		if (dc instanceof JDFDevCap)
			v = ((JDFDevCap) dc).getNamePathVector();
		else if (dc instanceof JDFAbstractState)
			v = ((JDFAbstractState) dc).getNamePathVector();
		else if (dc instanceof JDFEvaluation)
		{
			ICapabilityElement refTarget = ((JDFEvaluation) dc).getRefTarget();
			if (refTarget != null)
				v = refTarget.getNamePathVector();
			else
				v = null;
		}

		if (v != null && v.size() > 0)
		{
			VString v2 = new VString();
			for (String s : v)
			{
				s = modifyXPath(s);
				v2.add(s);
			}
			v = v2;
			v.unify();
		}
		return v;
	}

	public String modifyXPath(String s)
	{
		VString vs = StringUtil.tokenize(s, "/", false);
		//					while (vs.size() > 0 && vs.elementAt(-1).equals(name))
		//						vs.remove(vs.size() - 1);
		for (int i = vs.size() - 2; i >= 0; i--)
		{
			if (vs.elementAt(i).equals(vs.elementAt(i + 1)))
				vs.remove(i + 1);
		}
		// remove parents of JMF, if any
		int posJMF = vs.indexOf("JMF");
		while (posJMF-- > 0)
			vs.remove(0);

		if (vs.size() == 0)
		{
			s = "/";
		}
		else
		{
			if ("JDF".equals(vs.get(0)))
				vs.set(0, JDFToXJDF.rootName);
			if (vs.size() > 1 && ElementName.RESOURCEPOOL.equals(vs.get(1)))
			{
				String className = null;
				if (vs.size() == 3)
				{
					String name = vs.get(2);
					className = this.jdfToXJDF.getClassName(name);
					if (className != null)
					{
						if ("Intent".equals(className))
						{
							vs.set(1, "ProductList/Product");
						}
						else
						{
							vs.set(1, className + "Set/" + className);
						}
					}
				}
				if (className == null)
				{
					vs.remove(1);
					vs.remove(0);
				}
			}
			s = StringUtil.setvString(vs, "/", "/", null);
			if (!s.startsWith("/" + JDFToXJDF.rootName))
				s = "/" + s;
		}
		return s;
	}

	@Override
	public boolean matches(KElement e)
	{
		// we are abstract...
		return false;
	}
}