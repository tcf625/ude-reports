/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch02.sec3;

import org.junit.Test;

import com.iisigroup.ude.report.common.FontStyle;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.layout.ItemPosition;
import com.iisigroup.ude.report.utils.ReportTextUtils;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;
import ude.report.sample.PDFSampleContent;

public class Sample_PageHeader_BasicText extends AbstractSample {

    private String noteText = "";

    public void outputRepeatText(final PDFDocument pdfDocument) {
        pdfDocument.writeText(this.noteText);
        for (int i = 1; i <= 50; i++) {
            pdfDocument.writeText(ReportTextUtils.CHINESE_BLOCK + i);
        }
    }

    @Test
    public void test_BASELINE() {
        this.noteText = ReportTextUtils.CHINESE_BLOCK + "使用預設 StringHeader 及換行範例，顯示對齊位置\n";
        this.noteText += ReportTextUtils.CHINESE_BLOCK + "在預設模式下，PDF行距為2PIXEL";
        final PDFSampleContent setting = pdfDocument -> {
            pdfDocument.setupPageSize(PageSize.A6.rotate());
            final String text = ReportTextUtils.CHINESE_BLOCK + "中文" + ReportTextUtils.CHINESE_BLOCK + "\n"
                    + ReportTextUtils.CHINESE_BLOCK + "第二行" + ReportTextUtils.CHINESE_BLOCK;
            final LayoutInfo layoutInfo = new LayoutInfo();
            layoutInfo.setTextHeader(ItemPosition.LeftHeader, text, 14);
            layoutInfo.setTextHeader(ItemPosition.CenterHeader, text, 12);
            layoutInfo.setTextHeader(ItemPosition.RightHeader, text, 8);
            layoutInfo.setTextHeader(ItemPosition.LeftFooter, text, 8);
            layoutInfo.setTextHeader(ItemPosition.CenterFooter, text, 12);
            layoutInfo.setTextHeader(ItemPosition.RightFooter, text, 14);
            pdfDocument.setLayoutInfo(layoutInfo);
        };
        super.createPDF(setting.andThen(this::outputRepeatText));
    }

    @Test
    public void test_fontStyle() {
        // this.noteText = ReportTextUtils.CHINESE_BLOCK + "使用預設 StringHeader 及換行範例，顯示對齊位置\n";
        // this.noteText += ReportTextUtils.CHINESE_BLOCK + "在預設模式下，PDF行距為2PIXEL";
        final PDFSampleContent setting = pdfDocument -> {
            pdfDocument.setupPageSize(PageSize.A6.rotate());
            final String text = ReportTextUtils.CHINESE_BLOCK + "中文" + ReportTextUtils.CHINESE_BLOCK + "\n"
                    + ReportTextUtils.CHINESE_BLOCK + "%s" + ReportTextUtils.CHINESE_BLOCK;
            final LayoutInfo layoutInfo = new LayoutInfo();
            layoutInfo.setTextHeader(ItemPosition.LeftHeader, String.format(text, "BOLD"), 12, FontStyle.BOLD);
            layoutInfo.setTextHeader(ItemPosition.CenterHeader, String.format(text, "ITALIC"), 12, FontStyle.ITALIC);
            layoutInfo.setTextHeader(ItemPosition.RightHeader, String.format(text, "UNDERLINE"), 12, FontStyle.UNDERLINE);

            layoutInfo.setTextHeader(ItemPosition.LeftFooter, String.format(text, "STRIKETHRU"), 12, FontStyle.STRIKETHRU);
            layoutInfo.setTextHeader(ItemPosition.CenterFooter, String.format(text, "BOLDITALIC"), 12, FontStyle.BOLDITALIC);
            layoutInfo.setTextHeader(ItemPosition.RightHeader, String.format(text, "UNDERLINE"), 12, FontStyle.UNDERLINE);
            pdfDocument.setLayoutInfo(layoutInfo);
        };
        super.createPDF(setting.andThen(this::outputRepeatText));
    }

}
