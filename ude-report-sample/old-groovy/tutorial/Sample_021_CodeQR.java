/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial;

import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.utils.PointF;
import com.iisigroup.ude.report.itext2.sample.SampleContent;
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.itext2.utils.barcode.BarcodeSupport;
import com.iisigroup.ude.report.itext2.utils.barcode.QRCodeDrawer;
import com.iisigroup.ude.report.utils.Coordinate;
import com.iisigroup.ude.report.utils.ReportException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;

import org.junit.Test;

public class Sample_021_CodeQR extends Sample_020_Barcode {

    @Test
    public void test_Basic() {
        this.text = "http://google.com";
        this.setContentGenerater(new SampleContent() {
            @Override
            public void generatePDFContent(final PDFDocument pdfDocument) {

                final PdfContentByte contentByte = pdfDocument.getPdfWriter().getDirectContent();
                final QRCodeDrawer barcode = new QRCodeDrawer(Coordinate.CM_TL);
                // 大小會自動擴增
                drarBarcode(contentByte, barcode, new PointF(3, 4), 5, 0.8f, true);
                drarBarcode(contentByte, barcode, new PointF(8.5f, 4), 5, 0.8f, false);
                //
                drarBarcode(contentByte, barcode, new PointF(3, 8), 5, 1.8f, true);
                drarBarcode(contentByte, barcode, new PointF(8.5f, 8), 5, 1.8f, false);
            }
        });
        this.generateTestReport();
    }

    @Test
    public void test_Table() {
        this.setPageSize(PageSize.A4.rotate());
        this.setContentGenerater(new SampleContent() {
            @Override
            public void generatePDFContent(final PDFDocument pdfDocument) {
                try {
                    final BarcodeSupport barCode = new QRCodeDrawer(Coordinate.CM_TL);

                    final TableiText table = pdfDocument.createTable(100, 2);
                    table.getDefaultFormat().setMinHeightInCM(5f);
                    table.addCell("TEST");
                    table.addCell(barCode.createCell(table, "TEST", 3, 4, false));

                    table.addCell("TEST2");
                    table.addCell(barCode.createCell(table, "TEST2", 3, 4, false));

                    table.addCell("TEST3");
                    table.addCell(barCode.createCell(table, "TEST2", 3, 4.5f, false));

                    table.appendMe();
                } catch (final DocumentException e) {
                    throw new ReportException(e);
                }
            }
        });
        this.generateTestReport();
    }

}
