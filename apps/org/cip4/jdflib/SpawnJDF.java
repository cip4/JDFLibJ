
//Titel:        JDF TestApplication
//Version:
//Copyright:    Copyright (c) 1999
//Autor:       Sabine Jonas, sjonas@topmail.de
//Firma:      BU/GH Wuppertal
//Beschreibung:  first Applications using the JDFLibrary
//package testApps;

package org.cip4.jdflib;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import org.cip4.jdflib.core.AttributeName;
import org.cip4.jdflib.core.ElementName;
import org.cip4.jdflib.core.JDFAudit;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.JDFParser;
import org.cip4.jdflib.core.JDFResourceLink;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.VElement;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.core.XMLDoc;
import org.cip4.jdflib.core.JDFElement.EnumVersion;
import org.cip4.jdflib.core.JDFResourceLink.EnumUsage;
import org.cip4.jdflib.datatypes.JDFAttributeMap;
import org.cip4.jdflib.datatypes.VJDFAttributeMap;
import org.cip4.jdflib.node.JDFNode;
import org.cip4.jdflib.node.JDFSpawned;
import org.cip4.jdflib.pool.JDFAmountPool;
import org.cip4.jdflib.pool.JDFAncestorPool;
import org.cip4.jdflib.pool.JDFAuditPool;
import org.cip4.jdflib.pool.JDFResourceLinkPool;
import org.cip4.jdflib.pool.JDFResourcePool;
import org.cip4.jdflib.resource.JDFMerged;
import org.cip4.jdflib.resource.JDFResource;
import org.cip4.jdflib.resource.JDFResource.EnumResourceClass;
import org.cip4.jdflib.resource.intent.JDFDropItemIntent;
import org.cip4.jdflib.resource.process.JDFComponent;
import org.cip4.jdflib.util.JDFSpawn;
import org.cip4.jdflib.util.MyArgs;
import org.cip4.jdflib.util.StringUtil;

public class SpawnJDF
{
    private static boolean fileExists(String name)
    {
        boolean exists = false;

        File file = new File(name);
        if (file.length() != 0.0)
        {
            exists = file.exists();
        }

        return exists;
    }

    public static void main(String argv[])
    {   
        // -i bookintent.jdf -o spawned.jdf -p 4
        // -i bookintent.jdf -o spawned.jdf -p 0 -w r0007
        // -iic2.jdf -ospawnic.jdf -pp1 -wOutput
        String prg = "SpawnJDF" + ":\t";
        System.out.print(prg + "START.");

        MyArgs args = new MyArgs(argv, "adenv", "iopkwt",null);
        System.out.println("MyArgs-args:" + args);

        String usage =
            "SpawnJDF: JDF spawn processor;\n"
            + "-i input JDF;\n"
            + "-o output JDF;\n"
            + "-p jobpartId;\n"
            + "-k keys comma separated list of part keys\n"
            + "-w spawn global resources rw(default=ro)\n"
            + "-d delete node from original file"
            + "-n export as new jdf";

        boolean doEscapes = args.boolParameter('e' + JDFConstants.EMPTYSTRING, false);
        System.out.println(prg + "e: doEscapes=" + doEscapes);

        boolean useVDOMParser = args.boolParameter('v' + JDFConstants.EMPTYSTRING, false);
        System.out.println(prg + "v: useVDOMParser=" + useVDOMParser);


        VString vRWResources = new VString(StringUtil.tokenize(args.parameter('w'), ",", false));
        System.out.println(prg + "w: resRW=" + args.parameter('w'));

        //-//HDM//-//Check command line and extract arguments.
        String xmlFile = args.parameter('i');
        System.out.println(prg + "i: xmlFile=" + xmlFile);

        if (!fileExists(xmlFile))
        {
            System.err.println(args.usage(usage));
            System.exit(1);
        }

        String outFile = args.parameter('o');
        System.out.println(prg + "o: outFile=" + outFile);

        String task = args.parameter('t');
        System.out.println(prg + "t: task=" + task);

        boolean v = args.boolParameter('v' + JDFConstants.EMPTYSTRING, false);
        System.out.println(prg + "v: =" + v);

        String strPartID = args.parameter('p');
        System.out.println(prg + "p: =" + strPartID);

        // start spawning
        // ==============
        JDFParser p = new JDFParser();
        JDFDoc docIn = p.parseFile(xmlFile);

        if (docIn == null)
        {
            System.err.println(args.usage(""));
            System.exit(1);
        }
        else
        {

            JDFNode rootIn = (JDFNode) docIn.getRoot();
            // always assume jdf 1.3 or higher  when spawning to jdf 2.0
            if(args.boolParameter('n', false))
                rootIn.fixVersion(EnumVersion.Version_1_3); 

            JDFNode pCut;
            if (strPartID.equals(""))
            {
                pCut = rootIn;
            }
            else
            {
                pCut = rootIn.getJobPart(strPartID, "");
            }

            if (pCut == null)
            {
                System.err.println("No such JobPartID: " + strPartID);
                System.exit(1);
            }
            else
            {
                VJDFAttributeMap vSpawnParts = new VJDFAttributeMap();
                JDFAttributeMap part1 = new JDFAttributeMap();
                Vector partKeys = StringUtil.tokenize(args.parameter('k'), ",", false);
                for (int iKey = 1; iKey < partKeys.size(); iKey += 2)
                {
                    part1.put(
                            (String) partKeys.elementAt(iKey - 1), (String) partKeys.elementAt(iKey));
                }

                vSpawnParts.add(part1);
                final JDFSpawn spawn=new JDFSpawn(pCut);

                JDFNode node = spawn.spawn(xmlFile,outFile,vRWResources,vSpawnParts,false,true,true,true);
                final KElement rootOut;

                if(args.boolParameter('n', false))
                {
                    rootOut=makeNewJDF(node,rootIn);
                }
                else
                {
                    rootOut = node;
                }

                // neu gespawntes File rausschreiben
                XMLDoc docOut = rootOut.getOwnerDocument_KElement();
                docOut.write2File(outFile, 0, false);

                // verändertes Ausgangsfile rausschreiben
                String strOutXMLFile = "_" + xmlFile;
                rootIn.eraseEmptyNodes(true);
                docIn.write2File(strOutXMLFile, 2, false);

                System.out.print(prg + "ENDE.");
                System.exit(0);
            }
        }
    }

    /**
     * @param node
     * @return
     */
    private static KElement makeNewJDF(JDFNode node, JDFNode rootIn)
    {
        JDFDoc newDoc=new JDFDoc("XJDF");
        KElement newRoot=newDoc.getRoot();
        setRootAttributes(node, newRoot);
        setProduct(node,rootIn);
        setResources(newRoot, node,null);
        setElements(node, newRoot);
        return newRoot;
    }

    /**
     * @param node
     * @param newRoot
     */
    private static void setElements(JDFNode node, KElement newRoot)
    {
        setAudits(newRoot,node);
        VElement v=node.getChildElementVector(null, null, null, true, 0,false);
        for(int i=0;i<v.size();i++)
        {
            KElement e=v.item(i);
            if(e instanceof JDFResourceLinkPool)
                continue;
            if(e instanceof JDFResourcePool)
                continue;
            if(e instanceof JDFAncestorPool)
                continue;
            if(e instanceof JDFAuditPool)
                continue;
            newRoot.copyElement(e, null);
        }
    }

    /**
     * @param newRoot
     * @param node
     */
    private static void setAudits(KElement newRoot, JDFNode node)
    {
        JDFAuditPool ap=node.getAuditPool();
        if(ap==null)
            return;
        VElement audits=ap.getAudits(null, null,null);
        KElement newPool=newRoot.appendElement("AuditPool");
        int n=0;
        for(int i=0;i<audits.size();i++)
        {
            JDFAudit audit=(JDFAudit) audits.elementAt(i);
            if(audit instanceof JDFSpawned)
                continue;
            if(audit instanceof JDFMerged)
                continue;
            newPool.copyElement(audit, null);
            n++;
        }
        if(n==0)
            newPool.deleteNode(); 
    }

    /**
     * @param node
     * @param rootIn
     */
    private static String setProduct(JDFNode node, JDFNode rootIn)
    {
        if(rootIn==null)
            return null;
        if(!rootIn.getType().equals("Product"))
            return null;
        KElement list=node.getCreateElement("ProductList");
        KElement product=list.appendElement("Product");
        product.setAttributes(rootIn);
        setProductResources(product,rootIn);
        VElement subProducts=rootIn.getvJDFNode("Product", null, true);
        for(int i=0;i<subProducts.size();i++)
        {
            String childID=setProduct(node, (JDFNode)subProducts.elementAt(i));
            product.appendAttribute("Children", childID, null, " ", true);
        }
        return product.getAttribute("ID");
    }

    /**
     * @param product
     * @param rootIn
     */
    private static void setProductResources(KElement product, JDFNode rootIn)
    {
        VElement prodLinks=rootIn.getResourceLinks(null);
        HashMap componentMap=new HashMap();
        for(int i=prodLinks.size()-1;i>=0;i--)
        {
            JDFResourceLink rl=(JDFResourceLink) prodLinks.elementAt(i);
            final JDFResource linkRoot = rl.getLinkRoot();
            if(linkRoot instanceof JDFComponent)
            {
                prodLinks.remove(i);
                if(EnumUsage.Output.equals(rl.getUsage()))
                {
                    linkRoot.setAttribute("tmp_id",linkRoot.getID());
                    componentMap.put(linkRoot.getID(), rootIn.getID());
                }
            }
        }
        setResources(product,rootIn, prodLinks);
        VElement vDropItems=product.getChildrenByTagName(ElementName.DROPITEMINTENT, null, null, false, true, 0);
        for(int i=0;i<vDropItems.size();i++)
        {
            final JDFDropItemIntent dropItemIntent = (JDFDropItemIntent) vDropItems.item(i);
            JDFComponent c=dropItemIntent.getComponent();
            if(c!=null)
            {
                String id=(String) componentMap.get(c.getAttribute("tmp_id", null, ""));
                if(id!=null)
                {
                    dropItemIntent.setAttribute("ProductRef", id);
                    c.deleteNode();
                }
            }
        }
    }

    /**
     * @param product
     * @param rootIn
     * @return
     */
    private static void setResources(KElement newRoot, JDFNode rootIn, VElement resLinks)
    {
        VElement vResLinks=resLinks==null ? rootIn.getResourceLinks(null) : resLinks;
        if(vResLinks==null)
            return;
        for(int i=0;i<vResLinks.size();i++)
        {
            JDFResourceLink rl=(JDFResourceLink) vResLinks.elementAt(i);
            final JDFResource linkRoot = rl.getLinkRoot();
            setResource(newRoot, rl, linkRoot);
        }
        return;
    }

    /**
     * @param newRoot
     * @param rl
     * @param linkRoot
     */
    private static void setResource(KElement newRoot, JDFResourceLink rl, final JDFResource linkRoot)
    {
        String className=getClassName(linkRoot);
        if(className==null)
            return;
        KElement resourceSet=newRoot.appendElement(className+"Set");

        setLinkAttributes(resourceSet, rl, linkRoot);
        
        VElement vRes=rl.getTargetVector(0);
        int dot=0;
        String resID=linkRoot.getID();
        for(int j=0;j<vRes.size();j++)
        {
            JDFResource r=(JDFResource)vRes.elementAt(j);
            VElement vLeaves=r.getLeaves(false);
            for(int k=0;k<vLeaves.size();k++)
            {
                JDFResource leaf=(JDFResource)vLeaves.elementAt(k);
                //TODO this is just a quick hack - generating true id, idref pairs would be better
                leaf.inlineRefElements(null, null, false);
                KElement newLeaf=resourceSet.appendElement(className);
                setLeafAttributes(leaf, rl, newLeaf);
                newLeaf.setAttribute("ID", resID+"."+StringUtil.formatInteger(dot++));
            }
        }
    }

    /**
     * @param leaf
     * @param newLeaf
     */
    private static void setLeafAttributes(JDFResource leaf, JDFResourceLink rl, KElement newLeaf)
    {
        JDFAttributeMap partMap=leaf.getPartMap();
        //                   JDFAttributeMap attMap=leaf.getAttributeMap();
        //                   attMap.remove("ID");
        JDFAmountPool ap=rl.getAmountPool();
        if(ap!=null)
        {
            VElement vPartAmounts=ap.getMatchingPartAmountVector(partMap); 
            if(vPartAmounts!=null)
            {
                KElement amountPool=newLeaf.appendElement("AmountPool");
                for(int i=0;i<vPartAmounts.size();i++)
                    amountPool.copyElement(vPartAmounts.item(i), null);
            }
        }
        if(partMap!=null &&partMap.size()>0)    
        {
            newLeaf.appendElement("Part").setAttributes(partMap);
            //                     attMap.removeKeys(partMap.keySet());
        }

        KElement newResLeaf=newLeaf.copyElement(leaf, null);
        newResLeaf.removeAttribute(AttributeName.ID);
        newResLeaf.removeAttribute(AttributeName.SPAWNID);
        newResLeaf.removeAttribute(AttributeName.SPAWNIDS);
        newResLeaf.removeAttribute(AttributeName.SPAWNSTATUS);
        newResLeaf.removeAttribute(AttributeName.PARTUSAGE);

        newLeaf.moveAttribute("DescriptiveName", newResLeaf, null, null, null);
    }

    /**
     * @param r
     */
    private static String getClassName(JDFResource r)
    {
        if(r==null)
            return null;
        final EnumResourceClass resourceClass = r.getResourceClass();
        if(resourceClass==null)
            return null;
        String className="Resource";
        if(resourceClass.equals(EnumResourceClass.Parameter)||resourceClass.equals(EnumResourceClass.Intent))
            className=resourceClass.getName();
        if(resourceClass.equals(EnumResourceClass.PlaceHolder))
            return null;
        return className;
    }

    /**
     * @param newRoot
     * @param rl
     */
    private static void setLinkAttributes(KElement resourceSet, KElement rl, JDFResource linkRoot)
    {
        resourceSet.setAttribute("Name",linkRoot.getNodeName());
        resourceSet.copyAttribute("ID", linkRoot, null, null, null);
        resourceSet.setAttributes(rl);
        resourceSet.removeAttribute(AttributeName.RREF);
        resourceSet.removeAttribute(AttributeName.RSUBREF);
        if(rl instanceof JDFResourceLink)
        {
            JDFResourceLink resLink=(JDFResourceLink)rl;
            VElement vCreators=linkRoot.getCreator(EnumUsage.Input.equals(resLink.getUsage()));
            final int size = vCreators==null ? 0 : vCreators.size();
            for( int i=0;i<size;i++)
            {
                JDFNode depNode=(JDFNode) vCreators.elementAt(i);
                KElement dependent=resourceSet.appendElement("Dependent");
                dependent.setAttribute(AttributeName.JOBID, depNode.getJobID(true));
                dependent.copyAttribute(AttributeName.JMFURL, depNode, null, null, null);
                dependent.copyAttribute(AttributeName.JOBPARTID, depNode, null, null, null);
            }
        }
    }

    /**
     * @param node
     * @param newRoot
     */
    private static void setRootAttributes(JDFNode node, KElement newRoot)
    {
        newRoot.setAttributes(node);
    }


}
