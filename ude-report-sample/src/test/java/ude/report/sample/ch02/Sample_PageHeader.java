/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch02;

import org.junit.Test;

import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.itext2.utils.PaintTool;
import com.iisigroup.ude.report.layout.ItemPosition;
import com.iisigroup.ude.report.layout.PageHeaderEN;
import com.iisigroup.ude.report.layout.PageHeaderZH;
import com.iisigroup.ude.report.layout.RepeatMode;
import com.iisigroup.ude.report.utils.PointF;
import com.iisigroup.ude.report.utils.ReportTextUtils;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;

import ude.report.sample.AbstractSample;
import ude.report.sample.PDFSampleContent;

public class Sample_PageHeader extends AbstractSample {

    private String noteText = "TES";

    public void outputRepeatText(final PDFDocument pdfDocument) {
        pdfDocument.writeText(this.noteText);
        for (int i = 1; i <= 100; i++) {
            pdfDocument.writeText(" " + ReportTextUtils.CHINESE_BLOCK + i);
        }
    }

    public void outputRepeatPage(final PDFDocument pdfDocument) {
        pdfDocument.writeText(this.noteText);
        for (int i = 1; i <= 1000; i++) {
            pdfDocument.writeText(" " + ReportTextUtils.CHINESE_BLOCK + i);
            pdfDocument.newPage();
        }
    }

    public void outputRepeatText2(final PDFDocument pdfDocument) {
        pdfDocument.writeText(this.noteText);
        for (int i = 1; i <= 100; i++) {
            final float bottom = pdfDocument.getDocument().bottom();
            final float v1 = pdfDocument.getPdfWriter().getVerticalPosition(true);
            pdfDocument.writeText(bottom + " " + ReportTextUtils.CHINESE_BLOCK + i + "   " + v1 + "," + (v1 - 12));
            final float v2 = pdfDocument.getPdfWriter().getVerticalPosition(true);

            final PdfContentByte contentByte = pdfDocument.getPdfWriter().getDirectContent();

            if (v1 > v2) {
                final PointF p1 = new PointF(10, v1);
                final PointF p2 = new PointF(300, v2);
                PaintTool.Default.drawLines(contentByte, p1, p2);
            } else {
                final PointF p1 = new PointF(300, v1);
                final PointF p2 = new PointF(400, v2);
                PaintTool.Default.drawLines(contentByte, p1, p2);
            }
            {
                final PointF p1 = new PointF(400, bottom - pdfDocument.getLayoutFooterExtra());
                final PointF p2 = new PointF(500, bottom);
                PaintTool.Default.drawLines(contentByte, p1, p2);
            }
        }
    }

    @Test
    public void test_BASIC_BASELINE() {

        this.noteText = "使用預設 StringHeader 及換行範例，顯示對齊位置";
        final PDFSampleContent setting = pdfDocument -> {
            pdfDocument.setPageSize(PageSize.A5.rotate());
            final LayoutInfo layoutInfo = super.getDocumentManager().createLayoutInfo();
            final String text = ReportTextUtils.CHINESE_BLOCK + "中文" + ReportTextUtils.CHINESE_BLOCK + "\n"
                    + ReportTextUtils.CHINESE_BLOCK + "第二行" + ReportTextUtils.CHINESE_BLOCK;

            layoutInfo.setHeader(ItemPosition.LeftHeader, text, 14);
            layoutInfo.setHeader(ItemPosition.CenterHeader, text, 12);
            layoutInfo.setHeader(ItemPosition.RightHeader, RepeatMode.FIRST_PAGE, text, 10);

            layoutInfo.setHeader(ItemPosition.LeftFooter, text, 14);
            layoutInfo.setHeader(ItemPosition.CenterFooter, text, 12);
            layoutInfo.setHeader(ItemPosition.RightFooter, text, 10);
            pdfDocument.setLayoutInfo(layoutInfo);
        };
        super.createPDF(setting.andThen(this::outputRepeatText));
    }

    @Test
    public void test_BASIC_PageHeader() {

        this.noteText = "";
        this.noteText += "基本頁次輸出(預設前後綴文字)\n";
        this.noteText += "　左上-" + "英文頁次";
        this.noteText += "／中上-" + "英文頁次／總頁數";
        this.noteText += "／右上-" + "英文總頁數\n";
        this.noteText += "　左下-" + "中文頁次";
        this.noteText += "／中下-" + "中文頁次／總頁數";
        this.noteText += "／右下-" + "中文總頁數";
        final PDFSampleContent setting = pdfDocument -> {
            pdfDocument.setPageSize(PageSize.A5.rotate());
            final LayoutInfo layoutInfo = new LayoutInfo();
            layoutInfo.setHeader(ItemPosition.LeftHeader, PageHeaderEN.PAGE, 14);
            layoutInfo.setHeader(ItemPosition.CenterHeader, PageHeaderEN.BOTH, 14);
            layoutInfo.setHeader(ItemPosition.RightHeader, PageHeaderEN.TOTAL_PAGES, 14);
            layoutInfo.setHeader(ItemPosition.LeftFooter, PageHeaderZH.PAGE, 14);
            layoutInfo.setHeader(ItemPosition.CenterFooter, PageHeaderZH.BOTH, 14);
            layoutInfo.setHeader(ItemPosition.RightFooter, PageHeaderZH.TOTAL_PAGES, 14);
            pdfDocument.setLayoutInfo(layoutInfo);
        };
        super.createPDF(setting.andThen(this::outputRepeatPage));
    }

}
