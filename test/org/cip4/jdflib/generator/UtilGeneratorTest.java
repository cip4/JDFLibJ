package org.cip4.jdflib.generator;

import junit.framework.TestCase;

public class UtilGeneratorTest extends TestCase
{

//    /*
//     * Test method for 'org.cip4.jdflib.generator.GeneratorUtil.getVersionInfo(String, String, String)'
//     */
//    public void testGetVersionInfoStringStringString ()
//    {
//        String first = null;
//        String last  = null;
//        System.out.println ("First   Last    optional    required\n");
//        for (int i = -1; i < 8; i++)
//        {
//            first = i<0 ? "" : "1."+i;
//            last  = "";
//            System.out.println (first+"     "+"   "+"     "+
//                    GeneratorUtil.getVersionInfo("optional", first, last)+"    "+
//                    GeneratorUtil.getVersionInfo("required", first, last));
//
//            for (int j = i; j < 8; j++)
//            {
//                last = j<0 ? "" : "1."+j;
//                System.out.println (first+"     "+last+"     "+
//                        GeneratorUtil.getVersionInfo("optional", first, last)+"    "+
//                        GeneratorUtil.getVersionInfo("required", first, last));
//            }
//            System.out.println ();
//        }
//    }
    
    /*
     * Test method for 'org.cip4.jdflib.generator.GeneratorUtil.getVersionInfo(Boolean, String, String, String)'
     */
    public void testGetVersionInfoBooleanStringStringString ()
    {
        String first = null;
        String last  = null;
        String maxOccurs0 = "0";
        String maxOccurs1 = "1";
        System.out.println ("First   Last    2-required-0  3-optional-0  5-required-1  6-optional-1\n");
        for (int i = 0; i < 8; i++)
        {
            first = "1."+i;
            for (int j = i; j < 8; j++)
            {
                last = "1."+j;
                System.out.println (" "+first+"     "+last+
                        "      "+GeneratorUtil.getVersionInfoElements(false, first, last, maxOccurs0)+
                        "      "+GeneratorUtil.getVersionInfoElements(true,  first, last, maxOccurs0)+
                        "      "+GeneratorUtil.getVersionInfoElements(false, first, last, maxOccurs1)+
                        "      "+GeneratorUtil.getVersionInfoElements(true,  first, last, maxOccurs1)
                        );
            }
            
            System.out.println ();
        }
    }

}
