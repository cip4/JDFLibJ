package org.cip4.jdflib.extensions.xjdfwalker.jdftoxjdf;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.cip4.jdflib.core.JDFDoc;
import org.cip4.jdflib.core.KElement;
import org.cip4.jdflib.util.FileUtil;
import org.cip4.lib.xjdf.xml.XJdfValidator;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.xml.bind.ValidationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class XjdfLibValidationTest {

    private final File jdfFile;
    private static final URL SAMPLE_DIR = XjdfLibValidationTest.class.getResource("/data/SampleFiles");

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws URISyntaxException {
        File sampleFolder = new File(SAMPLE_DIR.toURI());
        List<Object[]> result = new ArrayList<>();
        for (File jdfFile : FileUtil.listFilesWithExtension(sampleFolder, "jdf")) {
            result.add(new Object[]{jdfFile});
        }
        return result;
    }

    public XjdfLibValidationTest(final File jdfFile) {
        this.jdfFile = jdfFile;
    }

    @Test
    @Ignore("Skipped, due to too much broken stuff in the conversion.")
    public void validateXjdf() throws Exception {
        JDFToXJDF converter = new JDFToXJDF();
        final JDFDoc d2 = JDFDoc.parseFile(jdfFile);
        KElement element = converter.convert(d2.getRoot());

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        element.write2Stream(bos);
        XJdfValidator validator = new XJdfValidator();
        try {
            validator.validate(new ByteArrayInputStream(bos.toByteArray()));
        } catch (ValidationException e) {
            throw new AssertionError(
                String.format(
                    "Converting JDF '%s' to XJDF resulted in an invalid XJDF file:\n%s",
                    jdfFile,
                    e.getMessage()
                )
            );
        }
    }
}