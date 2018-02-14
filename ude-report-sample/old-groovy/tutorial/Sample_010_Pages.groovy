/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.commons.LayoutInfo
import com.iisigroup.ude.report.itext2.header.HeaderPosition
import com.iisigroup.ude.report.itext2.header.PageHeaderCH
import com.iisigroup.ude.report.itext2.header.AbstractPageHeader.PagePattern
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.utils.Coordinate
import com.lowagie.text.PageSize

import org.junit.Before
import org.junit.Test

/**
 *
 */
class Sample_010_Pages  extends AbstractITextSampleGroovy {

    @Before
    void setup() {
        super.setContentGenerater({ PDFDocument pdfDocument  ->
            pdfDocument.writeText("空白文件");
        }
        );
    }


    @Test
    void test_BlankPage() {
        final LayoutInfo layoutInfo = new LayoutInfo(super.getiTextConfig());
        layoutInfo.setHeader(HeaderPosition.CenterFooter, new PageHeaderCH(PagePattern.BOTH, 14));
        this.setLayoutInfo(layoutInfo);
        super.groovyReport  { PDFDocument pdfDocument  ->
            pdfDocument.writeText("P1. 連續兩次newPage不會有空白頁");
            pdfDocument.newPage();
            pdfDocument.newPage();
            pdfDocument.writeText("P2. 要新增空白頁，應於newPage間加入頁面元素");
            pdfDocument.newPage();
            pdfDocument.writeText("");
            pdfDocument.newPage();
            pdfDocument.writeText("P4. 我是第四頁");
        }
    }

    @Test
    void test_PageChangeCheck() {
        final LayoutInfo layoutInfo = new LayoutInfo(super.getiTextConfig());
        layoutInfo.setHeader(HeaderPosition.CenterFooter, new PageHeaderCH(PagePattern.BOTH, 14));
        this.setLayoutInfo(layoutInfo);
        super.groovyReport  { PDFDocument pdfDocument  ->

            for(int i=0;i<200;i++){
                pdfDocument.writeText( i + "-1換頁測試\n" + i + "-2換頁測試\n" + i + "-3換頁測試\n");
                if (pdfDocument.isPageChanged()){
                    if (pdfDocument.isNewPageBegin()){
                        pdfDocument.newPage();
                        pdfDocument.writeText("前項輸出導致換頁，新頁面還沒有內容輸出");
                    } else {
                        pdfDocument.writeText("前項輸出導致換頁，新頁面已有內容輸出");
                    }
                }
            }
        }
    }




    @Test
    void test_PageSizeA3() {
        this.setPageSize(PageSize.A3);
    }

    @Test
    void test_PageSizeA4Rotate() {
        this.setPageSize(PageSize.A4.rotate());
    }



    @Test
    void test_Margin() {
        final float marginLeft = Coordinate.CM_BL.trans(2.5f); // 以公分為單位
        final float marginRight = 40f; // 以 pixel 為單位
        final float marginTop = 50f;
        final float marginBottom = 80f;
        final LayoutInfo layoutInfo = new LayoutInfo(marginLeft, marginRight, marginTop, marginBottom);
        layoutInfo.setFooterExtra(Coordinate.CM_BL.trans(1f));
        layoutInfo.setHeaderExtra(80);
        layoutInfo.setHeader(HeaderPosition.LeftHeader, "左上", 12);
        layoutInfo.setHeader(HeaderPosition.RightHeader, "右上", 12);
        layoutInfo.setHeader(HeaderPosition.LeftFooter, "左下", 12);
        layoutInfo.setHeader(HeaderPosition.RightFooter, "右下", 12);
        layoutInfo.setHeader(HeaderPosition.CenterHeader, "上中", 12);
        layoutInfo.setHeader(HeaderPosition.CenterFooter, "下中", 12);
        this.setPageSize(PageSize.A4.rotate());
        this.setLayoutInfo(layoutInfo);
    }
}
