package org.cip4.jdflib.pool;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.resource.process.prepress.JDFPreflightDetail;
import org.cip4.jdflib.resource.process.prepress.JDFPreflightInstance;

/*
* The CIP4 Software License, Version 1.0
*
*
* Copyright (c) 2001-2002 The International Cooperation for the Integration of 
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

//EndCopyRight
/*
*
*
* COPYRIGHT Heidelberger Druckmaschinen AG, 1999-2001
*      ALL RIGHTS RESERVED 
*
*  Author: Kai Mattern
* 
* Warning! preliminary version. Interface subject to change without prior notice!
* Revision history:
* created 2001-12-17
* 030902 RP IsValid() removed erroneous check for maxOccurs=1 of  PreflightDetail PreflightInstance
*
* based on JDF Schema version JDFCore_1_0_0.xsd
*
*/

public class JDFPreflightResultsPool extends JDFPool
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for JDFPreflightResultsPool
     * @param myOwnerDocument
     * @param qualifiedName
     */
    public JDFPreflightResultsPool(
        CoreDocumentImpl myOwnerDocument,
        String qualifiedName)
    {
        super(myOwnerDocument, qualifiedName);
    }

    /**
     * Constructor for JDFPreflightResultsPool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     */
    public JDFPreflightResultsPool(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName);
    }

    /**
     * Constructor for JDFPreflightResultsPool
     * @param myOwnerDocument
     * @param myNamespaceURI
     * @param qualifiedName
     * @param myLocalName
     */
    public JDFPreflightResultsPool(
        CoreDocumentImpl myOwnerDocument,
        String myNamespaceURI,
        String qualifiedName,
        String myLocalName)
    {
        super(myOwnerDocument, myNamespaceURI, qualifiedName, myLocalName);
    }

 
 
    /* ******************************************************
    // Attribute Getter / Setter
    ****************************************************** */

    /* ******************************************************
    // Element Getter / Setter
    **************************************************************** */

    /**
	 * Get Element PreflightInstance
	 * <p>
	 * default: GetCreatePreflightInstance(0)
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPreflightInstance: the element
	 */
    JDFPreflightInstance getCreatePreflightInstance(int iSkip)
    {
        return (JDFPreflightInstance) 
            getCreateElement_KElement("PreflightInstance", JDFConstants.EMPTYSTRING, iSkip);
    }

    /**
	 * Remove element PreflightInstance
	 * <p>
	 * default: RemovePreflightInstance(0)
	 * 
	 * @param iSkip
	 *            number of elements to skip
	 */
    public void removePreflightInstance(int iSkip)
    {
        removeChild("PreflightInstance", JDFConstants.EMPTYSTRING, iSkip);
    }

    /**
	 * Append element PreflightInstance
	 * 
	 * @return JDFPreflightInstance: the element
	 */
    JDFPreflightInstance appendPreflightInstance()
    {
        return (JDFPreflightInstance) appendElement(ElementName.PREFLIGHTINSTANCE, null);
     }

    /**
	 * get element PreflightInstance
	 * <p>
	 * default: GetPreflightInstance(0)
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPreflightInstance: the element. Null if not found.
	 */
    JDFPreflightInstance getPreflightInstance(int iSkip)
    {
        return (JDFPreflightInstance) getElement(ElementName.PREFLIGHTINSTANCE, null, iSkip);
    }
 
    /**
	 * Get Element PreflightDetail
	 * <p>
	 * default: GetCreatePreflightDetail(0)
	 * 
	 * @param iSkip number of elements to skip
	 * @return JDFPreflightInstance: the element
	 */
    public JDFPreflightDetail getCreatePreflightDetail(int iSkip)
    {
        return (JDFPreflightDetail) getCreateElement_KElement(ElementName.PREFLIGHTDETAIL, null, iSkip);
    }

    /**
	 * Append element PreflightDetail
	 * @return JDFPreflightInstance: the element
	 */
    JDFPreflightDetail appendPreflightDetail()
    {
       return (JDFPreflightDetail) appendElement(ElementName.PREFLIGHTDETAIL, null);
    }

    /**
	 * get first element PreflightDetail
	 * <p>
	 * default: GetPreflightDetail(0)
	 * 
	 * @return JDFPreflightInstance: the element
	 */
    JDFPreflightDetail getPreflightDetail(int iSkip)
    {
        return (JDFPreflightDetail) getElement(ElementName.PREFLIGHTDETAIL, null, iSkip);
    }


}
