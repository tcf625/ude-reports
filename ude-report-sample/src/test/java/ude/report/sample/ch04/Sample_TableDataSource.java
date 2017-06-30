/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04;

import org.junit.Test;

import com.iisigroup.ude.report.BlankTemplateSupplier;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;
import ude.report.sample.AbstractSampleReport;
import ude.report.sample.SampleVO;
import ude.report.sample.SampleVO_OM;

public class Sample_TableDataSource extends AbstractSample {

    final SampleVO testData100 = SampleVO_OM.testData(100);

    final SampleVO testData101 = SampleVO_OM.testData(101);

    private BlankTemplateSupplier<AbstractSampleReport> blankTemplateSupplier = () -> new AbstractSampleReport(PageSize.A4) {
        @Override
        public void generatePDFContent(final PDFDocument pdfDocument) {
            final TableiText table = pdfDocument.createTable(100, 6);
            table.setDataObject(Sample_TableDataSource.this.testData100);
            // final CellFormat defaultFormat = table.getDefaultFormat();
            // defaultFormat.setBorder(Border.N);
            table.addCell(dataSource("text1"));
            table.addCell(dataSource("text2"));
            table.addCell(dataSource("text3"));
            table.addCell(dataSource("text4"));
            table.addCell(dataSource("value1"));
            table.addCell(dataSource("value2"));
            table.setDataObject(Sample_TableDataSource.this.testData101);
            table.addCell(dataSource("text1"));
            table.addCell(dataSource("text2"));
            table.addCell(dataSource("text3"));
            table.addCell(dataSource("text4"));
            table.appendMe();
        };
    };

    @Test
    public void test_basicTable() {
        super.createPDF(this.blankTemplateSupplier.create());
    }

    @Test
    public void test_basicTable_showSource() {
        super.createPDF(this.blankTemplateSupplier.showSource());
    }

}
