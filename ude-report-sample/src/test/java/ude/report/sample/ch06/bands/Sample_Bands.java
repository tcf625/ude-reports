/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch06.bands;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.iisigroup.ude.core.testkit.fixture.TestData;
import com.iisigroup.ude.core.testkit.fixture.UdeTestFixture;
import com.iisigroup.ude.report.data.source.BeanProperty;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.table.transfer.PDFTableTransfer;
import com.iisigroup.ude.report.table.AbstractTableMetadata;
import com.iisigroup.ude.report.table.NestTableMetadata;
import com.iisigroup.ude.report.table.TreeTableMetadata;
import com.iisigroup.ude.report.table.band.BandType;
import com.iisigroup.ude.report.table.band.NestBlock;
import com.iisigroup.ude.report.table.band.TreeBlock;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;
import ude.report.sample.SampleVO_OM;

@RunWith(UdeTestFixture.class)
public class Sample_Bands extends AbstractSample {

    private void setPageSizeA5R(final PDFDocument pdfDocument) {
        pdfDocument.setPageSize(PageSize.A5.rotate());
        pdfDocument.writeText(this.testName.getMethodName());
    }

    private void basicTable(final AbstractTableMetadata<?> metadata) {
        metadata.append("年度", new BeanProperty("text1"));
        metadata.append("地區", new BeanProperty("text2"));
        metadata.append("項目", new BeanProperty("text3"));
        metadata.append("值1", new BeanProperty("value1"));
        metadata.append("值2", new BeanProperty("value2"));
    }

    private TreeBlock treeBand() {
        final TreeBlock block = new TreeBlock();
        block.append("T1");
        block.append("T2");
        return block;
    }

    private NestBlock nestBand() {
        final NestBlock block = new NestBlock();
        block.append("N1");
        block.append("N2");
        block.nextRow();
        block.append("N3");
        return block;
    }

    //####################################################################
    //## [Method] sub-block : 基本標題 - TableHeader : TreeBlock
    //####################################################################

    @TestData("TableHeader")
    public void test_TableHeader_T2T(final BandType bandType) {
        final AbstractTableMetadata<?> metadata = new TreeTableMetadata();
        metadata.addBand(bandType, this::treeBand);
        basicTable(metadata);
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

    @Test
    public void test_TableHeader_T2N() {
        final AbstractTableMetadata<?> metadata = new NestTableMetadata();
        metadata.addBand(BandType.TableHeader, this::treeBand);
        basicTable(metadata);
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

    //####################################################################
    //## [Method] sub-block : 基本標題 - TableHeader NestBlock
    //####################################################################

    @Test
    public void test_TableHeader_N2T() {
        final AbstractTableMetadata<?> metadata = new TreeTableMetadata();
        metadata.addBand(BandType.TableHeader, this::nestBand);
        basicTable(metadata);
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

    @Test
    public void test_TableHeader_N2N() {
        final AbstractTableMetadata<?> metadata = new NestTableMetadata();
        metadata.addBand(BandType.TableHeader, this::nestBand);
        basicTable(metadata);
        super.createPDF(this::setPageSizeA5R, pdfDocument -> {
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, metadata);
            transfer.transTable(SampleVO_OM.testDataset());
        });
    }

}
