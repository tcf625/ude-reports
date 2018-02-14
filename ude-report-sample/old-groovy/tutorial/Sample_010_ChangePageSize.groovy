/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.PageCounter;
import com.iisigroup.ude.report.itext2.commons.LayoutInfo
import com.iisigroup.ude.report.itext2.header.HeaderPosition
import com.iisigroup.ude.report.itext2.header.PageHeaderEN
import com.iisigroup.ude.report.itext2.header.AbstractPageHeader.PagePattern
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle

import org.junit.Before
import org.junit.Test

/**
 *
 */
class Sample_010_ChangePageSize  extends AbstractITextSampleGroovy {



    private void setPageInfo(PDFDocument pdfDocument, Rectangle pagesize) {
        pdfDocument.setPageSize(pagesize);
        final float marginBottom = pagesize.getHeight() / 8;
        final float marginTop = pagesize.getHeight() / 8;
        final float marginRight = pagesize.getWidth() / 8;
        final float marginLeft = pagesize.getWidth() / 10;
        final LayoutInfo layoutInfo = new LayoutInfo(marginLeft, marginRight, marginTop, marginBottom);
        layoutInfo.setHeader(HeaderPosition.LeftHeader, new PageHeaderEN(PagePattern.PAGE, 14));
        pdfDocument.setLayoutInfo(layoutInfo);
    }


    @Test
    void test() {
        final Rectangle[] sizes = [PageSize.A4, PageSize.A5, PageSize.A6, PageSize.A5, PageSize.A4.rotate()];
        super.groovyReport({ PDFDocument pdfDocument  ->
            setPageInfo(pdfDocument, PageSize.A3.rotate());
            int i = 0;
            for (final Rectangle rectangle : sizes) {
                i++;
                pdfDocument.writeText(i + ". 空白文件 , current page size: " + pdfDocument.getPdfWriter().getPageSize());
                pdfDocument.writeText("")
                int x = 1;
                for (final Rectangle rectangle2 : sizes) {
                    x++;
                    if (pdfDocument.getPageNumber()==x || pdfDocument.getPageNumber()==1){
                        pdfDocument.writeText("  ==> page.$x:" + rectangle2, 10);
                    }
                }
                pdfDocument.writeText("pageInIText :　" + pdfDocument.getPageNumber(), 8);
                pdfDocument.writeText("pageCounter :　" + pdfDocument.getPageCounter(), 8);
                setPageInfo(pdfDocument, rectangle);
                pdfDocument.newPage();
            }
            i++;
            pdfDocument.newPage();
            pdfDocument.writeText(i + ". 最後一頁A:" + pdfDocument.getPdfWriter().getPageSize());
            pdfDocument.writeText(i + ". 最後一頁B:" + pdfDocument.getPdfWriter().getPageSize());
            pdfDocument.writeText("pageInIText :　" + pdfDocument.getPageNumber());
            pdfDocument.writeText("pageCounter :　" + pdfDocument.getPageCounter());
        }
        );
    }


    @Test
    void test_ResetAfterNewPage() {
        final Rectangle[] sizes = [PageSize.A4, PageSize.A5, PageSize.A6, PageSize.A5, PageSize.A4.rotate()];
        super.groovyReport({ PDFDocument pdfDocument  ->
            setPageInfo(pdfDocument, PageSize.A3.rotate());
            pdfDocument.writeText("在產出下一頁(newPage)後，才呼叫切換頁面資訊");
            int i = 0;
            for (final Rectangle rectangle : sizes) {
                i++;
                pdfDocument.writeText(i + ". 空白文件 , current page size: " + pdfDocument.getPdfWriter().getPageSize());
                pdfDocument.writeText("")
                int x = 1;
                for (final Rectangle rectangle2 : sizes) {
                    x++;
                    if (pdfDocument.getPageNumber()==x || pdfDocument.getPageNumber()==1){
                        pdfDocument.writeText("  ==> page.$x:" + rectangle2, 10);
                    }
                }
                pdfDocument.writeText("pageInIText :　" + pdfDocument.getPageNumber(), 8);
                pdfDocument.writeText("pageCounter :　" + pdfDocument.getPageCounter(), 8);
                pdfDocument.newPage();
                setPageInfo(pdfDocument, rectangle);  // 應該在產出下一頁前做切換
            }
            i++;
            pdfDocument.newPage();
            pdfDocument.writeText(i + ". 最後一頁A:" + pdfDocument.getPdfWriter().getPageSize());
            pdfDocument.writeText(i + ". 最後一頁B:" + pdfDocument.getPdfWriter().getPageSize());
            pdfDocument.writeText("pageInIText :　" + pdfDocument.getPageNumber());
            pdfDocument.writeText("pageCounter :　" + pdfDocument.getPageCounter());
        }
        );
    }
}
