/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch06;

import java.util.List;

import org.junit.Test;

import com.iisigroup.ude.report.data.source.BeanProperty;
import com.iisigroup.ude.report.excel.ExcelSheet;
import com.iisigroup.ude.report.excel.table.transfer.ExcelTableTransfer;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.table.transfer.PDFTableTransfer;
import com.iisigroup.ude.report.table.TreeTableMetadata;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
import com.iisigroup.ude.report.table.grouping.GroupingFunction;
import com.iisigroup.ude.report.table.grouping.GroupingInfo;
import com.iisigroup.ude.report.table.grouping.GroupingInfo.Position;
import com.iisigroup.ude.report.table.metadata.TreeColumnMetadata;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;
import ude.report.sample.SampleVO;
import ude.report.sample.SampleVO_OM;

public class Sample_Grouping extends AbstractSample {

    public void setPageSizeA5R(final PDFDocument pdfDocument) {
        pdfDocument.setPageSize(PageSize.A5.rotate());
    }
    //####################################################################
    //## [Method] sub-block : 基本 GROUP
    //####################################################################

    @Test
    public void test_basic0() {
        final TreeTableMetadata tableMetadata = new TreeTableMetadata();
        tableMetadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        tableMetadata.createGroupingInfo("總合", Position.AFTER); // ! 宣告使用 Grouping 機制
        tableMetadata.append("年度", new BeanProperty("text1"));
        tableMetadata.append("地區", new BeanProperty("text2"));
        tableMetadata.append("項目", new BeanProperty("text3")).setGroupFunction(GroupingFunction.COUNT);
        tableMetadata.append("值1", new BeanProperty("value1")).setGroupFunction(GroupingFunction.SUM);
        tableMetadata.append("值2", new BeanProperty("value2")).setGroupFunction(GroupingFunction.SUM);

        final List<SampleVO> testDataset = SampleVO_OM.testDataset(101);
        super.createPDF(pdfDocument -> {
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, tableMetadata);
            transfer.transTable(testDataset);
        });
        super.createExcel(content -> {
            final ExcelSheet<?> sheet = content.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(tableMetadata, sheet);
            transfer.transTable(testDataset);
        });
    }

    @Test
    public void test_basic1() {
        final TreeTableMetadata tableMetadata = new TreeTableMetadata();
        tableMetadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        final GroupingInfo groupingInfo = tableMetadata.createGroupingInfo("總合", Position.AFTER);
        tableMetadata.append("年度", new BeanProperty("text1"), yearColumn -> {
            yearColumn.setGroupFunction(GroupingFunction.HEADER);
            groupingInfo.addGroupLevel("小計", yearColumn);
        });
        tableMetadata.append("地區", new BeanProperty("text2"));
        tableMetadata.append("項目", new BeanProperty("text3")).setGroupFunction(GroupingFunction.COUNT);
        tableMetadata.append("值1", new BeanProperty("value1")).setGroupFunction(GroupingFunction.SUM);
        tableMetadata.append("值2", new BeanProperty("value2")).setGroupFunction(GroupingFunction.SUM);

        final List<SampleVO> testDataset = SampleVO_OM.testDataset(101);
        super.createPDF(pdfDocument -> {
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, tableMetadata);
            transfer.transTable(testDataset);
        });
        super.createExcel(content -> {
            final ExcelSheet<?> sheet = content.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(tableMetadata, sheet);
            transfer.transTable(testDataset);
        });
    }

    @Test
    public void test_basicGrouping() {
        final TreeTableMetadata tableMetadata = new TreeTableMetadata();
        tableMetadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        final TreeColumnMetadata yearColumn = tableMetadata.append("年度", new BeanProperty("text1"));
        tableMetadata.append("地區", new BeanProperty("text2"));
        tableMetadata.append("項目", new BeanProperty("text3")).setGroupFunction(GroupingFunction.COUNT);
        tableMetadata.append("值1", new BeanProperty("value1")).setGroupFunction(GroupingFunction.SUM);
        tableMetadata.append("值2", new BeanProperty("value2")).setGroupFunction(GroupingFunction.SUM);

        final GroupingInfo groupingInfo = tableMetadata.createGroupingInfo("總合", Position.AFTER);
        yearColumn.setGroupFunction(GroupingFunction.HEADER);
        groupingInfo.addGroupLevel("小計", yearColumn);

        final List<SampleVO> testDataset = SampleVO_OM.testDataset(101);
        super.createPDF(pdfDocument -> {
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, tableMetadata);
            transfer.transTable(testDataset);
        });
        super.createExcel(content -> {
            final ExcelSheet<?> sheet = content.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(tableMetadata, sheet);
            transfer.transTable(testDataset);
        });
    }

}
