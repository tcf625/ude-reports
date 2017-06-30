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
import com.iisigroup.ude.report.table.NormalTableMetadata
import com.iisigroup.ude.report.table.internal.ColumnMetadata
import com.iisigroup.ude.report.table.metadata.TreeColumnMetadata

/**
 *
 */
class Sample_204_DuplicatedTable_V_Case extends AbstractITextSampleGroovy {



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
        tableMeta.getDefaultHeaderFormat().setMinHeightInCM(0.8f);
        tableMeta.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        TreeColumnMetadata col_A = tableMeta.append("年度", new BeanProperty("text1"), 20);
        TreeColumnMetadata col_B = tableMeta.append("地區", new BeanProperty("text2"), 20);
        TreeColumnMetadata col_C = tableMeta.append("項目", new BeanProperty("text3"), 20);
        TreeColumnMetadata col_D = tableMeta.append("值1", new BeanProperty("value1"), 20);
        col_D.getContentFormat().setAlignH(AlignH.RIGHT);
        tableMeta.setSpacingBefore(20);
        return tableMeta;
    }

    void processTest(PDFDocument pdfDocument, Mode v, boolean fillToEnd, int repeat, int maxDataSize) {

        pdfDocument.setHeader(HeaderPosition.RightHeader, "SIZE:${maxDataSize}", 10)
        List<SAMPLE_VO> rawData = SAMPLE_VO.mockDataset(105);
        while (rawData.size()>maxDataSize) {
            rawData.remove(rawData.size()-1);
        }

        if (2==2) {
            pdfDocument.newPage();
            pdfDocument.setHeader(HeaderPosition.LeftHeader, "含標題表格：(標題：測試表格)", 10)
            PDFDuplicatedTableTransfer trans = new PDFDuplicatedTableTransfer(pdfDocument, BASIC_METADATA("標題：測試表格"),v)
                    .setRepeat(repeat)
                    .setExtendToPage(fillToEnd)
            trans.transTable(rawData);
            pdfDocument.writeText("前表輸出位置:" + trans.helper().getLastRange());
            pdfDocument.writeText("接續內容文字!");
        }

        if (1==1) {
            pdfDocument.setHeader(HeaderPosition.LeftHeader, "無標題表格", 10)
            PDFDuplicatedTableTransfer trans1 = new PDFDuplicatedTableTransfer(pdfDocument, BASIC_METADATA(null), v)
                    .setRepeat(repeat)
                    .setExtendToPage(fillToEnd)

            trans1.transTable(rawData);
            pdfDocument.writeText("前表輸出位置:" + trans1.helper().getLastRange());
            pdfDocument.writeText("接續內容文字!");
            pdfDocument.writeText("建立TableMetadata時可傳入Caption文字。若為空字串就不顯示標題，也不佔用空間。");
            pdfDocument.writeText("標題部分會每頁重複顯示(預設為無)。");
        }



        if (3==3) {
            pdfDocument.newPage();
            pdfDocument.setHeader(HeaderPosition.LeftHeader, "擴展最後區塊", 10)
            PDFDuplicatedTableTransfer trans2 = new PDFDuplicatedTableTransfer(pdfDocument, BASIC_METADATA("標題：測試表格"),v)
                    .setRepeat(repeat)
                    .setExtendToPage(fillToEnd)
            //.setRenderMode(RenderMode.ExtendToPage)
            trans2.transTable(rawData);
            pdfDocument.writeText("前表輸出位置:" + trans2.helper().getLastRange());
            pdfDocument.writeText("接續內容文字!");
        }
    }

    @Test
    void test_BASIC_VFirst_19() {
        this.setPageSize(PageSize.A5.rotate());
        this.groovyReport { PDFDocument pdfDocument->
            processTest(pdfDocument, Mode.V_FIRST, true, 3, 19);
        }
    }

    @Test
    void test_BASIC_VFirst_18() {
        this.setPageSize(PageSize.A5.rotate());
        this.groovyReport { PDFDocument pdfDocument->
            processTest(pdfDocument, Mode.V_FIRST, true, 3, 18);
        }
    }

    @Test
    void test_BASIC_VFirst_17() {
        this.setPageSize(PageSize.A5.rotate());
        this.groovyReport { PDFDocument pdfDocument->
            processTest(pdfDocument, Mode.V_FIRST, true, 3, 17);
        }
    }


    //    @Test
    //    void test_BASIC_VFirst_NotFill() {
    //        this.groovyReport { PDFDocument pdfDocument->
    //            processTest(pdfDocument, Mode.V_FIRST, false, 3);
    //        }
    //    }





}
