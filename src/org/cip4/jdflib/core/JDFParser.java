/*
 * Created on Jul 3, 2003
 */
package org.cip4.jdflib.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.xni.QName;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * @author matternk
 */
public class JDFParser extends DOMParser
{
    public XMLErrorHandler m_ErrorHandler = null;
    public String m_SchemaLocation=null;
    public String m_DocumentClass=DocumentJDFImpl.class.getName();
    public Exception lastExcept=null;
    /**
     * if true, empty pools and whitespace are removed when parsing
     */
    public boolean m_eraseEmpty=true;

    public JDFParser()
    {
        super();
    }

    /**
     * @deprecated - use default constructor
     * @param strDocType
     */
    public JDFParser(String strDocType)
    {
        this();
    }

    public Element createElementNode(QName element)
    {
        if (fCurrentNode.getLocalName() != null)
        {
            ((DocumentJDFImpl) (this.fDocument)).setParentNode(fCurrentNode);
        }

        Element e= super.createElementNode(element);
        ((DocumentJDFImpl) (this.fDocument)).setParentNode(null);
        return e;
        
    }

    /**
     * parseFile - parse a file specified by strFile
     * @param strFile link to the document to parse
     * @return JDFDoc or null if File not found
     */
    public JDFDoc parseFile(String strFile)
    {
        JDFDoc doc = null;
        if (strFile == null)
            return null;

        final File file = new File(strFile);
        if (file.canRead())
        {
            try
            {
                InputSource inSource = new InputSource(new FileInputStream(file));
                doc = parseInputSource(inSource);
                if (doc != null)
                {
                    doc.setOriginalFileName(file.getAbsolutePath());
                }
                else
                {            
                    setIgnoreNamespace();
                    inSource = new InputSource(new FileInputStream(file));
                    doc= parseInputSource(inSource);
                }
                return doc;

            }
            catch (FileNotFoundException e)
            {
                return null;
            }
        }

        return doc;
    }

    /**
     * parseFile - parse a file specified by strFile
     * 
     * @param strFile        link to the document to parse
     * @param schemaLocation link to the schema to use, null if no validation required
     * @return JDFDoc or null if File not found
     * default: parseFile(strFile,null)
     * @deprecated set the parser members instead
     */
    public JDFDoc parseFile(String strFile, String schemaLocation)
    {
        m_SchemaLocation=schemaLocation;
        return parseFile(strFile);
    }

    
    /**
     * parseString - parse a string specified by stringInput
     * @param stringInput string to parse
     * @return JDFDoc or null if parse failed
     * default: parseString(stringInput)
     */
    public JDFDoc parseString(String stringInput)
    {
        InputSource inSource = new InputSource(new StringReader(stringInput));

        JDFDoc doc= parseInputSource(inSource);
        if(doc==null)
        {            
            setIgnoreNamespace();
            inSource = new InputSource(new StringReader(stringInput));
            doc= parseInputSource(inSource);
        }
        return doc;
    }

    /**
     * parseStream - parse a stream specified by inStream
     * @param inStream stream to parse
     * @return JDFDoc or null if parse failed
     * default: parseStream(inStream)
     */
    public JDFDoc parseStream(InputStream inStream)
    {
        if(inStream==null)
            return null;

        final InputSource inSource = new InputSource(inStream);        
        return parseInputSource(inSource);
    }
    
    /**
     * parse an input source
     * @param inSource the InputSource to parse
     */
    public void parse(InputSource inSource)
    {
        parseInputSource(inSource);
    }

    /**
     * parse an input source
     * @param inSource the InputSource to parse
     * @return JDFDoc the newly parsed doc
     */
    public JDFDoc parseInputSource(InputSource inSource)
    {
        JDFDoc jdfDoc = null;
        
        if (inSource != null)
        {
            initParser(m_SchemaLocation, m_DocumentClass, m_ErrorHandler);
            jdfDoc = runParser(inSource, m_eraseEmpty);
        }
        
        return jdfDoc;
    }
    
    /**
     * This is the sophisticated parse function, 
     * where validation, error handlers et al. can be set
     * 
     * @param inSource
     * @param schemaLocation schema location, null if no validation required
     * @param documentClassName
     * @param errorHandler
     * @param bEraseEmpty   if true empty nodes are erased after parsing
     * @param bDoNamespaces if false a second parse is done, 
     *                      	where namespaces are ignored
     * 
     * @return JDFDoc
     * 
     * default: parseInputSource(inSource, 
     *              null, DocumentJDFImpl.class.getName(), null, true, true);
     * @deprecated set the parser members instead
     */
    public JDFDoc parseInputSource(
            InputSource inSource,
            String schemaLocation,
            String documentClassName,
            ErrorHandler errorHandler,
            boolean bEraseEmpty,
            boolean bDoNamespaces)
    {
        JDFDoc doc = null;
        if(errorHandler instanceof XMLErrorHandler)
        {
            initParser(schemaLocation, documentClassName, (XMLErrorHandler)errorHandler);
        }
        else
        {
            initParser(schemaLocation, documentClassName, null);
        }

        doc = runParser(inSource, bEraseEmpty);
        if (doc == null)
        { // this is the error case:
            if (!bDoNamespaces)
            {
                // try again, ignoring name spaces
                setIgnoreNamespace();
                doc = runParser(inSource, bEraseEmpty);
            }
        }

        return doc;
    }

    /**
     * @param schemaLocation
     * @param documentClassName
     * @param ErrorHandler
     * 
     * default: initParser(null, DocumentJDFImpl.class.getName(), null);
     */
    private void initParser( String schemaLocation, 
            String documentClassName,
            XMLErrorHandler errorHandler)
    {
        m_SchemaLocation=schemaLocation;
        m_ErrorHandler=errorHandler;
        m_DocumentClass=documentClassName;

        try
        {
            if (schemaLocation == null || schemaLocation.equals(JDFConstants.EMPTYSTRING))
            {
                this.setFeature("http://xml.org/sax/features/validation", false);
                this.setFeature("http://apache.org/xml/features/validation/schema", false);
            }
            else
            {
                if(!schemaLocation.startsWith(JDFElement.getSchemaURL()))
                    schemaLocation=JDFElement.getSchemaURL()+" "+schemaLocation;
                this.setFeature("http://xml.org/sax/features/validation", true);
                this.setFeature("http://apache.org/xml/features/validation/schema", true);
//              this.setFeature("http://apache.org/xml/features/validation/schema/element-default", false);
//              this.setFeature("http://apache.org/xml/features/validation/schema/normalized-value", false);
                this.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", schemaLocation);
            }

            // use our own JDF document for creating JDF elements
            this.setProperty("http://apache.org/xml/properties/dom/document-class-name", documentClassName);

            this.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
            this.setFeature("http://xml.org/sax/features/namespaces", true);

            this.setErrorHandler(errorHandler);
        }
        catch (SAXNotRecognizedException e)
        {
            lastExcept=e;
        }
        catch (SAXNotSupportedException e)
        {
            lastExcept=e;
        }
    }

    public void setErrorHandler(ErrorHandler handler)
    {
        m_ErrorHandler = handler!= null && (handler instanceof XMLErrorHandler) ? 
                (XMLErrorHandler) handler : new XMLErrorHandler();
                super.setErrorHandler(m_ErrorHandler);
    }
    /**
     * @param parser
     * @param inSource
     * @param bEraseEmpty
     * @return
     */
    private JDFDoc runParser(InputSource inSource, boolean bEraseEmpty)
    {
        JDFDoc doc = new JDFDoc();
        try
        {
            super.parse(inSource);

            doc.setMemberDoc((DocumentJDFImpl) getDocument());

            if (bEraseEmpty)
            {
                doc.getRoot().eraseEmptyNodes(true); // cleanup the XML
            }
        }
        catch (SAXException e)
        {
            lastExcept = e;
            doc = null;
        }
        catch (IOException e)
        {
            lastExcept = e;
            doc = null;
        }
        catch (JDFException e)
        {
            lastExcept = e;
            doc = null;
        }

        if (doc != null && m_ErrorHandler != null)
        {
            doc.setValidationResult(m_ErrorHandler.getXMLOutput());
            m_ErrorHandler.cleanXML(m_SchemaLocation);
        }

        if(doc!=null)
        {
            KElement root=doc.getRoot();
            if(root.getNamespaceURI()!=JDFElement.getSchemaURL())
            {
                final DocumentJDFImpl memberDocument = doc.getMemberDocument();
                memberDocument.bKElementOnly=true;
                memberDocument.setIgnoreNSDefault(true);               
            }
        }
        return doc;
    }


    private void setIgnoreNamespace()
    {
        try
        {
            setFeature("http://xml.org/sax/features/namespaces", false);
            setFeature("http://xml.org/sax/features/validation", false);
        }
        catch (SAXNotRecognizedException e)
        {
            lastExcept=e;
        }
        catch (SAXNotSupportedException e)
        {
            lastExcept=e;
        }
    }

}
