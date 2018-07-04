/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial;

import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.utils.PointF;
import com.iisigroup.ude.report.itext2.sample.SampleContent;
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.itext2.utils.barcode.Barcode39Drawer;
import com.iisigroup.ude.report.itext2.utils.barcode.BarcodeSupport;
import com.iisigroup.ude.report.utils.Coordinate;
import com.iisigroup.ude.report.utils.ReportException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

import org.junit.Test;

public class Sample_020_Barcode39 extends Sample_020_Barcode {

    @Test
    public void test_Basic() {
        this.setContentGenerater(new SampleContent() {
            @Override
            public void generatePDFContent(final PDFDocument pdfDocument) {
                final PdfContentByte contentByte = pdfDocument.getPdfWriter().getDirectContent();
                final Barcode39Drawer barCode39 = new Barcode39Drawer(Coordinate.CM_TL);

                // FIXED SIZE
                drarBarcode(contentByte, barCode39, new PointF(3, 4), 5, 0.8f, true);
                drarBarcode(contentByte, barCode39, new PointF(8.5f, 4), 5, 0.8f, false);
                // DEFUALT SIZE
                drarBarcode(contentByte, barCode39, new PointF(8.5f, 8), -1, -1, false);
                //pdfDocument.writeText("在文件上直接輸出：");
                //barCode39.append(pdfDocument, Sample_010_Barcode39.this.text, 3, 1, false);
            }
        });
        this.generateTestReport();
    }

    @Test
    public void test_Table() {
        this.setContentGenerater(new SampleContent() {
            @Override
            public void generatePDFContent(final PDFDocument pdfDocument) {

                try {
                    final BarcodeSupport barCode = new Barcode39Drawer(Coordinate.CM_TL);

                    final TableiText table = pdfDocument.createTable(100, 2);
                    table.getDefaultFormat().setMinHeightInCM(2f);
                    table.addCell("TEST");
                    table.addCell(barCode.createCell(table, "TEST", 4, 1, false));

                    table.addCell("TEST2");
                    table.addCell(barCode.createCell(table, "TEST2", 6, 1, false));

                    table.addCell("TEST3");
                    table.addCell(barCode.createCell(table, "TEST2", 6, 0.5f, false));

                    table.appendMe();
                } catch (final DocumentException e) {
                    throw new ReportException(e);
                }
            }
        });
        this.generateTestReport();
    }

}
