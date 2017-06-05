package ude.report.sample.ch01;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.iisigroup.ude.report.DocumentFormat;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.PDFDocumentManager;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;

public class BasicPDFTest extends AbstractSample {

    @Test
    public void testCreatePDF() throws IOException {
        final PDFDocumentManager documentManager = getDocumentManager();
        final File file = createFileByTestName(DocumentFormat.PDF);
        try (PDFDocument pdfDocument = documentManager.getPDFDocument(file, PageSize.A5)) {
            pdfDocument.writeText("TEST");
        }
    }

    @Test
    public void testCreateEmptyPDF() throws IOException {
        final PDFDocumentManager documentManager = getDocumentManager();
        final File file = createFileByTestName(DocumentFormat.PDF);
        try (PDFDocument pdfDocument = documentManager.getPDFDocument(file, PageSize.A5)) {
            //
        }
    }

}
