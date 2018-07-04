/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04;

import org.junit.Test;

import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.report.table.format.cellcreator.BarcodeCell;
import com.iisigroup.ude.report.table.format.cellcreator.BarcodeCell.BarcodeType;

import ude.report.sample.AbstractSample;

public class Sample_021_CodeQR extends AbstractSample {

    @Test
    public void test_codes() {
        final String text = "https://cloudicweb.nhi.gov.tw/edesk/system/uptransPage.aspx?PIPELINES=3&CASE_NO=104650001200000004";
        System.err.println(text.length());
        super.createPDF(pdfDocument -> {
            final TableiText table = pdfDocument.createTable(4);
            table.addCell(text, new CellFormat(new BarcodeCell(BarcodeType.QRCODE, 100, 100)));
            table.completeRow();
            table.appendMe();
        });
    }

    //    @Test
    //    public void test_Basic() {
    //        this.text = "http://google.com";
    //        this.setContentGenerater(new SampleContent() {
    //            @Override
    //            public void generatePDFContent(final PDFDocument pdfDocument) {
    //
    //                final PdfContentByte contentByte = pdfDocument.getPdfWriter().getDirectContent();
    //                final QRCodeDrawer barcode = new QRCodeDrawer(Coordinate.CM_TL);
    //                // 大小會自動擴增
    //                drarBarcode(contentByte, barcode, new PointF(3, 4), 5, 0.8f, true);
    //                drarBarcode(contentByte, barcode, new PointF(8.5f, 4), 5, 0.8f, false);
    //                //
    //                drarBarcode(contentByte, barcode, new PointF(3, 8), 5, 1.8f, true);
    //                drarBarcode(contentByte, barcode, new PointF(8.5f, 8), 5, 1.8f, false);
    //            }
    //        });
    //        this.generateTestReport();
    //    }
    //
    //    @Test
    //    public void test_Table() {
    //        this.setPageSize(PageSize.A4.rotate());
    //        this.setContentGenerater(new SampleContent() {
    //            @Override
    //            public void generatePDFContent(final PDFDocument pdfDocument) {
    //                try {
    //                    final BarcodeSupport barCode = new QRCodeDrawer(Coordinate.CM_TL);
    //
    //                    final TableiText table = pdfDocument.createTable(100, 2);
    //                    table.getDefaultFormat().setMinHeightInCM(5f);
    //                    table.addCell("TEST");
    //                    table.addCell(barCode.createCell(table, "TEST", 3, 4, false));
    //
    //                    table.addCell("TEST2");
    //                    table.addCell(barCode.createCell(table, "TEST2", 3, 4, false));
    //
    //                    table.addCell("TEST3");
    //                    table.addCell(barCode.createCell(table, "TEST2", 3, 4.5f, false));
    //
    //                    table.appendMe();
    //                } catch (final DocumentException e) {
    //                    throw new ReportException(e);
    //                }
    //            }
    //        });
    //        this.generateTestReport();
    //    }

}
