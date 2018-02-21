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
import com.iisigroup.ude.report.table.TreeTableMetadata;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
import com.iisigroup.ude.report.table.format.DocumentAlign;
import com.iisigroup.ude.report.table.metadata.TreeColumnMetadata;
import com.iisigroup.ude.report.utils.LengthUnit;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;
import ude.report.sample.SampleVO_OM;

public class Sample_TreeTableMetadata extends AbstractSample {

    public void setPageSizeA5R(final PDFDocument pdfDocument) {
        pdfDocument.setupPageSize(PageSize.A5.rotate());
    }

    //####################################################################
    //## [Method] sub-block : 基本標題 -
    //####################################################################

    @Test
    public void test_basicTable() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));
        metadata.append("項目", new BeanProperty("text3"));
        metadata.append("值1", new BeanProperty("value1"));
        metadata.append("值2", new BeanProperty("value2"));
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());
        });
        super.createExcel(excelDocument -> {
            final ExcelSheet<?> sheet = excelDocument.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

    @Test
    public void test_basicCaption() {
        final TreeTableMetadata metadata = new TreeTableMetadata("標題");
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));
        metadata.append("項目", new BeanProperty("text3"));
        metadata.append("值1", new BeanProperty("value1"));
        metadata.append("值2", new BeanProperty("value2"));
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("建立TableMetadata時可傳入Caption文字。標題部分會每頁重複顯示");
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
    //## [Method] sub-block : 多層標題
    //####################################################################

    @Test
    public void test_columnGroup() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));
        metadata.append("項目", new BeanProperty("text3"));
        metadata.append("資料內容", column -> {
            column.append("值1", new BeanProperty("value1"));
            column.append("值2", new BeanProperty("value2"));
        });

        final TreeColumnMetadata column = metadata.append("資料內容");
        column.append("值1", new BeanProperty("value1"));
        column.append("值2", new BeanProperty("value2"));

        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());
        });
        super.createExcel(excelDocument -> {
            final ExcelSheet<?> sheet = excelDocument.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

    @Test
    public void test_columnGroup2() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));
        metadata.append("所有資料", columnGroup -> {
            columnGroup.append("項目", new BeanProperty("text3"));
            columnGroup.append("資料內容", column -> {
                column.append("值1", new BeanProperty("value1"));
                column.append("值2", new BeanProperty("value2"));
            });
        });
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
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
    //## [Method] sub-block : Widths
    //####################################################################

    @Test
    public void test_widths() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"), 10);
        metadata.append("地區", new BeanProperty("text2"), 20);
        metadata.append("項目", new BeanProperty("text3"), 30);
        metadata.append("資料內容", column -> {
            column.append("值1", new BeanProperty("value1"), 10);
            column.append("值2", new BeanProperty("value2"), 10);
        });
        metadata.append("資料內容", 10, column -> {
            column.append("值3", new BeanProperty("value1"), 10);
            column.append("值4", new BeanProperty("value2"), 10);
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
    //## [Method] sub-block : Widths
    //####################################################################

    @Test
    public void test_widthsUnit() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"), 10);
        metadata.append("地區", new BeanProperty("text2"), 20);
        metadata.append("項目", new BeanProperty("text3"), 30);
        metadata.append("資料內容", column -> {
            column.append("值1", new BeanProperty("value1"), 10);
            column.append("值2", new BeanProperty("value2"), 10);
        });
        metadata.append("資料內容", 10, column -> {
            column.append("值3", new BeanProperty("value1"), 10);
            column.append("值4", new BeanProperty("value2"), 10);
        });
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {

            metadata.setWidthUnit(LengthUnit.MM); // TODO FIX IT
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());

            pdfDocument.newPage();
            metadata.setHorizontalAlignment(DocumentAlign.LEFT);
            transfer.transTable(SampleVO_OM.testDataset());
        });
        super.createExcel(excelDocument -> {
            metadata.setWidthUnit(LengthUnit.ExcelPoint);
            final ExcelSheet<?> sheet = excelDocument.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

}
