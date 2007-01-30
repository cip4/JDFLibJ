package org.cip4.jdflib.generator.gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;


/**
 * OrganizeImports
 * 
 * Important!!! For OrganizeImports to work it is necessary to add the jar files of the JDFLib  
 * and all external jars from the build path through the menu "Tools / Add external jars ..."
 * (JDFLib.jar, commons-lang.jar, junit.jar, xercesImpl.jar, xml-apis.jar as of 23.5.2006)
 * 
 * INFO: here are the regex definitions with are used to find the imports. These one are in one long
 * regex inside the method. Thats why they are split apart here. Others are used also, these are not
 * mentioned here.
 * 
 * regex to find declarations "\\w+(?=\\s+\\w+\\s*=)" regex to find methods
 * "\\w+(?=\\s+\\w+\\s*\\(.*\\)\\s*\\{)" regex to find new "(?<=new\\s{1,100})\\w+"; regex to find
 * throws "(?<=throws\\s{1,100})\\w+" regex to find catches "(?<=catch\\s{0,100}\\(\\s{0,100})\\w+"
 * regex to find extends "(?<=extends\\s{1,100})\\w+"
 * 
 * @author Mattern
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class OrganizeImports
{
    protected static final ArrayList knownTypes        = new ArrayList();

    protected static final Pattern   m_importsToFind     = Pattern
                                                               .compile("\\w+(?=\\s+\\w+\\s*=)|\\w+(?=\\s+\\w+\\s*\\(.*\\)\\s*\\{)|(?<=new\\s{1,100})\\w+|(?<=throws\\s{1,100})\\w+|(?<=catch\\s{0,100}\\(\\s{0,100})\\w+|(?<=extends\\s{1,100})\\w+|\\s+[A-Z]\\w+\\.|\\([A-Z]\\w+\\.");

    private static final String      EMPTYSTRING       = "";

    private static final String      BLANK             = " ";

    private static final String      COMMA             = ",";

    protected final String           lineSep           = System.getProperty("line.separator");

    protected final String           fileSep           = System.getProperty("file.separator");

    protected boolean                bLoadFilesDone    = false;

    protected boolean                bfindImportsDone  = false;

    protected boolean                bWriteFilesDone   = false;

    protected ArrayList              m_loadQueue       = new ArrayList();

    protected ArrayList              m_findImportQueue = new ArrayList();

    protected ArrayList              paths             = new ArrayList();

    protected ListButtonPanel        motherComp        = null;

    /**
     * Organizes imports for most files with some limitations
     * 
     * @param strPath
     *            complete path "C:\test.java" for example
     * @return true if done
     */
    public void organizeImports (ArrayList strPaths, ListButtonPanel mother)
    {
        motherComp = mother;
        paths.addAll(strPaths);
        loadFile();
        findImports();
        writeImports();
    }

    protected ArrayList getFilePaths ()
    {
        return paths;
    }

    protected void setFilePaths (ArrayList list)
    {
        paths = list;
    }

    private void loadFile ()
    {
        new Thread() {

            public void run ()
            {
                setPriority(1);
                Iterator it = paths.iterator();
                while (it.hasNext())
                {
                    String strFilePath = (String) it.next();
                    String strFileName = strFilePath.substring(
                            strFilePath.lastIndexOf(fileSep) + 1, strFilePath.length() - 5);
                    StringBuffer buff = new StringBuffer(1000);
                    String line = EMPTYSTRING;
                    BufferedReader br = null;

                    try
                    {
                        br = new BufferedReader(new FileReader(strFilePath));

                        while ((line = br.readLine()) != null)
                        {
                            buff.append(line);
                            buff.append(lineSep);
                        }

                        m_loadQueue.add(new String[] { buff.toString(), strFileName, strFilePath });
                    }
                    catch (IOException ioe)
                    {
                        ioe.printStackTrace();
                    }
                    finally
                    {
                        if (br != null)
                        {
                            try
                            {
                                br.close();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                bLoadFilesDone = true;
            }
        }.start();

    }

    /**
     * findImports using the regexp pattern m_importsToFind candidates are added to set all results
     * are stored in array list l, which in turn is stored in m_findImportQueue
     * 
     */
    private void findImports ()
    {
        new Thread() {
            public void run ()
            {
                GeneratorUI mainFrame = motherComp.getComplexTypeList().getMainFrame();
                String frameTitle = mainFrame.getTitle();
                int iAlreadyOrganized = 1;
                setPriority(1);

                while (!m_loadQueue.isEmpty() || bLoadFilesDone == false)
                {
                    if (m_loadQueue.isEmpty())
                    {
                        try
                        {
                            sleep(100);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        String[] params = (String[]) m_loadQueue.get(0);
                        String strFileContent = params[0];
                        String strFilePath = params[2];
                        String strFilePathCutted = params[1];

                        int iIndexOfSep = strFilePathCutted.lastIndexOf(lineSep);
                        String strFileName = 
                            strFilePathCutted.substring(iIndexOfSep + 1, strFilePathCutted.length());

                        
                        DefaultTableModel dtm = mainFrame.getStatusPanel().getDefaultTableModel();
                        dtm.insertRow(0, new Object[] { "Organizing Imports " + strFileName, "Working..." });

                        
                        Pattern[] patternSet = new Pattern[] { m_importsToFind };
                        TreeSet set = new TreeSet();
                        
                        for (int i = 0; i < patternSet.length; i++)
                        {
                            Matcher findMatches = patternSet[i].matcher(strFileContent);
                            while (findMatches.find())
                            {
                                String s = findMatches.group();
                                if (s.endsWith("."))
                                {
                                    s = s.substring(0, s.length() - 1);
                                }

                                if (s.startsWith("("))
                                {
                                    s = s.substring(1);
                                }

                                s = s.trim();
                                set.add(s);
                            }
                        }
                        

                        // special regex for constructors
                        String regex_constructor = "(?<=" + strFileName + "\\s{0,100}\\().*?(?=\\))";
                        Pattern constructorPattern = 
                            Pattern.compile(regex_constructor, Pattern.DOTALL);
                        
                        Matcher findMatches = constructorPattern.matcher(strFileContent);
                        while (findMatches.find())
                        {
                            String s = findMatches.group();
                            StringTokenizer st = new StringTokenizer(s, COMMA);
                            while (st.hasMoreTokens())
                            {
                                String token = st.nextToken();
                                token = token.trim();
                                int i = token.indexOf(BLANK);
                                if (i != -1)
                                {
                                    token = token.substring(0, i);
                                    set.add(token);
                                }
                            }
                        }
                        

                        // special regex for method parameter, this can't be
                        // done in the overall regex
                        // because parameters can be comma separated
                        String regex_methodParameter = "(?<=\\()[A-Z]\\w+\\s+\\w+.*?(?=\\))";
                        Pattern methodParameterPattern = Pattern.compile(regex_methodParameter, Pattern.DOTALL);
                        Matcher findMethodParameter = methodParameterPattern.matcher(strFileContent);

                        while (findMethodParameter.find())
                        {
                            String s = findMethodParameter.group();
                            StringTokenizer st = new StringTokenizer(s, COMMA);
                            while (st.hasMoreTokens())
                            {
                                String token = st.nextToken();
                                token = token.trim();
                                int i = token.indexOf(BLANK);
                                if (i != -1)
                                {
                                    token = token.substring(0, i);
                                    set.add(token);
                                }
                            }
                        }
                        
                        set.removeAll(knownTypes);
                        
                        ArrayList l = new ArrayList();
                        l.add(set);
                        l.add(strFilePathCutted);
                        l.add(strFileContent);
                        l.add(strFileName);
                        l.add(strFilePath);
                        m_findImportQueue.add(l);
                        
                        m_loadQueue.remove(0);

                        try
                        {
                            dtm.setValueAt("Done", 0, 1);
                            mainFrame.setTitle(
                                    frameTitle + " - " + "Already organized: " + iAlreadyOrganized++);
                        }
                        catch (ArrayIndexOutOfBoundsException e)
                        {
                            e.hashCode(); // to remove e never read warning
                        }
                    }
                }

                bfindImportsDone = true;
                motherComp.setGenerateButtonEnabled(true);
                mainFrame.setTitle(frameTitle);
            }
        }.start();

    }

    private void writeImports ()
    {
        new Thread() {
            public void run ()
            {
                setPriority(1);
                StringBuffer buff = new StringBuffer(1000);

                while (!m_findImportQueue.isEmpty() || bfindImportsDone == false)
                {
                    if (m_findImportQueue.isEmpty())
                    {
                        try
                        {
                            sleep(100);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        ArrayList l = (ArrayList) m_findImportQueue.get(0);

                        List li = new ArrayList();
                        TreeSet set = (TreeSet) l.get(0);
                        Iterator it = set.iterator();

                        while (it.hasNext())
                        {
                            String classname = (String) it.next();
                            if (GeneratorUI.jarClasses.get(classname) != null)
                            {
                                li.add(GeneratorUI.jarClasses.get(classname));
                            }
                        }

                        Collections.sort(li);
                        Iterator li_it = li.iterator();

                        while (li_it.hasNext())
                        {
                            buff.append("import ").append(li_it.next()).append(";");
                            buff.append(lineSep);
                        }

                        buff = beautifyImports(buff);

                        m_findImportQueue.remove(0);
                        String imports = buff.toString().trim() + lineSep;
                        buff.delete(0, buff.length());

                        String strFileContent = (String) l.get(2);
                        ArrayList retArr = removeImportsFromFile(strFileContent);

                        String strFilePath = (String) l.get(4);

                        writeFileToHD(imports, retArr, strFilePath);
                    }
                }

                bWriteFilesDone = true;
            }
        }.start();
    }

    /**
     * @param str
     *            the file as String
     * 
     * @return Arraylist at 0, the removed filecontent at 1, the line number of the first import (0
     *         based, 0 is first line) at 2, the line number of the public class definition (0
     *         based, 0 is first line)
     */
    protected ArrayList removeImportsFromFile (String str)
    {
        boolean publicClassNotFound = true;
        boolean endOfFileNotReached = true;
        boolean firstImportNotFound = true;
        int indexOfFirstImport = 0;
        int indexOfPublicClass = 0;
        int actualLineNumber = 0;

        Pattern importPat = Pattern.compile("import.*?;", Pattern.DOTALL);
        Pattern publicClassPat = Pattern.compile("public\\s*class");
        String strFileContent = str;
        String[] fileAsLines = strFileContent.split(lineSep);
        int lastLine = fileAsLines.length;

        StringBuffer strBuff = new StringBuffer(1000);

        while (endOfFileNotReached)
        {
            if (publicClassNotFound)
            {
                Matcher findImport = importPat.matcher(fileAsLines[actualLineNumber]);
                if (findImport.find())
                {
                    if (firstImportNotFound)
                    {
                        indexOfFirstImport = actualLineNumber;
                        firstImportNotFound = false;
                    }
                }
                else
                {
                    strBuff.append(fileAsLines[actualLineNumber]);
                    strBuff.append(lineSep);
                }

                Matcher findPublicClass = publicClassPat.matcher(fileAsLines[actualLineNumber]);
                if (findPublicClass.find())
                {
                    indexOfPublicClass = actualLineNumber;
                    publicClassNotFound = false;
                }
            }
            else
            {
                strBuff.append(fileAsLines[actualLineNumber]);
                strBuff.append(lineSep);
            }
            // stop at file end
            if (actualLineNumber++ == lastLine - 1)
            {
                endOfFileNotReached = false;
            }
        }

        ArrayList retArr = new ArrayList();
        retArr.add(strBuff.toString());
        retArr.add(new Integer(indexOfFirstImport));
        retArr.add(new Integer(indexOfPublicClass));

        return retArr;
    }

    /**
     * 
     * @param fileContent
     *            the imports to add
     * @param info
     *            line numbers of where to insert the imports and the filecontent itself as String
     * @see removeImportsFromFile for details about info organization
     */
    protected void writeFileToHD (String importContent, ArrayList info, String strFilePath)
    {
        StringBuffer str = new StringBuffer(1000);
        String strFileContent = (String) info.get(0);
        int iLineOfFirstImport = ((Integer) info.get(1)).intValue();

        int i = 0;
        String[] sepLines = strFileContent.split(lineSep);
        for (; i < iLineOfFirstImport; i++)
        {
            str.append(sepLines[i]);
            str.append(lineSep);
        }
        str.append(importContent);
        i++;
        for (; i < sepLines.length; i++)
        {
            str.append(sepLines[i]);
            str.append(lineSep);
        }

        FileWriter fw = null;
        try
        {
            try
            {
                fw = new FileWriter(strFilePath);
                fw.write(str.toString());
            }
            finally
            {
                if (fw != null)
                    fw.close();
            }
        }
        catch (IOException e)
        {
            e.hashCode(); // to remove e never read warning
        }

    }

    /**
     * @param b
     *            the StingBuffer of all imports
     * @return StringBuffer with imports separated in sections "java", "org", "com" inside a section
     *         the imports are sorted alphabetically, after a section is a newline
     */
    protected StringBuffer beautifyImports (StringBuffer b)
    {
        String[] importlines = b.toString().split(lineSep);
        HashSet hash = new HashSet();
        int index = -1;

        for (int i = 0; i < importlines.length; i++)
        {
            if ((index = importlines[i].indexOf(".")) != -1)
            {
                String pack = importlines[i].substring("import ".length(), index);
                hash.add(pack);
            }
        }

        ArrayList packSort = new ArrayList();

        packSort.add("java");
        hash.remove("java");

        packSort.add("org");
        hash.remove("org");

        packSort.add("com");
        hash.remove("com");

        Iterator it = hash.iterator();
        while (it.hasNext())
        {
            packSort.add(it.next());
        }

        StringBuffer buffi = new StringBuffer(600);
        List separatedPack = new ArrayList();
        Object[] packages = packSort.toArray();

        for (int i = 0; i < packages.length; i++)
        {
            for (int j = importlines.length - 1; j > -1; j--)
            {
                if ((importlines[j]).startsWith("import " + (String) packages[i]))
                {
                    separatedPack.add(importlines[j]);
                }
            }

            Collections.sort(separatedPack);

            Iterator sepPackIt = separatedPack.iterator();
            while (sepPackIt.hasNext())
            {
                buffi.append((String) sepPackIt.next());
                buffi.append(lineSep);
            }

            buffi.append(lineSep);
            separatedPack.clear();
        }

        return buffi;
    }

    static
    {
        knownTypes.add("String");
        knownTypes.add("int");
        knownTypes.add("double");
        knownTypes.add("long");
        knownTypes.add("float");
        knownTypes.add("byte");
        knownTypes.add("char");
        knownTypes.add("void");
        knownTypes.add("boolean");
        knownTypes.add("private");
        knownTypes.add("static");
        knownTypes.add("public");
        knownTypes.add("protected");
    }
}
