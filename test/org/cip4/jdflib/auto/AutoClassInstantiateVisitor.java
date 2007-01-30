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

import java.io.File;

import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.node.JDFNode;
import org.w3c.dom.DOMException;


public class AutoClassInstantiateVisitor implements DirectoryVisitor
{
    boolean totalResult = true;
    
    public void enterDirectory(File dir)
    { totalResult = true; }

    public void leaveDirectory(File dir)
    { 
        if (!totalResult)
        {
            totalResult = true;
            throw new DOMException(DOMException.NOT_FOUND_ERR, 
                    "Error!!! There were classes, which could not be instantiated");
        }
    }

    public void visitFile(File file)
    {
        testJDFClass(file.getName());
    }

    private void testJDFClass(final String fileName)
    {
        boolean result = false;

        String elementName = fileName;
        String prefix = elementName.startsWith("JDFAuto") ? "JDFAuto" : "JDF";

        elementName = elementName.substring(prefix.length(), elementName.length()-".java".length());

        // adjust the element name
        if (elementName.startsWith("Span"))
            elementName = elementName.substring("Span".length());
        else if (elementName.equals("ShapeElement"))
            elementName = "Shape";
        else if (elementName.equals("Node"))
            elementName = "JDF";

        final JDFDoc jdfDoc   = new JDFDoc(ElementName.JDF);
        final JDFNode jdfRoot = (JDFNode) jdfDoc.getRoot();

        KElement kElem = jdfRoot.appendElement(elementName); // create a class for elementName
        
        String createdClass = kElem.getClass().toString();
        createdClass = createdClass.substring(createdClass.lastIndexOf(".") + 1);

        result = elementName.equals(createdClass.substring("JDF".length()))
                    || (elementName.equals(ElementName.COLORSUSED) && createdClass.equals("JDFSeparationList"))
                    || (elementName.equals(ElementName.SHAPE) && createdClass.equals("JDFShapeElement"))
                    || (elementName.endsWith(JDFConstants.LINK) && createdClass.substring("JDF".length()).equals(ElementName.RESOURCELINK))
                    ;

        if (!result)
        {
            totalResult = false;
            throw new DOMException(DOMException.NOT_FOUND_ERR, 
                "Error!!! Class " + elementName + " (" + fileName + ") could not be instantiated!" +
                " --> missing entry in DocumentJDFImpl.sm_PackageNames ???");
        }
    }
}
