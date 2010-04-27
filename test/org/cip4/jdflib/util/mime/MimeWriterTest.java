/**
 * 
 */
package org.cip4.jdflib.util.mime;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.cip4.jdflib.util.ByteArrayIOStream;
import org.cip4.jdflib.util.mime.MimeWriter.FixSemiColonStream;

/**
  * @author Rainer Prosi, Heidelberger Druckmaschinen *
 */
public class MimeWriterTest extends TestCase
{

	/**
	 * @throws Exception
	 */
	public void testFixSemicolon1() throws Exception
	{
		ByteArrayIOStream ios = new ByteArrayIOStream();
		FixSemiColonStream fs = new FixSemiColonStream(ios);
		fs.write("AAAAAAAAAAAAAAAAAAAA   \n\ngdf".getBytes());
		fs.write("content-type: multipart/related; foo=bar\nBBBBB".getBytes());
		fs.close();
		ByteArrayInputStream is = ios.getInputStream();
		byte b[] = new byte[100];
		is.read(b);
		String s = new String(b);
		assertTrue(s.indexOf("related;") < 0);
	}

	/**
	 * @throws Exception
	 */
	public void testFixSemicolon2() throws Exception
	{
		ByteArrayIOStream ios = new ByteArrayIOStream();
		FixSemiColonStream fs = new FixSemiColonStream(ios);
		fs.write("AAAAAAAAAAAAAAAAAAAA   \n\ngdf".getBytes());
		fs.write("content-type: multipart/related;\nbbbb".getBytes());
		fs.close();
		ByteArrayInputStream is = ios.getInputStream();
		byte b[] = new byte[100];
		is.read(b);
		String s = new String(b);
		assertTrue(s.indexOf("related;") < 0);
		assertTrue(s.indexOf("\nbbb") > 0);
	}

}
