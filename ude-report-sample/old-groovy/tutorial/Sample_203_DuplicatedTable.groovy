/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import java.util.List

import org.junit.Test

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.transfer.PDFDuplicatedTableTransfer.Mode
import com.iisigroup.ude.report.table.NestTableMetadata
import com.iisigroup.ude.report.table.NormalTableMetadata
import com.iisigroup.ude.report.table.internal.ColumnMetadata
import com.iisigroup.ude.report.table.metadata.TreeColumnMetadata

/**
 *
 */
class Sample_203_DuplicatedTable extends AbstractITextSampleGroovy {



    private static TreeColumnMetadata find(List<ColumnMetadata> dataColumns, String name) {
        for (ColumnMetadata columnMetadata : dataColumns) {
            final String columnName = columnMetadata.getName();
            if (StringUtils.equals(name, columnName)) {
                return columnMetadata;
            }
        }
        return Mockito.mock(ColumnMetadata.class);
    }


    //####################################################################
    //## [Method] sub-block : 基本標題 -
    //####################################################################

    public NormalTableMetadata BASIC_METADATA(String captionText) {
        final NormalTableMetadata tableMeta = new NormalTableMetadata(captionText);
        tableMeta.setWidthPercentage(95);
        tableMeta.getDefaultHeaderFormat().setMinHeightInCM(0.8f).setFontBold(true);
        tableMeta.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        TreeColumnMetadata col_A = tableMeta.append("年度", new BeanProperty("text1"), 20);
        TreeColumnMetadata col_B = tableMeta.append("地區", new BeanProperty("text2"), 20);
        TreeColumnMetadata col_C = tableMeta.append("項目", new BeanProperty("text3"), 20);
        TreeColumnMetadata col_D = tableMeta.append("值1", new BeanProperty("value1"), 20);
        col_B.getContentFormat().setAlignH(AlignH.CENTER);
        col_D.getContentFormat().setAlignH(AlignH.RIGHT);
        return tableMeta;
    }
    public NestTableMetadata NEST_METADATA(String captionText) {
        final NestTableMetadata tableMeta = new NestTableMetadata(captionText);
        tableMeta.setWidthPercentage(95);
        tableMeta.getDefaultHeaderFormat().setMinHeightInCM(0.8f).setFontBold(true);
        tableMeta.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        TreeColumnMetadata col_A = tableMeta.append("年度", new BeanProperty("text1"), 20);
        TreeColumnMetadata col_B = tableMeta.append("地區", new BeanProperty("text2"), 20);
        TreeColumnMetadata col_C = tableMeta.append("項目", new BeanProperty("text3"), 20);
        col_B.getContentFormat().setAlignH(AlignH.CENTER);

        tableMeta.nextRow();
        TreeColumnMetadata col_D = tableMeta.append("值1", new BeanProperty("value1"), 20);
        TreeColumnMetadata col_E = tableMeta.append("值2", new BeanProperty("value1"), 20);
        col_D.getContentFormat().setAlignH(AlignH.RIGHT);
        return tableMeta;
    }

    void processTest( PDFDocument pdfDocument, Mode v, boolean fillToEnd, int repeat) {

        if (true){
            pdfDocument.writeText("COLUMN-HEADER 部分會每頁重複顯示。");
            pdfDocument.writeText("無標題表格");
            pdfDocument.writeText("");
            PDFDuplicatedTableTransfer trans1 = new PDFDuplicatedTableTransfer(pdfDocument, BASIC_METADATA(""), v)
                    .setRepeat(repeat)
                    .setFillPage(fillToEnd)
            trans1.transTable(SAMPLE_VO.mockDataset(115));
            pdfDocument.writeText("前表輸出位置:" + trans1.helper().getLastRange());
            pdfDocument.writeText("接續內容文字!");
            pdfDocument.newPage();
        }


        pdfDocument.writeText("NEST TABLE :", 28);
        pdfDocument.newPage();

        if (true){
            pdfDocument.writeText("COLUMN-HEADER 部分會每頁重複顯示。");
            pdfDocument.writeText("無標題表格");
            pdfDocument.writeText("");
            PDFDuplicatedTableTransfer trans1 = new PDFDuplicatedTableTransfer(pdfDocument, NEST_METADATA(""), v)
                    .setRepeat(repeat)
                    .setFillPage(fillToEnd)
            trans1.transTable(SAMPLE_VO.mockDataset(115));
            pdfDocument.writeText("前表輸出位置:" + trans1.helper().getLastRange());
            pdfDocument.writeText("接續內容文字!");
            pdfDocument.newPage();
        }

        pdfDocument.writeText("含標題表格：(標題：測試表格)", 28);
        pdfDocument.newPage();

        if (true){
            pdfDocument.writeText("");
            pdfDocument.writeText("含標題表格：(標題：測試表格)");
            pdfDocument.writeText("");
            PDFDuplicatedTableTransfer trans = new PDFDuplicatedTableTransfer(pdfDocument, BASIC_METADATA("標題：測試表格"),v)
                    .setRepeat(repeat)
                    .setFillPage(fillToEnd)
            trans.transTable(SAMPLE_VO.mockDataset(105));
            pdfDocument.writeText("前表輸出位置:" + trans.helper().getLastRange());
            pdfDocument.writeText("接續內容文字!");
        }



    }


    @Test
    void test_BASIC_VFirst() {
        this.groovyReport { PDFDocument pdfDocument->
            processTest(pdfDocument, Mode.V_FIRST, true, 3);
        }
    }
    @Test
    void test_BASIC_VFirst_NotFill() {
        this.groovyReport { PDFDocument pdfDocument->
            processTest(pdfDocument, Mode.V_FIRST, false, 3);
        }
    }

    @Test
    void test_BASIC_HFirst() {
        this.setPageSize(PageSize.A3.rotate());
        this.groovyReport { PDFDocument pdfDocument->
            processTest(pdfDocument, Mode.H_FIRST, true, 5);
        }
    }
    @Test
    void test_BASIC_HFirst_NotFill() {
        this.setPageSize(PageSize.A3.rotate());
        this.groovyReport { PDFDocument pdfDocument->
            processTest(pdfDocument, Mode.H_FIRST, false, 5);
        }
    }





}
