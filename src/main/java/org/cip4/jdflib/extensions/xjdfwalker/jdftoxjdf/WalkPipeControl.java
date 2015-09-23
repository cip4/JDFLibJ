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

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.extensions.xjdfwalker.XJMFTypeMap;
import org.cip4.jdflib.jmf.JDFCommand;
import org.cip4.jdflib.jmf.JDFMessage;
import org.cip4.jdflib.util.StringUtil;

/**
 * @author Rainer Prosi, Heidelberger Druckmaschinen <br/>
 * walker for JMF mesaages
 */
public class WalkPipeControl extends WalkMessage
{
	/**
	 * 
	 */
	public WalkPipeControl()
	{
		super();
	}

	/**
	 * 
	 * @see org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf.WalkMessage#makeTypesafe(org.cip4.jdflib.jmf.JDFMessage)
	 */
	@Override
	void makeTypesafe(JDFMessage m)
	{
		String originalType = super.getMessageType(m);
		super.makeTypesafe(m);
		if (m instanceof JDFCommand)
		{
			KElement pipeParams = m.getCreateElement(ElementName.PIPEPARAMS, null, 0);
			pipeParams.setAttribute(AttributeName.OPERATION, originalType);
			String id = m.getID();
			XJMFTypeMap.getMap().put(id, originalType);
		}
	}

	/**
	 * 
	 * @see org.cip4.jdflib.extensions.XJDF20.WalkMessage#getMessageType(org.cip4.jdflib.jmf.JDFMessage)
	 */
	@Override
	String getMessageType(JDFMessage m)
	{
		return "PipeControl";
	}

	/**
	 * @see org.cip4.jdflib.extensions.XJDF20.WalkMessage#matches(org.cip4.jdflib.core.KElement)
	 */
	@Override
	public boolean matches(KElement toCheck)
	{
		return !jdfToXJDF.isRetainAll() && (super.matches(toCheck) && isPipeControl(toCheck.getAttribute(AttributeName.TYPE)));
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	private boolean isPipeControl(String type)
	{
		return type.startsWith("Pipe") && StringUtil.hasToken("PipePush,PipePull,PipeClose,PipePause", type, ",", 0);
	}

}