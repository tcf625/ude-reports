package ude.report.sample.ch02.sec1;

import org.junit.Test;

import com.iisigroup.ude.report.DocumentFormat;
import com.iisigroup.ude.report.DocumentGenerator;
import com.iisigroup.ude.report.itext2.AbstractPDFGenerator;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;
import ude.report.sample.ch01.GSS0010;

public class Sample_Sections extends AbstractSample {

    @Test
    public void test_PDF_Section() {
        final PDFSection s1 = new PDFSection(PageSize.A6.rotate(), "Chap 1");
        final PDFSection s2 = new PDFSection(PageSize.A6.rotate(), "Chap 2");
        final PDFSection s3 = new PDFSection(PageSize.A6.rotate(), "Chap 3");
        final PDFSection s4 = new PDFSection(PageSize.A6.rotate(), "Chap 4");
        final PDFSection s5 = new PDFSection(PageSize.A6.rotate(), "Chap 5");

        final AbstractPDFGenerator root = AbstractPDFGenerator.of(s1, s2, s3, s4, s5);
        s2.addSection(new PDFSection(PageSize.A7, "Chap 2-1"));
        s2.addSection(new PDFSection(PageSize.A7, "Chap 2-2"));
        s2.addSection(new PDFSection(PageSize.A7, "Chap 2-3"));
        super.createPDF(root);
    }

    @Test
    public void test_Excel_PDF_Section() {
        final GSS0010 s1 = new GSS0010();
        final GSS0010 s2 = new GSS0010("S1");
        final GSS0010 s3 = new GSS0010("S2");
        final DocumentGenerator root = AbstractPDFGenerator.of(s1, s2, s3);
        super.doDocument(root, DocumentFormat.EXCEL);
        super.doDocument(root, DocumentFormat.PDF);
    }

}
