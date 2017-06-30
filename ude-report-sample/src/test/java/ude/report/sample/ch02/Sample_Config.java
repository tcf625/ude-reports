package ude.report.sample.ch02;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.iisigroup.ude.report.DocumentFormat;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.PDFDocumentManager;
import com.iisigroup.ude.report.itext2.testkit.AbstractITextTestkit;
import com.iisigroup.ude.report.itext2.testkit.ITextTestConfig;
import com.iisigroup.ude.system.UdeSystemPropertyItem;
import com.lowagie.text.PageSize;

public class Sample_Config extends AbstractITextTestkit {

    public Sample_Config() {
        super(prepareConfig());
    }

    private static ITextTestConfig prepareConfig() {
        final ITextTestConfig config = new ITextTestConfig();
        config.setFixedBaseDir(".");
        config.setPath(UdeSystemPropertyItem.GLOBAL_RESOURCE_PATH, "${BASEDIR}");
        config.iTextConfigPath = "classpath:itext-config-test-narrow.properties";
        return config;
    }

    @Test
    public void testNarrowMargin() throws IOException {
        final PDFDocumentManager documentManager = getDocumentManager();
        final File file = createFileByTestName(DocumentFormat.PDF);
        try (PDFDocument pdfDocument = documentManager.getPDFDocument(file, PageSize.A6.rotate())) {
            //
        }
    }

}
