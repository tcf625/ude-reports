package com.iisigroup.ude.report.itext2.sample.tutorial

/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
import static com.iisigroup.ude.report.itext2.header.AbstractPageHeader.PagePattern.*;
import static com.iisigroup.ude.report.itext2.header.HeaderPosition.*;

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.commons.LayoutInfo
import com.iisigroup.ude.report.itext2.header.PageHeaderCH
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.lowagie.text.PageSize

import org.junit.Before
import org.junit.Test

/**
 *
 */
class Sample_012_Cover  extends AbstractITextSampleGroovy {

    @Before
    void setup() {
        this.setPageSize(PageSize.A6.rotate());
        final LayoutInfo layoutInfo = new LayoutInfo();
        layoutInfo.setHeader(CenterFooter, new PageHeaderCH(SECTION_AND_PAGE, 10).setPrefix("第").setConjunction("節，第").setSuffix("頁"));
        this.setLayoutInfo(layoutInfo)
    }

    @Test
    void test() {
        super.groovyReport { PDFDocument pdfDocument ->

            pdfDocument.setSkipPager(true);
            pdfDocument.writeText("封面....");
            for (int s = 1; s <= 5; s++) {
                pdfDocument.newSection();
                pdfDocument.setSkipPager(true);
                pdfDocument.writeText("第" + pdfDocument.getSection() + "小節封面....");

                pdfDocument.newPage();
                pdfDocument.setSkipPager(false);
                pdfDocument.writeText("小節開始頁");
                for (int i = 2; i <= 2 + s; i++) {
                    //pdfDocument.writeText("跳過封面頁次輸出");
                    pdfDocument.newPage();
                    pdfDocument.writeText("TEST PAGE: " + s + "-" + i);
                    //pdfDocument.writeText("PDF文件頁數:" + pdfDocument.getPageNumber());
                }
                pdfDocument.writeText("小節最後頁");
            }

            //
            //            // Cover
            //            pdfDocument.setSkipPager(true);
            //            pdfDocument.newPage();
            //            pdfDocument.writeText("\n\n\n\n")
            //            pdfDocument.writeText("封面頁", 48, DocumentAlign.CENTER);
            //            pdfDocument.newPage();
            //
            //            //
            //            // Section 1 .
            //            pdfDocument.setSkipPager(false);
            //            pdfDocument.writeText("第一節", 18)
            //            pdfDocument.writeText("bababa....")
            //            pdfDocument.newPage();
            //            pdfDocument.writeText("bababa....")

        }
    }
}
