package org.cip4.jdflib.generator.gui;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


class SyntaxHighlighter
{

    private MutableAttributeSet tagAttributes, elementAttributes, characterAttributes, cdataAttributes;
    private Pattern             partPattern, namePattern, attributePattern;
    private Matcher             partMatcher, nameMatcher, attributeMatcher;
    private JTextPane           motherComp;

    SyntaxHighlighter(JTextPane mother)
    {
       motherComp = mother;
       initAttributeSets();
       initPatterns();

    }


    private void initAttributeSets()
    {
        tagAttributes = new SimpleAttributeSet();
        StyleConstants.setForeground(tagAttributes, Color.blue);

        cdataAttributes = new SimpleAttributeSet();
        StyleConstants.setFontFamily(cdataAttributes, "Courier");

        characterAttributes = new SimpleAttributeSet();
        StyleConstants.setBold(characterAttributes, true);

        elementAttributes = new SimpleAttributeSet();
        StyleConstants.setForeground(elementAttributes, new Color(153, 0, 0));
    }

    private void initPatterns()
    {

        // REX/Javascript 1.0
        // Robert D. Cameron "REX: XML Shallow Parsing with Regular Expressions",
        // Technical Report TR 1998-17, School of Computing Science, Simon Fraser
        // University, November, 1998.
        // Copyright (c) 1998, Robert D. Cameron.
        // The following code may be freely used and distributed provided that
        // this copyright and citation notice remains intact and that modifications
        // or additions are clearly identified.

        // Angepaﬂt an java.util.regex.
        // Kai Mattern: angepasst an Code generator

        String TextSE = "[^<]+";
        String UntilHyphen = "[^-]*-";
        String Until2Hyphens = UntilHyphen + "([^-]" + UntilHyphen + ")*-";
        String CommentCE = Until2Hyphens + ">?";
        String UntilRSBs = "[^]]*]([^]]+])*]+";
        String CDATA_CE = UntilRSBs + "([^]>]" + UntilRSBs + ")*>";
        String S = "[ \\n\\t\\r]+";
        String NameStrt = "[A-Za-z_:]|[^\\x00-\\x7F]";
        String NameChar = "[A-Za-z0-9_:.-]|[^\\x00-\\x7F]";
        String Name = "(" + NameStrt + ")(" + NameChar + ")*";
        String QuoteSE = "\"[^\"]" + "*" + "\"" + "|'[^']*'";
        String DT_IdentSE = S + Name + "(" + S + "(" + Name + "|" + QuoteSE
                + "))*";
        String MarkupDeclCE = "([^]\"'><]+|" + QuoteSE + ")*>";
        String S1 = "[\\n\\r\\t ]";
        String UntilQMs = "[^?]*\\?+";
        String PI_Tail = "\\?>|" + S1 + UntilQMs + "([^>?]" + UntilQMs + ")*>";
        String DT_ItemSE = "<(!(--" + Until2Hyphens + ">|[^-]" + MarkupDeclCE
                + ")|\\?" + Name + "(" + PI_Tail + "))|%" + Name + ";|" + S;
        String DocTypeCE = DT_IdentSE + "(" + S + ")?(\\[(" + DT_ItemSE
                + ")*](" + S + ")?)?>?";
        String DeclCE = "--(" + CommentCE + ")?|\\[CDATA\\[(" + CDATA_CE
                + ")?|DOCTYPE(" + DocTypeCE + ")?";
        String PI_CE = Name + "(" + PI_Tail + ")?";
        String EndTagCE = Name + "(" + S + ")?>?";
        String AttValSE = "\"[^<\"]" + "*" + "\"" + "|'[^<']*'";
        String ElemTagCE = Name + "(" + S + Name + "(" + S + ")?=(" + S + ")?("
                + AttValSE + "))*(" + S + ")?/?>?";
        String MarkupSPE = "<(!(" + DeclCE + ")?|\\?(" + PI_CE + ")?|/("
                + EndTagCE + ")?|(" + ElemTagCE + ")?)";
        String XML_SPE = TextSE + "|" + MarkupSPE;

        partPattern = Pattern.compile(XML_SPE);
        namePattern = Pattern.compile(Name);
        attributePattern = Pattern.compile(AttValSE);
    }

    public void doSyntaxHighLight(String text)
    {
        String strText = text;
        motherComp.setText(strText);
        nameMatcher = namePattern.matcher(strText);
        partMatcher = partPattern.matcher(strText);
        attributeMatcher = attributePattern.matcher(strText);
        while (partMatcher.find())
        {
            formatPart(partMatcher);
        }
    }



    private void formatPart(Matcher myPartMatcher)
    {
        String part = myPartMatcher.group();
        if (!part.startsWith("<"))
        {
            applyAttributeSet(myPartMatcher, characterAttributes);
        }
        else
        {
            applyAttributeSet(myPartMatcher, tagAttributes);
            if (!part.startsWith("<?") && !part.startsWith("<!"))
            {
                formatElement(myPartMatcher);
            }
            if (part.startsWith("<![CDATA[") && part.endsWith("]]>"))
            {
                applyAttributeSet(myPartMatcher, cdataAttributes, 9, 12);
            }
        }
    }

    /**
     * Formatiert Namen und Attribute in einem XML-Element.
     */

    private void formatElement(Matcher myPartMatcher)
    {
        nameMatcher.find(myPartMatcher.start());
        do
        {
            applyAttributeSet(nameMatcher, elementAttributes);
            if (!nameMatcher.find())
                break;
        } while (nameMatcher.end() < myPartMatcher.end());

        if (!attributeMatcher.find(myPartMatcher.start()))
            return;
        do
        {
            applyAttributeSet(attributeMatcher, characterAttributes, 1, 1);
            if (!attributeMatcher.find())
                break;
        } while (attributeMatcher.end() < myPartMatcher.end());
    }

    
    private void applyAttributeSet(Matcher m, AttributeSet attributeSet)
    {
        applyAttributeSet(m, attributeSet, 0, 0);
    }

    
    private void applyAttributeSet(Matcher m, AttributeSet attributeSet,
            int additionalOffset, int lengthReduction)
    {
        int offset = m.start();
        int length = m.group().length();
        motherComp.getStyledDocument().setCharacterAttributes(
                offset + additionalOffset, length - lengthReduction,
                attributeSet, true);
        
    }
}


