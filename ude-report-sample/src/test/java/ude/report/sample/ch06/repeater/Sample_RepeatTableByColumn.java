/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch06.repeater;

import java.util.Collections;

import org.junit.Test;

import com.iisigroup.ude.report.data.source.BeanProperty;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.table.transfer.PDFDuplicatedTableTransfer;
import com.iisigroup.ude.report.table.TreeTableMetadata;
import com.iisigroup.ude.report.table.band.BandType;
import com.iisigroup.ude.report.table.band.TreeBlock;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;

import ude.report.sample.SampleVO_OM;

public class Sample_RepeatTableByColumn extends Sample_BaseRepeatTable {

    @Override
    protected PDFDuplicatedTableTransfer createPDFTransfer(final PDFDocument pdfDocument, final TreeTableMetadata metadata) {
        final PDFDuplicatedTableTransfer transfer = PDFDuplicatedTableTransfer.byColumn(pdfDocument, metadata, 3);
        return transfer;
    }

    //####################################################################
    //## [Method] sub-block : 基本
    //####################################################################

    @Test
    public void test_basic() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("值1", new BeanProperty("value1"));
        metadata.append("值2", new BeanProperty("value2"));
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFDuplicatedTableTransfer transfer = createPDFTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset().subList(0, 70));
            outputNextTable(pdfDocument);
        });
    }

    @Test
    public void test_byColumn_noData() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.setSpacingAfter(20);
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("值1", new BeanProperty("value1"));
        metadata.append("值2", new BeanProperty("value2"));
        metadata.addBand(BandType.NoData, () -> {
            final TreeBlock treeBlock = new TreeBlock();
            treeBlock.append("NODATA");
            return treeBlock;
        });

        super.createPDF(this::setPageSizeA4, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFDuplicatedTableTransfer transfer = createPDFTransfer(pdfDocument, metadata);
            transfer.transTable(Collections.emptyList());
            transfer.transTable(SampleVO_OM.testDataset().subList(0, 1));
            transfer.transTable(SampleVO_OM.testDataset().subList(0, 2));
            transfer.transTable(SampleVO_OM.testDataset().subList(0, 3));
            transfer.transTable(SampleVO_OM.testDataset().subList(0, 4));
            transfer.transTable(SampleVO_OM.testDataset().subList(0, 5));
        });
    }

    @Test
    public void test_byColumn_afterSpacing() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("值1", new BeanProperty("value1"));
        metadata.append("值2", new BeanProperty("value2"));
        metadata.setSpacingAfter(8F);
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFDuplicatedTableTransfer transfer = createPDFTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset().subList(0, 70));
            outputNextTable(pdfDocument);
        });
    }

    //####################################################################
    //## [Method] sub-block : Bands
    //####################################################################

    @Test
    public void test_Bands() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.setSpacingAfter(20);
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("值1", new BeanProperty("value1"));
        metadata.append("值2", new BeanProperty("value2"));

        this.appendBand(metadata, BandType.TableHeader);
        this.appendBand(metadata, BandType.TableHeaderRepeat);
        this.appendBand(metadata, BandType.TableFooter);
        this.appendBand(metadata, BandType.TableFooterRepeat);

        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFDuplicatedTableTransfer transfer = createPDFTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset(120));
        });
    }

    @Test
    public void test_Bands_notAutoHeight() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.setSpacingAfter(20);
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("值1", new BeanProperty("value1"));
        metadata.append("值2", new BeanProperty("value2"));

        this.appendBand(metadata, BandType.TableHeader);
        this.appendBand(metadata, BandType.TableHeaderRepeat);
        this.appendBand(metadata, BandType.TableFooter);
        this.appendBand(metadata, BandType.TableFooterRepeat);

        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFDuplicatedTableTransfer transfer = createPDFTransfer(pdfDocument, metadata);
            transfer.setAutoHeight(false);
            transfer.transTable(SampleVO_OM.testDataset(120));
        });
    }

    //####################################################################
    //## [Method] sub-block : 外觀
    //####################################################################

    @Test
    public void test_byColumn_noGap() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("值1", new BeanProperty("value1"));
        metadata.append("值2", new BeanProperty("value2"));
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFDuplicatedTableTransfer transfer = createPDFTransfer(pdfDocument, metadata);
            transfer.setGapWidth(0);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

    @Test
    public void test_byColumn_border() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("值1", new BeanProperty("value1"));
        metadata.append("值2", new BeanProperty("value2"));
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            pdfDocument.writeText("基本表格，標題部分每頁重複顯示");
            final PDFDuplicatedTableTransfer transfer = createPDFTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

}
