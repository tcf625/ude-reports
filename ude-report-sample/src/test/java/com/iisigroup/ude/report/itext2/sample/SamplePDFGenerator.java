package com.iisigroup.ude.report.itext2.sample;

import com.iisigroup.ude.report.itext2.AbstractPDFGenerator;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.itext2.sample.content.PDFContent;
import com.lowagie.text.Rectangle;

public class SamplePDFGenerator extends AbstractPDFGenerator {

    private final PDFContent content;

    public SamplePDFGenerator(final PDFContent content) {
        super();
        this.content = content;
    }

    @Override
    public void generatePDFContent(final PDFDocument pdfDocument) {
        this.content.generatePDFContent(pdfDocument);
    }

    @Override
    public LayoutInfo prepareLayoutInfo(final Rectangle secPageSize) {
        return null;
    }

}
