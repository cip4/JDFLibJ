/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2025 The International Cooperation for the Integration of Processes in Prepress, Press and Postpress (CIP4). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This product includes software developed by the The International Cooperation for
 * the Integration of Processes in Prepress, Press and Postpress (www.cip4.org)" Alternately, this acknowledgment may appear in the software itself, if and wherever such third-party acknowledgments
 * normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of Processes in Prepress, Press and Postpress" must not be used to endorse or promote products derived from this software
 * without prior written permission. For written permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4", nor may "CIP4" appear in their name, without prior written permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE. ====================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the The International Cooperation for the Integration of Processes in Prepress, Press and Postpress and was
 * originally based on software copyright (c) 1999-2001, Heidelberger Druckmaschinen AG copyright (c) 1999-2001, Agfa-Gevaert N.V.
 *
 * For more information on The International Cooperation for the Integration of Processes in Prepress, Press and Postpress , please see <http://www.cip4.org/>.
 *
 *
 */
/**
 *
 * Copyright (c) 2001 Heidelberger Druckmaschinen AG, All Rights Reserved.
 *
 * KString.java
 *
 * Last changes
 *
 */
package org.cip4.jdflib.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.enums.ValuedEnum;
import org.apache.commons.lang3.StringUtils;
import org.cip4.jdflib.core.JDFConstants;
import org.cip4.jdflib.core.JDFCoreConstants;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.core.StringArray;
import org.cip4.jdflib.core.VString;
import org.cip4.jdflib.datatypes.JDFBaseDataTypes;
import org.cip4.jdflib.datatypes.JDFNumberList;
import org.cip4.jdflib.ifaces.IStreamWriter;

/**
 * collection of static string utilities
 *
 * @author prosirai
 *
 */
public class StringUtil
{
	enum EDataType
	{
		string, integer, number, bool, numberlist, date
	}

	private static final NumberFormatter formatter = new NumberFormatter();
	/**
	 *
	 */
	public static final String UTF8 = StandardCharsets.UTF_8.name();

	private static final String m_sep = JDFConstants.BLANK;

	/**
	 * @deprecated use {@link UrlUtil}.m_URIEscape
	 */
	@Deprecated
	public static final String m_URIEscape = UrlUtil.m_URIEscape;

	private StringUtil()
	{
		// hide constructor to avoid accidental instantiation
	}

	/**
	 * returns a random string <br/>
	 * really important routine - written on a friday afternoon ;-) <br/>
	 * please add more at your leisure.... <br/>
	 * parts (c) Monty Python, Star Trek, Douglas Adams, Black Adder, Goethe, Firefly, Capt. Jack Sparrow
	 *
	 */
	private static String[] strings = { "Randomly inserted error", "Me. I'm dishonest! And a dishonest man you can always trust to be dishonest... honestly!", "fooBar", "Snafu",
			"The problem is not the problem. The problem is your attitude about the problem.", "If you were waiting for the opportune moment, that was it.",
			"Soon TM is a registered trademark of CIP4 and does not imply, either implicitly or explicitly, any action will take place in any given time, if ever.",
			"Programming today is a race between software engineers striving to build bigger and better programs, and the Universe trying to produce bigger and better idiots. So far, the Universe is winning.",
			"Nichtsnutz geht über Eigennutz!", "Dumb people are always blissfully unaware of how dumb they really are…",
			"Well, it’s no secret that the best thing about a secret is secretly telling someone your secret, thereby adding another secret to their secret collection of secrets, secretly.",
			"Bad Karma", "This is either madness, or brilliance. It’s remarkable how often those two traits coincide.",
			"Ihr Instrumente freilich spottet mein, Mit Rad und Kämmen, Walz und Bügel: Ich stand am Tor, ihr solltet Schlüssel sein; Zwar euer Bart ist kraus, doch hebt ihr nicht die Riegel.",
			"What do you expect from a simulator", "Paper Jam", "Strawberry jam", "Elderberry jam", "Your mother was a hamster and your father smelt of elderberries!",
			"I'm French! Why do think I have this outrageous accent, you silly king-a?!", "It's just a flesh wound.", "All right, we'll call it a draw.",
			"Oh, oh, I see! Running away, eh? You yellow bastards! Come back here and take what's coming to you! I'll bite your legs off!",
			"You don't frighten us, English pig-dogs!", "Go and boil your bottom, sons of a silly person.",
			"I blow my nose at you, so-called Arthur King, you and all your silly English k-nnnnniggets. Thpppppt! Thppt! Thppt!",
			"I don't wanna talk to you no more, you empty headed animal food trough wiper!", "I fart in your general direction!",
			"Listen, strange women lyin' in ponds distributin' swords is no basis for a system of government. Supreme executive power derives from a mandate from the masses, not from some farcical aquatic ceremony",
			"C'est un lapin, lapin de bois.", "Quoi? Un cadeau. What? A present. Oh, un cadeau. Oui, oui. Hurry. What? Let's go. Oh. On y va. Bon magne. Over here...",
			"Oh. Oh, I see. Running away, eh? You yellow bastards! Come back here and take what's coming to you. I'll bite your legs off!", "You're using coconuts!",
			"The swallow may fly south with the sun or the house martin or the plover may seek warmer climes in winter, yet these are not strangers to our land?",
			"Are you suggesting coconuts migrate?", "Assumption is the mother of all fuckups",
			"It's not a question of where he grips it! It's a simple question of weight ratios! A five ounce bird could not carry a one pound coconut.",
			"Listen. In order to maintain air-speed velocity, a swallow needs to beat its wings forty-three times every second, right?",
			"Oh, King, eh, very nice. And how d'you get that, eh? By exploiting the workers! By 'anging on to outdated imperialist dogma which perpetuates the economic and social differences in our society. If there's ever going to be any progress with the--",
			"Oh! Come and see the violence inherent in the system! Help! Help! I'm being repressed!",
			"Here I am, brain the size of a planet, and they ask me to take you to the bridge. Call that job satisfaction, 'cause I don't.",
			"Ghastly, isn't it? All the doors on this spaceship have been programmed to have a cheery and sunny disposition.", "whazzap?",
			"Explain again how sheep's bladders may be employed to prevent earthquakes.",
			"Pardon me for breathing, which I never do anyway so I don't know why I even bothered to say it. Oh god, I'm so depressed.",
			"I've got this pain in all the diodes down my left side",
			"I would like to say that it is a very great pleasure, honour and privilege for me to open this bridge, but I can't because my lying circuits are all out of commission",
			"Do you want me to sit in the corner and rust, or just fall apart where I'm standing?",
			"You may think you've read Hamlet before, but you can't really appreciate it until you've read it in the original Klingon.",
			"What's this, then? \"Romanes eunt domus\"? People called Romanes, they go, the house?",
			"Life's a piece of shit when you look at it. Life's a laugh and death's a joke; it's true.",
			"Excuse me. Are you the Judean People's Front? Fuck off! We're the People's Front of Judea",
			"How many Romans? - Aaaaagh ! Plural, plural, er, \"ite\" ! - \"Domus\"? Nominative? \"Go home\" is motion towards, isn't it? - Dative ! -  Aaagh ! Not the dative, not the dative ! Er, er, accusative, \"Domum\" ! - But \"Domus\" takes the locative, which is...? Er, \"Domum\" ! ",
			"Making it worse? How can it be worse? Jehovah! Jehovah! Jehovah! ",
			"No, I'm only joking. I'm not really Brian. No, I'm not Brian. I was only - It was a joke. I'm only pulling your leg! It's a joke! I'm not him! I'm just having you on! Put me back! Bloody Romans! Can't take a joke! ",
			"Er, well, um, if you're dropping by again, do pop in. Heh. And thanks a lot for the gold and frankincense, er, but don't worry too much about the myrrh next time. All right? Heh. Thank you. Good-bye.",
			"Under the terms of the Roman occupancy, we're entitled to be crucified in a purely Jewish area.",
			"Amongst our weaponry are such diverse elements as: fear, surprise, ruthless efficiency, an almost fanatical devotion to the Pope, and nice red uniforms - Oh damn!",
			"*Nobody* expects the Spanish Inquisition! Our chief weapon is surprise, surprise and fear, fear and surprise. Our *two* weapons are fear and surprise, and ruthless efficiency. Our *three* weapons are fear and surprise and ruthless efficiency and an almost fanatical dedication to the pope. Our *four*... No... Amongst our weapons... Amongst our weaponry are such elements as fear, sur- I'll come in again. ",
			"NOBODY expects the Spanish Inquisition!", "Hm! She is made of harder stuff! Cardinal Fang! Fetch...THE COMFY CHAIR!",
			"So you think you are strong because you can survive the soft cushions. Well, we shall see. Biggles! Put her in the Comfy Chair!",
			"Tungsten carbide drills? What the bloody hell's tungsten carbide drills? - It's something they use in coal mining, father.' Your bloody fancy talk since you left London.",
			"I'll tell you what's wrong with it, my lad. It's dead, that's what's wrong with it!",
			"All right then, if it's restin', I'll wake him up! (shouts into cage) Hello Polly! I've got a nice cuttlefish for you when you wake up, Polly Parrot!",
			"Look, I took the liberty of examining that parrot, and I discovered the only reason that it had been sitting on its perch in the first place was that it had been nailed there.",
			"Look matey (picks up the parrot) this parrot wouldn't voom if you put four thousand volts through it! It's bleedin' demised!",
			"It's not pining, it's passed on. This parrot is no more! It has ceased to be. It's expired and gone to meet its maker.This is a late parrot. It's a stiff. Bereft of life, it rests in peace. If you hadn't nailed it to the perch it would be pushing up the daisies. It's rung down the curtain and joined the choir invisible. This is an ex-parrot.",
			"Pining for the fjords, what kind of talk is that? Look, why did it fall flat on its back the moment I got it home?",
			"Whatever it was, I'm sure it was better than my plan to get out of this by pretending to be mad. I mean, who would have noticed another madman round here?",
			"C: Big, blue wobbly thing that mermaids live in.",
			"Baldrick, you wouldn't recognize a subtle plan if it painted itself purple and danced naked on a harpsicord singing 'subtle plans are here again'.",
			"To you it's a potato, to me it's a potato. But to Sir Walter Bloody Raleigh it's country estates, fine carriages, and as many girls as his tongue can cope with. He's making a fortune out of the things; people are smoking them, building houses out of them... They'll be eating them next.",
			"E: Look, there's no need to panic. Someone in the crew will know how to steer this thing.\nR: The crew, milord?\nE: Yes, the crew.\nR: What crew?\nE: I was under the impression that it was common maritime practice for a ship to have a crew.\nR: Opinion is divided on the subject.\n",
			"If we do happen to step on a mine, Sir, what do we do ?\nNormal procedure, Lieutenant, is to jump 200 feet in the air and scatter oneself over a wide area.",
			"Gentlemen, you can't fight in here, this is the War Room!", "I love it when a plan comes together", "Das also war des Pudels Kern!",
			"I feel that if a person can't communicate the very least he can do is to shut up",
			"Always carry a flagon of whiskey in case of snakebite and furthermore always carry a small snake.", "Anyone who hates children and animals can't be all bad.",
			"Ein Teil von jener Kraft, Die stets das Böse will und stets das Gute schafft.", "Von allen Geistern, die verneinen, ist mir der Schalk am wenigsten zur Last.",
			"And the Lord spake, saying, 'First shalt thou take out the Holy Pin. Then, shalt thou count to three, no more, no less. Three shalt be the number thou shalt count, and the number of the counting shall be three. Four shalt thou not count, nor either count thou two, excepting that thou then proceed to three. Five is right out. Once the number three, being the third number, be reached, then lobbest thou thy Holy Hand Grenade of Antioch towards thy foe, who, being naughty in my sight, shall snuff it.",
			"The slightest thought hadn't even begun to speculate about the merest possibility of crossing my mind.",
			"Right, Baldrick, let's try again, shall we? This is called adding. If I have two beans, and then I add two more beans, what do I have?\nSome beans.\nYes...and no. Let's try again, shall we? I have two beans, then I add two more beans. What does that make?\nA very small casserole.\nBaldrick, the ape creatures of the Indus have mastered this. Now try again. One, two, three, four. So how many are there?\nThree\nWhat?\nAnd that one.\nThree and that one. So if I add that one to the three what will I have?\nOh! Some beans.\nYes. To you Baldrick, the Renaissance was just something that happened to other people wasn't it?",
			"It's got a cop motor, a 440 cubic inch plant, it's got cop tires, cop suspensions, cop shocks. It's a model made before catalytic converters so it'll run good on regular gas. What do you say, is it the new Bluesmobile or what?",
			"Yes. The plans were on display in the bottom of a locked filing cabinet, stuck in a disused lavatory with a sign on the door saying 'Beware of the Leopard'",
			"It's 106 miles to Chicago, we got a full tank of gas, half a pack of cigarettes, it's dark, and we're wearing sunglasses. ",
			"Ich bin der Geist, der stets verneint! Und das mit Recht; denn alles, was entsteht,  Ist wert, daß es zugrunde geht;  Drum besser wär's, daß nichts entstünde.  So ist denn alles, was ihr Sünde,  Zerstörung, kurz das Böse nennt,  Mein eigentliches Element.",
			"Als Gregor Samsa eines Morgens aus unruhigen Träumen erwachte, fand er sich in seinem Bett zu einem ungeheueren Ungeziefer verwandelt.",
			"Du hast wohl recht; ich finde nicht die Spur\nvon einem Geist, und alles ist Dressur.",
			"'I got very bored and depressed, so I went and plugged myself in to its external computer feed. I talked to the computer at great length and explained my view of the Universe to it', said Marvin. 'And what happened?' pressed Ford. 'It committed suicide.'",
			"Wie nur dem Kopf nicht alle Hoffnung schwindet,\nDer immerfort an schalem Zeuge klebt,\nMit gier'ger Hand nach Schätzen gräbt,\nUnd froh ist, wenn er Regenwürmer findet!",
			"Der Dadaist ist ein Künstler, bei dem das Produkt als solches nebensächlich ist. Er ist ein Geistesreisender, der immer neue Erfahrungen macht. Ein Zeichen dafür, dass Dadaismus doch produktiv im herkömmlichen Sinne der bildenden Kunst ist, zeigt der groteske Humor, der sich in den Gedichten und Bildern wieder findet. Auch die Ironie, die die Künstler in ihre Werke einfließen lassen, spricht für modale Präsenz von „Sinn im Unsinn“, der immer dort ist, wo er nicht zu sein scheint.",
			"Half a bee, philosophically, must, ipso facto, half not be. But half the bee has got to be, vis a vis its entity - do you see? But can a bee be said to be or not to be an entire bee when half the bee is not a bee due to some ancient injury?",
			"Mein schönes Fräulein, darf ich wagen, Meinen Arm und Geleit Ihr anzutragen? Bin weder Fräulein, weder schön, Kann ungeleitet nach Hause gehn.",
			"And I'm thinking you weren't burdened with an overabundance of schooling. So why don't we just ignore each other 'til we go away.",
			"Im übrigen will ich keines Menschen Urteil, ich will nur Kenntnisse verbreiten, ich berichte nur, auch Ihnen, hohe Herren von der Akademie, habe ich nur berichtet.",
			"I'm afraid not. The guns have stopped because we are about to attack. Not even our generals are mad enough to shell their own men. They feel it's more sporting to let the Germans do it.",
			"If they take the ship they will rape us to death, eat our flesh, and sew our skins in to their clothing, and if we're very, very lucky, they’ll do it in that order.",
			"Hier konnte niemand sonst Einlaß erhalten, denn dieser Eingang war nur für dich bestimmt. Ich gehe jetzt und schließe ihn.",
			"Hohe Herren von der Akademie! Sie erweisen mir die Ehre, mich aufzufordern, der Akademie einen Bericht über mein äffisches Vorleben einzureichen.",
			"Not only is this incomprehensible, but the ink is ugly and the paper is from the wrong kind of tree.",
			"If the code and the comments disagree, then both are probably wrong.", "A radioactive cat has eighteen half-lives.", "Silence! I kill you!",
			"Although golf was originally restricted to wealthy, overweight Protestants, today it's open to anybody who owns hideous clothing.",
			"Base eight is just like base ten really - if you're missing two fingers", "Biology is the only science in which multiplication means the same thing as division.",
			"My dog's not eating, he no longer barks; He hit the propeller and turned into sparks",
			"One leg is missing; the other is gone; The third leg is scattered all over the lawn; No need explaining the one leg remaining is spinning on the carport floor",
			"\"Once the rockets are up, who cares where they come down? That's not my department,\" says Wernher von Braun.",
			"Knock, knock … Who’s there? … *very long pause* … Java.", "Programming made the impossible possible. You can have a null object and a constant variable.",
			"Voodoo Programming", "It’s not a bug — it’s an undocumented feature.", "Linux is only free if your time has no value.",
			"A system administrator has two problems: 1. Dumb users. 2. Smart users.", "When we had no computers, we had no programming problems either.",
			"I’ve reached that age where my brain goes from 'You probably shouldn't say that' to 'What the hell, let's see what happens.'",
			"An apple a day keeps anyone away if you throw it hard enough.", "When someone says 'Expect the unexpected', slap them and say 'You didn't expect that, did you?'",
			"Remember if you can't say something nice…make it funny.", "Be careful when you follow the masses. Sometimes the M is silent.",
			"When in doubt, remember FISH: F-ck it, sh-t happens.",
			"Theorien wie Stringtheorien und Paralleluniversen sind mindestens genauso gewagt wie die Vorstellung, dass hier pausenlos irgendwelche Außerirdischen landen.",
			"Wenn alle nur lauschen, wird es logischerweise zu keiner interplanetaren Kommunikation kommen.",
			"Wir können das Universum nicht erklären, sondern nur beschreiben; und wir wissen nicht, ob unsere Theorien wahr sind, wir wissen nur, dass sie nicht falsch sind.",
			"The good thing about science is that it's true whether or not you believe in it.",
			"We are all connected; To each other, biologically. To the earth, chemically. To the rest of the universe atomically.",
			"Curious that we spend more time congratulating people who have succeeded than encouraging people who have not.", "Last quote" };

	/**
	 * returns a random string for testing fun stuff - similar to unix fortune but biased towards monty python or the hitchhikers guide to the galaxy
	 *
	 * @return a random string
	 */
	public static String getRandomString()
	{
		final int pos = (int) (strings.length * Math.random() * 0.99999);
		return strings[pos];
	}

	/**
	 * Returns a string with deleted whitespaces near 'delim' and from the both ends of the string (if they were there)<br>
	 *
	 * tokenizes a given string 'str' into tokens without separators. Trims every token from both sides to remove the whitespaces and builds a new string from these tokens
	 * separated by 'delim'.
	 *
	 * @param str working string
	 * @param delim the delimiter
	 *
	 * @return String - the modified string
	 */
	public static String zappTokenWS(final String str, final String delim)
	{
		final StringBuilder s = new StringBuilder();

		final StringArray vs = new StringArray(str, delim);
		final int size = vs.size();

		if (size > 0)
		{
			s.append(vs.get(0).trim());

			for (int i = 1; i < size; i++)
			{
				s.append(delim);
				s.append(vs.get(i).trim());
			}
		}
		return intern(s.toString());
	}

	/**
	 * write to a file
	 *
	 * @param file the file to write
	 * @param w the writer to write to
	 *
	 * @return the file that was created, null if snafu
	 */
	public static String write2String(final IStreamWriter w)
	{
		final ByteArrayIOStream os = new ByteArrayIOStream();
		final OutputStream ok = StreamUtil.write2Stream(w, os);
		return ok == null ? null : new String(os.toByteArray(), StandardCharsets.UTF_8);
	}

	/**
	 * format a string using C++ sprintf functionality
	 *
	 * @param format the format to print, see C++ spec for details
	 * @param template - comma separated string - the values are parsed and the appropriate objects are created more objects exist in template than the number of '%' tokens in
	 *        format, the remainder of objects is ignored duplicate '\\,' is taken as literal ','
	 * @return String the formatted string
	 * @throws IllegalArgumentException in case format and o do not match, i.e. not enough objects are passed to fill format
	 */
	public static String sprintf(final String format, String template)
	{
		if (isEmpty(template) || isEmpty(format))
		{
			return null;
		}
		template = StringUtil.replaceString(template, "\\,", "__comma__äö-eher selten"); // quick hack ;-)

		final VString vTemplate = tokenize(template, ",", false);
		return sprintf(format, vTemplate);
	}

	public static String sprintf(final String format, final List<String> vTemplate)
	{
		final Object[] vObj = new Object[ContainerUtil.size(vTemplate)];
		for (int i = 0; i < vObj.length; i++)
		{
			final String s = vTemplate.get(i);
			if (isInteger(s))
			{
				vObj[i] = Integer.valueOf(parseInt(s, 0));
			}
			else if (isNumber(s))
			{
				vObj[i] = Double.valueOf(parseDouble(s, 0));
			}
			else
			{
				vObj[i] = StringUtil.replaceString(s, "__comma__äö-eher selten", ","); // undo quick hack ;-)
			}
		}
		return sprintf(format, vObj);
	}

	/**
	 * format a string using C++ sprintf functionality
	 *
	 * @param format the format to print, see C++ spec for details
	 * @param objects the array of objects, either String, Double, Integer or ValuedEnum, if objects is longer than the number of '%' tokens in format, the remainder of objects is
	 *        ignored The method works fairly loosely typed, thus doubles are printed as integers, Strings are converted to numbers, if possible etc.
	 *
	 * @return String the formatted string
	 * @throws IllegalArgumentException in case format and o do not match, i.e. not eough objects are passed to fill format
	 * 
	 */
	public static String sprintf(final String format, final Object[] objects)
	{
		if (objects == null || isEmpty(format))
		{
			return null;
		}

		return intern(String.format(format, objects));
	}

	/**
	 * create a string from an array of tokens
	 *
	 * @param a the token array
	 * @param sep the separator between the tokens
	 * @param front the front end of the string
	 * @param back the back end of the string
	 * @return String - the vector as String
	 *
	 *         default: setvString(v, JDFConstants.BLANK, null, null)
	 */
	public static String setvString(final String[] a, final String sep, final String front, final String back)
	{
		if (a == null)
		{
			return null;
		}
		final VString v = new VString(a);
		return setvString(v, sep, front, back);
	}

	/**
	 * create a string from a vector of tokens
	 * <p>
	 * default: setvString(v, JDFConstants.BLANK, null, null)
	 *
	 * @param v the token vector
	 *
	 * @return String - the vector as String
	 */
	public static String setvString(final List<?> v)
	{
		return setvString(v, m_sep, null, null);
	}

	/**
	 * create a string from a vector of tokens
	 *
	 * @param v the token vector
	 * @param sep the separator between the tokens
	 * @param front the front end of the string
	 * @param back the back end of the string
	 *
	 * @return String - the vector as String
	 *
	 *         default: setvString(v, JDFConstants.BLANK, null, null)
	 */
	public static String setvString(final List<?> v, final String sep, final String front, final String back)
	{
		if (v == null)
		{
			return null;
		}

		final int siz = v.size();
		final StringBuilder buf = new StringBuilder(siz * 16); // guestimat 16 chars
		// per token max

		if (front != null)
		{
			buf.append(front);
		}

		boolean next = false;
		for (int i = 0; i < siz; i++)
		{
			if (next && sep != null)
			{
				buf.append(sep);
			}
			final Object elementAt = v.get(i);
			if (elementAt instanceof String)
			{
				buf.append((String) elementAt);
				next = true;
			}
			else if (elementAt instanceof ValuedEnum)
			{
				buf.append(((ValuedEnum) elementAt).getName());
				next = true;
			}
			else if (elementAt != null)
			{
				throw new IllegalArgumentException("illegal vector contents");
			}

		}

		if (back != null)
		{
			buf.append(back);
		}

		final String ret = buf.toString();
		return getNonEmpty(ret);
	}

	/**
	 * n > 0 substring(0, n) take the first n chars (leftmost) n < 0 substring(0, s.length()+n) take the string and cut n chars on the right example: string = "abcdefgh"
	 * string.leftStr( 2) = "ab" string.leftStr(-3) = "abcde"
	 *
	 * @param strWork the string to work on
	 * @param n number of characters to cut (negative) or retain (positive)
	 * @return the modified string
	 */
	public static String leftStr(final String strWork, int n)
	{
		if (strWork == null)
		{
			return null;
		}

		if (n < 0)
		{
			n = strWork.length() + n;
		}

		if (n <= 0)
		{
			return null;
		}

		return intern(strWork.substring(0, n <= strWork.length() ? n : strWork.length()));
	}

	/**
	 * similar to substring but also null safe and allowing negative numbers to count backwards
	 *
	 * @param strWork the string to work on
	 * @param first the position of the starting character 0=first
	 * @param last the position of the end character 0=last
	 * @return the modified string
	 */
	public static String substring(final String strWork, int first, int last)
	{
		if (strWork == null)
		{
			return null;
		}

		if (first < 0)
		{
			first = strWork.length() + first;
		}
		if (first < 0)
		{
			first = 0;
		}
		if (first > strWork.length())
		{
			return null;
		}
		if (last <= 0)
		{
			last = strWork.length() + last;
		}
		if (last < first)
		{
			return null;
		}
		if (last >= strWork.length())
		{
			last = strWork.length();
		}

		return intern(strWork.substring(first, last));
	}

	/**
	 * get the end of a string n > 0 str.substring(str.length() - n) take the rightmost n chars n < 0 substring(-n) take the string and cut n chars on the left example: string =
	 * "abcdefgh" string.rightStr( 2) = "gh" string.rightStr(-3) = "defgh"
	 *
	 * @param strWork the string to work on
	 * @param n number of characters to cut (negative) or retain (positive)
	 * @return the modified string
	 */
	public static String rightStr(final String strWork, int n)
	{
		if (strWork == null)
		{
			return null;
		}

		if (n < 0)
		{
			n = strWork.length() + n;
		}

		if (n <= 0)
		{
			return null;
		}

		if (n > strWork.length())
		{
			return strWork;
		}

		return intern(strWork.substring(strWork.length() - n));
	}

	/**
	 * return a vector of individual tokens<br>
	 * Multiple consecutive delimitors are treated as one (similar to whitespace handling).
	 * <p>
	 * default: tokenize(strWork, delim, false)
	 *
	 * @param strWork the string to tokenize
	 * @param delim the delimiter, if null use whitespace
	 * @param delim2token should a delimiter be a token?
	 * @return the vector of strings - never null
	 */
	public static VString tokenize(final String strWork, String delim, final boolean delim2token)
	{
		if (delim2token)
		{
			delim = delim == null ? JDFConstants.BLANK : delim;
			final VString v = new VString();
			final StringTokenizer st = new StringTokenizer(strWork, delim, delim2token);
			boolean lastToken = false;
			while (st.hasMoreTokens())
			{
				String nextToken = st.nextToken();
				if (delim.contains(nextToken))
				{
					if (lastToken)
					{
						nextToken = v.get(-1) + nextToken;
						v.set(v.size() - 1, nextToken);
						continue;
					}
					lastToken = true;
				}
				else
				{
					lastToken = false;
				}
				v.add(intern(nextToken));
			}
			return v;
		}
		else
		{
			return new VString(strWork, delim);
		}
	}

	/**
	 *
	 * constructs a VString by tokenizing a string
	 *
	 * @param strIn the string to tokenize
	 * @param strSep the list of separator characters - null if whitespace
	 */
	public static List<String> tokenize(final List<String> l, final String strIn, String strSep)
	{
		if (!StringUtil.isEmpty(strIn))
		{
			String keep = JDFConstants.EMPTYSTRING;
			if (isEmpty(strSep))
			{
				for (final int s : JDFCoreConstants.WHITESPACE.getBytes())
				{
					if (strIn.indexOf(s) >= 0)
					{
						keep += (char) s;
					}
				}
				strSep = keep;
			}
			else if (strSep.length() == 1 && strIn.indexOf(strSep.charAt(0)) < 0)
			{
				strSep = JDFConstants.EMPTYSTRING;
			}
			if (strSep.isEmpty())
			{
				l.add(intern(strIn));
			}
			else
			{
				final StringTokenizer sToken = new StringTokenizer(strIn, strSep);
				while (sToken.hasMoreTokens())
				{
					l.add(intern(sToken.nextToken()));
				}
			}
		}
		return l;
	}

	/**
	 * tokenize while counting the referenced in and out so that internal tokens are skipped used e.g for "?:" of "()"
	 *
	 * @param strWork
	 * @param delimIn the
	 * @param delimOut
	 * @return
	 */
	public static VString tokenizeBrackets(final String strWork, final char delimIn, final char delimOut)
	{
		if (strWork == null)
			return null;

		final VString v = new VString();
		int depth = 0;
		int pos0 = 0;
		for (int i = 0; i < strWork.length(); i++)
		{
			final char c = strWork.charAt(i);
			if (c == delimIn)
			{
				if (v.isEmpty())
				{
					v.add(strWork.substring(0, i));
					pos0 = i + 1;
				}
				else
				{
					depth++;
				}
			}
			else if (c == delimOut && depth-- == 0)
			{
				if (v.isEmpty())
				{
					v.add(strWork.substring(0, i));
					v.add("");
				}
				else
				{
					v.add(strWork.substring(pos0, i));
				}
				pos0 = i + 1;
				break;
			}
		}
		if (pos0 <= strWork.length())
		{
			v.add(strWork.substring(pos0));
		}

		return v;
	}

	/**
	 * check whether a String contains a given token
	 * <p>
	 * default: hasToken(strWork, token, delim, 0)
	 *
	 * @param strWork the string to work on
	 * @param token the token to search for
	 * @param delim the delimiter of the tokens
	 * @param iSkip the number of matching tokens to skip before returning true
	 * @return boolean - true if <code>strWork</code> contains <code>token</code>
	 */
	public static boolean hasToken(final String strWork, final String token, String delim, final int iSkip)
	{
		if (isEmpty(strWork))
			return false;
		if (delim == null)
			delim = JDFConstants.SPACE;
		if (iSkip == 0 || iSkip == -1)
			return indexOfToken(strWork, token, delim, 0) >= 0;
		if (iSkip < -1)
		{
			final StringArray a = StringArray.getVString(strWork, null);
			final String[] as = a.toArray(new String[] {});
			return hasToken(as, token, iSkip);
		}
		else
		{
			int posToken1 = strWork.indexOf(token);
			if (posToken1 < 0)
			{
				return false;
			}
			if (posToken1 > 0)
			{
				posToken1--; // go back one in case the char before the first token was not the deliminator
			}
			final String substring = strWork.substring(posToken1);
			final StringTokenizer st = new StringTokenizer(substring, delim, false);
			int n = 0;
			while (st.hasMoreTokens())
			{
				if (st.nextToken().equals(token))
				{
					if (n++ >= iSkip)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * check whether a vector of Strings contains a given token
	 * <p>
	 * default: hasToken(strWork, token, 0)
	 *
	 * @param strWork the vector of strings string to work on
	 * @param token the token to search for
	 * @param iSkip the number of matching tokens to skip before returning true
	 * @return true, if <code>strWork</code> contains <code>token</code>
	 */
	public static boolean hasToken(final String strWork[], final String token, final int iSkip)
	{
		if (strWork != null)
		{
			int n = 0;
			if (iSkip < 0)
			{
				for (int i = strWork.length - 1; i >= 0; i--)
				{
					if (token.equals(strWork[i]) && (--n <= iSkip))
					{
						return true;
					}
				}
			}
			else
			{
				for (final String element : strWork)
				{
					if (token.equals(element) && (n++ >= iSkip))
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * replace a token in a string
	 *
	 * @param strWork the String to work on
	 * @param delim the delimiter
	 * @param newToken the new token, if null said token is removed
	 * @return the modified string
	 */
	public static String addToken(String strWork, final String delim, String newToken)
	{
		if (isEmpty(strWork))
			return newToken;
		if (isEmpty(newToken))
			return strWork;
		while (strWork != null && strWork.endsWith(delim))
			strWork = leftStr(strWork, -delim.length());
		while (newToken != null && newToken.startsWith(delim))
			newToken = rightStr(newToken, -delim.length());
		return ((strWork == null) ? JDFConstants.EMPTYSTRING : strWork) + delim + newToken;
	}

	/**
	 * replace a token in a string
	 *
	 * @param strWork the String to work on
	 * @param index index of the token to replace if<0 return from end (e.g. -1 is the last token)
	 * @param delim the delimiter
	 *
	 * @return the modified string, null if last token was removed
	 */
	public static String removeToken(final String strWork, final int index, final String delim)
	{
		return getNonEmpty(replaceToken(strWork, index, delim, null));
	}

	/**
	 *
	 * @param strWork
	 * @param oldToken
	 * @param delim
	 * @param newToken
	 * @return
	 */
	public static String replaceToken(final String strWork, final String oldToken, final String delim, final String newToken)
	{
		final int pos = posOfToken(strWork, oldToken, delim, 0);
		return (pos >= 0) ? replaceToken(strWork, pos, delim, newToken) : strWork;
	}

	/**
	 *
	 * @param strWork
	 * @param oldToken
	 * @param delim
	 * @param newToken
	 * @return
	 */
	public static String removeToken(final String strWork, final String oldToken, final String delim)
	{
		return replaceToken(strWork, oldToken, delim, null);
	}

	/**
	 * replace a token in a string
	 *
	 * @param strWork the String to work on
	 * @param index index of the token to replace if<0 return from end (e.g. -1 is the last token)
	 * @param delim the delimiter
	 * @param newToken the new token, if null said token is removed
	 * @return the modified string - never null
	 */
	public static String replaceToken(final String strWork, int index, String delim, final String newToken)
	{
		if (delim == null)
			delim = JDFConstants.BLANK;
		final VString v = tokenize(strWork, delim, false);
		if (index < 0)
			index += v.size();
		if (index >= v.size() || index < 0)
			return strWork;
		final VString v2 = tokenize(strWork, delim, true);
		int n = 0;
		int i = 0;
		for (final String token : v2)
		{
			if (!delim.contains(token.substring(0, 1)))
			{
				if (n == index)
					break;
				n++;
			}
			i++;
		}
		if (newToken == null)
		{
			v2.remove(i);
			if (i > 0 && delim.equals(v2.get(i - 1)))
				v2.remove(i - 1);
			else if (i < v2.size() && delim.equals(v2.get(i)))
				v2.remove(i);
		}
		else
			v2.set(i, newToken);
		return setvString(v2, null, null, null);

	}

	/**
	 * get a single token from a String
	 * <p/>
	 * default: Token(strWork, index," \t\n")
	 *
	 * @param strWork the String to work on
	 * @param index index of the token to return<br>
	 *        if<0 return from end (e.g. -1 is the last token)
	 * @param delim the delimiter
	 * @return the single token (<code>null</code> if no token found)
	 */
	public static String token(final String strWork, int index, String delim)
	{
		if (getNonEmpty(strWork) == null)
		{
			return null; // null bleibt null
		}

		if (delim == null)
		{
			delim = JDFConstants.BLANK;
		}

		final int pos = delim.length() == 1 ? strWork.indexOf(delim) : 0;
		if (pos < 0) // speed up incase we only have one entry
		{
			return (index == -1 || index == 0) ? strWork : null;
		}

		if (index < 0)
		{
			final VString v = StringUtil.tokenize(strWork, delim, false);
			index = v.size() + index;
			if (index < 0)
			{
				return null;
			}

			if (index < v.size())
			{
				return intern(v.get(index));
			}

			return null;
		}

		// index >0 don't need to calculate # of tokens
		final StringTokenizer st = new StringTokenizer(strWork, delim, false);
		int n = 0;
		String s = null;
		while (st.hasMoreTokens())
		{
			s = st.nextToken();
			if (n++ == index)
			{
				return intern(s);
			}
		}
		return null;
	}

	/**
	 * replace any of a set of characters in a given String
	 * <p>
	 * default: replaceCharSet(strWork, c, s, 0)
	 *
	 * @param strWork String to work on
	 * @param charSet characters to replace
	 * @param replaceString String to insert for any character in charSet, if null simply remove all occurrences of any char in charSet
	 * @param offset where to start replacing
	 * @return the String with replaced characters
	 */
	public static String replaceCharSet(String strWork, final String charSet, final String replaceString, final int offset)
	{
		if (charSet != null)
		{
			for (int i = 0; i < charSet.length(); i++)
			{
				strWork = replaceChar(strWork, charSet.charAt(i), replaceString, offset);
			}
		}
		return intern(strWork);
	}

	/**
	 * replace multiple occurrences of a character in a given String
	 * <p>
	 * default: replaceChar(strWork, c, s, 0)
	 *
	 * @param strWork String to work on
	 * @param c character to replace
	 * @param replaceString String to insert for c, if null simply remove c
	 * @param offset
	 * @return the String with replaced characters
	 */
	public static String replaceChar(final String strWork, final char c, final String replaceString, final int offset)
	{
		if (strWork == null)
		{
			return null;
		}
		if (offset > strWork.length() || strWork.indexOf(c, offset) < 0)
		{
			return strWork;
		}

		final StringBuilder b = new StringBuilder(strWork.length() * 2);
		int lastPos = offset;
		b.append(strWork.substring(0, offset));
		while (lastPos >= 0)
		{
			final int pos = strWork.indexOf(c, lastPos);
			if (pos >= 0)
			{
				b.append(strWork.substring(lastPos, pos));
				if (replaceString != null)
				{
					b.append(replaceString);
				}
			}
			else
			{
				b.append(strWork.substring(lastPos));
			}
			lastPos = pos >= 0 ? pos + 1 : pos;
		}

		return intern(b.toString());
	}

	/**
	 * replace a string in a given String if the replacement string is contained by the string to replace, recursively replace until no ocurrences of the original remain thus
	 * replaceString("a000000", "00", "0") will return "a0" rather than "a000"
	 *
	 * @param strWork String to work on
	 * @param toReplace String to match and replace
	 * @param replaceBy String to insert for toReplace, null if nothing should be inserted
	 * @return the String with replaced characters
	 */
	public static String replaceString(final String strWork, final String toReplace, final String replaceBy)
	{
		return new StringReplacer(strWork).replaceString(toReplace, replaceBy);
	}

	/**
	 * class that provides additional functionality for replacing string
	 *
	 * @author rainer prosi
	 * @date Apr 3, 2011
	 */
	public static class StringReplacer
	{
		private String strWork;
		private boolean reRead;

		/**
		 *
		 * set the reread algorithm - if true (the default) the replaced string is checked again. If false the algorithm continues after the replacement
		 *
		 * @param reRead
		 */
		public void setReRead(final boolean reRead)
		{
			this.reRead = reRead;
		}

		/**
		 *
		 * @param str
		 */
		public StringReplacer(final String str)
		{
			super();
			this.strWork = str;
			reRead = true;
		}

		/**
		 * replace a string in a given String if the replacement string is contained by the string to replace, recursively replace until no ocurrences of the original remain thus
		 * replaceString("a000000", "00", "0") will return "a0" rather than "a000"
		 *
		 * @param toReplace String to match and replace
		 * @param replaceBy String to insert for toReplace, null if nothing should be inserted
		 * @return the String with replaced characters
		 */
		public String replaceString(final String toReplace, final String replaceBy)
		{
			if ((getNonEmpty(toReplace) == null) || StringUtil.equals(toReplace, replaceBy))
				return strWork;

			if (replaceBy != null && replaceBy.contains(toReplace))
				reRead = false;

			if (strWork == null)
			{
				return strWork;
			}

			final int lenIn = strWork.length();
			int indexOf = strWork.indexOf(toReplace);
			if (indexOf < 0)
			{
				return strWork;
			}

			final int len = toReplace.length();
			final StringBuilder b = new StringBuilder(strWork.length() * 2);
			do
			{
				b.append(strWork.substring(0, indexOf));
				if (replaceBy != null)
				{
					b.append(replaceBy);
				}
				strWork = strWork.substring(indexOf + len);
				indexOf = strWork.indexOf(toReplace);
			}
			while (indexOf >= 0);

			b.append(strWork);

			final String outString = b.toString();
			final int lenOut = outString.length();

			return lenOut == lenIn || !reRead ? intern(outString) : StringUtil.replaceString(outString, toReplace, replaceBy);
		}
	}

	/**
	 * @param strWork
	 * @return the escaped string
	 */
	public static String xmlNameEscape(final String strWork)
	{
		String strWorkLocal = strWork;

		strWorkLocal = replaceChar(strWorkLocal, '*', "_star_", 0);
		strWorkLocal = replaceChar(strWorkLocal, '&', "_and_", 0);

		return strWorkLocal;
	}

	/**
	 * the filename extension of pathName
	 *
	 * @param pathName
	 * @return
	 * @deprecated use URLUtil.extension
	 */
	@Deprecated
	public static String extension(final String pathName)
	{
		return UrlUtil.extension(pathName);
	}

	/**
	 * inverse of extension
	 *
	 * @param strWork the string to work on
	 * @return the prefix
	 * @deprecated use UrlUtil.prefix(strWork);
	 */
	@Deprecated
	public static String prefix(final String strWork)
	{
		return UrlUtil.prefix(strWork);
	}

	/**
	 *
	 * normalize a string by stripping and converting any internal whitespace to a single blank
	 *
	 * @param strWork the input
	 * @param toLower if true return all lower case
	 * @return the output; null if all characters were removed
	 */
	public static String normalize(final String strWork, final boolean toLower)
	{
		return normalize(strWork, toLower, JDFConstants.BLANK);
	}

	/**
	 *
	 * normalize a string by stripping and converting any internal whitespace to a single blank
	 *
	 * @param strWork the input
	 *
	 * @return the output; null if all characters were removed
	 */
	public static String normalize(final String strWork)
	{
		return normalize(strWork, false, JDFConstants.BLANK);
	}

	/**
	 *
	 * normalize a string by stripping and converting any internal whitespace to the value of replace
	 *
	 * @param strWork the input
	 * @param toLower if true return all lower case
	 * @param replace the replace character, if null remove all whitespace
	 * @return the output; null if all characters were removed
	 */
	public static String normalize(String strWork, final boolean toLower, String replace)
	{
		if (strWork == null)
			return null;
		strWork = strWork.trim();
		if (getNonEmpty(strWork) == null)
			return null;
		if (replace == null)
			replace = JDFConstants.EMPTYSTRING;
		strWork = strWork.replaceAll("\\s+", replace);
		if (toLower)
			strWork = strWork.toLowerCase();
		return intern(strWork);
	}

	/**
	 * return null if s==null or s==def, else s<br/>
	 * used e.g. to zapp "" strings
	 *
	 * @param s the String to test
	 * @param def the default that is converted to null
	 * @return the converted String
	 */
	public static String getDefaultNull(final String s, final String def)
	{
		return s == null || s.equals(def) ? null : s;
	}

	/**
	 *
	 * @param s1
	 * @param s2
	 * @param normalize
	 * @param ignoreCase
	 * @param ignoreEmpty
	 * @param reduceSubstring if true slide over the string and check only the substring
	 * @return
	 */
	public static int getDistance(final String s1, final String s2, final boolean normalize, final boolean ignoreCase, final boolean ignoreEmpty, final boolean reduceSubstring)
	{
		if (!reduceSubstring)
			return getDistance(s1, s2, normalize, ignoreCase, ignoreEmpty);
		else
		{
			final int s11 = length(s1);
			final int s21 = length(s2);
			final int min = Math.min(s11, s21);
			final int max = Math.max(s11, s21);
			if (min == 0)
				return 0;
			final String base = s11 < s21 ? s2 : s1;
			final String other = s11 < s21 ? s1 : s2;
			final int steps = max - min + 1;
			int ret = Integer.MAX_VALUE;

			for (int i = 0; i < steps; i++)
			{
				final int d = getDistance(base.substring(i, min + i), other, normalize, ignoreCase, ignoreEmpty);
				ret = Math.min(d, ret);
				if (ret == 0)
					return ret;
			}
			return ret;
		}
	}

	public static int length(final String s1)
	{
		return s1 == null ? 0 : s1.length();
	}

	/**
	 * return the Levenschtein ditance where null is treated as ""
	 *
	 *
	 * @param s1
	 * @param s2
	 * @param normalize if true, normalize strings prior to calculating
	 * @param ignoreCase if true, ignore case strings when calculating
	 * @param ignoreEmpty if true, the result is 0 if one of the two strings is null or ""
	 * @return the Levenshtein distance
	 */
	public static int getDistance(String s1, String s2, final boolean normalize, final boolean ignoreCase, final boolean ignoreEmpty)
	{
		if (s1 == null)
			s1 = JDFConstants.EMPTYSTRING;
		if (s2 == null)
			s2 = JDFConstants.EMPTYSTRING;

		if (ignoreCase)
		{
			s1 = s1.toLowerCase();
			s2 = s2.toLowerCase();
		}
		if (normalize)
		{
			s1 = normalize(s1, false);
			s2 = normalize(s2, false);
			if (s1 == null)
				s1 = JDFConstants.EMPTYSTRING;
			if (s2 == null)
				s2 = JDFConstants.EMPTYSTRING;
		}
		if (s1.equals(s2) || ignoreEmpty && (JDFConstants.EMPTYSTRING.equals(s1) || JDFConstants.EMPTYSTRING.equals(s2)))
		{
			return 0;
		}
		// we weight deviations of shorter strings higher so that a,b doesent pass distance==1...
		final int minLen = Math.min(s1.length(), s2.length());
		int factor = 1;
		for (int i = 0; i < 4; i++)
		{
			if (minLen <= i)
			{
				factor *= 2;
			}
		}
		return factor * StringUtils.getLevenshteinDistance(s1, s2);
	}

	/**
	 * return null if s==null or s=="", else s<br/>
	 * used e.g. to zapp "" strings
	 *
	 * @param s the String to test
	 * @return the converted String
	 */
	public static String getNonEmpty(final String s)
	{
		return isEmpty(s) ? null : s.intern();
	}

	/**
	 * return true if s==null or s==""
	 *
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(final String s)
	{
		return s == null || JDFConstants.EMPTYSTRING.equals(s);
	}

	/**
	 * are we null or empty or contain only an empty JDFAttributeMap
	 *
	 * @param v
	 * @return
	 */
	public static boolean isEmpty(final Collection<String> v)
	{
		return v == null || v.isEmpty() || v.size() == 1 && StringUtil.isEmpty(v.iterator().next());
	}

	/**
	 * return false if s==null or s has only whotespace
	 *
	 * @param s
	 * @return
	 */
	public static boolean hasContent(final String s)
	{
		return s != null && !JDFConstants.EMPTYSTRING.equals(s) && normalize(s, false) != null;
	}

	/**
	 * replace the .extension of a file name
	 *
	 * @param strWork the file path
	 * @param newExt the new extension (works with or without the initial "."
	 * @return the strWork with a replaced extension
	 * @deprecated use UrlUtil.newExtension
	 */
	@Deprecated
	public static String newExtension(final String strWork, final String newExt)
	{
		return UrlUtil.newExtension(strWork, newExt);
	}

	/**
	 * @deprecated 060314 use KElement.xmlnsprefix
	 * @param strWork
	 * @return String
	 */
	@Deprecated
	public static String xmlNameSpace(final String strWork)
	{
		return KElement.xmlnsPrefix(strWork);
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * get the mime type for a given extension
	 *
	 * @param strWork String to work in
	 * @return the mime type
	 * @deprecated use MimeUtil.getMimeTypeFromExt(strWork);
	 */
	@Deprecated
	public static String mime(final String strWork)
	{
		return UrlUtil.getMimeTypeFromURL(strWork);
	}

	/**
	 * checks whether a string is a NMTOKEN
	 *
	 * @param strWork the string to check
	 * @return boolean - true if strWork is a NMTOKEN
	 */
	public static boolean isNMTOKEN(final String strWork)
	{
		if (strWork == null)
		{
			return false;
		}
		if (strWork.length() >= 64)
		{
			return false;
		}
		if ("*".equals(strWork))
		{
			return true;
		}
		// validate the value against the xsd definition
		// Nmtoken ::= (NameChar)+ NameChar ::= Letter | Digit | '.' | '-' | '_' | ':' | CombiningChar | Extender
		// CombiningChar | Extender should be handled by \pL
		return matches(strWork, "(\\pL|[0-9]|\\.|:|\\-|_)+");
	}

	/**
	 * checks whether a string is an ID
	 *
	 * @param strWork the string to check
	 * @return boolean - true if strWork is an ID
	 */
	public static boolean isID(final String strWork)
	{
		if (isEmpty(strWork))
		{
			return false;
		}
		if (StringUtils.isNumeric(strWork.substring(0, 1)))
		{
			return false;
		}
		return isNMTOKEN(strWork);
	}

	/**
	 * return true if d1 and d2 are within a range of epsilon or close enough to be serialized identically
	 *
	 * @param d1
	 * @param d2
	 * @return true if (almost) identical
	 */
	public static boolean isEqual(final double d1, final double d2)
	{
		return isEqual(d1, d2, JDFBaseDataTypes.EPSILON);
	}

	/**
	 * return true if d1 and d2 are within a range of epsilon or close enough to be serialized identically
	 *
	 * @param d1
	 * @param d2
	 * @return true if (almost) identical
	 */
	public static boolean isEqual(final double d1, final double d2, final double delta)
	{
		if (d1 == d2)
		{
			return true;
		}
		if (delta > 0)
		{
			if (Math.abs(d1 - d2) <= delta)
			{
				return true;
			}
			if (delta < 0.42 && d1 != 0 && Math.abs((d2 / d1) - 1.0) < delta)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * return -1 if d1 < d2 , 0 if d1==d2 ; +1 if d1>d2 are within a range of epsilon or close enough to be serialized identically
	 *
	 * @param d1
	 * @param d2
	 * @return int 1,0 or -1
	 */
	public static int compareTo(final double d1, final double d2)
	{
		if (isEqual(d1, d2))
		{
			return 0;
		}
		return d1 < d2 ? -1 : 1;

	}

	/**
	 * static implementation of compare for any comparable object that gracefully handles null<br/>
	 * null is always the smallest
	 *
	 * @param c0
	 * @param c1
	 * @return -1 if c0 &lt; c1, 0 if equal, 1 if c0 &gt; c1;
	 */
	public static int compare(final String c0, final String c1, final boolean ignoreCase)
	{
		if (c0 == null)
		{
			return c1 == null ? 0 : -1;
		}
		if (c1 == null)
		{
			return 1;
		}
		return ignoreCase ? c0.compareToIgnoreCase(c1) : c0.compareTo(c1);
	}

	/**
	 * checks whether a string is matches an NMTOKENS list
	 *
	 * @param strWork the string to check
	 * @return boolean - true if strWork is an NMTOKENS list
	 */
	public static boolean isNMTOKENS(final String strWork)
	{
		return isNMTOKENS(strWork, false);
	}

	/**
	 * checks whether a string is a NMTOKENS list
	 *
	 * @param strWork the string to check
	 * @param bID if true, also check that each individual token matches the pattern for an ID
	 * @return boolean true if strWork is a NMTOKENS list
	 */
	public static boolean isNMTOKENS(final String strWork, final boolean bID)
	{
		if (strWork == null)
		{
			return false;
		}
		final List<String> vs = StringUtil.tokenize(new StringArray(), strWork, null);
		if (vs.isEmpty())
		{
			return true; // tbd is an empty list an NMTOKENS ?
		}
		for (final String s : vs)
		{
			if (!StringUtil.isNMTOKEN(s) || (bID && !StringUtil.isID(s)))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * checks whether a string matches the boolean values "true" or "false"
	 *
	 * @param strWork the string to check
	 * @return boolean true if strWork is represents boolean value
	 */
	public static boolean isBoolean(final String strWork)
	{
		return parseBoolean(strWork, true) == parseBoolean(strWork, false);
	}

	/**
	 * checks whether a string matches the boolean values "true" or "false"
	 *
	 * @param strWork the string to check
	 * @return boolean true if strWork is represents boolean value
	 */
	public static boolean isDate(final String strWork)
	{
		return JDFDate.createDate(strWork) != null;
	}

	/**
	 * checks whether a string matches the boolean values "true" or "false"
	 *
	 * @param strWork the string to check
	 * @return boolean true if strWork is represents boolean value
	 */
	public static boolean isNumberList(final String strWork)
	{
		return JDFNumberList.createNumberList(strWork) != null;
	}

	/**
	 *
	 * @param csName
	 * @return
	 */
	public static Charset getCharset(final String csName)
	{
		try
		{
			return Charset.forName(csName);
		}
		catch (final Exception e)
		{
			return null;
		}
	}

	/**
	 *
	 * @param s
	 * @return null if nulkl, else the datatype
	 */
	public static EDataType getDataType(final String s)
	{
		if (s == null)
			return null;
		else if (isNumber(s))
			return isInteger(s) ? EDataType.integer : EDataType.number;
		else if (isBoolean(s))
			return EDataType.bool;
		else if (isDate(s))
			return EDataType.date;
		else if (isNumberList(s))
			return EDataType.numberlist;
		else
			return EDataType.string;
	}

	/**
	 * checks whether a string is a number
	 *
	 * @param str the string to check
	 * @return boolean true if strWork is a number
	 */
	public static boolean isNumber(final String str)
	{
		if (str == null)
		{
			return false;
		}
		final String dStr = str.trim();
		if (dStr.isEmpty())
		{
			return false;
		}
		final int first = dStr.charAt(0);
		if (first == '+' || first == '-' || (first >= '0' && first <= '9') || first == 'I' || first == '.')
		{
			if (dStr.indexOf(JDFConstants.BLANK) >= 0)
				return false;

			// NaN is not a number...
			return !Double.isNaN(parseDouble(str, Double.NaN));
		}
		return false;
	}

	/**
	 * replaces all chars that are not compatible with xml1.0
	 *
	 * @param strText the text to check
	 * @param replace the single char string to replace non xml chars with; if null the non-xml char is simply omitted
	 * @return the clean string, may be the same string
	 */
	public static String wipeInvalidXML10Chars(final String strText, final String replace)
	{
		String strTextLocal = strText;

		final char[] chars = strTextLocal.toCharArray();

		boolean found = false;
		int n = 0;
		for (int i = 0; i < chars.length; i++)
		{
			if (n > 0)
			{
				chars[i - n] = chars[i];
			}
			if (!isValidXML10Char(chars[i]))
			{
				if (replace != null)
				{
					chars[i] = replace.charAt(0);
				}
				else
				{
					n++;
				}

				found = true;
			}
		}

		if (found)
		{

			strTextLocal = new String(chars);
			if (n > 0)
			{
				strTextLocal = strTextLocal.substring(0, chars.length - n);
			}
		}

		return intern(strTextLocal);
	}

	private static boolean isValidXML10Char(final char c)
	{
		if ((c >= 0x20) && (c <= 0xD7FF))
		{
			return true;
		}
		else if ((c == 0x9) || (c == 0xA) || (c == 0xD))
		{
			return true;
		}
		else if ((c >= 0xE000) && (c <= 0xFFFD))
		{
			return true;
		}
		return false;
	}

	/**
	 * find the last character in strwork that is not in strNotList
	 *
	 * @param strWork the string to search
	 * @param strNotList the list of characters to ignore
	 * @return position of the last matching char, -1 if all strWork only contains chars from strNotList
	 */
	public static int find_last_not_of(final String strWork, final String strNotList)
	{
		if (strWork == null)
		{
			return -1;
		}
		if (strNotList == null || strNotList.length() == 0)
		{
			return strWork.length() - 1;
		}

		for (int i = strWork.length() - 1; i >= 0; i--)
		{
			if (strNotList.indexOf(strWork.charAt(i)) < 0)
			{
				return i;
			}
		}

		return -1;
	}

	/**
	 * returns the position of the token, if it is in the String.<br>
	 * The separator is excluded from the tokens. Multiple consecutive separators are treated as one (similar to whitespace handling).
	 *
	 * @param strWork the string to work on
	 * @param name the token to search
	 * @param separator separator
	 * @param iSkip number of tokens to skip before accepting (if 0 -> take the first etc., -1 -> first as well)
	 * @return int - 0 based position if the token exists, else -1
	 */
	public static int posOfToken(final String strWork, final String name, final String separator, final int iSkip)
	{
		if (strWork == null || name == null || strWork.indexOf(name) < 0)
			return -1;

		int posOfToken = -1;
		final VString vNames = StringUtil.tokenize(strWork, separator, false);

		if (iSkip == -1 || iSkip == 0)
		{
			posOfToken = vNames.indexOf(name);
		}
		else
		{
			int occurence = 0;
			for (int i = 0; i < vNames.size(); i++)
			{
				final String strName = vNames.elementAt(i);
				if (strName.equals(name))
				{
					if (occurence++ == iSkip)
					{
						posOfToken = i;
						break;
					}
				}
			}
		}
		return posOfToken;
	}

	/**
	 * check whether a string contains a complete token
	 * <p>
	 * default: hasToken(strWork, token, delim)
	 *
	 * @param strWork the string to work on
	 * @param token the token to search for
	 * @param delim the delimiter of the tokens
	 * @return boolean -
	 * @deprecated use the 4 parameter version
	 *
	 */
	@Deprecated
	public static boolean hasToken(final String strWork, final String token, final String delim)
	{
		return hasToken(strWork, token, delim, 0);
	}

	/**
	 * index of a token in a string
	 *
	 * @param strWork the string to work on
	 * @param token the token to search for
	 * @param delim the delimiter of the tokens
	 * @param start position to search in the string
	 * @return
	 */
	public static int indexOfToken(final String strWork, final String token, String delim, final int start)
	{
		if (delim == null)
			delim = JDFConstants.BLANK;
		if (strWork != null)
		{
			final int tl = token.length();
			final int sl = strWork.length();
			int p0 = start;
			while ((tl + p0) <= sl)
			{
				final int posToken1 = strWork.indexOf(token, p0);
				if (posToken1 < 0)
				{
					return posToken1;
				}
				else if (posToken1 == 0 && (tl == sl || delim.indexOf(strWork.charAt(tl)) >= 0))
				{
					return posToken1;
				}
				else if (posToken1 > 0 && delim.indexOf(strWork.charAt(posToken1 - 1)) >= 0 && ((sl == posToken1 + tl) || delim.indexOf(strWork.charAt(posToken1 + tl)) >= 0))
				{
					return posToken1;
				}
				else if (posToken1 >= 0)
				{
					p0 = posToken1 + 1;
				}
			}
		}
		return -1;
	}

	/**
	 * counts the number of occurrences of subString in String the total number of occurrences is counted, e.g "aaa" contains "aa" twice
	 *
	 * @param strWork
	 * @param subString
	 * @return the number of occurrences
	 */
	static public int numSubstrings(final String strWork, final String subString)
	{
		if (strWork == null || subString == null || subString.length() == 0)
			return 0;
		int n = 0;
		int pos = 0;
		while (pos >= 0)
		{
			pos = strWork.indexOf(subString, pos);
			if (pos >= 0)
			{
				n++;
				pos++;
			}
		}
		return n;
	}

	/**
	 * set a string to the raw bytes specified in buffer, bypassing all transcoders
	 *
	 * @param buffer the buffer to assign to <code>this</code>
	 * @param len
	 * @return the raw string
	 */
	public static String setRawBytes(final byte[] buffer, int len)
	{
		if (len < 0)
		{
			len = buffer.length;
		}

		char[] target = null;

		if (len > 0)
		{
			target = new char[len];

			for (int i = 0; i < len; i++)
			{
				target[i] = (char) buffer[i];
			}
		}
		else
		{
			target = new char[0]; // should never reached
		}
		return intern(new String(target));
	}

	/**
	 * get the raw bytes specified in strUnicode, bypassing all transcoders<br>
	 * any character values above 255 is truncated (c=c&0xff)
	 *
	 * @param strUnicode the unicode string
	 *
	 * @return char array of the raw bytes assigned to this
	 */
	public static byte[] getRawBytes(final String strUnicode)
	{
		final char[] pBuf = strUnicode.toCharArray();

		final int len = pBuf.length;

		final byte[] pc = new byte[len];

		for (int i = 0; i < len; i++)
		{
			pc[i] = (byte) (pBuf[i] & 0x00ff);
		}
		return pc;
	}

	/**
	 * get buffer as HexBinary <br>
	 * any character values above 255 is truncated
	 *
	 * @param buffer the String which you want to encode to HexBinary
	 * @param len the length of the buffer. <br>
	 *        If<0, default is -1. In this case the length of the char array will be used.
	 * @return the hexbinary representation
	 */

	public static String setHexBinaryBytes(final byte[] buffer, int len)
	{
		char[] target = null;

		if (buffer != null)
		{
			if (len < 0)
			{
				len = buffer.length;
				target = new char[len * 2];
			}
			if (len > 0)
			{
				target = new char[len * 2];
				for (int i = 0; i < len; i++)
				{
					char c = (char) ((buffer[i] & 0x00f0) >> 4);
					target[2 * i] = (c >= 10) ? (char) ('A' - 10 + c) : (char) ('0' + c);
					c = (char) (buffer[i] & 0x000f);
					target[2 * i + 1] = (c >= 10) ? (char) ('A' - 10 + c) : (char) ('0' + c);
				}
			}
			else
			{
				target = new char[0];
			}
		}
		else
		{
			target = new char[0];
		}
		return intern(new String(target));
	}

	/**
	 * Decode a HexBinary encoded byte array back to Unicode
	 *
	 * @param unicodeArray array which stores the HexBinary
	 * @return array of byte holding the unicode chars
	 */
	public static byte[] getHexBinaryBytes(final byte[] unicodeArray)
	{
		final byte emptyArray[] = new byte[0];
		final int len = unicodeArray == null ? 0 : unicodeArray.length;

		// check if there is at least one 16Bit unicode char
		if (len % 2 > 0)
		{
			return emptyArray;
		}

		// this will be the container for output
		final byte pc[] = new byte[len / 2];
		byte c = '0';

		for (int i = 0; i < len / 2; i++)
		{
			// maskiert das obere Byte
			int p = unicodeArray[i * 2] & 0x00ff;

			if (p >= '0' && p <= '9')
			{
				c = (byte) (p - '0');
			}
			else
			{
				if (p >= 'A' && p <= 'F')
				{
					c = (byte) (10 + p - 'A');
				}
				else
				{
					if (p >= 'a' && p <= 'f')
					{
						c = (byte) (10 + p - 'a');
					}
					else
					{
						return emptyArray;
					}
				}
			}

			pc[i] = (byte) (c << 4);

			p = unicodeArray[i * 2 + 1] & 0x00ff;

			if (p >= '0' && p <= '9')
			{
				c = (byte) (p - '0');
			}
			else
			{
				if (p >= 'A' && p <= 'F')
				{
					c = (byte) (10 + p - 'A');
				}
				else
				{
					if (p >= 'a' && p <= 'f')
					{
						c = (byte) (10 + p - 'a');
					}
					else
					{
						return emptyArray;
					}
				}
			}
			pc[i] += c;
		}
		return pc;
	}

	/**
	 * return the UTF8 String <code>strUnicode</code> as Unicode byte array
	 *
	 * @param strUnicode the unicode string to transcode to utf8
	 * @return a byte array[] representing the utf-8 code of the input string, <code>null</code> if an error occurred
	 */
	public static byte[] getUTF8Bytes(final String strUnicode)
	{
		if (StringUtil.getNonEmpty(strUnicode) != null)
		{
			return strUnicode.getBytes(StandardCharsets.UTF_8);
		}
		return null;
	}

	/**
	 *
	 * @deprecated use getUTF8Bytes instead
	 * @param strUnicode
	 * @return
	 */
	@Deprecated
	public static byte[] setUTF8String(final String strUnicode)
	{
		return getUTF8Bytes(strUnicode);
	}

	/**
	 * get the unicode string representing the UTF8 representation of the byte buffer fall back on default encoding in case someone accidentally sends in non utf-8
	 *
	 * @param utf8 the utf-8 encoded byte array
	 *
	 * @return String - the unicode string representation of the utf8 bytes assigned to this, <code>null</code> if an error occurrred
	 */
	public static String getUTF8String(final byte utf8[])
	{
		if (utf8 != null && utf8.length != 0)
		{
			try
			{
				String s = new String(utf8, UTF8);
				if (s.indexOf(0xfffd) >= 0)
				{
					s = new String(utf8, "Cp1252");
				}
				return intern(s);
			}
			catch (final UnsupportedEncodingException e)
			{
				return intern(new String(utf8, StandardCharsets.UTF_8));
			}
		}
		return null;
	}

	/**
	 * returns a formatted double. Truncates to 8 digits after the "." <br>
	 * If the double is representable as an integer, any ".0" is stripped.
	 *
	 * @param d the double to format
	 * @return the formatted string that represents d TBD handle exp format
	 */
	public static String formatDouble(final double d)
	{
		return formatter.formatDouble(d);
	}

	/**
	 * returns a formatted integer, replaces string constants with according int constants
	 *
	 * @param i the integer to format
	 * @return the formatted string that represents i
	 */
	public static String formatInteger(final int i)
	{
		String s = null;

		if (i == Integer.MAX_VALUE)
		{
			s = JDFConstants.POSINF;
		}
		else if (i == Integer.MIN_VALUE)
		{
			s = JDFConstants.NEGINF;
		}
		else
		{
			s = String.valueOf(i);
		}
		return s;
	}

	/**
	 * returns a formatted integer, replaces string constants with according int constants
	 *
	 * @param i the integer to format
	 * @return the formatted string that represents i
	 */
	public static String formatLong(final long i)
	{
		String s = null;

		if (i == Long.MAX_VALUE)
		{
			s = JDFConstants.POSINF;
		}
		else if (i == Long.MIN_VALUE)
		{
			s = JDFConstants.NEGINF;
		}
		else
		{
			s = String.valueOf(i);
		}
		return intern(s);
	}

	/**
	 * checks whether <code>str</code> reprents an integer
	 *
	 * @param str the String to check
	 * @return boolean - true if the string represents an integer number
	 */
	public static boolean isInteger(final String str)
	{
		if (str == null || str.isEmpty())
		{
			return false;
		}
		String intStr = str.charAt(0) == ' ' ? str.trim() : str;
		if (intStr.isEmpty())
		{
			return false;
		}
		final int first = intStr.charAt(0);
		if (first == '+' || first == '-' || (first >= '0' && first <= '9') || first == 'I' || first == 'u')
		{

			if (str.charAt(0) != ' ')
				intStr = str.trim();

			if (intStr.equals(JDFConstants.POSINF) || intStr.equals(JDFConstants.NEGINF))
			{
				return true;
			}
			// hack for xml schema conformance, which uses unbounded to define + infinity
			if (intStr.equals("unbounded"))
			{
				return true;
			}

			try
			{
				Integer.parseInt(intStr);
				return true;
			}
			catch (final NumberFormatException e)
			{
				return false;
			}
		}
		return false;
	}

	/**
	 * checks whether <code>str</code> reprents an integer
	 *
	 * @param str the String to check
	 * @return boolean - true if the string represents an integer number
	 */
	public static boolean isLong(final String str)
	{
		if (str == null)
		{
			return false;
		}
		final String intStr = str.trim();
		if (intStr.length() == 0)
		{
			return false;
		}
		final int first = intStr.charAt(0);
		if (first == '+' || first == '-' || (first >= '0' && first <= '9') || first == 'I' || first == 'u' || first == '.')
		{

			if (intStr.equals(JDFConstants.POSINF))
			{
				return true;
			}

			if (intStr.equals(JDFConstants.NEGINF))
			{
				return true;
			}
			// hack for xml schema conformance, which uses unbounded to define +
			// infinity
			if (intStr.equals("unbounded"))
			{
				return true;
			}

			try
			{
				Long.parseLong(intStr);
				return true;
			}
			catch (final NumberFormatException e)
			{
				return false;
			}
		}
		return false;
	}

	/**
	 * escape a string by prepending escapeChar and a numerical representation of the string. Characters to be escaped are defined by toEscape, escapeBelow and escapeAbove
	 * <p>
	 * default: escape(String toEscape, null, 0, 0, 0, 256); //Note that an escaped character can't be unescaped without the knowledge of the escapelength
	 *
	 * @param strToEscape the String to escape
	 * @param strCharSet the set of characters that should be escaped eg "äöüß$€"
	 * @param strEscapeChar the character sequence that marks an escape sequence. If <code>null</code>, "\\" is used
	 *
	 * @param iRadix the numerical representation base of the escaped chars, e.g. 8 for octal, 16 for hex<br>
	 *        if radix == 0 the escape char is merely inserted in front of the char to escape<br>
	 *        if radix <0 the escape char is replaced by the prefix<br>
	 *        valid radix: -1,0,2,8,10,16
	 *
	 * @param iEscapeLen the number of digits per escaped char, not including escapeChar
	 * @param iEscapeBelow all characters with an encoding below escapeBelow should also be escaped, if negative, no lower limit applies
	 * @param iEscapeAbove all characters with an encoding above escapeAbove should also be escaped, if negative, no upper limit applies
	 *
	 * @return the string where all required sequences have been replaced by their escaped representation
	 */
	public static String escape(final String strToEscape, final String strCharSet, final String strEscapeChar, final int iRadix, final int iEscapeLen, final int iEscapeBelow, final int iEscapeAbove)
	{
		final byte[] a_toEscape = getUTF8Bytes(strToEscape);
		return getUTF8String(escape(a_toEscape, strCharSet, strEscapeChar, iRadix, iEscapeLen, iEscapeBelow, iEscapeAbove));
	}

	/**
	 * return true if a equals b or both are null or ""
	 *
	 * @param a String to compare
	 * @param b String to compare
	 * @return boolean true if a equals b or both are one of null or ""
	 */
	public static boolean equals(final String a, final String b)
	{
		return ContainerUtil.equals(getNonEmpty(a), getNonEmpty(b));
	}

	/**
	 * escape a string by prepending escapeChar and a numerical representation of the string. Characters to be escaped are defined by toEscape, escapeBelow and escapeAbove
	 * <p>
	 * default: escape(String toEscape, null, 0, 0, 0, 256); //Note that an escaped character can't be unescaped without the knowledge of the escapelength
	 *
	 * @param a_toEscape the bytes to escape
	 * @param strCharSet the set of characters that should be escaped eg "äöüß$€"
	 * @param strEscapeChar the character sequence that marks an escape sequence. If <code>null</code>, "\\" is used
	 *
	 * @param iRadix the numerical representation base of the escaped chars, e.g. 8 for octal, 16 for hex<br>
	 *        if radix == 0 the escape char is merely inserted in front of the char to escape<br>
	 *        if radix <0 the escape char is replaced by the prefix<br>
	 *        valid radix: -1,0,2,8,10,16
	 *
	 * @param iEscapeLen the number of digits per escaped char, not including escapeChar
	 * @param iEscapeBelow all characters with an encoding below escapeBelow should also be escaped, if negative, no lower limit applies
	 * @param iEscapeAbove all characters with an encoding above escapeAbove should also be escaped, if negative, no upper limit applies
	 *
	 * @return the string where all illegal sequences have been replaced by their escaped representation
	 */
	public static byte[] escape(final byte[] a_toEscape, final String strCharSet, String strEscapeChar, final int iRadix, final int iEscapeLen, final int iEscapeBelow, int iEscapeAbove)
	{
		if (a_toEscape == null)
			return null;
		if (strEscapeChar == null)
		{
			strEscapeChar = "\\";
		}

		if (iEscapeAbove < 0)
		{
			iEscapeAbove = 0x7fffffff;
		}

		final int l = a_toEscape.length;
		int cToEscape;
		final byte[] escaped = new byte[a_toEscape.length * 4];
		int posE = 0;
		final byte[] escapeCharbytes = strEscapeChar.getBytes();

		for (int i = 0; i < l; i++)
		{
			cToEscape = a_toEscape[i];
			if (cToEscape < 0)
			{
				cToEscape = 256 + cToEscape;
			}

			if ((cToEscape > iEscapeAbove) || (cToEscape < iEscapeBelow) || (strCharSet != null && strCharSet.indexOf(cToEscape) != -1))
			{ // the character must be escaped
				for (final byte escapeCharbyte : escapeCharbytes)
				{
					escaped[posE] = escapeCharbyte;
					posE++;
				}

				if (iRadix > 0)
				{ // radix is a flag to convert to octal, hex etc.
					final StringBuilder buf = new StringBuilder();

					if (iRadix == 2)
					{
						buf.append(Integer.toBinaryString(cToEscape));
					}
					else if (iRadix == 8)
					{
						buf.append(Integer.toOctalString(cToEscape));
					}
					else if (iRadix == 10)
					{
						buf.append(Integer.toString(cToEscape));
					}
					else if (iRadix == 16)
					{
						buf.append(Integer.toHexString(cToEscape));
					}
					else
					{
						throw new IllegalArgumentException("StringUtil.escape radix out of range");
					}

					if (iEscapeLen > 0)
					{ // check if the length of the buffer is smaler then the
						// ordered escape length. If this is the case
						// insert some 0 in front of. for Example buf = 12345
						// iEscapeLen is 7. The result String is 0012345
						final int lenBuf = buf.length();
						if (lenBuf < iEscapeLen)
						{
							for (int j = 0; j < iEscapeLen - lenBuf; j++)
							{
								buf.insert(j, '0');
							}
						}
					}

					final byte[] bufbytes = buf.toString().getBytes();
					for (final byte bufbyte : bufbytes)
					{
						escaped[posE] = bufbyte;
						posE++;
					}

					// empty StringBuilder
					buf.delete(0, buf.length());
				}
				else if (iRadix < 0)
				{
					// noop
				}
				else
				{ // radix = 0; just insert the escape character in front of the
					// actual char
					escaped[posE] = a_toEscape[i];
					posE++;
				}
			}
			else
			{ // no escape necessary --> just copy it
				escaped[posE] = a_toEscape[i];
				posE++;
			}
		}
		final byte[] stringByte = new byte[posE];
		for (int i = 0; i < posE; i++)
		{
			stringByte[i] = escaped[i];
		}
		return stringByte;
	}

	/**
	 * unescape a String which was escaped with the Java StringUtil.escape method
	 *
	 * @param strToUnescape the String to unescape. For example <code>zz\d6\zzz\c4\\dc\z\d6\\24\\3f\zz@z</code>
	 * @param strEscapeChar the char which indicates a escape sequence "\\" in this case (thats also the default)
	 * @param iRadix the radix of the escape sequenze. 16 in this example.
	 * @param escapeLen the number of digits per escaped char, not including strEscapeChar
	 *
	 * @return the unescaped String.
	 */
	public static String unEscape(final String strToUnescape, final String strEscapeChar, final int iRadix, final int escapeLen)
	{
		if (strToUnescape == null)
			return null;
		byte[] byteUnEscape = getUTF8Bytes(strToUnescape);
		byteUnEscape = unEscape(byteUnEscape, strEscapeChar, iRadix, escapeLen);
		return getUTF8String(byteUnEscape);
	}

	/**
	 * unescape a String which was escaped with the Java StringUtil.escape method
	 *
	 * @param byteUnEscape the bytes to unescape. For example <code>zz\d6\zzz\c4\\dc\z\d6\\24\\3f\zz�z</code>
	 * @param strEscapeChar the char which indicates a escape sequence "\\" in this case (thats also the default)
	 * @param iRadix the radix of the escape sequenze. 16 in this example.
	 * @param escapeLen the number of digits per escaped char, not including strEscapeChar
	 *
	 * @return the unescaped byte array. <code>zz�zzz��z�$?zz�z</code> in this example
	 */
	public static byte[] unEscape(final byte[] byteUnEscape, final String strEscapeChar, final int iRadix, final int escapeLen)
	{
		if (byteUnEscape == null)
			return null;
		final byte[] byteEscape = new byte[byteUnEscape.length];
		final byte escapeChar = strEscapeChar.getBytes()[0]; // dont even dream of using � as an escape char
		int n = 0;
		final byte[] escapeSeq = new byte[escapeLen];

		for (int i = 0; i < byteUnEscape.length; i++)
		{
			// only check for escaping in case enough chars remain at the end
			if (byteUnEscape[i] != escapeChar || i >= byteUnEscape.length - escapeLen)
			{
				byteEscape[n++] = byteUnEscape[i];
			}
			else if (iRadix <= 0 && (i + 1) < byteUnEscape.length)
			{
				byteEscape[n++] = byteUnEscape[++i];
			}
			else
			{
				for (int j = 0; j < escapeLen; j++)
				{
					escapeSeq[j] = byteUnEscape[i + j + 1];
				}

				final String strIsEscaped = new String(escapeSeq); // get the escaped str 'd6'
				try
				{
					final Integer integer = Integer.valueOf(strIsEscaped, iRadix);// and get the int value
					byteEscape[n++] = (byte) integer.intValue();
				}
				catch (final NumberFormatException ex)
				{
					byteEscape[n++] = escapeChar;
					for (int j = 0; j < escapeLen; j++)
					{
						byteEscape[n++] = byteUnEscape[i + j + 1];
					}
				}
				i += escapeLen;
			}
		}
		byte[] stringByte = null;
		if (n == byteEscape.length)
		{
			stringByte = byteEscape;
		}
		else
		{
			stringByte = new byte[n];
			for (int i = 0; i < n; i++)
			{
				stringByte[i] = byteEscape[i];
			}
		}
		return stringByte;
	}

	/**
	 * converts a VString to a single string represents all members of the VString concatenated together
	 *
	 * @param vs
	 *
	 * @deprecated use vs.getString(" ",null,null)
	 * @return String - the unicode string representation of the utf8 bytes assigned to this, null if an error occurrred
	 */
	@Deprecated
	public static String vStringToString(final VString vs)
	{
		return StringUtil.setvString(vs, " ", null, null);
	}

	/**
	 * parses a string to double and catches any format exception
	 *
	 * @param s the string to parse
	 * @param def the default to return in case of error
	 * @return the parsed double of s
	 * @since 080404 handles "" gracefully
	 */
	public static double parseDouble(String s, final double def)
	{
		if (s == null)
		{
			return def;
		}
		s = s.trim();
		if (s.isEmpty())
			return def;
		final int first = s.charAt(0);
		if (first == '+' || first == '-' || (first >= '0' && first <= '9') || first == 'I' || first == 'u' || first == '.')
		{

			if (s.equalsIgnoreCase(JDFConstants.POSINF) || s.equalsIgnoreCase(JDFConstants.UNBOUNDED))
			{
				return Double.MAX_VALUE;
			}

			if (s.equalsIgnoreCase(JDFConstants.NEGINF))
			{
				return -Double.MAX_VALUE;
			}

			try
			{
				return Double.parseDouble(s);
			}
			catch (final NumberFormatException nfe)
			{
				try
				{
					s = replaceChar(s, ',', ".", 0);
					return Double.parseDouble(s);
				}
				catch (final NumberFormatException nfe2)
				{
					// nop
				}
			}
		}
		return def;
	}

	/**
	 * parses a string to boolean and catches any format exception "1" and "0" are treated as true and false respectively
	 *
	 * @param s the string to parse
	 * @param def the default to return in case of error
	 * @return the parsed boolean of s
	 * @since 080404 handles "" gracefully
	 */
	public static boolean parseBoolean(String s, final boolean def)
	{
		if (s == null)
		{
			return def;
		}

		s = s.trim().toLowerCase();
		if ("false".equals(s) || "0".equals(s))
		{
			return false;
		}

		if ("true".equals(s) || "1".equals(s))
		{
			return true;
		}

		return def;
	}

	/**
	 * parses a string to integer and catches any format exception
	 *
	 * @param s the string to parse
	 * @param def the default to return in case of error
	 * @return the parsed double of s
	 * @since 080404 handles "" gracefully
	 */
	public static int parseInt(String s, final int def)
	{

		if (s == null)
		{
			return def;
		}
		s = s.trim();
		if (s.isEmpty())
			return def;
		final int first = s.charAt(0);
		if (first == '+' || first == '-' || (first >= '0' && first <= '9') || first == 'I' || first == 'u')
		{
			try
			{
				return Integer.parseInt(s);
			}
			catch (final NumberFormatException nfe)
			{
				// nop
			}
			s = s.toLowerCase();
			final int pos0x = s.indexOf("0x");
			if (pos0x >= 0)
			{
				s = (pos0x > 0 ? StringUtil.leftStr(s, pos0x) : "") + s.substring(pos0x + 2);
				try
				{
					return Integer.parseInt(s, 16);
				}
				catch (final NumberFormatException nfe)
				{
					return def;
				}
			}

			if (s.equalsIgnoreCase(JDFConstants.POSINF) || s.equalsIgnoreCase(JDFConstants.UNBOUNDED))
			{
				return Integer.MAX_VALUE;
			}

			if (s.equalsIgnoreCase(JDFConstants.NEGINF))
			{
				return Integer.MIN_VALUE;
			}

			try
			{
				final double d = Double.parseDouble(s);
				if (d > Integer.MAX_VALUE)
				{
					return Integer.MAX_VALUE;
				}
				else if (d < Integer.MIN_VALUE)
				{
					return Integer.MIN_VALUE;
				}
				return (int) (d + 0.4999);
			}
			catch (final NumberFormatException nfe2)
			{
				// nop
			}
		}
		return def;
	}

	/**
	 * parses a string to long and catches any format exception
	 *
	 * @param s the string to parse
	 * @param def the default to return in case of error
	 * @return the parsed double of s
	 * @since 080404 handles "" gracefully
	 */
	public static long parseLong(String s, final long def)
	{
		if (s == null)
		{
			return def;
		}
		s = s.trim();
		if (s.isEmpty())
			return def;
		final int first = s.charAt(0);
		if (first == '+' || first == '-' || (first >= '0' && first <= '9') || first == 'I' || first == 'u')
		{

			try
			{
				return Long.parseLong(s);
			}
			catch (final NumberFormatException nfe)
			{
				// nop
			}
			s = s.toLowerCase();
			final int pos0x = s.indexOf("0x");
			if (pos0x >= 0)
			{
				s = (pos0x > 0 ? StringUtil.leftStr(s, pos0x) : "") + s.substring(pos0x + 2);
				try
				{
					return Long.parseLong(s, 16);
				}
				catch (final NumberFormatException nfe)
				{
					return def;
				}
			}
			if (s.equalsIgnoreCase(JDFConstants.POSINF) || s.equalsIgnoreCase(JDFConstants.UNBOUNDED))
			{
				return Long.MAX_VALUE;
			}

			if (s.equalsIgnoreCase(JDFConstants.NEGINF))
			{
				return Long.MIN_VALUE;
			}

			try
			{
				return Long.parseLong(s);
			}
			catch (final NumberFormatException nfe)
			{
				try
				{
					final double d = Double.parseDouble(s);
					if (d > Long.MAX_VALUE)
					{
						return Long.MAX_VALUE;
					}
					else if (d < Long.MIN_VALUE)
					{
						return Long.MIN_VALUE;
					}
					return (long) (d + 0.4999);
				}
				catch (final NumberFormatException nfe2)
				{
					// nop
				}
			}
		}

		return def;
	}

	/**
	 * Convert a UNC path to a valid file URL or IRL note that some internal functions use network protocol and therefor performance may be non-optimal
	 *
	 * @param unc The UNC string to parse, may also be used for local characters
	 * @param bEscape128 if true, escape non -ascii chars (URI), if false, don't (IRI)
	 * @return the URL string
	 */
	public static String uncToUrl(final String unc, final boolean bEscape128)
	{
		return UrlUtil.fileToUrl(new File(unc), bEscape128);
	}

	/**
	 * gets the file name from a path - regardless of the OS syntax that the path is declared in
	 *
	 * @param pathName
	 * @return
	 */
	public static String pathToName(final String pathName)
	{
		if (UrlUtil.isWindowsLocalPath(pathName) || UrlUtil.isUNC(pathName))
		{
			return token(pathName, -1, "\\");
		}
		return token(pathName, -1, "/");
	}

	public static String underToCamel(final String toConvert)
	{
		final StringArray v = StringArray.getVString(toConvert, JDFConstants.UNDERSCORE);
		if (StringArray.isEmpty(v))
		{
			return null;
		}
		if (v.size() == 1)
		{
			return intern(StringUtils.isAllUpperCase(v.get(0)) ? StringUtils.capitalize(v.get(0).toLowerCase()) : StringUtils.capitalize(v.get(0)));
		}
		else
		{
			String ret = "";
			for (final String s : v)
			{
				ret += StringUtils.capitalize(s.toLowerCase());
			}
			return intern(ret);
		}
	}

	/**
	 * @param pathName
	 * @return
	 * @deprecated use UrlUtil.isWindowsLocalPath(pathName);
	 */
	@Deprecated
	public static boolean isWindowsLocalPath(final String pathName)
	{
		return UrlUtil.isWindowsLocalPath(pathName);

	}

	// ///////////////////////////////////////////////////////////////
	/**
	 * @param pathName
	 * @return
	 * @deprecated use URLUtil.isUNC(pathName)
	 */
	@Deprecated
	public static boolean isUNC(final String pathName)
	{
		return UrlUtil.isUNC(pathName);
	}

	/**
	 * converts a simple regexp to a real regexp <br/>
	 * * --> (.*) (any # of chars) <br/>
	 * . --> \. (literal ".")<br/>
	 * ? --> . (exactly one character) if one of ([|\ is found in the expression we assume it is a real regexp that has already been converted
	 *
	 * @param simpleRegExp the simple regexp
	 * @return the converted real regexp
	 */
	public static String simpleRegExptoRegExp(final String simpleRegExp)
	{
		return simpleRegExptoRegExp(simpleRegExp, false);
	}

	/**
	 * converts a simple regexp to a real regexp <br/>
	 * * --> (.*) (any # of chars) <br/>
	 * . --> \. (literal ".")<br/>
	 * ? --> . (exactly one character) if not alwaysSimple one of ([|\ is found in the expression we assume it is a real regexp that has already been converted
	 *
	 * @param simpleRegExp the simple regexp
	 * @param alwaysSimple
	 * @return the converted real regexp
	 */
	public static String simpleRegExptoRegExp(String simpleRegExp, final boolean alwaysSimple)
	{
		if (isEmpty(simpleRegExp))
		{
			return null;
		}
		// don't resimplify explicit regexp
		if (!alwaysSimple && StringUtils.containsAny(simpleRegExp, "{([|"))
			return simpleRegExp;

		simpleRegExp = StringUtil.escape(simpleRegExp, ".\\{}|[]()", JDFConstants.BACK_SLASH, 0, 0, 0, Integer.MAX_VALUE);
		// attention note sequence, otherwise we get unwanted side effects
		final String[] in = new String[] { "*", "+", "?", "$" };
		final String[] out = new String[] { "(.*)", "(.+)", "(.)", "(\\$)" };
		for (int i = 0; i < in.length; i++)
		{
			final StringBuilder b = new StringBuilder(simpleRegExp.length() * 2);
			int lastPos = 0;
			while (lastPos >= 0)
			{
				final int posSimpleToken = simpleRegExp.indexOf(in[i], lastPos);
				if (posSimpleToken >= 0)
				{
					b.append(simpleRegExp.substring(lastPos, posSimpleToken));
					b.append(out[i]);
				}
				else
				{
					b.append(simpleRegExp.substring(lastPos));
				}
				lastPos = posSimpleToken >= 0 ? posSimpleToken + 1 : posSimpleToken;
			}

			simpleRegExp = b.toString();
		}
		return intern(simpleRegExp);
	}

	/**
	 * match a regular expression using String.matches(), but also catch exceptions and handle simplified regexp. The <code>null</code> expression is assumed to match anything.
	 *
	 * @param str the string to match
	 * @param regExp the expression to match against
	 * @return true, if str matches regExp or regexp is empty
	 */
	public static boolean matchesSimple(final String str, final String regExp)
	{
		return matchesSimple(str, regExp, false);
	}

	/**
	 * match a regular expression using String.matches(), but also catch exceptions and handle simplified regexp. The <code>null</code> expression is assumed to match anything.
	 *
	 * @param str the string to match
	 * @param regExp the expression to match against
	 * @param alwaysSimple if true - always escape
	 * @return true, if str matches regExp or regexp is empty
	 */
	public static boolean matchesSimple(final String str, String regExp, final boolean alwaysSimple)
	{
		regExp = simpleRegExptoRegExp(regExp, alwaysSimple);
		return matches(str, regExp);
	}

	/**
	 * simple comparison of strings with some heuristics
	 *
	 * @param attribute
	 * @param attribute2
	 * @param delta absolute delta of numeric values to be considered equal
	 * @return
	 */
	public static boolean equals(final String attribute, final String attribute2, final double delta)
	{
		if (isEmpty(attribute))
			return isEmpty(attribute2);

		if (equals(attribute.trim(), attribute2.trim()))
		{
			return true;
		}
		if (delta >= 0)
		{
			final EDataType dt = getDataType(attribute);
			final EDataType dt2 = getDataType(attribute2);
			final boolean isNumber = EDataType.number.equals(dt) && EDataType.integer.equals(dt2) || EDataType.number.equals(dt2) && EDataType.integer.equals(dt);

			if (!isNumber && !ContainerUtil.equals(dt, dt2))
			{
				return false;
			}
			if (EDataType.integer.equals(dt) && !isNumber)
			{
				return parseInt(attribute, -1) == parseInt(attribute2, -2);
			}
			else if (EDataType.number.equals(dt) || isNumber)
			{
				final double parseDouble = parseDouble(attribute, -1);
				final double parseDouble2 = parseDouble(attribute2, -2);
				return isEqual(parseDouble, parseDouble2, delta);
			}
			else if (EDataType.bool.equals(dt))
			{
				return parseBoolean(attribute, true) == parseBoolean(attribute2, false);
			}
			else if (EDataType.numberlist.equals(dt))
			{
				final JDFNumberList nl1 = JDFNumberList.createNumberList(attribute);
				final JDFNumberList nl2 = JDFNumberList.createNumberList(attribute2);
				return nl1.matches(nl2, delta);
			}
			else if (EDataType.date.equals(dt))
			{
				final JDFDate d1 = JDFDate.createDate(attribute);
				final JDFDate d2 = JDFDate.createDate(attribute2);
				return Math.abs(d1.getTimeInMillis() - d2.getTimeInMillis()) < delta;
			}
		}
		return false;
	}

	/**
	 * match a regular expression using String.matches(), but also catch exceptions </br>
	 * does NOT handle simplified regexp. The <code>null</code> expression is assumed to match anything.
	 *
	 * @param str the string to match
	 * @param regExp the expression to match against
	 * @return true, if str matches regExp or regexp is empty
	 */
	public static boolean matches(final String str, final String regExp)
	{
		// the null expression is assumed to match anything
		if ((regExp == null) || (regExp.length() == 0) || "*".equals(regExp))
		{
			return true;
		}
		else if (str == null)
		{
			return false;
		}

		boolean b;
		try
		{
			b = str.matches(regExp);
		}
		catch (final PatternSyntaxException e)
		{
			b = false;
		}

		return b;
	}

	/**
	 * match a regular expression using ignoring cases using String.matches(), but also catch exceptions and handle simplified regexp. The <code>null</code> expression is assumed
	 * to match anything.
	 *
	 * @param str the string to match
	 * @param regExp the simplified expression to match against
	 * @return true, if str matches regExp or regexp is empty
	 */
	public static boolean matchesIgnoreCase(final String str, final String regExp)
	{
		return matchesIgnoreCase(str, regExp, false);
	}

	/**
	 * match a regular expression using ignoring cases using String.matches(), but also catch exceptions and handle simplified regexp. The <code>null</code> expression is assumed
	 * to match anything.
	 *
	 * @param str the string to match
	 * @param regExp the simplified expression to match against
	 * @param alwaysSimple TODO
	 * @return true, if str matches regExp or regexp is empty
	 */
	public static boolean matchesIgnoreCase(final String str, final String regExp, final boolean alwaysSimple)
	{
		return matchesSimple(str == null ? null : str.toLowerCase(), regExp == null ? null : regExp.toLowerCase(), alwaysSimple);
	}

	/**
	 * add the string appendString to all Strings in VString vs
	 *
	 * @param vS the string vector
	 * @param appendString the string to append
	 */
	public static void concatStrings(final VString vS, final String appendString)
	{
		if (vS != null)
		{
			for (int i = 0; i < vS.size(); i++)
			{
				String s = vS.get(i);
				s += appendString;
				vS.setElementAt(s, i);
			}
		}
	}

	/**
	 *
	 * @param s1
	 * @param s2
	 * @param delim
	 * @return
	 */
	public static String concat(final String s1, final String s2, final String delim)
	{
		if (isEmpty(s1))
			return getNonEmpty(s2);
		if (isEmpty(s2))
			return s1;
		if (isEmpty(delim))
			return s1 + s2;
		else
			return s1 + delim + s2;
	}

	/**
	 * create a string from an input stream
	 *
	 * @param is the input stream
	 * @return the string representation
	 */
	public static String createString(final InputStream is)
	{
		if (is == null)
			return null;

		final StringBuilder b = new StringBuilder();
		int n = 0;
		final byte[] by = new byte[4000];
		do
		{
			try
			{
				n = is.read(by);
			}
			catch (final IOException e)
			{
				n = -1;
			}
			if (n > 0)
			{
				final String s = new String(by, 0, n);
				b.append(s);
			}
		}
		while (n >= 0);

		return b.toString();
	}

	// //////////////////////////////////////////////////////////////////////

	/**
	 * returns the relative URL of a file relative to the current working directory
	 *
	 * @param f the file to get the relative url for
	 * @param baseDir the file that describes cwd, if null cwd is calculated
	 * @return
	 * @deprecated use getRelativeURL(File f, File fCWD, boolean bEscape128)
	 */
	@Deprecated
	public static String getRelativeURL(final File f, final File baseDir)
	{
		return UrlUtil.getRelativeURL(f, baseDir, true);
	}

	/**
	 * returns the relative URL of a file relative to the current workin directory
	 *
	 * @param f the file to get the relative url for
	 * @param baseDir the file that describes cwd, if null cwd is calculated
	 * @param bEscape128 if true, escape > 128 (URL) else retain (IRL)
	 * @return
	 * @deprecated use URLUtil.getRelativeURL
	 */
	@Deprecated
	public static String getRelativeURL(final File f, final File baseDir, final boolean bEscape128)
	{
		return UrlUtil.getRelativeURL(f, baseDir, bEscape128);
	}

	/**
	 * returns the relative URL of a file relative to the current working directory<br>
	 * this includes escaping of %20 etc.
	 *
	 * @param f the file to get the relative path for
	 * @param fCWD the file that describes cwd, if <code>null</code> cwd is calculated
	 * @return
	 * @deprecated use URLUtil.getRelativePath(f, fCWD);
	 */
	@Deprecated
	public static String getRelativePath(final File f, final File fCWD)
	{
		return UrlUtil.getRelativePath(f, fCWD);
	}

	/**
	 * get a vector of names in an iteration
	 *
	 * @param e any member of the enum to iterate over
	 * @return VString - the vector of enum names
	 * @deprecated use @see EnumUtil.getNamesVector
	 */
	@Deprecated
	public static VString getNamesVector(final Class<? extends ValuedEnum> e)
	{
		return EnumUtil.getNamesVector(e);
	}

	// //////////////////////////////////////////////////////////////////////////
	// //////

	/**
	 * get a vector of elements in an iteration
	 *
	 * @param e any member of the enum to iterate over
	 * @return Vector - the vector of enum instances
	 * @deprecated use @se EnumUtil.getEnumsVector
	 */
	@Deprecated
	public static Vector<ValuedEnum> getEnumsVector(final Class<? extends ValuedEnum> e)
	{
		return EnumUtil.getEnumsVector(e);
	}

	/**
	 * @param f
	 * @param b
	 * @return
	 * @deprecated use UrlUtil.fileToUrl(f, b);
	 */
	@Deprecated
	public static String fileToUrl(final File f, final boolean b)
	{
		return UrlUtil.fileToUrl(f, b);
	}

	/**
	 * strip a prefix, if it is there else return the string
	 *
	 * @param str the string to strip
	 * @param prefix the prefix to strip
	 * @param bIgnoreCase if true ignore the case of the prefix
	 * @return
	 */
	public static String stripPrefix(String str, final String prefix, final boolean bIgnoreCase)
	{
		if (str != null && prefix != null)
		{
			if (bIgnoreCase && str.toLowerCase().startsWith(prefix.toLowerCase()) || (str.startsWith(prefix)))
			{
				str = StringUtil.rightStr(str, -prefix.length());
			}
		}
		return str;
	}

	/**
	 * strip leading and trailing quotes from a string
	 *
	 * @param str the work string
	 * @param quote the quote character
	 * @param bTrim if true, trim whitespace prior to trimming quotes
	 *
	 * @return the work string trimmed and trailing + leading quote chars removed
	 */
	public static String stripQuote(String str, final String quote, final boolean bTrim)
	{
		if (str == null || quote == null)
		{
			return str;
		}
		if (bTrim)
			str = str.trim();
		if (str.length() >= 2)
		{
			if (str.startsWith(quote) && str.endsWith(quote))
				str = str.substring(1, str.length() - 1);
		}
		return intern(str);
	}

	/**
	 * returns a new string that has all characters stripped from work that are not in keepChars
	 *
	 * @param work
	 * @param keepChars
	 * @return
	 */
	public static String stripNot(final String work, final String keepChars)
	{
		if (work == null || keepChars == null)
		{
			return null;
		}
		final StringBuilder b = new StringBuilder(work.length());
		for (int i = 0; i < work.length(); i++)
		{
			if (keepChars.indexOf(work.charAt(i)) >= 0)
			{
				b.append(work.charAt(i));
			}

		}
		return b.length() > 0 ? intern(b.toString()) : null;
	}

	/**
	 * returns a new string that has all characters trimmed from the front and back of work that are not in trimChars
	 *
	 * @param work
	 * @param trimChars if null - use standard whitespace
	 * @return null if no chars left
	 */
	public static String trim(final String work, final String trimChars)
	{
		if (work == null)
		{
			return null;
		}
		if (trimChars == null)
		{
			return getNonEmpty(work.trim());
		}

		int nStart = 0;
		int nEnd = work.length();
		for (int i = 0; i < work.length(); i++)
		{
			if (trimChars.indexOf(work.charAt(i)) >= 0)
			{
				nStart = i + 1;
			}
			else
			{
				break;
			}
		}
		for (int i = work.length() - 1; i >= nStart; i--)
		{
			if (trimChars.indexOf(work.charAt(i)) >= 0)
			{
				nEnd = i;
			}
			else
			{
				break;
			}
		}
		final String trimmed = work.substring(nStart, nEnd);
		return getNonEmpty(trimmed);
	}

	/**
	 * returns a formatted double. Truncates to @param precision digits after the "." <br>
	 * If the double is representable as an integer, any ".0" is stripped.
	 *
	 * @param value
	 * @param precision number of digits after the "."
	 * @return
	 */
	public static String formatDouble(final double value, final int precision)
	{
		return formatter.formatDouble(value, precision);
	}

	/**
	 * 
	 * @param in
	 * @param search
	 * @param i
	 * @return
	 */
	public static int index(final String in, final String search, final int i)
	{
		return in == null || search == null ? -1 : in.indexOf(search, i);
	}

	/**
	 * 
	 * @param urlString
	 * @return
	 */
	public static String normalizeASCII(final String urlString)
	{
		String normalized = StringUtil.normalize(UrlUtil.urlToFileName(urlString), false, JDFConstants.UNDERSCORE);
		normalized = StringUtil.escape(normalized, UrlUtil.m_URIEscape, "_", -1, 0, 0x20, 0x7f);
		normalized = StringUtil.replaceString(normalized, "__", JDFConstants.UNDERSCORE);
		return normalized;
	}

	public static String intern(final String value)
	{
		return value == null ? null : value.intern();
	}

}
