/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch02.sec3;

import org.junit.Test;

import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.layout.ItemPosition;
import com.iisigroup.ude.report.layout.RepeatMode;
import com.iisigroup.ude.report.layout.paging.PagingHeaderEN;
import com.iisigroup.ude.report.layout.paging.PagingHeaderZH;
import com.iisigroup.ude.report.layout.paging.PagingItem;
import com.iisigroup.ude.report.utils.ReportTextUtils;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;
import ude.report.sample.PDFSampleContent;

public class Sample_PageHeader_BasicPage extends AbstractSample {

    private String noteText;

    public void outputRepeatPage(final PDFDocument pdfDocument) {
        pdfDocument.writeText(this.noteText);
        for (int i = 1; i <= 2; i++) {
            pdfDocument.writeText(ReportTextUtils.CHINESE_BLOCK + i);
            pdfDocument.newPage();
        }
    }

    public void outputRepeatSection(final PDFDocument pdfDocument) {
        pdfDocument.writeText(this.noteText);
        for (int i = 1; i <= 4; i++) {
            pdfDocument.writeText(" ");
            pdfDocument.newPage();
        }
        pdfDocument.newSection();
        for (int i = 1; i <= 2; i++) {
            pdfDocument.writeText(" ");
            pdfDocument.newPage();
        }
    }

    @Test
    public void test_BasicPaging() {

        this.noteText = "";
        this.noteText += "基本頁次輸出，使用預設前後綴文字\n";
        //
        this.noteText += "左上-" + "英文頁次";
        this.noteText += "／中上-" + "英文頁次／總頁數";
        this.noteText += "／右上-" + "英文總頁數\n";
        //
        this.noteText += "左下-" + "中文頁次";
        this.noteText += "／中下-" + "中文頁次／總頁數";
        this.noteText += "／右下-" + "中文總頁數\n ";
        final PDFSampleContent setting = pdfDocument -> {
            pdfDocument.setupPageSize(PageSize.A5.rotate());
            final LayoutInfo layoutInfo = new LayoutInfo();
            layoutInfo.setPagingHeader(ItemPosition.LeftHeader, PagingHeaderEN.PAGE, 14);
            layoutInfo.setPagingHeader(ItemPosition.CenterHeader, PagingHeaderEN.BOTH, 14);
            layoutInfo.setPagingHeader(ItemPosition.RightHeader, PagingHeaderEN.TOTAL_PAGES, 14);
            layoutInfo.setPagingHeader(ItemPosition.LeftFooter, PagingHeaderZH.PAGE, 10);
            layoutInfo.setPagingHeader(ItemPosition.CenterFooter, PagingHeaderZH.BOTH, 10);
            layoutInfo.setPagingHeader(ItemPosition.RightFooter, PagingHeaderZH.TOTAL_PAGES, 10);
            pdfDocument.setLayoutInfo(layoutInfo);
        };
        super.createPDF(setting.andThen(this::outputRepeatPage));
    }

    @Test
    public void test_Sections() {

        this.noteText = "";
        final PDFSampleContent setting = pdfDocument -> {
            pdfDocument.setupPageSize(PageSize.A7.rotate());
            final LayoutInfo layout = new LayoutInfo();

            // 一左一右
            layout.setPagingHeader(ItemPosition.LeftHeader, RepeatMode.ODD_PAGES, PagingHeaderEN.SECTION_AND_PAGE, 10);
            layout.setPagingHeader(ItemPosition.RightHeader, RepeatMode.EVEN_PAGES, PagingHeaderEN.SECTION_AND_PAGE, 10);

            // 自訂格式文字
            layout.setPagingHeader(ItemPosition.CenterHeader, PagingItem.BOTH_IN_SECTION.conjunction(" of "), 10);
            layout.setPagingHeader(ItemPosition.LeftFooter, PagingHeaderEN.TOTAL_PAGES, 8);
            layout.setPagingHeader(ItemPosition.CenterFooter, PagingItem.SECTION.prefix("Sec. "), 10);
            layout.setPagingHeader(ItemPosition.RightFooter, PagingItem.SECTION_PAGES.prefix("Section Pages:"), 8);

            pdfDocument.setLayoutInfo(layout);
        };
        super.createPDF(setting.andThen(this::outputRepeatSection));
    }

}

//public void outputRepeatText2(final PDFDocument pdfDocument) {
//    pdfDocument.writeText(this.noteText);
//    for (int i = 1; i <= 100; i++) {
//        final float bottom = pdfDocument.getDocument().bottom();
//        final float v1 = pdfDocument.getPdfWriter().getVerticalPosition(true);
//        pdfDocument.writeText(bottom + " " + ReportTextUtils.CHINESE_BLOCK + i + "   " + v1 + "," + (v1 - 12));
//        final float v2 = pdfDocument.getPdfWriter().getVerticalPosition(true);
//
//        final PdfContentByte contentByte = pdfDocument.getPdfWriter().getDirectContent();
//
//        if (v1 > v2) {
//            final PointF p1 = new PointF(10, v1);
//            final PointF p2 = new PointF(300, v2);
//            PaintTool.Default.drawLines(contentByte, p1, p2);
//        } else {
//            final PointF p1 = new PointF(300, v1);
//            final PointF p2 = new PointF(400, v2);
//            PaintTool.Default.drawLines(contentByte, p1, p2);
//        }
//        {
//            final PointF p1 = new PointF(400, bottom - pdfDocument.getLayoutFooterExtra());
//            final PointF p2 = new PointF(500, bottom);
//            PaintTool.Default.drawLines(contentByte, p1, p2);
//        }
//    }
//}
