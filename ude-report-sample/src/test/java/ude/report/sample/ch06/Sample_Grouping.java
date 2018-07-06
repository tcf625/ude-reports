/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch06;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.iisigroup.ude.report.data.op.number.NumberValue;
import com.iisigroup.ude.report.data.source.BeanProperty;
import com.iisigroup.ude.report.data.source.Counter;
import com.iisigroup.ude.report.excel.ExcelSheet;
import com.iisigroup.ude.report.excel.table.transfer.ExcelTableTransfer;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.table.transfer.PDFTableTransfer;
import com.iisigroup.ude.report.table.TreeTableMetadata;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
import com.iisigroup.ude.report.table.grouping.GroupingFunction;
import com.iisigroup.ude.report.table.grouping.GroupingInfo;
import com.iisigroup.ude.report.table.grouping.GroupingInfo.Position;
import com.iisigroup.ude.report.table.grouping.GroupingLevel.TitleMode;
import com.iisigroup.ude.report.table.metadata.TreeColumnMetadata;
import com.iisigroup.ude.util.lang.UdeRegexUtils;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.parser.PdfTextExtractor;

import ude.report.sample.AbstractSample;
import ude.report.sample.SampleVO;
import ude.report.sample.SampleVO_OM;

public class Sample_Grouping extends AbstractSample {

    public void setPageSizeA5R(final PDFDocument pdfDocument) {
        pdfDocument.setupPageSize(PageSize.A5.rotate());
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

    //####################################################################
    //## [Method] sub-block : 基本 GROUP
    //####################################################################

    @Test
    public void test_basic_2_level() {

        final TreeTableMetadata tableMetadata = new TreeTableMetadata();
        tableMetadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);

        final GroupingInfo groupingInfo = tableMetadata.createGroupingInfo("總計", Position.BOTH);

        tableMetadata.append("年度", new BeanProperty("text1"), yearColumn -> {
            yearColumn.setGroupFunction(GroupingFunction.HEADER);
            groupingInfo.addGroupLevel(yearColumn)//
                    .setTitleMode(TitleMode.THIS) //
                    .setMergedRows(true) //
                    .setSuffix(" 小計");
        });

        tableMetadata.append("地區", new BeanProperty("text2"), areaColumn -> {
            groupingInfo.addGroupLevel(areaColumn)//
                    .setMergedRowsLevel(2)//
                    .setSuffix(" 計") //
            ;
        });

        // !!
        final TreeColumnMetadata C_0 = tableMetadata.append("項目", new BeanProperty("text3"));
        final TreeColumnMetadata C_A = tableMetadata.append("A:COUNT", new BeanProperty("value1"));
        final TreeColumnMetadata C_B = tableMetadata.append("B:值1-SUM", new BeanProperty("value1"));
        final TreeColumnMetadata C_C = tableMetadata.append("C:值2-SUM", new BeanProperty("value2"));
        final TreeColumnMetadata C_D = tableMetadata.append("D:值3-SUM", new BeanProperty("value3"));
        C_0.setGroupFunction(GroupingFunction.COUNT);
        C_A.setGroupFunction(GroupingFunction.COUNT);
        C_B.setGroupFunction(GroupingFunction.SUM);
        C_C.setGroupFunction(GroupingFunction.SUM);
        C_D.setGroupFunction(GroupingFunction.SUM);

        // !! 水平加總
        final TreeColumnMetadata C_AB = tableMetadata.append("A+B", new NumberValue(C_A).add(C_B));
        final TreeColumnMetadata C_BC = tableMetadata.append("B+C", new NumberValue(C_B).add(C_C));
        final TreeColumnMetadata C_ABBC = tableMetadata.append("(A+B)+(B+C)", new NumberValue(C_AB).add(C_BC));
        C_ABBC.setGroupFunction(GroupingFunction.SUM);
        C_BC.setGroupFunction(GroupingFunction.SUM);
        C_AB.setGroupFunction(GroupingFunction.SUM);

        // !! Counter
        final TreeColumnMetadata C_CCC = tableMetadata.append("Counter", new Counter(10));
        final TreeColumnMetadata C_CC1 = tableMetadata.appendAt("Counter+1", new NumberValue(C_CCC).add(1), 1F, 7);
        C_CCC.setGroupFunction(GroupingFunction.SUM);
        C_CC1.setGroupFunction(GroupingFunction.SUM);

        final List<SampleVO> testDataset = SampleVO_OM.testDataset(101);
        final File pdf = super.createPDF(pdfDocument -> {
            pdfDocument.setupPageSize(PageSize.A3.rotate());
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, tableMetadata);
            transfer.transTable(testDataset);
        });

        assertPdfWith(pdf, reader -> {
            final PdfTextExtractor extractor = new PdfTextExtractor(reader);
            final String textFromPage = extractor.getTextFromPage(1);
            assertEquals(24, UdeRegexUtils.count(textFromPage, Pattern.compile("類\\s+1\\s+")));
            for (int i = 1; i <= 24; i++) {
                final String pattern = StringUtils.join(Arrays.asList(2, i + 1, i + 3), "\\s+");
                assertEquals(pattern, 1, UdeRegexUtils.count(textFromPage, Pattern.compile(pattern)));
            }
        });
        super.createExcel(content -> {
            final ExcelSheet<?> sheet = content.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(tableMetadata, sheet);
            transfer.transTable(testDataset);
        });
    }

    //####################################################################
    //## [Method] sub-block : GROUP 列水平公式
    //####################################################################

    @Test
    public void test_basic_2_groupingLevel_expression() {

        final TreeTableMetadata tableMetadata = new TreeTableMetadata();
        tableMetadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        final GroupingInfo groupingInfo = tableMetadata.createGroupingInfo("總計", Position.AFTER);
        tableMetadata.append("年度", new BeanProperty("text1"), yearColumn -> {
            yearColumn.setGroupFunction(GroupingFunction.HEADER);
            groupingInfo.addGroupLevel(yearColumn).setSuffix(" 小計");
        });
        tableMetadata.append("地區", new BeanProperty("text2"), areaColumn -> {
            groupingInfo.addGroupLevel(areaColumn).setSuffix(" 計");
        });

        // !!
        final TreeColumnMetadata C_0 = tableMetadata.append("項目", new BeanProperty("text3"));
        final TreeColumnMetadata C_A = tableMetadata.append("A:COUNT", new BeanProperty("value1"));
        final TreeColumnMetadata C_B = tableMetadata.append("B:值1-SUM", new BeanProperty("value1"));
        final TreeColumnMetadata C_C = tableMetadata.append("C:值2-SUM", new BeanProperty("value2"));
        final TreeColumnMetadata C_D = tableMetadata.append("D:值3-SUM", new BeanProperty("value3"));
        C_0.setGroupFunction(GroupingFunction.COUNT);
        C_A.setGroupFunction(GroupingFunction.COUNT);
        C_B.setGroupFunction(GroupingFunction.SUM);
        C_C.setGroupFunction(GroupingFunction.SUM);
        C_D.setGroupFunction(GroupingFunction.SUM);

        // !! 水平加總
        final TreeColumnMetadata C_AB = tableMetadata.append("A+B");
        final TreeColumnMetadata C_BC = tableMetadata.append("B+C");
        final TreeColumnMetadata C_ABBC = tableMetadata.append("(A+B)+(B+C)");

        C_AB.setGroupFunction(new NumberValue(C_A).add(C_B));
        C_BC.setGroupFunction(new NumberValue(C_B).add(C_C));
        C_ABBC.setGroupFunction(new NumberValue(C_AB).add(C_BC));

        final List<SampleVO> testDataset = SampleVO_OM.testDataset(101);
        final File pdf = super.createPDF(pdfDocument -> {
            pdfDocument.setupPageSize(PageSize.A3.rotate());
            final PDFTableTransfer transfer = new PDFTableTransfer(pdfDocument, tableMetadata);
            transfer.transTable(testDataset);
        });

        assertPdfWith(pdf, reader -> {
            final PdfTextExtractor extractor = new PdfTextExtractor(reader);
            @SuppressWarnings("unused")
            final String textFromPage = extractor.getTextFromPage(1);
        });
        super.createExcel(content -> {
            final ExcelSheet<?> sheet = content.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(tableMetadata, sheet);
            transfer.transTable(testDataset);
        });
    }

    //####################################################################
    //## [Method] sub-block : 基本 GROUP
    //####################################################################

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
        groupingInfo.addGroupLevel(yearColumn).setSuffix("小計");

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
