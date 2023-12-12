package org.cip4.jdflib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.cip4.jdflib.JDFTestCaseBase;
import org.junit.jupiter.api.Test;

class StreamWriterTest extends JDFTestCaseBase
{

	@Test
	void testToString() throws IOException
	{
		StreamWriter sw = new StreamWriter(null);
		assertNotNull(sw.toString());
		sw.writeStream(new ByteArrayOutputStream());
	}

	@Test
	void testCopy() throws IOException
	{
		StreamWriter sw = new StreamWriter(new ByteArrayInputStream("abc".getBytes()));
		assertNotNull(sw.toString());
		ByteArrayIOStream os = new ByteArrayIOStream();
		sw.writeStream(os);
		assertEquals('a', os.getInputStream().read());
	}

}
