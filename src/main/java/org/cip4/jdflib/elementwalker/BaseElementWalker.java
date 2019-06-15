/*
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2014 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
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

package org.cip4.jdflib.elementwalker;

import java.lang.reflect.Constructor;

import org.cip4.jdflib.util.StringUtil;

/**
 *
 * elementwalker class that allows you to traverse a dom tree starting at a given root also handles the construction of the walker classes by name, just make sure that your walker subclasses match the
 * naming convention $Walk<name>, e.g. if your class is called FixVersion, the subclasses must be called WalkFoo, WalkBar etc.
 *
 * @author rainer prosi
 *
 */
public class BaseElementWalker extends ElementWalker
{

	/**
	 * @param _theFactory
	 */
	public BaseElementWalker(final BaseWalkerFactory _theFactory)
	{
		super(_theFactory);
		constructWalkers("$Walk");
	}

	/**
	 * construct all walkers confirming to the naming convention public <classname>$Walk<xxx>
	 *
	 * @param classPrefix
	 */
	private void constructWalkers(final String classPrefix)
	{
		Class<?> parent = getClass();

		while (parent != null)
		{
			final String name = parent.getSimpleName();
			final Class<?>[] cs = parent.getDeclaredClasses();
			for (final Class<?> theClass : cs)
			{
				String className = theClass.getName();
				className = StringUtil.token(className, -1, ".");
				if (className.startsWith(name + classPrefix))
				{
					try
					{
						final Constructor<?> con = theClass.getDeclaredConstructor(new Class[] { parent });
						con.newInstance(new Object[] { this });
					}
					catch (final Throwable x)
					{
						log.error("Snafu instantiating walker", x);
					}
				}
			}
			parent = parent.getSuperclass();
		}
	}

	/**
	 * @return the factory for this worker
	 */
	@Override
	public BaseWalkerFactory getFactory()
	{
		return (BaseWalkerFactory) theFactory;
	}

}
