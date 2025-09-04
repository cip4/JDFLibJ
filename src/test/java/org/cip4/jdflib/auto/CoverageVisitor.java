/*--------------------------------------------------------------------------------------------------
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2006 The International Cooperation for the Integration of
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
 * copyright (c) 1999-2006, Heidelberger Druckmaschinen AG
 * copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *
 */
package org.cip4.jdflib.auto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFException;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.ifaces.ICapabilityElement;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.resource.devicecapability.JDFAbstractState;
import org.cip4.jdflib.resource.devicecapability.JDFTerm;
import org.cip4.jdflib.span.JDFSpanBase;
import org.cip4.jdflib.util.ContainerUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;

class CoverageVisitor implements DirectoryVisitor
{
	final Set<String> skip;
	final HashMap<String, String> enums;

	public CoverageVisitor()
	{
		super();
		final StringArray skip0 = new StringArray(
				"JDFBarCode JDFDevCaps JDFIDPrintingParams JDFLayout.appendSignature JDFLayout.appendSurface JDFLayout.appendFrontSurface JDFLayout.appendBackSurface JDFLayout.appendSheet "
						+ "JDFAbortQueueEntryParams.appendQueueFilter JDFNotification JDFNumberItem JDFPartAmount");
		final StringArray skip1 = new StringArray(
				"getTest getActionPool getTestPool getTestTerm getUpdatedPreviousAudit getBindingType getCreateHoleType getHoleType appendHoleType"
						+ " getMethod getSurplusHandling getServiceLevel getReturnMethod getTransfer JDFDevCaps"
						+ " getParentPool getModulePool getCreateParentPool"
						+ " JDFIDPrintingParams JDFLayout.getCreateSignature JDFLayout.getCreateSurface JDFLayout.getCreateFrontSurface JDFLayout.getCreateBackSurface JDFLayout.getCreateSheet"
						+ " getCreateModulePool getCreateDevCapPool getDevCapPool getParentPool getDevCapVector getDevCap getMinOccurs getMaxOccurs JDFNumberItem JDFPartAmount JDFPartStatus JDFStatusPool JDFResourceLink");
		;
		final StringArray skip2 = new StringArray(
				"JDFNewJDFQuParams setFamily setIdentical setRefTarget JDFColorantControl.setSeparation setPhoneNumber setEMailLocator setQuery JDFPartAmount JDFLayout.refSurface JDFLayout.refSheet");
		skip = new HashSet<>();
		skip.addAll(skip0);
		skip.addAll(skip1);
		skip.addAll(skip2);
		enums = new HashMap<>();
	}

	boolean totalResult = true;
	final static Log log = LogFactory.getLog(CoverageVisitor.class);

	@Override
	public void enterDirectory(final File dir)
	{
		totalResult = true;
	}

	@Override
	public void leaveDirectory(final File dir)
	{
		if (!totalResult)
		{
			totalResult = true;
			throw new DOMException(DOMException.NOT_FOUND_ERR, "Error!!! There were classes, which could not be instantiated");
		}
	}

	@Override
	public void visitFile(final File file)
	{
		try
		{
			testJDFCoverage(file.getName());
		}
		catch (final Exception e)
		{
			log.error("bad coverage", e);
			throw new JDFException(e.getMessage());
		}
	}

	private void testJDFCoverage(final String fileName) throws Exception
	{
		String elementName = fileName;
		final String prefix = elementName.startsWith("JDFAuto") ? "JDFAuto" : "JDF";

		elementName = elementName.substring(prefix.length(), elementName.length() - ".java".length());

		// adjust the element name
		if (elementName.startsWith("Span"))
		{
			elementName = elementName.substring("Span".length());
		}
		else if (elementName.equals("ShapeElement"))
		{
			elementName = "Shape";
		}
		else if (elementName.equals("Node"))
		{
			elementName = "JDF";
		}

		final JDFDoc jdfDoc = new JDFDoc(ElementName.JDF);
		final JDFNode jdfRoot = (JDFNode) jdfDoc.getRoot();

		final KElement kElem = jdfRoot.appendElement(elementName);

		cover(kElem);
	}

	void cover(final KElement kElem) throws Exception
	{
		try
		{
			coverAppenders(kElem);
			coverSetters(kElem);
			coverrefs(kElem);
			coverEnums(kElem);
			kElem.removeAttribute(AttributeName.RREF);
			coverGetters(kElem);
		}
		catch (Throwable x)
		{
			log.error("Snafu for " + kElem.getLocalName(), x);
			throw x;
		}
	}

	private void coverGetters(final KElement kElem) throws Exception
	{
		final Class<? extends KElement> c = kElem.getClass();
		final Method[] methods = c.getMethods();
		for (final Method method : methods)
		{
			final String ab = c.getSimpleName() + "." + method.getName();
			if (method.getName().startsWith("get"))
			{
				final Class<?>[] types = method.getParameterTypes();
				try
				{
					if (types.length == 0)
					{
						log.info(ab);
						method.invoke(kElem, new Object[] {});
					}
					else if (types.length == 0 && int.class.equals(types[0]))
					{
						method.invoke(kElem, 0);
					}

				}
				catch (final InvocationTargetException i)
				{
					final Throwable t = i.getTargetException();
					if (JDFTerm.class.isAssignableFrom(c) || ICapabilityElement.class.isAssignableFrom(c) || skip.contains(method.getName())
							|| skip.contains(ab) || skip.contains(c.getSimpleName()))
					{
						// skip devcaps
					}
					else if (t instanceof RuntimeException)
					{
						log.warn("Runtime Exception :", t);
					}
					else
					{
						throw i;
					}
				}
				catch (final Exception j)
				{

					log.warn("snafu ", j);
				}
			}
		}
	}

	private void coverEnums(final KElement kElem) throws Exception
	{
		final Class<? extends KElement> c = kElem.getClass();
		HashSet<String> ignoreclasses = new HashSet<String>();
		ignoreclasses.add(ElementName.SIGNATURE);
		ignoreclasses.add(ElementName.SHEET);
		if (ignoreclasses.contains(c.getSimpleName().substring(3)))
		{
			return;
		}
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		Class<?> cs = c;
		boolean auto = false;
		while (cs.getPackageName().startsWith("org.cip4"))
		{
			final Class<?>[] superclasses = cs.getDeclaredClasses();
			if (cs.getPackageName().startsWith("org.cip4.jdflib.auto"))
			{
				ContainerUtil.addAll(classes, superclasses);
				auto = true;
			}
			else
			{
				ContainerUtil.removeAll(classes, superclasses);
			}
			cs = cs.getSuperclass();
		}
		HashSet<String> ignore = new HashSet<String>();
		ignore.add(AttributeName.LEVEL);
		ignore.add(AttributeName.ROTATEPOLICY);
		ignore.add(AttributeName.TYPE);
		ignore.add(AttributeName.REASON);
		ignore.add(AttributeName.MATERIAL);
		ignore.add(AttributeName.ACTION);
		ignore.add(AttributeName.MEDIASIDE);
		if (auto)
		{
			for (final Class<?> cl : classes)
			{
				if (cl.isEnum())
				{
					Class<Enum<?>> cle = (Class<Enum<?>>) cl;
					Enum<?>[] vals = (Enum<?>[]) cl.getEnumConstants();
					if (enums.containsKey(cl.getSimpleName()))
					{
						log.warn("duplicate enum: " + kElem.getLocalName() + " " + cl.getSimpleName() + " / " + enums.get(cl.getSimpleName()));
						if (ignore.contains(cl.getSimpleName()))
						{
							log.error("duplicate enum: " + kElem.getLocalName() + " " + cl.getSimpleName() + " / " + enums.get(cl.getSimpleName()));
						}
						// throw new Exception("duplicate enum: " + kElem.getLocalName() + " " + cl.getSimpleName());
					}
					else
					{
						enums.put(cl.getSimpleName(), kElem.getLocalName());
					}
				}
			}
		}
	}

	private void coverAppenders(final KElement kElem) throws Exception
	{
		final Class<? extends KElement> c = kElem.getClass();
		final Method[] methods = c.getMethods();
		for (final Method method : methods)
		{
			if (method.getName().startsWith("append"))
			{
				final String ab = c.getSimpleName() + "." + method.getName();
				final Class<?>[] types = method.getParameterTypes();
				try
				{
					if (types.length == 0)
					{
						log.info(ab);
						final KElement e = (KElement) method.invoke(kElem, new Object[] {});
						if (JDFTerm.class.isAssignableFrom(c) || ICapabilityElement.class.isAssignableFrom(c) || skip.contains(method.getName())
								|| skip.contains(ab) || skip.contains(c.getSimpleName()))
						{
							// nop
						}
						else
						{
							assertNotNull(e);
						}
						if ((e instanceof JDFAbstractState) || (e instanceof JDFSpanBase))
						{
							cover(e);
						}
					}
				}
				catch (final InvocationTargetException i)
				{
					final Throwable t = i.getTargetException();
					if (JDFTerm.class.isAssignableFrom(c) || ICapabilityElement.class.isAssignableFrom(c) || skip.contains(method.getName())
							|| skip.contains(ab) || skip.contains(c.getSimpleName()))
					{
						// nop
					}
					else if (t instanceof RuntimeException)
					{
						log.warn("Runtime Exception :", t);
					}
					else
					{
						throw i;
					}
				}
				catch (final Exception j)
				{

					log.warn("snafu ", j);
				}
			}
		}
	}

	private void coverrefs(final KElement kElem) throws Exception
	{
		final Class<? extends KElement> c = kElem.getClass();
		final Method[] methods = c.getMethods();
		for (final Method method : methods)
		{
			if (method.getName().startsWith("ref"))
			{
				final String ab = c.getSimpleName() + "." + method.getName();
				final Class<?>[] types = method.getParameterTypes();
				try
				{
					if (types.length == 1)
					{
						log.info(ab);
						method.invoke(kElem, new Object[] { null });
					}
				}
				catch (final InvocationTargetException i)
				{
					final Throwable t = i.getTargetException();
					if (JDFTerm.class.isAssignableFrom(c) || ICapabilityElement.class.isAssignableFrom(c) || skip.contains(method.getName())
							|| skip.contains(ab) || skip.contains(c.getSimpleName()))
					{
						// nop
					}
					else if (t instanceof RuntimeException)
					{
						log.warn("Runtime Exception :", t);
					}
					else
					{
						throw i;
					}
				}
				catch (final Exception j)
				{

					log.warn("snafu ", j);
				}
			}
		}
	}

	private void coverSetters(final KElement kElem) throws Exception
	{
		final Class<? extends KElement> c = kElem.getClass();
		final Method[] methods = c.getMethods();
		for (final Method method : methods)
		{
			if (method.getName().startsWith("set") && !Modifier.isStatic(method.getModifiers()))
			{
				final Class<?>[] types = method.getParameterTypes();
				final String ab = c.getSimpleName() + "." + method.getName();
				try
				{
					if (types.length == 1)
					{
						if (String.class.equals(types[0]))
						{
							method.invoke(kElem, "test");
						}
						else if (VString.class.equals(types[0]))
						{
							method.invoke(kElem, new VString("test1 test2"));
						}
						else if (int.class.equals(types[0]))
						{
							method.invoke(kElem, 1);
						}
						else if (boolean.class.equals(types[0]))
						{
							method.invoke(kElem, true);
						}
						else if (double.class.equals(types[0]))
						{
							method.invoke(kElem, 42.0);
						}
						else if (!Attr.class.equals(types[0]))
						{
							log.info(ab + " " + types[0]);
							method.invoke(kElem, new Object[] { null });
						}
						//
					}

				}
				catch (final InvocationTargetException i)
				{
					final Throwable t = i.getTargetException();
					if (JDFTerm.class.isAssignableFrom(c) || ICapabilityElement.class.isAssignableFrom(c) || skip.contains(method.getName())
							|| skip.contains(ab) || skip.contains(c.getSimpleName()))
					{
						// nop
					}
					else if (t instanceof RuntimeException)
					{
						log.warn("Runtime Exception :", t);
					}
					else
					{
						throw i;
					}
				}
				catch (final Exception j)
				{

					log.warn("snafu ", j);
				}
			}
		}
	}
}
