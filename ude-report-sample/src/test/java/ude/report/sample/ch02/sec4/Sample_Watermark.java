/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch02.sec4;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.itext2.marker.markinfo.MarkInfo;
import com.iisigroup.ude.report.itext2.marker.markinfo.PropertiesMarkInfo;
import com.iisigroup.ude.report.itext2.marker.watermark.TextWatermarkProperties;
import com.iisigroup.ude.report.itext2.text.font.CNS11643;
import com.iisigroup.ude.report.layout.ItemPosition;
import com.iisigroup.ude.report.layout.paging.PagingHeaderZH;
import com.iisigroup.ude.report.utils.ReportTextUtils;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;

@SuppressWarnings("unused")
public class Sample_Watermark extends AbstractSample {

    @Test
    public void test_TextWaterMark_BASIC() throws IOException {
        // ! [TEST_INVOKE]
        final File pdfFile = super.createPDF(pdfDocument -> {

            final TextWatermarkProperties markProp = new TextWatermarkProperties("測試測試「" + "𠀝" + "」");
            //markProp.setStrikethru(true);
            markProp.setHollow(true);
            markProp.setFontType(CNS11643.KAI);
            markProp.setFontSize(48);
            pdfDocument.setMarkMaker(new PropertiesMarkInfo(markProp));

            for (int i = 1; i <= 100; i++) {
                pdfDocument.writeText(" " + ReportTextUtils.CHINESE_BLOCK + i);
            }
        });

        //        // ! [TEST_ASSERT]
        //        try (BufferedInputStream in = UdeFileUtils.createInputStream(pdfFile)) {
        //            final PdfReader r = new PdfReader(in);
        //            final int numberOfPages = r.getNumberOfPages();
        //            Assert.assertEquals(4, numberOfPages);
        //            final PdfTextExtractor pdfTextExtractor = new PdfTextExtractor(r);
        //            final String lastPageText = pdfTextExtractor.getTextFromPage(4);
        //            StringAssert.assertContains("第4頁，共", lastPageText);
        //            StringAssert.assertContains("P4. 我是第四頁", lastPageText);
        //        }
    }

}
