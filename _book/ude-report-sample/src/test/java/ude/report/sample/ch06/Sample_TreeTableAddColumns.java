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
import com.iisigroup.ude.report.table.format.Border;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
import com.iisigroup.ude.report.table.metadata.TreeColumnMetadata;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;
import ude.report.sample.SampleVO_OM;

public class Sample_TreeTableAddColumns extends AbstractSample {

    public void setPageSizeA5R(final PDFDocument pdfDocument) {
        pdfDocument.setupPageSize(PageSize.A5.rotate());
    }

    //####################################################################
    //## [Method] sub-block :
    //####################################################################

    @Test
    public void test_afterAndBefore() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));

        // !
        final TreeColumnMetadata columnValue = metadata.append("值1", new BeanProperty("value1"));
        metadata.append("值2", new BeanProperty("value2"));
        columnValue.before("值1前面");
        columnValue.after("值1後面");

        // !
        final TreeColumnMetadata columnGroup = metadata.append("資料集");
        columnGroup.append("值3", new BeanProperty("value3"));
        columnGroup.append("值4", new BeanProperty("value4"));
        columnGroup.before("資料集前面");
        columnGroup.after("資料集後面");

        metadata.append("值5");

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
    //## [Method] sub-block : columnGroup
    //####################################################################

    @Test
    public void test_columnGroup() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));

        // !
        final TreeColumnMetadata columnGroup = metadata.append("資料集");
        columnGroup.append("值1", new BeanProperty("value1"));
        columnGroup.append("值2", new BeanProperty("value2"));
        columnGroup.prepend("資料集第1項");
        columnGroup.appendAt("資料集第3項", 2);
        metadata.append("值3");

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
    //## [Method] sub-block : columnGroup
    //####################################################################

    @Test
    public void test_noTitle1() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.setWidthPercentage(80);
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append(new BeanProperty("value1"));
        metadata.append(new BeanProperty("value2"));
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

    @Test
    public void test_noTitle2() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.setWidthPercentage(80);
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);

        final TreeColumnMetadata areaGroup = metadata.append("年度、地區");
        areaGroup.append(new BeanProperty("text1"));
        areaGroup.append("地區", new BeanProperty("text2"));
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

    @Test
    public void test_splitColumn() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.setWidthPercentage(80);
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);

        metadata.append("年度、地區", areaGroup -> {
            areaGroup.append(new BeanProperty("text1"));
            areaGroup.append(new BeanProperty("text2"));
        });
        metadata.append("資料集", columnGroup -> {
            columnGroup.append(new BeanProperty("value1")).getContentFormat().setBorder(Border.NR);
            columnGroup.append(new BeanProperty("value2")).getContentFormat().setBorder(Border.TB);
            columnGroup.append(new BeanProperty("value3")).getContentFormat().setBorder(Border.NL);
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

}
