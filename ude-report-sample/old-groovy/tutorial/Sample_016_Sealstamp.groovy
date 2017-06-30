package com.iisigroup.ude.report.itext2.sample.tutorial

/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
import static com.iisigroup.ude.report.itext2.header.AbstractPageHeader.PagePattern.*
import static com.iisigroup.ude.report.itext2.header.HeaderPosition.*

import com.iisigroup.ude.report.itext2.PDFDocument
import com.iisigroup.ude.report.itext2.PageCounter
import com.iisigroup.ude.report.itext2.commons.LayoutInfo
import com.iisigroup.ude.report.itext2.marker.markinfo.PropertiesMarkInfo
import com.iisigroup.ude.report.itext2.marker.watermark.PositionType
import com.iisigroup.ude.report.itext2.marker.watermark.SealstampProperties
import com.iisigroup.ude.report.itext2.marker.watermark.TextDirection
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.table.TableiText
import com.iisigroup.ude.report.table.format.DocumentAlign
import com.lowagie.text.PageSize

import org.junit.Before
import org.junit.Test

/**
 *
 */
class Sample_016_Sealstamp  extends AbstractITextSampleGroovy {

    def WithCover = { PDFDocument pdfDocument ->
        pdfDocument.setSkipPager(true);
        pdfDocument.writeText("封面....");
        for (int s = 1; s <= 3; s++) {
            pdfDocument.newSection();
            pdfDocument.setSkipPager(true);
            pdfDocument.writeText("第" + pdfDocument.getSection() + "小節封面....");
            for (int i = 1; i <=  s*2-1; i++) {
                pdfDocument.newPage();
                pdfDocument.setSkipPager(false);
                PageCounter pageCounter = pdfDocument.getPageCounter();
                pdfDocument.writeText("TEST PAGE (PageCounter0): " + pageCounter.getSection() + "-" + pageCounter.getPageNow());
                pageCounter = pdfDocument.getPageCounter();
                pdfDocument.writeText("TEST PAGE (PageCounter1): " + pageCounter.getSection() + "-" + pageCounter.getPageNow());
                pdfDocument.writeText("TEST PAGE (LOCAL-COUNT): " + s + "-" + i);

                pdfDocument.writeText("PDF文件頁數: " + pdfDocument.getPageNumber());
                pdfDocument.writeText("PDF文件頁數: " + pageCounter.getPagePDF()   );

                TableiText table = pdfDocument.createTable(60, 5);
                table.setHorizontalAlignment(DocumentAlign.CENTER);
                for (int k = 1; k <= 90; k++) {
                    table.addCell("TEST");
                }
                table.appendMe();
            }
        }
    };

    def NoCover = { PDFDocument pdfDocument ->
        for (int s = 1; s <= 3; s++) {
            pdfDocument.newSection();
            for (int i = 1; i <=  s*2-1; i++) {
                pdfDocument.newPage();
                PageCounter pageCounter = pdfDocument.getPageCounter();
                pdfDocument.writeText("TEST PAGE (PageCounter0): " + pageCounter.getSection() + "-" + pageCounter.getPageNow());
                pageCounter = pdfDocument.getPageCounter();
                pdfDocument.writeText("TEST PAGE (PageCounter1): " + pageCounter.getSection() + "-" + pageCounter.getPageNow());
                pdfDocument.writeText("TEST PAGE (LOCAL-COUNT): " + s + "-" + i);

                pdfDocument.writeText("PDF文件頁數: " + pdfDocument.getPageNumber());
                pdfDocument.writeText("PDF文件頁數: " + pageCounter.getPagePDF()   );

                TableiText table = pdfDocument.createTable(60, 5);
                table.setHorizontalAlignment(DocumentAlign.CENTER);
                for (int k = 1; k <= 90; k++) {
                    table.addCell("TEST");
                }
                table.appendMe();
            }
        }
    };

    def NoSection = { PDFDocument pdfDocument ->
      
        
        for (int i = 1; i <= maxPage; i++) {
            
            pdfDocument.newPage();
            pdfDocument.setSkipPager(true);
            pdfDocument.writeText("封面....");
            
            pdfDocument.newPage();
            pdfDocument.setSkipPager(false);
            PageCounter pageCounter = pdfDocument.getPageCounter();
            pdfDocument.writeText("TEST PAGE (PageCounter0): " + pageCounter.getSection() + "-" + pageCounter.getPageNow());
            pageCounter = pdfDocument.getPageCounter();
            pdfDocument.writeText("TEST PAGE (PageCounter1): " + pageCounter.getSection() + "-" + pageCounter.getPageNow());
            pdfDocument.writeText("TEST PAGE (LOCAL-COUNT): " + i);
            pdfDocument.writeText("PDF文件頁數: " + pdfDocument.getPageNumber());
            pdfDocument.writeText("PDF文件頁數: " + pageCounter.getPagePDF()   );
            TableiText table = pdfDocument.createTable(60, 5);
            table.setHorizontalAlignment(DocumentAlign.CENTER);
            for (int k = 1; k <= 90; k++) {
                table.addCell("TEST");
            }
            table.appendMe();
        }
    };

    int maxPage = 10;

    SealstampProperties markProp;

    @Before
    void setup() {
        this.maxPage = 10;
        this.markProp = new SealstampProperties("classpath:RCSS00001.png");
        super.setMarkInfos(new PropertiesMarkInfo(markProp));
        this.setPageSize(PageSize.A4);
        final LayoutInfo layoutInfo = new LayoutInfo();
        this.setLayoutInfo(layoutInfo)
        this.setContentGenerater(NoSection);
    }

    //####################################################################
    //## [Method] sub-block : 連續頁次.
    //####################################################################

    @Test
    void test_MultiPagesWithDefault() {
        markProp.setIgnoreOnePage(true);
        super.groovyReport (NoSection)
    }
    @Test
    void test_MultiPagesIgnoreSection() {
        markProp.setPositionType(PositionType.IGNORE_SECTIONS);
        super.groovyReport (NoSection)
    }
    @Test
    void test_MultiPagesBinding() {
        markProp.setPositionType(PositionType.BINDING);
        markProp.setSkipPages(1)
        super.groovyReport (NoSection)
    }
    @Test
    void test_MultiPagesFull() {
        markProp.setPositionType(PositionType.FULL);
        super.groovyReport (NoSection)
    }

    //####################################################################
    //## [Method] sub-block : 單一頁次.
    //####################################################################


    @Test
    void test_OnePageWithDefault() {
        this.maxPage = 1;
        super.groovyReport (NoSection)
    }

    @Test
    void test_OnePageIgnore() {
        this.maxPage = 1;
        this.markProp.setIgnoreOnePage(true);
        super.groovyReport (NoSection)
    }


    @Test
    void test_OnePageIgnoreSection() {
        this.maxPage = 1;
        markProp.setPositionType(PositionType.IGNORE_SECTIONS);
        super.groovyReport (NoSection)
    }
    @Test
    void test_OnePageBinding() {
        this.maxPage = 1;
        markProp.setPositionType(PositionType.BINDING);
        super.groovyReport (NoSection)
    }
    @Test
    void test_OnePageFull() {
        this.maxPage = 1;
        markProp.setPositionType(PositionType.FULL);
        super.groovyReport (NoSection)
    }

    //####################################################################
    //## [Method] sub-block : 分節.
    //####################################################################

    @Test
    void test_SectionsWithDefault() {
        super.groovyReport (NoCover)
    }
    @Test
    void test_SectionsIgnoreSection() {
        markProp.setPositionType(PositionType.IGNORE_SECTIONS);
        super.groovyReport (NoCover)
    }
    @Test
    void test_SectionsBinding() {
        markProp.setPositionType(PositionType.BINDING);
        super.groovyReport (NoCover)
    }
    @Test
    void test_SectionsFull() {
        markProp.setPositionType(PositionType.FULL);
        super.groovyReport (NoCover)
    }


    //####################################################################
    //## [Method] sub-block : 連續頁次.
    //####################################################################

    @Test
    void test_CoverWithDefault() {
        super.groovyReport (WithCover)
    }
    @Test
    void test_CoverIgnoreSection() {
        markProp.setPositionType(PositionType.IGNORE_SECTIONS);
        super.groovyReport (WithCover)
    }
    @Test
    void test_CoverBinding() {
        markProp.setPositionType(PositionType.BINDING);
        super.groovyReport (WithCover)
    }
    @Test
    void test_CoverFull() {
        markProp.setPositionType(PositionType.FULL);
        super.groovyReport (WithCover)
    }

    //####################################################################
    //## [Method] sub-block : T->B
    //####################################################################

    @Test
    void test_TB_Normal() {
        setPageSize(PageSize.A4.rotate());
        markProp.setTextDirection(TextDirection.T2B);
    }


    @Test
    void test_TB_Full() {
        setPageSize(PageSize.A4.rotate());
        markProp.setTextDirection(TextDirection.T2B);
        markProp.setPositionType(PositionType.FULL);
    }


    @Test
    void test_TB_Binding() {
        setPageSize(PageSize.A4.rotate());
        markProp.setTextDirection(TextDirection.T2B);
        markProp.setPositionType(PositionType.BINDING);
        markProp.setSkipPages(1);
    }

    //####################################################################
    //## [Method] sub-block : L->R
    //####################################################################

    @Test
    void test_RL_Normal() {
        setPageSize(PageSize.A4);
        markProp.setTextDirection(TextDirection.R2L);
    }


    @Test
    void test_RL_Full() {
        setPageSize(PageSize.A4);
        markProp.setTextDirection(TextDirection.R2L);
        markProp.setPositionType(PositionType.FULL);
    }


    @Test
    void test_RL_Binding() {
        setPageSize(PageSize.A4);
        markProp.setTextDirection(TextDirection.R2L);
        markProp.setPositionType(PositionType.BINDING);
        markProp.setSkipPages(1);
    }

}
