/**
 *
 *  Copyright (c)   2002 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *  Author:         Kai Mattern
 *  Titel:          SchemaDoc.java
 *  Version:        0.1
 *  Description:
 *
 *  History:        03-13-2002  Kai Mattern started file
 *
 *  TBD:
 */

//package
package org.cip4.jdflib.generator; 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import org.cip4.jdflib.core.DocumentJDFImpl;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.datatypes.JDFAttributeMap;


//======================================================================================================
//     SchemaDoc
//=======================================================================================================
public class SchemaDoc extends XMLDoc
{
    //class globals
    
    public SchemaDoc(DocumentJDFImpl doc)
    {
        super(doc);
    }

    /**
     * This is the main entrance method after the Generator has parsed the xml file.
     *
     * @param   String strType  -   "Message" for Message-Type
     *                               "Core" for Core-Type
     *                               "Node" for Node-Type
     * @param   isJava              true if the code to be generated is Java. False for C++
     *  
     * @return int - zero always
     */
    public Vector getSchemaInfo(String strType, boolean isJava)
    {
        Vector vComplexTypes = new Vector();
        
        KElement r = getRoot();
        // Get all complex Types out of the schema
        VElement vAllComplexTypes = r.getChildrenByTagName ("xs:complexType", "", new JDFAttributeMap(), false, true, 0);
        VElement vAllSimpleTypes  = r.getChildrenByTagName ("xs:simpleType", "", new JDFAttributeMap(), false, true, 0);
        VElement vAllElements     = r.getChildElementVector("xs:element", "", new JDFAttributeMap(), true, 0,false);
        
        SchemaComplexType complexType = null;

        // wrap all objects inside a "SchemaComplexType" and add them to main Vector
        final int vAllComplexTypeSize = vAllComplexTypes.size();
        for (int i = 0; i < vAllComplexTypeSize; i++)
        {
            if (((vAllComplexTypeSize-i) % 50) == 0) 
                System.out.println(vAllComplexTypeSize-i);
            
            // Wrap the KElement "Assembly_AssemblySection_AssemblySection_lr" 
            // into the SchemaComplexType "Assembly" (leads to duplicates)
            KElement schemaElement = vAllComplexTypes.elementAt(i);
            // System.out.println (schemaElement.getAttribute_KElement("name","",""));
            complexType = new SchemaComplexType(schemaElement); // sct.strSchemaComplexTypeName

            complexType = fillComplexType (schemaElement, complexType, vAllComplexTypes, 
                    vAllSimpleTypes, vAllElements, vComplexTypes, strType, isJava);

            vComplexTypes.add(complexType);
        }

        vComplexTypes = removeDuplicates(vComplexTypes);
        
        return vComplexTypes;
    }


    /**
     * @param schemaElement TODO
     * @param vAllComplexTypes TODO
     * @param vAllSimpleTypes
     * @param vAllElements
     * @param vComplexTypes
     * @param strType
     * @param isJava
     * @param schemaElement
     * @return
     */
    private SchemaComplexType fillComplexType(
            KElement schemaElement, SchemaComplexType complexType, VElement vAllComplexTypes, 
            VElement vAllSimpleTypes, VElement vAllElements, Vector vComplexTypes, String strType, boolean isJava)
    {
        // sct.strSchemaComplexTypeName
        
        // First all names of the complextypes need to be shortened. Some end on '__' '_r' '_re' etc. 
        // these "extensions" are not needed here. Cut them away.
        complexType = GeneratorUtil.unifyComplexTypNames(complexType, strType);
    
        // set the kind of element "who am i" need for all generation later.
        // set Kind of Code to generate at the moment
        complexType.isJava = isJava;
        // typesafe all ComplexTypes for further use
        if ("Message".equals(strType))
        {
            complexType.isMessage = true;
        }
        else if ("Core".equals(strType))
        {
            complexType.isCore = true;
        }
        else if ("Node".equals(strType))
        {
            complexType.isNode = true;
        }
        else if ("CoreMessage".equals(strType))
        {
            complexType.isCore     = true;
            complexType.hasMessage = true;
        }
    
        String[] parents          = GeneratorUtil.fillParents(complexType);
        VElement vAppInfoElements = new VElement();
        String motherOf           = complexType.getStrMotherOfComplexType();

        if (!JDFConstants.EMPTYSTRING.equals(motherOf))
        {
            GeneratorUtil.fillAppInfoElements(motherOf, parents, vAllElements,     vAppInfoElements);
            GeneratorUtil.fillAppInfoElements(motherOf, parents, vAllComplexTypes, vAppInfoElements);
        }
        
        // Take a member out of the schema, process the attributes and put it back into the vector
        complexType = GeneratorUtil.getAllValidAttributes(
                schemaElement, vComplexTypes, vAllSimpleTypes, parents, vAppInfoElements, complexType);
    
        // Take a member out of the schema, process the elements and put it back into the vector
        complexType = GeneratorUtil.getAllValidElements(parents, vAppInfoElements, complexType);
    
        // Now its time to get the rest info (enumerations, node String infos etc)
        complexType = GeneratorUtil.getRestInfo(parents, vAppInfoElements, complexType);
        
        return complexType;
    }

    
    /**
     * 
     */
    private Vector removeDuplicates(Vector vMyCompleteSchema)
    {
        SchemaComplexType nOldSchemaComplexType;
        SchemaComplexType nNewSchemaComplexType;
        
        // At this point you have a Vector of all ComplexTyps of the Schema.
        // But quit a few of em are double or triple. Those will now be ripped out.
        Vector nJustALittleHelper = new Vector ();

        while (vMyCompleteSchema.size () != 0)
        {
            // Ok first dump the first Element in the helper Vector
            nJustALittleHelper.add (vMyCompleteSchema.elementAt (0));
            // Delete the first element.. its dumped in the Second Vector and no longer needed
            vMyCompleteSchema.removeElementAt (0);

            // now find the elements with same name...transfer the elements and atttributes unique and delete them
            for (int j = 0; j < vMyCompleteSchema.size (); j++)
            {
                // ALTER TYP
                nOldSchemaComplexType = (SchemaComplexType) vMyCompleteSchema.elementAt (j);
                // NEUER TYP
                nNewSchemaComplexType = (SchemaComplexType) nJustALittleHelper
                        .elementAt (nJustALittleHelper.size () - 1);
                // Die Namen der beiden Element. Wichtig zur Feststellung von Gleichheit
                String str_A_SchemaName = nOldSchemaComplexType.m_SchemaComplexTypeName;
                String str_B_SchemaName = nNewSchemaComplexType.m_SchemaComplexTypeName;
                
                // TBD Löse den IF ausdruck auf, das kann ja kein .... lesen
                // im Klartext...wenn der Name des Elements am platz 'i' genauso ist wie der letze hinzugefügte zum
                // helper vector..
                // dann leg los
                if (str_A_SchemaName != null && str_A_SchemaName.equals (str_B_SchemaName))
                {
                    nNewSchemaComplexType = GeneratorUtil
                            .addElementUniqueToVector (nNewSchemaComplexType, nOldSchemaComplexType);
                    nNewSchemaComplexType = GeneratorUtil.addAttributeUniqueToVector (nNewSchemaComplexType,
                            nOldSchemaComplexType);

                    // something spezial for nodes
                    if (nOldSchemaComplexType.isNode)
                    {
                        // Due to the name unifier there are now 2 some complex Types in the vector
                        // one of both has the complete usage string for the elements, because they are definde
                        // in his body. The other one has a "empty" usage string. So the one with the empy needs it
                        // we dont want to lose it.

                        // if you found a empty usage string....
                        if (!",".equals (nOldSchemaComplexType.getStrNodeUsageString ()))
                        {
                            // get the other one!
                            nNewSchemaComplexType.getAllDataFromComplexType (nOldSchemaComplexType);
                        }
                    }
                    vMyCompleteSchema.removeElementAt (j);
                    // Because i deleted one element it is now needed to decrement j again.
                    // The old element x was deleted. The Element x+1 is now Element x
                    // If we justwould increment x in the next loop we would let out the old x+1 element
                    j -= 1;
                }
                
                nJustALittleHelper.setElementAt (nNewSchemaComplexType, nJustALittleHelper.size () - 1);
            } // for
        } // while

        // for Nodes exclusive delete out the '_' elements
        // for further information check the architecture of the Node-Schema
        for (int i = 0; i < nJustALittleHelper.size (); i++)
        {
            SchemaComplexType nSchemaComplexType = (SchemaComplexType) nJustALittleHelper.elementAt (i);
            if (nSchemaComplexType.m_SchemaComplexTypeName.endsWith ("_"))
            {
                nJustALittleHelper.removeElementAt (i);
                // cause you remover one member directly from the Vector the n+1 element will be the n element
                // after the remove operation. Therefore, you need to decrement the counter.
                i -= 1;
            }
        }

        vMyCompleteSchema = nJustALittleHelper;

        // 'init' all members (this writes some elementar informations into the complex-types
        // e.g. names, extends, return values etc..
        for (int i = 0; i < vMyCompleteSchema.size (); i++)
        {
            SchemaComplexType nSchemaComplexType = (SchemaComplexType) vMyCompleteSchema.elementAt (i);
            nSchemaComplexType.setStrSchemaComplexType (); // test
            nSchemaComplexType.m_ExtendOff = GeneratorUtil.getStrExtendsOff (nSchemaComplexType.m_kElem);
        }

        // last but not least, filter out those you dont want
        for (int i = 0; i < vMyCompleteSchema.size (); i++)
        {
            String strComplexTypeName = ((SchemaComplexType) vMyCompleteSchema.elementAt (i)).m_SchemaComplexTypeName;
            if (!GeneratorUtil.isComplexTypeToGenerate (strComplexTypeName))
            {
                vMyCompleteSchema.removeElementAt (i);
                i -= 1;
            }
        }

        for (int i = 0; i < vMyCompleteSchema.size (); i++)
        {
            KElement nPlaceHolder = ((SchemaComplexType) vMyCompleteSchema.elementAt (i)).
                m_kElem.getElement ("xs:complexContent", "", 0);
            
            if (nPlaceHolder != null)
            {
                SchemaComplexType test = new SchemaComplexType (nPlaceHolder);
                KElement nKElement = test.m_kElem.getElement ("xs:extension", "", 0);
                if (nKElement != null)
                {
                    String base = nKElement.getAttribute ("base", "", "");
                    if (base.equals (""))
                    {
                        base = "jdf:Parameter";
                    }
                    
                    SchemaComplexType sct = (SchemaComplexType) vMyCompleteSchema.elementAt (i);
                    sct.setBase (base);
                }
            }
        }
        
        return vMyCompleteSchema;
    }


   /**
     *  If you just want to generate a few of the complextypes and not the whole schema, you can call this method
     *  The Vector input parameter need to fulfill a view demands. Every Element in this Vector needs to be from the
     *  'SchemaComplexType' type. The generator needs the Info stored in those typs to generate the specific files.

     *  @param Vector nSchemaFragment - the SchemaFragment to generate
     */
    public static void toCoreJava(Vector nSchemaFragment, boolean bGenerateAll)
    {
        String strJavaFile = JDFConstants.EMPTYSTRING;
        SchemaComplexType nSchemaComplexType = null;

        final int schemaFragmentSize = nSchemaFragment.size();
        for (int i = 0; i < schemaFragmentSize; i++)
        {
            if (((schemaFragmentSize-i) % 50) == 0) 
                System.out.println(schemaFragmentSize-i);
            
            nSchemaComplexType = (SchemaComplexType) nSchemaFragment.elementAt(i);
            
            strJavaFile = JavaCoreStringUtil.getStrJavaCoreFile(nSchemaComplexType);
            writeToFile(Generator.m_strJdfCoreJava, Generator.m_strJdfLostAndFound, 
                    nSchemaComplexType.getStrAutoCoreFileName(), strJavaFile, bGenerateAll);
        }
    }
    
    
    /**
     *  If you just want to generate a few of the complextypes and not the whole schema, you can call this method
     *  The Vector input parameter need to fulfill a view demands. Every Element in this Vector needs to be from the
     *  'SchemaComplexType' type. The generator needs the Info stored in those typs to generate the specific files.

     *  @param Vector nSchemaFragment - the SchemaFragment to generate
     *  @return void - nothing
     */
    public static void toCoreCpp(Vector nSchemaFragment, boolean bGenerateAll)
    {
        String strCppFile    = JDFConstants.EMPTYSTRING;
        String strHeaderFile = JDFConstants.EMPTYSTRING;
        SchemaComplexType nSchemaComplexType = null;

        for (int i = 0; i < nSchemaFragment.size(); i++)
        {
            nSchemaComplexType = (SchemaComplexType) nSchemaFragment.elementAt(i);
            
            strCppFile = CppCoreStringUtil.getStrCppCoreFile(/*nSchemaComplexType*/);
            writeToFile(Generator.m_strJdfCoreCpp, Generator.m_strJdfLostAndFound, 
                    nSchemaComplexType.getStrAutoCppCoreFileNameCPP(), strCppFile, bGenerateAll);

            strHeaderFile = CppCoreStringUtil.getStrHeaderFile(nSchemaComplexType);
            writeToFile(Generator.m_strJdfCoreCpp, Generator.m_strJdfLostAndFound, 
                    nSchemaComplexType.getStrAutoCppCoreFileNameH(), strHeaderFile, bGenerateAll);
        }
    }
    
    
    private static void writeToFile(String strURLGood, String strURLBad, String strFileName, String strFileContent, boolean bGenerateAll)
    {
        if(bGenerateAll || isFileToGenerate(strFileName))
        {
            write2file(strURLGood, strFileName, true, strFileContent);
        }
        else
        {
            write2file(strURLBad, strFileName, true, strFileContent);
        }
    }
    
   /**
     * after all information was been collected the autofile will be written to hard drive. This method
     * will write a file to the hard drive and overwrite any exisiting file.
     *
     * @param   
     *  
     * @return 
     */   
    public static boolean write2file(String strPath, String fName, boolean overwriteFile, String content)
    {
//        String warning = "------> WARNING: ";
//        String error   = "===============> ERROR: ";
        FileOutputStream fos = null;
        try
        {// global path for library
            File path= new File(strPath);
            if (!path.exists() )
            {
                path.mkdirs();
            }
            if ( !path.exists() || !path.isDirectory())
            {
                return false;
            }
            // just the output-file
            if ( ! strPath.endsWith("/"))
            {
                strPath += "/";
            }
            File file= new File(strPath+fName);
            if (file.exists())
            {
                //System.err.println(warning +"["+file+"] exists.");
                if (overwriteFile)
                {
                    file.delete();
                    //System.err.println(warning +"\t and will be overwritten.");
                }
                else
                {
                    //System.err.println("");
                    return false;
                }
            }
            
            if (file.createNewFile()) 
            {
                if (file.canWrite())               
                {
                    fos = new FileOutputStream(file);
                    fos.write( content.getBytes() );
                }
            }
        }
        catch (FileNotFoundException e)
        {
            return false;
        }
        catch (IOException e)
        {
            return false;
        }
        finally
        {
            if (fos != null)
            {

                try
                {
                    fos.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
        return true;
    }
    
             
    private static boolean isFileToGenerate(String strFileName)
    {
        return strFileName == strFileName;
//        return isJavaFile(strFileName) || isCppFile(strFileName) || isHFile(strFileName);
    }
    
} // class SchemaDoc
