/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch02.sec4;

import org.junit.Before;
import org.junit.Test;

import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.PageCounter;
import com.iisigroup.ude.report.itext2.marker.markinfo.PropertiesMarkInfo;
import com.iisigroup.ude.report.itext2.marker.watermark.PositionType;
import com.iisigroup.ude.report.itext2.marker.watermark.SealstampProperties;
import com.iisigroup.ude.report.itext2.marker.watermark.TextDirection;
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.table.format.DocumentAlign;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;

@SuppressWarnings("unused")
public class Sample_Sealstamp extends AbstractSample {

    public void withCover(final PDFDocument pdfDocument) {
        pdfDocument.setSkipPager(true);
        pdfDocument.writeText("封面....");
        for (int s = 1; s <= 3; s++) {
            pdfDocument.newSection();
            pdfDocument.setSkipPager(true);
            pdfDocument.writeText("第" + pdfDocument.getSection() + "小節封面....");
            for (int i = 1; i <= s * 2 - 1; i++) {
                pdfDocument.newPage();
                pdfDocument.setSkipPager(false);
                PageCounter pageCounter = pdfDocument.getPageCounter();
                pdfDocument.writeText(
                        "TEST PAGE (PageCounter0): " + pageCounter.getSection() + "-" + pageCounter.getPageNumberInSection());
                pageCounter = pdfDocument.getPageCounter();
                pdfDocument.writeText(
                        "TEST PAGE (PageCounter1): " + pageCounter.getSection() + "-" + pageCounter.getPageNumberInSection());
                pdfDocument.writeText("TEST PAGE (LOCAL-COUNT): " + s + "-" + i);

                pdfDocument.writeText("PDF文件頁數: " + pdfDocument.getPageNumber());
                pdfDocument.writeText("PDF文件頁數: " + pageCounter.getRealPages());

                final TableiText table = pdfDocument.createTableWithRatio(60, 5);
                table.setHorizontalAlignment(DocumentAlign.CENTER);
                for (int k = 1; k <= 90; k++) {
                    table.addCell("TEST");
                }
                table.appendMe();
            }
        }
    };

    public void NoCover(final PDFDocument pdfDocument) {

        for (int s = 1; s <= 3; s++) {
            pdfDocument.newSection();
            for (int i = 1; i <= s * 2 - 1; i++) {
                pdfDocument.newPage();
                PageCounter pageCounter = pdfDocument.getPageCounter();
                pdfDocument.writeText(
                        "TEST PAGE (PageCounter0): " + pageCounter.getSection() + "-" + pageCounter.getPageNumberInSection());
                pageCounter = pdfDocument.getPageCounter();
                pdfDocument.writeText(
                        "TEST PAGE (PageCounter1): " + pageCounter.getSection() + "-" + pageCounter.getPageNumberInSection());
                pdfDocument.writeText("TEST PAGE (LOCAL-COUNT): " + s + "-" + i);

                pdfDocument.writeText("PDF文件頁數: " + pdfDocument.getPageNumber());
                pdfDocument.writeText("PDF文件頁數: " + pageCounter.getRealPages());

                final TableiText table = pdfDocument.createTableWithRatio(60, 5);
                table.setHorizontalAlignment(DocumentAlign.CENTER);
                for (int k = 1; k <= 90; k++) {
                    table.addCell("TEST");
                }
                table.appendMe();
            }
        }
    };

    public void NoSection(final PDFDocument pdfDocument) {

        for (int i = 1; i <= this.maxPage; i++) {

            pdfDocument.newPage();
            pdfDocument.setSkipPager(true);
            pdfDocument.writeText("封面....");

            pdfDocument.newPage();
            pdfDocument.setSkipPager(false);
            PageCounter pageCounter = pdfDocument.getPageCounter();
            pdfDocument.writeText(
                    "TEST PAGE (PageCounter0): " + pageCounter.getSection() + "-" + pageCounter.getPageNumberInSection());
            pageCounter = pdfDocument.getPageCounter();
            pdfDocument.writeText(
                    "TEST PAGE (PageCounter1): " + pageCounter.getSection() + "-" + pageCounter.getPageNumberInSection());
            pdfDocument.writeText("TEST PAGE (LOCAL-COUNT): " + i);
            pdfDocument.writeText("PDF文件頁數: " + pdfDocument.getPageNumber());
            pdfDocument.writeText("PDF文件頁數: " + pageCounter.getRealPages());
            final TableiText table = pdfDocument.createTableWithRatio(60, 5);
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
    public void setup() {
        this.maxPage = 10;
        this.markProp = new SealstampProperties("classpath:RCSS00001.png");
    }

    public void setMarkers(final PDFDocument pdfDocument) {
        pdfDocument.setMarkMaker(new PropertiesMarkInfo(this.markProp));
    }
    //####################################################################
    //## [Method] sub-block : 連續頁次.
    //####################################################################

    @Test
    public void test_MultiPagesWithDefault() {
        this.markProp.setIgnoreOnePage(true);
        super.createPDF(this::setMarkers, this::NoSection);
    }

    @Test
    public void test_MultiPagesIgnoreSection() {
        this.markProp.setPositionType(PositionType.IGNORE_SECTIONS);
        super.createPDF(this::setMarkers, this::NoSection);
    }

    @Test
    public void test_MultiPagesBinding() {
        this.markProp.setPositionType(PositionType.BINDING);
        this.markProp.setSkipPages(1);
        super.createPDF(this::setMarkers, this::NoSection);
    }

    @Test
    public void test_MultiPagesFull() {
        this.markProp.setPositionType(PositionType.FULL);
        super.createPDF(this::setMarkers, this::NoSection);
    }

    //####################################################################
    //## [Method] sub-block : L->R
    //####################################################################

    @Test
    public void test_RL_Normal() {
        this.markProp.setTextDirection(TextDirection.R2L);
        super.createPDF(this::setMarkers, this::NoSection);
    }

    @Test
    public void test_RL_Full() {
        this.markProp.setTextDirection(TextDirection.R2L);
        this.markProp.setPositionType(PositionType.FULL);
        super.createPDF(this::setMarkers, this::NoSection);
    }

    @Test
    public void test_RL_Binding() {
        this.markProp.setTextDirection(TextDirection.R2L);
        this.markProp.setPositionType(PositionType.BINDING);
        this.markProp.setSkipPages(1);
        super.createPDF(this::setMarkers, this::NoSection);
    }

}
