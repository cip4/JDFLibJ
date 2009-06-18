/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2009 The International Cooperation for the Integration of
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
 *    Alternately, this acknowledgment mrSubRefay appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior writtenrestartProcesses()
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
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIrSubRefAL DAMAGES (INCLUDING, BUT NOT
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
 * originally based on software restartProcesses()
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.extensions;

import java.io.File;

import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.elementwalker.BaseElementWalker;
import org.cip4.jdflib.elementwalker.BaseWalker;
import org.cip4.jdflib.elementwalker.BaseWalkerFactory;
import org.cip4.jdflib.util.FileUtil;

/**
 * 
 * most extremely very prototypical skeleton schema converter<br/>
 * TODO: we may want to simply generate from autoclasses rather than from the existing schema...<br/>
 * or forget the walker pattern and simply loop
 * 
 * @author Dr. Rainer Prosi, Heidelberger Druckmaschinen AG
 * 
 * June 3, 2009
 */
public class XJDFSchemaWalker extends BaseElementWalker
{

	/**
	 * 
	 */
	public XJDFSchemaWalker()
	{
		super(new BaseWalkerFactory());
		init();
	}

	/**
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public void newFile(final File in, final File out)
	{
		if (!in.canRead())
		{
			throw new IllegalArgumentException("need a valid input file");
		}
		if (!out.canWrite())
		{

			FileUtil.createNewFile(out);
			if (!out.canWrite())
			{
				throw new IllegalArgumentException("need a valid output file");
			}
		}
		final JDFParser p = new JDFParser();
		p.bKElementOnly = true;
		final XMLDoc dIn = p.parseFile(in);
		final KElement rootIn = dIn.getRoot();
		final XMLDoc dOut = new XMLDoc(rootIn.getNodeName(), rootIn.getNamespaceURI());
		final KElement rootOut = dOut.getRoot();
		walkTree(rootIn, rootOut);
		dOut.write2File(out, 2, false);
	}

	/**
	 * 
	 */
	private void init()
	{
		// nop as of now

	}

	/**
	 * 
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkElement extends BaseWalker
	{
		@SuppressWarnings("synthetic-access")
		public WalkElement()
		{
			super(getFactory());
		}

		/**
		 * @param in
		 * @param out
		 * @return not null if must continue
		 */
		@Override
		public KElement walk(final KElement in, final KElement out)
		{
			final KElement eNew = out.copyElement(in, null);
			eNew.removeChildren(null, null, null);
			return eNew;
		}
	}

	/**
	 * any matching class will be removed with extreme prejudice...
	 * @author Rainer Prosi, Heidelberger Druckmaschinen
	 * 
	 */
	protected class WalkIgnore extends WalkElement
	{

		public WalkIgnore()
		{
			super();
		}

		/**
		 * @param xjdf
		 * @return true if must continue
		 */
		@Override
		public KElement walk(final KElement jdf, final KElement xjdf)
		{
			return null;
		}

		/**
		 * @see org.cip4.jdflib.elementwalker.BaseWalker#matches(org.cip4.jdflib.core.KElement)
		 * @param toCheck
		 * @return true if it matches
		 */
		@Override
		public boolean matches(final KElement toCheck)
		{
			final String elmName = toCheck.getLocalName();
			if (elmName == null)
			{
				return false;
			}
			if ("complexType".equals(elmName))
			{
				final String name = toCheck.getAttribute("name");
				boolean b = name.endsWith("_re");
				b = b || name.endsWith("_r");
				return b;
			}
			if ("element".equals(elmName))
			{
				final String name = toCheck.getAttribute("name");
				boolean b = name.equals("Identical");
				b = b || name.endsWith("Update");
				return b;
			}
			return false;
		}
	}

}
