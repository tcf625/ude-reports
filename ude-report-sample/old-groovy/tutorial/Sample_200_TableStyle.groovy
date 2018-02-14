/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import java.awt.Color

import org.apache.commons.lang3.StringUtils
import org.junit.Test
import org.mockito.Mockito

import com.iisigroup.ude.report.data.source.BeanProperty
import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.transfer.PDFNormalTableTransfer
import com.iisigroup.ude.report.table.NormalTableMetadata
import com.iisigroup.ude.report.table.format.CellFormat.AlignH
import com.iisigroup.ude.report.table.format.CellFormat.AlignV
import com.iisigroup.ude.report.table.metadata.TreeColumnMetadata

/**
 *
 */
class Sample_200_TableStyle extends AbstractITextSampleGroovy {



    private static TreeColumnMetadata find(List<TreeColumnMetadata> dataColumns, String name) {
        for (TreeColumnMetadata TreeColumnMetadata : dataColumns) {
            final String columnName = TreeColumnMetadata.getName();
            if (StringUtils.equals(name, columnName)) {
                return TreeColumnMetadata;
            }
        }
        return Mockito.mock(TreeColumnMetadata.class);
    }


    //####################################################################
    //## [Method] sub-block : 基本格式繼承
    //####################################################################



    @Test
    void test_BorderWidth() {
        this.groovyReport { PDFDocument pdfDocument->
            pdfDocument.writeText("所有樣式預設置中，粗框");
            pdfDocument.writeText("HEADER 的外粗框由 DefaultHeaderFormat而來");
            pdfDocument.writeText("");
            final NormalTableMetadata table1 = new NormalTableMetadata("");
            table1.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
            TreeColumnMetadata col_A = table1.append("年度", new BeanProperty("text1"), 20);
            TreeColumnMetadata col_B = table1.append("地區", new BeanProperty("text2"), 20);
            TreeColumnMetadata col_C = table1.append("項目", new BeanProperty("text3"), 20);
            TreeColumnMetadata col_G2 = table1.append("GROUP 內容靠右 / 內部細框 ");
            col_G2.getHeaderFormat().setBorderWidth(1).setAlignH(AlignH.LEFT);
            col_G2.getContentFormat().setBorderWidth(0.5).setAlignH(AlignH.RIGHT);
            TreeColumnMetadata col_D = col_G2.append("值1", new BeanProperty("value1"), 20);
            TreeColumnMetadata col_E = col_G2.prepend("值2", new BeanProperty("value1"), 20);
            col_E.getContentFormat().setBackgroundColor(Color.lightGray);
            table1.getDefaultContentFormat().setBorderWidth(4).setAlignH(AlignH.CENTER);
            table1.getDefaultHeaderFormat().setBorderWidth(3).setAlignH(AlignH.CENTER);
            new PDFNormalTableTransfer(pdfDocument, table1).transTable(SAMPLE_VO.mockDataset(100));
            //
            //
            //
            pdfDocument.writeText("HEADER 的無外框(width=0) 所以只呈現GROUP的框線 ");
            pdfDocument.writeText("");
            table1.getDefaultHeaderFormat().setBorderWidth(0);
            new PDFNormalTableTransfer(pdfDocument, table1).transTable(SAMPLE_VO.mockDataset(100));
        }
    }



    @Test
    void test_Style() {
        this.groovyReport { PDFDocument pdfDocument->
            final NormalTableMetadata table1 = new NormalTableMetadata("");
            table1.getDefaultContentFormat().setBorderWidth(0.5).setAlignH(AlignH.CENTER);
            table1.getDefaultHeaderFormat().setBorderWidth(0.5).setAlignH(AlignH.CENTER);
            table1.getStyle("st1").getHeader().setBorderWidth(2).setAlignH(AlignH.LEFT);
            table1.getStyle("st1").getContent().setBorderWidth(1).setAlignH(AlignH.RIGHT);
            table1.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
            TreeColumnMetadata col_A = table1.append("年度", new BeanProperty("text1"), 20);
            TreeColumnMetadata col_B = table1.append("地區", new BeanProperty("text2"), 20);
            TreeColumnMetadata col_G2 = table1.append("GROUP").setStyle("st1");
            TreeColumnMetadata col_D = col_G2.append("值1", new BeanProperty("value1"), 20);
            TreeColumnMetadata col_E = col_G2.prepend("值2", new BeanProperty("value1"), 20);

            pdfDocument.writeText("所有樣式預設置中");
            pdfDocument.writeText(" [Style]標題靠左、內容靠右");
            pdfDocument.writeText("");
            new PDFNormalTableTransfer(pdfDocument, table1).transTable(SAMPLE_VO.mockDataset(100));
            //
            //
            //
            table1.getStyle("st1").getHeader().setBorderWidth(1).setAlignH(AlignH.RIGHT);
            table1.getStyle("st1").getContent().setBorderWidth(2).setAlignH(AlignH.LEFT);
            pdfDocument.writeText("ChangeStyle");
            pdfDocument.writeText(" [Style]標題靠右、內容靠左");
            pdfDocument.writeText("");
            new PDFNormalTableTransfer(pdfDocument, table1).transTable(SAMPLE_VO.mockDataset(100));
        }
    }



}
