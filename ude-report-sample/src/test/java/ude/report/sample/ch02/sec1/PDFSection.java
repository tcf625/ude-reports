package ude.report.sample.ch02.sec1;

import com.iisigroup.ude.report.itext2.AbstractPDFGenerator;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.table.format.DocumentAlign;
import com.lowagie.text.Rectangle;

public class PDFSection extends AbstractPDFGenerator {
    private String text;

    public PDFSection(final Rectangle pageSize, final String text) {
        super(pageSize);
        this.text = text;
    }

    @Override
    public void generatePDFContent(final PDFDocument pdfDocument) {
        pdfDocument.writeText(this.text, 28, DocumentAlign.CENTER);
    }
}
