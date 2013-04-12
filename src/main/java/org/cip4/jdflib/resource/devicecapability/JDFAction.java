/*
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
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * JDFAction.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.resource.devicecapability;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.auto.JDFAutoAction;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.resource.process.JDFPreflightAction;
import org.w3c.dom.DOMException;

/**
 * 
 * @author Rainer Prosi, Heidelberger Druckmaschinen
 *
 */
public class JDFAction extends JDFAutoAction
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for JDFAction
	 * 
	 * @param myOwnerDocument
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFAction(CoreDocumentImpl myOwnerDocument, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, qualifiedName);
	}

	/**
	 * Constructor for JDFAction
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @throws DOMException
	 */
	public JDFAction(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName);
	}

	/**
	 * Constructor for JDFAction
	 * 
	 * @param myOwnerDocument
	 * @param myNamespaceURI
	 * @param qualifiedName
	 * @param myLocalName
	 * @throws DOMException
	 */
	public JDFAction(CoreDocumentImpl myOwnerDocument, String myNamespaceURI, String qualifiedName, String myLocalName) throws DOMException
	{
		super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "JDFAction[  --> " + super.toString() + " ]";
	}

	/**
	 * get the Test element in the TestPool that is referenced by this action
	 * 
	 * @return JDFTest: the referenced test, null if none exists
	 */
	public JDFTest getTest()
	{
		final JDFTestPool testPool = getTestPool();
		if (testPool == null)
			return null;
		return testPool.getTest(getTestRef());
	}

	/**
	 * get the testPool that all IDRefs in this action refer to
	 * 
	 * @return JDFTestPool: the neighboring TestPool
	 */
	public JDFTestPool getTestPool()
	{
		final KElement commonParent = getActionPool().getParentNode_KElement();
		if (commonParent == null)
			return null;

		final JDFTestPool testPool = (JDFTestPool) commonParent.getElement(ElementName.TESTPOOL);
		return testPool;
	}

	/**
	 * get the root Term of the Test element in the TestPool that is referenced
	 * by this action
	 * 
	 * @return JDFTerm: the referenced term, null if none exists
	 */
	public JDFTerm getTestTerm()
	{
		final JDFTest test = getTest();
		if (test == null)
			return null;
		return test.getTerm(null, 0);
	}

	/**
	 * get the action pool <code>this</code> resides in
	 * 
	 * @return JDFActionPool - the actionpool
	 */
	public JDFActionPool getActionPool()
	{
		return (JDFActionPool) getParentNode_KElement();
	}

	/**
	 * init()
	 * 
	 * @see org.cip4.jdflib.core.KElement#init()
	 */
	@Override
	public boolean init()
	{
		appendAnchor(null);
		return super.init();
	}

	/**
	 * getIDPrefix
	 * 
	 * @return String: the default ID prefix of non-overwritten JDF elements
	 */
	@Override
	protected String getIDPrefix()
	{
		return "A";
	}

	/**
	 * set testRef to the value of test/@ID
	 * 
	 * @param test
	 *            the value to set testRef to
	 */
	public void setTest(JDFTest test)
	{
		test.appendAnchor(null); // just in case it is missing
		final String id2 = test.getID();
		setTestRef(id2);
	}

	/**
	 * set PreflightAction/@SetRef to the value of test/@ID
	 * 
	 * @param test
	 *            the test to use
	 */
	public void setPreflightActionSetRef(JDFTest test)
	{
		test.appendAnchor(null); // just in case it is missing
		final String id2 = test.getID();
		getCreatePreflightAction(0).setSetRef(id2);
	}

	/**
	 * get the test defined by PreflightAction/@SetRef
	 * 
	 * @return JDFTest: the test to use
	 */
	public JDFTest getPreflightActionSetRef()
	{
		JDFPreflightAction pfa = getPreflightAction(0);
		if (pfa == null)
			return null;
		String setRef = pfa.getSetRef();
		final JDFTestPool testPool = getTestPool();
		if (testPool == null)
			return null;
		return testPool.getTest(setRef);
	}

	/**
	 * get the term defined by PreflightAction/@SetRef
	 * 
	 * @return JDFTerm: the term to use
	 */
	public JDFTerm getPreflightActionSetTerm()
	{
		final JDFTest test = getPreflightActionSetRef();
		if (test == null)
			return null;
		return test.getTerm();
	}

}
