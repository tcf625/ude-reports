/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch02;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.layout.ItemPosition;
import com.iisigroup.ude.report.layout.PageHeaderZH;
import com.iisigroup.ude.report.table.format.DocumentAlign;
import com.iisigroup.ude.report.utils.LengthUnit;
import com.iisigroup.ude.util.io.UdeFileUtils;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;

import junitx.framework.StringAssert;
import ude.report.sample.AbstractSample;

public class Sample_Pages extends AbstractSample {

    @Test
    public void test_newPage() throws IOException {
        // ! [TEST_INVOKE]
        final File pdfFile = super.createPDF(pdfDocument -> {
            final LayoutInfo layoutInfo = new LayoutInfo();
            layoutInfo.setHeader(ItemPosition.CenterFooter, PageHeaderZH.BOTH, 14);
            pdfDocument.setLayoutInfo(layoutInfo);
            pdfDocument.setPageSize(PageSize.A8.rotate());
            pdfDocument.writeText("P1"); // 一開始在 P1
            pdfDocument.newPage(); // 進入 P2
            pdfDocument.newPage(); // 連續兩次呼叫兩次 newPage() 不會有空白頁
            pdfDocument.writeText("P2");
            pdfDocument.newPage(); // 進入 P3
            pdfDocument.newPage();
            pdfDocument.newPage();
            pdfDocument.newPage(true); // 強制換頁後，才進入 P4
            pdfDocument.writeText("P4. 我是第四頁");
            pdfDocument.newPage();     // 後續未輸出內容，P5 不會產生。 
        });
        // ! [TEST_ASSERT]
        try (BufferedInputStream in = UdeFileUtils.createInputStream(pdfFile)) {
            final PdfReader r = new PdfReader(in);
            final int numberOfPages = r.getNumberOfPages();
            Assert.assertEquals(4, numberOfPages);
            final PdfTextExtractor pdfTextExtractor = new PdfTextExtractor(r);
            final String lastPageText = pdfTextExtractor.getTextFromPage(4);
            StringAssert.assertContains("第4頁，共", lastPageText);
            StringAssert.assertContains("P4. 我是第四頁", lastPageText);
        }
    }

    @Test
    public void test_forceNewPage() {
        super.createPDF(pdfDocument -> {
            final LayoutInfo layoutInfo = new LayoutInfo();
            layoutInfo.setHeader(ItemPosition.CenterFooter, PageHeaderZH.BOTH, 12);
            pdfDocument.setLayoutInfo(layoutInfo);
            pdfDocument.setPageSize(PageSize.A8.rotate());
            // !
            Assert.assertFalse("一開始未換頁", pdfDocument.isPageChanged());
            Assert.assertTrue(pdfDocument.isNewPageBegin());
            // !
            pdfDocument.writeText("P1");
            Assert.assertFalse(pdfDocument.isPageChanged());
            Assert.assertFalse(pdfDocument.isNewPageBegin());
            // !
            pdfDocument.newPage(true);   // 強制換頁
            Assert.assertTrue("因為 newPage() 觸發換頁事件", pdfDocument.isPageChanged());
            Assert.assertTrue("新頁面還沒有輸出任何內容，但關檔時強制輸出新頁。", pdfDocument.isNewPageBegin());
        });
    }

    @Test
    public void test_PageChangeChecking() {
        super.createPDF(pdfDocument -> {
            pdfDocument.setPageSize(PageSize.A6.rotate());
            for (int i = 0; i < 200; i++) {
                final String prefix = i + StringUtils.repeat(" ", i % 4 + 1);
                pdfDocument.writeText(prefix + "-1換頁測試\n" + prefix + "-2換頁測試\n" + prefix + "-3換頁測試\n");
                if (pdfDocument.isPageChanged()) {
                    if (pdfDocument.isNewPageBegin()) {
                        pdfDocument.newPage();
                        pdfDocument.writeText("前項輸出剛好導致換頁，新頁面還沒有內容輸出", 10, DocumentAlign.CENTER);
                    } else {
                        pdfDocument.writeText("前項輸出導致換頁，而且部分內容已輸出到新頁面", 10, DocumentAlign.CENTER);
                    }
                }
            }
        });
    }

    @Test
    public void test_Margin() {
        super.createPDF(pdfDocument -> {
            // ! 定義頁面大小.
            pdfDocument.setPageSize(PageSize.A4.rotate());
            // ! 定義四周邊界大小.
            final float marginLeft = LengthUnit.CM.trans(2.54f);  // 以 公分為單位
            final float marginRight = 36;                         // 以 pixel 為單位
            final float marginTop = LengthUnit.MM.trans(12.7f);   // 以 公厘為單位
            final float marginBottom = LengthUnit.INCH.trans(1);  // 以英吋為單位
            final LayoutInfo layoutInfo = new LayoutInfo(marginLeft, marginRight, marginTop, marginBottom);

            // ! 定義上下頁首尾間距.
            layoutInfo.setHeaderExtra(36);
            layoutInfo.setFooterExtra(72);

            // ! 設定版面資訊
            pdfDocument.setLayoutInfo(layoutInfo);
        });
    }

}
