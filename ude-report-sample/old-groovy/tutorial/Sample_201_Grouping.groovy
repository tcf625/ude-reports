/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import org.apache.commons.lang3.StringUtils
import org.junit.Test
import org.mockito.Mockito

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.transfer.PDFNormalTableTransfer
import com.iisigroup.ude.report.table.NormalTableMetadata
import com.iisigroup.ude.report.table.format.CellFormat.AlignH
import com.iisigroup.ude.report.table.grouping.GroupMode
import com.iisigroup.ude.report.table.grouping.GroupingFunction
import com.iisigroup.ude.report.table.grouping.GroupingInfo
import com.iisigroup.ude.report.table.grouping.GroupingLevel
import com.iisigroup.ude.report.table.grouping.GroupingInfo.Position
import com.iisigroup.ude.report.table.grouping.GroupingLevel.TitleMode
import com.iisigroup.ude.report.table.internal.ColumnMetadata
import com.iisigroup.ude.report.table.metadata.BaseColumnMetadata
import com.iisigroup.ude.report.table.metadata.TreeColumnMetadata

/**
 *
 */
class Sample_201_Grouping extends AbstractITextSampleGroovy {


    private static TreeColumnMetadata find(List<BaseColumnMetadata> dataColumns, String name) {
        for (BaseColumnMetadata columnMetadata : dataColumns) {
            final String columnName = columnMetadata.getName();
            if (StringUtils.equals(name, columnName)) {
                return columnMetadata;
            }
        }
        println "UNFIND " + name
        return Mockito.mock(BaseColumnMetadata.class);
    }


    //####################################################################
    //## [Method] sub-block : Grouping Table
    //####################################################################

    @Test
    public void test_GROUP_BRFORE() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->
            final NormalTableMetadata tableMeta = SAMPLE_METADATA.BASIC_1.get();
            List<ColumnMetadata> dataColumns = tableMeta.getDataColumns();
            for (ColumnMetadata columnMetadata : dataColumns) {
                final String name = columnMetadata.getName();
                if (name.matches("值.*\\d")) {
                    columnMetadata.setGroupFunction(GroupingFunction.SUM);
                }
                if (name.startsWith("合計值")) {
                    columnMetadata.setGroupFunction(GroupingFunction.SUM);
                }
            }
            GroupingInfo groupingInfo = tableMeta.createGroupingInfo("總計：");
            GroupingLevel groupingLV1 = groupingInfo.addGroupLevel("小計：", find(dataColumns, "年度"));
            GroupingLevel groupingLV2 = groupingInfo.addGroupLevel("小計：", find(dataColumns, "地區"));
            groupingInfo.getHeaderFormat().setFontBold(true).setPadding(3f);
            groupingLV1.getGroupHeaderFormat().setFontBold(true).setPadding(3f).setPaddingL(15f);
            groupingLV2.getGroupHeaderFormat().setFontBold(true).setPadding(3f).setPaddingL(15f);
            final PDFNormalTableTransfer createTransfer = new PDFNormalTableTransfer(pdfDocument, tableMeta);
            createTransfer.transTable(SAMPLE_VO.mockDataset().subList(0, 10));
        }

    }

    @Test
    public void test_GROUP_BRFORE_HIDE_GROUPING() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->

            pdfDocument.writeText("前兩項欄位寬度被設為0，所以不會顯示")
            pdfDocument.writeText("  tableMeta.newSubColumn(\"年度\", new CellBeanProperty(\"text1\"), 0)");
            pdfDocument.writeText("  tableMeta.newSubColumn(\"地區\", new CellBeanProperty(\"text2\"), 0)");
            pdfDocument.writeText("")

            final NormalTableMetadata tableMeta = SAMPLE_METADATA.BASIC_1_SIZE0_1ST.get();
            List<ColumnMetadata> dataColumns = tableMeta.getDataColumns();
            for (ColumnMetadata columnMetadata : dataColumns) {
                final String name = columnMetadata.getName();
                if (name.matches("值\\d")) {
                    columnMetadata.setGroupFunction(GroupingFunction.SUM);
                }
                if (name.startsWith("合計值")) {
                    columnMetadata.setGroupFunction(GroupingFunction.SUM);
                }
            }
            find(dataColumns, "項目").setGroupFunction(GroupingFunction.HEADER);
            GroupingInfo groupingInfo = tableMeta.createGroupingInfo("總計：");
            GroupingLevel groupingLV1 = groupingInfo.addGroupLevel("小計：", find(dataColumns, "年度"));
            GroupingLevel groupingLV2 = groupingInfo.addGroupLevel("小計：", find(dataColumns, "地區"));
            groupingInfo.getHeaderFormat().setFontBold(true).setPadding(3f);
            groupingLV1.getGroupHeaderFormat().setFontBold(true).setPadding(3f).setPaddingL(15f);
            groupingLV2.getGroupHeaderFormat().setFontBold(true).setPadding(3f).setPaddingL(15f);
            final PDFNormalTableTransfer createTransfer = new PDFNormalTableTransfer(pdfDocument, tableMeta);
            createTransfer.transTable(SAMPLE_VO.mockDataset());
        }

    }

    @Test
    public void test_GROUP_AFTER() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->
            final NormalTableMetadata tableMeta = SAMPLE_METADATA.BASIC_1.get();
            List<ColumnMetadata> dataColumns = tableMeta.getDataColumns();
            for (ColumnMetadata columnMetadata : dataColumns) {
                final String name = columnMetadata.getName();
                if (name.matches("值\\d")) {
                    columnMetadata.setGroupFunction(GroupingFunction.SUM);
                }
            }
            GroupingInfo groupingInfo = tableMeta.createGroupingInfo("總計：");
            groupingInfo.setOutputPosition(Position.AFTER);
            GroupingLevel groupingLV1 = groupingInfo.addGroupLevel("小計：", find(dataColumns, "年度"));
            GroupingLevel groupingLV2 = groupingInfo.addGroupLevel("小計：", find(dataColumns, "地區"));
            groupingInfo.getHeaderFormat().setFontBold(true).setPadding(3f);
            groupingLV1.getGroupHeaderFormat().setFontBold(true).setPadding(3f).setPaddingL(15f);
            groupingLV2.getGroupHeaderFormat().setFontBold(true).setPadding(3f).setPaddingL(15f);

            final PDFNormalTableTransfer createTransfer = new PDFNormalTableTransfer(pdfDocument, tableMeta);
            createTransfer.transTable(SAMPLE_VO.mockDataset());
        }

    }

    @Test
    public void test_GROUP_MD() throws Exception {
        this.groovyReport { PDFDocument pdfDocument->


            pdfDocument.writeText("使用 Master-detail 模式輸出，前兩項欄位定義為 MASTER 項目，所以把寬度設為0不顯示在子表中");
            pdfDocument.writeText("")

            final NormalTableMetadata tableMeta = SAMPLE_METADATA.BASIC_1_SIZE0_1ST.get();
            List<ColumnMetadata> dataColumns = tableMeta.getDataColumns();

            find(dataColumns, "值1").setGroupFunction(GroupingFunction.SUM);
            find(dataColumns, "值2").setGroupFunction(GroupingFunction.SUM);
            find(dataColumns, "值3").setGroupFunction(GroupingFunction.SUM);
            find(dataColumns, "項目").setGroupFunction(GroupingFunction.HEADER);


            GroupingInfo groupingInfo = tableMeta.createGroupingInfo("總計：");
            GroupingLevel groupingLV1 = groupingInfo.addGroupLevel("小計：", find(dataColumns, "年度"));
            GroupingLevel groupingLV2 = groupingInfo.addGroupLevel("小計：", find(dataColumns, "地區"));
            find(dataColumns, "年度").getContentFormat().setBorder(0).setFontBold(true);
            find(dataColumns, "地區").getContentFormat().setBorder(0).setFontBold(true);
            find(dataColumns, "年度").getHeaderFormat().setBorder(0);
            find(dataColumns, "地區").getHeaderFormat().setBorder(0);

            groupingInfo.getHeaderFormat().setFontBold(true).setPadding(3f).setBorder(0);
            groupingInfo.getGroupLevel(0).setShowGroupingRow(false);
            //groupingLV1.setShowGroupingRow(false); // 不顯示第一層(年度)
            groupingLV1.setTitleMode(TitleMode.THIS);
            groupingLV2.setTitleMode(TitleMode.ALL);
            groupingLV1.getGroupHeaderFormat().setFontBold(true).setPadding(3f).setPaddingL(3f).setBorder(0).setAlignH(AlignH.RIGHT);
            groupingLV2.getGroupHeaderFormat().setFontBold(true).setPadding(3f).setPaddingL(3f).setBorder(0).setAlignH(AlignH.RIGHT);

            tableMeta.setGroupingMode(GroupMode.MASTER_DETAIL);
            final PDFNormalTableTransfer createTransfer = new PDFNormalTableTransfer(pdfDocument, tableMeta);
            createTransfer.transTable(SAMPLE_VO.mockDataset());
        }

    }


}
