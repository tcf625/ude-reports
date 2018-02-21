package ude.report.sample.ch02;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.iisigroup.ude.report.DocumentFormat;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.PDFDocumentManager;
import com.iisigroup.ude.report.itext2.font.CHTFontFactories;
import com.iisigroup.ude.report.itext2.font.CHTFontFactory;
import com.iisigroup.ude.report.itext2.font.WindowsFont;
import com.iisigroup.ude.report.utils.ReportTextUtils;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;

public class Sample_Fonts extends AbstractSample {

    @Test
    public void testFonts() throws IOException {
        final PDFDocumentManager documentManager = getDocumentManager();
        final File file = createFileByTestName(DocumentFormat.PDF);
        try (PDFDocument pdfDocument = documentManager.createPDFDocument(file, PageSize.A6.rotate())) {
            pdfDocument.writeText("細明體");
            pdfDocument.writeText("ABCD");
            pdfDocument.writeText(ReportTextUtils.CHINESE_BLOCK);

            final CHTFontFactory fontFactory = CHTFontFactories.INSTANCE.getFactory(WindowsFont.NEWMINGLIU);
            pdfDocument.setFontInfo(fontFactory.createFontInfo(12));
            pdfDocument.writeText("新細明體");
            pdfDocument.writeText("ABCD");
            pdfDocument.writeText(ReportTextUtils.CHINESE_BLOCK);
        }
    }

}
