/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch06;

import org.junit.Test;

import com.iisigroup.ude.report.data.source.BeanProperty;
import com.iisigroup.ude.report.excel.ExcelSheet;
import com.iisigroup.ude.report.excel.table.transfer.ExcelTableTransfer;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.table.transfer.PDFTableTransfer;
import com.iisigroup.ude.report.table.NestTableMetadata;
import com.iisigroup.ude.report.table.format.Border;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;
import ude.report.sample.SampleVO_OM;

public class Sample_NestTableMetadata extends AbstractSample {

    public void setPageSizeA5R(final PDFDocument pdfDocument) {
        pdfDocument.setupPageSize(PageSize.A5.rotate());
    }

    //####################################################################
    //## [Method] sub-block : 基本標題 -
    //####################################################################

    @Test
    public void test_basic() {
        final NestTableMetadata metadata = new NestTableMetadata();
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));
        metadata.append("項目", new BeanProperty("text3"));
        metadata.subTable(subTable -> {
            subTable.append("值1");
            subTable.append("值2");
            subTable.nextRow();
            subTable.append("值3");
            subTable.append("值4");
            subTable.append("值5");
            subTable.nextRow();
            subTable.append("值A");
            subTable.append("值B");
            subTable.append("值C");
            subTable.append("值D");
        });
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());
        });
        super.createExcel(excelDocument -> {
            final ExcelSheet<?> sheet = excelDocument.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

    //####################################################################
    //## [Method] sub-block : BORDER
    //####################################################################

    @Test
    public void test_noItemBorder() {
        final NestTableMetadata metadata = new NestTableMetadata();
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));
        metadata.append("項目", new BeanProperty("text3"));
        metadata.subTable(subTable -> {
            subTable.append("值1");
            subTable.append("值2");
            subTable.nextRow();
            subTable.append("值3");
            subTable.append("值4");
            subTable.append("值5");
            subTable.nextRow();
            subTable.append("值A");
            subTable.append("值B");
            subTable.append("值C");
            subTable.append("值D");
        });
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            metadata.setBorder(Border.B);
            transfer.transTable(SampleVO_OM.testDataset(100));

            metadata.setBorderWidth(0F);
            transfer.transTable(SampleVO_OM.testDataset(100));
        });
        super.createExcel(excelDocument -> {
            final ExcelSheet<?> sheet = excelDocument.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset(100));
        });
    }

    @Test
    public void test_noContentBorder0() {
        final NestTableMetadata metadata = new NestTableMetadata();
        metadata.getDefaultFormat().setBorder(Border.N);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));
        metadata.append("項目", new BeanProperty("text3"));
        metadata.subTable(subTable -> {
            subTable.append("值1");
            subTable.append("值2");
            subTable.nextRow();
            subTable.append("值3");
            subTable.append("值4");
            subTable.append("值5");
            subTable.nextRow();
            subTable.append("值A");
            subTable.append("值B");
            subTable.append("值C");
            subTable.append("值D");
        });
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset(100));
            metadata.setBorder(Border.B);
            metadata.setBorderWidth(0.25f);
            transfer.transTable(SampleVO_OM.testDataset(100));
        });
        super.createExcel(excelDocument -> {
            final ExcelSheet<?> sheet = excelDocument.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset(100));
        });
    }

    @Test
    public void test_noContentBorder() {
        final NestTableMetadata metadata = new NestTableMetadata();
        metadata.getDefaultFormat().setBorder(Border.N);
        metadata.getDefaultHeaderFormat().setBorder(Border.B);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));
        metadata.append("項目", new BeanProperty("text3"));
        metadata.subTable(subTable -> {
            subTable.append("值1");
            subTable.append("值2");
            subTable.nextRow();
            subTable.append("值3");
            subTable.append("值4");
            subTable.append("值5");
            subTable.nextRow();
            subTable.append("值A");
            subTable.append("值B");
            subTable.append("值C");
            subTable.append("值D");
        });
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset(100));

            metadata.setBorder(Border.B);
            metadata.setBorderWidth(0.25f);
            transfer.transTable(SampleVO_OM.testDataset(100));
        });
        super.createExcel(excelDocument -> {
            final ExcelSheet<?> sheet = excelDocument.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset(100));
        });
    }

}
