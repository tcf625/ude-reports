package ude.report.sample.ch02.sec1;

import org.junit.Test;

import com.iisigroup.ude.report.itext2.PDFGenerator;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;

public class Sample_Sections extends AbstractSample {

    @Test
    public void test_PDF_Section() {
        final PDFSection s1 = new PDFSection(PageSize.A6.rotate(), "Chap 1");
        final PDFSection s2 = new PDFSection(PageSize.A6.rotate(), "Chap 2");
        final PDFSection s3 = new PDFSection(PageSize.A6.rotate(), "Chap 3");
        final PDFSection s4 = new PDFSection(PageSize.A6.rotate(), "Chap 4");
        final PDFSection s5 = new PDFSection(PageSize.A6.rotate(), "Chap 5");
        final PDFGenerator root = PDFGenerator.Helper.from(s1, s2, s3, s4, s5);
        s2.addSection(new PDFSection(PageSize.A7, "Chap 2-1"));
        s2.addSection(new PDFSection(PageSize.A7, "Chap 2-2"));
        s2.addSection(new PDFSection(PageSize.A7, "Chap 2-3"));
        super.createPDF(root);
    }

}
