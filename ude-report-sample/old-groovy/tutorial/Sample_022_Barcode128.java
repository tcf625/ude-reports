/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial;

import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.utils.PointF;
import com.iisigroup.ude.report.itext2.sample.SampleContent;
import com.iisigroup.ude.report.itext2.utils.barcode.Barcode128Drawer;
import com.iisigroup.ude.report.utils.Coordinate;
import com.lowagie.text.pdf.PdfContentByte;

import org.junit.Test;

public class Sample_022_Barcode128 extends Sample_020_Barcode {

    @Test
    public void test_Basic() {
        this.setContentGenerater(new SampleContent() {
            @Override
            public void generatePDFContent(final PDFDocument pdfDocument) {
                final PdfContentByte contentByte = pdfDocument.getPdfWriter().getDirectContent();
                final Barcode128Drawer barCode = new Barcode128Drawer(Coordinate.CM_TL);
                drarBarcode(contentByte, barCode, new PointF(3, 4), 5, 0.8f, true);
                drarBarcode(contentByte, barCode, new PointF(8.5f, 4), 5, 0.8f, false);
            }
        });
        this.generateTestReport();
    }

}
